package com.neurix.akuntansi.master.mappingJurnal.bo;

import com.neurix.akuntansi.master.mappingJurnal.model.MappingJurnal;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface MappingJurnalBo extends BaseMasterBo<MappingJurnal> {
    public void saveDelete(MappingJurnal bean) throws GeneralBOException;

    public void saveAddList(List<MappingJurnal> listBean, MappingJurnal data , String userLogin, Timestamp updateTime) throws GeneralBOException;
}
