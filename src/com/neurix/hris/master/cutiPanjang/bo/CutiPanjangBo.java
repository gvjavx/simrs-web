package com.neurix.hris.master.cutiPanjang.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.cutiPanjang.model.CutiPanjang;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface CutiPanjangBo extends BaseMasterBo<CutiPanjang>{
    public void saveDelete(CutiPanjang bean) throws GeneralBOException;

    public List<CutiPanjang> getComboCutiPanjangWithCriteria(String query) throws GeneralBOException;

}
