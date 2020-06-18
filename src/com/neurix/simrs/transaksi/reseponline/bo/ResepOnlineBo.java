package com.neurix.simrs.transaksi.reseponline.bo;

import com.neurix.common.bo.GeneralBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.reseponline.model.ResepOnline;

import java.util.List;

/**
 * @author gondok
 * Wednesday, 17/06/20 9:54
 */
public interface ResepOnlineBo extends GeneralBo {
    public List<ResepOnline> getByCriteria(ResepOnline bean) throws GeneralBOException;
}
