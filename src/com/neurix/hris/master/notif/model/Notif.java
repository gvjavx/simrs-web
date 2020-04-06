package com.neurix.hris.master.notif.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class Notif extends BaseModel implements Serializable {
    private String notifId;
    private String notifName;
    private String url;
    private String typeNotif;
    private String jml;

    public String getJml() {
        return jml;
    }

    public void setJml(String jml) {
        this.jml = jml;
    }

    public String getTypeNotif() {
        return typeNotif;
    }

    public void setTypeNotif(String typeNotif) {
        this.typeNotif = typeNotif;
    }

    public String getNotifId() {
        return notifId;
    }

    public void setNotifId(String notifId) {
        this.notifId = notifId;
    }

    public String getNotifName() {
        return notifName;
    }

    public void setNotifName(String notifName) {
        this.notifName = notifName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
