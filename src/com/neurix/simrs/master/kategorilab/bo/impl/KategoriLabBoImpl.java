package com.neurix.simrs.master.kategorilab.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisobat.bo.JenisObatBo;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import com.neurix.simrs.master.kategorilab.bo.KategoriLabBo;
import com.neurix.simrs.master.kategorilab.dao.KategoriLabDao;
import com.neurix.simrs.master.kategorilab.model.ImSimrsKategoriLabEntity;
import com.neurix.simrs.master.kategorilab.model.KategoriLab;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KategoriLabBoImpl implements KategoriLabBo {

    protected static transient Logger logger = Logger.getLogger(KategoriLabBoImpl.class);
    private KategoriLabDao kategoriLabDao;

    public static Logger getLogger() {
        return logger;
    }

    public void setKategoriLabDao(KategoriLabDao kategoriLabDao) {
        this.kategoriLabDao = kategoriLabDao;
    }


    @Override
    public List<KategoriLab> getByCriteria(KategoriLab bean) throws GeneralBOException {
        logger.info("[KategoriLabBoImpl.getByCriteria] Start >>>>>>>");
        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdKategoriLab() != null && !"".equalsIgnoreCase(bean.getIdKategoriLab())) {
                hsCriteria.put("id_kategori_lab", bean.getIdKategoriLab());
            }

            hsCriteria.put("flag", "Y");

            List<ImSimrsKategoriLabEntity> kategoriLabEntityList = new ArrayList<>();
            List<KategoriLab> result = new ArrayList<>();

            try {
                kategoriLabEntityList = kategoriLabDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[KategoriLabBoImpl.getByCriteria] error when get data jenis obat by get by criteria "+ e.getMessage());
            }

            if (!kategoriLabEntityList.isEmpty()){
                KategoriLab kategoriLab;
                for (ImSimrsKategoriLabEntity kategoriLabEntity : kategoriLabEntityList){
                    kategoriLab = new KategoriLab();
                    kategoriLab.setIdKategoriLab(kategoriLabEntity.getIdKategoriLab());
                    kategoriLab.setNamaKategori(kategoriLabEntity.getNamaKategori());
                    kategoriLab.setFlag(kategoriLabEntity.getFlag());
                    kategoriLab.setAction(kategoriLabEntity.getAction());
                    kategoriLab.setCreatedDate(kategoriLabEntity.getCreatedDate());
                    kategoriLab.setCreatedWho(kategoriLabEntity.getCreatedWho());
                    kategoriLab.setLastUpdate(kategoriLabEntity.getLastUpdate());
                    kategoriLab.setLastUpdateWho(kategoriLabEntity.getLastUpdateWho());
                    result.add(kategoriLab);
                }
            }

            logger.info("[KategoriLabBoImpl.getByCriteria] End <<<<<<<");
            return result;
        }
        logger.info("[KategoriLabBoImpl.getByCriteria] End <<<<<<<");
        return null;
    }
}