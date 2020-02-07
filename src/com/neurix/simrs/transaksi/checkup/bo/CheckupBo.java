package com.neurix.simrs.transaksi.checkup.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.model.AlertPasien;
import com.neurix.simrs.transaksi.checkup.model.CheckupAlergi;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSImrsCheckupAlergiEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.pemeriksaanfisik.model.ItSimrsPemeriksaanFisikEntity;
import com.neurix.simrs.transaksi.pemeriksaanfisik.model.PemeriksaanFisik;
import com.neurix.simrs.transaksi.resikojatuh.model.ImSimrsSkorResikoJatuhEntity;
import com.neurix.simrs.transaksi.resikojatuh.model.ResikoJatuh;
import com.neurix.simrs.transaksi.resikojatuh.model.ResikoJatuhResponse;

import java.util.List;

/**
 * Created by Toshiba on 08/11/2019.
 */
public interface CheckupBo {
    public List<HeaderCheckup> getByCriteria(HeaderCheckup bean)throws GeneralBOException;
    public void saveAdd(HeaderCheckup bean)throws GeneralBOException;
    public void saveEdit(HeaderCheckup bean)throws GeneralBOException;
    public void updatePenunjang(HeaderCheckup bean) throws GeneralBOException;
    public void saveAddAlergi(CheckupAlergi bean) throws GeneralBOException;
    public void saveEditAlergi(CheckupAlergi bean) throws GeneralBOException;
    public List<ItSImrsCheckupAlergiEntity> getListAlergi(String noCheckup) throws GeneralBOException;
    public AlertPasien getAlertPasien(String idPasien, String branchId) throws GeneralBOException;
    public List<AlertPasien> listOfRekamMedic(String idPasien) throws GeneralBOException;
    public ItSimrsPemeriksaanFisikEntity getEntityPemeriksaanFisikByNoCheckup(String noCheckup) throws GeneralBOException;
    public void savePemeriksaanFisik(PemeriksaanFisik bean) throws GeneralBOException;
    public ResikoJatuhResponse getResikojatuh(ResikoJatuh bean) throws GeneralBOException;
    public List<ImSimrsSkorResikoJatuhEntity> getListSkorResikoByIdParameter(String id) throws GeneralBOException;

    Long saveErrorMessage(String message, String s);

    HeaderCheckup completeBpjs(String nomorBpjs,String unitId);
    public String getNextHeaderId();
}
