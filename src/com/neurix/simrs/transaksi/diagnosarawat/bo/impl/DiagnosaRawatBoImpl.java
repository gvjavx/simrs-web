package com.neurix.simrs.transaksi.diagnosarawat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.bo.impl.CheckupBoImpl;
import com.neurix.simrs.transaksi.diagnosarawat.bo.DiagnosaRawatBo;
import com.neurix.simrs.transaksi.diagnosarawat.dao.DiagnosaRawatDao;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author gondok
 * Friday, 15/11/19 15:07
 */
public class DiagnosaRawatBoImpl implements DiagnosaRawatBo {
    protected static transient Logger logger = Logger.getLogger(CheckupBoImpl.class);

    private DiagnosaRawatDao diagnosaRawatDao;

    public void setDiagnosaRawatDao(DiagnosaRawatDao diagnosaRawatDao) {
        this.diagnosaRawatDao = diagnosaRawatDao;
    }

    @Override
    public List<DiagnosaRawat> getByCriteria(DiagnosaRawat bean) throws GeneralBOException {
        return null;
    }

    @Override
    public void saveAdd(DiagnosaRawat bean) throws GeneralBOException {
        logger.info("[DiagnosaRawatBoImpl.saveAdd] Start >>>>>>>");
        if (bean != null) {

        }
    }
}
