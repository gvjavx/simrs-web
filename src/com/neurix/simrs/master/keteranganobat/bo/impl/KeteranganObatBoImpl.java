package com.neurix.simrs.master.keteranganobat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenispersediaanobat.dao.JenisPersediaanObatDao;
import com.neurix.simrs.master.jenispersediaanobat.model.ImSimrsJenisPersediaanObatEntity;
import com.neurix.simrs.master.jenispersediaanobatsub.dao.JenisPersdiaanObatSubDao;
import com.neurix.simrs.master.jenispersediaanobatsub.model.ImSimrsJenisPersediaanObatSubEntity;
import com.neurix.simrs.master.keteranganobat.bo.KeteranganObatBo;
import com.neurix.simrs.master.keteranganobat.dao.KeteranganObatDao;
import com.neurix.simrs.master.keteranganobat.model.ImSimrsKeteranganObatEntity;
import com.neurix.simrs.master.keteranganobat.model.KeteranganObat;
import com.neurix.simrs.master.parameterketeranganobat.dao.ParameterKeteranganObatDao;
import com.neurix.simrs.master.parameterketeranganobat.model.ImSimrsParameterKeteranganObatEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public void saveAdd(KeteranganObat bean) throws GeneralBOException {
        logger.info("[KeteranganObatBoImpl.saveAdd] START >>> ");

        boolean found = false;
        try {
            found = keteranganObatDao.checkIfAvailableByCriteria(bean.getIdSubJenis(), bean.getIdParameterKeterangan(), bean.getKeterangan(), bean.getWarnaLabel(), bean.getWarnaBackground());
        } catch (HibernateException e){
            logger.error("[ParameterKeteranganObatBoImpl.saveAdd] ERROR, error when. ",e);
            throw new GeneralBOException("[ParameterKeteranganObatBoImpl.saveAdd] ERROR, error when. "+e);
        }

        if (found){
            throw new GeneralBOException("Tidak dapat insert karna data yang sama sudah ada. ");
        }

        ImSimrsKeteranganObatEntity keteranganObatEntity = new ImSimrsKeteranganObatEntity();
        keteranganObatEntity.setId(getNextId());
        keteranganObatEntity.setIdSubJenis(bean.getIdSubJenis());
        keteranganObatEntity.setIdParameterKeterangan(bean.getIdParameterKeterangan());
        keteranganObatEntity.setKeterangan(bean.getKeterangan());
        keteranganObatEntity.setCreatedDate(bean.getCreatedDate());
        keteranganObatEntity.setCreatedWho(bean.getCreatedWho());
        keteranganObatEntity.setLastUpdate(bean.getLastUpdate());
        keteranganObatEntity.setLastUpdateWho(bean.getLastUpdateWho());
        keteranganObatEntity.setFlag("Y");
        keteranganObatEntity.setAction("C");
        keteranganObatEntity.setWarnaLabel(bean.getWarnaLabel());
        keteranganObatEntity.setWarnaBackground(bean.getWarnaBackground());

        try {
            keteranganObatDao.addAndSave(keteranganObatEntity);
        } catch (HibernateException e){
            logger.error("[ParameterKeteranganObatBoImpl.saveAdd] ERROR, error when. ",e);
            throw new GeneralBOException("[ParameterKeteranganObatBoImpl.saveAdd] ERROR, error when. "+e);
        }

        logger.info("[KeteranganObatBoImpl.saveAdd] END <<< ");
    }

    @Override
    public void saveEdit(KeteranganObat bean) throws GeneralBOException {
        logger.info("[KeteranganObatBoImpl.saveEdit] START >>> ");

        boolean found = false;
        if ("Y".equalsIgnoreCase(bean.getFlag())){
            try {
                found = keteranganObatDao.checkIfAvailableByCriteria(bean.getIdSubJenis(), bean.getIdParameterKeterangan(), bean.getKeterangan(), bean.getWarnaLabel(), bean.getWarnaBackground());
            } catch (HibernateException e){
                logger.error("[ParameterKeteranganObatBoImpl.saveEdit] ERROR, ",e);
                throw new GeneralBOException("[ParameterKeteranganObatBoImpl.saveEdit] ERROR, "+e);
            }
        }

        if (found){
            throw new GeneralBOException("Tidak dapat insert karna data yang sama sudah ada. ");
        }

        ImSimrsKeteranganObatEntity keteranganObatEntity = new ImSimrsKeteranganObatEntity();
        try {
            keteranganObatEntity = keteranganObatDao.getById("id", bean.getId());
        } catch (HibernateException e){
            logger.error("[ParameterKeteranganObatBoImpl.saveEdit] ERROR, ",e);
            throw new GeneralBOException("[ParameterKeteranganObatBoImpl.saveEdit] ERROR, "+e);
        }

        if (keteranganObatEntity != null && keteranganObatEntity.getId() != null){
            keteranganObatEntity.setIdSubJenis(bean.getIdSubJenis());
            keteranganObatEntity.setIdParameterKeterangan(bean.getIdParameterKeterangan());
            keteranganObatEntity.setKeterangan(bean.getKeterangan());
            keteranganObatEntity.setWarnaLabel(bean.getWarnaLabel());
            keteranganObatEntity.setWarnaBackground(bean.getWarnaBackground());
            keteranganObatEntity.setFlag(bean.getFlag());
            keteranganObatEntity.setAction("U");
            keteranganObatEntity.setLastUpdate(bean.getLastUpdate());
            keteranganObatEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                keteranganObatDao.updateAndSave(keteranganObatEntity);
            } catch (HibernateException e){
                logger.error("[ParameterKeteranganObatBoImpl.saveEdit] ERROR, ",e);
                throw new GeneralBOException("[ParameterKeteranganObatBoImpl.saveEdit] ERROR, "+e);
            }
        }

        logger.info("[KeteranganObatBoImpl.saveEdit] END <<< ");
    }

    @Override
    public List<KeteranganObat> getKeteranganObat(String idParam) throws GeneralBOException {
        logger.info("[KeteranganObatBoImpl.getKeteranganObat] START >>> ");
        List<KeteranganObat> keteranganObatList = new ArrayList<>();
        try {
            keteranganObatList = keteranganObatDao.getKeteranganObat(idParam);
        }catch (HibernateException e){
            logger.error(e);
        }
        logger.info("[KeteranganObatBoImpl.getKeteranganObat] END <<< ");
        return keteranganObatList;
    }

    @Override
    public List<ImSimrsParameterKeteranganObatEntity> getAllParameterKeterangan() throws GeneralBOException {
        logger.info("[KeteranganObatBoImpl.getAllParameterKeterangan] START >>> ");
        logger.info("[KeteranganObatBoImpl.getAllParameterKeterangan] END <<< ");
        return parameterKeteranganObatDao.getAll();
    }

    @Override
    public List<ImSimrsJenisPersediaanObatEntity> getAllJenisPersedian() throws GeneralBOException {
        logger.info("[KeteranganObatBoImpl.getAllJenisPersedian] START >>> ");
        logger.info("[KeteranganObatBoImpl.getAllJenisPersedian] END <<< ");
        return jenisPersediaanObatDao.getAll();
    }

    @Override
    public List<ImSimrsJenisPersediaanObatSubEntity> getAllJenisPersediaanSub() throws GeneralBOException {
        logger.info("[KeteranganObatBoImpl.getAllJenisPersediaanSub] START >>> ");
        logger.info("[KeteranganObatBoImpl.getAllJenisPersediaanSub] END <<< ");
        return jenisPersdiaanObatSubDao.getAll();
    }

    private String getNextId() throws GeneralBOException{
        logger.info("[KeteranganObatBoImpl.getNextId] START >>> ");

        String id = "";
        try {
            id = keteranganObatDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[ParameterKeteranganObatBoImpl.getNextId] ERROR, error when. ",e);
            throw new GeneralBOException("[ParameterKeteranganObatBoImpl.getNextId] ERROR, error when. "+e);
        }

        logger.info("[KeteranganObatBoImpl.getNextId] END <<< ");
        return id;
    }

    @Override
    public List<KeteranganObat> getListKeteranganObatBySubJenis(String id) {
        logger.info("[KeteranganObatBoImpl.getListKeteranganObatBySubJenis] START >>> ");

        List<KeteranganObat> results = new ArrayList<>();
        List<KeteranganObat> keteranganObatList = new ArrayList<>();

        try {
            keteranganObatList = keteranganObatDao.getListKeteranganObatBySubJenisObat(id);
        } catch (HibernateException e){
            logger.error("[ParameterKeteranganObatBoImpl.getListKeteranganObatBySubJenis] ERROR, error when. ",e);
            throw new GeneralBOException("[ParameterKeteranganObatBoImpl.getListKeteranganObatBySubJenis] ERROR, error when. "+e);
        }

        int maxlevel = new Integer(0);

        try {
            maxlevel = keteranganObatDao.getMaxLevelOfKeteranganBySubJenis(id);
        } catch (HibernateException e){
            logger.error("[ParameterKeteranganObatBoImpl.getListKeteranganObatBySubJenis] ERROR, error when. ",e);
            throw new GeneralBOException("[ParameterKeteranganObatBoImpl.getListKeteranganObatBySubJenis] ERROR, error when. "+e);
        }

        if (keteranganObatList.size() == 0 || maxlevel == 0){
            KeteranganObat keteranganObat = new KeteranganObat();
            keteranganObat.setKeterangan("lainnya");
            results.add(keteranganObat);
            return results;
        }

        if (keteranganObatList.size() > 0){
            List<KeteranganObat> listOfLevel2Keatas = keteranganObatList.stream().filter(
                    p->p.getId() != "1"
            ).collect(Collectors.toList());

            results.addAll(listOfLevel2Keatas);

//            for (int i = maxlevel ; i > 0 ; i--){
//
//                if (i == 1){
//                    break;
//                }
//
//                List<KeteranganObat> currentLevel = filteredByLevel(listOfLevel2Keatas, String.valueOf(i));
//                if (currentLevel == null)
//                    break;
//
//                int levelParent = i-1;
//                for (KeteranganObat keteranganObat : currentLevel){
//
//                    if (keteranganObat.getParentId() != null && !"".equalsIgnoreCase(keteranganObat.getParentId()))
//                        results.add(keteranganObat);
//                    else {
//
//                        List<KeteranganObat> parentLevel = filteredByLevel(listOfLevel2Keatas, String.valueOf(levelParent));
//                        if (parentLevel == null)
//                            break;
//
//                        for (KeteranganObat keteranganParent : parentLevel){
//                            keteranganObat.setParentId(keteranganParent.getId());
//                            results.add(keteranganObat);
//                        }
//                    }
//
//                }
//            }
        }

//        List<KeteranganObat> resultSigna = new ArrayList<>();
//        if (listOfSigna.size() > 0){
//
//            for (int i = 0; i <= maxlevel; i ++){
//                List<KeteranganObat> currentLevel = filteredByLevel(listOfSigna, String.valueOf(i));
//
//                if (currentLevel.size() > 0){
//                    for (KeteranganObat keteranganObat : currentLevel){
//                        List<KeteranganObat> listChild = filteredByParent(listOfSigna, keteranganObat.getParentId());
//                        if (listChild.size() > 0){
//                            for (KeteranganObat signaChild : listChild){
//                                if (resultSigna.size() == 0){
//                                    KeteranganObat signa = new KeteranganObat();
//                                    signa.setKeterangan(keteranganObat.getKeterangan()+"-"+signaChild.getKeterangan()+"-");
//                                    resultSigna.add(signa);
//                                } else {
//
//                                    List<KeteranganObat> filterdByKeterangan = filteredByKeterangan(listOfSigna, keteranganObat.getKeterangan()+"-"+signaChild.getKeterangan()+"-");
//                                    if (filterdByKeterangan.size() > 0){
//                                        KeteranganObat filteredSigna =  filterdByKeterangan.get(0);
//                                        filteredSigna.setKeterangan(filteredSigna.getKeterangan()+signaChild.getKeterangan()+"-");
//                                    } else {
//                                        KeteranganObat signa = new KeteranganObat();
//                                        signa.setKeterangan(keteranganObat.getKeterangan()+"-"+signaChild.getKeterangan()+"-");
//                                        resultSigna.add(signa);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }

        logger.info("[KeteranganObatBoImpl.getListKeteranganObatBySubJenis] END <<< ");
        return results;
    }

    @Override
    public String getIdSubJenisObat(String idObat) {
        logger.info("[KeteranganObatBoImpl.getIdSubJenisObat] START >>> ");

        String idSubJenis = "";

        try {
            idSubJenis = keteranganObatDao.getIdSubJenisObat(idObat);
        } catch (HibernateException e){
            logger.error("[ParameterKeteranganObatBoImpl.getIdSubJenisObat] ERROR, error when. ",e);
            throw new GeneralBOException("[ParameterKeteranganObatBoImpl.getIdSubJenisObat] ERROR, error when. "+e);
        }

        logger.info("[KeteranganObatBoImpl.getIdSubJenisObat] END <<< ");
        return idSubJenis;
    }

    private List<KeteranganObat> filteredByLevel(List<KeteranganObat> keteranganObatList, String level){
        logger.info("[KeteranganObatBoImpl.filteredByLevel] START >>> ");

        if (keteranganObatList.size() == 0){
            return null;
        }

        logger.info("[KeteranganObatBoImpl.filteredByLevel] END <<< ");
        return keteranganObatList.stream().filter(
                p->p.getLevel().equalsIgnoreCase(level)
        ).collect(Collectors.toList());
    }

    private List<KeteranganObat> filteredByParent(List<KeteranganObat> keteranganObatList, String parentId){
        logger.info("[KeteranganObatBoImpl.filteredByParent] START >>> ");

        if (keteranganObatList.size() == 0){
            return null;
        }

        logger.info("[KeteranganObatBoImpl.filteredByParent] END <<< ");
        return keteranganObatList.stream().filter(
                p->p.getParentId().equalsIgnoreCase(parentId)
        ).collect(Collectors.toList());
    }

    private List<KeteranganObat> filteredByKeterangan(List<KeteranganObat> keteranganObatList, String keterangan){
        logger.info("[KeteranganObatBoImpl.filteredByKeterangan] START >>> ");

        if (keteranganObatList.size() == 0){
            return null;
        }

        logger.info("[KeteranganObatBoImpl.filteredByKeterangan] END <<< ");
        return keteranganObatList.stream().filter(
                p->p.getKeterangan().contains(keterangan)
        ).collect(Collectors.toList());
    }
}
