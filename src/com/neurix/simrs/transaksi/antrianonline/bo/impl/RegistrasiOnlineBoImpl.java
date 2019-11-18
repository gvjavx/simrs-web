package com.neurix.simrs.transaksi.antrianonline.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.antrianonline.bo.RegistrasiOnlineBo;
import com.neurix.simrs.transaksi.antrianonline.dao.RegistrasiOnlineDao;
import com.neurix.simrs.transaksi.antrianonline.model.ItSimrsRegistrasiOnlineEntity;
import com.neurix.simrs.transaksi.antrianonline.model.RegistrasiOnline;
import com.neurix.simrs.transaksi.checkup.bo.impl.CheckupBoImpl;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gondok
 * Monday, 18/11/19 10:36
 */
public class RegistrasiOnlineBoImpl implements RegistrasiOnlineBo {
    protected static transient Logger logger = Logger.getLogger(CheckupBoImpl.class);
    private RegistrasiOnlineDao registrasiOnlineDao;

    public void setRegistrasiOnlineDao(RegistrasiOnlineDao registrasiOnlineDao) {
        this.registrasiOnlineDao = registrasiOnlineDao;
    }

    @Override
    public List<RegistrasiOnline> getByCriteria(RegistrasiOnline bean) throws GeneralBOException {
        logger.info("[RegistrasiOnlineBoImpl.getByCriteria] Start >>>>>>>");

        List<RegistrasiOnline> listOfResult = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getNoCheckupOnline() != null && !"".equalsIgnoreCase(bean.getNoCheckupOnline())) {
                hsCriteria.put("no_checkup_online", bean.getNoCheckupOnline());
            }

            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                hsCriteria.put("id_pasien", bean.getIdPasien());
            }

            if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama())) {
                hsCriteria.put("nama", bean.getNama());
            }

            if (bean.getJenisKelamin() != null && !"".equalsIgnoreCase(bean.getJenisKelamin())) {
                hsCriteria.put("jenis_kelamin", bean.getJenisKelamin());
            }

            if (bean.getNoKtp() != null && !"".equalsIgnoreCase(bean.getNoKtp())) {
                hsCriteria.put("no_ktp", bean.getNoKtp());
            }

            if (bean.getTempatLahir() != null && !"".equalsIgnoreCase(bean.getTempatLahir())) {
                hsCriteria.put("tempat_lahir", bean.getTempatLahir());
            }

            if (bean.getTglLahir() != null && !"".equalsIgnoreCase(bean.getTglLahir().toString())) {
                hsCriteria.put("tgl_lahir", bean.getTglLahir());
            }

            if (bean.getDesaId() != null && !"".equalsIgnoreCase(bean.getDesaId().toString())) {
                hsCriteria.put("desa_id", bean.getDesaId());
            }

            if (bean.getJalan() != null && !"".equalsIgnoreCase(bean.getJalan())) {
                hsCriteria.put("jalan", bean.getJalan());
            }

            if (bean.getSuku() != null && !"".equalsIgnoreCase(bean.getSuku())) {
                hsCriteria.put("suku", bean.getSuku());
            }

            if (bean.getAgama() != null && !"".equalsIgnoreCase(bean.getAgama())) {
                hsCriteria.put("agama", bean.getAgama());
            }

            if (bean.getProfesi() != null && !"".equalsIgnoreCase(bean.getProfesi())) {
                hsCriteria.put("profesi", bean.getProfesi());
            }

            if (bean.getNoTelp() != null && !"".equalsIgnoreCase(bean.getNoTelp())) {
                hsCriteria.put("no_telp", bean.getNoTelp());
            }

            if (bean.getIdJenisPeriksaPasien() != null && !"".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                hsCriteria.put("id_jenis_periksa_pasien", bean.getIdJenisPeriksaPasien());
            }

            if (bean.getBranchId() != null) {
                hsCriteria.put("branch_id", bean.getBranchId());
            }

            List<ItSimrsRegistrasiOnlineEntity> listOfRegistrasi = new ArrayList<>();

            try {
                listOfRegistrasi = registrasiOnlineDao.getByCriteria(hsCriteria);
            } catch (GeneralBOException e) {
                logger.error("[RegistrasiOnlineBoImpl.getByCriteria] error when get data by criteria on getListRegistrasiOnlineByCriteria "+ e.getMessage());
            }

            if (listOfRegistrasi.size() != 0) {
                for (ItSimrsRegistrasiOnlineEntity item : listOfRegistrasi) {
                    RegistrasiOnline registrasiItem = new RegistrasiOnline();
                    registrasiItem.setNoCheckupOnline(item.getNoCheckupOnline());
                    registrasiItem.setIdJenisPeriksaPasien(item.getIdJenisPeriksaPasien());
                    registrasiItem.setIdPasien(item.getIdPasien());
                    registrasiItem.setNoKtp(item.getNoKtp());
                    registrasiItem.setNama(item.getNama());
                    registrasiItem.setAgama(item.getAgama());
                    registrasiItem.setSuku(item.getSuku());
                    registrasiItem.setNoTelp(item.getNoTelp());
                    registrasiItem.setJalan(item.getJalan());
                    registrasiItem.setJenisKelamin(item.getJenisKelamin());
                    registrasiItem.setDesaId(item.getDesaId());
                    registrasiItem.setProfesi(item.getProfesi());
                    registrasiItem.setTempatLahir(item.getTempatLahir());
                    registrasiItem.setTglLahir(item.getTglLahir());
                    registrasiItem.setUrlKtp(item.getUrlKtp());
                    registrasiItem.setAction(item.getAction());
                    registrasiItem.setFlag(item.getFlag());
                    registrasiItem.setCreatedDate(item.getCreatedDate());
                    registrasiItem.setCreatedWho(item.getCreatedWho());
                    registrasiItem.setLastUpdate(item.getLastUpdate());
                    registrasiItem.setLastUpdateWho(item.getLastUpdateWho());
                    registrasiItem.setBranchId(item.getBranchId());

                    listOfResult.add(registrasiItem);
                }
            }

        }

        logger.info("[RegistrasiOnlineBoImpl.getByCriteria] End <<<<<<<");
        return listOfResult;
    }

    @Override
    public void saveAdd(RegistrasiOnline bean) throws GeneralBOException {
        if (bean != null) {

            String id = "";
            id = getNextCheckupOnlineId();

            ItSimrsRegistrasiOnlineEntity registrasiOnlineEntity = new ItSimrsRegistrasiOnlineEntity();
            registrasiOnlineEntity.setNoCheckupOnline("CKO" + id);
            registrasiOnlineEntity.setNama(bean.getNama());
            registrasiOnlineEntity.setJenisKelamin(bean.getJenisKelamin());
            registrasiOnlineEntity.setProfesi(bean.getProfesi());
            registrasiOnlineEntity.setNoTelp(bean.getNoTelp());
            registrasiOnlineEntity.setSuku(bean.getSuku());
            registrasiOnlineEntity.setTempatLahir(bean.getTempatLahir());
            registrasiOnlineEntity.setTglLahir(bean.getTglLahir());
            registrasiOnlineEntity.setIdPasien(bean.getIdPasien());
            registrasiOnlineEntity.setIdJenisPeriksaPasien(bean.getIdJenisPeriksaPasien());
            registrasiOnlineEntity.setBranchId(bean.getBranchId());
            registrasiOnlineEntity.setJalan(bean.getJalan());
            registrasiOnlineEntity.setDesaId(bean.getDesaId());
            registrasiOnlineEntity.setNoKtp(bean.getNoKtp());
            registrasiOnlineEntity.setUrlKtp(bean.getUrlKtp());
            registrasiOnlineEntity.setAgama(bean.getAgama());
            registrasiOnlineEntity.setAction("C");
            registrasiOnlineEntity.setFlag("Y");
            registrasiOnlineEntity.setCreatedDate(bean.getCreatedDate());
            registrasiOnlineEntity.setCreatedWho(bean.getCreatedWho());
            registrasiOnlineEntity.setLastUpdate(bean.getLastUpdate());
            registrasiOnlineEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try{
                registrasiOnlineDao.addAndSave(registrasiOnlineEntity);
            } catch (HibernateException e) {
                logger.error("[RegistrasiOnlineBoImpl.saveAdd] Error When Saving data registrasi online" + e.getMessage());
                throw new GeneralBOException("[RegistrasiOnlineBoImpl.saveAdd] Error When Saving data registrasi online");
            }
        }
    }

    private String getNextCheckupOnlineId() {
        String id = "";
        try {
            id = registrasiOnlineDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[AntrianOnlineBoImpl.getNextAntrianId] Error get next seq id "+e.getMessage());
            throw new GeneralBOException("[AntrianOnlineBoImpl.getNextAntrianId] Error When Error get next seq id");
        }

        return id;
    }
}
