package com.neurix.authorization.function.model;

import com.neurix.common.dao.CompositeKey;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/01/13
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class ImFunctionsPK extends CompositeKey<Long> {

    //    @Override
//    @Id
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="sequence_func")
//    @SequenceGenerator(name = "sequence_func", sequenceName = "seq_functions")
//    @Column(name = "func_id", insertable=false, nullable=false, updatable=false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
