package com.neurix.akuntansi.transaksi.pengajuanBiaya.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.mappingJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.master.trans.dao.TransDao;
import com.neurix.akuntansi.transaksi.budgeting.dao.BudgetingPengadaanDao;
import com.neurix.akuntansi.transaksi.budgeting.model.ItAkunBudgetingPengadaanEntity;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDao;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDetailDao;
import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalEntity;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.bo.PengajuanBiayaBo;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.dao.PengajuanBiayaDao;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.dao.PengajuanBiayaDetailDao;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.ImPengajuanBiayaEntity;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.ItPengajuanBiayaDetailEntity;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiaya;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiayaDetail;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.user.dao.UserDao;
import com.neurix.authorization.user.model.User;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiDao;
import com.neurix.hris.transaksi.notifikasi.model.ImNotifikasiEntity;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class PengajuanBiayaBoImpl implements PengajuanBiayaBo {

    protected static transient Logger logger = Logger.getLogger(PengajuanBiayaBoImpl.class);
    private PengajuanBiayaDao pengajuanBiayaDao;
    private KodeRekeningDao kodeRekeningDao;
    private JurnalDao jurnalDao;
    private JurnalDetailDao jurnalDetailDao;
    private TransDao transDao;
    private BranchDao branchDao;
    private MappingJurnalDao mappingJurnalDao;
    private UserDao userDao;
    private PengajuanBiayaDetailDao pengajuanBiayaDetailDao;
    private PositionDao positionDao;
    private BudgetingPengadaanDao budgetingPengadaanDao;
    private NotifikasiDao notifikasiDao;

    public NotifikasiDao getNotifikasiDao() {
        return notifikasiDao;
    }

    public void setNotifikasiDao(NotifikasiDao notifikasiDao) {
        this.notifikasiDao = notifikasiDao;
    }

    public BudgetingPengadaanDao getBudgetingPengadaanDao() {
        return budgetingPengadaanDao;
    }

    public void setBudgetingPengadaanDao(BudgetingPengadaanDao budgetingPengadaanDao) {
        this.budgetingPengadaanDao = budgetingPengadaanDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public PengajuanBiayaDetailDao getPengajuanBiayaDetailDao() {
        return pengajuanBiayaDetailDao;
    }

    public void setPengajuanBiayaDetailDao(PengajuanBiayaDetailDao pengajuanBiayaDetailDao) {
        this.pengajuanBiayaDetailDao = pengajuanBiayaDetailDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public MappingJurnalDao getMappingJurnalDao() {
        return mappingJurnalDao;
    }

    public void setMappingJurnalDao(MappingJurnalDao mappingJurnalDao) {
        this.mappingJurnalDao = mappingJurnalDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public TransDao getTransDao() {
        return transDao;
    }

    public void setTransDao(TransDao transDao) {
        this.transDao = transDao;
    }

    public JurnalDetailDao getJurnalDetailDao() {
        return jurnalDetailDao;
    }

    public void setJurnalDetailDao(JurnalDetailDao jurnalDetailDao) {
        this.jurnalDetailDao = jurnalDetailDao;
    }

    public JurnalDao getJurnalDao() {
        return jurnalDao;
    }

    public void setJurnalDao(JurnalDao jurnalDao) {
        this.jurnalDao = jurnalDao;
    }

    public KodeRekeningDao getKodeRekeningDao() {
        return kodeRekeningDao;
    }

    public void setKodeRekeningDao(KodeRekeningDao kodeRekeningDao) {
        this.kodeRekeningDao = kodeRekeningDao;
    }
    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PengajuanBiayaBoImpl.logger = logger;
    }

    public PengajuanBiayaDao getPengajuanBiayaDao() {
        return pengajuanBiayaDao;
    }

    public void setPengajuanBiayaDao(PengajuanBiayaDao pengajuanBiayaDao) {
        this.pengajuanBiayaDao = pengajuanBiayaDao;
    }

    @Override
    public void saveDelete(PengajuanBiaya bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean!=null) {
            ImPengajuanBiayaEntity imPengajuanBiayaEntity = new ImPengajuanBiayaEntity();
            try {
                // Get data from database by ID
                imPengajuanBiayaEntity = pengajuanBiayaDao.getById("pengajuanBiayaId", bean.getPengajuanBiayaId());
            } catch (HibernateException e) {
                logger.error("[PengajuanBiayaBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPengajuanBiayaEntity != null) {
                // Modify from bean to entity serializable
                imPengajuanBiayaEntity.setFlag(bean.getFlag());
                imPengajuanBiayaEntity.setAction(bean.getAction());
                imPengajuanBiayaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPengajuanBiayaEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    pengajuanBiayaDao.updateAndSave(imPengajuanBiayaEntity);
                } catch (HibernateException e) {
                    logger.error("[PengajuanBiayaBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PengajuanBiaya, please info to your admin..." + e.getMessage());
                }

            } else {
                logger.error("[PengajuanBiayaBoImpl.saveDelete] Error, not found data PengajuanBiaya with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PengajuanBiaya with request id, please check again your data ...");

            }
        }
        logger.info("[PengajuanBiayaBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(PengajuanBiaya bean) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.saveEdit] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KodeRekening> kodeRekeningList = (List<KodeRekening>) session.getAttribute("listOfResultKodeRekening");

        if (bean!=null) {
            ImPengajuanBiayaEntity imPengajuanBiayaEntity = null;
            try {
                // Get data from database by ID
                imPengajuanBiayaEntity = pengajuanBiayaDao.getById("pengajuanBiayaId", bean.getPengajuanBiayaId());
            } catch (HibernateException e) {
                logger.error("[PengajuanBiayaBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PengajuanBiaya by Kode PengajuanBiaya, please inform to your admin...," + e.getMessage());
            }
            if (imPengajuanBiayaEntity != null) {
                imPengajuanBiayaEntity.setFlag(bean.getFlag());
                imPengajuanBiayaEntity.setAction(bean.getAction());
                imPengajuanBiayaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPengajuanBiayaEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    pengajuanBiayaDao.updateAndSave(imPengajuanBiayaEntity);
                } catch (HibernateException e) {
                    logger.error("[PengajuanBiayaBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PengajuanBiaya, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PengajuanBiayaBoImpl.saveEdit] Error, not found data PengajuanBiaya with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PengajuanBiaya with request id, please check again your data ...");
            }
        }
        logger.info("[PengajuanBiayaBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PengajuanBiaya saveAdd(PengajuanBiaya bean) throws GeneralBOException {
        return null;
    }

    @Override
    public  List<Notifikasi> saveAddPengajuanBiaya(PengajuanBiaya bean) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.saveAddPengajuanBiaya] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();
        if (bean!=null) {
            String id = pengajuanBiayaDao.getNextPengajuanBiayaId();
            ImPengajuanBiayaEntity pengajuanBiayaEntity = new ImPengajuanBiayaEntity();
            pengajuanBiayaEntity.setPengajuanBiayaId(id);
            pengajuanBiayaEntity.setDivisiId(bean.getDivisiId());
            pengajuanBiayaEntity.setCoaAjuan(bean.getCoaAjuan());
            pengajuanBiayaEntity.setCoaTarget(bean.getCoaTarget());
            pengajuanBiayaEntity.setTotalBiaya(bean.getTotalBiaya());
            pengajuanBiayaEntity.setTanggal(CommonUtil.convertStringToDate2(bean.getStTanggal()));
            pengajuanBiayaEntity.setKeterangan(bean.getKeterangan());
            pengajuanBiayaEntity.setNoJurnal(bean.getNoJurnal());
            pengajuanBiayaEntity.setBranchId(bean.getBranchId());
            pengajuanBiayaEntity.setTransaksi(bean.getTransaksi());
            pengajuanBiayaEntity.setBudgetTerpakai(bean.getBudgetTerpakai());

            pengajuanBiayaEntity.setCreatedDate(bean.getCreatedDate());
            pengajuanBiayaEntity.setLastUpdate(bean.getLastUpdate());
            pengajuanBiayaEntity.setCreatedWho(bean.getCreatedWho());
            pengajuanBiayaEntity.setLastUpdateWho(bean.getLastUpdateWho());
            pengajuanBiayaEntity.setFlag(bean.getFlag());
            pengajuanBiayaEntity.setAction(bean.getAction());
            try {
                // insert into database
                pengajuanBiayaDao.addAndSave(pengajuanBiayaEntity);
            } catch (HibernateException e) {
                logger.error("[PengajuanBiayaBoImpl.saveAddPengajuanBiaya] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }

            List<User> userList = new ArrayList<>();
            switch (bean.getTransaksi()){
                case "SMK":
                    userList=userDao.getUserByBranchAndRole(bean.getBranchId(),CommonConstant.ROLE_ID_ADMIN_AKS);
                    break;
                case "PDU":
                    userList=userDao.getUserByBranchAndRole(bean.getBranchId(),CommonConstant.ROLE_ID_ADMIN_AKS);
                    break;
            }

            for (User user : userList){
                //Send notif ke nip tertentu
                Notifikasi notif= new Notifikasi();
                notif.setNip(user.getUserId());
                notif.setNoRequest(id);
                notif.setTipeNotifId("TN01");
                notif.setTipeNotifName(("Keuangan"));
                notif.setNote(bean.getKeterangan());
                notif.setCreatedWho(bean.getCreatedWho());
                notif.setTo("ditentukan");

                notifikasiList.add(notif);
            }

        }
        logger.info("[PengajuanBiayaBoImpl.saveAddPengajuanBiaya] end process <<<");
        return notifikasiList;
    }


    @Override
    public  List<Notifikasi> saveAddPengajuan(PengajuanBiaya bean, List<PengajuanBiayaDetail> pengajuanBiayaDetailList) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.saveAddPengajuan] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();
        Date tanggalSekarang = new Date(new java.util.Date().getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(tanggalSekarang);
        c.add(Calendar.DATE, -1);
        tanggalSekarang = new Date(c.getTimeInMillis());

        // mengecek dahulu apakah masih mengajukan biaya
        List<ItPengajuanBiayaDetailEntity> validasiMasihMengajukan = pengajuanBiayaDetailDao.getListMasihMengajukan(bean.getBranchId(),bean.getDivisiId());

        if (validasiMasihMengajukan.size()>0){
            String status = "[PengajuanBiayaBoImpl.saveAddPengajuan] ERROR : masih ada pengajuan biaya yang belum di approve";
            logger.error(status);
            throw new GeneralBOException(status);
        }
        if (bean!=null) {
            String id = pengajuanBiayaDao.getNextPengajuanBiayaId();

            //saving detail first
            for (PengajuanBiayaDetail pengajuanBiayaDetail : pengajuanBiayaDetailList){
                String idDetail = pengajuanBiayaDetailDao.getNextPengajuanBiayaDetailId();
                ItPengajuanBiayaDetailEntity detailEntity = new ItPengajuanBiayaDetailEntity();
                detailEntity.setPengajuanBiayaDetailId(idDetail);
                detailEntity.setPengajuanBiayaId(id);
                detailEntity.setBranchId(pengajuanBiayaDetail.getBranchId());
                detailEntity.setDivisiId(pengajuanBiayaDetail.getDivisiId());
                detailEntity.setTanggal(CommonUtil.convertStringToDate2(pengajuanBiayaDetail.getStTanggal()));
                detailEntity.setTransaksi(pengajuanBiayaDetail.getTransaksi());
                detailEntity.setNoBudgeting(pengajuanBiayaDetail.getNoBudgeting());
                detailEntity.setJumlah(pengajuanBiayaDetail.getJumlah());
                detailEntity.setBudgetBiaya(pengajuanBiayaDetail.getBudgetBiaya());
                detailEntity.setBudgetTerpakai(pengajuanBiayaDetail.getBudgetTerpakai());
                detailEntity.setSisaBudget(pengajuanBiayaDetail.getSisaBudget());
                detailEntity.setBudgetBiayaSdBulanIni(pengajuanBiayaDetail.getBudgetBiayaSdBulanIni());
                detailEntity.setBudgetTerpakaiSdBulanIni(pengajuanBiayaDetail.getBudgetTerpakaiSdBulanIni());
                detailEntity.setSisaBudgetSdBulanIni(pengajuanBiayaDetail.getSisaBudgetSdBulanIni());
                detailEntity.setTipePembayaran(pengajuanBiayaDetail.getPembayaran());

                if ("I".equalsIgnoreCase(pengajuanBiayaDetail.getTransaksi())){
                    detailEntity.setKeperluanId(pengajuanBiayaDetail.getKeperluanId());
                    detailEntity.setKeperluan(pengajuanBiayaDetail.getKeperluanName());
                    detailEntity.setNoKontrak(pengajuanBiayaDetail.getNoKontrak());
                    detailEntity.setNamaKontrak(pengajuanBiayaDetail.getNamaKontrak());
                }else{
                    detailEntity.setKeperluan(pengajuanBiayaDetail.getKeperluanName());
                }
                detailEntity.setKeterangan(pengajuanBiayaDetail.getKeterangan());

                detailEntity.setCreatedDate(bean.getCreatedDate());
                detailEntity.setLastUpdate(bean.getLastUpdate());
                detailEntity.setCreatedWho(bean.getCreatedWho());
                detailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                detailEntity.setFlag(bean.getFlag());
                detailEntity.setAction(bean.getAction());

                //validasi tanggal jika kurang dari tanggal ini
                if (detailEntity.getTanggal().before(tanggalSekarang)){
                    String status = "ERROR : tidak bisa mengajukan tanggal kemarin";
                    logger.error("[PengajuanBiayaBoImpl.saveAddPengajuan] Error, " + status);
                    throw new GeneralBOException(status);
                }

                try {
                    // insert into database
                    pengajuanBiayaDetailDao.addAndSave(detailEntity);
                } catch (HibernateException e) {
                    logger.error("[PengajuanBiayaBoImpl.saveAddPengajuan] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                }
            }

            //save entity
            ImPengajuanBiayaEntity pengajuanBiayaEntity = new ImPengajuanBiayaEntity();
            pengajuanBiayaEntity.setPengajuanBiayaId(id);
            pengajuanBiayaEntity.setBranchId(bean.getBranchId());
            pengajuanBiayaEntity.setDivisiId(bean.getDivisiId());
            pengajuanBiayaEntity.setTransaksi("PB");
            pengajuanBiayaEntity.setKeterangan(bean.getKeterangan());
            pengajuanBiayaEntity.setTanggal(CommonUtil.convertStringToDate2(bean.getStTanggal()));
            pengajuanBiayaEntity.setTotalBiaya(bean.getTotalBiaya());
            pengajuanBiayaEntity.setFlagBatal(bean.getFlagBatal());

            pengajuanBiayaEntity.setCreatedDate(bean.getCreatedDate());
            pengajuanBiayaEntity.setLastUpdate(bean.getLastUpdate());
            pengajuanBiayaEntity.setCreatedWho(bean.getCreatedWho());
            pengajuanBiayaEntity.setLastUpdateWho(bean.getLastUpdateWho());
            pengajuanBiayaEntity.setFlag(bean.getFlag());
            pengajuanBiayaEntity.setAction(bean.getAction());

            //validasi tanggal jika kurang dari tanggal ini
            if (pengajuanBiayaEntity.getTanggal().before(tanggalSekarang)){
                String status = "ERROR : tidak bisa mengajukan tanggal kemarin";
                logger.error("[PengajuanBiayaBoImpl.saveAddPengajuan] Error, " + status);
                throw new GeneralBOException(status);
            }

            try {
                // insert into database
                pengajuanBiayaDao.addAndSave(pengajuanBiayaEntity);
            } catch (HibernateException e) {
                logger.error("[PengajuanBiayaBoImpl.saveAddPengajuan] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }

            List<User> userList = new ArrayList<>();

            ImPosition position = positionDao.getById("positionId",bean.getDivisiId());
            String[] koderingPosisi = position.getKodering().split("\\.");

            //jika bukan kanpus
            List<ImPosition> positionList = positionDao.getListPositionKoderingNKelompokPosition(koderingPosisi[0]+"."+koderingPosisi[1]+"%",CommonConstant.KELOMPOK_ID_PEJABAT_MUDA);

            for (ImPosition imPosition : positionList ){
                userList=userDao.getUserByBranchAndPositionAndRole(bean.getBranchId(),imPosition.getPositionId(),CommonConstant.ROLE_ID_KARYAWAN);
            }

            for (User user : userList){
                //Send notif ke nip tertentu
                Notifikasi notif= new Notifikasi();
                notif.setNip(user.getUserId());
                notif.setNoRequest(id);
                notif.setTipeNotifId("TN04");
                notif.setTipeNotifName(("Pengajuan Biaya"));
                notif.setNote(bean.getKeterangan());
                notif.setCreatedWho(bean.getCreatedWho());
                notif.setTo("ditentukan");

                notifikasiList.add(notif);
            }

        }
        logger.info("[PengajuanBiayaBoImpl.saveAddPengajuanBiaya] end process <<<");
        return notifikasiList;
    }

    @Override
    public List<PengajuanBiaya> getByCriteria(PengajuanBiaya searchBean) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PengajuanBiaya> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPengajuanBiayaId() != null && !"".equalsIgnoreCase(searchBean.getPengajuanBiayaId())) {
                hsCriteria.put("pengajuan_biaya_id", searchBean.getPengajuanBiayaId());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getDivisiId() != null && !"".equalsIgnoreCase(searchBean.getDivisiId())) {
                hsCriteria.put("divisi_id", searchBean.getDivisiId());
            }
            if (searchBean.getNoJurnal() != null && !"".equalsIgnoreCase(searchBean.getNoJurnal())) {
                hsCriteria.put("no_jurnal", searchBean.getNoJurnal());
            }
            if (searchBean.getKeterangan() != null && !"".equalsIgnoreCase(searchBean.getKeterangan())) {
                hsCriteria.put("keterangan", searchBean.getKeterangan());
            }
            if (searchBean.getStTanggalDari() != null && !"".equalsIgnoreCase(searchBean.getStTanggalDari())) {
                Timestamp tanggalDari = CommonUtil.convertToTimestamp(searchBean.getStTanggalDari());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTanggalSelesai() != null && !"".equalsIgnoreCase(searchBean.getStTanggalSelesai())) {
                Timestamp tanggalSelesai = CommonUtil.convertToTimestamp(searchBean.getStTanggalSelesai());
                hsCriteria.put("tanggal_selesai", tanggalSelesai);
            }
            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                if ("N".equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchBean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImPengajuanBiayaEntity> imPengajuanBiayaEntity = null;
            try {
                imPengajuanBiayaEntity = pengajuanBiayaDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PengajuanBiayaBoImpl.getSearchPengajuanBiayaByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPengajuanBiayaEntity != null){
                PengajuanBiaya returnPengajuanBiaya;
                // Looping from dao to object and save in collection
                for(ImPengajuanBiayaEntity pengajuanBiayaEntity : imPengajuanBiayaEntity){
                    returnPengajuanBiaya = new PengajuanBiaya();
                    returnPengajuanBiaya.setPengajuanBiayaId(pengajuanBiayaEntity.getPengajuanBiayaId());
                    returnPengajuanBiaya.setDivisiId(pengajuanBiayaEntity.getDivisiId());
                    returnPengajuanBiaya.setCoaAjuan(pengajuanBiayaEntity.getCoaAjuan());
                    returnPengajuanBiaya.setCoaTarget(pengajuanBiayaEntity.getCoaTarget());
                    returnPengajuanBiaya.setTotalBiaya(pengajuanBiayaEntity.getTotalBiaya());
                    if (pengajuanBiayaEntity.getTotalBiaya()!=null){
                        returnPengajuanBiaya.setStTotalBiaya(CommonUtil.numbericFormat(pengajuanBiayaEntity.getTotalBiaya(),"###,###"));
                    }
                    returnPengajuanBiaya.setTanggal(pengajuanBiayaEntity.getTanggal());
                    returnPengajuanBiaya.setStTanggal(CommonUtil.convertDateToString(pengajuanBiayaEntity.getTanggal()));
                    returnPengajuanBiaya.setAprovalId(pengajuanBiayaEntity.getAprovalId());
                    returnPengajuanBiaya.setAprovalDate(pengajuanBiayaEntity.getAprovalDate());
                    returnPengajuanBiaya.setAprovalFlag(pengajuanBiayaEntity.getAprovalFlag());
                    returnPengajuanBiaya.setBudgetSaatIni(pengajuanBiayaEntity.getBudgetSaatIni());
                    returnPengajuanBiaya.setBranchId(pengajuanBiayaEntity.getBranchId());
                    returnPengajuanBiaya.setTransaksi(pengajuanBiayaEntity.getTransaksi());
                    returnPengajuanBiaya.setAprovalName(pengajuanBiayaEntity.getAprovalName());
                    returnPengajuanBiaya.setNoJurnal(pengajuanBiayaEntity.getNoJurnal());
                    returnPengajuanBiaya.setKeterangan(pengajuanBiayaEntity.getKeterangan());
                    returnPengajuanBiaya.setFlagBatal(pengajuanBiayaEntity.getFlagBatal());
                    returnPengajuanBiaya.setKeteranganBatal(pengajuanBiayaEntity.getKeteranganBatal());
                    returnPengajuanBiaya.setDivisiName("");
                    returnPengajuanBiaya.setBranchName("");

                    //melengkapi info
                    ImPosition position = positionDao.getById("positionId",pengajuanBiayaEntity.getDivisiId());
                    if (position!=null){
                        returnPengajuanBiaya.setDivisiName(position.getPositionName());
                    }
                    List<ImBranches> branches = branchDao.getListBranchById(pengajuanBiayaEntity.getBranchId());
                    if (branches.size()!=0){
                        for (ImBranches imBranches : branches){
                            returnPengajuanBiaya.setBranchName(imBranches.getBranchName());
                        }
                    }

                    if ("Y".equalsIgnoreCase(pengajuanBiayaEntity.getAprovalFlag())){
                        returnPengajuanBiaya.setStatusSaatIni("Sudah Close");
                    }else if ("Y".equalsIgnoreCase(pengajuanBiayaEntity.getFlagBatal())){
                        returnPengajuanBiaya.setStatusSaatIni("Dibatalkan");
                    }else{
                        returnPengajuanBiaya.setStatusSaatIni("Belum Close");
                    }

                    returnPengajuanBiaya.setCreatedWho(pengajuanBiayaEntity.getCreatedWho());
                    returnPengajuanBiaya.setLastUpdateWho(pengajuanBiayaEntity.getLastUpdateWho());
                    returnPengajuanBiaya.setCreatedDate(pengajuanBiayaEntity.getCreatedDate());
                    returnPengajuanBiaya.setLastUpdate(pengajuanBiayaEntity.getLastUpdate());
                    returnPengajuanBiaya.setAction(pengajuanBiayaEntity.getAction());
                    returnPengajuanBiaya.setFlag(pengajuanBiayaEntity.getFlag());
                    listOfResult.add(returnPengajuanBiaya);
                }
            }
        }
        logger.info("[PengajuanBiayaBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PengajuanBiaya> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public List<Notifikasi> saveApprove(PengajuanBiaya bean) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.saveApprove] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();

        if (bean!=null) {
            String PengajuanBiayaId = bean.getPengajuanBiayaId();
            ImPengajuanBiayaEntity itPengajuanBiayaEntity = null;
            try {
                // Get data from database by ID
                itPengajuanBiayaEntity = pengajuanBiayaDao.getById("pengajuanBiayaId", PengajuanBiayaId,"Y");
            } catch (HibernateException e) {
                logger.error("[PengajuanBiayaBoImpl.saveApprove] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PengajuanBiaya by Kode PengajuanBiaya, please inform to your admin...," + e.getMessage());
            }

            if (itPengajuanBiayaEntity != null) {
                String userLogin = CommonUtil.userIdLogin();
                itPengajuanBiayaEntity.setPengajuanBiayaId(bean.getPengajuanBiayaId());
                itPengajuanBiayaEntity.setFlag(bean.getFlag());
                //Approve
                itPengajuanBiayaEntity.setAprovalFlag(bean.getAprovalFlag());
                itPengajuanBiayaEntity.setAprovalId(CommonUtil.userIdLogin());
                itPengajuanBiayaEntity.setAprovalName(CommonUtil.userLogin());
                itPengajuanBiayaEntity.setAprovalDate(bean.getAprovalDate());
                itPengajuanBiayaEntity.setAction(bean.getAction());
                itPengajuanBiayaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itPengajuanBiayaEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    pengajuanBiayaDao.updateAndSave(itPengajuanBiayaEntity);
                } catch (HibernateException e) {
                    logger.error("[PengajuanBiayaBoImpl.saveApprove] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PengajuanBiaya, please info to your admin..." + e.getMessage());
                }
            }
        }
        logger.info("[PengajuanBiayaBoImpl.saveApprove] end process <<<");
        return notifikasiList;
    }

    @Override
    public List<PengajuanBiayaDetail> searchPengajuanDetail(String pengajuanId) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.searchPengajuanDetail] start process >>>");

        // Mapping with collection and put
        List<PengajuanBiayaDetail> listOfResult = new ArrayList();

        Map hsCriteria = new HashMap();

        if (pengajuanId != null && !"".equalsIgnoreCase(pengajuanId)) {
            hsCriteria.put("pengajuan_biaya_id", pengajuanId);
        }
        hsCriteria.put("flag", "Y");

        List<ItPengajuanBiayaDetailEntity> itPengajuanBiayaDetailEntityList = null;
        try {
            itPengajuanBiayaDetailEntityList = pengajuanBiayaDetailDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[PengajuanBiayaBoImpl.getSearchPengajuanBiayaByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(itPengajuanBiayaDetailEntityList != null){
            PengajuanBiayaDetail returnPengajuanBiayaDetail;
            // Looping from dao to object and save in collection
            for(ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity : itPengajuanBiayaDetailEntityList){
                returnPengajuanBiayaDetail = new PengajuanBiayaDetail();
                returnPengajuanBiayaDetail.setPengajuanBiayaId(pengajuanBiayaDetailEntity.getPengajuanBiayaId());
                returnPengajuanBiayaDetail.setPengajuanBiayaDetailId(pengajuanBiayaDetailEntity.getPengajuanBiayaDetailId());
                returnPengajuanBiayaDetail.setBranchId(pengajuanBiayaDetailEntity.getBranchId());
                returnPengajuanBiayaDetail.setDivisiId(pengajuanBiayaDetailEntity.getDivisiId());
                returnPengajuanBiayaDetail.setTransaksi(pengajuanBiayaDetailEntity.getTransaksi());
                returnPengajuanBiayaDetail.setNoBudgeting(pengajuanBiayaDetailEntity.getNoBudgeting());
                returnPengajuanBiayaDetail.setKeperluan(pengajuanBiayaDetailEntity.getKeperluan());
                returnPengajuanBiayaDetail.setStatusKeuangan(pengajuanBiayaDetailEntity.getStatusKeuangan());
                returnPengajuanBiayaDetail.setKeterangan(pengajuanBiayaDetailEntity.getKeterangan());
                returnPengajuanBiayaDetail.setStTanggal(CommonUtil.convertDateToString(pengajuanBiayaDetailEntity.getTanggal()));
                returnPengajuanBiayaDetail.setJumlah(pengajuanBiayaDetailEntity.getJumlah());
                returnPengajuanBiayaDetail.setBudgetBiaya(pengajuanBiayaDetailEntity.getBudgetBiaya());
                returnPengajuanBiayaDetail.setBudgetTerpakai(pengajuanBiayaDetailEntity.getBudgetTerpakai());

                returnPengajuanBiayaDetail.setJumlah(pengajuanBiayaDetailEntity.getJumlah());
                returnPengajuanBiayaDetail.setBudgetBiaya(pengajuanBiayaDetailEntity.getBudgetBiaya());
                returnPengajuanBiayaDetail.setBudgetTerpakai(pengajuanBiayaDetailEntity.getBudgetTerpakai());
                returnPengajuanBiayaDetail.setSisaBudget(pengajuanBiayaDetailEntity.getSisaBudget());

                returnPengajuanBiayaDetail.setBudgetBiayaSdBulanIni(pengajuanBiayaDetailEntity.getBudgetBiayaSdBulanIni());
                returnPengajuanBiayaDetail.setBudgetTerpakaiSdBulanIni(pengajuanBiayaDetailEntity.getBudgetTerpakaiSdBulanIni());
                returnPengajuanBiayaDetail.setSisaBudgetSdBulanIni(pengajuanBiayaDetailEntity.getSisaBudgetSdBulanIni());

                returnPengajuanBiayaDetail.setStJumlah(CommonUtil.numbericFormat(pengajuanBiayaDetailEntity.getJumlah(),"###,###"));
                returnPengajuanBiayaDetail.setStBudgetBiaya(CommonUtil.numbericFormat(pengajuanBiayaDetailEntity.getBudgetBiaya(),"###,###"));
                returnPengajuanBiayaDetail.setStBudgetTerpakai(CommonUtil.numbericFormat(pengajuanBiayaDetailEntity.getBudgetTerpakai(),"###,###"));
                returnPengajuanBiayaDetail.setStSisaBudget(CommonUtil.numbericFormat(pengajuanBiayaDetailEntity.getSisaBudget(),"###,###"));

                returnPengajuanBiayaDetail.setStBudgetBiayaSdBulanIni(CommonUtil.numbericFormat(pengajuanBiayaDetailEntity.getBudgetBiayaSdBulanIni(),"###,###"));
                returnPengajuanBiayaDetail.setStBudgetTerpakaiSdBulanIni(CommonUtil.numbericFormat(pengajuanBiayaDetailEntity.getBudgetTerpakaiSdBulanIni(),"###,###"));
                returnPengajuanBiayaDetail.setStSisaBudgetSdBulanIni(CommonUtil.numbericFormat(pengajuanBiayaDetailEntity.getSisaBudgetSdBulanIni(),"###,###"));
                returnPengajuanBiayaDetail.setApprovalKadivFlag(pengajuanBiayaDetailEntity.getApprovalKadivFlag());
                returnPengajuanBiayaDetail.setApprovalKasubdivFlag(pengajuanBiayaDetailEntity.getApprovalKasubdivFlag());
                returnPengajuanBiayaDetail.setApprovalKeuanganFlag(pengajuanBiayaDetailEntity.getApprovalKeuanganFlag());
                returnPengajuanBiayaDetail.setApprovalGmFlag(pengajuanBiayaDetailEntity.getApprovalGmFlag());

                if ("Y".equalsIgnoreCase(pengajuanBiayaDetailEntity.getDiterimaFlag())){
                    returnPengajuanBiayaDetail.setStatusApproval("D");
                }else if ("Y".equalsIgnoreCase(pengajuanBiayaDetailEntity.getApprovalKeuanganKpFlag())){
                    returnPengajuanBiayaDetail.setStatusApproval("TKE");
                }else if ("KP".equalsIgnoreCase(pengajuanBiayaDetailEntity.getStatusKeuangan())&&"Y".equalsIgnoreCase(pengajuanBiayaDetailEntity.getApprovalKeuanganFlag())){
                    returnPengajuanBiayaDetail.setStatusApproval("KEKP");
                }else if ("A".equalsIgnoreCase(pengajuanBiayaDetailEntity.getStatusKeuangan())&&"Y".equalsIgnoreCase(pengajuanBiayaDetailEntity.getApprovalKeuanganFlag())){
                    returnPengajuanBiayaDetail.setStatusApproval("D");
                }else if ("Y".equalsIgnoreCase(pengajuanBiayaDetailEntity.getApprovalGmFlag())){
                    returnPengajuanBiayaDetail.setStatusApproval("KE");
                }else if ("Y".equalsIgnoreCase(pengajuanBiayaDetailEntity.getApprovalKadivFlag())){
                    returnPengajuanBiayaDetail.setStatusApproval("GM");
                }else if ("Y".equalsIgnoreCase(pengajuanBiayaDetailEntity.getApprovalKasubdivFlag())){
                    returnPengajuanBiayaDetail.setStatusApproval("KD");
                }else{
                    returnPengajuanBiayaDetail.setStatusApproval("KS");
                }

                //jika not approve
                if ("N".equalsIgnoreCase(pengajuanBiayaDetailEntity.getApprovalKeuanganKpFlag())){
                    returnPengajuanBiayaDetail.setStatusApproval("NKEKP");
                }else if ("N".equalsIgnoreCase(pengajuanBiayaDetailEntity.getApprovalKeuanganFlag())){
                    returnPengajuanBiayaDetail.setStatusApproval("NKE");
                }else if ("N".equalsIgnoreCase(pengajuanBiayaDetailEntity.getApprovalGmFlag())){
                    returnPengajuanBiayaDetail.setStatusApproval("NGM");
                }else if ("N".equalsIgnoreCase(pengajuanBiayaDetailEntity.getApprovalKadivFlag())){
                    returnPengajuanBiayaDetail.setStatusApproval("NKD");
                }else if ("N".equalsIgnoreCase(pengajuanBiayaDetailEntity.getApprovalKasubdivFlag())){
                    returnPengajuanBiayaDetail.setStatusApproval("NKS");
                }


                String positionId = CommonUtil.userPosisiId();
                String kelompokId="";
                if (positionId!=null){
                    ImPosition imPosition = positionDao.getById("positionId",positionId);
                    kelompokId = imPosition.getKelompokId();
                }

                if (CommonConstant.ROLE_ID_ADMIN_AKS.equalsIgnoreCase(CommonUtil.roleIdAsLogin())&&!CommonConstant.ID_KANPUS.equalsIgnoreCase(CommonUtil.userBranchLogin())&&"Y".equalsIgnoreCase(pengajuanBiayaDetailEntity.getApprovalKeuanganKpFlag())){
                    returnPengajuanBiayaDetail.setStatusUserApproval("TKE");
                }else if (CommonConstant.ROLE_ID_ADMIN_AKS.equalsIgnoreCase(CommonUtil.roleIdAsLogin())&&!CommonConstant.ID_KANPUS.equalsIgnoreCase(CommonUtil.userBranchLogin())){
                    returnPengajuanBiayaDetail.setStatusUserApproval("KE");
                }else if (CommonConstant.ROLE_ID_ADMIN_AKS.equalsIgnoreCase(CommonUtil.roleIdAsLogin())&&CommonConstant.ID_KANPUS.equalsIgnoreCase(CommonUtil.userBranchLogin())){
                    returnPengajuanBiayaDetail.setStatusUserApproval("KEKP");
                }else{
                    if (kelompokId.equalsIgnoreCase(CommonConstant.KELOMPOK_ID_PEJABAT_MUDA)){
                        returnPengajuanBiayaDetail.setStatusUserApproval("KS");
                    }else if (kelompokId.equalsIgnoreCase(CommonConstant.KELOMPOK_ID_PEJABAT_MADYA)){
                        returnPengajuanBiayaDetail.setStatusUserApproval("KD");
                    }else if (kelompokId.equalsIgnoreCase(CommonConstant.KELOMPOK_ID_PEJABAT_UTAMA)){
                        returnPengajuanBiayaDetail.setStatusUserApproval("GM");
                    }
                }

                //untuk set keperluan
                if ("R".equalsIgnoreCase(pengajuanBiayaDetailEntity.getTransaksi())){
                    returnPengajuanBiayaDetail.setKeperluanName(pengajuanBiayaDetailEntity.getKeperluan());
                }else{
                    returnPengajuanBiayaDetail.setKeperluanName(pengajuanBiayaDao.getKeperluanNameBudgetting(pengajuanBiayaDetailEntity.getKeperluanId()));
                }
                returnPengajuanBiayaDetail.setNoKontrak(pengajuanBiayaDetailEntity.getNoKontrak());
                returnPengajuanBiayaDetail.setCreatedWho(pengajuanBiayaDetailEntity.getCreatedWho());
                returnPengajuanBiayaDetail.setLastUpdateWho(pengajuanBiayaDetailEntity.getLastUpdateWho());
                returnPengajuanBiayaDetail.setCreatedDate(pengajuanBiayaDetailEntity.getCreatedDate());
                returnPengajuanBiayaDetail.setLastUpdate(pengajuanBiayaDetailEntity.getLastUpdate());
                returnPengajuanBiayaDetail.setAction(pengajuanBiayaDetailEntity.getAction());
                returnPengajuanBiayaDetail.setFlag(pengajuanBiayaDetailEntity.getFlag());
                listOfResult.add(returnPengajuanBiayaDetail);
            }
        }
        logger.info("[PengajuanBiayaBoImpl.searchPengajuanDetail] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PengajuanBiayaDetail> cariPengajuanBiayaDetail(String pengajuanDetailId) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.searchPengajuanDetail] start process >>>");

        // Mapping with collection and put
        List<PengajuanBiayaDetail> listOfResult = new ArrayList();

//        String divisi="";
//        List<ImPosition> positionList = positionDao.getListPositionKodering(divisiId);
//        for (ImPosition position : positionList ){
//            divisi = position.getPositionId();
//        }

        List<ItPengajuanBiayaDetailEntity> itPengajuanBiayaDetailEntityList = null;
        try {
            itPengajuanBiayaDetailEntityList = pengajuanBiayaDetailDao.getListPengajuanBiayaDetailForKasKeluar(pengajuanDetailId);
        } catch (HibernateException e) {
            logger.error("[PengajuanBiayaBoImpl.getSearchPengajuanBiayaByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(itPengajuanBiayaDetailEntityList != null){
            // Looping from dao to object and save in collection
            for(ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity : itPengajuanBiayaDetailEntityList){
                List<ItJurnalEntity> jurnalEntityList = jurnalDao.getListJurnalByPengajuanId(pengajuanBiayaDetailEntity.getPengajuanBiayaDetailId());
                if (jurnalEntityList.size()==0){
                    listOfResult.add(convertPengajuanBiayaDetail(pengajuanBiayaDetailEntity));
                }
            }
        }
        logger.info("[PengajuanBiayaBoImpl.searchPengajuanDetail] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Notifikasi> saveApproveAtasanPengajuan(PengajuanBiayaDetail bean) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.saveApprove] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();
        ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity = null;
        List<User> userList = new ArrayList<>();

        try {
            // Get data from database by ID
            pengajuanBiayaDetailEntity = pengajuanBiayaDetailDao.getById("pengajuanBiayaDetailId", bean.getPengajuanBiayaDetailId(),"Y");
        } catch (HibernateException e) {
            logger.error("[PengajuanBiayaBoImpl.saveApproveAtasanPengajuan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data PengajuanBiaya by Kode PengajuanBiaya, please inform to your admin...," + e.getMessage());
        }

        if (pengajuanBiayaDetailEntity != null) {
            //Approve
            switch (bean.getStatusApproval()){
                case "KS":
                    pengajuanBiayaDetailEntity.setApprovalKasubdivFlag(bean.getApprovalKasubdivFlag());
                    pengajuanBiayaDetailEntity.setApprovalKasubdivId(bean.getApprovalKasubdivId());
                    pengajuanBiayaDetailEntity.setApprovalKasubdivDate(bean.getApprovalKasubdivDate());

                    ImPosition position = positionDao.getById("positionId",pengajuanBiayaDetailEntity.getDivisiId());
                    String[] koderingPosisi = position.getKodering().split("\\.");
                    List<ImPosition> positionList = positionDao.getListPositionKoderingNKelompokPosition(koderingPosisi[0]+"%",CommonConstant.KELOMPOK_ID_PEJABAT_MADYA);

                    for (ImPosition imPosition : positionList ){
                        userList.addAll(userDao.getUserByBranchAndPositionAndRole(pengajuanBiayaDetailEntity.getBranchId(),imPosition.getPositionId(),CommonConstant.ROLE_ID_KARYAWAN));
                    }
                    break;
                case "KD":
                    pengajuanBiayaDetailEntity.setApprovalKadivFlag(bean.getApprovalKadivFlag());
                    pengajuanBiayaDetailEntity.setApprovalKadivId(bean.getApprovalKadivId());
                    pengajuanBiayaDetailEntity.setApprovalKadivDate(bean.getApprovalKadivDate());

                    List<ImBranches> branchesList = branchDao.getListBranchById(pengajuanBiayaDetailEntity.getBranchId());
                    BigDecimal maxPengajuanBiaya = BigDecimal.ZERO;
                    for (ImBranches branches : branchesList){
                        maxPengajuanBiaya = branches.getMaxPengajuanBiaya();
                    }

                    position = positionDao.getById("positionId",pengajuanBiayaDetailEntity.getDivisiId());
                    koderingPosisi = position.getKodering().split("\\.");
                    positionList = positionDao.getListPositionKoderingNKelompokPosition(koderingPosisi[0]+"%",CommonConstant.KELOMPOK_ID_PEJABAT_UTAMA);

                    if (pengajuanBiayaDetailEntity.getJumlah().compareTo(maxPengajuanBiaya)<0){
                        pengajuanBiayaDetailEntity.setApprovalGmFlag(bean.getApprovalKadivFlag());
                        pengajuanBiayaDetailEntity.setApprovalGmId(bean.getApprovalKadivId());
                        pengajuanBiayaDetailEntity.setApprovalGmDate(bean.getApprovalKadivDate());
                        userList.addAll(userDao.getUserByBranchAndRole(pengajuanBiayaDetailEntity.getBranchId(),CommonConstant.ROLE_ID_ADMIN_AKS));
                    }else{
                        for (ImPosition imPosition : positionList ){
                            userList.addAll(userDao.getUserByBranchAndPositionAndRole(pengajuanBiayaDetailEntity.getBranchId(),imPosition.getPositionId(),CommonConstant.ROLE_ID_KARYAWAN));
                        }
                    }
                    break;
                case "GM":
                    pengajuanBiayaDetailEntity.setApprovalGmFlag(bean.getApprovalGmFlag());
                    pengajuanBiayaDetailEntity.setApprovalGmId(bean.getApprovalGmId());
                    pengajuanBiayaDetailEntity.setApprovalGmDate(bean.getApprovalGmDate());
                    userList.addAll(userDao.getUserByBranchAndRole(pengajuanBiayaDetailEntity.getBranchId(),CommonConstant.ROLE_ID_ADMIN_AKS));
                    break;
                case "KE":
                    pengajuanBiayaDetailEntity.setApprovalKeuanganFlag(bean.getApprovalKeuanganFlag());
                    pengajuanBiayaDetailEntity.setApprovalKeuanganId(bean.getApprovalKeuanganId());
                    pengajuanBiayaDetailEntity.setApprovalKeuanganDate(bean.getApprovalKeuanganDate());
                    pengajuanBiayaDetailEntity.setStatusKeuangan(bean.getStatusKeuangan());
                    pengajuanBiayaDetailEntity.setTanggalRealisasi(bean.getTanggalRealisasi());
                    userList.addAll(userDao.getUserByBranchAndRole(CommonConstant.ID_KANPUS,CommonConstant.ROLE_ID_ADMIN_AKS));
                    break;
            }

            pengajuanBiayaDetailEntity.setJumlah(bean.getJumlah());
            pengajuanBiayaDetailEntity.setAction(bean.getAction());
            pengajuanBiayaDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
            pengajuanBiayaDetailEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // Update into database
                pengajuanBiayaDetailDao.updateAndSave(pengajuanBiayaDetailEntity);
            } catch (HibernateException e) {
                logger.error("[PengajuanBiayaBoImpl.saveApproveAtasanPengajuan] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving update data PengajuanBiaya, please info to your admin..." + e.getMessage());
            }

            //mengedit total biaya
            String pengajuanId = pengajuanBiayaDao.getIdPengajuanByIdPengajuanDetail(bean.getPengajuanBiayaDetailId());
            BigDecimal totalBiaya = BigDecimal.ZERO;
            List<ItPengajuanBiayaDetailEntity> editPengajuanDetailList = pengajuanBiayaDetailDao.getByPengajuanBiayaId(pengajuanId);
            for (ItPengajuanBiayaDetailEntity data : editPengajuanDetailList){
                if (data.getPengajuanBiayaDetailId().equalsIgnoreCase(bean.getPengajuanBiayaDetailId())){
                    totalBiaya = totalBiaya.add(bean.getJumlah());
                }else{
                    totalBiaya = totalBiaya.add(data.getJumlah());
                }
            }

            //menyimpan total biaya
            ImPengajuanBiayaEntity pengajuanBiayaEntity = pengajuanBiayaDao.getById("pengajuanBiayaId",pengajuanId);
            pengajuanBiayaEntity.setTotalBiaya(totalBiaya);
            try {
                // Update into database
                pengajuanBiayaDao.updateAndSave(pengajuanBiayaEntity);
            } catch (HibernateException e) {
                logger.error("[PengajuanBiayaBoImpl.saveApproveAtasanPengajuan] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving update data PengajuanBiaya, please info to your admin..." + e.getMessage());
            }

            for (User user : userList){
                //Send notif ke nip tertentu
                Notifikasi notif= new Notifikasi();
                notif.setNip(user.getUserId());
                notif.setNoRequest(pengajuanBiayaDetailEntity.getPengajuanBiayaId());
                notif.setTipeNotifId("TN04");
                notif.setTipeNotifName(("Pengajuan Biaya"));
                notif.setNote(pengajuanBiayaEntity.getKeterangan());
                notif.setCreatedWho(bean.getLastUpdateWho());
                notif.setTo("ditentukan");

                //mencari dulu apakah notif sudah ada
                List<ImNotifikasiEntity> notifikasiEntityList = notifikasiDao.getDataForCheck(pengajuanBiayaDetailEntity.getPengajuanBiayaId(),user.getUserId());
                if (notifikasiEntityList.size()==0){
                    notifikasiList.add(notif);

                }
            }
        }

        logger.info("[PengajuanBiayaBoImpl.saveApproveAtasanPengajuan] end process <<<");
        return notifikasiList;
    }


    @Override
    public List<Notifikasi> saveApproveKeuanganPengajuan(PengajuanBiayaDetail bean) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.saveApproveKeuanganPengajuan] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();

        ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity = null;
        try {
            // Get data from database by ID
            pengajuanBiayaDetailEntity = pengajuanBiayaDetailDao.getById("pengajuanBiayaDetailId", bean.getPengajuanBiayaDetailId(),"Y");
        } catch (HibernateException e) {
            logger.error("[PengajuanBiayaBoImpl.saveApproveKeuanganPengajuan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data PengajuanBiaya by Kode PengajuanBiaya, please inform to your admin...," + e.getMessage());
        }

        if (pengajuanBiayaDetailEntity != null) {
            //Approve
            switch (bean.getStatusApproval()){
                case "KE":
                    pengajuanBiayaDetailEntity.setApprovalKeuanganFlag(bean.getApprovalKeuanganFlag());
                    pengajuanBiayaDetailEntity.setApprovalKeuanganId(bean.getApprovalKeuanganId());
                    pengajuanBiayaDetailEntity.setApprovalKeuanganDate(bean.getApprovalKeuanganDate());
                    pengajuanBiayaDetailEntity.setStatusKeuangan(bean.getStatusKeuangan());
                    pengajuanBiayaDetailEntity.setTanggalRealisasi(bean.getTanggalRealisasi());
                    pengajuanBiayaDetailEntity.setDiterimaFlag(bean.getDiterimaFlag());
                    pengajuanBiayaDetailEntity.setClosed("Y");
                    pengajuanBiayaDetailEntity.setNoJurnal(bean.getNoJurnal());

                    if ("I".equalsIgnoreCase(pengajuanBiayaDetailEntity.getTransaksi())){
                        ItAkunBudgetingPengadaanEntity budgetingPengadaanEntity = budgetingPengadaanDao.getById("idPengadaan",pengajuanBiayaDetailEntity.getKeperluanId());
                        budgetingPengadaanEntity.setRealisasiKontrak(pengajuanBiayaDetailEntity.getBudgetTerpakai().add(pengajuanBiayaDetailEntity.getJumlah()));
                        budgetingPengadaanEntity.setSisaNilaiKontrak(pengajuanBiayaDetailEntity.getSisaBudget().subtract(pengajuanBiayaDetailEntity.getJumlah()));
                        budgetingPengadaanEntity.setPembayaran(pengajuanBiayaDetailEntity.getTipePembayaran());

                        budgetingPengadaanDao.updateAndSave(budgetingPengadaanEntity);
                    }
                    break;
                case "KEKP":
                    pengajuanBiayaDetailEntity.setApprovalKeuanganKpFlag(bean.getApprovalKeuanganKpFlag());
                    pengajuanBiayaDetailEntity.setApprovalKeuanganKpId(bean.getApprovalKeuanganId());
                    pengajuanBiayaDetailEntity.setApprovalKeuanganKpDate(bean.getApprovalKeuanganKpDate());
                    pengajuanBiayaDetailEntity.setStatusKeuangan(bean.getStatusKeuangan());
                    pengajuanBiayaDetailEntity.setClosed("Y");
                    if ("I".equalsIgnoreCase(pengajuanBiayaDetailEntity.getTransaksi())){
                        ItAkunBudgetingPengadaanEntity budgetingPengadaanEntity = budgetingPengadaanDao.getById("idPengadaan",pengajuanBiayaDetailEntity.getKeperluanId());
                        budgetingPengadaanEntity.setRealisasiKontrak(pengajuanBiayaDetailEntity.getBudgetTerpakai().add(pengajuanBiayaDetailEntity.getJumlah()));
                        budgetingPengadaanEntity.setSisaNilaiKontrak(pengajuanBiayaDetailEntity.getSisaBudget().subtract(pengajuanBiayaDetailEntity.getJumlah()));
                        budgetingPengadaanEntity.setPembayaran(pengajuanBiayaDetailEntity.getTipePembayaran());

                        budgetingPengadaanDao.updateAndSave(budgetingPengadaanEntity);
                    }
                    break;
                case "TKE":
                    pengajuanBiayaDetailEntity.setDiterimaFlag(bean.getDiterimaFlag());
                    pengajuanBiayaDetailEntity.setDiterimaId(bean.getDiterimaId());
                    pengajuanBiayaDetailEntity.setDiterimaDate(bean.getDiterimaDate());
                    pengajuanBiayaDetailEntity.setStatusKeuangan(bean.getStatusKeuangan());
                    pengajuanBiayaDetailEntity.setNoJurnal(bean.getNoJurnal());
                    pengajuanBiayaDetailEntity.setClosed("Y");
                    break;
            }

            pengajuanBiayaDetailEntity.setJumlah(bean.getJumlah());
            pengajuanBiayaDetailEntity.setAction(bean.getAction());
            pengajuanBiayaDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
            pengajuanBiayaDetailEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // Update into database
                pengajuanBiayaDetailDao.updateAndSave(pengajuanBiayaDetailEntity);
            } catch (HibernateException e) {
                logger.error("[PengajuanBiayaBoImpl.saveApproveKeuanganPengajuan] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving update data PengajuanBiaya, please info to your admin..." + e.getMessage());
            }

            //mengedit total biaya
            String pengajuanId = pengajuanBiayaDao.getIdPengajuanByIdPengajuanDetail(bean.getPengajuanBiayaDetailId());
            BigDecimal totalBiaya = BigDecimal.ZERO;
            List<ItPengajuanBiayaDetailEntity> editPengajuanDetailList = pengajuanBiayaDetailDao.getByPengajuanBiayaId(pengajuanId);
            for (ItPengajuanBiayaDetailEntity data : editPengajuanDetailList){
                if (data.getPengajuanBiayaDetailId().equalsIgnoreCase(bean.getPengajuanBiayaDetailId())){
                    totalBiaya = totalBiaya.add(bean.getJumlah());
                }else{
                    totalBiaya = totalBiaya.add(data.getJumlah());
                }
            }

            //menyimpan total biaya
            ImPengajuanBiayaEntity pengajuanBiayaEntity = pengajuanBiayaDao.getById("pengajuanBiayaId",pengajuanId);
            pengajuanBiayaEntity.setTotalBiaya(totalBiaya);
            try {
                // Update into database
                pengajuanBiayaDao.updateAndSave(pengajuanBiayaEntity);
            } catch (HibernateException e) {
                logger.error("[PengajuanBiayaBoImpl.saveApproveAtasanPengajuan] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving update data PengajuanBiaya, please info to your admin..." + e.getMessage());
            }

            if ("A".equalsIgnoreCase(bean.getStatusKeuangan())){
                //untuk mengecek jika sudah tutup semua maka akan di close
                List<ItPengajuanBiayaDetailEntity> pengajuanBiayaDetailEntityList = pengajuanBiayaDetailDao.getByPengajuanBiayaId(pengajuanBiayaDetailEntity.getPengajuanBiayaId());
                int count = 0;
                for (ItPengajuanBiayaDetailEntity data : pengajuanBiayaDetailEntityList){
                    if ("Y".equalsIgnoreCase(data.getClosed())){
                        count++;
                    }
                }
                if (count==pengajuanBiayaDetailEntityList.size()){
                    pengajuanBiayaEntity.setAprovalFlag("Y");
                    pengajuanBiayaEntity.setAprovalId(CommonUtil.userIdLogin());
                    pengajuanBiayaEntity.setAprovalName(CommonUtil.userLogin());
                    pengajuanBiayaEntity.setAprovalDate(new Date(bean.getLastUpdate().getTime()));

                    try {
                        // Update into database
                        pengajuanBiayaDao.updateAndSave(pengajuanBiayaEntity);
                    } catch (HibernateException e) {
                        logger.error("[PengajuanBiayaBoImpl.saveApproveKeuanganPengajuan] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data PengajuanBiaya, please info to your admin..." + e.getMessage());
                    }
                }
            }
        }
        logger.info("[PengajuanBiayaBoImpl.saveApproveKeuanganPengajuan] end process <<<");
        return notifikasiList;
    }

    @Override
    public List<PengajuanBiayaDetail> getDetailPembayaran(String pengajuanBiayaId) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.getDetailPembayaran] start process >>>");
        List<PengajuanBiayaDetail> listOfResult = new ArrayList<>();
        ImPengajuanBiayaEntity pengajuanBiayaEntity = new ImPengajuanBiayaEntity();
        List<ItPengajuanBiayaDetailEntity> pengajuanBiayaDetailEntityList ;
        try {
            pengajuanBiayaDetailEntityList = pengajuanBiayaDetailDao.getByPengajuanBiayaId(pengajuanBiayaId);
            pengajuanBiayaEntity=pengajuanBiayaDao.getById("pengajuanBiayaId",pengajuanBiayaId);
        } catch (HibernateException e) {
            logger.error("[PengajuanBiayaBoImpl.getDetailPembayaran] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if(pengajuanBiayaDetailEntityList != null){
            PengajuanBiayaDetail returnData;
            // Looping from dao to object and save in collection
            for(ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity : pengajuanBiayaDetailEntityList){
                returnData = convertPengajuanBiayaDetail(pengajuanBiayaDetailEntity);
                if ("Y".equalsIgnoreCase(pengajuanBiayaEntity.getFlagBatal())){
                    returnData.setStatusSaatIni("Dibatalkan");
                }
                List<ItJurnalEntity> jurnalEntityList = jurnalDao.getListJurnalByPengajuanId(returnData.getPengajuanBiayaDetailId());
                if (jurnalEntityList.size()>0){
                    returnData.setSudahDibayar(true);
                }
                listOfResult.add(returnData);
            }
        }
        logger.info("[PengajuanBiayaBoImpl.getDetailPembayaran] end process <<<");

        return listOfResult;
    }

    private PengajuanBiayaDetail convertPengajuanBiayaDetail (ItPengajuanBiayaDetailEntity data){
        PengajuanBiayaDetail returnData = new PengajuanBiayaDetail();
        returnData.setPengajuanBiayaId(data.getPengajuanBiayaId());
        returnData.setPengajuanBiayaDetailId(data.getPengajuanBiayaDetailId());
        returnData.setBranchId(data.getBranchId());
        returnData.setDivisiId(data.getDivisiId());
        returnData.setTransaksi(data.getTransaksi());
        returnData.setKeperluan(data.getKeperluan());
        returnData.setKeperluanId(data.getKeperluanId());
        returnData.setKeterangan(data.getKeterangan());

        ImPosition position = positionDao.getById("positionId",data.getDivisiId());
        returnData.setDivisiName(position.getPositionName());
        returnData.setCoaDivisi(position.getKodering());

        List<ImBranches> branchesList = branchDao.getListBranchById(data.getBranchId());
        for (ImBranches branches : branchesList){
            returnData.setBranchName(branches.getBranchName());
        }

        switch (data.getTransaksi()){
            case "I":
                returnData.setTransaksiName("Investasi");
                ItAkunBudgetingPengadaanEntity budgetingPengadaanEntity = budgetingPengadaanDao.getById("idPengadaan",data.getKeperluanId());
                returnData.setKeperluanName(budgetingPengadaanEntity.getNamaKontrak());
                break;
            case "R":
                returnData.setTransaksiName("Rutin");
                returnData.setKeperluanName(data.getKeperluan());

                break;
        }

        returnData.setNoBudgeting(data.getNoBudgeting());

        if (data.getNoBudgeting()!=null){
            String[] coa = data.getNoBudgeting().split("-");
            returnData.setCoa(coa[3]);

            if (coa[3] != null){
                List<ImKodeRekeningEntity> kodeRekeningEntityList = kodeRekeningDao.getIdByCoa(coa[3]);
                for (ImKodeRekeningEntity kodeRekeningEntity : kodeRekeningEntityList ){
                    returnData.setCoaName(kodeRekeningEntity.getNamaKodeRekening());
                }
            }
        }

        returnData.setTanggal(data.getTanggal());
        returnData.setStTanggal(CommonUtil.convertDateToString(data.getTanggal()));
        returnData.setTanggalRealisasi(data.getTanggalRealisasi());
        if (data.getTanggalRealisasi()!=null){
            returnData.setStTanggalRealisasi(CommonUtil.convertDateToString(data.getTanggalRealisasi()));
        }else{
            returnData.setStTanggalRealisasi("");
        }

        returnData.setJumlah(data.getJumlah());
        returnData.setBudgetBiaya(data.getBudgetBiaya());
        returnData.setBudgetTerpakai(data.getBudgetTerpakai());
        returnData.setSisaBudget(data.getSisaBudget());

        returnData.setBudgetBiayaSdBulanIni(data.getBudgetBiayaSdBulanIni());
        returnData.setBudgetTerpakaiSdBulanIni(data.getBudgetTerpakaiSdBulanIni());
        returnData.setSisaBudgetSdBulanIni(data.getSisaBudgetSdBulanIni());

        returnData.setStJumlah(CommonUtil.numbericFormat(data.getJumlah(),"###,###"));
        returnData.setStBudgetBiaya(CommonUtil.numbericFormat(data.getBudgetBiaya(),"###,###"));
        returnData.setStBudgetTerpakai(CommonUtil.numbericFormat(data.getBudgetTerpakai(),"###,###"));
        returnData.setStSisaBudget(CommonUtil.numbericFormat(data.getSisaBudget(),"###,###"));

        returnData.setStBudgetBiayaSdBulanIni(CommonUtil.numbericFormat(data.getBudgetBiayaSdBulanIni(),"###,###"));
        returnData.setStBudgetTerpakaiSdBulanIni(CommonUtil.numbericFormat(data.getBudgetTerpakaiSdBulanIni(),"###,###"));
        returnData.setStSisaBudgetSdBulanIni(CommonUtil.numbericFormat(data.getSisaBudgetSdBulanIni(),"###,###"));

        returnData.setApprovalKasubdivFlag(data.getApprovalKasubdivFlag());
        returnData.setApprovalKasubdivDate(data.getApprovalKasubdivDate());
        returnData.setApprovalKasubdivId(data.getApprovalKasubdivId());

        returnData.setApprovalKadivFlag(data.getApprovalKadivFlag());
        returnData.setApprovalKadivDate(data.getApprovalKadivDate());
        returnData.setApprovalKadivId(data.getApprovalKadivId());

        returnData.setApprovalGmFlag(data.getApprovalGmFlag());
        returnData.setApprovalGmDate(data.getApprovalGmDate());
        returnData.setApprovalGmId(data.getApprovalGmId());

        returnData.setApprovalKeuanganFlag(data.getApprovalKeuanganFlag());
        returnData.setApprovalKeuanganDate(data.getApprovalKeuanganDate());
        returnData.setApprovalKeuanganId(data.getApprovalKeuanganId());

        returnData.setApprovalKeuanganKpFlag(data.getApprovalKeuanganKpFlag());
        returnData.setApprovalKeuanganKpDate(data.getApprovalKeuanganKpDate());
        returnData.setApprovalKeuanganKpId(data.getApprovalKeuanganKpId());

        returnData.setNoKontrak(data.getNoKontrak());
        returnData.setNamaKontrak(data.getNamaKontrak());

        returnData.setFlag(data.getFlag());
        returnData.setAction(data.getAction());

        returnData.setStatusKeuangan(data.getStatusKeuangan());

        returnData.setDiterimaFlag(data.getDiterimaFlag());
        returnData.setDiterimaDate(data.getDiterimaDate());
        returnData.setDiterimaId(data.getDiterimaId());

        if (data.getClosed()!=null){
            returnData.setClosed(data.getClosed());
        }else{
            returnData.setClosed("");
        }
        if (data.getNoJurnal()!=null){
            returnData.setNoJurnal(data.getNoJurnal());
        }else {
            returnData.setNoJurnal("");
        }

        //set status saat ini
        if ("N".equalsIgnoreCase(data.getApprovalKeuanganKpFlag())){
            returnData.setStatusSaatIni("Tidak diapprove oleh Admin Keuangan Kantor Pusat");
        }else if ("N".equalsIgnoreCase(data.getApprovalKeuanganFlag())){
            returnData.setStatusSaatIni("Tidak diapprove oleh Admin Keuangan");
        }else if ("N".equalsIgnoreCase(data.getApprovalGmFlag())) {
            if (!CommonConstant.ID_KANPUS.equalsIgnoreCase(data.getBranchId())) {
                returnData.setStatusSaatIni("Tidak diapprove oleh Kepala Rumah Sakit");
            } else {
                returnData.setStatusSaatIni("Tidak diapprove oleh Direktur Keuangan");
            }
        }else if ("N".equalsIgnoreCase(data.getApprovalKadivFlag())){
            if (!CommonConstant.ID_KANPUS.equalsIgnoreCase(data.getBranchId())){
                returnData.setStatusSaatIni("Tidak diapprove oleh Kepala Divisi");
            }else {
                returnData.setStatusSaatIni("Tidak diapprove oleh Kepala Bidang");
            }
        }else if ("N".equalsIgnoreCase(data.getApprovalKasubdivFlag())){
            if (!CommonConstant.ID_KANPUS.equalsIgnoreCase(data.getBranchId())){
                returnData.setStatusSaatIni("Tidak diapprove oleh Kepala Sub Divisi");
            }else {
                returnData.setStatusSaatIni("Tidak diapprove oleh Kepala Sub Bidang");
            }
        }else {
            if (data.getApprovalKasubdivFlag()==null){
                if (!CommonConstant.ID_KANPUS.equalsIgnoreCase(data.getBranchId())){
                    returnData.setStatusSaatIni("Menunggu approval Kepala Sub Divisi");
                }else {
                    returnData.setStatusSaatIni("Menunggu approval Kepala Sub Bidang");
                }
            }else if (data.getApprovalKadivFlag()==null){
                if (!CommonConstant.ID_KANPUS.equalsIgnoreCase(data.getBranchId())){
                    returnData.setStatusSaatIni("Menunggu approval Kepala Divisi");
                }else {
                    returnData.setStatusSaatIni("Menunggu approval Kepala Bidang");
                }
            }else if (data.getApprovalGmFlag()==null){
                if (!CommonConstant.ID_KANPUS.equalsIgnoreCase(data.getBranchId())){
                    returnData.setStatusSaatIni("Menunggu approval Kepala Rumah Sakit");
                }else {
                    returnData.setStatusSaatIni("Menunggu approval Direktur Keuangan");
                }
            }else if (data.getStatusKeuangan()==null){
                returnData.setStatusSaatIni("Menunggu approval Admin Keuangan");
            }else if ("A".equalsIgnoreCase(data.getStatusKeuangan())){
                returnData.setStatusSaatIni("Sudah diapprove admin keuangan");
                returnData.setCanPrint(true);
            }else if ("KP".equalsIgnoreCase(data.getStatusKeuangan())){
                if (data.getApprovalKeuanganKpFlag()==null){
                    returnData.setStatusSaatIni("Menunggu approval keuangan kantor pusat");
                }else if ("Y".equalsIgnoreCase(data.getApprovalKeuanganKpFlag())){
                    returnData.setStatusSaatIni("Sudah diapprove admin keuangan kantor pusat");
                    returnData.setCanPrint(true);
                }
            }
        }
        return returnData;
    }

    @Override
    public List<Notifikasi> saveNotApprovePengajuanBiaya(PengajuanBiayaDetail bean) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.saveNotApprovePengajuanBiaya] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();
        ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity = null;
        List<User> userList = new ArrayList<>();

        try {
            // Get data from database by ID
            pengajuanBiayaDetailEntity = pengajuanBiayaDetailDao.getById("pengajuanBiayaDetailId", bean.getPengajuanBiayaDetailId());
        } catch (HibernateException e) {
            logger.error("[PengajuanBiayaBoImpl.saveNotApprovePengajuanBiaya] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data PengajuanBiaya by Kode PengajuanBiaya, please inform to your admin...," + e.getMessage());
        }

        if (pengajuanBiayaDetailEntity != null) {
            pengajuanBiayaDetailEntity.setNotApprovalNote(bean.getNotApprovalNote());
            pengajuanBiayaDetailEntity.setClosed("Y");
            //mencari approval yang masih null
            ImPosition position = positionDao.getById("positionId",pengajuanBiayaDetailEntity.getDivisiId());
            String[] koderingPosisi = position.getKodering().split("\\.");

            if (pengajuanBiayaDetailEntity.getApprovalKasubdivFlag()==null){
                pengajuanBiayaDetailEntity.setApprovalKasubdivFlag("N");
                pengajuanBiayaDetailEntity.setApprovalKasubdivDate(bean.getLastUpdate());
                pengajuanBiayaDetailEntity.setApprovalKasubdivId(bean.getLastUpdateWho());

                //ke admin divisi
                userList.addAll(userDao.getUserByBranchAndPositionAndRole(bean.getBranchId(),pengajuanBiayaDetailEntity.getDivisiId(),CommonConstant.ROLE_ID_ADMIN_DIVISI));

            }else if (pengajuanBiayaDetailEntity.getApprovalKadivFlag()==null){
                pengajuanBiayaDetailEntity.setApprovalKadivFlag("N");
                pengajuanBiayaDetailEntity.setApprovalKadivDate(bean.getLastUpdate());
                pengajuanBiayaDetailEntity.setApprovalKadivId(bean.getLastUpdateWho());

                //ke admin divisi
                userList.addAll(userDao.getUserByBranchAndPositionAndRole(bean.getBranchId(),pengajuanBiayaDetailEntity.getDivisiId(),CommonConstant.ROLE_ID_ADMIN_DIVISI));

                //ke kasubdiv / penyelia
                List<ImPosition> positionList = positionDao.getListPositionKoderingNKelompokPosition(koderingPosisi[0]+"."+koderingPosisi[1]+"%",CommonConstant.KELOMPOK_ID_PEJABAT_MUDA);

                for (ImPosition imPosition : positionList ){
                    userList.addAll(userDao.getUserByBranchAndPositionAndRole(pengajuanBiayaDetailEntity.getBranchId(),imPosition.getPositionId(),CommonConstant.ROLE_ID_KARYAWAN));
                }

            }else if (pengajuanBiayaDetailEntity.getApprovalGmFlag()==null){
                pengajuanBiayaDetailEntity.setApprovalGmFlag("N");
                pengajuanBiayaDetailEntity.setApprovalGmDate(bean.getLastUpdate());
                pengajuanBiayaDetailEntity.setApprovalGmId(bean.getLastUpdateWho());

                //ke admin divisi
                userList.addAll(userDao.getUserByBranchAndPositionAndRole(bean.getBranchId(),pengajuanBiayaDetailEntity.getDivisiId(),CommonConstant.ROLE_ID_ADMIN_DIVISI));

                //ke kasubdiv / penyelia
                List<ImPosition> positionList = positionDao.getListPositionKoderingNKelompokPosition(koderingPosisi[0]+"."+koderingPosisi[1]+"%",CommonConstant.KELOMPOK_ID_PEJABAT_MUDA);

                for (ImPosition imPosition : positionList ){
                    userList.addAll(userDao.getUserByBranchAndPositionAndRole(pengajuanBiayaDetailEntity.getBranchId(),imPosition.getPositionId(),CommonConstant.ROLE_ID_KARYAWAN));
                }

                //ke kadiv / kasubbid
                positionList = positionDao.getListPositionKoderingNKelompokPosition(koderingPosisi[0]+"%",CommonConstant.KELOMPOK_ID_PEJABAT_MADYA);

                for (ImPosition imPosition : positionList ){
                    userList.addAll(userDao.getUserByBranchAndPositionAndRole(pengajuanBiayaDetailEntity.getBranchId(),imPosition.getPositionId(),CommonConstant.ROLE_ID_KARYAWAN));
                }

            }else if (pengajuanBiayaDetailEntity.getApprovalKeuanganFlag()==null){
                pengajuanBiayaDetailEntity.setApprovalKeuanganFlag("N");
                pengajuanBiayaDetailEntity.setApprovalKeuanganDate(bean.getLastUpdate());
                pengajuanBiayaDetailEntity.setApprovalKeuanganId(bean.getLastUpdateWho());

                //ke admin divisi
                userList.addAll(userDao.getUserByBranchAndPositionAndRole(bean.getBranchId(),pengajuanBiayaDetailEntity.getDivisiId(),CommonConstant.ROLE_ID_ADMIN_DIVISI));

                //ke kasubdiv / penyelia
                List<ImPosition> positionList = positionDao.getListPositionKoderingNKelompokPosition(koderingPosisi[0]+"."+koderingPosisi[1]+"%",CommonConstant.KELOMPOK_ID_PEJABAT_MUDA);

                for (ImPosition imPosition : positionList ){
                    userList.addAll(userDao.getUserByBranchAndPositionAndRole(pengajuanBiayaDetailEntity.getBranchId(),imPosition.getPositionId(),CommonConstant.ROLE_ID_KARYAWAN));
                }

                //ke kadiv / kasubbid
                positionList = positionDao.getListPositionKoderingNKelompokPosition(koderingPosisi[0]+"%",CommonConstant.KELOMPOK_ID_PEJABAT_MADYA);

                for (ImPosition imPosition : positionList ){
                    userList.addAll(userDao.getUserByBranchAndPositionAndRole(pengajuanBiayaDetailEntity.getBranchId(),imPosition.getPositionId(),CommonConstant.ROLE_ID_KARYAWAN));
                }

                // ke GM / kabid
                positionList = positionDao.getListPositionKoderingNKelompokPosition(koderingPosisi[0]+"%",CommonConstant.KELOMPOK_ID_PEJABAT_UTAMA);

                for (ImPosition imPosition : positionList ){
                    userList.addAll(userDao.getUserByBranchAndPositionAndRole(pengajuanBiayaDetailEntity.getBranchId(),imPosition.getPositionId(),CommonConstant.ROLE_ID_KARYAWAN));
                }

            }else if (pengajuanBiayaDetailEntity.getApprovalKeuanganKpFlag()==null){
                pengajuanBiayaDetailEntity.setApprovalKeuanganKpFlag("N");
                pengajuanBiayaDetailEntity.setApprovalKeuanganKpDate(bean.getLastUpdate());
                pengajuanBiayaDetailEntity.setApprovalKeuanganKpId(bean.getLastUpdateWho());

                //ke admin divisi
                userList.addAll(userDao.getUserByBranchAndPositionAndRole(pengajuanBiayaDetailEntity.getBranchId(),pengajuanBiayaDetailEntity.getDivisiId(),CommonConstant.ROLE_ID_ADMIN_DIVISI));

                //ke kasubdiv / penyelia
                List<ImPosition> positionList = positionDao.getListPositionKoderingNKelompokPosition(koderingPosisi[0]+"."+koderingPosisi[1]+"%",CommonConstant.KELOMPOK_ID_PEJABAT_MUDA);

                for (ImPosition imPosition : positionList ){
                    userList.addAll(userDao.getUserByBranchAndPositionAndRole(pengajuanBiayaDetailEntity.getBranchId(),imPosition.getPositionId(),CommonConstant.ROLE_ID_KARYAWAN));
                }

                //ke kadiv / kasubbid
                positionList = positionDao.getListPositionKoderingNKelompokPosition(koderingPosisi[0]+"%",CommonConstant.KELOMPOK_ID_PEJABAT_MADYA);

                for (ImPosition imPosition : positionList ){
                    userList.addAll(userDao.getUserByBranchAndPositionAndRole(pengajuanBiayaDetailEntity.getBranchId(),imPosition.getPositionId(),CommonConstant.ROLE_ID_KARYAWAN));
                }

                // ke GM / kabid
                positionList = positionDao.getListPositionKoderingNKelompokPosition(koderingPosisi[0]+"%",CommonConstant.KELOMPOK_ID_PEJABAT_UTAMA);

                for (ImPosition imPosition : positionList ){
                    userList.addAll(userDao.getUserByBranchAndPositionAndRole(pengajuanBiayaDetailEntity.getBranchId(),imPosition.getPositionId(),CommonConstant.ROLE_ID_KARYAWAN));
                }

                // ke keuangan unit
                userList.addAll(userDao.getUserByBranchAndRole(pengajuanBiayaDetailEntity.getBranchId(),CommonConstant.ROLE_ID_ADMIN_AKS));
            }

            try {
                // Update into database
                pengajuanBiayaDetailDao.updateAndSave(pengajuanBiayaDetailEntity);
            } catch (HibernateException e) {
                logger.error("[PengajuanBiayaBoImpl.saveNotApprovePengajuanBiaya] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving update data PengajuanBiaya, please info to your admin..." + e.getMessage());
            }

            for (User user : userList){
                //Send notif ke nip tertentu
                Notifikasi notif= new Notifikasi();
                notif.setNip(user.getUserId());
                notif.setNoRequest(pengajuanBiayaDetailEntity.getPengajuanBiayaId());
                notif.setTipeNotifId("umum");
                notif.setTipeNotifName(("Pengajuan Biaya"));
                notif.setNote("Pengajuan Biaya dengan ID "+pengajuanBiayaDetailEntity.getPengajuanBiayaDetailId()+" tidak disetujui dikarenakan "+pengajuanBiayaDetailEntity.getNotApprovalNote());
                notif.setCreatedWho(bean.getLastUpdateWho());
                notif.setTo("ditentukan");

                notifikasiList.add(notif);
            }

            //untuk mengecek jika sudah tutup semua maka akan di close
            List<ItPengajuanBiayaDetailEntity> pengajuanBiayaDetailEntityList = pengajuanBiayaDetailDao.getByPengajuanBiayaId(pengajuanBiayaDetailEntity.getPengajuanBiayaId());
            int count = 0;
            for (ItPengajuanBiayaDetailEntity data : pengajuanBiayaDetailEntityList){
                if ("Y".equalsIgnoreCase(data.getClosed())){
                    count++;
                }
            }
            if (count==pengajuanBiayaDetailEntityList.size()){
                ImPengajuanBiayaEntity pengajuanBiayaEntity = pengajuanBiayaDao.getById("pengajuanBiayaId",pengajuanBiayaDetailEntity.getPengajuanBiayaId());
                pengajuanBiayaEntity.setAprovalFlag("Y");
                pengajuanBiayaEntity.setAprovalId(CommonUtil.userIdLogin());
                pengajuanBiayaEntity.setAprovalName(CommonUtil.userLogin());
                pengajuanBiayaEntity.setAprovalDate(new Date(bean.getLastUpdate().getTime()));

                try {
                    // Update into database
                    pengajuanBiayaDao.updateAndSave(pengajuanBiayaEntity);
                } catch (HibernateException e) {
                    logger.error("[PengajuanBiayaBoImpl.saveNotApprovePengajuanBiaya] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PengajuanBiaya, please info to your admin..." + e.getMessage());
                }
            }
        }

        logger.info("[PengajuanBiayaBoImpl.saveNotApprovePengajuanBiaya] end process <<<");
        return notifikasiList;
    }

    @Override
    public PengajuanBiaya getPengajuanBiayaForRk(String pengajuanId, String status) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.getPengajuanBiayaForRk] start process >>>");
        ImPengajuanBiayaEntity itPengajuanBiayaEntity = null;
        PengajuanBiaya result = new PengajuanBiaya();
        try {
            // Get data from database by ID
            itPengajuanBiayaEntity = pengajuanBiayaDao.getById("pengajuanBiayaId", pengajuanId);
        } catch (HibernateException e) {
            logger.error("[PengajuanBiayaBoImpl.getPengajuanBiayaForRk] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data PengajuanBiaya by Kode PengajuanBiaya, please inform to your admin...," + e.getMessage());
        }

        if (itPengajuanBiayaEntity != null) {
            result.setCoaTarget(itPengajuanBiayaEntity.getCoaTarget());

            List<ImKodeRekeningEntity> kodeRekeningEntityList = kodeRekeningDao.getIdByCoa(itPengajuanBiayaEntity.getCoaTarget());
            for (ImKodeRekeningEntity kodeRekeningEntity : kodeRekeningEntityList){
                result.setNamaCoa(kodeRekeningEntity.getNamaKodeRekening());
            }

            result.setTotalBiaya(itPengajuanBiayaEntity.getTotalBiaya());

            List<ItPengajuanBiayaDetailEntity> pengajuanBiayaDetailEntityList = pengajuanBiayaDetailDao.getDetailPengajuanForRk(pengajuanId);
            String keteranganId="";
            for (ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity : pengajuanBiayaDetailEntityList){
                keteranganId = keteranganId +" "+ pengajuanBiayaDetailEntity.getPengajuanBiayaDetailId()+ " = "+CommonUtil.numbericFormat(pengajuanBiayaDetailEntity.getJumlah(),"###,###")+",";
            }
            String unit = "";
            String divisi = "";
            List<ImBranches> branchesList = branchDao.getListBranchById(itPengajuanBiayaEntity.getBranchId());
            for (ImBranches branches : branchesList){
                unit =branches.getBranchName();
            }
            ImPosition position = positionDao.getById("positionId",itPengajuanBiayaEntity.getDivisiId());
            divisi = position.getPositionName();

            if ("K".equalsIgnoreCase(status)){
                keteranganId = "Pengiriman terhadap RK untuk pengajuan biaya pada unit "+unit+" divisi "+divisi+" dengan pengajuan ID :"+keteranganId +" pada tanggal "+CommonUtil.convertDateToString(itPengajuanBiayaEntity.getTanggal());
            }else{
                keteranganId = "Penerimaan terhadap RK untuk pengajuan biaya dari Kantor Pusat untuk divisi "+divisi+" dengan pengajuan ID :"+keteranganId +" pada tanggal "+CommonUtil.convertDateToString(itPengajuanBiayaEntity.getTanggal());
            }

            result.setKeterangan(keteranganId);
        }
        logger.info("[PengajuanBiayaBoImpl.getPengajuanBiayaForRk] end process <<<");
        return result;
    }

    @Override
    public void cekApakahBisaDiClose(String pengajuanId) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.cekApakahBisaDiClose] start process >>>");
        //untuk mengecek jika sudah tutup semua maka akan di close
        List<ItPengajuanBiayaDetailEntity> pengajuanBiayaDetailEntityList = pengajuanBiayaDetailDao.getByPengajuanBiayaId(pengajuanId);
        int count = 0;
        for (ItPengajuanBiayaDetailEntity data : pengajuanBiayaDetailEntityList){
            if ("Y".equalsIgnoreCase(data.getClosed())){
                count++;
            }
        }
        if (count==pengajuanBiayaDetailEntityList.size()){
            ImPengajuanBiayaEntity pengajuanBiayaEntity = pengajuanBiayaDao.getById("pengajuanBiayaId",pengajuanId);
            pengajuanBiayaEntity.setAprovalFlag("Y");
            pengajuanBiayaEntity.setAprovalId(CommonUtil.userIdLogin());
            pengajuanBiayaEntity.setAprovalName(CommonUtil.userLogin());
            java.util.Date date = new java.util.Date();
            pengajuanBiayaEntity.setAprovalDate(new Date(date.getTime()));

            try {
                // Update into database
                pengajuanBiayaDao.updateAndSave(pengajuanBiayaEntity);
            } catch (HibernateException e) {
                logger.error("[PengajuanBiayaBoImpl.saveNotApprovePengajuanBiaya] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving update data PengajuanBiaya, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[PengajuanBiayaBoImpl.cekApakahBisaDiClose] end process <<<");
    }

    @Override
    public void setRkSudahDikirim(String pengajuanId,String coa) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.setRkSudahDikirim] start process >>>");
        try {
            ImPengajuanBiayaEntity pengajuanBiayaEntity = pengajuanBiayaDao.getById("pengajuanBiayaId",pengajuanId);
            pengajuanBiayaEntity.setRkDikirim("Y");
            pengajuanBiayaEntity.setCoaTarget(coa);

            // Update into database
            pengajuanBiayaDao.updateAndSave(pengajuanBiayaEntity);
        } catch (HibernateException e) {
            logger.error("[PengajuanBiayaBoImpl.setRkSudahDikirim] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving update data PengajuanBiaya, please info to your admin..." + e.getMessage());
        }

        logger.info("[PengajuanBiayaBoImpl.setRkSudahDikirim] end process <<<");
    }

    @Override
    public PengajuanBiaya cekApakahBolehRk(String pengajuanId) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.setRkSudahDikirim] start process >>>");
        PengajuanBiaya pengajuanBiaya = new PengajuanBiaya();
        ImPengajuanBiayaEntity pengajuanBiayaEntity = pengajuanBiayaDao.getById("pengajuanBiayaId",pengajuanId);

        if (!CommonConstant.ID_KANPUS.equalsIgnoreCase(CommonUtil.userBranchLogin())){
            if ("Y".equalsIgnoreCase(pengajuanBiayaEntity.getRkDikirim())){
                pengajuanBiaya.setShowTerimaRk(true);
            }
        }

        List<ItPengajuanBiayaDetailEntity> pengajuanBiayaDetailEntityList = pengajuanBiayaDetailDao.getByPengajuanBiayaId(pengajuanId);
        for (ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity : pengajuanBiayaDetailEntityList){
            if(!"Y".equalsIgnoreCase(pengajuanBiayaDetailEntity.getClosed())||!CommonUtil.userBranchLogin().equalsIgnoreCase(CommonConstant.ID_KANPUS)||!CommonConstant.ROLE_ID_ADMIN_AKS.equalsIgnoreCase(CommonUtil.roleIdAsLogin())||"Y".equalsIgnoreCase(pengajuanBiayaEntity.getRkDikirim())){
                pengajuanBiaya.setShowBuatRk(false);
                break;
            }
        }
        logger.info("[PengajuanBiayaBoImpl.setRkSudahDikirim] end process <<<");
        return pengajuanBiaya;
    }

    @Override
    public String batalkanPengajuanBiaya(PengajuanBiaya bean) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.batalkanPengajuanBiaya] start process >>>");
        String response ="";
        List<ItPengajuanBiayaDetailEntity> pengajuanBiayaDetailEntityList = pengajuanBiayaDetailDao.getByPengajuanBiayaId(bean.getPengajuanBiayaId());

        //cek dulu apakah ada yang sudah di approve
        for (ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity : pengajuanBiayaDetailEntityList){
            if (pengajuanBiayaDetailEntity.getApprovalKasubdivFlag()!=null){
                response="Tidak bisa dibatalkan karena sudah ada data yang diapprove oleh atasan";
            }
        }
        if ("".equalsIgnoreCase(response)){
            String PengajuanBiayaId = bean.getPengajuanBiayaId();
            ImPengajuanBiayaEntity itPengajuanBiayaEntity = null;
            try {
                // Get data from database by ID
                itPengajuanBiayaEntity = pengajuanBiayaDao.getById("pengajuanBiayaId", PengajuanBiayaId,"Y");
            } catch (HibernateException e) {
                logger.error("[PengajuanBiayaBoImpl.batalkanPengajuanBiaya] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PengajuanBiaya by Kode PengajuanBiaya, please inform to your admin...," + e.getMessage());
            }

            if (itPengajuanBiayaEntity != null) {
                itPengajuanBiayaEntity.setPengajuanBiayaId(bean.getPengajuanBiayaId());
                itPengajuanBiayaEntity.setFlagBatal(bean.getFlagBatal());
                itPengajuanBiayaEntity.setKeteranganBatal(bean.getKeteranganBatal());

                itPengajuanBiayaEntity.setAction(bean.getAction());
                itPengajuanBiayaEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itPengajuanBiayaEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    pengajuanBiayaDao.updateAndSave(itPengajuanBiayaEntity);
                } catch (HibernateException e) {
                    logger.error("[PengajuanBiayaBoImpl.batalkanPengajuanBiaya] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PengajuanBiaya, please info to your admin..." + e.getMessage());
                }

                for (ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity : pengajuanBiayaDetailEntityList){
                    pengajuanBiayaDetailEntity.setClosed("Y");
                    pengajuanBiayaDetailDao.updateAndSave(pengajuanBiayaDetailEntity);
                }

                List<ImNotifikasiEntity> notifikasiEntityList = notifikasiDao.getDataByNoRequest(bean.getPengajuanBiayaId());

                if (notifikasiEntityList!=null){
                    for (ImNotifikasiEntity notifikasiEntity : notifikasiEntityList){
                        notifikasiEntity.setFlag("N");

                        try {
                            // Update into database
                            notifikasiDao.updateAndSave(notifikasiEntity);
                        } catch (HibernateException e) {
                            logger.error("[CutiPegawaiBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving update data alat, please info to your admin..." + e.getMessage());
                        }

                    }
                }
            }
        }

        logger.info("[PengajuanBiayaBoImpl.batalkanPengajuanBiaya] end process <<<");
        return response;
    }

    @Override
    public String cekApakahSudahCloseSemua(String pengajuanDetailId) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.setRkSudahDikirim] start process >>>");
        String status="Y";
        ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity = pengajuanBiayaDetailDao.getById("pengajuanBiayaDetailId",pengajuanDetailId);

        List<ItPengajuanBiayaDetailEntity> pengajuanBiayaDetailEntityList = pengajuanBiayaDetailDao.getByPengajuanBiayaId(pengajuanBiayaDetailEntity.getPengajuanBiayaId());
        for(ItPengajuanBiayaDetailEntity data : pengajuanBiayaDetailEntityList){
            if (data.getClosed()==null){
                status="N";
                break;
            }
        }

        logger.info("[PengajuanBiayaBoImpl.setRkSudahDikirim] end process <<<");
        return status;
    }

    @Override
    public PengajuanBiayaDetail getDetailPembayaranForReport(String pengajuanBiayaDetailId) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.getDetailPembayaranForReport] start process >>>");
        ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity ;
        try {
            pengajuanBiayaDetailEntity = pengajuanBiayaDetailDao.getById("pengajuanBiayaDetailId",pengajuanBiayaDetailId);
        } catch (HibernateException e) {
            logger.error("[PengajuanBiayaBoImpl.getDetailPembayaranForReport] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        logger.info("[PengajuanBiayaBoImpl.getDetailPembayaran] end process <<<");

        return convertPengajuanBiayaDetail(pengajuanBiayaDetailEntity);
    }

    @Override
    public boolean cekApakahPengajuanBisaDiubah(String id, BigDecimal jumlah){
        logger.info("[PengajuanBiayaBoImpl.cekApakahPengajuanBisaDiubah] start process >>>");
        boolean result = true;
        ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity ;
        try {
            pengajuanBiayaDetailEntity = pengajuanBiayaDetailDao.getById("pengajuanBiayaDetailId",id);
        } catch (HibernateException e) {
            logger.error("[PengajuanBiayaBoImpl.getDetailPembayaranForReport] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (jumlah.compareTo(pengajuanBiayaDetailEntity.getJumlah())>0){
            result = false;
        }
        logger.info("[PengajuanBiayaBoImpl.cekApakahPengajuanBisaDiubah] end process <<<");
        return result;
    }

    @Override
    public List<PengajuanBiayaDetail> getByCriteriaDetail(PengajuanBiayaDetail searchBean) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.getByCriteriaDetail] start process >>>");

        // Mapping with collection and put
        List<PengajuanBiayaDetail> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPengajuanBiayaDetailId() != null && !"".equalsIgnoreCase(searchBean.getPengajuanBiayaDetailId())) {
                hsCriteria.put("pengajuan_biaya_detail_id", searchBean.getPengajuanBiayaDetailId());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getDivisiId() != null && !"".equalsIgnoreCase(searchBean.getDivisiId())) {
                hsCriteria.put("divisi_id", searchBean.getDivisiId());
            }
            if (searchBean.getTransaksi() != null && !"".equalsIgnoreCase(searchBean.getTransaksi())) {
                hsCriteria.put("transaksi", searchBean.getTransaksi());
            }
            if (searchBean.getStTanggalDari() != null && !"".equalsIgnoreCase(searchBean.getStTanggalDari())) {
                Timestamp tanggalDari = CommonUtil.convertToTimestamp(searchBean.getStTanggalDari());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTanggalSelesai() != null && !"".equalsIgnoreCase(searchBean.getStTanggalSelesai())) {
                Timestamp tanggalSelesai = CommonUtil.convertToTimestamp(searchBean.getStTanggalSelesai());
                hsCriteria.put("tanggal_selesai", tanggalSelesai);
            }
            if (searchBean.getStTanggalDariRealisasi() != null && !"".equalsIgnoreCase(searchBean.getStTanggalDariRealisasi())) {
                Timestamp tanggalDariRealisasi = CommonUtil.convertToTimestamp(searchBean.getStTanggalDariRealisasi());
                hsCriteria.put("tanggal_dari_realisasi", tanggalDariRealisasi);
            }
            if (searchBean.getStTanggalSelesaiRealisasi() != null && !"".equalsIgnoreCase(searchBean.getStTanggalSelesaiRealisasi())) {
                Timestamp tanggalSelesaiRealisasi = CommonUtil.convertToTimestamp(searchBean.getStTanggalSelesaiRealisasi());
                hsCriteria.put("tanggal_selesai_realisasi", tanggalSelesaiRealisasi);
            }
            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                if ("N".equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchBean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ItPengajuanBiayaDetailEntity> itPengajuanBiayaDetailEntityList = null;
            try {
                itPengajuanBiayaDetailEntityList = pengajuanBiayaDetailDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PengajuanBiayaBoImpl.getByCriteriaDetail] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itPengajuanBiayaDetailEntityList != null){
                // Looping from dao to object and save in collection
                BigDecimal total = BigDecimal.ZERO;
                for(ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity : itPengajuanBiayaDetailEntityList){
                    PengajuanBiayaDetail returnData = convertPengajuanBiayaDetail(pengajuanBiayaDetailEntity);

                    if (!"".equalsIgnoreCase(searchBean.getStatusSaatIni())){
                        if (!returnData.getStatusSaatIni().equalsIgnoreCase(searchBean.getStatusSaatIni())){
                            continue;
                        }
                    }
                    List<ItJurnalEntity> jurnalEntityList = jurnalDao.getListJurnalByPengajuanId(returnData.getPengajuanBiayaDetailId());
                    if (jurnalEntityList.size()>0){
                        returnData.setSudahDibayar(true);
                    }
                    if (returnData.getTanggalRealisasi()!=null&&!returnData.isSudahDibayar()){
                        total = total.add(pengajuanBiayaDetailEntity.getJumlah());
                    }
                    listOfResult.add(returnData);
                }

                PengajuanBiayaDetail pengajuanBiayaDetail = new PengajuanBiayaDetail();
                pengajuanBiayaDetail.setPengajuanBiayaDetailId("");
                pengajuanBiayaDetail.setBranchName("");
                pengajuanBiayaDetail.setDivisiName("JUMLAH");
                pengajuanBiayaDetail.setStTanggal("");
                pengajuanBiayaDetail.setStTanggalRealisasi("");
                pengajuanBiayaDetail.setKeperluanName("");
                pengajuanBiayaDetail.setStatusSaatIni("");
                pengajuanBiayaDetail.setCanView(false);
                pengajuanBiayaDetail.setJumlah(total);
                pengajuanBiayaDetail.setStJumlah(CommonUtil.numbericFormat(total,"###,###"));
                listOfResult.add(pengajuanBiayaDetail);
            }
        }
        logger.info("[PengajuanBiayaBoImpl.getByCriteriaDetail] end process <<<");

        return listOfResult;
    }

    @Override
    public PengajuanBiayaDetail modalPopUpDetail(String id){
        return convertPengajuanBiayaDetail(pengajuanBiayaDetailDao.getById("pengajuanBiayaDetailId",id));
    }

    @Override
    public void setRkDiterima(String pengajuanId) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.setRkDiterima] start process >>>");
        //untuk mengecek jika sudah tutup semua maka akan di close
        List<ItPengajuanBiayaDetailEntity> pengajuanBiayaDetailEntityList = pengajuanBiayaDetailDao.getByPengajuanBiayaId(pengajuanId);
        for (ItPengajuanBiayaDetailEntity data : pengajuanBiayaDetailEntityList){
            data.setDiterimaFlag("Y");
            pengajuanBiayaDetailDao.updateAndSave(data);
        }
        logger.info("[PengajuanBiayaBoImpl.setRkDiterima] end process <<<");
    }

    @Override
    public String getNoKontrak(String keperluanId){
        logger.info("[PengajuanBiayaBoImpl.getNoKontrak] start process >>>");
        ItAkunBudgetingPengadaanEntity budgetingPengadaanEntity = budgetingPengadaanDao.getById("idPengadaan",keperluanId);
        String noKontrakAdendum1,noKontrakAdendum2,noKontrakAdendum3;

        if (budgetingPengadaanEntity.getNoKontrakAdendum1()==null){
            noKontrakAdendum1="";
        }else{
            noKontrakAdendum1=budgetingPengadaanEntity.getNoKontrakAdendum1();
        }

        if (budgetingPengadaanEntity.getNoKontrakAdendum2()==null){
            noKontrakAdendum2="";
        }else{
            noKontrakAdendum2=budgetingPengadaanEntity.getNoKontrakAdendum2();
        }

        if (budgetingPengadaanEntity.getNoKontrakAdendum3()==null){
            noKontrakAdendum3="";
        }else{
            noKontrakAdendum3=budgetingPengadaanEntity.getNoKontrakAdendum3();
        }

        String noKontrak = budgetingPengadaanEntity.getNoKontrak()+noKontrakAdendum1+noKontrakAdendum2+noKontrakAdendum3;
        logger.info("[PengajuanBiayaBoImpl.getNoKontrak] end process <<<");
        return noKontrak;
    }
}
