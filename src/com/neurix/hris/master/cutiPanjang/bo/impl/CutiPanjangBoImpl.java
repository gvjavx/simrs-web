package com.neurix.hris.master.cutiPanjang.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.cutiPanjang.bo.CutiPanjangBo;
import com.neurix.hris.master.cutiPanjang.dao.CutiPanjangDao;
import com.neurix.hris.master.cutiPanjang.model.CutiPanjang;
import com.neurix.hris.master.cutiPanjang.model.ImCutiPanjangEntity;
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
public class CutiPanjangBoImpl implements CutiPanjangBo {

    protected static transient Logger logger = Logger.getLogger(CutiPanjangBoImpl.class);
    private CutiPanjangDao cutiPanjangDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        CutiPanjangBoImpl.logger = logger;
    }

    public CutiPanjangDao getCutiPanjangDao() {
        return cutiPanjangDao;
    }

    public void setCutiPanjangDao(CutiPanjangDao cutiPanjangDao) {
        this.cutiPanjangDao = cutiPanjangDao;
    }

    @Override
    public void saveDelete(CutiPanjang bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String cutiPanjangId = bean.getCutiPanjangId();

            ImCutiPanjangEntity imCutiPanjangEntity = null;

            try {
                // Get data from database by ID
                imCutiPanjangEntity = cutiPanjangDao.getById("cutiPanjangId", cutiPanjangId);
            } catch (HibernateException e) {
                logger.error("[CutiPanjangBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imCutiPanjangEntity != null) {

                // Modify from bean to entity serializable
                imCutiPanjangEntity.setFlag(bean.getFlag());
                imCutiPanjangEntity.setAction(bean.getAction());
                imCutiPanjangEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imCutiPanjangEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    cutiPanjangDao.updateAndSave(imCutiPanjangEntity);
                } catch (HibernateException e) {
                    logger.error("[CutiPanjangBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data CutiPanjang, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[CutiPanjangBoImpl.saveDelete] Error, not found data CutiPanjang with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data CutiPanjang with request id, please check again your data ...");

            }
        }
        logger.info("[CutiPanjangBoImpl.saveDelete] end process <<<");
    }

    @Override
    public List<CutiPanjang> getComboCutiPanjangWithCriteria(String query) throws GeneralBOException {
        return null;
    }

    @Override
    public void saveEdit(CutiPanjang bean) throws GeneralBOException {
        logger.info("[CutiPanjangBoImpl.saveEdit] start process >>>");

        if (bean!=null) {
            String historyId = "";
            String cutiPanjangId = bean.getCutiPanjangId();

            ImCutiPanjangEntity imCutiPanjangEntity = null;

            try {
                // Get data from database by ID
                imCutiPanjangEntity = cutiPanjangDao.getById("cutiPanjangId", cutiPanjangId);

            } catch (HibernateException e) {
                logger.error("[CutiPanjangBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data CutiPanjang by Kode CutiPanjang, please inform to your admin...," + e.getMessage());
            }

            if (imCutiPanjangEntity != null) {


                imCutiPanjangEntity.setCutiPanjangId(bean.getCutiPanjangId());
                imCutiPanjangEntity.setJumlahCuti(bean.getJumlahCuti());
                imCutiPanjangEntity.setTipeHari(bean.getTipeHari());
                imCutiPanjangEntity.setGolonganId(bean.getGolonganId());
                imCutiPanjangEntity.setBranchId(bean.getBranchId());

                imCutiPanjangEntity.setFlag(bean.getFlag());
                imCutiPanjangEntity.setAction(bean.getAction());
                imCutiPanjangEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imCutiPanjangEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    cutiPanjangDao.updateAndSave(imCutiPanjangEntity);

                } catch (HibernateException e) {
                    logger.error("[CutiPanjangBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data CutiPanjang, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[CutiPanjangBoImpl.saveEdit] Error, not found data CutiPanjang with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data CutiPanjang with request id, please check again your data ...");
            }
        }
        logger.info("[CutiPanjangBoImpl.saveEdit] end process <<<");
    }

    @Override
    public CutiPanjang saveAdd(CutiPanjang bean) throws GeneralBOException {
        logger.info("[CutiPanjangBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String cutiPanjangId;
            try {
                // Generating ID, get from postgre sequence
                cutiPanjangId = cutiPanjangDao.getNextCutiPanjangId();
            } catch (HibernateException e) {
                logger.error("[CutiPanjangBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence cutiPanjangId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImCutiPanjangEntity imCutiPanjangEntity = new ImCutiPanjangEntity();

            imCutiPanjangEntity.setCutiPanjangId(cutiPanjangId);
            imCutiPanjangEntity.setGolonganId(bean.getGolonganId());
            imCutiPanjangEntity.setBranchId(bean.getBranchId());
            imCutiPanjangEntity.setTipeHari(bean.getTipeHari());
            imCutiPanjangEntity.setJumlahCuti(bean.getJumlahCuti());

            imCutiPanjangEntity.setFlag(bean.getFlag());
            imCutiPanjangEntity.setAction(bean.getAction());
            imCutiPanjangEntity.setCreatedWho(bean.getCreatedWho());
            imCutiPanjangEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imCutiPanjangEntity.setCreatedDate(bean.getCreatedDate());
            imCutiPanjangEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                cutiPanjangDao.addAndSave(imCutiPanjangEntity);
            } catch (HibernateException e) {
                logger.error("[CutiPanjangBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data CutiPanjang, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[CutiPanjangBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<CutiPanjang> getByCriteria(CutiPanjang searchBean) throws GeneralBOException {
        logger.info("[CutiPanjangBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<CutiPanjang> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getCutiPanjangId() != null && !"".equalsIgnoreCase(searchBean.getCutiPanjangId())) {
                hsCriteria.put("cutiPanjang_id", searchBean.getCutiPanjangId());
            }
            if (searchBean.getGolonganId() != null && !"".equalsIgnoreCase(searchBean.getGolonganId())) {
                hsCriteria.put("golongan_id", searchBean.getGolonganId());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getTipeHari() != null && !"".equalsIgnoreCase(searchBean.getTipeHari())) {
                hsCriteria.put("tipe_hari", searchBean.getTipeHari());
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


            List<ImCutiPanjangEntity> imCutiPanjangEntity = null;
            try {

                imCutiPanjangEntity = cutiPanjangDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[CutiPanjangBoImpl.getSearchCutiPanjangByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imCutiPanjangEntity != null){
                CutiPanjang returnCutiPanjang;
                // Looping from dao to object and save in collection
                for(ImCutiPanjangEntity cutiPanjangEntity : imCutiPanjangEntity){
                    returnCutiPanjang = new CutiPanjang();
                    returnCutiPanjang.setCutiPanjangId(cutiPanjangEntity.getCutiPanjangId());
                    returnCutiPanjang.setBranchId(cutiPanjangEntity.getBranchId());
                    returnCutiPanjang.setGolonganId(cutiPanjangEntity.getGolonganId());
                    returnCutiPanjang.setTipeHari(cutiPanjangEntity.getTipeHari());
                    returnCutiPanjang.setJumlahCuti(cutiPanjangEntity.getJumlahCuti());

                    returnCutiPanjang.setCreatedWho(cutiPanjangEntity.getCreatedWho());
                    returnCutiPanjang.setCreatedDate(cutiPanjangEntity.getCreatedDate());
                    returnCutiPanjang.setLastUpdate(cutiPanjangEntity.getLastUpdate());
                    returnCutiPanjang.setAction(cutiPanjangEntity.getAction());
                    returnCutiPanjang.setFlag(cutiPanjangEntity.getFlag());
                    listOfResult.add(returnCutiPanjang);
                }
            }
        }
        logger.info("[CutiPanjangBoImpl.getByCriteria] end process <<<");
//return null;
        return listOfResult;
    }

    @Override
    public List<CutiPanjang> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

/*    public List<CutiPanjang> getComboCutiPanjangWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<CutiPanjang> listComboCutiPanjang = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImCutiPanjangEntity> listCutiPanjang = null;
        try {
            listCutiPanjang = cutiPanjangDao.getListCutiPanjang(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listCutiPanjang != null) {
            for (ImCutiPanjangEntity imCutiPanjangEntity : listCutiPanjang) {
                CutiPanjang itemComboCutiPanjang = new CutiPanjang();
                itemComboCutiPanjang.setCutiPanjangId(imCutiPanjangEntity.getCutiPanjangId());
                itemComboCutiPanjang.setCutiPanjangName(imCutiPanjangEntity.getCutiPanjangName());
                listComboCutiPanjang.add(itemComboCutiPanjang);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboCutiPanjang;
    }*/
}
