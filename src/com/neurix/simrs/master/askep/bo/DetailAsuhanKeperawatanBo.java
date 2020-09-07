package com.neurix.simrs.master.askep.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.askep.model.DetailAsuhanKeperawatan;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;

import java.util.List;

public interface DetailAsuhanKeperawatanBo {
    public List<DetailAsuhanKeperawatan> getByCriteria(DetailAsuhanKeperawatan bean) throws GeneralBOException;
}
