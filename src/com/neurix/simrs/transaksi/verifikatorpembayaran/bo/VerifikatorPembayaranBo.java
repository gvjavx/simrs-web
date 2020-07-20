package com.neurix.simrs.transaksi.verifikatorpembayaran.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.antriantelemedic.model.AntrianTelemedic;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.PembayaranOnline;

import java.util.List;

/**
 * Created by reza on 10/06/20.
 */
public interface VerifikatorPembayaranBo {

    public List<PembayaranOnline> getSearchByCriteria(PembayaranOnline bean) throws GeneralBOException;
    public List<ItSimrsPembayaranOnlineEntity> getSearchEntityByCriteria(PembayaranOnline bean) throws GeneralBOException;
    public void updateBuktiTransfer(String idTele, String pathBukti, String keterangan) throws GeneralBOException;
    public String approveTransaksi(HeaderCheckup bean) throws GeneralBOException;
    public ItSimrsPembayaranOnlineEntity getPembayaranOnlineById(String id) throws GeneralBOException;
    public ItSimrsHeaderChekupEntity getHeaderCheckupByIdAntrinTelemedic(String idAntrianOnline) throws GeneralBOException;
    public void saveEdit(ItSimrsPembayaranOnlineEntity bean) throws GeneralBOException;
    public String approveTransaksiResep(HeaderCheckup bean, String idTransaksi) throws GeneralBOException;
    public String saveAddResep(PermintaanResep bean, List<TransaksiObatDetail> detailList) throws GeneralBOException;
    public String generateIdSementara();

}
