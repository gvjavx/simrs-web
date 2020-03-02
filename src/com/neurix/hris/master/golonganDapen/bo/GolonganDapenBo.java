package com.neurix.hris.master.golonganDapen.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.golonganDapen.model.GolonganDapen;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface GolonganDapenBo extends BaseMasterBo<GolonganDapen>{
    public void saveDelete(GolonganDapen bean) throws GeneralBOException;

    public List<GolonganDapen> getComboGolonganDapenWithCriteria(String query) throws GeneralBOException;

}
