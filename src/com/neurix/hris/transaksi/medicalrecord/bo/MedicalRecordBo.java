package com.neurix.hris.transaksi.medicalrecord.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.medicalrecord.model.BuktiPengobatan;
import com.neurix.hris.transaksi.medicalrecord.model.MedicalRecord;
import com.neurix.hris.transaksi.medicalrecord.model.Pengobatan;

import java.util.List;

/**
 * Created by thinkpad on 19/03/2018.
 */
public interface MedicalRecordBo extends BaseMasterBo<MedicalRecord> {
    public String getNextPengobatanId() throws GeneralBOException;
    public String getNextBuktiPembayaranId() throws GeneralBOException;
    public String getNextSuratJaminanId() throws GeneralBOException;
    public void saveMedicalRecord(MedicalRecord medicalRecord, List<Pengobatan> pengobatans) throws GeneralBOException;
    public void saveBuktiPengobatan(List<BuktiPengobatan> buktiPengobatans) throws GeneralBOException;
    public List<Pengobatan> searchPengobatan(Pengobatan bean) throws GeneralBOException;
    public List<BuktiPengobatan> searchBuktiPengobatan(BuktiPengobatan bean) throws GeneralBOException;

    void koreksi(MedicalRecord bean) throws GeneralBOException;

    public void saveApprove(MedicalRecord bean) throws  GeneralBOException;
    public void saveUpdateMedicalRecord(MedicalRecord medicalRecord, List<Pengobatan> pengobatans) throws GeneralBOException;
    public MedicalRecord getPrintMedicalRecord(String id) throws GeneralBOException;
    public String getJumlahBiaya(String id) throws GeneralBOException;

    List<MedicalRecord> getMedicalRecordUser(MedicalRecord searchBean);
}
