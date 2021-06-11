package com.neurix.hris.master.rskelas.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.rskelas.bo.RsKelasBo;
import com.neurix.hris.master.rskelas.dao.RsKelasDao;
import com.neurix.hris.master.rskelas.model.ImHrisRsKelas;
import com.neurix.hris.master.rskelas.model.ImHrisRsKelasHistory;
import com.neurix.hris.master.rskelas.model.RsKelas;
import com.neurix.hris.master.tipelibur.bo.TipeLiburBo;
import com.neurix.hris.master.tipelibur.dao.TipeLiburDao;
import com.neurix.hris.master.tipelibur.model.ImHrisTipeLibur;
import com.neurix.hris.master.tipelibur.model.ImHrisTipeLiburHistory;
import com.neurix.hris.master.tipelibur.model.TipeLibur;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class RsKelasBoImpl implements RsKelasBo{
    protected static transient Logger logger = Logger.getLogger(RsKelasBoImpl.class);

    private RsKelasDao rsKelasDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RsKelasBoImpl.logger = logger;
    }

    public RsKelasDao getRsKelasDao() {
        return rsKelasDao;
    }

    public void setRsKelasDao(RsKelasDao rsKelasDao) {
        this.rsKelasDao = rsKelasDao;
    }

    @Override
    public void saveDelete(RsKelas bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(RsKelas bean) throws GeneralBOException {
        logger.info("[TipePegawaiBoImpl.saveEdit] start process >>>");
        boolean saved = false;
        if (bean!=null) {
            ImHrisRsKelas entityData = new ImHrisRsKelas();
            entityData.setRsKelasId(bean.getRsKelasId());
            entityData.setRsId(bean.getRsId());
            entityData.setKelompokId(bean.getKelompokId());
            entityData.setGolonganId(bean.getGolonganId());
            entityData.setKelas(bean.getKelas());
            entityData.setBranchId(bean.getBranchId());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());
            try {
                rsKelasDao.updateAndSave(entityData);
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
                id = rsKelasDao.getRsKelasHistoryId();
            } catch (HibernateException e) {
                logger.error("[TipePegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            ImHrisRsKelasHistory entityData = new ImHrisRsKelasHistory();
            entityData.setRsKelasId(bean.getRsKelasId());
            entityData.setRsId(bean.getRsId());
            entityData.setKelompokId(bean.getKelompokId());
            entityData.setGolonganId(bean.getGolonganId());
            entityData.setKelas(bean.getKelas());
            entityData.setBranchId(bean.getBranchId());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());
            entityData.setId(id);
            try {
                rsKelasDao.addAndSaveHistory(entityData);
                saved = true;
            } catch (HibernateException e) {
                logger.error("[TipePegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }
        logger.info("[TipePegawaiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public RsKelas saveAdd(RsKelas bean) throws GeneralBOException {
        logger.info("[TipePegawaiBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String id;
            try {
                // Generating ID, get from postgre sequence
                id = rsKelasDao.getRsKelasId();
            } catch (HibernateException e) {
                logger.error("[TipePegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImHrisRsKelas entityData = new ImHrisRsKelas();

            entityData.setRsKelasId("RSK"+id);
            entityData.setRsId(bean.getRsId());
            entityData.setKelompokId(bean.getKelompokId());
            entityData.setGolonganId(bean.getGolonganId());
            entityData.setKelas(bean.getKelas());
            entityData.setBranchId(bean.getBranchId());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                rsKelasDao.addAndSave(entityData);
            } catch (HibernateException e) {
                logger.error("[TipePegawaiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[TipePegawaiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<RsKelas> getByCriteria(RsKelas searchBean) throws GeneralBOException {
        logger.info("[TipePegawaiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<RsKelas> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getRsKelasId() != null && !"".equalsIgnoreCase(searchBean.getRsKelasId())) {
                hsCriteria.put("rs_kelas_id", searchBean.getRsKelasId());
            }
            if (searchBean.getRsId() != null && !"".equalsIgnoreCase(searchBean.getRsId())) {
                hsCriteria.put("rs_id", searchBean.getRsId());
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


            List<ImHrisRsKelas> imHrisRsKelases = null;
            try {

                imHrisRsKelases = rsKelasDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[TipePegawaiBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imHrisRsKelases != null){
                RsKelas returnData;
                // Looping from dao to object and save in collection
                for(ImHrisRsKelas entityData : imHrisRsKelases){
                    returnData = new RsKelas();
                    returnData.setRsKelasId(entityData.getRsKelasId());
                    returnData.setRsId(entityData.getRsId());
                    returnData.setKelompokId(entityData.getKelompokId());
                    returnData.setGolonganId(entityData.getGolonganId());
                    returnData.setKelas(entityData.getKelas());
                    returnData.setBranchId(entityData.getBranchId());
                    returnData.setCreatedDate(entityData.getCreatedDate());
                    returnData.setCreatedWho(entityData.getCreateDateWho());
                    returnData.setLastUpdate(entityData.getLastUpdate());
                    returnData.setLastUpdateWho(entityData.getLastUpdateWho());
                    returnData.setFlag(entityData.getFlag());
                    returnData.setAction(entityData.getAction());
                    listOfResult.add(returnData);
                }
            }
        }
        logger.info("[TipePegawaiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<RsKelas> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
