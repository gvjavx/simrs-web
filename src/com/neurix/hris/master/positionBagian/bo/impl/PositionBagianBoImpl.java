package com.neurix.hris.master.positionBagian.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.positionBagian.bo.PositionBagianBo;
import com.neurix.hris.master.positionBagian.dao.PositionBagianDao;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianEntity;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianHistoryEntity;
import com.neurix.hris.master.positionBagian.model.positionBagian;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
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
public class PositionBagianBoImpl implements PositionBagianBo {

    protected static transient Logger logger = Logger.getLogger(PositionBagianBoImpl.class);
    private PositionBagianDao positionBagianDao;
    private DepartmentDao departmentDao;

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PositionBagianBoImpl.logger = logger;
    }

    public PositionBagianDao getPositionBagianDao() {
        return positionBagianDao;
    }

    public void setPositionBagianDao(PositionBagianDao positionBagianDao) {
        this.positionBagianDao = positionBagianDao;
    }

    @Override
    public void saveDelete(positionBagian bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String kelompokPositionId = bean.getBagianId();
            String idHistory = "";
            ImPositionBagianEntity imPositionBagianEntity = null;
            ImPositionBagianHistoryEntity imPositionBagianHistoryEntity = new ImPositionBagianHistoryEntity();
            try {
                // Get data from database by ID
                imPositionBagianEntity = positionBagianDao.getById("bagianId", kelompokPositionId);
                idHistory = positionBagianDao.getNextPositionBagianHistoryId();
            } catch (HibernateException e) {
                logger.error("[PositionBagianBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imPositionBagianEntity != null) {
                imPositionBagianHistoryEntity.setIdHistory(idHistory);
                imPositionBagianHistoryEntity.setBagianId(imPositionBagianEntity.getBagianId());
                imPositionBagianHistoryEntity.setBagianName(imPositionBagianEntity.getBagianName());
                imPositionBagianHistoryEntity.setBranchId(imPositionBagianEntity.getBranchId());
//                    imPositionBagianHistoryEntity.setDivisiId(imPositionBagianEntity.getDivisiId());
                imPositionBagianHistoryEntity.setKodering(imPositionBagianEntity.getKodering());
                imPositionBagianHistoryEntity.setFlag(imPositionBagianEntity.getFlag());
                imPositionBagianHistoryEntity.setAction(imPositionBagianEntity.getAction());
                imPositionBagianHistoryEntity.setLastUpdateWho(imPositionBagianEntity.getLastUpdateWho());
                imPositionBagianHistoryEntity.setLastUpdate(imPositionBagianEntity.getLastUpdate());
                imPositionBagianHistoryEntity.setCreatedWho(imPositionBagianEntity.getCreatedWho());
                imPositionBagianHistoryEntity.setCreatedDate(imPositionBagianEntity.getCreatedDate());

                // Modify from bean to entity serializable
                imPositionBagianEntity.setFlag(bean.getFlag());
                imPositionBagianEntity.setAction(bean.getAction());
                imPositionBagianEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPositionBagianEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    positionBagianDao.updateAndSave(imPositionBagianEntity);
                    positionBagianDao.addAndSaveHistory(imPositionBagianHistoryEntity);
                } catch (HibernateException e) {
                    logger.error("[PositionBagianBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PositionBagian, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[PositionBagianBoImpl.saveDelete] Error, not found data PositionBagian with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PositionBagian with request id, please check again your data ...");

            }
        }
        logger.info("[PositionBagianBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(positionBagian bean) throws GeneralBOException {
        logger.info("[PositionBagianBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {

            String status = cekStatusEdit(bean.getBagianName());
            if (!status.equalsIgnoreCase("exist")){
                String kelompokPositionId = bean.getBagianId();
                String idHistory = "";
                ImPositionBagianEntity imPositionBagianEntity = null;
                ImPositionBagianHistoryEntity imPositionBagianHistoryEntity = new ImPositionBagianHistoryEntity();
                try {
                    // Get data from database by ID
                    imPositionBagianEntity = positionBagianDao.getById("bagianId", kelompokPositionId);
                    idHistory = positionBagianDao.getNextPositionBagianHistoryId();
                } catch (HibernateException e) {
                    logger.error("[PositionBagianBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data PositionBagian by Kode PositionBagian, please inform to your admin...," + e.getMessage());
                }

                if (imPositionBagianEntity != null) {
                    imPositionBagianHistoryEntity.setIdHistory(idHistory);
                    imPositionBagianHistoryEntity.setBagianId(imPositionBagianEntity.getBagianId());
                    imPositionBagianHistoryEntity.setBagianName(imPositionBagianEntity.getBagianName());
                    imPositionBagianHistoryEntity.setBranchId(imPositionBagianEntity.getBranchId());
//                    imPositionBagianHistoryEntity.setDivisiId(imPositionBagianEntity.getDivisiId());
                    imPositionBagianHistoryEntity.setKodering(imPositionBagianEntity.getKodering());
                    imPositionBagianHistoryEntity.setFlag(imPositionBagianEntity.getFlag());
                    imPositionBagianHistoryEntity.setAction(imPositionBagianEntity.getAction());
                    imPositionBagianHistoryEntity.setLastUpdateWho(imPositionBagianEntity.getLastUpdateWho());
                    imPositionBagianHistoryEntity.setLastUpdate(imPositionBagianEntity.getLastUpdate());
                    imPositionBagianHistoryEntity.setCreatedWho(imPositionBagianEntity.getCreatedWho());
                    imPositionBagianHistoryEntity.setCreatedDate(imPositionBagianEntity.getCreatedDate());

                    imPositionBagianEntity.setBagianName(bean.getBagianName());
                    imPositionBagianEntity.setFlag(bean.getFlag());
                    imPositionBagianEntity.setAction(bean.getAction());
                    imPositionBagianEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imPositionBagianEntity.setLastUpdate(bean.getLastUpdate());

                    String flag;
                    try {
                        // Update into database
                        positionBagianDao.updateAndSave(imPositionBagianEntity);
                        positionBagianDao.addAndSaveHistory(imPositionBagianHistoryEntity);
                    } catch (HibernateException e) {
                        logger.error("[PositionBagianBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data PositionBagian, please info to your admin..." + e.getMessage());
                    }
                } else {
                    logger.error("[PositionBagianBoImpl.saveEdit] Error, not found data PositionBagian with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data PositionBagian with request id, please check again your data ...");
//                condition = "Error, not found data PositionBagian with request id, please check again your data ...";
                }
            }else {
                throw new GeneralBOException("Maaf Data Tersebut Sudah Ada");
            }
        }
        logger.info("[PositionBagianBoImpl.saveEdit] end process <<<");
    }

    @Override
    public positionBagian saveAdd(positionBagian bean) throws GeneralBOException {
        logger.info("[PositionBagianBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            String status = cekStatus(bean.getBagianName());
            if (!status.equalsIgnoreCase("Exist")){
                String kelompokPositionId = "";
                try {
                    // Generating ID, get from postgre sequence
                    kelompokPositionId = positionBagianDao.getNextPositioBagianId();
                } catch (HibernateException e) {
                    logger.error("[PositionBagianBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence kelompokPositionId id, please info to your admin..." + e.getMessage());
                }

                // creating object entity serializable
                ImPositionBagianEntity imPositionBagianEntity = new ImPositionBagianEntity();

                imPositionBagianEntity.setBagianId(kelompokPositionId);
                imPositionBagianEntity.setBagianName(bean.getBagianName());
                imPositionBagianEntity.setDivisiId(bean.getDivisiId());
                ImDepartmentEntity departmentEntity = departmentDao.getById("departmentId",bean.getDivisiId());
                List<ImPositionBagianEntity> positionBagianEntityList= positionBagianDao.getListPositionBagianByDivisi(bean.getDivisiId());
                String sId = String.format("%02d", positionBagianEntityList.size()+1);

                imPositionBagianEntity.setKodering(departmentEntity.getKodering()+"."+sId);
                imPositionBagianEntity.setFlag(bean.getFlag());
                imPositionBagianEntity.setAction(bean.getAction());
                imPositionBagianEntity.setCreatedWho(bean.getCreatedWho());
                imPositionBagianEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imPositionBagianEntity.setCreatedDate(bean.getCreatedDate());
                imPositionBagianEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    positionBagianDao.addAndSave(imPositionBagianEntity);
                } catch (HibernateException e) {
                    logger.error("[PositionBagianBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PositionBagian, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Nama Bagian atau Kodering Tersebut Sudah Ada");
            }

        }

        logger.info("[PositionBagianBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<positionBagian> getByCriteria(positionBagian searchBean) throws GeneralBOException {
        logger.info("[PositionBagianBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<positionBagian> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getBagianId() != null && !"".equalsIgnoreCase(searchBean.getBagianId())) {
                hsCriteria.put("bagian_id", searchBean.getBagianId());
            }
            if (searchBean.getBagianName() != null && !"".equalsIgnoreCase(searchBean.getBagianName())) {
                hsCriteria.put("bagian_name", searchBean.getBagianName());
            }
            if (searchBean.getDivisiId() != null && !"".equalsIgnoreCase(searchBean.getDivisiId())) {
                hsCriteria.put("divisi_id", searchBean.getDivisiId());
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


            List<ImPositionBagianEntity> imPositionBagianEntity = null;
            try {

                imPositionBagianEntity = positionBagianDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PositionBagianBoImpl.getSearchPositionBagianByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imPositionBagianEntity != null){
                positionBagian returnPositionBagian;
                // Looping from dao to object and save in collection
                for(ImPositionBagianEntity kelompokPositionEntity : imPositionBagianEntity){
                    returnPositionBagian = new positionBagian();
                    returnPositionBagian.setBagianId(kelompokPositionEntity.getBagianId());
                    returnPositionBagian.setBagianName(kelompokPositionEntity.getBagianName());
                    returnPositionBagian.setDivisiId(kelompokPositionEntity.getDivisiId());
                    ImDepartmentEntity departmentEntity = departmentDao.getById("departmentId",kelompokPositionEntity.getDivisiId());
                    if (departmentEntity!=null){
                        returnPositionBagian.setDivisiName(departmentEntity.getDepartmentName());
                    }
                    returnPositionBagian.setKodering(kelompokPositionEntity.getKodering());
                    returnPositionBagian.setCreatedWho(kelompokPositionEntity.getCreatedWho());
                    returnPositionBagian.setCreatedDate(kelompokPositionEntity.getCreatedDate());
                    returnPositionBagian.setLastUpdate(kelompokPositionEntity.getLastUpdate());
                    returnPositionBagian.setLastUpdateWho(kelompokPositionEntity.getLastUpdateWho());
                    returnPositionBagian.setAction(kelompokPositionEntity.getAction());
                    returnPositionBagian.setFlag(kelompokPositionEntity.getFlag());
                    listOfResult.add(returnPositionBagian);
                }
            }
        }
        logger.info("[PositionBagianBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<positionBagian> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<positionBagian> getComboKelompokWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<positionBagian> listComboPositionBagian = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImPositionBagianEntity> listPositionBagian = null;
        try {
            listPositionBagian = positionBagianDao.getListPositionBagian(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listPositionBagian != null) {
            for (ImPositionBagianEntity imPositionBagianEntity : listPositionBagian) {
                positionBagian itemComboPositionBagian = new positionBagian();
                itemComboPositionBagian.setBagianId(imPositionBagianEntity.getBagianId());
                itemComboPositionBagian.setBagianName(imPositionBagianEntity.getBagianName());
                listComboPositionBagian.add(itemComboPositionBagian);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboPositionBagian;
    }
    @Override
    public List<positionBagian> getBagian(positionBagian bean){
        List<positionBagian> listOfResult = new ArrayList<>();
        List<ImPositionBagianEntity> listOfResultQuery = new ArrayList<>();
        logger.info("[UserBoImpl.getBagian] start process >>>");
        try {
            listOfResultQuery = positionBagianDao.getListOfBagian(bean.getBranchId());
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }
        for (ImPositionBagianEntity positionBagianEntity: listOfResultQuery){
            positionBagian positionBagian = new positionBagian();
            positionBagian.setBagianId(positionBagianEntity.getBagianId());
            positionBagian.setBagianName(positionBagianEntity.getBagianName());
            listOfResult.add(positionBagian);
        }
        return listOfResult;
    }
    @Override
    public positionBagian getBagianById(String id , String flag){
        positionBagian result = new positionBagian();
        ImPositionBagianEntity positionBagianEntity= new ImPositionBagianEntity();
        logger.info("[UserBoImpl.getBagian] start process >>>");
        try {
            positionBagianEntity = positionBagianDao.getById("bagianId",id,flag);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }
        result.setBagianId(positionBagianEntity.getBagianId());
        result.setBagianName(positionBagianEntity.getBagianName());

        return result;
    }
    public String cekStatus(String bagianName)throws GeneralBOException{
        String status ="";
        List<ImPositionBagianEntity> skalaGajiEntity = new ArrayList<>();
        List<ImPositionBagianEntity> positionBagianEntities = new ArrayList<>();
        try {
            skalaGajiEntity = positionBagianDao.getListPositionBagian(bagianName);
        } catch (HibernateException e) {
            logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (skalaGajiEntity.size()>0){
            status = "exist";
        }else{
            if (positionBagianEntities.size()>0)
                status = "exist";
            else
                status="notExits";
        }
        return status;
    }

    public String cekStatusEdit(String bagianName)throws GeneralBOException{
        String status ="";
        List<ImPositionBagianEntity> skalaGajiEntity = new ArrayList<>();
        try {
            skalaGajiEntity = positionBagianDao.getListPositionBagian(bagianName);
        } catch (HibernateException e) {
            logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (skalaGajiEntity.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }

    public List<positionBagian> searchPositionBagian(String divisiId) throws GeneralBOException {
        List<ImPositionBagianEntity> posisiList = null;
        List<positionBagian> positions = new ArrayList<>();

        posisiList = positionBagianDao.getDataPosisiBagian(divisiId);
        if (posisiList != null) {
            for (ImPositionBagianEntity imPositionBagianEntity : posisiList) {
                positionBagian position1 = new positionBagian();
                position1.setBagianId(imPositionBagianEntity.getBagianId());
                position1.setBagianName(imPositionBagianEntity.getBagianName());
                positions.add(position1);
            }
        }
        return positions;
    }
}
