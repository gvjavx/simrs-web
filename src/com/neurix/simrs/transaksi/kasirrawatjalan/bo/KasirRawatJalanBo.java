package com.neurix.simrs.transaksi.kasirrawatjalan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkupdetail.model.UangMuka;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;

import java.util.List;

public interface KasirRawatJalanBo {
    public List<RiwayatTindakan> getListAllTindakan(RiwayatTindakan bean) throws GeneralBOException;
    public List<UangMuka> getListUangMuka(UangMuka bean) throws GeneralBOException;
    public void updateNotaUangMukaById(UangMuka bean) throws GeneralBOException;
}
