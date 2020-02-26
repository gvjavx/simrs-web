package com.neurix.akuntansi.transaksi.billingSystem.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.tipeJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.master.tipeJurnal.model.ImMappingJurnalEntity;
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
    public String createJurnal(String transId, Map data, String branchId, String catatanPembuatanJurnal, String flagRegister,String noJurnal){
        logger.info("[PembayaranUtangPiutangBoImpl.createJurnal] start process >>>");
        String status="success";
        userLogin = CommonUtil.userLogin();
        updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        String tipeJurnalId=null;
        String noNota = null;

        //memberi bukti sumber
        if (data.get("bukti")!=null){
            noNota = (String) data.get("bukti");
        }

        //untuk closing pembayaran catatan diganti
        if (data.get("metode_bayar")!=null){
            String metodeBayar = (String) data.get("metode_bayar");
            // tunai / transfer
            if (("transfer").equalsIgnoreCase(metodeBayar)){
                String bank = (String) data.get("bank");
                catatanPembuatanJurnal="Pembayaran "+metodeBayar+" ke dalam Kas Bank "+bank;

            }
        }

        //mencari tipe jurnal Id
        try {
            tipeJurnalId = mappingJurnalDao.tipeJurnalByTransId(transId);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (tipeJurnalId!=null){
            try {
                // MEMBUAT HEADER JURNAL //
                if (("").equalsIgnoreCase(noJurnal)){
                    try {
                        // Generating ID, get from postgre sequence
                        noJurnal=jurnalDao.getNextJurnalId();
                    } catch (HibernateException e) {
                        logger.error("[PembayaranUtangPiutangBoImpl.createJurnal] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when getting sequence noJurnal , please info to your admin..." + e.getMessage());
                    }
                }

                ItJurnalEntity jurnalEntity = new ItJurnalEntity();
                jurnalEntity.setNoJurnal(noJurnal);
                jurnalEntity.setTipeJurnalId(tipeJurnalId);
                jurnalEntity.setTanggalJurnal(new java.sql.Date(new Date().getTime()));
                jurnalEntity.setMataUangId("032");
                jurnalEntity.setKurs(BigDecimal.valueOf(1));
                jurnalEntity.setKeterangan(catatanPembuatanJurnal);
                jurnalEntity.setSumber(noNota);
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

                // CREATE JURNAL DETAIL PER TRANS
                createJurnalDetail(data,noJurnal,tipeJurnalId,transId);
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
        return status;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////// DETAIL BILLING PER TRANS //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void createJurnalDetail ( Map data , String noJurnal ,String tipeJurnalId,String transId ){
        //MEMBUAT JURNAL DETAIL
        String status;
        List<ImMappingJurnalEntity> mappingJurnal ;
        try {
            mappingJurnal = mappingJurnalDao.getMappingByTipeJurnalIdNTransId(tipeJurnalId,transId);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        String masterId = null;
        String noNota = null;
        if (data.get("master_id")!=null){
            masterId = (String) data.get("master_id");
        }
        if (data.get("bukti")!=null){
            noNota = (String) data.get("bukti");
        }

        //untuk pembayaran utang piutang
        boolean pembayaran = false;
        String rekeningIdPembayaran="";
        String rekeningIdKasPembayaran="";

        if (data.get("rekening_id")!=null){
            rekeningIdPembayaran = (String) data.get("rekening_id");
            rekeningIdKasPembayaran = (String) data.get("rekening_id_kas");
            pembayaran=true;
        }

        //untuk closing pembayaran
        boolean closing = false;
        if (data.get("metode_bayar")!=null){
            String metodeBayar = (String) data.get("metode_bayar");
            // tunai / transfer
            if (("transfer").equalsIgnoreCase(metodeBayar)){
                String bank = (String) data.get("bank");
                String rekeningIdKasClosing;
                try {
                    rekeningIdKasClosing = kodeRekeningDao.searchRekeningIdBankLikeName(bank);
                } catch (HibernateException e) {
                    logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }
                if (rekeningIdKasClosing!=null){
                    rekeningIdKasPembayaran=rekeningIdKasClosing;
                    closing=true;
                }
            }
        }

        if (mappingJurnal.size()!=0){
            //Membuat jurnal berdasarkan mapping kemudian dimasukkan di List
            List<ItJurnalDetailEntity> jurnalDetailEntityList = new ArrayList<>();
            BigDecimal totalBayar= new BigDecimal(0);
            for (ImMappingJurnalEntity mapping : mappingJurnal){
                if (mapping.getKeterangan()!=null){
                    String rekeningId = null;
                    ///////////////////////DIGUNAKAN UNTUK PEMBAYARAN HUTANG PIUTANG //////////////////////////////
                    if (pembayaran){
                        if (!("Y").equalsIgnoreCase(mapping.getMasterId())&&!("Y").equalsIgnoreCase(mapping.getBukti())){
                            rekeningId=rekeningIdKasPembayaran;
                        }else if (("Y").equalsIgnoreCase(mapping.getMasterId())&&("Y").equalsIgnoreCase(mapping.getBukti())){
                            rekeningId=rekeningIdPembayaran;
                        }
                    ///////////////////////DIGUNAKAN UNTUK PEMBAYARAN HUTANG PIUTANG //////////////////////////////
                    }else if (closing){
                        if (!("Y").equalsIgnoreCase(mapping.getMasterId())&&!("Y").equalsIgnoreCase(mapping.getBukti())){
                            rekeningId=rekeningIdKasPembayaran;
                        }
                    }else{
                    /////////////////////JIKA DARI JURNAL OTOMATIS //////////////////////////////////////////////////
                        List<ImKodeRekeningEntity> kodeRekeningEntityList;
                        try {
                            kodeRekeningEntityList = kodeRekeningDao.getIdByCoa(mapping.getKodeRekening());
                        } catch (HibernateException e) {
                            logger.error("[PembayaranUtangPiutangBoImpl.createJurnalDetail] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }
                        for (ImKodeRekeningEntity kodeRekeningEntity : kodeRekeningEntityList){
                            rekeningId = kodeRekeningEntity.getRekeningId();
                        }
                    }

                    if (rekeningId !=null){
                        if (data.get(mapping.getKeterangan())!=null){
                            BigDecimal biayaPembayaran = (BigDecimal) data.get(mapping.getKeterangan());
                            ItJurnalDetailEntity jurnalDetailEntity = new ItJurnalDetailEntity();
                            jurnalDetailEntity.setJurnalDetailId(jurnalDetailDao.getNextJurnalDetailId());
                            jurnalDetailEntity.setNoJurnal(noJurnal);
                            jurnalDetailEntity.setRekeningId(rekeningId);
                            if (("Y").equalsIgnoreCase(mapping.getMasterId())){
                                jurnalDetailEntity.setMasterId(masterId);
                            }
                            if (("Y").equalsIgnoreCase(mapping.getBukti())){
                                jurnalDetailEntity.setNoNota(noNota);
                            }
                            if (("D").equalsIgnoreCase(mapping.getPosisi())){
                                jurnalDetailEntity.setJumlahDebit(biayaPembayaran);
                                jurnalDetailEntity.setJumlahKredit(BigDecimal.ZERO);
                            }else if (("K").equalsIgnoreCase(mapping.getPosisi())){
                                jurnalDetailEntity.setJumlahKredit(biayaPembayaran);
                                jurnalDetailEntity.setJumlahDebit(BigDecimal.ZERO);
                            }
                            jurnalDetailEntity.setBiaya(null);
                            jurnalDetailEntity.setFlag("Y");
                            jurnalDetailEntity.setAction("C");
                            jurnalDetailEntity.setCreatedDate(updateTime);
                            jurnalDetailEntity.setLastUpdate(updateTime);
                            jurnalDetailEntity.setCreatedWho(userLogin);
                            jurnalDetailEntity.setLastUpdateWho(userLogin);

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
                            totalBayar = totalBayar.add(jurnalDetailEntity.getJumlahDebit()).subtract(jurnalDetailEntity.getJumlahKredit());
                            jurnalDetailEntityList.add(jurnalDetailEntity);

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
            if (totalBayar.equals(new BigDecimal(0))){
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
    }
}