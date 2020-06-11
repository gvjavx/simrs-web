package com.neurix.simrs.transaksi.verifikatorpembayaran.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.antriantelemedic.model.AntrianTelemedic;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.PembayaranOnline;

import java.util.List;

/**
 * Created by reza on 10/06/20.
 */
public interface VerifikatorPembayaranBo {

    public List<PembayaranOnline> getSearchByCriteria(PembayaranOnline bean) throws GeneralBOException;
    public List<ItSimrsPembayaranOnlineEntity> getSearchEntityByCriteria(PembayaranOnline bean) throws GeneralBOException;
    public String approveTransaksi(HeaderCheckup bean) throws GeneralBOException;

}
