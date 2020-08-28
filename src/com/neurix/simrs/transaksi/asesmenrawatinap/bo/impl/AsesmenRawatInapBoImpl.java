package com.neurix.simrs.transaksi.asesmenrawatinap.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenrawatinap.bo.AsesmenRawatInapBo;
import com.neurix.simrs.transaksi.asesmenrawatinap.dao.AsesmenRawatInapDao;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.AsesmenRawatInap;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.ItSimrsAsesmenRawatInapEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsesmenRawatInapBoImpl implements AsesmenRawatInapBo {
    private static transient Logger logger = Logger.getLogger(AsesmenRawatInapBoImpl.class);
    private AsesmenRawatInapDao asesmenRawatInapDao;

    @Override
    public List<AsesmenRawatInap> getByCriteria(AsesmenRawatInap bean) throws GeneralBOException {
        List<AsesmenRawatInap> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdAsesmenKeperawatanRawatInap() != null && !"".equalsIgnoreCase(bean.getIdAsesmenKeperawatanRawatInap())) {
                hsCriteria.put("id_asesmen_keperawatan_rawat_inap", bean.getIdAsesmenKeperawatanRawatInap());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())) {
                hsCriteria.put("keterangan", bean.getKeterangan());
            }
            if (bean.getJenis() != null && !"".equalsIgnoreCase(bean.getJenis())) {
                hsCriteria.put("jenis", bean.getJenis());
            }

            List<ItSimrsAsesmenRawatInapEntity> entityList = new ArrayList<>();

            try {
                entityList = asesmenRawatInapDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsAsesmenRawatInapEntity entity : entityList) {
                    AsesmenRawatInap asesmenRawatInap = new AsesmenRawatInap();
                    asesmenRawatInap.setIdAsesmenKeperawatanRawatInap(entity.getIdAsesmenKeperawatanRawatInap());
                    asesmenRawatInap.setIdDetailCheckup(entity.getIdDetailCheckup());
                    asesmenRawatInap.setParameter(entity.getParameter());
                    if ("ttd".equalsIgnoreCase(entity.getTipe())) {
                        asesmenRawatInap.setJawaban(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_TTD_RM + entity.getJawaban());
                    } else if ("gambar".equalsIgnoreCase(entity.getTipe())) {
                        asesmenRawatInap.setJawaban(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_IMG_RM + entity.getJawaban());
                    } else {
                        asesmenRawatInap.setJawaban(entity.getJawaban());
                    }
                    asesmenRawatInap.setKeterangan(entity.getKeterangan());
                    asesmenRawatInap.setJenis(entity.getJenis());
                    asesmenRawatInap.setSkor(entity.getSkor());
                    asesmenRawatInap.setAction(entity.getAction());
                    asesmenRawatInap.setFlag(entity.getFlag());
                    asesmenRawatInap.setCreatedDate(entity.getCreatedDate());
                    asesmenRawatInap.setCreatedWho(entity.getCreatedWho());
                    asesmenRawatInap.setLastUpdate(entity.getLastUpdate());
                    asesmenRawatInap.setLastUpdateWho(entity.getLastUpdateWho());
                    asesmenRawatInap.setTipe(entity.getTipe());
                    asesmenRawatInap.setInformasi(entity.getInformasi());
                    asesmenRawatInap.setNamaTerang(entity.getNamaTerang());
                    asesmenRawatInap.setSip(entity.getSip());
                    list.add(asesmenRawatInap);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(List<AsesmenRawatInap> list) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (list.size() > 0) {
            AsesmenRawatInap inap = list.get(0);
            AsesmenRawatInap rawatInap = new AsesmenRawatInap();
            rawatInap.setIdDetailCheckup(inap.getIdDetailCheckup());
            rawatInap.setKeterangan(inap.getKeterangan());
            List<AsesmenRawatInap> rawatInapList = getByCriteria(rawatInap);

            if("hand_over".equalsIgnoreCase(inap.getJenis())){
                AsesmenRawatInap rw = new AsesmenRawatInap();
                rw.setIdDetailCheckup(inap.getIdDetailCheckup());
                rw.setJawaban(inap.getJawaban());
                rw.setJenis(inap.getJenis());
                Boolean cekData = asesmenRawatInapDao.cekHandOver(rw);
                if(cekData){
                    response.setStatus("error");
                    response.setMsg("Found error, Data pada shift "+inap.getJawaban()+" sudah ada..!");
                }else{
                    response = saveAsesmen(list);
                }
            }else{
                if(rawatInapList.size() > 0){
                    response.setStatus("error");
                    response.setMsg("Found error, Data yang anda masukan sudah ada...!");
                }else{
                    response = saveAsesmen(list);
                }
            }
        }
        return response;
    }

    private CrudResponse saveAsesmen(List<AsesmenRawatInap> list){
        CrudResponse response = new CrudResponse();
        for (AsesmenRawatInap bean : list) {
            ItSimrsAsesmenRawatInapEntity asesmenRawatInapEntity = new ItSimrsAsesmenRawatInapEntity();
            asesmenRawatInapEntity.setIdAsesmenKeperawatanRawatInap("ARI" + asesmenRawatInapDao.getNextSeq());
            asesmenRawatInapEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
            asesmenRawatInapEntity.setParameter(bean.getParameter());
            asesmenRawatInapEntity.setJawaban(bean.getJawaban());
            asesmenRawatInapEntity.setKeterangan(bean.getKeterangan());
            asesmenRawatInapEntity.setJenis(bean.getJenis());
            asesmenRawatInapEntity.setSkor(bean.getSkor());
            asesmenRawatInapEntity.setAction(bean.getAction());
            asesmenRawatInapEntity.setFlag(bean.getFlag());
            asesmenRawatInapEntity.setCreatedDate(bean.getCreatedDate());
            asesmenRawatInapEntity.setCreatedWho(bean.getCreatedWho());
            asesmenRawatInapEntity.setLastUpdate(bean.getLastUpdate());
            asesmenRawatInapEntity.setLastUpdateWho(bean.getLastUpdateWho());
            asesmenRawatInapEntity.setTipe(bean.getTipe());
            asesmenRawatInapEntity.setInformasi(bean.getInformasi());
            asesmenRawatInapEntity.setNamaTerang(bean.getNamaTerang());
            asesmenRawatInapEntity.setSip(bean.getSip());

            try {
                asesmenRawatInapDao.addAndSave(asesmenRawatInapEntity);
                response.setStatus("success");
                response.setMsg("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("Found Error " + e.getMessage());
                logger.error(e.getMessage());
                return response;
            }
        }
        return response;
    }

    @Override
    public CrudResponse saveDelete(AsesmenRawatInap bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        List<ItSimrsAsesmenRawatInapEntity> entityList = new ArrayList<>();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        hsCriteria.put("keterangan", bean.getKeterangan());
        try {
            entityList = asesmenRawatInapDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            response.setStatus("error");
            response.setMsg("Found Error, "+e.getMessage());
            logger.error(e.getMessage());
            return response;
        }

        if(entityList.size() > 0){
            for (ItSimrsAsesmenRawatInapEntity inap: entityList){
                inap.setLastUpdate(bean.getLastUpdate());
                inap.setLastUpdateWho(bean.getLastUpdateWho());
                inap.setFlag("N");
                try {
                    asesmenRawatInapDao.updateAndSave(inap);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMsg("Found Error, "+e.getMessage());
                    logger.error(e.getMessage());
                    return response;
                }
            }
        }else{
            response.setStatus("error");
            response.setMsg("Data yang anda delete tidak ditemukan");
        }

        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setAsesmenRawatInapDao(AsesmenRawatInapDao asesmenRawatInapDao) {
        this.asesmenRawatInapDao = asesmenRawatInapDao;
    }
}
