package com.neurix.hris.master.tunjangan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.tipelibur.bo.TipeLiburBo;
import com.neurix.hris.master.tipelibur.dao.TipeLiburDao;
import com.neurix.hris.master.tipelibur.model.ImHrisTipeLibur;
import com.neurix.hris.master.tipelibur.model.TipeLibur;
import com.neurix.hris.master.tunjangan.bo.TunjanganBo;
import com.neurix.hris.master.tunjangan.dao.TunjanganDao;
import com.neurix.hris.master.tunjangan.model.ImHrisTunjangan;
import com.neurix.hris.master.tunjangan.model.ImHrisTunjanganHistory;
import com.neurix.hris.master.tunjangan.model.Tunjangan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class TunjanganBoImpl implements TunjanganBo{
    protected static transient Logger logger = Logger.getLogger(TunjanganBoImpl.class);

    private TunjanganDao tunjanganDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TunjanganBoImpl.logger = logger;
    }

    public TunjanganDao getTunjanganDao() {
        return tunjanganDao;
    }

    public void setTunjanganDao(TunjanganDao tunjanganDao) {
        this.tunjanganDao = tunjanganDao;
    }

    @Override
    public void saveDelete(Tunjangan bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(Tunjangan bean) throws GeneralBOException {
        logger.info("[TipePegawaiBoImpl.saveEdit] start process >>>");
        boolean saved = false;
        if (bean!=null) {
            ImHrisTunjangan entityData = new ImHrisTunjangan();
            entityData.setTunjanganId(bean.getTunjanganId());
            entityData.setTunjanganName(bean.getTunjanganName());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());
            try {
                tunjanganDao.updateAndSave(entityData);
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
                id = tunjanganDao.getTunjanganHistoryId();
            } catch (HibernateException e) {
                logger.error("[TipePegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            ImHrisTunjanganHistory entityData = new ImHrisTunjanganHistory();
            entityData.setTunjanganId(bean.getTunjanganId());
            entityData.setTunjanganName(bean.getTunjanganName());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());
            entityData.setId(id);
            try {
                tunjanganDao.addAndSaveHistory(entityData);
                saved = true;
            } catch (HibernateException e) {
                logger.error("[TipePegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }
        logger.info("[TipePegawaiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Tunjangan saveAdd(Tunjangan bean) throws GeneralBOException {
        logger.info("[TipePegawaiBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String liburId;
            try {
                // Generating ID, get from postgre sequence
                liburId = tunjanganDao.getTunjanganId();
            } catch (HibernateException e) {
                logger.error("[TipePegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImHrisTunjangan entityData = new ImHrisTunjangan();

            entityData.setTunjanganId("TJ"+liburId);
            entityData.setTunjanganName(bean.getTunjanganName());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                tunjanganDao.addAndSave(entityData);
            } catch (HibernateException e) {
                logger.error("[TipePegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[TipePegawaiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Tunjangan> getByCriteria(Tunjangan searchBean) throws GeneralBOException {
        logger.info("[TipePegawaiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Tunjangan> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTunjanganId() != null && !"".equalsIgnoreCase(searchBean.getTunjanganId())) {
                hsCriteria.put("tunjangan_id", searchBean.getTunjanganId());
            }
            if (searchBean.getTunjanganName() != null && !"".equalsIgnoreCase(searchBean.getTunjanganName())) {
                hsCriteria.put("tunjangan_name", searchBean.getTunjanganName());
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


            List<ImHrisTunjangan> imHrisTunjangans = null;
            try {

                imHrisTunjangans = tunjanganDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[TipePegawaiBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imHrisTunjangans != null){
                Tunjangan returnData;
                // Looping from dao to object and save in collection
                for(ImHrisTunjangan tunjanganEntity : imHrisTunjangans){
                    returnData = new Tunjangan();
                    returnData.setTunjanganId(tunjanganEntity.getTunjanganId());
                    returnData.setTunjanganName(tunjanganEntity.getTunjanganName());
                    returnData.setCreatedDate(tunjanganEntity.getCreatedDate());
                    returnData.setCreatedWho(tunjanganEntity.getCreateDateWho());
                    returnData.setLastUpdate(tunjanganEntity.getLastUpdate());
                    returnData.setLastUpdateWho(tunjanganEntity.getLastUpdateWho());
                    returnData.setFlag(tunjanganEntity.getFlag());
                    returnData.setAction(tunjanganEntity.getAction());
                    listOfResult.add(returnData);
                }
            }
        }
        logger.info("[TipePegawaiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Tunjangan> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
