package com.neurix.authorization.role.model;

import com.neurix.common.dao.CompositeKey;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/01/13
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class ImRolesPK extends CompositeKey<Long> {

    @Override
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="sequence_role")
    @SequenceGenerator(name = "sequence_role", sequenceName = "seq_roles")
    @Column(name = "role_id", insertable=false, nullable=false, updatable=false)
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
