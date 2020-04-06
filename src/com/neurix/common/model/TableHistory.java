package com.neurix.common.model;

import javax.persistence.Basic;
import javax.persistence.Column;

/**
 * Created by Ferdi on 03/10/2014.
 */
public class TableHistory {
    protected Long id;

    @Basic
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
