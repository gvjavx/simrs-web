package com.neurix.simrs.transaksi.teamdokter.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dokter.bo.impl.DokterBoImpl;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.transaksi.CrudResponse;
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
                    dokterTeam.setIdPelayanan(entity.getIdPelayanan());

                    Dokter dokter = new Dokter();
                    dokter.setIdDokter(entity.getIdDokter());

                    List<Dokter> dokters = getByCriteria(dokter);

                    if (!dokters.isEmpty()){
                        Dokter dokterData = dokters.get(0);
                        dokterTeam.setNamaDokter(dokterData.getNamaDokter());
                        dokterTeam.setNamaSpesialis(dokterData.getNamaSpesialis());
                    }

                    if(entity.getIdPelayanan() != null && !"".equalsIgnoreCase(entity.getIdPelayanan())){
                        dokterTeam.setNamaPelayanan(dokterTeamDao.namaPelayanan(entity.getIdPelayanan()));
                    }

                    results.add(dokterTeam);
                }
            }
        }

        logger.info("[TeamDokterBoImpl.getByCriteria] End <<<<<<<<");
        return results;
    }

    @Override
    public CrudResponse savaAdd(DokterTeam bean) throws GeneralBOException {
        logger.info("[TeamDokterBoImpl.savaAdd] Start >>>>>>>>");
        CrudResponse response = new CrudResponse();
        ItSimrsDokterTeamEntity entity = new ItSimrsDokterTeamEntity();
        String id = getNextId();

        entity.setIdTeamDokter("TDT"+id);
        entity.setIdDokter(bean.getIdDokter());
        entity.setIdDetailCheckup(bean.getIdDetailCheckup());
        entity.setIdPelayanan(bean.getIdPelayanan());
        entity.setFlag("Y");
        entity.setAction("C");
        entity.setCreatedDate(bean.getCreatedDate());
        entity.setCreatedWho(bean.getCreatedWho());
        entity.setLastUpdate(bean.getLastUpdate());
        entity.setLastUpdateWho(bean.getLastUpdateWho());

        try {
            dokterTeamDao.addAndSave(entity);
            response.setStatus("success");
            response.setMsg("Berhasil");
        } catch (HibernateException e){
            logger.error("[TeamDokterBoImpl.savaAdd] Error when save add dokter team ",e);
            response.setStatus("error");
            response.setMsg("Error"+e.getMessage());
        }

        logger.info("[TeamDokterBoImpl.savaAdd] End <<<<<<<<");
        return response;
    }

    @Override
    public CrudResponse saveEdit(DokterTeam bean) throws GeneralBOException {
        logger.info("[TeamDokterBoImpl.saveEdit] Start >>>>>>>>");
        CrudResponse response = new CrudResponse();
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
                response.setStatus("success");
                response.setMsg("Bwrhasil");
            } catch (HibernateException e){
                logger.error("[TeamDokterBoImpl.saveEdit] Error when edit dokter team ",e);
                response.setStatus("error");
                response.setMsg("Error"+e.getMessage());
            }
        }else{
            response.setStatus("error");
            response.setMsg("ID Dokte team tidak ditemukan...!");
        }

        logger.info("[TeamDokterBoImpl.saveEdit] End <<<<<<<<");
        return response;
    }

    @Override
    public CrudResponse doneDokter(DokterTeam bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        ItSimrsDokterTeamEntity entityList = null;
        try {
            entityList = dokterTeamDao.getById("idTeamDokter", bean.getIdTeamDokter());
        } catch (HibernateException e){
            logger.error("[TeamDokterBoImpl.saveEdit] Error when save edit dokter team ",e);
            throw new GeneralBOException("[TeamDokterBoImpl.savaAdd] Error when save edit dokter team "+e.getMessage());
        }
        if(entityList != null){
            entityList.setFlagApprove("S");
            entityList.setAction(bean.getAction());
            entityList.setLastUpdate(bean.getLastUpdate());
            entityList.setLastUpdateWho(bean.getLastUpdateWho());
            try {
                dokterTeamDao.updateAndSave(entityList);
                response.setStatus("success");
                response.setMsg("Bwrhasil");
            } catch (HibernateException e){
                logger.error("[TeamDokterBoImpl.saveEdit] Error when edit dokter team ",e);
                response.setStatus("error");
                response.setMsg("Error"+e.getMessage());
            }
        }else{
            response.setStatus("error");
            response.setMsg("ID Dokte team tidak ditemukan...!");
        }
        return response;
    }

    @Override
    public DokterTeam getNamaDokter(String idDetailCheckup) throws GeneralBOException {
        return dokterTeamDao.getNamaDokter(idDetailCheckup);
    }

    @Override
    public CrudResponse saveApproveDokter(DokterTeam bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){
            ItSimrsDokterTeamEntity dokterTeamEntity = dokterTeamDao.getById("idTeamDokter", bean.getIdTeamDokter());
            if(dokterTeamEntity != null){
                dokterTeamEntity.setFlagApprove(bean.getFlagApprove());
                dokterTeamEntity.setKeterangan(bean.getKeterangan());
                dokterTeamEntity.setAction("U");
                dokterTeamEntity.setLastUpdate(bean.getLastUpdate());
                dokterTeamEntity.setLastUpdateWho(bean.getLastUpdateWho());
                try {
                    dokterTeamDao.updateAndSave(dokterTeamEntity);
                    response.setStatus("success");
                    response.setMsg("success");
                }catch (HibernateException e){
                    logger.error(e.getMessage());
                    response.setStatus("error");
                    response.setMsg("Error when edit flag approve, "+e.getMessage());
                }
            }
        }
        return response;
    }

    @Override
    public CrudResponse saveDokterTeam(DokterTeam bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        ItSimrsDokterTeamEntity entity = new ItSimrsDokterTeamEntity();
        entity.setIdTeamDokter("TDT"+getNextId());
        entity.setIdDokter(bean.getIdDokter());
        entity.setIdDetailCheckup(bean.getIdDetailCheckup());
        entity.setIdPelayanan(bean.getIdPelayanan());
        entity.setJenisDpjp(bean.getJenisDpjp());
        entity.setFlag("Y");
        entity.setAction("C");
        entity.setCreatedDate(bean.getCreatedDate());
        entity.setCreatedWho(bean.getCreatedWho());
        entity.setLastUpdate(bean.getLastUpdate());
        entity.setLastUpdateWho(bean.getLastUpdateWho());

        try {
            dokterTeamDao.addAndSave(entity);
            response.setStatus("success");
            response.setMsg("Berhasil");
        } catch (HibernateException e){
            logger.error("[TeamDokterBoImpl.savaAdd] Error when save add dokter team ",e);
            response.setStatus("error");
            response.setMsg("Error"+e.getMessage());
        }
        return response;
    }

    @Override
    public List<ItSimrsDokterTeamEntity> cekRequestDokter(String idDetailCheckup) throws GeneralBOException {
        List<ItSimrsDokterTeamEntity> entityList = new ArrayList<>();
        if(idDetailCheckup != null){
            try {
                entityList = dokterTeamDao.cekRequestDokter(idDetailCheckup);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }
        }
        return entityList;
    }

    @Override
    public List<DokterTeam> cekRequestDokterByIdDokter(String idDokter, String flagApprove) throws GeneralBOException {
        List<DokterTeam> entityList = new ArrayList<>();
        if(idDokter != null){
            try {
                entityList = dokterTeamDao.cekRequestDokterByIdDokter(idDokter, flagApprove);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }
        }
        return entityList;
    }

    @Override
    public List<ItSimrsDokterTeamEntity> getListEntityTeamDokter(DokterTeam bean) throws GeneralBOException{
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
