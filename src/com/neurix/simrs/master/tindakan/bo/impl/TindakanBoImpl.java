package com.neurix.simrs.master.tindakan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.dao.TindakanDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 13/11/2019.
 */
public class TindakanBoImpl implements TindakanBo {
    private static transient Logger logger = Logger.getLogger(TindakanBoImpl.class);

    private TindakanDao tindakanDao;

    @Override
    public List<Tindakan> getByCriteria(Tindakan bean) throws GeneralBOException {
        logger.info("[TindakanBoImpl.getByCriteria] Start >>>>>>>");
        List<Tindakan> results = new ArrayList<>();

        if (bean != null){
            List<ImSimrsTindakanEntity> entityList = getListEntityTindakan(bean);
            if (!entityList.isEmpty()){
                results = setToTindakanTemplate(entityList);
            }
        }

        logger.info("[TindakanBoImpl.getByCriteria] End <<<<<<<");
        return results;
    }

    @Override
    public List<Tindakan> getComboBoxTindakan(Tindakan bean) throws GeneralBOException {
        List<Tindakan> tindakanList = new ArrayList<>();
        if(bean != null){
            try {
                tindakanList = tindakanDao.getListComboBoxTindakan(bean);
            }catch (HibernateException e){
                logger.error("Found Error"+e.getMessage());
            }
        }
        return tindakanList;
    }

    protected List<ImSimrsTindakanEntity> getListEntityTindakan(Tindakan bean) throws GeneralBOException{
        logger.info("[TindakanBoImpl.getListEntityTindakan] Start >>>>>>>");
        List<ImSimrsTindakanEntity> results = new ArrayList<>();

        Map hsCiteria = new HashMap();

        if (bean.getIdTindakan() != null && !"".equalsIgnoreCase(bean.getIdTindakan())){
            hsCiteria.put("id_tindakan", bean.getIdTindakan());
        }
        if (bean.getIdKategoriTindakan() != null && !"".equalsIgnoreCase(bean.getIdKategoriTindakan())){
            hsCiteria.put("id_kategori_tindakan", bean.getIdKategoriTindakan());
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            hsCiteria.put("branch_id", bean.getBranchId());
        }
        if (bean.getIsIna() != null && !"".equalsIgnoreCase(bean.getIsIna())){
            hsCiteria.put("is_ina", bean.getIsIna());
        }
        hsCiteria.put("flag", "Y");

        try {
            results = tindakanDao.getByCriteria(hsCiteria);
        } catch (HibernateException e){
            logger.error("[TindakanBoImpl.getListEntityTindakan] Error when get data tindakan ", e);
        }

        logger.info("[TindakanBoImpl.getListEntityTindakan] End <<<<<<<");
        return results;
    }

    protected List<Tindakan> setToTindakanTemplate(List<ImSimrsTindakanEntity> entities) throws GeneralBOException{
        logger.info("[TindakanBoImpl.setToTindakanTemplate] Start >>>>>>>");
        List<Tindakan> results = new ArrayList<>();

        Tindakan tindakan;
        for (ImSimrsTindakanEntity entity : entities){
            tindakan = new Tindakan();
            tindakan.setIdTindakan(entity.getIdTindakan());
            tindakan.setTindakan(entity.getTindakan());
            tindakan.setIdKategoriTindakan(entity.getIdKategoriTindakan());
            tindakan.setTarif(entity.getTarif());
            tindakan.setTarifBpjs(entity.getTarifBpjs());
            tindakan.setFlag(entity.getFlag());
            tindakan.setAction(entity.getAction());
            tindakan.setCreatedDate(entity.getCreatedDate());
            tindakan.setCreatedWho(entity.getCreatedWho());
            tindakan.setLastUpdate(entity.getLastUpdate());
            tindakan.setLastUpdateWho(entity.getLastUpdateWho());
            results.add(tindakan);
        }

        logger.info("[TindakanBoImpl.setToTindakanTemplate] End <<<<<<<");
        return results;
    }

    public void setTindakanDao(TindakanDao tindakanDao) {
        this.tindakanDao = tindakanDao;
    }
}
