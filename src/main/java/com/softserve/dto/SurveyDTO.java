package com.softserve.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter @Setter
@ToString
public class SurveyDTO implements Serializable {
    private PollDTO poll;
}
