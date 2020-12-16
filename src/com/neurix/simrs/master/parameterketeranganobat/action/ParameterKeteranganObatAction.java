package com.neurix.simrs.master.parameterketeranganobat.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.parameterketeranganobat.bo.ParameterKeteranganObatBo;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.List;

public class ParameterKeteranganObatAction {
    public static transient Logger logger = Logger.getLogger(ParameterKeteranganObatAction.class);
    private ParameterKeteranganObatBo parameterKeteranganObatBo;

    public void setParameterKeteranganObatBo(ParameterKeteranganObatBo parameterKeteranganObatBo) {
        this.parameterKeteranganObatBo = parameterKeteranganObatBo;
    }
}
