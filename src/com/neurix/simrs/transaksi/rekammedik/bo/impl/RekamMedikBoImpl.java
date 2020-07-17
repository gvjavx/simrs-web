package com.neurix.simrs.transaksi.rekammedik.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.rekammedik.bo.RekamMedikBo;
import com.neurix.simrs.transaksi.rekammedik.dao.StatusPengisianRekamMedisDao;
import com.neurix.simrs.transaksi.rekammedik.model.ItSimrsStatusPengisianRekamMedisEntity;
import com.neurix.simrs.transaksi.rekammedik.model.StatusPengisianRekamMedis;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class RekamMedikBoImpl implements RekamMedikBo {

    protected static transient Logger logger = Logger.getLogger(RekamMedikBoImpl.class);
    private CheckupDetailDao checkupDetailDao;
    private StatusPengisianRekamMedisDao statusPengisianRekamMedisDao;

    @Override
    public List<HeaderDetailCheckup> getListPasien(HeaderDetailCheckup bean) throws GeneralBOException {
        List<HeaderDetailCheckup> list = new ArrayList<>();
        try {
            list = checkupDetailDao.getListPasienRekamMedik(bean);
        }catch (HibernateException e){
            logger.error("Found Error "+e.getMessage());
        }
        return list;
    }

    @Override
    public List<HeaderDetailCheckup> getDetailListRekamMedis(String idPasien) throws GeneralBOException {
        List<HeaderDetailCheckup> list = new ArrayList<>();
        try {
            list = checkupDetailDao.getDetailListRekamMedis(idPasien);
        }catch (HibernateException e){
            logger.error("Found Error "+e.getMessage());
        }
        return list;
    }

    @Override
    public CrudResponse saveAdd(StatusPengisianRekamMedis bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(bean != null){

            ItSimrsStatusPengisianRekamMedisEntity entity = new ItSimrsStatusPengisianRekamMedisEntity();
            entity.setIdStatusPengisianRekamMedis("SRM"+statusPengisianRekamMedisDao.getNextIdSeq());
            entity.setNoCheckup(bean.getNoCheckup());
            entity.setIdDetailCheckup(bean.getIdDetailCheckup());
            entity.setIdPasien(bean.getIdPasien());
            entity.setIdRekamMedisPasien(bean.getIdRekamMedisPasien());
            entity.setIsPengisian(bean.getIsPengisian());
            entity.setAction(bean.getAction());
            entity.setFlag(bean.getFlag());
            entity.setCreatedDate(bean.getCreatedDate());
            entity.setCreatedWho(bean.getCreatedWho());
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                statusPengisianRekamMedisDao.addAndSave(entity);
                response.setStatus("success");
                response.setMsg("Berhasil");
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMsg("Error When save status pengisian "+e.getMessage());
            }
        }
        return response;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setStatusPengisianRekamMedisDao(StatusPengisianRekamMedisDao statusPengisianRekamMedisDao) {
        this.statusPengisianRekamMedisDao = statusPengisianRekamMedisDao;
    }

    public static Logger getLogger() {
        return logger;
    }
}
