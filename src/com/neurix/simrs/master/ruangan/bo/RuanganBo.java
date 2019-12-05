package com.neurix.simrs.master.ruangan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.ruangan.model.Ruangan;

import java.util.List;

public interface RuanganBo {
    public List<Ruangan> getByCriteria(Ruangan bean)throws GeneralBOException;
}