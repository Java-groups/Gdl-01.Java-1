package com.softserve.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "poll")
@Getter @Setter
@ToString
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPoll;

    @Column(name = "question")
    private String question;

    @Column(name = "id_app_user")
    private Integer userId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "closed_date")
    private Timestamp closedDate;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "modification_date")
    private Timestamp modificationDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "poll")
    private Set<PollOption> pollOption;
}
