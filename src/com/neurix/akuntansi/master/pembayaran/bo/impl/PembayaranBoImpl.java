package com.neurix.akuntansi.master.pembayaran.bo.impl;

import com.neurix.akuntansi.master.pembayaran.bo.PembayaranBo;
import com.neurix.akuntansi.master.pembayaran.dao.PembayaranDao;
import com.neurix.akuntansi.master.pembayaran.model.ImAkunPembayaranEntity;
import com.neurix.akuntansi.master.pembayaran.model.Pembayaran;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PembayaranBoImpl implements PembayaranBo {
    protected static transient Logger logger = Logger.getLogger(PembayaranBoImpl.class);
    private PembayaranDao pembayaranDao;

    public PembayaranDao getPembayaranDao() {
        return pembayaranDao;
    }

    public void setPembayaranDao(PembayaranDao pembayaranDao) {
        this.pembayaranDao = pembayaranDao;
    }

    @Override
    public void saveDelete(Pembayaran bean) throws GeneralBOException {
        logger.info("[saveDelete.PembayaranBoImpl] start process >>>");

        if (bean!=null) {

            String idPembayaran = bean.getPembayaranId();

            ImAkunPembayaranEntity entity = null;

            try {
                // Get data from database by ID
                entity = pembayaranDao.getById("pembayaranId", idPembayaran);
            } catch (HibernateException e) {
                logger.error("[PembayaranBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Akun Pembayaran by idPembayaran, please inform to your admin...," + e.getMessage());
            }

            if (entity != null) {
                // Modify from bean to entity serializable
                entity.setPembayaranId(bean.getPembayaranId());
                entity.setFlag(bean.getFlag());
                entity.setAction(bean.getAction());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    pembayaranDao.updateAndSave(entity);
                } catch (HibernateException e) {
                    logger.error("[PembayaranBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Lab Detail, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PembayaranBoImpl.saveDelete] Error, not found data Lab Detail with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Lab with request id, please check again your data ...");
            }
        }
        logger.info("[PembayaranBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Pembayaran bean) throws GeneralBOException {
        logger.info("[PembayaranBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String pembayaranId = bean.getPembayaranId();

            ImAkunPembayaranEntity entity = null;
            try {
                // Get data from database by ID
                entity = pembayaranDao.getById("pembayaranId", pembayaranId);
                //historyId = payrollSkalaGajiDao.getNextSkalaGaji();
            } catch (HibernateException e) {
                logger.error("[PembayaranBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data akunPembayaran by pembayaranId, please inform to your admin...," + e.getMessage());
            }

            if (entity != null) {
                entity.setPembayaranId(bean.getPembayaranId());
                entity.setPembayaranName(bean.getPembayaranName());
                entity.setCoa(bean.getCoa());
                entity.setFlag(bean.getFlag());
                entity.setAction(bean.getAction());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    pembayaranDao.updateAndSave(entity);
                    //payrollSkalaGajiDao.addAndSaveHistory(imPayrollSkalaGajiHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[PembayaranBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Akun Pembayaran, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PembayaranBoImpl.saveEdit] Error, not found data Akun Pembayaran with pembayaran id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Lab with request id, please check again your data ...");
            }
        }
        logger.info("[PembayaranBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Pembayaran saveAdd(Pembayaran bean) throws GeneralBOException {
        logger.info("[PembayaranBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String status = cekStatus(bean.getPembayaranName());
            String pembayaranId;
            if (!status.equalsIgnoreCase("exist")){
                try {
                    // Generating ID, get from postgre sequence
                    pembayaranId = pembayaranDao.getNextLabDetailId();
                } catch (HibernateException e) {
                    logger.error("[PembayaranBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence pembayaranId id, please info to your admin..." + e.getMessage());
                }
                // creating object entity serializable
                ImAkunPembayaranEntity entity = new ImAkunPembayaranEntity();
                entity.setPembayaranId(pembayaranId);
                entity.setPembayaranName(bean.getPembayaranName());
                entity.setCoa(bean.getCoa());
                entity.setFlag(bean.getFlag());
                entity.setAction(bean.getAction());
                entity.setCreatedWho(bean.getCreatedWho());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setCreatedDate(bean.getCreatedDate());
                entity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    pembayaranDao.addAndSave(entity);
                } catch (HibernateException e) {
                    logger.error("[PembayaranImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Lab, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Data dengan Nama Akun Pembayaran Tersebut Sudah Ada");
            }
        }

        logger.info("[PembayaranBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Pembayaran> getByCriteria(Pembayaran bean) throws GeneralBOException {
        logger.info("[PembayaranBoImpl.getByCriteria] Start >>>>>>>");
        List<Pembayaran> result = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getPembayaranId() != null && !"".equalsIgnoreCase(bean.getPembayaranId())) {
                hsCriteria.put("pembayaran_id", bean.getPembayaranId());
            }
            if (bean.getPembayaranName() != null && !"".equalsIgnoreCase(bean.getPembayaranName())) {
                hsCriteria.put("pembayaran_name", bean.getPembayaranName());
            }
            if (bean.getCoa() != null && !"".equalsIgnoreCase(bean.getCoa())){
                hsCriteria.put("coa", bean.getCoa());
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

            List<ImAkunPembayaranEntity> entities = new ArrayList<>();
            try {
                entities = pembayaranDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PembayaranBoImpl.getByCriteria] error when get data Pembayaran by get by criteria "+ e.getMessage());
            }

            if (!entities.isEmpty()){
                Pembayaran pembayaran;
                for (ImAkunPembayaranEntity entity : entities){
                    pembayaran = new Pembayaran();
                    pembayaran.setPembayaranId(entity.getPembayaranId());
                    pembayaran.setPembayaranName(entity.getPembayaranName());
                    pembayaran.setCoa(entity.getCoa());
                    pembayaran.setFlag(entity.getFlag());
                    pembayaran.setAction(entity.getAction());
                    pembayaran.setCreatedDate(entity.getCreatedDate());
                    pembayaran.setStCreatedDate(entity.getCreatedDate().toString());
                    pembayaran.setCreatedWho(entity.getCreatedWho());
                    pembayaran.setStLastUpdate(entity.getLastUpdate().toString());
                    pembayaran.setLastUpdate(entity.getLastUpdate());
                    pembayaran.setLastUpdateWho(entity.getLastUpdateWho());

                    result.add(pembayaran);
                }
            }
        }
        logger.info("[PembayaranBoImpl.getByCriteria] End <<<<<<<");
        return result;
    }

    @Override
    public List<Pembayaran> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public String cekStatus(String namaDetailPeriksa)throws GeneralBOException{
        String status ="";
        List<ImAkunPembayaranEntity> entities = new ArrayList<>();
        try {
            entities = pembayaranDao.getDataAkunPembayaran(namaDetailPeriksa);
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
}
