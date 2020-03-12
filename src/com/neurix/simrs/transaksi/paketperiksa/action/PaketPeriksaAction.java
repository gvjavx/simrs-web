package com.neurix.simrs.transaksi.paketperiksa.action;

import com.neurix.akuntansi.master.masterVendor.bo.MasterVendorBo;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.simrs.transaksi.paketperiksa.bo.PaketPeriksaBo;
import org.apache.log4j.Logger;

/**
 * Created by reza on 12/03/20.
 */
public class PaketPeriksaAction extends BaseTransactionAction {

    public static transient Logger logger = Logger.getLogger(PaketPeriksaAction.class);

    private PaketPeriksaBo paketPeriksaBoProxy;
    //private MasterVendorBo masterVendorBoProxy;

    public void setPaketPeriksaBoProxy(PaketPeriksaBo paketPeriksaBoProxy) {
        this.paketPeriksaBoProxy = paketPeriksaBoProxy;
    }

    @Override
    public String search() {
        return null;
    }

    @Override
    public String initForm() {
        return null;
    }
}
