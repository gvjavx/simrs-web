package com.neurix.authorization.user.model;

import com.neurix.common.dao.CompositeKey;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/01/13
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class ImUsersPK extends CompositeKey<String> {

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }


}
