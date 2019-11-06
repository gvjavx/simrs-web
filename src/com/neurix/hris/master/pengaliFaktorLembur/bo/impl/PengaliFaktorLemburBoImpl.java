package com.neurix.hris.master.pengaliFaktorLembur.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.pengaliFaktorLembur.bo.PengaliFaktorLemburBo;
import com.neurix.hris.master.pengaliFaktorLembur.dao.PengaliFaktorLemburDao;
import com.neurix.hris.transaksi.lembur.model.PengaliFaktorLembur;
import com.neurix.hris.transaksi.lembur.model.PengaliFaktorLemburEntity;
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
public class PengaliFaktorLemburBoImpl implements PengaliFaktorLemburBo {

    protected static transient Logger logger = Logger.getLogger(PengaliFaktorLemburBoImpl.class);
    private PengaliFaktorLemburDao pengaliFaktorLemburDao;


    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PengaliFaktorLemburBoImpl.logger = logger;
    }

    public PengaliFaktorLemburDao getPengaliFaktorLemburDao() {
        return pengaliFaktorLemburDao;
    }

    public void setPengaliFaktorLemburDao(PengaliFaktorLemburDao pengaliFaktorLemburDao) {
        this.pengaliFaktorLemburDao = pengaliFaktorLemburDao;
    }

    @Override
    public void saveDelete(PengaliFaktorLembur bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String pengaliFaktorLemburId = bean.getPengaliFaktorId();

            PengaliFaktorLemburEntity pengaliFaktorLemburEntity = null;

            try {
                // Get data from database by ID
                pengaliFaktorLemburEntity = pengaliFaktorLemburDao.getById("pengaliFaktorLemburId", pengaliFaktorLemburId);
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (pengaliFaktorLemburEntity != null) {

                // Modify from bean to entity serializable
                pengaliFaktorLemburEntity.setPengaliFaktorId(bean.getPengaliFaktorId());
                pengaliFaktorLemburEntity.setFaktor(bean.getFaktor());
                pengaliFaktorLemburEntity.setTipePegawaiId(bean.getTipePegawaiId());
                pengaliFaktorLemburEntity.setFlag(bean.getFlag());
                pengaliFaktorLemburEntity.setAction(bean.getAction());
                pengaliFaktorLemburEntity.setLastUpdateWho(bean.getLastUpdateWho());
                pengaliFaktorLemburEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    pengaliFaktorLemburDao.updateAndSave(pengaliFaktorLemburEntity);
                } catch (HibernateException e) {
                    logger.error("[AlatBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[AlatBoImpl.saveDelete] Error, not found data alat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data alat with request id, please check again your data ...");

            }
        }
        logger.info("[AlatBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(PengaliFaktorLembur bean) throws GeneralBOException {
        logger.info("[PengaliFaktorLemburBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {

            String pengaliFaktorLemburId = bean.getPengaliFaktorId();

            PengaliFaktorLemburEntity pengaliFaktorLemburEntity = null;

            try {
                // Get data from database by ID
                pengaliFaktorLemburEntity = pengaliFaktorLemburDao.getById("pengaliFaktorLemburId", pengaliFaktorLemburId);
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (pengaliFaktorLemburEntity != null) {
                //
                pengaliFaktorLemburEntity.setPengaliFaktorId(bean.getPengaliFaktorId());
                pengaliFaktorLemburEntity.setFaktor(bean.getFaktor());
                pengaliFaktorLemburEntity.setTipePegawaiId(bean.getTipePegawaiId());
                pengaliFaktorLemburEntity.setFlag(bean.getFlag());
                pengaliFaktorLemburEntity.setAction(bean.getAction());
                pengaliFaktorLemburEntity.setLastUpdateWho(bean.getLastUpdateWho());
                pengaliFaktorLemburEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    pengaliFaktorLemburDao.updateAndSave(pengaliFaktorLemburEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[AlatBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[AlatBoImpl.saveEdit] Error, not found data alat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data alat with request id, please check again your data ...");
//                condition = "Error, not found data alat with request id, please check again your data ...";
            }
        }
        logger.info("[AlatBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PengaliFaktorLembur saveAdd(PengaliFaktorLembur bean) throws GeneralBOException {
        logger.info("[PengaliFaktorLemburBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String alatId;
            try {
                // Generating ID, get from postgre sequence
                alatId = pengaliFaktorLemburDao.getNextPengaliFaktorId();
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            PengaliFaktorLemburEntity pengaliFaktorLemburEntity = new PengaliFaktorLemburEntity();

            pengaliFaktorLemburEntity.setPengaliFaktorId(alatId);
            pengaliFaktorLemburEntity.setTipePegawaiId(bean.getTipePegawaiId());
            pengaliFaktorLemburEntity.setFaktor(bean.getFaktor());

            pengaliFaktorLemburEntity.setFlag(bean.getFlag());
            pengaliFaktorLemburEntity.setAction(bean.getAction());
            pengaliFaktorLemburEntity.setCreatedWho(bean.getCreatedWho());
            pengaliFaktorLemburEntity.setLastUpdateWho(bean.getLastUpdateWho());
            pengaliFaktorLemburEntity.setCreatedDate(bean.getCreatedDate());
            pengaliFaktorLemburEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                pengaliFaktorLemburDao.addAndSave(pengaliFaktorLemburEntity);
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[AlatBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<PengaliFaktorLembur> getByCriteria(PengaliFaktorLembur searchBean) throws GeneralBOException {
        logger.info("[PengaliFaktorLemburBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PengaliFaktorLembur> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPengaliFaktorId() != null && !"".equalsIgnoreCase(searchBean.getPengaliFaktorId())) {
                hsCriteria.put("pengali_faktor_id", searchBean.getPengaliFaktorId());
            }
            if (searchBean.getTipePegawaiId() != null && !"".equalsIgnoreCase(searchBean.getTipePegawaiId())) {
                hsCriteria.put("tipe_pegawai", searchBean.getTipePegawaiId());
            }
            if (searchBean.getFaktor() != null ) {
                hsCriteria.put("faktor", searchBean.getFaktor());
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

            List<PengaliFaktorLemburEntity> pengaliFaktorLemburEntityList = null;
            try {

                pengaliFaktorLemburEntityList = pengaliFaktorLemburDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.getSearchAlatByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(pengaliFaktorLemburEntityList != null){
                PengaliFaktorLembur returnPengaliFaktorLembur;
                // Looping from dao to object and save in collection
                for(PengaliFaktorLemburEntity pengaliFaktorLemburEntity : pengaliFaktorLemburEntityList){
                    returnPengaliFaktorLembur = new PengaliFaktorLembur();
                    returnPengaliFaktorLembur.setPengaliFaktorId(pengaliFaktorLemburEntity.getPengaliFaktorId());
                    returnPengaliFaktorLembur.setFaktor(pengaliFaktorLemburEntity.getFaktor());
                    returnPengaliFaktorLembur.setTipePegawaiId(pengaliFaktorLemburEntity.getTipePegawaiId());
                    returnPengaliFaktorLembur.setCreatedWho(pengaliFaktorLemburEntity.getCreatedWho());
                    returnPengaliFaktorLembur.setCreatedDate(pengaliFaktorLemburEntity.getCreatedDate());
                    returnPengaliFaktorLembur.setLastUpdate(pengaliFaktorLemburEntity.getLastUpdate());
                    returnPengaliFaktorLembur.setAction(pengaliFaktorLemburEntity.getAction());
                    returnPengaliFaktorLembur.setFlag(pengaliFaktorLemburEntity.getFlag());
                    listOfResult.add(returnPengaliFaktorLembur);
                }
            }
        }
        logger.info("[AlatBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PengaliFaktorLembur> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<PengaliFaktorLembur> getComboPengaliFaktorLemburWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<PengaliFaktorLembur> listComboPengaliFaktorLembur = new ArrayList();
        String criteria = "%" + query + "%";

        List<PengaliFaktorLemburEntity> listPengaliFaktorLembur = null;
        try {
            listPengaliFaktorLembur = pengaliFaktorLemburDao.getListPengaliFaktorLembur(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listPengaliFaktorLembur != null) {
            for (PengaliFaktorLemburEntity pengaliFaktorLemburEntity : listPengaliFaktorLembur) {
                PengaliFaktorLembur itemComboPengaliFaktorLembur = new PengaliFaktorLembur();
                itemComboPengaliFaktorLembur.setPengaliFaktorId(pengaliFaktorLemburEntity.getPengaliFaktorId());
                itemComboPengaliFaktorLembur.setTipePegawaiId(pengaliFaktorLemburEntity.getTipePegawaiId());
                itemComboPengaliFaktorLembur.setFaktor(pengaliFaktorLemburEntity.getFaktor());
                listComboPengaliFaktorLembur.add(itemComboPengaliFaktorLembur);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboPengaliFaktorLembur;
    }
}
