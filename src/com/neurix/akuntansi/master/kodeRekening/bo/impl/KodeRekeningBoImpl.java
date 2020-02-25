package com.neurix.akuntansi.master.kodeRekening.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.common.exception.GeneralBOException;
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
public class KodeRekeningBoImpl implements KodeRekeningBo {

    protected static transient Logger logger = Logger.getLogger(KodeRekeningBoImpl.class);
    private KodeRekeningDao kodeRekeningDao;

    @Override
    public void saveDelete(KodeRekening bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(KodeRekening bean) throws GeneralBOException {
        logger.info("[KodeRekeningBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            ImKodeRekeningEntity kodeRekeningEntity= null;
            try {
                // Get data from database by ID
                kodeRekeningEntity = kodeRekeningDao.getById("rekeningId", bean.getRekeningId());
            } catch (HibernateException e) {
                logger.error("[KodeRekeningBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data, please inform to your admin...," + e.getMessage());
            }

            if (kodeRekeningEntity != null) {
                if (("Y").equalsIgnoreCase(bean.getFlag())){
                 kodeRekeningEntity.setTipeRekeningId(bean.getTipeRekeningId());
                 kodeRekeningEntity.setKodeRekening(bean.getKodeRekening());
                 kodeRekeningEntity.setNamaKodeRekening(bean.getNamaKodeRekening());

                    String[] coa = bean.getKodeRekening().split("\\.");
                    if (coa.length==1){
                        kodeRekeningEntity.setParentId("-");
                    }else{
                        String coaParent ="";
                        for (int i=0;i<coa.length-1;i++){
                            if (i==0){
                                coaParent=coaParent+coa[i];
                            }else{
                                coaParent=coaParent+"."+coa[i];
                            }
                        }
                        List<ImKodeRekeningEntity> kodeRekeningEntityList = kodeRekeningDao.getIdByCoa(coaParent);
                        for (ImKodeRekeningEntity kodeRekeningEntity1 : kodeRekeningEntityList){
                            kodeRekeningEntity.setParentId(kodeRekeningEntity1.getRekeningId());
                        }
                    }
                    kodeRekeningEntity.setLevel((long) coa.length);
                }

                kodeRekeningEntity.setFlag(bean.getFlag());
                kodeRekeningEntity.setAction(bean.getAction());
                kodeRekeningEntity.setLastUpdateWho(bean.getLastUpdateWho());
                kodeRekeningEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Update into database
                    kodeRekeningDao.updateAndSave(kodeRekeningEntity);
                } catch (HibernateException e) {
                    logger.error("[KodeRekeningBoImpl.saveEdit] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data, please info to your admin..." + e.getMessage());
                }
            } else {
                logger.error("[KodeRekeningBoImpl.saveEdit] Error, not found data with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data with request id, please check again your data ...");
            }
        }
        logger.info("[KodeRekeningBoImpl.saveEdit] end process <<<");
    }

    @Override
    public List<KodeRekening> getByCriteria(KodeRekening searchBean) throws GeneralBOException {
        logger.info("[KodeRekeningBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<KodeRekening> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();

            if (searchBean.getRekeningId() != null && !"".equalsIgnoreCase(searchBean.getRekeningId())) {
                hsCriteria.put("rekening_id", searchBean.getRekeningId());
            }
            if (searchBean.getNamaKodeRekening() != null && !"".equalsIgnoreCase(searchBean.getNamaKodeRekening())) {
                hsCriteria.put("nama_kode_rekening", searchBean.getNamaKodeRekening());
            }

            if (searchBean.getKodeRekening() != null && !"".equalsIgnoreCase(searchBean.getKodeRekening())) {
                hsCriteria.put("kode_rekening", searchBean.getKodeRekening());
            }
            if (searchBean.getTipeRekeningId() != null && !"".equalsIgnoreCase(searchBean.getTipeRekeningId())) {
                hsCriteria.put("tipe_rekening_id", searchBean.getTipeRekeningId());
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


            List<ImKodeRekeningEntity> imKodeRekeningEntityList = null;
            try {

                imKodeRekeningEntityList = kodeRekeningDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[KodeRekeningBoImpl.getSearchIjinByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imKodeRekeningEntityList != null){
                KodeRekening returnKodeRekening;
                // Looping from dao to object and save in collection
                for(ImKodeRekeningEntity kodeRekeningEntity : imKodeRekeningEntityList){
                    returnKodeRekening = new KodeRekening();
                    returnKodeRekening.setRekeningId(kodeRekeningEntity.getRekeningId());
                    returnKodeRekening.setKodeRekening(kodeRekeningEntity.getKodeRekening());
                    returnKodeRekening.setNamaKodeRekening(kodeRekeningEntity.getNamaKodeRekening());
                    returnKodeRekening.setParentId(kodeRekeningEntity.getParentId());
                    returnKodeRekening.setTipeRekeningId(kodeRekeningEntity.getTipeRekeningId());
                    returnKodeRekening.setLevel(kodeRekeningEntity.getLevel());

                    if (kodeRekeningEntity.getTipeRekeningId()!=null){
                        returnKodeRekening.setTipeRekeningName("Jurnal");
                    }
                    returnKodeRekening.setCreatedWho(kodeRekeningEntity.getCreatedWho());
                    returnKodeRekening.setCreatedDate(kodeRekeningEntity.getCreatedDate());
                    returnKodeRekening.setLastUpdate(kodeRekeningEntity.getLastUpdate());
                    returnKodeRekening.setAction(kodeRekeningEntity.getAction());
                    returnKodeRekening.setFlag(kodeRekeningEntity.getFlag());
                    listOfResult.add(returnKodeRekening);
                }
            }
        }
        logger.info("[KodeRekeningBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public KodeRekening saveAdd(KodeRekening bean) throws GeneralBOException {
        logger.info("[KodeRekeningBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String kodeRekeningId;
            try {
                // Generating ID, get from postgre sequence
                kodeRekeningId = kodeRekeningDao.getNextKodeRekeningId();
            } catch (HibernateException e) {
                logger.error("[KodeRekeningBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when getting sequence id, please info to your admin..." + e.getMessage());
            }
            // creating object entity serializable
            ImKodeRekeningEntity imKodeRekeningEntity = new ImKodeRekeningEntity();

            imKodeRekeningEntity.setRekeningId(kodeRekeningId);
            imKodeRekeningEntity.setKodeRekening(bean.getKodeRekening());
            imKodeRekeningEntity.setNamaKodeRekening(bean.getNamaKodeRekening());
            imKodeRekeningEntity.setTipeRekeningId(bean.getTipeRekeningId());

            String[] coa = bean.getKodeRekening().split("\\.");
            if (coa.length==1){
                imKodeRekeningEntity.setParentId("-");
            }else{
                String coaParent ="";
                for (int i=0;i<coa.length-1;i++){
                    if (i==0){
                        coaParent=coaParent+coa[i];
                    }else{
                        coaParent=coaParent+"."+coa[i];
                    }
                }
                List<ImKodeRekeningEntity> kodeRekeningEntityList = kodeRekeningDao.getIdByCoa(coaParent);
                for (ImKodeRekeningEntity kodeRekeningEntity : kodeRekeningEntityList){
                    imKodeRekeningEntity.setParentId(kodeRekeningEntity.getRekeningId());
                }
            }
            imKodeRekeningEntity.setLevel((long) coa.length);
            imKodeRekeningEntity.setFlag(bean.getFlag());
            imKodeRekeningEntity.setAction(bean.getAction());
            imKodeRekeningEntity.setCreatedWho(bean.getCreatedWho());
            imKodeRekeningEntity.setLastUpdateWho(bean.getLastUpdateWho());
            imKodeRekeningEntity.setCreatedDate(bean.getCreatedDate());
            imKodeRekeningEntity.setLastUpdate(bean.getLastUpdate());

            try {
                // insert into database
                kodeRekeningDao.addAndSave(imKodeRekeningEntity);
            } catch (HibernateException e) {
                logger.error("[KodeRekeningBoImpl.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when saving new data , please info to your admin..." + e.getMessage());
            }
        }

        logger.info("[KodeRekeningBoImpl.saveAdd] end process <<<");
        return null;
    }
    @Override
    public List<KodeRekening> typeaheadKodeRekening(String coa) throws GeneralBOException {
        logger.info("[KodeRekeningBoImpl.typeaheadKodeRekening] start process >>>");

        // Mapping with collection and put
        List<KodeRekening> listOfResult = new ArrayList();
        List<ImKodeRekeningEntity> imKodeRekeningEntityList = null;
        try {

            imKodeRekeningEntityList = kodeRekeningDao.getKodeRekeningListByLikeCoa(coa);
        } catch (HibernateException e) {
            logger.error("[KodeRekeningBoImpl.typeaheadKodeRekening] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }

        if(imKodeRekeningEntityList != null){
            KodeRekening returnKodeRekening;
            // Looping from dao to object and save in collection
            for(ImKodeRekeningEntity kodeRekeningEntity : imKodeRekeningEntityList){
                returnKodeRekening = new KodeRekening();
                returnKodeRekening.setRekeningId(kodeRekeningEntity.getRekeningId());
                returnKodeRekening.setKodeRekening(kodeRekeningEntity.getKodeRekening());
                returnKodeRekening.setNamaKodeRekening(kodeRekeningEntity.getNamaKodeRekening());
                returnKodeRekening.setParentId(kodeRekeningEntity.getParentId());
                returnKodeRekening.setTipeRekeningId(kodeRekeningEntity.getTipeRekeningId());

                returnKodeRekening.setCreatedWho(kodeRekeningEntity.getCreatedWho());
                returnKodeRekening.setCreatedDate(kodeRekeningEntity.getCreatedDate());
                returnKodeRekening.setLastUpdate(kodeRekeningEntity.getLastUpdate());
                returnKodeRekening.setAction(kodeRekeningEntity.getAction());
                returnKodeRekening.setFlag(kodeRekeningEntity.getFlag());
                listOfResult.add(returnKodeRekening);
            }
        }
        logger.info("[KodeRekeningBoImpl.typeaheadKodeRekening] end process <<<");

        return listOfResult;
    }
    @Override
    public List<KodeRekening> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }


    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        KodeRekeningBoImpl.logger = logger;
    }

    public KodeRekeningDao getKodeRekeningDao() {
        return kodeRekeningDao;
    }

    public void setKodeRekeningDao(KodeRekeningDao kodeRekeningDao) {
        this.kodeRekeningDao = kodeRekeningDao;
    }


}
