package com.neurix.hris.mobileapi;

import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.User;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.mobileapi.model.Training;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.training.bo.TrainingBo;
import com.neurix.hris.transaksi.training.model.TrainingPerson;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.sql.Timestamp;
import java.util.*;

public class TrainingController implements ModelDriven<Object> {
    private static final Logger logger = Logger.getLogger(TrainingController.class);

    private Training model = new Training();
    private Collection<Training> listOfTraining = new ArrayList<>();
    private TrainingBo trainingBoProxy;
    private UserBo userBoProxy;
    private NotifikasiBo notifikasiBoProxy;

    private String id;
    private String idTraining;
    private String trainingPersonId;
    private String nip;
    private String unit;
    private String confirm;
    private String idNotif;
    private String approveFlag;
    private String catatan;

    public String getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(String idNotif) {
        this.idNotif = idNotif;
    }

    public void setUserBoProxy(UserBo userBoProxy) {
        this.userBoProxy = userBoProxy;
    }

    public void setTrainingBoProxy(TrainingBo trainingBoProxy) {
        this.trainingBoProxy = trainingBoProxy;
    }

    public HttpHeaders create(){
        logger.info("[TrainingController.create] start process POST /training <<<");

        List<Object[]> listTraining = null;
        try {
            listTraining = trainingBoProxy.findInfoTraining(idTraining, nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "TrainingController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[TrainingController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[TrainingController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        if(listTraining != null){
            for(Object[] obj : listTraining){
                Training training = new Training();
                training.setTrainingId(obj[0].toString());
                training.setTrainingName(obj[1].toString());
                training.setNip(obj[2].toString());
                training.setUserName(obj[3].toString());
                training.setTanggalAwalSt(obj[4].toString());
                training.setTanggalAkhirSt(obj[5].toString());
                training.setUnit(obj[6].toString());
                training.setTrainingPersonId(obj[9].toString());

               listOfTraining.add(training);
            }
        }
        logger.info("[TrainingController.create] end process POST /training <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }

    public String update(){
        logger.info("[TrainingController.update] start process PUT /training <<<");
        boolean atasan = false;

        User user = userBoProxy.getUserById(id, "Y");
//        String branchId = user.getBranchId();
        String userLogin = user.getUsername();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        TrainingPerson trainingPerson = trainingBoProxy.findTrainingPerson(trainingPersonId);

        if (trainingPerson != null){
            trainingPerson.setLastUpdate(updateTime);
            trainingPerson.setLastUpdateWho(userLogin);
            trainingPerson.setApprovalFlag(approveFlag);
            trainingPerson.setNotApprovalNote(catatan);
            trainingPerson.setApprovalDate(updateTime);
            trainingPerson.setApprovalName(userLogin);
            trainingPerson.setUnitId(unit);
            atasan = true;
            trainingPerson.setLastUpdate(updateTime);
            trainingPerson.setLastUpdateWho(userLogin);
        }

        if (atasan){
            try {
                trainingBoProxy.saveApproveAtasan(trainingPerson);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "TrainingBo.getComboDesaWithCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
                }
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
            }
        }

        // FOR CHANGE READ NOTIFICATION
        Notifikasi notifikasi = new Notifikasi();
        notifikasi.setNotifId(idNotif);
        List<Notifikasi> listOfNotifikasi = new ArrayList<Notifikasi>();

        try {
            listOfNotifikasi =  notifikasiBoProxy.getByCriteria(notifikasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "trainingBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TrainingController.create] Error when saving error,", e1);
            }
            logger.error("[TrainingController.create] Error when " + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        if (listOfNotifikasi != null){
            for (Notifikasi listNotif :listOfNotifikasi){
                if (idNotif.equalsIgnoreCase(listNotif.getNotifId())){
                    listNotif.setRead("N");
                    listNotif.setLastUpdate(updateTime);
                    listNotif.setLastUpdateWho(user.getUsername());
                    listNotif.setAction("U");
                    try {
                        notifikasiBoProxy.setReadNotif(listNotif);
                    } catch (GeneralBOException e) {
                        Long logId = null;
                        try {
                            logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
                        } catch (GeneralBOException e1) {
                            logger.error("[TrainingController.create] Error when saving error,", e1);
                        }
                        logger.error("[TrainingController.create] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
                    }
                }
            }
        }
        logger.info("[TrainingController.create] end process PUT /sppd <<<");
        return "success";
    }

    public String confirm() {
        logger.info("[TrainingController.confirm] start process POST /training/{id}/confirm >>>");

        List<Object[]> listTraining = null;
        try {
            listTraining = trainingBoProxy.findAllConfirmByIdAtasan(id, confirm);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "TrainingController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[TrainingController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[TrainingController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        if(listTraining != null){
            for(Object[] obj : listTraining){
                Training pegawai = new Training();
                pegawai.setTrainingId(obj[0].toString());
                pegawai.setNip(obj[1].toString());
                pegawai.setUserName(obj[2].toString());
                pegawai.setTanggalAwalSt(obj[3].toString());
                pegawai.setTanggalAkhirSt(obj[4].toString());
                pegawai.setTrainingName(obj[5].toString());
                pegawai.setUnit(obj[6].toString());

                listOfTraining.add(pegawai);
            }
        }
        logger.info("[TrainingController.confirm] end process POST /training/{id}/confirm <<<");
        return "success";
    }



    @Override
    public Object getModel() {
        return listOfTraining.size() != 0 ? listOfTraining : model;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(String idTraining) {
        this.idTraining = idTraining;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrainingPersonId() {
        return trainingPersonId;
    }

    public void setTrainingPersonId(String trainingPersonId) {
        this.trainingPersonId = trainingPersonId;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }
}
