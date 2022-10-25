package com.softserve.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "poll_option")
public class PollOption {
    @Id
    @Column(name = "id_poll_option")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @Column(name = "poll_value")
    @Getter @Setter
    private String value;

    @Column(name = "poll_order")
    @Getter @Setter
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "id_poll")
    private Poll id_poll;
}
