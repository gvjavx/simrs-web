package com.neurix.simrs.transaksi.verifikator.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;

public interface VerifikatorBo {
    public CheckResponse updateApproveBpjsFlag(TindakanRawat bean) throws GeneralBOException;
    public CheckResponse updateKlaimBpjsFlag(HeaderCheckup bean) throws GeneralBOException;
}