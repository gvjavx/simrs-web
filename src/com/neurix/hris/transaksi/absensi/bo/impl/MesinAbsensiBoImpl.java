package com.neurix.hris.transaksi.absensi.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.absensi.bo.MesinAbsensiBo;
import com.neurix.hris.transaksi.absensi.dao.MesinDao;
import com.neurix.hris.transaksi.absensi.model.ImMesinAbsensiEntity;
import com.neurix.hris.transaksi.absensi.model.ImMesinAbsensiHistoryEntity;
import com.neurix.hris.transaksi.absensi.model.MesinAbsensi;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.Timestamp;
import java.util.*;

public class MesinAbsensiBoImpl implements MesinAbsensiBo {
    protected static transient Logger logger = Logger.getLogger(MesinAbsensiBoImpl.class);
    private MesinDao mesinDao;
    private BranchDao branchDao;

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public MesinDao getMesinDao() {
        return mesinDao;
    }

    public void setMesinDao(MesinDao mesinDao) {
        this.mesinDao = mesinDao;
    }

    @Override
    public void saveDelete(MesinAbsensi bean) throws GeneralBOException {
        logger.info("[saveDelete.PelayananBoImpl] start process >>>");

        if (bean!=null) {
            String mesinAbsensiId = bean.getMesinId();
            String historyId = "";
            ImMesinAbsensiEntity entity = null;
            ImMesinAbsensiHistoryEntity historyEntity = new ImMesinAbsensiHistoryEntity();

            try {
                // Get data from database by ID
                entity = mesinDao.getById("mesinId", mesinAbsensiId);
                historyId = mesinDao.getNextMesinAbsensiIdHistory();
            } catch (HibernateException e) {
                logger.error("[MesinAbsensiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (entity != null) {
                historyEntity.setMesinIdHistory(historyId);
                historyEntity.setMesinId(entity.getMesinId());
                historyEntity.setMesinName(entity.getMesinName());
                historyEntity.setMesinSn(entity.getMesinSn());
                historyEntity.setFlag(entity.getFlag());
                historyEntity.setAction(entity.getAction());
                historyEntity.setCreatedDate(entity.getCreatedDate());
                historyEntity.setCreatedWho(entity.getCreatedWho());
                historyEntity.setLastUpdate(entity.getLastUpdate());
                historyEntity.setLastUpdateWho(entity.getLastUpdateWho());

                // Modify from bean to entity serializable
                entity.setMesinId(bean.getMesinId());
                entity.setFlag(bean.getFlag());
                entity.setAction(bean.getAction());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    mesinDao.updateAndSave(entity);
                    mesinDao.addAndSaveHistory(historyEntity);
                } catch (HibernateException e) {
                    logger.error("[MesinAbsensiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data MesinAbsensi, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[MesinAbsensiBoImpl.saveDelete] Error, not found data PayrollSkalaGaji with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollSkalaGaji with request id, please check again your data ...");
            }
        }
        logger.info("[MesinAbsensiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(MesinAbsensi bean) throws GeneralBOException {
        logger.info("[MesinAbsensiBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String mesinAbsensiId = bean.getMesinId();
            String historyId = "";

            ImMesinAbsensiEntity imMesinAbsensiEntity = null;
            ImMesinAbsensiHistoryEntity historyEntity = new ImMesinAbsensiHistoryEntity();

            try {
                // Get data from database by ID
                imMesinAbsensiEntity = mesinDao.getById("mesinId", mesinAbsensiId);
                historyId = mesinDao.getNextMesinAbsensiIdHistory();
            } catch (HibernateException e) {
                logger.error("[MesinAbsensiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data MesinAbsensi by ID MesinAbsensi, please inform to your admin...," + e.getMessage());
            }

            if (imMesinAbsensiEntity != null) {
                historyEntity.setMesinIdHistory(historyId);
                historyEntity.setMesinId(imMesinAbsensiEntity.getMesinId());
                historyEntity.setMesinName(imMesinAbsensiEntity.getMesinName());
                historyEntity.setMesinSn(imMesinAbsensiEntity.getMesinSn());
                historyEntity.setFlag(imMesinAbsensiEntity.getFlag());
                historyEntity.setAction(imMesinAbsensiEntity.getAction());
                historyEntity.setCreatedDate(imMesinAbsensiEntity.getCreatedDate());
                historyEntity.setCreatedWho(imMesinAbsensiEntity.getCreatedWho());
                historyEntity.setLastUpdate(imMesinAbsensiEntity.getLastUpdate());
                historyEntity.setLastUpdateWho(imMesinAbsensiEntity.getLastUpdateWho());

                //
                imMesinAbsensiEntity.setMesinId(bean.getMesinId());
                imMesinAbsensiEntity.setMesinName(bean.getMesinAddress());
                imMesinAbsensiEntity.setMesinSn(bean.getMesinSn());
                imMesinAbsensiEntity.setBranchId(bean.getBranchId());
                imMesinAbsensiEntity.setFlag(bean.getFlag());
                imMesinAbsensiEntity.setAction(bean.getAction());
                imMesinAbsensiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imMesinAbsensiEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    mesinDao.updateAndSave(imMesinAbsensiEntity);
                    mesinDao.addAndSaveHistory(historyEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[MesinAbsensiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Mesin Absensi, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[MesinAbsensiBoImpl.saveEdit] Error, not found data MesinAbsensi with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data MesinAbsensi with request id, please check again your data ...");
//                condition = "Error, not found data GolonganPkwt with request id, please check again your data ...";
            }
        }
        logger.info("[MesinAbsensiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public MesinAbsensi saveAdd(MesinAbsensi bean) throws GeneralBOException {
        logger.info("[MesinAbsensiBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String mesinExist = "";
            try{
                mesinExist = mesinDao.cekAvailMesin(bean.getMesinAddress(), bean.getMesinSn());
            }catch (HibernateException e){
                logger.error("[MesinAbsensiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when check Availability Mesin Absensi, please info to your admin..." + e.getMessage());
            }

            if(!"exist".equalsIgnoreCase(mesinExist)) {
                String mesinAbsensiId;
                try {
                    // Generating ID, get from postgre sequence
                    mesinAbsensiId = mesinDao.getNextMesinAbsensiId();
                } catch (HibernateException e) {
                    logger.error("[MesinAbsensiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence MesinAbsensi id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImMesinAbsensiEntity entity = new ImMesinAbsensiEntity();

                entity.setMesinId(mesinAbsensiId);
                entity.setMesinName(bean.getMesinAddress());
                entity.setMesinSn(bean.getMesinSn());
                entity.setBranchId(bean.getBranchId());
                entity.setFlag(bean.getFlag());
                entity.setAction(bean.getAction());
                entity.setCreatedWho(bean.getCreatedWho());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setCreatedDate(bean.getCreatedDate());
                entity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    mesinDao.addAndSave(entity);
                } catch (HibernateException e) {
                    logger.error("[MesinAbsensiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data MesinAbsensi, please info to your admin..." + e.getMessage());
                }
            }else{
                logger.error("[MesinAbsensiBoImpl.saveAdd] Data Mesin Absensi dengan Address / SN tersebut telah tersedia.");
                throw new GeneralBOException("Mesin Absensi dengan data Address / SN tersebut telah tersedia.");
            }

        }

        logger.info("[MesinAbsensiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<MesinAbsensi> getByCriteria(MesinAbsensi bean) throws GeneralBOException {
        logger.info("[PelayananBoImpl.getByCriteria] Start >>>>>>");
        List<MesinAbsensi> result = new ArrayList<>();

        if(bean != null){

            Map hsCriteria = new HashMap();

            if (bean.getMesinId() != null && !"".equalsIgnoreCase(bean.getMesinId())){
                hsCriteria.put("mesin_id", bean.getMesinId());
            }
            if (bean.getMesinAddress() != null && !"".equalsIgnoreCase(bean.getMesinAddress())){
                hsCriteria.put("mesin_address", bean.getMesinAddress());
            }
            if (bean.getMesinSn() != null && !"".equalsIgnoreCase(bean.getMesinSn())){
                hsCriteria.put("mesin_sn", bean.getMesinSn());
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
                hsCriteria.put("branch_id", bean.getBranchId());
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

            List<ImMesinAbsensiEntity> entityList = new ArrayList<>();

            try {
                entityList = mesinDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[MesinAbsesnsiBoImpl.getByCriteria] Error get pelayanan data "+e.getMessage());
            }

            if (!entityList.isEmpty()){
                MesinAbsensi mesinAbsensi;
                for (ImMesinAbsensiEntity entity : entityList){
                    mesinAbsensi = new MesinAbsensi();
                    mesinAbsensi.setMesinId(entity.getMesinId());
                    mesinAbsensi.setMesinAddress(entity.getMesinName());
                    mesinAbsensi.setMesinSn(entity.getMesinSn());
                    mesinAbsensi.setBranchId(entity.getBranchId());
                    if (entity.getBranchId()!=null){
                        List<ImBranches> branchesList = branchDao.getListBranchById(entity.getBranchId());
                        for (ImBranches branches : branchesList){
                            mesinAbsensi.setBranchName(branches.getBranchName());
                        }
                    }
                    mesinAbsensi.setAction(entity.getAction());
                    mesinAbsensi.setFlag(entity.getFlag());
                    mesinAbsensi.setCreatedDate(entity.getCreatedDate());
                    mesinAbsensi.setStCreatedDate(entity.getCreatedDate().toString());
                    mesinAbsensi.setCreatedWho(entity.getCreatedWho());
                    mesinAbsensi.setStLastUpdate(entity.getLastUpdate().toString());
                    mesinAbsensi.setLastUpdate(entity.getLastUpdate());
                    mesinAbsensi.setLastUpdateWho(entity.getLastUpdateWho());

                    result.add(mesinAbsensi);
                }
            }
        }

        logger.info("[MesinAbsensiBoImpl.getByCriteria] End <<<<<<");
        return result;
    }

    @Override
    public List<MesinAbsensi> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}