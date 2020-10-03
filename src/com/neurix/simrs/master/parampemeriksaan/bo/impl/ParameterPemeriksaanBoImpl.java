package com.neurix.simrs.master.parampemeriksaan.bo.impl;
import com.neurix.simrs.master.parampemeriksaan.dao.ParameterPemeriksaanDao;
import org.apache.log4j.Logger;

public class ParameterPemeriksaanBoImpl{

    protected static transient Logger logger = Logger.getLogger(ParameterPemeriksaanBoImpl.class);
    private ParameterPemeriksaanDao parameterPemeriksaanDao;

    public void setParameterPemeriksaanDao(ParameterPemeriksaanDao parameterPemeriksaanDao) {
        this.parameterPemeriksaanDao = parameterPemeriksaanDao;
    }

    public static Logger getLogger() {
        return logger;
    }
}