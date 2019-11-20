package org.ziniakov.codingenergyconsumption.model.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "village")
public class Village {
    @Id
    @Column(name="village_id", nullable=false, unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name="village_name", nullable=false, unique=true)
    private String name;
}
