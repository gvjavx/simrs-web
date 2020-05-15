package com.neurix.simrs.master.kategoritindakanina.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kategoritindakanina.bo.KategoriTindakanInaBo;
import com.neurix.simrs.master.kategoritindakanina.dao.KategoriTindakanInaDao;
import com.neurix.simrs.master.kategoritindakanina.model.ImSimrsKategoriTindakanInaEntity;
import com.neurix.simrs.master.kategoritindakanina.model.KategoriTindakanIna;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KategoriTindakanInaBoImpl implements KategoriTindakanInaBo{
    protected static transient Logger logger = Logger.getLogger(KategoriTindakanInaBoImpl.class);
    private KategoriTindakanInaDao kategoriTindakanInaDao;

    public void setKategoriTindakanInaDao(KategoriTindakanInaDao kategoriTindakanInaDao) {
        this.kategoriTindakanInaDao = kategoriTindakanInaDao;
    }

    @Override
    public void saveDelete(KategoriTindakanIna bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(KategoriTindakanIna bean) throws GeneralBOException {

    }

    @Override
    public KategoriTindakanIna saveAdd(KategoriTindakanIna bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<KategoriTindakanIna> getByCriteria(KategoriTindakanIna bean) throws GeneralBOException {
        logger.info("[KategoriTindakanInaBoImpl.getByCriteria] Start >>>>>>");
        List<KategoriTindakanIna> result = new ArrayList<>();

        if(bean != null){

            Map hsCriteria = new HashMap();

            if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId())){
                hsCriteria.put("id", bean.getId());
            }
            if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama())){
                hsCriteria.put("nama", bean.getNama());
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

            List<ImSimrsKategoriTindakanInaEntity> entityList = new ArrayList<>();

            try {
                entityList = kategoriTindakanInaDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PelayananBoImpl.getByCriteria] Error get pelayanan data "+e.getMessage());
            }

            if (!entityList.isEmpty()){
                KategoriTindakanIna kategoriTindakanIna;
                for (ImSimrsKategoriTindakanInaEntity entity : entityList){
                    kategoriTindakanIna = new KategoriTindakanIna();
                    kategoriTindakanIna.setId(entity.getId());
                    kategoriTindakanIna.setNama(entity.getNama());
                    kategoriTindakanIna.setAction(entity.getAction());
                    kategoriTindakanIna.setFlag(entity.getFlag());
                    kategoriTindakanIna.setCreatedDate(entity.getCreatedDate());
                    kategoriTindakanIna.setCreatedWho(entity.getCreatedWho());
                    kategoriTindakanIna.setLastUpdate(entity.getLastUpdate());
                    kategoriTindakanIna.setLastUpdateWho(entity.getLastUpdateWho());

                    result.add(kategoriTindakanIna);
                }
            }
        }

        logger.info("[KategoriTindakanInaBoImpl.getByCriteria] End <<<<<<");
        return result;
    }

    @Override
    public List<KategoriTindakanIna> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}