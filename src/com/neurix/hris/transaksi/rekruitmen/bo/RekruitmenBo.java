package com.neurix.hris.transaksi.rekruitmen.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.rekruitmen.model.*;
import com.neurix.hris.transaksi.training.model.TrainingPerson;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface RekruitmenBo extends BaseMasterBo<Rekruitmen>{
    String getNextCalonPegawaiId();

    public void saveDelete(Rekruitmen bean) throws GeneralBOException;

    Rekruitmen saveAction(RekruitmenStatus bean) throws GeneralBOException;

    RekruitmenStatus saveAddStatusClosed(RekruitmenStatus bean) throws GeneralBOException;

    void saveAddUraianPekerjaan(List<RekruitmenUraianKerjaan> beanList, RekruitmenUraianKerjaan bean) throws GeneralBOException;

    Rekruitmen saveAddRekruitmen(Rekruitmen bean, RekruitmenStatus bean2) throws GeneralBOException;

    List<RekruitmenUploadDoc> getByCriteriaDocument(Rekruitmen searchBean) throws GeneralBOException;

    List<StudyCalonPegawai> getByCriteriaStudy(Rekruitmen searchBean) throws GeneralBOException;

    List<RekruitmenStatus> getByCriteriaStatus(Rekruitmen searchBean) throws GeneralBOException;

    public List<Rekruitmen> getComboRekruitmenWithCriteria(String query) throws GeneralBOException;

    void saveClosed(Rekruitmen bean) throws GeneralBOException;

    public void saveEditCaption(Rekruitmen bean) throws GeneralBOException ;
    public List<Rekruitmen> getListOfPersonil(String query, String branchId) throws GeneralBOException;
    public List<Rekruitmen> getListOfPersonilPosition(String query) throws GeneralBOException;
    public List<Rekruitmen> getListOfRsKelas(String query, String status, String nip, String golonganId) throws GeneralBOException;
    public List<TrainingPerson> getListTrainingPerson(TrainingPerson bean) throws GeneralBOException;

    List<StudyCalonPegawai> getComboRekruitmenStudyPerson(String studyId) throws GeneralBOException;

    List<RekruitmenUploadDoc> getListRekruitmenDocumentBo(String calonPegawaiId, String flag) throws GeneralBOException;

    List<StudyCalonPegawai> getListRekruitmenStudyBo(String calonPegawaiId, String flag) throws GeneralBOException;

    List<RekruitmenStatus> getListRekruitmenStatusBo(String calonPegawaiId, String flag) throws GeneralBOException;

    List<RekruitmenUraianKerjaan> getListUraianPekerjaan(String calonPegawaiId, String flag) throws GeneralBOException;

    public void addRekruitmenDoc(RekruitmenUploadDoc bean) throws GeneralBOException ;

    public String getNextDocId() throws GeneralBOException ;


    Rekruitmen getPrintRekruitmen(String id) throws GeneralBOException;

    RekruitmenStatus getStatusById(String capegId, String statusId) throws GeneralBOException;

    List<RekruitmenKontrak> getListRekruitmenKontrakJangkaWaktu(String flag, String kontrakDari, String kontrakSelesai, String bagianName, String branchName, String alamatUnit) throws GeneralBOException;

    List<RekruitmenKontrak> getListRekruitmenKontrakUpah(String flag, BigInteger upah) throws GeneralBOException;

    List<RekruitmenKontrak> getListRekruitmenKontrakFasilitas(String flag, String positionId, String golongan, String branchId) throws GeneralBOException;

    List<RekruitmenKontrak> getListRekruitmenKontrakKewajibanPihakPertama(String flag) throws GeneralBOException;

    List<RekruitmenKontrak> getListRekruitmenKontrakKewajibanPihakKedua(String flag) throws GeneralBOException;

    List<RekruitmenKontrak> getListRekruitmenKontrakCutiLembur(String flag) throws GeneralBOException;

    List<RekruitmenKontrak> getListRekruitmenKontrakTatib(String flag) throws GeneralBOException;

    List<RekruitmenKontrak> getListRekruitmenKontrakPemutusanHubungan(String flag) throws GeneralBOException;

    List<RekruitmenKontrak> getListRekruitmenKontrakPenutup(String flag) throws GeneralBOException;

    List<RekruitmenKontrak> getListRekruitmenKontrakPeraturan(String flag) throws GeneralBOException;
}
