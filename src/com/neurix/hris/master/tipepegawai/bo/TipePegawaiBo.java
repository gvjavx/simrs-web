package com.neurix.hris.master.tipepegawai.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.tipepegawai.model.TipePegawai;

import java.util.List;


/**
 * Created by thinkpad on 19/03/2018.
 */
public interface TipePegawaiBo extends BaseMasterBo<TipePegawai> {

    List<TipePegawai> searchTipePegawaiByBranch(String unitId) throws GeneralBOException;
}
