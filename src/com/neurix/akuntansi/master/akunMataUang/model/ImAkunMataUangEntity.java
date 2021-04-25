package com.neurix.akuntansi.master.akunMataUang.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * Created by Aji Noor on 05/02/2021
 */
public class ImAkunMataUangEntity implements Serializable {
    private String mataUangId;
    private String kodeMataUang;
    private String namaMataUang;
    private String flag;
    private String userName;
    private String workStation;
    private String cabangId;
    private Date lastUpdate;

    public String getMataUangId() {
        return mataUangId;
    }

    public void setMataUangId(String mataUangId) {
        this.mataUangId = mataUangId;
    }

    public String getKodeMataUang() {
        return kodeMataUang;
    }

    public void setKodeMataUang(String kodeMataUang) {
        this.kodeMataUang = kodeMataUang;
    }

    public String getNamaMataUang() {
        return namaMataUang;
    }

    public void setNamaMataUang(String namaMataUang) {
        this.namaMataUang = namaMataUang;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkStation() {
        return workStation;
    }

    public void setWorkStation(String workStation) {
        this.workStation = workStation;
    }

    public String getCabangId() {
        return cabangId;
    }

    public void setCabangId(String cabangId) {
        this.cabangId = cabangId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImAkunMataUangEntity that = (ImAkunMataUangEntity) o;
        return Objects.equals(mataUangId, that.mataUangId) &&
                Objects.equals(kodeMataUang, that.kodeMataUang) &&
                Objects.equals(namaMataUang, that.namaMataUang) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(workStation, that.workStation) &&
                Objects.equals(cabangId, that.cabangId) &&
                Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mataUangId, kodeMataUang, namaMataUang, flag, userName, workStation, cabangId, lastUpdate);
    }
}
