package com.neurix.simrs.master.kategoritindakan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kategoritindakan.dao.KategoriTindakanDao;
import com.neurix.simrs.master.kategoritindakan.model.ImSimrsKategoriTindakanEntity;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 13/11/2019.
 */
public class KategoriTindakanBoImpl implements KategoriTindakanBo {
    private static transient Logger logger = Logger.getLogger(KategoriTindakanBoImpl.class);
    private KategoriTindakanDao kategoriTindakanDao;

    @Override
    public void saveDelete(KategoriTindakan bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean != null) {
            String idKategoriTindakan = bean.getIdKategoriTindakan();
            ImSimrsKategoriTindakanEntity imSimrsKategoriTindakanEntity = null;

            try {
                // Get data from database by ID
                imSimrsKategoriTindakanEntity = kategoriTindakanDao.getById("idKategoriTindakan", idKategoriTindakan);
            } catch (HibernateException e) {
                logger.error("[KategoriTindakanBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSimrsKategoriTindakanEntity != null) {
                imSimrsKategoriTindakanEntity.setKategoriTindakan(bean.getKategoriTindakan());

                imSimrsKategoriTindakanEntity.setFlag(bean.getFlag());
                imSimrsKategoriTindakanEntity.setAction(bean.getAction());
                imSimrsKategoriTindakanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsKategoriTindakanEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    kategoriTindakanDao.updateAndSave(imSimrsKategoriTindakanEntity);
                } catch (HibernateException e) {
                    logger.error("[KategoriTindakanBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data KategoriTindakan, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[KategoriTindakanBoImpl.saveDelete] Error, not found data KategoriTindakan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data KategoriTindakan with request id, please check again your data ...");
            }
        }
        logger.info("[KategoriTindakanBoImpl.saveDelete] end process <<<");

    }

    @Override
    public void saveEdit(KategoriTindakan bean) throws GeneralBOException {
        logger.info("[KategoriTindakanBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String idKategoriTindakan = bean.getIdKategoriTindakan();
            ImSimrsKategoriTindakanEntity imSimrsKategoriTindakanEntity = null;
            try {
                // Get data from database by ID
                imSimrsKategoriTindakanEntity = kategoriTindakanDao.getById("idKategoriTindakan", idKategoriTindakan);

            } catch (HibernateException e) {
                logger.error("[KategoriTindakanBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data KategoriTindakan by Kode KategoriTindakan, please inform to your admin...," + e.getMessage());
            }
            if (imSimrsKategoriTindakanEntity != null) {
//                imSimrsKategoriTindakanEntity.setIdKategoriTindakan(idKategoriTindakan);
                imSimrsKategoriTindakanEntity.setKategoriTindakan(bean.getKategoriTindakan());

                imSimrsKategoriTindakanEntity.setFlag(bean.getFlag());
                imSimrsKategoriTindakanEntity.setAction(bean.getAction());
                imSimrsKategoriTindakanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsKategoriTindakanEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    kategoriTindakanDao.updateAndSave(imSimrsKategoriTindakanEntity);
                } catch (HibernateException e) {
                    logger.error("[KategoriTindakanBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update dataKategoriTindakan, please info to your admin..." + e.getMessage());
                }
            }
        } else {
            logger.error("[KategoriTindakanBoImpl.saveEdit] Error, not found data KategoriTindakan with request id, please check again your data ...");
            throw new GeneralBOException("Error, not found data KategoriTindakani with request id, please check again your data ...");
        }
    }

    @Override
    public KategoriTindakan saveAdd(KategoriTindakan bean) throws GeneralBOException {
        if (bean!=null) {
            List<ImSimrsKategoriTindakanEntity> cekList = new ArrayList<>();
            try {
                cekList = kategoriTindakanDao.getKategoriTindakan(bean.getKategoriTindakan());
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }
            if(cekList.size() > 0){
                throw new GeneralBOException("Nama kategori tindakan sudah ada...!");
            }else{
                String idKategoriTindakan;
                try {
                    // Generating ID, get from postgre sequence
                    idKategoriTindakan = kategoriTindakanDao.getNextId();
                } catch (HibernateException e) {
                    logger.error("[kategoritindakanDaoBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence kategori tindakan id, please info to your admin..." + e.getMessage());
                }
                // creating object entity serializable
                ImSimrsKategoriTindakanEntity imSimrsKategoriTindakanEntity = new ImSimrsKategoriTindakanEntity();
                imSimrsKategoriTindakanEntity.setIdKategoriTindakan(idKategoriTindakan);

                imSimrsKategoriTindakanEntity.setKategoriTindakan(bean.getKategoriTindakan());
                imSimrsKategoriTindakanEntity.setFlag(bean.getFlag());
                imSimrsKategoriTindakanEntity.setAction(bean.getAction());
                imSimrsKategoriTindakanEntity.setCreatedWho(bean.getCreatedWho());
                imSimrsKategoriTindakanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsKategoriTindakanEntity.setCreatedDate(bean.getCreatedDate());
                imSimrsKategoriTindakanEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // insert into database
                    kategoriTindakanDao.addAndSave(imSimrsKategoriTindakanEntity);
                } catch (HibernateException e) {
                    logger.error("[jenisObatBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data kategoritindakan, please info to your admin..." + e.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public List<KategoriTindakan> getByCriteria(KategoriTindakan bean) throws GeneralBOException {
        logger.info("[KategoriTindakanBoImpl.getByCriteria] Start >>>>>>>");
        List<KategoriTindakan> results = new ArrayList<>();

        if (bean != null){
            List<ImSimrsKategoriTindakanEntity> entityList = getListEntityKategoriTindakan(bean);
            if (!entityList.isEmpty()){
                results = setToKategoriTindakanTemplate(entityList);
            }
        }
        logger.info("[KategoriTindakanBoImpl.getByCriteria] End <<<<<<<");
        return results;
    }

    @Override
    public List<KategoriTindakan> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public List<KategoriTindakan> getListKategoriTindakan(String idPelayanan, String kategori, String branchId) throws GeneralBOException {
        List<KategoriTindakan> list = new ArrayList<>();
        try {
            list = kategoriTindakanDao.getListKategoriTindakan(idPelayanan, kategori, branchId);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return list;
    }

    @Override
    public List<KategoriTindakan> getDataByCriteria(KategoriTindakan bean) throws GeneralBOException {
        List<KategoriTindakan> listOfResultKategoriTindakan = new ArrayList<>();
        if(bean != null){

            Map hsCriteria = new HashMap();

            if(bean.getIdKategoriTindakan() != null && !"".equalsIgnoreCase(bean.getIdKategoriTindakan())){
                hsCriteria.put("id_kategori_tindakan", bean.getIdKategoriTindakan());
            }
            if(bean.getKategoriTindakan() != null && !"".equalsIgnoreCase(bean.getKategoriTindakan())){
                hsCriteria.put("kategori_tindakan", bean.getKategoriTindakan());
            }
            if(bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
                hsCriteria.put("branch_id", bean.getBranchId());
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

            List<ImSimrsKategoriTindakanEntity> kategoriTindakanEntities = new ArrayList<>();
            try {
                kategoriTindakanEntities = kategoriTindakanDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error("Found Error when search kategori tindakan "+e.getMessage());
            }

            if(kategoriTindakanEntities.size() > 0){
                for (ImSimrsKategoriTindakanEntity entity: kategoriTindakanEntities){
                    KategoriTindakan kategoriTindakan = new KategoriTindakan();
                    kategoriTindakan.setIdKategoriTindakan(entity.getIdKategoriTindakan());
                    kategoriTindakan.setKategoriTindakan(entity.getKategoriTindakan());
                    kategoriTindakan.setFlag(entity.getFlag());
                    kategoriTindakan.setAction(entity.getAction());
                    kategoriTindakan.setCreatedWho(entity.getCreatedWho());
                    kategoriTindakan.setStCreatedDate(entity.getCreatedDate().toString());
                    kategoriTindakan.setCreatedDate(entity.getCreatedDate());
                    kategoriTindakan.setStLastUpdate(entity.getLastUpdate().toString());
                    kategoriTindakan.setLastUpdate(entity.getLastUpdate());
                    kategoriTindakan.setLastUpdateWho(entity.getLastUpdateWho());
                    kategoriTindakan.setBranchId(entity.getBranchId());
                    listOfResultKategoriTindakan.add(kategoriTindakan);
                }
            }
        }

        return listOfResultKategoriTindakan;
    }

    protected List<ImSimrsKategoriTindakanEntity> getListEntityKategoriTindakan(KategoriTindakan bean) throws GeneralBOException {
        logger.info("[KategoriTindakanBoImpl.getListEntityKategoriTindakan] Start >>>>>>>");
        List<ImSimrsKategoriTindakanEntity> results = new ArrayList<>();

        Map hsCiteria = new HashMap();

        if (bean.getIdKategoriTindakan() != null && !"".equalsIgnoreCase(bean.getIdKategoriTindakan())){
            hsCiteria.put("id_kategori_tindakan", bean.getIdKategoriTindakan());
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            hsCiteria.put("branch_id", bean.getBranchId());
        }
        hsCiteria.put("flag", "Y");

        try {
            results = kategoriTindakanDao.getByCriteria(hsCiteria);
        } catch (HibernateException e){
            logger.error("[KategoriTindakanBoImpl.getListEntityKategoriTindakan] Error when get data tindakan ", e);
        }

        logger.info("[KategoriTindakanBoImpl.getListEntityKategoriTindakan] End <<<<<<<");
        return results;
    }

    protected List<KategoriTindakan> setToKategoriTindakanTemplate(List<ImSimrsKategoriTindakanEntity> entities) throws GeneralBOException{
        logger.info("[KategoriTindakanBoImpl.setToKategoriTindakanTemplate] Start >>>>>>>");
        List<KategoriTindakan> results = new ArrayList<>();

        KategoriTindakan kategoriTindakan;
        for (ImSimrsKategoriTindakanEntity entity : entities){
            kategoriTindakan = new KategoriTindakan();
            kategoriTindakan.setIdKategoriTindakan(entity.getIdKategoriTindakan());
            kategoriTindakan.setKategoriTindakan(entity.getKategoriTindakan());
            kategoriTindakan.setFlag(entity.getFlag());
            kategoriTindakan.setAction(entity.getAction());
            kategoriTindakan.setCreatedDate(entity.getCreatedDate());
            kategoriTindakan.setCreatedWho(entity.getCreatedWho());
            kategoriTindakan.setLastUpdate(entity.getLastUpdate());
            kategoriTindakan.setLastUpdateWho(entity.getLastUpdateWho());
            results.add(kategoriTindakan);
        }

        logger.info("[KategoriTindakanBoImpl.setToKategoriTindakanTemplate] End <<<<<<<");
        return results;
    }


    public void setKategoriTindakanDao(KategoriTindakanDao kategoriTindakanDao) {
        this.kategoriTindakanDao = kategoriTindakanDao;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
