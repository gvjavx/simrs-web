package com.neurix.simrs.master.rekammedis.bo;

import com.neurix.common.bo.GeneralBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.rekammedis.model.RekamMedisPasien;
import java.util.List;

public interface RekamMedisPasienBo extends GeneralBo {
    public List<RekamMedisPasien> getByCriteria(RekamMedisPasien bean) throws GeneralBOException;
    public List<RekamMedisPasien> getListRekamMedisByTipePelayanan(String tipePelayanan, String jenis, String id) throws GeneralBOException;
    public List<RekamMedisPasien> getRiwayatListRekamMedis(String id, String tipePelayanan, String jenis) throws GeneralBOException;
    public List<RekamMedisPasien> getListRekamMedisLama(String id, String branchId) throws GeneralBOException;
}
