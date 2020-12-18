package com.neurix.simrs.master.keteranganobat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenispersediaanobat.dao.JenisPersediaanObatDao;
import com.neurix.simrs.master.jenispersediaanobatsub.dao.JenisPersdiaanObatSubDao;
import com.neurix.simrs.master.keteranganobat.bo.KeteranganObatBo;
import com.neurix.simrs.master.keteranganobat.dao.KeteranganObatDao;
import com.neurix.simrs.master.keteranganobat.model.ImSimrsKeteranganObatEntity;
import com.neurix.simrs.master.keteranganobat.model.KeteranganObat;
import com.neurix.simrs.master.parameterketeranganobat.dao.ParameterKeteranganObatDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeteranganObatBoImpl implements KeteranganObatBo{
    private static transient Logger logger = Logger.getLogger(KeteranganObatBoImpl.class);
    private KeteranganObatDao keteranganObatDao;
    private ParameterKeteranganObatDao parameterKeteranganObatDao;
    private JenisPersdiaanObatSubDao jenisPersdiaanObatSubDao;
    private JenisPersediaanObatDao jenisPersediaanObatDao;

    public void setKeteranganObatDao(KeteranganObatDao keteranganObatDao) {
        this.keteranganObatDao = keteranganObatDao;
    }

    public void setParameterKeteranganObatDao(ParameterKeteranganObatDao parameterKeteranganObatDao) {
        this.parameterKeteranganObatDao = parameterKeteranganObatDao;
    }

    public void setJenisPersdiaanObatSubDao(JenisPersdiaanObatSubDao jenisPersdiaanObatSubDao) {
        this.jenisPersdiaanObatSubDao = jenisPersdiaanObatSubDao;
    }

    public void setJenisPersediaanObatDao(JenisPersediaanObatDao jenisPersediaanObatDao) {
        this.jenisPersediaanObatDao = jenisPersediaanObatDao;
    }

    @Override
    public List<ImSimrsKeteranganObatEntity> getListEntitiyByCriteria(KeteranganObat bean) throws GeneralBOException {
        logger.info("[KeteranganObatBoImpl.getListEntitiyByCriteria] START >>> ");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId()))
            hsCriteria.put("id", bean.getId());
        if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan()))
            hsCriteria.put("keterangan", bean.getKeterangan());
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag()))
            hsCriteria.put("flag", bean.getFlag());
        if (bean.getIdSubJenis() != null && !"".equalsIgnoreCase(bean.getIdSubJenis()))
            hsCriteria.put("id_sub_jenis", bean.getIdSubJenis());
        if (bean.getIdParameterKeterangan() != null && !"".equalsIgnoreCase(bean.getIdParameterKeterangan()))
            hsCriteria.put("id_parameter_keterangan", bean.getIdParameterKeterangan());

        List<ImSimrsKeteranganObatEntity> keteranganObatEntities = new ArrayList<>();

        try {
            keteranganObatEntities = keteranganObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[ParameterKeteranganObatBoImpl.getListEntitiyByCriteria] ERROR, error when. ",e);
            throw new GeneralBOException("[ParameterKeteranganObatBoImpl.getListEntitiyByCriteria] ERROR, error when. "+e);
        }

        logger.info("[KeteranganObatBoImpl.getListEntitiyByCriteria] END <<< ");
        return keteranganObatEntities;
    }

    @Override
    public List<KeteranganObat> getListSearchByCriteria(KeteranganObat bean) throws GeneralBOException {
        logger.info("[KeteranganObatBoImpl.getListSearchByCriteria] START >>> ");

        List<KeteranganObat> keteranganObats = new ArrayList<>();

        try {
            keteranganObats = keteranganObatDao.getListSearchKeteranganObat(bean);
        } catch (HibernateException e){
            logger.error("[ParameterKeteranganObatBoImpl.getListSearchByCriteria] ERROR, error when. ",e);
            throw new GeneralBOException("[ParameterKeteranganObatBoImpl.getListSearchByCriteria] ERROR, error when. "+e);
        }

        logger.info("[KeteranganObatBoImpl.getListSearchByCriteria] END <<< ");
        return keteranganObats;
    }

    @Override
    public void saveAdd(List<KeteranganObat> listBean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(List<KeteranganObat> listBean) throws GeneralBOException {

    }

    @Override
    public List<KeteranganObat> getKeteranganObat(String idParam) throws GeneralBOException {
        List<KeteranganObat> keteranganObatList = new ArrayList<>();
        try {
            keteranganObatList = keteranganObatDao.getKeteranganObat(idParam);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return keteranganObatList;
    }
}
