package com.neurix.simrs.master.labdetail.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.lab.dao.LabDao;
import com.neurix.simrs.master.lab.model.ImSimrsLabEntity;
import com.neurix.simrs.master.labdetail.bo.LabDetailBo;
import com.neurix.simrs.master.labdetail.dao.LabDetailDao;
import com.neurix.simrs.master.labdetail.model.ImSimrsLabDetailEntity;
import com.neurix.simrs.master.labdetail.model.LabDetail;
import com.neurix.simrs.master.parampemeriksaan.dao.ParameterPemeriksaanDao;
import com.neurix.simrs.master.parampemeriksaan.model.ImSimrsParameterPemeriksaanEntity;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LabDetailBoImpl implements LabDetailBo {

    protected static transient Logger logger = Logger.getLogger(com.neurix.simrs.master.labdetail.bo.impl.LabDetailBoImpl.class);
    private LabDetailDao labDetailDao;
    private ParameterPemeriksaanDao parameterPemeriksaanDao;
    private LabDao labDao;

    @Override
    public CrudResponse saveEdit(LabDetail bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (bean != null) {
            ImSimrsLabDetailEntity entity = null;
            ImSimrsLabEntity labEntity = new ImSimrsLabEntity();
            try {
                labEntity = labDao.getById("idLab", bean.getIdLab());
            }catch (HibernateException e){
                logger.error(e.getMessage());
                response.setStatus("error");
                response.setMsg("Mohon maaf error saat mencari ID lab, " +e.getMessage());
            }

            if(labEntity != null){
                labEntity.setNamaLab(bean.getNamaLab());
                labEntity.setLastUpdate(bean.getLastUpdate());
                labEntity.setLastUpdateWho(bean.getLastUpdateWho());
                labEntity.setAction("U");
                try {
                    labDao.updateAndSave(labEntity);
                }catch (HibernateException e){
                    logger.error(e.getMessage());
                    response.setStatus("error");
                    response.setMsg("Mohon maaf error saat update database, " +e.getMessage());
                }

                try {
                    entity = labDetailDao.getById("idLabDetail", bean.getIdLabDetail());
                    response.setStatus("success");
                    response.setMsg("success");
                } catch (HibernateException e) {
                    logger.error(e.getMessage());
                    response.setStatus("error");
                    response.setMsg("Mohon maaf error saat mencari ID lab, " +e.getMessage());
                }

                if (entity != null) {
                    if(bean.getIdLab() != null){
                        entity.setIdLab(bean.getIdLab());
                    }
                    if(bean.getIdParameterPemeriksaan() != null){
                        entity.setIdParameterPemeriksaan(bean.getIdParameterPemeriksaan());
                    }
                    if(bean.getTarif() != null){
                        entity.setTarif(bean.getTarif());
                    }
                    entity.setFlag(bean.getFlag());
                    entity.setAction(bean.getAction());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    entity.setLastUpdate(bean.getLastUpdate());
                    try {
                        labDetailDao.updateAndSave(entity);
                        response.setStatus("success");
                        response.setMsg("success");
                    } catch (HibernateException e) {
                        logger.error(e.getMessage());
                        response.setStatus("error");
                        response.setMsg("Mohon maaf error saat update database, " +e.getMessage());
                    }
                } else {
                    response.setStatus("error");
                    response.setMsg("Mohon maaf error tidak menukan ID lab");
                }
            }
        }
        return response;
    }

    @Override
    public CrudResponse saveAdd(List<LabDetail> list, String isNew) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (list.size() > 0) {
            String idLab = null;
            LabDetail labDetail = list.get(0);
            if("Y".equalsIgnoreCase(isNew)){
                String id = null;
                try {
                    id = labDao.getNextLabId();
                    response.setStatus("success");
                    response.setMsg("success");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Mohon maaf error saat mencari ID lab, " +e.getMessage());
                }
                if(id != null){
                    ImSimrsLabEntity entity = new ImSimrsLabEntity();
                    entity.setIdLab(id);
                    entity.setNamaLab(labDetail.getNamaLab());
                    entity.setIdKategoriLab(labDetail.getIdKategoriLab());
                    entity.setFlag(labDetail.getFlag());
                    entity.setAction(labDetail.getAction());
                    entity.setCreatedWho(labDetail.getCreatedWho());
                    entity.setLastUpdateWho(labDetail.getLastUpdateWho());
                    entity.setCreatedDate(labDetail.getCreatedDate());
                    entity.setLastUpdate(labDetail.getLastUpdate());
                    try {
                        labDao.addAndSave(entity);
                        response.setStatus("success");
                        response.setMsg("success");
                        idLab = entity.getIdLab();
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg("Mohon maaf error saat mencari ID lab, " +e.getMessage());
                    }
                }else {
                    response.setStatus("error");
                    response.setMsg("Mohon maaf error saat mencari ID lab");
                }
            }else{
                response.setStatus("success");
                response.setMsg("success");
                idLab = labDetail.getIdLab();
            }

            if("success".equalsIgnoreCase(response.getStatus())){
                for (LabDetail bean: list){
                    String id = null;
                    try {
                        id = labDetailDao.getNextLabDetailId();
                        response.setStatus("success");
                        response.setMsg("success");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg("Mohon maaf error saat mencari ID lab, " +e.getMessage());
                    }
                    if (id != null) {
                        ImSimrsLabDetailEntity entity = new ImSimrsLabDetailEntity();
                        entity.setIdLabDetail(id);
                        entity.setIdLab(idLab);
                        entity.setIdParameterPemeriksaan(bean.getIdParameterPemeriksaan());
                        entity.setTarif(bean.getTarif());
                        entity.setBranchId(bean.getBranchId());
                        entity.setFlag(bean.getFlag());
                        entity.setAction(bean.getAction());
                        entity.setCreatedWho(bean.getCreatedWho());
                        entity.setLastUpdateWho(bean.getLastUpdateWho());
                        entity.setCreatedDate(bean.getCreatedDate());
                        entity.setLastUpdate(bean.getLastUpdate());
                        try {
                            labDetailDao.addAndSave(entity);
                            response.setStatus("success");
                            response.setMsg("success");
                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMsg("Mohon maaf error saat mencari ID lab, " +e.getMessage());
                        }
                    } else {
                        response.setStatus("error");
                        response.setMsg("Mohon maaf error saat mencari ID Lab");
                    }
                }
            }
        }
        return response;
    }

    @Override
    public List<LabDetail> getByCriteria(LabDetail bean) throws GeneralBOException {
        List<LabDetail> result = new ArrayList<>();
        List<ImSimrsLabDetailEntity> labDetailEntityList = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdLabDetail() != null && !"".equalsIgnoreCase(bean.getIdLabDetail())) {
                hsCriteria.put("id_lab_detail", bean.getIdLabDetail());
            }
            if (bean.getIdLab() != null && !"".equalsIgnoreCase(bean.getIdLab())) {
                hsCriteria.put("id_lab", bean.getIdLab());
            }
            if (bean.getIdParameterPemeriksaan() != null && !"".equalsIgnoreCase(bean.getIdParameterPemeriksaan())) {
                hsCriteria.put("id_parameter_pemeriksaan", bean.getIdParameterPemeriksaan());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                hsCriteria.put("flag", bean.getFlag());
            } else {
                hsCriteria.put("flag", "Y");
            }
            try {
                labDetailEntityList = labDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[LabDetailBoImpl.getByCriteria] error when get data lab detailt by get by criteria " + e.getMessage());
            }

            if (labDetailEntityList.size() > 0) {
                LabDetail labDetail;
                for (ImSimrsLabDetailEntity labDetailEntity : labDetailEntityList) {
                    labDetail = new LabDetail();
                    labDetail.setIdLabDetail(labDetailEntity.getIdLabDetail());
                    labDetail.setIdLab(labDetailEntity.getIdLab());
                    labDetail.setFlag(labDetailEntity.getFlag());
                    labDetail.setAction(labDetailEntity.getAction());
                    labDetail.setCreatedDate(labDetailEntity.getCreatedDate());
                    labDetail.setStCreatedDate(labDetailEntity.getCreatedDate().toString());
                    labDetail.setCreatedWho(labDetailEntity.getCreatedWho());
                    labDetail.setStLastUpdate(labDetailEntity.getLastUpdate().toString());
                    labDetail.setLastUpdate(labDetailEntity.getLastUpdate());
                    labDetail.setLastUpdateWho(labDetailEntity.getLastUpdateWho());
                    try {
                        ImSimrsParameterPemeriksaanEntity pemeriksaanEntity = parameterPemeriksaanDao.getById("idParameterPemeriksaan", labDetailEntity.getIdParameterPemeriksaan());
                        if(pemeriksaanEntity != null){
                            labDetail.setNamaDetailPeriksa(pemeriksaanEntity.getNamaPemeriksaan());
                            labDetail.setKeteranganAcuanL(pemeriksaanEntity.getKeteranganAcuanL());
                            labDetail.setKeteranganAcuanP(pemeriksaanEntity.getKeteranganAcuanP());
                            labDetail.setSatuan(pemeriksaanEntity.getSatuan());
                        }
                    }catch (HibernateException e){
                        logger.error(e.getMessage());
                    }
                    result.add(labDetail);
                }
            }
        }
        return result;
    }

    @Override
    public ImSimrsLabDetailEntity getLabDetailEntityById(String idParameter) throws GeneralBOException {
        return labDetailDao.getById("idLabDetail", idParameter);
    }

    @Override
    public List<LabDetail> getDetaillab(String idLab, String branchId) throws GeneralBOException {
        List<LabDetail> labDetails = new ArrayList<>();
        logger.info("[LabDetailBoImpl.getDetaillab] Start <<<<<<<");
        try {
            labDetails = labDetailDao.getDetailLab(idLab, branchId);
        } catch (HibernateException e) {
            logger.error("[LabDetailBoImpl.getDetaillab] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        logger.info("[LabDetailBoImpl.getDetaillab] End <<<<<<<");
        return labDetails;
    }

    @Override
    public List<LabDetail> getDataParameterPemeriksaan(LabDetail bean) throws GeneralBOException {
        List<LabDetail> result = new ArrayList<>();
        try {
            result = labDetailDao.getDataParameterPemeriksaan(bean);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return result;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setLabDetailDao(LabDetailDao labDetailDao) {
        this.labDetailDao = labDetailDao;
    }

    public void setParameterPemeriksaanDao(ParameterPemeriksaanDao parameterPemeriksaanDao) {
        this.parameterPemeriksaanDao = parameterPemeriksaanDao;
    }

    public void setLabDao(LabDao labDao) {
        this.labDao = labDao;
    }
}