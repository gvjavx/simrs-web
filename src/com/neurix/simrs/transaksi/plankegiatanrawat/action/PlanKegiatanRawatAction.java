package com.neurix.simrs.transaksi.plankegiatanrawat.action;

import com.neurix.common.action.BaseTransactionAction;
import com.neurix.simrs.transaksi.plankegiatanrawat.bo.PlanKegiatanRawatBo;
import org.apache.log4j.Logger;

/**
 * Created by reza on 06/03/20.
 */
public class PlanKegiatanRawatAction extends BaseTransactionAction {
    Logger logger = Logger.getLogger(PlanKegiatanRawatAction.class);
    private PlanKegiatanRawatBo planKegiatanRawatBoProxy;

    @Override
    public String search() {
        return null;
    }

    @Override
    public String initForm() {
        return null;
    }

    public void setPlanKegiatanRawatBoProxy(PlanKegiatanRawatBo planKegiatanRawatBoProxy) {
        this.planKegiatanRawatBoProxy = planKegiatanRawatBoProxy;
    }
}
