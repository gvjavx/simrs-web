package com.neurix.simrs.master.jenispersediaanobat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenispersediaanobat.bo.JenisPersediaanObatBo;
import com.neurix.simrs.master.jenispersediaanobat.dao.JenisPersediaanObatDao;
import com.neurix.simrs.master.jenispersediaanobat.model.ImSimrsJenisPersediaanObatEntity;
import com.neurix.simrs.master.jenispersediaanobat.model.JenisPersediaanObat;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JenisPersediaanObatBoImpl implements JenisPersediaanObatBo{
    private static transient Logger logger = Logger.getLogger(JenisPersediaanObatBoImpl.class);
    private JenisPersediaanObatDao jenisPersediaanObatDao;

    public void setJenisPersediaanObatDao(JenisPersediaanObatDao jenisPersediaanObatDao) {
        this.jenisPersediaanObatDao = jenisPersediaanObatDao;
    }

    @Override
    public List<ImSimrsJenisPersediaanObatEntity> getListEntity(JenisPersediaanObat bean) throws GeneralBOException {
        logger.info("[JenisPersediaanObatBoImpl.getListEntity] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId()))
            hsCriteria.put("id", bean.getId());
        if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama()))
            hsCriteria.put("nama", bean.getNama());
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag()))
            hsCriteria.put("flag", bean.getFlag());

        List<ImSimrsJenisPersediaanObatEntity> jenisPersediaanObatEntities = new ArrayList<>();

        try {
            jenisPersediaanObatEntities = jenisPersediaanObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[JenisPersediaanObatBoImpl.getListEntitiyByCriteria] ERROR, error when. ",e);
            throw new GeneralBOException("[JenisPersediaanObatBoImpl.getListEntitiyByCriteria] ERROR, error when. "+e);
        }

        logger.info("[JenisPersediaanObatBoImpl.getListEntity] END <<<");
        return jenisPersediaanObatEntities;
    }

    @Override
    public List<JenisPersediaanObat> getSearchByCriteria(JenisPersediaanObat bean) throws GeneralBOException {
        logger.info("[JenisPersediaanObatBoImpl.getSearchByCriteria] START >>>");

        List<ImSimrsJenisPersediaanObatEntity> jenisPersediaanObatEntities = new ArrayList<>();

        try {
            jenisPersediaanObatEntities = getListEntity(bean);
        } catch (HibernateException e){
            logger.error("[JenisPersediaanObatBoImpl.getListSearchByCriteria] ERROR, error when. ",e);
            throw new GeneralBOException("[JenisPersediaanObatBoImpl.getListSearchByCriteria] ERROR, error when. "+e);
        }

        List<JenisPersediaanObat> jenisPersediaanObats = listEntityToListModel(jenisPersediaanObatEntities);

        logger.info("[JenisPersediaanObatBoImpl.getSearchByCriteria] END <<<");
        return jenisPersediaanObats;
    }

    @Override
    public List<JenisPersediaanObat> getAll() throws GeneralBOException {
        return listEntityToListModel(jenisPersediaanObatDao.getAll());
    }

    private List<JenisPersediaanObat> listEntityToListModel(List<ImSimrsJenisPersediaanObatEntity> list) throws GeneralBOException{
        List<JenisPersediaanObat> jenisPersediaanObats = new ArrayList<>();
        if (list != null && list.size() > 0){
            for (ImSimrsJenisPersediaanObatEntity paramEntity : list){
                JenisPersediaanObat jenisPersediaanObat = new JenisPersediaanObat();
                jenisPersediaanObat.setId(paramEntity.getId());
                jenisPersediaanObat.setNama(paramEntity.getNama());
                jenisPersediaanObat.setFlag(paramEntity.getFlag());
                jenisPersediaanObat.setAction(paramEntity.getAction());
                jenisPersediaanObat.setCreatedDate(paramEntity.getCreatedDate());
                jenisPersediaanObat.setCreatedWho(paramEntity.getCreatedWho());
                jenisPersediaanObat.setLastUpdate(paramEntity.getLastUpdate());
                jenisPersediaanObat.setLastUpdateWho(paramEntity.getLastUpdateWho());
                jenisPersediaanObats.add(jenisPersediaanObat);
            }
        }
        return jenisPersediaanObats;
    }
}
