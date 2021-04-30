package com.neurix.hris.transaksi.training.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.medicalrecord.model.BuktiPengobatan;
import com.neurix.hris.transaksi.medicalrecord.model.MedicalRecord;
import com.neurix.hris.transaksi.medicalrecord.model.Pengobatan;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.training.model.Training;
import com.neurix.hris.transaksi.training.model.TrainingDoc;
import com.neurix.hris.transaksi.training.model.TrainingPerson;

import java.util.List;

/**
 * Created by thinkpad on 19/03/2018.
 */
public interface TrainingBo extends BaseMasterBo<Training> {
    public String getNextPersonId() throws GeneralBOException;
    public String getDocId() throws GeneralBOException;

    List<Notifikasi> saveAddTraining(Training training, List<TrainingPerson> trainingPersonList) throws GeneralBOException;

    public List<TrainingPerson> searchTrainingPerson(TrainingPerson bean) throws GeneralBOException;
    public List<TrainingPerson> searchTrainingPerson(String nip) throws GeneralBOException;
    public List<TrainingDoc> searchTrainingDoc(TrainingDoc bean) throws GeneralBOException;
    public void saveUpdateTraining(Training training, List<TrainingPerson> trainingPersonList) throws GeneralBOException;
    public void saveDocTraining(List<TrainingDoc> trainingDocList) throws GeneralBOException;
    public void saveApproveAtasan(TrainingPerson trainingPerson) throws GeneralBOException;
    public void saveApproveKepala(TrainingPerson trainingPerson) throws GeneralBOException;
    public void saveCloseTraining(Training bean) throws GeneralBOException;

    List<Notifikasi> saveApproveSdm(TrainingPerson trainingPerson) throws GeneralBOException;

    List<Object[]> findInfoTraining(String idTraining, String nip) throws GeneralBOException;
    TrainingPerson findTrainingPerson(String idTrainingPerson) throws GeneralBOException;

    Notifikasi findNotif(String idNotif) throws GeneralBOException;

    List<Object[]> findAllConfirmByIdAtasan(String nip, String flag) throws GeneralBOException;
}
