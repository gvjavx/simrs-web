package com.neurix.hris.transaksi.absensi.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.absensi.model.MesinAbsensiDetail;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface MesinAbsensiDetailBo extends BaseMasterBo<MesinAbsensiDetail> {
    public void saveDelete(MesinAbsensiDetail bean) throws GeneralBOException;

    List<MesinAbsensiDetail> getByCriteriaMobile(MesinAbsensiDetail searchBean) throws GeneralBOException;
}
