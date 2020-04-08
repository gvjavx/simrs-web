package com.neurix.simrs.master.labdetail.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.labdetail.bo.LabDetailBo;
import com.neurix.simrs.master.labdetail.dao.LabDetailDao;
import com.neurix.simrs.master.labdetail.model.ImSimrsLabDetailEntity;
import com.neurix.simrs.master.labdetail.model.LabDetail;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LabDetailBoImpl implements LabDetailBo {

    protected static transient Logger logger = Logger.getLogger(LabDetailBoImpl.class);
    private LabDetailDao labDetailDao;

    public static Logger getLogger() {
        return logger;
    }

    public void setLabDetailDao(LabDetailDao labDetailDao) {
        this.labDetailDao = labDetailDao;
    }

    @Override
    public List<LabDetail> getByCriteria(LabDetail bean) throws GeneralBOException {
        logger.info("[LabDetailBoImpl.getByCriteria] Start >>>>>>>");
        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdLabDetail() != null && !"".equalsIgnoreCase(bean.getIdLabDetail())) {
                hsCriteria.put("id_lab_detail", bean.getIdLabDetail());
            }
            if (bean.getIdLab() != null && !"".equalsIgnoreCase(bean.getIdLab())) {
                hsCriteria.put("id_lab", bean.getIdLab());
            }

            hsCriteria.put("flag", "Y");

            List<ImSimrsLabDetailEntity> labDetailEntityList = new ArrayList<>();
            List<LabDetail> result = new ArrayList<>();

            try {
                labDetailEntityList = labDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[LabDetailBoImpl.getByCriteria] error when get data lab detailt by get by criteria "+ e.getMessage());
            }

            if (!labDetailEntityList.isEmpty()){
                LabDetail labDetail;
                for (ImSimrsLabDetailEntity labDetailEntity : labDetailEntityList){
                    labDetail = new LabDetail();
                    labDetail.setIdLabDetail(labDetailEntity.getIdLabDetail());
                    labDetail.setIdLab(labDetailEntity.getIdLab());
                    labDetail.setNamaDetailPeriksa(labDetailEntity.getNamaDetailPeriksa());
                    labDetail.setSatuan(labDetailEntity.getSatuan());
                    labDetail.setKetentuanAcuan(labDetailEntity.getKetentuanAcuan());
                    labDetail.setFlag(labDetailEntity.getFlag());
                    labDetail.setAction(labDetailEntity.getAction());
                    labDetail.setCreatedDate(labDetailEntity.getCreatedDate());
                    labDetail.setCreatedWho(labDetailEntity.getCreatedWho());
                    labDetail.setLastUpdate(labDetailEntity.getLastUpdate());
                    labDetail.setLastUpdateWho(labDetailEntity.getLastUpdateWho());
                    result.add(labDetail);
                }
            }

            logger.info("[LabDetailBoImpl.getByCriteria] End <<<<<<<");
            return result;
        }
        logger.info("[LabDetailBoImpl.getByCriteria] End <<<<<<<");
        return null;
    }
}