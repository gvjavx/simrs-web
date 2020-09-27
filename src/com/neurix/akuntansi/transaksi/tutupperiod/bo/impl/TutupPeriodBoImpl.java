package com.neurix.akuntansi.transaksi.tutupperiod.bo.impl;

import com.neurix.akuntansi.master.kodeRekening.dao.KodeRekeningDao;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDao;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDetailActivityDao;
import com.neurix.akuntansi.transaksi.jurnal.dao.JurnalDetailDao;
import com.neurix.akuntansi.transaksi.jurnal.dao.pending.JurnalDetailActivityPendingDao;
import com.neurix.akuntansi.transaksi.jurnal.dao.pending.JurnalDetailPendingDao;
import com.neurix.akuntansi.transaksi.jurnal.dao.pending.JurnalPendingDao;
import com.neurix.akuntansi.transaksi.jurnal.model.*;
import com.neurix.akuntansi.transaksi.saldoakhir.dao.SaldoAkhirDao;
import com.neurix.akuntansi.transaksi.saldoakhir.dao.SaldoAkhirDetailDao;
import com.neurix.akuntansi.transaksi.saldoakhir.dao.SaldoAkhirDetailHistoryDao;
import com.neurix.akuntansi.transaksi.saldoakhir.dao.SaldoAkhirHistoryDao;
import com.neurix.akuntansi.transaksi.saldoakhir.model.*;
import com.neurix.akuntansi.transaksi.tutupperiod.bo.TutupPeriodBo;
import com.neurix.akuntansi.transaksi.tutupperiod.dao.BatasTutupPeriodDao;
import com.neurix.akuntansi.transaksi.tutupperiod.dao.TutupPeriodDao;
import com.neurix.akuntansi.transaksi.tutupperiod.model.BatasTutupPeriod;
import com.neurix.akuntansi.transaksi.tutupperiod.model.ItAkunTutupPeriodEntity;
import com.neurix.akuntansi.transaksi.tutupperiod.model.ItSimrsBatasTutupPeriodEntity;
import com.neurix.akuntansi.transaksi.tutupperiod.model.TutupPeriod;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by reza on 18/03/20.
 */
public class TutupPeriodBoImpl implements TutupPeriodBo {
    private static transient Logger logger = Logger.getLogger(TutupPeriodBoImpl.class);

    protected RiwayatTindakanDao riwayatTindakanDao;
    protected BatasTutupPeriodDao batasTutupPeriodDao;
    protected KodeRekeningDao kodeRekeningDao;
    protected JurnalDao jurnalDao;
    protected JurnalDetailDao jurnalDetailDao;
    protected JurnalDetailActivityDao jurnalDetailActivityDao;
    private SaldoAkhirDao saldoAkhirDao;
    private TutupPeriodDao tutupPeriodDao;
    private CheckupDetailDao checkupDetailDao;
    private SaldoAkhirDetailDao saldoAkhirDetailDao;
    private SaldoAkhirHistoryDao saldoAkhirHistoryDao;
    private SaldoAkhirDetailHistoryDao saldoAkhirDetailHistoryDao;

    private JurnalPendingDao jurnalPendingDao;
    private JurnalDetailPendingDao jurnalDetailPendingDao;
    private JurnalDetailActivityPendingDao jurnalDetailActivityPendingDao;
    private boolean isTutupTahun = false;

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

    protected Integer getLowestLevelKodeRekening(){
        return tutupPeriodDao.getLowestLevelKodeRekening();
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

                List<TutupPeriod> listOfTutupData = new ArrayList<>();
                Integer level = tutupPeriodDao.getLowestLevelKodeRekening();
                if (level.compareTo(0) == 1){

                    KodeRekening kodeRekening = new KodeRekening();
                    kodeRekening.setLevel(level.longValue());

                    List<ImKodeRekeningEntity> kodeRekeningEntities = getListEntityKodeRekening(kodeRekening);
                    if (kodeRekeningEntities.size() > 0){
                        for (ImKodeRekeningEntity kodeRekeningEntity : kodeRekeningEntities){

                            TutupPeriod jurnalDetail = new TutupPeriod();
                            jurnalDetail.setIdTutupPeriod(batasTutupPeriodEntity.getId());
                            jurnalDetail.setBulan(bean.getBulan());
                            jurnalDetail.setTahun(bean.getTahun());
                            jurnalDetail.setUnit(bean.getUnit());
                            jurnalDetail.setRekeningId(kodeRekeningEntity.getRekeningId());
                            jurnalDetail.setParentId(kodeRekeningEntity.getParentId());

                            List<TutupPeriod> jurnalDatas = tutupPeriodDao.getListDetailJurnalByCriteria(jurnalDetail);

                            if (jurnalDatas.size() > 0){
                                TutupPeriod jurnalData = jurnalDatas.get(0);

                                jurnalDetail.setJumlahDebit(jurnalData.getJumlahDebit());
                                jurnalDetail.setJumlahKredit(jurnalData.getJumlahKredit());
                            } else {
                                jurnalDetail.setJumlahDebit(new BigDecimal(0));
                                jurnalDetail.setJumlahKredit(new BigDecimal(0));
                            }

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

                                // jika bulan 1, saldo akhir dikurangi dengan jurnal koreksi tutup tahun
                                // saldo bulan lalu dikurangi saldo tutup tahun
                                String posisiJurnalAkhir = "";
                                BigDecimal jurnalAkhirSaldo = new BigDecimal(0);
                                if ("1".equalsIgnoreCase(bean.getBulan())){
                                    String[] tipePeriode = {"12b", "12a", "12"};
                                    for (int i = 0 ; i < 3 ; i++){
                                        Integer intTahunLalu = Integer.valueOf(bean.getTahun()) - 1;
                                        TutupPeriod jurnalAkhirTahun = new TutupPeriod();
                                        jurnalAkhirTahun.setBulan("12");
                                        jurnalAkhirTahun.setTahun(intTahunLalu.toString());
                                        jurnalAkhirTahun.setUnit(bean.getUnit());
                                        jurnalAkhirTahun.setRekeningId(kodeRekeningEntity.getRekeningId());
                                        jurnalAkhirTahun.setTipePeriode(tipePeriode[i]);

                                        TutupPeriod noJurnalAkhir = tutupPeriodDao.getNoJurnalJurnalAkhirTahun(jurnalAkhirTahun);
                                        if (noJurnalAkhir != null){

                                            jurnalAkhirTahun.setNoJurnal(noJurnalAkhir.getNoJurnal());
                                            List<TutupPeriod> listJurnalAkhir = tutupPeriodDao.getListDetailJurnalAkhirTahunByCriteria(jurnalAkhirTahun);
                                            if (listJurnalAkhir.size() > 0){
                                                TutupPeriod jurnalAkhir = listJurnalAkhir.get(0);
                                                posisiJurnalAkhir = jurnalAkhir.getPosisi();
                                                jurnalAkhirSaldo = jurnalAkhir.getSaldo();

//                                            if ("D".equalsIgnoreCase(jurnalAkhir.getPosisi())){
//                                                saldoAkhirLalu.setJumlahDebit(saldoAkhirLalu.getJumlahDebit().add(jurnalAkhir.getSaldo()));
//                                            } else {
//                                                // jika saldo akhir lalu adalah kredit maka akan menambah jumlah kredit
//                                                saldoAkhirLalu.setJumlahKredit(saldoAkhirLalu.getJumlahKredit().add(jurnalAkhir.getSaldo()));
//                                            }
//
//                                            if (saldoAkhirLalu.getJumlahDebit().compareTo(saldoAkhirLalu.getJumlahKredit()) == 1){
//                                                // jika debit lebih besar maka debit - kredit = saldo, posisi = debit
//                                                saldoAkhirLalu.setPosisi("D");
//                                                saldoAkhirLalu.setSaldo(saldoAkhirLalu.getJumlahDebit().subtract(saldoAkhirLalu.getJumlahKredit()));
//                                            } else {
//                                                // jika kredit lebih besar maka kredit - debit = saldo, posisi = kredit
//                                                saldoAkhirLalu.setPosisi("K");
//                                                saldoAkhirLalu.setSaldo(saldoAkhirLalu.getJumlahKredit().subtract(saldoAkhirLalu.getJumlahDebit()));
//                                            }
                                            }

                                            break;
                                        }
                                    }
                                }

                                BigDecimal saldoLalu = saldoAkhirLalu.getSaldo();
                                String posisiLalu = saldoAkhirLalu.getPosisi();
                                if (posisiJurnalAkhir != ""){
                                    if ("D".equalsIgnoreCase(posisiJurnalAkhir)){

                                        if ("D".equalsIgnoreCase(posisiLalu)){
                                            saldoLalu = saldoLalu.add(jurnalAkhirSaldo);
                                        } else {
                                            saldoLalu = saldoLalu.subtract(jurnalAkhirSaldo).abs();
                                        }
//                                        saldoAkhirEntity.setJumlahDebit(saldoAkhirEntity.getJumlahDebit().add(jurnalAkhirSaldo));
//                                        jurnalDetail.setJumlahDebit(saldoAkhirEntity.getJumlahDebit());
                                    } else {

                                        // K
                                        if ("D".equalsIgnoreCase(posisiLalu)){
                                            saldoLalu = saldoLalu.subtract(jurnalAkhirSaldo).abs();
                                        } else {
                                            // jika sama - sama K
                                            saldoLalu = saldoLalu.add(jurnalAkhirSaldo);
                                        }

                                        // jika saldo akhir lalu adalah kredit maka akan menambah jumlah kredit
//                                        saldoAkhirEntity.setJumlahKredit(saldoAkhirEntity.getJumlahKredit().add(jurnalAkhirSaldo));
//                                        jurnalDetail.setJumlahKredit(saldoAkhirEntity.getJumlahKredit());
                                    }
                                }

                                // jika posisi saldo akhir yang lalu dengan rekening_id yang dicari adalah debit maka akan menambah jumlah debit
                                if ("D".equalsIgnoreCase(posisiLalu)){
                                    saldoAkhirEntity.setJumlahDebit(saldoAkhirEntity.getJumlahDebit().add(saldoLalu));
                                    jurnalDetail.setJumlahDebit(saldoAkhirEntity.getJumlahDebit());
                                } else {
                                    // jika saldo akhir lalu adalah kredit maka akan menambah jumlah kredit
                                    saldoAkhirEntity.setJumlahKredit(saldoAkhirEntity.getJumlahKredit().add(saldoLalu));
                                    jurnalDetail.setJumlahKredit(saldoAkhirEntity.getJumlahKredit());
                                }


                            }
                            // end

                            // menghitung saldo
                            if (saldoAkhirEntity.getJumlahDebit().compareTo(saldoAkhirEntity.getJumlahKredit()) == 1){

                                // jika debit lebih besar maka debit - kredit = saldo, posisi = debit
                                saldoAkhirEntity.setPosisi("D");
                                saldoAkhirEntity.setSaldo(saldoAkhirEntity.getJumlahDebit().subtract(saldoAkhirEntity.getJumlahKredit()));
                            } else {

                                // jika kredit lebih besar maka kredit - debit = saldo, posisi = kredit
                                saldoAkhirEntity.setPosisi("K");
                                saldoAkhirEntity.setSaldo(saldoAkhirEntity.getJumlahKredit().subtract(saldoAkhirEntity.getJumlahDebit()));
                            }

                            listOfTutupData.add(jurnalDetail);

                            try {
                                saldoAkhirDao.addAndSave(saldoAkhirEntity);

                                TutupPeriod saldoDetail = new TutupPeriod();
                                saldoDetail.setUnit(saldoAkhirEntity.getBranchId());
                                saldoDetail.setSaldoAkhirId(saldoAkhirEntity.getSaldoAkhirId());
                                saldoDetail.setPeriode(saldoAkhirEntity.getPeriode());
                                saldoDetail.setRekeningId(saldoAkhirEntity.getRekeningId());
                                saldoDetail.setBulan(bean.getBulan());
                                saldoDetail.setTahun(bean.getTahun());
                                saldoDetail.setCreatedDate(bean.getCreatedDate());
                                saldoDetail.setCreatedWho(bean.getCreatedWho());
                                saldoDetail.setLastUpdate(bean.getLastUpdate());
                                saldoDetail.setLastUpdateWho(bean.getLastUpdateWho());

                                // SAVE TO DETAIL
                                saveSaldoAkhirDetail(saldoDetail, saldoAkhirEntity);

                            } catch (HibernateException e){
                                logger.error("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. ",e);
                                throw new GeneralBOException("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. ",e);
                            }
                        }

                        // mengambil parent diatasnya sekaligus insert;
                        List<TutupPeriod> tutupPeriods = prosesTutupPeriod(listOfTutupData, bean, level-1);
                        isClear = tutupPeriods.size() == 0;
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

                        // mencari list pending lalu pindah ke jurnal detail
                        List<String> listJurnalPending = getListNoJurnalPending(bean.getTahun(), bean.getBulan(), bean.getUnit());
                        if (listJurnalPending.size() > 0){
                            movePendingToJurnalDetail(listJurnalPending, bean);
                        }
                    } catch (HibernateException e){
                        logger.error("[TutupPeriodBoImpl.saveSettingPeriod] ERROR. ",e);
                        throw new GeneralBOException("[TutupPeriodBoImpl.saveSettingPeriod] ERROR. ",e);
                    }
                } else {
                    logger.error("[TutupPeriodBoImpl.saveSettingPeriod] ERROR. gagal menyimpan tutup period");
                    throw new GeneralBOException("[TutupPeriodBoImpl.saveSettingPeriod] ERROR. gagal menyimpan tutup period");
                }
            }
        }
    }

    protected void saveUpdateSaldoAkhirTahun(TutupPeriod bean) throws GeneralBOException {

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

                List<TutupPeriod> listOfTutupData = new ArrayList<>();
                Integer level = tutupPeriodDao.getLowestLevelKodeRekening();
                if (level.compareTo(0) == 1){

                    KodeRekening kodeRekening = new KodeRekening();
                    kodeRekening.setLevel(level.longValue());

                    List<ImKodeRekeningEntity> kodeRekeningEntities = getListEntityKodeRekening(kodeRekening);
                    if (kodeRekeningEntities.size() > 0){
                        for (ImKodeRekeningEntity kodeRekeningEntity : kodeRekeningEntities){

                            TutupPeriod jurnalDetail = new TutupPeriod();
                            jurnalDetail.setIdTutupPeriod(batasTutupPeriodEntity.getId());
                            //jurnalDetail.setBulan(bean.getBulan());
                            jurnalDetail.setTahun(bean.getTahun());
                            jurnalDetail.setUnit(bean.getUnit());
                            jurnalDetail.setRekeningId(kodeRekeningEntity.getRekeningId());
                            jurnalDetail.setParentId(kodeRekeningEntity.getParentId());

                            List<TutupPeriod> jurnalDatas = tutupPeriodDao.getListDetailJurnalByCriteria(jurnalDetail);
                            if (jurnalDatas.size() > 0){
                                TutupPeriod jurnalData = jurnalDatas.get(0);

                                jurnalDetail.setJumlahDebit(jurnalData.getJumlahDebit());
                                jurnalDetail.setJumlahKredit(jurnalData.getJumlahKredit());
                            } else {
                                jurnalDetail.setJumlahDebit(new BigDecimal(0));
                                jurnalDetail.setJumlahKredit(new BigDecimal(0));
                            }

                            ItAkunSaldoAkhirHistoryEntity saldoAkhirEntity = new ItAkunSaldoAkhirHistoryEntity();
                            saldoAkhirEntity.setSaldoAkhirId(getNextSaldoAkhirTahunId());
                            saldoAkhirEntity.setBranchId(bean.getUnit());
                            saldoAkhirEntity.setPeriode(bean.getBulan()+"-"+bean.getTahun());
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
//                            SaldoAkhir saldoAkhir = new SaldoAkhir();
//                            saldoAkhir.setPeriode(getPeriodeSebelum(jurnalDetail.getBulan(), jurnalDetail.getTahun()));
//                            saldoAkhir.setBranchId(jurnalDetail.getUnit());
//                            saldoAkhir.setRekeningId(jurnalDetail.getRekeningId());

//                            ItAkunSaldoAkhirEntity saldoAkhirLalu = new ItAkunSaldoAkhirEntity();
//                            List<ItAkunSaldoAkhirEntity> saldoAkhirEntities = getListEntitySaldoAkhir(saldoAkhir);
//                            if (saldoAkhirEntities.size() > 0){
//                                saldoAkhirLalu = saldoAkhirEntities.get(0);
//
//                                // jika posisi saldo akhir yang lalu dengan rekening_id yang dicari adalah debit maka akan menambah jumlah debit
//                                if ("D".equalsIgnoreCase(saldoAkhirLalu.getPosisi())){
//                                    saldoAkhirEntity.setJumlahDebit(saldoAkhirEntity.getJumlahDebit().add(saldoAkhirLalu.getSaldo()));
//                                    jurnalDetail.setJumlahDebit(saldoAkhirEntity.getJumlahDebit().add(saldoAkhirLalu.getSaldo()));
//                                } else {
//                                    // jika saldo akhir lalu adalah kredit maka akan menambah jumlah kredit
//                                    saldoAkhirEntity.setJumlahKredit(saldoAkhirEntity.getJumlahKredit().add(saldoAkhirLalu.getSaldo()));
//                                    jurnalDetail.setJumlahKredit(saldoAkhirEntity.getJumlahKredit().add(saldoAkhirLalu.getSaldo()));
//                                }
//                            }
//                            // end

                            if (saldoAkhirEntity.getJumlahDebit().compareTo(saldoAkhirEntity.getJumlahKredit()) == 1){

                                // jika debit lebih besar maka debit - kredit = saldo, posisi = debit
                                saldoAkhirEntity.setPosisi("D");
                                saldoAkhirEntity.setSaldo(saldoAkhirEntity.getJumlahDebit().subtract(saldoAkhirEntity.getJumlahKredit()));
                            } else {

                                // jika kredit lebih besar maka kredit - debit = saldo, posisi = kredit
                                saldoAkhirEntity.setPosisi("K");
                                saldoAkhirEntity.setSaldo(saldoAkhirEntity.getJumlahKredit().subtract(saldoAkhirEntity.getJumlahDebit()));
                            }

                            listOfTutupData.add(jurnalDetail);

                            try {
                                saldoAkhirHistoryDao.addAndSave(saldoAkhirEntity);

                                TutupPeriod saldoDetail = new TutupPeriod();
                                saldoDetail.setUnit(saldoAkhirEntity.getBranchId());
                                saldoDetail.setSaldoAkhirId(saldoAkhirEntity.getSaldoAkhirId());
                                saldoDetail.setPeriode(saldoAkhirEntity.getPeriode());
                                saldoDetail.setRekeningId(saldoAkhirEntity.getRekeningId());
                                saldoDetail.setBulan(bean.getBulan());
                                saldoDetail.setTahun(bean.getTahun());
                                saldoDetail.setCreatedDate(bean.getCreatedDate());
                                saldoDetail.setCreatedWho(bean.getCreatedWho());
                                saldoDetail.setLastUpdate(bean.getLastUpdate());
                                saldoDetail.setLastUpdateWho(bean.getLastUpdateWho());

                                // SAVE TO DETAIL
                                saveSaldoAkhirDetailTutupTahun(saldoDetail, saldoAkhirEntity);

                            } catch (HibernateException e){
                                logger.error("[TutupPeriodBoImpl.saveUpdateSaldoAkhirTahun] ERROR. ",e);
                                throw new GeneralBOException("[TutupPeriodBoImpl.saveUpdateSaldoAkhirTahun] ERROR. ",e);
                            }
                        }

                        // mengambil parent diatasnya sekaligus insert;
                        List<TutupPeriod> tutupPeriods = hitungAndUpdateParentAkhirTahun(listOfTutupData, bean, level-1);
                    }
                }
            }
        }
    }

    private BigDecimal nullEscape(BigDecimal nilai){
        if (nilai == null){
            return new BigDecimal(0);
        }
        return nilai;
    }


    private List<String> getListNoJurnalPending(String tahun, String bulan, String unit){
        return tutupPeriodDao.getListNoJurnalPending(bulan, tahun, unit);
    }

    private String flagPendingTransitoris(String noJurnal){
        return tutupPeriodDao.checkIsPendingTransitorisByNoJurnal(noJurnal);
    }

    private void movePendingToJurnalDetail(List<String> noJurnals, TutupPeriod bean){

        for (String noJurnal : noJurnals){

            ItJurnalPendingEntity jurnalPendingEntity = jurnalPendingDao.getById("noJurnal", noJurnal);

            if (jurnalPendingEntity != null) {

                DateTime tglSekarang = new DateTime(System.currentTimeMillis());
                DateTime bulanBerikutnya = tglSekarang.plusMonths(1).withDayOfMonth(1);

                ItJurnalEntity jurnalEntity = new ItJurnalEntity();
                jurnalEntity.setNoJurnal(jurnalPendingEntity.getNoJurnal());
                jurnalEntity.setTipeJurnalId(jurnalPendingEntity.getTipeJurnalId());

                // jika transitoris masuk pending
                if ("Y".equalsIgnoreCase(flagPendingTransitoris(jurnalPendingEntity.getNoJurnal()))){
                    jurnalEntity.setTanggalJurnal(jurnalPendingEntity.getTanggalJurnal());
                } else {
                    jurnalEntity.setTanggalJurnal(new java.sql.Date(bulanBerikutnya.getMillis()));
                }

                jurnalEntity.setMataUangId(jurnalPendingEntity.getMataUangId());
                jurnalEntity.setKurs(jurnalPendingEntity.getKurs());
                jurnalEntity.setKeterangan(jurnalPendingEntity.getKeterangan());
                jurnalEntity.setSumber(jurnalPendingEntity.getSumber());
                jurnalEntity.setPrintRegisterCount(jurnalPendingEntity.getPrintRegisterCount());
                jurnalEntity.setPrintCount(jurnalPendingEntity.getPrintCount());
                jurnalEntity.setRegisteredFlag(jurnalPendingEntity.getRegisteredFlag());
                jurnalEntity.setRegisteredUser(jurnalPendingEntity.getRegisteredUser());
                jurnalEntity.setRegisteredDate(jurnalPendingEntity.getRegisteredDate());
                jurnalEntity.setBranchId(jurnalPendingEntity.getBranchId());
                jurnalEntity.setFlag(jurnalPendingEntity.getFlag());
                jurnalEntity.setCreatedWho(bean.getCreatedWho());
                jurnalEntity.setLastUpdateWho(bean.getLastUpdateWho());
                jurnalEntity.setCreatedDate(bean.getCreatedDate());
                jurnalEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    jurnalDao.addAndSave(jurnalEntity);
                } catch (HibernateException e){
                    logger.error("[TutupPeriodBoImpl.movePendingToJurnalDetail] ERROR. ",e);
                    throw new GeneralBOException("[TutupPeriodBoImpl.movePendingToJurnalDetail] ERROR. ",e);
                }

                Map hsCriteria = new HashMap();
                hsCriteria.put("no_jurnal", jurnalPendingEntity.getNoJurnal());
                hsCriteria.put("flag", jurnalPendingEntity.getFlag());

                List<ItJurnalDetailPendingEntity> detailPendingEntities = jurnalDetailPendingDao.getByCriteria(hsCriteria);
                if (detailPendingEntities.size() > 0){
                    for (ItJurnalDetailPendingEntity detailPending : detailPendingEntities){

                        ItJurnalDetailEntity jurnalDetailEntity = new ItJurnalDetailEntity();
                        jurnalDetailEntity.setJurnalDetailId(detailPending.getJurnalDetailId());
                        jurnalDetailEntity.setNoJurnal(detailPending.getNoJurnal());
                        jurnalDetailEntity.setRekeningId(detailPending.getRekeningId());
                        jurnalDetailEntity.setMasterId(detailPending.getMasterId());
                        jurnalDetailEntity.setNoNota(detailPending.getNoNota());
                        jurnalDetailEntity.setJumlahDebit(detailPending.getJumlahDebit());
                        jurnalDetailEntity.setJumlahKredit(detailPending.getJumlahKredit());
                        jurnalDetailEntity.setBiaya(detailPending.getBiaya());
                        jurnalDetailEntity.setFlag(detailPending.getFlag());
                        jurnalDetailEntity.setAction(detailPending.getAction());
                        jurnalDetailEntity.setCreatedDate(detailPending.getCreatedDate());
                        jurnalDetailEntity.setLastUpdate(detailPending.getLastUpdate());
                        jurnalDetailEntity.setCreatedWho(detailPending.getCreatedWho());
                        jurnalDetailEntity.setLastUpdateWho(detailPending.getLastUpdateWho());
                        jurnalDetailEntity.setKdBarang(detailPending.getKdBarang());
                        jurnalDetailEntity.setPasienId(detailPending.getPasienId());
                        jurnalDetailEntity.setNomorRekening(detailPending.getNomorRekening());
                        jurnalDetailEntity.setDivisiId(detailPending.getDivisiId());

                        try {
                            jurnalDetailDao.addAndSave(jurnalDetailEntity);
                        } catch (HibernateException e){
                            logger.error("[TutupPeriodBoImpl.movePendingToJurnalDetail] ERROR. ",e);
                            throw new GeneralBOException("[TutupPeriodBoImpl.movePendingToJurnalDetail] ERROR. ",e);
                        }

                        hsCriteria = new HashMap();
                        hsCriteria.put("jurnal_detail_id", detailPending.getJurnalDetailId());
                        hsCriteria.put("flag", detailPending.getFlag());

                        List<ItJurnalDetailActivityPendingEntity> activityPendingEntities = jurnalDetailActivityPendingDao.getByCriteria(hsCriteria);
                        if (activityPendingEntities.size() > 0){

                            for (ItJurnalDetailActivityPendingEntity pendingEntity : activityPendingEntities){

                                ItJurnalDetailActivityEntity activityEntity = new ItJurnalDetailActivityEntity();
                                activityEntity.setJurnalDetailActivityId(pendingEntity.getJurnalDetailActivityId());
                                activityEntity.setJurnalDetailId(pendingEntity.getJurnalDetailId());
                                activityEntity.setActivityId(pendingEntity.getActivityId());
                                activityEntity.setJumlah(pendingEntity.getJumlah());
                                activityEntity.setPersonId(pendingEntity.getPersonId());
                                activityEntity.setFlag(pendingEntity.getFlag());
                                activityEntity.setTipe(pendingEntity.getTipe());
                                activityEntity.setNoTrans(pendingEntity.getNoTrans());
                                activityEntity.setAction(pendingEntity.getAction());
                                activityEntity.setCreatedDate(pendingEntity.getCreatedDate());
                                activityEntity.setLastUpdate(pendingEntity.getLastUpdate());
                                activityEntity.setCreatedWho(pendingEntity.getCreatedWho());
                                activityEntity.setLastUpdateWho(pendingEntity.getLastUpdateWho());

                                try {
                                    jurnalDetailActivityDao.addAndSave(activityEntity);
                                } catch (HibernateException e){
                                    logger.error("[TutupPeriodBoImpl.movePendingToJurnalDetail] ERROR. ",e);
                                    throw new GeneralBOException("[TutupPeriodBoImpl.movePendingToJurnalDetail] ERROR. ",e);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void saveSaldoAkhirDetail(TutupPeriod bean, ItAkunSaldoAkhirEntity saldoAkhirEntity){

        List<TutupPeriod> nilaiJurnalPerDivisi = tutupPeriodDao.getListDetailJurnalByCriteriaPerDivisi(bean);
        if (nilaiJurnalPerDivisi.size() > 0){
            for (TutupPeriod jurnal : nilaiJurnalPerDivisi){

                SaldoAkhir saldoAkhir = new SaldoAkhir();
                saldoAkhir.setPeriode(getPeriodeSebelum(bean.getBulan(), bean.getTahun()));
                saldoAkhir.setBranchId(bean.getUnit());
                saldoAkhir.setRekeningId(bean.getRekeningId());

                if (jurnal.getMasterId() != null && !"".equalsIgnoreCase(jurnal.getMasterId())){
                    saldoAkhir.setMasterId(jurnal.getMasterId());
                }

                if (jurnal.getDivisiId() != null && !"".equalsIgnoreCase(jurnal.getDivisiId())){
                    saldoAkhir.setDivisiId(jurnal.getDivisiId());
                }

                if (jurnal.getPasienId() != null && !"".equalsIgnoreCase(jurnal.getPasienId())){
                    saldoAkhir.setPasienId(jurnal.getPasienId());
                }

                if (jurnal.getKdBarang() != null && !"".equalsIgnoreCase(jurnal.getKdBarang())){
                    saldoAkhir.setKdBarang(jurnal.getKdBarang());
                }

                // MENDAPATKAN SALDO AKHIR DETAIL PERIODE LALU
                ItAkunSaldoAkhirDetailEntity saldoAkhirDetaillalu = new ItAkunSaldoAkhirDetailEntity();
                List<ItAkunSaldoAkhirDetailEntity> saldoAkhirDetailEntities = getListEntitySaldoAkhirDetail(saldoAkhir);
                if (saldoAkhirDetailEntities.size() > 0){
                    for (ItAkunSaldoAkhirDetailEntity saldoAkhirDetailEntity : saldoAkhirDetailEntities){
                        saldoAkhirDetaillalu = saldoAkhirDetailEntity;
                    }
                }


                ItAkunSaldoAkhirDetailEntity detailEntity = new ItAkunSaldoAkhirDetailEntity();
                detailEntity.setId(saldoAkhirEntity.getPeriode()+"-"+getNextSaldoAkhirDetailId());
                detailEntity.setSaldoAkhirId(saldoAkhirEntity.getSaldoAkhirId());
                detailEntity.setBranchId(saldoAkhirEntity.getBranchId());
                detailEntity.setPeriode(saldoAkhirEntity.getPeriode());
                detailEntity.setRekeningId(saldoAkhirEntity.getRekeningId());
                detailEntity.setJumlahDebit(jurnal.getJumlahDebit());
                detailEntity.setJumlahKredit(jurnal.getJumlahKredit());
                detailEntity.setFlag("Y");
                detailEntity.setAction("C");
                detailEntity.setCreatedDate(bean.getCreatedDate());
                detailEntity.setCreatedWho(bean.getCreatedWho());
                detailEntity.setLastUpdate(bean.getLastUpdate());
                detailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                detailEntity.setMasterId(jurnal.getMasterId());
                detailEntity.setDivisiId(jurnal.getDivisiId());
                detailEntity.setPasienId(jurnal.getPasienId());


                // jika bulan 1, saldo akhir dikurangi dengan jurnal koreksi tutup tahun
                // saldo bulan lalu dikurangi saldo tutup tahun
                if ("1".equalsIgnoreCase(bean.getBulan())){
                    String[] tipePeriode = {"12b", "12a", "12"};
                    for (int i = 0 ; i < 3 ; i++){
                        Integer intTahunLalu = Integer.valueOf(bean.getTahun()) - 1;
                        TutupPeriod jurnalAkhirTahun = new TutupPeriod();
                        jurnalAkhirTahun.setBulan("12");
                        jurnalAkhirTahun.setTahun(intTahunLalu.toString());
                        jurnalAkhirTahun.setUnit(bean.getUnit());
                        jurnalAkhirTahun.setRekeningId(bean.getRekeningId());
                        jurnalAkhirTahun.setTipePeriode(tipePeriode[i]);

                        List<TutupPeriod> listJurnalAkhir = tutupPeriodDao.getListDetailJurnalByCriteriaPerDivisiAkhirTahun(jurnalAkhirTahun);
                        if (listJurnalAkhir.size() > 0){

                            List<TutupPeriod> filterListJurnalAkhir = listJurnalAkhir.stream().filter(p->
                                    p.getMasterId().equals(saldoAkhir.getMasterId()) &&
                                    p.getDivisiId().equals(saldoAkhir.getDivisiId()) &&
                                    p.getPasienId().equals(saldoAkhir.getPasienId()) &&
                                    p.getKdBarang().equals(saldoAkhir.getKdBarang())

                            ).collect(Collectors.toList());

                            if (filterListJurnalAkhir != null && filterListJurnalAkhir.size() > 0){
                                TutupPeriod jurnalAkhir = listJurnalAkhir.get(0);
                                saldoAkhirDetaillalu.setSaldo(nullEscape(saldoAkhirDetaillalu.getSaldo()).subtract(nullEscape(jurnalAkhir.getSaldo())));
                                break;
                            }
                        }
                    }
                }

                // DITAMBAH DENGAN SALDO BULAN LALU
                if (saldoAkhirDetaillalu != null && saldoAkhirDetaillalu.getSaldo() != null){
                    if ("D".equalsIgnoreCase(saldoAkhirDetaillalu.getPosisi())){
                        detailEntity.setJumlahDebit(detailEntity.getJumlahDebit().add(saldoAkhirDetaillalu.getSaldo()));
                    } else {
                        detailEntity.setJumlahKredit(detailEntity.getJumlahKredit().add(saldoAkhirDetaillalu.getSaldo()));
                    }
                }

                // JIKA DEBIT LEBIH BESAR DARI KREDIT
                BigDecimal saldo = new BigDecimal(0);
                String posisi = "";
                if (detailEntity.getJumlahDebit().compareTo(detailEntity.getJumlahKredit()) == 1){
                    saldo = detailEntity.getJumlahDebit().subtract(detailEntity.getJumlahKredit());
                    posisi = "D";
                }
                if (detailEntity.getJumlahKredit().compareTo(detailEntity.getJumlahDebit()) == 1){
                    saldo = detailEntity.getJumlahKredit().subtract(detailEntity.getJumlahDebit());
                    posisi = "K";
                }

                detailEntity.setSaldo(saldo);
                detailEntity.setPosisi(posisi);

                try {
                    saldoAkhirDetailDao.addAndSave(detailEntity);
                } catch (HibernateException e){
                    logger.error("[TutupPeriodBoImpl.saveSaldoAkhirDetail] ERROR. ",e);
                    throw new GeneralBOException("[TutupPeriodBoImpl.saveSaldoAkhirDetail] ERROR. ",e);
                }
            }
        }
    }

    public void updateSaldoAkhirDetail(TutupPeriod bean, ItAkunSaldoAkhirEntity saldoAkhirEntity){

        SaldoAkhir saldoAkhirNo = new SaldoAkhir();
        saldoAkhirNo.setPeriode(bean.getPeriode());
        saldoAkhirNo.setBranchId(bean.getUnit());
        saldoAkhirNo.setRekeningId(bean.getRekeningId());
        saldoAkhirNo.setSaldoAkhirId(saldoAkhirEntity.getSaldoAkhirId());

        // MENDAPATKAN SALDO AKHIR DETAIL PERIODE LALU
        List<ItAkunSaldoAkhirDetailEntity> saldoAkhirDetailEntities = getListEntitySaldoAkhirDetail(saldoAkhirNo);
        if (saldoAkhirDetailEntities.size() > 0){
            for (ItAkunSaldoAkhirDetailEntity detailEntity : saldoAkhirDetailEntities){

                // jika bulan 1, saldo akhir dikurangi dengan jurnal koreksi tutup tahun
                // saldo bulan lalu dikurangi saldo tutup tahun
                if ("1".equalsIgnoreCase(bean.getBulan())){
                    String[] tipePeriode = {"12b", "12a", "12"};
                    for (int i = 0 ; i < 3 ; i++){
                        Integer intTahunLalu = Integer.valueOf(bean.getTahun()) - 1;
                        TutupPeriod jurnalAkhirTahun = new TutupPeriod();
                        jurnalAkhirTahun.setBulan("12");
                        jurnalAkhirTahun.setTahun(intTahunLalu.toString());
                        jurnalAkhirTahun.setUnit(bean.getUnit());
                        jurnalAkhirTahun.setRekeningId(bean.getRekeningId());
                        jurnalAkhirTahun.setTipePeriode(tipePeriode[i]);

                        TutupPeriod noJurnalAkhir = tutupPeriodDao.getNoJurnalJurnalAkhirTahun(jurnalAkhirTahun);
                        if (noJurnalAkhir != null){

                            jurnalAkhirTahun.setNoJurnal(noJurnalAkhir.getNoJurnal());
                            List<TutupPeriod> listJurnalAkhir = tutupPeriodDao.getListDetailJurnalByCriteriaPerDivisiAkhirTahun(jurnalAkhirTahun);
                            if (listJurnalAkhir.size() > 0){

                                List<TutupPeriod> filterListJurnalAkhir = listJurnalAkhir.stream().filter(p->
                                        p.getMasterId().equals(detailEntity.getMasterId()) &&
                                                p.getDivisiId().equals(detailEntity.getDivisiId()) &&
                                                p.getPasienId().equals(detailEntity.getPasienId()) &&
                                                p.getKdBarang().equals(detailEntity.getKdBarang())

                                ).collect(Collectors.toList());

                                if (filterListJurnalAkhir != null && filterListJurnalAkhir.size() > 0){
                                    TutupPeriod jurnalAkhir = listJurnalAkhir.get(0);
                                    // DITAMBAH DENGAN SALDO BULAN LALU
                                    if (jurnalAkhir != null && jurnalAkhir.getSaldo() != null){
                                        if ("D".equalsIgnoreCase(jurnalAkhir.getPosisi())){
                                            detailEntity.setJumlahDebit(detailEntity.getJumlahDebit().add(jurnalAkhir.getSaldo()));
                                        } else {
                                            detailEntity.setJumlahKredit(detailEntity.getJumlahKredit().add(jurnalAkhir.getSaldo()));
                                        }
                                    }
                                }
                            }

                            break;
                        }
                    }
                } else {

                    SaldoAkhir saldoAkhir = new SaldoAkhir();
                    saldoAkhir.setPeriode(getPeriodeSebelum(bean.getBulan(), bean.getTahun()));
                    saldoAkhir.setBranchId(bean.getUnit());
                    saldoAkhir.setRekeningId(bean.getRekeningId());

                    if (detailEntity.getMasterId() != null && !"".equalsIgnoreCase(detailEntity.getMasterId())){
                        saldoAkhir.setMasterId(detailEntity.getMasterId());
                    }

                    if (detailEntity.getDivisiId() != null && !"".equalsIgnoreCase(detailEntity.getDivisiId())){
                        saldoAkhir.setDivisiId(detailEntity.getDivisiId());
                    }

                    if (detailEntity.getPasienId() != null && !"".equalsIgnoreCase(detailEntity.getPasienId())){
                        saldoAkhir.setPasienId(detailEntity.getPasienId());
                    }

                    if (detailEntity.getKdBarang() != null && !"".equalsIgnoreCase(detailEntity.getKdBarang())){
                        saldoAkhir.setKdBarang(detailEntity.getKdBarang());
                    }

                    // MENDAPATKAN SALDO AKHIR DETAIL PERIODE LALU
                    ItAkunSaldoAkhirDetailEntity saldoAkhirDetaillalu = new ItAkunSaldoAkhirDetailEntity();
                    List<ItAkunSaldoAkhirDetailEntity> saldoAkhirLaluDetailEntities = getListEntitySaldoAkhirDetail(saldoAkhir);
                    if (saldoAkhirLaluDetailEntities.size() > 0){
                        for (ItAkunSaldoAkhirDetailEntity saldoAkhirDetailEntity : saldoAkhirDetailEntities){
                            saldoAkhirDetaillalu = saldoAkhirDetailEntity;
                        }
                    }


                    // DITAMBAH DENGAN SALDO BULAN LALU
                    if (saldoAkhirDetaillalu != null && saldoAkhirDetaillalu.getSaldo() != null){
                        if ("D".equalsIgnoreCase(saldoAkhirDetaillalu.getPosisi())){
                            detailEntity.setJumlahDebit(detailEntity.getJumlahDebit().add(saldoAkhirDetaillalu.getSaldo()));
                        } else {
                            detailEntity.setJumlahKredit(detailEntity.getJumlahKredit().add(saldoAkhirDetaillalu.getSaldo()));
                        }
                    }
                }


                // JIKA DEBIT LEBIH BESAR DARI KREDIT
                BigDecimal saldo = new BigDecimal(0);
                String posisi = "";
                if (detailEntity.getJumlahDebit().compareTo(detailEntity.getJumlahKredit()) == 1){
                    saldo = detailEntity.getJumlahDebit().subtract(detailEntity.getJumlahKredit());
                    posisi = "D";
                }
                if (detailEntity.getJumlahKredit().compareTo(detailEntity.getJumlahDebit()) == 1){
                    saldo = detailEntity.getJumlahKredit().subtract(detailEntity.getJumlahDebit());
                    posisi = "K";
                }

                detailEntity.setSaldo(saldo);
                detailEntity.setPosisi(posisi);
                detailEntity.setAction("U");
                detailEntity.setLastUpdate(bean.getLastUpdate());
                detailEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    saldoAkhirDetailDao.updateAndSave(detailEntity);
                } catch (HibernateException e){
                    logger.error("[TutupPeriodBoImpl.updateSaldoAkhirDetail] ERROR. ",e);
                    throw new GeneralBOException("[TutupPeriodBoImpl.updateSaldoAkhirDetail] ERROR. ",e);
                }
            }
        }
    }

    public void saveSaldoAkhirDetailTutupTahun(TutupPeriod bean, ItAkunSaldoAkhirHistoryEntity saldoAkhirEntity){

        List<TutupPeriod> nilaiJurnalPerDivisi = tutupPeriodDao.getListDetailJurnalByCriteriaPerDivisi(bean);
        if (nilaiJurnalPerDivisi.size() > 0){
            for (TutupPeriod jurnal : nilaiJurnalPerDivisi){

//                SaldoAkhir saldoAkhir = new SaldoAkhir();
//                saldoAkhir.setPeriode(getPeriodeSebelum(bean.getBulan(), bean.getTahun()));
//                saldoAkhir.setBranchId(bean.getUnit());
//                saldoAkhir.setRekeningId(bean.getRekeningId());
//
//                if (jurnal.getMasterId() != null && !"".equalsIgnoreCase(jurnal.getMasterId())){
//                    saldoAkhir.setMasterId(jurnal.getMasterId());
//                }
//
//                if (jurnal.getDivisiId() != null && !"".equalsIgnoreCase(jurnal.getDivisiId())){
//                    saldoAkhir.setDivisiId(jurnal.getDivisiId());
//                }
//
//                if (jurnal.getPasienId() != null && !"".equalsIgnoreCase(jurnal.getPasienId())){
//                    saldoAkhir.setPasienId(jurnal.getPasienId());
//                }
//
//                if (jurnal.getKdBarang() != null && !"".equalsIgnoreCase(jurnal.getKdBarang())){
//                    saldoAkhir.setKdBarang(jurnal.getKdBarang());
//                }
//
//                // MENDAPATKAN SALDO AKHIR DETAIL PERIODE LALU
//                ItAkunSaldoAkhirDetailEntity saldoAkhirDetaillalu = new ItAkunSaldoAkhirDetailEntity();
//                List<ItAkunSaldoAkhirDetailEntity> saldoAkhirDetailEntities = getListEntitySaldoAkhirDetail(saldoAkhir);
//                if (saldoAkhirDetailEntities.size() > 0){
//                    for (ItAkunSaldoAkhirDetailEntity saldoAkhirDetailEntity : saldoAkhirDetailEntities){
//                        saldoAkhirDetaillalu = saldoAkhirDetailEntity;
//                    }
//                }


                ItAkunSaldoAkhirDetailHistoryEntity detailEntity = new ItAkunSaldoAkhirDetailHistoryEntity();
                detailEntity.setId(saldoAkhirEntity.getPeriode()+"-"+getNextSaldoAkhirDetailTahunId());
                detailEntity.setSaldoAkhirId(saldoAkhirEntity.getSaldoAkhirId());
                detailEntity.setBranchId(saldoAkhirEntity.getBranchId());
                detailEntity.setPeriode(saldoAkhirEntity.getPeriode());
                detailEntity.setRekeningId(saldoAkhirEntity.getRekeningId());
                detailEntity.setJumlahDebit(jurnal.getJumlahDebit());
                detailEntity.setJumlahKredit(jurnal.getJumlahKredit());
                detailEntity.setFlag("Y");
                detailEntity.setAction("C");
                detailEntity.setCreatedDate(bean.getCreatedDate());
                detailEntity.setCreatedWho(bean.getCreatedWho());
                detailEntity.setLastUpdate(bean.getLastUpdate());
                detailEntity.setLastUpdateWho(bean.getLastUpdateWho());
                detailEntity.setMasterId(jurnal.getMasterId());
                detailEntity.setDivisiId(jurnal.getDivisiId());
                detailEntity.setPasienId(jurnal.getPasienId());

                // DITAMBAH DENGAN SALDO BULAN LALU
//                if (saldoAkhirDetaillalu != null && saldoAkhirDetaillalu.getSaldo() != null){
//                    if ("D".equalsIgnoreCase(saldoAkhirDetaillalu.getPosisi())){
//                        detailEntity.setJumlahDebit(detailEntity.getJumlahDebit().add(saldoAkhirDetaillalu.getSaldo()));
//                    } else {
//                        detailEntity.setJumlahKredit(detailEntity.getJumlahKredit().add(saldoAkhirDetaillalu.getSaldo()));
//                    }
//                }

                // JIKA DEBIT LEBIH BESAR DARI KREDIT
                BigDecimal saldo = new BigDecimal(0);
                String posisi = "";
                if (detailEntity.getJumlahDebit().compareTo(detailEntity.getJumlahKredit()) == 1){
                    saldo = detailEntity.getJumlahDebit().subtract(detailEntity.getJumlahKredit());
                    posisi = "D";
                }
                if (detailEntity.getJumlahKredit().compareTo(detailEntity.getJumlahDebit()) == 1){
                    saldo = detailEntity.getJumlahKredit().subtract(detailEntity.getJumlahDebit());
                    posisi = "K";
                }

                detailEntity.setSaldo(saldo);
                detailEntity.setPosisi(posisi);

                try {
                    saldoAkhirDetailHistoryDao.addAndSave(detailEntity);
                } catch (HibernateException e){
                    logger.error("[TutupPeriodBoImpl.saveSaldoAkhirDetailTutupTahun] ERROR. ",e);
                    throw new GeneralBOException("[TutupPeriodBoImpl.saveSaldoAkhirDetailTutupTahun] ERROR. ",e);
                }
            }
        }
    }


    public List<TutupPeriod> prosesTutupPeriod(List<TutupPeriod> periods, TutupPeriod bean, Integer level) throws GeneralBOException{

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        List<TutupPeriod> parentPeriods = new ArrayList<>();

        // jika parameter periods sebagai child tidak kosong
        // maka prosess untuk insert, sum, collecting data parrentPeriods;
        if (level.compareTo(0) == 1){

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

            List<TutupPeriod> listOfMapingParents = new ArrayList<>();

            KodeRekening kodeRekening = new KodeRekening();
            kodeRekening.setLevel(level.longValue());
            List<ImKodeRekeningEntity> kodeRekeningEntities = getListEntityKodeRekening(kodeRekening);
            if (kodeRekeningEntities.size() > 0){
                for (ImKodeRekeningEntity kodeRekeningEntity : kodeRekeningEntities){

                    TutupPeriod parentPeriod = new TutupPeriod();
                    parentPeriod.setRekeningId(kodeRekeningEntity.getRekeningId());
                    parentPeriod.setParentId(kodeRekeningEntity.getParentId());
                    parentPeriod.setJumlahDebit(new BigDecimal(0));
                    parentPeriod.setJumlahKredit(new BigDecimal(0));
                    parentPeriod.setUnit(bean.getUnit());
                    parentPeriod.setBulan(bean.getBulan());
                    parentPeriod.setTahun(bean.getTahun());

                    ItAkunSaldoAkhirEntity saldoAkhirEntity = new ItAkunSaldoAkhirEntity();
                    saldoAkhirEntity.setSaldoAkhirId(getNextSaldoAkhirId());
                    saldoAkhirEntity.setBranchId(bean.getUnit());
                    saldoAkhirEntity.setPeriode(bean.getBulan()+"-"+bean.getTahun());
                    saldoAkhirEntity.setRekeningId(kodeRekeningEntity.getRekeningId());
                    saldoAkhirEntity.setJumlahDebit(new BigDecimal(0));
                    saldoAkhirEntity.setJumlahKredit(new BigDecimal(0));
                    saldoAkhirEntity.setFlag("Y");
                    saldoAkhirEntity.setAction("C");
                    saldoAkhirEntity.setCreatedDate(time);
                    saldoAkhirEntity.setCreatedWho(userLogin);
                    saldoAkhirEntity.setLastUpdate(time);
                    saldoAkhirEntity.setLastUpdateWho(userLogin);

                    if (parentPeriods.size() > 0){
                        List<TutupPeriod> parents = parentPeriods.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(kodeRekeningEntity.getRekeningId())).collect(Collectors.toList());
                        if (parents.size() > 0){
                            TutupPeriod parent = parents.get(0);
                            saldoAkhirEntity.setJumlahDebit(parent.getJumlahDebit());
                            saldoAkhirEntity.setJumlahKredit(parent.getJumlahKredit());
                            parentPeriod.setJumlahDebit(parent.getJumlahDebit());
                            parentPeriod.setJumlahKredit(parent.getJumlahKredit());
                        }
                    }

                    // SET SALDO
                    if (saldoAkhirEntity.getJumlahDebit().compareTo(saldoAkhirEntity.getJumlahKredit()) == 1){

                        // jika debit lebih besar maka debit - kredit = saldo, posisi = debit
                        saldoAkhirEntity.setPosisi("D");
                        saldoAkhirEntity.setSaldo(saldoAkhirEntity.getJumlahDebit().subtract(saldoAkhirEntity.getJumlahKredit()));
                    } else {

                        // jika kredit lebih besar maka kredit - debit = saldo, posisi = kredit
                        saldoAkhirEntity.setPosisi("K");
                        saldoAkhirEntity.setSaldo(saldoAkhirEntity.getJumlahKredit().subtract(saldoAkhirEntity.getJumlahDebit()));
                    }

                    listOfMapingParents.add(parentPeriod);

                    try {
                        saldoAkhirDao.addAndSave(saldoAkhirEntity);
                    } catch (HibernateException e){
                        logger.error("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. ",e);
                        throw new GeneralBOException("[TutupPeriodBoImpl.saveUpdateTutupPeriod] ERROR. ",e);
                    }
                }

                // prosess kembali dengan parameter parrent yang sudah di collect dan di insert;
                // parameter parent diprosess kembali sebagai child;
                level--;
                prosesTutupPeriod(listOfMapingParents, bean, level);
            }
        }
        return new ArrayList<>();
    }

    public List<TutupPeriod> hitungAndUpdateParentAkhirTahun(List<TutupPeriod> periods, TutupPeriod bean, Integer level) throws GeneralBOException{

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        List<TutupPeriod> parentPeriods = new ArrayList<>();

        // jika parameter periods sebagai child tidak kosong
        // maka prosess untuk insert, sum, collecting data parrentPeriods;
        if (level.compareTo(0) == 1){

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

            List<TutupPeriod> listOfMapingParents = new ArrayList<>();

            KodeRekening kodeRekening = new KodeRekening();
            kodeRekening.setLevel(level.longValue());
            List<ImKodeRekeningEntity> kodeRekeningEntities = getListEntityKodeRekening(kodeRekening);
            if (kodeRekeningEntities.size() > 0){
                for (ImKodeRekeningEntity kodeRekeningEntity : kodeRekeningEntities){

                    TutupPeriod parentPeriod = new TutupPeriod();
                    parentPeriod.setRekeningId(kodeRekeningEntity.getRekeningId());
                    parentPeriod.setParentId(kodeRekeningEntity.getParentId());
                    parentPeriod.setJumlahDebit(new BigDecimal(0));
                    parentPeriod.setJumlahKredit(new BigDecimal(0));
                    parentPeriod.setUnit(bean.getUnit());
                    parentPeriod.setBulan(bean.getBulan());
                    parentPeriod.setTahun(bean.getTahun());

                    ItAkunSaldoAkhirHistoryEntity saldoAkhirEntity = new ItAkunSaldoAkhirHistoryEntity();
                    saldoAkhirEntity.setSaldoAkhirId(getNextSaldoAkhirTahunId());
                    saldoAkhirEntity.setBranchId(bean.getUnit());
                    saldoAkhirEntity.setPeriode(bean.getBulan()+"-"+bean.getTahun());
                    saldoAkhirEntity.setRekeningId(kodeRekeningEntity.getRekeningId());
                    saldoAkhirEntity.setJumlahDebit(new BigDecimal(0));
                    saldoAkhirEntity.setJumlahKredit(new BigDecimal(0));
                    saldoAkhirEntity.setFlag("Y");
                    saldoAkhirEntity.setAction("C");
                    saldoAkhirEntity.setCreatedDate(time);
                    saldoAkhirEntity.setCreatedWho(userLogin);
                    saldoAkhirEntity.setLastUpdate(time);
                    saldoAkhirEntity.setLastUpdateWho(userLogin);

                    if (parentPeriods.size() > 0){
                        List<TutupPeriod> parents = parentPeriods.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(kodeRekeningEntity.getRekeningId())).collect(Collectors.toList());
                        if (parents.size() > 0){
                            TutupPeriod parent = parents.get(0);
                            saldoAkhirEntity.setJumlahDebit(parent.getJumlahDebit());
                            saldoAkhirEntity.setJumlahKredit(parent.getJumlahKredit());
                            parentPeriod.setJumlahDebit(parent.getJumlahDebit());
                            parentPeriod.setJumlahKredit(parent.getJumlahKredit());
                        }
                    }

                    // SET SALDO
                    if (saldoAkhirEntity.getJumlahDebit().compareTo(saldoAkhirEntity.getJumlahKredit()) == 1){

                        // jika debit lebih besar maka debit - kredit = saldo, posisi = debit
                        saldoAkhirEntity.setPosisi("D");
                        saldoAkhirEntity.setSaldo(saldoAkhirEntity.getJumlahDebit().subtract(saldoAkhirEntity.getJumlahKredit()));
                    } else {

                        // jika kredit lebih besar maka kredit - debit = saldo, posisi = kredit
                        saldoAkhirEntity.setPosisi("K");
                        saldoAkhirEntity.setSaldo(saldoAkhirEntity.getJumlahKredit().subtract(saldoAkhirEntity.getJumlahDebit()));
                    }

                    listOfMapingParents.add(parentPeriod);

                    try {
                        saldoAkhirHistoryDao.addAndSave(saldoAkhirEntity);
                    } catch (HibernateException e){
                        logger.error("[TutupPeriodBoImpl.hitungAndUpdateParentAkhirTahun] ERROR. ",e);
                        throw new GeneralBOException("[TutupPeriodBoImpl.hitungAndUpdateParentAkhirTahun] ERROR. ",e);
                    }
                }

                // prosess kembali dengan parameter parrent yang sudah di collect dan di insert;
                // parameter parent diprosess kembali sebagai child;
                level--;
                hitungAndUpdateParentAkhirTahun(listOfMapingParents, bean, level);
            }
        }
        return new ArrayList<>();
    }

    public List<TutupPeriod> hitungAndUpdateParent(List<TutupPeriod> periods, TutupPeriod bean, Integer level) throws GeneralBOException{

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        List<TutupPeriod> parentPeriods = new ArrayList<>();

        // jika parameter periods sebagai child tidak kosong
        // maka prosess untuk insert, sum, collecting data parrentPeriods;
        if (level.compareTo(0) == 1){

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

            List<TutupPeriod> listOfMapingParents = new ArrayList<>();

            KodeRekening kodeRekening = new KodeRekening();
            kodeRekening.setLevel(level.longValue());
            List<ImKodeRekeningEntity> kodeRekeningEntities = getListEntityKodeRekening(kodeRekening);
            if (kodeRekeningEntities.size() > 0){
                for (ImKodeRekeningEntity kodeRekeningEntity : kodeRekeningEntities){

                    TutupPeriod parentPeriod = new TutupPeriod();
                    parentPeriod.setRekeningId(kodeRekeningEntity.getRekeningId());
                    parentPeriod.setParentId(kodeRekeningEntity.getParentId());
                    parentPeriod.setJumlahDebit(new BigDecimal(0));
                    parentPeriod.setJumlahKredit(new BigDecimal(0));
                    parentPeriod.setUnit(bean.getUnit());
                    parentPeriod.setBulan(bean.getBulan());
                    parentPeriod.setTahun(bean.getTahun());

                    SaldoAkhir saldoAkhir = new SaldoAkhir();
                    saldoAkhir.setRekeningId(kodeRekeningEntity.getRekeningId());
                    saldoAkhir.setBranchId(bean.getUnit());
                    saldoAkhir.setPeriode(bean.getBulan()+"-"+bean.getTahun());

                    List<ItAkunSaldoAkhirEntity> saldoAkhirEntities = getListEntitySaldoAkhir(saldoAkhir);
                    if (saldoAkhirEntities.size() > 0){
                        ItAkunSaldoAkhirEntity saldoAkhirEntity = saldoAkhirEntities.get(0);
                        saldoAkhirEntity.setAction("U");
                        saldoAkhirEntity.setLastUpdate(time);
                        saldoAkhirEntity.setLastUpdateWho(userLogin);

                        if (parentPeriods.size() > 0){
                            List<TutupPeriod> parents = parentPeriods.stream().filter(p -> p.getRekeningId().equalsIgnoreCase(kodeRekeningEntity.getRekeningId())).collect(Collectors.toList());
                            if (parents.size() > 0){
                                TutupPeriod parent = parents.get(0);
                                saldoAkhirEntity.setJumlahDebit(parent.getJumlahDebit());
                                saldoAkhirEntity.setJumlahKredit(parent.getJumlahKredit());
                                parentPeriod.setJumlahDebit(parent.getJumlahDebit());
                                parentPeriod.setJumlahKredit(parent.getJumlahKredit());
                            }
                        }

                        // SET SALDO
                        if (saldoAkhirEntity.getJumlahDebit().compareTo(saldoAkhirEntity.getJumlahKredit()) == 1){

                            // jika debit lebih besar maka debit - kredit = saldo, posisi = debit
                            saldoAkhirEntity.setPosisi("D");
                            saldoAkhirEntity.setSaldo(saldoAkhirEntity.getJumlahDebit().subtract(saldoAkhirEntity.getJumlahKredit()));
                        } else {

                            // jika kredit lebih besar maka kredit - debit = saldo, posisi = kredit
                            saldoAkhirEntity.setPosisi("K");
                            saldoAkhirEntity.setSaldo(saldoAkhirEntity.getJumlahKredit().subtract(saldoAkhirEntity.getJumlahDebit()));
                        }

                        try {
                            saldoAkhirDao.updateAndSave(saldoAkhirEntity);
                        } catch (HibernateException e){
                            logger.error("[TutupPeriodBoImpl.hitungAndUpdateParent] ERROR. ",e);
                            throw new GeneralBOException("[TutupPeriodBoImpl.hitungAndUpdateParent] ERROR. ",e);
                        }

                    }

                    listOfMapingParents.add(parentPeriod);
                }

                // prosess kembali dengan parameter parrent yang sudah di collect dan di insert;
                // parameter parent diprosess kembali sebagai child;
                level--;
                hitungAndUpdateParent(listOfMapingParents, bean, level);
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
        return intBulan+"-"+intTahun;
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

    @Override
    public void saveUpdateLockPeriodKoreksi(TutupPeriod bean) throws GeneralBOException {

//        Integer intTahunDepan = Integer.valueOf(bean.getTahun()) + 1;
//        String tahunDepan = intTahunDepan.toString();

//        BatasTutupPeriod periodSaldoTerakhir = getLastBulanBerjalanSaldoAkhir(bean.getTahun(), bean.getUnit());
//        if (periodSaldoTerakhir != null){
//            // lock prosess bulan berjalan
//            lockProsesKoreksi(periodSaldoTerakhir);
//        }

        BatasTutupPeriod batasTutupPeriod = new BatasTutupPeriod();
        batasTutupPeriod.setUnit(bean.getUnit());
        batasTutupPeriod.setTahun(bean.getTahun());
        batasTutupPeriod.setBulan("12");

        // update flag p bulan desember tahun terkoreksi;
        lockProsesKoreksi(batasTutupPeriod);
    }

    private void lockProsesKoreksi(BatasTutupPeriod bean){
        List<ItSimrsBatasTutupPeriodEntity> batasTutupPeriodEntities = getListEntityBatasTutupPeriode(bean);
        if (batasTutupPeriodEntities.size() > 0){

            ItSimrsBatasTutupPeriodEntity batasTutupPeriodEntity = batasTutupPeriodEntities.get(0);

            // jika ditemukan update
            if ("Y".equalsIgnoreCase(batasTutupPeriodEntity.getFlagDesemberA())){
                if (batasTutupPeriodEntity.getFlagDesemberB() == null || "".equalsIgnoreCase(batasTutupPeriodEntity.getFlagDesemberB())){
                    batasTutupPeriodEntity.setFlagDesemberB("P");
                }
            } else if (batasTutupPeriodEntity.getNoJurnalKoreksi() != null && !"".equalsIgnoreCase(batasTutupPeriodEntity.getNoJurnalKoreksi())){
                if (batasTutupPeriodEntity.getFlagDesemberA() == null || "".equalsIgnoreCase(batasTutupPeriodEntity.getFlagDesemberA())){
                    batasTutupPeriodEntity.setFlagDesemberA("P");
                }
            } else {
                batasTutupPeriodEntity.setFlagTutup("P");
            }
            batasTutupPeriodEntity.setAction("U");
            batasTutupPeriodEntity.setLastUpdate(bean.getLastUpdate());
            batasTutupPeriodEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                batasTutupPeriodDao.updateAndSave(batasTutupPeriodEntity);
            } catch (HibernateException e){
                logger.error("[TutupPeriodBoImpl.saveUpdateLockPeriod] ERROR. ",e);
                throw new GeneralBOException("[TutupPeriodBoImpl.saveUpdateLockPeriod] ERROR. ",e);
            }
        }
    }

    protected List<ImKodeRekeningEntity> getListEntityKodeRekening(KodeRekening bean) throws GeneralBOException{

        Map hsCriteria = new HashMap();
        if (bean.getLevel() != null){
            hsCriteria.put("level", bean.getLevel());
        }
        if (bean.getRekeningId() != null){
            hsCriteria.put("rekening_id", bean.getRekeningId());
        }
        if (bean.getParentId() != null){
            hsCriteria.put("parent_id", bean.getParentId());
        }

        hsCriteria.put("parent_order", "Y");
        hsCriteria.put("flag", "Y");

        List<ImKodeRekeningEntity> kodeRekeningEntities = new ArrayList<>();
        try {
            kodeRekeningEntities = kodeRekeningDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[TutupPeriodBoImpl.getListEntityKodeRekening] ERROR. ",e);
            throw new GeneralBOException("[TutupPeriodBoImpl.getListEntityKodeRekening] ERROR. ",e);
        }
        return kodeRekeningEntities;
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

    private List<ItAkunSaldoAkhirDetailEntity> getListEntitySaldoAkhirDetail(SaldoAkhir bean) throws GeneralBOException{

        List<ItAkunSaldoAkhirDetailEntity> saldoAkhirEntities = new ArrayList<>();

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

            if (bean.getMasterId() != null){
                hsCriteria.put("master_id", bean.getMasterId());
            } else {
                hsCriteria.put("master_id", "null");
            }

            if (bean.getDivisiId() != null){
                hsCriteria.put("divisi_id", bean.getMasterId());
            } else {
                hsCriteria.put("divisi_id", "null");
            }

            if (bean.getPasienId() != null){
                hsCriteria.put("pasien_id", bean.getMasterId());
            } else {
                hsCriteria.put("pasien_id", "null");
            }

            if (bean.getKdBarang() != null){
                hsCriteria.put("kd_barang", bean.getKdBarang());
            } else {
                hsCriteria.put("kd_barang", "null");
            }

            if (bean.getSaldoAkhirId() != null){
                hsCriteria.put("saldo_akhir_id", bean.getSaldoAkhirId());
            } else {
                hsCriteria.put("saldo_akhir_id", "null");
            }


            try {
                saldoAkhirEntities = saldoAkhirDetailDao.getByCriteria(hsCriteria);
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

    protected String checkIsJurnalTransitoris(String transId){
        return tutupPeriodDao.checkIfJurnalTransitoris(transId);
    }

    protected List<SaldoAkhir> getListSaldoAkhir(String branchId, String periode, String rekeningId, BigInteger level){
        return tutupPeriodDao.getNilaiSaldoAkhir(periode, branchId, rekeningId, level);
    }

    protected List<SaldoAkhir> getListSAldoAkhirDetailByIdSaldo(String idSaldo){
        return tutupPeriodDao.getListSaldoAkhirDetailById(idSaldo);
    }

    @Override
    public BatasTutupPeriod getLastBulanBerjalanSaldoAkhir(String tahun, String branchId) throws GeneralBOException {

        Integer intTahunDepan = Integer.valueOf(tahun) + 1;
        String tahunDepan = intTahunDepan.toString();

        SaldoAkhir saldoAkhir = saldoAkhirDao.getLastSaldoAkhirByTahun(tahunDepan, branchId);
        if (saldoAkhir != null){

            String[] arrPeriode = saldoAkhir.getPeriode().split("-");
            String bulan = arrPeriode[0].toString();

            BatasTutupPeriod batasTutupPeriod = new BatasTutupPeriod();
            batasTutupPeriod.setBulan(bulan);
            batasTutupPeriod.setTahun(tahunDepan);
            batasTutupPeriod.setUnit(branchId);
            batasTutupPeriod.setBulanName(CommonUtil.convertNumberToStringBulan(bulan));
            return batasTutupPeriod;
        }
        return null;
    }

    @Override
    public void updateSaldoAkhirBulanBerjalan(TutupPeriod bean) throws GeneralBOException {

        Integer inTahunDepan = Integer.valueOf(bean.getTahun()) + 1;
        String tahunDepan = inTahunDepan.toString();

        Integer inBulan = 0;
        BatasTutupPeriod lastBulanBerjalan = getLastBulanBerjalanSaldoAkhir(bean.getTahun(), bean.getUnit());
        if (lastBulanBerjalan != null){
            inBulan = Integer.valueOf(lastBulanBerjalan.getBulan());
        }

        for (int i = 1; i <= inBulan; i++){

            String bulan = String.valueOf(i);
            List<TutupPeriod> listOfTutupData = new ArrayList<>();

            Integer level = getLowestLevelKodeRekening();
            KodeRekening kodeRekening = new KodeRekening();
            kodeRekening.setLevel(level.longValue());

            List<ImKodeRekeningEntity> kodeRekeningEntities = getListEntityKodeRekening(kodeRekening);
            if (kodeRekeningEntities.size() > 0){

                for (ImKodeRekeningEntity kodeRekeningEntity : kodeRekeningEntities){

                    TutupPeriod jurnalDetail = new TutupPeriod();
                    jurnalDetail.setBulan(bulan);
                    jurnalDetail.setTahun(bean.getTahun());
                    jurnalDetail.setUnit(bean.getUnit());
                    jurnalDetail.setRekeningId(kodeRekeningEntity.getRekeningId());
                    jurnalDetail.setParentId(kodeRekeningEntity.getParentId());

                    // mendapatkan data saldo akhir periode sebelumnya dengan dan unit
                    SaldoAkhir saldoAkhir = new SaldoAkhir();
                    saldoAkhir.setPeriode(bulan + "-" + tahunDepan);
                    saldoAkhir.setBranchId(bean.getUnit());
                    saldoAkhir.setRekeningId(kodeRekeningEntity.getRekeningId());

                    List<ItAkunSaldoAkhirEntity> saldoAkhirEntities = getListEntitySaldoAkhir(saldoAkhir);

                    if (saldoAkhirEntities.size() > 0){
                        ItAkunSaldoAkhirEntity saldoAkhirEntity = saldoAkhirEntities.get(0);

                        String posisiJurnalAkhir = "";
                        BigDecimal jurnalAkhirSaldo = new BigDecimal(0);
                        if ("1".equalsIgnoreCase(bulan)){
                            String[] tipePeriode = {"12b", "12a", "12"};
                            for (int n = 0 ; n < 3 ; n++){
                                TutupPeriod jurnalAkhirTahun = new TutupPeriod();
                                jurnalAkhirTahun.setBulan("12");
                                jurnalAkhirTahun.setTahun(bean.getTahun());
                                jurnalAkhirTahun.setUnit(bean.getUnit());
                                jurnalAkhirTahun.setRekeningId(saldoAkhirEntity.getRekeningId());
                                jurnalAkhirTahun.setTipePeriode(tipePeriode[n]);
                                TutupPeriod noJurnalAkhir = tutupPeriodDao.getNoJurnalJurnalAkhirTahun(jurnalAkhirTahun);
                                if (noJurnalAkhir != null){

                                    jurnalAkhirTahun.setNoJurnal(noJurnalAkhir.getNoJurnal());
                                    List<TutupPeriod> listJurnalAkhir = tutupPeriodDao.getListDetailJurnalAkhirTahunByCriteria(jurnalAkhirTahun);
                                    if (listJurnalAkhir.size() > 0){
                                        TutupPeriod jurnalAkhir = listJurnalAkhir.get(0);
                                        posisiJurnalAkhir = jurnalAkhir.getPosisi();
                                        jurnalAkhirSaldo = jurnalAkhir.getSaldo();

//                                            if ("D".equalsIgnoreCase(jurnalAkhir.getPosisi())){
//                                                saldoAkhirLalu.setJumlahDebit(saldoAkhirLalu.getJumlahDebit().add(jurnalAkhir.getSaldo()));
//                                            } else {
//                                                // jika saldo akhir lalu adalah kredit maka akan menambah jumlah kredit
//                                                saldoAkhirLalu.setJumlahKredit(saldoAkhirLalu.getJumlahKredit().add(jurnalAkhir.getSaldo()));
//                                            }
//
//                                            if (saldoAkhirLalu.getJumlahDebit().compareTo(saldoAkhirLalu.getJumlahKredit()) == 1){
//                                                // jika debit lebih besar maka debit - kredit = saldo, posisi = debit
//                                                saldoAkhirLalu.setPosisi("D");
//                                                saldoAkhirLalu.setSaldo(saldoAkhirLalu.getJumlahDebit().subtract(saldoAkhirLalu.getJumlahKredit()));
//                                            } else {
//                                                // jika kredit lebih besar maka kredit - debit = saldo, posisi = kredit
//                                                saldoAkhirLalu.setPosisi("K");
//                                                saldoAkhirLalu.setSaldo(saldoAkhirLalu.getJumlahKredit().subtract(saldoAkhirLalu.getJumlahDebit()));
//                                            }
                                        }
                                    break;
                                }
                            }
                        } else {

                            List<ItAkunSaldoAkhirEntity> saldoAkhirlaluEntities = getListEntitySaldoAkhir(saldoAkhir);
                            if (saldoAkhirlaluEntities.size() > 0){

                                ItAkunSaldoAkhirEntity saldoAkhirLalu = saldoAkhirEntities.get(0);
                                // jika posisi saldo akhir yang lalu dengan rekening_id yang dicari adalah debit maka akan menambah jumlah debit

                                if ("D".equalsIgnoreCase(saldoAkhirLalu.getPosisi())){
                                    saldoAkhirEntity.setJumlahDebit(saldoAkhirEntity.getJumlahDebit().add(saldoAkhirLalu.getSaldo()));
//                                    jurnalDetail.setJumlahDebit(saldoAkhirEntity.getJumlahDebit());
                                } else {
                                    // jika saldo akhir lalu adalah kredit maka akan menambah jumlah kredit
                                    saldoAkhirEntity.setJumlahKredit(saldoAkhirEntity.getJumlahKredit().add(saldoAkhirLalu.getSaldo()));
//                                    jurnalDetail.setJumlahKredit(saldoAkhirEntity.getJumlahKredit());
                                }

                            }
                        }

                        // dicalculate dengan jurnal akhir tahun
                        if (posisiJurnalAkhir != ""){
                            // jika posisi saldo akhir yang lalu dengan rekening_id yang dicari adalah debit maka akan menambah jumlah debit
                            if ("D".equalsIgnoreCase(posisiJurnalAkhir)){
                                saldoAkhirEntity.setJumlahDebit(saldoAkhirEntity.getJumlahDebit().add(jurnalAkhirSaldo));
                                jurnalDetail.setJumlahDebit(saldoAkhirEntity.getJumlahDebit());
                            } else {
                                // jika saldo akhir lalu adalah kredit maka akan menambah jumlah kredit
                                saldoAkhirEntity.setJumlahKredit(saldoAkhirEntity.getJumlahKredit().add(jurnalAkhirSaldo));
                                jurnalDetail.setJumlahKredit(saldoAkhirEntity.getJumlahKredit());
                            }
                            posisiJurnalAkhir = "";
                            jurnalAkhirSaldo = new BigDecimal(0);
                        }

                        if (saldoAkhirEntity.getJumlahDebit().compareTo(saldoAkhirEntity.getJumlahKredit()) == 1){

                            // jika debit lebih besar maka debit - kredit = saldo, posisi = debit
                            saldoAkhirEntity.setPosisi("D");
                            saldoAkhirEntity.setSaldo(saldoAkhirEntity.getJumlahDebit().subtract(saldoAkhirEntity.getJumlahKredit()));
                        } else {

                            // jika kredit lebih besar maka kredit - debit = saldo, posisi = kredit
                            saldoAkhirEntity.setPosisi("K");
                            saldoAkhirEntity.setSaldo(saldoAkhirEntity.getJumlahKredit().subtract(saldoAkhirEntity.getJumlahDebit()));
                        }

                        jurnalDetail.setJumlahDebit(saldoAkhirEntity.getJumlahDebit());
                        jurnalDetail.setJumlahKredit(saldoAkhirEntity.getJumlahKredit());
                        jurnalDetail.setSaldo(saldoAkhirEntity.getSaldo());

                        listOfTutupData.add(jurnalDetail);
                        try {
                            saldoAkhirDao.updateAndSave(saldoAkhirEntity);

                            TutupPeriod saldoDetail = new TutupPeriod();
                            saldoDetail.setUnit(saldoAkhirEntity.getBranchId());
                            saldoDetail.setSaldoAkhirId(saldoAkhirEntity.getSaldoAkhirId());
                            saldoDetail.setPeriode(saldoAkhirEntity.getPeriode());
                            saldoDetail.setRekeningId(saldoAkhirEntity.getRekeningId());
                            saldoDetail.setBulan(bulan);
                            saldoDetail.setTahun(bean.getTahun());
                            saldoDetail.setLastUpdate(bean.getLastUpdate());
                            saldoDetail.setLastUpdateWho(bean.getLastUpdateWho());

                            updateSaldoAkhirDetail(saldoDetail, saldoAkhirEntity);

                        } catch (HibernateException e){
                            logger.error("[TutupPeriodBoImpl.updateSaldoAkhirBulanBerjalan] ERROR. ",e);
                            throw new GeneralBOException("[TutupPeriodBoImpl.updateSaldoAkhirBulanBerjalan] ERROR. ",e);
                        }
                    }
                }

                // mengambil parent diatasnya sekaligus insert;
                List<TutupPeriod> tutupPeriods = hitungAndUpdateParent(listOfTutupData, bean, level-1);
                boolean isClear = tutupPeriods.size() == 0;
                if (isClear){

                    BatasTutupPeriod batasTutupPeriod = new BatasTutupPeriod();
                    batasTutupPeriod.setBulan(bulan);
                    batasTutupPeriod.setTahun(bean.getTahun());

                    List<ItSimrsBatasTutupPeriodEntity> tutupPeriodEntities = getListEntityBatasTutupPeriode(batasTutupPeriod);
                    if (tutupPeriodEntities.size() > 0){
                        ItSimrsBatasTutupPeriodEntity batasTutupPeriodEntity = tutupPeriodEntities.get(0);
                        batasTutupPeriodEntity.setFlagTutup("Y");
                        batasTutupPeriodEntity.setAction("U");
                        batasTutupPeriodEntity.setLastUpdate(bean.getLastUpdate());
                        batasTutupPeriodEntity.setLastUpdateWho(bean.getLastUpdateWho());

                        try {
                            batasTutupPeriodDao.updateAndSave(batasTutupPeriodEntity);
                        } catch (HibernateException e){
                            logger.error("[TutupPeriodBoImpl.updateSaldoAkhirBulanBerjalan] ERROR. ",e);
                            throw new GeneralBOException("[TutupPeriodBoImpl.updateSaldoAkhirBulanBerjalan] ERROR. ",e);
                        }
                    }
                } else {
                    logger.error("[TutupPeriodBoImpl.updateSaldoAkhirBulanBerjalan] ERROR. gagal menyimpan tutup period");
                    throw new GeneralBOException("[TutupPeriodBoImpl.updateSaldoAkhirBulanBerjalan] ERROR. gagal menyimpan tutup period");
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

    private String getNextSaldoAkhirDetailId(){
        String id = "";
        try {
            id = saldoAkhirDetailDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[TutupPeriodBoImpl.getNextSaldoAkhirDetailId] ERROR. ", e);
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

    private String getNextSaldoAkhirTahunId(){
        String id = "";
        try {
            id = saldoAkhirHistoryDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[TutupPeriodBoImpl.getNextSaldoAkhirTahunId] ERROR. ", e);
        }

        return id;
    }

    private String getNextSaldoAkhirDetailTahunId(){
        String id = "";
        try {
            id = saldoAkhirDetailHistoryDao.getNextSeq();
        } catch (HibernateException e){
            logger.error("[TutupPeriodBoImpl.getNextSaldoAkhirDetailTahunId] ERROR. ", e);
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

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setSaldoAkhirDetailDao(SaldoAkhirDetailDao saldoAkhirDetailDao) {
        this.saldoAkhirDetailDao = saldoAkhirDetailDao;
    }

    public void setJurnalDetailActivityDao(JurnalDetailActivityDao jurnalDetailActivityDao) {
        this.jurnalDetailActivityDao = jurnalDetailActivityDao;
    }

    public void setJurnalPendingDao(JurnalPendingDao jurnalPendingDao) {
        this.jurnalPendingDao = jurnalPendingDao;
    }

    public void setJurnalDetailPendingDao(JurnalDetailPendingDao jurnalDetailPendingDao) {
        this.jurnalDetailPendingDao = jurnalDetailPendingDao;
    }

    public void setJurnalDetailActivityPendingDao(JurnalDetailActivityPendingDao jurnalDetailActivityPendingDao) {
        this.jurnalDetailActivityPendingDao = jurnalDetailActivityPendingDao;
    }

    public void setSaldoAkhirHistoryDao(SaldoAkhirHistoryDao saldoAkhirHistoryDao) {
        this.saldoAkhirHistoryDao = saldoAkhirHistoryDao;
    }

    public void setSaldoAkhirDetailHistoryDao(SaldoAkhirDetailHistoryDao saldoAkhirDetailHistoryDao) {
        this.saldoAkhirDetailHistoryDao = saldoAkhirDetailHistoryDao;
    }


}
