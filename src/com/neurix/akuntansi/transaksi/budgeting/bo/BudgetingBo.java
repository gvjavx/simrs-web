package com.neurix.akuntansi.transaksi.budgeting.bo;

import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.transaksi.budgeting.model.*;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ParameterBudgeting;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

/**
 * Created by reza on 29/04/20.
 */
public interface BudgetingBo {

    public List<Budgeting> getSearchByCriteria(Budgeting bean) throws GeneralBOException;
    public List<BudgetingDetail> getListBudgetingDetailByNoBudgeting(String id) throws GeneralBOException;
    public List<BudgetingPengadaan> getListBudgetingPengadaanNoDetail(String id) throws GeneralBOException;
    public List<ItAkunBudgetingPengadaanEntity> getListBudgetingPengadaanByIdDetail(String id) throws GeneralBOException;
    public void saveAddCoaBudgeting(List<Budgeting> budgetingList) throws GeneralBOException;
    public void saveBudgetingDetail(List<Budgeting> budgetingList, String type) throws GeneralBOException;
    public void saveAllBudgeting(List<Budgeting> budgetingList, String type) throws GeneralBOException;
    public void saveAddBudgeting(List<Budgeting> budgetingList, List<BudgetingDetail> budgetingDetails, List<BudgetingPengadaan> budgetingPengadaans, String type, Budgeting bean) throws GeneralBOException;
    public void saveEditBudgeting(List<Budgeting> budgetingList, List<BudgetingDetail> budgetingDetails, List<BudgetingPengadaan> budgetingPengadaans, String statusTrans, String typeTrans, Budgeting bean) throws GeneralBOException;
    public Long getlastLevelKodeRekening();
    public Boolean foundWithSameStatus(String tahun, String branchId, String status, String tipe);

    public String generateBudgetingDetailId();
    public String generateBudgetingPengadaan();
    public String checkLastTipeBudgeting();
    public Budgeting checkBudgeting(String branchId, String tahun) throws GeneralBOException;
    public Budgeting checkBudgetingByTipe(String branchId, String tahun, String tipeBudgeting) throws GeneralBOException;
    public String checkNilaiDasarByTahun(String tahun) throws GeneralBOException;
    public ItAkunBudgetingEntity getBudgetingEntityById(String id) throws GeneralBOException;

    public Budgeting getBudgetBiayaDivisiSaatIni(Budgeting bean);

    List<Budgeting> getNoBudgetByDivisi(Budgeting bean);

    List<Budgeting> getInvestasiByDivisi(Budgeting bean);

    List<BudgetingPengadaan> getInvestasiByNoBudgeting(String noBudgeting);

    Budgeting getBudgetBiayaInvestasiSaatIni(Budgeting bean);

    List<BudgetingPengadaan> getTerminPembayaran(String pengadaanId);
    public List<Budgeting> getListLabaRugi(String tahun, String unit, String status);
    public String ceckAvailBudgetingByTahun(String tahun);
    public List<ParameterBudgeting> getListBudgetingRealisasi(String idJenisBudgeting, String unit, String tahun, String status);
    public List<ParameterBudgeting> getListBudgetingRealisasiPerperiode(String idJenisBudgeting, String unit, String tahun, String divisiId, String masterId, String rekeningId, String status);
    public List<ParameterBudgeting> getListBudgetingRealisasiPerKodeRekening(String idJenisBudgeting, String unit, String tahun, String divisiId, String masterId, String status);
    public String getIdBudgetingDetailInvestasi(String status, String unit, String tahun, String divisi, String tipe);
}
