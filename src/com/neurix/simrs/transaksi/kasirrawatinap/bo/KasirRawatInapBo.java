package com.neurix.simrs.transaksi.kasirrawatinap.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsUangMukaPendaftaranEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;

import java.util.List;

public interface KasirRawatInapBo {
    public List<RiwayatTindakan> getListAllTindakan(RiwayatTindakan bean) throws GeneralBOException;
    public List<RawatInap> getListRawatInap(RawatInap bean) throws GeneralBOException;
}
