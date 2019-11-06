package com.neurix.hris.master.jamLembur.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.lembur.model.JamLembur;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface JamLemburBo extends BaseMasterBo<JamLembur>{
    public void saveDelete(JamLembur bean) throws GeneralBOException;

    public List<JamLembur> getComboJamLemburWithCriteria(String query) throws GeneralBOException;

}
