package com.neurix.simrs.master.vendor.bo.impl;

import com.neurix.akuntansi.master.masterVendor.bo.MasterVendorBo;
import com.neurix.akuntansi.master.masterVendor.dao.MasterVendorDao;
import com.neurix.akuntansi.master.masterVendor.model.ImMasterVendorEntity;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.vendor.bo.VendorBo;
import com.neurix.simrs.master.vendor.dao.VendorDao;
import com.neurix.simrs.master.vendor.model.ImSimrsVendorEntity;
import com.neurix.simrs.master.vendor.model.Vendor;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendorBoImpl implements VendorBo {
    protected static transient Logger logger = Logger.getLogger(VendorBoImpl.class);
    private VendorDao vendorDao;
    private MasterVendorDao masterVendorDao;

    public void setMasterVendorDao(MasterVendorDao masterVendorDao) {
        this.masterVendorDao = masterVendorDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setVendorDao(VendorDao vendorDao) {
        this.vendorDao = vendorDao;
    }

    @Override
    public List<Vendor> getByCriteria(Vendor bean) throws GeneralBOException {
        logger.info("[VendorBoImpl.getByCriteria] START >>>>>>>");

        List<Vendor> result = new ArrayList<>();

        if (bean != null) {
            Map hsCriteria = new HashMap();

            if (bean.getIdVendor() != null && !"".equalsIgnoreCase(bean.getIdVendor())) {
                hsCriteria.put("id_vendor", bean.getIdVendor());
            }
            if (bean.getNpwp() != null && !"".equalsIgnoreCase(bean.getNpwp())) {
                hsCriteria.put("npwp", bean.getNpwp());
            }

            hsCriteria.put("flag", "Y");

            List<ImSimrsVendorEntity> vendorEntityList = new ArrayList<>();

            try {
                vendorEntityList = vendorDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[VendorBoImpl.getByCriteria] error when get data by criteria vendor "+ e.getMessage());
            }

            if (!vendorEntityList.isEmpty()){

                Vendor vendor;
                for (ImSimrsVendorEntity vendorEntity : vendorEntityList){
                    vendor = new Vendor();
                    vendor.setIdVendor(vendorEntity.getIdVendor());
                    vendor.setNamaVendor(vendorEntity.getNamaVendor());
                    vendor.setNoTelp(vendorEntity.getNoTelp());
                    vendor.setEmail(vendorEntity.getEmail());
                    vendor.setFlag(vendorEntity.getFlag());
                    vendor.setAlamat(vendorEntity.getAlamat());
                    vendor.setAction(vendorEntity.getAction());
                    vendor.setCreatedDate(vendorEntity.getCreatedDate());
                    vendor.setCreatedWho(vendorEntity.getCreatedWho());
                    vendor.setLastUpdate(vendorEntity.getLastUpdate());
                    vendor.setLastUpdateWho(vendorEntity.getLastUpdateWho());
                    vendor.setNpwp(vendorEntity.getNpwp());
                    result.add(vendor);
                }
            }
        }

        logger.info("[VendorBoImpl.getByCriteria] END <<<<<<<");
        return result;
    }

    @Override
    public CheckResponse saveAdd(Vendor bean) throws GeneralBOException {
        logger.info("[VendorBoImpl.saveAdd] START process >>>>>>");
        CheckResponse response = new CheckResponse();
        if(bean != null){

            ImMasterVendorEntity imVendorEntity = new ImMasterVendorEntity();

            imVendorEntity.setNomorMaster(getNextIdVendor());
            imVendorEntity.setNama(bean.getNamaVendor());
            imVendorEntity.setAlamat(bean.getAlamat());
            imVendorEntity.setNpwp(bean.getNpwp());
            imVendorEntity.setEmail(bean.getEmail());
            imVendorEntity.setNoTelp(bean.getNoTelp());
            imVendorEntity.setFlag(bean.getFlag());
            imVendorEntity.setAction(bean.getAction());
            imVendorEntity.setCreatedWho(bean.getCreatedWho());
            imVendorEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imVendorEntity.setCreatedDate(bean.getCreatedDate());
            imVendorEntity.setLastUpdate(bean.getLastUpdate());

            try {
                masterVendorDao.addAndSave(imVendorEntity);
                response.setStatus("success");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Found Error when save vendor "+e.getMessage());
                logger.error("[VendorBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Vendor, please info to your admin..." + e.getMessage());
            }

            ImSimrsVendorEntity vendorEntity = new ImSimrsVendorEntity();
            vendorEntity.setIdVendor(imVendorEntity.getNomorMaster());
            vendorEntity.setNpwp(bean.getNpwp());
            vendorEntity.setNamaVendor(bean.getNamaVendor());
            vendorEntity.setEmail(bean.getEmail());
            vendorEntity.setNoTelp(bean.getNoTelp());
            vendorEntity.setAlamat(bean.getAlamat());
            vendorEntity.setCreatedWho(bean.getCreatedWho());
            vendorEntity.setCreatedDate(bean.getCreatedDate());
            vendorEntity.setLastUpdateWho(bean.getLastUpdateWho());
            vendorEntity.setLastUpdate(bean.getLastUpdate());
            vendorEntity.setFlag(bean.getFlag());
            vendorEntity.setAction(bean.getAction());

            try {
                vendorDao.addAndSave(vendorEntity);
                response.setStatus("success");
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMessage("Found Error when save vendor "+e.getMessage());
                logger.error("[VendorBoImpl.saveAdd] Found Error when save vendor");
            }


        }
        logger.info("[VendorBoImpl.saveAdd] END process >>>>>>");
        return response;
    }

    @Override
    public CheckResponse saveEdit(Vendor bean) throws GeneralBOException {
        logger.info("[VendorBoImpl.saveEdit] START process >>>>>>");
        CheckResponse response = new CheckResponse();
        if(bean != null){

            ImMasterVendorEntity imVendorEntity = new ImMasterVendorEntity();

            try {
                imVendorEntity = masterVendorDao.getById("nomorMaster", bean.getIdVendor());
                response.setStatus("success");
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMessage("Found Error when search vendor by id "+e.getMessage());
                logger.error("Found Error when search vendor "+e.getMessage());
            }

            if(imVendorEntity != null){

                imVendorEntity.setNama(bean.getNamaVendor());
                imVendorEntity.setAlamat(bean.getAlamat());
                imVendorEntity.setNpwp(bean.getNpwp());
                imVendorEntity.setEmail(bean.getEmail());
                imVendorEntity.setNoTelp(bean.getNoTelp());
                imVendorEntity.setFlag("Y");
                imVendorEntity.setAction(bean.getAction());
                imVendorEntity.setCreatedWho(bean.getCreatedWho());
                imVendorEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imVendorEntity.setCreatedDate(bean.getCreatedDate());
                imVendorEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    masterVendorDao.updateAndSave(imVendorEntity);
                    response.setStatus("success");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMessage("Found Error when update vendor "+e.getMessage());
                    logger.error("[VendorBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Vendor, please info to your admin..." + e.getMessage());
                }

                ImSimrsVendorEntity vendorEntity = new ImSimrsVendorEntity();

                try {
                    vendorEntity = vendorDao.getById("idVendor", bean.getIdVendor());
                    response.setStatus("success");
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMessage("Found Error when search vendor by id "+e.getMessage());
                    logger.error("Found Error when search vendor "+e.getMessage());
                }

                if(vendorEntity != null) {

                    vendorEntity.setNpwp(bean.getNpwp());
                    vendorEntity.setNamaVendor(bean.getNamaVendor());
                    vendorEntity.setEmail(bean.getEmail());
                    vendorEntity.setNoTelp(bean.getNoTelp());
                    vendorEntity.setAlamat(bean.getAlamat());
                    vendorEntity.setCreatedWho(bean.getCreatedWho());
                    vendorEntity.setCreatedDate(bean.getCreatedDate());
                    vendorEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    vendorEntity.setLastUpdate(bean.getLastUpdate());
                    vendorEntity.setAction("U");
                    vendorEntity.setFlag("Y");

                    try {
                        vendorDao.updateAndSave(vendorEntity);
                        response.setStatus("success");
                    }catch (HibernateException e){
                        response.setStatus("error");
                        response.setMessage("Found Error when update vendor "+e.getMessage());
                        logger.error("[VendorBoImpl.saveEdit] Found Error when save update vendor");
                    }
                }
            }

        }
        logger.info("[VendorBoImpl.saveEdit] END process >>>>>>");
        return response;
    }

    @Override
    public ImSimrsVendorEntity getEntityVendorById(String idVendor) throws GeneralBOException {
        return vendorDao.getById("idVendor", idVendor);
    }

    private String getNextIdVendor() {
        String id = "";
        try {
            id = masterVendorDao.getNextVendorUmumId();
        } catch (HibernateException e) {
            logger.error("[VendorBoImpl.getNextId] Error when get next id vendor", e);
        }
        return id;
    }
}