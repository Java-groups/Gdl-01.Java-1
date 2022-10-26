package com.softserve.controller.survey;

import com.softserve.dto.SurveyDTO;
import com.softserve.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SurveyRestController {

    @Autowired
    private SurveyService surveyService;

    @PostMapping(value = "/api/surveys", consumes = "application/json")
    public void createSurvey(@RequestBody SurveyDTO surveyDTO){
        surveyService.save(surveyDTO);
    }
}
