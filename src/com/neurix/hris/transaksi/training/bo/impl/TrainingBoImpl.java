package com.neurix.hris.transaksi.training.bo.impl;

import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.exception.GeneralBOException;

import com.neurix.common.util.CommonUtil;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.master.biodata.dao.BiodataDao;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.strukturJabatan.dao.StrukturJabatanDao;
import com.neurix.hris.master.strukturJabatan.model.ImStrukturJabatanEntity;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiDao;
import com.neurix.hris.transaksi.medicalrecord.dao.BuktiPengobatanDao;
import com.neurix.hris.transaksi.medicalrecord.dao.MedicalRecordDao;
import com.neurix.hris.transaksi.medicalrecord.dao.PengobatanDao;
import com.neurix.hris.transaksi.medicalrecord.model.*;

import com.neurix.hris.transaksi.notifikasi.dao.NotifikasiFcmDao;
import com.neurix.hris.transaksi.notifikasi.model.ImNotifikasiEntity;
import com.neurix.hris.transaksi.notifikasi.model.ItNotifikasiFcmEntity;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import com.neurix.hris.transaksi.training.bo.TrainingBo;
import com.neurix.hris.transaksi.training.dao.TrainingDao;
import com.neurix.hris.transaksi.training.dao.TrainingDocDao;
import com.neurix.hris.transaksi.training.dao.TrainingPersonDao;
import com.neurix.hris.transaksi.training.model.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class TrainingBoImpl implements TrainingBo {
    protected static transient Logger logger = Logger.getLogger(TrainingBoImpl.class);

    private TrainingDao trainingDao;
    private TrainingPersonDao trainingPersonDao;
    private TrainingDocDao trainingDocDao;
    private NotifikasiDao notifikasiDao;
    private StrukturJabatanDao strukturJabatanDao;
    private BiodataDao biodataDao;
    private NotifikasiFcmDao notifikasiFcmDao;
    private PersonilPositionDao personilPositionDao;
    private PositionDao positionDao;
    private DepartmentDao departmentDao;
    private String ACTION_CLICK = "TASK_TRAINING";

    public NotifikasiFcmDao getNotifikasiFcmDao() {
        return notifikasiFcmDao;
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public void setNotifikasiFcmDao(NotifikasiFcmDao notifikasiFcmDao) {
        this.notifikasiFcmDao = notifikasiFcmDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TrainingBoImpl.logger = logger;
    }

    public TrainingDao getTrainingDao() {
        return trainingDao;
    }

    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    public TrainingPersonDao getTrainingPersonDao() {
        return trainingPersonDao;
    }

    public void setTrainingPersonDao(TrainingPersonDao trainingPersonDao) {
        this.trainingPersonDao = trainingPersonDao;
    }

    public TrainingDocDao getTrainingDocDao() {
        return trainingDocDao;
    }

    public void setTrainingDocDao(TrainingDocDao trainingDocDao) {
        this.trainingDocDao = trainingDocDao;
    }

    public NotifikasiDao getNotifikasiDao() {
        return notifikasiDao;
    }

    public void setNotifikasiDao(NotifikasiDao notifikasiDao) {
        this.notifikasiDao = notifikasiDao;
    }

    public StrukturJabatanDao getStrukturJabatanDao() {
        return strukturJabatanDao;
    }

    public void setStrukturJabatanDao(StrukturJabatanDao strukturJabatanDao) {
        this.strukturJabatanDao = strukturJabatanDao;
    }

    public BiodataDao getBiodataDao() {
        return biodataDao;
    }

    public void setBiodataDao(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
    }

    @Override
    public void saveDelete(Training bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(Training bean) throws GeneralBOException {

    }

    @Override
    public Training saveAdd(Training bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<Training> getByCriteria(Training searchBean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Training> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getTrainingId() != null && !"".equalsIgnoreCase(searchBean.getTrainingId())) {
                hsCriteria.put("training_id", searchBean.getTrainingId());
            }
            if (searchBean.getTrainingName() != null && !"".equalsIgnoreCase(searchBean.getTrainingName())) {
                hsCriteria.put("training_name", searchBean.getTrainingName());
            }
            if (searchBean.getTipeTraining() != null && !"".equalsIgnoreCase(searchBean.getTipeTraining())) {
                hsCriteria.put("tipe_training", searchBean.getTipeTraining());
            }

            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                if ("N".equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchBean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }


            List<ItHrisTrainingEntity> itHrisTrainingEntities = null;
            try {
                itHrisTrainingEntities = trainingDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(itHrisTrainingEntities != null){
                Training returnData;
                for(ItHrisTrainingEntity listEntity : itHrisTrainingEntities){
                    returnData = new Training();
                    returnData.setTrainingId(listEntity.getTrainingId());
                    returnData.setTrainingName(listEntity.getTrainingName());
                    returnData.setTipeTraining(listEntity.getTipeTraining());
                    returnData.setTrainingStartDate(listEntity.getTrainingStartDate());
                    returnData.setTrainingEndDate(listEntity.getTrainingEndDate());

                    if (listEntity.getTrainingStartDate()!=null){
                        returnData.setStTanggalStart(CommonUtil.convertDateToString(listEntity.getTrainingStartDate()));
                    }

                    if (listEntity.getTrainingEndDate()!=null){
                        returnData.setStTanggalEnd(CommonUtil.convertDateToString(listEntity.getTrainingEndDate()));
                    }

                    if (listEntity.getInstansi() != null){
                        returnData.setInstansi(listEntity.getInstansi());
                    }
                    if (listEntity.getClosed() != null){
                        returnData.setClosed(listEntity.getClosed());
                    }

                    returnData.setClosedNote(listEntity.getClosedNote());
                    returnData.setUnitId(listEntity.getUnitId());
                    returnData.setCreatedDate(listEntity.getCreatedDate());
                    returnData.setKota(listEntity.getKota());
                    returnData.setCreatedWho(listEntity.getCreateDateWho());
                    returnData.setLastUpdate(listEntity.getLastUpdate());
                    returnData.setLastUpdateWho(listEntity.getLastUpdateWho());
                    returnData.setFlag(listEntity.getFlag());
                    returnData.setAction(listEntity.getAction());

                    hsCriteria = new HashMap();
                    hsCriteria.put("training_id",listEntity.getTrainingId());
                    hsCriteria.put("flag","Y");
                    List<ItHrisTrainingPersonEntity> trainingPersonEntities = null;

                    trainingPersonEntities = trainingPersonDao.getByCriteria(hsCriteria);
                    if (trainingPersonEntities != null){

                        for (ItHrisTrainingPersonEntity listPerson : trainingPersonEntities){
                            if (("Y").equalsIgnoreCase(listPerson.getApprovalFlag())&&listPerson.getApprovalSdm()==null){
                                returnData.setApprove(true);
                                break;
                            }
//                            else if ("Y".equalsIgnoreCase(listPerson.getApprovalSdm())){
//                                returnData.setApprove(false);
//                            }
                        }
                        for (ItHrisTrainingPersonEntity listPerson : trainingPersonEntities){
                            if (listPerson.getApprovalSdm()!=null && listPerson.getApprovalFlag()!=null&& listPerson.getApprovalBosFlag()!=null){
                                returnData.setFinish(true);
                            }else{
                                returnData.setFinish(false);
                                break;
                            }
                        }
                    }

//                    returnData.setApprove(true);

                    if ("Y".equalsIgnoreCase(listEntity.getClosed())){
                        returnData.setEdit(false);
                        returnData.setClose(false);
                        returnData.setIconApprove("<img src='\\hris\\pages\\images\\icon_success.ico'/>");
                    } else {
                        returnData.setEdit(true);
                        returnData.setClose(true);
                    }
                    listOfResult.add(returnData);
                }
            }
        }
        logger.info("[ShiftBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Training> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public String getNextPersonId() throws GeneralBOException {
        String id = "";
        id = trainingPersonDao.getNextId();
        return id;
    }

    @Override
    public String getDocId() throws GeneralBOException {
        String id = "";
        id = trainingDocDao.getNextDocId();
        return id;
    }

    @Override
    public List<Notifikasi> saveAddTraining(Training training, List<TrainingPerson> trainingPersonList) throws GeneralBOException {
        List<Notifikasi> notifikasiList = new ArrayList<>();
        boolean saved = false;
        if (training != null){
            ItHrisTrainingEntity add = new ItHrisTrainingEntity();
            String id = trainingDao.getNextId();

            if(id != null){
                add.setTrainingId(training.getTrainingId()+id);
                add.setTrainingName(training.getTrainingName());
                add.setTipeTraining(training.getTipeTraining());
                add.setKota(training.getKota());
                add.setTrainingStartDate(training.getTrainingStartDate());
                add.setTrainingEndDate(training.getTrainingEndDate());

                if (training.getInstansi() != null){
                    add.setInstansi(training.getInstansi());
                }

                add.setUnitId(training.getUnitId());
                add.setCreatedDate(training.getCreatedDate());
                add.setCreateDateWho(training.getCreatedWho());
                add.setLastUpdate(training.getLastUpdate());
                add.setLastUpdateWho(training.getLastUpdateWho());
                add.setFlag(training.getFlag());
                add.setAction(training.getAction());
                try {
                    trainingDao.addAndSave(add);
                    saved = true;
                } catch (HibernateException e) {
                    logger.error("[saveAddTraining.saveAddTraining] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }

                if (saved){
                    if (trainingPersonList != null){
                        for (TrainingPerson listPerson : trainingPersonList){
                            ItHrisTrainingPersonEntity addPerson = new ItHrisTrainingPersonEntity();
                            addPerson.setTrainingPersonId(listPerson.getTrainingPersonId());
                            addPerson.setTrainingId(add.getTrainingId());
                            addPerson.setPersonId(listPerson.getPersonId());
                            addPerson.setPersonName(listPerson.getPersonName());
                            addPerson.setDivisiId(listPerson.getDivisiId());
                            addPerson.setCreatedDate(training.getCreatedDate());
                            addPerson.setCreateDateWho(training.getCreatedWho());
                            addPerson.setLastUpdate(training.getLastUpdate());
                            addPerson.setLastUpdateWho(training.getLastUpdateWho());
                            addPerson.setFlag("Y");
                            addPerson.setAction("C");

                            // if personId is not null, search Data relation in Struktur Jabatan
                            if (listPerson.getPersonId() != null){
                                ImBiodataEntity imBiodataEntity = new ImBiodataEntity();
                                try {
                                    imBiodataEntity =  biodataDao.getById("nip", listPerson.getPersonId(), "Y");
                                } catch (HibernateException e) {
                                    logger.error("[TrainingBoImpl.saveAddTraining] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem when saving new data, please info to your admin..." + e.getMessage());
                                }

                                //Send notif ke atasan
                                Notifikasi notifAtasan= new Notifikasi();
                                notifAtasan.setNip(listPerson.getPersonId());
                                notifAtasan.setNoRequest(add.getTrainingId());
                                notifAtasan.setTipeNotifId("TN23");
                                notifAtasan.setTipeNotifName("Training");
                                notifAtasan.setNote("Data Dari User : " + imBiodataEntity.getNamaPegawai() + " Menunggu di Approve");
                                notifAtasan.setCreatedWho(listPerson.getPersonId());
                                notifAtasan.setTo("atasan");

                                notifikasiList.add(notifAtasan);

//                                List<StrukturJabatan> strukturJabatanList = new ArrayList<>();
//
//                                try {
//                                    strukturJabatanList = strukturJabatanDao.searchStrukturRelation2(personId,branchId);
//                                } catch (HibernateException e) {
//                                    logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                }
//                                for (StrukturJabatan strukturJabatan:strukturJabatanList){
//                                    // Search Leader
//                                    Map hsCriteria;
//                                    if (strukturJabatan != null){
//                                        String[] parts = strukturJabatan.getParentId().split("-");
//                                        String parent = parts[0];
//
//                                        if (parent != null){
//
//                                            // search data postion_id from struktur jabatan by parameter parent
//                                            hsCriteria = new HashMap();
//                                            hsCriteria.put("struktur_jabatan_id", parent);
//                                            hsCriteria.put("flag","Y");
//                                            List<ImStrukturJabatanEntity> strukturJabatanEntities = null;
//                                            try {
//                                                strukturJabatanEntities = strukturJabatanDao.getByCriteria(hsCriteria);
//                                            } catch (HibernateException e) {
//                                                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                            }
//
//                                            if (strukturJabatanEntities!=null){
//                                                for (ImStrukturJabatanEntity listStruktur : strukturJabatanEntities){
//
//                                                    // search data nip from personil by parameter position_id from struktur jabatan
//                                                    String stPosition = "";
//                                                    if (listStruktur.getPositionId() != null){
//                                                        stPosition = String.valueOf(listStruktur.getPositionId());
//                                                    }
//                                                    hsCriteria = new HashMap();
//                                                    hsCriteria.put("position_id",stPosition);
//                                                    hsCriteria.put("branch_id",branchId);
//                                                    hsCriteria.put("flag","Y");
//                                                    List<ItPersonilPositionEntity> itPersonilPositionEntities = null;
//                                                    try {
//                                                        itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteria);
//                                                    } catch (HibernateException e) {
//                                                        logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                    }
//
//                                                    if (itPersonilPositionEntities != null){
//                                                        for (ItPersonilPositionEntity listPersonilPosition : itPersonilPositionEntities){
//                                                            String nip = listPersonilPosition.getNip();
//
//                                                            hsCriteria = new HashMap();
//                                                            hsCriteria.put("nip",nip);
//                                                            hsCriteria.put("flag","Y");
//
//                                                            List<ImBiodataEntity> imBiodataEntityList = null;
//                                                            try {
//                                                                imBiodataEntityList = biodataDao.getByCriteria(hsCriteria);
//                                                            } catch (HibernateException e) {
//                                                                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
//                                                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                            }
//
//                                                            if (imBiodataEntityList != null){
//                                                                for (ImBiodataEntity listBio : imBiodataEntityList){
//
//                                                                    //set date nip from personil into Trainig person's approvalId
//                                                                    addPerson.setApprovalId(listBio.getNip());
//                                                                    addPerson.setApprovalName(listBio.getNamaPegawai());
//                                                                }
//                                                            }
//                                                            // Send Notification
//                                                            ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
//                                                            String idNotif = notifikasiDao.getNextNotifikasiId();
//
//                                                            addNotif.setNotifId(idNotif);
//
//                                                            addNotif.setNote("Data Dari User : "+addPerson.getPersonName()+" Menunggu di Approve");
//                                                            String noteMobile = addPerson.getPersonName()+" menunggu di Approve";
//                                                            addNotif.setTipeNotifId("TN23");
//                                                            addNotif.setTipeNotifName("Training");
//                                                            addNotif.setRead("Y");
//                                                            addNotif.setFlag("Y");
//                                                            addNotif.setAction("C");
//                                                            addNotif.setNip(addPerson.getApprovalId());
//                                                            addNotif.setFromPerson(addPerson.getPersonId());
//                                                            addNotif.setNoRequest(add.getTrainingId());
//                                                            addNotif.setCreatedDate(training.getCreatedDate());
//                                                            addNotif.setCreatedWho(training.getCreatedWho());
//                                                            addNotif.setLastUpdate(training.getLastUpdate());
//                                                            addNotif.setLastUpdateWho(training.getLastUpdateWho());
//
//                                                            try {
//                                                                notifikasiDao.addAndSave(addNotif);
//                                                            } catch (HibernateException e) {
//                                                                logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
//                                                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                            }
//
//                                                            List<ItNotifikasiFcmEntity> notifikasiFcm = null;
//                                                            try {
//                                                                notifikasiFcm = notifikasiFcmDao.getAll();
//                                                            } catch (HibernateException e) {
//                                                                logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
//                                                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                                                            }
//
//                                                            for (ItNotifikasiFcmEntity entity : notifikasiFcm){
//                                                                if(entity.getUserId().equals(addNotif.getNip())){
//                                                                    FirebasePushNotif.sendNotificationFirebase(entity.getTokenFcm(), addNotif.getTipeNotifName(), noteMobile, ACTION_CLICK);
//                                                                    break;
//                                                                }
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
                            }
                            try {
                                trainingPersonDao.addAndSave(addPerson);
                            } catch (HibernateException e) {
                                logger.error("[saveAddTraining.saveAddPerson] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }
                        }
                    }
                }

            }
        }
        return notifikasiList;
    }

    @Override
    public List<TrainingPerson> searchTrainingPerson(TrainingPerson bean) throws GeneralBOException {
        List<TrainingPerson> listOfResult = new ArrayList<TrainingPerson>();
        if (bean != null){
            Map hsCriteria = new HashMap();
            if (!"".equalsIgnoreCase(bean.getTrainingId()) && bean.getTrainingId() != ""){
                hsCriteria.put("training_id", bean.getTrainingId());
                hsCriteria.put("flag","Y");
            }

            List<ItHrisTrainingPersonEntity> itHrisTrainingPersonEntities = null;
            try {
                itHrisTrainingPersonEntities = trainingPersonDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[saveAddTraining.saveAddTraining] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            if (itHrisTrainingPersonEntities!=null){
                TrainingPerson addData;
                for (ItHrisTrainingPersonEntity listData : itHrisTrainingPersonEntities){
                    addData = new TrainingPerson();
                    addData.setTrainingPersonId(listData.getTrainingPersonId());
                    addData.setTrainingId(listData.getTrainingId());
                    addData.setPersonId(listData.getPersonId());
                    addData.setPersonName(listData.getPersonName());
                    addData.setDivisiId(listData.getDivisiId());

                    if (listData.getDivisiId()!=null){
                        ImDepartmentEntity departmentEntity = new ImDepartmentEntity();
                        try {
                            departmentEntity = departmentDao.getById("departmentId",listData.getDivisiId());
                        } catch (HibernateException e) {
                            logger.error("[saveAddTraining.saveAddTraining] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                        }
                        if (departmentEntity.getDepartmentName()!=null){
                            addData.setDivisiName(departmentEntity.getDepartmentName());
                        }
                    }

                    addData.setApprovalId(listData.getApprovalId());
                    addData.setApprovalName(listData.getApprovalName());
                    addData.setApprovalDate(listData.getApprovalDate());
                    addData.setApprovalFlag(listData.getApprovalFlag());
                    addData.setNotApprovalNote(listData.getNotApprovalNote());
                    addData.setApprovalBosId(listData.getApprovalBosId());
                    addData.setApprovalBosName(listData.getApprovalBosName());
                    addData.setApprovalBosDate(listData.getApprovalBosDate());
                    addData.setApprovalBosFlag(listData.getApprovalBosFlag());
                    addData.setNotApprovalBosNote(listData.getNotApprovalBosNote());
                    addData.setCreatedDate(listData.getCreatedDate());
                    addData.setCreatedWho(listData.getCreateDateWho());
                    addData.setLastUpdate(listData.getLastUpdate());
                    addData.setLastUpdateWho(listData.getLastUpdateWho());
                    addData.setFlag(listData.getFlag());
                    addData.setAction(listData.getAction());
                    addData.setApprovalSdmDate(listData.getApprovalSdmDate());
                    addData.setApprovalSdm(listData.getApprovalSdm());
                    addData.setNotApprovalNote(listData.getNotApprovalNote());

                    if ("Y".equalsIgnoreCase(listData.getApprovalFlag())){
                        addData.setIconApproveAtasan("<img src='\\hris\\pages\\images\\icon_success.ico'/>");
                    } else if ("N".equalsIgnoreCase(listData.getApprovalFlag())){
                        addData.setIconApproveAtasan("<img src='\\hris\\pages\\images\\icon_failure.ico'/>");
                    }

                    if ("Y".equalsIgnoreCase(listData.getApprovalBosFlag())){
                        addData.setIconApproveKepala("<img src='\\hris\\pages\\images\\icon_success.ico'/>");
                    } else if ("N".equalsIgnoreCase(listData.getApprovalBosFlag())){
                        addData.setIconApproveKepala("<img src='\\hris\\pages\\images\\icon_failure.ico'/>");
                    }

                    if ("Y".equalsIgnoreCase(listData.getApprovalSdm())){
                        addData.setIconApproveSdm("<img src='\\hris\\pages\\images\\icon_success.ico'/>");
                        addData.setApproveSdm(false);
                    } else if ("N".equalsIgnoreCase(listData.getApprovalBosFlag())){
                        addData.setIconApproveSdm("<img src='\\hris\\pages\\images\\icon_failure.ico'/>");
                    } else if (listData.getApprovalFlag() == null) {
                        addData.setApproveSdm(false);
                    } else {
                        addData.setApproveSdm(true);
                    }
                    listOfResult.add(addData);
                }
            }
        }
        return listOfResult;
    }

    @Override
    public List<TrainingPerson> searchTrainingPerson(String nip) throws GeneralBOException {
        List<TrainingPerson> trainingPersonList = new ArrayList<>();

        trainingPersonList = trainingPersonDao.getDataTrainingPerson(nip);

        return trainingPersonList;
    }

    @Override
    public List<TrainingDoc> searchTrainingDoc(TrainingDoc bean) throws GeneralBOException {
        List<TrainingDoc> listOfResult = new ArrayList<TrainingDoc>();
        if (bean != null){
            Map hsCriteria = new HashMap();
            if (!"".equalsIgnoreCase(bean.getTrainingId()) && bean.getTrainingId() != ""){
                hsCriteria.put("training_id", bean.getTrainingId());
            }

            if (!"".equalsIgnoreCase(bean.getTrainingPersonId()) && bean.getTrainingPersonId() != ""){
                hsCriteria.put("training_person_id",bean.getTrainingPersonId());
            }

            if (!"".equalsIgnoreCase(bean.getFlag()) && bean.getFlag() != ""){
                hsCriteria.put("flag",bean.getFlag());
            } else {
                hsCriteria.put("flag","Y");
            }

            List<ItHrisTrainingDocEntity> itHrisTrainingDocEntities = null;
            try {
                itHrisTrainingDocEntities = trainingDocDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[saveAddTraining.searchTrainingDoc] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (itHrisTrainingDocEntities != null){
                TrainingDoc addData;
                for (ItHrisTrainingDocEntity listData : itHrisTrainingDocEntities){
                    addData = new TrainingDoc();
                    addData.setTrainingDocId(listData.getTrainingDocId());
                    addData.setTrainingPersonId(listData.getTrainingPersonId());
                    addData.setTrainingId(listData.getTrainingId());
                    addData.setFileUploadDoc(listData.getFileUploadDoc());
                    addData.setCreatedDate(listData.getCreatedDate());
                    addData.setCreatedWho(listData.getCreateDateWho());
                    addData.setLastUpdate(listData.getLastUpdate());
                    addData.setLastUpdateWho(listData.getLastUpdateWho());
                    addData.setFlag(listData.getFlag());
                    addData.setAction(listData.getAction());
                    listOfResult.add(addData);
                }
            }
        }
        return listOfResult;
    }

    @Override
    public void saveUpdateTraining(Training training, List<TrainingPerson> trainingPersonList) throws GeneralBOException {
        boolean saved = false;
        boolean sendNotif = false;
        if (training != null){
            ItHrisTrainingEntity add = new ItHrisTrainingEntity();
            add.setTrainingId(training.getTrainingId());
            add.setTrainingName(training.getTrainingName());
            add.setTipeTraining(training.getTipeTraining());
            add.setKota(training.getKota());
            add.setTrainingStartDate(training.getTrainingStartDate());
            add.setTrainingEndDate(training.getTrainingEndDate());

            if (training.getInstansi() != null){
                add.setInstansi(training.getInstansi());
            }
            add.setClosed(training.getClosed());
            add.setClosedNote(training.getClosedNote());
            add.setUnitId(training.getUnitId());
            add.setCreatedDate(training.getCreatedDate());
            add.setLastUpdate(training.getLastUpdate());
            add.setCreateDateWho(training.getCreatedWho());
            add.setLastUpdateWho(training.getLastUpdateWho());
            add.setFlag(training.getFlag());
            add.setAction(training.getAction());
            try {
                trainingDao.updateAndSave(add);
                saved = true;
            } catch (HibernateException e) {
                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
            if (saved){
                if (trainingPersonList != null){
                    for (TrainingPerson listPerson : trainingPersonList){
                        ItHrisTrainingPersonEntity addPerson = new ItHrisTrainingPersonEntity();

                        addPerson.setTrainingPersonId(listPerson.getTrainingPersonId());
                        addPerson.setTrainingId(add.getTrainingId());
                        addPerson.setPersonId(listPerson.getPersonId());
                        addPerson.setPersonName(listPerson.getPersonName());
                        addPerson.setDivisiId(listPerson.getDivisiId());
                        addPerson.setApprovalId(listPerson.getApprovalId());
                        addPerson.setApprovalName(listPerson.getApprovalName());
                        addPerson.setApprovalDate(listPerson.getApprovalDate());
                        addPerson.setApprovalFlag(listPerson.getApprovalFlag());
                        addPerson.setNotApprovalNote(listPerson.getNotApprovalNote());
                        addPerson.setApprovalBosId(listPerson.getApprovalBosId());
                        addPerson.setApprovalSdm(listPerson.getApprovalSdm());
                        addPerson.setApprovalSdmDate(listPerson.getApprovalSdmDate());
                        addPerson.setApprovalBosName(listPerson.getApprovalBosName());
                        addPerson.setApprovalBosDate(listPerson.getApprovalBosDate());
                        addPerson.setApprovalBosFlag(listPerson.getApprovalBosFlag());
                        addPerson.setNotApprovalBosNote(listPerson.getNotApprovalBosNote());


                        if (listPerson.isAdd()){

                            String personId = listPerson.getPersonId();
                            String branchId=null;

                            List<ItPersonilPositionEntity> personilPositionEntityList= new ArrayList<>();

                            personilPositionEntityList=personilPositionDao.getListPersonilPosition(personId);

                            for (ItPersonilPositionEntity personilPositionEntity:personilPositionEntityList){
                                branchId=personilPositionEntity.getBranchId();
                            }

                            // if personId is not null, search Data relation in Struktur Jabatan
                            if (personId != null){
                                List<StrukturJabatan> strukturJabatanList = new ArrayList<>();

                                try {
                                    strukturJabatanList = strukturJabatanDao.searchStrukturRelation2(personId,branchId);
                                } catch (HibernateException e) {
                                    logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                }

                                // Search Leader
                                Map hsCriteria;
                                if (strukturJabatanList != null){
                                    for (StrukturJabatan strukturJabatan :strukturJabatanList ){
                                        String[] parts = strukturJabatan.getParentId().split("-");
                                        String parent = parts[0];

                                        if (parent != null){

                                            // search data postion_id from struktur jabatan by parameter parent
                                            hsCriteria = new HashMap();
                                            hsCriteria.put("struktur_jabatan_id", parent);
                                            hsCriteria.put("branch_id", branchId);
                                            hsCriteria.put("flag","Y");
                                            List<ImStrukturJabatanEntity> strukturJabatanEntities = null;
                                            try {
                                                strukturJabatanEntities = strukturJabatanDao.getByCriteria(hsCriteria);
                                            } catch (HibernateException e) {
                                                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                            }

                                            if (strukturJabatanEntities!=null){
                                                for (ImStrukturJabatanEntity listStruktur : strukturJabatanEntities){

                                                    // search data nip from personil by parameter position_id from struktur jabatan
                                                    hsCriteria = new HashMap();
                                                    hsCriteria.put("position_id",listStruktur.getPositionId());
                                                    hsCriteria.put("branch_id", branchId);
                                                    hsCriteria.put("flag","Y");
                                                    List<ItPersonilPositionEntity> itPersonilPositionEntities = null;
                                                    try {
                                                        itPersonilPositionEntities = personilPositionDao.getByCriteria(hsCriteria);
                                                    } catch (HibernateException e) {
                                                        logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                    }

                                                    if (itPersonilPositionEntities != null){
                                                        for (ItPersonilPositionEntity listPersonilPosition : itPersonilPositionEntities){
                                                            String nip = listPersonilPosition.getNip();

                                                            hsCriteria = new HashMap();
                                                            hsCriteria.put("nip",nip);
                                                            hsCriteria.put("flag","Y");

                                                            List<ImBiodataEntity> imBiodataEntityList = null;
                                                            try {
                                                                imBiodataEntityList = biodataDao.getByCriteria(hsCriteria);
                                                            } catch (HibernateException e) {
                                                                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                                                            }

                                                            if (imBiodataEntityList != null){
                                                                for (ImBiodataEntity listBio : imBiodataEntityList){

                                                                    //set date nip from personil into Trainig person's approvalId
                                                                    addPerson.setApprovalId(listBio.getNip());
                                                                    addPerson.setApprovalName(listBio.getNamaPegawai());
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                            addPerson.setCreatedDate(listPerson.getLastUpdate());
                            addPerson.setCreateDateWho(listPerson.getLastUpdateWho());
                            addPerson.setLastUpdate(listPerson.getLastUpdate());
                            addPerson.setLastUpdateWho(listPerson.getLastUpdateWho());
                            addPerson.setFlag("Y");
                            addPerson.setAction("C");
                            try {
                                trainingPersonDao.addAndSave(addPerson);
                                saved = true;
                                sendNotif = true;
                            } catch (HibernateException e) {
                                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }
                        } else {
                            addPerson.setCreatedDate(listPerson.getCreatedDate());
                            addPerson.setCreateDateWho(listPerson.getCreatedWho());
                            addPerson.setLastUpdate(listPerson.getLastUpdate());
                            addPerson.setLastUpdateWho(listPerson.getLastUpdateWho());
                            addPerson.setFlag(listPerson.getFlag());
                            addPerson.setAction("U");
                            try {
                                trainingPersonDao.updateAndSave(addPerson);
                                saved = true;
                            } catch (HibernateException e) {
                                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }
                        }
                        if (sendNotif){
                            // Send Notification
                            ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
                            String idNotif = notifikasiDao.getNextNotifikasiId();

                            addNotif.setNotifId(idNotif);

                            addNotif.setNote("Data Dari User : "+addPerson.getPersonName()+" Menunggu di Approve");
                            addNotif.setTipeNotifId("TN23");
                            addNotif.setTipeNotifName("Training");
                            addNotif.setRead("Y");
                            addNotif.setFlag("Y");
                            addNotif.setAction("C");
                            addNotif.setNip(addPerson.getApprovalId());
                            addNotif.setFromPerson(addPerson.getPersonId());
                            addNotif.setNoRequest(add.getTrainingId());
                            addNotif.setCreatedDate(training.getCreatedDate());
                            addNotif.setCreatedWho(training.getCreatedWho());
                            addNotif.setLastUpdate(training.getLastUpdate());
                            addNotif.setLastUpdateWho(training.getLastUpdateWho());

                            try {
                                notifikasiDao.addAndSave(addNotif);
                            } catch (HibernateException e) {
                                logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
                                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                            }
                        }
                    }
                }
            }


//            if (medicalRecord.isEdit() == true){
//                ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
//                String idNotif = notifikasiDao.getNextNotifikasiId();
////                Long notifId = Long.parseLong(idNotif);
//
//                addNotif.setNotifId(idNotif);
//                addNotif.setNote("Data Telah di Update dan Menunggu Untuk Approve Medical Record untuk id Medical record : "+medicalRecord.getMedicalRecordId());
//                addNotif.setTipeNotifId("TN01");
//                addNotif.setTipeNotifName("Medical Record");
//                addNotif.setRead("Y");
//                addNotif.setFlag("Y");
//                addNotif.setAction("C");
//                addNotif.setNip(medicalRecord.getNip());
//                addNotif.setCreatedDate(medicalRecord.getCreatedDate());
//                addNotif.setCreatedWho(medicalRecord.getCreatedWho());
//                addNotif.setLastUpdate(medicalRecord.getLastUpdate());
//                addNotif.setLastUpdateWho(medicalRecord.getLastUpdateWho());
//
//                try {
//                    notifikasiDao.addAndSave(addNotif);
//                } catch (HibernateException e) {
//                    logger.error("[TrainingBoImpl.saveMedicalRecord] Error, " + e.getMessage());
//                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//                }
//            }
        }
    }

    @Override
    public void saveDocTraining(List<TrainingDoc> trainingDocList) throws GeneralBOException {
        if (trainingDocList != null){
            for (TrainingDoc listData : trainingDocList){
                ItHrisTrainingDocEntity add = new ItHrisTrainingDocEntity();

                add.setTrainingDocId(listData.getTrainingDocId());
                add.setTrainingPersonId(listData.getTrainingPersonId());
                add.setFileUploadDoc(listData.getFileUploadDoc());
                add.setNote(listData.getNote());
                add.setTrainingId(listData.getTrainingId());
                add.setCreatedDate(listData.getCreatedDate());
                add.setCreateDateWho(listData.getCreatedWho());
                add.setLastUpdate(listData.getLastUpdate());
                add.setLastUpdateWho(listData.getLastUpdateWho());

                if (listData.isAdd()){
                    add.setFlag("Y");
                    add.setAction("C");
                    try {
                        trainingDocDao.addAndSave(add);
                    } catch (HibernateException e) {
                        logger.error("[TrainingBoImpl.saveBuktiPengobatan] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }
                } else  {
                    add.setFlag(listData.getFlag());
                    add.setAction("U");
                    try {
                        trainingDocDao.updateAndSave(add);
                    } catch (HibernateException e) {
                        logger.error("[TrainingBoImpl.saveBuktiPengobatan] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public void saveApproveAtasan(TrainingPerson trainingPerson) throws GeneralBOException {
        boolean saved = false;
        if (trainingPerson != null){
            ItHrisTrainingPersonEntity addPerson = new ItHrisTrainingPersonEntity();

            addPerson.setTrainingPersonId(trainingPerson.getTrainingPersonId());
            addPerson.setTrainingId(trainingPerson.getTrainingId());
            addPerson.setPersonId(trainingPerson.getPersonId());
            addPerson.setPersonName(trainingPerson.getPersonName());
            addPerson.setDivisiId(trainingPerson.getDivisiId());
            addPerson.setApprovalId(trainingPerson.getApprovalId());
            addPerson.setApprovalName(trainingPerson.getApprovalName());
            addPerson.setApprovalDate(trainingPerson.getApprovalDate());
            addPerson.setApprovalFlag(trainingPerson.getApprovalFlag());
            addPerson.setNotApprovalNote(trainingPerson.getNotApprovalNote());
            addPerson.setApprovalBosId(trainingPerson.getApprovalBosId());
            addPerson.setApprovalBosName(trainingPerson.getApprovalBosName());
            addPerson.setApprovalBosDate(trainingPerson.getApprovalBosDate());
            addPerson.setApprovalBosFlag(trainingPerson.getApprovalBosFlag());
            addPerson.setNotApprovalBosNote(trainingPerson.getNotApprovalBosNote());


            addPerson.setCreatedDate(trainingPerson.getLastUpdate());
            addPerson.setCreateDateWho(trainingPerson.getLastUpdateWho());
            addPerson.setLastUpdate(trainingPerson.getLastUpdate());
            addPerson.setLastUpdateWho(trainingPerson.getLastUpdateWho());
            addPerson.setFlag("Y");
            addPerson.setAction("U");
            try {
                trainingPersonDao.updateAndSave(addPerson);
                saved = true;
            } catch (HibernateException e) {
                logger.error("[TrainingBoImpl.saveApproveAtasan] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            //untuk set read to approve
            List<ImNotifikasiEntity> imNotifikasiEntityList = new ArrayList<>();

            try {
                imNotifikasiEntityList = notifikasiDao.getDataByNoRequest(trainingPerson.getTrainingId(),trainingPerson.getPersonId());
            } catch (HibernateException e) {
                logger.error("[TrainingBoImpl.saveApproveAtasan] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            for (ImNotifikasiEntity updateNotif : imNotifikasiEntityList){
                updateNotif.setFlag("N");
                notifikasiDao.updateAndSave(updateNotif);
            }

            if (saved){
                // Send Notification
                ImNotifikasiEntity addNotif = new ImNotifikasiEntity();
                String idNotif = notifikasiDao.getNextNotifikasiId();

                addNotif.setNotifId(idNotif);

                addNotif.setNote("Data Dari User : "+addPerson.getPersonName()+" Menunggu di Approve. dengan training id : "+trainingPerson.getTrainingId());
                addNotif.setTipeNotifId("umum");
                addNotif.setTipeNotifName("Training");
                addNotif.setRead("Y");
                addNotif.setFlag("Y");
                addNotif.setAction("C");
                addNotif.setNip("0001");
                addNotif.setFromPerson(addPerson.getPersonId());
                addNotif.setNoRequest(addPerson.getTrainingId());
                addNotif.setCreatedDate(addPerson.getCreatedDate());
                addNotif.setCreatedWho(addPerson.getCreateDateWho());
                addNotif.setLastUpdate(addPerson.getLastUpdate());
                addNotif.setLastUpdateWho(addPerson.getLastUpdateWho());

                try {
                    notifikasiDao.addAndSave(addNotif);
                } catch (HibernateException e) {
                    logger.error("[TrainingBoImpl.saveApproveAtasan] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
                }
            }
        }
    }

    @Override
    public void saveApproveKepala(TrainingPerson trainingPerson) throws GeneralBOException {
        if (trainingPerson != null) {
            ItHrisTrainingPersonEntity addPerson = new ItHrisTrainingPersonEntity();

            addPerson.setTrainingPersonId(trainingPerson.getTrainingPersonId());
            addPerson.setTrainingId(trainingPerson.getTrainingId());
            addPerson.setPersonId(trainingPerson.getPersonId());
            addPerson.setPersonName(trainingPerson.getPersonName());
            addPerson.setDivisiId(trainingPerson.getDivisiId());
            addPerson.setApprovalId(trainingPerson.getApprovalId());
            addPerson.setApprovalName(trainingPerson.getApprovalName());
            addPerson.setApprovalDate(trainingPerson.getApprovalDate());
            addPerson.setApprovalFlag(trainingPerson.getApprovalFlag());
            addPerson.setNotApprovalNote(trainingPerson.getNotApprovalNote());
            addPerson.setApprovalBosId(trainingPerson.getApprovalBosId());
            addPerson.setApprovalBosName(trainingPerson.getApprovalBosName());
            addPerson.setApprovalBosDate(trainingPerson.getApprovalBosDate());
            addPerson.setApprovalBosFlag(trainingPerson.getApprovalBosFlag());
            addPerson.setNotApprovalBosNote(trainingPerson.getNotApprovalBosNote());
            addPerson.setCreatedDate(trainingPerson.getLastUpdate());
            addPerson.setCreateDateWho(trainingPerson.getLastUpdateWho());
            addPerson.setLastUpdate(trainingPerson.getLastUpdate());
            addPerson.setLastUpdateWho(trainingPerson.getLastUpdateWho());
            addPerson.setFlag("Y");
            addPerson.setAction("U");
            addPerson.setApprovalSdm(trainingPerson.getApprovalSdm());
            addPerson.setApprovalSdmDate(trainingPerson.getApprovalSdmDate());
            addPerson.setNotApprovalSdmNote(trainingPerson.getNotApprovalSdmNote());
            try {
                trainingPersonDao.updateAndSave(addPerson);
            } catch (HibernateException e) {
                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            //untuk set read to approve
            List<ImNotifikasiEntity> imNotifikasiEntityList = new ArrayList<>();

            try {
                imNotifikasiEntityList = notifikasiDao.getDataByNoRequest(trainingPerson.getTrainingId(),trainingPerson.getPersonId());
            } catch (HibernateException e) {
                logger.error("[TrainingBoImpl.saveApproveAtasan] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            for (ImNotifikasiEntity updateNotif : imNotifikasiEntityList){
                updateNotif.setFlag("N");
                notifikasiDao.updateAndSave(updateNotif);
            }

        }
    }

    @Override
    public void saveCloseTraining(Training bean) throws GeneralBOException {
        if (bean != null){
            ItHrisTrainingEntity add = new ItHrisTrainingEntity();
            add.setTrainingId(bean.getTrainingId());
            add.setTrainingName(bean.getTrainingName());
            add.setTipeTraining(bean.getTipeTraining());
            add.setKota(bean.getKota());

            add.setTrainingStartDate(CommonUtil.convertStringToDate(bean.getStTanggalStart()));
            add.setTrainingEndDate(CommonUtil.convertStringToDate(bean.getStTanggalEnd()));

            if (bean.getInstansi() != null){
                add.setInstansi(bean.getInstansi());
            }

            add.setClosed(bean.getClosed());
            add.setClosedNote(bean.getClosedNote());
            add.setUnitId(bean.getUnitId());
            add.setCreatedDate(bean.getCreatedDate());
            add.setLastUpdate(bean.getLastUpdate());
            add.setCreateDateWho(bean.getCreatedWho());
            add.setLastUpdateWho(bean.getLastUpdateWho());
            add.setFlag(bean.getFlag());
            add.setAction("U");
            try {
                trainingDao.updateAndSave(add);
            } catch (HibernateException e) {
                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            List<ItHrisTrainingPersonEntity> trainingPersonEntityList = new ArrayList<>();
            trainingPersonEntityList=trainingPersonDao.getListTrainingById(bean.getTrainingId());

            for (ItHrisTrainingPersonEntity itHrisTrainingPersonEntity:trainingPersonEntityList){
                String historyId= trainingPersonDao.getNextTrainingHistoryId();
                ImtHrisHistoryTrainingPegawaiEntity history = new ImtHrisHistoryTrainingPegawaiEntity();
                List<ItHrisTrainingEntity> trainingEntityList = trainingDao.getListTrainingById(bean.getTrainingId());
                for (ItHrisTrainingEntity itHrisTrainingEntity : trainingEntityList){
                    history.setHistoryTrainingPegawaiId(historyId);
                    history.setNamaPelatihan(itHrisTrainingEntity.getTrainingName());
                    history.setLembaga(itHrisTrainingEntity.getInstansi());
                    history.setNip(itHrisTrainingPersonEntity.getPersonId());
                    DateFormat date = new SimpleDateFormat("yyyy");
                    history.setKota(itHrisTrainingEntity.getKota());
                    history.setTahun(date.format(add.getTrainingEndDate()));
                    history.setAction("C");
                    history.setCreateDateWho(bean.getCreatedWho());
                    history.setCreatedDate(bean.getCreatedDate());
                    history.setLastUpdate(bean.getLastUpdate());
                    history.setLastUpdateWho(bean.getLastUpdateWho());
                    history.setFlag("Y");

                    trainingPersonDao.addAndSaveHistory(history);
                }
            }
        }
    }

    @Override
    public List<Notifikasi> saveApproveSdm(TrainingPerson trainingPerson) throws GeneralBOException {
        boolean saved = false;
        List<Notifikasi> notifikasiList = new ArrayList<>();
        if (trainingPerson != null) {
            ItHrisTrainingPersonEntity addPerson = new ItHrisTrainingPersonEntity();
            addPerson.setTrainingPersonId(trainingPerson.getTrainingPersonId());
            addPerson.setTrainingId(trainingPerson.getTrainingId());
            addPerson.setPersonId(trainingPerson.getPersonId());
            addPerson.setPersonName(trainingPerson.getPersonName());
            addPerson.setDivisiId(trainingPerson.getDivisiId());
            addPerson.setApprovalId(trainingPerson.getApprovalId());
            addPerson.setApprovalName(trainingPerson.getApprovalName());
            addPerson.setApprovalDate(trainingPerson.getApprovalDate());
            addPerson.setApprovalFlag(trainingPerson.getApprovalFlag());
            addPerson.setNotApprovalNote(trainingPerson.getNotApprovalNote());
            addPerson.setCreatedDate(trainingPerson.getLastUpdate());
            addPerson.setCreateDateWho(trainingPerson.getLastUpdateWho());
            addPerson.setLastUpdate(trainingPerson.getLastUpdate());
            addPerson.setLastUpdateWho(trainingPerson.getLastUpdateWho());
            addPerson.setFlag("Y");
            addPerson.setAction("U");
            addPerson.setApprovalSdm(trainingPerson.getApprovalSdm());
            addPerson.setApprovalSdmDate(trainingPerson.getApprovalSdmDate());
            addPerson.setNotApprovalSdmNote(trainingPerson.getNotApprovalSdmNote());
            try {
                saved=true;
                trainingPersonDao.updateAndSave(addPerson);
            } catch (HibernateException e) {
                logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (saved){
                ImBiodataEntity imBiodataEntity = new ImBiodataEntity();
                try {
                    imBiodataEntity =  biodataDao.getById("nip", trainingPerson.getPersonId(), "Y");
                } catch (HibernateException e) {
                    logger.error("[TrainingBoImpl.saveApproveSdm] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data, please info to your admin..." + e.getMessage());
                }

                //Send notif ke Kabid
                Notifikasi notifAtasan= new Notifikasi();
                notifAtasan.setNip(trainingPerson.getPersonId());
                notifAtasan.setNoRequest(trainingPerson.getTrainingId());
                notifAtasan.setTipeNotifId("TN23");
                notifAtasan.setTipeNotifName("Training");
                notifAtasan.setNote("Data Dari User : " + imBiodataEntity.getNamaPegawai() + " Menunggu di Approve");
                notifAtasan.setCreatedWho(trainingPerson.getPersonId());
                notifAtasan.setTo("kabid");

                notifikasiList.add(notifAtasan);

            }
        }
        return notifikasiList;
    }

    @Override
    public List<Object[]> findInfoTraining(String idTraining, String nip) throws GeneralBOException {
        logger.info("[TrainingBoImpl.findInfoTraining] start process >>>");
        List<Object[]> listSppd = null;

        try {
            listSppd = trainingDao.findInfoTraining(idTraining, nip);
        } catch (HibernateException e) {
            logger.error("[SppdBoImpl.findInfoSppd] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list notif, please info to your admin..." + e.getMessage());
        }

        logger.info("[TrainingBoImpl.findInfoTraining] end process <<<");
        return listSppd;
    }

    @Override
    public TrainingPerson findTrainingPerson(String idTrainingPerson) throws GeneralBOException {
        logger.info("[TrainingBoImpl.findTrainingPerson] start process >>>");

        ItHrisTrainingPersonEntity trainingPersonEntity = null;
        try {
            trainingPersonEntity = trainingPersonDao.getById("trainingPersonId", idTrainingPerson, "Y");
        } catch (HibernateException e) {
            logger.error("[TrainingBoImpl.saveUpdateTraining] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        TrainingPerson addData = null;

        if(trainingPersonEntity != null){
            addData = new TrainingPerson();
            addData.setTrainingPersonId(trainingPersonEntity.getTrainingPersonId());
            addData.setTrainingId(trainingPersonEntity.getTrainingId());
            addData.setPersonId(trainingPersonEntity.getPersonId());
            addData.setPersonName(trainingPersonEntity.getPersonName());
            addData.setDivisiId(trainingPersonEntity.getDivisiId());
            addData.setApprovalId(trainingPersonEntity.getApprovalId());
            addData.setApprovalName(trainingPersonEntity.getApprovalName());
            addData.setApprovalDate(trainingPersonEntity.getApprovalDate());
            addData.setApprovalFlag(trainingPersonEntity.getApprovalFlag());
            addData.setNotApprovalNote(trainingPersonEntity.getNotApprovalNote());
            addData.setApprovalBosId(trainingPersonEntity.getApprovalBosId());
            addData.setApprovalBosName(trainingPersonEntity.getApprovalBosName());
            addData.setApprovalBosDate(trainingPersonEntity.getApprovalBosDate());
            addData.setApprovalBosFlag(trainingPersonEntity.getApprovalBosFlag());
            addData.setNotApprovalBosNote(trainingPersonEntity.getNotApprovalBosNote());
            addData.setCreatedDate(trainingPersonEntity.getCreatedDate());
            addData.setCreatedWho(trainingPersonEntity.getCreateDateWho());
            addData.setLastUpdate(trainingPersonEntity.getLastUpdate());
            addData.setLastUpdateWho(trainingPersonEntity.getLastUpdateWho());
            addData.setFlag(trainingPersonEntity.getFlag());
            addData.setAction(trainingPersonEntity.getAction());
            addData.setApprovalSdmDate(trainingPersonEntity.getApprovalSdmDate());
            addData.setApprovalSdm(trainingPersonEntity.getApprovalSdm());
            addData.setNotApprovalNote(trainingPersonEntity.getNotApprovalNote());

            if ("Y".equalsIgnoreCase(trainingPersonEntity.getApprovalFlag())){
                addData.setIconApproveAtasan("<img src='\\hris\\pages\\images\\icon_success.ico'/>");
            } else if ("N".equalsIgnoreCase(trainingPersonEntity.getApprovalFlag())){
                addData.setIconApproveAtasan("<img src='\\hris\\pages\\images\\icon_failure.ico'/>");
            }

            if ("Y".equalsIgnoreCase(trainingPersonEntity.getApprovalBosFlag())){
                addData.setIconApproveKepala("<img src='\\hris\\pages\\images\\icon_success.ico'/>");
            } else if ("N".equalsIgnoreCase(trainingPersonEntity.getApprovalBosFlag())){
                addData.setIconApproveKepala("<img src='\\hris\\pages\\images\\icon_failure.ico'/>");
            }

            if ("Y".equalsIgnoreCase(trainingPersonEntity.getApprovalSdm())){
                addData.setIconApproveSdm("<img src='\\hris\\pages\\images\\icon_success.ico'/>");
                addData.setApproveSdm(false);
            } else if ("N".equalsIgnoreCase(trainingPersonEntity.getApprovalBosFlag())){
                addData.setIconApproveSdm("<img src='\\hris\\pages\\images\\icon_failure.ico'/>");
            } else if (trainingPersonEntity.getApprovalFlag() == null) {
                addData.setApproveSdm(false);
            } else {
                addData.setApproveSdm(true);
            }

        }

        logger.info("[TrainingBoImpl.findTrainingPerson] end process <<<");
        return addData;
    }

    @Override
    public List<Object[]> findAllConfirmByIdAtasan(String nip, String flag) throws GeneralBOException {
        logger.info("[TrainingBoImpl.findAllConfirmByIdAtasan] start process >>>");
        List<Object[]> listConfirmStatus = null;

        try {
            listConfirmStatus = trainingDao.findAllConfirmByIdAtasan(nip, flag);
        } catch (HibernateException e) {
            logger.error("[TrainingBoImpl.findAllConfirmByIdAtasan] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list notif, please info to your admin..." + e.getMessage());
        }

        logger.info("[TrainingBoImpl.findAllConfirmByIdAtasan] end process <<<");
        return listConfirmStatus;
    }
    @Override
    public Notifikasi findNotif(String idNotif) throws GeneralBOException {
        logger.info("[TrainingBoImpl.findNotif] start process >>>");

        ImNotifikasiEntity notifikasiEntity = null;
        try {
            notifikasiEntity = notifikasiDao.getById("notifId", idNotif, "Y");
        } catch (HibernateException e) {
            logger.error("[TrainingBoImpl.findNotif] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        Notifikasi addData = null;

        if(notifikasiEntity != null){
            addData = new Notifikasi();
            addData.setNotifId(notifikasiEntity.getNotifId());
            addData.setRead(notifikasiEntity.getRead());
            addData.setFromPerson(notifikasiEntity.getFromPerson());
            addData.setNip(notifikasiEntity.getNip());
            addData.setNoRequest(notifikasiEntity.getNoRequest());
            addData.setNote(notifikasiEntity.getNote());
            addData.setTipeNotifName(notifikasiEntity.getTipeNotifName());
            addData.setTipeNotifId(notifikasiEntity.getTipeNotifId());

            addData.setCreatedDate(notifikasiEntity.getCreatedDate());
            addData.setCreatedWho(notifikasiEntity.getCreatedWho());
            addData.setLastUpdate(notifikasiEntity.getLastUpdate());
            addData.setLastUpdateWho(notifikasiEntity.getLastUpdateWho());
            addData.setFlag(notifikasiEntity.getFlag());
            addData.setAction(notifikasiEntity.getAction());
        }

        logger.info("[TrainingBoImpl.findNotif] end process <<<");
        return addData;
    }
}
