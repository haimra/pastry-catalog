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

    private final RegistrationService registrationService;
    @Autowired
    public SignUpController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ModelAndView signupView() {
        return new ModelAndView("signup");
    }

    @PostMapping
    public ModelAndView signup(@ModelAttribute SignUp signUp) {
        registrationService.register(signUp.getAccount(),signUp.getAddress());
        return new ModelAndView("redirect:/");
    }
}
