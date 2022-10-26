package com.softserve.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
public class SurveyDTO implements Serializable {
    @Getter @Setter
    private PollDTO poll;

    @Getter @Setter
    private PollOptionDTO pollOptionDTO;
}
