package com.neurix.hris.master.smkKategoriAspekBobot.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.bo.SmkIndikatorPenilaianAspekBo;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.dao.SmkIndikatorPenilaianAspekDao;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.ImSmkIndikatorPenilaianAspekEntity;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.SmkIndikatorPenilaianAspek;
import com.neurix.hris.master.smkKategoriAspekBobot.bo.SmkKategoriAspekBobotBo;
import com.neurix.hris.master.smkKategoriAspekBobot.dao.SmkKategoriAspekBobotDao;
import com.neurix.hris.master.smkKategoriAspekBobot.model.ImSmkKategoriAspekBobotEntity;
import com.neurix.hris.master.smkKategoriAspekBobot.model.SmkKategoriAspekBobot;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class SmkKategoriAspekBoImpl implements SmkKategoriAspekBobotBo {

    protected static transient Logger logger = Logger.getLogger(SmkKategoriAspekBoImpl.class);
    private SmkKategoriAspekBobotDao smkKategoriAspekDao;
    private SmkIndikatorPenilaianAspekDao smkIndikatorPenilaianAspekDao;
    private SmkIndikatorPenilaianAspekBo smkIndikatorPenilaianAspekBoProxy;

    public SmkIndikatorPenilaianAspekBo getSmkIndikatorPenilaianAspekBoProxy() {
        return smkIndikatorPenilaianAspekBoProxy;
    }

    public void setSmkIndikatorPenilaianAspekBoProxy(SmkIndikatorPenilaianAspekBo smkIndikatorPenilaianAspekBoProxy) {
        this.smkIndikatorPenilaianAspekBoProxy = smkIndikatorPenilaianAspekBoProxy;
    }

    public SmkIndikatorPenilaianAspekDao getSmkIndikatorPenilaianAspekDao() {
        return smkIndikatorPenilaianAspekDao;
    }

    public void setSmkIndikatorPenilaianAspekDao(SmkIndikatorPenilaianAspekDao smkIndikatorPenilaianAspekDao) {
        this.smkIndikatorPenilaianAspekDao = smkIndikatorPenilaianAspekDao;
    }

    public SmkKategoriAspekBobotDao getSmkKategoriAspekDao() {
        return smkKategoriAspekDao;
    }

    public void setSmkKategoriAspekDao(SmkKategoriAspekBobotDao smkKategoriAspekDao) {
        this.smkKategoriAspekDao = smkKategoriAspekDao;
    }

    public SmkKategoriAspekBobotDao getSmkKategoriAspekBDao() {
        return smkKategoriAspekDao;
    }

    public void setSmkKategoriAspekBDao(SmkKategoriAspekBobotDao smkKategoriAspekDao) {
        this.smkKategoriAspekDao = smkKategoriAspekDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkKategoriAspekBoImpl.logger = logger;
    }

    public SmkKategoriAspekBobotDao getSmkKategoriDao() {
        return smkKategoriAspekDao;
    }


    public void setSmkKategoriDao(SmkKategoriAspekBobotDao smkKategoriAspekDao) {
        this.smkKategoriAspekDao = smkKategoriAspekDao;
    }

    @Override
    public void saveEdit(SmkKategoriAspekBobot bean) throws GeneralBOException {
    }

    @Override
    public void saveDelete(SmkKategoriAspekBobot bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {
            String smkKategoriId = bean.getKategoriAspekId();
            ImSmkKategoriAspekBobotEntity imSmkKategoriAspekBEntity = null;

            try {
                // Get data from database by ID
                imSmkKategoriAspekBEntity = smkKategoriAspekDao.getById("kategoriAspekId", smkKategoriId);
            } catch (HibernateException e) {
                logger.error("[SmkKategoriBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSmkKategoriAspekBEntity != null) {
                imSmkKategoriAspekBEntity.setFlag(bean.getFlag());
                imSmkKategoriAspekBEntity.setAction(bean.getAction());
                imSmkKategoriAspekBEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkKategoriAspekBEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    smkKategoriAspekDao.updateAndSave(imSmkKategoriAspekBEntity);
                    smkIndikatorPenilaianAspekDao.deleteIndikator(smkKategoriId);
                } catch (HibernateException e) {
                    logger.error("[SmkKategoriBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkKategoriAspek, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[SmkKategoriBoImpl.saveDelete] Error, not found data SmkKategoriAspek with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkKategoriAspek with request id, please check again your data ...");

            }
        }
        logger.info("[SmkKategoriBoImpl.saveDelete] end process <<<");
    }


    @Override
    public SmkKategoriAspekBobot saveAdd(SmkKategoriAspekBobot bean) throws GeneralBOException {
        logger.info("[SmkKategoriBoImpl.saveAdd] start process >>>");

        logger.info("[SppdBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String smkKategoriId;
            String smkIndikatorId;
            try {
                // Generating ID, get from postgre sequence
                smkKategoriId = smkKategoriAspekDao.getNextSmkKategoriAspekBobotId();
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence sppdId id, please info to your admin..." + e.getMessage());
            }

        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultSppdPerson");
        logger.info("[SppdBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<SmkKategoriAspekBobot> getByCriteria(SmkKategoriAspekBobot searchBean) throws GeneralBOException {
        logger.info("[SmkKategoriBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<SmkKategoriAspekBobot> listOfResult = new ArrayList();

        return listOfResult;
    }

   /* public SmkKategoriAspek saveAdd(SmkKategoriAspek bean) throws GeneralBOException {

    }
*/
    @Override
    public List<SmkKategoriAspekBobot> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }



    public List<SmkKategoriAspekBobot> getComboSmkKategoriWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<SmkKategoriAspekBobot> listComboSmkKategoriAspekB = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImSmkKategoriAspekBobotEntity> listSmkKategori = null;
        try {
            listSmkKategori = smkKategoriAspekDao.getListSmkKategori(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listSmkKategori != null) {
            for (ImSmkKategoriAspekBobotEntity imSmkKategoriAspekBEntity : listSmkKategori) {
                SmkKategoriAspekBobot itemComboSmkKategoriAspekB = new SmkKategoriAspekBobot();
                itemComboSmkKategoriAspekB.setKategoriAspekId(imSmkKategoriAspekBEntity.getKategoriAspekId());
                listComboSmkKategoriAspekB.add(itemComboSmkKategoriAspekB);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboSmkKategoriAspekB;
    }
}
