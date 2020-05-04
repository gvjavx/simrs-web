package com.neurix.akuntansi.transaksi.budgeting.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.transaksi.budgeting.bo.BudgetingBo;
import com.neurix.akuntansi.transaksi.budgeting.dao.BudgetingDao;
import com.neurix.akuntansi.transaksi.budgeting.dao.BudgetingDetailDao;
import com.neurix.akuntansi.transaksi.budgeting.dao.BudgetingPengadaanDao;
import com.neurix.akuntansi.transaksi.budgeting.model.*;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
                budgeting.setNoBudgeting(budgetingEntity.getNoBudgeting());
                budgeting.setTahun(budgeting.getTahun());
                budgeting.setBranchId(budgetingEntity.getBranchId());
                budgeting.setRekeningId(budgetingEntity.getRekeningId());
                budgeting.setStatus(budgetingEntity.getStatus());
                budgeting.setNilaiTotal(budgetingEntity.getNilaiTotal());
                budgeting.setNilaiAwal(budgetingEntity.getNilaiTotal());
                budgeting.setTipe(budgetingEntity.getTipe());
                budgeting.setSemester1(budgetingEntity.getSemester1());
                budgeting.setSemester2(budgetingEntity.getSemester2());
                budgeting.setQuartal1(budgetingEntity.getQuartal1());
                budgeting.setQuartal2(budgetingEntity.getQuartal2());
                budgeting.setQuartal3(budgetingEntity.getQuartal3());
                budgeting.setQuartal4(budgetingEntity.getQuartal4());
                budgeting.setSelisih(budgetingEntity.getSelisih());
                budgeting.setApproveFlag(budgetingEntity.getApproveFlag());
                budgeting.setApproveWho(budgetingEntity.getApproveWho());
                budgeting.setApproveTime(budgetingEntity.getApproveTime());
                budgeting.setFlag(budgetingEntity.getFlag());
                budgeting.setAction(budgetingEntity.getAction());
                budgeting.setCreatedDate(budgetingEntity.getCreatedDate());
                budgeting.setCreatedWho(budgetingEntity.getCreatedWho());
                budgeting.setLastUpdate(budgetingEntity.getLastUpdate());
                budgeting.setLastUpdateWho(budgetingEntity.getLastUpdateWho());
                budgeting.setNilaiDraf(getSumNilaiTotalByStatus(budgetingEntity.getRekeningId(), budgetingEntity.getBranchId(), budgetingEntity.getTahun(), "DRAFT"));
                budgeting.setNilaiFinal(getSumNilaiTotalByStatus(budgetingEntity.getRekeningId(), budgetingEntity.getBranchId(), budgetingEntity.getTahun(), "FINAL"));
                budgeting.setNilaiRevisi(getSumNilaiTotalByStatus(budgetingEntity.getRekeningId(), budgetingEntity.getBranchId(), budgetingEntity.getTahun(), "REVISI"));

                // kode rekening;
                ImKodeRekeningEntity kodeRekeningEntity = getEntityKoderekeningById(budgetingEntity.getRekeningId());
                if (kodeRekeningEntity != null){
                    budgeting.setKodeRekening(kodeRekeningEntity.getKodeRekening());
                    budgeting.setNamaKodeRekening(kodeRekeningEntity.getNamaKodeRekening());
                    budgeting.setLevel(kodeRekeningEntity.getLevel());
                    budgeting.setStLevel(kodeRekeningEntity.getLevel() == null ? "" : kodeRekeningEntity.getLevel().toString());
                    budgeting.setParentId(kodeRekeningEntity.getParentId());
                }

                budgetings.add(budgeting);
            }
        }

        // short berdasarakan kodeRekening
        Collections.sort(budgetings, Budgeting.getKodeRekeningSorting());

        logger.info("[BudgetingBoImpl.getSearchByCriteria] END <<<");
        return budgetings;
    }

    private BigDecimal getSumNilaiTotalByStatus(String rekeningId, String branchId, String tahun, String status){
        return budgetingDao.getSumNilaiByStatus(rekeningId, branchId, tahun, status);
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
                        budgetingEntity.setIdBudgeting(nextBudgetingId());
                        budgetingEntity.setNoBudgeting(name+"-"+bean.getBranchId()+"-"+bean.getTahun()+"-"+kodeRekeningEntity.getKodeRekening());
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

                        Budgeting budgetingNew = new Budgeting();

                        budgetingNew.setIdBudgeting(budgetingEntity.getIdBudgeting());
                        budgetingNew.setNoBudgeting(name+"-"+bean.getBranchId()+"-"+bean.getTahun()+"-"+kodeRekeningEntity.getKodeRekening());
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

                            budgetingNew.setNilaiTotal(budgeting.getNilaiTotal());
                            budgetingNew.setSemester1(budgeting.getSemester1());
                            budgetingNew.setSemester2(budgeting.getSemester2());
                            budgetingNew.setQuartal1(budgeting.getQuartal1());
                            budgetingNew.setQuartal2(budgeting.getQuartal2());
                            budgetingNew.setQuartal3(budgeting.getQuartal3());
                            budgetingNew.setQuartal4(budgeting.getQuartal4());
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
                                budgetingDetailEntity.setIdBudgetingDetail(nextBudgetingDetailId());
                                budgetingDetailEntity.setIdBudgeting(budgetingEntity.getIdBudgeting());
                                budgetingDetailEntity.setNoBudgetingDetail(budgetingEntity.getNoBudgeting()+"-"+budgetingDetail.getTipe()+"-"+budgetingDetail.getDivisiId());
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

                                List<BudgetingPengadaan> pengadaans = budgetingPengadaans.stream().filter( p -> p.getRekeningId().equalsIgnoreCase(kodeRekeningEntity.getRekeningId())).collect(Collectors.toList());
                                if (pengadaans.size() > 0){
                                    for (BudgetingPengadaan budgetingPengadaan : pengadaans){

                                        ItAkunBudgetingPengadaanEntity pengadaanEntity = new ItAkunBudgetingPengadaanEntity();
                                        pengadaanEntity.setIdPengadaan(nextBudgetingPengadaanId());
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

                List<BudgetingDetail> details = budgetingDetails.stream().filter( p -> p.getRekeningId().equalsIgnoreCase(budgetingEntity.getRekeningId())).collect(Collectors.toList());
                if (details.size() > 0){
                    for (BudgetingDetail budgetingDetail : details){

                        ItAkunBudgetingDetailEntity budgetingDetailEntity = budgetingDetailDao.getById("idBudgetingDetail", budgetingDetail.getIdBudgetingDetail());
                        if (budgetingDetailEntity != null){
                            budgetingDetailEntity.setDivisiId(budgetingDetail.getDivisiId());
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
                        } else {
                            budgetingDetailEntity = new ItAkunBudgetingDetailEntity();
                            budgetingDetailEntity.setIdBudgetingDetail(nextBudgetingDetailId());
                            budgetingDetailEntity.setIdBudgeting(budgetingEntity.getIdBudgeting());
                            budgetingDetailEntity.setNoBudgeting(budgetingEntity.getNoBudgeting());
                            budgetingDetailEntity.setNoBudgetingDetail(budgetingEntity.getNoBudgeting()+"-"+budgetingDetail.getTipe()+"-"+budgetingDetail.getDivisiId());
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
                        }


                        List<BudgetingPengadaan> pengadaans = budgetingPengadaans.stream().filter( p -> p.getRekeningId().equalsIgnoreCase(budgetingEntity.getRekeningId())).collect(Collectors.toList());
                        if (details.size() > 0){
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
                                    pengadaanEntity.setIdPengadaan(nextBudgetingPengadaanId());
                                    pengadaanEntity.setIdBudgetingDetail(budgetingDetail.getIdBudgetingDetail());
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
                    budgetingEntity.setIdBudgeting(nextBudgetingId());
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

                            budgeting.setNilaiTotal(parent.getNilaiTotal());
                            budgeting.setSemester1(parent.getSemester1());
                            budgeting.setSemester2(parent.getSemester2());
                            budgeting.setQuartal1(parent.getQuartal1());
                            budgeting.setQuartal2(parent.getQuartal2());
                            budgeting.setQuartal3(parent.getQuartal3());
                            budgeting.setQuartal4(parent.getQuartal4());
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
                budgetingDetail.setDivisiName("");
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

                if (!"".equalsIgnoreCase(budgetingDetail.getPositionId())){
                    ImPosition position = positionDao.getById("positionId", budgetingDetail.getPositionId());
                    budgetingDetail.setDivisiName(position.getPositionName());
                }

                if (!"".equalsIgnoreCase(budgetingDetail.getIdBudgeting())){
                    ItAkunBudgetingEntity budgetingEntity = budgetingDao.getById("idBudgeting", budgetingDetail.getIdBudgeting());
                    if (budgetingEntity != null){
                        budgetingDetail.setRekeningId(budgetingEntity.getRekeningId());
                    }
                }

                budgetingDetails.add(budgetingDetail);
            }
        }
        return budgetingDetails;
    }

    @Override
    public List<ItAkunBudgetingPengadaanEntity> getListBudgetingPengadaanByIdDetail(String id) throws GeneralBOException {
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_budgeting_detail", id);
        return budgetingPengadaanDao.getByCriteria(hsCriteria);
    }

    private String nextBudgetingId(){
        return budgetingDao.getNextId();
    }
    private String nextBudgetingDetailId(){
        return budgetingDetailDao.getNextId();
    }
    private String nextBudgetingPengadaanId(){
        return budgetingPengadaanDao.getNextId();
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
}
