package com.neurix.simrs.transaksi.daftarulang.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.ruangan.dao.RuanganDao;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.master.ruangan.model.Ruangan;
import com.neurix.simrs.master.tindakan.dao.TindakanDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.dao.UangMukaDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsUangMukaPendaftaranEntity;
import com.neurix.simrs.transaksi.daftarulang.bo.DaftarUlangBo;
import com.neurix.simrs.transaksi.diagnosarawat.dao.DiagnosaRawatDao;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.diagnosarawat.model.ItSimrsDiagnosaRawatEntity;
import com.neurix.simrs.transaksi.rawatinap.dao.RawatInapDao;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.teamdokter.dao.DokterTeamDao;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.neurix.simrs.transaksi.tindakanrawat.dao.TindakanRawatDao;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaftarUlangBoImpl implements DaftarUlangBo {

    public static transient Logger logger = Logger.getLogger(DaftarUlangBoImpl.class);
    private CheckupDetailDao checkupDetailDao;
    private RiwayatTindakanDao riwayatTindakanDao;
    private UangMukaDao uangMukaDao;
    private DokterTeamDao dokterTeamDao;
    private TindakanRawatDao tindakanRawatDao;
    private RawatInapDao rawatInapDao;
    private DiagnosaRawatDao diagnosaRawatDao;
    private RuanganDao ruanganDao;
    private TindakanDao tindakanDao;
    private HeaderCheckupDao headerCheckupDao;

    @Override
    public List<HeaderDetailCheckup> getListDaftarUlangPasien(HeaderDetailCheckup headerDetailCheckup) throws GeneralBOException {
        List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();

        if (headerDetailCheckup != null) {
            try {
                detailCheckupList = checkupDetailDao.getDaftarUlangPasien(headerDetailCheckup);
            } catch (HibernateException e) {
                logger.error("Found Error when search status periksa " + e.getMessage());
            }
        }

        return detailCheckupList;
    }

    @Override
    public CheckResponse saveDaftarUlang(HeaderDetailCheckup bean) throws GeneralBOException {
        logger.info("[CheckupDetailBoImpl.saveEdit] Start >>>>>>>");
        CheckResponse response = new CheckResponse();

        ItSimrsHeaderChekupEntity chekupEntity = new ItSimrsHeaderChekupEntity();

        try {
            chekupEntity = headerCheckupDao.getById("noCheckup", bean.getNoCheckup());
            if(chekupEntity != null){
                try {

                    chekupEntity.setLastUpdate(bean.getLastUpdate());
                    chekupEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        headerCheckupDao.updateAndSave(chekupEntity);
                    }catch (HibernateException e){
                        response.setStatus("error");
                        response.setMessage("Error When Saving data checkup " + e.getMessage());
                    }

                }catch (HibernateException e){
                    logger.error("Found Error "+e.getMessage());
                    response.setStatus("error");
                    response.setMessage("Error When Saving data checkup " + e.getMessage());
                }
            }

        }catch (HibernateException e){
            logger.error("Found Error "+e.getMessage());
            response.setStatus("error");
            response.setMessage("Error When Saving data detail checkup " + e.getMessage());
        }

        ItSimrsHeaderDetailCheckupEntity detail = new ItSimrsHeaderDetailCheckupEntity();

        try {
            detail = checkupDetailDao.getById("idDetailCheckup", bean.getIdDetailCheckup());

            if(detail != null){

                detail.setKeteranganSelesai("Sudah Lanjut Biaya");
                detail.setLastUpdateWho(bean.getLastUpdateWho());
                detail.setLastUpdate(bean.getLastUpdate());

                try {
                    checkupDetailDao.updateAndSave(detail);
                    response.setStatus("success");
                    response.setMessage("Berhasil");
                }catch (HibernateException e){
                    response.setStatus("error");
                    response.setMessage("Error When Saving data detail checkup " + e.getMessage());
                    logger.error("found error "+e.getMessage());
                }
            }

        }catch (HibernateException e){
            logger.error("Found Error "+e.getMessage());
            response.setStatus("error");
            response.setMessage("Error When Saving data detail checkup " + e.getMessage());
        }

        String id = "";
        String idDokter = "";
        id = getNextDetailCheckupId();

        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();
        detailCheckupEntity.setIdDetailCheckup("DCM" + id);
        detailCheckupEntity.setNoCheckup(bean.getNoCheckup());
        detailCheckupEntity.setIdPelayanan(detail.getIdPelayanan());
        detailCheckupEntity.setStatusPeriksa("1");
        detailCheckupEntity.setFlag("Y");
        detailCheckupEntity.setAction("C");
        detailCheckupEntity.setCreatedDate(bean.getCreatedDate());
        detailCheckupEntity.setCreatedWho(bean.getCreatedWho());
        detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
        detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
        detailCheckupEntity.setTglAntrian(bean.getCreatedDate());
        detailCheckupEntity.setIdJenisPeriksaPasien(bean.getIdJenisPeriksaPasien());
        detailCheckupEntity.setBranchId(CommonUtil.userBranchLogin());

        if("umum".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
            detailCheckupEntity.setMetodePembayaran(bean.getMetodePembayaran());
        }

        if("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
            detailCheckupEntity.setNoRujukan(bean.getNoRujukan());
            detailCheckupEntity.setNoPpkRujukan(bean.getNoPpk());
            detailCheckupEntity.setTglRujukan(Date.valueOf(bean.getTglRujukan()));
            detailCheckupEntity.setUrlDocRujuk(bean.getSuratRujukan());
            detailCheckupEntity.setIdPelayananBpjs(bean.getIdPelayanan());
            detailCheckupEntity.setKelasPasien(bean.getIdKelas());
            detailCheckupEntity.setRujuk(bean.getPerujuk());
            detailCheckupEntity.setKetRujukan(bean.getNamaPerujuk());
            detailCheckupEntity.setNoSep(bean.getNoSep());
            detailCheckupEntity.setTarifBpjs(bean.getTarifBpjs());
            detailCheckupEntity.setKodeCbg(bean.getKodeCbg());
        }

        if("asuransi".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
            detailCheckupEntity.setIdAsuransi(bean.getIdAsuransi());
            detailCheckupEntity.setNoKartuAsuransi(bean.getNoKartuAsuransi());
            detailCheckupEntity.setCoverBiaya(bean.getCoverBiaya());
        }

        try {
            checkupDetailDao.addAndSave(detailCheckupEntity);
            response.setStatus("success");
            response.setMessage("Berhasil");
        } catch (HibernateException e) {
            response.setStatus("error");
            response.setMessage("Error When Saving data detail checkup " + e.getMessage());
            logger.error("[CheckupDetailBoImpl.saveAdd] Error When Saving data detail checkup" + e.getMessage());
            throw new GeneralBOException("[CheckupDetailBoImpl.saveAdd] Error When Saving data detail checkup");
        }

        // saving dokter
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {

            List<ItSimrsDokterTeamEntity> listDokter = new ArrayList<>();

            Map hsCriteria = new HashMap();
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());
            try {
                listDokter = dokterTeamDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                response.setStatus("error");
                response.setMessage("Found Error "+e.getMessage());
                logger.error("Found Error when search dokter "+e.getMessage());
            }

            ItSimrsDokterTeamEntity dokter = new ItSimrsDokterTeamEntity();
            if(listDokter.size() > 0){
                dokter = listDokter.get(0);
                idDokter = dokter.getIdDokter();
            }

            DokterTeam dokterTeam = new DokterTeam();
            dokterTeam.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
            dokterTeam.setIdDokter(dokter.getIdDokter());
            dokterTeam.setCreatedWho(bean.getCreatedWho());
            dokterTeam.setLastUpdateWho(bean.getLastUpdateWho());
            response = saveTeamDokter(dokterTeam);
        }

        // for rawat Inap
        if (bean.getRawatInap()) {
            List<ItSimrsDokterTeamEntity> dokterEntities = null;
            Map hsCriteria = new HashMap();
            // set criteria with old detail id for get old dokter team data
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());

            try {
                dokterEntities = dokterTeamDao.getByCriteria(hsCriteria);
                response.setStatus("success");
                response.setMessage("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Errro when search dokter team " + e.getMessage());
                logger.error("[CheckupDetailBoImpl.saveAdd] Error When getting data dokter team" + e.getMessage());
                throw new GeneralBOException("[CheckupDetailBoImpl.saveAdd] Error When getting data dokter team");
            }

            ItSimrsDokterTeamEntity dokTeam = new ItSimrsDokterTeamEntity();

            if (!dokterEntities.isEmpty()) {
                dokTeam = dokterEntities.get(0);

                DokterTeam dokterTeam = new DokterTeam();
                // set id detail with new id detail
                dokterTeam.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                dokterTeam.setIdDokter(dokTeam.getIdDokter());
                dokterTeam.setCreatedWho(bean.getCreatedWho());
                dokterTeam.setLastUpdateWho(bean.getLastUpdateWho());
                response = saveTeamDokter(dokterTeam);
            }

            // save to table rawat inap
            RawatInap rawatInap = new RawatInap();
            rawatInap.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
            rawatInap.setIdRuangan(bean.getIdRuangan());
            rawatInap.setNoCheckup(bean.getNoCheckup());
            rawatInap.setCreatedDate(bean.getCreatedDate());
            rawatInap.setCreatedWho(bean.getCreatedWho());
            response = saveRawatInap(rawatInap);

            if (bean.getTindakanList() != null && bean.getTindakanList().size() > 0) {
                for (Tindakan tindakan : bean.getTindakanList()) {
                    List<ImSimrsTindakanEntity> tindakanEntities = getListEntityTindakan(tindakan);
                    if (tindakanEntities.size() > 0) {

                        ImSimrsTindakanEntity tindakanEntity = tindakanEntities.get(0);
                        ItSimrsTindakanRawatEntity tindakanRawatEntity = new ItSimrsTindakanRawatEntity();
                        tindakanRawatEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                        tindakanRawatEntity.setIdTindakanRawat("TDR" + getNextTindakanRawatId());
                        tindakanRawatEntity.setIdTindakan(tindakanEntity.getIdTindakan());
                        tindakanRawatEntity.setNamaTindakan(tindakanEntity.getTindakan());
                        tindakanRawatEntity.setIdDokter(dokTeam.getIdDokter());
                        tindakanRawatEntity.setCreatedDate(bean.getCreatedDate());
                        tindakanRawatEntity.setCreatedWho(bean.getCreatedWho());
                        tindakanRawatEntity.setLastUpdate(bean.getCreatedDate());
                        tindakanRawatEntity.setLastUpdateWho(bean.getCreatedWho());
                        tindakanRawatEntity.setFlag("Y");
                        tindakanRawatEntity.setAction("U");

                        if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                            tindakanRawatEntity.setTarif(tindakanEntity.getTarifBpjs());
                        } else {
                            tindakanRawatEntity.setTarif(tindakanEntity.getTarif());
                        }

                        tindakanRawatEntity.setQty(new BigInteger(String.valueOf(1)));
                        tindakanRawatEntity.setTarifTotal(tindakanRawatEntity.getTarif().multiply(tindakanRawatEntity.getQty()));

                        try {

                            tindakanRawatDao.addAndSave(tindakanRawatEntity);

                            if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {

                                ItSimrsRiwayatTindakanEntity riwayatTindakan = new ItSimrsRiwayatTindakanEntity();
                                riwayatTindakan.setIdRiwayatTindakan("RWT" + getNextIdRiwayatTindakan());
                                riwayatTindakan.setIdTindakan(tindakanRawatEntity.getIdTindakanRawat());
                                riwayatTindakan.setNamaTindakan(tindakanRawatEntity.getNamaTindakan());
                                riwayatTindakan.setTotalTarif(new BigDecimal(String.valueOf(tindakanRawatEntity.getTarifTotal())));
                                riwayatTindakan.setApproveBpjsFlag("Y");
                                riwayatTindakan.setKategoriTindakanBpjs("konsultasi");
                                riwayatTindakan.setKeterangan("tindakan");
                                riwayatTindakan.setJenisPasien(bean.getIdJenisPeriksaPasien());
                                riwayatTindakan.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                                riwayatTindakan.setFlagUpdateKlaim("Y");
                                riwayatTindakan.setCreatedWho(bean.getCreatedWho());
                                riwayatTindakan.setCreatedDate(bean.getCreatedDate());
                                riwayatTindakan.setLastUpdate(bean.getLastUpdate());
                                riwayatTindakan.setLastUpdateWho(bean.getLastUpdateWho());
                                riwayatTindakan.setFlag("Y");
                                riwayatTindakan.setAction("C");
                                riwayatTindakan.setTanggalTindakan(bean.getCreatedDate());

                                try {
                                    riwayatTindakanDao.addAndSave(riwayatTindakan);
                                    response.setStatus("success");
                                    response.setMessage("Berhasil");
                                } catch (HibernateException e) {
                                    response.setStatus("error");
                                    response.setMessage("Error when saving tindakan rawat " + e.getMessage());
                                    logger.error("[CheckupBoImpl FOunf error]" + e.getMessage());
                                }

                            }

                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMessage("Error when saving tindakan rawat " + e.getMessage());
                            logger.error("[CheckupBoImpl.saveAdd] Error When Saving tindakan rawat" + e.getMessage());
                            throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving tindakan rawat" + e.getMessage());
                        }

                    }
                }
            }
        }

        if ("umum".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
            // save uang muka
            ItSimrsUangMukaPendaftaranEntity uangMukaPendaftaranEntity = new ItSimrsUangMukaPendaftaranEntity();
            if (bean.getBranchId() != null && !bean.getBranchId().equalsIgnoreCase("")) {
                uangMukaPendaftaranEntity.setId("UM" + bean.getBranchId() + dateFormater("MM") + dateFormater("yy") + uangMukaDao.getNextId());
            } else
                uangMukaPendaftaranEntity.setId("UM" + CommonUtil.userBranchLogin() + dateFormater("MM") + dateFormater("yy") + uangMukaDao.getNextId());
            uangMukaPendaftaranEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
            uangMukaPendaftaranEntity.setFlag("Y");
            uangMukaPendaftaranEntity.setAction("C");
            uangMukaPendaftaranEntity.setCreatedDate(bean.getCreatedDate());
            uangMukaPendaftaranEntity.setCreatedWho(bean.getCreatedWho());
            uangMukaPendaftaranEntity.setLastUpdate(bean.getCreatedDate());
            uangMukaPendaftaranEntity.setLastUpdateWho(bean.getCreatedWho());

            if (bean.getInvoice() != null) {
                uangMukaPendaftaranEntity.setNoNota(bean.getInvoice());
                uangMukaPendaftaranEntity.setStatusBayar("Y");
            }

            if ("".equalsIgnoreCase(bean.getJumlahUangMuka().toString()) || bean.getJumlahUangMuka() == null || bean.getJumlahUangMuka().compareTo(new BigInteger(String.valueOf(0))) == 0) {
                uangMukaPendaftaranEntity.setJumlah(new BigInteger(String.valueOf(0)));
            } else {
                uangMukaPendaftaranEntity.setJumlah(bean.getJumlahUangMuka());
            }

            try {
                uangMukaDao.addAndSave(uangMukaPendaftaranEntity);
                response.setStatus("success");
                response.setMessage("Berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Error When Saving uang muka " + e.getMessage());
                logger.error("[CheckupBoImpl.saveAdd] Error When Saving" + e.getMessage());
                throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving" + e.getMessage());
            }
        }

        if("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){

            if (bean.getTindakanList() != null && bean.getTindakanList().size() > 0) {
                for (Tindakan tindakan : bean.getTindakanList()) {
                    List<ImSimrsTindakanEntity> tindakanEntities = getListEntityTindakan(tindakan);
                    if (tindakanEntities.size() > 0) {

                        ImSimrsTindakanEntity tindakanEntity = tindakanEntities.get(0);
                        ItSimrsTindakanRawatEntity tindakanRawatEntity = new ItSimrsTindakanRawatEntity();
                        tindakanRawatEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                        tindakanRawatEntity.setIdTindakanRawat("TDR" + getNextTindakanRawatId());
                        tindakanRawatEntity.setIdTindakan(tindakanEntity.getIdTindakan());
                        tindakanRawatEntity.setNamaTindakan(tindakanEntity.getTindakan());
                        tindakanRawatEntity.setIdDokter(idDokter);
                        tindakanRawatEntity.setCreatedDate(bean.getCreatedDate());
                        tindakanRawatEntity.setCreatedWho(bean.getCreatedWho());
                        tindakanRawatEntity.setLastUpdate(bean.getCreatedDate());
                        tindakanRawatEntity.setLastUpdateWho(bean.getCreatedWho());
                        tindakanRawatEntity.setFlag("Y");
                        tindakanRawatEntity.setAction("U");

                        if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                            tindakanRawatEntity.setTarif(tindakanEntity.getTarifBpjs());
                        } else {
                            tindakanRawatEntity.setTarif(tindakanEntity.getTarif());
                        }

                        tindakanRawatEntity.setQty(new BigInteger(String.valueOf(1)));
                        tindakanRawatEntity.setTarifTotal(tindakanRawatEntity.getTarif().multiply(tindakanRawatEntity.getQty()));

                        try {

                            tindakanRawatDao.addAndSave(tindakanRawatEntity);

                            if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {

                                ItSimrsRiwayatTindakanEntity riwayatTindakan = new ItSimrsRiwayatTindakanEntity();
                                riwayatTindakan.setIdRiwayatTindakan("RWT" + getNextIdRiwayatTindakan());
                                riwayatTindakan.setIdTindakan(tindakanRawatEntity.getIdTindakanRawat());
                                riwayatTindakan.setNamaTindakan(tindakanRawatEntity.getNamaTindakan());
                                riwayatTindakan.setTotalTarif(new BigDecimal(String.valueOf(tindakanRawatEntity.getTarifTotal())));
                                riwayatTindakan.setApproveBpjsFlag("Y");
                                riwayatTindakan.setKategoriTindakanBpjs("konsultasi");
                                riwayatTindakan.setKeterangan("tindakan");
                                riwayatTindakan.setJenisPasien(bean.getIdJenisPeriksaPasien());
                                riwayatTindakan.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                                riwayatTindakan.setFlagUpdateKlaim("Y");
                                riwayatTindakan.setCreatedWho(bean.getCreatedWho());
                                riwayatTindakan.setCreatedDate(bean.getCreatedDate());
                                riwayatTindakan.setLastUpdate(bean.getLastUpdate());
                                riwayatTindakan.setLastUpdateWho(bean.getLastUpdateWho());
                                riwayatTindakan.setFlag("Y");
                                riwayatTindakan.setAction("C");
                                riwayatTindakan.setTanggalTindakan(bean.getCreatedDate());

                                try {
                                    riwayatTindakanDao.addAndSave(riwayatTindakan);
                                    response.setStatus("success");
                                    response.setMessage("Berhasil");
                                } catch (HibernateException e) {
                                    response.setStatus("error");
                                    response.setMessage("Error when saving tindakan rawat " + e.getMessage());
                                    logger.error("[CheckupBoImpl FOunf error]" + e.getMessage());
                                }

                            }

                        } catch (HibernateException e) {
                            response.setStatus("error");
                            response.setMessage("Error when saving tindakan rawat " + e.getMessage());
                            logger.error("[CheckupBoImpl.saveAdd] Error When Saving tindakan rawat" + e.getMessage());
                            throw new GeneralBOException("[CheckupBoImpl.saveAdd] Error When Saving tindakan rawat" + e.getMessage());
                        }

                    }
                }
            }

            if (bean.getDiagnosa() != null && !"".equalsIgnoreCase(bean.getDiagnosa()) && detailCheckupEntity.getIdDetailCheckup() != null && !"".equalsIgnoreCase(detailCheckupEntity.getIdDetailCheckup())) {
                DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
                diagnosaRawat.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                diagnosaRawat.setIdDiagnosa(bean.getDiagnosa());
                diagnosaRawat.setKeteranganDiagnosa(bean.getNamaDiagnosa());
                diagnosaRawat.setJenisDiagnosa("0");
                response = saveDiagnosa(diagnosaRawat);
            }

        }else{

            List<ItSimrsDiagnosaRawatEntity> diagnosaList = new ArrayList<>();
            Map hsCriteria = new HashMap();
            hsCriteria.put("id_detail_checkup", bean.getIdDetailCheckup());

            try {
                diagnosaList = diagnosaRawatDao.getByCriteria(hsCriteria);
            }catch (HibernateException e){
                logger.error("Found Error "+e.getMessage());
            }

            if(diagnosaList.size() > 0){
                for (ItSimrsDiagnosaRawatEntity entity: diagnosaList){
                    if("0".equalsIgnoreCase(entity.getJenisDiagnosa())){
                        DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
                        diagnosaRawat.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                        diagnosaRawat.setIdDiagnosa(entity.getIdDiagnosa());
                        diagnosaRawat.setKeteranganDiagnosa(entity.getKeteranganDiagnosa());
                        diagnosaRawat.setJenisDiagnosa("0");
                        response = saveDiagnosa(diagnosaRawat);
                        break;
                    }
                }
            }
        }

        logger.info("[CheckupDetailBoImpl.saveEdit] End <<<<<<<<");
        return response;
    }

    private CheckResponse saveTeamDokter(DokterTeam bean) {
        logger.info("[CheckupDetailBoImpl.saveTeamDokter] Start >>>>>>>>");
        CheckResponse response = new CheckResponse();

        ItSimrsDokterTeamEntity entity = new ItSimrsDokterTeamEntity();
        String id = getNextTeamDokterId();

        entity.setIdTeamDokter("TDT" + id);
        entity.setIdDokter(bean.getIdDokter());
        entity.setIdDetailCheckup(bean.getIdDetailCheckup());
        entity.setFlag("Y");
        entity.setAction("C");
        entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        entity.setCreatedWho(bean.getCreatedWho());
        entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        entity.setLastUpdateWho(bean.getLastUpdateWho());

        try {
            dokterTeamDao.addAndSave(entity);
            response.setStatus("success");
            response.setMessage("Berhasil");
        } catch (HibernateException e) {
            response.setStatus("error");
            response.setMessage("Error when save add dokter team " + e.getMessage());
            logger.error("[CheckupDetailBoImpl.saveTeamDokter] Error when save add dokter team ", e);
            throw new GeneralBOException("[CheckupDetailBoImpl.saveTeamDokter] Error when save add dokter team " + e.getMessage());
        }

        logger.info("[CheckupDetailBoImpl.savaAdd] End <<<<<<<<");
        return response;
    }

    public CheckResponse saveDiagnosa(DiagnosaRawat bean) throws GeneralBOException {
        logger.info("[DiagnosaRawatBoImpl.saveAdd] Start >>>>>>>>>");
        CheckResponse response = new CheckResponse();

        if (bean != null && bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            ItSimrsDiagnosaRawatEntity entity = new ItSimrsDiagnosaRawatEntity();

            String id = getNextIdDiagnosa();
            entity.setIdDiagnosaRawat("DGR" + id);
            entity.setIdDiagnosa(bean.getIdDiagnosa());
            entity.setIdDetailCheckup(bean.getIdDetailCheckup());
            entity.setKeteranganDiagnosa(bean.getKeteranganDiagnosa());
            entity.setJenisDiagnosa(bean.getJenisDiagnosa());
            entity.setTipe(bean.getTipe());
            entity.setFlag("Y");
            entity.setAction("U");
            entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            entity.setCreatedWho(CommonUtil.userLogin());
            entity.setLastUpdateWho(CommonUtil.userLogin());

            try {
                diagnosaRawatDao.addAndSave(entity);
                response.setStatus("success");
                response.setMessage("Bewrhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage("Error when saveing diagnosa " + e.getMessage());
                logger.error("[DiagnosaRawatBoImpl.saveAdd] Error when saving diagnosa ", e);
                throw new GeneralBOException("Error when saving diagnosa " + e.getMessage());
            }
        }

        logger.info("[DiagnosaRawatBoImpl.saveAdd] End <<<<<<<<<");
        return response;
    }

    private CheckResponse saveRawatInap(RawatInap bean) {
        logger.info("[CheckupDetailBoImpl.saveRawatInap] Start >>>>>>>>");
        CheckResponse response = new CheckResponse();

        if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup()) &&
                bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            ItSimrsRawatInapEntity entity = new ItSimrsRawatInapEntity();
            MtSimrsRuanganEntity ruanganEntity = new MtSimrsRuanganEntity();

            // get data ruangan;
            if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())) {
                Ruangan ruangan = new Ruangan();
                ruangan.setIdRuangan(bean.getIdRuangan());

                List<MtSimrsRuanganEntity> listRuangan = getListEntityRuangan(ruangan);

                if (!listRuangan.isEmpty()) {
                    ruanganEntity = listRuangan.get(0);

                    if (ruanganEntity != null) {

                        // set to entity table rawat inap for ruangan data
                        entity.setIdRuangan(ruanganEntity.getIdRuangan());
                        entity.setTarif(ruanganEntity.getTarif());
                        entity.setNamaRangan(ruanganEntity.getNamaRuangan());
                        entity.setNoRuangan(ruanganEntity.getNoRuangan());
                    }
                }
            }

            // set to entity table rawat inap
            String id = getNextRawatInapId();
            entity.setIdRawatInap("RNP" + id);
            entity.setIdDetailCheckup(bean.getIdDetailCheckup());
            entity.setNoCheckup(bean.getNoCheckup());
            entity.setFlag("Y");
            entity.setAction("C");
            entity.setCreatedDate(bean.getCreatedDate());
            entity.setLastUpdate(bean.getCreatedDate());
            entity.setCreatedWho(bean.getCreatedWho());
            entity.setLastUpdateWho(bean.getCreatedWho());
            entity.setTglMasuk(bean.getCreatedDate());

            try {
                rawatInapDao.addAndSave(entity);
                response.setStatus("success");
                response.setMessage("berhasil");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMessage(" Error when save add Rawat Inap " + e.getMessage());
                logger.error("[CheckupDetailBoImpl.saveRawatInap] Error when save add Rawat Inap ", e);
                throw new GeneralBOException("[CheckupDetailBoImpl.saveRawatInap] Error when save add  Rawat Inap " + e.getMessage());
            }

            // set ketersedian ruangan to tidak tersedia
            if (ruanganEntity != null) {

                ruanganEntity.setSisaKuota(ruanganEntity.getSisaKuota() - 1);
                ruanganEntity.setAction("U");
                ruanganEntity.setLastUpdate(bean.getCreatedDate());
                ruanganEntity.setLastUpdateWho(bean.getCreatedWho());

                if (ruanganEntity.getSisaKuota() == 0) {
                    ruanganEntity.setStatusRuangan("N");
                }

                try {
                    ruanganDao.updateAndSave(ruanganEntity);
                    response.setStatus("success");
                    response.setMessage("berhasil");
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMessage("Error When Saving data detail checkup " + e.getMessage());
                    logger.error("[CheckupDetailBoImpl.saveRawatInap] Error when Update data ruangan ", e);
                    throw new GeneralBOException("[CheckupDetailBoImpl.saveRawatInap] Error when Update data ruangan " + e.getMessage());
                }
            }
        }

        logger.info("[CheckupDetailBoImpl.saveRawatInap] End <<<<<<<<");
        return response;
    }

    private List<ImSimrsTindakanEntity> getListEntityTindakan(Tindakan bean) throws GeneralBOException {
        logger.info("[CheckupBoImpl.getListEntityTindakan] Start >>>>>>>");

        List<ImSimrsTindakanEntity> tindakanEntities = new ArrayList<>();
        if (bean != null) {
            Map hsCriteria = new HashMap();
            if (bean.getIdTindakan() != null) {
                hsCriteria.put("id_tindakan", bean.getIdTindakan());
            }
            if (bean.getIdKategoriTindakan() != null) {
                hsCriteria.put("id_kategori_tindakan", bean.getIdKategoriTindakan());
            }
            if (bean.getFlag() != null) {
                hsCriteria.put("flag", bean.getFlag());
            }
            try {
                tindakanEntities = tindakanDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[CheckupBoImpl.getListEntityTindakan] ERROR " + e.getMessage());
                throw new GeneralBOException("[CheckupBoImpl.getListEntityTindakan] ERROR " + e.getMessage());
            }

        }
        logger.info("[CheckupBoImpl.getListEntityTindakan] End <<<<<<<");
        return tindakanEntities;

    }

    protected List<MtSimrsRuanganEntity> getListEntityRuangan(Ruangan bean) {
        logger.info("[CheckupModuls.getListRuangan] Start >>>>>>>>>");

        List<MtSimrsRuanganEntity> results = new ArrayList<>();
        Map hsCriteria = new HashMap();

        if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())) {
            hsCriteria.put("id_ruangan", bean.getIdRuangan());
        }
        if (bean.getStatusRuangan() != null && !"".equalsIgnoreCase(bean.getStatusRuangan())) {
            hsCriteria.put("status_ruangan", bean.getStatusRuangan());

        }
        hsCriteria.put("flag", "Y");

        try {
            results = ruanganDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[CheckupModuls.getListRuangan] Error When get data ruangan");
        }

        logger.info("[CheckupModuls.getListRuangan] End <<<<<<<<<");
        return results;
    }

    private String dateFormater(String type) {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat df = new SimpleDateFormat(type);
        return df.format(date);
    }

    private String getNextIdDiagnosa() {
        String id = "";
        try {
            id = diagnosaRawatDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[DiagnosaRawatBoImpl.getNextId] Error when get next diagnosa rawat id ", e);
        }
        return id;
    }

    private String getNextDetailCheckupId() {
        String id = "";
        try {
            id = checkupDetailDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[CheckupDetailBoImpl.getNextDetailCheckupId] Error get next seq id " + e.getMessage());
            throw new GeneralBOException("[CheckupDetailBoImpl.getNextDetailCheckupId] Error When Error get next seq id");
        }
        return id;
    }

    private String getNextTindakanRawatId() {
        String id = "";
        try {
            id = tindakanRawatDao.getNextTindakanRawatId();
        } catch (HibernateException e) {
            logger.error("[CheckupBoImpl.getNextTindakanRawatId] Error when get seq ", e);
            throw new GeneralBOException("[CheckupBoImpl.getNextTindakanRawatId] Error when get seq " + e.getMessage());
        }
        return id;
    }

    private String getNextIdRiwayatTindakan() {
        String id = "";
        try {
            id = riwayatTindakanDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[RiwayatTindakanBoImpl.getNextIdRiwayatTindakan] ERROR When create sequences", e);
        }
        return id;
    }

    private String getNextTeamDokterId() {
        String id = "";
        try {
            id = dokterTeamDao.getNextSeq();
        } catch (HibernateException e) {
            logger.error("[CheckupDetailBoImpl.getNextTeamDokterId] Error get next seq id " + e.getMessage());
            throw new GeneralBOException("[CheckupDetailBoImpl.getNextTeamDokterId] Error When Error get next seq id");
        }
        return id;
    }

    private String getNextRawatInapId() {
        String id = "";
        try {
            id = rawatInapDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[CheckupDetailBoImpl.getNextRawatInapId] Error get next seq id " + e.getMessage());
            throw new GeneralBOException("[CheckupDetailBoImpl.getNextRawatInapId] Error When Error get next seq id");
        }
        return id;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
    }

    public void setUangMukaDao(UangMukaDao uangMukaDao) {
        this.uangMukaDao = uangMukaDao;
    }

    public void setDokterTeamDao(DokterTeamDao dokterTeamDao) {
        this.dokterTeamDao = dokterTeamDao;
    }

    public void setTindakanRawatDao(TindakanRawatDao tindakanRawatDao) {
        this.tindakanRawatDao = tindakanRawatDao;
    }

    public void setRawatInapDao(RawatInapDao rawatInapDao) {
        this.rawatInapDao = rawatInapDao;
    }

    public void setDiagnosaRawatDao(DiagnosaRawatDao diagnosaRawatDao) {
        this.diagnosaRawatDao = diagnosaRawatDao;
    }

    public void setRuanganDao(RuanganDao ruanganDao) {
        this.ruanganDao = ruanganDao;
    }

    public void setTindakanDao(TindakanDao tindakanDao) {
        this.tindakanDao = tindakanDao;
    }

    public void setHeaderCheckupDao(HeaderCheckupDao headerCheckupDao) {
        this.headerCheckupDao = headerCheckupDao;
    }
}
