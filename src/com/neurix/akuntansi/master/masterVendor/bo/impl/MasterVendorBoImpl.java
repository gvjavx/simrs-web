package com.neurix.akuntansi.master.masterVendor.bo.impl;

import com.neurix.akuntansi.master.masterVendor.bo.MasterVendorBo;
import com.neurix.akuntansi.master.masterVendor.dao.MasterVendorDao;
import com.neurix.akuntansi.master.masterVendor.dao.MasterVendorDao;
import com.neurix.akuntansi.master.masterVendor.model.ImMasterVendorEntity;
import com.neurix.akuntansi.master.masterVendor.model.MasterVendor;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.vendor.dao.VendorDao;
import com.neurix.simrs.master.vendor.model.ImSimrsVendorEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

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
public class MasterVendorBoImpl implements MasterVendorBo {

    protected static transient Logger logger = Logger.getLogger(MasterVendorBoImpl.class);
    private MasterVendorDao masterVendorDao;
    private VendorDao vendorDao;

    public VendorDao getVendorDao() {
        return vendorDao;
    }

    public void setVendorDao(VendorDao vendorDao) {
        this.vendorDao = vendorDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MasterVendorBoImpl.logger = logger;
    }

    public MasterVendorDao getMasterVendorDao() {
        return masterVendorDao;
    }

    public void setMasterVendorDao(MasterVendorDao masterVendorDao) {
        this.masterVendorDao = masterVendorDao;
    }

    @Override
    public void saveDelete(MasterVendor bean) throws GeneralBOException {
        logger.info("[VendorBoImpl.saveDelete] start process >>>");
        if (bean!=null) {

            //validasi apakah vendor sudah ada di jurnal
            Integer jumlah = 0;
            try {
                jumlah = masterVendorDao.cekExistingJurnalDetail(bean.getNomorMaster());
            } catch (HibernateException e) {
                logger.error("[VendorBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
            }
            if (jumlah>0){
                String status="ERROR : Kode vendor sudah ada pada jurnal";
                logger.error("[VendorBoImpl.saveDelete] "+status);
                throw new GeneralBOException(status);
            }

            //save
            ImMasterVendorEntity imVendorEntity = new ImMasterVendorEntity();
            try {
                // Get data from database by ID
                imVendorEntity = masterVendorDao.getById("nomorMaster", bean.getNomorMaster());
            } catch (HibernateException e) {
                logger.error("[VendorBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
            }

            if (imVendorEntity != null) {
                // Modify from bean to entity serializable
                imVendorEntity.setFlag(bean.getFlag());
                imVendorEntity.setAction(bean.getAction());
                imVendorEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imVendorEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    masterVendorDao.updateAndSave(imVendorEntity);
                } catch (HibernateException e) {
                    logger.error("[VendorBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Vendor, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[VendorBoImpl.saveDelete] Error, not found data Vendor with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Vendor with request id, please check again your data ...");

            }
        }
        logger.info("[VendorBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(MasterVendor bean) throws GeneralBOException {
        logger.info("[VendorBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            ImMasterVendorEntity imVendorEntity = null;
            try {
                // Get data from database by ID
                imVendorEntity = masterVendorDao.getById("nomorMaster", bean.getNomorMaster());
            } catch (HibernateException e) {
                logger.error("[VendorBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Vendor by Kode Vendor, please inform to your admin...," + e.getMessage());
            }
            if (imVendorEntity != null) {
                imVendorEntity.setNama(bean.getNama());
                imVendorEntity.setAlamat(bean.getAlamat());
                imVendorEntity.setNpwp(bean.getNpwp());
                imVendorEntity.setEmail(bean.getEmail());
                imVendorEntity.setNoTelp(bean.getNoTelp());
                imVendorEntity.setTipeVendor(bean.getTipeVendor());
                imVendorEntity.setNoRekening(bean.getNoRekening());
                imVendorEntity.setFlag(bean.getFlag());
                imVendorEntity.setAction(bean.getAction());
                imVendorEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imVendorEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    masterVendorDao.updateAndSave(imVendorEntity);
                } catch (HibernateException e) {
                    logger.error("[VendorBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Vendor, please info to your admin..." + e.getMessage());
                }

                if ("Y".equalsIgnoreCase(imVendorEntity.getVendorObat())){
                    ImSimrsVendorEntity vendorEntity = vendorDao.getById("idVendor",bean.getNomorMaster());
                    vendorEntity.setNamaVendor(bean.getNama());
                    vendorEntity.setAlamat(bean.getAlamat());
                    vendorEntity.setNpwp(bean.getNpwp());
                    vendorEntity.setEmail(bean.getEmail());
                    vendorEntity.setNoTelp(bean.getNoTelp());
                    vendorEntity.setFlag(bean.getFlag());
                    vendorEntity.setAction(bean.getAction());
                    vendorEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    vendorEntity.setLastUpdate(bean.getLastUpdate());
                    try {
                        // Update into database
                        vendorDao.updateAndSave(vendorEntity);
                    } catch (HibernateException e) {
                        logger.error("[VendorBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Vendor, please info to your admin..." + e.getMessage());
                    }
                }
            } else {
                logger.error("[VendorBoImpl.saveEdit] Error, not found data Vendor with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Vendor with request id, please check again your data ...");
            }
        }
        logger.info("[VendorBoImpl.saveEdit] end process <<<");
    }

    @Override
    public MasterVendor saveAdd(MasterVendor bean) throws GeneralBOException {
        logger.info("[VendorBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String vendorId;
            try {
                switch (bean.getTipeVendor()){
                    case "umum":
                        vendorId = masterVendorDao.getNextVendorUmumId();
                        break;
                    case "bpjs":
                        vendorId = masterVendorDao.getNextVendorBpjsId();
                        break;
                    case "lahan":
                        vendorId = masterVendorDao.getNextVendorSewaLahanId();
                        break;
                    case "lain":
                        vendorId = masterVendorDao.getNextVendorLainId();
                        break;
                    default:
                        String status = "ERROR : Tipe Vendor tidak ditemukan";
                        logger.error("[VendorBoImpl.saveAdd]"+status);
                        throw new GeneralBOException("Found problem when getting sequence vendorId id, please info to your admin..." + status);
                }
            } catch (HibernateException e) {
                logger.error("[VendorBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence vendorId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImMasterVendorEntity imVendorEntity = new ImMasterVendorEntity();

            imVendorEntity.setNomorMaster(vendorId);
            imVendorEntity.setNama(bean.getNama());
            imVendorEntity.setAlamat(bean.getAlamat());
            imVendorEntity.setNpwp(bean.getNpwp());
            imVendorEntity.setEmail(bean.getEmail());
            imVendorEntity.setNoTelp(bean.getNoTelp());
            imVendorEntity.setTipeVendor(bean.getTipeVendor());
            imVendorEntity.setNoRekening(bean.getNoRekening());
            imVendorEntity.setFlag(bean.getFlag());
            imVendorEntity.setAction(bean.getAction());
            imVendorEntity.setCreatedWho(bean.getCreatedWho());
            imVendorEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imVendorEntity.setCreatedDate(bean.getCreatedDate());
            imVendorEntity.setLastUpdate(bean.getLastUpdate());

            if (bean.getObat()){
                imVendorEntity.setVendorObat("Y");
            }else{
                imVendorEntity.setVendorObat("N");
            }

            try {
                // insert into database
                masterVendorDao.addAndSave(imVendorEntity);
            } catch (HibernateException e) {
                logger.error("[VendorBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Vendor, please info to your admin..." + e.getMessage());
            }

            if (bean.getObat()){
                ImSimrsVendorEntity vendorEntity = new ImSimrsVendorEntity();
                vendorEntity.setIdVendor(vendorId);
                vendorEntity.setNamaVendor(bean.getNama());
                vendorEntity.setAlamat(bean.getAlamat());
                vendorEntity.setEmail(bean.getEmail());
                vendorEntity.setNoTelp(bean.getNoTelp());
                vendorEntity.setFlag(bean.getFlag());
                vendorEntity.setNpwp(bean.getNpwp());
                vendorEntity.setAction(bean.getAction());
                vendorEntity.setLastUpdate(bean.getLastUpdate());
                vendorEntity.setLastUpdateWho(bean.getLastUpdateWho());
                vendorEntity.setCreatedWho(bean.getCreatedWho());
                vendorEntity.setCreatedDate(bean.getCreatedDate());
                try {
                    // insert into database
                    vendorDao.addAndSave(vendorEntity);
                } catch (HibernateException e) {
                    logger.error("[VendorBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Vendor, please info to your admin..." + e.getMessage());
                }
            }
        }

        logger.info("[VendorBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<MasterVendor> getByCriteria(MasterVendor searchBean) throws GeneralBOException {
        logger.info("[VendorBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<MasterVendor> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getNomorMaster() != null && !"".equalsIgnoreCase(searchBean.getNomorMaster())) {
                hsCriteria.put("nomor_master", searchBean.getNomorMaster());
            }
            if (searchBean.getNama() != null && !"".equalsIgnoreCase(searchBean.getNama())) {
                hsCriteria.put("nama", searchBean.getNama());
            }
            if (searchBean.getTipeVendor() != null && !"".equalsIgnoreCase(searchBean.getTipeVendor())) {
                hsCriteria.put("tipe_vendor", searchBean.getTipeVendor());
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

            List<ImMasterVendorEntity> imVendorEntity = null;
            try {
                imVendorEntity = masterVendorDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[VendorBoImpl.getSearchVendorByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imVendorEntity != null){
                MasterVendor returnVendor;
                // Looping from dao to object and save in collection
                for(ImMasterVendorEntity vendorEntity : imVendorEntity){
                    returnVendor = new MasterVendor();
                    returnVendor.setNomorMaster(vendorEntity.getNomorMaster());
                    returnVendor.setNama(vendorEntity.getNama());
                    returnVendor.setAlamat(vendorEntity.getAlamat());
                    returnVendor.setNpwp(vendorEntity.getNpwp());
                    returnVendor.setEmail(vendorEntity.getEmail());
                    returnVendor.setNoTelp(vendorEntity.getNoTelp());
                    returnVendor.setVendorObat(vendorEntity.getVendorObat());
                    returnVendor.setNoRekening(vendorEntity.getNoRekening());
                    returnVendor.setTipeVendor(vendorEntity.getTipeVendor());

                    switch (vendorEntity.getTipeVendor()){
                        case "umum":
                            returnVendor.setTipeVendorName("Rekanan/swasta/vendor/asuransi");
                            break;
                        case "bpjs":
                            returnVendor.setTipeVendorName("Perusahaan yg berelasi/kerjasama/mitra");
                            break;
                        case "lahan":
                            returnVendor.setTipeVendorName("Penyewa Lahan");
                            break;
                        case "lain":
                            returnVendor.setTipeVendorName("Lain - Lain");
                            break;
                            default:
                                returnVendor.setTipeVendorName("");
                    }

                    returnVendor.setCreatedWho(vendorEntity.getCreatedWho());
                    returnVendor.setCreatedDate(vendorEntity.getCreatedDate());
                    returnVendor.setLastUpdate(vendorEntity.getLastUpdate());
                    returnVendor.setAction(vendorEntity.getAction());
                    returnVendor.setFlag(vendorEntity.getFlag());
                    listOfResult.add(returnVendor);
                }
            }
        }
        logger.info("[VendorBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<MasterVendor> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
