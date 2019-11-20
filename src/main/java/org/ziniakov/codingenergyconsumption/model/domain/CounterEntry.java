package org.ziniakov.codingenergyconsumption.model.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "counter_entry")
public class CounterEntry {

    @Id
    @Column(name="counter_entry_id", nullable=false, unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="amount", nullable=false)
    private Float amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;

    @ManyToOne
    @JoinColumn(name="counter_id", referencedColumnName="counter_id")
    private Counter counter;
}
