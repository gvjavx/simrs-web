package com.neurix.hris.master.strukturJabatan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.positionBagian.dao.PositionBagianDao;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianEntity;
import com.neurix.hris.master.strukturJabatan.bo.StrukturJabatanBo;
import com.neurix.hris.master.strukturJabatan.dao.StrukturJabatanDao;
import com.neurix.hris.master.strukturJabatan.model.ImStrukturJabatanHistoryEntity;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.master.strukturJabatan.model.ImStrukturJabatanEntity;
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
public class StrukturJabatanBoImpl implements StrukturJabatanBo {

    protected static transient Logger logger = Logger.getLogger(StrukturJabatanBoImpl.class);
    private StrukturJabatanDao strukturJabatanDao;
    private PositionBagianDao positionBagianDao;

    public PositionBagianDao getPositionBagianDao() {
        return positionBagianDao;
    }

    public void setPositionBagianDao(PositionBagianDao positionBagianDao) {
        this.positionBagianDao = positionBagianDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        StrukturJabatanBoImpl.logger = logger;
    }

    public StrukturJabatanDao getStrukturJabatanDao() {
        return strukturJabatanDao;
    }

    public void setStrukturJabatanDao(StrukturJabatanDao strukturJabatanDao) {
        this.strukturJabatanDao = strukturJabatanDao;
    }

    @Override
    public String saveDeleteStruktur(StrukturJabatan bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        String status = "";

        if (bean!=null) {

            String strukturJabatanId = bean.getStrukturJabatanId();

            // validasi jika masih digunakan oleh bawahan
            List<ImStrukturJabatanEntity> strukturJabatanEntityList = strukturJabatanDao.getIdStrukturJabatanBawah(strukturJabatanId);
            if (strukturJabatanEntityList.size()>0){
                status = "Struktur jabatan ini masih memiliki jabatan di bawahnya , silahkan hapus / pindahkan terlebih dahulu jabatan di bawahnya";
            }

            if ("".equalsIgnoreCase(status)){
                ImStrukturJabatanEntity imStrukturJabatanEntity = null;
                ImStrukturJabatanHistoryEntity imStrukturJabatanHistoryEntity = new ImStrukturJabatanHistoryEntity();
                String idHistory = "";
                try {
                    // Get data from database by ID
                    imStrukturJabatanEntity = strukturJabatanDao.getById("strukturJabatanId", strukturJabatanId);
                    idHistory = strukturJabatanDao.getNextStrukturJabatanHistoryId();
                } catch (HibernateException e) {
                    logger.error("[StrukturJabatanBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
                }

                if (imStrukturJabatanEntity != null) {
                    imStrukturJabatanHistoryEntity.setId(idHistory);
                    imStrukturJabatanHistoryEntity.setStrukturJabatanId(imStrukturJabatanEntity.getStrukturJabatanId());
                    imStrukturJabatanHistoryEntity.setPositionId(imStrukturJabatanEntity.getPositionId());
                    imStrukturJabatanHistoryEntity.setBranchId(imStrukturJabatanEntity.getBranchId());
                    imStrukturJabatanHistoryEntity.setParentId(imStrukturJabatanEntity.getParentId());
                    imStrukturJabatanHistoryEntity.setLevel(imStrukturJabatanEntity.getLevel());
                    imStrukturJabatanHistoryEntity.setFlag(imStrukturJabatanEntity.getFlag());
                    imStrukturJabatanHistoryEntity.setAction(imStrukturJabatanEntity.getAction());
                    imStrukturJabatanHistoryEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imStrukturJabatanHistoryEntity.setLastUpdate(bean.getLastUpdate());
                    imStrukturJabatanHistoryEntity.setCreatedWho(imStrukturJabatanEntity.getCreatedWho());
                    imStrukturJabatanHistoryEntity.setCreatedDate(imStrukturJabatanEntity.getCreatedDate());

                    // Modify from bean to entity serializable
                    imStrukturJabatanEntity.setStrukturJabatanId(bean.getStrukturJabatanId());
                    imStrukturJabatanEntity.setPositionId(bean.getPositionId());
                    imStrukturJabatanEntity.setLevel(bean.getLevel());
                    imStrukturJabatanEntity.setParentId(bean.getParentId());
                    imStrukturJabatanEntity.setFlag(bean.getFlag());
                    imStrukturJabatanEntity.setAction(bean.getAction());
                    imStrukturJabatanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imStrukturJabatanEntity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Delete (Edit) into database
                        strukturJabatanDao.updateAndSave(imStrukturJabatanEntity);
                        strukturJabatanDao.addAndSaveHistory(imStrukturJabatanHistoryEntity);
                    } catch (HibernateException e) {
                        logger.error("[StrukturJabatanBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data StrukturJabatan, please info to your admin..." + e.getMessage());
                    }


                } else {
                    logger.error("[StrukturJabatanBoImpl.saveDelete] Error, not found data StrukturJabatan with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data StrukturJabatan with request id, please check again your data ...");

                }
            }
        }
        logger.info("[StrukturJabatanBoImpl.saveDelete] end process <<<");
        return status;
    }

    @Override
    public void saveDelete(StrukturJabatan bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(StrukturJabatan bean) throws GeneralBOException {
        logger.info("[StrukturJabatanBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {

            String strukturJabatanId = bean.getStrukturJabatanId();
            String idHistory = "";
            ImStrukturJabatanEntity imStrukturJabatanEntity = null;
            ImStrukturJabatanHistoryEntity imStrukturJabatanHistoryEntity = new ImStrukturJabatanHistoryEntity();

            try {
                // Get data from database by ID
                imStrukturJabatanEntity = strukturJabatanDao.getById("strukturJabatanId", strukturJabatanId);
                idHistory = strukturJabatanDao.getNextStrukturJabatanHistoryId();
            } catch (HibernateException e) {
                logger.error("[StrukturJabatanBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data StrukturJabatan by Kode StrukturJabatan, please inform to your admin...," + e.getMessage());
            }

            if (imStrukturJabatanEntity != null) {
                String[] parts = bean.getParentId().split("-");

                imStrukturJabatanHistoryEntity.setId(idHistory);
                imStrukturJabatanHistoryEntity.setStrukturJabatanId(imStrukturJabatanEntity.getStrukturJabatanId());
                imStrukturJabatanHistoryEntity.setPositionId(imStrukturJabatanEntity.getPositionId());
                imStrukturJabatanHistoryEntity.setBranchId(imStrukturJabatanEntity.getBranchId());
                imStrukturJabatanHistoryEntity.setParentId(imStrukturJabatanEntity.getParentId());
                imStrukturJabatanHistoryEntity.setLevel(imStrukturJabatanEntity.getLevel());
                imStrukturJabatanHistoryEntity.setFlag(imStrukturJabatanEntity.getFlag());
                imStrukturJabatanHistoryEntity.setAction(imStrukturJabatanEntity.getAction());
                imStrukturJabatanHistoryEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imStrukturJabatanHistoryEntity.setLastUpdate(bean.getLastUpdate());
                imStrukturJabatanHistoryEntity.setCreatedWho(imStrukturJabatanEntity.getCreatedWho());
                imStrukturJabatanHistoryEntity.setCreatedDate(imStrukturJabatanEntity.getCreatedDate());

                imStrukturJabatanEntity.setStrukturJabatanId(bean.getStrukturJabatanId());
                imStrukturJabatanEntity.setPositionId(bean.getPositionId());
                imStrukturJabatanEntity.setBranchId(bean.getBranchId());
                imStrukturJabatanEntity.setParentId(parts[0]);
                imStrukturJabatanEntity.setLevel(Long.parseLong(parts[1]) + 1);
                imStrukturJabatanEntity.setFlag(bean.getFlag());
                imStrukturJabatanEntity.setAction(bean.getAction());
                imStrukturJabatanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imStrukturJabatanEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    strukturJabatanDao.updateAndSave(imStrukturJabatanEntity);
                    strukturJabatanDao.addAndSaveHistory(imStrukturJabatanHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[StrukturJabatanBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data StrukturJabatan, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[StrukturJabatanBoImpl.saveEdit] Error, not found data StrukturJabatan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data StrukturJabatan with request id, please check again your data ...");
//                condition = "Error, not found data StrukturJabatan with request id, please check again your data ...";
            }
        }
        logger.info("[StrukturJabatanBoImpl.saveEdit] end process <<<");
    }

    @Override
    public StrukturJabatan getDataStrukturJab(String id) throws GeneralBOException {
        ImStrukturJabatanEntity strukturJabatan = new ImStrukturJabatanEntity();
        StrukturJabatan strukturJabatan1 = new StrukturJabatan();

        strukturJabatan = strukturJabatanDao.getById("strukturJabatanId", id);
        if(strukturJabatan.getStrukturJabatanId() != null){
            strukturJabatan1.setStrukturJabatanId(strukturJabatan.getStrukturJabatanId());
            strukturJabatan1.setParentId(strukturJabatan.getParentId());
            strukturJabatan1.setLevel(strukturJabatan.getLevel());
            strukturJabatan1.setBranchId(strukturJabatan.getBranchId());
            strukturJabatan1.setPositionId(strukturJabatan.getPositionId());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String createDate = dateFormat.format(strukturJabatan.getCreatedDate());
//            strukturJabatan1.setCreatedDate(createDate);
            strukturJabatan1.setcDate(createDate);
            strukturJabatan1.setCreatedWho(strukturJabatan.getCreatedWho());
            String lastUpdate = dateFormat.format(strukturJabatan.getLastUpdate());
            strukturJabatan1.setlUpdate(lastUpdate);
//            strukturJabatan1.setLastUpdate(strukturJabatan.getLastUpdate());
            strukturJabatan1.setLastUpdateWho(strukturJabatan.getLastUpdateWho());
        }

        return strukturJabatan1;
    }

    @Override
    public StrukturJabatan saveAdd(StrukturJabatan bean) throws GeneralBOException {
        logger.info("[StrukturJabatanBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String status = cekStatus(bean.getBranchId(),bean.getPositionId());
            if (!status.equalsIgnoreCase("Exist")){
                String strukturJabatanId;
                try {
                    // Generating ID, get from postgre sequence
                    strukturJabatanId = strukturJabatanDao.getNextStrukturJabatanId();
                } catch (HibernateException e) {
                    logger.error("[StrukturJabatanBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence strukturJabatanId id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImStrukturJabatanEntity imStrukturJabatanEntity = new ImStrukturJabatanEntity();

                imStrukturJabatanEntity.setStrukturJabatanId(strukturJabatanId);
                imStrukturJabatanEntity.setPositionId(bean.getPositionId());
                imStrukturJabatanEntity.setBranchId(bean.getBranchId());
                if (bean.getParentId()!=null){
                    String strParent[] = bean.getParentId().split("-");
                    if(bean.getParentId() == null){
                        imStrukturJabatanEntity.setParentId(strukturJabatanId);
                        imStrukturJabatanEntity.setLevel(Long.parseLong("1"));
                    }else{
                        imStrukturJabatanEntity.setLevel(bean.getLevel() + 1);
                        imStrukturJabatanEntity.setParentId(strParent[0]);
                    }
                }else{
                    imStrukturJabatanEntity.setParentId("-");
                    imStrukturJabatanEntity.setLevel(Long.valueOf(0));
                }
                imStrukturJabatanEntity.setFlag(bean.getFlag());
                imStrukturJabatanEntity.setAction(bean.getAction());
                imStrukturJabatanEntity.setCreatedWho(bean.getCreatedWho());
                imStrukturJabatanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imStrukturJabatanEntity.setCreatedDate(bean.getCreatedDate());
                imStrukturJabatanEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    strukturJabatanDao.addAndSave(imStrukturJabatanEntity);
                } catch (HibernateException e) {
                    logger.error("[StrukturJabatanBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data StrukturJabatan, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Posisi Tersebut Sudah Ada");
            }
        }

        logger.info("[StrukturJabatanBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<StrukturJabatan> getByCriteria(StrukturJabatan searchBean) throws GeneralBOException {
        logger.info("[StrukturJabatanBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<StrukturJabatan> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getStrukturJabatanId() != null && !"".equalsIgnoreCase(searchBean.getStrukturJabatanId())) {
                hsCriteria.put("struktur_jabatan_id", searchBean.getStrukturJabatanId());
            }
            if (searchBean.getPositionId() != null) {
                //hsCriteria.put("position_id", searchBean.getPositionId());
            }
            if (searchBean.getBranchId() != "") {
                hsCriteria.put("branch_id", searchBean.getBranchId());
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


            List<ImStrukturJabatanEntity> imStrukturJabatanEntity = null;
            try {

                imStrukturJabatanEntity = strukturJabatanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[StrukturJabatanBoImpl.getSearchStrukturJabatanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imStrukturJabatanEntity != null){
                StrukturJabatan returnStrukturJabatan;
                // Looping from dao to object and save in collection
                for(ImStrukturJabatanEntity strukturJabatanEntity : imStrukturJabatanEntity){
                    returnStrukturJabatan = new StrukturJabatan();
                    if(searchBean.getPositionId() != null){
                        if(searchBean.getPositionId().equals(strukturJabatanEntity.getPositionId())){
                            returnStrukturJabatan.setParentId("");
                            returnStrukturJabatan.setSubParent("");
                        }else{
                            returnStrukturJabatan.setParentId(strukturJabatanEntity.getParentId());
                        }
                    }

                    returnStrukturJabatan.setStrukturJabatanId(strukturJabatanEntity.getStrukturJabatanId());
                    returnStrukturJabatan.setPositionId(strukturJabatanEntity.getPositionId());
                    returnStrukturJabatan.setLevel(strukturJabatanEntity.getLevel());

                    returnStrukturJabatan.setBranchId(strukturJabatanEntity.getBranchId());
                    returnStrukturJabatan.setBranchName(strukturJabatanEntity.getImBranches().getBranchName());
                    returnStrukturJabatan.setPositionName(strukturJabatanEntity.getImPosition().getPositionName());

                    returnStrukturJabatan.setCreatedWho(strukturJabatanEntity.getCreatedWho());
                    returnStrukturJabatan.setCreatedDate(strukturJabatanEntity.getCreatedDate());
                    returnStrukturJabatan.setLastUpdate(strukturJabatanEntity.getLastUpdate());

                    returnStrukturJabatan.setAction(strukturJabatanEntity.getAction());
                    returnStrukturJabatan.setFlag(strukturJabatanEntity.getFlag());
                    listOfResult.add(returnStrukturJabatan);
                }
            }
        }
        logger.info("[StrukturJabatanBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<StrukturJabatan> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<StrukturJabatan> getComboStrukturJabatanWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<StrukturJabatan> listComboStrukturJabatan = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImStrukturJabatanEntity> listStrukturJabatan = null;
        try {
            listStrukturJabatan = strukturJabatanDao.getListStrukturJabatan(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listStrukturJabatan != null) {
            for (ImStrukturJabatanEntity imStrukturJabatanEntity : listStrukturJabatan) {
                StrukturJabatan itemComboStrukturJabatan = new StrukturJabatan();
                itemComboStrukturJabatan.setStrukturJabatanId(imStrukturJabatanEntity.getStrukturJabatanId());
                itemComboStrukturJabatan.setPositionName(imStrukturJabatanEntity.getImPosition().getPositionName());
                itemComboStrukturJabatan.setPositionId(imStrukturJabatanEntity.getPositionId());
                itemComboStrukturJabatan.setBranchId(imStrukturJabatanEntity.getBranchId());
                itemComboStrukturJabatan.setLevel(imStrukturJabatanEntity.getLevel());
                itemComboStrukturJabatan.setParentId(imStrukturJabatanEntity.getParentId());
                listComboStrukturJabatan.add(itemComboStrukturJabatan);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboStrukturJabatan;
    }

    private List<StrukturJabatan> strukturJabatanList = new ArrayList();
    private List<StrukturJabatan>strukturJabatans = new ArrayList<>();

    @Override
    public List<StrukturJabatan> getDataStrukturJab(String branchId, String positionId, String nip) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

//        String parentId = "";
//        List<ImStrukturJabatanEntity>listStrukturJabatan = null;
//        try {
//            listStrukturJabatan = strukturJabatanDao.getStrukturJabatanSearch(branchId, positionId, "", nip);
//        } catch (HibernateException e) {
//            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
//            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
//        }
//
//        if(!positionId.equalsIgnoreCase("") || !nip.equalsIgnoreCase("")){
//            if (listStrukturJabatan != null) {
//                for (ImStrukturJabatanEntity imStrukturJabatanEntity : listStrukturJabatan) {
//                    parentId = imStrukturJabatanEntity.getStrukturJabatanId();
//                }
//            }
//        }

        strukturJabatanList.clear();
        if(!positionId.equalsIgnoreCase("") && !nip.equalsIgnoreCase("")){
            getListStruktur(branchId, positionId, "", nip);
        }
        else if(!positionId.equalsIgnoreCase("")){
            getListStruktur(branchId, positionId, "", "");
        }else if(!nip.equalsIgnoreCase("")){
            getListStruktur(branchId, "", "", nip);
        }else{
            getListStruktur(branchId, "", "-", "");
        }

        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return strukturJabatanList;
    }

    //recursiv
    private String getListStruktur(String branchId, String positionId, String parentId, String nip){
        List<StrukturJabatan> strukturJabatans = null;
        String hasil = "";
        strukturJabatans = strukturJabatanDao.getStrukturJabatanSearch(branchId, positionId, parentId, nip);
        if(strukturJabatans.size() > 0){
            for(StrukturJabatan imStrukturJabatanEntity : strukturJabatans){
                StrukturJabatan itemComboStrukturJabatan = new StrukturJabatan();
                itemComboStrukturJabatan.setStrukturJabatanId(imStrukturJabatanEntity.getStrukturJabatanId());
                itemComboStrukturJabatan.setPositionName(imStrukturJabatanEntity.getPositionName());
                itemComboStrukturJabatan.setPositionId(imStrukturJabatanEntity.getPositionId());
                itemComboStrukturJabatan.setBranchId(imStrukturJabatanEntity.getBranchId());
                itemComboStrukturJabatan.setLevel(imStrukturJabatanEntity.getLevel());
                itemComboStrukturJabatan.setParentId(imStrukturJabatanEntity.getParentId());
                itemComboStrukturJabatan.setJenisPegawai(imStrukturJabatanEntity.getJenisPegawai());
                itemComboStrukturJabatan.setFlagDefault(imStrukturJabatanEntity.getFlagDefault());
                if(imStrukturJabatanEntity.getNip() == null){
                    itemComboStrukturJabatan.setNip("-");
                    itemComboStrukturJabatan.setName("-");
                }else{
                    itemComboStrukturJabatan.setNip(imStrukturJabatanEntity.getNip());
                    itemComboStrukturJabatan.setName(imStrukturJabatanEntity.getNamaPegawai());
                }

                //digunakan jika filter posisi ada, set nilai parent id supaya bisa dibaca oleh table
                if(!positionId.equalsIgnoreCase("")){
                    if(imStrukturJabatanEntity.getPositionId().equalsIgnoreCase(positionId)){
                        itemComboStrukturJabatan.setParentId("-");
                    }
                }else if(!nip.equalsIgnoreCase("")){
                    if(imStrukturJabatanEntity.getNip().equalsIgnoreCase(nip)){
                        itemComboStrukturJabatan.setParentId("-");
                    }
                }

                strukturJabatanList.add(itemComboStrukturJabatan);
                hasil = itemComboStrukturJabatan.getStrukturJabatanId();
                getListStruktur(branchId, "", hasil, "");
            }
        }
        return hasil;
    }

    @Override
    public List <StrukturJabatan> getPerBagianSys() throws GeneralBOException {
        List<ImStrukturJabatanEntity> imStrukturJabatanEntities  = strukturJabatanDao.getPerBagianDao();
        List<StrukturJabatan> strukturJabatans  = new ArrayList<>();
        if(imStrukturJabatanEntities.size() > 0){
            strukturJabatanList.clear();
            for(ImStrukturJabatanEntity imStrukturJabatanEntity : imStrukturJabatanEntities){

                StrukturJabatan itemComboStrukturJabatan = new StrukturJabatan();

                itemComboStrukturJabatan.setStrukturJabatanId(imStrukturJabatanEntity.getStrukturJabatanId());
                itemComboStrukturJabatan.setParentId(imStrukturJabatanEntity.getParentId());
                itemComboStrukturJabatan.setBranchId(imStrukturJabatanEntity.getBranchId());
                itemComboStrukturJabatan.setPositionId(imStrukturJabatanEntity.getPositionId());
                itemComboStrukturJabatan.setPositionName(imStrukturJabatanEntity.getPositionName());
                itemComboStrukturJabatan.setPositionKelompokId(imStrukturJabatanEntity.getKelompokId());
                itemComboStrukturJabatan.setNip(imStrukturJabatanEntity.getNip());
                itemComboStrukturJabatan.setName(imStrukturJabatanEntity.getNamaPegawai());

                getListStruktur(imStrukturJabatanEntity.getStrukturJabatanId());

                String bagian[] = itemComboStrukturJabatan.getPositionName().split(" ");
                String bagian1 = "Bagian ";

                for(int a = 0 ; a < bagian.length; a++){
                    if(bagian[a].equalsIgnoreCase("Bagian")){
                        for(int b = a ; b < bagian.length - 1; b++){
                            bagian1 += " " + bagian[b + 1];
                        }
                        if (("Bagian  Pemeriksa Non Ops").equalsIgnoreCase(bagian1)||("Bagian  Pemeriksa Operasional").equalsIgnoreCase(bagian1)){
                            bagian1="Bagian  SPI";
                        }else if (("Bagian  Agronomi").equalsIgnoreCase(bagian1)||("Bagian  Sosial Ekonomi").equalsIgnoreCase(bagian1)){
                            bagian1="Bagian  Tanaman";
                        }else if (("Bagian  Risk Management dan GCG").equalsIgnoreCase(bagian1)){
                            bagian1="Bagian  Sekretaris Perusahaan";
                        }
                        break;
                    }
                }

                itemComboStrukturJabatan.setBagian(bagian1);
                strukturJabatans.add(itemComboStrukturJabatan);

                boolean ada = false;
                for(StrukturJabatan strukturJabatan: strukturJabatanList){
                    for(StrukturJabatan strukturJabatan1 : strukturJabatans){
                        if(strukturJabatan.getNip().equalsIgnoreCase(strukturJabatan1.getNip())){
                            ada = true;
                            break;
                        }
                    }
                    if(ada == false){
                        strukturJabatan.setBagian(bagian1);
                        strukturJabatans.add(strukturJabatan);
                    }
                    ada = false;
                }
            }
        }
        List<StrukturJabatan> strukturJabatanList = getPerBagianSisa();
        List<StrukturJabatan> forBagian = new ArrayList<>();
        List<StrukturJabatan> forBagianFinal = new ArrayList<>();
        strukturJabatans.addAll(strukturJabatanList);
        List<StrukturJabatan> daftarBagian = getPerBagian();
        for (StrukturJabatan strukturJabatan : daftarBagian){
            for (StrukturJabatan finalBagian : strukturJabatans){
                if (strukturJabatan.getBagian().equalsIgnoreCase(finalBagian.getBagian())){
                    forBagian.add(finalBagian);
                }
            }
        }

        for (StrukturJabatan strukturJabatan:forBagian){
            if (("Staff Riset").equalsIgnoreCase(strukturJabatan.getPositionName())||("PKWT Pengembangan Umum & Usaha").equalsIgnoreCase(strukturJabatan.getPositionName())||("Staff Riset dan Pengembangan Usaha").equalsIgnoreCase(strukturJabatan.getPositionName())){
                strukturJabatan.setBagian("Bagian  Riset dan Pengembangan");
            }
            forBagianFinal.add(strukturJabatan);
        }

        return forBagianFinal;
    }
    @Override
    public List <StrukturJabatan> getPerBagianSisa() throws GeneralBOException {
        List<ImStrukturJabatanEntity> imStrukturJabatanEntities  = strukturJabatanDao.getAllStrukturJabatan();
        List<StrukturJabatan> strukturJabatans  = new ArrayList<>();
        if(imStrukturJabatanEntities.size() > 0){
            for (ImStrukturJabatanEntity imStrukturJabatanEntity:imStrukturJabatanEntities){
                StrukturJabatan strukturJabatan = new StrukturJabatan();
                strukturJabatan.setNip(imStrukturJabatanEntity.getNip());
                strukturJabatan.setStrukturJabatanId(imStrukturJabatanEntity.getStrukturJabatanId());
                strukturJabatan.setBranchId(imStrukturJabatanEntity.getBranchId());
                strukturJabatan.setPositionId(imStrukturJabatanEntity.getPositionId());
                strukturJabatan.setPositionName(imStrukturJabatanEntity.getPositionName());
                strukturJabatan.setName(imStrukturJabatanEntity.getNamaPegawai());
                if (imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("11")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("127")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("164")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("166")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("82")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("170")){
                    strukturJabatan.setBagian("Bagian  Sekretaris Perusahaan");
                    strukturJabatans.add(strukturJabatan);
                }else if (imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("5")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("137")){
                    strukturJabatan.setBagian("Bagian  Tanaman");
                    strukturJabatans.add(strukturJabatan);
                }else if (imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("6")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("29")){
                    strukturJabatan.setBagian("Bagian  Teknik");
                    strukturJabatans.add(strukturJabatan);
                }/*else if (imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("7")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("36")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("193")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("172")){
                    strukturJabatan.setBagian("Bagian  Riset dan Pengembangan");
                    strukturJabatans.add(strukturJabatan);
                }*/else if (imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("8")||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("189")){
                    strukturJabatan.setBagian("Bagian  Keuangan");
                    strukturJabatans.add(strukturJabatan);
                }else if (imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("9")){
                    strukturJabatan.setBagian("Bagian  SDM");
                    strukturJabatans.add(strukturJabatan);
                }else if (imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("10")){
                    strukturJabatan.setBagian("Bagian  SPI");
                    strukturJabatans.add(strukturJabatan);
                }
            }
        }
        //||imStrukturJabatanEntity.getPositionId().equalsIgnoreCase("168")
        return strukturJabatans;
    }
    private String getListStruktur(String id){
        List<ImStrukturJabatanEntity> imStrukturJabatanEntities = null;
        String hasil = "";
        imStrukturJabatanEntities = strukturJabatanDao.getIdStrukturJabatan(id);
        if(imStrukturJabatanEntities.size() > 0){
            for(ImStrukturJabatanEntity imStrukturJabatanEntity : imStrukturJabatanEntities){
                StrukturJabatan strukturJabatan1 = new StrukturJabatan();
                strukturJabatan1.setNip(imStrukturJabatanEntity.getNip());
                strukturJabatan1.setPositionName(imStrukturJabatanEntity.getPositionName());
                strukturJabatan1.setBagian(imStrukturJabatanEntity.getPositionName());

                hasil = imStrukturJabatanEntity.getStrukturJabatanId();
                strukturJabatanList.add(strukturJabatan1);
                if(imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL02") || imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL03") ||
                        imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL04") || imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL05") ||
                        imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL06") || imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL07")){
                    strukturJabatanList.add(strukturJabatan1);
                }
                getListStruktur(getListStruktur(hasil));
            }
        }
        return hasil;
    }
    @Override
    public List<StrukturJabatan> getPerBagianDropDown() throws GeneralBOException {
        List<ImPositionBagianEntity> positionBagianEntityList  = positionBagianDao.getComboPositionBagianKandir();
        List<StrukturJabatan> strukturJabatans  = new ArrayList<>();
        if(positionBagianEntityList.size() > 0){
            for(ImPositionBagianEntity imPositionBagianEntity : positionBagianEntityList){

                StrukturJabatan itemComboStrukturJabatan = new StrukturJabatan();
                itemComboStrukturJabatan.setBagian(imPositionBagianEntity.getBagianId());
                itemComboStrukturJabatan.setBagianName(imPositionBagianEntity.getBagianName());
                strukturJabatans.add(itemComboStrukturJabatan);
            }
        }
        return strukturJabatans;
    }
    @Override
    public List<StrukturJabatan> getPerBagianDropDownUnit() throws GeneralBOException {
        List<ImPositionBagianEntity> positionBagianEntityList  = positionBagianDao.getComboPositionBagian();
        List<StrukturJabatan> strukturJabatans  = new ArrayList<>();
        if(positionBagianEntityList.size() > 0){
            for(ImPositionBagianEntity imPositionBagianEntity : positionBagianEntityList){

                StrukturJabatan itemComboStrukturJabatan = new StrukturJabatan();
                itemComboStrukturJabatan.setBagian(imPositionBagianEntity.getBagianId());
                itemComboStrukturJabatan.setBagianName(imPositionBagianEntity.getBagianName());
                strukturJabatans.add(itemComboStrukturJabatan);
            }
        }
        return strukturJabatans;
    }
    @Override
    public List<StrukturJabatan> getPerBagian() throws GeneralBOException {
        List<ImStrukturJabatanEntity> imStrukturJabatanEntities  = strukturJabatanDao.getPerBagianDao();
        List<StrukturJabatan> strukturJabatans  = new ArrayList<>();
        List<StrukturJabatan> strukturJabatanFinal  = new ArrayList<>();
        if(imStrukturJabatanEntities.size() > 0){
            strukturJabatanList.clear();
            for(ImStrukturJabatanEntity imStrukturJabatanEntity : imStrukturJabatanEntities){

                StrukturJabatan itemComboStrukturJabatan = new StrukturJabatan();

                itemComboStrukturJabatan.setStrukturJabatanId(imStrukturJabatanEntity.getStrukturJabatanId());
                itemComboStrukturJabatan.setParentId(imStrukturJabatanEntity.getParentId());
                itemComboStrukturJabatan.setBranchId(imStrukturJabatanEntity.getBranchId());
                itemComboStrukturJabatan.setPositionId(imStrukturJabatanEntity.getPositionId());
                itemComboStrukturJabatan.setPositionName(imStrukturJabatanEntity.getPositionName());
                itemComboStrukturJabatan.setPositionKelompokId(imStrukturJabatanEntity.getKelompokId());
                itemComboStrukturJabatan.setNip(imStrukturJabatanEntity.getNip());
                itemComboStrukturJabatan.setName(imStrukturJabatanEntity.getNamaPegawai());

                String bagian[] = itemComboStrukturJabatan.getPositionName().split(" ");
                String bagian1 = "Bagian ";

                for(int a = 0 ; a < bagian.length; a++){
                    if(bagian[a].equalsIgnoreCase("Bagian")){
                        for(int b = a ; b < bagian.length - 1; b++){
                            bagian1 += " " + bagian[b + 1];
                        }
                        break;
                    }
                }
                itemComboStrukturJabatan.setBagian(bagian1);
                strukturJabatans.add(itemComboStrukturJabatan);
            }
            StrukturJabatan strukturJabatanAdd;
            strukturJabatanAdd = new StrukturJabatan();
            strukturJabatanAdd.setBagian("Bagian  Sekretaris Perusahaan");
            strukturJabatans.add(strukturJabatanAdd);
            strukturJabatanAdd = new StrukturJabatan();
            strukturJabatanAdd.setBagian("Bagian  SPI");
            strukturJabatans.add(strukturJabatanAdd);
            strukturJabatanAdd = new StrukturJabatan();
            strukturJabatanAdd.setBagian("Bagian  Tanaman");
            strukturJabatans.add(strukturJabatanAdd);
        }
        for (StrukturJabatan strukturJabatan:strukturJabatans){
            if (("Bagian  Risk Management dan GCG").equalsIgnoreCase(strukturJabatan.getBagian())||("Bagian  Agronomi").equalsIgnoreCase(strukturJabatan.getBagian())||("Bagian  Pemeriksa Non Ops").equalsIgnoreCase(strukturJabatan.getBagian())||("Bagian  Pemeriksa Operasional").equalsIgnoreCase(strukturJabatan.getBagian())||("Bagian  Sosial Ekonomi").equalsIgnoreCase(strukturJabatan.getBagian())){
            }else{
                strukturJabatanFinal.add(strukturJabatan);
            }
        }
        return strukturJabatanFinal;
    }
    public String cekStatus(String branchId, String golonganId)throws GeneralBOException{
        String status ="";
        List<ImStrukturJabatanEntity> skalaGajiEntity = new ArrayList<>();
        try {
            skalaGajiEntity = strukturJabatanDao.getListStruktur(branchId,golonganId);
        } catch (HibernateException e) {
            logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (skalaGajiEntity.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }
}