package com.neurix.akuntansi.transaksi.pengajuanSetor.bo.impl;

import com.neurix.akuntansi.transaksi.pengajuanSetor.bo.PengajuanSetorBo;
import com.neurix.akuntansi.transaksi.pengajuanSetor.dao.PengajuanSetorDao;
import com.neurix.akuntansi.transaksi.pengajuanSetor.dao.PengajuanSetorDetailDao;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.ItPengajuanSetorDetailEntity;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.ItPengajuanSetorEntity;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.PengajuanSetor;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.PengajuanSetorDetail;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
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
import org.hibernate.HibernateException;

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
public class PengajuanSetorBoImpl implements PengajuanSetorBo {

    protected static transient Logger logger = Logger.getLogger(PengajuanSetorBoImpl.class);
    private PayrollDao payrollDao;
    private PositionDao positionDao;
    private BranchDao branchDao;
    private DepartmentDao departmentDao;
    private PositionBagianDao positionBagianDao;
    private BiodataDao biodataDao;
    private DokterDao dokterDao;

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

    private PengajuanSetorDetail convertPph21Pengajuan( PengajuanSetorDetail data){
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
        pengajuanSetorEntity.setTipePengajuanSetor("PPH21");
        pengajuanSetorEntity.setKeterangan(bean.getKeterangan());

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
        pengajuanSetorEntity.setTipePengajuanSetor("PPN");
        pengajuanSetorEntity.setKeterangan(bean.getKeterangan());
        pengajuanSetorEntity.setJumlahPpnMasukanB2(bean.getJumlahPpnMasukanB2());
        pengajuanSetorEntity.setJumlahPpnKeluaran(bean.getJumlahPpnKeluaran());
        pengajuanSetorEntity.setJumlahPpnMasukanB3(bean.getJumlahPpnMasukanB3());

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
        List<ImBranches> branches = branchDao.getListBranchById(data.getBranchId());
        if (branches.size()!=0){
            for (ImBranches imBranches : branches){
                result.setBranchName(imBranches.getBranchName());
            }
        }
        if ("Payroll".equalsIgnoreCase(tipe)){
            ImBiodataEntity biodataEntity = biodataDao.getById("nip",data.getPersonId());

            result.setNama(biodataEntity.getNamaPegawai());

            ImPosition position = positionDao.getById("positionId",data.getDivisiId());
            result.setPosisiName(position.getPositionName());

            if (position.getBagianId()!=null){
                ImPositionBagianEntity positionBagian= positionBagianDao.getById("bagianId",position.getBagianId());
                result.setBagianName(positionBagian.getBagianName());
            }else{
                result.setBagianName("");
            }
            if (position.getDepartmentId()!=null){
                ImDepartmentEntity departmentEntity= departmentDao.getById("departmentId",position.getDepartmentId());
                result.setDivisiName(departmentEntity.getDepartmentName());
            }else{
                result.setDivisiName("");
            }

            if (data.getJumlah()!=null){
                result.setStJumlah(CommonUtil.numbericFormat(data.getJumlah(),"###,###"));
            }else{
                result.setStJumlah("0");
            }
        } else if ("Dokter KSO".equalsIgnoreCase(tipe)){
            ImSimrsDokterEntity dokterEntity= dokterDao.getById("idDokter",data.getPersonId());

            result.setNama(dokterEntity.getNamaDokter());

            if (data.getJumlah()!=null){
                result.setStJumlah(CommonUtil.numbericFormat(data.getJumlah(),"###,###"));
            }else{
                result.setStJumlah("0");
            }
        } else if ("Pengajuan Biaya PPH21".equalsIgnoreCase(tipe)){
            ImPosition position = positionDao.getById("positionId",data.getDivisiId());
            result.setPosisiName(position.getPositionName());

            if (position.getBagianId()!=null){
                ImPositionBagianEntity positionBagian= positionBagianDao.getById("bagianId",position.getBagianId());
                result.setBagianName(positionBagian.getBagianName());
            }else{
                result.setBagianName("");
            }
            if (position.getDepartmentId()!=null){
                ImDepartmentEntity departmentEntity= departmentDao.getById("departmentId",position.getDepartmentId());
                result.setDivisiName(departmentEntity.getDepartmentName());
            }else{
                result.setDivisiName("");
            }

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
                itPengajuanSetorEntity.setApprovalId(bean.getApprovalId());
                itPengajuanSetorEntity.setApprovalDate(bean.getApprovalDate());
                itPengajuanSetorEntity.setApprovalFlag(bean.getApprovalFlag());
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
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
