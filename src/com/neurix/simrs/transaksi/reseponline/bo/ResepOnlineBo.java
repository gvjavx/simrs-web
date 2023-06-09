package com.neurix.simrs.transaksi.reseponline.bo;

import com.neurix.common.bo.GeneralBo;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.reseponline.model.ImSimrsSettingHargaPengirimanEntity;
import com.neurix.simrs.transaksi.reseponline.model.PengirimanObat;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.reseponline.model.ResepOnline;
import com.neurix.simrs.transaksi.reseponline.model.SettingHargaPengiriman;

import java.util.List;

/**
 * @author gondok
 * Wednesday, 17/06/20 9:54
 */
public interface ResepOnlineBo {
    public List<ResepOnline> getByCriteria(ResepOnline bean) throws GeneralBOException;
    public List<PengirimanObat> getListPengirimanObat(PengirimanObat bean) throws GeneralBOException;
    public List<PermintaanResep> getListResepTelemedic(String branchId) throws GeneralBOException;
    public CrudResponse saveAddPengirimanObat(PengirimanObat bean) throws GeneralBOException;
    public List<SettingHargaPengiriman> getListResepOnlineByCriteria(SettingHargaPengiriman bean) throws GeneralBOException;
    public void saveAddSettingHargaPengiriman(SettingHargaPengiriman bean) throws GeneralBOException;
    public void saveEditSettingHargaPengiriman(SettingHargaPengiriman bean) throws GeneralBOException;
}
