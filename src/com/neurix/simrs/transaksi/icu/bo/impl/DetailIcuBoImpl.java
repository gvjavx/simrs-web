package com.neurix.simrs.transaksi.icu.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.hemodialisa.model.Hemodialisa;
import com.neurix.simrs.transaksi.icu.bo.DetailIcuBo;
import com.neurix.simrs.transaksi.icu.model.DetailIcu;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailIcuBoImpl implements DetailIcuBo {

    private static transient Logger logger = Logger.getLogger(DetailIcuBoImpl.class);

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public List<DetailIcu> getByCriteria(DetailIcu bean) throws GeneralBOException {
        return null;
    }

    @Override
    public CrudResponse saveAdd(List<DetailIcu> list) throws GeneralBOException {
        return null;
    }
}
