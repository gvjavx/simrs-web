package com.neurix.simrs.master.rekammedis.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.rekammedis.bo.RekamMedisPasienBo;
import com.neurix.simrs.master.rekammedis.dao.RekamMedisPasienDao;
import com.neurix.simrs.master.rekammedis.model.ImSimrsRekamMedisPasienEntity;
import com.neurix.simrs.master.rekammedis.model.RekamMedisPasien;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RekamMedisPasienBoImpl implements RekamMedisPasienBo {

    private static transient Logger logger = Logger.getLogger(RekamMedisPasienBoImpl.class);
    private RekamMedisPasienDao rekamMedisPasienDao;

    @Override
    public List<RekamMedisPasien> getByCriteria(RekamMedisPasien bean) throws GeneralBOException {
        List<RekamMedisPasien> list = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdRekamMedisPasien() != null && !"".equalsIgnoreCase(bean.getIdRekamMedisPasien())) {
                hsCriteria.put("id_rekam_medis_pasien", bean.getIdRekamMedisPasien());
            }
            if (bean.getKodeRm() != null && !"".equalsIgnoreCase(bean.getKodeRm())) {
                hsCriteria.put("kode_rm", bean.getKodeRm());
            }
            if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())) {
                hsCriteria.put("keterangan", bean.getKeterangan());
            }
            if (bean.getJenis() != null && !"".equalsIgnoreCase(bean.getJenis())) {
                hsCriteria.put("jenis", bean.getJenis());
            }

            List<ImSimrsRekamMedisPasienEntity> entityList = new ArrayList<>();

            try {
                entityList = rekamMedisPasienDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }

            if (entityList.size() > 0) {
                for (ImSimrsRekamMedisPasienEntity entity : entityList) {
                    RekamMedisPasien rekamMedisPasien = new RekamMedisPasien();
                    rekamMedisPasien.setIdRekamMedisPasien(entity.getIdRekamMedisPasien());
                    rekamMedisPasien.setKodeRm(entity.getKodeRm());
                    rekamMedisPasien.setNamaRm(entity.getNamaRm());
                    rekamMedisPasien.setKeterangan(entity.getKeterangan());
                    rekamMedisPasien.setJenis(entity.getJenis());
                    rekamMedisPasien.setAction(entity.getAction());
                    rekamMedisPasien.setFlag(entity.getFlag());
                    rekamMedisPasien.setCreatedDate(entity.getCreatedDate());
                    rekamMedisPasien.setCreatedWho(entity.getCreatedWho());
                    rekamMedisPasien.setLastUpdate(entity.getLastUpdate());
                    rekamMedisPasien.setLastUpdateWho(entity.getLastUpdateWho());
                    rekamMedisPasien.setFunction(entity.getFunction());
                    rekamMedisPasien.setJumlahKategori(entity.getJumlahKategori());
                    list.add(rekamMedisPasien);
                }
            }
        }

        return list;
    }

    @Override
    public List<RekamMedisPasien> getListRekamMedisByTipePelayanan(String tipePelayanan, String jenis, String id) throws GeneralBOException {
        return rekamMedisPasienDao.getListRekamMedisByPelayanan(tipePelayanan, jenis, id);
    }

    @Override
    public List<RekamMedisPasien> getRiwayatListRekamMedis(String id, String tipePelayanan, String jenis) throws GeneralBOException {
        return rekamMedisPasienDao.getRiwayatRekamMedis(id, tipePelayanan, jenis);
    }

    @Override
    public List<RekamMedisPasien> getListRekamMedisLama(String id, String branchId) throws GeneralBOException {
        return rekamMedisPasienDao.getRiwayatRekamMedisLama(id, branchId);
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setRekamMedisPasienDao(RekamMedisPasienDao rekamMedisPasienDao) {
        this.rekamMedisPasienDao = rekamMedisPasienDao;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
