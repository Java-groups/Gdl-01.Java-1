package com.softserve.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "poll_option")
@Getter @Setter
@ToString
public class PollOption {
    @Id
    @Column(name = "id_poll_option")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "poll_value")
    private String value;

    @Column(name = "poll_order")
    private Integer order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_poll")
    private Poll poll;
}
