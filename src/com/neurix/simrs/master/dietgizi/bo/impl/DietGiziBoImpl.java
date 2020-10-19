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
        List<DietGizi> results = new ArrayList<>();

        if (bean != null) {
            List<ImSimrsDietGizi> entities = getListEntityDietGizi(bean);
            if (!entities.isEmpty()) {
                DietGizi dietGizi;
                for (ImSimrsDietGizi gizi: entities){
                    dietGizi = new DietGizi();
                    dietGizi.setIdDietGizi(gizi.getIdDietGizi());
                    dietGizi.setNamaDietGizi(gizi.getNamaDietGizi());
                    dietGizi.setTarif(gizi.getTarif());
                    dietGizi.setBranchId(gizi.getBranchId());
                    dietGizi.setAction(gizi.getAction());
                    dietGizi.setFlag(gizi.getFlag());
                    dietGizi.setCreatedDate(gizi.getCreatedDate());
                    dietGizi.setCreatedWho(gizi.getCreatedWho());
                    dietGizi.setLastUpdate(gizi.getLastUpdate());
                    dietGizi.setLastUpdateWho(gizi.getLastUpdateWho());

                    results.add(dietGizi);
                }
            }
        }

        logger.info("[DietGiziBoImpl.getByCriteria] End <<<<<<<<");
        return results;
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
