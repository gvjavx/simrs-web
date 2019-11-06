package com.neurix.hris.transaksi.jadwalShiftKerja.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.groupShift.model.GroupShift;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.JadwalKerjaDTO;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.JadwalShiftKerja;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.JadwalShiftKerjaDetail;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface JadwalShiftKerjaBo extends BaseMasterBo<JadwalShiftKerja> {
    List<JadwalShiftKerjaDetail> getByCriteriaDetail(JadwalShiftKerjaDetail searchBean) throws GeneralBOException;

    JadwalShiftKerja saveEdit(JadwalShiftKerja bean, JadwalShiftKerjaDetail bean2) throws GeneralBOException;

    List<GroupShift> deleteJadwalShiftKerja(String id) throws GeneralBOException;

    String saveTanggalOtomatis(JadwalShiftKerja searchBean) throws GeneralBOException;

    List<JadwalShiftKerja> getJadwalforReport(JadwalShiftKerja bean) throws GeneralBOException;
}
