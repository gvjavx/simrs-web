package com.neurix.simrs.transaksi.permintaanvendor.model;

/**
 * Created by Toshiba on 02/01/2020.
 */
public class CheckObatResponse {
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
