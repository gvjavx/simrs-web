package com.neurix.simrs.master.parameterketeranganobat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.keteranganobat.model.KeteranganObat;
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
    public List<ParameterKeteranganObat> getByCriteria(ParameterKeteranganObat bean) throws GeneralBOException {
        logger.info("[ParameterKeteranganObatBoImpl.getByCriteria] Start >>>>>>>>");
        List<ParameterKeteranganObat> listOfResultParameterKeteranganObat = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId())) {
                hsCriteria.put("id", bean.getId());
            }
            if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama())) {
                hsCriteria.put("nama", bean.getNama());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                if ("N".equalsIgnoreCase(bean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", bean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImSimrsParameterKeteranganObatEntity> imSimrsParameterKeteranganObat = null;
            try {
                imSimrsParameterKeteranganObat = parameterKeteranganObatDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[JenisDietBoImpl.getByCriteria] error when get data Jenis Diet by get by criteria "+ e.getMessage());
            }

            if (imSimrsParameterKeteranganObat.size() > 0) {
                for (ImSimrsParameterKeteranganObatEntity ParameterKeteranganObat : imSimrsParameterKeteranganObat){
                    ParameterKeteranganObat parameterKeteranganObat = new ParameterKeteranganObat();

                    parameterKeteranganObat.setId(ParameterKeteranganObat.getId());
                    parameterKeteranganObat.setNama(ParameterKeteranganObat.getNama());


                    parameterKeteranganObat.setAction(ParameterKeteranganObat.getAction());
                    parameterKeteranganObat.setFlag(ParameterKeteranganObat.getFlag());
                    parameterKeteranganObat.setCreatedDate(ParameterKeteranganObat.getCreatedDate());
                    parameterKeteranganObat.setCreatedWho(ParameterKeteranganObat.getCreatedWho());
                    parameterKeteranganObat.setLastUpdate(ParameterKeteranganObat.getLastUpdate());
                    parameterKeteranganObat.setLastUpdateWho(ParameterKeteranganObat.getLastUpdateWho());
                    listOfResultParameterKeteranganObat.add(parameterKeteranganObat);
                }
            }
        }

        logger.info("[ParameterKeteranganObatBoImpl.getByCriteria] End <<<<<<<<");
        return listOfResultParameterKeteranganObat;
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

    @Override
    public List<KeteranganObat> getParameterKeteranganWaktu(String idJenis) throws GeneralBOException {
        List<KeteranganObat> parameterKeteranganObats = new ArrayList<>();
        try {
            parameterKeteranganObats = parameterKeteranganObatDao.getKeteranganObatWaktu(idJenis);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return parameterKeteranganObats;
    }
}
