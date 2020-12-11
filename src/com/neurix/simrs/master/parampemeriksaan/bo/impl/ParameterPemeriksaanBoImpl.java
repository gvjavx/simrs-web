package com.neurix.simrs.master.parampemeriksaan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kategorilab.dao.KategoriLabDao;
import com.neurix.simrs.master.kategorilab.model.ImSimrsKategoriLabEntity;
import com.neurix.simrs.master.parampemeriksaan.bo.ParameterPemeriksaanBo;
import com.neurix.simrs.master.parampemeriksaan.dao.ParameterPemeriksaanDao;
import com.neurix.simrs.master.parampemeriksaan.model.ImSimrsParameterPemeriksaanEntity;
import com.neurix.simrs.master.parampemeriksaan.model.ParameterPemeriksaan;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParameterPemeriksaanBoImpl implements ParameterPemeriksaanBo {

    protected static transient Logger logger = Logger.getLogger(ParameterPemeriksaanBoImpl.class);
    private ParameterPemeriksaanDao parameterPemeriksaanDao;
    private KategoriLabDao kategoriLabDao;

    @Override
    public CrudResponse saveAdd(ParameterPemeriksaan bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){
            ImSimrsParameterPemeriksaanEntity pemeriksaanEntity = new ImSimrsParameterPemeriksaanEntity();
            String id = null;
            List<ImSimrsParameterPemeriksaanEntity> cekParameterList = new ArrayList<>();
            try {
                cekParameterList = parameterPemeriksaanDao.getHeaderParameter(bean.getNamaPemeriksaan(), bean.getIdKategoriLab());
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("Mohon maaf error saat mencari data pemeriksaan...!, "+e.getMessage());
                logger.error(e.getMessage());
            }
            if(cekParameterList.size() > 0){
                response.setStatus("error");
                response.setMsg("Mohon Maaf Data Parameter Pemeriksaan "+bean.getNamaPemeriksaan()+" sudah ada...!");
                logger.error("");
            }else{
                try {
                    id = parameterPemeriksaanDao.getNextId();
                    response.setStatus("success");
                    response.setMsg("success");
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMsg("Mohon maaf error saat mencari ID...!, "+e.getMessage());
                    logger.error(e.getMessage());
                }
                if(id != null){
                    pemeriksaanEntity.setIdParameterPemeriksaan(id);
                    pemeriksaanEntity.setIdKategoriLab(bean.getIdKategoriLab());
                    pemeriksaanEntity.setNamaPemeriksaan(bean.getNamaPemeriksaan());
                    pemeriksaanEntity.setKeteranganAcuanL(bean.getKeteranganAcuanL());
                    pemeriksaanEntity.setKeteranganAcuanP(bean.getKeteranganAcuanP());
                    pemeriksaanEntity.setSatuan(bean.getSatuan());
                    pemeriksaanEntity.setTarif(bean.getTarif());
                    pemeriksaanEntity.setFlag(bean.getFlag());
                    pemeriksaanEntity.setAction(bean.getAction());
                    pemeriksaanEntity.setCreatedDate(bean.getCreatedDate());
                    pemeriksaanEntity.setCreatedWho(bean.getCreatedWho());
                    pemeriksaanEntity.setLastUpdate(bean.getLastUpdate());
                    pemeriksaanEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        parameterPemeriksaanDao.addAndSave(pemeriksaanEntity);
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
        return response;
    }

    @Override
    public CrudResponse saveEdit(ParameterPemeriksaan bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){
            ImSimrsParameterPemeriksaanEntity pemeriksaanEntity = new ImSimrsParameterPemeriksaanEntity();
            try {
                pemeriksaanEntity = parameterPemeriksaanDao.getById("idParameterPemeriksaan", bean.getIdParameterPemeriksaan());
                response.setStatus("success");
                response.setMsg("success");
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("Mohon maaf error saat mencari ID...!, "+e.getMessage());
                logger.error(e.getMessage());
            }
            if(pemeriksaanEntity != null){
                if(bean.getIdKategoriLab() != null){
                    pemeriksaanEntity.setIdKategoriLab(bean.getIdKategoriLab());
                }
                if(bean.getNamaPemeriksaan() != null){
                    pemeriksaanEntity.setNamaPemeriksaan(bean.getNamaPemeriksaan());
                }
                if(bean.getKeteranganAcuanL() != null){
                    pemeriksaanEntity.setKeteranganAcuanL(bean.getKeteranganAcuanL());
                }
                if(bean.getKeteranganAcuanP() != null){
                    pemeriksaanEntity.setKeteranganAcuanP(bean.getKeteranganAcuanP());
                }
                if(bean.getSatuan() != null){
                    pemeriksaanEntity.setSatuan(bean.getSatuan());
                }
                if(bean.getTarif() != null){
                    pemeriksaanEntity.setTarif(bean.getTarif());
                }
                pemeriksaanEntity.setFlag(bean.getFlag());
                pemeriksaanEntity.setAction(bean.getAction());
                pemeriksaanEntity.setLastUpdate(bean.getLastUpdate());
                pemeriksaanEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    parameterPemeriksaanDao.updateAndSave(pemeriksaanEntity);
                    response.setStatus("success");
                    response.setMsg("success");
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
    public List<ParameterPemeriksaan> getByCriteria(ParameterPemeriksaan bean) throws GeneralBOException {
        List<ParameterPemeriksaan> pemeriksaanList = new ArrayList<>();
        List<ImSimrsParameterPemeriksaanEntity> entityList = new ArrayList<>();
        HashMap hsCriteria = new HashMap();
        if(bean.getIdParameterPemeriksaan() != null && !"".equalsIgnoreCase(bean.getIdParameterPemeriksaan())){
            hsCriteria.put("id_parameter_pemeriksaan", bean.getIdParameterPemeriksaan());
        }
        if(bean.getIdKategoriLab() != null && !"".equalsIgnoreCase(bean.getIdKategoriLab())){
            hsCriteria.put("id_kategori_lab", bean.getIdKategoriLab());
        }
        if(bean.getNamaPemeriksaan() != null && !"".equalsIgnoreCase(bean.getNamaPemeriksaan())){
            hsCriteria.put("nama_pemeriksaan", bean.getNamaPemeriksaan());
        }
        if(bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
            hsCriteria.put("flag", bean.getFlag());
        }
        try {
            entityList = parameterPemeriksaanDao.getByCriteria(hsCriteria);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        if(entityList.size() > 0){
            for (ImSimrsParameterPemeriksaanEntity pemeriksaan: entityList){
                ParameterPemeriksaan param = new ParameterPemeriksaan();
                param.setIdParameterPemeriksaan(pemeriksaan.getIdParameterPemeriksaan());
                param.setIdKategoriLab(pemeriksaan.getIdKategoriLab());
                param.setNamaPemeriksaan(pemeriksaan.getNamaPemeriksaan());
                param.setKeteranganAcuanL(pemeriksaan.getKeteranganAcuanL());
                param.setKeteranganAcuanP(pemeriksaan.getKeteranganAcuanP());
                param.setSatuan(pemeriksaan.getSatuan());
                param.setTarif(pemeriksaan.getTarif());
                param.setFlag(pemeriksaan.getFlag());
                param.setAction(pemeriksaan.getAction());
                param.setCreatedWho(pemeriksaan.getCreatedWho());
                param.setCreatedDate(pemeriksaan.getCreatedDate());
                param.setLastUpdateWho(pemeriksaan.getLastUpdateWho());
                param.setLastUpdate(pemeriksaan.getLastUpdate());
                ImSimrsKategoriLabEntity kategoriLabEntity = kategoriLabDao.getById("idKategoriLab", pemeriksaan.getIdKategoriLab());
                if(kategoriLabEntity != null){
                    param.setNamaKategori(kategoriLabEntity.getNamaKategori());
                }
                pemeriksaanList.add(param);
            }
        }
        return pemeriksaanList;
    }

    public void setParameterPemeriksaanDao(ParameterPemeriksaanDao parameterPemeriksaanDao) {
        this.parameterPemeriksaanDao = parameterPemeriksaanDao;
    }

    public void setKategoriLabDao(KategoriLabDao kategoriLabDao) {
        this.kategoriLabDao = kategoriLabDao;
    }

    public static Logger getLogger() {
        return logger;
    }
}