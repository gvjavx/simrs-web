package com.neurix.akuntansi.transaksi.laporanAkuntansi.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
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
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.util.*;


public class LaporanAkuntansiBoImpl implements LaporanAkuntansiBo {

    protected static transient Logger logger = Logger.getLogger(LaporanAkuntansiBoImpl.class);
    private LaporanAkuntansiDao laporanAkuntansiDao;
    private PersonilPositionDao personilPositionDao;
    private BiodataDao biodataDao;
    private KodeRekeningDao kodeRekeningDao;
    private SettingReportKeuanganKonsolDao settingReportKeuanganKonsolDao;
    private BudgetingDao budgetingDao;

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

        //untuk manager keuangan
        managerList=personilPositionDao.getPersonilPositionByUnitdanPosisi(branchId,"201");
        if (managerList.size()!=0){
            for (ItPersonilPositionEntity manager : managerList){
                ImBiodataEntity biodataEntity = biodataDao.getById("nip",manager.getNip());
                nama.setNipManagerKeuangan(biodataEntity.getNip());
                nama.setNamaManagerKeuangan(biodataEntity.getNamaPegawai());
            }
        }
        //untuk general manager
        managerList=personilPositionDao.getPersonilPositionByUnitdanPosisi(branchId,"4");
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
    public List<ArusKasDTO> getArusKas(String reportId, String unit, String periode, String tipeLaporan) throws GeneralBOException {
        List<ArusKasDTO> arusKasDTOList= new ArrayList<>();
        try {
            if (("AK").equalsIgnoreCase(tipeLaporan)){
                arusKasDTOList = laporanAkuntansiDao.getArusKas(reportId,unit,periode);

                if (arusKasDTOList.size()!=0){
                    List<ArusKasDTO> arusKasDTOListPending= laporanAkuntansiDao.getArusKasBiayaPendingTanggalItu(reportId,unit,periode);
                    List<ArusKasDTO> arusKasDTOListPendingDibayar= laporanAkuntansiDao.getArusKasBiayaPendingDibayarTanggalItu(reportId,unit,periode);

                    if (arusKasDTOListPending.size()!=0){
                        //Menambahkan 1 baris baru yang kosong

                        arusKasDTOList.addAll(arusKasDTOListPending);
                    }

                    if (arusKasDTOListPendingDibayar.size()!=0){
                        //Menambahkan 1 baris baru yang kosong
                        arusKasDTOList.addAll(arusKasDTOListPendingDibayar);
                    }
                }
            }else if (("ARD").equalsIgnoreCase(tipeLaporan)){
                arusKasDTOList = laporanAkuntansiDao.getArusKasDetail(reportId,unit,periode);

                if (arusKasDTOList.size()!=0){
                    List<ArusKasDTO> arusKasDTOListPending= laporanAkuntansiDao.getArusKasDetailBiayaPendingTanggalItu(reportId,unit,periode);
                    List<ArusKasDTO> arusKasDTOListPendingDibayar= laporanAkuntansiDao.getArusKasDetailBiayaPendingDibayarTanggalItu(reportId,unit,periode);

                    if (arusKasDTOListPending.size()!=0){
                        //Menambahkan 1 baris baru yang kosong

                        arusKasDTOList.addAll(arusKasDTOListPending);
                    }

                    if (arusKasDTOListPendingDibayar.size()!=0){
                        //Menambahkan 1 baris baru yang kosong
                        arusKasDTOList.addAll(arusKasDTOListPendingDibayar);
                    }
                }
            }
        } catch (HibernateException e) {
            logger.error("[LaporanAkuntansiBoImpl.getArusKas] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem , please info to your admin..." + e.getMessage());
        }
        return arusKasDTOList;
    }

    @Override
    public List<AkunSettingReportKeuanganKonsol> getLaporanAkuntansiKonsol(String periode, String branchId1, String branchId2, String branchId3, String branchId4, String branchIdAll){
        List<AkunSettingReportKeuanganKonsol> result = new ArrayList<>();
        List<ImAkunSettingReportKeuanganKonsol> konsolList = new ArrayList<>();
        List<AkunSettingReportKeuanganKonsolDetail> keuanganKonsolDetailList = new ArrayList<>();

        try {
            konsolList = settingReportKeuanganKonsolDao.listReportKeuanganKonsol();
            keuanganKonsolDetailList = laporanAkuntansiDao.getAllDataLaporanAkuntansiKonsol(periode,branchId1,branchId2,branchId3,branchId4,branchIdAll);
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
                BigDecimal totalUnitAll= BigDecimal.ZERO;

                BigDecimal totalLastSaldoUnit1= BigDecimal.ZERO;
                BigDecimal totalLastSaldoUnit2= BigDecimal.ZERO;
                BigDecimal totalLastSaldoUnit3= BigDecimal.ZERO;
                BigDecimal totalLastSaldoUnit4= BigDecimal.ZERO;
                BigDecimal totalLastSaldoUnitAll= BigDecimal.ZERO;

                BigDecimal totalCurSaldoUnit1= BigDecimal.ZERO;
                BigDecimal totalCurSaldoUnit2= BigDecimal.ZERO;
                BigDecimal totalCurSaldoUnit3= BigDecimal.ZERO;
                BigDecimal totalCurSaldoUnit4= BigDecimal.ZERO;
                BigDecimal totalCurSaldoUnitAll= BigDecimal.ZERO;

                BigDecimal totalSaldoUnit11TahunLalu= BigDecimal.ZERO;
                BigDecimal totalSaldoUnit21TahunLalu= BigDecimal.ZERO;
                BigDecimal totalSaldoUnit31TahunLalu= BigDecimal.ZERO;
                BigDecimal totalSaldoUnit41TahunLalu= BigDecimal.ZERO;
                BigDecimal totalSaldoUnitAll1TahunLalu= BigDecimal.ZERO;

                BigDecimal totalSaldoUnit12TahunLalu= BigDecimal.ZERO;
                BigDecimal totalSaldoUnit22TahunLalu= BigDecimal.ZERO;
                BigDecimal totalSaldoUnit32TahunLalu= BigDecimal.ZERO;
                BigDecimal totalSaldoUnit42TahunLalu= BigDecimal.ZERO;
                BigDecimal totalSaldoUnitAll2TahunLalu= BigDecimal.ZERO;

                for (AkunSettingReportKeuanganKonsolDetail konsolDetail : keuanganKonsolDetailList){
                    if (konsolDetail.getSettingReportKonsolId().equalsIgnoreCase(konsol.getSettingReportKonsolId())){
                        if ("T".equalsIgnoreCase(konsolDetail.getOperator())){
                            totalUnit1 = totalUnit1.add(konsolDetail.getSaldoUnit1());
                            totalUnit2 = totalUnit2.add(konsolDetail.getSaldoUnit2());
                            totalUnit3 = totalUnit3.add(konsolDetail.getSaldoUnit3());
                            totalUnit4 = totalUnit4.add(konsolDetail.getSaldoUnit4());
                            totalUnitAll = totalUnitAll.add(konsolDetail.getSaldoUnitAll());

                            totalLastSaldoUnit1 = totalLastSaldoUnit1.add(konsolDetail.getLastSaldoUnit1());
                            totalLastSaldoUnit2 = totalLastSaldoUnit2.add(konsolDetail.getLastSaldoUnit2());
                            totalLastSaldoUnit3 = totalLastSaldoUnit3.add(konsolDetail.getLastSaldoUnit3());
                            totalLastSaldoUnit4 = totalLastSaldoUnit4.add(konsolDetail.getLastSaldoUnit4());
                            totalLastSaldoUnitAll = totalLastSaldoUnitAll.add(konsolDetail.getLastSaldoUnitAll());

                            totalCurSaldoUnit1 = totalCurSaldoUnit1.add(konsolDetail.getCurSaldoUnit1());
                            totalCurSaldoUnit2 = totalCurSaldoUnit2.add(konsolDetail.getCurSaldoUnit2());
                            totalCurSaldoUnit3 = totalCurSaldoUnit3.add(konsolDetail.getCurSaldoUnit3());
                            totalCurSaldoUnit4 = totalCurSaldoUnit4.add(konsolDetail.getCurSaldoUnit4());
                            totalCurSaldoUnitAll = totalCurSaldoUnitAll.add(konsolDetail.getCurSaldoUnitAll());

                            totalSaldoUnit11TahunLalu = totalSaldoUnit11TahunLalu.add(konsolDetail.getSaldoUnit11TahunLalu());
                            totalSaldoUnit21TahunLalu = totalSaldoUnit21TahunLalu.add(konsolDetail.getSaldoUnit21TahunLalu());
                            totalSaldoUnit31TahunLalu = totalSaldoUnit31TahunLalu.add(konsolDetail.getSaldoUnit31TahunLalu());
                            totalSaldoUnit41TahunLalu = totalSaldoUnit41TahunLalu.add(konsolDetail.getSaldoUnit41TahunLalu());
                            totalSaldoUnitAll1TahunLalu = totalSaldoUnitAll1TahunLalu.add(konsolDetail.getSaldoUnitAll1TahunLalu());

                            totalSaldoUnit12TahunLalu = totalSaldoUnit12TahunLalu.add(konsolDetail.getSaldoUnit12TahunLalu());
                            totalSaldoUnit22TahunLalu = totalSaldoUnit22TahunLalu.add(konsolDetail.getSaldoUnit22TahunLalu());
                            totalSaldoUnit32TahunLalu = totalSaldoUnit32TahunLalu.add(konsolDetail.getSaldoUnit32TahunLalu());
                            totalSaldoUnit42TahunLalu = totalSaldoUnit42TahunLalu.add(konsolDetail.getSaldoUnit42TahunLalu());
                            totalSaldoUnitAll2TahunLalu = totalSaldoUnitAll2TahunLalu.add(konsolDetail.getSaldoUnitAll2TahunLalu());

                        }else if ("K".equalsIgnoreCase(konsolDetail.getOperator())){
                            totalUnit1 = totalUnit1.subtract(konsolDetail.getSaldoUnit1());
                            totalUnit2 = totalUnit2.subtract(konsolDetail.getSaldoUnit2());
                            totalUnit3 = totalUnit3.subtract(konsolDetail.getSaldoUnit3());
                            totalUnit4 = totalUnit4.subtract(konsolDetail.getSaldoUnit4());
                            totalUnitAll = totalUnitAll.subtract(konsolDetail.getSaldoUnitAll());

                            totalLastSaldoUnit1 = totalLastSaldoUnit1.subtract(konsolDetail.getLastSaldoUnit1());
                            totalLastSaldoUnit2 = totalLastSaldoUnit2.subtract(konsolDetail.getLastSaldoUnit2());
                            totalLastSaldoUnit3 = totalLastSaldoUnit3.subtract(konsolDetail.getLastSaldoUnit3());
                            totalLastSaldoUnit4 = totalLastSaldoUnit4.subtract(konsolDetail.getLastSaldoUnit4());
                            totalLastSaldoUnitAll = totalLastSaldoUnitAll.subtract(konsolDetail.getLastSaldoUnitAll());

                            totalCurSaldoUnit1 = totalCurSaldoUnit1.subtract(konsolDetail.getCurSaldoUnit1());
                            totalCurSaldoUnit2 = totalCurSaldoUnit2.subtract(konsolDetail.getCurSaldoUnit2());
                            totalCurSaldoUnit3 = totalCurSaldoUnit3.subtract(konsolDetail.getCurSaldoUnit3());
                            totalCurSaldoUnit4 = totalCurSaldoUnit4.subtract(konsolDetail.getCurSaldoUnit4());
                            totalCurSaldoUnitAll = totalCurSaldoUnitAll.subtract(konsolDetail.getCurSaldoUnitAll());

                            totalSaldoUnit11TahunLalu = totalSaldoUnit11TahunLalu.subtract(konsolDetail.getSaldoUnit11TahunLalu());
                            totalSaldoUnit21TahunLalu = totalSaldoUnit21TahunLalu.subtract(konsolDetail.getSaldoUnit21TahunLalu());
                            totalSaldoUnit31TahunLalu = totalSaldoUnit31TahunLalu.subtract(konsolDetail.getSaldoUnit31TahunLalu());
                            totalSaldoUnit41TahunLalu = totalSaldoUnit41TahunLalu.subtract(konsolDetail.getSaldoUnit41TahunLalu());
                            totalSaldoUnitAll1TahunLalu = totalSaldoUnitAll1TahunLalu.subtract(konsolDetail.getSaldoUnitAll1TahunLalu());

                            totalSaldoUnit12TahunLalu = totalSaldoUnit12TahunLalu.subtract(konsolDetail.getSaldoUnit12TahunLalu());
                            totalSaldoUnit22TahunLalu = totalSaldoUnit22TahunLalu.subtract(konsolDetail.getSaldoUnit22TahunLalu());
                            totalSaldoUnit32TahunLalu = totalSaldoUnit32TahunLalu.subtract(konsolDetail.getSaldoUnit32TahunLalu());
                            totalSaldoUnit42TahunLalu = totalSaldoUnit42TahunLalu.subtract(konsolDetail.getSaldoUnit42TahunLalu());
                            totalSaldoUnitAll2TahunLalu = totalSaldoUnitAll2TahunLalu.subtract(konsolDetail.getSaldoUnitAll2TahunLalu());
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
                data.setSaldoUnitAll(totalUnitAll);

                data.setLastSaldoUnit1(totalLastSaldoUnit1);
                data.setLastSaldoUnit2(totalLastSaldoUnit2);
                data.setLastSaldoUnit3(totalLastSaldoUnit3);
                data.setLastSaldoUnit4(totalLastSaldoUnit4);
                data.setLastSaldoUnitAll(totalLastSaldoUnitAll);

                data.setCurSaldoUnit1(totalCurSaldoUnit1);
                data.setCurSaldoUnit2(totalCurSaldoUnit2);
                data.setCurSaldoUnit3(totalCurSaldoUnit3);
                data.setCurSaldoUnit4(totalCurSaldoUnit4);
                data.setCurSaldoUnitAll(totalCurSaldoUnitAll);

                data.setSaldoUnit11TahunLalu(totalSaldoUnit11TahunLalu);
                data.setSaldoUnit21TahunLalu(totalSaldoUnit21TahunLalu);
                data.setSaldoUnit31TahunLalu(totalSaldoUnit31TahunLalu);
                data.setSaldoUnit41TahunLalu(totalSaldoUnit41TahunLalu);
                data.setSaldoUnitAll1TahunLalu(totalSaldoUnitAll1TahunLalu);

                data.setSaldoUnit12TahunLalu(totalSaldoUnit12TahunLalu);
                data.setSaldoUnit22TahunLalu(totalSaldoUnit22TahunLalu);
                data.setSaldoUnit32TahunLalu(totalSaldoUnit32TahunLalu);
                data.setSaldoUnit42TahunLalu(totalSaldoUnit42TahunLalu);
                data.setSaldoUnitAll2TahunLalu(totalSaldoUnitAll2TahunLalu);

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
}