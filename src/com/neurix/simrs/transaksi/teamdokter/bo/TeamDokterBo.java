package com.neurix.simrs.transaksi.teamdokter.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;

import java.util.List;

/**
 * Created by Toshiba on 18/11/2019.
 */
public interface TeamDokterBo {
    public List<DokterTeam> getByCriteria(DokterTeam bean) throws GeneralBOException;
    public List<ItSimrsDokterTeamEntity> getListEntityTeamDokter(DokterTeam bean) throws GeneralBOException;
    public CrudResponse savaAdd(DokterTeam bean) throws GeneralBOException;
    public CrudResponse saveEdit(DokterTeam bean) throws GeneralBOException;
    public CrudResponse doneDokter(DokterTeam bean) throws GeneralBOException;

    public DokterTeam getNamaDokter(String idDetailCheckup) throws GeneralBOException;
    public CrudResponse saveApproveDokter(DokterTeam bean) throws GeneralBOException;
    public CrudResponse saveDokterTeam(DokterTeam bean) throws GeneralBOException;
    public List<ItSimrsDokterTeamEntity> cekRequestDokter(String idDetailCheckup) throws GeneralBOException;
    public List<DokterTeam> cekRequestDokterByIdDokter(String idDokter, String flagApprove) throws GeneralBOException;
}
