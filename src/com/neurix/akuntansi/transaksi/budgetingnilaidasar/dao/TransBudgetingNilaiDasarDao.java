package com.neurix.akuntansi.transaksi.budgetingnilaidasar.dao;

import com.neurix.akuntansi.transaksi.budgetingnilaidasar.model.ItAkunBudgetingNilaiDasarEntity;
import com.neurix.common.dao.GenericDao;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 07/08/20.
 */
public class TransBudgetingNilaiDasarDao extends GenericDao<ItAkunBudgetingNilaiDasarEntity, String>{

    @Override
    protected Class<ItAkunBudgetingNilaiDasarEntity> getEntityClass() {
        return null;
    }

    @Override
    public List<ItAkunBudgetingNilaiDasarEntity> getByCriteria(Map mapCriteria) {
        return null;
    }
}
