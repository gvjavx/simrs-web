package com.neurix.simrs.transaksi.checkup.bo.impl;

import com.neurix.akuntansi.master.masterVendor.model.MasterVendor;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.provinsi.dao.ProvinsiDao;
import com.neurix.simrs.bpjs.BpjsService;
import com.neurix.simrs.bpjs.tindakan.model.TindakanBpjs;
import com.neurix.simrs.master.dokter.dao.DokterDao;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterEntity;
import com.neurix.simrs.master.jenisperiksapasien.dao.AsuransiDao;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import com.neurix.simrs.master.lab.dao.LabDao;
import com.neurix.simrs.master.lab.model.ImSimrsLabEntity;
import com.neurix.simrs.master.lab.model.Lab;
import com.neurix.simrs.master.labdetail.dao.LabDetailDao;
import com.neurix.simrs.master.labdetail.model.ImSimrsLabDetailEntity;
import com.neurix.simrs.master.labdetail.model.LabDetail;
import com.neurix.simrs.master.pasien.dao.PasienDao;
import com.neurix.simrs.master.pasien.dao.RekamMedicLamaDao;
import com.neurix.simrs.master.pasien.dao.UploadRekamMedicLamaDao;
import com.neurix.simrs.master.pasien.model.ImSImrsRekamMedicLamaEntity;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pasien.model.ImSimrsUploadRekamMedicLamaEntity;
import com.neurix.simrs.master.pasien.model.RekamMedicLama;
import com.neurix.simrs.master.rekananops.dao.RekananOpsDao;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import com.neurix.simrs.master.ruangan.dao.TempatTidurDao;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganTempatTidurEntity;
import com.neurix.simrs.master.ruangan.model.TempatTidur;
import com.neurix.simrs.master.tindakan.dao.TindakanDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.antrianonline.dao.AntrianOnlineDao;
import com.neurix.simrs.transaksi.antrianonline.model.ItSimrsAntianOnlineEntity;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.dao.AsesmenDao;
import com.neurix.simrs.transaksi.checkup.dao.CheckupAlergiDao;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.dao.PelayananPaketDao;
import com.neurix.simrs.transaksi.checkup.model.*;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.dao.UangMukaDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsUangMukaPendaftaranEntity;
import com.neurix.simrs.transaksi.diagnosarawat.dao.DiagnosaRawatDao;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.diagnosarawat.model.ItSimrsDiagnosaRawatEntity;
import com.neurix.simrs.transaksi.paketperiksa.dao.DetailPaketDao;
import com.neurix.simrs.transaksi.paketperiksa.dao.ItemPaketDao;
import com.neurix.simrs.transaksi.paketperiksa.dao.PaketDao;
import com.neurix.simrs.transaksi.paketperiksa.dao.PaketPasienDao;
import com.neurix.simrs.transaksi.paketperiksa.model.*;
import com.neurix.simrs.transaksi.patrus.dao.PatrusDao;
import com.neurix.simrs.transaksi.patrus.model.ItSImrsPatrusEntity;
import com.neurix.simrs.transaksi.pemeriksaanfisik.dao.PemeriksaanFisikDao;
import com.neurix.simrs.transaksi.pemeriksaanfisik.model.ItSimrsPemeriksaanFisikEntity;
import com.neurix.simrs.transaksi.pemeriksaanfisik.model.PemeriksaanFisik;
import com.neurix.simrs.transaksi.pengkajian.model.RingkasanKeluarMasukRs;
import com.neurix.simrs.transaksi.periksalab.dao.HeaderPemeriksaanDao;
import com.neurix.simrs.transaksi.periksalab.dao.OrderPeriksaLabDao;
import com.neurix.simrs.transaksi.periksalab.dao.PeriksaLabDao;
import com.neurix.simrs.transaksi.periksalab.dao.PeriksaLabDetailDao;
import com.neurix.simrs.transaksi.periksalab.model.*;
import com.neurix.simrs.transaksi.periksaradiologi.dao.PeriksaRadiologiDao;
import com.neurix.simrs.transaksi.periksaradiologi.model.ItSimrsPeriksaRadiologiEntity;
import com.neurix.simrs.transaksi.permintaanresep.dao.PermintaanResepDao;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.ObatKronis;
import com.neurix.simrs.transaksi.psikososial.dao.PsikososialDao;
import com.neurix.simrs.transaksi.psikososial.model.ItSimrsDataPsikososialEntity;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.rekonsiliasiobat.dao.RekonsiliasiObatDao;
import com.neurix.simrs.transaksi.rekonsiliasiobat.model.ItSimrsRekonsiliasiObatEntity;
import com.neurix.simrs.transaksi.rencanarawat.dao.KategoriRencanaRawatDao;
import com.neurix.simrs.transaksi.rencanarawat.dao.ParameterRencanaRawatDao;
import com.neurix.simrs.transaksi.rencanarawat.dao.RencanaRawatDao;
import com.neurix.simrs.transaksi.rencanarawat.model.ImSimrsParameterRencanaRawatEntity;
import com.neurix.simrs.transaksi.rencanarawat.model.ItSimrsRencanaRawatEntity;
import com.neurix.simrs.transaksi.resikojatuh.dao.KategoriResikoJatuhDao;
import com.neurix.simrs.transaksi.resikojatuh.dao.ParameterResikoJatuhDao;
import com.neurix.simrs.transaksi.resikojatuh.dao.ResikoJatuhDao;
import com.neurix.simrs.transaksi.resikojatuh.dao.SkorResikoJatuhDao;
import com.neurix.simrs.transaksi.resikojatuh.model.*;

import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.teamdokter.dao.DokterTeamDao;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.neurix.simrs.transaksi.tindakanrawat.dao.TindakanRawatDao;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.transaksiobat.dao.ApprovalTransaksiObatDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailDao;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsApprovalTransaksiObatEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.neurix.simrs.transaksi.transaksitindakanbpjs.dao.TransaksiTindakanBpjsDao;
import com.neurix.simrs.transaksi.transfusi.dao.TranfusiDao;
import com.neurix.simrs.transaksi.transfusi.model.ItSimrsTranfusiEntity;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.formula.functions.T;
import org.hibernate.HibernateException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Toshiba on 08/11/2019.
 */

public class CheckupBoImpl extends BpjsService implements CheckupBo {
    protected static transient Logger logger = Logger.getLogger(CheckupBoImpl.class);

    private HeaderCheckupDao headerCheckupDao;
    private CheckupDetailDao checkupDetailDao;
    private ProvinsiDao provinsiDao;
    private DokterTeamDao dokterTeamDao;
    private CheckupAlergiDao checkupAlergiDao;
    private BranchDao branchDao;
    private TindakanDao tindakanDao;
    private TindakanRawatDao tindakanRawatDao;
    private DiagnosaRawatDao diagnosaRawatDao;
    private PemeriksaanFisikDao pemeriksaanFisikDao;
    private KategoriResikoJatuhDao kategoriResikoJatuhDao;
    private ParameterResikoJatuhDao parameterResikoJatuhDao;
    private SkorResikoJatuhDao skorResikoJatuhDao;
    private ResikoJatuhDao resikoJatuhDao;
    private PsikososialDao psikososialDao;
    private KategoriRencanaRawatDao kategoriRencanaRawatDao;
    private ParameterRencanaRawatDao parameterRencanaRawatDao;
    private RencanaRawatDao rencanaRawatDao;
    private PatrusDao patrusDao;
    private TranfusiDao tranfusiDao;
    private RekonsiliasiObatDao rekonsiliasiObatDao;
    private RiwayatTindakanDao riwayatTindakanDao;
    private DokterDao dokterDao;
    private RekamMedicLamaDao rekamMedicLamaDao;
    private UploadRekamMedicLamaDao uploadRekamMedicLamaDao;
    private UangMukaDao uangMukaDao;
    private AntrianOnlineDao antrianOnlineDao;
    private AsesmenDao asesmenDao;
    private TransaksiTindakanBpjsDao transaksiTindakanBpjsDao;
    private PermintaanResepDao permintaanResepDao;
    private ApprovalTransaksiObatDao approvalTransaksiObatDao;
    private TransaksiObatDetailDao transaksiObatDetailDao;
    private PaketDao paketDao;
    private ItemPaketDao itemPaketDao;
    private PaketPasienDao paketPasienDao;
    private PeriksaLabDao periksaLabDao;
    private PeriksaLabDetailDao periksaLabDetailDao;
    private PeriksaRadiologiDao periksaRadiologiDao;
    private LabDetailDao labDetailDao;
    private OrderPeriksaLabDao orderPeriksaLabDao;
    private RekananOpsDao rekananOpsDao;
    private DetailPaketDao detailPaketDao;
    private PelayananPaketDao pelayananPaketDao;
    private TempatTidurDao tempatTidurDao;
    private RawatInapDao rawatInapDao;
    private PasienDao pasienDao;
    private HeaderPemeriksaanDao headerPemeriksaanDao;
    private LabDao labDao;
    private AsuransiDao asuransiDao;

    @Override
    public List<HeaderCheckup> getByCriteria(HeaderCheckup bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getByCriteria] Start >>>>>>>");
        if (bean != null) {

            Boolean isPoli = false;
            Boolean isStatus = false;
            Map hsCriteria = new HashMap();

            //sodiq, 17 Nov 2019, penambahan no_checkup
            if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())) {
                hsCriteria.put("no_checkup", bean.getNoCheckup());
            }

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
                isPoli = true;
                hsCriteria.put("id_pelayanan", bean.getIdPelayanan());
            }
            if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())) {
                isStatus = true;
                hsCriteria.put("status_periksa", bean.getStatusPeriksa());
            }
            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                hsCriteria.put("id_pasien", bean.getIdPasien());
            }
            if (bean.getNoKtp() != null && !"".equalsIgnoreCase(bean.getNoKtp())) {
                hsCriteria.put("no_ktp", bean.getNoKtp());
            }
            if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama())) {
                hsCriteria.put("nama", bean.getNama());
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                hsCriteria.put("branch_id", bean.getBranchId());
            }
            if (bean.getStTglFrom() != null && !"".equalsIgnoreCase(bean.getStTglFrom())) {
                hsCriteria.put("date_from", bean.getStTglFrom());
            }
            if (bean.getGetStTglTo() != null && !"".equalsIgnoreCase(bean.getGetStTglTo())) {
                hsCriteria.put("date_to", bean.getGetStTglTo());
            }
            if (bean.getIdAntrianOnline() != null && !"".equalsIgnoreCase(bean.getIdAntrianOnline())) {
                hsCriteria.put("id_antrian_online", bean.getIdAntrianOnline());
            }

            hsCriteria.put("flag", "Y");
            List<String> listOfNoCheckup = new ArrayList<>();

            try {
                listOfNoCheckup = headerCheckupDao.getListNoCheckupByCriteria(hsCriteria, isPoli, isStatus);
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.getByCriteria] error when get data by criteria on getListNoCheckupByCriteria " + e.getMessage());
            }

            if (!listOfNoCheckup.isEmpty()) {
                hsCriteria = new HashMap();
                hsCriteria.put("list_no_checkup", listOfNoCheckup);
                List<ItSimrsHeaderChekupEntity> headerChekupEntities = null;
                try {
                    headerChekupEntities = headerCheckupDao.getByCriteria(hsCriteria);
                } catch (HibernateException e) {
                    logger.error("[CheckupBoImpl.getByCriteria] error when get data by criteria " + e.getMessage());
                }

                if (!headerChekupEntities.isEmpty()) {
                    logger.info("[CheckupBoImpl.getByCriteria] End <<<<<<<");
                    return setTemplateToHeaderCheckupResult(headerChekupEntities);
                }
            }
        }
        logger.info("[CheckupBoImpl.getByCriteria] End <<<<<<<");
        return new ArrayList<>();
    }

    @Override
    public List<ItSimrsHeaderChekupEntity> getListEntityHeaderCheckup(HeaderCheckup bean) throws GeneralBOException {

        Map hsCriteria = new HashMap();

        //sodiq, 17 Nov 2019, penambahan no_checkup
        if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())) {
            hsCriteria.put("no_checkup", bean.getNoCheckup());
        }

        if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
            hsCriteria.put("id_pasien", bean.getIdPasien());
        }
        if (bean.getNoKtp() != null && !"".equalsIgnoreCase(bean.getNoKtp())) {
            hsCriteria.put("no_ktp", bean.getNoKtp());
        }
        if (bean.getNama() != null && !"".equalsIgnoreCase(bean.getNama())) {
            hsCriteria.put("nama", bean.getNama());
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
            hsCriteria.put("branch_id", bean.getBranchId());
        }

        List<ItSimrsHeaderChekupEntity> headerChekupEntities = new ArrayList<>();
        try {
            headerChekupEntities = headerCheckupDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getListEntityHeaderCheckup] ERROR " + e.getMessage());
        }

        return headerChekupEntities;
    }

    @Override
    public Long saveErrorMessage(String message, String s) {
        return null;
    }

    public List<HeaderCheckup> setTemplateToHeaderCheckupResult(List<ItSimrsHeaderChekupEntity> listHeader) {
        logger.info("[CheckupBoImpl.setTemplateToHeaderCheckupResult] Start >>>>>>>");
        List<HeaderCheckup> result = new ArrayList<>();
        for (ItSimrsHeaderChekupEntity headerList : listHeader) {
            HeaderCheckup headerCheckup = new HeaderCheckup();
            headerCheckup.setNoCheckup(headerList.getNoCheckup());
            headerCheckup.setIdPasien(headerList.getIdPasien());
            headerCheckup.setNama(headerList.getNama());
            headerCheckup.setJenisKelamin(headerList.getJenisKelamin());
            headerCheckup.setNoKtp(headerList.getNoKtp());
            headerCheckup.setTempatLahir(headerList.getTempatLahir());
            headerCheckup.setTglLahir(headerList.getTglLahir());
            if(headerList.getTglLahir() != null){
                headerCheckup.setUmur(CommonUtil.calculateAge(headerList.getTglLahir(), true)+" Tahun");
            }
            if(headerCheckup.getTglLahir() != null){
                headerCheckup.setStTglLahir(headerCheckup.getTglLahir().toString());
            }
            headerCheckup.setDesaId(headerList.getDesaId());
            headerCheckup.setJalan(headerList.getJalan());
            headerCheckup.setSuku(headerList.getSuku());
            headerCheckup.setAgama(headerList.getAgama());
            headerCheckup.setProfesi(headerList.getProfesi());
            headerCheckup.setNoTelp(headerList.getNoTelp());
            headerCheckup.setKeteranganKeluar(headerList.getKeteranganKeluar());
            headerCheckup.setTinggi(headerList.getTinggi());
            headerCheckup.setBerat(headerList.getBerat());

            if (headerList.getUrlKtp() != null && !"".equalsIgnoreCase(headerList.getUrlKtp())) {
                String src = CommonConstant.URL_IMG + CommonConstant.RESOURCE_PATH_KTP_PASIEN + headerList.getUrlKtp();
                headerCheckup.setUrlKtp(src);
            }

            headerCheckup.setBranchId(headerList.getBranchId());
            headerCheckup.setFlag(headerList.getFlag());
            if(headerList.getCreatedDate() != null){
                headerCheckup.setCreatedDate(headerList.getCreatedDate());
                String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(headerList.getCreatedDate());
                headerCheckup.setFormatTglMasuk(formatDate);
            }
            headerCheckup.setLastUpdate(headerList.getLastUpdate());
            headerCheckup.setCreatedWho(headerList.getCreatedWho());
            headerCheckup.setLastUpdateWho(headerList.getLastUpdateWho());
            headerCheckup.setNamaPenanggung(headerList.getNamaPenanggung());
            headerCheckup.setHubunganKeluarga(headerList.getHubunganKeluarga());
            headerCheckup.setJenisKunjungan(headerList.getJenisKunjungan());

            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();

            try {
                headerDetailCheckup = headerCheckupDao.getLastPoliAndStatus(headerList.getNoCheckup());
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.setTemplateToHeaderCheckupResult] error when get data on getLastPoliAndStatus " + e.getMessage());
            }

            if (headerDetailCheckup != null) {
                headerCheckup.setIdPelayanan(headerDetailCheckup.getIdPelayanan());
                headerCheckup.setStatusPeriksa(headerDetailCheckup.getStatusPeriksa());
                headerCheckup.setStatusPeriksaName(headerDetailCheckup.getStatusPeriksaName());
                headerCheckup.setNamaPelayanan(headerDetailCheckup.getNamaPelayanan());
                headerCheckup.setNamaRuangan(headerDetailCheckup.getNamaRuangan());
                headerCheckup.setNoRuangan(headerDetailCheckup.getNoRuangan());
                headerCheckup.setIdDetailCheckup(headerDetailCheckup.getIdDetailCheckup());
                headerCheckup.setNoSep(headerDetailCheckup.getNoSep());
                headerCheckup.setTarifBpjs(headerDetailCheckup.getTarifBpjs());
                headerCheckup.setIsKronis(headerDetailCheckup.getIsKronis());
                headerCheckup.setKodeCbg(headerDetailCheckup.getKodeCbg());

                DokterTeam dokterTeam = new DokterTeam();
                dokterTeam.setIdDetailCheckup(headerDetailCheckup.getIdDetailCheckup());

                List<ItSimrsDokterTeamEntity> teamEntityList = getListEntityTeamDokter(dokterTeam);
                ItSimrsDokterTeamEntity teamEntity = new ItSimrsDokterTeamEntity();
                if (!teamEntityList.isEmpty()) {
                    teamEntity = teamEntityList.get(0);
                }
                if (teamEntity != null) {
                    headerCheckup.setIdDokter(teamEntity.getIdDokter());
                    headerCheckup.setIdTeamDokter(teamEntity.getIdTeamDokter());
                    ImSimrsDokterEntity dokterEntity = dokterDao.getById("idDokter", teamEntity.getIdDokter());
                    if(dokterEntity != null){
                        headerCheckup.setNamaDokter(dokterEntity.getNamaDokter());
                    }
                }
            }

            if (headerCheckup.getDesaId() != null) {
                List<Object[]> objs = provinsiDao.getListAlamatByDesaId(headerCheckup.getDesaId().toString());
                if (!objs.isEmpty()) {
                    Object[] obj = objs.get(0);
                    headerCheckup.setNamaDesa(obj[0].toString());
                    headerCheckup.setNamaKecamatan(obj[1].toString());
                    headerCheckup.setNamaKota(obj[2].toString());
                    headerCheckup.setNamaProvinsi(obj[3].toString());
                    headerCheckup.setKecamatanId(obj[4].toString());
                    headerCheckup.setKotaId(obj[5].toString());
                    headerCheckup.setProvinsiId(obj[6].toString());
                }
            }

            result.add(headerCheckup);
        }

        logger.info("[CheckupBoImpl.setTemplateToHeaderCheckupResult] End <<<<<<<");
        return result;
    }

    @Override
    public void saveAdd(HeaderCheckup bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.saveAdd] Start >>>>>>>");
        if (bean != null) {
            ItSimrsHeaderChekupEntity headerEntity = new ItSimrsHeaderChekupEntity();
            headerEntity.setNoCheckup(bean.getNoCheckup());
            headerEntity.setIdPasien(bean.getIdPasien());
            headerEntity.setNama(bean.getNama());
            headerEntity.setJenisKelamin(bean.getJenisKelamin());
            headerEntity.setNoKtp(bean.getNoKtp().replace("_", ""));
            headerEntity.setTempatLahir(bean.getTempatLahir());
            headerEntity.setTglLahir(bean.getTglLahir());
            headerEntity.setDesaId(bean.getDesaId());
            headerEntity.setJalan(bean.getJalan());
            headerEntity.setSuku(bean.getSuku());
            headerEntity.setProfesi(bean.getProfesi() != null && !"".equalsIgnoreCase(bean.getProfesi()) ? bean.getProfesi() : null);
            if (bean.getNoTelp() != null && !"".equalsIgnoreCase(bean.getNoTelp())) {
                String noHp = bean.getNoTelp().replace("-", "").replace("_", "");
                headerEntity.setNoTelp(noHp);
            }
            headerEntity.setAgama(bean.getAgama());
            headerEntity.setUrlKtp(bean.getUrlKtp());
            headerEntity.setBranchId(bean.getBranchId());
            headerEntity.setFlag("Y");
            headerEntity.setAction("C");
            headerEntity.setCreatedDate(bean.getCreatedDate());
            headerEntity.setLastUpdate(bean.getLastUpdate());
            headerEntity.setCreatedWho(bean.getCreatedWho());
            headerEntity.setLastUpdateWho(bean.getLastUpdateWho());
            headerEntity.setJenisKunjungan(bean.getJenisKunjungan());
            headerEntity.setNamaPenanggung(bean.getNamaPenanggung() != null && !"".equalsIgnoreCase(bean.getNamaPenanggung()) ? bean.getNamaPenanggung() : null);
            headerEntity.setHubunganKeluarga(bean.getHubunganKeluarga() != null && !"".equalsIgnoreCase(bean.getHubunganKeluarga()) ? bean.getHubunganKeluarga() : null);
            headerEntity.setBerat(bean.getBerat());
            headerEntity.setTinggi(bean.getTinggi());
            headerEntity.setPendidikan(bean.getPendidikan());
            headerEntity.setStatusPerkawinan(bean.getStatusPerkawinan());
            headerEntity.setKunjunganPoli(bean.getKunjunganPoli());

            try {
                headerCheckupDao.addAndSave(headerEntity);
                updateDataPasien(headerEntity);
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.saveAdd] Error When Saving data header checkup" + e.getMessage());
                throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving data header checkup");
            }

            // saving detail checkup
            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
                ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();

                detailCheckupEntity.setIdDetailCheckup("DCM" + getNextDetailCheckupId());
                detailCheckupEntity.setNoCheckup(headerEntity.getNoCheckup());
                detailCheckupEntity.setIdPelayanan(bean.getIdPelayanan());
                detailCheckupEntity.setIdJenisPeriksaPasien(bean.getIdJenisPeriksaPasien());

                if ("asuransi".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                    detailCheckupEntity.setMetodePembayaran("non_tunai");
                }else{
                    if("umum".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
                        detailCheckupEntity.setMetodePembayaran("tunai");
                    }
                }

                detailCheckupEntity.setNoRujukan(bean.getNoRujukan() != null && !"".equalsIgnoreCase(bean.getNoRujukan()) ? bean.getNoRujukan() : null);
                if(bean.getTglRujukan() != null && !"".equalsIgnoreCase(bean.getTglRujukan())){
                    detailCheckupEntity.setTglRujukan(Date.valueOf(bean.getTglRujukan()));
                }
                detailCheckupEntity.setUrlDocRujuk(bean.getUrlDocRujuk() != null && !"".equalsIgnoreCase(bean.getUrlDocRujuk()) ? bean.getUrlDocRujuk() : null);
                detailCheckupEntity.setIdPaket(bean.getIdPaket() != null && !"".equalsIgnoreCase(bean.getIdPaket()) ? bean.getIdPaket() : null);
                detailCheckupEntity.setIdAsuransi(bean.getIdAsuransi() != null && !"".equalsIgnoreCase(bean.getIdAsuransi()) ? bean.getIdAsuransi() : null);
                detailCheckupEntity.setCoverBiaya(bean.getCoverBiaya() != null && !"".equalsIgnoreCase(bean.getCoverBiaya().toString()) ? bean.getCoverBiaya() : null);
                detailCheckupEntity.setNoKartuAsuransi(bean.getNoKartuAsuransi() != null && !"".equalsIgnoreCase(bean.getNoKartuAsuransi()) ? bean.getNoKartuAsuransi() : null);
                detailCheckupEntity.setBranchId(bean.getBranchId());
                detailCheckupEntity.setFlag("Y");
                detailCheckupEntity.setAction("C");
                detailCheckupEntity.setCreatedDate(bean.getCreatedDate());
                detailCheckupEntity.setCreatedWho(bean.getCreatedWho());
                detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                detailCheckupEntity.setIsEksekutif(bean.getIsEksekutif());
                detailCheckupEntity.setIsVaksin(bean.getIsVaksin());

                if ("paket_perusahaan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                    detailCheckupEntity.setStatusPeriksa("1");
                    detailCheckupEntity.setCoverBiaya(bean.getCoverBiaya() == null ? null : bean.getCoverBiaya());
                } else {
                    detailCheckupEntity.setStatusPeriksa(bean.getStatusPeriksa());
                }

                if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                    detailCheckupEntity.setKodeCbg(bean.getKodeCbg());
                    detailCheckupEntity.setRujuk(bean.getRujuk() != null ? bean.getRujuk() : null);
                    detailCheckupEntity.setKetRujukan(bean.getKetPerujuk() != null ? bean.getKetPerujuk() : null);
                    detailCheckupEntity.setNoSep(bean.getNoSep());
                    detailCheckupEntity.setTarifBpjs(bean.getTarifBpjs());
                    detailCheckupEntity.setKelasPasien(bean.getKelasPasien());
                    detailCheckupEntity.setIdPelayananBpjs(bean.getIdPelayananBpjs());
                    detailCheckupEntity.setNoPpkRujukan(bean.getNoPpkRujukan() != null ? bean.getNoPpkRujukan() : null);
                }

                if(!"paket_perusahaan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
                    List<HeaderCheckup> antrian = new ArrayList<>();
                    int jumlahOnline = 0;
                    String noAntrian = "";
                    try {
                        antrian = headerCheckupDao.listAntrianOnline(bean.getBranchId(), bean.getIdPelayanan());
                    }catch (HibernateException e){
                        logger.error("[CheckupBoImpl.saveAdd] Error When search no antrian" + e.getMessage());
                        throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When search no antrian");
                    }
                    if(antrian.size() > 0){
                        jumlahOnline = antrian.size();
                    }
                    if (bean.getNoCheckupOnline() != null && !"".equalsIgnoreCase(bean.getNoCheckupOnline())) {
                        int urutan = 0;
                        for (HeaderCheckup ckp: antrian){
                            urutan++;
                            if(bean.getNoCheckupOnline().equalsIgnoreCase(ckp.getNoCheckupOnline())){
                                noAntrian = String.valueOf(urutan);
                            }
                        }
                    }else{
                        HeaderCheckup lastAntrian = new HeaderCheckup();
                        try {
                            lastAntrian = headerCheckupDao.lastAntrian(bean.getBranchId(), bean.getIdPelayanan(), bean.getIdDokter());
                        }catch (HibernateException e){
                            logger.error("[CheckupBoImpl.saveAdd] Error When search no antrian" + e.getMessage());
                            throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When search no antrian");
                        }
                        if(lastAntrian.getStNoAntrian() != null){
                            int jumlah = Integer.valueOf(lastAntrian.getStNoAntrian()) + 1;
                            noAntrian = String.valueOf(jumlah);
                        }else{
                            int jumlah = jumlahOnline + 1;
                            noAntrian = String.valueOf(jumlah);
                        }
                    }

                    if(!"".equalsIgnoreCase(noAntrian)){
                        detailCheckupEntity.setNoAntrian(noAntrian);
                    }
                }

                if ("Y".equalsIgnoreCase(bean.getIsOnline())) {
                    detailCheckupEntity.setTglAntrian(Timestamp.valueOf(bean.getStTglAntrian()));
                    detailCheckupEntity.setNoCheckupOnline(bean.getNoCheckupOnline());
                } else {
                    detailCheckupEntity.setTglAntrian(bean.getCreatedDate());
                }

                try {
                    checkupDetailDao.addAndSave(detailCheckupEntity);
                } catch (HibernateException e) {
                    logger.error("[CheckupBoImpl.saveAdd] Error When Saving data detail checkup" + e.getMessage());
                    throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving data detail checkup");
                }

                // saving dokter
                if (bean.getIdDokter() != null && !"".equalsIgnoreCase(bean.getIdDokter()) &&
                        detailCheckupEntity.getIdDetailCheckup() != null && !"".equalsIgnoreCase(detailCheckupEntity.getIdDetailCheckup())
                && !"N".equalsIgnoreCase(bean.getIdDokter())) {
                    DokterTeam dokterTeam = new DokterTeam();
                    dokterTeam.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                    dokterTeam.setIdDokter(bean.getIdDokter());
                    dokterTeam.setIdPelayanan(bean.getIdPelayanan());
                    dokterTeam.setFlagApprove("Y");
                    saveTeamDokter(dokterTeam);
                }

                //saving diagnosa, form IGD
                if (bean.getDiagnosa() != null && !"".equalsIgnoreCase(bean.getDiagnosa()) && detailCheckupEntity.getIdDetailCheckup() != null && !"".equalsIgnoreCase(detailCheckupEntity.getIdDetailCheckup())) {
                    DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
                    diagnosaRawat.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                    diagnosaRawat.setIdDiagnosa(bean.getDiagnosa());
                    diagnosaRawat.setKeteranganDiagnosa(bean.getNamaDiagnosa());
                    diagnosaRawat.setJenisDiagnosa("1");
                    saveDiagnosa(diagnosaRawat);
                }

                BigDecimal diskon = null;
                if ("bpjs_rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                    RekananOps ops = new RekananOps();
                    try {
                        ops = rekananOpsDao.getDetailRekananOps(bean.getIdAsuransi(), bean.getBranchId());
                    } catch (HibernateException e) {
                        logger.error("Error when search diskon, " + e.getMessage());
                    }

                    if (ops != null) {
                        if (ops.getDiskon() != null && !"".equalsIgnoreCase(ops.getDiskon().toString()) && ops.getDiskon().intValue() > 0) {
                            diskon = ops.getDiskon();
                        }
                    }
                }

                if (bean.getTindakanList() != null && bean.getTindakanList().size() > 0) {
                    for (Tindakan tindakan : bean.getTindakanList()) {
                        ItSimrsTindakanRawatEntity tindakanRawatEntity = new ItSimrsTindakanRawatEntity();
                        tindakanRawatEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                        tindakanRawatEntity.setIdTindakanRawat("TDR" + getNextTindakanRawatId());
                        tindakanRawatEntity.setIdTindakan(tindakan.getIdTindakan());
                        tindakanRawatEntity.setNamaTindakan(tindakan.getTindakan());
                        tindakanRawatEntity.setIdDokter(bean.getIdDokter());
                        tindakanRawatEntity.setCreatedDate(bean.getCreatedDate());
                        tindakanRawatEntity.setCreatedWho(bean.getCreatedWho());
                        tindakanRawatEntity.setLastUpdate(bean.getCreatedDate());
                        tindakanRawatEntity.setLastUpdateWho(bean.getCreatedWho());
                        tindakanRawatEntity.setFlag("Y");
                        tindakanRawatEntity.setAction("U");
                        tindakanRawatEntity.setApproveFlag("Y");

                        if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                            if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                                tindakanRawatEntity.setTarif(tindakan.getTarifBpjs());
                            } else {
                                if (diskon != null && diskon.intValue() > 0) {
                                    BigDecimal hasil = new BigDecimal(tindakan.getTarifBpjs()).multiply(diskon);
                                    tindakanRawatEntity.setTarif(hasil.toBigInteger());
                                }
                            }
                        } else {
                            tindakanRawatEntity.setTarif(tindakan.getTarif());
                        }

                        tindakanRawatEntity.setQty(new BigInteger(String.valueOf(1)));
                        tindakanRawatEntity.setTarifTotal(tindakanRawatEntity.getTarif().multiply(tindakanRawatEntity.getQty()));

                        try {

                            tindakanRawatDao.addAndSave(tindakanRawatEntity);

                            if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {

                                String jenPasien = "";
                                if ("bpjs_rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                                    jenPasien = "bpjs";
                                } else {
                                    jenPasien = bean.getIdJenisPeriksaPasien();
                                }

                                ItSimrsRiwayatTindakanEntity riwayatTindakan = new ItSimrsRiwayatTindakanEntity();
                                riwayatTindakan.setIdRiwayatTindakan("RWT" + getNextIdRiwayatTindakan());
                                riwayatTindakan.setIdTindakan(tindakanRawatEntity.getIdTindakanRawat());
                                riwayatTindakan.setNamaTindakan(tindakanRawatEntity.getNamaTindakan());
                                riwayatTindakan.setTotalTarif(new BigDecimal(String.valueOf(tindakanRawatEntity.getTarifTotal())));
                                riwayatTindakan.setApproveBpjsFlag("Y");
                                riwayatTindakan.setKategoriTindakanBpjs(tindakan.getKategoriInaBpjs());
                                riwayatTindakan.setKeterangan("tindakan");
                                riwayatTindakan.setJenisPasien(jenPasien);
                                riwayatTindakan.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                                riwayatTindakan.setFlagUpdateKlaim("Y");
                                riwayatTindakan.setCreatedWho(bean.getCreatedWho());
                                riwayatTindakan.setCreatedDate(bean.getCreatedDate());
                                riwayatTindakan.setLastUpdate(bean.getLastUpdate());
                                riwayatTindakan.setLastUpdateWho(bean.getLastUpdateWho());
                                riwayatTindakan.setFlag("Y");
                                riwayatTindakan.setAction("C");
                                riwayatTindakan.setTanggalTindakan(bean.getCreatedDate());

                                try {
                                    riwayatTindakanDao.addAndSave(riwayatTindakan);
                                } catch (HibernateException e) {
                                    logger.error("[CheckupBoImpl FOunf error]" + e.getMessage());
                                }

                            }

                        } catch (HibernateException e) {
                            logger.error("[CheckupBoImpl.saveAdd] Error When Saving tindakan rawat" + e.getMessage());
                            throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving tindakan rawat" + e.getMessage());
                        }
                    }
                }

                // save uang muka
                if ("umum".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {

                    if (bean.getUangMuka() != null && !"".equalsIgnoreCase(bean.getUangMuka().toString())) {
                        ItSimrsUangMukaPendaftaranEntity uangMukaPendaftaranEntity = new ItSimrsUangMukaPendaftaranEntity();
                        uangMukaPendaftaranEntity.setId("UM" + bean.getBranchId() + dateFormater("MM") + dateFormater("yy") + uangMukaDao.getNextId());
                        uangMukaPendaftaranEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                        uangMukaPendaftaranEntity.setFlag("Y");
                        uangMukaPendaftaranEntity.setAction("C");
                        uangMukaPendaftaranEntity.setCreatedDate(bean.getCreatedDate());
                        uangMukaPendaftaranEntity.setCreatedWho(bean.getCreatedWho());
                        uangMukaPendaftaranEntity.setLastUpdate(bean.getCreatedDate());
                        uangMukaPendaftaranEntity.setLastUpdateWho(bean.getCreatedWho());

                        if (bean.getNoNota() != null) {
                            uangMukaPendaftaranEntity.setNoNota(bean.getNoNota());
                            uangMukaPendaftaranEntity.setStatusBayar("Y");
                        }

                        if (bean.getUangMuka() == null || bean.getUangMuka().compareTo(new BigInteger(String.valueOf(0))) == 0) {
                            uangMukaPendaftaranEntity.setJumlah(new BigInteger(String.valueOf(0)));
                        } else {
                            uangMukaPendaftaranEntity.setJumlah(bean.getUangMuka());
                        }

                        try {
                            uangMukaDao.addAndSave(uangMukaPendaftaranEntity);
                        } catch (HibernateException e) {
                            logger.error("[CheckupBoImpl.saveAdd] Error When Saving" + e.getMessage());
                            throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving" + e.getMessage());
                        }
                    }
                }

                if (bean.getNoCheckupOnline() != null && !"".equalsIgnoreCase(bean.getNoCheckupOnline())) {

                    List<ItSimrsAntianOnlineEntity> onlineEntityList = new ArrayList<>();
                    Map hsCriteria = new HashMap();
                    hsCriteria.put("no_checkup_online", bean.getNoCheckupOnline());

                    try {
                        onlineEntityList = antrianOnlineDao.getByCriteria(hsCriteria);
                    } catch (HibernateException e) {
                        logger.error("Found Error when search registrasi online " + e.getMessage());
                    }

                    ItSimrsAntianOnlineEntity onlineEntity = new ItSimrsAntianOnlineEntity();
                    if (onlineEntityList.size() > 0) {
                        onlineEntity = onlineEntityList.get(0);

                        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                        String userLogin = CommonUtil.userLogin();
                        onlineEntity.setLastUpdate(updateTime);
                        onlineEntity.setLastUpdateWho(userLogin);
                        onlineEntity.setNoCheckup(headerEntity.getNoCheckup());
                        onlineEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());

                        try {
                            antrianOnlineDao.updateAndSave(onlineEntity);
                        } catch (HibernateException e) {
                            logger.error("Found Error when update antrian online");
                        }
                    }
                }

                if (bean.getListPemeriksaan().size() > 0 && bean.getListPemeriksaan() != null) {
                    List<PeriksaLab> periksaLabList = new ArrayList<>();
                    for (String idPemeriksaan: bean.getListPemeriksaan()){
                        if(idPemeriksaan != null && !"".equalsIgnoreCase(idPemeriksaan)){
                            PeriksaLab periksaLab = new PeriksaLab();
                            periksaLab.setIdDokterPengirim(bean.getIdDokter());
                            periksaLab.setIdPemeriksaan(idPemeriksaan);
                            ImSimrsLabEntity labEntity = labDao.getById("idLab", idPemeriksaan);
                            if(labEntity != null){
                                periksaLab.setNamaPemeriksaan(labEntity.getNamaLab());
                            }
                            periksaLab.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                            periksaLab.setCreatedWho(bean.getCreatedWho());
                            periksaLab.setCreatedDate(bean.getCreatedDate());
                            periksaLab.setLastUpdateWho(bean.getLastUpdateWho());
                            periksaLab.setLastUpdate(bean.getLastUpdate());
                            periksaLab.setIdKategoriLab(headerCheckupDao.getIdKategoriLab(idPemeriksaan));
                            periksaLab.setIsJustLab("Y");
                            periksaLabList.add(periksaLab);
                        }
                    }
                    if(periksaLabList.size() > 0){
                        saveOrderLab(periksaLabList);
                    }
                }

                if ("Y".equalsIgnoreCase(bean.getIsOrderLab())) {
                    saveLabCheckupUlang(bean.getLastIdDetailCheckup(), detailCheckupEntity.getIdDetailCheckup(), bean.getIdDokter());
                }

                if ("paket_perusahaan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "paket_individu".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                    insertItemPaketToPeriksa(detailCheckupEntity.getIdDetailCheckup(), bean.getIdPasien(), bean.getIdDokter(), bean.getCreatedWho(), bean.getBranchId(), bean.getIdJenisPeriksaPasien(), bean.getIdPaket(), headerEntity.getNoCheckup());
                }

                //AMBULANCE, sodiq, 25.02.2020
                if(bean.getIdTindakan() != null && !"".equalsIgnoreCase(bean.getIdTindakan())){
                    ItSimrsTindakanRawatEntity tindakanRawatEntity = new ItSimrsTindakanRawatEntity();
                    tindakanRawatEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                    tindakanRawatEntity.setIdTindakanRawat("TDR" + getNextTindakanRawatId());
                    tindakanRawatEntity.setIdTindakan(bean.getIdTindakan());
                    tindakanRawatEntity.setNamaTindakan(bean.getNamaTindakan());
                    tindakanRawatEntity.setIdDokter(bean.getIdDokter());
                    tindakanRawatEntity.setCreatedDate(bean.getCreatedDate());
                    tindakanRawatEntity.setCreatedWho(bean.getCreatedWho());
                    tindakanRawatEntity.setLastUpdate(bean.getCreatedDate());
                    tindakanRawatEntity.setLastUpdateWho(bean.getCreatedWho());
                    tindakanRawatEntity.setFlag("Y");
                    tindakanRawatEntity.setAction("U");
                    tindakanRawatEntity.setApproveFlag("Y");
                    tindakanRawatEntity.setTarif(new BigInteger(bean.getTarif()));
                    tindakanRawatEntity.setQty(new BigInteger(bean.getJumlah()));
                    tindakanRawatEntity.setTarifTotal(tindakanRawatEntity.getTarif().multiply(tindakanRawatEntity.getQty()));
                    try {
                        tindakanRawatDao.addAndSave(tindakanRawatEntity);
                    }catch (Exception e){
                        throw new GeneralBOException("Error when save tindakan ambulance"+e.getMessage());
                    }
                }
            }else{
                throw new GeneralBOException("[CheckupBoImpl.saveAdd] ID Pelayanan tidak ditemukan...!");
            }
            logger.info("[CheckupBoImpl.saveAdd] End <<<<<<<");
        }
    }

    public void updateDataPasien(ItSimrsHeaderChekupEntity bean){
        ImSimrsPasienEntity pasienEntity = new ImSimrsPasienEntity();
        try {
            pasienEntity = pasienDao.getById("idPasien", bean.getIdPasien());
        }catch (HibernateException e){
            logger.error("[CheckupBoImpl.updateDataPasien] Error when searching data pasien", e);
            throw new GeneralBOException(" Error when searching data pasien " + e.getMessage());
        }
        if(pasienEntity != null){
            pasienEntity.setNama(bean.getNama());
            pasienEntity.setJenisKelamin(bean.getJenisKelamin());
            pasienEntity.setNoKtp(bean.getNoKtp());
            pasienEntity.setTempatLahir(bean.getTempatLahir());
            pasienEntity.setTglLahir(bean.getTglLahir());
            pasienEntity.setDesaId(bean.getDesaId());
            pasienEntity.setJalan(bean.getJalan());
            pasienEntity.setSuku(bean.getSuku());
            pasienEntity.setAgama(bean.getAgama());
            pasienEntity.setProfesi(bean.getProfesi());
            pasienEntity.setPendidikan(bean.getPendidikan());
            pasienEntity.setStatusPerkawinan(bean.getStatusPerkawinan());
            pasienEntity.setAction("U");
            pasienEntity.setLastUpdate(bean.getLastUpdate());
            pasienEntity.setLastUpdateWho(bean.getLastUpdateWho());
            try {
                pasienDao.updateAndSave(pasienEntity);
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.updateDataPasien] Error when update data pasien", e);
                throw new GeneralBOException(" Error when update data pasien " + e.getMessage());
            }
        }
    }

    private void saveOrderLab(List<PeriksaLab> list) {
        if (list != null && list.size() > 0) {
            PeriksaLab periksaLab = list.get(0);
            ItSimrsHeaderPemeriksaanEntity pemeriksaanEntity = new ItSimrsHeaderPemeriksaanEntity();
            pemeriksaanEntity.setIdHeaderPemeriksaan(headerPemeriksaanDao.getNextId());
            pemeriksaanEntity.setIdDetailCheckup(periksaLab.getIdDetailCheckup());
            pemeriksaanEntity.setIdDokterPengirim(periksaLab.getIdDokterPengirim());
            pemeriksaanEntity.setStatusPeriksa("0");
            pemeriksaanEntity.setFlag("Y");
            pemeriksaanEntity.setAction("C");
            pemeriksaanEntity.setCreatedDate(periksaLab.getCreatedDate());
            pemeriksaanEntity.setCreatedWho(periksaLab.getCreatedWho());
            pemeriksaanEntity.setLastUpdate(periksaLab.getLastUpdate());
            pemeriksaanEntity.setLastUpdateWho(periksaLab.getLastUpdateWho());
            pemeriksaanEntity.setIsJustLab(periksaLab.getIsJustLab());
            pemeriksaanEntity.setIsPending("N");
            pemeriksaanEntity.setIsPeriksaLuar("N");
            pemeriksaanEntity.setIdKategoriLab(periksaLab.getIdKategoriLab());

            try {
                headerPemeriksaanDao.addAndSave(pemeriksaanEntity);
            } catch (HibernateException e) {
                logger.error("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data periksa lab " + e.getMessage());
                throw new GeneralBOException("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data periksa lab " + e.getMessage());
            }

            for (PeriksaLab lab: list){
                ItSimrsPeriksaLabEntity periksaLabEntity = new ItSimrsPeriksaLabEntity();
                periksaLabEntity.setIdPeriksaLab(getNextPeriksaLabId());
                periksaLabEntity.setIdHeaderPemeriksaan(pemeriksaanEntity.getIdHeaderPemeriksaan());
                periksaLabEntity.setIdPemeriksaan(lab.getIdPemeriksaan());
                periksaLabEntity.setNamaPemeriksaan(lab.getNamaPemeriksaan());
                periksaLabEntity.setFlag("Y");
                periksaLabEntity.setAction("C");
                periksaLabEntity.setCreatedWho(lab.getCreatedWho());
                periksaLabEntity.setCreatedDate(lab.getCreatedDate());
                periksaLabEntity.setLastUpdateWho(lab.getLastUpdateWho());
                periksaLabEntity.setLastUpdate(lab.getLastUpdate());

                try {
                    periksaLabDao.addAndSave(periksaLabEntity);
                } catch (HibernateException e) {
                    logger.error("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data periksa lab " + e.getMessage());
                    throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data periksa lab " + e.getMessage());
                }
            }
        }
    }

    private void saveLabCheckupUlang(String lastIdDetailCheckup, String idDetailCheckup, String idDokter) {
        if (lastIdDetailCheckup != null && !"".equalsIgnoreCase(lastIdDetailCheckup)) {
            String userLogin = CommonUtil.userLogin();
            Timestamp now = new Timestamp(System.currentTimeMillis());
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_detail_checkup", lastIdDetailCheckup);
            hsCriteria.put("is_pemeriksaan", "N");
            hsCriteria.put("flag", "Y");
            List<ItSimrsOrderPeriksaLabEntity> periksaLabEntities = new ArrayList<>();
            try {
                periksaLabEntities = orderPeriksaLabDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PeriksaLabBoImpl.getListEntityPeriksaLab] ERROR When search data periksa lab ", e);
                throw new GeneralBOException("[PeriksaLabBoImpl.getListEntityPeriksaLab] ERROR When search data periksa lab " + e.getCause());
            }

            ItSimrsOrderPeriksaLabEntity orderPeriksaLabEntity = new ItSimrsOrderPeriksaLabEntity();
            if (periksaLabEntities.size() > 0) {
                orderPeriksaLabEntity = periksaLabEntities.get(0);
                ItSimrsHeaderPemeriksaanEntity pemeriksaanEntity = new ItSimrsHeaderPemeriksaanEntity();
                pemeriksaanEntity.setIdHeaderPemeriksaan(headerPemeriksaanDao.getNextId());
                pemeriksaanEntity.setIdDetailCheckup(idDetailCheckup);
                pemeriksaanEntity.setIdKategoriLab(orderPeriksaLabEntity.getIdKategoriLab());
                pemeriksaanEntity.setIdDokterPengirim(idDokter);
                pemeriksaanEntity.setIsPending("N");
                pemeriksaanEntity.setIsPeriksaLuar("N");
                pemeriksaanEntity.setIsJustLab("N");
                pemeriksaanEntity.setIsPending("N");
                pemeriksaanEntity.setStatusPeriksa("0");
                pemeriksaanEntity.setFlag("Y");
                pemeriksaanEntity.setAction("C");
                pemeriksaanEntity.setCreatedWho(userLogin);
                pemeriksaanEntity.setCreatedDate(now);
                pemeriksaanEntity.setLastUpdateWho(userLogin);
                pemeriksaanEntity.setLastUpdate(now);

                try {
                    headerPemeriksaanDao.addAndSave(pemeriksaanEntity);
                }catch (HibernateException e){
                    logger.error("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data periksa lab " + e.getMessage());
                    throw new GeneralBOException("Error, "+e.getMessage());
                }

                String idPemeriksaan = "";
                String idPeriksaLab = "";

                for (ItSimrsOrderPeriksaLabEntity orderLab: periksaLabEntities){
                    ItSimrsPeriksaLabEntity periksaLabEntity = new ItSimrsPeriksaLabEntity();
                    periksaLabEntity.setIdPeriksaLab(getNextPeriksaLabId());
                    periksaLabEntity.setIdHeaderPemeriksaan(pemeriksaanEntity.getIdHeaderPemeriksaan());
                    periksaLabEntity.setIdPemeriksaan(orderLab.getIdPemeriksaan());
                    periksaLabEntity.setNamaPemeriksaan(orderLab.getNamaPemeriksaan());
                    periksaLabEntity.setFlag("Y");
                    periksaLabEntity.setAction("C");
                    periksaLabEntity.setCreatedWho(userLogin);
                    periksaLabEntity.setCreatedDate(now);
                    periksaLabEntity.setLastUpdateWho(userLogin);
                    periksaLabEntity.setLastUpdate(now);

                    if(!idPemeriksaan.equalsIgnoreCase(orderLab.getIdPemeriksaan())){
                        idPemeriksaan = orderLab.getIdPemeriksaan();
                        try {
                            periksaLabDao.addAndSave(periksaLabEntity);
                            idPeriksaLab = periksaLabEntity.getIdPeriksaLab();
                        } catch (HibernateException e) {
                            logger.error("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data periksa lab " + e.getMessage());
                            throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data periksa lab " + e.getMessage());
                        }
                    }

                    List<LabDetail> labDetailList = new ArrayList<>();
                    if(orderLab.getIdLabDetail() != null && !"".equalsIgnoreCase(orderLab.getIdLabDetail())){
                        LabDetail lab = new LabDetail();
                        lab.setIdLabDetail(orderLab.getIdLabDetail());
                        try {
                            labDetailList = labDetailDao.getDataParameterPemeriksaan(lab);
                        } catch (GeneralBOException e) {
                            logger.error(e.getMessage());
                            throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab " + e.getMessage());
                        }
                        if (labDetailList.size() > 0) {
                            lab = labDetailList.get(0);
                            ItSimrsPeriksaLabDetailEntity detailEntity = new ItSimrsPeriksaLabDetailEntity();
                            detailEntity.setIdPeriksaLabDetail(getNextDetailLapId());
                            detailEntity.setIdPeriksaLab(idPeriksaLab);
                            detailEntity.setIdLabDetail(lab.getIdLabDetail());
                            detailEntity.setNamaDetailPeriksa(lab.getNamaDetailPeriksa());
                            detailEntity.setKeteranganAcuanL(lab.getKeteranganAcuanL());
                            detailEntity.setKeteranganAcuanP(lab.getKeteranganAcuanP());
                            detailEntity.setSatuan(lab.getSatuan());
                            detailEntity.setTarif(lab.getTarif());
                            detailEntity.setFlag("Y");
                            detailEntity.setAction("C");
                            detailEntity.setCreatedWho(userLogin);
                            detailEntity.setCreatedDate(now);
                            detailEntity.setLastUpdateWho(userLogin);
                            detailEntity.setLastUpdate(now);

                            try {
                                periksaLabDetailDao.addAndSave(detailEntity);
                            } catch (HibernateException e) {
                                logger.error("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab " + e.getMessage());
                                throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab " + e.getMessage());
                            }
                        }else{
                            throw new GeneralBOException("[PeriksaLabBoImpl.saveOrderPemeriksaan] ERROR when saving data detail periksa lab ");
                        }
                    }
                }
            }
        }
    }

    private void insertItemPaketToPeriksa(String idDetailCheckup, String idPasien, String idDokter, String userLogin, String branchId, String jenisPeriksa, String idPaket, String noCheckup) {

        Timestamp time = new Timestamp(System.currentTimeMillis());
        List<PaketPeriksa> paketPasienList = new ArrayList<>();

        if ("paket_perusahaan".equalsIgnoreCase(jenisPeriksa)) {

            if (idPasien != null && !"".equalsIgnoreCase(idPasien)) {
                try {
                    paketPasienList = paketPasienDao.getItemPaket(idPasien);
                } catch (HibernateException e) {
                    logger.error("Found Error " + e.getMessage());
                    throw new GeneralBOException("Found Error " + e.getMessage());
                }
            }
        }

        if ("paket_individu".equalsIgnoreCase(jenisPeriksa)) {
            if (idPaket != null && !"".equalsIgnoreCase(idPaket)) {
                try {
                    paketPasienList = paketPasienDao.getItemPaketWithIdPaket(idPaket, null);
                } catch (HibernateException e) {
                    logger.error("Found Error " + e.getMessage());
                    throw new GeneralBOException("Found Error " + e.getMessage());
                }
            }
        }

        if (paketPasienList.size() > 0) {

            String seqIdHeaderPemeriksaan = "";
            String jenisPemeriksaan = "";
            String idPemeriksaan = "";
            String seqIdPeriksaLab = "";

            for (PaketPeriksa paketPeriksa : paketPasienList) {
                if ("tindakan".equalsIgnoreCase(paketPeriksa.getJenisItem())) {
                    ItSimrsTindakanRawatEntity tindakanRawatEntity = new ItSimrsTindakanRawatEntity();
                    tindakanRawatEntity.setIdTindakanRawat("TDR" + getNextTindakanRawatId());
                    tindakanRawatEntity.setIdTindakan(paketPeriksa.getIdItem());
                    tindakanRawatEntity.setIdPerawat(userLogin);
                    tindakanRawatEntity.setIdDokter(idDokter);
                    tindakanRawatEntity.setIdDetailCheckup(idDetailCheckup);
                    tindakanRawatEntity.setQty(new BigInteger("1"));

                    List<Tindakan> tindakanEntity = new ArrayList<>();
                    Tindakan resultTindakan = new Tindakan();
                    Tindakan tin = new Tindakan();
                    tin.setIdTindakan(paketPeriksa.getIdItem());

                    try {
                        tindakanEntity = tindakanDao.getListDataTindakan(tin);
                    } catch (HibernateException e) {
                        logger.error("[CheckupBoImpl.insertItemPaketToPeriksa] ERROR", e);
                        throw new GeneralBOException("[CheckupBoImpl.insertItemPaketToPeriksa] ERROR", e);
                    }

                    if (tindakanEntity.size() > 0) {
                        resultTindakan = tindakanEntity.get(0);
                        tindakanRawatEntity.setNamaTindakan(resultTindakan.getTindakan());
                        tindakanRawatEntity.setTarif(resultTindakan.getTarif());
                        tindakanRawatEntity.setTarifTotal(resultTindakan.getTarif().multiply(tindakanRawatEntity.getQty()));
                        tindakanRawatEntity.setFlag("Y");
                        tindakanRawatEntity.setAction("C");
                        tindakanRawatEntity.setCreatedDate(time);
                        tindakanRawatEntity.setCreatedWho(userLogin);
                        tindakanRawatEntity.setLastUpdate(time);
                        tindakanRawatEntity.setLastUpdateWho(userLogin);
                        tindakanRawatEntity.setApproveFlag("Y");
                        try {
                            tindakanRawatDao.addAndSave(tindakanRawatEntity);
                        } catch (HibernateException e) {
                            logger.error("[CheckupBoImpl.insertItemPaketToPeriksa] ERROR", e);
                            throw new GeneralBOException("[CheckupBoImpl.insertItemPaketToPeriksa] ERROR", e);
                        }
                    }
                }

                if ("laboratorium".equalsIgnoreCase(paketPeriksa.getJenisItem()) || "radiologi".equalsIgnoreCase(paketPeriksa.getJenisItem())) {
                    String idKategoriLab = labDetailDao.kategoriLab(paketPeriksa.getIdKategoriItem(), CommonUtil.userBranchLogin());
                    if(!jenisPemeriksaan.equalsIgnoreCase(paketPeriksa.getJenisItem())){
                        jenisPemeriksaan = paketPeriksa.getJenisItem();
                        ItSimrsHeaderPemeriksaanEntity pemeriksaanEntity = new ItSimrsHeaderPemeriksaanEntity();
                        pemeriksaanEntity.setIdHeaderPemeriksaan(headerPemeriksaanDao.getNextId());
                        pemeriksaanEntity.setIdDetailCheckup(idDetailCheckup);
                        pemeriksaanEntity.setIdKategoriLab(idKategoriLab);
                        pemeriksaanEntity.setIdDokterPengirim(idDokter);
                        pemeriksaanEntity.setIsPending("N");
                        pemeriksaanEntity.setIsPeriksaLuar("N");
                        pemeriksaanEntity.setIsJustLab("N");
                        pemeriksaanEntity.setIsPending("N");
                        pemeriksaanEntity.setStatusPeriksa("0");
                        pemeriksaanEntity.setFlag("Y");
                        pemeriksaanEntity.setAction("C");
                        pemeriksaanEntity.setCreatedWho(userLogin);
                        pemeriksaanEntity.setCreatedDate(time);
                        pemeriksaanEntity.setLastUpdateWho(userLogin);
                        pemeriksaanEntity.setLastUpdate(time);
                        pemeriksaanEntity.setJenisPasien(jenisPeriksa);

                        try {
                            headerPemeriksaanDao.addAndSave(pemeriksaanEntity);
                            seqIdHeaderPemeriksaan = pemeriksaanEntity.getIdHeaderPemeriksaan();
                        }catch (HibernateException e){
                            logger.error("[CheckupBoImpl.insertItemPaketToPeriksa] ERROR when saving data periksa lab " + e.getMessage());
                            throw new GeneralBOException("Error, "+e.getMessage());
                        }
                    }

                    if(!idPemeriksaan.equalsIgnoreCase(paketPeriksa.getIdKategoriItem())){
                        idPemeriksaan = paketPeriksa.getIdKategoriItem();
                        ImSimrsLabEntity labEntity = labDao.getById("idLab", paketPeriksa.getIdKategoriItem());
                        if(labEntity != null){
                            ItSimrsPeriksaLabEntity periksaLabEntity = new ItSimrsPeriksaLabEntity();
                            periksaLabEntity.setIdPeriksaLab(getNextPeriksaLabId());
                            periksaLabEntity.setIdHeaderPemeriksaan(seqIdHeaderPemeriksaan);
                            periksaLabEntity.setIdPemeriksaan(paketPeriksa.getIdKategoriItem());
                            periksaLabEntity.setNamaPemeriksaan(labEntity.getNamaLab());
                            periksaLabEntity.setFlag("Y");
                            periksaLabEntity.setAction("C");
                            periksaLabEntity.setCreatedWho(userLogin);
                            periksaLabEntity.setCreatedDate(time);
                            periksaLabEntity.setLastUpdateWho(userLogin);
                            periksaLabEntity.setLastUpdate(time);
                            try {
                                periksaLabDao.addAndSave(periksaLabEntity);
                                seqIdPeriksaLab = periksaLabEntity.getIdPeriksaLab();
                            } catch (HibernateException e) {
                                logger.error("[CheckupBoImpl.insertItemPaketToPeriksa] ERROR when saving data periksa lab " + e.getMessage());
                                throw new GeneralBOException("[CheckupBoImpl.insertItemPaketToPeriksa] ERROR when saving data periksa lab " + e.getMessage());
                            }
                        }
                    }

                    List<LabDetail> labDetailList = new ArrayList<>();
                    LabDetail lab = new LabDetail();
                    lab.setIdLabDetail(paketPeriksa.getIdItem());
                    try {
                        labDetailList = labDetailDao.getDataParameterPemeriksaan(lab);
                    } catch (GeneralBOException e) {
                        logger.error(e.getMessage());
                        throw new GeneralBOException("[CheckupBoImpl.insertItemPaketToPeriksa] ERROR when saving data detail periksa lab " + e.getMessage());
                    }
                    if (labDetailList.size() > 0) {
                        lab = labDetailList.get(0);
                        ItSimrsPeriksaLabDetailEntity detailEntity = new ItSimrsPeriksaLabDetailEntity();
                        detailEntity.setIdPeriksaLabDetail(getNextDetailLapId());
                        detailEntity.setIdPeriksaLab(seqIdPeriksaLab);
                        detailEntity.setIdLabDetail(paketPeriksa.getIdItem());
                        detailEntity.setNamaDetailPeriksa(lab.getNamaDetailPeriksa());
                        detailEntity.setKeteranganAcuanL(lab.getKeteranganAcuanL());
                        detailEntity.setKeteranganAcuanP(lab.getKeteranganAcuanP());
                        detailEntity.setSatuan(lab.getSatuan());
                        detailEntity.setTarif(paketPeriksa.getTarif());
                        detailEntity.setFlag("Y");
                        detailEntity.setAction("C");
                        detailEntity.setCreatedWho(userLogin);
                        detailEntity.setCreatedDate(time);
                        detailEntity.setLastUpdateWho(userLogin);
                        detailEntity.setLastUpdate(time);

                        try {
                            periksaLabDetailDao.addAndSave(detailEntity);
                        } catch (HibernateException e) {
                            logger.error("[CheckupBoImpl.insertItemPaketToPeriksa] ERROR when saving data detail periksa lab " + e.getMessage());
                            throw new GeneralBOException("[CheckupBoImpl.insertItemPaketToPeriksa] ERROR when saving data detail periksa lab " + e.getMessage());
                        }
                    }else{
                        throw new GeneralBOException("[CheckupBoImpl.insertItemPaketToPeriksa] ERROR when saving data detail periksa lab ");
                    }
                }
            }

            if ("paket_perusahaan".equalsIgnoreCase(jenisPeriksa)) {

                PaketPeriksa periksa = new PaketPeriksa();
                periksa = paketPasienList.get(0);
                ItSimrsPaketPasienEntity pasienEntity = new ItSimrsPaketPasienEntity();

                try {
                    pasienEntity = paketPasienDao.getById("id", periksa.getIdPaketPasien());

                    if (pasienEntity != null) {

                        pasienEntity.setFlag("N");
                        pasienEntity.setLastUpdateWho(userLogin);
                        pasienEntity.setLastUpdate(time);

                        try {
                            paketPasienDao.updateAndSave(pasienEntity);
                        } catch (HibernateException e) {
                            logger.error("Found Error " + e.getMessage());
                        }
                    }

                } catch (HibernateException e) {
                    logger.error("Found error " + e.getMessage());
                }
            }

            List<MtSimrsDetailPaketEntity> detailPaketList = new ArrayList<>();
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_paket", idPaket);
            hsCriteria.put("flag", "Y");
            try {
                detailPaketList = detailPaketDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("search detail paket, " + e.getMessage());
                throw new GeneralBOException("Error ketika mencari detail paket..!");
            }

            if (detailPaketList.size() > 0) {
                for (MtSimrsDetailPaketEntity detailPaketEntity : detailPaketList) {
                    ItSimrsPelayananPaketEntity paketEntity = new ItSimrsPelayananPaketEntity();
                    paketEntity.setIdPelayananPaket("PPK" + pelayananPaketDao.getNextSeq());
                    paketEntity.setNoCheckup(noCheckup);
                    PaketPeriksa periksa = paketPasienList.get(0);
                    if (detailPaketEntity.getIdPelayanan().equalsIgnoreCase(periksa.getIdPelayanan())) {
                        paketEntity.setIdDetailCheckup(idDetailCheckup);
                        paketEntity.setIsPeriksa("Y");
                    }
                    paketEntity.setIdPelayanan(detailPaketEntity.getIdPelayanan());
                    paketEntity.setUrutan(detailPaketEntity.getUrutan());
                    paketEntity.setIdPaket(detailPaketEntity.getIdPaket());
                    paketEntity.setCreatedDate(time);
                    paketEntity.setCreatedWho(userLogin);
                    paketEntity.setLastUpdate(time);
                    paketEntity.setLastUpdateWho(userLogin);

                    try {
                        pelayananPaketDao.addAndSave(paketEntity);
                    } catch (HibernateException e) {
                        logger.error("Insert Error" + e.getMessage());
                        throw new GeneralBOException("Insert detail paket Error");
                    }
                }
            }
        }
    }

    private String dateFormater(String type) {
        Date date = new Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

    private List<ImSimrsTindakanEntity> getListEntityTindakan(Tindakan bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getListEntityTindakan] Start >>>>>>>");

        List<ImSimrsTindakanEntity> tindakanEntities = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdTindakan() != null) {
                hsCriteria.put("id_tindakan", bean.getIdTindakan());
            }
            if (bean.getIdKategoriTindakan() != null) {
                hsCriteria.put("id_kategori_tindakan", bean.getIdKategoriTindakan());
            }
            if (bean.getFlag() != null) {
                hsCriteria.put("flag", bean.getFlag());
            }
            try {
                tindakanEntities = tindakanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.getListEntityTindakan] ERROR " + e.getMessage());
                throw new GeneralBOException("[CheckupBoImpl.getListEntityTindakan] ERROR " + e.getMessage());
            }

        }
        logger.info("[CheckupBoImpl.getListEntityTindakan] End <<<<<<<");
        return tindakanEntities;

    }

    @Override
    public void saveEdit(HeaderCheckup bean) throws GeneralBOException {

        logger.info("[CheckupBoImpl.saveEdit] Start >>>>>>>");

        if (bean != null) {

            ItSimrsHeaderChekupEntity headerEntity = new ItSimrsHeaderChekupEntity();

            try {
                headerEntity = headerCheckupDao.getById("noCheckup", bean.getNoCheckup());
            } catch (HibernateException e) {
                logger.error("[TeamDokterBoImpl.saveEdit] Error when save edit dokter team ", e);
                throw new GeneralBOException("[TeamDokterBoImpl.savaAdd] Error when save edit dokter team " + e.getMessage());
            }

            if (headerEntity != null) {
                headerEntity.setIdPasien(bean.getIdPasien());
                headerEntity.setNama(bean.getNama());
                headerEntity.setJenisKelamin(bean.getJenisKelamin());
                headerEntity.setNoKtp(bean.getNoKtp());
                headerEntity.setTempatLahir(bean.getTempatLahir());
                headerEntity.setTglLahir(bean.getTglLahir());
                headerEntity.setDesaId(bean.getDesaId());
                headerEntity.setJalan(bean.getJalan());
                headerEntity.setSuku(bean.getSuku());
                headerEntity.setProfesi(bean.getProfesi());
                headerEntity.setNoTelp(bean.getNoTelp());
                headerEntity.setAgama(bean.getAgama());
                headerEntity.setUrlKtp(bean.getUrlKtp());
                headerEntity.setBranchId(bean.getBranchId());
                headerEntity.setFlag("Y");
                headerEntity.setAction("U");
                headerEntity.setLastUpdate(bean.getLastUpdate());
                headerEntity.setLastUpdateWho(bean.getLastUpdateWho());
                headerEntity.setJenisKunjungan(bean.getJenisKunjungan());
                headerEntity.setNamaPenanggung(bean.getNamaPenanggung());
                headerEntity.setHubunganKeluarga(bean.getHubunganKeluarga());

            }

            try {
                headerCheckupDao.updateAndSave(headerEntity);
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.saveEdit] Error When Saving data header checkup" + e.getMessage());
                throw new GeneralBOException("[CheckupBoImpl.saveEdit] Error When Saving data header checkup");
            }

            // saving detail checkup
            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {

                ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();

                try {
                    detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());
                } catch (HibernateException e) {
                    logger.error("[TeamDokterBoImpl.saveEdit] Error when save edit dokter team ", e);
                    throw new GeneralBOException("[TeamDokterBoImpl.savaAdd] Error when save edit dokter team " + e.getMessage());
                }

                if (detailCheckupEntity != null) {
                    detailCheckupEntity.setNoCheckup(headerEntity.getNoCheckup());
                    detailCheckupEntity.setIdPelayanan(bean.getIdPelayanan());
                    detailCheckupEntity.setStatusPeriksa("0");
                    detailCheckupEntity.setFlag("Y");
                    detailCheckupEntity.setAction("U");
                    detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                    detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                }

                try {
                    checkupDetailDao.updateAndSave(detailCheckupEntity);
                } catch (HibernateException e) {
                    logger.error("[CheckupBoImpl.saveEdit] Error When Saving data detail checkup" + e.getMessage());
                    throw new GeneralBOException("[CheckupBoImpl.saveEdit] Error When Saving data detail checkup");
                }

                // saving dokter
                if (bean.getIdDokter() != null && !"".equalsIgnoreCase(bean.getIdDokter()) &&
                        detailCheckupEntity.getIdDetailCheckup() != null && !"".equalsIgnoreCase(detailCheckupEntity.getIdDetailCheckup())) {
                    ItSimrsDokterTeamEntity entityDokter = new ItSimrsDokterTeamEntity();

                    try {
                        entityDokter = dokterTeamDao.getById("idTeamDokter", bean.getIdTeamDokter());
                    } catch (HibernateException e) {
                        logger.error("[TeamDokterBoImpl.saveEdit] Error when save edit dokter team ", e);
                        throw new GeneralBOException("[TeamDokterBoImpl.savaAdd] Error when save edit dokter team " + e.getMessage());
                    }

                    entityDokter.setIdDokter(bean.getIdDokter());
                    entityDokter.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                    entityDokter.setFlag("Y");
                    entityDokter.setAction("U");
                    entityDokter.setLastUpdate(bean.getLastUpdate());
                    entityDokter.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        dokterTeamDao.updateAndSave(entityDokter);
                    } catch (HibernateException e) {
                        logger.error("[CheckupBoImpl.saveTeamDokter] Error when save add dokter team ", e);
                        throw new GeneralBOException("[CheckupBoImpl.saveTeamDokter] Error when save add dokter team " + e.getMessage());
                    }

                }
            }

            logger.info("[CheckupBoImpl.saveEdit] End <<<<<<<");
        }
    }

    private CrudResponse saveTeamDokter(DokterTeam bean) {
        CrudResponse response = new CrudResponse();
        logger.info("[TeamDokterBoImpl.savaAdd] Start >>>>>>>>");

        ItSimrsDokterTeamEntity entity = new ItSimrsDokterTeamEntity();
        String id = getNextTeamDokterId();

        entity.setIdTeamDokter("TDT" + id);
        entity.setIdDokter(bean.getIdDokter());
        entity.setIdDetailCheckup(bean.getIdDetailCheckup());
        entity.setIdPelayanan(bean.getIdPelayanan());
        entity.setJenisDpjp(bean.getJenisDpjp());
        entity.setFlag("Y");
        entity.setAction("C");
        entity.setFlagApprove(bean.getFlagApprove());
        entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        entity.setCreatedWho(CommonUtil.userLogin());
        entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        entity.setLastUpdateWho(CommonUtil.userLogin());

        try {
            dokterTeamDao.addAndSave(entity);
            response.setStatus("success");
            response.setMsg("OKE");
        } catch (HibernateException e) {
            response.setStatus("error");
            response.setMsg("Error" + e.getMessage());
            logger.error("[CheckupBoImpl.saveTeamDokter] Error when save add dokter team ", e);
            throw new GeneralBOException("[CheckupBoImpl.saveTeamDokter] Error when save add dokter team " + e.getMessage());
        }

        logger.info("[CheckupBoImpl.savaAdd] End <<<<<<<<");
        return response;
    }

    public void saveDiagnosa(DiagnosaRawat bean) throws GeneralBOException {
        logger.info("[DiagnosaRawatBoImpl.saveAdd] Start >>>>>>>>>");

        if (bean != null && bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            ItSimrsDiagnosaRawatEntity entity = new ItSimrsDiagnosaRawatEntity();

            String id = getNextIdDiagnosa();
            entity.setIdDiagnosaRawat("DGR" + id);
            entity.setIdDiagnosa(bean.getIdDiagnosa());
            entity.setIdDetailCheckup(bean.getIdDetailCheckup());
            entity.setKeteranganDiagnosa(bean.getKeteranganDiagnosa());
            entity.setJenisDiagnosa(bean.getJenisDiagnosa());
            entity.setTipe(bean.getTipe());
            entity.setFlag("Y");
            entity.setAction("U");
            entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            entity.setCreatedWho(CommonUtil.userLogin());
            entity.setLastUpdateWho(CommonUtil.userLogin());

            try {
                diagnosaRawatDao.addAndSave(entity);
            } catch (HibernateException e) {
                logger.error("[DiagnosaRawatBoImpl.saveAdd] Error when saving diagnosa ", e);
                throw new GeneralBOException("Error when saving diagnosa " + e.getMessage());
            }
        }

        logger.info("[DiagnosaRawatBoImpl.saveAdd] End <<<<<<<<<");
    }

    private String getNextIdDiagnosa() {
        String id = "";
        try {
            id = diagnosaRawatDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[DiagnosaRawatBoImpl.getNextId] Error when get next diagnosa rawat id ", e);
        }
        return id;
    }

    @Override
    public String getNextHeaderId() {
        String id = "";
        try {
            id = headerCheckupDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getNextHeaderId] Error get next seq id " + e.getMessage());
            throw new GeneralBOException("[CheckupBoImpl.getNextHeaderId] Error When Error get next seq id");
        }
        return id;
    }

    @Override
    public List<HeaderCheckup> getListAntrian(String branch, String poli) throws GeneralBOException {
        List<HeaderCheckup> result = new ArrayList<>();

        try {
            result = headerCheckupDao.getListAntrianPasien(branch, poli);
        } catch (HibernateException e) {
            logger.error("[Found Error when search list antrian pasien] " + e);
        }

        return result;
    }

    @Override
    public List<HeaderCheckup> getListPeriksa(String branch, String poli) throws GeneralBOException {
        List<HeaderCheckup> result = new ArrayList<>();

        try {
            result = headerCheckupDao.getListPeriksaPasien(branch, poli);
        } catch (HibernateException e) {
            logger.error("[Found Error when search list antrian pasien] " + e);
        }

        return result;
    }

    @Override
    public List<HeaderCheckup> getListAntrianApotikPeriksa(String branch, String poli) throws
            GeneralBOException {
        List<HeaderCheckup> result = new ArrayList<>();

        try {
            result = headerCheckupDao.getListAntrianObat(branch, poli);
        } catch (HibernateException e) {
            logger.error("[Found Error when search list antrian pasien] " + e);
        }

        return result;
    }

    @Override
    public HeaderCheckup getDataDetailPasien(String idDetailCheckup) throws GeneralBOException {
        HeaderCheckup checkup = new HeaderCheckup();

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                checkup = headerCheckupDao.getDataDetailPasien(idDetailCheckup);
            } catch (HibernateException e) {
                logger.error("Found Erro when sear data pasien " + e.getMessage());
            }
        }

        return checkup;
    }

    private String getNextDetailCheckupId() {
        String id = "";
        try {
            id = checkupDetailDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getNextDetailCheckupId] Error get next seq id " + e.getMessage());
            throw new GeneralBOException("[CheckupBoImpl.getNextDetailCheckupId] Error When Error get next seq id");
        }
        return id;
    }

    private String getNextTeamDokterId() {
        String id = "";
        try {
            id = dokterTeamDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getNextTeamDokterId] Error get next seq id " + e.getMessage());
            throw new GeneralBOException("[CheckupBoImpl.getNextTeamDokterId] Error When Error get next seq id");
        }
        return id;
    }

    private List<ItSimrsDokterTeamEntity> getListEntityTeamDokter(DokterTeam bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getListEntityTeamDokter] Start >>>>>>>>");
        List<ItSimrsDokterTeamEntity> entities = new ArrayList<>();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        try {
            entities = dokterTeamDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getListEntityTeamDokter] Error when get data");
        }

        logger.info("[CheckupBoImpl.getListEntityTeamDokter] End <<<<<<<<");
        return entities;
    }

    @Override
    public HeaderCheckup completeBpjs(String nomorBpjs, String unitId) {
        logger.info("[CheckupBoImpl.completeBpjs] Start >>>>>>>");

        HeaderCheckup finalResult = new HeaderCheckup();
        java.util.Date dt = new java.util.Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = s.format(dt);

        String feature = CommonConstant.BPJS_BASE_URL + CommonConstant.BPJS_SERVICE_VKLAIM + "/Peserta/nokartu/" + nomorBpjs + "/tglSEP/" + tanggal;

        ImBranches resultBranch = null;
        try {
            // Get data from database by ID
            resultBranch = branchDao.getConsSecrBranchById(unitId);
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.completeBpjs] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please inform to your admin...," + e.getMessage());
        }

        if (resultBranch != null) {
            try {
                String result = GET(feature, resultBranch.getConstId(), resultBranch.getSecretKey());
                JSONObject myResponseCheck = new JSONObject(result);
                if (myResponseCheck.isNull("response")) {
                    finalResult = null;
                    JSONObject response = myResponseCheck.getJSONObject("metaData");
                    logger.error("[CheckupBoImpl.completeBpjs] : " + response.getString("message"));
                } else {
                    JSONObject response = myResponseCheck.getJSONObject("response");
                    JSONObject obj = response.getJSONObject("peserta");
                    finalResult.setNoKtp(obj.getString("nik"));
                    finalResult.setNama(obj.getString("nama"));
                    finalResult.setJenisKelamin(obj.getString("sex"));
                    finalResult.setTglLahir(Date.valueOf(obj.get("tglLahir").toString()));
                    String[] tglLahir = obj.getString("tglLahir").split("-");
                    finalResult.setStTglLahir(tglLahir[2] + "-" + tglLahir[1] + "-" + tglLahir[0]);

                    if (obj.has("provUmum")) {
                        JSONObject prov = obj.getJSONObject("provUmum");
                        finalResult.setNamaProvider(prov.getString("nmProvider"));
                    }
                    if (obj.has("statusPeserta")) {
                        JSONObject stsPeserta = obj.getJSONObject("statusPeserta");
                        finalResult.setStatusBpjs(stsPeserta.getString("keterangan"));
                    }
                    if (obj.has("hakKelas")) {
                        JSONObject akses = obj.getJSONObject("hakKelas");
                        finalResult.setKelasPasien(akses.getString("keterangan"));
                        finalResult.setKelasRawat(akses.getString("kode"));
                    }
                    if (obj.has("mr")) {
                        JSONObject mr = obj.getJSONObject("mr");
                        finalResult.setNoTelp(mr.getString("noTelepon"));
                    }
                    if (obj.has("jenisPeserta")) {
                        JSONObject jnsPeserta = obj.getJSONObject("jenisPeserta");
                        finalResult.setProfesi(jnsPeserta.getString("keterangan"));
                    }
                }
            } catch (Exception e) {
                logger.error("[CheckupBoImpl.completeBpjs] Error when get data");
            }
        }


        logger.info("[CheckupBoImpl.completeBpjs] End <<<<<<<");
        return finalResult;
    }


    @Override
    public void updatePenunjang(HeaderCheckup bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.updatePenunjang] Start >>>>>>>>");

        List<ItSimrsHeaderChekupEntity> chekupEntities = null;
        Map hsCriteria = new HashMap();
        hsCriteria.put("no_checkup", bean.getNoCheckup());

        try {
            chekupEntities = headerCheckupDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.updatePenunjang] Error when get data checkup ", e);
            throw new GeneralBOException("[CheckupBoImpl.updatePenunjang]  Error when get data checkup " + e.getMessage());
        }

        if (chekupEntities.size() > 0) {
            ItSimrsHeaderChekupEntity chekupEntity = chekupEntities.get(0);
            chekupEntity.setTinggi(bean.getTinggi());
            chekupEntity.setBerat(bean.getBerat());
            chekupEntity.setLastUpdate(bean.getLastUpdate());
            chekupEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                headerCheckupDao.updateAndSave(chekupEntity);
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.updatePenunjang] Error when update data checkup ", e);
                throw new GeneralBOException("[CheckupBoImpl.updatePenunjang] Error when update data checkup " + e.getMessage());
            }
        }

        logger.info("[CheckupBoImpl.updatePenunjang] End <<<<<<<<");
    }

    @Override
    public List<ItSImrsCheckupAlergiEntity> getListAlergi(String idPasien) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getListAlergi] Start >>>>>>>>");
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_pasien", idPasien);
        List<ItSImrsCheckupAlergiEntity> alergiEntities = null;
        if (idPasien != null && !"".equalsIgnoreCase(idPasien)) {
            try {
                alergiEntities = checkupAlergiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.getListAlergi] Error when get criteria ", e);
                throw new GeneralBOException("[CheckupBoImpl.getListAlergi] Error when get criteria " + e.getMessage());
            }
        }
        logger.info("[CheckupBoImpl.getListAlergi] End <<<<<<<<");
        return alergiEntities;
    }

    @Override
    public void saveAddAlergi(CheckupAlergi bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.saveAddAlergi] Start >>>>>>>>");

        ItSImrsCheckupAlergiEntity alergiEntity = new ItSImrsCheckupAlergiEntity();
        alergiEntity.setNoCheckup(bean.getNoCheckup());
        alergiEntity.setIdAlergi("ALG" + getNextIdAlergi());
        alergiEntity.setAlergi(bean.getAlergi());
        alergiEntity.setFlag("Y");
        alergiEntity.setAction("C");
        alergiEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        alergiEntity.setCreatedWho(CommonUtil.userLogin());
        alergiEntity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        alergiEntity.setLastUpdateWho(CommonUtil.userLogin());
        alergiEntity.setJenis(bean.getJenis());
        alergiEntity.setIdPasien(bean.getIdPasien());

        try {
            checkupAlergiDao.addAndSave(alergiEntity);
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.saveAddAlergi] Error when save Add ", e);
            throw new GeneralBOException("[CheckupBoImpl.saveAddAlergi] Error when save Add " + e.getMessage());
        }

        logger.info("[CheckupBoImpl.saveAddAlergi] End <<<<<<<<");
    }

    @Override
    public void saveEditAlergi(CheckupAlergi bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.saveEditAlergi] Start >>>>>>>>");

        if (bean.getIdAlergi() != null && !"".equalsIgnoreCase(bean.getIdAlergi())) {

            Map hsCriteria = new HashMap();
            hsCriteria.put("id_alergi", bean.getIdAlergi());

            List<ItSImrsCheckupAlergiEntity> alergiEntities = null;
            try {
                alergiEntities = checkupAlergiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.saveEditAlergi] Error when get criteria ", e);
                throw new GeneralBOException("[CheckupBoImpl.saveEditAlergi] Error when get criteria " + e.getMessage());
            }

            if (alergiEntities != null && alergiEntities.size() > 0) {
                ItSImrsCheckupAlergiEntity alergiEntity = alergiEntities.get(0);

                if (bean.getAlergi() != null && !"".equalsIgnoreCase(bean.getAlergi())) {
                    alergiEntity.setAlergi(bean.getAlergi());
                }
                if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                    alergiEntity.setFlag(bean.getFlag());
                    alergiEntity.setAction("U");
                }

                if (bean.getJenis() != null && !"".equalsIgnoreCase(bean.getJenis())) {
                    alergiEntity.setJenis(bean.getJenis());
                }

                alergiEntity.setLastUpdate(bean.getLastUpdate());
                alergiEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    checkupAlergiDao.updateAndSave(alergiEntity);
                } catch (HibernateException e) {
                    logger.error("[CheckupBoImpl.saveEditAlergi] Error when save update ", e);
                    throw new GeneralBOException("[CheckupBoImpl.saveEditAlergi] Error when save update " + e.getMessage());
                }
            }
        }

        logger.info("[CheckupBoImpl.saveEditAlergi] End <<<<<<<<");
    }

    @Override
    public AlertPasien getAlertPasien(String idPasien, String branchId) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getAlertPasien] Start >>>>>>>>");

        AlertPasien alertPasien = new AlertPasien();

        try {
            alertPasien = headerCheckupDao.getAlertPasien(idPasien, branchId);
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getAlertPasien] Error when get alert pasien ", e);
            throw new GeneralBOException("[CheckupBoImpl.getAlertPasien] Error when alert pasien " + e.getMessage());
        }

        List<String> listAlergi = new ArrayList<>();
        if (alertPasien != null) {
            List<ItSImrsCheckupAlergiEntity> alergiEntities = getListAlergi(alertPasien.getNoCheckup());
            if (alergiEntities != null && alergiEntities.size() > 0) {
                for (ItSImrsCheckupAlergiEntity alergiEntity : alergiEntities) {
                    listAlergi.add(alergiEntity.getAlergi());
                }
            }
            alertPasien.setListOfAlergi(listAlergi);
        }

        logger.info("[CheckupBoImpl.getAlertPasien] End <<<<<<<<");
        return alertPasien;
    }

    @Override
    public List<AlertPasien> listOfRekamMedic(HeaderCheckup bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.listOfRekamMedic] Start >>>>>>>>");

        List<ItSimrsHeaderChekupEntity> headerChekupEntities = null;
        Map hsCriteria = new HashMap();
        if (bean.getNoCheckup() != null) {
            hsCriteria.put("no_checkup", bean.getNoCheckup());
        } else {
            hsCriteria.put("id_pasien", bean.getIdPasien());
            hsCriteria.put("tgl_keluar_not_null", "Y");
        }

        try {
            headerChekupEntities = headerCheckupDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.listOfRekamMedic] Error when get checkup criteria ", e);
            throw new GeneralBOException("[CheckupBoImpl.listOfRekamMedic] Error when get checkup criteria " + e.getMessage());
        }

        List<AlertPasien> alertPasienList = new ArrayList<>();
        if (headerChekupEntities != null && headerChekupEntities.size() > 0) {
            AlertPasien alertPasien;
            for (ItSimrsHeaderChekupEntity headerChekupEntity : headerChekupEntities) {
                alertPasien = new AlertPasien();
                alertPasien.setNoCheckup(headerChekupEntity.getNoCheckup());
                alertPasien.setNamaPasien(headerChekupEntity.getNama());

                AlertPasien alertPasienDiagnosa = new AlertPasien();
                try {
                    alertPasienDiagnosa = headerCheckupDao.gelLastDiagnosa(headerChekupEntity.getNoCheckup(), "");
                } catch (HibernateException e) {
                    logger.error("[CheckupBoImpl.listOfRekamMedic] Error when get diagnosa ", e);
                    throw new GeneralBOException("[CheckupBoImpl.listOfRekamMedic] Error when get diagnosa " + e.getMessage());
                }

                if (alertPasienDiagnosa != null) {
                    alertPasien.setDiagnosa(alertPasienDiagnosa.getDiagnosa());
                }

                alertPasien.setStTglMasuk(alertPasienDiagnosa.getStTglMasuk());
                alertPasien.setStTglKeluar(alertPasienDiagnosa.getStTglKeluar());
                alertPasienList.add(alertPasien);
            }
        }

        logger.info("[CheckupBoImpl.listOfRekamMedic] End <<<<<<<<");
        return alertPasienList;
    }

    @Override
    public ItSimrsPemeriksaanFisikEntity getEntityPemeriksaanFisikByNoCheckup(String noCheckup) throws
            GeneralBOException {
        logger.info("[CheckupBoImpl.getEntityPemeriksaanFisikByNoCheckup] Start >>>>>>>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("no_checkup", noCheckup);

        List<ItSimrsPemeriksaanFisikEntity> pemeriksaanFisikEntities = new ArrayList<>();
        try {
            pemeriksaanFisikEntities = pemeriksaanFisikDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getEntityPemeriksaanFisikByNoCheckup] ERROR ", e);
            throw new GeneralBOException("[CheckupBoImpl.getEntityPemeriksaanFisikByNoCheckup] ERROR " + e.getMessage());
        }

        ItSimrsPemeriksaanFisikEntity pemeriksaanFisikEntity = new ItSimrsPemeriksaanFisikEntity();
        if (pemeriksaanFisikEntities.size() > 0) {
            pemeriksaanFisikEntity = pemeriksaanFisikEntities.get(0);
        }
        logger.info("[CheckupBoImpl.getEntityPemeriksaanFisikByNoCheckup] End <<<<<<<<");
        return pemeriksaanFisikEntity;
    }

    @Override
    public void savePemeriksaanFisik(PemeriksaanFisik bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.savePemeriksaanFisik] Start >>>>>>>>");

        ItSimrsPemeriksaanFisikEntity entity = getEntityPemeriksaanFisikByNoCheckup(bean.getNoCheckup());
        if (entity.getId() != null && !"".equalsIgnoreCase(entity.getId())) {

            entity.setKepala(bean.getKepala());
            entity.setMata(bean.getMata());
            entity.setLeher(bean.getLeher());
            entity.setThorak(bean.getThorak());
            entity.setThorakChor(bean.getThorakChor());
            entity.setThorakPulmo(bean.getThorakPulmo());
            entity.setAbdoman(bean.getAbdoman());
            entity.setExtrimitas(bean.getExtrimitas());
            entity.setTinggiBadan(bean.getTinggiBadan());
            entity.setBeratBadan(bean.getBeratBadan());
            entity.setNadi(bean.getNadi());
            entity.setRespirationRate(bean.getRespirationRate());
            entity.setTekananDarah(bean.getTekananDarah());
            entity.setSuhu(bean.getSuhu());
            entity.setTriase(bean.getTriase());

            entity.setFlag(bean.getFlag());
            entity.setAction("U");
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                pemeriksaanFisikDao.updateAndSave(entity);
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.savePemeriksaanFisik] Error when update ", e);
                throw new GeneralBOException("[CheckupBoImpl.updatePenunjang] Error when update " + e.getMessage());
            }

        } else {

            DateFormat df = new SimpleDateFormat("yyMM"); // Just the year, with 2 digits
            String formattedDate = df.format(Calendar.getInstance().getTime());

            entity = new ItSimrsPemeriksaanFisikEntity();
            entity.setId(formattedDate + "F" + getNextIdPemeriksaanFisik());
            entity.setNoCheckup(bean.getNoCheckup());
            entity.setKepala(bean.getKepala());
            entity.setMata(bean.getMata());
            entity.setLeher(bean.getLeher());
            entity.setThorak(bean.getThorak());
            entity.setThorakChor(bean.getThorakChor());
            entity.setThorakPulmo(bean.getThorakPulmo());
            entity.setAbdoman(bean.getAbdoman());
            entity.setExtrimitas(bean.getExtrimitas());
            entity.setTinggiBadan(bean.getTinggiBadan());
            entity.setBeratBadan(bean.getBeratBadan());
            entity.setNadi(bean.getNadi());
            entity.setRespirationRate(bean.getRespirationRate());
            entity.setTekananDarah(bean.getTekananDarah());
            entity.setSuhu(bean.getSuhu());
            entity.setTriase(bean.getTriase());

            entity.setFlag(bean.getFlag());
            entity.setAction(bean.getAction());
            entity.setCreatedDate(bean.getCreatedDate());
            entity.setLastUpdate(bean.getLastUpdate());
            entity.setCreatedWho(bean.getCreatedWho());
            entity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                pemeriksaanFisikDao.addAndSave(entity);
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.savePemeriksaanFisik] Error when insert ", e);
                throw new GeneralBOException("[CheckupBoImpl.savePemeriksaanFisik] Error when insert " + e.getMessage());
            }
        }

        logger.info("[CheckupBoImpl.savePemeriksaanFisik] End <<<<<<<<");
    }

    @Override
    public ResikoJatuhResponse getResikojatuh(ResikoJatuh bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.savePemeriksaanFisik] Start >>>>>>>>");

        ResikoJatuhResponse response = new ResikoJatuhResponse();

        Map hsCriteria = new HashMap();
        hsCriteria.put("no_checkup", bean.getNoCheckup());
        List<ItSImrsResikoJatuhEntity> resikoJatuhEntities = resikoJatuhDao.getByCriteria(hsCriteria);
        if (resikoJatuhEntities.size() > 0) {
            response.setStatus("success");
            response.setResikoJatuhEntityList(resikoJatuhEntities);
        } else {


            hsCriteria = new HashMap();
            hsCriteria.put("umur", bean.getUmur());
            List<ImSimrsKategoriResikoJatuhEntity> kategoriResikoJatuhEntities = kategoriResikoJatuhDao.getByCriteria(hsCriteria);
            if (kategoriResikoJatuhEntities.size() > 0) {

                hsCriteria = new HashMap();
                hsCriteria.put("id_kategori", kategoriResikoJatuhEntities.get(0).getIdKategori());
                List<ImSimrsParameterResikoJatuhEntity> parameterResikoJatuhEntities = parameterResikoJatuhDao.getByCriteria(hsCriteria);
                if (parameterResikoJatuhEntities.size() > 0) {

                    resikoJatuhEntities = new ArrayList<>();
                    ItSImrsResikoJatuhEntity resikoJatuhEntity;
                    for (ImSimrsParameterResikoJatuhEntity parameter : parameterResikoJatuhEntities) {
                        resikoJatuhEntity = new ItSImrsResikoJatuhEntity();
                        resikoJatuhEntity.setIdParameter(parameter.getIdParameter());
                        resikoJatuhEntity.setNamaParameter(parameter.getNamaParameter());
                        resikoJatuhEntity.setIdKategori(parameter.getIdKategori());
                        resikoJatuhEntities.add(resikoJatuhEntity);
                    }
                    response.setStatus("success");
                    response.setResikoJatuhEntityList(resikoJatuhEntities);
                }
            }
        }

        logger.info("[CheckupBoImpl.savePemeriksaanFisik] End <<<<<<<<");
        return response;
    }

    @Override
    public List<ImSimrsSkorResikoJatuhEntity> getListSkorResikoByIdParameter(String id) throws
            GeneralBOException {
        logger.info("[CheckupBoImpl.getListSkorResikoById] Start >>>>>>>>");

        List<ImSimrsSkorResikoJatuhEntity> skorResikoJatuhEntities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_parameter", id);

        try {
            skorResikoJatuhEntities = skorResikoJatuhDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getListSkorResikoById] ERROR " + e.getMessage());
        }

        logger.info("[CheckupBoImpl.getListSkorResikoById] End <<<<<<<<");
        return skorResikoJatuhEntities;
    }

    @Override
    public List<ItSimrsRencanaRawatEntity> getListRencanaRawat(String noCheckup, String idDetail, String
            kategori) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getListRencanaRawat] Start >>>>>>>>");

        Map hsCriteria = new HashMap();
        if (!"".equalsIgnoreCase(noCheckup))
            hsCriteria.put("no_checkup", noCheckup);
        if (!"".equalsIgnoreCase(idDetail))
            hsCriteria.put("id_detail_checkup", idDetail);
        if (!"".equalsIgnoreCase(kategori))
            hsCriteria.put("id_kategori", kategori);

        List<ItSimrsRencanaRawatEntity> rencanaRawatEntities = rencanaRawatDao.getByCriteria(hsCriteria);
        if (rencanaRawatEntities.size() == 0) {
            rencanaRawatEntities = new ArrayList<>();

            hsCriteria = new HashMap();
            hsCriteria.put("id_kategori", kategori);

            List<ImSimrsParameterRencanaRawatEntity> parameterRencanaRawatEntities = parameterRencanaRawatDao.getByCriteria(hsCriteria);
            if (parameterRencanaRawatEntities.size() > 0) {

                ItSimrsRencanaRawatEntity rencanaRawatEntity;
                for (ImSimrsParameterRencanaRawatEntity parameterRencanaRawatEntity : parameterRencanaRawatEntities) {
                    rencanaRawatEntity = new ItSimrsRencanaRawatEntity();
                    rencanaRawatEntity.setNamaParameter(parameterRencanaRawatEntity.getNamaParameter());
                    rencanaRawatEntity.setIdParameter(parameterRencanaRawatEntity.getIdParameter());
                    rencanaRawatEntities.add(rencanaRawatEntity);
                }
            }
        }

        logger.info("[CheckupBoImpl.getListRencanaRawat] End <<<<<<<<");
        return rencanaRawatEntities;
    }

    @Override
    public void saveRencanaRawat(String noCheckup, String
            idDetail, List<ItSimrsRencanaRawatEntity> rencanaRawats) throws GeneralBOException {
        logger.info("[CheckupBoImpl.saveRencanaRawat] Start >>>>>>>>");

        for (ItSimrsRencanaRawatEntity rencana : rencanaRawats) {

            Map hsCriteria = new HashMap();
            hsCriteria.put("no_checkup", noCheckup);
            hsCriteria.put("id_detail_checkup", idDetail);
            hsCriteria.put("id_parameter", rencana.getIdParameter());

            List<ItSimrsRencanaRawatEntity> rencanaRawatEntities = rencanaRawatDao.getByCriteria(hsCriteria);
            if (rencanaRawatEntities.size() > 0) {
                ItSimrsRencanaRawatEntity rencanaRawatEntity = rencanaRawatEntities.get(0);
                rencanaRawatEntity.setCheck(rencana.getCheck());
                rencanaRawatEntity.setAction("U");
                rencanaRawatEntity.setLastUpdate(rencana.getLastUpdate());
                rencanaRawatEntity.setLastUpdateWho(rencana.getLastUpdateWho());

                try {
                    rencanaRawatDao.updateAndSave(rencanaRawatEntity);
                } catch (HibernateException e) {
                    logger.error("[CheckupBoImpl.saveRencanaRawat] ERROR " + e.getMessage());
                    throw new GeneralBOException("[CheckupBoImpl.saveRencanaRawat] ERROR " + e.getMessage());
                }
            } else {
                ItSimrsRencanaRawatEntity rencanaRawatEntity = new ItSimrsRencanaRawatEntity();
                rencanaRawatEntity.setIdRencana("RCN" + rencanaRawatDao.getNextSeq());
                rencanaRawatEntity.setIdParameter(rencana.getIdParameter());
                rencanaRawatEntity.setNamaParameter(rencana.getNamaParameter());
                rencanaRawatEntity.setCheck(rencana.getCheck());
                rencanaRawatEntity.setNoCheckup(noCheckup);
                rencanaRawatEntity.setIdDetailCheckup(idDetail);
                rencanaRawatEntity.setFlag(rencana.getFlag());
                rencanaRawatEntity.setAction(rencana.getAction());
                rencanaRawatEntity.setCreatedDate(rencana.getCreatedDate());
                rencanaRawatEntity.setCreatedWho(rencana.getCreatedWho());
                rencanaRawatEntity.setLastUpdate(rencana.getLastUpdate());
                rencanaRawatEntity.setLastUpdateWho(rencana.getLastUpdateWho());

                try {
                    rencanaRawatDao.addAndSave(rencanaRawatEntity);
                } catch (HibernateException e) {
                    logger.error("[CheckupBoImpl.saveRencanaRawat] ERROR " + e.getMessage());
                    throw new GeneralBOException("[CheckupBoImpl.saveRencanaRawat] ERROR " + e.getMessage());
                }
            }
        }

        logger.info("[CheckupBoImpl.saveRencanaRawat] End <<<<<<<<");
    }

    @Override
    public void saveResikoJatuh(String noCheckup, List<ItSImrsResikoJatuhEntity> resikoJatuhList) throws
            GeneralBOException {
        logger.info("[CheckupBoImpl.saveResikoJatuh] Start >>>>>>>>");

        for (ItSImrsResikoJatuhEntity resikoJatuh : resikoJatuhList) {
            Map hsCriteria = new HashMap();
            hsCriteria.put("no_checkup", noCheckup);
            hsCriteria.put("id_parameter", resikoJatuh.getIdParameter());
            hsCriteria.put("id_kategori", resikoJatuh.getIdParameter());
            List<ItSImrsResikoJatuhEntity> resikoJatuhEntities = resikoJatuhDao.getByCriteria(hsCriteria);
            if (resikoJatuhEntities.size() > 0) {
                ItSImrsResikoJatuhEntity resikoJatuhEntity = resikoJatuhEntities.get(0);

                resikoJatuhEntity.setSkor(resikoJatuh.getSkor());
                resikoJatuhEntity.setAction("U");
                resikoJatuhEntity.setLastUpdate(resikoJatuh.getLastUpdate());
                resikoJatuhEntity.setLastUpdateWho(resikoJatuh.getLastUpdateWho());
                try {
                    resikoJatuhDao.updateAndSave(resikoJatuhEntity);
                } catch (HibernateException e) {
                    logger.error("[CheckupBoImpl.saveResikoJatuh] ERROR " + e.getMessage());
                    throw new GeneralBOException("[CheckupBoImpl.saveResikoJatuh] ERROR " + e.getMessage());
                }
            } else {
                ItSImrsResikoJatuhEntity resikoJatuhEntity = new ItSImrsResikoJatuhEntity();
                resikoJatuhEntity.setId("RJH" + getNextIdResikoJatuh());
                resikoJatuhEntity.setIdParameter(resikoJatuh.getIdParameter());
                resikoJatuhEntity.setNamaParameter(resikoJatuh.getNamaParameter());
                resikoJatuhEntity.setNoCheckup(noCheckup);
                resikoJatuhEntity.setIdKategori(resikoJatuh.getIdKategori());
                resikoJatuhEntity.setSkor(resikoJatuh.getSkor());
                resikoJatuhEntity.setFlag(resikoJatuh.getFlag());
                resikoJatuhEntity.setAction(resikoJatuh.getAction());
                resikoJatuhEntity.setCreatedDate(resikoJatuh.getCreatedDate());
                resikoJatuhEntity.setCreatedWho(resikoJatuh.getCreatedWho());
                resikoJatuhEntity.setLastUpdate(resikoJatuh.getLastUpdate());
                resikoJatuhEntity.setLastUpdateWho(resikoJatuh.getLastUpdateWho());

                try {
                    resikoJatuhDao.addAndSave(resikoJatuhEntity);
                } catch (HibernateException e) {
                    logger.error("[CheckupBoImpl.saveResikoJatuh] ERROR " + e.getMessage());
                    throw new GeneralBOException("[CheckupBoImpl.saveResikoJatuh] ERROR " + e.getMessage());
                }
            }
        }
        logger.info("[CheckupBoImpl.saveResikoJatuh] End <<<<<<<<");
    }

    @Override
    public String getSumResikoJatuh(String noCheckup, String idKategori) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getSumResikoJatuh] Start >>>>>>>>");

        String sum = "";
        try {
            sum = resikoJatuhDao.getSumOfResikoJatuh(noCheckup, idKategori);
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.saveResikoJatuh] ERROR " + e.getMessage());
            throw new GeneralBOException("[CheckupBoImpl.saveResikoJatuh] ERROR " + e.getMessage());
        }

        logger.info("[CheckupBoImpl.getSumResikoJatuh] End <<<<<<<<");
        return sum;
    }

    @Override
    public ImSimrsKategoriResikoJatuhEntity getKategoriResikoJatuh(Integer umur) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getKategoriResikoJatuh] Start >>>>>>>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("umur", umur);

        List<ImSimrsKategoriResikoJatuhEntity> resikoJatuhEntities = new ArrayList<>();

        try {
            resikoJatuhEntities = kategoriResikoJatuhDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getKategoriResikoJatuh] ERROR " + e.getMessage());
            throw new GeneralBOException("[CheckupBoImpl.getKategoriResikoJatuh] ERROR " + e.getMessage());
        }

        ImSimrsKategoriResikoJatuhEntity kategoriResikoJatuhEntity = new ImSimrsKategoriResikoJatuhEntity();
        if (resikoJatuhEntities.size() > 0) {
            kategoriResikoJatuhEntity = resikoJatuhEntities.get(0);
        }
        logger.info("[CheckupBoImpl.getKategoriResikoJatuh] End <<<<<<<<");
        return kategoriResikoJatuhEntity;
    }

    @Override
    public ItSimrsDataPsikososialEntity getDataPsikososial(String noCheckup) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getDataPsikososial] Start >>>>>>>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("no_checkup", noCheckup);

        List<ItSimrsDataPsikososialEntity> dataPsikososialEntities = new ArrayList<>();
        try {
            dataPsikososialEntities = psikososialDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getDataPsikososial] ERROR " + e.getMessage());
            throw new GeneralBOException("[CheckupBoImpl.getDataPsikososial] ERROR " + e.getMessage());
        }

        ItSimrsDataPsikososialEntity dataPsikososialEntity = new ItSimrsDataPsikososialEntity();
        if (dataPsikososialEntities.size() > 0) {
            dataPsikososialEntity = dataPsikososialEntities.get(0);
        }
        logger.info("[CheckupBoImpl.getDataPsikososial] End <<<<<<<<");
        return dataPsikososialEntity;
    }

    @Override
    public void saveDataPsikososial(String noCheckup, ItSimrsDataPsikososialEntity psikososial) throws
            GeneralBOException {
        logger.info("[CheckupBoImpl.saveDataPsikososial] Start >>>>>>>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("no_checkup", noCheckup);

        List<ItSimrsDataPsikososialEntity> dataPsikososialEntities = psikososialDao.getByCriteria(hsCriteria);
        if (dataPsikososialEntities.size() > 0) {
            ItSimrsDataPsikososialEntity dataPsikososialEntity = dataPsikososialEntities.get(0);

            dataPsikososialEntity.setNoCheckup(psikososial.getNoCheckup());
            dataPsikososialEntity.setKomunikasi(psikososial.getKomunikasi());
            dataPsikososialEntity.setKemampuanBicara(psikososial.getKemampuanBicara());
            dataPsikososialEntity.setKonsepDiri(psikososial.getKonsepDiri());
            dataPsikososialEntity.setPernahDirawat(psikososial.getPernahDirawat());
            dataPsikososialEntity.setTahuTentangSakitNya(psikososial.getTahuTentangSakitNya());
            dataPsikososialEntity.setObatDariRumah(psikososial.getObatDariRumah());
            dataPsikososialEntity.setNyeri(psikososial.getNyeri());
            dataPsikososialEntity.setIntensitasNyeri(psikososial.getIntensitasNyeri());
            dataPsikososialEntity.setJenisIntensitasNyeri(psikososial.getJenisIntensitasNyeri());
            dataPsikososialEntity.setNumericRatingScale(psikososial.getNumericRatingScale());
            dataPsikososialEntity.setWongBakerPainScale(psikososial.getWongBakerPainScale());

            dataPsikososialEntity.setAction("U");
            dataPsikososialEntity.setLastUpdate(psikososial.getLastUpdate());
            dataPsikososialEntity.setLastUpdateWho(psikososial.getLastUpdateWho());

            try {
                psikososialDao.updateAndSave(dataPsikososialEntity);
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.saveDataPsikososial] ERROR " + e.getMessage());
                throw new GeneralBOException("[CheckupBoImpl.saveDataPsikososial] ERROR " + e.getMessage());
            }
        } else {

            ItSimrsDataPsikososialEntity dataPsikososialEntity = new ItSimrsDataPsikososialEntity();
            dataPsikososialEntity.setId("PS" + getNextPsikososialId());
            dataPsikososialEntity.setNoCheckup(psikososial.getNoCheckup());
            dataPsikososialEntity.setKomunikasi(psikososial.getKomunikasi());
            dataPsikososialEntity.setKemampuanBicara(psikososial.getKemampuanBicara());
            dataPsikososialEntity.setTahuTentangSakitNya(psikososial.getTahuTentangSakitNya());
            dataPsikososialEntity.setObatDariRumah(psikososial.getObatDariRumah());
            dataPsikososialEntity.setNyeri(psikososial.getNyeri());
            dataPsikososialEntity.setIntensitasNyeri(psikososial.getIntensitasNyeri());
            dataPsikososialEntity.setJenisIntensitasNyeri(psikososial.getJenisIntensitasNyeri());
            dataPsikososialEntity.setNumericRatingScale(psikososial.getNumericRatingScale());
            dataPsikososialEntity.setWongBakerPainScale(psikososial.getWongBakerPainScale());

            dataPsikososialEntity.setFlag("Y");
            dataPsikososialEntity.setAction("C");
            dataPsikososialEntity.setCreatedDate(psikososial.getCreatedDate());
            dataPsikososialEntity.setCreatedWho(psikososial.getCreatedWho());
            dataPsikososialEntity.setLastUpdate(psikososial.getLastUpdate());
            dataPsikososialEntity.setLastUpdateWho(psikososial.getLastUpdateWho());

            try {
                psikososialDao.addAndSave(dataPsikososialEntity);
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.saveDataPsikososial] ERROR " + e.getMessage());
                throw new GeneralBOException("[CheckupBoImpl.saveDataPsikososial] ERROR " + e.getMessage());
            }
        }

        logger.info("[CheckupBoImpl.saveDataPsikososial] End <<<<<<<<");
    }

    @Override
    public List<ItSimrsTranfusiEntity> getListTranfusi(String noCheckup) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getListTranfusi] Start >>>>>>>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("no_checkup", noCheckup);

        List<ItSimrsTranfusiEntity> tranfusiEntities = new ArrayList<>();
        try {
            tranfusiEntities = tranfusiDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getListTranfusi] ERROR " + e.getMessage());
            throw new GeneralBOException("[CheckupBoImpl.getListTranfusi] ERROR " + e.getMessage());
        }

        logger.info("[CheckupBoImpl.getListTranfusi] End <<<<<<<<");
        return tranfusiEntities;
    }

    @Override
    public List<ItSImrsPatrusEntity> getDataPatrus(String noCheckup) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getDataPatrus] Start >>>>>>>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("no_checkup", noCheckup);

        ItSImrsPatrusEntity patrusEntity = new ItSImrsPatrusEntity();
        List<ItSImrsPatrusEntity> patrusEntities = new ArrayList<>();
        try {
            patrusEntities = patrusDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getDataPatrus] ERROR " + e.getMessage());
            throw new GeneralBOException("[CheckupBoImpl.getDataPatrus] ERROR " + e.getMessage());
        }

        logger.info("[CheckupBoImpl.getDataPatrus] End <<<<<<<<");
        return patrusEntities;
    }

    @Override
    public List<ItSimrsRekonsiliasiObatEntity> getListRekonsiliasiObat(String noCheckup) throws
            GeneralBOException {
        logger.info("[CheckupBoImpl.getListRekonsiliasiObat] Start >>>>>>>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("no_checkup", noCheckup);

        List<ItSimrsRekonsiliasiObatEntity> rekonsiliasiObatEntities = new ArrayList<>();
        try {
            rekonsiliasiObatEntities = rekonsiliasiObatDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getListRekonsiliasiObat] ERROR " + e.getMessage());
            throw new GeneralBOException("[CheckupBoImpl.getListRekonsiliasiObat] ERROR " + e.getMessage());
        }

        logger.info("[CheckupBoImpl.getDataPatrus] End <<<<<<<<");
        return rekonsiliasiObatEntities;
    }

    @Override
    public void saveRekonObat(String noCheckup, ItSimrsRekonsiliasiObatEntity obatEntity) throws
            GeneralBOException {

        if (obatEntity.getNoCheckup() != null && !"".equalsIgnoreCase(obatEntity.getNoCheckup())) {
            obatEntity.setId("RKN" + getIdRekonsiliasi());
            try {
                rekonsiliasiObatDao.addAndSave(obatEntity);
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.saveRekonObat] ERROR " + e.getMessage());
                throw new GeneralBOException("[CheckupBoImpl.saveRekonObat] ERROR " + e.getMessage());
            }
        }
    }

    @Override
    public CrudResponse savePatrus(ItSImrsPatrusEntity bean) {

        CrudResponse response = new CrudResponse();
        if (bean.getKetPatrus() != null && !"".equalsIgnoreCase(bean.getKetPatrus())) {
            bean.setId("PTR" + getIdPatrus());
            try {
                patrusDao.addAndSave(bean);
                response.setStatus("success");
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.savePatrus] ERROR " + e.getMessage());
                response.setStatus("error");
                response.setMsg("[CheckupBoImpl.savePatrus] ERROR " + e.getMessage());
            }
        }

        return response;
    }

    @Override
    public CrudResponse saveTranfusi(ItSimrsTranfusiEntity bean) {
        CrudResponse response = new CrudResponse();

        bean.setId("TFS" + getIdPatrus());
        try {
            tranfusiDao.addAndSave(bean);
            response.setStatus("success");
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.saveTranfusi] ERROR " + e.getMessage());
            response.setStatus("error");
            response.setMsg("[CheckupBoImpl.saveTranfusi] ERROR " + e.getMessage());
        }

        return response;
    }

    @Override
    public RingkasanKeluarMasukRs getRingkasanKeluarMasuk(String noCheckup, String kategori) {

        Map hsCriteria = new HashMap();
        hsCriteria.put("no_checkup", noCheckup);

        RingkasanKeluarMasukRs ringkasan = new RingkasanKeluarMasukRs();
        if ("masuk".equalsIgnoreCase(kategori)) {
            List<ItSimrsHeaderChekupEntity> headerChekupEntities = headerCheckupDao.getByCriteria(hsCriteria);
            if (headerChekupEntities.size() > 0) {
                ItSimrsHeaderChekupEntity checkupEntity = new ItSimrsHeaderChekupEntity();
                ringkasan = headerCheckupDao.getRingkasanMasukRs(noCheckup);
//                ringkasan.setRujukan(checkupEntity.getRujuk());
//                ringkasan.setKetRujukan(checkupEntity.getKetRujukan());
            }
        }
        if ("keluar".equalsIgnoreCase(kategori)) {
            ringkasan = headerCheckupDao.getRingkasanKeluarRs(noCheckup);
            if (ringkasan.getIdDetailCheckup() != null) {
                hsCriteria = new HashMap();
                hsCriteria.put("id_detail_checkup", ringkasan.getIdDetailCheckup());
                List<ItSimrsDokterTeamEntity> dokterTeamEntities = dokterTeamDao.getByCriteria(hsCriteria);
                if (dokterTeamEntities.size() > 0) {

                    Dokter dokter;
                    List<Dokter> dokterList = new ArrayList<>();
                    for (ItSimrsDokterTeamEntity entity : dokterTeamEntities) {

                        hsCriteria = new HashMap();
                        hsCriteria.put("id_dokter", entity.getIdDokter());
                        List<ImSimrsDokterEntity> dokterEntities = dokterDao.getByCriteria(hsCriteria);
                        if (dokterEntities.size() > 0) {
                            dokter = new Dokter();
                            ImSimrsDokterEntity dokterEntity = dokterEntities.get(0);
                            dokter.setNamaDokter(dokterEntity.getNamaDokter());
                            dokter.setIdDokter(dokterEntity.getIdDokter());
                            dokterList.add(dokter);
                        }
                    }
                    ringkasan.setDokterList(dokterList);
                }
            }
        }
        return ringkasan;
    }

    @Override
    public List<TindakanRawat> getListTindakan(String noCheckup, String kategori) {

        List<TindakanRawat> resultTindakans = new ArrayList<>();
        List<String> idDetailCheckups = headerCheckupDao.listOfIdDetailCheckupByTipePelayanan(noCheckup, kategori);
        if (idDetailCheckups.size() > 0) {
            for (String idDetail : idDetailCheckups) {
                List<ItSimrsTindakanRawatEntity> tindakanRawatEntities = new ArrayList<>();

                Map hsCriteria = new HashMap();
                hsCriteria.put("id_detail_checkup", idDetail);
                hsCriteria.put("approve_flag", "Y");

                tindakanRawatEntities = tindakanRawatDao.getByCriteria(hsCriteria);
                if (tindakanRawatEntities.size() > 0) {
                    TindakanRawat tindakanRawat;
                    for (ItSimrsTindakanRawatEntity tindakanRawatEntity : tindakanRawatEntities) {
                        tindakanRawat = new TindakanRawat();
                        tindakanRawat.setNamaTindakan(tindakanRawatEntity.getNamaTindakan());
                        tindakanRawat.setCreatedDate(tindakanRawatEntity.getCreatedDate());
                        tindakanRawat.setCreatedWho(tindakanRawatEntity.getCreatedWho());
                        tindakanRawat.setStDate(tindakanRawatEntity.getCreatedDate().toString());
                        resultTindakans.add(tindakanRawat);
                    }
                }
            }
        }
        return resultTindakans;
    }

    @Override
    public List<RekamMedicLama> getListRekamMedicLama(String idPasien) {
        List<RekamMedicLama> rekamMedicLamas = new ArrayList<>();
        if (!"".equalsIgnoreCase(idPasien)) {
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_pasien", idPasien);
            List<ImSImrsRekamMedicLamaEntity> rekamMedicLamaEntities = rekamMedicLamaDao.getByCriteria(hsCriteria);
            if (rekamMedicLamaEntities.size() > 0) {
                RekamMedicLama rekamMedicLama;
                for (ImSImrsRekamMedicLamaEntity rekamMedicLamaEntity : rekamMedicLamaEntities) {
                    rekamMedicLama = new RekamMedicLama();
                    rekamMedicLama.setId(rekamMedicLamaEntity.getId());
                    rekamMedicLama.setStDate(rekamMedicLamaEntity.getCreatedDate().toString());
                    rekamMedicLama.setCreatedWho(rekamMedicLamaEntity.getCreatedWho());
                    rekamMedicLamas.add(rekamMedicLama);
                }
            }
        }
        return rekamMedicLamas;
    }

    @Override
    public List<RekamMedicLama> getListUploadRekamMedicLama(String headId) {
        List<RekamMedicLama> rekamMedicLamas = new ArrayList<>();
        if (!"".equalsIgnoreCase(headId)) {
            Map hsCriteria = new HashMap();
            hsCriteria.put("head_id", headId);
            List<ImSimrsUploadRekamMedicLamaEntity> uploads = uploadRekamMedicLamaDao.getByCriteria(hsCriteria);
            if (uploads.size() > 0) {
                RekamMedicLama rekamMedicLama;
                for (ImSimrsUploadRekamMedicLamaEntity rekamMedicLamaEntity : uploads) {
                    rekamMedicLama = new RekamMedicLama();
                    rekamMedicLama.setId(rekamMedicLamaEntity.getId());
                    rekamMedicLama.setUrlImg(CommonConstant.EXTERNAL_IMG_URI + File.separator + CommonConstant.URL_IMG_RM + File.separator + rekamMedicLamaEntity.getUrlImg());
                    rekamMedicLama.setStDate(rekamMedicLamaEntity.getCreatedDate().toString());
                    rekamMedicLama.setCreatedWho(rekamMedicLamaEntity.getCreatedWho());
                    rekamMedicLamas.add(rekamMedicLama);
                }
            }
        }
        return rekamMedicLamas;
    }

    public List<ObatKronis> findRiwayatKronis(String idPasien) throws GeneralBOException {

        List<ObatKronis> obatKronisList = new ArrayList<>();
        try {
            obatKronisList = permintaanResepDao.getLastObatKronis(idPasien, "");
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.findRiwayatKronis] Error ", e);
            throw new GeneralBOException("[CheckupBoImpl.findRiwayatKronis] Error " + e.getMessage());
        }

        Date datenow = new Date(System.currentTimeMillis());

        // jika ada riwayat kronis yang belum diambil
        if (obatKronisList.size() > 0) {
            for (ObatKronis obatDetail : obatKronisList) {
                Long longDate = obatDetail.getCreatedDate().getTime();

                Date tglPengambilan = new Date(longDate + TimeUnit.DAYS.toMillis(obatDetail.getIntervalHariKronis()));

                // jika datenow lebih besar atau sama dari tgl pengambilan
                if (datenow.compareTo(tglPengambilan) == 1 || datenow.compareTo(tglPengambilan) == 0) {
                    obatDetail.setFlagPengambilan("Y");
                    return obatKronisList;
                } else {
                    // jika kurang dari tgl pengambilan maka kirim alert
                    obatDetail.setFlagPengambilan("N");
                    obatDetail.setMsg("Belum waktunya mengambil obat kronis");
                    obatDetail.setTglPengambilan(tglPengambilan);
                    return obatKronisList;
                }
            }
        } else {
            // jika tidak ada maka cari yang sudah diambil

            obatKronisList = new ArrayList<>();

            try {
                obatKronisList = permintaanResepDao.getLastObatKronis(idPasien, "Y");
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.findRiwayatKronis] Error ", e);
                throw new GeneralBOException("[CheckupBoImpl.findRiwayatKronis] Error " + e.getMessage());
            }

            if (obatKronisList.size() > 0) {
                for (ObatKronis obatDetailDiambil : obatKronisList) {

                    Long longDate = obatDetailDiambil.getCreatedDate().getTime();
                    Date tglPengambilan = new Date(longDate + TimeUnit.DAYS.toMillis(30));

                    // jika datenow lebih kecil tgl pengambilan maka kirim alert
                    if (datenow.compareTo(tglPengambilan) == -1) {
                        obatDetailDiambil.setFlagKronisDiambil("K");
                        obatDetailDiambil.setMsg("Masih dalam waktu obat kronis. selama 30 hari.");
                        obatDetailDiambil.setTglPengambilan(tglPengambilan);
                        return obatKronisList;
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<TransaksiObatDetail> getListObatKronis(String idDetailCheckup, String idApproval) throws
            GeneralBOException {

        List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup) && idApproval != null && !"".equalsIgnoreCase(idApproval)) {

            try {
                transaksiObatDetails = headerCheckupDao.getListObatkronis(idDetailCheckup, idApproval);
            } catch (HibernateException e) {
                logger.error("Found Error when search obat kronis " + e.getMessage());
            }
        }

        return transaksiObatDetails;
    }

    @Override
    public CrudResponse savePengambilanObatKronis(HeaderCheckup bean, ItSimrsHeaderChekupEntity
            headerChekupEntity, ItSimrsHeaderDetailCheckupEntity detailCheckupEntity, ItSimrsDiagnosaRawatEntity
                                                          diagnosaRawatEntity, ImSimrsPermintaanResepEntity
                                                          resepEntity, List<ItSimrsDokterTeamEntity> dokterTeamEntities, List<ImtSimrsTransaksiObatDetailEntity> obatDetailEntities) throws
            GeneralBOException {

        CrudResponse response = new CrudResponse();
        if (headerChekupEntity != null) {
            headerChekupEntity.setNoCheckup("CKP" + getNextHeaderId());
            headerChekupEntity.setFlag(bean.getFlag());
            headerChekupEntity.setAction(bean.getAction());
            headerChekupEntity.setCreatedDate(bean.getCreatedDate());
            headerChekupEntity.setCreatedWho(bean.getCreatedWho());
            headerChekupEntity.setLastUpdate(bean.getLastUpdate());
            headerChekupEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                headerCheckupDao.addAndSave(headerChekupEntity);
            } catch (HibernateException e) {
                logger.error("[CheckupAction.savePengambilanObatKronis] ERROR " + e.getMessage());
            }
        }

        if (detailCheckupEntity != null) {
            detailCheckupEntity.setIdDetailCheckup("DCM" + getNextDetailCheckupId());
            detailCheckupEntity.setTarifBpjs(new BigDecimal(0));
            detailCheckupEntity.setKodeCbg(bean.getKodeCbg());
            detailCheckupEntity.setNoCheckup(headerChekupEntity.getNoCheckup());
            detailCheckupEntity.setFlag(bean.getFlag());
            detailCheckupEntity.setAction(bean.getAction());
            detailCheckupEntity.setCreatedDate(bean.getCreatedDate());
            detailCheckupEntity.setCreatedWho(bean.getCreatedWho());
            detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
            detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
            detailCheckupEntity.setStatusPeriksa("1");
            detailCheckupEntity.setIsKronis("Y");

            try {
                checkupDetailDao.addAndSave(detailCheckupEntity);
            } catch (HibernateException e) {
                logger.error("[CheckupAction.savePengambilanObatKronis] ERROR " + e.getMessage());
            }
        }

        if (diagnosaRawatEntity != null) {
            diagnosaRawatEntity.setIdDiagnosaRawat("DGR" + getNextIdDiagnosa());
            diagnosaRawatEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
            diagnosaRawatEntity.setFlag(bean.getFlag());
            diagnosaRawatEntity.setAction(bean.getAction());
            diagnosaRawatEntity.setCreatedDate(bean.getCreatedDate());
            diagnosaRawatEntity.setCreatedWho(bean.getCreatedWho());
            diagnosaRawatEntity.setLastUpdate(bean.getLastUpdate());
            diagnosaRawatEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                checkupDetailDao.addAndSave(detailCheckupEntity);
            } catch (HibernateException e) {
                logger.error("[CheckupAction.savePengambilanObatKronis] ERROR " + e.getMessage());
            }
        }

        if (dokterTeamEntities.size() > 0) {
            for (ItSimrsDokterTeamEntity dokterTeamEntity : dokterTeamEntities) {
                dokterTeamEntity.setIdTeamDokter("TDT" + getNextTeamDokterId());
                dokterTeamEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                dokterTeamEntity.setFlag(bean.getFlag());
                dokterTeamEntity.setAction(bean.getAction());
                dokterTeamEntity.setCreatedDate(bean.getCreatedDate());
                dokterTeamEntity.setCreatedWho(bean.getCreatedWho());
                dokterTeamEntity.setLastUpdate(bean.getLastUpdate());
                dokterTeamEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    dokterTeamDao.addAndSave(dokterTeamEntity);
                } catch (HibernateException e) {
                    logger.error("[CheckupAction.savePengambilanObatKronis] ERROR " + e.getMessage());
                }
            }
        }

        if (resepEntity != null) {
            resepEntity.setIdPermintaanResep("RSP" + getNextIdPermintaanResep());
            resepEntity.setIdApprovalObat("INV" + getNextIdApprovalObat());
            resepEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
            resepEntity.setFlag(bean.getFlag());
            resepEntity.setAction(bean.getAction());
            resepEntity.setCreatedDate(bean.getCreatedDate());
            resepEntity.setCreatedWho(bean.getCreatedWho());
            resepEntity.setLastUpdate(bean.getLastUpdate());
            resepEntity.setLastUpdateWho(bean.getLastUpdateWho());
            resepEntity.setIsUmum("N");
            resepEntity.setTglAntrian(bean.getLastUpdate());
            resepEntity.setStatus("0");

            try {
                permintaanResepDao.addAndSave(resepEntity);
            } catch (HibernateException e) {
                logger.error("[CheckupAction.savePengambilanObatKronis] ERROR " + e.getMessage());
            }

            ImtSimrsApprovalTransaksiObatEntity approvalObat = new ImtSimrsApprovalTransaksiObatEntity();
            approvalObat.setIdApprovalObat(resepEntity.getIdApprovalObat());
            approvalObat.setTipePermintaan("001");
            approvalObat.setIdPelayanan(detailCheckupEntity.getIdPelayanan());
            approvalObat.setFlag(bean.getFlag());
            approvalObat.setAction(bean.getAction());
            approvalObat.setCreatedDate(bean.getCreatedDate());
            approvalObat.setCreatedWho(bean.getCreatedWho());
            approvalObat.setLastUpdate(bean.getLastUpdate());
            approvalObat.setLastUpdateWho(bean.getLastUpdateWho());
            approvalObat.setBranchId(headerChekupEntity.getBranchId());

            try {
                approvalTransaksiObatDao.addAndSave(approvalObat);
            } catch (HibernateException e) {
                logger.error("[CheckupAction.savePengambilanObatKronis] ERROR " + e.getMessage());
            }

            if (obatDetailEntities.size() > 0) {
                for (ImtSimrsTransaksiObatDetailEntity trans : obatDetailEntities) {
                    trans.setIdTransaksiObatDetail("ODT" + getNextIdTransObatDetail());
                    trans.setIdApprovalObat(approvalObat.getIdApprovalObat());
                    trans.setFlag(bean.getFlag());
                    trans.setAction(bean.getAction());
                    trans.setCreatedDate(bean.getCreatedDate());
                    trans.setCreatedWho(bean.getCreatedWho());
                    trans.setLastUpdate(bean.getLastUpdate());
                    trans.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        transaksiObatDetailDao.addAndSave(trans);
                    } catch (HibernateException e) {
                        logger.error("[CheckupAction.savePengambilanObatKronis] ERROR " + e.getMessage());
                    }
                }
            }
        }
        return response;
    }


    @Override
    public ItSimrsHeaderChekupEntity getEntityCheckupById(String id) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getEntityCheckupById] START >>>");
        logger.info("[CheckupBoImpl.getEntityCheckupById] END <<<");
        return headerCheckupDao.getById("noCheckup", id);
    }

    @Override
    public HeaderCheckup getLastDataPasienByIdPasien(String idPasien) throws GeneralBOException {
        HeaderCheckup headerCheckup = new HeaderCheckup();
        try {
            headerCheckup = headerCheckupDao.getDataPasienByIdPasien(idPasien);
        } catch (HibernateException e) {
            logger.error("Found error " + e.getMessage());
        }
        return headerCheckup;
    }

    @Override
    public List<HeaderCheckup> getHistoryPasien(String idPasien, String branchId) throws GeneralBOException {
        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try {
            headerCheckupList = headerCheckupDao.getListHistoryPasien(idPasien, branchId);
        } catch (HibernateException e) {
            logger.error("Found error " + e.getMessage());
        }
        return headerCheckupList;
    }

    @Override
    public List<HeaderCheckup> getListDetailHistory(String id, String keterangan) throws GeneralBOException {
        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try {
            headerCheckupList = headerCheckupDao.getListDetailHistoryPasien(id, keterangan);
        } catch (HibernateException e) {
            logger.error("Found error " + e.getMessage());
        }
        return headerCheckupList;
    }

    @Override
    public List<HeaderCheckup> getListVedioRm(String id) throws GeneralBOException {
        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try {
            headerCheckupList = headerCheckupDao.getListVideoRm(id);
        } catch (HibernateException e) {
            logger.error("Found error " + e.getMessage());
        }
        return headerCheckupList;
    }

    @Override
    public CrudResponse updateAnamnese(HeaderCheckup bean) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        ItSimrsHeaderChekupEntity chekupEntity = new ItSimrsHeaderChekupEntity();
        try {
            chekupEntity = headerCheckupDao.getById("noCheckup", bean.getNoCheckup());
            if (chekupEntity.getNoCheckup() != null) {
                chekupEntity.setAutoanamnesis(bean.getAutoanamnesis());
                chekupEntity.setHeteroanamnesis(bean.getHeteroanamnesis());
                chekupEntity.setTensi(bean.getTensi());
                chekupEntity.setSuhu(bean.getSuhu());
                chekupEntity.setNadi(bean.getNadi());
                chekupEntity.setRr(bean.getPernafasan());
                chekupEntity.setLastUpdate(bean.getLastUpdate());
                chekupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                chekupEntity.setCatatanKlinis(bean.getCatatanKlinis());
                chekupEntity.setTinggi(bean.getTinggi());
                chekupEntity.setBerat(bean.getBerat());
                chekupEntity.setSpo2(bean.getSpo2());
                try {
                    headerCheckupDao.updateAndSave(chekupEntity);
                    response.setMsg("Berhasil");
                    response.setStatus("success");
                } catch (HibernateException e) {
                    response.setMsg("Error when update data " + e.getMessage());
                    response.setStatus("error");
                }
            }
        } catch (HibernateException e) {
            response.setMsg("Error when update data " + e.getMessage());
            response.setStatus("error");
        }
        return response;
    }

    @Override
    public String getDiagnosaPasien(String idDetailCheckup) throws GeneralBOException {
        return headerCheckupDao.getDiagnosa(idDetailCheckup);
    }

    @Override
    public String getTindakanRawat(String idDetailCheckup) throws GeneralBOException {
        return headerCheckupDao.getTindakanRawat(idDetailCheckup);
    }

    @Override
    public String getTindakanRawatICD9(String idDetailCheckup) throws GeneralBOException {
        return headerCheckupDao.getTindakanRawatIC9(idDetailCheckup);
    }

    @Override
    public String getDiagnosaPrimer(String idDetailCheckup) throws GeneralBOException {
        return headerCheckupDao.getDiagnosaPrimer(idDetailCheckup);
    }

    @Override
    public String getDiagnosaMasuk(String idDetailCheckup) throws GeneralBOException {
        return headerCheckupDao.getDiagnosaMasuk(idDetailCheckup);
    }

    @Override
    public String getDiagnosaSekunder(String idDetailCheckup) throws GeneralBOException {
        return headerCheckupDao.getDiagnosaSekunder(idDetailCheckup);
    }

    @Override
    public String getPenunjangMedis(String idDetailCheckup, String tipe) throws GeneralBOException {
        return headerCheckupDao.getPenunjangMendis(idDetailCheckup, tipe);
    }

    @Override
    public String getResepPasien(String idDetailCheckup) throws GeneralBOException {
        return headerCheckupDao.getResepPasien(idDetailCheckup);
    }

    @Override
    public String getAlergi(String noCheckup) throws GeneralBOException {
        return headerCheckupDao.getAlergiPasien(noCheckup);
    }

    @Override
    public HeaderCheckup getDataPemeriksaanFisik(String noCheckup) throws GeneralBOException {
        return headerCheckupDao.getPemeriksaanFisik(noCheckup);
    }

    @Override
    public List<HeaderCheckup> getRiwayatPemeriksaan(String idPasien) throws GeneralBOException {
        return headerCheckupDao.getRiwayatPemeriksaan(idPasien);
    }

    @Override
    public Dokter getNamaSipDokter(String id, String tipe) throws GeneralBOException {
        return headerCheckupDao.getNamaSipDokter(id, tipe);
    }

    @Override
    public List<PelayananPaket> getListPelayananPaket(String noCheckup) throws GeneralBOException {
        return pelayananPaketDao.getListPelayananPaket(noCheckup);
    }

    @Override
    public CrudResponse nextItemPaketToPeriksa(PelayananPaket bean, HeaderDetailCheckup detail) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        ItSimrsHeaderDetailCheckupEntity checkupDetailEntity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());
        if (checkupDetailEntity != null) {
            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();
            detailCheckupEntity.setIdDetailCheckup("DCM" + checkupDetailDao.getNextId());
            detailCheckupEntity.setNoCheckup(detail.getNoCheckup());
            detailCheckupEntity.setIdPelayanan(detail.getIdPelayanan());
            if ("paket_individu".equalsIgnoreCase(checkupDetailEntity.getIdJenisPeriksaPasien())) {
                detailCheckupEntity.setStatusPeriksa("0");
            } else {
                detailCheckupEntity.setStatusPeriksa("1");
            }
            detailCheckupEntity.setFlag("Y");
            detailCheckupEntity.setAction("C");
            detailCheckupEntity.setCreatedDate(bean.getCreatedDate());
            detailCheckupEntity.setCreatedWho(bean.getCreatedWho());
            detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
            detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
            detailCheckupEntity.setTglAntrian(bean.getCreatedDate());
            detailCheckupEntity.setBranchId(checkupDetailEntity.getBranchId());
            detailCheckupEntity.setIdJenisPeriksaPasien(checkupDetailEntity.getIdJenisPeriksaPasien());
            detailCheckupEntity.setIdPaket(checkupDetailEntity.getIdPaket());
            detailCheckupEntity.setCoverBiaya(checkupDetailEntity.getCoverBiaya());

            try {
                checkupDetailDao.addAndSave(detailCheckupEntity);
                response.setStatus("success");
                response.setMsg("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("Error When Saving data detail checkup " + e.getMessage());
                logger.error("[CheckupDetailBoImpl.saveAdd] Error When Saving data detail checkup" + e.getMessage());
                throw new GeneralBOException("[CheckupDetailBoImpl.saveAdd] Error When Saving data detail checkup");
            }

            if (detail.getDokterTeamList().size() > 0) {
                for (DokterTeam dokterTeam : detail.getDokterTeamList()) {
                    DokterTeam dkt = new DokterTeam();
                    dkt.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                    dkt.setIdDokter(dokterTeam.getIdDokter());
                    dkt.setIdPelayanan(dokterTeam.getIdPelayanan());
                    dkt.setJenisDpjp(dokterTeam.getJenisDpjp());
                    dkt.setCreatedWho(detail.getCreatedWho());
                    dkt.setLastUpdateWho(detail.getLastUpdateWho());
                    response = saveTeamDokter(dkt);
                }
            }

            List<PaketPeriksa> paketPasienList = new ArrayList<>();
            try {
                paketPasienList = paketPasienDao.getItemPaketWithIdPaket(bean.getIdPaket(), bean.getUrutan().toString());
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
                throw new GeneralBOException("Found Error " + e.getMessage());
            }

            if (paketPasienList.size() > 0) {
                String kategoriLab = "";
                String idPeriksaLab = "";
                for (PaketPeriksa paketPeriksa : paketPasienList) {
                    if ("tindakan".equalsIgnoreCase(paketPeriksa.getJenisItem())) {
                        ItSimrsTindakanRawatEntity tindakanRawatEntity = new ItSimrsTindakanRawatEntity();
                        tindakanRawatEntity.setIdTindakanRawat("TDR" + getNextTindakanRawatId());
                        tindakanRawatEntity.setIdTindakan(paketPeriksa.getIdItem());
                        tindakanRawatEntity.setIdDokter(bean.getIdDokter());
                        tindakanRawatEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                        tindakanRawatEntity.setQty(new BigInteger("1"));

                        List<Tindakan> tindakanEntity = new ArrayList<>();
                        Tindakan resultTindakan = new Tindakan();
                        Tindakan tin = new Tindakan();
                        tin.setIdTindakan(paketPeriksa.getIdItem());

                        try {
                            tindakanEntity = tindakanDao.getListDataTindakan(tin);
                        } catch (HibernateException e) {
                            logger.error("[CheckupBoImpl.insertItemPaketToPeriksa] ERROR", e);
                            throw new GeneralBOException("[CheckupBoImpl.insertItemPaketToPeriksa] ERROR", e);
                        }

                        if (tindakanEntity.size() > 0) {
                            resultTindakan = tindakanEntity.get(0);
                            tindakanRawatEntity.setNamaTindakan(resultTindakan.getTindakan());
                            tindakanRawatEntity.setTarif(resultTindakan.getTarif());
                            tindakanRawatEntity.setTarifTotal(resultTindakan.getTarif().multiply(tindakanRawatEntity.getQty()));
                            tindakanRawatEntity.setFlag("Y");
                            tindakanRawatEntity.setAction("C");
                            tindakanRawatEntity.setCreatedDate(bean.getCreatedDate());
                            tindakanRawatEntity.setCreatedWho(bean.getCreatedWho());
                            tindakanRawatEntity.setLastUpdate(bean.getLastUpdate());
                            tindakanRawatEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            tindakanRawatEntity.setApproveFlag("Y");
                        }

                        try {
                            tindakanRawatDao.addAndSave(tindakanRawatEntity);
                        } catch (HibernateException e) {
                            logger.error("[CheckupBoImpl.insertItemPaketToPeriksa] ERROR", e);
                            throw new GeneralBOException("[CheckupBoImpl.insertItemPaketToPeriksa] ERROR", e);
                        }
                    }

                    if ("laboratorium".equalsIgnoreCase(paketPeriksa.getJenisItem()) || "radiologi".equalsIgnoreCase(paketPeriksa.getJenisItem())) {
                        ItSimrsPeriksaLabEntity entityPeriksaLab = new ItSimrsPeriksaLabEntity();
                        if (!kategoriLab.equalsIgnoreCase(paketPeriksa.getIdKategoriItem())) {
                            String idKategoriLab = labDetailDao.kategoriLab(paketPeriksa.getIdKategoriItem(), CommonUtil.userBranchLogin());
                            kategoriLab = paketPeriksa.getIdKategoriItem();
                            String id = getNextPeriksaLabId();
                            entityPeriksaLab.setIdPeriksaLab("PRL" + id);
//                            entityPeriksaLab.setIdLab(paketPeriksa.getIdKategoriItem());
//                            entityPeriksaLab.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
//                            entityPeriksaLab.setIdDokterPengirim(bean.getIdDokter());
//                            entityPeriksaLab.setStatusPeriksa("0");
                            entityPeriksaLab.setFlag("Y");
                            entityPeriksaLab.setAction("C");
                            entityPeriksaLab.setCreatedDate(bean.getCreatedDate());
                            entityPeriksaLab.setCreatedWho(bean.getCreatedWho());
                            entityPeriksaLab.setLastUpdate(bean.getLastUpdate());
                            entityPeriksaLab.setLastUpdateWho(bean.getLastUpdateWho());
//                            entityPeriksaLab.setIdKategoriLab(idKategoriLab);

                            try {
                                periksaLabDao.addAndSave(entityPeriksaLab);
                            } catch (HibernateException e) {
                                logger.error("Found Error when insert " + e.getMessage());
                            }
                            idPeriksaLab = entityPeriksaLab.getIdPeriksaLab();
                        }

                        ItSimrsPeriksaLabDetailEntity detailEntity = new ItSimrsPeriksaLabDetailEntity();
                        String id = getNextDetailLapId();
                        detailEntity.setIdPeriksaLabDetail("DPL" + id);
                        detailEntity.setIdPeriksaLab(idPeriksaLab);
                        detailEntity.setIdLabDetail(paketPeriksa.getIdItem());

                        List<LabDetail> labDetailList = new ArrayList<>();
                        LabDetail resultLab = new LabDetail();
                        LabDetail labDetail = new LabDetail();
                        labDetail.setIdLabDetail(paketPeriksa.getIdItem());

                        try {
                            labDetailList = labDetailDao.getDataParameterPemeriksaan(labDetail);
                        } catch (HibernateException e) {
                            logger.error("Found Error when insert " + e.getMessage());
                        }
                        if (labDetailList.size() > 0) {
                            resultLab = labDetailList.get(0);
                            detailEntity.setNamaDetailPeriksa(resultLab.getNamaDetailPeriksa());
                            detailEntity.setKeteranganAcuanP(resultLab.getKeteranganAcuanP());
                            detailEntity.setKeteranganAcuanL(resultLab.getKeteranganAcuanL());
                            detailEntity.setSatuan(resultLab.getSatuan());
                            detailEntity.setTarif(resultLab.getTarif());
                            detailEntity.setFlag("Y");
                            detailEntity.setAction("C");
                            detailEntity.setCreatedDate(bean.getCreatedDate());
                            detailEntity.setCreatedWho(bean.getCreatedWho());
                            detailEntity.setLastUpdate(bean.getLastUpdate());
                            detailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            try {
                                periksaLabDetailDao.addAndSave(detailEntity);
                            } catch (HibernateException e) {
                                logger.error("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data detail periksa lab " + e.getMessage());
                                throw new GeneralBOException("[PeriksaLabBoImpl.saveAddWithParameter] ERROR when saving data detail periksa lab " + e.getMessage());
                            }
                        }
                    }
                }

                try {
                    ItSimrsPelayananPaketEntity paketEntity = pelayananPaketDao.getById("idPelayananPaket", bean.getIdPelayananPaket());
                    if (paketEntity != null) {
                        paketEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                        paketEntity.setIsPeriksa("Y");
                        paketEntity.setAction("U");
                        paketEntity.setLastUpdate(bean.getLastUpdate());
                        paketEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        try {
                            pelayananPaketDao.addAndSave(paketEntity);
                            response.setStatus("success");
                            response.setMsg("Oke");
                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMsg("Update Error " + e.getMessage());
                            logger.error("Insert Error" + e.getMessage());
                            throw new GeneralBOException("Insert detail paket Error");
                        }
                    }
                } catch (HibernateException e) {
                    logger.error("Search Error" + e.getMessage());
                    throw new GeneralBOException("Search detail paket Error");
                }
            }
        }
        return response;
    }

    @Override
    public CrudResponse saveAddWithResponse(HeaderCheckup bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.saveAdd] Start >>>>>>>");
        CrudResponse response = new CrudResponse();
        if (bean != null) {
            String id = "";
            ItSimrsHeaderChekupEntity headerEntity = new ItSimrsHeaderChekupEntity();
            id = getNextHeaderId();
            headerEntity.setNoCheckup("CKP"+id);
            headerEntity.setIdPasien(bean.getIdPasien());
            headerEntity.setNama(bean.getNama());
            headerEntity.setJenisKelamin(bean.getJenisKelamin());
            headerEntity.setNoKtp(bean.getNoKtp());
            headerEntity.setTempatLahir(bean.getTempatLahir());
            headerEntity.setTglLahir(bean.getTglLahir());
            headerEntity.setDesaId(bean.getDesaId());
            headerEntity.setJalan(bean.getJalan());
            headerEntity.setSuku(bean.getSuku());
            headerEntity.setProfesi(bean.getProfesi() != null && !"".equalsIgnoreCase(bean.getProfesi()) ? bean.getProfesi() : null);
            if (bean.getNoTelp() != null && !"".equalsIgnoreCase(bean.getNoTelp())) {
                String noHp = bean.getNoTelp().replace("-", "").replace("_", "");
                headerEntity.setNoTelp(noHp);
            }
            headerEntity.setAgama(bean.getAgama());
            headerEntity.setUrlKtp(bean.getUrlKtp());
            headerEntity.setBranchId(bean.getBranchId());
            headerEntity.setFlag("Y");
            headerEntity.setAction("C");
            headerEntity.setCreatedDate(bean.getCreatedDate());
            headerEntity.setLastUpdate(bean.getLastUpdate());
            headerEntity.setCreatedWho(bean.getCreatedWho());
            headerEntity.setLastUpdateWho(bean.getLastUpdateWho());
            headerEntity.setJenisKunjungan(bean.getJenisKunjungan());
            headerEntity.setNamaPenanggung(bean.getNamaPenanggung() != null && !"".equalsIgnoreCase(bean.getNamaPenanggung()) ? bean.getNamaPenanggung() : null);
            headerEntity.setHubunganKeluarga(bean.getHubunganKeluarga() != null && !"".equalsIgnoreCase(bean.getHubunganKeluarga()) ? bean.getHubunganKeluarga() : null);
            headerEntity.setBerat(bean.getBerat());
            headerEntity.setTinggi(bean.getTinggi());
            headerEntity.setKunjunganPoli(bean.getKunjunganPoli());

            try {
                headerCheckupDao.addAndSave(headerEntity);
                response.setStatus("success");
                response.setMsg("berhasil");
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.saveAdd] Error When Saving data header checkup" + e.getMessage());
                response.setStatus("error");
                response.setMsg(e.getMessage());
            }

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
                ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();
                id = getNextDetailCheckupId();
                detailCheckupEntity.setIdDetailCheckup("DCM" + id);
                detailCheckupEntity.setNoCheckup(headerEntity.getNoCheckup());
                detailCheckupEntity.setIdPelayanan(bean.getIdPelayanan());
                detailCheckupEntity.setIdJenisPeriksaPasien(bean.getIdJenisPeriksaPasien());

                if ("asuransi".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                    detailCheckupEntity.setMetodePembayaran("non_tunai");
                } else {
                    detailCheckupEntity.setMetodePembayaran(bean.getMetodePembayaran() != null && !"".equalsIgnoreCase(bean.getMetodePembayaran()) ? bean.getMetodePembayaran() : null);
                }

                detailCheckupEntity.setNoRujukan(bean.getNoRujukan() != null && !"".equalsIgnoreCase(bean.getNoRujukan()) ? bean.getNoRujukan() : null);
                detailCheckupEntity.setTglRujukan(bean.getTglRujukan() != null && !"".equalsIgnoreCase(bean.getTglRujukan()) ? Date.valueOf(bean.getTglRujukan()) : null);
                detailCheckupEntity.setUrlDocRujuk(bean.getUrlDocRujuk() != null && !"".equalsIgnoreCase(bean.getUrlDocRujuk()) ? bean.getUrlDocRujuk() : null);
                detailCheckupEntity.setIdPaket(bean.getIdPaket() != null && !"".equalsIgnoreCase(bean.getIdPaket()) ? bean.getIdPaket() : null);
                detailCheckupEntity.setIdAsuransi(bean.getIdAsuransi() != null && !"".equalsIgnoreCase(bean.getIdAsuransi()) ? bean.getIdAsuransi() : null);
                detailCheckupEntity.setCoverBiaya(bean.getCoverBiaya() != null && !"".equalsIgnoreCase(bean.getCoverBiaya().toString()) ? bean.getCoverBiaya() : null);
                detailCheckupEntity.setNoKartuAsuransi(bean.getNoKartuAsuransi() != null && !"".equalsIgnoreCase(bean.getNoKartuAsuransi()) ? bean.getNoKartuAsuransi() : null);
                detailCheckupEntity.setBranchId(bean.getBranchId());
                detailCheckupEntity.setFlag("Y");
                detailCheckupEntity.setAction("C");
                detailCheckupEntity.setCreatedDate(bean.getCreatedDate());
                detailCheckupEntity.setCreatedWho(bean.getCreatedWho());
                detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                detailCheckupEntity.setFlagTppri("Y");

                if ("paket_perusahaan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                    detailCheckupEntity.setStatusPeriksa("1");
                    detailCheckupEntity.setCoverBiaya(bean.getCoverBiaya() == null ? null : bean.getCoverBiaya());
                } else {
                    detailCheckupEntity.setStatusPeriksa(bean.getStatusPeriksa());
                }

                if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                    detailCheckupEntity.setKodeCbg(bean.getKodeCbg());
                    detailCheckupEntity.setRujuk(bean.getRujuk() != null ? bean.getRujuk() : null);
                    detailCheckupEntity.setKetRujukan(bean.getKetPerujuk() != null ? bean.getKetPerujuk() : null);
                    detailCheckupEntity.setNoSep(bean.getNoSep());
                    detailCheckupEntity.setTarifBpjs(bean.getTarifBpjs());
                    detailCheckupEntity.setKelasPasien(bean.getKelasPasien());
                    detailCheckupEntity.setIdPelayananBpjs(bean.getIdPelayananBpjs());
                    detailCheckupEntity.setNoPpkRujukan(bean.getNoPpkRujukan() != null ? bean.getNoPpkRujukan() : null);
                }

                if (detailCheckupEntity.getNoCheckupOnline() != null && !"".equalsIgnoreCase(detailCheckupEntity.getNoCheckupOnline())) {
                    detailCheckupEntity.setNoCheckupOnline(bean.getNoCheckupOnline());
                }

                if ("Y".equalsIgnoreCase(bean.getIsOnline())) {
                    detailCheckupEntity.setTglAntrian(bean.getTglAntian());
                } else {
                    detailCheckupEntity.setTglAntrian(bean.getCreatedDate());
                }

                try {
                    checkupDetailDao.addAndSave(detailCheckupEntity);
                    response.setStatus("success");
                    response.setMsg("berhasil");
                } catch (HibernateException e) {
                    logger.error("[CheckupBoImpl.saveAdd] Error When Saving data detail checkup" + e.getMessage());
                    response.setStatus("error");
                    response.setMsg(e.getMessage());
                }

                if (bean.getDokterTeamList().size() > 0) {
                    for (DokterTeam dokterTeam : bean.getDokterTeamList()) {
                        DokterTeam dkt = new DokterTeam();
                        dkt.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                        dkt.setIdDokter(dokterTeam.getIdDokter());
                        dkt.setIdPelayanan(dokterTeam.getIdPelayanan());
                        dkt.setJenisDpjp(dokterTeam.getJenisDpjp());
                        dkt.setCreatedWho(bean.getCreatedWho());
                        dkt.setLastUpdateWho(bean.getLastUpdateWho());
                        dkt.setFlagApprove(dokterTeam.getFlagApprove());
                        response = saveTeamDokter(dkt);
                    }
                }

                if (bean.getDiagnosa() != null && !"".equalsIgnoreCase(bean.getDiagnosa()) && detailCheckupEntity.getIdDetailCheckup() != null && !"".equalsIgnoreCase(detailCheckupEntity.getIdDetailCheckup())) {
                    DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
                    diagnosaRawat.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                    diagnosaRawat.setIdDiagnosa(bean.getDiagnosa());
                    diagnosaRawat.setKeteranganDiagnosa(bean.getNamaDiagnosa());
                    diagnosaRawat.setJenisDiagnosa("1");
                    saveDiagnosa(diagnosaRawat);
                }

                BigDecimal diskon = null;
                if ("bpjs_rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                    RekananOps ops = new RekananOps();
                    try {
                        ops = rekananOpsDao.getDetailRekananOps(bean.getIdAsuransi(), bean.getBranchId());
                    } catch (HibernateException e) {
                        logger.error("Error when search diskon, " + e.getMessage());
                    }

                    if (ops != null) {
                        if (ops.getDiskon() != null && !"".equalsIgnoreCase(ops.getDiskon().toString())) {
                            diskon = ops.getDiskon();
                        }
                    }
                }

                if (bean.getTindakanList() != null && bean.getTindakanList().size() > 0) {
                    for (Tindakan tindakan : bean.getTindakanList()) {
                        ItSimrsTindakanRawatEntity tindakanRawatEntity = new ItSimrsTindakanRawatEntity();
                        tindakanRawatEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                        tindakanRawatEntity.setIdTindakanRawat("TDR" + getNextTindakanRawatId());
                        tindakanRawatEntity.setIdTindakan(tindakan.getIdTindakan());
                        tindakanRawatEntity.setNamaTindakan(tindakan.getTindakan());
                        tindakanRawatEntity.setIdDokter(bean.getIdDokter());
                        tindakanRawatEntity.setCreatedDate(bean.getCreatedDate());
                        tindakanRawatEntity.setCreatedWho(bean.getCreatedWho());
                        tindakanRawatEntity.setLastUpdate(bean.getCreatedDate());
                        tindakanRawatEntity.setLastUpdateWho(bean.getCreatedWho());
                        tindakanRawatEntity.setFlag("Y");
                        tindakanRawatEntity.setAction("U");
                        tindakanRawatEntity.setApproveFlag("Y");

                        if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                            if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                                tindakanRawatEntity.setTarif(tindakan.getTarifBpjs());
                            } else {
                                if (diskon != null) {
                                    BigDecimal hasil = new BigDecimal(tindakan.getTarifBpjs()).multiply(diskon);
                                    tindakanRawatEntity.setTarif(hasil.toBigInteger());
                                }
                            }
                        } else {
                            tindakanRawatEntity.setTarif(tindakan.getTarif());
                        }

                        tindakanRawatEntity.setQty(new BigInteger(String.valueOf(1)));
                        tindakanRawatEntity.setTarifTotal(tindakanRawatEntity.getTarif().multiply(tindakanRawatEntity.getQty()));

                        try {

                            tindakanRawatDao.addAndSave(tindakanRawatEntity);

                            if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {

                                String jenPasien = "";
                                if ("bpjs_rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                                    jenPasien = "bpjs";
                                } else {
                                    jenPasien = bean.getIdJenisPeriksaPasien();
                                }

                                ItSimrsRiwayatTindakanEntity riwayatTindakan = new ItSimrsRiwayatTindakanEntity();
                                riwayatTindakan.setIdRiwayatTindakan("RWT" + getNextIdRiwayatTindakan());
                                riwayatTindakan.setIdTindakan(tindakanRawatEntity.getIdTindakanRawat());
                                riwayatTindakan.setNamaTindakan(tindakanRawatEntity.getNamaTindakan());
                                riwayatTindakan.setTotalTarif(new BigDecimal(String.valueOf(tindakanRawatEntity.getTarifTotal())));
                                riwayatTindakan.setApproveBpjsFlag("Y");
                                riwayatTindakan.setKategoriTindakanBpjs(tindakan.getKategoriInaBpjs());
                                riwayatTindakan.setKeterangan("tindakan");
                                riwayatTindakan.setJenisPasien(jenPasien);
                                riwayatTindakan.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                                riwayatTindakan.setFlagUpdateKlaim("Y");
                                riwayatTindakan.setCreatedWho(bean.getCreatedWho());
                                riwayatTindakan.setCreatedDate(bean.getCreatedDate());
                                riwayatTindakan.setLastUpdate(bean.getLastUpdate());
                                riwayatTindakan.setLastUpdateWho(bean.getLastUpdateWho());
                                riwayatTindakan.setFlag("Y");
                                riwayatTindakan.setAction("C");
                                riwayatTindakan.setTanggalTindakan(bean.getCreatedDate());

                                try {
                                    riwayatTindakanDao.addAndSave(riwayatTindakan);
                                    response.setStatus("success");
                                    response.setMsg("berhasil");
                                } catch (HibernateException e) {
                                    logger.error("[CheckupBoImpl FOunf error]" + e.getMessage());
                                    response.setStatus("error");
                                    response.setMsg(e.getMessage());
                                }

                            }

                        } catch (HibernateException e) {
                            logger.error("[CheckupBoImpl.saveAdd] Error When Saving tindakan rawat" + e.getMessage());
                            response.setStatus("error");
                            response.setMsg(e.getMessage());
                        }
                    }
                }

                if ("umum".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {

                    if (bean.getUangMuka() != null && !"".equalsIgnoreCase(bean.getUangMuka().toString())) {
                        ItSimrsUangMukaPendaftaranEntity uangMukaPendaftaranEntity = new ItSimrsUangMukaPendaftaranEntity();
                        uangMukaPendaftaranEntity.setId("UM" + bean.getBranchId() + dateFormater("MM") + dateFormater("yy") + uangMukaDao.getNextId());
                        uangMukaPendaftaranEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                        uangMukaPendaftaranEntity.setFlag("Y");
                        uangMukaPendaftaranEntity.setAction("C");
                        uangMukaPendaftaranEntity.setCreatedDate(bean.getCreatedDate());
                        uangMukaPendaftaranEntity.setCreatedWho(bean.getCreatedWho());
                        uangMukaPendaftaranEntity.setLastUpdate(bean.getCreatedDate());
                        uangMukaPendaftaranEntity.setLastUpdateWho(bean.getCreatedWho());

                        if (bean.getNoNota() != null) {
                            uangMukaPendaftaranEntity.setNoNota(bean.getNoNota());
                            uangMukaPendaftaranEntity.setStatusBayar("Y");
                        }

                        if (bean.getUangMuka() == null || bean.getUangMuka().compareTo(new BigInteger(String.valueOf(0))) == 0) {
                            uangMukaPendaftaranEntity.setJumlah(new BigInteger(String.valueOf(0)));
                        } else {
                            uangMukaPendaftaranEntity.setJumlah(bean.getUangMuka());
                        }

                        try {
                            uangMukaDao.addAndSave(uangMukaPendaftaranEntity);
                            response.setStatus("success");
                            response.setMsg("berhasil");
                        } catch (HibernateException e) {
                            logger.error("[CheckupBoImpl.saveAdd] Error When Saving" + e.getMessage());
                            response.setStatus("error");
                            response.setMsg(e.getMessage());
                        }
                    }
                }

                if(bean.getRawatInap()){
                    RawatInap rawatInap = new RawatInap();
                    rawatInap.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                    rawatInap.setIdRuangan(bean.getIdRuangan());
                    rawatInap.setNoCheckup(headerEntity.getNoCheckup());
                    rawatInap.setCreatedDate(bean.getCreatedDate());
                    rawatInap.setCreatedWho(bean.getCreatedWho());
                    rawatInap.setTglMasuk(bean.getCreatedDate());
                    rawatInap.setStatus("1");
                    response = saveRawatInap(rawatInap);
                }
            }
            logger.info("[CheckupBoImpl.saveAdd] End <<<<<<<");
        }
        return response;
    }

    @Override
    public List<HeaderCheckup> cekKunjunganPoliPasien(String idPasien, String idPelayanan) throws GeneralBOException {
        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try{
            headerCheckupList = headerCheckupDao.cekKunjunganPoliPasien(idPasien, idPelayanan);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return headerCheckupList;
    }

    @Override
    public List<HeaderCheckup> daftarPasienOnline(String branchId, String idPelayanan) throws GeneralBOException {
        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try{
            headerCheckupList = headerCheckupDao.getDaftarPasienOnline(branchId, idPelayanan);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return headerCheckupList;
    }

    @Override
    public void cancelPeriksa(HeaderDetailCheckup detailCheckup) throws GeneralBOException {
        try {
            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", detailCheckup.getIdDetailCheckup());
            if(detailCheckupEntity != null){
                detailCheckupEntity.setKeteranganSelesai(detailCheckup.getKeteranganSelesai());
                detailCheckupEntity.setLastUpdate(detailCheckup.getLastUpdate());
                detailCheckupEntity.setLastUpdateWho(detailCheckup.getLastUpdateWho());
                detailCheckupEntity.setAction("U");
                detailCheckupEntity.setStatusPeriksa("5");
                detailCheckupEntity.setTindakLanjut("batal");
                try {
                    checkupDetailDao.updateAndSave(detailCheckupEntity);
                }catch (HibernateException e){
                    throw new GeneralBOException("Errro"+e.getMessage());
                }
            }
        }catch (Exception e){
            throw new GeneralBOException("Errro"+e.getMessage());
        }
    }

    @Override
    public void cancelPeriksaInap(HeaderDetailCheckup detailCheckup) throws GeneralBOException {
        if(detailCheckup.getNoCheckup() != null && !"".equalsIgnoreCase(detailCheckup.getNoCheckup())){
            RawatInap rawatInap = rawatInapDao.getPeriksaInap(detailCheckup.getNoCheckup());
            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", rawatInap.getIdDetailCheckup());
            if(detailCheckupEntity != null){
                detailCheckupEntity.setKeteranganSelesai(detailCheckup.getKeteranganSelesai());
                detailCheckupEntity.setLastUpdate(detailCheckup.getLastUpdate());
                detailCheckupEntity.setLastUpdateWho(detailCheckup.getLastUpdateWho());
                detailCheckupEntity.setAction("U");
                detailCheckupEntity.setStatusPeriksa("5");
                detailCheckupEntity.setTindakLanjut("batal");
                try {
                    checkupDetailDao.updateAndSave(detailCheckupEntity);
                }catch (HibernateException e){
                    throw new GeneralBOException("Errro"+e.getMessage());
                }
            }

            ItSimrsRawatInapEntity rawatInapEntity = rawatInapDao.getById("idRawatInap", rawatInap.getIdRawatInap());
            if(rawatInapEntity != null){
                rawatInapEntity.setLastUpdate(detailCheckup.getLastUpdate());
                rawatInapEntity.setLastUpdateWho(detailCheckup.getLastUpdateWho());
                rawatInapEntity.setFlag("N");
                rawatInapEntity.setAction("U");
                rawatInapEntity.setStatus("5");
                try {
                    rawatInapDao.updateAndSave(rawatInapEntity);
                }catch (HibernateException e){
                    throw new GeneralBOException("Errro"+e.getMessage());
                }

                MtSimrsRuanganTempatTidurEntity tempatTidurEntity = tempatTidurDao.getById("idTempatTidur", rawatInapEntity.getIdRuangan());
                if(tempatTidurEntity != null) {
                    tempatTidurEntity.setStatus("Y");
                    tempatTidurEntity.setAction("U");
                    tempatTidurEntity.setLastUpdate(detailCheckup.getLastUpdate());
                    tempatTidurEntity.setLastUpdateWho(detailCheckup.getLastUpdateWho());
                    try {
                        tempatTidurDao.updateAndSave(tempatTidurEntity);
                    } catch (HibernateException e) {
                        logger.error("Error when Update data ruangan "+e.getMessage());
                    }
                }
            }
        }else{
            throw new GeneralBOException("NO checkup tidak ditemukan...!");
        }
    }

    @Override
    public String fisrtCheckup(String noCheckup) throws GeneralBOException {
        String res = "";
        try{
            res = headerCheckupDao.firstCheckup(noCheckup);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return res;
    }

    @Override
    public void updateVitalSign(HeaderCheckup bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.updateVitalSign] Start <<<<<<<");
        if(bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())){
            try {
                ItSimrsHeaderChekupEntity entity = headerCheckupDao.getById("noCheckup", bean.getNoCheckup());
                if(entity != null){
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    entity.setAction("U");
                    if(bean.getTensi() != null && !"".equalsIgnoreCase(bean.getTensi())){
                        entity.setTensi(bean.getTensi());
                    }
                    if(bean.getNadi() != null && !"".equalsIgnoreCase(bean.getNadi())){
                        entity.setNadi(bean.getNadi());
                    }
                    if(bean.getPernafasan() != null && !"".equalsIgnoreCase(bean.getPernafasan())){
                        entity.setRr(bean.getPernafasan());
                    }
                    if(bean.getSuhu() != null && !"".equalsIgnoreCase(bean.getSuhu())){
                        entity.setSuhu(bean.getSuhu());
                    }
                    headerCheckupDao.updateAndSave(entity);
                }
            }catch (Exception e){
                logger.error("[CheckupBoImpl.updateVitalSign] Error, "+e.getMessage());
            }
        }
        logger.info("[CheckupBoImpl.updateVitalSign] End <<<<<<<");
    }

    @Override
    public HeaderCheckup getDataPendaftaranPasien(String idDetailCheckup) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getDataPendaftaranPasien] Start <<<<<<<");
        HeaderCheckup headerCheckup = new HeaderCheckup();
        if(idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)){
            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", idDetailCheckup);
            if(detailCheckupEntity != null){
                ItSimrsHeaderChekupEntity headerChekupEntity = headerCheckupDao.getById("noCheckup", detailCheckupEntity.getNoCheckup());
                if(headerChekupEntity != null){
                    List<ItSimrsDokterTeamEntity> dokterTeamEntityList = new ArrayList<>();
                    ItSimrsDokterTeamEntity dokterTeam = new ItSimrsDokterTeamEntity();
                    ImSimrsDokterEntity dokterEntity = new ImSimrsDokterEntity();

                    HashMap hsCriteria = new HashMap();
                    hsCriteria.put("id_detail_checkup", detailCheckupEntity.getIdDetailCheckup());
                    try {
                       dokterTeamEntityList = dokterTeamDao.getByCriteria(hsCriteria);
                    }catch (HibernateException e){
                        logger.error("[CheckupBoImpl.getDataPendaftaranPasien] Error"+e.getMessage());
                    }

                    if(dokterTeamEntityList.size() > 0){
                        dokterTeam = dokterTeamEntityList.get(0);
                        List<ImSimrsDokterEntity> dokterEntityList = new ArrayList<>();
                        hsCriteria = new HashMap();
                        hsCriteria.put("id_dokter", dokterTeam.getIdDokter());
                        try{
                            dokterEntityList = dokterDao.getByCriteria(hsCriteria);
                        }catch (HibernateException e){
                            logger.error("[CheckupBoImpl.getDataPendaftaranPasien] Error"+e.getMessage());
                        }

                        if(dokterEntityList.size() > 0){
                            dokterEntity = dokterEntityList.get(0);
                        }
                    }

                    ImSimrsAsuransiEntity asuransiEntity = new ImSimrsAsuransiEntity();
                    if(detailCheckupEntity.getIdAsuransi() != null){
                        List<ImSimrsAsuransiEntity> asuransiEntityList = new ArrayList<>();
                        hsCriteria = new HashMap();
                        hsCriteria.put("id_asuransi", detailCheckupEntity.getIdAsuransi());
                        try{
                            asuransiEntityList = asuransiDao.getByCriteria(hsCriteria);
                        }catch (HibernateException e){
                            logger.error("[CheckupBoImpl.getDataPendaftaranPasien] Error"+e.getMessage());
                        }

                        if(asuransiEntityList.size() > 0){
                            asuransiEntity = asuransiEntityList.get(0);
                        }
                    }

                    RekananOps rekananOps = new RekananOps();
                    if(detailCheckupEntity.getIdAsuransi() != null){
                        try{
                            rekananOps = rekananOpsDao.getDetailRekananOps(detailCheckupEntity.getIdAsuransi(), headerChekupEntity.getBranchId());
                        }catch (HibernateException e){
                            logger.error("[CheckupBoImpl.getDataPendaftaranPasien] Error"+e.getMessage());
                        }
                    }

                    //checkup data
                    headerCheckup.setNoCheckup(headerChekupEntity.getNoCheckup());
                    headerCheckup.setIdPasien(headerChekupEntity.getIdPasien());
                    headerCheckup.setNama(headerChekupEntity.getNama());
                    headerCheckup.setJenisKelamin(headerChekupEntity.getJenisKelamin());
                    headerCheckup.setNoKtp(headerChekupEntity.getNoKtp());
                    headerCheckup.setTempatLahir(headerChekupEntity.getTempatLahir());
                    headerCheckup.setTglLahir(headerChekupEntity.getTglLahir());
                    if(headerChekupEntity.getTglLahir() != null){
                        headerCheckup.setUmur(CommonUtil.calculateAge(headerChekupEntity.getTglLahir(), true)+" Tahun");
                    }
                    headerCheckup.setStTglLahir(headerCheckup.getTglLahir().toString());
                    headerCheckup.setDesaId(headerChekupEntity.getDesaId());
                    headerCheckup.setJalan(headerChekupEntity.getJalan());
                    headerCheckup.setSuku(headerChekupEntity.getSuku());
                    headerCheckup.setAgama(headerChekupEntity.getAgama());
                    headerCheckup.setProfesi(headerChekupEntity.getProfesi());
                    headerCheckup.setNoTelp(headerChekupEntity.getNoTelp());
                    headerCheckup.setKeteranganKeluar(headerChekupEntity.getKeteranganKeluar());
                    headerCheckup.setTinggi(headerChekupEntity.getTinggi());
                    headerCheckup.setBerat(headerChekupEntity.getBerat());
                    headerCheckup.setStatusPerkawinan(headerChekupEntity.getStatusPerkawinan());
                    headerCheckup.setPendidikan(headerChekupEntity.getPendidikan());

                    if (headerChekupEntity.getUrlKtp() != null && !"".equalsIgnoreCase(headerChekupEntity.getUrlKtp())) {
                        String src = CommonConstant.URL_IMG + CommonConstant.RESOURCE_PATH_KTP_PASIEN + headerChekupEntity.getUrlKtp();
                        headerCheckup.setUrlKtp(src);
                    }

                    logger.info("[CheckupBoImpl.getByCriteria] URL KTP : " + headerCheckup.getUrlKtp());
                    headerCheckup.setBranchId(headerChekupEntity.getBranchId());
                    headerCheckup.setNamaPenanggung(headerChekupEntity.getNamaPenanggung());
                    headerCheckup.setHubunganKeluarga(headerChekupEntity.getHubunganKeluarga());
                    headerCheckup.setJenisKunjungan(headerChekupEntity.getJenisKunjungan());

                    //detail data
                    headerCheckup.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                    headerCheckup.setIdPelayanan(detailCheckupEntity.getIdPelayanan());
                    headerCheckup.setStatusPeriksa(detailCheckupEntity.getStatusPeriksa());
                    headerCheckup.setStatusBayar(detailCheckupEntity.getStatusBayar());
                    headerCheckup.setIdPelayananBpjs(detailCheckupEntity.getIdPelayananBpjs());
                    headerCheckup.setIdJenisPeriksaPasien(detailCheckupEntity.getIdJenisPeriksaPasien());
                    headerCheckup.setNoKartuAsuransi(detailCheckupEntity.getNoKartuAsuransi());
                    headerCheckup.setIdAsuransi(detailCheckupEntity.getIdAsuransi());
                    headerCheckup.setIsLaka(asuransiEntity.getIsLaka());
                    headerCheckup.setCoverBiaya(detailCheckupEntity.getCoverBiaya());
                    headerCheckup.setIsEksekutif(detailCheckupEntity.getIsEksekutif());
                    headerCheckup.setIsVaksin(detailCheckupEntity.getIsVaksin());
                    headerCheckup.setUrlDocRujuk(detailCheckupEntity.getUrlDocRujuk());
                    headerCheckup.setIdPaket(detailCheckupEntity.getIdPaket());

                    //data rekanan
                    headerCheckup.setIsRekananWithBpjs(rekananOps.getIsBpjs());
                    headerCheckup.setTipeRekanan(rekananOps.getTipe());

                    //dokter team data
                    headerCheckup.setIdDokter(dokterTeam.getIdDokter());
                    headerCheckup.setIdTeamDokter(dokterTeam.getIdTeamDokter());
                    headerCheckup.setNamaDokter(dokterEntity.getNamaDokter());

                    //alamat pasien
                    if (headerCheckup.getDesaId() != null) {
                        List<Object[]> objs = provinsiDao.getListAlamatByDesaId(headerCheckup.getDesaId().toString());
                        if (objs.size() > 0) {
                            Object[] obj = objs.get(0);
                            headerCheckup.setNamaDesa(obj[0].toString());
                            headerCheckup.setNamaKecamatan(obj[1].toString());
                            headerCheckup.setNamaKota(obj[2].toString());
                            headerCheckup.setNamaProvinsi(obj[3].toString());

                            headerCheckup.setKecamatanId(obj[4].toString());
                            headerCheckup.setKotaId(obj[5].toString());
                            headerCheckup.setProvinsiId(obj[6].toString());
                        }
                    }

                    if("umum".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())){
                        List<ItSimrsUangMukaPendaftaranEntity> uangMukaPendaftaranEntityList = new ArrayList<>();
                        ItSimrsUangMukaPendaftaranEntity uangMukaPendaftaranEntity = new ItSimrsUangMukaPendaftaranEntity();
                        hsCriteria = new HashMap();
                        hsCriteria.put("id_detail_checkup", detailCheckupEntity.getIdDetailCheckup());
                        try{
                            uangMukaPendaftaranEntityList = uangMukaDao.getByCriteria(hsCriteria);
                        }catch (HibernateException e){
                            logger.error("[CheckupBoImpl.getDataPendaftaranPasien] Error"+e.getMessage());
                        }

                        if(uangMukaPendaftaranEntityList.size() > 0){
                            uangMukaPendaftaranEntity = uangMukaPendaftaranEntityList.get(0);
                        }
                        //uang muka
                        headerCheckup.setUangMuka(uangMukaPendaftaranEntity.getJumlah());
                        headerCheckup.setIdUangMuka(uangMukaPendaftaranEntity.getId());
                        headerCheckup.setStatusBayar(uangMukaPendaftaranEntity.getStatusBayar());
                    }

                    if("bpjs".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())){
                        ImSimrsPasienEntity pasienEntity = pasienDao.getById("idPasien", headerChekupEntity.getIdPasien());
                        if(pasienEntity != null){
                            headerCheckup.setNoBpjs(pasienEntity.getNoBpjs());
                        }
                    }
                }
            }
        }
        logger.info("[CheckupBoImpl.getDataPendaftaranPasien] End <<<<<<<");
        return headerCheckup;
    }

    @Override
    public void updateDataPendaftaran(HeaderCheckup bean) throws GeneralBOException {
        if(bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())){
            ItSimrsHeaderChekupEntity headerEntity = headerCheckupDao.getById("noCheckup", bean.getNoCheckup());
            if(headerEntity != null){
                headerEntity.setIdPasien(bean.getIdPasien());
                headerEntity.setNama(bean.getNama());
                headerEntity.setJenisKelamin(bean.getJenisKelamin());
                headerEntity.setNoKtp(bean.getNoKtp().replace("_", ""));
                headerEntity.setTempatLahir(bean.getTempatLahir());
                if(bean.getStTglLahir() != null && !"".equalsIgnoreCase(bean.getStTglLahir())){
                    headerEntity.setTglLahir(Date.valueOf(bean.getStTglLahir()));
                }
                headerEntity.setDesaId(bean.getDesaId());
                headerEntity.setJalan(bean.getJalan());
                headerEntity.setSuku(bean.getSuku());
                headerEntity.setProfesi(bean.getProfesi() != null && !"".equalsIgnoreCase(bean.getProfesi()) ? bean.getProfesi() : null);
                if (bean.getNoTelp() != null && !"".equalsIgnoreCase(bean.getNoTelp())) {
                    String noHp = bean.getNoTelp().replace("-", "").replace("_", "");
                    headerEntity.setNoTelp(noHp);
                }
                headerEntity.setAgama(bean.getAgama());
                headerEntity.setUrlKtp(bean.getUrlKtp());
                headerEntity.setAction("U");
                headerEntity.setLastUpdate(bean.getLastUpdate());
                headerEntity.setLastUpdateWho(bean.getLastUpdateWho());
                headerEntity.setJenisKunjungan(bean.getJenisKunjungan());
                headerEntity.setNamaPenanggung(bean.getNamaPenanggung() != null && !"".equalsIgnoreCase(bean.getNamaPenanggung()) ? bean.getNamaPenanggung() : null);
                headerEntity.setHubunganKeluarga(bean.getHubunganKeluarga() != null && !"".equalsIgnoreCase(bean.getHubunganKeluarga()) ? bean.getHubunganKeluarga() : null);
                headerEntity.setBerat(bean.getBerat());
                headerEntity.setTinggi(bean.getTinggi());
                headerEntity.setPendidikan(bean.getPendidikan());
                headerEntity.setStatusPerkawinan(bean.getStatusPerkawinan());
                headerEntity.setKunjunganPoli(bean.getKunjunganPoli());

                try {
                    headerCheckupDao.updateAndSave(headerEntity);
                    updateDataPasien(headerEntity);
                } catch (HibernateException e) {
                    logger.error("[CheckupBoImpl.saveAdd] Error When Saving data header checkup" + e.getMessage());
                    throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving data header checkup");
                }
            }

            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());
                if(detailCheckupEntity != null){
                    detailCheckupEntity.setNoCheckup(headerEntity.getNoCheckup());
                    detailCheckupEntity.setIdPelayanan(bean.getIdPelayanan());
                    detailCheckupEntity.setIdJenisPeriksaPasien(bean.getIdJenisPeriksaPasien());

                    if ("asuransi".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "bpjs_rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                        detailCheckupEntity.setMetodePembayaran("non_tunai");
                    }else{
                        if("umum".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
                            detailCheckupEntity.setMetodePembayaran("tunai");
                        }
                    }

                    detailCheckupEntity.setNoRujukan(bean.getNoRujukan() != null && !"".equalsIgnoreCase(bean.getNoRujukan()) ? bean.getNoRujukan() : null);
                    if(bean.getTglRujukan() != null && !"".equalsIgnoreCase(bean.getTglRujukan())){
                        detailCheckupEntity.setTglRujukan(Date.valueOf(bean.getTglRujukan()));
                    }

                    detailCheckupEntity.setUrlDocRujuk(bean.getUrlDocRujuk() != null && !"".equalsIgnoreCase(bean.getUrlDocRujuk()) ? bean.getUrlDocRujuk() : null);
                    detailCheckupEntity.setIdPaket(bean.getIdPaket() != null && !"".equalsIgnoreCase(bean.getIdPaket()) ? bean.getIdPaket() : null);
                    detailCheckupEntity.setIdAsuransi(bean.getIdAsuransi() != null && !"".equalsIgnoreCase(bean.getIdAsuransi()) ? bean.getIdAsuransi() : null);
                    detailCheckupEntity.setCoverBiaya(bean.getCoverBiaya() != null && !"".equalsIgnoreCase(bean.getCoverBiaya().toString()) ? bean.getCoverBiaya() : null);
                    detailCheckupEntity.setNoKartuAsuransi(bean.getNoKartuAsuransi() != null && !"".equalsIgnoreCase(bean.getNoKartuAsuransi()) ? bean.getNoKartuAsuransi() : null);
                    detailCheckupEntity.setAction("U");
                    detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                    detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    detailCheckupEntity.setIsEksekutif(bean.getIsEksekutif());
                    detailCheckupEntity.setIsVaksin(bean.getIsVaksin());

                    try {
                        checkupDetailDao.updateAndSave(detailCheckupEntity);
                    } catch (HibernateException e) {
                        logger.error("[CheckupBoImpl.saveAdd] Error When Saving data header checkup" + e.getMessage());
                        throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving data header checkup");
                    }
                }

                if(bean.getIdTeamDokter() != null && !"".equalsIgnoreCase(bean.getIdTeamDokter())){
                    ItSimrsDokterTeamEntity dokterTeamEntity = dokterTeamDao.getById("idTeamDokter", bean.getIdTeamDokter());
                    if(dokterTeamEntity != null){
                        dokterTeamEntity.setIdDokter(bean.getIdDokter());
                        dokterTeamEntity.setAction("U");
                        dokterTeamEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        dokterTeamEntity.setLastUpdate(bean.getLastUpdate());
                        try {
                            dokterTeamDao.updateAndSave(dokterTeamEntity);
                        }catch (HibernateException e){
                            logger.error("[CheckupBoImpl.saveAdd] Error When Saving data header checkup" + e.getMessage());
                            throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving data header checkup");
                        }
                    }
                }

                if("umum".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())){
                    ItSimrsUangMukaPendaftaranEntity uangMukaPendaftaranEntity = uangMukaDao.getById("id", bean.getIdUangMuka());
                    if(uangMukaPendaftaranEntity != null){
                        if(bean.getUangMuka() != null && !"".equalsIgnoreCase(bean.getUangMuka().toString())){
                            uangMukaPendaftaranEntity.setJumlah(bean.getUangMuka());
                        }
                        uangMukaPendaftaranEntity.setAction("U");
                        uangMukaPendaftaranEntity.setLastUpdate(bean.getLastUpdate());
                        uangMukaPendaftaranEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        try {
                            uangMukaDao.updateAndSave(uangMukaPendaftaranEntity);
                        }catch (HibernateException e){
                            logger.error("[CheckupBoImpl.saveAdd] Error When Saving data header checkup" + e.getMessage());
                            throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving data header checkup");
                        }
                    }
                }
            }
        }
    }

    private CrudResponse saveRawatInap(RawatInap bean) {
        logger.info("[CheckupDetailBoImpl.saveRawatInap] Start >>>>>>>>");
        CrudResponse response = new CrudResponse();
        if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup()) &&
                bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            ItSimrsRawatInapEntity entity = new ItSimrsRawatInapEntity();
            TempatTidur tempatTidur = new TempatTidur();
            if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())) {
                TempatTidur tt = new TempatTidur();
                tt.setIdTempatTidur(bean.getIdRuangan());
                List<TempatTidur> tempatTidurList = tempatTidurDao.getDataTempatTidur(tt);
                if (tempatTidurList.size() > 0) {
                    tempatTidur = tempatTidurList.get(0);
                    if (tempatTidur != null) {
                        entity.setIdRuangan(tempatTidur.getIdTempatTidur());
                        entity.setTarif(tempatTidur.getTarif());
                        entity.setNamaRangan(tempatTidur.getNamaRuangan());
                        entity.setNoRuangan(tempatTidur.getNoRuangan());
                    }
                }
                entity.setIdRawatInap("RNP" + rawatInapDao.getNextId());
                entity.setIdDetailCheckup(bean.getIdDetailCheckup());
                entity.setNoCheckup(bean.getNoCheckup());
                entity.setFlag("Y");
                entity.setAction("C");
                entity.setCreatedDate(bean.getCreatedDate());
                entity.setLastUpdate(bean.getCreatedDate());
                entity.setCreatedWho(bean.getCreatedWho());
                entity.setLastUpdateWho(bean.getCreatedWho());
                entity.setTglMasuk(bean.getTglMasuk());
                entity.setStatus(bean.getStatus());
                entity.setKeterangan(bean.getKeterangan());

                try {
                    rawatInapDao.addAndSave(entity);
                    response.setStatus("success");
                    response.setMsg("berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg(" Error when save add Rawat Inap " + e.getMessage());
                    logger.error("[CheckupDetailBoImpl.saveRawatInap] Error when save add Rawat Inap ", e);
                    throw new GeneralBOException("[CheckupDetailBoImpl.saveRawatInap] Error when save add  Rawat Inap " + e.getMessage());
                }

                // set ketersedian ruangan to tidak tersedia
                if (tempatTidur != null) {
                    MtSimrsRuanganTempatTidurEntity tempatTidurEntity = tempatTidurDao.getById("idTempatTidur", tempatTidur.getIdTempatTidur());
                    if(tempatTidurEntity != null){
                        tempatTidurEntity.setStatus("N");
                        tempatTidurEntity.setAction("U");
                        tempatTidurEntity.setLastUpdate(bean.getCreatedDate());
                        tempatTidurEntity.setLastUpdateWho(bean.getCreatedWho());
                        try {
                            tempatTidurDao.updateAndSave(tempatTidurEntity);
                            response.setStatus("success");
                            response.setMsg(entity.getIdRawatInap());
                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMsg("Error When Saving data detail checkup " + e.getMessage());
                            logger.error("[CheckupDetailBoImpl.saveRawatInap] Error when Update data ruangan ", e);
                            throw new GeneralBOException("[CheckupDetailBoImpl.saveRawatInap] Error when Update data ruangan " + e.getMessage());
                        }
                    }
                }
            }
        }
        logger.info("[CheckupDetailBoImpl.saveRawatInap] End <<<<<<<<");
        return response;
    }

    public ItSimrsHeaderChekupEntity getById(String columnName, String id) throws GeneralBOException {
        return headerCheckupDao.getById(columnName, id);
    }

    @Override
    public List<MasterVendor> getComboListPtpn() throws GeneralBOException {
        List<MasterVendor> vendorList = new ArrayList<>();
        try {
            vendorList = headerCheckupDao.getComboListPtpn();
        } catch (HibernateException e) {
            logger.error("Found Error " + e.getMessage());
        }
        return vendorList;
    }

    private String getNextDetailLapId() {
        String id = "";
        try {
            id = periksaLabDetailDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PeriksaLabBoImpl.getNextDetailLapId] ERROR When create sequences", e);
        }
        return id;
    }

    private String getNextPeriksaRadiologId() throws GeneralBOException {
        String id = "";
        try {
            id = periksaRadiologiDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PeriksaRadiologiBoImpl.getNextPeriksaRadiologId] ERROR when generate new id, ", e.getCause());
            throw new GeneralBOException("[PeriksaRadiologiBoImpl.getNextPeriksaRadiologId] ERROR when generate new id, ", e.getCause());
        }
        return id;
    }

    private String getNextPeriksaLabId() {
        String id = "";
        try {
            id = periksaLabDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PeriksaLabBoImpl.getNextPeriksaLabId] ERROR When create sequences", e);
        }
        return id;
    }

    private String getNextIdAlergi() {
        String id = "";
        try {
            id = checkupAlergiDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.saveEditAlergi] Error when get seq ", e);
            throw new GeneralBOException("[CheckupBoImpl.updatePenunjang] Error when get seq " + e.getMessage());
        }
        return id;
    }

    private String getNextTindakanRawatId() {
        String id = "";
        try {
            id = tindakanRawatDao.getNextTindakanRawatId();
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getNextTindakanRawatId] Error when get seq ", e);
            throw new GeneralBOException("[CheckupBoImpl.getNextTindakanRawatId] Error when get seq " + e.getMessage());
        }
        return id;
    }

    private String getNextIdPemeriksaanFisik() {
        String id = "";
        try {
            id = pemeriksaanFisikDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getNextIdPemeriksaanFisik] Error when get seq ", e);
            throw new GeneralBOException("[CheckupBoImpl.getNextIdPemeriksaanFisik] Error when get seq " + e.getMessage());
        }
        return id;
    }

    private String getNextIdResikoJatuh() {
        String id = "";
        try {
            id = resikoJatuhDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getNextIdResikoJatuh] Error when get seq ", e);
            throw new GeneralBOException("[CheckupBoImpl.getNextIdResikoJatuh] Error when get seq " + e.getMessage());
        }
        return id;
    }

    private String getNextPsikososialId() {
        String id = "";
        try {
            id = psikososialDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getNextPsikososialId] Error when get seq ", e);
            throw new GeneralBOException("[CheckupBoImpl.getNextPsikososialId] Error when get seq " + e.getMessage());
        }
        return id;
    }

    private String getNextRencanaRawatId() {
        String id = "";
        try {
            id = rencanaRawatDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getNextRencanaRawatId] Error when get seq ", e);
            throw new GeneralBOException("[CheckupBoImpl.getNextRencanaRawatId] Error when get seq " + e.getMessage());
        }
        return id;
    }


    private String getIdTranfusi() {
        String id = "";
        try {
            id = tranfusiDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getIdTranfusi] Error when get seq ", e);
            throw new GeneralBOException("[CheckupBoImpl.getIdTranfusi] Error when get seq " + e.getMessage());
        }
        return id;
    }

    private String getIdPatrus() {
        String id = "";
        try {
            id = patrusDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getIdPatrus] Error when get seq ", e);
            throw new GeneralBOException("[CheckupBoImpl.getIdPatrus] Error when get seq " + e.getMessage());
        }
        return id;
    }

    private String getIdRekonsiliasi() {
        String id = "";
        try {
            id = rekonsiliasiObatDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getIdRekonsiliasi] Error when get seq ", e);
            throw new GeneralBOException("[CheckupBoImpl.getIdRekonsiliasi] Error when get seq " + e.getMessage());
        }
        return id;
    }

    private String getNextIdRiwayatTindakan() {
        String id = "";
        try {
            id = riwayatTindakanDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[RiwayatTindakanBoImpl.getNextIdRiwayatTindakan] ERROR When create sequences", e);
        }
        return id;
    }

    private String getNextIdAsesmen() {
        String id = "";
        try {
            id = asesmenDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[RiwayatTindakanBoImpl.getNextIdRiwayatTindakan] ERROR When create sequences", e);
        }
        return id;
    }

    private String getNextIdPermintaanResep() {
        String id = "";
        try {
            id = permintaanResepDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[RiwayatTindakanBoImpl.getNextIdPermintaanResep] ERROR When create sequences", e);
        }
        return id;
    }

    private String getNextIdApprovalObat() {
        String id = "";
        try {
            id = approvalTransaksiObatDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[RiwayatTindakanBoImpl.getNextIdApprovalObat] ERROR When create sequences", e);
        }
        return id;
    }

    private String getNextIdTransObatDetail() {
        String id = "";
        try {
            id = transaksiObatDetailDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[RiwayatTindakanBoImpl.getNextIdTransObatDetail] ERROR When create sequences", e);
        }
        return id;
    }

    public void setHeaderCheckupDao(HeaderCheckupDao headerCheckupDao) {
        this.headerCheckupDao = headerCheckupDao;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setProvinsiDao(ProvinsiDao provinsiDao) {
        this.provinsiDao = provinsiDao;
    }

    public void setDokterTeamDao(DokterTeamDao dokterTeamDao) {
        this.dokterTeamDao = dokterTeamDao;
    }

    public void setCheckupAlergiDao(CheckupAlergiDao checkupAlergiDao) {
        this.checkupAlergiDao = checkupAlergiDao;
    }

    public void setDiagnosaRawatDao(DiagnosaRawatDao diagnosaRawatDao) {
        this.diagnosaRawatDao = diagnosaRawatDao;
    }

    public void setTindakanDao(TindakanDao tindakanDao) {
        this.tindakanDao = tindakanDao;
    }

    public void setTindakanRawatDao(TindakanRawatDao tindakanRawatDao) {
        this.tindakanRawatDao = tindakanRawatDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public CheckupBoImpl() throws GeneralSecurityException, IOException {
    }

    public void setPemeriksaanFisikDao(PemeriksaanFisikDao pemeriksaanFisikDao) {
        this.pemeriksaanFisikDao = pemeriksaanFisikDao;
    }

    public void setKategoriResikoJatuhDao(KategoriResikoJatuhDao kategoriResikoJatuhDao) {
        this.kategoriResikoJatuhDao = kategoriResikoJatuhDao;
    }

    public void setParameterResikoJatuhDao(ParameterResikoJatuhDao parameterResikoJatuhDao) {
        this.parameterResikoJatuhDao = parameterResikoJatuhDao;
    }

    public void setSkorResikoJatuhDao(SkorResikoJatuhDao skorResikoJatuhDao) {
        this.skorResikoJatuhDao = skorResikoJatuhDao;
    }

    public void setResikoJatuhDao(ResikoJatuhDao resikoJatuhDao) {
        this.resikoJatuhDao = resikoJatuhDao;
    }

    public void setPsikososialDao(PsikososialDao psikososialDao) {
        this.psikososialDao = psikososialDao;
    }

    public void setKategoriRencanaRawatDao(KategoriRencanaRawatDao kategoriRencanaRawatDao) {
        this.kategoriRencanaRawatDao = kategoriRencanaRawatDao;
    }

    public void setParameterRencanaRawatDao(ParameterRencanaRawatDao parameterRencanaRawatDao) {
        this.parameterRencanaRawatDao = parameterRencanaRawatDao;
    }

    public void setRencanaRawatDao(RencanaRawatDao rencanaRawatDao) {
        this.rencanaRawatDao = rencanaRawatDao;
    }

    public void setPatrusDao(PatrusDao patrusDao) {
        this.patrusDao = patrusDao;
    }

    public void setTranfusiDao(TranfusiDao tranfusiDao) {
        this.tranfusiDao = tranfusiDao;
    }

    public void setRekonsiliasiObatDao(RekonsiliasiObatDao rekonsiliasiObatDao) {
        this.rekonsiliasiObatDao = rekonsiliasiObatDao;
    }

    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
    }

    public void setDokterDao(DokterDao dokterDao) {
        this.dokterDao = dokterDao;
    }

    public void setRekamMedicLamaDao(RekamMedicLamaDao rekamMedicLamaDao) {
        this.rekamMedicLamaDao = rekamMedicLamaDao;
    }

    public void setUploadRekamMedicLamaDao(UploadRekamMedicLamaDao uploadRekamMedicLamaDao) {
        this.uploadRekamMedicLamaDao = uploadRekamMedicLamaDao;
    }

    public void setUangMukaDao(UangMukaDao uangMukaDao) {
        this.uangMukaDao = uangMukaDao;
    }

    public void setAntrianOnlineDao(AntrianOnlineDao antrianOnlineDao) {
        this.antrianOnlineDao = antrianOnlineDao;
    }

    public void setTransaksiTindakanBpjsDao(TransaksiTindakanBpjsDao transaksiTindakanBpjsDao) {
        this.transaksiTindakanBpjsDao = transaksiTindakanBpjsDao;
    }

    public void setAsesmenDao(AsesmenDao asesmenDao) {
        this.asesmenDao = asesmenDao;
    }

    public void setPermintaanResepDao(PermintaanResepDao permintaanResepDao) {
        this.permintaanResepDao = permintaanResepDao;
    }

    public void setApprovalTransaksiObatDao(ApprovalTransaksiObatDao approvalTransaksiObatDao) {
        this.approvalTransaksiObatDao = approvalTransaksiObatDao;
    }

    public void setTransaksiObatDetailDao(TransaksiObatDetailDao transaksiObatDetailDao) {
        this.transaksiObatDetailDao = transaksiObatDetailDao;
    }

    public void setPeriksaLabDao(PeriksaLabDao periksaLabDao) {
        this.periksaLabDao = periksaLabDao;
    }

    public void setPeriksaLabDetailDao(PeriksaLabDetailDao periksaLabDetailDao) {
        this.periksaLabDetailDao = periksaLabDetailDao;
    }

    public void setPeriksaRadiologiDao(PeriksaRadiologiDao periksaRadiologiDao) {
        this.periksaRadiologiDao = periksaRadiologiDao;
    }

    public void setPaketDao(PaketDao paketDao) {
        this.paketDao = paketDao;
    }

    public void setItemPaketDao(ItemPaketDao itemPaketDao) {
        this.itemPaketDao = itemPaketDao;
    }

    public void setPaketPasienDao(PaketPasienDao paketPasienDao) {
        this.paketPasienDao = paketPasienDao;
    }

    public void setLabDetailDao(LabDetailDao labDetailDao) {
        this.labDetailDao = labDetailDao;
    }

    public void setOrderPeriksaLabDao(OrderPeriksaLabDao orderPeriksaLabDao) {
        this.orderPeriksaLabDao = orderPeriksaLabDao;
    }

    public void setRekananOpsDao(RekananOpsDao rekananOpsDao) {
        this.rekananOpsDao = rekananOpsDao;
    }

    public void setDetailPaketDao(DetailPaketDao detailPaketDao) {
        this.detailPaketDao = detailPaketDao;
    }

    public void setPelayananPaketDao(PelayananPaketDao pelayananPaketDao) {
        this.pelayananPaketDao = pelayananPaketDao;
    }

    public void setTempatTidurDao(TempatTidurDao tempatTidurDao) {
        this.tempatTidurDao = tempatTidurDao;
    }

    public void setRawatInapDao(RawatInapDao rawatInapDao) {
        this.rawatInapDao = rawatInapDao;
    }

    public void setPasienDao(PasienDao pasienDao) {
        this.pasienDao = pasienDao;
    }

    public void setHeaderPemeriksaanDao(HeaderPemeriksaanDao headerPemeriksaanDao) {
        this.headerPemeriksaanDao = headerPemeriksaanDao;
    }

    public void setLabDao(LabDao labDao) {
        this.labDao = labDao;
    }

    public void setAsuransiDao(AsuransiDao asuransiDao) {
        this.asuransiDao = asuransiDao;
    }
}
