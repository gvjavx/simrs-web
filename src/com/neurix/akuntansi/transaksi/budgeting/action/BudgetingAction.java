package com.neurix.akuntansi.transaksi.budgeting.action;

import com.neurix.akuntansi.transaksi.budgeting.bo.BudgetingBo;
import com.neurix.akuntansi.transaksi.budgeting.model.Budgeting;
import org.apache.log4j.Logger;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by reza on 29/04/20.
 */
public class BudgetingAction {
    private static transient Logger logger = Logger.getLogger(BudgetingAction.class);
    private BudgetingBo budgetingBoProxy;
    private Budgeting budgeting;

    public Budgeting getBudgeting() {
        return budgeting;
    }

    public void setBudgeting(Budgeting budgeting) {
        this.budgeting = budgeting;
    }

    public String initForm(){
        logger.info("[BudgetingAction.initForm] START >>>");

        setBudgeting(new Budgeting());

        logger.info("[BudgetingAction.initForm] END <<<");
        return "search";
    }

    public List<Budgeting> getSearchListBudgeting(String jsonString) throws JSONException{
        List<Budgeting> budgetings = new ArrayList<>();

        return budgetings;
    }

    public void setBudgetingBoProxy(BudgetingBo budgetingBoProxy) {
        this.budgetingBoProxy = budgetingBoProxy;
    }
}
