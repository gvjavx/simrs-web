package com.neurix.simrs.master.askep.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.askep.bo.DiagnosaAsuhanKeperawatanBo;
import com.neurix.simrs.master.askep.dao.DiagnosaAsuhanKeperawatanDao;
import com.neurix.simrs.master.askep.model.DiagnosaAsuhanKeperawatan;
import com.neurix.simrs.master.askep.model.ImSimrsDiagnosaAsuhanKeperawatanEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagnosaAsuhanKeperawatanBoImpl implements DiagnosaAsuhanKeperawatanBo {

    private static transient Logger logger = Logger.getLogger(DiagnosaAsuhanKeperawatanBoImpl.class);
    private DiagnosaAsuhanKeperawatanDao diagnosaAsuhanKeperawatanDao;

    @Override
    public List<DiagnosaAsuhanKeperawatan> getByCriteria(DiagnosaAsuhanKeperawatan bean) throws GeneralBOException {
        List<DiagnosaAsuhanKeperawatan> list = new ArrayList<>();

        if(bean != null){

            Map hsCriteria = new HashMap();
            if(bean.getIdDiagnosaAsuhanKeperawatan() != null && !"".equalsIgnoreCase(bean.getIdDiagnosaAsuhanKeperawatan())){
                hsCriteria.put("id_diagnosa_askep", bean.getIdDiagnosaAsuhanKeperawatan());
            }
            if(bean.getDiagnosa() != null && !"".equalsIgnoreCase(bean.getDiagnosa())){
                hsCriteria.put("diagnosa", bean.getDiagnosa());
            }

            List<ImSimrsDiagnosaAsuhanKeperawatanEntity> entityList = new ArrayList<>();

            try {
                entityList = diagnosaAsuhanKeperawatanDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }

            if(entityList.size() > 0){
                for (ImSimrsDiagnosaAsuhanKeperawatanEntity entity: entityList){
                    DiagnosaAsuhanKeperawatan diagnosaAsuhanKeperawatan = new DiagnosaAsuhanKeperawatan();
                    diagnosaAsuhanKeperawatan.setIdDiagnosaAsuhanKeperawatan(entity.getIdDiagnosaAsuhanKeperawatan());
                    diagnosaAsuhanKeperawatan.setDiagnosa(entity.getDiagnosa());
                    diagnosaAsuhanKeperawatan.setAction(entity.getAction());
                    diagnosaAsuhanKeperawatan.setFlag(entity.getFlag());
                    diagnosaAsuhanKeperawatan.setCreatedDate(entity.getCreatedDate());
                    diagnosaAsuhanKeperawatan.setCreatedWho(entity.getCreatedWho());
                    diagnosaAsuhanKeperawatan.setLastUpdate(entity.getLastUpdate());
                    diagnosaAsuhanKeperawatan.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(diagnosaAsuhanKeperawatan);
                }
            }
        }
        return list;
    }

    @Override
    public List<DiagnosaAsuhanKeperawatan> getListDiagnosa(String key) throws GeneralBOException {
        return diagnosaAsuhanKeperawatanDao.getListDiagnosa(key);
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setDiagnosaAsuhanKeperawatanDao(DiagnosaAsuhanKeperawatanDao diagnosaAsuhanKeperawatanDao) {
        this.diagnosaAsuhanKeperawatanDao = diagnosaAsuhanKeperawatanDao;
    }
}
