package com.neurix.simrs.transaksi.ringkasanpasien.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.ringkasanpasien.model.RingkasanPasien;

import java.util.List;

public interface RingkasanPasienBo {
    public List<RingkasanPasien> getByCriteria(RingkasanPasien bean) throws GeneralBOException;
    public CrudResponse saveAdd(List<RingkasanPasien> list) throws GeneralBOException;
    public CrudResponse saveDelete(RingkasanPasien bean) throws GeneralBOException;
    public HeaderCheckup getResumeMedis(String id) throws GeneralBOException;
}
