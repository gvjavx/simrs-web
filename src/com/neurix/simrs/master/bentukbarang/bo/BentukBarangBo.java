package com.neurix.simrs.master.bentukbarang.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.bentukbarang.model.BentukBarang;

import java.util.List;

public interface BentukBarangBo {
    public List<BentukBarang> getByCriteria(BentukBarang bean) throws GeneralBOException;
    public List<BentukBarang> getDataByCriteria(BentukBarang bean) throws GeneralBOException;
    public void saveAdd(BentukBarang bean) throws GeneralBOException;
    public void saveEdit(BentukBarang bean) throws GeneralBOException;
    public void saveDelete(BentukBarang bean) throws GeneralBOException;
}
