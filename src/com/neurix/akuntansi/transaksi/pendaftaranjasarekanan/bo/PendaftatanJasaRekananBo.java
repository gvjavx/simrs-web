package com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.bo;

import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.model.PendaftaranJasa;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

public interface PendaftatanJasaRekananBo {
    public List<PendaftaranJasa> getSearchByCriteria(PendaftaranJasa bean) throws GeneralBOException;
    public void saveAdd(PendaftaranJasa bean) throws GeneralBOException;
    public void saveEdit(PendaftaranJasa bean) throws GeneralBOException;
}
