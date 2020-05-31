package com.neurix.hris.master.payrollSkalaGaji.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollSkalaGaji.bo.PayrollSkalaGajiBo;
import com.neurix.hris.master.payrollSkalaGaji.dao.PayrollSkalaGajiDao;
import com.neurix.hris.master.payrollSkalaGaji.model.PayrollSkalaGaji;
import com.neurix.hris.master.payrollSkalaGaji.model.ImPayrollSkalaGajiEntity;
import com.neurix.hris.master.payrollSkalaGaji.model.ImPayrollSkalaGajiHistoryEntity;
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
public class PayrollSkalaGajiBoImpl implements PayrollSkalaGajiBo {

    protected static transient Logger logger = Logger.getLogger(PayrollSkalaGajiBoImpl.class);
    private PayrollSkalaGajiDao payrollSkalaGajiDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollSkalaGajiBoImpl.logger = logger;
    }

    public PayrollSkalaGajiDao getPayrollSkalaGajiDao() {
        return payrollSkalaGajiDao;
    }


    public void setPayrollSkalaGajiDao(PayrollSkalaGajiDao payrollSkalaGajiDao) {
        this.payrollSkalaGajiDao = payrollSkalaGajiDao;
    }

    @Override
    public void saveDelete(PayrollSkalaGaji bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollSkalaGajiId = bean.getSkalaGajiId();

            ImPayrollSkalaGajiEntity imPayrollSkalaGajiEntity = null;

            try {
                // Get data from database by ID
                imPayrollSkalaGajiEntity = payrollSkalaGajiDao.getById("skalaGajiId", payrollSkalaGajiId);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiEntity != null) {

                // Modify from bean to entity serializable
                imPayrollSkalaGajiEntity.setSkalaGajiId(bean.getSkalaGajiId());
                imPayrollSkalaGajiEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiEntity.setAction(bean.getAction());
                imPayrollSkalaGajiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollSkalaGajiDao.updateAndSave(imPayrollSkalaGajiEntity);
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
    public void saveEdit(PayrollSkalaGaji bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String historyId = "";
            String payrollSkalaGajiId = bean.getSkalaGajiId();

            ImPayrollSkalaGajiEntity imPayrollSkalaGajiEntity = null;
            ImPayrollSkalaGajiHistoryEntity imPayrollSkalaGajiHistoryEntity = new ImPayrollSkalaGajiHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollSkalaGajiEntity = payrollSkalaGajiDao.getById("skalaGajiId", payrollSkalaGajiId);
                //historyId = payrollSkalaGajiDao.getNextSkalaGaji();
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollSkalaGaji by Kode PayrollSkalaGaji, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiEntity != null) {
                imPayrollSkalaGajiEntity.setSkalaGajiId(bean.getSkalaGajiId());
                imPayrollSkalaGajiEntity.setNilai(bean.getNilai());
                imPayrollSkalaGajiEntity.setSantunanKhusus(bean.getSantunanKhusus());
                imPayrollSkalaGajiEntity.setRumah(bean.getRumah());
                imPayrollSkalaGajiEntity.setListrik(bean.getListrik());
                imPayrollSkalaGajiEntity.setAir(bean.getAir());
                imPayrollSkalaGajiEntity.setBbm(bean.getBbm());
                imPayrollSkalaGajiEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiEntity.setAction(bean.getAction());
                imPayrollSkalaGajiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollSkalaGajiDao.updateAndSave(imPayrollSkalaGajiEntity);
                    //payrollSkalaGajiDao.addAndSaveHistory(imPayrollSkalaGajiHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollSkalaGaji, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollSkalaGajiBoImpl.saveEdit] Error, not found data PayrollSkalaGaji with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollSkalaGaji with request id, please check again your data ...");
            }
        }
        logger.info("[PayrollSkalaGajiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PayrollSkalaGaji saveAdd(PayrollSkalaGaji bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String status = cekStatus(bean.getGolonganId(),bean.getTahun());
            String payrollSkalaGajiId;
            if (!status.equalsIgnoreCase("exist")){
                try {
                    // Generating ID, get from postgre sequence
                    payrollSkalaGajiId = payrollSkalaGajiDao.getNextSkalaGaji();
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollSkalaGajiId id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImPayrollSkalaGajiEntity imPayrollSkalaGajiEntity = new ImPayrollSkalaGajiEntity();

                imPayrollSkalaGajiEntity.setSkalaGajiId(payrollSkalaGajiId);
                imPayrollSkalaGajiEntity.setGolonganId(bean.getGolonganId());
                if (bean.getNilai()==null){
                    imPayrollSkalaGajiEntity.setNilai(BigDecimal.valueOf(0));
                }else{
                    imPayrollSkalaGajiEntity.setNilai(bean.getNilai());
                }
                if (bean.getSantunanKhusus()==null){
                    imPayrollSkalaGajiEntity.setSantunanKhusus(BigDecimal.valueOf(0));
                }else{
                    imPayrollSkalaGajiEntity.setSantunanKhusus(bean.getSantunanKhusus());
                }
                if (bean.getRumah()==null){
                    imPayrollSkalaGajiEntity.setRumah(BigDecimal.valueOf(0));
                }else{
                    imPayrollSkalaGajiEntity.setRumah(bean.getRumah());
                }
                if (bean.getListrik()==null){
                    imPayrollSkalaGajiEntity.setListrik(BigDecimal.valueOf(0));
                }else{
                    imPayrollSkalaGajiEntity.setListrik(bean.getListrik());
                }
                if (bean.getAir()==null){
                    imPayrollSkalaGajiEntity.setAir(BigDecimal.valueOf(0));
                }else{
                    imPayrollSkalaGajiEntity.setAir(bean.getAir());
                }
                if (bean.getBbm()==null){
                    imPayrollSkalaGajiEntity.setBbm(BigDecimal.valueOf(0));
                }else{
                    imPayrollSkalaGajiEntity.setBbm(bean.getBbm());
                }
                imPayrollSkalaGajiEntity.setTotal(imPayrollSkalaGajiEntity.getNilai().add(imPayrollSkalaGajiEntity.getSantunanKhusus())
                        .add(imPayrollSkalaGajiEntity.getRumah()).add(imPayrollSkalaGajiEntity.getListrik())
                        .add(imPayrollSkalaGajiEntity.getAir()).add(imPayrollSkalaGajiEntity.getBbm()));
                imPayrollSkalaGajiEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiEntity.setAction(bean.getAction());
                imPayrollSkalaGajiEntity.setCreatedWho(bean.getCreatedWho());
                imPayrollSkalaGajiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiEntity.setCreatedDate(bean.getCreatedDate());
                imPayrollSkalaGajiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    payrollSkalaGajiDao.addAndSave(imPayrollSkalaGajiEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollSkalaGaji, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Data dengan Level Tersebut Sudah Ada");
            }
        }

        logger.info("[PayrollSkalaGajiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<PayrollSkalaGaji> getByCriteria(PayrollSkalaGaji searchBean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PayrollSkalaGaji> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getSkalaGajiId() != null && !"".equalsIgnoreCase(searchBean.getSkalaGajiId())) {
                hsCriteria.put("skala_gaji_id", searchBean.getSkalaGajiId());
            }
            if (searchBean.getGolonganId() != null && !"".equalsIgnoreCase(searchBean.getGolonganId())) {
                hsCriteria.put("golongan_id", searchBean.getGolonganId());
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


            List<ImPayrollSkalaGajiEntity> imPayrollSkalaGajiEntity = null;
            try {

                imPayrollSkalaGajiEntity = payrollSkalaGajiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollSkalaGajiEntity != null){
                PayrollSkalaGaji returnPayrollSkalaGaji;
                // Looping from dao to object and save in collection
                for(ImPayrollSkalaGajiEntity payrollSkalaGajiEntity : imPayrollSkalaGajiEntity){
                    returnPayrollSkalaGaji = new PayrollSkalaGaji();
                    returnPayrollSkalaGaji.setSkalaGajiId(payrollSkalaGajiEntity.getSkalaGajiId());
                    returnPayrollSkalaGaji.setGolonganId(payrollSkalaGajiEntity.getGolonganId());
                    if(payrollSkalaGajiEntity.getGolonganId() != null){
                        returnPayrollSkalaGaji.setStLevel(String.valueOf(payrollSkalaGajiEntity.getImGolonganEntity().getLevel()));
                    }
                    returnPayrollSkalaGaji.setNilai(payrollSkalaGajiEntity.getNilai());
                    returnPayrollSkalaGaji.setStNilai(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getNilai(),"###,###"));
                    returnPayrollSkalaGaji.setSantunanKhusus(payrollSkalaGajiEntity.getSantunanKhusus());
                    returnPayrollSkalaGaji.setStSantunanKhusus(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getSantunanKhusus(),"###,###"));
                    returnPayrollSkalaGaji.setRumah(payrollSkalaGajiEntity.getRumah());
                    returnPayrollSkalaGaji.setStRumah(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getRumah(),"###,###"));
                    returnPayrollSkalaGaji.setListrik(payrollSkalaGajiEntity.getListrik());
                    returnPayrollSkalaGaji.setStListrik(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getListrik(),"###,###"));
                    returnPayrollSkalaGaji.setAir(payrollSkalaGajiEntity.getAir());
                    returnPayrollSkalaGaji.setStAir(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getAir(),"###,###"));
                    returnPayrollSkalaGaji.setBbm(payrollSkalaGajiEntity.getBbm());
                    returnPayrollSkalaGaji.setStBbm(CommonUtil.numbericFormat(payrollSkalaGajiEntity.getBbm(),"###,###"));

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
    public List<PayrollSkalaGaji> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
    public String cekStatus(String golonganId,String tahun)throws GeneralBOException{
        String status ="";
        List<ImPayrollSkalaGajiEntity> skalaGajiEntity = new ArrayList<>();
        try {
            skalaGajiEntity = payrollSkalaGajiDao.getDataSkalaGajiSimRs(golonganId,tahun);
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
