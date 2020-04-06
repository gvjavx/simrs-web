package com.neurix.simrs.transaksi.antrianonline.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.antrianonline.model.AntianOnline;

import java.sql.Date;
import java.util.List;

/**
 * @author gondok
 * Friday, 15/11/19 14:46
 */
public interface AntrianOnlineBo {
    public List<AntianOnline> getByCriteria(AntianOnline bean)throws GeneralBOException;
    public void saveAdd(AntianOnline bean)throws GeneralBOException;
    public List<AntianOnline> getAntrianByCriteria(String idPelayanan, String idDokter, String noCheckupOnline, Date tglCheckup, String jamAwal, String jamAkhir, String branchId);
    public CrudResponse updateScanFlag(String noCheckupOnline, String noCheckup, String idDetailCheckup) throws GeneralBOException;
}
