package com.neurix.hris.master.cuti.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.cuti.bo.CutiBo;
import com.neurix.hris.master.cuti.dao.CutiDao;
import com.neurix.hris.master.cuti.model.Cuti;
import com.neurix.hris.master.cuti.model.ImCutiEntity;
import com.neurix.hris.master.cuti.model.ImCutiHistoryEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

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
public class CutiBoImpl implements CutiBo {

    protected static transient Logger logger = Logger.getLogger(CutiBoImpl.class);
    private CutiDao cutiDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        CutiBoImpl.logger = logger;
    }

    public CutiDao getCutiDao() {
        return cutiDao;
    }

    public void setCutiDao(CutiDao cutiDao) {
        this.cutiDao = cutiDao;
    }

    @Override
    public void saveDelete(Cuti bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String cutiId = bean.getCutiId();

            ImCutiEntity imCutiEntity = null;
            try {
                // Get data from database by ID
                imCutiEntity = cutiDao.getById("cutiId", cutiId);
            } catch (HibernateException e) {
                logger.error("[CutiBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imCutiEntity != null) {

                // Modify from bean to entity serializable
                imCutiEntity.setCutiId(bean.getCutiId());
                imCutiEntity.setCutiName(bean.getCutiName());
                imCutiEntity.setJumlahCuti(bean.getJumlahCuti());
                imCutiEntity.setFlag(bean.getFlag());
                imCutiEntity.setAction(bean.getAction());
                imCutiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imCutiEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    cutiDao.updateAndSave(imCutiEntity);
                } catch (HibernateException e) {
                    logger.error("[CutiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Cuti, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[CutiBoImpl.saveDelete] Error, not found data Cuti with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Cuti with request id, please check again your data ...");

            }
        }
        logger.info("[CutiBoImpl.saveDelete] end process <<<");
    }

    @Override
    public Cuti findCutiByIdcuti(String id) throws GeneralBOException {
        logger.info("[CutiBoImpl.findCutiByIdcuti] start process >>>");

        Cuti cuti = null;

        if (id != null) {

            ImCutiEntity imCutiEntity = null;
            try {

                imCutiEntity = cutiDao.getById("cutiId", id, "Y");
            } catch (HibernateException e) {
                logger.error("[CutiBoImpl.findCutiByIdcuti] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imCutiEntity != null){
                cuti = new Cuti();
                cuti.setCutiId(imCutiEntity.getCutiId());
                cuti.setCutiName(imCutiEntity.getCutiName());
                cuti.setJumlahCuti(imCutiEntity.getJumlahCuti());
                cuti.setTipeHari(imCutiEntity.getTipeHari());
            }
        }
        logger.info("[CutiBoImpl.findCutiByIdcuti] end process <<<");
        return cuti;
    }

    @Override
    public void saveEdit(Cuti bean) throws GeneralBOException {
        logger.info("[CutiBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String cutiId = bean.getCutiId();
            ImCutiEntity imCutiEntity = null;
            String idHistory = "";
            try {
                // Get data from database by ID
                imCutiEntity = cutiDao.getById("cutiId", cutiId);
                idHistory = cutiDao.getNextCutiHistoryId();
            } catch (HibernateException e) {
                logger.error("[CutiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data Cuti by Kode Cuti, please inform to your admin...," + e.getMessage());
            }

            if (imCutiEntity != null) {
                ImCutiHistoryEntity imCutiHistoryEntity = new ImCutiHistoryEntity();

                imCutiHistoryEntity.setId(idHistory);
                imCutiHistoryEntity.setCutiId(imCutiEntity.getCutiId());
                imCutiHistoryEntity.setCutiName(imCutiEntity.getCutiName());
                imCutiHistoryEntity.setJumlahCuti(imCutiEntity.getJumlahCuti());
                imCutiHistoryEntity.setTipeHari(imCutiEntity.getTipeHari());
                imCutiHistoryEntity.setGolonganId(imCutiEntity.getGolonganId());
                imCutiHistoryEntity.setBranchId(imCutiEntity.getBranchId());

                imCutiHistoryEntity.setFlag(imCutiEntity.getFlag());
                imCutiHistoryEntity.setAction(imCutiEntity.getAction());
                imCutiHistoryEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imCutiHistoryEntity.setLastUpdate(bean.getLastUpdate());
                imCutiHistoryEntity.setCreatedWho(imCutiEntity.getLastUpdateWho());
                imCutiHistoryEntity.setCreatedDate(imCutiEntity.getLastUpdate());

                imCutiEntity.setCutiId(bean.getCutiId());
                imCutiEntity.setTipeHari(bean.getTipeHari());
                imCutiEntity.setCutiName(bean.getCutiName());
                imCutiEntity.setJumlahCuti(bean.getJumlahCuti());
                imCutiEntity.setGolonganId(bean.getGolonganId());
                imCutiEntity.setBranchId(bean.getBranchId());
                imCutiEntity.setTipeHari(bean.getTipeHari());

                imCutiEntity.setFlag(bean.getFlag());
                imCutiEntity.setAction(bean.getAction());
                imCutiEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imCutiEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    cutiDao.updateAndSave(imCutiEntity);
                    cutiDao.addAndSaveHistory(imCutiHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[CutiBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Cuti, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[CutiBoImpl.saveEdit] Error, not found data Cuti with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Cuti with request id, please check again your data ...");
//                condition = "Error, not found data Cuti with request id, please check again your data ...";
            }
        }
        logger.info("[CutiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Cuti saveAdd(Cuti bean) throws GeneralBOException {
        logger.info("[CutiBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String cutiId;
            try {
                // Generating ID, get from postgre sequence
                cutiId = cutiDao.getNextCutiId();
            } catch (HibernateException e) {
                logger.error("[CutiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence cutiId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImCutiEntity imCutiEntity = new ImCutiEntity();

            imCutiEntity.setCutiId(cutiId);
            imCutiEntity.setCutiName(bean.getCutiName());
            imCutiEntity.setJumlahCuti(bean.getJumlahCuti());
            imCutiEntity.setTipeHari(bean.getTipeHari());
            imCutiEntity.setBranchId(bean.getBranchId());
            imCutiEntity.setGolonganId(bean.getGolonganId());
            imCutiEntity.setFlag(bean.getFlag());
            imCutiEntity.setAction(bean.getAction());
            imCutiEntity.setCreatedWho(bean.getCreatedWho());
            imCutiEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imCutiEntity.setCreatedDate(bean.getCreatedDate());
            imCutiEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                cutiDao.addAndSave(imCutiEntity);
            } catch (HibernateException e) {
                logger.error("[CutiBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data Cuti, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[CutiBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Cuti> getByCriteria(Cuti searchBean) throws GeneralBOException {
        logger.info("[CutiBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<Cuti> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getCutiId() != null && !"".equalsIgnoreCase(searchBean.getCutiId())) {
                hsCriteria.put("cuti_id", searchBean.getCutiId());
            }
            if (searchBean.getCutiName() != null && !"".equalsIgnoreCase(searchBean.getCutiName())) {
                hsCriteria.put("cuti_name", searchBean.getCutiName());
            }

            if (searchBean.getTipeHari() != null && !"".equalsIgnoreCase(searchBean.getTipeHari())) {
                hsCriteria.put("tipe_hari", searchBean.getTipeHari());
            }

            if (searchBean.getGolonganId() != null && !"".equalsIgnoreCase(searchBean.getGolonganId())) {
                hsCriteria.put("golongan_id", searchBean.getGolonganId());
            }

            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }

            if (searchBean.getJenisCuti() != null && !"".equalsIgnoreCase(searchBean.getJenisCuti())){
                hsCriteria.put("jenis_cuti", searchBean.getJenisCuti());
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


            List<ImCutiEntity> imCutiEntity = null;
            try {

                imCutiEntity = cutiDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[CutiBoImpl.getSearchCutiByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imCutiEntity != null){
                Cuti returnCuti;
                // Looping from dao to object and save in collection
                for(ImCutiEntity cutiEntity : imCutiEntity){
                    returnCuti = new Cuti();
                    returnCuti.setCutiId(cutiEntity.getCutiId());
                    returnCuti.setCutiName(cutiEntity.getCutiName());
                    returnCuti.setJumlahCuti(cutiEntity.getJumlahCuti());
                    returnCuti.setTipeHari(cutiEntity.getTipeHari());
                    returnCuti.setGolonganId(cutiEntity.getGolonganId());
                    returnCuti.setBranchId(cutiEntity.getBranchId());
                    if (cutiEntity.getBranchId().equalsIgnoreCase("")){
                        returnCuti.setBranchName("");
                    }
                    else {
                        returnCuti.setBranchName(cutiEntity.getImBranch().getBranchName());
                    }
                    if (cutiEntity.getGolonganId().equalsIgnoreCase("")){
                        returnCuti.setGolonganName("");
                    }
                    else{
                        returnCuti.setGolonganName(cutiEntity.getImGolongan().getGolonganName());
                    }
                    returnCuti.setBranchId(cutiEntity.getBranchId());

                    returnCuti.setCreatedWho(cutiEntity.getCreatedWho());
                    returnCuti.setCreatedDate(cutiEntity.getCreatedDate());
                    returnCuti.setLastUpdate(cutiEntity.getLastUpdate());

                    returnCuti.setAction(cutiEntity.getAction());
                    returnCuti.setFlag(cutiEntity.getFlag());
                    listOfResult.add(returnCuti);
                }
            }
        }
        logger.info("[CutiBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<Cuti> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<Cuti> getComboCutiWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Cuti> listComboCuti = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImCutiEntity> listCuti = null;
        try {
            listCuti = cutiDao.getListCuti(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listCuti != null) {
            for (ImCutiEntity imCutiEntity : listCuti) {
                Cuti itemComboCuti = new Cuti();
                itemComboCuti.setCutiId(imCutiEntity.getCutiId());
                itemComboCuti.setCutiName(imCutiEntity.getCutiName());
                listComboCuti.add(itemComboCuti);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboCuti;
    }

    public List<Cuti> getComboCutiTipeWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Cuti> listComboCutiTipe = new ArrayList();
        String criteria = query ;

        List<ImCutiEntity> listCutiTipe = null;
        try {
            listCutiTipe = cutiDao.getListCutiTipe(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listCutiTipe != null) {
            for (ImCutiEntity imCutiTipeEntity : listCutiTipe) {
                Cuti itemComboCutiTipe = new Cuti();
                itemComboCutiTipe.setCutiId(imCutiTipeEntity.getCutiId());
                itemComboCutiTipe.setCutiName(imCutiTipeEntity.getCutiName());
                itemComboCutiTipe.setTipeHari(imCutiTipeEntity.getTipeHari());
                itemComboCutiTipe.setJumlahCuti(imCutiTipeEntity.getJumlahCuti());
                listComboCutiTipe.add(itemComboCutiTipe);
            }
        }
        else {
            for (ImCutiEntity imCutiTipeEntity : listCutiTipe) {
                Cuti itemComboCutiTipe = new Cuti();
                itemComboCutiTipe.setCutiId(null);
                itemComboCutiTipe.setCutiName(null);
                itemComboCutiTipe.setTipeHari(null);
                itemComboCutiTipe.setJumlahCuti(null);
                listComboCutiTipe.add(itemComboCutiTipe);

            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboCutiTipe;
    }
}
