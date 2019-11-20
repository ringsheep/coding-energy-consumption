package org.ziniakov.codingenergyconsumption.model.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "counter")
public class Counter {
    @Id
    @Column(name="counter_id", nullable=false, unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @ManyToOne
    @JoinColumn(name="village_id", referencedColumnName="village_id")
    private Village village;
}
