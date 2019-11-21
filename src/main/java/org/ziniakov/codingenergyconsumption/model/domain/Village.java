package org.ziniakov.codingenergyconsumption.model.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "village")
public class Village {

    @Id
    @Column(name = "village_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "village_name", nullable = false)
    private String name;
}
