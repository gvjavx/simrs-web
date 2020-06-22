package com.neurix.hris.master.payrollBpjs.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.company.model.ImBranchesPK;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollBpjs.bo.PayrollBpjsBo;
import com.neurix.hris.transaksi.payroll.dao.PayrollBpjsDao;
import com.neurix.hris.transaksi.payroll.dao.PayrollBpjsHistoryDao;
import com.neurix.hris.transaksi.payroll.model.ImPayrollBpjsEntity;
import com.neurix.hris.transaksi.payroll.model.ImPayrollBpjsHistoryEntity;
import com.neurix.hris.transaksi.payroll.model.PayrollBpjs;
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
public class PayrollBpjsBoImpl implements PayrollBpjsBo {

    protected static transient Logger logger = Logger.getLogger(PayrollBpjsBoImpl.class);
    private PayrollBpjsDao payrollBpjsDao;
    private PayrollBpjsHistoryDao payrollBpjsHistoryDao;
    private BranchDao branchDao;

    public PayrollBpjsHistoryDao getPayrollBpjsHistoryDao() {
        return payrollBpjsHistoryDao;
    }

    public void setPayrollBpjsHistoryDao(PayrollBpjsHistoryDao payrollBpjsHistoryDao) {
        this.payrollBpjsHistoryDao = payrollBpjsHistoryDao;
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
        PayrollBpjsBoImpl.logger = logger;
    }

    public PayrollBpjsDao getPayrollBpjsDao() {
        return payrollBpjsDao;
    }

    public void setPayrollBpjsDao(PayrollBpjsDao payrollBpjsDao) {
        this.payrollBpjsDao = payrollBpjsDao;
    }

    @Override
    public void saveDelete(PayrollBpjs bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollSkalaGajiId = bean.getBpjsId();

            ImPayrollBpjsEntity imPayrollSkalaGajiEntity = null;
            ImPayrollBpjsHistoryEntity historyEntity = new ImPayrollBpjsHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollSkalaGajiEntity = payrollBpjsDao.getById("bpjsId", payrollSkalaGajiId);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiEntity != null) {
                //entity history
                String payrollBpjsHistoryId;
                try {
                    // Generating ID, get from postgre sequence
                    payrollBpjsHistoryId = payrollBpjsHistoryDao.getNextBpjsHistory();
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollTunjanganJabatanStruktural id, please info to your admin..." + e.getMessage());
                }
                historyEntity.setBpjsIdHistory(payrollBpjsHistoryId);
                historyEntity.setBpjsId(imPayrollSkalaGajiEntity.getBpjsId());
                historyEntity.setMinBpjsKs(imPayrollSkalaGajiEntity.getMinBpjsKs());
                historyEntity.setMaxBpjsKs(imPayrollSkalaGajiEntity.getMaxBpjsKs());
                historyEntity.setMinBpjsTk(imPayrollSkalaGajiEntity.getMinBpjsTk());
                historyEntity.setMaxBpjsTk(imPayrollSkalaGajiEntity.getMaxBpjsTk());
                historyEntity.setBranchId(imPayrollSkalaGajiEntity.getBranchId());
                historyEntity.setBranchName(imPayrollSkalaGajiEntity.getBranchName());
                historyEntity.setIuranBpjsKsKaryPersen(imPayrollSkalaGajiEntity.getIuranBpjsKsKaryPersen());
                historyEntity.setIuranBpjsKsPersPersen(imPayrollSkalaGajiEntity.getIuranBpjsKsPersPersen());
                historyEntity.setIuranBpjsTkKaryPersen(imPayrollSkalaGajiEntity.getIuranBpjsTkKaryPersen());
                historyEntity.setIuranBpjsTkPersPersen(imPayrollSkalaGajiEntity.getIuranBpjsTkPersPersen());
                historyEntity.setIuranKary(imPayrollSkalaGajiEntity.getIuranKary());
                historyEntity.setJpkKary(imPayrollSkalaGajiEntity.getJpkKary());
                historyEntity.setJkkPers(imPayrollSkalaGajiEntity.getJkkPers());
                historyEntity.setJhtPers(imPayrollSkalaGajiEntity.getJhtPers());
                historyEntity.setJkmPers(imPayrollSkalaGajiEntity.getJkmPers());
                historyEntity.setJpkPers(imPayrollSkalaGajiEntity.getJpkPers());
                historyEntity.setIuranPers(imPayrollSkalaGajiEntity.getIuranPers());
                historyEntity.setCreatedDate(imPayrollSkalaGajiEntity.getLastUpdate());
                historyEntity.setCreatedWho(imPayrollSkalaGajiEntity.getLastUpdateWho());
                historyEntity.setLastUpdate(imPayrollSkalaGajiEntity.getLastUpdate());
                historyEntity.setLastUpdateWho(imPayrollSkalaGajiEntity.getLastUpdateWho());
                historyEntity.setFlag("Y");
                historyEntity.setAction(imPayrollSkalaGajiEntity.getAction());

                try {
                    // insert into database
                    payrollBpjsHistoryDao.addAndSave(historyEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollTunjanganJabatanStruktural, please info to your admin..." + e.getMessage());
                }

                // Modify from bean to entity serializable
                imPayrollSkalaGajiEntity.setBpjsId(bean.getBpjsId());
                imPayrollSkalaGajiEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiEntity.setAction(bean.getAction());
                imPayrollSkalaGajiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollBpjsDao.updateAndSave(imPayrollSkalaGajiEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollSkalaGaji, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, not found data PayrollSkalaGaji with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollSkalaGaji with request id, please check again your data ...");

            }
        }
        logger.info("[PayrollSkalaGajiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(PayrollBpjs bean) throws GeneralBOException {
        logger.info("[PayrollBpjsBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            //String historyId = "";
            String payrollBpjsId = bean.getBpjsId();

            ImPayrollBpjsEntity imPayrollBpjsEntity = null;
            ImPayrollBpjsHistoryEntity historyEntity = new ImPayrollBpjsHistoryEntity();
            //ImPayrollBpjsHistoryEntity imPayrollBpjsHistoryEntity = new ImPayrollBpjsHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollBpjsEntity = payrollBpjsDao.getById("bpjsId", payrollBpjsId);
                //historyId = payrollBpjsDao.getNextPayrollBpjsHistoryId();
            } catch (HibernateException e) {
                logger.error("[PayrollBpjsBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollBpjs by Kode PayrollBpjs, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollBpjsEntity != null) {
                //entity history
                String payrollBpjsHistoryId;
                try {
                    // Generating ID, get from postgre sequence
                    payrollBpjsHistoryId = payrollBpjsHistoryDao.getNextBpjsHistory();
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollTunjanganJabatanStruktural id, please info to your admin..." + e.getMessage());
                }
                historyEntity.setBpjsIdHistory(payrollBpjsHistoryId);
                historyEntity.setBpjsId(imPayrollBpjsEntity.getBpjsId());
                historyEntity.setMinBpjsKs(imPayrollBpjsEntity.getMinBpjsKs());
                historyEntity.setMaxBpjsKs(imPayrollBpjsEntity.getMaxBpjsKs());
                historyEntity.setMinBpjsTk(imPayrollBpjsEntity.getMinBpjsTk());
                historyEntity.setMaxBpjsTk(imPayrollBpjsEntity.getMaxBpjsTk());
                historyEntity.setBranchId(imPayrollBpjsEntity.getBranchId());
                historyEntity.setBranchName(imPayrollBpjsEntity.getBranchName());
                historyEntity.setIuranBpjsKsKaryPersen(imPayrollBpjsEntity.getIuranBpjsKsKaryPersen());
                historyEntity.setIuranBpjsKsPersPersen(imPayrollBpjsEntity.getIuranBpjsKsPersPersen());
                historyEntity.setIuranBpjsTkKaryPersen(imPayrollBpjsEntity.getIuranBpjsTkKaryPersen());
                historyEntity.setIuranBpjsTkPersPersen(imPayrollBpjsEntity.getIuranBpjsTkPersPersen());
                historyEntity.setIuranKary(imPayrollBpjsEntity.getIuranKary());
                historyEntity.setJpkKary(imPayrollBpjsEntity.getJpkKary());
                historyEntity.setJkkPers(imPayrollBpjsEntity.getJkkPers());
                historyEntity.setJhtPers(imPayrollBpjsEntity.getJhtPers());
                historyEntity.setJkmPers(imPayrollBpjsEntity.getJkmPers());
                historyEntity.setJpkPers(imPayrollBpjsEntity.getJpkPers());
                historyEntity.setIuranPers(imPayrollBpjsEntity.getIuranPers());
                historyEntity.setCreatedDate(imPayrollBpjsEntity.getLastUpdate());
                historyEntity.setCreatedWho(imPayrollBpjsEntity.getLastUpdateWho());
                historyEntity.setLastUpdate(imPayrollBpjsEntity.getLastUpdate());
                historyEntity.setLastUpdateWho(imPayrollBpjsEntity.getLastUpdateWho());
                historyEntity.setFlag("Y");
                historyEntity.setAction(imPayrollBpjsEntity.getAction());

                try {
                    // insert into database
                    payrollBpjsHistoryDao.addAndSave(historyEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollTunjanganJabatanStruktural, please info to your admin..." + e.getMessage());
                }

                /*imPayrollBpjsHistoryEntity.setId(historyId);
                imPayrollBpjsHistoryEntity.setPayrollBpjsId(imPayrollBpjsEntity.getPayrollBpjsId());
                imPayrollBpjsHistoryEntity.setPayrollBpjsName(imPayrollBpjsEntity.getPayrollBpjsName());
                imPayrollBpjsHistoryEntity.setFlag(imPayrollBpjsEntity.getFlag());
                imPayrollBpjsHistoryEntity.setAction(imPayrollBpjsEntity.getAction());
                imPayrollBpjsHistoryEntity.setLastUpdateWho(imPayrollBpjsEntity.getLastUpdateWho());
                imPayrollBpjsHistoryEntity.setLastUpdate(imPayrollBpjsEntity.getLastUpdate());
                imPayrollBpjsHistoryEntity.setCreatedWho(imPayrollBpjsEntity.getLastUpdateWho());
                imPayrollBpjsHistoryEntity.setCreatedDate(imPayrollBpjsEntity.getLastUpdate());*/

                imPayrollBpjsEntity.setMaxBpjsKs(bean.getMaxBpjsKs());
                imPayrollBpjsEntity.setMinBpjsKs(bean.getMinBpjsKs());
                imPayrollBpjsEntity.setMaxBpjsTk(bean.getMaxBpjsTk());
                imPayrollBpjsEntity.setMinBpjsTk(bean.getMinBpjsTk());

                imPayrollBpjsEntity.setIuranKary(bean.getIuranKary());
                imPayrollBpjsEntity.setJpkKary(bean.getJpkKary());

                imPayrollBpjsEntity.setJkkPers(bean.getJkkPers());
                imPayrollBpjsEntity.setJhtPers(bean.getJhtPers());
                imPayrollBpjsEntity.setJkmPers(bean.getJkmPers());
                imPayrollBpjsEntity.setIuranPers(bean.getIuranPers());
                imPayrollBpjsEntity.setJpkPers(bean.getJpkPers());

                imPayrollBpjsEntity.setIuranBpjsKsKaryPersen(bean.getIuranBpjsKsKaryPersen());
                imPayrollBpjsEntity.setIuranBpjsKsPersPersen(bean.getIuranBpjsKsPersPersen());
                imPayrollBpjsEntity.setIuranBpjsTkKaryPersen(bean.getIuranBpjsTkKaryPersen());
                imPayrollBpjsEntity.setIuranBpjsTkPersPersen(bean.getIuranBpjsTkPersPersen());

                imPayrollBpjsEntity.setBpjsId(bean.getBpjsId());
                imPayrollBpjsEntity.setFlag(bean.getFlag());
                imPayrollBpjsEntity.setAction(bean.getAction());
                imPayrollBpjsEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollBpjsEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollBpjsDao.updateAndSave(imPayrollBpjsEntity);
                    //payrollBpjsDao.addAndSaveHistory(imPayrollBpjsHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[PayrollBpjsBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollBpjs, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollBpjsBoImpl.saveEdit] Error, not found data PayrollBpjs with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollBpjs with request id, please check again your data ...");
//                condition = "Error, not found data PayrollBpjs with request id, please check again your data ...";
            }
        }
        logger.info("[PayrollBpjsBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PayrollBpjs saveAdd(PayrollBpjs bean) throws GeneralBOException {
        logger.info("[PayrollBpjsBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String status = cekStatus(bean.getBranchId());
            if (!status.equalsIgnoreCase("Exist")){
                String payrollBpjsId;
                try {
                    // Generating ID, get from postgre sequence
                    payrollBpjsId = payrollBpjsDao.getNextBpjs();
                } catch (HibernateException e) {
                    logger.error("[PayrollBpjsBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollBpjsId id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImPayrollBpjsEntity imPayrollBpjsEntity = new ImPayrollBpjsEntity();

                imPayrollBpjsEntity.setBpjsId(payrollBpjsId);
                ImBranches imBranches = null;
                ImBranchesPK primaryKey = new ImBranchesPK();
                primaryKey.setId(bean.getBranchId());

                try {
                    imBranches = branchDao.getById(primaryKey,"Y");
                } catch (HibernateException e) {
                    logger.error("[BranchBoImpl.getBranchById] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when retrieving Branch based on id and flag, please info to your admin..." + e.getMessage());
                }
                if (imBranches!=null){
                    imPayrollBpjsEntity.setBranchName(imBranches.getBranchName());
                }else{
                    imPayrollBpjsEntity.setBranchName("-");
                }
                imPayrollBpjsEntity.setBranchId(bean.getBranchId());
                imPayrollBpjsEntity.setMaxBpjsKs(bean.getMaxBpjsKs());
                imPayrollBpjsEntity.setMinBpjsKs(bean.getMinBpjsKs());
                imPayrollBpjsEntity.setMaxBpjsTk(bean.getMaxBpjsTk());
                imPayrollBpjsEntity.setMinBpjsTk(bean.getMinBpjsTk());

                imPayrollBpjsEntity.setIuranKary(bean.getIuranKary());
                imPayrollBpjsEntity.setJpkKary(bean.getJpkKary());

                imPayrollBpjsEntity.setJkkPers(bean.getJkkPers());
                imPayrollBpjsEntity.setJhtPers(bean.getJhtPers());
                imPayrollBpjsEntity.setJkmPers(bean.getJkmPers());
                imPayrollBpjsEntity.setIuranPers(bean.getIuranPers());
                imPayrollBpjsEntity.setJpkPers(bean.getJpkPers());

                imPayrollBpjsEntity.setIuranBpjsKsKaryPersen(bean.getIuranBpjsKsKaryPersen());
                imPayrollBpjsEntity.setIuranBpjsKsPersPersen(bean.getIuranBpjsKsPersPersen());
                imPayrollBpjsEntity.setIuranBpjsTkKaryPersen(bean.getIuranBpjsTkKaryPersen());
                imPayrollBpjsEntity.setIuranBpjsTkPersPersen(bean.getIuranBpjsTkPersPersen());
                imPayrollBpjsEntity.setFlag(bean.getFlag());
                imPayrollBpjsEntity.setAction(bean.getAction());
                imPayrollBpjsEntity.setCreatedWho(bean.getCreatedWho());
                imPayrollBpjsEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollBpjsEntity.setCreatedDate(bean.getCreatedDate());
                imPayrollBpjsEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    payrollBpjsDao.addAndSave(imPayrollBpjsEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollBpjsBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollBpjs, please info to your admin..." + e.getMessage());
                }
            }else {
                throw new GeneralBOException("Maaf Data Dengan Unit Tersebut Sudah ada");
            }
        }

        logger.info("[PayrollBpjsBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<PayrollBpjs> getByCriteria(PayrollBpjs searchBean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PayrollBpjs> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getBpjsId() != null && !"".equalsIgnoreCase(searchBean.getBpjsId())) {
                hsCriteria.put("bpjs_id", searchBean.getBpjsId());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
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


            List<ImPayrollBpjsEntity> imPayrollSkalaGajiEntity = null;
            try {

                imPayrollSkalaGajiEntity = payrollBpjsDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollSkalaGajiEntity != null){
                PayrollBpjs returnPayrollSkalaGaji;
                // Looping from dao to object and save in collection
                for(ImPayrollBpjsEntity payrollSkalaGajiEntity : imPayrollSkalaGajiEntity){
                    returnPayrollSkalaGaji = new PayrollBpjs();
                    returnPayrollSkalaGaji.setBpjsId(payrollSkalaGajiEntity.getBpjsId());
                    returnPayrollSkalaGaji.setBranchId(payrollSkalaGajiEntity.getBranchId());
                    returnPayrollSkalaGaji.setBranchName(payrollSkalaGajiEntity.getBranchName());


                    returnPayrollSkalaGaji.setMinBpjsKs(payrollSkalaGajiEntity.getMinBpjsKs());
                    returnPayrollSkalaGaji.setMaxBpjsKs(payrollSkalaGajiEntity.getMaxBpjsKs());
                    returnPayrollSkalaGaji.setMinBpjsTk(payrollSkalaGajiEntity.getMinBpjsTk());
                    returnPayrollSkalaGaji.setMaxBpjsTk(payrollSkalaGajiEntity.getMaxBpjsTk());

                    returnPayrollSkalaGaji.setNumMinBpjsKs(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getMinBpjsKs(), "###,###"));
                    returnPayrollSkalaGaji.setNumMaxBpjsKs(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getMaxBpjsKs(), "###,###"));
                    returnPayrollSkalaGaji.setNumMinBpjsTk(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getMinBpjsTk(), "###,###"));
                    returnPayrollSkalaGaji.setNumMaxBpjsTk(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getMaxBpjsTk(), "###,###"));

                    returnPayrollSkalaGaji.setIuranKary(payrollSkalaGajiEntity.getIuranKary());
                    returnPayrollSkalaGaji.setJpkKary(payrollSkalaGajiEntity.getJpkKary());

                    returnPayrollSkalaGaji.setJkkPers(payrollSkalaGajiEntity.getJkkPers());
                    returnPayrollSkalaGaji.setJhtPers(payrollSkalaGajiEntity.getJhtPers());
                    returnPayrollSkalaGaji.setJkmPers(payrollSkalaGajiEntity.getJkmPers());
                    returnPayrollSkalaGaji.setIuranPers(payrollSkalaGajiEntity.getIuranPers());
                    returnPayrollSkalaGaji.setJpkPers(payrollSkalaGajiEntity.getJpkPers());

                    returnPayrollSkalaGaji.setIuranBpjsKsKaryPersen(payrollSkalaGajiEntity.getIuranBpjsKsKaryPersen());
                    returnPayrollSkalaGaji.setIuranBpjsKsPersPersen(payrollSkalaGajiEntity.getIuranBpjsKsPersPersen());
                    returnPayrollSkalaGaji.setIuranBpjsTkKaryPersen(payrollSkalaGajiEntity.getIuranBpjsTkKaryPersen());
                    returnPayrollSkalaGaji.setIuranBpjsTkPersPersen(payrollSkalaGajiEntity.getIuranBpjsTkPersPersen());

                    returnPayrollSkalaGaji.setCreatedWho(payrollSkalaGajiEntity.getCreatedWho());
                    returnPayrollSkalaGaji.setCreatedDate(payrollSkalaGajiEntity.getCreatedDate());
                    returnPayrollSkalaGaji.setLastUpdate(payrollSkalaGajiEntity.getLastUpdate());
                    returnPayrollSkalaGaji.setLastUpdateWho(payrollSkalaGajiEntity.getLastUpdateWho());

                    returnPayrollSkalaGaji.setAction(payrollSkalaGajiEntity.getAction());
                    returnPayrollSkalaGaji.setFlag(payrollSkalaGajiEntity.getFlag());
                    listOfResult.add(returnPayrollSkalaGaji);
                }
            }
        }
        logger.info("[PayrollSkalaGajiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PayrollBpjs> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<PayrollBpjs> getComboPayrollBpjsWithCriteria(String query) throws GeneralBOException {
        return null;
    }
    public String cekStatus(String golonganId)throws GeneralBOException{
        String status ="";
        List<ImPayrollBpjsEntity> skalaGajiEntity = new ArrayList<>();
        try {
            skalaGajiEntity = payrollBpjsDao.getBpjsFilter(golonganId);
        } catch (HibernateException e) {
            logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (skalaGajiEntity.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }
}