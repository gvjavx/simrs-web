package com.neurix.simrs.transaksi.verifikator.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;

public interface VerifikatorBo {
    public void updateApproveBpjsFlag(TindakanRawat bean) throws GeneralBOException;
    public void saveApproveTindakan(TindakanRawat bean) throws GeneralBOException;
}