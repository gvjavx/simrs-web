package com.neurix.hris.master.tipepegawai.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.tipepegawai.bo.TipePegawaiBo;
import com.neurix.hris.master.tipepegawai.dao.TipePegawaiDao;
import com.neurix.hris.master.tipepegawai.model.ImHrisTipePegawai;
import com.neurix.hris.master.tipepegawai.model.ImHrisTipePegawaiHistory;
import com.neurix.hris.master.tipepegawai.model.TipePegawai;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class TipePegawaiBoImpl implements TipePegawaiBo{
    protected static transient Logger logger = Logger.getLogger(TipePegawaiBoImpl.class);

    private TipePegawaiDao tipePegawaiDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TipePegawaiBoImpl.logger = logger;
    }

    public TipePegawaiDao getTipePegawaiDao() {
        return tipePegawaiDao;
    }

    public void setTipePegawaiDao(TipePegawaiDao tipePegawaiDao) {
        this.tipePegawaiDao = tipePegawaiDao;
    }

    @Override
    public void saveDelete(TipePegawai bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(TipePegawai bean) throws GeneralBOException {
        logger.info("[TipePegawaiBoImpl.saveEdit] start process >>>");
        boolean saved = false;
        if (bean!=null) {
            ImHrisTipePegawai entityData = new ImHrisTipePegawai();
            entityData.setTipePegawaiId(bean.getTipePegawaiId());
            entityData.setTipePegawaiName(bean.getTipePegawaiName());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());
            try {
                tipePegawaiDao.updateAndSave(entityData);
                saved = true;
            } catch (HibernateException e) {
                logger.error("[TipePegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }
        if (saved){
            String id;
            try {
                // Generating ID, get from postgre sequence
                id = tipePegawaiDao.getTipePegawaiHistoryId();
            } catch (HibernateException e) {
                logger.error("[TipePegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }
            ImHrisTipePegawaiHistory entityData = new ImHrisTipePegawaiHistory();
            entityData.setTipePegawaiId(bean.getTipePegawaiId());
            entityData.setTipePegawaiName(bean.getTipePegawaiName());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());
            entityData.setId(id);
            try {
                tipePegawaiDao.addAndSaveHistory(entityData);
                saved = true;
            } catch (HibernateException e) {
                logger.error("[TipePegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }

        }
        logger.info("[TipePegawaiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public TipePegawai saveAdd(TipePegawai bean) throws GeneralBOException {
        logger.info("[TipePegawaiBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String liburId;
            try {
                // Generating ID, get from postgre sequence
                liburId = tipePegawaiDao.getTipePegawaiId();
            } catch (HibernateException e) {
                logger.error("[TipePegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImHrisTipePegawai entityData = new ImHrisTipePegawai();

            entityData.setTipePegawaiId("TP"+liburId);
            entityData.setTipePegawaiName(bean.getTipePegawaiName());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                tipePegawaiDao.addAndSave(entityData);
            } catch (HibernateException e) {
                logger.error("[TipePegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[TipePegawaiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<TipePegawai> getByCriteria(TipePegawai searchBean) throws GeneralBOException {
        logger.info("[TipePegawaiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<TipePegawai> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTipePegawaiId() != null && !"".equalsIgnoreCase(searchBean.getTipePegawaiId())) {
                hsCriteria.put("tipe_pegawai_id", searchBean.getTipePegawaiId());
            }
            if (searchBean.getTipePegawaiName() != null && !"".equalsIgnoreCase(searchBean.getTipePegawaiName())) {
                hsCriteria.put("tipe_pegawai_name", searchBean.getTipePegawaiName());
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


            List<ImHrisTipePegawai> imHrisTipePegawais = null;
            try {

                imHrisTipePegawais = tipePegawaiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[TipePegawaiBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imHrisTipePegawais != null){
                TipePegawai returnData;
                // Looping from dao to object and save in collection
                for(ImHrisTipePegawai tipePegawaiEntity : imHrisTipePegawais){
                    returnData = new TipePegawai();
                    returnData.setTipePegawaiId(tipePegawaiEntity.getTipePegawaiId());
                    returnData.setTipePegawaiName(tipePegawaiEntity.getTipePegawaiName());
                    returnData.setCreatedDate(tipePegawaiEntity.getCreatedDate());
                    returnData.setCreatedWho(tipePegawaiEntity.getCreateDateWho());
                    returnData.setLastUpdate(tipePegawaiEntity.getLastUpdate());
                    returnData.setLastUpdateWho(tipePegawaiEntity.getLastUpdateWho());
                    returnData.setFlag(tipePegawaiEntity.getFlag());
                    returnData.setAction(tipePegawaiEntity.getAction());
                    listOfResult.add(returnData);
                }
            }
        }
        logger.info("[TipePegawaiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<TipePegawai> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
    @Override
    public List<TipePegawai> searchTipePegawaiByBranch(String unitId) throws GeneralBOException {
        List<ImHrisTipePegawai> tipePegawaiList = null;
        List<TipePegawai> tipePegawais = new ArrayList<>();
        if (unitId.equalsIgnoreCase("KD01")){
            tipePegawaiList = tipePegawaiDao.getTipePegawaiByUnit(unitId);
        }else{
            tipePegawaiList = tipePegawaiDao.getAllTipePegawai();
        }
        if (tipePegawaiList != null) {
            for (ImHrisTipePegawai imHrisTipePegawai : tipePegawaiList) {
                TipePegawai tipePegawai = new TipePegawai();
                tipePegawai.setTipePegawaiId(imHrisTipePegawai.getTipePegawaiId());
                tipePegawai.setTipePegawaiName(imHrisTipePegawai.getTipePegawaiName());
                tipePegawais.add(tipePegawai);
            }
        }
        return tipePegawais;
    }
}
