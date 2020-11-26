package com.neurix.simrs.transaksi.icu.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.icu.model.HeaderIcu;

import java.util.List;

public interface HeaderIcuBo {
    public List<HeaderIcu> getByCriteria(HeaderIcu bean) throws GeneralBOException;
    public CrudResponse saveAdd(List<HeaderIcu> list) throws GeneralBOException;
    public List<HeaderIcu> getListDetail(String id, String kategori) throws GeneralBOException;
    public Boolean cekData(String id, String waktu, String kategori) throws GeneralBOException;
    public CrudResponse saveDelete(HeaderIcu bean) throws GeneralBOException;
    public List<HeaderIcu> getListHeadIcu(String id, String kategori, String tgl) throws GeneralBOException;
    public CrudResponse editObat(HeaderIcu bean) throws GeneralBOException;
}
