package com.neurix.akuntansi.transaksi.pengajuanSetor.bo;

import com.neurix.akuntansi.transaksi.pengajuanSetor.model.*;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface PengajuanSetorBo extends BaseMasterBo<PengajuanSetor> {

    List<PengajuanSetorDetail> listPPhPayroll(PengajuanSetor search);

    List<PengajuanSetorDetail> listPPh21KsoDokter(PengajuanSetor search);

    List<PengajuanSetorDetail> listPPh21Pengajuan(PengajuanSetor search);

    List<PengajuanSetorDetail> listPPh21Jasa(PengajuanSetor search);

    void saveAddPengajuanSetorPph21(PengajuanSetor bean, List<PengajuanSetorDetail> pengajuanSetorDetailListPayroll, List<PengajuanSetorDetail> pengajuanSetorDetailListKso, List<PengajuanSetorDetail> pengajuanSetorDetailListPengajuan);

    void saveAddPengajuanSetorPpn(PengajuanSetor bean, List<PengajuanSetorDetail> pengajuanSetorDetailListPayroll, List<PengajuanSetorDetail> pengajuanSetorDetailListKso, List<PengajuanSetorDetail> pengajuanSetorDetailListPengajuan);

    List<PengajuanSetorDetail> getDetailPengajuanSetorPPh21(String pengajuanBiayaId, String tipe) throws GeneralBOException;

    void postingJurnal(PengajuanSetor bean) throws GeneralBOException;

    void approvePengajuanSetor(PengajuanSetor bean) throws GeneralBOException;

    void postingJurnalProsesPpn(PerhitunganPpnKd bean) throws GeneralBOException;

    void batalkanPengajuan(PengajuanSetor bean) throws GeneralBOException;

    List<PengajuanSetorDetail> listPPnKeluaran(PengajuanSetor search);

    List<PengajuanSetorDetail> listPPnMasukan(PengajuanSetor search);

    List<PengajuanSetorDetail> listPPnPengajuan(PengajuanSetor search);

    List<PengajuanSetorDetail> getDetailPengajuanSetorPPn(String pengajuanBiayaId, String tipe) throws GeneralBOException;

    ItPengajuanSetorEntity getPengajuanSetorById(String pengajuanSetorId);

    Map getBillingForPosting(String pengajuanSetorId);

    List<ProsesPpnKd> prosesPPnKanpus(PengajuanSetor bean);

    List<ProsesPpnKd> prosesPPnKanpusB2(PengajuanSetor bean);

    List<ProsesPpnKd> prosesPPnKanpusB3(PengajuanSetor bean);

    BigDecimal perhitunganKembaliPpn(PengajuanSetor search);

    void saveAddProsesPpnKd(PerhitunganPpnKd bean, List<ProsesPpnKd> prosesPpnKdListNormal, List<ProsesPpnKd> prosesPpnKdListB2, List<ProsesPpnKd> prosesPpnKdListB3, PerhitunganPpnKd perhitunganPpnKdListNormal, PerhitunganPpnKd perhitunganPpnKdListB2, PerhitunganPpnKd perhitunganPpnKdListB3,PerhitunganKembaliPpn perhitunganKembaliPpn,List<PerhitunganKembaliUnit> perhitunganKembaliUnitList);

    List<PerhitunganPpnKd> getSearchHomeProsesPpnKd(PerhitunganPpnKd bean) throws GeneralBOException;

    PerhitunganPpnKd getPerhitunganPpnKdList(PerhitunganPpnKd search, String tipe);

    List<ProsesPpnKd> getProsesPpnKdList(String perhitunganPpnKdId);

    List<ItAkunPerhitunganPpnKdEntity> getListUntukValidasi(PerhitunganPpnKd search);

    PerhitunganKembaliPpn getPerhitunganKembali(PerhitunganPpnKd search);

    PerhitunganPpnKd getModalPostingPpn(String bulan, String tahun);

    BigDecimal getJasaRs(PengajuanSetor search);

    BigDecimal getObatrawatInap(PengajuanSetor search);

    Map getBillingForPostingProsesPpnKoreksi(String bulan,String tahun);

    Map getBillingForPostingProsesPpnKasKeluar(String bulan, String tahun, String kas);

    void cancelProsesPpn(PerhitunganPpnKd bean) throws GeneralBOException;

    Map getBillingForPostingPengelompokanPpnKeluaran(String bulan, String tahun, String branchId);

    Map getBillingForPostingPengelompokanPpnMasukan(String bulan, String tahun, String branchId);

    Map getBillingForPostingPpnKeluaranRk(String branchId,Map data);

    Map getBillingForPostingPpnMasukanRk(String branchId, Map data);

    Map getBillingForPostingRkPpnKeluaran(String branchId, Map data,String buktiJurnal5);

    Map getBillingForPostingRkPpnMasukan(String branchId, Map data,String buktiJurnal6);

    Map getBillingForPostingPengurangPpnKeluaran(String bulan, String tahun, BigDecimal totalKeluaranJurnal5, String buktiJurnal5, BigDecimal totalMasukanJurnal6, String buktiJurnal6);

    Map getBillingForPostingPembayaranPpnKeluaran(String bulan, String tahun, BigDecimal sisaPpnKeluaran, String buktiJurnal5, String coaBank);

    Map getBillingForPostingPembagianRkUntukUnit(String bulan, String tahun, String branchId, String sumberBiayaObat);

    Map getBillingForPostingPenerimaanRkUntukUnit(String bulan, String tahun, String branchId);
}
