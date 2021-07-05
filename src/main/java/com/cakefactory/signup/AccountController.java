package com.cakefactory.signup;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AddressService addressService;

    @Autowired
    public AccountController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ModelAndView accountView(Map<String, Object> model, Principal principal) {
        if (principal == null) {
            return new ModelAndView("redirect:login");
        }
        final String email = principal.getName();
        final var address = addressService.findByEmail(email);
        model.put("address", address);
        return new ModelAndView("account",model);
    }

    @PostMapping
    public ModelAndView updateAddress(@ModelAttribute("address") Address address,Principal principal) {
        if (principal == null) {
            return new ModelAndView("redirect:login");
        }
        final String email = principal.getName();
        addressService.update(address, email);
        return new ModelAndView("redirect:/");
    }
}
