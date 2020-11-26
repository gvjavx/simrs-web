package com.neurix.hris.master.shift.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.company.model.ImBranchesPK;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.kelompokPosition.dao.KelompokPositionDao;
import com.neurix.hris.master.kelompokPosition.model.ImKelompokPositionEntity;
import com.neurix.hris.master.positionBagian.dao.PositionBagianDao;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianEntity;
import com.neurix.hris.master.profesi.dao.ProfesiDao;
import com.neurix.hris.master.profesi.model.ImProfesiEntity;
import com.neurix.hris.master.shift.bo.ShiftBo;
import com.neurix.hris.master.shift.dao.ShiftDao;
import com.neurix.hris.master.shift.model.ImHrisShiftEntity;
import com.neurix.hris.master.shift.model.ImHrisShiftHistory;
import com.neurix.hris.master.shift.model.Shift;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class ShiftBoImpl implements ShiftBo {
    protected static transient Logger logger = Logger.getLogger(ShiftBoImpl.class);

    private ShiftDao shiftDao;
    private BranchDao branchDao;
    private KelompokPositionDao kelompokPositionDao;
    private ProfesiDao profesiDao;
    private PositionBagianDao positionBagianDao;

    public PositionBagianDao getPositionBagianDao() {
        return positionBagianDao;
    }

    public void setPositionBagianDao(PositionBagianDao positionBagianDao) {
        this.positionBagianDao = positionBagianDao;
    }

    public ProfesiDao getProfesiDao() {
        return profesiDao;
    }

    public void setProfesiDao(ProfesiDao profesiDao) {
        this.profesiDao = profesiDao;
    }

    public KelompokPositionDao getKelompokPositionDao() {
        return kelompokPositionDao;
    }

    public void setKelompokPositionDao(KelompokPositionDao kelompokPositionDao) {
        this.kelompokPositionDao = kelompokPositionDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ShiftBoImpl.logger = logger;
    }

    public ShiftDao getShiftDao() {
        return shiftDao;
    }

    public void setShiftDao(ShiftDao shiftDao) {
        this.shiftDao = shiftDao;
    }

    @Override
    public void saveDelete(Shift bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(Shift bean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.saveEdit] start process >>>");
        boolean saved = false;
        if (bean!=null) {
            ImHrisShiftEntity entityData = new ImHrisShiftEntity();

            entityData.setShiftId(bean.getShiftId());
            entityData.setShiftName(bean.getShiftName());
            entityData.setJamAwal(bean.getJamAwal());
            entityData.setJamAkhir(bean.getJamAkhir());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());
            entityData.setIdBranch(bean.getIdBranch());
            entityData.setProfesiId(bean.getProfesiId());

            try {
                shiftDao.updateAndSave(entityData);
                saved = true;
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }

        if (saved){
            String id;

            ImHrisShiftEntity shiftEntity = null;
            ImHrisShiftHistory entityData = new ImHrisShiftHistory();
            try {
                // Get data from database by ID
                shiftEntity = shiftDao.getById("shiftId", bean.getShiftId());
                //historyId = payrollSkalaGajiDao.getNextSkalaGaji();
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollSkalaGaji by Kode PayrollSkalaGaji, please inform to your admin...," + e.getMessage());
            }

            if (shiftEntity != null){
                try {
                    // Generating ID, get from postgre sequence
                    id = shiftDao.getNextShiftHistoryId();
                } catch (HibernateException e) {
                    logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
                }

                entityData.setShiftId(shiftEntity.getShiftId());
                entityData.setShiftName(shiftEntity.getShiftName());
                if ("Y".equalsIgnoreCase(shiftEntity.getFlag())){
//                    entityData.setJamAwal(shiftEntity.getJamAwal()+"."+bean.getJamAwalMenit());
//                    entityData.setJamAkhir(shiftEntity.getJamAkhirJam()+"."+bean.getJamAkhirMenit());
                    entityData.setJamAwal(shiftEntity.getJamAwal());
                    entityData.setJamAkhir(shiftEntity.getJamAkhir());
                } else {
                    entityData.setJamAwal(bean.getJamAwal());
                    entityData.setJamAkhir(bean.getJamAkhir());
                }
                entityData.setFlag("Y");
                entityData.setAction(shiftEntity.getAction());
                entityData.setCreateDateWho(shiftEntity.getCreateDateWho());
                entityData.setLastUpdateWho(shiftEntity.getLastUpdateWho());
                entityData.setCreatedDate(shiftEntity.getCreatedDate());
                entityData.setLastUpdate(shiftEntity.getLastUpdate());
                entityData.setId(id);
                entityData.setIdBranch(shiftEntity.getIdBranch());
                entityData.setProfesiId(shiftEntity.getProfesiId());
                try {
                    shiftDao.addAndSaveHistory(entityData);
                } catch (HibernateException e) {
                    logger.error("[ShiftBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                }
            }
        }

        logger.info("[ShiftBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Shift saveAdd(Shift bean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String shiftId;
            try {
                // Generating ID, get from postgre sequence
                shiftId = shiftDao.getNextShiftId();
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            ImHrisShiftEntity entityData = new ImHrisShiftEntity();

            entityData.setShiftId("SF"+shiftId);
            entityData.setShiftName(bean.getShiftName());
            entityData.setJamAwal(bean.getJamAwal());
            entityData.setJamAkhir(bean.getJamAkhir());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());
            entityData.setIdBranch(bean.getIdBranch());
            entityData.setProfesiId(bean.getProfesiId());

            try {
                shiftDao.addAndSave(entityData);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[ShiftBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Shift> getByCriteria(Shift searchBean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Shift> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getShiftId() != null && !"".equalsIgnoreCase(searchBean.getShiftId())) {
                hsCriteria.put("shift_id", searchBean.getShiftId());
            }
            if (searchBean.getProfesiId() != null && !"".equalsIgnoreCase(searchBean.getProfesiId())) {
                hsCriteria.put("profesi_id", searchBean.getProfesiId());
            }
            if (searchBean.getShiftName() != null && !"".equalsIgnoreCase(searchBean.getShiftName())) {
                hsCriteria.put("shift_name", searchBean.getShiftName());
            }
            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                if ("N".equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchBean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }


            List<ImHrisShiftEntity> imHrisShiftList = null;
            try {

                imHrisShiftList = shiftDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imHrisShiftList != null){
                Shift returnData;
                // Looping from dao to object and save in collection
                for(ImHrisShiftEntity listEntity : imHrisShiftList){
                    returnData = new Shift();
                    returnData.setShiftId(listEntity.getShiftId());
                    returnData.setShiftName(listEntity.getShiftName()+" | "+listEntity.getJamAwal()+" s/d "+listEntity.getJamAkhir());
                    returnData.setJamAwal(listEntity.getJamAwal());
                    returnData.setJamAkhir(listEntity.getJamAkhir());
                    returnData.setCreatedDate(listEntity.getCreatedDate());
                    returnData.setCreatedWho(listEntity.getCreateDateWho());
                    returnData.setLastUpdate(listEntity.getLastUpdate());
                    returnData.setLastUpdateWho(listEntity.getLastUpdateWho());
                    returnData.setFlag(listEntity.getFlag());
                    returnData.setAction(listEntity.getAction());
                    returnData.setIdBranch(listEntity.getIdBranch());
                    returnData.setProfesiId(listEntity.getProfesiId());

                    if (listEntity.getIdBranch()!=null){
                        ImBranches imBranches = null;
                        ImBranchesPK primaryKey = new ImBranchesPK();
                        primaryKey.setId(listEntity.getIdBranch());

                        List<ImBranches> listBranch = new ArrayList<>();
                        listBranch = branchDao.getListBranchById(listEntity.getIdBranch());
                        if (listBranch.size()>0){
                            for (ImBranches branchLoop: listBranch){
                                returnData.setBranchName(branchLoop.getBranchName());
                            }
                        }
//                        imBranches = branchDao.getListBranchById(listEntity.getIdBranch());
                    }else{
                        returnData.setBranchName("");
                    }

                    if (listEntity.getProfesiId()!=null && !"".equalsIgnoreCase(listEntity.getProfesiId())){

                        ImPositionBagianEntity positionBagianEntity = positionBagianDao.getById("bagianId",listEntity.getProfesiId());
                        returnData.setProfesiName(positionBagianEntity.getBagianName());
                    }else{
                        returnData.setProfesiName("");
                    }

                    listOfResult.add(returnData);
                }
            }
        }
        logger.info("[ShiftBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Shift> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
