package com.neurix.simrs.transaksi.kasirrawatjalan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;

import java.util.List;

public interface KasirRawatJalanBo {
    public List<RiwayatTindakan> getListAllTindakan(RiwayatTindakan bean) throws GeneralBOException;
}
