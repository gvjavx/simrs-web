package com.neurix.simrs.transaksi.checkupdetail.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.statuspasien.bo.StatusPasienBo;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.RiwayatTindakanDTO;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Toshiba on 13/11/2019.
 */
public interface CheckupDetailBo {
    public List<HeaderDetailCheckup> getByCriteria(HeaderDetailCheckup bean) throws GeneralBOException;

    public List<HeaderDetailCheckup> getSearchRawatJalan(HeaderDetailCheckup bean) throws GeneralBOException;

    public void updateRuanganInap(String idRuangan, String idDetailCheckup) throws GeneralBOException;

    public CrudResponse saveEdit(HeaderDetailCheckup bean) throws GeneralBOException;

    public CrudResponse saveAdd(HeaderDetailCheckup bean) throws GeneralBOException;

    public BigInteger getSumOfTindakanByNoCheckup(String idDetailCheckup) throws GeneralBOException;

    public CheckResponse saveApproveAllTindakanRawatJalan(HeaderDetailCheckup bean) throws GeneralBOException;

    public List<HeaderDetailCheckup> getListUangPendaftaran(HeaderDetailCheckup bean) throws GeneralBOException;

    public void updateFlagPeriksaAntrianOnline(String idDetailCheckup) throws GeneralBOException;

    public void updateStatusBayarDetailCheckup(HeaderDetailCheckup bean) throws GeneralBOException;

    public BigDecimal getSumJumlahTindakan(String idDetailCheckup, String ket);
    public BigDecimal getSumJumlahTindakanNonBpjs(String idDetailCheckup, String ket);
    public BigDecimal getSumJumlahTindakanTransitoris(String idDetailCheckup, String ket);
    public BigDecimal getSumJumlahTindakanByJenis(String idDetailCheckup, String jenis, String ket);
    public BigDecimal getSumJumlajTindakanTransitorisByJenis(String idDetailCheckup, String jenis, String ket);
    public String findResep(String idDetailCheckup);
    public CheckResponse updateInvoiceBpjs(String idDetailCheckup, String invNumber);
    public ItSimrsHeaderDetailCheckupEntity getEntityDetailCheckupByIdDetail(String idDetailCheckup) throws GeneralBOException;
    public void saveUpdateNoJuran(HeaderDetailCheckup bean) throws GeneralBOException;
    public List<Dokter> getListDokterByDetailCheckup(String idDetailCheckup) throws GeneralBOException;

    public void saveTtd(HeaderDetailCheckup bean) throws GeneralBOException;
    public List<ItSimrsHeaderDetailCheckupEntity> getListEntityByCriteria(HeaderDetailCheckup bean) throws GeneralBOException;

    public HeaderDetailCheckup getBiayaTindakan(String idDetailCheckup) throws GeneralBOException;

    public CheckResponse saveUpdateDataAsuransi(HeaderCheckup bean) throws GeneralBOException;

    public PermintaanResep getDataDokter(String id) throws GeneralBOException;
    public HeaderDetailCheckup getCoverBiayaAsuransi(String idDetailCheckup) throws GeneralBOException;
    public HeaderDetailCheckup getTotalBiayaTindakanBpjs(String idDetailCheckup) throws GeneralBOException;
    public ItSimrsHeaderDetailCheckupEntity getDetailCheckupById(String id) throws GeneralBOException;
    public List<HeaderDetailCheckup> getListRawatInapExisiting(String branchId) throws GeneralBOException;

    List<RiwayatTindakanDTO> getRiwayatTindakanDanDokter(String idDetailCheckup) throws GeneralBOException;
    public Boolean checkAdaTransitoris(String idDetailCheckup) throws GeneralBOException;
}
