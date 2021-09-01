package com.neurix.simrs.transaksi;

import java.util.List;

/**
 * Created by reza on 07/02/20.
 */
public class CrudResponse {

    private String status;
    private String msg;

    private List list;
    //RAKA-01SEP2021
    private String data;

    public void addResponse(String status, String msg){
        this.setStatus(status);
        this.setMsg(msg);
    }

    public void hasError(String msg){
        this.setStatus("error");
        this.setMsg(msg);
    }

    public void hasSuccess(String msg){
        this.setStatus("success");
        this.setMsg(msg);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

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
}
