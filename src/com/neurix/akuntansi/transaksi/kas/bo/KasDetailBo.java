package com.neurix.akuntansi.transaksi.kas.bo;

import com.neurix.akuntansi.transaksi.kas.model.KasDetail;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.bo.GeneralBo;

import java.util.Map;

/**
 * Created by Aji Noor on 24/03/2021
 */
public interface KasDetailBo extends BaseMasterBo<KasDetail> {

    Map<String,KasDetail> getListKasDetail(String kasId);

    KasDetail getById(String kasDetailId);
}
