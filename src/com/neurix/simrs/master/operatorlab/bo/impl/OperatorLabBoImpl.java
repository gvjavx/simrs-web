package com.neurix.simrs.master.operatorlab.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.operatorlab.bo.OperatorLabBo;
import com.neurix.simrs.master.operatorlab.dao.OperatorLabDao;
import com.neurix.simrs.master.operatorlab.model.ImSimrsOperatorLabEntity;
import com.neurix.simrs.master.operatorlab.model.OperatorLab;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperatorLabBoImpl implements OperatorLabBo {

    protected static transient Logger logger = Logger.getLogger(OperatorLabBoImpl.class);
    private OperatorLabDao operatorLabDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        OperatorLabBoImpl.logger = logger;
    }

    public OperatorLabDao getOperatorLabDao() {
        return operatorLabDao;
    }

    public void setOperatorLabDao(OperatorLabDao operatorLabDao) {
        this.operatorLabDao = operatorLabDao;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
return null;
    }

    @Override
    public void saveDelete(OperatorLab bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(OperatorLab bean) throws GeneralBOException {

    }

    @Override
    public OperatorLab saveAdd(OperatorLab bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<OperatorLab> getByCriteria(OperatorLab bean) throws GeneralBOException {
        logger.info("[OperatorLabBoImpl.getByCriteria] Start >>>>>>");
        List<OperatorLab> result = new ArrayList<>();

        if(bean != null){

            Map hsCriteria = new HashMap();

            if (bean.getIdOperatorLab() != null && !"".equalsIgnoreCase(bean.getIdOperatorLab())){
                hsCriteria.put("id_operator_lab", bean.getIdOperatorLab());
            }
            if (bean.getNamaOperator() != null && !"".equalsIgnoreCase(bean.getNamaOperator())){
                hsCriteria.put("nama_operator", bean.getNamaOperator());
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

            List<ImSimrsOperatorLabEntity> entityList = new ArrayList<>();

            try {
                entityList = operatorLabDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[OperatoLabBoImpl.getByCriteria] Error get pelayanan data "+e.getMessage());
            }

            if (!entityList.isEmpty()){
                OperatorLab operatorLab;
                for (ImSimrsOperatorLabEntity entity : entityList){
                    operatorLab = new OperatorLab();
                    operatorLab.setIdOperatorLab(entity.getIdOperatorLab());
                    operatorLab.setNamaOperator(entity.getNamaOperator());
                    operatorLab.setAction(entity.getAction());
                    operatorLab.setFlag(entity.getFlag());
                    operatorLab.setCreatedDate(entity.getCreatedDate());
                    operatorLab.setCreatedWho(entity.getCreatedWho());
                    operatorLab.setLastUpdate(entity.getLastUpdate());
                    operatorLab.setLastUpdateWho(entity.getLastUpdateWho());
                    result.add(operatorLab);
                }
            }
        }

        logger.info("[OperatorLabBoImpl.getByCriteria] End <<<<<<");
        return result;
    }

    @Override
    public List<OperatorLab> getAll() throws GeneralBOException {
        return null;
    }
}