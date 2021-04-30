package com.neurix.akuntansi.transaksi.billingSystem.bo;

import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalEntity;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.akuntansi.transaksi.tutupperiod.model.TutupPeriod;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface BillingSystemBo {

    //updated by ferdi, 01-12-2020, prepare jurnal before create jurnal
    public ItJurnalEntity prepareCreateJurnal(String transId, Map data, String branchId, String flagRegister) throws GeneralBOException;
    public String createInvoiceNumber(String jurnalId,String branchId)throws GeneralBOException;
    public void settlementPGInvoice(Map paramInvoice) throws GeneralBOException;

    //jurnal untuk pembayaran utang piutang
    Jurnal createJurnal(String transId, Map data, String branchId, String catatanPembuatanJurnal, String flagRegister);

    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException;

    String getParameterPembayaran(String transaksiId);
//    //tutup period, Sigit
//    public void saveTutupPeriod(List<TutupPeriod> listTransitoris, TutupPeriod tutupPeriod) throws GeneralBOException;
//
//    List<ItJurnalEntity> getJurnalByPengajuanId(String pengajuanId) throws GeneralBOException;
}
