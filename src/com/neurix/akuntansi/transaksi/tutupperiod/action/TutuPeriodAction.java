package com.neurix.akuntansi.transaksi.tutupperiod.action;

import com.neurix.akuntansi.transaksi.tutupperiod.bo.TutupPeriodBo;
import com.neurix.akuntansi.transaksi.tutupperiod.model.BatasTutupPeriod;
import com.neurix.akuntansi.transaksi.tutupperiod.model.ItSimrsBatasTutupPeriodEntity;
import com.neurix.akuntansi.transaksi.tutupperiod.model.TutupPeriod;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.lab.bo.LabBo;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.JurnalResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by reza on 18/03/20.
 */
public class TutuPeriodAction extends BaseTransactionAction {
    private static transient Logger logger = Logger.getLogger(TutuPeriodAction.class);

    private TutupPeriodBo tutupPeriodBoProxy;
    private TutupPeriod tutupPeriod;

    public void setTutupPeriodBoProxy(TutupPeriodBo tutupPeriodBoProxy) {
        this.tutupPeriodBoProxy = tutupPeriodBoProxy;
    }

    public TutupPeriod getTutupPeriod() {
        return tutupPeriod;
    }

    public void setTutupPeriod(TutupPeriod tutupPeriod) {
        this.tutupPeriod = tutupPeriod;
    }

    @Override
    public String search() {
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[TutuPeriodAction.initForm] START >>>");
        logger.info("[TutuPeriodAction.initForm] END >>>");
        return SUCCESS;
    }

    public BatasTutupPeriod getDataBatasTutupPeriod(String unit, String tahun, String bulan){

        BatasTutupPeriod batasTutupPeriod = new BatasTutupPeriod();
        batasTutupPeriod.setUnit(unit);
        batasTutupPeriod.setTahun(tahun);
        batasTutupPeriod.setBulan(bulan);

        // get current date
        Date dateNow = new Date(System.currentTimeMillis());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TutupPeriodBo tutupPeriodBo = (TutupPeriodBo) ctx.getBean("tutupPeriodBoProxy");

        List<ItSimrsBatasTutupPeriodEntity> batasTutupPeriodEntities = tutupPeriodBo.getListEntityBatasTutupPeriode(batasTutupPeriod);
        if (batasTutupPeriodEntities.size() > 0){

            ItSimrsBatasTutupPeriodEntity periodEntity = batasTutupPeriodEntities.get(0);

            batasTutupPeriod = new BatasTutupPeriod();
            batasTutupPeriod.setId(periodEntity.getId());
            batasTutupPeriod.setUnit(periodEntity.getUnit());
            batasTutupPeriod.setBulan(periodEntity.getBulan());
            batasTutupPeriod.setTahun(periodEntity.getTahun());
            batasTutupPeriod.setTglBatas(periodEntity.getTglBatas());
            batasTutupPeriod.setFlag(periodEntity.getFlag());
            batasTutupPeriod.setAction(periodEntity.getAction());
            batasTutupPeriod.setLastUpdate(periodEntity.getLastUpdate());
            batasTutupPeriod.setLastUpdateWho(periodEntity.getLastUpdateWho());
            batasTutupPeriod.setCreatedDate(periodEntity.getCreatedDate());
            batasTutupPeriod.setCreatedWho(periodEntity.getCreatedWho());
            batasTutupPeriod.setStTglBatas(periodEntity.getTglBatas() == null ? "" : periodEntity.getTglBatas().toString());

            String statusTanggal = "null";
            if (periodEntity.getTglBatas() != null){
                if (dateNow.before(periodEntity.getTglBatas())){
                    statusTanggal = "kurang";
                } else if (dateNow.after(periodEntity.getTglBatas())){
                    statusTanggal = "lebih";
                } else {
                    statusTanggal = "siap";
                }
            }

            batasTutupPeriod.setStatusTanggal(statusTanggal);
            batasTutupPeriod.setFlagTutup(periodEntity.getFlagTutup());
            return batasTutupPeriod;
        }

        return new BatasTutupPeriod();
    }

    public CrudResponse saveTutupPeriod(String unit, String tahun, String bulan){
        CrudResponse response = new CrudResponse();

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TutupPeriodBo tutupPeriodBo = (TutupPeriodBo) ctx.getBean("tutupPeriodBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        // set object tutup period
        TutupPeriod tutupPeriod = new TutupPeriod();
        tutupPeriod.setUnit(unit);
        tutupPeriod.setTahun(tahun);
        tutupPeriod.setBulan(bulan);
        tutupPeriod.setFlagTutup("Y");
        tutupPeriod.setFlag("Y");
        tutupPeriod.setCreatedDate(time);
        tutupPeriod.setCreatedWho(userLogin);
        tutupPeriod.setLastUpdate(time);
        tutupPeriod.setLastUpdateWho(userLogin);


        try {
            List<HeaderDetailCheckup> detailCheckups = checkupDetailBo.getListRawatInapExisiting(unit);
            if (detailCheckups.size() > 0){
                for (HeaderDetailCheckup detailCheckup : detailCheckups){
                    CrudResponse responseRiwayat =  saveAddToRiwayatTindakan(detailCheckup.getIdDetailCheckup(), detailCheckup.getIdJenisPeriksaPasien());
                    if ("error".equalsIgnoreCase(responseRiwayat.getStatus())){
                        response.setStatus("error");
                        response.setMsg(responseRiwayat.getMsg());
                        return response;
                    }

                    // create jurnal transitoris
                    tutupPeriod.setIdDetailCheckup(detailCheckup.getIdDetailCheckup());
                    tutupPeriod.setIdJenisPeriksaPasien(detailCheckup.getIdJenisPeriksaPasien());
                    createJurnalTransitoris(tutupPeriod);
                }
            }
        } catch (GeneralBOException e){
            logger.error("[TutupPeriodAction.saveTutupPeriod] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("[TutupPeriodAction.saveTutupPeriod] ERROR. "+e);
            return response;
        }


        try {
            tutupPeriodBo.saveUpdateTutupPeriod(tutupPeriod);
            response.setStatus("success");
        } catch (GeneralBOException e){
            logger.error("[TutupPeriodAction.saveTutupPeriod] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("[TutupPeriodAction.saveTutupPeriod] ERROR. "+e);
            return response;
        }
        return response;
    }

    private JurnalResponse createJurnalTransitoris(TutupPeriod bean){
        logger.info("[TutuPeriodAction.createJurnalTransitoris] START >>>");
        JurnalResponse response = new JurnalResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TutupPeriodBo tutupPeriodBo = (TutupPeriodBo) ctx.getBean("tutupPeriodBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");

        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(bean.getIdDetailCheckup());
        if (detailCheckupEntity != null){

        }

        Map mapJurnal = new HashMap();


        logger.info("[TutuPeriodAction.createJurnalTransitoris] END <<<");
        return response;
    }

    public CrudResponse saveLockPeriod(String unit, String tahun, String bulan){
        CrudResponse response = new CrudResponse();

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TutupPeriodBo tutupPeriodBo = (TutupPeriodBo) ctx.getBean("tutupPeriodBoProxy");

        TutupPeriod tutupPeriod = new TutupPeriod();
        tutupPeriod.setUnit(unit);
        tutupPeriod.setTahun(tahun);
        tutupPeriod.setBulan(bulan);
        tutupPeriod.setFlagTutup("P");
        tutupPeriod.setFlag("Y");
        tutupPeriod.setCreatedDate(time);
        tutupPeriod.setCreatedWho(userLogin);
        tutupPeriod.setLastUpdate(time);
        tutupPeriod.setLastUpdateWho(userLogin);

        try {
            tutupPeriodBo.saveUpdateLockPeriod(tutupPeriod);
            response.setStatus("success");
        } catch (GeneralBOException e){
            logger.error("[TutupPeriodAction.saveTutupPeriod] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("[TutupPeriodAction.saveTutupPeriod] ERROR. "+e);
            return response;
        }
        return response;
    }

    public CrudResponse saveAddToRiwayatTindakan(String idDetail, String jenisPasien) {
        logger.info("[CheckupDetailAction.saveAddToRiwayatTindakan] START process >>>");

        CrudResponse response = new CrudResponse();

        if (idDetail != null && !"".equalsIgnoreCase(idDetail)) {

            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String user = CommonUtil.userLogin();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");
            PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
            RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
            LabBo labBo = (LabBo) ctx.getBean("labBoProxy");
            PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
            TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
            RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
            OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");

            List<TindakanRawat> listTindakan = new ArrayList<>();
            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdDetailCheckup(idDetail);
            tindakanRawat.setApproveFlag("Y");

            try {
                listTindakan = tindakanRawatBo.getByCriteria(tindakanRawat);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search tindakan :" + e.getMessage());
                response.setStatus("error");
                response.setMsg("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search tindakan :" + e.getMessage());
                return response;
            }

            if (listTindakan.size() > 0) {
                for (TindakanRawat entity : listTindakan) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdTindakanRawat());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                        response.setStatus("error");
                        response.setMsg("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                        return response;
                    }

                    if (riwayatTindakanList.isEmpty()) {
                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                        riwayatTindakan.setIdTindakan(entity.getIdTindakanRawat());
                        riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                        riwayatTindakan.setNamaTindakan(entity.getNamaTindakan());
                        riwayatTindakan.setTotalTarif(new BigDecimal(entity.getTarifTotal()));
                        riwayatTindakan.setKeterangan("tindakan");
                        riwayatTindakan.setJenisPasien(jenisPasien);
                        riwayatTindakan.setAction("C");
                        riwayatTindakan.setFlag("Y");
                        riwayatTindakan.setCreatedWho(user);
                        riwayatTindakan.setCreatedDate(updateTime);
                        riwayatTindakan.setLastUpdate(updateTime);
                        riwayatTindakan.setLastUpdateWho(user);
                        riwayatTindakan.setTanggalTindakan(entity.getCreatedDate());

                        try {
                            riwayatTindakanBo.saveAdd(riwayatTindakan);
                        } catch (GeneralBOException e) {
                            logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                            response.setStatus("error");
                            response.setMsg("[CheckupDetailAction.saveAddToRiwayatTindakan] ERROR :" + e.getMessage());
                            return response;
                        }
                    }
                }
            }

            List<PeriksaLab> periksaLabList = new ArrayList<>();
            PeriksaLab periksaLab = new PeriksaLab();
            periksaLab.setIdDetailCheckup(idDetail);
            periksaLab.setApproveFlag("Y");

            try {
                periksaLabList = periksaLabBo.getByCriteria(periksaLab);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                response.setStatus("error");
                response.setMsg("[CheckupDetailAction.saveAddToRiwayatTindakan] ERROR :" + e.getMessage());
                return response;
            }

            if (periksaLabList.size() > 0) {
                for (PeriksaLab entity : periksaLabList) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdPeriksaLab());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                        response.setStatus("error");
                        response.setMsg("[CheckupDetailAction.saveAddToRiwayatTindakan] ERROR :" + e.getMessage());
                        return response;
                    }

                    if (riwayatTindakanList.isEmpty()) {
                        PeriksaLab lab = new PeriksaLab();

                        try {
                            lab = periksaLabBo.getTarifTotalPemeriksaan(entity.getIdLab(), entity.getIdPeriksaLab());
                        }catch (HibernateException e){
                            logger.error("Found Error "+e.getMessage());
                            response.setStatus("error");
                            response.setMsg("[CheckupDetailAction.saveAddToRiwayatTindakan] ERROR :" + e.getMessage());
                            return response;
                        }

                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                        riwayatTindakan.setIdTindakan(entity.getIdPeriksaLab());
                        riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                        riwayatTindakan.setNamaTindakan("Periksa Lab " + entity.getLabName());
                        riwayatTindakan.setTotalTarif(lab.getTarif());
                        riwayatTindakan.setKeterangan(lab.getKategoriLabName());
                        riwayatTindakan.setJenisPasien(jenisPasien);
                        riwayatTindakan.setAction("C");
                        riwayatTindakan.setFlag("Y");
                        riwayatTindakan.setCreatedWho(user);
                        riwayatTindakan.setCreatedDate(updateTime);
                        riwayatTindakan.setLastUpdate(updateTime);
                        riwayatTindakan.setLastUpdateWho(user);
                        riwayatTindakan.setTanggalTindakan(entity.getCreatedDate());

                        try {
                            riwayatTindakanBo.saveAdd(riwayatTindakan);
                        } catch (GeneralBOException e) {
                            logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                            response.setStatus("error");
                            response.setMsg("[CheckupDetailAction.saveAddToRiwayatTindakan] ERROR :" + e.getMessage());
                            return response;
                        }
                    }
                }
            }

            List<PermintaanResep> resepList = new ArrayList<>();
            PermintaanResep resep = new PermintaanResep();
            resep.setIdDetailCheckup(idDetail);
//            resep.setFlag("Y");

            try {
                resepList = permintaanResepBo.getByCriteria(resep);
            } catch (GeneralBOException e) {
                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search tindakan :" + e.getMessage());
                response.setStatus("error");
                response.setMsg("[CheckupDetailAction.saveAddToRiwayatTindakan] ERROR :" + e.getMessage());
                return response;
            }

            if (resepList.size() > 0) {
                for (PermintaanResep entity : resepList) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdPermintaanResep());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                        response.setStatus("error");
                        response.setMsg("[CheckupDetailAction.saveAddToRiwayatTindakan] ERROR :" + e.getMessage());
                        return response;
                    }

                    if (riwayatTindakanList.isEmpty()) {

//                        List<TransaksiObatDetail> obatDetailList = new ArrayList<>();
//                        TransaksiObatDetail detail = new TransaksiObatDetail();
//                        detail.setIdPermintaanResep(entity.getIdPermintaanResep());
                        TransaksiObatDetail obatDetailList = new TransaksiObatDetail();

                        try {
                            obatDetailList = transaksiObatBo.getTotalHargaResep(entity.getIdPermintaanResep());
                        } catch (HibernateException e) {
                            logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search list detail obat :" + e.getMessage());
                            response.setStatus("error");
                            response.setMsg("[CheckupDetailAction.saveAddToRiwayatTindakan] ERROR :" + e.getMessage());
                            return response;
                        }

//                        BigInteger hitungTotalResep = hitungTotalBayar(obatDetailList);
                        if (obatDetailList.getTotalHarga() != null && !"".equalsIgnoreCase(obatDetailList.getTotalHarga().toString())) {
                            RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                            riwayatTindakan.setIdTindakan(entity.getIdPermintaanResep());
                            riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                            riwayatTindakan.setNamaTindakan("Tarif Resep dengan No. Resep " + entity.getIdPermintaanResep());
                            riwayatTindakan.setTotalTarif(new BigDecimal(obatDetailList.getTotalHarga()));
                            riwayatTindakan.setKeterangan("resep");
                            riwayatTindakan.setJenisPasien(jenisPasien);
                            riwayatTindakan.setAction("C");
                            riwayatTindakan.setFlag("Y");
                            riwayatTindakan.setCreatedWho(user);
                            riwayatTindakan.setCreatedDate(updateTime);
                            riwayatTindakan.setLastUpdate(updateTime);
                            riwayatTindakan.setLastUpdateWho(user);
                            riwayatTindakan.setTanggalTindakan(entity.getCreatedDate());

                            try {
                                riwayatTindakanBo.saveAdd(riwayatTindakan);
                            } catch (GeneralBOException e) {
                                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                                response.setStatus("error");
                                response.setMsg("[CheckupDetailAction.saveAddToRiwayatTindakan] ERROR :" + e.getMessage());
                                return response;
                            }
                        }
                    }
                }
            }

            RawatInap rawatInap = new RawatInap();
            rawatInap.setIdDetailCheckup(idDetail);
            List<RawatInap> rawatInapList = new ArrayList<>();

            try {
                rawatInapList = rawatInapBo.getByCriteria(rawatInap);
            } catch (GeneralBOException e) {
                logger.error("Found Error" + e.getMessage());
                response.setStatus("error");
                response.setMsg("[CheckupDetailAction.saveAddToRiwayatTindakan] ERROR :" + e.getMessage());
                return response;
            }

            if (rawatInapList.size() > 0) {

                rawatInap = rawatInapList.get(0);

                if (rawatInap != null) {

                    OrderGizi orderGizi = new OrderGizi();
                    orderGizi.setIdRawatInap(rawatInap.getIdRawatInap());
                    orderGizi.setDiterimaFlag("Y");
                    List<OrderGizi> giziList = new ArrayList<>();

                    try {
                        giziList = orderGiziBo.getByCriteria(orderGizi);
                    } catch (GeneralBOException e) {
                        logger.error("Found Error" + e.getMessage());
                        response.setStatus("error");
                        response.setMsg("[CheckupDetailAction.saveAddToRiwayatTindakan] ERROR :" + e.getMessage());
                        return response;
                    }

                    if (giziList.size() > 0) {

                        for (OrderGizi gizi : giziList) {

                            List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                            RiwayatTindakan tindakan = new RiwayatTindakan();
                            tindakan.setIdTindakan(gizi.getIdOrderGizi());

                            try {
                                riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                            } catch (HibernateException e) {
                                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                                response.setStatus("error");
                                response.setMsg("[CheckupDetailAction.saveAddToRiwayatTindakan] ERROR :" + e.getMessage());
                                return response;
                            }

                            if (riwayatTindakanList.isEmpty()) {

                                RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                                riwayatTindakan.setIdTindakan(gizi.getIdOrderGizi());
                                riwayatTindakan.setIdDetailCheckup(rawatInap.getIdDetailCheckup());
                                riwayatTindakan.setNamaTindakan("Tarif Gizi dengan No. Gizi " + gizi.getIdOrderGizi());
                                riwayatTindakan.setTotalTarif(gizi.getTarifTotal());
                                riwayatTindakan.setKeterangan("gizi");
                                riwayatTindakan.setJenisPasien(jenisPasien);
                                riwayatTindakan.setAction("C");
                                riwayatTindakan.setFlag("Y");
                                riwayatTindakan.setCreatedWho(user);
                                riwayatTindakan.setCreatedDate(updateTime);
                                riwayatTindakan.setLastUpdate(updateTime);
                                riwayatTindakan.setLastUpdateWho(user);
                                riwayatTindakan.setTanggalTindakan(gizi.getCreatedDate());

                                try {
                                    riwayatTindakanBo.saveAdd(riwayatTindakan);
                                } catch (GeneralBOException e) {
                                    logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                                    response.setStatus("error");
                                    response.setMsg("[CheckupDetailAction.saveAddToRiwayatTindakan] ERROR :" + e.getMessage());
                                    return response;
                                }
                            }
                        }
                    }
                }
            }
        }

        logger.info("[CheckupDetailAction.saveAddToRiwayatTindakan] END process >>>");
        response.setStatus("success");
        return response;
    }
}
