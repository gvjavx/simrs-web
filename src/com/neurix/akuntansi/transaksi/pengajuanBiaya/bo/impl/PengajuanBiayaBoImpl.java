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
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.ImPengajuanBiayaEntity;
import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.PengajuanBiaya;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
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
public class PengajuanBiayaBoImpl implements PengajuanBiayaBo {

    protected static transient Logger logger = Logger.getLogger(PengajuanBiayaBoImpl.class);
    private PengajuanBiayaDao pengajuanBiayaDao;
    private KodeRekeningDao kodeRekeningDao;
    private JurnalDao jurnalDao;
    private JurnalDetailDao jurnalDetailDao;
    private TransDao transDao;
    private BranchDao branchDao;
    private MappingJurnalDao mappingJurnalDao;

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
    public List<PengajuanBiaya> getByCriteria(PengajuanBiaya searchBean) throws GeneralBOException {
        logger.info("[PengajuanBiayaBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<PengajuanBiaya> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getPengajuanBiayaId() != null && !"".equalsIgnoreCase(searchBean.getPengajuanBiayaId())) {
                hsCriteria.put("pengajuan_biaya_id", searchBean.getPengajuanBiayaId());
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
}
