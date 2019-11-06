package com.neurix.authorization.company.model;

import com.neurix.common.dao.CompositeKey;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 26/01/13
 * Time: 5:27
 * To change this template use File | Settings | File Templates.
 */
public class ImAreasPK extends CompositeKey<String> {

    @Override
    @Id
    @GenericGenerator(name="sequence_area", strategy="com.neurix.authorization.company.AreaIdGenerator")
    @GeneratedValue( generator="sequence_area" )
    @Column(name = "area_id", insertable=true, nullable=false, updatable=true)
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

}
