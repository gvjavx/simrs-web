package com.neurix.hris.master.payrollSkalaGajiDplkPegawai.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.golonganDapen.dao.GolonganDapenDao;
import com.neurix.hris.master.golonganDapen.model.ImGolonganDapenEntity;
import com.neurix.hris.master.payrollSkalaGajiDplkPegawai.bo.PayrollSkalaGajiDplkPegawaiBo;
import com.neurix.hris.master.payrollSkalaGajiDplkPegawai.dao.PayrollSkalaGajiDplkPegawaiDao;
import com.neurix.hris.master.payrollSkalaGajiDplkPegawai.model.ImPayrollSkalaGajiDplkPegawaiEntity;
import com.neurix.hris.master.payrollSkalaGajiDplkPegawai.model.payrollSkalaGajiDplkPegawai;
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
public class PayrollSkalaGajiDplkPegawaiBoImpl implements PayrollSkalaGajiDplkPegawaiBo {

    protected static transient Logger logger = Logger.getLogger(PayrollSkalaGajiDplkPegawaiBoImpl.class);
    private PayrollSkalaGajiDplkPegawaiDao payrollSkalaGajiDplkPegawaiDao;
    private GolonganDapenDao golonganDapenDao;

    public GolonganDapenDao getGolonganDapenDao() {
        return golonganDapenDao;
    }

    public void setGolonganDapenDao(GolonganDapenDao golonganDapenDao) {
        this.golonganDapenDao = golonganDapenDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PayrollSkalaGajiDplkPegawaiBoImpl.logger = logger;
    }

    public PayrollSkalaGajiDplkPegawaiDao getPayrollSkalaGajiDplkPegawaiDao() {
        return payrollSkalaGajiDplkPegawaiDao;
    }


    public void setPayrollSkalaGajiDplkPegawaiDao(PayrollSkalaGajiDplkPegawaiDao payrollSkalaGajiDplkPegawaiDao) {
        this.payrollSkalaGajiDplkPegawaiDao = payrollSkalaGajiDplkPegawaiDao;
    }

    @Override
    public void saveDelete(payrollSkalaGajiDplkPegawai bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollSkalaGajiId = bean.getSkalaGajiPensiunId();

            ImPayrollSkalaGajiDplkPegawaiEntity imPayrollSkalaGajiEntity = null;

            try {
                // Get data from database by ID
                imPayrollSkalaGajiEntity = payrollSkalaGajiDplkPegawaiDao.getById("skalaGajiPensiunId", payrollSkalaGajiId);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiEntity != null) {

                // Modify from bean to entity serializable
                imPayrollSkalaGajiEntity.setSkalaGajiPensiunId(bean.getSkalaGajiPensiunId());
                imPayrollSkalaGajiEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiEntity.setAction(bean.getAction());
                imPayrollSkalaGajiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollSkalaGajiDplkPegawaiDao.updateAndSave(imPayrollSkalaGajiEntity);
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
    public void saveEdit(payrollSkalaGajiDplkPegawai bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiDplkPegawaiBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            //String historyId = "";
            String payrollSkalaGajiPensiunId = bean.getSkalaGajiPensiunId();

            ImPayrollSkalaGajiDplkPegawaiEntity imPayrollSkalaGajiDplkPegawaiEntity = null;
            //ImPayrollSkalaGajiPensiunHistoryEntity imPayrollSkalaGajiPensiunHistoryEntity = new ImPayrollSkalaGajiPensiunHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollSkalaGajiDplkPegawaiEntity = payrollSkalaGajiDplkPegawaiDao.getById("skalaGajiPensiunId", payrollSkalaGajiPensiunId);
                //historyId = payrollSkalaGajiDplkPegawaiDao.getNextPayrollSkalaGajiPensiunHistoryId();
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiDplkPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollSkalaGajiPensiun by Kode PayrollSkalaGajiPensiun, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiDplkPegawaiEntity != null) {
                /*imPayrollSkalaGajiPensiunHistoryEntity.setId(historyId);
                imPayrollSkalaGajiPensiunHistoryEntity.setPayrollSkalaGajiPensiunId(imPayrollSkalaGajiDplkPegawaiEntity.getPayrollSkalaGajiPensiunId());
                imPayrollSkalaGajiPensiunHistoryEntity.setPayrollSkalaGajiPensiunName(imPayrollSkalaGajiDplkPegawaiEntity.getPayrollSkalaGajiPensiunName());
                imPayrollSkalaGajiPensiunHistoryEntity.setFlag(imPayrollSkalaGajiDplkPegawaiEntity.getFlag());
                imPayrollSkalaGajiPensiunHistoryEntity.setAction(imPayrollSkalaGajiDplkPegawaiEntity.getAction());
                imPayrollSkalaGajiPensiunHistoryEntity.setLastUpdateWho(imPayrollSkalaGajiDplkPegawaiEntity.getLastUpdateWho());
                imPayrollSkalaGajiPensiunHistoryEntity.setLastUpdate(imPayrollSkalaGajiDplkPegawaiEntity.getLastUpdate());
                imPayrollSkalaGajiPensiunHistoryEntity.setCreatedWho(imPayrollSkalaGajiDplkPegawaiEntity.getLastUpdateWho());
                imPayrollSkalaGajiPensiunHistoryEntity.setCreatedDate(imPayrollSkalaGajiDplkPegawaiEntity.getLastUpdate());*/

                imPayrollSkalaGajiDplkPegawaiEntity.setSkalaGajiPensiunId(bean.getSkalaGajiPensiunId());
                imPayrollSkalaGajiDplkPegawaiEntity.setGolonganId(bean.getGolonganId());
                imPayrollSkalaGajiDplkPegawaiEntity.setPoin(bean.getPoin());
                imPayrollSkalaGajiDplkPegawaiEntity.setNilai(bean.getNilai());
                imPayrollSkalaGajiDplkPegawaiEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiDplkPegawaiEntity.setAction(bean.getAction());
                imPayrollSkalaGajiDplkPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiDplkPegawaiEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollSkalaGajiDplkPegawaiDao.updateAndSave(imPayrollSkalaGajiDplkPegawaiEntity);
                    //payrollSkalaGajiDplkPegawaiDao.addAndSaveHistory(imPayrollSkalaGajiPensiunHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiDplkPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollSkalaGajiPensiun, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollSkalaGajiDplkPegawaiBoImpl.saveEdit] Error, not found data PayrollSkalaGajiPensiun with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollSkalaGajiPensiun with request id, please check again your data ...");
//                condition = "Error, not found data PayrollSkalaGajiPensiun with request id, please check again your data ...";
            }
        }
        logger.info("[PayrollSkalaGajiDplkPegawaiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public payrollSkalaGajiDplkPegawai saveAdd(payrollSkalaGajiDplkPegawai bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiDplkPegawaiBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            List<ImPayrollSkalaGajiDplkPegawaiEntity> cekData = payrollSkalaGajiDplkPegawaiDao.getSkalaGajiPensiunSimRs(bean.getGolonganId());
            if(cekData.size()>0){
                logger.error("[PayrollSkalaGajiDplkPegawaiBoImpl.saveAdd] Data sudah tersedia, tidak bisa menambahkan data yg sama.");
                throw new GeneralBOException("Data sudah tersedia, tidak dapat menambahkan data yang sama");
            }else {
                String payrollSkalaGajiPensiunId;
                try {
                    // Generating ID, get from postgre sequence
                    payrollSkalaGajiPensiunId = payrollSkalaGajiDplkPegawaiDao.getNextSkalaGajiPensiun();
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiDplkPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollSkalaGajiPensiunId id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImPayrollSkalaGajiDplkPegawaiEntity imPayrollSkalaGajiDplkPegawaiEntity = new ImPayrollSkalaGajiDplkPegawaiEntity();

                imPayrollSkalaGajiDplkPegawaiEntity.setSkalaGajiPensiunId(payrollSkalaGajiPensiunId);
                imPayrollSkalaGajiDplkPegawaiEntity.setGolonganId(bean.getGolonganId());
                imPayrollSkalaGajiDplkPegawaiEntity.setPoin(bean.getPoin());
                imPayrollSkalaGajiDplkPegawaiEntity.setNilai(bean.getNilai());
                imPayrollSkalaGajiDplkPegawaiEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiDplkPegawaiEntity.setAction(bean.getAction());
                imPayrollSkalaGajiDplkPegawaiEntity.setCreatedWho(bean.getCreatedWho());
                imPayrollSkalaGajiDplkPegawaiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiDplkPegawaiEntity.setCreatedDate(bean.getCreatedDate());
                imPayrollSkalaGajiDplkPegawaiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    payrollSkalaGajiDplkPegawaiDao.addAndSave(imPayrollSkalaGajiDplkPegawaiEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiDplkPegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollSkalaGajiPensiun, please info to your admin..." + e.getMessage());
                }
            }
        }

        logger.info("[PayrollSkalaGajiDplkPegawaiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<payrollSkalaGajiDplkPegawai> getByCriteria(payrollSkalaGajiDplkPegawai searchBean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<payrollSkalaGajiDplkPegawai> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getSkalaGajiPensiunId() != null && !"".equalsIgnoreCase(searchBean.getSkalaGajiPensiunId())) {
                hsCriteria.put("skala_gaji_pensiun_id", searchBean.getSkalaGajiPensiunId());
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


            List<ImPayrollSkalaGajiDplkPegawaiEntity> imPayrollSkalaGajiEntity = null;
            try {

                imPayrollSkalaGajiEntity = payrollSkalaGajiDplkPegawaiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollSkalaGajiEntity != null){
                payrollSkalaGajiDplkPegawai returnPayrollSkalaGaji;
                // Looping from dao to object and save in collection
                for(ImPayrollSkalaGajiDplkPegawaiEntity payrollSkalaGajiEntity : imPayrollSkalaGajiEntity){
                    returnPayrollSkalaGaji = new payrollSkalaGajiDplkPegawai();
                    returnPayrollSkalaGaji.setSkalaGajiPensiunId(payrollSkalaGajiEntity.getSkalaGajiPensiunId());
                    returnPayrollSkalaGaji.setGolonganId(payrollSkalaGajiEntity.getGolonganId());
                    ImGolonganDapenEntity golonganDapenEntity = golonganDapenDao.getById("golonganDapenId",payrollSkalaGajiEntity.getGolonganId());
                    if (golonganDapenEntity!=null){
                        returnPayrollSkalaGaji.setGolonganName(golonganDapenEntity.getGolonganDapenName());
                    }

                    returnPayrollSkalaGaji.setNilai(payrollSkalaGajiEntity.getNilai());
                    returnPayrollSkalaGaji.setPoin(payrollSkalaGajiEntity.getPoin());

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
    public List<payrollSkalaGajiDplkPegawai> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<payrollSkalaGajiDplkPegawai> getComboPayrollSkalaGajiPensiunWithCriteria(String query) throws GeneralBOException {
        return null;
    }
}