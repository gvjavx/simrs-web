package com.neurix.simrs.master.obatgejala.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obatgejala.bo.ObatGejalaBo;
import com.neurix.simrs.master.obatgejala.dao.ObatGejalaDao;
import com.neurix.simrs.master.obatgejala.model.ImSimrsObatGejalaEntity;
import com.neurix.simrs.master.obatgejala.model.ObatGejala;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 03/12/2019.
 */
public class ObatGejalaBoImpl implements ObatGejalaBo{

    private static transient Logger logger = Logger.getLogger(ObatGejalaBoImpl.class);
    private ObatGejalaDao obatGejalaDao;

    @Override
    public List<ObatGejala> getByCriteria(ObatGejala bean) throws GeneralBOException {
        logger.info("[ObatGejalaBoImpl.getByCriteria] START >>>>>>>>>");

        List<ObatGejala> obatGejalas = new ArrayList<>();

        List<ImSimrsObatGejalaEntity> obatGejalaEntityList = getListEntity(bean);
        if (!obatGejalaEntityList.isEmpty() && obatGejalaEntityList.size() > 0)
        {
            ObatGejala obatGejala;
            for (ImSimrsObatGejalaEntity obatGejalaEntity : obatGejalaEntityList)
            {
                obatGejala = new ObatGejala();
                obatGejala.setIdObatGejala(obatGejalaEntity.getIdObatGejala());
                obatGejala.setIdObat(obatGejalaEntity.getIdObat());
                obatGejala.setIdJenisObat(obatGejalaEntity.getIdJenisObat());
                obatGejala.setFlag(obatGejalaEntity.getFlag());
                obatGejala.setAction(obatGejalaEntity.getAction());
                obatGejala.setCreatedDate(obatGejalaEntity.getCreatedDate());
                obatGejala.setCreatedWho(obatGejalaEntity.getCreatedWho());
                obatGejala.setLastUpdate(obatGejalaEntity.getLastUpdate());
                obatGejala.setLastUpdateWho(obatGejalaEntity.getLastUpdateWho());
                obatGejalas.add(obatGejala);

            }
        }

        logger.info("[ObatGejalaBoImpl.getByCriteria] END <<<<<<<<<");
        return obatGejalas;
    }

    private List<ImSimrsObatGejalaEntity> getListEntity(ObatGejala bean) throws GeneralBOException {
        logger.info("[ObatGejalaBoImpl.getListEntity] START >>>>>>>>>");

        Map hsCriteria = new HashMap();

        if (bean.getIdObatGejala() != null && !"".equalsIgnoreCase(bean.getIdObatGejala())){
            hsCriteria.put("id_obat_gejala", bean.getIdObatGejala());
        }

        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())){
            hsCriteria.put("id_obat", bean.getIdObat());
        }

        if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat())){
            hsCriteria.put("id_jenis_obat",bean.getIdJenisObat());
        }

        List<ImSimrsObatGejalaEntity> results = new ArrayList<>();
        try {
            results = obatGejalaDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[ObatGejalaBoImpl.getListEntity] ERROR WHEN GET data entity obat gejala, "+e.getMessage());
            throw new GeneralBOException("[ObatGejalaBoImpl.getListEntity] ERROR WHEN GET data entity obat gejala, "+e.getMessage());
        }

        logger.info("[ObatGejalaBoImpl.getListEntity] END <<<<<<<<<");
        return results;
    }

    @Override
    public void saveAdd(ObatGejala bean) throws GeneralBOException {
        logger.info("[ObatGejalaBoImpl.saveAdd] START >>>>>>>>>");

        if (bean != null)
        {
            ImSimrsObatGejalaEntity entity = new ImSimrsObatGejalaEntity();

            String id = getIdNextObatGejala();
            String userLogin = CommonUtil.userLogin();
            Timestamp time = new Timestamp(System.currentTimeMillis());

            entity.setIdObatGejala("OGJ"+id);
            entity.setIdObat(bean.getIdObat());
            entity.setIdJenisObat(bean.getIdJenisObat());
            entity.setFlag("Y");
            entity.setAction("C");
            entity.setCreatedDate(time);
            entity.setCreatedWho(userLogin);
            entity.setLastUpdate(time);
            entity.setLastUpdateWho(userLogin);

            try {
                obatGejalaDao.addAndSave(entity);
            } catch (HibernateException e){
                logger.error("[ObatGejalaBoImpl.saveAdd] ERROR WHEN insert data entity obat gejala, "+e.getMessage());
                throw new GeneralBOException("[ObatGejalaBoImpl.saveAdd] ERROR WHEN insert data entity obat gejala, "+e.getMessage());
            }
        }

        logger.info("[ObatGejalaBoImpl.saveAdd] END <<<<<<<<<");
    }


    @Override
    public void saveEdit(ObatGejala bean) throws GeneralBOException {
        logger.info("[ObatGejalaBoImpl.saveEdit] START >>>>>>>>>");

        if (bean != null && bean.getIdObatGejala() != null && !"".equalsIgnoreCase(bean.getIdObatGejala()))
        {
            List<ImSimrsObatGejalaEntity> gejalaEntityList = getListEntity(bean);
            if (bean != null)
            {
                ImSimrsObatGejalaEntity entity = gejalaEntityList.get(0);

                String userLogin = CommonUtil.userLogin();
                Timestamp time = new Timestamp(System.currentTimeMillis());

                entity.setIdObat(bean.getIdObat());
                entity.setIdJenisObat(bean.getIdJenisObat());
                entity.setFlag(bean.getFlag());
                entity.setAction(bean.getAction());
                entity.setLastUpdate(time);
                entity.setLastUpdateWho(userLogin);

                try {
                    obatGejalaDao.updateAndSave(entity);
                } catch (HibernateException e){
                    logger.error("[ObatGejalaBoImpl.saveEdit] ERROR WHEN update data entity obat gejala, "+e.getMessage());
                    throw new GeneralBOException("[ObatGejalaBoImpl.saveEdit] ERROR WHEN update data entity obat gejala, "+e.getMessage());
                }
            }
        }
        logger.info("[ObatGejalaBoImpl.saveEdit] END <<<<<<<<<");
    }

    private String getIdNextObatGejala() throws GeneralBOException{
        String id = "";

        try {
            id = obatGejalaDao.getNextId();
        } catch (HibernateException e){
            logger.error("[ObatGejalaBoImpl.getIdNextObatGejala] ERROR WHEN GET data id obat gejala, "+e.getMessage());
            throw new GeneralBOException("[ObatGejalaBoImpl.getIdNextObatGejala] ERROR WHEN GET data id obat gejala, "+e.getMessage());
        }

        return id;
    }

    public void setObatGejalaDao(ObatGejalaDao obatGejalaDao) {
        this.obatGejalaDao = obatGejalaDao;
    }

}
