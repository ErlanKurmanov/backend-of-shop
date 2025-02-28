package com.example.shop_connectingsql.service.cart;

import com.example.shop_connectingsql.exceptions.ResourceNotFoundException;
import com.example.shop_connectingsql.model.Cart;
import com.example.shop_connectingsql.model.CartItem;
import com.example.shop_connectingsql.model.Product;
import com.example.shop_connectingsql.repository.CartItemRepository;
import com.example.shop_connectingsql.repository.CartRepository;
import com.example.shop_connectingsql.service.product.IProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartItemService implements ICartItemService{
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final IProductService productService;
    private final ICartService cartService;

    public CartItemService(CartItemRepository cartItemRepository, CartRepository cartRepository, IProductService productService, ICartService cartService) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartService = cartService;
    }

    @Override
    public void addItemToCart(Long cartId, Long productId, int quantity) {
        //1. Get the cart
        //2. Get the product
        //3. Check if the product already in the cart
        //4. If Yes, then increase the quantity with the requested quantity
        //5. If No, then initiate a new CartItem entry.

        //1. get tha Cart instance
        Cart cart = cartService.getCart(cartId);

        //2. get Product object
        Product product = productService.getProductById(productId);

        //3. Search in the cart for already added product, if not found creates a new CartItem
        CartItem cartItem = cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElse(new CartItem());

        //4. If not found, set to the new instantiated object
        if (cartItem.getId() == null) {
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
        }
        else {
            //4. If found, it increases quantity of the product in the cart
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        //retrieves from database cart by id, and creates object cart
        Cart cart = cartService.getCart(cartId);

        //CartItem object (created and assigned to itemToRemove) we get from the method,
        // which found with id cart and product
        CartItem itemToRemove = getCartItem(cartId, productId);

        //removeItem method in the cart model removes itemToRemove from the Set<CartItem>,
        // and in the CartItem object set to null to delete link
        cart.removeItem(itemToRemove);

        //just saving changed Cart object with removed from the Set our item
        cartRepository.save(cart);
    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {
        //retrieve cart from database, and assigned that object to cart
        Cart cart = cartService.getCart(cartId);

        //it searches in the Set<CartItem> of Cart object
        cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))

                //once it found retrieve it
                .findFirst()

                //sets a new provided quantity, and updates total price, unit price
                .ifPresent(item -> {
                    item.setQuantity(quantity);
                    item.setUnitPrice(item.getProduct().getPrice());
                    item.setTotalPrice();
                });


        BigDecimal totalAmount = cart.getItems()
                .stream().map(CartItem ::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);
    }


    // Finds by cart id and product id in the database our cart item
    // and returns it
    @Override
    public CartItem getCartItem(Long cartId, Long productId) {
        //retrieves from database cart by id and assigned to cart object
        Cart cart = cartService.getCart(cartId);


        return  cart.getItems()
                .stream()

                //iterates through list of items in the cart, once it founds in the item by id of product
                .filter(item -> item.getProduct().getId().equals(productId))

                //returns first matched item, if not found returns else:
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Item not found"));
    }
}