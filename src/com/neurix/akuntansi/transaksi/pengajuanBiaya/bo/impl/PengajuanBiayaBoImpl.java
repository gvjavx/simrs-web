package com.neurix.akuntansi.transaksi.pengajuanBiaya.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.mappingJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;
import com.neurix.akuntansi.master.trans.dao.TransDao;
import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.akuntansi.master.trans.model.Trans;
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
import com.neurix.authorization.user.model.ImUsers;
import com.neurix.authorization.user.model.User;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                    userList=userDao.getUserByBranchAndRole(bean.getBranchId(),"39");
                    break;
                case "PDU":
                    userList=userDao.getUserByBranchAndRole(bean.getBranchId(),"39");
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
                detailEntity.setKeperluan(pengajuanBiayaDetail.getKeperluan());
                detailEntity.setKeterangan(pengajuanBiayaDetail.getKeterangan());

                detailEntity.setCreatedDate(bean.getCreatedDate());
                detailEntity.setLastUpdate(bean.getLastUpdate());
                detailEntity.setCreatedWho(bean.getCreatedWho());
                detailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                detailEntity.setFlag(bean.getFlag());
                detailEntity.setAction(bean.getAction());
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
                logger.error("[PengajuanBiayaBoImpl.saveAddPengajuan] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }

            List<User> userList = new ArrayList<>();

            ImPosition position = positionDao.getById("positionId",bean.getDivisiId());
            String[] koderingPosisi = position.getKodering().split("\\.");

            if (CommonConstant.ID_KANPUS.equalsIgnoreCase(bean.getBranchId())){
                //sementara diarahkan ke kabid langsung
                List<ImPosition> positionList = positionDao.getListPositionKoderingNKelompokPosition(koderingPosisi[0]+"."+koderingPosisi[1]+"%","KL06");

                for (ImPosition imPosition : positionList ){
                    userList=userDao.getUserByBranchAndPositionAndRole(bean.getBranchId(),imPosition.getPositionId(),"45");
                }
            }else {
                //jika bukan kanpus
                List<ImPosition> positionList = positionDao.getListPositionKoderingNKelompokPosition(koderingPosisi[0]+"."+koderingPosisi[1]+"%","KL02");

                for (ImPosition imPosition : positionList ){
                    userList=userDao.getUserByBranchAndPositionAndRole(bean.getBranchId(),imPosition.getPositionId(),"45");
                }
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
                    returnPengajuanBiaya.setAprovalId(pengajuanBiayaEntity.getAprovalId());
                    returnPengajuanBiaya.setAprovalDate(pengajuanBiayaEntity.getAprovalDate());
                    returnPengajuanBiaya.setAprovalFlag(pengajuanBiayaEntity.getAprovalFlag());
                    returnPengajuanBiaya.setBudgetSaatIni(pengajuanBiayaEntity.getBudgetSaatIni());
                    returnPengajuanBiaya.setBranchId(pengajuanBiayaEntity.getBranchId());
                    returnPengajuanBiaya.setTransaksi(pengajuanBiayaEntity.getTransaksi());
                    returnPengajuanBiaya.setAprovalName(pengajuanBiayaEntity.getAprovalName());
                    returnPengajuanBiaya.setNoJurnal(pengajuanBiayaEntity.getNoJurnal());
                    returnPengajuanBiaya.setKeterangan(pengajuanBiayaEntity.getKeterangan());
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
                returnPengajuanBiayaDetail.setKeterangan(pengajuanBiayaDetailEntity.getKeterangan());
                returnPengajuanBiayaDetail.setStTanggal(CommonUtil.convertDateToString(pengajuanBiayaDetailEntity.getTanggal()));
                returnPengajuanBiayaDetail.setJumlah(pengajuanBiayaDetailEntity.getJumlah());
                returnPengajuanBiayaDetail.setBudgetBiaya(pengajuanBiayaDetailEntity.getBudgetBiaya());
                returnPengajuanBiayaDetail.setBudgetTerpakai(pengajuanBiayaDetailEntity.getBudgetTerpakai());

                returnPengajuanBiayaDetail.setStJumlah(CommonUtil.numbericFormat(pengajuanBiayaDetailEntity.getJumlah(),"###,###"));
                returnPengajuanBiayaDetail.setStBudgetTerpakai(CommonUtil.numbericFormat(pengajuanBiayaDetailEntity.getBudgetTerpakai(),"###,###"));
                returnPengajuanBiayaDetail.setStBudgetBiaya(CommonUtil.numbericFormat(pengajuanBiayaDetailEntity.getBudgetBiaya(),"###,###"));

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

                String positionId = CommonUtil.userPosisiId();
                String kelompokId="";
                if (positionId!=null){
                    ImPosition imPosition = positionDao.getById("positionId",positionId);
                    kelompokId = imPosition.getKelompokId();
                }

                if ("39".equalsIgnoreCase(CommonUtil.roleIdAsLogin())&&!CommonConstant.ID_KANPUS.equalsIgnoreCase(CommonUtil.userBranchLogin())&&"Y".equalsIgnoreCase(pengajuanBiayaDetailEntity.getApprovalKeuanganKpFlag())){
                    returnPengajuanBiayaDetail.setStatusUserApproval("TKE");
                }else if ("39".equalsIgnoreCase(CommonUtil.roleIdAsLogin())&&!CommonConstant.ID_KANPUS.equalsIgnoreCase(CommonUtil.userBranchLogin())){
                    returnPengajuanBiayaDetail.setStatusUserApproval("KE");
                }else if ("39".equalsIgnoreCase(CommonUtil.roleIdAsLogin())&&CommonConstant.ID_KANPUS.equalsIgnoreCase(CommonUtil.userBranchLogin())){
                    returnPengajuanBiayaDetail.setStatusUserApproval("KEKP");
                }else{
                    switch (kelompokId){
                        case "KL02":
                            returnPengajuanBiayaDetail.setStatusUserApproval("KS");
                            break;
                        case "KL01":
                            returnPengajuanBiayaDetail.setStatusUserApproval("KD");
                            break;
                        case "KL08":
                            returnPengajuanBiayaDetail.setStatusUserApproval("KD");
                            break;
                        case "KL04":
                            returnPengajuanBiayaDetail.setStatusUserApproval("GM");
                            break;
                        case "KL06":
                            returnPengajuanBiayaDetail.setStatusUserApproval("GM");
                            break;
                    }
                }


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
            if (CommonConstant.ID_KANPUS.equalsIgnoreCase(pengajuanBiayaDetailEntity.getBranchId())){

            }else {
                //Approve
                switch (bean.getStatusApproval()){
                    case "KS":
                        pengajuanBiayaDetailEntity.setApprovalKasubdivFlag(bean.getApprovalKasubdivFlag());
                        pengajuanBiayaDetailEntity.setApprovalKasubdivId(bean.getApprovalKasubdivId());
                        pengajuanBiayaDetailEntity.setApprovalKasubdivDate(bean.getApprovalKasubdivDate());

                        ImPosition position = positionDao.getById("positionId",pengajuanBiayaDetailEntity.getDivisiId());
                        String[] koderingPosisi = position.getKodering().split("\\.");
                        List<ImPosition> positionList = positionDao.getListPositionKoderingNKelompokPosition(koderingPosisi[0]+"%","KL01");

                        for (ImPosition imPosition : positionList ){
                            userList=userDao.getUserByBranchAndPositionAndRole(pengajuanBiayaDetailEntity.getBranchId(),imPosition.getPositionId(),"45");
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
                        if (pengajuanBiayaDetailEntity.getJumlah().compareTo(maxPengajuanBiaya)<0){
                            pengajuanBiayaDetailEntity.setApprovalGmFlag(bean.getApprovalKadivFlag());
                            pengajuanBiayaDetailEntity.setApprovalGmId(bean.getApprovalKadivId());
                            pengajuanBiayaDetailEntity.setApprovalGmDate(bean.getApprovalKadivDate());
                            userList=userDao.getUserByBranchAndRole(pengajuanBiayaDetailEntity.getBranchId(),"39");
                        }else{
                            userList=userDao.getUserByBranchAndPositionAndRole(pengajuanBiayaDetailEntity.getBranchId(),"P078","45");
                        }
                        break;
                    case "GM":
                        pengajuanBiayaDetailEntity.setApprovalGmFlag(bean.getApprovalGmFlag());
                        pengajuanBiayaDetailEntity.setApprovalGmId(bean.getApprovalGmId());
                        pengajuanBiayaDetailEntity.setApprovalGmDate(bean.getApprovalGmDate());
                        userList=userDao.getUserByBranchAndRole(pengajuanBiayaDetailEntity.getBranchId(),"39");
                        break;
                    case "KE":
                        pengajuanBiayaDetailEntity.setApprovalKeuanganFlag(bean.getApprovalKeuanganFlag());
                        pengajuanBiayaDetailEntity.setApprovalKeuanganId(bean.getApprovalKeuanganId());
                        pengajuanBiayaDetailEntity.setApprovalKeuanganDate(bean.getApprovalKeuanganDate());
                        pengajuanBiayaDetailEntity.setStatusKeuangan(bean.getStatusKeuangan());
                        userList=userDao.getUserByBranchAndRole(CommonConstant.ID_KANPUS,"39");
                        break;
                }
            }

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


            for (User user : userList){
                //Send notif ke nip tertentu
                Notifikasi notif= new Notifikasi();
                notif.setNip(user.getUserId());
                notif.setNoRequest(pengajuanBiayaDetailEntity.getPengajuanBiayaId());
                notif.setTipeNotifId("TN04");
                notif.setTipeNotifName(("Pengajuan Biaya"));
                notif.setNote(pengajuanBiayaDetailEntity.getKeterangan());
                notif.setCreatedWho(bean.getLastUpdateWho());
                notif.setTo("ditentukan");

                notifikasiList.add(notif);
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
                    pengajuanBiayaDetailEntity.setClosed("Y");
                    pengajuanBiayaDetailEntity.setNoJurnal(bean.getNoJurnal());
                    break;
                case "KEKP":
                    pengajuanBiayaDetailEntity.setApprovalKeuanganKpFlag(bean.getApprovalKeuanganKpFlag());
                    pengajuanBiayaDetailEntity.setApprovalKeuanganKpId(bean.getApprovalKeuanganId());
                    pengajuanBiayaDetailEntity.setApprovalKeuanganKpDate(bean.getApprovalKeuanganKpDate());
                    pengajuanBiayaDetailEntity.setStatusKeuangan(bean.getStatusKeuangan());
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

            //jika semua sudah approve
            if ("KE".equalsIgnoreCase(bean.getStatusApproval())&&"A".equalsIgnoreCase(pengajuanBiayaDetailEntity.getStatusKeuangan())||"TKE".equalsIgnoreCase(bean.getStatusApproval())){
                Map hsCriteria = new HashMap();
                hsCriteria.put("pengajuan_biaya_id",pengajuanBiayaDetailEntity.getPengajuanBiayaId());
                hsCriteria.put("flag","Y");
                List<ItPengajuanBiayaDetailEntity> pengajuanBiayaDetailEntityList = pengajuanBiayaDetailDao.getByCriteria(hsCriteria);

                int count = 0;
                for (ItPengajuanBiayaDetailEntity detailEntity : pengajuanBiayaDetailEntityList){
                    if ("Y".equalsIgnoreCase(detailEntity.getClosed())){
                        count++;
                    }
                }

                // jika semua data sudah di approve oleh keuangan maka langsung di close
                if (count==pengajuanBiayaDetailEntityList.size()){
                    ImPengajuanBiayaEntity pengajuanBiayaEntity = null;
                    pengajuanBiayaEntity = pengajuanBiayaDao.getById("pengajuanBiayaId",pengajuanBiayaDetailEntity.getPengajuanBiayaId());

                    pengajuanBiayaEntity.setAprovalFlag("Y");
                    pengajuanBiayaEntity.setAprovalDate(new Date(bean.getLastUpdate().getTime()));
                    pengajuanBiayaEntity.setAprovalId(bean.getLastUpdateWho());
                    pengajuanBiayaEntity.setAprovalName(CommonUtil.userLogin());

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
}
