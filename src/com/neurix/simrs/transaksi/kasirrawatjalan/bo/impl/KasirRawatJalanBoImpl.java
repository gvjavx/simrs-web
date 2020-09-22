package com.neurix.simrs.transaksi.kasirrawatjalan.bo.impl;

import com.neurix.akuntansi.master.pembayaran.dao.PembayaranDao;
import com.neurix.akuntansi.master.pembayaran.model.ImAkunPembayaranEntity;
import com.neurix.akuntansi.master.pembayaran.model.Pembayaran;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.dao.FpkDao;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.Fpk;
import com.neurix.simrs.transaksi.checkup.model.ItSImrsFpkEntity;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.dao.UangMukaDao;
import com.neurix.simrs.transaksi.checkupdetail.model.*;
import com.neurix.simrs.transaksi.kasirrawatjalan.bo.KasirRawatJalanBo;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KasirRawatJalanBoImpl implements KasirRawatJalanBo {

    private RiwayatTindakanDao riwayatTindakanDao;
    private RawatInapDao rawatInapDao;
    private UangMukaDao uangMukaDao;
    private CheckupDetailDao checkupDetailDao;
    private FpkDao fpkDao;
    private PembayaranDao pembayaranDao;

    public void setPembayaranDao(PembayaranDao pembayaranDao) {
        this.pembayaranDao = pembayaranDao;
    }

    public void setFpkDao(FpkDao fpkDao) {
        this.fpkDao = fpkDao;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setUangMukaDao(UangMukaDao uangMukaDao) {
        this.uangMukaDao = uangMukaDao;
    }

    public void setRawatInapDao(RawatInapDao rawatInapDao) {
        this.rawatInapDao = rawatInapDao;
    }

    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
    }

    private static transient Logger logger = Logger.getLogger(KasirRawatJalanBoImpl.class);

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public List<RiwayatTindakan> getListAllTindakan(RiwayatTindakan bean) throws GeneralBOException {
        logger.info("[KasirRawatJalanBoImpl.getListAllTindakan] START process <<<");
        List<RiwayatTindakan> result = new ArrayList<>();
        if(bean != null){
            try {
                result = riwayatTindakanDao.getListTindakan(bean);
            }catch (HibernateException e){
                logger.error("[KasirRawatJalanBoImpl.getListAllTindakan] Error when save update data flag approve tindakan rawat ", e);
            }
        }
        logger.info("[KasirRawatJalanBoImpl.getListAllTindakan] END process <<<");
        return result;
    }

    @Override
    public List<UangMuka> getListUangMuka(UangMuka bean) throws GeneralBOException {
        List<UangMuka> uangMukaList = new ArrayList<>();

        if(bean != null){

            Map hsCriterian = new HashMap();

            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                hsCriterian.put("id_detail_checkup", bean.getIdDetailCheckup());
            }
            if(bean.getStatusBayar() != null && !"".equalsIgnoreCase(bean.getStatusBayar())){
                hsCriterian.put("status_bayar", bean.getStatusBayar());
//                hsCriterian.put("nota_not_null", "Y");
            }
            if(bean.getFlagRefund() != null && !"".equalsIgnoreCase(bean.getFlagRefund())){
                hsCriterian.put("flag_refund", bean.getFlagRefund());
            }

            List<ItSimrsUangMukaPendaftaranEntity> entityList = new ArrayList<>();
            try {
                entityList = uangMukaDao.getByCriteria(hsCriterian);
            }catch (HibernateException e){
                logger.error("Found Error when search uang muka");
            }

            if(entityList.size() > 0){
                UangMuka uangMuka;
                for (ItSimrsUangMukaPendaftaranEntity entity :entityList){
                    uangMuka = new UangMuka();
                    uangMuka.setIdDetailCheckup(entity.getIdDetailCheckup());
                    uangMuka.setId(entity.getId());
                    uangMuka.setJumlah(entity.getJumlah());
                    uangMuka.setDibayar(entity.getJumlahDibayar());
                    uangMuka.setCreatedDate(entity.getCreatedDate());
                    uangMuka.setCreatedWho(entity.getCreatedWho());
                    uangMuka.setFlag(entity.getFlag());
                    uangMuka.setStatusBayar(entity.getStatusBayar());
                    uangMuka.setNoNota(entity.getNoNota());
                    String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(entity.getCreatedDate());
                    uangMuka.setStCreatedDate(formatDate);
                    uangMuka.setStDate(entity.getCreatedDate().toString());
                    uangMukaList.add(uangMuka);
                }
            }
        }
        return uangMukaList;
    }

    @Override
    public void updateNotaUangMukaById(UangMuka bean) throws GeneralBOException {
        Map hsCriteria = new HashMap();
        hsCriteria.put("id", bean.getId());

        List<ItSimrsUangMukaPendaftaranEntity> uangMukaPendaftaranEntities = new ArrayList<>();
        try {
            uangMukaPendaftaranEntities = uangMukaDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[KasirRawatJalanBoImpl.updateNotaUangMukaById] Error ", e);
            new GeneralBOException("[KasirRawatJalanBoImpl.updateNotaUangMukaById] Error  ", e);
        }

        if (uangMukaPendaftaranEntities.size() > 0){
            for (ItSimrsUangMukaPendaftaranEntity pendaftaranEntity : uangMukaPendaftaranEntities){
                pendaftaranEntity.setStatusBayar("Y");
                pendaftaranEntity.setAction("U");
                pendaftaranEntity.setJumlahDibayar(bean.getDibayar());
                pendaftaranEntity.setLastUpdate(bean.getLastUpdate());
                pendaftaranEntity.setLastUpdateWho(bean.getLastUpdateWho());
                pendaftaranEntity.setNoJurnal(bean.getNoJurnal());

                try {
                    uangMukaDao.updateAndSave(pendaftaranEntity);
                } catch (HibernateException e){
                    logger.error("[KasirRawatJalanBoImpl.updateNotaUangMukaById] Error ", e);
                    new GeneralBOException("[KasirRawatJalanBoImpl.updateNotaUangMukaById] Error  ", e);
                }
            }
        }

    }

    @Override
    public List<HeaderDetailCheckup> getSearchFPK(HeaderDetailCheckup bean) throws GeneralBOException {

        List<HeaderDetailCheckup> list = new ArrayList<>();
        if(bean != null){

            try {
                list = checkupDetailDao.getSearchFPK(bean);
            }catch (HibernateException e){
                logger.error("Found Error "+e.getMessage());
            }
            for (HeaderDetailCheckup detailCheckup : list){
                detailCheckup.setTotalBiaya(checkupDetailDao.getBiayaByNoSep(detailCheckup.getNoSep()).toBigInteger());
                detailCheckup.setStTotalBiaya(CommonUtil.numbericFormat(checkupDetailDao.getBiayaByNoSep(detailCheckup.getNoSep()),"###,###"));
            }
        }
        return list;
    }

    @Override
    public CrudResponse saveNoFPK(List<Fpk> listData) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(listData != null){

            for (Fpk list: listData){
                ItSImrsFpkEntity entity = new ItSImrsFpkEntity();
                entity.setIdFpk("FPK"+getNextIdFpk());
                entity.setNoSep(list.getNoSep());
                entity.setNoFpk(list.getNoFpk());
                entity.setIdDetailCheckup(list.getIdDetailCheckup());
                entity.setTanggalFpk(list.getTanggalFpk());
                entity.setFlag("Y");
                entity.setAction("C");
                entity.setCreatedWho(CommonUtil.userLogin());
                entity.setLastUpdateWho(CommonUtil.userLogin());
                entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));

                try {
                    fpkDao.addAndSave(entity);
                    response.setStatus("success");
                }catch (HibernateException e){
                    logger.error("Found Error");
                    response.setStatus("error");
                    response.setMsg("Found Error "+e);
                }
            }
        }
        return response;
    }

    @Override
    public CrudResponse pembayaranFPK(List<Fpk> listData) throws GeneralBOException {
        CrudResponse response = new CrudResponse();
        if(listData != null){

            for (Fpk list: listData){
                ItSImrsFpkEntity entity = new ItSImrsFpkEntity();

                try {
                    entity = fpkDao.getById("idFpk", list.getIdFpk());
                }catch (HibernateException e){
                    logger.error("Found Error");
                    response.setStatus("error");
                    response.setMsg("Found Errror "+e);
                }

                if(entity != null){

                    entity.setStatusBayar("Y");
                    entity.setNoSlip(list.getNoSlip());
                    entity.setAction("U");
                    entity.setLastUpdateWho(CommonUtil.userLogin());
                    entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));

                    try {
                        fpkDao.updateAndSave(entity);
                        response.setStatus("success");
                    }catch (HibernateException e){
                        logger.error("Found Error");
                        response.setStatus("error");
                        response.setMsg("Found Error "+e);
                    }
                }
            }
        }
        return response;
    }

    @Override
    public CheckResponse saveRefund(String id, String noJurnal) throws GeneralBOException {
        CheckResponse response = new CheckResponse();

        ItSimrsUangMukaPendaftaranEntity entity = new ItSimrsUangMukaPendaftaranEntity();

        try {

            entity = uangMukaDao.getById("id", id);

            if(entity != null){

                entity.setFlagRefund("Y");
                entity.setLastUpdateWho(CommonUtil.userLogin());
                entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                entity.setNoJurnalRefund(noJurnal);

                try {
                    uangMukaDao.updateAndSave(entity);
                    response.setStatus("success");
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMessage("Found Error "+e.getMessage());
                    logger.error("Found error "+e.getMessage());
                }
            }

        }catch (HibernateException e){
            response.setStatus("error");
            response.setMessage("Found Error "+e.getMessage());
            logger.error("Found error "+e.getMessage());
        }

        return response;
    }

    @Override
    public List<ImAkunPembayaranEntity> getListPembayaran() throws GeneralBOException {
        List<ImAkunPembayaranEntity> list = new ArrayList<>();
        Map hscriteria = new HashMap();
        hscriteria.put("flag", "Y");
        try {
            list = pembayaranDao.getByCriteria(hscriteria);
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }
        return list;
    }

    private String getNextIdFpk() {
        String id = "";
        try {
            id = fpkDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[RiwayatTindakanBoImpl.getNextIdRiwayatTindakan] ERROR When create sequences", e);
        }
        return id;
    }

    @Override
    public List<KlaimFpkDTO> getSearchCheckupBySep(String noSep) throws GeneralBOException {
        List<KlaimFpkDTO> list = new ArrayList<>();
        List<KlaimFpkDTO> listOfResult = new ArrayList<>();
        try {
            list = checkupDetailDao.getSearchCheckupBySep(noSep);
        }catch (HibernateException e){
            logger.error("Found Error "+e.getMessage());
        }

        for (KlaimFpkDTO data : list){
            if (("Y").equalsIgnoreCase(data.getStatusBayar())){
                data.setStatus("Y");
            }
            listOfResult.add(data);
        }
        return listOfResult;
    }

    @Override
    public Map setMappingJurnalFpk(KlaimFpkDTO data,List<KlaimFpkDTO> listOfKlaim){
        Map dataMap = new HashMap();
        List<Map> piutangeksesbpjslist = new ArrayList<>();
        List<Map> piutangpasienbpjslist = new ArrayList<>();
        List<Map> pendapatanselisihbpjslist = new ArrayList<>();
        List<Map> activityList = new ArrayList<>();
        BigInteger jumlahPiutangTerverif = BigInteger.ZERO;
        for (KlaimFpkDTO dataKlaim : listOfKlaim){
            //mendapat id poli terlebih dahulu
            String divisiId ="";
            List<RiwayatTindakanDTO> riwayatTindakanDTO = checkupDetailDao.getRiwayatTindakanDanDokter(dataKlaim.getIdDetailCheckup());
            for (RiwayatTindakanDTO riwayattindakan : riwayatTindakanDTO){
                Map activity = new HashMap();
                activity.put("activity_id",riwayattindakan.getIdTindakan());
                activity.put("person_id",riwayattindakan.getIdDokter());
                activity.put("nilai",new BigDecimal(riwayattindakan.getTotalTarif()));
                activity.put("no_trans",dataKlaim.getIdDetailCheckup());
                activity.put("tipe","bpjs");
                activityList.add(activity);

                divisiId=riwayattindakan.getKoderingPoli();
            }

            if (("P").equalsIgnoreCase(dataKlaim.getStatusBayar())){
                Map piutangPasien = new HashMap();
                piutangPasien.put("nilai",new BigDecimal(dataKlaim.getTotalBiayaBpjs()));
                piutangPasien.put("bukti",dataKlaim.getNoSep());
                piutangPasien.put("master_id","02.000");
                piutangPasien.put("activity",activityList);
                piutangpasienbpjslist.add(piutangPasien);

                jumlahPiutangTerverif=jumlahPiutangTerverif.add(dataKlaim.getTotalBiayaBpjs());
            }else if (("LB").equalsIgnoreCase(dataKlaim.getStatusBayar())){
                Map piutangPasien = new HashMap();
                piutangPasien.put("nilai",new BigDecimal(dataKlaim.getTotalBiaya()));
                piutangPasien.put("bukti",dataKlaim.getNoSep());
                piutangPasien.put("master_id","02.000");
                piutangPasien.put("activity",activityList);
                piutangpasienbpjslist.add(piutangPasien);

                Map pendapatanselisihbpjs = new HashMap();
                pendapatanselisihbpjs.put("nilai",new BigDecimal(dataKlaim.getTotalBiayaBpjs().subtract(dataKlaim.getTotalBiaya())));
                pendapatanselisihbpjs.put("bukti",dataKlaim.getNoSep());
                pendapatanselisihbpjs.put("divisi_id",divisiId);
                pendapatanselisihbpjs.put("master_id","02.000");
                pendapatanselisihbpjslist.add(pendapatanselisihbpjs);

                jumlahPiutangTerverif=jumlahPiutangTerverif.add(dataKlaim.getTotalBiayaBpjs());
            }else if (("KB").equalsIgnoreCase(dataKlaim.getStatusBayar())){
                Map piutangeksesbpjs = new HashMap();
                piutangeksesbpjs.put("nilai",new BigDecimal(dataKlaim.getTotalBiaya().subtract(dataKlaim.getTotalBiayaBpjs())));
                piutangeksesbpjs.put("bukti",data.getNoSep());
                piutangeksesbpjs.put("master_id","02.000");
                piutangeksesbpjslist.add(piutangeksesbpjs);

                Map piutangPasien = new HashMap();
                piutangPasien.put("nilai",new BigDecimal(dataKlaim.getTotalBiaya()));
                piutangPasien.put("bukti",dataKlaim.getNoSep());
                piutangPasien.put("master_id","02.000");
                piutangPasien.put("activity",activityList);
                piutangpasienbpjslist.add(piutangPasien);

                jumlahPiutangTerverif=jumlahPiutangTerverif.add(dataKlaim.getTotalBiayaBpjs());
            }
        }

        Map piutangTerverif = new HashMap();
        piutangTerverif.put("nilai",new BigDecimal(jumlahPiutangTerverif));
        piutangTerverif.put("bukti",data.getNoFpk());
        piutangTerverif.put("master_id","02.000");

        //create zero value
        Map piutangPasien = new HashMap();
        piutangPasien.put("nilai",BigDecimal.ZERO);
        piutangPasien.put("bukti","");
        piutangPasien.put("master_id","02.000");
        piutangpasienbpjslist.add(piutangPasien);

        Map piutangeksesbpjs = new HashMap();
        piutangeksesbpjs.put("nilai",BigDecimal.ZERO);
        piutangeksesbpjs.put("bukti","");
        piutangeksesbpjs.put("master_id","02.000");
        piutangeksesbpjslist.add(piutangeksesbpjs);

        Map pendapatanselisihbpjs = new HashMap();
        pendapatanselisihbpjs.put("nilai",BigDecimal.ZERO);
        pendapatanselisihbpjs.put("bukti","");
        pendapatanselisihbpjs.put("divisi_id","");
        pendapatanselisihbpjs.put("master_id","02.000");
        pendapatanselisihbpjslist.add(pendapatanselisihbpjs);

        dataMap.put("piutang_terverif",piutangTerverif);
        dataMap.put("piutang_ekses_bpjs",piutangeksesbpjslist);
        dataMap.put("piutang_pasien_bpjs",piutangpasienbpjslist);
        dataMap.put("pendapatan_selisih_bpjs",pendapatanselisihbpjslist);
        return dataMap;
    }

    @Override
    public void saveFpk(KlaimFpkDTO klaimFpkDTO, List<KlaimFpkDTO> listData) throws GeneralBOException {
        if(listData != null){

            for (KlaimFpkDTO list: listData){
                if (("P").equalsIgnoreCase(list.getStatusBayar())||("LB").equalsIgnoreCase(list.getStatusBayar())||("KB").equalsIgnoreCase(list.getStatusBayar())){
                    //save FPK
                    ItSImrsFpkEntity entity = new ItSImrsFpkEntity();
                    entity.setIdFpk("FPK"+getNextIdFpk());
                    entity.setNoSep(list.getNoSep());
                    entity.setNoFpk(klaimFpkDTO.getNoFpk());
                    entity.setIdDetailCheckup(list.getIdDetailCheckup());
                    entity.setTanggalFpk(CommonUtil.convertStringToDate(klaimFpkDTO.getStTanggalFpk()));
                    entity.setStatusBayar("Y");
                    entity.setFlag("Y");
                    entity.setAction("C");
                    entity.setCreatedWho(CommonUtil.userLogin());
                    entity.setLastUpdateWho(CommonUtil.userLogin());
                    entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    try {
                        fpkDao.addAndSave(entity);
                    }catch (HibernateException e){
                        logger.error("Found Error");
                    }

                    //Update detail checkup bayar to Y
                    ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup",list.getIdDetailCheckup());
                    detailCheckupEntity.setStatusBayar("Y");
                    detailCheckupEntity.setLastUpdateWho(CommonUtil.userLogin());
                    detailCheckupEntity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    try {
                        checkupDetailDao.updateAndSave(detailCheckupEntity);
                    }catch (HibernateException e){
                        logger.error("Found Error");
                    }

                }
            }
        }
    }

    @Override
    public ItSimrsUangMukaPendaftaranEntity getEnityUangMukaById(String id) throws GeneralBOException {
        return uangMukaDao.getById("id", id);
    }

    @Override
    public ImAkunPembayaranEntity getPembayaranEntityByCoa(String coa) throws GeneralBOException {
        return pembayaranDao.getById("coa", coa);
    }

    @Override
    public ItSimrsUangMukaPendaftaranEntity getUangMukaEntityById(String id) throws GeneralBOException {
        return uangMukaDao.getById("id", id);
    }
}
