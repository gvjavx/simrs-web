package com.neurix.akuntansi.transaksi.penyewaanLahan.bo.impl;

import com.neurix.akuntansi.master.mappingJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;
import com.neurix.akuntansi.master.masterVendor.dao.MasterVendorDao;
import com.neurix.akuntansi.master.masterVendor.model.ImMasterVendorEntity;
import com.neurix.akuntansi.master.pembayaran.dao.PembayaranDao;
import com.neurix.akuntansi.master.pembayaran.model.ImAkunPembayaranEntity;
import com.neurix.akuntansi.transaksi.penyewaanLahan.bo.PenyewaanLahanBo;
import com.neurix.akuntansi.transaksi.penyewaanLahan.dao.PenyewaanLahanDao;
import com.neurix.akuntansi.transaksi.penyewaanLahan.model.ItAkunPenyewaanLahanEntity;
import com.neurix.akuntansi.transaksi.penyewaanLahan.model.PenyewaanLahan;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
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
public class PenyewaanLahanBoImpl implements PenyewaanLahanBo {

    protected static transient Logger logger = Logger.getLogger(PenyewaanLahanBoImpl.class);
    private PenyewaanLahanDao penyewaanLahanDao;
    private BranchDao branchDao;
    private MasterVendorDao masterVendorDao;
    private PembayaranDao pembayaranDao;

    public MasterVendorDao getMasterVendorDao() {
        return masterVendorDao;
    }

    public void setMasterVendorDao(MasterVendorDao masterVendorDao) {
        this.masterVendorDao = masterVendorDao;
    }

    public PembayaranDao getPembayaranDao() {
        return pembayaranDao;
    }

    public void setPembayaranDao(PembayaranDao pembayaranDao) {
        this.pembayaranDao = pembayaranDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PenyewaanLahanBoImpl.logger = logger;
    }

    public PenyewaanLahanDao getPenyewaanLahanDao() {
        return penyewaanLahanDao;
    }

    public void setPenyewaanLahanDao(PenyewaanLahanDao penyewaanLahanDao) {
        this.penyewaanLahanDao = penyewaanLahanDao;
    }

    @Override
    public void saveDelete(PenyewaanLahan bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean!=null) {
        }
        logger.info("[PenyewaanLahanBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(PenyewaanLahan bean) throws GeneralBOException {
        logger.info("[PenyewaanLahanBoImpl.saveEdit] start process >>>");

        if (bean!=null) {

        }
        logger.info("[PenyewaanLahanBoImpl.saveEdit] end process <<<");
    }

    @Override
    public PenyewaanLahan saveAdd(PenyewaanLahan bean) throws GeneralBOException {
        logger.info("[PenyewaanLahanBoImpl.saveAdd] start process >>>");
        String penyewaanLahanId = penyewaanLahanDao.getNextId();
        ItAkunPenyewaanLahanEntity penyewaanLahanEntity = new ItAkunPenyewaanLahanEntity();
        if (bean!=null) {
            penyewaanLahanEntity.setPenyewaanLahanId(penyewaanLahanId);
            penyewaanLahanEntity.setBranchId(bean.getBranchId());
            penyewaanLahanEntity.setNamaPenyewa(bean.getNamaPenyewa());
            penyewaanLahanEntity.setKeterangan(bean.getKeterangan());
            penyewaanLahanEntity.setTanggalBayar(bean.getTanggalBayar());
            penyewaanLahanEntity.setNilai(bean.getNilai());
            penyewaanLahanEntity.setNilaiNetto(bean.getNilaiNetto());
            penyewaanLahanEntity.setNilaiPpn(bean.getNilaiPpn());
            penyewaanLahanEntity.setNilaiPph(bean.getNilaiPph());
            penyewaanLahanEntity.setNoFaktur(bean.getNoFaktur());
            penyewaanLahanEntity.setUrlFakturImage(bean.getUrlFakturImage());
            penyewaanLahanEntity.setCancelFlag(bean.getCancelFlag());
            penyewaanLahanEntity.setMetodeBayar(bean.getMetodeBayar());
            penyewaanLahanEntity.setBank(bean.getBank());
            penyewaanLahanEntity.setAction(bean.getAction());
            penyewaanLahanEntity.setFlag(bean.getFlag());
            penyewaanLahanEntity.setCreatedDate(bean.getCreatedDate());
            penyewaanLahanEntity.setCreatedWho(bean.getCreatedWho());
            penyewaanLahanEntity.setLastUpdate(bean.getLastUpdate());
            penyewaanLahanEntity.setLastUpdateWho(bean.getLastUpdateWho());

            penyewaanLahanDao.addAndSave(penyewaanLahanEntity);
        }
        logger.info("[PenyewaanLahanBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<PenyewaanLahan> getByCriteria(PenyewaanLahan searchBean) throws GeneralBOException {
        logger.info("[PenyewaanLahanBoImpl.getByCriteria] start process >>>");
        List<PenyewaanLahan> listOfResult = new ArrayList();
        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPenyewaanLahanId() != null && !"".equalsIgnoreCase(searchBean.getPenyewaanLahanId())) {
                hsCriteria.put("penyewaan_lahan_id", searchBean.getPenyewaanLahanId());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getKeterangan() != null && !"".equalsIgnoreCase(searchBean.getKeterangan())) {
                hsCriteria.put("keterangan", searchBean.getKeterangan());
            }
            if (searchBean.getNamaPenyewa() != null && !"".equalsIgnoreCase(searchBean.getNamaPenyewa())) {
                hsCriteria.put("nama_penyewa", searchBean.getNamaPenyewa());
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
            List<ItAkunPenyewaanLahanEntity> itAkunPenyewaanLahanEntityList = null;
            try {
                itAkunPenyewaanLahanEntityList = penyewaanLahanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PengajuanSetorBoImpl.getSearchPengajuanBiayaByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itAkunPenyewaanLahanEntityList != null){
                // Looping from dao to object and save in collection
                for(ItAkunPenyewaanLahanEntity penyewaanLahanEntity : itAkunPenyewaanLahanEntityList){

                    listOfResult.add(convertEntity(penyewaanLahanEntity));
                }
            }
        }
        logger.info("[PenyewaanLahanBoImpl.getByCriteria] end process <<<");
        return listOfResult;
    }

    @Override
    public List<PenyewaanLahan> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public ItAkunPenyewaanLahanEntity getPenyewaanLahanById(String penyewaanLahanId){
        return penyewaanLahanDao.getById("penyewaanLahanId",penyewaanLahanId);
    }

    @Override
    public void batalkanPenyewaanLahan(PenyewaanLahan bean) throws GeneralBOException {
        logger.info("[PenyewaanLahanBoImpl.batalkanPenyewaanLahan] start process >>>");
        if (bean!=null) {
            ItAkunPenyewaanLahanEntity itAkunPenyewaanLahanEntity = null;
            try {
                // Get data from database by ID
                itAkunPenyewaanLahanEntity = penyewaanLahanDao.getById("penyewaanLahanId", bean.getPenyewaanLahanId());
            } catch (HibernateException e) {
                logger.error("[PenyewaanLahanBoImpl.batalkanPenyewaanLahan] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
            }
            if (itAkunPenyewaanLahanEntity != null) {
                itAkunPenyewaanLahanEntity.setCancelFlag(bean.getCancelFlag());
                itAkunPenyewaanLahanEntity.setCancelDate(bean.getCancelDate());
                itAkunPenyewaanLahanEntity.setCancelWho(bean.getCancelWho());

                itAkunPenyewaanLahanEntity.setFlag(bean.getFlag());
                itAkunPenyewaanLahanEntity.setAction(bean.getAction());
                itAkunPenyewaanLahanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itAkunPenyewaanLahanEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    penyewaanLahanDao.updateAndSave(itAkunPenyewaanLahanEntity);
                } catch (HibernateException e) {
                    logger.error("[PenyewaanLahanBoImpl.batalkanPenyewaanLahan] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PenyewaanLahanBoImpl.batalkanPenyewaanLahan] Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");
            }
        }
        logger.info("[PenyewaanLahanBoImpl.batalkanPenyewaanLahan] end process <<<");
    }

    @Override
    public void postingJurnal(PenyewaanLahan bean) throws GeneralBOException {
        logger.info("[PenyewaanLahanBoImpl.postingJurnal] start process >>>");
        if (bean!=null) {
            ItAkunPenyewaanLahanEntity itAkunPenyewaanLahanEntity = null;
            try {
                // Get data from database by ID
                itAkunPenyewaanLahanEntity = penyewaanLahanDao.getById("penyewaanLahanId", bean.getPenyewaanLahanId());
            } catch (HibernateException e) {
                logger.error("[PenyewaanLahanBoImpl.postingJurnal] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PembayaranUtangPiutang by Kode PembayaranUtangPiutang, please inform to your admin...," + e.getMessage());
            }

            if (itAkunPenyewaanLahanEntity != null) {
                itAkunPenyewaanLahanEntity.setApprovalWho(bean.getApprovalWho());
                itAkunPenyewaanLahanEntity.setApprovalDate(bean.getApprovalDate());
                itAkunPenyewaanLahanEntity.setApprovalFlag(bean.getApprovalFlag());
                itAkunPenyewaanLahanEntity.setNoJurnal(bean.getNoJurnal());

                itAkunPenyewaanLahanEntity.setFlag(bean.getFlag());
                itAkunPenyewaanLahanEntity.setAction(bean.getAction());
                itAkunPenyewaanLahanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itAkunPenyewaanLahanEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    penyewaanLahanDao.updateAndSave(itAkunPenyewaanLahanEntity);
                } catch (HibernateException e) {
                    logger.error("[PenyewaanLahanBoImpl.postingJurnal] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[PenyewaanLahanBoImpl.postingJurnal] Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");
            }
        }
        logger.info("[PenyewaanLahanBoImpl.postingJurnal] end process <<<");
    }

    private PenyewaanLahan convertEntity (ItAkunPenyewaanLahanEntity data){
        PenyewaanLahan result =  new PenyewaanLahan();
        result.setPenyewaanLahanId(data.getPenyewaanLahanId());
        result.setNamaPenyewa(data.getNamaPenyewa());
        result.setKeterangan(data.getKeterangan());
        result.setTanggalBayar(data.getTanggalBayar());
        result.setStTanggalBayar(CommonUtil.convertDateToString(data.getTanggalBayar()));
        result.setNilai(data.getNilai());
        result.setNilaiNetto(data.getNilaiNetto());
        result.setNilaiPpn(data.getNilaiPpn());
        result.setNilaiPph(data.getNilaiPph());
        result.setStNilai(CommonUtil.numbericFormat(data.getNilai(),"###,###"));
        result.setStNilaiNetto(CommonUtil.numbericFormat(data.getNilaiNetto(),"###,###"));
        result.setStNilaiPpn(CommonUtil.numbericFormat(data.getNilaiPpn(),"###,###"));
        result.setStNilaiPph(CommonUtil.numbericFormat(data.getNilaiPph(),"###,###"));
        result.setBranchId(data.getBranchId());
        result.setNoFaktur(data.getNoFaktur());
        result.setUrlFakturImage(CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_FAKTUR_PAJAK+data.getUrlFakturImage());
        List<ImBranches> branchesList = branchDao.getListBranchById(data.getBranchId());
        for (ImBranches branches : branchesList){
            result.setBranchName(branches.getBranchName());
        }

        result.setCancelFlag(data.getCancelFlag());
        result.setCancelWho(data.getCancelWho());
        result.setCancelDate(data.getCancelDate());
        result.setApprovalFlag(data.getApprovalFlag());
        result.setApprovalWho(data.getApprovalWho());
        result.setApprovalDate(data.getApprovalDate());
        result.setMetodeBayar(data.getMetodeBayar());
        result.setBank(data.getBank());
        result.setFlag(data.getFlag());
        result.setAction(data.getAction());
        result.setCreatedDate(data.getCreatedDate());
        result.setCreatedWho(data.getCreatedWho());
        result.setLastUpdate(data.getLastUpdate());
        result.setLastUpdateWho(data.getLastUpdateWho());
        result.setNoJurnal(data.getNoJurnal());
        List<ImAkunPembayaranEntity> pembayaranEntityList  = pembayaranDao.getDataAkunPembayaranByCoa(data.getBank());
        for (ImAkunPembayaranEntity pembayaranEntity : pembayaranEntityList){
            result.setBankName(pembayaranEntity.getPembayaranName());
        }

        ImMasterVendorEntity masterVendorEntity = masterVendorDao.getById("nomorMaster",data.getNamaPenyewa());
        result.setNamaPenyewaName(masterVendorEntity.getNama());

        return result;
    }
}
