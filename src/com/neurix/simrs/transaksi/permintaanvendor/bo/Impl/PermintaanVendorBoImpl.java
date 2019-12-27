package com.neurix.simrs.transaksi.permintaanvendor.bo.Impl;

import com.neurix.simrs.transaksi.permintaanvendor.bo.PermintaanVendorBo;
import com.neurix.simrs.transaksi.permintaanvendor.dao.PermintaanVendorDao;
import org.apache.log4j.Logger;

/**
 * Created by Toshiba on 27/12/2019.
 */
public class PermintaanVendorBoImpl implements PermintaanVendorBo {
    private static transient Logger logger = Logger.getLogger(PermintaanVendorBoImpl.class);

    private PermintaanVendorDao permintaanVendorDao;

    public PermintaanVendorDao getPermintaanVendorDao() {
        return permintaanVendorDao;
    }

    public void setPermintaanVendorDao(PermintaanVendorDao permintaanVendorDao) {
        this.permintaanVendorDao = permintaanVendorDao;
    }

    public static void setLogger(Logger logger) {
        PermintaanVendorBoImpl.logger = logger;
    }
}
