package com.neurix.akuntansi.transaksi.tutupperiod.bo.impl;

import com.neurix.akuntansi.transaksi.tutupperiod.bo.TutupPeriodBo;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import org.apache.log4j.Logger;

/**
 * Created by reza on 18/03/20.
 */
public class TutupPeriodBoImpl implements TutupPeriodBo {
    private static transient Logger logger = Logger.getLogger(TutupPeriodBoImpl.class);

    private RiwayatTindakanDao riwayatTindakanDao;


    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
    }
}
