package com.neurix.simrs.master.kategoripersediaan.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dietgizi.bo.impl.DietGiziBoImpl;


import com.neurix.simrs.master.kategoripersediaan.bo.KategoriPersediaanBo;
import com.neurix.simrs.master.kategoripersediaan.dao.KategoriPersedianDao;
import com.neurix.simrs.master.kategoripersediaan.model.ImSimrsKategoriPersediaanEntity;
import com.neurix.simrs.master.kategoripersediaan.model.KategoriPersediaan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 30/09/20.
 */
public class KategoriPersediaanBoImpl implements KategoriPersediaanBo {
    private static transient Logger logger = Logger.getLogger(DietGiziBoImpl.class);
    private KategoriPersedianDao kategoriPersedianDao;
   private KodeRekeningDao kodeRekeningDao;

    public KodeRekeningDao getKodeRekeningDao() {
        return kodeRekeningDao;
    }

    public void setKodeRekeningDao(KodeRekeningDao kodeRekeningDao) {
        this.kodeRekeningDao = kodeRekeningDao;
    }

    public KategoriPersedianDao getKategoriPersedianDao() {
        return kategoriPersedianDao;
    }

    public void setKategoriPersedianDao(KategoriPersedianDao kategoriPersedianDao) {
        this.kategoriPersedianDao = kategoriPersedianDao;
    }

    @Override
    public List<KategoriPersediaan> getByCriteria(KategoriPersediaan bean) throws GeneralBOException {
        logger.info("[KategoriPersediaanBoImpl.getByCriteria] Start >>>>>>>>");
        List<KategoriPersediaan> listOfResultKategoriPersediaan = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdKategoriPersediaan() != null && !"".equalsIgnoreCase(bean.getIdKategoriPersediaan())) {
                hsCriteria.put("id_kategori_persediaan", bean.getIdKategoriPersediaan());
            }
            if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama())) {
                hsCriteria.put("nama", bean.getNama());
            }
            if (bean.getRekeningId() != null && !"".equalsIgnoreCase(bean.getRekeningId())) {
                hsCriteria.put("rekening_id", bean.getRekeningId());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                if ("N".equalsIgnoreCase(bean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", bean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImSimrsKategoriPersediaanEntity> imSimrsKategoriPersediaan = null;
            try {
                imSimrsKategoriPersediaan = kategoriPersedianDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[KategoriPersediaantBoImpl.getByCriteria] error when get data KategoriPersediaan by get by criteria "+ e.getMessage());
            }

            if (imSimrsKategoriPersediaan.size() > 0) {
                for (ImSimrsKategoriPersediaanEntity KategoriPersediaan : imSimrsKategoriPersediaan){
                    KategoriPersediaan kategoriPersediaan = new KategoriPersediaan();

                    kategoriPersediaan.setIdKategoriPersediaan(KategoriPersediaan.getIdKategoriPersediaan());
                    kategoriPersediaan.setNama(KategoriPersediaan.getNama());
                    kategoriPersediaan.setRekeningId(KategoriPersediaan.getRekeningId());

                    ImKodeRekeningEntity kodeRekeningEntity = kodeRekeningDao.getById("rekeningId", KategoriPersediaan.getRekeningId());
                    if(kodeRekeningEntity != null){
                        kategoriPersediaan.setNamaRekening(kodeRekeningEntity.getNamaKodeRekening());
                    }

                    kategoriPersediaan.setAction(KategoriPersediaan.getAction());
                    kategoriPersediaan.setFlag(KategoriPersediaan.getFlag());
                    kategoriPersediaan.setCreatedDate(KategoriPersediaan.getCreatedDate());
                    kategoriPersediaan.setCreatedWho(KategoriPersediaan.getCreatedWho());
                    kategoriPersediaan.setLastUpdate(KategoriPersediaan.getLastUpdate());
                    kategoriPersediaan.setLastUpdateWho(KategoriPersediaan.getLastUpdateWho());
                    listOfResultKategoriPersediaan.add(kategoriPersediaan);
                }
            }
        }
        logger.info("[DietGiziBoImpl.getByCriteria] End <<<<<<<<");
        return listOfResultKategoriPersediaan;
    }

    @Override
    public void saveAdd(KategoriPersediaan bean) throws GeneralBOException {
        if (bean!=null) {
            List<ImSimrsKategoriPersediaanEntity> cekList = new ArrayList<>();
            try {
                cekList = kategoriPersedianDao.getKategoriPersediaan(bean.getNama());
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }if (cekList.size()> 0){
                throw new GeneralBOException("nama Kategori Persediaan tidak boleh sama");
            }else {

                String kategoriid;
                try {
                    // Generating ID, get from postgre sequence
                    kategoriid = kategoriPersedianDao.getNextId();
                } catch (HibernateException e) {
                    logger.error("[Kategori Persediaan DaoBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence Kategori Persediaan id, please info to your admin..." + e.getMessage());
                }
                // creating object entity serializable
                ImSimrsKategoriPersediaanEntity imSimrsKategoriPersediaan = new ImSimrsKategoriPersediaanEntity();

                imSimrsKategoriPersediaan.setIdKategoriPersediaan(kategoriid);
                imSimrsKategoriPersediaan.setNama(bean.getNama());
                imSimrsKategoriPersediaan.setRekeningId(bean.getRekeningId());

                imSimrsKategoriPersediaan.setFlag(bean.getFlag());
                imSimrsKategoriPersediaan.setAction(bean.getAction());
                imSimrsKategoriPersediaan.setCreatedWho(bean.getCreatedWho());
                imSimrsKategoriPersediaan.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsKategoriPersediaan.setCreatedDate(bean.getCreatedDate());
                imSimrsKategoriPersediaan.setLastUpdate(bean.getLastUpdate());
                try {
                    // insert into database
                    kategoriPersedianDao.addAndSave(imSimrsKategoriPersediaan);
                } catch (HibernateException e) {
                    logger.error("[KategoriPersediaanBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Kategori Persediaan, please info to your admin..." + e.getMessage());
                }
            }
        }
    }

    @Override
    public void saveEdit(KategoriPersediaan bean) throws GeneralBOException {
        logger.info("[DietGiziBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String idKategoriPersediaan = bean.getIdKategoriPersediaan();
            ImSimrsKategoriPersediaanEntity imSimrsKategoriPersediaan= null;
            try {
                // Get data from database by ID
                imSimrsKategoriPersediaan = kategoriPersedianDao.getById("idKategoriPersediaan", idKategoriPersediaan);

            } catch (HibernateException e) {
                logger.error("[DietGiziBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data DietGizi , please inform to your admin...," + e.getMessage());
            }
            if (imSimrsKategoriPersediaan != null) {
//                imSimrsJenisDietEntity.setIdJenisDiet(idJenisDiet);
                imSimrsKategoriPersediaan.setNama(bean.getNama());
                imSimrsKategoriPersediaan.setRekeningId(bean.getRekeningId());

                imSimrsKategoriPersediaan.setFlag(bean.getFlag());
                imSimrsKategoriPersediaan.setAction(bean.getAction());
                imSimrsKategoriPersediaan.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsKategoriPersediaan.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    kategoriPersedianDao.updateAndSave(imSimrsKategoriPersediaan);
                } catch (HibernateException e) {
                    logger.error("KategoriPersediaanBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update KategoriPersediaan, please info to your admin..." + e.getMessage());
                }
            }
        } else {
            logger.error("[KategoriPersediaanBoImpl.saveEdit] Error, not found data KategoriPersediaan with request id, please check again your data ...");
            throw new GeneralBOException("Error, not found data KategoriPersediaan with request id, please check again your data ...");
        }
    }

    @Override
    public void saveDelete(KategoriPersediaan bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean != null) {
            String idKategoriPersediaan = bean.getIdKategoriPersediaan();

            ImSimrsKategoriPersediaanEntity imSimrsKategoriPersediaanEntity = null;

            try {
                // Get data from database by ID
                imSimrsKategoriPersediaanEntity = kategoriPersedianDao.getById("idKategoriPersediaan", idKategoriPersediaan);
            } catch (HibernateException e) {
                logger.error("[KategoriPersediaanBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by KategoriPersediaan, please inform to your admin...," + e.getMessage());
            }

            if (imSimrsKategoriPersediaanEntity != null) {
                imSimrsKategoriPersediaanEntity.setIdKategoriPersediaan(bean.getIdKategoriPersediaan());

                imSimrsKategoriPersediaanEntity.setFlag(bean.getFlag());
                imSimrsKategoriPersediaanEntity.setAction(bean.getAction());
                imSimrsKategoriPersediaanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsKategoriPersediaanEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    kategoriPersedianDao.updateAndSave(imSimrsKategoriPersediaanEntity);
                } catch (HibernateException e) {
                    logger.error("[KategoriPersediaanBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data KategoriPersediaan, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[KategoriPersediaanBoImpl.saveDelete] Error, not found data KategoriPersediaan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data KategoriPersediaan with request id, please check again your data ...");
            }
        }
        logger.info("[KategoriPersediaanBoImpl.saveDelete] end process <<<");
    }
}
