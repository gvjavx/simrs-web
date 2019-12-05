package com.neurix.simrs.master.lab.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.lab.action.LabAction;
import com.neurix.simrs.master.lab.bo.LabBo;
import com.neurix.simrs.master.lab.dao.LabDao;
import com.neurix.simrs.master.lab.model.ImSimrsLabEntity;
import com.neurix.simrs.master.lab.model.Lab;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LabBoImpl implements LabBo {

    protected static transient Logger logger = Logger.getLogger(LabBoImpl.class);
    private LabDao labDao;

    public static Logger getLogger() {
        return logger;
    }

    public void setLabDao(LabDao labDao) {
        this.labDao = labDao;
    }

    @Override
    public List<Lab> getByCriteria(Lab bean) throws GeneralBOException {
        logger.info("[LabBoImpl.getByCriteria] Start >>>>>>>");
        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdLab() != null && !"".equalsIgnoreCase(bean.getIdLab())) {
                hsCriteria.put("id_lab", bean.getIdLab());
            }
            if (bean.getIdOperatorLab() != null && !"".equalsIgnoreCase(bean.getIdOperatorLab())) {
                hsCriteria.put("id_operator_lab", bean.getIdOperatorLab());
            }
            if (bean.getIdDokter() != null && !"".equalsIgnoreCase(bean.getIdDokter())) {
                hsCriteria.put("id_dokter", bean.getIdDokter());
            }
            if (bean.getIdKategoriLab() != null && !"".equalsIgnoreCase(bean.getIdKategoriLab())) {
                hsCriteria.put("id_kategori_lab", bean.getIdKategoriLab());
            }

            hsCriteria.put("flag", "Y");

            List<ImSimrsLabEntity> labEntityList = new ArrayList<>();
            List<Lab> result = new ArrayList<>();

            try {
                labEntityList = labDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[LabBoImpl.getByCriteria] error when get data lab by get by criteria "+ e.getMessage());
            }

            if (!labEntityList.isEmpty()){
                Lab lab;
                for (ImSimrsLabEntity labEntity : labEntityList){
                    lab = new Lab();
                    lab.setIdLab(labEntity.getIdLab());
                    lab.setNamaLab(labEntity.getNamaLab());
                    lab.setIdOperatorLab(labEntity.getIdOperatorLab());
                    lab.setIdDokter(labEntity.getIdDokter());
                    lab.setIdKategoriLab(labEntity.getIdKategoriLab());
                    lab.setFlag(labEntity.getFlag());
                    lab.setAction(labEntity.getAction());
                    lab.setCreatedDate(labEntity.getCreatedDate());
                    lab.setCreatedWho(labEntity.getCreatedWho());
                    lab.setLastUpdate(labEntity.getLastUpdate());
                    lab.setLastUpdateWho(labEntity.getLastUpdateWho());
                    result.add(lab);
                }
            }

            logger.info("[LabBoImpl.getByCriteria] End <<<<<<<");
            return result;
        }
        logger.info("[LabBoImpl.getByCriteria] End <<<<<<<");
        return null;
    }
}