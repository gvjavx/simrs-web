package com.neurix.simrs.transaksi.teamdokter.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dokter.bo.impl.DokterBoImpl;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.dao.DokterTeamDao;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 18/11/2019.
 */
public class TeamDokterBoImpl extends DokterBoImpl implements TeamDokterBo{
    private static transient Logger logger = Logger.getLogger(TeamDokterBoImpl.class);
    private DokterTeamDao dokterTeamDao;

    @Override
    public List<DokterTeam> getByCriteria(DokterTeam bean) throws GeneralBOException {
        logger.info("[TeamDokterBoImpl.getByCriteria] Start >>>>>>>>");
        List<DokterTeam> results = new ArrayList<>();
        if (bean != null){
            List<ItSimrsDokterTeamEntity> entities = getListEntityTeamDokter(bean);
            if (!entities.isEmpty()){
                DokterTeam dokterTeam;
                for (ItSimrsDokterTeamEntity entity : entities){
                    dokterTeam = new DokterTeam();
                    dokterTeam.setIdTeamDokter(entity.getIdTeamDokter());
                    dokterTeam.setIdDokter(entity.getIdDokter());
                    dokterTeam.setIdDetailCheckup(entity.getIdDetailCheckup());
                    dokterTeam.setFlag(entity.getFlag());
                    dokterTeam.setAction(entity.getAction());
                    dokterTeam.setCreatedDate(entity.getCreatedDate());
                    dokterTeam.setCreatedWho(entity.getCreatedWho());
                    dokterTeam.setLastUpdate(entity.getLastUpdate());
                    dokterTeam.setLastUpdateWho(entity.getLastUpdateWho());

                    Dokter dokter = new Dokter();
                    dokter.setIdDokter(entity.getIdDokter());

                    List<Dokter> dokters = getByCriteria(dokter);

                    if (!dokters.isEmpty()){
                        Dokter dokterData = dokters.get(0);
                        dokterTeam.setNamaDokter(dokterData.getNamaDokter());
                        dokterTeam.setNamaSpesialis(dokterData.getNamaSpesialis());
                    }

                    results.add(dokterTeam);
                }
            }
        }

        logger.info("[TeamDokterBoImpl.getByCriteria] End <<<<<<<<");
        return results;
    }

    @Override
    public void savaAdd(DokterTeam bean) throws GeneralBOException {
        logger.info("[TeamDokterBoImpl.savaAdd] Start >>>>>>>>");

        ItSimrsDokterTeamEntity entity = new ItSimrsDokterTeamEntity();
        String id = getNextId();

        entity.setIdTeamDokter("TDT"+id);
        entity.setIdDokter(bean.getIdDokter());
        entity.setIdDetailCheckup(bean.getIdDetailCheckup());
        entity.setFlag("Y");
        entity.setAction("C");
        entity.setCreatedDate(bean.getCreatedDate());
        entity.setCreatedWho(bean.getCreatedWho());
        entity.setLastUpdate(bean.getLastUpdate());
        entity.setLastUpdateWho(bean.getLastUpdateWho());

        try {
            dokterTeamDao.addAndSave(entity);
        } catch (HibernateException e){
            logger.error("[TeamDokterBoImpl.savaAdd] Error when save add dokter team ",e);
            throw new GeneralBOException("[TeamDokterBoImpl.savaAdd] Error when save add dokter team "+e.getMessage());
        }

        logger.info("[TeamDokterBoImpl.savaAdd] End <<<<<<<<");
    }

    @Override
    public void saveEdit(DokterTeam bean) throws GeneralBOException {
        logger.info("[TeamDokterBoImpl.saveEdit] Start >>>>>>>>");

        ItSimrsDokterTeamEntity entityList = null;

        try {
            entityList = dokterTeamDao.getById("idTeamDokter", bean.getIdTeamDokter());
        } catch (HibernateException e){
            logger.error("[TeamDokterBoImpl.saveEdit] Error when save edit dokter team ",e);
            throw new GeneralBOException("[TeamDokterBoImpl.savaAdd] Error when save edit dokter team "+e.getMessage());
        }
        if(entityList != null){
            entityList.setIdDokter(bean.getIdDokter());
            entityList.setAction(bean.getAction());
            entityList.setLastUpdate(bean.getLastUpdate());
            entityList.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                dokterTeamDao.updateAndSave(entityList);
            } catch (HibernateException e){
                logger.error("[TeamDokterBoImpl.saveEdit] Error when edit dokter team ",e);
                throw new GeneralBOException("[TeamDokterBoImpl.saveEdit] Error when save edit dokter team "+e.getMessage());
            }
        }

        logger.info("[TeamDokterBoImpl.saveEdit] End <<<<<<<<");
    }

    protected List<ItSimrsDokterTeamEntity> getListEntityTeamDokter(DokterTeam bean) throws GeneralBOException{
        logger.info("[TeamDokterBoImpl.getListEntityTeamDokter] Start >>>>>>>>");
        List<ItSimrsDokterTeamEntity> entities = new ArrayList<>();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        try {
            entities = dokterTeamDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[TeamDokterBoImpl.getListEntityTeamDokter] Error when get data");
        }

        logger.info("[TeamDokterBoImpl.getListEntityTeamDokter] End <<<<<<<<");
        return entities;
    }

    private String getNextId(){
        String id = "";
        try {
            id = dokterTeamDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[TeamDokterBoImpl.getNextId] Error when get seq id team dokter");
        }
        return id;
    }

    public void setDokterTeamDao(DokterTeamDao dokterTeamDao) {
        this.dokterTeamDao = dokterTeamDao;
    }

}
