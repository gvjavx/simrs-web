package com.neurix.hris.transaksi.payroll.model;

import com.neurix.common.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */

public class PayrollMinimumPromosi extends BaseModel {
    private String promosiId;
    private int masaKerjaMin;
    private int masaKerjaMaks;
    private int minimalPoin;

    public int getMasaKerjaMaks() {
        return masaKerjaMaks;
    }

    public void setMasaKerjaMaks(int masaKerjaMaks) {
        this.masaKerjaMaks = masaKerjaMaks;
    }

    public int getMasaKerjaMin() {
        return masaKerjaMin;
    }

    public void setMasaKerjaMin(int masaKerjaMin) {
        this.masaKerjaMin = masaKerjaMin;
    }

    public int getMinimalPoin() {
        return minimalPoin;
    }

    public void setMinimalPoin(int minimalPoin) {
        this.minimalPoin = minimalPoin;
    }

    public String getPromosiId() {
        return promosiId;
    }

    public void setPromosiId(String promosiId) {
        this.promosiId = promosiId;
    }
}