package com.neurix.simrs.transaksi.kasirrawatjalan.bo;

import com.neurix.akuntansi.master.pembayaran.model.ImAkunPembayaranEntity;
import com.neurix.akuntansi.master.pembayaran.model.Pembayaran;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.Fpk;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsUangMukaPendaftaranEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.KlaimFpkDTO;
import com.neurix.simrs.transaksi.checkupdetail.model.UangMuka;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;

import java.util.List;
import java.util.Map;

public interface KasirRawatJalanBo {
    public List<RiwayatTindakan> getListAllTindakan(RiwayatTindakan bean) throws GeneralBOException;
    public List<UangMuka> getListUangMuka(UangMuka bean) throws GeneralBOException;
    public void updateNotaUangMukaById(UangMuka bean) throws GeneralBOException;

    public List<HeaderDetailCheckup> getSearchFPK(HeaderDetailCheckup bean) throws GeneralBOException;
    public CrudResponse saveNoFPK (List<Fpk> listData) throws GeneralBOException;
    public CrudResponse pembayaranFPK (List<Fpk> listData) throws GeneralBOException;

    List<KlaimFpkDTO> getSearchCheckupBySep(String noSep) throws GeneralBOException;
    public CheckResponse saveRefund(String id, String noJurnal) throws GeneralBOException;
    public List<ImAkunPembayaranEntity> getListPembayaran() throws GeneralBOException;
    public ItSimrsUangMukaPendaftaranEntity getEnityUangMukaById(String id) throws GeneralBOException;

    public ImAkunPembayaranEntity getPembayaranEntityByCoa(String coa) throws GeneralBOException;

    Map setMappingJurnalFpk(KlaimFpkDTO data,List<KlaimFpkDTO> listOfKlaim);

    void saveFpk(KlaimFpkDTO klaimFpkDTO, List<KlaimFpkDTO> listData) throws GeneralBOException;
    public ItSimrsUangMukaPendaftaranEntity getUangMukaEntityById(String id) throws GeneralBOException;

}
