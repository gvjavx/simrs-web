package com.neurix.hris.transaksi.absensi.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.absensi.bo.MesinAbsensiDetailBo;
import com.neurix.hris.transaksi.absensi.dao.MesinAbsensiDetailDao;
import com.neurix.hris.transaksi.absensi.model.MesinAbsensiDetail;
import com.neurix.hris.transaksi.absensi.model.MesinAbsensiDetailEntity;
//import com.neurix.hris.transaksi.absensi.model.ItMesinAbsensiDetailHistoryEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.Timestamp;
import java.util.*;
import java.util.zip.DataFormatException;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class MesinAbsensiDetailBoImpl implements MesinAbsensiDetailBo {

    protected static transient Logger logger = Logger.getLogger(MesinAbsensiDetailBoImpl.class);
    private MesinAbsensiDetailDao mesinAbsensiDetailDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MesinAbsensiDetailBoImpl.logger = logger;
    }

    public MesinAbsensiDetailDao getMesinAbsensiDetailDao() {
        return mesinAbsensiDetailDao;
    }

    public void setMesinAbsensiDetailDao(MesinAbsensiDetailDao mesinAbsensiDetailDao) {
        this.mesinAbsensiDetailDao = mesinAbsensiDetailDao;
    }

    @Override
    public void saveDelete(MesinAbsensiDetail bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String mesinAbsensiDetailId = bean.getMesinAbsensiDetailId();

            MesinAbsensiDetailEntity itMesinAbsensiDetailEntity = null;
            try {
                // Get data from database by ID
                itMesinAbsensiDetailEntity = mesinAbsensiDetailDao.getById("mesinAbsensiDetailId", mesinAbsensiDetailId);
            } catch (HibernateException e) {
                logger.error("[MesinAbsensiDetailBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (itMesinAbsensiDetailEntity != null) {

                // Modify from bean to entity serializable
                itMesinAbsensiDetailEntity.setMesinAbsensiDetailId(bean.getMesinAbsensiDetailId());
                itMesinAbsensiDetailEntity.setPin(bean.getPin());
                itMesinAbsensiDetailEntity.setStatus(bean.getStatus());
                itMesinAbsensiDetailEntity.setScanDate(bean.getScanDate());
                itMesinAbsensiDetailEntity.setVerifyMode(bean.getVerifyMode());
                itMesinAbsensiDetailEntity.setWorkCode(bean.getWorkCode());

                itMesinAbsensiDetailEntity.setFlag(bean.getFlag());
                itMesinAbsensiDetailEntity.setAction(bean.getAction());
                itMesinAbsensiDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itMesinAbsensiDetailEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    mesinAbsensiDetailDao.updateAndSave(itMesinAbsensiDetailEntity);
                } catch (HibernateException e) {
                    logger.error("[MesinAbsensiDetailBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data MesinAbsensiDetail, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[MesinAbsensiDetailBoImpl.saveDelete] Error, not found data MesinAbsensiDetail with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data MesinAbsensiDetail with request id, please check again your data ...");

            }
        }
        logger.info("[MesinAbsensiDetailBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(MesinAbsensiDetail bean) throws GeneralBOException {
        logger.info("[MesinAbsensiDetailBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {

            String mesinAbsensiDetailId = bean.getMesinAbsensiDetailId();

            MesinAbsensiDetailEntity itMesinAbsensiDetailEntity = null;
            String idHistory = "";
            try {
                // Get data from database by ID
                itMesinAbsensiDetailEntity = mesinAbsensiDetailDao.getById("mesinAbsensiDetailId", mesinAbsensiDetailId);
//                idHistory = mesinAbsensiDetailDao.getNextMesinAbsensiDetailHistoryId();
            } catch (HibernateException e) {
                logger.error("[MesinAbsensiDetailBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data MesinAbsensiDetail by Kode MesinAbsensiDetail, please inform to your admin...," + e.getMessage());
            }

            if (itMesinAbsensiDetailEntity != null) {
//                MesinAbsensiDetailEntity itMesinAbsensiDetailHistoryEntity = new ImMesinAbsensiDetailHistoryEntity();
//
//                imMesinAbsensiDetailHistoryEntity.setId(idHistory);
//                imMesinAbsensiDetailHistoryEntity.setMesinAbsensiDetailId(imMesinAbsensiDetailEntity.getMesinAbsensiDetailId());
//                imMesinAbsensiDetailHistoryEntity.setMesinAbsensiDetailName(imMesinAbsensiDetailEntity.getMesinAbsensiDetailName());
//                imMesinAbsensiDetailHistoryEntity.setJumlahMesinAbsensiDetail(imMesinAbsensiDetailEntity.getJumlahMesinAbsensiDetail());
//                imMesinAbsensiDetailHistoryEntity.setTipeHari(imMesinAbsensiDetailEntity.getTipeHari());
//                imMesinAbsensiDetailHistoryEntity.setGolonganId(imMesinAbsensiDetailEntity.getGolonganId());
//                imMesinAbsensiDetailHistoryEntity.setBranchId(imMesinAbsensiDetailEntity.getBranchId());
//
//                imMesinAbsensiDetailHistoryEntity.setFlag(imMesinAbsensiDetailEntity.getFlag());
//                imMesinAbsensiDetailHistoryEntity.setAction(imMesinAbsensiDetailEntity.getAction());
//                imMesinAbsensiDetailHistoryEntity.setLastUpdateWho(bean.getLastUpdateWho());
//                imMesinAbsensiDetailHistoryEntity.setLastUpdate(bean.getLastUpdate());
//                imMesinAbsensiDetailHistoryEntity.setCreatedWho(imMesinAbsensiDetailEntity.getLastUpdateWho());
//                imMesinAbsensiDetailHistoryEntity.setCreatedDate(imMesinAbsensiDetailEntity.getLastUpdate());

                itMesinAbsensiDetailEntity.setMesinAbsensiDetailId(bean.getMesinAbsensiDetailId());
                itMesinAbsensiDetailEntity.setPin(bean.getPin());
                itMesinAbsensiDetailEntity.setStatus(bean.getStatus());
                itMesinAbsensiDetailEntity.setScanDate(bean.getScanDate());
                itMesinAbsensiDetailEntity.setVerifyMode(bean.getVerifyMode());
                itMesinAbsensiDetailEntity.setWorkCode(bean.getWorkCode());

                itMesinAbsensiDetailEntity.setFlag(bean.getFlag());
                itMesinAbsensiDetailEntity.setAction(bean.getAction());
                itMesinAbsensiDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itMesinAbsensiDetailEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    mesinAbsensiDetailDao.updateAndSave(itMesinAbsensiDetailEntity);
//                    mesinAbsensiDetailDao.addAndSaveHistory(imMesinAbsensiDetailHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[MesinAbsensiDetailBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data MesinAbsensiDetail, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[MesinAbsensiDetailBoImpl.saveEdit] Error, not found data MesinAbsensiDetail with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data MesinAbsensiDetail with request id, please check again your data ...");
//                condition = "Error, not found data MesinAbsensiDetail with request id, please check again your data ...";
            }
        }
        logger.info("[MesinAbsensiDetailBoImpl.saveEdit] end process <<<");
    }

    @Override
    public MesinAbsensiDetail saveAdd(MesinAbsensiDetail bean) throws GeneralBOException {
        logger.info("[MesinAbsensiDetailBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String mesinAbsensiDetailId;
            try {
                // Generating ID, get from postgre sequence
                mesinAbsensiDetailId = mesinAbsensiDetailDao.getNextMesinAbsensiDetailId();
            } catch (HibernateException e) {
                logger.error("[MesinAbsensiDetailBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence mesinAbsensiDetailId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            MesinAbsensiDetailEntity itMesinAbsensiDetailEntity = new MesinAbsensiDetailEntity();

            itMesinAbsensiDetailEntity.setMesinAbsensiDetailId(mesinAbsensiDetailId);
            itMesinAbsensiDetailEntity.setPin(bean.getPin());
            itMesinAbsensiDetailEntity.setStatus(bean.getStatus());
            Date scanDate = CommonUtil.convertStringToDate(bean.getStScanDate());
            String scanDateFinal = scanDate+" "+bean.getJam()+":00.0";



            itMesinAbsensiDetailEntity.setScanDate(Timestamp.valueOf(scanDateFinal));
            itMesinAbsensiDetailEntity.setVerifyMode(bean.getVerifyMode());
            itMesinAbsensiDetailEntity.setWorkCode(bean.getWorkCode());


//            imMesinAbsensiDetailEntity.setMesinAbsensiDetailName(bean.getMesinAbsensiDetailName());
//            imMesinAbsensiDetailEntity.setJumlahMesinAbsensiDetail(bean.getJumlahMesinAbsensiDetail());
//            imMesinAbsensiDetailEntity.setTipeHari(bean.getTipeHari());
            itMesinAbsensiDetailEntity.setBranchId(bean.getBranchId());
//            imMesinAbsensiDetailEntity.setGolonganId(bean.getGolonganId());
            itMesinAbsensiDetailEntity.setFlag(bean.getFlag());
            itMesinAbsensiDetailEntity.setAction(bean.getAction());
            itMesinAbsensiDetailEntity.setCreatedWho(bean.getCreatedWho());
            itMesinAbsensiDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itMesinAbsensiDetailEntity.setCreatedDate(bean.getCreatedDate());
            itMesinAbsensiDetailEntity.setLastUpdate(bean.getLastUpdate());
            //SYAMS 6SEP21 => Tambah keterangan
            itMesinAbsensiDetailEntity.setKeterangan(bean.getKeterangan());

            try {
                // insert into database
                mesinAbsensiDetailDao.addAndSave(itMesinAbsensiDetailEntity);
            } catch (HibernateException e) {
                logger.error("[MesinAbsensiDetailBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data MesinAbsensiDetail, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[MesinAbsensiDetailBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<MesinAbsensiDetail> getByCriteria(MesinAbsensiDetail searchBean) throws GeneralBOException {
        logger.info("[MesinAbsensiDetailBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<MesinAbsensiDetail> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getMesinAbsensiDetailId() != null && !"".equalsIgnoreCase(searchBean.getMesinAbsensiDetailId())) {
                hsCriteria.put("mesin_Absensi_Detail_id", searchBean.getMesinAbsensiDetailId());
            }
            if (searchBean.getPin() != null && !"".equalsIgnoreCase(searchBean.getPin())) {
                hsCriteria.put("pin", searchBean.getPin());
            }

            if (searchBean.getStatus() != null && !"".equalsIgnoreCase(searchBean.getStatus())) {
                hsCriteria.put("status", searchBean.getStatus());
            }
            if (searchBean.getTanggalDari() != null) {
                hsCriteria.put("tanggal_dari", searchBean.getTanggalDari());
            }
            if (searchBean.getTanggalSelesai() != null) {
                hsCriteria.put("tanggal_selesai", searchBean.getTanggalSelesai());
            }
            if (searchBean.getScanDate() != null) {
                hsCriteria.put("scan_date", searchBean.getScanDate());
            }

            if (searchBean.getVerifyMode() != null && !"".equalsIgnoreCase(searchBean.getVerifyMode())) {
                hsCriteria.put("verify_mode", searchBean.getVerifyMode());
            }
            if (searchBean.getWorkCode() != null && !"".equalsIgnoreCase(searchBean.getWorkCode())) {
                hsCriteria.put("work_code", searchBean.getWorkCode());
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


            List<MesinAbsensiDetailEntity> itMesinAbsensiDetailEntity = null;
            try {

                itMesinAbsensiDetailEntity = mesinAbsensiDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[MesinAbsensiDetailBoImpl.getSearchMesinAbsensiDetailByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itMesinAbsensiDetailEntity != null){
                MesinAbsensiDetail returnMesinAbsensiDetail;
                // Looping from dao to object and save in collection
                for(MesinAbsensiDetailEntity mesinAbsensiDetailEntity : itMesinAbsensiDetailEntity){
                    returnMesinAbsensiDetail = new MesinAbsensiDetail();
                    returnMesinAbsensiDetail.setMesinAbsensiDetailId(mesinAbsensiDetailEntity.getMesinAbsensiDetailId());
                    returnMesinAbsensiDetail.setPin(mesinAbsensiDetailEntity.getPin());
                    returnMesinAbsensiDetail.setStatus(mesinAbsensiDetailEntity.getStatus());
                    returnMesinAbsensiDetail.setScanDate(mesinAbsensiDetailEntity.getScanDate());
                    returnMesinAbsensiDetail.setVerifyMode(mesinAbsensiDetailEntity.getWorkCode());
                    returnMesinAbsensiDetail.setWorkCode(mesinAbsensiDetailEntity.getWorkCode());
                    returnMesinAbsensiDetail.setCreatedWho(mesinAbsensiDetailEntity.getCreatedWho());
                    returnMesinAbsensiDetail.setCreatedDate(mesinAbsensiDetailEntity.getCreatedDate());
                    returnMesinAbsensiDetail.setLastUpdate(mesinAbsensiDetailEntity.getLastUpdate());

                    returnMesinAbsensiDetail.setAction(mesinAbsensiDetailEntity.getAction());
                    returnMesinAbsensiDetail.setFlag(mesinAbsensiDetailEntity.getFlag());

                    listOfResult.add(returnMesinAbsensiDetail);
                }
            }
        }
        logger.info("[MesinAbsensiDetailBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<MesinAbsensiDetail> getByCriteriaMobile(MesinAbsensiDetail searchBean) throws GeneralBOException {
        logger.info("[MesinAbsensiDetailBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<MesinAbsensiDetail> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getMesinAbsensiDetailId() != null && !"".equalsIgnoreCase(searchBean.getMesinAbsensiDetailId())) {
                hsCriteria.put("mesin_Absensi_Detail_id", searchBean.getMesinAbsensiDetailId());
            }
            if (searchBean.getPin() != null && !"".equalsIgnoreCase(searchBean.getPin())) {
                hsCriteria.put("pin", searchBean.getPin());
            }

            if (searchBean.getStatus() != null && !"".equalsIgnoreCase(searchBean.getStatus())) {
                hsCriteria.put("status", searchBean.getStatus());
            }
            if (searchBean.getTanggalDari() != null) {
                hsCriteria.put("tanggal_dari", searchBean.getTanggalDari());
            }
//            if (searchBean.getTanggalSelesai() != null) {
//                hsCriteria.put("tanggal_selesai", searchBean.getTanggalSelesai());
//            }
//            if (searchBean.getScanDate() != null) {
//                hsCriteria.put("scan_date", searchBean.getScanDate());
//            }

            if (searchBean.getVerifyMode() != null && !"".equalsIgnoreCase(searchBean.getVerifyMode())) {
                hsCriteria.put("verify_mode", searchBean.getVerifyMode());
            }
            if (searchBean.getWorkCode() != null && !"".equalsIgnoreCase(searchBean.getWorkCode())) {
                hsCriteria.put("work_code", searchBean.getWorkCode());
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


            List<MesinAbsensiDetailEntity> itMesinAbsensiDetailEntity = null;
            try {

                itMesinAbsensiDetailEntity = mesinAbsensiDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[MesinAbsensiDetailBoImpl.getSearchMesinAbsensiDetailByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itMesinAbsensiDetailEntity != null){
                MesinAbsensiDetail returnMesinAbsensiDetail;
                // Looping from dao to object and save in collection
                for(MesinAbsensiDetailEntity mesinAbsensiDetailEntity : itMesinAbsensiDetailEntity){
                    returnMesinAbsensiDetail = new MesinAbsensiDetail();
                    returnMesinAbsensiDetail.setMesinAbsensiDetailId(mesinAbsensiDetailEntity.getMesinAbsensiDetailId());
                    returnMesinAbsensiDetail.setPin(mesinAbsensiDetailEntity.getPin());
                    returnMesinAbsensiDetail.setStatus(mesinAbsensiDetailEntity.getStatus());
                    returnMesinAbsensiDetail.setScanDate(mesinAbsensiDetailEntity.getScanDate());
                    returnMesinAbsensiDetail.setVerifyMode(mesinAbsensiDetailEntity.getWorkCode());
                    returnMesinAbsensiDetail.setWorkCode(mesinAbsensiDetailEntity.getWorkCode());
//                    returnMesinAbsensiDetail.setMesinAbsensiDetailName(mesinAbsensiDetailEntity.getMesinAbsensiDetailName());
//                    returnMesinAbsensiDetail.setJumlahMesinAbsensiDetail(mesinAbsensiDetailEntity.getJumlahMesinAbsensiDetail());
//                    returnMesinAbsensiDetail.setTipeHari(mesinAbsensiDetailEntity.getTipeHari());
//                    returnMesinAbsensiDetail.setGolonganId(mesinAbsensiDetailEntity.getGolonganId());
//                    returnMesinAbsensiDetail.setBranchId(mesinAbsensiDetailEntity.getBranchId());
//                    if (mesinAbsensiDetailEntity.getBranchId().equalsIgnoreCase("")){
//                        returnMesinAbsensiDetail.setBranchName("");
//                    }
//                    else {
//                        returnMesinAbsensiDetail.setBranchName(mesinAbsensiDetailEntity.getImBranch().getBranchName());
//                    }
//                    if (mesinAbsensiDetailEntity.getGolonganId().equalsIgnoreCase("")){
//                        returnMesinAbsensiDetail.setGolonganName("");
//                    }
//                    else{
//                        returnMesinAbsensiDetail.setGolonganName(mesinAbsensiDetailEntity.getImGolongan().getGolonganName());
//                    }
//                    returnMesinAbsensiDetail.setBranchId(mesinAbsensiDetailEntity.getBranchId());

                    returnMesinAbsensiDetail.setCreatedWho(mesinAbsensiDetailEntity.getCreatedWho());
                    returnMesinAbsensiDetail.setCreatedDate(mesinAbsensiDetailEntity.getCreatedDate());
                    returnMesinAbsensiDetail.setLastUpdate(mesinAbsensiDetailEntity.getLastUpdate());

                    returnMesinAbsensiDetail.setAction(mesinAbsensiDetailEntity.getAction());
                    returnMesinAbsensiDetail.setFlag(mesinAbsensiDetailEntity.getFlag());
                    if (returnMesinAbsensiDetail.getScanDate().after(searchBean.getTanggalDari())) {
                        listOfResult.add(returnMesinAbsensiDetail);
                    }
                }
            }
        }
        logger.info("[MesinAbsensiDetailBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<MesinAbsensiDetail> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
