package com.neurix.simrs.transaksi.riwayattindakan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;

import java.util.List;

public interface RiwayatTindakanBo {

    public List<RiwayatTindakan> getByCriteria(RiwayatTindakan bean) throws GeneralBOException;
    public void saveAdd(RiwayatTindakan bean) throws GeneralBOException;
//    public List<RiwayatTindakan> cekTodayTarifKamar(String idDetail, String tanggal) throws GeneralBOException;

}