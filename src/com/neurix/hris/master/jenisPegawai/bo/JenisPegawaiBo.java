package com.neurix.hris.master.jenisPegawai.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.jenisPegawai.model.JenisPegawai;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface JenisPegawaiBo extends BaseMasterBo<JenisPegawai> {
    public void saveDelete(JenisPegawai bean) throws GeneralBOException;
    public JenisPegawai getJenisPegawaiById(String jenisPegawaiId) throws GeneralBOException;
    
}
