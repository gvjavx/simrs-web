package com.neurix.simrs.transaksi.konsultasigizi.action;

import com.neurix.common.action.BaseTransactionAction;

public class KonsultasiGiziAction extends BaseTransactionAction {
    @Override
    public String search() {
        return "search";
    }

    @Override
    public String initForm() {
        return "search";
    }
}
