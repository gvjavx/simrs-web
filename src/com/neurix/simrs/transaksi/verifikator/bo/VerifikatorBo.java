package com.neurix.simrs.transaksi.verifikator.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;

import java.util.List;

public interface VerifikatorBo {
    public CheckResponse updateApproveBpjsFlag(RiwayatTindakan bean) throws GeneralBOException;
    public CheckResponse updateKlaimBpjsFlag(HeaderDetailCheckup bean) throws GeneralBOException;
    public List<HeaderDetailCheckup> getListVerifikasiRawatJalan(HeaderDetailCheckup bean) throws GeneralBOException;
    public List<RiwayatTindakan> getListAllTindakan(RiwayatTindakan bean) throws GeneralBOException;
    public CheckResponse updateFlagKlaim(RiwayatTindakan bean) throws GeneralBOException;
    public List<RawatInap> getListVerifikasiRawatInap(RawatInap bean) throws GeneralBOException;
}