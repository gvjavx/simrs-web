package com.neurix.simrs.transaksi.kasirrawatjalan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.kasirrawatjalan.bo.KasirRawatJalanBo;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class KasirRawatJalanBoImpl implements KasirRawatJalanBo {

    private RiwayatTindakanDao riwayatTindakanDao;
    private RawatInapDao rawatInapDao;

    public void setRawatInapDao(RawatInapDao rawatInapDao) {
        this.rawatInapDao = rawatInapDao;
    }

    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
    }

    private static transient Logger logger = Logger.getLogger(KasirRawatJalanBoImpl.class);

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public List<RiwayatTindakan> getListAllTindakan(RiwayatTindakan bean) throws GeneralBOException {
        logger.info("[KasirRawatJalanBoImpl.getListAllTindakan] START process <<<");
        List<RiwayatTindakan> result = new ArrayList<>();
        if(bean != null){
            try {
                result = riwayatTindakanDao.getListTindakan(bean);
            }catch (HibernateException e){
                logger.error("[KasirRawatJalanBoImpl.getListAllTindakan] Error when save update data flag approve tindakan rawat ", e);
            }
        }
        logger.info("[KasirRawatJalanBoImpl.getListAllTindakan] END process <<<");
        return result;
    }
}
