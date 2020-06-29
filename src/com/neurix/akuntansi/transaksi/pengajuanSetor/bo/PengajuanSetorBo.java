package com.neurix.akuntansi.transaksi.pengajuanSetor.bo;

import com.neurix.akuntansi.transaksi.pengajuanSetor.model.PengajuanSetor;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.PengajuanSetorDetail;
import com.neurix.common.bo.BaseMasterBo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface PengajuanSetorBo extends BaseMasterBo<PengajuanSetor> {

    List<PengajuanSetorDetail> listPPhPayroll(PengajuanSetor search);

    List<PengajuanSetorDetail> listPPh21KsoDokter(PengajuanSetor search);

    List<PengajuanSetorDetail> listPPh21Pengajuan(PengajuanSetor search);

    void saveAddPengajuanSetorPph21(PengajuanSetor bean, List<PengajuanSetorDetail> pengajuanSetorDetailListPayroll, List<PengajuanSetorDetail> pengajuanSetorDetailListKso, List<PengajuanSetorDetail> pengajuanSetorDetailListPengajuan);
}
