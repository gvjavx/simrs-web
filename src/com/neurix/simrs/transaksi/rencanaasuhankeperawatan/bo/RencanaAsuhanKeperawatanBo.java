package com.neurix.simrs.transaksi.rencanaasuhankeperawatan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.rencanaasuhankeperawatan.model.RencanaAsuhanKeperawatan;

import java.util.List;

public interface RencanaAsuhanKeperawatanBo {
    public List<RencanaAsuhanKeperawatan> getByCriteria(RencanaAsuhanKeperawatan bean) throws GeneralBOException;
    public CrudResponse saveAdd(RencanaAsuhanKeperawatan bean) throws GeneralBOException;
    public CrudResponse saveDelete(RencanaAsuhanKeperawatan bean) throws GeneralBOException;
}
