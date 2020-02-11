package com.neurix.simrs.master.ruangan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.dao.RuanganDao;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuanganBoImpl implements RuanganBo {
    protected static transient Logger logger = Logger.getLogger(RuanganBoImpl.class);
    private RuanganDao ruanganDao;

    public static void setLogger(Logger logger) {
        RuanganBoImpl.logger = logger;
    }

    public void setRuanganDao(RuanganDao ruanganDao) {
        this.ruanganDao = ruanganDao;
    }

    @Override
    public List<Ruangan> getByCriteria(Ruangan bean) throws GeneralBOException {
        logger.info("[RuanganBoImpl.getByCriteria] Start >>>>>>");
        List<Ruangan> result = new ArrayList<>();

        Map hsCriteria = new HashMap();

        if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())){
            hsCriteria.put("id_ruangan", bean.getIdRuangan());
        }
        if (bean.getIdKelasRuangan() != null && !"".equalsIgnoreCase(bean.getIdKelasRuangan())){
            hsCriteria.put("id_kelas_ruangan", bean.getIdKelasRuangan());
        }
        if (bean.getStatusRuangan() != null && !"".equalsIgnoreCase(bean.getStatusRuangan())){
            hsCriteria.put("status_ruangan", bean.getStatusRuangan());
        }

        hsCriteria.put("flag","Y");

        List<MtSimrsRuanganEntity> mtSimrsRuanganEntityList = null;
        try {
            mtSimrsRuanganEntityList = ruanganDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[RuanganBoImpl.getByCriteria] Error get ruangan data "+e.getMessage());
        }

        if (!mtSimrsRuanganEntityList.isEmpty()){
            Ruangan ruangan;
            for (MtSimrsRuanganEntity listEntity : mtSimrsRuanganEntityList){
                ruangan = new Ruangan();
                ruangan.setIdKelasRuangan(listEntity.getIdKelasRuangan());
                ruangan.setIdRuangan(listEntity.getIdRuangan());
                ruangan.setNoRuangan(listEntity.getNoRuangan());
                ruangan.setNamaRuangan(listEntity.getNamaRuangan());
                ruangan.setTarif(listEntity.getTarif());
                ruangan.setStatusRuangan(listEntity.getStatusRuangan());
                ruangan.setNamaKelasRuangan(listEntity.getImSimrsKelasRuanganEntity().getNamaKelasRuangan());
                ruangan.setKeterangan(listEntity.getKeterangan());
                ruangan.setFlag(listEntity.getFlag());
                ruangan.setAction(listEntity.getAction());

                result.add(ruangan);
            }
        }
        logger.info("[RuanganBoImpl.getByCriteria] End <<<<<<");

        return result;
    }

    @Override
    public void saveAdd(Ruangan ruangan) throws GeneralBOException {
        logger.info("[RuanganBoImpl.saveAdd] Start >>>>>>>");

        if (ruangan != null){
            MtSimrsRuanganEntity mtSimrsRuanganEntity = new MtSimrsRuanganEntity();
            String id = getIdRuangan();

            mtSimrsRuanganEntity.setIdRuangan("R"+id);
            mtSimrsRuanganEntity.setNamaRuangan(ruangan.getNamaRuangan());
            mtSimrsRuanganEntity.setNoRuangan(ruangan.getNoRuangan());
            mtSimrsRuanganEntity.setStatusRuangan(ruangan.getStatusRuangan());
            mtSimrsRuanganEntity.setIdKelasRuangan(ruangan.getIdKelasRuangan());
            mtSimrsRuanganEntity.setKeterangan(ruangan.getKeterangan());
            mtSimrsRuanganEntity.setTarif(ruangan.getTarif());
            mtSimrsRuanganEntity.setBranchId(ruangan.getBranchId());

            mtSimrsRuanganEntity.setFlag("Y");
            mtSimrsRuanganEntity.setAction("C");
            mtSimrsRuanganEntity.setCreatedDate(ruangan.getCreatedDate());
            mtSimrsRuanganEntity.setLastUpdate(ruangan.getLastUpdate());
            mtSimrsRuanganEntity.setCreatedWho(ruangan.getCreatedWho());
            mtSimrsRuanganEntity.setLastUpdateWho(ruangan.getLastUpdateWho());

            try {
                ruanganDao.addAndSave(mtSimrsRuanganEntity);
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

    @Override
    public void saveEdit(Ruangan ruangan) throws GeneralBOException {
        logger.info("[PasienBoImpl.saveEdit] Start >>>>>>>");

        if (ruangan != null && ruangan.getIdRuangan() != null && !"".equalsIgnoreCase(ruangan.getIdRuangan())){

            Ruangan editRuangan = new Ruangan();
            editRuangan.setIdRuangan(ruangan.getIdRuangan());
            MtSimrsRuanganEntity mtSimrsRuanganEntity = getEntityByCriteria(editRuangan).get(0);

            if (mtSimrsRuanganEntity != null){

                mtSimrsRuanganEntity.setNamaRuangan(ruangan.getNamaRuangan());
                mtSimrsRuanganEntity.setNoRuangan(ruangan.getNoRuangan());
                mtSimrsRuanganEntity.setStatusRuangan(ruangan.getStatusRuangan());
                mtSimrsRuanganEntity.setIdKelasRuangan(ruangan.getIdKelasRuangan());
                mtSimrsRuanganEntity.setKeterangan(ruangan.getKeterangan());
                mtSimrsRuanganEntity.setTarif(ruangan.getTarif());
                mtSimrsRuanganEntity.setBranchId(ruangan.getBranchId());
                mtSimrsRuanganEntity.setFlag(ruangan.getFlag());
                mtSimrsRuanganEntity.setAction("U");
                mtSimrsRuanganEntity.setLastUpdate(ruangan.getLastUpdate());
                mtSimrsRuanganEntity.setLastUpdateWho(ruangan.getLastUpdateWho());

                try {
                    ruanganDao.updateAndSave(mtSimrsRuanganEntity);
                } catch (HibernateException e){
                    logger.error("[RuanganBoImpl.saveAdd] Error when Updating data ruangan", e);
                    throw new GeneralBOException(" Error when Updating data ruangan "+e.getMessage());
                }
            } else {
                logger.error("[RuanganBoImpl.saveAdd] Error when get entity ruangan is null");
                throw new GeneralBOException("  Error when get entity ruangan is null");
            }

        } else {
            logger.error("[RuanganBoImpl.saveAdd] Error when saving data ruangan data is null");
            throw new GeneralBOException(" Error when saving data ruangan data is null");
        }

        logger.info("[RuanganBoImpl.saveEdit] End <<<<<<<");
    }

    @Override
    public void saveDelete(Ruangan ruangan) throws GeneralBOException {
        logger.info("[RuanganBoImpl.saveDelete] start process");

        if (ruangan!=null) {

            String idRuangan = ruangan.getIdRuangan();

            MtSimrsRuanganEntity entity = null;
            try {
                // Get data from database by ID
                entity = ruanganDao.getById("idRuangan", idRuangan);
            } catch (HibernateException e) {
                logger.error("[RuanganBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data ruangan by Ruangan id, please inform to your admin...," + e.getMessage());
            }

            if (entity != null) {
                // Modify from bean to entity serializable
                entity.setNamaRuangan(ruangan.getNamaRuangan());
                entity.setNoRuangan(ruangan.getNoRuangan());
                entity.setIdKelasRuangan(ruangan.getIdKelasRuangan());
                entity.setKeterangan(ruangan.getKeterangan());
                entity.setTarif(ruangan.getTarif());
                entity.setBranchId(ruangan.getBranchId());

                entity.setFlag(ruangan.getFlag());
                entity.setAction("U");
                entity.setLastUpdate(ruangan.getLastUpdate());
                entity.setLastUpdateWho(ruangan.getLastUpdateWho());

                try {
                    // Delete (Edit) into database
                    ruanganDao.updateAndSave(entity);
                } catch (HibernateException e) {
                    logger.error("[RuanganBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Ruangan, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[RuanganBoImpl.saveDelete] Error, not found data Ruangan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Ruangan with request id, please check again your data ...");
            }
        }

        logger.info("[RuanganBoImpl.saveEdit] End <<<<<<<");
    }

    public String getIdRuangan(){
        logger.info("[RuanganBoImpl.getIdRuangan] Start >>>>>>>");
        String id = "";

        try {
            id = ruanganDao.getNextIdRuangan();
        } catch (HibernateException e){
            logger.error("[PasienBoImpl.getIdPasien] Error when get next id pasien");
        }

        logger.info("[PasienBoImpl.getIdPasien] End <<<<<<<");
        return id;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<MtSimrsRuanganEntity> getEntityByCriteria(Ruangan bean) throws GeneralBOException{
        logger.info("[RuanganBoImpl.getEntityByCriteria] Start >>>>>>>");
        List<MtSimrsRuanganEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())){
            hsCriteria.put("id_ruangan", bean.getIdRuangan());
        }
        if (bean.getNamaRuangan() != null && !"".equalsIgnoreCase(bean.getNamaRuangan())){
            hsCriteria.put("nama_ruangan", bean.getNamaRuangan());
        }
        if (bean.getNoRuangan() != null && !"".equalsIgnoreCase(bean.getNoRuangan())){
            hsCriteria.put("no_ruangan", bean.getNoRuangan());
        }
        if (bean.getStatusRuangan() != null && !"".equalsIgnoreCase(bean.getStatusRuangan())){
            hsCriteria.put("status_ruangan", bean.getStatusRuangan());
        }
        if (bean.getIdKelasRuangan() != null && !"".equalsIgnoreCase(bean.getIdKelasRuangan())){
            hsCriteria.put("id_kelas_ruangan", bean.getIdKelasRuangan());
        }
        if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())){
            hsCriteria.put("keterangan", bean.getKeterangan());
        }
        if (bean.getTarif() != null && !"".equalsIgnoreCase(String.valueOf(bean.getTarif()))){
            hsCriteria.put("tarif", bean.getTarif());
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
            hsCriteria.put("branch_id", bean.getBranchId());
        }

        List<MtSimrsRuanganEntity> mtSimrsRuanganEntities = null;
        try {
            mtSimrsRuanganEntities = ruanganDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[RuanganBoImpl.getByByCriteria] Error when search pasien by criteria "+e.getMessage());
        }

        if (!mtSimrsRuanganEntities.isEmpty()){
            results = mtSimrsRuanganEntities;
        }

        logger.info("[RuanganBoImpl.getEntityByCriteria] End <<<<<<<");
        return results;
    }
}