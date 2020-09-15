package com.neurix.simrs.transaksi.pengkajiankeperawatan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.pengkajiankeperawatan.bo.PengkajianUlangKeperawatanBo;
import com.neurix.simrs.transaksi.pengkajiankeperawatan.dao.PengkajianUlangKeperawatanDao;
import com.neurix.simrs.transaksi.pengkajiankeperawatan.model.ItSimrsPengkajianUlangKeperawatanEntity;
import com.neurix.simrs.transaksi.pengkajiankeperawatan.model.PengkajianUlangKeperawatan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PengkajianUlangKeperawatanBoImpl implements PengkajianUlangKeperawatanBo {
    private static transient Logger logger = Logger.getLogger(PengkajianUlangKeperawatanBoImpl.class);
    private PengkajianUlangKeperawatanDao pengkajianUlangKeperawatanDao;

    @Override
    public List<PengkajianUlangKeperawatan> getByCriteria(PengkajianUlangKeperawatan bean) throws GeneralBOException {
        List<PengkajianUlangKeperawatan> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdPengkajianUlangKeperawatan() != null && !"".equalsIgnoreCase(bean.getIdPengkajianUlangKeperawatan())) {
                hsCriteria.put("id_asesmen_keperawatan_rawat_inap", bean.getIdPengkajianUlangKeperawatan());
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
            if (bean.getTanggal() != null && !"".equalsIgnoreCase(bean.getTanggal().toString())) {
                hsCriteria.put("tanggal", bean.getTanggal());
            }

            List<ItSimrsPengkajianUlangKeperawatanEntity> entityList = new ArrayList<>();

            try {
                entityList = pengkajianUlangKeperawatanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ItSimrsPengkajianUlangKeperawatanEntity entity : entityList) {
                    PengkajianUlangKeperawatan keperawatan = new PengkajianUlangKeperawatan();
                    keperawatan.setIdPengkajianUlangKeperawatan(entity.getIdPengkajianUlangKeperawatan());
                    keperawatan.setIdDetailCheckup(entity.getIdDetailCheckup());
                    keperawatan.setParameter(entity.getParameter());
                    keperawatan.setPagi(entity.getPagi());
                    keperawatan.setSiang(entity.getSiang());
                    keperawatan.setMalam(entity.getMalam());
                    keperawatan.setKodeParameter(entity.getKodeParameter());
                    keperawatan.setKeterangan(entity.getKeterangan());
                    keperawatan.setJenis(entity.getJenis());
                    keperawatan.setAction(entity.getAction());
                    keperawatan.setFlag(entity.getFlag());
                    keperawatan.setCreatedDate(entity.getCreatedDate());
                    keperawatan.setCreatedWho(entity.getCreatedWho());
                    keperawatan.setLastUpdate(entity.getLastUpdate());
                    keperawatan.setLastUpdateWho(entity.getLastUpdateWho());
                    list.add(keperawatan);
                }
            }
        }

        return list;
    }

    @Override
    public CrudResponse saveAdd(PengkajianUlangKeperawatan bean, List<PengkajianUlangKeperawatan> keperawatanList) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (bean != null) {

            Boolean cekInsertData = false;
            Boolean cekExistData = false;

            try {
                cekInsertData = cekPengkajianUlangKeperawatan(bean, "insert");
            } catch (GeneralBOException e) {
                response.setStatus("error");
                response.setMsg("Found Error " + e.getMessage());
                logger.error("Errof");
            }

            try {
                cekExistData = cekPengkajianUlangKeperawatan(bean, "update");
            } catch (GeneralBOException e) {
                response.setStatus("error");
                response.setMsg("Found Error " + e.getMessage());
                logger.error("Errof");
            }

            for (PengkajianUlangKeperawatan ulangKeperawatan : keperawatanList) {
                if (cekInsertData) {
                    if (cekExistData) {
                        response.setStatus("error");
                        response.setMsg("Data pada waktu " + bean.getWaktu() + " sudah ada");
                        return response;
                    } else {
                        response = saveEdit(ulangKeperawatan);
                    }
                } else {
                    ItSimrsPengkajianUlangKeperawatanEntity keperawatanEntity = new ItSimrsPengkajianUlangKeperawatanEntity();
                    keperawatanEntity.setIdPengkajianUlangKeperawatan("PUK" + pengkajianUlangKeperawatanDao.getNextSeq());
                    keperawatanEntity.setIdDetailCheckup(ulangKeperawatan.getIdDetailCheckup());
                    keperawatanEntity.setTanggal(ulangKeperawatan.getTanggal());
                    keperawatanEntity.setParameter(ulangKeperawatan.getParameter());
                    keperawatanEntity.setPagi(ulangKeperawatan.getPagi());
                    keperawatanEntity.setSiang(ulangKeperawatan.getSiang());
                    keperawatanEntity.setMalam(ulangKeperawatan.getMalam());
                    keperawatanEntity.setKodeParameter(ulangKeperawatan.getKodeParameter());
                    keperawatanEntity.setKeterangan(ulangKeperawatan.getKeterangan());
                    keperawatanEntity.setJenis(ulangKeperawatan.getJenis());
                    keperawatanEntity.setAction(ulangKeperawatan.getAction());
                    keperawatanEntity.setFlag(ulangKeperawatan.getFlag());
                    keperawatanEntity.setCreatedDate(ulangKeperawatan.getCreatedDate());
                    keperawatanEntity.setCreatedWho(ulangKeperawatan.getCreatedWho());
                    keperawatanEntity.setLastUpdate(ulangKeperawatan.getLastUpdate());
                    keperawatanEntity.setLastUpdateWho(ulangKeperawatan.getLastUpdateWho());

                    try {
                        pengkajianUlangKeperawatanDao.addAndSave(keperawatanEntity);
                        response.setStatus("success");
                        response.setMsg("Berhasil");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg("Found Error " + e.getMessage());
                        logger.error(e.getMessage());
                    }
                }
            }
        }
        return response;
    }

    @Override
    public CrudResponse saveEdit(PengkajianUlangKeperawatan bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (bean != null) {

            ItSimrsPengkajianUlangKeperawatanEntity keperawatanEntity = getEntity(bean);
            if (keperawatanEntity.getIdPengkajianUlangKeperawatan() != null) {

                if ("pagi".equalsIgnoreCase(bean.getWaktu())) {
                    keperawatanEntity.setPagi(bean.getPagi());
                }
                if ("siang".equalsIgnoreCase(bean.getWaktu())) {
                    keperawatanEntity.setSiang(bean.getSiang());
                }
                if ("malam".equalsIgnoreCase(bean.getWaktu())) {
                    keperawatanEntity.setMalam(bean.getMalam());
                }

                keperawatanEntity.setLastUpdate(bean.getLastUpdate());
                keperawatanEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    pengkajianUlangKeperawatanDao.updateAndSave(keperawatanEntity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Found Error " + e.getMessage());
                    logger.error(e.getMessage());
                }
            } else {
                response.setStatus("error");
                response.setMsg("Found Error ID tidak ditemukan....!");
            }
        }
        return response;
    }

    @Override
    public CrudResponse saveDelete(PengkajianUlangKeperawatan bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        ItSimrsPengkajianUlangKeperawatanEntity entity = pengkajianUlangKeperawatanDao.getById("idPengkajianUlangKeperawatan", bean.getIdPengkajianUlangKeperawatan());
        if (entity != null) {
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_detail_checkup", entity.getIdDetailCheckup());
            hsCriteria.put("keterangan", entity.getKeterangan());
            hsCriteria.put("jenis", entity.getJenis());
            hsCriteria.put("tanggal", entity.getTanggal());
            List<ItSimrsPengkajianUlangKeperawatanEntity> entityList = new ArrayList<>();
            try {
                entityList = pengkajianUlangKeperawatanDao.getByCriteria(hsCriteria);
                response.setStatus("success");
                response.setMsg("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("Data tidak ditemukan...!");
                logger.error(e.getMessage());
            }
            if (entityList.size() > 0) {
                for (ItSimrsPengkajianUlangKeperawatanEntity keperawatanEntity : entityList) {
                    if ("pagi".equalsIgnoreCase(bean.getWaktu())) {
                        keperawatanEntity.setPagi(null);
                    }
                    if ("siang".equalsIgnoreCase(bean.getWaktu())) {
                        keperawatanEntity.setSiang(null);
                    }
                    if ("malam".equalsIgnoreCase(bean.getWaktu())) {
                        keperawatanEntity.setMalam(null);
                    }
                    keperawatanEntity.setLastUpdate(bean.getLastUpdate());
                    keperawatanEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        pengkajianUlangKeperawatanDao.updateAndSave(keperawatanEntity);
                        response.setStatus("success");
                        response.setMsg("Berhasil");
                    }catch (HibernateException e){
                        response.setStatus("error");
                        response.setMsg(e.getMessage());
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

    @Override
    public Boolean cekPengkajianUlangKeperawatan(PengkajianUlangKeperawatan bean, String tipe) throws GeneralBOException {
        Boolean response = false;
        try {
            response = pengkajianUlangKeperawatanDao.cekPengkajianUlangkeperawatan(bean, tipe);
        } catch (HibernateException e) {
            logger.error("Found Error " + e.getMessage());
        }
        return response;
    }

    private ItSimrsPengkajianUlangKeperawatanEntity getEntity(PengkajianUlangKeperawatan bean) {
        ItSimrsPengkajianUlangKeperawatanEntity keperawatanEntity = new ItSimrsPengkajianUlangKeperawatanEntity();
        Map hsCriteria = new HashMap();

        if (bean != null) {
            if (bean.getIdPengkajianUlangKeperawatan() != null && !"".equalsIgnoreCase(bean.getIdPengkajianUlangKeperawatan())) {
                hsCriteria.put("id_asesmen_keperawatan_rawat_inap", bean.getIdPengkajianUlangKeperawatan());
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
            if (bean.getKodeParameter() != null && !"".equalsIgnoreCase(bean.getKodeParameter())) {
                hsCriteria.put("kode_parameter", bean.getKodeParameter());
            }
            if (bean.getTanggal() != null && !"".equalsIgnoreCase(bean.getTanggal().toString())) {
                hsCriteria.put("tanggal", bean.getTanggal());
            }

            List<ItSimrsPengkajianUlangKeperawatanEntity> entityList = new ArrayList<>();

            try {
                entityList = pengkajianUlangKeperawatanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                keperawatanEntity = entityList.get(0);
            }
        }

        return keperawatanEntity;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setPengkajianUlangKeperawatanDao(PengkajianUlangKeperawatanDao pengkajianUlangKeperawatanDao) {
        this.pengkajianUlangKeperawatanDao = pengkajianUlangKeperawatanDao;
    }
}
