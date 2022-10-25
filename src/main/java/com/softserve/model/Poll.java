package com.softserve.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "poll")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private Long id;

    @Column(name = "question")
    @Getter @Setter
    private String question;

    @Column(name = "id_app_user")
    @Getter @Setter
    private Integer userId;

    @Column(name = "status")
    @Getter @Setter
    private Integer status;

    @Column(name = "closed_date")
    @Getter @Setter
    private Timestamp closedDate;

    @Column(name = "creation_date")
    @Getter @Setter
    private Timestamp creationDate;

    @Column(name = "modification_date")
    @Getter @Setter
    private Timestamp modificationDate;
}
