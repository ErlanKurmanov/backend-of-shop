package com.example.dream_shop_connectingsql.service.order;

import com.example.dream_shop_connectingsql.dto.OrderDto;
import com.example.dream_shop_connectingsql.enums.OrderStatus;
import com.example.dream_shop_connectingsql.exceptions.ResourceNotFoundException;
import com.example.dream_shop_connectingsql.model.Cart;
import com.example.dream_shop_connectingsql.model.Order;
import com.example.dream_shop_connectingsql.model.OrderItem;
import com.example.dream_shop_connectingsql.model.Product;
import com.example.dream_shop_connectingsql.repository.OrderRepository;
import com.example.dream_shop_connectingsql.repository.ProductRepository;
import com.example.dream_shop_connectingsql.service.cart.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, CartService cartService, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.cartService = cartService;
        this.modelMapper = modelMapper;
    }


    @Transactional
    @Override
    public Order placeOrder(Long userId) {
        //Get our cart by user ID (custom interface)
        Cart cart   = cartService.getCartByUserId(userId);

        //get an order object based on the cart
        Order order = createOrder(cart);

        //Assigning to
        List<OrderItem> orderItemList = createOrderItems(order, cart);

        //new created object of List order items, sets to Order object
        order.setOrderItems(new HashSet<>(orderItemList));

        //sets to total amount in the Order object, based on refreshed calculation
        order.setTotalAmount(calculateTotalAmount(orderItemList));

        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(cart.getId());
        return savedOrder;
    }


    //Creates new object order, and sets necessary information (user, status, date) to it
    private Order createOrder(Cart cart) {
        Order order = new Order();
       order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return  order;
    }

    // Retrieves items in the cart, loops, sets to OrderItem, and returns as a List
     private List<OrderItem> createOrderItems(Order order, Cart cart) {
        return  cart.getItems().stream().map(cartItem -> {

            Product product = cartItem.getProduct();

            product.setInventory(product.getInventory() - cartItem.getQuantity());

            productRepository.save(product);

            return  new OrderItem(
                    order,
                    product,
                    cartItem.getQuantity(),
                    cartItem.getUnitPrice());
        }).toList();

     }

     //Calculating list of items total amount
     private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList) {
        return  orderItemList
                .stream()
                .map(item -> item.getPrice()
                        .multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
     }

    @Override
    public OrderDto getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .map(this :: convertToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }


    @Override
    public List<OrderDto> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return  orders.stream().map(this :: convertToDto).toList();
    }

    @Override
    public OrderDto convertToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }
}