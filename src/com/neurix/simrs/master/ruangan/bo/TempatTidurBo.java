package com.neurix.simrs.master.ruangan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.master.ruangan.model.TempatTidur;
import com.neurix.simrs.transaksi.CrudResponse;

import java.util.List;

public interface TempatTidurBo{
    public List<TempatTidur> getByCriteria(TempatTidur bean)throws GeneralBOException;
    public CrudResponse saveAdd(List<TempatTidur> list, String isNew) throws GeneralBOException;
    public CrudResponse saveEdit(TempatTidur ruangan) throws GeneralBOException;
    public List<TempatTidur> getDataTempatTidur(TempatTidur bean)throws GeneralBOException;
    public List<Ruangan> getComboRuangan(Ruangan bean)throws GeneralBOException;
}