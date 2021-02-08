package com.neurix.akuntansi.transaksi.billingSystem.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.mappingJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.master.master.dao.MasterDao;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;
import com.neurix.akuntansi.master.trans.dao.TransDao;
import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.jurnal.dao.*;
import com.neurix.akuntansi.transaksi.jurnal.model.*;
import com.neurix.akuntansi.transaksi.saldoakhir.model.SaldoAkhir;
import com.neurix.akuntansi.transaksi.tutupperiod.bo.impl.TutupPeriodBoImpl;
import com.neurix.akuntansi.transaksi.tutupperiod.dao.BatasTutupPeriodDao;
import com.neurix.akuntansi.transaksi.tutupperiod.model.BatasTutupPeriod;
import com.neurix.akuntansi.transaksi.tutupperiod.model.ItSimrsBatasTutupPeriodEntity;
import com.neurix.akuntansi.transaksi.tutupperiod.model.TutupPeriod;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.company.model.ImBranchesPK;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisperiksapasien.dao.AsuransiDao;
import com.neurix.simrs.master.jenisperiksapasien.dao.JenisPeriksaPasienDao;
import com.neurix.simrs.master.jenisperiksapasien.model.ImJenisPeriksaPasienEntity;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.dao.KelasRuanganDao;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import com.neurix.simrs.master.obat.dao.ObatDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.dao.RuanganDao;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.transaksi.bataltelemedic.model.ItSimrsBatalTelemedicEntity;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.obatpoli.dao.ObatPoliDao;
import com.neurix.simrs.transaksi.obatpoli.model.MtSimrsObatPoliEntity;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.model.ItSimrsPeriksaLabEntity;
import com.neurix.simrs.transaksi.periksaradiologi.bo.PeriksaRadiologiBo;
import com.neurix.simrs.transaksi.periksaradiologi.model.ItSimrsPeriksaRadiologiEntity;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.dao.PermintaanResepDao;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayatbarang.dao.TransaksiStokDao;
import com.neurix.simrs.transaksi.riwayatbarang.model.ItSimrsTransaksiStokEntity;
import com.neurix.simrs.transaksi.riwayatbarang.model.TransaksiStok;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.dao.TindakanTransitorisDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsTindakanTransitorisEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.dao.DokterTeamDao;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import io.agora.recording.common.Common;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class BillingSystemBoImpl extends TutupPeriodBoImpl implements BillingSystemBo  {

    protected static transient Logger logger = Logger.getLogger(BillingSystemBoImpl.class);

    private MappingJurnalDao mappingJurnalDao;
    private String userLogin;
    private Timestamp updateTime;
    private TransDao transDao;
    private TransaksiStokDao transaksiStokDao;
    private ObatDao obatDao;

    private CheckupDetailDao checkupDetailDao;
    private HeaderCheckupDao headerCheckupDao;
    private JenisPeriksaPasienDao jenisPeriksaPasienDao;
    private TindakanTransitorisDao tindakanTransitorisDao;
    private PermintaanResepDao permintaanResepDao;
    private RawatInapDao rawatInapDao;
    private KelasRuanganDao kelasRuanganDao;
    private RuanganDao ruanganDao;
    private PelayananDao pelayananDao;
    private DokterTeamDao dokterTeamDao;
    private PositionDao positionDao;
    private MasterDao masterDao;
    private AsuransiDao asuransiDao;
    private ObatPoliDao obatPoliDao;
    private BranchDao branchDao;
    private JurnalAkhirTahunDao jurnalAkhirTahunDao;
    private JurnalDetailAkhirTahunDao jurnalDetailAkhirTahunDao;

    public void setJurnalAkhirTahunDao(JurnalAkhirTahunDao jurnalAkhirTahunDao) {
        this.jurnalAkhirTahunDao = jurnalAkhirTahunDao;
    }

    public void setJurnalDetailAkhirTahunDao(JurnalDetailAkhirTahunDao jurnalDetailAkhirTahunDao) {
        this.jurnalDetailAkhirTahunDao = jurnalDetailAkhirTahunDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public void setObatPoliDao(ObatPoliDao obatPoliDao) {
        this.obatPoliDao = obatPoliDao;
    }

    public void setTransaksiStokDao(TransaksiStokDao transaksiStokDao) {
        this.transaksiStokDao = transaksiStokDao;
    }

    public void setObatDao(ObatDao obatDao) {
        this.obatDao = obatDao;
    }

    public void setAsuransiDao(AsuransiDao asuransiDao) {
        this.asuransiDao = asuransiDao;
    }

    public void setMasterDao(MasterDao masterDao) {
        this.masterDao = masterDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public void setPelayananDao(PelayananDao pelayananDao) {
        this.pelayananDao = pelayananDao;
    }

    public void setDokterTeamDao(DokterTeamDao dokterTeamDao) {
        this.dokterTeamDao = dokterTeamDao;
    }

    public void setTindakanTransitorisDao(TindakanTransitorisDao tindakanTransitorisDao) {
        this.tindakanTransitorisDao = tindakanTransitorisDao;
    }

    public void setPermintaanResepDao(PermintaanResepDao permintaanResepDao) {
        this.permintaanResepDao = permintaanResepDao;
    }

    public void setRawatInapDao(RawatInapDao rawatInapDao) {
        this.rawatInapDao = rawatInapDao;
    }

    public void setKelasRuanganDao(KelasRuanganDao kelasRuanganDao) {
        this.kelasRuanganDao = kelasRuanganDao;
    }

    public void setRuanganDao(RuanganDao ruanganDao) {
        this.ruanganDao = ruanganDao;
    }

    public void setJenisPeriksaPasienDao(JenisPeriksaPasienDao jenisPeriksaPasienDao) {
        this.jenisPeriksaPasienDao = jenisPeriksaPasienDao;
    }

    public void setHeaderCheckupDao(HeaderCheckupDao headerCheckupDao) {
        this.headerCheckupDao = headerCheckupDao;
    }

    @Override
    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setBatasTutupPeriodDao(BatasTutupPeriodDao batasTutupPeriodDao) {
        this.batasTutupPeriodDao = batasTutupPeriodDao;
    }

    public void setJurnalDetailActivityDao(JurnalDetailActivityDao jurnalDetailActivityDao) {
        this.jurnalDetailActivityDao = jurnalDetailActivityDao;
    }

    public void setTransDao(TransDao transDao) {
        this.transDao = transDao;
    }

    public void setKodeRekeningDao(KodeRekeningDao kodeRekeningDao) {
        this.kodeRekeningDao = kodeRekeningDao;
    }

    public MappingJurnalDao getMappingJurnalDao() {
        return mappingJurnalDao;
    }

    public void setMappingJurnalDao(MappingJurnalDao mappingJurnalDao) {
        this.mappingJurnalDao = mappingJurnalDao;
    }

    public void setJurnalDao(JurnalDao jurnalDao) {
        this.jurnalDao = jurnalDao;
    }

    public void setJurnalDetailDao(JurnalDetailDao jurnalDetailDao) {
        this.jurnalDetailDao = jurnalDetailDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        BillingSystemBoImpl.logger = logger;
    }

    @Override
    public String createInvoiceNumber(String jurnalId,String branchId){
        logger.info("[PembayaranUtangPiutangBoImpl.createInvoiceNumber] start process >>>");
        String invoice ="";
        try {
            invoice = mappingJurnalDao.getNextInvoiceId(jurnalId,branchId);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        logger.info("[PembayaranUtangPiutangBoImpl.createInvoiceNumber] end process >>>");
        return invoice;
    }

    @Override
    public Jurnal createJurnal(String transId, Map data, String branchId, String catatanPembuatanJurnal, String flagRegister){
        logger.info("[PembayaranUtangPiutangBoImpl.createJurnal] start process >>>");
        String noJurnal;
        String status;
        if (data.get("user_id") != null ){
            if(!data.get("user_id").toString().equalsIgnoreCase("")) {
                userLogin = data.get("user_id").toString();
            } else userLogin = CommonUtil.userIdLogin();
        } else userLogin = CommonUtil.userIdLogin();
        updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String tipeJurnalId;
        String sumber = null;
        Jurnal returnJurnal = new Jurnal();

        //mencari tipe jurnal Id
        try {
            tipeJurnalId = mappingJurnalDao.tipeJurnalByTransId(transId);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        //Pencarian di tipe jurnal apakah jurnal ini perlu membuat sumber baru
        ImTransEntity transEntity;
        try {
            transEntity = transDao.getById("transId",transId);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        Date tanggalSekarang = new Date();
        DateTime datetime = new DateTime(tanggalSekarang);

        DateTime bulanBerikutnya = new DateTime(tanggalSekarang);
        bulanBerikutnya = bulanBerikutnya.plusMonths(1).withDayOfMonth(1);

        String bulanSekarang = String.valueOf(Integer.parseInt(datetime.toString("MM")));
        String tahunSekarang = datetime.toString("YYYY");
        String periodSudahTutup=null;

        List<ItSimrsBatasTutupPeriodEntity> batasTutupPeriodEntityList = batasTutupPeriodDao.getBatasTutupPeriod(branchId,bulanSekarang,tahunSekarang);
        for (ItSimrsBatasTutupPeriodEntity simrsBatasTutupPeriodEntity : batasTutupPeriodEntityList){
            periodSudahTutup=simrsBatasTutupPeriodEntity.getFlagTutup();
        }

        // perbaikan periode
        String isTransitoris = checkIsJurnalTransitoris(transId);
        if ("Y".equalsIgnoreCase(isTransitoris)){
            periodSudahTutup=null;
        }

        String pengajuanId=null;
        //mengambil pengajuan Id
        if (data.get("pengajuan_id")!=null){
            pengajuanId = (String) data.get("pengajuan_id");
        }

        String sumberDana=null;
        //mengambil Sumber Dana
        if (data.get("sumber_dana")!=null){
            sumberDana = (String) data.get("sumber_dana");
        }

        if (tipeJurnalId!=null){
            try {
                // Generating ID, get from postgre sequence
                noJurnal=jurnalDao.getNextJurnalId();

                sumber = createJurnalDetail(data,noJurnal,tipeJurnalId,transId,periodSudahTutup,branchId);

//                // MEMBUAT JURNAL DETAIL TERLEBIH DAHULU UNTUK MENGAMBIL NOMOR INVOICE DARI PEMBAYARAN
//                if (("Y").equalsIgnoreCase(transEntity.getFlagSumberBaru())){
//                    createJurnalDetail(data,noJurnal,tipeJurnalId,transId,periodSudahTutup);
//                    sumber = createInvoiceNumber(tipeJurnalId,branchId);
//                }else{
//                }

                //MEMBUAT JURNAL HEADER
                ItJurnalEntity jurnalEntity = new ItJurnalEntity();
                jurnalEntity.setNoJurnal(noJurnal);
                jurnalEntity.setPengajuanBiayaId(pengajuanId);
                jurnalEntity.setTipeJurnalId(tipeJurnalId);
                jurnalEntity.setTanggalJurnal(new java.sql.Date(tanggalSekarang.getTime()));
                jurnalEntity.setMataUangId("032");
                jurnalEntity.setKurs(BigDecimal.valueOf(1));
                jurnalEntity.setKeterangan(catatanPembuatanJurnal);
                jurnalEntity.setSumber(sumber);
                jurnalEntity.setSumberDanaId(sumberDana);
                jurnalEntity.setPrintRegisterCount(BigDecimal.ZERO);
                jurnalEntity.setPrintCount(BigDecimal.ZERO);
                if (("Y").equalsIgnoreCase(flagRegister)){
                    jurnalEntity.setRegisteredFlag("Y");
                    jurnalEntity.setRegisteredUser(userLogin);
                    jurnalEntity.setRegisteredDate(new java.sql.Date(new Date().getTime()));
                }
                jurnalEntity.setBranchId(branchId);
                jurnalEntity.setFlag("Y");
                jurnalEntity.setCreatedWho(userLogin);
                jurnalEntity.setLastUpdateWho(userLogin);
                jurnalEntity.setCreatedDate(updateTime);
                jurnalEntity.setLastUpdate(updateTime);
                jurnalEntity.setAction("C");
                // dirubah oleh sigit penambahan filter JKR 2020-09-15
                if (periodSudahTutup==null || "JKR".equalsIgnoreCase(jurnalEntity.getTipeJurnalId())){
                    try {
                        jurnalDao.addAndSave(jurnalEntity);
                    } catch (HibernateException e) {
                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnal] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }
                }else if ("Y".equalsIgnoreCase(periodSudahTutup)){
                    jurnalEntity.setTanggalJurnal(new java.sql.Date(bulanBerikutnya.getMillis()));
                    try {
                        jurnalDao.addAndSave(jurnalEntity);
                    } catch (HibernateException e) {
                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnal] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }
                }else{
                    ItJurnalPendingEntity pending = new ItJurnalPendingEntity();
                    pending.setNoJurnal(noJurnal);
                    pending.setTipeJurnalId(tipeJurnalId);
                    pending.setTanggalJurnal(new java.sql.Date(tanggalSekarang.getTime()));
                    pending.setMataUangId("032");
                    pending.setKurs(BigDecimal.valueOf(1));
                    pending.setKeterangan(catatanPembuatanJurnal);
                    pending.setSumber(sumber);
                    pending.setPrintRegisterCount(BigDecimal.ZERO);
                    pending.setPrintCount(BigDecimal.ZERO);
                    if (("Y").equalsIgnoreCase(flagRegister)){
                        pending.setRegisteredFlag("Y");
                        pending.setRegisteredUser(userLogin);
                        pending.setRegisteredDate(new java.sql.Date(new Date().getTime()));
                    }
                    pending.setBranchId(branchId);
                    pending.setFlag("Y");
                    pending.setCreatedWho(userLogin);
                    pending.setLastUpdateWho(userLogin);
                    pending.setCreatedDate(updateTime);
                    pending.setLastUpdate(updateTime);
                    pending.setAction("C");

                    try {
                        jurnalDao.addAndSavePending(pending);
                    } catch (HibernateException e) {
                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnal] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }
                }

                returnJurnal.setNoJurnal(noJurnal);
                returnJurnal.setSumber(sumber);
            } catch (Exception e){
                logger.error("[PembayaranUtangPiutangBoImpl.createJurnal]"+e);
                throw new GeneralBOException("Found problem : "+e+", please info to your admin...");
            }
        }else{
            status="ERROR : tidak bisa menemukan Tipe Jurnal Id";
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnal]"+status);
            throw new GeneralBOException("Found problem when "+status+", please info to your admin...");
        }
        logger.info("[PembayaranUtangPiutangBoImpl.createJurnal] End process <<<");
        return returnJurnal;
    }

    public Jurnal createJurnalTutupTahun(String transId, Map data, String branchId, String catatanPembuatanJurnal, String flagRegister, String tglJurnal, String tipePeriode){
        logger.info("[PembayaranUtangPiutangBoImpl.createJurnal] start process >>>");
        String noJurnal;
        String status;
        userLogin = CommonUtil.userIdLogin();
        updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String tipeJurnalId;
        String sumber = null;
        Jurnal returnJurnal = new Jurnal();

        //mencari tipe jurnal Id
        try {
            tipeJurnalId = mappingJurnalDao.tipeJurnalByTransId(transId);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        //Pencarian di tipe jurnal apakah jurnal ini perlu membuat sumber baru
        ImTransEntity transEntity;
        try {
            transEntity = transDao.getById("transId",transId);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        Date tanggalSekarang = new Date();
        DateTime datetime = new DateTime(tanggalSekarang);

        DateTime bulanBerikutnya = new DateTime(tanggalSekarang);
        bulanBerikutnya = bulanBerikutnya.plusMonths(1).withDayOfMonth(1);

        String bulanSekarang = String.valueOf(Integer.parseInt(datetime.toString("MM")));
        String tahunSekarang = datetime.toString("YYYY");
        String periodSudahTutup=null;

        List<ItSimrsBatasTutupPeriodEntity> batasTutupPeriodEntityList = batasTutupPeriodDao.getBatasTutupPeriod(branchId,bulanSekarang,tahunSekarang);
        for (ItSimrsBatasTutupPeriodEntity simrsBatasTutupPeriodEntity : batasTutupPeriodEntityList){
            periodSudahTutup=simrsBatasTutupPeriodEntity.getFlagTutup();
        }

        // perbaikan periode
        String isTransitoris = checkIsJurnalTransitoris(transId);
        if ("Y".equalsIgnoreCase(isTransitoris)){
            periodSudahTutup=null;
        }

        String pengajuanId=null;
        //mengambil pengajuan Id
        if (data.get("pengajuan_id")!=null){
            pengajuanId = (String) data.get("pengajuan_id");
        }

        String sumberDana=null;
        //mengambil Sumber Dana
        if (data.get("sumber_dana")!=null){
            sumberDana = (String) data.get("sumber_dana");
        }

        java.sql.Date tglNow = new java.sql.Date(tanggalSekarang.getTime());
        java.sql.Date tglJurnalCreate = java.sql.Date.valueOf(tglJurnal);
        boolean isJurnalCreate = tglJurnalCreate.after(tglNow);

        if (tipeJurnalId!=null){
            try {
                // Generating ID, get from postgre sequence
                noJurnal=jurnalAkhirTahunDao.getNextJurnalId();

                sumber = createJurnalDetailAkhirTahun(data,noJurnal,tipeJurnalId,transId,periodSudahTutup,branchId);

//                // MEMBUAT JURNAL DETAIL TERLEBIH DAHULU UNTUK MENGAMBIL NOMOR INVOICE DARI PEMBAYARAN
//                if (("Y").equalsIgnoreCase(transEntity.getFlagSumberBaru())){
//                    createJurnalDetail(data,noJurnal,tipeJurnalId,transId,periodSudahTutup);
//                    sumber = createInvoiceNumber(tipeJurnalId,branchId);
//                }else{
//                }

                //MEMBUAT JURNAL HEADER
                ItAkunJurnalAkhirTahunEntity jurnalEntity = new ItAkunJurnalAkhirTahunEntity();
                jurnalEntity.setNoJurnal(noJurnal);
                jurnalEntity.setPengajuanBiayaId(pengajuanId);
                jurnalEntity.setTipeJurnalId(tipeJurnalId);
                jurnalEntity.setTanggalJurnal(isJurnalCreate ? tglJurnalCreate : tglNow);
                jurnalEntity.setMataUangId("032");
                jurnalEntity.setKurs(BigDecimal.valueOf(1));
                jurnalEntity.setKeterangan(catatanPembuatanJurnal);
                jurnalEntity.setSumber(sumber);
                jurnalEntity.setSumberDanaId(sumberDana);
                jurnalEntity.setPrintRegisterCount(BigDecimal.ZERO);
                jurnalEntity.setPrintCount(BigDecimal.ZERO);
                if (("Y").equalsIgnoreCase(flagRegister)){
                    jurnalEntity.setRegisteredFlag("Y");
                    jurnalEntity.setRegisteredUser(userLogin);
                    jurnalEntity.setRegisteredDate(new java.sql.Date(new Date().getTime()));
                }
                jurnalEntity.setBranchId(branchId);
                jurnalEntity.setFlag("Y");
                jurnalEntity.setCreatedWho(userLogin);
                jurnalEntity.setLastUpdateWho(userLogin);
                jurnalEntity.setCreatedDate(updateTime);
                jurnalEntity.setLastUpdate(updateTime);
                jurnalEntity.setAction("C");
                jurnalEntity.setTipePeriode(tipePeriode);
                // dirubah oleh sigit penambahan filter JKR 2020-09-15
                if (periodSudahTutup==null || "JKR".equalsIgnoreCase(jurnalEntity.getTipeJurnalId())){
                    try {
                        jurnalAkhirTahunDao.addAndSave(jurnalEntity);
                    } catch (HibernateException e) {
                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnal] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }
                }else if ("Y".equalsIgnoreCase(periodSudahTutup)) {
                    jurnalEntity.setTanggalJurnal(new java.sql.Date(bulanBerikutnya.getMillis()));
                    try {
                        jurnalAkhirTahunDao.addAndSave(jurnalEntity);
                    } catch (HibernateException e) {
                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnal] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }
                }

                returnJurnal.setNoJurnal(noJurnal);
                returnJurnal.setSumber(sumber);
            } catch (Exception e){
                logger.error("[PembayaranUtangPiutangBoImpl.createJurnal]"+e);
                throw new GeneralBOException("Found problem : "+e+", please info to your admin...");
            }
        }else{
            status="ERROR : tidak bisa menemukan Tipe Jurnal Id";
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnal]"+status);
            throw new GeneralBOException("Found problem when "+status+", please info to your admin...");
        }
        logger.info("[PembayaranUtangPiutangBoImpl.createJurnal] End process <<<");
        return returnJurnal;
    }

    //////////////////////////////////////// DETAIL BILLING PER TRANS //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String createJurnalDetail ( Map data , String noJurnal ,String tipeJurnalId,String transId,String periodSudahTutup ,String branchId ){
        //MEMBUAT JURNAL DETAIL
        String status;
        String metodeBayar=null;
        String bank=null;
        String masterId = null;
        String pasienId = null;
        String sumber = createInvoiceNumber(tipeJurnalId,branchId);
        String divisiId= null;
        String nomorRekeningPembayaran =null;

        List<ImMappingJurnalEntity> mappingJurnal ;
        try {
            mappingJurnal = mappingJurnalDao.getMappingByTipeJurnalIdNTransId(tipeJurnalId,transId);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (mappingJurnal.size()!=0){
            //Membuat jurnal berdasarkan mapping kemudian dimasukkan di List
            List<ItJurnalDetailEntity> jurnalDetailEntityList = new ArrayList<>();
            List<ItJurnalDetailActivityEntity> jurnalDetailActivityEntityList = new ArrayList<>();

            BigDecimal totalDebit= new BigDecimal(0);
            BigDecimal totalKredit= new BigDecimal(0);
            for (ImMappingJurnalEntity mapping : mappingJurnal){
                if (mapping.getKeterangan()!=null){
                    String jurnalDetailId=jurnalDetailDao.getNextJurnalDetailId();
                    String rekeningId = null;
                    String nomorRekening = null;

                    ///////////////////////DIGUNAKAN UNTUK PENGECEKAN DAN PENGAMBILAN BUKTI DAN NILAI UNTUK JURNAL BUKAN LIST  //////////////////////////////
                    if (data.get(mapping.getKeterangan())!=null){
                        String noNota = null;
                        BigDecimal nilai= null;
                        //JIKA YANG DIKIRIM BUKAN LIST
                        if (("N").equalsIgnoreCase(mapping.getKirimList())&&("N").equalsIgnoreCase(mapping.getKodeBarang())){
                            //untuk mendapatkan bukti dan nilai di masing masing parameter mapping
                            Map listOfMap = (Map) data.get(mapping.getKeterangan());
                            ///////////////////////DIGUNAKAN UNTUK PENGECEKAN KAS  //////////////////////////////
                            if (("kas").equalsIgnoreCase(mapping.getKeterangan())){
                                //untuk pembayaran
                                String rekeningIdKas = null;
                                if (listOfMap.get("metode_bayar")!=null){
                                    metodeBayar = (String) listOfMap.get("metode_bayar");
                                    // tunai / transfer
                                    if (("transfer").equalsIgnoreCase(metodeBayar)){
                                        if (listOfMap.get("bank")!=null){
                                            bank = (String) listOfMap.get("bank");
                                        }else{
                                            status="ERROR : Bank belum dipilih";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                        if (listOfMap.get("nomor_rekening")!=null){
                                            nomorRekeningPembayaran = (String) listOfMap.get("nomor_rekening");
                                        }
                                        try {
                                            rekeningIdKas = kodeRekeningDao.searchRekeningIdBankLikeName(bank);
                                        } catch (HibernateException e) {
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                        }
                                    }else if (("tunai").equalsIgnoreCase(metodeBayar)){
                                        try {
                                            rekeningIdKas = kodeRekeningDao.searchRekeningIdTunaiLikeName("KAS BESAR/TUNAI");
                                        } catch (HibernateException e) {
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                        }
                                    }
                                }

                                if (metodeBayar!=null){
                                    if (("tunai").equalsIgnoreCase(metodeBayar)){
                                        rekeningId=rekeningIdKas;
                                    }else if (("transfer").equalsIgnoreCase(metodeBayar)){
                                        rekeningId=rekeningIdKas;
                                        nomorRekening=nomorRekeningPembayaran;
                                    }else{
                                        status="ERROR : Metode pembayaran transfer tetapi belum memilih bank";
                                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                        throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                    }
                                }else {
                                    status="ERROR : Metode pembayaran kas tidak ada";
                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                }
                            }else{
                                Integer level = kodeRekeningDao.getLevelKodeRekening(mapping.getKodeRekening());
                                if (level==5){
                                    rekeningId=getRekeningForMappingOtomatis(mapping.getKodeRekening());
                                }else{
                                    if (listOfMap.get("rekening_id")!=null){
                                        rekeningId= String.valueOf(listOfMap.get("rekening_id"));
                                    }else{
                                        status="ERROR : Rekening ID tidak ditemukan pada trans ID"+mapping.getTransId();
                                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                        throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                    }
                                }
                            }

                            if (("Y").equalsIgnoreCase(mapping.getBukti())){
                                if (listOfMap.get("bukti")!=null){
                                    noNota = (String) listOfMap.get("bukti");
                                    //untuk mengambil no invoice sebagai kode sumber dari jurnal header
//                                    if (("uang_muka").equalsIgnoreCase(mapping.getKeterangan())){
//                                        sumber=noNota;
//                                    }
                                }
                                if (noNota==null){
                                    noNota = sumber;
//                                    status="ERROR : dibutuhkan bukti ( Invoice )";
//                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
//                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                }
                            }
                                /*else if (!("Y").equalsIgnoreCase(mapping.getKodeBarang())){
                                    if (("N").equalsIgnoreCase(mapping.getDivisiId())&&("N").equalsIgnoreCase(mapping.getMasterId())){
                                        try {
                                            nilai=(BigDecimal) data.get(mapping.getKeterangan());
                                        } catch (GeneralBOException e) {
                                            status="ERROR : ada masalah saat pengambilan biaya";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                        // cek biaya pembayaran apakah null
                                        if (nilai==null){
                                            status="ERROR : Biaya yang dikirim tidak ada";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                    }
                                }*/

                            ///////////////////////DIGUNAKAN UNTUK PENGECEKAN MASTER / PASIEN JIKA BUKAN LIST //////////////////////////////
                            if (("Y").equalsIgnoreCase(mapping.getMasterId())){
                                if (listOfMap.get("master_id")!=null) {
                                    masterId = (String) listOfMap.get("master_id");
                                }else if (listOfMap.get("pasien_id")!=null) {
                                    pasienId = (String) listOfMap.get("pasien_id");
                                }

                                if (masterId==null&&pasienId==null){
                                    status="ERROR : Membutuhkan master Id / Pasien Id";
                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                }
                            }else{
                                masterId=null;
                                pasienId=null;
                            }

                            // PENGECEKAN DIVISI
                            if (("Y").equalsIgnoreCase(mapping.getDivisiId())){
                                divisiId = (String) listOfMap.get("divisi_id");
                                if (divisiId==null){
                                    status="ERROR : Membutuhkan Divisi ID";
                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                }
                            }else{
                                divisiId=null;
                            }

                            //PENGECEKAN NILAI
                            if (listOfMap.get("nilai")!=null) {
                                nilai=(BigDecimal) listOfMap.get("nilai");
                            }else{
                                status="ERROR : Biaya yang dikirim tidak ada";
                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                            }
                            //mendapatkan nilai activity jika bukan list
                            if (listOfMap.get("activity")!=null){
                                try{
                                    List<Map> activityList = (List<Map>) listOfMap.get("activity");
                                    BigDecimal nilaiActivity = BigDecimal.ZERO;
                                    for (int x=0;x<activityList.size();x++){
                                        ItJurnalDetailActivityEntity saveActivity = new ItJurnalDetailActivityEntity();
                                        if (activityList.get(x).get("activity_id")!=null){
                                            saveActivity.setActivityId((String)activityList.get(x).get("activity_id"));
                                        }else{
                                            status="ERROR : Activity ID tidak ditemukan";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                        if (activityList.get(x).get("person_id")!=null){
                                            saveActivity.setPersonId((String)activityList.get(x).get("person_id"));
                                        }else{
                                            status="ERROR : Person ID tidak ditemukan";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                        if (activityList.get(x).get("nilai")!=null){
                                            saveActivity.setJumlah((BigDecimal)activityList.get(x).get("nilai"));
                                        }else{
                                            status="ERROR : nilai tidak ditemukan";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                        if (activityList.get(x).get("no_trans")!=null){
                                            saveActivity.setNoTrans((String)activityList.get(x).get("no_trans"));
                                        }else{
                                            status="ERROR : No. Trans tidak ditemukan";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                        if (activityList.get(x).get("tipe")!=null){
                                            saveActivity.setTipe((String)activityList.get(x).get("tipe"));
                                        }else{
                                            status="ERROR : Tipe tidak ditemukan";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                        saveActivity.setJurnalDetailActivityId(jurnalDetailActivityDao.getNextJurnalActivityId());
                                        saveActivity.setJurnalDetailId(jurnalDetailId);
                                        saveActivity.setFlag("Y");
                                        saveActivity.setAction("C");
                                        saveActivity.setCreatedDate(updateTime);
                                        saveActivity.setLastUpdate(updateTime);
                                        saveActivity.setCreatedWho(userLogin);
                                        saveActivity.setLastUpdateWho(userLogin);

                                        nilaiActivity = nilaiActivity.add(saveActivity.getJumlah());
                                        jurnalDetailActivityEntityList.add(saveActivity);
                                    }
                                    if (nilaiActivity.compareTo(nilai)!=0){
                                        status="ERROR : Total Nilai Activity dan nilai yang dikirim tidak sesuai";
                                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                        throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                    }
                                }catch (Exception e){
                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+e.getMessage());
                                    throw new GeneralBOException("Found problem "+e.getMessage()+", please info to your admin...");
                                }
                            }
                        }

                        ///////////////////////MEMASUKKAN KE ENTITY  //////////////////////////////
                        ///////// JIKA JURNAL DETAIL LIST ( jurnal detail dengan data yang dikirim berupa list )
                        if (("Y").equalsIgnoreCase(mapping.getKirimList())){
                            if (data.get(mapping.getKeterangan())!=null){

                                List<Map> mapList = (List<Map>) data.get(mapping.getKeterangan());
                                for (int i=0;i<mapList.size();i++){
                                    String buktiLoop = null;
                                    String masterIdLoop = null;
                                    String pasienIdLoop = null;
                                    String divisiIdLoop = null;
                                    BigDecimal nilaiLoop=null;
                                    //////////////////VALIDASI////////////////////
                                    if (("Y").equalsIgnoreCase(mapping.getBukti())){
                                        if (mapList.get(i).get("bukti")!=null){
                                            buktiLoop=(String)mapList.get(i).get("bukti");
                                        }else{
                                            buktiLoop=sumber;
//                                            status="ERROR : ada bukti belum di kirim";
//                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
//                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                    }
                                    if (("Y").equalsIgnoreCase(mapping.getDivisiId())){

                                        if (mapList.get(i).get("divisi_id")!=null){
                                            divisiIdLoop=(String)mapList.get(i).get("divisi_id");
                                        }else{
                                            status="ERROR : ada divisi belum di kirim";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                    }
                                    if (("Y").equalsIgnoreCase(mapping.getMasterId())){
                                        if (mapList.get(i).get("master_id")!=null){
                                            masterIdLoop=(String)mapList.get(i).get("master_id");
                                        }else if (mapList.get(i).get("pasien_id")!=null){
                                            pasienIdLoop=(String)mapList.get(i).get("pasien_id");
                                        }else{
                                            status="ERROR : master / pasien Id belum dikirim";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                    }
                                    if (mapList.get(i).get("nilai")!=null){
                                        nilaiLoop=(BigDecimal) mapList.get(i).get("nilai");
                                    }else{
                                        status="ERROR : ada nilai yang masih kosong";
                                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                        throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                    }

                                    //mencari rekening Id
                                    Integer level = kodeRekeningDao.getLevelKodeRekening(mapping.getKodeRekening());
                                    if (level==5){
                                        rekeningId=getRekeningForMappingOtomatis(mapping.getKodeRekening());
                                    }else{
                                        if (mapList.get(i).get("rekening_id")!=null){
                                            rekeningId= String.valueOf(mapList.get(i).get("rekening_id"));
                                        }else{
                                            status="ERROR : Rekening ID tidak ditemukan pada trans ID"+mapping.getTransId();
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                    }

                                    ///////////////////////MEMASUKKAN KE ENTITY  //////////////////////////////
                                    String jurnalDetailIdLoop="";
                                    ItJurnalDetailEntity jurnalDetailEntity = new ItJurnalDetailEntity();
                                    try {
                                        jurnalDetailIdLoop=jurnalDetailDao.getNextJurnalDetailId();
                                    } catch (HibernateException e) {
                                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                    }
                                    jurnalDetailEntity.setJurnalDetailId(jurnalDetailIdLoop);
                                    jurnalDetailEntity.setRekeningId(rekeningId);
                                    jurnalDetailEntity.setBiaya(null);
                                    jurnalDetailEntity.setMasterId(masterIdLoop);
                                    jurnalDetailEntity.setPasienId(pasienIdLoop);
                                    jurnalDetailEntity.setDivisiId(divisiIdLoop);
                                    jurnalDetailEntity.setKdBarang(null);
                                    jurnalDetailEntity.setNoNota(buktiLoop);
                                    jurnalDetailEntity.setNoJurnal(noJurnal);


                                    if (("D").equalsIgnoreCase(mapping.getPosisi())){
                                        jurnalDetailEntity.setJumlahDebit(nilaiLoop);
                                        jurnalDetailEntity.setJumlahKredit(BigDecimal.ZERO);
                                    }else if (("K").equalsIgnoreCase(mapping.getPosisi())){
                                        jurnalDetailEntity.setJumlahKredit(nilaiLoop);
                                        jurnalDetailEntity.setJumlahDebit(BigDecimal.ZERO);
                                    }

                                    jurnalDetailEntity.setFlag("Y");
                                    jurnalDetailEntity.setAction("C");
                                    jurnalDetailEntity.setCreatedDate(updateTime);
                                    jurnalDetailEntity.setLastUpdate(updateTime);
                                    jurnalDetailEntity.setCreatedWho(userLogin);
                                    jurnalDetailEntity.setLastUpdateWho(userLogin);

                                    // Total dari pembayaran debet dan kredit untuk nanti disamakan
                                    totalDebit = totalDebit.add(jurnalDetailEntity.getJumlahDebit());
                                    totalKredit = totalKredit.add(jurnalDetailEntity.getJumlahKredit());
                                    jurnalDetailEntityList.add(jurnalDetailEntity);

                                    if (mapList.get(i).get("activity")!=null){
                                        try{
                                            List<Map> activityList = (List<Map>) mapList.get(i).get("activity");
                                            BigDecimal nilaiActivity = BigDecimal.ZERO;
                                            // DIRUBAH SIGIT, 2020-05-08 Perubahan INDEX : sebelumnya i menjadi x; START
                                            for (int x=0;x<activityList.size();x++){
                                                ItJurnalDetailActivityEntity saveActivity = new ItJurnalDetailActivityEntity();
                                                if (activityList.get(x).get("activity_id")!=null){
                                                    saveActivity.setActivityId((String)activityList.get(x).get("activity_id"));
                                                }else{
                                                    status="ERROR : Activity ID tidak ditemukan";
                                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                                }
                                                if (activityList.get(x).get("person_id")!=null){
                                                    saveActivity.setPersonId((String)activityList.get(x).get("person_id"));
                                                }else{
                                                    status="ERROR : Person ID tidak ditemukan";
                                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                                }
                                                if (activityList.get(x).get("nilai")!=null){
                                                    saveActivity.setJumlah((BigDecimal)activityList.get(x).get("nilai"));
                                                }else{
                                                    status="ERROR : nilai tidak ditemukan";
                                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                                }
                                                if (activityList.get(x).get("no_trans")!=null){
                                                    saveActivity.setNoTrans((String)activityList.get(x).get("no_trans"));
                                                }else{
                                                    status="ERROR : No. Trans tidak ditemukan";
                                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                                }
                                                if (activityList.get(x).get("tipe")!=null){
                                                    saveActivity.setTipe((String)activityList.get(x).get("tipe"));
                                                }else{
                                                    status="ERROR : Tipe tidak ditemukan";
                                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                                }

                                                // DIRUBAH SIGIT, 2020-05-08 Perubahan INDEX : sebelumnya i menjadi x; END

                                                saveActivity.setJurnalDetailActivityId(jurnalDetailActivityDao.getNextJurnalActivityId());
                                                saveActivity.setJurnalDetailId(jurnalDetailIdLoop);
                                                saveActivity.setFlag("Y");
                                                saveActivity.setAction("C");
                                                saveActivity.setCreatedDate(updateTime);
                                                saveActivity.setLastUpdate(updateTime);
                                                saveActivity.setCreatedWho(userLogin);
                                                saveActivity.setLastUpdateWho(userLogin);

                                                nilaiActivity = nilaiActivity.add(saveActivity.getJumlah());
                                                jurnalDetailActivityEntityList.add(saveActivity);
                                            }
                                            if (nilaiActivity.compareTo(nilaiLoop)!=0){
                                                status="ERROR : Total Nilai Activity dan nilai yang dikirim tidak sesuai";
                                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                                throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                            }
                                        }catch (Exception e){
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+e.getMessage());
                                            throw new GeneralBOException("Found problem "+e.getMessage()+", please info to your admin...");
                                        }
                                    }
                                }
                            }else{
                                status="ERROR : Tidak bisa mendapat map list";
                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                            }
                        }else if (("Y").equalsIgnoreCase(mapping.getKodeBarang())){
                            ///////// JIKA JURNAL BARANG ( jurnal detail yang di looping )
                            if (data.get(mapping.getKeterangan())!=null){

                                List<Map> mapList = (List<Map>) data.get(mapping.getKeterangan());
                                for (int i=0;i<mapList.size();i++){
                                    String noKdBarang = null;
                                    BigDecimal biayaPembayaranBarang=null;
                                    String divisi = null;

                                    //////////////////VALIDASI////////////////////
                                    if (mapList.get(i).get("kd_barang")!=null){
                                        noKdBarang=(String)mapList.get(i).get("kd_barang");
                                    }

                                    if (mapList.get(i).get("nilai")!=null){
                                        biayaPembayaranBarang=(BigDecimal)mapList.get(i).get("nilai") ;
                                    }else{
                                        status="ERROR : Biaya dari data yang dikirim tidak ada";
                                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                        throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                    }
                                    if (("Y").equalsIgnoreCase(mapping.getMasterId())){
                                        if (masterId==null){
                                            status="Kode Vendor masih kosong";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                    }else{
                                        masterId=null;
                                    }
                                    if (("Y").equalsIgnoreCase(mapping.getDivisiId())){
                                        if (mapList.get(i).get("divisi_id")!=null){
                                            divisiId=(String)mapList.get(i).get("divisi_id");
                                        }else{
                                            status="ERROR : ada divisi belum di kirim";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                    }

                                    //mencari rekening ID
                                    Integer level = kodeRekeningDao.getLevelKodeRekening(mapping.getKodeRekening());
                                    if (level==5){
                                        rekeningId=getRekeningForMappingOtomatis(mapping.getKodeRekening());
                                    }else{
                                        if (mapList.get(i).get("rekening_id")!=null){
                                            rekeningId= String.valueOf(mapList.get(i).get("rekening_id"));
                                        }else{
                                            status="ERROR : Rekening ID tidak ditemukan pada trans ID"+mapping.getTransId();
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                    }

                                    ///////////////////////MEMASUKKAN KE ENTITY  //////////////////////////////
                                    String jurnalDetailBarangId="";
                                    ItJurnalDetailEntity jurnalBarang = new ItJurnalDetailEntity();
                                    try {
                                        jurnalDetailBarangId=jurnalDetailDao.getNextJurnalDetailId();
                                    } catch (HibernateException e) {
                                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                    }
                                    jurnalBarang.setRekeningId(rekeningId);
                                    jurnalBarang.setBiaya(null);
                                    jurnalBarang.setMasterId(masterId);
                                    jurnalBarang.setPasienId(pasienId);
                                    jurnalBarang.setKdBarang(noKdBarang);
                                    jurnalBarang.setDivisiId(divisi);
                                    jurnalBarang.setNoNota(null);
                                    jurnalBarang.setJurnalDetailId(jurnalDetailBarangId);
                                    jurnalBarang.setNoJurnal(noJurnal);


                                    if (("D").equalsIgnoreCase(mapping.getPosisi())){
                                        jurnalBarang.setJumlahDebit(biayaPembayaranBarang);
                                        jurnalBarang.setJumlahKredit(BigDecimal.ZERO);
                                    }else if (("K").equalsIgnoreCase(mapping.getPosisi())){
                                        jurnalBarang.setJumlahKredit(biayaPembayaranBarang);
                                        jurnalBarang.setJumlahDebit(BigDecimal.ZERO);
                                    }

                                    jurnalBarang.setFlag("Y");
                                    jurnalBarang.setAction("C");
                                    jurnalBarang.setCreatedDate(updateTime);
                                    jurnalBarang.setLastUpdate(updateTime);
                                    jurnalBarang.setCreatedWho(userLogin);
                                    jurnalBarang.setLastUpdateWho(userLogin);

                                    // Total dari pembayaran debet dan kredit untuk nanti disamakan
                                    totalDebit = totalDebit.add(jurnalBarang.getJumlahDebit());
                                    totalKredit = totalKredit.add(jurnalBarang.getJumlahKredit());
                                    jurnalDetailEntityList.add(jurnalBarang);
                                }

                            }else{
                                status="ERROR : Tidak bisa mendapat map list";
                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                            }
                        }else {
                            ///////// JIKA JURNAL BIASA ( bukan jurnal detail looping )
                            ItJurnalDetailEntity jurnalDetailEntity = new ItJurnalDetailEntity();
                            jurnalDetailEntity.setNoJurnal(noJurnal);
                            jurnalDetailEntity.setRekeningId(rekeningId);
                            jurnalDetailEntity.setNomorRekening(nomorRekening);
                            jurnalDetailEntity.setMasterId(masterId);
                            jurnalDetailEntity.setPasienId(pasienId);
                            jurnalDetailEntity.setNoNota(noNota);
                            jurnalDetailEntity.setDivisiId(divisiId);
                            jurnalDetailEntity.setBiaya(null);

                            jurnalDetailEntity.setFlag("Y");
                            jurnalDetailEntity.setAction("C");
                            jurnalDetailEntity.setCreatedDate(updateTime);
                            jurnalDetailEntity.setLastUpdate(updateTime);
                            jurnalDetailEntity.setCreatedWho(userLogin);
                            jurnalDetailEntity.setLastUpdateWho(userLogin);

                            jurnalDetailEntity.setJurnalDetailId(jurnalDetailId);
                            if (("D").equalsIgnoreCase(mapping.getPosisi())){
                                jurnalDetailEntity.setJumlahDebit(nilai);
                                jurnalDetailEntity.setJumlahKredit(BigDecimal.ZERO);
                            }else if (("K").equalsIgnoreCase(mapping.getPosisi())){
                                jurnalDetailEntity.setJumlahKredit(nilai);
                                jurnalDetailEntity.setJumlahDebit(BigDecimal.ZERO);
                            }

                            try {
                                List<ItJurnalDetailEntity> listOfDuplicate = jurnalDetailDao.getListJurnalDetailDuplicate(jurnalDetailEntity.getRekeningId(),jurnalDetailEntity.getNoNota(),jurnalDetailEntity.getMasterId(),jurnalDetailEntity.getJumlahDebit(),jurnalDetailEntity.getJumlahKredit());
                                if (listOfDuplicate.size()!=0){
                                    status="ERROR : Ada duplikasi data pada data yang dikirim";
                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                }
                            } catch (HibernateException e) {
                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }

                            // Total dari pembayaran debet dan kredit untuk nanti disamakan
                            totalDebit = totalDebit.add(jurnalDetailEntity.getJumlahDebit());
                            totalKredit = totalKredit.add(jurnalDetailEntity.getJumlahKredit());


                            jurnalDetailEntityList.add(jurnalDetailEntity);
                        }
                    }else{
                        status="ERROR : tidak bisa menemukan biaya pada map yang dikirim";
                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                        throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                    }
                }else{
                    status="ERROR : Keterangan pada mapping masih kosong";
                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                }
            }
            //Pengecekan apakah antara debet dan kredit sudah balance
            totalDebit = totalDebit.setScale(2, BigDecimal.ROUND_HALF_UP);
            totalKredit = totalKredit.setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal balance = totalDebit.subtract(totalKredit);
            if (balance.compareTo(new BigDecimal(0)) == 0){
                // penambahan filter JKR, Sigit 2020-09-15
                if (periodSudahTutup==null || ("Y").equalsIgnoreCase(periodSudahTutup) || "JKR".equalsIgnoreCase(tipeJurnalId)){
                    for (ItJurnalDetailEntity jurnalDetailEntity : jurnalDetailEntityList){
                        /////////////////////// Save data ///////////////////////
                        if (jurnalDetailEntity.getJumlahDebit().compareTo(BigDecimal.ZERO)==0&&jurnalDetailEntity.getJumlahKredit().compareTo(BigDecimal.ZERO)==0){

                        }else{
                            try {
                                jurnalDetailDao.addAndSave(jurnalDetailEntity);
                            } catch (HibernateException e) {
                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }
                        }
                    }
                    for (ItJurnalDetailActivityEntity jurnalDetailActivityEntity : jurnalDetailActivityEntityList){
                        if (jurnalDetailActivityEntity.getJumlah().compareTo(BigDecimal.ZERO)>0){
                            try {
                                jurnalDetailActivityDao.addAndSave(jurnalDetailActivityEntity);
                            } catch (HibernateException e) {
                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }
                        }
                    }
                }else{
                    for (ItJurnalDetailEntity jurnalDetailEntity : jurnalDetailEntityList){
                        /////////////////////// Save data Pending ///////////////////////
                        ItJurnalDetailPendingEntity detailPending = new ItJurnalDetailPendingEntity();
                        detailPending.setNoJurnal(jurnalDetailEntity.getNoJurnal());
                        detailPending.setRekeningId(jurnalDetailEntity.getRekeningId());
                        detailPending.setMasterId(jurnalDetailEntity.getMasterId());
                        detailPending.setNoNota(jurnalDetailEntity.getNoNota());
                        detailPending.setJumlahDebit(jurnalDetailEntity.getJumlahDebit());
                        detailPending.setJumlahKredit(jurnalDetailEntity.getJumlahKredit());
                        detailPending.setBiaya(jurnalDetailEntity.getBiaya());
                        detailPending.setFlag(jurnalDetailEntity.getFlag());
                        detailPending.setAction(jurnalDetailEntity.getAction());
                        detailPending.setCreatedDate(jurnalDetailEntity.getCreatedDate());
                        detailPending.setLastUpdate(jurnalDetailEntity.getLastUpdate());
                        detailPending.setCreatedWho(jurnalDetailEntity.getCreatedWho());
                        detailPending.setLastUpdateWho(jurnalDetailEntity.getLastUpdateWho());
                        detailPending.setJurnalDetailId(jurnalDetailEntity.getJurnalDetailId());
                        detailPending.setKdBarang(jurnalDetailEntity.getKdBarang());
                        detailPending.setPasienId(jurnalDetailEntity.getPasienId());
                        detailPending.setNomorRekening(jurnalDetailEntity.getNomorRekening());
                        detailPending.setDivisiId(jurnalDetailEntity.getDivisiId());
                        if (detailPending.getJumlahDebit().compareTo(BigDecimal.ZERO)==0&&detailPending.getJumlahKredit().compareTo(BigDecimal.ZERO)==0){

                        }else{
                            try {
                                jurnalDetailDao.addAndSavePending(detailPending);
                            } catch (HibernateException e) {
                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }
                        }
                    }
                    for (ItJurnalDetailActivityEntity jurnalDetailActivityEntity : jurnalDetailActivityEntityList){
                        ItJurnalDetailActivityPendingEntity activityPending = new ItJurnalDetailActivityPendingEntity();
                        activityPending.setJurnalDetailActivityId(jurnalDetailActivityEntity.getJurnalDetailActivityId());
                        activityPending.setJurnalDetailId(jurnalDetailActivityEntity.getJurnalDetailId());
                        activityPending.setActivityId(jurnalDetailActivityEntity.getActivityId());
                        activityPending.setJumlah(jurnalDetailActivityEntity.getJumlah());
                        activityPending.setPersonId(jurnalDetailActivityEntity.getPersonId());
                        activityPending.setFlag(jurnalDetailActivityEntity.getFlag());
                        activityPending.setTipe(jurnalDetailActivityEntity.getTipe());
                        activityPending.setNoTrans(jurnalDetailActivityEntity.getNoTrans());
                        activityPending.setAction(jurnalDetailActivityEntity.getAction());
                        activityPending.setCreatedDate(jurnalDetailActivityEntity.getCreatedDate());
                        activityPending.setLastUpdate(jurnalDetailActivityEntity.getLastUpdate());
                        activityPending.setCreatedWho(jurnalDetailActivityEntity.getCreatedWho());
                        activityPending.setLastUpdateWho(jurnalDetailActivityEntity.getLastUpdateWho());
                        if (activityPending.getJumlah().compareTo(BigDecimal.ZERO)>0){
                            try {
                                jurnalDetailActivityDao.addAndSavePending(activityPending);
                            } catch (HibernateException e) {
                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }
                        }

                    }
                }

            }else{
                status="ERROR : antara debet dan kredit tidak balance";
                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                throw new GeneralBOException("Found problem "+status+", please info to your admin...");
            }
        }else{
            status="ERROR : tidak bisa menemukan mapping";
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
        }
        return sumber;
    }

    private String createJurnalDetailAkhirTahun ( Map data , String noJurnal ,String tipeJurnalId,String transId,String periodSudahTutup ,String branchId ){
        //MEMBUAT JURNAL DETAIL
        String status;
        String metodeBayar=null;
        String bank=null;
        String masterId = null;
        String pasienId = null;
        String sumber = createInvoiceNumber(tipeJurnalId,branchId);
        String divisiId= null;
        String nomorRekeningPembayaran =null;

        List<ImMappingJurnalEntity> mappingJurnal ;
        try {
            mappingJurnal = mappingJurnalDao.getMappingByTipeJurnalIdNTransId(tipeJurnalId,transId);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (mappingJurnal.size()!=0){
            //Membuat jurnal berdasarkan mapping kemudian dimasukkan di List
            List<ItAkunJurnalDetailAkhirTahunEntity> jurnalDetailEntityList = new ArrayList<>();
            List<ItJurnalDetailActivityEntity> jurnalDetailActivityEntityList = new ArrayList<>();

            BigDecimal totalDebit= new BigDecimal(0);
            BigDecimal totalKredit= new BigDecimal(0);
            for (ImMappingJurnalEntity mapping : mappingJurnal){
                if (mapping.getKeterangan()!=null){
                    String jurnalDetailId=jurnalDetailAkhirTahunDao.getNextJurnalDetailId();
                    String rekeningId = null;
                    String nomorRekening = null;

                    ///////////////////////DIGUNAKAN UNTUK PENGECEKAN DAN PENGAMBILAN BUKTI DAN NILAI UNTUK JURNAL BUKAN LIST  //////////////////////////////
                    if (data.get(mapping.getKeterangan())!=null){
                        String noNota = null;
                        BigDecimal nilai= null;
                        //JIKA YANG DIKIRIM BUKAN LIST
                        if (("N").equalsIgnoreCase(mapping.getKirimList())&&("N").equalsIgnoreCase(mapping.getKodeBarang())){
                            //untuk mendapatkan bukti dan nilai di masing masing parameter mapping
                            Map listOfMap = (Map) data.get(mapping.getKeterangan());
                            ///////////////////////DIGUNAKAN UNTUK PENGECEKAN KAS  //////////////////////////////
                            if (("kas").equalsIgnoreCase(mapping.getKeterangan())){
                                //untuk pembayaran
                                String rekeningIdKas = null;
                                if (listOfMap.get("metode_bayar")!=null){
                                    metodeBayar = (String) listOfMap.get("metode_bayar");
                                    // tunai / transfer
                                    if (("transfer").equalsIgnoreCase(metodeBayar)){
                                        if (listOfMap.get("bank")!=null){
                                            bank = (String) listOfMap.get("bank");
                                        }else{
                                            status="ERROR : Bank belum dipilih";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                        if (listOfMap.get("nomor_rekening")!=null){
                                            nomorRekeningPembayaran = (String) listOfMap.get("nomor_rekening");
                                        }
                                        try {
                                            rekeningIdKas = kodeRekeningDao.searchRekeningIdBankLikeName(bank);
                                        } catch (HibernateException e) {
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                        }
                                    }else if (("tunai").equalsIgnoreCase(metodeBayar)){
                                        try {
                                            rekeningIdKas = kodeRekeningDao.searchRekeningIdTunaiLikeName("KAS BESAR/TUNAI");
                                        } catch (HibernateException e) {
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                        }
                                    }
                                }

                                if (metodeBayar!=null){
                                    if (("tunai").equalsIgnoreCase(metodeBayar)){
                                        rekeningId=rekeningIdKas;
                                    }else if (("transfer").equalsIgnoreCase(metodeBayar)){
                                        rekeningId=rekeningIdKas;
                                        nomorRekening=nomorRekeningPembayaran;
                                    }else{
                                        status="ERROR : Metode pembayaran transfer tetapi belum memilih bank";
                                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                        throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                    }
                                }else {
                                    status="ERROR : Metode pembayaran kas tidak ada";
                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                }
                            }else{
                                Integer level = kodeRekeningDao.getLevelKodeRekening(mapping.getKodeRekening());
                                if (level==5){
                                    rekeningId=getRekeningForMappingOtomatis(mapping.getKodeRekening());
                                }else{
                                    if (listOfMap.get("rekening_id")!=null){
                                        rekeningId= String.valueOf(listOfMap.get("rekening_id"));
                                    }else{
                                        status="ERROR : Rekening ID tidak ditemukan pada trans ID"+mapping.getTransId();
                                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                        throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                    }
                                }
                            }

                            if (("Y").equalsIgnoreCase(mapping.getBukti())){
                                if (listOfMap.get("bukti")!=null){
                                    noNota = (String) listOfMap.get("bukti");
                                    //untuk mengambil no invoice sebagai kode sumber dari jurnal header
//                                    if (("uang_muka").equalsIgnoreCase(mapping.getKeterangan())){
//                                        sumber=noNota;
//                                    }
                                }
                                if (noNota==null){
                                    noNota = sumber;
//                                    status="ERROR : dibutuhkan bukti ( Invoice )";
//                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
//                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                }
                            }
                                /*else if (!("Y").equalsIgnoreCase(mapping.getKodeBarang())){
                                    if (("N").equalsIgnoreCase(mapping.getDivisiId())&&("N").equalsIgnoreCase(mapping.getMasterId())){
                                        try {
                                            nilai=(BigDecimal) data.get(mapping.getKeterangan());
                                        } catch (GeneralBOException e) {
                                            status="ERROR : ada masalah saat pengambilan biaya";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                        // cek biaya pembayaran apakah null
                                        if (nilai==null){
                                            status="ERROR : Biaya yang dikirim tidak ada";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                    }
                                }*/

                            ///////////////////////DIGUNAKAN UNTUK PENGECEKAN MASTER / PASIEN JIKA BUKAN LIST //////////////////////////////
                            if (("Y").equalsIgnoreCase(mapping.getMasterId())){
                                if (listOfMap.get("master_id")!=null) {
                                    masterId = (String) listOfMap.get("master_id");
                                }else if (listOfMap.get("pasien_id")!=null) {
                                    pasienId = (String) listOfMap.get("pasien_id");
                                }

                                if (masterId==null&&pasienId==null){
                                    status="ERROR : Membutuhkan master Id / Pasien Id";
                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                }
                            }else{
                                masterId=null;
                                pasienId=null;
                            }

                            // PENGECEKAN DIVISI
                            if (("Y").equalsIgnoreCase(mapping.getDivisiId())){
                                divisiId = (String) listOfMap.get("divisi_id");
                                if (divisiId==null){
                                    status="ERROR : Membutuhkan Divisi ID";
                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                }
                            }else{
                                divisiId=null;
                            }

                            //PENGECEKAN NILAI
                            if (listOfMap.get("nilai")!=null) {
                                nilai=(BigDecimal) listOfMap.get("nilai");
                            }else{
                                status="ERROR : Biaya yang dikirim tidak ada";
                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                            }
                            //mendapatkan nilai activity jika bukan list
                            if (listOfMap.get("activity")!=null){
                                try{
                                    List<Map> activityList = (List<Map>) listOfMap.get("activity");
                                    BigDecimal nilaiActivity = BigDecimal.ZERO;
                                    for (int x=0;x<activityList.size();x++){
                                        ItJurnalDetailActivityEntity saveActivity = new ItJurnalDetailActivityEntity();
                                        if (activityList.get(x).get("activity_id")!=null){
                                            saveActivity.setActivityId((String)activityList.get(x).get("activity_id"));
                                        }else{
                                            status="ERROR : Activity ID tidak ditemukan";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                        if (activityList.get(x).get("person_id")!=null){
                                            saveActivity.setPersonId((String)activityList.get(x).get("person_id"));
                                        }else{
                                            status="ERROR : Person ID tidak ditemukan";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                        if (activityList.get(x).get("nilai")!=null){
                                            saveActivity.setJumlah((BigDecimal)activityList.get(x).get("nilai"));
                                        }else{
                                            status="ERROR : nilai tidak ditemukan";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                        if (activityList.get(x).get("no_trans")!=null){
                                            saveActivity.setNoTrans((String)activityList.get(x).get("no_trans"));
                                        }else{
                                            status="ERROR : No. Trans tidak ditemukan";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                        if (activityList.get(x).get("tipe")!=null){
                                            saveActivity.setTipe((String)activityList.get(x).get("tipe"));
                                        }else{
                                            status="ERROR : Tipe tidak ditemukan";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                        saveActivity.setJurnalDetailActivityId(jurnalDetailActivityDao.getNextJurnalActivityId());
                                        saveActivity.setJurnalDetailId(jurnalDetailId);
                                        saveActivity.setFlag("Y");
                                        saveActivity.setAction("C");
                                        saveActivity.setCreatedDate(updateTime);
                                        saveActivity.setLastUpdate(updateTime);
                                        saveActivity.setCreatedWho(userLogin);
                                        saveActivity.setLastUpdateWho(userLogin);

                                        nilaiActivity = nilaiActivity.add(saveActivity.getJumlah());
                                        jurnalDetailActivityEntityList.add(saveActivity);
                                    }
                                    if (nilaiActivity.compareTo(nilai)!=0){
                                        status="ERROR : Total Nilai Activity dan nilai yang dikirim tidak sesuai";
                                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                        throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                    }
                                }catch (Exception e){
                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+e.getMessage());
                                    throw new GeneralBOException("Found problem "+e.getMessage()+", please info to your admin...");
                                }
                            }
                        }

                        ///////////////////////MEMASUKKAN KE ENTITY  //////////////////////////////
                        ///////// JIKA JURNAL DETAIL LIST ( jurnal detail dengan data yang dikirim berupa list )
                        if (("Y").equalsIgnoreCase(mapping.getKirimList())){
                            if (data.get(mapping.getKeterangan())!=null){

                                List<Map> mapList = (List<Map>) data.get(mapping.getKeterangan());
                                for (int i=0;i<mapList.size();i++){
                                    String buktiLoop = null;
                                    String masterIdLoop = null;
                                    String pasienIdLoop = null;
                                    String divisiIdLoop = null;
                                    BigDecimal nilaiLoop=null;
                                    //////////////////VALIDASI////////////////////
                                    if (("Y").equalsIgnoreCase(mapping.getBukti())){
                                        if (mapList.get(i).get("bukti")!=null){
                                            buktiLoop=(String)mapList.get(i).get("bukti");
                                        }else{
                                            buktiLoop=sumber;
//                                            status="ERROR : ada bukti belum di kirim";
//                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
//                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                    }
                                    if (("Y").equalsIgnoreCase(mapping.getDivisiId())){

                                        if (mapList.get(i).get("divisi_id")!=null){
                                            divisiIdLoop=(String)mapList.get(i).get("divisi_id");
                                        }else{
                                            status="ERROR : ada divisi belum di kirim";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                    }
                                    if (("Y").equalsIgnoreCase(mapping.getMasterId())){
                                        if (mapList.get(i).get("master_id")!=null){
                                            masterIdLoop=(String)mapList.get(i).get("master_id");
                                        }else if (mapList.get(i).get("pasien_id")!=null){
                                            pasienIdLoop=(String)mapList.get(i).get("pasien_id");
                                        }else{
                                            status="ERROR : master / pasien Id belum dikirim";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                    }
                                    if (mapList.get(i).get("nilai")!=null){
                                        nilaiLoop=(BigDecimal) mapList.get(i).get("nilai");
                                    }else{
                                        status="ERROR : ada nilai yang masih kosong";
                                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                        throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                    }

                                    //mencari rekening Id
                                    Integer level = kodeRekeningDao.getLevelKodeRekening(mapping.getKodeRekening());
                                    if (level==5){
                                        rekeningId=getRekeningForMappingOtomatis(mapping.getKodeRekening());
                                    }else{
                                        if (mapList.get(i).get("rekening_id")!=null){
                                            rekeningId= String.valueOf(mapList.get(i).get("rekening_id"));
                                        }else{
                                            status="ERROR : Rekening ID tidak ditemukan pada trans ID"+mapping.getTransId();
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                        }
                                    }

                                    ///////////////////////MEMASUKKAN KE ENTITY  //////////////////////////////
                                    String jurnalDetailIdLoop="";
                                    ItAkunJurnalDetailAkhirTahunEntity jurnalDetailEntity = new ItAkunJurnalDetailAkhirTahunEntity();
                                    try {
                                        jurnalDetailIdLoop=jurnalDetailDao.getNextJurnalDetailId();
                                    } catch (HibernateException e) {
                                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                    }
                                    jurnalDetailEntity.setJurnalDetailId(jurnalDetailIdLoop);
                                    jurnalDetailEntity.setRekeningId(rekeningId);
                                    jurnalDetailEntity.setBiaya(null);
                                    jurnalDetailEntity.setMasterId(masterIdLoop);
                                    jurnalDetailEntity.setPasienId(pasienIdLoop);
                                    jurnalDetailEntity.setDivisiId(divisiIdLoop);
                                    jurnalDetailEntity.setKdBarang(null);
                                    jurnalDetailEntity.setNoNota(buktiLoop);
                                    jurnalDetailEntity.setNoJurnal(noJurnal);


                                    if (("D").equalsIgnoreCase(mapping.getPosisi())){
                                        jurnalDetailEntity.setJumlahDebit(nilaiLoop);
                                        jurnalDetailEntity.setJumlahKredit(BigDecimal.ZERO);
                                    }else if (("K").equalsIgnoreCase(mapping.getPosisi())){
                                        jurnalDetailEntity.setJumlahKredit(nilaiLoop);
                                        jurnalDetailEntity.setJumlahDebit(BigDecimal.ZERO);
                                    }

                                    jurnalDetailEntity.setFlag("Y");
                                    jurnalDetailEntity.setAction("C");
                                    jurnalDetailEntity.setCreatedDate(updateTime);
                                    jurnalDetailEntity.setLastUpdate(updateTime);
                                    jurnalDetailEntity.setCreatedWho(userLogin);
                                    jurnalDetailEntity.setLastUpdateWho(userLogin);

                                    // Total dari pembayaran debet dan kredit untuk nanti disamakan
                                    totalDebit = totalDebit.add(jurnalDetailEntity.getJumlahDebit());
                                    totalKredit = totalKredit.add(jurnalDetailEntity.getJumlahKredit());
                                    jurnalDetailEntityList.add(jurnalDetailEntity);

                                    if (mapList.get(i).get("activity")!=null){
                                        try{
                                            List<Map> activityList = (List<Map>) mapList.get(i).get("activity");
                                            BigDecimal nilaiActivity = BigDecimal.ZERO;
                                            // DIRUBAH SIGIT, 2020-05-08 Perubahan INDEX : sebelumnya i menjadi x; START
                                            for (int x=0;x<activityList.size();x++){
                                                ItJurnalDetailActivityEntity saveActivity = new ItJurnalDetailActivityEntity();
                                                if (activityList.get(x).get("activity_id")!=null){
                                                    saveActivity.setActivityId((String)activityList.get(x).get("activity_id"));
                                                }else{
                                                    status="ERROR : Activity ID tidak ditemukan";
                                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                                }
                                                if (activityList.get(x).get("person_id")!=null){
                                                    saveActivity.setPersonId((String)activityList.get(x).get("person_id"));
                                                }else{
                                                    status="ERROR : Person ID tidak ditemukan";
                                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                                }
                                                if (activityList.get(x).get("nilai")!=null){
                                                    saveActivity.setJumlah((BigDecimal)activityList.get(x).get("nilai"));
                                                }else{
                                                    status="ERROR : nilai tidak ditemukan";
                                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                                }
                                                if (activityList.get(x).get("no_trans")!=null){
                                                    saveActivity.setNoTrans((String)activityList.get(x).get("no_trans"));
                                                }else{
                                                    status="ERROR : No. Trans tidak ditemukan";
                                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                                }
                                                if (activityList.get(x).get("tipe")!=null){
                                                    saveActivity.setTipe((String)activityList.get(x).get("tipe"));
                                                }else{
                                                    status="ERROR : Tipe tidak ditemukan";
                                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                                }

                                                // DIRUBAH SIGIT, 2020-05-08 Perubahan INDEX : sebelumnya i menjadi x; END

                                                saveActivity.setJurnalDetailActivityId(jurnalDetailActivityDao.getNextJurnalActivityId());
                                                saveActivity.setJurnalDetailId(jurnalDetailIdLoop);
                                                saveActivity.setFlag("Y");
                                                saveActivity.setAction("C");
                                                saveActivity.setCreatedDate(updateTime);
                                                saveActivity.setLastUpdate(updateTime);
                                                saveActivity.setCreatedWho(userLogin);
                                                saveActivity.setLastUpdateWho(userLogin);

                                                nilaiActivity = nilaiActivity.add(saveActivity.getJumlah());
                                                jurnalDetailActivityEntityList.add(saveActivity);
                                            }
                                            if (nilaiActivity.compareTo(nilaiLoop)!=0){
                                                status="ERROR : Total Nilai Activity dan nilai yang dikirim tidak sesuai";
                                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                                throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                            }
                                        }catch (Exception e){
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+e.getMessage());
                                            throw new GeneralBOException("Found problem "+e.getMessage()+", please info to your admin...");
                                        }
                                    }
                                }
                            }else{
                                status="ERROR : Tidak bisa mendapat map list";
                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                            }
                        } else {
                            ///////// JIKA JURNAL BIASA ( bukan jurnal detail looping )
                            ItAkunJurnalDetailAkhirTahunEntity jurnalDetailEntity = new ItAkunJurnalDetailAkhirTahunEntity();
                            jurnalDetailEntity.setNoJurnal(noJurnal);
                            jurnalDetailEntity.setRekeningId(rekeningId);
                            jurnalDetailEntity.setNomorRekening(nomorRekening);
                            jurnalDetailEntity.setMasterId(masterId);
                            jurnalDetailEntity.setPasienId(pasienId);
                            jurnalDetailEntity.setNoNota(noNota);
                            jurnalDetailEntity.setDivisiId(divisiId);
                            jurnalDetailEntity.setBiaya(null);

                            jurnalDetailEntity.setFlag("Y");
                            jurnalDetailEntity.setAction("C");
                            jurnalDetailEntity.setCreatedDate(updateTime);
                            jurnalDetailEntity.setLastUpdate(updateTime);
                            jurnalDetailEntity.setCreatedWho(userLogin);
                            jurnalDetailEntity.setLastUpdateWho(userLogin);

                            jurnalDetailEntity.setJurnalDetailId(jurnalDetailId);
                            if (("D").equalsIgnoreCase(mapping.getPosisi())){
                                jurnalDetailEntity.setJumlahDebit(nilai);
                                jurnalDetailEntity.setJumlahKredit(BigDecimal.ZERO);
                            }else if (("K").equalsIgnoreCase(mapping.getPosisi())){
                                jurnalDetailEntity.setJumlahKredit(nilai);
                                jurnalDetailEntity.setJumlahDebit(BigDecimal.ZERO);
                            }

                            try {
                                List<ItJurnalDetailEntity> listOfDuplicate = jurnalDetailAkhirTahunDao.getListJurnalDetailDuplicate(jurnalDetailEntity.getRekeningId(),jurnalDetailEntity.getNoNota(),jurnalDetailEntity.getMasterId(),jurnalDetailEntity.getJumlahDebit(),jurnalDetailEntity.getJumlahKredit());
                                if (listOfDuplicate.size()!=0){
                                    status="ERROR : Ada duplikasi data pada data yang dikirim";
                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                }
                            } catch (HibernateException e) {
                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }

                            // Total dari pembayaran debet dan kredit untuk nanti disamakan
                            totalDebit = totalDebit.add(jurnalDetailEntity.getJumlahDebit());
                            totalKredit = totalKredit.add(jurnalDetailEntity.getJumlahKredit());


                            jurnalDetailEntityList.add(jurnalDetailEntity);
                        }
                    }else{
                        status="ERROR : tidak bisa menemukan biaya pada map yang dikirim";
                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                        throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                    }
                }else{
                    status="ERROR : Keterangan pada mapping masih kosong";
                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                }
            }
            //Pengecekan apakah antara debet dan kredit sudah balance
            totalDebit = totalDebit.setScale(2, BigDecimal.ROUND_HALF_UP);
            totalKredit = totalKredit.setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal balance = totalDebit.subtract(totalKredit);
            if (balance.compareTo(new BigDecimal(0)) == 0){
                // penambahan filter JKR, Sigit 2020-09-15
                if (periodSudahTutup==null || ("Y").equalsIgnoreCase(periodSudahTutup) || "JKR".equalsIgnoreCase(tipeJurnalId)){
                    for (ItAkunJurnalDetailAkhirTahunEntity jurnalDetailEntity : jurnalDetailEntityList){
                        /////////////////////// Save data ///////////////////////
                        if (jurnalDetailEntity.getJumlahDebit().compareTo(BigDecimal.ZERO)==0&&jurnalDetailEntity.getJumlahKredit().compareTo(BigDecimal.ZERO)==0){

                        }else{
                            try {
                                jurnalDetailAkhirTahunDao.addAndSave(jurnalDetailEntity);
                            } catch (HibernateException e) {
                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }
                        }
                    }
//                    for (ItJurnalDetailActivityEntity jurnalDetailActivityEntity : jurnalDetailActivityEntityList){
//                        if (jurnalDetailActivityEntity.getJumlah().compareTo(BigDecimal.ZERO)>0){
//                            try {
//                                jurnalDetailActivityDao.addAndSave(jurnalDetailActivityEntity);
//                            } catch (HibernateException e) {
//                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
//                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                            }
//                        }
//                    }
                }else{
                    for (ItAkunJurnalDetailAkhirTahunEntity jurnalDetailEntity : jurnalDetailEntityList){
                        /////////////////////// Save data Pending ///////////////////////
                        ItJurnalDetailPendingEntity detailPending = new ItJurnalDetailPendingEntity();
                        detailPending.setNoJurnal(jurnalDetailEntity.getNoJurnal());
                        detailPending.setRekeningId(jurnalDetailEntity.getRekeningId());
                        detailPending.setMasterId(jurnalDetailEntity.getMasterId());
                        detailPending.setNoNota(jurnalDetailEntity.getNoNota());
                        detailPending.setJumlahDebit(jurnalDetailEntity.getJumlahDebit());
                        detailPending.setJumlahKredit(jurnalDetailEntity.getJumlahKredit());
                        detailPending.setBiaya(jurnalDetailEntity.getBiaya());
                        detailPending.setFlag(jurnalDetailEntity.getFlag());
                        detailPending.setAction(jurnalDetailEntity.getAction());
                        detailPending.setCreatedDate(jurnalDetailEntity.getCreatedDate());
                        detailPending.setLastUpdate(jurnalDetailEntity.getLastUpdate());
                        detailPending.setCreatedWho(jurnalDetailEntity.getCreatedWho());
                        detailPending.setLastUpdateWho(jurnalDetailEntity.getLastUpdateWho());
                        detailPending.setJurnalDetailId(jurnalDetailEntity.getJurnalDetailId());
                        detailPending.setKdBarang(jurnalDetailEntity.getKdBarang());
                        detailPending.setPasienId(jurnalDetailEntity.getPasienId());
                        detailPending.setNomorRekening(jurnalDetailEntity.getNomorRekening());
                        detailPending.setDivisiId(jurnalDetailEntity.getDivisiId());
                        if (detailPending.getJumlahDebit().compareTo(BigDecimal.ZERO)==0&&detailPending.getJumlahKredit().compareTo(BigDecimal.ZERO)==0){

                        }else{
                            try {
                                jurnalDetailDao.addAndSavePending(detailPending);
                            } catch (HibernateException e) {
                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }
                        }
                    }
//                    for (ItJurnalDetailActivityEntity jurnalDetailActivityEntity : jurnalDetailActivityEntityList){
//                        ItJurnalDetailActivityPendingEntity activityPending = new ItJurnalDetailActivityPendingEntity();
//                        activityPending.setJurnalDetailActivityId(jurnalDetailActivityEntity.getJurnalDetailActivityId());
//                        activityPending.setJurnalDetailId(jurnalDetailActivityEntity.getJurnalDetailId());
//                        activityPending.setActivityId(jurnalDetailActivityEntity.getActivityId());
//                        activityPending.setJumlah(jurnalDetailActivityEntity.getJumlah());
//                        activityPending.setPersonId(jurnalDetailActivityEntity.getPersonId());
//                        activityPending.setFlag(jurnalDetailActivityEntity.getFlag());
//                        activityPending.setTipe(jurnalDetailActivityEntity.getTipe());
//                        activityPending.setNoTrans(jurnalDetailActivityEntity.getNoTrans());
//                        activityPending.setAction(jurnalDetailActivityEntity.getAction());
//                        activityPending.setCreatedDate(jurnalDetailActivityEntity.getCreatedDate());
//                        activityPending.setLastUpdate(jurnalDetailActivityEntity.getLastUpdate());
//                        activityPending.setCreatedWho(jurnalDetailActivityEntity.getCreatedWho());
//                        activityPending.setLastUpdateWho(jurnalDetailActivityEntity.getLastUpdateWho());
//                        if (activityPending.getJumlah().compareTo(BigDecimal.ZERO)>0){
//                            try {
//                                jurnalDetailActivityDao.addAndSavePending(activityPending);
//                            } catch (HibernateException e) {
//                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
//                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                            }
//                        }
//
//                    }
                }

            }else{
                status="ERROR : antara debet dan kredit tidak balance";
                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                throw new GeneralBOException("Found problem "+status+", please info to your admin...");
            }
        }else{
            status="ERROR : tidak bisa menemukan mapping";
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
        }
        return sumber;
    }

    private String getRekeningForMappingOtomatis(String kodeRekening){
        String rekeningId=null;
        /////////////////////JIKA DARI JURNAL OTOMATIS //////////////////////////////////////////////////
        List<ImKodeRekeningEntity> kodeRekeningEntityList;
        try {
            kodeRekeningEntityList = kodeRekeningDao.getIdByCoa(kodeRekening);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        for (ImKodeRekeningEntity kodeRekeningEntity : kodeRekeningEntityList){
            rekeningId = kodeRekeningEntity.getRekeningId();
        }

        if (rekeningId ==null){
            String status="ERROR : tidak bisa menemukan kode rekening";
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
        }

        return rekeningId;
    }

    @Override
    public String getParameterPembayaran(String transaksiId) {
        String parameter;
        try {
            parameter = mappingJurnalDao.getParameterByTransId(transaksiId);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if (parameter == null) {
            String status = "ERROR : tidak ditemukan parameter";
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + status);
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + status);
        }
        return parameter;
    }

    private ItSimrsHeaderDetailCheckupEntity getEntityDetailCheckupByIdDetail(String idDetailCheckup) throws GeneralBOException {
        if (!"".equalsIgnoreCase(idDetailCheckup)){
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_detail_checkup", idDetailCheckup);

            List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntities = new ArrayList<>();
            try {
                detailCheckupEntities = checkupDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PermintaanResepBoImpl.getEntityDetailCheckupByIdDetail] ERROR. ", e);
                throw new GeneralBOException("[PermintaanResepBoImpl.getEntityDetailCheckupByIdDetail] ERROR. ", e);
            }

            if (detailCheckupEntities.size() > 0){
                return detailCheckupEntities.get(0);
            }
        }
        return new ItSimrsHeaderDetailCheckupEntity();
    }

    private ItSimrsHeaderChekupEntity getEntityCheckupById(String id) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getEntityCheckupById] START >>>");
        logger.info("[CheckupBoImpl.getEntityCheckupById] END <<<");
        return headerCheckupDao.getById("noCheckup", id);
    }

    private BigDecimal getSumJumlahTindakanNonBpjs(String idDetailCheckup, String ket) throws GeneralBOException {
        return checkupDetailDao.getSumAllTarifTindakan(idDetailCheckup, "", ket);
    }

    private ImJenisPeriksaPasienEntity getJenisPerikasEntityById(String id) throws GeneralBOException {
        return jenisPeriksaPasienDao.getById("idJenisPeriksaPasien", id);
    }

    private List<ItSimrsDokterTeamEntity> getListEntityTeamDokter(DokterTeam bean) throws GeneralBOException{
        logger.info("[TeamDokterBoImpl.getListEntityTeamDokter] Start >>>>>>>>");
        List<ItSimrsDokterTeamEntity> entities = new ArrayList<>();
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        try {
            entities = dokterTeamDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[TeamDokterBoImpl.getListEntityTeamDokter] Error when get data", e);
            throw new GeneralBOException("[TeamDokterBoImpl.getListEntityTeamDokter] Error when get data"+e);
        }

        logger.info("[TeamDokterBoImpl.getListEntityTeamDokter] End <<<<<<<<");
        return entities;
    }

    private List<ItSimrsRiwayatTindakanEntity> getListEntityRiwayatTindakan(RiwayatTindakan bean) throws GeneralBOException{
        logger.info("[RiwayatTindakanBoImpl.getListEntityRiwayatTindakan] Start >>>>>>>>");
        List<ItSimrsRiwayatTindakanEntity> entities = new ArrayList<>();
        Map hsCriteria = new HashMap();

        if (bean != null) {
            if (bean.getIdRiwayatTindakan() != null) {
                hsCriteria.put("id_riwayat_tindakan", bean.getIdRiwayatTindakan());
            }
            if (bean.getIdTindakan() != null) {
                hsCriteria.put("id_tindakan", bean.getIdTindakan());
            }
            if (bean.getIdDetailCheckup() != null) {
                hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if (bean.getApproveBpjsFlag() != null) {
                hsCriteria.put("approve_bpjs_flag", bean.getApproveBpjsFlag());
            }
            if (bean.getKeterangan() != null) {
                hsCriteria.put("keterangan", bean.getKeterangan());
            }
            if (bean.getJenisPasien() != null) {
                hsCriteria.put("jenis_pasien", bean.getJenisPasien());
            }
            if (bean.getNotResep() != null) {
                hsCriteria.put("not_resep", bean.getNotResep());
            }

            hsCriteria.put("flag", "Y");

            try {
                entities = riwayatTindakanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RiwayatTindakanBoImpl.getListEntityRiwayatTindakan] Error when get data riwayat tindakan. ", e);
                throw new GeneralBOException("[RiwayatTindakanBoImpl.getListEntityRiwayatTindakan] Error when get data riwayat tindakan. "+e);
            }
        }

        logger.info("[RiwayatTindakanBoImpl.getListEntityRiwayatTindakan] End <<<<<<<<");
        return entities;
    }

    public ItSimrsTindakanTransitorisEntity getTindakanTransitorisById(String id) throws GeneralBOException {
        return tindakanTransitorisDao.getById("idRiwayatTindakan", id);
    }

    private ItSimrsRiwayatTindakanEntity getRiwayatTindakanResep(String idDetail, String jenisPasien) throws GeneralBOException {

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", idDetail);

        if (jenisPasien != null && !"".equalsIgnoreCase(jenisPasien)){
            hsCriteria.put("jenis_pasien", jenisPasien);
        }

        hsCriteria.put("keterangan", "resep");

        List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = new ArrayList<>();

        try {
            riwayatTindakanEntities = riwayatTindakanDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[RiwayatTindakanBoImpl.getRiwayatTindakanResep] ERROR. ", e);
            throw new GeneralBOException("[RiwayatTindakanBoImpl.getRiwayatTindakanResep] ERROR. ", e);
        }

        if (riwayatTindakanEntities.size() > 0){
            return riwayatTindakanEntities.get(0);
        }

        return new ItSimrsRiwayatTindakanEntity();
    }

    private ImSimrsPermintaanResepEntity getEntityPermintaanResepById(String id) throws GeneralBOException {
        return permintaanResepDao.getById("idPermintaanResep", id);
    }

    public ImSimrsPelayananEntity getPelayananById(String id) throws GeneralBOException {
        logger.info("[PelayananBoImpl.getByCriteria] Start >>>>>>");
        logger.info("[PelayananBoImpl.getByCriteria] End <<<<<<");
        return pelayananDao.getById("idPelayanan", id);
    }

    private ImPosition getPositionEntityById(String id) throws GeneralBOException {
        return positionDao.getById("positionId", id);
    }

    private ItSimrsHeaderDetailCheckupEntity getDetailCheckupById(String id) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.getDetailCheckupById] START >>>>");
        logger.info("[CheckupDetailBoImpl.getDetailCheckupById] END <<<");
        return checkupDetailDao.getById("idDetailCheckup", id);
    }

    private RawatInap getLastUsedRoom(String id) throws GeneralBOException {
        return rawatInapDao.getLastRuanganById(id);
    }

    private MtSimrsRuanganEntity getEntityRuanganById(String id) throws GeneralBOException {
        return ruanganDao.getById("idRuangan", id);
    }

    private ImSimrsKelasRuanganEntity getKelasRuanganById(String id) throws GeneralBOException {
        return kelasRuanganDao.getById("idKelasRuangan", id);
    }

    private ImSimrsAsuransiEntity getEntityAsuransiById(String idAsuransi) throws GeneralBOException {
        return asuransiDao.getById("idAsuransi", idAsuransi);
    }

    public ImMasterEntity getEntityMasterById(String id) throws GeneralBOException {
        return masterDao.getById("primaryKey.nomorMaster", id);
    }

    private BigDecimal getJumlahNilaiBiayaByKeterangan(String idDetailCheckup, String jenisPasien, String keterangan, String idRuangan){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        BigDecimal nilai = new BigDecimal(0);
        try {
            if (idRuangan == null || "".equalsIgnoreCase(idRuangan)) {
                nilai = checkupDetailBo.getSumJumlahTindakanByJenis(idDetailCheckup, jenisPasien, keterangan);
            } else {
                nilai = checkupDetailBo.getSumJumlahTindakanByJenisRI(idDetailCheckup, jenisPasien, keterangan, idRuangan);
            }
        } catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR ", e);
            throw new GeneralBOException("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR " + e);

        }
        return nilai;
    }

    public String getDivisiId(String idDetailCheckup, String jenisPasien, String keterangan, String idRuangan) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        KelasRuanganBo kelasRuanganBo = (KelasRuanganBo) ctx.getBean("kelasRuanganBoProxy");
        RuanganBo ruanganBo = (RuanganBo) ctx.getBean("ruanganBoProxy");
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

        String divisiId = "";

        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);

        if ("resep".equalsIgnoreCase(keterangan)) {
            ItSimrsRiwayatTindakanEntity riwayatTindakanEntity = riwayatTindakanBo.getRiwayatTindakanResep(idDetailCheckup, jenisPasien);
            if (riwayatTindakanEntity != null) {
                ImSimrsPermintaanResepEntity permintaanResepEntity = permintaanResepBo.getEntityPermintaanResepById(riwayatTindakanEntity.getIdTindakan());
                if (permintaanResepEntity != null) {
                    Pelayanan pelayananEntity = pelayananBo.getPelayananById(permintaanResepEntity.getTujuanPelayanan());
                    if (pelayananEntity != null) {
                        ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                        if (position != null) {
                            divisiId = position.getKodering();
                        }
                    }
                }
            }
        } else if ("laboratorium".equalsIgnoreCase(keterangan) || "radiologi".equalsIgnoreCase(keterangan) || "lab".equalsIgnoreCase(keterangan)){
            divisiId = periksaLabBo.getDivisiIdKodering(idDetailCheckup, keterangan);
        } else if ("gizi".equalsIgnoreCase(keterangan)){

            detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
            if (detailCheckupEntity != null){

                Pelayanan pelayanan = new Pelayanan();
                pelayanan.setBranchId(detailCheckupEntity.getBranchId());
                pelayanan.setTipePelayanan("gizi");

                List<Pelayanan> pelayananList = pelayananBo.getByCriteria(pelayanan);
                if (pelayananList.size() > 0){
                    Pelayanan pelayananData = pelayananList.get(0);

                    ImPosition position = positionBo.getPositionEntityById(pelayananData.getDivisiId());
                    if (position != null) {
                        divisiId = position.getKodering();
                    }
                }
            }

        } else {

            if (detailCheckupEntity != null && detailCheckupEntity.getIdPelayanan() != null) {
                try {
                    Pelayanan pelayananEntity = pelayananBo.getPelayananById(detailCheckupEntity.getIdPelayanan());

                    // jika poli selain rawat inap maka mengambil kodering dari pelayanan
                    // jika poli rawat rawat inap maka mengambil kodering dari kelas ruangan , Sigit
                    if (pelayananEntity != null && !"rawat_inap".equalsIgnoreCase(pelayananEntity.getTipePelayanan())) {

                        ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                        if (position != null) {
                            divisiId = position.getKodering();
                        }

                    } else {

                        if (idRuangan != null && !"".equalsIgnoreCase(idRuangan)){
                            MtSimrsRuanganEntity ruanganEntity = ruanganBo.getEntityRuanganById(idRuangan);
                            if (ruanganEntity != null) {
                                ImSimrsKelasRuanganEntity kelasRuanganEntity = kelasRuanganBo.getKelasRuanganById(ruanganEntity.getIdKelasRuangan());
                                if (kelasRuanganEntity != null) {
                                    ImPosition position = positionBo.getPositionEntityById(kelasRuanganEntity.getDivisiId());
                                    if (position != null) {
                                        divisiId = position.getKodering();
                                    }
                                }
                            }
                        } else {
                            RawatInap lastRuangan = rawatInapBo.getLastUsedRoom(idDetailCheckup);
                            if (lastRuangan != null) {
                                MtSimrsRuanganEntity ruanganEntity = ruanganBo.getEntityRuanganById(lastRuangan.getIdRuang());
                                if (ruanganEntity != null) {
                                    ImSimrsKelasRuanganEntity kelasRuanganEntity = kelasRuanganBo.getKelasRuanganById(ruanganEntity.getIdKelasRuangan());
                                    if (kelasRuanganEntity != null) {
                                        ImPosition position = positionBo.getPositionEntityById(kelasRuanganEntity.getDivisiId());
                                        if (position != null) {
                                            divisiId = position.getKodering();
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (GeneralBOException e) {
                    throw new GeneralBOException("[getDivisiId] ERROR " + e);
                }
            } else {
                throw new GeneralBOException("[getDivisiId] ERROR gagal mendapakatkan divisi_id atau data detail checkup");
            }
        }
        return divisiId;
    }


    private List<Map> getAcitivityList(String idDetailCheckup, String jenisPasien, String ket, String type, String idRuangan) {
        logger.info("[CheckupDetailAction.getAcitivityList] START >>>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TeamDokterBo teamDokterBo = (TeamDokterBo) ctx.getBean("teamDokterBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");

        //** mencari tindakan dan dimasukan ke jurnal detail activity. START **//
        // dokter team

        List<Map> activityList = new ArrayList<>();

        String idDokter = "";
        DokterTeam dokterTeam = new DokterTeam();
        dokterTeam.setIdDetailCheckup(idDetailCheckup);
        List<ItSimrsDokterTeamEntity> dokterTeamEntities = teamDokterBo.getListEntityTeamDokter(dokterTeam);
        if (dokterTeamEntities.size() > 0) {
            ItSimrsDokterTeamEntity dokterTeamEntity = dokterTeamEntities.get(0);
            idDokter = dokterTeamEntity.getIdDokter();
        }

        // riwayat tindakan list
        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
        riwayatTindakan.setIdDetailCheckup(idDetailCheckup);
        riwayatTindakan.setJenisPasien(jenisPasien);

        if ("".equalsIgnoreCase(ket)) {
            riwayatTindakan.setNotResep("Y");
        } else {
            riwayatTindakan.setKeterangan(ket);
        }

        if (idRuangan != null && !"".equalsIgnoreCase(idRuangan))
            riwayatTindakan.setIdRuangan(idRuangan);

        List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = riwayatTindakanBo.getListEntityRiwayatTindakan(riwayatTindakan);
        if (riwayatTindakanEntities.size() > 0) {
            for (ItSimrsRiwayatTindakanEntity riwayatTindakanEntity : riwayatTindakanEntities) {

                // cari id dokter per keterangan riwayat tindakan
                String idDokterTindakan = getIdDokter(riwayatTindakanEntity.getIdTindakan(), riwayatTindakanEntity.getKeterangan());

                // jika selain JRJ
                // maka obat dikenakan PPN
                BigDecimal ppn = new BigDecimal(0);

                // mencari apakah tindakan transitoris
                boolean nonTransitoris = true;
                if ("JRI".equalsIgnoreCase(type)) {
                    ItSimrsTindakanTransitorisEntity transitorisEntity = riwayatTindakanBo.getTindakanTransitorisById(riwayatTindakanEntity.getIdRiwayatTindakan());
                    if (transitorisEntity != null) {
                        // jika ditemukan transitoris
                        // maka transitoris;
                        nonTransitoris = false;
                    }
                }

                // jika bukan Transitoris
                // maka ditambahkan activity
                if (nonTransitoris) {
                    Map activityMap = new HashMap();
                    activityMap.put("activity_id", riwayatTindakanEntity.getIdTindakan());
                    activityMap.put("person_id", idDokterTindakan != null && !"".equalsIgnoreCase(idDokterTindakan) ? idDokterTindakan : idDokter);
                    activityMap.put("nilai", riwayatTindakanEntity.getTotalTarif().add(ppn));
                    activityMap.put("no_trans", riwayatTindakanEntity.getIdDetailCheckup());
                    activityMap.put("tipe", riwayatTindakanEntity.getJenisPasien());
                    activityList.add(activityMap);
                }
            }
        }
        //** mencari tindakan dan dimasukan ke jurnal detail activity. END **//
        logger.info("[CheckupDetailAction.getAcitivityList] END <<<");
        return activityList;
    }


    // create transitoris
    private void createJurnalTransitoris(TutupPeriod bean) throws GeneralBOException{
        logger.info("[TutuPeriodAction.createJurnalTransitoris] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");

        Jurnal returnJurnal= new Jurnal();

        String masterId = "";
        String divisiId = "";
        String jenisPasien = "";
        String bukti = "";
        String transId = "";
        String idPasien = "";
        String divisiResep = "";
        String idJenisPasien = "";
        String invoiceNumber = createInvoiceNumber("JRI", bean.getUnit());

        Map mapJurnal = new HashMap();
        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = getEntityDetailCheckupByIdDetail(bean.getIdDetailCheckup());
        if (detailCheckupEntity != null){

            idPasien = getEntityCheckupById(detailCheckupEntity.getNoCheckup()).getIdPasien();
            bukti = invoiceNumber;
            if ("bpjs".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())){

                bukti = detailCheckupEntity.getNoSep();
                jenisPasien = "No. SEP : "+detailCheckupEntity.getNoSep();

                ImJenisPeriksaPasienEntity jenisPeriksaPasienEntity = getJenisPerikasEntityById(detailCheckupEntity.getIdJenisPeriksaPasien());
                if (jenisPeriksaPasienEntity != null){
                    masterId = jenisPeriksaPasienEntity.getMasterId();
                }

                idJenisPasien = "bpjs";

            } else if ("asuransi".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())){

                ImSimrsAsuransiEntity asuransiEntity = getEntityAsuransiById(detailCheckupEntity.getIdAsuransi());
                jenisPasien = "Asuransi "+asuransiEntity.getNamaAsuransi();
                masterId = asuransiEntity.getNoMaster();
                idJenisPasien = "asuransi";

            } else if ("ptpn".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())){

                ImMasterEntity masterEntity = getEntityMasterById(detailCheckupEntity.getIdAsuransi());
                jenisPasien = masterEntity.getNama();
                masterId = detailCheckupEntity.getIdAsuransi();
                idJenisPasien = "bpjs";
            } else {
                ItSimrsHeaderChekupEntity headerChekupEntity = getEntityCheckupById(detailCheckupEntity.getNoCheckup());
                if (headerChekupEntity == null){
                    logger.error("[TutupPeriodAction.createJurnalTransitoris] ERROR. data header is null");
                    throw new GeneralBOException("[TutupPeriodAction.createJurnalTransitoris] ERROR. data header is null");
                }
                jenisPasien = "No. RM "+headerChekupEntity.getIdPasien();
                ImJenisPeriksaPasienEntity jenisPeriksaPasienEntity = getJenisPerikasEntityById(detailCheckupEntity.getIdJenisPeriksaPasien());
                if (jenisPeriksaPasienEntity != null){
                    masterId = jenisPeriksaPasienEntity.getMasterId();
                }

                idJenisPasien = "umum";
            }

            BigDecimal jumlahAllTindakan = getJumlahNilaiBiayaByKeterangan(bean.getIdDetailCheckup(), "", "", "");


            jenisPasien = jenisPasien + " No. Detail Checkup "+detailCheckupEntity.getIdDetailCheckup();
            transId = "32";

            Map mapPiutang = new HashMap();
            mapPiutang.put("bukti", bukti);
            mapPiutang.put("nilai", jumlahAllTindakan);
            if ("umum".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien()) && "non_tunai".equalsIgnoreCase(detailCheckupEntity.getMetodePembayaran())){
                mapPiutang.put("pasien_id", idPasien);
            } else {
                mapPiutang.put("master_id", masterId);
            }

            List<Map> listOfMapTindakan = new ArrayList<>();
            List<String> listOfKeteranganRiwayat = riwayatTindakanBo.getListKeteranganByIdDetailCheckup(bean.getIdDetailCheckup());
            if (listOfKeteranganRiwayat.size() > 0){

                for (String keterangan : listOfKeteranganRiwayat) {
                    if ("kamar".equalsIgnoreCase(keterangan) || "tindakan".equalsIgnoreCase(keterangan)){

                        // mencari list ruangan
                        List<String> listRuangan = riwayatTindakanBo.getListRuanganRiwayatTindakan(bean.getIdDetailCheckup(), keterangan);
                        if (listRuangan.size() > 0){
                            for (String ruangan : listRuangan){
                                Map mapTindakan = new HashMap();
                                mapTindakan.put("master_id", masterId);
                                mapTindakan.put("divisi_id", getDivisiId(bean.getIdDetailCheckup(), idJenisPasien, keterangan, ruangan));
                                mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(bean.getIdDetailCheckup(), idJenisPasien, keterangan, ruangan));
                                mapTindakan.put("activity", getAcitivityList(bean.getIdDetailCheckup(), idJenisPasien, keterangan, "JRI", ruangan));
                                listOfMapTindakan.add(mapTindakan);
                            }
                        }
                    } else {
                        Map mapTindakan = new HashMap();
                        mapTindakan.put("master_id", masterId);
                        mapTindakan.put("divisi_id", getDivisiId(bean.getIdDetailCheckup(), idJenisPasien, keterangan,""));
                        mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(bean.getIdDetailCheckup(), idJenisPasien, keterangan, ""));
                        mapTindakan.put("activity", getAcitivityList(bean.getIdDetailCheckup(), idJenisPasien, keterangan, "JRI", ""));
                        listOfMapTindakan.add(mapTindakan);
                    }
                }
            }


            mapJurnal.put("piutang_transistoris_pasien_rawat_inap", mapPiutang);
            mapJurnal.put("pendapatan_rawat_inap", listOfMapTindakan);
        }
        String catatan = "Transitoris Rawat Inap saat tutup periode "+jenisPasien;

        try {

            returnJurnal = createJurnal(transId, mapJurnal, bean.getUnit(), catatan, "Y");

            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setIdDetailCheckup(bean.getIdDetailCheckup());
            detailCheckup.setTransPeriode(bean.getBulan()+"-"+bean.getTahun());
            detailCheckup.setTransDate(bean.getCreatedDate());
            detailCheckup.setNoJurnalTrans(returnJurnal.getNoJurnal());
            detailCheckup.setInvoice(invoiceNumber);
            detailCheckup.setAction("U");
            detailCheckup.setLastUpdate(bean.getCreatedDate());
            detailCheckup.setLastUpdateWho(bean.getCreatedWho());

            // update invoice transitoris & no jurnal to detail ceckup
            saveUpdateNoJuran(detailCheckup);

        } catch (GeneralBOException e){
            logger.error("[TutupPeriodAction.createJurnalTransitoris] ERROR. ", e);
            throw new GeneralBOException("[TutupPeriodAction.createJurnalTransitoris] ERROR. ", e);
        }

        logger.info("[TutuPeriodAction.createJurnalTransitoris] END <<<");
    }

    public void saveUpdateNoJuran(HeaderDetailCheckup bean) throws GeneralBOException {
        if (bean != null){

            HeaderDetailCheckup detail = new HeaderDetailCheckup();
            detail.setIdDetailCheckup(bean.getIdDetailCheckup());

            List<ItSimrsHeaderDetailCheckupEntity> details = getListEntityByCriteria(detail);
            if (details.size() > 0){
                for (ItSimrsHeaderDetailCheckupEntity detailCheckupEntity : details){
                    detailCheckupEntity.setAction(bean.getAction());
                    detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                    detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    // for transitoris or jurnal rawat
                    if (bean.getNoJurnalTrans() != null && !"".equalsIgnoreCase(bean.getNoJurnalTrans())){
                        detailCheckupEntity.setNoJurnalTrans(bean.getNoJurnalTrans());
                        detailCheckupEntity.setTransPeriode(bean.getTransPeriode());
                        detailCheckupEntity.setTransDate(bean.getTransDate());
                        detailCheckupEntity.setInvoiceTrans(bean.getInvoice());
                    } else {
                        detailCheckupEntity.setNoJurnal(bean.getNoJurnal());
                    }

                    try {
                        checkupDetailDao.updateAndSave(detailCheckupEntity);
                    } catch (HibernateException e){
                        logger.error("[PermintaanResepBoImpl.saveUpdateNoJuran] ERROR. ", e);
                        throw new GeneralBOException("[PermintaanResepBoImpl.saveUpdateNoJuran] ERROR. ", e);
                    }
                }
            }
        }
    }

    private List<ItSimrsHeaderDetailCheckupEntity> getListEntityByCriteria(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.getListEntityByCriteria] Start >>>>>>>");
        List<ItSimrsHeaderDetailCheckupEntity> entityList = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())) {
            hsCriteria.put("no_checkup", bean.getNoCheckup());
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
        }

        try {
            entityList = checkupDetailDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[CheckupDetailBoImpl.getListEntityByCriteria] Error when get data detail checkup entity ", e);
            throw new GeneralBOException("Error when get data detail checkup entity " + e.getMessage());
        }

        logger.info("[CheckupDetailBoImpl.getListEntityByCriteria] End <<<<<<<<");
        return entityList;
    }

    public String generateTrasitorisId() {
        java.util.Date now = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        return "TR" +  f.format(now) + tindakanTransitorisDao.getNextSeq();
    }

    private void saveTindakanTransitoris(String idDetailCheckup, Timestamp time, String user) throws GeneralBOException {

        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
        riwayatTindakan.setIdDetailCheckup(idDetailCheckup);

        List<ItSimrsRiwayatTindakanEntity> tindakanEntities = getListEntityRiwayatTindakan(riwayatTindakan);
        if (tindakanEntities.size() > 0){
            for (ItSimrsRiwayatTindakanEntity tindakanEntity : tindakanEntities){

                ItSimrsTindakanTransitorisEntity transitorisEntity = new ItSimrsTindakanTransitorisEntity();
                transitorisEntity.setId(generateTrasitorisId());
                transitorisEntity.setIdRiwayatTindakan(tindakanEntity.getIdRiwayatTindakan());
                transitorisEntity.setIdTindakan(tindakanEntity.getIdTindakan());
                transitorisEntity.setNamaTindakan(tindakanEntity.getNamaTindakan());
                transitorisEntity.setKeterangan(tindakanEntity.getKeterangan());
                transitorisEntity.setJenisPasien(tindakanEntity.getJenisPasien());
                transitorisEntity.setTotalTarif(tindakanEntity.getTotalTarif());
                transitorisEntity.setKategoriTindakanBpjs(tindakanEntity.getKategoriTindakanBpjs());
                transitorisEntity.setApproveBpjsFlag(tindakanEntity.getApproveBpjsFlag());
                transitorisEntity.setAction(tindakanEntity.getAction());
                transitorisEntity.setFlag(tindakanEntity.getFlag());
                transitorisEntity.setTanggalTindakan(tindakanEntity.getTanggalTindakan());
                transitorisEntity.setIdDetailCheckup(idDetailCheckup);

                transitorisEntity.setCreatedDate(time);
                transitorisEntity.setCreatedWho(user);
                transitorisEntity.setLastUpdate(time);
                transitorisEntity.setLastUpdateWho(user);

                try {
                    tindakanTransitorisDao.addAndSave(transitorisEntity);
                } catch (HibernateException e){
                    logger.error("[RiwayatTindakanBoImpl.saveTindakanTransitoris] ERROR. ", e);
                    throw new GeneralBOException("[RiwayatTindakanBoImpl.saveTindakanTransitoris] ERROR. ", e);
                }
            }
        }
    }

    @Override
    public void saveTutupPeriod(List<TutupPeriod> listTransitoris, TutupPeriod tutupPeriod) throws GeneralBOException{

        logger.info("[BillingSystemBoImpl.saveTutupPeriod] START >>>");

        // jika ada transitoris
        if (!"12a".equalsIgnoreCase(tutupPeriod.getTipePeriode()) && !"12b".equalsIgnoreCase(tutupPeriod.getTipePeriode())){
            for (TutupPeriod transJurnal : listTransitoris){

                try {
                    // create jurnal transitoris, Sigit
                    createJurnalTransitoris(transJurnal);
                } catch (GeneralBOException e){
                    logger.error("[BillingSystemBoImpl.saveTutupPeriod] ERROR when create jurnal transitoris. ",e);
                    throw new GeneralBOException("[BillingSystemBoImpl.saveTutupPeriod] ERROR when create jurnal transitoris. "+e);
                }

                try {
                    // jika dibuatkan jurnal save tindakan - tindakan ke table tindakan transitoris, Sigit
                    saveTindakanTransitoris(transJurnal.getIdDetailCheckup(), transJurnal.getCreatedDate(), transJurnal.getCreatedWho());
                } catch (GeneralBOException e){
                    logger.error("[BillingSystemBoImpl.saveTutupPeriod] ERROR when insert tindakan to tindankan transitoris. ",e);
                    throw new GeneralBOException("[BillingSystemBoImpl.saveTutupPeriod] ERROR when insert tindakan to tindankan transitoris. "+e);
                }
            }

            // tutup period dan generate saldo bulan lalu transaksi RS, sigit
            try {
                saveUpdateTutupPeriod(tutupPeriod);
            } catch (GeneralBOException e){
                logger.error("[TutupPeriodAction.saveTutupPeriod] ERROR when create tutup periode. ", e);
                throw new GeneralBOException("[BillingSystemBoImpl.saveTutupPeriod] ERROR when create tutup periode. "+e);
            }
        }




        // membuat jurnal balik akhir tahun
        // create saldo akhir tutup tahun
        if ("12".equalsIgnoreCase(tutupPeriod.getBulan())){

            BatasTutupPeriod batas = new BatasTutupPeriod();
            batas.setBulan(tutupPeriod.getBulan());
            batas.setTahun(tutupPeriod.getTahun());
            batas.setUnit(tutupPeriod.getUnit());

//            String tipePeriode = "12";
//            List<ItSimrsBatasTutupPeriodEntity> tutupPeriodEntities = getListEntityBatasTutupPeriode(batas);
//            if (tutupPeriodEntities.size() > 0){
//                ItSimrsBatasTutupPeriodEntity batasTutupPeriodEntity = tutupPeriodEntities.get(0);
//                if ("Y".equalsIgnoreCase(batasTutupPeriodEntity.getFlagDesemberA())){
//                    tipePeriode = "12a";
//                }
//                if ("Y".equalsIgnoreCase(batasTutupPeriodEntity.getFlagDesemberA())){
//                    tipePeriode = "12b";
//                }
//            }

//            tutupPeriod.setTipePeriode(tutupPeriod.getTipePeriode());
            tutupPeriod.setPeriode(tutupPeriod.getBulan() +"-"+ tutupPeriod.getTahun());

            // create jurnal
            createJurnalBalikAkhirTahun(tutupPeriod);
            // jika transaksi bulan berjalan maka update bulan berjalan;
//            boolean bulanBerjalan = "12a".equalsIgnoreCase(tutupPeriod.getTipePeriode()) || "12b".equalsIgnoreCase(tutupPeriod.getTipePeriode());
//            if (bulanBerjalan){
//                updateSaldoAkhirBulanBerjalan(tutupPeriod);
//            }
//            createSaldoAkhirTahun(tutupPeriod);
        }

        // create saldo bulan lalu pada transaksi stok;
        Map hsCriteria = new HashMap();
        hsCriteria.put("branch_id", tutupPeriod.getUnit());
        hsCriteria.put("in_pelayanan_medic", "Y");
        hsCriteria.put("flag", "Y");
//        List<ImSimrsPelayananEntity> pelayananEntities = pelayananDao.getByCriteria(hsCriteria);
//        List<ImSimrsPelayananEntity> pelayananEntities = new ArrayList<>();
        Pelayanan ply = new Pelayanan();
        ply.setBranchId(tutupPeriod.getUnit());
        ply.setFlag("Y");
        List<Pelayanan> pelayananEntities = pelayananDao.getListObjectPelayanan(ply);
        if (pelayananEntities.size() > 0){
            for (Pelayanan pelayananEntity : pelayananEntities){

                // pelayanan selain gudang obat;
                if (!"gudang_obat".equalsIgnoreCase(pelayananEntity.getTipePelayanan())){
                    List<String> idObats = obatPoliDao.getIdObatGroup(pelayananEntity.getIdPelayanan(), tutupPeriod.getUnit());
                    if (idObats.size() > 0){
                        for (String idObat : idObats){

                            // generate saldo bulan lalu (bulan ini) untuk bulan depan
                            generateAndSaveCurrentSaldoPersediaanToNextMonth(
                                    tutupPeriod.getUnit(),
                                    idObat,
                                    Integer.valueOf(tutupPeriod.getBulan()),
                                    Integer.valueOf(tutupPeriod.getTahun()),
                                    pelayananEntity.getIdPelayanan(),
                                    tutupPeriod.getLastUpdateWho(),
                                    tutupPeriod.getLastUpdate(),
                                    ""
                                    );
                        }
                    }
                } else {

                    // untuk pelayanan gudang obat;
                    List<String> idObats = obatDao.getListIdObatGroupByBranchId(tutupPeriod.getUnit());
                    if (idObats.size() > 0){
                        for (String idObat : idObats){

                            // generate saldo bulan lalu (bulan ini) untuk bulan depan
                            generateAndSaveCurrentSaldoPersediaanToNextMonth(
                                    tutupPeriod.getUnit(),
                                    idObat,
                                    Integer.valueOf(tutupPeriod.getBulan()),
                                    Integer.valueOf(tutupPeriod.getTahun()),
                                    pelayananEntity.getIdPelayanan(),
                                    tutupPeriod.getLastUpdateWho(),
                                    tutupPeriod.getLastUpdate(),
                                    ""
                            );
                        }
                    }
                }
            }
        }

        logger.info("[BillingSystemBoImpl.saveTutupPeriod] END <<<");
    }

    private String tahunDepan(String tahun){
        Integer intTahunDepan = Integer.valueOf(tahun) + 1;
        return intTahunDepan.toString();
    }

    private void createJurnalBalikAkhirTahun(TutupPeriod tutupPeriod){
        logger.info("[BillingSystemBoImpl.createJurnalBalikAkhirTahun] START >>>");

        ImBranchesPK primaryKey = new ImBranchesPK();
        primaryKey.setId(tutupPeriod.getUnit());
        ImBranches imBranches = branchDao.getById(primaryKey,"Y");

        boolean bulanBerjalan = "12a".equalsIgnoreCase(tutupPeriod.getTipePeriode()) || "12b".equalsIgnoreCase(tutupPeriod.getTipePeriode());

        Integer level = getLowestLevelKodeRekening();

//        Map mapTrans = new HashMap();
//        mapTrans.put("pendapatan", "");
//        mapTrans.put("biaya", "");
//        mapTrans.put("investasi", "");

        // bulan berjalan, cari bulan berjalan terakhir pada saldo akhir
        if (bulanBerjalan){
            BatasTutupPeriod periodeBerjalan = getLastBulanBerjalanSaldoAkhir(tutupPeriod.getTahun(), tutupPeriod.getUnit());
            if (periodeBerjalan != null){
                tutupPeriod.setPeriode(periodeBerjalan.getBulan()+"-"+periodeBerjalan.getTahun());
            }
        }

        List<SaldoAkhir> listSaldo = getListSaldoAkhir(tutupPeriod.getUnit(), tutupPeriod.getPeriode(), "%", new BigInteger(String.valueOf(level)));
        if (listSaldo.size() > 0){

            Map dataBilling = new HashMap();
            // debit
            List<Map> mapPendapatan = new ArrayList<>();
            // kredit
            List<Map> mapBiaya = new ArrayList<>();

            BigDecimal nilaiPendapatan = new BigDecimal(0);
            BigDecimal nilaiBiaya = new BigDecimal(0);
            for (SaldoAkhir saldoAkhir : listSaldo){

                Map mapData = new HashMap();
                if ("02".equalsIgnoreCase(saldoAkhir.getTipeCoa())){

                    List<SaldoAkhir> listSaldoAkhirDetail = getListSAldoAkhirDetailByIdSaldo(saldoAkhir.getSaldoAkhirId());
                    if (listSaldoAkhirDetail.size() > 0){
                        for (SaldoAkhir saldoDetail : listSaldoAkhirDetail){
                            mapData.put("rekening_id",saldoAkhir.getRekeningId());
                            mapData.put("nilai",saldoDetail.getSaldo());
                            mapData.put("master_id", saldoDetail.getMasterId() == null ? "" : saldoDetail.getMasterId());
                            mapData.put("divisi_id", saldoDetail.getDivisiId() == null ? "" : saldoDetail.getDivisiId());
                            mapPendapatan.add(mapData);
                            nilaiPendapatan = nilaiPendapatan.add(saldoDetail.getSaldo());
                        }
                    } else {
                        mapData.put("rekening_id",saldoAkhir.getRekeningId());
                        mapData.put("nilai",saldoAkhir.getSaldo());
                        mapData.put("master_id", "");
                        mapData.put("divisi_id", "");
                        mapPendapatan.add(mapData);
                        nilaiPendapatan = nilaiPendapatan.add(saldoAkhir.getSaldo());
                    }
                } else if ("03".equalsIgnoreCase(saldoAkhir.getTipeCoa())){
                    List<SaldoAkhir> listSaldoAkhirDetail = getListSAldoAkhirDetailByIdSaldo(saldoAkhir.getSaldoAkhirId());
                    if (listSaldoAkhirDetail.size() > 0){
                        for (SaldoAkhir saldoDetail : listSaldoAkhirDetail){
                            mapData.put("rekening_id",saldoAkhir.getRekeningId());
                            mapData.put("nilai",saldoDetail.getSaldo());
                            mapData.put("divisi_id", saldoDetail.getDivisiId() == null ? "" : saldoDetail.getDivisiId());
                            mapBiaya.add(mapData);
                            nilaiBiaya = nilaiBiaya.add(saldoDetail.getSaldo());
                        }
                    } else {
                        mapData.put("rekening_id",saldoAkhir.getRekeningId());
                        mapData.put("nilai",saldoAkhir.getSaldo());
                        mapData.put("divisi_id", "");
                        mapBiaya.add(mapData);
                        nilaiBiaya = nilaiBiaya.add(saldoAkhir.getSaldo());
                    }
                }

//                if ("D".equalsIgnoreCase(saldoAkhir.getPosisi())){
//                    // jika debit masukan ke kredit;
//                    mapBiaya.add(mapData);
//                } else {
//                    // jika kredit masukan ke debit;
//                    mapPendapatan.add(mapData);
//                }
            }



            ImKodeRekeningEntity kodeRekeningEntity = kodeRekeningDao.getById("kodeRekening", CommonConstant.KODE_REKENING_LABA_RUGI);
            if (nilaiPendapatan.compareTo(nilaiBiaya) == 1){
                nilaiPendapatan = nilaiPendapatan.subtract(nilaiBiaya);
                Map mapLabaRugi = new HashMap();
                mapLabaRugi.put("rekening_id", kodeRekeningEntity.getRekeningId());
                mapLabaRugi.put("nilai", nilaiPendapatan);
                mapLabaRugi.put("divisi_id", "");
                mapBiaya.add(mapLabaRugi);
            } else {
                nilaiBiaya = nilaiBiaya.subtract(nilaiPendapatan);
                Map mapLabaRugi = new HashMap();
                mapLabaRugi.put("rekening_id", kodeRekeningEntity.getRekeningId());
                mapLabaRugi.put("nilai", nilaiBiaya);
                mapLabaRugi.put("master_id", "");
                mapLabaRugi.put("divisi_id", "");
                mapPendapatan.add(mapLabaRugi);
            }

            dataBilling.put("pendapatan", mapPendapatan);
            dataBilling.put("biaya", mapBiaya);

            String periode = "";
            if (bulanBerjalan){
                periode = tutupPeriod.getTipePeriode() + " " + tutupPeriod.getTahun();
            } else {
                periode = tutupPeriod.getBulan() +" "+ tutupPeriod.getTahun();
            }

            String tipePeriod = tutupPeriod.getTipePeriode() == null || "".equalsIgnoreCase(tutupPeriod.getTipePeriode()) ? "12" : tutupPeriod.getTipePeriode();

            String catatan = "Jurnal Balik Tutup Tahun " +imBranches.getBranchName() + " Periode "+ periode;

            Integer lastDateOfMonth = CommonUtil.getLastDateOfMonth(formatToMM(tutupPeriod.getBulan()) +"-"+tutupPeriod.getTahun());
            String lastDate = tutupPeriod.getTahun() + "-" + tutupPeriod.getBulan() + "-" + lastDateOfMonth;

            try {
                Jurnal jurnal = createJurnalTutupTahun(CommonConstant.TRANSAKSI_ID_KOREKSI_AKHIR_TAHUN, dataBilling, tutupPeriod.getUnit() ,catatan ,"Y", lastDate, tipePeriod);

                // update batas tutup period
                TutupPeriod period = new TutupPeriod();
                period.setNoJurnal(jurnal.getNoJurnal());
                period.setBulan(tutupPeriod.getBulan());
                period.setTahun(tutupPeriod.getTahun());
                period.setUnit(tutupPeriod.getUnit());
                period.setTipePeriode(tutupPeriod.getTipePeriode());

                if (bulanBerjalan){
                    if ("12a".equalsIgnoreCase(tutupPeriod.getTipePeriode())){
                        period.setFlagDesemberA("Y");
                    }
                    if ("12b".equalsIgnoreCase(tutupPeriod.getTipePeriode())){
                        period.setFlagDesemberB("Y");
                    }
                }
                updateBatasTutupPeriod(period);
            } catch (GeneralBOException e){
                logger.error("[TutupPeriodAction.createJurnalBalikAkhirTahun] ERROR when create jurnal balik. ", e);
                throw new GeneralBOException("[BillingSystemBoImpl.createJurnalBalikAkhirTahun] ERROR when create jurnal balik. "+e);
            }
        }

        logger.info("[BillingSystemBoImpl.createJurnalBalikAkhirTahun] END <<<");
    }

    private String formatToMM(String bulan){
        Integer intBulan = Integer.valueOf(bulan);
        if (intBulan < 10){
            return "0"+intBulan.toString();
        }
        return bulan;
    }

    private void updateBatasTutupPeriod(TutupPeriod period) throws GeneralBOException{

        Map hsCriteria = new HashMap();
        hsCriteria.put("bulan", period.getBulan());
        hsCriteria.put("tahun", period.getTahun());
        hsCriteria.put("unit", period.getUnit());

        List<ItSimrsBatasTutupPeriodEntity> batasTutupPeriodEntities = batasTutupPeriodDao.getByCriteria(hsCriteria);
        if (batasTutupPeriodEntities.size() > 0){
            ItSimrsBatasTutupPeriodEntity tutupPeriodEntity = batasTutupPeriodEntities.get(0);
            tutupPeriodEntity.setNoJurnalKoreksi(period.getNoJurnal() != null && !"".equalsIgnoreCase(period.getNoJurnal()) ? period.getNoJurnal() : tutupPeriodEntity.getNoJurnalKoreksi());
            tutupPeriodEntity.setFlagDesemberA(period.getFlagDesemberA() != null && !"".equalsIgnoreCase(period.getFlagDesemberA()) ? period.getFlagDesemberA() : tutupPeriodEntity.getFlagDesemberA());
            tutupPeriodEntity.setFlagDesemberB(period.getFlagDesemberB() != null && !"".equalsIgnoreCase(period.getFlagDesemberB()) ? period.getFlagDesemberB() : tutupPeriodEntity.getFlagDesemberB());
            tutupPeriodEntity.setAction("U");
            tutupPeriodEntity.setLastUpdate(period.getLastUpdate());
            tutupPeriodEntity.setLastUpdateWho(period.getLastUpdateWho());
            try {
                batasTutupPeriodDao.updateAndSave(tutupPeriodEntity);
            } catch (GeneralBOException e){
                logger.error("[TutupPeriodAction.updateBatasTutupPeriod] ERROR when update setting batas. ", e);
                throw new GeneralBOException("[BillingSystemBoImpl.updateBatasTutupPeriod] ERROR when update setting batas. "+e);
            }
        }
    }

    private void createSaldoAkhirTahun(TutupPeriod tutupPeriod){
        logger.info("[BillingSystemBoImpl.createSaldoAkhirTahun] START >>>");
        try {
            saveUpdateSaldoAkhirTahun(tutupPeriod);
        } catch (GeneralBOException e){
            logger.error("[TutupPeriodAction.createSaldoAkhirTahun] ERROR when create saldo akhir tahun. ", e);
            throw new GeneralBOException("[BillingSystemBoImpl.createSaldoAkhirTahun] ERROR when create saldo akhir tahun. " +e);
        }
        logger.info("[BillingSystemBoImpl.createSaldoAkhirTahun] END <<<");
    }

    private List<TransaksiStok> getListTransaksiObat(String idPelayanan, Integer tahun, Integer bulan, String idObat) throws GeneralBOException{

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_barang", idObat);
        hsCriteria.put("id_pelayanan", idPelayanan);
        hsCriteria.put("tahun", tahun);
        hsCriteria.put("bulan", bulan);

        List<ItSimrsTransaksiStokEntity> stokEntities = new ArrayList<>();
        try {
            stokEntities = transaksiStokDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[ObatPoliBoImpl.getListReporTransaksiObat] ERROR Search Transaksi Stock .", e);
            throw new GeneralBOException("[ObatPoliBoImpl.getListReporTransaksiObat] ERROR Search Transaksi Stock."+ e);
        }

        BigDecimal nol = new BigDecimal(0);
        BigInteger nolB = new BigInteger(String.valueOf(0));
        List<TransaksiStok> listOfTransaksi = new ArrayList<>();
        if (stokEntities.size() > 0){

            int n = 0;
            TransaksiStok trans;
            String namaObat = "";
            for (ItSimrsTransaksiStokEntity stok : stokEntities){

                // get nama obat
                if ("".equalsIgnoreCase(namaObat)){
                    ImSimrsObatEntity obatEntity = new ImSimrsObatEntity();
                    try {
                        obatEntity = obatDao.getById("idBarang", stok.getIdBarang());
                    } catch (HibernateException e){
                        logger.error("[ObatPoliBoImpl.getListReporTransaksiObat] ERROR get Obat By ID.", e);
                        throw new GeneralBOException("[ObatPoliBoImpl.getListReporTransaksiObat] ERROR get Obat By ID. "+ e);
                    }

                    if (obatEntity != null){
                        namaObat = obatEntity.getNamaObat();
                    }
                }

                if (listOfTransaksi.size() == 0){

                    // saldo bulan lalu tanpa data pendukung
                    if (stok.getQtyLalu() != null && stok.getQtyLalu().compareTo(new BigInteger(String.valueOf(0))) == 1){

                        trans = new TransaksiStok();
                        trans.setNamaObat(namaObat);
                        trans.setQtyLalu(nolB);
                        trans.setTotalLalu(nol);
                        trans.setSubTotalLalu(nol);

                        trans.setQtyLalu(stok.getQtyLalu() == null ? new BigInteger(String.valueOf(0)) : stok.getQtyLalu());
                        trans.setTotalLalu(stok.getTotalLalu() == null ? new BigDecimal(0) : stok.getTotalLalu());
                        trans.setSubTotalLalu(stok.getSubTotalLalu() == null ? new BigDecimal(0) : stok.getSubTotalLalu());
                        listOfTransaksi.add(trans);
                        n++;
                    } else {
                        trans = new TransaksiStok();
                        trans.setNamaObat(namaObat);
                        trans.setQtyLalu(nolB);
                        trans.setTotalLalu(nol);
                        trans.setSubTotalLalu(nol);
                        listOfTransaksi.add(trans);
                        n++;
                    }

                    // data seletelah saldo bulan lalu dengan data pendukung
                    trans = new TransaksiStok();
                    trans.setNamaObat(namaObat);
                    trans.setRegisteredDate(stok.getRegisteredDate());
                    trans.setCreatedDate(stok.getCreatedDate());
                    trans.setKeterangan(stok.getKeterangan());
                    trans.setTipe(stok.getTipe());

                    TransaksiStok minStok = listOfTransaksi.get(n-1);
                    if ("D".equalsIgnoreCase(stok.getTipe())){
                        trans.setQty(stok.getQty() == null ? new BigInteger(String.valueOf(0)) : stok.getQty());
                        trans.setTotal(stok.getTotal() == null ? new BigDecimal(0) : stok.getTotal());
                        trans.setSubTotal(stok.getSubTotal() == null ? new BigDecimal(0) : stok.getSubTotal());

                        // qty saldo = qty masuk + qty lalu;
                        trans.setQtySaldo(minStok.getQtyLalu().add(trans.getQty()));

                        // total saldo = sub total lalu + sub total / qty saldo
                        trans.setTotalSaldo(minStok.getSubTotalLalu().add(stok.getSubTotal()).divide(new BigDecimal(trans.getQtySaldo()), 2, BigDecimal.ROUND_HALF_UP));

                        // sub total saldo = total saldo * qty saldo
//                        trans.setSubTotalSaldo(trans.getTotal().multiply(new BigDecimal(trans.getQtySaldo())));
                        trans.setSubTotalSaldo(trans.getTotalSaldo().multiply(new BigDecimal(trans.getQtySaldo())));
                    } else {

                        trans.setQtyKredit(stok.getQty() == null ? new BigInteger(String.valueOf(0)) : stok.getQty());
                        trans.setTotalKredit(stok.getTotal() == null ? new BigDecimal(0) : stok.getTotal());
                        trans.setSubTotalKredit(stok.getSubTotal() == null ? new BigDecimal(0) : stok.getSubTotal());

                        // qty saldo = qty bulan lalu - qty masuk
                        trans.setQtySaldo((minStok.getQtyLalu() == null ? new BigInteger(String.valueOf(0)) : minStok.getQtyLalu()).subtract(trans.getQty() == null ? new BigInteger(String.valueOf(0)) : trans.getQty()));

                        // total saldo = total lalu
                        trans.setTotalSaldo(stok.getTotalLalu() == null ? new BigDecimal(String.valueOf(0)) : stok.getTotalLalu());

                        // sub total saldo = total saldo * qty saldo
//                        trans.setSubTotalSaldo(trans.getTotal().multiply(new BigDecimal(trans.getQtySaldo())));
                        trans.setSubTotalSaldo(trans.getTotalSaldo().multiply(new BigDecimal(trans.getQtySaldo())));
                    }
                    listOfTransaksi.add(trans);
                    n++;
                } else {

                    // data pendukung
                    trans = new TransaksiStok();
                    trans.setNamaObat(namaObat);
                    trans.setRegisteredDate(stok.getRegisteredDate());
                    trans.setCreatedDate(stok.getCreatedDate());
                    trans.setKeterangan(stok.getKeterangan());
                    trans.setTipe(stok.getTipe());

                    TransaksiStok minStok = listOfTransaksi.get(n-1);

                    if ("D".equalsIgnoreCase(stok.getTipe())){
                        trans.setQty(stok.getQty() == null ? new BigInteger(String.valueOf(0)) : stok.getQty());
                        trans.setTotal(stok.getTotal() == null ? new BigDecimal(0) : stok.getTotal());
                        trans.setSubTotal(stok.getSubTotal() == null ? new BigDecimal(0) : stok.getSubTotal());

                        // qty saldo = qty saldo lalu + qty
                        trans.setQtySaldo(minStok.getQtySaldo().add(trans.getQty()));

                        // total saldo = sub total saldo lalu + sub total / qty saldo
                        trans.setTotalSaldo(minStok.getSubTotalSaldo().add(trans.getSubTotal()).divide(new BigDecimal(trans.getQtySaldo()), 2, BigDecimal.ROUND_HALF_UP));

                        // sub total saldo = sub total saldo
                        trans.setSubTotalSaldo(trans.getTotalSaldo().multiply(new BigDecimal(trans.getQtySaldo())));
                    } else {

                        trans.setQtyKredit(stok.getQty() == null ? new BigInteger(String.valueOf(0)) : stok.getQty());
                        trans.setTotalKredit(stok.getTotal() == null ? new BigDecimal(0) : stok.getTotal());
                        trans.setSubTotalKredit(stok.getSubTotal() == null ? new BigDecimal(0) : stok.getSubTotal());

                        // qty saldo = qty saldo - qty
                        trans.setQtySaldo(minStok.getQtySaldo().subtract(trans.getQtyKredit()));

                        // total saldo = total saldo lalu
                        trans.setTotalSaldo(minStok.getTotalSaldo());

                        // sub total saldo = sub total saldo
                        trans.setSubTotalSaldo(trans.getTotalSaldo().multiply(new BigDecimal(trans.getQtySaldo())));
                    }
                    listOfTransaksi.add(trans);
                    n++;
                }
            }
        }
        return listOfTransaksi;
    }

    public String generateNextIdTransaksiStock( String branchId ){
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        return  "RB"+ branchId + f.format(System.currentTimeMillis()) + transaksiStokDao.getNextSeq();
    }

    private void generateAndSaveCurrentSaldoPersediaanToNextMonth(String branchId, String idObat, Integer bulan, Integer tahun, String idPelayanan, String userLogin, Timestamp times, String idBarang){


        List<TransaksiStok> saldoBulanLaluList = getListTransaksiObat(idPelayanan, tahun, bulan, idObat);
        if (saldoBulanLaluList.size() > 0){
            // ambil data yang terakhir untuk saldo bulan lalu
            TransaksiStok saldoBulanLalu = saldoBulanLaluList.get(saldoBulanLaluList.size() -1);
            if (saldoBulanLalu != null){

                ItSimrsTransaksiStokEntity transaksiStokEntity = new ItSimrsTransaksiStokEntity();
                transaksiStokEntity.setIdTransaksi(generateNextIdTransaksiStock(branchId));
                transaksiStokEntity.setIdObat(idObat);
                transaksiStokEntity.setKeterangan("Saldo Bulan Lalu "+idObat);
                transaksiStokEntity.setTipe("D");
                transaksiStokEntity.setBranchId(branchId);
                transaksiStokEntity.setQty(new BigInteger(String.valueOf(0)));
                transaksiStokEntity.setTotal(new BigDecimal(0));
                transaksiStokEntity.setSubTotal(new BigDecimal(0));
                transaksiStokEntity.setQtyLalu(saldoBulanLalu.getQtySaldo());
                transaksiStokEntity.setTotalLalu(saldoBulanLalu.getTotalSaldo());
                transaksiStokEntity.setSubTotalLalu(saldoBulanLalu.getSubTotalSaldo());
                transaksiStokEntity.setCreatedDate(times);
                transaksiStokEntity.setCreatedWho(userLogin);
                transaksiStokEntity.setLastUpdate(times);
                transaksiStokEntity.setLastUpdateWho(userLogin);
                transaksiStokEntity.setIdBarang(idBarang);
                transaksiStokEntity.setIdPelayanan(idPelayanan);
                transaksiStokEntity.setRegisteredDate(getStDateNextMonth(tahun, bulan));

                try {
                    transaksiStokDao.addAndSave(transaksiStokEntity);
                } catch (HibernateException e){
                    logger.error("[BillingSystemBoImpl.generateAndSaveSaldoCurrentToNextMonth] ERROR .", e);
                    throw new GeneralBOException("[BillingSystemBoImpl.generateAndSaveSaldoCurrentToNextMonth] ERROR .", e);
                }
            }
        }
        if (saldoBulanLaluList.size() == 0) {
            ItSimrsTransaksiStokEntity transaksiStokEntity = new ItSimrsTransaksiStokEntity();
            transaksiStokEntity.setIdTransaksi(generateNextIdTransaksiStock(branchId));
            transaksiStokEntity.setIdObat(idObat);
            transaksiStokEntity.setKeterangan("Saldo Bulan Lalu "+idObat);
            transaksiStokEntity.setTipe("D");
            transaksiStokEntity.setBranchId(branchId);
            transaksiStokEntity.setQty(new BigInteger(String.valueOf(0)));
            transaksiStokEntity.setTotal(new BigDecimal(0));
            transaksiStokEntity.setSubTotal(new BigDecimal(0));
            transaksiStokEntity.setQtyLalu(new BigInteger(String.valueOf(0)));
            transaksiStokEntity.setTotalLalu(new BigDecimal(0));
            transaksiStokEntity.setSubTotalLalu(new BigDecimal(0));
            transaksiStokEntity.setCreatedDate(times);
            transaksiStokEntity.setCreatedWho(userLogin);
            transaksiStokEntity.setLastUpdate(times);
            transaksiStokEntity.setLastUpdateWho(userLogin);
            transaksiStokEntity.setIdBarang(idBarang);
            transaksiStokEntity.setIdPelayanan(idPelayanan);
            transaksiStokEntity.setRegisteredDate(getStDateNextMonth(tahun, bulan));

            try {
                transaksiStokDao.addAndSave(transaksiStokEntity);
            } catch (HibernateException e){
                logger.error("[BillingSystemBoImpl.generateAndSaveSaldoCurrentToNextMonth] ERROR .", e);
                throw new GeneralBOException("[BillingSystemBoImpl.generateAndSaveSaldoCurrentToNextMonth] ERROR .", e);
            }
        }
    }

    private java.sql.Date getStDateNextMonth(Integer tahun, Integer bulan){
        Integer tahunDepan = new Integer(0);
        Integer bulanDepan = new Integer(0);

        if ("12".equalsIgnoreCase(bulan.toString())){
            tahunDepan = tahun + 1;
            bulanDepan = 1;
        } else {
            tahunDepan = tahun;
            bulanDepan = bulan + 1;
        }
        String stDate = tahunDepan+"-"+bulanDepan+"-"+"1";
        return java.sql.Date.valueOf(stDate);
    }

    @Override
    public List<ItJurnalEntity> getJurnalByPengajuanId(String pengajuanId) throws GeneralBOException{
        logger.info("[BillingSystemBoImpl.getJurnalByPengajuanId] START >>>");
        logger.info("[BillingSystemBoImpl.getJurnalByPengajuanId] END <<<");
        return jurnalDao.getListJurnalByPengajuanId(pengajuanId);
    }

    private String getIdDokter(String idTindakan, String keterangan){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
        PeriksaRadiologiBo periksaRadiologiBo = (PeriksaRadiologiBo) ctx.getBean("periksaRadiologiBoProxy");
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");

        String idDokter = "";
        if ("tindakan".equalsIgnoreCase(keterangan)){
            ItSimrsTindakanRawatEntity tindakanRawatEntity = tindakanRawatBo.getTindakanRawatEntityById(idTindakan);
            if (tindakanRawatEntity != null){
                idDokter = tindakanRawatEntity.getIdDokter();
            }
        }
        if ("laboratorium".equalsIgnoreCase(keterangan)){
            ItSimrsPeriksaLabEntity periksaLabEntity = periksaLabBo.getPeriksaLabEntityById(idTindakan);
            if (periksaLabEntity != null){
                idDokter = periksaLabEntity.getIdDokter();
            }
        }
        if ("radiologi".equalsIgnoreCase(keterangan)){
            ItSimrsPeriksaRadiologiEntity periksaRadiologiEntity = periksaRadiologiBo.getEntityPeriksaRadiologi(idTindakan);
            if (periksaRadiologiEntity != null){
                idDokter = periksaRadiologiEntity.getIdDokterRadiologi();
            }
        }
        if ("resep".equalsIgnoreCase(keterangan)){
            ImSimrsPermintaanResepEntity permintaanResepEntity = permintaanResepBo.getEntityPermintaanResepById(idTindakan);
            if (permintaanResepEntity != null){
                idDokter = permintaanResepEntity.getIdDokter();
            }
        }
        return idDokter;
    }

}