package com.neurix.simrs.master.pasien.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.dao.PasienDao;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pasien.model.Pasien;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Toshiba on 13/11/2019.
 */
public class PasienBoImpl implements PasienBo {

    protected static transient org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PasienBoImpl.class);

    private PasienDao pasienDao;

    @Override
    public List<Pasien> getByByCriteria(Pasien bean) throws GeneralBOException {
        logger.info("[PasienBoImpl.getByByCriteria] Start >>>>>>>");

        List<Pasien> pasiens = new ArrayList<>();
        if (bean != null){

            Map hsCriteria = new HashMap();
            hsCriteria.put("flag", "Y");

            List<ImSimrsPasienEntity> imSimrsPasienEntities = null;
            try {
                imSimrsPasienEntities = pasienDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PasienBoImpl.getByByCriteria] Error when search pasien by criteria "+e.getMessage());
            }

            if (!imSimrsPasienEntities.isEmpty()){
                pasiens = setTemplatePasien(imSimrsPasienEntities);
            }

        }

        logger.info("[PasienBoImpl.getByByCriteria] End <<<<<<<");
        return pasiens;
    }

    public List<Pasien> setTemplatePasien(List<ImSimrsPasienEntity> listEntity){
        logger.info("[PasienBoImpl.setTemplatePasien] Start >>>>>>>");
        List<Pasien> list = new ArrayList<>();

        Pasien pasien;
        for (ImSimrsPasienEntity data : listEntity){
            pasien = new Pasien();
            pasien.setIdPasien(data.getIdPasien());
            pasien.setNama(data.getNama());
            pasien.setJenisKelamin(data.getJenisKelamin());
            pasien.setNoKtp(data.getNoKtp());
            pasien.setNoBpjs(data.getNoBpjs());
            pasien.setTempatLahir(data.getTempatLahir());
            pasien.setTglLahir(data.getTglLahir());
            pasien.setDesaId(data.getDesaId());
            pasien.setJalan(data.getJalan());
            pasien.setSuku(data.getSuku());
            pasien.setAgama(data.getAgama());
            pasien.setProfesi(data.getProfesi());
            pasien.setNoTelp(data.getNoTelp());
            pasien.setUrlKtp(data.getUrlKtp());
            pasien.setFlag(data.getFlag());
            pasien.setAction(data.getAction());
            pasien.setCreatedDate(data.getCreatedDate());
            pasien.setCreatedWho(data.getCreatedWho());
            pasien.setLastUpdate(data.getLastUpdate());
            pasien.setLastUpdateWho(data.getLastUpdateWho());
            list.add(pasien);
        }

        logger.info("[PasienBoImpl.setTemplatePasien] End <<<<<<<");
        return list;
    }

    @Override
    public void saveAdd(Pasien pasien) throws GeneralBOException {
        logger.info("[PasienBoImpl.saveAdd] Start >>>>>>>");

        if (pasien != null){
            ImSimrsPasienEntity pasienEntity = new ImSimrsPasienEntity();
            String id = getIdPasien();

            pasienEntity.setIdPasien(id);
            pasienEntity.setNama(pasien.getNama());
            pasienEntity.setJenisKelamin(pasien.getJenisKelamin());
            pasienEntity.setNoKtp(pasien.getNoKtp());
            pasienEntity.setNoBpjs(pasien.getNoBpjs());
            pasienEntity.setTempatLahir(pasien.getTempatLahir());
            pasienEntity.setTglLahir(pasien.getTglLahir());
            pasienEntity.setDesaId(pasien.getDesaId());
            pasienEntity.setJalan(pasien.getJalan());
            pasienEntity.setSuku(pasien.getSuku());
            pasienEntity.setAgama(pasien.getAgama());
            pasienEntity.setProfesi(pasien.getProfesi());
            pasienEntity.setNoTelp(pasien.getNoTelp());
            pasienEntity.setUrlKtp(pasien.getNoKtp());
            pasienEntity.setFlag("Y");
            pasienEntity.setAction("C");
            pasienEntity.setCreatedDate(pasien.getCreatedDate());
            pasienEntity.setLastUpdate(pasien.getLastUpdate());
            pasienEntity.setCreatedWho(pasien.getCreatedWho());
            pasienEntity.setLastUpdateWho(pasien.getLastUpdateWho());


            try {
                pasienDao.addAndSave(pasienEntity);
            } catch (HibernateException e){
                logger.error("[PasienBoImpl.saveAdd] Error when saving data pasien", e);
                throw new GeneralBOException(" Error when saving data pasien "+e.getMessage());
            }
        } else {
            logger.error("[PasienBoImpl.saveAdd] Error when saving data pasien data is null");
            throw new GeneralBOException(" Error when saving data pasien data is null");
        }

        logger.info("[PasienBoImpl.saveAdd] End <<<<<<<");
    }

    public String getIdPasien(){
        logger.info("[PasienBoImpl.getIdPasien] Start >>>>>>>");
        String id = "";

        try {
            id = pasienDao.getNextIdPasien();
        } catch (HibernateException e){
            logger.error("[PasienBoImpl.getIdPasien] Error when get next id pasien");
        }

        logger.info("[PasienBoImpl.getIdPasien] End <<<<<<<");
        return id;
    }

    public void setPasienDao(PasienDao pasienDao) {
        this.pasienDao = pasienDao;
    }

}
