package com.neurix.hris.transaksi.training.model;

import com.neurix.common.model.BaseModel;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class Training extends BaseModel implements Serializable {
    private String trainingId;
    private String trainingName;
    private String tipeTraining;
    private Date trainingStartDate;
    private Date trainingEndDate;
    private String instansi;
    private String closed;
    private String closedNote;
    private String unitId;
    private String iconApprove;
    private String kota;

    private boolean approve = false;
    private boolean print = false;
    private boolean edit = false;
    private boolean close = false;
    private boolean finish = false;

    private String stTanggalStart;
    private String stTanggalEnd;

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public String getIconApprove() {
        return iconApprove;
    }

    public void setIconApprove(String iconApprove) {
        this.iconApprove = iconApprove;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getTipeTraining() {
        return tipeTraining;
    }

    public void setTipeTraining(String tipeTraining) {
        this.tipeTraining = tipeTraining;
    }

    public Date getTrainingStartDate() {
        return trainingStartDate;
    }

    public void setTrainingStartDate(Date trainingStartDate) {
        this.trainingStartDate = trainingStartDate;
    }

    public Date getTrainingEndDate() {
        return trainingEndDate;
    }

    public void setTrainingEndDate(Date trainingEndDate) {
        this.trainingEndDate = trainingEndDate;
    }

    public String getInstansi() {
        return instansi;
    }

    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getClosedNote() {
        return closedNote;
    }

    public void setClosedNote(String closedNote) {
        this.closedNote = closedNote;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public boolean isPrint() {
        return print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public String getStTanggalStart() {
        return stTanggalStart;
    }

    public void setStTanggalStart(String stTanggalStart) {
        this.stTanggalStart = stTanggalStart;
    }

    public String getStTanggalEnd() {
        return stTanggalEnd;
    }

    public void setStTanggalEnd(String stTanggalEnd) {
        this.stTanggalEnd = stTanggalEnd;
    }
}
