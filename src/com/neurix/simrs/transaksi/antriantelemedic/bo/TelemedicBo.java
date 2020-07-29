package com.neurix.simrs.transaksi.antriantelemedic.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.antriantelemedic.model.AntrianTelemedic;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import com.neurix.simrs.transaksi.bataltelemedic.model.BatalTelemedic;
import com.neurix.simrs.transaksi.bataltelemedic.model.ItSimrsBatalTelemedicEntity;
import com.neurix.simrs.transaksi.reseponline.model.ItSimrsPengirimanObatEntity;
import com.neurix.simrs.transaksi.reseponline.model.PengirimanObat;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.neurix.simrs.transaksi.verifikatorasuransi.model.ItSimrsStrukAsuransiEntity;
import com.neurix.simrs.transaksi.verifikatorasuransi.model.StrukAsuransi;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by reza on 08/06/20.
 */
public interface TelemedicBo {
    public List<ItSimrsAntrianTelemedicEntity> getListEntityByCriteria(AntrianTelemedic bean) throws GeneralBOException;
    public List<AntrianTelemedic> getSearchByCriteria(AntrianTelemedic bean) throws GeneralBOException;
    public List<AntrianTelemedic> getListAntrianByCriteria(AntrianTelemedic bean) throws GeneralBOException;
    public ItSimrsAntrianTelemedicEntity getAntrianTelemedicEntityById(String id) throws GeneralBOException;
    public String saveAdd(ItSimrsAntrianTelemedicEntity bean, String branchId, String kodeBank) throws GeneralBOException;
    public void saveEdit(AntrianTelemedic bean, String branchId, String kodeBank) throws GeneralBOException;
    public ItSimrsAntrianTelemedicEntity getAntrianTelemedicFirstOrder(String idPelayanan, String idDokter, String status) throws GeneralBOException;

    public List<PengirimanObat> getListPengirimanById(String idKurir, String idPasien) throws  GeneralBOException;
    public List<PengirimanObat> getPengirimanByCriteria(PengirimanObat bean) throws GeneralBOException;
    public List<PengirimanObat> getHistoryPengiriman(String idKurir) throws GeneralBOException;

    public void saveAddPengirimanObat(PengirimanObat bean) throws GeneralBOException;
    public void saveEditPengirimanObat(PengirimanObat bean) throws GeneralBOException;
    public BigDecimal insertResepOnline(String idTransaksiOnline, List<TransaksiObatDetail> listObat) throws GeneralBOException;
    public void createPembayaranResep(AntrianTelemedic bean, List<TransaksiObatDetail> listObat) throws GeneralBOException;
    public List<AntrianTelemedic> getHistoryByIdPasien(String idPasien, String flagEresep) throws GeneralBOException;

    public void createStrukAsuransi(ItSimrsAntrianTelemedicEntity bean, String jenis) throws GeneralBOException;
    public List<ItSimrsStrukAsuransiEntity> getStrukAsuransi(StrukAsuransi bean) throws GeneralBOException;
    public void updateFlagApproveStrukAsuransi(StrukAsuransi bean) throws GeneralBOException;

    public void generateListPembayaran(ItSimrsAntrianTelemedicEntity bean, String branchId, String tipe, String kodeBank, String jenisPeriksa) throws GeneralBOException;
    public List<ItSimrsAntrianTelemedicEntity> processBatalDokter(AntrianTelemedic bean, String alasan) throws GeneralBOException;
    public void confirmKembalian(BatalTelemedic bean) throws GeneralBOException;
    public void updateNoJurnalBatalDokter(String idBatalDokter, String noJurnal);
    public ItSimrsBatalTelemedicEntity getEnitityBatalTelemedicById(String idBatalTelemedic);
}
