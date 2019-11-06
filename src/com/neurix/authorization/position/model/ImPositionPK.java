package com.neurix.authorization.position.model;

import com.neurix.common.dao.CompositeKey;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/01/13
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */

public class ImPositionPK extends CompositeKey<Long> {

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_position")
    @SequenceGenerator(name = "sequence_position", sequenceName = "seq_position")
    @Column(name = "position_id", insertable = false, nullable = false, updatable = false)
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


}
