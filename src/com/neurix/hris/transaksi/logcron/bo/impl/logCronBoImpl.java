package com.neurix.hris.transaksi.logcron.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.logcron.bo.logCronBo;
import com.neurix.hris.transaksi.logcron.model.logCron;

import java.util.List;

public class logCronBoImpl implements logCronBo {
    @Override
    public void saveDelete(logCron bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(logCron bean) throws GeneralBOException {

    }

    @Override
    public logCron saveAdd(logCron bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<logCron> getByCriteria(logCron searchBean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<logCron> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
