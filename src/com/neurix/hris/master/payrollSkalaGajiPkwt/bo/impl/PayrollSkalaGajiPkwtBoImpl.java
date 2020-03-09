package com.neurix.hris.master.payrollSkalaGajiPkwt.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.golonganPkwt.dao.GolonganPkwtDao;
import com.neurix.hris.master.golonganPkwt.model.GolonganPkwt;
import com.neurix.hris.master.golonganPkwt.model.ImGolonganPkwtEntity;
import com.neurix.hris.master.payrollSkalaGajiPkwt.bo.PayrollSkalaGajiPkwtBo;
import com.neurix.hris.master.payrollSkalaGajiPkwt.dao.PayrollSkalaGajiPkwtDao;
import com.neurix.hris.master.payrollSkalaGajiPkwt.model.ImPayrollSkalaGajiPkwtEntity;
import com.neurix.hris.master.payrollSkalaGajiPkwt.model.payrollSkalaGajiPkwt;
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
public class PayrollSkalaGajiPkwtBoImpl implements PayrollSkalaGajiPkwtBo {

    protected static transient Logger logger = Logger.getLogger(PayrollSkalaGajiPkwtBoImpl.class);
    private PayrollSkalaGajiPkwtDao payrollSkalaGajiPkwtDao;
    private GolonganPkwtDao golonganPkwtDao;

    public GolonganPkwtDao getGolonganPkwtDao() {
        return golonganPkwtDao;
    }

    public void setGolonganPkwtDao(GolonganPkwtDao golonganPkwtDao) {
        this.golonganPkwtDao = golonganPkwtDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollSkalaGajiPkwtBoImpl.logger = logger;
    }

    public PayrollSkalaGajiPkwtDao getPayrollSkalaGajiPkwtDao() {
        return payrollSkalaGajiPkwtDao;
    }

    public void setPayrollSkalaGajiPkwtDao(PayrollSkalaGajiPkwtDao payrollSkalaGajiPkwtDao) {
        this.payrollSkalaGajiPkwtDao = payrollSkalaGajiPkwtDao;
    }

    @Override
    public void saveDelete(payrollSkalaGajiPkwt bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollSkalaGajiId = bean.getSkalaGajiPkwtId();

            ImPayrollSkalaGajiPkwtEntity imPayrollSkalaGajiEntity = null;

            try {
                // Get data from database by ID
                imPayrollSkalaGajiEntity = payrollSkalaGajiPkwtDao.getById("skalaGajiPkwtId", payrollSkalaGajiId);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiEntity != null) {

                // Modify from bean to entity serializable
                imPayrollSkalaGajiEntity.setSkalaGajiPkwtId(bean.getSkalaGajiPkwtId());
                imPayrollSkalaGajiEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiEntity.setAction(bean.getAction());
                imPayrollSkalaGajiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollSkalaGajiPkwtDao.updateAndSave(imPayrollSkalaGajiEntity);
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
    public void saveEdit(payrollSkalaGajiPkwt bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiPkwtBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            //String historyId = "";
            String payrollSkalaGajiPkwtId = bean.getSkalaGajiPkwtId();

            ImPayrollSkalaGajiPkwtEntity imPayrollSkalaGajiPkwtEntity = null;
            //ImPayrollSkalaGajiPkwtHistoryEntity imPayrollSkalaGajiPkwtHistoryEntity = new ImPayrollSkalaGajiPkwtHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollSkalaGajiPkwtEntity = payrollSkalaGajiPkwtDao.getById("skalaGajiPkwtId", payrollSkalaGajiPkwtId);
                //historyId = payrollSkalaGajiPkwtDao.getNextPayrollSkalaGajiPkwtHistoryId();
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiPkwtBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollSkalaGajiPkwt by Kode PayrollSkalaGajiPkwt, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiPkwtEntity != null) {
                /*imPayrollSkalaGajiPkwtHistoryEntity.setId(historyId);
                imPayrollSkalaGajiPkwtHistoryEntity.setPayrollSkalaGajiPkwtId(imPayrollSkalaGajiPkwtEntity.getPayrollSkalaGajiPkwtId());
                imPayrollSkalaGajiPkwtHistoryEntity.setPayrollSkalaGajiPkwtName(imPayrollSkalaGajiPkwtEntity.getPayrollSkalaGajiPkwtName());
                imPayrollSkalaGajiPkwtHistoryEntity.setFlag(imPayrollSkalaGajiPkwtEntity.getFlag());
                imPayrollSkalaGajiPkwtHistoryEntity.setAction(imPayrollSkalaGajiPkwtEntity.getAction());
                imPayrollSkalaGajiPkwtHistoryEntity.setLastUpdateWho(imPayrollSkalaGajiPkwtEntity.getLastUpdateWho());
                imPayrollSkalaGajiPkwtHistoryEntity.setLastUpdate(imPayrollSkalaGajiPkwtEntity.getLastUpdate());
                imPayrollSkalaGajiPkwtHistoryEntity.setCreatedWho(imPayrollSkalaGajiPkwtEntity.getLastUpdateWho());
                imPayrollSkalaGajiPkwtHistoryEntity.setCreatedDate(imPayrollSkalaGajiPkwtEntity.getLastUpdate());*/

                /*imPayrollSkalaGajiPkwtEntity.setSkalaGajiPkwtId(bean.getSkalaGajiPkwtId());
                imPayrollSkalaGajiPkwtEntity.setGolonganPkwtId(bean.getGolonganPkwtId());*/
                imPayrollSkalaGajiPkwtEntity.setGajiPokok(bean.getGajiPokokNilai());
                imPayrollSkalaGajiPkwtEntity.setSantunanKhusus(bean.getSantunanKhususNilai());
                imPayrollSkalaGajiPkwtEntity.setTunjFunsional(bean.getTunjFunsionalNilai());
                imPayrollSkalaGajiPkwtEntity.setTunjtambahan(bean.getTunjtambahanNilai());
                imPayrollSkalaGajiPkwtEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiPkwtEntity.setAction(bean.getAction());
                imPayrollSkalaGajiPkwtEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiPkwtEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollSkalaGajiPkwtDao.updateAndSave(imPayrollSkalaGajiPkwtEntity);
                    //payrollSkalaGajiPkwtDao.addAndSaveHistory(imPayrollSkalaGajiPkwtHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiPkwtBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollSkalaGajiPkwt, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollSkalaGajiPkwtBoImpl.saveEdit] Error, not found data PayrollSkalaGajiPkwt with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollSkalaGajiPkwt with request id, please check again your data ...");
//                condition = "Error, not found data PayrollSkalaGajiPkwt with request id, please check again your data ...";
            }
        }
        logger.info("[PayrollSkalaGajiPkwtBoImpl.saveEdit] end process <<<");
    }

    @Override
    public payrollSkalaGajiPkwt saveAdd(payrollSkalaGajiPkwt bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiPkwtBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String payrollSkalaGajiPkwtId;
            try {
                // Generating ID, get from postgre sequence
                payrollSkalaGajiPkwtId = payrollSkalaGajiPkwtDao.getNextSkalaGajiPkwt();
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiPkwtBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence payrollSkalaGajiPkwtId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImPayrollSkalaGajiPkwtEntity imPayrollSkalaGajiPkwtEntity = new ImPayrollSkalaGajiPkwtEntity();

            imPayrollSkalaGajiPkwtEntity.setSkalaGajiPkwtId(payrollSkalaGajiPkwtId);
            imPayrollSkalaGajiPkwtEntity.setGolonganPkwtId(bean.getGolonganPkwtId());
            imPayrollSkalaGajiPkwtEntity.setGajiPokok(bean.getGajiPokokNilai());
            imPayrollSkalaGajiPkwtEntity.setSantunanKhusus(bean.getSantunanKhususNilai());
            imPayrollSkalaGajiPkwtEntity.setTunjFunsional(bean.getTunjFunsionalNilai());
            imPayrollSkalaGajiPkwtEntity.setTunjtambahan(bean.getTunjtambahanNilai());
            imPayrollSkalaGajiPkwtEntity.setFlag(bean.getFlag());
            imPayrollSkalaGajiPkwtEntity.setAction(bean.getAction());
            imPayrollSkalaGajiPkwtEntity.setCreatedWho(bean.getCreatedWho());
            imPayrollSkalaGajiPkwtEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPayrollSkalaGajiPkwtEntity.setCreatedDate(bean.getCreatedDate());
            imPayrollSkalaGajiPkwtEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                payrollSkalaGajiPkwtDao.addAndSave(imPayrollSkalaGajiPkwtEntity);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiPkwtBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PayrollSkalaGajiPkwt, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PayrollSkalaGajiPkwtBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<payrollSkalaGajiPkwt> getByCriteria(payrollSkalaGajiPkwt searchBean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<payrollSkalaGajiPkwt> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getSkalaGajiPkwtId() != null && !"".equalsIgnoreCase(searchBean.getSkalaGajiPkwtId())) {
                hsCriteria.put("skala_gaji_pkwt_id", searchBean.getSkalaGajiPkwtId());
            }
            if (searchBean.getGolonganPkwtId() != null && !"".equalsIgnoreCase(searchBean.getGolonganPkwtId())) {
                hsCriteria.put("golongan_pkwt_id", searchBean.getGolonganPkwtId());
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


            List<ImPayrollSkalaGajiPkwtEntity> imPayrollSkalaGajiEntity = null;
            try {

                imPayrollSkalaGajiEntity = payrollSkalaGajiPkwtDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollSkalaGajiEntity != null){
                payrollSkalaGajiPkwt returnPayrollSkalaGaji;
                // Looping from dao to object and save in collection
                for(ImPayrollSkalaGajiPkwtEntity payrollSkalaGajiEntity : imPayrollSkalaGajiEntity){
                    returnPayrollSkalaGaji = new payrollSkalaGajiPkwt();
                    returnPayrollSkalaGaji.setSkalaGajiPkwtId(payrollSkalaGajiEntity.getSkalaGajiPkwtId());

                    ImGolonganPkwtEntity golonganPkwt = new ImGolonganPkwtEntity();
                    returnPayrollSkalaGaji.setGolonganPkwtId(payrollSkalaGajiEntity.getGolonganPkwtId());
                    golonganPkwt = golonganPkwtDao.getById("golonganPkwtId",payrollSkalaGajiEntity.getGolonganPkwtId());
                    returnPayrollSkalaGaji.setGolonganPkwtName(golonganPkwt.getGolonganPkwtName());
                    returnPayrollSkalaGaji.setGajiPokokNilai(payrollSkalaGajiEntity.getGajiPokok());
                    returnPayrollSkalaGaji.setGajiPokok(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getGajiPokok(),"###,###"));
                    returnPayrollSkalaGaji.setSantunanKhususNilai(payrollSkalaGajiEntity.getSantunanKhusus());
                    returnPayrollSkalaGaji.setSantunanKhusus(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getSantunanKhusus(),"###,###"));
                    returnPayrollSkalaGaji.setTunjFunsionalNilai(payrollSkalaGajiEntity.getTunjFunsional());
                    returnPayrollSkalaGaji.setTunjFunsional(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getTunjFunsional(),"###,###"));
                    returnPayrollSkalaGaji.setTunjtambahanNilai(payrollSkalaGajiEntity.getTunjtambahan());
                    returnPayrollSkalaGaji.setTunjtambahan(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getTunjtambahan(),"###,###"));

                    returnPayrollSkalaGaji.setCreatedWho(payrollSkalaGajiEntity.getCreatedWho());
                    returnPayrollSkalaGaji.setCreatedDate(payrollSkalaGajiEntity.getCreatedDate());
                    returnPayrollSkalaGaji.setLastUpdate(payrollSkalaGajiEntity.getLastUpdate());

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
    public List<payrollSkalaGajiPkwt> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<payrollSkalaGajiPkwt> getComboPayrollSkalaGajiPkwtWithCriteria(String query) throws GeneralBOException {
        return null;
    }
}