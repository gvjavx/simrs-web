package com.neurix.akuntansi.transaksi.pengajuanSetor.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.masterVendor.dao.MasterVendorDao;
import com.neurix.akuntansi.master.masterVendor.model.ImMasterVendorEntity;
import com.neurix.akuntansi.transaksi.pengajuanSetor.bo.PengajuanSetorBo;
import com.neurix.akuntansi.transaksi.pengajuanSetor.dao.*;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.*;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.company.model.ImBranchesPK;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.positionBagian.dao.PositionBagianDao;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianEntity;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.payroll.dao.PayrollDao;
import com.neurix.hris.transaksi.payroll.model.ItPayrollEntity;
import com.neurix.simrs.master.dokter.dao.DokterDao;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterEntity;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class PengajuanSetorBoImpl implements PengajuanSetorBo {

    protected static transient Logger logger = Logger.getLogger(PengajuanSetorBoImpl.class);
    private PayrollDao payrollDao;
    private PositionDao positionDao;
    private BranchDao branchDao;
    private DepartmentDao departmentDao;
    private PositionBagianDao positionBagianDao;
    private BiodataDao biodataDao;
    private DokterDao dokterDao;
    private KodeRekeningDao kodeRekeningDao;
    private MasterVendorDao masterVendorDao;
    private PerhitunganPpnKdDao perhitunganPpnKdDao;
    private ProsesPpnKdDao prosesPpnKdDao;
    private PerhitunganKembaliPpnDao perhitunganKembaliPpnDao;
    private PerhitunganKembaliUnitDao perhitunganKembaliUnitDao;

    public PerhitunganKembaliUnitDao getPerhitunganKembaliUnitDao() {
        return perhitunganKembaliUnitDao;
    }

    public void setPerhitunganKembaliUnitDao(PerhitunganKembaliUnitDao perhitunganKembaliUnitDao) {
        this.perhitunganKembaliUnitDao = perhitunganKembaliUnitDao;
    }

    public PerhitunganKembaliPpnDao getPerhitunganKembaliPpnDao() {
        return perhitunganKembaliPpnDao;
    }

    public void setPerhitunganKembaliPpnDao(PerhitunganKembaliPpnDao perhitunganKembaliPpnDao) {
        this.perhitunganKembaliPpnDao = perhitunganKembaliPpnDao;
    }

    public PerhitunganPpnKdDao getPerhitunganPpnKdDao() {
        return perhitunganPpnKdDao;
    }

    public void setPerhitunganPpnKdDao(PerhitunganPpnKdDao perhitunganPpnKdDao) {
        this.perhitunganPpnKdDao = perhitunganPpnKdDao;
    }

    public ProsesPpnKdDao getProsesPpnKdDao() {
        return prosesPpnKdDao;
    }

    public void setProsesPpnKdDao(ProsesPpnKdDao prosesPpnKdDao) {
        this.prosesPpnKdDao = prosesPpnKdDao;
    }

    public MasterVendorDao getMasterVendorDao() {
        return masterVendorDao;
    }

    public void setMasterVendorDao(MasterVendorDao masterVendorDao) {
        this.masterVendorDao = masterVendorDao;
    }

    public KodeRekeningDao getKodeRekeningDao() {
        return kodeRekeningDao;
    }

    public void setKodeRekeningDao(KodeRekeningDao kodeRekeningDao) {
        this.kodeRekeningDao = kodeRekeningDao;
    }

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public DokterDao getDokterDao() {
        return dokterDao;
    }

    public void setDokterDao(DokterDao dokterDao) {
        this.dokterDao = dokterDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public PositionBagianDao getPositionBagianDao() {
        return positionBagianDao;
    }

    public void setPositionBagianDao(PositionBagianDao positionBagianDao) {
        this.positionBagianDao = positionBagianDao;
    }

    public PayrollDao getPayrollDao() {
        return payrollDao;
    }

    public void setPayrollDao(PayrollDao payrollDao) {
        this.payrollDao = payrollDao;
    }

    public static Logger getLogger() {
        return logger;
    }
    private PengajuanSetorDao pengajuanSetorDao;
    private PengajuanSetorDetailDao pengajuanSetorDetailDao;

    public static void setLogger(Logger logger) {
        PengajuanSetorBoImpl.logger = logger;
    }

    public PengajuanSetorDao getPengajuanSetorDao() {
        return pengajuanSetorDao;
    }

    public void setPengajuanSetorDao(PengajuanSetorDao pengajuanSetorDao) {
        this.pengajuanSetorDao = pengajuanSetorDao;
    }

    public PengajuanSetorDetailDao getPengajuanSetorDetailDao() {
        return pengajuanSetorDetailDao;
    }

    public void setPengajuanSetorDetailDao(PengajuanSetorDetailDao pengajuanSetorDetailDao) {
        this.pengajuanSetorDetailDao = pengajuanSetorDetailDao;
    }

    @Override
    public void saveDelete(PengajuanSetor bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(PengajuanSetor bean) throws GeneralBOException {

    }

    @Override
    public PengajuanSetor saveAdd(PengajuanSetor bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<PengajuanSetor> getByCriteria(PengajuanSetor searchBean) throws GeneralBOException {
        logger.info("[PengajuanSetorBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PengajuanSetor> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPengajuanSetorId() != null && !"".equalsIgnoreCase(searchBean.getPengajuanSetorId())) {
                hsCriteria.put("pengajuan_setor_id", searchBean.getPengajuanSetorId());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getKeterangan() != null && !"".equalsIgnoreCase(searchBean.getKeterangan())) {
                hsCriteria.put("keterangan", searchBean.getKeterangan());
            }
            if (searchBean.getTipePengajuanSetor() != null && !"".equalsIgnoreCase(searchBean.getTipePengajuanSetor())) {
                hsCriteria.put("tipe_pengajuan_setor", searchBean.getTipePengajuanSetor());
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
            List<ItPengajuanSetorEntity> itPengajuanSetorEntities = null;
            try {
                itPengajuanSetorEntities = pengajuanSetorDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PengajuanSetorBoImpl.getSearchPengajuanBiayaByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itPengajuanSetorEntities != null){
                PengajuanSetor returnPengajuanSetor;
                // Looping from dao to object and save in collection
                for(ItPengajuanSetorEntity pengajuanSetorEntity : itPengajuanSetorEntities){
                    returnPengajuanSetor = new PengajuanSetor();
                    returnPengajuanSetor.setPengajuanSetorId(pengajuanSetorEntity.getPengajuanSetorId());
                    returnPengajuanSetor.setTahun(pengajuanSetorEntity.getTahun());
                    returnPengajuanSetor.setBulan(pengajuanSetorEntity.getBulan());
                    returnPengajuanSetor.setBulanName(CommonUtil.convertNumberToStringBulan(pengajuanSetorEntity.getBulan()));
                    returnPengajuanSetor.setRegisteredDate(pengajuanSetorEntity.getRegisteredDate());
                    returnPengajuanSetor.setStRegisteredDate(CommonUtil.convertDateToString(pengajuanSetorEntity.getRegisteredDate()));
                    returnPengajuanSetor.setJumlahPph21Payroll(pengajuanSetorEntity.getJumlahPph21Payroll());
                    returnPengajuanSetor.setJumlahPph21Kso(pengajuanSetorEntity.getJumlahPph21Kso());
                    returnPengajuanSetor.setJumlahPph21Pengajuan(pengajuanSetorEntity.getJumlahPph21Pengajuan());
                    returnPengajuanSetor.setJumlahPpnMasukanB2(pengajuanSetorEntity.getJumlahPpnMasukanB2());
                    returnPengajuanSetor.setJumlahPpnKeluaran(pengajuanSetorEntity.getJumlahPpnKeluaran());
                    returnPengajuanSetor.setJumlahPpnMasukanB3(pengajuanSetorEntity.getJumlahPpnMasukanB3());
                    returnPengajuanSetor.setJumlahSeluruhnya(pengajuanSetorEntity.getJumlahSeluruhnya());
                    returnPengajuanSetor.setBranchId(pengajuanSetorEntity.getBranchId());
                    returnPengajuanSetor.setApprovalId(pengajuanSetorEntity.getApprovalId());
                    returnPengajuanSetor.setApprovalFlag(pengajuanSetorEntity.getApprovalFlag());
                    returnPengajuanSetor.setApprovalDate(pengajuanSetorEntity.getApprovalDate());
                    returnPengajuanSetor.setCancelId(pengajuanSetorEntity.getCancelId());
                    returnPengajuanSetor.setCancelFlag(pengajuanSetorEntity.getCancelFlag());
                    returnPengajuanSetor.setCancelDate(pengajuanSetorEntity.getCancelDate());
                    returnPengajuanSetor.setKeterangan(pengajuanSetorEntity.getKeterangan());
                    returnPengajuanSetor.setKas(pengajuanSetorEntity.getKas());
                    returnPengajuanSetor.setPostingId(pengajuanSetorEntity.getPostingId());
                    returnPengajuanSetor.setPostingFlag(pengajuanSetorEntity.getPostingFlag());
                    returnPengajuanSetor.setPostingDate(pengajuanSetorEntity.getPostingDate());

                    List<ImKodeRekeningEntity> kodeRekeningEntityList = kodeRekeningDao.getKodeRekeningList(pengajuanSetorEntity.getKas());
                    for (ImKodeRekeningEntity rekeningEntity : kodeRekeningEntityList){
                        returnPengajuanSetor.setKasName(rekeningEntity.getNamaKodeRekening());
                    }

                    if ("PPH21".equalsIgnoreCase(searchBean.getTipePengajuanSetor())){
                        returnPengajuanSetor.setStJumlahPph21Payroll(CommonUtil.numbericFormat(pengajuanSetorEntity.getJumlahPph21Payroll(),"###,###"));
                        returnPengajuanSetor.setStJumlahPph21Kso(CommonUtil.numbericFormat(pengajuanSetorEntity.getJumlahPph21Kso(),"###,###"));
                        returnPengajuanSetor.setStJumlahPph21Pengajuan(CommonUtil.numbericFormat(pengajuanSetorEntity.getJumlahPph21Pengajuan(),"###,###"));
                        returnPengajuanSetor.setStJumlahSeluruhnya(CommonUtil.numbericFormat(pengajuanSetorEntity.getJumlahSeluruhnya(),"###,###"));
                    }else if ("PPN".equalsIgnoreCase(searchBean.getTipePengajuanSetor())){
                        returnPengajuanSetor.setStJumlahPpnMasukanB2(CommonUtil.numbericFormat(pengajuanSetorEntity.getJumlahPpnMasukanB2(),"###,###"));
                        returnPengajuanSetor.setStJumlahPpnKeluaran(CommonUtil.numbericFormat(pengajuanSetorEntity.getJumlahPpnKeluaran(),"###,###"));
                        returnPengajuanSetor.setStJumlahPpnMasukanB3(CommonUtil.numbericFormat(pengajuanSetorEntity.getJumlahPpnMasukanB3(),"###,###"));
                        returnPengajuanSetor.setStJumlahSeluruhnya(CommonUtil.numbericFormat(pengajuanSetorEntity.getJumlahSeluruhnya(),"###,###"));
                    }

                    List<ImBranches> branches = branchDao.getListBranchById(pengajuanSetorEntity.getBranchId());
                    if (branches.size()!=0){
                        for (ImBranches imBranches : branches){
                            returnPengajuanSetor.setBranchName(imBranches.getBranchName());
                        }
                    }

                    returnPengajuanSetor.setCreatedWho(pengajuanSetorEntity.getCreatedWho());
                    returnPengajuanSetor.setLastUpdateWho(pengajuanSetorEntity.getLastUpdateWho());
                    returnPengajuanSetor.setCreatedDate(pengajuanSetorEntity.getCreatedDate());
                    returnPengajuanSetor.setLastUpdate(pengajuanSetorEntity.getLastUpdate());
                    returnPengajuanSetor.setAction(pengajuanSetorEntity.getAction());
                    returnPengajuanSetor.setFlag(pengajuanSetorEntity.getFlag());
                    listOfResult.add(returnPengajuanSetor);
                }
            }
        }
        logger.info("[PengajuanSetorBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<PengajuanSetor> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public List<PengajuanSetorDetail> listPPhPayroll(PengajuanSetor search){
        logger.info("[PengajuanSetorBoImpl.listPPhPayroll] start process >>>");
        List<PengajuanSetorDetail> pengajuanSetorDetailList = new ArrayList<>();

        try {
            // Get data from database by ID
            pengajuanSetorDetailList = pengajuanSetorDao.listPPhPayroll(search);
            for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailList){
                pengajuanSetorDetail.setDibayar("Y");
                convertPph21Payroll(pengajuanSetorDetail);
            }
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.listPPhPayroll] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
        }

        logger.info("[PengajuanSetorBoImpl.listPPhPayroll] stop process >>>");
        return pengajuanSetorDetailList;
    }

    @Override
    public List<PengajuanSetorDetail> listPPh21KsoDokter(PengajuanSetor search){
        logger.info("[PengajuanSetorBoImpl.listPPh21KsoDokter] start process >>>");
        List<PengajuanSetorDetail> pengajuanSetorDetailList = new ArrayList<>();

        try {
            // Get data from database by ID
            pengajuanSetorDetailList = pengajuanSetorDao.listPPhKsoDokter(search);
            for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailList){
                pengajuanSetorDetail.setDibayar("Y");
                convertPph21DokterKso(pengajuanSetorDetail);
            }
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.listPPh21KsoDokter] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
        }

        logger.info("[PengajuanSetorBoImpl.listPPh21KsoDokter] stop process >>>");
        return pengajuanSetorDetailList;
    }

    @Override
    public List<PengajuanSetorDetail> listPPh21Pengajuan(PengajuanSetor search){
        logger.info("[PengajuanSetorBoImpl.listPPh21Pengajuan] start process >>>");
        List<PengajuanSetorDetail> pengajuanSetorDetailList = new ArrayList<>();
        List<PengajuanSetorDetail> finalPengajuanSetorList = new ArrayList<>();
        try {
            // Get data from database by ID
            pengajuanSetorDetailList = pengajuanSetorDao.listPPhPengajuan(search);
            for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailList){
                pengajuanSetorDetail.setDibayar("Y");
                convertPph21Pengajuan(pengajuanSetorDetail);
                //cek apakah sudah pernah dibayarkan di ppn
                List<PengajuanSetorDetail> pengajuanSetorDetailEntityList = pengajuanSetorDetailDao.cekApakahSudahDibayarkan(pengajuanSetorDetail.getTransaksiId(),"PPH21");
                if (pengajuanSetorDetailEntityList.size()==0){
                    finalPengajuanSetorList.add(pengajuanSetorDetail);
                }

            }
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.listPPh21Pengajuan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
        }

        logger.info("[PengajuanSetorBoImpl.listPPh21Pengajuan] stop process >>>");
        return finalPengajuanSetorList;
    }

    private PengajuanSetorDetail convertPph21Payroll( PengajuanSetorDetail data){
        if (data.getJumlah()!=null){
            data.setStJumlah(CommonUtil.numbericFormat(data.getJumlah(),"###,###"));
        }else{
            data.setStJumlah("0");
        }
        return data;
    }

    private PengajuanSetorDetail convertPph21Pengajuan( PengajuanSetorDetail data){
        if (data.getJumlah()!=null){
            data.setStJumlah(CommonUtil.numbericFormat(data.getJumlah(),"###,###"));
        }else{
            data.setStJumlah("0");
        }
        return data;
    }

    private PengajuanSetorDetail convertPph21DokterKso( PengajuanSetorDetail data){

        if (data.getJumlah()!=null){
            data.setStJumlah(CommonUtil.numbericFormat(data.getJumlah(),"###,###"));
        }else{
            data.setStJumlah("0");
        }
        return data;
    }

    @Override
    public void saveAddPengajuanSetorPph21(PengajuanSetor bean, List<PengajuanSetorDetail> pengajuanSetorDetailListPayroll, List<PengajuanSetorDetail> pengajuanSetorDetailListKso, List<PengajuanSetorDetail> pengajuanSetorDetailListPengajuan){
        logger.info("[PengajuanSetorBoImpl.saveAddPengajuanSetorPph21] start process >>>");

        //validasi
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date());
        c.add(Calendar.DATE, -1);

        Date tanggalSekarang = new Date(c.getTimeInMillis());

        if (bean.getRegisteredDate().before(tanggalSekarang)){
            String status ="Tanggal Pengajuan Setor tidak boleh kurang dari tanggal sekarang";
            logger.error("[PengajuanSetorBoImpl.saveAddPengajuanSetorPph21] Error :, " + status);
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + status);
        }

        if (pengajuanSetorDetailListPayroll.size()==0&&pengajuanSetorDetailListKso.size()==0&&pengajuanSetorDetailListPengajuan.size()==0){
            String status ="Data yang dicari tidak ada atau sudah diajukan";
            logger.error("[PengajuanSetorBoImpl.saveAddPengajuanSetorPph21] Error :, " + status);
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + status);
        }

        List<PengajuanSetorDetail> listDetailAll = new ArrayList<>();
        listDetailAll.addAll(pengajuanSetorDetailListPayroll);
        listDetailAll.addAll(pengajuanSetorDetailListKso);
        listDetailAll.addAll(pengajuanSetorDetailListPengajuan);

        String pengajuanSetorId;

        try {
            pengajuanSetorId = pengajuanSetorDao.getNextPengajuanSetorId();
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.saveAddPengajuanSetorPph21] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
        }

        ItPengajuanSetorEntity pengajuanSetorEntity = new ItPengajuanSetorEntity();
        pengajuanSetorEntity.setPengajuanSetorId(pengajuanSetorId);
        pengajuanSetorEntity.setBranchId(bean.getBranchId());
        pengajuanSetorEntity.setBulan(bean.getBulan());
        pengajuanSetorEntity.setTahun(bean.getTahun());
        pengajuanSetorEntity.setJumlahPph21Payroll(bean.getJumlahPph21Payroll());
        pengajuanSetorEntity.setJumlahPph21Kso(bean.getJumlahPph21Kso());
        pengajuanSetorEntity.setJumlahPph21Pengajuan(bean.getJumlahPph21Pengajuan());
        pengajuanSetorEntity.setJumlahPpnMasukanB2(BigDecimal.ZERO);
        pengajuanSetorEntity.setJumlahPpnKeluaran(BigDecimal.ZERO);
        pengajuanSetorEntity.setJumlahPpnMasukanB3(BigDecimal.ZERO);
        pengajuanSetorEntity.setJumlahSeluruhnya(bean.getJumlahSeluruhnya());
        pengajuanSetorEntity.setRegisteredDate(bean.getRegisteredDate());
        pengajuanSetorEntity.setCancelFlag("N");
        pengajuanSetorEntity.setPostingFlag("N");
        pengajuanSetorEntity.setTipePengajuanSetor("PPH21");
        pengajuanSetorEntity.setKeterangan(bean.getKeterangan());
        pengajuanSetorEntity.setKas(bean.getKas());

        pengajuanSetorEntity.setAction(bean.getAction());
        pengajuanSetorEntity.setFlag(bean.getFlag());
        pengajuanSetorEntity.setCreatedDate(bean.getCreatedDate());
        pengajuanSetorEntity.setCreatedWho(bean.getCreatedWho());
        pengajuanSetorEntity.setLastUpdate(bean.getLastUpdate());
        pengajuanSetorEntity.setLastUpdateWho(bean.getLastUpdateWho());

        try {
            pengajuanSetorDao.addAndSave(pengajuanSetorEntity);
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.saveAddPengajuanSetorPph21] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
        }

        //save detail
        for (PengajuanSetorDetail pengajuanSetorDetail : listDetailAll){
            ItPengajuanSetorDetailEntity pengajuanSetorDetailEntity = new ItPengajuanSetorDetailEntity();
            pengajuanSetorDetailEntity.setPengajuanSetorDetailId(pengajuanSetorDetailDao.getNextPengajuanSetorDetailId());
            pengajuanSetorDetailEntity.setPengajuanSetorId(pengajuanSetorId);
            pengajuanSetorDetailEntity.setBranchId(bean.getBranchId());
            pengajuanSetorDetailEntity.setTransaksiId(pengajuanSetorDetail.getTransaksiId());
            pengajuanSetorDetailEntity.setPersonId(pengajuanSetorDetail.getPersonId());
            pengajuanSetorDetailEntity.setTipe(pengajuanSetorDetail.getTipe());
            pengajuanSetorDetailEntity.setNote(pengajuanSetorDetail.getNote());
            pengajuanSetorDetailEntity.setJumlah(pengajuanSetorDetail.getJumlah());
            pengajuanSetorDetailEntity.setDivisiId(pengajuanSetorDetail.getPositionId());

            pengajuanSetorDetailEntity.setAction(bean.getAction());
            pengajuanSetorDetailEntity.setFlag(bean.getFlag());
            pengajuanSetorDetailEntity.setCreatedDate(bean.getCreatedDate());
            pengajuanSetorDetailEntity.setCreatedWho(bean.getCreatedWho());
            pengajuanSetorDetailEntity.setLastUpdate(bean.getLastUpdate());
            pengajuanSetorDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());

            if ("Y".equalsIgnoreCase(pengajuanSetorDetail.getDibayar())){
                try {
                    pengajuanSetorDetailDao.addAndSave(pengajuanSetorDetailEntity);
                } catch (HibernateException e) {
                    logger.error("[PengajuanSetorBoImpl.saveAddPengajuanSetorPph21] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
                }
            }
        }

        logger.info("[PengajuanSetorBoImpl.saveAddPengajuanSetorPph21] stop process >>>");
    }

    @Override
    public void saveAddPengajuanSetorPpn(PengajuanSetor bean, List<PengajuanSetorDetail> pengajuanSetorDetailListMasukanB2, List<PengajuanSetorDetail> pengajuanSetorDetailListKeluaran, List<PengajuanSetorDetail> pengajuanSetorDetailListMasukanB3){
        logger.info("[PengajuanSetorBoImpl.saveAddPengajuanSetorPpn] start process >>>");

        //validasi
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date());
        c.add(Calendar.DATE, -1);

        Date tanggalSekarang = new Date(c.getTimeInMillis());

        if (bean.getRegisteredDate().before(tanggalSekarang)){
            String status ="Tanggal Pengajuan Setor tidak boleh kurang dari tanggal sekarang";
            logger.error("[PengajuanSetorBoImpl.saveAddPengajuanSetorPpn] Error :, " + status);
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + status);
        }

        if (pengajuanSetorDetailListMasukanB2.size()==0&&pengajuanSetorDetailListKeluaran.size()==0&&pengajuanSetorDetailListMasukanB3.size()==0){
            String status ="Data yang dicari tidak ada atau sudah diajukan";
            logger.error("[PengajuanSetorBoImpl.saveAddPengajuanSetorPpn] Error :, " + status);
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + status);
        }

        if (bean.getJumlahSeluruhnya().compareTo(BigDecimal.ZERO)<1){
            String status ="Jumlah seluruhnya tidak boleh minus";
            logger.error("[PengajuanSetorBoImpl.saveAddPengajuanSetorPpn] Error :, " + status);
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + status);
        }

        List<PengajuanSetorDetail> listDetailAll = new ArrayList<>();
        listDetailAll.addAll(pengajuanSetorDetailListMasukanB2);
        listDetailAll.addAll(pengajuanSetorDetailListKeluaran);
        listDetailAll.addAll(pengajuanSetorDetailListMasukanB3);

        String pengajuanSetorId;

        try {
            pengajuanSetorId = pengajuanSetorDao.getNextPengajuanSetorId();
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.saveAddPengajuanSetorPpn] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
        }

        ItPengajuanSetorEntity pengajuanSetorEntity = new ItPengajuanSetorEntity();
        pengajuanSetorEntity.setPengajuanSetorId(pengajuanSetorId);
        pengajuanSetorEntity.setBranchId(bean.getBranchId());
        pengajuanSetorEntity.setBulan(bean.getBulan());
        pengajuanSetorEntity.setTahun(bean.getTahun());
        pengajuanSetorEntity.setJumlahPph21Payroll(bean.getJumlahPph21Payroll());
        pengajuanSetorEntity.setJumlahPph21Kso(bean.getJumlahPph21Kso());
        pengajuanSetorEntity.setJumlahPph21Pengajuan(bean.getJumlahPph21Pengajuan());
        pengajuanSetorEntity.setJumlahSeluruhnya(bean.getJumlahSeluruhnya());
        pengajuanSetorEntity.setRegisteredDate(bean.getRegisteredDate());
        pengajuanSetorEntity.setCancelFlag("N");
        pengajuanSetorEntity.setPostingFlag("N");
        pengajuanSetorEntity.setTipePengajuanSetor("PPN");
        pengajuanSetorEntity.setKeterangan(bean.getKeterangan());
        pengajuanSetorEntity.setJumlahPpnMasukanB2(bean.getJumlahPpnMasukanB2());
        pengajuanSetorEntity.setJumlahPpnKeluaran(bean.getJumlahPpnKeluaran());
        pengajuanSetorEntity.setJumlahPpnMasukanB3(bean.getJumlahPpnMasukanB3());
        pengajuanSetorEntity.setKas(bean.getKas());

        pengajuanSetorEntity.setAction(bean.getAction());
        pengajuanSetorEntity.setFlag(bean.getFlag());
        pengajuanSetorEntity.setCreatedDate(bean.getCreatedDate());
        pengajuanSetorEntity.setCreatedWho(bean.getCreatedWho());
        pengajuanSetorEntity.setLastUpdate(bean.getLastUpdate());
        pengajuanSetorEntity.setLastUpdateWho(bean.getLastUpdateWho());

        try {
            pengajuanSetorDao.addAndSave(pengajuanSetorEntity);
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.saveAddPengajuanSetorPpn] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
        }

        //save detail
        for (PengajuanSetorDetail pengajuanSetorDetail : listDetailAll){
            ItPengajuanSetorDetailEntity pengajuanSetorDetailEntity = new ItPengajuanSetorDetailEntity();
            pengajuanSetorDetailEntity.setPengajuanSetorDetailId(pengajuanSetorDetailDao.getNextPengajuanSetorDetailId());
            pengajuanSetorDetailEntity.setPengajuanSetorId(pengajuanSetorId);
            pengajuanSetorDetailEntity.setBranchId(bean.getBranchId());
            pengajuanSetorDetailEntity.setTransaksiId(pengajuanSetorDetail.getTransaksiId());
            pengajuanSetorDetailEntity.setPersonId(pengajuanSetorDetail.getPersonId());
            pengajuanSetorDetailEntity.setTipe(pengajuanSetorDetail.getTipe());
            pengajuanSetorDetailEntity.setNote(pengajuanSetorDetail.getNote());
            pengajuanSetorDetailEntity.setJumlah(pengajuanSetorDetail.getJumlah());
            pengajuanSetorDetailEntity.setDivisiId(pengajuanSetorDetail.getPositionId());

            pengajuanSetorDetailEntity.setAction(bean.getAction());
            pengajuanSetorDetailEntity.setFlag(bean.getFlag());
            pengajuanSetorDetailEntity.setCreatedDate(bean.getCreatedDate());
            pengajuanSetorDetailEntity.setCreatedWho(bean.getCreatedWho());
            pengajuanSetorDetailEntity.setLastUpdate(bean.getLastUpdate());
            pengajuanSetorDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());

            if ("PPN Keluaran".equalsIgnoreCase(pengajuanSetorDetail.getTipe())){
                if ("Y".equalsIgnoreCase(pengajuanSetorDetail.getDibayar())){
                    try {
                        pengajuanSetorDetailDao.addAndSave(pengajuanSetorDetailEntity);
                    } catch (HibernateException e) {
                        logger.error("[PengajuanSetorBoImpl.saveAddPengajuanSetorPpn] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
                    }
                }
            }else{
                try {
                    pengajuanSetorDetailDao.addAndSave(pengajuanSetorDetailEntity);
                } catch (HibernateException e) {
                    logger.error("[PengajuanSetorBoImpl.saveAddPengajuanSetorPpn] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
                }
            }
        }

        logger.info("[PengajuanSetorBoImpl.saveAddPengajuanSetorPpn] stop process >>>");
    }

    @Override
    public List<PengajuanSetorDetail> getDetailPengajuanSetorPPh21(String pengajuanBiayaId, String tipe) throws GeneralBOException {
        logger.info("[PengajuanSetorBoImpl.getDetailPembayaran] start process >>>");
        List<PengajuanSetorDetail> listOfResult = new ArrayList<>();
        List<ItPengajuanSetorDetailEntity> pengajuanSetorDetailEntityList ;
        try {
            pengajuanSetorDetailEntityList = pengajuanSetorDetailDao.getByPengajuanBiayaIdAndTipe(pengajuanBiayaId,tipe);
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.getDetailPembayaran] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if(pengajuanSetorDetailEntityList != null){
            PengajuanSetorDetail returnData;
            // Looping from dao to object and save in collection
            for(ItPengajuanSetorDetailEntity pengajuanSetorDetailEntity : pengajuanSetorDetailEntityList){
                returnData = convertPengajuanSetorDetailPPh21(pengajuanSetorDetailEntity,tipe);

                listOfResult.add(returnData);
            }
        }
        logger.info("[PengajuanSetorBoImpl.getDetailPembayaran] end process <<<");

        return listOfResult;
    }

    private PengajuanSetorDetail convertPengajuanSetorDetailPPh21 (ItPengajuanSetorDetailEntity data,String tipe ){
        PengajuanSetorDetail result = new PengajuanSetorDetail();
        result.setBranchId(data.getBranchId());
        result.setTipe(data.getTipe());
        result.setNote(data.getNote());
        result.setPersonId(data.getPersonId());
        result.setTransaksiId(data.getTransaksiId());
        result.setJumlah(data.getJumlah());
        List<ImBranches> branches = branchDao.getListBranchById(data.getBranchId());
        if (branches.size()!=0){
            for (ImBranches imBranches : branches){
                result.setBranchName(imBranches.getBranchName());
            }
        }
        if ("Payroll".equalsIgnoreCase(tipe)){
            if (data.getPersonId()!=null){
                ImBiodataEntity biodataEntity = biodataDao.getById("nip",data.getPersonId());
                result.setNama(biodataEntity.getNamaPegawai());
            }else{
                result.setNama("");
            }
            if (data.getJumlah()!=null){
                result.setStJumlah(CommonUtil.numbericFormat(data.getJumlah(),"###,###"));
            }else{
                result.setStJumlah("0");
            }
        } else if ("Dokter KSO".equalsIgnoreCase(tipe)){
            List<ImSimrsDokterEntity> dokterEntityList = dokterDao.getDataDokterByKodering(data.getPersonId());
            for (ImSimrsDokterEntity dokterEntity : dokterEntityList){
                result.setNama(dokterEntity.getNamaDokter());
            }

            if (data.getJumlah()!=null){
                result.setStJumlah(CommonUtil.numbericFormat(data.getJumlah(),"###,###"));
            }else{
                result.setStJumlah("0");
            }
        } else if ("Pengajuan Biaya PPH21".equalsIgnoreCase(tipe)){
            ImMasterVendorEntity vendorEntity = masterVendorDao.getById("nomorMaster",data.getPersonId());
            result.setNama(vendorEntity.getNama());

            if (data.getJumlah()!=null){
                result.setStJumlah(CommonUtil.numbericFormat(data.getJumlah(),"###,###"));
            }else{
                result.setStJumlah("0");
            }
        }
        return result;
    }

    @Override
    public void postingJurnal(PengajuanSetor bean) throws GeneralBOException {
        logger.info("[PengajuanSetorBoImpl.postingJurnal] start process >>>");
        if (bean!=null) {
            ItPengajuanSetorEntity itPengajuanSetorEntity = null;
            try {
                // Get data from database by ID
                itPengajuanSetorEntity = pengajuanSetorDao.getById("pengajuanSetorId", bean.getPengajuanSetorId());
            } catch (HibernateException e) {
                logger.error("[PengajuanSetorBoImpl.postingJurnal] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PembayaranUtangPiutang by Kode PembayaranUtangPiutang, please inform to your admin...," + e.getMessage());
            }

            if (itPengajuanSetorEntity != null) {
                itPengajuanSetorEntity.setPostingId(bean.getPostingId());
                itPengajuanSetorEntity.setPostingDate(bean.getPostingDate());
                itPengajuanSetorEntity.setPostingFlag(bean.getPostingFlag());
                itPengajuanSetorEntity.setNoJurnal(bean.getNoJurnal());

                itPengajuanSetorEntity.setFlag(bean.getFlag());
                itPengajuanSetorEntity.setAction(bean.getAction());
                itPengajuanSetorEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itPengajuanSetorEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    pengajuanSetorDao.updateAndSave(itPengajuanSetorEntity);
                } catch (HibernateException e) {
                    logger.error("[PengajuanSetorBoImpl.postingJurnal] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PengajuanSetorBoImpl.postingJurnal] Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");
            }
        }
        logger.info("[PengajuanSetorBoImpl.postingJurnal] end process <<<");
    }


    @Override
    public void approvePengajuanSetor(PengajuanSetor bean) throws GeneralBOException {
        logger.info("[PengajuanSetorBoImpl.approvePengajuanSetor] start process >>>");
        if (bean!=null) {
            ItPengajuanSetorEntity itPengajuanSetorEntity = null;
            try {
                // Get data from database by ID
                itPengajuanSetorEntity = pengajuanSetorDao.getById("pengajuanSetorId", bean.getPengajuanSetorId());
            } catch (HibernateException e) {
                logger.error("[PengajuanSetorBoImpl.approvePengajuanSetor] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data , please inform to your admin...," + e.getMessage());
            }

            if (itPengajuanSetorEntity != null) {
                itPengajuanSetorEntity.setApprovalId(bean.getApprovalId());
                itPengajuanSetorEntity.setApprovalDate(bean.getApprovalDate());
                itPengajuanSetorEntity.setApprovalFlag(bean.getApprovalFlag());

                itPengajuanSetorEntity.setFlag(bean.getFlag());
                itPengajuanSetorEntity.setAction(bean.getAction());
                itPengajuanSetorEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itPengajuanSetorEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    pengajuanSetorDao.updateAndSave(itPengajuanSetorEntity);
                } catch (HibernateException e) {
                    logger.error("[PengajuanSetorBoImpl.approvePengajuanSetor] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data , please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PengajuanSetorBoImpl.approvePengajuanSetor] Error, not found data  with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data  with request id, please check again your data ...");
            }
        }
        logger.info("[PengajuanSetorBoImpl.approvePengajuanSetor] end process <<<");
    }

    @Override
    public void postingJurnalProsesPpn(PerhitunganPpnKd bean) throws GeneralBOException {
        logger.info("[PengajuanSetorBoImpl.postingJurnalProsesPpn] start process >>>");
        PerhitunganPpnKd search = new PerhitunganPpnKd();
        search.setTahun(bean.getTahun());
        search.setBulan(bean.getBulan());

        if (bean!=null) {

            //Untuk Perhitungan Proses PPN
            List<ItAkunPerhitunganPpnKdEntity> perhitunganPpnKdEntityList = new ArrayList<>();
            try {
                // Get data from database by ID
                perhitunganPpnKdEntityList = perhitunganPpnKdDao.getAllPerhitunganPpnKdList(search);
            } catch (HibernateException e) {
                logger.error("[PengajuanSetorBoImpl.postingJurnalProsesPpn] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
            }
            for (ItAkunPerhitunganPpnKdEntity perhitunganPpnKdEntity : perhitunganPpnKdEntityList){
                perhitunganPpnKdEntity.setApprovalId(bean.getApprovalWho());
                perhitunganPpnKdEntity.setApprovalDate(bean.getApprovalDate());
                perhitunganPpnKdEntity.setApprovalFlag(bean.getApprovalFlag());
                perhitunganPpnKdEntity.setPiutangPpnKeluaran(bean.getPiutangPpnKeluaran());
                perhitunganPpnKdEntity.setBuktiPiutangPpnKeluaran(bean.getBuktiPiutangPpnKeluaran());
                perhitunganPpnKdEntity.setKeterangan(bean.getKeterangan());

                perhitunganPpnKdEntity.setFlag(bean.getFlag());
                perhitunganPpnKdEntity.setAction(bean.getAction());
                perhitunganPpnKdEntity.setLastUpdateWho(bean.getLastUpdateWho());
                perhitunganPpnKdEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    perhitunganPpnKdDao.updateAndSave(perhitunganPpnKdEntity);
                } catch (HibernateException e) {
                    logger.error("[PengajuanSetorBoImpl.postingJurnalProsesPpn] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving, please info to your admin..." + e.getMessage());
                }
            }

            //Untuk Pengajuan Setor
            //Untuk Perhitungan Proses PPN
            List<ItPengajuanSetorEntity> pengajuanSetorEntityList = new ArrayList<>();
            try {
                // Get data from database by ID
                pengajuanSetorEntityList = pengajuanSetorDao.getListPengajuanSetorPpnByBulanDanTahunForPosting(bean.getBulan(),bean.getTahun());
            } catch (HibernateException e) {
                logger.error("[PengajuanSetorBoImpl.postingJurnalProsesPpn] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
            }
            for (ItPengajuanSetorEntity pengajuanSetorEntity : pengajuanSetorEntityList){
                pengajuanSetorEntity.setPostingFlag(bean.getApprovalFlag());
                pengajuanSetorEntity.setPostingDate(bean.getApprovalDate());
                pengajuanSetorEntity.setPostingId(bean.getApprovalWho());

                pengajuanSetorEntity.setFlag(bean.getFlag());
                pengajuanSetorEntity.setAction(bean.getAction());
                pengajuanSetorEntity.setLastUpdateWho(bean.getLastUpdateWho());
                pengajuanSetorEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    pengajuanSetorDao.updateAndSave(pengajuanSetorEntity);
                } catch (HibernateException e) {
                    logger.error("[PengajuanSetorBoImpl.postingJurnalProsesPpn] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving, please info to your admin..." + e.getMessage());
                }
            }
        }
        logger.info("[PengajuanSetorBoImpl.postingJurnalProsesPpn] end process <<<");
    }

    @Override
    public void batalkanPengajuan(PengajuanSetor bean) throws GeneralBOException {
        logger.info("[PengajuanSetorBoImpl.batalkanPengajuan] start process >>>");
        if (bean!=null) {
            ItPengajuanSetorEntity itPengajuanSetorEntity = null;
            try {
                // Get data from database by ID
                itPengajuanSetorEntity = pengajuanSetorDao.getById("pengajuanSetorId", bean.getPengajuanSetorId());
            } catch (HibernateException e) {
                logger.error("[PengajuanSetorBoImpl.batalkanPengajuan] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
            }
            if (itPengajuanSetorEntity != null) {
                itPengajuanSetorEntity.setCancelFlag(bean.getCancelFlag());
                itPengajuanSetorEntity.setCancelDate(bean.getCancelDate());
                itPengajuanSetorEntity.setCancelId(bean.getCancelId());

                itPengajuanSetorEntity.setFlag(bean.getFlag());
                itPengajuanSetorEntity.setAction(bean.getAction());
                itPengajuanSetorEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itPengajuanSetorEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    pengajuanSetorDao.updateAndSave(itPengajuanSetorEntity);
                } catch (HibernateException e) {
                    logger.error("[PengajuanSetorBoImpl.batalkanPengajuan] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PengajuanSetorBoImpl.batalkanPengajuan] Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");
            }
        }
        logger.info("[PengajuanSetorBoImpl.batalkanPengajuan] end process <<<");
    }

    @Override
    public List<PengajuanSetorDetail> listPPnKeluaran(PengajuanSetor search){
        logger.info("[PengajuanSetorBoImpl.listPPnKeluaran] start process >>>");
        List<PengajuanSetorDetail> pengajuanSetorDetailList = new ArrayList<>();

        try {
            // Get data from database by ID
            pengajuanSetorDetailList = pengajuanSetorDao.listPPnKeluaran(search);
            for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailList){
                pengajuanSetorDetail.setDibayar("Y");
                convertPpnKeluaran(pengajuanSetorDetail);
            }
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.listPPnKeluaran] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
        }
                logger.info("[PengajuanSetorBoImpl.listPPnKeluaran] stop process >>>");
        return pengajuanSetorDetailList;
    }

    private PengajuanSetorDetail convertPpnKeluaran( PengajuanSetorDetail data){

        if (data.getJumlah()!=null){
            data.setStJumlah(CommonUtil.numbericFormat(data.getJumlah(),"###,###"));
        }else{
            data.setStJumlah("0");
        }
        return data;
    }

    @Override
    public List<PengajuanSetorDetail> listPPnMasukan(PengajuanSetor search){
        logger.info("[PengajuanSetorBoImpl.listPPnMasukan] start process >>>");
        List<PengajuanSetorDetail> pengajuanSetorDetailList = new ArrayList<>();

        try {
            // Get data from database by ID
            pengajuanSetorDetailList = pengajuanSetorDao.listPPnMasukan(search);
            for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailList){
                pengajuanSetorDetail.setDibayar("Y");
                convertPpnMasukan(pengajuanSetorDetail);
            }
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.listPPnMasukan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
        }
        logger.info("[PengajuanSetorBoImpl.listPPnMasukan] stop process >>>");
        return pengajuanSetorDetailList;
    }

    private PengajuanSetorDetail convertPpnMasukan( PengajuanSetorDetail data){

        if (data.getJumlah()!=null){
            data.setStJumlah(CommonUtil.numbericFormat(data.getJumlah(),"###,###"));
        }else{
            data.setStJumlah("0");
        }
        return data;
    }

    private PengajuanSetorDetail convertPpnPengajuan( PengajuanSetorDetail data){
        ImPosition position = positionDao.getById("positionId",data.getPositionId());
        data.setPosisiName(position.getPositionName());

        if (position.getBagianId()!=null){
            ImPositionBagianEntity positionBagian= positionBagianDao.getById("bagianId",position.getBagianId());
            data.setBagianName(positionBagian.getBagianName());
        }else{
            data.setBagianName("");
        }
        if (position.getDepartmentId()!=null){
            ImDepartmentEntity departmentEntity= departmentDao.getById("departmentId",position.getDepartmentId());
            data.setDivisiName(departmentEntity.getDepartmentName());
        }else{
            data.setDivisiName("");
        }

        if (data.getJumlah()!=null){
            data.setStJumlah(CommonUtil.numbericFormat(data.getJumlah(),"###,###"));
        }else{
            data.setStJumlah("0");
        }

        return data;
    }

    @Override
    public List<PengajuanSetorDetail> listPPnPengajuan(PengajuanSetor search){
        logger.info("[PengajuanSetorBoImpl.listPPh21Pengajuan] start process >>>");
        List<PengajuanSetorDetail> pengajuanSetorDetailList = new ArrayList<>();
        List<PengajuanSetorDetail> finalPengajuanSetorList = new ArrayList<>();

        try {
            // Get data from database by ID
            pengajuanSetorDetailList = pengajuanSetorDao.listPPnPengajuan(search);
            for (PengajuanSetorDetail pengajuanSetorDetail : pengajuanSetorDetailList){
                pengajuanSetorDetail.setDibayar("Y");
                convertPpnPengajuan(pengajuanSetorDetail);

                //cek apakah sudah pernah dibayarkan di ppn
                List<PengajuanSetorDetail> pengajuanSetorDetailEntityList = pengajuanSetorDetailDao.cekApakahSudahDibayarkan(pengajuanSetorDetail.getTransaksiId(),"PPN");
                if (pengajuanSetorDetailEntityList.size()==0){
                    finalPengajuanSetorList.add(pengajuanSetorDetail);
                }
            }
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.listPPh21Pengajuan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
        }

        logger.info("[PengajuanSetorBoImpl.listPPh21Pengajuan] stop process >>>");
        return finalPengajuanSetorList;
    }

    @Override
    public List<PengajuanSetorDetail> getDetailPengajuanSetorPPn(String pengajuanBiayaId, String tipe) throws GeneralBOException {
        logger.info("[PengajuanSetorBoImpl.getDetailPengajuanSetorPPn] start process >>>");
        List<PengajuanSetorDetail> listOfResult = new ArrayList<>();
        List<ItPengajuanSetorDetailEntity> pengajuanSetorDetailEntityList ;
        try {
            pengajuanSetorDetailEntityList = pengajuanSetorDetailDao.getByPengajuanBiayaIdAndTipe(pengajuanBiayaId,tipe);
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.getDetailPengajuanSetorPPn] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if(pengajuanSetorDetailEntityList != null){
            PengajuanSetorDetail returnData;
            // Looping from dao to object and save in collection
            for(ItPengajuanSetorDetailEntity pengajuanSetorDetailEntity : pengajuanSetorDetailEntityList){
                returnData = convertPengajuanSetorDetailPPn(pengajuanSetorDetailEntity,tipe);

                listOfResult.add(returnData);
            }
        }
        logger.info("[PengajuanSetorBoImpl.getDetailPengajuanSetorPPn] end process <<<");

        return listOfResult;
    }

    private PengajuanSetorDetail convertPengajuanSetorDetailPPn (ItPengajuanSetorDetailEntity data,String tipe ){
        PengajuanSetorDetail result = new PengajuanSetorDetail();
        result.setBranchId(data.getBranchId());
        result.setTipe(data.getTipe());
        result.setNote(data.getNote());
        result.setPersonId(data.getPersonId());
        result.setTransaksiId(data.getTransaksiId());
        List<ImBranches> branches = branchDao.getListBranchById(data.getBranchId());
        if (branches.size()!=0){
            for (ImBranches imBranches : branches){
                result.setBranchName(imBranches.getBranchName());
            }
        }
        if ("PPN Masukan B2".equalsIgnoreCase(tipe)){
            if (data.getJumlah()!=null){
                result.setStJumlah(CommonUtil.numbericFormat(data.getJumlah(),"###,###"));
            }else{
                result.setStJumlah("0");
            }
        }else if ("PPN Masukan B3".equalsIgnoreCase(tipe)){
            if (data.getJumlah()!=null){
                result.setStJumlah(CommonUtil.numbericFormat(data.getJumlah(),"###,###"));
            }else{
                result.setStJumlah("0");
            }
        } else if ("PPN Keluaran".equalsIgnoreCase(tipe)){
            if (data.getJumlah()!=null){
                result.setStJumlah(CommonUtil.numbericFormat(data.getJumlah(),"###,###"));
            }else{
                result.setStJumlah("0");
            }
        }
        return result;
    }

    @Override
    public ItPengajuanSetorEntity getPengajuanSetorById(String pengajuanSetorId){
        return pengajuanSetorDao.getById("pengajuanSetorId",pengajuanSetorId);
    }

    @Override
    public Map getBillingForPosting(String pengajuanSetorId){
        logger.info("[PengajuanSetorBoImpl.getBillingForPosting] start process <<<");
        Map dataBilling = new HashMap();

        List<Map> dataDetailList = new ArrayList<>();

        BigDecimal total = BigDecimal.ZERO;

        ItPengajuanSetorEntity pengajuanSetorEntity = pengajuanSetorDao.getById("pengajuanSetorId",pengajuanSetorId);

        List<ItPengajuanSetorDetailEntity> pengajuanSetorDetailEntityList = pengajuanSetorDetailDao.getByPengajuanSetorId(pengajuanSetorId);
        for (ItPengajuanSetorDetailEntity data : pengajuanSetorDetailEntityList){
            Map dataDetail = new HashMap();
            dataDetail.put("master_id",data.getPersonId());
            dataDetail.put("bukti",data.getTransaksiId());
            dataDetail.put("nilai",data.getJumlah());
            total = total.add(data.getJumlah());
            dataDetailList.add(dataDetail);
        }

        Map kas = new HashMap();
        kas.put("metode_bayar","transfer");
        kas.put("bank", pengajuanSetorEntity.getKas());
        kas.put("nilai",total);

        dataBilling.put("hutang_pph_21",dataDetailList);
        dataBilling.put("kas",kas);

        logger.info("[PengajuanSetorBoImpl.getBillingForPosting] stop process >>>");
        return dataBilling;
    }

    @Override
    public List<ProsesPpnKd> prosesPPnKanpus(PengajuanSetor bean){
        logger.info("[PengajuanSetorBoImpl.listPPh21Pengajuan] start process >>>");
        String status = "";
        Integer urutan = 1;
        List<ProsesPpnKd> listOfResult = new ArrayList<>();
        ProsesPpnKd jumlahUnit = new ProsesPpnKd();
        ProsesPpnKd direksi = rekapBantuan(bean,"normal");
        ProsesPpnKd konsol = new ProsesPpnKd();
        List<ImBranches> branchesList = branchDao.getAllBranch();
        //menghapus kantor pusat
        int i=0;
        for (ImBranches branches : branchesList){
            if (CommonConstant.ID_KANPUS.equalsIgnoreCase(branches.getPrimaryKey().getId())){
                branchesList.remove(i);
                break;
            }
            i++;
        }

        List<ItPengajuanSetorEntity> itPengajuanSetorEntityList = pengajuanSetorDao.getListPengajuanSetorByBulanDanTahun(bean.getBulan(),bean.getTahun());

        jumlahUnit.setBranchId("unit");
        jumlahUnit.setBranchName("JUMLAH UNIT");

        konsol.setBranchId("konsol");
        konsol.setBranchName("JUMLAH KONSOLIDASI");

        //        if (branchesList.size()==itPengajuanSetorEntityList.size()){
        if (1==1){
            for (ItPengajuanSetorEntity pengajuanSetorEntity : itPengajuanSetorEntityList){
                ProsesPpnKd prosesPpnKd = new ProsesPpnKd();
                prosesPpnKd.setNo(urutan);
                prosesPpnKd.setBranchId(pengajuanSetorEntity.getBranchId());

                List<ImBranches> branches = branchDao.getListBranchById(pengajuanSetorEntity.getBranchId());
                for (ImBranches branch : branches){
                    prosesPpnKd.setBranchName(branch.getBranchName());
                }

                //set keluaran
                prosesPpnKd.setKeluaranUnit(pengajuanSetorEntity.getJumlahPpnKeluaran());
                prosesPpnKd.setKeluaranKoreksi(BigDecimal.ZERO);
                prosesPpnKd.setKeluaranDiambilKp(prosesPpnKd.getKeluaranUnit().add(prosesPpnKd.getKeluaranKoreksi()));

                //set masukan
                prosesPpnKd.setMasukanUnit(pengajuanSetorEntity.getJumlahPpnMasukanB2().add(pengajuanSetorEntity.getJumlahPpnMasukanB3()));
                prosesPpnKd.setMasukanKoreksi(BigDecimal.ZERO);
                prosesPpnKd.setMasukanDiambilKp(prosesPpnKd.getMasukanUnit().subtract(prosesPpnKd.getMasukanKoreksi()));

                jumlahUnit.setKeluaranUnit(jumlahUnit.getKeluaranUnit().add(prosesPpnKd.getKeluaranUnit()));
                jumlahUnit.setMasukanUnit(jumlahUnit.getMasukanUnit().add(prosesPpnKd.getMasukanUnit()));
                jumlahUnit.setKeluaranKoreksi(jumlahUnit.getKeluaranKoreksi().add(prosesPpnKd.getKeluaranKoreksi()));
                jumlahUnit.setMasukanKoreksi(jumlahUnit.getMasukanKoreksi().add(prosesPpnKd.getMasukanKoreksi()));
                jumlahUnit.setKeluaranDiambilKp(jumlahUnit.getKeluaranDiambilKp().add(prosesPpnKd.getKeluaranDiambilKp()));
                jumlahUnit.setMasukanDiambilKp(jumlahUnit.getMasukanDiambilKp().add(prosesPpnKd.getMasukanDiambilKp()));

                urutan++;
                listOfResult.add(prosesPpnKd);
            }
        }else{
            status="ERROR : PPN unit masih ada yang belum di proses atau belum di approve";
            logger.error("[PengajuanSetorBoImpl.prosesPPnKanpus]"+status);
            throw new GeneralBOException(status);
        }


        jumlahUnit.setNo(urutan);
        direksi.setNo(urutan+1);
        konsol.setNo(urutan+2);

        konsol.setKeluaranUnit(jumlahUnit.getKeluaranUnit().add(direksi.getKeluaranUnit()));
        konsol.setMasukanUnit(jumlahUnit.getMasukanUnit().add(direksi.getMasukanUnit()));
        konsol.setKeluaranKoreksi(jumlahUnit.getKeluaranKoreksi().add(direksi.getKeluaranKoreksi()));
        konsol.setMasukanKoreksi(jumlahUnit.getMasukanKoreksi().add(direksi.getMasukanKoreksi()));
        konsol.setKeluaranDiambilKp(jumlahUnit.getKeluaranDiambilKp().add(direksi.getKeluaranDiambilKp()));
        konsol.setMasukanDiambilKp(jumlahUnit.getMasukanDiambilKp().add(direksi.getMasukanDiambilKp()));

        listOfResult.add(jumlahUnit);
        listOfResult.add(direksi);
        listOfResult.add(konsol);

        logger.info("[PengajuanSetorBoImpl.getDetailPengajuanSetorPPn] end process <<<");
        return listOfResult;
    }

    private ProsesPpnKd rekapBantuan (PengajuanSetor data,String tipe){
        logger.info("[PengajuanSetorBoImpl.listPPh21Pengajuan] start process >>>");
        String status="";
        ProsesPpnKd direksi = new ProsesPpnKd();
        List<ItPengajuanSetorEntity> itPengajuanSetorEntityListKp = pengajuanSetorDao.getListPengajuanSetorByBulanTahunDanBranch(data.getBulan(),data.getTahun(),CommonConstant.ID_KANPUS);

        if (itPengajuanSetorEntityListKp.size()!=0){
            for (ItPengajuanSetorEntity pengajuanSetorEntity : itPengajuanSetorEntityListKp) {
                direksi.setBranchId(pengajuanSetorEntity.getBranchId());
                List<ImBranches> branches = branchDao.getListBranchById(pengajuanSetorEntity.getBranchId());
                for (ImBranches branches1 : branches) {
                    direksi.setBranchName(branches1.getBranchName());
                }

                //set keluaran
                direksi.setKeluaranUnit(pengajuanSetorEntity.getJumlahPpnKeluaran());
                direksi.setKeluaranKoreksi(BigDecimal.ZERO);
                direksi.setKeluaranDiambilKp(direksi.getKeluaranUnit().add(direksi.getKeluaranKoreksi()));

                if ("normal".equalsIgnoreCase(tipe)){
                    direksi.setMasukanUnit(pengajuanSetorEntity.getJumlahPpnMasukanB2().add(pengajuanSetorEntity.getJumlahPpnMasukanB3()));
                }else if ("b2".equalsIgnoreCase(tipe)){
                    direksi.setMasukanUnit(pengajuanSetorEntity.getJumlahPpnMasukanB2());
                }else if ("b3".equalsIgnoreCase(tipe)){
                    direksi.setMasukanUnit(pengajuanSetorEntity.getJumlahPpnMasukanB3());
                }
                //set masukan
                direksi.setMasukanKoreksi(BigDecimal.ZERO);
                direksi.setMasukanDiambilKp(direksi.getMasukanUnit().subtract(direksi.getMasukanKoreksi()));

            }
        }else{
            status="ERROR : PPN Kantor Pusat belum di proses atau belum di approve";
            logger.error("[PengajuanSetorBoImpl.prosesPPnKanpus]"+status);
            throw new GeneralBOException(status);
        }

        logger.info("[PengajuanSetorBoImpl.getDetailPengajuanSetorPPn] end process <<<");
        return direksi;
    }


    @Override
    public List<ProsesPpnKd> prosesPPnKanpusB2(PengajuanSetor bean){
        logger.info("[PengajuanSetorBoImpl.prosesPPnKanpusB2] start process >>>");
        String status = "";
        Integer urutan = 1;
        List<ProsesPpnKd> listOfResult = new ArrayList<>();
        ProsesPpnKd jumlahUnit = new ProsesPpnKd();
        ProsesPpnKd direksi = rekapBantuan(bean,"b2");
        ProsesPpnKd konsol = new ProsesPpnKd();
        List<ImBranches> branchesList = branchDao.getAllBranch();
        //menghapus kantor pusat
        int i=0;
        for (ImBranches branches : branchesList){
            if (CommonConstant.ID_KANPUS.equalsIgnoreCase(branches.getPrimaryKey().getId())){
                branchesList.remove(i);
                break;
            }
            i++;
        }

        List<ItPengajuanSetorEntity> itPengajuanSetorEntityList = pengajuanSetorDao.getListPengajuanSetorByBulanDanTahun(bean.getBulan(),bean.getTahun());

        jumlahUnit.setBranchId("unit");
        jumlahUnit.setBranchName("JUMLAH UNIT");

        konsol.setBranchId("konsol");
        konsol.setBranchName("JUMLAH KONSOLIDASI");

        //        if (branchesList.size()==itPengajuanSetorEntityList.size()){
        if (1==1){
            for (ItPengajuanSetorEntity pengajuanSetorEntity : itPengajuanSetorEntityList){
                ProsesPpnKd prosesPpnKd = new ProsesPpnKd();
                prosesPpnKd.setNo(urutan);
                prosesPpnKd.setBranchId(pengajuanSetorEntity.getBranchId());

                List<ImBranches> branches = branchDao.getListBranchById(pengajuanSetorEntity.getBranchId());
                for (ImBranches branch : branches){
                    prosesPpnKd.setBranchName(branch.getBranchName());
                }

                //set keluaran
                prosesPpnKd.setKeluaranUnit(pengajuanSetorEntity.getJumlahPpnKeluaran());
                prosesPpnKd.setKeluaranKoreksi(BigDecimal.ZERO);
                prosesPpnKd.setKeluaranDiambilKp(prosesPpnKd.getKeluaranUnit().add(prosesPpnKd.getKeluaranKoreksi()));

                //set masukan
                if (pengajuanSetorEntity.getJumlahPpnMasukanB2()==null){
                    pengajuanSetorEntity.setJumlahPpnMasukanB2(BigDecimal.ZERO);
                }

                prosesPpnKd.setMasukanUnit(pengajuanSetorEntity.getJumlahPpnMasukanB2());
                prosesPpnKd.setMasukanKoreksi(BigDecimal.ZERO);
                prosesPpnKd.setMasukanDiambilKp(prosesPpnKd.getMasukanUnit().subtract(prosesPpnKd.getMasukanKoreksi()));

                jumlahUnit.setKeluaranUnit(jumlahUnit.getKeluaranUnit().add(prosesPpnKd.getKeluaranUnit()));
                jumlahUnit.setMasukanUnit(jumlahUnit.getMasukanUnit().add(prosesPpnKd.getMasukanUnit()));
                jumlahUnit.setKeluaranKoreksi(jumlahUnit.getKeluaranKoreksi().add(prosesPpnKd.getKeluaranKoreksi()));
                jumlahUnit.setMasukanKoreksi(jumlahUnit.getMasukanKoreksi().add(prosesPpnKd.getMasukanKoreksi()));
                jumlahUnit.setKeluaranDiambilKp(jumlahUnit.getKeluaranDiambilKp().add(prosesPpnKd.getKeluaranDiambilKp()));
                jumlahUnit.setMasukanDiambilKp(jumlahUnit.getMasukanDiambilKp().add(prosesPpnKd.getMasukanDiambilKp()));

                urutan++;
                listOfResult.add(prosesPpnKd);
            }
        }else{
            status="ERROR : PPN unit masih ada yang belum di proses atau belum di approve";
            logger.error("[PengajuanSetorBoImpl.prosesPPnKanpusB2]"+status);
            throw new GeneralBOException(status);
        }


        jumlahUnit.setNo(urutan);
        direksi.setNo(urutan+1);
        konsol.setNo(urutan+2);

        konsol.setKeluaranUnit(jumlahUnit.getKeluaranUnit().add(direksi.getKeluaranUnit()));
        konsol.setMasukanUnit(jumlahUnit.getMasukanUnit().add(direksi.getMasukanUnit()));
        konsol.setKeluaranKoreksi(jumlahUnit.getKeluaranKoreksi().add(direksi.getKeluaranKoreksi()));
        konsol.setMasukanKoreksi(jumlahUnit.getMasukanKoreksi().add(direksi.getMasukanKoreksi()));
        konsol.setKeluaranDiambilKp(jumlahUnit.getKeluaranDiambilKp().add(direksi.getKeluaranDiambilKp()));
        konsol.setMasukanDiambilKp(jumlahUnit.getMasukanDiambilKp().add(direksi.getMasukanDiambilKp()));

        listOfResult.add(jumlahUnit);
        listOfResult.add(direksi);
        listOfResult.add(konsol);

        logger.info("[PengajuanSetorBoImpl.prosesPPnKanpusB2] end process <<<");
        return listOfResult;
    }

    @Override
    public List<ProsesPpnKd> prosesPPnKanpusB3(PengajuanSetor bean){
        logger.info("[PengajuanSetorBoImpl.prosesPPnKanpusB2] start process >>>");
        String status = "";
        Integer urutan = 1;
        List<ProsesPpnKd> listOfResult = new ArrayList<>();
        ProsesPpnKd jumlahUnit = new ProsesPpnKd();
        ProsesPpnKd direksi = rekapBantuan(bean,"b3");
        ProsesPpnKd konsol = new ProsesPpnKd();
        List<ImBranches> branchesList = branchDao.getAllBranch();
        //menghapus kantor pusat
        int i=0;
        for (ImBranches branches : branchesList){
            if (CommonConstant.ID_KANPUS.equalsIgnoreCase(branches.getPrimaryKey().getId())){
                branchesList.remove(i);
                break;
            }
            i++;
        }

        List<ItPengajuanSetorEntity> itPengajuanSetorEntityList = pengajuanSetorDao.getListPengajuanSetorByBulanDanTahun(bean.getBulan(),bean.getTahun());

        jumlahUnit.setBranchId("unit");
        jumlahUnit.setBranchName("JUMLAH UNIT");

        konsol.setBranchId("konsol");
        konsol.setBranchName("JUMLAH KONSOLIDASI");

        //        if (branchesList.size()==itPengajuanSetorEntityList.size()){
        if (1==1){
            for (ItPengajuanSetorEntity pengajuanSetorEntity : itPengajuanSetorEntityList){
                ProsesPpnKd prosesPpnKd = new ProsesPpnKd();
                prosesPpnKd.setNo(urutan);
                prosesPpnKd.setBranchId(pengajuanSetorEntity.getBranchId());

                List<ImBranches> branches = branchDao.getListBranchById(pengajuanSetorEntity.getBranchId());
                for (ImBranches branch : branches){
                    prosesPpnKd.setBranchName(branch.getBranchName());
                }

                //set keluaran
                prosesPpnKd.setKeluaranUnit(pengajuanSetorEntity.getJumlahPpnKeluaran());
                prosesPpnKd.setKeluaranKoreksi(BigDecimal.ZERO);
                prosesPpnKd.setKeluaranDiambilKp(prosesPpnKd.getKeluaranUnit().add(prosesPpnKd.getKeluaranKoreksi()));

                //set masukan
                if (pengajuanSetorEntity.getJumlahPpnMasukanB3()==null){
                    pengajuanSetorEntity.setJumlahPpnMasukanB3(BigDecimal.ZERO);
                }

                prosesPpnKd.setMasukanUnit(pengajuanSetorEntity.getJumlahPpnMasukanB3());
                prosesPpnKd.setMasukanKoreksi(BigDecimal.ZERO);
                prosesPpnKd.setMasukanDiambilKp(prosesPpnKd.getMasukanUnit().subtract(prosesPpnKd.getMasukanKoreksi()));

                jumlahUnit.setKeluaranUnit(jumlahUnit.getKeluaranUnit().add(prosesPpnKd.getKeluaranUnit()));
                jumlahUnit.setMasukanUnit(jumlahUnit.getMasukanUnit().add(prosesPpnKd.getMasukanUnit()));
                jumlahUnit.setKeluaranKoreksi(jumlahUnit.getKeluaranKoreksi().add(prosesPpnKd.getKeluaranKoreksi()));
                jumlahUnit.setMasukanKoreksi(jumlahUnit.getMasukanKoreksi().add(prosesPpnKd.getMasukanKoreksi()));
                jumlahUnit.setKeluaranDiambilKp(jumlahUnit.getKeluaranDiambilKp().add(prosesPpnKd.getKeluaranDiambilKp()));
                jumlahUnit.setMasukanDiambilKp(jumlahUnit.getMasukanDiambilKp().add(prosesPpnKd.getMasukanDiambilKp()));

                urutan++;
                listOfResult.add(prosesPpnKd);
            }
        }else{
            status="ERROR : PPN unit masih ada yang belum di proses atau belum di approve";
            logger.error("[PengajuanSetorBoImpl.prosesPPnKanpusB2]"+status);
            throw new GeneralBOException(status);
        }


        jumlahUnit.setNo(urutan);
        direksi.setNo(urutan+1);
        konsol.setNo(urutan+2);

        konsol.setKeluaranUnit(jumlahUnit.getKeluaranUnit().add(direksi.getKeluaranUnit()));
        konsol.setMasukanUnit(jumlahUnit.getMasukanUnit().add(direksi.getMasukanUnit()));
        konsol.setKeluaranKoreksi(jumlahUnit.getKeluaranKoreksi().add(direksi.getKeluaranKoreksi()));
        konsol.setMasukanKoreksi(jumlahUnit.getMasukanKoreksi().add(direksi.getMasukanKoreksi()));
        konsol.setKeluaranDiambilKp(jumlahUnit.getKeluaranDiambilKp().add(direksi.getKeluaranDiambilKp()));
        konsol.setMasukanDiambilKp(jumlahUnit.getMasukanDiambilKp().add(direksi.getMasukanDiambilKp()));

        listOfResult.add(jumlahUnit);
        listOfResult.add(direksi);
        listOfResult.add(konsol);

        logger.info("[PengajuanSetorBoImpl.prosesPPnKanpusB2] end process <<<");
        return listOfResult;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public BigDecimal perhitunganKembaliPpn(PengajuanSetor search){
        logger.info("[PengajuanSetorBoImpl.perhitunganKembaliPpn] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        MathContext mc = new MathContext(10);

        List<PerhitunganKembaliPpn> perhitunganKembaliPpnList = new ArrayList<>();
        PerhitunganKembaliPpn perhitunganKembaliPpnKd = new PerhitunganKembaliPpn();
        // SET MILIK BRANCH ID PERHITUNGAN PPN
        perhitunganKembaliPpnKd.setBranchId(CommonConstant.ID_KANPUS);

        // AMBIL PPN YANG SUDAH DI BAYARKAN ATAU DI KREDITKAN
        perhitunganKembaliPpnKd.setPpnPpnMasukanYangTelahDikreditkan(perhitunganPpnKdDao.getPpnMasukanYangTelahDikreditkan(search));
        perhitunganKembaliPpnKd.setDppPpnMasukanYangTelahDikreditkan(perhitunganKembaliPpnKd.getPpnPpnMasukanYangTelahDikreditkan().multiply(BigDecimal.TEN));

        // AMBIL PPN OBAT YANG ADA DI RAWAT JALAN ( PPN KELUARAN )
        perhitunganKembaliPpnKd.setPpnPenyerahanTerutangPpn(perhitunganPpnKdDao.getPenyerahanYangTerutangPpn(search));
        perhitunganKembaliPpnKd.setDppPenyerahanTerutangPpn(perhitunganKembaliPpnKd.getPpnPenyerahanTerutangPpn().multiply(BigDecimal.TEN));
        perhitunganKembaliPpnKd.setPpnPenyerahanTidakTerutangPpn(BigDecimal.ZERO);

        // AMBIL OBAT YANG ADA DI RAWAT INAP
        perhitunganKembaliPpnKd.setDppPenyerahanTidakTerutangPpn(perhitunganPpnKdDao.getPenyerahanTidakTerutang(search));
        perhitunganKembaliPpnKd.setDppTotalPendapatanRumahSakit(perhitunganKembaliPpnKd.getDppPenyerahanTerutangPpn().add(perhitunganKembaliPpnKd.getDppPenyerahanTidakTerutangPpn()));
        perhitunganKembaliPpnKd.setPpnTotalPendapatanRumahSakit(perhitunganKembaliPpnKd.getPpnPenyerahanTerutangPpn().add(perhitunganKembaliPpnKd.getPpnPenyerahanTidakTerutangPpn()));

        if (!perhitunganKembaliPpnKd.getDppTotalPendapatanRumahSakit().equals(BigDecimal.ZERO)){
            BigDecimal proses1 = perhitunganKembaliPpnKd.getDppPenyerahanTidakTerutangPpn().divide(perhitunganKembaliPpnKd.getDppTotalPendapatanRumahSakit(),mc);
            BigDecimal ppnMasukanYangTidakDapatDiKreditkan = proses1.multiply(perhitunganKembaliPpnKd.getPpnPpnMasukanYangTelahDikreditkan());
            perhitunganKembaliPpnKd.setPpnPpnMasukanYangTidakDapatDikreditkan(ppnMasukanYangTidakDapatDiKreditkan);
        }

        PerhitunganKembaliPpn caridata = new PerhitunganKembaliPpn();
        caridata.setTahun(search.getTahun());
        caridata.setBulan(CommonUtil.bulanSebelumnya(search.getBulan()));
        ItAkunPpnPerhitunganKembaliEntity ambilBulanLalu = perhitunganKembaliPpnDao.getPerhitunganKembaliPadaBulanLalu(caridata);
        perhitunganKembaliPpnKd.setPpnTelahDiperhitungkanKembaliPpnMasukan(ambilBulanLalu.getPpnTelahDiperhitungkanKembali().add(ambilBulanLalu.getPpnHasilPerhitunganKembaliPpn()));

        perhitunganKembaliPpnKd.setPpnHasilPerhitunganKembaliPpn(perhitunganKembaliPpnKd.getPpnPpnMasukanYangTidakDapatDikreditkan().subtract(perhitunganKembaliPpnKd.getPpnTelahDiperhitungkanKembaliPpnMasukan()));
        convertPerhitunganKembaliPpn(perhitunganKembaliPpnKd);

        List<PerhitunganKembaliUnit> perhitunganKembaliUnitList = new ArrayList<>();

        List<ImBranches> branchesList = branchDao.getAllBranch();

        //menghitung perhitungan kembali ke 1
        for (ImBranches branches : branchesList){
            if (!CommonConstant.ID_KANPUS.equalsIgnoreCase(branches.getPrimaryKey().getId())){
                search.setBranchId(branches.getPrimaryKey().getId());
                PerhitunganKembaliUnit perhitunganKembaliUnit = new PerhitunganKembaliUnit();

                //menghitung Nilai PM
                BigDecimal nilaiPajakMasukanObat = perhitunganPpnKdDao.getPpnMasukanYangTelahDikreditkanUnit(search);
                BigDecimal nilaiTotalNmuB2 = BigDecimal.ZERO;
                BigDecimal nilaiPm = nilaiPajakMasukanObat.add(nilaiTotalNmuB2);

                //menghitung perhitungan kembali
                BigDecimal nilaiPendapatanObatRawatInap = perhitunganPpnKdDao.getPenyerahanTidakTerutangUnit(search);
                BigDecimal nilaiPendapatanObatRawatJalan = perhitunganPpnKdDao.getDppPenyerahanYangTerutangPpnUnit(search);
                BigDecimal nilaiPendapatanApotek = nilaiPendapatanObatRawatInap.add(nilaiPendapatanObatRawatJalan);
                BigDecimal nilaiPerhitunganKembali = nilaiPm.multiply(nilaiPendapatanObatRawatInap.divide(nilaiPendapatanApotek,mc));

                perhitunganKembaliUnit.setBranchId(branches.getPrimaryKey().getId());
                perhitunganKembaliUnit.setPajakMasukan(nilaiPm);
                perhitunganKembaliUnit.setPerhitunganKembali1(nilaiPerhitunganKembali);

                perhitunganKembaliUnitList.add(perhitunganKembaliUnit);
            }
        }

        BigDecimal totalPerhitunganKembali = BigDecimal.ZERO;
        //menghitung perhitungan total perhitungan kembali 1
        for (PerhitunganKembaliUnit data : perhitunganKembaliUnitList){
            totalPerhitunganKembali=totalPerhitunganKembali.add(data.getPerhitunganKembali1());
        }


        for (PerhitunganKembaliUnit data : perhitunganKembaliUnitList){
            //Menghitung perhitungan kembali 2
            data.setPerhitunganKembali2(data.getPerhitunganKembali1().divide(totalPerhitunganKembali,mc).multiply(perhitunganKembaliPpnKd.getPpnPpnMasukanYangTidakDapatDikreditkan()));

            //perhitungan pm yang sudah dibuku
            PerhitunganKembaliUnit searchData = new PerhitunganKembaliUnit();
            searchData.setTahun(search.getTahun());
            searchData.setBulan(search.getBulan());
            ItAkunPerhitunganKembaliUnitEntity dataSebelumnya = perhitunganKembaliUnitDao.getPerhitunganKembaliPadaBulanSebelumnya(searchData);
            data.setPerhitunganPmSudahDibuku(dataSebelumnya.getPerhitunganPmYmhDibuku());

            //perhitungan pm ymh dibuku
            data.setPerhitunganPmYmhDibuku(data.getPerhitunganKembali2().subtract(data.getPerhitunganPmSudahDibuku()));
        }

        logger.info("[PengajuanSetorBoImpl.perhitunganKembaliPpn] end process <<<");

        session.setAttribute("perhitunganKembaliPpn",perhitunganKembaliPpnKd);
        session.setAttribute("perhitunganKembaliPpnUnit",perhitunganKembaliUnitList);
        return perhitunganKembaliPpnKd.getPpnHasilPerhitunganKembaliPpn();
    }

    @Override
    public void saveAddProsesPpnKd(PerhitunganPpnKd bean, List<ProsesPpnKd> prosesPpnKdListNormal, List<ProsesPpnKd> prosesPpnKdListB2, List<ProsesPpnKd> prosesPpnKdListB3, PerhitunganPpnKd perhitunganPpnKdListNormal, PerhitunganPpnKd perhitunganPpnKdListB2, PerhitunganPpnKd perhitunganPpnKdListB3,PerhitunganKembaliPpn perhitunganKembaliPpn,List<PerhitunganKembaliUnit> perhitunganKembaliUnitList){
        logger.info("[PengajuanSetorBoImpl.saveAddProsesPpnKd] start process >>>");

        String PerhitunganPpnKdId;
        ItAkunPerhitunganPpnKdEntity perhitunganPpnKdEntity = new ItAkunPerhitunganPpnKdEntity();

        //NORMAL
        try {
            PerhitunganPpnKdId = perhitunganPpnKdDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.saveAddProsesPpnKd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
        }

        perhitunganPpnKdEntity.setPerhitunganPpnKdId(PerhitunganPpnKdId);
        perhitunganPpnKdEntity.setTahun(perhitunganPpnKdListNormal.getTahun());
        perhitunganPpnKdEntity.setBulan(perhitunganPpnKdListNormal.getBulan());
        perhitunganPpnKdEntity.setCancelFlag(bean.getCancelFlag());

        perhitunganPpnKdEntity.setPpnMasukan(perhitunganPpnKdListNormal.getPpnMasukan());
        perhitunganPpnKdEntity.setTotalPpnMasukan(perhitunganPpnKdListNormal.getTotalPpnMasukan());
        perhitunganPpnKdEntity.setPpnKeluaran(perhitunganPpnKdListNormal.getPpnKeluaran());
        perhitunganPpnKdEntity.setKurangBayar(perhitunganPpnKdListNormal.getKurangBayar());
        perhitunganPpnKdEntity.setPerhitunganKembali(perhitunganPpnKdListNormal.getPerhitunganKembali());
        perhitunganPpnKdEntity.setTotalKurangBayar(perhitunganPpnKdListNormal.getTotalKurangBayar());
        perhitunganPpnKdEntity.setLbBulanYll(perhitunganPpnKdListNormal.getLbBulanYll());
        perhitunganPpnKdEntity.setPpnEkspor(perhitunganPpnKdListNormal.getPpnEkspor());
        perhitunganPpnKdEntity.setDipungutSendiri(perhitunganPpnKdListNormal.getDipungutSendiri());
        perhitunganPpnKdEntity.setDipungutOlehPemungut(perhitunganPpnKdListNormal.getDipungutOlehPemungut());
        perhitunganPpnKdEntity.setTidakDipungut(perhitunganPpnKdListNormal.getTidakDipungut());
        perhitunganPpnKdEntity.setDibebaskan(perhitunganPpnKdListNormal.getDibebaskan());
        perhitunganPpnKdEntity.setJumlahTerutangPpn(perhitunganPpnKdListNormal.getJumlahTerutangPpn());
        perhitunganPpnKdEntity.setJasaRs(perhitunganPpnKdListNormal.getJasaRs());
        perhitunganPpnKdEntity.setObatRawatInap(perhitunganPpnKdListNormal.getObatRawatInap());
        perhitunganPpnKdEntity.setJumlahTidakTerutang(perhitunganPpnKdListNormal.getJumlahTidakTerutang());
        perhitunganPpnKdEntity.setPenyerahanBarangDanJasa(perhitunganPpnKdListNormal.getPenyerahanBarangDanJasa());
        perhitunganPpnKdEntity.setTipe("normal");
        perhitunganPpnKdEntity.setAction(bean.getAction());
        perhitunganPpnKdEntity.setFlag(bean.getFlag());
        perhitunganPpnKdEntity.setCreatedDate(bean.getCreatedDate());
        perhitunganPpnKdEntity.setCreatedWho(bean.getCreatedWho());
        perhitunganPpnKdEntity.setLastUpdate(bean.getLastUpdate());
        perhitunganPpnKdEntity.setLastUpdateWho(bean.getLastUpdateWho());

        try {
            perhitunganPpnKdDao.addAndSave(perhitunganPpnKdEntity);
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.saveAddProsesPpnKd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
        }

        //save detail
        if (prosesPpnKdListNormal!=null){
            for (ProsesPpnKd prosesPpnKd : prosesPpnKdListNormal){
                ItAkunProsesPpnKdEntity prosesPpnKdEntity = new ItAkunProsesPpnKdEntity();
                prosesPpnKdEntity.setProsesPpnKdId(prosesPpnKdDao.getNextId());
                prosesPpnKdEntity.setPerhitunganPpnKdId(PerhitunganPpnKdId);
                prosesPpnKdEntity.setBranchId(prosesPpnKd.getBranchId());
                prosesPpnKdEntity.setBranchName(prosesPpnKd.getBranchName());
                prosesPpnKdEntity.setKeluaranUnit(prosesPpnKd.getKeluaranUnit());
                prosesPpnKdEntity.setMasukanUnit(prosesPpnKd.getMasukanUnit());
                prosesPpnKdEntity.setKeluaranKoreksi(prosesPpnKd.getKeluaranKoreksi());
                prosesPpnKdEntity.setMasukanKoreksi(prosesPpnKd.getMasukanKoreksi());
                prosesPpnKdEntity.setKeluaranDiambilKp(prosesPpnKd.getKeluaranDiambilKp());
                prosesPpnKdEntity.setMasukanDiambilKp(prosesPpnKd.getMasukanDiambilKp());
                prosesPpnKdEntity.setB4(prosesPpnKd.getB4());
                prosesPpnKdEntity.setNoUrut(prosesPpnKd.getNo());

                prosesPpnKdEntity.setAction(bean.getAction());
                prosesPpnKdEntity.setFlag(bean.getFlag());
                prosesPpnKdEntity.setCreatedDate(bean.getCreatedDate());
                prosesPpnKdEntity.setCreatedWho(bean.getCreatedWho());
                prosesPpnKdEntity.setLastUpdate(bean.getLastUpdate());
                prosesPpnKdEntity.setLastUpdateWho(bean.getLastUpdateWho());

                prosesPpnKdDao.addAndSave(prosesPpnKdEntity);
            }
        }


        //B2
        try {
            PerhitunganPpnKdId = perhitunganPpnKdDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.saveAddProsesPpnKd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
        }

        perhitunganPpnKdEntity = new ItAkunPerhitunganPpnKdEntity();
        perhitunganPpnKdEntity.setPerhitunganPpnKdId(PerhitunganPpnKdId);
        perhitunganPpnKdEntity.setTahun(perhitunganPpnKdListB2.getTahun());
        perhitunganPpnKdEntity.setBulan(perhitunganPpnKdListB2.getBulan());
        perhitunganPpnKdEntity.setCancelFlag(bean.getCancelFlag());
        perhitunganPpnKdEntity.setStatusB2(perhitunganPpnKdListB2.getStatusB2());

        perhitunganPpnKdEntity.setAction(bean.getAction());
        perhitunganPpnKdEntity.setFlag(bean.getFlag());
        perhitunganPpnKdEntity.setCreatedDate(bean.getCreatedDate());
        perhitunganPpnKdEntity.setCreatedWho(bean.getCreatedWho());
        perhitunganPpnKdEntity.setLastUpdate(bean.getLastUpdate());
        perhitunganPpnKdEntity.setLastUpdateWho(bean.getLastUpdateWho());
        perhitunganPpnKdEntity.setPpnMasukan(perhitunganPpnKdListB2.getPpnMasukan());
        perhitunganPpnKdEntity.setTotalPpnMasukan(perhitunganPpnKdListB2.getTotalPpnMasukan());
        perhitunganPpnKdEntity.setPpnKeluaran(perhitunganPpnKdListB2.getPpnKeluaran());
        perhitunganPpnKdEntity.setKurangBayar(perhitunganPpnKdListB2.getKurangBayar());
        perhitunganPpnKdEntity.setPerhitunganKembali(perhitunganPpnKdListB2.getPerhitunganKembali());
        perhitunganPpnKdEntity.setTotalKurangBayar(perhitunganPpnKdListB2.getTotalKurangBayar());
        perhitunganPpnKdEntity.setLbBulanYll(perhitunganPpnKdListB2.getLbBulanYll());
        perhitunganPpnKdEntity.setPpnEkspor(perhitunganPpnKdListB2.getPpnEkspor());
        perhitunganPpnKdEntity.setDipungutSendiri(perhitunganPpnKdListB2.getDipungutSendiri());
        perhitunganPpnKdEntity.setDipungutOlehPemungut(perhitunganPpnKdListB2.getDipungutOlehPemungut());
        perhitunganPpnKdEntity.setTidakDipungut(perhitunganPpnKdListB2.getTidakDipungut());
        perhitunganPpnKdEntity.setDibebaskan(perhitunganPpnKdListB2.getDibebaskan());
        perhitunganPpnKdEntity.setJumlahTerutangPpn(perhitunganPpnKdListB2.getJumlahTerutangPpn());
        perhitunganPpnKdEntity.setJasaRs(perhitunganPpnKdListB2.getJasaRs());
        perhitunganPpnKdEntity.setObatRawatInap(perhitunganPpnKdListB2.getObatRawatInap());
        perhitunganPpnKdEntity.setJumlahTidakTerutang(perhitunganPpnKdListB2.getJumlahTidakTerutang());
        perhitunganPpnKdEntity.setPenyerahanBarangDanJasa(perhitunganPpnKdListB2.getPenyerahanBarangDanJasa());
        perhitunganPpnKdEntity.setTipe("B2");

        try {
            perhitunganPpnKdDao.addAndSave(perhitunganPpnKdEntity);
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.saveAddProsesPpnKd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
        }

        //save detail
        if (prosesPpnKdListB2!=null){
            for (ProsesPpnKd prosesPpnKd : prosesPpnKdListB2){
                ItAkunProsesPpnKdEntity prosesPpnKdEntity = new ItAkunProsesPpnKdEntity();
                prosesPpnKdEntity.setProsesPpnKdId(prosesPpnKdDao.getNextId());
                prosesPpnKdEntity.setPerhitunganPpnKdId(PerhitunganPpnKdId);
                prosesPpnKdEntity.setBranchId(prosesPpnKd.getBranchId());
                prosesPpnKdEntity.setBranchName(prosesPpnKd.getBranchName());
                prosesPpnKdEntity.setKeluaranUnit(prosesPpnKd.getKeluaranUnit());
                prosesPpnKdEntity.setMasukanUnit(prosesPpnKd.getMasukanUnit());
                prosesPpnKdEntity.setKeluaranKoreksi(prosesPpnKd.getKeluaranKoreksi());
                prosesPpnKdEntity.setMasukanKoreksi(prosesPpnKd.getMasukanKoreksi());
                prosesPpnKdEntity.setKeluaranDiambilKp(prosesPpnKd.getKeluaranDiambilKp());
                prosesPpnKdEntity.setMasukanDiambilKp(prosesPpnKd.getMasukanDiambilKp());
                prosesPpnKdEntity.setB4(prosesPpnKd.getB4());
                prosesPpnKdEntity.setNoUrut(prosesPpnKd.getNo());

                prosesPpnKdEntity.setAction(bean.getAction());
                prosesPpnKdEntity.setFlag(bean.getFlag());
                prosesPpnKdEntity.setCreatedDate(bean.getCreatedDate());
                prosesPpnKdEntity.setCreatedWho(bean.getCreatedWho());
                prosesPpnKdEntity.setLastUpdate(bean.getLastUpdate());
                prosesPpnKdEntity.setLastUpdateWho(bean.getLastUpdateWho());

                prosesPpnKdDao.addAndSave(prosesPpnKdEntity);
            }
        }

        //save Perhitungan Kembali
        ItAkunPpnPerhitunganKembaliEntity perhitunganKembaliEntity = new ItAkunPpnPerhitunganKembaliEntity();
        perhitunganKembaliEntity.setPerhitunganKembaliPpnId(perhitunganKembaliPpnDao.getNextId());
        perhitunganKembaliEntity.setDppTotalPendapatanRumahSakit(perhitunganKembaliPpn.getDppTotalPendapatanRumahSakit());
        perhitunganKembaliEntity.setDppPenyerahanTidakTerutangPpn(perhitunganKembaliPpn.getDppPenyerahanTidakTerutangPpn());
        perhitunganKembaliEntity.setDppPpnMasukanYangTelahDireditkan(perhitunganKembaliPpn.getDppPpnMasukanYangTelahDikreditkan());
        perhitunganKembaliEntity.setPpnTotalPendapatanRumahSakit(perhitunganKembaliPpn.getPpnTotalPendapatanRumahSakit());
        perhitunganKembaliEntity.setPpnPenyerahanTidakTerutangPpn(perhitunganKembaliPpn.getPpnPenyerahanTidakTerutangPpn());
        perhitunganKembaliEntity.setPpnPenyerahanTerutangPpn(perhitunganKembaliPpn.getPpnPenyerahanTerutangPpn());
        perhitunganKembaliEntity.setPpnPpnMasukanYangTelahDikreditkan(perhitunganKembaliPpn.getPpnPpnMasukanYangTelahDikreditkan());
        perhitunganKembaliEntity.setPpnPpnMasukanYangTidakDapatDikreditkan(perhitunganKembaliPpn.getPpnPpnMasukanYangTidakDapatDikreditkan());
        perhitunganKembaliEntity.setPpnTelahDiperhitungkanKembali(perhitunganKembaliPpn.getPpnTelahDiperhitungkanKembaliPpnMasukan());
        perhitunganKembaliEntity.setPpnHasilPerhitunganKembaliPpn(perhitunganKembaliPpn.getPpnHasilPerhitunganKembaliPpn());
        perhitunganKembaliEntity.setTahun(perhitunganPpnKdListB2.getTahun());
        perhitunganKembaliEntity.setBulan(perhitunganPpnKdListB2.getBulan());
        perhitunganKembaliEntity.setProsesPpnKdId(perhitunganPpnKdEntity.getPerhitunganPpnKdId());
        perhitunganKembaliEntity.setAction(bean.getAction());
        perhitunganKembaliEntity.setFlag(bean.getFlag());
        perhitunganKembaliEntity.setCreatedDate(bean.getCreatedDate());
        perhitunganKembaliEntity.setCreatedWho(bean.getCreatedWho());
        perhitunganKembaliEntity.setLastUpdate(bean.getLastUpdate());
        perhitunganKembaliEntity.setLastUpdateWho(bean.getLastUpdateWho());

        perhitunganKembaliPpnDao.addAndSave(perhitunganKembaliEntity);

        //B3
        try {
            PerhitunganPpnKdId = perhitunganPpnKdDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.saveAddProsesPpnKd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
        }

        perhitunganPpnKdEntity = new ItAkunPerhitunganPpnKdEntity();
        perhitunganPpnKdEntity.setPerhitunganPpnKdId(PerhitunganPpnKdId);
        perhitunganPpnKdEntity.setTahun(perhitunganPpnKdListB3.getTahun());
        perhitunganPpnKdEntity.setBulan(perhitunganPpnKdListB3.getBulan());
        perhitunganPpnKdEntity.setCancelFlag(bean.getCancelFlag());

        perhitunganPpnKdEntity.setAction(bean.getAction());
        perhitunganPpnKdEntity.setFlag(bean.getFlag());
        perhitunganPpnKdEntity.setCreatedDate(bean.getCreatedDate());
        perhitunganPpnKdEntity.setCreatedWho(bean.getCreatedWho());
        perhitunganPpnKdEntity.setLastUpdate(bean.getLastUpdate());
        perhitunganPpnKdEntity.setLastUpdateWho(bean.getLastUpdateWho());
        perhitunganPpnKdEntity.setPpnMasukan(perhitunganPpnKdListB3.getPpnMasukan());
        perhitunganPpnKdEntity.setTotalPpnMasukan(perhitunganPpnKdListB3.getTotalPpnMasukan());
        perhitunganPpnKdEntity.setPpnKeluaran(perhitunganPpnKdListB3.getPpnKeluaran());
        perhitunganPpnKdEntity.setKurangBayar(perhitunganPpnKdListB3.getKurangBayar());
        perhitunganPpnKdEntity.setPerhitunganKembali(perhitunganPpnKdListB3.getPerhitunganKembali());
        perhitunganPpnKdEntity.setTotalKurangBayar(perhitunganPpnKdListB3.getTotalKurangBayar());
        perhitunganPpnKdEntity.setLbBulanYll(perhitunganPpnKdListB3.getLbBulanYll());
        perhitunganPpnKdEntity.setPpnEkspor(perhitunganPpnKdListB3.getPpnEkspor());
        perhitunganPpnKdEntity.setDipungutSendiri(perhitunganPpnKdListB3.getDipungutSendiri());
        perhitunganPpnKdEntity.setDipungutOlehPemungut(perhitunganPpnKdListB3.getDipungutOlehPemungut());
        perhitunganPpnKdEntity.setTidakDipungut(perhitunganPpnKdListB3.getTidakDipungut());
        perhitunganPpnKdEntity.setDibebaskan(perhitunganPpnKdListB3.getDibebaskan());
        perhitunganPpnKdEntity.setJumlahTerutangPpn(perhitunganPpnKdListB3.getJumlahTerutangPpn());
        perhitunganPpnKdEntity.setJasaRs(perhitunganPpnKdListB3.getJasaRs());
        perhitunganPpnKdEntity.setObatRawatInap(perhitunganPpnKdListB3.getObatRawatInap());
        perhitunganPpnKdEntity.setJumlahTidakTerutang(perhitunganPpnKdListB3.getJumlahTidakTerutang());
        perhitunganPpnKdEntity.setPenyerahanBarangDanJasa(perhitunganPpnKdListB3.getPenyerahanBarangDanJasa());
        perhitunganPpnKdEntity.setTipe("B3");

        try {
            perhitunganPpnKdDao.addAndSave(perhitunganPpnKdEntity);
        } catch (HibernateException e) {
            logger.error("[PengajuanSetorBoImpl.saveAddProsesPpnKd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
        }

        //save detail
        if (prosesPpnKdListB3!=null){
            for (ProsesPpnKd prosesPpnKd : prosesPpnKdListB3){
                ItAkunProsesPpnKdEntity prosesPpnKdEntity = new ItAkunProsesPpnKdEntity();
                prosesPpnKdEntity.setProsesPpnKdId(prosesPpnKdDao.getNextId());
                prosesPpnKdEntity.setPerhitunganPpnKdId(PerhitunganPpnKdId);
                prosesPpnKdEntity.setBranchId(prosesPpnKd.getBranchId());
                prosesPpnKdEntity.setBranchName(prosesPpnKd.getBranchName());
                prosesPpnKdEntity.setKeluaranUnit(prosesPpnKd.getKeluaranUnit());
                prosesPpnKdEntity.setMasukanUnit(prosesPpnKd.getMasukanUnit());
                prosesPpnKdEntity.setKeluaranKoreksi(prosesPpnKd.getKeluaranKoreksi());
                prosesPpnKdEntity.setMasukanKoreksi(prosesPpnKd.getMasukanKoreksi());
                prosesPpnKdEntity.setKeluaranDiambilKp(prosesPpnKd.getKeluaranDiambilKp());
                prosesPpnKdEntity.setMasukanDiambilKp(prosesPpnKd.getMasukanDiambilKp());
                prosesPpnKdEntity.setB4(prosesPpnKd.getB4());
                prosesPpnKdEntity.setNoUrut(prosesPpnKd.getNo());

                prosesPpnKdEntity.setAction(bean.getAction());
                prosesPpnKdEntity.setFlag(bean.getFlag());
                prosesPpnKdEntity.setCreatedDate(bean.getCreatedDate());
                prosesPpnKdEntity.setCreatedWho(bean.getCreatedWho());
                prosesPpnKdEntity.setLastUpdate(bean.getLastUpdate());
                prosesPpnKdEntity.setLastUpdateWho(bean.getLastUpdateWho());

                prosesPpnKdDao.addAndSave(prosesPpnKdEntity);
            }
        }

        if (perhitunganKembaliUnitList !=null){
            for (PerhitunganKembaliUnit data : perhitunganKembaliUnitList){
                ItAkunPerhitunganKembaliUnitEntity perhitunganKembaliUnitEntity = new ItAkunPerhitunganKembaliUnitEntity();
                perhitunganKembaliUnitEntity.setPerhitunganKembaliUnitId(perhitunganKembaliUnitDao.getNextId());
                perhitunganKembaliUnitEntity.setBranchId(data.getBranchId());
                perhitunganKembaliUnitEntity.setBulan(perhitunganPpnKdListNormal.getBulan());
                perhitunganKembaliUnitEntity.setTahun(perhitunganPpnKdListNormal.getTahun());
                perhitunganKembaliUnitEntity.setPerhitunganPpnKdId(PerhitunganPpnKdId);
                perhitunganKembaliUnitEntity.setPajakMasukan(data.getPajakMasukan());
                perhitunganKembaliUnitEntity.setPerhitunganKembali1(data.getPerhitunganKembali1());
                perhitunganKembaliUnitEntity.setPerhitunganKembali2(data.getPerhitunganKembali2());
                perhitunganKembaliUnitEntity.setPerhitunganPmSudahDibuku(data.getPerhitunganPmSudahDibuku());
                perhitunganKembaliUnitEntity.setPerhitunganPmYmhDibuku(data.getPerhitunganPmYmhDibuku());

                perhitunganKembaliUnitEntity.setAction(bean.getAction());
                perhitunganKembaliUnitEntity.setFlag(bean.getFlag());
                perhitunganKembaliUnitEntity.setCreatedDate(bean.getCreatedDate());
                perhitunganKembaliUnitEntity.setCreatedWho(bean.getCreatedWho());
                perhitunganKembaliUnitEntity.setLastUpdate(bean.getLastUpdate());
                perhitunganKembaliUnitEntity.setLastUpdateWho(bean.getLastUpdateWho());

                perhitunganKembaliUnitDao.addAndSave(perhitunganKembaliUnitEntity);
            }
        }

        logger.info("[PengajuanSetorBoImpl.saveAddProsesPpnKd] stop process >>>");
    }

    @Override
    public List<PerhitunganPpnKd> getSearchHomeProsesPpnKd(PerhitunganPpnKd bean) throws GeneralBOException {
        List<PerhitunganPpnKd> perhitunganPpnKdList = new ArrayList();

        try {
            // Get data from database by ID
            perhitunganPpnKdList = perhitunganPpnKdDao.getDataSearchHome(bean.getBulan(), bean.getTahun(), bean.getBulan1(),
                    bean.getTahun1());
        } catch (HibernateException e) {
            logger.error("[PayrollBoImpl.saveDelete] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
        }

        if(perhitunganPpnKdList.size() > 0){
            for(PerhitunganPpnKd perhitunganPpnKd : perhitunganPpnKdList){
                perhitunganPpnKd.setNamaBulan(CommonUtil.convertNumberToStringBulan(perhitunganPpnKd.getBulan()));
            }
        }
        return perhitunganPpnKdList;
    }

    @Override
    public PerhitunganPpnKd getPerhitunganPpnKdList(PerhitunganPpnKd search, String tipe){
        PerhitunganPpnKd result = new PerhitunganPpnKd();

        List<ItAkunPerhitunganPpnKdEntity> perhitunganPpnKdEntityList = perhitunganPpnKdDao.getPerhitunganPpnKdList(search,tipe);

        for (ItAkunPerhitunganPpnKdEntity perhitunganPpnKdEntity : perhitunganPpnKdEntityList){
                result = convertPerhitunganPpnKd(perhitunganPpnKdEntity);
        }

        return result;
    }

    @Override
    public List<ProsesPpnKd> getProsesPpnKdList(String perhitunganPpnKdId){
        List<ProsesPpnKd> listOfresult = new ArrayList<>();

        List<ItAkunProsesPpnKdEntity> prosesPpnKdEntityList = prosesPpnKdDao.getProsesPpnKdList(perhitunganPpnKdId);

        for (ItAkunProsesPpnKdEntity prosesPpnKdEntity : prosesPpnKdEntityList){
            listOfresult.add(convertProsesPpnKd(prosesPpnKdEntity));
        }
        return listOfresult;
    }

    @Override
    public List<ItAkunPerhitunganPpnKdEntity> getListUntukValidasi(PerhitunganPpnKd search){
        return perhitunganPpnKdDao.getListUntukValidasi(search);
    }

    private PerhitunganPpnKd convertPerhitunganPpnKd ( ItAkunPerhitunganPpnKdEntity data ){
        PerhitunganPpnKd result = new PerhitunganPpnKd();
        result.setPerhitunganPpnKdId(data.getPerhitunganPpnKdId());
        result.setTahun(data.getTahun());
        result.setBulan(data.getBulan());
        result.setApprovalFlag(data.getApprovalFlag());
        result.setApprovalWho(data.getApprovalId());
        result.setApprovalDate(data.getApprovalDate());
        result.setCancelFlag(data.getCancelFlag());
        result.setCancelWho(data.getCancelId());
        result.setCancelDate(data.getCancelDate());
        result.setPpnMasukan(data.getPpnMasukan());
        result.setTotalPpnMasukan(data.getTotalPpnMasukan());
        result.setPpnKeluaran(data.getPpnKeluaran());
        result.setKurangBayar(data.getKurangBayar());
        result.setPerhitunganKembali(data.getPerhitunganKembali());
        result.setTotalKurangBayar(data.getTotalKurangBayar());
        result.setLbBulanYll(data.getLbBulanYll());
        result.setPpnEkspor(data.getPpnEkspor());
        result.setDipungutSendiri(data.getDipungutSendiri());
        result.setDipungutOlehPemungut(data.getDipungutOlehPemungut());
        result.setDibebaskan(data.getDibebaskan());
        result.setJumlahTerutangPpn(data.getJumlahTerutangPpn());
        result.setJasaRs(data.getJasaRs());
        result.setObatRawatInap(data.getObatRawatInap());
        result.setJumlahTidakTerutang(data.getJumlahTidakTerutang());
        result.setPenyerahanBarangDanJasa(data.getPenyerahanBarangDanJasa());
        result.setTipe(data.getTipe());
        result.setStatusB2(data.getStatusB2());

        //convert to numeric
        result.setStPpnMasukan(CommonUtil.numbericFormat(result.getPpnMasukan(),"###,###"));
        result.setStTotalPpnMasukan(CommonUtil.numbericFormat(result.getTotalPpnMasukan(),"###,###"));
        result.setStPpnKeluaran(CommonUtil.numbericFormat(result.getPpnKeluaran(),"###,###"));
        result.setStKurangBayar(CommonUtil.numbericFormat(result.getKurangBayar(),"###,###"));
        result.setStPerhitunganKembali(CommonUtil.numbericFormat(result.getPerhitunganKembali(),"###,###"));
        result.setStTotalKurangBayar(CommonUtil.numbericFormat(result.getTotalKurangBayar(),"###,###"));
        result.setStLbBulanYll(CommonUtil.numbericFormat(result.getLbBulanYll(),"###,###"));
        result.setStPpnEkspor(CommonUtil.numbericFormat(result.getPpnEkspor(),"###,###"));
        result.setStDipungutSendiri(CommonUtil.numbericFormat(result.getDipungutSendiri(),"###,###"));
        result.setStDipungutOlehPemungut(CommonUtil.numbericFormat(result.getDipungutOlehPemungut(),"###,###"));
        result.setStDibebaskan(CommonUtil.numbericFormat(result.getDibebaskan(),"###,###"));
        result.setStJumlahTerutangPpn(CommonUtil.numbericFormat(result.getJumlahTerutangPpn(),"###,###"));
        result.setStJasaRs(CommonUtil.numbericFormat(result.getJasaRs(),"###,###"));
        result.setStObatRawatInap(CommonUtil.numbericFormat(result.getObatRawatInap(),"###,###"));
        result.setStJumlahTidakTerutang(CommonUtil.numbericFormat(result.getJumlahTidakTerutang(),"###,###"));
        result.setStPenyerahanBarangDanJasa(CommonUtil.numbericFormat(result.getPenyerahanBarangDanJasa(),"###,###"));


        return result;
    }

    private ProsesPpnKd convertProsesPpnKd ( ItAkunProsesPpnKdEntity data ){
        ProsesPpnKd result = new ProsesPpnKd();
        result.setProsesPpnKdId(data.getProsesPpnKdId());
        result.setBranchId(data.getBranchId());
        result.setBranchName(data.getBranchName());
        result.setKeluaranUnit(data.getKeluaranUnit());
        result.setMasukanUnit(data.getMasukanUnit());
        result.setKeluaranKoreksi(data.getKeluaranKoreksi());
        result.setKeluaranKoreksi(data.getKeluaranKoreksi());
        result.setMasukanDiambilKp(data.getMasukanDiambilKp());
        result.setKeluaranDiambilKp(data.getKeluaranDiambilKp());
        result.setB4(data.getB4());
        result.setNo(data.getNoUrut());

        result.setStKeluaranUnit(CommonUtil.numbericFormat(data.getKeluaranUnit(),"###,###"));
        result.setStMasukanUnit(CommonUtil.numbericFormat(data.getMasukanUnit(),"###,###"));
        result.setStKeluaranKoreksi(CommonUtil.numbericFormat(data.getKeluaranKoreksi(),"###,###"));
        result.setStMasukanKoreksi(CommonUtil.numbericFormat(data.getMasukanKoreksi(),"###,###"));
        result.setStKeluaranDiambilKp(CommonUtil.numbericFormat(data.getKeluaranDiambilKp(),"###,###"));
        result.setStMasukanDiambilKp(CommonUtil.numbericFormat(data.getMasukanDiambilKp(),"###,###"));

        result.setPerhitunganPpnKdId(data.getPerhitunganPpnKdId());

        return result;
    }

    private PerhitunganKembaliPpn convertPerhitunganKembaliPpn(PerhitunganKembaliPpn data){
        data.setStDppTotalPendapatanRumahSakit(CommonUtil.numbericFormat(data.getDppTotalPendapatanRumahSakit(),"###,###"));
        data.setStDppPenyerahanTidakTerutangPpn(CommonUtil.numbericFormat(data.getDppPenyerahanTidakTerutangPpn(),"###,###"));
        data.setStDppPenyerahanTerutangPpn(CommonUtil.numbericFormat(data.getDppPenyerahanTerutangPpn(),"###,###"));
        data.setStDppPpnMasukanYangTelahDikreditkan(CommonUtil.numbericFormat(data.getDppPpnMasukanYangTelahDikreditkan(),"###,###"));
        data.setStPpnTotalPendapatanRumahSakit(CommonUtil.numbericFormat(data.getPpnTotalPendapatanRumahSakit(),"###,###"));
        data.setStPpnPenyerahanTidakTerutangPpn(CommonUtil.numbericFormat(data.getPpnPenyerahanTidakTerutangPpn(),"###,###"));
        data.setStPpnPenyerahanTerutangPpn(CommonUtil.numbericFormat(data.getPpnPenyerahanTerutangPpn(),"###,###"));
        data.setStPpnPpnMasukanYangTelahDikreditkan(CommonUtil.numbericFormat(data.getPpnPpnMasukanYangTelahDikreditkan(),"###,###"));
        data.setStPpnPpnMasukanYangTidakDapatDikreditkan(CommonUtil.numbericFormat(data.getPpnPpnMasukanYangTidakDapatDikreditkan(),"###,###"));
        data.setStPpnTelahDiperhitungkanKembaliPpnMasukan(CommonUtil.numbericFormat(data.getPpnTelahDiperhitungkanKembaliPpnMasukan(),"###,###"));
        data.setStPpnHasilPerhitunganKembaliPpn(CommonUtil.numbericFormat(data.getPpnHasilPerhitunganKembaliPpn(),"###,###"));
        return data;
    }

    @Override
    public PerhitunganKembaliPpn getPerhitunganKembali(PerhitunganPpnKd search){
        PerhitunganKembaliPpn searchData = new PerhitunganKembaliPpn();
        searchData.setTahun(search.getTahun());
        searchData.setBulan(search.getBulan());
        PerhitunganKembaliPpn result = new PerhitunganKembaliPpn();
        List<ItAkunPpnPerhitunganKembaliEntity> perhitunganKembaliEntityList = perhitunganKembaliPpnDao.getPerhitunganKembaliPadaBulan(searchData);

        for (ItAkunPpnPerhitunganKembaliEntity perhitunganKembaliEntity : perhitunganKembaliEntityList){
            result.setDppTotalPendapatanRumahSakit(perhitunganKembaliEntity.getDppTotalPendapatanRumahSakit());
            result.setDppPenyerahanTidakTerutangPpn(perhitunganKembaliEntity.getDppPenyerahanTidakTerutangPpn());
            result.setDppPenyerahanTerutangPpn(perhitunganKembaliEntity.getDppPenyerahanTidakTerutangPpn());
            result.setDppPpnMasukanYangTelahDikreditkan(perhitunganKembaliEntity.getDppPpnMasukanYangTelahDireditkan());
            result.setPpnTotalPendapatanRumahSakit(perhitunganKembaliEntity.getPpnTotalPendapatanRumahSakit());
            result.setPpnPenyerahanTidakTerutangPpn(perhitunganKembaliEntity.getPpnPenyerahanTidakTerutangPpn());
            result.setPpnPenyerahanTerutangPpn(perhitunganKembaliEntity.getPpnPenyerahanTerutangPpn());
            result.setPpnPpnMasukanYangTelahDikreditkan(perhitunganKembaliEntity.getPpnPpnMasukanYangTelahDikreditkan());
            result.setPpnPpnMasukanYangTidakDapatDikreditkan(perhitunganKembaliEntity.getPpnPpnMasukanYangTidakDapatDikreditkan());
            result.setPpnTelahDiperhitungkanKembaliPpnMasukan(perhitunganKembaliEntity.getPpnTelahDiperhitungkanKembali());
            result.setPpnHasilPerhitunganKembaliPpn(perhitunganKembaliEntity.getPpnHasilPerhitunganKembaliPpn());

            convertPerhitunganKembaliPpn(result);

        }
        return result;
    }

    @Override
    public PerhitunganPpnKd getModalPostingPpn(String bulan, String tahun){
        PerhitunganPpnKd result = new PerhitunganPpnKd();

        List<ItAkunPerhitunganPpnKdEntity> itAkunPerhitunganPpnKdEntityList = perhitunganPpnKdDao.getForModalPosting(bulan,tahun,"B2");

        for (ItAkunPerhitunganPpnKdEntity perhitunganPpnKdEntity : itAkunPerhitunganPpnKdEntityList){
            result = convertPerhitunganPpnKd(perhitunganPpnKdEntity);
        }

        return result;
    }

    @Override
    public BigDecimal getJasaRs(PengajuanSetor search){
        BigDecimal totalJasaRs = perhitunganPpnKdDao.getJasaRs(search);

        return totalJasaRs;
    }

    @Override
    public BigDecimal getObatrawatInap(PengajuanSetor search){
        BigDecimal obatRawatInap = perhitunganPpnKdDao.getObatRi(search);

        return obatRawatInap;
    }

    @Override
    public Map getBillingForPostingProsesPpnKoreksi(String bulan,String tahun){
        logger.info("[PengajuanSetorBoImpl.getBillingForPostingProsesPpnKoreksi] start process <<<");
        Map dataBilling = new HashMap();
        List<Map> ppnKeluaranList = new ArrayList<>();
        List<Map> ppnMasukanList = new ArrayList<>();

        BigDecimal totalPpnKeluaran = BigDecimal.ZERO;
        BigDecimal totalPpnMasukan = BigDecimal.ZERO;

        List<ItPengajuanSetorEntity> pengajuanSetorEntityList = pengajuanSetorDao.getListPengajuanSetorByBulanDanTahun(bulan,tahun);

        for (ItPengajuanSetorEntity pengajuanSetorEntity : pengajuanSetorEntityList){
            List<ItPengajuanSetorDetailEntity> pengajuanSetorDetailEntityListB2 = pengajuanSetorDetailDao.getByPengajuanSetorIdAndTipe(pengajuanSetorEntity.getPengajuanSetorId(),"PPN Masukan B2");
            List<ItPengajuanSetorDetailEntity> pengajuanSetorDetailEntityListKeluaran = pengajuanSetorDetailDao.getByPengajuanSetorIdAndTipe(pengajuanSetorEntity.getPengajuanSetorId(),"PPN Keluaran");

            //Map Untuk B2
            for (ItPengajuanSetorDetailEntity pengajuanSetorDetailEntity : pengajuanSetorDetailEntityListB2){
                Map mapB2 = new HashMap();
                mapB2.put("master_id",pengajuanSetorDetailEntity.getPersonId());
                mapB2.put("bukti",pengajuanSetorDetailEntity.getTransaksiId());
                mapB2.put("nilai",pengajuanSetorDetailEntity.getJumlah());

                totalPpnMasukan=totalPpnMasukan.add(pengajuanSetorDetailEntity.getJumlah());
                ppnMasukanList.add(mapB2);
            }

            //Map Untuk Keluaran
            for (ItPengajuanSetorDetailEntity pengajuanSetorDetailEntity : pengajuanSetorDetailEntityListKeluaran){
                Map mapKeluaran = new HashMap();
                mapKeluaran.put("master_id",pengajuanSetorDetailEntity.getPersonId());
                mapKeluaran.put("bukti",pengajuanSetorDetailEntity.getTransaksiId());
                mapKeluaran.put("nilai",pengajuanSetorDetailEntity.getJumlah());

                totalPpnKeluaran=totalPpnKeluaran.add(pengajuanSetorDetailEntity.getJumlah());
                ppnKeluaranList.add(mapKeluaran);
            }
        }

        Map mapPiutangPpn = new HashMap();
        BigDecimal jumlahPerhitunganKembali =totalPpnMasukan.subtract(totalPpnKeluaran).abs();

        mapPiutangPpn.put("master_id",CommonConstant.JUNK_MASTER_PIUTANG_PPN_MASUKAN);
        mapPiutangPpn.put("nilai",jumlahPerhitunganKembali);

        dataBilling.put("ppn_masukan",ppnMasukanList);
        dataBilling.put("ppn_keluaran",ppnKeluaranList);
        dataBilling.put("piutang_ppn",mapPiutangPpn);

        logger.info("[PengajuanSetorBoImpl.getBillingForPostingProsesPpnKoreksi] stop process >>>");
        return dataBilling;
    }

    @Override
    public Map getBillingForPostingProsesPpnKasKeluar(String bulan, String tahun, String kas){
        logger.info("[PengajuanSetorBoImpl.getBillingForPostingProsesPpnKasKeluar] start process <<<");
        Map dataBilling = new HashMap();

        List<ItAkunPerhitunganPpnKdEntity> perhitunganPpnKdEntityList = perhitunganPpnKdDao.getForModalPosting(bulan,tahun,"B2");

        if (perhitunganPpnKdEntityList.size()!=1){
            String status = "ERROR : Data Perhitungan lebih dari 1 , cek kembali datanya ";
            logger.error(status);
            throw new GeneralBOException(status);
        }
        for (ItAkunPerhitunganPpnKdEntity perhitunganPpnKdEntity : perhitunganPpnKdEntityList){
            Map hutangPpn = new HashMap();
            hutangPpn.put("nilai",perhitunganPpnKdEntity.getTotalKurangBayar());

            Map Mapkas = new HashMap();
            Mapkas.put("metode_bayar","transfer");
            Mapkas.put("bank", kas);
            Mapkas.put("nilai",perhitunganPpnKdEntity.getTotalKurangBayar());

            dataBilling.put("hutang_ppn",hutangPpn);
            dataBilling.put("kas",Mapkas);
        }

        logger.info("[PengajuanSetorBoImpl.getBillingForPostingProsesPpnKasKeluar] stop process >>>");
        return dataBilling;
    }

    @Override
    public void cancelProsesPpn(PerhitunganPpnKd bean) throws GeneralBOException {
        logger.info("[PengajuanSetorBoImpl.cancelProsesPpn] start process >>>");
        List<ItAkunPerhitunganPpnKdEntity> perhitunganPpnKdEntityList = new ArrayList<>();
        PerhitunganPpnKd search = new PerhitunganPpnKd();
        search.setBulan(bean.getBulan());
        search.setTahun(bean.getTahun());

        if (bean!=null) {
            try {
                // Get data from database by ID
                perhitunganPpnKdEntityList = perhitunganPpnKdDao.getAllPerhitunganPpnKdList(search);
            } catch (HibernateException e) {
                logger.error("[PengajuanSetorBoImpl.cancelProsesPpn] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
            }

            for (ItAkunPerhitunganPpnKdEntity perhitunganPpnKdEntity : perhitunganPpnKdEntityList){
                perhitunganPpnKdEntity.setCancelFlag(bean.getCancelFlag());
                perhitunganPpnKdEntity.setCancelDate(bean.getCancelDate());
                perhitunganPpnKdEntity.setCancelId(bean.getCancelWho());

                perhitunganPpnKdEntity.setFlag(bean.getFlag());
                perhitunganPpnKdEntity.setAction(bean.getAction());
                perhitunganPpnKdEntity.setLastUpdateWho(bean.getLastUpdateWho());
                perhitunganPpnKdEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    perhitunganPpnKdDao.updateAndSave(perhitunganPpnKdEntity);
                } catch (HibernateException e) {
                    logger.error("[PengajuanSetorBoImpl.cancelProsesPpn] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data, please info to your admin..." + e.getMessage());
                }
            }
        }
        logger.info("[PengajuanSetorBoImpl.cancelProsesPpn] end process <<<");
    }

    @Override
    public Map getBillingForPostingPengelompokanPpnKeluaran(String bulan,String tahun,String branchId){
        logger.info("[PengajuanSetorBoImpl.getBillingForPostingPengelompokanPpnKeluaran] start process <<<");
        Map dataBilling = new HashMap();
        List<Map> ppnKeluaranList = new ArrayList<>();

        BigDecimal totalPpnKeluaran = BigDecimal.ZERO;

        List<ItPengajuanSetorEntity> pengajuanSetorEntityList = pengajuanSetorDao.getListPengajuanSetorByBulanTahunDanBranch(bulan,tahun,branchId);

        for (ItPengajuanSetorEntity pengajuanSetorEntity : pengajuanSetorEntityList){
            List<ItPengajuanSetorDetailEntity> pengajuanSetorDetailEntityListKeluaran = pengajuanSetorDetailDao.getByPengajuanSetorIdAndTipe(pengajuanSetorEntity.getPengajuanSetorId(),"PPN Keluaran");

            //Map Untuk Keluaran
            for (ItPengajuanSetorDetailEntity pengajuanSetorDetailEntity : pengajuanSetorDetailEntityListKeluaran){
                Map mapKeluaran = new HashMap();
                mapKeluaran.put("master_id",pengajuanSetorDetailEntity.getPersonId());
                mapKeluaran.put("bukti",pengajuanSetorDetailEntity.getTransaksiId());
                mapKeluaran.put("nilai",pengajuanSetorDetailEntity.getJumlah());

                totalPpnKeluaran=totalPpnKeluaran.add(pengajuanSetorDetailEntity.getJumlah());
                ppnKeluaranList.add(mapKeluaran);
            }
        }

        Map mapTotalPpnKeluaran = new HashMap();
        mapTotalPpnKeluaran.put("master_id",CommonConstant.JUNK_MASTER_PIUTANG_PPN_KELUARAN); ///////////////////////////////////////////////////////
        mapTotalPpnKeluaran.put("nilai",totalPpnKeluaran);

        dataBilling.put("ppn_keluaran",ppnKeluaranList);
        dataBilling.put("total_ppn_keluaran",mapTotalPpnKeluaran);

        logger.info("[PengajuanSetorBoImpl.getBillingForPostingPengelompokanPpnKeluaran] stop process >>>");
        return dataBilling;
    }

    @Override
    public Map getBillingForPostingPengelompokanPpnMasukan(String bulan, String tahun, String branchId){
        logger.info("[PengajuanSetorBoImpl.getBillingForPostingPengelompokanPpnMasukan] start process <<<");
        Map dataBilling = new HashMap();
        List<Map> ppnMasukanList = new ArrayList<>();

        BigDecimal totalPpnMasukan = BigDecimal.ZERO;

        List<ItPengajuanSetorEntity> pengajuanSetorEntityList = pengajuanSetorDao.getListPengajuanSetorByBulanTahunDanBranch(bulan,tahun,branchId);

        for (ItPengajuanSetorEntity pengajuanSetorEntity : pengajuanSetorEntityList){
            List<ItPengajuanSetorDetailEntity> pengajuanSetorDetailEntityListMasukan = pengajuanSetorDetailDao.getByPengajuanSetorIdAndTipe(pengajuanSetorEntity.getPengajuanSetorId(),"PPN Masukan B2");

            for (ItPengajuanSetorDetailEntity pengajuanSetorDetailEntity : pengajuanSetorDetailEntityListMasukan){
                Map mapMasukan = new HashMap();
                mapMasukan.put("master_id",pengajuanSetorDetailEntity.getPersonId());
                mapMasukan.put("bukti",pengajuanSetorDetailEntity.getTransaksiId());
                mapMasukan.put("nilai",pengajuanSetorDetailEntity.getJumlah());

                totalPpnMasukan=totalPpnMasukan.add(pengajuanSetorDetailEntity.getJumlah());
                ppnMasukanList.add(mapMasukan);
            }
        }

        Map mapTotalPpnMasukan = new HashMap();
        mapTotalPpnMasukan.put("master_id",CommonConstant.JUNK_MASTER_PIUTANG_PPN_MASUKAN); ///////////////////////////////////////////////////////
        mapTotalPpnMasukan.put("nilai",totalPpnMasukan);

        dataBilling.put("ppn_masukan",ppnMasukanList);
        dataBilling.put("total_ppn_masukan",mapTotalPpnMasukan);

        logger.info("[PengajuanSetorBoImpl.getBillingForPostingPengelompokanPpnMasukan] stop process >>>");
        return dataBilling;
    }

    @Override
    public Map getBillingForPostingPpnKeluaranRk(String branchId,Map data){
        logger.info("[PengajuanSetorBoImpl.getBillingForPostingPpnKeluaranRk] start process <<<");
        Map dataBilling = new HashMap();
        Map mapPpnKeluaran = (Map) data.get("total_ppn_keluaran");

        ImBranchesPK imBranchesPK = new ImBranchesPK();
        imBranchesPK.setId(branchId);
        ImBranches branches = branchDao.getById("primaryKey",imBranchesPK);

        Map rkUnit = new HashMap();
        rkUnit.put("nilai",mapPpnKeluaran.get("nilai"));
        rkUnit.put("rekening_id",kodeRekeningDao.getRekeningIdByCoa(branches.getCoaRk()));

        dataBilling.put("ppn_keluaran",mapPpnKeluaran);
        dataBilling.put("rk_kd_unit",rkUnit);

        logger.info("[PengajuanSetorBoImpl.getBillingForPostingPpnKeluaranRk] stop process >>>");
        return dataBilling;
    }

    @Override
    public Map getBillingForPostingPpnMasukanRk(String branchId, Map data){
        logger.info("[PengajuanSetorBoImpl.getBillingForPostingPpnMasukanRk] start process <<<");
        Map dataBilling = new HashMap();
        Map mapPpnMasukan = (Map) data.get("total_ppn_masukan");

        ImBranchesPK imBranchesPK = new ImBranchesPK();
        imBranchesPK.setId(branchId);
        ImBranches branches = branchDao.getById("primaryKey",imBranchesPK);

        Map rkUnit = new HashMap();
        rkUnit.put("nilai",mapPpnMasukan.get("nilai"));
        rkUnit.put("rekening_id",kodeRekeningDao.getRekeningIdByCoa(branches.getCoaRk()));

        dataBilling.put("ppn_masukan",mapPpnMasukan);
        dataBilling.put("rk_kd_unit",rkUnit);

        logger.info("[PengajuanSetorBoImpl.getBillingForPostingPpnMasukanRk] stop process >>>");
        return dataBilling;
    }

    @Override
    public Map getBillingForPostingRkPpnKeluaran(String branchId, Map data,String buktiJurnal5) {
        logger.info("[PengajuanSetorBoImpl.getBillingForPostingRkPpnKeluaran] start process <<<");
        Map dataBilling = new HashMap();
        Map mapPpnKeluaran = (Map) data.get("ppn_keluaran");
        if (!"".equalsIgnoreCase(buktiJurnal5)&&buktiJurnal5!=null){
            mapPpnKeluaran.put("bukti",buktiJurnal5);
        }

        ImBranchesPK imBranchesPK = new ImBranchesPK();
        imBranchesPK.setId(branchId);
        ImBranches branches = branchDao.getById("primaryKey",imBranchesPK);

        Map rkUnit = new HashMap();
        rkUnit.put("nilai",mapPpnKeluaran.get("nilai"));
        rkUnit.put("rekening_id",kodeRekeningDao.getRekeningIdByCoa(branches.getCoaRk()));

        dataBilling.put("ppn_keluaran",mapPpnKeluaran);
        dataBilling.put("rk_kd_unit",rkUnit);

        logger.info("[PengajuanSetorBoImpl.getBillingForPostingRkPpnKeluaran] stop process >>>");
        return dataBilling;
    }

    @Override
    public Map getBillingForPostingRkPpnMasukan(String branchId, Map data,String buktiJurnal5) {
        logger.info("[PengajuanSetorBoImpl.getBillingForPostingRkPpnMasukan] start process <<<");
        Map dataBilling = new HashMap();
        Map mapPpnMasukan = (Map) data.get("ppn_masukan");
        if (!"".equalsIgnoreCase(buktiJurnal5)&&buktiJurnal5!=null){
            mapPpnMasukan.put("bukti",buktiJurnal5);
        }
        ImBranchesPK imBranchesPK = new ImBranchesPK();
        imBranchesPK.setId(branchId);
        ImBranches branches = branchDao.getById("primaryKey",imBranchesPK);

        Map rkUnit = new HashMap();
        rkUnit.put("nilai",mapPpnMasukan.get("nilai"));
        rkUnit.put("rekening_id",kodeRekeningDao.getRekeningIdByCoa(branches.getCoaRk()));

        dataBilling.put("ppn_masukan",mapPpnMasukan);
        dataBilling.put("rk_kd_unit",rkUnit);

        logger.info("[PengajuanSetorBoImpl.getBillingForPostingRkPpnMasukan] stop process >>>");
        return dataBilling;
    }

    @Override
    public Map getBillingForPostingPengurangPpnKeluaran(String bulan, String tahun, BigDecimal totalKeluaranJurnal5, String buktiJurnal5, BigDecimal totalMasukanJurnal6, String buktiJurnal6) {
        logger.info("[PengajuanSetorBoImpl.getBillingForPostingPengurangPpnKeluaran] start process <<<");
        Map dataBilling = new HashMap();

        BigDecimal biayaObatRj = perhitunganKembaliPpnDao.getPpnHasilPerhitunganKembali(bulan,tahun);
        BigDecimal ppnKeluaran;
        BigDecimal piutangPajakKeluaran = BigDecimal.ZERO;


        if (totalMasukanJurnal6.subtract(biayaObatRj).compareTo(totalKeluaranJurnal5)<0){
            ppnKeluaran=totalMasukanJurnal6.subtract(biayaObatRj);
        }else{
            ppnKeluaran = totalKeluaranJurnal5;
        }

        if (totalMasukanJurnal6.subtract(biayaObatRj).compareTo(totalKeluaranJurnal5)>0){
            piutangPajakKeluaran = totalMasukanJurnal6.subtract(biayaObatRj).subtract(ppnKeluaran);
        }

        Map mapBiayaObatRj = new HashMap();
        mapBiayaObatRj.put("nilai",biayaObatRj);

        Map mapPpnKeluaran = new HashMap();
        mapPpnKeluaran.put("bukti",buktiJurnal5);
        mapPpnKeluaran.put("nilai",ppnKeluaran);
        mapPpnKeluaran.put("master_id",CommonConstant.JUNK_MASTER_PIUTANG_PPN_KELUARAN);

        Map mapPiutangPpnKeluaran = new HashMap();
        mapPiutangPpnKeluaran.put("nilai",piutangPajakKeluaran);
        mapPiutangPpnKeluaran.put("master_id",CommonConstant.JUNK_MASTER_PIUTANG_PPN_KELUARAN);

        Map mapPpnMasukan = new HashMap();
        mapPpnMasukan.put("bukti",buktiJurnal6);
        mapPpnMasukan.put("nilai",totalMasukanJurnal6);
        mapPpnMasukan.put("master_id",CommonConstant.JUNK_MASTER_PIUTANG_PPN_MASUKAN);

        dataBilling.put("biaya_obat_rj",mapBiayaObatRj);
        dataBilling.put("ppn_keluaran",mapPpnKeluaran);
        dataBilling.put("piutang_ppn_keluaran",mapPiutangPpnKeluaran);
        dataBilling.put("ppn_masukan",mapPpnMasukan);

        logger.info("[PengajuanSetorBoImpl.getBillingForPostingPengurangPpnKeluaran] stop process >>>");
        return dataBilling;
    }

    @Override
    public Map getBillingForPostingPembayaranPpnKeluaran(String bulan, String tahun, BigDecimal sisaPpnKeluaran, String buktiJurnal5, String coaBank) {
        logger.info("[PengajuanSetorBoImpl.getBillingForPostingPembayaranPpnKeluaran] start process <<<");
        Map dataBilling = new HashMap();
        BigDecimal piutangPajakKeluaran = perhitunganPpnKdDao.getLastNilaiPiutangPajakKeluaran(bulan,tahun);
        String buktiPiutangKeluaran =perhitunganPpnKdDao.getLastBuktiPiutangPajakKeluaran(bulan,tahun);

        Map mapPpnKeluaran = new HashMap();
        mapPpnKeluaran.put("bukti",buktiJurnal5);
        mapPpnKeluaran.put("nilai",sisaPpnKeluaran);
        mapPpnKeluaran.put("master_id",CommonConstant.JUNK_MASTER_PIUTANG_PPN_KELUARAN);

        Map mapPiutangPpnKeluaran = new HashMap();
        mapPiutangPpnKeluaran.put("nilai",piutangPajakKeluaran);
        mapPiutangPpnKeluaran.put("bukti",buktiPiutangKeluaran);
        mapPiutangPpnKeluaran.put("master_id",CommonConstant.JUNK_MASTER_PIUTANG_PPN_KELUARAN);

        Map kas = new HashMap();
        kas.put("metode_bayar","transfer");
        kas.put("bank", coaBank);
        kas.put("nilai",sisaPpnKeluaran.subtract(piutangPajakKeluaran));

        dataBilling.put("ppn_keluaran",mapPpnKeluaran);
        dataBilling.put("piutang_ppn_keluaran",mapPiutangPpnKeluaran);
        dataBilling.put("kas",kas);

        logger.info("[PengajuanSetorBoImpl.getBillingForPostingPembayaranPpnKeluaran] stop process >>>");
        return dataBilling;
    }

    @Override
    public Map getBillingForPostingPembagianRkUntukUnit(String bulan, String tahun, String branchId, String sumberBiayaObat) {
        logger.info("[PengajuanSetorBoImpl.getBillingForPostingPembagianRkUntukUnit] start process <<<");
        Map dataBilling = new HashMap();
        BigDecimal nilaiPerhitungan = BigDecimal.ZERO;
        ImBranchesPK imBranchesPK = new ImBranchesPK();
        imBranchesPK.setId(branchId);
        ImBranches branches = branchDao.getById("primaryKey",imBranchesPK);

        PerhitunganKembaliUnit searchData = new PerhitunganKembaliUnit();
        searchData.setBulan(bulan);
        searchData.setTahun(tahun);
        searchData.setBranchId(branchId);
        List<ItAkunPerhitunganKembaliUnitEntity> perhitunganKembaliUnitEntityList = perhitunganKembaliUnitDao.getPerhitunganKembaliPadaBulan(searchData);
        for (ItAkunPerhitunganKembaliUnitEntity data : perhitunganKembaliUnitEntityList){
            nilaiPerhitungan=data.getPerhitunganPmYmhDibuku();
        }

        Map rkUnit = new HashMap();
        rkUnit.put("nilai",nilaiPerhitungan);
        rkUnit.put("rekening_id",kodeRekeningDao.getRekeningIdByCoa(branches.getCoaRk()));

        Map biayaObatRj = new HashMap();
        biayaObatRj.put("bukti",sumberBiayaObat);
        biayaObatRj.put("nilai",nilaiPerhitungan);

        dataBilling.put("rk_kd_unit",rkUnit);
        dataBilling.put("biaya_obat_rj",biayaObatRj);

        logger.info("[PengajuanSetorBoImpl.getBillingForPostingPembagianRkUntukUnit] stop process >>>");
        return dataBilling;
    }

    @Override
    public Map getBillingForPostingPenerimaanRkUntukUnit(String bulan, String tahun, String branchId) {
        logger.info("[PengajuanSetorBoImpl.getBillingForPostingPenerimaanRkUntukUnit] start process <<<");
        Map dataBilling = new HashMap();
        BigDecimal nilaiPerhitungan = BigDecimal.ZERO;
        ImBranchesPK imBranchesPK = new ImBranchesPK();
        imBranchesPK.setId(branchId);
        ImBranches branches = branchDao.getById("primaryKey",imBranchesPK);

        PerhitunganKembaliUnit searchData = new PerhitunganKembaliUnit();
        searchData.setBulan(bulan);
        searchData.setTahun(tahun);
        searchData.setBranchId(branchId);
        List<ItAkunPerhitunganKembaliUnitEntity> perhitunganKembaliUnitEntityList = perhitunganKembaliUnitDao.getPerhitunganKembaliPadaBulan(searchData);
        for (ItAkunPerhitunganKembaliUnitEntity data : perhitunganKembaliUnitEntityList){
            nilaiPerhitungan=data.getPerhitunganPmYmhDibuku();
        }

        Map rkUnit = new HashMap();
        rkUnit.put("nilai",nilaiPerhitungan);
        rkUnit.put("rekening_id",kodeRekeningDao.getRekeningIdByCoa(branches.getCoaRk()));

        Map biayaObatRj = new HashMap();
        biayaObatRj.put("nilai",nilaiPerhitungan);

        dataBilling.put("rk_kd_unit",rkUnit);
        dataBilling.put("biaya_obat_rj",biayaObatRj);

        logger.info("[PengajuanSetorBoImpl.getBillingForPostingPenerimaanRkUntukUnit] stop process >>>");
        return dataBilling;
    }
}