package com.neurix.simrs.transaksi.paketperiksa.bo.impl;

import com.neurix.simrs.transaksi.paketperiksa.bo.PaketPeriksaBo;
import com.neurix.simrs.transaksi.paketperiksa.dao.ItemPaketDao;
import com.neurix.simrs.transaksi.paketperiksa.dao.KelasPaketDao;
import com.neurix.simrs.transaksi.paketperiksa.dao.PaketDao;
import com.neurix.simrs.transaksi.paketperiksa.dao.PaketPasienDao;
import org.apache.log4j.Logger;

/**
 * Created by reza on 12/03/20.
 */
public class PaketPeriksaBoImpl implements PaketPeriksaBo {

    private static transient Logger logger = Logger.getLogger(PaketPeriksaBoImpl.class);

    private ItemPaketDao itemPaketDao;
    private KelasPaketDao kelasPaketDao;
    private PaketDao paketDao;
    private PaketPasienDao paketPasienDao;

    public void setItemPaketDao(ItemPaketDao itemPaketDao) {
        this.itemPaketDao = itemPaketDao;
    }

    public void setKelasPaketDao(KelasPaketDao kelasPaketDao) {
        this.kelasPaketDao = kelasPaketDao;
    }

    public void setPaketDao(PaketDao paketDao) {
        this.paketDao = paketDao;
    }

    public void setPaketPasienDao(PaketPasienDao paketPasienDao) {
        this.paketPasienDao = paketPasienDao;
    }
}
