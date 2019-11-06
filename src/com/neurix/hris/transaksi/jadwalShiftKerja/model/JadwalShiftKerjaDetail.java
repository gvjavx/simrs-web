package com.neurix.hris.transaksi.jadwalShiftKerja.model;

import com.neurix.common.model.BaseModel;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class JadwalShiftKerjaDetail extends BaseModel {
    private String jadwalShiftKerjaDetailId;
    private String jadwalShiftKerjaId;
    private String shiftGroupId;

    public String getJadwalShiftKerjaDetailId() {
        return jadwalShiftKerjaDetailId;
    }

    public void setJadwalShiftKerjaDetailId(String jadwalShiftKerjaDetailId) {
        this.jadwalShiftKerjaDetailId = jadwalShiftKerjaDetailId;
    }

    public String getJadwalShiftKerjaId() {
        return jadwalShiftKerjaId;
    }

    public void setJadwalShiftKerjaId(String jadwalShiftKerjaId) {
        this.jadwalShiftKerjaId = jadwalShiftKerjaId;
    }

    public String getShiftGroupId() {
        return shiftGroupId;
    }

    public void setShiftGroupId(String shiftGroupId) {
        this.shiftGroupId = shiftGroupId;
    }
}
