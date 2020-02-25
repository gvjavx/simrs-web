package com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDao;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDetailDao;
import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalDetailEntity;
import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalEntity;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.bo.PembayaranUtangPiutangBo;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.dao.PembayaranUtangPiutangDetailDao;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.dao.PembayaranUtangPiutangDao;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.ImPembayaranUtangPiutangDetailEntity;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.ImPembayaranUtangPiutangEntity;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.PembayaranUtangPiutang;
import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.PembayaranUtangPiutangDetail;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
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
                    returnPembayaranUtangPiutang.setKodeRekeningKas(pembayaranUtangPiutangEntity.getKodeRekeningKas());
                    returnPembayaranUtangPiutang.setBayar(pembayaranUtangPiutangEntity.getBayar());
                    returnPembayaranUtangPiutang.setKeterangan(pembayaranUtangPiutangEntity.getKeterangan());
                    returnPembayaranUtangPiutang.setNoSlipBank(pembayaranUtangPiutangEntity.getNoSlipBank());
                    returnPembayaranUtangPiutang.setBranchId(pembayaranUtangPiutangEntity.getBranchId());
                    returnPembayaranUtangPiutang.setNoJurnal(pembayaranUtangPiutangEntity.getNoJurnal());

                    if (pembayaranUtangPiutangEntity.getRegisteredFlag()!=null){
                        if (("Y").equalsIgnoreCase(pembayaranUtangPiutangEntity.getRegisteredFlag())){
                            returnPembayaranUtangPiutang.setFlagPosting(true);
                        }
                    }
                    returnPembayaranUtangPiutang.setRegisteredFlag(pembayaranUtangPiutangEntity.getRegisteredFlag());
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
    public List<PembayaranUtangPiutangDetail> getSearchNotaPembayaran(String masterId,String transaksiId) throws GeneralBOException {
        logger.info("[PembayaranUtangPiutangBoImpl.getSearchNotaPembayaran] start process >>>");
        List<PembayaranUtangPiutangDetail> listOfResult = new ArrayList<>();

        List<PembayaranUtangPiutangDetail> pembayaranUtangPiutangDetailList ;
        try {

            pembayaranUtangPiutangDetailList = pembayaranUtangPiutangDao.getSearchNotaPembayaran(masterId,transaksiId);
        } catch (HibernateException e) {
            logger.error("[PembayaranUtangPiutangBoImpl.getSearchPembayaranUtangPiutangByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(pembayaranUtangPiutangDetailList != null){
            PembayaranUtangPiutangDetail returnPembayaranUtangPiutangDetail;
            // Looping from dao to object and save in collection
            for(PembayaranUtangPiutangDetail pembayaranUtangPiutangDetail : pembayaranUtangPiutangDetailList){
                returnPembayaranUtangPiutangDetail = new PembayaranUtangPiutangDetail();
                returnPembayaranUtangPiutangDetail.setMasterId(pembayaranUtangPiutangDetail.getMasterId());
                returnPembayaranUtangPiutangDetail.setRekeningId(pembayaranUtangPiutangDetail.getRekeningId());
                returnPembayaranUtangPiutangDetail.setNoNota(pembayaranUtangPiutangDetail.getNoNota());
                returnPembayaranUtangPiutangDetail.setStJumlahPembayaran(pembayaranUtangPiutangDetail.getStJumlahPembayaran().replace(",","."));
                returnPembayaranUtangPiutangDetail.setStJumlahPembayaran(returnPembayaranUtangPiutangDetail.getStJumlahPembayaran().replace(" ",""));
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
                jurnalEntity.setRegisteredDate(bean.getRegisteredDate());
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

            } else {
                logger.error("[PembayaranUtangPiutangBoImpl.postingJurnal] Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PembayaranUtangPiutang with request id, please check again your data ...");
            }
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
    public String saveAddPembayaran(PembayaranUtangPiutang bean, List<PembayaranUtangPiutangDetail> pembayaranUtangPiutangDetailList) throws GeneralBOException {
        logger.info("[PembayaranUtangPiutangBoImpl.saveAdd] start process >>>");
        String jurnalId = null;

        if (bean!=null) {
            String pembayaranUtangPiutangId;

            try {
                // Generating ID, get from postgre sequence
                jurnalId=jurnalDao.getNextJurnalId();
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
            imPembayaranUtangPiutangEntity.setKodeRekeningKas(bean.getKodeRekeningKas());
            imPembayaranUtangPiutangEntity.setBayar(bean.getBayar());
            imPembayaranUtangPiutangEntity.setKeterangan(bean.getKeterangan());
            imPembayaranUtangPiutangEntity.setNoSlipBank(bean.getNoSlipBank());
            imPembayaranUtangPiutangEntity.setBranchId(bean.getBranchId());
            imPembayaranUtangPiutangEntity.setNoJurnal(jurnalId);

            imPembayaranUtangPiutangEntity.setFlag(bean.getFlag());
            imPembayaranUtangPiutangEntity.setAction(bean.getAction());
            imPembayaranUtangPiutangEntity.setCreatedWho(bean.getCreatedWho());
            imPembayaranUtangPiutangEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imPembayaranUtangPiutangEntity.setCreatedDate(bean.getCreatedDate());
            imPembayaranUtangPiutangEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                pembayaranUtangPiutangDao.addAndSave(imPembayaranUtangPiutangEntity);
            } catch (HibernateException e) {
                logger.error("[PembayaranUtangPiutangBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
            }


            for (PembayaranUtangPiutangDetail data : pembayaranUtangPiutangDetailList){
                BigDecimal jumlahPembayaran = new BigDecimal(data.getStJumlahPembayaran().replace(".",""));
                ImPembayaranUtangPiutangDetailEntity pembayaranUtangPiutangDetailEntity = new ImPembayaranUtangPiutangDetailEntity();
                String pembayaranUtangPiutangDetailId = pembayaranUtangPiutangDetailDao.getNextPembayaranUtangPiutangDetailId();
                pembayaranUtangPiutangDetailEntity.setPembayaranUtangPiutangDetailId(pembayaranUtangPiutangDetailId);
                pembayaranUtangPiutangDetailEntity.setPembayaranUtangPiutangId(pembayaranUtangPiutangId);
                pembayaranUtangPiutangDetailEntity.setMasterId(data.getMasterId());
                pembayaranUtangPiutangDetailEntity.setNoNota(data.getNoNota());
                pembayaranUtangPiutangDetailEntity.setRekeningId(data.getRekeningId());
                pembayaranUtangPiutangDetailEntity.setJumlahPembayaran(jumlahPembayaran);

                pembayaranUtangPiutangDetailEntity.setFlag(bean.getFlag());
                pembayaranUtangPiutangDetailEntity.setAction(bean.getAction());
                pembayaranUtangPiutangDetailEntity.setCreatedWho(bean.getCreatedWho());
                pembayaranUtangPiutangDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                pembayaranUtangPiutangDetailEntity.setCreatedDate(bean.getCreatedDate());
                pembayaranUtangPiutangDetailEntity.setLastUpdate(bean.getLastUpdate());


                // Cek apakah nota sudah pernah dibayarkan sebelumnya
                Map hsCriteria = new HashMap();
                hsCriteria.put("rekening_id",data.getRekeningId());
                hsCriteria.put("master_id",data.getMasterId());
                hsCriteria.put("no_nota",data.getNoNota());
                hsCriteria.put("flag","Y");

                try {
                    List<ItJurnalDetailEntity> jurnalDetailEntityList = jurnalDetailDao.getByCriteria(hsCriteria);
                    if (jurnalDetailEntityList.size()!=0){
                        for (ItJurnalDetailEntity jurnalDetailEntity : jurnalDetailEntityList){
                            jurnalId=null;
                            logger.error("[PembayaranUtangPiutangBoImpl.saveAdd] Error, duplicate pembayaran");
                            throw new GeneralBOException("sudah pernah dibayarkan dengan nomor jurnal "+jurnalDetailEntity.getNoJurnal()+", please info to your admin...");
                        }
                    }
                } catch (HibernateException e) {
                    logger.error("[PembayaranUtangPiutangBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
                }

                try {
                    // insert into database
                    pembayaranUtangPiutangDetailDao.addAndSave(pembayaranUtangPiutangDetailEntity);
                } catch (HibernateException e) {
                    logger.error("[PembayaranUtangPiutangBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PembayaranUtangPiutang, please info to your admin..." + e.getMessage());
                }
            }
        }
        logger.info("[PembayaranUtangPiutangBoImpl.saveAdd] end process <<<");
        return jurnalId;
    }
}
