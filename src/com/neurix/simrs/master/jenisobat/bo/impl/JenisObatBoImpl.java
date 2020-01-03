package com.neurix.simrs.master.jenisobat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisobat.bo.JenisObatBo;
import com.neurix.simrs.master.jenisobat.dao.JenisObatDao;
import com.neurix.simrs.master.jenisobat.model.ImSimrsJenisObatEntity;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JenisObatBoImpl implements JenisObatBo {

    protected static transient Logger logger = Logger.getLogger(JenisObatBoImpl.class);
    private JenisObatDao jenisObatDao;

    public static Logger getLogger() {
        return logger;
    }

    public void setJenisObatDao(JenisObatDao jenisObatDao) {
        this.jenisObatDao = jenisObatDao;
    }

    @Override
    public List<JenisObat> getByCriteria(JenisObat bean) throws GeneralBOException {
        logger.info("[JenisObatBoImpl.getByCriteria] Start >>>>>>>");
        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat())) {
                hsCriteria.put("id_jenis_obat", bean.getIdJenisObat());
            }

            hsCriteria.put("flag", "Y");

            List<ImSimrsJenisObatEntity> jenisObatEntityList = new ArrayList<>();
            List<JenisObat> result = new ArrayList<>();

            try {
                jenisObatEntityList = jenisObatDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[JenisObatBoImpl.getByCriteria] error when get data jenis obat by get by criteria "+ e.getMessage());
            }

            if (!jenisObatEntityList.isEmpty()){
                JenisObat jenisObat;
                for (ImSimrsJenisObatEntity jenisObatEntity : jenisObatEntityList){
                    jenisObat = new JenisObat();
                    jenisObat.setIdJenisObat(jenisObatEntity.getIdJenisObat());
                    jenisObat.setNamaJenisObat(jenisObatEntity.getNamaJenisObat());
                    jenisObat.setFlag(jenisObatEntity.getFlag());
                    jenisObat.setAction(jenisObatEntity.getAction());
                    jenisObat.setCreatedDate(jenisObatEntity.getCreatedDate());
                    jenisObat.setCreatedWho(jenisObatEntity.getCreatedWho());
                    jenisObat.setLastUpdate(jenisObatEntity.getLastUpdate());
                    jenisObat.setLastUpdateWho(jenisObatEntity.getLastUpdateWho());
                    result.add(jenisObat);
                }
            }

            logger.info("[JenisObatBoImpl.getByCriteria] End <<<<<<<");
            return result;
        }
        logger.info("[JenisObatBoImpl.getByCriteria] End <<<<<<<");
        return null;
    }
}