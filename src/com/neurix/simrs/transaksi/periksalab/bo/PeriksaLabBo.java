package com.neurix.simrs.transaksi.periksalab.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.labdetail.model.LabDetail;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.periksalab.model.ItSimrsPeriksaLabEntity;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLabDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Toshiba on 27/11/2019.
 */
public interface PeriksaLabBo {
    public List<PeriksaLab> getByCriteria(PeriksaLab bean) throws GeneralBOException;
    public List<PeriksaLab> getSearchLab(PeriksaLab bean) throws GeneralBOException;
    public void saveAdd(PeriksaLab bean) throws GeneralBOException;
    public CrudResponse saveEdit(PeriksaLab periksaLab, List<String> labDetailIds) throws GeneralBOException;
    public CrudResponse saveAddWithParameter(PeriksaLab periksaLab, List<String> labDetailIds) throws GeneralBOException;
    public CrudResponse saveUpdateHasilLab(PeriksaLabDetail bean) throws GeneralBOException;
    public List<PeriksaLabDetail> getListParameterLab(PeriksaLabDetail bean) throws GeneralBOException;
    public CheckResponse saveDokterLab(PeriksaLab bean, List<PeriksaLabDetail> list) throws GeneralBOException;

    public CheckResponse updateFlagApprovePeriksaLab (PeriksaLab bean) throws GeneralBOException;

    public BigDecimal getTarifTotalPemeriksaan(String idPeriksaan) throws GeneralBOException;
    public String getDivisiIdKodering(String idDetailCheckup, String tipeLab) throws GeneralBOException;

    public void saveEditStatusPeriksa(PeriksaLab bean) throws GeneralBOException;

    public List<Dokter> getListDokterLabRadiologi(String tipe) throws GeneralBOException;
    public PeriksaLab getNamaLab(String idPeriksa) throws GeneralBOException;

    public CrudResponse saveUpdateParameter(PeriksaLab bean, List<String> listParams) throws GeneralBOException;
    public ItSimrsPeriksaLabEntity getPeriksaLabEntityById(String id) throws GeneralBOException;

    public List<PeriksaLab> getListLab(String noChekcup) throws GeneralBOException;
}
