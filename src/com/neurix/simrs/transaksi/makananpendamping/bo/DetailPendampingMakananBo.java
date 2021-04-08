package com.neurix.simrs.transaksi.makananpendamping.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.makananpendamping.model.DetailPendampingMakanan;

import java.util.List;

public interface DetailPendampingMakananBo {
    public List<DetailPendampingMakanan> getByCriteria(DetailPendampingMakanan bean) throws GeneralBOException;
    public void saveEdit(DetailPendampingMakanan bean) throws GeneralBOException;
    public void updateDetail(List<DetailPendampingMakanan> bean) throws GeneralBOException;
}
