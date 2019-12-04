package com.neurix.simrs.transaksi.obatinap.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisobat.dao.JenisObatDao;
import com.neurix.simrs.master.jenisobat.model.ImSimrsJenisObatEntity;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import com.neurix.simrs.master.obat.dao.ObatDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.obatinap.bo.ObatInapBo;
import com.neurix.simrs.transaksi.obatinap.dao.ObatInapDao;
import com.neurix.simrs.transaksi.obatinap.model.ItSimrsObatInapEntity;
import com.neurix.simrs.transaksi.obatinap.model.ObatInap;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObatInapBoImpl implements ObatInapBo {
    private static transient Logger logger = Logger.getLogger(ObatInapBoImpl.class);
    private ObatInapDao obatInapDao;
    private JenisObatDao jenisObatDao;
    private ObatDao obatDao;

    public void setObatDao(ObatDao obatDao) {
        this.obatDao = obatDao;
    }

    public void setJenisObatDao(JenisObatDao jenisObatDao) {
        this.jenisObatDao = jenisObatDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setObatInapDao(ObatInapDao obatInapDao) {
        this.obatInapDao = obatInapDao;
    }

    @Override
    public List<ObatInap> getByCriteria(ObatInap bean) throws GeneralBOException {
        logger.info("[ObatInapBoImpl.getByCriteria] Start >>>>>>>");

        List<ObatInap> results = new ArrayList<>();

        if (bean != null){
            List<ItSimrsObatInapEntity> obatInapEntities = getListEntity(bean);
            if (!obatInapEntities.isEmpty()){
                results = setToTemplate(obatInapEntities);
            }
        }

        logger.info("[ObatInapBoImpl.getByCriteria] End <<<<<<");
        return results;
    }

    @Override
    public void saveAdd(ObatInap bean) throws GeneralBOException {
        logger.info("[ObatInapBoImpl.saveAdd] Start >>>>>>>");

        if (bean != null){
            String id = getNextId();
            if (id != null && !"".equalsIgnoreCase(id)) {
                ItSimrsObatInapEntity obatInapEntity = new ItSimrsObatInapEntity();
                obatInapEntity.setIdObatInap("OBI" + id);
                obatInapEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
                obatInapEntity.setIdObat(bean.getIdObat());
                obatInapEntity.setNamaObat(bean.getNamaObat());
                obatInapEntity.setHarga(bean.getHarga());
                obatInapEntity.setQty(bean.getQty());
                obatInapEntity.setTotalHarga(bean.getTotalHarga());
                obatInapEntity.setFlag("Y");
                obatInapEntity.setAction("C");
                obatInapEntity.setCreatedDate(bean.getCreatedDate());
                obatInapEntity.setCreatedWho(bean.getCreatedWho());
                obatInapEntity.setLastUpdate(bean.getLastUpdate());
                obatInapEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    obatInapDao.addAndSave(obatInapEntity);
                } catch (HibernateException e) {
                    logger.error("[ObatInapBoImpl.saveAdd] Error when insert obat inap ", e);
                    throw new GeneralBOException("[ObatInapBoImpl.saveAdd] Error when insert obat inap " + e.getMessage());
                }
            }
        }
        bean.setCekQty("new");
        updateStokObat(bean);
        logger.info("[ObatInapBoImpl.saveAdd] End <<<<<<");
    }

    @Override
    public void saveEdit(ObatInap bean) throws GeneralBOException {
        logger.info("[ObatInapBoImpl.saveEdit] Start >>>>>>>");

        if (bean != null){

            ItSimrsObatInapEntity entity = null;

            try {
                entity = obatInapDao.getById("idObatInap", bean.getIdObatInap());
            } catch (HibernateException e){
                logger.error("[TeamDokterBoImpl.saveEdit] Error when getById rawat inap ",e);
                throw new GeneralBOException("[TeamDokterBoImpl.savaAdd] Error when save edit diagnosa rawat "+e.getMessage());
            }
            if(entity != null){

                int font = bean.getQty().intValue();
                int end  = entity.getQty().intValue();

                if ( font > end){
                    bean.setCekQty("mines");
                    BigInteger qty = bean.getQty().subtract(entity.getQty());
                    bean.setQtyEdit(qty);
                }

                if(end > font){
                    bean.setCekQty("plus");
                    BigInteger qty = entity.getQty().subtract(bean.getQty());
                    bean.setQtyEdit(qty);
                }

                entity.setIdDetailCheckup(bean.getIdDetailCheckup());
                entity.setIdObat(bean.getIdObat());
                entity.setNamaObat(bean.getNamaObat());
                entity.setHarga(bean.getHarga());
                entity.setQty(bean.getQty());
                entity.setTotalHarga(bean.getTotalHarga());
                entity.setAction(bean.getAction());
                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());

                logger.info("[ObatInapBoImpl.saveEdit] FRONT --> "+font);
                logger.info("[ObatInapBoImpl.saveEdit] END   --> "+end);
            }

            try {
                obatInapDao.updateAndSave(entity);
            } catch (HibernateException e){
                logger.error("[DiagnosaRawatBoImpl.saveEdit] Error when edit obat inap ", e);
                throw new GeneralBOException("Error when edit diagnosa " + e.getMessage());
            }
        }

        updateStokObat(bean);
        logger.info("[ObatInapBoImpl.saveEdit] End <<<<<<");
    }

    public String getNextId(){
        logger.info("[ObatInapBoImpl.getNextId] Start >>>>>>>");

        String id = "";
        try {
            id = obatInapDao.getNextId();
        } catch (HibernateException e){
            logger.error("[ObatInapBoImpl.getNextId] Error when get next id obat inap");
        }

        logger.info("[ObatInapBoImpl.getNextId] End <<<<<<");
        return id;
    }

    public List<ItSimrsObatInapEntity> getListEntity(ObatInap bean) throws GeneralBOException{
        logger.info("[ObatInapBoImpl.getListEntity] Start >>>>>>>");
        List<ItSimrsObatInapEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdObatInap() != null && !"".equalsIgnoreCase(bean.getIdObatInap())){
            hsCriteria.put("id_obat_inap", bean.getIdObatInap());
        }
        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())){
            hsCriteria.put("id_obat", bean.getIdObat());
        }

        hsCriteria.put("flag","Y");
        try {
            results = obatInapDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[ObatInapBoImpl.getListEntity] Erro when searching data obat inap ", e);
        }

        logger.info("[ObatInapBoImpl.getListEntityT] End <<<<<<");
        return results;
    }

    protected List<ObatInap> setToTemplate(List<ItSimrsObatInapEntity> entities) throws GeneralBOException{
        logger.info("[ObatInapBoImpl.setToTemplate] Start >>>>>>>");
        List<ObatInap> results = new ArrayList<>();

        ObatInap obatInap;
        for (ItSimrsObatInapEntity entity : entities){

            obatInap = new ObatInap();
            obatInap.setIdObatInap(entity.getIdObatInap());
            obatInap.setIdDetailCheckup(entity.getIdDetailCheckup());
            obatInap.setIdObat(entity.getIdObat());
            obatInap.setNamaObat(entity.getNamaObat());
            obatInap.setHarga(entity.getHarga());
            obatInap.setQty(entity.getQty());
            obatInap.setTotalHarga(entity.getTotalHarga());
            obatInap.setFlag(entity.getFlag());
            obatInap.setAction(entity.getAction());
            obatInap.setCreatedDate(entity.getCreatedDate());
            obatInap.setCreatedWho(entity.getCreatedWho());
            obatInap.setLastUpdate(entity.getLastUpdate());
            obatInap.setLastUpdateWho(entity.getLastUpdateWho());

            Obat obat = new Obat();
            obat.setIdObat(entity.getIdObat());
            List<ImSimrsObatEntity> obatEntityList = getListObatEntity(obat);

            ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();
            if (!obatEntityList.isEmpty()){
                obatEntity = obatEntityList.get(0);
            }

            if (obatEntity != null){

                obatInap.setStokMasterObat(obat.getQty());

                JenisObat jenisObat = new JenisObat();
                jenisObat.setIdJenisObat(obatEntity.getIdJenisObat());
                List<ImSimrsJenisObatEntity> jenisObatEntityList = getListJenisObatEntity(jenisObat);

                ImSimrsJenisObatEntity jenisObatEntity = new ImSimrsJenisObatEntity();
                if (!jenisObatEntityList.isEmpty()){
                    jenisObatEntity = jenisObatEntityList.get(0);
                }

                if (jenisObatEntity != null){
                    obatInap.setIdJenisObat(jenisObatEntity.getIdJenisObat());
                    obatInap.setNamaJenisObat(jenisObatEntity.getNamaJenisObat());
                }

            }

            results.add(obatInap);
        }
        logger.info("[ObatInapBoImpl.setToTemplate] End <<<<<<");
        return results;
    }

    private List<ImSimrsObatEntity> getListObatEntity(Obat bean) throws GeneralBOException{
        logger.info("[ObatInapBoImpl.getListObatEntity] Start >>>>>>>");
        List<ImSimrsObatEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdObat() != null && !"".equalsIgnoreCase(bean.getIdObat())){
            hsCriteria.put("id_obat", bean.getIdObat());
        }

        hsCriteria.put("flag","Y");
        try {
            results = obatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[ObatInapBoImpl.getListObatEntity] Erro when searching data obat ", e);
        }

        logger.info("[ObatInapBoImpl.getListObatEntity] End <<<<<<");
        return results;
    }

    private List<ImSimrsJenisObatEntity> getListJenisObatEntity(JenisObat bean) throws GeneralBOException{
        logger.info("[ObatInapBoImpl.getListJenisObatEntity] Start >>>>>>>");
        List<ImSimrsJenisObatEntity> results = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getIdJenisObat() != null && !"".equalsIgnoreCase(bean.getIdJenisObat())){
            hsCriteria.put("id_jenis_obat", bean.getIdJenisObat());
        }

        hsCriteria.put("flag","Y");
        try {
            results = jenisObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[ObatInapBoImpl.getListJenisObatEntity] Erro when searching data jenis obat ", e);
        }

        logger.info("[ObatInapBoImpl.getListJenisObatEntity] End <<<<<<");
        return results;
    }

    private void updateStokObat(ObatInap bean) throws GeneralBOException {
        logger.info("[ObatInapBoImpl.updateStokObat] Start >>>>>>>");

        if (bean != null){

            ImSimrsObatEntity entity = null;

            try {
                entity = obatDao.getById("idObat", bean.getIdObat());
            } catch (HibernateException e){
                logger.error("[TeamDokterBoImpl.updateStokObat] Error when update stok obatp ",e);
                throw new GeneralBOException("[TeamDokterBoImpl.updateStokObat] Error when update stok obat"+e.getMessage());
            }
            if(entity != null){

                if("new".equalsIgnoreCase(bean.getCekQty())){
                    entity.setQty(entity.getQty().subtract(bean.getQty()));
                }

                if("plus".equalsIgnoreCase(bean.getCekQty())){
                    entity.setQty(entity.getQty().add(bean.getQtyEdit()));
                }

                if("mines".equalsIgnoreCase(bean.getCekQty())){
                    entity.setQty(entity.getQty().subtract(bean.getQtyEdit()));
                }

                entity.setLastUpdate(bean.getLastUpdate());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
            }

            try {
                obatDao.updateAndSave(entity);
            } catch (HibernateException e){
                logger.error("[DiagnosaRawatBoImpl.updateStokObat] Error when update stok obat ", e);
                throw new GeneralBOException("Error when update stok obat " + e.getMessage());
            }
        }
        logger.info("[ObatInapBoImpl.updateStokObat] End <<<<<<");
    }
}