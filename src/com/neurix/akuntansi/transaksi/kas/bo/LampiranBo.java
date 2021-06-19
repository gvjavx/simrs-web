package com.neurix.akuntansi.transaksi.kas.bo;

import com.neurix.akuntansi.transaksi.kas.model.Lampiran;
import com.neurix.common.bo.BaseMasterBo;

import java.util.Map;

/**
 * Created by Aji Noor on 24/03/2021
 */
public interface LampiranBo extends BaseMasterBo<LampiranBo> {

    Map<String, Lampiran> getListLampiran(String transaksiId);

    Lampiran getById(String kasDetailId);
}
