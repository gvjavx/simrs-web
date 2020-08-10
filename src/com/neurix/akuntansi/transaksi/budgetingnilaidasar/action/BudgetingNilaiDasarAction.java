package com.neurix.akuntansi.transaksi.budgetingnilaidasar.action;

import com.neurix.akuntansi.transaksi.budgetingnilaidasar.bo.BudgetingNilaiDasarBo;
import org.apache.log4j.Logger;

/**
 * Created by reza on 10/08/20.
 */
public class BudgetingNilaiDasarAction {
    private static transient Logger logger = Logger.getLogger(BudgetingNilaiDasarAction.class);
    private BudgetingNilaiDasarBo budgetingNilaiDasarBoProxy;

    public void setBudgetingNilaiDasarBoProxy(BudgetingNilaiDasarBo budgetingNilaiDasarBoProxy) {
        this.budgetingNilaiDasarBoProxy = budgetingNilaiDasarBoProxy;
    }

}
