package com.neurix.akuntansi.transaksi.kas.bo.impl;

import com.neurix.akuntansi.master.akunMataUang.dao.AkunMataUangDao;
import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.mappingJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;
import com.neurix.akuntansi.master.master.dao.MasterDao;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.master.master.model.MasterPK;
import com.neurix.akuntansi.master.trans.dao.TransDao;
import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.akuntansi.master.trans.model.Trans;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDao;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDetailDao;
import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalEntity;
import com.neurix.akuntansi.transaksi.kas.bo.KasBo;
import com.neurix.akuntansi.transaksi.kas.dao.KasDao;
import com.neurix.akuntansi.transaksi.kas.dao.KasDetailDao;
import com.neurix.akuntansi.transaksi.kas.dao.LampiranDao;
import com.neurix.akuntansi.transaksi.kas.model.*;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.user.dao.UserDao;
import com.neurix.authorization.user.model.User;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class KasBoImpl implements KasBo {

    protected static transient Logger logger = Logger.getLogger(KasBoImpl.class);
    private KasDao kasDao;
    private KasDetailDao kasDetailDao;
    private KodeRekeningDao kodeRekeningDao;
    private JurnalDao jurnalDao;
    private JurnalDetailDao jurnalDetailDao;
    private TransDao transDao;
    private BranchDao branchDao;
    private PositionDao positionDao;
    private MappingJurnalDao mappingJurnalDao;
    private UserDao userDao;
    private LampiranDao lampiranDao;
    private MasterDao masterDao;
//    private PengajuanBiayaRkDao pengajuanBiayaRkDao;

    private AkunMataUangDao akunMataUangDao;

    public void setMasterDao(MasterDao masterDao) {
        this.masterDao = masterDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        KasBoImpl.logger = logger;
    }

    public void setKasDao(KasDao kasDao) {
        this.kasDao = kasDao;
    }

    public void setKasDetailDao(KasDetailDao kasDetailDao) {
        this.kasDetailDao = kasDetailDao;
    }

    public void setKodeRekeningDao(KodeRekeningDao kodeRekeningDao) {
        this.kodeRekeningDao = kodeRekeningDao;
    }

    public void setJurnalDao(JurnalDao jurnalDao) {
        this.jurnalDao = jurnalDao;
    }

    public void setJurnalDetailDao(JurnalDetailDao jurnalDetailDao) {
        this.jurnalDetailDao = jurnalDetailDao;
    }

    public void setTransDao(TransDao transDao) {
        this.transDao = transDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public void setMappingJurnalDao(MappingJurnalDao mappingJurnalDao) {
        this.mappingJurnalDao = mappingJurnalDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setLampiranDao(LampiranDao lampiranDao) {
        this.lampiranDao = lampiranDao;
    }

    public void setAkunMataUangDao(AkunMataUangDao akunMataUangDao) {
        this.akunMataUangDao = akunMataUangDao;
    }


    @Override
    public void saveDelete(Kas bean) throws GeneralBOException {
        logger.info("[KasBoImpl.saveDelete] start process >>>");
        if (bean!=null) {
            ItAkunKasEntity itAkunKasEntity;
            try {
                // Get data from database by ID
                itAkunKasEntity = kasDao.getById("kasId", bean.getKasId());
            } catch (HibernateException e) {
                logger.error("[KasBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data kas, please inform to your admin...," + e.getMessage());
            }

            if (itAkunKasEntity != null) {
                // Modify from bean to entity serializable
                itAkunKasEntity.setFlag(bean.getFlag());
                itAkunKasEntity.setAction(bean.getAction());
                itAkunKasEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itAkunKasEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    kasDao.updateAndSave(itAkunKasEntity);
                } catch (HibernateException e) {
                    logger.error("[KasBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Kas, please info to your admin..." + e.getMessage());
                }

                //Mapping dihapus terlebih dahulu
                List<ItAkunKasDetailEntity> itAkunKasDetailEntityList = kasDetailDao.getListPembayaranUtangPiutangDetailByTipeJurnalId(bean.getKasId());
                for (ItAkunKasDetailEntity kasDetailEntity : itAkunKasDetailEntityList){
                    kasDetailEntity.setFlag("N");
                    kasDetailEntity.setAction(bean.getAction());
                    kasDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    kasDetailEntity.setLastUpdate(bean.getLastUpdate());
                    kasDetailDao.updateAndSave(kasDetailEntity);
                }
            } else {
                logger.error("[KasBoImpl.saveDelete] Error, not found data Kas with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Kas with request id, please check again your data ...");

            }
        }
        logger.info("[KasBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Kas bean) throws GeneralBOException {
        logger.info("[KasBoImpl.saveEdit] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KodeRekening> kodeRekeningList = (List<KodeRekening>) session.getAttribute("listOfResultKodeRekening");

        if (bean!=null) {
            ItAkunKasEntity itAkunKasEntity;
            try {
                // Get data from database by ID
                itAkunKasEntity = kasDao.getById("kasId", bean.getKasId());
            } catch (HibernateException e) {
                logger.error("[KasBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Kas by Kode Kas, please inform to your admin...," + e.getMessage());
            }
            if (itAkunKasEntity != null) {
                itAkunKasEntity.setFlag(bean.getFlag());
                itAkunKasEntity.setAction(bean.getAction());
                itAkunKasEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itAkunKasEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    kasDao.updateAndSave(itAkunKasEntity);
                } catch (HibernateException e) {
                    logger.error("[KasBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Kas, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[KasBoImpl.saveEdit] Error, not found data Kas with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Kas with request id, please check again your data ...");
            }

            //Mapping dihapus terlebih dahulu
            List<ItAkunKasDetailEntity> kasDetailEntityList = kasDetailDao.getListPembayaranUtangPiutangDetailByTipeJurnalId(bean.getKasId());
            for (ItAkunKasDetailEntity kasDetailEntity : kasDetailEntityList){
                kasDetailEntity.setFlag("N");
                kasDetailEntity.setAction(bean.getAction());
                kasDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                kasDetailEntity.setLastUpdate(bean.getLastUpdate());
                kasDetailDao.updateAndSave(kasDetailEntity);
            }

            //Mapping yang baru ditambahkan
            for (KodeRekening kodeRekening : kodeRekeningList){
                ItAkunKasDetailEntity itAkunKasDetailEntity = new ItAkunKasDetailEntity();
                itAkunKasDetailEntity.setFlag(bean.getFlag());
                itAkunKasDetailEntity.setAction(bean.getAction());
                itAkunKasDetailEntity.setCreatedWho(bean.getCreatedWho());
                itAkunKasDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itAkunKasDetailEntity.setCreatedDate(bean.getCreatedDate());
                itAkunKasDetailEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // insert into database
                    kasDetailDao.addAndSave(itAkunKasDetailEntity);
                } catch (HibernateException e) {
                    logger.error("[KasBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving edit data Kas, please info to your admin..." + e.getMessage());
                }
            }
        }
        logger.info("[KasBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Kas saveAdd(Kas bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<Kas> getByCriteria(Kas searchBean) throws GeneralBOException {
        logger.info("[KasBoImpl.getByCriteria] start process >>>");
        // Mapping with collection and put
        List<Kas> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getKasId() != null && !"".equalsIgnoreCase(searchBean.getKasId())) {
                hsCriteria.put("kas_id", searchBean.getKasId());
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
            if (searchBean.getTipeKas() != null && !"".equalsIgnoreCase(searchBean.getTipeKas())) {
                hsCriteria.put("tipe_kas", searchBean.getTipeKas());
            }
            if (searchBean.getBilling() != null && !"".equalsIgnoreCase(searchBean.getBilling())) {
                hsCriteria.put("is_billing", searchBean.getBilling());
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

            List<ItAkunKasEntity> itKasEntity;
            try {
                itKasEntity = kasDao.getByWithUangMuka(hsCriteria); //edit by aji // mencari kas dan uang muka
            } catch (HibernateException e) {
                logger.error("[KasBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itKasEntity != null){
                Kas returnKas;
                // Looping from dao to object and save in collection
                for(ItAkunKasEntity kasEntity : itKasEntity){
                    returnKas = convertEntityToKas(kasEntity);
                    listOfResult.add(returnKas);
                }
            }
        }
        logger.info("[KasBoImpl.getByCriteria] end process <<<");
        return listOfResult;
    }

    private Lampiran convertEntityToLmapiran(ItAkunLampiranEntity itAkunLampiranEntity){
        Lampiran result ;
        try {
            result = new Lampiran();
            result.setFlag(itAkunLampiranEntity.getFlag());
            result.setCreatedWho(itAkunLampiranEntity.getCreatedWho());
            result.setLampiranId(itAkunLampiranEntity.getLampiranId());
            result.setNamaLampiran(itAkunLampiranEntity.getNamaLaporan());
            result.setTransaksiId(itAkunLampiranEntity.getTransaksiId());
            result.setAction(itAkunLampiranEntity.getAction());
            result.setCreatedDate(itAkunLampiranEntity.getCreatedDate());
            result.setUploadFile(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_LAMPIRAN + itAkunLampiranEntity.getUrl());
            result.setLastUpdate(itAkunLampiranEntity.getLastUpdate());
            result.setCreatedWho(itAkunLampiranEntity.getCreatedWho());
            result.setLastUpdateWho(itAkunLampiranEntity.getLastUpdateWho());
        }
        catch (Exception e){
            logger.error("[KasBoImpl.convertEntityToLmapiran] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when convert entity, please info to your admin..." + e.getMessage());
        }
        return result;
    }

    private Kas convertEntityToKas(ItAkunKasEntity kasEntity){
        Kas returnKas = new Kas();
        returnKas.setKasId(kasEntity.getKasId());
        returnKas.setTipeTransaksi(kasEntity.getTipeTransaksi());
        returnKas.setTipeKas(kasEntity.getTipeKas());
        returnKas.setTanggal(kasEntity.getTanggal());
        returnKas.setStTanggal(CommonUtil.convertDateToString(kasEntity.getTanggal()));
        returnKas.setCoaKas(kasEntity.getCoaKas());
        returnKas.setKodeRekening(kasEntity.getCoaKas());
        returnKas.setBayar(kasEntity.getBayar());
        returnKas.setStBayar(CommonUtil.numbericFormatIndo(kasEntity.getBayar()));
        returnKas.setCurrencyId(kasEntity.getCurrencyId());
        returnKas.setKeterangan(kasEntity.getKeterangan());
        returnKas.setNoSlipBank(kasEntity.getNoSlipBank());
        returnKas.setBranchId(kasEntity.getBranchId());
        returnKas.setMetodePembayaran(kasEntity.getMetodePembayaran());
        returnKas.setApprovalKeuanganFlag(kasEntity.getApprovalKeuanganFlag());
        returnKas.setApprovalKeuanganId(kasEntity.getApprovalKeuanganId());
        returnKas.setApprovalKeuanganName(kasEntity.getApprovalKeuanganName());
        returnKas.setApprovalKeuanganDate(kasEntity.getApprovalKeuanganDate());
        if (kasEntity.getApprovalKeuanganDate()!=null){
            returnKas.setStApprovalKeuanganDate(CommonUtil.convertTimestampToStringLengkap(kasEntity.getApprovalKeuanganDate()));
        }
        returnKas.setApprovalKasubKeuanganFlag(kasEntity.getApprovalKasubKeuanganFlag());
        returnKas.setApprovalKasubKeuanganId(kasEntity.getApprovalKasubKeuanganId());
        returnKas.setApprovalKasubKeuanganName(kasEntity.getApprovalKasubKeuanganName());
        returnKas.setApprovalKasubKeuanganDate(kasEntity.getApprovalKasubKeuanganDate());
        if (kasEntity.getApprovalKasubKeuanganDate()!=null){
            returnKas.setStApprovalKasubKeuanganDate(CommonUtil.convertTimestampToStringLengkap(kasEntity.getApprovalKasubKeuanganDate()));
        }

        List<ImBranches> branchesList = branchDao.getListBranchById(kasEntity.getBranchId());
        for (ImBranches branches : branchesList){
            returnKas.setBranchName(branches.getBranchName());
        }

        String roleId = CommonUtil.roleIdAsLogin();
        String branchIdUser = CommonUtil.userBranchLogin();

        if (branchIdUser.equalsIgnoreCase(kasEntity.getBranchId())){
            if (CommonConstant.ROLE_ID_ADMIN_AKS.equalsIgnoreCase(roleId)){
                returnKas.setJabatan("keu");
            }else if (CommonConstant.ROLE_ID_KASUB_KEU.equalsIgnoreCase(roleId)){
                if ("Y".equalsIgnoreCase(kasEntity.getApprovalKeuanganFlag())){
                    returnKas.setJabatan("kasub");
                }
            }else if (CommonConstant.ROLE_ID_KA_KEU.equalsIgnoreCase(roleId)){
                if ("Y".equalsIgnoreCase(kasEntity.getApprovalKasubKeuanganFlag())) {
                    returnKas.setJabatan("ka");
                }
            }
        }

        returnKas.setNoJurnal(kasEntity.getNoJurnal());
        returnKas.setTipeKas(kasEntity.getTipeKas());

        if (kasEntity.getRegisteredFlag()!=null){
            if (("Y").equalsIgnoreCase(kasEntity.getRegisteredFlag())){
                returnKas.setFlagPosting(true);
            }
        }
        if (kasEntity.getTipeTransaksi()!=null){
            try {
                ImTransEntity  transEntity = transDao.getById("transId",kasEntity.getTipeTransaksi());
                returnKas.setStTipeTransaksi(transEntity != null ? transEntity.getTransName() : "Uang Muka");
                returnKas.setStBilling(transEntity != null ? "Non Billing" : "Billing");
            } catch (HibernateException e) {
                logger.error("[KasBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
        }
        returnKas.setNamaKas(kodeRekeningDao.getNamaRekeningByCoa(kasEntity.getCoaKas()));

        returnKas.setRegisteredFlag(kasEntity.getRegisteredFlag());
        returnKas.setRegisteredWho(kasEntity.getRegisteredWho());
        returnKas.setRegisteredDate(kasEntity.getRegisteredDate());
        if (kasEntity.getRegisteredDate()!=null){
            returnKas.setStRegisteredDate(CommonUtil.convertTimestampToStringLengkap(kasEntity.getRegisteredDate()));
        }
        returnKas.setCreatedWho(kasEntity.getCreatedWho());
        returnKas.setCreatedDate(kasEntity.getCreatedDate());
        returnKas.setLastUpdate(kasEntity.getLastUpdate());
        returnKas.setAction(kasEntity.getAction());
        returnKas.setFlag(kasEntity.getFlag());
        return returnKas;
    }

    @Override
    public List<KasDetail> getSearchNotaKas(String masterId, String transaksiId, String branchId, String divisiId, String coa) throws GeneralBOException {
        logger.info("[KasBoImpl.getSearchNotaKas] start process >>>");
        List<KasDetail> listOfResult = new ArrayList<>();
        String unit="";
        if ((CommonConstant.BRANCH_KP).equalsIgnoreCase(branchId)){
            List<ImBranches> branchList = new ArrayList<>();
            branchList = branchDao.getAllBranch();
            int i = 1;
            for (ImBranches dataUnit : branchList){
                if (i==1){
                    unit="'"+dataUnit.getPrimaryKey().getId()+"'";
                }else{
                    unit=unit+",'"+dataUnit.getPrimaryKey().getId()+"'";
                }
                i++;
            }
        }else{
            unit="'"+branchId+"'";
        }
        List<KasDetail> kasDetailList;

        try {
            String tipeJurnal = transDao.getTipeBayarByTransId(transaksiId);
            String rekeningId = kodeRekeningDao.getRekeningIdByCoa(coa);
            kasDetailList = kasDao.getSearchNotaKas(masterId,unit,tipeJurnal,divisiId,coa);
        } catch (HibernateException e) {
            logger.error("[KasBoImpl.getSearchNotaKas] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data nota kas, please info to your admin..." + e.getMessage());
        }

        if(kasDetailList != null){
            KasDetail returnKasDetail;
            // Looping from dao to object and save in collection
            for(KasDetail kasDetail : kasDetailList){
                returnKasDetail = new KasDetail();
                returnKasDetail.setRekeningId(kasDetail.getRekeningId());
                String jumlahPembayaran = kasDetail.getStJumlahPembayaran().replaceAll("[^\\d.]", "");
                returnKasDetail.setStJumlahPembayaran(kasDetail.getStJumlahPembayaran().replace(",","."));  // perlu dibuat function - note by aji
                returnKasDetail.setStJumlahPembayaran(returnKasDetail.getStJumlahPembayaran().replace(" ",""));
                returnKasDetail.setStJumlahPembayaran(returnKasDetail.getStJumlahPembayaran().replace("-",""));

                if (kasDetail.getDivisiId()==null){
                    returnKasDetail.setDivisiId("");
                }else{
                    returnKasDetail.setDivisiId(kasDetail.getDivisiId());
                }
                if (kasDetail.getNoNota()==null){
                    returnKasDetail.setNoNota("");
                }else{
                    returnKasDetail.setNoNota(kasDetail.getNoNota());
                }
                if (kasDetail.getMasterId()==null){
                    returnKasDetail.setMasterId("");
                }else{
                    returnKasDetail.setMasterId(kasDetail.getMasterId());
                }
                listOfResult.add(returnKasDetail);
            }
        }
        logger.info("[KasBoImpl.getSearchNotaKas] end process <<<");

        return listOfResult;
    }

    @Override
    public void postingJurnal(Kas bean) throws GeneralBOException {
        logger.info("[KasBoImpl.postingJurnal] start process >>>");
        if (bean!=null) {
            ItAkunKasEntity itAkunKasEntity;
            try {
                // Get data from database by ID
                itAkunKasEntity = kasDao.getById("kasId", bean.getKasId());
            } catch (HibernateException e) {
                logger.error("[KasBoImpl.postingJurnal] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Kas by Kode Kas, please inform to your admin...," + e.getMessage());
            }
            if (itAkunKasEntity != null) {
                itAkunKasEntity.setRegisteredFlag("Y");
                itAkunKasEntity.setRegisteredDate(bean.getRegisteredDate());
                itAkunKasEntity.setRegisteredWho(bean.getLastUpdateWho());

                itAkunKasEntity.setFlag(bean.getFlag());
                itAkunKasEntity.setAction(bean.getAction());
                itAkunKasEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itAkunKasEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    kasDao.updateAndSave(itAkunKasEntity);
                } catch (HibernateException e) {
                    logger.error("[KasBoImpl.postingJurnal] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Kas, please info to your admin..." + e.getMessage());
                }

                //update flag jurnal telah posting
                ItJurnalEntity jurnalEntity = jurnalDao.getById("noJurnal",itAkunKasEntity.getNoJurnal());
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
                    logger.error("[KasBoImpl.postingJurnal] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Kas, please info to your admin..." + e.getMessage());
                }

//                // JIKA PEMBAYARAN DO MAKA SET PEMBAYARAN DO KE SUDAH DIBAYAR
//                List<ItAkunKasDetailEntity> pembayaranUtangPiutangDetailEntities = kasDetailDao.getByAkunKoreksiId(bean.getPembayaranUtangPiutangId());
//                for (ItAkunKasDetailEntity pembayaranUtangPiutangDetailEntity : pembayaranUtangPiutangDetailEntities){
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
                logger.error("[KasBoImpl.postingJurnal] Error, not found data Kas with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Kas with request id, please check again your data ...");
            }
        }
        logger.info("[KasBoImpl.postingJurnal] end process <<<");
    }

    @Override
    public void addPrintCount(String noJurnal) throws GeneralBOException {
        logger.info("[KasBoImpl.addPrintCount] start process >>>");
        ItJurnalEntity jurnalEntity;
        try {
            // Update into database
            jurnalEntity=jurnalDao.getById("noJurnal",noJurnal);
            jurnalEntity.setPrintRegisterCount(jurnalEntity.getPrintRegisterCount().add(new BigDecimal(1)));

        } catch (HibernateException e) {
            logger.error("[KasBoImpl.addPrintCount] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when print data Kas, please info to your admin..." + e.getMessage());
        }

        try {
            // Update into database
            jurnalDao.updateAndSave(jurnalEntity);
        } catch (HibernateException e) {
            logger.error("[KasBoImpl.addPrintCount] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when print data Kas, please info to your admin..." + e.getMessage());
        }

        logger.info("[KasBoImpl.postingJurnal] end process <<<");
    }

    @Override
    public List<Kas> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public String saveAddKas(Kas bean, List<KasDetail> kasDetailList, List<Lampiran> lampiranList) throws GeneralBOException {
        logger.info("[KasBoImpl.saveAddKas] start process >>>");

        if (bean!=null) {
            String kasId;
            try {
                // Generating ID, get from postgre sequence
                kasId = kasDao.getNextKasId();
            } catch (HibernateException e) {
                logger.error("[KasBoImpl.saveAddKas] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence kasId id, please info to your admin..." + e.getMessage());
            }
            // creating object entity serializable
            ItAkunKasEntity itAkunKasEntity = new ItAkunKasEntity();
            itAkunKasEntity.setKasId(kasId);
            itAkunKasEntity.setTipeTransaksi(bean.getTipeTransaksi());
            itAkunKasEntity.setTanggal(bean.getTanggal());
            itAkunKasEntity.setCoaKas(bean.getCoaKas());
            itAkunKasEntity.setBayar(bean.getBayar());
            itAkunKasEntity.setKeterangan(bean.getKeterangan());
            itAkunKasEntity.setNoSlipBank(bean.getNoSlipBank());
            itAkunKasEntity.setBranchId(bean.getBranchId());
            itAkunKasEntity.setNoJurnal(bean.getNoJurnal());
            itAkunKasEntity.setTipeKas(bean.getTipeKas());
            itAkunKasEntity.setMetodePembayaran(bean.getMetodePembayaran());
            itAkunKasEntity.setCurrencyId(bean.getCurrencyId());
            itAkunKasEntity.setFlag(bean.getFlag());
            itAkunKasEntity.setAction(bean.getAction());
            itAkunKasEntity.setCreatedWho(bean.getCreatedWho());
            itAkunKasEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itAkunKasEntity.setCreatedDate(bean.getCreatedDate());
            itAkunKasEntity.setLastUpdate(bean.getLastUpdate());

            //langsung diapprove bawahan apabila yang membuat adalah atasan
            if (CommonConstant.ROLE_ID_KASUB_KEU.equalsIgnoreCase(CommonUtil.roleIdAsLogin())||CommonConstant.ROLE_ID_KA_KEU.equalsIgnoreCase(CommonUtil.roleIdAsLogin())){
                itAkunKasEntity.setApprovalKeuanganId(CommonUtil.userIdLogin());
                itAkunKasEntity.setApprovalKeuanganDate(bean.getLastUpdate());
                itAkunKasEntity.setApprovalKeuanganName(bean.getCreatedWho());
                itAkunKasEntity.setApprovalKeuanganFlag("Y");
            }

            if (CommonConstant.ROLE_ID_KA_KEU.equalsIgnoreCase(CommonUtil.roleIdAsLogin())) {
                itAkunKasEntity.setApprovalKasubKeuanganId(CommonUtil.userIdLogin());
                itAkunKasEntity.setApprovalKasubKeuanganDate(bean.getLastUpdate());
                itAkunKasEntity.setApprovalKasubKeuanganName(bean.getCreatedWho());
                itAkunKasEntity.setApprovalKasubKeuanganFlag("Y");
            }

            try {
                // insert into database
                kasDao.addAndSave(itAkunKasEntity);
            } catch (HibernateException e) {
                logger.error("[KasBoImpl.saveAddKas] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Kas, please info to your admin..." + e.getMessage());
            }

            if (kasDetailList !=null){
                for (KasDetail data : kasDetailList){
                    BigDecimal jumlahPembayaran = new BigDecimal(data.getStJumlahPembayaran().replace(".",""));
                    BigDecimal ppn = BigDecimal.ZERO;
                    BigDecimal pph = BigDecimal.ZERO;
                    if ("Pengajuan Biaya".equalsIgnoreCase(bean.getTipeMaster())) {             //sepertinya akan dipindah - note by aji
                        ppn = new BigDecimal(data.getStPpn().replace(".",""));
                        pph = new BigDecimal(data.getStPph().replace(".",""));
                    }
                    ItAkunKasDetailEntity itAkunKasDetailEntity = new ItAkunKasDetailEntity();
                    String kasDetailId = kasDetailDao.getNextKasDetailId();
                    itAkunKasDetailEntity.setKasDetailId(kasDetailId);
                    itAkunKasDetailEntity.setKasId(kasId);
                    itAkunKasDetailEntity.setMasterId(data.getMasterId());
                    itAkunKasDetailEntity.setNoNota(data.getNoNota());
                    itAkunKasDetailEntity.setKodeCoa(data.getRekeningId());
                    itAkunKasDetailEntity.setJumlahPembayaran(jumlahPembayaran);
                    itAkunKasDetailEntity.setDivisiId(data.getDivisiId());
                    itAkunKasDetailEntity.setPpn(ppn);
                    itAkunKasDetailEntity.setPph(pph);
                    itAkunKasDetailEntity.setNoFakturPajak(data.getNoFakturPajak());

                    try {
                        if(data.getStFileUpload() != null && !data.getStFileUpload().isEmpty()){

                            BASE64Decoder decoder = new BASE64Decoder();                            //butuh fungsi save upload- note by aji
                            byte[] decodedBytes = decoder.decodeBuffer(data.getStFileUpload());
                            logger.info("Decoded upload data : " + decodedBytes.length);

                            String fileName = data.getNoFakturPajak() + "-" + dateFormater("MM") + dateFormater("yy") + ".png";
                            String pathFolder = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_FAKTUR_PAJAK;
                            String uploadFile = pathFolder + fileName;
                            Path path = Paths.get(pathFolder);
                            if (!Files.exists(path)) {
                                File file = new File(pathFolder);
                                file.mkdirs();
                            }

                            logger.info("File save path : " + uploadFile);
                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                            if (image == null) {
                                logger.error("Buffered Image is null");
                            } else {
                                File f = new File(uploadFile);
                                // write the image
                                ImageIO.write(image, "png", f);
                                itAkunKasDetailEntity.setUrlFakturImage(fileName);
                            }
                        }
                    }catch (Exception e){
                        String error = "ERROR : "+e.getMessage();
                        logger.error(error);
                        throw new GeneralBOException(error);
                    }

                    itAkunKasDetailEntity.setFlag(bean.getFlag());
                    itAkunKasDetailEntity.setAction(bean.getAction());
                    itAkunKasDetailEntity.setCreatedWho(bean.getCreatedWho());
                    itAkunKasDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    itAkunKasDetailEntity.setCreatedDate(bean.getCreatedDate());
                    itAkunKasDetailEntity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // insert into database
                        kasDetailDao.addAndSave(itAkunKasDetailEntity);
                    } catch (HibernateException e) {
                        logger.error("[KasBoImpl.saveAddKas] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving new data Kas, please info to your admin..." + e.getMessage());
                    }
                }
            }

            if (lampiranList!=null){
                for (Lampiran lampiran : lampiranList){
                    ItAkunLampiranEntity lampiranEntity = new ItAkunLampiranEntity();
                    try {
                        if(lampiran.getUploadFile() != null && !lampiran.getUploadFile().isEmpty()) {

                            BASE64Decoder decoder = new BASE64Decoder();
                            byte[] decodedBytes;
                            decodedBytes = decoder.decodeBuffer(lampiran.getUploadFile());
                            logger.info("Decoded upload data : " + decodedBytes.length);
                            String potNama = lampiran.getNamaLampiran().replace(" ", "");
                            if (potNama.length() > 20) {
                                potNama = potNama.substring(0, 20);
                            }
                            String randomNumber = "-" + String.valueOf(CommonUtil.getRandomNumberInts(1, 999)) + "-";

                            String fileName = potNama + randomNumber + dateFormater("dd") + dateFormater("MM") + dateFormater("yy") + ".png";
                            String folder = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_LAMPIRAN;
                            String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_LAMPIRAN + fileName;
                            Path path = Paths.get(folder);
                            if (!Files.exists(path)) {
                                File file = new File(folder);
                                file.mkdirs();
                            }
                            logger.info("File save path : " + uploadFile);
                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                            if (image == null) {
                                logger.error("Buffered Image is null");
                            } else {
                                File f = new File(uploadFile);
                                // write the image
                                ImageIO.write(image, "png", f);
                                lampiranEntity.setUrl(fileName);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String lampiranId =lampiranDao.getNextLampiranId();
                    lampiranEntity.setLampiranId(lampiranId);
                    lampiranEntity.setNamaLaporan(lampiran.getNamaLampiran());
                    lampiranEntity.setTransaksiId(kasId);

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
                        logger.error("[KasBoImpl.saveAddKas] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving new data Kas, please info to your admin..." + e.getMessage());
                    }
                }
            }
        }
        logger.info("[KasBoImpl.saveAddKas] end process <<<");
        return null;
    }

    @Override
    public String saveEditKas(Kas bean, List<KasDetail> kasDetailList, List<Lampiran> lampiranList) throws GeneralBOException {
//        Kas kas = saveEditKas(bean);
        logger.info("[KasBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            ItAkunKasEntity itAkunKasEntity;
            try {
                // Get data from database by ID
                itAkunKasEntity = kasDao.getById("kasId", bean.getKasId());
            } catch (HibernateException e) {
                logger.error("[KasBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Kas by Kode Kas, please inform to your admin...," + e.getMessage());
            }
            if (itAkunKasEntity != null) {
                itAkunKasEntity.setMetodePembayaran(bean.getMetodePembayaran());
                itAkunKasEntity.setTanggal(CommonUtil.convertToDate(bean.getStTanggal()));
                itAkunKasEntity.setBayar(CommonUtil.StringDenganFormatToBigDecimal(bean.getStBayar()));
                itAkunKasEntity.setKeterangan(bean.getKeterangan());
                itAkunKasEntity.setNoSlipBank(bean.getNoSlipBank());
                itAkunKasEntity.setCoaKas(bean.getKodeRekening());

                itAkunKasEntity.setFlag(bean.getFlag());
                itAkunKasEntity.setAction(bean.getAction());
                itAkunKasEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itAkunKasEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    kasDao.updateAndSave(itAkunKasEntity);
                } catch (HibernateException e) {
                    logger.error("[KasBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Kas, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[KasBoImpl.saveEdit] Error, not found data Kas with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Kas with request id, please check again your data ...");
            }

            for (KasDetail kasDetailEntity : kasDetailList) {
                ItAkunKasDetailEntity itAkunKasDetailEntity = kasDetailDao.getById("kasDetailId", kasDetailEntity.getKasDetailId());
                // update or Delete
                if (itAkunKasDetailEntity != null) {
                    itAkunKasDetailEntity.setKodeCoa(kasDetailEntity.getRekeningId());
                    itAkunKasDetailEntity.setDivisiId(kasDetailEntity.getDivisiId());
                    itAkunKasDetailEntity.setMasterId(kasDetailEntity.getMasterId());
                    itAkunKasDetailEntity.setNoNota(kasDetailEntity.getNoNota());
                    itAkunKasDetailEntity.setJumlahPembayaran(CommonUtil.StringDenganFormatToBigDecimal(kasDetailEntity.getStJumlahPembayaran()));

                    itAkunKasDetailEntity.setFlag(kasDetailEntity.getFlag());
                    itAkunKasDetailEntity.setAction(bean.getAction());
                    itAkunKasDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    itAkunKasDetailEntity.setLastUpdate(bean.getLastUpdate());
                    try {
                        // insert into database
                        kasDetailDao.updateAndSave(itAkunKasDetailEntity);
                    } catch (HibernateException e) {
                        logger.error("[KasBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving edit data Kas, please info to your admin..." + e.getMessage());
                    }
                } else {
//                    create baru
                    String kasDetailId = kasDetailDao.getNextKasDetailId();
                    itAkunKasDetailEntity = new ItAkunKasDetailEntity();
                    itAkunKasDetailEntity.setKasDetailId(kasDetailId);
                    itAkunKasDetailEntity.setKasId(kasDetailEntity.getKasId());
                    itAkunKasDetailEntity.setNoFakturPajak(kasDetailEntity.getNoFakturPajak());
                    itAkunKasDetailEntity.setUrlFakturImage(kasDetailEntity.getUrlFakturImage());

                    itAkunKasDetailEntity.setKodeCoa(kasDetailEntity.getRekeningId());
                    itAkunKasDetailEntity.setDivisiId(kasDetailEntity.getDivisiId());
                    itAkunKasDetailEntity.setMasterId(kasDetailEntity.getMasterId());
                    itAkunKasDetailEntity.setNoNota(kasDetailEntity.getNoNota());
                    itAkunKasDetailEntity.setJumlahPembayaran(CommonUtil.StringDenganFormatToBigDecimal(kasDetailEntity.getStJumlahPembayaran()));
                    itAkunKasDetailEntity.setPph(kasDetailEntity.getStPph() == null ? BigDecimal.ZERO : CommonUtil.StringDenganFormatToBigDecimal(kasDetailEntity.getStPph()));
                    itAkunKasDetailEntity.setPpn(kasDetailEntity.getStPpn() == null ? BigDecimal.ZERO : CommonUtil.StringDenganFormatToBigDecimal(kasDetailEntity.getStPpn()));

                    itAkunKasDetailEntity.setFlag(kasDetailEntity.getFlag());
                    itAkunKasDetailEntity.setAction(kasDetailEntity.getAction());
                    itAkunKasDetailEntity.setCreatedDate(kasDetailEntity.getCreatedDate());
                    itAkunKasDetailEntity.setCreatedWho(kasDetailEntity.getCreatedWho());
                    try {
                        // insert into database
                        kasDetailDao.addAndSave(itAkunKasDetailEntity);
                    } catch (HibernateException e) {
                        logger.error("[KasBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving edit data Kas, please info to your admin..." + e.getMessage());
                    }
                }
            }
                    //////////////////////////////////////////////Lampiran/////////////////////////////////////////////////////
            for (Lampiran lampiranEntity : lampiranList) {
                ItAkunLampiranEntity itLampiranEntity = lampiranDao.getById("lampiranId", lampiranEntity.getLampiranId());
                // update or Delete
                if (itLampiranEntity != null) {
                    itLampiranEntity.setNamaLaporan(lampiranEntity.getNamaLampiran());
                    itLampiranEntity.setFlag(lampiranEntity.getFlag());
                    itLampiranEntity.setAction(bean.getAction());
                    itLampiranEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    try {
                        String url = CommonUtil.uploadImage(lampiranEntity);
                        if(url != null) {
                            itLampiranEntity.setUrl(url);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        logger.error("[KasBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving edit data Kas, please info to your admin..." + e.getMessage());
                    }
                    itLampiranEntity.setLastUpdate(bean.getLastUpdate());
                    try {
                        // update into database
                        lampiranDao.updateAndSave(itLampiranEntity);
                    } catch (HibernateException e) {
                        logger.error("[KasBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving edit data Kas, please info to your admin..." + e.getMessage());
                    }
                }
                else {
//                    create baru
                    String lampiranId = lampiranDao.getNextLampiranId();
                    itLampiranEntity = new ItAkunLampiranEntity();
                    itLampiranEntity.setLampiranId(lampiranId);
                    itLampiranEntity.setTransaksiId(lampiranEntity.getTransaksiId());
                    itLampiranEntity.setFlag(lampiranEntity.getFlag());
                    itLampiranEntity.setNamaLaporan(lampiranEntity.getNamaLampiran());
                    itLampiranEntity.setFlag(lampiranEntity.getFlag());
                    itLampiranEntity.setAction(lampiranEntity.getAction());
                    itLampiranEntity.setCreatedDate(lampiranEntity.getCreatedDate());
                    itLampiranEntity.setCreatedWho(lampiranEntity.getCreatedWho());
                    try {
                        String url = CommonUtil.uploadImage(lampiranEntity);
                        if(url!=null){
                            itLampiranEntity.setUrl(url);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        logger.error("[KasBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving edit data Kas, please info to your admin..." + e.getMessage());
                    }
                    try {
                        // insert into database
                        if(itLampiranEntity.getUrl() != null){
                            lampiranDao.addAndSave(itLampiranEntity);
                        }
                    } catch (HibernateException e) {
                        logger.error("[KasBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving edit data Kas, please info to your admin..." + e.getMessage());
                    }
                }
            }
        }
        logger.info("[KasBoImpl.saveEdit] end process <<<");
        return null;
    }

    /*@Override
    public Kas saveEditKas(Kas bean) throws GeneralBOException {
        // creating object entity serializable
        ItAkunKasEntity itAkunKasEntity = kasDao.getById("kasId",bean.getKasId());
        if(itAkunKasEntity != null){
            itAkunKasEntity.setTipeTransaksi(bean.getTipeTransaksi());
            itAkunKasEntity.setTanggal(bean.getTanggal());
            itAkunKasEntity.setCoaKas(bean.getCoaKas());
            itAkunKasEntity.setBayar(bean.getBayar());
            itAkunKasEntity.setKeterangan(bean.getKeterangan());
            itAkunKasEntity.setNoSlipBank(bean.getNoSlipBank());
            itAkunKasEntity.setBranchId(bean.getBranchId());
            itAkunKasEntity.setNoJurnal(bean.getNoJurnal());
            itAkunKasEntity.setTipeKas(bean.getTipeKas());
            itAkunKasEntity.setMetodePembayaran(bean.getMetodePembayaran());

            itAkunKasEntity.setFlag(bean.getFlag());
            itAkunKasEntity.setAction(bean.getAction());
            itAkunKasEntity.setCreatedWho(bean.getCreatedWho());
            itAkunKasEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itAkunKasEntity.setCreatedDate(bean.getCreatedDate());
            itAkunKasEntity.setLastUpdate(bean.getLastUpdate());
        }


        //langsung diapprove bawahan apabila yang membuat adalah atasan
        if (CommonConstant.ROLE_ID_KASUB_KEU.equalsIgnoreCase(CommonUtil.roleIdAsLogin())||CommonConstant.ROLE_ID_KA_KEU.equalsIgnoreCase(CommonUtil.roleIdAsLogin())){
            itAkunKasEntity.setApprovalKeuanganId(CommonUtil.userIdLogin());
            itAkunKasEntity.setApprovalKeuanganDate(bean.getLastUpdate());
            itAkunKasEntity.setApprovalKeuanganName(bean.getCreatedWho());
            itAkunKasEntity.setApprovalKeuanganFlag("Y");
        }

        if (CommonConstant.ROLE_ID_KA_KEU.equalsIgnoreCase(CommonUtil.roleIdAsLogin())) {
            itAkunKasEntity.setApprovalKasubKeuanganId(CommonUtil.userIdLogin());
            itAkunKasEntity.setApprovalKasubKeuanganDate(bean.getLastUpdate());
            itAkunKasEntity.setApprovalKasubKeuanganName(bean.getCreatedWho());
            itAkunKasEntity.setApprovalKasubKeuanganFlag("Y");
        }

        try {
            // insert into database
            kasDao.addAndSave(itAkunKasEntity);
        } catch (HibernateException e) {
            logger.error("[KasBoImpl.saveAddKas] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving new data Kas, please info to your admin..." + e.getMessage());
        }
        return null;
    }*/

    @Override
    public List<KasDetail> getKasDetail(String kasId) throws GeneralBOException {
        logger.info("[KasBoImpl.getKasDetail] start process >>>");
        List<KasDetail> listOfResult = new ArrayList<>();

        List<ItAkunKasDetailEntity> kasDetailList;
        try {
            kasDetailList = kasDetailDao.getByAkunKasId(kasId);
        } catch (HibernateException e) {
            logger.error("[KasBoImpl.getKasDetail] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by kas id, please info to your admin..." + e.getMessage());
        }

        if(kasDetailList != null){
            KasDetail returnKasDetail;
            // Looping from dao to object and save in collection
            for(ItAkunKasDetailEntity akunKasDetail : kasDetailList){
                returnKasDetail = new KasDetail();
                returnKasDetail.setKasDetailId(akunKasDetail.getKasDetailId());
                returnKasDetail.setMasterId(akunKasDetail.getMasterId());
                if(akunKasDetail.getMasterId() != null)
                {
                    ImMasterEntity imMasterEntity = null;
                    try {
                        imMasterEntity = masterDao.getById("primaryKey.nomorMaster","Y");
                    }catch (Exception e){
                        logger.error("[KasBoImpl.getKasDetail] Error, " + e.getMessage());
                    }
                    if(imMasterEntity != null){
                        returnKasDetail.setMasterName(imMasterEntity.getNama());
                    }
                }
                String namaRekening = "";
                try {
                    namaRekening = kodeRekeningDao.getNamaRekeningByCoa(akunKasDetail.getKodeCoa());
                    if(namaRekening != null){
                        returnKasDetail.setNamaRekening(namaRekening);
                        returnKasDetail.setDivisiId(akunKasDetail.getDivisiId());
                    }
                }
                catch (Exception e){
                    logger.error("[KasBoImpl.getKasDetail] Error, " + e.getMessage());
                }

                List<ImPosition> positionList = positionDao.getListPositionKodering(akunKasDetail.getDivisiId() != null ? akunKasDetail.getDivisiId() : "");
                if (positionList.size()!=0){
                    for (ImPosition position : positionList){
                        returnKasDetail.setDivisiName(position.getPositionName());
                    }
                }else{
                    returnKasDetail.setDivisiName("");
                }

                String posisi = akunKasDetail.getDivisiId().isEmpty() ? "K":"D";
                returnKasDetail.setRekeningId(akunKasDetail.getKodeCoa());
                returnKasDetail.setNoNota(akunKasDetail.getNoNota());
                returnKasDetail.setPosisiCoa(posisi); //unutk Koreksi
                returnKasDetail.setStJumlahPembayaran(CommonUtil.numbericFormatIndo(akunKasDetail.getJumlahPembayaran()));

                if (akunKasDetail.getPph()!=null){
                    returnKasDetail.setStPph(CommonUtil.numbericFormatIndo(akunKasDetail.getPph()));
                }
                if (akunKasDetail.getPpn()!=null){
                    returnKasDetail.setStPpn(CommonUtil.numbericFormatIndo(akunKasDetail.getPpn()));
                }

                if (akunKasDetail.getNoFakturPajak()!=null){
                    returnKasDetail.setNoFakturPajak(akunKasDetail.getNoFakturPajak());
                    returnKasDetail.setUrlFakturImage(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_FAKTUR_PAJAK+akunKasDetail.getUrlFakturImage());
                }else{
                    returnKasDetail.setNoFakturPajak("");
                    returnKasDetail.setUrlFakturImage("");
                }

                listOfResult.add(returnKasDetail);
            }
        }
        logger.info("[KasBoImpl.getKasDetail] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Lampiran> getLampiranList(String kasId) throws GeneralBOException {
        logger.info("[KasBoImpl.getLampiranList] start process >>>");
        List<Lampiran> lampiranList= new ArrayList<>();

        List<ItAkunLampiranEntity> lampiranEntityList ;
        try {

            lampiranEntityList = lampiranDao.getByTransaksiId(kasId);
        } catch (HibernateException e) {
            logger.error("[KasBoImpl.getLampiranList] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when get lampiran, please info to your admin..." + e.getMessage());
        }

        if(lampiranEntityList != null){
            // Looping from dao to object and save in collection
            for(ItAkunLampiranEntity data : lampiranEntityList){
                Lampiran returnLampiran = new Lampiran();
                returnLampiran.setLampiranId(data.getLampiranId());
                returnLampiran.setAction(data.getAction());
                returnLampiran.setFlag(data.getFlag());
                returnLampiran.setCreatedDate(data.getCreatedDate());
                returnLampiran.setCreatedWho(data.getCreatedWho());
                returnLampiran.setNamaLampiran(data.getNamaLaporan());
                returnLampiran.setUploadFile(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_LAMPIRAN+data.getUrl());
                lampiranList.add(returnLampiran);
            }
        }
        logger.info("[KasBoImpl.getLampiranList] end process <<<");

        return lampiranList;
    }

    @Override
    public Map<String, Lampiran> getListLampiran(String transactionId) {
        logger.info("[KasDetailBoImpl.getListLampiran] start process >>>");
        Map<String, Lampiran> resultList = new HashMap<>();
        List<ItAkunLampiranEntity> detailEntityList;
        try {
            detailEntityList = lampiranDao.getByTransaksiId(transactionId);
        } catch (Exception e) {
            logger.error("[KoreksiDetailBoImpl.getListLampiran] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data MappingJurnal by transaksiId , please inform to your admin...," + e.getMessage());
        }
        if (detailEntityList.size() > 0) {
            for (ItAkunLampiranEntity itAkunKasDetailEntity : detailEntityList) {
                Lampiran lampiran = convertEntityToLmapiran(itAkunKasDetailEntity);
                resultList.put(lampiran.getLampiranId(), lampiran);
            }
        }
        logger.info("[MappingJurnalBoImpl.getListLampiran] end process <<<");
        return resultList;
    }

    @Override
    public String getKodeRekeningKasJurnal(String noJurnal) throws GeneralBOException {
        logger.info("[KasBoImpl.getKodeRekeningKasJurnal] start process >>>");
        String rekeningKas="";
        try {
            // Update into database
            rekeningKas= kodeRekeningDao.getKodeRekeningKasForJurnal(noJurnal);
        } catch (HibernateException e) {
            logger.error("[KasBoImpl.getKodeRekeningKasJurnal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching kode rekening Kas, please info to your admin..." + e.getMessage());
        }
        logger.info("[KasBoImpl.getKodeRekeningKasJurnal] end process <<<");
        return rekeningKas;
    }

    @Override
    public List<Kas> getWithUangMuka(Kas searchBean) throws GeneralBOException {
        return null;
    }

    @Override
    public ImTransEntity getTipeMaster(String transId) throws GeneralBOException {
        logger.info("[KasBoImpl.getTipeMaster] start process >>>");
        ImTransEntity transEntity= new ImTransEntity();
        try {
            transEntity= transDao.getById("transId",transId);
        } catch (HibernateException e) {
            logger.error("[KasBoImpl.getTipeMaster] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data Kas, please info to your admin..." + e.getMessage());
        }
        logger.info("[KasBoImpl.getTipeMaster] end process <<<");
        return transEntity;
    }

    @Override
    public String getNamaRekeningKasJurnal(String noJurnal) throws GeneralBOException {
        logger.info("[KasBoImpl.getNamaRekeningKasJurnal] start process >>>");
        String rekeningKas="";
        try {
            // Update into database
            rekeningKas= kodeRekeningDao.getNamaRekeningKasForJurnal(noJurnal);
        } catch (HibernateException e) {
            logger.error("[KasBoImpl.getNamaRekeningKasJurnal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when get data Kas, please info to your admin..." + e.getMessage());
        }
        logger.info("[KasBoImpl.getNamaRekeningKasJurnal] end process <<<");
        return rekeningKas;
    }

    @Override
    public Kas getKasById(String kasId) throws GeneralBOException {
        Kas result = null;
        try {
            ItAkunKasEntity itAkunKasEntity = kasDao.getById("kasId",kasId);
            if(itAkunKasEntity != null){
                result = convertEntityToKas(itAkunKasEntity);
            }
        }
        catch (Exception e){
            logger.error("[KasBoImpl.getKasById] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when get data Kas, please info to your admin..." + e.getMessage());
        }
        return result;
    }

    @Override
    public Trans getDisableTrans(String transId,String coaLawan) throws GeneralBOException {
        logger.info("[KasBoImpl.getDisableTrans] start process >>>");
        Trans data = new Trans();
        boolean belumketemu=true;
        List<ImMappingJurnalEntity> mappingJurnalEntityList;
        try {
            do {
                if(coaLawan == null){
                    belumketemu = false;
                }
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
            logger.error("[KasBoImpl.getDisableTrans] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving update data Kas, please info to your admin..." + e.getMessage());
        }

        logger.info("[KasBoImpl.getDisableTrans] end process <<<");
        return data; // ini dapatnya id bukan kode coa - note by aji
    }

    @Override
    public String getPosisiCoaDiMappingJurnal(String transId, String kodeRekening) throws GeneralBOException {
        logger.info("[KasBoImpl.getPosisiCoaDiMappingJurnal] start process >>>");
        String posisiCoa = "";
        boolean belumketemu=true;
        List<ImMappingJurnalEntity> mappingJurnalEntityList;
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
            logger.error("[KasBoImpl.getPosisiCoaDiMappingJurnal] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving update data Kas, please info to your admin..." + e.getMessage());
        }
        for (ImMappingJurnalEntity mappingJurnalEntity : mappingJurnalEntityList ){
            posisiCoa = mappingJurnalEntity.getPosisi();
        }
        logger.info("[KasBoImpl.getPosisiCoaDiMappingJurnal] end process <<<");
        return posisiCoa;
    }

    @Override
    public List<Notifikasi> approveKas(Kas bean) throws GeneralBOException {
        logger.info("[KasBoImpl.approveKas] start process >>>");
        List<Notifikasi> notifikasiList = new ArrayList<>();
        List<User> users = new ArrayList<>();
        if (bean!=null) {
            ItAkunKasEntity itAkunKasEntity = null;
            try {
                // Get data from database by ID
                itAkunKasEntity = kasDao.getById("kasId", bean.getKasId());
            } catch (HibernateException e) {
                logger.error("[KasBoImpl.approveKas] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Kas by Kode Kas, please inform to your admin...," + e.getMessage());
            }
            if (itAkunKasEntity != null) {
                if (bean.getApprovalKeuanganFlag()!=null){
                    itAkunKasEntity.setApprovalKeuanganFlag(bean.getApprovalKeuanganFlag());
                    itAkunKasEntity.setApprovalKeuanganId(bean.getApprovalKeuanganId());
                    itAkunKasEntity.setApprovalKeuanganName(bean.getApprovalKeuanganName());
                    itAkunKasEntity.setApprovalKeuanganDate(bean.getApprovalKeuanganDate());

                    if ("Y".equalsIgnoreCase(bean.getApprovalKeuanganFlag())){
                        itAkunKasEntity.setApprovalKasubKeuanganFlag(null);
                        itAkunKasEntity.setApprovalKasubKeuanganId(null);
                        itAkunKasEntity.setApprovalKasubKeuanganName(null);
                        itAkunKasEntity.setApprovalKasubKeuanganDate(null);
                    }

                    //mengirim notif ke kasub keuangan
                    if (itAkunKasEntity.getApprovalKasubKeuanganFlag()==null){
                        users = userDao.getUserByBranchAndRole(itAkunKasEntity.getBranchId(),CommonConstant.ROLE_ID_KASUB_KEU);
                    }

                }else if (bean.getApprovalKasubKeuanganFlag()!=null){
                    itAkunKasEntity.setApprovalKasubKeuanganFlag(bean.getApprovalKasubKeuanganFlag());
                    itAkunKasEntity.setApprovalKasubKeuanganId(bean.getApprovalKasubKeuanganId());
                    itAkunKasEntity.setApprovalKasubKeuanganName(bean.getApprovalKasubKeuanganName());
                    itAkunKasEntity.setApprovalKasubKeuanganDate(bean.getApprovalKasubKeuanganDate());

                    if ("N".equalsIgnoreCase(bean.getApprovalKasubKeuanganFlag())){
                        itAkunKasEntity.setApprovalKeuanganFlag(null);
                        itAkunKasEntity.setApprovalKeuanganId(null);
                        itAkunKasEntity.setApprovalKeuanganName(null);
                        itAkunKasEntity.setApprovalKeuanganDate(null);
                    }

                    if ("Y".equalsIgnoreCase(bean.getApprovalKasubKeuanganFlag())){
                        users = userDao.getUserByBranchAndRole(itAkunKasEntity.getBranchId(),CommonConstant.ROLE_ID_KA_KEU);
                    }
                }

                itAkunKasEntity.setAction(bean.getAction());
                itAkunKasEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itAkunKasEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    kasDao.updateAndSave(itAkunKasEntity);
                } catch (HibernateException e) {
                    logger.error("[KasBoImpl.approveKas] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Kas, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[KasBoImpl.approveKas] Error, not found data Kas with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Kas with request id, please check again your data ...");
            }
        }
        logger.info("[KasBoImpl.approveKas] end process <<<");

        return notifikasiList; //return tidak pernah diisi - note by aji
    }

    @Override
    public List<KasDetail> searchPengajuanBiaya(String branchId) throws GeneralBOException {
        logger.info("[KasBoImpl.searchPengajuanBiaya] start process >>>");
        List<KasDetail> listOfResult = new ArrayList<>();
        List<KasDetail> kasDetailList;

        try {
            kasDetailList = kasDao.searchPengajuanBiaya(branchId);
        } catch (HibernateException e) {
            logger.error("[KasBoImpl.searchPengajuanBiaya] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(kasDetailList != null){
            KasDetail returnKasDetail;
            // Looping from dao to object and save in collection
            for(KasDetail kasDetail : kasDetailList){
                returnKasDetail = new KasDetail();
                if (kasDetail.getNoNota()==null){
                    returnKasDetail.setNoNota("");
                }else{
                    returnKasDetail.setNoNota(kasDetail.getNoNota());
                }
                listOfResult.add(returnKasDetail);
            }
        }
        logger.info("[KasBoImpl.searchPengajuanBiaya] end process <<<");

        return listOfResult;
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }
}
