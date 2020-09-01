package com.neurix.simrs.transaksi.pengkajiankeperawatan.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.pengkajiankeperawatan.model.PengkajianUlangKeperawatan;

import java.util.List;

public interface PengkajianUlangKeperawatanBo {
    public List<PengkajianUlangKeperawatan> getByCriteria(PengkajianUlangKeperawatan bean) throws GeneralBOException;
    public CrudResponse saveAdd(PengkajianUlangKeperawatan bean, List<PengkajianUlangKeperawatan> keperawatanList) throws GeneralBOException;
    public CrudResponse saveEdit(PengkajianUlangKeperawatan bean) throws GeneralBOException;
    public CrudResponse saveDelete(PengkajianUlangKeperawatan bean) throws GeneralBOException;
    public Boolean cekPengkajianUlangKeperawatan(PengkajianUlangKeperawatan bean, String tipe) throws GeneralBOException;
}
