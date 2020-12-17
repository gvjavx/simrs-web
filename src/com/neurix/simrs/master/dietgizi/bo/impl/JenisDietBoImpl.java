package com.neurix.simrs.master.dietgizi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dietgizi.bo.JenisDietBo;
import com.neurix.simrs.master.dietgizi.dao.JenisDietDao;
import com.neurix.simrs.master.dietgizi.model.ImSimrsJenisDietEntity;
import com.neurix.simrs.master.dietgizi.model.JenisDiet;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JenisDietBoImpl implements JenisDietBo {

    protected static transient Logger logger = Logger.getLogger(JenisDietBoImpl.class);
    private JenisDietDao jenisDietDao;

    public static Logger getLogger() {
        return logger;
    }

    public void setJenisDietDao(JenisDietDao jenisDietDao) {
        this.jenisDietDao = jenisDietDao;
    }

    @Override
    public List<JenisDiet> getByCriteria(JenisDiet bean) throws GeneralBOException {
        logger.info("[JenisDietBoImpl.getByCriteria] Start >>>>>>>");
        List<JenisDiet> listOfResultJenisDiet = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdJenisDiet() != null && !"".equalsIgnoreCase(bean.getIdJenisDiet())) {
                hsCriteria.put("id_jenis_diet", bean.getIdJenisDiet());
            }
            if (bean.getNamaJenisDiet() != null && !"".equalsIgnoreCase(bean.getNamaJenisDiet())) {
                hsCriteria.put("nama_jenis_diet", bean.getNamaJenisDiet());
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

            List<ImSimrsJenisDietEntity> imSimrsJenisDietEntities = null;
            try {
                imSimrsJenisDietEntities = jenisDietDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[JenisDietBoImpl.getByCriteria] error when get data Jenis Diet by get by criteria "+ e.getMessage());
            }

            if (imSimrsJenisDietEntities.size() > 0){
                for (ImSimrsJenisDietEntity jenisDietEntity : imSimrsJenisDietEntities){
                    JenisDiet jenisDiet = new JenisDiet();
                    jenisDiet.setIdJenisDiet(jenisDietEntity.getIdJenisDiet());
                    jenisDiet.setNamaJenisDiet(jenisDietEntity.getNamaJenisDiet());

                    jenisDiet.setFlag(jenisDietEntity.getFlag());
                    jenisDiet.setAction(jenisDietEntity.getAction());
                    jenisDiet.setCreatedDate(jenisDietEntity.getCreatedDate());
                    jenisDiet.setCreatedWho(jenisDietEntity.getCreatedWho());
                    jenisDiet.setLastUpdate(jenisDietEntity.getLastUpdate());
                    jenisDiet.setLastUpdateWho(jenisDietEntity.getLastUpdateWho());
                    listOfResultJenisDiet.add(jenisDiet);
                }
            }
        }
        logger.info("[JenisDietBoImpl.getByCriteria] End <<<<<<<");
        return listOfResultJenisDiet;
    }

    @Override
    public List<JenisDiet> getJenisDietByCriteria(JenisDiet searchBean) throws GeneralBOException {
        return null;
    }

    @Override
    public void saveAdd(JenisDiet bean) throws GeneralBOException {
        if (bean!=null) {
            List<ImSimrsJenisDietEntity> cekList = new ArrayList<>();
            try {
                cekList = jenisDietDao.getJenisDiet(bean.getNamaJenisDiet());

            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }
            if (cekList.size() > 0) {
                throw new GeneralBOException("nama jenis diet tidak boleh sama");
            } else {
                String jenisdietId;
                try {
                    // Generating ID, get from postgre sequence
                    jenisdietId = jenisDietDao.getNextId();
                } catch (HibernateException e) {
                    logger.error("[jenisdietDaoBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence Jenis Diet id, please info to your admin..." + e.getMessage());
                }
                // creating object entity serializable
                ImSimrsJenisDietEntity imSimrsJenisDietEntity = new ImSimrsJenisDietEntity();
                imSimrsJenisDietEntity.setIdJenisDiet(jenisdietId);

                imSimrsJenisDietEntity.setNamaJenisDiet(bean.getNamaJenisDiet());

                imSimrsJenisDietEntity.setFlag(bean.getFlag());
                imSimrsJenisDietEntity.setAction(bean.getAction());
                imSimrsJenisDietEntity.setCreatedWho(bean.getCreatedWho());
                imSimrsJenisDietEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsJenisDietEntity.setCreatedDate(bean.getCreatedDate());
                imSimrsJenisDietEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // insert into database
                    jenisDietDao.addAndSave(imSimrsJenisDietEntity);
                } catch (HibernateException e) {
                    logger.error("[jenisDietBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Jenis Obat, please info to your admin..." + e.getMessage());
                }
            }
        }

    }

    @Override
    public void saveEdit(JenisDiet bean) throws GeneralBOException {
        logger.info("[JenisDietBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String idJenisDiet = bean.getIdJenisDiet();
            ImSimrsJenisDietEntity imSimrsJenisDietEntity = null;
            try {
                // Get data from database by ID
                imSimrsJenisDietEntity = jenisDietDao.getById("idJenisDiet", idJenisDiet);

            } catch (HibernateException e) {
                logger.error("[JenisDietBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data JenisDiet by Kode JenisDiet, please inform to your admin...," + e.getMessage());
            }
            if (imSimrsJenisDietEntity != null) {
//                imSimrsJenisDietEntity.setIdJenisDiet(idJenisDiet);
                imSimrsJenisDietEntity.setNamaJenisDiet(bean.getNamaJenisDiet());
                imSimrsJenisDietEntity.setIdJenisDiet(bean.getIdJenisDiet());

                imSimrsJenisDietEntity.setFlag(bean.getFlag());
                imSimrsJenisDietEntity.setAction(bean.getAction());
                imSimrsJenisDietEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsJenisDietEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    jenisDietDao.updateAndSave(imSimrsJenisDietEntity);
                } catch (HibernateException e) {
                    logger.error("[JenisDietBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update dataJenisDiet, please info to your admin..." + e.getMessage());
                }
            }
        } else {
            logger.error("[JenisDietBoImpl.saveEdit] Error, not found data JenisDiet with request id, please check again your data ...");
            throw new GeneralBOException("Error, not found data JenisDieti with request id, please check again your data ...");
        }
    }

    @Override
    public void saveDelete(JenisDiet bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean != null) {
            String idJenisDiet = bean.getIdJenisDiet();

            ImSimrsJenisDietEntity imSimrsJenisDietEntity = null;

            try {
                // Get data from database by ID
                imSimrsJenisDietEntity = jenisDietDao.getById("idJenisDiet", idJenisDiet);
            } catch (HibernateException e) {
                logger.error("[JenisDietBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSimrsJenisDietEntity != null) {
                imSimrsJenisDietEntity.setIdJenisDiet(bean.getIdJenisDiet());

                imSimrsJenisDietEntity.setFlag(bean.getFlag());
                imSimrsJenisDietEntity.setAction(bean.getAction());
                imSimrsJenisDietEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsJenisDietEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    jenisDietDao.updateAndSave(imSimrsJenisDietEntity);
                } catch (HibernateException e) {
                    logger.error("[JenisDietBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data JenisDiet, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[JenisDietBoImpl.saveDelete] Error, not found data JenisDiet with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data JenisDiet with request id, please check again your data ...");
            }
        }
        logger.info("[JenisDietBoImpl.saveDelete] end process <<<");

    }
}