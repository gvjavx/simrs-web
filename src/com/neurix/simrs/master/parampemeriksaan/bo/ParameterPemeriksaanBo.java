package com.neurix.simrs.master.parampemeriksaan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.parampemeriksaan.model.ParameterPemeriksaan;
import com.neurix.simrs.transaksi.CrudResponse;

import java.util.List;

public interface ParameterPemeriksaanBo{
    public CrudResponse saveAdd(ParameterPemeriksaan bean) throws GeneralBOException;
    public CrudResponse saveEdit(ParameterPemeriksaan bean) throws GeneralBOException;
    public List<ParameterPemeriksaan> getByCriteria(ParameterPemeriksaan bean) throws GeneralBOException;
}