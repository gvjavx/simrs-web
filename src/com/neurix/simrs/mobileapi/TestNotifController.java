package com.neurix.simrs.mobileapi;

import com.neurix.common.util.FirebasePushNotif;
import com.neurix.simrs.mobileapi.model.TestNotif;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.Collection;

/**
 * @author gondok
 * Saturday, 13/06/20 10:17
 */
public class TestNotifController implements ModelDriven<Object> {
    private static final transient Logger logger = Logger.getLogger(TestNotifController.class);
    private TestNotif model = new TestNotif();
    private Collection<TestNotif> listOfNotif;

    @Override
    public Object getModel() {
        return listOfNotif != null ? listOfNotif : model;
    }

    public HttpHeaders create() {
        logger.info("[TestNotifController.create] start process POST / <<<");
        boolean isSuccess = FirebasePushNotif.sendNotificationFirebase(model.getTokenId(), model.getTitle(), model.getBody(), model.getClick_action(), model.getOs(), true);

        if (isSuccess) {
            model.setMessage("Success");
        } else model.setMessage(("Failed"));

        logger.info("[TestNotifController.create] end process POST / <<<");
        return new DefaultHttpHeaders("create").disableCaching();
    }
}
