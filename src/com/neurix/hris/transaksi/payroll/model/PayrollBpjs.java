package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class PayrollBpjs extends BaseModel {
    private String bpjsId;
    private double bpjsKesehatanPersen;
    private double bpjsPensiunPersen;
    private double bpjsJhtPersen;

    public String getBpjsId() {
        return bpjsId;
    }

    public void setBpjsId(String bpjsId) {
        this.bpjsId = bpjsId;
    }

    public double getBpjsKesehatanPersen() {
        return bpjsKesehatanPersen;
    }

    public void setBpjsKesehatanPersen(double bpjsKesehatanPersen) {
        this.bpjsKesehatanPersen = bpjsKesehatanPersen;
    }

    public double getBpjsPensiunPersen() {
        return bpjsPensiunPersen;
    }

    public void setBpjsPensiunPersen(double bpjsPensiunPersen) {
        this.bpjsPensiunPersen = bpjsPensiunPersen;
    }

    public double getBpjsJhtPersen() {
        return bpjsJhtPersen;
    }

    public void setBpjsJhtPersen(double bpjsJhtPersen) {
        this.bpjsJhtPersen = bpjsJhtPersen;
    }
}