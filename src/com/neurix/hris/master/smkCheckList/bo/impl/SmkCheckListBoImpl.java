package com.neurix.hris.master.smkCheckList.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkCheckList.bo.SmkCheckListBo;
import com.neurix.hris.master.smkCheckList.dao.SmkCheckListDao;
import com.neurix.hris.master.smkCheckList.model.ImSmkCheckListEntity;
import com.neurix.hris.master.smkCheckList.model.SmkCheckList;
import com.neurix.hris.master.smkIndikatorPenilaianCheckList.bo.SmkIndikatorPenilaianCheckListBo;
import com.neurix.hris.master.smkIndikatorPenilaianCheckList.dao.SmkIndikatorPenilaianCheckListDao;
import com.neurix.hris.master.smkIndikatorPenilaianCheckList.model.ImSmkIndikatorPenilaianCheckListEntity;
import com.neurix.hris.master.smkIndikatorPenilaianCheckList.model.SmkIndikatorPenilaianCheckList;
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
public class SmkCheckListBoImpl implements SmkCheckListBo {

    protected static transient Logger logger = Logger.getLogger(SmkCheckListBoImpl.class);
    private SmkCheckListDao smkCheckListDao;
    private SmkIndikatorPenilaianCheckListDao smkIndikatorPenilaianCheckListDao;
    private SmkIndikatorPenilaianCheckListBo smkIndikatorPenilaianCheckListBoProxy;

    public SmkIndikatorPenilaianCheckListBo getSmkIndikatorPenilaianCheckListBoProxy() {
        return smkIndikatorPenilaianCheckListBoProxy;
    }

    public void setSmkIndikatorPenilaianCheckListBoProxy(SmkIndikatorPenilaianCheckListBo smkIndikatorPenilaianCheckListBoProxy) {
        this.smkIndikatorPenilaianCheckListBoProxy = smkIndikatorPenilaianCheckListBoProxy;
    }

    public SmkIndikatorPenilaianCheckListDao getSmkIndikatorPenilaianCheckListDao() {
        return smkIndikatorPenilaianCheckListDao;
    }

    public void setSmkIndikatorPenilaianCheckListDao(SmkIndikatorPenilaianCheckListDao smkIndikatorPenilaianCheckListDao) {
        this.smkIndikatorPenilaianCheckListDao = smkIndikatorPenilaianCheckListDao;
    }

/*    public SmkCheckListDao getSmkCheckListDao() {
        return smkCheckListDao;
    }*/

/*    public void setSmkCheckListDao(SmkCheckListDao smkCheckListDao) {
        this.smkCheckListDao = smkCheckListDao;
    }*/

    public SmkCheckListDao getSmkCheckListBDao() {
        return smkCheckListDao;
    }

    public void setSmkCheckListBDao(SmkCheckListDao smkCheckListDao) {
        this.smkCheckListDao = smkCheckListDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkCheckListBoImpl.logger = logger;
    }

    public SmkCheckListDao getSmkCheckListDao() {
        return smkCheckListDao;
    }


    public void setSmkCheckListDao(SmkCheckListDao smkCheckListDao) {
        this.smkCheckListDao = smkCheckListDao;
    }

    @Override
    public void saveEdit(SmkCheckList bean) throws GeneralBOException {
    }

    @Override
    public void saveDelete(SmkCheckList bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {
            String smkCheckListId = bean.getCheckListId();
            ImSmkCheckListEntity imSmkCheckListBEntity = null;

            try {
                // Get data from database by ID
                imSmkCheckListBEntity = smkCheckListDao.getById("checkListId", smkCheckListId);
            } catch (HibernateException e) {
                logger.error("[SmkCheckListBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSmkCheckListBEntity != null) {
                imSmkCheckListBEntity.setFlag(bean.getFlag());
                imSmkCheckListBEntity.setAction(bean.getAction());
                imSmkCheckListBEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkCheckListBEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    smkCheckListDao.updateAndSave(imSmkCheckListBEntity);
                    smkIndikatorPenilaianCheckListDao.deleteIndikator(smkCheckListId);
                } catch (HibernateException e) {
                    logger.error("[SmkCheckListBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkCheckList, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[SmkCheckListBoImpl.saveDelete] Error, not found data SmkCheckList with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkCheckList with request id, please check again your data ...");
            }
        }
        logger.info("[SmkCheckListBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(SmkCheckList bean, List<SmkIndikatorPenilaianCheckList> data) throws GeneralBOException {
        logger.info("[SmkCheckListBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String historyId = "";
            String smkCheckListId = bean.getCheckListId();

            ImSmkCheckListEntity imSmkCheckListBEntity = null;
            try {
                // Get data from database by ID
                imSmkCheckListBEntity = smkCheckListDao.getById("checkListId", smkCheckListId);
            } catch (HibernateException e) {
                logger.error("[SmkCheckListBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data SmkCheckList by Kode SmkCheckList, please inform to your admin...," + e.getMessage());
            }

            if (imSmkCheckListBEntity != null) {

                imSmkCheckListBEntity.setCheckListId(bean.getCheckListId());
                imSmkCheckListBEntity.setCheckListName(bean.getCheckListName());
                imSmkCheckListBEntity.setBranchId(bean.getBranchId());
                imSmkCheckListBEntity.setFlag(bean.getFlag());
                imSmkCheckListBEntity.setAction(bean.getAction());
                imSmkCheckListBEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkCheckListBEntity.setLastUpdate(bean.getLastUpdate());

                List<SmkIndikatorPenilaianCheckList> smkIndikatorPenilaianCheckListList = new ArrayList();


                HttpSession session = ServletActionContext.getRequest().getSession();
                List<SmkIndikatorPenilaianCheckList> listOfsearchSppdPerson = (List<SmkIndikatorPenilaianCheckList>) session.getAttribute("checkListIndikator");

                if(smkIndikatorPenilaianCheckListList != null){
                    for (SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckList1Database: data) {
                        if(listOfsearchSppdPerson != null){
                            String flag = "N";
                            for (SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckListSession: listOfsearchSppdPerson) {
                                if(smkIndikatorPenilaianCheckList1Database.getIndikatorPenilaianCheckListId().equals(smkIndikatorPenilaianCheckListSession.getIndikatorPenilaianCheckListId())){
                                    flag = "Y";
                                }
                            }
                            if(flag == "N"){
                                String smkIndikatorId = smkIndikatorPenilaianCheckList1Database.getIndikatorPenilaianCheckListId();
                                ImSmkIndikatorPenilaianCheckListEntity imSmkIndikatorEntity = null;
                                try {
                                    imSmkIndikatorEntity = smkIndikatorPenilaianCheckListDao.getById("indikatorPenilaianCheckListId", smkIndikatorId);
                                } catch (HibernateException e) {
                                    logger.error("[SmkIndikatorBoImpl.saveEdit] Error, " + e.getMessage());
                                    throw new GeneralBOException("Found problem when searching data SmkIndikator by Kode SmkIndikator, please inform to your admin...," + e.getMessage());
                                }
                                if (imSmkIndikatorEntity != null) {
                                    imSmkIndikatorEntity.setFlag("N");
                                    imSmkIndikatorEntity.setAction("D");
                                    imSmkIndikatorEntity.setLastUpdateWho(bean.getLastUpdateWho());
                                    imSmkIndikatorEntity.setLastUpdate(bean.getLastUpdate());
                                    try {
                                        smkIndikatorPenilaianCheckListDao.updateAndSave(imSmkIndikatorEntity);
                                    } catch (HibernateException e) {
                                        logger.error("[SmkIndikatorBoImpl.saveEdit] Error, " + e.getMessage());
                                        throw new GeneralBOException("Found problem when saving update data SmkIndikator, please info to your admin..." + e.getMessage());
                                    }
                                }
                            }
                        }
                    }
                }

                if(listOfsearchSppdPerson != null){
                    for (SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckListSession: listOfsearchSppdPerson) {
                        if(smkIndikatorPenilaianCheckListSession.getIndikatorPenilaianCheckListId() == null){
                            String smkIndikatorId = smkIndikatorPenilaianCheckListDao.getNextSmkIndikatorPenilaianCheckListId();
                            ImSmkIndikatorPenilaianCheckListEntity imSmkIndikatorPenilaianCheckListEntity = new ImSmkIndikatorPenilaianCheckListEntity();

                            imSmkIndikatorPenilaianCheckListEntity.setIndikatorPenilaianCheckListId(smkIndikatorId);
                            imSmkIndikatorPenilaianCheckListEntity.setCheckListId(smkCheckListId);
                            imSmkIndikatorPenilaianCheckListEntity.setIndikatorName(smkIndikatorPenilaianCheckListSession.getIndikatorName());
                            imSmkIndikatorPenilaianCheckListEntity.setNilai(smkIndikatorPenilaianCheckListSession.getNilai());
                            imSmkIndikatorPenilaianCheckListEntity.setFlag(bean.getFlag());
                            imSmkIndikatorPenilaianCheckListEntity.setAction(bean.getAction());
                            imSmkIndikatorPenilaianCheckListEntity.setCreatedWho(bean.getCreatedWho());
                            imSmkIndikatorPenilaianCheckListEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            imSmkIndikatorPenilaianCheckListEntity.setCreatedDate(bean.getCreatedDate());
                            imSmkIndikatorPenilaianCheckListEntity.setLastUpdate(bean.getLastUpdate());
                            smkIndikatorPenilaianCheckListDao.addAndSave(imSmkIndikatorPenilaianCheckListEntity);
                        }
                    }
                }

                try {
                    // Update into database
                    smkCheckListDao.updateAndSave(imSmkCheckListBEntity);
                } catch (HibernateException e) {
                    logger.error("[SmkCheckListBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkCheckList, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SmkCheckListBoImpl.saveEdit] Error, not found data SmkCheckList with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkCheckList with request id, please check again your data ...");
            }
        }
        logger.info("[SmkCheckListBoImpl.saveEdit] end process <<<");
    }

    @Override
    public SmkCheckList saveAdd(SmkCheckList bean) throws GeneralBOException {
        logger.info("[SmkCheckListBoImpl.saveAdd] start process >>>");

        logger.info("[SppdBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String smkCheckListId;
            String smkIndikatorId;
            try {
                // Generating ID, get from postgre sequence
                smkCheckListId = smkCheckListDao.getNextSmkCheckListId();
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence sppdId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImSmkCheckListEntity imSmkCheckListEntity = new ImSmkCheckListEntity();

            imSmkCheckListEntity.setCheckListId(smkCheckListId);
            imSmkCheckListEntity.setCheckListName(bean.getCheckListName());
            imSmkCheckListEntity.setBranchId(bean.getBranchId());

            imSmkCheckListEntity.setFlag(bean.getFlag());
            imSmkCheckListEntity.setAction(bean.getAction());
            imSmkCheckListEntity.setCreatedWho(bean.getCreatedWho());
            imSmkCheckListEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imSmkCheckListEntity.setCreatedDate(bean.getCreatedDate());
            imSmkCheckListEntity.setLastUpdate(bean.getLastUpdate());
            try {
                smkCheckListDao.addAndSave(imSmkCheckListEntity);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }

            HttpSession session = ServletActionContext.getRequest().getSession();
            List<SmkIndikatorPenilaianCheckList> listOfsearchSppdPerson = (List<SmkIndikatorPenilaianCheckList>) session.getAttribute("checkListIndikator");

            if(listOfsearchSppdPerson != null){
                for (SmkIndikatorPenilaianCheckList smkIndikatorPenilaianCheckList: listOfsearchSppdPerson) {
                    smkIndikatorId = smkIndikatorPenilaianCheckListDao.getNextSmkIndikatorPenilaianCheckListId();
                    ImSmkIndikatorPenilaianCheckListEntity imSmkIndikatorPenilaianCheckListEntity = new ImSmkIndikatorPenilaianCheckListEntity();

                    imSmkIndikatorPenilaianCheckListEntity.setIndikatorPenilaianCheckListId(smkIndikatorId);
                    imSmkIndikatorPenilaianCheckListEntity.setCheckListId(smkCheckListId);
                    imSmkIndikatorPenilaianCheckListEntity.setIndikatorName(smkIndikatorPenilaianCheckList.getIndikatorName());
                    imSmkIndikatorPenilaianCheckListEntity.setNilai(smkIndikatorPenilaianCheckList.getNilai());
                    imSmkIndikatorPenilaianCheckListEntity.setFlag(bean.getFlag());
                    imSmkIndikatorPenilaianCheckListEntity.setAction(bean.getAction());
                    imSmkIndikatorPenilaianCheckListEntity.setCreatedWho(bean.getCreatedWho());
                    imSmkIndikatorPenilaianCheckListEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imSmkIndikatorPenilaianCheckListEntity.setCreatedDate(bean.getCreatedDate());
                    imSmkIndikatorPenilaianCheckListEntity.setLastUpdate(bean.getLastUpdate());
                    smkIndikatorPenilaianCheckListDao.addAndSave(imSmkIndikatorPenilaianCheckListEntity);
                }
            }
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("checkListIndikator");
        logger.info("[SppdBoImpl.saveAdd] end process <<<");
        return null;
    }


    @Override
    public List<SmkCheckList> getByCriteria(SmkCheckList searchBean) throws GeneralBOException {
        logger.info("[SmkCheckListBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<SmkCheckList> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getCheckListId() != null && !"".equalsIgnoreCase(searchBean.getCheckListId())) {
                hsCriteria.put("checkListId", searchBean.getCheckListId());
            }

            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branchId", searchBean.getBranchId());
            }
            if (searchBean.getCheckListName() != null && !"".equalsIgnoreCase(searchBean.getCheckListName())) {
                hsCriteria.put("checkListName", searchBean.getCheckListName());
            }

            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                if ("N".equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchBean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }


            List<ImSmkCheckListEntity> imSmkCheckListBEntity = null;
            try {

                imSmkCheckListBEntity = smkCheckListDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[SmkCheckListBoImpl.getSearchSmkCheckListByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imSmkCheckListBEntity != null){
                SmkCheckList returnSmkCheckList;
                // Looping from dao to object and save in collection
                for(ImSmkCheckListEntity smkCheckListEntity : imSmkCheckListBEntity){
                    returnSmkCheckList = new SmkCheckList();
                    returnSmkCheckList.setCheckListId(smkCheckListEntity.getCheckListId());
                    returnSmkCheckList.setCheckListName(smkCheckListEntity.getCheckListName());
                    returnSmkCheckList.setBranchId(smkCheckListEntity.getBranchId());
                    returnSmkCheckList.setBranchName(smkCheckListEntity.getImBranches().getBranchName());

                    returnSmkCheckList.setCreatedWho(smkCheckListEntity.getCreatedWho());
                    returnSmkCheckList.setCreatedDate(smkCheckListEntity.getCreatedDate());
                    returnSmkCheckList.setLastUpdate(smkCheckListEntity.getLastUpdate());

                    returnSmkCheckList.setAction(smkCheckListEntity.getAction());
                    returnSmkCheckList.setFlag(smkCheckListEntity.getFlag());
                    listOfResult.add(returnSmkCheckList);
                }
            }
        }
        logger.info("[SmkCheckListBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

   /* public SmkCheckList saveAdd(SmkCheckList bean) throws GeneralBOException {

    }
*/
    @Override
    public List<SmkCheckList> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<SmkCheckList> getComboSmkCheckListWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<SmkCheckList> listComboSmkCheckListB = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImSmkCheckListEntity> listSmkCheckList = null;
        try {
            listSmkCheckList = smkCheckListDao.getListSmkCheckList(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listSmkCheckList != null) {
            for (ImSmkCheckListEntity imSmkCheckListBEntity : listSmkCheckList) {
                SmkCheckList itemComboSmkCheckListB = new SmkCheckList();
                itemComboSmkCheckListB.setCheckListId(imSmkCheckListBEntity.getCheckListId());
                listComboSmkCheckListB.add(itemComboSmkCheckListB);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboSmkCheckListB;
    }
}
