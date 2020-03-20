package com.neurix.hris.master.payrollTunjanganJabatanStruktural.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.kelompokPosition.dao.KelompokPositionDao;
import com.neurix.hris.master.kelompokPosition.model.ImKelompokPositionEntity;
import com.neurix.hris.master.payrollTunjanganJabatanStruktural.bo.PayrollTunjanganJabatanStrukturalBo;
import com.neurix.hris.master.payrollTunjanganJabatanStruktural.dao.PayrollTunjanganJabatanStrukturalDao;
import com.neurix.hris.transaksi.payroll.model.ImPayrollTunjanganJabatanStrukturalEntity;
//import com.neurix.hris.master.payrollTunjanganJabatanStruktural.model.ImPayrollTunjanganJabatanStrukturalHistoryEntity;
import com.neurix.hris.transaksi.payroll.model.PayrollTunjanganJabatanStruktural;
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
    private KelompokPositionDao kelompokPositionDao;
    private BranchDao branchDao;

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

            String payrollTunjanganJabatanStruktural = bean.getTunjJabStrukturId();

            ImPayrollTunjanganJabatanStrukturalEntity imPayrollTunjanganJabatanStrukturalEntity = null;

            try {
                // Get data from database by ID
                imPayrollTunjanganJabatanStrukturalEntity = payrollTunjanganJabatanStrukturalDao.getById("tunjJabStrukturId", payrollTunjanganJabatanStruktural);
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPayrollTunjanganJabatanStrukturalEntity != null) {

                // Modify from bean to entity serializable
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
            String payrollTunjanganJabatanStruktural = bean.getTunjJabStrukturId();

            ImPayrollTunjanganJabatanStrukturalEntity imPayrollTunjanganJabatanStrukturalEntity = null;
//            ImPayrollTunjanganJabatanStrukturalHistoryEntity imPayrollTunjanganJabatanStrukturalHistoryEntity = new ImPayrollTunjanganJabatanStrukturalHistoryEntity();
            try {
                // Get data from database by ID
                imPayrollTunjanganJabatanStrukturalEntity = payrollTunjanganJabatanStrukturalDao.getById("tunjJabStrukturId", payrollTunjanganJabatanStruktural);
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

                imPayrollTunjanganJabatanStrukturalEntity.setTunjJabatan(bean.getTunjJabatan());
                imPayrollTunjanganJabatanStrukturalEntity.setTunjStruktural(bean.getTunjStruktural());

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
            String status = cekStatus(bean.getKelompokId());
            if (!status.equalsIgnoreCase("exist")){
                String payrollTunjanganJabatanStruktural;
                try {
                    // Generating ID, get from postgre sequence
                    payrollTunjanganJabatanStruktural = payrollTunjanganJabatanStrukturalDao.getNextTunjanganJabatanStruktural();
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollTunjanganJabatanStruktural id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImPayrollTunjanganJabatanStrukturalEntity imPayrollTunjanganJabatanStrukturalEntity = new ImPayrollTunjanganJabatanStrukturalEntity();

                imPayrollTunjanganJabatanStrukturalEntity.setTunjJabStrukturId(payrollTunjanganJabatanStruktural);
                imPayrollTunjanganJabatanStrukturalEntity.setKelompokId(bean.getKelompokId());
                imPayrollTunjanganJabatanStrukturalEntity.setTunjJabatan(bean.getTunjJabatan());
                imPayrollTunjanganJabatanStrukturalEntity.setTunjStruktural(bean.getTunjStruktural());
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
            }else{
                throw new GeneralBOException("Maaf Data dengan Kelompok Jabatan Tersebut Sudah Ada");
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

            if (searchBean.getTunjJabStrukturId() != null && !"".equalsIgnoreCase(searchBean.getTunjJabStrukturId())) {
                hsCriteria.put("tunjJabStrukturId", searchBean.getTunjJabStrukturId());
            }
            if (searchBean.getKelompokId() != null && !"".equalsIgnoreCase(searchBean.getKelompokId())) {
                hsCriteria.put("kelompokId", searchBean.getKelompokId());
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


            List<ImPayrollTunjanganJabatanStrukturalEntity> imPayrollTunjanganJabatanStrukturalEntity = null;
            try {
                imPayrollTunjanganJabatanStrukturalEntity = payrollTunjanganJabatanStrukturalDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.getSearchPayrollTunjanganJabatanStrukturalByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPayrollTunjanganJabatanStrukturalEntity != null){
                PayrollTunjanganJabatanStruktural returnPayrollTunjanganJabatanStruktural;
                // Looping from dao to object and save in collection
                for(ImPayrollTunjanganJabatanStrukturalEntity payrollTunjanganJabatanStrukturalEntity : imPayrollTunjanganJabatanStrukturalEntity){
                    returnPayrollTunjanganJabatanStruktural = new PayrollTunjanganJabatanStruktural();
                    returnPayrollTunjanganJabatanStruktural.setTunjJabStrukturId(payrollTunjanganJabatanStrukturalEntity.getTunjJabStrukturId());
                    returnPayrollTunjanganJabatanStruktural.setKelompokId(payrollTunjanganJabatanStrukturalEntity.getKelompokId());
                    returnPayrollTunjanganJabatanStruktural.setTunjJabatan(payrollTunjanganJabatanStrukturalEntity.getTunjJabatan());
                    returnPayrollTunjanganJabatanStruktural.setTunjStruktural(payrollTunjanganJabatanStrukturalEntity.getTunjStruktural());


                    if(returnPayrollTunjanganJabatanStruktural.getKelompokId() != null){
                        try {
                            ImKelompokPositionEntity position = kelompokPositionDao.getById("kelompokId",payrollTunjanganJabatanStrukturalEntity.getKelompokId());
                            if (position!=null){
                                returnPayrollTunjanganJabatanStruktural.setKelompokName(position.getKelompokName());
                            }
                        } catch (HibernateException e) {
                            logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.getSearchPayrollTunjanganJabatanStrukturalByCriteria] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }
                    }

                    returnPayrollTunjanganJabatanStruktural.setCreatedWho(payrollTunjanganJabatanStrukturalEntity.getCreatedWho());
                    returnPayrollTunjanganJabatanStruktural.setCreatedDate(payrollTunjanganJabatanStrukturalEntity.getCreatedDate());
                    returnPayrollTunjanganJabatanStruktural.setLastUpdate(payrollTunjanganJabatanStrukturalEntity.getLastUpdate());
                    returnPayrollTunjanganJabatanStruktural.setLastUpdateWho(payrollTunjanganJabatanStrukturalEntity.getLastUpdateWho());

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
    public String cekStatus(String golonganId)throws GeneralBOException{
        String status ="";
        List<ImPayrollTunjanganJabatanStrukturalEntity> skalaGajiEntity = new ArrayList<>();
        try {
            skalaGajiEntity = payrollTunjanganJabatanStrukturalDao.getData2(golonganId);
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
