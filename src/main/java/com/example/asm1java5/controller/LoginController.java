package com.example.asm1java5.controller;

import com.example.asm1java5.dto.request.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@Slf4j
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/login")
    public String login() {
        return "login_view/login";
    }

    @PostMapping("/login-action")
    public String loginAction(LoginRequest loginRequest) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

            Authentication authentication = authenticationManager.authenticate(authToken);

            if (authentication.isAuthenticated()) {

                SecurityContextHolder.getContext().setAuthentication(authentication);
                return "redirect:/sell-manager/index";
            } else {
                return "redirect:/auth/login-errors";
            }
        } catch (Exception e) {
            log.error("Authentication failed: ", e);
            return "redirect:/auth/login-errors";
        }
    }

    @GetMapping("/login-errors")
    public String loginError(Model model) {
        model.addAttribute("loginError", "Login failed, please check your account and password again");
        return "login_view/login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "login_view/access_denied";
    }
}
