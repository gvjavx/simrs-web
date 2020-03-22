package com.neurix.akuntansi.transaksi.tutupperiod.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDao;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDetailDao;
import com.neurix.akuntansi.transaksi.saldoakhir.dao.SaldoAkhirDao;
import com.neurix.akuntansi.transaksi.tutupperiod.bo.TutupPeriodBo;
import com.neurix.akuntansi.transaksi.tutupperiod.dao.BatasTutupPeriodDao;
import com.neurix.akuntansi.transaksi.tutupperiod.dao.TutupPeriodDao;
import com.neurix.akuntansi.transaksi.tutupperiod.model.BatasTutupPeriod;
import com.neurix.akuntansi.transaksi.tutupperiod.model.ItAkunTutupPeriodEntity;
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
    private JurnalDao jurnalDao;
    private JurnalDetailDao jurnalDetailDao;
    private SaldoAkhirDao saldoAkhirDao;
    private KodeRekeningDao kodeRekeningDao;
    private TutupPeriodDao tutupPeriodDao;

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

                ItSimrsBatasTutupPeriodEntity batasTutupPeriodEntity = batasTutupPeriodEntities.get(0);

                TutupPeriod tutupPeriod = new TutupPeriod();
                tutupPeriod.setTahun(bean.getTahun());
                tutupPeriod.setBulan(bean.getBulan());
                tutupPeriod.setUnit(bean.getUnit());

                List<TutupPeriod> jurnalDatas = tutupPeriodDao.getListDetailJurnalByCriteria(tutupPeriod);
                if (jurnalDatas.size() > 0) {

                    for (TutupPeriod jurnalDetail : jurnalDatas) {

                        ItAkunTutupPeriodEntity akunTutupPeriodEntity = new ItAkunTutupPeriodEntity();
                        akunTutupPeriodEntity.setId(getNextTutupPeriodId());
                        akunTutupPeriodEntity.setIdTutupPeriod(batasTutupPeriodEntity.getId());
                        akunTutupPeriodEntity.setKodeRekening(jurnalDetail.getKodeRekening());
                        akunTutupPeriodEntity.setRekeningId(jurnalDetail.getRekeningId());
                        akunTutupPeriodEntity.setParentId(jurnalDetail.getParentId());
                        akunTutupPeriodEntity.setFlag("Y");
                        akunTutupPeriodEntity.setAction("C");
                        akunTutupPeriodEntity.setCreatedDate(jurnalDetail.getCreatedDate());
                        akunTutupPeriodEntity.setCreatedWho(jurnalDetail.getCreatedWho());
                        akunTutupPeriodEntity.setLastUpdate(jurnalDetail.getLastUpdate());
                        akunTutupPeriodEntity.setLastUpdateWho(jurnalDetail.getLastUpdateWho());

                        try {
                            tutupPeriodDao.addAndSave(akunTutupPeriodEntity);
                        } catch (HibernateException e) {
                            logger.error("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. ", e);
                            throw new GeneralBOException("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. ", e);
                        }
                    }
                }


                // jika ditemukan update
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

                TutupPeriod tutupPeriod = new TutupPeriod();
                tutupPeriod.setTahun(bean.getTahun());
                tutupPeriod.setBulan(bean.getBulan());
                tutupPeriod.setUnit(bean.getUnit());

                // if tutup period
                List<TutupPeriod> jurnalDatas = tutupPeriodDao.getListDetailJurnalByCriteria(tutupPeriod);
                if (jurnalDatas.size() > 0) {

                    for (TutupPeriod jurnalDetail : jurnalDatas) {

                        ItAkunTutupPeriodEntity akunTutupPeriodEntity = new ItAkunTutupPeriodEntity();
                        akunTutupPeriodEntity.setId(getNextTutupPeriodId());
                        akunTutupPeriodEntity.setIdTutupPeriod(batasEntity.getId());
                        akunTutupPeriodEntity.setKodeRekening(jurnalDetail.getKodeRekening());
                        akunTutupPeriodEntity.setRekeningId(jurnalDetail.getRekeningId());
                        akunTutupPeriodEntity.setParentId(jurnalDetail.getParentId());
                        akunTutupPeriodEntity.setFlag("Y");
                        akunTutupPeriodEntity.setAction("C");
                        akunTutupPeriodEntity.setCreatedDate(jurnalDetail.getCreatedDate());
                        akunTutupPeriodEntity.setCreatedWho(jurnalDetail.getCreatedWho());
                        akunTutupPeriodEntity.setLastUpdate(jurnalDetail.getLastUpdate());
                        akunTutupPeriodEntity.setLastUpdateWho(jurnalDetail.getLastUpdateWho());

                        try {
                            tutupPeriodDao.addAndSave(akunTutupPeriodEntity);
                        } catch (HibernateException e) {
                            logger.error("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. ", e);
                            throw new GeneralBOException("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. ", e);
                        }
                    }
                }

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

    private String getNextSaldoAkhirId(){
        String id = "";
        try {
            id = saldoAkhirDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[TutupPeriodBoImpl.getNextSaldoAkhirId] ERROR. ", e);
        }

        return id;
    }

    private String getNextTutupPeriodId(){
        String id = "";
        try {
            id = tutupPeriodDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[TutupPeriodBoImpl.getNextTutupPeriodId] ERROR. ", e);
        }

        return id;
    }

    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
    }

    public void setBatasTutupPeriodDao(BatasTutupPeriodDao batasTutupPeriodDao) {
        this.batasTutupPeriodDao = batasTutupPeriodDao;
    }

    public void setJurnalDao(JurnalDao jurnalDao) {
        this.jurnalDao = jurnalDao;
    }

    public void setJurnalDetailDao(JurnalDetailDao jurnalDetailDao) {
        this.jurnalDetailDao = jurnalDetailDao;
    }

    public void setSaldoAkhirDao(SaldoAkhirDao saldoAkhirDao) {
        this.saldoAkhirDao = saldoAkhirDao;
    }

    public void setKodeRekeningDao(KodeRekeningDao kodeRekeningDao) {
        this.kodeRekeningDao = kodeRekeningDao;
    }

    public void setTutupPeriodDao(TutupPeriodDao tutupPeriodDao) {
        this.tutupPeriodDao = tutupPeriodDao;
    }
}
