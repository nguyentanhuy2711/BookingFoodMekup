package com.ecommerce.customer.controller;

import com.ecommerce.library.dto.CartItemDto;
import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.model.CartItem;
import com.ecommerce.library.model.Customer;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.ShoppingCart;
import com.ecommerce.library.service.CartService;
import com.ecommerce.library.service.CustomerService;
import com.ecommerce.library.service.ProductService;
import com.ecommerce.library.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



@Controller
public class CartController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;
    @GetMapping("/cart")
    public String cart(Model model, Principal principal, HttpSession session){
        if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        ShoppingCart shoppingCart = customer.getShoppingCart();
        System.out.println("------------- shoppingCartID ================ "+ shoppingCart.getId());
        if(shoppingCart == null){
            model.addAttribute("check", "No item in your cart");
            session.setAttribute("totalItems", 0);
            model.addAttribute("subTotal", 0);
        }else{
            List<CartItemDto> cartItemDtos = cartService.getAllCartItem1(shoppingCart.getId());

            session.setAttribute("totalItems", shoppingCart.getTotalItems());
            model.addAttribute("subTotal", shoppingCart.getTotalPrices());

            model.addAttribute("shoppingCart", cartItemDtos);
            List<String> listString = new ArrayList<>();
//            listString.add("1");
//            listString.add("2");
//            model.addAttribute("lstTest", listString);
        }
        return "CartCopy";
    }


    @PostMapping("/add-to-cart")
    public String addItemToCart(
            @RequestParam("id") Long productId,
            @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
            Principal principal,
            HttpServletRequest request){

        if(principal == null){
            return "redirect:/login";
        }
        Product product = productService.getProductById(productId);
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);

        ShoppingCart cart = shoppingCartService.addItemToCart(product, quantity, customer);
        return "redirect:" + request.getHeader("Referer");

    }


    @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=update")
    public String updateCart(@RequestParam("quantity") int quantity,
                             @RequestParam("id") Long productId,
                             Model model,
                             Principal principal
                             ){

        if(principal == null){
            return "redirect:/login";
        }else{
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            Product product = productService.getProductById(productId);
            ShoppingCart cart = shoppingCartService.updateItemInCart(product, quantity, customer);

            model.addAttribute("shoppingCart", cart);
            return "redirect:/cart";
        }

    }


        @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=delete")
    public String deleteItemFromCart(@RequestParam("id") Long productId,
                                     Model model,
                                     Principal principal){
        if(principal == null){
            return "redirect:/login";
        }else{
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            Product product = productService.getProductById(productId);
            ShoppingCart cart = shoppingCartService.deleteItemFromCart(product, customer);
            model.addAttribute("shoppingCart", cart);
            return "redirect:/cart";
        }

    }




}
