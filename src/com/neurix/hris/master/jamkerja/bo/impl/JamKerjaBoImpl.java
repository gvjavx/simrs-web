package com.neurix.hris.master.jamkerja.bo.impl;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.group.bo.GroupBo;
import com.neurix.hris.master.group.dao.GroupDao;
import com.neurix.hris.master.group.model.Group;
import com.neurix.hris.master.group.model.ImHrisGroupEntity;
import com.neurix.hris.master.group.model.ImHrisGroupHistory;
import com.neurix.hris.master.jamkerja.bo.JamKerjaBo;
import com.neurix.hris.master.jamkerja.dao.JamKerjaDao;
import com.neurix.hris.master.jamkerja.model.ImHrisJamKerja;
import com.neurix.hris.master.jamkerja.model.ImHrisJamKerjaHistory;
import com.neurix.hris.master.jamkerja.model.JamKerja;
import com.neurix.hris.master.tipepegawai.bo.TipePegawaiBo;
import com.neurix.hris.master.tipepegawai.model.TipePegawai;
import com.neurix.hris.master.tunjangan.model.ImHrisTunjangan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

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
            String jamKerjaId = bean.getJamKerjaId();
            ImHrisJamKerja entityData = null;
            ImHrisJamKerjaHistory jamKerjaHistory = new ImHrisJamKerjaHistory();
            String jamKerjaHistoryId = "";

            try {
                // Get data from database by ID
                entityData = jamKerjaDao.getById("jamKerjaId", jamKerjaId);
                jamKerjaHistoryId = jamKerjaDao.getNextJamKerjaHistoryId();
            } catch (HibernateException e) {
                logger.error("[AlatBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }


            if (entityData != null){
                jamKerjaHistory.setJamKerjaIdHistory(jamKerjaHistoryId);
                jamKerjaHistory.setJamKerjaId(entityData.getJamKerjaId());
                jamKerjaHistory.setTipePegawaiId(entityData.getTipePegawaiId());
                jamKerjaHistory.setHariKerja(entityData.getHariKerja());
                jamKerjaHistory.setJamAwalKerja(entityData.getJamAwalKerja());
                jamKerjaHistory.setJamAkhirKerja(entityData.getJamAkhirKerja());
                jamKerjaHistory.setBranchId(entityData.getBranchId());
                jamKerjaHistory.setIstirahatAwal(entityData.getIstirahatAwal());
                jamKerjaHistory.setIstirahatAkhir(entityData.getIstirahatAkhir());
                jamKerjaHistory.setCreatedDate(entityData.getCreatedDate());
                jamKerjaHistory.setCreateDateWho(entityData.getCreateDateWho());
                jamKerjaHistory.setLastUpdate(entityData.getLastUpdate());
                jamKerjaHistory.setLastUpdateWho(entityData.getLastUpdateWho());
                jamKerjaHistory.setFlag(entityData.getFlag());
                jamKerjaHistory.setAction(entityData.getAction());

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
                    jamKerjaDao.addAndSaveHistory(jamKerjaHistory);
                    saved = true;
                } catch (HibernateException e) {
                    logger.error("[ShiftBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data alat, please info to your admin..." + e.getMessage());
                }
            }
        }
        logger.info("[ShiftBoImpl.saveEdit] end process <<<");
    }

    @Override
    public JamKerja saveAdd(JamKerja bean) throws GeneralBOException {
        logger.info("[ShiftBoImpl.saveAdd] start process >>>");

        if (bean!=null) {
            List<ImHrisJamKerja> jamKerjaList = new ArrayList();
            try {
                jamKerjaList = jamKerjaDao.getJamKerjaByBranchAndHari(bean.getBranchId(), bean.getHariKerja());
            } catch (HibernateException e) {
                logger.error("[JamKerjaBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Error when retrieving Jam Kerja by Branch and Hari, " + e.getMessage());
            }
            if (jamKerjaList.size() == 0) {
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
                    throw new GeneralBOException("Found problem when saving new data Jam Kerja, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[JamKerjaBoImpl.saveAdd] Error, Data Jam Kerja Br dan Hari tersebut sudah ada.");
                throw new GeneralBOException("Data dengan branch dan hari tersebut sudah tersedia.");
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
                    ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                    if (listEntity.getBranchId() != null){
                        Branch branch = new Branch();
                        BranchBo branchBo = (BranchBo) context.getBean("branchBoProxy");
                        branch.setBranchId(listEntity.getBranchId());
                        branch.setFlag("Y");
                        List<Branch> branches = branchBo.getByCriteria(branch);
                        String branchName = branches.get(0).getBranchName();
                        returnData.setBranchName(branchName);
                    }
                    if (listEntity.getTipePegawaiId() != null){
                        if (!"".equalsIgnoreCase(listEntity.getTipePegawaiId())){
                            TipePegawai tipePegawai = new TipePegawai();
                            TipePegawaiBo tipePegawaiBo = (TipePegawaiBo) context.getBean("tipePegawaiBoProxy");
                            tipePegawai.setTipePegawaiId(listEntity.getTipePegawaiId());
                            tipePegawai.setFlag("Y");
                            List<TipePegawai> tipePegawaiList = tipePegawaiBo.getByCriteria(tipePegawai);
                            String tipePegawaiName = tipePegawaiList.get(0).getTipePegawaiName();
                            returnData.setTipePegawaiName(tipePegawaiName);
                        }else {
                            returnData.setTipePegawaiName("");
                        }
                    }else {
                        returnData.setTipePegawaiName("");
                    }

                    returnData.setJamAkhirKerja(listEntity.getJamAkhirKerja());
                    returnData.setCreatedDate(listEntity.getCreatedDate());
                    returnData.setCreatedWho(listEntity.getCreateDateWho());
                    returnData.setLastUpdate(listEntity.getLastUpdate());
                    returnData.setIstirahatAwal(listEntity.getIstirahatAwal());
                    returnData.setIstirahatAkhir(listEntity.getIstirahatAkhir());
                    returnData.setLastUpdateWho(listEntity.getLastUpdateWho());
                    returnData.setFlag(listEntity.getFlag());
                    returnData.setAction(listEntity.getAction());

                    returnData.setStCreatedDate(listEntity.getCreatedDate().toString());
                    returnData.setStLastUpdate(listEntity.getLastUpdate().toString());
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
