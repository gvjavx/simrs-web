package com.neurix.simrs.master.tindakanicd9.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.master.tindakanicd9.bo.TindakanICD9Bo;
import com.neurix.simrs.master.tindakanicd9.dao.TindakanICD9Dao;
import com.neurix.simrs.master.tindakanicd9.model.ImSimrsTindakanICD9Entity;
import com.neurix.simrs.master.tindakanicd9.model.TindakanICD9;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TindakanICD9BoImpl implements TindakanICD9Bo {

    private static transient Logger logger = Logger.getLogger(TindakanICD9BoImpl.class);
    private TindakanICD9Dao tindakanICD9Dao;

    @Override
    public List<TindakanICD9> getByCriteria(TindakanICD9 bean) throws GeneralBOException {
        logger.info("[TindakanBoImpl.getByCriteria] Start >>>>>>>");
        List<TindakanICD9> results = new ArrayList<>();

        if (bean != null) {
            List<ImSimrsTindakanICD9Entity> entityList = new ArrayList<>();
            Map hsCiteria = new HashMap();

            if (bean.getIdIcd9() != null && !"".equalsIgnoreCase(bean.getIdIcd9())) {
                hsCiteria.put("id_icd9", bean.getIdIcd9());
            }
            if (bean.getNamaIcd9() != null && !"".equalsIgnoreCase(bean.getNamaIcd9())) {
                hsCiteria.put("nama_icd9", bean.getNamaIcd9());
            }

            hsCiteria.put("flag", "Y");

            try {
                entityList = tindakanICD9Dao.getByCriteria(hsCiteria);
            }catch (HibernateException e){
                logger.error("Found error "+e.getMessage());
            }
            if (entityList.size() > 0) {
                for (ImSimrsTindakanICD9Entity entity: entityList){
                    TindakanICD9 tindakanICD9 = new TindakanICD9();
                    tindakanICD9.setIdIcd9(entity.getIdIcd9());
                    tindakanICD9.setNamaIcd9(entity.getNamaIcd9());
                    tindakanICD9.setFlag(entity.getFlag());
                    tindakanICD9.setAction(entity.getAction());
                    tindakanICD9.setCreatedDate(entity.getCreatedDate());
                    tindakanICD9.setCreatedWho(entity.getCreatedWho());
                    tindakanICD9.setLastUpdate(entity.getLastUpdate());
                    tindakanICD9.setLastUpdateWho(entity.getLastUpdateWho());
                    results.add(tindakanICD9);
                }
            }
        }

        logger.info("[TindakanBoImpl.getByCriteria] End <<<<<<<");
        return results;
    }

    @Override
    public List<TindakanICD9> getSearchICD9(String key) throws GeneralBOException {
        return tindakanICD9Dao.getSearchICD9(key);
    }

    public void setTindakanICD9Dao(TindakanICD9Dao tindakanICD9Dao) {
        this.tindakanICD9Dao = tindakanICD9Dao;
    }

    public static Logger getLogger() {
        return logger;
    }
}
