package com.neurix.simrs.master.obatpoli.bo.impl;

import com.neurix.simrs.master.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.master.obatpoli.dao.ObatPoliDao;
import org.apache.log4j.Logger;

/**
 * Created by Toshiba on 11/12/2019.
 */
public class ObatPoliBoImpl implements ObatPoliBo {

    private static transient Logger logger = Logger.getLogger(ObatPoliBoImpl.class);
    private ObatPoliDao obatPoliDao;



    public void setObatPoliDao(ObatPoliDao obatPoliDao) {
        this.obatPoliDao = obatPoliDao;
    }
}
