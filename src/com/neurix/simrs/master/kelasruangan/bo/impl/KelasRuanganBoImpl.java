package com.neurix.simrs.master.kelasruangan.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.model.KelasRuangan;

import java.util.List;

public class KelasRuanganBoImpl implements KelasRuanganBo {
    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public void saveDelete(KelasRuangan bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(KelasRuangan bean) throws GeneralBOException {

    }

    @Override
    public KelasRuangan saveAdd(KelasRuangan bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<KelasRuangan> getByCriteria(KelasRuangan searchBean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<KelasRuangan> getAll() throws GeneralBOException {
        return null;
    }
}