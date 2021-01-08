package com.neurix.simrs.master.bentukbarang.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.bentukbarang.bo.BentukBarangBo;
import com.neurix.simrs.master.bentukbarang.dao.BentukBarangDao;
import com.neurix.simrs.master.bentukbarang.model.ImSimrsBentukBarangEntity;
import com.neurix.simrs.master.bentukbarang.model.BentukBarang;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 13/11/2019.
 */
public class BentukBarangBoImpl implements BentukBarangBo{
    private static transient Logger logger = Logger.getLogger(BentukBarangBoImpl.class);
    private BentukBarangDao bentukBarangDao;


    public BentukBarangDao getBentukBarangDao() {
        return bentukBarangDao;
    }

    public void setBentukBarangDao(BentukBarangDao bentukBarangDao) {
        this.bentukBarangDao = bentukBarangDao;
    }

    @Override
    public List<BentukBarang> getByCriteria(BentukBarang bean) throws GeneralBOException {
        logger.info("[BentukBarangBoImpl.getByCriteria] Start >>>>>>>");
        List<BentukBarang> listOfResultBentukBarang = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdBentuk() != null && !"".equalsIgnoreCase(bean.getIdBentuk())) {
                hsCriteria.put("id_bentuk", bean.getIdBentuk());
            }
            if (bean.getBentuk() != null && !"".equalsIgnoreCase(bean.getBentuk())) {
                hsCriteria.put("bentuk", bean.getBentuk());
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

            List<ImSimrsBentukBarangEntity> imSimrsBentukBarangEntities = null;
            try {
                imSimrsBentukBarangEntities = bentukBarangDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[BentukBarangBoImpl.getByCriteria] error when get data jenis obat by get by criteria "+ e.getMessage());
            }

            if (imSimrsBentukBarangEntities.size() > 0){
                for (ImSimrsBentukBarangEntity bentukBarangEntity : imSimrsBentukBarangEntities){
                    BentukBarang bentukBarang = new BentukBarang();
                    bentukBarang.setIdBentuk(bentukBarangEntity.getIdBentuk());
                    bentukBarang.setBentuk(bentukBarangEntity.getBentuk());

                    bentukBarang.setFlag(bentukBarangEntity.getFlag());
                    bentukBarang.setAction(bentukBarangEntity.getAction());
                    bentukBarang.setCreatedDate(bentukBarangEntity.getCreatedDate());
                    bentukBarang.setCreatedWho(bentukBarangEntity.getCreatedWho());
                    bentukBarang.setLastUpdate(bentukBarangEntity.getLastUpdate());
                    bentukBarang.setLastUpdateWho(bentukBarangEntity.getLastUpdateWho());
                    listOfResultBentukBarang.add(bentukBarang);
                }
            }
        }
        logger.info("[BentukBarangBoImpl.getByCriteria] End <<<<<<<");
        return listOfResultBentukBarang;

    }

    @Override
    public List<BentukBarang> getDataByCriteria(BentukBarang bean) throws GeneralBOException {
        return null;
    }

    @Override
    public void saveAdd(BentukBarang bean) throws GeneralBOException {
        if (bean!=null) {
            List<ImSimrsBentukBarangEntity> cekList = new ArrayList<>();
            List<ImSimrsBentukBarangEntity> cekListBentuk = new ArrayList<>();

            try {
                cekList = bentukBarangDao.getBentukBarang(bean.getIdBentuk());
                cekListBentuk = bentukBarangDao.getBentukBarang(bean.getBentuk());
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }
            if(cekList.size() > 0){
                throw new GeneralBOException("Id Jenis Obat sudah ada...!");
            }if(cekListBentuk.size()>0){
                 throw  new GeneralBOException("nama bentuk barang sudah ada");
            }
            else{
                ImSimrsBentukBarangEntity imSimrsBentukBarangEntity = new ImSimrsBentukBarangEntity();

                imSimrsBentukBarangEntity.setIdBentuk(bean.getIdBentuk());
                imSimrsBentukBarangEntity.setBentuk(bean.getBentuk());

                imSimrsBentukBarangEntity.setFlag(bean.getFlag());
                imSimrsBentukBarangEntity.setAction(bean.getAction());
                imSimrsBentukBarangEntity.setCreatedWho(bean.getCreatedWho());
                imSimrsBentukBarangEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsBentukBarangEntity.setCreatedDate(bean.getCreatedDate());
                imSimrsBentukBarangEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // insert into database
                    bentukBarangDao.addAndSave(imSimrsBentukBarangEntity);
                } catch (HibernateException e) {
                    logger.error("[jenisObatBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Jenis Obat, please info to your admin..." + e.getMessage());
                }
            }
        }
    }

    @Override
    public void saveEdit(BentukBarang bean) throws GeneralBOException {
        logger.info("[BentukBarangBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String idBentuk = bean.getIdBentuk();
            ImSimrsBentukBarangEntity imSimrsBentukBarangEntity = null;
            try {
                // Get data from database by ID
                imSimrsBentukBarangEntity = bentukBarangDao.getById("idBentuk", idBentuk);

            } catch (HibernateException e) {
                logger.error("[BentukBarangBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data BentukBarang by Kode BentukBarang, please inform to your admin...," + e.getMessage());
            }
            if (imSimrsBentukBarangEntity != null) {
//
                imSimrsBentukBarangEntity.setIdBentuk(bean.getIdBentuk());
                imSimrsBentukBarangEntity.setBentuk(bean.getBentuk());

                imSimrsBentukBarangEntity.setFlag(bean.getFlag());
                imSimrsBentukBarangEntity.setAction(bean.getAction());
                imSimrsBentukBarangEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsBentukBarangEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    bentukBarangDao.updateAndSave(imSimrsBentukBarangEntity);
                } catch (HibernateException e) {
                    logger.error("[BentukBarangBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update dataBentukBarang, please info to your admin..." + e.getMessage());
                }
            }
        } else {
            logger.error("[BentukBarangBoImpl.saveEdit] Error, not found data BentukBarang with request id, please check again your data ...");
            throw new GeneralBOException("Error, not found data BentukBarangi with request id, please check again your data ...");
        }
    }

    @Override
    public void saveDelete(BentukBarang bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean != null) {
            String idBentukBarang = bean.getIdBentuk();

            ImSimrsBentukBarangEntity imSimrsBentukBarangEntity = null;

            try {
                // Get data from database by ID
                imSimrsBentukBarangEntity = bentukBarangDao.getById("idBentuk", idBentukBarang);
            } catch (HibernateException e) {
                logger.error("[BentukBarangBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSimrsBentukBarangEntity != null) {
                imSimrsBentukBarangEntity.setIdBentuk(bean.getIdBentuk());

                imSimrsBentukBarangEntity.setFlag(bean.getFlag());
                imSimrsBentukBarangEntity.setAction(bean.getAction());
                imSimrsBentukBarangEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsBentukBarangEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    bentukBarangDao.updateAndSave(imSimrsBentukBarangEntity);
                } catch (HibernateException e) {
                    logger.error("[BentukBarangBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data BentukBarang, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[BentukBarangBoImpl.saveDelete] Error, not found data BentukBarang with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data BentukBarang with request id, please check again your data ...");
            }
        }
        logger.info("[BentukBarangBoImpl.saveDelete] end process <<<");
    }
}
