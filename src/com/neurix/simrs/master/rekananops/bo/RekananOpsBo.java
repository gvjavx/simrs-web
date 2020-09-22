package com.neurix.simrs.master.rekananops.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import com.neurix.simrs.transaksi.CrudResponse;

import java.util.List;

public interface RekananOpsBo {
    public List<RekananOps> getByCriteria(RekananOps bean)throws GeneralBOException;
    public CrudResponse saveAdd(RekananOps bean) throws GeneralBOException;
    public CrudResponse saveEdit(RekananOps bean) throws GeneralBOException;
    public CrudResponse saveDelete(RekananOps bean) throws GeneralBOException;
}
