package com.neurix.simrs.master.ruangan.bo;

import com.neurix.common.bo.GeneralBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.neurix.simrs.master.ruangan.model.Ruangan;

import java.util.List;

public interface RuanganBo extends GeneralBo {
    public List<Ruangan> getByCriteria(Ruangan bean)throws GeneralBOException;
    public void saveAdd(Ruangan ruangan) throws GeneralBOException;
    public void saveEdit(Ruangan ruangan) throws GeneralBOException;
    public void saveDelete(Ruangan ruangan) throws GeneralBOException;
}