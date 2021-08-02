package com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.dao;

import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.model.ItAkunPendaftaranJasaEntity;
import com.neurix.common.dao.GenericDao;

import java.util.List;
import java.util.Map;

public class PendaftaranJasaRekananDao extends GenericDao<ItAkunPendaftaranJasaEntity, String>{

    @Override
    protected Class<ItAkunPendaftaranJasaEntity> getEntityClass() {
        return ItAkunPendaftaranJasaEntity.class;
    }

    @Override
    public List<ItAkunPendaftaranJasaEntity> getByCriteria(Map mapCriteria) {
        return null;
    }
}
