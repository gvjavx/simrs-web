package com.neurix.simrs.transaksi.obatpoli.action;

import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import org.apache.log4j.Logger;

/**
 * Created by Toshiba on 12/12/2019.
 */
public class ObatPoliAction {
    private static transient Logger logger = Logger.getLogger(ObatPoliAction.class);
    private ObatPoli obatPoli;

    private ObatPoliBo obatPoliBoProxy;

    public ObatPoli getObatPoli() {
        return obatPoli;
    }

    public void setObatPoli(ObatPoli obatPoli) {
        this.obatPoli = obatPoli;
    }

    public void setObatPoliBoProxy(ObatPoliBo obatPoliBoProxy) {
        this.obatPoliBoProxy = obatPoliBoProxy;
    }
}
