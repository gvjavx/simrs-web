package com.neurix.hris.master.payrollTunjanganJabatanStruktural.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.payrollTunjanganJabatanStruktural.bo.PayrollTunjanganJabatanStrukturalBo;
import com.neurix.hris.master.payrollTunjanganJabatanStruktural.dao.PayrollTunjanganJabatanStrukturalDao;
import com.neurix.hris.master.payrollTunjanganJabatanStruktural.model.ImmPayrollTunjanganJabatanStrukturalEntity;
//import com.neurix.hris.master.payrollTunjanganJabatanStruktural.model.ImPayrollTunjanganJabatanStrukturalHistoryEntity;
import com.neurix.hris.master.payrollTunjanganJabatanStruktural.model.PayrollTunjanganJabatanStruktural;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

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
public class PayrollTunjanganJabatanStrukturalBoImpl implements PayrollTunjanganJabatanStrukturalBo {

    protected static transient Logger logger = Logger.getLogger(PayrollTunjanganJabatanStrukturalBoImpl.class);
    private PayrollTunjanganJabatanStrukturalDao payrollTunjanganJabatanStrukturalDao;
    private PositionDao positionDao;
    private BranchDao branchDao;

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollTunjanganJabatanStrukturalBoImpl.logger = logger;
    }

    public PayrollTunjanganJabatanStrukturalDao getPayrollTunjanganJabatanStrukturalDao() {
        return payrollTunjanganJabatanStrukturalDao;
    }


    public void setPayrollTunjanganJabatanStrukturalDao(PayrollTunjanganJabatanStrukturalDao payrollTunjanganJabatanStrukturalDao) {
        this.payrollTunjanganJabatanStrukturalDao = payrollTunjanganJabatanStrukturalDao;
    }

    @Override
    public void saveDelete(PayrollTunjanganJabatanStruktural bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollTunjanganJabatanStruktural = bean.getTunjJabatanStrukturalId();

            ImmPayrollTunjanganJabatanStrukturalEntity imPayrollTunjanganJabatanStrukturalEntity = null;

            try {
                // Get data from database by ID
                imPayrollTunjanganJabatanStrukturalEntity = payrollTunjanganJabatanStrukturalDao.getById("tunjJabatanStrukturalId", payrollTunjanganJabatanStruktural);
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollTunjanganJabatanStrukturalEntity != null) {

                // Modify from bean to entity serializable
                imPayrollTunjanganJabatanStrukturalEntity.setTunjJabatanStrukturalId(bean.getTunjJabatanStrukturalId());
                imPayrollTunjanganJabatanStrukturalEntity.setFlag(bean.getFlag());
                imPayrollTunjanganJabatanStrukturalEntity.setAction(bean.getAction());
                imPayrollTunjanganJabatanStrukturalEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollTunjanganJabatanStrukturalEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollTunjanganJabatanStrukturalDao.updateAndSave(imPayrollTunjanganJabatanStrukturalEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollTunjanganJabatanStruktural, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveDelete] Error, not found data PayrollTunjanganJabatanStruktural with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollTunjanganJabatanStruktural with request id, please check again your data ...");

            }
        }
        logger.info("[PayrollTunjanganJabatanStrukturalBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(PayrollTunjanganJabatanStruktural bean) throws GeneralBOException {
        logger.info("[PayrollTunjanganJabatanStrukturalBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String historyId = "";
            String payrollTunjanganJabatanStruktural = bean.getTunjJabatanStrukturalId();

            ImmPayrollTunjanganJabatanStrukturalEntity imPayrollTunjanganJabatanStrukturalEntity = null;
//            ImPayrollTunjanganJabatanStrukturalHistoryEntity imPayrollTunjanganJabatanStrukturalHistoryEntity = new ImPayrollTunjanganJabatanStrukturalHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollTunjanganJabatanStrukturalEntity = payrollTunjanganJabatanStrukturalDao.getById("tunjJabatanStrukturalId", payrollTunjanganJabatanStruktural);
                //historyId = payrollTunjanganJabatanStrukturalDao.getNextSkalaGaji();
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollTunjanganJabatanStruktural by Kode PayrollTunjanganJabatanStruktural, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollTunjanganJabatanStrukturalEntity != null) {
                /*imPayrollTunjanganJabatanStrukturalHistoryEntity.setId(historyId);
                imPayrollTunjanganJabatanStrukturalHistoryEntity.setPayrollTunjanganJabatanStruktural(imPayrollTunjanganJabatanStrukturalEntity.getPayrollTunjanganJabatanStruktural());
                imPayrollTunjanganJabatanStrukturalHistoryEntity.setPayrollTunjanganJabatanStrukturalName(imPayrollTunjanganJabatanStrukturalEntity.getPayrollTunjanganJabatanStrukturalName());
                imPayrollTunjanganJabatanStrukturalHistoryEntity.setFlag(imPayrollTunjanganJabatanStrukturalEntity.getFlag());
                imPayrollTunjanganJabatanStrukturalHistoryEntity.setAction(imPayrollTunjanganJabatanStrukturalEntity.getAction());
                imPayrollTunjanganJabatanStrukturalHistoryEntity.setLastUpdateWho(imPayrollTunjanganJabatanStrukturalEntity.getLastUpdateWho());
                imPayrollTunjanganJabatanStrukturalHistoryEntity.setLastUpdate(imPayrollTunjanganJabatanStrukturalEntity.getLastUpdate());
                imPayrollTunjanganJabatanStrukturalHistoryEntity.setCreatedWho(imPayrollTunjanganJabatanStrukturalEntity.getLastUpdateWho());
                imPayrollTunjanganJabatanStrukturalHistoryEntity.setCreatedDate(imPayrollTunjanganJabatanStrukturalEntity.getLastUpdate());*/

                imPayrollTunjanganJabatanStrukturalEntity.setTunjJabatanStrukturalId(bean.getTunjJabatanStrukturalId());
                imPayrollTunjanganJabatanStrukturalEntity.setPositionId(bean.getPositionId());
                imPayrollTunjanganJabatanStrukturalEntity.setNilai(bean.getNilai());
                imPayrollTunjanganJabatanStrukturalEntity.setBranchId(bean.getBranchId());
                
                imPayrollTunjanganJabatanStrukturalEntity.setFlag(bean.getFlag());
                imPayrollTunjanganJabatanStrukturalEntity.setAction(bean.getAction());
                imPayrollTunjanganJabatanStrukturalEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollTunjanganJabatanStrukturalEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollTunjanganJabatanStrukturalDao.updateAndSave(imPayrollTunjanganJabatanStrukturalEntity);
                    //payrollTunjanganJabatanStrukturalDao.addAndSaveHistory(imPayrollTunjanganJabatanStrukturalHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollTunjanganJabatanStruktural, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveEdit] Error, not found data PayrollTunjanganJabatanStruktural with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollTunjanganJabatanStruktural with request id, please check again your data ...");
            }
        }
        logger.info("[PayrollTunjanganJabatanStrukturalBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PayrollTunjanganJabatanStruktural saveAdd(PayrollTunjanganJabatanStruktural bean) throws GeneralBOException {
        logger.info("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String payrollTunjanganJabatanStruktural;
            try {
                // Generating ID, get from postgre sequence
                payrollTunjanganJabatanStruktural = payrollTunjanganJabatanStrukturalDao.getNextTunjanganJabatanStruktural();
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence payrollTunjanganJabatanStruktural id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImmPayrollTunjanganJabatanStrukturalEntity imPayrollTunjanganJabatanStrukturalEntity = new ImmPayrollTunjanganJabatanStrukturalEntity();

            imPayrollTunjanganJabatanStrukturalEntity.setTunjJabatanStrukturalId(payrollTunjanganJabatanStruktural);
            imPayrollTunjanganJabatanStrukturalEntity.setPositionId(bean.getPositionId());
            imPayrollTunjanganJabatanStrukturalEntity.setNilai(bean.getNilai());
            imPayrollTunjanganJabatanStrukturalEntity.setBranchId(bean.getBranchId());
            imPayrollTunjanganJabatanStrukturalEntity.setFlag(bean.getFlag());
            imPayrollTunjanganJabatanStrukturalEntity.setAction(bean.getAction());
            imPayrollTunjanganJabatanStrukturalEntity.setCreatedWho(bean.getCreatedWho());
            imPayrollTunjanganJabatanStrukturalEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPayrollTunjanganJabatanStrukturalEntity.setCreatedDate(bean.getCreatedDate());
            imPayrollTunjanganJabatanStrukturalEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                payrollTunjanganJabatanStrukturalDao.addAndSave(imPayrollTunjanganJabatanStrukturalEntity);
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PayrollTunjanganJabatanStruktural, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<PayrollTunjanganJabatanStruktural> getByCriteria(PayrollTunjanganJabatanStruktural searchBean) throws GeneralBOException {
        logger.info("[PayrollTunjanganJabatanStrukturalBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PayrollTunjanganJabatanStruktural> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTunjJabatanStrukturalId() != null && !"".equalsIgnoreCase(searchBean.getTunjJabatanStrukturalId())) {
                hsCriteria.put("tunjJabatanStrukturalId", searchBean.getTunjJabatanStrukturalId());
            }
            if (searchBean.getPositionId() != null && !"".equalsIgnoreCase(searchBean.getPositionId())) {
                hsCriteria.put("positionId", searchBean.getPositionId());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branchId", searchBean.getBranchId());
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


            List<ImmPayrollTunjanganJabatanStrukturalEntity> imPayrollTunjanganJabatanStrukturalEntity = null;
            try {
                imPayrollTunjanganJabatanStrukturalEntity = payrollTunjanganJabatanStrukturalDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.getSearchPayrollTunjanganJabatanStrukturalByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollTunjanganJabatanStrukturalEntity != null){
                PayrollTunjanganJabatanStruktural returnPayrollTunjanganJabatanStruktural;
                // Looping from dao to object and save in collection
                for(ImmPayrollTunjanganJabatanStrukturalEntity payrollTunjanganJabatanStrukturalEntity : imPayrollTunjanganJabatanStrukturalEntity){
                    returnPayrollTunjanganJabatanStruktural = new PayrollTunjanganJabatanStruktural();
                    returnPayrollTunjanganJabatanStruktural.setTunjJabatanStrukturalId(payrollTunjanganJabatanStrukturalEntity.getTunjJabatanStrukturalId());
                    returnPayrollTunjanganJabatanStruktural.setPositionId(payrollTunjanganJabatanStrukturalEntity.getPositionId());
                    returnPayrollTunjanganJabatanStruktural.setBranchId(payrollTunjanganJabatanStrukturalEntity.getBranchId());


                    if(returnPayrollTunjanganJabatanStruktural.getPositionId() != null){
                        try {
                            ImPosition position = positionDao.getById("positionId",payrollTunjanganJabatanStrukturalEntity.getPositionId());
                            if (position!=null){
                                returnPayrollTunjanganJabatanStruktural.setPositionName(position.getPositionName());
                            }
                        } catch (HibernateException e) {
                            logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.getSearchPayrollTunjanganJabatanStrukturalByCriteria] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }
                    }

                    if(returnPayrollTunjanganJabatanStruktural.getBranchId() != null){
                        try {
                            hsCriteria = new HashMap();
                            hsCriteria.put("branch_id",payrollTunjanganJabatanStrukturalEntity.getBranchId());
                            hsCriteria.put("flag","Y");
                            List<ImBranches> branchesList = branchDao.getByCriteria(hsCriteria);
                            if (branchesList!=null){
                                for(ImBranches imBranches : branchesList){
                                    returnPayrollTunjanganJabatanStruktural.setBranchName(imBranches.getBranchName());
                                }
                            }

                        } catch (HibernateException e) {
                            logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.getSearchPayrollTunjanganJabatanStrukturalByCriteria] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }
                    }
                    returnPayrollTunjanganJabatanStruktural.setNilai(payrollTunjanganJabatanStrukturalEntity.getNilai());

                    returnPayrollTunjanganJabatanStruktural.setCreatedWho(payrollTunjanganJabatanStrukturalEntity.getCreatedWho());
                    returnPayrollTunjanganJabatanStruktural.setCreatedDate(payrollTunjanganJabatanStrukturalEntity.getCreatedDate());
                    returnPayrollTunjanganJabatanStruktural.setLastUpdate(payrollTunjanganJabatanStrukturalEntity.getLastUpdate());

                    returnPayrollTunjanganJabatanStruktural.setAction(payrollTunjanganJabatanStrukturalEntity.getAction());
                    returnPayrollTunjanganJabatanStruktural.setFlag(payrollTunjanganJabatanStrukturalEntity.getFlag());
                    listOfResult.add(returnPayrollTunjanganJabatanStruktural);
                }
            }
        }
        logger.info("[PayrollTunjanganJabatanStrukturalBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PayrollTunjanganJabatanStruktural> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
