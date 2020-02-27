package com.neurix.simrs.transaksi.kasirrawatjalan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkupdetail.dao.UangMukaDao;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsUangMukaPendaftaranEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.UangMuka;
import com.neurix.simrs.transaksi.kasirrawatjalan.bo.KasirRawatJalanBo;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KasirRawatJalanBoImpl implements KasirRawatJalanBo {

    private RiwayatTindakanDao riwayatTindakanDao;
    private RawatInapDao rawatInapDao;
    private UangMukaDao uangMukaDao;

    public void setUangMukaDao(UangMukaDao uangMukaDao) {
        this.uangMukaDao = uangMukaDao;
    }

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

    @Override
    public List<UangMuka> getListUangMuka(UangMuka bean) throws GeneralBOException {
        List<UangMuka> uangMukaList = new ArrayList<>();

        if(bean != null){

            Map hsCriterian = new HashMap();

            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriterian.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if(bean.getStatusBayar() != null && !"".equalsIgnoreCase(bean.getStatusBayar())){
                hsCriterian.put("status_bayar", bean.getStatusBayar());
//                hsCriterian.put("nota_not_null", "Y");
            }

            List<ItSimrsUangMukaPendaftaranEntity> entityList = new ArrayList<>();
            try {
                entityList = uangMukaDao.getByCriteria(hsCriterian);
            }catch (HibernateException e){
                logger.error("Found Error when search uang muka");
            }

            if(entityList.size() > 0){
                UangMuka uangMuka;
                for (ItSimrsUangMukaPendaftaranEntity entity :entityList){
                    uangMuka = new UangMuka();
                    uangMuka.setIdDetailCheckup(entity.getIdDetailCheckup());
                    uangMuka.setId(entity.getId());
                    uangMuka.setJumlah(entity.getJumlah());
                    uangMuka.setCreatedDate(entity.getCreatedDate());
                    uangMuka.setCreatedWho(entity.getCreatedWho());
                    uangMuka.setFlag(entity.getFlag());
                    uangMuka.setStatusBayar(entity.getStatusBayar());
                    uangMuka.setNoNota(entity.getNoNota());
                    String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(entity.getCreatedDate());
                    uangMuka.setStCreatedDate(formatDate);
                    uangMuka.setStDate(entity.getCreatedDate().toString());
                    uangMukaList.add(uangMuka);
                }
            }
        }
        return uangMukaList;
    }

    @Override
    public void updateNotaUangMukaById(UangMuka bean) throws GeneralBOException {
        Map hsCriteria = new HashMap();
        hsCriteria.put("id", bean.getId());

        List<ItSimrsUangMukaPendaftaranEntity> uangMukaPendaftaranEntities = new ArrayList<>();
        try {
            uangMukaPendaftaranEntities = uangMukaDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[KasirRawatJalanBoImpl.updateNotaUangMukaById] Error ", e);
            new GeneralBOException("[KasirRawatJalanBoImpl.updateNotaUangMukaById] Error  ", e);
        }

        if (uangMukaPendaftaranEntities.size() > 0){
            for (ItSimrsUangMukaPendaftaranEntity pendaftaranEntity : uangMukaPendaftaranEntities){
                pendaftaranEntity.setStatusBayar("Y");
                pendaftaranEntity.setAction("U");
                pendaftaranEntity.setJumlahDibayar(bean.getDibayar());
//                pendaftaranEntity.setNoNota(bean.getNoNota());
                pendaftaranEntity.setLastUpdate(bean.getLastUpdate());
                pendaftaranEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    uangMukaDao.updateAndSave(pendaftaranEntity);
                } catch (HibernateException e){
                    logger.error("[KasirRawatJalanBoImpl.updateNotaUangMukaById] Error ", e);
                    new GeneralBOException("[KasirRawatJalanBoImpl.updateNotaUangMukaById] Error  ", e);
                }
            }
        }

    }
}
