package com.neurix.akuntansi.transaksi.tutupperiod.action;

import com.neurix.akuntansi.transaksi.tutupperiod.bo.TutupPeriodBo;
import com.neurix.akuntansi.transaksi.tutupperiod.model.TutupPeriod;
import com.neurix.common.action.BaseTransactionAction;
import org.apache.log4j.Logger;

/**
 * Created by reza on 18/03/20.
 */
public class SettingTutupPeriodAction extends BaseTransactionAction {
    private static transient Logger logger = Logger.getLogger(SettingTutupPeriodAction.class);

    private TutupPeriodBo tutupPeriodBoProxy;

    private TutupPeriod tutupPeriod;

    public void setTutupPeriodBoProxy(TutupPeriodBo tutupPeriodBoProxy) {
        this.tutupPeriodBoProxy = tutupPeriodBoProxy;
    }

    public TutupPeriod getTutupPeriod() {
        return tutupPeriod;
    }

    public void setTutupPeriod(TutupPeriod tutupPeriod) {
        this.tutupPeriod = tutupPeriod;
    }

    @Override
    public String search() {
        return SUCCESS;
    }

    @Override
    public String initForm() {
        return SUCCESS;
    }
}
