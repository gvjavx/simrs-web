package com.neurix.akuntansi.transaksi.budgetingnilaidasar.bo.impl;

import com.neurix.akuntansi.transaksi.budgetingnilaidasar.bo.BudgetingNilaiDasarBo;
import com.neurix.akuntansi.transaksi.budgetingnilaidasar.dao.MasterBudgetingNilaiDasarDao;
import com.neurix.akuntansi.transaksi.budgetingnilaidasar.dao.TransBudgetingNilaiDasarDao;
import org.apache.log4j.Logger;

/**
 * Created by reza on 10/08/20.
 */
public class BudgetingNilaiDasarBoImpl implements BudgetingNilaiDasarBo {

    private static transient Logger logger = Logger.getLogger(BudgetingNilaiDasarBoImpl.class);
    private MasterBudgetingNilaiDasarDao masterBudgetingNilaiDasarDao;
    private TransBudgetingNilaiDasarDao transBudgetingNilaiDasarDao;

    public void setMasterBudgetingNilaiDasarDao(MasterBudgetingNilaiDasarDao masterBudgetingNilaiDasarDao) {
        this.masterBudgetingNilaiDasarDao = masterBudgetingNilaiDasarDao;
    }

    public void setTransBudgetingNilaiDasarDao(TransBudgetingNilaiDasarDao transBudgetingNilaiDasarDao) {
        this.transBudgetingNilaiDasarDao = transBudgetingNilaiDasarDao;
    }
}
