package com.neurix.hris.master.payrollSkalaGajiPensiunRni.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollDanaPensiun.dao.PayrollDanaPensiunDao;
import com.neurix.hris.master.payrollDanaPensiun.model.ImPayrollDanaPensiunEntity;
import com.neurix.hris.master.payrollSkalaGajiPensiunRni.bo.PayrollSkalaGajiPensiunRniBo;
import com.neurix.hris.master.payrollSkalaGajiPensiunRni.dao.PayrollSkalaGajiPensiunRniDao;
import com.neurix.hris.master.payrollSkalaGajiPensiunRni.dao.PayrollSkalaGajiPensiunRniHistoryDao;
import com.neurix.hris.master.payrollSkalaGajiPensiunRni.model.ImPayrollSkalaGajiPensiunRniEntity;
import com.neurix.hris.master.payrollSkalaGajiPensiunRni.model.ImPayrollSkalaGajiPensiunRniHistoryEntity;
import com.neurix.hris.master.payrollSkalaGajiPensiunRni.model.payrollSkalaGajiPensiunRni;
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
public class PayrollSkalaGajiPensiunRniBoImpl implements PayrollSkalaGajiPensiunRniBo {

    protected static transient Logger logger = Logger.getLogger(PayrollSkalaGajiPensiunRniBoImpl.class);
    private PayrollSkalaGajiPensiunRniDao payrollSkalaGajiPensiunRniDao;
    private PayrollSkalaGajiPensiunRniHistoryDao payrollSkalaGajiPensiunRniHistoryDao;
    private PayrollDanaPensiunDao payrollDanaPensiunDao;

    public PayrollDanaPensiunDao getPayrollDanaPensiunDao() {
        return payrollDanaPensiunDao;
    }

    public void setPayrollDanaPensiunDao(PayrollDanaPensiunDao payrollDanaPensiunDao) {
        this.payrollDanaPensiunDao = payrollDanaPensiunDao;
    }

    public PayrollSkalaGajiPensiunRniHistoryDao getPayrollSkalaGajiPensiunRniHistoryDao() {
        return payrollSkalaGajiPensiunRniHistoryDao;
    }

    public void setPayrollSkalaGajiPensiunRniHistoryDao(PayrollSkalaGajiPensiunRniHistoryDao payrollSkalaGajiPensiunRniHistoryDao) {
        this.payrollSkalaGajiPensiunRniHistoryDao = payrollSkalaGajiPensiunRniHistoryDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollSkalaGajiPensiunRniBoImpl.logger = logger;
    }

    public PayrollSkalaGajiPensiunRniDao getPayrollSkalaGajiPensiunRniDao() {
        return payrollSkalaGajiPensiunRniDao;
    }

    public void setPayrollSkalaGajiPensiunRniDao(PayrollSkalaGajiPensiunRniDao payrollSkalaGajiPensiunRniDao) {
        this.payrollSkalaGajiPensiunRniDao = payrollSkalaGajiPensiunRniDao;
    }

    @Override
    public void saveDelete(payrollSkalaGajiPensiunRni bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollSkalaGajiId = bean.getSkalaGajiPensiunId();

            ImPayrollSkalaGajiPensiunRniEntity imPayrollSkalaGajiEntity = null;
            ImPayrollSkalaGajiPensiunRniHistoryEntity historyEntity = new ImPayrollSkalaGajiPensiunRniHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollSkalaGajiEntity = payrollSkalaGajiPensiunRniDao.getById("skalaGajiPensiunId", payrollSkalaGajiId);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiEntity != null) {
                //entity history
                String payrollSkalaGajiDapenbun;
                try {
                    // Generating ID, get from postgre sequence
                    payrollSkalaGajiDapenbun = payrollSkalaGajiPensiunRniHistoryDao.getNextSkalaGajiPensiunHistory();
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiPensiunRni.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollTunjanganJabatanStruktural id, please info to your admin..." + e.getMessage());
                }
                historyEntity.setSkalaGajiPensiunIdHistory(payrollSkalaGajiDapenbun);
                historyEntity.setSkalaGajiPensiunId(imPayrollSkalaGajiEntity.getSkalaGajiPensiunId());
                historyEntity.setGolonganId(imPayrollSkalaGajiEntity.getGolonganId());
                historyEntity.setPoin(imPayrollSkalaGajiEntity.getPoin());
                historyEntity.setNilai(imPayrollSkalaGajiEntity.getNilai());
                historyEntity.setAction(imPayrollSkalaGajiEntity.getAction());
                historyEntity.setFlag("Y");
                historyEntity.setCreatedDate(imPayrollSkalaGajiEntity.getLastUpdate());
                historyEntity.setCreatedWho(imPayrollSkalaGajiEntity.getLastUpdateWho());
                historyEntity.setLastUpdate(imPayrollSkalaGajiEntity.getLastUpdate());
                historyEntity.setLastUpdateWho(imPayrollSkalaGajiEntity.getLastUpdateWho());

                try {
                    // insert into database
                    payrollSkalaGajiPensiunRniHistoryDao.addAndSave(historyEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollTunjanganJabatanStruktural, please info to your admin..." + e.getMessage());
                }

                // Modify from bean to entity serializable
                imPayrollSkalaGajiEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiEntity.setAction(bean.getAction());
                imPayrollSkalaGajiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollSkalaGajiPensiunRniDao.updateAndSave(imPayrollSkalaGajiEntity);
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
    public void saveEdit(payrollSkalaGajiPensiunRni bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiPensiunBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            //String historyId = "";
            String payrollSkalaGajiPensiunId = bean.getSkalaGajiPensiunId();

            ImPayrollSkalaGajiPensiunRniEntity imPayrollSkalaGajiPensiunEntity = null;
            ImPayrollSkalaGajiPensiunRniHistoryEntity historyEntity = new ImPayrollSkalaGajiPensiunRniHistoryEntity();
            //ImPayrollSkalaGajiPensiunHistoryEntity imPayrollSkalaGajiPensiunHistoryEntity = new ImPayrollSkalaGajiPensiunHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollSkalaGajiPensiunEntity = payrollSkalaGajiPensiunRniDao.getById("skalaGajiPensiunId", payrollSkalaGajiPensiunId);
                //historyId = payrollSkalaGajiPensiunRniDao.getNextPayrollSkalaGajiPensiunHistoryId();
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiPensiunBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollSkalaGajiPensiun by Kode PayrollSkalaGajiPensiun, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiPensiunEntity != null) {
                //entity history
                String payrollSkalaGajiDapenbun;
                try {
                    // Generating ID, get from postgre sequence
                    payrollSkalaGajiDapenbun = payrollSkalaGajiPensiunRniHistoryDao.getNextSkalaGajiPensiunHistory();
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiPensiunRni.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollTunjanganJabatanStruktural id, please info to your admin..." + e.getMessage());
                }
                historyEntity.setSkalaGajiPensiunIdHistory(payrollSkalaGajiDapenbun);
                historyEntity.setSkalaGajiPensiunId(imPayrollSkalaGajiPensiunEntity.getSkalaGajiPensiunId());
                historyEntity.setGolonganId(imPayrollSkalaGajiPensiunEntity.getGolonganId());
                historyEntity.setPoin(imPayrollSkalaGajiPensiunEntity.getPoin());
                historyEntity.setNilai(imPayrollSkalaGajiPensiunEntity.getNilai());
                historyEntity.setAction(imPayrollSkalaGajiPensiunEntity.getAction());
                historyEntity.setFlag("Y");
                historyEntity.setCreatedDate(imPayrollSkalaGajiPensiunEntity.getLastUpdate());
                historyEntity.setCreatedWho(imPayrollSkalaGajiPensiunEntity.getLastUpdateWho());
                historyEntity.setLastUpdate(imPayrollSkalaGajiPensiunEntity.getLastUpdate());
                historyEntity.setLastUpdateWho(imPayrollSkalaGajiPensiunEntity.getLastUpdateWho());

                try {
                    // insert into database
                    payrollSkalaGajiPensiunRniHistoryDao.addAndSave(historyEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollTunjanganJabatanStruktural, please info to your admin..." + e.getMessage());
                }

                imPayrollSkalaGajiPensiunEntity.setNilai(bean.getNilai());
                imPayrollSkalaGajiPensiunEntity.setPoin(bean.getPoin());
                imPayrollSkalaGajiPensiunEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiPensiunEntity.setAction(bean.getAction());
                imPayrollSkalaGajiPensiunEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiPensiunEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    payrollSkalaGajiPensiunRniDao.updateAndSave(imPayrollSkalaGajiPensiunEntity);
                    //payrollSkalaGajiPensiunRniDao.addAndSaveHistory(imPayrollSkalaGajiPensiunHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiPensiunBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollSkalaGajiPensiun, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollSkalaGajiPensiunBoImpl.saveEdit] Error, not found data PayrollSkalaGajiPensiun with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollSkalaGajiPensiun with request id, please check again your data ...");
//                condition = "Error, not found data PayrollSkalaGajiPensiun with request id, please check again your data ...";
            }
        }
        logger.info("[PayrollSkalaGajiPensiunBoImpl.saveEdit] end process <<<");
    }

    @Override
    public payrollSkalaGajiPensiunRni saveAdd(payrollSkalaGajiPensiunRni bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiPensiunBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String status = cekStatus(bean.getGolonganId(),bean.getPoin(),bean.getTipeDapenId());
            if (!status.equalsIgnoreCase("Exist")){
                String payrollSkalaGajiPensiunId;
                try {
                    // Generating ID, get from postgre sequence
                    payrollSkalaGajiPensiunId = payrollSkalaGajiPensiunRniDao.getNextSkalaGajiPensiun();
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiPensiunBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollSkalaGajiPensiunId id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImPayrollSkalaGajiPensiunRniEntity imPayrollSkalaGajiPensiunEntity = new ImPayrollSkalaGajiPensiunRniEntity();

                imPayrollSkalaGajiPensiunEntity.setSkalaGajiPensiunId(payrollSkalaGajiPensiunId);
                imPayrollSkalaGajiPensiunEntity.setGolonganId(bean.getGolonganId());
                imPayrollSkalaGajiPensiunEntity.setTipeDapenId(bean.getTipeDapenId());
                imPayrollSkalaGajiPensiunEntity.setPoin(bean.getPoin());
                imPayrollSkalaGajiPensiunEntity.setNilai(bean.getNilai());
                imPayrollSkalaGajiPensiunEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiPensiunEntity.setAction(bean.getAction());
                imPayrollSkalaGajiPensiunEntity.setCreatedWho(bean.getCreatedWho());
                imPayrollSkalaGajiPensiunEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiPensiunEntity.setCreatedDate(bean.getCreatedDate());
                imPayrollSkalaGajiPensiunEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    payrollSkalaGajiPensiunRniDao.addAndSave(imPayrollSkalaGajiPensiunEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiPensiunBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollSkalaGajiPensiun, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Data dengan Level dan Masa Golongan Tersebut Sudah Ada");
            }
        }

        logger.info("[PayrollSkalaGajiPensiunBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<payrollSkalaGajiPensiunRni> getByCriteria(payrollSkalaGajiPensiunRni searchBean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<payrollSkalaGajiPensiunRni> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getSkalaGajiPensiunId() != null && !"".equalsIgnoreCase(searchBean.getSkalaGajiPensiunId())) {
                hsCriteria.put("skalaGajiPensiunId", searchBean.getSkalaGajiPensiunId());
            }
            if (searchBean.getGolonganId() != null && !"".equalsIgnoreCase(searchBean.getGolonganId())) {
                hsCriteria.put("golonganId", searchBean.getGolonganId());
            }
            if (searchBean.getTipeDapenId() != null && !"".equalsIgnoreCase(searchBean.getTipeDapenId())) {
                hsCriteria.put("tipeDapenId", searchBean.getTipeDapenId());
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


            List<ImPayrollSkalaGajiPensiunRniEntity> imPayrollSkalaGajiEntity = null;
            try {

                imPayrollSkalaGajiEntity = payrollSkalaGajiPensiunRniDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollSkalaGajiEntity != null){
                payrollSkalaGajiPensiunRni returnPayrollSkalaGaji;
                // Looping from dao to object and save in collection
                for(ImPayrollSkalaGajiPensiunRniEntity payrollSkalaGajiEntity : imPayrollSkalaGajiEntity){
                    returnPayrollSkalaGaji = new payrollSkalaGajiPensiunRni();
                    returnPayrollSkalaGaji.setSkalaGajiPensiunId(payrollSkalaGajiEntity.getSkalaGajiPensiunId());
                    returnPayrollSkalaGaji.setGolonganId(payrollSkalaGajiEntity.getGolonganId());
                    if(payrollSkalaGajiEntity.getGolonganId() != null){
                        returnPayrollSkalaGaji.setGolonganName(payrollSkalaGajiEntity.getImGolonganDapenEntity().getGolonganDapenName());
                    }
                    if (payrollSkalaGajiEntity.getTipeDapenId()!=null){
                        ImPayrollDanaPensiunEntity danaPensiunEntity = payrollDanaPensiunDao.getById("danaPensiunId",payrollSkalaGajiEntity.getTipeDapenId());
                        returnPayrollSkalaGaji.setTipeDapenName(danaPensiunEntity.getDanaPensiun());
                    }

                    returnPayrollSkalaGaji.setNilai(payrollSkalaGajiEntity.getNilai());
                    returnPayrollSkalaGaji.setStNilai(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getNilai(), "###,###"));
                    returnPayrollSkalaGaji.setPoin(payrollSkalaGajiEntity.getPoin());

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
    public List<payrollSkalaGajiPensiunRni> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<payrollSkalaGajiPensiunRni> getComboPayrollSkalaGajiPensiunWithCriteria(String query) throws GeneralBOException {
        return null;
    }
    public String cekStatus(String golonganId, Integer masa,String dapenId)throws GeneralBOException{
        String status ="";
        List<ImPayrollSkalaGajiPensiunRniEntity> skalaGajiEntity = new ArrayList<>();
        try {
            skalaGajiEntity = payrollSkalaGajiPensiunRniDao.getSkalaGajiPensiunRni(golonganId, masa,dapenId);
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