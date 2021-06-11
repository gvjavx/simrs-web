package com.neurix.simrs.transaksi.kasirrawatinap.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkupdetail.dao.UangMukaDao;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsUangMukaPendaftaranEntity;
import com.neurix.simrs.transaksi.kasirrawatinap.bo.KasirRawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class KasirRawatInapBoImpl implements KasirRawatInapBo {

    private static transient Logger logger = Logger.getLogger(KasirRawatInapBoImpl.class);
    private RiwayatTindakanDao riwayatTindakanDao;
    private RawatInapDao rawatInapDao;

    public void setRawatInapDao(RawatInapDao rawatInapDao) {
        this.rawatInapDao = rawatInapDao;
    }

    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public List<RiwayatTindakan> getListAllTindakan(RiwayatTindakan bean) throws GeneralBOException {
        logger.info("[KasirRawatInapBoImpl.getListAllTindakan] START process <<<");
        List<RiwayatTindakan> result = new ArrayList<>();
        if(bean != null){
            try {
                result = riwayatTindakanDao.getListTindakan(bean);
            }catch (HibernateException e){
                logger.error("[KasirRawatInapBoImpl.getListAllTindakan] Error when save update data flag approve tindakan rawat ", e);
            }
        }
        logger.info("[KasirRawatInapBoImpl.getListAllTindakan] END process <<<");
        return result;
    }

    @Override
    public List<RawatInap> getListRawatInap(RawatInap bean) throws GeneralBOException {
        logger.info("[KasirRawatJalanBoImpl.getListRawatInap] START process <<<");
        List<RawatInap> result = new ArrayList<>();
        if(bean != null){
            try {
                result = rawatInapDao.getSearchVerifikasiRawatInap(bean, bean.getIdJenisPeriksa());
            }catch (HibernateException e){
                logger.error("[KasirRawatJalanBoImpl.getListRawatInap] Error when save update data flag approve tindakan rawat ", e);
            }
        }
        logger.info("[KasirRawatJalanBoImpl.getListRawatInap] END process <<<");
        return result;
    }
}
