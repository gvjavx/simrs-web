package com.neurix.simrs.transaksi.verifikatorpembayaran.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by reza on 10/06/20.
 */
public class ItSimrsPembayaranOnlineEntity {
    private String id;
    private String idItem;
    private String keterangan;
    private String idAntrianTelemedic;
    private String urlFotoBukti;
    private String approvedFlag;
    private String approvedWho;
    private String flag;
    private String action;
    private Timestamp createdDate;
    private String createdWho;
    private Timestamp lastUpdate;
    private String lastUpdateWho;
    private BigDecimal nominal;
    private String idRiwayatTindakan;
    private String kodeBank;

    public String getKodeBank() {
        return kodeBank;
    }

    public void setKodeBank(String kodeBank) {
        this.kodeBank = kodeBank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getIdAntrianTelemedic() {
        return idAntrianTelemedic;
    }

    public void setIdAntrianTelemedic(String idAntrianTelemedic) {
        this.idAntrianTelemedic = idAntrianTelemedic;
    }

    public String getUrlFotoBukti() {
        return urlFotoBukti;
    }

    public void setUrlFotoBukti(String urlFotoBukti) {
        this.urlFotoBukti = urlFotoBukti;
    }

    public String getApprovedFlag() {
        return approvedFlag;
    }

    public void setApprovedFlag(String approvedFlag) {
        this.approvedFlag = approvedFlag;
    }

    public String getApprovedWho() {
        return approvedWho;
    }

    public void setApprovedWho(String approvedWho) {
        this.approvedWho = approvedWho;
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

    public BigDecimal getNominal() {
        return nominal;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
    }

    public String getIdRiwayatTindakan() {
        return idRiwayatTindakan;
    }

    public void setIdRiwayatTindakan(String idRiwayatTindakan) {
        this.idRiwayatTindakan = idRiwayatTindakan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItSimrsPembayaranOnlineEntity that = (ItSimrsPembayaranOnlineEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idItem != null ? !idItem.equals(that.idItem) : that.idItem != null) return false;
        if (keterangan != null ? !keterangan.equals(that.keterangan) : that.keterangan != null) return false;
        if (idAntrianTelemedic != null ? !idAntrianTelemedic.equals(that.idAntrianTelemedic) : that.idAntrianTelemedic != null)
            return false;
        if (urlFotoBukti != null ? !urlFotoBukti.equals(that.urlFotoBukti) : that.urlFotoBukti != null) return false;
        if (approvedFlag != null ? !approvedFlag.equals(that.approvedFlag) : that.approvedFlag != null) return false;
        if (approvedWho != null ? !approvedWho.equals(that.approvedWho) : that.approvedWho != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdWho != null ? !createdWho.equals(that.createdWho) : that.createdWho != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;
        if (lastUpdateWho != null ? !lastUpdateWho.equals(that.lastUpdateWho) : that.lastUpdateWho != null)
            return false;
        if (nominal != null ? !nominal.equals(that.nominal) : that.nominal != null) return false;
        if (idRiwayatTindakan != null ? !idRiwayatTindakan.equals(that.idRiwayatTindakan) : that.idRiwayatTindakan != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idItem != null ? idItem.hashCode() : 0);
        result = 31 * result + (keterangan != null ? keterangan.hashCode() : 0);
        result = 31 * result + (idAntrianTelemedic != null ? idAntrianTelemedic.hashCode() : 0);
        result = 31 * result + (urlFotoBukti != null ? urlFotoBukti.hashCode() : 0);
        result = 31 * result + (approvedFlag != null ? approvedFlag.hashCode() : 0);
        result = 31 * result + (approvedWho != null ? approvedWho.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdWho != null ? createdWho.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (lastUpdateWho != null ? lastUpdateWho.hashCode() : 0);
        result = 31 * result + (nominal != null ? nominal.hashCode() : 0);
        result = 31 * result + (idRiwayatTindakan != null ? idRiwayatTindakan.hashCode() : 0);
        return result;
    }
}
