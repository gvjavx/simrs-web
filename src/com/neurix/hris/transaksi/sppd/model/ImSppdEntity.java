package com.neurix.hris.transaksi.sppd.model;

import com.neurix.hris.master.biodata.model.ImBiodataEntity;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.provinsi.model.ImKotaEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

public class ImSppdEntity implements Serializable {

    private String sppdId;
    private String personName;
    private String dinas;
    private String branchId;
    private String noSurat;
    private String tugasSppd;
    private String stTanggalSppdBerangkat;
    private String stTanggalSppdKembali;
    private Date tanggalSppdBerangkat;
    private Date tanggalSppdKembali;
    private Date tanggalTmp;

    private String closed;
    private String flagEdit;
    private String divisiId;
    private String pemberiTugas;
    private String positoinId;


    private String flag;
    private String action;
    private Timestamp createdDate;
    private Timestamp lastUpdate;
    private String createdWho;
    private String lastUpdateWho;

    private ImDepartmentEntity imDepartmentEntity ;
    private ImBiodataEntity imBiodataEntity;
    private ImKotaEntity imKotaEntity ;
    private ItSppdPersonEntity itSppdPersonEntity ;

    public String getPemberiTugas() {
        return pemberiTugas;
    }

    public void setPemberiTugas(String pemberiTugas) {
        this.pemberiTugas = pemberiTugas;
    }

    public ImBiodataEntity getImBiodataEntity() {
        return imBiodataEntity;
    }

    public String getFlagEdit() {
        return flagEdit;
    }

    public void setFlagEdit(String flagEdit) {
        this.flagEdit = flagEdit;
    }

    public void setImBiodataEntity(ImBiodataEntity imBiodataEntity) {
        this.imBiodataEntity = imBiodataEntity;
    }

    public ImKotaEntity getImKotaEntity() {
        return imKotaEntity;
    }

    public void setImKotaEntity(ImKotaEntity imKotaEntity) {
        this.imKotaEntity = imKotaEntity;
    }

    public ItSppdPersonEntity getItSppdPersonEntity() {
        return itSppdPersonEntity;
    }

    public void setItSppdPersonEntity(ItSppdPersonEntity itSppdPersonEntity) {
        this.itSppdPersonEntity = itSppdPersonEntity;
    }

    public ImDepartmentEntity getImDepartmentEntity() {
        return imDepartmentEntity;
    }

    public void setImDepartmentEntity(ImDepartmentEntity imDepartmentEntity) {
        this.imDepartmentEntity = imDepartmentEntity;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPositoinId() {
        return positoinId;
    }

    public void setPositoinId(String positoinId) {
        this.positoinId = positoinId;
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
