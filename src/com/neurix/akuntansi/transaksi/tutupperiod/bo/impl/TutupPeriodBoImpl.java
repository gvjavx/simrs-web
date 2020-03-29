package com.neurix.akuntansi.transaksi.tutupperiod.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDao;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDetailDao;
import com.neurix.akuntansi.transaksi.saldoakhir.dao.SaldoAkhirDao;
import com.neurix.akuntansi.transaksi.saldoakhir.model.ItAkunSaldoAkhirEntity;
import com.neurix.akuntansi.transaksi.saldoakhir.model.SaldoAkhir;
import com.neurix.akuntansi.transaksi.tutupperiod.bo.TutupPeriodBo;
import com.neurix.akuntansi.transaksi.tutupperiod.dao.BatasTutupPeriodDao;
import com.neurix.akuntansi.transaksi.tutupperiod.dao.TutupPeriodDao;
import com.neurix.akuntansi.transaksi.tutupperiod.model.BatasTutupPeriod;
import com.neurix.akuntansi.transaksi.tutupperiod.model.ItAkunTutupPeriodEntity;
import com.neurix.akuntansi.transaksi.tutupperiod.model.ItSimrsBatasTutupPeriodEntity;
import com.neurix.akuntansi.transaksi.tutupperiod.model.TutupPeriod;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.sql.Timestamp;
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

            boolean isClear = false;

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

                        jurnalDetail.setIdTutupPeriod(batasTutupPeriodEntity.getId());
                        jurnalDetail.setBulan(bean.getBulan());
                        jurnalDetail.setTahun(bean.getTahun());
                        jurnalDetail.setUnit(bean.getUnit());

                        ItAkunSaldoAkhirEntity saldoAkhirEntity = new ItAkunSaldoAkhirEntity();
                        saldoAkhirEntity.setSaldoAkhirId(getNextSaldoAkhirId());
                        saldoAkhirEntity.setBranchId(jurnalDetail.getUnit());
                        saldoAkhirEntity.setPeriode(jurnalDetail.getBulan()+"-"+jurnalDetail.getTahun());
                        saldoAkhirEntity.setRekeningId(jurnalDetail.getRekeningId());
                        saldoAkhirEntity.setJumlahDebit(jurnalDetail.getJumlahDebit());
                        saldoAkhirEntity.setJumlahKredit(jurnalDetail.getJumlahKredit());
                        saldoAkhirEntity.setFlag("Y");
                        saldoAkhirEntity.setAction("C");
                        saldoAkhirEntity.setCreatedDate(bean.getCreatedDate());
                        saldoAkhirEntity.setCreatedWho(bean.getCreatedWho());
                        saldoAkhirEntity.setLastUpdate(bean.getLastUpdate());
                        saldoAkhirEntity.setLastUpdateWho(bean.getLastUpdateWho());

                        // mendapatkan data saldo akhir periode sebelumnya dengan rekening_id dan unit
                        SaldoAkhir saldoAkhir = new SaldoAkhir();
                        saldoAkhir.setPeriode(getPeriodeSebelum(jurnalDetail.getBulan(), jurnalDetail.getTahun()));
                        saldoAkhir.setBranchId(jurnalDetail.getUnit());
                        saldoAkhir.setRekeningId(jurnalDetail.getRekeningId());

                        ItAkunSaldoAkhirEntity saldoAkhirLalu = new ItAkunSaldoAkhirEntity();
                        List<ItAkunSaldoAkhirEntity> saldoAkhirEntities = getListEntitySaldoAkhir(saldoAkhir);
                        if (saldoAkhirEntities.size() > 0){
                            saldoAkhirLalu = saldoAkhirEntities.get(0);

                            // jika posisi saldo akhir yang lalu dengan rekening_id yang dicari adalah debit maka akan menambah jumlah debit
                            if ("D".equalsIgnoreCase(saldoAkhirLalu.getPosisi())){
                                jurnalDetail.setJumlahDebit(jurnalDetail.getJumlahDebit().add(saldoAkhirLalu.getJumlahDebit()));
                            } else {
                                // jika saldo akhir lalu adalah kredit maka akan menambah jumlah kredit
                                jurnalDetail.setJumlahKredit(jurnalDetail.getJumlahKredit().add(saldoAkhirLalu.getJumlahKredit()));
                            }
                        }
                        // end

                        if (jurnalDetail.getJumlahDebit().compareTo(jurnalDetail.getJumlahKredit()) == 1){

                            // jika debit lebih besar maka debit - kredit = saldo, posisi = debit
                            saldoAkhirEntity.setPosisi("D");
                            saldoAkhirEntity.setSaldo(jurnalDetail.getJumlahDebit().subtract(jurnalDetail.getJumlahKredit()));
                        } else {

                            // jika kredit lebih besar maka kredit - debit = saldo, posisi = kredit
                            saldoAkhirEntity.setPosisi("K");
                            saldoAkhirEntity.setSaldo(jurnalDetail.getJumlahKredit().subtract(jurnalDetail.getJumlahDebit()));
                        }

                        try {
                            saldoAkhirDao.addAndSave(saldoAkhirEntity);
                        } catch (HibernateException e){
                            logger.error("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. ",e);
                            throw new GeneralBOException("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. ",e);
                        }
                    }

                    // mengambil parent diatasnya sekaligus insert;
                    List<TutupPeriod> tutupPeriods = prosesTutupPeriod(jurnalDatas);
                    if (tutupPeriods.size() == 0){
                        isClear = true;
                    }
                }


                // jika ditemukan update
                batasTutupPeriodEntity.setFlagTutup(bean.getFlagTutup());
                batasTutupPeriodEntity.setAction("U");
                batasTutupPeriodEntity.setLastUpdate(bean.getLastUpdate());
                batasTutupPeriodEntity.setLastUpdateWho(bean.getLastUpdateWho());

                if (isClear){
                    try {
                        batasTutupPeriodDao.updateAndSave(batasTutupPeriodEntity);
                    } catch (HibernateException e){
                        logger.error("[TutupPeriodBoImpl.saveSettingPeriod] ERROR. ",e);
                        throw new GeneralBOException("[TutupPeriodBoImpl.saveSettingPeriod] ERROR. ",e);
                    }
                } else {
                    logger.error("[TutupPeriodBoImpl.saveSettingPeriod] ERROR. gagal menyimpan tutup period");
                    throw new GeneralBOException("[TutupPeriodBoImpl.saveSettingPeriod] ERROR. gagal menyimpan tutup period");
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

                        jurnalDetail.setIdTutupPeriod(batasEntity.getId());
                        jurnalDetail.setBulan(bean.getBulan());
                        jurnalDetail.setTahun(bean.getTahun());
                        jurnalDetail.setUnit(bean.getUnit());

                        ItAkunSaldoAkhirEntity saldoAkhirEntity = new ItAkunSaldoAkhirEntity();
                        saldoAkhirEntity.setSaldoAkhirId(getNextSaldoAkhirId());
                        saldoAkhirEntity.setBranchId(jurnalDetail.getUnit());
                        saldoAkhirEntity.setPeriode(jurnalDetail.getBulan()+"-"+jurnalDetail.getTahun());
                        saldoAkhirEntity.setRekeningId(jurnalDetail.getRekeningId());
                        saldoAkhirEntity.setJumlahDebit(jurnalDetail.getJumlahDebit());
                        saldoAkhirEntity.setJumlahKredit(jurnalDetail.getJumlahKredit());
                        saldoAkhirEntity.setFlag("Y");
                        saldoAkhirEntity.setAction("C");
                        saldoAkhirEntity.setCreatedDate(bean.getCreatedDate());
                        saldoAkhirEntity.setCreatedWho(bean.getCreatedWho());
                        saldoAkhirEntity.setLastUpdate(bean.getLastUpdate());
                        saldoAkhirEntity.setLastUpdateWho(bean.getLastUpdateWho());

                        // mendapatkan data saldo akhir periode sebelumnya dengan rekening_id dan unit
                        SaldoAkhir saldoAkhir = new SaldoAkhir();
                        saldoAkhir.setPeriode(getPeriodeSebelum(jurnalDetail.getBulan(), jurnalDetail.getTahun()));
                        saldoAkhir.setBranchId(jurnalDetail.getUnit());
                        saldoAkhir.setRekeningId(jurnalDetail.getRekeningId());

                        ItAkunSaldoAkhirEntity saldoAkhirLalu = new ItAkunSaldoAkhirEntity();
                        List<ItAkunSaldoAkhirEntity> saldoAkhirEntities = getListEntitySaldoAkhir(saldoAkhir);
                        if (saldoAkhirEntities.size() > 0){
                            saldoAkhirLalu = saldoAkhirEntities.get(0);

                            // jika posisi saldo akhir yang lalu dengan rekening_id yang dicari adalah debit maka akan menambah jumlah debit
                            if ("D".equalsIgnoreCase(saldoAkhirLalu.getPosisi())){
                                jurnalDetail.setJumlahDebit(jurnalDetail.getJumlahDebit().add(saldoAkhirLalu.getJumlahDebit()));
                            } else {
                                // jika saldo akhir lalu adalah kredit maka akan menambah jumlah kredit
                                jurnalDetail.setJumlahKredit(jurnalDetail.getJumlahKredit().add(saldoAkhirLalu.getJumlahKredit()));
                            }
                        }
                        // end

                        if (jurnalDetail.getJumlahDebit().compareTo(jurnalDetail.getJumlahKredit()) == 1){

                            // jika debit lebih besar maka debit - kredit = saldo, posisi = debit
                            saldoAkhirEntity.setPosisi("D");
                            saldoAkhirEntity.setSaldo(jurnalDetail.getJumlahDebit().subtract(jurnalDetail.getJumlahKredit()));
                        } else {

                            // jika kredit lebih besar maka kredit - debit = saldo, posisi = kredit
                            saldoAkhirEntity.setPosisi("K");
                            saldoAkhirEntity.setSaldo(jurnalDetail.getJumlahKredit().subtract(jurnalDetail.getJumlahDebit()));
                        }

                        try {
                            saldoAkhirDao.addAndSave(saldoAkhirEntity);
                        } catch (HibernateException e){
                            logger.error("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. ",e);
                            throw new GeneralBOException("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. ",e);
                        }
                    }

                    // mengambil parent dari rekening diatasnya sekaligus insert ke saldo akhir;
                    List<TutupPeriod> tutupPeriods = prosesTutupPeriod(jurnalDatas);
                    if (tutupPeriods.size() == 0){
                        isClear = true;
                    }
                }

                if (isClear){
                    try {
                        batasTutupPeriodDao.addAndSave(batasEntity);
                    } catch (HibernateException e){
                        logger.error("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. ",e);
                        throw new GeneralBOException("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. ",e);
                    }
                } else {
                    logger.error("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. gagal menyimpan tutup period");
                    throw new GeneralBOException("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. gagal menyimpan tutup period");
                }
            }
        }
    }

    @Override
    public void saveUpdateLockPeriod(TutupPeriod bean) throws GeneralBOException {

        BatasTutupPeriod batasTutupPeriod = new BatasTutupPeriod();
        batasTutupPeriod.setTahun(bean.getTahun());
        batasTutupPeriod.setBulan(bean.getBulan());
        batasTutupPeriod.setUnit(bean.getUnit());

        List<ItSimrsBatasTutupPeriodEntity> batasTutupPeriodEntities = getListEntityBatasTutupPeriode(batasTutupPeriod);
        if (batasTutupPeriodEntities.size() > 0){

            ItSimrsBatasTutupPeriodEntity batasTutupPeriodEntity = batasTutupPeriodEntities.get(0);

            // jika ditemukan update
            batasTutupPeriodEntity.setFlagTutup(bean.getFlagTutup());
            batasTutupPeriodEntity.setAction("U");
            batasTutupPeriodEntity.setLastUpdate(bean.getLastUpdate());
            batasTutupPeriodEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                batasTutupPeriodDao.updateAndSave(batasTutupPeriodEntity);
            } catch (HibernateException e){
                logger.error("[TutupPeriodBoImpl.saveUpdateLockPeriod] ERROR. ",e);
                throw new GeneralBOException("[TutupPeriodBoImpl.saveUpdateLockPeriod] ERROR. ",e);
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

            try {
                batasTutupPeriodDao.addAndSave(batasEntity);
            } catch (HibernateException e){
                logger.error("[TutupPeriodBoImpl.saveUpdateLockPeriod] ERROR. ",e);
                throw new GeneralBOException("[TutupPeriodBoImpl.saveUpdateLockPeriod] ERROR. ",e);
            }
        }
    }

    public List<TutupPeriod> prosesTutupPeriod(List<TutupPeriod> periods) throws GeneralBOException{

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        List<TutupPeriod> parentPeriods = new ArrayList<>();

        // jika parameter periods sebagai child tidak kosong
        // maka prosess untuk insert, sum, collecting data parrentPeriods;
        if (periods.size() > 0){

            // pengulangan dari parameter periods sebagai child;
            // pengulangan untuk mencari parent dan menghitung jumlah debit kredit pada parent tersebut;
            int i = 0;
            for (TutupPeriod childPeriod : periods){

                TutupPeriod tutupPeriod = new TutupPeriod();
                tutupPeriod.setRekeningId(childPeriod.getParentId());
                tutupPeriod.setIdTutupPeriod(childPeriod.getIdTutupPeriod());
                tutupPeriod.setJumlahDebit(childPeriod.getJumlahDebit());
                tutupPeriod.setJumlahKredit(childPeriod.getJumlahKredit());
                tutupPeriod.setUnit(childPeriod.getUnit());
                tutupPeriod.setBulan(childPeriod.getBulan());
                tutupPeriod.setTahun(childPeriod.getTahun());

                ImKodeRekeningEntity kodeRekening = getKodeRekeningById(tutupPeriod.getRekeningId());
                if ( kodeRekening != null && kodeRekening.getRekeningId() != null){
                    tutupPeriod.setParentId(kodeRekening.getParentId());
                    tutupPeriod.setKodeRekening(kodeRekening.getKodeRekening());
                }

                // jika parent_id != null untuk filtering level tertinggi;
                // parent_id = null berarti diatas level tertinggi maka logic if false. tidak dapat lanjut proses;
                if (!"".equalsIgnoreCase(tutupPeriod.getParentId()) && tutupPeriod.getParentId() != null){

                    // jika list parent kosong
                    if (parentPeriods.size() == 0){
                        parentPeriods.add(tutupPeriod);
                        i++;
                    } else {

                        TutupPeriod minParentPeriod = parentPeriods.get(i-1);
                        // jika parent index sebelumnya ditemukan parent rekening_id sama dengan child parent_id;
                        // maka dilakukan sum kredit, debit;
                        // kemudian update parent period;
                        if (minParentPeriod.getRekeningId().equalsIgnoreCase(tutupPeriod.getRekeningId())){

                            tutupPeriod.setJumlahDebit(minParentPeriod.getJumlahDebit().add(tutupPeriod.getJumlahDebit()));
                            tutupPeriod.setJumlahKredit(minParentPeriod.getJumlahKredit().add(tutupPeriod.getJumlahKredit()));

                            // update list parrentPeriods;
                            parentPeriods.remove(minParentPeriod);
                            parentPeriods.add(tutupPeriod);
                        } else {

                            // jika tidak parent rekening_id tidak sama dengan child parent_id;
                            // maka memasukan object baru pada list parrentPeriods;
                            // dan menambah nilai i untuk index parrentPeriods;
                            tutupPeriod.setJumlahDebit(childPeriod.getJumlahDebit());
                            tutupPeriod.setJumlahKredit(childPeriod.getJumlahKredit());
                            parentPeriods.add(tutupPeriod);
                            i++;
                        }
                    }
                }
            }

            // setelah data parent di collect kemudian di insert ke table tutup period
            if (parentPeriods.size() > 0){

                for (TutupPeriod parentPeriod : parentPeriods){

                    ItAkunSaldoAkhirEntity saldoAkhirEntity = new ItAkunSaldoAkhirEntity();
                    saldoAkhirEntity.setSaldoAkhirId(getNextSaldoAkhirId());
                    saldoAkhirEntity.setBranchId(parentPeriod.getUnit());
                    saldoAkhirEntity.setPeriode(parentPeriod.getBulan()+"-"+parentPeriod.getTahun());
                    saldoAkhirEntity.setRekeningId(parentPeriod.getRekeningId());
                    saldoAkhirEntity.setJumlahDebit(parentPeriod.getJumlahDebit());
                    saldoAkhirEntity.setJumlahKredit(parentPeriod.getJumlahKredit());
                    saldoAkhirEntity.setFlag("Y");
                    saldoAkhirEntity.setAction("C");
                    saldoAkhirEntity.setCreatedDate(time);
                    saldoAkhirEntity.setCreatedWho(userLogin);
                    saldoAkhirEntity.setLastUpdate(time);
                    saldoAkhirEntity.setLastUpdateWho(userLogin);

                    // mendapatkan data saldo akhir periode sebelumnya dengan rekening_id dan unit
                    SaldoAkhir saldoAkhir = new SaldoAkhir();
                    saldoAkhir.setPeriode(getPeriodeSebelum(parentPeriod.getBulan(), parentPeriod.getTahun()));
                    saldoAkhir.setBranchId(parentPeriod.getUnit());
                    saldoAkhir.setRekeningId(parentPeriod.getRekeningId());

                    ItAkunSaldoAkhirEntity saldoAkhirLalu = new ItAkunSaldoAkhirEntity();
                    List<ItAkunSaldoAkhirEntity> saldoAkhirEntities = getListEntitySaldoAkhir(saldoAkhir);
                    if (saldoAkhirEntities.size() > 0){
                        saldoAkhirLalu = saldoAkhirEntities.get(0);

                        // jika posisi saldo akhir yang lalu dengan rekening_id yang dicari adalah debit maka akan menambah jumlah debit
                        if ("D".equalsIgnoreCase(saldoAkhirLalu.getPosisi())){
                            parentPeriod.setJumlahDebit(parentPeriod.getJumlahDebit().add(saldoAkhirLalu.getJumlahDebit()));
                        } else {
                            // jika saldo akhir lalu adalah kredit maka akan menambah jumlah kredit
                            parentPeriod.setJumlahKredit(parentPeriod.getJumlahKredit().add(saldoAkhirLalu.getJumlahKredit()));
                        }
                    }
                    // end

                    if (parentPeriod.getJumlahDebit().compareTo(parentPeriod.getJumlahKredit()) == 1){

                        // jika debit lebih besar maka debit - kredit = saldo, posisi = debit
                        saldoAkhirEntity.setPosisi("D");
                        saldoAkhirEntity.setSaldo(parentPeriod.getJumlahDebit().subtract(parentPeriod.getJumlahKredit()));
                    } else {

                        // jika kredit lebih besar maka kredit - debit = saldo, posisi = kredit
                        saldoAkhirEntity.setPosisi("K");
                        saldoAkhirEntity.setSaldo(parentPeriod.getJumlahKredit().subtract(parentPeriod.getJumlahDebit()));
                    }

                    try {
                        saldoAkhirDao.addAndSave(saldoAkhirEntity);
                    } catch (HibernateException e){
                        logger.error("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. ",e);
                        throw new GeneralBOException("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. ",e);
                    }
                }

                // prosess kembali dengan parameter parrent yang sudah di collect dan di insert;
                // parameter parent diprosess kembali sebagai child;
                prosesTutupPeriod(parentPeriods);
            }
        }

        return new ArrayList<>();
    }

    private String getPeriodeSebelum(String bulan, String tahun){

        Integer intBulan = Integer.parseInt(bulan) - 1;
        Integer intTahun = Integer.parseInt(tahun);

        if (intBulan.compareTo(0) == 0){
            intBulan = new Integer(12);
            intTahun = intTahun - 1;
        }
        return intBulan+"_"+intTahun;
    }

    private List<ItAkunSaldoAkhirEntity> getListEntitySaldoAkhir(SaldoAkhir bean) throws GeneralBOException{

        List<ItAkunSaldoAkhirEntity> saldoAkhirEntities = new ArrayList<>();

        if (bean != null){

            Map hsCriteria = new HashMap();
            if (bean.getRekeningId() != null){
                hsCriteria.put("rekening_id", bean.getRekeningId());
            }
            if (bean.getRekeningId() != null){
                hsCriteria.put("periode", bean.getPeriode());
            }
            if (bean.getBranchId() != null){
                hsCriteria.put("branch_id", bean.getBranchId());
            }

            try {
                saldoAkhirEntities = saldoAkhirDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[TutupPeriodBoImpl.getListEntitySaldoAkhir] ERROR. ",e);
                throw new GeneralBOException("[TutupPeriodBoImpl.getListEntitySaldoAkhir] ERROR. ",e);
            }
        }
        return saldoAkhirEntities;
    }

    private List<ItAkunTutupPeriodEntity> getListTutupAkunTutupPeriod(TutupPeriod bean) throws GeneralBOException{

        List<ItAkunTutupPeriodEntity> akunTutupPeriodEntities = new ArrayList<>();
        if (bean != null){

            Map hsCriteria = new HashMap();
            if (bean.getIdTutupPeriod() != null){
                hsCriteria.put("id_tutup_period", bean.getIdTutupPeriod());
            }

            try {
                akunTutupPeriodEntities = tutupPeriodDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[TutupPeriodBoImpl.getListAkunTutupPeriod] ERROR. ",e);
                throw new GeneralBOException("[TutupPeriodBoImpl.getListAkunTutupPeriod] ERROR. ",e);
            }
        }

        return akunTutupPeriodEntities;
    }

    private ImKodeRekeningEntity getKodeRekeningById(String id) throws GeneralBOException{

        ImKodeRekeningEntity kodeRekeningEntity = new ImKodeRekeningEntity();

        try {
            kodeRekeningEntity = kodeRekeningDao.getById("rekeningId", id);
        } catch (HibernateException e){
            logger.error("[TutupPeriodBoImpl.getKodeRekeningById] ERROR. ", e);
            throw new GeneralBOException("[TutupPeriodBoImpl.getKodeRekeningById] ERROR. "+ e);
        }

        return kodeRekeningEntity;
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
