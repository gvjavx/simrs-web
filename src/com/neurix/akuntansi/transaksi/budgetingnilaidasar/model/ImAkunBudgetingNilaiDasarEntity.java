package com.neurix.akuntansi.transaksi.budgetingnilaidasar.model;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 07/08/20.
 */
public class ImAkunBudgetingNilaiDasarEntity {
    private String id;
    private String namaNilaiDasar;
    private BigInteger nilaiDasar;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private Integer urutan;
    private String tipe;

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public Integer getUrutan() {
        return urutan;
    }

    public void setUrutan(Integer urutan) {
        this.urutan = urutan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaNilaiDasar() {
        return namaNilaiDasar;
    }

    public void setNamaNilaiDasar(String namaNilaiDasar) {
        this.namaNilaiDasar = namaNilaiDasar;
    }

    public BigInteger getNilaiDasar() {
        return nilaiDasar;
    }

    public void setNilaiDasar(BigInteger nilaiDasar) {
        this.nilaiDasar = nilaiDasar;
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

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateWho() {
        return lastUpdateWho;
    }

    public void setLastUpdateWho(String lastUpdateWho) {
        this.lastUpdateWho = lastUpdateWho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImAkunBudgetingNilaiDasarEntity that = (ImAkunBudgetingNilaiDasarEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (namaNilaiDasar != null ? !namaNilaiDasar.equals(that.namaNilaiDasar) : that.namaNilaiDasar != null)
            return false;
        if (nilaiDasar != null ? !nilaiDasar.equals(that.nilaiDasar) : that.nilaiDasar != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (namaNilaiDasar != null ? namaNilaiDasar.hashCode() : 0);
        result = 31 * result + (nilaiDasar != null ? nilaiDasar.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        return result;
    }
}
