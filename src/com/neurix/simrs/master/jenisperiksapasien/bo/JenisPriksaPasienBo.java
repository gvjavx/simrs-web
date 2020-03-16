package com.neurix.simrs.master.jenisperiksapasien.bo;

import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;

import java.util.List;

/**
 * Created by Toshiba on 12/11/2019.
 */
public interface JenisPriksaPasienBo {
    public List<JenisPriksaPasien> getListAllJenisPeriksa(JenisPriksaPasien bean);
    public List<JenisPriksaPasien> getListJenisPeriksaNotBpjs(JenisPriksaPasien bean);
}
