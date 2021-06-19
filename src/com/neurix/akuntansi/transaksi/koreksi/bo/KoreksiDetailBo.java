package com.neurix.akuntansi.transaksi.koreksi.bo;

import com.neurix.akuntansi.transaksi.koreksi.model.KoreksiDetail;
import com.neurix.common.bo.BaseMasterBo;

import java.util.Map;

/**
 * Created by Aji Noor on 18/03/2021
 */
public interface KoreksiDetailBo extends BaseMasterBo<KoreksiDetail> {

    Map<String,KoreksiDetail> getListKoreksiDetail(String koreksiId);

    KoreksiDetail getById(String koreksiDetailId);
}
