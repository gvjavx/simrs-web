package com.neurix.simrs.master.kurir.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kurir.bo.KurirBo;
import com.neurix.simrs.master.kurir.dao.KurirDao;
import com.neurix.simrs.master.kurir.model.ImSimrsKurirEntity;
import com.neurix.simrs.master.kurir.model.Kurir;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author gondok
 * Tuesday, 16/06/20 10:47
 */
public class KurirBoImpl implements KurirBo {
    protected static transient Logger logger = org.apache.log4j.Logger.getLogger(KurirBoImpl.class);

    private KurirDao kurirDao;

    public KurirDao getKurirDao() {
        return kurirDao;
    }

    public void setKurirDao(KurirDao kurirDao) {
        this.kurirDao = kurirDao;
    }

    private Date date;

    @Override
    public List<Kurir> getByCriteria(Kurir bean) throws GeneralBOException {
        logger.info("[KurirBoImpl.getByCriteria] Start >>>>>>>");

        List<Kurir> kurirs = new ArrayList<>();
        if (bean != null) {
            List<ImSimrsKurirEntity> imSimrsKurirEntities = getEntityByCriteria(bean);

            if (!imSimrsKurirEntities.isEmpty()) {
                kurirs = setTemplateKurir(imSimrsKurirEntities);
            }
        }

        logger.info("[KurirBoImpl.getByCriteria] End >>>>>>>");
        return kurirs;
    }

    public List <ImSimrsKurirEntity> getEntityByCriteria(Kurir bean) throws GeneralBOException {
        logger.info("[KurirBoImpl.getByCriteria] Start >>>>>>>");
        List<ImSimrsKurirEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        if (bean.getIdKurir() != null && !"".equalsIgnoreCase(bean.getIdKurir())) {
            hsCriteria.put("id_kurir", bean.getIdKurir());
        }
        if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama())) {
            hsCriteria.put("nama", bean.getNama());
        }
        if (bean.getNoKtp() != null && !"".equalsIgnoreCase(bean.getNoKtp())) {
            hsCriteria.put("no_ktp", bean.getNoKtp());
        }
        if (bean.getNoPolisi() != null && !"".equalsIgnoreCase(bean.getNoPolisi())) {
            hsCriteria.put("no_polisi", bean.getNoPolisi());
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
            hsCriteria.put("branch_id", bean.getBranchId());
        }
        if (bean.getPassword() != null && !"".equalsIgnoreCase(bean.getPassword())) {
            hsCriteria.put("password", bean.getPassword());
        }

        List<ImSimrsKurirEntity> imSimrsKurirEntities = null;
        try{
            imSimrsKurirEntities = kurirDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[KurirBoImpl.getByByCriteria] Error when search kurir by criteria " + e.getMessage());
        }

        if (!imSimrsKurirEntities.isEmpty()) {
            results = imSimrsKurirEntities;
        }

        logger.info("[KurirBoImpl.getByCriteria] End >>>>>>>");
        return results;
    }

    public List<Kurir> setTemplateKurir(List<ImSimrsKurirEntity> listEntity) {
        logger.info("[KurirBoImpl.setTemplateKurir] Start >>>>>>>");
        List<Kurir> list = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Kurir kurir;
        for (ImSimrsKurirEntity data : listEntity) {
            kurir =  new Kurir();
            kurir.setIdKurir(data.getIdKurir());
            kurir.setNama(data.getNama());
            kurir.setNoKtp(data.getNoKtp());
            kurir.setNoTelp(data.getNoTelp());
            kurir.setNoPolisi(data.getNoPolisi());
            kurir.setBranchId(data.getBranchId());
            kurir.setFlagReady(data.getFlagReady());
            kurir.setFlag(data.getFlag());
            kurir.setAction(data.getAction());
            kurir.setCreatedDate(data.getCreatedDate());
            kurir.setLastUpdate(data.getLastUpdate());
            kurir.setCreatedWho(data.getCreatedWho());
            kurir.setLastUpdateWho(data.getLastUpdateWho());
            kurir.setPassword(data.getPassword());

            kurir.setStCreatedDate(formatter.format(data.getCreatedDate()));
            kurir.setStLastUpdate(formatter.format(data.getLastUpdate()));

            list.add(kurir);
        }

        logger.info("[KurirBoImpl.setTemplateKurir] End >>>>>>>");
        return list;
    }

    public String getIdKurir() {
        logger.info("[PasienBoImpl.getIdPasien] Start >>>>>>>");
        String id = "";

        try {
            id = kurirDao.getNextIdPasien();
        } catch (HibernateException e) {
            logger.error("[PasienBoImpl.getIdPasien] Error when get next id pasien");
        }

        logger.info("[PasienBoImpl.getIdPasien] End <<<<<<<");
        return id;
    }

    @Override
    public void saveAdd(Kurir kurir) {
        logger.info("[KurirBoImpl.saveAdd] Start >>>>>>>");

        if (kurir != null) {
            ImSimrsKurirEntity kurirEntity = new ImSimrsKurirEntity();
            String id = getIdKurir();

            kurirEntity.setIdKurir("KUR"+id);
            kurirEntity.setNama(kurir.getNama());
            kurirEntity.setNoKtp(kurir.getNoKtp());
            kurirEntity.setNoPolisi(kurir.getNoPolisi());
            kurirEntity.setNoTelp(kurir.getNoTelp());
            kurirEntity.setFlagReady(kurir.getFlagReady());
            kurirEntity.setCreatedDate(kurir.getCreatedDate());
            kurirEntity.setCreatedWho(kurir.getCreatedWho());
            kurirEntity.setLastUpdate(kurir.getLastUpdate());
            kurirEntity.setLastUpdateWho(kurir.getLastUpdateWho());
            kurirEntity.setBranchId(kurir.getBranchId());
            kurirEntity.setPassword(kurir.getPassword());
            kurirEntity.setFlag("Y");
            kurirEntity.setAction("C");

            try {
                kurirDao.addAndSave(kurirEntity);
            } catch (HibernateException e){
                logger.error("[KurirBoImpl.saveAdd] Error when saving data kurir", e);
                throw new GeneralBOException(" Error when saving data kurir " + e.getMessage());
            }
        } else {
            logger.error("[KurirBoImpl.saveAdd] Error when saving data kurir is null");
            throw new GeneralBOException(" Error when saving data kurir is null");
        }

        logger.info("[KurirBoImpl.saveAdd] End >>>>>>>");
    }

    @Override
    public void saveEdit(Kurir kurir) {
        logger.info("[KurirBoImpl.saveEdit] Start >>>>>>>");



        if (kurir != null && kurir.getIdKurir() != null && !"".equalsIgnoreCase(kurir.getIdKurir())) {
            Kurir newKurir = new Kurir();
            newKurir.setIdKurir(kurir.getIdKurir());
            ImSimrsKurirEntity kurirEntity = getEntityByCriteria(newKurir).get(0);

            kurirEntity.setNama(kurir.getNama());
            kurirEntity.setNoKtp(kurir.getNoKtp());
            kurirEntity.setNoPolisi(kurir.getNoPolisi());
            kurirEntity.setNoTelp(kurir.getNoTelp());
            kurirEntity.setFlagReady(kurir.getFlagReady());
            kurirEntity.setCreatedDate(kurir.getCreatedDate());
            kurirEntity.setCreatedWho(kurir.getCreatedWho());
            kurirEntity.setLastUpdate(kurir.getLastUpdate());
            kurirEntity.setLastUpdateWho(kurir.getLastUpdateWho());
            kurirEntity.setLat(kurir.getLat());
            kurirEntity.setLon(kurir.getLon());
            kurirEntity.setFlag("Y");
            kurirEntity.setAction("U");

            try {
                kurirDao.updateAndSave(kurirEntity);
            } catch (HibernateException e){
                logger.error("[KurirBoImpl.saveEdit] Error when saving data kurir", e);
                throw new GeneralBOException(" Error when saving data kurir " + e.getMessage());
            }
        } else {
            logger.error("[KurirBoImpl.saveEdit] Error when saving data kurir is null");
            throw new GeneralBOException(" Error when saving data kurir is null");
        }

        logger.info("[KurirBoImpl.saveEdit] End >>>>>>>");
    }

    @Override
    public Boolean isUserKurirById(String userId, String password) throws GeneralBOException {
        logger.info("[KurirnBoImpl.isUserKurirById] Start >>>>>>>");

        Boolean isFound = false;
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_kurir", userId);
        hsCriteria.put("password", password);

        List<ImSimrsKurirEntity> kurirEntities = null;
        try {
            kurirEntities = kurirDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PasienBoImpl.isUserPasienById] Error when search pasien by criteria " + e.getMessage());
            throw new GeneralBOException("[PasienBoImpl.isUserPasienById] Error when search pasien by criteria " + e.getMessage());
        }

        if (!kurirEntities.isEmpty() && kurirEntities.size() > 0) {
            isFound = true;
        }

        logger.info("[KurirnBoImpl.isUserKurirById] End >>>>>>>");
        return isFound;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
