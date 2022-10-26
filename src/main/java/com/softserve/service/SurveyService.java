package com.softserve.service;

import com.softserve.dto.PollOptionDTO;
import com.softserve.dto.SurveyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {
    @Autowired
    PollOptionService pollOptionService;

    @Autowired
    PollService pollService;

    public void save(SurveyDTO surveyDTO) {
        pollService.save(surveyDTO.getPoll());
        pollOptionService.savePollOptions(surveyDTO.getPoll().getPollOptions());
    }
}
