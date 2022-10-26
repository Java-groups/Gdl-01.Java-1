package com.softserve.controller.survey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SurveyController {

    @GetMapping("/surveys")
    public String showSurveys(Model model){
        return "admin/surveys";
    }
}
