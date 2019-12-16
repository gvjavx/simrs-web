package com.neurix.simrs.master.keterangankeluar.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.keterangankeluar.model.KeteranganKeluar;

import java.util.List;

public interface KeteranganKeluarBo {
    public List<KeteranganKeluar> getByCriteria(KeteranganKeluar bean) throws GeneralBOException;
}