package com.neurix.simrs.master.statuspasien.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.statuspasien.bo.StatusPasienBo;
import com.neurix.simrs.master.statuspasien.dao.StatusPasienDao;
import com.neurix.simrs.master.statuspasien.model.ImSimrsStatusPasienEntity;
import com.neurix.simrs.master.statuspasien.model.StatusPasien;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 13/11/2019.
 */
public class StatusPasienBoImpl implements StatusPasienBo {

    private static transient Logger logger = Logger.getLogger(StatusPasienBoImpl.class);
    private StatusPasienDao statusPasienDao;

    protected List<ImSimrsStatusPasienEntity> getListEntityStatusPasien(StatusPasien bean) throws GeneralBOException {
        logger.info("[StatusPasienBoImpl.getListEnstityStatusPasien] Start >>>>>>>>>");

        List<ImSimrsStatusPasienEntity> results = new ArrayList<>();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_status_pasien", bean.getIdStatusPasien());

        try {
            results = statusPasienDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[StatusPasienBoImpl.getListEnstityStatusPasien] Error When get data status pasien");
        }

        logger.info("[StatusPasienBoImpl.getListEnstityStatusPasien] End <<<<<<<<<");
        return results;
    }


    public void setStatusPasienDao(StatusPasienDao statusPasienDao) {
        this.statusPasienDao = statusPasienDao;
    }
}
