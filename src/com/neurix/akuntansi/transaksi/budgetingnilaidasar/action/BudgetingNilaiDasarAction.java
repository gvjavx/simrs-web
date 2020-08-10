package com.neurix.akuntansi.transaksi.budgetingnilaidasar.action;

import com.neurix.akuntansi.transaksi.budgetingnilaidasar.bo.BudgetingNilaiDasarBo;
import com.neurix.akuntansi.transaksi.budgetingnilaidasar.model.BudgetingNilaiDasar;
import com.neurix.common.action.BaseMasterAction;
import org.apache.log4j.Logger;

/**
 * Created by reza on 10/08/20.
 */
public class BudgetingNilaiDasarAction extends BaseMasterAction{
    private static transient Logger logger = Logger.getLogger(BudgetingNilaiDasarAction.class);
    private BudgetingNilaiDasarBo budgetingNilaiDasarBoProxy;
    private BudgetingNilaiDasar budgetingNilaiDasar;

    public BudgetingNilaiDasar getBudgetingNilaiDasar() {
        return budgetingNilaiDasar;
    }

    public void setBudgetingNilaiDasar(BudgetingNilaiDasar budgetingNilaiDasar) {
        this.budgetingNilaiDasar = budgetingNilaiDasar;
    }

    public void setBudgetingNilaiDasarBoProxy(BudgetingNilaiDasarBo budgetingNilaiDasarBoProxy) {
        this.budgetingNilaiDasarBoProxy = budgetingNilaiDasarBoProxy;
    }

    @Override
    public String add() {
        return null;
    }

    @Override
    public String edit() {
        return null;
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public String search() {
        return null;
    }

    @Override
    public String initForm() {
        setBudgetingNilaiDasar(new BudgetingNilaiDasar());
        return "search";
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }
}
