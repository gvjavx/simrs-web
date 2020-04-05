package com.neurix.simrs.master.dokter.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.dokter.model.Dokter;

import java.util.List;

/**
 * Created by Toshiba on 14/11/2019.
 */
public interface DokterBo {
    public List<Dokter> getByCriteria(Dokter bean) throws GeneralBOException;
    public List<Dokter> getByIdPelayanan(String idPelayanan, String branchId) throws GeneralBOException;
    public boolean editKuota(String idDokter, String kuota) throws GeneralBOException;

    public List<Dokter> getDokterByPelayanan(String idPelayanan) throws GeneralBOException;
}
