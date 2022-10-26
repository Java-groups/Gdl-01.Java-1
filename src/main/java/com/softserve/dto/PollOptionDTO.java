package com.softserve.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter @Setter
public class PollOptionDTO implements Serializable {

    private Integer id;

    private String value;

    private Integer order;

    private Integer pollID;
}
