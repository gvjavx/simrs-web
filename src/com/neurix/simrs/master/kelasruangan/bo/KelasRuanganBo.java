package com.neurix.simrs.master.kelasruangan.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kelasruangan.model.KelasRuangan;

import java.util.List;

public interface KelasRuanganBo {
    public List<KelasRuangan> getByCriteria(KelasRuangan bean)throws GeneralBOException;
}