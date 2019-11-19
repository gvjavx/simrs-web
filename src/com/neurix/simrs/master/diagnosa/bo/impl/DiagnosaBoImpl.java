package com.neurix.simrs.master.diagnosa.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.diagnosa.bo.DiagnosaBo;
import com.neurix.simrs.master.diagnosa.dao.DiagnosaDao;
import com.neurix.simrs.master.diagnosa.model.Diagnosa;
import com.neurix.simrs.master.diagnosa.model.ImSimrsDiagnosaEntity;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagnosaBoImpl implements DiagnosaBo {
    protected static transient Logger logger = Logger.getLogger(DiagnosaBoImpl.class);
    private DiagnosaDao diagnosaDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        DiagnosaBoImpl.logger = logger;
    }

    public DiagnosaDao getDiagnosaDao() {
        return diagnosaDao;
    }

    public void setDiagnosaDao(DiagnosaDao diagnosaDao) {
        this.diagnosaDao = diagnosaDao;
    }

    @Override
    public List<Diagnosa> getByCriteria(Diagnosa bean) throws GeneralBOException {
        logger.info("[DiagnosaBoImpl.getByCriteria] Start >>>>>>>");
        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdDiagnosa() != null && !"".equalsIgnoreCase(bean.getIdDiagnosa())) {
                hsCriteria.put("id_diagnosa", bean.getIdDiagnosa());
            }

            hsCriteria.put("flag", "Y");

            List<ImSimrsDiagnosaEntity> listOfDiagnosa = new ArrayList<>();

            try {
                listOfDiagnosa = diagnosaDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[DiagnosaBoImpl.getByCriteria] error when get data by criteria on getListNoCheckupByCriteria "+ e.getMessage());
            }

            if (!listOfDiagnosa.isEmpty()){
                logger.info("[DiagnosaBoImpl.getByCriteria] End <<<<<<<");
                return setTemplateToDiagnosaResult(listOfDiagnosa);
            }
        }
        logger.info("[CheckupBoImpl.getByCriteria] End <<<<<<<");
        return null;
    }

    public List<Diagnosa> setTemplateToDiagnosaResult(List<ImSimrsDiagnosaEntity> listDiagnosa){

        logger.info("[DiagnosaBoImpl.setTemplateToDiagnosaResult] Start >>>>>>>");

        List<Diagnosa> result = new ArrayList<>();

        Diagnosa diagnosa;
        for (ImSimrsDiagnosaEntity diagnosaEntity : listDiagnosa){
            diagnosa = new Diagnosa();
            diagnosa.setIdDiagnosa(diagnosaEntity.getIdDiagnosa());
            diagnosa.setDescOfDiagnosa(diagnosaEntity.getDescOfDiagnosa());
            diagnosa.setFlag(diagnosaEntity.getFlag());
            diagnosa.setAction(diagnosaEntity.getAction());
            diagnosa.setCreatedDate(diagnosaEntity.getCreatedDate());
            diagnosa.setCreatedWho(diagnosaEntity.getCreatedWho());
            diagnosa.setLastUpdate(diagnosaEntity.getLastUpdate());
            diagnosa.setLastUpdateWho(diagnosaEntity.getLastUpdateWho());
            result.add(diagnosa);
        }

        logger.info("[DiagnosaBoImpl.setTemplateToDiagnosaResult] End <<<<<<<");
        return result;
    }
}