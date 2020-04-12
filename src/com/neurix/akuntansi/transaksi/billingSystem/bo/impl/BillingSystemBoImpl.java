package com.neurix.akuntansi.transaksi.billingSystem.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.mappingJurnal.dao.MappingJurnalDao;
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
import com.neurix.akuntansi.transaksi.tutupperiod.dao.BatasTutupPeriodDao;
import com.neurix.akuntansi.transaksi.tutupperiod.model.ItSimrsBatasTutupPeriodEntity;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;


public class BillingSystemBoImpl implements BillingSystemBo {

    protected static transient Logger logger = Logger.getLogger(BillingSystemBoImpl.class);

    private JurnalDao jurnalDao;
    private JurnalDetailDao jurnalDetailDao;
    private JurnalDetailActivityDao jurnalDetailActivityDao;
    private MappingJurnalDao mappingJurnalDao;
    private KodeRekeningDao kodeRekeningDao;
    private String userLogin;
    private Timestamp updateTime;
    private TipeJurnalDao tipeJurnalDao;
    private TransDao transDao;
    private BatasTutupPeriodDao batasTutupPeriodDao;

    public BatasTutupPeriodDao getBatasTutupPeriodDao() {
        return batasTutupPeriodDao;
    }

    public void setBatasTutupPeriodDao(BatasTutupPeriodDao batasTutupPeriodDao) {
        this.batasTutupPeriodDao = batasTutupPeriodDao;
    }

    public JurnalDetailActivityDao getJurnalDetailActivityDao() {
        return jurnalDetailActivityDao;
    }

    public void setJurnalDetailActivityDao(JurnalDetailActivityDao jurnalDetailActivityDao) {
        this.jurnalDetailActivityDao = jurnalDetailActivityDao;
    }

    public TransDao getTransDao() {
        return transDao;
    }

    public void setTransDao(TransDao transDao) {
        this.transDao = transDao;
    }

    public TipeJurnalDao getTipeJurnalDao() {
        return tipeJurnalDao;
    }

    public void setTipeJurnalDao(TipeJurnalDao tipeJurnalDao) {
        this.tipeJurnalDao = tipeJurnalDao;
    }

    public KodeRekeningDao getKodeRekeningDao() {
        return kodeRekeningDao;
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

    public JurnalDao getJurnalDao() {
        return jurnalDao;
    }

    public void setJurnalDao(JurnalDao jurnalDao) {
        this.jurnalDao = jurnalDao;
    }

    public JurnalDetailDao getJurnalDetailDao() {
        return jurnalDetailDao;
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
        boolean periodSudahTutup = false;

        List<ItSimrsBatasTutupPeriodEntity> batasTutupPeriodEntityList = batasTutupPeriodDao.getBatasTutupPeriod(branchId,bulanSekarang,tahunSekarang);
        for (ItSimrsBatasTutupPeriodEntity simrsBatasTutupPeriodEntity : batasTutupPeriodEntityList){
            if (simrsBatasTutupPeriodEntity.getFlagTutup()!=null){
                periodSudahTutup=true;
            }
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

                if (!periodSudahTutup){
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
                    pending.setTanggalJurnal(new java.sql.Date(bulanBerikutnya.getMillis()));
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
    private String createJurnalDetail ( Map data , String noJurnal ,String tipeJurnalId,String transId,boolean periodSudahTutup ){
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

        //untuk pembayaran
        String rekeningIdKas = null;
        if (data.get("metode_bayar")!=null){
            metodeBayar = (String) data.get("metode_bayar");
            // tunai / transfer
            if (("transfer").equalsIgnoreCase(metodeBayar)){
                if (data.get("bank")!=null){
                    bank = (String) data.get("bank");
                }else{
                    status="ERROR : Bank belum dipilih";
                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                }
                if (data.get("nomor_rekening")!=null){
                    nomorRekeningPembayaran = (String) data.get("nomor_rekening");
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
                    if (data.get("master_id")!=null){
                        masterId = (String) data.get("master_id");
                    }
                    if (data.get("pasien_id")!=null){
                        pasienId = (String) data.get("pasien_id");
                    }
                    if (data.get("divisi_id")!=null){
                        divisiId = (String) data.get("divisi_id");
                    }
                    ///////////////////////DIGUNAKAN UNTUK PENGECEKAN KAS  //////////////////////////////
                    if (("kas").equalsIgnoreCase(mapping.getKeterangan())){
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
                        rekeningId=getRekeningForMappingOtomatis(mapping.getKodeRekening());
                    }

                    ///////////////////////DIGUNAKAN UNTUK PENGECEKAN MASTER / PASIEN JIKA BUKAN LIST //////////////////////////////
                    if (("Y").equalsIgnoreCase(mapping.getMasterId())&&("N").equalsIgnoreCase(mapping.getKirimList())){
                        if (masterId==null&&pasienId==null){
                            status="ERROR : Membutuhkan master Id / Pasien Id";
                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                        }
                        if (("Y").equalsIgnoreCase(mapping.getDivisiId())){
                            if (divisiId==null){
                                status="ERROR : Membutuhkan Divisi ID";
                                logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                            }
                        }else{
                            divisiId=null;
                        }
                    }else{
                        masterId=null;
                        pasienId=null;
                    }

                    ///////////////////////DIGUNAKAN UNTUK PENGECEKAN DAN PENGAMBILAN BUKTI DAN NILAI UNTUK JURNAL BUKAN LIST  //////////////////////////////
                    if (rekeningId !=null){
                        if (data.get(mapping.getKeterangan())!=null){
                            String noNota = null;
                            BigDecimal nilai= null;
                            //JIKA YANG DIKIRIM BUKAN LIST
                            if (("N").equalsIgnoreCase(mapping.getKirimList())&&("N").equalsIgnoreCase(mapping.getKodeBarang())){
                                //untuk mendapatkan bukti dan nilai di masing masing parameter mapping
                                if (("Y").equalsIgnoreCase(mapping.getBukti())){
                                    Map listOfMap = (Map) data.get(mapping.getKeterangan());
                                    if (listOfMap.get("bukti")!=null){
                                        noNota = (String) listOfMap.get("bukti");

                                        //untuk mengambil no invoice sebagai kode sumber dari jurnal header
                                        if (("uang_muka").equalsIgnoreCase(mapping.getKeterangan())){
                                            sumber=noNota;
                                        }
                                    }
                                    if (listOfMap.get("nilai")!=null){
                                        nilai=(BigDecimal) listOfMap.get("nilai");
                                    }
                                    if (noNota==null){
                                        status="ERROR : dibutuhkan bukti ( Invoice )";
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
                                }else if (!("Y").equalsIgnoreCase(mapping.getKodeBarang())){
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
                                                for (int x=0;x<activityList.size();x++){
                                                    ItJurnalDetailActivityEntity saveActivity = new ItJurnalDetailActivityEntity();
                                                    if (activityList.get(i).get("activity_id")!=null){
                                                        saveActivity.setActivityId((String)activityList.get(i).get("activity_id"));
                                                    }else{
                                                        status="ERROR : Activity ID tidak ditemukan";
                                                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                                        throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                                    }
                                                    if (activityList.get(i).get("person_id")!=null){
                                                        saveActivity.setPersonId((String)activityList.get(i).get("person_id"));
                                                    }else{
                                                        status="ERROR : Person ID tidak ditemukan";
                                                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                                        throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                                    }
                                                    if (activityList.get(i).get("nilai")!=null){
                                                        saveActivity.setJumlah((BigDecimal)activityList.get(i).get("nilai"));
                                                    }else{
                                                        status="ERROR : nilai tidak ditemukan";
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
                        status="ERROR : tidak bisa menemukan kode rekening";
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
                if (!periodSudahTutup){
                    for (ItJurnalDetailEntity jurnalDetailEntity : jurnalDetailEntityList){
                        /////////////////////// Save data ///////////////////////
                        if (jurnalDetailEntity.getJumlahDebit().compareTo(BigDecimal.ZERO)==0&&jurnalDetailEntity.getJumlahDebit().compareTo(BigDecimal.ZERO)==0){

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
                        if (detailPending.getJumlahDebit().compareTo(BigDecimal.ZERO)==0&&detailPending.getJumlahDebit().compareTo(BigDecimal.ZERO)==0){

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
        return rekeningId;
    }
}