package com.neurix.akuntansi.transaksi.kas.bo.impl;

import com.neurix.akuntansi.transaksi.kas.bo.LampiranBo;
import com.neurix.akuntansi.transaksi.kas.model.Lampiran;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;
import java.util.Map;

/**
 * Created by Aji Noor on 24/03/2021
 */
public class LampiranBoImpl implements LampiranBo {

    @Override
    public Map<String, Lampiran> getListLampiran(String transaksiId) {
        return null;
    }

    @Override
    public Lampiran getById(String kasDetailId) {
        return null;
    }

    @Override
    public void saveDelete(LampiranBo bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(LampiranBo bean) throws GeneralBOException {

    }

    @Override
    public LampiranBo saveAdd(LampiranBo bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<LampiranBo> getByCriteria(LampiranBo searchBean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<LampiranBo> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
