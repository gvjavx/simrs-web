package com.neurix.hris.master.jamkerja.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.hris.master.jamkerja.model.JamKerja;

import java.util.List;

/**
 * Created by thinkpad on 19/03/2018.
 */
public interface JamKerjaBo extends BaseMasterBo<JamKerja> {
    public List<JamKerja> getByCriteria(JamKerja bean);
}
