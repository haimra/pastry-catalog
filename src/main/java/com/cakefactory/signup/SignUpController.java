package com.cakefactory.signup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @GetMapping
    public ModelAndView signupView() {
        return new ModelAndView("signup");
    }

    @PostMapping
    public ModelAndView signup(@ModelAttribute Account account, @ModelAttribute Address address ) {
        return new ModelAndView("redirect:/");
    }
}
