package com.neurix.simrs.transaksi.resikojatuh.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by reza on 06/02/20.
 */
public class ResikoJatuhResponse {
    private String status;
    private String msg;

    private List<ItSImrsResikoJatuhEntity> resikoJatuhEntityList = new ArrayList<>();

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

    public List<ItSImrsResikoJatuhEntity> getResikoJatuhEntityList() {
        return resikoJatuhEntityList;
    }

    public void setResikoJatuhEntityList(List<ItSImrsResikoJatuhEntity> resikoJatuhEntityList) {
        this.resikoJatuhEntityList = resikoJatuhEntityList;
    }
}
