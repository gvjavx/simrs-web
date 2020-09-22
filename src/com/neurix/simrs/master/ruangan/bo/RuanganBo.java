package com.neurix.simrs.master.ruangan.bo;

import com.neurix.common.bo.GeneralBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;

import java.util.List;

public interface RuanganBo extends GeneralBo {
    public List<Ruangan> getByCriteria(Ruangan bean)throws GeneralBOException;
    public void saveAdd(Ruangan ruangan) throws GeneralBOException;
    public void saveEdit(Ruangan ruangan) throws GeneralBOException;
    public void saveDelete(Ruangan ruangan) throws GeneralBOException;
    public CheckResponse updateRuangan(Ruangan bean) throws GeneralBOException;
    public MtSimrsRuanganEntity getEntityRuanganById(String id) throws GeneralBOException;

    public List<Ruangan> getListRuangan(Ruangan bean) throws GeneralBOException;
}