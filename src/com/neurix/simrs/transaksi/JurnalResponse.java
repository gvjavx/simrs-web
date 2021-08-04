package com.neurix.simrs.transaksi;

/**
 * Created by reza on 28/02/20.
 */
public class JurnalResponse {
    private String status;
    private String msg;
    private String noJurnal;
    private String invoice;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public void hasSuccess(String noJurnal, String invoice){
        this.setStatus("success");
        this.setMsg("Success Created Jurnal With No. Jurnal : "+noJurnal);
        this.setInvoice(invoice);
        this.setNoJurnal(noJurnal);
    }

    public void hasError(String errorMsg){
        this.setStatus("error");
        this.setMsg(errorMsg);
    }
}
