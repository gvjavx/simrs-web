package com.neurix.simrs.transaksi.notifikasiadmin.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.notifikasiadmin.model.ItNotifikasiAdminTelemedicEntity;
import com.neurix.simrs.transaksi.notifikasiadmin.model.NotifikasiAdmin;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by reza on 06/08/20.
 */
public interface NotifikasiAdminBo {
    public List<ItNotifikasiAdminTelemedicEntity> getNotifikasiAdminEntityByCriteria(NotifikasiAdmin bean) throws GeneralBOException;
    public void createNotifikasiAdmin(String idItem, String roleId, String branchId, String userName, Timestamp createdDate, String keterangan);
    public void readNotifikasiAdmin(String idNotif, String userName, Timestamp lastUpdate) throws GeneralBOException;
}
