package com.neurix.simrs.transaksi.antriantelemedic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by reza on 08/06/20.
 */
public class StatusAntrianTelemedic {
    private String status;
    private Integer index;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<StatusAntrianTelemedic> getStatusAntrianOrder(){

        List<StatusAntrianTelemedic> antrianTelemedics = new ArrayList<>();

        StatusAntrianTelemedic statusAntrian = new StatusAntrianTelemedic();

        statusAntrian.setStatus("SL");
        statusAntrian.setIndex(1);
        antrianTelemedics.add(statusAntrian);

        statusAntrian = new StatusAntrianTelemedic();
        statusAntrian.setStatus("WL");;
        statusAntrian.setIndex(2);
        antrianTelemedics.add(statusAntrian);

        statusAntrian = new StatusAntrianTelemedic();
        statusAntrian.setStatus("LL");;
        statusAntrian.setIndex(3);
        antrianTelemedics.add(statusAntrian);

        return antrianTelemedics;
    }


}
