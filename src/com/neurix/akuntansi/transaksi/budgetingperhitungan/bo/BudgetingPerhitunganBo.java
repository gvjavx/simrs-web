package com.neurix.akuntansi.transaksi.budgetingperhitungan.bo;

import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.transaksi.budgeting.model.Budgeting;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.*;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;

import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by reza on 14/08/20.
 */
public interface BudgetingPerhitunganBo {
    public List<ImAkunParameterBudgetingEntity> getListParameterBudgetingEntity(ParameterBudgeting bean) throws GeneralBOException;
    public List<ItAkunPerhitunganBudgetingEntity> getListPerhitunganBudgetingEntity(PerhitunganBudgeting bean) throws GeneralBOException;
    public List<PerhitunganBudgeting> getListPendapatanTindakan(String branchId, String bulan, String tahun, String tipe) throws GeneralBOException;
    public List<PerhitunganBudgeting> getListPendapatanObat(String branchId, String bulan, String tahun, String tipe) throws GeneralBOException;
    public List<ParameterBudgeting> getSearchListParameterBudgeting(ParameterBudgeting bean) throws GeneralBOException;
    public BigDecimal hitungNilaiBudgeting(List<ItAkunPerhitunganBudgetingEntity> beans) throws GeneralBOException;
    public void saveAddPerhitunganBudgeting(List<ItAkunNilaiParameterBudgetingEntity> nilaiParameters, List<ItAkunPerhitunganBudgetingEntity> listPerhitungan, List<ItAkunNilaiParameterPengadaaanEntity> listPengadaaan, PerhitunganBudgeting bean) throws GeneralBOException;
    public String getNextIdNilaiParameter(String branchId, String tahun, String idNilai) throws GeneralBOException;
    public List<KategoriParameter> getListKategoriParameter(KategoriParameter bean) throws GeneralBOException;
    public ImPosition getPositionByKodering(String id) throws GeneralBOException;
    public ImMasterEntity getMasterByKodering(String id) throws GeneralBOException;
    public List<Budgeting> getBranchBudgeting(String tahun) throws GeneralBOException;
    public List<ParameterBudgeting> getNilaiParameterByNilaiParam(ParameterBudgeting bean) throws GeneralBOException;
    public List<ParameterBudgeting> getListMasterParameterBudgetingByIdKategori(String idKategori) throws GeneralBOException;
    public List<ParameterBudgeting> getListDivisiParameterBudgetingByKategororiAndMaster(String idKategori, String masterId) throws GeneralBOException;
    public List<ParameterBudgeting> getListDivisiParameterBudgetingByKategororiAndMasterGroup(String idKategori, String masterId) throws GeneralBOException;
    public List<ParameterBudgeting> getlistParameterRekeningByDivisi(String idKategori, String divisiId) throws GeneralBOException;
    public List<ImAkunJenisBudgetingEntity> getListEntityJenisBudgetingByCriteria(ParameterBudgeting bean) throws GeneralBOException;
    public List<ParameterBudgeting> getListSumOfKategoriBudgeting(String idJenisBudgeting, String tahun, String branchId) throws GeneralBOException;
    public List<ItAkunNilaiParameterPengadaaanEntity> getListEntityNilaiParameterPengadaan(ParameterBudgeting bean) throws GeneralBOException;
    public List<ParameterBudgeting> getListRefrensiBiaya(String tahun, String branchId, String rekeningId, String divisiId, String master);
    public ImAkunParameterBudgetingEntity getParameterBudgetingEntityById(String id) throws GeneralBOException;
    public ImAkunParameterBudgetingRekeningEntity getParameterBudgetingRekeningEntityById(String id) throws GeneralBOException;
}
