package com.neurix.simrs.master.dokter.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.dao.DokterDao;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.dokter.model.DokterSpesialis;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterEntity;
import com.neurix.simrs.master.pelayanan.dao.PelayananSpesialisDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPoliSpesialisEntity;
import com.neurix.simrs.master.spesialis.model.ImSimrsSpesialisEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 14/11/2019.
 */
public class DokterBoImpl extends DokterSpesialisModuls implements DokterBo{
    private static transient Logger logger = Logger.getLogger(DokterBoImpl.class);
    private DokterDao dokterDao;
    private PelayananSpesialisDao pelayananSpesialisDao;

    @Override
    public List<Dokter> getByCriteria(Dokter bean) throws GeneralBOException {
        logger.info("[DokterBoImpl.getByCriteria] Start >>>>>>>>");
        List<Dokter> results = new ArrayList<>();

        if (bean != null){
            List<ImSimrsDokterEntity> entities = getListEntityDokter(bean);
            if (!entities.isEmpty()){
                results = setToTemplateDokter(entities);
            }
        }

        logger.info("[DokterBoImpl.getByCriteria] End <<<<<<<<");
        return results;
    }

    protected List<ImSimrsDokterEntity> getListEntityDokter(Dokter bean) throws GeneralBOException{
        logger.info("[DokterBoImpl.getListEntityDokter] Start >>>>>>>>");
        List<ImSimrsDokterEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        hsCriteria.put("id_dokter", bean.getIdDokter());

        try {
            results = dokterDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[DokterBoImpl.getListEntityDokter] Error When search dokter data ");
        }

        logger.info("[DokterBoImpl.getListEntityDokter] End <<<<<<<<");
        return results;
    }

    private List<Dokter> setToTemplateDokter(List<ImSimrsDokterEntity> entityList){
        logger.info("[DokterBoImpl.setToTemplateDokter] Start >>>>>>>>");
        List<Dokter> results = new ArrayList<>();

        Dokter dokter;
        for (ImSimrsDokterEntity entity : entityList){
            dokter = new Dokter();
            dokter.setIdDokter(entity.getIdDokter());
            dokter.setNamaDokter(entity.getNamaDokter());
            dokter.setFlag(entity.getFlag());
            dokter.setAction(entity.getAction());
            dokter.setCreatedDate(entity.getCreatedDate());
            dokter.setCreatedWho(entity.getCreatedWho());
            dokter.setLastUpdate(entity.getLastUpdate());
            dokter.setLastUpdateWho(entity.getLastUpdateWho());
            results.add(dokter);
        }

        logger.info("[DokterBoImpl.setToTemplateDokter] End <<<<<<<<");
        return results;
    }

    @Override
    public List<Dokter> getByIdPelayanan(String idPelayanan, String branchId) throws GeneralBOException {
        logger.info("[DokterBoImpl.getByIdPelayanan] Start >>>>>>>>");
        List<Dokter> results = new ArrayList<>();
        if (idPelayanan != null && !"".equalsIgnoreCase(idPelayanan)){

            List<ImSimrsPoliSpesialisEntity> poliSpesialisEntities = null;
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_pelayanan", idPelayanan);

            try {
                poliSpesialisEntities = pelayananSpesialisDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.info("[DokterBoImpl.getByIdPelayanan] Error when get dokter by pelayanan ", e);
            }

            if (!poliSpesialisEntities.isEmpty()){
                for (ImSimrsPoliSpesialisEntity poliEntity : poliSpesialisEntities){
                    DokterSpesialis dokterSpesialis = new DokterSpesialis();
                    dokterSpesialis.setIdSpesialis(poliEntity.getPrimaryKey().getIdSpesialis());

                    List<DokterSpesialis> dokterSpesialisList = getListDokterSpesialis(dokterSpesialis);
                    if (!dokterSpesialisList.isEmpty()){
                        Dokter dokter;
                        for (DokterSpesialis listDokter : dokterSpesialisList){

                            dokter = new Dokter();
                            dokter.setIdDokter(listDokter.getIdDokter());
                            ImSimrsDokterEntity dokterData = getListEntityDokter(dokter).get(0);

                            dokter.setIdSpesialis(listDokter.getIdSpesialis());
                            dokter.setNamaSpesialis(listDokter.getSpesialisName());
                            dokter.setFlag(dokterData.getFlag());
                            dokter.setAction(dokterData.getAction());
                            dokter.setLastUpdate(dokterData.getLastUpdate());
                            dokter.setLastUpdateWho(dokterData.getLastUpdateWho());
                            dokter.setCreatedDate(dokterData.getCreatedDate());
                            dokter.setCreatedWho(dokterData.getCreatedWho());
                            results.add(dokter);
                        }
                    }
                }
            }
        }
        logger.info("[DokterBoImpl.getByIdPelayanan] End <<<<<<<<");
        return null;
    }

    public void setDokterDao(DokterDao dokterDao) {
        this.dokterDao = dokterDao;
    }

    public void setPelayananSpesialisDao(PelayananSpesialisDao pelayananSpesialisDao) {
        this.pelayananSpesialisDao = pelayananSpesialisDao;
    }
}
