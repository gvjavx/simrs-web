package com.neurix.authorization.position.bo.impl;

import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.dao.PositionHistoryDao;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.position.model.ImPositionHistory;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.exception.GenerateBoLog;
import com.neurix.hris.master.department.dao.DepartmentDao;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.kelompokPosition.dao.KelompokPositionDao;
import com.neurix.hris.master.positionBagian.dao.PositionBagianDao;
import com.neurix.hris.master.positionBagian.model.ImPositionBagianEntity;
import com.neurix.hris.transaksi.personilPosition.dao.PersonilPositionDao;
import com.neurix.hris.transaksi.personilPosition.model.ItPersonilPositionEntity;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by Ferdi on 02/02/2015.
 */
public class PositionBoImpl implements PositionBo {

    protected static transient Logger logger = Logger.getLogger(PositionBoImpl.class);
    private PositionDao positionDao;
    private PositionHistoryDao positionHistoryDao;
    private DepartmentDao departmentDao;
    private PositionBagianDao positionBagianDao;
    private PersonilPositionDao personilPositionDao;
    private KelompokPositionDao kelompokPositionDao;

    public KelompokPositionDao getKelompokPositionDao() {
        return kelompokPositionDao;
    }

    public void setKelompokPositionDao(KelompokPositionDao kelompokPositionDao) {
        this.kelompokPositionDao = kelompokPositionDao;
    }

    public PersonilPositionDao getPersonilPositionDao() {
        return personilPositionDao;
    }

    public void setPersonilPositionDao(PersonilPositionDao personilPositionDao) {
        this.personilPositionDao = personilPositionDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PositionBoImpl.logger = logger;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public PositionBagianDao getPositionBagianDao() {
        return positionBagianDao;
    }

    public void setPositionBagianDao(PositionBagianDao positionBagianDao) {
        this.positionBagianDao = positionBagianDao;
    }

    public PositionHistoryDao getPositionHistoryDao() {
        return positionHistoryDao;
    }

    public void setPositionHistoryDao(PositionHistoryDao positionHistoryDao) {
        this.positionHistoryDao = positionHistoryDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public List<Position> getAll() throws GeneralBOException {

        logger.info("[PositionBoImpl.getAll] start process >>>");

        List<Position> listOfResultPosition = new ArrayList();
        List<ImPosition> listOfPosition = null;
        try {
            listOfPosition = positionDao.getAll();
        } catch (HibernateException e) {
            logger.error("[PositionBoImpl.getAll] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when getting all data, please info to your admin..." + e.getMessage());
        }

        if ( listOfPosition != null) {
            Position resultPosition;
            for (ImPosition imPosition : listOfPosition) {
                resultPosition = new Position();

                try {
                    BeanUtils.copyProperties(resultPosition, imPosition);
                } catch (IllegalAccessException e) {
                    logger.error("[PositionBoImpl.getAll] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping saving ImPosition to resultPositions at getting all data, please info to your admin..." + e.getMessage());
                } catch (InvocationTargetException e) {
                    logger.error("[PositionBoImpl.getAll] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when coping saving ImPosition to resultPositions at getting all data, please info to your admin..." + e.getMessage());
                }

                resultPosition.setFlag(imPosition.getFlag());
                listOfResultPosition.add(resultPosition);
            }
        }

        logger.info("[PositionBoImpl.getAll] end process <<<");

        return listOfResultPosition;
    }

    @Override
    public List<Position> getComboDivisiWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<Position> listComboDivisi = new ArrayList();
        String criteria = query ;

        List<ImPosition> listDivisi = null;
        try {
            listDivisi = positionDao.getListDivisi(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listDivisi != null) {
            for (ImPosition imPosition : listDivisi) {
                Position itemComboPosition = new Position();
                itemComboPosition.setDepartmentId(imPosition.getDepartmentId());
                itemComboPosition.setPositionName(imPosition.getPositionName());
                itemComboPosition.setPositionId(imPosition.getPositionId());
                listComboDivisi.add(itemComboPosition);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboDivisi;
    }

    public List<Position> getByCriteria(Position searchPosition) throws GeneralBOException {

        logger.info("[PositionBoImpl.getByCriteria] start process >>>");

        List<Position> listOfResultPosition = new ArrayList();

        if (searchPosition != null) {
            Map hsCriteria = new HashMap();
            if (searchPosition.getStPositionId() != null && !"".equalsIgnoreCase(searchPosition.getStPositionId())) {
                hsCriteria.put("position_id", searchPosition.getStPositionId());
            }
            if (searchPosition.getPositionId() != null && !"".equalsIgnoreCase(searchPosition.getPositionId())) {
                hsCriteria.put("position_id", searchPosition.getPositionId());
            }
            if (searchPosition.getPositionName() != null && !"".equalsIgnoreCase(searchPosition.getPositionName())) {
                hsCriteria.put("position_name", searchPosition.getPositionName());
            }

            if (searchPosition.getDepartmentId() != null && !"".equalsIgnoreCase(searchPosition.getDepartmentId())) {
                hsCriteria.put("department_id", searchPosition.getDepartmentId());
            }

            if (searchPosition.getBagianId() != null && !"".equalsIgnoreCase(searchPosition.getBagianId())) {
                hsCriteria.put("bagian_id", searchPosition.getBagianId());
            }
            if (searchPosition.getKelompokId() != null && !"".equalsIgnoreCase(searchPosition.getKelompokId())) {
                hsCriteria.put("kelompok_id", searchPosition.getKelompokId());
            }
            if (searchPosition.getKodering() != null && !"".equalsIgnoreCase(searchPosition.getKodering())) {
                hsCriteria.put("kodering", searchPosition.getKodering());
            }
            if (searchPosition.getKategori() != null && !"".equalsIgnoreCase(searchPosition.getKategori())) {
                hsCriteria.put("kategori", searchPosition.getKategori());
            }

            if (searchPosition.getFlag() != null && !"".equalsIgnoreCase(searchPosition.getFlag())) {
                if ("N".equalsIgnoreCase(searchPosition.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", searchPosition.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            List<ImPosition> listOfPosition = null;
            try {
                listOfPosition = positionDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[PositionBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if (listOfPosition != null) {
                Position resultPosition;
                for (ImPosition imPosition : listOfPosition) {
                    resultPosition = new Position();

                    resultPosition.setPositionId(imPosition.getPositionId());
                    resultPosition.setStPositionId(imPosition.getPositionId().toString());
                    resultPosition.setPositionName(imPosition.getPositionName());
                    resultPosition.setKodering(imPosition.getKodering());

                    resultPosition.setDepartmentId(imPosition.getDepartmentId());
                    if(imPosition.getImDepartmentEntity() != null){
                        resultPosition.setDepartmentName(imPosition.getImDepartmentEntity().getDepartmentName());
                    }else{
                        resultPosition.setDepartmentName("");
                    }

                    if(imPosition.getImKelompokPositionEntity() != null){
                        resultPosition.setKelompokName(imPosition.getImKelompokPositionEntity().getKelompokName());
                    }else{
                        resultPosition.setKelompokName("-");
                    }
                    resultPosition.setKelompokId(imPosition.getKelompokId());

                    if(imPosition.getImPositionBagianEntity() != null){
                        resultPosition.setBagianName(imPosition.getImPositionBagianEntity().getBagianName());
                    }else{
                        resultPosition.setBagianName("-");
                    }
                    resultPosition.setBagianId(imPosition.getBagianId());
                    resultPosition.setFlagDijabatSatuOrang(imPosition.getFlagDijabatSatuOrang());
                    resultPosition.setAction(imPosition.getAction());
                    resultPosition.setCreatedDate(imPosition.getCreatedDate());
                    resultPosition.setCreatedWho(imPosition.getCreatedWho());
                    resultPosition.setLastUpdate(imPosition.getLastUpdate());
                    resultPosition.setLastUpdateWho(imPosition.getLastUpdateWho());
                    resultPosition.setFlag(imPosition.getFlag());
                    resultPosition.setKategori(imPosition.getKategori());

                    listOfResultPosition.add(resultPosition);
                }
            }

        }

        logger.info("[PositionBoImpl.getByCriteria] end process <<<");

        return listOfResultPosition;
    }

    public Position saveAdd(Position position) throws GeneralBOException {

        logger.info("[PositionBoImpl.saveAdd] start process >>>");

        if (position != null) {
            String status = cekStatus(position.getPositionName());
            if (!status.equalsIgnoreCase("Exist")){
                ImPosition imPosition = new ImPosition();

                imPosition.setFlag("Y");
                imPosition.setPositionId(positionDao.getNextPosition() + "");
                imPosition.setPositionName(position.getPositionName());
                imPosition.setDepartmentId(position.getDepartmentId());
                imPosition.setKelompokId(position.getKelompokId());
                imPosition.setBagianId(position.getBagianId());

                ImPositionBagianEntity positionBagianEntity = positionBagianDao.getById("bagianId",position.getBagianId());
                List<ImPosition> positionList = positionDao.getListByBagianId(position.getBagianId());
                String sId = String.format("%02d", positionList.size()+1);

                imPosition.setKodering(positionBagianEntity.getKodering()+"."+sId);

                imPosition.setCreatedDate(position.getCreatedDate());
                imPosition.setLastUpdate(position.getLastUpdate());
                imPosition.setLastUpdateWho(position.getLastUpdateWho());
                imPosition.setCreatedWho(position.getCreatedWho());
                imPosition.setAction(position.getAction());
                try {
                    positionDao.addAndSave(imPosition);
                } catch (HibernateException e) {
                    logger.error("[PositionBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data position, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Posisi atau Kodering Tersebut Sudah Ada");
            }
        }

        logger.info("[PositionBoImpl.saveAdd] end process <<<");
        return null;
    }

    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {

//        Long result = GenerateBoLog.generateBoLog(positionDao, message, moduleMethod);

        return null;
    }

    public void saveEdit(Position bean) throws GeneralBOException{
        logger.info("[PositionBoImpl.saveEdit] start process >>>");

        if (bean != null){
            String status = cekStatusEdit(bean.getPositionName(), bean.getDepartmentId(), bean.getBagianId(), bean.getKelompokId());
            if (!status.equalsIgnoreCase("exist")){
                String positionId = bean.getPositionId();
                ImPosition imPosition = null;
                ImPositionHistory historyEntity = new ImPositionHistory();

                try{
                    imPosition = positionDao.getById("positionId", positionId);
                }catch (HibernateException e){
                    logger.error("[PositionBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data Position by Position Id, please inform to your admin...," + e.getMessage());
                }

                if (imPosition != null){
                    //entity history
                    String positionHistoryId;
                    try {
                        // Generating ID, get from postgre sequence
                        positionHistoryId = positionHistoryDao.getNextPositionHistory();
                    } catch (HibernateException e) {
                        logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when getting sequence payrollTunjanganJabatanStruktural id, please info to your admin..." + e.getMessage());
                    }

                    historyEntity.setPositionIdHistory(positionHistoryId);
                    historyEntity.setPositionName(imPosition.getPositionName());
                    historyEntity.setDepartmentId(imPosition.getDepartmentId());
                    historyEntity.setKelompokId(imPosition.getKelompokId());
                    historyEntity.setBagianId(imPosition.getBagianId());
                    historyEntity.setKodering(imPosition.getKodering());
                    historyEntity.setPositionId(imPosition.getPositionId());
                    historyEntity.setFlagDijabatSatuOrang(imPosition.getFlagDijabatSatuOrang());
                    historyEntity.setKodering(imPosition.getKodering());
                    historyEntity.setCreatedDate(imPosition.getLastUpdate());
                    historyEntity.setCreatedWho(imPosition.getLastUpdateWho());
                    historyEntity.setLastUpdate(imPosition.getLastUpdate());
                    historyEntity.setLastUpdateWho(imPosition.getLastUpdateWho());
                    historyEntity.setFlag("Y");
                    historyEntity.setAction(imPosition.getAction());

                    try {
                        // insert into database
                        positionHistoryDao.addAndSave(historyEntity);
                    } catch (HibernateException e) {
                        logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving new data PayrollTunjanganJabatanStruktural, please info to your admin..." + e.getMessage());
                    }

                    imPosition.setPositionId(bean.getPositionId());
                    imPosition.setPositionName(bean.getPositionName());
                    imPosition.setKodering(bean.getKodering());
                    imPosition.setDepartmentId(bean.getDepartmentId());
                    imPosition.setKelompokId(bean.getKelompokId());
                    imPosition.setBagianId(bean.getBagianId());
                    imPosition.setFlag(bean.getFlag());
                    imPosition.setAction(bean.getAction());
                    imPosition.setLastUpdateWho(bean.getLastUpdateWho());
                    imPosition.setLastUpdate(bean.getLastUpdate());

                    try{
                        positionDao.updateAndSave(imPosition);
                    }catch (HibernateException e){
                        logger.error("[PositionBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Position, please info to your admin..." + e.getMessage());
                    }
                }else {
                    logger.error("[PositionBoImpl.saveEdit] Error, not found data Position with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data Position with request id, please check again your data ...");
                }
            }else {
                throw new GeneralBOException("Maaf Posisi Tersebut Sudah Terisi");
            }
        }
        logger.info("[PositionBoImpl.saveEdit] end process <<<");
    }

//    public void saveEdit(Position positionNew) throws GeneralBOException {
//
//        logger.info("[PositionBoImpl.saveEdit] start process >>>");
//
//        if (positionNew != null) {
//
//            //copy new data to model tabel
//            ImPosition imPositionNew = new ImPosition();
//            try {
//                BeanUtils.copyProperties(imPositionNew, positionNew);
//            } catch (IllegalAccessException e) {
//                logger.error("[PositionBoImpl.saveEdit] Error, " + e.getMessage());
//                throw new GeneralBOException("Found problem when coping data object positionNewN to ImPositionNew, please info to your admin..." + e.getMessage());
//            } catch (InvocationTargetException e) {
//                logger.error("[PositionBoImpl.saveEdit] Error, " + e.getMessage());
//                throw new GeneralBOException("Found problem when coping data object positionNew to ImPositionNew, please info to your admin..." + e.getMessage());
//            }
//
//            //retrieve last data by id
//            String positionId = positionNew.getPositionId();
//
//            ImPosition imPositionOld = null;
//            try {
//                imPositionOld = positionDao.getById("positionId",positionId, "Y");
//            } catch (HibernateException e) {
//                logger.error("[PositionBoImpl.saveEdit] Error, " + e.getMessage());
//                throw new GeneralBOException("Found problem when searching data position by id, please inform to your admin...," + e.getMessage());
//            }
//
//            if (imPositionOld != null) {
//
//                // move last data to table history
//                ImPositionHistory imPositionDeactive = new ImPositionHistory();
//                /*try {
//                    BeanUtils.copyProperties(imPositionDeactive, imPositionOld);
//                } catch (IllegalAccessException e) {
//                    logger.error("[PositionBoImpl.saveEdit] Error, " + e.getMessage());
//                    throw new GeneralBOException("Found problem when coping data object positionOld to ImPositionBeforeDeactive, please info to your admin..." + e.getMessage());
//                } catch (InvocationTargetException e) {
//                    logger.error("[PositionBoImpl.saveEdit] Error, " + e.getMessage());
//                    throw new GeneralBOException("Found problem when coping data object positionOld to ImPositionBeforeDeactive, please info to your admin..." + e.getMessage());
//                }
//
//                try {
//                    positionDao.addAndSaveHistory(imPositionDeactive);
//                } catch (HibernateException e) {
//                    logger.error("[PositionBoImpl.saveEdit] Error, " + e.getMessage());
//                    throw new GeneralBOException("Found problem when saving deactive data role, please info to your admin..." + e.getMessage());
//                }*/
//
//                //update some of last data become new data
//                imPositionNew.setPositionId(positionId);
//                imPositionNew.setDepartmentId(positionNew.getDepartmentId());
//                imPositionNew.setKelompokId(positionNew.getKelompokId());
//                imPositionNew.setBagianId(positionNew.getBagianId());
//                imPositionNew.setFlag(imPositionOld.getFlag());
//
//                ImPosition imPositionActive = (ImPosition) positionDao.getSessionFactory().getCurrentSession().merge(imPositionNew);
//
//                try {
//                    positionDao.updateAndSave(imPositionActive);
//                } catch (HibernateException e) {
//                    logger.error("[PositionBoImpl.saveEdit] Error, " + e.getMessage());
//                    throw new GeneralBOException("Found problem when saving updated data position, please inform to your admin...," + e.getMessage());
//                }
//
//            } else {
//                logger.error("[PositionBoImpl.saveEdit] Unable to save edit cause no found role key.");
//                throw new GeneralBOException("Found problem when saving edit data position cause no found role key., please info to your admin...");
//            }
//        }
//
//        logger.info("[PositionBoImpl.saveEdit] end process <<<");
//    }

    public void saveDelete(Position position) throws GeneralBOException {

        logger.info("[PositionBoImpl.saveDelete] start process >>>");

        if (position != null) {

            String positionId = position.getPositionId();

            //validasi
            List<ItPersonilPositionEntity> personilPositionEntityList= personilPositionDao.getListPersonilPositionByPositionId(positionId);

            if (personilPositionEntityList.size()>0){
                String status = "ERROR : data tidak bisa dihapus dikarenakan sudah digunakan di transaksi";
                logger.error(status);
                throw new GeneralBOException(status);
            }

            ImPosition imPosition = null;
            ImPositionHistory historyEntity = new ImPositionHistory();
            try {
                imPosition = positionDao.getById("positionId",positionId, "Y");
            } catch (HibernateException e) {
                logger.error("[PositionBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving delete data position, please info to your admin..." + e.getMessage());
            }

            if (imPosition != null) {
                //entity history
                String positionHistoryId;
                try {
                    // Generating ID, get from postgre sequence
                    positionHistoryId = positionHistoryDao.getNextPositionHistory();
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollTunjanganJabatanStruktural id, please info to your admin..." + e.getMessage());
                }

                historyEntity.setPositionIdHistory(positionHistoryId);
                historyEntity.setPositionName(imPosition.getPositionName());
                historyEntity.setDepartmentId(imPosition.getDepartmentId());
                historyEntity.setKelompokId(imPosition.getKelompokId());
                historyEntity.setBagianId(imPosition.getBagianId());
                historyEntity.setKodering(imPosition.getKodering());
                historyEntity.setPositionId(imPosition.getPositionId());
                historyEntity.setFlagDijabatSatuOrang(imPosition.getFlagDijabatSatuOrang());
                historyEntity.setKodering(imPosition.getKodering());
                historyEntity.setCreatedDate(imPosition.getLastUpdate());
                historyEntity.setCreatedWho(imPosition.getLastUpdateWho());
                historyEntity.setLastUpdate(imPosition.getLastUpdate());
                historyEntity.setLastUpdateWho(imPosition.getLastUpdateWho());
                historyEntity.setFlag("Y");
                historyEntity.setAction(imPosition.getAction());

                try {
                    // insert into database
                    positionHistoryDao.addAndSave(historyEntity);
                } catch (HibernateException e) {
                    logger.error("[PayrollTunjanganJabatanStrukturalBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PayrollTunjanganJabatanStruktural, please info to your admin..." + e.getMessage());
                }

                imPosition.setPositionId(position.getPositionId());
//                imPosition.setPositionName(bean.getPositionName());
//                imPosition.setKodering(bean.getKodering());
//                imPosition.setDepartmentId(bean.getDepartmentId());
//                imPosition.setKelompokId(bean.getKelompokId());
//                imPosition.setBagianId(bean.getBagianId());
                imPosition.setFlag(position.getFlag());
                imPosition.setAction(position.getAction());
                imPosition.setLastUpdateWho(position.getLastUpdateWho());
                imPosition.setLastUpdate(position.getLastUpdate());

                try{
                    positionDao.updateAndSave(imPosition);
                }catch (HibernateException e){
                    logger.error("[PositionBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data Position, please info to your admin..." + e.getMessage());
                }



//                Set listOfImUsers = imPositionOld.getImUserses();
//                ImPosition imPositionToDeactive = imPositionOld;
//
//                    /*try {
//                        BeanUtils.copyProperties(imPositionToDeactive, imPositionOld);
//                    } catch (IllegalAccessException e) {
//                        logger.error("[PositionBoImpl.saveDelete] Error, " + e.getMessage());
//                        throw new GeneralBOException("Found problem when coping data object position Will be Delete to ImPositionBeforeDeactive, please info to your admin..." + e.getMessage());
//                    } catch (InvocationTargetException e) {
//                        logger.error("[PositionBoImpl.saveDelete] Error, " + e.getMessage());
//                        throw new GeneralBOException("Found problem when coping data object position  Will be Delete to ImPositionBeforeDeactive, please info to your admin..." + e.getMessage());
//                    }*/
//
//                //update data with flag=N
//                imPositionToDeactive.setFlag("N");
//                imPositionToDeactive.setAction(position.getAction());
//                imPositionToDeactive.setLastUpdate(position.getLastUpdate());
//                imPositionToDeactive.setLastUpdateWho(position.getLastUpdateWho());
//
//                ImPosition imPositionDeactive = (ImPosition) positionDao.getSessionFactory().getCurrentSession().merge(imPositionToDeactive);
//
//                try {
//                    positionDao.updateAndSave(imPositionDeactive);
//                } catch (HibernateException e) {
//                    logger.error("[PositionBoImpl.saveDelete] Error, " + e.getMessage());
//                    throw new GeneralBOException("Found problem when saving delete data position, please info to your admin..." + e.getMessage());
//                }

//                if (imPositionOld != null) {
//
//                } else {
//                    logger.error("[PositionBoImpl.saveDelete] Unable to delete cause have reference data exist in user table.");
//                    throw new GeneralBOException("Found problem when saving delete data role cause have reference data exist in user table, please info to your admin...");
//                }
            } else {
                logger.error("[PositionBoImpl.saveDelete] Unable to delete cause no found position key.");
                throw new GeneralBOException("Found problem when saving delete data role cause no found position key., please info to your admin...");
            }
        }

        logger.info("[PositionBoImpl.saveDelete] end process <<<");
    }
    @Override
    public Position getPositionById(String positionId, String flag) throws GeneralBOException {

        logger.info("[PositionBoImpl.getPositionById] start process >>>");

        String getFlag = "";
        if (flag != null && !"".equalsIgnoreCase(flag)) {
            if (flag.equalsIgnoreCase("")) getFlag = "Y";
            else getFlag = flag;
        } else {
            getFlag = "Y";
        }

        ImPosition imPosition = null;
        try {
            imPosition = positionDao.getById("positionId",(String)positionId,getFlag);
        } catch (HibernateException e) {
            logger.error("[PositionBoImpl.getPositionById] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retrieving position based on id and flag, please info to your admin..." + e.getMessage());
        }

        Position resultPosition = new Position();

        if(imPosition != null){
            resultPosition.setPositionId(imPosition.getPositionId());
            resultPosition.setStPositionId(imPosition.getPositionId());
            resultPosition.setPositionName(imPosition.getPositionName());
            resultPosition.setKodering(imPosition.getKodering());
            resultPosition.setBagianId(imPosition.getBagianId());
            resultPosition.setBagianName(imPosition.getImPositionBagianEntity().getBagianName());
            resultPosition.setKelompokId(imPosition.getKelompokId());
            resultPosition.setKelompokName(imPosition.getImKelompokPositionEntity().getKelompokName());
            resultPosition.setDepartmentId(imPosition.getDepartmentId());
            resultPosition.setDepartmentName(imPosition.getImDepartmentEntity().getDepartmentName());
            resultPosition.setFlag(imPosition.getFlag());
        }

        /*if (imPosition != null) {

            try {
                BeanUtils.copyProperties(resultPosition, imPosition);
            } catch (IllegalAccessException e) {
                logger.error("[PositionBoImpl.getPositionById] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping search ImPosition to result position to display , please info to your admin..." + e.getMessage());
            } catch (InvocationTargetException e) {
                logger.error("[PositionBoImpl.getPositionById] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when coping search ImPosition to result position to display , please info to your admin..." + e.getMessage());
            }

            resultPosition.setStPositionId(imPosition.getPositionId().toString());
            resultPosition.setFlag(imPosition.getFlag());
        }*/

        logger.info("[PositionBoImpl.getPositionById] end process <<<");

        return resultPosition;
    }

    public List<Position> getComboPositionWithCriteria(String query) throws GeneralBOException {
        logger.info("[PositionBoImpl.getComboPositionWithCriteria] start process >>>");

        List<Position> listComboPosition = new ArrayList();
        String criteria = "%" + query + "%";

        List<ImPosition> listPosition = null;
        try {
            listPosition = positionDao.getListPosition(criteria);
        } catch (HibernateException e) {
            logger.error("[PositionBoImpl.getComboPositionWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list position with criteria, please info to your admin..." + e.getMessage());
        }

        if (listPosition != null) {
            for (ImPosition imPosition : listPosition) {
                Position itemComboRoles = new Position();
                itemComboRoles.setPositionId(imPosition.getPositionId());
                itemComboRoles.setPositionName(imPosition.getPositionName());
                listComboPosition.add(itemComboRoles);
            }
        }
        logger.info("[PositionBoImpl.getComboPositionWithCriteria] end process <<<");
        return listComboPosition;
    }

    @Override
    public List<Position> searchPosition2Sys(Position position) throws GeneralBOException {
        List<ImPosition> posisiList = null;
        List<Position> positions = new ArrayList<>();

        posisiList = positionDao.getDataPosisi(position.getBranchId(), position.getDepartmentId());
        if (posisiList != null) {
            for (ImPosition imPosition : posisiList) {
                Position position1 = new Position();
                position1.setPositionId(imPosition.getPositionId());
                position1.setPositionName(imPosition.getPositionName());
                positions.add(position1);
            }
        }
        return positions;
    }
    @Override
    public List<Position> searchPositionMutasi(Position position) throws GeneralBOException {
        List<ImPosition> posisiList = null;
        List<Position> positions = new ArrayList<>();

        posisiList = positionDao.getDataPosisi2(position.getBranchId(), position.getDepartmentId());
        if (posisiList != null) {
            for (ImPosition imPosition : posisiList) {
                Position position1 = new Position();
                position1.setPositionId(imPosition.getPositionId());
                position1.setPositionName(imPosition.getPositionName());
                positions.add(position1);
            }
        }
        return positions;
    }
    @Override
    public List<Position> searchDivisi(Position position) throws GeneralBOException {
        List<Position> posisiList = null;
        List<Position> positions = new ArrayList<>();

        posisiList = positionDao.getDataDevisi(position.getBranchId());
        if (posisiList != null) {
            for (Position imPosition : posisiList) {
                if (imPosition.getDepartmentId()!=null) {
                    Position position1 = new Position();
                    position1.setDepartmentId(imPosition.getDepartmentId());
                    position1.setDepartmentName(imPosition.getDepartmentName());
                    positions.add(position1);
                }
            }
        }
        return positions;
    }

    @Override
    public List<Position> searchPosition2Sys(String unitId) throws GeneralBOException {
        List<ImPosition> posisiList = null;
        List<Position> positions = new ArrayList<>();

        posisiList = positionDao.getDataPosisi(unitId);
        if (posisiList != null) {
            for (ImPosition imPosition : posisiList) {
                Position position1 = new Position();
                position1.setPositionId(imPosition.getPositionId());
                position1.setPositionName(imPosition.getPositionName());
                positions.add(position1);
            }
        }
        return positions;
    }

    @Override
    public List<Position> searchPositionBiodataSys(String divisiId) throws GeneralBOException {
        List<ImPosition> posisiList = null;
        List<Position> positions = new ArrayList<>();

        posisiList = positionDao.getDataPosisiBiodata(divisiId);
        if (posisiList != null) {
            for (ImPosition imPosition : posisiList) {
                Position position1 = new Position();
                position1.setPositionId(imPosition.getPositionId());
                position1.setPositionName(imPosition.getPositionName());
                positions.add(position1);
            }
        }
        return positions;
    }
    public List<Position> searchPositionBiodataSysHistory(String divisiId) throws GeneralBOException {
        List<ImPosition> posisiList = null;
        List<Position> positions = new ArrayList<>();

        posisiList = positionDao.getDataPosisiBiodataHistory(divisiId);
        if (posisiList != null) {
            for (ImPosition imPosition : posisiList) {
                Position position1 = new Position();
                position1.setPositionId(imPosition.getPositionId());
                position1.setPositionName(imPosition.getPositionName());
                positions.add(position1);
            }
        }
        return positions;
    }
    public String cekStatus(String positionName)throws GeneralBOException{
        String status ="";
        List<ImPosition> imPositions = new ArrayList<>();
        List<ImPosition> positionList = new ArrayList<>();
        try {
            imPositions = positionDao.getListPosition(positionName);
        } catch (HibernateException e) {
            logger.error("[PositionBoImpl.cekStatus] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (imPositions.size()>0){
            status = "exist";
        }else{
            if (positionList.size()>0)
                status = "exist";
            else
                status="notExits";
        }
        return status;
    }

    public String cekStatusEdit(String positionName, String department, String bagian, String kelompok) throws GeneralBOException{
        String status = "";
        List<ImPosition> positions = new ArrayList<>();
        try{
            positions = positionDao.getListPositionByCriteria(positionName, department, bagian, kelompok);
        }catch (HibernateException e){
            logger.error("[PositionBoImpl.cekStatusEdit] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (positions.size()>0){
            status = "exist";
        }else {
            status = "notExist";
        }
        return status;
    }

    @Override
    public ImPosition getPositionEntityById(String id) throws GeneralBOException {
        return positionDao.getById("positionId", id);
    }

    @Override
    public List<Position> typeAheadPosition(String key) throws GeneralBOException {
        logger.info("[PositionBoImpl.getPositionById] start process >>>");
        List<Position> positionList = new ArrayList<>();
        List<ImPosition> imPositionList = null;
        try {
            imPositionList = positionDao.getTypeAheadPosition(key);
        } catch (HibernateException e) {
            logger.error("[PositionBoImpl.getPositionById] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retrieving position based on id and flag, please info to your admin..." + e.getMessage());
        }
        if (imPositionList != null) {
            for (ImPosition imPosition : imPositionList) {
                Position resultPosition = new Position();
                resultPosition.setPositionId(imPosition.getPositionId());
                resultPosition.setStPositionId(imPosition.getPositionId());
                resultPosition.setPositionName(imPosition.getPositionName());
                resultPosition.setKodering(imPosition.getKodering());
                resultPosition.setBagianId(imPosition.getBagianId());
                resultPosition.setBagianName(imPosition.getImPositionBagianEntity().getBagianName());
                resultPosition.setKelompokId(imPosition.getKelompokId());
                resultPosition.setKelompokName(imPosition.getImKelompokPositionEntity().getKelompokName());
                resultPosition.setDepartmentId(imPosition.getDepartmentId());
                resultPosition.setDepartmentName(imPosition.getImDepartmentEntity().getDepartmentName());
                resultPosition.setFlag(imPosition.getFlag());

                positionList.add(resultPosition);
            }
        }
        logger.info("[PositionBoImpl.getPositionById] end process <<<");

        return positionList;
    }

    @Override
    public List<Position> getComboBodBoc() throws GeneralBOException {
        logger.info("[PositionBoImpl.getByCriteria] start process >>>");

        List<Position> listOfResultPosition = new ArrayList();

        List<ImPosition> listOfPosition = null;
        try {
            listOfPosition = positionDao.getPositionBodBoc();
        } catch (HibernateException e) {
            logger.error("[PositionBoImpl.getByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if (listOfPosition != null) {
            Position resultPosition;
            for (ImPosition imPosition : listOfPosition) {
                resultPosition = new Position();

                resultPosition.setPositionId(imPosition.getPositionId());
                resultPosition.setStPositionId(imPosition.getPositionId().toString());
                resultPosition.setPositionName(imPosition.getPositionName());
                resultPosition.setKodering(imPosition.getKodering());

                resultPosition.setDepartmentId(imPosition.getDepartmentId());
                if(imPosition.getImDepartmentEntity() != null){
                    resultPosition.setDepartmentName(imPosition.getImDepartmentEntity().getDepartmentName());
                }else{
                    resultPosition.setDepartmentName("");
                }

                if(imPosition.getImKelompokPositionEntity() != null){
                    resultPosition.setKelompokName(imPosition.getImKelompokPositionEntity().getKelompokName());
                }else{
                    resultPosition.setKelompokName("-");
                }
                resultPosition.setKelompokId(imPosition.getKelompokId());

                if(imPosition.getImPositionBagianEntity() != null){
                    resultPosition.setBagianName(imPosition.getImPositionBagianEntity().getBagianName());
                }else{
                    resultPosition.setBagianName("-");
                }
                resultPosition.setBagianId(imPosition.getBagianId());
                resultPosition.setFlagDijabatSatuOrang(imPosition.getFlagDijabatSatuOrang());
                resultPosition.setAction(imPosition.getAction());
                resultPosition.setCreatedDate(imPosition.getCreatedDate());
                resultPosition.setCreatedWho(imPosition.getCreatedWho());
                resultPosition.setLastUpdate(imPosition.getLastUpdate());
                resultPosition.setLastUpdateWho(imPosition.getLastUpdateWho());
                resultPosition.setFlag(imPosition.getFlag());
                resultPosition.setKategori(imPosition.getKategori());

                listOfResultPosition.add(resultPosition);
            }
        }

        logger.info("[PositionBoImpl.getByCriteria] end process <<<");

        return listOfResultPosition;
    }

    public List<ImPosition> getPositionByString(String query) throws GeneralBOException {
        String term = "%"+query+"%";
        return positionDao.getListPosition(term);
    }

    @Override
    public PersonilPosition getAndCheckJabatanTerpakai(String positionId, String branchId) throws GeneralBOException {
        logger.info("[PositionBoImpl.getAndCheckJabatanTerpakai]  START >>>");

        PersonilPosition personilPosition = new PersonilPosition();

        boolean isMultiple = false;

        try {
            isMultiple = positionDao.checkIsMultiplePersonByPositionId(positionId);
        } catch (HibernateException e){
            logger.error("[PositionBoImpl.getAndCheckJabatanTerpakai] ERROR checkIsMultiplePersonByPositionId, " + e.getMessage());
            throw new GeneralBOException("[PositionBoImpl.getAndCheckJabatanTerpakai] Found problem when check is multiple ." + e.getMessage());
        }

        if (!isMultiple){
            try {
                personilPosition = positionDao.getPersonilPositionAktif(branchId, positionId);
            } catch (HibernateException e){
                logger.error("[PositionBoImpl.getAndCheckJabatanTerpakai] ERROR getPersonilPositionAktif, " + e.getMessage());
                throw new GeneralBOException("[PositionBoImpl.getAndCheckJabatanTerpakai] Found problem when searc position aktif ." + e.getMessage());
            }
        }

        logger.info("[PositionBoImpl.getAndCheckJabatanTerpakai]  END <<<");
        if (personilPosition != null && personilPosition.getNip() != null)
            return personilPosition;
        else
            return null;
    }
}