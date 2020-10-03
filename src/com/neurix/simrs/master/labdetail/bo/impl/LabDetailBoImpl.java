package com.neurix.simrs.master.labdetail.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.lab.bo.LabBo;
import com.neurix.simrs.master.lab.model.Lab;
import com.neurix.simrs.master.labdetail.bo.LabDetailBo;
import com.neurix.simrs.master.labdetail.dao.LabDetailDao;
import com.neurix.simrs.master.labdetail.model.ImSimrsLabDetailEntity;
import com.neurix.simrs.master.labdetail.model.LabDetail;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LabDetailBoImpl implements LabDetailBo {

    protected static transient Logger logger = Logger.getLogger(com.neurix.simrs.master.labdetail.bo.impl.LabDetailBoImpl.class);
    private LabDetailDao labDetailDao;

    public static Logger getLogger() {
        return logger;
    }

    public void setLabDetailDao(LabDetailDao labDetailDao) {
        this.labDetailDao = labDetailDao;
    }

    @Override
    public void saveDelete(LabDetail bean) throws GeneralBOException {
        logger.info("[saveDelete.LabDetailBoImpl] start process >>>");

        if (bean!=null) {
            String idLabDetail = bean.getIdLabDetail();
            String status = cekBeforeDelete(idLabDetail);
            if (!status.equalsIgnoreCase("exist")){
                ImSimrsLabDetailEntity entity = null;

                try {
                    // Get data from database by ID
                    entity = labDetailDao.getById("idLabDetail", idLabDetail);
                } catch (HibernateException e) {
                    logger.error("[LabDetailBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data Lab by IdLabDetail, please inform to your admin...," + e.getMessage());
                }

                if (entity != null) {
                    // Modify from bean to entity serializable
                    entity.setIdLabDetail(bean.getIdLabDetail());
                    entity.setFlag(bean.getFlag());
                    entity.setAction(bean.getAction());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    entity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Delete (Edit) into database
                        labDetailDao.updateAndSave(entity);
                    } catch (HibernateException e) {
                        logger.error("[LabDetailBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Lab Detail, please info to your admin..." + e.getMessage());
                    }


                } else {
                    logger.error("[LabDetailBoImpl.saveDelete] Error, not found data Lab Detail with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data Lab with request id, please check again your data ...");
                }
            }else {
                throw new GeneralBOException("Maaf Data tidak dapat dihapus, karna masih digunakan pada data Transaksi");
            }
        }
        logger.info("[LabDetailBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(LabDetail bean) throws GeneralBOException {
        logger.info("[LabDetailBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String labDetailId = bean.getIdLabDetail();

            ImSimrsLabDetailEntity entity = null;
            try {
                // Get data from database by ID
                entity = labDetailDao.getById("idLabDetail", labDetailId);
                //historyId = payrollSkalaGajiDao.getNextSkalaGaji();
            } catch (HibernateException e) {
                logger.error("[LabDetailBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Lab Detail by IdLabDetail, please inform to your admin...," + e.getMessage());
            }

            if (entity != null) {
                if (bean.getNamaDetailPeriksa().equalsIgnoreCase(entity.getNamaDetailPeriksa())){
                    entity.setIdLabDetail(bean.getIdLabDetail());
                    entity.setIdLab(bean.getIdLab());
                    entity.setNamaDetailPeriksa(bean.getNamaDetailPeriksa());
                    entity.setSatuan(bean.getSatuan());
                    entity.setKetentuanAcuan(bean.getKetentuanAcuan());
                    entity.setTarif(bean.getTarif());
                    entity.setFlag(bean.getFlag());
                    entity.setAction(bean.getAction());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    entity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Update into database
                        labDetailDao.updateAndSave(entity);
                        //payrollSkalaGajiDao.addAndSaveHistory(imPayrollSkalaGajiHistoryEntity);
                    } catch (HibernateException e) {
                        logger.error("[LabDetailBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Lab Detail, please info to your admin..." + e.getMessage());
                    }
                }else {
                    String status = cekStatus(bean.getNamaDetailPeriksa());
                    if (!status.equalsIgnoreCase("exist")){
                        entity.setIdLabDetail(bean.getIdLabDetail());
                        entity.setIdLab(bean.getIdLab());
                        entity.setNamaDetailPeriksa(bean.getNamaDetailPeriksa());
                        entity.setSatuan(bean.getSatuan());
                        entity.setKetentuanAcuan(bean.getKetentuanAcuan());
                        entity.setTarif(bean.getTarif());
                        entity.setFlag(bean.getFlag());
                        entity.setAction(bean.getAction());
                        entity.setLastUpdateWho(bean.getLastUpdateWho());
                        entity.setLastUpdate(bean.getLastUpdate());

                        try {
                            // Update into database
                            labDetailDao.updateAndSave(entity);
                            //payrollSkalaGajiDao.addAndSaveHistory(imPayrollSkalaGajiHistoryEntity);
                        } catch (HibernateException e) {
                            logger.error("[LabDetailBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving update data Lab Detail, please info to your admin..." + e.getMessage());
                        }
                    }else {
                        throw new GeneralBOException("Maaf Data dengan Nama Detail Periksa Tersebut Sudah Ada");
                    }
                }
            } else {
                logger.error("[LabDetailBoImpl.saveEdit] Error, not found data Lab Detail with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Lab with request id, please check again your data ...");
            }
        }
        logger.info("[LabBoImpl.saveEdit] end process <<<");
    }

    @Override
    public LabDetail saveAdd(LabDetail bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String status = cekStatus(bean.getNamaDetailPeriksa());
            String labDetailId;
            if (!status.equalsIgnoreCase("exist")){
                try {
                    // Generating ID, get from postgre sequence
                    labDetailId = labDetailDao.getNextLabDetailId();
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollSkalaGajiId id, please info to your admin..." + e.getMessage());
                }
                // creating object entity serializable
                ImSimrsLabDetailEntity entity = new ImSimrsLabDetailEntity();
                entity.setIdLabDetail(labDetailId);
                entity.setIdLab(bean.getIdLab());
                entity.setNamaDetailPeriksa(bean.getNamaDetailPeriksa());
                entity.setSatuan(bean.getSatuan());
                entity.setKetentuanAcuan(bean.getKetentuanAcuan());
                entity.setTarif(bean.getTarif());
                entity.setFlag(bean.getFlag());
                entity.setAction(bean.getAction());
                entity.setCreatedWho(bean.getCreatedWho());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setCreatedDate(bean.getCreatedDate());
                entity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    labDetailDao.addAndSave(entity);
                } catch (HibernateException e) {
                    logger.error("[LabDetailBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Lab, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Data dengan Nama Detail Periksa Tersebut Sudah Ada");
            }
        }

        logger.info("[LabBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<LabDetail> getByCriteria(LabDetail bean) throws GeneralBOException {
        logger.info("[LabDetailBoImpl.getByCriteria] Start >>>>>>>");
        List<LabDetail> result = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdLabDetail() != null && !"".equalsIgnoreCase(bean.getIdLabDetail())) {
                hsCriteria.put("id_lab_detail", bean.getIdLabDetail());
            }
            if (bean.getIdLab() != null && !"".equalsIgnoreCase(bean.getIdLab())) {
                hsCriteria.put("id_lab", bean.getIdLab());
            }
            if (bean.getNamaDetailPeriksa() != null && !"".equalsIgnoreCase(bean.getNamaDetailPeriksa())){
                hsCriteria.put("nama_detail_periksa", bean.getNamaDetailPeriksa());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                if ("N".equalsIgnoreCase(bean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", bean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImSimrsLabDetailEntity> labDetailEntityList = new ArrayList<>();
            try {
                labDetailEntityList = labDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[LabDetailBoImpl.getByCriteria] error when get data lab detailt by get by criteria "+ e.getMessage());
            }

            if (!labDetailEntityList.isEmpty()){
                LabDetail labDetail;
                for (ImSimrsLabDetailEntity labDetailEntity : labDetailEntityList){
                    labDetail = new LabDetail();
                    labDetail.setIdLabDetail(labDetailEntity.getIdLabDetail());
                    labDetail.setIdLab(labDetailEntity.getIdLab());
                    labDetail.setNamaDetailPeriksa(labDetailEntity.getNamaDetailPeriksa());
                    labDetail.setSatuan(labDetailEntity.getSatuan());
                    labDetail.setKetentuanAcuan(labDetailEntity.getKetentuanAcuan());
                    labDetail.setFlag(labDetailEntity.getFlag());
                    labDetail.setAction(labDetailEntity.getAction());
                    labDetail.setCreatedDate(labDetailEntity.getCreatedDate());
                    labDetail.setStCreatedDate(labDetailEntity.getCreatedDate().toString());
                    labDetail.setCreatedWho(labDetailEntity.getCreatedWho());
                    labDetail.setStLastUpdate(labDetailEntity.getLastUpdate().toString());
                    labDetail.setLastUpdate(labDetailEntity.getLastUpdate());
                    labDetail.setLastUpdateWho(labDetailEntity.getLastUpdateWho());
                    if (labDetailEntity.getTarif() != null){
                        labDetail.setTarif(labDetailEntity.getTarif());
                        labDetail.setStTarif(CommonUtil.numbericFormat(labDetailEntity.getTarif(), "###,###"));
                    }

                    if (labDetailEntity.getIdLab() != null){
                        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                        Lab lab = new Lab();
                        LabBo labBo = (LabBo) context.getBean("labBoProxy");
                        lab.setIdLab(labDetailEntity.getIdLab());
                        lab.setFlag("Y");
                        List<Lab> labs = labBo.getByCriteria(lab);
                        if(labs.size() > 0){
                            String labName = labs.get(0).getNamaLab();
                            labDetail.setNamaLab(labName);
                        }
                    }

                    result.add(labDetail);
                }
            }
        }
        logger.info("[LabDetailBoImpl.getByCriteria] End <<<<<<<");
        return result;
    }

    @Override
    public ImSimrsLabDetailEntity getLabDetailEntityById(String idParameter) throws GeneralBOException {
        return labDetailDao.getById("idLabDetail", idParameter);
    }

    @Override
    public List<LabDetail> getDetaillab(String idLab, String branchId) throws GeneralBOException {
        List<LabDetail> labDetails = new ArrayList<>();
        logger.info("[LabDetailBoImpl.getDetaillab] Start <<<<<<<");
        try {
            labDetails = labDetailDao.getDetailLab(idLab, branchId);
        } catch (HibernateException e) {
            logger.error("[LabDetailBoImpl.getDetaillab] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        logger.info("[LabDetailBoImpl.getDetaillab] End <<<<<<<");
        return labDetails;
    }

    @Override
    public List<LabDetail> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public String cekStatus(String namaDetailPeriksa)throws GeneralBOException{
        String status ="";
        List<ImSimrsLabDetailEntity> entities = new ArrayList<>();
        try {
            entities = labDetailDao.getDataLabDetail(namaDetailPeriksa);
        } catch (HibernateException e) {
            logger.error("[LabDetailBoImpl.cekStatus] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }

    public String cekBeforeDelete(String idLabDetail)throws GeneralBOException{
        String status ="";
        List<ImSimrsLabDetailEntity> entities = new ArrayList<>();
        try {
            entities = labDetailDao.cekData(idLabDetail);
        } catch (HibernateException e) {
            logger.error("[LabDetailBoImpl.cekBeforeDelete] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }
}