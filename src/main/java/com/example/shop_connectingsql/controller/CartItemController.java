package com.example.shop_connectingsql.controller;

import com.example.shop_connectingsql.exceptions.ResourceNotFoundException;
import com.example.shop_connectingsql.model.Cart;
import com.example.shop_connectingsql.model.User;
import com.example.shop_connectingsql.response.ApiResponse;
import com.example.shop_connectingsql.service.cart.ICartItemService;
import com.example.shop_connectingsql.service.cart.ICartService;
import com.example.shop_connectingsql.service.user.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@RestController
@RequestMapping("${apiPrefix}/cartItems")
public class CartItemController {
    private final ICartItemService cartItemService;
    private final ICartService cartService;
    private final IUserService userService;

    public CartItemController(ICartItemService cartItemService, ICartService cartService, IUserService userService) {
        this.cartItemService = cartItemService;
        this.cartService = cartService;
        this.userService = userService;
    }


    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(
                                                     @RequestParam Long productId,
                                                     @RequestParam Integer quantity) {
        try {
            User user = userService.getUserById(1L);
            Cart cart= cartService.initializeNewCart(user);
//            if (cartId == null) {
//                Cart cartId= cartService.initializeNewCart(user);
//            }
            cartItemService.addItemToCart(cart.getId(), productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Add Item Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        try {
            cartItemService.removeItemFromCart(cartId, itemId);
            return ResponseEntity.ok(new ApiResponse("Remove Item Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/cart/{cartId}/item/{itemId}/update")
    public  ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId,
                                                           @PathVariable Long itemId,
                                                           @RequestParam Integer quantity) {
        try {
            cartItemService.updateItemQuantity(cartId, itemId, quantity);
            return ResponseEntity.ok(new ApiResponse("Update Item Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }
}
