package com.neurix.simrs.transaksi;

import java.util.List;

/**
 * Created by reza on 07/02/20.
 */
public class CrudResponse {

    private String status;
    private String msg;

    private List list;

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
