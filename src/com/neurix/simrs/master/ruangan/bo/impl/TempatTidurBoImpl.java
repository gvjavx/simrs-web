package com.neurix.simrs.master.ruangan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kelasruangan.dao.KelasRuanganDao;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import com.neurix.simrs.master.ruangan.bo.TempatTidurBo;
import com.neurix.simrs.master.ruangan.dao.RuanganDao;
import com.neurix.simrs.master.ruangan.dao.TempatTidurDao;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganTempatTidurEntity;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.master.ruangan.model.TempatTidur;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TempatTidurBoImpl implements TempatTidurBo {
    protected static transient Logger logger = Logger.getLogger(TempatTidurBoImpl.class);
    private TempatTidurDao tempatTidurDao;
    private RuanganDao ruanganDao;
    private KelasRuanganDao kelasRuanganDao;

    @Override
    public CrudResponse saveAdd(List<TempatTidur> list, String isNew) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(list.size() > 0){
            String idRuangan = null;
            TempatTidur tempatTidur = list.get(0);
            if("Y".equalsIgnoreCase(isNew)){
                String id = null;
                try {
                    id = ruanganDao.getNextIdRuangan();
                    response.setStatus("success");
                    response.setMsg("success");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Mohon maaf error saat mencari ID TT, " +e.getMessage());
                }
                if(id != null){
                    MtSimrsRuanganEntity entity = new MtSimrsRuanganEntity();
                    entity.setIdRuangan("RUA"+id);
                    entity.setIdKelasRuangan(tempatTidur.getIdKelasRuangan());
                    entity.setNoRuangan(tempatTidur.getNoRuangan());
                    entity.setNamaRuangan(tempatTidur.getNamaRuangan());
                    entity.setStatusRuangan("Y");
                    entity.setTarif(tempatTidur.getTarif());
                    entity.setBranchId(tempatTidur.getBranchId());
                    entity.setAction(tempatTidur.getAction());
                    entity.setFlag("Y");
                    entity.setCreatedWho(tempatTidur.getCreatedWho());
                    entity.setLastUpdateWho(tempatTidur.getLastUpdateWho());
                    entity.setCreatedDate(tempatTidur.getCreatedDate());
                    entity.setLastUpdate(tempatTidur.getLastUpdate());
                    try {
                        ruanganDao.addAndSave(entity);
                        response.setStatus("success");
                        response.setMsg("success");
                        idRuangan = entity.getIdRuangan();
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
                idRuangan = tempatTidur.getIdRuangan();
            }

            if("success".equalsIgnoreCase(response.getStatus())){
                for (TempatTidur bean: list){
                    MtSimrsRuanganTempatTidurEntity tempatTidurEntity = new MtSimrsRuanganTempatTidurEntity();
                    String id = null;
                    try {
                        id = tempatTidurDao.getNextId();
                        response.setStatus("success");
                        response.setMsg("success");
                    }catch (HibernateException e){
                        response.setStatus("error");
                        response.setMsg("Mohon maaf error saat mencari ID...!, "+e.getMessage());
                        logger.error(e.getMessage());
                    }
                    if(id != null){
                        tempatTidurEntity.setIdTempatTidur(id);
                        tempatTidurEntity.setIdRuangan(idRuangan);
                        tempatTidurEntity.setNamaTempatTidur(bean.getNamaTempatTidur());
                        tempatTidurEntity.setStatus("Y");
                        tempatTidurEntity.setFlag(bean.getFlag());
                        tempatTidurEntity.setAction(bean.getAction());
                        tempatTidurEntity.setCreatedDate(bean.getCreatedDate());
                        tempatTidurEntity.setCreatedWho(bean.getCreatedWho());
                        tempatTidurEntity.setLastUpdate(bean.getLastUpdate());
                        tempatTidurEntity.setLastUpdateWho(bean.getLastUpdateWho());

                        try {
                            tempatTidurDao.addAndSave(tempatTidurEntity);
                            response.setStatus("success");
                            response.setMsg("success");
                        }catch (HibernateException e){
                            response.setStatus("error");
                            response.setMsg("Mohon maaf error saat insert database...!, "+e.getMessage());
                            logger.error(e.getMessage());
                        }
                    }
                }
            }
        }
        return response;
    }

    @Override
    public CrudResponse saveEdit(TempatTidur bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){
            MtSimrsRuanganTempatTidurEntity tempatTidurEntity = new MtSimrsRuanganTempatTidurEntity();
            try {
                tempatTidurEntity = tempatTidurDao.getById("idTempatTidur", bean.getIdTempatTidur());
                response.setStatus("success");
                response.setMsg("success");
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("Mohon maaf error saat mencari ID...!, "+e.getMessage());
                logger.error(e.getMessage());
            }
            if(tempatTidurEntity != null){
                if(bean.getNamaTempatTidur() != null){
                    tempatTidurEntity.setNamaTempatTidur(bean.getNamaTempatTidur());
                }
                if(bean.getIdRuangan() != null){
                    tempatTidurEntity.setIdRuangan(bean.getIdRuangan());
                }
                if(bean.getStatus() != null){
                    tempatTidurEntity.setStatus(bean.getStatus());
                }
                tempatTidurEntity.setFlag(bean.getFlag());
                tempatTidurEntity.setAction(bean.getAction());
                tempatTidurEntity.setLastUpdate(bean.getLastUpdate());
                tempatTidurEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    tempatTidurDao.updateAndSave(tempatTidurEntity);
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMsg("Mohon maaf error saat insert database...!, "+e.getMessage());
                    logger.error(e.getMessage());
                }
            }
        }
        return response;
    }

    @Override
    public List<TempatTidur> getDataTempatTidur(TempatTidur bean) throws GeneralBOException {
        List<TempatTidur> tempatTidurList = new ArrayList<>();
        try {
            tempatTidurList = tempatTidurDao.getDataTempatTidur(bean);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return tempatTidurList;
    }

    @Override
    public List<Ruangan> getComboRuangan(Ruangan bean) throws GeneralBOException {
        List<Ruangan> tempatTidurList = new ArrayList<>();
        try {
            tempatTidurList = tempatTidurDao.getComboRuangan(bean);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return tempatTidurList;
    }

    @Override
    public List<TempatTidur> getByCriteria(TempatTidur bean) throws GeneralBOException {
        List<TempatTidur> pemeriksaanList = new ArrayList<>();
        List<MtSimrsRuanganTempatTidurEntity> entityList = new ArrayList<>();
        HashMap hsCriteria = new HashMap();
        if(bean.getIdTempatTidur() != null && !"".equalsIgnoreCase(bean.getIdTempatTidur())){
            hsCriteria.put("id_tempat_tidur", bean.getIdTempatTidur());
        }
        if(bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())){
            hsCriteria.put("id_ruangan", bean.getIdRuangan());
        }
        if(bean.getNamaTempatTidur() != null && !"".equalsIgnoreCase(bean.getNamaTempatTidur())){
            hsCriteria.put("nama_tempat_tidur", bean.getNamaTempatTidur());
        }
        if(bean.getStatus() != null && !"".equalsIgnoreCase(bean.getStatus())){
            hsCriteria.put("status", bean.getStatus());
        }
        if(bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
            hsCriteria.put("flag", bean.getFlag());
        }else{
            hsCriteria.put("flag", "Y");
        }

        try {
            entityList = tempatTidurDao.getByCriteria(hsCriteria);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }

        if(entityList.size() > 0){
            for (MtSimrsRuanganTempatTidurEntity tempatTidurEntity: entityList){
                TempatTidur param = new TempatTidur();
                param.setIdTempatTidur(tempatTidurEntity.getIdTempatTidur());
                param.setNamaTempatTidur(tempatTidurEntity.getNamaTempatTidur());
                param.setIdRuangan(tempatTidurEntity.getIdRuangan());
                param.setFlag(tempatTidurEntity.getFlag());
                param.setAction(tempatTidurEntity.getAction());
                param.setCreatedWho(tempatTidurEntity.getCreatedWho());
                param.setCreatedDate(tempatTidurEntity.getCreatedDate());
                param.setLastUpdateWho(tempatTidurEntity.getLastUpdateWho());
                param.setLastUpdate(tempatTidurEntity.getLastUpdate());
                MtSimrsRuanganEntity ruanganEntity = ruanganDao.getById("idRuangan", tempatTidurEntity.getIdRuangan());
                if(ruanganEntity != null){
                    param.setNoRuangan(ruanganEntity.getNoRuangan());
                    param.setNamaRuangan(ruanganEntity.getNamaRuangan());
                    param.setTarif(ruanganEntity.getTarif());
                    ImSimrsKelasRuanganEntity kelasRuanganEntity = kelasRuanganDao.getById("idKelasRuangan", ruanganEntity.getIdKelasRuangan());
                    if(kelasRuanganEntity != null){
                        param.setIdKelasRuangan(kelasRuanganEntity.getIdKelasRuangan());
                        param.setNamaKelasRuangan(kelasRuanganEntity.getNamaKelasRuangan());
                    }
                }
                pemeriksaanList.add(param);
            }
        }
        return pemeriksaanList;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setTempatTidurDao(TempatTidurDao tempatTidurDao) {
        this.tempatTidurDao = tempatTidurDao;
    }

    public void setRuanganDao(RuanganDao ruanganDao) {
        this.ruanganDao = ruanganDao;
    }

    public void setKelasRuanganDao(KelasRuanganDao kelasRuanganDao) {
        this.kelasRuanganDao = kelasRuanganDao;
    }
}