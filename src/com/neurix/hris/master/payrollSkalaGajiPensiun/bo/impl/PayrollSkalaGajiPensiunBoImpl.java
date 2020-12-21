package com.neurix.hris.master.payrollSkalaGajiPensiun.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollDanaPensiun.dao.PayrollDanaPensiunDao;
import com.neurix.hris.master.payrollDanaPensiun.model.ImPayrollDanaPensiunEntity;
import com.neurix.hris.master.payrollSkalaGajiPensiun.bo.PayrollSkalaGajiPensiunBo;
import com.neurix.hris.master.payrollSkalaGajiPensiun.dao.PayrollSkalaGajiPensiunDao;
import com.neurix.hris.master.payrollSkalaGajiPensiun.dao.PayrollSkalaGajiPensiunHistoryDao;
import com.neurix.hris.master.payrollSkalaGajiPensiun.model.ImPayrollSkalaGajiPensiunEntity;
import com.neurix.hris.master.payrollSkalaGajiPensiun.model.ImPayrollSkalaGajiPensiunHistoryEntity;
import com.neurix.hris.master.payrollSkalaGajiPensiun.model.payrollSkalaGajiPensiun;
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
public class PayrollSkalaGajiPensiunBoImpl implements PayrollSkalaGajiPensiunBo {

    protected static transient Logger logger = Logger.getLogger(PayrollSkalaGajiPensiunBoImpl.class);
    private PayrollSkalaGajiPensiunDao payrollSkalaGajiPensiunDao;
    private PayrollSkalaGajiPensiunHistoryDao payrollSkalaGajiPensiunHistoryDao;
    private PayrollDanaPensiunDao payrollDanaPensiunDao;

    public PayrollDanaPensiunDao getPayrollDanaPensiunDao() {
        return payrollDanaPensiunDao;
    }

    public void setPayrollDanaPensiunDao(PayrollDanaPensiunDao payrollDanaPensiunDao) {
        this.payrollDanaPensiunDao = payrollDanaPensiunDao;
    }

    public PayrollSkalaGajiPensiunHistoryDao getPayrollSkalaGajiPensiunHistoryDao() {
        return payrollSkalaGajiPensiunHistoryDao;
    }

    public void setPayrollSkalaGajiPensiunHistoryDao(PayrollSkalaGajiPensiunHistoryDao payrollSkalaGajiPensiunHistoryDao) {
        this.payrollSkalaGajiPensiunHistoryDao = payrollSkalaGajiPensiunHistoryDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollSkalaGajiPensiunBoImpl.logger = logger;
    }

    public PayrollSkalaGajiPensiunDao getPayrollSkalaGajiPensiunDao() {
        return payrollSkalaGajiPensiunDao;
    }

    public void setPayrollSkalaGajiPensiunDao(PayrollSkalaGajiPensiunDao payrollSkalaGajiPensiunDao) {
        this.payrollSkalaGajiPensiunDao = payrollSkalaGajiPensiunDao;
    }

    @Override
    public void saveDelete(payrollSkalaGajiPensiun bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollSkalaGajiId = bean.getSkalaGajiPensiunId();

            ImPayrollSkalaGajiPensiunEntity imPayrollSkalaGajiEntity = null;
            ImPayrollSkalaGajiPensiunHistoryEntity historyEntity = new ImPayrollSkalaGajiPensiunHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollSkalaGajiEntity = payrollSkalaGajiPensiunDao.getById("skalaGajiPensiunId", payrollSkalaGajiId);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiEntity != null) {
                //entity history
                String payrollSkalaGajiDapenbun;
                try {
                    // Generating ID, get from postgre sequence
                    payrollSkalaGajiDapenbun = payrollSkalaGajiPensiunHistoryDao.getNextSkalaGajiPensiunHistory();
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiPensiun.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollTunjanganJabatanStruktural id, please info to your admin..." + e.getMessage());
                }
                historyEntity.setSkalaGajiPensiunIdHistory(payrollSkalaGajiDapenbun);
                historyEntity.setSkalaGajiPensiunId(imPayrollSkalaGajiEntity.getSkalaGajiPensiunId());
                historyEntity.setGolonganId(imPayrollSkalaGajiEntity.getGolonganId());
                historyEntity.setPoin(imPayrollSkalaGajiEntity.getPoin());
                historyEntity.setMasaKerjaGol(imPayrollSkalaGajiEntity.getMasaKerjaGol());
                historyEntity.setNilai(imPayrollSkalaGajiEntity.getNilai());
                historyEntity.setAction(imPayrollSkalaGajiEntity.getAction());
                historyEntity.setFlag("Y");
                historyEntity.setCreatedDate(imPayrollSkalaGajiEntity.getLastUpdate());
                historyEntity.setCreatedWho(imPayrollSkalaGajiEntity.getLastUpdateWho());
                historyEntity.setLastUpdate(imPayrollSkalaGajiEntity.getLastUpdate());
                historyEntity.setLastUpdateWho(imPayrollSkalaGajiEntity.getLastUpdateWho());

                try {
                    // insert into database
                    payrollSkalaGajiPensiunHistoryDao.addAndSave(historyEntity);
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
                    payrollSkalaGajiPensiunDao.updateAndSave(imPayrollSkalaGajiEntity);
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
    public void saveEdit(payrollSkalaGajiPensiun bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiPensiunBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            //String historyId = "";
            String payrollSkalaGajiPensiunId = bean.getSkalaGajiPensiunId();

            ImPayrollSkalaGajiPensiunEntity imPayrollSkalaGajiPensiunEntity = null;
            ImPayrollSkalaGajiPensiunHistoryEntity historyEntity = new ImPayrollSkalaGajiPensiunHistoryEntity();
            //ImPayrollSkalaGajiPensiunHistoryEntity imPayrollSkalaGajiPensiunHistoryEntity = new ImPayrollSkalaGajiPensiunHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollSkalaGajiPensiunEntity = payrollSkalaGajiPensiunDao.getById("skalaGajiPensiunId", payrollSkalaGajiPensiunId);
                //historyId = payrollSkalaGajiPensiunDao.getNextPayrollSkalaGajiPensiunHistoryId();
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiPensiunBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollSkalaGajiPensiun by Kode PayrollSkalaGajiPensiun, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiPensiunEntity != null) {
                //entity history
                String payrollSkalaGajiDapenbun;
                try {
                    // Generating ID, get from postgre sequence
                    payrollSkalaGajiDapenbun = payrollSkalaGajiPensiunHistoryDao.getNextSkalaGajiPensiunHistory();
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiPensiun.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollTunjanganJabatanStruktural id, please info to your admin..." + e.getMessage());
                }
                historyEntity.setSkalaGajiPensiunIdHistory(payrollSkalaGajiDapenbun);
                historyEntity.setSkalaGajiPensiunId(imPayrollSkalaGajiPensiunEntity.getSkalaGajiPensiunId());
                historyEntity.setGolonganId(imPayrollSkalaGajiPensiunEntity.getGolonganId());
                historyEntity.setPoin(imPayrollSkalaGajiPensiunEntity.getPoin());
                historyEntity.setMasaKerjaGol(imPayrollSkalaGajiPensiunEntity.getMasaKerjaGol());
                historyEntity.setNilai(imPayrollSkalaGajiPensiunEntity.getNilai());
                historyEntity.setAction(imPayrollSkalaGajiPensiunEntity.getAction());
                historyEntity.setFlag("Y");
                historyEntity.setCreatedDate(imPayrollSkalaGajiPensiunEntity.getLastUpdate());
                historyEntity.setCreatedWho(imPayrollSkalaGajiPensiunEntity.getLastUpdateWho());
                historyEntity.setLastUpdate(imPayrollSkalaGajiPensiunEntity.getLastUpdate());
                historyEntity.setLastUpdateWho(imPayrollSkalaGajiPensiunEntity.getLastUpdateWho());

                try {
                    // insert into database
                    payrollSkalaGajiPensiunHistoryDao.addAndSave(historyEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollTunjanganJabatanStruktural, please info to your admin..." + e.getMessage());
                }

                imPayrollSkalaGajiPensiunEntity.setNilai(bean.getNilai());
                imPayrollSkalaGajiPensiunEntity.setPoin(bean.getPoin());
                imPayrollSkalaGajiPensiunEntity.setMasaKerjaGol(bean.getMasaKerjaGol());
                imPayrollSkalaGajiPensiunEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiPensiunEntity.setAction(bean.getAction());
                imPayrollSkalaGajiPensiunEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiPensiunEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    payrollSkalaGajiPensiunDao.updateAndSave(imPayrollSkalaGajiPensiunEntity);
                    //payrollSkalaGajiPensiunDao.addAndSaveHistory(imPayrollSkalaGajiPensiunHistoryEntity);
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
    public payrollSkalaGajiPensiun saveAdd(payrollSkalaGajiPensiun bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiPensiunBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String status = cekStatus(bean.getGolonganId(),bean.getPoin(),bean.getTipeDapenId());
            if (!status.equalsIgnoreCase("Exist")){
                String payrollSkalaGajiPensiunId;
                try {
                    // Generating ID, get from postgre sequence
                    payrollSkalaGajiPensiunId = payrollSkalaGajiPensiunDao.getNextSkalaGajiPensiun();
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiPensiunBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollSkalaGajiPensiunId id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImPayrollSkalaGajiPensiunEntity imPayrollSkalaGajiPensiunEntity = new ImPayrollSkalaGajiPensiunEntity();

                imPayrollSkalaGajiPensiunEntity.setSkalaGajiPensiunId(payrollSkalaGajiPensiunId);
                imPayrollSkalaGajiPensiunEntity.setGolonganId(bean.getGolonganId());
                imPayrollSkalaGajiPensiunEntity.setTipeDapenId(bean.getTipeDapenId());
                imPayrollSkalaGajiPensiunEntity.setPoin(bean.getPoin());
                imPayrollSkalaGajiPensiunEntity.setMasaKerjaGol(bean.getMasaKerjaGol());
                imPayrollSkalaGajiPensiunEntity.setNilai(bean.getNilai());
                imPayrollSkalaGajiPensiunEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiPensiunEntity.setAction(bean.getAction());
                imPayrollSkalaGajiPensiunEntity.setCreatedWho(bean.getCreatedWho());
                imPayrollSkalaGajiPensiunEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiPensiunEntity.setCreatedDate(bean.getCreatedDate());
                imPayrollSkalaGajiPensiunEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    payrollSkalaGajiPensiunDao.addAndSave(imPayrollSkalaGajiPensiunEntity);
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
    public List<payrollSkalaGajiPensiun> getByCriteria(payrollSkalaGajiPensiun searchBean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<payrollSkalaGajiPensiun> listOfResult = new ArrayList();

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


            List<ImPayrollSkalaGajiPensiunEntity> imPayrollSkalaGajiEntity = null;
            try {

                imPayrollSkalaGajiEntity = payrollSkalaGajiPensiunDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollSkalaGajiEntity != null){
                payrollSkalaGajiPensiun returnPayrollSkalaGaji;
                // Looping from dao to object and save in collection
                for(ImPayrollSkalaGajiPensiunEntity payrollSkalaGajiEntity : imPayrollSkalaGajiEntity){
                    returnPayrollSkalaGaji = new payrollSkalaGajiPensiun();
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

                    returnPayrollSkalaGaji.setMasaKerjaGol(payrollSkalaGajiEntity.getMasaKerjaGol());

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
    public List<payrollSkalaGajiPensiun> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<payrollSkalaGajiPensiun> getComboPayrollSkalaGajiPensiunWithCriteria(String query) throws GeneralBOException {
        return null;
    }
    public String cekStatus(String golonganId, Integer masa,String dapenId)throws GeneralBOException{
        String status ="";
        List<ImPayrollSkalaGajiPensiunEntity> skalaGajiEntity = new ArrayList<>();
        try {
            skalaGajiEntity = payrollSkalaGajiPensiunDao.getSkalaGajiPensiun(golonganId, masa,dapenId);
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