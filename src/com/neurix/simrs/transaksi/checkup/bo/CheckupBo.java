package com.neurix.simrs.transaksi.checkup.bo;

import com.neurix.akuntansi.master.masterVendor.model.MasterVendor;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.pasien.model.RekamMedicLama;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.*;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.diagnosarawat.model.ItSimrsDiagnosaRawatEntity;
import com.neurix.simrs.transaksi.patrus.model.ItSImrsPatrusEntity;
import com.neurix.simrs.transaksi.pemeriksaanfisik.model.ItSimrsPemeriksaanFisikEntity;
import com.neurix.simrs.transaksi.pemeriksaanfisik.model.PemeriksaanFisik;
import com.neurix.simrs.transaksi.pengkajian.model.RingkasanKeluarMasukRs;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.ObatKronis;
import com.neurix.simrs.transaksi.psikososial.model.ItSimrsDataPsikososialEntity;
import com.neurix.simrs.transaksi.rekonsiliasiobat.model.ItSimrsRekonsiliasiObatEntity;
import com.neurix.simrs.transaksi.rencanarawat.model.ItSimrsRencanaRawatEntity;
import com.neurix.simrs.transaksi.resikojatuh.model.*;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.neurix.simrs.transaksi.transfusi.model.ItSimrsTranfusiEntity;

import java.util.List;

/**
 * Created by Toshiba on 08/11/2019.
 */
public interface CheckupBo {
    public List<HeaderCheckup> getByCriteria(HeaderCheckup bean)throws GeneralBOException;
    public List<ItSimrsHeaderChekupEntity> getListEntityHeaderCheckup(HeaderCheckup bean) throws GeneralBOException;
    public void saveAdd(HeaderCheckup bean)throws GeneralBOException;
    public void saveEdit(HeaderCheckup bean)throws GeneralBOException;
    public void updatePenunjang(HeaderCheckup bean) throws GeneralBOException;
    public void saveAddAlergi(CheckupAlergi bean) throws GeneralBOException;
    public void saveEditAlergi(CheckupAlergi bean) throws GeneralBOException;
    public List<ItSImrsCheckupAlergiEntity> getListAlergi(String noCheckup) throws GeneralBOException;
    public AlertPasien getAlertPasien(String idPasien, String branchId) throws GeneralBOException;
    public List<AlertPasien> listOfRekamMedic(HeaderCheckup bean) throws GeneralBOException;
    public ItSimrsPemeriksaanFisikEntity getEntityPemeriksaanFisikByNoCheckup(String noCheckup) throws GeneralBOException;
    public void savePemeriksaanFisik(PemeriksaanFisik bean) throws GeneralBOException;
    public ResikoJatuhResponse getResikojatuh(ResikoJatuh bean) throws GeneralBOException;
    public List<ImSimrsSkorResikoJatuhEntity> getListSkorResikoByIdParameter(String id) throws GeneralBOException;

    public List<ItSimrsRencanaRawatEntity> getListRencanaRawat(String noCheckup, String idDetail, String kategori) throws GeneralBOException;
    public void saveRencanaRawat(String noCheckup, String idDetail, List<ItSimrsRencanaRawatEntity> rencanaRawats) throws GeneralBOException;
    public void saveResikoJatuh(String noCheckup, List<ItSImrsResikoJatuhEntity> resikoJatuhList) throws GeneralBOException;
    public String getSumResikoJatuh(String noCheckup, String idKategori) throws GeneralBOException;
    public ImSimrsKategoriResikoJatuhEntity getKategoriResikoJatuh(Integer umur) throws GeneralBOException;
    public ItSimrsDataPsikososialEntity getDataPsikososial(String noCheckup) throws GeneralBOException;
    public void saveDataPsikososial(String noCheckup, ItSimrsDataPsikososialEntity psikososial) throws GeneralBOException;
    public List<ItSimrsTranfusiEntity> getListTranfusi(String noCheckup) throws GeneralBOException;
    public List<ItSImrsPatrusEntity> getDataPatrus(String noCheckup) throws GeneralBOException;
    public List<ItSimrsRekonsiliasiObatEntity> getListRekonsiliasiObat(String noCheckup) throws GeneralBOException;
    public void saveRekonObat(String noCheckup, ItSimrsRekonsiliasiObatEntity obatEntity) throws GeneralBOException;
    public CrudResponse savePatrus(ItSImrsPatrusEntity bean);
    public CrudResponse saveTranfusi(ItSimrsTranfusiEntity bean);
    public RingkasanKeluarMasukRs getRingkasanKeluarMasuk(String noCheckup, String kategori);
    public List<TindakanRawat> getListTindakan(String noCheckup, String kategori);
    public List<RekamMedicLama> getListRekamMedicLama(String idPasien);
    public List<RekamMedicLama> getListUploadRekamMedicLama(String headId);

    Long saveErrorMessage(String message, String s);

    HeaderCheckup completeBpjs(String nomorBpjs,String unitId);
    public String getNextHeaderId();

    public List<HeaderCheckup> getListAntrian(String branch, String poli) throws GeneralBOException;
    public List<HeaderCheckup> getListPeriksa(String branch, String poli) throws GeneralBOException;
    public List<HeaderCheckup> getListAntrianApotikPeriksa(String branch, String poli) throws GeneralBOException;

    public HeaderCheckup getDataDetailPasien(String idDetailCheckup) throws GeneralBOException;
    public List<ObatKronis> findRiwayatKronis(String idPasien) throws GeneralBOException;

    public List<TransaksiObatDetail> getListObatKronis(String idDetailCheckup, String idApproval) throws GeneralBOException;
    public CrudResponse savePengambilanObatKronis(HeaderCheckup bean,
                                                  ItSimrsHeaderChekupEntity headerChekupEntity,
                                                  ItSimrsHeaderDetailCheckupEntity detailCheckupEntity,
                                                  ItSimrsDiagnosaRawatEntity diagnosaRawatEntity,
                                                  ImSimrsPermintaanResepEntity resepEntity,
                                                  List<ItSimrsDokterTeamEntity> dokterTeamEntities,
                                                  List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities ) throws GeneralBOException;

    public List<MasterVendor> getComboListPtpn() throws GeneralBOException;
    public ItSimrsHeaderChekupEntity getEntityCheckupById(String id) throws GeneralBOException;

    public HeaderCheckup getLastDataPasienByIdPasien(String idPasien) throws GeneralBOException;
    public List<HeaderCheckup> getHistoryPasien(String idPasien, String branchId) throws GeneralBOException;
    public List<HeaderCheckup> getListDetailHistory(String id, String keterangan) throws GeneralBOException;
    public List<HeaderCheckup> getListVedioRm(String id) throws GeneralBOException;
    public ItSimrsHeaderChekupEntity getById(String columnName, String id) throws GeneralBOException;

    public CrudResponse updateAnamnese(HeaderCheckup bean) throws GeneralBOException;
    public String getDiagnosaPasien(String idDetailCheckup) throws GeneralBOException;
    public String getTindakanRawat(String idDetailCheckup) throws GeneralBOException;
    public String getTindakanRawatICD9(String idDetailCheckup) throws GeneralBOException;
    public String getDiagnosaPrimer(String idDetailCheckup) throws GeneralBOException;
    public String getDiagnosaSekunder(String idDetailCheckup) throws GeneralBOException;
    public String getPenunjangMedis(String idDetailCheckup, String tipe) throws GeneralBOException;
    public String getResepPasien(String idDetailCheckup) throws GeneralBOException;
    public String getAlergi(String noCheckup) throws GeneralBOException;

    public HeaderCheckup getDataPemeriksaanFisik(String noCheckup) throws GeneralBOException;
    public List<HeaderCheckup> getRiwayatPemeriksaan(String idPasien) throws GeneralBOException;

    public Dokter getNamaSipDokter(String id, String tipe) throws GeneralBOException;
    public List<PelayananPaket> getListPelayananPaket(String noCheckup) throws GeneralBOException;
    public CrudResponse nextItemPaketToPeriksa(PelayananPaket bean) throws GeneralBOException;

}
