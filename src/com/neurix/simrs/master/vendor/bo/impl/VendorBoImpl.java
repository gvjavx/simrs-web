package com.neurix.simrs.master.vendor.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.vendor.bo.VendorBo;
import com.neurix.simrs.master.vendor.dao.VendorDao;
import com.neurix.simrs.master.vendor.model.ImSimrsVendorEntity;
import com.neurix.simrs.master.vendor.model.Vendor;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendorBoImpl implements VendorBo {
    protected static transient Logger logger = Logger.getLogger(VendorBoImpl.class);
    private VendorDao vendorDao;

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
                    result.add(vendor);
                }
            }
        }

        logger.info("[VendorBoImpl.getByCriteria] END <<<<<<<");
        return result;
    }
}