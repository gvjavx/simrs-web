package com.neurix.simrs.transaksi.notifikasiadmin.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.notifikasiadmin.bo.NotifikasiAdminBo;
import com.neurix.simrs.transaksi.notifikasiadmin.dao.NotifikasiAdminTelemedicDao;
import com.neurix.simrs.transaksi.notifikasiadmin.model.ItNotifikasiAdminTelemedicEntity;
import com.neurix.simrs.transaksi.notifikasiadmin.model.NotifikasiAdmin;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 06/08/20.
 */
public class NotifikasiAdminBoImpl implements NotifikasiAdminBo {

    private static transient Logger logger = Logger.getLogger(NotifikasiAdminBoImpl.class);

    private NotifikasiAdminTelemedicDao notifikasiAdminTelemedicDao;

    public void setNotifikasiAdminTelemedicDao(NotifikasiAdminTelemedicDao notifikasiAdminTelemedicDao) {
        this.notifikasiAdminTelemedicDao = notifikasiAdminTelemedicDao;
    }

    @Override
    public List<ItNotifikasiAdminTelemedicEntity> getNotifikasiAdminEntityByCriteria(NotifikasiAdmin bean) throws GeneralBOException {

        Map hsCriteria = new HashMap();
        if (bean.getId() != null)
            hsCriteria.put("id", bean.getId());
        if (bean.getBranchId() != null)
            hsCriteria.put("branch_id", bean.getBranchId());
        if (bean.getRoleId() != null)
            hsCriteria.put("role_id", bean.getRoleId());
        if (bean.getUserId() != null)
            hsCriteria.put("user_id", bean.getUserId());
        if (bean.getFlag() != null)
            hsCriteria.put("flag", bean.getFlag());
        if (bean.getIdItem() != null)
            hsCriteria.put("id_item", bean.getIdItem());

        List<ItNotifikasiAdminTelemedicEntity> notif = new ArrayList<>();
        try {
            notif = notifikasiAdminTelemedicDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[NotifikasiAdminBoImpl.getNotifikasiAdminEntityByCriteria] ERROR. ", e);
            throw new GeneralBOException("[NotifikasiAdminBoImpl.getNotifikasiAdminEntityByCriteria] ERROR. ", e);
        }

        return notif;
    }

    @Override
    public void createNotifikasiAdmin(String idItem, String roleId, String branchId, String userName, Timestamp createdDate, String keterangan) {

        ItNotifikasiAdminTelemedicEntity notif = new ItNotifikasiAdminTelemedicEntity();
        notif.setId(generateIdNotif(branchId));
        notif.setIdItem(idItem);
        notif.setRoleId(roleId);
        notif.setBranchId(branchId);
        notif.setKeterangan(keterangan);
        notif.setFlag("Y");
        notif.setAction("C");
        notif.setCreatedWho(userName);
        notif.setLastUpdateWho(userName);
        notif.setCreatedDate(createdDate);
        notif.setLastUpdate(createdDate);

        try {
            notifikasiAdminTelemedicDao.addAndSave(notif);
        } catch (HibernateException e){
            logger.error("[NotifikasiAdminBoImpl.createNotifikasiAdmin] ERROR. ", e);
            throw new GeneralBOException("[NotifikasiAdminBoImpl.createNotifikasiAdmin] ERROR. ", e);
        }
    }

    @Override
    public void readNotifikasiAdmin(String idNotif, String userName, Timestamp lastUpdate) throws GeneralBOException {
        ItNotifikasiAdminTelemedicEntity notif = notifikasiAdminTelemedicDao.getById("id", idNotif);
        if (notif != null){

            notif.setFlag("N");
            notif.setAction("U");
            notif.setLastUpdate(lastUpdate);
            notif.setLastUpdateWho(userName);

            try {
                notifikasiAdminTelemedicDao.updateAndSave(notif);
            } catch (HibernateException e){
                logger.error("[NotifikasiAdminBoImpl.readNotifikasiAdmin] ERROR. ", e);
                throw new GeneralBOException("[NotifikasiAdminBoImpl.readNotifikasiAdmin] ERROR. ", e);
            }
        }
    }

    private String generateIdNotif(String branchId){
        String stDate = CommonUtil.stDateSeq();
        return "NTF"+ branchId + stDate + notifikasiAdminTelemedicDao.getNextSeq();
    }
}
