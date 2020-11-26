package com.neurix.akuntansi.master.tipeLaporan.bo;

import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.tipeLaporan.model.TipeLaporan;
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
public interface TipeLaporanBo extends BaseMasterBo<TipeLaporan> {
    public void saveDelete(TipeLaporan bean) throws GeneralBOException;
    public TipeLaporan getTipeLaporanById(String tipeLaporanId) throws GeneralBOException;
    
}
