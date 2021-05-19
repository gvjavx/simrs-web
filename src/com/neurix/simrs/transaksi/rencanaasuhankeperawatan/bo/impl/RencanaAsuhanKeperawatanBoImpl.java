package com.neurix.simrs.transaksi.rencanaasuhankeperawatan.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.rencanaasuhankeperawatan.bo.RencanaAsuhanKeperawatanBo;
import com.neurix.simrs.transaksi.rencanaasuhankeperawatan.dao.RencanaAsuhanKeperawatanDao;
import com.neurix.simrs.transaksi.rencanaasuhankeperawatan.model.ItSimrsRencanaAsuhanKeperawatanEntity;
import com.neurix.simrs.transaksi.rencanaasuhankeperawatan.model.RencanaAsuhanKeperawatan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RencanaAsuhanKeperawatanBoImpl implements RencanaAsuhanKeperawatanBo {

    private static transient Logger logger = Logger.getLogger(RencanaAsuhanKeperawatanBoImpl.class);
    private RencanaAsuhanKeperawatanDao rencanaAsuhanKeperawatanDao;

    @Override
    public List<RencanaAsuhanKeperawatan> getByCriteria(RencanaAsuhanKeperawatan bean) throws GeneralBOException {
        List<RencanaAsuhanKeperawatan> list = new ArrayList<>();

        if(bean != null){
            Map hsCriteria = new HashMap();
            if(bean.getIdRencanaAsuhanKeperawatan() != null && !"".equalsIgnoreCase(bean.getIdRencanaAsuhanKeperawatan())){
                hsCriteria.put("id_rencana_asuhan_keperawatan", bean.getIdRencanaAsuhanKeperawatan());
            }
            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if(bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())){
                hsCriteria.put("keterangan", bean.getKeterangan());
            }

            List<ItSimrsRencanaAsuhanKeperawatanEntity> entityList = new ArrayList<>();
            try {
                entityList = rencanaAsuhanKeperawatanDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }

            if(entityList.size() > 0){
                for (ItSimrsRencanaAsuhanKeperawatanEntity entity: entityList){
                    RencanaAsuhanKeperawatan asuhan = new RencanaAsuhanKeperawatan();
                    asuhan.setIdRencanaAsuhanKeperawatan(entity.getIdRencanaAsuhanKeperawatan());
                    asuhan.setIdDetailCheckup(entity.getIdDetailCheckup());
                    asuhan.setWaktu(entity.getWaktu());
                    asuhan.setDiagnosa(entity.getDiagnosa());
                    asuhan.setHasil(entity.getHasil());
                    asuhan.setIntervensi(entity.getIntervensi());
                    asuhan.setImplementasi(entity.getImplementasi());
                    asuhan.setEvaluasi(entity.getEvaluasi());
                    if(entity.getTtdDokter() != null){
                        asuhan.setTtdDokter(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_RM+entity.getTtdDokter());
                    }
                    if(entity.getTtdPerawat() != null){
                        asuhan.setTtdPerawat(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_RM+entity.getTtdPerawat());
                    }
                    asuhan.setKeterangan(entity.getKeterangan());
                    asuhan.setAction(entity.getAction());
                    asuhan.setFlag(entity.getFlag());
                    asuhan.setCreatedDate(entity.getCreatedDate());
                    asuhan.setCreatedWho(entity.getCreatedWho());
                    asuhan.setLastUpdate(entity.getLastUpdate());
                    asuhan.setLastUpdateWho(entity.getLastUpdateWho());
                    asuhan.setNamaTerang(entity.getNamaTerang());
                    asuhan.setNamaDokter(entity.getNamaDokter());
                    asuhan.setSip(entity.getSip());
                    list.add(asuhan);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(RencanaAsuhanKeperawatan bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){

            ItSimrsRencanaAsuhanKeperawatanEntity asuhanKeperawatanEntity = new ItSimrsRencanaAsuhanKeperawatanEntity();
            asuhanKeperawatanEntity.setIdRencanaAsuhanKeperawatan("RAP"+rencanaAsuhanKeperawatanDao.getNextSeq());
            asuhanKeperawatanEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            asuhanKeperawatanEntity.setWaktu(bean.getWaktu());
            asuhanKeperawatanEntity.setDiagnosa(bean.getDiagnosa());
            asuhanKeperawatanEntity.setHasil(bean.getHasil());
            asuhanKeperawatanEntity.setIntervensi(bean.getIntervensi());
            asuhanKeperawatanEntity.setImplementasi(bean.getImplementasi());
            asuhanKeperawatanEntity.setEvaluasi(bean.getEvaluasi());
            asuhanKeperawatanEntity.setTtdPerawat(bean.getTtdPerawat());
            asuhanKeperawatanEntity.setKeterangan(bean.getKeterangan());
            asuhanKeperawatanEntity.setAction(bean.getAction());
            asuhanKeperawatanEntity.setFlag(bean.getFlag());
            asuhanKeperawatanEntity.setCreatedDate(bean.getCreatedDate());
            asuhanKeperawatanEntity.setCreatedWho(bean.getCreatedWho());
            asuhanKeperawatanEntity.setLastUpdate(bean.getLastUpdate());
            asuhanKeperawatanEntity.setLastUpdateWho(bean.getLastUpdateWho());
            asuhanKeperawatanEntity.setNamaTerang(bean.getNamaTerang());
            asuhanKeperawatanEntity.setNamaDokter(bean.getNamaDokter());
            asuhanKeperawatanEntity.setSip(bean.getSip());
            asuhanKeperawatanEntity.setTtdDokter(bean.getTtdDokter());

            try {
                rencanaAsuhanKeperawatanDao.addAndSave(asuhanKeperawatanEntity);
                response.setStatus("success");
                response.setMsg("Berhasil");
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("Found Error "+e.getMessage());
                logger.error(e.getMessage());
            }
        }

        return response;
    }

    @Override
    public CrudResponse saveDelete(RencanaAsuhanKeperawatan bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        ItSimrsRencanaAsuhanKeperawatanEntity entity = rencanaAsuhanKeperawatanDao.getById("idRencanaAsuhanKeperawatan", bean.getIdRencanaAsuhanKeperawatan());
        if(entity != null){
            entity.setFlag("N");
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho());
            try {
                rencanaAsuhanKeperawatanDao.updateAndSave(entity);
                response.setStatus("success");
                response.setMsg("Berhasil");
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }
        }else{
            response.setStatus("error");
            response.setMsg("Data tidak ditemukan...!");
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setRencanaAsuhanKeperawatanDao(RencanaAsuhanKeperawatanDao rencanaAsuhanKeperawatanDao) {
        this.rencanaAsuhanKeperawatanDao = rencanaAsuhanKeperawatanDao;
    }
}
