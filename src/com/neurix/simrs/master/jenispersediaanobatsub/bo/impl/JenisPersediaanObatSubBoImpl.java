package com.neurix.simrs.master.jenispersediaanobatsub.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenispersediaanobat.model.ImSimrsJenisPersediaanObatEntity;
import com.neurix.simrs.master.jenispersediaanobat.model.JenisPersediaanObat;
import com.neurix.simrs.master.jenispersediaanobatsub.bo.JenisPersediaanObatSubBo;
import com.neurix.simrs.master.jenispersediaanobatsub.dao.JenisPersdiaanObatSubDao;
import com.neurix.simrs.master.jenispersediaanobatsub.model.ImSimrsJenisPersediaanObatSubEntity;
import com.neurix.simrs.master.jenispersediaanobatsub.model.JenisPersediaanObatSub;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JenisPersediaanObatSubBoImpl implements JenisPersediaanObatSubBo{
    private static transient Logger logger = Logger.getLogger(JenisPersediaanObatSubBoImpl.class);
    private JenisPersdiaanObatSubDao jenisPersdiaanObatSubDao;

    public void setJenisPersdiaanObatSubDao(JenisPersdiaanObatSubDao jenisPersdiaanObatSubDao) {
        this.jenisPersdiaanObatSubDao = jenisPersdiaanObatSubDao;
    }

    @Override
    public List<ImSimrsJenisPersediaanObatSubEntity> getListEntity(JenisPersediaanObatSub bean) throws GeneralBOException {
        logger.info("[JenisPersediaanObatSubBoImpl.getListEntity] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId()))
            hsCriteria.put("id", bean.getId());
        if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama()))
            hsCriteria.put("nama", bean.getNama());
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag()))
            hsCriteria.put("flag", bean.getFlag());
        if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat()))
            hsCriteria.put("id_jenis_obat", bean.getIdJenisObat());

        List<ImSimrsJenisPersediaanObatSubEntity> jenisPersediaanObatEntities = new ArrayList<>();

        try {
            jenisPersediaanObatEntities = jenisPersdiaanObatSubDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[JenisPersediaanObatSubBoImpl.getListEntitiyByCriteria] ERROR, error when. ",e);
            throw new GeneralBOException("[JenisPersediaanObatSubBoImpl.getListEntitiyByCriteria] ERROR, error when. "+e);
        }

        logger.info("[JenisPersediaanObatSubBoImpl.getListEntity] END <<<");
        return jenisPersediaanObatEntities;
    }

    @Override
    public List<JenisPersediaanObatSub> getSearchByCriteria(JenisPersediaanObatSub bean) throws GeneralBOException {
        logger.info("[JenisPersediaanObatSubBoImpl.getSearchByCriteria] START >>>");

        List<ImSimrsJenisPersediaanObatSubEntity> jenisPersediaanObatSubEntities = new ArrayList<>();

        try {
            jenisPersediaanObatSubEntities = getListEntity(bean);
        } catch (HibernateException e){
            logger.error("[JenisPersediaanObatSubBoImpl.getListSearchByCriteria] ERROR, error when. ",e);
            throw new GeneralBOException("[JenisPersediaanObatSubBoImpl.getListSearchByCriteria] ERROR, error when. "+e);
        }

        List<JenisPersediaanObatSub> jenisPersediaanObatSubs = listEntityToListModel(jenisPersediaanObatSubEntities);

        logger.info("[JenisPersediaanObatSubBoImpl.getSearchByCriteria] END <<<");
        return jenisPersediaanObatSubs;
    }

    @Override
    public List<JenisPersediaanObatSub> getAll() throws GeneralBOException {
        return listEntityToListModel(jenisPersdiaanObatSubDao.getAll());
    }

    private List<JenisPersediaanObatSub> listEntityToListModel(List<ImSimrsJenisPersediaanObatSubEntity> list) throws GeneralBOException{
        List<JenisPersediaanObatSub> jenisPersediaanObatSubs = new ArrayList<>();
        if (list != null && list.size() > 0){
            for (ImSimrsJenisPersediaanObatSubEntity paramEntity : list){
                JenisPersediaanObatSub jenisPersediaanObatSub = new JenisPersediaanObatSub();
                jenisPersediaanObatSub.setId(paramEntity.getId());
                jenisPersediaanObatSub.setNama(paramEntity.getNama());
                jenisPersediaanObatSub.setIdJenisObat(paramEntity.getIdJenisObat());
                jenisPersediaanObatSub.setFlag(paramEntity.getFlag());
                jenisPersediaanObatSub.setAction(paramEntity.getAction());
                jenisPersediaanObatSub.setCreatedDate(paramEntity.getCreatedDate());
                jenisPersediaanObatSub.setCreatedWho(paramEntity.getCreatedWho());
                jenisPersediaanObatSub.setLastUpdate(paramEntity.getLastUpdate());
                jenisPersediaanObatSub.setLastUpdateWho(paramEntity.getLastUpdateWho());
                jenisPersediaanObatSubs.add(jenisPersediaanObatSub);
            }
        }
        return jenisPersediaanObatSubs;
    }
}
