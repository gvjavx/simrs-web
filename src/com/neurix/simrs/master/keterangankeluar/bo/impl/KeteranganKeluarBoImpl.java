package com.neurix.simrs.master.keterangankeluar.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.keterangankeluar.bo.KeteranganKeluarBo;
import com.neurix.simrs.master.keterangankeluar.dao.KeteranganKeluarDao;
import com.neurix.simrs.master.keterangankeluar.model.ImSimrsKeteranganKeluarEntity;
import com.neurix.simrs.master.keterangankeluar.model.KeteranganKeluar;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeteranganKeluarBoImpl implements KeteranganKeluarBo {
    protected static transient Logger logger = Logger.getLogger(KeteranganKeluarBoImpl.class);
    private KeteranganKeluarDao keteranganKeluarDao;

    public static Logger getLogger() {
        return logger;
    }

    public void setKeteranganKeluarDao(KeteranganKeluarDao keteranganKeluarDao) {
        this.keteranganKeluarDao = keteranganKeluarDao;
    }

    @Override
    public List<KeteranganKeluar> getByCriteria(KeteranganKeluar bean) throws GeneralBOException {
        logger.info("[KeteranganKeluarBoImpl.getByCriteria] Start >>>>>>>");
        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdKeterangan() != null && !"".equalsIgnoreCase(bean.getIdKeterangan())) {
                hsCriteria.put("id_keterangan", bean.getIdKeterangan());
            }
            hsCriteria.put("flag", "Y");

            List<ImSimrsKeteranganKeluarEntity> keteranganKeluarEntityList = new ArrayList<>();
            List<KeteranganKeluar> result = new ArrayList<>();

            try {
                keteranganKeluarEntityList = keteranganKeluarDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[KeteranganKeluarBoImpl.getByCriteria] error when get data keterangan keluar by get by criteria "+ e.getMessage());
            }

            if (!keteranganKeluarEntityList.isEmpty()){
                KeteranganKeluar keteranganKeluar;
                for (ImSimrsKeteranganKeluarEntity keteranganList : keteranganKeluarEntityList){
                    keteranganKeluar = new KeteranganKeluar();
                    keteranganKeluar.setIdKeterangan(keteranganList.getIdKeterangan());
                    keteranganKeluar.setKeterangan(keteranganList.getKeterangan());
                    keteranganKeluar.setFlag(keteranganList.getFlag());
                    keteranganKeluar.setAction(keteranganList.getAction());
                    keteranganKeluar.setCreatedDate(keteranganList.getCreatedDate());
                    keteranganKeluar.setCreatedWho(keteranganList.getCreatedWho());
                    keteranganKeluar.setLastUpdate(keteranganList.getLastUpdate());
                    keteranganKeluar.setLastUpdateWho(keteranganList.getLastUpdateWho());
                    keteranganKeluar.setKategori(keteranganList.getKategori());
                    result.add(keteranganKeluar);
                }
            }

            logger.info("[KeteranganKeluarBoImpl.getByCriteria] End <<<<<<<");
            return result;
        }
        logger.info("[KeteranganKeluarBoImpl.getByCriteria] End <<<<<<<");
        return null;
    }
}