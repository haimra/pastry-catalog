package com.cakefactory.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        final String emailAddress = signUp.getEmailAddress();
        if (registrationService.accountExists(emailAddress)) {
            return new ModelAndView("redirect:/login");
        }
        registrationService.register(signUp.getAccount(), signUp.getAddress());
        //login immediately
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(emailAddress, "", List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(token);
        return new ModelAndView("redirect:/");
    }
}
