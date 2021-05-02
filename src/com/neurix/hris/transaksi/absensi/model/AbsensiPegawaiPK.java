package com.neurix.hris.transaksi.absensi.model;

import com.neurix.common.dao.CompositeKey;

/**
 * Created by thinkpad on 13/09/2017.
 */
public class AbsensiPegawaiPK extends CompositeKey<String> {

    @Override
    protected String getId() {
        return this.id = id;
    }

    @Override
    protected void setId(String id) {
        this.id = id;
    }
}
