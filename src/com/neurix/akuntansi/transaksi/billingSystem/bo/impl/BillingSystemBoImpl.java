package com.neurix.akuntansi.transaksi.billingSystem.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.tipeJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.master.tipeJurnal.dao.TipeJurnalDao;
import com.neurix.akuntansi.master.tipeJurnal.model.ImMappingJurnalEntity;
import com.neurix.akuntansi.master.tipeJurnal.model.ImTipeJurnalEntity;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDao;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDetailDao;
import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalDetailEntity;
import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalEntity;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.akuntansi.transaksi.jurnal.model.JurnalDetail;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.PembayaranUtangPiutangDetail;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;


public class BillingSystemBoImpl implements BillingSystemBo {

    protected static transient Logger logger = Logger.getLogger(BillingSystemBoImpl.class);

    private JurnalDao jurnalDao;
    private JurnalDetailDao jurnalDetailDao;
    private MappingJurnalDao  mappingJurnalDao;
    private KodeRekeningDao kodeRekeningDao;
    private String userLogin;
    private Timestamp updateTime;
    private TipeJurnalDao tipeJurnalDao;

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
        userLogin = CommonUtil.userLogin();
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
        ImTipeJurnalEntity tipeJurnalEntity;
        try {
            tipeJurnalEntity = tipeJurnalDao.getById("tipeJurnalId",tipeJurnalId);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if (tipeJurnalId!=null){
            try {
                // Generating ID, get from postgre sequence
                noJurnal=jurnalDao.getNextJurnalId();

                // MEMBUAT JURNAL DETAIL TERLEBIH DAHULU UNTUK MENGAMBIL NOMOR INVOICE DARI PEMBAYARAN
                if (("Y").equalsIgnoreCase(tipeJurnalEntity.getFlagSumberBaru())){
                    createJurnalDetail(data,noJurnal,tipeJurnalId,transId);
                    sumber = createInvoiceNumber(tipeJurnalId,branchId);
                }else{
                    sumber = createJurnalDetail(data,noJurnal,tipeJurnalId,transId);
                }

                //MEMBUAT JURNAL HEADER
                ItJurnalEntity jurnalEntity = new ItJurnalEntity();
                jurnalEntity.setNoJurnal(noJurnal);
                jurnalEntity.setTipeJurnalId(tipeJurnalId);
                jurnalEntity.setTanggalJurnal(new java.sql.Date(new Date().getTime()));
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
                try {
                    jurnalDao.addAndSave(jurnalEntity);
                } catch (HibernateException e) {
                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnal] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
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
    private String createJurnalDetail ( Map data , String noJurnal ,String tipeJurnalId,String transId ){
        //MEMBUAT JURNAL DETAIL
        String status;
        String metodeBayar=null;
        String bank=null;
        String masterId = null;
        String pasienId = null;
        String sumber = null;
        String nomorRekeningPembayaran =null;

        List<ImMappingJurnalEntity> mappingJurnal ;

        if (data.get("master_id")!=null){
            masterId = (String) data.get("master_id");
        }if (data.get("pasien_id")!=null){
            pasienId = (String) data.get("pasien_id");
        }

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
                }else{
                    status="ERROR : Nomor rekening tidak ada";
                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                }

                try {
                    rekeningIdKas = kodeRekeningDao.searchRekeningIdBankLikeName(bank);
                } catch (HibernateException e) {
                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }
            }else if (("tunai").equalsIgnoreCase(metodeBayar)){
                try {
                    rekeningIdKas = kodeRekeningDao.searchRekeningIdTunaiLikeName("Kas Tunai");
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
            BigDecimal totalDebit= new BigDecimal(0);
            BigDecimal totalKredit= new BigDecimal(0);
            for (ImMappingJurnalEntity mapping : mappingJurnal){
                if (mapping.getKeterangan()!=null){
                    String rekeningId = null;
                    String nomorRekening = null;

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

                    ///////////////////////DIGUNAKAN UNTUK PENGECEKAN MASTER / PASIEN  //////////////////////////////
                    if (("Y").equalsIgnoreCase(mapping.getMasterId())){
                        if (masterId==null&&pasienId==null){
                            status="ERROR : Membutuhkan master Id / Pasien Id";
                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                            throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                        }
                    }

                    ///////////////////////DIGUNAKAN UNTUK PENGECEKAN DAN PENGAMBILAN BUKTI DAN NILAI  //////////////////////////////
                    if (rekeningId !=null){
                        if (data.get(mapping.getKeterangan())!=null){
                            //untuk mendapatkan bukti dan nilai di masing masing parameter mapping
                            String noNota = null;
                            BigDecimal biaya= null;
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
                                    biaya=(BigDecimal) listOfMap.get("nilai");
                                }

                                if (noNota==null){
                                    status="ERROR : dibutuhkan bukti ( Invoice )";
                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                }
                            }else if (!("Y").equalsIgnoreCase(mapping.getKodeBarang())){
                                try {
                                    biaya=(BigDecimal) data.get(mapping.getKeterangan());
                                } catch (GeneralBOException e) {
                                    status="ERROR : ada masalah saat pengambilan biaya";
                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                }
                                // cek biaya pembayaran apakah null
                                if (biaya==null){
                                    status="ERROR : Biaya yang dikirim tidak ada";
                                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail]"+status);
                                    throw new GeneralBOException("Found problem "+status+", please info to your admin...");
                                }
                            }

                            ///////////////////////MEMASUKKAN KE ENTITY  //////////////////////////////
                            ///////// JIKA JURNAL BIASA ( bukan jurnal detail looping )
                            if (!("Y").equalsIgnoreCase(mapping.getKodeBarang())){
                                ItJurnalDetailEntity jurnalDetailEntity = new ItJurnalDetailEntity();
                                jurnalDetailEntity.setNoJurnal(noJurnal);
                                jurnalDetailEntity.setRekeningId(rekeningId);
                                jurnalDetailEntity.setNomorRekening(nomorRekening);
                                jurnalDetailEntity.setMasterId(masterId);
                                jurnalDetailEntity.setPasienId(pasienId);
                                jurnalDetailEntity.setNoNota(noNota);
                                jurnalDetailEntity.setBiaya(null);

                                jurnalDetailEntity.setFlag("Y");
                                jurnalDetailEntity.setAction("C");
                                jurnalDetailEntity.setCreatedDate(updateTime);
                                jurnalDetailEntity.setLastUpdate(updateTime);
                                jurnalDetailEntity.setCreatedWho(userLogin);
                                jurnalDetailEntity.setLastUpdateWho(userLogin);

                                jurnalDetailEntity.setJurnalDetailId(jurnalDetailDao.getNextJurnalDetailId());
                                if (("D").equalsIgnoreCase(mapping.getPosisi())){
                                    jurnalDetailEntity.setJumlahDebit(biaya);
                                    jurnalDetailEntity.setJumlahKredit(BigDecimal.ZERO);
                                }else if (("K").equalsIgnoreCase(mapping.getPosisi())){
                                    jurnalDetailEntity.setJumlahKredit(biaya);
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
                            }else {
                                ///////// JIKA JURNAL BARANG ( jurnal detail yang di looping )
                                if (data.get(mapping.getKeterangan())!=null){
                                    List<Map> mapList = (List<Map>) data.get(mapping.getKeterangan());

                                    for (int i=0;i<mapList.size();i++){
                                        String noKdBarang = null;
                                        BigDecimal biayaPembayaranBarang=null;

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
                for (ItJurnalDetailEntity jurnalDetailEntity : jurnalDetailEntityList){
                    /////////////////////// Save data ///////////////////////
                    try {
                        jurnalDetailDao.addAndSave(jurnalDetailEntity);
                    } catch (HibernateException e) {
                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
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