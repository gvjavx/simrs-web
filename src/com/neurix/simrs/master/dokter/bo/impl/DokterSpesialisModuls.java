package com.neurix.simrs.master.dokter.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dokter.dao.DokterSpesialisDao;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.dokter.model.DokterSpesialis;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterSpesialisEntity;
import com.neurix.simrs.master.spesialis.dao.SpesialisDao;
import com.neurix.simrs.master.spesialis.model.ImSimrsSpesialisEntity;
import com.neurix.simrs.master.spesialis.model.Spesialis;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 14/11/2019.
 */
public class DokterSpesialisModuls {
    private static transient Logger logger = Logger.getLogger(DokterSpesialisModuls.class);
    private DokterSpesialisDao dokterSpesialisDao;
    private SpesialisDao spesialisDao;

    protected List<DokterSpesialis> getListDokterSpesialis(DokterSpesialis bean) throws GeneralBOException{
        logger.info("[DokterSpesialisModuls.getDokterSpesialisData] Start >>>>>>>>>");
        List<DokterSpesialis> results = new ArrayList<>();

        if (bean != null && bean.getIdSpesialis() != null && !"".equalsIgnoreCase(bean.getIdSpesialis())){
            Map hsCriteria = new HashMap();
            hsCriteria.put("flag", "Y");
            if (bean.getIdDokter() != null && !"".equalsIgnoreCase(bean.getIdDokter())){
                hsCriteria.put("id_dokter", bean.getIdDokter());
            }
            if (bean.getIdSpesialis() != null && !"".equalsIgnoreCase(bean.getIdSpesialis())){
                hsCriteria.put("id_spesialis", bean.getIdSpesialis());
            }

            List<ImSimrsDokterSpesialisEntity> dokterSpesialisEntities = null;
            try {
                dokterSpesialisEntities = dokterSpesialisDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[DokterSpesialisModuls.getDokterSpesialisData] Error when get data dokter spesialis", e);
            }

            if (!dokterSpesialisEntities.isEmpty()){

                DokterSpesialis dokterSpesialis;
                for (ImSimrsDokterSpesialisEntity entity : dokterSpesialisEntities){
                    dokterSpesialis = new DokterSpesialis();
                    dokterSpesialis.setIdDokter(entity.getPrimariKey().getIdDokter());
                    dokterSpesialis.setIdSpesialis(entity.getPrimariKey().getIdSpesialis());

                    Spesialis spesialis = new Spesialis();
                    spesialis.setIdSpesialis(entity.getPrimariKey().getIdSpesialis());

                    dokterSpesialis.setSpesialisName(getListSpesialis(spesialis).get(0).getNamaSpesialis());
                    dokterSpesialis.setFlag(entity.getFlag());
                    dokterSpesialis.setAction(entity.getAction());
                    dokterSpesialis.setCreatedDate(entity.getCreatedDate());
                    dokterSpesialis.setCreatedWho(entity.getCreatedWho());
                    dokterSpesialis.setLastUpdate(entity.getLastUpdate());
                    dokterSpesialis.setLastUpdateWho(entity.getLastUpdateWho());

                    results.add(dokterSpesialis);
                }

            }

        } else {
            logger.error("[DokterSpesialisModuls.getDokterSpesialisData] Error when get data dokter spesialis data is null");
        }

        logger.info("[DokterSpesialisModuls.getDokterSpesialisData] End <<<<<<<<");
        return results;
    }

    protected List<ImSimrsSpesialisEntity> getListSpesialis(Spesialis bean) throws GeneralBOException{
        logger.info("[DokterSpesialisModuls.getListSpesialis] Start >>>>>>>>>");
        List<ImSimrsSpesialisEntity> results = new ArrayList<>();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_spesialis", bean.getIdSpesialis());

        try {
            results = spesialisDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[DokterSpesialisModuls.getListSpesialis] Error when get master spesialis data ",e);
        }

        logger.info("[DokterSpesialisModuls.getListSpesialis] End <<<<<<<<");
        return results;
    }

    public void setDokterSpesialisDao(DokterSpesialisDao dokterSpesialisDao) {
        this.dokterSpesialisDao = dokterSpesialisDao;
    }

    public void setSpesialisDao(SpesialisDao spesialisDao) {
        this.spesialisDao = spesialisDao;
    }
}
