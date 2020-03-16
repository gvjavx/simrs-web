package com.neurix.hris.master.tipelibur.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;

/**
 * Created by thinkpad on 20/03/2018.
 */
public class TipeLibur extends BaseModel implements Serializable {
    private String tipeLiburId;
    private String tipeLiburName;

    public String getTipeLiburId() {
        return tipeLiburId;
    }

    public void setTipeLiburId(String tipeLiburId) {
        this.tipeLiburId = tipeLiburId;
    }

    public String getTipeLiburName() {
        return tipeLiburName;
    }

    public void setTipeLiburName(String tipeLiburName) {
        this.tipeLiburName = tipeLiburName;
    }
}
