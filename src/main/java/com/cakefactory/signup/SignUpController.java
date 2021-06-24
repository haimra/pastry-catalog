package com.cakefactory.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private final AddressService addressService;
    private final AccountService accountService;

    @Autowired
    public SignUpController(AddressService addressService, AccountService accountService) {
        this.addressService = addressService;
        this.accountService = accountService;
    }

    @GetMapping
    public ModelAndView signupView() {
        return new ModelAndView("signup");
    }

    @PostMapping
    public ModelAndView signup(@ModelAttribute SignUp signUp) {
        addressService.save(signUp.getAddress());
        accountService.save(signUp.getAccount());
        return new ModelAndView("redirect:/");
    }
}
