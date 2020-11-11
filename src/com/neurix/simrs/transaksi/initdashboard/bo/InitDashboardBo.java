package com.neurix.simrs.transaksi.initdashboard.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;

import java.util.List;

public interface InitDashboardBo {
    public HeaderCheckup getCountAll(String branch) throws GeneralBOException;
    public List<HeaderCheckup> getKunjunganRJ(String bulan, String tahun, String branchId) throws GeneralBOException;
    public List<HeaderCheckup> getDetailKunjunganRJ(String bulan, String tahun, String branchId) throws GeneralBOException;
    public List<HeaderCheckup> getTahunPeriksa() throws GeneralBOException;
}
