package com.neurix.hris.transaksi.sppd.model;

import com.neurix.common.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class Sppd extends BaseModel {
    private String sppdId;
    private String personName;
    private String personNip;
    private String berangkatVia;
    private String pulangVia;
    private String dinas;
    private String branchId;
    private String noSurat;
    private String tugasSppd;
    private String pemeberiTugas;
    private String stTanggalSppdBerangkat;
    private String stTanggalSppdKembali;
    private Date tanggalSppdBerangkat;
    private Date tanggalSppdKembali;
    private Date tanggalTmp;

    private String closed;
    private String flagEdit;
    private String bagianId;
    private String bagianName;
    private String divisiId;
    private String divisiName;
    private String positionId;
    private String berangkatDari;
    private String berangkatDariId;
    private String tujuanKe;
    private String tujuanKeId;
    private String pemberiTugas;

    private String flagTraining;
    private String idTraining;

    private String tmpApprove;

    private boolean sppdClosed = false;
    private boolean sppdApprove = false;
    private boolean sppdApproveStatus = false;
    private boolean sppdApproveSdm = false;
    private boolean sppdApproveSdmStatus = false;
    private boolean sppdEditStatus = false;
    private boolean sppdEditStatusAtasan = false;

    public String getBagianId() {
        return bagianId;
    }

    public void setBagianId(String bagianId) {
        this.bagianId = bagianId;
    }

    public String getBagianName() {
        return bagianName;
    }

    public void setBagianName(String bagianName) {
        this.bagianName = bagianName;
    }

    public boolean isSppdEditStatusAtasan() {
        return sppdEditStatusAtasan;
    }

    public void setSppdEditStatusAtasan(boolean sppdEditStatusAtasan) {
        this.sppdEditStatusAtasan = sppdEditStatusAtasan;
    }

    public String getBerangkatDariId() {
        return berangkatDariId;
    }

    public void setBerangkatDariId(String berangkatDariId) {
        this.berangkatDariId = berangkatDariId;
    }

    public String getTujuanKeId() {
        return tujuanKeId;
    }

    public void setTujuanKeId(String tujuanKeId) {
        this.tujuanKeId = tujuanKeId;
    }

    public String getFlagTraining() {
        return flagTraining;
    }

    public void setFlagTraining(String flagTraining) {
        this.flagTraining = flagTraining;
    }

    public String getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(String idTraining) {
        this.idTraining = idTraining;
    }

    public String getPemberiTugas() {
        return pemberiTugas;
    }

    public void setPemberiTugas(String pemberiTugas) {
        this.pemberiTugas = pemberiTugas;
    }

    public boolean isSppdEditStatus() {
        return sppdEditStatus;
    }

    public void setSppdEditStatus(boolean sppdEditStatus) {
        this.sppdEditStatus = sppdEditStatus;
    }

    public String getFlagEdit() {
        return flagEdit;
    }

    public void setFlagEdit(String flagEdit) {
        this.flagEdit = flagEdit;
    }

    public boolean isSppdApproveSdmStatus() {
        return sppdApproveSdmStatus;
    }

    public void setSppdApproveSdmStatus(boolean sppdApproveSdmStatus) {
        this.sppdApproveSdmStatus = sppdApproveSdmStatus;
    }

    public String getTmpApprove() {
        return tmpApprove;
    }

    public void setTmpApprove(String tmpApprove) {
        this.tmpApprove = tmpApprove;
    }

    public boolean isSppdApproveStatus() {
        return sppdApproveStatus;
    }

    public void setSppdApproveStatus(boolean sppdApproveStatus) {
        this.sppdApproveStatus = sppdApproveStatus;
    }

    public boolean isSppdApprove() {
        return sppdApprove;
    }

    public void setSppdApprove(boolean sppdApprove) {
        this.sppdApprove = sppdApprove;
    }

    public boolean isSppdApproveSdm() {
        return sppdApproveSdm;
    }

    public void setSppdApproveSdm(boolean sppdApproveSdm) {
        this.sppdApproveSdm = sppdApproveSdm;
    }

    public boolean isSppdClosed() {
        return sppdClosed;
    }

    public void setSppdClosed(boolean sppdClosed) {
        this.sppdClosed = sppdClosed;
    }

    public String getBerangkatDari() {
        return berangkatDari;
    }

    public void setBerangkatDari(String berangkatDari) {
        this.berangkatDari = berangkatDari;
    }

    public String getTujuanKe() {
        return tujuanKe;
    }

    public void setTujuanKe(String tujuanKe) {
        this.tujuanKe = tujuanKe;
    }

    public String getBerangkatVia() {
        return berangkatVia;
    }

    public void setBerangkatVia(String berangkatVia) {
        this.berangkatVia = berangkatVia;
    }

    public String getPulangVia() {
        return pulangVia;
    }

    public void setPulangVia(String pulangVia) {
        this.pulangVia = pulangVia;
    }

    public String getDivisiName() {
        return divisiName;
    }

    public void setDivisiName(String divisiName) {
        this.divisiName = divisiName;
    }

    public String getPersonNip() {
        return personNip;
    }

    public void setPersonNip(String personNip) {
        this.personNip = personNip;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getSppdId() {
        return sppdId;
    }

    public void setSppdId(String sppdId) {
        this.sppdId = sppdId;
    }

    public String getDinas() {
        return dinas;
    }

    public void setDinas(String dinas) {
        this.dinas = dinas;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getNoSurat() {
        return noSurat;
    }

    public void setNoSurat(String noSurat) {
        this.noSurat = noSurat;
    }

    public String getTugasSppd() {
        return tugasSppd;
    }

    public void setTugasSppd(String tugasSppd) {
        this.tugasSppd = tugasSppd;
    }

    public String getPemeberiTugas() {
        return pemeberiTugas;
    }

    public void setPemeberiTugas(String pemeberiTugas) {
        this.pemeberiTugas = pemeberiTugas;
    }

    public String getStTanggalSppdBerangkat() {
        return stTanggalSppdBerangkat;
    }

    public void setStTanggalSppdBerangkat(String stTanggalSppdBerangkat) {
        this.stTanggalSppdBerangkat = stTanggalSppdBerangkat;
    }

    public String getStTanggalSppdKembali() {
        return stTanggalSppdKembali;
    }

    public void setStTanggalSppdKembali(String stTanggalSppdKembali) {
        this.stTanggalSppdKembali = stTanggalSppdKembali;
    }

    public Date getTanggalSppdBerangkat() {
        return tanggalSppdBerangkat;
    }

    public void setTanggalSppdBerangkat(Date tanggalSppdBerangkat) {
        this.tanggalSppdBerangkat = tanggalSppdBerangkat;
    }

    public Date getTanggalSppdKembali() {
        return tanggalSppdKembali;
    }

    public void setTanggalSppdKembali(Date tanggalSppdKembali) {
        this.tanggalSppdKembali = tanggalSppdKembali;
    }

    public Date getTanggalTmp() {
        return tanggalTmp;
    }

    public void setTanggalTmp(Date tanggalTmp) {
        this.tanggalTmp = tanggalTmp;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getDivisiId() {
        return divisiId;
    }

    public void setDivisiId(String divisiId) {
        this.divisiId = divisiId;
    }
}