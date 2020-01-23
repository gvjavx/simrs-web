package com.neurix.hris.mobileapi;

import com.neurix.authorization.user.bo.UserBo;
import com.neurix.authorization.user.model.User;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.mobileapi.model.IjinKeluar;
import com.neurix.hris.transaksi.ijinKeluar.bo.IjinKeluarBo;
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

public class IjinKeluarController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(IjinKeluarController.class);

    private IjinKeluarBo ijinKeluarBoProxy;
    private UserBo userBoProxy;
    private NotifikasiBo notifikasiBoProxy;

    private IjinKeluar model = new IjinKeluar();
    private List<IjinKeluar> listOfIjinKeluar = new ArrayList<>();
    private String idIjinKeluar;
    private String nip;
    private String statusApprove;
    private String id;
    private String catatan;
    private String idNotif;
    private String confirm;

    public void setIjinKeluarBoProxy(IjinKeluarBo ijinKeluarBoProxy) {
        this.ijinKeluarBoProxy = ijinKeluarBoProxy;
    }

    public void setUserBoProxy(UserBo userBoProxy) {
        this.userBoProxy = userBoProxy;
    }

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

    public HttpHeaders create(){
        logger.info("[IjinKeluarController.create] end process POST /ijinkeluar <<<");

        List<Object[]> listCuti = null;
        try {
            listCuti = ijinKeluarBoProxy.findInfoIjin(idIjinKeluar, nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "CutiPegawaiController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[IjinKeluarController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[IjinKeluarController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        if(listCuti != null){
            for(Object[] obj : listCuti){
                IjinKeluar pegawai = new IjinKeluar();
                pegawai.setNip(obj[0].toString());
                pegawai.setUserName(obj[1].toString());
                pegawai.setDepartementName(obj[2].toString());
                pegawai.setIjinName(obj[3].toString());
                pegawai.setPositionName(obj[4].toString());
//                pegawai.setTanggalDari((Date) obj[5]);
                pegawai.setTanggalDariSt(obj[5].toString());
//                pegawai.setTanggalSelesai((Date) obj[6]);
                pegawai.setTanggalSelesaiSt(obj[6].toString());
                pegawai.setLamaIjin((BigInteger) obj[7]);
                pegawai.setUnit(obj[8].toString());
                pegawai.setIjinKeluarId(obj[9].toString());
                pegawai.setKeterangan(obj[10].toString());

                listOfIjinKeluar.add(pegawai);
            }
        }
        logger.info("[IjinKeluarController.create] end process POST /ijinkeluar <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }

    public String update(){
        logger.info("[IjinKeluarController.update] end process PUT /ijinkeluar <<<");

        User user = userBoProxy.getUserById(id, "Y");

        try {
            com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluar ijinKeluar = new com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluar();

            ijinKeluar.setIjinKeluarId(idIjinKeluar);

            if (statusApprove.equals("Y")) {
                ijinKeluar.setApprovalFlag(statusApprove);
            } else {
                ijinKeluar.setApprovalFlag("N");
                ijinKeluar.setNotApprovalNote(catatan);
            }


            ijinKeluar.setUserIdActive(user.getUserId());
            ijinKeluar.setUserNameActive(user.getUsername());

            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ijinKeluar.setTmpApprove("atasan");
            ijinKeluar.setNip(user.getUserId());
            ijinKeluar.setLastUpdateWho(user.getUsername());
            ijinKeluar.setLastUpdate(updateTime);
            ijinKeluar.setAction("U");
            ijinKeluar.setFlag("Y");

           List<Notifikasi> notifikasiList = ijinKeluarBoProxy.saveApprove(ijinKeluar);

            for (Notifikasi notifikasi : notifikasiList){
                notifikasiBoProxy.sendNotif(notifikasi);
            }
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "IjinKeluarController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[IjinKeluarController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[IjinKeluarController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
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
                logger.error("[IjinKeluarController.create] Error when saving error,", e1);
            }
            logger.error("[IjinKeluarController.create] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
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
                            logger.error("[IjinKeluarController.create] Error when saving error,", e1);
                        }
                        logger.error("[IjinKeluarController.create] Error when get combo," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
                    }
                }
            }
        }
        logger.info("[IjinKeluarController.create] end process PUT /ijinkeluar <<<");
        return "success";
    }

    public String confirm() {
        logger.info("[IjinKeluarController.confirm] start process POST /ijinkeluar/{id}/confirm >>>");

        List<Object[]> listIjinKeluar = null;
        try {
            listIjinKeluar = ijinKeluarBoProxy.findAllConfirmByIdAtasan(id, confirm);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinKeluarBoProxy.saveErrorMessage(e.getMessage(), "IjinKeluarController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[IjinKeluarController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[IjinKeluarController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        if(listIjinKeluar != null){
            for(Object[] obj : listIjinKeluar){
                IjinKeluar pegawai = new IjinKeluar();
                pegawai.setIjinKeluarId(obj[0].toString());
                pegawai.setNip(obj[1].toString());
                pegawai.setUserName(obj[2].toString());
//                pegawai.setTanggalDari((Date) obj[3]);
                pegawai.setTanggalDariSt(obj[3].toString());
//                pegawai.setTanggalSelesai((Date) obj[4]);
                pegawai.setTanggalSelesaiSt(obj[4].toString());
                pegawai.setUnit(obj[5].toString());
                pegawai.setKeterangan(obj[7].toString());

                listOfIjinKeluar.add(pegawai);
            }
        }

        logger.info("[IjinKeluarController.confirm] end process POST /ijinkeluar/{id}/confirm <<<");
        return "success";
    }

    @Override
    public Object getModel() {
        return (listOfIjinKeluar != null ? listOfIjinKeluar : model);
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

    public String getIdIjinKeluar() {
        return idIjinKeluar;
    }

    public void setIdIjinKeluar(String idIjinKeluar) {
        this.idIjinKeluar = idIjinKeluar;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(String idNotif) {
        this.idNotif = idNotif;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
