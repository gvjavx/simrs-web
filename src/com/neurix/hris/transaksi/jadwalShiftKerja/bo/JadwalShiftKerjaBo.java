package com.neurix.hris.transaksi.jadwalShiftKerja.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.groupShift.model.GroupShift;
import com.neurix.hris.transaksi.jadwalShiftKerja.model.*;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.simrs.transaksi.CrudResponse;

import java.sql.Date;
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

    List<GroupShift> deleteJadwalShiftKerja(String id) throws GeneralBOException;

    String saveTanggalOtomatis(JadwalShiftKerja searchBean) throws GeneralBOException;

    List<JadwalShiftKerja> getJadwalforReport(JadwalShiftKerja bean) throws GeneralBOException;

    List<JadwalShiftKerjaDetail> getPegawaiByGrup(String kelompokPositionId, String unit) throws GeneralBOException;

    List<JadwalPelayananDTO> getJadwalPelayanan(String idPelayanan, String kelompokId, String branchId, String nip, Date tanggal) throws GeneralBOException;

    List<JadwalShiftKerja> getJadwalShiftKerjaByUnitAndTanggal(String branchId, Date tanggal) throws GeneralBOException;

    List<JadwalShiftKerjaDetail> getJadwalShiftKerjaByUnitAndProfesiAndTanggal(String branchId, Date tglFrom, Date tglTo, String profesiId) throws GeneralBOException;

    CrudResponse getListLibur(String tanggalAwal, String tanggalAkhir) throws GeneralBOException;

    List<JadwalShiftKerjaDetail> getJadwalShiftByBulanTahun(String nip, String branchId, String profesiId, String tanggalAwal, String tanggalAkhir) throws GeneralBOException;

    List<Notifikasi> savePanggilBerdasarkanId(JadwalShiftKerjaDetail bean);

    void saveLiburBerdasarkanId(JadwalShiftKerjaDetail bean);

    List<HistoryOnCall> getHistoryOnCall(HistoryOnCall search);
    public List<JadwalPelayananDTO> getListJadwalDokter(String branchId, String idPelayanan, String notLike) throws GeneralBOException;
}
