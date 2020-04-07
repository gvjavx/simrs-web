package com.neurix.simrs.master.jenisperiksapasien.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisperiksapasien.bo.AsuransiBo;
import com.neurix.simrs.master.jenisperiksapasien.dao.AsuransiDao;
import com.neurix.simrs.master.jenisperiksapasien.model.Asuransi;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsuransiBoImpl implements AsuransiBo  {

    protected static transient Logger logger = Logger.getLogger(AsuransiBoImpl.class);
    private AsuransiDao asuransiDao;

    @Override
    public List<Asuransi> getByCriteria(Asuransi bean) throws GeneralBOException {
        List<Asuransi> asuransiList = new ArrayList<>();
        if(bean != null){

            Map hsCriteria = new HashMap();

            if(bean.getIdAsuransi() != null && !"".equalsIgnoreCase(bean.getIdAsuransi())){
                hsCriteria.put("id_asuransi", bean.getIdAsuransi());
            }

            hsCriteria.put("flag", "Y");

            List<ImSimrsAsuransiEntity> asuransiEntityList = new ArrayList<>();
            try {
                asuransiEntityList = asuransiDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error("Found Error when search asuransi "+e.getMessage());
            }

            if(asuransiEntityList.size() > 0){
                for (ImSimrsAsuransiEntity entity: asuransiEntityList){
                    Asuransi asuransi = new Asuransi();
                    asuransi.setIdAsuransi(entity.getIdAsuransi());
                    asuransi.setNamaAsuransi(entity.getNamaAsuransi());
                    asuransi.setFlag(entity.getFlag());
                    asuransi.setAction(entity.getAction());
                    asuransi.setCreatedWho(entity.getCreatedWho());
                    asuransi.setCreatedDate(entity.getCreatedDate());
                    asuransi.setLastUpdate(entity.getLastUpdate());
                    asuransi.setLastUpdateWho(entity.getLastUpdateWho());
                    asuransiList.add(asuransi);
                }
            }
        }

        return asuransiList;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setAsuransiDao(AsuransiDao asuransiDao) {
        this.asuransiDao = asuransiDao;
    }
}
