package com.neurix.hris.master.payrollTunjanganStrategis.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.golongan.dao.GolonganDao;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;
import com.neurix.hris.master.kelompokPosition.dao.KelompokPositionDao;
import com.neurix.hris.master.kelompokPosition.model.ImKelompokPositionEntity;
import com.neurix.hris.master.profesi.dao.ProfesiDao;
import com.neurix.hris.master.profesi.model.ImProfesiEntity;
import com.neurix.hris.transaksi.payroll.dao.PayrollTunjanganStrategisDao;
import com.neurix.hris.master.payrollTunjanganStrategis.bo.PayrollTunjanganStrategisBo;
import com.neurix.hris.transaksi.payroll.dao.PayrollTunjanganStrategisHistoryDao;
import com.neurix.hris.transaksi.payroll.model.ImPayrollTunjanganStrategisEntity;
import com.neurix.hris.transaksi.payroll.model.ImPayrollTunjanganStrategisHistoryEntity;
import com.neurix.hris.transaksi.payroll.model.PayrollTunjanganStrategis;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.neurix.hris.master.payrollTunjanganStrategis.model.ImPayrollTunjanganStrategisHistoryEntity;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class PayrollTunjanganStrategisBoImpl implements PayrollTunjanganStrategisBo {

    protected static transient Logger logger = Logger.getLogger(PayrollTunjanganStrategisBoImpl.class);
    private PayrollTunjanganStrategisDao payrollTunjanganStrategisDao;
    private PositionDao positionDao;
    private KelompokPositionDao kelompokPositionDao;
    private BranchDao branchDao;
    private GolonganDao golonganDao;
    private PayrollTunjanganStrategisHistoryDao payrollTunjanganStrategisHistoryDao;
    private ProfesiDao profesiDao;

    public ProfesiDao getProfesiDao() {
        return profesiDao;
    }

    public void setProfesiDao(ProfesiDao profesiDao) {
        this.profesiDao = profesiDao;
    }

    public PayrollTunjanganStrategisHistoryDao getPayrollTunjanganStrategisHistoryDao() {
        return payrollTunjanganStrategisHistoryDao;
    }

    public void setPayrollTunjanganStrategisHistoryDao(PayrollTunjanganStrategisHistoryDao payrollTunjanganStrategisHistoryDao) {
        this.payrollTunjanganStrategisHistoryDao = payrollTunjanganStrategisHistoryDao;
    }

    public GolonganDao getGolonganDao() {
        return golonganDao;
    }

    public void setGolonganDao(GolonganDao golonganDao) {
        this.golonganDao = golonganDao;
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
        PayrollTunjanganStrategisBoImpl.logger = logger;
    }

    public PayrollTunjanganStrategisDao getPayrollTunjanganStrategisDao() {
        return payrollTunjanganStrategisDao;
    }


    public void setPayrollTunjanganStrategisDao(PayrollTunjanganStrategisDao payrollTunjanganStrategisDao) {
        this.payrollTunjanganStrategisDao = payrollTunjanganStrategisDao;
    }

    @Override
    public void saveDelete(PayrollTunjanganStrategis bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String payrollTunjanganStrategis = bean.getTunjStrategisId();

            ImPayrollTunjanganStrategisEntity imPayrollTunjanganStrategisEntity = null;
            ImPayrollTunjanganStrategisHistoryEntity historyEntity = new ImPayrollTunjanganStrategisHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollTunjanganStrategisEntity = payrollTunjanganStrategisDao.getById("tunjStrategisId", payrollTunjanganStrategis);
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganStrategisBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollTunjanganStrategisEntity != null) {
                //entity history
                String payrollTunjFungsionalId;
                try {
                    // Generating ID, get from postgre sequence
                    payrollTunjFungsionalId = payrollTunjanganStrategisHistoryDao.getNextTunjStrategisHistory();
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollTunjanganJabatanStruktural id, please info to your admin..." + e.getMessage());
                }
                historyEntity.setTunjStrategisHistoryId(payrollTunjFungsionalId);
                historyEntity.setTunjStrategisId(imPayrollTunjanganStrategisEntity.getTunjStrategisId());
                historyEntity.setPositionId(imPayrollTunjanganStrategisEntity.getPositionId());
                historyEntity.setNilai(imPayrollTunjanganStrategisEntity.getNilai());
                historyEntity.setGolonganId(imPayrollTunjanganStrategisEntity.getGolonganId());
                historyEntity.setCreatedDate(imPayrollTunjanganStrategisEntity.getLastUpdate());
                historyEntity.setCreatedWho(imPayrollTunjanganStrategisEntity.getLastUpdateWho());
                historyEntity.setLastUpdate(imPayrollTunjanganStrategisEntity.getLastUpdate());
                historyEntity.setLastUpdateWho(imPayrollTunjanganStrategisEntity.getLastUpdateWho());
                historyEntity.setFlag("Y");
                historyEntity.setAction(imPayrollTunjanganStrategisEntity.getAction());

                try {
                    // insert into database
                    payrollTunjanganStrategisHistoryDao.addAndSave(historyEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollTunjanganJabatanStruktural, please info to your admin..." + e.getMessage());
                }

                // Modify from bean to entity serializable
                imPayrollTunjanganStrategisEntity.setFlag(bean.getFlag());
                imPayrollTunjanganStrategisEntity.setAction(bean.getAction());
                imPayrollTunjanganStrategisEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollTunjanganStrategisEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    payrollTunjanganStrategisDao.updateAndSave(imPayrollTunjanganStrategisEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganStrategisBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollTunjanganStrategis, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PayrollTunjanganStrategisBoImpl.saveDelete] Error, not found data PayrollTunjanganStrategis with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollTunjanganStrategis with request id, please check again your data ...");

            }
        }
        logger.info("[PayrollTunjanganStrategisBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(PayrollTunjanganStrategis bean) throws GeneralBOException {
        logger.info("[PayrollTunjanganStrategisBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String historyId = "";
            String payrollTunjanganStrategis = bean.getTunjStrategisId();

            ImPayrollTunjanganStrategisEntity imPayrollTunjanganStrategisEntity = null;
            ImPayrollTunjanganStrategisHistoryEntity historyEntity = new ImPayrollTunjanganStrategisHistoryEntity();
//            ImPayrollTunjanganStrategisHistoryEntity imPayrollTunjanganStrategisHistoryEntity = new ImPayrollTunjanganStrategisHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollTunjanganStrategisEntity = payrollTunjanganStrategisDao.getById("tunjStrategisId", payrollTunjanganStrategis);
                //historyId = payrollTunjanganStrategisDao.getNextSkalaGaji();
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganStrategisBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollTunjanganStrategis by Kode PayrollTunjanganStrategis, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollTunjanganStrategisEntity != null) {
                //entity history
                String payrollTunjFungsionalId;
                try {
                    // Generating ID, get from postgre sequence
                    payrollTunjFungsionalId = payrollTunjanganStrategisHistoryDao.getNextTunjStrategisHistory();
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollTunjanganJabatanStruktural id, please info to your admin..." + e.getMessage());
                }
                historyEntity.setTunjStrategisHistoryId(payrollTunjFungsionalId);
                historyEntity.setTunjStrategisId(imPayrollTunjanganStrategisEntity.getTunjStrategisId());
                historyEntity.setPositionId(imPayrollTunjanganStrategisEntity.getPositionId());
                historyEntity.setNilai(imPayrollTunjanganStrategisEntity.getNilai());
                historyEntity.setGolonganId(imPayrollTunjanganStrategisEntity.getGolonganId());
                historyEntity.setCreatedDate(imPayrollTunjanganStrategisEntity.getLastUpdate());
                historyEntity.setCreatedWho(imPayrollTunjanganStrategisEntity.getLastUpdateWho());
                historyEntity.setLastUpdate(imPayrollTunjanganStrategisEntity.getLastUpdate());
                historyEntity.setLastUpdateWho(imPayrollTunjanganStrategisEntity.getLastUpdateWho());
                historyEntity.setFlag("Y");
                historyEntity.setAction(imPayrollTunjanganStrategisEntity.getAction());

                try {
                    // insert into database
                    payrollTunjanganStrategisHistoryDao.addAndSave(historyEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollTunjanganJabatanStruktural, please info to your admin..." + e.getMessage());
                }

                /*imPayrollTunjanganStrategisHistoryEntity.setId(historyId);
                imPayrollTunjanganStrategisHistoryEntity.setPayrollTunjanganStrategis(imPayrollTunjanganStrategisEntity.getPayrollTunjanganStrategis());
                imPayrollTunjanganStrategisHistoryEntity.setPayrollTunjanganStrategisName(imPayrollTunjanganStrategisEntity.getPayrollTunjanganStrategisName());
                imPayrollTunjanganStrategisHistoryEntity.setFlag(imPayrollTunjanganStrategisEntity.getFlag());
                imPayrollTunjanganStrategisHistoryEntity.setAction(imPayrollTunjanganStrategisEntity.getAction());
                imPayrollTunjanganStrategisHistoryEntity.setLastUpdateWho(imPayrollTunjanganStrategisEntity.getLastUpdateWho());
                imPayrollTunjanganStrategisHistoryEntity.setLastUpdate(imPayrollTunjanganStrategisEntity.getLastUpdate());
                imPayrollTunjanganStrategisHistoryEntity.setCreatedWho(imPayrollTunjanganStrategisEntity.getLastUpdateWho());
                imPayrollTunjanganStrategisHistoryEntity.setCreatedDate(imPayrollTunjanganStrategisEntity.getLastUpdate());*/

//                imPayrollTunjanganStrategisEntity.setNilai();
                imPayrollTunjanganStrategisEntity.setTunjStrategisId(payrollTunjanganStrategis);
                imPayrollTunjanganStrategisEntity.setPositionId(bean.getProfesiId());
                if (bean.getNilai() != null){
                    imPayrollTunjanganStrategisEntity.setNilai(bean.getNilai());
                }else {
                    imPayrollTunjanganStrategisEntity.setNilai(BigDecimal.valueOf(0));
                }
                imPayrollTunjanganStrategisEntity.setGolonganId(bean.getGolonganId());
                imPayrollTunjanganStrategisEntity.setFlag(bean.getFlag());
                imPayrollTunjanganStrategisEntity.setAction(bean.getAction());
                imPayrollTunjanganStrategisEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollTunjanganStrategisEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    payrollTunjanganStrategisDao.updateAndSave(imPayrollTunjanganStrategisEntity);
                    //payrollTunjanganStrategisDao.addAndSaveHistory(imPayrollTunjanganStrategisHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganStrategisBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollTunjanganStrategis, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PayrollTunjanganStrategisBoImpl.saveEdit] Error, not found data PayrollTunjanganStrategis with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollTunjanganStrategis with request id, please check again your data ...");
            }
        }
        logger.info("[PayrollTunjanganStrategisBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PayrollTunjanganStrategis saveAdd(PayrollTunjanganStrategis bean) throws GeneralBOException {
        logger.info("[PayrollTunjanganStrategisBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String status = cekStatus(bean.getProfesiId(), bean.getGolonganId());
            if (!status.equalsIgnoreCase("Exist")){
                String payrollTunjanganStrategis;
                try {
                    // Generating ID, get from postgre sequence
                    payrollTunjanganStrategis = payrollTunjanganStrategisDao.getNextTunjStrategis();
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganStrategisBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollTunjanganStrategis id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImPayrollTunjanganStrategisEntity imPayrollTunjanganStrategisEntity = new ImPayrollTunjanganStrategisEntity();

                imPayrollTunjanganStrategisEntity.setTunjStrategisId(payrollTunjanganStrategis);
                imPayrollTunjanganStrategisEntity.setPositionId(bean.getProfesiId());
                if (bean.getNilai() != null){
                    imPayrollTunjanganStrategisEntity.setNilai(bean.getNilai());
                }else {
                    imPayrollTunjanganStrategisEntity.setNilai(BigDecimal.valueOf(0));
                }
                imPayrollTunjanganStrategisEntity.setGolonganId(bean.getGolonganId());
                imPayrollTunjanganStrategisEntity.setFlag(bean.getFlag());
                imPayrollTunjanganStrategisEntity.setAction(bean.getAction());
                imPayrollTunjanganStrategisEntity.setCreatedWho(bean.getCreatedWho());
                imPayrollTunjanganStrategisEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPayrollTunjanganStrategisEntity.setCreatedDate(bean.getCreatedDate());
                imPayrollTunjanganStrategisEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    payrollTunjanganStrategisDao.addAndSave(imPayrollTunjanganStrategisEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganStrategisBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollTunjanganStrategis, please info to your admin..." + e.getMessage());
                }
            }else {
                throw new GeneralBOException("Maaf Data dengan Jabatan dan level tersebut sudah ada");
            }
        }

        logger.info("[PayrollTunjanganStrategisBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<PayrollTunjanganStrategis> getByCriteria(PayrollTunjanganStrategis searchBean) throws GeneralBOException {
        logger.info("[PayrollTunjanganStrategisBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PayrollTunjanganStrategis> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTunjStrategisId() != null && !"".equalsIgnoreCase(searchBean.getTunjStrategisId())) {
                hsCriteria.put("tunj_strategis_id", searchBean.getTunjStrategisId());
            }if (searchBean.getProfesiId() != null && !"".equalsIgnoreCase(searchBean.getProfesiId())) {
                hsCriteria.put("position_id", searchBean.getProfesiId());
            }if (searchBean.getGolonganId() != null && !"".equalsIgnoreCase(searchBean.getGolonganId())){
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

            List<ImPayrollTunjanganStrategisEntity> imPayrollTunjanganStrategisEntity = null;
            try {
                imPayrollTunjanganStrategisEntity = payrollTunjanganStrategisDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganStrategisBoImpl.getSearchPayrollTunjanganStrategisByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollTunjanganStrategisEntity != null){
                PayrollTunjanganStrategis returnPayrollTunjanganStrategis;
                // Looping from dao to object and save in collection
                for(ImPayrollTunjanganStrategisEntity payrollTunjanganStrategisEntity : imPayrollTunjanganStrategisEntity){
                    returnPayrollTunjanganStrategis = new PayrollTunjanganStrategis();
                    returnPayrollTunjanganStrategis.setTunjStrategisId(payrollTunjanganStrategisEntity.getTunjStrategisId());
                    returnPayrollTunjanganStrategis.setGolonganId(payrollTunjanganStrategisEntity.getGolonganId());
                    returnPayrollTunjanganStrategis.setNilai(payrollTunjanganStrategisEntity.getNilai());
                    returnPayrollTunjanganStrategis.setStNilai(CommonUtil.numbericFormat(payrollTunjanganStrategisEntity.getNilai(), "###,###"));
//                    returnPayrollTunjanganStrategis.setPositionId(payrollTunjanganStrategisEntity.getPositionId());
                    returnPayrollTunjanganStrategis.setProfesiId(payrollTunjanganStrategisEntity.getPositionId());
                    if (payrollTunjanganStrategisEntity.getPositionId()!=null){
//                        ImPosition position = positionDao.getById("positionId",payrollTunjanganStrategisEntity.getPositionId());
//                        if (position!=null){
//                            returnPayrollTunjanganStrategis.setPositionName(position.getPositionName());
//                        }
                        ImProfesiEntity profesiEntity = profesiDao.getById("profesiId", payrollTunjanganStrategisEntity.getPositionId());
                        if (profesiEntity!= null)
                            returnPayrollTunjanganStrategis.setProfesiName(profesiEntity.getProfesiName());
                    }
                    if (payrollTunjanganStrategisEntity.getGolonganId() != null){
                        ImGolonganEntity golonganEntity = golonganDao.getById("golonganId",payrollTunjanganStrategisEntity.getGolonganId());
                        if (golonganEntity != null){
                            returnPayrollTunjanganStrategis.setGolonganName(golonganEntity.getGolonganName());
                        }
                    }
                    /*if(returnPayrollTunjanganStrategis.getGolonganId() != null){
                        try {
                            ImKelompokPositionEntity position = kelompokPositionDao.getById("kelompokId",payrollTunjanganStrategisEntity.getKelompokId());
                            if (position!=null){
                                returnPayrollTunjanganStrategis.setKelompokName(position.getKelompokName());
                            }
                        } catch (HibernateException e) {
                            logger.error("[PayrollTunjanganStrategisBoImpl.getSearchPayrollTunjanganStrategisByCriteria] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }
                    }*/

                    returnPayrollTunjanganStrategis.setCreatedWho(payrollTunjanganStrategisEntity.getCreatedWho());
                    returnPayrollTunjanganStrategis.setCreatedDate(payrollTunjanganStrategisEntity.getCreatedDate());
                    returnPayrollTunjanganStrategis.setLastUpdate(payrollTunjanganStrategisEntity.getLastUpdate());
                    returnPayrollTunjanganStrategis.setLastUpdateWho(payrollTunjanganStrategisEntity.getLastUpdateWho());

                    returnPayrollTunjanganStrategis.setAction(payrollTunjanganStrategisEntity.getAction());
                    returnPayrollTunjanganStrategis.setFlag(payrollTunjanganStrategisEntity.getFlag());
                    listOfResult.add(returnPayrollTunjanganStrategis);
                }
            }
        }
        logger.info("[PayrollTunjanganStrategisBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PayrollTunjanganStrategis> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public String cekStatus(String profesiId, String golonganId)throws GeneralBOException{
        String status ="";
        List<ImPayrollTunjanganStrategisEntity> skalaGajiEntity = new ArrayList<>();
        try {
            skalaGajiEntity = payrollTunjanganStrategisDao.getListPosition(profesiId, golonganId);
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
