package com.neurix.simrs.master.obat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisobat.dao.JenisObatDao;
import com.neurix.simrs.master.jenisobat.model.ImSimrsJenisObatEntity;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.dao.ObatDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObatBoImpl implements ObatBo {

    protected static transient Logger logger = Logger.getLogger(ObatBoImpl.class);
    private ObatDao obatDao;
    private JenisObatDao jenisObatDao;

    public void setJenisObatDao(JenisObatDao jenisObatDao) {
        this.jenisObatDao = jenisObatDao;
    }

    public void setObatDao(ObatDao obatDao) {
        this.obatDao = obatDao;
    }

    public static Logger getLogger() {
        return logger;
    }


    @Override
    public List<Obat> getByCriteria(Obat bean) throws GeneralBOException {
        logger.info("[ObatBoImpl.getByCriteria] Start >>>>>>>");
        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())) {
                hsCriteria.put("id_obat", bean.getIdObat());
            }
            if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat())) {
                hsCriteria.put("id_jenis_obat", bean.getIdJenisObat());
            }
            if (bean.getNamaObat() != null && !"".equalsIgnoreCase(bean.getNamaObat())) {
                hsCriteria.put("nama_obat", bean.getNamaObat());
            }

            hsCriteria.put("flag", "Y");

            List<ImSimrsObatEntity> obatEntityList = new ArrayList<>();
            List<Obat> result = new ArrayList<>();

            try {
                obatEntityList = obatDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[ObatBoImpl.getByCriteria] error when get data obat by get by criteria "+ e.getMessage());
            }

            if (!obatEntityList.isEmpty()){
                Obat obat;
                for (ImSimrsObatEntity obatEntity : obatEntityList){
                    obat = new Obat();
                    obat.setIdObat(obatEntity.getIdObat());
                    obat.setIdJenisObat(obatEntity.getIdJenisObat());
                    obat.setNamaObat(obatEntity.getNamaObat());
                    obat.setHarga(obatEntity.getHarga());
                    obat.setFlag(obatEntity.getFlag());
                    obat.setAction(obatEntity.getAction());
                    obat.setCreatedDate(obatEntity.getCreatedDate());
                    obat.setCreatedWho(obatEntity.getCreatedWho());
                    obat.setLastUpdate(obatEntity.getLastUpdate());
                    obat.setLastUpdateWho(obatEntity.getLastUpdateWho());

                    JenisObat jenisObat = new JenisObat();
                    jenisObat.setIdJenisObat(obatEntity.getIdJenisObat());
                    List<ImSimrsJenisObatEntity> jenisObatEntityList = getListEntityJenisObat(jenisObat);

                    ImSimrsJenisObatEntity jenisObatEntity = new ImSimrsJenisObatEntity();
                    if (!jenisObatEntityList.isEmpty()){
                        jenisObatEntity = jenisObatEntityList.get(0);
                    }
                    if(jenisObatEntity != null){
                        obat.setJenisObat(jenisObatEntity.getNamaJenisObat());
                    }

                    result.add(obat);
                }
            }

            logger.info("[ObatBoImpl.getByCriteria] End <<<<<<<");
            return result;
        }
        logger.info("[ObatBoImpl.getByCriteria] End <<<<<<<");
        return null;
    }


    public List<ImSimrsJenisObatEntity> getListEntityJenisObat(JenisObat bean) throws GeneralBOException {
        logger.info("[ObatBoImpl.getListEntityJenisObat] Start >>>>>>>");
        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat())) {
                hsCriteria.put("id_jenis_obat", bean.getIdJenisObat());
            }

            hsCriteria.put("flag", "Y");

            List<ImSimrsJenisObatEntity> jenisObatEntityList = new ArrayList<>();

            try {
                jenisObatEntityList = jenisObatDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[ObatBoImpl.getListEntityJenisObat] error when get data jenis obat by get by criteria "+ e.getMessage());
            }

            logger.info("[ObatBoImpl.getListEntityJenisObat] End <<<<<<<");
            return jenisObatEntityList;
        }
        logger.info("[ObatBoImpl.getListEntityJenisObat] End <<<<<<<");
        return null;
    }

    @Override
    public List<Obat> getListObatByJenisObat(String idObat, String branchId) throws GeneralBOException {
        logger.info("[ObatBoImpl.getListObatByJenisObat] Start >>>>>>>");

        List<Obat> obats = new ArrayList<>();

        try {
          obats = obatDao.getListObatByJenisObat(idObat, branchId);
        } catch (HibernateException e){
            logger.error("[ObatBoImpl.getListEntityJenisObat] error when get data obat by jenis obat "+ e.getMessage());
        }

        logger.info("[ObatBoImpl.getListObatByJenisObat] End <<<<<<<");
        return obats;
    }
}