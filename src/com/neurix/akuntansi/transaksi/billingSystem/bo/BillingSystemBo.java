package com.neurix.akuntansi.transaksi.billingSystem.bo;

import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalEntity;
import com.neurix.akuntansi.transaksi.tutupperiod.model.TutupPeriod;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface BillingSystemBo {

    String createInvoiceNumber(String jurnalId,String branchId);

    //jurnal untuk pembayaran utang piutang
    String createJurnal(String transId, Map data, String branchId, String catatanPembuatanJurnal, String flagRegister);

    String getParameterPembayaran(String transaksiId);
    //tutup period, Sigit
    public void saveTutupPeriod(List<TutupPeriod> listTransitoris, TutupPeriod tutupPeriod) throws GeneralBOException;

    List<ItJurnalEntity> getJurnalByPengajuanId(String pengajuanId) throws GeneralBOException;
}
