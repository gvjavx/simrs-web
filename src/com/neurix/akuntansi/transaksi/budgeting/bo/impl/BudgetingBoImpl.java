package com.neurix.akuntansi.transaksi.budgeting.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.master.dao.MasterDao;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.transaksi.budgeting.bo.BudgetingBo;
import com.neurix.akuntansi.transaksi.budgeting.dao.BudgetingDao;
import com.neurix.akuntansi.transaksi.budgeting.dao.BudgetingDetailDao;
import com.neurix.akuntansi.transaksi.budgeting.dao.BudgetingPengadaanDao;
import com.neurix.akuntansi.transaksi.budgeting.model.*;
import com.neurix.akuntansi.transaksi.saldoakhir.dao.SaldoAkhirDao;
import com.neurix.akuntansi.transaksi.saldoakhir.dao.SaldoAkhirDetailDao;
import com.neurix.akuntansi.transaksi.saldoakhir.model.ItAkunSaldoAkhirDetailEntity;
import com.neurix.akuntansi.transaksi.saldoakhir.model.ItAkunSaldoAkhirEntity;
import com.neurix.akuntansi.transaksi.saldoakhir.model.SaldoAkhir;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.dao.LaporanAkuntansiDao;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.company.model.ImBranchesPK;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by reza on 29/04/20.
 */
public class BudgetingBoImpl implements BudgetingBo {
    private static transient Logger logger = Logger.getLogger(BudgetingBoImpl.class);

    private BudgetingDao budgetingDao;
    private BudgetingDetailDao budgetingDetailDao;
    private BudgetingPengadaanDao budgetingPengadaanDao;
    private KodeRekeningDao kodeRekeningDao;
    private BranchDao branchDao;
    private PositionDao positionDao;
    private MasterDao masterDao;
    private LaporanAkuntansiDao laporanAkuntansiDao;
    private SaldoAkhirDao saldoAkhirDao;
    private SaldoAkhirDetailDao saldoAkhirDetailDao;

    public LaporanAkuntansiDao getLaporanAkuntansiDao() {
        return laporanAkuntansiDao;
    }

    public void setLaporanAkuntansiDao(LaporanAkuntansiDao laporanAkuntansiDao) {
        this.laporanAkuntansiDao = laporanAkuntansiDao;
    }

    @Override
    public List<Budgeting> getSearchByCriteria(Budgeting bean) throws GeneralBOException {
        logger.info("[BudgetingBoImpl.getSearchByCriteria] START >>>");

        List<ItAkunBudgetingEntity> budgetingEntities = new ArrayList<>();
        try {
            budgetingEntities = getListBudgetingEntityByCriteria(bean);
        } catch (HibernateException e){
            logger.error("[BudgetingBoImpl.getSearchByCriteria] ERROR. ",e);
            throw new GeneralBOException("[BudgetingBoImpl.getSearchByCriteria] ERROR. ",e);
        }

        List<Budgeting> budgetings = new ArrayList<>();
        if (budgetingEntities.size() > 0){
            Budgeting budgeting;
            for (ItAkunBudgetingEntity budgetingEntity : budgetingEntities){
                budgeting = new Budgeting();
                budgeting.setIdBudgeting(budgetingEntity.getIdBudgeting());
                budgeting.setTahun(budgetingEntity.getTahun());
                budgeting.setBranchId(budgetingEntity.getBranchId());
                budgeting.setRekeningId(budgetingEntity.getRekeningId());
                budgeting.setStatus(budgetingEntity.getStatus());
                budgeting.setNilaiTotal(nullEscape(budgetingEntity.getNilaiTotal()));
                budgeting.setNilaiAwal(nullEscape(budgetingEntity.getNilaiTotal()));
                budgeting.setTipe(budgetingEntity.getTipe());
                budgeting.setSemester1(nullEscape(budgetingEntity.getSemester1()));
                budgeting.setSemester2(nullEscape(budgetingEntity.getSemester2()));
                budgeting.setQuartal1(nullEscape(budgetingEntity.getQuartal1()));
                budgeting.setQuartal2(nullEscape(budgetingEntity.getQuartal2()));
                budgeting.setQuartal3(nullEscape(budgetingEntity.getQuartal3()));
                budgeting.setQuartal4(nullEscape(budgetingEntity.getQuartal4()));
                budgeting.setSelisih(nullEscape(budgetingEntity.getSelisih()));
                budgeting.setApproveFlag(budgetingEntity.getApproveFlag());
                budgeting.setNoTrans(budgetingEntity.getNoTrans());
                if (budgeting.getApproveFlag() != null){
                    budgeting.setNoBudgeting(budgetingEntity.getNoBudgeting());
                } else {
                    String[] arrStatus = budgeting.getStatus().split("_",2);
                    String statusTrans = arrStatus[1].toString();
                    budgeting.setNoBudgeting(noBudgetingSebelumnya(statusTrans,budgetingEntity.getTahun(),budgetingEntity.getBranchId(),budgetingEntity.getRekeningId()));
                }
                budgeting.setApproveWho(budgetingEntity.getApproveWho());
                budgeting.setApproveTime(budgetingEntity.getApproveTime());
                budgeting.setFlag(budgetingEntity.getFlag());
                budgeting.setAction(budgetingEntity.getAction());
                budgeting.setCreatedDate(budgetingEntity.getCreatedDate());
                budgeting.setCreatedWho(budgetingEntity.getCreatedWho());
                budgeting.setLastUpdate(budgetingEntity.getLastUpdate());
                budgeting.setLastUpdateWho(budgetingEntity.getLastUpdateWho());
                budgeting.setNilaiDraf(getSumNilaiTotalByStatus(budgetingEntity.getRekeningId(), budgetingEntity.getBranchId(), budgetingEntity.getTahun(), "DRAFT", "Y"));
                budgeting.setNilaiFinal(getSumNilaiTotalByStatus(budgetingEntity.getRekeningId(), budgetingEntity.getBranchId(), budgetingEntity.getTahun(), "FINAL", "Y"));
                budgeting.setNilaiRevisi(getSumNilaiTotalByStatus(budgetingEntity.getRekeningId(), budgetingEntity.getBranchId(), budgetingEntity.getTahun(), "REVISI", "Y"));

                budgeting.setJanuari(nullEscape(budgetingEntity.getJanuari()));
                budgeting.setFebruari(nullEscape(budgetingEntity.getFebruari()));
                budgeting.setMaret(nullEscape(budgetingEntity.getMaret()));
                budgeting.setApril(nullEscape(budgetingEntity.getApril()));
                budgeting.setMei(nullEscape(budgetingEntity.getMei()));
                budgeting.setJuni(nullEscape(budgetingEntity.getJuni()));
                budgeting.setJuli(nullEscape(budgetingEntity.getJuli()));
                budgeting.setAgustus(nullEscape(budgetingEntity.getAgustus()));
                budgeting.setSeptember(nullEscape(budgetingEntity.getSeptember()));
                budgeting.setOktober(nullEscape(budgetingEntity.getOktober()));
                budgeting.setNovember(nullEscape(budgetingEntity.getNovember()));
                budgeting.setDesember(nullEscape(budgetingEntity.getDesember()));

                // kode rekening;
                ImKodeRekeningEntity kodeRekeningEntity = getEntityKoderekeningById(budgetingEntity.getRekeningId());
                if (kodeRekeningEntity != null){
                    budgeting.setKodeRekening(kodeRekeningEntity.getKodeRekening());
                    budgeting.setNamaKodeRekening(kodeRekeningEntity.getNamaKodeRekening());
                    budgeting.setLevel(kodeRekeningEntity.getLevel());
                    budgeting.setStLevel(kodeRekeningEntity.getLevel() == null ? "" : kodeRekeningEntity.getLevel().toString());
                    budgeting.setParentId(kodeRekeningEntity.getParentId());
                    budgeting.setTipeCoa(kodeRekeningEntity.getTipeRekeningId());
                    budgeting.setFlagDivisi(kodeRekeningEntity.getFlagDivisi());
                    budgeting.setFlagMaster(kodeRekeningEntity.getFlagMaster());
                }

                // mencari list periode;
                String saldoAkhirId = "";
                List<BudgetingPeriode> budgetingPeriodes = new ArrayList<>();
                SaldoAkhir saldoPeriod = budgetingDao.getSaldoAkhirLastPeriod(budgetingEntity.getTahun(), budgetingEntity.getRekeningId(), budgetingEntity.getBranchId());
                if (saldoPeriod != null){
                    saldoAkhirId = saldoPeriod.getSaldoAkhirId();
                    BudgetingPeriode budgetingPeriode = new BudgetingPeriode();
                    for (BudgetingPeriode periode : budgetingPeriode.getListBudgetingPeriode()){
                        if (saldoPeriod.getBulan().compareTo(periode.getBulan()) == 1 || saldoPeriod.getBulan().compareTo(periode.getBulan()) == 0){
                            budgetingPeriodes.add(periode);
                        }
                    }
                budgeting.setListPeriode(budgetingPeriodes);
                // list periode end;

                }

                // mencari branch data
                if (!"".equalsIgnoreCase(budgeting.getBranchId())){
                    ImBranchesPK branchesPK = new ImBranchesPK();
                    branchesPK.setId(budgeting.getBranchId());
                    ImBranches imBranches = branchDao.getById(branchesPK, "Y");
                    if (imBranches != null){
                        budgeting.setBranchName(imBranches.getBranchName());
                    }
                }

                // mencari jumlah saldo yang telah ditutup pada rekening id
                BigDecimal saldoAkhir = new BigDecimal(0);
                BudgetingPeriode budgetingPeriode = new BudgetingPeriode();
                for (BudgetingPeriode periode : budgetingPeriode.getListBudgetingPeriode()){
                    Map hsCriteria = new HashMap();
                    hsCriteria.put("branch_id", budgeting.getBranchId());
                    hsCriteria.put("periode", periode.getBulan() + "-" + budgeting.getTahun());
                    hsCriteria.put("rekening_id", budgeting.getRekeningId());
                    if (!"".equalsIgnoreCase(saldoAkhirId)){
                        hsCriteria.put("saldo_akhir_id", saldoAkhirId);
                    }

                    List<ItAkunSaldoAkhirEntity> saldoAkhirEntities = saldoAkhirDao.getByCriteria(hsCriteria);
                    if (saldoAkhirEntities.size() > 0){
                        for (ItAkunSaldoAkhirEntity saldoAkhirEntity : saldoAkhirEntities){
                            saldoAkhir = saldoAkhir.add(saldoAkhirEntity.getSaldo());
                        }
                    }
                }
                BigDecimal selisihSaldoAkhir = budgeting.getNilaiTotal().subtract(saldoAkhir);
                budgeting.setSaldoAkhir(saldoAkhir);
                budgeting.setSelisihSaldoAkhir(selisihSaldoAkhir);
                // mencari saldo akhir end;

                // set to list
                budgetings.add(budgeting);
            }
        }

        // short berdasarakan kodeRekening
        Collections.sort(budgetings, Budgeting.getKodeRekeningSorting());

        logger.info("[BudgetingBoImpl.getSearchByCriteria] END <<<");
        return budgetings;
    }

    private String noBudgetingSebelumnya(String status, String tahun, String branchId, String rekeningId){
        String noBudgeting = "";
        if (!"DRAFT".equalsIgnoreCase(status)){
            if ("FINAL".equalsIgnoreCase(status)){
                status = "DRAFT";
            } else if ("REVISI".equalsIgnoreCase(status)){
                status = "FINAL";
            }
            noBudgeting = budgetingDao.getNoSebelumnya(tahun,branchId,rekeningId,status);
        }
        return noBudgeting;
    }

    private BigDecimal nullEscape(BigDecimal nilai){
        return nilai == null ? new BigDecimal(0) : nilai;
    }

    private BigDecimal getSumNilaiTotalByStatus(String rekeningId, String branchId, String tahun, String status, String approveFlag){
        return budgetingDao.getSumNilaiByStatus(rekeningId, branchId, tahun, status, approveFlag);
    }

    public List<ItAkunBudgetingEntity> getListBudgetingEntityByCriteria(Budgeting bean){

        logger.info("[BudgetingBoImpl.getListBudgetingEntityByCriteria] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getTahun() != null){
            hsCriteria.put("tahun", bean.getTahun());
        }
        if (bean.getBranchId() != null){
            hsCriteria.put("branch_id", bean.getBranchId());
        }
        if (bean.getRekeningId() != null){
            hsCriteria.put("rekening_id", bean.getRekeningId());
        }
        if (bean.getStatus() != null){
            hsCriteria.put("status", bean.getStatus());
        }
        if (bean.getTipe() != null){
            hsCriteria.put("tipe", bean.getTipe());
        }
        if (bean.getApproveFlag() != null){
            hsCriteria.put("approve_flag", bean.getApproveFlag());
        }
        if (bean.getBranchId() != null){
            hsCriteria.put("branch_id", bean.getBranchId());
        }
        if (bean.getIdBudgeting() != null){
            hsCriteria.put("id_budgeting", bean.getIdBudgeting());
        }

        List<ItAkunBudgetingEntity> budgetingEntities = new ArrayList<>();

        try {
            budgetingEntities = budgetingDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[BudgetingBoImpl.getListBudgetingEntityByCriteria] ERROR. ",e);
            throw new GeneralBOException("[BudgetingBoImpl.getListBudgetingEntityByCriteria] ERROR. ",e);
        }
        logger.info("[BudgetingBoImpl.getListBudgetingEntityByCriteria] END <<<<");
        return budgetingEntities;
    }

    private ImKodeRekeningEntity getEntityKoderekeningById(String id) throws GeneralBOException{
        return kodeRekeningDao.getById("rekeningId", id);
    }

    @Override
    public void saveAddCoaBudgeting(List<Budgeting> budgetingList) throws GeneralBOException {

    }

    @Override
    public void saveBudgetingDetail(List<Budgeting> budgetingList, String type) throws GeneralBOException {

    }

    @Override
    public void saveAllBudgeting(List<Budgeting> budgetingList, String type) throws GeneralBOException {

    }

    @Override
    public Long getlastLevelKodeRekening() {
        return kodeRekeningDao.getLowestLevelKodeRekening();
    }

    @Override
    public Boolean foundWithSameStatus(String tahun, String branchId, String status) {
        return budgetingDao.checkIfSameStatus(branchId, tahun, status);
    }

    @Override
    public String generateBudgetingDetailId() {
        java.util.Date now = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        return "DT" +  f.format(now) + budgetingDetailDao.getNextId();
    }

    @Override
    public String generateBudgetingPengadaan() {
        java.util.Date now = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        return "PN" + f.format(now) + budgetingPengadaanDao.getNextId();
    }

    public String generateBudgetingId() {
        java.util.Date now = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        return "BT" + f.format(now) + budgetingDao.getNextId();
    }

    @Override
    public String checkLastTipeBudgeting() {
        return budgetingDao.checkLastTipeOfBudgeting();
    }

    @Override
    public void saveAddBudgeting(List<Budgeting> budgetingList, List<BudgetingDetail> budgetingDetails, List<BudgetingPengadaan> budgetingPengadaans, String type, Budgeting bean) throws GeneralBOException {
        logger.info("[BudgetingBoImpl.saveAddBudgeting] START >>>");

        if (budgetingList.size() > 0){

            // mencari head coa
            List<String> listHostCoa = new ArrayList<>();
            for (Budgeting child : budgetingList){
                String kode = child.getKodeRekening();
                String[] arrKode = kode.split("\\.", 5);
                String head = arrKode[0].toString();

                if (listHostCoa.size() == 0){
                    listHostCoa.add(head);
                } else {
                    // mencari jika ada;
                    boolean found = false;
                    for (String kodeString : listHostCoa){
                        if (kodeString.equalsIgnoreCase(head)){
                            found = true;
                        }
                    }
                    // jika tidak ditemukan menambahkan list baru
                    if (!found){
                        listHostCoa.add(head);
                    }
                }
            }

            String name = "";
            if ("DRAFT".equalsIgnoreCase(type)){
                name = "DRAFT";
            } else if ("FINAL".equalsIgnoreCase(type)){
                name = "FIN";
            } else {
                name = "REV";
            }


            List<Budgeting> newChilds = new ArrayList<>();
            for (String postCoa : listHostCoa){

                Long level = getlastLevelKodeRekening();
                KodeRekening kodeRekening = new KodeRekening();
                kodeRekening.setLevel(level.longValue());
                kodeRekening.setPostCoa(postCoa);
                List<ImKodeRekeningEntity> kodeRekeningEntities = getListEntityKodeRekening(kodeRekening);
                if (kodeRekeningEntities.size() > 0){
                    for (ImKodeRekeningEntity kodeRekeningEntity : kodeRekeningEntities){

                        ItAkunBudgetingEntity budgetingEntity = new ItAkunBudgetingEntity();
                        budgetingEntity.setIdBudgeting(generateBudgetingId());
                        budgetingEntity.setNoBudgeting(name+"-"+bean.getBranchId()+"-"+bean.getTahun()+"-"+kodeRekeningEntity.getKodeRekening());

                        // modify
                        budgetingEntity.setNoTrans(bean.getBranchId()+"-"+bean.getTahun()+"-"+kodeRekeningEntity.getKodeRekening());
                        //

                        budgetingEntity.setRekeningId(kodeRekeningEntity.getRekeningId());
                        budgetingEntity.setTahun(bean.getTahun());
                        budgetingEntity.setBranchId(bean.getBranchId());
                        budgetingEntity.setStatus(bean.getStatus());
                        budgetingEntity.setTipe(bean.getTipe());
                        budgetingEntity.setNilaiTotal(new BigDecimal(0));
                        budgetingEntity.setSemester1(new BigDecimal(0));
                        budgetingEntity.setSemester2(new BigDecimal(0));
                        budgetingEntity.setQuartal1(new BigDecimal(0));
                        budgetingEntity.setQuartal2(new BigDecimal(0));
                        budgetingEntity.setQuartal3(new BigDecimal(0));
                        budgetingEntity.setQuartal4(new BigDecimal(0));

                        budgetingEntity.setJanuari( new BigDecimal(0));
                        budgetingEntity.setFebruari( new BigDecimal(0));
                        budgetingEntity.setMaret( new BigDecimal(0));
                        budgetingEntity.setApril( new BigDecimal(0));
                        budgetingEntity.setMei( new BigDecimal(0));
                        budgetingEntity.setJuni( new BigDecimal(0));
                        budgetingEntity.setJuli( new BigDecimal(0));
                        budgetingEntity.setAgustus( new BigDecimal(0));
                        budgetingEntity.setSeptember( new BigDecimal(0));
                        budgetingEntity.setOktober( new BigDecimal(0));
                        budgetingEntity.setNovember( new BigDecimal(0));
                        budgetingEntity.setDesember( new BigDecimal(0));

                        Budgeting budgetingNew = new Budgeting();

                        budgetingNew.setIdBudgeting(budgetingEntity.getIdBudgeting());
                        budgetingNew.setNoBudgeting(name+"-"+bean.getBranchId()+"-"+bean.getTahun()+"-"+kodeRekeningEntity.getKodeRekening());

                        // modify
                        budgetingNew.setNoTrans(bean.getBranchId()+"-"+bean.getTahun()+"-"+kodeRekeningEntity.getKodeRekening());
                        //

                        budgetingNew.setRekeningId(kodeRekeningEntity.getRekeningId());
                        budgetingNew.setParentId(kodeRekeningEntity.getParentId());
                        budgetingNew.setTahun(bean.getTahun());
                        budgetingNew.setBranchId(bean.getBranchId());
                        budgetingNew.setStatus(bean.getStatus());
                        budgetingNew.setTipe(bean.getTipe());
                        budgetingNew.setNilaiTotal(new BigDecimal(0));
                        budgetingNew.setSemester1(new BigDecimal(0));
                        budgetingNew.setSemester2(new BigDecimal(0));
                        budgetingNew.setQuartal1(new BigDecimal(0));
                        budgetingNew.setQuartal2(new BigDecimal(0));
                        budgetingNew.setQuartal3(new BigDecimal(0));
                        budgetingNew.setQuartal4(new BigDecimal(0));

                        budgetingNew.setJanuari( new BigDecimal(0));
                        budgetingNew.setFebruari( new BigDecimal(0));
                        budgetingNew.setMaret( new BigDecimal(0));
                        budgetingNew.setApril( new BigDecimal(0));
                        budgetingNew.setMei( new BigDecimal(0));
                        budgetingNew.setJuni( new BigDecimal(0));
                        budgetingNew.setJuli( new BigDecimal(0));
                        budgetingNew.setAgustus( new BigDecimal(0));
                        budgetingNew.setSeptember( new BigDecimal(0));
                        budgetingNew.setOktober( new BigDecimal(0));
                        budgetingNew.setNovember( new BigDecimal(0));
                        budgetingNew.setDesember( new BigDecimal(0));

                        List<Budgeting> budgetings = budgetingList.stream().filter( p -> p.getRekeningId().equalsIgnoreCase(kodeRekeningEntity.getRekeningId())).collect(Collectors.toList());
                        if (budgetings.size() > 0){
                            Budgeting budgeting = budgetings.get(0);
                            budgetingEntity.setNilaiTotal(budgeting.getNilaiTotal());
                            budgetingEntity.setSemester1(budgeting.getSemester1());
                            budgetingEntity.setSemester2(budgeting.getSemester2());
                            budgetingEntity.setQuartal1(budgeting.getQuartal1());
                            budgetingEntity.setQuartal2(budgeting.getQuartal2());
                            budgetingEntity.setQuartal3(budgeting.getQuartal3());
                            budgetingEntity.setQuartal4(budgeting.getQuartal4());

                            budgetingEntity.setJanuari(budgeting.getJanuari());
                            budgetingEntity.setFebruari(budgeting.getFebruari());
                            budgetingEntity.setMaret(budgeting.getMaret());
                            budgetingEntity.setApril(budgeting.getApril());
                            budgetingEntity.setMei(budgeting.getMei());
                            budgetingEntity.setJuni(budgeting.getJuni());
                            budgetingEntity.setJuli(budgeting.getJuli());
                            budgetingEntity.setAgustus(budgeting.getAgustus());
                            budgetingEntity.setSeptember(budgeting.getSeptember());
                            budgetingEntity.setOktober(budgeting.getOktober());
                            budgetingEntity.setNovember(budgeting.getNovember());
                            budgetingEntity.setDesember(budgeting.getDesember());

                            budgetingNew.setNilaiTotal(budgeting.getNilaiTotal());
                            budgetingNew.setSemester1(budgeting.getSemester1());
                            budgetingNew.setSemester2(budgeting.getSemester2());
                            budgetingNew.setQuartal1(budgeting.getQuartal1());
                            budgetingNew.setQuartal2(budgeting.getQuartal2());
                            budgetingNew.setQuartal3(budgeting.getQuartal3());
                            budgetingNew.setQuartal4(budgeting.getQuartal4());

                            budgetingNew.setJanuari(budgeting.getJanuari());
                            budgetingNew.setFebruari(budgeting.getFebruari());
                            budgetingNew.setMaret(budgeting.getMaret());
                            budgetingNew.setApril(budgeting.getApril());
                            budgetingNew.setMei(budgeting.getMei());
                            budgetingNew.setJuni(budgeting.getJuni());
                            budgetingNew.setJuli(budgeting.getJuli());
                            budgetingNew.setAgustus(budgeting.getAgustus());
                            budgetingNew.setSeptember(budgeting.getSeptember());
                            budgetingNew.setOktober(budgeting.getOktober());
                            budgetingNew.setNovember(budgeting.getNovember());
                            budgetingNew.setDesember(budgeting.getDesember());
                        }


                        budgetingEntity.setFlag(bean.getFlag());
                        budgetingEntity.setAction(bean.getAction());
                        budgetingEntity.setCreatedDate(bean.getCreatedDate());
                        budgetingEntity.setCreatedWho(bean.getCreatedWho());
                        budgetingEntity.setLastUpdate(bean.getLastUpdate());
                        budgetingEntity.setLastUpdateWho(bean.getLastUpdateWho());

                        budgetingNew.setFlag(bean.getFlag());
                        budgetingNew.setAction(bean.getAction());
                        budgetingNew.setCreatedDate(bean.getCreatedDate());
                        budgetingNew.setCreatedWho(bean.getCreatedWho());
                        budgetingNew.setLastUpdate(bean.getLastUpdate());
                        budgetingNew.setLastUpdateWho(bean.getLastUpdateWho());

                        try {
                            budgetingDao.addAndSave(budgetingEntity);
                        } catch (HibernateException e){
                            logger.error("[BudgetingBoImpl.saveAddBudgeting] ERROR. ",e);
                            throw new GeneralBOException("[BudgetingBoImpl.saveAddBudgeting] ERROR. "+e);
                        }

                        newChilds.add(budgetingNew);

                        List<BudgetingDetail> details = budgetingDetails.stream().filter( p -> p.getRekeningId().equalsIgnoreCase(kodeRekeningEntity.getRekeningId())).collect(Collectors.toList());
                        if (details.size() > 0){
                            for (BudgetingDetail budgetingDetail : details){

                                ItAkunBudgetingDetailEntity budgetingDetailEntity = new ItAkunBudgetingDetailEntity();
                                budgetingDetailEntity.setIdBudgetingDetail(generateBudgetingDetailId());
                                budgetingDetailEntity.setIdBudgeting(budgetingEntity.getIdBudgeting());

                                if (budgetingDetail != null && !"".equalsIgnoreCase(budgetingDetail.getMasterId())){
                                    budgetingDetailEntity.setMasterId(budgetingDetail.getMasterId());
                                }

                                if (budgetingDetailEntity.getMasterId() != null){
                                    budgetingDetailEntity.setNoBudgetingDetail(budgetingEntity.getNoTrans()+"-"+budgetingDetail.getTipe()+"-"+budgetingDetail.getDivisiId()+"-"+budgetingDetail.getMasterId());
                                } else {
                                    budgetingDetailEntity.setNoBudgetingDetail(budgetingEntity.getNoTrans()+"-"+budgetingDetail.getTipe()+"-"+budgetingDetail.getDivisiId());
                                }

                                budgetingDetailEntity.setNoBudgeting(budgetingEntity.getNoBudgeting());
                                budgetingDetailEntity.setDivisiId(budgetingDetail.getDivisiId());
                                budgetingDetailEntity.setNilai(budgetingDetail.getNilai());
                                budgetingDetailEntity.setQty(budgetingDetail.getQty());
                                budgetingDetailEntity.setSubTotal(budgetingDetail.getSubTotal());
                                budgetingDetailEntity.setTipe(budgetingDetail.getTipe());
                                budgetingDetailEntity.setFlag(bean.getFlag());
                                budgetingDetailEntity.setAction(bean.getAction());
                                budgetingDetailEntity.setCreatedDate(bean.getCreatedDate());
                                budgetingDetailEntity.setCreatedWho(bean.getCreatedWho());
                                budgetingDetailEntity.setLastUpdate(bean.getLastUpdate());
                                budgetingDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                budgetingDetailEntity.setPositionId(budgetingDetail.getPositionId());

                                try {
                                    budgetingDetailDao.addAndSave(budgetingDetailEntity);
                                } catch (HibernateException e){
                                    logger.error("[BudgetingBoImpl.saveAddBudgeting] ERROR. ",e);
                                    throw new GeneralBOException("[BudgetingBoImpl.saveAddBudgeting] ERROR. "+e);
                                }

                                List<BudgetingPengadaan> pengadaans = budgetingPengadaans.stream().filter( p -> p.getIdBudgetingDetail().equalsIgnoreCase(budgetingDetail.getIdBudgetingDetail())).collect(Collectors.toList());
                                if (pengadaans.size() > 0){
                                    for (BudgetingPengadaan budgetingPengadaan : pengadaans){

                                        ItAkunBudgetingPengadaanEntity pengadaanEntity = new ItAkunBudgetingPengadaanEntity();
                                        pengadaanEntity.setIdPengadaan(generateBudgetingPengadaan());
                                        pengadaanEntity.setNoBudgetingDetail(budgetingDetailEntity.getNoBudgetingDetail());
                                        pengadaanEntity.setIdBudgetingDetail(budgetingDetailEntity.getIdBudgetingDetail());
                                        pengadaanEntity.setNamPengadaan(budgetingPengadaan.getNamPengadaan());
                                        pengadaanEntity.setNilai(budgetingPengadaan.getNilai());
                                        pengadaanEntity.setQty(budgetingPengadaan.getQty());
                                        pengadaanEntity.setSubTotal(budgetingPengadaan.getSubTotal());
                                        pengadaanEntity.setTipe(budgetingPengadaan.getTipe());
                                        pengadaanEntity.setFlag(bean.getFlag());
                                        pengadaanEntity.setAction(bean.getAction());
                                        pengadaanEntity.setCreatedDate(bean.getCreatedDate());
                                        pengadaanEntity.setCreatedWho(bean.getCreatedWho());
                                        pengadaanEntity.setLastUpdate(bean.getLastUpdate());
                                        pengadaanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                        if (budgetingPengadaan.getNoPengadaan() != null && !"".equalsIgnoreCase(budgetingPengadaan.getNoPengadaan())){
                                            pengadaanEntity.setNoPengadaan(budgetingPengadaan.getNoPengadaan());
                                        } else {
                                            pengadaanEntity.setNoPengadaan(budgetingDetailEntity.getNoBudgetingDetail()+"-"+pengadaanEntity.getIdPengadaan());
                                        }

                                        try {
                                            budgetingPengadaanDao.addAndSave(pengadaanEntity);
                                        } catch (HibernateException e){
                                            logger.error("[BudgetingBoImpl.saveAddBudgeting] ERROR. ",e);
                                            throw new GeneralBOException("[BudgetingBoImpl.saveAddBudgeting] ERROR. "+e);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                // proses sum parent
                level --;
                List<Budgeting> sumParent = prosesSumPostBudgeting(newChilds, bean, level, name, postCoa);
            }
        }
        logger.info("[BudgetingBoImpl.saveAddBudgeting] END <<<");
    }

    @Override
    public void saveEditBudgeting(List<Budgeting> budgetingList, List<BudgetingDetail> budgetingDetails, List<BudgetingPengadaan> budgetingPengadaans, String statusTrans, String typeTrans, Budgeting bean) throws GeneralBOException {

        logger.info("[BudgetingBoImpl.saveEditBudgeting] START >>>");

        for (Budgeting budgeting : budgetingList){

            ItAkunBudgetingEntity budgetingEntity = budgetingDao.getById("idBudgeting", budgeting.getIdBudgeting());
            if (budgetingEntity != null){
                budgetingEntity.setStatus(bean.getStatus());
                budgetingEntity.setTipe(bean.getTipe());
                budgetingEntity.setNilaiTotal(budgeting.getNilaiTotal());
                budgetingEntity.setSemester1(budgeting.getSemester1());
                budgetingEntity.setSemester2(budgeting.getSemester2());
                budgetingEntity.setQuartal1(budgeting.getQuartal1());
                budgetingEntity.setQuartal2(budgeting.getQuartal2());
                budgetingEntity.setQuartal3(budgeting.getQuartal3());
                budgetingEntity.setQuartal4(budgeting.getQuartal4());

                budgetingEntity.setJanuari(budgeting.getJanuari());
                budgetingEntity.setFebruari(budgeting.getFebruari());
                budgetingEntity.setMaret(budgeting.getMaret());
                budgetingEntity.setApril(budgeting.getApril());
                budgetingEntity.setMei(budgeting.getMei());
                budgetingEntity.setJuni(budgeting.getJuni());
                budgetingEntity.setJuli(budgeting.getJuli());
                budgetingEntity.setAgustus(budgeting.getAgustus());
                budgetingEntity.setSeptember(budgeting.getSeptember());
                budgetingEntity.setOktober(budgeting.getOktober());
                budgetingEntity.setNovember(budgeting.getNovember());
                budgetingEntity.setDesember(budgeting.getDesember());

                budgetingEntity.setFlag(bean.getFlag());
                budgetingEntity.setAction("U");
                budgetingEntity.setLastUpdate(bean.getLastUpdate());
                budgetingEntity.setLastUpdateWho(bean.getLastUpdateWho());

                if ("APPROVE".equalsIgnoreCase(typeTrans)){
                    budgetingEntity.setApproveFlag("Y");
                    budgetingEntity.setApproveWho(bean.getLastUpdateWho());
                    budgetingEntity.setApproveTime(bean.getLastUpdate());
                }

                try {
                    budgetingDao.updateAndSave(budgetingEntity);
                } catch (HibernateException e){
                    logger.error("[BudgetingBoImpl.saveEditBudgeting] ERROR. ",e);
                    throw new GeneralBOException("[BudgetingBoImpl.saveEditBudgeting] ERROR. "+e);
                }

                // budgeting detail
                List<BudgetingDetail> details = budgetingDetails.stream().filter( p -> p.getRekeningId().equalsIgnoreCase(budgetingEntity.getRekeningId())).collect(Collectors.toList());
                if (details.size() > 0){
                    for (BudgetingDetail budgetingDetail : details){

                        ItAkunBudgetingDetailEntity budgetingDetailEntity = budgetingDetailDao.getById("idBudgetingDetail", budgetingDetail.getIdBudgetingDetail());
                        if (budgetingDetailEntity != null){
                            budgetingDetailEntity.setNilai(budgetingDetail.getNilai());
                            budgetingDetailEntity.setQty(budgetingDetail.getQty());
                            budgetingDetailEntity.setSubTotal(budgetingDetail.getSubTotal());
                            budgetingDetailEntity.setTipe(budgetingDetail.getTipe());
                            budgetingDetailEntity.setFlag(bean.getFlag());
                            budgetingDetailEntity.setAction("U");
                            budgetingDetailEntity.setLastUpdate(bean.getLastUpdate());
                            budgetingDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            budgetingDetailEntity.setPositionId(budgetingDetail.getPositionId());

                            try {
                                budgetingDetailDao.updateAndSave(budgetingDetailEntity);
                            } catch (HibernateException e){
                                logger.error("[BudgetingBoImpl.saveEditBudgeting] ERROR. ",e);
                                throw new GeneralBOException("[BudgetingBoImpl.saveEditBudgeting] ERROR. "+e);
                            }

                            // investasi
                            ItAkunBudgetingDetailEntity finalBudgetingDetailEntity = budgetingDetailEntity;
                            List<BudgetingPengadaan> pengadaans = budgetingPengadaans.stream().filter(p -> p.getIdBudgetingDetail().equalsIgnoreCase(finalBudgetingDetailEntity.getIdBudgetingDetail())).collect(Collectors.toList());
                            if (pengadaans.size() > 0){
                                for (BudgetingPengadaan budgetingPengadaan : pengadaans){

                                    ItAkunBudgetingPengadaanEntity pengadaanEntity = budgetingPengadaanDao.getById("idPengadaan", budgetingPengadaan.getIdPengadaan());
                                    if (pengadaanEntity != null){
                                        pengadaanEntity.setNamPengadaan(budgetingPengadaan.getNamPengadaan());
                                        pengadaanEntity.setNilai(budgetingPengadaan.getNilai());
                                        pengadaanEntity.setQty(budgetingPengadaan.getQty());
                                        pengadaanEntity.setSubTotal(budgetingPengadaan.getSubTotal());
                                        pengadaanEntity.setTipe(budgetingPengadaan.getTipe());
                                        pengadaanEntity.setFlag(bean.getFlag());
                                        pengadaanEntity.setAction("U");
                                        pengadaanEntity.setLastUpdate(bean.getLastUpdate());
                                        pengadaanEntity.setLastUpdateWho(bean.getLastUpdateWho());

                                        try {
                                            budgetingPengadaanDao.updateAndSave(pengadaanEntity);
                                        } catch (HibernateException e){
                                            logger.error("[BudgetingBoImpl.saveEditBudgeting] ERROR. ",e);
                                            throw new GeneralBOException("[BudgetingBoImpl.saveEditBudgeting] ERROR. "+e);
                                        }
                                    } else {
                                        pengadaanEntity = new ItAkunBudgetingPengadaanEntity();
                                        pengadaanEntity.setIdPengadaan(budgetingPengadaan.getIdPengadaan());
                                        pengadaanEntity.setIdBudgetingDetail(budgetingDetailEntity.getIdBudgetingDetail());
                                        pengadaanEntity.setNoBudgetingDetail(budgetingDetailEntity.getNoBudgetingDetail());
                                        pengadaanEntity.setNamPengadaan(budgetingPengadaan.getNamPengadaan());
                                        pengadaanEntity.setNilai(budgetingPengadaan.getNilai());
                                        pengadaanEntity.setQty(budgetingPengadaan.getQty());
                                        pengadaanEntity.setSubTotal(budgetingPengadaan.getSubTotal());
                                        pengadaanEntity.setTipe(budgetingPengadaan.getTipe());
                                        pengadaanEntity.setFlag(bean.getFlag());
                                        pengadaanEntity.setAction("U");
                                        pengadaanEntity.setLastUpdate(bean.getLastUpdate());
                                        pengadaanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                        pengadaanEntity.setNoPengadaan(budgetingDetailEntity.getNoBudgetingDetail()+"-"+pengadaanEntity.getIdPengadaan());

                                        try {
                                            budgetingPengadaanDao.updateAndSave(pengadaanEntity);
                                        } catch (HibernateException e){
                                            logger.error("[BudgetingBoImpl.saveEditBudgeting] ERROR. ",e);
                                            throw new GeneralBOException("[BudgetingBoImpl.saveEditBudgeting] ERROR. "+e);
                                        }
                                    }
                                }
                            }

                        } else {

                            //budgeting detail
                            budgetingDetailEntity = new ItAkunBudgetingDetailEntity();
                            budgetingDetailEntity.setIdBudgetingDetail(budgetingDetail.getIdBudgetingDetail());
                            budgetingDetailEntity.setIdBudgeting(budgetingEntity.getIdBudgeting());
                            budgetingDetailEntity.setNoBudgeting(budgetingEntity.getNoBudgeting());
                            if (budgetingDetail.getMasterId() != null && !"".equalsIgnoreCase(budgetingDetail.getMasterId())){
                                budgetingDetailEntity.setNoBudgetingDetail(budgetingEntity.getNoTrans()+"-"+budgetingDetail.getTipe()+"-"+budgetingDetail.getDivisiId()+"-"+budgetingDetail.getMasterId());
                            } else {
                                budgetingDetailEntity.setNoBudgetingDetail(budgetingEntity.getNoTrans()+"-"+budgetingDetail.getTipe()+"-"+budgetingDetail.getDivisiId());
                            }
                            budgetingDetailEntity.setMasterId(budgetingDetail.getMasterId());
                            budgetingDetailEntity.setDivisiId(budgetingDetail.getDivisiId());
                            budgetingDetailEntity.setNilai(budgetingDetail.getNilai());
                            budgetingDetailEntity.setQty(budgetingDetail.getQty());
                            budgetingDetailEntity.setSubTotal(budgetingDetail.getSubTotal());
                            budgetingDetailEntity.setTipe(budgetingDetail.getTipe());
                            budgetingDetailEntity.setFlag(bean.getFlag());
                            budgetingDetailEntity.setAction("C");
                            budgetingDetailEntity.setLastUpdate(bean.getLastUpdate());
                            budgetingDetailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            budgetingDetailEntity.setPositionId(budgetingDetail.getPositionId());

                            try {
                                budgetingDetailDao.addAndSave(budgetingDetailEntity);
                            } catch (HibernateException e){
                                logger.error("[BudgetingBoImpl.saveEditBudgeting] ERROR. ",e);
                                throw new GeneralBOException("[BudgetingBoImpl.saveEditBudgeting] ERROR. "+e);
                            }

                            // investasi
                            ItAkunBudgetingDetailEntity finalBudgetingDetailEntity = budgetingDetailEntity;
                            List<BudgetingPengadaan> pengadaans = budgetingPengadaans.stream().filter(p -> p.getIdBudgetingDetail().equalsIgnoreCase(finalBudgetingDetailEntity.getIdBudgetingDetail())).collect(Collectors.toList());
                            if (pengadaans.size() > 0){
                                for (BudgetingPengadaan budgetingPengadaan : pengadaans){
                                    ItAkunBudgetingPengadaanEntity pengadaanEntity = new ItAkunBudgetingPengadaanEntity();
                                    pengadaanEntity.setIdPengadaan(generateBudgetingPengadaan());
                                    pengadaanEntity.setIdBudgetingDetail(budgetingDetailEntity.getIdBudgetingDetail());
                                    pengadaanEntity.setNoBudgetingDetail(budgetingDetailEntity.getNoBudgetingDetail());
                                    pengadaanEntity.setNamPengadaan(budgetingPengadaan.getNamPengadaan());
                                    pengadaanEntity.setNilai(budgetingPengadaan.getNilai());
                                    pengadaanEntity.setQty(budgetingPengadaan.getQty());
                                    pengadaanEntity.setSubTotal(budgetingPengadaan.getSubTotal());
                                    pengadaanEntity.setTipe(budgetingPengadaan.getTipe());
                                    pengadaanEntity.setFlag(bean.getFlag());
                                    pengadaanEntity.setAction("U");
                                    pengadaanEntity.setLastUpdate(bean.getLastUpdate());
                                    pengadaanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                    pengadaanEntity.setCreatedDate(bean.getCreatedDate());
                                    pengadaanEntity.setCreatedWho(bean.getCreatedWho());
                                    pengadaanEntity.setNoPengadaan(budgetingDetailEntity.getNoBudgetingDetail()+"-"+pengadaanEntity.getIdPengadaan());

                                    try {
                                        budgetingPengadaanDao.updateAndSave(pengadaanEntity);
                                    } catch (HibernateException e){
                                        logger.error("[BudgetingBoImpl.saveEditBudgeting] ERROR. ",e);
                                        throw new GeneralBOException("[BudgetingBoImpl.saveEditBudgeting] ERROR. "+e);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        logger.info("[BudgetingBoImpl.saveEditBudgeting] END <<<");
    }

    public List<Budgeting> prosesSumPostBudgeting(List<Budgeting> budgetings, Budgeting bean, Long level, String name, String postCoa) throws GeneralBOException {

        List<Budgeting> parentBudgetings = new ArrayList<>();

        // jika parameter budgetings sebagai child tidak kosong
        // maka prosess untuk insert, sum, collecting data parrentPeriods;
        if (level.compareTo(Long.valueOf(0)) == 1){

            // pengulangan dari parameter budgetings sebagai child;
            // pengulangan untuk mencari parent dan menghitung jumlah debit kredit pada parent tersebut;
            int i = 0;
            for (Budgeting child : budgetings){

                Budgeting budgeting = new Budgeting();
                budgeting.setRekeningId(child.getParentId());

                ImKodeRekeningEntity kodeRekening = getKodeRekeningById(budgeting.getRekeningId());
                if ( kodeRekening != null && kodeRekening.getRekeningId() != null){
                    budgeting.setParentId(kodeRekening.getParentId());
                    budgeting.setKodeRekening(kodeRekening.getKodeRekening());
                }

                // jika parent_id != null untuk filtering level tertinggi;
                // parent_id = null berarti diatas level tertinggi maka logic if false. tidak dapat lanjut proses;
                if (!"".equalsIgnoreCase(budgeting.getParentId()) && budgeting.getParentId() != null){

                    // jika list parent kosong
                    if (parentBudgetings.size() == 0){

                        budgeting.setNilaiTotal(child.getNilaiTotal());
                        budgeting.setSemester1(child.getSemester1());
                        budgeting.setSemester2(child.getSemester2());
                        budgeting.setQuartal1(child.getQuartal1());
                        budgeting.setQuartal2(child.getQuartal2());
                        budgeting.setQuartal3(child.getQuartal3());
                        budgeting.setQuartal4(child.getQuartal4());

                        budgeting.setJanuari(child.getJanuari());
                        budgeting.setFebruari(child.getFebruari());
                        budgeting.setMaret(child.getMaret());
                        budgeting.setApril(child.getApril());
                        budgeting.setMei(child.getMei());
                        budgeting.setJuni(child.getJuni());
                        budgeting.setJuli(child.getJuli());
                        budgeting.setAgustus(child.getAgustus());
                        budgeting.setSeptember(child.getSeptember());
                        budgeting.setOktober(child.getOktober());
                        budgeting.setNovember(child.getNovember());
                        budgeting.setDesember(child.getDesember());
                        parentBudgetings.add(budgeting);
                        i++;
                    } else {

                        Budgeting minParentPeriod = parentBudgetings.get(i-1);
                        // jika parent index sebelumnya ditemukan parent rekening_id sama dengan child parent_id;
                        // maka dilakukan sum kredit, debit;
                        // kemudian update parent period;
                        if (minParentPeriod.getRekeningId().equalsIgnoreCase(budgeting.getRekeningId())){

                            budgeting.setNilaiTotal(minParentPeriod.getNilaiTotal().add(child.getNilaiTotal()));
                            budgeting.setSemester1(minParentPeriod.getSemester1().add(child.getSemester1()));
                            budgeting.setSemester2(minParentPeriod.getSemester2().add(child.getSemester2()));
                            budgeting.setQuartal1(minParentPeriod.getQuartal1().add(child.getQuartal1()));
                            budgeting.setQuartal2(minParentPeriod.getQuartal2().add(child.getQuartal2()));
                            budgeting.setQuartal3(minParentPeriod.getQuartal3().add(child.getQuartal3()));
                            budgeting.setQuartal4(minParentPeriod.getQuartal4().add(child.getQuartal4()));

                            budgeting.setJanuari(minParentPeriod.getJanuari().add(child.getJanuari()));
                            budgeting.setFebruari(minParentPeriod.getFebruari().add(child.getFebruari()));
                            budgeting.setMaret(minParentPeriod.getMaret().add(child.getMaret()));
                            budgeting.setApril(minParentPeriod.getApril().add(child.getApril()));
                            budgeting.setMei(minParentPeriod.getMei().add(child.getMei()));
                            budgeting.setJuni(minParentPeriod.getJuni().add(child.getJuni()));
                            budgeting.setJuli(minParentPeriod.getJuli().add(child.getJuli()));
                            budgeting.setAgustus(minParentPeriod.getAgustus().add(child.getAgustus()));
                            budgeting.setSeptember(minParentPeriod.getSeptember().add(child.getSeptember()));
                            budgeting.setOktober(minParentPeriod.getOktober().add(child.getOktober()));
                            budgeting.setNovember(minParentPeriod.getNovember().add(child.getNovember()));
                            budgeting.setDesember(minParentPeriod.getDesember().add(child.getDesember()));

                            // update list parrentPeriods;
                            parentBudgetings.remove(minParentPeriod);
                            parentBudgetings.add(budgeting);
                        } else {

                            // jika tidak parent rekening_id tidak sama dengan child parent_id;
                            // maka memasukan object baru pada list parrentPeriods;
                            // dan menambah nilai i untuk index parrentPeriods;
                            budgeting.setNilaiTotal(child.getNilaiTotal());
                            budgeting.setSemester1(child.getSemester1());
                            budgeting.setSemester2(child.getSemester2());
                            budgeting.setQuartal1((child.getQuartal1()));
                            budgeting.setQuartal2((child.getQuartal2()));
                            budgeting.setQuartal3((child.getQuartal3()));
                            budgeting.setQuartal4((child.getQuartal4()));

                            budgeting.setJanuari(child.getJanuari());
                            budgeting.setFebruari(child.getFebruari());
                            budgeting.setMaret(child.getMaret());
                            budgeting.setApril(child.getApril());
                            budgeting.setMei(child.getMei());
                            budgeting.setJuni(child.getJuni());
                            budgeting.setJuli(child.getJuli());
                            budgeting.setAgustus(child.getAgustus());
                            budgeting.setSeptember(child.getSeptember());
                            budgeting.setOktober(child.getOktober());
                            budgeting.setNovember(child.getNovember());
                            budgeting.setDesember(child.getDesember());
                            parentBudgetings.add(budgeting);
                            i++;
                        }
                    }
                }
            }

            List<Budgeting> listOfMapingParents = new ArrayList<>();

            KodeRekening kodeRekening = new KodeRekening();
            kodeRekening.setLevel(level.longValue());
            kodeRekening.setPostCoa(postCoa);
            List<ImKodeRekeningEntity> kodeRekeningEntities = getListEntityKodeRekening(kodeRekening);
            if (kodeRekeningEntities.size() > 0){
                for (ImKodeRekeningEntity kodeRekeningEntity : kodeRekeningEntities){

                    // insert table
                    ItAkunBudgetingEntity budgetingEntity = new ItAkunBudgetingEntity();
                    budgetingEntity.setRekeningId(kodeRekeningEntity.getRekeningId());
                    budgetingEntity.setIdBudgeting(generateBudgetingId());
                    budgetingEntity.setNoBudgeting(name+"-"+bean.getBranchId()+"-"+bean.getTahun()+"-"+kodeRekeningEntity.getKodeRekening());
                    budgetingEntity.setBranchId(bean.getBranchId());
                    budgetingEntity.setTahun(bean.getTahun());
                    budgetingEntity.setStatus(bean.getStatus());
                    budgetingEntity.setTipe(bean.getTipe());
                    budgetingEntity.setNilaiTotal(new BigDecimal(0));
                    budgetingEntity.setSemester1(new BigDecimal(0));
                    budgetingEntity.setSemester2(new BigDecimal(0));
                    budgetingEntity.setQuartal1(new BigDecimal(0));
                    budgetingEntity.setQuartal2(new BigDecimal(0));
                    budgetingEntity.setQuartal3(new BigDecimal(0));
                    budgetingEntity.setQuartal4(new BigDecimal(0));

                    budgetingEntity.setJanuari( new BigDecimal(0));
                    budgetingEntity.setFebruari( new BigDecimal(0));
                    budgetingEntity.setMaret( new BigDecimal(0));
                    budgetingEntity.setApril( new BigDecimal(0));
                    budgetingEntity.setMei( new BigDecimal(0));
                    budgetingEntity.setJuni( new BigDecimal(0));
                    budgetingEntity.setJuli( new BigDecimal(0));
                    budgetingEntity.setAgustus( new BigDecimal(0));
                    budgetingEntity.setSeptember( new BigDecimal(0));
                    budgetingEntity.setOktober( new BigDecimal(0));
                    budgetingEntity.setNovember( new BigDecimal(0));
                    budgetingEntity.setDesember( new BigDecimal(0));

                    budgetingEntity.setFlag(bean.getFlag());
                    budgetingEntity.setAction(bean.getAction());
                    budgetingEntity.setCreatedDate(bean.getCreatedDate());
                    budgetingEntity.setCreatedWho(bean.getCreatedWho());
                    budgetingEntity.setLastUpdate(bean.getLastUpdate());
                    budgetingEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    // untuk diolah lagi
                    Budgeting budgeting = new Budgeting();
                    budgeting.setRekeningId(kodeRekeningEntity.getRekeningId());
                    budgeting.setParentId(kodeRekeningEntity.getParentId());
                    budgeting.setNilaiTotal(new BigDecimal(0));
                    budgeting.setSemester1(new BigDecimal(0));
                    budgeting.setSemester2(new BigDecimal(0));
                    budgeting.setQuartal1(new BigDecimal(0));
                    budgeting.setQuartal2(new BigDecimal(0));
                    budgeting.setQuartal3(new BigDecimal(0));
                    budgeting.setQuartal4(new BigDecimal(0));

                    budgeting.setJanuari( new BigDecimal(0));
                    budgeting.setFebruari( new BigDecimal(0));
                    budgeting.setMaret( new BigDecimal(0));
                    budgeting.setApril( new BigDecimal(0));
                    budgeting.setMei( new BigDecimal(0));
                    budgeting.setJuni( new BigDecimal(0));
                    budgeting.setJuli( new BigDecimal(0));
                    budgeting.setAgustus( new BigDecimal(0));
                    budgeting.setSeptember( new BigDecimal(0));
                    budgeting.setOktober( new BigDecimal(0));
                    budgeting.setNovember( new BigDecimal(0));
                    budgeting.setDesember( new BigDecimal(0));


                    if (parentBudgetings.size() > 0){
                        List<Budgeting> parents = parentBudgetings.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(kodeRekeningEntity.getRekeningId())).collect(Collectors.toList());
                        if (parents.size() > 0){
                            Budgeting parent = parents.get(0);
                            budgetingEntity.setNilaiTotal(parent.getNilaiTotal());
                            budgetingEntity.setSemester1(parent.getSemester1());
                            budgetingEntity.setSemester2(parent.getSemester2());
                            budgetingEntity.setQuartal1(parent.getQuartal1());
                            budgetingEntity.setQuartal2(parent.getQuartal2());
                            budgetingEntity.setQuartal3(parent.getQuartal3());
                            budgetingEntity.setQuartal4(parent.getQuartal4());

                            budgetingEntity.setJanuari(parent.getJanuari());
                            budgetingEntity.setFebruari(parent.getFebruari());
                            budgetingEntity.setMaret(parent.getMaret());
                            budgetingEntity.setApril(parent.getApril());
                            budgetingEntity.setMei(parent.getMei());
                            budgetingEntity.setJuni(parent.getJuni());
                            budgetingEntity.setJuli(parent.getJuli());
                            budgetingEntity.setAgustus(parent.getAgustus());
                            budgetingEntity.setSeptember(parent.getSeptember());
                            budgetingEntity.setOktober(parent.getOktober());
                            budgetingEntity.setNovember(parent.getNovember());
                            budgetingEntity.setDesember(parent.getDesember());

                            budgeting.setNilaiTotal(parent.getNilaiTotal());
                            budgeting.setSemester1(parent.getSemester1());
                            budgeting.setSemester2(parent.getSemester2());
                            budgeting.setQuartal1(parent.getQuartal1());
                            budgeting.setQuartal2(parent.getQuartal2());
                            budgeting.setQuartal3(parent.getQuartal3());
                            budgeting.setQuartal4(parent.getQuartal4());

                            budgeting.setJanuari(parent.getJanuari());
                            budgeting.setFebruari(parent.getFebruari());
                            budgeting.setMaret(parent.getMaret());
                            budgeting.setApril(parent.getApril());
                            budgeting.setMei(parent.getMei());
                            budgeting.setJuni(parent.getJuni());
                            budgeting.setJuli(parent.getJuli());
                            budgeting.setAgustus(parent.getAgustus());
                            budgeting.setSeptember(parent.getSeptember());
                            budgeting.setOktober(parent.getOktober());
                            budgeting.setNovember(parent.getNovember());
                            budgeting.setDesember(parent.getDesember());
                        }
                    }

                    listOfMapingParents.add(budgeting);

                    try {
                        budgetingDao.addAndSave(budgetingEntity);
                    } catch (HibernateException e){
                        logger.error("[BudgetingBoImpl.saveUpdateTutupPeriod] ERROR. ",e);
                        throw new GeneralBOException("[BudgetingBoImpl.saveUpdateTutupPeriod] ERROR. ",e);
                    }
                }

                // prosess kembali dengan parameter parrent yang sudah di collect dan di insert;
                // parameter parent diprosess kembali sebagai child;
                level--;
                prosesSumPostBudgeting(listOfMapingParents, bean, level, name, postCoa);
            }
        }

        return new ArrayList<>();
    }

    private ImKodeRekeningEntity getKodeRekeningById(String id) throws GeneralBOException{

        ImKodeRekeningEntity kodeRekeningEntity = new ImKodeRekeningEntity();

        try {
            kodeRekeningEntity = kodeRekeningDao.getById("rekeningId", id);
        } catch (HibernateException e){
            logger.error("[TutupPeriodBoImpl.getKodeRekeningById] ERROR. ", e);
            throw new GeneralBOException("[TutupPeriodBoImpl.getKodeRekeningById] ERROR. "+ e);
        }

        return kodeRekeningEntity;
    }

    private List<ImKodeRekeningEntity> getListEntityKodeRekening(KodeRekening bean) throws GeneralBOException{

        Map hsCriteria = new HashMap();
        if (bean.getLevel() != null){
            hsCriteria.put("level", bean.getLevel());
        }
        if (bean.getRekeningId() != null){
            hsCriteria.put("rekening_id", bean.getRekeningId());
        }
        if (bean.getParentId() != null){
            hsCriteria.put("parent_id", bean.getParentId());
        }
        if (bean.getPostCoa() != null){
            hsCriteria.put("post_coa", bean.getPostCoa());
        }

        hsCriteria.put("parent_order", "Y");
        hsCriteria.put("flag", "Y");

        List<ImKodeRekeningEntity> kodeRekeningEntities = new ArrayList<>();
        try {
            kodeRekeningEntities = kodeRekeningDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[TutupPeriodBoImpl.getListEntityKodeRekening] ERROR. ",e);
            throw new GeneralBOException("[TutupPeriodBoImpl.getListEntityKodeRekening] ERROR. ",e);
        }
        return kodeRekeningEntities;
    }

    @Override
    public List<BudgetingDetail> getListBudgetingDetailByNoBudgeting(String id) throws GeneralBOException {

        List<BudgetingDetail> budgetingDetails = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_budgeting", id);

        List<ItAkunBudgetingDetailEntity> budgetingDetailEntities = budgetingDetailDao.getByCriteria(hsCriteria);
        if (budgetingDetailEntities.size() > 0){

            BudgetingDetail budgetingDetail;
            for (ItAkunBudgetingDetailEntity budgetingDetailEntity : budgetingDetailEntities){
                budgetingDetail = new BudgetingDetail();
                budgetingDetail.setIdBudgetingDetail(budgetingDetailEntity.getIdBudgetingDetail());
                budgetingDetail.setNoBudgetingDetail(budgetingDetailEntity.getNoBudgetingDetail());
                budgetingDetail.setIdBudgeting(budgetingDetailEntity.getIdBudgeting());
                budgetingDetail.setDivisiId(budgetingDetailEntity.getDivisiId() == null ? "" : budgetingDetailEntity.getDivisiId());
                budgetingDetail.setMasterId(budgetingDetailEntity.getMasterId() == null ? "" : budgetingDetailEntity.getMasterId());
                budgetingDetail.setDivisiName("");
                budgetingDetail.setMasterName("");
                budgetingDetail.setQty(budgetingDetailEntity.getQty());
                budgetingDetail.setNilai(budgetingDetailEntity.getNilai());
                budgetingDetail.setSubTotal(budgetingDetailEntity.getSubTotal());
                budgetingDetail.setFlag(budgetingDetailEntity.getFlag());
                budgetingDetail.setAction(budgetingDetailEntity.getAction());
                budgetingDetail.setCreatedDate(budgetingDetailEntity.getCreatedDate());
                budgetingDetail.setCreatedWho(budgetingDetailEntity.getCreatedWho());
                budgetingDetail.setLastUpdate(budgetingDetailEntity.getLastUpdate());
                budgetingDetail.setLastUpdateWho(budgetingDetailEntity.getLastUpdateWho());
                budgetingDetail.setPositionId(budgetingDetailEntity.getPositionId());
                budgetingDetail.setTipe(budgetingDetailEntity.getTipe());

                if (!"".equalsIgnoreCase(budgetingDetail.getPositionId()) && budgetingDetail.getPositionId() != null){
                    ImPosition position = positionDao.getById("positionId", budgetingDetail.getPositionId());
                    budgetingDetail.setDivisiName(position.getPositionName());
                }

                if (!"".equalsIgnoreCase(budgetingDetail.getMasterId())){
                    ImMasterEntity masterEntity = masterDao.getById("primaryKey.nomorMaster", budgetingDetail.getMasterId());
                    if (masterEntity != null){
                        budgetingDetail.setMasterName(masterEntity.getNama());
                    }
                }

                if ("INVS".equalsIgnoreCase(budgetingDetailEntity.getDivisiId())){
                    budgetingDetail.setDivisiName("Investasi");
                }

                String tipe = "";
                String tahun = "";
                if (!"".equalsIgnoreCase(budgetingDetail.getIdBudgeting())){
                    ItAkunBudgetingEntity budgetingEntity = budgetingDao.getById("idBudgeting", budgetingDetail.getIdBudgeting());
                    if (budgetingEntity != null){
                        budgetingDetail.setRekeningId(budgetingEntity.getRekeningId());
                        tipe = budgetingEntity.getTipe();
                        tahun = budgetingEntity.getTahun();

                        // mencari list periode;
                        List<BudgetingPeriode> budgetingPeriodes = new ArrayList<>();
                        SaldoAkhir saldoPeriod = budgetingDao.getSaldoAkhirLastPeriod(budgetingEntity.getTahun(), budgetingEntity.getRekeningId(), budgetingEntity.getBranchId());
                            if (saldoPeriod != null){
                                BudgetingPeriode budgetingPeriode = new BudgetingPeriode();
                                for (BudgetingPeriode periode : budgetingPeriode.getListBudgetingPeriode()){
                                    if (saldoPeriod.getBulan().compareTo(periode.getBulan()) == 1 || saldoPeriod.getBulan().compareTo(periode.getBulan()) == 0){
                                        budgetingPeriodes.add(periode);
                                    }
                                }
                            }
                        budgetingDetail.setListPeriode(budgetingPeriodes);
                        // list periode end;
                    }
                }


                // get saldo akhir and selisih by period start
                final String finalTipe = budgetingDetail.getTipe();
                List<BudgetingPeriode> listOfPeriode = new ArrayList<>();
                BudgetingPeriode budgetingPeriode = new BudgetingPeriode();
                if ("bulanan".equalsIgnoreCase(tipe)){
                    listOfPeriode = budgetingPeriode.getListBudgetingPeriode().stream().filter(p->p.getNamaBulan().equalsIgnoreCase(finalTipe)).collect(Collectors.toList());
                } else if ("quartal".equalsIgnoreCase(tipe)){
                    listOfPeriode = budgetingPeriode.getListBudgetingPeriode().stream().filter(p->p.getKuartal().equalsIgnoreCase(finalTipe)).collect(Collectors.toList());
                } else if ("semester".equalsIgnoreCase(tipe)){
                    listOfPeriode = budgetingPeriode.getListBudgetingPeriode().stream().filter(p->p.getSemester().equalsIgnoreCase(finalTipe)).collect(Collectors.toList());
                } else {
                    listOfPeriode = budgetingPeriode.getListBudgetingPeriode();
                }

                Integer periodeBulan = new Integer(0);
                BigDecimal saldoAkhir = new BigDecimal(0);
                if (listOfPeriode.size() > 0){
                    periodeBulan = listOfPeriode.get(0).getBulan();
                    for (BudgetingPeriode periode : listOfPeriode){
                        hsCriteria = new HashMap();
                        hsCriteria.put("rekening_id", budgetingDetail.getRekeningId());
                        hsCriteria.put("periode", periode.getBulan() + "-" + tahun);

                        if (budgetingDetail.getMasterId() != null && !"".equalsIgnoreCase(budgetingDetail.getMasterId())){
                            hsCriteria.put("master_id", budgetingDetail.getMasterId());
                        }
                        if (budgetingDetail.getDivisiId() != null && !"".equalsIgnoreCase(budgetingDetail.getDivisiId())){
                            hsCriteria.put("divisi_id", budgetingDetail.getDivisiId());
                        }

                        List<ItAkunSaldoAkhirDetailEntity> saldoAkhirDetailEntities = saldoAkhirDetailDao.getByCriteria(hsCriteria);
                        if (saldoAkhirDetailEntities.size() > 0){
                            for (ItAkunSaldoAkhirDetailEntity detailEntity : saldoAkhirDetailEntities){
                                saldoAkhir = saldoAkhir.add(detailEntity.getSaldo());
                            }
                        }
                    }
                }

                BigDecimal selisihSaldoAkhir = budgetingDetail.getSubTotal().subtract(saldoAkhir);
                budgetingDetail.setSaldoAkhir(saldoAkhir);
                budgetingDetail.setSelisihSaldoAkhir(selisihSaldoAkhir);
                budgetingDetail.setBulan(periodeBulan);
                // get saldo akhir and selisih END //

                // set to list
                budgetingDetails.add(budgetingDetail);

            }
        }

        // sorting bulan / periode / tipe
        Collections.sort(budgetingDetails, BudgetingDetail.getTipePeriodeSorting());
        return budgetingDetails;
    }

    @Override
    public List<BudgetingPengadaan> getListBudgetingPengadaanNoDetail(String id) throws GeneralBOException {
        logger.info("[BudgetingBoImpl.getListBudgetingPengadaanNoDetail] START >>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_budgeting_detail", id);

        List<BudgetingPengadaan> budgetingPengadaans = new ArrayList<>();
        List<ItAkunBudgetingPengadaanEntity> budgetingPengadaanEntities = budgetingPengadaanDao.getByCriteria(hsCriteria);
        if (budgetingPengadaanEntities.size() > 0) {

            BudgetingPengadaan budgetingPengadaan;
            for (ItAkunBudgetingPengadaanEntity pengadaanEntity : budgetingPengadaanEntities) {
                budgetingPengadaan = new BudgetingPengadaan();
                budgetingPengadaan.setIdPengadaan(pengadaanEntity.getIdPengadaan());
                budgetingPengadaan.setNoBudgeting(pengadaanEntity.getNoBudgetingDetail());
                budgetingPengadaan.setNamPengadaan(pengadaanEntity.getNamPengadaan());
                budgetingPengadaan.setNilai(pengadaanEntity.getNilai());
                budgetingPengadaan.setQty(pengadaanEntity.getQty());
                budgetingPengadaan.setSubTotal(pengadaanEntity.getSubTotal());
                budgetingPengadaan.setTipe(pengadaanEntity.getTipe());
                budgetingPengadaan.setFlag(pengadaanEntity.getFlag());
                budgetingPengadaan.setAction(pengadaanEntity.getAction());
                budgetingPengadaan.setCreatedDate(pengadaanEntity.getCreatedDate());
                budgetingPengadaan.setCreatedWho(pengadaanEntity.getCreatedWho());
                budgetingPengadaan.setLastUpdate(pengadaanEntity.getLastUpdate());
                budgetingPengadaan.setLastUpdateWho(pengadaanEntity.getLastUpdateWho());
                budgetingPengadaan.setIdBudgetingDetail(pengadaanEntity.getIdBudgetingDetail());
                budgetingPengadaan.setNilaiKontrak(pengadaanEntity.getNilaiKontrak() == null ? new BigDecimal(0) : pengadaanEntity.getNilaiKontrak());
                budgetingPengadaan.setNoKontrak(pengadaanEntity.getNoKontrak() == null ? "" : pengadaanEntity.getNoKontrak());
                budgetingPengadaan.setRealisasi(new BigDecimal(0));
                budgetingPengadaan.setSelisih(budgetingPengadaan.getSubTotal().subtract(budgetingPengadaan.getRealisasi()));
                budgetingPengadaan.setNoPengadaan(pengadaanEntity.getNoPengadaan());
                budgetingPengadaans.add(budgetingPengadaan);
            }

        }

        logger.info("[BudgetingBoImpl.getListBudgetingPengadaanNoDetail] END <<<");
        return budgetingPengadaans;
    }

    @Override
    public Budgeting checkBudgeting(String branchId, String tahun) throws GeneralBOException {
        return budgetingDao.getCheckTransaksi(branchId, tahun);
    }

    @Override
    public ItAkunBudgetingEntity getBudgetingEntityById(String id) throws GeneralBOException {
        return budgetingDao.getById("idBudgeting", id);
    }

    @Override
    public List<ItAkunBudgetingPengadaanEntity> getListBudgetingPengadaanByIdDetail(String id) throws GeneralBOException {
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_budgeting_detail", id);
        return budgetingPengadaanDao.getByCriteria(hsCriteria);
    }

    public void setBudgetingDao(BudgetingDao budgetingDao) {
        this.budgetingDao = budgetingDao;
    }

    public void setBudgetingDetailDao(BudgetingDetailDao budgetingDetailDao) {
        this.budgetingDetailDao = budgetingDetailDao;
    }

    public void setBudgetingPengadaanDao(BudgetingPengadaanDao budgetingPengadaanDao) {
        this.budgetingPengadaanDao = budgetingPengadaanDao;
    }

    public void setKodeRekeningDao(KodeRekeningDao kodeRekeningDao) {
        this.kodeRekeningDao = kodeRekeningDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public void setMasterDao(MasterDao masterDao) {
        this.masterDao = masterDao;
    }

    public void setSaldoAkhirDao(SaldoAkhirDao saldoAkhirDao) {
        this.saldoAkhirDao = saldoAkhirDao;
    }

    public void setSaldoAkhirDetailDao(SaldoAkhirDetailDao saldoAkhirDetailDao) {
        this.saldoAkhirDetailDao = saldoAkhirDetailDao;
    }

    @Override
    public String getBudgetBiayaDivisiSaatIni(Budgeting bean){
        logger.info("[BudgetingBoImpl.getBudgetBiayaDivisiSaatIni] START >>>");
        List<BudgettingDTO> budgettingDTOList;
        BigDecimal budget = BigDecimal.ZERO;
        try {
            ImPosition position = positionDao.getById("positionId",bean.getDivisi());

            budgettingDTOList = laporanAkuntansiDao.getBudgettingPerDivisi(bean.getBranchId(),bean.getStatus(),bean.getTahun(),position.getKodering(),bean.getCoa());
        } catch (HibernateException e){
            logger.error("[BudgetingBoImpl.getBudgetBiayaDivisiSaatIni] ERROR. ",e);
            throw new GeneralBOException("[BudgetingBoImpl.getBudgetBiayaDivisiSaatIni] ERROR. ",e);
        }

        for (BudgettingDTO budgettingDTO : budgettingDTOList){
            budget = budgettingDTO.getSubTotal();
        }

        logger.info("[BudgetingBoImpl.getBudgetBiayaDivisiSaatIni] END <<<<");
        return  CommonUtil.numbericFormat(budget,"###,###");
    }


    @Override
    public List<Budgeting> getNoBudgetByDivisi(Budgeting bean){
        logger.info("[BudgetingBoImpl.getNoBudgetByDivisi] START >>>");
        List<Budgeting> budgetingList;
        try {
            ImPosition position = positionDao.getById("positionId",bean.getDivisi());

            budgetingList = laporanAkuntansiDao.getNoBudgetByDivisi(bean.getBranchId(),bean.getStatus(),bean.getTahun(),position.getKodering());
        } catch (HibernateException e){
            logger.error("[BudgetingBoImpl.getBudgetBiayaDivisiSaatIni] ERROR. ",e);
            throw new GeneralBOException("[BudgetingBoImpl.getBudgetBiayaDivisiSaatIni] ERROR. ",e);
        }

        logger.info("[BudgetingBoImpl.getNoBudgetByDivisi] END <<<<");
        return  budgetingList;
    }
    @Override
    public List<Budgeting> getInvestasiByDivisi(Budgeting bean){
        logger.info("[BudgetingBoImpl.getNoBudgetByDivisi] START >>>");
        List<Budgeting> budgetingList;
        try {
            ImPosition position = positionDao.getById("positionId",bean.getDivisi());

            budgetingList = laporanAkuntansiDao.getInvestasiByDivisi(bean.getBranchId(),bean.getStatus(),bean.getTahun(),position.getKodering());
        } catch (HibernateException e){
            logger.error("[BudgetingBoImpl.getBudgetBiayaDivisiSaatIni] ERROR. ",e);
            throw new GeneralBOException("[BudgetingBoImpl.getBudgetBiayaDivisiSaatIni] ERROR. ",e);
        }

        logger.info("[BudgetingBoImpl.getNoBudgetByDivisi] END <<<<");
        return  budgetingList;
    }

    @Override
    public List<BudgetingPengadaan> getInvestasiByNoBudgeting(String noBudgeting){
        logger.info("[BudgetingBoImpl.getInvestasiByNoBudgeting] START >>>");
        List<BudgetingPengadaan> budgetingList;
        try {
            budgetingList = laporanAkuntansiDao.getInvestasiByNoBudgeting(noBudgeting);
        } catch (HibernateException e){
            logger.error("[BudgetingBoImpl.getInvestasiByNoBudgeting] ERROR. ",e);
            throw new GeneralBOException("[BudgetingBoImpl.getInvestasiByNoBudgeting] ERROR. ",e);
        }

        logger.info("[BudgetingBoImpl.getInvestasiByNoBudgeting] END <<<<");
        return  budgetingList;
    }
    @Override
    public String getBudgetBiayaInvestasiSaatIni(Budgeting bean){
        logger.info("[BudgetingBoImpl.getBudgetBiayaDivisiSaatIni] START >>>");
        List<BudgettingDTO> budgettingDTOList;
        BigDecimal budget = BigDecimal.ZERO;
        try {
            budgettingDTOList = laporanAkuntansiDao.getBudgettingPengadaan(bean.getIdPengadaan());
        } catch (HibernateException e){
            logger.error("[BudgetingBoImpl.getBudgetBiayaDivisiSaatIni] ERROR. ",e);
            throw new GeneralBOException("[BudgetingBoImpl.getBudgetBiayaDivisiSaatIni] ERROR. ",e);
        }

        for (BudgettingDTO budgettingDTO : budgettingDTOList){
            budget = budgettingDTO.getSubTotal();
        }

        logger.info("[BudgetingBoImpl.getBudgetBiayaDivisiSaatIni] END <<<<");
        return  CommonUtil.numbericFormat(budget,"###,###");
    }

}
