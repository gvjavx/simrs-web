package com.neurix.hris.master.smkBudget.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.smkBudget.bo.SmkBudgetBo;
import com.neurix.hris.master.smkBudget.dao.SmkBudgetDao;
import com.neurix.hris.master.smkBudget.model.SmkBudget;
import com.neurix.hris.master.smkBudget.model.ImSmkBudgetEntity;
import com.neurix.hris.master.smkSkalaPointPrestasi.dao.SmkSkalaPointPrestasiDao;
import com.neurix.hris.master.smkSkalaPointPrestasi.model.ImSmkSkalaPointPrestasiEntity;
import com.neurix.hris.master.smkSkalaPointPrestasi.model.SmkSkalaPointPrestasi;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigInteger;
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
public class SmkBudgetBoImpl implements SmkBudgetBo {

    protected static transient Logger logger = Logger.getLogger(SmkBudgetBoImpl.class);
    private SmkBudgetDao smkBudgetDao;
    private SmkSkalaPointPrestasiDao smkSkalaPointPrestasiDao;

    public SmkSkalaPointPrestasiDao getSmkSkalaPointPrestasiDao() {
        return smkSkalaPointPrestasiDao;
    }

    public void setSmkSkalaPointPrestasiDao(SmkSkalaPointPrestasiDao smkSkalaPointPrestasiDao) {
        this.smkSkalaPointPrestasiDao = smkSkalaPointPrestasiDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkBudgetBoImpl.logger = logger;
    }

    public SmkBudgetDao getSmkBudgetDao() {
        return smkBudgetDao;
    }

    public void setSmkBudgetDao(SmkBudgetDao smkBudgetDao) {
        this.smkBudgetDao = smkBudgetDao;
    }

    @Override
    public void saveDelete(SmkBudget bean) throws GeneralBOException {
        logger.info("[saveDelete.saveDelete] start process >>>");

        if (bean!=null) {

            String BudgetId = bean.getBudgetId();

            ImSmkBudgetEntity imSmkBudgetEntity = null;

            try {
                // Get data from database by ID
                imSmkBudgetEntity = smkBudgetDao.getById("budgetId", BudgetId);
            } catch (HibernateException e) {
                logger.error("[SmkBudgetBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSmkBudgetEntity != null) {

                // Modify from bean to entity serializable
                imSmkBudgetEntity.setBudgetId(bean.getBudgetId());
                imSmkBudgetEntity.setStrukturJabatanId(bean.getStrukturJabatanId());
                imSmkBudgetEntity.setPositionId(bean.getPositionId());
                imSmkBudgetEntity.setPositionName(bean.getPositionName());
                imSmkBudgetEntity.setBobot(bean.getBobot());
                imSmkBudgetEntity.setTarget(bean.getTarget());
                imSmkBudgetEntity.setRealisasi(bean.getRealisasi());
                imSmkBudgetEntity.setPeriode(bean.getPeriode());
                imSmkBudgetEntity.setBagianName(bean.getBagianName());
                imSmkBudgetEntity.setUnitId(bean.getUnitId());

                imSmkBudgetEntity.setFlag(bean.getFlag());
                imSmkBudgetEntity.setAction(bean.getAction());
                imSmkBudgetEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkBudgetEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    smkBudgetDao.updateAndSave(imSmkBudgetEntity);
                } catch (HibernateException e) {
                    logger.error("[SmkBudgetBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkBudget, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[SmkBudgetBoImpl.saveDelete] Error, not found data SmkBudget with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkBudget with request id, please check again your data ...");

            }
        }
        logger.info("[SmkBudgetBoImpl.saveDelete] end process <<<");
    }

    @Override
    public void saveEdit(SmkBudget bean) throws GeneralBOException {
        logger.info("[SmkBudgetBoImpl.saveEdit] start process >>>");

//        String condition = null;

        if (bean!=null) {
            String historyId = "";
            String budgetId = bean.getBudgetId();

            ImSmkBudgetEntity imSmkBudgetEntity = null;

            try {
                // Get data from database by ID
                imSmkBudgetEntity = smkBudgetDao.getById("budgetId", budgetId);

            } catch (HibernateException e) {
                logger.error("[SmkBudgetBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data SmkBudget by Kode SmkBudget, please inform to your admin...," + e.getMessage());
            }

            if (imSmkBudgetEntity != null) {


                imSmkBudgetEntity.setBudgetId(bean.getBudgetId());
                imSmkBudgetEntity.setStrukturJabatanId(bean.getStrukturJabatanId());
                imSmkBudgetEntity.setPositionId(bean.getPositionId());
                imSmkBudgetEntity.setPositionName(bean.getPositionName());
                imSmkBudgetEntity.setBobot(bean.getBobot());
                imSmkBudgetEntity.setTarget(bean.getTarget());
                imSmkBudgetEntity.setRealisasi(bean.getRealisasi());
                imSmkBudgetEntity.setPeriode(bean.getPeriode());
                imSmkBudgetEntity.setBagianName(bean.getBagianName());
                imSmkBudgetEntity.setUnitId(bean.getUnitId());
                imSmkBudgetEntity.setNilaiRealisasi(bean.getNilaiRealisasi());
                imSmkBudgetEntity.setNilaiPrestasi(bean.getNilaiPrestasi());
                imSmkBudgetEntity.setPointPrestasiBagian(bean.getPointPrestasiBagian());

                imSmkBudgetEntity.setFlag(bean.getFlag());
                imSmkBudgetEntity.setAction(bean.getAction());
                imSmkBudgetEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSmkBudgetEntity.setLastUpdate(bean.getLastUpdate());

                String flag;
                try {
                    // Update into database
                    smkBudgetDao.updateAndSave(imSmkBudgetEntity);

//                    condition = "Data SuccessFully Updated";
                } catch (HibernateException e) {
                    logger.error("[SmkBudgetBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data SmkBudget, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[SmkBudgetBoImpl.saveEdit] Error, not found data SmkBudget with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data SmkBudget with request id, please check again your data ...");
//                condition = "Error, not found data SmkBudget with request id, please check again your data ...";
            }
        }
        logger.info("[SmkBudgetBoImpl.saveEdit] end process <<<");
    }

    @Override
    public SmkBudget saveAdd(SmkBudget bean) throws GeneralBOException {
        logger.info("[SmkBudgetBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String budgetId;
            try {
                // Generating ID, get from postgre sequence
                budgetId = smkBudgetDao.getNextSmkBudgetId();
            } catch (HibernateException e) {
                logger.error("[SmkBudgetBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence smkBudgetId id, please info to your admin..." + e.getMessage());
            }

            // creating object entity serializable
            ImSmkBudgetEntity imSmkBudgetEntity = new ImSmkBudgetEntity();

            imSmkBudgetEntity.setBudgetId(budgetId);
            imSmkBudgetEntity.setStrukturJabatanId(bean.getStrukturJabatanId());
            imSmkBudgetEntity.setPositionId(bean.getPositionId());
            imSmkBudgetEntity.setPositionName(bean.getPositionName());
            imSmkBudgetEntity.setBobot(bean.getBobot());
            imSmkBudgetEntity.setTarget(bean.getTarget());
            imSmkBudgetEntity.setRealisasi(bean.getRealisasi());
            imSmkBudgetEntity.setPeriode(bean.getPeriode());
            imSmkBudgetEntity.setBagianName(bean.getBagianName());
            imSmkBudgetEntity.setUnitId(bean.getUnitId());
            imSmkBudgetEntity.setNilaiRealisasi(bean.getNilaiRealisasi());
            imSmkBudgetEntity.setNilaiPrestasi(bean.getNilaiPrestasi());
            imSmkBudgetEntity.setPointPrestasiBagian(bean.getPointPrestasiBagian());

            imSmkBudgetEntity.setFlag(bean.getFlag());
            imSmkBudgetEntity.setAction(bean.getAction());
            imSmkBudgetEntity.setCreatedWho(bean.getCreatedWho());
            imSmkBudgetEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imSmkBudgetEntity.setCreatedDate(bean.getCreatedDate());
            imSmkBudgetEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                smkBudgetDao.addAndSave(imSmkBudgetEntity);
            } catch (HibernateException e) {
                logger.error("[SmkBudgetBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data SmkBudget, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[SmkBudgetBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<SmkBudget> getByCriteria(SmkBudget searchBean) throws GeneralBOException {
        logger.info("[SmkBudgetBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<SmkBudget> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getBudgetId() != null && !"".equalsIgnoreCase(searchBean.getBudgetId())) {
                hsCriteria.put("budget_id", searchBean.getBudgetId());
            }
            if (searchBean.getPeriode() !=null && !"".equalsIgnoreCase(searchBean.getPeriode())){
                hsCriteria.put("periode",searchBean.getPeriode());
            }
            if (searchBean.getUnitId() !=null && !"".equalsIgnoreCase(searchBean.getUnitId())){
                hsCriteria.put("unit_id",searchBean.getUnitId());
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


            List<ImSmkBudgetEntity> imSmkBudgetEntity = null;
            try {

                imSmkBudgetEntity = smkBudgetDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[SmkBudgetBoImpl.getSearchSmkBudgetByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imSmkBudgetEntity != null){
                SmkBudget returnSmkBudget;
                // Looping from dao to object and save in collection
                for(ImSmkBudgetEntity smkBudgetEntity : imSmkBudgetEntity){
                    returnSmkBudget = new SmkBudget();
                    returnSmkBudget.setBudgetId(smkBudgetEntity.getBudgetId());
                    returnSmkBudget.setStrukturJabatanId(smkBudgetEntity.getStrukturJabatanId());
                    returnSmkBudget.setPositionId(smkBudgetEntity.getPositionId());
                    returnSmkBudget.setPositionName(smkBudgetEntity.getPositionName());
                    returnSmkBudget.setTarget(smkBudgetEntity.getTarget());
                    returnSmkBudget.setBobot(smkBudgetEntity.getBobot());
                    returnSmkBudget.setRealisasi(smkBudgetEntity.getRealisasi());
                    returnSmkBudget.setPeriode(smkBudgetEntity.getPeriode());
                    returnSmkBudget.setBagianName(smkBudgetEntity.getBagianName());
                    returnSmkBudget.setUnitId(smkBudgetEntity.getUnitId());
                    returnSmkBudget.setNilaiRealisasi(smkBudgetEntity.getNilaiRealisasi());
                    returnSmkBudget.setNilaiPrestasi(smkBudgetEntity.getNilaiPrestasi());
                    returnSmkBudget.setPointPrestasiBagian(smkBudgetEntity.getPointPrestasiBagian());

                    returnSmkBudget.setCreatedWho(smkBudgetEntity.getCreatedWho());
                    returnSmkBudget.setCreatedDate(smkBudgetEntity.getCreatedDate());
                    returnSmkBudget.setLastUpdate(smkBudgetEntity.getLastUpdate());
                    returnSmkBudget.setAction(smkBudgetEntity.getAction());
                    returnSmkBudget.setFlag(smkBudgetEntity.getFlag());
                    listOfResult.add(returnSmkBudget);
                }
            }
        }
        logger.info("[SmkBudgetBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<SmkBudget> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public List<SmkBudget> getComboSmkBudgetWithCriteria(String query) throws GeneralBOException {
        logger.info("[UserBoImpl.getComboUserWithCriteria] start process >>>");

        List<SmkBudget> listComboSmkBudget = new ArrayList();
        String criteria = query;

        List<ImSmkBudgetEntity> listSmkBudget = null;
        try {
            listSmkBudget = smkBudgetDao.getListSmkBudget(criteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listSmkBudget != null) {
            for (ImSmkBudgetEntity imSmkBudgetEntity : listSmkBudget) {
                SmkBudget itemComboSmkBudget = new SmkBudget();
                itemComboSmkBudget.setBudgetId(imSmkBudgetEntity.getBudgetId());
                itemComboSmkBudget.setStrukturJabatanId(imSmkBudgetEntity.getStrukturJabatanId());
                itemComboSmkBudget.setPositionId(imSmkBudgetEntity.getPositionId());
                itemComboSmkBudget.setPositionName(imSmkBudgetEntity.getPositionName());
                itemComboSmkBudget.setTarget(imSmkBudgetEntity.getTarget());
                itemComboSmkBudget.setBobot(imSmkBudgetEntity.getBobot());
                itemComboSmkBudget.setRealisasi(imSmkBudgetEntity.getRealisasi());
                itemComboSmkBudget.setPeriode(imSmkBudgetEntity.getPeriode());
                itemComboSmkBudget.setBagianName(imSmkBudgetEntity.getBagianName());
                itemComboSmkBudget.setUnitId(imSmkBudgetEntity.getUnitId());
                listComboSmkBudget.add(itemComboSmkBudget);
            }
        }
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboSmkBudget;
    }
    public List<SmkBudget> getCalculatePoint(double bobot,double target,double realisasi) throws GeneralBOException {
        logger.info("[SMKBudgetAction.getCalculatePoint] start process >>>");
        bobot = bobot/100;
        double nilaiRealisasi,nilaiPrestasi,pointPrestasiBagian = 0;

        if ((target/realisasi)*100>=100){
            nilaiRealisasi=100;
        }
        else {
            nilaiRealisasi=(target/realisasi)*100;
        }

        nilaiPrestasi = nilaiRealisasi*bobot;
        Long bi = (long) nilaiPrestasi;
        List<SmkBudget> listComboSmkBudget = new ArrayList();
        List<SmkSkalaPointPrestasi> listComboSmkSkalaPoinPrestasi = new ArrayList<>();
        Map hsCriteria = new HashMap();
        hsCriteria.put("point_prestasi_id", bi);
/*        String criteria = query;*/

        List<ImSmkSkalaPointPrestasiEntity> listSkalaPoinPrestasi = null;
        try {
            listSkalaPoinPrestasi = smkSkalaPointPrestasiDao.getByCriteriaPrestasi(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[UserBoImpl.getComboUserWithCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when retieving list user with criteria, please info to your admin..." + e.getMessage());
        }

        if (listSkalaPoinPrestasi != null) {
            for (ImSmkSkalaPointPrestasiEntity imSmkSkalaPointPrestasiEntity : listSkalaPoinPrestasi) {
                SmkSkalaPointPrestasi itemSmkSkalaPointPrestasi = new SmkSkalaPointPrestasi();
                itemSmkSkalaPointPrestasi.setNilaiAtas(imSmkSkalaPointPrestasiEntity.getNilaiAtas());
                itemSmkSkalaPointPrestasi.setNilaiBawah(imSmkSkalaPointPrestasiEntity.getNilaiBawah());
                itemSmkSkalaPointPrestasi.setPoint(imSmkSkalaPointPrestasiEntity.getPoint());
                pointPrestasiBagian = imSmkSkalaPointPrestasiEntity.getPoint();
                if (target>realisasi){
                    pointPrestasiBagian=pointPrestasiBagian*1;
                }
                else {
                    pointPrestasiBagian=pointPrestasiBagian*-1;
                }
                itemSmkSkalaPointPrestasi.setPointPrestasiId(imSmkSkalaPointPrestasiEntity.getPointPrestasiId());
                listComboSmkSkalaPoinPrestasi.add(itemSmkSkalaPointPrestasi);
            }
        }

        SmkBudget itemComboSmkBudget = new SmkBudget();
        itemComboSmkBudget.setNilaiPrestasi(nilaiPrestasi);
        itemComboSmkBudget.setNilaiRealisasi(nilaiRealisasi);
        itemComboSmkBudget.setPointPrestasiBagian(pointPrestasiBagian);
        listComboSmkBudget.add(itemComboSmkBudget);
        logger.info("[UserBoImpl.getComboUserWithCriteria] end process <<<");
        return listComboSmkBudget;
    }
}
