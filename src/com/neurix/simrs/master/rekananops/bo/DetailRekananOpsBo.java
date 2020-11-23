package com.neurix.simrs.master.rekananops.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.rekananops.model.DetailRekananOps;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import com.neurix.simrs.transaksi.CrudResponse;

import java.util.List;

public interface DetailRekananOpsBo {
    public List<DetailRekananOps> getSearchByCriteria (DetailRekananOps bean) throws GeneralBOException;
    public CrudResponse saveAdd(DetailRekananOps bean) throws GeneralBOException;


}
