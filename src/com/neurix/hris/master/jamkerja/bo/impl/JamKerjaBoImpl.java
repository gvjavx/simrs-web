package com.neurix.hris.master.jamkerja.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.group.bo.GroupBo;
import com.neurix.hris.master.group.dao.GroupDao;
import com.neurix.hris.master.group.model.Group;
import com.neurix.hris.master.group.model.ImHrisGroupEntity;
import com.neurix.hris.master.group.model.ImHrisGroupHistory;
import com.neurix.hris.master.jamkerja.bo.JamKerjaBo;
import com.neurix.hris.master.jamkerja.dao.JamKerjaDao;
import com.neurix.hris.master.jamkerja.model.ImHrisJamKerja;
import com.neurix.hris.master.jamkerja.model.JamKerja;
import com.neurix.hris.master.tunjangan.model.ImHrisTunjangan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class JamKerjaBoImpl implements JamKerjaBo {
    protected static transient Logger logger = Logger.getLogger(JamKerjaBoImpl.class);

    private JamKerjaDao jamKerjaDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JamKerjaBoImpl.logger = logger;
    }

    public JamKerjaDao getJamKerjaDao() {
        return jamKerjaDao;
    }

    public void setJamKerjaDao(JamKerjaDao jamKerjaDao) {
        this.jamKerjaDao = jamKerjaDao;
    }

    @Override
    public void saveDelete(JamKerja bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(JamKerja bean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.saveEdit] start process >>>");

        boolean saved = false;

        if (bean!=null) {
            ImHrisJamKerja entityData = new ImHrisJamKerja();
            entityData.setJamKerjaId(bean.getJamKerjaId());
            entityData.setStatusGiling(bean.getStatusGiling());
            entityData.setTipePegawaiId(bean.getTipePegawaiId());
            entityData.setHariKerja(bean.getHariKerja());
            entityData.setJamAwalKerja(bean.getJamAwalKerja());
            entityData.setJamAkhirKerja(bean.getJamAkhirKerja());
            entityData.setIstirahatAwal(bean.getIstirahatAwal());
            entityData.setIstirahatAkhir(bean.getIstirahatAkhir());
            entityData.setFlag(bean.getFlag());
            entityData.setBranchId(bean.getBranchId());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());

            try {
                jamKerjaDao.updateAndSave(entityData);
                saved = true;
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }
        logger.info("[ShiftBoImpl.saveEdit] end process <<<");
    }

    @Override
    public JamKerja saveAdd(JamKerja bean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.saveAdd] start process >>>");

        if (bean!=null) {

            String jkId;
            try {
                // Generating ID, get from postgre sequence
                jkId = jamKerjaDao.getNextJamKerjaId();
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence alat id, please info to your admin..." + e.getMessage());
            }

            ImHrisJamKerja entityData = new ImHrisJamKerja();

            entityData.setJamKerjaId(jkId);
            entityData.setStatusGiling(bean.getStatusGiling());
            entityData.setTipePegawaiId(bean.getTipePegawaiId());
            entityData.setHariKerja(bean.getHariKerja());
            entityData.setBranchId(bean.getBranchId());
            entityData.setJamAwalKerja(bean.getJamAwalKerja());
            entityData.setJamAkhirKerja(bean.getJamAkhirKerja());
            entityData.setIstirahatAwal(bean.getIstirahatAwal());
            entityData.setIstirahatAkhir(bean.getIstirahatAkhir());
            entityData.setFlag(bean.getFlag());
            entityData.setAction(bean.getAction());
            entityData.setCreateDateWho(bean.getCreatedWho());
            entityData.setLastUpdateWho(bean.getLastUpdateWho());
            entityData.setCreatedDate(bean.getCreatedDate());
            entityData.setLastUpdate(bean.getLastUpdate());
            try {
                jamKerjaDao.addAndSave(entityData);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[ShiftBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<JamKerja> getByCriteria(JamKerja searchBean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<JamKerja> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getJamKerjaId() != null && !"".equalsIgnoreCase(searchBean.getJamKerjaId())) {
                hsCriteria.put("jam_kerja_id", searchBean.getJamKerjaId());
            }
            if (searchBean.getStatusGiling() != null && !"".equalsIgnoreCase(searchBean.getStatusGiling())) {
                hsCriteria.put("status_giling", searchBean.getStatusGiling());
            }
            if (searchBean.getBranchId() != null && !"".equalsIgnoreCase(searchBean.getBranchId())) {
                hsCriteria.put("branch_id", searchBean.getBranchId());
            }
            if (searchBean.getTipePegawaiId() != null && !"".equalsIgnoreCase(searchBean.getTipePegawaiId())) {
                hsCriteria.put("tipe_pegawai", searchBean.getTipePegawaiId());
            }
            if (searchBean.getHariKerja() != null) {
                hsCriteria.put("hari", searchBean.getHariKerja());
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


            List<ImHrisJamKerja> imHrisJamKerjas = null;
            try {

                imHrisJamKerjas = jamKerjaDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[ShiftBoImpl.getByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imHrisJamKerjas != null){
                JamKerja returnData;
                // Looping from dao to object and save in collection
                for(ImHrisJamKerja listEntity : imHrisJamKerjas){
                    returnData = new JamKerja();
                    returnData.setJamKerjaId(listEntity.getJamKerjaId());
                    returnData.setStatusGiling(listEntity.getStatusGiling());
                    returnData.setTipePegawaiId(listEntity.getTipePegawaiId());
                    returnData.setHariKerja(listEntity.getHariKerja());
                    if (listEntity.getHariKerja() == 2){
                        returnData.setHariName("Senin");
                    }
                    if (listEntity.getHariKerja() == 3){
                        returnData.setHariName("Selasa");
                    }
                    if (listEntity.getHariKerja() == 4){
                        returnData.setHariName("Rabu");
                    }
                    if (listEntity.getHariKerja() == 5){
                        returnData.setHariName("Kamis");
                    }
                    if (listEntity.getHariKerja() == 6){
                        returnData.setHariName("Jum'at");
                    }
                    if (listEntity.getHariKerja() == 7){
                        returnData.setHariName("Sabtu");
                    }
                    if (listEntity.getHariKerja() == 1){
                        returnData.setHariName("Minggu");
                    }
                    returnData.setJamAwalKerja(listEntity.getJamAwalKerja());
                    returnData.setBranchId(listEntity.getBranchId());
                    returnData.setJamAkhirKerja(listEntity.getJamAkhirKerja());
                    returnData.setCreatedDate(listEntity.getCreatedDate());
                    returnData.setCreatedWho(listEntity.getCreateDateWho());
                    returnData.setLastUpdate(listEntity.getLastUpdate());
                    returnData.setIstirahatAwal(listEntity.getIstirahatAwal());
                    returnData.setIstirahatAkhir(listEntity.getIstirahatAkhir());
                    returnData.setLastUpdateWho(listEntity.getLastUpdateWho());
                    returnData.setFlag(listEntity.getFlag());
                    returnData.setAction(listEntity.getAction());
                    listOfResult.add(returnData);
                }
            }
        }
        logger.info("[ShiftBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public List<JamKerja> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}
