package com.neurix.simrs.transaksi.edukasipasien.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.edukasipasien.model.EdukasiPasien;

import java.util.List;

public interface EdukasiPasienBo {
    public List<EdukasiPasien> getByCriteria(EdukasiPasien bean) throws GeneralBOException;
    public CrudResponse saveAdd(List<EdukasiPasien> list) throws GeneralBOException;
}
