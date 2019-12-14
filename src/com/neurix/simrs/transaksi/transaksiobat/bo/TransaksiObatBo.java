package com.neurix.simrs.transaksi.transaksiobat.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;

import java.util.List;

/**
 * Created by Toshiba on 11/12/2019.
 */
public interface TransaksiObatBo {
    public List<TransaksiObatDetail> getSearchObatTransaksiByCriteria(TransaksiObatDetail bean) throws GeneralBOException;
    public void saveAdd(TransaksiObatDetail bean, List<TransaksiObatDetail> listOfObatResep, List<TransaksiObatDetail> listOfObat) throws GeneralBOException;

    public List<TransaksiObatDetail> getByCriteria(TransaksiObatDetail bean) throws GeneralBOException;
    public void saveEditDetail(TransaksiObatDetail bean) throws GeneralBOException;
}
