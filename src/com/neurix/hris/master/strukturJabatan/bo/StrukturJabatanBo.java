package com.neurix.hris.master.strukturJabatan.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface StrukturJabatanBo extends BaseMasterBo<StrukturJabatan>{
    public void saveDelete(StrukturJabatan bean) throws GeneralBOException;

    public List<StrukturJabatan> getComboStrukturJabatanWithCriteria(String query) throws GeneralBOException;
    public List<StrukturJabatan> getDataStrukturJab(String branchId, String positionId, String nip) throws GeneralBOException;
    public StrukturJabatan getDataStrukturJab(String id) throws GeneralBOException;
    public List<StrukturJabatan> getPerBagianSys() throws GeneralBOException;

    List <StrukturJabatan> getPerBagianSisa() throws GeneralBOException;

    public List<StrukturJabatan> getPerBagianDropDown() throws GeneralBOException;

    List<StrukturJabatan> getPerBagianDropDownUnit() throws GeneralBOException;

    List<StrukturJabatan> getPerBagian() throws GeneralBOException;
}