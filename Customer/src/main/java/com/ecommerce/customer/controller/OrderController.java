package com.ecommerce.customer.controller;

import com.ecommerce.library.dto.OrderDto;
import com.ecommerce.library.model.Customer;
import com.ecommerce.library.model.ShoppingCart;
import com.ecommerce.library.service.CustomerService;
import com.ecommerce.library.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/check-out")
    public String checkout(Model model, Principal principal){
        try{
        if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);

            if(customer.getPhoneNumber() == null || customer.getAddress()== null){
                model.addAttribute("error", "You must fill the phone and address before checkout!");
                return "redirect:/account";
            }else{
                model.addAttribute("customer", customer);
                ShoppingCart cart = customer.getShoppingCart();
                model.addAttribute("cart", cart);
            }
        }catch (Exception e){
            System.out.println("-----Error Checkout"+ e.getMessage());
        }


        return "checkout";
    }


//    @GetMapping("/order/{customerId}")
//    public String order(@PathVariable("customerId") Long customerId,Model model, Principal principal){
//            if(principal==null){
//                return "redirect:/login";
//            }
//            List<OrderDto> orderDtos = new ArrayList<>();
//            Customer customer = customerService.findByUsername(principal.getName());
//            if(customerId == customer.getId()){
//                orderDtos   = orderService.findAllByCustomerId(customer.getId());
//                model.addAttribute("listOrder", orderDtos);
//            }else {
//                return "redirect:/login";
//            }
//
//        return "order";
//    }

    @GetMapping("/order/{customerId}")
    public String order(@PathVariable("customerId") Long customerId, Model model, Principal principal){
        if(principal==null){
            return "redirect:/login";
        }
        List<OrderDto> orderDtos = new ArrayList<>();
        Customer customer = customerService.findByUsername(principal.getName());
        if(customerId == customer.getId()){
            orderDtos   = orderService.findAllByCustomerId(customer.getId());
            model.addAttribute("listOrder", orderDtos);
        }else {
            return "redirect:/login";
        }

        return "order";
    }

}
