package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.mobileapi.model.Notifikasi;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class NotifikasiController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(NotifikasiController.class);
    private Notifikasi model = new Notifikasi();
    private NotifikasiBo notifikasiBoProxy;
    private Collection<Notifikasi> listOfNotification = new ArrayList<>();
    private String nip;
    private String typeNotif;

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

    public HttpHeaders create() {
        logger.info("[NotifikasiController.create] end process POST /activity <<<");

        List<Object[]> listObjectNotif = null;
        if(typeNotif.equals("TN66")){
            try {
                listObjectNotif = notifikasiBoProxy.findAllNotifByNip(nip, typeNotif);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "NotifikasiController.isFoundOtherSessionActiveUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                }
                logger.error("[NotifikasiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }
        } else if(typeNotif.equals("TN55")){
            try {
                listObjectNotif = notifikasiBoProxy.findAllNotifTypeNotif(nip, typeNotif);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "NotifikasiController.isFoundOtherSessionActiveUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                }
                logger.error("[NotifikasiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }
        } else if(typeNotif.equals("TI")){
            try {
                listObjectNotif = notifikasiBoProxy.findAllNotifSppd(nip, typeNotif);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "NotifikasiController.isFoundOtherSessionActiveUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                }
                logger.error("[NotifikasiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }
        } else if(typeNotif.equals("TN23")) {
            try {
                listObjectNotif = notifikasiBoProxy.findAllNotifTraining(nip, typeNotif);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "NotifikasiController.isFoundOtherSessionActiveUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                }
                logger.error("[NotifikasiController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }
        } else if (typeNotif.equals("TN77")) {
            try {
                listObjectNotif = notifikasiBoProxy.findAllNotifLembur(nip, typeNotif);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "NotifikasiController.isFoundOtherSessionActiveUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                }
                logger.error("[NotifikasiController.isFoundOthePrSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }
        } else if (typeNotif.equals(("umum")) || typeNotif.equalsIgnoreCase("TN10") || typeNotif.equalsIgnoreCase("TN11") || typeNotif.equalsIgnoreCase("TN12")) {

            try {
                listObjectNotif = notifikasiBoProxy.findAllNotifTypeNotif(nip, typeNotif);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "NotifikasiController.isFoundOtherSessionActiveUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[NotifikasiController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                }
                logger.error("[NotifikasiController.isFoundOthePrSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }
        }

        if (listObjectNotif != null) {
            for (Object[] obj : listObjectNotif) {
                Notifikasi pegawai = new Notifikasi();
                if(obj.length >= 8){
                    pegawai.setDepartement(obj[7].toString());
                    pegawai.setPosition(obj[8].toString());
                    pegawai.setJamAwal(obj[7].toString());
                    pegawai.setJamSelesai(obj[8].toString());
                }
                pegawai.setCutiPegawaiId(obj[0].toString());
                pegawai.setNip(obj[1].toString());
                pegawai.setNamaPegawai(obj[2].toString());
                if (obj[3] != null)
                    pegawai.setTanggalDariSt(obj[3].toString());
                else
                    pegawai.setTanggalDariSt("-");
                if (obj[4] != null)
                    pegawai.setTanggalSelesaiSt(obj[4].toString());
                else
                    pegawai.setTanggalSelesaiSt("-");
                pegawai.setNotifId(obj[6].toString());

                listOfNotification.add(pegawai);
            }
        }

        logger.info("[NotifikasiController.create] end process POST /activity <<<");
        return new DefaultHttpHeaders("index").disableCaching();
    }

    public HttpHeaders update() {

        return new DefaultHttpHeaders("update").disableCaching();
    }

    @Override
    public Object getModel() {
        return (listOfNotification != null ? listOfNotification : model);
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getTypeNotif() {
        return typeNotif;
    }

    public void setTypeNotif(String typeNotif) {
        this.typeNotif = typeNotif;
    }
}
