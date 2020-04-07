package com.neurix.simrs.master.jenisperiksapasien.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisperiksapasien.model.Asuransi;

import java.util.List;

public interface AsuransiBo  {

    public List<Asuransi> getByCriteria(Asuransi bean) throws GeneralBOException;
}
