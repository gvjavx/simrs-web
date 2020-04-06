package com.neurix.hris.master.provinsi.model;

import com.neurix.common.dao.CompositeKey;

/**
 * Created by thinkpad on 13/09/2017.
 */
public class ImProvinsiPK extends CompositeKey<String> {

    @Override
    protected String getId() {
        return this.id = id;
    }

    @Override
    protected void setId(String id) {
        this.id = id;
    }
}
