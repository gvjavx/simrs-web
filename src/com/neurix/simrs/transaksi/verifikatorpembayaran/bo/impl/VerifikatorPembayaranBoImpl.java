package com.neurix.simrs.transaksi.verifikatorpembayaran.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.tindakan.dao.TindakanDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.antrianonline.model.ItSimrsAntianOnlineEntity;
import com.neurix.simrs.transaksi.antriantelemedic.dao.TelemedicDao;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.Asesmen;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsAsesmenEntity;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsUangMukaPendaftaranEntity;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.teamdokter.dao.DokterTeamDao;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.neurix.simrs.transaksi.tindakanrawat.dao.TindakanRawatDao;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.verifikatorpembayaran.action.VerifikatorPembayaranAction;
import com.neurix.simrs.transaksi.verifikatorpembayaran.bo.VerifikatorPembayaranBo;
import com.neurix.simrs.transaksi.verifikatorpembayaran.dao.VerifikatorPembayaranDao;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.PembayaranOnline;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.xml.soap.Detail;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by reza on 10/06/20.
 */
public class VerifikatorPembayaranBoImpl implements VerifikatorPembayaranBo {

    private static final transient Logger logger = Logger.getLogger(VerifikatorPembayaranBoImpl.class);
    private TelemedicDao telemedicDao;
    private VerifikatorPembayaranDao verifikatorPembayaranDao;
    private HeaderCheckupDao headerCheckupDao;
    private CheckupDetailDao detailDao;
    private TindakanDao tindakanDao;
    private RiwayatTindakanDao riwayatTindakanDao;
    private TindakanRawatDao tindakanRawatDao;
    private CheckupDetailDao checkupDetailDao;
    private DokterTeamDao dokterTeamDao;

    public void setDokterTeamDao(DokterTeamDao dokterTeamDao) {
        this.dokterTeamDao = dokterTeamDao;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setHeaderCheckupDao(HeaderCheckupDao headerCheckupDao) {
        this.headerCheckupDao = headerCheckupDao;
    }

    public void setDetailDao(CheckupDetailDao detailDao) {
        this.detailDao = detailDao;
    }

    public void setTindakanDao(TindakanDao tindakanDao) {
        this.tindakanDao = tindakanDao;
    }

    public void setRiwayatTindakanDao(RiwayatTindakanDao riwayatTindakanDao) {
        this.riwayatTindakanDao = riwayatTindakanDao;
    }

    public void setTindakanRawatDao(TindakanRawatDao tindakanRawatDao) {
        this.tindakanRawatDao = tindakanRawatDao;
    }

    public void setTelemedicDao(TelemedicDao telemedicDao) {
        this.telemedicDao = telemedicDao;
    }

    public void setVerifikatorPembayaranDao(VerifikatorPembayaranDao verifikatorPembayaranDao) {
        this.verifikatorPembayaranDao = verifikatorPembayaranDao;
    }

    @Override
    public List<PembayaranOnline> getSearchByCriteria(PembayaranOnline bean) throws GeneralBOException {

        List<PembayaranOnline> pembayaranOnlines = new ArrayList<>();
        List<ItSimrsPembayaranOnlineEntity> pembayaranOnlineEntities = getSearchEntityByCriteria(bean);
        if (pembayaranOnlineEntities.size() > 0){

            for (ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity : pembayaranOnlineEntities){
                PembayaranOnline pembayaranOnline = new PembayaranOnline();
                pembayaranOnline.setId(pembayaranOnlineEntity.getId());
                pembayaranOnline.setIdAntrianTelemedic(pembayaranOnlineEntity.getIdAntrianTelemedic());
                pembayaranOnline.setIdItem(pembayaranOnlineEntity.getIdItem());
                pembayaranOnline.setIdRiwayatTindakan(pembayaranOnlineEntity.getIdRiwayatTindakan());
                pembayaranOnline.setNominal(pembayaranOnlineEntity.getNominal() == null ? new BigDecimal(0) : pembayaranOnlineEntity.getNominal());
                pembayaranOnline.setKeterangan(pembayaranOnlineEntity.getKeterangan());
                pembayaranOnline.setApprovedFlag(pembayaranOnlineEntity.getApprovedFlag());
                pembayaranOnline.setApprovedWho(pembayaranOnlineEntity.getApprovedWho());
                pembayaranOnline.setKodeBank(pembayaranOnlineEntity.getKodeBank());
                pembayaranOnline.setCreatedDate(pembayaranOnlineEntity.getCreatedDate());
                pembayaranOnline.setCreatedWho(pembayaranOnlineEntity.getCreatedWho());
                pembayaranOnline.setLastUpdate(pembayaranOnlineEntity.getLastUpdate());
                pembayaranOnline.setLastUpdateWho(pembayaranOnlineEntity.getLastUpdateWho());
                pembayaranOnline.setFlag(pembayaranOnlineEntity.getFlag());
                pembayaranOnline.setAction(pembayaranOnlineEntity.getAction());
                pembayaranOnline.setUrlFotoBukti(pembayaranOnlineEntity.getUrlFotoBukti());

                // mencari apakah sudah di bayar melalui bank
                ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = telemedicDao.getById("id", bean.getIdAntrianTelemedic());
                if (antrianTelemedicEntity != null){
                    if ("konsultasi".equalsIgnoreCase(pembayaranOnlineEntity.getKeterangan())){
                        if ("Y".equalsIgnoreCase(antrianTelemedicEntity.getFlagBayarKonsultasi())){
                            pembayaranOnline.setFlagBayar("Y");
                        }
                    } else if ("resep".equalsIgnoreCase(pembayaranOnlineEntity.getKeterangan())){
                        if ("Y".equalsIgnoreCase(antrianTelemedicEntity.getFlagBayarResep())){
                            pembayaranOnline.setFlagBayar("Y");
                        }
                    }
                }


                pembayaranOnlines.add(pembayaranOnline);
            }
        }

        return pembayaranOnlines;
    }

    @Override
    public List<ItSimrsPembayaranOnlineEntity> getSearchEntityByCriteria(PembayaranOnline bean) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.getSearchEntityByCriteria] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getIdAntrianTelemedic() != null){
            hsCriteria.put("id_antrian_telemedic", bean.getIdAntrianTelemedic());
        }
        if (bean.getApprovedFlag() != null){
            hsCriteria.put("approve_flag", bean.getApprovedFlag());
        }
        if (bean.getFlag() != null){
            hsCriteria.put("flag", bean.getFlag());
        }

        List<ItSimrsPembayaranOnlineEntity> pembayaranOnlineEntities = new ArrayList<>();
        try {
            pembayaranOnlineEntities = verifikatorPembayaranDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[VerifikatorPembayaranBoImpl.getSearchEntityByCriteria] ERROR. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.getSearchEntityByCriteria] ERROR. ", e);
        }

        logger.info("[VerifikatorPembayaranBoImpl.getSearchEntityByCriteria] END <<<");
        return pembayaranOnlineEntities;
    }

    @Override
    public void updateBuktiTransfer(String idTele, String pathBukti, String keterangan) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] START >>>");
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_antrian_telemedic", idTele);

        List<ItSimrsPembayaranOnlineEntity> resultPembayaran = new ArrayList<>();

        try {
           resultPembayaran = verifikatorPembayaranDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
        }

        resultPembayaran.get(0).setUrlFotoBukti(pathBukti);

        try {
            verifikatorPembayaranDao.updateAndSave(resultPembayaran.get(0));
        } catch (HibernateException e){
            logger.error("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
        }

        Map hsCriteria2 = new HashMap();
        hsCriteria2.put("id", idTele);

        List<ItSimrsAntrianTelemedicEntity> resultTelemedic = new ArrayList<>();

        try {
            resultTelemedic = telemedicDao.getByCriteria(hsCriteria2);
        } catch (HibernateException e) {
            logger.error("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
        }

        if(keterangan.equalsIgnoreCase("konsultasi")) {
            resultTelemedic.get(0).setFlagBayarKonsultasi("Y");
        } else resultTelemedic.get(0).setFlagBayarResep("Y");

        logger.info("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] END <<<");
    }
    public String approveTransaksi(HeaderCheckup bean) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.approveTransaksi] START >>>");

        String idDetailCheckup = "";
        if (bean != null) {

            String id = "";
            //id = getNextHeaderId();
            ItSimrsHeaderChekupEntity headerEntity = new ItSimrsHeaderChekupEntity();
            headerEntity.setNoCheckup(bean.getNoCheckup());
            headerEntity.setIdPasien(bean.getIdPasien());
            headerEntity.setNama(bean.getNama());
            headerEntity.setJenisKelamin(bean.getJenisKelamin());
            headerEntity.setNoKtp(bean.getNoKtp());
            headerEntity.setTempatLahir(bean.getTempatLahir());
            headerEntity.setTglLahir(bean.getTglLahir());
            headerEntity.setDesaId(bean.getDesaId());
            headerEntity.setJalan(bean.getJalan());
            headerEntity.setSuku(bean.getSuku());
            headerEntity.setProfesi(bean.getProfesi() != null && !"".equalsIgnoreCase(bean.getProfesi()) ? bean.getProfesi() : null);
            headerEntity.setNoTelp(bean.getNoTelp() != null && !"".equalsIgnoreCase(bean.getNoTelp()) ? bean.getNoTelp() : null);
            headerEntity.setAgama(bean.getAgama());
            headerEntity.setUrlKtp(bean.getUrlKtp());
            headerEntity.setBranchId(bean.getBranchId());
            headerEntity.setFlag("Y");
            headerEntity.setAction("C");
            headerEntity.setCreatedDate(bean.getCreatedDate());
            headerEntity.setLastUpdate(bean.getLastUpdate());
            headerEntity.setCreatedWho(bean.getCreatedWho());
            headerEntity.setLastUpdateWho(bean.getLastUpdateWho());
            headerEntity.setJenisKunjungan(bean.getJenisKunjungan());
            headerEntity.setNamaPenanggung(bean.getNamaPenanggung() != null && !"".equalsIgnoreCase(bean.getNamaPenanggung()) ? bean.getNamaPenanggung() : null);
            headerEntity.setHubunganKeluarga(bean.getHubunganKeluarga() != null && !"".equalsIgnoreCase(bean.getHubunganKeluarga()) ? bean.getHubunganKeluarga() : null);
            headerEntity.setBerat(bean.getBerat());
            headerEntity.setTinggi(bean.getTinggi());
            headerEntity.setIdAntrianOnline(bean.getIdAntrianOnline());

            try {
                headerCheckupDao.addAndSave(headerEntity);
            } catch (HibernateException e) {
                logger.error("[VerifikatorPembayaranBoImpl.approveTransaksi] Error When Saving data header checkup" + e.getMessage());
                throw new GeneralBOException("[VerifikatorPembayaranBoImpl.approveTransaksi] Error When Saving data header checkup");
            }

            // saving detail checkup
            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
                ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();

                id = "";
                id = getNextDetailCheckupId();
                detailCheckupEntity.setIdDetailCheckup("DCM" + id);
                detailCheckupEntity.setNoCheckup(headerEntity.getNoCheckup());
                detailCheckupEntity.setIdPelayanan(bean.getIdPelayanan());
                detailCheckupEntity.setIdJenisPeriksaPasien(bean.getIdJenisPeriksaPasien());

                if ("asuransi".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "ptpn".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                    detailCheckupEntity.setMetodePembayaran("non_tunai");
                } else {
                    detailCheckupEntity.setMetodePembayaran(bean.getMetodePembayaran() != null && !"".equalsIgnoreCase(bean.getMetodePembayaran()) ? bean.getMetodePembayaran() : null);
                }

                detailCheckupEntity.setNoRujukan(bean.getNoRujukan() != null && !"".equalsIgnoreCase(bean.getNoRujukan()) ? bean.getNoRujukan() : null);
                detailCheckupEntity.setTglRujukan(bean.getTglRujukan() != null && !"".equalsIgnoreCase(bean.getTglRujukan()) ? Date.valueOf(bean.getTglRujukan()) : null);
                detailCheckupEntity.setUrlDocRujuk(bean.getUrlDocRujuk() != null && !"".equalsIgnoreCase(bean.getUrlDocRujuk()) ? bean.getUrlDocRujuk() : null);
                detailCheckupEntity.setIdPaket(bean.getIdPaket() != null && !"".equalsIgnoreCase(bean.getIdPaket()) ? bean.getIdPaket() : null);
                detailCheckupEntity.setIdAsuransi(bean.getIdAsuransi() != null && !"".equalsIgnoreCase(bean.getIdAsuransi()) ? bean.getIdAsuransi() : null);
                detailCheckupEntity.setCoverBiaya(bean.getCoverBiaya() != null && !"".equalsIgnoreCase(bean.getCoverBiaya().toString()) ? bean.getCoverBiaya() : null);
                detailCheckupEntity.setNoKartuAsuransi(bean.getNoKartuAsuransi() != null && !"".equalsIgnoreCase(bean.getNoKartuAsuransi()) ? bean.getNoKartuAsuransi() : null);
                detailCheckupEntity.setBranchId(bean.getBranchId());
                detailCheckupEntity.setFlag("Y");
                detailCheckupEntity.setAction("C");
                detailCheckupEntity.setCreatedDate(bean.getCreatedDate());
                detailCheckupEntity.setCreatedWho(bean.getCreatedWho());
                detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                detailCheckupEntity.setIdTransaksiOnline(bean.getIdTransaksiOnline());

                if ("paket_perusahaan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                    detailCheckupEntity.setStatusPeriksa("1");
                    detailCheckupEntity.setCoverBiaya(bean.getCoverBiaya() == null ? null : bean.getCoverBiaya());
                } else {
                    detailCheckupEntity.setStatusPeriksa(bean.getStatusPeriksa());
                }

                if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "ptpn".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                    detailCheckupEntity.setKodeCbg(bean.getKodeCbg());
                    detailCheckupEntity.setRujuk(bean.getRujuk() != null ? bean.getRujuk() : null);
                    detailCheckupEntity.setKetRujukan(bean.getKetPerujuk() != null ? bean.getKetPerujuk() : null);
                    detailCheckupEntity.setNoSep(bean.getNoSep());
                    detailCheckupEntity.setTarifBpjs(bean.getTarifBpjs());
                    detailCheckupEntity.setKelasPasien(bean.getKelasPasien());
                    detailCheckupEntity.setIdPelayananBpjs(bean.getIdPelayananBpjs());
                    detailCheckupEntity.setNoPpkRujukan(bean.getNoPpkRujukan() != null ? bean.getNoPpkRujukan() : null);
                }

                if (detailCheckupEntity.getNoCheckupOnline() != null && !"".equalsIgnoreCase(detailCheckupEntity.getNoCheckupOnline())) {
                    detailCheckupEntity.setNoCheckupOnline(bean.getNoCheckupOnline());
                }

                if ("Y".equalsIgnoreCase(bean.getIsOnline())) {
                    detailCheckupEntity.setTglAntrian(bean.getTglAntian());
                } else {
                    detailCheckupEntity.setTglAntrian(bean.getCreatedDate());
                }

                try {
                    checkupDetailDao.addAndSave(detailCheckupEntity);
                } catch (HibernateException e) {
                    logger.error("[VerifikatorPembayaranBoImpl.approveTransaksi] Error When Saving data detail checkup" + e.getMessage());
                    throw new GeneralBOException("[VerifikatorPembayaranBoImpl.approveTransaksi] Error When Saving data detail checkup");
                }

                // saving dokter
                if (bean.getIdDokter() != null && !"".equalsIgnoreCase(bean.getIdDokter()) &&
                        detailCheckupEntity.getIdDetailCheckup() != null && !"".equalsIgnoreCase(detailCheckupEntity.getIdDetailCheckup())) {
                    DokterTeam dokterTeam = new DokterTeam();
                    dokterTeam.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                    dokterTeam.setIdDokter(bean.getIdDokter());
                    saveTeamDokter(dokterTeam);
                }

                //saving diagnosa, form IGD
//                if (bean.getDiagnosa() != null && !"".equalsIgnoreCase(bean.getDiagnosa()) && detailCheckupEntity.getIdDetailCheckup() != null && !"".equalsIgnoreCase(detailCheckupEntity.getIdDetailCheckup())) {
//                    DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
//                    diagnosaRawat.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
//                    diagnosaRawat.setIdDiagnosa(bean.getDiagnosa());
//                    diagnosaRawat.setKeteranganDiagnosa(bean.getNamaDiagnosa());
//                    diagnosaRawat.setJenisDiagnosa("0");
//                    saveDiagnosa(diagnosaRawat);
//                }

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
                            tindakanRawatEntity.setIdDokter(bean.getIdDokter());
                            tindakanRawatEntity.setCreatedDate(bean.getCreatedDate());
                            tindakanRawatEntity.setCreatedWho(bean.getCreatedWho());
                            tindakanRawatEntity.setLastUpdate(bean.getCreatedDate());
                            tindakanRawatEntity.setLastUpdateWho(bean.getCreatedWho());
                            tindakanRawatEntity.setFlag("Y");
                            tindakanRawatEntity.setAction("U");
                            tindakanRawatEntity.setApproveFlag("Y");

                            if ("bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) || "ptpn".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                                tindakanRawatEntity.setTarif(tindakanEntity.getTarifBpjs());
                            } else {
                                tindakanRawatEntity.setTarif(tindakanEntity.getTarif());
                            }

                            tindakanRawatEntity.setQty(new BigInteger(String.valueOf(1)));
                            tindakanRawatEntity.setTarifTotal(tindakanRawatEntity.getTarif().multiply(tindakanRawatEntity.getQty()));

                            try {

                                tindakanRawatDao.addAndSave(tindakanRawatEntity);

                                String jenPasien = bean.getIdJenisPeriksaPasien();
                                ItSimrsRiwayatTindakanEntity riwayatTindakan = new ItSimrsRiwayatTindakanEntity();
                                riwayatTindakan.setIdRiwayatTindakan("RWT" + getNextIdRiwayatTindakan());
                                riwayatTindakan.setIdTindakan(tindakanRawatEntity.getIdTindakanRawat());
                                riwayatTindakan.setNamaTindakan(tindakanRawatEntity.getNamaTindakan());
                                riwayatTindakan.setTotalTarif(new BigDecimal(String.valueOf(tindakanRawatEntity.getTarifTotal())));
                                riwayatTindakan.setApproveBpjsFlag("Y");
                                riwayatTindakan.setKategoriTindakanBpjs(tindakan.getKategoriInaBpjs());
                                riwayatTindakan.setKeterangan("tindakan");
                                riwayatTindakan.setJenisPasien(jenPasien);
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
                                } catch (HibernateException e) {
                                    logger.error("[VerifikatorPembayaranBoImpl.approveTransaksi] ERROR. " + e.getMessage());
                                    throw new GeneralBOException("[VerifikatorPembayaranBoImpl.approveTransaksi] Error When Saving tindakan rawat" + e.getMessage());
                                }


                            } catch (HibernateException e) {
                                logger.error("[VerifikatorPembayaranBoImpl.approveTransaksi] Error When Saving tindakan rawat" + e.getMessage());
                                throw new GeneralBOException("[VerifikatorPembayaranBoImpl.approveTransaksi] Error When Saving tindakan rawat" + e.getMessage());
                            }
                        }
                    }
                }

                // set idDetailCheckup;
                idDetailCheckup = detailCheckupEntity.getIdDetailCheckup();
            }
        }
        logger.info("[VerifikatorPembayaranBoImpl.approveTransaksi] End <<<<<<<");
        return idDetailCheckup;
    }

    @Override
    public ItSimrsPembayaranOnlineEntity getPembayaranOnlineById(String id) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.getPembayaranOnlineById] START >>>>>>>");
        logger.info("[VerifikatorPembayaranBoImpl.getPembayaranOnlineById] END <<<<<<<");
        return verifikatorPembayaranDao.getById("id", id);
    }

    private List<ImSimrsTindakanEntity> getListEntityTindakan(Tindakan bean) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.getListEntityTindakan] Start >>>>>>>");

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
                logger.error("[VerifikatorPembayaranBoImpl.getListEntityTindakan] ERROR " + e.getMessage());
                throw new GeneralBOException("[VerifikatorPembayaranBoImpl.getListEntityTindakan] ERROR " + e.getMessage());
            }

        }
        logger.info("[VerifikatorPembayaranBoImpl.getListEntityTindakan] End <<<<<<<");
        return tindakanEntities;
    }

    private void saveTeamDokter(DokterTeam bean) {
        logger.info("[VerifikatorPembayaranBoImpl.savaAdd] Start >>>>>>>>");

        ItSimrsDokterTeamEntity entity = new ItSimrsDokterTeamEntity();
        String id = getNextTeamDokterId();

        entity.setIdTeamDokter("TDT" + id);
        entity.setIdDokter(bean.getIdDokter());
        entity.setIdDetailCheckup(bean.getIdDetailCheckup());
        entity.setFlag("Y");
        entity.setAction("C");
        entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        entity.setCreatedWho(CommonUtil.userLogin());
        entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        entity.setLastUpdateWho(CommonUtil.userLogin());

        try {
            dokterTeamDao.addAndSave(entity);
        } catch (HibernateException e) {
            logger.error("[VerifikatorPembayaranBoImpl.saveTeamDokter] Error when save add dokter team ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.saveTeamDokter] Error when save add dokter team " + e.getMessage());
        }

        logger.info("[VerifikatorPembayaranBoImpl.savaAdd] End <<<<<<<<");
    }

    @Override
    public ItSimrsHeaderChekupEntity getHeaderCheckupByIdAntrinTelemedic(String idAntrianOnline) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.getHeaderCheckupByIdAntrinTelemedic] End <<<<<<<<");
        return headerCheckupDao.getById("idAntrianOnline", idAntrianOnline);
    }

    @Override
    public void saveEdit(ItSimrsPembayaranOnlineEntity bean) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.saveEdit] Start >>>>>>>>");

        try {
            verifikatorPembayaranDao.updateAndSave(bean);
        } catch (GeneralBOException e){
            logger.error("[VerifikatorPembayaranBoImpl.saveEdit] Error when update ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.saveEdit] Error when update " + e.getMessage());
        }

        logger.info("[VerifikatorPembayaranBoImpl.saveEdit] End <<<<<<<<");

    }

    private String getNextDetailCheckupId(){
        logger.info("[VerifikatorPembayaranBoImpl.getNextDetailCheckupId] START >>>");
        return checkupDetailDao.getNextId();
    }

    private String getNextIdRiwayatTindakan(){
        logger.info("[VerifikatorPembayaranBoImpl.getNextIdRiwayatTindakan] START >>>");
        return riwayatTindakanDao.getNextSeq();
    }

    private String getNextTindakanRawatId(){
        logger.info("[VerifikatorPembayaranBoImpl.getNextTindakanRawatId] START >>>");
        return tindakanRawatDao.getNextTindakanRawatId();
    }

    private String getNextTeamDokterId(){
        logger.info("[VerifikatorPembayaranBoImpl.getNextTeamDokterId] START >>>");
        return dokterTeamDao.getNextSeq();
    }

}
