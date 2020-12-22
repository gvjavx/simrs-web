package com.neurix.simrs.mobileapi;

import com.lowagie.text.pdf.BarcodeEAN;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.simrs.mobileapi.model.TestNotif;
import com.opensymphony.xwork2.ModelDriven;
import groovy.json.JsonException;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * @author gondok
 * Saturday, 13/06/20 10:17
 */
public class TestNotifController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(TestNotifController.class);
    private TestNotif model = new TestNotif();
    private Collection<TestNotif> listOfNotif;
    private NotifikasiBo notifikasiBoProxy;

    public static Logger getLogger() {
        return logger;
    }

    public void setModel(TestNotif model) {
        this.model = model;
    }

    public Collection<TestNotif> getListOfNotif() {
        return listOfNotif;
    }

    public void setListOfNotif(Collection<TestNotif> listOfNotif) {
        this.listOfNotif = listOfNotif;
    }

    public NotifikasiBo getNotifikasiBoProxy() {
        return notifikasiBoProxy;
    }

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

    @Override
    public Object getModel() {
        return listOfNotif != null ? listOfNotif : model;
    }

    public HttpHeaders create() throws JSONException {
        logger.info("[TestNotifController.create] start process POST / <<<");

        Timestamp now = new Timestamp(System.currentTimeMillis());

        JSONObject data = new JSONObject();
        data.put("namaDokter", "Adi Winarno");

        com.neurix.hris.transaksi.notifikasi.model.Notifikasi bean = new Notifikasi();
        bean.setTipeNotifId("TN10");
        bean.setTipeNotifName("Telemedic");
        bean.setOs("Android");
        bean.setNip("RS0104200035");
        bean.setNamaPegawai("admin");
        bean.setChannelId("android-notif");
        bean.setNote("Test Notif");
        bean.setTo("RS0104200035");
        bean.setFromPerson("admin");
        bean.setNoRequest("TMC");
        bean.setFlag("Y");
        bean.setRead("N");
        bean.setAction("C");
        bean.setCreatedDate(now);
        bean.setLastUpdate(now);
        bean.setCreatedWho("admin");
        bean.setLastUpdateWho("admin");

        try {
            notifikasiBoProxy.saveAdd(bean);
            model.setMessage("Success");
        } catch (GeneralBOException e){
            model.setMessage(("Failed"));
            e.printStackTrace();
        }

//        boolean isSuccess = FirebasePushNotif.sendNotificationFirebase(model.getTokenId(), model.getTitle(), model.getBody(), model.getClick_action(), model.getOs(), data);

        logger.info("[TestNotifController.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }
}
