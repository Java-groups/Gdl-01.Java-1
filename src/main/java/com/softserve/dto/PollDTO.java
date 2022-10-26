package com.softserve.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@ToString
public class PollDTO implements Serializable {
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String question;

    @Getter @Setter
    private Integer userId;

    @Getter @Setter
    private Integer status;

    @Getter @Setter @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp closedDate;

    @Getter @Setter @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp creationDate;

    @Getter @Setter @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modificationDate;

    @Getter @Setter
    private Set<PollOptionDTO> pollOptions;
}
