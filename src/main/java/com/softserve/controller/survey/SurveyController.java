package com.softserve.controller.survey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SurveyController {

    @GetMapping("/surveys")
    public String showSurveys(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        java.util.Collection<? extends GrantedAuthority> col = authentication.getAuthorities();

        model.addAttribute("role", col.toArray()[0]);
        return "admin/surveys";
    }
}
