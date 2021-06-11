package com.neurix.simrs.master.jenisperiksapasien.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisperiksapasien.model.ImJenisPeriksaPasienEntity;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;

import java.util.List;

/**
 * Created by Toshiba on 12/11/2019.
 */
public interface JenisPriksaPasienBo {
    public List<JenisPriksaPasien> getListAllJenisPeriksa(JenisPriksaPasien bean);
    public List<JenisPriksaPasien> getListJenisPeriksaNotBpjs(JenisPriksaPasien bean);
    public ImJenisPeriksaPasienEntity getJenisPerikasEntityById(String id) throws GeneralBOException;
    public List<JenisPriksaPasien> getListJenisPeriksaByIdDetailCheckup(String jenis, String idDetail);

    public List<JenisPriksaPasien> getJenisPeriksaExecMCU() throws GeneralBOException;
}
