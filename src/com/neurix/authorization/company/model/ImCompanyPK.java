package com.neurix.authorization.company.model;

import com.neurix.common.dao.CompositeKey;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/01/13
 * Time: 21:06
 * To change this template use File | Settings | File Templates.
 */
public class ImCompanyPK extends CompositeKey<String> {

    @Override
    @Id
    @GenericGenerator(name="sequence_company", strategy="com.neurix.authorization.company.CompanyIdGenerator")
    @GeneratedValue( generator="sequence_company" )
    @Column(name = "company_id", insertable=false, nullable=false, updatable=false)
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

}
