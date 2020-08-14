package com.neurix.akuntansi.transaksi.budgetingnilaidasar.dao;

import com.neurix.akuntansi.transaksi.budgetingnilaidasar.model.ImAkunBudgetingNilaiDasarEntity;
import com.neurix.common.dao.GenericDao;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 07/08/20.
 */
public class MasterBudgetingNilaiDasarDao extends GenericDao<ImAkunBudgetingNilaiDasarEntity, String> {
    @Override
    protected Class<ImAkunBudgetingNilaiDasarEntity> getEntityClass() {
        return null;
    }

    @Override
    public List<ImAkunBudgetingNilaiDasarEntity> getByCriteria(Map mapCriteria) {
        return null;
    }
}
