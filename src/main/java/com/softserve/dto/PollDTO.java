package com.softserve.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

public class PollDTO implements Serializable {
    @Getter @Setter
    private String question;

    @Getter @Setter
    private Integer status;

    @Getter @Setter
    private String responses;

    @Getter @Setter
    private Timestamp closedDate;

    @Getter @Setter
    private Timestamp creationDate;
}
