package com.neurix.akuntansi.master.tipeJurnal.bo;

import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.tipeJurnal.model.TipeJurnal;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface TipeJurnalBo extends BaseMasterBo<TipeJurnal> {
    public void saveDelete(TipeJurnal bean) throws GeneralBOException;

    List<KodeRekening> getMappingJurnalInKodeRekening(String id) throws GeneralBOException;

    String getTipeJurnalByTransId(String transId);
}
