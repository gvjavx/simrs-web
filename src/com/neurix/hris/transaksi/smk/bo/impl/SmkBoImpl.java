package com.neurix.hris.transaksi.smk.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.dao.BiodataHistoryDao;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.biodata.model.ImBiodataHistoryEntity;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.positionBagian.dao.PositionBagianDao;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianEntity;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.dao.SmkIndikatorPenilaianAspekDao;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.ImSmkIndikatorPenilaianAspekEntity;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.SmkIndikatorPenilaianAspek;
import com.neurix.hris.master.smkIndikatorPenilaianCheckList.dao.SmkIndikatorPenilaianCheckListDao;
import com.neurix.hris.master.smkIndikatorPenilaianCheckList.model.ImSmkIndikatorPenilaianCheckListEntity;
import com.neurix.hris.master.smkIndikatorPenilaianCheckList.model.SmkIndikatorPenilaianCheckList;
import com.neurix.hris.master.smkJabatan.dao.SmkJabatanDao;
import com.neurix.hris.master.smkJabatan.dao.SmkJabatanDetailDao;
import com.neurix.hris.master.smkJabatan.model.ImtSmkJabatanDetailEntity;
import com.neurix.hris.master.smkJabatan.model.ImtSmkJabatanEntity;
import com.neurix.hris.master.smkJabatan.model.SmkJabatanDetail;
import com.neurix.hris.master.smkSkalaNilai.dao.SmkSkalaNilaiDao;
import com.neurix.hris.master.smkSkalaNilai.model.ImSmkSkalaNilaiEntity;
import com.neurix.hris.master.smkSkalaNilai.model.SmkSkalaNilai;
import com.neurix.hris.master.strukturJabatan.dao.StrukturJabatanDao;
import com.neurix.hris.master.strukturJabatan.model.ImStrukturJabatanEntity;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.transaksi.personilPosition.dao.HistoryJabatanPegawaiDao;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.HistoryJabatanPegawai;
import com.neurix.hris.transaksi.personilPosition.model.ImtHrisHistoryJabatanPegawaiEntity;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.hris.transaksi.smk.bo.SmkBo;
import com.neurix.hris.transaksi.smk.dao.*;
import com.neurix.hris.transaksi.smk.model.*;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class SmkBoImpl implements SmkBo {

    protected static transient Logger logger = Logger.getLogger(SmkBoImpl.class);
    private SmkDao smkDao;
    private SmkIndikatorPenilaianAspekDao smkIndikatorPenilaianAspekDao;
    private SmkEvaluasiPegawaiAspekDao smkEvaluasiPegawaiAspekDao;
    private SmkEvaluasiPegawaiAspekDetailDao smkEvaluasiPegawaiAspekDetailDao;
    private SmkEvaluasiPegawaiAspekActivityDao smkEvaluasiPegawaiAspekActivityDao;
    private SmkJabatanDao smkJabatanDao;
    private SmkJabatanDetailDao smkJabatanDetaiDao;
    private SmkIndikatorPenilaianCheckListDao smkIndikatorPenilaianCheckListDao;
    private SmkAspekActivityMonthlyDao smkAspekActivityMonthlyDao;
    private SmkEvaluasiPegawaiAspekActivityPeristiwaDao smkEvaluasiPegawaiAspekActivityPeristiwaDao;
    private SmkHistoryEvaluasiPegawaiDao smkHistory;
    private SmkSkalaNilaiDao smkSkalaNilaiDao;
    private BiodataDao biodataDao;
    private BiodataHistoryDao biodataHistoryDao;
    private StrukturJabatanDao strukturJabatanDao;
    private PersonilPositionDao personilPositionDao;
    private PositionDao positionDao;
    private DepartmentDao departmentDao;
    private PositionBagianDao positionBagianDao;
    private SmkHistoryGolonganDao smkHistoryGolonganDao;
    private HistoryJabatanPegawaiDao historyJabatanPegawaiDao;
    private BranchDao branchDao;

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public HistoryJabatanPegawaiDao getHistoryJabatanPegawaiDao() {
        return historyJabatanPegawaiDao;
    }

    public void setHistoryJabatanPegawaiDao(HistoryJabatanPegawaiDao historyJabatanPegawaiDao) {
        this.historyJabatanPegawaiDao = historyJabatanPegawaiDao;
    }

    private SmkHistoryDao smkHistoryDao;
    private SmkHistoryEvaluasiPegawaiAspekDao smkHistoryEvaluasiPegawaiAspekDao;
    private SmkHistoryEvaluasiPegawaiAspekDetailDao smkHistoryEvaluasiPegawaiAspekDetailDao;
    private SmkHistoryEvaluasiPegawaiAspekActivityDao smkHistoryEvaluasiPegawaiAspekActivityDao;
    private SmkHistoryEvaluasiPegawaiAspekActivityPeristiwaDao smkHistoryEvaluasiPegawaiAspekActivityPeristiwaDao;
    private SmkHistoryAspekActivityMonthlyDao smkHistoryAspekActivityMonthlyDao;

    List<ItSmkEntity> smkTmpAll = new ArrayList<>();
    List<ItSmkEvaluasiPegawaiAspekEntity> smkTmpAspekAll = new ArrayList<>();
    List<ItSmkEvaluasiPegawaiAspekDetailEntity> smkTmpAspekDetailAll = new ArrayList<>();
    List<ItSmkEvaluasiPegawaiAspekActivityEntity> smkTmpAspekActivityAll = new ArrayList<>();
    List<ItSmkAspekActivityMonthlyEntity> smkTmpAspekActivityMonthlyAll = new ArrayList<>();
    List<ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity> smkTmpAspekActivityPeristiwaAll = new ArrayList<>();

    List<ItSmkEntity> smkSessionAll = new ArrayList<>();
    List<ItSmkEvaluasiPegawaiAspekEntity> smkSessionAspekAll = new ArrayList<>();
    List<ItSmkEvaluasiPegawaiAspekDetailEntity> smkSessionAspekDetailAll = new ArrayList<>();
    List<ItSmkEvaluasiPegawaiAspekActivityEntity> smkSessionAspekActivityAll = new ArrayList<>();
    List<ItSmkAspekActivityMonthlyEntity> smkSessionAspekActivityMonthlyAll = new ArrayList<>();
    List<ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity> smkSessionAspekActivityPeristiwaAll = new ArrayList<>();

    public SmkHistoryGolonganDao getSmkHistoryGolonganDao() {
        return smkHistoryGolonganDao;
    }

    public void setSmkHistoryGolonganDao(SmkHistoryGolonganDao smkHistoryGolonganDao) {
        this.smkHistoryGolonganDao = smkHistoryGolonganDao;
    }

    public PositionBagianDao getPositionBagianDao() {
        return positionBagianDao;
    }

    public void setPositionBagianDao(PositionBagianDao positionBagianDao) {
        this.positionBagianDao = positionBagianDao;
    }

    public SmkHistoryAspekActivityMonthlyDao getSmkHistoryAspekActivityMonthlyDao() {
        return smkHistoryAspekActivityMonthlyDao;
    }

    public void setSmkHistoryAspekActivityMonthlyDao(SmkHistoryAspekActivityMonthlyDao smkHistoryAspekActivityMonthlyDao) {
        this.smkHistoryAspekActivityMonthlyDao = smkHistoryAspekActivityMonthlyDao;
    }

    public SmkHistoryDao getSmkHistoryDao() {
        return smkHistoryDao;
    }

    public void setSmkHistoryDao(SmkHistoryDao smkHistoryDao) {
        this.smkHistoryDao = smkHistoryDao;
    }

    public SmkHistoryEvaluasiPegawaiAspekActivityDao getSmkHistoryEvaluasiPegawaiAspekActivityDao() {
        return smkHistoryEvaluasiPegawaiAspekActivityDao;
    }

    public void setSmkHistoryEvaluasiPegawaiAspekActivityDao(SmkHistoryEvaluasiPegawaiAspekActivityDao smkHistoryEvaluasiPegawaiAspekActivityDao) {
        this.smkHistoryEvaluasiPegawaiAspekActivityDao = smkHistoryEvaluasiPegawaiAspekActivityDao;
    }

    public SmkHistoryEvaluasiPegawaiAspekActivityPeristiwaDao getSmkHistoryEvaluasiPegawaiAspekActivityPeristiwaDao() {
        return smkHistoryEvaluasiPegawaiAspekActivityPeristiwaDao;
    }

    public void setSmkHistoryEvaluasiPegawaiAspekActivityPeristiwaDao(SmkHistoryEvaluasiPegawaiAspekActivityPeristiwaDao smkHistoryEvaluasiPegawaiAspekActivityPeristiwaDao) {
        this.smkHistoryEvaluasiPegawaiAspekActivityPeristiwaDao = smkHistoryEvaluasiPegawaiAspekActivityPeristiwaDao;
    }

    public SmkHistoryEvaluasiPegawaiAspekDao getSmkHistoryEvaluasiPegawaiAspekDao() {
        return smkHistoryEvaluasiPegawaiAspekDao;
    }

    public void setSmkHistoryEvaluasiPegawaiAspekDao(SmkHistoryEvaluasiPegawaiAspekDao smkHistoryEvaluasiPegawaiAspekDao) {
        this.smkHistoryEvaluasiPegawaiAspekDao = smkHistoryEvaluasiPegawaiAspekDao;
    }

    public SmkHistoryEvaluasiPegawaiAspekDetailDao getSmkHistoryEvaluasiPegawaiAspekDetailDao() {
        return smkHistoryEvaluasiPegawaiAspekDetailDao;
    }

    public void setSmkHistoryEvaluasiPegawaiAspekDetailDao(SmkHistoryEvaluasiPegawaiAspekDetailDao smkHistoryEvaluasiPegawaiAspekDetailDao) {
        this.smkHistoryEvaluasiPegawaiAspekDetailDao = smkHistoryEvaluasiPegawaiAspekDetailDao;
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public BiodataHistoryDao getBiodataHistoryDao() {
        return biodataHistoryDao;
    }

    public void setBiodataHistoryDao(BiodataHistoryDao biodataHistoryDao) {
        this.biodataHistoryDao = biodataHistoryDao;
    }

    public StrukturJabatanDao getStrukturJabatanDao() {
        return strukturJabatanDao;
    }

    public void setStrukturJabatanDao(StrukturJabatanDao strukturJabatanDao) {
        this.strukturJabatanDao = strukturJabatanDao;
    }

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    public SmkSkalaNilaiDao getSmkSkalaNilaiDao() {
        return smkSkalaNilaiDao;
    }

    public void setSmkSkalaNilaiDao(SmkSkalaNilaiDao smkSkalaNilaiDao) {
        this.smkSkalaNilaiDao = smkSkalaNilaiDao;
    }

    public SmkHistoryEvaluasiPegawaiDao getSmkHistory() {
        return smkHistory;
    }

    public void setSmkHistory(SmkHistoryEvaluasiPegawaiDao smkHistory) {
        this.smkHistory = smkHistory;
    }

    public SmkEvaluasiPegawaiAspekActivityPeristiwaDao getSmkEvaluasiPegawaiAspekActivityPeristiwaDao() {
        return smkEvaluasiPegawaiAspekActivityPeristiwaDao;
    }

    public void setSmkEvaluasiPegawaiAspekActivityPeristiwaDao(SmkEvaluasiPegawaiAspekActivityPeristiwaDao smkEvaluasiPegawaiAspekActivityPeristiwaDao) {
        this.smkEvaluasiPegawaiAspekActivityPeristiwaDao = smkEvaluasiPegawaiAspekActivityPeristiwaDao;
    }

    public SmkAspekActivityMonthlyDao getSmkAspekActivityMonthlyDao() {
        return smkAspekActivityMonthlyDao;
    }

    public void setSmkAspekActivityMonthlyDao(SmkAspekActivityMonthlyDao smkAspekActivityMonthlyDao) {
        this.smkAspekActivityMonthlyDao = smkAspekActivityMonthlyDao;
    }

    public SmkEvaluasiPegawaiAspekActivityDao getSmkEvaluasiPegawaiAspekActivityDao() {
        return smkEvaluasiPegawaiAspekActivityDao;
    }

    public void setSmkEvaluasiPegawaiAspekActivityDao(SmkEvaluasiPegawaiAspekActivityDao smkEvaluasiPegawaiAspekActivityDao) {
        this.smkEvaluasiPegawaiAspekActivityDao = smkEvaluasiPegawaiAspekActivityDao;
    }

    public SmkIndikatorPenilaianCheckListDao getSmkIndikatorPenilaianCheckListDao() {
        return smkIndikatorPenilaianCheckListDao;
    }

    public void setSmkIndikatorPenilaianCheckListDao(SmkIndikatorPenilaianCheckListDao smkIndikatorPenilaianCheckListDao) {
        this.smkIndikatorPenilaianCheckListDao = smkIndikatorPenilaianCheckListDao;
    }

    public SmkEvaluasiPegawaiAspekDetailDao getSmkEvaluasiPegawaiAspekDetailDao() {
        return smkEvaluasiPegawaiAspekDetailDao;
    }

    public void setSmkEvaluasiPegawaiAspekDetailDao(SmkEvaluasiPegawaiAspekDetailDao smkEvaluasiPegawaiAspekDetailDao) {
        this.smkEvaluasiPegawaiAspekDetailDao = smkEvaluasiPegawaiAspekDetailDao;
    }

    public SmkJabatanDetailDao getSmkJabatanDetaiDao() {
        return smkJabatanDetaiDao;
    }

    public void setSmkJabatanDetaiDao(SmkJabatanDetailDao smkJabatanDetaiDao) {
        this.smkJabatanDetaiDao = smkJabatanDetaiDao;
    }

    public SmkJabatanDao getSmkJabatanDao() {
        return smkJabatanDao;
    }

    public void setSmkJabatanDao(SmkJabatanDao smkJabatanDao) {
        this.smkJabatanDao = smkJabatanDao;
    }

    public SmkEvaluasiPegawaiAspekDao getSmkEvaluasiPegawaiAspekDao() {
        return smkEvaluasiPegawaiAspekDao;
    }

    public void setSmkEvaluasiPegawaiAspekDao(SmkEvaluasiPegawaiAspekDao smkEvaluasiPegawaiAspekDao) {
        this.smkEvaluasiPegawaiAspekDao = smkEvaluasiPegawaiAspekDao;
    }

    public SmkIndikatorPenilaianAspekDao getSmkIndikatorPenilaianAspekDao() {
        return smkIndikatorPenilaianAspekDao;
    }

    public void setSmkIndikatorPenilaianAspekDao(SmkIndikatorPenilaianAspekDao smkIndikatorPenilaianAspekDao) {
        this.smkIndikatorPenilaianAspekDao = smkIndikatorPenilaianAspekDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkBoImpl.logger = logger;
    }

    public SmkDao getSmkDao() {
        return smkDao;
    }


    public void setSmkDao(SmkDao smkDao) {
        this.smkDao = smkDao;
    }

    @Override
    public void saveDelete(Smk bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String smkId = bean.getEvaluasiPegawaiId();

            ItSmkEntity itSmkEntity = null;

            try {
                // Get data from database by ID
                itSmkEntity = smkDao.getById("smkId", smkId);
            } catch (HibernateException e) {
                logger.error("[SmkBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (itSmkEntity != null) {

                // Modify from bean to entity serializable
                itSmkEntity.setEvaluasiPegawaiId(bean.getEvaluasiPegawaiId());
                itSmkEntity.setFlag(bean.getFlag());
                itSmkEntity.setAction(bean.getAction());
                itSmkEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itSmkEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    smkDao.updateAndSave(itSmkEntity);
                } catch (HibernateException e) {
                    logger.error("[SmkBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Smk, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[SmkBoImpl.saveDelete] Error, not found data Smk with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Smk with request id, please check again your data ...");

            }
        }
        logger.info("[SmkBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(Smk bean) throws GeneralBOException {
        logger.info("[SmkBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String smkId = bean.getEvaluasiPegawaiId();

            ItSmkEntity itSmkEntity = null;
            try {
                itSmkEntity = smkDao.getById("evaluasiPegawaiId", smkId);
            } catch (HibernateException e) {
                logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
            }

            if(bean.getClosed().equalsIgnoreCase("Y")) {
                if(itSmkEntity != null){
                    double pointPrestasi = dataPointPrestasi(smkId);
                    double pointPrestasiRevisi = bean.getNilaiRevisi();
                    double nilai = 0;
                    if(pointPrestasiRevisi > 0){
                        nilai = pointPrestasiRevisi;
                    }else{
                        nilai = pointPrestasi;
                    }

                    String poin_nilaiPrestasi[];
                    poin_nilaiPrestasi = skalaNilai(nilai).split("-");
                    String nilaiPrestasi = poin_nilaiPrestasi[0];
                    int nilaiPoin = Integer.parseInt(poin_nilaiPrestasi[1]);
                    String poinPegawai[] = getPointPegawai(bean.getNip()).split("-");
                    int pointLama = Integer.parseInt(poinPegawai[0]);
                    int poinLebih = Integer.parseInt(poinPegawai[1]);

                    BigDecimal nilaiPres ;
                    BigDecimal nilaiShare  = new BigDecimal(0);
                    if(!bean.getNip().equalsIgnoreCase("US")){
                        nilaiShare  = hitungNilaiShare(bean);
                    }else{
                        bean.setPositionId("1");
                        nilaiShare = new BigDecimal(itSmkEntity.getGrandTotalNilaiPrestasi());
                        pointPrestasi = itSmkEntity.getGrandTotalNilaiPrestasi();
                    }
                    getBawahanJabatan(bean.getBranchId(), bean.getPositionId());

                    if(cekApproveSys(bean.getBranchId(), bean.getPositionId(), bean.getPeriode()) == true){
                        if(strukturJabatanList.size() > 0){
                            for(StrukturJabatan strukturJabatan : strukturJabatanList){
                                List<ItSmkEvaluasiPegawaiAspekDetailEntity> itSmkEntity1 = null;
                                itSmkEntity1 = smkDao.aspekDetail(bean.getPeriode(), strukturJabatan.getNip(), hasilUraian);
                                if(itSmkEntity1.size() > 0){
                                    for (ItSmkEvaluasiPegawaiAspekDetailEntity itSmkEntity2: itSmkEntity1){
                                        nilaiPres = new BigDecimal(0) ;
                                        ItSmkEvaluasiPegawaiAspekDetailEntity evaluasiPegawaiAspekDetail = null;
                                        evaluasiPegawaiAspekDetail = smkEvaluasiPegawaiAspekDetailDao.getById("evaluasiPegawaiAspekDetailId",
                                                itSmkEntity2.getEvaluasiPegawaiAspekDetailId(),"Y");
                                        evaluasiPegawaiAspekDetail.setNilai(nilaiShare);
                                        nilaiPres =  nilaiShare.multiply(itSmkEntity2.getBobot());
                                        nilaiPres = nilaiPres.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_HALF_UP);
                                        evaluasiPegawaiAspekDetail.setNilaiPrestasi(nilaiPres);
                                        try {
                                            smkEvaluasiPegawaiAspekDetailDao.updateAndSave(evaluasiPegawaiAspekDetail);
                                        } catch (HibernateException e) {
                                            logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
                                        }

                                        try {
                                            //update pgawai aspek khusus aspek A
                                            smkEvaluasiPegawaiAspekDetailDao.updateNilaiAfterApprove(itSmkEntity2.getEvaluasiPegawaiId(), itSmkEntity2.getEvaluasiPegawaiAspekDetailId(), nilaiPres);
                                        } catch (HibernateException e) {
                                            logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
                                        }
                                    }
                                }
                            }
                        }
                        try {
                            itSmkEntity.setClosed("Y");
                            itSmkEntity.setNilaiPrestasi(nilaiPrestasi);
                            nilaiPoin += itSmkEntity.getPoin();
                            itSmkEntity.setPointnew(nilaiPoin);
                            itSmkEntity.setNilaiShare(nilaiShare);
                            if(!bean.getNip().equalsIgnoreCase("US")){
                                itSmkEntity.setGrandTotalNilaiPrestasi(pointPrestasi);
                                itSmkEntity.setGrandTotalNilaiPrestasiRevisi(pointPrestasiRevisi);
                            }else{
                                itSmkEntity.setGrandTotalNilaiPrestasi(pointPrestasi);
                                itSmkEntity.setGrandTotalNilaiPrestasiRevisi(pointPrestasiRevisi);
                            }
                            //insertHistory(bean, nilai, nilaiPrestasi, nilaiPoin);
                            //updatePointPegawai(bean.getNip(), pointLama,  nilaiPoin, poinLebih);
                            smkDao.updateAndSave(itSmkEntity);
                        } catch (HibernateException e) {
                            logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
                        }

                        String userLogin = CommonUtil.userLogin();
                        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                        for(ItSmkEvaluasiPegawaiAspekEntity itSmkEvaluasiPegawaiAspekEntity: smkSessionAspekAll){
                            String id = smkHistoryEvaluasiPegawaiAspekDao.getNextSmkEvaluasiPegawaiAspekId();
                            ItHistorySmkEvaluasiPegawaiAspekEntity itHistorySmkEvaluasiPegawaiAspekEntity = new ItHistorySmkEvaluasiPegawaiAspekEntity();
                            itHistorySmkEvaluasiPegawaiAspekEntity.setId(Integer.parseInt(id));
                            itHistorySmkEvaluasiPegawaiAspekEntity.setEvaluasiPegawaiAspekId(itSmkEvaluasiPegawaiAspekEntity.getEvaluasiPegawaiAspekId());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setEvaluasiPegawaiId(itSmkEvaluasiPegawaiAspekEntity.getEvaluasiPegawaiId());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setTipeAspekId(itSmkEvaluasiPegawaiAspekEntity.getTipeAspekId());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setPointPrestasi(itSmkEvaluasiPegawaiAspekEntity.getPointPrestasi());

                            itHistorySmkEvaluasiPegawaiAspekEntity.setCreatedWho(userLogin);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setLastUpdate(updateTime);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setLastUpdateWho(userLogin);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setCreatedDate(updateTime);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setAction("C");
                            itHistorySmkEvaluasiPegawaiAspekEntity.setFlag("Y");

                            smkHistoryEvaluasiPegawaiAspekDao.addAndSave(itHistorySmkEvaluasiPegawaiAspekEntity);
                        }

                        for(ItSmkEvaluasiPegawaiAspekActivityEntity itSmkEvaluasiPegawaiAspekActivityEntity: smkSessionAspekActivityAll){
                            String id = smkHistoryEvaluasiPegawaiAspekActivityDao.getNextSmkEvaluasi_pegawai_aspek_activity();
                            ItHistorySmkEvaluasiPegawaiAspekActivityEntity itHistorySmkEvaluasiPegawaiAspekEntity = new ItHistorySmkEvaluasiPegawaiAspekActivityEntity();

                            itHistorySmkEvaluasiPegawaiAspekEntity.setId(Integer.parseInt(id));
                            itHistorySmkEvaluasiPegawaiAspekEntity.setEvaluasiPegawaiAspekActivityId(itSmkEvaluasiPegawaiAspekActivityEntity.getEvaluasiPegawaiAspekActivityId());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setNilaiActivity(itSmkEvaluasiPegawaiAspekActivityEntity.getNilaiActivity());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setEvaluasiPegawaiAspekDetailId(itSmkEvaluasiPegawaiAspekActivityEntity.getEvaluasiPegawaiAspekDetailId());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setRataRata(itSmkEvaluasiPegawaiAspekActivityEntity.getRataRata());

                            itHistorySmkEvaluasiPegawaiAspekEntity.setCreatedWho(userLogin);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setLastUpdate(updateTime);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setLastUpdateWho(userLogin);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setCreatedDate(updateTime);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setAction("C");
                            itHistorySmkEvaluasiPegawaiAspekEntity.setFlag("Y");
                            smkHistoryEvaluasiPegawaiAspekActivityDao.addAndSave(itHistorySmkEvaluasiPegawaiAspekEntity);
                        }

                        for(ItSmkAspekActivityMonthlyEntity itSmkAspekActivityMonthlyEntity: smkSessionAspekActivityMonthlyAll){
                            String id = smkHistoryAspekActivityMonthlyDao.getNextSmkAspekActivityMonthlyId();
                            ItHistorySmkAspekActivityMonthlyEntity itHistorySmkEvaluasiPegawaiAspekEntity = new ItHistorySmkAspekActivityMonthlyEntity();

                            itHistorySmkEvaluasiPegawaiAspekEntity.setId(Integer.parseInt(id));
                            itHistorySmkEvaluasiPegawaiAspekEntity.setAspekActivityMonthly(itSmkAspekActivityMonthlyEntity.getAspekActivityMonthly());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setEvaluasiPegawaiAspekId(itSmkAspekActivityMonthlyEntity.getEvaluasiPegawaiAspekId());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setBulan(itSmkAspekActivityMonthlyEntity.getBulan());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setNilai(itSmkAspekActivityMonthlyEntity.getNilai());

                            itHistorySmkEvaluasiPegawaiAspekEntity.setCreatedWho(userLogin);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setLastUpdate(updateTime);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setLastUpdateWho(userLogin);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setCreatedDate(updateTime);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setAction("C");
                            itHistorySmkEvaluasiPegawaiAspekEntity.setFlag("Y");
                            smkHistoryAspekActivityMonthlyDao.addAndSave(itHistorySmkEvaluasiPegawaiAspekEntity);
                        }

                        for(ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity: smkSessionAspekActivityPeristiwaAll){
                            String id = smkHistoryEvaluasiPegawaiAspekActivityPeristiwaDao.getNextSmkAspekActivityMonthlyPeristiwaId();
                            ItHistorySmkEvaluasiPegawaiAspekActivityPeristiwaEntity itHistorySmkEvaluasiPegawaiAspekEntity = new ItHistorySmkEvaluasiPegawaiAspekActivityPeristiwaEntity();

                            itHistorySmkEvaluasiPegawaiAspekEntity.setId(Integer.parseInt(id));
                            itHistorySmkEvaluasiPegawaiAspekEntity.setEvaluasiActivityPeristiwaId(itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getEvaluasiActivityPeristiwaId());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setAspekActivityMonthlyId(itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getAspekActivityMonthlyId());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setTanggalKejadian(itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getTanggalKejadian());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setKejadian(itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getKejadian());

                            itHistorySmkEvaluasiPegawaiAspekEntity.setCreatedWho(userLogin);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setLastUpdate(updateTime);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setLastUpdateWho(userLogin);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setCreatedDate(updateTime);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setAction("C");
                            itHistorySmkEvaluasiPegawaiAspekEntity.setFlag("Y");
                            smkHistoryEvaluasiPegawaiAspekActivityPeristiwaDao.addAndSave(itHistorySmkEvaluasiPegawaiAspekEntity);
                        }

                        for(ItSmkEvaluasiPegawaiAspekDetailEntity itSmkEvaluasiPegawaiAspekDetailEntity: smkSessionAspekDetailAll){
                            String id = smkHistoryEvaluasiPegawaiAspekDetailDao.getNextSmkEvaluasiPegawaiAspekDetail();
                            ItHistorySmkEvaluasiPegawaiAspekDetailEntity itHistorySmkEvaluasiPegawaiAspekEntity = new ItHistorySmkEvaluasiPegawaiAspekDetailEntity();

                            itHistorySmkEvaluasiPegawaiAspekEntity.setId(Integer.parseInt(id));
                            itHistorySmkEvaluasiPegawaiAspekEntity.setEvaluasiPegawaiAspekDetailId(itSmkEvaluasiPegawaiAspekDetailEntity.getEvaluasiPegawaiAspekDetailId());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setEvaluasiPegawaiAspekId(itSmkEvaluasiPegawaiAspekDetailEntity.getEvaluasiPegawaiAspekId());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setTarget(itSmkEvaluasiPegawaiAspekDetailEntity.getTarget());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setRealisasi(itSmkEvaluasiPegawaiAspekDetailEntity.getRealisasi());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setNilai(itSmkEvaluasiPegawaiAspekDetailEntity.getNilai());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setSubAspekId(itSmkEvaluasiPegawaiAspekDetailEntity.getSubAspekId());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setUraian(itSmkEvaluasiPegawaiAspekDetailEntity.getUraian());
                            itHistorySmkEvaluasiPegawaiAspekEntity.setNilaiPrestasi(itSmkEvaluasiPegawaiAspekDetailEntity.getNilaiPrestasi());

                            itHistorySmkEvaluasiPegawaiAspekEntity.setCreatedWho(userLogin);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setLastUpdate(updateTime);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setLastUpdateWho(userLogin);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setCreatedDate(updateTime);
                            itHistorySmkEvaluasiPegawaiAspekEntity.setAction("C");
                            itHistorySmkEvaluasiPegawaiAspekEntity.setFlag("Y");
                            smkHistoryEvaluasiPegawaiAspekDetailDao.addAndSave(itHistorySmkEvaluasiPegawaiAspekEntity);
                        }

                    }
                }
            }else if(bean.getClosed().equalsIgnoreCase("D")){
                if (bean!=null) {
                    smkId = bean.getEvaluasiPegawaiId();

                    itSmkEntity = null;
                    try {
                        itSmkEntity = smkDao.getById("evaluasiPegawaiId", smkId);
                    } catch (HibernateException e) {
                        logger.error("[SmkBoImpl.apllySmk] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
                    }

                    try {
                        if(itSmkEntity != null){
                            double pointPrestasi = dataPointPrestasi(smkId);

                            double nilai = 0;
                            nilai = pointPrestasi;

                            String poin_nilaiPrestasi[];
                            poin_nilaiPrestasi = skalaNilai(nilai).split("-");
                            String nilaiPrestasi = poin_nilaiPrestasi[0];

                            int nilaiPoin = Integer.parseInt(poin_nilaiPrestasi[1]);
                            String poinPegawai[] = getPointPegawai(bean.getNip()).split("-");

                            int pointLama = Integer.parseInt(poinPegawai[0]);
                            int poinLebih = Integer.parseInt(poinPegawai[1]);

                            BigDecimal nilaiPres ;
                            BigDecimal nilaiShare  = new BigDecimal(0);
                            if(!bean.getNip().equalsIgnoreCase("US")){
                                nilaiShare  = hitungNilaiShare(bean);
                            }else{
                                bean.setPositionId("1");
                                nilaiShare = new BigDecimal(bean.getUnitUsaha());
                                pointPrestasi = bean.getUnitUsaha();
                            }
                            getBawahanJabatan(bean.getBranchId(), bean.getPositionId());

                            if(strukturJabatanList.size() > 0){
                                for(StrukturJabatan strukturJabatan : strukturJabatanList){
                                    List<ItSmkEvaluasiPegawaiAspekDetailEntity> itSmkEntity1 = null;
                                    itSmkEntity1 = smkDao.aspekDetail(bean.getPeriode(), strukturJabatan.getNip(), hasilUraian);
                                    if(itSmkEntity1.size() > 0){
                                        for (ItSmkEvaluasiPegawaiAspekDetailEntity itSmkEntity2: itSmkEntity1){
                                            nilaiPres = new BigDecimal(0) ;
                                            ItSmkEvaluasiPegawaiAspekDetailEntity evaluasiPegawaiAspekDetail = null;
                                            evaluasiPegawaiAspekDetail = smkEvaluasiPegawaiAspekDetailDao.getById("evaluasiPegawaiAspekDetailId",
                                                    itSmkEntity2.getEvaluasiPegawaiAspekDetailId(),"Y");
                                            evaluasiPegawaiAspekDetail.setNilai(nilaiShare);
                                            nilaiPres =  nilaiShare.multiply(itSmkEntity2.getBobot());
                                            nilaiPres = nilaiPres.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_HALF_UP);
                                            evaluasiPegawaiAspekDetail.setNilaiPrestasi(nilaiPres);
                                            try {
                                                smkEvaluasiPegawaiAspekDetailDao.updateAndSave(evaluasiPegawaiAspekDetail);
                                            } catch (HibernateException e) {
                                                logger.error("[SmkBoImpl.applySmk] Error, " + e.getMessage());
                                                throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
                                            }

                                            try {
                                                //update pgawai aspek khusus aspek A
                                                smkEvaluasiPegawaiAspekDetailDao.updateNilaiAfterApprove(itSmkEntity2.getEvaluasiPegawaiId(), itSmkEntity2.getEvaluasiPegawaiAspekDetailId(), nilaiPres);
                                            } catch (HibernateException e) {
                                                logger.error("[SmkBoImpl.applySmk] Error, " + e.getMessage());
                                                throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
                                            }
                                        }
                                    }
                                }
                            }

                            List<ImtHistorySmkGolonganEntity> historySmkGolonganEntity = new ArrayList<>();
                            historySmkGolonganEntity = smkHistoryGolonganDao.getHistoryJabatan(bean.getNip(), bean.getPeriode());
                            if(historySmkGolonganEntity.size() > 0){
                                for(ImtHistorySmkGolonganEntity historyGolonganLoop: historySmkGolonganEntity){
                                    historyGolonganLoop.setNilaiHuruf(nilaiPrestasi);
                                    historyGolonganLoop.setNilaiAngka(BigDecimal.valueOf(pointPrestasi));
                                    smkHistoryGolonganDao.updateAndSave(historyGolonganLoop);
                                }
                            }

                            try {
                                itSmkEntity.setNilaiPrestasi(nilaiPrestasi);
                                nilaiPoin += itSmkEntity.getPoin();
                                //itSmkEntity.setPointnew(nilaiPoin);
                                itSmkEntity.setNilaiShare(nilaiShare);
                                if(!bean.getNip().equalsIgnoreCase("US")){
                                    itSmkEntity.setGrandTotalNilaiPrestasi(pointPrestasi);
                                }else{
                                    itSmkEntity.setGrandTotalNilaiPrestasi(pointPrestasi);
                                }
                                smkDao.updateAndSave(itSmkEntity);
                            } catch (HibernateException e) {
                                logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
                            }
                        }
                    } catch (Exception e) {
                        logger.error("[SmkBoImpl.apllySmk] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
                    }
                }
            }
            
        }
        logger.info("[SmkBoImpl.saveEdit] end process <<<");
    }

    //function digunakan untuk mengecek apakah atasan satu tingkat diatasnya sudah diapprove atau belum
    public boolean cekApproveSys(String branchId, String positionId, String periode){
        List<ImStrukturJabatanEntity> imStrukturJabatanEntities = null;
        List<ImStrukturJabatanEntity> imStrukturJabatanEntities1 = null;

        List<ItSmkEntity> itSmkEntity = null;

        String nip = "";
        String unit = "";
        String posisi = "";
        String kelompokId = "";
        Boolean hasil = false;
        imStrukturJabatanEntities = strukturJabatanDao.getParent(positionId, branchId);

        if(imStrukturJabatanEntities.size() > 0){
            for(ImStrukturJabatanEntity strukturJabatanEntity : imStrukturJabatanEntities){
                kelompokId = strukturJabatanEntity.getKelompokId();

                imStrukturJabatanEntities1 = strukturJabatanDao.getIdStrukturJabatanAtas(strukturJabatanEntity.getParentId());
            }
            if(positionId.equalsIgnoreCase("1")){
                hasil = true;
            }else{
                if(imStrukturJabatanEntities1.size() > 0){
                    for(ImStrukturJabatanEntity imStrukturJabatanEntity1 : imStrukturJabatanEntities1){
                        nip = imStrukturJabatanEntity1.getNip();
                        unit = imStrukturJabatanEntity1.getBranchId();
                        posisi = imStrukturJabatanEntity1.getPositionId();
                    }
                    itSmkEntity = smkDao.getList(nip, unit, posisi, periode);

                    if(itSmkEntity.size() > 0){
                        for(ItSmkEntity itSmkEntity1: itSmkEntity){
                            if(itSmkEntity1.getClosed() != null){
                                hasil = true ;
                            }
                            //mengecek jika general manager, return selalu true, karena general manager selalu ada diatasan
                            if(kelompokId.equalsIgnoreCase("KL02") || kelompokId.equalsIgnoreCase("KL03")){
                                hasil = true ;
                            }
                        }
                    }else{
                        hasil = true ;
                    }
                }
            }
        }

        return hasil;
    }

    @Override //digunakan untuk mengecek apakah user dalam periode tersebut sudah ada atau belum
    public boolean cekUserSmkSys(String nip, String periode) throws GeneralBOException {
        boolean hasil = false ;
        List<ItSmkEntity> itSmkEntity = null;

        itSmkEntity = smkDao.getListSmkByNipPeriode(nip, periode);
        if(itSmkEntity.size() > 0){
            hasil = false;
        }else{
            hasil = true;
        }

        return hasil;
    }

    private String getPointPegawai(String id){
        int poin = 0 ;
        int poinLebih = 0 ;
        ImBiodataEntity imBiodataEntity = null;

        try {
            imBiodataEntity = biodataDao.getById("nip", id, "Y");
        } catch (HibernateException e) {
            logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
        }

        if(imBiodataEntity != null){
            poin += imBiodataEntity.getPoint();
//            poinLebih += imBiodataEntity.getPoinLebih();
        }

        return poin + "-" + poinLebih;
    }

    @Override
    public void updatePoinPrestasi(String smkId) throws GeneralBOException {
        try {
            smkAspekActivityMonthlyDao.updateNilaiPoint(smkId);
        } catch (HibernateException e) {
            logger.error("[SmkBoImpl.updatePoinPrestasi] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
        }
    }

    public void updatePointPegawai(String id, int poinLama, int poinBaru, int poinLebih)throws GeneralBOException {
        ImBiodataEntity imBiodataEntity = null;
        ImBiodataHistoryEntity imBiodataHistoryEntity = new ImBiodataHistoryEntity();

        int jumlahPoin = 0;
        int hasiljumlahPoin = 0;
        int poinBaruHistory = poinBaru;

        if(!id.equalsIgnoreCase("US")){
            imBiodataEntity = biodataDao.getById("nip", id, "Y");
            if(poinLama + poinBaru >= 20){
                jumlahPoin = poinLama + poinBaru + poinLebih;
                poinBaru = 20;
                hasiljumlahPoin = jumlahPoin - 20;
            }else{
                jumlahPoin = poinBaru + poinLama;
            }
        }

        if(imBiodataEntity != null){
            try {
                String bioHisId = biodataHistoryDao.getNextPersonalHistoryId();
                imBiodataHistoryEntity.setId(bioHisId);
            }catch (HibernateException e){
                logger.error("[SmkBoImpl.updatePointPegawai] Error, " + e.getMessage());
                throw new GeneralBOException("Error when retrieving Next Biodata History ID, " + e.getMessage());
            }
            imBiodataHistoryEntity.setNip(imBiodataEntity.getNip());
            imBiodataHistoryEntity.setNamaPegawai(imBiodataEntity.getNamaPegawai());
            imBiodataHistoryEntity.setGelarDepan(imBiodataEntity.getGelarDepan());
            imBiodataHistoryEntity.setGelarBelakang(imBiodataEntity.getGelarBelakang());
            imBiodataHistoryEntity.setNoKtp(imBiodataEntity.getNoKtp());
            imBiodataHistoryEntity.setAlamat(imBiodataEntity.getAlamat());
            imBiodataHistoryEntity.setRtRw(imBiodataEntity.getRtRw());
            imBiodataHistoryEntity.setDesa(imBiodataEntity.getDesaId());
            imBiodataHistoryEntity.setKecamatan(imBiodataEntity.getKecamatanId());
            imBiodataHistoryEntity.setNoTelp(imBiodataEntity.getNoTelp());
            imBiodataHistoryEntity.setKabupaten(imBiodataEntity.getKotaId());
            imBiodataHistoryEntity.setProvinsi(imBiodataEntity.getProvinsi());
            imBiodataHistoryEntity.setTanggalLahir(imBiodataEntity.getTanggalLahir());
            imBiodataHistoryEntity.setTempatLahir(imBiodataEntity.getTempatLahir());
            imBiodataHistoryEntity.setTipePegawai(imBiodataEntity.getTipePegawai());
            imBiodataHistoryEntity.setFotoUpload(imBiodataEntity.getFotoUpload());
            imBiodataHistoryEntity.setStatusCaption(imBiodataEntity.getStatusCaption());
            imBiodataHistoryEntity.setKeterangan(imBiodataEntity.getKeterangan());

            imBiodataHistoryEntity.setTanggalAktif(imBiodataEntity.getTanggalAktif());
            imBiodataHistoryEntity.setGolongan(imBiodataEntity.getGolongan());
            imBiodataHistoryEntity.setStatusPegawai(imBiodataEntity.getStatusPegawai());
            imBiodataHistoryEntity.setStatusKeluarga(imBiodataEntity.getStatusKeluarga());
            imBiodataHistoryEntity.setJumlahAnak(imBiodataEntity.getJumlahAnak());
            imBiodataHistoryEntity.setJenisKelamin(imBiodataEntity.getGender());
            imBiodataHistoryEntity.setNoSkAktif(imBiodataEntity.getNoSkAktif());
            imBiodataHistoryEntity.setPin(imBiodataEntity.getPin());
            imBiodataHistoryEntity.setPoint(imBiodataEntity.getPoint());
            imBiodataHistoryEntity.setZakatProfesi(imBiodataEntity.getZakatProfesi());
            imBiodataHistoryEntity.setTanggalPensiun(imBiodataEntity.getTanggalPensiun());
            imBiodataHistoryEntity.setDanaPensiun(imBiodataEntity.getDanaPensiun());

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            imBiodataHistoryEntity.setFlag("Y");
            imBiodataHistoryEntity.setAction("C");
            imBiodataHistoryEntity.setCreatedDate(updateTime);
            imBiodataHistoryEntity.setCreatedWho(userLogin);
            imBiodataHistoryEntity.setLastUpdate(updateTime);
            imBiodataHistoryEntity.setLastUpdateWho(userLogin);

            imBiodataEntity.setPoint(jumlahPoin);

            try {
                biodataDao.updateAndSave(imBiodataEntity);
                biodataHistoryDao.addAndSave(imBiodataHistoryEntity);
            } catch (HibernateException e) {
                logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
            }
        }
    }

    private String skalaNilai(double nilai ){
        List<ImSmkSkalaNilaiEntity> imSkala = null;
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");
        String nilaiPrestasi = "";
        String poin = "";
        try {
            // Generating ID, get from postgre sequence
            imSkala = smkSkalaNilaiDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[SmkBoImpl.saveAdd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when getting sequence smkId id, please info to your admin..." + e.getMessage());
        }

        if(imSkala != null){
            String flagAda = "N";
            for(ImSmkSkalaNilaiEntity imSkalaNilai : imSkala){
                if(nilai >= imSkalaNilai.getNilaiBawah() && nilai <= imSkalaNilai.getNilaiAtas()){
                    nilaiPrestasi = imSkalaNilai.getKodeSkala();
                    poin = imSkalaNilai.getPoin() + "";
                    flagAda = "Y";
                    break;
                }
            }
            if(flagAda == "N"){
                for(ImSmkSkalaNilaiEntity imSkalaNilai : imSkala){
                    if(nilai >= imSkalaNilai.getNilaiAtas()){
                        nilaiPrestasi = imSkalaNilai.getKodeSkala();
                        poin = imSkalaNilai.getPoin() + "";
                        break;
                    }
                }
            }
        }

        return nilaiPrestasi + "-" + poin;
    }

    private double dataPointPrestasi(String smkId){
        List<SmkEvaluasiPegawaiAspek> listOfResult = new ArrayList();
        List<ItSmkEvaluasiPegawaiAspekEntity> itAspek = null;

        try {
            //mengambil nilai pada tabel it_hris_evaluasi_pegawai_aspek, mengambil total nilai dari aspek A, B dan C kemudian dijumlahkan seluruh aspek
            itAspek = smkEvaluasiPegawaiAspekDao.getDataAspek(smkId);
        } catch (HibernateException e) {
            logger.error("[SmkBoImpl.getSearchSmkByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        double jumlahPointBaru = 0;
        if(itAspek != null){
            for(ItSmkEvaluasiPegawaiAspekEntity itAsspek : itAspek){
                jumlahPointBaru += itAsspek.getPointPrestasi();
            }
        }

        return jumlahPointBaru;
    }

    @Override
    public Smk getNilaiPrestasiSys(String smkId) throws GeneralBOException {
        Smk smk = new Smk();
        double nilai = dataPointPrestasi(smkId);
        String poin_nilaiPrestasi[] = skalaNilai(nilai).split("-");
        String nilaiPrestasi = poin_nilaiPrestasi[0];

        smk.setNilai(nilai);
        smk.setNilaiPrestasi(nilaiPrestasi);
        return smk;
    }

    @Override
    public String getKlasifikasiNilaiSys(String nilai) throws GeneralBOException {
        String strNilai[] = skalaNilai(Double.parseDouble(nilai)).split("-");

        return strNilai[0];
    }

    //Mengambil Jumlah Nilai dari Aspek A yang akan digunakan untuk memeberikan nilai kepada bawahannya
    private BigDecimal hitungNilaiShare(Smk bean){
        List<SmkEvaluasiPegawaiAspek> listOfResult = new ArrayList();
        List<ItSmkEvaluasiPegawaiAspekEntity> itAspek = null;
        List<ItSmkEvaluasiPegawaiAspekDetailEntity> itAspekDetail = null;
        List<ImtSmkJabatanEntity> imtJabatan = null;
        List<ImtSmkJabatanDetailEntity> imtSmkJabatanDetail = null;

        try {
            //mengambil nilai pada tabel it_hris_evaluasi_pegawai_aspek, mengambil total nilai dari aspek A, B dan C kemudian dijumlahkan seluruh aspek
            itAspek = smkEvaluasiPegawaiAspekDao.getDataAspek(bean.getEvaluasiPegawaiId());
            //Mengambil Data Dari tabel imt_hris_smk_jabatan digunakan untuk mengambi data jumlah bobot
            imtJabatan = smkJabatanDao.getDataJabatan(bean.getBranchId(), bean.getPositionId(), "A");
        } catch (HibernateException e) {
            logger.error("[SmkBoImpl.getSearchSmkByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        //Nilai Prestasi Hanya A
        BigDecimal jumlahNilaiPrestasiA = new BigDecimal(0);
        if(itAspek != null){
            for(ItSmkEvaluasiPegawaiAspekEntity itAsspek : itAspek){
                if(itAsspek.getTipeAspekId().equalsIgnoreCase("A")){
                    jumlahNilaiPrestasiA = jumlahNilaiPrestasiA.add(BigDecimal.valueOf(itAsspek.getPointPrestasi()));
                    itAspekDetail = smkEvaluasiPegawaiAspekDetailDao.getListAspekId(itAsspek.getEvaluasiPegawaiId());
                    break;
                }
            }
        }

        //Nilai Jumlah Bobot AspekId A
        BigDecimal jumlahBobot = new BigDecimal(0) ;
        if(imtJabatan != null){
            for(ImtSmkJabatanEntity imJabatan : imtJabatan){
                jumlahBobot = jumlahBobot.add(BigDecimal.valueOf(imJabatan.getBobot()));

                //digunakan untuk mengambil bobot unit usaha, kabid, kabag.
                imtSmkJabatanDetail = smkJabatanDetaiDao.getListData(imJabatan.getJabatanSmkId());
            }
        }

        BigDecimal pengurangBobot = new BigDecimal(0);
        BigDecimal pengurangNilaiPrestasi = new BigDecimal(0);
        BigDecimal tmpNilaiPrestasi = new BigDecimal(0);
        BigDecimal tmpBobot = new BigDecimal(0);

        if(imtSmkJabatanDetail.size() > 0){
            for(ImtSmkJabatanDetailEntity imtSmkJabatanDetailEntity : imtSmkJabatanDetail){
                if(imtSmkJabatanDetailEntity.getIndikatorKinerja().equalsIgnoreCase("Unit Usaha") ||
                        imtSmkJabatanDetailEntity.getIndikatorKinerja().equalsIgnoreCase("Bidang") ||
                        imtSmkJabatanDetailEntity.getIndikatorKinerja().equalsIgnoreCase("Bagian")){
                    pengurangBobot = pengurangBobot.add(imtSmkJabatanDetailEntity.getBobot());
                }
            }
        }

        if(itAspekDetail.size() > 0){
            for(ItSmkEvaluasiPegawaiAspekDetailEntity itAspekDetails : itAspekDetail){
                if(itAspekDetails.getUraian().equalsIgnoreCase("Unit Usaha") || itAspekDetails.getUraian().equalsIgnoreCase("Bidang") ||
                        itAspekDetails.getUraian().equalsIgnoreCase("Bagian")){
                    pengurangNilaiPrestasi = pengurangNilaiPrestasi.add(itAspekDetails.getNilaiPrestasi());
                }
            }
        }

        tmpBobot = jumlahBobot.subtract(pengurangBobot);
        tmpNilaiPrestasi = jumlahNilaiPrestasiA.subtract(pengurangNilaiPrestasi);

        //Hasil Dari perhitungan Nilai untuk bawahan
        BigDecimal hasilNilai = new BigDecimal(0);
        hasilNilai = tmpNilaiPrestasi.divide(tmpBobot, RoundingMode.HALF_UP);
        hasilNilai = hasilNilai.multiply(BigDecimal.valueOf(100));

        return hasilNilai;
    }

    public void insertHistory(Smk itSmkEntity, double nilai, String nilaiPrestasi, int poin){
        ItSmkHistoryEvaluasiPegawaiEntity itHistorySmk = new ItSmkHistoryEvaluasiPegawaiEntity();
        String id = smkHistory.getNextHistory();
        itHistorySmk.setHistoryEvaluasiPegawaiId(id);
        itHistorySmk.setNip(itSmkEntity.getNip());
        itHistorySmk.setPegawaiName(itSmkEntity.getPegawaiName());
        itHistorySmk.setGolonganId(itSmkEntity.getGolonganId());
        itHistorySmk.setGolonganName(itSmkEntity.getGolonganName());
        itHistorySmk.setPeriode(itSmkEntity.getPeriode());
        itHistorySmk.setNilai(nilai);
        itHistorySmk.setPoin(poin);
        itHistorySmk.setNilaiPrestasi(nilaiPrestasi);

        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        itHistorySmk.setFlag("Y");
        itHistorySmk.setAction("C");
        itHistorySmk.setCreatedWho(userLogin);
        itHistorySmk.setLastUpdateWho(userLogin);
        itHistorySmk.setCreatedDate(updateTime);
        itHistorySmk.setLastUpdate(updateTime);

        try {
            smkHistory.addAndSave(itHistorySmk);
        } catch (HibernateException e) {
            logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
        }

        // insert SMK History Golongan
        String idSmkGolongan = smkHistoryGolonganDao.getNextId(itSmkEntity.getPeriode());
        ImtHistorySmkGolonganEntity imtHistorySmkGolonganEntity = new ImtHistorySmkGolonganEntity();

        imtHistorySmkGolonganEntity.setIdHistorySmkGolongan(idSmkGolongan);
        imtHistorySmkGolonganEntity.setNip(itSmkEntity.getNip());
        imtHistorySmkGolonganEntity.setTahun(itSmkEntity.getPeriode());
        imtHistorySmkGolonganEntity.setNilaiHuruf(nilaiPrestasi);
        imtHistorySmkGolonganEntity.setNilaiAngka(BigDecimal.valueOf(nilai));
        imtHistorySmkGolonganEntity.setPositionId(itSmkEntity.getPositionId());
        imtHistorySmkGolonganEntity.setBranchId(itSmkEntity.getBranchId());
        imtHistorySmkGolonganEntity.setGolonganId(itSmkEntity.getGolonganId());
        imtHistorySmkGolonganEntity.setPoin(poin);


        imtHistorySmkGolonganEntity.setCreatedWho(userLogin);
        imtHistorySmkGolonganEntity.setLastUpdate(updateTime);
        imtHistorySmkGolonganEntity.setLastUpdateWho(userLogin);
        imtHistorySmkGolonganEntity.setCreatedDate(updateTime);
        imtHistorySmkGolonganEntity.setAction("C");
        imtHistorySmkGolonganEntity.setFlag("Y");
        try {
            smkHistoryGolonganDao.addAndSave(imtHistorySmkGolonganEntity);
        } catch (HibernateException e) {
            logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
        }

    }

    //Digunakan untuk mengambil bawahan jabatan
    String hasilUraian = "";
    private void getBawahanJabatan(String branchId, String posisiId){
        List<ImStrukturJabatanEntity> strukturJabatans = null;
        strukturJabatans = strukturJabatanDao.getIdStrukturJabatanAtasan(branchId, posisiId);
        String idParent = "";
        String []uraian ;

        if(strukturJabatans.size() > 0){
            for(ImStrukturJabatanEntity strukturJabatanEntity : strukturJabatans){
                idParent = strukturJabatanEntity.getStrukturJabatanId();
                uraian = strukturJabatanEntity.getPositionName().split(" ");
                if(strukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL01")){
                    this.hasilUraian = "Unit Usaha";
                }else{
                    this.hasilUraian = uraian[1];
                }
            }
        }
        strukturJabatanList.clear();
        getListStruktur(idParent);
    }

    private List<StrukturJabatan> strukturJabatanList = new ArrayList();

    private String getListStruktur(String id){
        //List<StrukturJabatan> strukturJabatans = new ArrayList();
        List<ImStrukturJabatanEntity> imStrukturJabatanEntities = null;
        String hasil = "";
        String []posistionName ;
        imStrukturJabatanEntities = strukturJabatanDao.getIdStrukturJabatan(id);
        if(imStrukturJabatanEntities.size() > 0){
            for(ImStrukturJabatanEntity imStrukturJabatanEntity : imStrukturJabatanEntities){
                StrukturJabatan strukturJabatan1 = new StrukturJabatan();
                strukturJabatan1.setNip(imStrukturJabatanEntity.getNip());
                strukturJabatan1.setPositionName(imStrukturJabatanEntity.getPositionName());
                strukturJabatan1.setUraian(hasilUraian);

                hasil = imStrukturJabatanEntity.getStrukturJabatanId();
                strukturJabatanList.add(strukturJabatan1);
                if(imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL02") || imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL03") ||
                        imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL04") || imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL05") ||
                        imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL06") || imStrukturJabatanEntity.getKelompokId().equalsIgnoreCase("KL07")){
                    strukturJabatanList.add(strukturJabatan1);
                }
                getListStruktur(getListStruktur(hasil));
            }
        }
        return hasil;
    }

    /*while(flag.equalsIgnoreCase("Y")) {
            imStrukturJabatanEntities = strukturJabatanDao.getIdStrukturJabatan(id);
            if(imStrukturJabatanEntities.size() > 0){
                for(ImStrukturJabatanEntity imStrukturJabatanEntity : imStrukturJabatanEntities){
                    StrukturJabatan strukturJabatan1 = new StrukturJabatan();
                    strukturJabatan1.setNip(imStrukturJabatanEntity.getNip());
                    strukturJabatan1.setPositionName(imStrukturJabatanEntity.getPositionName());

                    hasil = imStrukturJabatanEntity.getStrukturJabatanId() + "-" + (imStrukturJabatanEntity.getLevel() + 1);
                    strukturJabatanList.add(strukturJabatan1);
                    getListStruktur(hasil);
                }
            }else{
                flag = "N";
            }
        }*/

    private boolean cekDataInsertSmk(Smk bean){
        boolean hasil = false;
        Map hsCriteria = new HashMap();
        hsCriteria.put("periode", bean.getPeriode());
        hsCriteria.put("nip", bean.getNip());
        hsCriteria.put("flag", "Y");

        List<ItSmkEntity> itSmkEntity = null;
        try {
            itSmkEntity = smkDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[SmkBoImpl.getSearchSmkByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(itSmkEntity.size() > 0){
            hasil = false;
        }else{
            hasil = true;
        }

        return hasil;
    }

    @Override
    public Smk saveAdd(Smk bean) throws GeneralBOException {
        logger.info("[SmkBoImpl.saveAdd] start process >>>");

        List<ImtSmkJabatanEntity> imtSmkJabatanEntityList = null;

        if(cekDataInsertSmk(bean) == true){
            if (bean!=null) {
                String smkId;
                String smkEvaluasiAspek;
                String[] per = bean.getPeriode().split("");
                try {
                    // Generating ID, get from postgre sequence
                    smkId = smkDao.getNextSmkId(per[2] + per[3]);
                } catch (HibernateException e) {
                    logger.error("[SmkBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence smkId id, please info to your admin..." + e.getMessage());
                }

                ItSmkEntity itSmkEntity = new ItSmkEntity();
                if(bean.getTipeSmk().equalsIgnoreCase("US")){
                    itSmkEntity.setGrandTotalNilaiPrestasi(bean.getUnitUsaha());
                    itSmkEntity.setPegawaiName("Unit Usaha");
                    itSmkEntity.setPositionId("-");
                }else{
                    itSmkEntity.setGrandTotalNilaiPrestasi(0);
                    imtSmkJabatanEntityList = smkJabatanDao.getDataAspek(bean.getPositionId(), bean.getBranchId()) ;
                    itSmkEntity.setPegawaiName(bean.getPegawaiName());
                    itSmkEntity.setPositionId(bean.getPositionId());
                }

                itSmkEntity.setEvaluasiPegawaiId(smkId);
                itSmkEntity.setNip(bean.getNip());
                itSmkEntity.setBranchId(bean.getBranchId());
                itSmkEntity.setDivisiId(bean.getDivisiId());
                itSmkEntity.setGolonganId(bean.getGolonganId());
                itSmkEntity.setPeriode(bean.getPeriode());
                itSmkEntity.setBagianId(bean.getBagianId());
                itSmkEntity.setPoin(bean.getPoin());
                itSmkEntity.setPointnew(0);
                itSmkEntity.setTipePegawaiId(bean.getTipePegawaiId());
                itSmkEntity.setStatusPegawai(bean.getStatusPegawai());
                itSmkEntity.setGrandTotalNilaiPrestasiRevisi(0);
                itSmkEntity.setMasaKerjaBln(bean.getMasaKerjaBln());

                itSmkEntity.setFlag(bean.getFlag());
                itSmkEntity.setAction("C");
                itSmkEntity.setCreatedDate(bean.getLastUpdate());
                itSmkEntity.setCreatedWho(bean.getCreatedWho());
                itSmkEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itSmkEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    smkDao.addAndSave(itSmkEntity);
                } catch (HibernateException e) {
                    logger.error("[SmkBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Smk, please info to your admin..." + e.getMessage());
                }

                if(imtSmkJabatanEntityList != null){
                    for(ImtSmkJabatanEntity imtSmkJabatanEntity : imtSmkJabatanEntityList){
                        smkEvaluasiAspek = smkEvaluasiPegawaiAspekDao.getNextSmkEvaluasiPegawaiAspekId();
                        ItSmkEvaluasiPegawaiAspekEntity itSmkEvaluasiPegawaiAspekEntity = new ItSmkEvaluasiPegawaiAspekEntity();
                        itSmkEvaluasiPegawaiAspekEntity.setEvaluasiPegawaiAspekId(smkEvaluasiAspek);
                        itSmkEvaluasiPegawaiAspekEntity.setEvaluasiPegawaiId(smkId);
                        itSmkEvaluasiPegawaiAspekEntity.setTipeAspekId(imtSmkJabatanEntity.getTipeAspekId());
                        itSmkEvaluasiPegawaiAspekEntity.setPointPrestasi(0.0);

                        itSmkEvaluasiPegawaiAspekEntity.setFlag(bean.getFlag());
                        itSmkEvaluasiPegawaiAspekEntity.setAction(bean.getAction());
                        itSmkEvaluasiPegawaiAspekEntity.setCreatedWho(bean.getCreatedWho());
                        itSmkEvaluasiPegawaiAspekEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        itSmkEvaluasiPegawaiAspekEntity.setCreatedDate(bean.getCreatedDate());
                        itSmkEvaluasiPegawaiAspekEntity.setLastUpdate(bean.getLastUpdate());

                        try {
                            smkEvaluasiPegawaiAspekDao.addAndSave(itSmkEvaluasiPegawaiAspekEntity);
                        } catch (HibernateException e) {
                            logger.error("[SmkBoImpl.saveAdd] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving new data Smk, please info to your admin..." + e.getMessage());
                        }

                        if(!imtSmkJabatanEntity.getTipeAspekId().equalsIgnoreCase("A")){
                            List<ImtSmkJabatanDetailEntity> imtSmkJabatanDetailEntities = null;
                            imtSmkJabatanDetailEntities = smkJabatanDetaiDao.getIndikator(imtSmkJabatanEntity.getPositionId(), imtSmkJabatanEntity.getBranchId(),
                                    imtSmkJabatanEntity.getTipeAspekId());
                            if(imtSmkJabatanDetailEntities != null){
                                for(ImtSmkJabatanDetailEntity imSmkIndikatorPenilaianAspekEntity : imtSmkJabatanDetailEntities){
                                    String pegawaiAspekDetailId = smkEvaluasiPegawaiAspekDetailDao.getNextSmkEvaluasiPegawaiAspekDetail(per[2] + per[3]);
                                    ItSmkEvaluasiPegawaiAspekDetailEntity itSmkEvaluasiPegawaiAspekDetailEntity = new ItSmkEvaluasiPegawaiAspekDetailEntity();

                                    itSmkEvaluasiPegawaiAspekDetailEntity.setEvaluasiPegawaiAspekDetailId(pegawaiAspekDetailId);
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setEvaluasiPegawaiAspekId(smkEvaluasiAspek);
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setBobot(imSmkIndikatorPenilaianAspekEntity.getBobot());
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setSatuan(imSmkIndikatorPenilaianAspekEntity.getSatuan());
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setNilai(BigDecimal.valueOf(0.0));
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setNilaiPrestasi(BigDecimal.valueOf(0.0));
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setUraian(imSmkIndikatorPenilaianAspekEntity.getIndikatorKinerja());

                                    itSmkEvaluasiPegawaiAspekDetailEntity.setFlag(bean.getFlag());
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setAction(bean.getAction());
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setCreatedWho(bean.getCreatedWho());
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setCreatedDate(bean.getCreatedDate());
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setLastUpdate(bean.getLastUpdate());
                                    try {
                                        smkEvaluasiPegawaiAspekDetailDao.addAndSave(itSmkEvaluasiPegawaiAspekDetailEntity);
                                    } catch (HibernateException e) {
                                        logger.error("[SmkBoImpl.saveAdd] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when saving new data Smk, please info to your admin..." + e.getMessage());
                                    }

                                    String pegawaiAspekActivityId = smkEvaluasiPegawaiAspekActivityDao.getNextSmkEvaluasi_pegawai_aspek_activity();
                                    ItSmkEvaluasiPegawaiAspekActivityEntity itSmkEvaluasiPegawaiAspekActivityEntity = new ItSmkEvaluasiPegawaiAspekActivityEntity();

                                    itSmkEvaluasiPegawaiAspekActivityEntity.setEvaluasiPegawaiAspekActivityId(pegawaiAspekActivityId);
                                    itSmkEvaluasiPegawaiAspekActivityEntity.setEvaluasiPegawaiAspekDetailId(pegawaiAspekDetailId);
                                    itSmkEvaluasiPegawaiAspekActivityEntity.setRataRata(0.0);
                                    itSmkEvaluasiPegawaiAspekActivityEntity.setNilaiActivity(0.0);
                                    itSmkEvaluasiPegawaiAspekActivityEntity.setFlag(bean.getFlag());
                                    itSmkEvaluasiPegawaiAspekActivityEntity.setAction(bean.getAction());
                                    itSmkEvaluasiPegawaiAspekActivityEntity.setCreatedWho(bean.getCreatedWho());
                                    itSmkEvaluasiPegawaiAspekActivityEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                    itSmkEvaluasiPegawaiAspekActivityEntity.setCreatedDate(bean.getCreatedDate());
                                    itSmkEvaluasiPegawaiAspekActivityEntity.setLastUpdate(bean.getLastUpdate());
                                    try {
                                        smkEvaluasiPegawaiAspekActivityDao.addAndSave(itSmkEvaluasiPegawaiAspekActivityEntity);
                                    } catch (HibernateException e) {
                                        logger.error("[SmkBoImpl.saveAdd] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when saving new data Smk, please info to your admin..." + e.getMessage());
                                    }

                                    for(int bulan = 1 ; bulan <= 12 ; bulan++){
                                        String aspekActivityMonthlyId = smkAspekActivityMonthlyDao.getNextSmkAspekActivityMonthlyId();
                                        ItSmkAspekActivityMonthlyEntity itSmkAspekActivityMonthlyEntity = new ItSmkAspekActivityMonthlyEntity();
                                        itSmkAspekActivityMonthlyEntity.setAspekActivityMonthly(aspekActivityMonthlyId);
                                        itSmkAspekActivityMonthlyEntity.setEvaluasiPegawaiAspekId(pegawaiAspekActivityId);
                                        itSmkAspekActivityMonthlyEntity.setBulan(bulan);
                                        itSmkAspekActivityMonthlyEntity.setNilai(0);

                                        itSmkAspekActivityMonthlyEntity.setFlag(bean.getFlag());
                                        itSmkAspekActivityMonthlyEntity.setAction(bean.getAction());
                                        itSmkAspekActivityMonthlyEntity.setCreatedWho(bean.getCreatedWho());
                                        itSmkAspekActivityMonthlyEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                        itSmkAspekActivityMonthlyEntity.setCreatedDate(bean.getCreatedDate());
                                        itSmkAspekActivityMonthlyEntity.setLastUpdate(bean.getLastUpdate());

                                        try {
                                            smkAspekActivityMonthlyDao.addAndSave(itSmkAspekActivityMonthlyEntity);
                                        } catch (HibernateException e) {
                                            logger.error("[SmkBoImpl.saveAdd] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when saving new data Smk, please info to your admin..." + e.getMessage());
                                        }
                                    }
                                }
                            }
                        }else{
                            //List<ImtSmkJabatanDetailEntity> imtSmkJabatanDetailEntities = null;
                            HttpSession session = ServletActionContext.getRequest().getSession();
                            List<SmkJabatanDetail> smkJabatanDetail = (List<SmkJabatanDetail>) session.getAttribute("listSmkJabatanSubTipeA");
                            //imtSmkJabatanDetailEntities = smkJabatanDetaiDao.getIndikator(imtSmkJabatanEntity.getPositionId(), imtSmkJabatanEntity.getBranchId(), "A");
                            if(smkJabatanDetail != null){
                                for(SmkJabatanDetail smkJabatanDetail1 : smkJabatanDetail){
                                    String pegawaiAspekDetailId = smkEvaluasiPegawaiAspekDetailDao.getNextSmkEvaluasiPegawaiAspekDetail(per[2] + per[3]);
                                    ItSmkEvaluasiPegawaiAspekDetailEntity itSmkEvaluasiPegawaiAspekDetailEntity = new ItSmkEvaluasiPegawaiAspekDetailEntity();

                                    itSmkEvaluasiPegawaiAspekDetailEntity.setEvaluasiPegawaiAspekDetailId(pegawaiAspekDetailId);
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setEvaluasiPegawaiAspekId(smkEvaluasiAspek);
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setNilai(smkJabatanDetail1.getNilai());
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setNilaiPrestasi(smkJabatanDetail1.getNilaiPrestasi());
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setTarget("-");
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setRealisasi("-");
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setUraian(smkJabatanDetail1.getIndikatorKinerja());
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setSubAspekId(smkJabatanDetail1.getSubAspekA());
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setBobot(smkJabatanDetail1.getBobot());
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setSatuan(smkJabatanDetail1.getSatuan());

                                    itSmkEvaluasiPegawaiAspekDetailEntity.setFlag(bean.getFlag());
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setAction(bean.getAction());
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setCreatedWho(bean.getCreatedWho());
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setCreatedDate(bean.getCreatedDate());
                                    itSmkEvaluasiPegawaiAspekDetailEntity.setLastUpdate(bean.getLastUpdate());
                                    try {
                                        smkEvaluasiPegawaiAspekDetailDao.addAndSave(itSmkEvaluasiPegawaiAspekDetailEntity);
                                    } catch (HibernateException e) {
                                        logger.error("[SmkBoImpl.saveAdd] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when saving new data Smk, please info to your admin..." + e.getMessage());
                                    }

                                    bulan(bean, pegawaiAspekDetailId);
                                }
                                session.removeAttribute("listSmkJabatanSubTipeA");
                            }
                        }
                    }
                }
            }
        }

        logger.info("[SmkBoImpl.saveAdd] end process <<<");
        return null;
    }

    private void bulan(Smk bean, String pegawaiAspekDetailId){
        String pegawaiAspekActivityId = smkEvaluasiPegawaiAspekActivityDao.getNextSmkEvaluasi_pegawai_aspek_activity();
        ItSmkEvaluasiPegawaiAspekActivityEntity itSmkEvaluasiPegawaiAspekActivityEntity = new ItSmkEvaluasiPegawaiAspekActivityEntity();

        itSmkEvaluasiPegawaiAspekActivityEntity.setEvaluasiPegawaiAspekActivityId(pegawaiAspekActivityId);
        itSmkEvaluasiPegawaiAspekActivityEntity.setEvaluasiPegawaiAspekDetailId(pegawaiAspekDetailId);
        itSmkEvaluasiPegawaiAspekActivityEntity.setRataRata(0.0);
        itSmkEvaluasiPegawaiAspekActivityEntity.setNilaiActivity(0.0);
        itSmkEvaluasiPegawaiAspekActivityEntity.setFlag(bean.getFlag());
        itSmkEvaluasiPegawaiAspekActivityEntity.setAction(bean.getAction());
        itSmkEvaluasiPegawaiAspekActivityEntity.setCreatedWho(bean.getCreatedWho());
        itSmkEvaluasiPegawaiAspekActivityEntity.setLastUpdateWho(bean.getLastUpdateWho());
        itSmkEvaluasiPegawaiAspekActivityEntity.setCreatedDate(bean.getCreatedDate());
        itSmkEvaluasiPegawaiAspekActivityEntity.setLastUpdate(bean.getLastUpdate());
        try {
            smkEvaluasiPegawaiAspekActivityDao.addAndSave(itSmkEvaluasiPegawaiAspekActivityEntity);
        } catch (HibernateException e) {
            logger.error("[SmkBoImpl.saveAdd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving new data Smk, please info to your admin..." + e.getMessage());
        }

        for(int bulan = 1 ; bulan <= 12 ; bulan++){
            String aspekActivityMonthlyId = smkAspekActivityMonthlyDao.getNextSmkAspekActivityMonthlyId();
            ItSmkAspekActivityMonthlyEntity itSmkAspekActivityMonthlyEntity = new ItSmkAspekActivityMonthlyEntity();
            itSmkAspekActivityMonthlyEntity.setAspekActivityMonthly(aspekActivityMonthlyId);
            itSmkAspekActivityMonthlyEntity.setEvaluasiPegawaiAspekId(pegawaiAspekActivityId);
            itSmkAspekActivityMonthlyEntity.setBulan(bulan);
            itSmkAspekActivityMonthlyEntity.setNilai(0);

            itSmkAspekActivityMonthlyEntity.setFlag(bean.getFlag());
            itSmkAspekActivityMonthlyEntity.setAction(bean.getAction());
            itSmkAspekActivityMonthlyEntity.setCreatedWho(bean.getCreatedWho());
            itSmkAspekActivityMonthlyEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itSmkAspekActivityMonthlyEntity.setCreatedDate(bean.getCreatedDate());
            itSmkAspekActivityMonthlyEntity.setLastUpdate(bean.getLastUpdate());

            try {
                smkAspekActivityMonthlyDao.addAndSave(itSmkAspekActivityMonthlyEntity);
            } catch (HibernateException e) {
                logger.error("[SmkBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Smk, please info to your admin..." + e.getMessage());
            }
        }
    }
    @Override
    public List<Smk> getByCriteria(Smk searchBean) throws GeneralBOException {
        logger.info("[SmkBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Smk> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getEvaluasiPegawaiId() != null && !"".equalsIgnoreCase(searchBean.getEvaluasiPegawaiId())) {
                hsCriteria.put("smk_id", searchBean.getEvaluasiPegawaiId());
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


            List<ItSmkEntity> itSmkEntity = null;
            try {

                itSmkEntity = smkDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[SmkBoImpl.getSearchSmkByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itSmkEntity != null){
                Smk returnSmk;
                // Looping from dao to object and save in collection
                for(ItSmkEntity smkEntity : itSmkEntity){
                    returnSmk = new Smk();
                    returnSmk.setEvaluasiPegawaiId(smkEntity.getEvaluasiPegawaiId());

                    returnSmk.setCreatedWho(smkEntity.getCreatedWho());
                    returnSmk.setCreatedDate(smkEntity.getCreatedDate());
                    returnSmk.setLastUpdate(smkEntity.getLastUpdate());

                    returnSmk.setAction(smkEntity.getAction());
                    returnSmk.setFlag(smkEntity.getFlag());
                    listOfResult.add(returnSmk);
                }
            }
        }
        logger.info("[SmkBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<SmkJabatanDetail> getDataIndikatorA(String nip, String positionId, String branchId, String periode) throws GeneralBOException {
        List<SmkJabatanDetail> listOfResult = new ArrayList();
        if (!branchId.equals("")) {
            List<ImtSmkJabatanDetailEntity> imtSmkJabatanDetailEntities = null;

            try {
                int periodeSebelumnnya = 0;
                if(!periode.equalsIgnoreCase("")){
                    periodeSebelumnnya = Integer.parseInt(periode) - 1;
                }
                List<ItSmkEntity> smkHistory = smkDao.getList(nip, branchId, positionId, periodeSebelumnnya + "");

                if(smkHistory.size() > 0){
                    imtSmkJabatanDetailEntities = smkJabatanDetaiDao.getIndikatorHistory(nip, "A", periodeSebelumnnya + "");
                    if(imtSmkJabatanDetailEntities.size() == 0){
                        imtSmkJabatanDetailEntities = smkJabatanDetaiDao.getIndikator(positionId, branchId, "A");
                    }
                }else{
                    imtSmkJabatanDetailEntities = smkJabatanDetaiDao.getIndikator(positionId, branchId, "A");
                }

            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.getSearchSmkJabatanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imtSmkJabatanDetailEntities != null){
                SmkJabatanDetail smkJabatanDetail;
                for(ImtSmkJabatanDetailEntity imtSmkJabatanDetailEntity : imtSmkJabatanDetailEntities){
                    smkJabatanDetail = new SmkJabatanDetail();
                    smkJabatanDetail.setJabatanSmkDetailId(imtSmkJabatanDetailEntity.getJabatanSmkDetailId());
                    smkJabatanDetail.setIndikatorKinerja(imtSmkJabatanDetailEntity.getIndikatorKinerja());
                    smkJabatanDetail.setBobot(imtSmkJabatanDetailEntity.getBobot());
                    smkJabatanDetail.setSatuan(imtSmkJabatanDetailEntity.getSatuan());
                    smkJabatanDetail.setSubAspekA(imtSmkJabatanDetailEntity.getSubAspekA());
                    smkJabatanDetail.setNilai(BigDecimal.valueOf(0));
                    smkJabatanDetail.setNilaiPrestasi(BigDecimal.valueOf(0));

                    if(imtSmkJabatanDetailEntity.getIndikatorKinerja().equalsIgnoreCase("Unit Usaha")){
                        BigDecimal nilai = getListNilai(branchId, periode);
                        BigDecimal total = nilai.multiply(imtSmkJabatanDetailEntity.getBobot()).divide(BigDecimal.valueOf(100), 4, BigDecimal.ROUND_HALF_UP);

                        smkJabatanDetail.setNilai(nilai);
                        smkJabatanDetail.setNilaiPrestasi(total);
                    }else if(imtSmkJabatanDetailEntity.getIndikatorKinerja().equalsIgnoreCase("Bidang")){
                        BigDecimal nilai = getListNilai(nip, branchId, periode, "KL03");

                        BigDecimal total = new BigDecimal(0);
                        if(nilai != null){
                            total = nilai.multiply(imtSmkJabatanDetailEntity.getBobot()).divide(BigDecimal.valueOf(100), 4, BigDecimal.ROUND_HALF_UP);
                        }else{
                            nilai = new BigDecimal(0);
                        }

                        smkJabatanDetail.setNilai(nilai);
                        smkJabatanDetail.setNilaiPrestasi(total);
                    }else if(imtSmkJabatanDetailEntity.getIndikatorKinerja().equalsIgnoreCase("Bagian")){
                        BigDecimal nilai = getListNilai(nip, branchId, periode, "KL04");
                        BigDecimal total = new BigDecimal(0);

                        if(nilai != null){
                            total = nilai.multiply(imtSmkJabatanDetailEntity.getBobot()).divide(BigDecimal.valueOf(100), 4, BigDecimal.ROUND_HALF_UP);
                        }else{
                            nilai = new BigDecimal(0);
                        }

                        smkJabatanDetail.setNilai(nilai);
                        smkJabatanDetail.setNilaiPrestasi(total);
                    }

                    listOfResult.add(smkJabatanDetail);
                }
            }
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listSmkJabatanSubTipeA");
        session.setAttribute("listSmkJabatanSubTipeA", listOfResult);

        return listOfResult;
    }

    @Override
    public List<SmkJabatanDetail> getDataIndikator(String branchId, String positionId, String tipeAspek) throws GeneralBOException {
        List<SmkJabatanDetail> listOfResult = new ArrayList();
        if (!branchId.equals("")) {
            List<ImtSmkJabatanDetailEntity> imtSmkJabatanDetailEntities = null;

            try {
                imtSmkJabatanDetailEntities = smkJabatanDetaiDao.getIndikator(positionId, branchId, tipeAspek);
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.getSearchSmkJabatanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imtSmkJabatanDetailEntities != null){
                SmkJabatanDetail smkJabatanDetail;
                // Looping from dao to object and save in collection
                for(ImtSmkJabatanDetailEntity imtSmkJabatanDetailEntity : imtSmkJabatanDetailEntities){
                    smkJabatanDetail = new SmkJabatanDetail();
                    smkJabatanDetail.setJabatanSmkDetailId(imtSmkJabatanDetailEntity.getJabatanSmkDetailId());
                    smkJabatanDetail.setIndikatorKinerja(imtSmkJabatanDetailEntity.getIndikatorKinerja());
                    smkJabatanDetail.setBobot(imtSmkJabatanDetailEntity.getBobot());
                    smkJabatanDetail.setSubAspekA(imtSmkJabatanDetailEntity.getSubAspekA());

                    listOfResult.add(smkJabatanDetail);
                }
            }
        }

        return listOfResult;
    }

    //get data indikator lama
    /*public List<SmkIndikatorPenilaianAspek> getDataIndikator(String branchId, String positionId, String tipeAspek) throws GeneralBOException {
        List<SmkIndikatorPenilaianAspek> listOfResult = new ArrayList();
        if (!branchId.equals("")) {
            List<ImSmkIndikatorPenilaianAspekEntity> imSmkIndikatorPenilaianAspekEntityList = null;

            try {
                imSmkIndikatorPenilaianAspekEntityList = smkIndikatorPenilaianAspekDao.getDataIndikator(branchId, positionId, tipeAspek);
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.getSearchSmkJabatanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imSmkIndikatorPenilaianAspekEntityList != null){
                SmkIndikatorPenilaianAspek returnSmkIndikatorPenilaianAspek;
                // Looping from dao to object and save in collection
                for(ImSmkIndikatorPenilaianAspekEntity smkIndikatorPenilaianAspekEntity : imSmkIndikatorPenilaianAspekEntityList){
                    returnSmkIndikatorPenilaianAspek = new SmkIndikatorPenilaianAspek();
                    returnSmkIndikatorPenilaianAspek.setBranchId(smkIndikatorPenilaianAspekEntity.getBranchId());
                    returnSmkIndikatorPenilaianAspek.setIndikatorName(smkIndikatorPenilaianAspekEntity.getIndikatorName());
                    returnSmkIndikatorPenilaianAspek.setBobot(smkIndikatorPenilaianAspekEntity.getBobot());

                    listOfResult.add(returnSmkIndikatorPenilaianAspek);
                }
            }
        }

        return listOfResult;
    }*/

    @Override
    public List<Smk> getSearch(Smk searchBean) throws GeneralBOException {
        logger.info("[SmkBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Smk> listOfResult = new ArrayList();
        String userLogin = CommonUtil.userIdLogin();
        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getNip() != null && !"".equalsIgnoreCase(searchBean.getNip())) {
                hsCriteria.put("nip", searchBean.getNip());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getPositionId() != null && !"".equalsIgnoreCase(searchBean.getPositionId())) {
                hsCriteria.put("position_id", searchBean.getPositionId());
            }
            if (searchBean.getPeriode() != null && !"".equalsIgnoreCase(searchBean.getPeriode())) {
                hsCriteria.put("periode", searchBean.getPeriode());
            }
            if (searchBean.getDivisiId() != null && !"".equalsIgnoreCase(searchBean.getDivisiId())) {
                hsCriteria.put("divisi_id", searchBean.getDivisiId());
            }

            List<ItSmkEntity> itSmkEntity = new ArrayList<>();
            String userIdLogin = CommonUtil.userIdLogin();
            String userPosisiId = CommonUtil.userPosisiId();

            List<ItSmkEntity> tmpSmk = smkDao.getListSmkByNipPeriode(userIdLogin, searchBean.getPeriode());
            if(tmpSmk.size() > 0){
                for(ItSmkEntity itSmkEntity1: tmpSmk){
                    userPosisiId = itSmkEntity1.getPositionId();
                }
            }

            try {
                getBawahanJabatan(searchBean.getBranchId(), userPosisiId);
            } catch (HibernateException e) {
                logger.error("[SmkBoImpl.getSearchSmkByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            String role = CommonUtil.roleAsLogin();
            if(role.equalsIgnoreCase("ADMIN")){
                try {
                    itSmkEntity = smkDao.getByCriteria(hsCriteria);
                } catch (HibernateException e) {
                    logger.error("[SmkBoImpl.getSearchSmkByCriteria] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }
            }else{
                if(searchBean.getPositionId().equalsIgnoreCase("")){
                    if(searchBean.getNip().equals("")){
                        if(strukturJabatanList.size() > 0){
                            itSmkEntity.addAll(smkDao.getList(userLogin, searchBean.getBranchId(), searchBean.getPeriode()));
                            for(StrukturJabatan strukturJabatan: strukturJabatanList){
                                String ada = "T";
                                for(ItSmkEntity smkEntity: itSmkEntity){
                                    if(smkEntity.getNip().equalsIgnoreCase(strukturJabatan.getNip())){
                                        ada = "Y";
                                        break;
                                    }
                                }

                                if(ada.equalsIgnoreCase("T")){
                                    itSmkEntity.addAll(smkDao.getList(strukturJabatan.getNip(), searchBean.getBranchId(), searchBean.getPeriode()));
                                }
                            }
                        }
                    }else{
                        for(StrukturJabatan strukturJabatan: strukturJabatanList){
                            if(strukturJabatan.getNip().equalsIgnoreCase(searchBean.getNip())){
                                itSmkEntity.addAll(smkDao.getListSmkByNipPeriode(searchBean.getNip(), searchBean.getPeriode()));
                                break;
                            }
                        }
                    }
                }else{
                    if(searchBean.getNip().equals("")){
                        if(strukturJabatanList.size() > 0){
                            itSmkEntity.addAll(smkDao.getList(userLogin, searchBean.getBranchId(), searchBean.getPositionId(), searchBean.getPeriode()));
                            for(StrukturJabatan strukturJabatan: strukturJabatanList){
                                String ada = "T";
                                for(ItSmkEntity smkEntity: itSmkEntity){
                                    if(smkEntity.getNip().equalsIgnoreCase(strukturJabatan.getNip())){
                                        ada = "Y";
                                        break;
                                    }
                                }

                                if(ada.equalsIgnoreCase("T")){
                                    itSmkEntity.addAll(smkDao.getList(strukturJabatan.getNip(), searchBean.getBranchId(), searchBean.getPositionId(), searchBean.getPeriode()));
                                }
                            }
                        }
                    }else{
                        for(StrukturJabatan strukturJabatan: strukturJabatanList){
                            if(strukturJabatan.getNip().equalsIgnoreCase(searchBean.getNip())){
                                itSmkEntity.addAll(smkDao.getList(searchBean.getNip(), searchBean.getBranchId(), searchBean.getPositionId(), searchBean.getPeriode()));
                                break;
                            }
                        }
                    }
                }
            }

            String idLogin = CommonUtil.userIdLogin();
            String kelompokId = "";
            List<ItPersonilPositionEntity> positionEntities = personilPositionDao.getListNip(idLogin);
            if(positionEntities != null){
                for(ItPersonilPositionEntity itPersonilPositionEntity: positionEntities){
                    if(itPersonilPositionEntity.getImPosition() != null){
                        kelompokId = itPersonilPositionEntity.getImPosition().getKelompokId();
                    }
                }
            }

            if(itSmkEntity != null){
                Smk returnSmk;
                // Looping from dao to object and save in collection
                for(ItSmkEntity smkEntity : itSmkEntity){
                    ImPosition imPosition = positionDao.getById("positionId", smkEntity.getPositionId(), "Y");
                    ImDepartmentEntity imDepartmentEntity = departmentDao.getById("departmentId", smkEntity.getDivisiId(), "Y");
                    returnSmk = new Smk();
                    returnSmk.setEvaluasiPegawaiId(smkEntity.getEvaluasiPegawaiId());
                    returnSmk.setNip(smkEntity.getNip());
                    returnSmk.setPegawaiName(smkEntity.getPegawaiName());
                    returnSmk.setPeriode(smkEntity.getPeriode());
                    returnSmk.setBranchId(smkEntity.getBranchId());
                    returnSmk.setPositionId(smkEntity.getPositionId());
                    returnSmk.setBranchName(smkEntity.getImBranches().getBranchName());
                    returnSmk.setDivisiId(smkEntity.getDivisiId());
                    returnSmk.setStatusPegawai(smkEntity.getStatusPegawai());

                    if(smkEntity.getNilaiPrestasi() == null ){
                        returnSmk.setPrintDraft("N");
                    }else{
                        returnSmk.setPrintDraft("Y");
                    }

                    if(imDepartmentEntity != null){
                        returnSmk.setDivisiName(imDepartmentEntity.getDepartmentName());
                    }else{
                        returnSmk.setDivisiName("");
                    }
                    if(imPosition != null){
                        returnSmk.setPositionName(imPosition.getPositionName());
                    }else{
                        returnSmk.setPositionName("");
                    }
                    if(smkEntity.getClosed() != null){
                        returnSmk.setSmkClosed(true);
                        returnSmk.setPrintDraft("Y");
                    }


                    if(kelompokId.equalsIgnoreCase("KL03") || kelompokId.equalsIgnoreCase("KL02") || role.equalsIgnoreCase("ADMIN")){
                        returnSmk.setEditAtasan(true);
                    }

                    listOfResult.add(returnSmk);
                }
            }
        }
        logger.info("[SmkBoImpl.getByCriteria] end process <<<");
        return listOfResult;
    }

    @Override
    public Smk getSearchEdit(Smk searchBean) throws GeneralBOException {
        ItSmkEntity smkEntity = null;

        smkTmpAll.clear();
        smkTmpAspekAll.clear();
        smkTmpAspekDetailAll.clear();
        smkTmpAspekActivityAll.clear();
        smkTmpAspekActivityPeristiwaAll.clear();
        smkTmpAspekActivityMonthlyAll.clear();

        smkSessionAspekAll.clear();
        smkSessionAspekDetailAll.clear();
        smkSessionAspekActivityAll.clear();
        smkSessionAspekActivityPeristiwaAll.clear();
        smkSessionAspekActivityMonthlyAll.clear();

        List<ItSmkEvaluasiPegawaiAspekEntity> smkSessionAspekA = null;
        List<ItSmkEvaluasiPegawaiAspekDetailEntity> smkSessionAspekDetailA = null;
        List<ItSmkEvaluasiPegawaiAspekActivityEntity> smkSessionAspekActivityA = null;
        List<ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity> smkSessionAspekActivityPersitiwaA = null;
        List<ItSmkAspekActivityMonthlyEntity> ItSmkAspekActivityMonthlyEntity = null;

        List<ItSmkEvaluasiPegawaiAspekEntity> smkSessionAspek = null;
        List<ItSmkEvaluasiPegawaiAspekDetailEntity> smkSessionAspekDetail = null;
        List<ItSmkEvaluasiPegawaiAspekActivityEntity> smkSessionAspekActivity = null;
        List<ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity> smkSessionAspekActivityPersitiwa = null;
        List<ItSmkAspekActivityMonthlyEntity> smkSessionAspekActivityMonthly = null;

        //Aspek A
        smkSessionAspekA = smkEvaluasiPegawaiAspekDao.getListNip(searchBean.getEvaluasiPegawaiId(), "A");
        smkTmpAspekAll.addAll(smkSessionAspekA);
        if(smkSessionAspekA != null){
            for(ItSmkEvaluasiPegawaiAspekEntity itSmkEvaluasiPegawaiAspekEntity: smkSessionAspekA ){
                smkSessionAspekDetailA = smkEvaluasiPegawaiAspekDetailDao.getListAspekId(itSmkEvaluasiPegawaiAspekEntity.getEvaluasiPegawaiAspekId());
                smkTmpAspekDetailAll.addAll(smkSessionAspekDetailA);
            }
            if(smkSessionAspekDetailA != null){
                for(ItSmkEvaluasiPegawaiAspekDetailEntity itSmkEvaluasiPegawaiAspekDetailEntity: smkSessionAspekDetailA){
                    smkSessionAspekActivityA = smkEvaluasiPegawaiAspekActivityDao.getListAspekActivity(itSmkEvaluasiPegawaiAspekDetailEntity.getEvaluasiPegawaiAspekDetailId());
                    smkTmpAspekActivityAll.addAll(smkSessionAspekActivityA);

                    if(smkSessionAspekActivityA != null){
                        for(ItSmkEvaluasiPegawaiAspekActivityEntity itSmkEvaluasiPegawaiAspekActivityEntity: smkSessionAspekActivityA){
                            ItSmkAspekActivityMonthlyEntity = smkAspekActivityMonthlyDao.getData(itSmkEvaluasiPegawaiAspekActivityEntity.getEvaluasiPegawaiAspekActivityId());
                            smkTmpAspekActivityMonthlyAll.addAll(ItSmkAspekActivityMonthlyEntity);
                            smkSessionAspekActivityPersitiwaA = smkEvaluasiPegawaiAspekActivityPeristiwaDao.getData(itSmkEvaluasiPegawaiAspekActivityEntity.getEvaluasiPegawaiAspekActivityId());
                            smkTmpAspekActivityPeristiwaAll.addAll(smkSessionAspekActivityPersitiwaA);
                        }
                    }
                }
            }
        }

        //Apek B
        //Hanya ada salah satu aspek B, kalau tidak Aspek B1 / aspek B2
        String aspekB = "B1";
        if(getDataIndikator(searchBean.getBranchId(), searchBean.getPositionId(), aspekB).size() == 0){
            aspekB = "B2";
        }
        smkSessionAspek = smkEvaluasiPegawaiAspekDao.getListNip(searchBean.getEvaluasiPegawaiId(), aspekB);
        smkTmpAspekAll.addAll(smkSessionAspek);
        if(smkSessionAspek != null){
            for(ItSmkEvaluasiPegawaiAspekEntity itSmkEvaluasiPegawaiAspekEntity: smkSessionAspek){
                smkSessionAspekDetail = smkEvaluasiPegawaiAspekDetailDao.getListAspekId(itSmkEvaluasiPegawaiAspekEntity.getEvaluasiPegawaiAspekId());
                smkTmpAspekDetailAll.addAll(smkSessionAspekDetail);
            }
            if(smkSessionAspekDetail != null){
                for(ItSmkEvaluasiPegawaiAspekDetailEntity itSmkEvaluasiPegawaiAspekDetailEntity: smkSessionAspekDetail){
                    smkSessionAspekActivity =
                            smkEvaluasiPegawaiAspekActivityDao.getListAspekActivity(itSmkEvaluasiPegawaiAspekDetailEntity.getEvaluasiPegawaiAspekDetailId());
                    smkTmpAspekActivityAll.addAll(smkSessionAspekActivity);

                    if(smkSessionAspekActivity != null){
                        for(ItSmkEvaluasiPegawaiAspekActivityEntity itSmkEvaluasiPegawaiAspekActivityEntity: smkSessionAspekActivity){
                            smkSessionAspekActivityMonthly = smkAspekActivityMonthlyDao.getData(itSmkEvaluasiPegawaiAspekActivityEntity.getEvaluasiPegawaiAspekActivityId());
                            smkTmpAspekActivityMonthlyAll.addAll(smkSessionAspekActivityMonthly);
                            smkSessionAspekActivityPersitiwa = smkEvaluasiPegawaiAspekActivityPeristiwaDao.getData(itSmkEvaluasiPegawaiAspekActivityEntity.getEvaluasiPegawaiAspekActivityId());
                            smkTmpAspekActivityPeristiwaAll.addAll(smkSessionAspekActivityPersitiwa);
                        }
                    }
                }

            }
        }

        smkSessionAspek = null;
        smkSessionAspekDetail = null;
        smkSessionAspekActivity = null;
        smkSessionAspekActivityPersitiwa = null;
        smkSessionAspekActivityMonthly = null;

        //Aspek C
        smkSessionAspek = smkEvaluasiPegawaiAspekDao.getListNip(searchBean.getEvaluasiPegawaiId(), "C");
        smkTmpAspekAll.addAll(smkSessionAspek);
        if(smkSessionAspek != null){
            for(ItSmkEvaluasiPegawaiAspekEntity itSmkEvaluasiPegawaiAspekEntity: smkSessionAspek){
                smkSessionAspekDetail = smkEvaluasiPegawaiAspekDetailDao.getListAspekId(itSmkEvaluasiPegawaiAspekEntity.getEvaluasiPegawaiAspekId());
                smkTmpAspekDetailAll.addAll(smkSessionAspekDetail);
            }
            if(smkSessionAspekDetail != null){
                for(ItSmkEvaluasiPegawaiAspekDetailEntity itSmkEvaluasiPegawaiAspekDetailEntity: smkSessionAspekDetail){
                    smkSessionAspekActivity =
                            smkEvaluasiPegawaiAspekActivityDao.getListAspekActivity(itSmkEvaluasiPegawaiAspekDetailEntity.getEvaluasiPegawaiAspekDetailId());
                    smkTmpAspekActivityAll.addAll(smkSessionAspekActivity);

                    if(smkSessionAspekActivity != null){
                        for(ItSmkEvaluasiPegawaiAspekActivityEntity itSmkEvaluasiPegawaiAspekActivityEntity: smkSessionAspekActivity){
                            smkSessionAspekActivityMonthly = smkAspekActivityMonthlyDao.getData(itSmkEvaluasiPegawaiAspekActivityEntity.getEvaluasiPegawaiAspekActivityId());
                            smkTmpAspekActivityMonthlyAll.addAll(smkSessionAspekActivityMonthly);
                            smkSessionAspekActivityPersitiwa = smkEvaluasiPegawaiAspekActivityPeristiwaDao.getData(itSmkEvaluasiPegawaiAspekActivityEntity.getEvaluasiPegawaiAspekActivityId());
                            smkTmpAspekActivityPeristiwaAll.addAll(smkSessionAspekActivityPersitiwa);
                        }
                    }
                }

            }
        }

        try {
            // Get data from database by ID
            smkEntity = smkDao.getById("evaluasiPegawaiId", searchBean.getEvaluasiPegawaiId());
            if(!searchBean.getNip().equalsIgnoreCase("US")){
                // karena SMK 2018 masih menggunakan SMK Manual
                if(!searchBean.getPeriode().equalsIgnoreCase("2018")){
                    smkAspekActivityMonthlyDao.updateNilaiPoint(searchBean.getEvaluasiPegawaiId());
                }
            }
            smkTmpAll.add(smkEntity);
        } catch (HibernateException e) {
            logger.error("[SmkBoImpl.saveDelete] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
        }

        Smk returnSmk = new Smk();
        String kelompokId = "";
        if (smkEntity != null) {
            if(smkEntity.getClosed() != null){
                returnSmk.setClosed(smkEntity.getClosed());

                String idLogin = CommonUtil.userIdLogin();
                List<ItPersonilPositionEntity> positionEntities = personilPositionDao.getListNip(idLogin);
                if(positionEntities != null){
                    for(ItPersonilPositionEntity itPersonilPositionEntity: positionEntities){
                        if(itPersonilPositionEntity.getImPosition() != null){
                            kelompokId = itPersonilPositionEntity.getImPosition().getKelompokId();
                        }
                    }
                }
                String role = CommonUtil.roleAsLogin();
                if(kelompokId.equalsIgnoreCase("KL03") || kelompokId.equalsIgnoreCase("KL02") || role.equalsIgnoreCase("ADMIN")){
                    returnSmk.setEditNilai(true);
                }else{
                    returnSmk.setEditNilai(false);
                }
            }else{
                returnSmk.setEditNilai(true);
            }

            String userLogin = CommonUtil.userIdLogin();
            if(!userLogin.equalsIgnoreCase(smkEntity.getNip())){
                returnSmk.setEditTarget(true);
            }else{
                returnSmk.setEditTarget(false);
            }

            returnSmk.setEvaluasiPegawaiId(smkEntity.getEvaluasiPegawaiId());
            returnSmk.setNip(smkEntity.getNip());
            returnSmk.setPegawaiName(smkEntity.getPegawaiName());
            returnSmk.setPeriode(smkEntity.getPeriode());
            returnSmk.setBranchId(smkEntity.getBranchId());
            returnSmk.setBranchName(smkEntity.getImBranches().getBranchName());
            returnSmk.setDivisiId(smkEntity.getDivisiId());
            if(!smkEntity.getDivisiId().equalsIgnoreCase("")){
                returnSmk.setDivisiName(smkEntity.getImDepartmentEntity().getDepartmentName());
            }else{
                returnSmk.setDivisiName("");
            }
            returnSmk.setPositionId(smkEntity.getPositionId());
            if(smkEntity.getImPosition() != null && !"".equalsIgnoreCase(smkEntity.getPositionId())&& !"-".equalsIgnoreCase(smkEntity.getPositionId())){
                returnSmk.setPositionName(smkEntity.getImPosition().getPositionName());
                returnSmk.setKelompokId(smkEntity.getImPosition().getKelompokId());
            }else{
                returnSmk.setKelompokId("");
                returnSmk.setPositionName("");
            }

            returnSmk.setGolonganId(smkEntity.getGolonganId());
            returnSmk.setTipePegawaiId(smkEntity.getTipePegawaiId());
            returnSmk.setUnitUsaha(smkEntity.getGrandTotalNilaiPrestasi());
            returnSmk.setStatusPegawai(smkEntity.getStatusPegawai());
            returnSmk.setPoin(smkEntity.getPoin());
            returnSmk.setPointBaru(smkEntity.getPointnew());

            String masaKerjaGolongan = masaKerjaGolongan(smkEntity.getNip(), smkEntity.getGolonganId(), Integer.parseInt(smkEntity.getPeriode()));
            returnSmk.setStrMasaKerjaGolongan(masaKerjaGolongan);
        }

        smkSessionAll.addAll(smkTmpAll);
        smkSessionAspekAll.addAll(smkTmpAspekAll);
        smkSessionAspekDetailAll.addAll(smkTmpAspekDetailAll);
        smkSessionAspekActivityAll.addAll(smkTmpAspekActivityAll);
        smkSessionAspekActivityPeristiwaAll.addAll(smkTmpAspekActivityPeristiwaAll);
        smkSessionAspekActivityMonthlyAll.addAll(smkTmpAspekActivityMonthlyAll);

        return returnSmk;
    }

    @Override
    public void cancelSmkSys() throws GeneralBOException {
        for(ItSmkEntity itSmk: smkTmpAll){
            ItSmkEntity itSmkEntity = smkDao.getById("evaluasiPegawaiId", itSmk.getEvaluasiPegawaiId(), "Y");
            if(itSmkEntity != null){
                itSmkEntity.setPoin(itSmk.getPoin());
                itSmkEntity.setPointnew(itSmk.getPointnew());
                itSmkEntity.setNilaiPrestasi(itSmk.getNilaiPrestasi());
                itSmkEntity.setGrandTotalNilaiPrestasi(itSmk.getGrandTotalNilaiPrestasi());
                itSmkEntity.setGrandTotalNilaiPrestasiRevisi(itSmk.getGrandTotalNilaiPrestasiRevisi());
                smkDao.updateAndSave(itSmkEntity);
            }
        }

        for(ItSmkEvaluasiPegawaiAspekEntity itSmkEvaluasiPegawaiAspekEntity: smkTmpAspekAll){
            ItSmkEvaluasiPegawaiAspekEntity itAspek = smkEvaluasiPegawaiAspekDao.getById("evaluasiPegawaiAspekId", itSmkEvaluasiPegawaiAspekEntity.getEvaluasiPegawaiAspekId(), "Y");
            if(itAspek != null){
                itAspek.setPointPrestasi(itSmkEvaluasiPegawaiAspekEntity.getPointPrestasi());
                smkEvaluasiPegawaiAspekDao.updateAndSave(itAspek);
            }
        }

        for(ItSmkEvaluasiPegawaiAspekDetailEntity itSmkEvaluasiPegawaiAspekDetailEntity: smkTmpAspekDetailAll){
            ItSmkEvaluasiPegawaiAspekDetailEntity itAspekDetail = smkEvaluasiPegawaiAspekDetailDao.getById("evaluasiPegawaiAspekDetailId",
                    itSmkEvaluasiPegawaiAspekDetailEntity.getEvaluasiPegawaiAspekDetailId(), "Y");
            if(itAspekDetail != null){
                itAspekDetail.setTarget(itSmkEvaluasiPegawaiAspekDetailEntity.getTarget());
                itAspekDetail.setRealisasi(itSmkEvaluasiPegawaiAspekDetailEntity.getRealisasi());
                itAspekDetail.setNilai(itSmkEvaluasiPegawaiAspekDetailEntity.getNilai());
                itAspekDetail.setNilaiPrestasi(itSmkEvaluasiPegawaiAspekDetailEntity.getNilaiPrestasi());
                smkEvaluasiPegawaiAspekDetailDao.updateAndSave(itAspekDetail);
            }
        }

        for(ItSmkEvaluasiPegawaiAspekActivityEntity itSmkEvaluasiPegawaiAspekActivityEntity: smkTmpAspekActivityAll){
            ItSmkEvaluasiPegawaiAspekActivityEntity itAspekActivity = smkEvaluasiPegawaiAspekActivityDao.getById("evaluasiPegawaiAspekActivityId",
                    itSmkEvaluasiPegawaiAspekActivityEntity.getEvaluasiPegawaiAspekActivityId(), "Y");
            if(itAspekActivity != null){
                itAspekActivity.setNilaiActivity(itSmkEvaluasiPegawaiAspekActivityEntity.getNilaiActivity());
                itAspekActivity.setRataRata(itSmkEvaluasiPegawaiAspekActivityEntity.getRataRata());
                smkEvaluasiPegawaiAspekActivityDao.updateAndSave(itAspekActivity);
            }
        }

        for(ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity: smkTmpAspekActivityPeristiwaAll){
            ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity itAspekActivityPeristiwa = smkEvaluasiPegawaiAspekActivityPeristiwaDao.getById("evaluasiActivityPeristiwaId",
                    itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getEvaluasiActivityPeristiwaId(), "Y");
            if(itAspekActivityPeristiwa!= null){
                itAspekActivityPeristiwa.setAspekActivityMonthlyId(itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getAspekActivityMonthlyId());
                itAspekActivityPeristiwa.setTanggalKejadian(itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getTanggalKejadian());
                itAspekActivityPeristiwa.setKejadian(itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getKejadian());
                smkEvaluasiPegawaiAspekActivityPeristiwaDao.updateAndSave(itAspekActivityPeristiwa);
            }
        }

        for(ItSmkAspekActivityMonthlyEntity itSmkMonthly: smkTmpAspekActivityMonthlyAll){
            ItSmkAspekActivityMonthlyEntity itAspekActivityMonthly = smkAspekActivityMonthlyDao.getById("aspekActivityMonthly",
                    itSmkMonthly.getAspekActivityMonthly(), "Y");
            if(itAspekActivityMonthly!= null){
                itAspekActivityMonthly.setNilai(itSmkMonthly.getNilai());
                smkAspekActivityMonthlyDao.updateAndSave(itAspekActivityMonthly);
            }
        }
    }

    @Override
    public List<Smk> getEditAspekA(Smk searchBean) throws GeneralBOException {
        List<Smk> listOfResult = new ArrayList();

        List<SmkJabatanDetail> smkJabatanDetailList = null;

        if (!searchBean.getPeriode().equalsIgnoreCase("") || !searchBean.getNip().equalsIgnoreCase("")) {
            List<ItSmkEntity> itSmkEntities = null;
            try {
                itSmkEntities = smkDao.getDataAspekA(searchBean.getPeriode(), searchBean.getNip(), "A");
                smkJabatanDetailList = cekJabatan(searchBean.getBranchId(), searchBean.getPositionId());
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.getSearchSmkJabatanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itSmkEntities != null){
                Smk smk;
                // Looping from dao to object and save in collection
                for(ItSmkEntity itSmkEntity : itSmkEntities){
                    smk = new Smk();
                    smk.setNip(itSmkEntity.getNip());
                    smk.setPegawaiName(itSmkEntity.getPegawaiName());
                    smk.setPositionId(itSmkEntity.getPositionId());
                    smk.setDivisiId(itSmkEntity.getDivisiId());
                    smk.setBranchId(itSmkEntity.getBranchId());
                    smk.setGolonganId(itSmkEntity.getGolonganId());
                    smk.setPeriode(itSmkEntity.getPeriode());
                    smk.setJabatanSmkId(itSmkEntity.getJabatanSmkId());
                    smk.setJumlahBobot(itSmkEntity.getJumlahBobot());
                    smk.setEvaluasiPegawaiAspekDetailId(itSmkEntity.getEvaluasiPegawaiAspekDetailId());
                    smk.setIndikatorKinerja(itSmkEntity.getIndikatorKinerja());
                    if(smkJabatanDetailList != null){
                        smk.setIndikatorKinerjaAtasan("N");
                        for(SmkJabatanDetail smkJabatanDetail : smkJabatanDetailList){
                            if(itSmkEntity.getIndikatorKinerja().equalsIgnoreCase(smkJabatanDetail.getIndikatorKinerja())){
                                smk.setIndikatorKinerjaAtasan("Y");
                                break;
                            }else{
                                smk.setIndikatorKinerjaAtasan("N");
                            }
                        }
                    }

                    smk.setSubAspekA(itSmkEntity.getSubAspekA());
                    smk.setBobot(itSmkEntity.getBobot());
                    smk.setPointPrestasi(itSmkEntity.getPointPrestasi());
                    smk.setTarget(itSmkEntity.getTarget());
                    smk.setRealisasi(itSmkEntity.getRealisasi());
                    smk.setNilai(itSmkEntity.getNilai());
                    smk.setNilaiPrestasiItem(itSmkEntity.getNilaiPrestasiItem());
                    smk.setEvaluasiPegawaiAspekId(itSmkEntity.getEvaluasiPegawaiAspekId());
                    listOfResult.add(smk);
                }
            }
        }

        return listOfResult;
    }

    public List<SmkJabatanDetail> cekJabatan(String branchId, String id) throws GeneralBOException {
        List<ImStrukturJabatanEntity>strukturJabatanEntityList= new ArrayList();
        List<SmkJabatanDetail> smkJabatanDetailList = new ArrayList();
        HttpSession session = ServletActionContext.getRequest().getSession();

        strukturJabatanEntityList = strukturJabatanDao.getParent(id, branchId);
        if(strukturJabatanEntityList.size() > 0){
            String parentStrukturId = "";
            String kelompokId = "";
            for(ImStrukturJabatanEntity strukturJabatanEntity : strukturJabatanEntityList){
                parentStrukturId = strukturJabatanEntity.getParentId();
                kelompokId = strukturJabatanEntity.getKelompokId();
            }
            SmkJabatanDetail smkJabatanDetail = new SmkJabatanDetail();

            for(StrukturJabatan strukturJabatan : getAtasanStruktur(parentStrukturId)){
                smkJabatanDetail = new SmkJabatanDetail();
                String label[] = strukturJabatan.getPositionName().split(" ");
                smkJabatanDetail.setNip(strukturJabatan.getNip());
                smkJabatanDetail.setJabatanSmkDetailId("-");
                if(strukturJabatan.getPositionKelompokId().equalsIgnoreCase("KL01")){
                    smkJabatanDetail.setIndikatorKinerja("Unit Usaha");
                }else{
                    smkJabatanDetail.setIndikatorKinerja(label[1]);
                }
                smkJabatanDetail.setBobot(BigDecimal.valueOf(5));
                smkJabatanDetail.setSubAspekA("");
                smkJabatanDetail.setSubAspekANama("");

                smkJabatanDetailList.add(smkJabatanDetail);
            }
        }
        return smkJabatanDetailList;
    }
    private List<StrukturJabatan>getAtasanStruktur(String parentId){
        List<ImStrukturJabatanEntity> imStrukturJabatanEntity = null;
        List<StrukturJabatan> strukturJabatanList = new ArrayList();
        String kelompok = "";
        String kelompok2 = "Y";

        if(!parentId.equalsIgnoreCase("-")){
            while(!kelompok.equalsIgnoreCase("KL01") && kelompok2.equalsIgnoreCase("Y")){
                imStrukturJabatanEntity = null;
                imStrukturJabatanEntity = strukturJabatanDao.getTambahanAspekA(parentId);
                if(imStrukturJabatanEntity != null){
                    for(ImStrukturJabatanEntity imStrukturJabatanEntity1 : imStrukturJabatanEntity){
                        parentId = imStrukturJabatanEntity1.getParentId();
                        kelompok = imStrukturJabatanEntity1.getKelompokId() ;
                        if(parentId.equalsIgnoreCase("-")){
                            kelompok2 = "N";
                        }

                        StrukturJabatan strukturJabatan = new StrukturJabatan();
                        strukturJabatan.setParentId(imStrukturJabatanEntity1.getParentId());
                        strukturJabatan.setNip(imStrukturJabatanEntity1.getNip());
                        strukturJabatan.setPositionId(imStrukturJabatanEntity1.getPositionId());
                        strukturJabatan.setPositionName(imStrukturJabatanEntity1.getPositionName());
                        strukturJabatan.setPositionKelompokId(imStrukturJabatanEntity1.getKelompokId());
                        strukturJabatan.setPositionKelompokName(imStrukturJabatanEntity1.getKelompokName());
                        strukturJabatanList.add(strukturJabatan);
                    }
                }

            }
        }

        return strukturJabatanList;
    }

    @Override
    public List<Smk> getEditAspek(Smk searchBean) throws GeneralBOException {
        List<Smk> listOfResult = new ArrayList();

        if (!searchBean.getPeriode().equalsIgnoreCase("") || !searchBean.getNip().equalsIgnoreCase("")) {
            List<ItSmkEntity> itSmkEntities = null;
            try {
                itSmkEntities = smkDao.getDataAspekA(searchBean.getPeriode(), searchBean.getNip(), searchBean.getTipeAspekId());
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.getSearchSmkJabatanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itSmkEntities != null){
                Smk smk;
                // Looping from dao to object and save in collection
                for(ItSmkEntity itSmkEntity : itSmkEntities){
                    smk = new Smk();
                    smk.setNip(itSmkEntity.getNip());
                    smk.setPegawaiName(itSmkEntity.getPegawaiName());
                    smk.setPositionId(itSmkEntity.getPositionId());
                    smk.setDivisiId(itSmkEntity.getDivisiId());
                    smk.setBranchId(itSmkEntity.getBranchId());
                    smk.setGolonganId(itSmkEntity.getGolonganId());
                    smk.setPeriode(itSmkEntity.getPeriode());
                    smk.setIndikatorKinerja(itSmkEntity.getIndikatorKinerja());
                    smk.setEvaluasiPegawaiAspekDetailId(itSmkEntity.getEvaluasiPegawaiAspekDetailId());
                    smk.setBobot(itSmkEntity.getBobot());
                    smk.setNilai(itSmkEntity.getNilai());
                    smk.setNilaiPrestasiItem(itSmkEntity.getNilaiPrestasiItem());
                    smk.setEvaluasiPegawaiAspekId(itSmkEntity.getEvaluasiPegawaiAspekId());

                    listOfResult.add(smk);
                }
            }
        }
        return listOfResult;
    }

    @Override
    public Smk getAspekDetail(Smk searchBean) throws GeneralBOException {
        Smk listOfResult = null;
        Smk smk = new Smk();
        if (!searchBean.getEvaluasiPegawaiAspekDetailId().equalsIgnoreCase("") || searchBean.getEvaluasiPegawaiAspekDetailId() != null) {
            List<ItSmkEntity> itSmkEntities = null;
            try {
                itSmkEntities = smkDao.getDataAspekDetail(searchBean.getEvaluasiPegawaiAspekDetailId());
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.getSearchSmkJabatanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itSmkEntities != null){
                String userLogin = CommonUtil.userIdLogin();
                for(ItSmkEntity itSmkEntity : itSmkEntities){
                    if(!userLogin.equalsIgnoreCase(itSmkEntity.getNip())){
                        smk.setEditNilai(true);
                    }

                    smk.setNip(itSmkEntity.getNip());
                    smk.setPegawaiName(itSmkEntity.getPegawaiName());
                    smk.setPositionId(itSmkEntity.getPositionId());
                    smk.setDivisiId(itSmkEntity.getDivisiId());
                    smk.setBranchId(itSmkEntity.getBranchId());
                    smk.setGolonganId(itSmkEntity.getGolonganId());
                    smk.setPeriode(itSmkEntity.getPeriode());
                    smk.setIndikatorKinerja(itSmkEntity.getIndikatorKinerja());
                    smk.setEvaluasiPegawaiAspekDetailId(itSmkEntity.getEvaluasiPegawaiAspekDetailId());
                    smk.setBobot(itSmkEntity.getBobot());
                    smk.setNilai(itSmkEntity.getNilai());
                    smk.setNilaiPrestasiItem(itSmkEntity.getNilaiPrestasiItem());
                    if(itSmkEntity.getClosed().equalsIgnoreCase("B")){
                        smk.setEditRealisasi(true);
                    }
                }
            }
        }
        return smk;
    }

    @Override
    public List<SmkAspekActivityMonthly> getAspekDetailMonthly(String aspekDetailId) throws GeneralBOException {
        List<SmkAspekActivityMonthly> listOfResult = new ArrayList();
        if (!aspekDetailId.equalsIgnoreCase("")) {
            List<ItSmkAspekActivityMonthlyEntity> itSmkAspekActivityMonthlyEntities = null;
            try {
                itSmkAspekActivityMonthlyEntities = smkAspekActivityMonthlyDao.getDataDetail(aspekDetailId);
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.getSearchSmkJabatanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itSmkAspekActivityMonthlyEntities != null){
                // Looping from dao to object and save in collection
                for(ItSmkAspekActivityMonthlyEntity itSmkAspekActivityMonthlyEntity : itSmkAspekActivityMonthlyEntities){
                    SmkAspekActivityMonthly smkAspekActivityMonthly = new SmkAspekActivityMonthly();
                    smkAspekActivityMonthly.setAspekActivityMonthly(itSmkAspekActivityMonthlyEntity.getAspekActivityMonthly());
                    smkAspekActivityMonthly.setEvaluasiPegawaiAspekId(itSmkAspekActivityMonthlyEntity.getEvaluasiPegawaiAspekId());
                    smkAspekActivityMonthly.setBulan(itSmkAspekActivityMonthlyEntity.getBulan());
                    smkAspekActivityMonthly.setNilai(itSmkAspekActivityMonthlyEntity.getNilai());
                    smkAspekActivityMonthly.setRataRata(itSmkAspekActivityMonthlyEntity.getRataRata());

                    listOfResult.add(smkAspekActivityMonthly);
                }
            }
        }
        return listOfResult;
    }

    @Override
    public List<SmkEvaluasiPegawaiAspekActivityPeristiwa> aspekPeristiwaSys(String activityId) throws GeneralBOException {
        List<SmkEvaluasiPegawaiAspekActivityPeristiwa> listOfResult = new ArrayList();
        if (!activityId.equalsIgnoreCase("")) {
            List<ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity> ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity = null;
            try {
                ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity = smkEvaluasiPegawaiAspekActivityPeristiwaDao.getDataPeristiwa(activityId);
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.getSearchSmkJabatanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity != null){
                // Looping from dao to object and save in collection
                for(ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity : ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity){
                    SmkEvaluasiPegawaiAspekActivityPeristiwa smkEvaluasiPegawaiAspekActivityPeristiwa = new SmkEvaluasiPegawaiAspekActivityPeristiwa();
                    smkEvaluasiPegawaiAspekActivityPeristiwa.setEvaluasiActivityPeristiwaId(itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getEvaluasiActivityPeristiwaId());
                    smkEvaluasiPegawaiAspekActivityPeristiwa.setAspekActivityMonthlyId(itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getAspekActivityMonthlyId());
                    smkEvaluasiPegawaiAspekActivityPeristiwa.setTanggalKejadian(itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getTanggalKejadian());
                    smkEvaluasiPegawaiAspekActivityPeristiwa.setStTanggalKejadian(CommonUtil.convertDateToString(itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getTanggalKejadian()));
                    smkEvaluasiPegawaiAspekActivityPeristiwa.setKejadian(itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getKejadian());

                    listOfResult.add(smkEvaluasiPegawaiAspekActivityPeristiwa);
                }
            }
        }
        return listOfResult;
    }

    @Override
    public List<SmkEvaluasiPegawaiAspekActivityPeristiwa> getAspekDetailPeristiwa(String monthlyId) throws GeneralBOException {
        List<SmkEvaluasiPegawaiAspekActivityPeristiwa> listOfResult = new ArrayList();
        if (!monthlyId.equalsIgnoreCase("")) {
            List<ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity> ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity = null;
            try {
                ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity = smkEvaluasiPegawaiAspekActivityPeristiwaDao.getDataMonthly(monthlyId);
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.getSearchSmkJabatanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity != null){
                // Looping from dao to object and save in collection
                for(ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity : ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity){
                    SmkEvaluasiPegawaiAspekActivityPeristiwa smkEvaluasiPegawaiAspekActivityPeristiwa = new SmkEvaluasiPegawaiAspekActivityPeristiwa();
                    smkEvaluasiPegawaiAspekActivityPeristiwa.setEvaluasiActivityPeristiwaId(itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getEvaluasiActivityPeristiwaId());
                    smkEvaluasiPegawaiAspekActivityPeristiwa.setAspekActivityMonthlyId(itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getAspekActivityMonthlyId());
                    smkEvaluasiPegawaiAspekActivityPeristiwa.setTanggalKejadian(itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getTanggalKejadian());
                    smkEvaluasiPegawaiAspekActivityPeristiwa.setStTanggalKejadian(CommonUtil.convertDateToString(itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getTanggalKejadian()));
                    smkEvaluasiPegawaiAspekActivityPeristiwa.setKejadian(itSmkEvaluasiPegawaiAspekActivityPeristiwaEntity.getKejadian());

                    listOfResult.add(smkEvaluasiPegawaiAspekActivityPeristiwa);
                }
            }
        }
        return listOfResult;
    }

    @Override
    public void saveAddPeristiwa(SmkEvaluasiPegawaiAspekActivityPeristiwa bean) throws GeneralBOException {
        if (bean!=null) {
            String id;
            try {
                id = getSmkEvaluasiPegawaiAspekActivityPeristiwaDao().getNextSmkAspekActivityMonthlyPeristiwaId();
            } catch (HibernateException e) {
                logger.error("[SmkBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence smkId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity itPeristiwa = new ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity();

            itPeristiwa.setEvaluasiActivityPeristiwaId(id);
            itPeristiwa.setAspekActivityMonthlyId(bean.getAspekActivityMonthlyId());
            itPeristiwa.setTanggalKejadian(bean.getTanggalKejadian());
            itPeristiwa.setKejadian(bean.getKejadian());

            itPeristiwa.setFlag(bean.getFlag());
            itPeristiwa.setAction(bean.getAction());
            itPeristiwa.setCreatedWho(bean.getCreatedWho());
            itPeristiwa.setLastUpdateWho(bean.getLastUpdateWho());
            itPeristiwa.setCreatedDate(bean.getCreatedDate());
            itPeristiwa.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                smkEvaluasiPegawaiAspekActivityPeristiwaDao.addAndSave(itPeristiwa);
            } catch (HibernateException e) {
                logger.error("[SmkBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Smk, please info to your admin..." + e.getMessage());
            }
        }
    }

    @Override
    public void saveEditPeristiwa(SmkEvaluasiPegawaiAspekActivityPeristiwa bean) throws GeneralBOException {
        ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity listPeristiwa = null;
        if (bean!=null) {
            String id;
            try {
                listPeristiwa = smkEvaluasiPegawaiAspekActivityPeristiwaDao.getById("evaluasiActivityPeristiwaId", bean.getEvaluasiActivityPeristiwaId(), "Y");
            } catch (HibernateException e) {
                logger.error("[SmkBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence smkId id, please info to your admin..." + e.getMessage());
            }

            listPeristiwa.setTanggalKejadian(bean.getTanggalKejadian());
            listPeristiwa.setKejadian(bean.getKejadian());

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            listPeristiwa.setAction("U");
            listPeristiwa.setCreatedWho(userLogin);
            listPeristiwa.setLastUpdateWho(userLogin);
            listPeristiwa.setCreatedDate(updateTime);
            listPeristiwa.setLastUpdate(updateTime);

            try {
                // insert into database
                smkEvaluasiPegawaiAspekActivityPeristiwaDao.updateAndSave(listPeristiwa);
            } catch (HibernateException e) {
                logger.error("[SmkBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Smk, please info to your admin..." + e.getMessage());
            }
        }
    }

    @Override
    public SmkEvaluasiPegawaiAspekActivityPeristiwa getItemPeristiwaSys(String idPeristiwa) throws GeneralBOException {
        ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity peristiwa = null;
        SmkEvaluasiPegawaiAspekActivityPeristiwa smkPeristiwa = new SmkEvaluasiPegawaiAspekActivityPeristiwa();
        peristiwa = smkEvaluasiPegawaiAspekActivityPeristiwaDao.getById("evaluasiActivityPeristiwaId", idPeristiwa, "Y");

        smkPeristiwa.setAspekActivityMonthlyId(peristiwa.getAspekActivityMonthlyId());
        smkPeristiwa.setStTanggalKejadian(CommonUtil.convertDateToString(peristiwa.getTanggalKejadian()));
        smkPeristiwa.setKejadian(peristiwa.getKejadian());

        return smkPeristiwa;
    }

    @Override
    public void saveDeletePeristiwaSys(String idPeristiwa) throws GeneralBOException {
        ItSmkEvaluasiPegawaiAspekActivityPeristiwaEntity peristiwa = null;
        SmkEvaluasiPegawaiAspekActivityPeristiwa smkPeristiwa = new SmkEvaluasiPegawaiAspekActivityPeristiwa();
        peristiwa = smkEvaluasiPegawaiAspekActivityPeristiwaDao.getById("evaluasiActivityPeristiwaId", idPeristiwa, "Y");

        peristiwa.setFlag("N");

        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        peristiwa.setAction("D");
        peristiwa.setCreatedWho(userLogin);
        peristiwa.setLastUpdateWho(userLogin);
        peristiwa.setCreatedDate(updateTime);
        peristiwa.setLastUpdate(updateTime);
        smkEvaluasiPegawaiAspekActivityPeristiwaDao.updateAndSave(peristiwa);
    }

    @Override
    public Smk getAspekADetail(Smk searchBean) throws GeneralBOException {
        Smk listOfResult = null;
        Smk smk = new Smk();
        if (!searchBean.getEvaluasiPegawaiAspekDetailId().equalsIgnoreCase("") || searchBean.getEvaluasiPegawaiAspekDetailId() != null) {
            List<ItSmkEntity> itSmkEntities = null;
            try {
                itSmkEntities = smkDao.getDataAspekADetail(searchBean.getEvaluasiPegawaiAspekDetailId());
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.getSearchSmkJabatanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itSmkEntities != null){
                // Looping from dao to object and save in collection
                for(ItSmkEntity itSmkEntity : itSmkEntities){
                    String userLogin = CommonUtil.userIdLogin();
                    if(!userLogin.equalsIgnoreCase(itSmkEntity.getNip())){
                        smk.setEditNilai(true);
                        smk.setEditTarget(true);
                        //smk.setEditRealisasi(true);
                    }

                    smk.setNip(itSmkEntity.getNip());
                    smk.setPegawaiName(itSmkEntity.getPegawaiName());
                    smk.setPositionId(itSmkEntity.getPositionId());
                    smk.setDivisiId(itSmkEntity.getDivisiId());
                    smk.setBranchId(itSmkEntity.getBranchId());
                    smk.setGolonganId(itSmkEntity.getGolonganId());
                    smk.setPeriode(itSmkEntity.getPeriode());
                    smk.setIndikatorKinerja(itSmkEntity.getIndikatorKinerja());
                    smk.setEvaluasiPegawaiAspekDetailId(itSmkEntity.getEvaluasiPegawaiAspekDetailId());
                    smk.setBobot(itSmkEntity.getBobot());
                    smk.setNilai(itSmkEntity.getNilai());
                    smk.setTarget(itSmkEntity.getTarget());
                    smk.setRealisasi(itSmkEntity.getRealisasi());
                    smk.setSubAspekA(itSmkEntity.getSubAspekA());
                    if(itSmkEntity.getClosed().equalsIgnoreCase("B")){
                        smk.setEditRealisasi(true);
                    }
                }
            }
        }
        return smk;
    }

    @Override
    public void saveEditMonthly(SmkAspekActivityMonthly bean) throws GeneralBOException {
        logger.info("[SmkBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String smkId = bean.getAspekActivityMonthly();

            ItSmkAspekActivityMonthlyEntity itMonthly = null;
            try {
                // Get data from database by ID
                itMonthly = smkAspekActivityMonthlyDao.getById("aspekActivityMonthly", smkId, "Y");
            } catch (HibernateException e) {
                logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
            }

            if (itMonthly != null) {
                itMonthly.setNilai(bean.getNilai());
                itMonthly.setLastUpdateWho(bean.getLastUpdateWho());
                itMonthly.setLastUpdate(bean.getLastUpdate());

                try {
                    smkAspekActivityMonthlyDao.updateAndSave(itMonthly);
                    smkAspekActivityMonthlyDao.getDataRata(bean.getEvaluasiPegawaiAspekId());
                } catch (HibernateException e) {
                    logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Department, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SmkBoImpl.saveEdit] Error, not found data Department with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Department with request id, please check again your data ...");
            }

        }
        logger.info("[SmkBoImpl.saveEdit] end process <<<");
    }

    @Override
    public void saveUpdateRataRata(SmkAspekActivityMonthly bean) throws GeneralBOException {
        try {
            smkAspekActivityMonthlyDao.getDataRata(bean.getEvaluasiPegawaiAspekId());
            smkAspekActivityMonthlyDao.updateNilai(bean.getIdAspekDetail(), bean.getEvaluasiPegawaiAspekId());
            smkAspekActivityMonthlyDao.updateNilaiPrestasi(bean.getIdAspekDetail(), bean.getEvaluasiPegawaiAspekId(), bean.getBobot());
        } catch (HibernateException e) {
            logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when saving update data Department, please info to your admin..." + e.getMessage());
        }
    }

    @Override
    public void saveEditAspekDetail(Smk bean) throws GeneralBOException {
        logger.info("[SmkBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String id = bean.getEvaluasiPegawaiAspekDetailId();
            double total = (Double.valueOf(bean.getNilai()) * Double.valueOf(bean.getBobot())) / 100 ;
            ItSmkEvaluasiPegawaiAspekDetailEntity itDetail = null;
            try {
                // Get data from database by ID
                itDetail = smkEvaluasiPegawaiAspekDetailDao.getById("evaluasiPegawaiAspekDetailId", id, "Y");
                smkEvaluasiPegawaiAspekDetailDao.updateRatarata(id);
                smkEvaluasiPegawaiAspekDetailDao.updateNilaiPrestasi(id, total);
            } catch (HibernateException e) {
                logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
            }

            if (itDetail != null) {
                itDetail.setNilai(BigDecimal.valueOf(bean.getNilai()));
                itDetail.setLastUpdateWho(bean.getLastUpdateWho());
                itDetail.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    smkEvaluasiPegawaiAspekDetailDao.updateAndSave(itDetail);
                } catch (HibernateException e) {
                    logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Department, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SmkBoImpl.saveEdit] Error, not found data Department with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Department with request id, please check again your data ...");
//                condition = "Error, not found data Department with request id, please check again your data ...";
            }

        }
        logger.info("[SmkBoImpl.saveEdit] end process <<<");
    }

    @Override
    public void saveEditAspekADetail(Smk bean) throws GeneralBOException {
        logger.info("[SmkBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String id = bean.getEvaluasiPegawaiAspekDetailId();
            double total = (Double.valueOf(bean.getNilai()) * Double.valueOf(bean.getBobot())) / 100 ;
            ItSmkEvaluasiPegawaiAspekDetailEntity itDetail = null;
            try {
                // Get data from database by ID
                itDetail = smkEvaluasiPegawaiAspekDetailDao.getById("evaluasiPegawaiAspekDetailId", id, "Y");
                smkEvaluasiPegawaiAspekDetailDao.updateRatarata(id);
                smkEvaluasiPegawaiAspekDetailDao.updateNilaiPrestasi(id, total);
            } catch (HibernateException e) {
                logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
            }

            if (itDetail != null) {
                itDetail.setNilai(BigDecimal.valueOf(bean.getNilai()));
                itDetail.setTarget(bean.getTarget());
                itDetail.setRealisasi(bean.getRealisasi());
                itDetail.setLastUpdateWho(bean.getLastUpdateWho());
                itDetail.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    smkEvaluasiPegawaiAspekDetailDao.updateAndSave(itDetail);
                } catch (HibernateException e) {
                    logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Department, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SmkBoImpl.saveEdit] Error, not found data Department with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Department with request id, please check again your data ...");
//                condition = "Error, not found data Department with request id, please check again your data ...";
            }

        }
        logger.info("[SmkBoImpl.saveEdit] end process <<<");
    }

    @Override
    public List<Smk> getAspekDetailMonthlyCheckList(String aspekDetailId) throws GeneralBOException {
        List<Smk> listOfResult = new ArrayList();
        if (!aspekDetailId.equalsIgnoreCase("")) {
            List<ItSmkEntity> itSmkEntities = null;
            try {
                itSmkEntities = smkAspekActivityMonthlyDao.getDataDetailCheckList(aspekDetailId);
            } catch (HibernateException e) {
                logger.error("[SmkJabatanBoImpl.getSearchSmkJabatanByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itSmkEntities != null){
                // Looping from dao to object and save in collection
                for(ItSmkEntity itSmkEntity : itSmkEntities){
                    Smk smk = new Smk();
                    smk.setEvaluasiPegawaiAspekDetailId(itSmkEntity.getEvaluasiPegawaiAspekDetailId());
                    smk.setIndikatorKinerja(itSmkEntity.getIndikatorKinerja());
                    smk.setCheckList(itSmkEntity.getCheckList());
                    smk.setActivityId(itSmkEntity.getActivityId());
                    smk.setJan(itSmkEntity.getJan());
                    smk.setFeb(itSmkEntity.getFeb());
                    smk.setMar(itSmkEntity.getMar());
                    smk.setApr(itSmkEntity.getApr());
                    smk.setMei(itSmkEntity.getMei());
                    smk.setJun(itSmkEntity.getJun());
                    smk.setJul(itSmkEntity.getJul());
                    smk.setAgs(itSmkEntity.getAgs());
                    smk.setSep(itSmkEntity.getSep());
                    smk.setOkt(itSmkEntity.getOkt());
                    smk.setNov(itSmkEntity.getNov());
                    smk.setDes(itSmkEntity.getDes());
                    smk.setRataRata(itSmkEntity.getRataRata());

                    listOfResult.add(smk);
                }
            }
        }
        return listOfResult;
    }

    @Override
    public void saveEditMonthlySub(SmkAspekActivityMonthly bean) throws GeneralBOException {
        logger.info("[SmkBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String smkId = bean.getAspekActivityMonthly();

            ItSmkAspekActivityMonthlyEntity itMonthly = null;
            try {
                // Get data from database by ID
                smkAspekActivityMonthlyDao.updateData(bean.getEvaluasiPegawaiAspekId(), bean.getBulan(), bean.getNilai());
            } catch (HibernateException e) {
                logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
            }

        }
        logger.info("[SmkBoImpl.saveEdit] end process <<<");
    }

    @Override
    public void saveEditAspek(SmkEvaluasiPegawaiAspek bean) throws GeneralBOException {
        logger.info("[SmkBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String id           = bean.getEvaluasiPegawaiAspekId();
            String idTipeAspek  = bean.getTipeAspekId();
            double pointPrestasi  = bean.getPointPrestasi();

            ItSmkEvaluasiPegawaiAspekEntity itAspek = null;
            try {
                // Get data from database by ID
                itAspek = smkEvaluasiPegawaiAspekDao.getById("evaluasiPegawaiAspekId", id, "Y");
            } catch (HibernateException e) {
                logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
            }

            if (itAspek != null) {
                itAspek.setPointPrestasi(pointPrestasi);

                itAspek.setLastUpdateWho(bean.getLastUpdateWho());
                itAspek.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    smkEvaluasiPegawaiAspekDao.updateAndSave(itAspek);
                } catch (HibernateException e) {
                    logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Department, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SmkBoImpl.saveEdit] Error, not found data Department with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Department with request id, please check again your data ...");
//                condition = "Error, not found data Department with request id, please check again your data ...";
            }

        }
        logger.info("[SmkBoImpl.saveEdit] end process <<<");
    }

    @Override
    public List<SmkHistoryEvaluasiPegawai> smkHistorySys(String nip, String tahun) throws GeneralBOException {
        List<ItSmkHistoryEvaluasiPegawaiEntity> historyList = new ArrayList<>();
        List<SmkHistoryEvaluasiPegawai> smkHistoryEvaluasiPegawais = new ArrayList<>();

        historyList = smkHistory.getHistorySmk(nip);
        if(historyList.size() > 0){
            for(ItSmkHistoryEvaluasiPegawaiEntity itSmkHistoryEvaluasiPegawaiEntity : historyList){
                if(!itSmkHistoryEvaluasiPegawaiEntity.getPeriode().equalsIgnoreCase(tahun)){
                    SmkHistoryEvaluasiPegawai smkHistoryEvaluasiPegawai = new SmkHistoryEvaluasiPegawai();
                    smkHistoryEvaluasiPegawai.setHistoryEvaluasiPegawaiId(itSmkHistoryEvaluasiPegawaiEntity.getHistoryEvaluasiPegawaiId());
                    smkHistoryEvaluasiPegawai.setNip(itSmkHistoryEvaluasiPegawaiEntity.getNip());
                    smkHistoryEvaluasiPegawai.setPegawaiName(itSmkHistoryEvaluasiPegawaiEntity.getPegawaiName());
                    smkHistoryEvaluasiPegawai.setPeriode(itSmkHistoryEvaluasiPegawaiEntity.getPeriode());
                    smkHistoryEvaluasiPegawai.setNilaiPrestasi(itSmkHistoryEvaluasiPegawaiEntity.getNilaiPrestasi());

                    smkHistoryEvaluasiPegawais.add(smkHistoryEvaluasiPegawai);
                }
            }
        }

        return smkHistoryEvaluasiPegawais;
    }

    @Override
    public List<Biodata> comboUserSmkSys(String query, String unit, String jabatan) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Biodata> listComboBiodata = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImBiodataEntity> listPersonal = null;
        try {
            listPersonal = biodataDao.getListPersonal(query);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listPersonal != null) {
            for (ImBiodataEntity imBiodataEntity : listPersonal) {
                Biodata itemComboBiodata = new Biodata();
                String date = "";
                if(imBiodataEntity.getTanggalAktif() != null){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    date = dateFormat.format(imBiodataEntity.getTanggalAktif());
                    itemComboBiodata.setStTanggalAktif(date);
                }
                itemComboBiodata.setNip(imBiodataEntity.getNip());
                itemComboBiodata.setGolongan(imBiodataEntity.getGolongan());
                itemComboBiodata.setTipePegawai(imBiodataEntity.getTipePegawai());
                itemComboBiodata.setStatusPegawai(imBiodataEntity.getStatusPegawai());
                itemComboBiodata.setNamaPegawai(imBiodataEntity.getNamaPegawai());
                itemComboBiodata.setStatusPegawai(imBiodataEntity.getStatusPegawai());
                itemComboBiodata.setPoint(imBiodataEntity.getPoint());

                Map hsCriteria = new HashMap();
                hsCriteria.put("nip",imBiodataEntity.getNip());
                hsCriteria.put("flag","Y");

                List<ItPersonilPositionEntity> itPersonilPositionEntityList = null;
                try {
                    itPersonilPositionEntityList = personilPositionDao.getByCriteria(hsCriteria);
                } catch (HibernateException e) {
                    logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
                }
                if (itPersonilPositionEntityList!=null){
                    for ( ItPersonilPositionEntity listOfPersonil:itPersonilPositionEntityList){
                        ImPosition posisi = new ImPosition();
                        posisi = positionDao.getById("positionId", listOfPersonil.getPositionId(), "Y");
                        if(posisi != null){
                            if(posisi.getImDepartmentEntity() != null){
                                itemComboBiodata.setDivisi(posisi.getImDepartmentEntity().getDepartmentId());
                            }
                        }
                        itemComboBiodata.setPositionId(listOfPersonil.getPositionId());
                        itemComboBiodata.setBranch(listOfPersonil.getBranchId());
                        itemComboBiodata.setPjs(listOfPersonil.getPjs());
                    }
                }

//                itemComboBiodata.setDivisi(imBiodataEntity.getDivisi());
                itemComboBiodata.setTipePegawai(imBiodataEntity.getTipePegawai());
                itemComboBiodata.setMasaGiling(imBiodataEntity.getMasaGiling());
                itemComboBiodata.setTanggalAktif(imBiodataEntity.getTanggalAktif());
                itemComboBiodata.setGolonganId(imBiodataEntity.getGolongan());
//                itemComboBiodata.setPositionId(imBiodataEntity.getPositionId());
                listComboBiodata.add(itemComboBiodata);

            }
        }

        getBawahanJabatan(unit, jabatan);
        List<Biodata> hasilBiodata = new ArrayList<>();

        for(Biodata biodata: listComboBiodata){
            String userLogin = CommonUtil.userIdLogin();
            if(biodata.getNip().equalsIgnoreCase(userLogin)){
                hasilBiodata.add(biodata);
            }

            for(StrukturJabatan strukturJabatan: strukturJabatanList){
                if(biodata.getNip().equalsIgnoreCase(strukturJabatan.getNip())){
                    hasilBiodata.add(biodata);
                    break;
                }
            }
        }


        logger.info("[SmkBoImpl.getComboUserWithCriteria] end process <<<");
        return hasilBiodata;
    }

    @Override
    public String getIdMonthlySys(String activityId, String tanggal) throws GeneralBOException {
        List<ItSmkAspekActivityMonthlyEntity> smkAspekActivityMonthly = null;
        String strTanggal[] = tanggal.split("-");

        smkAspekActivityMonthly = smkAspekActivityMonthlyDao.getDataView(activityId, Integer.parseInt(strTanggal[1]));
        String hasil = "";
        for(ItSmkAspekActivityMonthlyEntity itSmkAspekActivityMonthlyEntity: smkAspekActivityMonthly){
            hasil = itSmkAspekActivityMonthlyEntity.getAspekActivityMonthly();
        }
        return hasil;
    }

    @Override
    public List<Smk> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public void applySmk(Smk smk) throws GeneralBOException {
        logger.info("[SmkBoImpl.applySmk] start process >>>");

        if (smk!=null) {
            String smkId = smk.getEvaluasiPegawaiId();

            ItSmkEntity itSmkEntity = null;
            try {
                itSmkEntity = smkDao.getById("evaluasiPegawaiId", smkId);
            } catch (HibernateException e) {
                logger.error("[SmkBoImpl.apllySmk] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
            }

            try {
                if(itSmkEntity != null){
                    double pointPrestasi = dataPointPrestasi(smkId);

                    double nilai = 0;
                    nilai = pointPrestasi;

                    String poin_nilaiPrestasi[];
                    poin_nilaiPrestasi = skalaNilai(nilai).split("-");
                    String nilaiPrestasi = poin_nilaiPrestasi[0];

                    int nilaiPoin = Integer.parseInt(poin_nilaiPrestasi[1]);
                    String poinPegawai[] = getPointPegawai(smk.getNip()).split("-");

                    int pointLama = Integer.parseInt(poinPegawai[0]);
                    int poinLebih = Integer.parseInt(poinPegawai[1]);

                    BigDecimal nilaiPres ;
                    BigDecimal nilaiShare  = new BigDecimal(0);
                    if(!smk.getNip().equalsIgnoreCase("US")){
                        nilaiShare  = hitungNilaiShare(smk);
                    }else{
                        smk.setPositionId("1");
                        nilaiShare = new BigDecimal(itSmkEntity.getGrandTotalNilaiPrestasi());
                        pointPrestasi = itSmkEntity.getGrandTotalNilaiPrestasi();
                    }
                    getBawahanJabatan(smk.getBranchId(), smk.getPositionId());

                    if(cekApproveSys(smk.getBranchId(), smk.getPositionId(), smk.getPeriode()) == true){
                        if(strukturJabatanList.size() > 0){
                            for(StrukturJabatan strukturJabatan : strukturJabatanList){
                                List<ItSmkEvaluasiPegawaiAspekDetailEntity> itSmkEntity1 = null;
                                itSmkEntity1 = smkDao.aspekDetail(smk.getPeriode(), strukturJabatan.getNip(), hasilUraian);
                                if(itSmkEntity1.size() > 0){
                                    for (ItSmkEvaluasiPegawaiAspekDetailEntity itSmkEntity2: itSmkEntity1){
                                        nilaiPres = new BigDecimal(0) ;
                                        ItSmkEvaluasiPegawaiAspekDetailEntity evaluasiPegawaiAspekDetail = null;
                                        evaluasiPegawaiAspekDetail = smkEvaluasiPegawaiAspekDetailDao.getById("evaluasiPegawaiAspekDetailId",
                                                itSmkEntity2.getEvaluasiPegawaiAspekDetailId(),"Y");
                                        evaluasiPegawaiAspekDetail.setNilai(nilaiShare);
                                        nilaiPres =  nilaiShare.multiply(itSmkEntity2.getBobot());
                                        nilaiPres = nilaiPres.divide(BigDecimal.valueOf(100), BigDecimal.ROUND_HALF_UP);
                                        evaluasiPegawaiAspekDetail.setNilaiPrestasi(nilaiPres);
                                        try {
                                            smkEvaluasiPegawaiAspekDetailDao.updateAndSave(evaluasiPegawaiAspekDetail);
                                        } catch (HibernateException e) {
                                            logger.error("[SmkBoImpl.applySmk] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
                                        }

                                        try {
                                            //update pgawai aspek khusus aspek A
                                            smkEvaluasiPegawaiAspekDetailDao.updateNilaiAfterApprove(itSmkEntity2.getEvaluasiPegawaiId(), itSmkEntity2.getEvaluasiPegawaiAspekDetailId(), nilaiPres);
                                        } catch (HibernateException e) {
                                            logger.error("[SmkBoImpl.applySmk] Error, " + e.getMessage());
                                            throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
                                        }
                                    }
                                }
                            }
                        }
                        try {
                            itSmkEntity.setNilaiPrestasi(nilaiPrestasi);
                            nilaiPoin += itSmkEntity.getPoin();
                            //itSmkEntity.setPointnew(nilaiPoin);
                            if(!smk.getNip().equalsIgnoreCase("US")){
                                itSmkEntity.setGrandTotalNilaiPrestasi(pointPrestasi);
                                //itSmkEntity.setGrandTotalNilaiPrestasiRevisi(pointPrestasiRevisi);
                            }else{
                                itSmkEntity.setGrandTotalNilaiPrestasi(pointPrestasi);
                                //itSmkEntity.setGrandTotalNilaiPrestasiRevisi(pointPrestasiRevisi);
                            }
                            //insertHistory(bean, nilai, nilaiPrestasi, nilaiPoin);
                            //updatePointPegawai(bean.getNip(), pointLama,  nilaiPoin, poinLebih);

                            smkDao.updateAndSave(itSmkEntity);
                        } catch (HibernateException e) {
                            logger.error("[SmkBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
                        }


                    }
                }
            } catch (Exception e) {
                logger.error("[SmkBoImpl.apllySmk] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Smk by Kode Smk, please inform to your admin...," + e.getMessage());
            }
        }
        logger.info("[SmkBoImpl.applySmk] end process <<<");
    }

    private BigDecimal getListNilai(String branch, String periode){

        BigDecimal hasil = new BigDecimal(0);
        try {
            List<ItSmkEntity> itSmkEntity = smkDao.getList("US", branch, periode);
            if(itSmkEntity.size() > 0){
                for(ItSmkEntity itSmkEntity1: itSmkEntity){
                    hasil = itSmkEntity1.getNilaiShare();
                    if(itSmkEntity1.getNilaiShare() == null){
                        hasil = BigDecimal.valueOf(0);
                    }
                }
            }
        } catch (HibernateException e) {
            logger.error("[SmkBoImpl.getListNilai] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching please inform to your admin...," + e.getMessage());
        }

        return hasil;
    }

    //untuk kabag / kabid
    private BigDecimal getListNilai(String nip, String branch, String periode, String kabagKabid){

        List<StrukturJabatan> strukturJabatanList = new ArrayList<>();
        String nipAtasan = "";
        String positionIdAtasan = "";

        try {
            strukturJabatanList = strukturJabatanDao.searchStrukturRelation2(nip, branch);
        } catch (HibernateException e) {
            logger.error("[SmkBoImpl.getListNilai] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        for (StrukturJabatan strukturJabatan:strukturJabatanList){
            // Search Leader
            if (strukturJabatan != null){
                String[] parts = strukturJabatan.getParentId().split("-");
                String parent = parts[0];
                int batas=1;
                if (parent != null){
                    boolean sudah = false;
                    //cek apakah parent adalah kabag
                    StrukturJabatan hasilStruktur=new StrukturJabatan();
                    List<StrukturJabatan> resultStruktur = new ArrayList<>();
                    do {
                        try {
                            resultStruktur = strukturJabatanDao.cekKelompok(parent, branch);
                        } catch (HibernateException e) {
                            logger.error("[smkBoImpl.getListNilai] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }
                        for (StrukturJabatan search : resultStruktur){
                            hasilStruktur.setPositionId(search.getPositionId());
                            hasilStruktur.setPositionKelompokId(search.getPositionKelompokId());
                            hasilStruktur.setNip(search.getNip());
                            hasilStruktur.setParentId(search.getParentId());
                            parent=search.getParentId();

                        }
                        batas++;
                        if ((kabagKabid).equalsIgnoreCase(hasilStruktur.getPositionKelompokId())||batas==10){
                            sudah=true;
                            nipAtasan = hasilStruktur.getNip();
                            positionIdAtasan = hasilStruktur.getPositionId();
                        }
                    }while (!sudah);
                }
            }
        }

        BigDecimal hasil = new BigDecimal(0);
        if(nipAtasan != ""){
            try {
                List<ItSmkEntity> itSmkEntity = smkDao.getListByPositionId(positionIdAtasan, branch, periode);
                if(itSmkEntity.size() > 0){
                    for(ItSmkEntity itSmkEntity1: itSmkEntity){
                        hasil = itSmkEntity1.getNilaiShare();
                    }
                }
            } catch (HibernateException e) {
                logger.error("[SmkBoImpl.getListNilai] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching please inform to your admin...," + e.getMessage());
            }

        }

        return hasil;
    }

    @Override
    public List<Smk> printReportSmkSys(String periode, String unit) throws GeneralBOException {
        List<ItSmkEntity> smkList = smkDao.getDetailReportSmk(periode, unit);
        List<Smk> smkTmp = new ArrayList<>();
        List<Smk> hasilSmkList = new ArrayList<>();

        // tool untuk isi bagian_id pada table smk
        /*if(smks.size() > 0){
            for(ItSmkEntity itSmkEntity: smks){
                ImPosition positions = positionDao.getById("positionId", itSmkEntity.getPositionId());

                itSmkEntity.setBagianId(positions.getImPositionBagianEntity().getBagianId());
                smkDao.updateAndSave(itSmkEntity);
            }
        }*/

        String bagian = "";
        int x = 0;
        List<ImPositionBagianEntity> imPositionBagianEntities = positionBagianDao.getDataBagianSmkV2(periode, unit);
        if(imPositionBagianEntities.size() > 0){
            for(ImPositionBagianEntity imPositionBagianEntity: imPositionBagianEntities){
                if(imPositionBagianEntity.getBagianName() != null){
                    if(x == 0){
                        smkTmp.addAll(headerSmk(imPositionBagianEntity.getBagianName(), Integer.parseInt(periode)));
                    } else if(!imPositionBagianEntity.getBagianName().equalsIgnoreCase(bagian)){
                        smkTmp.addAll(footerSmk(imPositionBagianEntity.getBagianName(), Integer.parseInt(periode)));
                    }
                }

                bagian = imPositionBagianEntity.getBagianName();
                for(ItSmkEntity itSmkEntity: smkList) {
                    if (!"".equalsIgnoreCase(imPositionBagianEntity.getNip()) && imPositionBagianEntity.getNip() != null &&
                            !"".equalsIgnoreCase(itSmkEntity.getNip()) && itSmkEntity.getNip() != null) {
                        if (imPositionBagianEntity.getNip().equalsIgnoreCase(itSmkEntity.getNip())) {
                            Smk smkAdd = new Smk();
                            Smk smkAdd2 = new Smk();

                            String golongan = "";
                            String poin = itSmkEntity.getPoin() + "";

                            if(itSmkEntity.getGolonganName() != null){{
                                golongan = itSmkEntity.getGolonganName().substring(9);
                            }}
                            if(itSmkEntity.getPoinLebih() > 0){{
                                poin = poin + "+" + itSmkEntity.getPoinLebih();
                            }}

                            x++;
                            smkAdd.setNip(x + " - " + itSmkEntity.getNip());
                            smkAdd.setPegawaiName(itSmkEntity.getPegawaiName());
                            smkAdd.setPeriode(periode);
                            smkAdd.setPositionName(itSmkEntity.getPositionName());
                            if(golongan.equalsIgnoreCase("")){
                                smkAdd.setGolonganName("-");
                            }else{
                                smkAdd.setGolonganName(golongan + "-" +poin);
                            }

                            smkAdd2.setNip("");
                            smkAdd2.setPegawaiName("");
                            smkAdd2.setPeriode(Integer.parseInt(periode) + 1 + "");
                            smkAdd2.setPositionName("");
                            smkAdd2.setGolonganName("");

                            smkAdd.setTahun1("-");
                            smkAdd.setTahun2("-");
                            smkAdd.setTahun3("-");
                            smkAdd.setTahun4("-");
                            smkAdd.setTahun5("-");
                            smkAdd.setHurufPrestasi("");
                            smkAdd.setNilaiPrestasi("");
                            smkAdd.setGolonganId("");
                            smkAdd.setMKG("");
                            smkAdd.setPoinPrestasi("");
                            smkAdd.setPoinPrestasiMin("");

                            smkAdd2.setTahun1("-");
                            smkAdd2.setTahun2("-");
                            smkAdd2.setTahun3("-");
                            smkAdd2.setTahun4("-");
                            smkAdd2.setTahun5("-");
                            smkAdd2.setHurufPrestasi("");
                            smkAdd2.setNilaiPrestasi("");
                            smkAdd2.setGolonganId("");
                            smkAdd2.setMKG("");
                            smkAdd2.setPoinPrestasi("");
                            smkAdd2.setPoinPrestasiMin("");

                            String txtTahunHistory = " and tahun in (";
                            if(!periode.equalsIgnoreCase("") && periode != null){
                                for(int a = 5; a >= 0 ; a--){
                                    int hasilTahun = Integer.parseInt(periode) - a;
                                    txtTahunHistory = txtTahunHistory + "'" + hasilTahun +"'" ;
                                    if(a > 0){
                                        txtTahunHistory = txtTahunHistory + ", ";
                                    }else{
                                        txtTahunHistory = txtTahunHistory + ") ";
                                    }
                                }

                                List<ItSmkEntity> smkDetail = smkDao.getDetailTahunSmk(itSmkEntity.getNip(), unit, txtTahunHistory);
                                if(smkDetail.size() > 0){

                                    for(int a = 5; a >= 0  ; a--){
                                        int hasilTahun = Integer.parseInt(periode) - a;
                                        for(ItSmkEntity smkDetail1 : smkDetail){
                                            if(smkDetail1.getPeriode().equalsIgnoreCase(hasilTahun + "")){
                                                golongan = "";
                                                poin = smkDetail1.getPoin() + "";

                                                if(smkDetail1.getGolonganName() != null){{
                                                    golongan = smkDetail1.getGolonganName().substring(9);
                                                }}

                                                if(smkDetail1.getPoinLebih() > 0){{
                                                    poin = poin + "+" + smkDetail1.getPoinLebih();
                                                }}

                                                if(a == 0){
                                                    if(golongan == null || golongan.equalsIgnoreCase("")){
                                                        smkAdd2.setHurufPrestasi("-");
                                                    }else{
                                                        smkAdd2.setHurufPrestasi(golongan + "-" + poin);
                                                    }

                                                    if(smkDetail1.getNilaiPrestasi() == null || smkDetail1.getNilaiPrestasi().equalsIgnoreCase("")){
                                                        smkAdd.setNilaiPrestasi("-");
                                                    }else{
                                                        smkAdd.setNilaiPrestasi(smkDetail1.getNilaiPrestasi());
                                                    }
                                                    smkAdd.setHurufPrestasi(smkDetail1.getNilaiPrestasi());
                                                    smkAdd.setNilaiPrestasi(CommonUtil.round(smkDetail1.getPointPrestasi(), 2) + "");
                                                    //smkAdd2.setNilaiPrestasi("");
                                                }else if(a == 1){
                                                    smkAdd2.setTahun5(golongan + "-" + poin);
                                                    smkAdd.setTahun5(smkDetail1.getNilaiPrestasi());
                                                }else if(a == 2){
                                                    smkAdd2.setTahun4(golongan + "-" + poin);
                                                    smkAdd.setTahun4(smkDetail1.getNilaiPrestasi());
                                                }else if(a == 3){
                                                    smkAdd2.setTahun3(golongan + "-" + poin);
                                                    smkAdd.setTahun3(smkDetail1.getNilaiPrestasi());
                                                }else if(a == 4){
                                                    smkAdd2.setTahun2(golongan + "-" + poin);
                                                    smkAdd.setTahun2(smkDetail1.getNilaiPrestasi());
                                                }else if(a == 5){
                                                    smkAdd2.setTahun1(golongan + "-" + poin);
                                                    smkAdd.setTahun1(smkDetail1.getNilaiPrestasi());
                                                }
                                                break;
                                            }
                                        }
                                    }
                                }
                                List<ItSmkEntity> smkList1 = smkDao.getEditMasaKerjaGolongan(itSmkEntity.getNip(), itSmkEntity.getGolonganId());
                                String hasil = "";
                                int per= Integer.parseInt(periode);
                                try{
                                    if(smkList1.size() > 0){
                                        int tahun = 0;
                                        per++;
                                        for(ItSmkEntity smkEntity: smkList1){
                                            tahun = Integer.parseInt(smkEntity.getPeriode());
                                        }
                                        hasil = per - tahun + " Tahun";
                                        smkAdd.setGolonganId(tahun + "");
                                        smkAdd.setMKG(hasil);
                                    }
                                }catch (Exception E){
                                    logger.error("[SmkBoImpl.printReportSmkSys] Error, " + E.getMessage());
                                    throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + E.getMessage());
                                }

                                // Jumlah Poin
                                List<ItSmkEntity> smkListPoin = smkDao.getJumlahPoinMkg(itSmkEntity.getNip(), itSmkEntity.getGolonganId());
                                int jumlahPoin = 0;
                                int syaratMin = 0;
                                try{
                                    if(smkListPoin.size() > 0){
                                        for(ItSmkEntity smkEntity: smkListPoin){
                                            if(!smkEntity.getNilaiPrestasi().equalsIgnoreCase("-")){
                                                String spl[] = smkEntity.getNilaiPrestasi().split("/");
                                                if(spl[0].equalsIgnoreCase("B")){
                                                    jumlahPoin += 2;
                                                }else if(spl[0].equalsIgnoreCase("C")){
                                                    jumlahPoin += 1;
                                                }else if(spl[0].equalsIgnoreCase("BS")){
                                                    jumlahPoin += 3;
                                                }
                                            }
                                        }

                                        if(jumlahPoin <= 4){
                                            syaratMin = 7;
                                        }else if(jumlahPoin > 4 && jumlahPoin <= 6){
                                            syaratMin = 8;
                                        }else if(jumlahPoin > 6){
                                            syaratMin = 9;
                                        }
                                        smkAdd.setPoinPrestasi(jumlahPoin + "");
                                        smkAdd.setPoinPrestasiMin(syaratMin + "");
                                    }
                                }catch (Exception E){
                                    logger.error("[SmkBoImpl.printReportSmkSys] Error, " + E.getMessage());
                                    throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + E.getMessage());
                                }
                            }
                            smkTmp.add(smkAdd);
                            smkTmp.add(smkAdd2);
                        }
                    }
                }
            }

        }

        return smkTmp;
    }

    private List<Smk> headerSmk(String bagian, int periode){
        Smk smk1 = new Smk();
        Smk smk2 = new Smk();

        List<Smk> smks = new ArrayList<>();


        smk1.setEvaluasiPegawaiId("");
        smk1.setNip("");
        smk1.setPegawaiName(bagian);
        smk1.setPeriode("");
        smk1.setPositionName("");
        smk1.setGolonganName("");
        smk1.setTahun1("");
        smk1.setTahun2("");
        smk1.setTahun3("");
        smk1.setTahun4("");
        smk1.setTahun5("");
        smk1.setHurufPrestasi("");
        smk1.setNilaiPrestasi("");
        smk1.setGolonganId("");
        smk1.setMKG("");
        smk1.setPoinPrestasi("");
        smk1.setPoinPrestasiMin("");

        smk2.setTahun1("");
        smk2.setTahun2("");
        smk2.setTahun3("");
        smk2.setTahun4("");
        smk2.setTahun5("");
        smk2.setHurufPrestasi(periode+"(Huruf)");
        smk2.setNilaiPrestasi(periode+"(Nilai)");
        smk2.setEvaluasiPegawaiId("");
        smk2.setNip("NIP");
        smk2.setPegawaiName("Nama");
        smk2.setPeriode("Periode");
        smk2.setPositionName("Jabatan");
        smk2.setGolonganName("Golongan");
        smk2.setGolonganId("Sejak");
        smk2.setMKG("MKG");
        smk2.setPoinPrestasi("Jml");
        smk2.setPoinPrestasiMin("Syarat Min");
        for(int a = 5; a > 0  ; a--) {
            int hasilTahun = periode - a;
            if(a == 1){
                smk2.setTahun5(hasilTahun + "");
            }else if(a == 2){
                smk2.setTahun4(hasilTahun + "");
            }else if(a == 3){
                smk2.setTahun3(hasilTahun + "");
            }else if(a == 4){
                smk2.setTahun2(hasilTahun + "");
            }else if(a == 5){
                smk2.setTahun1(hasilTahun + "");
            }
        }

        smks.add(smk1);
        smks.add(smk2);

        return smks;
    }

    private List<Smk> footerSmk(String bagian, int periode){
        Smk smk1 = new Smk();
        Smk smk2 = new Smk();
        Smk smkFooter = new Smk();

        List<Smk> smks = new ArrayList<>();

        smkFooter.setEvaluasiPegawaiId("");
        smkFooter.setNip("");
        smkFooter.setPegawaiName("");
        smkFooter.setPeriode("");
        smkFooter.setPositionName("");
        smkFooter.setGolonganName("");

        smk1.setEvaluasiPegawaiId("");
        smk1.setNip("");
        smk1.setPegawaiName(bagian);
        smk1.setPeriode("");
        smk1.setPositionName("");
        smk1.setGolonganName("");

        smkFooter.setTahun1("");
        smkFooter.setTahun2("");
        smkFooter.setTahun3("");
        smkFooter.setTahun4("");
        smkFooter.setTahun5("");
        smkFooter.setHurufPrestasi("");
        smkFooter.setNilaiPrestasi("");
        smkFooter.setGolonganId("");
        smkFooter.setMKG("");
        smkFooter.setPoinPrestasi("");
        smkFooter.setPoinPrestasiMin("");

        smk1.setTahun1("");
        smk1.setTahun2("");
        smk1.setTahun3("");
        smk1.setTahun4("");
        smk1.setTahun5("");
        smk1.setHurufPrestasi("");
        smk1.setNilaiPrestasi("");
        smk1.setGolonganId("");
        smk1.setMKG("");
        smk1.setPoinPrestasi("");
        smk1.setPoinPrestasiMin("");

        smk2.setTahun1("");
        smk2.setTahun2("");
        smk2.setTahun3("");
        smk2.setTahun4("");
        smk2.setTahun5("");
        smk2.setEvaluasiPegawaiId("");
        smk2.setNip("NIP");
        smk2.setPegawaiName("Nama");
        smk2.setPeriode("Periode");
        smk2.setPositionName("Jabatan");
        smk2.setGolonganName("Golongan");
        for(int a = 5; a > 0  ; a--) {
            int hasilTahun = periode - a;
            if(a == 1){
                smk2.setTahun5(hasilTahun + "");
            }else if(a == 2){
                smk2.setTahun4(hasilTahun + "");
            }else if(a == 3){
                smk2.setTahun3(hasilTahun + "");
            }else if(a == 4){
                smk2.setTahun2(hasilTahun + "");
            }else if(a == 5){
                smk2.setTahun1(hasilTahun + "");
            }
        }
        smk2.setHurufPrestasi(periode+"(Huruf)");
        smk2.setNilaiPrestasi(periode+"(Nilai)");
        smk2.setGolonganId("Sejak");
        smk2.setMKG("MKG");
        smk2.setPoinPrestasi("Jml");
        smk2.setPoinPrestasiMin("Syarat Min");



        smks.add(smkFooter);
        smks.add(smk1);
        smks.add(smk2);

        return smks;
    }

    @Override
    public BigDecimal getNilaiShareSys(Smk smk) throws GeneralBOException {
        BigDecimal hasil = new BigDecimal(0);

        try{
            hasil = hitungNilaiShare(smk);
        }catch (Exception E){
            logger.error("[SmkBoImpl.getNilaiShareSys] Error, " + E.getMessage());
        }
        return hasil;
    }

    private String masaKerjaGolongan(String nip, String golonganId, int periode){
        List<ItSmkEntity> smkList = smkDao.getEditMasaKerjaGolongan(nip, golonganId);
        String hasil = "";

        if(smkList.size() > 0){
            int tahun = 0;
            periode++;
            for(ItSmkEntity smkEntity: smkList){
                tahun = Integer.parseInt(smkEntity.getPeriode());
            }
            hasil = periode - tahun + " Tahun";
        }
        return hasil;
    }

    private String cariBranchName(String branchId){
        List<ImBranches> branches = branchDao.getListBranchById(branchId);
        String hasil = "";
        if(branches.size() > 0){
            for(ImBranches imBranches: branches){
                hasil = imBranches.getBranchName();
            }
        }
        return hasil;
    }

    private String cariPosisiName(String positionId){
        ImPosition position = positionDao.getById("positionId", positionId);
        String hasil = "";
        hasil = position.getPositionName();

        return hasil;
    }

    @Override
    public List<Branch> listBranch(String nip, String periode) throws GeneralBOException {
        List<ImtHistorySmkGolonganEntity> historyGolongan = smkHistoryGolonganDao.getHistoryJabatan(nip, periode);
        List<Branch> branchForSmk = new ArrayList<>();

        if(historyGolongan.size() > 0){
            for(ImtHistorySmkGolonganEntity historyLoop: historyGolongan){
                // insert branch for SMK
                Branch branch = new Branch();
                branch.setBranchId(historyLoop.getBranchId());
                branch.setBranchName(cariBranchName(historyLoop.getBranchId()));

                if(historyLoop.getFlagMutasi() != null){
                    if(historyLoop.getFlagMutasi().equalsIgnoreCase("Y")){
                        branchForSmk.add(branch);

                        List<ImtHrisHistoryJabatanPegawaiEntity> historyJabatan = historyJabatanPegawaiDao.getDataHistoryJabatan(nip, periode);
                        if(historyJabatan.size() > 0){
                            for(ImtHrisHistoryJabatanPegawaiEntity historyJabatanLoop: historyJabatan){
                                branch = new Branch();
                                branch.setBranchId(historyJabatanLoop.getBranchId());

                                // cek aapakah branch sudah ada
                                if(branchForSmk.size() > 0){
                                    String branchAda = "N";
                                    for(Branch branchLoop: branchForSmk){
                                        if(branchLoop.getBranchId().equalsIgnoreCase(historyJabatanLoop.getBranchId())){
                                            branchAda = "Y";
                                            break;
                                        }
                                    }
                                    if(branchAda.equalsIgnoreCase("N")){
                                        branch.setBranchName(cariBranchName(historyJabatanLoop.getBranchId()));
                                        branchForSmk.add(branch);
                                    }
                                }
                            }
                        }
                    }else{
                        branchForSmk.add(branch);
                    }
                }else{
                    // cek aapakah branch sudah ada
                    if(branchForSmk.size() > 0){
                        String branchAda = "N";
                        for(Branch branchLoop: branchForSmk){
                            if(branchLoop.getBranchId().equalsIgnoreCase(branchLoop.getBranchId())){
                                branchAda = "Y";
                                break;
                            }
                        }
                        if(branchAda.equalsIgnoreCase("N")){
                            branchForSmk.add(branch);
                        }
                    }

                    branchForSmk.add(branch);
                }
            }
        }

        return branchForSmk;
    }

    @Override
    public List<ImPosition> listPosisi(String nip, String periode) throws GeneralBOException {
        List<ImtHrisHistoryJabatanPegawaiEntity> historyJabatan = historyJabatanPegawaiDao.getDataHistoryJabatan(nip);
        List<ImPosition> posisiForSmk = new ArrayList();

        if(historyJabatan.size() > 0){
            for(ImtHrisHistoryJabatanPegawaiEntity historyLoop: historyJabatan){
                // insert positionId for SMK
                ImPosition position = new ImPosition();
                position.setPositionId(historyLoop.getPositionId());
                position.setPositionName(historyLoop.getPositionName());
                posisiForSmk.add(position);
            }
        }

        return posisiForSmk;
    }

    @Override
    public List<ImPosition> listPosisi(String nip, String periode, String branchId) throws GeneralBOException {
        List<ImtHistorySmkGolonganEntity> historyGolongan = smkHistoryGolonganDao.getHistoryJabatan(nip, periode);
        List<ImPosition> posisiForSmk = new ArrayList();
        List<ImPosition> hasilPosisiForSmk = new ArrayList();

        if(historyGolongan.size() > 0){
            for(ImtHistorySmkGolonganEntity historyLoop: historyGolongan){
                // insert posisi for SMK
                ImPosition position = new ImPosition();
                position.setPositionId(historyLoop.getPositionId());
                position.setPositionName(cariPosisiName(historyLoop.getPositionId()));
                position.setFlag(historyLoop.getBranchId());

                if(historyLoop.getFlagMutasi() != null){
                    if(historyLoop.getFlagMutasi().equalsIgnoreCase("Y")){
                        posisiForSmk.add(position);

                        List<ImtHrisHistoryJabatanPegawaiEntity> historyJabatan = historyJabatanPegawaiDao.getDataHistoryJabatan(nip, periode);
                        if(historyJabatan.size() > 0){
                            for(ImtHrisHistoryJabatanPegawaiEntity historyJabatanLoop: historyJabatan){
                                position = new ImPosition();
                                position.setPositionId(historyJabatanLoop.getPositionId());
                                position.setPositionName(cariPosisiName(historyJabatanLoop.getPositionId()));
                                // menampung branchId
                                position.setFlag(historyJabatanLoop.getBranchId());
                                posisiForSmk.add(position);
                            }
                        }
                    }else{
                        posisiForSmk.add(position);
                    }
                }else{
                    posisiForSmk.add(position);
                }
            }
            for(ImPosition position: posisiForSmk){
                if(position.getFlag().equalsIgnoreCase(branchId)){
                    hasilPosisiForSmk.add(position);
                }
            }
        }
        return hasilPosisiForSmk;
    }

    @Override
    public String masaKerjaBulan(String nip, String periode, String branchId, String positionId) throws GeneralBOException {
        int hasil = 0;
        int tmpHasil = 0;
        int tmpKandir = 0;
        int tmpKrebet = 0;
        int tmpRejoAgung = 0;
        int tmpBulan = 0;
        String tmpUnit = "";

        List<ImtHistorySmkGolonganEntity> historyGolongan = smkHistoryGolonganDao.getHistoryJabatan(nip, periode);
        List<ImPosition> posisiForSmk = new ArrayList();
        List<ImPosition> hasilPosisiForSmk = new ArrayList();
        List<Branch> listBranch = new ArrayList();

        if(historyGolongan.size() > 0){
            for(ImtHistorySmkGolonganEntity historyLoop: historyGolongan){
                // insert posisi for SMK
                ImPosition position = new ImPosition();
                position.setPositionId(historyLoop.getPositionId());
                position.setPositionName(cariPosisiName(historyLoop.getPositionId()));
                // menampung branchId
                position.setFlag(historyLoop.getBranchId());

                Branch branch = new Branch();
                branch.setBranchId(historyLoop.getBranchId());
                tmpUnit = historyLoop.getBranchId();

                if(historyLoop.getFlagMutasi() != null){
                    if(historyLoop.getFlagMutasi().equalsIgnoreCase("Y")){
                        posisiForSmk.add(position);
                        listBranch.add(branch);

                        List<ImtHrisHistoryJabatanPegawaiEntity> historyJabatan = historyJabatanPegawaiDao.getDataHistoryJabatan(nip, periode);
                        if(historyJabatan.size() > 0){
                            for(ImtHrisHistoryJabatanPegawaiEntity historyJabatanLoop: historyJabatan){
                                position = new ImPosition();
                                branch = new Branch();
                                position.setPositionId(historyJabatanLoop.getPositionId());
                                position.setPositionName(cariPosisiName(historyJabatanLoop.getPositionId()));
                                // menampung branchId
                                position.setFlag(historyJabatanLoop.getBranchId());

                                branch.setBranchId(historyJabatanLoop.getBranchId());
                                posisiForSmk.add(position);

                                // cek apakah ada mutasi keluar unit
                                // cek apakah branch sudah ada
                                if(listBranch.size() > 0){
                                    String branchAda = "N";
                                    for(Branch branchLoop: listBranch){
                                        if(branchLoop.getBranchId().equalsIgnoreCase(historyJabatanLoop.getBranchId())){
                                            branchAda = "Y";
                                            break;
                                        }
                                    }
                                    if(branchAda.equalsIgnoreCase("N")){
                                        listBranch.add(branch);
                                    }
                                }
                            }
                        }
                    }else{
                        posisiForSmk.add(position);
                        listBranch.add(branch);
                    }
                }else{
                    posisiForSmk.add(position);
                    listBranch.add(branch);
                }
            }
        }

        // jika > 1 berarti pernah mutasi luar unit dalam satu tahun
        if(listBranch.size() > 1){
            List<ImtHrisHistoryJabatanPegawaiEntity> historyJabatan = historyJabatanPegawaiDao.getDataHistoryJabatan(nip, periode);
            int jumlahLoop = 0;
            if(historyJabatan.size() > 0){
                for(ImtHrisHistoryJabatanPegawaiEntity historyLoop : historyJabatan){
                    jumlahLoop++;
                    if(historyLoop.getTanggalSkMutasi() != null ){
                        if(jumlahLoop == 1){
                            tmpHasil = historyLoop.getTanggalSkMutasi().getMonth() + 1 ;
                        }else{
                            tmpHasil = (historyLoop.getTanggalSkMutasi().getMonth() + 1) - tmpBulan;
                        }
                        tmpBulan += tmpHasil;
                    }
                    if(tmpUnit.equalsIgnoreCase("KD01")){
                        tmpKandir += tmpHasil;
                    }else if(tmpUnit.equalsIgnoreCase("PGKB")){
                        tmpKrebet += tmpHasil;
                    }else if(tmpUnit.equalsIgnoreCase("PGRA")){
                        tmpRejoAgung += tmpHasil;
                    }

                    tmpUnit = historyLoop.getBranchId();
                    if(jumlahLoop == historyJabatan.size()){
                        tmpHasil = 12 - tmpBulan;
                        if(tmpUnit.equalsIgnoreCase("KD01")){
                            tmpKandir += tmpHasil;
                        }else if(tmpUnit.equalsIgnoreCase("PGKB")){
                            tmpKrebet += tmpHasil;
                        }else if(tmpUnit.equalsIgnoreCase("PGRA")){
                            tmpRejoAgung += tmpHasil;
                        }
                    }
                }
                if(branchId.equalsIgnoreCase("KD01")){
                    hasil = tmpKandir;
                }else if(branchId.equalsIgnoreCase("PGKB")){
                    hasil = tmpKrebet;
                }else if(branchId.equalsIgnoreCase("PGRA")){
                    hasil = tmpRejoAgung;
                }
            }
        }else{
            hasil = 12;
        }

        return hasil + "";
    }
}
