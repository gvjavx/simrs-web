package com.neurix.hris.transaksi.payroll.model;

import java.math.BigDecimal;

public class Ptt {
    String tipePttId;
    String tipePttName;
    String nilai;
    BigDecimal nilaiPtt;

    public String getTipePttId() {
        return tipePttId;
    }

    public void setTipePttId(String tipePttId) {
        this.tipePttId = tipePttId;
    }

    public String getTipePttName() {
        return tipePttName;
    }

    public void setTipePttName(String tipePttName) {
        this.tipePttName = tipePttName;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public BigDecimal getNilaiPtt() {
        return nilaiPtt;
    }

    public void setNilaiPtt(BigDecimal nilaiPtt) {
        this.nilaiPtt = nilaiPtt;
    }
}
