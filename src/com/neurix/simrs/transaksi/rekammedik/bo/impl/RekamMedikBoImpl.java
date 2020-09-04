package com.neurix.simrs.transaksi.rekammedik.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.rekammedis.dao.RekamMedisPasienDao;
import com.neurix.simrs.master.rekammedis.model.ImSimrsRekamMedisPasienEntity;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.rekammedik.bo.RekamMedikBo;
import com.neurix.simrs.transaksi.rekammedik.dao.StatusPengisianRekamMedisDao;
import com.neurix.simrs.transaksi.rekammedik.model.ItSimrsStatusPengisianRekamMedisEntity;
import com.neurix.simrs.transaksi.rekammedik.model.StatusPengisianRekamMedis;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RekamMedikBoImpl implements RekamMedikBo {

    protected static transient Logger logger = Logger.getLogger(RekamMedikBoImpl.class);
    private CheckupDetailDao checkupDetailDao;
    private StatusPengisianRekamMedisDao statusPengisianRekamMedisDao;
    private RekamMedisPasienDao rekamMedisPasienDao;

    @Override
    public List<HeaderDetailCheckup> getListPasien(HeaderDetailCheckup bean) throws GeneralBOException {
        List<HeaderDetailCheckup> list = new ArrayList<>();
        try {
            list = checkupDetailDao.getListPasienRekamMedik(bean);
        } catch (HibernateException e) {
            logger.error("Found Error " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<HeaderDetailCheckup> getDetailListRekamMedis(String idPasien) throws GeneralBOException {
        List<HeaderDetailCheckup> list = new ArrayList<>();
        try {
            list = checkupDetailDao.getDetailListRekamMedis(idPasien);
        } catch (HibernateException e) {
            logger.error("Found Error " + e.getMessage());
        }
        return list;
    }

    @Override
    public CrudResponse saveAdd(StatusPengisianRekamMedis bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup()) &&
            bean.getIdRekamMedisPasien() != null && !"".equalsIgnoreCase(bean.getIdRekamMedisPasien())) {

            List<ItSimrsStatusPengisianRekamMedisEntity> entityList = new ArrayList<>();
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_rekam_medis_pasien", bean.getIdRekamMedisPasien());
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            try {
                entityList = statusPengisianRekamMedisDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("Found Error when search ID RM, " + e.getMessage());
            }

            ItSimrsStatusPengisianRekamMedisEntity entity = new ItSimrsStatusPengisianRekamMedisEntity();

            if (entityList.size() > 0) {
                entity = entityList.get(0);
                if (entity != null) {
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    int jmlKategori = 0;
                    int jumlah = 0;
                    int penambah = 1;
                    if (entity.getJumlahKategori() != null && !"".equalsIgnoreCase(entity.getJumlahKategori())) {
                        jmlKategori = Integer.valueOf(entity.getJumlahKategori());
                    }
                    jumlah = jmlKategori + penambah;
                    if (jmlKategori == jumlah) {
                        entity.setIsPengisian("Y");
                    }
                    entity.setJumlahKategori(String.valueOf(jumlah));

                    try {
                        statusPengisianRekamMedisDao.updateAndSave(entity);
                        response.setStatus("success");
                        response.setMsg("Berhasil");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg("Error When save status pengisian " + e.getMessage());
                    }
                }
            } else {
                List<ImSimrsRekamMedisPasienEntity> entityArrayList = new ArrayList<>();
                ImSimrsRekamMedisPasienEntity pasienEntity = new ImSimrsRekamMedisPasienEntity();
                Map hasCriteria = new HashMap();
                hasCriteria.put("id_rekam_medis_pasien", bean.getIdRekamMedisPasien());
                hasCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
                try {
                    entityArrayList = rekamMedisPasienDao.getByCriteria(hasCriteria);
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Found Error when search ID RM, " + e.getMessage());
                }
                if (entityArrayList.size() > 0) {
                    pasienEntity = entityArrayList.get(0);
                }
                entity.setIdStatusPengisianRekamMedis("SRM" + statusPengisianRekamMedisDao.getNextIdSeq());
                entity.setNoCheckup(bean.getNoCheckup());
                entity.setIdDetailCheckup(bean.getIdDetailCheckup());
                entity.setIdPasien(bean.getIdPasien());
                entity.setIdRekamMedisPasien(bean.getIdRekamMedisPasien());
                if ("1".equalsIgnoreCase(pasienEntity.getJumlahKategori())) {
                    entity.setIsPengisian("Y");
                }
                entity.setJumlahKategori("1");
                entity.setAction(bean.getAction());
                entity.setFlag(bean.getFlag());
                entity.setCreatedDate(bean.getCreatedDate());
                entity.setCreatedWho(bean.getCreatedWho());
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    statusPengisianRekamMedisDao.addAndSave(entity);
                    response.setStatus("success");
                    response.setMsg("Berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Error When save status pengisian " + e.getMessage());
                }
            }
        } else {
            response.setMsg("ID detail Checkup tidak ada...!");
            response.setStatus("error");
        }
        return response;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setStatusPengisianRekamMedisDao(StatusPengisianRekamMedisDao statusPengisianRekamMedisDao) {
        this.statusPengisianRekamMedisDao = statusPengisianRekamMedisDao;
    }

    public void setRekamMedisPasienDao(RekamMedisPasienDao rekamMedisPasienDao) {
        this.rekamMedisPasienDao = rekamMedisPasienDao;
    }

    public static Logger getLogger() {
        return logger;
    }
}
