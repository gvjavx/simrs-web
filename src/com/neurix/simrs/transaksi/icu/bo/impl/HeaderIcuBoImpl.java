package com.neurix.simrs.transaksi.icu.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.icu.bo.HeaderIcuBo;
import com.neurix.simrs.transaksi.icu.dao.DetailIcuDao;
import com.neurix.simrs.transaksi.icu.dao.HeaderIcuDao;
import com.neurix.simrs.transaksi.icu.model.HeaderIcu;
import com.neurix.simrs.transaksi.icu.model.ItSimrsDetailIcuEntity;
import com.neurix.simrs.transaksi.icu.model.ItSimrsHeaderIcuEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeaderIcuBoImpl implements HeaderIcuBo {
    private static transient Logger logger = Logger.getLogger(HeaderIcuBoImpl.class);
    private HeaderIcuDao headerIcuDao;
    private DetailIcuDao detailIcuDao;

    @Override
    public List<HeaderIcu> getByCriteria(HeaderIcu bean) throws GeneralBOException {
        List<HeaderIcu> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdHeaderIcu() != null && !"".equalsIgnoreCase(bean.getIdHeaderIcu())) {
                hsCriteria.put("id_header_id", bean.getIdHeaderIcu());
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getKategori() != null && !"".equalsIgnoreCase(bean.getKategori())) {
                hsCriteria.put("kategori", bean.getKategori());
            }
            if (bean.getJenis() != null && !"".equalsIgnoreCase(bean.getJenis())) {
                hsCriteria.put("jenis", bean.getJenis());
            }

            List<ItSimrsHeaderIcuEntity> entityList = new ArrayList<>();

            try {
                entityList = headerIcuDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsHeaderIcuEntity entity : entityList) {
                    HeaderIcu headerIcu = new HeaderIcu();
                    headerIcu.setIdHeaderIcu(entity.getIdHeaderIcu());
                    headerIcu.setIdDetailCheckup(entity.getIdDetailCheckup());
                    headerIcu.setJenis(entity.getJenis());
                    headerIcu.setKategori(entity.getKategori());
                    headerIcu.setAction(entity.getAction());
                    headerIcu.setFlag(entity.getFlag());
                    headerIcu.setCreatedDate(entity.getCreatedDate());
                    headerIcu.setCreatedWho(entity.getCreatedWho());
                    headerIcu.setLastUpdate(entity.getLastUpdate());
                    headerIcu.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(headerIcu);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(List<HeaderIcu> list, Boolean isNew) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (list.size() > 0) {
            for (HeaderIcu bean : list) {

                ItSimrsHeaderIcuEntity headerIcuEntity = new ItSimrsHeaderIcuEntity();
                ItSimrsDetailIcuEntity detailIcuEntity = new ItSimrsDetailIcuEntity();
                String idHeaderIcu = bean.getIdHeaderIcu();

                if (isNew) {

                    headerIcuEntity.setIdHeaderIcu("HIC" + headerIcuDao.getNextSeq());
                    headerIcuEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                    headerIcuEntity.setJenis(bean.getJenis());
                    headerIcuEntity.setKategori(bean.getKategori());
                    headerIcuEntity.setAction(bean.getAction());
                    headerIcuEntity.setFlag(bean.getFlag());
                    headerIcuEntity.setCreatedDate(bean.getCreatedDate());
                    headerIcuEntity.setCreatedWho(bean.getCreatedWho());
                    headerIcuEntity.setLastUpdate(bean.getLastUpdate());
                    headerIcuEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        headerIcuDao.addAndSave(headerIcuEntity);
                        response.setStatus("success");
                        response.setMsg("Berhasil");
                        idHeaderIcu = headerIcuEntity.getIdHeaderIcu();
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg("Found Error " + e.getMessage());
                        logger.error(e.getMessage());
                    }
                }

                detailIcuEntity.setIdDetailIcu("IDI" + detailIcuDao.getNextSeq());
                detailIcuEntity.setIdHeaderIcu(idHeaderIcu);
                detailIcuEntity.setNilai(bean.getNilai());
                detailIcuEntity.setWaktu(bean.getWaktu());
                detailIcuEntity.setAction(bean.getAction());
                detailIcuEntity.setFlag(bean.getFlag());
                detailIcuEntity.setCreatedDate(bean.getCreatedDate());
                detailIcuEntity.setCreatedWho(bean.getCreatedWho());
                detailIcuEntity.setLastUpdate(bean.getLastUpdate());
                detailIcuEntity.setLastUpdateWho(bean.getLastUpdateWho());
                detailIcuEntity.setIdDetailChekcup(bean.getIdDetailCheckup());

                try {
                    detailIcuDao.addAndSave(detailIcuEntity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Found Error " + e.getMessage());
                    logger.error(e.getMessage());
                }
            }
        }
        return response;
    }

    @Override
    public List<HeaderIcu> getListDetail(String id, String kategori) throws GeneralBOException {
        return headerIcuDao.getListDetail(id, kategori);
    }

    @Override
    public Boolean cekData(String id, String waktu, String kategori) throws GeneralBOException {
        return headerIcuDao.cekData(id, waktu, kategori);
    }

    @Override
    public CrudResponse saveDelete(HeaderIcu bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        ItSimrsDetailIcuEntity detailIcuEntity = detailIcuDao.getById("idDetailIcu", bean.getIdDetailIcu());
        if (detailIcuEntity != null) {
            List<ItSimrsDetailIcuEntity> detailIcuEntityList = new ArrayList<>();
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_detail_checkup", detailIcuEntity.getIdDetailChekcup());
            hsCriteria.put("created_date", detailIcuEntity.getCreatedDate());
            try {
                detailIcuEntityList = detailIcuDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("Found Error, " + e.getMessage());
            }

            if (detailIcuEntityList.size() > 0) {
                for (ItSimrsDetailIcuEntity entity : detailIcuEntityList) {
                    entity.setFlag("N");
                    entity.setAction("D");
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    try {
                        detailIcuDao.updateAndSave(entity);
                        response.setStatus("success");
                        response.setMsg("Berhasil");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg("Found Error, " + e.getMessage());
                    }
                }
            } else {
                response.setStatus("error");
                response.setMsg("Data tidak ditemukan...!");
            }
        } else {
            response.setStatus("error");
            response.setMsg("Data tidak ditemukan...!");
        }
        return response;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setHeaderIcuDao(HeaderIcuDao headerIcuDao) {
        this.headerIcuDao = headerIcuDao;
    }

    public void setDetailIcuDao(DetailIcuDao detailIcuDao) {
        this.detailIcuDao = detailIcuDao;
    }
}
