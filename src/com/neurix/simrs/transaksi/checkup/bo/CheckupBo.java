package com.neurix.simrs.transaksi.checkup.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.AlertPasien;
import com.neurix.simrs.transaksi.checkup.model.CheckupAlergi;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSImrsCheckupAlergiEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.patrus.model.ItSImrsPatrusEntity;
import com.neurix.simrs.transaksi.pemeriksaanfisik.model.ItSimrsPemeriksaanFisikEntity;
import com.neurix.simrs.transaksi.pemeriksaanfisik.model.PemeriksaanFisik;
import com.neurix.simrs.transaksi.psikososial.model.ItSimrsDataPsikososialEntity;
import com.neurix.simrs.transaksi.rekonsiliasiobat.model.ItSimrsRekonsiliasiObatEntity;
import com.neurix.simrs.transaksi.rencanarawat.model.ItSimrsRencanaRawatEntity;
import com.neurix.simrs.transaksi.resikojatuh.model.*;
import com.neurix.simrs.transaksi.transfusi.model.ItSimrsTranfusiEntity;

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
    public List<AlertPasien> listOfRekamMedic(HeaderCheckup bean) throws GeneralBOException;
    public ItSimrsPemeriksaanFisikEntity getEntityPemeriksaanFisikByNoCheckup(String noCheckup) throws GeneralBOException;
    public void savePemeriksaanFisik(PemeriksaanFisik bean) throws GeneralBOException;
    public ResikoJatuhResponse getResikojatuh(ResikoJatuh bean) throws GeneralBOException;
    public List<ImSimrsSkorResikoJatuhEntity> getListSkorResikoByIdParameter(String id) throws GeneralBOException;

    public List<ItSimrsRencanaRawatEntity> getListRencanaRawat(String noCheckup, String idDetail, String kategori) throws GeneralBOException;
    public void saveRencanaRawat(String noCheckup, String idDetail, List<ItSimrsRencanaRawatEntity> rencanaRawats) throws GeneralBOException;
    public void saveResikoJatuh(String noCheckup, List<ItSImrsResikoJatuhEntity> resikoJatuhList) throws GeneralBOException;
    public String getSumResikoJatuh(String noCheckup, String idKategori) throws GeneralBOException;
    public ImSimrsKategoriResikoJatuhEntity getKategoriResikoJatuh(Integer umur) throws GeneralBOException;
    public ItSimrsDataPsikososialEntity getDataPsikososial(String noCheckup) throws GeneralBOException;
    public void saveDataPsikososial(String noCheckup, ItSimrsDataPsikososialEntity psikososial) throws GeneralBOException;
    public List<ItSimrsTranfusiEntity> getListTranfusi(String noCheckup) throws GeneralBOException;
    public List<ItSImrsPatrusEntity> getDataPatrus(String noCheckup) throws GeneralBOException;
    public List<ItSimrsRekonsiliasiObatEntity> getListRekonsiliasiObat(String noCheckup) throws GeneralBOException;
    public void saveRekonObat(String noCheckup, ItSimrsRekonsiliasiObatEntity obatEntity) throws GeneralBOException;
    public CrudResponse savePatrus(ItSImrsPatrusEntity bean);
    public CrudResponse saveTranfusi(ItSimrsTranfusiEntity bean);

    Long saveErrorMessage(String message, String s);

    HeaderCheckup completeBpjs(String nomorBpjs,String unitId);
    public String getNextHeaderId();

    public List<HeaderCheckup> getListAntrian(String branch, String poli) throws GeneralBOException;
    public List<HeaderCheckup> getListPeriksa(String branch, String poli) throws GeneralBOException;
}
