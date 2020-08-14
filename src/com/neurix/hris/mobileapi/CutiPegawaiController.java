package com.neurix.hris.mobileapi;

import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.User;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.mobileapi.model.CutiPegawai;
import com.neurix.hris.transaksi.cutiPegawai.bo.CutiPegawaiBo;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CutiPegawaiController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(CutiPegawaiController.class);

    private CutiPegawaiBo cutiPegawaiBoProxy;
    private UserBo userBoProxy;
    private NotifikasiBo notifikasiBoProxy;

    private CutiPegawai model = new CutiPegawai();
    private List<CutiPegawai> listOfCuti = new ArrayList<>();
    private String id;
    private String nip;
    private String idCutiPegawai;
    private String statusApprove;
    private String idNotif;
    private String catatan;
    private String confirm;
    private String nipPengganti;
    private String namaPengganti;
    private String nipUserLogin;

    public String getNipUserLogin() {
        return nipUserLogin;
    }

    public void setNipUserLogin(String nipUserLogin) {
        this.nipUserLogin = nipUserLogin;
    }

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

    public void setUserBoProxy(UserBo userBoProxy) {
        this.userBoProxy = userBoProxy;
    }

    public String getNipPengganti() {
        return nipPengganti;
    }

    public void setNipPengganti(String nipPengganti) {
        this.nipPengganti = nipPengganti;
    }

    public String getNamaPengganti() {
        return namaPengganti;
    }

    public void setNamaPengganti(String namaPengganti) {
        this.namaPengganti = namaPengganti;
    }

    public void setCutiPegawaiBoProxy(CutiPegawaiBo cutiPegawaiBoProxy) {
        this.cutiPegawaiBoProxy = cutiPegawaiBoProxy;
    }

    public HttpHeaders create(){
        logger.info("[CutiPegawaiController.create] end process POST /activity <<<");

        List<Object[]> listCuti = null;
        try {
            listCuti = cutiPegawaiBoProxy.findInfoCuti(idCutiPegawai, nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiPegawaiController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[CutiPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        if(listCuti != null){
            for(Object[] obj : listCuti){
                CutiPegawai pegawai = new CutiPegawai();
                pegawai.setNip(obj[0].toString());
                pegawai.setUserName(obj[1].toString());
                pegawai.setDepartementName(obj[2].toString());
                pegawai.setCutiName(obj[3].toString());
                pegawai.setPositionName(obj[4].toString());
                pegawai.setTanggalDari((Date) obj[5]);
                pegawai.setTanggalDariSt(obj[5].toString());
                pegawai.setTanggalSelesai((Date) obj[6]);
                pegawai.setTanggalSelesaiSt(obj[6].toString());
                pegawai.setLamaHariCuti((BigInteger) obj[7]);
                pegawai.setIdPenggantiSementara(obj[8] == "" ? " - " : obj[8].toString());
                pegawai.setUnit(obj[9].toString());
                pegawai.setCutiPegawaiId(obj[10].toString());
                pegawai.setSisaCuti(obj[11].toString());
                pegawai.setKeteranganCuti(obj[12].toString());

                listOfCuti.add(pegawai);
            }
        }

        logger.info("[CutiPegawaiController.create] end process POST /activity <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }

    public String update(){
        logger.info("[CutiPegawaiController.update] end process POST /activity <<<");

        User user = userBoProxy.getUserById(id, "Y");

        try {

            com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai editCutiPegawai = new com.neurix.hris.transaksi.cutiPegawai.model.CutiPegawai();

            if (statusApprove.equals("Y")) {
                editCutiPegawai.setApprovalFlag(statusApprove);
            } else {
                editCutiPegawai.setApprovalFlag("N");
                editCutiPegawai.setNoteApproval(catatan);
            }

            editCutiPegawai.setNip(user.getUserId());
            editCutiPegawai.setUserIdActive(user.getUserId());
            editCutiPegawai.setUserNameActive(user.getUsername());
            editCutiPegawai.setCutiPegawaiId(idCutiPegawai);
            editCutiPegawai.setTmpApprove("atasan");
            String userLogin = user.getUsername();
            editCutiPegawai.setNipUserLogin(nipUserLogin);
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editCutiPegawai.setLastUpdateWho(userLogin);
            editCutiPegawai.setLastUpdate(updateTime);
            editCutiPegawai.setAction("U");
            editCutiPegawai.setFlag("Y");
            editCutiPegawai.setPegawaiPenggantiSementara(nipPengganti);
            editCutiPegawai.setForMobile(true);

           List<Notifikasi> notifikasiList = cutiPegawaiBoProxy.saveApprove(editCutiPegawai);

           for (Notifikasi notifikasi : notifikasiList){
               notifikasiBoProxy.sendNotif(notifikasi);
           }
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiPegawaiController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[CutiPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
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
                logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "trainingBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiController.create] Error when saving error,", e1);
            }
            logger.error("[CutiPegawaiController.create] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
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
                            logger.error("[CutiPegawaiController.create] Error when saving error,", e1);
                        }
                        logger.error("[CutiPegawaiController.create] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
                    }
                }
            }
        }
        logger.info("[CutiPegawaiController.create] end process POST /activity <<<");
        return "success";
    }

    public String confirm() {
        logger.info("[CutiPegawaiController.confirm] start process POST /ijinkeluar/{id}/confirm >>>");

        List<Object[]> listCuti = null;
        try {
            listCuti = cutiPegawaiBoProxy.findAllConfirmByIdAtasan(id, confirm);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = cutiPegawaiBoProxy.saveErrorMessage(e.getMessage(), "CutiPegawaiController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[CutiPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[CutiPegawaiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        if(listCuti != null){
            for(Object[] obj : listCuti){
                CutiPegawai pegawai = new CutiPegawai();
                pegawai.setCutiPegawaiId(obj[0].toString());
                pegawai.setNip(obj[1].toString());
                pegawai.setUserName(obj[2].toString());
                pegawai.setTanggalDari((Date) obj[3]);
                pegawai.setTanggalDariSt(obj[3].toString());
                pegawai.setTanggalSelesai((Date) obj[4]);
                pegawai.setTanggalSelesaiSt(obj[4].toString());
                pegawai.setUnit(obj[5].toString());
                pegawai.setKeteranganReject(obj[7].toString());

                listOfCuti.add(pegawai);
            }
        }
        logger.info("[CutiPegawaiController.confirm] end process POST /ijinkeluar/{id}/confirm <<<");
        return "success";
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getIdCutiPegawai() {
        return idCutiPegawai;
    }

    public void setIdCutiPegawai(String idCutiPegawai) {
        this.idCutiPegawai = idCutiPegawai;
    }

    public String getStatusApprove() {
        return statusApprove;
    }

    public void setStatusApprove(String statusApprove) {
        this.statusApprove = statusApprove;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public Object getModel() {
        return (listOfCuti != null ? listOfCuti : model);
    }
}
