package com.neurix.simrs.transaksi.checkup.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;

import java.util.List;

/**
 * Created by Toshiba on 08/11/2019.
 */
public interface CheckupBo {
    public List<HeaderCheckup> getByCriteria(HeaderCheckup bean)throws GeneralBOException;
    public void saveAdd(HeaderCheckup bean)throws GeneralBOException;
    public void saveEdit(HeaderCheckup bean)throws GeneralBOException;
    Long saveErrorMessage(String message, String s);
}
