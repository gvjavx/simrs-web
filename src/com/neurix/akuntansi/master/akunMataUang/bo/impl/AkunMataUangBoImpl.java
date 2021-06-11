package com.neurix.akuntansi.master.akunMataUang.bo.impl;

import com.neurix.akuntansi.master.akunMataUang.bo.AkunMataUangBo;
import com.neurix.akuntansi.master.akunMataUang.dao.AkunMataUangDao;
import com.neurix.akuntansi.master.akunMataUang.model.AkunMataUang;
import com.neurix.akuntansi.master.akunMataUang.model.ImAkunMataUangEntity;
import com.neurix.akuntansi.master.tipeRekening.dao.TipeRekeningDao;
import com.neurix.akuntansi.master.tipeRekening.model.ImTipeRekeningEntity;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.type.ActiveType;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Aji Noor on 05/02/2021.
 */

public class AkunMataUangBoImpl implements AkunMataUangBo {

    protected static transient Logger logger = Logger.getLogger(AkunMataUangBoImpl.class);
    private AkunMataUangDao akunMataUangDao;

    @Override
    public void saveDelete(AkunMataUang bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(AkunMataUang bean) throws GeneralBOException {
    }

    @Override
    public List<AkunMataUang> getByCriteria(AkunMataUang searchBean) throws GeneralBOException {
        logger.info("[AkunMataUangBoImpl.getByCriteria] start process >>>");

        // Mapping with collection and put
        List<AkunMataUang> listOfResult = new ArrayList();

        if (searchBean != null) {
            Map hsCriteria = new HashMap();
            hsCriteria.put("flag", ActiveType.ACTIVE.getDescription());

            if (searchBean.getKodeMataUang() != null && !"".equalsIgnoreCase(searchBean.getKodeMataUang())) {
                hsCriteria.put("kode_mata_uang", searchBean.getKodeMataUang());
            }
            if (searchBean.getNamaMataUang() != null && !"".equalsIgnoreCase(searchBean.getNamaMataUang())) {
                hsCriteria.put("nama_mata_uang", searchBean.getNamaMataUang());
            }

            if (searchBean.getMataUangId() != null && !"".equalsIgnoreCase(searchBean.getMataUangId())) {
                hsCriteria.put("mata_uang_id", searchBean.getMataUangId());
            }

            if (searchBean.getFlag() != null && !"".equalsIgnoreCase(searchBean.getFlag())) {
                if (ActiveType.NONACTIVE.getDescription().equalsIgnoreCase(searchBean.getFlag())) {
                    hsCriteria.put("flag", "N");
                }
            }
            List<ImAkunMataUangEntity> imAkunMataUangEntityList = null;
            try {
                imAkunMataUangEntityList = akunMataUangDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[AkunMataUangBoImpl.getSearchIjinByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }

            if(imAkunMataUangEntityList != null){
                AkunMataUang returnAkunMataUang;
                // Looping from dao to object and save in collection
                for(ImAkunMataUangEntity akunMataUangEntity : imAkunMataUangEntityList){
                    returnAkunMataUang = new AkunMataUang();
                    returnAkunMataUang.setFlag(akunMataUangEntity.getFlag());
                    returnAkunMataUang.setKodeMataUang(akunMataUangEntity.getKodeMataUang());
                    returnAkunMataUang.setNamaMataUang(akunMataUangEntity.getNamaMataUang());
                    returnAkunMataUang.setMataUangId(akunMataUangEntity.getMataUangId());

                    listOfResult.add(returnAkunMataUang);
                }
            }
        }
        logger.info("[AkunMataUangBoImpl.getByCriteria] end process <<<");

        return listOfResult;
    }

    @Override
    public AkunMataUang saveAdd(AkunMataUang bean) throws GeneralBOException {
        return null;
    }


    @Override
    public List<AkunMataUang> getAll() throws GeneralBOException {
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
        AkunMataUangBoImpl.logger = logger;
    }

    public AkunMataUangDao getAkunMataUangDao() {
        return akunMataUangDao;
    }

    public void setAkunMataUangDao(AkunMataUangDao akunMataUangDao) {
        this.akunMataUangDao = akunMataUangDao;
    }

    @Override
    public List<ImAkunMataUangEntity> getMataUangByKode(String kodeMataUang) throws GeneralBOException {
        return null;
    }

    @Override
    public List<ImAkunMataUangEntity> getCurrencyActive() throws GeneralBOException {
        logger.info("[AkunMataUangBoImpl.getByCriteria] start process >>>");

            List<ImAkunMataUangEntity> imAkunMataUangEntityList = null;
            try {
                Map<String,String> map = new HashMap<>();
                map.put("flag","Y");
                imAkunMataUangEntityList = akunMataUangDao.getByCriteria(map);
            } catch (HibernateException e) {
                logger.error("[AkunMataUangBoImpl.getSearchIjinByCriteria] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
            }
        logger.info("[AkunMataUangBoImpl.getByCriteria] end process <<<");

        return imAkunMataUangEntityList;
    }
}
