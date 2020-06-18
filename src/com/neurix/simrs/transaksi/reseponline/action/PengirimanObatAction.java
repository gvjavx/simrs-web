package com.neurix.simrs.transaksi.reseponline.action;

import org.apache.log4j.Logger;

/**
 * Created by reza on 18/06/20.
 */
public class PengirimanObatAction {
    private static transient Logger logger = Logger.getLogger(PengirimanObatAction.class);

    private String initForm(){
        logger.info("PengirimanObatAction.initForm >>> ");

        logger.info("PengirimanObatAction.initForm <<< ");
        return "search";
    }


}
