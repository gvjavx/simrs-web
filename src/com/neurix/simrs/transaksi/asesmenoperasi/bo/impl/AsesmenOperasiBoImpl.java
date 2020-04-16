package com.neurix.simrs.transaksi.asesmenoperasi.bo.impl;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.asesmenoperasi.bo.AsesmenOperasiBo;
import com.neurix.simrs.transaksi.asesmenoperasi.dao.AsesmenOperasiDao;
import com.neurix.simrs.transaksi.asesmenoperasi.model.AsesmenOperasi;
import com.neurix.simrs.transaksi.asesmenoperasi.model.ItSimrsAsesmenOperasiEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsesmenOperasiBoImpl implements AsesmenOperasiBo {
    private static transient Logger logger = Logger.getLogger(AsesmenOperasiBoImpl.class);
    private AsesmenOperasiDao asesmenOperasiDao;

    @Override
    public List<AsesmenOperasi> getByCriteria(AsesmenOperasi bean) throws GeneralBOException {
        List<AsesmenOperasi> list = new ArrayList<>();
        if(bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdAsesmenOperasi() != null && !"".equalsIgnoreCase(bean.getIdAsesmenOperasi())) {
                hsCriteria.put("id_asesmen_operasi", bean.getIdAsesmenOperasi());
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

            List<ItSimrsAsesmenOperasiEntity> entityList = new ArrayList<>();

            try {
                entityList = asesmenOperasiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }
            if(entityList.size() > 0){
                for (ItSimrsAsesmenOperasiEntity entity: entityList){
                    AsesmenOperasi operasi = new AsesmenOperasi();
                    operasi.setIdAsesmenOperasi(entity.getIdAsesmenOperasi());
                    operasi.setIdDetailCheckup(entity.getIdDetailCheckup());
                    operasi.setParameter(entity.getParameter());
                    if("penandaan_area".equalsIgnoreCase(entity.getKeterangan())){
                        if("area_penanda".equalsIgnoreCase(entity.getJenis())){
                            operasi.setJawaban1(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_AREA_OPERASI+entity.getJawaban1());
                        }
                        if("ttd_pasien".equalsIgnoreCase(entity.getJenis())){
                            operasi.setJawaban1(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_PASIEN+entity.getJawaban1());
                        }
                        if("ttd_dokter".equalsIgnoreCase(entity.getJenis())){
                            operasi.setJawaban1(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_TTD_DOKTER+entity.getJawaban1());
                        }
                    }else{
                        operasi.setJawaban1(entity.getJawaban1());
                    }
                    operasi.setJawaban2(entity.getJawaban2());
                    operasi.setKeterangan(entity.getKeterangan());
                    operasi.setJenis(entity.getJenis());
                    operasi.setSkor(entity.getSkor());
                    operasi.setAction(entity.getAction());
                    operasi.setFlag(entity.getFlag());
                    operasi.setCreatedDate(entity.getCreatedDate());
                    operasi.setCreatedWho(entity.getCreatedWho());
                    operasi.setLastUpdate(entity.getLastUpdate());
                    operasi.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(operasi);
                }
            }
        }
        return list;
    }

    @Override
    public CrudResponse saveAdd(AsesmenOperasi bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){
            ItSimrsAsesmenOperasiEntity operasi = new ItSimrsAsesmenOperasiEntity();
            operasi.setIdAsesmenOperasi("ASO"+asesmenOperasiDao.getNextSeq());
            operasi.setIdDetailCheckup(bean.getIdDetailCheckup());
            operasi.setParameter(bean.getParameter());
            operasi.setJawaban1(bean.getJawaban1());
            operasi.setJawaban2(bean.getJawaban2());
            operasi.setKeterangan(bean.getKeterangan());
            operasi.setJenis(bean.getJenis());
            operasi.setSkor(bean.getSkor());
            operasi.setAction(bean.getAction());
            operasi.setFlag(bean.getFlag());
            operasi.setCreatedDate(bean.getCreatedDate());
            operasi.setCreatedWho(bean.getCreatedWho());
            operasi.setLastUpdate(bean.getLastUpdate());
            operasi.setLastUpdateWho(bean.getLastUpdateWho());
            try {
                asesmenOperasiDao.addAndSave(operasi);
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

    public static Logger getLogger() {
        return logger;
    }

    public void setAsesmenOperasiDao(AsesmenOperasiDao asesmenOperasiDao) {
        this.asesmenOperasiDao = asesmenOperasiDao;
    }
}
