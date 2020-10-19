package com.neurix.hris.master.libur.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.libur.bo.LiburBo;
import com.neurix.hris.master.libur.dao.LiburDao;
import com.neurix.hris.master.libur.model.ImLiburEntity;
import com.neurix.hris.master.libur.model.ImLiburHistoryEntity;
import com.neurix.hris.master.libur.model.Libur;

import com.neurix.hris.master.tipelibur.bo.TipeLiburBo;
import com.neurix.hris.master.tipelibur.model.TipeLibur;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class LiburBoImpl implements LiburBo {

    protected static transient Logger logger = Logger.getLogger(LiburBoImpl.class);
    private LiburDao liburDao;


    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        LiburBoImpl.logger = logger;
    }

    public LiburDao getLiburDao() {
        return liburDao;
    }

    public void setLiburDao(LiburDao liburDao) {
        this.liburDao = liburDao;
    }

    @Override
    public void saveDelete(Libur bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String liburId = bean.getLiburId();
            ImLiburEntity imLiburEntity = null;
            ImLiburHistoryEntity historyEntity = new ImLiburHistoryEntity();
            String liburIdHistory = "";

            try {
                // Get data from database by ID
                imLiburEntity = liburDao.getById("liburId", liburId);
                liburIdHistory = liburDao.getNextLiburHistoryId();
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imLiburEntity != null) {
                historyEntity.setLiburHistoryId(liburIdHistory);
                historyEntity.setLiburId(imLiburEntity.getLiburId());
                historyEntity.setTanggal(imLiburEntity.getTanggal());
                historyEntity.setLiburTahun(imLiburEntity.getLiburTahun());
                historyEntity.setLiburKeterangan(imLiburEntity.getLiburKeterangan());
                historyEntity.setTipeLiburId(imLiburEntity.getTipeLiburId());
                historyEntity.setFlag(imLiburEntity.getFlag());
                historyEntity.setAction(imLiburEntity.getAction());
                historyEntity.setCreatedDate(imLiburEntity.getCreatedDate());
                historyEntity.setCreatedWho(imLiburEntity.getCreatedWho());
                historyEntity.setLastUpdate(imLiburEntity.getLastUpdate());
                historyEntity.setLastUpdateWho(imLiburEntity.getLastUpdateWho());

                // Modify from bean to entity serializable
                imLiburEntity.setLiburId(bean.getLiburId());
                imLiburEntity.setLiburTahun(bean.getLiburTahun());
                imLiburEntity.setLiburKeterangan(bean.getLiburKeterangan());
                imLiburEntity.setFlag(bean.getFlag());
                imLiburEntity.setAction(bean.getAction());
                imLiburEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imLiburEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    liburDao.updateAndSave(imLiburEntity);
                    liburDao.addAndSaveHistory(historyEntity);
                } catch (HibernateException e) {
                    logger.error("[AlatBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[AlatBoImpl.saveDelete] Error, not found data alat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data alat with request id, please check again your data ...");

            }
        }
        logger.info("[AlatBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Libur bean) throws GeneralBOException {
        logger.info("[LiburBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String liburId = bean.getLiburId();
            ImLiburEntity imLiburEntity = null;
            ImLiburHistoryEntity historyEntity = new ImLiburHistoryEntity();
            String liburIdHistory = "";

            try {
                // Get data from database by ID
                imLiburEntity = liburDao.getById("liburId", liburId);
                liburIdHistory = liburDao.getNextLiburHistoryId();
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imLiburEntity != null) {
                historyEntity.setLiburHistoryId(liburIdHistory);
                historyEntity.setLiburId(imLiburEntity.getLiburId());
                historyEntity.setTanggal(imLiburEntity.getTanggal());
                historyEntity.setLiburTahun(imLiburEntity.getLiburTahun());
                historyEntity.setLiburKeterangan(imLiburEntity.getLiburKeterangan());
                historyEntity.setTipeLiburId(imLiburEntity.getTipeLiburId());
                historyEntity.setFlag(imLiburEntity.getFlag());
                historyEntity.setAction(imLiburEntity.getAction());
                historyEntity.setCreatedDate(imLiburEntity.getCreatedDate());
                historyEntity.setCreatedWho(imLiburEntity.getCreatedWho());
                historyEntity.setLastUpdate(imLiburEntity.getLastUpdate());
                historyEntity.setLastUpdateWho(imLiburEntity.getLastUpdateWho());

                //
                imLiburEntity.setLiburId(bean.getLiburId());
                imLiburEntity.setLiburTahun(bean.getLiburTahun());
                imLiburEntity.setTanggal(bean.getTanggal());
                imLiburEntity.setLiburKeterangan(bean.getLiburKeterangan());
                imLiburEntity.setTipeLiburId(bean.getTipeLiburId());
                imLiburEntity.setLiburKeterangan(bean.getLiburKeterangan());
                imLiburEntity.setFlag(bean.getFlag());
                imLiburEntity.setAction(bean.getAction());
                imLiburEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imLiburEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    liburDao.updateAndSave(imLiburEntity);
                    liburDao.addAndSaveHistory(historyEntity);
//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[AlatBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[AlatBoImpl.saveEdit] Error, not found data alat with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data alat with request id, please check again your data ...");
//                condition = "Error, not found data alat with request id, please check again your data ...";
            }
        }
        logger.info("[AlatBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Libur saveAdd(Libur bean) throws GeneralBOException {
        logger.info("[LiburBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String liburId;
            try {
                // Generating ID, get from postgre sequence
                liburId = liburDao.getNextLiburId();
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImLiburEntity imLiburEntity = new ImLiburEntity();

            imLiburEntity.setLiburId(liburId);
            imLiburEntity.setLiburTahun(bean.getLiburTahun());
            imLiburEntity.setTipeLiburId(bean.getTipeLiburId());

            try{
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date parsedDate = dateFormat.parse(bean.getStTanggal());
                Timestamp timestamp = new Timestamp(parsedDate.getTime());
                imLiburEntity.setTanggal(timestamp);
            }catch (Exception e){
                throw new GeneralBOException("Found problem when saving new data libur, please info to your admin..." + e.getMessage());
            }

            imLiburEntity.setLiburKeterangan(bean.getLiburKeterangan());

            imLiburEntity.setFlag(bean.getFlag());
            imLiburEntity.setAction(bean.getAction());
            imLiburEntity.setCreatedWho(bean.getCreatedWho());
            imLiburEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imLiburEntity.setCreatedDate(bean.getCreatedDate());
            imLiburEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                liburDao.addAndSave(imLiburEntity);
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[AlatBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Libur> getByCriteria(Libur searchBean) throws GeneralBOException {
        logger.info("[LiburBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Libur> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getLiburId() != null && !"".equalsIgnoreCase(searchBean.getLiburId())) {
                hsCriteria.put("libur_id", searchBean.getLiburId());
            }
            if (searchBean.getLiburTahun() != null && !"".equalsIgnoreCase(searchBean.getLiburTahun())) {
                hsCriteria.put("tahun", searchBean.getLiburTahun());
            }
            if (searchBean.getTanggal() != null ) {
                hsCriteria.put("tanggal", searchBean.getTanggal());
            }
            if (searchBean.getTipeLiburId() != null && !"".equalsIgnoreCase(searchBean.getTipeLiburId())) {
                hsCriteria.put("tipe_libur_id", searchBean.getTipeLiburId());
            }
            if (searchBean.getLiburKeterangan() != null && !"".equalsIgnoreCase(searchBean.getLiburKeterangan())) {
                hsCriteria.put("keterangan", searchBean.getLiburKeterangan());
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
            if (searchBean.getTanggal() != null) {
                hsCriteria.put("tanggal", searchBean.getTanggal());
                hsCriteria.put("flag","Y");
            }

            List<ImLiburEntity> imLiburEntity = null;
            try {

                imLiburEntity = liburDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.getSearchAlatByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imLiburEntity != null){
                Libur returnLibur;
                // Looping from dao to object and save in collection
                for(ImLiburEntity liburEntity : imLiburEntity){
                    returnLibur = new Libur();
                    returnLibur.setLiburId(liburEntity.getLiburId());
                    returnLibur.setLiburTahun(liburEntity.getLiburTahun());
                    returnLibur.setTipeLiburId(liburEntity.getTipeLiburId());
                    returnLibur.setTanggal(liburEntity.getTanggal());
                    returnLibur.setCreatedWho(liburEntity.getCreatedWho());
                    returnLibur.setCreatedDate(liburEntity.getCreatedDate());
                    returnLibur.setLastUpdate(liburEntity.getLastUpdate());
                    returnLibur.setLiburKeterangan(liburEntity.getLiburKeterangan());
                    returnLibur.setAction(liburEntity.getAction());
                    returnLibur.setFlag(liburEntity.getFlag());
                    returnLibur.setStTanggal(CommonUtil.convertDateToString(liburEntity.getTanggal()));

                    ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                    if (liburEntity.getTipeLiburId() != null){
                        TipeLibur tipeLibur = new TipeLibur();
                        TipeLiburBo tipeLiburBo = (TipeLiburBo) context.getBean("tipeLiburBoProxy");
                        tipeLibur.setTipeLiburId(liburEntity.getTipeLiburId());
                        tipeLibur.setFlag("Y");
                        List<TipeLibur> tipeLiburs = tipeLiburBo.getByCriteria(tipeLibur);
                        if (tipeLiburs.size() > 0){
                            String tipeLiburName = tipeLiburs.get(0).getTipeLiburName();
                            returnLibur.setTipeLiburName(tipeLiburName);
                        }else {
                            String tipeLiburName = "-";
                            returnLibur.setTipeLiburName(tipeLiburName);
                        }
                    }
                    returnLibur.setStCreatedDate(liburEntity.getCreatedDate().toString());
                    returnLibur.setStLastUpdate(liburEntity.getLastUpdate().toString());

                    listOfResult.add(returnLibur);
                }
            }
        }
        logger.info("[AlatBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Libur> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    /*public List<Libur> getComboLiburWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Libur> listComboLibur = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImLiburEntity> listLibur = null;
        try {
            listLibur = liburDao.getListLibur(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listLibur != null) {
            for (ImLiburEntity imLiburEntity : listLibur) {
                Libur itemComboLibur = new Libur();
                itemComboLibur.setLiburId(imLiburEntity.getLiburId());
                itemComboLibur.setLiburTahun(imLiburEntity.getLiburTahun());
                listComboLibur.add(itemComboLibur);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboLibur;
    }*/

}
