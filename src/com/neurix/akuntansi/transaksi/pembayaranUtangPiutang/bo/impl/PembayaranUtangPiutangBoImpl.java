package com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.mappingJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;
import com.neurix.akuntansi.master.trans.dao.TransDao;
import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.akuntansi.master.trans.model.Trans;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDao;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDetailDao;
import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalDetailEntity;
import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalEntity;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.bo.PembayaranUtangPiutangBo;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.dao.LampiranDao;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.dao.PembayaranUtangPiutangDetailDao;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.dao.PembayaranUtangPiutangDao;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.*;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.dao.PengajuanBiayaRkDao;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.ItAkunPengajuanBiayaRkEntity;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.user.dao.UserDao;
import com.neurix.authorization.user.model.ImUsers;
import com.neurix.authorization.user.model.User;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import io.agora.recording.common.Common;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class PembayaranUtangPiutangBoImpl implements PembayaranUtangPiutangBo {

    protected static transient Logger logger = Logger.getLogger(PembayaranUtangPiutangBoImpl.class);
    private PembayaranUtangPiutangDao pembayaranUtangPiutangDao;
    private PembayaranUtangPiutangDetailDao pembayaranUtangPiutangDetailDao;
    private KodeRekeningDao kodeRekeningDao;
    private JurnalDao jurnalDao;
    private JurnalDetailDao jurnalDetailDao;
    private TransDao transDao;
    private BranchDao branchDao;
    private PositionDao positionDao;
    private MappingJurnalDao mappingJurnalDao;
    private UserDao userDao;
    private LampiranDao lampiranDao;
    private PengajuanBiayaRkDao pengajuanBiayaRkDao;

    public PengajuanBiayaRkDao getPengajuanBiayaRkDao() {
        return pengajuanBiayaRkDao;
    }

    public void setPengajuanBiayaRkDao(PengajuanBiayaRkDao pengajuanBiayaRkDao) {
        this.pengajuanBiayaRkDao = pengajuanBiayaRkDao;
    }

    public LampiranDao getLampiranDao() {
        return lampiranDao;
    }

    public void setLampiranDao(LampiranDao lampiranDao) {
        this.lampiranDao = lampiranDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
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

    public PembayaranUtangPiutangDetailDao getPembayaranUtangPiutangDetailDao() {
        return pembayaranUtangPiutangDetailDao;
    }

    public void setPembayaranUtangPiutangDetailDao(PembayaranUtangPiutangDetailDao pembayaranUtangPiutangDetailDao) {
        this.pembayaranUtangPiutangDetailDao = pembayaranUtangPiutangDetailDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PembayaranUtangPiutangBoImpl.logger = logger;
    }

    public PembayaranUtangPiutangDao getPembayaranUtangPiutangDao() {
        return pembayaranUtangPiutangDao;
    }

    public void setPembayaranUtangPiutangDao(PembayaranUtangPiutangDao pembayaranUtangPiutangDao) {
        this.pembayaranUtangPiutangDao = pembayaranUtangPiutangDao;
    }

    @Override
    public void saveDelete(PembayaranUtangPiutang bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean!=null) {
            ImPembayaranUtangPiutangEntity imPembayaranUtangPiutangEntity = new ImPembayaranUtangPiutangEntity();
            try {
                // Get data from database by ID
                imPembayaranUtangPiutangEntity = pembayaranUtangPiutangDao.getById("pembayaranUtangPiutangId", bean.getPembayaranUtangPiutangId());
            } catch (HibernateException e) {
                logger.error("[PembayaranUtangPiutangBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPembayaranUtangPiutangEntity != null) {
                // Modify from bean to entity serializable
                imPembayaranUtangPiutangEntity.setFlag(bean.getFlag());
                imPembayaranUtangPiutangEntity.setAction(bean.getAction());
                imPembayaranUtangPiutangEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPembayaranUtangPiutangEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    pembayaranUtangPiutangDao.updateAndSave(imPembayaranUtangPiutangEntity);
                } catch (HibernateException e) {
                    logger.error("[PembayaranUtangPiutangBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
                }

                //Mapping dihapus terlebih dahulu
                List<ImPembayaranUtangPiutangDetailEntity> pembayaranUtangPiutangDetailEntityList = pembayaranUtangPiutangDetailDao.getListPembayaranUtangPiutangDetailByTipeJurnalId(bean.getPembayaranUtangPiutangId());
                for (ImPembayaranUtangPiutangDetailEntity pembayaranUtangPiutangDetailEntity : pembayaranUtangPiutangDetailEntityList){
                    pembayaranUtangPiutangDetailEntity.setFlag("N");
                    pembayaranUtangPiutangDetailEntity.setAction(bean.getAction());
                    pembayaranUtangPiutangDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    pembayaranUtangPiutangDetailEntity.setLastUpdate(bean.getLastUpdate());
                    pembayaranUtangPiutangDetailDao.updateAndSave(pembayaranUtangPiutangDetailEntity);
                }

            } else {
                logger.error("[PembayaranUtangPiutangBoImpl.saveDelete] Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");

            }
        }
        logger.info("[PembayaranUtangPiutangBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(PembayaranUtangPiutang bean) throws GeneralBOException {
        logger.info("[PembayaranUtangPiutangBoImpl.saveEdit] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KodeRekening> kodeRekeningList = (List<KodeRekening>) session.getAttribute("listOfResultKodeRekening");

        if (bean!=null) {
            ImPembayaranUtangPiutangEntity imPembayaranUtangPiutangEntity = null;
            try {
                // Get data from database by ID
                imPembayaranUtangPiutangEntity = pembayaranUtangPiutangDao.getById("pembayaranUtangPiutangId", bean.getPembayaranUtangPiutangId());
            } catch (HibernateException e) {
                logger.error("[PembayaranUtangPiutangBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PembayaranUtangPiutang by Kode PembayaranUtangPiutang, please inform to your admin...," + e.getMessage());
            }
            if (imPembayaranUtangPiutangEntity != null) {
                imPembayaranUtangPiutangEntity.setFlag(bean.getFlag());
                imPembayaranUtangPiutangEntity.setAction(bean.getAction());
                imPembayaranUtangPiutangEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPembayaranUtangPiutangEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    pembayaranUtangPiutangDao.updateAndSave(imPembayaranUtangPiutangEntity);
                } catch (HibernateException e) {
                    logger.error("[PembayaranUtangPiutangBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PembayaranUtangPiutangBoImpl.saveEdit] Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");
            }

            //Mapping dihapus terlebih dahulu
            List<ImPembayaranUtangPiutangDetailEntity> pembayaranUtangPiutangDetailEntityList = pembayaranUtangPiutangDetailDao.getListPembayaranUtangPiutangDetailByTipeJurnalId(bean.getPembayaranUtangPiutangId());
            for (ImPembayaranUtangPiutangDetailEntity pembayaranUtangPiutangDetailEntity : pembayaranUtangPiutangDetailEntityList){
                pembayaranUtangPiutangDetailEntity.setFlag("N");
                pembayaranUtangPiutangDetailEntity.setAction(bean.getAction());
                pembayaranUtangPiutangDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                pembayaranUtangPiutangDetailEntity.setLastUpdate(bean.getLastUpdate());
                pembayaranUtangPiutangDetailDao.updateAndSave(pembayaranUtangPiutangDetailEntity);
            }

            //Mapping yang baru ditambahkan
            for (KodeRekening kodeRekening : kodeRekeningList){
                ImPembayaranUtangPiutangDetailEntity pembayaranUtangPiutangDetailEntity = new ImPembayaranUtangPiutangDetailEntity();
                String mappingId = pembayaranUtangPiutangDetailDao.getNextPembayaranUtangPiutangDetailId();

                pembayaranUtangPiutangDetailEntity.setFlag(bean.getFlag());
                pembayaranUtangPiutangDetailEntity.setAction(bean.getAction());
                pembayaranUtangPiutangDetailEntity.setCreatedWho(bean.getCreatedWho());
                pembayaranUtangPiutangDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                pembayaranUtangPiutangDetailEntity.setCreatedDate(bean.getCreatedDate());
                pembayaranUtangPiutangDetailEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    pembayaranUtangPiutangDetailDao.addAndSave(pembayaranUtangPiutangDetailEntity);
                } catch (HibernateException e) {
                    logger.error("[PembayaranUtangPiutangBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
                }
            }
        }
        logger.info("[PembayaranUtangPiutangBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PembayaranUtangPiutang saveAdd(PembayaranUtangPiutang bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<PembayaranUtangPiutang> getByCriteria(PembayaranUtangPiutang searchBean) throws GeneralBOException {
        logger.info("[PembayaranUtangPiutangBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PembayaranUtangPiutang> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPembayaranUtangPiutangId() != null && !"".equalsIgnoreCase(searchBean.getPembayaranUtangPiutangId())) {
                hsCriteria.put("pembayaran_id", searchBean.getPembayaranUtangPiutangId());
            }
            if (searchBean.getNoJurnal() != null && !"".equalsIgnoreCase(searchBean.getNoJurnal())) {
                hsCriteria.put("no_jurnal", searchBean.getNoJurnal());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getTanggal() != null) {
                hsCriteria.put("tanggal", searchBean.getTanggal());
            }
            if (searchBean.getTipeTransaksi() != null && !"".equalsIgnoreCase(searchBean.getTipeTransaksi())) {
                hsCriteria.put("tipe_transaksi", searchBean.getTipeTransaksi());
            }
            if (searchBean.getTipePembayaran() != null && !"".equalsIgnoreCase(searchBean.getTipePembayaran())) {
                hsCriteria.put("tipe_pembayaran", searchBean.getTipePembayaran());
            }
            if (searchBean.getStTanggalDari() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalDari()))) {
                Timestamp tanggalDari = CommonUtil.convertToTimestamp(searchBean.getStTanggalDari());
                hsCriteria.put("tanggal_dari", tanggalDari);
            }
            if (searchBean.getStTanggalSelesai() != null && !"".equalsIgnoreCase(String.valueOf(searchBean.getStTanggalSelesai()))) {
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

            List<ImPembayaranUtangPiutangEntity> imPembayaranUtangPiutangEntity = null;
            try {
                imPembayaranUtangPiutangEntity = pembayaranUtangPiutangDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PembayaranUtangPiutangBoImpl.getSearchPembayaranUtangPiutangByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPembayaranUtangPiutangEntity != null){
                PembayaranUtangPiutang returnPembayaranUtangPiutang;
                // Looping from dao to object and save in collection
                for(ImPembayaranUtangPiutangEntity pembayaranUtangPiutangEntity : imPembayaranUtangPiutangEntity){
                    returnPembayaranUtangPiutang = new PembayaranUtangPiutang();
                    returnPembayaranUtangPiutang.setPembayaranUtangPiutangId(pembayaranUtangPiutangEntity.getPembayaranUtangPiutangId());
                    returnPembayaranUtangPiutang.setTipeTransaksi(pembayaranUtangPiutangEntity.getTipeTransaksi());
                    returnPembayaranUtangPiutang.setTanggal(pembayaranUtangPiutangEntity.getTanggal());
                    returnPembayaranUtangPiutang.setStTanggal(CommonUtil.convertDateToString(pembayaranUtangPiutangEntity.getTanggal()));
                    returnPembayaranUtangPiutang.setMetodePembayaran(pembayaranUtangPiutangEntity.getMetodeBayar());
                    returnPembayaranUtangPiutang.setBank(pembayaranUtangPiutangEntity.getBank());
                    returnPembayaranUtangPiutang.setBayar(pembayaranUtangPiutangEntity.getBayar());
                    returnPembayaranUtangPiutang.setStBayar(CommonUtil.numbericFormat(pembayaranUtangPiutangEntity.getBayar(), "###,###"));
                    returnPembayaranUtangPiutang.setKeterangan(pembayaranUtangPiutangEntity.getKeterangan());
                    returnPembayaranUtangPiutang.setNoSlipBank(pembayaranUtangPiutangEntity.getNoSlipBank());
                    returnPembayaranUtangPiutang.setBranchId(pembayaranUtangPiutangEntity.getBranchId());
                    returnPembayaranUtangPiutang.setApprovalKeuanganFlag(pembayaranUtangPiutangEntity.getApprovalKeuanganFlag());
                    returnPembayaranUtangPiutang.setApprovalKeuanganId(pembayaranUtangPiutangEntity.getApprovalKeuanganId());
                    returnPembayaranUtangPiutang.setApprovalKeuanganName(pembayaranUtangPiutangEntity.getApprovalKeuanganName());
                    returnPembayaranUtangPiutang.setApprovalKeuanganDate(pembayaranUtangPiutangEntity.getApprovalKeuanganDate());
                    if (pembayaranUtangPiutangEntity.getApprovalKeuanganDate()!=null){
                        returnPembayaranUtangPiutang.setStApprovalKeuanganDate(CommonUtil.convertTimestampToStringLengkap(pembayaranUtangPiutangEntity.getApprovalKeuanganDate()));
                    }
                    returnPembayaranUtangPiutang.setApprovalKasubKeuanganFlag(pembayaranUtangPiutangEntity.getApprovalKasubKeuanganFlag());
                    returnPembayaranUtangPiutang.setApprovalKasubKeuanganId(pembayaranUtangPiutangEntity.getApprovalKasubKeuanganId());
                    returnPembayaranUtangPiutang.setApprovalKasubKeuanganName(pembayaranUtangPiutangEntity.getApprovalKasubKeuanganName());
                    returnPembayaranUtangPiutang.setApprovalKasubKeuanganDate(pembayaranUtangPiutangEntity.getApprovalKasubKeuanganDate());
                    if (pembayaranUtangPiutangEntity.getApprovalKasubKeuanganDate()!=null){
                        returnPembayaranUtangPiutang.setStApprovalKasubKeuanganDate(CommonUtil.convertTimestampToStringLengkap(pembayaranUtangPiutangEntity.getApprovalKasubKeuanganDate()));
                    }

                    List<ImBranches> branchesList = branchDao.getListBranchById(pembayaranUtangPiutangEntity.getBranchId());
                    for (ImBranches branches : branchesList){
                        returnPembayaranUtangPiutang.setBranchName(branches.getBranchName());
                    }

                    String roleId = CommonUtil.roleIdAsLogin();
                    String branchIdUser = CommonUtil.userBranchLogin();

                    if (branchIdUser.equalsIgnoreCase(pembayaranUtangPiutangEntity.getBranchId())){
                        if (CommonConstant.ROLE_ID_ADMIN_AKS.equalsIgnoreCase(roleId)){
                            returnPembayaranUtangPiutang.setJabatan("keu");
                        }else if (CommonConstant.ROLE_ID_KASUB_KEU.equalsIgnoreCase(roleId)){
                            if ("Y".equalsIgnoreCase(pembayaranUtangPiutangEntity.getApprovalKeuanganFlag())){
                                returnPembayaranUtangPiutang.setJabatan("kasub");
                            }
                        }else if (CommonConstant.ROLE_ID_KA_KEU.equalsIgnoreCase(roleId)){
                            if ("Y".equalsIgnoreCase(pembayaranUtangPiutangEntity.getApprovalKasubKeuanganFlag())) {
                                returnPembayaranUtangPiutang.setJabatan("ka");
                            }
                        }
                    }

                    returnPembayaranUtangPiutang.setNoJurnal(pembayaranUtangPiutangEntity.getNoJurnal());
                    returnPembayaranUtangPiutang.setTipePembayaran(pembayaranUtangPiutangEntity.getTipePembayaran());

                    if (pembayaranUtangPiutangEntity.getRegisteredFlag()!=null){
                        if (("Y").equalsIgnoreCase(pembayaranUtangPiutangEntity.getRegisteredFlag())){
                            returnPembayaranUtangPiutang.setFlagPosting(true);
                        }
                    }
                    if (pembayaranUtangPiutangEntity.getTipeTransaksi()!=null){
                        ImTransEntity transEntity;
                        try {
                            transEntity = transDao.getById("transId",pembayaranUtangPiutangEntity.getTipeTransaksi());
                            returnPembayaranUtangPiutang.setStTipeTransaksi(transEntity.getTransName());
                        } catch (HibernateException e) {
                            logger.error("[PembayaranUtangPiutangBoImpl.getSearchPembayaranUtangPiutangByCriteria] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }
                    }
                    returnPembayaranUtangPiutang.setMetodePembayaranName(kodeRekeningDao.getNamaRekeningByCoa(pembayaranUtangPiutangEntity.getMetodeBayar()));

                    returnPembayaranUtangPiutang.setRegisteredFlag(pembayaranUtangPiutangEntity.getRegisteredFlag());
                    returnPembayaranUtangPiutang.setRegisteredWho(pembayaranUtangPiutangEntity.getRegisteredWho());
                    returnPembayaranUtangPiutang.setRegisteredDate(pembayaranUtangPiutangEntity.getRegisteredDate());
                    if (pembayaranUtangPiutangEntity.getRegisteredDate()!=null){
                        returnPembayaranUtangPiutang.setStRegisteredDate(CommonUtil.convertTimestampToStringLengkap(pembayaranUtangPiutangEntity.getRegisteredDate()));
                    }
                    returnPembayaranUtangPiutang.setCreatedWho(pembayaranUtangPiutangEntity.getCreatedWho());
                    returnPembayaranUtangPiutang.setCreatedDate(pembayaranUtangPiutangEntity.getCreatedDate());
                    returnPembayaranUtangPiutang.setLastUpdate(pembayaranUtangPiutangEntity.getLastUpdate());
                    returnPembayaranUtangPiutang.setAction(pembayaranUtangPiutangEntity.getAction());
                    returnPembayaranUtangPiutang.setFlag(pembayaranUtangPiutangEntity.getFlag());
                    listOfResult.add(returnPembayaranUtangPiutang);
                }
            }
        }
        logger.info("[PembayaranUtangPiutangBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PembayaranUtangPiutangDetail> getSearchNotaPembayaran(String masterId,String transaksiId,String branchId,String divisiId,String coa) throws GeneralBOException {
        logger.info("[PembayaranUtangPiutangBoImpl.getSearchNotaPembayaran] start process >>>");
        List<PembayaranUtangPiutangDetail> listOfResult = new ArrayList<>();
//        String unit="";
//        if ((CommonConstant.ID_KANPUS).equalsIgnoreCase(branchId)){
//            List<ImBranches> branchList = new ArrayList<>();
//            branchList = branchDao.getAllBranch();
//            int i = 1;
//            for (ImBranches dataUnit : branchList){
//                if (i==1){
//                    unit="'"+dataUnit.getPrimaryKey().getId()+"'";
//                }else{
//                    unit=unit+",'"+dataUnit.getPrimaryKey().getId()+"'";
//                }
//                i++;
//            }
//        }else{
//            unit="'"+branchId+"'";
//        }
        String unit="'"+branchId+"'";

        List<PembayaranUtangPiutangDetail> pembayaranUtangPiutangDetailList ;

        try {
            String tipeBayar = transDao.getTipeBayarByTransId(transaksiId);
            String rekeningId = kodeRekeningDao.getRekeningIdByCoa(coa);
            pembayaranUtangPiutangDetailList = pembayaranUtangPiutangDao.getSearchNotaPembayaran(masterId,unit,tipeBayar,divisiId,rekeningId);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.getSearchPembayaranUtangPiutangByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(pembayaranUtangPiutangDetailList != null){
            PembayaranUtangPiutangDetail returnPembayaranUtangPiutangDetail;
            // Looping from dao to object and save in collection
            for(PembayaranUtangPiutangDetail pembayaranUtangPiutangDetail : pembayaranUtangPiutangDetailList){
                returnPembayaranUtangPiutangDetail = new PembayaranUtangPiutangDetail();
                returnPembayaranUtangPiutangDetail.setRekeningId(pembayaranUtangPiutangDetail.getRekeningId());
                returnPembayaranUtangPiutangDetail.setStJumlahPembayaran(pembayaranUtangPiutangDetail.getStJumlahPembayaran().replace(",","."));
                returnPembayaranUtangPiutangDetail.setStJumlahPembayaran(returnPembayaranUtangPiutangDetail.getStJumlahPembayaran().replace(" ",""));
                returnPembayaranUtangPiutangDetail.setStJumlahPembayaran(returnPembayaranUtangPiutangDetail.getStJumlahPembayaran().replace("-",""));

                if (pembayaranUtangPiutangDetail.getDivisiId()==null){
                    returnPembayaranUtangPiutangDetail.setDivisiId("");
                }else{
                    returnPembayaranUtangPiutangDetail.setDivisiId(pembayaranUtangPiutangDetail.getDivisiId());
                }
                if (pembayaranUtangPiutangDetail.getNoNota()==null){
                    returnPembayaranUtangPiutangDetail.setNoNota("");
                }else{
                    returnPembayaranUtangPiutangDetail.setNoNota(pembayaranUtangPiutangDetail.getNoNota());
                }
                if (pembayaranUtangPiutangDetail.getMasterId()==null){
                    returnPembayaranUtangPiutangDetail.setMasterId("");
                }else{
                    returnPembayaranUtangPiutangDetail.setMasterId(pembayaranUtangPiutangDetail.getMasterId());
                }
                listOfResult.add(returnPembayaranUtangPiutangDetail);
            }
        }
        logger.info("[PembayaranUtangPiutangBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public void postingJurnal(PembayaranUtangPiutang bean) throws GeneralBOException {
        logger.info("[PembayaranUtangPiutangBoImpl.postingJurnal] start process >>>");
        if (bean!=null) {
            ImPembayaranUtangPiutangEntity imPembayaranUtangPiutangEntity = null;
            try {
                // Get data from database by ID
                imPembayaranUtangPiutangEntity = pembayaranUtangPiutangDao.getById("pembayaranUtangPiutangId", bean.getPembayaranUtangPiutangId());
            } catch (HibernateException e) {
                logger.error("[PembayaranUtangPiutangBoImpl.postingJurnal] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PembayaranUtangPiutang by Kode PembayaranUtangPiutang, please inform to your admin...," + e.getMessage());
            }
            if (imPembayaranUtangPiutangEntity != null) {
                imPembayaranUtangPiutangEntity.setRegisteredFlag("Y");
                imPembayaranUtangPiutangEntity.setRegisteredDate(bean.getRegisteredDate());
                imPembayaranUtangPiutangEntity.setRegisteredWho(bean.getLastUpdateWho());

                imPembayaranUtangPiutangEntity.setFlag(bean.getFlag());
                imPembayaranUtangPiutangEntity.setAction(bean.getAction());
                imPembayaranUtangPiutangEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPembayaranUtangPiutangEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    pembayaranUtangPiutangDao.updateAndSave(imPembayaranUtangPiutangEntity);
                } catch (HibernateException e) {
                    logger.error("[PembayaranUtangPiutangBoImpl.postingJurnal] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
                }

                ItJurnalEntity jurnalEntity = jurnalDao.getById("noJurnal",imPembayaranUtangPiutangEntity.getNoJurnal());
                jurnalEntity.setRegisteredDate(CommonUtil.convertTimestampToDate(bean.getRegisteredDate()));
                jurnalEntity.setRegisteredUser(bean.getLastUpdateWho());
                jurnalEntity.setRegisteredFlag("Y");

                jurnalEntity.setFlag(bean.getFlag());
                jurnalEntity.setAction(bean.getAction());
                jurnalEntity.setLastUpdateWho(bean.getLastUpdateWho());
                jurnalEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    jurnalDao.updateAndSave(jurnalEntity);
                } catch (HibernateException e) {
                    logger.error("[PembayaranUtangPiutangBoImpl.postingJurnal] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
                }

//                // JIKA PEMBAYARAN DO MAKA SET PEMBAYARAN DO KE SUDAH DIBAYAR
//                List<ImPembayaranUtangPiutangDetailEntity> pembayaranUtangPiutangDetailEntities = pembayaranUtangPiutangDetailDao.getByPembayaranId(bean.getPembayaranUtangPiutangId());
//                for (ImPembayaranUtangPiutangDetailEntity pembayaranUtangPiutangDetailEntity : pembayaranUtangPiutangDetailEntities){
//                    List<ItAkunPengajuanBiayaRkEntity> pengajuanBiayaRkEntityList = pengajuanBiayaRkDao.getByNoTransaksiId(pembayaranUtangPiutangDetailEntity.getNoNota());
//                    for (ItAkunPengajuanBiayaRkEntity entity : pengajuanBiayaRkEntityList){
//                        entity.setStatus("D");
//                        entity.setMetodeBayar(imPembayaranUtangPiutangEntity.getMetodeBayar());
//                        entity.setNoJurnal(imPembayaranUtangPiutangEntity.getNoJurnal());
//                        entity.setLastUpdate(bean.getLastUpdate());
//                        entity.setLastUpdateWho(bean.getLastUpdateWho());
//                        entity.setAction(bean.getAction());
//
//                        pengajuanBiayaRkDao.updateAndSave(entity);
//                    }
//                }
            } else {
                logger.error("[PembayaranUtangPiutangBoImpl.postingJurnal] Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");
            }
        }
        logger.info("[PembayaranUtangPiutangBoImpl.postingJurnal] end process <<<");
    }


    @Override
    public void addPrintCount(String noJurnal) throws GeneralBOException {
        logger.info("[PembayaranUtangPiutangBoImpl.addPrintCount] start process >>>");
        ItJurnalEntity jurnalEntity;
        try {
            // Update into database
            jurnalEntity=jurnalDao.getById("noJurnal",noJurnal);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.postingJurnal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving update data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
        }
        jurnalEntity.setPrintRegisterCount(jurnalEntity.getPrintRegisterCount().add(new BigDecimal(1)));

        try {
            // Update into database
            jurnalDao.updateAndSave(jurnalEntity);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.postingJurnal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving update data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
        }


        logger.info("[PembayaranUtangPiutangBoImpl.postingJurnal] end process <<<");
    }

    @Override
    public List<PembayaranUtangPiutang> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public String saveAddPembayaran(PembayaranUtangPiutang bean, List<PembayaranUtangPiutangDetail> pembayaranUtangPiutangDetailList, List<Lampiran> lampiranList) throws GeneralBOException {
        logger.info("[PembayaranUtangPiutangBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String pembayaranUtangPiutangId;

            try {
                // Generating ID, get from postgre sequence
                pembayaranUtangPiutangId = pembayaranUtangPiutangDao.getNextPembayaranUtangPiutangId();
            } catch (HibernateException e) {
                logger.error("[PembayaranUtangPiutangBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence pembayaranUtangPiutangId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImPembayaranUtangPiutangEntity imPembayaranUtangPiutangEntity = new ImPembayaranUtangPiutangEntity();
            imPembayaranUtangPiutangEntity.setPembayaranUtangPiutangId(pembayaranUtangPiutangId);
            imPembayaranUtangPiutangEntity.setTipeTransaksi(bean.getTipeTransaksi());
            imPembayaranUtangPiutangEntity.setTanggal(bean.getTanggal());
            imPembayaranUtangPiutangEntity.setMetodeBayar(bean.getMetodePembayaran());
            imPembayaranUtangPiutangEntity.setBank(bean.getBank());
            imPembayaranUtangPiutangEntity.setBayar(bean.getBayar());
            imPembayaranUtangPiutangEntity.setKeterangan(bean.getKeterangan());
            imPembayaranUtangPiutangEntity.setNoSlipBank(bean.getNoSlipBank());
            imPembayaranUtangPiutangEntity.setBranchId(bean.getBranchId());
            imPembayaranUtangPiutangEntity.setNoJurnal(bean.getNoJurnal());
            imPembayaranUtangPiutangEntity.setTipePembayaran(bean.getTipePembayaran());

            imPembayaranUtangPiutangEntity.setFlag(bean.getFlag());
            imPembayaranUtangPiutangEntity.setAction(bean.getAction());
            imPembayaranUtangPiutangEntity.setCreatedWho(bean.getCreatedWho());
            imPembayaranUtangPiutangEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPembayaranUtangPiutangEntity.setCreatedDate(bean.getCreatedDate());
            imPembayaranUtangPiutangEntity.setLastUpdate(bean.getLastUpdate());

            //langsung diapprove bawahan apabila yang membuat adalah atasan
            if (CommonConstant.ROLE_ID_KASUB_KEU.equalsIgnoreCase(CommonUtil.roleIdAsLogin())||CommonConstant.ROLE_ID_KA_KEU.equalsIgnoreCase(CommonUtil.roleIdAsLogin())){
                imPembayaranUtangPiutangEntity.setApprovalKeuanganId(CommonUtil.userIdLogin());
                imPembayaranUtangPiutangEntity.setApprovalKeuanganDate(bean.getLastUpdate());
                imPembayaranUtangPiutangEntity.setApprovalKeuanganName(bean.getCreatedWho());
                imPembayaranUtangPiutangEntity.setApprovalKeuanganFlag("Y");
            }

            if (CommonConstant.ROLE_ID_KA_KEU.equalsIgnoreCase(CommonUtil.roleIdAsLogin())) {
                imPembayaranUtangPiutangEntity.setApprovalKasubKeuanganId(CommonUtil.userIdLogin());
                imPembayaranUtangPiutangEntity.setApprovalKasubKeuanganDate(bean.getLastUpdate());
                imPembayaranUtangPiutangEntity.setApprovalKasubKeuanganName(bean.getCreatedWho());
                imPembayaranUtangPiutangEntity.setApprovalKasubKeuanganFlag("Y");
            }

            try {
                // insert into database
                pembayaranUtangPiutangDao.addAndSave(imPembayaranUtangPiutangEntity);
            } catch (HibernateException e) {
                logger.error("[PembayaranUtangPiutangBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
            }

            if (pembayaranUtangPiutangDetailList!=null){
                for (PembayaranUtangPiutangDetail data : pembayaranUtangPiutangDetailList){
                    BigDecimal jumlahPembayaran = new BigDecimal(data.getStJumlahPembayaran().replace(".",""));
                    BigDecimal ppn = BigDecimal.ZERO;
                    BigDecimal pph = BigDecimal.ZERO;
                    if ("Pengajuan Biaya".equalsIgnoreCase(bean.getTipeMaster())) {
                        ppn = new BigDecimal(data.getStPpn().replace(".",""));
                        pph = new BigDecimal(data.getStPph().replace(".",""));
                    }
                    ImPembayaranUtangPiutangDetailEntity pembayaranUtangPiutangDetailEntity = new ImPembayaranUtangPiutangDetailEntity();
                    String pembayaranUtangPiutangDetailId = pembayaranUtangPiutangDetailDao.getNextPembayaranUtangPiutangDetailId();
                    pembayaranUtangPiutangDetailEntity.setPembayaranUtangPiutangDetailId(pembayaranUtangPiutangDetailId);
                    pembayaranUtangPiutangDetailEntity.setPembayaranUtangPiutangId(pembayaranUtangPiutangId);
                    pembayaranUtangPiutangDetailEntity.setMasterId(data.getMasterId());
                    pembayaranUtangPiutangDetailEntity.setNoNota(data.getNoNota());
                    pembayaranUtangPiutangDetailEntity.setRekeningId(data.getRekeningId());
                    pembayaranUtangPiutangDetailEntity.setJumlahPembayaran(jumlahPembayaran);
                    pembayaranUtangPiutangDetailEntity.setDivisiId(data.getDivisiId());
                    pembayaranUtangPiutangDetailEntity.setPosisiCoa(data.getPosisiCoa());
                    pembayaranUtangPiutangDetailEntity.setPpn(ppn);
                    pembayaranUtangPiutangDetailEntity.setPph(pph);
                    pembayaranUtangPiutangDetailEntity.setNoFakturPajak(data.getNoFakturPajak());
                    pembayaranUtangPiutangDetailEntity.setUrlFakturImage(data.getUrlFakturImage());

                    pembayaranUtangPiutangDetailEntity.setFlag(bean.getFlag());
                    pembayaranUtangPiutangDetailEntity.setAction(bean.getAction());
                    pembayaranUtangPiutangDetailEntity.setCreatedWho(bean.getCreatedWho());
                    pembayaranUtangPiutangDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    pembayaranUtangPiutangDetailEntity.setCreatedDate(bean.getCreatedDate());
                    pembayaranUtangPiutangDetailEntity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // insert into database
                        pembayaranUtangPiutangDetailDao.addAndSave(pembayaranUtangPiutangDetailEntity);
                    } catch (HibernateException e) {
                        logger.error("[PembayaranUtangPiutangBoImpl.saveAdd] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving new data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
                    }
                }
            }

            if (lampiranList!=null){
                for (Lampiran lampiran : lampiranList){
                    ItAkunLampiranEntity lampiranEntity = new ItAkunLampiranEntity();
                    try {
                        BASE64Decoder decoder = new BASE64Decoder();
                        byte[] decodedBytes = new byte[0];
                        decodedBytes = decoder.decodeBuffer(lampiran.getUploadFile());
                        logger.info("Decoded upload data : " + decodedBytes.length);
                        String potNama = lampiran.getNamaLampiran().replace(" ","");
                        if (potNama.length()>20){
                            potNama = potNama.substring(0,20);
                        }
                        String randomNumber = "-"+String.valueOf(CommonUtil.getRandomNumberInts(1,999))+"-";

                        String fileName = potNama+randomNumber+dateFormater("dd")+dateFormater("MM")+dateFormater("yy")+".png";
                        String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_LAMPIRAN+fileName;
                        logger.info("File save path : " + uploadFile);
                        BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                        if (image == null) {
                            logger.error("Buffered Image is null");
                        }else{
                            File f = new File(uploadFile);
                            // write the image
                            ImageIO.write(image, "png", f);
                            lampiranEntity.setUrl(fileName);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String lampiranId =lampiranDao.getNextLampiranId();
                    lampiranEntity.setLampiranId(lampiranId);
                    lampiranEntity.setNamaLaporan(lampiran.getNamaLampiran());
                    lampiranEntity.setTransaksiId(pembayaranUtangPiutangId);

                    lampiranEntity.setFlag(bean.getFlag());
                    lampiranEntity.setAction(bean.getAction());
                    lampiranEntity.setCreatedWho(bean.getCreatedWho());
                    lampiranEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    lampiranEntity.setCreatedDate(bean.getCreatedDate());
                    lampiranEntity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // insert into database
                        lampiranDao.addAndSave(lampiranEntity);
                    } catch (HibernateException e) {
                        logger.error("[PembayaranUtangPiutangBoImpl.saveAdd] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving new data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
                    }
                }
            }
        }
        logger.info("[PembayaranUtangPiutangBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<PembayaranUtangPiutangDetail> getDetailPembayaran(String pembayaranId) throws GeneralBOException {
        logger.info("[PembayaranUtangPiutangBoImpl.getDetailPembayaran] start process >>>");
        List<PembayaranUtangPiutangDetail> listOfResult = new ArrayList<>();

        List<ImPembayaranUtangPiutangDetailEntity> pembayaranUtangPiutangDetailList ;
        try {

            pembayaranUtangPiutangDetailList = pembayaranUtangPiutangDetailDao.getByPembayaranId(pembayaranId);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.getDetailPembayaran] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(pembayaranUtangPiutangDetailList != null){
            PembayaranUtangPiutangDetail returnPembayaranUtangPiutangDetail;
            // Looping from dao to object and save in collection
            for(ImPembayaranUtangPiutangDetailEntity pembayaranUtangPiutangDetail : pembayaranUtangPiutangDetailList){
                returnPembayaranUtangPiutangDetail = new PembayaranUtangPiutangDetail();
                returnPembayaranUtangPiutangDetail.setPembayaranUtangPiutangDetailId(pembayaranUtangPiutangDetail.getPembayaranUtangPiutangDetailId());
                returnPembayaranUtangPiutangDetail.setMasterId(pembayaranUtangPiutangDetail.getMasterId());
                returnPembayaranUtangPiutangDetail.setDivisiId(pembayaranUtangPiutangDetail.getDivisiId());

                List<ImPosition> positionList = positionDao.getListPositionKodering(pembayaranUtangPiutangDetail.getDivisiId());
                if (positionList.size()!=0){
                    for (ImPosition position : positionList){
                        returnPembayaranUtangPiutangDetail.setDivisiName(position.getPositionName());
                    }
                }else{
                    returnPembayaranUtangPiutangDetail.setDivisiName("");
                }

                returnPembayaranUtangPiutangDetail.setRekeningId(pembayaranUtangPiutangDetail.getRekeningId());
                returnPembayaranUtangPiutangDetail.setNoNota(pembayaranUtangPiutangDetail.getNoNota());
                returnPembayaranUtangPiutangDetail.setPosisiCoa(pembayaranUtangPiutangDetail.getPosisiCoa());
                returnPembayaranUtangPiutangDetail.setStJumlahPembayaran(CommonUtil.numbericFormat(pembayaranUtangPiutangDetail.getJumlahPembayaran(),"###,###"));

                if (pembayaranUtangPiutangDetail.getPph()!=null){
                    returnPembayaranUtangPiutangDetail.setStPph(CommonUtil.numbericFormat(pembayaranUtangPiutangDetail.getPph(),"###,###"));
                }
                if (pembayaranUtangPiutangDetail.getPpn()!=null){
                    returnPembayaranUtangPiutangDetail.setStPpn(CommonUtil.numbericFormat(pembayaranUtangPiutangDetail.getPpn(),"###,###"));
                }

                if (pembayaranUtangPiutangDetail.getNoFakturPajak()!=null){
                    returnPembayaranUtangPiutangDetail.setNoFakturPajak(pembayaranUtangPiutangDetail.getNoFakturPajak());
                    returnPembayaranUtangPiutangDetail.setUrlFakturImage(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_FAKTUR_PAJAK+pembayaranUtangPiutangDetail.getUrlFakturImage());
                }else{
                    returnPembayaranUtangPiutangDetail.setNoFakturPajak("");
                    returnPembayaranUtangPiutangDetail.setUrlFakturImage("");
                }

                listOfResult.add(returnPembayaranUtangPiutangDetail);
            }
        }
        logger.info("[PembayaranUtangPiutangBoImpl.getDetailPembayaran] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Lampiran> getLampiranList(String pembayaranId) throws GeneralBOException {
        logger.info("[PembayaranUtangPiutangBoImpl.getLampiranList] start process >>>");
        List<Lampiran> lampiranList= new ArrayList<>();

        List<ItAkunLampiranEntity> lampiranEntityList ;
        try {

            lampiranEntityList = lampiranDao.getByTransaksiId(pembayaranId);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.getLampiranList] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(lampiranEntityList != null){
            // Looping from dao to object and save in collection
            for(ItAkunLampiranEntity data : lampiranEntityList){
                Lampiran returnLampiran = new Lampiran();
                returnLampiran.setNamaLampiran(data.getNamaLaporan());
                returnLampiran.setUploadFile(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_LAMPIRAN+data.getUrl());
                lampiranList.add(returnLampiran);
            }
        }
        logger.info("[PembayaranUtangPiutangBoImpl.getLampiranList] end process <<<");

        return lampiranList;
    }

    @Override
    public String getKodeRekeningKasJurnal(String noJurnal) throws GeneralBOException {
        logger.info("[PembayaranUtangPiutangBoImpl.postingJurnal] start process >>>");
        String rekeningKas="";
        try {
            // Update into database
            rekeningKas= kodeRekeningDao.getKodeRekeningKasForJurnal(noJurnal);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.postingJurnal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving update data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
        }
        logger.info("[PembayaranUtangPiutangBoImpl.postingJurnal] end process <<<");
        return rekeningKas;
    }

    @Override
    public ImTransEntity getTipeMaster(String transId) throws GeneralBOException {
        logger.info("[PembayaranUtangPiutangBoImpl.getTipeMaster] start process >>>");
        ImTransEntity transEntity= new ImTransEntity();
        try {
            transEntity= transDao.getById("transId",transId);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.getTipeMaster] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving update data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
        }
        logger.info("[PembayaranUtangPiutangBoImpl.getTipeMaster] end process <<<");
        return transEntity;
    }

    @Override
    public String getNamaRekeningKasJurnal(String noJurnal) throws GeneralBOException {
        logger.info("[PembayaranUtangPiutangBoImpl.postingJurnal] start process >>>");
        String rekeningKas="";
        try {
            // Update into database
            rekeningKas= kodeRekeningDao.getNamaRekeningKasForJurnal(noJurnal);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.postingJurnal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving update data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
        }
        logger.info("[PembayaranUtangPiutangBoImpl.postingJurnal] end process <<<");
        return rekeningKas;
    }

    @Override
    public Trans getDisableTrans(String transId,String coaLawan) throws GeneralBOException {
        logger.info("[PembayaranUtangPiutangBoImpl.getDisableTrans] start process >>>");
        Trans data = new Trans();

        boolean belumketemu=true;
        List<ImMappingJurnalEntity> mappingJurnalEntityList = new ArrayList<>();
        try {
            do {
                mappingJurnalEntityList = mappingJurnalDao.getListMappingJurnalByTransIdAndKodeRekening(transId,coaLawan);

                if (mappingJurnalEntityList.size()>0){
                    for (ImMappingJurnalEntity mappingJurnalEntity : mappingJurnalEntityList){
                        data.setDivisiId(mappingJurnalEntity.getDivisiId());
                        data.setMasterId(mappingJurnalEntity.getMasterId());
                        data.setNoNota(mappingJurnalEntity.getBukti());
                        data.setBiaya(mappingJurnalEntity.getEditBiaya());
                    }
                    belumketemu=false;
                }
                String rekeningIdParent = kodeRekeningDao.getKodeRekeningParent(coaLawan);
                coaLawan = kodeRekeningDao.getCoaByRekeningId(rekeningIdParent);
            }while (belumketemu);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.getDisableTrans] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving update data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
        }

        logger.info("[PembayaranUtangPiutangBoImpl.getDisableTrans] end process <<<");
        return data;
    }

    @Override
    public String getPosisiCoaDiMappingJurnal(String transId, String kodeRekening) throws GeneralBOException {
        logger.info("[PembayaranUtangPiutangBoImpl.getPosisiCoaDiMappingJurnal] start process >>>");
        String posisiCoa = "";
        boolean belumketemu=true;
        List<ImMappingJurnalEntity> mappingJurnalEntityList = new ArrayList<>();
        try {
            do {
                mappingJurnalEntityList = mappingJurnalDao.getListMappingJurnalByTransIdAndKodeRekening(transId,kodeRekening);

                if (mappingJurnalEntityList.size()>0){
                    belumketemu=false;
                }
                String rekeningIdParent = kodeRekeningDao.getKodeRekeningParent(kodeRekening);
                kodeRekening = kodeRekeningDao.getCoaByRekeningId(rekeningIdParent);
            }while (belumketemu);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.getDisableTrans] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving update data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
        }
        for (ImMappingJurnalEntity mappingJurnalEntity : mappingJurnalEntityList ){
            posisiCoa = mappingJurnalEntity.getPosisi();
        }
        logger.info("[PembayaranUtangPiutangBoImpl.getPosisiCoaDiMappingJurnal] end process <<<");
        return posisiCoa;
    }

    @Override
    public List<Notifikasi> approvePembayaran(PembayaranUtangPiutang bean) throws GeneralBOException {
        logger.info("[PembayaranUtangPiutangBoImpl.approvePembayaran] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();
        List<User> users = new ArrayList<>();
        if (bean!=null) {
            ImPembayaranUtangPiutangEntity imPembayaranUtangPiutangEntity = null;
            try {
                // Get data from database by ID
                imPembayaranUtangPiutangEntity = pembayaranUtangPiutangDao.getById("pembayaranUtangPiutangId", bean.getPembayaranUtangPiutangId());
            } catch (HibernateException e) {
                logger.error("[PembayaranUtangPiutangBoImpl.postingJurnal] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PembayaranUtangPiutang by Kode PembayaranUtangPiutang, please inform to your admin...," + e.getMessage());
            }
            if (imPembayaranUtangPiutangEntity != null) {
                if (bean.getApprovalKeuanganFlag()!=null){
                    imPembayaranUtangPiutangEntity.setApprovalKeuanganFlag(bean.getApprovalKeuanganFlag());
                    imPembayaranUtangPiutangEntity.setApprovalKeuanganId(bean.getApprovalKeuanganId());
                    imPembayaranUtangPiutangEntity.setApprovalKeuanganName(bean.getApprovalKeuanganName());
                    imPembayaranUtangPiutangEntity.setApprovalKeuanganDate(bean.getApprovalKeuanganDate());

                    if ("Y".equalsIgnoreCase(bean.getApprovalKeuanganFlag())){
                        imPembayaranUtangPiutangEntity.setApprovalKasubKeuanganFlag(null);
                        imPembayaranUtangPiutangEntity.setApprovalKasubKeuanganId(null);
                        imPembayaranUtangPiutangEntity.setApprovalKasubKeuanganName(null);
                        imPembayaranUtangPiutangEntity.setApprovalKasubKeuanganDate(null);
                    }

                    //mengirim notif ke kasub keuangan
                    if (imPembayaranUtangPiutangEntity.getApprovalKasubKeuanganFlag()==null){
                        users = userDao.getUserByBranchAndRole(imPembayaranUtangPiutangEntity.getBranchId(),CommonConstant.ROLE_ID_KASUB_KEU);
                    }

                }else if (bean.getApprovalKasubKeuanganFlag()!=null){
                    imPembayaranUtangPiutangEntity.setApprovalKasubKeuanganFlag(bean.getApprovalKasubKeuanganFlag());
                    imPembayaranUtangPiutangEntity.setApprovalKasubKeuanganId(bean.getApprovalKasubKeuanganId());
                    imPembayaranUtangPiutangEntity.setApprovalKasubKeuanganName(bean.getApprovalKasubKeuanganName());
                    imPembayaranUtangPiutangEntity.setApprovalKasubKeuanganDate(bean.getApprovalKasubKeuanganDate());

                    if ("N".equalsIgnoreCase(bean.getApprovalKasubKeuanganFlag())){
                        imPembayaranUtangPiutangEntity.setApprovalKeuanganFlag(null);
                        imPembayaranUtangPiutangEntity.setApprovalKeuanganId(null);
                        imPembayaranUtangPiutangEntity.setApprovalKeuanganName(null);
                        imPembayaranUtangPiutangEntity.setApprovalKeuanganDate(null);
                    }

                    if ("Y".equalsIgnoreCase(bean.getApprovalKasubKeuanganFlag())){
                        users = userDao.getUserByBranchAndRole(imPembayaranUtangPiutangEntity.getBranchId(),CommonConstant.ROLE_ID_KA_KEU);
                    }
                }

                imPembayaranUtangPiutangEntity.setAction(bean.getAction());
                imPembayaranUtangPiutangEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPembayaranUtangPiutangEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    pembayaranUtangPiutangDao.updateAndSave(imPembayaranUtangPiutangEntity);
                } catch (HibernateException e) {
                    logger.error("[PembayaranUtangPiutangBoImpl.postingJurnal] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PembayaranUtangPiutangBoImpl.postingJurnal] Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");
            }
        }
        logger.info("[PembayaranUtangPiutangBoImpl.approvePembayaran] end process <<<");

        return notifikasiList;
    }

    @Override
    public List<PembayaranUtangPiutangDetail> searchPengajuanBiaya(String branchId) throws GeneralBOException {
        logger.info("[PembayaranUtangPiutangBoImpl.searchPengajuanBiaya] start process >>>");
        List<PembayaranUtangPiutangDetail> listOfResult = new ArrayList<>();
        List<PembayaranUtangPiutangDetail> pembayaranUtangPiutangDetailList ;

        try {
            pembayaranUtangPiutangDetailList = pembayaranUtangPiutangDao.searchPengajuanBiaya(branchId);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.searchPengajuanBiaya] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(pembayaranUtangPiutangDetailList != null){
            PembayaranUtangPiutangDetail returnPembayaranUtangPiutangDetail;
            // Looping from dao to object and save in collection
            for(PembayaranUtangPiutangDetail pembayaranUtangPiutangDetail : pembayaranUtangPiutangDetailList){
                returnPembayaranUtangPiutangDetail = new PembayaranUtangPiutangDetail();
                if (pembayaranUtangPiutangDetail.getNoNota()==null){
                    returnPembayaranUtangPiutangDetail.setNoNota("");
                }else{
                    returnPembayaranUtangPiutangDetail.setNoNota(pembayaranUtangPiutangDetail.getNoNota());
                }
                listOfResult.add(returnPembayaranUtangPiutangDetail);
            }
        }
        logger.info("[PembayaranUtangPiutangBoImpl.searchPengajuanBiaya] end process <<<");

        return listOfResult;
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }
}
