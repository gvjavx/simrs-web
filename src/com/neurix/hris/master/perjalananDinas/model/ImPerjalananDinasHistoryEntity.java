package com.neurix.hris.master.perjalananDinas.model;

import com.neurix.authorization.position.model.ImPosition;
import com.neurix.hris.master.biayaPerjalananDinas.model.ImBiayaPerjalananDinasEntity;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class ImPerjalananDinasHistoryEntity implements Serializable {

    private String id;
    private String perjalananDinasId;
    private String kelompokId;
    private String positionName;
    private String biayaDinasId;
    private String biayaDinasName;
    private BigDecimal jumlahBiaya;
    private String dinas;
    private String golonganId;
    private String golonganName;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImPosition imPosition;
    private ImGolonganEntity imGolonganEntity;
    private ImBiayaPerjalananDinasEntity imBiayaPerjalananDinasEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ImPosition getImPosition() {
        return imPosition;
    }

    public void setImPosition(ImPosition imPosition) {
        this.imPosition = imPosition;
    }

    public ImGolonganEntity getImGolonganEntity() {
        return imGolonganEntity;
    }

    public void setImGolonganEntity(ImGolonganEntity imGolonganEntity) {
        this.imGolonganEntity = imGolonganEntity;
    }

    public ImBiayaPerjalananDinasEntity getImBiayaPerjalananDinasEntity() {
        return imBiayaPerjalananDinasEntity;
    }

    public void setImBiayaPerjalananDinasEntity(ImBiayaPerjalananDinasEntity imBiayaPerjalananDinasEntity) {
        this.imBiayaPerjalananDinasEntity = imBiayaPerjalananDinasEntity;
    }

    public String getPerjalananDinasId() {
        return perjalananDinasId;
    }

    public void setPerjalananDinasId(String perjalananDinasId) {
        this.perjalananDinasId = perjalananDinasId;
    }

    public String getKelompokId() {
        return kelompokId;
    }

    public void setKelompokId(String kelompokId) {
        this.kelompokId = kelompokId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getBiayaDinasId() {
        return biayaDinasId;
    }

    public void setBiayaDinasId(String biayaDinasId) {
        this.biayaDinasId = biayaDinasId;
    }

    public String getBiayaDinasName() {
        return biayaDinasName;
    }

    public void setBiayaDinasName(String biayaDinasName) {
        this.biayaDinasName = biayaDinasName;
    }

    public BigDecimal getJumlahBiaya() {
        return jumlahBiaya;
    }

    public void setJumlahBiaya(BigDecimal jumlahBiaya) {
        this.jumlahBiaya = jumlahBiaya;
    }

    public String getDinas() {
        return dinas;
    }

    public void setDinas(String dinas) {
        this.dinas = dinas;
    }

    public String getGolonganId() {
        return golonganId;
    }

    public void setGolonganId(String golonganId) {
        this.golonganId = golonganId;
    }

    public String getGolonganName() {
        return golonganName;
    }

    public void setGolonganName(String golonganName) {
        this.golonganName = golonganName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }
}
