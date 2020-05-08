package com.neurix.akuntansi.transaksi.billingSystem.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.mappingJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.master.master.dao.MasterDao;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.master.master.model.Master;
import com.neurix.akuntansi.master.tipeJurnal.dao.TipeJurnalDao;
import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;
import com.neurix.akuntansi.master.tipeJurnal.model.ImTipeJurnalEntity;
import com.neurix.akuntansi.master.trans.dao.TransDao;
import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDao;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDetailActivityDao;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDetailDao;
import com.neurix.akuntansi.transaksi.jurnal.model.*;
import com.neurix.akuntansi.transaksi.tutupperiod.bo.impl.TutupPeriodBoImpl;
import com.neurix.akuntansi.transaksi.tutupperiod.dao.BatasTutupPeriodDao;
import com.neurix.akuntansi.transaksi.tutupperiod.model.ItSimrsBatasTutupPeriodEntity;
import com.neurix.akuntansi.transaksi.tutupperiod.model.TutupPeriod;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisperiksapasien.dao.AsuransiDao;
import com.neurix.simrs.master.jenisperiksapasien.dao.JenisPeriksaPasienDao;
import com.neurix.simrs.master.jenisperiksapasien.model.ImJenisPeriksaPasienEntity;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.dao.KelasRuanganDao;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.dao.RuanganDao;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.dao.PermintaanResepDao;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.dao.TindakanTransitorisDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsTindakanTransitorisEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.dao.DokterTeamDao;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


public class BillingSystemBoImpl extends TutupPeriodBoImpl implements BillingSystemBo  {

    protected static transient Logger logger = Logger.getLogger(BillingSystemBoImpl.class);

//    private JurnalDao jurnalDao;
//    private JurnalDetailDao jurnalDetailDao;
//    private JurnalDetailActivityDao jurnalDetailActivityDao;
    private MappingJurnalDao mappingJurnalDao;
//    private KodeRekeningDao kodeRekeningDao;
    private String userLogin;
    private Timestamp updateTime;
    private TipeJurnalDao tipeJurnalDao;
    private TransDao transDao;
//    private BatasTutupPeriodDao batasTutupPeriodDao;


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

    public void setTipeJurnalDao(TipeJurnalDao tipeJurnalDao) {
        this.tipeJurnalDao = tipeJurnalDao;
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
    public String createJurnal(String transId, Map data, String branchId, String catatanPembuatanJurnal, String flagRegister){
        logger.info("[PembayaranUtangPiutangBoImpl.createJurnal] start process >>>");
        String noJurnal;
        String status;
        userLogin = CommonUtil.userIdLogin();
        updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String tipeJurnalId;
        String sumber = null;

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

        if (tipeJurnalId!=null){
            try {
                // Generating ID, get from postgre sequence
                noJurnal=jurnalDao.getNextJurnalId();

                // MEMBUAT JURNAL DETAIL TERLEBIH DAHULU UNTUK MENGAMBIL NOMOR INVOICE DARI PEMBAYARAN
                if (("Y").equalsIgnoreCase(transEntity.getFlagSumberBaru())){
                    createJurnalDetail(data,noJurnal,tipeJurnalId,transId,periodSudahTutup);
                    sumber = createInvoiceNumber(tipeJurnalId,branchId);
                }else{
                    sumber = createJurnalDetail(data,noJurnal,tipeJurnalId,transId,periodSudahTutup);
                }

                //MEMBUAT JURNAL HEADER
                ItJurnalEntity jurnalEntity = new ItJurnalEntity();
                jurnalEntity.setNoJurnal(noJurnal);
                jurnalEntity.setTipeJurnalId(tipeJurnalId);
                jurnalEntity.setTanggalJurnal(new java.sql.Date(tanggalSekarang.getTime()));
                jurnalEntity.setMataUangId("032");
                jurnalEntity.setKurs(BigDecimal.valueOf(1));
                jurnalEntity.setKeterangan(catatanPembuatanJurnal);
                jurnalEntity.setSumber(sumber);
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

                if (periodSudahTutup==null){
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
        return noJurnal;
    }

    //////////////////////////////////////// DETAIL BILLING PER TRANS //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String createJurnalDetail ( Map data , String noJurnal ,String tipeJurnalId,String transId,String periodSudahTutup ){
        //MEMBUAT JURNAL DETAIL
        String status;
        String metodeBayar=null;
        String bank=null;
        String masterId = null;
        String pasienId = null;
        String sumber = null;
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
                                    if (("uang_muka").equalsIgnoreCase(mapping.getKeterangan())){
                                        sumber=noNota;
                                    }
                                }
                                if (noNota==null){
                                    status="ERROR : dibutuhkan bukti ( Invoice )";
                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
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
                                            status="ERROR : ada bukti belum di kirim";
                                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
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
            BigDecimal balance = totalDebit.subtract(totalKredit);
            if (balance.compareTo(new BigDecimal(0)) == 0){
                if (periodSudahTutup==null||("Y").equalsIgnoreCase(periodSudahTutup)){
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

    private BigDecimal getJumlahNilaiBiayaByKeterangan(String idDetailCheckup, String jenisPasien, String keterangan){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        BigDecimal nilai = new BigDecimal(0);
        try {
            nilai = checkupDetailBo.getSumJumlahTindakanByJenis(idDetailCheckup, jenisPasien, keterangan);
        } catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR ", e);
            throw new GeneralBOException("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR " + e);

        }
        return nilai;
    }

    public String getDivisiId(String idDetailCheckup, String jenisPasien, String keterangan) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

        String divisiId = "";

        if ("resep".equalsIgnoreCase(keterangan)) {
            ItSimrsRiwayatTindakanEntity riwayatTindakanEntity = getRiwayatTindakanResep(idDetailCheckup, jenisPasien);
            if (riwayatTindakanEntity != null) {
                ImSimrsPermintaanResepEntity permintaanResepEntity = getEntityPermintaanResepById(riwayatTindakanEntity.getIdTindakan());
                if (permintaanResepEntity != null) {
                    ImSimrsPelayananEntity pelayananEntity = getPelayananById(permintaanResepEntity.getTujuanPelayanan());
                    if (pelayananEntity != null) {
                        ImPosition position = getPositionEntityById(pelayananEntity.getDivisiId());
                        if (position != null) {
                            divisiId = position.getKodering();
                        }
                    }
                }
            }
        } else if ("laboratorium".equalsIgnoreCase(keterangan) || "radiologi".equalsIgnoreCase(keterangan)){
            divisiId = periksaLabBo.getDivisiIdKodering(idDetailCheckup, keterangan);
        } else if ("gizi".equalsIgnoreCase(keterangan)){

            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = getEntityDetailCheckupByIdDetail(idDetailCheckup);
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

            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = getDetailCheckupById(idDetailCheckup);
            if (detailCheckupEntity != null && detailCheckupEntity.getIdPelayanan() != null) {
                try {
                    ImSimrsPelayananEntity pelayananEntity = getPelayananById(detailCheckupEntity.getIdPelayanan());

                    // jika poli selain rawat inap maka mengambil kodering dari pelayanan
                    // jika poli rawat rawat inap maka mengambil kodering dari kelas ruangan , Sigit
                    if (pelayananEntity != null && !"rawat_inap".equalsIgnoreCase(pelayananEntity.getTipePelayanan())) {

                        ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                        if (position != null) {
                            divisiId = position.getKodering();
                        }

                    } else {

                        RawatInap lastRuangan = getLastUsedRoom(idDetailCheckup);
                        if (lastRuangan != null) {
                            MtSimrsRuanganEntity ruanganEntity = getEntityRuanganById(lastRuangan.getIdRuang());
                            if (ruanganEntity != null) {
                                ImSimrsKelasRuanganEntity kelasRuanganEntity = getKelasRuanganById(ruanganEntity.getIdKelasRuangan());
                                if (kelasRuanganEntity != null) {
                                    ImPosition position = getPositionEntityById(kelasRuanganEntity.getDivisiId());
                                    if (position != null) {
                                        divisiId = position.getKodering();
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

    private List<Map> getAcitivityList(String idDetailCheckup, String jenisPasien, String ket, String type) {
        logger.info("[CheckupDetailAction.getAcitivityList] START >>>>");

        //** mencari tindakan dan dimasukan ke jurnal detail activity. START **//
        // dokter team

        List<Map> activityList = new ArrayList<>();

        String idDokter = "";
        DokterTeam dokterTeam = new DokterTeam();
        dokterTeam.setIdDetailCheckup(idDetailCheckup);
        List<ItSimrsDokterTeamEntity> dokterTeamEntities = getListEntityTeamDokter(dokterTeam);
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

        List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = getListEntityRiwayatTindakan(riwayatTindakan);
        if (riwayatTindakanEntities.size() > 0) {
            for (ItSimrsRiwayatTindakanEntity riwayatTindakanEntity : riwayatTindakanEntities) {

                // jika selain JRJ
                // maka obat dikenakan PPN
                BigDecimal ppn = new BigDecimal(0);

                // mencari apakah tindakan transitoris
                boolean nonTransitoris = true;
                if ("JRI".equalsIgnoreCase(type)) {
                    ItSimrsTindakanTransitorisEntity transitorisEntity = getTindakanTransitorisById(riwayatTindakanEntity.getIdRiwayatTindakan());
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
                    activityMap.put("person_id", idDokter);
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

            BigDecimal jumlahAllTindakan = getJumlahNilaiBiayaByKeterangan(bean.getIdDetailCheckup(), "", "");


            jenisPasien = jenisPasien + " No. Detail Checkup "+detailCheckupEntity.getIdDetailCheckup();
            transId = "32";

            Map mapPiutang = new HashMap();
            mapPiutang.put("bukti", bukti);
            mapPiutang.put("nilai", jumlahAllTindakan);
            if ("umum".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien()) && "non_tunai".equalsIgnoreCase(detailCheckupEntity.getMetodePembayaran())){
                mapPiutang.put("id_pasien", idPasien);
            } else {
                mapPiutang.put("master_id", masterId);
            }

            List<Map> listOfMapTindakan = new ArrayList<>();
            List<String> listOfKeteranganRiwayat = riwayatTindakanBo.getListKeteranganByIdDetailCheckup(bean.getIdDetailCheckup());
            if (listOfKeteranganRiwayat.size() > 0){

                for (String keterangan : listOfKeteranganRiwayat){
                    Map mapTindakan = new HashMap();
                    mapTindakan.put("master_id", masterId);
                    mapTindakan.put("divisi_id", getDivisiId(bean.getIdDetailCheckup(), idJenisPasien, keterangan));
                    mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(bean.getIdDetailCheckup(), idJenisPasien, keterangan));
                    mapTindakan.put("activity", getAcitivityList(bean.getIdDetailCheckup(), idJenisPasien, keterangan, "JRI"));
                    listOfMapTindakan.add(mapTindakan);
                }
            }


            mapJurnal.put("piutang_transistoris_pasien_rawat_inap", mapPiutang);
            mapJurnal.put("pendapatan_rawat_inap", listOfMapTindakan);
        }
        String catatan = "Transitoris Rawat Inap saat tutup periode "+jenisPasien;

        try {

            String noJurnal = createJurnal(transId, mapJurnal, bean.getUnit(), catatan, "Y");

            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setIdDetailCheckup(bean.getIdDetailCheckup());
            detailCheckup.setTransPeriode(bean.getBulan()+"-"+bean.getTahun());
            detailCheckup.setTransDate(bean.getCreatedDate());
            detailCheckup.setNoJurnalTrans(noJurnal);
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

        // tutup period, sigit
        try {
            saveUpdateTutupPeriod(tutupPeriod);
        } catch (GeneralBOException e){
            logger.error("[TutupPeriodAction.saveTutupPeriod] ERROR when create tutup periode. ", e);
            throw new GeneralBOException("[BillingSystemBoImpl.saveTutupPeriod] ERROR when create tutup periode. "+e);
        }

        logger.info("[BillingSystemBoImpl.saveTutupPeriod] END <<<");
    }
}