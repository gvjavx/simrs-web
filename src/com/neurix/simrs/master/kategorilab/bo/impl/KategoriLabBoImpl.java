package com.neurix.simrs.master.kategorilab.bo.impl;

import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisobat.bo.JenisObatBo;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import com.neurix.simrs.master.kategorilab.bo.KategoriLabBo;
import com.neurix.simrs.master.kategorilab.dao.KategoriLabDao;
import com.neurix.simrs.master.kategorilab.model.ImSimrsKategoriLabEntity;
import com.neurix.simrs.master.kategorilab.model.KategoriLab;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KategoriLabBoImpl implements KategoriLabBo {

    protected static transient Logger logger = Logger.getLogger(KategoriLabBoImpl.class);
    private KategoriLabDao kategoriLabDao;
    private PositionDao positionDao;

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setKategoriLabDao(KategoriLabDao kategoriLabDao) {
        this.kategoriLabDao = kategoriLabDao;
    }


    @Override
    public void saveDelete(KategoriLab bean) throws GeneralBOException {
        logger.info("[saveDelete.KategoriLabBoImpl] start process >>>");

        if (bean!=null) {
            String idKategoriLab = bean.getIdKategoriLab();
            String status = cekBeforeDelete(idKategoriLab);
            if (!status.equalsIgnoreCase("exist")){
                ImSimrsKategoriLabEntity entity = null;

                try {
                    // Get data from database by ID
                    entity = kategoriLabDao.getById("idKategoriLab", idKategoriLab);
                } catch (HibernateException e) {
                    logger.error("[KategoriLabBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data Lab by IdLab, please inform to your admin...," + e.getMessage());
                }

                if (entity != null) {

                    // Modify from bean to entity serializable
                    entity.setIdKategoriLab(bean.getIdKategoriLab());
                    entity.setFlag(bean.getFlag());
                    entity.setAction(bean.getAction());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    entity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Delete (Edit) into database
                        kategoriLabDao.updateAndSave(entity);
                    } catch (HibernateException e) {
                        logger.error("[KategoriLabBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Lab, please info to your admin..." + e.getMessage());
                    }

                } else {
                    logger.error("[KategoriLabBoImpl.saveDelete] Error, not found data Lab with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data Lab with request id, please check again your data ...");
                }
            }else {
                throw new GeneralBOException("Maaf Data tidak dapat dihapus, karna masih digunakan pada data Transaksi");
            }
        }
        logger.info("[KategoriLabBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(KategoriLab bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String kategoriLabId = bean.getIdKategoriLab();

            ImSimrsKategoriLabEntity entity = null;
            try {
                // Get data from database by ID
                entity = kategoriLabDao.getById("idKategoriLab", kategoriLabId);
                //historyId = payrollSkalaGajiDao.getNextSkalaGaji();
            } catch (HibernateException e) {
                logger.error("[KategoriLabBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data KategoriLab by IdKategoriLab, please inform to your admin...," + e.getMessage());
            }

            if (entity != null) {
                if (bean.getNamaKategori().equalsIgnoreCase(entity.getNamaKategori())){
                    entity.setIdKategoriLab(bean.getIdKategoriLab());
                    entity.setNamaKategori(bean.getNamaKategori());
                    entity.setDivisiId(bean.getDivisiId());
                    entity.setFlag(bean.getFlag());
                    entity.setAction(bean.getAction());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    entity.setLastUpdate(bean.getLastUpdate());

                    String flag;
                    try {
                        // Update into database
                        kategoriLabDao.updateAndSave(entity);
                        //payrollSkalaGajiDao.addAndSaveHistory(imPayrollSkalaGajiHistoryEntity);
                    } catch (HibernateException e) {
                        logger.error("[KategoriLabBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data KategoriLab, please info to your admin..." + e.getMessage());
                    }
                }else {
                    String status = cekStatus(bean.getNamaKategori());
                    if (!status.equalsIgnoreCase("exist")){
                        entity.setIdKategoriLab(bean.getIdKategoriLab());
                        entity.setNamaKategori(bean.getNamaKategori());
                        entity.setDivisiId(bean.getDivisiId());
                        entity.setFlag(bean.getFlag());
                        entity.setAction(bean.getAction());
                        entity.setLastUpdateWho(bean.getLastUpdateWho());
                        entity.setLastUpdate(bean.getLastUpdate());

                        String flag;
                        try {
                            // Update into database
                            kategoriLabDao.updateAndSave(entity);
                            //payrollSkalaGajiDao.addAndSaveHistory(imPayrollSkalaGajiHistoryEntity);
                        } catch (HibernateException e) {
                            logger.error("[KategoriLabBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving update data KategoriLab, please info to your admin..." + e.getMessage());
                        }
                    }else {
                        throw new GeneralBOException("Maaf Data dengan Nama Kategori Lab Tersebut Sudah Ada");
                    }
                }
            } else {
                logger.error("[KategoriLabBoImpl.saveEdit] Error, not found data Lab with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data KategoriLab with request id, please check again your data ...");
            }
        }
        logger.info("[KategoriLabBoImpl.saveEdit] end process <<<");
    }

    @Override
    public KategoriLab saveAdd(KategoriLab bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String status = cekStatus(bean.getNamaKategori());
            String kategoriLabId;
            if (!status.equalsIgnoreCase("exist")){
                try {
                    // Generating ID, get from postgre sequence
                    kategoriLabId = kategoriLabDao.getNextLabKategoriId();
                } catch (HibernateException e) {
                    logger.error("[KategoriLabBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence KategoriId id, please info to your admin..." + e.getMessage());
                }
                // creating object entity serializable
                ImSimrsKategoriLabEntity entity = new ImSimrsKategoriLabEntity();
                entity.setIdKategoriLab(kategoriLabId);
                entity.setNamaKategori(bean.getNamaKategori());
                entity.setDivisiId(bean.getDivisiId());
                entity.setFlag(bean.getFlag());
                entity.setAction(bean.getAction());
                entity.setCreatedWho(bean.getCreatedWho());
                entity.setLastUpdateWho(bean.getLastUpdateWho());
                entity.setCreatedDate(bean.getCreatedDate());
                entity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    kategoriLabDao.addAndSave(entity);
                } catch (HibernateException e) {
                    logger.error("[KategoriLabBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data KategoriLab, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Data dengan Nama Kategori Lab Tersebut Sudah Ada");
            }
        }

        logger.info("[LabBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<KategoriLab> getByCriteria(KategoriLab bean) throws GeneralBOException {
        logger.info("[KategoriLabBoImpl.getByCriteria] Start >>>>>>>");
        List<KategoriLab> result = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdKategoriLab() != null && !"".equalsIgnoreCase(bean.getIdKategoriLab())) {
                hsCriteria.put("id_kategori_lab", bean.getIdKategoriLab());
            }
            if (bean.getNamaKategori() != null && !"".equalsIgnoreCase(bean.getNamaKategori())) {
                hsCriteria.put("nama_kategori", bean.getNamaKategori());
            }
            if (bean.getDivisiId() != null && !"".equalsIgnoreCase(bean.getDivisiId())) {
                hsCriteria.put("divisi_id", bean.getDivisiId());
            }
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                if ("N".equalsIgnoreCase(bean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", bean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImSimrsKategoriLabEntity> kategoriLabEntityList = new ArrayList<>();

            try {
                kategoriLabEntityList = kategoriLabDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[KategoriLabBoImpl.getByCriteria] error when get data jenis obat by get by criteria "+ e.getMessage());
            }

            if (!kategoriLabEntityList.isEmpty()){
                KategoriLab kategoriLab;
                for (ImSimrsKategoriLabEntity kategoriLabEntity : kategoriLabEntityList){
                    kategoriLab = new KategoriLab();
                    kategoriLab.setIdKategoriLab(kategoriLabEntity.getIdKategoriLab());
                    kategoriLab.setNamaKategori(kategoriLabEntity.getNamaKategori());
                    kategoriLab.setFlag(kategoriLabEntity.getFlag());
                    kategoriLab.setAction(kategoriLabEntity.getAction());
                    if(kategoriLabEntity.getCreatedDate() != null){
                        kategoriLab.setStCreatedDate(kategoriLabEntity.getCreatedDate().toString());
                    }
                    kategoriLab.setCreatedDate(kategoriLabEntity.getCreatedDate());
                    kategoriLab.setCreatedWho(kategoriLabEntity.getCreatedWho());
                    if(kategoriLabEntity.getLastUpdate() != null){
                        kategoriLab.setStLastUpdate(kategoriLabEntity.getLastUpdate().toString());
                    }
                    kategoriLab.setLastUpdate(kategoriLabEntity.getLastUpdate());
                    kategoriLab.setLastUpdateWho(kategoriLabEntity.getLastUpdateWho());

                    //set divisi dari tabel lain ke tabel kategorilab dan mengambil nama berdasarkan id nya
                    if (kategoriLabEntity.getDivisiId() != null){
                        kategoriLab.setDivisiId(kategoriLabEntity.getDivisiId());
                        ImPosition position = positionDao.getById("positionId",kategoriLabEntity.getDivisiId());
                        if(position != null){
                            kategoriLab.setDivisiName(position.getPositionName());
                        }
                    }
                    result.add(kategoriLab);
                }
            }
        }
        logger.info("[KategoriLabBoImpl.getByCriteria] End <<<<<<<");
        return result;
    }

    @Override
    public List<KategoriLab> getKategoriLab(String idLab, String branchId) throws GeneralBOException {
        List<KategoriLab> kategoriLabList = new ArrayList<>();
        try {
            kategoriLabList = kategoriLabDao.getKategoriLabByLab(idLab, branchId);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return kategoriLabList;
    }

    @Override
    public ImSimrsKategoriLabEntity getDataLab(String idKategori) throws GeneralBOException {
        return kategoriLabDao.getById("idKategoriLab", idKategori);
    }

    @Override
    public List<KategoriLab> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public String cekStatus(String namaPelayanan)throws GeneralBOException{
        String status ="";
        List<ImSimrsKategoriLabEntity> entities = new ArrayList<>();
        try {
            entities = kategoriLabDao.getDataKategoriLab(namaPelayanan);
        } catch (HibernateException e) {
            logger.error("[KategoriLabBoImpl.cekStatus] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }

    public String cekBeforeDelete(String idKategoriLab)throws GeneralBOException{
        String status ="";
        List<ImSimrsKategoriLabEntity> entities = new ArrayList<>();
        try {
            entities = kategoriLabDao.cekData(idKategoriLab);
        } catch (HibernateException e) {
            logger.error("[KategoriLabBoImpl.cekBeforeDelete] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }
}