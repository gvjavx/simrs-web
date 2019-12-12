package com.neurix.simrs.transaksi.transaksiobat.bo.impl;

import com.neurix.simrs.master.obatpoli.dao.ObatPoliDao;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.dao.ApprovalTransaksiObatDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.RiwayatTransaksiObatDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailDao;
import org.apache.log4j.Logger;

/**
 * Created by Toshiba on 11/12/2019.
 */
public class TransaksiObatBoImpl implements TransaksiObatBo {
    private static transient Logger logger = Logger.getLogger(TransaksiObatBoImpl.class);

    private ApprovalTransaksiObatDao approvalTransaksiObatDao;
    private RiwayatTransaksiObatDao riwayatTransaksiObatDao;
    private TransaksiObatDetailDao transaksiObatDetailDao;
    private ObatPoliDao obatPoliDao;



    public void setApprovalTransaksiObatDao(ApprovalTransaksiObatDao approvalTransaksiObatDao) {
        this.approvalTransaksiObatDao = approvalTransaksiObatDao;
    }

    public void setRiwayatTransaksiObatDao(RiwayatTransaksiObatDao riwayatTransaksiObatDao) {
        this.riwayatTransaksiObatDao = riwayatTransaksiObatDao;
    }

    public void setTransaksiObatDetailDao(TransaksiObatDetailDao transaksiObatDetailDao) {
        this.transaksiObatDetailDao = transaksiObatDetailDao;
    }

    public void setObatPoliDao(ObatPoliDao obatPoliDao) {
        this.obatPoliDao = obatPoliDao;
    }
}
