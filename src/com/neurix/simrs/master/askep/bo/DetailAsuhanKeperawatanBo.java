package com.neurix.simrs.master.askep.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.askep.model.DetailAsuhanKeperawatan;

import java.util.List;

public interface DetailAsuhanKeperawatanBo {
    public List<DetailAsuhanKeperawatan> getByCriteria(DetailAsuhanKeperawatan bean) throws GeneralBOException;
}
