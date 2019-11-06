package com.neurix.hris.master.smkKategoriAspek.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.bo.SmkIndikatorPenilaianAspekBo;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.dao.SmkIndikatorPenilaianAspekDao;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.ImSmkIndikatorPenilaianAspekEntity;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.SmkIndikatorPenilaianAspek;
import com.neurix.hris.master.smkKategoriAspek.bo.SmkKategoriAspekBo;
import com.neurix.hris.master.smkKategoriAspek.dao.SmkKategoriAspekDao;
import com.neurix.hris.master.smkKategoriAspek.model.SmkKategoriAspek;
import com.neurix.hris.master.smkKategoriAspek.model.ImSmkKategoriAspekEntity;
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
public class SmkKategoriAspekBoImpl implements SmkKategoriAspekBo {

    protected static transient Logger logger = Logger.getLogger(SmkKategoriAspekBoImpl.class);
    private SmkKategoriAspekDao smkKategoriAspekDao;
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

    public SmkKategoriAspekDao getSmkKategoriAspekDao() {
        return smkKategoriAspekDao;
    }

    public void setSmkKategoriAspekDao(SmkKategoriAspekDao smkKategoriAspekDao) {
        this.smkKategoriAspekDao = smkKategoriAspekDao;
    }

    public SmkKategoriAspekDao getSmkKategoriAspekBDao() {
        return smkKategoriAspekDao;
    }

    public void setSmkKategoriAspekBDao(SmkKategoriAspekDao smkKategoriAspekDao) {
        this.smkKategoriAspekDao = smkKategoriAspekDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkKategoriAspekBoImpl.logger = logger;
    }

    public SmkKategoriAspekDao getSmkKategoriDao() {
        return smkKategoriAspekDao;
    }


    public void setSmkKategoriDao(SmkKategoriAspekDao smkKategoriAspekDao) {
        this.smkKategoriAspekDao = smkKategoriAspekDao;
    }

    @Override
    public void saveEdit(SmkKategoriAspek bean) throws GeneralBOException {
    }

    @Override
    public void saveDelete(SmkKategoriAspek bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {
            String smkKategoriId = bean.getKategoriAspekId();
            ImSmkKategoriAspekEntity imSmkKategoriAspekBEntity = null;

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
    public void saveEdit(SmkKategoriAspek bean, List<SmkIndikatorPenilaianAspek> data) throws GeneralBOException {
        logger.info("[SmkKategoriBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String historyId = "";
            String smkKategoriId = bean.getKategoriAspekId();

            ImSmkKategoriAspekEntity imSmkKategoriAspekBEntity = null;
            try {
                // Get data from database by ID
                imSmkKategoriAspekBEntity = smkKategoriAspekDao.getById("kategoriAspekId", smkKategoriId);
            } catch (HibernateException e) {
                logger.error("[SmkKategoriBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data SmkKategoriAspek by Kode SmkKategoriAspek, please inform to your admin...," + e.getMessage());
            }

            if (imSmkKategoriAspekBEntity != null) {

                imSmkKategoriAspekBEntity.setKategoriAspekId(bean.getKategoriAspekId());
                imSmkKategoriAspekBEntity.setTipeAspekId(bean.getTipeAspekId());
                imSmkKategoriAspekBEntity.setKategoriName(bean.getKategoriName());
                imSmkKategoriAspekBEntity.setBranchId(bean.getBranchId());
                imSmkKategoriAspekBEntity.setFlag(bean.getFlag());
                imSmkKategoriAspekBEntity.setAction(bean.getAction());
                imSmkKategoriAspekBEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkKategoriAspekBEntity.setLastUpdate(bean.getLastUpdate());

                List<SmkIndikatorPenilaianAspek> smkIndikatorPenilaianAspekList = new ArrayList();


                HttpSession session = ServletActionContext.getRequest().getSession();
                List<SmkIndikatorPenilaianAspek> listOfsearchSppdPerson = (List<SmkIndikatorPenilaianAspek>) session.getAttribute("kategoriAspekIndikator");

                if(smkIndikatorPenilaianAspekList != null){
                    for (SmkIndikatorPenilaianAspek smkIndikatorPenilaianAspek1Database: data) {
                        if(listOfsearchSppdPerson != null){
                            String flag = "N";
                            for (SmkIndikatorPenilaianAspek smkIndikatorPenilaianAspekSession: listOfsearchSppdPerson) {
                                if(smkIndikatorPenilaianAspek1Database.getIndikatorPenilaianAspekId().equals(smkIndikatorPenilaianAspekSession.getIndikatorPenilaianAspekId())){
                                    flag = "Y";
                                }
                            }
                            if(flag == "N"){
                                String smkIndikatorId = smkIndikatorPenilaianAspek1Database.getIndikatorPenilaianAspekId();
                                ImSmkIndikatorPenilaianAspekEntity imSmkIndikatorEntity = null;
                                try {
                                    imSmkIndikatorEntity = smkIndikatorPenilaianAspekDao.getById("indikatorPenilaianAspekId", smkIndikatorId);
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
                                        smkIndikatorPenilaianAspekDao.updateAndSave(imSmkIndikatorEntity);
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
                    for (SmkIndikatorPenilaianAspek smkIndikatorPenilaianAspekSession: listOfsearchSppdPerson) {
                        if(smkIndikatorPenilaianAspekSession.getStatus() != null){
                            ImSmkIndikatorPenilaianAspekEntity imSmkIndikatorPenilaianAspekEntity = new ImSmkIndikatorPenilaianAspekEntity();

                            imSmkIndikatorPenilaianAspekEntity.setIndikatorPenilaianAspekId(smkIndikatorPenilaianAspekSession.getIndikatorPenilaianAspekId());
                            imSmkIndikatorPenilaianAspekEntity.setKategoriAspekId(smkKategoriId);
                            imSmkIndikatorPenilaianAspekEntity.setIndikatorName(smkIndikatorPenilaianAspekSession.getIndikatorName());
                            imSmkIndikatorPenilaianAspekEntity.setBobot(smkIndikatorPenilaianAspekSession.getBobot());
                            imSmkIndikatorPenilaianAspekEntity.setFlag(bean.getFlag());
                            imSmkIndikatorPenilaianAspekEntity.setAction(bean.getAction());
                            imSmkIndikatorPenilaianAspekEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            imSmkIndikatorPenilaianAspekEntity.setLastUpdate(bean.getLastUpdate());
                            smkIndikatorPenilaianAspekDao.addAndSave(imSmkIndikatorPenilaianAspekEntity);
                        }
                    }
                }

                try {
                    // Update into database
                    smkKategoriAspekDao.updateAndSave(imSmkKategoriAspekBEntity);
                } catch (HibernateException e) {
                    logger.error("[SmkKategoriBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkKategoriAspek, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SmkKategoriBoImpl.saveEdit] Error, not found data SmkKategoriAspek with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkKategoriAspek with request id, please check again your data ...");
            }
        }
        logger.info("[SmkKategoriBoImpl.saveEdit] end process <<<");
    }

    @Override
    public String getIdIndikator() throws GeneralBOException {
        String id = smkIndikatorPenilaianAspekDao.getNextSmkIndikatorId();
        return id;
    }

    @Override
    public SmkKategoriAspek saveAdd(SmkKategoriAspek bean) throws GeneralBOException {
        logger.info("[SmkKategoriBoImpl.saveAdd] start process >>>");

        logger.info("[SppdBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String smkKategoriId;
            String smkIndikatorId;
            try {
                // Generating ID, get from postgre sequence
                smkKategoriId = smkKategoriAspekDao.getNextSmkKategoriId();
            } catch (HibernateException e) {
                logger.error("[SppdBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence sppdId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImSmkKategoriAspekEntity imSmkKategoriAspekEntity = new ImSmkKategoriAspekEntity();

            imSmkKategoriAspekEntity.setKategoriAspekId(smkKategoriId);
            imSmkKategoriAspekEntity.setKategoriName(bean.getKategoriName());
            imSmkKategoriAspekEntity.setBranchId(bean.getBranchId());
            imSmkKategoriAspekEntity.setTipeAspekId(bean.getTipeAspekId());

            imSmkKategoriAspekEntity.setFlag(bean.getFlag());
            imSmkKategoriAspekEntity.setAction(bean.getAction());
            imSmkKategoriAspekEntity.setCreatedWho(bean.getCreatedWho());
            imSmkKategoriAspekEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imSmkKategoriAspekEntity.setCreatedDate(bean.getCreatedDate());
            imSmkKategoriAspekEntity.setLastUpdate(bean.getLastUpdate());
            try {
                smkKategoriAspekDao.addAndSave(imSmkKategoriAspekEntity);
            } catch (HibernateException e) {
                logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
            }

            HttpSession session = ServletActionContext.getRequest().getSession();
            List<SmkIndikatorPenilaianAspek> listOfsearchSppdPerson = (List<SmkIndikatorPenilaianAspek>) session.getAttribute("kategoriAspekIndikator");

            if(listOfsearchSppdPerson != null){
                for (SmkIndikatorPenilaianAspek smkIndikatorPenilaianAspek: listOfsearchSppdPerson) {
                    smkIndikatorId = smkIndikatorPenilaianAspekDao.getNextSmkIndikatorId();
                    ImSmkIndikatorPenilaianAspekEntity imSmkIndikatorPenilaianAspekEntity = new ImSmkIndikatorPenilaianAspekEntity();

                    imSmkIndikatorPenilaianAspekEntity.setIndikatorPenilaianAspekId(smkIndikatorId);
                    imSmkIndikatorPenilaianAspekEntity.setKategoriAspekId(smkKategoriId);
                    imSmkIndikatorPenilaianAspekEntity.setIndikatorName(smkIndikatorPenilaianAspek.getIndikatorName());
                    imSmkIndikatorPenilaianAspekEntity.setBobot(smkIndikatorPenilaianAspek.getBobot());
                    imSmkIndikatorPenilaianAspekEntity.setFlag(bean.getFlag());
                    imSmkIndikatorPenilaianAspekEntity.setAction(bean.getAction());
                    imSmkIndikatorPenilaianAspekEntity.setCreatedWho(bean.getCreatedWho());
                    imSmkIndikatorPenilaianAspekEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imSmkIndikatorPenilaianAspekEntity.setCreatedDate(bean.getCreatedDate());
                    imSmkIndikatorPenilaianAspekEntity.setLastUpdate(bean.getLastUpdate());
                    smkIndikatorPenilaianAspekDao.addAndSave(imSmkIndikatorPenilaianAspekEntity);
                }
            }
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultSppdPerson");
        logger.info("[SppdBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<SmkKategoriAspek> getByCriteria(SmkKategoriAspek searchBean) throws GeneralBOException {
        logger.info("[SmkKategoriBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<SmkKategoriAspek> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getKategoriAspekId() != null && !"".equalsIgnoreCase(searchBean.getKategoriAspekId())) {
                hsCriteria.put("kategoriAspekId", searchBean.getKategoriAspekId());
            }
            if (searchBean.getTipeAspekId() != null && !"".equalsIgnoreCase(searchBean.getTipeAspekId())) {
                hsCriteria.put("tipeAspekId", searchBean.getTipeAspekId());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branchId", searchBean.getBranchId());
            }
            if (searchBean.getKategoriName() != null && !"".equalsIgnoreCase(searchBean.getKategoriName())) {
                hsCriteria.put("kategoriName", searchBean.getKategoriName());
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


            List<ImSmkKategoriAspekEntity> imSmkKategoriAspekBEntity = null;
            try {

                imSmkKategoriAspekBEntity = smkKategoriAspekDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[SmkKategoriBoImpl.getSearchSmkKategoriByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imSmkKategoriAspekBEntity != null){
                SmkKategoriAspek returnSmkKategoriAspek;
                // Looping from dao to object and save in collection
                for(ImSmkKategoriAspekEntity smkKategoriEntity : imSmkKategoriAspekBEntity){
                    returnSmkKategoriAspek = new SmkKategoriAspek();
                    returnSmkKategoriAspek.setKategoriAspekId(smkKategoriEntity.getKategoriAspekId());
                    returnSmkKategoriAspek.setKategoriName(smkKategoriEntity.getKategoriName());
                    returnSmkKategoriAspek.setBranchId(smkKategoriEntity.getBranchId());
                    returnSmkKategoriAspek.setTipeAspekId(smkKategoriEntity.getTipeAspekId());
                    returnSmkKategoriAspek.setTipeAspekName(smkKategoriEntity.getImTipeAspekEntity().getTipeAspekName());
                    returnSmkKategoriAspek.setBranchName(smkKategoriEntity.getImBranches().getBranchName());

                    returnSmkKategoriAspek.setCreatedWho(smkKategoriEntity.getCreatedWho());
                    returnSmkKategoriAspek.setCreatedDate(smkKategoriEntity.getCreatedDate());
                    returnSmkKategoriAspek.setLastUpdate(smkKategoriEntity.getLastUpdate());

                    returnSmkKategoriAspek.setAction(smkKategoriEntity.getAction());
                    returnSmkKategoriAspek.setFlag(smkKategoriEntity.getFlag());
                    listOfResult.add(returnSmkKategoriAspek);
                }
            }
        }
        logger.info("[SmkKategoriBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

   /* public SmkKategoriAspek saveAdd(SmkKategoriAspek bean) throws GeneralBOException {

    }
*/
    @Override
    public List<SmkKategoriAspek> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<SmkKategoriAspek> getComboSmkKategoriWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<SmkKategoriAspek> listComboSmkKategoriAspekB = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImSmkKategoriAspekEntity> listSmkKategori = null;
        try {
            listSmkKategori = smkKategoriAspekDao.getListSmkKategori(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listSmkKategori != null) {
            for (ImSmkKategoriAspekEntity imSmkKategoriAspekBEntity : listSmkKategori) {
                SmkKategoriAspek itemComboSmkKategoriAspekB = new SmkKategoriAspek();
                itemComboSmkKategoriAspekB.setKategoriAspekId(imSmkKategoriAspekBEntity.getKategoriAspekId());
                listComboSmkKategoriAspekB.add(itemComboSmkKategoriAspekB);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboSmkKategoriAspekB;
    }
}
