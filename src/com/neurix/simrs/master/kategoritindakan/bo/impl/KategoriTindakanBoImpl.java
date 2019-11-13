package com.neurix.simrs.master.kategoritindakan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kategoritindakan.dao.KategoriTindakanDao;
import com.neurix.simrs.master.kategoritindakan.model.ImSimrsKategoriTindakanEntity;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 13/11/2019.
 */
public class KategoriTindakanBoImpl implements KategoriTindakanBo {
    private static transient Logger logger = Logger.getLogger(KategoriTindakanBoImpl.class);
    private KategoriTindakanDao kategoriTindakanDao;

    @Override
    public List<KategoriTindakan> getByCriteria(KategoriTindakan bean) throws GeneralBOException {
        logger.info("[KategoriTindakanBoImpl.getByCriteria] Start >>>>>>>");
        List<KategoriTindakan> results = new ArrayList<>();

        if (bean != null){
            List<ImSimrsKategoriTindakanEntity> entityList = getListEntityKategoriTindakan(bean);
            if (!entityList.isEmpty()){
                results = setToKategoriTindakanTemplate(entityList);
            }
        }
        logger.info("[KategoriTindakanBoImpl.getByCriteria] End <<<<<<<");
        return null;
    }

    protected List<ImSimrsKategoriTindakanEntity> getListEntityKategoriTindakan(KategoriTindakan bean) throws GeneralBOException {
        logger.info("[KategoriTindakanBoImpl.getListEntityKategoriTindakan] Start >>>>>>>");
        List<ImSimrsKategoriTindakanEntity> results = new ArrayList<>();

        Map hsCiteria = new HashMap();

        if (bean.getIdKategoriTindakan() != null && !"".equalsIgnoreCase(bean.getIdKategoriTindakan())){
            hsCiteria.put("id_kategori_tindakan", bean.getIdKategoriTindakan());
        }
        hsCiteria.put("flag", "Y");

        try {
            results = kategoriTindakanDao.getByCriteria(hsCiteria);
        } catch (HibernateException e){
            logger.error("[KategoriTindakanBoImpl.getListEntityKategoriTindakan] Error when get data tindakan ", e);
        }

        logger.info("[KategoriTindakanBoImpl.getListEntityKategoriTindakan] End <<<<<<<");
        return results;
    }

    protected List<KategoriTindakan> setToKategoriTindakanTemplate(List<ImSimrsKategoriTindakanEntity> entities) throws GeneralBOException{
        logger.info("[KategoriTindakanBoImpl.setToKategoriTindakanTemplate] Start >>>>>>>");
        List<KategoriTindakan> results = new ArrayList<>();

        KategoriTindakan kategoriTindakan;
        for (ImSimrsKategoriTindakanEntity entity : entities){
            kategoriTindakan = new KategoriTindakan();
            kategoriTindakan.setIdKategoriTindakan(entity.getKategoriTindakan());
            kategoriTindakan.setKategoriTindakan(entity.getKategoriTindakan());
            kategoriTindakan.setFlag(entity.getFlag());
            kategoriTindakan.setAction(entity.getAction());
            kategoriTindakan.setCreatedDate(entity.getCreatedDate());
            kategoriTindakan.setCreatedWho(entity.getCreatedWho());
            kategoriTindakan.setLastUpdate(entity.getLastUpdate());
            kategoriTindakan.setLastUpdateWho(entity.getLastUpdateWho());
            results.add(kategoriTindakan);
        }

        logger.info("[KategoriTindakanBoImpl.setToKategoriTindakanTemplate] End <<<<<<<");
        return results;
    }


    public void setKategoriTindakanDao(KategoriTindakanDao kategoriTindakanDao) {
        this.kategoriTindakanDao = kategoriTindakanDao;
    }
}
