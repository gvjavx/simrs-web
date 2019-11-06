package com.neurix.hris.master.medicalBiayaKacamata.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.medicalBiayaKacamata.bo.MedicalBiayaKacamataBo;
import com.neurix.hris.master.medicalBiayaKacamata.dao.MedicalBiayaKacamataDao;
import com.neurix.hris.master.medicalBiayaKacamata.model.ImMedicalBiayaKacamataEntity;
import com.neurix.hris.master.medicalBiayaKacamata.model.MedicalBiayaKacamata;
import com.neurix.hris.master.positionBagian.dao.PositionBagianDao;
import com.neurix.hris.transaksi.smk.model.ImtHistorySmkGolonganEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class MedicalBiayaKacamataBoImpl implements MedicalBiayaKacamataBo {

    protected static transient Logger logger = Logger.getLogger(MedicalBiayaKacamataBoImpl.class);
    private MedicalBiayaKacamataDao medicalBiayaKacamataDao;
    private BiodataDao biodataDao;
    private PositionBagianDao positionBagianDao;


    public MedicalBiayaKacamataDao getMedicalBiayaKacamataDao() {
        return medicalBiayaKacamataDao;
    }

    public void setMedicalBiayaKacamataDao(MedicalBiayaKacamataDao medicalBiayaKacamataDao) {
        this.medicalBiayaKacamataDao = medicalBiayaKacamataDao;
    }

    public PositionBagianDao getPositionBagianDao() {
        return positionBagianDao;
    }

    public void setPositionBagianDao(PositionBagianDao positionBagianDao) {
        this.positionBagianDao = positionBagianDao;
    }

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MedicalBiayaKacamataBoImpl.logger = logger;
    }
    
    @Override
    public void saveDelete(MedicalBiayaKacamata bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {
            String biayaId = bean.getBiayaKacamataId();
            ImMedicalBiayaKacamataEntity imMedicalBiayaKacamataEntity = new ImMedicalBiayaKacamataEntity();

            try {
                // Get data from database by ID
                imMedicalBiayaKacamataEntity = medicalBiayaKacamataDao.getById("biayaKacamataId", biayaId);
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imMedicalBiayaKacamataEntity != null) {

                imMedicalBiayaKacamataEntity.setFlag(bean.getFlag());
                imMedicalBiayaKacamataEntity.setAction(bean.getAction());
                imMedicalBiayaKacamataEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imMedicalBiayaKacamataEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    medicalBiayaKacamataDao.updateAndSave(imMedicalBiayaKacamataEntity);
                } catch (HibernateException e) {
                    logger.error("[GolonganBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Golongan, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[GolonganBoImpl.saveDelete] Error, not found data Golongan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Golongan with request id, please check again your data ...");

            }
        }
        logger.info("[MedicalKacamataBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(MedicalBiayaKacamata bean) throws GeneralBOException {
        logger.info("[MedicalKacamataBoImpl.saveEdit] start process >>>");

        if (bean!=null) {

            String getBiayaId = bean.getBiayaKacamataId();

            ImMedicalBiayaKacamataEntity imMedicalBiayaKacamataEntity = new ImMedicalBiayaKacamataEntity();

            try {
                // Get data from database by ID
                imMedicalBiayaKacamataEntity = medicalBiayaKacamataDao.getById("biayaKacamataId", getBiayaId);
            } catch (HibernateException e) {
                logger.error("[GolonganBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Golongan by Kode Golongan, please inform to your admin...," + e.getMessage());
            }

            if (imMedicalBiayaKacamataEntity != null) {
                imMedicalBiayaKacamataEntity.setUnitId(bean.getBranchId());
                imMedicalBiayaKacamataEntity.setGolonganId(bean.getGolonganId());
                imMedicalBiayaKacamataEntity.setBiayaLensa(bean.getBiayaLensa());
                imMedicalBiayaKacamataEntity.setBiayaGagang(bean.getBiayaGagang());

                imMedicalBiayaKacamataEntity.setFlag(bean.getFlag());
                imMedicalBiayaKacamataEntity.setAction(bean.getAction());
                imMedicalBiayaKacamataEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imMedicalBiayaKacamataEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    medicalBiayaKacamataDao.updateAndSave(imMedicalBiayaKacamataEntity);
                } catch (HibernateException e) {
                    logger.error("[GolonganBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Golongan, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[GolonganBoImpl.saveEdit] Error, not found data Golongan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Golongan with request id, please check again your data ...");
//                condition = "Error, not found data Golongan with request id, please check again your data ...";
            }
        }

        logger.info("[MedicalKacamataBoImpl.saveEdit] end process <<<");
    }

    @Override
    public MedicalBiayaKacamata saveAdd(MedicalBiayaKacamata bean) throws GeneralBOException {
        logger.info("[MedicalKacamataBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String biayaId;
            try {
                // Generating ID, get from postgre sequence
                biayaId = medicalBiayaKacamataDao.getNextBiayaId();
            } catch (HibernateException e) {
                logger.error("[MedicalKacamataBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence golonganId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImMedicalBiayaKacamataEntity imGolonganEntity = new ImMedicalBiayaKacamataEntity();
            imGolonganEntity.setBiayaKacamataId(biayaId);
            imGolonganEntity.setGolonganId(bean.getGolonganId());
            imGolonganEntity.setBiayaLensa(bean.getBiayaLensa());
            imGolonganEntity.setBiayaGagang(bean.getBiayaGagang());
            imGolonganEntity.setUnitId(bean.getBranchId());

            imGolonganEntity.setFlag(bean.getFlag());
            imGolonganEntity.setAction(bean.getAction());
            imGolonganEntity.setCreatedWho(bean.getCreatedWho());
            imGolonganEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imGolonganEntity.setCreatedDate(bean.getCreatedDate());
            imGolonganEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                medicalBiayaKacamataDao.addAndSave(imGolonganEntity);
            } catch (HibernateException e) {
                logger.error("[MedicalKacamataBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Golongan, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[UpdateMedicalKacamataBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<MedicalBiayaKacamata> getByCriteria(MedicalBiayaKacamata searchBean) throws GeneralBOException {
        logger.info("[MedicalKacamataBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<MedicalBiayaKacamata> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getBiayaKacamataId() != null && !"".equalsIgnoreCase(searchBean.getBiayaKacamataId())) {
                hsCriteria.put("biaya_kacamata_id", searchBean.getBiayaKacamataId());
            }

            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }

            if (searchBean.getGolonganId() != null && !"".equalsIgnoreCase(searchBean.getGolonganId())) {
                hsCriteria.put("golongan_id", searchBean.getGolonganId());
            }

            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                hsCriteria.put("flag", searchBean.getFlag());
            }

            List<ImMedicalBiayaKacamataEntity> imGolonganEntity = null;
            try {

                imGolonganEntity = medicalBiayaKacamataDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[MedicalKacamataBoImpl.getSearchGolonganByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imGolonganEntity != null){
                MedicalBiayaKacamata returnGolongan;
                // Looping from dao to object and save in collection
                for(ImMedicalBiayaKacamataEntity biayaLoop : imGolonganEntity){
                    returnGolongan = new MedicalBiayaKacamata();

                    returnGolongan.setBiayaKacamataId(biayaLoop.getBiayaKacamataId());
                    returnGolongan.setGolonganId(biayaLoop.getGolonganId());
                    returnGolongan.setGolonganName(biayaLoop.getGolonganEntity().getGolonganName());
                    returnGolongan.setBranchId(biayaLoop.getUnitId());
                    returnGolongan.setBranchName(biayaLoop.getImBranches().getBranchName());
                    returnGolongan.setStrBiayaLensa(CommonUtil.numbericFormat(biayaLoop.getBiayaLensa(), "###,###"));
                    returnGolongan.setStrBiayaGagang(CommonUtil.numbericFormat(biayaLoop.getBiayaGagang(), "###,###"));
                    returnGolongan.setBiayaLensa(biayaLoop.getBiayaLensa());
                    returnGolongan.setBiayaGagang(biayaLoop.getBiayaGagang());


                    returnGolongan.setCreatedWho(biayaLoop.getCreatedWho());
                    returnGolongan.setCreatedDate(biayaLoop.getCreatedDate());
                    returnGolongan.setLastUpdate(biayaLoop.getLastUpdate());

                    returnGolongan.setAction(biayaLoop.getAction());
                    returnGolongan.setFlag(biayaLoop.getFlag());
                    listOfResult.add(returnGolongan);
                }
            }
        }
        logger.info("[MedicalKacamataBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public String getBiaya(String branchId, String golonganId, String tipe) throws GeneralBOException {
        List<ImMedicalBiayaKacamataEntity> biayaMedical = medicalBiayaKacamataDao.getBiayaKacamata(branchId, golonganId);
        String hasilBiaya = "0";

        if(biayaMedical.size() > 0){
            for(ImMedicalBiayaKacamataEntity biayaLoop: biayaMedical){
                if(tipe.equalsIgnoreCase("Lensa")){
                    hasilBiaya = biayaLoop.getBiayaLensa() + "";
                }else if(tipe.equalsIgnoreCase("Gagang")){
                    hasilBiaya = biayaLoop.getBiayaGagang() + "";
                }else if(tipe.equalsIgnoreCase("Set")){
                    hasilBiaya = biayaLoop.getBiayaGagang().add(biayaLoop.getBiayaLensa()) + "";
                }
            }
        }

        return hasilBiaya;
    }

    @Override
    public List<MedicalBiayaKacamata> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }


}
