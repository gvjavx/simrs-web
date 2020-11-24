package com.neurix.simrs.master.jenisperiksapasien.bo.impl;

import com.neurix.akuntansi.master.master.dao.MasterDao;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisperiksapasien.bo.AsuransiBo;
import com.neurix.simrs.master.jenisperiksapasien.dao.AsuransiDao;
import com.neurix.simrs.master.jenisperiksapasien.model.Asuransi;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsuransiBoImpl implements AsuransiBo  {

    protected static transient Logger logger = Logger.getLogger(AsuransiBoImpl.class);
    private AsuransiDao asuransiDao;
    private MasterDao masterDao;

    public static void setLogger(Logger logger) {
        AsuransiBoImpl.logger = logger;
    }

    //seter geter dari masterDao
    public void setMasterDao(MasterDao masterDao) {
        this.masterDao = masterDao;
    }

    @Override
    public void saveDelete(Asuransi bean) throws GeneralBOException {
        logger.info("[saveDelete.PelayananBoImpl] start process >>>");

        if (bean!=null) {
            String status = cekBeforeDelete(bean.getIdAsuransi());
            if (!status.equalsIgnoreCase("exist")){
                String idAsuransi = bean.getIdAsuransi();
                ImSimrsAsuransiEntity entity = null;

                try {
                    // Get data from database by ID
                    entity = asuransiDao.getById("idAsuransi", idAsuransi);
                } catch (HibernateException e) {
                    logger.error("[AsuransiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
                }

                if (entity != null) {

                    // Modify from bean to entity serializable
                    entity.setIdAsuransi(bean.getIdAsuransi());
                    entity.setFlag(bean.getFlag());
                    entity.setAction(bean.getAction());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    entity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Delete (Edit) into database
                        asuransiDao.updateAndSave(entity);
                    } catch (HibernateException e) {
                        logger.error("[AsuransiBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Asuransi, please info to your admin..." + e.getMessage());
                    }


                } else {
                    logger.error("[AsuransiBoImpl.saveDelete] Error, not found data Asuransi with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data Asuransi with request id, please check again your data ...");
                }
            }else {
                throw new GeneralBOException("Maaf Data tidak dapat dihapus, karna masih digunakan pada data Transaksi");
            }
        }
        logger.info("[AsuransiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Asuransi bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String asuransiId = bean.getIdAsuransi();
            ImSimrsAsuransiEntity imSimrsAsuransiEntity = null;
            try {
                // Get data from database by ID
                imSimrsAsuransiEntity = asuransiDao.getById("idAsuransi", asuransiId);
                //historyId = payrollSkalaGajiDao.getNextSkalaGaji();
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollSkalaGaji by Kode PayrollSkalaGaji, please inform to your admin...," + e.getMessage());
            }
//          mengambil nama master / nama asuransi dari masterdao berdasarkan no master;
            ImMasterEntity masterEntity = new ImMasterEntity();
            try {
                masterEntity = masterDao.getById("primaryKey.nomorMaster", bean.getNoMaster());
            }catch (HibernateException e){
                logger.error("Found Error when search asuransi "+e.getMessage());
            }
//           imSimrsAsuransiEntity.setIdAsuransi(asuransiId);
            //cari ke dao akun Master berdasarkan no master

            if (imSimrsAsuransiEntity != null) {
                if (imSimrsAsuransiEntity.getNamaAsuransi().equalsIgnoreCase(bean.getNamaAsuransi())){
                    imSimrsAsuransiEntity.setIdAsuransi(asuransiId);
//                      imSimrsAsuransiEntity.setNamaAsuransi(masterEntity.getNama());
                    if (masterEntity != null){
                        imSimrsAsuransiEntity.setNamaAsuransi(masterEntity.getNama());
                        //set berdasarkan kebutuhan fild yang di ambil dari tabel lain
                    }
                    imSimrsAsuransiEntity.setNoMaster(bean.getNoMaster());
                    imSimrsAsuransiEntity.setFlag(bean.getFlag());
                    imSimrsAsuransiEntity.setIsLaka(bean.getIsLaka());
                    imSimrsAsuransiEntity.setAction(bean.getAction());
                    imSimrsAsuransiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imSimrsAsuransiEntity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Update into database
                        asuransiDao.updateAndSave(imSimrsAsuransiEntity);
                        //payrollSkalaGajiDao.addAndSaveHistory(imPayrollSkalaGajiHistoryEntity);
                    } catch (HibernateException e) {
                        logger.error("[AsuransiBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Asuransi, please info to your admin..." + e.getMessage());
                    }
                }else {
                    String status = cekStatus(bean.getNoMaster(),bean.getIdAsuransi());
                    if (!status.equalsIgnoreCase("exist")){
                        imSimrsAsuransiEntity.setIdAsuransi(bean.getIdAsuransi());
//                        imSimrsAsuransiEntity.setNamaAsuransi(masterEntity.getNama());

                        if (masterEntity != null){
                            imSimrsAsuransiEntity.setNamaAsuransi(masterEntity.getNama());
                            //set berdasarkan kebutuhan fild yang di ambil dari tabel lain
                        }

                        imSimrsAsuransiEntity.setNoMaster(bean.getNoMaster());
                        imSimrsAsuransiEntity.setFlag(bean.getFlag());
                        imSimrsAsuransiEntity.setIsLaka(bean.getIsLaka());
                        imSimrsAsuransiEntity.setAction(bean.getAction());
                        imSimrsAsuransiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        imSimrsAsuransiEntity.setLastUpdate(bean.getLastUpdate());

                        try {
                            // Update into database
                            asuransiDao.updateAndSave(imSimrsAsuransiEntity);
                            //payrollSkalaGajiDao.addAndSaveHistory(imPayrollSkalaGajiHistoryEntity);
                        } catch (HibernateException e) {
                            logger.error("[AsuransiBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving update data Asuransi, please info to your admin..." + e.getMessage());
                        }
                    }else {
                        throw new GeneralBOException("Maaf Data dengan Nama Asuransi Tersebut Sudah Ada");
                    }
                }
            } else {
                logger.error("[AsuransiBoImpl.saveEdit] Error, not found data Asuransi with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Asuransi with request id, please check again your data ...");
            }
        }
        logger.info("[AsuransiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Asuransi saveAdd(Asuransi bean) throws GeneralBOException {
        logger.info("[AsurnasiBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            List<ImSimrsAsuransiEntity> cekList = new ArrayList<>();
            try {
                cekList = asuransiDao.getDataAsuransibaru(bean.getNoMaster());
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }
            if(cekList.size() > 0){
                throw new GeneralBOException("Nama Asuransi sudah ada...!");
            }else{
                String asuransiId;
                try {
                    // Generating ID, get from postgre sequence
                    asuransiId = asuransiDao.getNextAsuransuId();
                } catch (HibernateException e) {
                    logger.error("[AsuransiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence Asuransi id, please info to your admin..." + e.getMessage());
                }
                // mengambil nama master / nama asuransi dari masterdao berdasarkan no master;
                ImMasterEntity masterEntity = new ImMasterEntity();
                try {
                    masterEntity = masterDao.getById("primaryKey.nomorMaster", bean.getNoMaster());
                }catch (HibernateException e){
                    logger.error("Found Error when search asuransi "+e.getMessage());
                }

                // creating object entity serializable
                ImSimrsAsuransiEntity imSimrsAsuransiEntity = new ImSimrsAsuransiEntity();

                imSimrsAsuransiEntity.setIdAsuransi(asuransiId);
                //cari ke dao akun Master berdasarkan no master

                imSimrsAsuransiEntity.setNoMaster(bean.getNoMaster());
                imSimrsAsuransiEntity.setFlag(bean.getFlag());
                imSimrsAsuransiEntity.setIsLaka(bean.getIsLaka());
                imSimrsAsuransiEntity.setAction(bean.getAction());
                imSimrsAsuransiEntity.setCreatedWho(bean.getCreatedWho());
                imSimrsAsuransiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsAsuransiEntity.setCreatedDate(bean.getCreatedDate());
                imSimrsAsuransiEntity.setLastUpdate(bean.getLastUpdate());
                if (masterEntity != null){
                    imSimrsAsuransiEntity.setNamaAsuransi(masterEntity.getNama());
                    //set berdasarkan kebutuhan fild yang di ambil dari tabel lain
                }
                try {
                    // insert into database
                    asuransiDao.addAndSave(imSimrsAsuransiEntity);
                } catch (HibernateException e) {
                    logger.error("[AsuransiiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Asuransi, please info to your admin..." + e.getMessage());
                }
            }

        }






//            String status = cekStatus(bean.getNoMaster(), bean.getIdAsuransi());

//            if (!status.equalsIgnoreCase("exist")){


        logger.info("[AsuransiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Asuransi> getByCriteria(Asuransi bean) throws GeneralBOException {
        List<Asuransi> asuransiList = new ArrayList<>();
        if(bean != null){

            Map hsCriteria = new HashMap();

            if(bean.getIdAsuransi() != null && !"".equalsIgnoreCase(bean.getIdAsuransi())){
                hsCriteria.put("id_asuransi", bean.getIdAsuransi());
            }
            if(bean.getNamaAsuransi() != null && !"".equalsIgnoreCase(bean.getNamaAsuransi())){
                hsCriteria.put("nama_asuransi", bean.getNamaAsuransi());
            }
            if(bean.getNoMaster() != null && !"".equalsIgnoreCase(bean.getNoMaster())){
                hsCriteria.put("no_master", bean.getNoMaster());
            }
            if(bean.getIsLaka() != null && !"".equalsIgnoreCase(bean.getIsLaka())){
                hsCriteria.put("is_laka", bean.getIsLaka());
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

            List<ImSimrsAsuransiEntity> asuransiEntityList = new ArrayList<>();
            try {
                asuransiEntityList = asuransiDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error("Found Error when search asuransi "+e.getMessage());
            }

            if(asuransiEntityList.size() > 0){
                for (ImSimrsAsuransiEntity entity: asuransiEntityList){
                    Asuransi asuransi = new Asuransi();
                    asuransi.setIdAsuransi(entity.getIdAsuransi());
                    asuransi.setNamaAsuransi(entity.getNamaAsuransi());
                    asuransi.setNoMaster(entity.getNoMaster());
                    asuransi.setFlag(entity.getFlag());
                    asuransi.setIsLaka(entity.getIsLaka());
                    asuransi.setAction(entity.getAction());
                    asuransi.setCreatedWho(entity.getCreatedWho());
                    asuransi.setStCreatedDate(entity.getCreatedDate().toString());
                    asuransi.setCreatedDate(entity.getCreatedDate());
                    asuransi.setStLastUpdate(entity.getLastUpdate().toString());
                    asuransi.setLastUpdate(entity.getLastUpdate());
                    asuransi.setLastUpdateWho(entity.getLastUpdateWho());
                    asuransi.setIsLaka(entity.getIsLaka());
                    asuransiList.add(asuransi);
                }
            }
        }

        return asuransiList;
    }

    @Override
    public List<Asuransi> getAll() throws GeneralBOException {

        return null;
    }


    @Override
    public ImSimrsAsuransiEntity getEntityAsuransiById(String idAsuransi) throws GeneralBOException {
        return asuransiDao.getById("idAsuransi", idAsuransi);
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setAsuransiDao(AsuransiDao asuransiDao) {
        this.asuransiDao = asuransiDao;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public String cekStatus(String noMaster, String idAsuransi)throws GeneralBOException{
        String status ="";
        List<ImSimrsAsuransiEntity> entities = new ArrayList<>();
        try {
            entities = asuransiDao.getDataAsuransi(noMaster,idAsuransi);
        } catch (HibernateException e) {
            logger.error("[AsuransiBoImpl.cekStatus] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }

    public String cekBeforeDelete(String idAsuransi)throws GeneralBOException{
        String status ="";
        List<ImSimrsAsuransiEntity> entities = new ArrayList<>();
        try {
            entities = asuransiDao.cekData(idAsuransi);
        } catch (HibernateException e) {
            logger.error("[AsuransiBoImpl.cekBeforeDelete] Error, " + e.getMessage());
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
