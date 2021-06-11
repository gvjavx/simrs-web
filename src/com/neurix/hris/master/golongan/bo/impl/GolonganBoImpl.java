package com.neurix.hris.master.golongan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.golongan.bo.GolonganBo;
import com.neurix.hris.master.golongan.dao.GolonganDao;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;
import com.neurix.hris.master.golongan.model.Golongan;
import com.neurix.hris.master.golongan.model.ImGolonganHistoryEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class GolonganBoImpl implements GolonganBo {

    protected static transient Logger logger = Logger.getLogger(GolonganBoImpl.class);
    private GolonganDao golonganDao;
    private BiodataDao biodataDao;

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        GolonganBoImpl.logger = logger;
    }

    public GolonganDao getGolonganDao() {
        return golonganDao;
    }

    public void setGolonganDao(GolonganDao golonganDao) {
        this.golonganDao = golonganDao;
    }

    @Override
    public void saveDelete(Golongan bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String golonganId = bean.getGolonganId();
            String historyId = "";
            ImGolonganEntity imGolonganEntity = null;
            ImGolonganHistoryEntity imGolonganHistoryEntity = new ImGolonganHistoryEntity();

            List<ImBiodataEntity> biodataEntityList = biodataDao.getBiodataByGolonganId(bean.getGolonganId());

            if (biodataEntityList.size()>0){
                String status = "ERROR : data tidak bisa dihapus dikarenakan sudah digunakan di transaksi";
                logger.error(status);
                throw new GeneralBOException(status);
            }

            try {
                // Get data from database by ID
                imGolonganEntity = golonganDao.getById("golonganId", golonganId);
                historyId = golonganDao.getNextGolonganHistoryId();
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imGolonganEntity != null) {
                imGolonganHistoryEntity.setId(historyId);
                imGolonganHistoryEntity.setGolonganId(imGolonganEntity.getGolonganId());
                imGolonganHistoryEntity.setGolonganName(imGolonganEntity.getGolonganName());
                imGolonganHistoryEntity.setGolonganName(imGolonganEntity.getGolonganName());
                imGolonganHistoryEntity.setFlag(imGolonganEntity.getFlag());
                imGolonganHistoryEntity.setAction(imGolonganEntity.getAction());
                imGolonganHistoryEntity.setLastUpdateWho(imGolonganEntity.getLastUpdateWho());
                imGolonganHistoryEntity.setLastUpdate(imGolonganEntity.getLastUpdate());
                imGolonganHistoryEntity.setCreatedWho(imGolonganEntity.getLastUpdateWho());
                imGolonganHistoryEntity.setCreatedDate(imGolonganEntity.getLastUpdate());

                // Modify from bean to entity serializable
                imGolonganEntity.setGolonganId(bean.getGolonganId());
                imGolonganEntity.setGolonganName(bean.getGolonganName());
                imGolonganEntity.setFlag(bean.getFlag());
                imGolonganEntity.setAction(bean.getAction());
                imGolonganEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imGolonganEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    golonganDao.updateAndSave(imGolonganEntity);
                    golonganDao.addAndSaveHistory(imGolonganHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[GolonganBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Golongan, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[GolonganBoImpl.saveDelete] Error, not found data Golongan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Golongan with request id, please check again your data ...");

            }
        }
        logger.info("[GolonganBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Golongan bean) throws GeneralBOException {
        logger.info("[GolonganBoImpl.saveEdit] start process >>>");
        String status = cekStatusName(bean.getGolonganName(), bean.getFlag());

        if (!status.equalsIgnoreCase("Exist")){
            if (bean!=null) {

                String golonganId = bean.getGolonganId();
                String historyId = "";

                ImGolonganEntity imGolonganEntity = null;
                ImGolonganHistoryEntity imGolonganHistoryEntity = new ImGolonganHistoryEntity();

                try {
                    // Get data from database by ID
                    imGolonganEntity = golonganDao.getById("golonganId", golonganId);
                    historyId = golonganDao.getNextGolonganHistoryId();
                } catch (HibernateException e) {
                    logger.error("[GolonganBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data Golongan by Kode Golongan, please inform to your admin...," + e.getMessage());
                }

                if (imGolonganEntity != null) {
                    //
                    imGolonganHistoryEntity.setId(historyId);
                    imGolonganHistoryEntity.setGolonganId(imGolonganEntity.getGolonganId());
                    imGolonganHistoryEntity.setGolonganName(imGolonganEntity.getGolonganName());
                    imGolonganHistoryEntity.setGolonganName(imGolonganEntity.getGolonganName());
                    imGolonganHistoryEntity.setFlag(imGolonganEntity.getFlag());
                    imGolonganHistoryEntity.setAction(imGolonganEntity.getAction());
                    imGolonganHistoryEntity.setLastUpdateWho(imGolonganEntity.getLastUpdateWho());
                    imGolonganHistoryEntity.setLastUpdate(imGolonganEntity.getLastUpdate());
                    imGolonganHistoryEntity.setCreatedWho(imGolonganEntity.getLastUpdateWho());
                    imGolonganHistoryEntity.setCreatedDate(imGolonganEntity.getLastUpdate());

                    imGolonganEntity.setGolonganId(bean.getGolonganId());
                    imGolonganEntity.setGolonganName(bean.getGolonganName());
                    imGolonganEntity.setLevel(Integer.parseInt(bean.getStLevel()));
                    imGolonganEntity.setFlag(bean.getFlag());
                    imGolonganEntity.setAction(bean.getAction());
                    imGolonganEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imGolonganEntity.setLastUpdate(bean.getLastUpdate());

                    String flag;
                    try {
                        // Update into database
                        golonganDao.updateAndSave(imGolonganEntity);
                        golonganDao.addAndSaveHistory(imGolonganHistoryEntity);
//                    condition = "Data SuccessFully Updated";
                    } catch (HibernateException e) {
                        logger.error("[GolonganBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Golongan, please info to your admin..." + e.getMessage());
                    }
                } else {
                    logger.error("[GolonganBoImpl.saveEdit] Error, not found data Golongan with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data Golongan with request id, please check again your data ...");
//                condition = "Error, not found data Golongan with request id, please check again your data ...";
                }
            }
        }else{
            throw new GeneralBOException("Maaf Data Dengan Grade/Nama Tersebut Sudah Ada");
        }

        logger.info("[GolonganBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Golongan saveAdd(Golongan bean) throws GeneralBOException {
        logger.info("[GolonganBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String status = cekStatus(bean.getLevel(),bean.getGolonganName(), bean.getFlag());
            if (!status.equalsIgnoreCase("Exist")){
                String golonganId;
                try {
                    // Generating ID, get from postgre sequence
                    golonganId = golonganDao.getNextGolonganId();
                } catch (HibernateException e) {
                    logger.error("[GolonganBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence golonganId id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImGolonganEntity imGolonganEntity = new ImGolonganEntity();

                imGolonganEntity.setGolonganId(golonganId);
                imGolonganEntity.setGolonganName(bean.getGolonganName());
                imGolonganEntity.setLevel(bean.getLevel());
                imGolonganEntity.setGolPensiun(bean.getGolPensiun());
                imGolonganEntity.setMsKerjaGolAwal(bean.getMsKerjaAwal());
                imGolonganEntity.setMsKerjaGolAkhir(bean.getMsKerjaAkhir());
                imGolonganEntity.setFlag(bean.getFlag());
                imGolonganEntity.setAction(bean.getAction());
                imGolonganEntity.setCreatedWho(bean.getCreatedWho());
                imGolonganEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imGolonganEntity.setCreatedDate(bean.getCreatedDate());
                imGolonganEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    golonganDao.addAndSave(imGolonganEntity);
                } catch (HibernateException e) {
                    logger.error("[GolonganBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Golongan, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Tidak boleh add dengan grade / nama yang sama ");
            }
        }

        logger.info("[GolonganBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Golongan> getByCriteria(Golongan searchBean) throws GeneralBOException {
        logger.info("[GolonganBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Golongan> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getGolonganId() != null && !"".equalsIgnoreCase(searchBean.getGolonganId())) {
                hsCriteria.put("golongan_id", searchBean.getGolonganId());
            }
            if (searchBean.getGolonganName() != null && !"".equalsIgnoreCase(searchBean.getGolonganName())) {
                hsCriteria.put("golongan_name", searchBean.getGolonganName());
            }
            if (searchBean.getStLevel() != null && !"".equalsIgnoreCase(searchBean.getStLevel())) {
                hsCriteria.put("grade_level", Integer.parseInt(searchBean.getStLevel()));
            }

            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                if ("N".equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchBean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }


            List<ImGolonganEntity> imGolonganEntity = null;
            try {

                imGolonganEntity = golonganDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[GolonganBoImpl.getSearchGolonganByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imGolonganEntity != null){
                Golongan returnGolongan;
                // Looping from dao to object and save in collection
                for(ImGolonganEntity golonganEntity : imGolonganEntity){
                    returnGolongan = new Golongan();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                    returnGolongan.setGolonganId(golonganEntity.getGolonganId());
                    returnGolongan.setGolonganName(golonganEntity.getGolonganName());
                    returnGolongan.setLevel(golonganEntity.getLevel());
                    returnGolongan.setStLevel(golonganEntity.getLevel().toString());
                    returnGolongan.setGolPensiun(golonganEntity.getGolPensiun());
                    returnGolongan.setMsKerjaAwal(golonganEntity.getMsKerjaGolAwal());
                    returnGolongan.setMsKerjaAkhir(golonganEntity.getMsKerjaGolAkhir());
                    returnGolongan.setCreatedWho(golonganEntity.getCreatedWho());

                    if (golonganEntity.getCreatedDate() != null){
                        String createdDate = dateFormat.format(golonganEntity.getCreatedDate());
                        returnGolongan.setStCreatedDate(createdDate);
                    }
                    returnGolongan.setCreatedDate(golonganEntity.getCreatedDate());

                    if (golonganEntity.getLastUpdate() != null){
                        String lastUpdate = dateFormat.format(golonganEntity.getLastUpdate());
                        returnGolongan.setStLastUpdate(lastUpdate);
                    }
                    returnGolongan.setLastUpdate(golonganEntity.getLastUpdate());

                    returnGolongan.setLastUpdateWho(golonganEntity.getLastUpdateWho());
                    returnGolongan.setAction(golonganEntity.getAction());
                    returnGolongan.setFlag(golonganEntity.getFlag());
                    listOfResult.add(returnGolongan);
                }
            }
        }
        logger.info("[GolonganBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Golongan> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<Golongan> getComboGolonganWithCriteria(String query) throws GeneralBOException {
        logger.info("[GolonganBoImpl.getComboGolonganWithCriteria] start process >>>");

        List<Golongan> listComboGolongan = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImGolonganEntity> listGolongan = null;
        try {
            listGolongan = golonganDao.getListGolongan(criteria);
        } catch (HibernateException e) {
            logger.error("[GolonganBoImpl.getComboGolonganWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list golongan with criteria, please info to your admin..." + e.getMessage());
        }

        if (listGolongan != null) {
            for (ImGolonganEntity imGolonganEntity : listGolongan) {
                Golongan itemComboGolongan = new Golongan();
                itemComboGolongan.setGolonganId(imGolonganEntity.getGolonganId());
                itemComboGolongan.setGolonganName(imGolonganEntity.getGolonganName());
                listComboGolongan.add(itemComboGolongan);
            }
        }
        logger.info("[GolonganBoImpl.getComboGolonganWithCriteria] end process <<<");
        return listComboGolongan;
    }
    public String cekStatus(Integer level,String nama, String flag)throws GeneralBOException{
        String status ="";
        List<ImGolonganEntity> golonganEntityList = new ArrayList<>();
        try{
            golonganEntityList = golonganDao.getDataGolongan(level,nama, flag);
        }catch (HibernateException e){
            logger.error("[GolonganBoImpl.cekStatus] Error, "+e.getMessage());
            throw new GeneralBOException("Found problem when saving data, please info to your admin..."+e.getMessage());
        }
        if (golonganEntityList.size() > 0){
            status = "exist";
        }else {
            status = "notExist";
        }
        return status;
    }

    public String cekStatusName(String nama, String flag)throws GeneralBOException{
        String status ="";
        List<ImGolonganEntity> golonganEntityList = new ArrayList<>();
        try{
            golonganEntityList = golonganDao.getDataGolonganName(nama, flag);
        }catch (HibernateException e){
            logger.error("[GolonganBoImpl.cekStatus] Error, "+e.getMessage());
            throw new GeneralBOException("Found problem when saving data, please info to your admin..."+e.getMessage());
        }
        if (golonganEntityList.size() > 0){
            status = "exist";
        }else {
            status = "notExist";
        }
        return status;
    }

    public List<Golongan> getDetailGolongan(String golPen) throws GeneralBOException{
        logger.info("[GolonganBoImpl.getRangeMasaGol] start process >>>");

        List<Golongan> listGolongan = new ArrayList();
        String criteria = "%" + golPen + "%";

        List<ImGolonganEntity> listGolonganEntities = null;
        try {
            listGolonganEntities = golonganDao.getGolonganById(criteria);
        } catch (HibernateException e) {
            logger.error("[GolonganBoImpl.getRangeMasaGol] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list range masa golongan with criteria, please info to your admin..." + e.getMessage());
        }

        if (listGolongan != null) {
            for (ImGolonganEntity imGolonganEntity : listGolonganEntities) {
                Golongan itemGolongan = new Golongan();
                itemGolongan.setGolonganId(imGolonganEntity.getGolonganId());
                itemGolongan.setGolonganName(imGolonganEntity.getGolonganName());
                itemGolongan.setMsKerjaAwal(imGolonganEntity.getMsKerjaGolAwal());
                itemGolongan.setMsKerjaAkhir(imGolonganEntity.getMsKerjaGolAkhir());
                listGolongan.add(itemGolongan);
            }
        }
        logger.info("[GolonganBoImpl.getComboGolonganWithCriteria] end process <<<");
        return listGolongan;
    }
}
