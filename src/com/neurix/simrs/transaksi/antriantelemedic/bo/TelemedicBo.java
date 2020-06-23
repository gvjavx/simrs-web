package com.neurix.simrs.transaksi.antriantelemedic.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.antriantelemedic.model.AntrianTelemedic;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by reza on 08/06/20.
 */
public interface TelemedicBo {
    public List<AntrianTelemedic> getSearchByCriteria(AntrianTelemedic bean) throws GeneralBOException;
    public List<AntrianTelemedic> getListAntrianByCriteria(AntrianTelemedic bean) throws GeneralBOException;
    public ItSimrsAntrianTelemedicEntity getAntrianTelemedicEntityById(String id) throws GeneralBOException;
    public String saveAdd(ItSimrsAntrianTelemedicEntity bean, String branchId, String kodeBank) throws GeneralBOException;
    public void saveEdit(AntrianTelemedic bean, String branchId, String kodeBank) throws GeneralBOException;
    public ItSimrsAntrianTelemedicEntity getAntrianTelemedicFirstOrder(String idPelayanan, String idDokter, String status) throws GeneralBOException;
    public BigDecimal insertResepOnline(String idTransaksiOnline, List<TransaksiObatDetail> listObat) throws GeneralBOException;
    public void createPembayaranResep(AntrianTelemedic bean, List<TransaksiObatDetail> listObat) throws GeneralBOException;
}
