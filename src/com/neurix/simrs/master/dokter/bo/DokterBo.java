package com.neurix.simrs.master.dokter.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dokter.model.Dokter;

import java.util.List;

/**
 * Created by Toshiba on 14/11/2019.
 */
public interface DokterBo extends BaseMasterBo<Dokter> {
    public List<Dokter> getByCriteria(Dokter bean) throws GeneralBOException;
    public List<Dokter> getByIdPelayanan(String idPelayanan, String branchId) throws GeneralBOException;
    public boolean editKuota(String idDokter, String kuota, String kuotaTele) throws GeneralBOException;
    public boolean editLatLon(String idDokter, String lat, String lon) throws GeneralBOException;
    public boolean editFlagCall(String idDokter, String flagCall) throws GeneralBOException;
    public boolean editFlagTele(String idDokter, String flagTele) throws GeneralBOException;
    public List<Dokter> getDokterByPelayanan(String idPelayanan, String notLike) throws GeneralBOException;
    public List<Dokter> getDokterById(String idDokter) throws GeneralBOException;

    public List<Dokter> getSearchByCriteria(Dokter bean) throws GeneralBOException;
    List<Dokter> typeaheadDokter(String dokterName) throws GeneralBOException;

    public List<Dokter> getListDokterByBranchId(String branchId, String idDokter) throws GeneralBOException;
    public List<Dokter> getListDokterByIdDetailCheckup(String idDetailChekcup, String approve) throws GeneralBOException;
}
