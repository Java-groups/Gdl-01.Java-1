package com.softserve.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "poll_option")
@ToString
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_poll")
    @Getter @Setter
    private Poll poll;
}
