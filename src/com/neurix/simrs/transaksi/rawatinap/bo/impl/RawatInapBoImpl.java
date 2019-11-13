package com.neurix.simrs.transaksi.rawatinap.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
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
public class RawatInapBoImpl implements RawatInapBo {

    private static transient Logger logger = Logger.getLogger(RawatInapBoImpl.class);
    private RawatInapDao rawatInapDao;

    protected List<ItSimrsRawatInapEntity> getListEntityRawatInap(RawatInap bean) throws GeneralBOException{
        logger.info("[RawatInapBoImpl.getListEntityRawatInap] Start >>>>>>>>");
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

        try {
            results = rawatInapDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[RawatInapBoImpl.getListEntityRawatInap] Error when get data rawat Inap entity ");
        }

        logger.info("[RawatInapBoImpl.getListEntityRawatInap] End <<<<<<<<");
        return results;
    }

    public void setRawatInapDao(RawatInapDao rawatInapDao) {
        this.rawatInapDao = rawatInapDao;
    }
}
