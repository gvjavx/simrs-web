package com.neurix.simrs.master.tindakanmedis.action;

import com.neurix.common.action.BaseTransactionAction;
import com.neurix.simrs.master.tindakan.bo.HeaderTindakanBo;
import com.neurix.simrs.master.tindakanmedis.bo.TindakanMedisBo;
import com.neurix.simrs.master.tindakanmedis.bo.TindakanMedisDetailBo;
import com.neurix.simrs.master.tindakanmedis.model.TindakanMedis;
import com.neurix.simrs.master.tindakanmedis.model.TindakanMedisDetail;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.List;

/** for handling request from jsp, tindakan medis */
public class TindakanMedisAction extends BaseTransactionAction {

    /** Logger */
    protected static transient Logger logger = Logger.getLogger(TindakanMedisAction.class);

    /** Object Processor and Bean */
    private TindakanMedis tm;
    private TindakanMedisBo tmBoProxy;

    /** set by spring bean */
    public void setTindakanMedisBoProxy(TindakanMedisBo tmBoProxy) {
        this.tmBoProxy = tmBoProxy;
    }

    @Override
    public String search() {
        return null;
    }

    @Override
    public String initForm() {
        return null;
    }

    public List<TindakanMedis> getListTindakanMedis(String type)
    {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanMedisBo tmBo = (TindakanMedisBo) ctx.getBean("tindakanMedisBoProxy");
        TindakanMedis filter;
        List<TindakanMedis> result = null;

        try {
            filter = new TindakanMedis();
            if (null!=type && !type.equalsIgnoreCase(""))
            { filter.setType(type); }

            result = tmBo.getByCriteria(filter);
        }
        catch (Exception e)
        {
            logger.error("Mohon maaf tidak bisa dilanjutkan dikarenakan, "+e.getMessage());
        }

        return result;
    }

    public List<TindakanMedisDetail> getTindakanMedisDetail(String id)
    {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanMedisDetailBo tmdBo = (TindakanMedisDetailBo) ctx.getBean("tindakanMedisDetailBoProxy");
        TindakanMedisDetail filter;
        List<TindakanMedisDetail> result = null;

        try {

            filter = new TindakanMedisDetail();
            if (null!=id && !id.equalsIgnoreCase(""))
            { filter.setParentid(id); }

            result = tmdBo.getByCriteria(filter);
        }
        catch (Exception e)
        {
            logger.error("Mohon maaf tidak bisa dilanjutkan dikarenakan, "+e.getMessage());
        }

        return result;
    }
}
