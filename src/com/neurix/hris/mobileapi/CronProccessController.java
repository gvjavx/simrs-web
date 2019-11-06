package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.mobileapi.model.Biodata;
import com.neurix.hris.transaksi.absensi.bo.AbsensiBo;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gondok
 * Saturday, 23/02/19 16:21
 */

public class CronProccessController implements ModelDriven<Object> {

    private static final transient Logger logger = Logger.getLogger(CronProccessController.class);

    public HttpHeaders index() throws Exception{
        logger.info("[BiodataController.find] end process POST /biodata/{id}/find <<<");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AbsensiBo absensiBo = (AbsensiBo) ctx.getBean("absensiBoProxy");
        try {
            absensiBo.getDataInquiryForCronJob();
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = absensiBo.saveErrorMessage(e.getMessage(), "CronProccessController.isFoundOtherSessionActiveUserSessionLog");
            } catch (GeneralBOException e1) {
                logger.error("[CronProccessController.isFoundOtherSessionActiveUserSessionLog] Error when saving error,", e1);
            }
            logger.error("[CronProccessController.isFoundOtherSessionActiveUserSessionLog] Error when searching / inquiring data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException(e);
        }

        logger.info("[CronProccessController.find] end process POST /cronproccess<<<");

        return new DefaultHttpHeaders("index").disableCaching();
    }


    @Override
    public Object getModel() {
        return null;
    }
}
