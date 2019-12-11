package com.neurix.simrs.transaksi.permintaanresep.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import org.apache.log4j.Logger;

/**
 * Created by Toshiba on 11/12/2019.
 */
public class PermintaanResepAction{
    private static transient Logger logger = Logger.getLogger(PermintaanResepAction.class);

    private PermintaanResep permintaanResep;
    private PermintaanResepBo permintaanResepBoProxy;



    public PermintaanResep getPermintaanResep() {
        return permintaanResep;
    }

    public void setPermintaanResep(PermintaanResep permintaanResep) {
        this.permintaanResep = permintaanResep;
    }

    public PermintaanResepBo getPermintaanResepBoProxy() {
        return permintaanResepBoProxy;
    }

    public void setPermintaanResepBoProxy(PermintaanResepBo permintaanResepBoProxy) {
        this.permintaanResepBoProxy = permintaanResepBoProxy;
    }
}
