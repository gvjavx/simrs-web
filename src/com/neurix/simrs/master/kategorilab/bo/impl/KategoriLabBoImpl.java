package com.neurix.simrs.master.kategorilab.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisobat.bo.JenisObatBo;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import com.neurix.simrs.master.kategorilab.dao.KategoriLabDao;
import org.apache.log4j.Logger;

import java.util.List;

public class KategoriLabBoImpl {

    protected static transient Logger logger = Logger.getLogger(KategoriLabBoImpl.class);
    private KategoriLabDao kategoriLabDao;

    public static Logger getLogger() {
        return logger;
    }
    public KategoriLabDao getKategoriLabDao() {
        return kategoriLabDao;
    }

}