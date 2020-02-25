package com.neurix.akuntansi.transaksi.billingSystem.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface BillingSystemBo {

    String createInvoiceNumber(String transId);

    //jurnal untuk pembayaran utang piutang
    String createJurnal(String transId, Map data, String branchId, String catatanPembuatanJurnal, String flagRegister);
}
