package com.neurix.simrs.transaksi.monitorruangan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.ruangan.dao.RuanganDao;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.transaksi.monitorruangan.bo.MonitorRuanganBo;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class MonitorRuanganBoImpl implements MonitorRuanganBo {
    private RuanganDao ruanganDao;
    protected static transient Logger logger = Logger.getLogger(MonitorRuanganBoImpl.class);

    @Override
    public List<Ruangan> getListRuangan(Ruangan bean) throws GeneralBOException {
        List<Ruangan> ruanganList = new ArrayList<>();
        if(bean != null){
            try {
                ruanganList = ruanganDao.getListRuangan(bean);
            }catch (HibernateException e){
                logger.error("Found Error"+e.getMessage());
            }
        }
        return ruanganList;

    }

    public void setRuanganDao(RuanganDao ruanganDao) {
        this.ruanganDao = ruanganDao;
    }

    public static Logger getLogger() {
        return logger;
    }
}
