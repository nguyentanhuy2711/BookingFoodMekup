package com.ecommerce.customer.controller;

import com.ecommerce.library.dto.CartItemDto;
import com.ecommerce.library.model.Customer;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.ShoppingCart;
import com.ecommerce.library.service.CartService;
import com.ecommerce.library.service.CustomerService;
import com.ecommerce.library.service.ProductService;
import com.ecommerce.library.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CartService cartService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductService productService;



    @ResponseBody
    @GetMapping("/list-cart")
    public List<CartItemDto> CartResponse(Principal principal){
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        ShoppingCart shoppingCart = customer.getShoppingCart();
        List<CartItemDto> cartItemDtos = cartService.getAllCartItem1(shoppingCart.getId());
        return cartItemDtos;
    }

    @ResponseBody
    @PostMapping(value = "/update-cart-way2")
    public ShoppingCart updateCart(@RequestParam("quantity") int quantity,
                             @RequestParam("id") Long productId,
                             Principal principal
    ){
        System.out.println("---------------- out -------------");
        ShoppingCart shoppingCart = new ShoppingCart();
        if(principal == null){
             System.out.println("You need Login");
        }else{
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            Product product = productService.getProductById(productId);
            shoppingCart = shoppingCartService.updateItemInCart(product, quantity, customer);

        }
        System.out.println("---------------- shoppingCart -------------"+shoppingCart);
        return  shoppingCart;
    }
}
