package com.neurix.simrs.transaksi.checkupdetail.bo.impl;

import com.neurix.simrs.master.statuspasien.dao.StatusPasienDao;
import com.neurix.simrs.master.statuspasien.model.ImSimrsStatusPasienEntity;
import com.neurix.simrs.master.statuspasien.model.StatusPasien;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 13/11/2019.
 */
public class CheckupModuls {
    private static transient Logger logger = Logger.getLogger(CheckupModuls.class);

    private StatusPasienDao statusPasienDao;
    private RawatInapDao rawatInapDao;

    protected List<ImSimrsStatusPasienEntity> getListEntityStatusPasien(StatusPasien bean){
        logger.info("[CheckupModuls.getListEntityStatusPasien] Start >>>>>>>>>");
        List<ImSimrsStatusPasienEntity> entities = new ArrayList<>();

        List<ImSimrsStatusPasienEntity> results = new ArrayList<>();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_status_pasien", bean.getIdStatusPasien());

        try {
            results = statusPasienDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[CheckupModuls.getListEnstityStatusPasien] Error When get data status pasien");
        }

        logger.info("[CheckupModuls.getListEntityStatusPasien] End <<<<<<<<<");
        return results;
    }

    protected List<ItSimrsRawatInapEntity> getListEntityRawatInap(RawatInap bean){
        logger.info("[CheckupModuls.getListEntityRawatInap] Start >>>>>>>>>");

        List<ItSimrsRawatInapEntity> results = new ArrayList<>();
        Map hsCriteria = new HashMap();

        if (bean.getIdRawatInap() != null && !"".equalsIgnoreCase(bean.getIdRawatInap())){
            hsCriteria.put("id_rawat_inap", bean.getIdRawatInap());
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }
        if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())){
            hsCriteria.put("no_checkup", bean.getNoCheckup());
        }
        if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())){
            hsCriteria.put("id_ruangan", bean.getIdRuangan());
        }

        hsCriteria.put("flag", "Y");

        try {
            results = rawatInapDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[CheckupModuls.getListEntityRawatInap] Error When get data status rawat inap");
        }

        logger.info("[CheckupModuls.getListEntityRawatInap] End <<<<<<<<<");
        return results;
    }

    public void setStatusPasienDao(StatusPasienDao statusPasienDao) {
        this.statusPasienDao = statusPasienDao;
    }

    public void setRawatInapDao(RawatInapDao rawatInapDao) {
        this.rawatInapDao = rawatInapDao;
    }
}
