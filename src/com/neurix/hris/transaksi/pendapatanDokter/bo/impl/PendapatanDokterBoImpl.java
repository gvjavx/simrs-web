package com.neurix.hris.transaksi.pendapatanDokter.bo.impl;

import com.neurix.akuntansi.master.mappingJurnal.dao.MappingJurnalDao;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDetailDao;
import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalDetailEntity;
import com.neurix.akuntansi.transaksi.jurnal.model.JurnalDetail;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.dokterKso.dao.DokterKsoDao;
import com.neurix.hris.master.dokterKso.model.ImSimrsDokterKso;
import com.neurix.hris.transaksi.pendapatanDokter.bo.PendapatanDokterBo;
import com.neurix.hris.transaksi.pendapatanDokter.dao.PajakPendapatanDokterDao;
import com.neurix.hris.transaksi.pendapatanDokter.dao.PendapatanDokterDao;
import com.neurix.hris.transaksi.pendapatanDokter.dao.PendapatanDokterDetailDao;
import com.neurix.hris.transaksi.pendapatanDokter.dao.PendapatanDokterPphLebihDao;
import com.neurix.hris.transaksi.pendapatanDokter.model.*;
import com.neurix.hris.transaksi.pendapatanDokter.model.BillingPendapatanDokter;
import com.neurix.hris.transaksi.pendapatanDokter.model.ItHrisPendapatanDokterDetailEntity;
import com.neurix.hris.transaksi.pendapatanDokter.model.ItHrisPendapatanDokterEntity;
import com.neurix.hris.transaksi.pendapatanDokter.model.PendapatanDokter;
import com.neurix.simrs.master.dokter.dao.DokterDao;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterEntity;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PendapatanDokterBoImpl implements PendapatanDokterBo {

    protected static transient Logger logger = Logger.getLogger(PendapatanDokterBoImpl.class);
    private PendapatanDokterDao pendapatanDokterDao;
    private PendapatanDokterDetailDao pendapatanDokterDetailDao;
    private DokterKsoDao dokterKsoDao;
    private BranchDao branchDao;
    private PendapatanDokterPphLebihDao pendapatanDokterPphLebihDao;
    private MappingJurnalDao mappingJurnalDao;
    private PelayananDao pelayananDao;
    private DokterDao dokterDao;
    private PajakPendapatanDokterDao pajakPendapatanDokterDao;
    private JurnalDetailDao jurnalDetailDao;

    public DokterDao getDokterDao() {
        return dokterDao;
    }

    public void setDokterDao(DokterDao dokterDao) {
        this.dokterDao = dokterDao;
    }

    public PelayananDao getPelayananDao() {
        return pelayananDao;
    }

    public void setPelayananDao(PelayananDao pelayananDao) {
        this.pelayananDao = pelayananDao;
    }

    public MappingJurnalDao getMappingJurnalDao() {
        return mappingJurnalDao;
    }

    public void setMappingJurnalDao(MappingJurnalDao mappingJurnalDao) {
        this.mappingJurnalDao = mappingJurnalDao;
    }

    public DokterKsoDao getDokterKsoDao() {
        return dokterKsoDao;
    }

    public void setDokterKsoDao(DokterKsoDao dokterKsoDao) {
        this.dokterKsoDao = dokterKsoDao;
    }

    public PendapatanDokterDao getPendapatanDokterDao() {
        return pendapatanDokterDao;
    }

    public void setPendapatanDokterDao(PendapatanDokterDao pendapatanDokterDao) {
        this.pendapatanDokterDao = pendapatanDokterDao;
    }

    public PendapatanDokterDetailDao getPendapatanDokterDetailDao() {
        return pendapatanDokterDetailDao;
    }

    public void setPendapatanDokterDetailDao(PendapatanDokterDetailDao pendapatanDokterDetailDao) {
        this.pendapatanDokterDetailDao = pendapatanDokterDetailDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public PendapatanDokterPphLebihDao getPendapatanDokterPphLebihDao() {
        return pendapatanDokterPphLebihDao;
    }

    public void setPendapatanDokterPphLebihDao(PendapatanDokterPphLebihDao pendapatanDokterPphLebihDao) {
        this.pendapatanDokterPphLebihDao = pendapatanDokterPphLebihDao;
    }

    public PajakPendapatanDokterDao getPajakPendapatanDokterDao() {
        return pajakPendapatanDokterDao;
    }

    public void setPajakPendapatanDokterDao(PajakPendapatanDokterDao pajakPendapatanDokterDao) {
        this.pajakPendapatanDokterDao = pajakPendapatanDokterDao;
    }

    public JurnalDetailDao getJurnalDetailDao() {
        return jurnalDetailDao;
    }

    public void setJurnalDetailDao(JurnalDetailDao jurnalDetailDao) {
        this.jurnalDetailDao = jurnalDetailDao;
    }

    @Override
    public List<PendapatanDokter> getByCriteriaForPendapatanDokter(PendapatanDokter bean) throws GeneralBOException{
        logger.info("[PendapatanDokterBoImpl.getByCriteriaForPendapatanDokter] start process >>>");
        List<PendapatanDokter> listOfResult = new ArrayList<>();
        if (bean != null){
            List<ImSimrsDokterKso> imSimrsDokterKsos = null;
            List<ItHrisPendapatanDokterEntity> itHrisPendapatanDokterEntity = null;
            List<Object[]> results = new ArrayList<Object[]>();
            List<ImHrisPajakEntity> imHrisPajakEntities = null;

            try{
                Map hsCriteria = new HashMap<>();
                hsCriteria.put("branch_id", bean.getBranchId());
                hsCriteria.put("flag", "Y");
                imSimrsDokterKsos = dokterKsoDao.getByCriteria(hsCriteria);
                Map map = new HashMap<>();
                map.put("flag", "Y");
                imHrisPajakEntities = pajakPendapatanDokterDao.getByCriteria(map);

                if (imSimrsDokterKsos != null){
                    for (ImSimrsDokterKso simrsDokterKso : imSimrsDokterKsos){

                        Double hrBruto, pndptnRs, dppPph50,dppPphKomulatif = 0.0, dppPph21, tarif = 0.0, pphDipungut, hrAktifitas, potKs, gajiBersih;
                        Double totHrBruto = 0.0, totpndptnRs = 0.0, totBruto = 0.0, totDppPph50 = 0.0, totDppPphKomulatif = 0.0, totDppPph21 = 0.0,
                                totPphDipungut = 0.0, totHrAktifitas = 0.0, totPotKs = 0.0, totGajiBersih = 0.0, komulatifLastMonth = 0.0;
                        BigDecimal tarifInajbg;
                        BigDecimal bruto;
                        BigDecimal ksoPersen;
                        BigDecimal bgPersenKs;
                        String dokterId = null;
                        int id = 0;

                        PendapatanDokter pendapatanDokter;
                        dokterId = simrsDokterKso.getNip();
                        results = pendapatanDokterDao.getPendapatanDokter(bean.getBranchId(), bean.getBulan(), bean.getTahun(), dokterId);
                        komulatifLastMonth = pendapatanDokterDao.getPphKomulatif(dokterId, bean.getBulan(), bean.getTahun()).doubleValue();

                        if (results != null){
                            for (Object[] obj : results){
                                pendapatanDokter = new PendapatanDokter();
                                id++;
                                pendapatanDokter.setPendapatanDokterId(String.valueOf(id));
                                pendapatanDokter.setDokterId(String.valueOf(obj[4]));
                                pendapatanDokter.setDokterName(String.valueOf(obj[5]));
                                pendapatanDokter.setBranchId(bean.getBranchId());
                                pendapatanDokter.setBulan(bean.getBulan());
                                pendapatanDokter.setTahun(bean.getTahun());
                                pendapatanDokter.setNoReg(String.valueOf(obj[6]));
                                pendapatanDokter.setJenisRawat(String.valueOf(obj[7]));
                                pendapatanDokter.setKdjnspas(String.valueOf(obj[8]));
                                pendapatanDokter.setNamaPasien(String.valueOf(obj[18]));
//                        pendapatanDokter.setTanggal(String.valueOf(obj[9]));
                                pendapatanDokter.setTanggal((Timestamp) obj[9]);
                                pendapatanDokter.setKeterangan(String.valueOf(obj[10]));
                                pendapatanDokter.setKodeJabatan(String.valueOf(obj[23]));

                                if (obj[11] != null){
                                    tarifInajbg = (BigDecimal) obj[11];
                                    pendapatanDokter.setBgTarifInacbg(tarifInajbg);
                                    pendapatanDokter.setTarifInacbg(CommonUtil.numbericFormat(tarifInajbg, "###,###"));
                                }else {
                                    pendapatanDokter.setTarifInacbg("-");
                                }

                                pendapatanDokter.setPoliId(String.valueOf(obj[20]));
                                pendapatanDokter.setPoliName(String.valueOf(obj[21]));
                                pendapatanDokter.setActivityId(String.valueOf(obj[3]));
                                pendapatanDokter.setActivityName(String.valueOf(obj[22]));

                                String jenisKso = String.valueOf(obj[13]);
                                String masterId = String.valueOf(obj[14]);
                                pendapatanDokter.setMasterId(masterId);

                                bruto = (BigDecimal) obj[12];

                                if ("tindakan".equalsIgnoreCase(jenisKso) && "bpjs".equalsIgnoreCase(masterId)){
                                    ksoPersen = (BigDecimal) obj[15];
                                    int kso = 100 - ksoPersen.intValue();
                                    pndptnRs = bruto.doubleValue() * ksoPersen.intValue() / 100;
                                    hrBruto = bruto.doubleValue() * kso/100;

                                    BigDecimal pdptnRs = CommonUtil.dobelToBigDecimal(pndptnRs);
                                    pendapatanDokter.setBgPendapatanRs(pdptnRs);
                                    pendapatanDokter.setPendapatanRs(CommonUtil.numbericFormat(pdptnRs, "###,###"));
                                    BigDecimal bgHrBruto = CommonUtil.dobelToBigDecimal(hrBruto);
                                    pendapatanDokter.setBgHrBruto(bgHrBruto);
                                    pendapatanDokter.setHrBruto(CommonUtil.numbericFormat(bgHrBruto, "###,###"));
                                }else {
                                    ksoPersen = (BigDecimal) obj[16];
                                    int kso = 100 - ksoPersen.intValue();
                                    pndptnRs = bruto.doubleValue() * ksoPersen.intValue() / 100;
                                    hrBruto = bruto.doubleValue() * kso/100;

                                    BigDecimal pdptnRs = CommonUtil.dobelToBigDecimal(pndptnRs);
                                    pendapatanDokter.setBgPendapatanRs(pdptnRs);
                                    pendapatanDokter.setPendapatanRs(CommonUtil.numbericFormat(pdptnRs, "###,###"));
                                    BigDecimal bgHrBruto = CommonUtil.dobelToBigDecimal(hrBruto);
                                    pendapatanDokter.setBgHrBruto(bgHrBruto);
                                    pendapatanDokter.setHrBruto(CommonUtil.numbericFormat(bgHrBruto, "###,###"));
                                }

                                pendapatanDokter.setBgBruto(bruto);
                                pendapatanDokter.setBruto(CommonUtil.numbericFormat(bruto,"###,###"));

//                                dppPph50 = bruto.doubleValue() * 0.5;
                                dppPph50 = hrBruto * 0.5;
                                BigDecimal pph50 = CommonUtil.dobelToBigDecimal(dppPph50);
                                pendapatanDokter.setBgDppPph50(pph50);
                                pendapatanDokter.setDppPph50(CommonUtil.numbericFormat(pph50,"###,###"));

                                if (komulatifLastMonth != 0.0)
                                    dppPphKomulatif = dppPph50 + dppPphKomulatif;
                                else
                                    dppPphKomulatif = dppPph50;

//                                dppPphKomulatif = dppPph50;
                                BigDecimal pphKomulatif = CommonUtil.dobelToBigDecimal(dppPphKomulatif);
                                pendapatanDokter.setBgDppPph21Komulatif(pphKomulatif);
                                pendapatanDokter.setDppPph21Komulatif(CommonUtil.numbericFormat(pphKomulatif,"###,###"));

                                dppPph21 = dppPph50;
                                BigDecimal pph21 = CommonUtil.dobelToBigDecimal(dppPph21);
                                pendapatanDokter.setBgDppPph21(pph21);
                                pendapatanDokter.setDppPph21(CommonUtil.numbericFormat(pph21,"###,###"));

                                for (ImHrisPajakEntity entity : imHrisPajakEntities){
                                    Double btsBawah = entity.getBatasBawah().doubleValue();
                                    Double btsAtas = entity.getBatasAtas().doubleValue();
                                    if (dppPphKomulatif > btsBawah && dppPphKomulatif <= btsAtas){
                                        tarif = entity.getPajak().doubleValue() / 100;
                                        pendapatanDokter.setTarif(entity.getPajak());
                                        pendapatanDokter.setStTarif(entity.getPajak().toString());
                                    }
                                }
//                                if (dppPphKomulatif <= 50000000) {
//                                    tarif = 0.05;
//                                    pendapatanDokter.setTarif(BigDecimal.valueOf(5));
//                                    pendapatanDokter.setStTarif("5%");
//                                }
//                                else if (dppPphKomulatif > 50000000 && dppPphKomulatif <= 250000000) {
//                                    tarif = 0.15;
//                                    pendapatanDokter.setTarif(BigDecimal.valueOf(15));
//                                    pendapatanDokter.setStTarif("15%");
//                                }
//                                else if (dppPphKomulatif > 250000000 && dppPphKomulatif <= 500000000){
//                                    tarif = 0.25;
//                                    pendapatanDokter.setTarif(BigDecimal.valueOf(25));
//                                    pendapatanDokter.setStTarif("25%");
//                                }
//                                else {
//                                    tarif = 0.30;
//                                    pendapatanDokter.setTarif(BigDecimal.valueOf(30));
//                                    pendapatanDokter.setStTarif("30%");
//                                }

                                pphDipungut = dppPph21 * tarif;
                                BigDecimal jumPphDipungut = CommonUtil.dobelToBigDecimal(pphDipungut);
                                pendapatanDokter.setBgPphDipungut(jumPphDipungut);
                                pendapatanDokter.setPphDipungut(CommonUtil.numbericFormat(jumPphDipungut,"###,###"));

                                hrAktifitas = hrBruto - pphDipungut;
                                BigDecimal hrAktftsNetto = CommonUtil.dobelToBigDecimal(hrAktifitas);
                                pendapatanDokter.setBgHrAktifitasNetto(hrAktftsNetto);
                                pendapatanDokter.setHrAktifitasNetto(CommonUtil.numbericFormat(hrAktftsNetto,"###,###"));

                                bgPersenKs = (BigDecimal) obj[19];
                                potKs = hrAktifitas * (bgPersenKs.doubleValue()/100);
                                BigDecimal bgPotKs = CommonUtil.dobelToBigDecimal(potKs);
                                pendapatanDokter.setBgPotKs(bgPotKs);
                                pendapatanDokter.setPotKs(CommonUtil.numbericFormat(bgPotKs, "###,###"));

                                gajiBersih = hrBruto - pphDipungut - potKs;
                                BigDecimal jumGaji = CommonUtil.dobelToBigDecimal(gajiBersih);
                                pendapatanDokter.setBgGajiBersih(jumGaji);
                                pendapatanDokter.setGajiBersih(CommonUtil.numbericFormat(jumGaji, "###,###"));

                                totBruto = totBruto + bruto.doubleValue();
                                totpndptnRs = totpndptnRs + pndptnRs;
                                totHrBruto = totHrBruto + hrBruto;
                                totDppPph50 = totDppPph50 + dppPph50;
                                totDppPphKomulatif = totDppPphKomulatif + dppPphKomulatif;
                                totDppPph21 = totDppPph21 + dppPph21;
                                totPphDipungut = totPphDipungut + pphDipungut;
                                totHrAktifitas = totHrAktifitas + hrAktifitas;
                                totPotKs = totPotKs + potKs;
                                totGajiBersih = totGajiBersih + gajiBersih;
                                if (id == results.size()){
                                    BigDecimal bgTotBruto = CommonUtil.dobelToBigDecimal(totBruto);
                                    pendapatanDokter.setTotalBruto(bgTotBruto);
                                    pendapatanDokter.setStTotalBruto(CommonUtil.numbericFormat(bgTotBruto, "###,###"));
                                    BigDecimal bgTotpndptnRs = CommonUtil.dobelToBigDecimal(totpndptnRs);
                                    pendapatanDokter.setTotalPendapatanRs(bgTotpndptnRs);
                                    pendapatanDokter.setStTotalPendapatanRs(CommonUtil.numbericFormat(bgTotpndptnRs, "###,###"));
                                    BigDecimal bgTotHrBruto = CommonUtil.dobelToBigDecimal(totHrBruto);
                                    pendapatanDokter.setTotalHrBruto(bgTotHrBruto);
                                    pendapatanDokter.setStTotalHrBruto(CommonUtil.numbericFormat(bgTotHrBruto, "###,###"));
                                    BigDecimal bgTotDppPph50 = CommonUtil.dobelToBigDecimal(totDppPph50);
                                    pendapatanDokter.setTotalDppPph50(bgTotDppPph50);
                                    pendapatanDokter.setStTotalDppPph50(CommonUtil.numbericFormat(bgTotDppPph50, "###,###"));
                                    BigDecimal bgTotDppPphKomulatif = CommonUtil.dobelToBigDecimal(totDppPphKomulatif);
                                    pendapatanDokter.setTotalDppPph21Komulatif(bgTotDppPphKomulatif);
                                    pendapatanDokter.setStTotalDppPph21Komulatif(CommonUtil.numbericFormat(bgTotDppPphKomulatif, "###,###"));
                                    BigDecimal bgTotDppPph21 = CommonUtil.dobelToBigDecimal(totDppPph21);
                                    pendapatanDokter.setTotalDppPph21(bgTotDppPph21);
                                    pendapatanDokter.setStTotalDppPph21(CommonUtil.numbericFormat(bgTotDppPph21, "###,###"));
                                    BigDecimal bgTotPphDipungut = CommonUtil.dobelToBigDecimal(totPphDipungut);
                                    pendapatanDokter.setTotalPphDipungut(bgTotPphDipungut);
                                    pendapatanDokter.setStTotalPphDipungut(CommonUtil.numbericFormat(bgTotPphDipungut, "###,###"));
                                    BigDecimal bgTotHrAktifitas = CommonUtil.dobelToBigDecimal(totHrAktifitas);
                                    pendapatanDokter.setTotalHrAktifitasNetto(bgTotHrAktifitas);
                                    pendapatanDokter.setStTotalHrAktifitasNetto(CommonUtil.numbericFormat(bgTotHrAktifitas, "###,###"));
                                    BigDecimal bgTotPotKs = CommonUtil.dobelToBigDecimal(totPotKs);
                                    pendapatanDokter.setTotalPotKs(bgTotPotKs);
                                    pendapatanDokter.setStTotalPotKs(CommonUtil.numbericFormat(bgTotPotKs, "###,###"));
                                    BigDecimal bgTotGajiBersih = CommonUtil.dobelToBigDecimal(totGajiBersih);
                                    pendapatanDokter.setTotalGajiBersih(bgTotGajiBersih);
                                    pendapatanDokter.setStTotalGajiBersih(CommonUtil.numbericFormat(bgTotGajiBersih, "###,###"));
                                }

                                String userLogin = CommonUtil.userLogin();
                                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                                pendapatanDokter.setApprovalFlag("Y");
                                pendapatanDokter.setApprovalWho(userLogin);
                                pendapatanDokter.setApprovalDate(updateTime);
                                pendapatanDokter.setFlag("Y");
                                pendapatanDokter.setAction("C");
                                pendapatanDokter.setCreatedDate(updateTime);
                                pendapatanDokter.setLastUpdate(updateTime);
                                pendapatanDokter.setCreatedWho(userLogin);
                                pendapatanDokter.setLastUpdateWho(userLogin);

                                listOfResult.add(pendapatanDokter);
                            }
                        }
                    }
                }
//                if (!"01".equalsIgnoreCase(bean.getBulan())){
//                    Map hsCriteris = new HashMap<>();
//                    hsCriteris.put("branch_id", bean.getBranchId());
//                    hsCriteris.put("bulan", bean.getBulan());
//                    hsCriteris.put("tahun", bean.getTahun());
//
//                    itHrisPendapatanDokterEntity = pendapatanDokterDao.getByCriteria(hsCriteris);
//                }
//                results = pendapatanDokterDao.getPendapatanDokter(bean.getBranchId(), bean.getBulan(), bean.getTahun());
            }catch (HibernateException e){
                logger.error("[PendapatanDokterBoImpl.getByCriteriaForPendapatanDokter] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode lembur, please inform to your admin...," + e.getMessage());
            }

//            int id = 0;
//            PendapatanDokter pendapatanDokter;
//            if (results != null){
//                for (Object[] obj : results){
//                    if (itHrisPendapatanDokterEntity != null){
//                        for (ItHrisPendapatanDokterEntity entity : itHrisPendapatanDokterEntity){
//                            if (entity.getDokterId().equalsIgnoreCase((String) obj[4])){
//                                pendapatanDokter = new PendapatanDokter();
//
//                                id++;
//                                pendapatanDokter.setPendapatanDokterId(String.valueOf(id));
//                                pendapatanDokter.setDokterId(entity.getDokterId());
//                                pendapatanDokter.setDokterName(entity.getDokterName());
//                                pendapatanDokter.setBranchId(entity.getBranchId());
//                                pendapatanDokter.setBulan(bean.getBulan());
//                                pendapatanDokter.setTahun(bean.getTahun());
//
//                                bruto = (BigDecimal) obj[5];
//                                pendapatanDokter.setBruto(CommonUtil.numbericFormat(bruto,"###,###"));
//
//                                dppPph50 = bruto.doubleValue() * 0.5;
//                                BigDecimal pph50 = CommonUtil.dobelToBigDecimal(dppPph50);
//                                pendapatanDokter.setDppPph50(CommonUtil.numbericFormat(pph50,"###,###"));
//
//                                dppPphKomulatif = dppPph50 + entity.getDppPph21Komulatif().doubleValue();
//                                BigDecimal pphKomulatif = CommonUtil.dobelToBigDecimal(dppPphKomulatif);
//                                pendapatanDokter.setDppPph21Komulatif(CommonUtil.numbericFormat(pphKomulatif,"###,###"));
//
//                                dppPph21 = dppPph50;
//                                BigDecimal pph21 = CommonUtil.dobelToBigDecimal(dppPph21);
//                                pendapatanDokter.setDppPph21(CommonUtil.numbericFormat(pph21,"###,###"));
//
//                                if (dppPphKomulatif <= 50000000) {
//                                    tarif = 0.05;
//                                    pendapatanDokter.setTarif(BigDecimal.valueOf(5));
//                                    pendapatanDokter.setStTarif("5%");
//                                }
//                                else if (dppPphKomulatif > 50000000 && dppPphKomulatif <= 250000000) {
//                                    tarif = 0.15;
//                                    pendapatanDokter.setTarif(BigDecimal.valueOf(15));
//                                    pendapatanDokter.setStTarif("15%");
//                                }
//                                else if (dppPphKomulatif > 250000000 && dppPphKomulatif <= 500000000){
//                                    tarif = 0.25;
//                                    pendapatanDokter.setTarif(BigDecimal.valueOf(25));
//                                    pendapatanDokter.setStTarif("25%");
//                                }
//                                else {
//                                    tarif = 0.30;
//                                    pendapatanDokter.setTarif(BigDecimal.valueOf(30));
//                                    pendapatanDokter.setStTarif("30%");
//                                }
//
//                                pphDipungut = dppPph21 * tarif;
//                                BigDecimal jumPphDipungut = CommonUtil.dobelToBigDecimal(pphDipungut);
//                                pendapatanDokter.setPphDipungut(CommonUtil.numbericFormat(jumPphDipungut,"###,###"));
//
//                                gajiBersih = bruto.doubleValue() - pphDipungut;
//                                BigDecimal jumGaji = CommonUtil.dobelToBigDecimal(gajiBersih);
//                                pendapatanDokter.setGajiBersih(CommonUtil.numbericFormat(jumGaji, "###,###"));
//
//                                String userLogin = CommonUtil.userLogin();
//                                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
//                                pendapatanDokter.setApprovalFlag("Y");
//                                pendapatanDokter.setApprovalWho(userLogin);
//                                pendapatanDokter.setApprovalDate(updateTime);
//                                pendapatanDokter.setFlag("Y");
//                                pendapatanDokter.setAction("C");
//                                pendapatanDokter.setCreatedDate(updateTime);
//                                pendapatanDokter.setLastUpdate(updateTime);
//                                pendapatanDokter.setCreatedWho(userLogin);
//                                pendapatanDokter.setLastUpdateWho(userLogin);
//
//                                listOfResult.add(pendapatanDokter);
//                            }
//                        }
//                    }else {
//                        pendapatanDokter = new PendapatanDokter();
//
//                        id++;
//                        pendapatanDokter.setPendapatanDokterId(String.valueOf(id));
//                        pendapatanDokter.setDokterId(String.valueOf(obj[4]));
//                        pendapatanDokter.setDokterName(String.valueOf(obj[5]));
//                        pendapatanDokter.setBranchId(bean.getBranchId());
//                        pendapatanDokter.setBulan(bean.getBulan());
//                        pendapatanDokter.setTahun(bean.getTahun());
//                        pendapatanDokter.setNoReg(String.valueOf(obj[6]));
//                        pendapatanDokter.setJenisRawat(String.valueOf(obj[7]));
//                        pendapatanDokter.setKdjnspas(String.valueOf(obj[8]));
//                        pendapatanDokter.setNamaPasien(String.valueOf(obj[18]));
////                        pendapatanDokter.setTanggal(String.valueOf(obj[9]));
//                        pendapatanDokter.setTanggal((Timestamp) obj[9]);
//                        pendapatanDokter.setKeterangan(String.valueOf(obj[10]));
//                        tarifInajbg = (BigDecimal) obj[11];
//                        pendapatanDokter.setBgTarifInacbg(tarifInajbg);
//                        pendapatanDokter.setTarifInacbg(CommonUtil.numbericFormat(tarifInajbg, "###,###"));
//                        pendapatanDokter.setPoliId(String.valueOf(obj[20]));
//                        pendapatanDokter.setPoliName(String.valueOf(obj[21]));
//                        pendapatanDokter.setActivityId(String.valueOf(obj[3]));
//                        pendapatanDokter.setActivityName(String.valueOf(obj[22]));
//
//                        String jenisKso = String.valueOf(obj[13]);
//                        String masterId = String.valueOf(obj[14]);
//
//                        bruto = (BigDecimal) obj[12];
//
//                        if ("tindakan".equalsIgnoreCase(jenisKso) && "bpjs".equalsIgnoreCase(masterId)){
//                            ksoPersen = (BigDecimal) obj[15];
//                            int kso = 100 - ksoPersen.intValue();
//                            pndptnRs = bruto.doubleValue() * (ksoPersen.intValue() / 100);
//                            hrBruto = bruto.doubleValue() * (kso/100);
//
//                            BigDecimal pdptnRs = CommonUtil.dobelToBigDecimal(pndptnRs);
//                            pendapatanDokter.setBgPendapatanRs(pdptnRs);
//                            pendapatanDokter.setPendapatanRs(CommonUtil.numbericFormat(pdptnRs, "###,###"));
//                            BigDecimal bgHrBruto = CommonUtil.dobelToBigDecimal(hrBruto);
//                            pendapatanDokter.setBgHrBruto(bgHrBruto);
//                            pendapatanDokter.setHrBruto(CommonUtil.numbericFormat(bgHrBruto, "###,###"));
//                        }else {
//                            ksoPersen = (BigDecimal) obj[16];
//                            int kso = 100 - ksoPersen.intValue();
//                            pndptnRs = bruto.doubleValue() * (ksoPersen.intValue() / 100);
//                            hrBruto = bruto.doubleValue() * (kso/100);
//
//                            BigDecimal pdptnRs = CommonUtil.dobelToBigDecimal(pndptnRs);
//                            pendapatanDokter.setBgPendapatanRs(pdptnRs);
//                            pendapatanDokter.setPendapatanRs(CommonUtil.numbericFormat(pdptnRs, "###,###"));
//                            BigDecimal bgHrBruto = CommonUtil.dobelToBigDecimal(hrBruto);
//                            pendapatanDokter.setBgHrBruto(bgHrBruto);
//                            pendapatanDokter.setHrBruto(CommonUtil.numbericFormat(bgHrBruto, "###,###"));
//                        }
//
//                        pendapatanDokter.setBgBruto(bruto);
//                        pendapatanDokter.setBruto(CommonUtil.numbericFormat(bruto,"###,###"));
//
//                        dppPph50 = bruto.doubleValue() * 0.5;
//                        BigDecimal pph50 = CommonUtil.dobelToBigDecimal(dppPph50);
//                        pendapatanDokter.setBgDppPph50(pph50);
//                        pendapatanDokter.setDppPph50(CommonUtil.numbericFormat(pph50,"###,###"));
//
//                        dppPphKomulatif = dppPph50;
//                        BigDecimal pphKomulatif = CommonUtil.dobelToBigDecimal(dppPphKomulatif);
//                        pendapatanDokter.setBgDppPph21Komulatif(pphKomulatif);
//                        pendapatanDokter.setDppPph21Komulatif(CommonUtil.numbericFormat(pphKomulatif,"###,###"));
//
//                        dppPph21 = dppPph50;
//                        BigDecimal pph21 = CommonUtil.dobelToBigDecimal(dppPph21);
//                        pendapatanDokter.setBgDppPph21(pph21);
//                        pendapatanDokter.setDppPph21(CommonUtil.numbericFormat(pph21,"###,###"));
//
//                        if (dppPphKomulatif <= 50000000) {
//                            tarif = 0.05;
//                            pendapatanDokter.setTarif(BigDecimal.valueOf(5));
//                            pendapatanDokter.setStTarif("5%");
//                        }
//                        else if (dppPphKomulatif > 50000000 && dppPphKomulatif <= 250000000) {
//                            tarif = 0.15;
//                            pendapatanDokter.setTarif(BigDecimal.valueOf(15));
//                            pendapatanDokter.setStTarif("15%");
//                        }
//                        else if (dppPphKomulatif > 250000000 && dppPphKomulatif <= 500000000){
//                            tarif = 0.25;
//                            pendapatanDokter.setTarif(BigDecimal.valueOf(25));
//                            pendapatanDokter.setStTarif("25%");
//                        }
//                        else {
//                            tarif = 0.30;
//                            pendapatanDokter.setTarif(BigDecimal.valueOf(30));
//                            pendapatanDokter.setStTarif("30%");
//                        }
//
//                        pphDipungut = dppPph21 * tarif;
//                        BigDecimal jumPphDipungut = CommonUtil.dobelToBigDecimal(pphDipungut);
//                        pendapatanDokter.setBgPphDipungut(jumPphDipungut);
//                        pendapatanDokter.setPphDipungut(CommonUtil.numbericFormat(jumPphDipungut,"###,###"));
//
//                        hrAktifitas = hrBruto - pphDipungut;
//                        BigDecimal hrAktftsNetto = CommonUtil.dobelToBigDecimal(hrAktifitas);
//                        pendapatanDokter.setBgHrAktifitasNetto(hrAktftsNetto);
//                        pendapatanDokter.setHrAktifitasNetto(CommonUtil.numbericFormat(hrAktftsNetto,"###,###"));
//
//                        bgPersenKs = (BigDecimal) obj[19];
//                        potKs = hrAktifitas * (bgPersenKs.doubleValue()/100);
//                        BigDecimal bgPotKs = CommonUtil.dobelToBigDecimal(potKs);
//                        pendapatanDokter.setBgPotKs(bgPotKs);
//                        pendapatanDokter.setPotKs(CommonUtil.numbericFormat(bgPotKs, "###,###"));
//
//                        gajiBersih = bruto.doubleValue() - pphDipungut - potKs;
//                        BigDecimal jumGaji = CommonUtil.dobelToBigDecimal(gajiBersih);
//                        pendapatanDokter.setBgGajiBersih(jumGaji);
//                        pendapatanDokter.setGajiBersih(CommonUtil.numbericFormat(jumGaji, "###,###"));
//
//                        totBruto = totBruto + bruto.doubleValue();
//                        totpndptnRs = totpndptnRs + pndptnRs;
//                        totHrBruto = totHrBruto + hrBruto;
//                        totDppPph50 = totDppPph50 + dppPph50;
//                        totDppPphKomulatif = totDppPphKomulatif + dppPphKomulatif;
//                        totDppPph21 = totDppPph21 + dppPph21;
//                        totPphDipungut = totPphDipungut + pphDipungut;
//                        totHrAktifitas = totHrAktifitas + hrAktifitas;
//                        totPotKs = totPotKs + potKs;
//                        totGajiBersih = totGajiBersih + gajiBersih;
//                        if (id == results.size()){
//                            BigDecimal bgTotBruto = CommonUtil.dobelToBigDecimal(totBruto);
//                            pendapatanDokter.setTotalBruto(bgTotBruto);
//                            pendapatanDokter.setStTotalBruto(CommonUtil.numbericFormat(bgTotBruto, "###,###"));
//                            BigDecimal bgTotpndptnRs = CommonUtil.dobelToBigDecimal(totpndptnRs);
//                            pendapatanDokter.setTotalPendapatanRs(bgTotpndptnRs);
//                            pendapatanDokter.setStTotalPendapatanRs(CommonUtil.numbericFormat(bgTotpndptnRs, "###,###"));
//                            BigDecimal bgTotHrBruto = CommonUtil.dobelToBigDecimal(totHrBruto);
//                            pendapatanDokter.setTotalHrBruto(bgTotHrBruto);
//                            pendapatanDokter.setStTotalHrBruto(CommonUtil.numbericFormat(bgTotHrBruto, "###,###"));
//                            BigDecimal bgTotDppPph50 = CommonUtil.dobelToBigDecimal(totDppPph50);
//                            pendapatanDokter.setTotalDppPph50(bgTotDppPph50);
//                            pendapatanDokter.setStTotalDppPph50(CommonUtil.numbericFormat(bgTotDppPph50, "###,###"));
//                            BigDecimal bgTotDppPphKomulatif = CommonUtil.dobelToBigDecimal(totDppPphKomulatif);
//                            pendapatanDokter.setTotalDppPph21Komulatif(bgTotDppPphKomulatif);
//                            pendapatanDokter.setStTotalDppPph21Komulatif(CommonUtil.numbericFormat(bgTotDppPphKomulatif, "###,###"));
//                            BigDecimal bgTotDppPph21 = CommonUtil.dobelToBigDecimal(totDppPph21);
//                            pendapatanDokter.setTotalDppPph21(bgTotDppPph21);
//                            pendapatanDokter.setStTotalDppPph21(CommonUtil.numbericFormat(bgTotDppPph21, "###,###"));
//                            BigDecimal bgTotPphDipungut = CommonUtil.dobelToBigDecimal(totPphDipungut);
//                            pendapatanDokter.setTotalPphDipungut(bgTotPphDipungut);
//                            pendapatanDokter.setStTotalPphDipungut(CommonUtil.numbericFormat(bgTotPphDipungut, "###,###"));
//                            BigDecimal bgTotHrAktifitas = CommonUtil.dobelToBigDecimal(totHrAktifitas);
//                            pendapatanDokter.setTotalHrAktifitasNetto(bgTotHrAktifitas);
//                            pendapatanDokter.setStTotalHrAktifitasNetto(CommonUtil.numbericFormat(bgTotHrAktifitas, "###,###"));
//                            BigDecimal bgTotPotKs = CommonUtil.dobelToBigDecimal(totPotKs);
//                            pendapatanDokter.setTotalPotKs(bgTotPotKs);
//                            pendapatanDokter.setStTotalPotKs(CommonUtil.numbericFormat(bgTotPotKs, "###,###"));
//                            BigDecimal bgTotGajiBersih = CommonUtil.dobelToBigDecimal(totGajiBersih);
//                            pendapatanDokter.setTotalGajiBersih(bgTotGajiBersih);
//                            pendapatanDokter.setStTotalGajiBersih(CommonUtil.numbericFormat(bgTotGajiBersih, "###,###"));
//                        }
//
//                        String userLogin = CommonUtil.userLogin();
//                        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
//                        pendapatanDokter.setApprovalFlag("Y");
//                        pendapatanDokter.setApprovalWho(userLogin);
//                        pendapatanDokter.setApprovalDate(updateTime);
//                        pendapatanDokter.setFlag("Y");
//                        pendapatanDokter.setAction("C");
//                        pendapatanDokter.setCreatedDate(updateTime);
//                        pendapatanDokter.setLastUpdate(updateTime);
//                        pendapatanDokter.setCreatedWho(userLogin);
//                        pendapatanDokter.setLastUpdateWho(userLogin);
//
//                        listOfResult.add(pendapatanDokter);
//                    }
//                }
//            }
        }

        return listOfResult;
    }

    @Override
    public List<PendapatanDokter> getDataPendapatanDokter(PendapatanDokter bean) {
        logger.info("[PendapatanDokterBoImpl.getByCriteriaForPendapatanDokter] start process >>>");
        List<PendapatanDokter> listPendapatan = new ArrayList<>();

        if (bean != null){
            List<ImSimrsDokterKso> imSimrsDokterKsos = null;
            List<ItHrisPendapatanDokterEntity> itHrisPendapatanDokterEntity = null;
            List<Object[]> results = new ArrayList<Object[]>();
            List<ImHrisPajakEntity> imHrisPajakEntities = null;

            try{
                Map hsCriteria = new HashMap<>();
                hsCriteria.put("branch_id", bean.getBranchId());
                hsCriteria.put("flag", "Y");
                imSimrsDokterKsos = dokterKsoDao.getByCriteria(hsCriteria);
                Map map = new HashMap<>();
                map.put("flag", "Y");
                imHrisPajakEntities = pajakPendapatanDokterDao.getByCriteria(map);

                for (ImSimrsDokterKso simrsDokterKso : imSimrsDokterKsos){

                    Double hrBruto, pndptnRs, dppPph50,dppPphKomulatif = 0.0, dppPph21, tarif = 0.0, pphDipungut, hrAktifitas, potKs, gajiBersih;
                    Double totHrBruto = 0.0, totpndptnRs = 0.0, totBruto = 0.0, totDppPph50 = 0.0, totDppPphKomulatif = 0.0, totDppPph21 = 0.0,
                            totPphDipungut = 0.0, totHrAktifitas = 0.0, totPotKs = 0.0, totGajiBersih = 0.0, komulatifLastMonth = 0.0;
                    BigDecimal tarifInajbg, bruto, ksoPersen, tarifPajak = null, bgPersenKs, tarifPajakLebih = null;
                    String stTarif = null, dokterId = null, level = "", stTarifLebih = null;
                    Double pph50Lebih = 0.0, komulatifLebih=0.0, pph21Lebih = 0.0, tarifLebih = 0.0, pphDipungutLebih = 0.0, pphLebihAwal = 0.0, pphLebih = 0.0, pphFinalAwal = 0.0, pphFinal=0.0;

                    dokterId = simrsDokterKso.getNip();
                    results = pendapatanDokterDao.getDataPendapatan(bean.getBranchId(), bean.getBulan(), bean.getTahun(), dokterId);
                    komulatifLastMonth = pendapatanDokterDao.getPphKomulatif(dokterId, bean.getBulan(), bean.getTahun()).doubleValue();
                    level = pendapatanDokterDao.getLevel(dokterId, bean.getBulan(), bean.getTahun());
                    int id = 0;
                    PendapatanDokter pendapatanDokter;
                    if (results != null){
                        for (Object[] obj : results){
                            pendapatanDokter = new PendapatanDokter();
                            id++;
                            String jenisKso = String.valueOf(obj[1]);
                            String masterId = String.valueOf(obj[2]);
                            bruto = (BigDecimal) obj[0];

                            if ("tindakan".equalsIgnoreCase(jenisKso) && "bpjs".equalsIgnoreCase(masterId)){
                                ksoPersen = (BigDecimal) obj[3];
                                int kso = 100 - ksoPersen.intValue();
                                pndptnRs = bruto.doubleValue() * ksoPersen.intValue() / 100;
                                hrBruto = bruto.doubleValue() * kso/100;
                            }else {
                                ksoPersen = (BigDecimal) obj[5];
                                int kso = 100 - ksoPersen.intValue();
                                pndptnRs = bruto.doubleValue() * ksoPersen.intValue() / 100;
                                hrBruto = bruto.doubleValue() * kso/100;
                            }

//                            dppPph50 = bruto.doubleValue() * 0.5;
                            dppPph50 = hrBruto * 0.5;
                            if (komulatifLastMonth != 0.0)
                                dppPphKomulatif = dppPph50 + komulatifLastMonth;
                            else
                                dppPphKomulatif = dppPph50;
                            dppPph21 = dppPph50;

                            for (ImHrisPajakEntity entity : imHrisPajakEntities){
                                Double btsBawah = entity.getBatasBawah().doubleValue();
                                Double btsAtas = entity.getBatasAtas().doubleValue();
                                if (dppPphKomulatif > btsBawah && dppPphKomulatif <= btsAtas){
                                    tarif = entity.getPajak().doubleValue() / 100;
                                    tarifPajak = entity.getPajak();
                                    stTarif = entity.getPajak().toString();
                                }
                            }
//                            if (dppPphKomulatif <= 50000000) {
//                                tarif = 0.05;
//                                tarifPajak = BigDecimal.valueOf(5);
//                                stTarif = "5%";
//                            }
//                            else if (dppPphKomulatif > 50000000 && dppPphKomulatif <= 250000000) {
//                                tarif = 0.15;
//                                tarifPajak = BigDecimal.valueOf(15);
//                                stTarif = "15%";
//                            }
//                            else if (dppPphKomulatif > 250000000 && dppPphKomulatif <= 500000000){
//                                tarif = 0.25;
//                                tarifPajak = BigDecimal.valueOf(25);
//                                stTarif = "25%";
//                            }
//                            else {
//                                tarif = 0.30;
//                                tarifPajak = BigDecimal.valueOf(30);
//                                stTarif = "30%";
//                            }

                            pphDipungut = dppPph21 * tarif;
                            hrAktifitas = hrBruto - pphDipungut;

                            bgPersenKs = (BigDecimal) obj[4];
                            potKs = hrAktifitas * (bgPersenKs.doubleValue() / 100);
                            gajiBersih = hrBruto - pphDipungut - potKs;

                            totBruto = totBruto + bruto.doubleValue();
                            totpndptnRs = totpndptnRs + pndptnRs;
                            totHrBruto = totHrBruto + hrBruto;
                            totDppPph50 = totDppPph50 + dppPph50;
                            totDppPphKomulatif = totDppPphKomulatif + dppPphKomulatif;
                            totDppPph21 = totDppPph21 + dppPph21;
                            totPphDipungut = totPphDipungut + pphDipungut;
                            totHrAktifitas = totHrAktifitas + hrAktifitas;
                            totPotKs = totPotKs + potKs;
                            totGajiBersih = totGajiBersih + gajiBersih;

                            if (id == results.size()){
                                ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                                Branch branch = new Branch();
                                BranchBo branchBo = (BranchBo) context.getBean("branchBoProxy");
                                branch.setBranchId(bean.getBranchId());
                                branch.setFlag("Y");
                                List<Branch> branches = branchBo.getByCriteria(branch);
                                String branchName = branches.get(0).getBranchName();
                                pendapatanDokter.setBranchName(branchName);


                                if (!"".equalsIgnoreCase(level)){
                                    if ("1".equalsIgnoreCase(level) && totDppPphKomulatif > 50000000){
                                        totDppPph50 = 50000000 - komulatifLastMonth;
                                        totDppPphKomulatif = komulatifLastMonth + totDppPph50;
                                        totDppPph21 = totDppPph50;

                                        for (ImHrisPajakEntity entity : imHrisPajakEntities){
                                            Double btsBawah = entity.getBatasBawah().doubleValue();
                                            Double btsAtas = entity.getBatasAtas().doubleValue();
                                            if (totDppPphKomulatif > btsBawah && totDppPphKomulatif <= btsAtas){
                                                tarif = entity.getPajak().doubleValue() / 100;
                                                tarifPajak = entity.getPajak();
                                                stTarif = entity.getPajak().toString();
                                            }
                                        }
//                                        if (totDppPphKomulatif <= 50000000) {
//                                            tarif = 0.05;
//                                            tarifPajak = BigDecimal.valueOf(5);
//                                            stTarif = "5%";
//                                        }
//                                        else if (totDppPphKomulatif > 50000000 && totDppPphKomulatif <= 250000000) {
//                                            tarif = 0.15;
//                                            tarifPajak = BigDecimal.valueOf(15);
//                                            stTarif = "15%";
//                                        }
//                                        else if (totDppPphKomulatif > 250000000 && totDppPphKomulatif <= 500000000){
//                                            tarif = 0.25;
//                                            tarifPajak = BigDecimal.valueOf(25);
//                                            stTarif = "25%";
//                                        }
//                                        else {
//                                            tarif = 0.30;
//                                            tarifPajak = BigDecimal.valueOf(30);
//                                            stTarif = "30%";
//                                        }

                                        totPphDipungut = totDppPph21 * tarif;
//                                        totGajiBersih = totHrBruto - totPphDipungut - totPotKs;

                                        pph50Lebih = (totHrBruto*0.5)+komulatifLastMonth-totDppPphKomulatif;
                                        komulatifLebih = pph50Lebih + totDppPphKomulatif;
                                        pph21Lebih = pph50Lebih;

                                        for (ImHrisPajakEntity entity : imHrisPajakEntities){
                                            Double btsBawah = entity.getBatasBawah().doubleValue();
                                            Double btsAtas = entity.getBatasAtas().doubleValue();
                                            if (komulatifLebih > btsBawah && komulatifLebih <= btsAtas){
                                                tarifLebih = entity.getPajak().doubleValue() / 100;
                                                tarifPajakLebih = entity.getPajak();
                                                stTarifLebih = entity.getPajak().toString();
                                            }
                                        }
//                                        if (komulatifLebih <= 50000000) {
//                                            tarifLebih = 0.05;
//                                            tarifPajakLebih = BigDecimal.valueOf(5);
//                                            stTarifLebih = "5%";
//                                        }
//                                        else if (komulatifLebih > 50000000 && komulatifLebih <= 250000000) {
//                                            tarifLebih = 0.15;
//                                            tarifPajakLebih = BigDecimal.valueOf(15);
//                                            stTarifLebih = "15%";
//                                        }
//                                        else if (komulatifLebih > 250000000 && komulatifLebih <= 500000000){
//                                            tarifLebih = 0.25;
//                                            tarifPajakLebih = BigDecimal.valueOf(25);
//                                            stTarifLebih = "25%";
//                                        }
//                                        else {
//                                            tarifLebih = 0.30;
//                                            tarifPajakLebih = BigDecimal.valueOf(30);
//                                            stTarifLebih = "30%";
//                                        }

                                        pphDipungutLebih = pph21Lebih * tarifLebih;
                                        pphLebih = pphDipungutLebih;
                                        pphFinal = totPphDipungut + pphLebih;

                                        totHrAktifitas = totHrBruto - pphFinal;
                                        bgPersenKs = (BigDecimal) obj[4];
                                        totPotKs = totHrAktifitas * (bgPersenKs.doubleValue() / 100);

                                        totGajiBersih = totHrAktifitas - totPotKs;
                                        int uL = Integer.parseInt(level);
                                        uL++;
                                        level = String.valueOf(uL);

                                        pendapatanDokter.setDokterId(dokterId);
                                        pendapatanDokter.setDokterName(String.valueOf(obj[6]));
                                        pendapatanDokter.setBranchId(bean.getBranchId());
                                        pendapatanDokter.setBulan(bean.getBulan());
                                        pendapatanDokter.setTahun(bean.getTahun());
                                        pendapatanDokter.setKodeJabatan(String.valueOf(obj[7]));

                                        BigDecimal bgTotBruto = CommonUtil.dobelToBigDecimal(totBruto);
                                        pendapatanDokter.setTotalBruto(bgTotBruto);
                                        pendapatanDokter.setStTotalBruto(CommonUtil.numbericFormat(bgTotBruto, "###,###"));

                                        BigDecimal bgTotDppPph50 = CommonUtil.dobelToBigDecimal(totDppPph50);
                                        pendapatanDokter.setTotalDppPph50(bgTotDppPph50);
                                        pendapatanDokter.setStTotalDppPph50(CommonUtil.numbericFormat(bgTotDppPph50, "###,###"));

                                        BigDecimal bgTotDppPphKomulatif = CommonUtil.dobelToBigDecimal(totDppPphKomulatif);
                                        pendapatanDokter.setTotalDppPph21Komulatif(bgTotDppPphKomulatif);
                                        pendapatanDokter.setStTotalDppPph21Komulatif(CommonUtil.numbericFormat(bgTotDppPphKomulatif, "###,###"));

                                        BigDecimal bgTotDppPph21 = CommonUtil.dobelToBigDecimal(totDppPph21);
                                        pendapatanDokter.setTotalDppPph21(bgTotDppPph21);
                                        pendapatanDokter.setStTotalDppPph21(CommonUtil.numbericFormat(bgTotDppPph21, "###,###"));

                                        pendapatanDokter.setTarif(tarifPajak);
                                        pendapatanDokter.setStTarif(stTarif);

                                        BigDecimal bgTotPphDipungut = CommonUtil.dobelToBigDecimal(totPphDipungut);
                                        pendapatanDokter.setTotalPphDipungut(bgTotPphDipungut);
                                        pendapatanDokter.setStTotalPphDipungut(CommonUtil.numbericFormat(bgTotPphDipungut, "###,###"));

//                                        BigDecimal bgPphLebihAwal = CommonUtil.dobelToBigDecimal(0.0);
//                                        pendapatanDokter.setTotalPphLebihAwal(bgPphLebihAwal);
//                                        pendapatanDokter.setStTotalPphDipungut(CommonUtil.numbericFormat(bgPphLebihAwal, "###,###"));

                                        BigDecimal bgTotDpp50Lebih = CommonUtil.dobelToBigDecimal(pph50Lebih);
                                        pendapatanDokter.setTotalDpp50Lebih(bgTotDpp50Lebih);
                                        pendapatanDokter.setStTotalDpp50Lebih(CommonUtil.numbericFormat(bgTotDpp50Lebih, "###,###"));

                                        BigDecimal bgTotDppKomulatifLebih = CommonUtil.dobelToBigDecimal(komulatifLebih);
                                        pendapatanDokter.setTotalDppPph21KomulatifLebih(bgTotDppKomulatifLebih);
                                        pendapatanDokter.setStTotalDppPph21KomulatifLebih(CommonUtil.numbericFormat(bgTotDppKomulatifLebih, "###,###"));

                                        BigDecimal bgTotDppPph21Lebih = CommonUtil.dobelToBigDecimal(pph21Lebih);
                                        pendapatanDokter.setTotalDppPph21Lebih(bgTotDppPph21Lebih);
                                        pendapatanDokter.setStTotalDppPph21Lebih(CommonUtil.numbericFormat(bgTotDppPph21Lebih, "###,###"));

                                        pendapatanDokter.setTarifLebih(tarifPajakLebih);
                                        pendapatanDokter.setStTarifLebih(stTarifLebih);

                                        BigDecimal bgTotPphDipungutLebih = CommonUtil.dobelToBigDecimal(pphDipungutLebih);
                                        pendapatanDokter.setTotalPphDipungutLebih(bgTotPphDipungutLebih);
                                        pendapatanDokter.setStTotalPphDipungutLebih(CommonUtil.numbericFormat(bgTotPphDipungutLebih, "###,###"));

                                        BigDecimal bgTotPphLebih = CommonUtil.dobelToBigDecimal(pphLebih);
                                        pendapatanDokter.setTotalPphLebih(bgTotPphLebih);
                                        pendapatanDokter.setStTotalPphLebih(CommonUtil.numbericFormat(bgTotPphLebih, "###,###"));

                                        BigDecimal bgTotPphFinal = CommonUtil.dobelToBigDecimal(pphFinal);
                                        pendapatanDokter.setTotalPphFinal(bgTotPphFinal);
                                        pendapatanDokter.setStTotalPphFinal(CommonUtil.numbericFormat(bgTotPphFinal, "###,###"));

                                        BigDecimal bgTotGajiBersih = CommonUtil.dobelToBigDecimal(totGajiBersih);
                                        pendapatanDokter.setTotalGajiBersih(bgTotGajiBersih);
                                        pendapatanDokter.setStTotalGajiBersih(CommonUtil.numbericFormat(bgTotGajiBersih, "###,###"));

                                        String userLogin = CommonUtil.userLogin();
                                        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                                        pendapatanDokter.setApprovalFlag("Y");
                                        pendapatanDokter.setApprovalWho(userLogin);
                                        pendapatanDokter.setApprovalDate(updateTime);
                                        pendapatanDokter.setFlag("Y");
                                        pendapatanDokter.setAction("C");
                                        pendapatanDokter.setCreatedDate(updateTime);
                                        pendapatanDokter.setLastUpdate(updateTime);
                                        pendapatanDokter.setCreatedWho(userLogin);
                                        pendapatanDokter.setLastUpdateWho(userLogin);
                                        pendapatanDokter.setLevel(level);

                                        BigDecimal bgTotpndptnRs = CommonUtil.dobelToBigDecimal(totpndptnRs);
                                        pendapatanDokter.setTotalPendapatanRs(bgTotpndptnRs);
                                        pendapatanDokter.setStTotalPendapatanRs(CommonUtil.numbericFormat(bgTotpndptnRs, "###,###"));

                                        BigDecimal bgTotHrBruto = CommonUtil.dobelToBigDecimal(totHrBruto);
                                        pendapatanDokter.setTotalHrBruto(bgTotHrBruto);
                                        pendapatanDokter.setStTotalHrBruto(CommonUtil.numbericFormat(bgTotHrBruto, "###,###"));

                                        BigDecimal bgTotHrAktifitas = CommonUtil.dobelToBigDecimal(totHrAktifitas);
                                        pendapatanDokter.setTotalHrAktifitasNetto(bgTotHrAktifitas);
                                        pendapatanDokter.setStTotalHrAktifitasNetto(CommonUtil.numbericFormat(bgTotHrAktifitas, "###,###"));

                                        BigDecimal bgTotPotKs = CommonUtil.dobelToBigDecimal(totPotKs);
                                        pendapatanDokter.setTotalPotKs(bgTotPotKs);
                                        pendapatanDokter.setStTotalPotKs(CommonUtil.numbericFormat(bgTotPotKs, "###,###"));

                                        listPendapatan.add(pendapatanDokter);
                                    }
                                    else if ("2".equalsIgnoreCase(level) && totDppPphKomulatif > 250000000){
                                        totDppPph50 = 250000000 - komulatifLastMonth;
                                        totDppPphKomulatif = komulatifLastMonth + totDppPph50;
                                        totDppPph21 = totDppPph50;

                                        for (ImHrisPajakEntity entity : imHrisPajakEntities){
                                            Double btsBawah = entity.getBatasBawah().doubleValue();
                                            Double btsAtas = entity.getBatasAtas().doubleValue();
                                            if (totDppPphKomulatif > btsBawah && totDppPphKomulatif <= btsAtas){
                                                tarif = entity.getPajak().doubleValue() / 100;
                                                tarifPajak = entity.getPajak();
                                                stTarif = entity.getPajak().toString();
                                            }
                                        }
//                                        if (totDppPphKomulatif <= 50000000) {
//                                            tarif = 0.05;
//                                            tarifPajak = BigDecimal.valueOf(5);
//                                            stTarif = "5%";
//                                        }
//                                        else if (totDppPphKomulatif > 50000000 && totDppPphKomulatif <= 250000000) {
//                                            tarif = 0.15;
//                                            tarifPajak = BigDecimal.valueOf(15);
//                                            stTarif = "15%";
//                                        }
//                                        else if (totDppPphKomulatif > 250000000 && totDppPphKomulatif <= 500000000){
//                                            tarif = 0.25;
//                                            tarifPajak = BigDecimal.valueOf(25);
//                                            stTarif = "25%";
//                                        }
//                                        else {
//                                            tarif = 0.30;
//                                            tarifPajak = BigDecimal.valueOf(30);
//                                            stTarif = "30%";
//                                        }

                                        totPphDipungut = totDppPph21 * tarif;
//                                        totGajiBersih = totHrBruto - totPphDipungut - totPotKs;

                                        pph50Lebih = (totHrBruto*0.5)+komulatifLastMonth-totDppPphKomulatif;
                                        komulatifLebih = pph50Lebih + totDppPphKomulatif;
                                        pph21Lebih = pph50Lebih;

                                        for (ImHrisPajakEntity entity : imHrisPajakEntities){
                                            Double btsBawah = entity.getBatasBawah().doubleValue();
                                            Double btsAtas = entity.getBatasAtas().doubleValue();
                                            if (komulatifLebih > btsBawah && komulatifLebih <= btsAtas){
                                                tarifLebih = entity.getPajak().doubleValue() / 100;
                                                tarifPajakLebih = entity.getPajak();
                                                stTarifLebih = entity.getPajak().toString();
                                            }
                                        }
//                                        if (komulatifLebih <= 50000000) {
//                                            tarifLebih = 0.05;
//                                            tarifPajakLebih = BigDecimal.valueOf(5);
//                                            stTarifLebih = "5%";
//                                        }
//                                        else if (komulatifLebih > 50000000 && komulatifLebih <= 250000000) {
//                                            tarifLebih = 0.15;
//                                            tarifPajakLebih = BigDecimal.valueOf(15);
//                                            stTarifLebih = "15%";
//                                        }
//                                        else if (komulatifLebih > 250000000 && komulatifLebih <= 500000000){
//                                            tarifLebih = 0.25;
//                                            tarifPajakLebih = BigDecimal.valueOf(25);
//                                            stTarifLebih = "25%";
//                                        }
//                                        else {
//                                            tarifLebih = 0.30;
//                                            tarifPajakLebih = BigDecimal.valueOf(30);
//                                            stTarifLebih = "30%";
//                                        }

                                        pphDipungutLebih = pph21Lebih * tarifLebih;
                                        pphLebih = pphDipungutLebih;
                                        pphFinal = totPphDipungut + pphLebih;

                                        totHrAktifitas = totHrBruto - pphFinal;
                                        bgPersenKs = (BigDecimal) obj[4];
                                        totPotKs = totHrAktifitas * (bgPersenKs.doubleValue() / 100);

                                        totGajiBersih = totHrAktifitas - totPotKs;
                                        int uL = Integer.parseInt(level);
                                        uL++;
                                        level = String.valueOf(uL);

                                        pendapatanDokter.setDokterId(dokterId);
                                        pendapatanDokter.setDokterName(String.valueOf(obj[6]));
                                        pendapatanDokter.setBranchId(bean.getBranchId());
                                        pendapatanDokter.setBulan(bean.getBulan());
                                        pendapatanDokter.setTahun(bean.getTahun());
                                        pendapatanDokter.setKodeJabatan(String.valueOf(obj[7]));

                                        BigDecimal bgTotBruto = CommonUtil.dobelToBigDecimal(totBruto);
                                        pendapatanDokter.setTotalBruto(bgTotBruto);
                                        pendapatanDokter.setStTotalBruto(CommonUtil.numbericFormat(bgTotBruto, "###,###"));

                                        BigDecimal bgTotDppPph50 = CommonUtil.dobelToBigDecimal(totDppPph50);
                                        pendapatanDokter.setTotalDppPph50(bgTotDppPph50);
                                        pendapatanDokter.setStTotalDppPph50(CommonUtil.numbericFormat(bgTotDppPph50, "###,###"));

                                        BigDecimal bgTotDppPphKomulatif = CommonUtil.dobelToBigDecimal(totDppPphKomulatif);
                                        pendapatanDokter.setTotalDppPph21Komulatif(bgTotDppPphKomulatif);
                                        pendapatanDokter.setStTotalDppPph21Komulatif(CommonUtil.numbericFormat(bgTotDppPphKomulatif, "###,###"));

                                        BigDecimal bgTotDppPph21 = CommonUtil.dobelToBigDecimal(totDppPph21);
                                        pendapatanDokter.setTotalDppPph21(bgTotDppPph21);
                                        pendapatanDokter.setStTotalDppPph21(CommonUtil.numbericFormat(bgTotDppPph21, "###,###"));

                                        pendapatanDokter.setTarif(tarifPajak);
                                        pendapatanDokter.setStTarif(stTarif);

                                        BigDecimal bgTotPphDipungut = CommonUtil.dobelToBigDecimal(totPphDipungut);
                                        pendapatanDokter.setTotalPphDipungut(bgTotPphDipungut);
                                        pendapatanDokter.setStTotalPphDipungut(CommonUtil.numbericFormat(bgTotPphDipungut, "###,###"));

//                                        BigDecimal bgPphLebihAwal = CommonUtil.dobelToBigDecimal(0.0);
//                                        pendapatanDokter.setTotalPphLebihAwal(bgPphLebihAwal);
//                                        pendapatanDokter.setStTotalPphDipungut(CommonUtil.numbericFormat(bgPphLebihAwal, "###,###"));

                                        BigDecimal bgTotDpp50Lebih = CommonUtil.dobelToBigDecimal(pph50Lebih);
                                        pendapatanDokter.setTotalDpp50Lebih(bgTotDpp50Lebih);
                                        pendapatanDokter.setStTotalDpp50Lebih(CommonUtil.numbericFormat(bgTotDpp50Lebih, "###,###"));

                                        BigDecimal bgTotDppKomulatifLebih = CommonUtil.dobelToBigDecimal(komulatifLebih);
                                        pendapatanDokter.setTotalDppPph21KomulatifLebih(bgTotDppKomulatifLebih);
                                        pendapatanDokter.setStTotalDppPph21KomulatifLebih(CommonUtil.numbericFormat(bgTotDppKomulatifLebih, "###,###"));

                                        BigDecimal bgTotDppPph21Lebih = CommonUtil.dobelToBigDecimal(pph21Lebih);
                                        pendapatanDokter.setTotalDppPph21Lebih(bgTotDppPph21Lebih);
                                        pendapatanDokter.setStTotalDppPph21Lebih(CommonUtil.numbericFormat(bgTotDppPph21Lebih, "###,###"));

                                        pendapatanDokter.setTarifLebih(tarifPajakLebih);
                                        pendapatanDokter.setStTarifLebih(stTarifLebih);

                                        BigDecimal bgTotPphDipungutLebih = CommonUtil.dobelToBigDecimal(pphDipungutLebih);
                                        pendapatanDokter.setTotalPphDipungutLebih(bgTotPphDipungutLebih);
                                        pendapatanDokter.setStTotalPphDipungutLebih(CommonUtil.numbericFormat(bgTotPphDipungutLebih, "###,###"));

                                        BigDecimal bgTotPphLebih = CommonUtil.dobelToBigDecimal(pphLebih);
                                        pendapatanDokter.setTotalPphLebih(bgTotPphLebih);
                                        pendapatanDokter.setStTotalPphLebih(CommonUtil.numbericFormat(bgTotPphLebih, "###,###"));

                                        BigDecimal bgTotPphFinal = CommonUtil.dobelToBigDecimal(pphFinal);
                                        pendapatanDokter.setTotalPphFinal(bgTotPphFinal);
                                        pendapatanDokter.setStTotalPphFinal(CommonUtil.numbericFormat(bgTotPphFinal, "###,###"));

                                        BigDecimal bgTotGajiBersih = CommonUtil.dobelToBigDecimal(totGajiBersih);
                                        pendapatanDokter.setTotalGajiBersih(bgTotGajiBersih);
                                        pendapatanDokter.setStTotalGajiBersih(CommonUtil.numbericFormat(bgTotGajiBersih, "###,###"));

                                        String userLogin = CommonUtil.userLogin();
                                        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                                        pendapatanDokter.setApprovalFlag("Y");
                                        pendapatanDokter.setApprovalWho(userLogin);
                                        pendapatanDokter.setApprovalDate(updateTime);
                                        pendapatanDokter.setFlag("Y");
                                        pendapatanDokter.setAction("C");
                                        pendapatanDokter.setCreatedDate(updateTime);
                                        pendapatanDokter.setLastUpdate(updateTime);
                                        pendapatanDokter.setCreatedWho(userLogin);
                                        pendapatanDokter.setLastUpdateWho(userLogin);
                                        pendapatanDokter.setLevel(level);

                                        BigDecimal bgTotpndptnRs = CommonUtil.dobelToBigDecimal(totpndptnRs);
                                        pendapatanDokter.setTotalPendapatanRs(bgTotpndptnRs);
                                        pendapatanDokter.setStTotalPendapatanRs(CommonUtil.numbericFormat(bgTotpndptnRs, "###,###"));

                                        BigDecimal bgTotHrBruto = CommonUtil.dobelToBigDecimal(totHrBruto);
                                        pendapatanDokter.setTotalHrBruto(bgTotHrBruto);
                                        pendapatanDokter.setStTotalHrBruto(CommonUtil.numbericFormat(bgTotHrBruto, "###,###"));

                                        BigDecimal bgTotHrAktifitas = CommonUtil.dobelToBigDecimal(totHrAktifitas);
                                        pendapatanDokter.setTotalHrAktifitasNetto(bgTotHrAktifitas);
                                        pendapatanDokter.setStTotalHrAktifitasNetto(CommonUtil.numbericFormat(bgTotHrAktifitas, "###,###"));

                                        BigDecimal bgTotPotKs = CommonUtil.dobelToBigDecimal(totPotKs);
                                        pendapatanDokter.setTotalPotKs(bgTotPotKs);
                                        pendapatanDokter.setStTotalPotKs(CommonUtil.numbericFormat(bgTotPotKs, "###,###"));

                                        listPendapatan.add(pendapatanDokter);

                                    }
                                    else if ("3".equalsIgnoreCase(level) && totDppPphKomulatif > 500000000){
                                        totDppPph50 = 500000000 - komulatifLastMonth;
                                        totDppPphKomulatif = komulatifLastMonth + totDppPph50;
                                        totDppPph21 = totDppPph50;

                                        for (ImHrisPajakEntity entity : imHrisPajakEntities){
                                            Double btsBawah = entity.getBatasBawah().doubleValue();
                                            Double btsAtas = entity.getBatasAtas().doubleValue();
                                            if (totDppPphKomulatif > btsBawah && totDppPphKomulatif <= btsAtas){
                                                tarif = entity.getPajak().doubleValue() / 100;
                                                tarifPajak = entity.getPajak();
                                                stTarif = entity.getPajak().toString();
                                            }
                                        }
//                                        if (totDppPphKomulatif <= 50000000) {
//                                            tarif = 0.05;
//                                            tarifPajak = BigDecimal.valueOf(5);
//                                            stTarif = "5%";
//                                        }
//                                        else if (totDppPphKomulatif > 50000000 && totDppPphKomulatif <= 250000000) {
//                                            tarif = 0.15;
//                                            tarifPajak = BigDecimal.valueOf(15);
//                                            stTarif = "15%";
//                                        }
//                                        else if (totDppPphKomulatif > 250000000 && totDppPphKomulatif <= 500000000){
//                                            tarif = 0.25;
//                                            tarifPajak = BigDecimal.valueOf(25);
//                                            stTarif = "25%";
//                                        }
//                                        else {
//                                            tarif = 0.30;
//                                            tarifPajak = BigDecimal.valueOf(30);
//                                            stTarif = "30%";
//                                        }

                                        totPphDipungut = totDppPph21 * tarif;
//                                        totGajiBersih = totHrBruto - totPphDipungut - totPotKs;

                                        pph50Lebih = (totHrBruto*0.5)+komulatifLastMonth-totDppPphKomulatif;
                                        komulatifLebih = pph50Lebih + totDppPphKomulatif;
                                        pph21Lebih = pph50Lebih;

                                        for (ImHrisPajakEntity entity : imHrisPajakEntities){
                                            Double btsBawah = entity.getBatasBawah().doubleValue();
                                            Double btsAtas = entity.getBatasAtas().doubleValue();
                                            if (komulatifLebih > btsBawah && komulatifLebih <= btsAtas){
                                                tarifLebih = entity.getPajak().doubleValue() / 100;
                                                tarifPajakLebih = entity.getPajak();
                                                stTarifLebih = entity.getPajak().toString();
                                            }
                                        }
//                                        if (komulatifLebih <= 50000000) {
//                                            tarifLebih = 0.05;
//                                            tarifPajakLebih = BigDecimal.valueOf(5);
//                                            stTarifLebih = "5%";
//                                        }
//                                        else if (komulatifLebih > 50000000 && komulatifLebih <= 250000000) {
//                                            tarifLebih = 0.15;
//                                            tarifPajakLebih = BigDecimal.valueOf(15);
//                                            stTarifLebih = "15%";
//                                        }
//                                        else if (komulatifLebih > 250000000 && komulatifLebih <= 500000000){
//                                            tarifLebih = 0.25;
//                                            tarifPajakLebih = BigDecimal.valueOf(25);
//                                            stTarifLebih = "25%";
//                                        }
//                                        else {
//                                            tarifLebih = 0.30;
//                                            tarifPajakLebih = BigDecimal.valueOf(30);
//                                            stTarifLebih = "30%";
//                                        }

                                        pphDipungutLebih = pph21Lebih * tarifLebih;
                                        pphLebih = pphDipungutLebih;
                                        pphFinal = totPphDipungut + pphLebih;

                                        totHrAktifitas = totHrBruto - pphFinal;
                                        bgPersenKs = (BigDecimal) obj[4];
                                        totPotKs = totHrAktifitas * (bgPersenKs.doubleValue() / 100);

                                        totGajiBersih = totHrAktifitas - totPotKs;
                                        int uL = Integer.parseInt(level);
                                        uL++;
                                        level = String.valueOf(uL);

                                        pendapatanDokter.setDokterId(dokterId);
                                        pendapatanDokter.setDokterName(String.valueOf(obj[6]));
                                        pendapatanDokter.setBranchId(bean.getBranchId());
                                        pendapatanDokter.setBulan(bean.getBulan());
                                        pendapatanDokter.setTahun(bean.getTahun());
                                        pendapatanDokter.setKodeJabatan(String.valueOf(obj[7]));

                                        BigDecimal bgTotBruto = CommonUtil.dobelToBigDecimal(totBruto);
                                        pendapatanDokter.setTotalBruto(bgTotBruto);
                                        pendapatanDokter.setStTotalBruto(CommonUtil.numbericFormat(bgTotBruto, "###,###"));

                                        BigDecimal bgTotDppPph50 = CommonUtil.dobelToBigDecimal(totDppPph50);
                                        pendapatanDokter.setTotalDppPph50(bgTotDppPph50);
                                        pendapatanDokter.setStTotalDppPph50(CommonUtil.numbericFormat(bgTotDppPph50, "###,###"));

                                        BigDecimal bgTotDppPphKomulatif = CommonUtil.dobelToBigDecimal(totDppPphKomulatif);
                                        pendapatanDokter.setTotalDppPph21Komulatif(bgTotDppPphKomulatif);
                                        pendapatanDokter.setStTotalDppPph21Komulatif(CommonUtil.numbericFormat(bgTotDppPphKomulatif, "###,###"));

                                        BigDecimal bgTotDppPph21 = CommonUtil.dobelToBigDecimal(totDppPph21);
                                        pendapatanDokter.setTotalDppPph21(bgTotDppPph21);
                                        pendapatanDokter.setStTotalDppPph21(CommonUtil.numbericFormat(bgTotDppPph21, "###,###"));

                                        pendapatanDokter.setTarif(tarifPajak);
                                        pendapatanDokter.setStTarif(stTarif);

                                        BigDecimal bgTotPphDipungut = CommonUtil.dobelToBigDecimal(totPphDipungut);
                                        pendapatanDokter.setTotalPphDipungut(bgTotPphDipungut);
                                        pendapatanDokter.setStTotalPphDipungut(CommonUtil.numbericFormat(bgTotPphDipungut, "###,###"));

//                                        BigDecimal bgPphLebihAwal = CommonUtil.dobelToBigDecimal(0.0);
//                                        pendapatanDokter.setTotalPphLebihAwal(bgPphLebihAwal);
//                                        pendapatanDokter.setStTotalPphDipungut(CommonUtil.numbericFormat(bgPphLebihAwal, "###,###"));

                                        BigDecimal bgTotDpp50Lebih = CommonUtil.dobelToBigDecimal(pph50Lebih);
                                        pendapatanDokter.setTotalDpp50Lebih(bgTotDpp50Lebih);
                                        pendapatanDokter.setStTotalDpp50Lebih(CommonUtil.numbericFormat(bgTotDpp50Lebih, "###,###"));

                                        BigDecimal bgTotDppKomulatifLebih = CommonUtil.dobelToBigDecimal(komulatifLebih);
                                        pendapatanDokter.setTotalDppPph21KomulatifLebih(bgTotDppKomulatifLebih);
                                        pendapatanDokter.setStTotalDppPph21KomulatifLebih(CommonUtil.numbericFormat(bgTotDppKomulatifLebih, "###,###"));

                                        BigDecimal bgTotDppPph21Lebih = CommonUtil.dobelToBigDecimal(pph21Lebih);
                                        pendapatanDokter.setTotalDppPph21Lebih(bgTotDppPph21Lebih);
                                        pendapatanDokter.setStTotalDppPph21Lebih(CommonUtil.numbericFormat(bgTotDppPph21Lebih, "###,###"));

                                        pendapatanDokter.setTarifLebih(tarifPajakLebih);
                                        pendapatanDokter.setStTarifLebih(stTarifLebih);

                                        BigDecimal bgTotPphDipungutLebih = CommonUtil.dobelToBigDecimal(pphDipungutLebih);
                                        pendapatanDokter.setTotalPphDipungutLebih(bgTotPphDipungutLebih);
                                        pendapatanDokter.setStTotalPphDipungutLebih(CommonUtil.numbericFormat(bgTotPphDipungutLebih, "###,###"));

                                        BigDecimal bgTotPphLebih = CommonUtil.dobelToBigDecimal(pphLebih);
                                        pendapatanDokter.setTotalPphLebih(bgTotPphLebih);
                                        pendapatanDokter.setStTotalPphLebih(CommonUtil.numbericFormat(bgTotPphLebih, "###,###"));

                                        BigDecimal bgTotPphFinal = CommonUtil.dobelToBigDecimal(pphFinal);
                                        pendapatanDokter.setTotalPphFinal(bgTotPphFinal);
                                        pendapatanDokter.setStTotalPphFinal(CommonUtil.numbericFormat(bgTotPphFinal, "###,###"));

                                        BigDecimal bgTotGajiBersih = CommonUtil.dobelToBigDecimal(totGajiBersih);
                                        pendapatanDokter.setTotalGajiBersih(bgTotGajiBersih);
                                        pendapatanDokter.setStTotalGajiBersih(CommonUtil.numbericFormat(bgTotGajiBersih, "###,###"));

                                        String userLogin = CommonUtil.userLogin();
                                        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                                        pendapatanDokter.setApprovalFlag("Y");
                                        pendapatanDokter.setApprovalWho(userLogin);
                                        pendapatanDokter.setApprovalDate(updateTime);
                                        pendapatanDokter.setFlag("Y");
                                        pendapatanDokter.setAction("C");
                                        pendapatanDokter.setCreatedDate(updateTime);
                                        pendapatanDokter.setLastUpdate(updateTime);
                                        pendapatanDokter.setCreatedWho(userLogin);
                                        pendapatanDokter.setLastUpdateWho(userLogin);
                                        pendapatanDokter.setLevel(level);

                                        BigDecimal bgTotpndptnRs = CommonUtil.dobelToBigDecimal(totpndptnRs);
                                        pendapatanDokter.setTotalPendapatanRs(bgTotpndptnRs);
                                        pendapatanDokter.setStTotalPendapatanRs(CommonUtil.numbericFormat(bgTotpndptnRs, "###,###"));

                                        BigDecimal bgTotHrBruto = CommonUtil.dobelToBigDecimal(totHrBruto);
                                        pendapatanDokter.setTotalHrBruto(bgTotHrBruto);
                                        pendapatanDokter.setStTotalHrBruto(CommonUtil.numbericFormat(bgTotHrBruto, "###,###"));

                                        BigDecimal bgTotHrAktifitas = CommonUtil.dobelToBigDecimal(totHrAktifitas);
                                        pendapatanDokter.setTotalHrAktifitasNetto(bgTotHrAktifitas);
                                        pendapatanDokter.setStTotalHrAktifitasNetto(CommonUtil.numbericFormat(bgTotHrAktifitas, "###,###"));

                                        BigDecimal bgTotPotKs = CommonUtil.dobelToBigDecimal(totPotKs);
                                        pendapatanDokter.setTotalPotKs(bgTotPotKs);
                                        pendapatanDokter.setStTotalPotKs(CommonUtil.numbericFormat(bgTotPotKs, "###,###"));

                                        listPendapatan.add(pendapatanDokter);
                                    }else {
                                        pphFinal = pphLebih + totPphDipungut;
                                        totHrAktifitas = totHrBruto - pphFinal;
                                        totGajiBersih = totHrAktifitas - totPotKs;

                                        pendapatanDokter.setDokterId(dokterId);
                                        pendapatanDokter.setDokterName(String.valueOf(obj[6]));
                                        pendapatanDokter.setBranchId(bean.getBranchId());
                                        pendapatanDokter.setBulan(bean.getBulan());
                                        pendapatanDokter.setTahun(bean.getTahun());
                                        pendapatanDokter.setKodeJabatan(String.valueOf(obj[7]));

                                        BigDecimal bgTotBruto = CommonUtil.dobelToBigDecimal(totBruto);
                                        pendapatanDokter.setTotalBruto(bgTotBruto);
                                        pendapatanDokter.setStTotalBruto(CommonUtil.numbericFormat(bgTotBruto, "###,###"));

                                        BigDecimal bgTotDppPph50 = CommonUtil.dobelToBigDecimal(totDppPph50);
                                        pendapatanDokter.setTotalDppPph50(bgTotDppPph50);
                                        pendapatanDokter.setStTotalDppPph50(CommonUtil.numbericFormat(bgTotDppPph50, "###,###"));

                                        BigDecimal bgTotDppPphKomulatif = CommonUtil.dobelToBigDecimal(totDppPphKomulatif);
                                        pendapatanDokter.setTotalDppPph21Komulatif(bgTotDppPphKomulatif);
                                        pendapatanDokter.setStTotalDppPph21Komulatif(CommonUtil.numbericFormat(bgTotDppPphKomulatif, "###,###"));

                                        BigDecimal bgTotDppPph21 = CommonUtil.dobelToBigDecimal(totDppPph21);
                                        pendapatanDokter.setTotalDppPph21(bgTotDppPph21);
                                        pendapatanDokter.setStTotalDppPph21(CommonUtil.numbericFormat(bgTotDppPph21, "###,###"));

                                        pendapatanDokter.setTarif(tarifPajak);
                                        pendapatanDokter.setStTarif(stTarif);

                                        BigDecimal bgTotPphDipungut = CommonUtil.dobelToBigDecimal(totPphDipungut);
                                        pendapatanDokter.setTotalPphDipungut(bgTotPphDipungut);
                                        pendapatanDokter.setStTotalPphDipungut(CommonUtil.numbericFormat(bgTotPphDipungut, "###,###"));

                                        BigDecimal bgTotPphLebih = CommonUtil.dobelToBigDecimal(pphLebih);
                                        pendapatanDokter.setTotalPphLebih(bgTotPphLebih);
                                        pendapatanDokter.setStTotalPphLebih(CommonUtil.numbericFormat(bgTotPphLebih, "###,###"));

                                        BigDecimal bgTotPphFinal = CommonUtil.dobelToBigDecimal(pphFinal);
                                        pendapatanDokter.setTotalPphFinal(bgTotPphFinal);
                                        pendapatanDokter.setStTotalPphFinal(CommonUtil.numbericFormat(bgTotPphFinal, "###,###"));

                                        BigDecimal bgTotGajiBersih = CommonUtil.dobelToBigDecimal(totGajiBersih);
                                        pendapatanDokter.setTotalGajiBersih(bgTotGajiBersih);
                                        pendapatanDokter.setStTotalGajiBersih(CommonUtil.numbericFormat(bgTotGajiBersih, "###,###"));

                                        String userLogin = CommonUtil.userLogin();
                                        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                                        pendapatanDokter.setApprovalFlag("Y");
                                        pendapatanDokter.setApprovalWho(userLogin);
                                        pendapatanDokter.setApprovalDate(updateTime);
                                        pendapatanDokter.setFlag("Y");
                                        pendapatanDokter.setAction("C");
                                        pendapatanDokter.setCreatedDate(updateTime);
                                        pendapatanDokter.setLastUpdate(updateTime);
                                        pendapatanDokter.setCreatedWho(userLogin);
                                        pendapatanDokter.setLastUpdateWho(userLogin);
                                        pendapatanDokter.setLevel("1");

                                        BigDecimal bgTotpndptnRs = CommonUtil.dobelToBigDecimal(totpndptnRs);
                                        pendapatanDokter.setTotalPendapatanRs(bgTotpndptnRs);
                                        pendapatanDokter.setStTotalPendapatanRs(CommonUtil.numbericFormat(bgTotpndptnRs, "###,###"));

                                        BigDecimal bgTotHrBruto = CommonUtil.dobelToBigDecimal(totHrBruto);
                                        pendapatanDokter.setTotalHrBruto(bgTotHrBruto);
                                        pendapatanDokter.setStTotalHrBruto(CommonUtil.numbericFormat(bgTotHrBruto, "###,###"));

                                        BigDecimal bgTotHrAktifitas = CommonUtil.dobelToBigDecimal(totHrAktifitas);
                                        pendapatanDokter.setTotalHrAktifitasNetto(bgTotHrAktifitas);
                                        pendapatanDokter.setStTotalHrAktifitasNetto(CommonUtil.numbericFormat(bgTotHrAktifitas, "###,###"));

                                        BigDecimal bgTotPotKs = CommonUtil.dobelToBigDecimal(totPotKs);
                                        pendapatanDokter.setTotalPotKs(bgTotPotKs);
                                        pendapatanDokter.setStTotalPotKs(CommonUtil.numbericFormat(bgTotPotKs, "###,###"));

                                        listPendapatan.add(pendapatanDokter);
                                    }
                                }else {
                                    pphFinal = pphLebih + totPphDipungut;
                                    totHrAktifitas = totHrBruto - pphFinal;
                                    totGajiBersih = totHrAktifitas - totPotKs;

                                    pendapatanDokter.setDokterId(dokterId);
                                    pendapatanDokter.setDokterName(String.valueOf(obj[6]));
                                    pendapatanDokter.setBranchId(bean.getBranchId());
                                    pendapatanDokter.setBulan(bean.getBulan());
                                    pendapatanDokter.setTahun(bean.getTahun());
                                    pendapatanDokter.setKodeJabatan(String.valueOf(obj[7]));

                                    BigDecimal bgTotBruto = CommonUtil.dobelToBigDecimal(totBruto);
                                    pendapatanDokter.setTotalBruto(bgTotBruto);
                                    pendapatanDokter.setStTotalBruto(CommonUtil.numbericFormat(bgTotBruto, "###,###"));

                                    BigDecimal bgTotDppPph50 = CommonUtil.dobelToBigDecimal(totDppPph50);
                                    pendapatanDokter.setTotalDppPph50(bgTotDppPph50);
                                    pendapatanDokter.setStTotalDppPph50(CommonUtil.numbericFormat(bgTotDppPph50, "###,###"));

                                    BigDecimal bgTotDppPphKomulatif = CommonUtil.dobelToBigDecimal(totDppPphKomulatif);
                                    pendapatanDokter.setTotalDppPph21Komulatif(bgTotDppPphKomulatif);
                                    pendapatanDokter.setStTotalDppPph21Komulatif(CommonUtil.numbericFormat(bgTotDppPphKomulatif, "###,###"));

                                    BigDecimal bgTotDppPph21 = CommonUtil.dobelToBigDecimal(totDppPph21);
                                    pendapatanDokter.setTotalDppPph21(bgTotDppPph21);
                                    pendapatanDokter.setStTotalDppPph21(CommonUtil.numbericFormat(bgTotDppPph21, "###,###"));

                                    pendapatanDokter.setTarif(tarifPajak);
                                    pendapatanDokter.setStTarif(stTarif);

                                    BigDecimal bgTotPphDipungut = CommonUtil.dobelToBigDecimal(totPphDipungut);
                                    pendapatanDokter.setTotalPphDipungut(bgTotPphDipungut);
                                    pendapatanDokter.setStTotalPphDipungut(CommonUtil.numbericFormat(bgTotPphDipungut, "###,###"));

                                    BigDecimal bgTotPphLebih = CommonUtil.dobelToBigDecimal(pphLebih);
                                    pendapatanDokter.setTotalPphLebih(bgTotPphLebih);
                                    pendapatanDokter.setStTotalPphLebih(CommonUtil.numbericFormat(bgTotPphLebih, "###,###"));

                                    BigDecimal bgTotPphFinal = CommonUtil.dobelToBigDecimal(pphFinal);
                                    pendapatanDokter.setTotalPphFinal(bgTotPphFinal);
                                    pendapatanDokter.setStTotalPphFinal(CommonUtil.numbericFormat(bgTotPphFinal, "###,###"));

                                    BigDecimal bgTotGajiBersih = CommonUtil.dobelToBigDecimal(totGajiBersih);
                                    pendapatanDokter.setTotalGajiBersih(bgTotGajiBersih);
                                    pendapatanDokter.setStTotalGajiBersih(CommonUtil.numbericFormat(bgTotGajiBersih, "###,###"));

                                    String userLogin = CommonUtil.userLogin();
                                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                                    pendapatanDokter.setApprovalFlag("Y");
                                    pendapatanDokter.setApprovalWho(userLogin);
                                    pendapatanDokter.setApprovalDate(updateTime);
                                    pendapatanDokter.setFlag("Y");
                                    pendapatanDokter.setAction("C");
                                    pendapatanDokter.setCreatedDate(updateTime);
                                    pendapatanDokter.setLastUpdate(updateTime);
                                    pendapatanDokter.setCreatedWho(userLogin);
                                    pendapatanDokter.setLastUpdateWho(userLogin);
                                    pendapatanDokter.setLevel("1");

                                    BigDecimal bgTotpndptnRs = CommonUtil.dobelToBigDecimal(totpndptnRs);
                                    pendapatanDokter.setTotalPendapatanRs(bgTotpndptnRs);
                                    pendapatanDokter.setStTotalPendapatanRs(CommonUtil.numbericFormat(bgTotpndptnRs, "###,###"));

                                    BigDecimal bgTotHrBruto = CommonUtil.dobelToBigDecimal(totHrBruto);
                                    pendapatanDokter.setTotalHrBruto(bgTotHrBruto);
                                    pendapatanDokter.setStTotalHrBruto(CommonUtil.numbericFormat(bgTotHrBruto, "###,###"));

                                    BigDecimal bgTotHrAktifitas = CommonUtil.dobelToBigDecimal(totHrAktifitas);
                                    pendapatanDokter.setTotalHrAktifitasNetto(bgTotHrAktifitas);
                                    pendapatanDokter.setStTotalHrAktifitasNetto(CommonUtil.numbericFormat(bgTotHrAktifitas, "###,###"));

                                    BigDecimal bgTotPotKs = CommonUtil.dobelToBigDecimal(totPotKs);
                                    pendapatanDokter.setTotalPotKs(bgTotPotKs);
                                    pendapatanDokter.setStTotalPotKs(CommonUtil.numbericFormat(bgTotPotKs, "###,###"));

                                    listPendapatan.add(pendapatanDokter);
                                }
                            }
                        }
                    }
                }

                if (listPendapatan.size() == 0)
                    throw new GeneralBOException("Gagal Memproses, Data pada tanggal "+bean.getBulan()+"-"+bean.getTahun()+" Belum Tersedia");
            }catch (HibernateException e){
                logger.error("[PendapatanDokterBoImpl.getByCriteriaForPendapatanDokter] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode lembur, please inform to your admin...," + e.getMessage());
            }
        }

        return listPendapatan;
    }

    @Override
    public List<PendapatanDokter> getDetailPendapatanPph(String idPendapatan) {
        logger.info("[PendapatanDokterBoImpl.getDetailPendapatanPph] start process >>>");
        List<PendapatanDokter> listOfResult = new ArrayList<>();
        List<Object[]> results = new ArrayList<Object[]>();

        try{
            results = pendapatanDokterDao.getDataPendapatanPphLebih(idPendapatan);
        }catch (HibernateException e){
            logger.error("[PendapatanDokterBoImpl.getByCriteriaForPendapatanDokter] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data alat by Kode lembur, please inform to your admin...," + e.getMessage());
        }

        if (results != null){
            for (Object[] objects : results){
                PendapatanDokter pendapatanDokter = new PendapatanDokter();
                BigDecimal komulatif = (BigDecimal) objects[0];
                pendapatanDokter.setStTotalDppPph21Komulatif(CommonUtil.numbericFormat(komulatif, "###,###"));
                BigDecimal pph21 = (BigDecimal) objects[1];
                pendapatanDokter.setStTotalDppPph21(CommonUtil.numbericFormat(pph21, "###,###"));
                BigDecimal pajak = (BigDecimal) objects[2];
                pendapatanDokter.setStTarif(CommonUtil.numbericFormat(pajak, "###,###"));
                BigDecimal pphDipungut = (BigDecimal) objects[3];
                pendapatanDokter.setStTotalPphDipungut(CommonUtil.numbericFormat(pphDipungut, "###,###"));


                BigDecimal pphLebih = (BigDecimal) objects[4];
                pendapatanDokter.setStTotalPphLebih(CommonUtil.numbericFormat(pphLebih, "###,###"));
                BigDecimal pphFinal = (BigDecimal) objects[5];
                pendapatanDokter.setStTotalPphFinal(CommonUtil.numbericFormat(pphFinal, "###,###"));
                BigDecimal pph21Lebih = (BigDecimal) objects[6];
                pendapatanDokter.setStTotalDppPph21Lebih(CommonUtil.numbericFormat(pph21Lebih, "###,###"));
                BigDecimal komulatifLebih = (BigDecimal) objects[7];
                pendapatanDokter.setStTotalDppPph21KomulatifLebih(CommonUtil.numbericFormat(komulatifLebih, "###,###"));


                BigDecimal pajakLebih = (BigDecimal) objects[8];
                pendapatanDokter.setStTarifLebih(CommonUtil.numbericFormat(pajakLebih, "###,###"));
                BigDecimal pphDipungutLebih = (BigDecimal) objects[9];
                pendapatanDokter.setStTotalPphDipungutLebih(CommonUtil.numbericFormat(pphDipungutLebih, "###,###"));

                listOfResult.add(pendapatanDokter);
            }
        }

        return listOfResult;
    }

    @Override
    public List<PendapatanDokter> getDetailPendapatan(PendapatanDokter bean) {
        logger.info("[PendapatanDokterBoImpl.getByCriteria] start process >>>");
        List<PendapatanDokter> listOfResult = new ArrayList<>();

        if (bean != null){
            Map hsCriteria = new HashMap<>();

            if (bean.getPendapatanDokterId() != null && !"".equalsIgnoreCase(bean.getPendapatanDokterId())){
                hsCriteria.put("pendapatan_dokter_id", bean.getPendapatanDokterId());
            }
            hsCriteria.put("flag", "Y");

            List<ItHrisPendapatanDokterDetailEntity> pendapatanDokterEntityList = null;
            try{
                pendapatanDokterEntityList = pendapatanDokterDetailDao.getByPendapatanId(hsCriteria);
            }catch (HibernateException e){
                logger.error("[PendapatanDokterBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (pendapatanDokterEntityList != null){
                PendapatanDokter pendapatanDokter;
                for (ItHrisPendapatanDokterDetailEntity entity : pendapatanDokterEntityList) {
                    pendapatanDokter = new PendapatanDokter();
                    pendapatanDokter.setPendapatanDokterId(entity.getPendapatanDokterId());
                    pendapatanDokter.setActivityId(entity.getActivityId());
                    pendapatanDokter.setActivityName(entity.getActivityName());
                    pendapatanDokter.setPoliId(entity.getPoliId());
                    pendapatanDokter.setPoliName(entity.getPoliName());
                    pendapatanDokter.setBruto(CommonUtil.numbericFormat(entity.getBiaya(), "###,###"));

                    pendapatanDokter.setNoReg(entity.getNoTrans());
                    pendapatanDokter.setJenisRawat(entity.getJenisRawat());
                    pendapatanDokter.setKdjnspas(entity.getKdjnspas());
                    pendapatanDokter.setNamaPasien(entity.getNamaPasien());
                    pendapatanDokter.setTanggal(entity.getTanggal());
                    pendapatanDokter.setKeterangan(entity.getKeterangan());
                    if (entity.getTarifInacbg() != null)
                        pendapatanDokter.setTarifInacbg(CommonUtil.numbericFormat(entity.getTarifInacbg(), "###,###"));
                    else
                        pendapatanDokter.setTarifInacbg("-");

                    pendapatanDokter.setBruto(CommonUtil.numbericFormat(entity.getBruto(), "###,###"));
                    pendapatanDokter.setPendapatanRs(CommonUtil.numbericFormat(entity.getPendapatanRs(), "###,###"));
                    pendapatanDokter.setHrBruto(CommonUtil.numbericFormat(entity.getHrBruto(), "###,###"));
                    pendapatanDokter.setDppPph21(CommonUtil.numbericFormat(entity.getDppPph21(), "###,###"));
                    pendapatanDokter.setDppPph21Komulatif(CommonUtil.numbericFormat(entity.getDppPph21Komulatif(), "###,###"));
                    pendapatanDokter.setStTarif(CommonUtil.numbericFormat(entity.getPajak(), "###,###"));
                    pendapatanDokter.setPphDipungut(CommonUtil.numbericFormat(entity.getPotonganPajak(), "###,###"));
                    pendapatanDokter.setHrAktifitasNetto(CommonUtil.numbericFormat(entity.getHrAktifitasNetto(), "###,###"));
                    pendapatanDokter.setPotKs(CommonUtil.numbericFormat(entity.getPotonganKs(), "###,###"));
                    pendapatanDokter.setGajiBersih(CommonUtil.numbericFormat(entity.getHrNetto(), "###,###"));

                    pendapatanDokter.setMasterId(entity.getMasterId());

//                    ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
//                    Branch branch = new Branch();
//                    BranchBo branchBo = (BranchBo) context.getBean("branchBoProxy");
//                    branch.setBranchId(entity.getBranchId());
//                    branch.setFlag("Y");
//                    List<Branch> branches = branchBo.getByCriteria(branch);
//                    String branchName = branches.get(0).getBranchName();
//                    pendapatanDokter.setBranchName(branchName);

                    listOfResult.add(pendapatanDokter);
                }
            }
        }

        return listOfResult;
    }

    @Override
    public void saveDelete(PendapatanDokter bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(PendapatanDokter bean) throws GeneralBOException {

    }

    @Override
    public PendapatanDokter saveAdd(PendapatanDokter bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<PendapatanDokter> saveAddPendapatanDokter(PendapatanDokter bean) throws GeneralBOException{
        logger.info("[PendapatanBoImpl.saveAddPendapatanDokter] start process >>>");
        List<PendapatanDokter> resultList = new ArrayList<>();

        if (bean != null){
            String idPendapatan, idDetailPendapatan, idPendapatanDokterPphLebih;
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<PendapatanDokter> listPendaptanDokter = (List<PendapatanDokter>) session.getAttribute("listOfResultPendapatanDokter");
            List<PendapatanDokter> listDetailPendapatan = (List<PendapatanDokter>) session.getAttribute("listOfResultDetailPendapatanDokter");

            if (listPendaptanDokter != null){
                String statusSave = cekPendapatanDokter(listPendaptanDokter.get(0).getBranchId(), listPendaptanDokter.get(0).getBulan(), listPendaptanDokter.get(0).getTahun());
                if (!statusSave.equalsIgnoreCase("exist")){

                    for (PendapatanDokter pendapatanDokter : listPendaptanDokter){
                        String noNota = mappingJurnalDao.getNextInvoiceId("JKR",pendapatanDokter.getBranchId());
                        List<BillingPendapatanDokter> billingDokter = new ArrayList<>();

                        ItHrisPendapatanDokterEntity entity = new ItHrisPendapatanDokterEntity();
                        idPendapatan = pendapatanDokterDao.getNextIdPendapatanDokter();

                        entity.setPendapatanDokterId(idPendapatan);
                        entity.setDokterId(pendapatanDokter.getDokterId());
                        entity.setDokterName(pendapatanDokter.getDokterName());
                        entity.setBranchId(pendapatanDokter.getBranchId());
                        entity.setBulan(pendapatanDokter.getBulan());
                        entity.setTahun(pendapatanDokter.getTahun());
                        entity.setBruto(pendapatanDokter.getTotalBruto());
                        entity.setDppPph50(pendapatanDokter.getTotalDppPph50());
                        entity.setDppPph21Komulatif(pendapatanDokter.getTotalDppPph21Komulatif());
                        entity.setDppPph21(pendapatanDokter.getTotalDppPph21());
                        entity.setTarif(pendapatanDokter.getTarif());
                        entity.setPphDipungut(pendapatanDokter.getTotalPphDipungut());
                        entity.setGajiBersih(pendapatanDokter.getTotalGajiBersih());
                        entity.setApprovalFlag(pendapatanDokter.getApprovalFlag());
                        entity.setApprovalWho(pendapatanDokter.getApprovalWho());
                        entity.setApprovalDate(pendapatanDokter.getApprovalDate());
                        entity.setFlag(pendapatanDokter.getFlag());
                        entity.setAction(pendapatanDokter.getAction());
                        entity.setCreatedDate(bean.getCreatedDate());
                        entity.setCreatedWho(bean.getCreatedWho());
                        entity.setLastUpdate(bean.getLastUpdate());
                        entity.setLastUpdateWho(bean.getLastUpdateWho());
                        entity.setPendapatanRs(pendapatanDokter.getTotalPendapatanRs());
                        entity.setHrBruto(pendapatanDokter.getTotalHrBruto());
                        entity.setHrAktifitasNetto(pendapatanDokter.getTotalHrAktifitasNetto());
                        entity.setPotKs(pendapatanDokter.getTotalPotKs());
                        entity.setLevel(pendapatanDokter.getLevel());
                        entity.setKodeJabatan(pendapatanDokter.getKodeJabatan());
                        if (pendapatanDokter.getTotalPphLebih() != null){
                            entity.setPphLebih(pendapatanDokter.getTotalPphLebih());
                        }
                        if (pendapatanDokter.getTotalPphFinal() != null){
                            entity.setPphFinal(pendapatanDokter.getTotalPphFinal());
                        }
                        entity.setNoNota(noNota);

                        try{
                            pendapatanDokterDao.addAndSave(entity);
                        }catch (HibernateException e){
                            logger.error("[PendapatanDokterBoImpl.saveAddPendapatanDokter] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving new data Pendapatan, please info to your admin..." + e.getMessage());
                        }

                        if (pendapatanDokter.getTotalDpp50Lebih() != null){
                            ItHrisPendapatanDokterPphLebihEntity pphLebih = new ItHrisPendapatanDokterPphLebihEntity();
                            idPendapatanDokterPphLebih = pendapatanDokterPphLebihDao.getNextIdPendapatanDokterPphLebih();
                            pphLebih.setPendapatanDokterPphLebihId(idPendapatanDokterPphLebih);
                            pphLebih.setPendapatanDokterId(idPendapatan);
                            pphLebih.setDppPph50(pendapatanDokter.getTotalDpp50Lebih());
                            pphLebih.setDppPph21Komulatif(pendapatanDokter.getTotalDppPph21KomulatifLebih());
                            pphLebih.setDppPph21(pendapatanDokter.getTotalDppPph21Lebih());
                            pphLebih.setTarif(pendapatanDokter.getTarifLebih());
                            pphLebih.setPphDipungut(pendapatanDokter.getTotalPphDipungutLebih());
                            pphLebih.setFlag(bean.getFlag());
                            pphLebih.setAction(bean.getAction());
                            pphLebih.setCreatedDate(bean.getCreatedDate());
                            pphLebih.setLastUpdate(bean.getLastUpdate());
                            pphLebih.setCreatedWho(bean.getCreatedWho());
                            pphLebih.setLastUpdateWho(bean.getLastUpdateWho());

                            try{
                                pendapatanDokterPphLebihDao.addAndSave(pphLebih);
                            }catch (HibernateException e){
                                logger.error("[PendapatanDokterBoImpl.saveAdd] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when saving new data Pendapatan, please info to your admin..." + e.getMessage());
                            }
                        }

                        for (PendapatanDokter pendapatanDokterdetail : listDetailPendapatan){
                            if (pendapatanDokter.getDokterId().equalsIgnoreCase(pendapatanDokterdetail.getDokterId())){
                                ItHrisPendapatanDokterDetailEntity detailEntity = new ItHrisPendapatanDokterDetailEntity();
                                idDetailPendapatan = pendapatanDokterDetailDao.getNextIdDetailPendapatanDokter();
                                detailEntity.setPendapatanDokterDetailId(idDetailPendapatan);
                                detailEntity.setPendapatanDokterId(idPendapatan);
                                detailEntity.setActivityId(pendapatanDokterdetail.getActivityId());
                                detailEntity.setActivityName(pendapatanDokterdetail.getActivityName());
                                detailEntity.setPoliId(pendapatanDokterdetail.getPoliId());
                                detailEntity.setPoliName(pendapatanDokterdetail.getPoliName());
                                detailEntity.setBiaya(pendapatanDokterdetail.getBgBruto());
                                detailEntity.setNoTrans(pendapatanDokterdetail.getNoReg());
                                detailEntity.setJenisRawat(pendapatanDokterdetail.getJenisRawat());
                                detailEntity.setKdjnspas(pendapatanDokterdetail.getKdjnspas());
                                detailEntity.setNamaPasien(pendapatanDokterdetail.getNamaPasien());
                                detailEntity.setTanggal(pendapatanDokterdetail.getTanggal());
                                detailEntity.setKeterangan(pendapatanDokterdetail.getKeterangan());
                                detailEntity.setTarifInacbg(pendapatanDokterdetail.getBgTarifInacbg());
                                detailEntity.setBruto(pendapatanDokterdetail.getBgBruto());
                                detailEntity.setPendapatanRs(pendapatanDokterdetail.getBgPendapatanRs());
                                detailEntity.setHrBruto(pendapatanDokterdetail.getBgHrBruto());
                                detailEntity.setDppPph21(pendapatanDokterdetail.getBgDppPph21());
                                detailEntity.setDppPph21Komulatif(pendapatanDokterdetail.getBgDppPph21Komulatif());
                                detailEntity.setPajak(pendapatanDokterdetail.getTarif());
                                detailEntity.setPotonganPajak(pendapatanDokterdetail.getBgPphDipungut());
                                detailEntity.setHrAktifitasNetto(pendapatanDokterdetail.getBgHrAktifitasNetto());
                                detailEntity.setPotonganKs(pendapatanDokterdetail.getBgPotKs());
                                detailEntity.setHrNetto(pendapatanDokterdetail.getBgGajiBersih());
                                detailEntity.setMasterId(pendapatanDokterdetail.getMasterId());

                                detailEntity.setFlag(pendapatanDokterdetail.getFlag());
                                detailEntity.setAction(pendapatanDokterdetail.getAction());
                                detailEntity.setCreatedDate(bean.getCreatedDate());
                                detailEntity.setCreatedWho(bean.getCreatedWho());
                                detailEntity.setLastUpdate(bean.getLastUpdate());
                                detailEntity.setLastUpdateWho(bean.getLastUpdateWho());

                                try{
                                    pendapatanDokterDetailDao.addAndSave(detailEntity);
                                }catch (HibernateException e){
                                    logger.error("[PendapatanDokterBoImpl.saveAddPendapatanDokter] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem when saving new data Pendapatan, please info to your admin..." + e.getMessage());
                                }


                                //memetakan biaya berdasarkan billing
                                ImSimrsPelayananEntity pelayananEntity = pelayananDao.getById("idPelayanan",detailEntity.getPoliId());
                                String masterId= "";

                                switch (detailEntity.getMasterId()){
                                    case "umum":
                                        masterId="01.000";
                                        break;
                                    case "bpjs":
                                        masterId="02.000";
                                        break;
                                }

                                boolean ada = false;
                                for (BillingPendapatanDokter billingPendapatanDokter : billingDokter){
                                    if (pelayananEntity.getKodering().equalsIgnoreCase(billingPendapatanDokter.getDivisiId())&&masterId.equalsIgnoreCase(billingPendapatanDokter.getMasterId())){
                                        billingPendapatanDokter.setNilai(billingPendapatanDokter.getNilai().add(pendapatanDokterdetail.getBgHrBruto()));
                                        ada=true;
                                        break;
                                    }
                                }
                                if (!ada){
                                    BillingPendapatanDokter billingPendapatanDokter1 = new BillingPendapatanDokter();
                                    billingPendapatanDokter1.setDivisiId(pelayananEntity.getKodering());
                                    billingPendapatanDokter1.setMasterId(masterId);
                                    billingPendapatanDokter1.setNilai(pendapatanDokterdetail.getBgHrBruto());
                                    billingPendapatanDokter1.setJenisRawat(pendapatanDokterdetail.getJenisRawat());
                                    billingDokter.add(billingPendapatanDokter1);
                                }
                            }
                        }

                        //untuk billing
                        PendapatanDokter result = new PendapatanDokter();
                        result.setDokterName(pendapatanDokter.getDokterName());
                        result.setBulan(pendapatanDokter.getBulan());
                        result.setTahun(pendapatanDokter.getTahun());
                        result.setBranchId(pendapatanDokter.getBranchId());
                        result.setDokterId(pendapatanDokter.getDokterId());

                        String namaUnit = "";
                        List<ImBranches> branches = branchDao.getListBranchById(result.getBranchId());
                        for (ImBranches unit : branches){
                            namaUnit=unit.getBranchName();
                        }

                        String koderingDokter = dokterDao.getById("idDokter",pendapatanDokter.getDokterId()).getKodering();

                        //membuat map ke billing
                        Map billing =  new HashMap();

                        List<Map> reduksiPendapatanRjList = new ArrayList<>();
                        List<Map> reduksiPendapatanRiList = new ArrayList<>();

                    for (BillingPendapatanDokter billingPendapatanDokter : billingDokter) {
                        if ("rawat_jalan".equalsIgnoreCase(billingPendapatanDokter.getJenisRawat())||"igd".equalsIgnoreCase(billingPendapatanDokter.getJenisRawat())){
                            Map reduksiPendapatanRj = new HashMap();
                                reduksiPendapatanRj.put("nilai",billingPendapatanDokter.getNilai());
                                reduksiPendapatanRj.put("divisi_id",billingPendapatanDokter.getDivisiId());
                                reduksiPendapatanRj.put("master_id",billingPendapatanDokter.getMasterId());
                                reduksiPendapatanRjList.add(reduksiPendapatanRj);
                            }else{
                                Map reduksiPendapatanRi = new HashMap();

                                reduksiPendapatanRi.put("nilai",billingPendapatanDokter.getNilai());
                                reduksiPendapatanRi.put("divisi_id",billingPendapatanDokter.getDivisiId());
                                reduksiPendapatanRi.put("master_id",billingPendapatanDokter.getMasterId());
                                reduksiPendapatanRiList.add(reduksiPendapatanRi);
                            }
                        }

                        if (reduksiPendapatanRiList.size()==0){
                            Map reduksiPendapatanRi = new HashMap();

                            reduksiPendapatanRi.put("nilai",BigDecimal.ZERO);
                            reduksiPendapatanRi.put("divisi_id","");
                            reduksiPendapatanRi.put("master_id","");
                            reduksiPendapatanRiList.add(reduksiPendapatanRi);
                        }
                        if (reduksiPendapatanRjList.size()==0){
                            Map reduksiPendapatanRj = new HashMap();

                            reduksiPendapatanRj.put("nilai",BigDecimal.ZERO);
                            reduksiPendapatanRj.put("divisi_id","");
                            reduksiPendapatanRj.put("master_id","");
                            reduksiPendapatanRjList.add(reduksiPendapatanRj);
                        }

                        Map hutangDokter = new HashMap();
                        hutangDokter.put("nilai",pendapatanDokter.getTotalGajiBersih());
                        hutangDokter.put("bukti",noNota);
                        hutangDokter.put("master_id",koderingDokter);

                        Map pphDokter = new HashMap();
                        pphDokter.put("nilai",pendapatanDokter.getTotalPphFinal());
                        pphDokter.put("master_id",koderingDokter);

                        Map potKs = new HashMap();
                        potKs.put("nilai",pendapatanDokter.getTotalPotKs());
                        potKs.put("master_id",koderingDokter);

                        billing.put("hutang_dokter",hutangDokter);
                        billing.put("pot_ks",potKs);
                        billing.put("pph_dokter",pphDokter);
                        billing.put("reduksi_pendapatan_rawat_jalan",reduksiPendapatanRjList);
                        billing.put("reduksi_pendapatan_rawat_inap",reduksiPendapatanRiList);

                        result.setDataBilling(billing);
                        result.setKeteranganBilling("Pendapatan dokter an. "+result.getDokterName()+", bulan "+CommonUtil.convertNumberToStringBulan(result.getBulan())+" tahun "+result.getTahun()+" unit "+namaUnit);
                        resultList.add(result);
                    }

                }else {
                    throw new GeneralBOException("Maaf Data Pendapatan dokter pada bulan "+listPendaptanDokter.get(0).getBulan()+"-"+listPendaptanDokter.get(0).getTahun()+" Tersebut Sudah Ada");
                }
            }
        }
        logger.info("[PendapatanDokterBoImpl.saveAddPendapatanDokter] end process <<<");
        return resultList;
    }

    @Override
    public List<PendapatanDokter> getByCriteria(PendapatanDokter searchBean) throws GeneralBOException {
        logger.info("[PendapatanDokterBoImpl.getByCriteria] start process >>>");
        List<PendapatanDokter> listOfResult = new ArrayList<>();
        List<ItJurnalDetailEntity> jurnalDetailList = null;

        if (searchBean != null){
            Map hsCriteria = new HashMap<>();

            if (searchBean.getPendapatanDokterId() != null && !"".equalsIgnoreCase(searchBean.getPendapatanDokterId())){
                hsCriteria.put("pendapatan_dokter_id", searchBean.getPendapatanDokterId());
            }
            if (searchBean.getDokterId() != null && !"".equalsIgnoreCase(searchBean.getDokterId())){
                hsCriteria.put("dokter_id", searchBean.getDokterId());
            }
            if (searchBean.getDokterName() != null && !"".equalsIgnoreCase(searchBean.getDokterName())){
                hsCriteria.put("dokter_name", searchBean.getDokterName());
            }
            if (searchBean.getBulan() != null && !"".equalsIgnoreCase(searchBean.getBulan())){
                hsCriteria.put("bulan", searchBean.getBulan());
            }
            if (searchBean.getTahun() != null && !"".equalsIgnoreCase(searchBean.getTahun())){
                hsCriteria.put("tahun", searchBean.getTahun());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())){
                hsCriteria.put("branch_id", searchBean.getBranchId());
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

            List<ItHrisPendapatanDokterEntity> pendapatanDokterEntityList = null;
            try{
                pendapatanDokterEntityList = pendapatanDokterDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error("[PendapatanDokterBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (pendapatanDokterEntityList != null){
                PendapatanDokter pendapatanDokter;
                for (ItHrisPendapatanDokterEntity entity : pendapatanDokterEntityList) {
                    pendapatanDokter = new PendapatanDokter();
                    pendapatanDokter.setPendapatanDokterId(entity.getPendapatanDokterId());
                    pendapatanDokter.setDokterId(entity.getDokterId());
                    pendapatanDokter.setDokterName(entity.getDokterName());
//                    pendapatanDokter.setBranchId(entity.getBranchId());
                    pendapatanDokter.setBulan(entity.getBulan());
                    pendapatanDokter.setTahun(entity.getTahun());
                    pendapatanDokter.setBruto(CommonUtil.numbericFormat(entity.getBruto(), "###,###"));
                    pendapatanDokter.setDppPph50(CommonUtil.numbericFormat(entity.getDppPph50(), "###,###"));
                    pendapatanDokter.setDppPph21Komulatif(CommonUtil.numbericFormat(entity.getDppPph21Komulatif(), "###,###"));
                    pendapatanDokter.setDppPph21(CommonUtil.numbericFormat(entity.getDppPph21(), "###,###"));
                    pendapatanDokter.setStTarif(CommonUtil.numbericFormat(entity.getTarif(), "###,###") + "%");
                    pendapatanDokter.setPphDipungut(CommonUtil.numbericFormat(entity.getPphDipungut(), "###,###"));
                    pendapatanDokter.setHrAktifitasNetto(CommonUtil.numbericFormat(entity.getHrAktifitasNetto(), "###,###"));

                    pendapatanDokter.setPotKs(CommonUtil.numbericFormat(entity.getPotKs(), "###,###"));
                    pendapatanDokter.setKodeJabatan(entity.getKodeJabatan());

                    if (entity.getFlagDiterima() != null)
                        pendapatanDokter.setFlagDiterima(entity.getFlagDiterima());
                    else
                        pendapatanDokter.setFlagDiterima("N");

                    pendapatanDokter.setApprovalFlag(entity.getApprovalFlag());
                    pendapatanDokter.setApprovalWho(entity.getApprovalWho());
                    pendapatanDokter.setFlag(entity.getFlag());
                    pendapatanDokter.setAction(entity.getAction());
                    pendapatanDokter.setCreatedDate(entity.getCreatedDate());
                    pendapatanDokter.setLastUpdate(entity.getLastUpdate());
                    pendapatanDokter.setCreatedWho(entity.getCreatedWho());
                    pendapatanDokter.setLastUpdateWho(entity.getLastUpdateWho());
                    pendapatanDokter.setApprovalDate(entity.getApprovalDate());
                    if (entity.getFlagDiterima() != null)
                        pendapatanDokter.setFlagDiterima(entity.getFlagDiterima());
                    else
                        pendapatanDokter.setFlagDiterima("N");
                    if (entity.getDateDiterima() != null) {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                        pendapatanDokter.setStDateDiterima(dateFormat.format(entity.getDateDiterima()));
                    } else {
                        pendapatanDokter.setStDateDiterima("-");
                    }

                    pendapatanDokter.setPemberiWho(entity.getPemberiWho());
                    pendapatanDokter.setPendapatanRs(CommonUtil.numbericFormat(entity.getPendapatanRs(), "###,###"));
                    pendapatanDokter.setHrBruto(CommonUtil.numbericFormat(entity.getHrBruto(), "###,###"));
                    pendapatanDokter.setHrAktifitasNetto(CommonUtil.numbericFormat(entity.getHrAktifitasNetto(), "###,###"));
                    pendapatanDokter.setPotKs(CommonUtil.numbericFormat(entity.getPotKs(), "###,###"));
                    pendapatanDokter.setGajiBersih(CommonUtil.numbericFormat(entity.getGajiBersih(), "###,###"));

                    ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                    Branch branch = new Branch();
                    BranchBo branchBo = (BranchBo) context.getBean("branchBoProxy");
                    branch.setBranchId(entity.getBranchId());
                    branch.setFlag("Y");
                    List<Branch> branches = branchBo.getByCriteria(branch);
                    String branchName = branches.get(0).getBranchName();
                    pendapatanDokter.setBranchName(branchName);

                    String noNota = entity.getNoNota();
                    Map map = new HashMap<>();
                    map.put("no_nota", noNota);
                    jurnalDetailList = jurnalDetailDao.getByCriteria(map);
                    if (jurnalDetailList.size() > 1){
                        pendapatanDokter.setApprovalDibayar(true);
                    }else {
                        pendapatanDokter.setApprovalDibayar(false);
                    }

                    pendapatanDokter.setNoNota(entity.getNoNota());
                    pendapatanDokter.setTotalPphLebih(entity.getPphLebih());

                    listOfResult.add(pendapatanDokter);
                }
            }
        }

        logger.info("[PendapatanDokterBoImpl.getByCriteria] end process <<<");
        return listOfResult;
    }

    @Override
    public List<PendapatanDokter> getAll() throws GeneralBOException {

        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public String cekPendapatanDokter(String branchId, String bulan, String tahun)throws GeneralBOException{
        String status ="";
        List<ItHrisPendapatanDokterEntity> entities = new ArrayList<>();
        try {
            entities = pendapatanDokterDao.getDataPendapatanDokter(branchId, bulan, tahun);
        } catch (HibernateException e) {
            logger.error("[PelayananBoImpl.cekStatus] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }
}