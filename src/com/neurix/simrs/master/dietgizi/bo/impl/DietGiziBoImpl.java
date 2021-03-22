package com.neurix.simrs.master.dietgizi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dietgizi.bo.DietGiziBo;
import com.neurix.simrs.master.dietgizi.dao.DietGiziDao;
import com.neurix.simrs.master.dietgizi.dao.JenisDietDao;
import com.neurix.simrs.master.dietgizi.model.DietGizi;
import com.neurix.simrs.master.dietgizi.model.ImSimrsDietGizi;
import com.neurix.simrs.master.dietgizi.model.ImSimrsJenisDietEntity;
import com.neurix.simrs.master.dietgizi.model.JenisDiet;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DietGiziBoImpl implements DietGiziBo {

    private static transient Logger logger = Logger.getLogger(DietGiziBoImpl.class);
    private DietGiziDao dietGiziDao;
    private JenisDietDao jenisDietDao;

    @Override
    public List<DietGizi> getByCriteria(DietGizi bean) throws GeneralBOException {
        logger.info("[DietGiziBoImpl.getByCriteria] Start >>>>>>>>");
        List<DietGizi> listOfResultDietGizi = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdDietGizi() != null && !"".equalsIgnoreCase(bean.getIdDietGizi())) {
                hsCriteria.put("id_diet_gizi", bean.getIdDietGizi());
            }
            if (bean.getNamaDietGizi() != null && !"".equalsIgnoreCase(bean.getNamaDietGizi())) {
                hsCriteria.put("nama_diet_gizi", bean.getNamaDietGizi());
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

            List<ImSimrsDietGizi> imSimrsDietGizi = null;
            try {
                imSimrsDietGizi = dietGiziDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[JenisDietBoImpl.getByCriteria] error when get data Jenis Diet by get by criteria "+ e.getMessage());
            }

        if (imSimrsDietGizi.size() > 0) {
            for (ImSimrsDietGizi DietGizi : imSimrsDietGizi){
                    DietGizi dietGizi = new DietGizi();
                    dietGizi.setIdDietGizi(DietGizi.getIdDietGizi());
                    dietGizi.setNamaDietGizi(DietGizi.getNamaDietGizi());
                    dietGizi.setTarif(DietGizi.getTarif());
                    dietGizi.setBranchId(DietGizi.getBranchId());
                    dietGizi.setAction(DietGizi.getAction());
                    dietGizi.setFlag(DietGizi.getFlag());
                    dietGizi.setCreatedDate(DietGizi.getCreatedDate());
                    dietGizi.setCreatedWho(DietGizi.getCreatedWho());
                    dietGizi.setLastUpdate(DietGizi.getLastUpdate());
                    dietGizi.setLastUpdateWho(DietGizi.getLastUpdateWho());
                    dietGizi.setIsSonde(DietGizi.getIsSonde());
                    listOfResultDietGizi.add(dietGizi);
                }
            }
        }

        logger.info("[DietGiziBoImpl.getByCriteria] End <<<<<<<<");
        return listOfResultDietGizi;
    }

    @Override
    public void saveAdd(DietGizi bean) throws GeneralBOException {
        if (bean!=null) {
            List<ImSimrsDietGizi> cekList = new ArrayList<>();
            try {
                cekList = dietGiziDao.getDietGizi(bean.getNamaDietGizi());
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }if (cekList.size()> 0){
                throw new GeneralBOException("nama diet gizi tidak boleh sama");
            }else {

            String dietgiziId;
            try {
                // Generating ID, get from postgre sequence
                dietgiziId = dietGiziDao.getNextId();
            } catch (HibernateException e) {
                logger.error("[dietgiziDaoBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence Jenis Diet id, please info to your admin..." + e.getMessage());
            }
            // creating object entity serializable
            ImSimrsDietGizi imSimrsDietGizi = new ImSimrsDietGizi();

            imSimrsDietGizi.setIdDietGizi(dietgiziId);
            imSimrsDietGizi.setNamaDietGizi(bean.getNamaDietGizi());
            imSimrsDietGizi.setTarif(bean.getTarif());
            imSimrsDietGizi.setBranchId(bean.getBranchId());

            imSimrsDietGizi.setFlag(bean.getFlag());
            imSimrsDietGizi.setAction(bean.getAction());
            imSimrsDietGizi.setCreatedWho(bean.getCreatedWho());
            imSimrsDietGizi.setLastUpdateWho(bean.getLastUpdateWho());
            imSimrsDietGizi.setCreatedDate(bean.getCreatedDate());
            imSimrsDietGizi.setLastUpdate(bean.getLastUpdate());
            try {
                // insert into database
                dietGiziDao.addAndSave(imSimrsDietGizi);
              } catch (HibernateException e) {
                logger.error("[jenisDietBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Jenis Obat, please info to your admin..." + e.getMessage());
                }
            }
        }
    }

    @Override
    public void saveEdit(DietGizi bean) throws GeneralBOException {
        logger.info("[DietGiziBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String idDietGizi = bean.getIdDietGizi();
            ImSimrsDietGizi imSimrsDietGizi = null;
            try {
                // Get data from database by ID
                imSimrsDietGizi = dietGiziDao.getById("idDietGizi", idDietGizi);

            } catch (HibernateException e) {
                logger.error("[DietGiziBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data DietGizi , please inform to your admin...," + e.getMessage());
            }
            if (imSimrsDietGizi != null) {
//                imSimrsJenisDietEntity.setIdJenisDiet(idJenisDiet);
                imSimrsDietGizi.setNamaDietGizi(bean.getNamaDietGizi());
                imSimrsDietGizi.setTarif(bean.getTarif());

                imSimrsDietGizi.setFlag(bean.getFlag());
                imSimrsDietGizi.setAction(bean.getAction());
                imSimrsDietGizi.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsDietGizi.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    dietGiziDao.updateAndSave(imSimrsDietGizi);
                } catch (HibernateException e) {
                    logger.error("[DietGiziBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update DietGizi, please info to your admin..." + e.getMessage());
                }
            }
        } else {
            logger.error("[DietGiziBoImpl.saveEdit] Error, not found data JenisDiet with request id, please check again your data ...");
            throw new GeneralBOException("Error, not found data DietGizi with request id, please check again your data ...");
        }
    }

    @Override
    public void saveDelete(DietGizi bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean != null) {
            String idDietGizi = bean.getIdDietGizi();

            ImSimrsDietGizi imSimrsDietGizi = null;

            try {
                // Get data from database by ID
                imSimrsDietGizi = dietGiziDao.getById("idDietGizi", idDietGizi);
            } catch (HibernateException e) {
                logger.error("[idDietGiziBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSimrsDietGizi != null) {
                imSimrsDietGizi.setIdDietGizi(bean.getIdDietGizi());

                imSimrsDietGizi.setFlag(bean.getFlag());
                imSimrsDietGizi.setAction(bean.getAction());
                imSimrsDietGizi.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsDietGizi.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    dietGiziDao.updateAndSave(imSimrsDietGizi);
                } catch (HibernateException e) {
                    logger.error("[DietGizitBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data DietGizi, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[DietGiziBoImpl.saveDelete] Error, not found data JenisDiet with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data JenisDiet with request id, please check again your data ...");
            }
        }
        logger.info("[DietGiziBoImpl.saveDelete] end process <<<");

    }


    @Override
    public List<JenisDiet> getJenisDietByCiteria(JenisDiet bean) throws GeneralBOException {
        List<ImSimrsJenisDietEntity> results = new ArrayList<>();
        List<JenisDiet> jenisDietList = new ArrayList<>();
        Map hsCriteria = new HashMap();
        if (bean != null) {
            if (bean.getIdJenisDiet() != null && !"".equalsIgnoreCase(bean.getIdJenisDiet())) {
                hsCriteria.put("id_jenis_diet", bean.getIdJenisDiet());
            }
            if (bean.getNamaJenisDiet() != null && !"".equalsIgnoreCase(bean.getNamaJenisDiet())) {
                hsCriteria.put("nama_jenis", bean.getNamaJenisDiet());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                hsCriteria.put("flag", bean.getFlag());
            }

            try {
                results = jenisDietDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[DietGiziBoImpl.getListEntityDietGizi] Error When search gizi data ");
            }

            if (results.size() > 0){
                for (ImSimrsJenisDietEntity entity: results){
                    JenisDiet jenisDiet = new JenisDiet();

                    jenisDiet.setIdJenisDiet(entity.getIdJenisDiet());
                    jenisDiet.setNamaJenisDiet(entity.getNamaJenisDiet());

                    jenisDiet.setAction(entity.getAction());
                    jenisDiet.setFlag(entity.getFlag());
                    jenisDiet.setCreatedDate(entity.getCreatedDate());
                    jenisDiet.setCreatedWho(entity.getCreatedWho());
                    jenisDiet.setLastUpdate(entity.getLastUpdate());
                    jenisDiet.setLastUpdateWho(entity.getLastUpdateWho());
                    jenisDietList.add(jenisDiet);
                }
            }
        }
        return jenisDietList;
    }

    protected List<ImSimrsDietGizi> getListEntityDietGizi(DietGizi bean) throws GeneralBOException {
        logger.info("[DietGiziBoImpl.getListEntityDietGizi] Start >>>>>>>>");
        List<ImSimrsDietGizi> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean != null) {

            if (bean.getIdDietGizi() != null && !"".equalsIgnoreCase(bean.getIdDietGizi())) {
                hsCriteria.put("id_diet_gizi", bean.getIdDietGizi());
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                hsCriteria.put("branch_id", bean.getIdDietGizi());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                hsCriteria.put("flag", bean.getFlag());
            }

            try {
                results = dietGiziDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[DietGiziBoImpl.getListEntityDietGizi] Error When search gizi data ");
            }

        }
        logger.info("[DietGiziBoImpl.getListEntityDietGizi] End <<<<<<<<");
        return results;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setDietGiziDao(DietGiziDao dietGiziDao) {
        this.dietGiziDao = dietGiziDao;
    }

    public void setJenisDietDao(JenisDietDao jenisDietDao) {
        this.jenisDietDao = jenisDietDao;
    }
}
