package com.neurix.akuntansi.transaksi.tutupperiod.bo.impl;

import com.neurix.akuntansi.transaksi.tutupperiod.bo.TutupPeriodBo;
import com.neurix.akuntansi.transaksi.tutupperiod.dao.BatasTutupPeriodDao;
import com.neurix.akuntansi.transaksi.tutupperiod.model.BatasTutupPeriod;
import com.neurix.akuntansi.transaksi.tutupperiod.model.ItSimrsBatasTutupPeriodEntity;
import com.neurix.akuntansi.transaksi.tutupperiod.model.TutupPeriod;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 18/03/20.
 */
public class TutupPeriodBoImpl implements TutupPeriodBo {
    private static transient Logger logger = Logger.getLogger(TutupPeriodBoImpl.class);

    private RiwayatTindakanDao riwayatTindakanDao;
    private BatasTutupPeriodDao batasTutupPeriodDao;

    @Override
    public void saveSettingPeriod(List<ItSimrsBatasTutupPeriodEntity> batasList) throws GeneralBOException {

        for (ItSimrsBatasTutupPeriodEntity batasEntity : batasList){

            BatasTutupPeriod batasTutupPeriod = new BatasTutupPeriod();
            batasTutupPeriod.setTahun(batasEntity.getTahun());
            batasTutupPeriod.setBulan(batasEntity.getBulan());
            batasTutupPeriod.setUnit(batasEntity.getUnit());

            List<ItSimrsBatasTutupPeriodEntity> batasTutupPeriodEntities = getListEntityBatasTutupPeriode(batasTutupPeriod);
            if (batasTutupPeriodEntities.size() > 0){

                // jika ditemukan update
                ItSimrsBatasTutupPeriodEntity batasTutupPeriodEntity = batasTutupPeriodEntities.get(0);
                if (batasEntity.getTglBatas() != null){
                    batasTutupPeriodEntity.setTglBatas(batasEntity.getTglBatas());
                }
                batasTutupPeriodEntity.setAction("U");
                batasTutupPeriodEntity.setLastUpdate(batasEntity.getLastUpdate());
                batasTutupPeriodEntity.setLastUpdateWho(batasEntity.getLastUpdateWho());
                try {
                    batasTutupPeriodDao.updateAndSave(batasTutupPeriodEntity);
                } catch (HibernateException e){
                    logger.error("[TutupPeriodBoImpl.saveSettingPeriod] ERROR. ",e);
                    throw new GeneralBOException("[TutupPeriodBoImpl.saveSettingPeriod] ERROR. ",e);
                }
            } else {
                batasEntity.setId(getNextIdBatasPeriod());
                try {
                    batasTutupPeriodDao.addAndSave(batasEntity);
                } catch (HibernateException e){
                    logger.error("[TutupPeriodBoImpl.saveSettingPeriod] ERROR. ",e);
                    throw new GeneralBOException("[TutupPeriodBoImpl.saveSettingPeriod] ERROR. ",e);
                }
            }
        }
    }

    @Override
    public List<ItSimrsBatasTutupPeriodEntity> getListEntityBatasTutupPeriode(BatasTutupPeriod bean) throws GeneralBOException{

        Map hsCriteria = new HashMap();
        if (bean.getTahun() != null){
            hsCriteria.put("tahun", bean.getTahun());
        }
        if (bean.getBulan() != null){
            hsCriteria.put("bulan", bean.getBulan());
        }
        if (bean.getUnit() != null){
            hsCriteria.put("unit", bean.getUnit());
        }

        List<ItSimrsBatasTutupPeriodEntity> tutupPeriodEntities = new ArrayList<>();
        try {
            tutupPeriodEntities = batasTutupPeriodDao.getByCriteria(hsCriteria);
        } catch (GeneralBOException e){
            logger.error("[TutupPeriodBoImpl.getListEntityBatasTutupPeriode] ERROR. ",e);
            throw new GeneralBOException("[TutupPeriodBoImpl.getListEntityBatasTutupPeriode] ERROR. ",e);
        }

        return tutupPeriodEntities;
    }

    @Override
    public void saveUpdateTutupPeriod(TutupPeriod bean) throws GeneralBOException {

        if (bean != null){

            BatasTutupPeriod batasTutupPeriod = new BatasTutupPeriod();
            batasTutupPeriod.setTahun(bean.getTahun());
            batasTutupPeriod.setBulan(bean.getBulan());
            batasTutupPeriod.setUnit(bean.getUnit());

            List<ItSimrsBatasTutupPeriodEntity> batasTutupPeriodEntities = getListEntityBatasTutupPeriode(batasTutupPeriod);
            if (batasTutupPeriodEntities.size() > 0){

                // jika ditemukan update
                ItSimrsBatasTutupPeriodEntity batasTutupPeriodEntity = batasTutupPeriodEntities.get(0);
                batasTutupPeriodEntity.setFlagTutup(bean.getFlagTutup());
                batasTutupPeriodEntity.setAction("U");
                batasTutupPeriodEntity.setLastUpdate(bean.getLastUpdate());
                batasTutupPeriodEntity.setLastUpdateWho(bean.getLastUpdateWho());
                try {
                    batasTutupPeriodDao.updateAndSave(batasTutupPeriodEntity);
                } catch (HibernateException e){
                    logger.error("[TutupPeriodBoImpl.saveSettingPeriod] ERROR. ",e);
                    throw new GeneralBOException("[TutupPeriodBoImpl.saveSettingPeriod] ERROR. ",e);
                }
            } else {

                ItSimrsBatasTutupPeriodEntity batasEntity = new ItSimrsBatasTutupPeriodEntity();
                batasEntity.setId(getNextIdBatasPeriod());
                batasEntity.setTahun(bean.getTahun());
                batasEntity.setBulan(bean.getBulan());
                batasEntity.setUnit(bean.getUnit());
                batasEntity.setFlagTutup(bean.getFlagTutup());
                batasEntity.setAction("C");
                batasEntity.setFlag(bean.getFlag());
                batasEntity.setCreatedDate(bean.getCreatedDate());
                batasEntity.setCreatedWho(bean.getCreatedWho());
                batasEntity.setLastUpdate(bean.getLastUpdate());
                batasEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    batasTutupPeriodDao.addAndSave(batasEntity);
                } catch (HibernateException e){
                    logger.error("[TutupPeriodBoImpl.saveSettingPeriod] ERROR. ",e);
                    throw new GeneralBOException("[TutupPeriodBoImpl.saveSettingPeriod] ERROR. ",e);
                }
            }
        }

    }

    private String getNextIdBatasPeriod(){
        String id = "";

        try {
            id = riwayatTindakanDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[TutupPeriodBoImpl.getNextSeq] ERROR. ", e);
        }

        if (!"".equalsIgnoreCase(id)){
            id = "PRD" + id;
        }

        return id;
    }

    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
    }

    public void setBatasTutupPeriodDao(BatasTutupPeriodDao batasTutupPeriodDao) {
        this.batasTutupPeriodDao = batasTutupPeriodDao;
    }
}
