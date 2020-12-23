package com.neurix.simrs.master.parameterketeranganobat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.parameterketeranganobat.bo.ParameterKeteranganObatBo;
import com.neurix.simrs.master.parameterketeranganobat.dao.ParameterKeteranganObatDao;
import com.neurix.simrs.master.parameterketeranganobat.model.ImSimrsParameterKeteranganObatEntity;
import com.neurix.simrs.master.parameterketeranganobat.model.ParameterKeteranganObat;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterKeteranganObatBoImpl implements ParameterKeteranganObatBo{
    private static transient Logger logger = Logger.getLogger(ParameterKeteranganObatBoImpl.class);
    private ParameterKeteranganObatDao parameterKeteranganObatDao;

    public void setParameterKeteranganObatDao(ParameterKeteranganObatDao parameterKeteranganObatDao) {
        this.parameterKeteranganObatDao = parameterKeteranganObatDao;
    }

    @Override
    public List<ImSimrsParameterKeteranganObatEntity> getListEntitiyByCriteria(ParameterKeteranganObat bean) throws GeneralBOException {
        logger.info("[ParameterKeteranganObatBoImpl.getListEntitiyByCriteria] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId()))
            hsCriteria.put("id", bean.getId());
        if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama()))
            hsCriteria.put("nama", bean.getNama());
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag()))
            hsCriteria.put("flag", bean.getFlag());

        List<ImSimrsParameterKeteranganObatEntity> parameterKeteranganObatEntities = new ArrayList<>();
        try {
            parameterKeteranganObatEntities = parameterKeteranganObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[ParameterKeteranganObatBoImpl.getListEntitiyByCriteria] ERROR, error when. ",e);
            throw new GeneralBOException("[ParameterKeteranganObatBoImpl.getListEntitiyByCriteria] ERROR, error when. "+e);
        }

        logger.info("[ParameterKeteranganObatBoImpl.getListEntitiyByCriteria] END <<<");
        return parameterKeteranganObatEntities;
    }

    @Override
    public List<ParameterKeteranganObat> getListSearchByCriteria(ParameterKeteranganObat bean) throws GeneralBOException {
        logger.info("[ParameterKeteranganObatBoImpl.getListSearchByCriteria] START >>>");

        List<ImSimrsParameterKeteranganObatEntity> parameterKeteranganObatEntities = new ArrayList<>();

        try {
            parameterKeteranganObatEntities = getListEntitiyByCriteria(bean);
        } catch (HibernateException e){
            logger.error("[ParameterKeteranganObatBoImpl.getListSearchByCriteria] ERROR, error when. ",e);
            throw new GeneralBOException("[ParameterKeteranganObatBoImpl.getListSearchByCriteria] ERROR, error when. "+e);
        }

        List<ParameterKeteranganObat> parameterKeteranganObats = new ArrayList<>();
        if (parameterKeteranganObatEntities.size() > 0){
            for (ImSimrsParameterKeteranganObatEntity paramEntity : parameterKeteranganObatEntities){
                ParameterKeteranganObat parameter = new ParameterKeteranganObat();
                parameter.setId(paramEntity.getId());
                parameter.setNama(paramEntity.getNama());
                parameter.setFlag(paramEntity.getFlag());
                parameter.setAction(paramEntity.getAction());
                parameter.setCreatedDate(paramEntity.getCreatedDate());
                parameter.setCreatedWho(paramEntity.getCreatedWho());
                parameter.setLastUpdate(paramEntity.getLastUpdate());
                parameter.setLastUpdateWho(paramEntity.getLastUpdateWho());
                parameter.setFlagLabelWaktu(paramEntity.getFlagLabelWaktu());
                parameterKeteranganObats.add(parameter);
            }
        }

        logger.info("[ParameterKeteranganObatBoImpl.getListSearchByCriteria] END <<<");
        return parameterKeteranganObats;
    }

    @Override
    public List<ParameterKeteranganObat> getParameterKeterangan(String idJenis) throws GeneralBOException {
        List<ParameterKeteranganObat> parameterKeteranganObats = new ArrayList<>();
        try {
            parameterKeteranganObats = parameterKeteranganObatDao.getParameterKeterangan(idJenis);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return parameterKeteranganObats;
    }
}
