package com.neurix.simrs.transaksi.periksaradiologi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dokter.dao.DokterDao;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterEntity;
import com.neurix.simrs.master.statuspasien.dao.StatusPasienDao;
import com.neurix.simrs.master.statuspasien.model.ImSimrsStatusPasienEntity;
import com.neurix.simrs.transaksi.periksaradiologi.bo.PeriksaRadiologiBo;
import com.neurix.simrs.transaksi.periksaradiologi.dao.PeriksaRadiologiDao;
import com.neurix.simrs.transaksi.periksaradiologi.model.ItSimrsPeriksaRadiologiEntity;
import com.neurix.simrs.transaksi.periksaradiologi.model.PeriksaRadiologi;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 29/11/2019.
 */
public class PeriksaRadiologiBoImpl implements PeriksaRadiologiBo{
    private static transient Logger logger = Logger.getLogger(PeriksaRadiologiBoImpl.class);
    private PeriksaRadiologiDao periksaRadiologiDao;
    private DokterDao dokterDao;
    private StatusPasienDao statusPasienDao;

    @Override
    public List<PeriksaRadiologi> getListPeriksaRadioLogiByCriteria(PeriksaRadiologi bean) throws GeneralBOException {
        logger.info("[PeriksaRadiologiBoImpl.getListPeriksaRadioLogiByCriteria] START >>>>>>");

        List<PeriksaRadiologi> periksaRadiologiList = new ArrayList<>();
        List<ItSimrsPeriksaRadiologiEntity> periksaRadiologiEntities = getListEntityRadiologi(bean);
        if (!periksaRadiologiEntities.isEmpty() && periksaRadiologiEntities.size() > 0)
        {
            PeriksaRadiologi periksaRadiologi;
            for (ItSimrsPeriksaRadiologiEntity radiologiEntity : periksaRadiologiEntities)
            {
                periksaRadiologi = new PeriksaRadiologi();
                periksaRadiologi.setIdPeriksaRadiologi(radiologiEntity.getIdPeriksaRadiologi());
                periksaRadiologi.setIdDetailCheckup(radiologiEntity.getIdDetailCheckup());
                periksaRadiologi.setIdLab(radiologiEntity.getIdLab());
                periksaRadiologi.setIdPeriksaLab(radiologiEntity.getIdPeriksaLab());
                periksaRadiologi.setPemeriksaan(radiologiEntity.getPemeriksaan());
                periksaRadiologi.setKesimpulan(radiologiEntity.getKesimpulan());
                periksaRadiologi.setFlag(radiologiEntity.getFlag());
                periksaRadiologi.setAction(radiologiEntity.getAction());
                periksaRadiologi.setLastUpdate(radiologiEntity.getLastUpdate());
                periksaRadiologi.setLastUpdateWho(radiologiEntity.getLastUpdateWho());
                periksaRadiologi.setCreatedDate(radiologiEntity.getCreatedDate());
                periksaRadiologi.setCreatedWho(radiologiEntity.getCreatedWho());

                if (radiologiEntity.getIdDokterRadiologi() != null && !"".equalsIgnoreCase(radiologiEntity.getIdDokterRadiologi()))
                {
                    ImSimrsDokterEntity dokterEntity = getMasterDokterById(radiologiEntity.getIdDokterRadiologi());
                    if (dokterEntity != null)
                    {
                        periksaRadiologi.setIdDokterRadiologi(radiologiEntity.getIdDokterRadiologi());
                        periksaRadiologi.setNamaDokter(dokterEntity.getNamaDokter());
                    }
                }

                if (radiologiEntity.getStatusPeriksa() != null && !"".equalsIgnoreCase(radiologiEntity.getStatusPeriksa()))
                {
                    ImSimrsStatusPasienEntity statusPasienEntity = getMasterStatusPasienById(radiologiEntity.getStatusPeriksa());
                    if (statusPasienEntity != null)
                    {
                        periksaRadiologi.setStatusPeriksa(radiologiEntity.getStatusPeriksa());
                        periksaRadiologi.setStatusPeriksaName(statusPasienEntity.getKeterangan());

                    }
                }
                periksaRadiologiList.add(periksaRadiologi);
            }
        }

        logger.info("[PeriksaRadiologiBoImpl.getListPeriksaRadioLogiByCriteria] END <<<<<<");
        return periksaRadiologiList;
    }

    private List<ItSimrsPeriksaRadiologiEntity> getListEntityRadiologi(PeriksaRadiologi bean) throws GeneralBOException{
        logger.info("[PeriksaRadiologiBoImpl.getListEntityRadiologi] START >>>>>>");

        Map hsCriteria = new HashMap();
        if (bean.getIdPeriksaRadiologi() != null && !"".equalsIgnoreCase(bean.getIdPeriksaRadiologi()))
        {
            hsCriteria.put("id_periksa_radiologi", bean.getIdPeriksaRadiologi());
        }
        if (bean.getIdDokterRadiologi() != null && !"".equalsIgnoreCase(bean.getIdDokterRadiologi()))
        {
            hsCriteria.put("id_dokter_radiologi", bean.getIdDokterRadiologi());
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup()))
        {
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }
        if (bean.getIdLab() != null && !"".equalsIgnoreCase(bean.getIdLab()))
        {
            hsCriteria.put("id_lab", bean.getIdLab());
        }
        if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa()))
        {
            hsCriteria.put("status", bean.getStatusPeriksa());
        }
        if (bean.getIdPeriksaLab() != null && !"".equalsIgnoreCase(bean.getIdPeriksaLab()))
        {
            hsCriteria.put("id_periksa_lab", bean.getIdPeriksaLab());
        }


        hsCriteria.put("flag", "Y");

        List<ItSimrsPeriksaRadiologiEntity> periksaRadiologiEntities = new ArrayList<>();
        try {
            periksaRadiologiEntities = periksaRadiologiDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[PeriksaRadiologiBoImpl.getListEntityRadiologi] ERROR when get data entity Radiologi, ", e.getCause());
            throw new GeneralBOException("[PeriksaRadiologiBoImpl.getListEntityRadiologi] ERROR when get data entity Radiologi, ", e.getCause());
        }

        logger.info("[PeriksaRadiologiBoImpl.getListEntityRadiologi] END <<<<<<");
        return periksaRadiologiEntities;
    }

    @Override
    public void saveAdd(PeriksaRadiologi bean) throws GeneralBOException {
        logger.info("[PeriksaRadiologiBoImpl.saveAdd] START >>>>>>");

        if (bean != null)
        {
            ItSimrsPeriksaRadiologiEntity entity = new ItSimrsPeriksaRadiologiEntity();
            String id = getNextPeriksaRadiologId();

            entity.setIdPeriksaRadiologi("RLG"+id);
            entity.setIdDetailCheckup(bean.getIdDetailCheckup());
            entity.setIdLab(bean.getIdLab());
            entity.setIdDokterRadiologi(bean.getIdDokterRadiologi());
            entity.setPemeriksaan(bean.getPemeriksaan());
            entity.setKesimpulan(bean.getKesimpulan());
            entity.setFlag("Y");
            entity.setAction("C");
            entity.setCreatedDate(bean.getCreatedDate());
            entity.setCreatedWho(bean.getCreatedWho());
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                periksaRadiologiDao.addAndSave(entity);
            } catch (HibernateException e){
                logger.error("[PeriksaRadiologiBoImpl.saveAdd] ERROR when insert Radiologi, ", e.getCause());
                throw new GeneralBOException("[PeriksaRadiologiBoImpl.saveAdd] ERROR when insert Radiologi, ", e.getCause());
            }

        } else {
            logger.error("[PeriksaRadiologiBoImpl.saveAdd] ERROR when insert Radiologi, data is null");
            throw new GeneralBOException("[PeriksaRadiologiBoImpl.saveAdd] ERROR when insert Radiologi, data is null");
        }

        logger.info("[PeriksaRadiologiBoImpl.saveAdd] END <<<<<<");
    }

    @Override
    public void saveEdit(PeriksaRadiologi bean) throws GeneralBOException {
        logger.info("[PeriksaRadiologiBoImpl.saveEdit] START >>>>>>");

        if (bean != null && bean.getIdPeriksaRadiologi() != null && !"".equalsIgnoreCase(bean.getIdPeriksaRadiologi()))
        {
            ItSimrsPeriksaRadiologiEntity entity = getListEntityRadiologi(bean).get(0);

            entity.setKesimpulan(bean.getKesimpulan());
            entity.setFlag(bean.getFlag());
            entity.setAction(bean.getAction());
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                periksaRadiologiDao.addAndSave(entity);
            } catch (HibernateException e){
                logger.error("[PeriksaRadiologiBoImpl.saveEdit] ERROR when updating Radiologi, ", e.getCause());
                throw new GeneralBOException("[PeriksaRadiologiBoImpl.getListEntityRadiologi] ERROR when updating Radiologi, ", e.getCause());
            }

        } else {
            logger.error("[PeriksaRadiologiBoImpl.saveEdit] ERROR when insert Radiologi, data is null");
            throw new GeneralBOException("[PeriksaRadiologiBoImpl.saveEdit] ERROR when insert Radiologi, data is null");
        }

        logger.info("[PeriksaRadiologiBoImpl.saveEdit] END <<<<<<");
    }

    private ImSimrsDokterEntity getMasterDokterById(String idDokter) throws GeneralBOException{
        logger.info("[PeriksaRadiologiBoImpl.getMasterDokterById] START >>>>>>");

        Map hsCritetria = new HashMap();
        hsCritetria.put("id_dokter", idDokter);

        List<ImSimrsDokterEntity> dokterEntities = new ArrayList<>();
        try {
            dokterEntities = dokterDao.getByCriteria(hsCritetria);
        } catch (HibernateException e){
            logger.error("[PeriksaRadiologiBoImpl.getMasterDokterById] ERROR when get data entity master dokter, ", e.getCause());
        }

        logger.info("[PeriksaRadiologiBoImpl.getMasterDokterById] END <<<<<<");
        if (!dokterEntities.isEmpty() && dokterEntities.size() > 0)
        {
            return dokterEntities.get(0);
        }

        return new ImSimrsDokterEntity();
    }

    private ImSimrsStatusPasienEntity getMasterStatusPasienById(String idStatus) throws GeneralBOException{
        logger.info("[PeriksaRadiologiBoImpl.getMasterStatusPasienById] START >>>>>>");

        Map hsCritetria = new HashMap();
        hsCritetria.put("id_status_pasien", idStatus);

        List<ImSimrsStatusPasienEntity> statusPasienEntities = new ArrayList<>();
        try {
            statusPasienEntities = statusPasienDao.getByCriteria(hsCritetria);
        } catch (HibernateException e){
            logger.error("[PeriksaRadiologiBoImpl.getMasterStatusPasienById] ERROR when get data entity master dokter, ", e.getCause());
        }

        logger.info("[PeriksaRadiologiBoImpl.getMasterStatusPasienById] END <<<<<<");
        if (!statusPasienEntities.isEmpty() && statusPasienEntities.size() > 0)
        {
            return statusPasienEntities.get(0);
        }
        return new ImSimrsStatusPasienEntity();
    }

    private String getNextPeriksaRadiologId() throws GeneralBOException{
        String id = "";
        try {
            id = periksaRadiologiDao.getNextId();
        } catch (HibernateException e){
            logger.error("[PeriksaRadiologiBoImpl.getNextPeriksaRadiologId] ERROR when generate new id, ", e.getCause());
            throw new GeneralBOException("[PeriksaRadiologiBoImpl.getNextPeriksaRadiologId] ERROR when generate new id, ", e.getCause());
        }
        return id;
    }

    public void setPeriksaRadiologiDao(PeriksaRadiologiDao periksaRadiologiDao) {
        this.periksaRadiologiDao = periksaRadiologiDao;
    }

    public void setDokterDao(DokterDao dokterDao) {
        this.dokterDao = dokterDao;
    }

    public void setStatusPasienDao(StatusPasienDao statusPasienDao) {
        this.statusPasienDao = statusPasienDao;
    }
}
