package com.neurix.simrs.transaksi.notifikasiadmin.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.notifikasiadmin.bo.NotifikasiAdminBo;
import com.neurix.simrs.transaksi.notifikasiadmin.model.ItNotifikasiAdminTelemedicEntity;
import com.neurix.simrs.transaksi.notifikasiadmin.model.NotifikasiAdmin;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by reza on 06/08/20.
 */
public class NotifikasiAdminAction {
    private static transient Logger logger = Logger.getLogger(NotifikasiAdminAction.class);
    private NotifikasiAdminBo notifikasiAdminBoProxy;

    public void setNotifikasiAdminBoProxy(NotifikasiAdminBo notifikasiAdminBoProxy) {
        this.notifikasiAdminBoProxy = notifikasiAdminBoProxy;
    }

    public List<ItNotifikasiAdminTelemedicEntity> getUnReadNotifAdminEntity(String roleId) {

        String branchid = CommonUtil.userBranchLogin();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiAdminBo notifikasiAdminBo = (NotifikasiAdminBo) ctx.getBean("notifikasiAdminBoProxy");

        NotifikasiAdmin notifikasiAdmin = new NotifikasiAdmin();
        notifikasiAdmin.setRoleId(roleId);
        notifikasiAdmin.setBranchId(branchid);
        notifikasiAdmin.setFlag("Y");

        return notifikasiAdminBo.getNotifikasiAdminEntityByCriteria(notifikasiAdmin);
    }

    public String readNotif(String idNotif) {

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiAdminBo notifikasiAdminBo = (NotifikasiAdminBo) ctx.getBean("notifikasiAdminBoProxy");
        try {
            notifikasiAdminBo.readNotifikasiAdmin(idNotif, userLogin, time);
        } catch (GeneralBOException e){
            return "error";
        }

        return "success";
    }
}
