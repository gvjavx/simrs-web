package com.neurix.hris.master.payrollSkalaGajiBod.bo.impl;

import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.payrollSkalaGajiBod.bo.PayrollSkalaGajiBodBo;
import com.neurix.hris.master.payrollSkalaGajiBod.dao.PayrollSkalaGajiBodDao;
import com.neurix.hris.master.payrollSkalaGajiBod.model.ImPayrollSkalaGajiBodEntity;
import com.neurix.hris.master.payrollSkalaGajiBod.model.PayrollSkalaGajiBod;
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
public class PayrollSkalaGajiBodBoImpl implements PayrollSkalaGajiBodBo {

    protected static transient Logger logger = Logger.getLogger(PayrollSkalaGajiBodBoImpl.class);
    private PayrollSkalaGajiBodDao payrollSkalaGajiBodDao;
    private PositionDao positionDao;

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
        PayrollSkalaGajiBodBoImpl.logger = logger;
    }

    public PayrollSkalaGajiBodDao getPayrollSkalaGajiBodDao() {
        return payrollSkalaGajiBodDao;
    }

    public void setPayrollSkalaGajiBodDao(PayrollSkalaGajiBodDao payrollSkalaGajiBodDao) {
        this.payrollSkalaGajiBodDao = payrollSkalaGajiBodDao;
    }

    @Override
    public void saveDelete(PayrollSkalaGajiBod bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {
            ImPayrollSkalaGajiBodEntity imPayrollSkalaGajiEntity = null;
            try {
                // Get data from database by ID
                imPayrollSkalaGajiEntity = payrollSkalaGajiBodDao.getById("skalaGajiBodId", bean.getPayrollSkalaGajiBodId());
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiEntity != null) {

                // Modify from bean to entity serializable
                imPayrollSkalaGajiEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiEntity.setAction(bean.getAction());
                imPayrollSkalaGajiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollSkalaGajiBodDao.updateAndSave(imPayrollSkalaGajiEntity);
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
    public void saveEdit(PayrollSkalaGajiBod bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBodBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            ImPayrollSkalaGajiBodEntity imPayrollSkalaGajiBodEntity = null;
            try {
                // Get data from database by ID
                imPayrollSkalaGajiBodEntity = payrollSkalaGajiBodDao.getById("skalaGajiBodId", bean.getPayrollSkalaGajiBodId());
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBodBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollSkalaGajiBod by Kode PayrollSkalaGajiBod, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollSkalaGajiBodEntity != null) {

                imPayrollSkalaGajiBodEntity.setGajiBod(bean.getGajiBod());
                imPayrollSkalaGajiBodEntity.setTunjRumah(bean.getTunjRumah());
                imPayrollSkalaGajiBodEntity.setTunjTelekomunikasi(bean.getTunjTelekomunikasi());
                imPayrollSkalaGajiBodEntity.setJumlahPengasilanBulan(bean.getGajiBod().add(bean.getTunjRumah()).add(bean.getTunjTelekomunikasi()));
                imPayrollSkalaGajiBodEntity.setTahun(bean.getTahun());
                imPayrollSkalaGajiBodEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiBodEntity.setAction(bean.getAction());
                imPayrollSkalaGajiBodEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiBodEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    payrollSkalaGajiBodDao.updateAndSave(imPayrollSkalaGajiBodEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiBodBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollSkalaGajiBod, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollSkalaGajiBodBoImpl.saveEdit] Error, not found data PayrollSkalaGajiBod with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollSkalaGajiBod with request id, please check again your data ...");
            }
        }
        logger.info("[PayrollSkalaGajiBodBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PayrollSkalaGajiBod saveAdd(PayrollSkalaGajiBod bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBodBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String status = cekStatus(bean.getPositionId(),bean.getTahun());
            if (!status.equalsIgnoreCase("Exist")){
                String payrollSkalaGajiBodId;
                try {
                    // Generating ID, get from postgre sequence
                    payrollSkalaGajiBodId = payrollSkalaGajiBodDao.getNextSkalaGajiBod();
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiBodBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollSkalaGajiBodId id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImPayrollSkalaGajiBodEntity imPayrollSkalaGajiBodEntity = new ImPayrollSkalaGajiBodEntity();

                imPayrollSkalaGajiBodEntity.setPayrollSkalaGajiBodId(payrollSkalaGajiBodId);
                imPayrollSkalaGajiBodEntity.setPositionId(bean.getPositionId());
                imPayrollSkalaGajiBodEntity.setGajiBod(bean.getGajiBod());
                imPayrollSkalaGajiBodEntity.setTunjRumah(bean.getTunjRumah());
                imPayrollSkalaGajiBodEntity.setTunjTelekomunikasi(bean.getTunjTelekomunikasi());
                imPayrollSkalaGajiBodEntity.setJumlahPengasilanBulan(bean.getGajiBod().add(bean.getTunjRumah()).add(bean.getTunjTelekomunikasi()));
                imPayrollSkalaGajiBodEntity.setTahun(bean.getTahun());
                imPayrollSkalaGajiBodEntity.setFlag(bean.getFlag());
                imPayrollSkalaGajiBodEntity.setAction(bean.getAction());
                imPayrollSkalaGajiBodEntity.setCreatedWho(bean.getCreatedWho());
                imPayrollSkalaGajiBodEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollSkalaGajiBodEntity.setCreatedDate(bean.getCreatedDate());
                imPayrollSkalaGajiBodEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    payrollSkalaGajiBodDao.addAndSave(imPayrollSkalaGajiBodEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiBodBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollSkalaGajiBod, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Data dengan posisi Tersebut Sudah Ada");
            }
        }

        logger.info("[PayrollSkalaGajiBodBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<PayrollSkalaGajiBod> getByCriteria(PayrollSkalaGajiBod searchBean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PayrollSkalaGajiBod> listOfResult = new ArrayList();
        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPayrollSkalaGajiBodId() != null && !"".equalsIgnoreCase(searchBean.getPayrollSkalaGajiBodId())) {
                hsCriteria.put("skala_gaji_bod_id", searchBean.getPayrollSkalaGajiBodId());
            }
            if (searchBean.getPositionId() != null && !"".equalsIgnoreCase(searchBean.getPositionId())) {
                hsCriteria.put("position_id", searchBean.getPositionId());
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

            List<ImPayrollSkalaGajiBodEntity> imPayrollSkalaGajiEntity = null;
            try {

                imPayrollSkalaGajiEntity = payrollSkalaGajiBodDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollSkalaGajiEntity != null){
                // Looping from dao to object and save in collection
                for(ImPayrollSkalaGajiBodEntity payrollSkalaGajiEntity : imPayrollSkalaGajiEntity){
                    listOfResult.add(convertEntity(payrollSkalaGajiEntity));
                }
            }
        }
        logger.info("[PayrollSkalaGajiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PayrollSkalaGajiBod> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public String cekStatus(String positionId,String tahun)throws GeneralBOException{
        String status ="";
        List<ImPayrollSkalaGajiBodEntity> skalaGajiEntity = new ArrayList<>();
        try {
            skalaGajiEntity = payrollSkalaGajiBodDao.getSkalaGajiBod(positionId,tahun);
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

    private PayrollSkalaGajiBod convertEntity (ImPayrollSkalaGajiBodEntity payrollSkalaGajiBodEntity){
        PayrollSkalaGajiBod result = new PayrollSkalaGajiBod();
        result.setPayrollSkalaGajiBodId(payrollSkalaGajiBodEntity.getPayrollSkalaGajiBodId());
        result.setPositionId(payrollSkalaGajiBodEntity.getPositionId());
        result.setGajiBod(payrollSkalaGajiBodEntity.getGajiBod());
        result.setTunjRumah(payrollSkalaGajiBodEntity.getTunjRumah());
        result.setTunjTelekomunikasi(payrollSkalaGajiBodEntity.getTunjTelekomunikasi());
        result.setJumlahPengasilanBulan(payrollSkalaGajiBodEntity.getJumlahPengasilanBulan());
        result.setTahun(payrollSkalaGajiBodEntity.getTahun());
        if (result.getGajiBod()!=null){
            result.setStGajiBod(CommonUtil.numbericFormat(result.getGajiBod(),"###,###"));
        }
        if (result.getTunjRumah()!=null){
            result.setStTunjRumah(CommonUtil.numbericFormat(result.getTunjRumah(),"###,###"));
        }
        if (result.getTunjTelekomunikasi()!=null){
            result.setStTunjTelekomunikasi(CommonUtil.numbericFormat(result.getTunjTelekomunikasi(),"###,###"));
        }
        if (result.getJumlahPengasilanBulan()!=null){
            result.setStJumlahPenghasilanBulan(CommonUtil.numbericFormat(result.getJumlahPengasilanBulan(),"###,###"));
        }

        if (payrollSkalaGajiBodEntity.getPositionId()!=null){
            ImPosition position ;
            try {
                position = positionDao.getById("positionId",payrollSkalaGajiBodEntity.getPositionId());
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.convertEntity] Error, " + e.getMessage());
                throw new GeneralBOException("Error : " + e.getMessage());
            }
            result.setPositionName(position.getPositionName());
        }
        return result;
    }
}