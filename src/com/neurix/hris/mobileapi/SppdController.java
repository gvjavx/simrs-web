package com.neurix.hris.mobileapi;

import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.User;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.mobileapi.model.Sppd;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.sppd.bo.SppdBo;
import com.neurix.hris.transaksi.sppd.model.SppdPerson;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SppdController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(SppdController.class);
    private SppdBo sppdBoProxy;
    private UserBo userBoProxy;
    private NotifikasiBo notifikasiBoProxy;

    private List<Sppd> listOfSppd = new ArrayList<>();
    private Sppd model = new Sppd();
    private String id;
    private String idSppd;
    private String nip;
    private String statusApprove;
    private String idNotif;
    private String catatan;
    private String noteTransportasi;
    private String nipPengganti;
    private String confirm;

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

    public void setSppdBoProxy(SppdBo sppdBoProxy) {
        this.sppdBoProxy = sppdBoProxy;
    }

    public void setUserBoProxy(UserBo userBoProxy) {
        this.userBoProxy = userBoProxy;
    }

    public HttpHeaders create(){
        logger.info("[SppdController.create] start process POST /sppd <<<");

        List<Object[]> listSppd = null;
        try {
            listSppd = sppdBoProxy.findInfoSppd(idSppd, nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "CutiPegawaiController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[SppdController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[SppdController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        if(listSppd != null){
            for(Object[] obj : listSppd){
                Sppd sppd = new Sppd();
                sppd.setSppdId(obj[0].toString());
                sppd.setNip(obj[1].toString());
                sppd.setUserName(obj[2].toString());
                sppd.setDepartementName(obj[3].toString());
                sppd.setPositionName(obj[4].toString());
                sppd.setUnit(obj[5].toString());
                sppd.setKotaBerangkat(obj[6].toString());
                sppd.setKotaTujuan(obj[7].toString());
                sppd.setBerangkatVia(obj[8].toString());
                sppd.setKembaliVia(obj[9].toString());
                sppd.setTugasSppd(obj[10].toString());
                sppd.setPenginapan(obj[11].toString());
                sppd.setTanggalBerangkatSt(obj[12].toString());
                sppd.setTanggalKembaliSt(obj[13].toString());

                listOfSppd.add(sppd);
//                model = sppd;
            }
        }
        logger.info("[SppdController.create] end process POST /sppd <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }


    public String update(){
        logger.info("[SppdController.update] start process PUT /sppd <<<");

        User user = userBoProxy.getUserById(id, "Y");


        try {
            SppdPerson sppdPerson = new SppdPerson();
            sppdPerson.setUserIdActive(user.getUserId());
            sppdPerson.setUserNameActive(user.getUsername());
            sppdPerson.setSppdId(idSppd);
            sppdPerson.setSppdPersonId(nip);

            if(statusApprove.equals("Y")){
                sppdPerson.setApprovalFlag("Y");
                sppdPerson.setNoteAtasanTransport(noteTransportasi);
            } else {
                sppdPerson.setApprovalFlag("N");
                sppdPerson.setNotApprovalNote(catatan);
            }

            if(nipPengganti != null){
                if(nipPengganti.equals("")){
                    sppdPerson.setPejabatSementara("");
                } else {
                    sppdPerson.setPejabatSementara(nipPengganti);
                }
            }else{
                sppdPerson.setPejabatSementara("");
            }


            sppdPerson.setTmpApprove("atasan");

            String userLogin = user.getUsername();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            sppdPerson.setLastUpdateWho(userLogin);
            sppdPerson.setLastUpdate(updateTime);
            sppdPerson.setAction("U");
            sppdPerson.setFlag("Y");

            sppdBoProxy.saveApprove(sppdPerson);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[SppdController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[SppdController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }


        // FOR CHANGE READ NOTIFICATION
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        Notifikasi notifikasi = new Notifikasi();
        notifikasi.setNotifId(idNotif);
        List<Notifikasi> listOfNotifikasi = new ArrayList<Notifikasi>();

        try {
            listOfNotifikasi =  notifikasiBoProxy.getByCriteria(notifikasi);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "NotifikasiBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SppdController.create] Error when saving error,", e1);
            }
            logger.error("[SppdController.create] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
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
                            logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "NotifikasiBo.getComboDesaWithCriteria");
                        } catch (GeneralBOException e1) {
                            logger.error("[SppdController.create] Error when saving error,", e1);
                        }
                        logger.error("[SppdController.create] Error when get combo," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
                    }
                }
            }
        }
        logger.info("[SppdController.create] end process PUT /sppd <<<");
        return "success";
    }

    public String confirm() {
        logger.info("[SppdController.confirm] start process POST /sppd/{id}/confirm >>>");

        List<Object[]> listObjSppd = null;
        try {
            listObjSppd = sppdBoProxy.findAllConfirmByIdAtasan(id, confirm);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = sppdBoProxy.saveErrorMessage(e.getMessage(), "SppdController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[SppdController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[SppdController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        if(listObjSppd != null){
            for(Object[] obj : listObjSppd){
                Sppd pegawai = new Sppd();
                pegawai.setSppdId(obj[0].toString());
                pegawai.setNip(obj[1].toString());
                pegawai.setUserName(obj[2].toString());
                pegawai.setTanggalBerangkatSt(obj[3].toString());
                pegawai.setTanggalKembaliSt(obj[4].toString());
                pegawai.setUnit(obj[5].toString());

                listOfSppd.add(pegawai);
            }
        }

        logger.info("[SppdController.confirm] start process POST /sppd/{id}/confirm <<<");
        return "success";
    }

    @Override
    public Object getModel() {
        return listOfSppd != null ? listOfSppd : model;
    }

    public String getNipPengganti() {
        return nipPengganti;
    }

    public void setNipPengganti(String nipPengganti) {
        this.nipPengganti = nipPengganti;
    }

    public String getNoteTransportasi() {
        return noteTransportasi;
    }

    public void setNoteTransportasi(String noteTransportasi) {
        this.noteTransportasi = noteTransportasi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSppd() {
        return idSppd;
    }

    public void setIdSppd(String idSppd) {
        this.idSppd = idSppd;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getStatusApprove() {
        return statusApprove;
    }

    public void setStatusApprove(String statusApprove) {
        this.statusApprove = statusApprove;
    }

    public String getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(String idNotif) {
        this.idNotif = idNotif;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
