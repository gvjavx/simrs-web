package com.neurix.simrs.master.obat.bo;

import com.neurix.akuntansi.master.reportDetail.model.ReportDetail;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.obat.model.ImSimrsKandunganObatEntity;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.KandunganObat;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.hargaobat.model.HargaObat;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.riwayatbarang.model.TransaksiStok;

import java.util.List;

public interface ObatBo{
    public List<Obat> getByCriteria(Obat searchBean) throws GeneralBOException;
    public List<Obat> getListObatByJenisObat(String idObat, String branchId) throws GeneralBOException;
    public List<Obat> getJenisObat(Obat bean) throws GeneralBOException;
    public void saveAdd(Obat bean, List<String> idJenisObats) throws GeneralBOException;
    public CheckObatResponse saveEdit(Obat bean, List<String> idJenisObats) throws GeneralBOException;

    public List<Obat> getListNamaObat(Obat bean) throws GeneralBOException;

    public CheckObatResponse checkObatStockLama(String idObat, String branchId) throws GeneralBOException;
    public CheckObatResponse checkFisikObat(Obat bean) throws GeneralBOException;
    public CheckObatResponse checkFisikObatByIdPabrik(Obat bean) throws GeneralBOException;
    public List<Obat> sortedListObat(List<Obat> obatList) throws GeneralBOException;

    public List<Obat> getEntityObatByCriteria(Obat bean) throws GeneralBOException;
    public List<Obat> getListObatGroup(Obat bean) throws GeneralBOException;
    public List<ImSimrsObatEntity> getListEntityObat(Obat bean) throws GeneralBOException;
    public ImSimrsObatEntity getObatByIdBarang(String idBarang) throws GeneralBOException;
    public List<Obat> getListHargaObat(Obat bean) throws GeneralBOException;
    public void saveHargaObat(HargaObat bean) throws GeneralBOException;

    public List<Obat> getListObatByGroup(Obat bean) throws GeneralBOException;
    public List<Obat> getListObatDetail(Obat bean) throws GeneralBOException;

    public CheckResponse saveReturObat(Obat bean, List<Obat> obatList) throws GeneralBOException;
    public List<Obat> searchReturObat(Obat bean) throws GeneralBOException;
    public List<Obat> detailReturObat(String idRetur) throws GeneralBOException;
    public List<Obat> searchObatByVendor(String idVendor, String branchId) throws GeneralBOException;
    public ImSimrsObatEntity getObatEntityByKodeBarang(String id) throws GeneralBOException;
    public List<TransaksiStok> getListReporTransaksiObat(String idPelayanan, String tahun, String bulan, String idObat) throws GeneralBOException;
    public void saveTransaksiStokOpname(Obat bean) throws GeneralBOException;
    public List<TransaksiStok> getListReportSumaryTransaksiObat(String idPelayanan, String tahun, String bulan) throws GeneralBOException;

    public List<TransaksiStok> getListSummaryStok(String branchId,String idPelayanan, String tahun, String bulan,String namaObat) throws GeneralBOException;
    public List<KandunganObat> getListKandunganObatDetail(String idObat) throws GeneralBOException;
    public ImSimrsKandunganObatEntity getMasterKandunganObatById(String idKandunganObat) throws GeneralBOException;
    public ImSimrsObatEntity getObatByIdObat(String idObat) throws GeneralBOException;
}