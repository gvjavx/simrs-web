package com.neurix.hris.transaksi.medicalKacamata.bo.impl;

import com.neurix.common.exception.GeneralBOException;

import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.positionBagian.dao.PositionBagianDao;

import com.neurix.hris.transaksi.medicalKacamata.bo.MedicalKacamataBo;
import com.neurix.hris.transaksi.medicalKacamata.dao.MedicalKacamataDao;
import com.neurix.hris.transaksi.medicalKacamata.model.ItMedicalKacamataEntity;
import com.neurix.hris.transaksi.medicalKacamata.model.MedicalKacamata;
import com.neurix.hris.transaksi.medicalrecord.model.ItHrisMedicalRecordEntity;
import com.neurix.hris.transaksi.smk.model.ImtHistorySmkGolonganEntity;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class MedicalKacamataBoImpl implements MedicalKacamataBo {

    protected static transient Logger logger = Logger.getLogger(MedicalKacamataBoImpl.class);
    private MedicalKacamataDao medicalKacamataDao;
    private BiodataDao biodataDao;
    private PositionBagianDao positionBagianDao;


    public MedicalKacamataDao getMedicalKacamataDao() {
        return medicalKacamataDao;
    }

    public void setMedicalKacamataDao(MedicalKacamataDao medicalKacamataDao) {
        this.medicalKacamataDao = medicalKacamataDao;
    }

    public PositionBagianDao getPositionBagianDao() {
        return positionBagianDao;
    }

    public void setPositionBagianDao(PositionBagianDao positionBagianDao) {
        this.positionBagianDao = positionBagianDao;
    }

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
        MedicalKacamataBoImpl.logger = logger;
    }

    public MedicalKacamataDao getmedicalKacamataDao() {
        return medicalKacamataDao;
    }

    public void setmedicalKacamataDao(MedicalKacamataDao medicalKacamataDao) {
        this.medicalKacamataDao = medicalKacamataDao;
    }

    @Override
    public void saveDelete(MedicalKacamata bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        /*if (bean!=null) {

            String golonganId = bean.getUpdateGolonganId();

            ItMedicalKacamataEntity imGolonganEntity = null;

            try {
                // Get data from database by ID
                imGolonganEntity = medicalKacamataDao.getById("golonganId", golonganId);
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imGolonganEntity != null) {

                // Modify from bean to entity serializable

                imGolonganEntity.setFlag(bean.getFlag());
                imGolonganEntity.setAction(bean.getAction());
                imGolonganEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imGolonganEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    medicalKacamataDao.updateAndSave(imGolonganEntity);
                } catch (HibernateException e) {
                    logger.error("[MedicalKacamataBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Golongan, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[MedicalKacamataBoImpl.saveDelete] Error, not found data Golongan with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Golongan with request id, please check again your data ...");

            }
        }*/
        logger.info("[MedicalKacamataBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(MedicalKacamata bean) throws GeneralBOException {
        logger.info("[MedicalKacamataBoImpl.saveEdit] start process >>>");
        logger.info("[MedicalKacamataBoImpl.saveEdit] end process <<<");
    }

    @Override
    public MedicalKacamata saveAdd(MedicalKacamata bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<MedicalKacamata> getByCriteria(MedicalKacamata searchBean) throws GeneralBOException {
        logger.info("[MedicalKacamataBoImpl.getByCriteria] start process >>>");
        // Mapping with collection and put
        List<MedicalKacamata> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getMedicalKacamataId() != null && !"".equalsIgnoreCase(searchBean.getMedicalKacamataId())) {
                hsCriteria.put("kacamata_id", searchBean.getMedicalKacamataId());
            }

            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }

            if (searchBean.getBidangId() != null && !"".equalsIgnoreCase(searchBean.getBidangId())) {
                hsCriteria.put("bidang_id", searchBean.getBidangId());
            }

            if (searchBean.getBagianId() != null && !"".equalsIgnoreCase(searchBean.getBagianId())) {
                hsCriteria.put("bagian_id", searchBean.getBagianId());
            }

            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                hsCriteria.put("flag", searchBean.getFlag());
            }


            List<ItMedicalKacamataEntity> imGolonganEntity = null;
            try {

                imGolonganEntity = medicalKacamataDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[MedicalKacamataBoImpl.getSearchGolonganByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imGolonganEntity != null){
                MedicalKacamata medicalKacamata;
                // Looping from dao to object and save in collection
                for(ItMedicalKacamataEntity golonganEntity : imGolonganEntity){
                    medicalKacamata = new MedicalKacamata();
                    medicalKacamata.setMedicalKacamataId(golonganEntity.getMedicalKacamataId());

                    medicalKacamata.setBranchName(golonganEntity.getImBranches().getBranchName());

                    if(golonganEntity.getImDepartmentEntity() != null){
                        medicalKacamata.setBidangName(golonganEntity.getImDepartmentEntity().getDepartmentName());
                    }else{
                        medicalKacamata.setBidangName("-");
                    }

                    if(golonganEntity.getImPositionBagianEntity() != null){
                        medicalKacamata.setBagianName(golonganEntity.getImPositionBagianEntity().getBagianName());
                    }else{
                        medicalKacamata.setBagianName("-");
                    }

                    medicalKacamata.setPositionName(golonganEntity.getImPosition().getPositionName());

                    if(golonganEntity.getTipePembayaran().equalsIgnoreCase("Set")){
                        medicalKacamata.setTipePembayaran("1 Set (Gagang & Lensa");
                    }else{
                        medicalKacamata.setTipePembayaran(golonganEntity.getTipePembayaran());
                    }

                    medicalKacamata.setStrTanggalPembayaran(CommonUtil.convertDateToString(golonganEntity.getTanggalPembayaran()));
                    medicalKacamata.setStrTanggalBerakhir(CommonUtil.convertDateToString(golonganEntity.getTanggalBerakhir()));

                    medicalKacamata.setStrBiaya(CommonUtil.numbericFormat(golonganEntity.getBiaya(), "###,###"));

                    medicalKacamata.setCreatedWho(golonganEntity.getCreatedWho());
                    medicalKacamata.setCreatedDate(golonganEntity.getCreatedDate());
                    medicalKacamata.setLastUpdate(golonganEntity.getLastUpdate());
                    medicalKacamata.setAction(golonganEntity.getAction());
                    medicalKacamata.setFlag(golonganEntity.getFlag());

                    listOfResult.add(medicalKacamata);
                }
            }
        }
        logger.info("[MedicalKacamataBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public void saveAddData(MedicalKacamata medicalKacamata) throws GeneralBOException {
        logger.info("[UpdateMedicalKacamataBoImpl.saveAdd] start process >>>");

        if (medicalKacamata!=null) {

            String medicalKacamataId;
            try {
                // Generating ID, get from postgre sequence
                medicalKacamataId = medicalKacamataDao.getNextKacamata();
            } catch (HibernateException e) {
                logger.error("[MedicalKacamataBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence golonganId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ItMedicalKacamataEntity itMedicalKacamataEntity = new ItMedicalKacamataEntity();

            itMedicalKacamataEntity.setMedicalKacamataId(medicalKacamataId);
            itMedicalKacamataEntity.setNip(medicalKacamata.getNip());
            itMedicalKacamataEntity.setBranchId(medicalKacamata.getBranchId());
            itMedicalKacamataEntity.setBidangId(medicalKacamata.getBidangId());
            itMedicalKacamataEntity.setBagianId(medicalKacamata.getBagianId());
            itMedicalKacamataEntity.setPositionId(medicalKacamata.getPositionId());
            itMedicalKacamataEntity.setGolonganId(medicalKacamata.getGolonganId());
            itMedicalKacamataEntity.setTipePembayaran(medicalKacamata.getTipePembayaran());
            itMedicalKacamataEntity.setTanggalPembayaran(medicalKacamata.getTanggalPembayaran());
            itMedicalKacamataEntity.setBiaya(medicalKacamata.getBiaya());
            itMedicalKacamataEntity.setTanggalBerakhir(medicalKacamata.getTanggalPembayaran());


            if(medicalKacamata.getTipePembayaran().equalsIgnoreCase("Set") || medicalKacamata.getTipePembayaran().equalsIgnoreCase("Gagang")){

                itMedicalKacamataEntity.setTanggalBerakhir(CommonUtil.addYear(medicalKacamata.getTanggalPembayaran(), 2));

            }

            itMedicalKacamataEntity.setFlag(medicalKacamata.getFlag());
            itMedicalKacamataEntity.setAction(medicalKacamata.getAction());
            itMedicalKacamataEntity.setCreatedWho(medicalKacamata.getCreatedWho());
            itMedicalKacamataEntity.setLastUpdateWho(medicalKacamata.getLastUpdateWho());
            itMedicalKacamataEntity.setCreatedDate(medicalKacamata.getCreatedDate());
            itMedicalKacamataEntity.setLastUpdate(medicalKacamata.getLastUpdate());

            try {
                // insert into database
                medicalKacamataDao.addAndSave(itMedicalKacamataEntity);
            } catch (HibernateException e) {
                logger.error("[MedicalKacamataBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Golongan, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[UpdateMedicalKacamataBoImpl.saveAdd] end process <<<");
    }

    @Override
    public void saveEditData(MedicalKacamata medicalKacamata) throws GeneralBOException {
        logger.info("[UpdateMedicalKacamataBoImpl.saveAdd] start process >>>");

        ItMedicalKacamataEntity itMedicalKacamataEntity = new ItMedicalKacamataEntity();
        if (medicalKacamata!=null) {

            try {
                // Generating ID, get from postgre sequence
                itMedicalKacamataEntity = medicalKacamataDao.getById("medicalKacamataId", medicalKacamata.getMedicalKacamataId());
            } catch (HibernateException e) {
                logger.error("[MedicalKacamataBoImpl.saveEditData] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence golonganId id, please info to your admin..." + e.getMessage());
            }

            if(itMedicalKacamataEntity != null){
                itMedicalKacamataEntity.setTipePembayaran(medicalKacamata.getTipePembayaran());
                itMedicalKacamataEntity.setTanggalPembayaran(medicalKacamata.getTanggalPembayaran());
                itMedicalKacamataEntity.setBiaya(medicalKacamata.getBiaya());
                itMedicalKacamataEntity.setTanggalBerakhir(medicalKacamata.getTanggalPembayaran());

                itMedicalKacamataEntity.setAction("U");
                itMedicalKacamataEntity.setLastUpdateWho(medicalKacamata.getLastUpdateWho());
                itMedicalKacamataEntity.setLastUpdate(medicalKacamata.getLastUpdate());
            }

            if(medicalKacamata.getTipePembayaran().equalsIgnoreCase("Set") || medicalKacamata.getTipePembayaran().equalsIgnoreCase("Gagang")){
                itMedicalKacamataEntity.setTanggalBerakhir(CommonUtil.addYear(medicalKacamata.getTanggalPembayaran(), 2));
            }

            try {
                // insert into database
                medicalKacamataDao.updateAndSave(itMedicalKacamataEntity);
            } catch (HibernateException e) {
                logger.error("[MedicalKacamataBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Golongan, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[MedicalKacamataBoImpl.saveEditData] end process <<<");
    }

    @Override
    public void saveDeleteKacamata(String id) throws GeneralBOException {
        ItMedicalKacamataEntity itMedicalKacamataEntity = new ItMedicalKacamataEntity();
        try {
            itMedicalKacamataEntity = medicalKacamataDao.getById("medicalKacamataId", id);
        } catch (HibernateException e) {
            logger.error("[MedicalKacamataBoImpl.saveEditData] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when getting sequence golonganId id, please info to your admin..." + e.getMessage());
        }

        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        itMedicalKacamataEntity.setLastUpdateWho(CommonUtil.userLogin());
        itMedicalKacamataEntity.setLastUpdate(updateTime);
        itMedicalKacamataEntity.setFlag("N");
        itMedicalKacamataEntity.setAction("D");

        try {
            medicalKacamataDao.updateAndSave(itMedicalKacamataEntity);
        } catch (HibernateException e) {
            logger.error("[MedicalKacamataBoImpl.saveAdd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving new data Golongan, please info to your admin..." + e.getMessage());
        }
    }

    @Override
    public String cekMedical(String nip) throws GeneralBOException {
        String hasil = "";
        try {
            List<ItMedicalKacamataEntity> medicalKacamataList = new ArrayList<>();
            medicalKacamataList = medicalKacamataDao.getListMedical(nip);
            if(medicalKacamataList.size() > 0){
                for(ItMedicalKacamataEntity biayaLoop: medicalKacamataList){
                    Date date = new Date();

                    if(date.compareTo(biayaLoop.getTanggalBerakhir()) >= 0){
                        hasil = "";
                    }else{
                        hasil = "Maaf proses tidak dapat dilanjutkan, karena karyawan tersebut sudah pernah mengajukan penggantian 1 set kacamata / gagang pada tanggal " +
                                CommonUtil.convertDateToString(biayaLoop.getTanggalPembayaran())  + " dan dapat doproses lagi pada tanggal " +
                                CommonUtil.convertDateToString(biayaLoop.getTanggalBerakhir());
                    }
                }
            }

        } catch (HibernateException e) {
            logger.error("[MedicalKacamataBoImpl.saveAdd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when getting sequence golonganId id, please info to your admin..." + e.getMessage());
        }

        return hasil;
    }

    @Override
    public String cekMedical(String id, String nip) throws GeneralBOException {
        String hasil = "";
        try {
            List<ItMedicalKacamataEntity> medicalKacamataList = new ArrayList<>();
            medicalKacamataList = medicalKacamataDao.getListMedical(id, nip);
            if(medicalKacamataList.size() > 0){
                for(ItMedicalKacamataEntity biayaLoop: medicalKacamataList){
                    Date date = new Date();

                    if(date.compareTo(biayaLoop.getTanggalBerakhir()) >= 0){
                        hasil = "";
                    }else{
                        hasil = "Maaf proses tidak dapat dilanjutkan, karena karyawan tersebut sudah pernah mengajukan penggantian 1 set kacamata / gagang pada tanggal " +
                                CommonUtil.convertDateToString(biayaLoop.getTanggalPembayaran())  + " dan dapat doproses lagi pada tanggal " +
                                CommonUtil.convertDateToString(biayaLoop.getTanggalBerakhir());
                    }
                }
            }

        } catch (HibernateException e) {
            logger.error("[MedicalKacamataBoImpl.saveAdd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when getting sequence golonganId id, please info to your admin..." + e.getMessage());
        }

        return hasil;
    }

    @Override
    public MedicalKacamata getDataKacamataById(String kacamataId) throws GeneralBOException {
        MedicalKacamata medicalKacamata = new MedicalKacamata();
        ItMedicalKacamataEntity itMedicalKacamataEntity = new ItMedicalKacamataEntity();

        itMedicalKacamataEntity = medicalKacamataDao.getById("medicalKacamataId", kacamataId);
        if(itMedicalKacamataEntity != null){
            medicalKacamata.setMedicalKacamataId(itMedicalKacamataEntity.getMedicalKacamataId());
            medicalKacamata.setNip(itMedicalKacamataEntity.getNip());
            medicalKacamata.setNamaPegawai(itMedicalKacamataEntity.getImBiodataEntity().getNamaPegawai());
            medicalKacamata.setBranchId(itMedicalKacamataEntity.getBranchId());
            medicalKacamata.setBidangId(itMedicalKacamataEntity.getBidangId());
            medicalKacamata.setBagianId(itMedicalKacamataEntity.getBagianId());
            medicalKacamata.setPositionId(itMedicalKacamataEntity.getPositionId());
            medicalKacamata.setGolonganId(itMedicalKacamataEntity.getGolonganId());

            if(itMedicalKacamataEntity.getTanggalPembayaran() != null){
                medicalKacamata.setStrTanggalPembayaran(CommonUtil.convertDateToString(itMedicalKacamataEntity.getTanggalPembayaran()));
            }else{
                medicalKacamata.setStrTanggalPembayaran("");
            }

            if(itMedicalKacamataEntity.getTanggalBerakhir() != null){
                medicalKacamata.setStrTanggalBerakhir(CommonUtil.convertDateToString(itMedicalKacamataEntity.getTanggalBerakhir()));
            }else{
                medicalKacamata.setStrTanggalBerakhir("");
            }

            medicalKacamata.setTipePembayaran(itMedicalKacamataEntity.getTipePembayaran());
            medicalKacamata.setBiaya(itMedicalKacamataEntity.getBiaya());
        }

        return medicalKacamata;
    }

    @Override
    public List<MedicalKacamata> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

}
