package com.neurix.simrs.transaksi.verifikatorasuransi.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.verifikatorasuransi.model.ItSimrsStrukAsuransiEntity;
import com.neurix.simrs.transaksi.verifikatorasuransi.model.StrukAsuransi;

import java.util.List;

/**
 * Created by reza on 17/07/20.
 */
public interface VerifikatorAsurasiBo {
    public List<StrukAsuransi> getSearchByCriteria(StrukAsuransi bean) throws GeneralBOException;
    public void saveApproveAsuransi(StrukAsuransi bean) throws GeneralBOException;
    public List<ItSimrsStrukAsuransiEntity> getListStrukAsurasiEntity(StrukAsuransi bean) throws GeneralBOException;
}
