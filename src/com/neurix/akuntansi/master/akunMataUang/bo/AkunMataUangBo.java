package com.neurix.akuntansi.master.akunMataUang.bo;

import com.neurix.akuntansi.master.akunMataUang.model.AkunMataUang;
import com.neurix.akuntansi.master.akunMataUang.model.ImAkunMataUangEntity;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

/**
 * Created by Aji Noor on 05/02/2021.
 */

public interface AkunMataUangBo extends BaseMasterBo<AkunMataUang> {

    List<ImAkunMataUangEntity> getMataUangByKode(String kodeMataUang) throws GeneralBOException;
    List<ImAkunMataUangEntity> getCurrencyActive() throws GeneralBOException;

}
