package com.softserve.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "poll")
@ToString
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long idPoll;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "poll")
    private Set<PollOption> pollOption;
}
