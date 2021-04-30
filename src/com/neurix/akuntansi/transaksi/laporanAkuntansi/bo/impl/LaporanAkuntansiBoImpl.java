package com.neurix.akuntansi.transaksi.laporanAkuntansi.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.settingReportArusKas.dao.SettingReportKeuanganArusKasDao;
import com.neurix.akuntansi.master.settingReportArusKas.model.AkunSettingReportKeuanganArusKas;
import com.neurix.akuntansi.master.settingReportArusKas.model.AkunSettingReportKeuanganArusKasDetail;
import com.neurix.akuntansi.master.settingReportArusKas.model.ImAkunSettingReportArusKasEntity;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.dao.SettingReportKeuanganKonsolDao;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsol;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsolDetail;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.ImAkunSettingReportKeuanganKonsol;
import com.neurix.akuntansi.transaksi.budgeting.action.BudgetingAction;
import com.neurix.akuntansi.transaksi.budgeting.dao.BudgetingDao;
import com.neurix.akuntansi.transaksi.budgeting.model.Budgeting;
import com.neurix.akuntansi.transaksi.budgeting.model.BudgettingDTO;
import com.neurix.akuntansi.transaksi.budgeting.model.ItAkunBudgetingEntity;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.bo.LaporanAkuntansiBo;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.dao.LaporanAkuntansiDao;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.*;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;


public class LaporanAkuntansiBoImpl implements LaporanAkuntansiBo {

    protected static transient Logger logger = Logger.getLogger(LaporanAkuntansiBoImpl.class);
    private LaporanAkuntansiDao laporanAkuntansiDao;
    private PersonilPositionDao personilPositionDao;
    private BiodataDao biodataDao;
    private KodeRekeningDao kodeRekeningDao;
    private SettingReportKeuanganKonsolDao settingReportKeuanganKonsolDao;
    private SettingReportKeuanganArusKasDao settingReportKeuanganArusKasDao;
    private BudgetingDao budgetingDao;

    public SettingReportKeuanganArusKasDao getSettingReportKeuanganArusKasDao() {
        return settingReportKeuanganArusKasDao;
    }

    public void setSettingReportKeuanganArusKasDao(SettingReportKeuanganArusKasDao settingReportKeuanganArusKasDao) {
        this.settingReportKeuanganArusKasDao = settingReportKeuanganArusKasDao;
    }

    public BudgetingDao getBudgetingDao() {
        return budgetingDao;
    }

    public void setBudgetingDao(BudgetingDao budgetingDao) {
        this.budgetingDao = budgetingDao;
    }

    public SettingReportKeuanganKonsolDao getSettingReportKeuanganKonsolDao() {
        return settingReportKeuanganKonsolDao;
    }

    public void setSettingReportKeuanganKonsolDao(SettingReportKeuanganKonsolDao settingReportKeuanganKonsolDao) {
        this.settingReportKeuanganKonsolDao = settingReportKeuanganKonsolDao;
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

    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        LaporanAkuntansiBoImpl.logger = logger;
    }

    public LaporanAkuntansiDao getLaporanAkuntansiDao() {
        return laporanAkuntansiDao;
    }


    public void setLaporanAkuntansiDao(LaporanAkuntansiDao laporanAkuntansiDao) {
        this.laporanAkuntansiDao = laporanAkuntansiDao;
    }


    @Override
    public void saveDelete(LaporanAkuntansi bean) throws GeneralBOException {
        logger.info("[LaporanAkuntansiBoImpl.saveDelete] start process >>>");
        if (bean!=null) {
            //untuk delete
            //validasi cek report id di setting user report dan report detail
            Integer jumlahData = 0;
            try {
                jumlahData= laporanAkuntansiDao.searchReportIdExisting(bean.getLaporanAkuntansiId());
            } catch (HibernateException e) {
                logger.error("[KodeRekeningBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving update data, please info to your admin..." + e.getMessage());
            }
            if (jumlahData>0){
                String status = "Report masih digunakan di report detail";
                logger.error("[KodeRekeningBoImpl.saveEdit] Error, " +status);
                throw new GeneralBOException("ERROR :" +status);
            }

            //save
            ItLaporanAkuntansiEntity itLaporanAkuntansiEntity = new ItLaporanAkuntansiEntity();
            try {
                // Get data from database by ID
                itLaporanAkuntansiEntity = laporanAkuntansiDao.getById("laporanAkuntansiId", bean.getLaporanAkuntansiId());
            } catch (HibernateException e) {
                logger.error("[LaporanAkuntansiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
            }

            if (itLaporanAkuntansiEntity != null) {
                // Modify from bean to entity serializable
                itLaporanAkuntansiEntity.setFlag(bean.getFlag());
                itLaporanAkuntansiEntity.setAction(bean.getAction());
                itLaporanAkuntansiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itLaporanAkuntansiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    laporanAkuntansiDao.updateAndSave(itLaporanAkuntansiEntity);
                } catch (HibernateException e) {
                    logger.error("[LaporanAkuntansiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Vendor, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[LaporanAkuntansiBoImpl.saveDelete] Error, not found data Vendor with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Vendor with request id, please check again your data ...");

            }
        }
        logger.info("[LaporanAkuntansiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(LaporanAkuntansi bean) throws GeneralBOException {
        logger.info("[LaporanAkuntansiBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            ItLaporanAkuntansiEntity itLaporanAkuntansiEntity = null;
            try {
                // Get data from database by ID
                itLaporanAkuntansiEntity = laporanAkuntansiDao.getById("laporanAkuntansiId", bean.getLaporanAkuntansiId());
            } catch (HibernateException e) {
                logger.error("[LaporanAkuntansiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Vendor by Kode Vendor, please inform to your admin...," + e.getMessage());
            }
            if (itLaporanAkuntansiEntity != null) {
                itLaporanAkuntansiEntity.setLaporanAkuntansiName(bean.getLaporanAkuntansiName());
                itLaporanAkuntansiEntity.setUrl(bean.getUrl());
                itLaporanAkuntansiEntity.setLevelKodeRekening(bean.getLevelKodeRekening());
                itLaporanAkuntansiEntity.setAdaTipeLaporan(bean.getAdaTipeLaporan());

                itLaporanAkuntansiEntity.setFlag(bean.getFlag());
                itLaporanAkuntansiEntity.setAction(bean.getAction());
                itLaporanAkuntansiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                itLaporanAkuntansiEntity.setLastUpdate(bean.getLastUpdate());
                try {
                    // Update into database
                    laporanAkuntansiDao.updateAndSave(itLaporanAkuntansiEntity);
                } catch (HibernateException e) {
                    logger.error("[LaporanAkuntansiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Vendor, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[LaporanAkuntansiBoImpl.saveEdit] Error, not found data Vendor with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Vendor with request id, please check again your data ...");
            }
        }
        logger.info("[LaporanAkuntansiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public LaporanAkuntansi saveAdd(LaporanAkuntansi bean) throws GeneralBOException {
        logger.info("[LaporanAkuntansiBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String reportId;
            try {
                // Generating ID, get from postgre sequence
                reportId = laporanAkuntansiDao.getNextLaporanAkuntansiId();
            } catch (HibernateException e) {
                logger.error("[LaporanAkuntansiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence vendorId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ItLaporanAkuntansiEntity itLaporanAkuntansiEntity = new ItLaporanAkuntansiEntity();

            itLaporanAkuntansiEntity.setLaporanAkuntansiId(reportId);
            itLaporanAkuntansiEntity.setLaporanAkuntansiName(bean.getLaporanAkuntansiName());
            itLaporanAkuntansiEntity.setUrl(bean.getUrl());
            itLaporanAkuntansiEntity.setLevelKodeRekening(bean.getLevelKodeRekening());
            itLaporanAkuntansiEntity.setAdaTipeLaporan(bean.getAdaTipeLaporan());
            itLaporanAkuntansiEntity.setFlag(bean.getFlag());
            itLaporanAkuntansiEntity.setAction(bean.getAction());
            itLaporanAkuntansiEntity.setCreatedWho(bean.getCreatedWho());
            itLaporanAkuntansiEntity.setLastUpdateWho(bean.getLastUpdateWho());
            itLaporanAkuntansiEntity.setCreatedDate(bean.getCreatedDate());
            itLaporanAkuntansiEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                laporanAkuntansiDao.addAndSave(itLaporanAkuntansiEntity);
            } catch (HibernateException e) {
                logger.error("[LaporanAkuntansiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Vendor, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[LaporanAkuntansiBoImpl.saveAdd] end process <<<");
        return null;
    }
    
    @Override
    public List<LaporanAkuntansi> getByCriteria(LaporanAkuntansi searchBean) throws GeneralBOException {
        List<LaporanAkuntansi> listOfResult = new ArrayList();
        logger.info("[LaporanAkuntansiBoImpl.getByCriteria] start process >>>");

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getLaporanAkuntansiId() != null && !"".equalsIgnoreCase(searchBean.getLaporanAkuntansiId())) {
                hsCriteria.put("laporan_akuntansi_id", searchBean.getLaporanAkuntansiId());
            }
            if (searchBean.getLaporanAkuntansiName() != null && !"".equalsIgnoreCase(searchBean.getLaporanAkuntansiName())) {
                hsCriteria.put("laporan_akuntansi_name", searchBean.getLaporanAkuntansiName());
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


            List<ItLaporanAkuntansiEntity> imLaporanAkuntansiEntity = null;
            try {
                imLaporanAkuntansiEntity = laporanAkuntansiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[LaporanAkuntansiBoImpl.getSearchLaporanAkuntansiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imLaporanAkuntansiEntity != null) {
                LaporanAkuntansi returnLaporanAkuntansi;
                // Looping from dao to object and save in collection
                for(ItLaporanAkuntansiEntity itLaporanAkuntansiEntity: imLaporanAkuntansiEntity){
                    returnLaporanAkuntansi = new LaporanAkuntansi();

                    returnLaporanAkuntansi.setLaporanAkuntansiId(itLaporanAkuntansiEntity.getLaporanAkuntansiId());
                    returnLaporanAkuntansi.setLaporanAkuntansiName(itLaporanAkuntansiEntity.getLaporanAkuntansiName());
                    returnLaporanAkuntansi.setUrl(itLaporanAkuntansiEntity.getUrl());
                    returnLaporanAkuntansi.setLevelKodeRekening(itLaporanAkuntansiEntity.getLevelKodeRekening());
                    returnLaporanAkuntansi.setAdaTipeLaporan(itLaporanAkuntansiEntity.getAdaTipeLaporan());
                    returnLaporanAkuntansi.setCreatedWho(itLaporanAkuntansiEntity.getCreatedWho());
                    returnLaporanAkuntansi.setCreatedDate(itLaporanAkuntansiEntity.getCreatedDate());
                    returnLaporanAkuntansi.setLastUpdate(itLaporanAkuntansiEntity.getLastUpdate());
                    returnLaporanAkuntansi.setAction(itLaporanAkuntansiEntity.getAction());
                    returnLaporanAkuntansi.setFlag(itLaporanAkuntansiEntity.getFlag());
                    listOfResult.add(returnLaporanAkuntansi);
                }
            }
        }
        logger.info("[LaporanAkuntansiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }
    @Override
    public LaporanAkuntansi getNipDanNamaManagerKeuanganDanGeneralManager(String branchId) throws GeneralBOException {
        LaporanAkuntansi nama = new LaporanAkuntansi();
        List<ItPersonilPositionEntity> managerList = new ArrayList<>();

        nama.setNipGeneralManager("");
        nama.setNamaGeneralManager("");
        nama.setNipManagerKeuangan("");
        nama.setNamaManagerKeuangan("");
        String posisiManagerKeuangan;
        String posisiGeneralManager;

        if (branchId.equalsIgnoreCase(CommonConstant.ID_KANPUS)){
            posisiManagerKeuangan = CommonConstant.posisiKasubbidKeuanganKp;
            posisiGeneralManager= CommonConstant.posisiKabidKeuanganKp;
            nama.setJudulGeneralManager(CommonConstant.NAMA_GENERAL_MANAGER_KP);
            nama.setJudulManagerKeuangan(CommonConstant.NAMA_MANAGER_KEUANGAN_KP);
        }else {
            posisiManagerKeuangan = CommonConstant.posisiKadivKeuanganUnit;
            posisiGeneralManager= CommonConstant.posisiGmUnit;
            nama.setJudulGeneralManager(CommonConstant.NAMA_GENERAL_MANAGER_UNIT);
            nama.setJudulManagerKeuangan(CommonConstant.NAMA_MANAGER_KEUANGAN_UNIT);
        }

        //untuk manager keuangan
        managerList=personilPositionDao.getPersonilPositionByUnitdanPosisi(branchId,posisiManagerKeuangan);
        if (managerList.size()!=0){
            for (ItPersonilPositionEntity manager : managerList){
                ImBiodataEntity biodataEntity = biodataDao.getById("nip",manager.getNip());
                nama.setNipManagerKeuangan(biodataEntity.getNip());
                nama.setNamaManagerKeuangan(biodataEntity.getNamaPegawai());
            }
        }
        //untuk general manager
        managerList=personilPositionDao.getPersonilPositionByUnitdanPosisi(branchId,posisiGeneralManager);
        if (managerList.size()!=0){
            for (ItPersonilPositionEntity manager : managerList){
                ImBiodataEntity biodataEntity = biodataDao.getById("nip",manager.getNip());
                nama.setNipGeneralManager(biodataEntity.getNip());
                nama.setNamaGeneralManager(biodataEntity.getNamaPegawai());
            }
        }

        return nama;
    }

    @Override
    public List<Aging> getAging(String branch, String periode, String masterId,String tipeAging,String reportId,String tipeLaporan) throws GeneralBOException {
        List<Aging> agingList = new ArrayList<>();
        try {
            if (("usaha").equalsIgnoreCase(tipeAging)){
                agingList = laporanAkuntansiDao.getAging(branch,periode,masterId,reportId,tipeLaporan);
            }else if (("pasien").equalsIgnoreCase(tipeAging)){
                agingList = laporanAkuntansiDao.getAgingPasien(branch,periode,masterId,reportId,tipeLaporan);
            }else if (("dokter").equalsIgnoreCase(tipeAging)){
                agingList = laporanAkuntansiDao.getAgingDokter(branch,periode,masterId,reportId,tipeLaporan);
            }else if (("pegawai").equalsIgnoreCase(tipeAging)){
                agingList = laporanAkuntansiDao.getAgingPegawai(branch,periode,masterId,reportId,tipeLaporan);
            }
        } catch (HibernateException e) {
            logger.error("[LaporanAkuntansiBoImpl.getSearchLaporanAkuntansiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        return agingList;
    }

    @Override
    public List<PendapatanDTO> getPendapatan(String reportId, String unit, String periode, String tipeLaporan) throws GeneralBOException {
        List<PendapatanDTO> pendapatanDTOList= new ArrayList<>();
        try {
            if (("PDA").equalsIgnoreCase(tipeLaporan)){
                pendapatanDTOList = laporanAkuntansiDao.getPendapatanPerActivityPerDokter(reportId,unit,periode);
            }else if (("PDDOK").equalsIgnoreCase(tipeLaporan)){
                pendapatanDTOList = laporanAkuntansiDao.getPendapatanPerDokterPerActivity(reportId,unit,periode);
            }
        } catch (HibernateException e) {
            logger.error("[LaporanAkuntansiBoImpl.getSearchLaporanAkuntansiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        return pendapatanDTOList;
    }

    @Override
    public List<AkunSettingReportKeuanganArusKas> getArusKas(String reportId, String unit, String periode) throws GeneralBOException {
        List<AkunSettingReportKeuanganArusKas> result = new ArrayList<>();
        List<ImAkunSettingReportArusKasEntity> arusKasList = new ArrayList<>();
        List<AkunSettingReportKeuanganArusKasDetail> keuanganArusKasDetailList = new ArrayList<>();

        try {
            arusKasList = settingReportKeuanganArusKasDao.listReportKeuanganArusKas();
            keuanganArusKasDetailList = laporanAkuntansiDao.getArusKas(unit,periode);
        } catch (HibernateException e) {
            logger.error("[LaporanAkuntansiBoImpl.getArusKas] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please info to your admin..." + e.getMessage());
        }

        for (ImAkunSettingReportArusKasEntity arusKas : arusKasList){
            String[] coaSplit = arusKas.getKodeRekeningAlias().split("\\.");
            if (coaSplit.length==3){
                AkunSettingReportKeuanganArusKas data = new AkunSettingReportKeuanganArusKas();

                data.setLevel1(settingReportKeuanganArusKasDao.getCoaAliasNameByCoaAlias(coaSplit[0]));
                data.setLevel2(settingReportKeuanganArusKasDao.getCoaAliasNameByCoaAlias(coaSplit[0]+"."+coaSplit[1]));

                BigDecimal totalUnit1= BigDecimal.ZERO;
                BigDecimal totalLastSaldoUnit1= BigDecimal.ZERO;
                BigDecimal totalCurSaldoUnit1= BigDecimal.ZERO;
                BigDecimal totalSaldoUnit11TahunLalu = BigDecimal.ZERO;
                BigDecimal totalSaldoUnit12TahunLalu = BigDecimal.ZERO;
                for (AkunSettingReportKeuanganArusKasDetail arusKasDetail : keuanganArusKasDetailList){
                    if (arusKasDetail.getSettingReportArusKasId().equalsIgnoreCase(arusKas.getSettingReportArusKasId())){
                        if ("T".equalsIgnoreCase(arusKasDetail.getOperator())){
                            totalUnit1 = totalUnit1.add(arusKasDetail.getSaldoUnit1());
                            totalLastSaldoUnit1 = totalLastSaldoUnit1.add(arusKasDetail.getLastSaldoUnit1());
                            totalCurSaldoUnit1 = totalCurSaldoUnit1.add(arusKasDetail.getCurSaldoUnit1());
                            totalSaldoUnit11TahunLalu = totalSaldoUnit11TahunLalu.add(arusKasDetail.getSaldoUnit11TahunLalu());
                            totalSaldoUnit12TahunLalu = totalSaldoUnit12TahunLalu.add(arusKasDetail.getSaldoUnit12TahunLalu());
                        }else if ("K".equalsIgnoreCase(arusKasDetail.getOperator())){
                            totalUnit1 = totalUnit1.subtract(arusKasDetail.getSaldoUnit1());
                            totalLastSaldoUnit1 = totalLastSaldoUnit1.subtract(arusKasDetail.getLastSaldoUnit1());
                            totalCurSaldoUnit1 = totalCurSaldoUnit1.subtract(arusKasDetail.getCurSaldoUnit1());
                            totalSaldoUnit11TahunLalu = totalSaldoUnit11TahunLalu.subtract(arusKasDetail.getSaldoUnit11TahunLalu());
                            totalSaldoUnit12TahunLalu = totalSaldoUnit12TahunLalu.subtract(arusKasDetail.getSaldoUnit12TahunLalu());
                        }
                    }
                }

                data.setSettingReportArusKasId(arusKas.getSettingReportArusKasId());
                data.setKodeRekeningAlias(arusKas.getKodeRekeningAlias());
                data.setNamaKodeRekeningAlias(arusKas.getNamaKodeRekeningAlias());
                data.setFlagLabel(arusKas.getFlagLabel());
                data.setSaldoUnit1(totalUnit1);
                data.setLastSaldoUnit1(totalLastSaldoUnit1);
                data.setCurSaldoUnit1(totalCurSaldoUnit1);
                data.setSaldoUnit11TahunLalu(totalSaldoUnit11TahunLalu);
                data.setSaldoUnit12TahunLalu(totalSaldoUnit12TahunLalu);
                result.add(data);
            }
        }
        return result;
    }

    @Override
    public List<AkunSettingReportKeuanganKonsol> getLaporanAkuntansiKonsol(String periode, String branchId1, String branchId2, String branchId3, String branchId4, String branchId5, String branchId6, String branchId7, String branchIdAll){
        List<AkunSettingReportKeuanganKonsol> result = new ArrayList<>();
        List<ImAkunSettingReportKeuanganKonsol> konsolList = new ArrayList<>();
        List<AkunSettingReportKeuanganKonsolDetail> keuanganKonsolDetailList = new ArrayList<>();

        try {
            konsolList = settingReportKeuanganKonsolDao.listReportKeuanganKonsol();
            keuanganKonsolDetailList = laporanAkuntansiDao.getAllDataLaporanAkuntansiKonsol(periode,branchId1,branchId2,branchId3,branchId4,branchId5,branchId6,branchId7,branchIdAll);
        } catch (HibernateException e) {
            logger.error("[LaporanAkuntansiBoImpl.getLaporanAkuntansiKonsol] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please info to your admin..." + e.getMessage());
        }

        for (ImAkunSettingReportKeuanganKonsol konsol : konsolList){
            String[] coaSplit = konsol.getKodeRekeningAlias().split("\\.");
            if (coaSplit.length==3){
                AkunSettingReportKeuanganKonsol data = new AkunSettingReportKeuanganKonsol();

                data.setLevel1(settingReportKeuanganKonsolDao.getCoaAliasNameByCoaAlias(coaSplit[0]));
                data.setLevel2(settingReportKeuanganKonsolDao.getCoaAliasNameByCoaAlias(coaSplit[0]+"."+coaSplit[1]));

                BigDecimal totalUnit1= BigDecimal.ZERO;
                BigDecimal totalUnit2= BigDecimal.ZERO;
                BigDecimal totalUnit3= BigDecimal.ZERO;
                BigDecimal totalUnit4= BigDecimal.ZERO;
                BigDecimal totalUnit5= BigDecimal.ZERO;
                BigDecimal totalUnit6= BigDecimal.ZERO;
                BigDecimal totalUnit7= BigDecimal.ZERO;
                BigDecimal totalUnitAll= BigDecimal.ZERO;

                BigDecimal totalLastSaldoUnit1= BigDecimal.ZERO;
                BigDecimal totalLastSaldoUnit2= BigDecimal.ZERO;
                BigDecimal totalLastSaldoUnit3= BigDecimal.ZERO;
                BigDecimal totalLastSaldoUnit4= BigDecimal.ZERO;
                BigDecimal totalLastSaldoUnit5= BigDecimal.ZERO;
                BigDecimal totalLastSaldoUnit6= BigDecimal.ZERO;
                BigDecimal totalLastSaldoUnit7= BigDecimal.ZERO;
                BigDecimal totalLastSaldoUnitAll= BigDecimal.ZERO;

                BigDecimal totalCurSaldoUnit1= BigDecimal.ZERO;
                BigDecimal totalCurSaldoUnit2= BigDecimal.ZERO;
                BigDecimal totalCurSaldoUnit3= BigDecimal.ZERO;
                BigDecimal totalCurSaldoUnit4= BigDecimal.ZERO;
                BigDecimal totalCurSaldoUnit5= BigDecimal.ZERO;
                BigDecimal totalCurSaldoUnit6= BigDecimal.ZERO;
                BigDecimal totalCurSaldoUnit7= BigDecimal.ZERO;
                BigDecimal totalCurSaldoUnitAll= BigDecimal.ZERO;


                for (AkunSettingReportKeuanganKonsolDetail konsolDetail : keuanganKonsolDetailList){
                    if (konsolDetail.getSettingReportKonsolId().equalsIgnoreCase(konsol.getSettingReportKonsolId())){
                        if ("T".equalsIgnoreCase(konsolDetail.getOperator())){
                            totalUnit1 = totalUnit1.add(konsolDetail.getSaldoUnit1());
                            totalUnit2 = totalUnit2.add(konsolDetail.getSaldoUnit2());
                            totalUnit3 = totalUnit3.add(konsolDetail.getSaldoUnit3());
                            totalUnit4 = totalUnit4.add(konsolDetail.getSaldoUnit4());
                            totalUnit5 = totalUnit5.add(konsolDetail.getSaldoUnit5());
                            totalUnit6 = totalUnit6.add(konsolDetail.getSaldoUnit6());
                            totalUnit7 = totalUnit7.add(konsolDetail.getSaldoUnit7());
                            totalUnitAll = totalUnitAll.add(konsolDetail.getSaldoUnitAll());

                            totalLastSaldoUnit1 = totalLastSaldoUnit1.add(konsolDetail.getLastSaldoUnit1());
                            totalLastSaldoUnit2 = totalLastSaldoUnit2.add(konsolDetail.getLastSaldoUnit2());
                            totalLastSaldoUnit3 = totalLastSaldoUnit3.add(konsolDetail.getLastSaldoUnit3());
                            totalLastSaldoUnit4 = totalLastSaldoUnit4.add(konsolDetail.getLastSaldoUnit4());
                            totalLastSaldoUnit5 = totalLastSaldoUnit5.add(konsolDetail.getLastSaldoUnit5());
                            totalLastSaldoUnit6 = totalLastSaldoUnit6.add(konsolDetail.getLastSaldoUnit6());
                            totalLastSaldoUnit7 = totalLastSaldoUnit7.add(konsolDetail.getLastSaldoUnit7());
                            totalLastSaldoUnitAll = totalLastSaldoUnitAll.add(konsolDetail.getLastSaldoUnitAll());

                            totalCurSaldoUnit1 = totalCurSaldoUnit1.add(konsolDetail.getCurSaldoUnit1());
                            totalCurSaldoUnit2 = totalCurSaldoUnit2.add(konsolDetail.getCurSaldoUnit2());
                            totalCurSaldoUnit3 = totalCurSaldoUnit3.add(konsolDetail.getCurSaldoUnit3());
                            totalCurSaldoUnit4 = totalCurSaldoUnit4.add(konsolDetail.getCurSaldoUnit4());
                            totalCurSaldoUnit5 = totalCurSaldoUnit5.add(konsolDetail.getCurSaldoUnit5());
                            totalCurSaldoUnit6 = totalCurSaldoUnit6.add(konsolDetail.getCurSaldoUnit6());
                            totalCurSaldoUnit7 = totalCurSaldoUnit7.add(konsolDetail.getCurSaldoUnit7());
                            totalCurSaldoUnitAll = totalCurSaldoUnitAll.add(konsolDetail.getCurSaldoUnitAll());


                        }else if ("K".equalsIgnoreCase(konsolDetail.getOperator())){
                            totalUnit1 = totalUnit1.subtract(konsolDetail.getSaldoUnit1());
                            totalUnit2 = totalUnit2.subtract(konsolDetail.getSaldoUnit2());
                            totalUnit3 = totalUnit3.subtract(konsolDetail.getSaldoUnit3());
                            totalUnit4 = totalUnit4.subtract(konsolDetail.getSaldoUnit4());
                            totalUnit5 = totalUnit5.subtract(konsolDetail.getSaldoUnit5());
                            totalUnit6 = totalUnit6.subtract(konsolDetail.getSaldoUnit6());
                            totalUnit7 = totalUnit7.subtract(konsolDetail.getSaldoUnit7());
                            totalUnitAll = totalUnitAll.subtract(konsolDetail.getSaldoUnitAll());

                            totalLastSaldoUnit1 = totalLastSaldoUnit1.subtract(konsolDetail.getLastSaldoUnit1());
                            totalLastSaldoUnit2 = totalLastSaldoUnit2.subtract(konsolDetail.getLastSaldoUnit2());
                            totalLastSaldoUnit3 = totalLastSaldoUnit3.subtract(konsolDetail.getLastSaldoUnit3());
                            totalLastSaldoUnit4 = totalLastSaldoUnit4.subtract(konsolDetail.getLastSaldoUnit4());
                            totalLastSaldoUnit5 = totalLastSaldoUnit5.subtract(konsolDetail.getLastSaldoUnit5());
                            totalLastSaldoUnit6 = totalLastSaldoUnit6.subtract(konsolDetail.getLastSaldoUnit6());
                            totalLastSaldoUnit7 = totalLastSaldoUnit7.subtract(konsolDetail.getLastSaldoUnit7());
                            totalLastSaldoUnitAll = totalLastSaldoUnitAll.subtract(konsolDetail.getLastSaldoUnitAll());

                            totalCurSaldoUnit1 = totalCurSaldoUnit1.subtract(konsolDetail.getCurSaldoUnit1());
                            totalCurSaldoUnit2 = totalCurSaldoUnit2.subtract(konsolDetail.getCurSaldoUnit2());
                            totalCurSaldoUnit3 = totalCurSaldoUnit3.subtract(konsolDetail.getCurSaldoUnit3());
                            totalCurSaldoUnit4 = totalCurSaldoUnit4.subtract(konsolDetail.getCurSaldoUnit4());
                            totalCurSaldoUnit5 = totalCurSaldoUnit5.subtract(konsolDetail.getCurSaldoUnit5());
                            totalCurSaldoUnit6 = totalCurSaldoUnit6.subtract(konsolDetail.getCurSaldoUnit6());
                            totalCurSaldoUnit7 = totalCurSaldoUnit7.subtract(konsolDetail.getCurSaldoUnit7());
                            totalCurSaldoUnitAll = totalCurSaldoUnitAll.subtract(konsolDetail.getCurSaldoUnitAll());

                        }
                    }
                }

                data.setSettingReportKonsolId(konsol.getSettingReportKonsolId());
                data.setKodeRekeningAlias(konsol.getKodeRekeningAlias());
                data.setNamaKodeRekeningAlias(konsol.getNamaKodeRekeningAlias());
                data.setFlagLabel(konsol.getFlagLabel());
                data.setSaldoUnit1(totalUnit1);
                data.setSaldoUnit2(totalUnit2);
                data.setSaldoUnit3(totalUnit3);
                data.setSaldoUnit4(totalUnit4);
                data.setSaldoUnit5(totalUnit5);
                data.setSaldoUnit6(totalUnit6);
                data.setSaldoUnit7(totalUnit7);
                data.setSaldoUnitAll(totalUnitAll);

                data.setLastSaldoUnit1(totalLastSaldoUnit1);
                data.setLastSaldoUnit2(totalLastSaldoUnit2);
                data.setLastSaldoUnit3(totalLastSaldoUnit3);
                data.setLastSaldoUnit4(totalLastSaldoUnit4);
                data.setLastSaldoUnit5(totalLastSaldoUnit5);
                data.setLastSaldoUnit6(totalLastSaldoUnit6);
                data.setLastSaldoUnit7(totalLastSaldoUnit7);
                data.setLastSaldoUnitAll(totalLastSaldoUnitAll);

                data.setCurSaldoUnit1(totalCurSaldoUnit1);
                data.setCurSaldoUnit2(totalCurSaldoUnit2);
                data.setCurSaldoUnit3(totalCurSaldoUnit3);
                data.setCurSaldoUnit4(totalCurSaldoUnit4);
                data.setCurSaldoUnit5(totalCurSaldoUnit5);
                data.setCurSaldoUnit6(totalCurSaldoUnit6);
                data.setCurSaldoUnit7(totalCurSaldoUnit7);
                data.setCurSaldoUnitAll(totalCurSaldoUnitAll);

                result.add(data);
            }
        }

        return result;
    }
    @Override
    public List<AkunSettingReportKeuanganKonsol> getLaporanAkuntansiKonsolUnit(String periode, String branchId1){
        List<AkunSettingReportKeuanganKonsol> result = new ArrayList<>();
        List<ImAkunSettingReportKeuanganKonsol> konsolList = new ArrayList<>();
        List<AkunSettingReportKeuanganKonsolDetail> keuanganKonsolDetailList = new ArrayList<>();

        try {
            konsolList = settingReportKeuanganKonsolDao.listReportKeuanganKonsol();
            keuanganKonsolDetailList = laporanAkuntansiDao.getDataLaporanAkuntansiKonsolUnit(periode,branchId1);
        } catch (HibernateException e) {
            logger.error("[LaporanAkuntansiBoImpl.getLaporanAkuntansiKonsol] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please info to your admin..." + e.getMessage());
        }

        for (ImAkunSettingReportKeuanganKonsol konsol : konsolList){
            String[] coaSplit = konsol.getKodeRekeningAlias().split("\\.");
            if (coaSplit.length==3){
                AkunSettingReportKeuanganKonsol data = new AkunSettingReportKeuanganKonsol();

                data.setLevel1(settingReportKeuanganKonsolDao.getCoaAliasNameByCoaAlias(coaSplit[0]));
                data.setLevel2(settingReportKeuanganKonsolDao.getCoaAliasNameByCoaAlias(coaSplit[0]+"."+coaSplit[1]));

                BigDecimal totalUnit1= BigDecimal.ZERO;
                BigDecimal totalLastSaldoUnit1= BigDecimal.ZERO;
                BigDecimal totalCurSaldoUnit1= BigDecimal.ZERO;
                BigDecimal totalSaldoUnit11TahunLalu = BigDecimal.ZERO;
                BigDecimal totalSaldoUnit12TahunLalu = BigDecimal.ZERO;
                for (AkunSettingReportKeuanganKonsolDetail konsolDetail : keuanganKonsolDetailList){
                    if (konsolDetail.getSettingReportKonsolId().equalsIgnoreCase(konsol.getSettingReportKonsolId())){
                        if ("T".equalsIgnoreCase(konsolDetail.getOperator())){
                            totalUnit1 = totalUnit1.add(konsolDetail.getSaldoUnit1());
                            totalLastSaldoUnit1 = totalLastSaldoUnit1.add(konsolDetail.getLastSaldoUnit1());
                            totalCurSaldoUnit1 = totalCurSaldoUnit1.add(konsolDetail.getCurSaldoUnit1());
                            totalSaldoUnit11TahunLalu = totalSaldoUnit11TahunLalu.add(konsolDetail.getSaldoUnit11TahunLalu());
                            totalSaldoUnit12TahunLalu = totalSaldoUnit12TahunLalu.add(konsolDetail.getSaldoUnit12TahunLalu());
                        }else if ("K".equalsIgnoreCase(konsolDetail.getOperator())){
                            totalUnit1 = totalUnit1.subtract(konsolDetail.getSaldoUnit1());
                            totalLastSaldoUnit1 = totalLastSaldoUnit1.subtract(konsolDetail.getLastSaldoUnit1());
                            totalCurSaldoUnit1 = totalCurSaldoUnit1.subtract(konsolDetail.getCurSaldoUnit1());
                            totalSaldoUnit11TahunLalu = totalSaldoUnit11TahunLalu.subtract(konsolDetail.getSaldoUnit11TahunLalu());
                            totalSaldoUnit12TahunLalu = totalSaldoUnit12TahunLalu.subtract(konsolDetail.getSaldoUnit12TahunLalu());
                        }
                    }
                }

                data.setSettingReportKonsolId(konsol.getSettingReportKonsolId());
                data.setKodeRekeningAlias(konsol.getKodeRekeningAlias());
                data.setNamaKodeRekeningAlias(konsol.getNamaKodeRekeningAlias());
                data.setFlagLabel(konsol.getFlagLabel());
                data.setSaldoUnit1(totalUnit1);
                data.setLastSaldoUnit1(totalLastSaldoUnit1);
                data.setCurSaldoUnit1(totalCurSaldoUnit1);
                data.setSaldoUnit11TahunLalu(totalSaldoUnit11TahunLalu);
                data.setSaldoUnit12TahunLalu(totalSaldoUnit12TahunLalu);
                result.add(data);
            }
        }
        return result;
    }
    @Override
    public LaporanAkuntansi getById(String reportId){
        LaporanAkuntansi result;
        ItLaporanAkuntansiEntity laporanAkuntansiEntity;
        try {
            laporanAkuntansiEntity = laporanAkuntansiDao.getById("laporanAkuntansiId",reportId);
        } catch (HibernateException e) {
            logger.error("[LaporanAkuntansiBoImpl.getSearchLaporanAkuntansiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        result = convertEntity(laporanAkuntansiEntity);

        return result;
    }


    @Override
    public String levelKodeRekening(String reportId) throws GeneralBOException {
        String level="";
        try {
            level = laporanAkuntansiDao.getLevelKodeRekening(reportId);
        } catch (HibernateException e) {
            logger.error("[LaporanAkuntansiBoImpl.getSearchLaporanAkuntansiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        return level;
    }

    @Override
    public String getKodeRekeningkas() throws GeneralBOException {
        String kodeRekeningKas="";
        try {
            kodeRekeningKas = kodeRekeningDao.getKodeRekeningKas()+"%";
        } catch (HibernateException e) {
            logger.error("[LaporanAkuntansiBoImpl.getSearchLaporanAkuntansiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        return kodeRekeningKas;
    }

    private LaporanAkuntansi convertEntity (ItLaporanAkuntansiEntity laporanAkuntansiEntity){
        LaporanAkuntansi result = new LaporanAkuntansi();
        result.setLaporanAkuntansiId(laporanAkuntansiEntity.getLaporanAkuntansiId());
        result.setLaporanAkuntansiName(laporanAkuntansiEntity.getLaporanAkuntansiName());
        result.setAdaTipeLaporan(laporanAkuntansiEntity.getAdaTipeLaporan());

        return result;
    }

    @Override
    public List<BudgettingDTO> getBudgetting(String tipeLaporan, String unit, String tahun){
        logger.info("[LaporanAkuntansiBoImpl.getBudgetting] start process >>>");
        List<BudgettingDTO> resultList = new ArrayList<>();
        Budgeting budgetingStatus = findLastStatus(unit, tahun);

        if ("B".equalsIgnoreCase(tipeLaporan)){
            resultList = laporanAkuntansiDao.getBudgetting(unit,tahun,budgetingStatus.getStatus());
        }else if ("BPS".equalsIgnoreCase(tipeLaporan)){
            List<BudgettingDTO> resultListTmp = new ArrayList<>();
            resultListTmp = laporanAkuntansiDao.getBudgettingPerSemester(unit,tahun,budgetingStatus.getStatus());
            for (BudgettingDTO budgettingDTO : resultListTmp){
                if (budgettingDTO.getTipe()==null){
                    resultList.add(budgettingDTO);
                }else {
                    if ("semester1".equalsIgnoreCase(budgettingDTO.getTipe())){
                        for (BudgettingDTO budgettingDTOSem2 : resultListTmp){
                            if (budgettingDTO.getNoBudgetting().equalsIgnoreCase(budgettingDTOSem2.getNoBudgetting())&&"semester2".equalsIgnoreCase(budgettingDTOSem2.getTipe())){
                                budgettingDTO.setQtySem2(budgettingDTOSem2.getQty());
                                budgettingDTO.setNilaiSem2(budgettingDTOSem2.getNilai());
                                budgettingDTO.setSubTotalSem2(budgettingDTOSem2.getSubTotal());
                                break;
                            }
                        }
                        resultList.add(budgettingDTO);
                    }
                }
            }
        } else if ("BPQ".equalsIgnoreCase(tipeLaporan)){
            List<BudgettingDTO> resultListTmp = new ArrayList<>();
            resultListTmp = laporanAkuntansiDao.getBudgettingPerQuartal(unit,tahun,budgetingStatus.getStatus());
            for (BudgettingDTO budgettingDTO : resultListTmp){
                if (budgettingDTO.getTipe()==null){
                    resultList.add(budgettingDTO);
                }else {
                    if ("quartal1".equalsIgnoreCase(budgettingDTO.getTipe())){
                        for (BudgettingDTO budgettingDTOQuartal : resultListTmp){
                            if (budgettingDTO.getNoBudgetting().equalsIgnoreCase(budgettingDTOQuartal.getNoBudgetting())&&"quartal1".equalsIgnoreCase(budgettingDTOQuartal.getTipe())){
                                budgettingDTO.setSubTotalQ1(budgettingDTOQuartal.getSubTotal());
                            }else if (budgettingDTO.getNoBudgetting().equalsIgnoreCase(budgettingDTOQuartal.getNoBudgetting())&&"quartal2".equalsIgnoreCase(budgettingDTOQuartal.getTipe())){
                                budgettingDTO.setSubTotalQ2(budgettingDTOQuartal.getSubTotal());
                            }else if (budgettingDTO.getNoBudgetting().equalsIgnoreCase(budgettingDTOQuartal.getNoBudgetting())&&"quartal3".equalsIgnoreCase(budgettingDTOQuartal.getTipe())){
                                budgettingDTO.setSubTotalQ3(budgettingDTOQuartal.getSubTotal());
                            }else if (budgettingDTO.getNoBudgetting().equalsIgnoreCase(budgettingDTOQuartal.getNoBudgetting())&&"quartal4".equalsIgnoreCase(budgettingDTOQuartal.getTipe())){
                                budgettingDTO.setSubTotalQ4(budgettingDTOQuartal.getSubTotal());
                            }
                        }
                        resultList.add(budgettingDTO);
                    }
                }
            }
        } else if ("BPB".equalsIgnoreCase(tipeLaporan)){
            List<BudgettingDTO> resultListTmp = new ArrayList<>();
            List<ImKodeRekeningEntity> kodeRekeningEntityList = kodeRekeningDao.getKodeRekeningListAsc("");
            resultListTmp = laporanAkuntansiDao.getBudgettingPerBulan(unit,tahun,budgetingStatus.getStatus());
            for (ImKodeRekeningEntity kodeRekening : kodeRekeningEntityList){
                BudgettingDTO data = new BudgettingDTO();
                String kodeGrup = kodeRekening.getKodeRekening().split("\\.")[0];
                data.setGrup(kodeRekeningDao.getNamaRekeningByCoa(kodeGrup));
                data.setKodeRekening(kodeRekening.getKodeRekening());
                data.setKodeRekeningName(kodeRekening.getNamaKodeRekening());
                data.setSubTotalJanuari(BigDecimal.ZERO);
                data.setSubTotalFebruari(BigDecimal.ZERO);
                data.setSubTotalMaret(BigDecimal.ZERO);
                data.setSubTotalApril(BigDecimal.ZERO);
                data.setSubTotalMei(BigDecimal.ZERO);
                data.setSubTotalJuni(BigDecimal.ZERO);
                data.setSubTotalJuli(BigDecimal.ZERO);
                data.setSubTotalAgustus(BigDecimal.ZERO);
                data.setSubTotalSeptember(BigDecimal.ZERO);
                data.setSubTotalOktober(BigDecimal.ZERO);
                data.setSubTotalNovember(BigDecimal.ZERO);
                data.setSubTotalDesember(BigDecimal.ZERO);

                //generate zero value
                for (BudgettingDTO budgettingDTO : resultListTmp){
                    //set kode rekening
                    if (budgettingDTO.getKodeRekening().equalsIgnoreCase(kodeRekening.getKodeRekening())){
                        data.setCetak(true);
                        data.setNilaiTotal(data.getNilaiTotal());
                    }

                    if (budgettingDTO.getKodeRekening().equalsIgnoreCase(kodeRekening.getKodeRekening())&&"januari".equalsIgnoreCase(budgettingDTO.getTipe())){
                        data.setSubTotalJanuari(budgettingDTO.getSubTotal());
                        break;
                    }else if (budgettingDTO.getKodeRekening().equalsIgnoreCase(kodeRekening.getKodeRekening())&&"februari".equalsIgnoreCase(budgettingDTO.getTipe())){
                        data.setSubTotalFebruari(budgettingDTO.getSubTotal());
                        break;
                    }else if (budgettingDTO.getKodeRekening().equalsIgnoreCase(kodeRekening.getKodeRekening())&&"maret".equalsIgnoreCase(budgettingDTO.getTipe())){
                        data.setSubTotalMaret(budgettingDTO.getSubTotal());
                        break;
                    }else if (budgettingDTO.getKodeRekening().equalsIgnoreCase(kodeRekening.getKodeRekening())&&"april".equalsIgnoreCase(budgettingDTO.getTipe())){
                        data.setSubTotalApril(budgettingDTO.getSubTotal());
                        break;
                    }else if (budgettingDTO.getKodeRekening().equalsIgnoreCase(kodeRekening.getKodeRekening())&&"mei".equalsIgnoreCase(budgettingDTO.getTipe())){
                        data.setSubTotalMei(budgettingDTO.getSubTotal());
                        break;
                    }else if (budgettingDTO.getKodeRekening().equalsIgnoreCase(kodeRekening.getKodeRekening())&&"juni".equalsIgnoreCase(budgettingDTO.getTipe())){
                        data.setSubTotalJuni(budgettingDTO.getSubTotal());
                        break;
                    }else if (budgettingDTO.getKodeRekening().equalsIgnoreCase(kodeRekening.getKodeRekening())&&"juli".equalsIgnoreCase(budgettingDTO.getTipe())){
                        data.setSubTotalJuli(budgettingDTO.getSubTotal());
                        break;
                    }else if (budgettingDTO.getKodeRekening().equalsIgnoreCase(kodeRekening.getKodeRekening())&&"agustus".equalsIgnoreCase(budgettingDTO.getTipe())){
                        data.setSubTotalAgustus(budgettingDTO.getSubTotal());
                        break;
                    }else if (budgettingDTO.getKodeRekening().equalsIgnoreCase(kodeRekening.getKodeRekening())&&"september".equalsIgnoreCase(budgettingDTO.getTipe())){
                        data.setSubTotalSeptember(budgettingDTO.getSubTotal());
                        break;
                    }else if (budgettingDTO.getKodeRekening().equalsIgnoreCase(kodeRekening.getKodeRekening())&&"oktober".equalsIgnoreCase(budgettingDTO.getTipe())){
                        data.setSubTotalOktober(budgettingDTO.getSubTotal());
                        break;
                    }else if (budgettingDTO.getKodeRekening().equalsIgnoreCase(kodeRekening.getKodeRekening())&&"november".equalsIgnoreCase(budgettingDTO.getTipe())){
                        data.setSubTotalNovember(budgettingDTO.getSubTotal());
                        break;
                    }else if (budgettingDTO.getKodeRekening().equalsIgnoreCase(kodeRekening.getKodeRekening())&&"desember".equalsIgnoreCase(budgettingDTO.getTipe())){
                        data.setSubTotalDesember(budgettingDTO.getSubTotal());
                        break;
                    }
                }
                if (data.isCetak()){
                    resultList.add(data);
                }
            }
        }else if ("BCPT".equalsIgnoreCase(tipeLaporan)){
            String tahunLalu = String.valueOf(Integer.parseInt(tahun)-1);
            String tahunLalu2 = String.valueOf(Integer.parseInt(tahun)-2);
            Budgeting budgetingStatusTahunLalu = findLastStatus(unit, tahunLalu);
            Budgeting budgetingStatus2TahunLalu = findLastStatus(unit, tahunLalu2);
            List<BudgettingDTO> resultListTmp = new ArrayList<>();

            resultListTmp = laporanAkuntansiDao.getBudgettingComparingPerTahun(unit,budgetingStatus.getStatus(),tahun,tahunLalu,tahunLalu2,budgetingStatusTahunLalu.getStatus(),budgetingStatus2TahunLalu.getStatus());

            for (BudgettingDTO budgettingDTO : resultListTmp){
                if (tahun.equalsIgnoreCase(budgettingDTO.getTahun())){
                    for (BudgettingDTO budgettingTahun : resultListTmp){
                        if (budgettingDTO.getKodeRekening().equalsIgnoreCase(budgettingTahun.getKodeRekening())&&tahunLalu.equalsIgnoreCase(budgettingTahun.getTahun())){
                            budgettingDTO.setNilaiTotalTahunLalu(budgettingTahun.getNilaiTotal());
                        }else if (budgettingDTO.getKodeRekening().equalsIgnoreCase(budgettingTahun.getKodeRekening())&&tahunLalu2.equalsIgnoreCase(budgettingTahun.getTahun())){
                            budgettingDTO.setNilaiTotal2TahunLalu(budgettingTahun.getNilaiTotal());
                        }
                    }
                    resultList.add(budgettingDTO);
                }
            }
        } else if ("BCR".equalsIgnoreCase(tipeLaporan)){
            List<BudgettingDTO> resultListTmp = new ArrayList<>();
            resultListTmp = laporanAkuntansiDao.getBudgettingComparingRealisasi(unit,budgetingStatus.getStatus(),tahun);
            for (BudgettingDTO budgettingDTO : resultListTmp){
                budgettingDTO.setNilaiTotalSisaBayar(budgettingDTO.getNilaiTotal().subtract(budgettingDTO.getNilaiTotalRealisasi()));
                resultList.add(budgettingDTO);
            }
        }else if ("BPD".equalsIgnoreCase(tipeLaporan)){
            List<BudgettingDTO> listDivisi= new ArrayList<>();
            List<BudgettingDTO> listBudgetting= new ArrayList<>();

            listDivisi = laporanAkuntansiDao.getDivisiDariBudgetting(unit,budgetingStatus.getStatus(),tahun);
            listBudgetting = laporanAkuntansiDao.getBudgettingMentah(unit,budgetingStatus.getStatus(),tahun);

            for (BudgettingDTO divisi : listDivisi){
                for (BudgettingDTO budgetting : listBudgetting){
                    BudgettingDTO finalResult = new BudgettingDTO();
                    finalResult.setDivisi(divisi.getDivisi());
                    finalResult.setDivisiId(divisi.getDivisiId());
                    finalResult.setKodeRekening(budgetting.getKodeRekening());
                    finalResult.setKodeRekeningName(budgetting.getKodeRekeningName());
                    finalResult.setGrup(budgetting.getGrup());
                    finalResult.setNoBudgetting(budgetting.getNoBudgetting());
                    int level = kodeRekeningDao.getLevelKodeRekening(budgetting.getKodeRekening());

                    List<BudgettingDTO> listBiayaDivisi = laporanAkuntansiDao.getBudgettingPerDivisi(unit,budgetingStatus.getStatus(),tahun,divisi.getDivisiId(),budgetting.getKodeRekening());
                    for (BudgettingDTO biayaDivisi : listBiayaDivisi){
                        if (level==5){
                            finalResult.setQty(biayaDivisi.getQty());
                            finalResult.setNilai(biayaDivisi.getNilai());
                        }
                        finalResult.setSubTotal(biayaDivisi.getSubTotal());
                    }


                    resultList.add(finalResult);
                }
            }
        }
        logger.info("[LaporanAkuntansiBoImpl.getBudgetting] end process <<<");
        return resultList;
    }

    @Override
    public List<LaporanAkuntansi> getAll() throws GeneralBOException {
        return null;
    }


    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }


    ////=== CLASS STATUS BUDGETING START ===////
    class StatusBudgeting{

        String statusDetail;
        String status;
        Integer idx;

        public String getStatusDetail() {
            return statusDetail;
        }

        public void setStatusDetail(String statusDetail) {
            this.statusDetail = statusDetail;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getIdx() {
            return idx;
        }

        public void setIdx(Integer idx) {
            this.idx = idx;
        }
    }
    class SortByIdxDesc implements Comparator<StatusBudgeting>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(StatusBudgeting a, StatusBudgeting b)
        {
            return b.getIdx() - a.getIdx();
        }
    }

    ////=== LIST STATUS BIDGETING SORTING BY IDX START ===////
    private List<StatusBudgeting> listOfStatusBudgeting(){

        StatusBudgeting statusBudgeting;
        List<StatusBudgeting> statusBudgetingList = new ArrayList<>();

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("DRAFT");
        statusBudgeting.setStatusDetail("ADD_DRAFT");
        statusBudgeting.setIdx(0);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("DRAFT");
        statusBudgeting.setStatusDetail("EDIT_DRAFT");
        statusBudgeting.setIdx(1);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("DRAFT");
        statusBudgeting.setStatusDetail("APPROVE_DRAFT");
        statusBudgeting.setIdx(2);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("DRAFT");
        statusBudgeting.setStatusDetail("ADJUST_DRAFT");
        statusBudgeting.setIdx(3);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("FINAL");
        statusBudgeting.setStatusDetail("EDIT_FINAL");
        statusBudgeting.setIdx(4);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("FINAL");
        statusBudgeting.setStatusDetail("APPROVE_FINAL");
        statusBudgeting.setIdx(5);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("FINAL");
        statusBudgeting.setStatusDetail("ADJUST_FINAL");
        statusBudgeting.setIdx(6);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("REVISI");
        statusBudgeting.setStatusDetail("EDIT_REVISI");
        statusBudgeting.setIdx(7);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("REVISI");
        statusBudgeting.setStatusDetail("APPROVE_REVISI");
        statusBudgeting.setIdx(8);
        statusBudgetingList.add(statusBudgeting);

        statusBudgeting = new StatusBudgeting();
        statusBudgeting.setStatus("REVISI");
        statusBudgeting.setStatusDetail("ADJUST_REVISI");
        statusBudgeting.setIdx(9);
        statusBudgetingList.add(statusBudgeting);

        Collections.sort(statusBudgetingList, new SortByIdxDesc());
        return statusBudgetingList;
    }

    public Budgeting findLastStatus(String branchId, String tahun){

        Budgeting budgeting = new Budgeting();
        for (StatusBudgeting statusBudgeting : listOfStatusBudgeting()){
            Boolean foundBudgeting = budgetingDao.checkIfSameStatus(branchId, tahun, statusBudgeting.getStatusDetail());
            if (foundBudgeting){
                budgeting.setStatus(statusBudgeting.getStatusDetail());
                String[] arrStatus = statusBudgeting.getStatusDetail().split("_", 2);
                String typeTrans = arrStatus[0].toString();
                if ("APPROVE".equalsIgnoreCase(typeTrans) || "ADJUST".equalsIgnoreCase(typeTrans)){
                    budgeting.setApproveFlag("Y");
                }
                break;
            }
        }
        return budgeting;
    }

    @Override
    public List<NeracaSaldoDTO> getListNeracaSaldo(String reportId, String periode, String branchId){
        return laporanAkuntansiDao.getListNeracaSaldo(reportId,periode,branchId);
    }

    @Override
    public List<NeracaSaldoDTO> getListNeracaMutasi(String reportId, String periode, String branchId){
        return laporanAkuntansiDao.getListNeracaMutasi(reportId,periode,branchId);
    }
    @Override
    public List<IkhtisarBukuBesarDTO> getListIkhitisarBukuBesar(String reportId, String periode, String branchId){
        return laporanAkuntansiDao.getListIkhtisarBukuBesarPerBukuBantu(reportId,periode,branchId);
    }

    @Override
    public List<KartuBukuBesarPerBukuBantuDTO> getListKartuBukuBesar(String reportId, String periode, String branchId, String kodeRekening, String nomorMaster, String tipe, Date tanggalDari, Date tanggalSampai){
        return laporanAkuntansiDao.getListKartuBukuBesarPerBukuBantu(reportId,periode,branchId,kodeRekening,nomorMaster,tipe,tanggalDari,tanggalSampai);
    }

    @Override
    public BigDecimal saldoAwalKodeRekening(String branchId, String kodeRekening, String masterId, String periode){
        return laporanAkuntansiDao.getSaldoAwal(branchId,kodeRekening,masterId,periode);
    }

    @Override
    public BigDecimal saldoAwalKodeRekeningByTanggal (String branchId,String kodeRekening,String masterId , String periode,String tanggalAwal){
        return laporanAkuntansiDao.getSaldoAwalByTanggal(branchId,kodeRekening,masterId,periode,tanggalAwal);
    }
}