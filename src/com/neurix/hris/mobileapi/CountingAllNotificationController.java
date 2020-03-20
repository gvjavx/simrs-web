package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.mobileapi.model.CountingNotifikasi;
import com.neurix.hris.mobileapi.model.Notifikasi;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CountingAllNotificationController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(CountingAllNotificationController.class);
    private CountingNotifikasi model;
    private NotifikasiBo notifikasiBoProxy;
    private Collection<Notifikasi> listOfNotification;
    private String nip;

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

    public HttpHeaders create() {
        logger.info("[CountingAllNotificationController.create] end process POST /allnotification <<<");

        model = new CountingNotifikasi();

            try {
               List<Object[]> cutis = notifikasiBoProxy.findAllNotifByNip(nip, "TN66");
               model.setCountCuti(cutis.size());
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "CountingAllNotificationController.isFoundOtherSessionActiveUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[CountingAllNotificationController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                }
                logger.error("[CountingAllNotificationController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

            try {
                List<Object[]> dispensasis = notifikasiBoProxy.findAllNotifTypeNotif(nip, "TN55");
                model.setCountDispensasi(dispensasis.size());
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "CountingAllNotificationController.isFoundOtherSessionActiveUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[CountingAllNotificationController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                }
                logger.error("[CountingAllNotificationController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

            try {
                List<Object[]> sppds = notifikasiBoProxy.findAllNotifSppd(nip, "TI");
                model.setCountSppd(sppds.size());
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "CountingAllNotificationController.isFoundOtherSessionActiveUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[CountingAllNotificationController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                }
                logger.error("[CountingAllNotificationController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

            try {
                List<Object[]> trainings = notifikasiBoProxy.findAllNotifTraining(nip, "TN23");
                model.setCountTraining(trainings.size());
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "CountingAllNotificationController.isFoundOtherSessionActiveUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[CountingAllNotificationController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                }
                logger.error("[CountingAllNotificationController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

            try {
                List<Object[]> lemburs = notifikasiBoProxy.findAllNotifLembur(nip, "TN77");
                model.setCountLembur(lemburs.size());
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = notifikasiBoProxy.saveErrorMessage(e.getMessage(), "CountingAllNotificationController.isFoundOtherSessionActiveUserSessionLog");
                } catch (GeneralBOException e1) {
                    logger.error("[CountingAllNotificationController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
                }
                logger.error("[CountingAllNotificationController.isFoundOthePrSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                throw new GeneralBOException(e);
            }

        logger.info("[CountingAllNotificationController.create] end process POST /allnotification <<<");
        return new DefaultHttpHeaders("index").disableCaching();
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
}
