package com.neurix.simrs.transaksi.riwayattindakan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsTindakanTransitorisEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;

import java.sql.Timestamp;
import java.util.List;

public interface RiwayatTindakanBo {

    public List<RiwayatTindakan> getByCriteria(RiwayatTindakan bean) throws GeneralBOException;
    public void saveAdd(RiwayatTindakan bean) throws GeneralBOException;
    public void saveEdit(RiwayatTindakan bean) throws GeneralBOException;
    public List<RiwayatTindakan> cekTodayTarifKamar(String idDetail) throws GeneralBOException;
    public void saveTindakanTransitoris(String  idDetailCheckup, Timestamp time, String user) throws GeneralBOException;
    public List<ItSimrsRiwayatTindakanEntity> getListEntityRiwayatTindakan(RiwayatTindakan bean);
    public void updateByEntity(ItSimrsRiwayatTindakanEntity entity) throws GeneralBOException;

}