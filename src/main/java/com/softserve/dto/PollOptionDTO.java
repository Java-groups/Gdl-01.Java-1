package com.softserve.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
public class PollOptionDTO implements Serializable {
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String value;

    @Getter @Setter
    private Integer order;

    @Getter @Setter
    private Integer pollID;
}
