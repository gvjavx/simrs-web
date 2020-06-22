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
import com.neurix.simrs.transaksi.permintaanresep.dao.PermintaanResepDao;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.reseponline.dao.ResepOnlineDao;
import com.neurix.simrs.transaksi.reseponline.model.ItSimrsResepOnlineEntity;
import com.neurix.simrs.transaksi.riwayattindakan.dao.RiwayatTindakanDao;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.teamdokter.dao.DokterTeamDao;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.neurix.simrs.transaksi.tindakanrawat.dao.TindakanRawatDao;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.transaksiobat.dao.ApprovalTransaksiObatDao;
import com.neurix.simrs.transaksi.transaksiobat.dao.TransaksiObatDetailDao;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsApprovalTransaksiObatEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
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
    private ApprovalTransaksiObatDao approvalTransaksiObatDao;
    private PermintaanResepDao permintaanResepDao;
    private TransaksiObatDetailDao transaksiObatDetailDao;
    private ResepOnlineDao resepOnlineDao;

    public void setResepOnlineDao(ResepOnlineDao resepOnlineDao) {
        this.resepOnlineDao = resepOnlineDao;
    }

    public void setTransaksiObatDetailDao(TransaksiObatDetailDao transaksiObatDetailDao) {
        this.transaksiObatDetailDao = transaksiObatDetailDao;
    }

    public void setApprovalTransaksiObatDao(ApprovalTransaksiObatDao approvalTransaksiObatDao) {
        this.approvalTransaksiObatDao = approvalTransaksiObatDao;
    }

    public void setPermintaanResepDao(PermintaanResepDao permintaanResepDao) {
        this.permintaanResepDao = permintaanResepDao;
    }

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
        if (bean.getKeterangan() != null) {
            hsCriteria.put("keterangan", bean.getKeterangan());
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
        hsCriteria.put("keterangan", keterangan);

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

        try {
            telemedicDao.updateAndSave(resultTelemedic.get(0));
        } catch (HibernateException e) {
            logger.error("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
        }

        logger.info("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] END <<<");
    }
    public String approveTransaksi(HeaderCheckup bean) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.approveTransaksi] START >>>");

        String idDetailCheckup = "";
        String id = "";
        String noCheckup = bean.getNoCheckup();
        if (bean != null) {

            boolean isAddCheckup = false;
            ItSimrsHeaderChekupEntity checkNoCheckup = headerCheckupDao.getById("noCheckup", bean.getNoCheckup());
            if (checkNoCheckup == null){
                isAddCheckup = true;
            }

            if (isAddCheckup){
                //id = getNextHeaderId();
                ItSimrsHeaderChekupEntity headerEntity = new ItSimrsHeaderChekupEntity();
                headerEntity.setNoCheckup(noCheckup);
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

            }

            // saving detail checkup
            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {

                // check jika tidak ada transaksi online maka insert;
                ItSimrsHeaderDetailCheckupEntity checkTransaksiOnline = checkupDetailDao.getById("idTransaksiOnline", bean.getIdTransaksiOnline());
                if (checkTransaksiOnline == null){

                    ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();

                    id = getNextDetailCheckupId();
                    detailCheckupEntity.setIdDetailCheckup("DCM" + id);
                    detailCheckupEntity.setNoCheckup(noCheckup);
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
                } else {

                    // set idDetailCheckup;
                    idDetailCheckup = checkTransaksiOnline.getIdDetailCheckup();
                }
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

    @Override
    public String approveTransaksiResep(HeaderCheckup bean, String idTransaksi) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.approveTransaksiResep] Start >>>>>>>>");

        String idPermintaanResep = "";
        String id = "";
        String noCheckup = bean.getNoCheckup();
        if (bean != null) {

            boolean isAddCheckup = false;
            ItSimrsHeaderChekupEntity checkNoCheckup = headerCheckupDao.getById("noCheckup", bean.getNoCheckup());
            if (checkNoCheckup == null){
                isAddCheckup = true;
            }

            if (isAddCheckup){
                //id = getNextHeaderId();
                ItSimrsHeaderChekupEntity headerEntity = new ItSimrsHeaderChekupEntity();
                headerEntity.setNoCheckup(noCheckup);
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
            }

            // saving detail checkup
            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {

                // check jika tidak ada transaksi online maka insert;
                ItSimrsHeaderDetailCheckupEntity checkTransaksiOnline = checkupDetailDao.getById("idTransaksiOnline", bean.getIdTransaksiOnline());
                if (checkTransaksiOnline == null){

                    ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = new ItSimrsHeaderDetailCheckupEntity();

                    id = getNextDetailCheckupId();
                    detailCheckupEntity.setIdDetailCheckup("DCM" + id);
                    detailCheckupEntity.setNoCheckup(noCheckup);
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


                    String pelayananTujuan = "";
                    List<TransaksiObatDetail> detailList = new ArrayList<>();
                    Map hsCriteria = new HashMap();
                    hsCriteria.put("id_transaksi_online", bean.getIdTransaksiOnline());
                    List<ItSimrsResepOnlineEntity> resepOnlineEntities = resepOnlineDao.getByCriteria(hsCriteria);
                    if (resepOnlineEntities.size() > 0){
                        for (ItSimrsResepOnlineEntity resepOnlineEntity : resepOnlineEntities){

                            pelayananTujuan = resepOnlineEntity.getIdPelayanan();

                            TransaksiObatDetail detail = new TransaksiObatDetail();
                            detail.setIdObat(resepOnlineEntity.getIdObat());
                            detail.setKeterangan(resepOnlineEntity.getKeterangan());
                            detail.setQty(resepOnlineEntity.getQty());
                            detail.setJenisSatuan("biji");
                            detailList.add(detail);
                        }
                    }

                    PermintaanResep permintaanResep = new PermintaanResep();
                    permintaanResep.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                    permintaanResep.setIdDokter(bean.getIdDokter());
                    permintaanResep.setIdPelayanan(bean.getIdPelayanan());
                    permintaanResep.setTujuanPelayanan(pelayananTujuan);
                    permintaanResep.setIdPasien(bean.getIdPasien());
                    permintaanResep.setCreatedWho(bean.getCreatedWho());
                    permintaanResep.setLastUpdate(bean.getCreatedDate());
                    permintaanResep.setCreatedDate(bean.getCreatedDate());
                    permintaanResep.setLastUpdateWho(bean.getCreatedWho());
                    permintaanResep.setAction("C");
                    permintaanResep.setFlag("Y");
                    permintaanResep.setBranchId(bean.getBranchId());
                    permintaanResep.setJenisResep(bean.getIdJenisPeriksaPasien());
                    permintaanResep.setIdTransaksiOnline(bean.getIdTransaksiOnline());
                    permintaanResep.setIdPasien(bean.getIdPasien());

                    // set idPermintaanResep;
                    idPermintaanResep = saveAddResep(permintaanResep, detailList);
                } else {

                    // set idPermintaanResep;
                    ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranDao.getById("id", checkTransaksiOnline.getIdTransaksiOnline());
                    if (pembayaranOnlineEntity != null){
                        idPermintaanResep = pembayaranOnlineEntity.getIdItem();
                    }
                }
            }
        }

        logger.info("[VerifikatorPembayaranBoImpl.approveTransaksiResep] End <<<<<<<<");
        return idPermintaanResep;
    }

    public String saveAddResep(PermintaanResep bean, List<TransaksiObatDetail> detailList) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.saveAddResep] START >>>>>>>");

        String id = getNextApprovalObatId();
        String idPermintaanResep = "";

        ImtSimrsApprovalTransaksiObatEntity approvalEntity = new ImtSimrsApprovalTransaksiObatEntity();
        approvalEntity.setIdApprovalObat("INV" + id);
        approvalEntity.setIdPelayanan(bean.getIdPelayanan());
        approvalEntity.setFlag("Y");
        approvalEntity.setAction("C");
        approvalEntity.setTipePermintaan("001");
        approvalEntity.setLastUpdate(bean.getCreatedDate());
        approvalEntity.setLastUpdateWho(bean.getCreatedWho());
        approvalEntity.setCreatedDate(bean.getCreatedDate());
        approvalEntity.setCreatedWho(bean.getCreatedWho());
        approvalEntity.setBranchId(bean.getBranchId());

        try {
            approvalTransaksiObatDao.addAndSave(approvalEntity);
        } catch (HibernateException e) {
            logger.error("[VerifikatorPembayaranBoImpl.saveAddResep] ERROR when insert into approval transaksi. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.saveAddResep] ERROR when insert into approval transaksi. ", e);
        }

        id = getNextPermintaanResepId();
        ImSimrsPermintaanResepEntity permintaanEntity = new ImSimrsPermintaanResepEntity();
        permintaanEntity.setIdPermintaanResep("RSP" + id);
        permintaanEntity.setIdDokter(bean.getIdDokter());
        permintaanEntity.setIdApprovalObat(approvalEntity.getIdApprovalObat());
        permintaanEntity.setIdPasien(bean.getIdPasien());
        permintaanEntity.setIdDetailCheckup(bean.getIdDetailCheckup());
        permintaanEntity.setFlag("Y");
        permintaanEntity.setAction("C");
        permintaanEntity.setCreatedDate(bean.getCreatedDate());
        permintaanEntity.setCreatedWho(bean.getCreatedWho());
        permintaanEntity.setLastUpdate(bean.getCreatedDate());
        permintaanEntity.setLastUpdateWho(bean.getCreatedWho());
        permintaanEntity.setBranchId(bean.getBranchId());
        permintaanEntity.setTujuanPelayanan(bean.getTujuanPelayanan());
        permintaanEntity.setTtdPasien(bean.getTtdPasien());
        permintaanEntity.setTtdDokter(bean.getTtdDokter());
        permintaanEntity.setStatus("0");
        permintaanEntity.setIsUmum("N");
        permintaanEntity.setTglAntrian(bean.getCreatedDate());
        permintaanEntity.setJenisResep(bean.getJenisResep());
        permintaanEntity.setIdTransaksiOnline(bean.getIdTransaksiOnline());

        try {
            permintaanResepDao.addAndSave(permintaanEntity);
        } catch (HibernateException e) {
            logger.error("[VerifikatorPembayaranBoImpl.saveAddResep]  ERROR when insert into permintaan resep. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.saveAddResep]  ERROR when insert into permintaan resep. ", e);
        }

        if (detailList.size() > 0) {

            for (TransaksiObatDetail detailObat : detailList) {
                TransaksiObatDetail detail = new TransaksiObatDetail();
                detail.setIdApprovalObat(approvalEntity.getIdApprovalObat());
                detail.setIdObat(detailObat.getIdObat());
                detail.setQty(detailObat.getQty());
                detail.setQtyApprove(detailObat.getQty());
                detail.setJenisSatuan(detailObat.getJenisSatuan());
                detail.setKeterangan(detailObat.getKeterangan());
                detail.setCreatedDate(bean.getCreatedDate());
                detail.setCreatedWho(bean.getCreatedWho());
                detail.setLastUpdate(bean.getCreatedDate());
                detail.setLastUpdateWho(bean.getCreatedWho());
                detail.setFlagRacik(detailObat.getFlagRacik());
                if (detailObat.getHariKronis() != null){
                    detail.setHariKronis(detailObat.getHariKronis());
                }
                saveObatResep(detail);
            }

            idPermintaanResep = permintaanEntity.getIdPermintaanResep();
        }
        logger.info("[VerifikatorPembayaranBoImpl.saveAddResep] END <<<<<<<");
        return idPermintaanResep;
    }

    public void saveObatResep(TransaksiObatDetail bean) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.saveObatResep] START >>>>>>>");

        ImtSimrsTransaksiObatDetailEntity obatDetailEntity = new ImtSimrsTransaksiObatDetailEntity();

        String id = getNextTransaksiObatDetail();
        obatDetailEntity.setIdTransaksiObatDetail("ODT" + id);
        obatDetailEntity.setIdApprovalObat(bean.getIdApprovalObat());
        obatDetailEntity.setIdObat(bean.getIdObat());
        obatDetailEntity.setQty(bean.getQty());
        obatDetailEntity.setQtyApprove(bean.getQtyApprove());
        obatDetailEntity.setJenisSatuan(bean.getJenisSatuan());
        obatDetailEntity.setFlag("Y");
        obatDetailEntity.setAction("C");
        obatDetailEntity.setCreatedDate(bean.getCreatedDate());
        obatDetailEntity.setCreatedWho(bean.getCreatedWho());
        obatDetailEntity.setLastUpdate(bean.getCreatedDate());
        obatDetailEntity.setLastUpdateWho(bean.getCreatedWho());
        obatDetailEntity.setKeterangan(bean.getKeterangan());
        obatDetailEntity.setFlagRacik(bean.getFlagRacik());
        if (bean.getHariKronis() != null && bean.getHariKronis().compareTo(0) == 1){
            obatDetailEntity.setHariKronis(bean.getHariKronis());
        }

        try {
            transaksiObatDetailDao.addAndSave(obatDetailEntity);
        } catch (HibernateException e) {
            logger.error("[VerifikatorPembayaranBoImpl.saveObatResep]  ERROR when insert into transaksi obat detail. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.saveObatResep]  ERROR when insert into transaksi obat detail. ", e);
        }
        logger.info("[VerifikatorPembayaranBoImpl.saveObatResep] END <<<<<<<");
    }

    private String getNextTransaksiObatDetail() throws GeneralBOException {
        String id = "";
        try {
            id = transaksiObatDetailDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[VerifikatorPembayaranBoImpl.getNextTransaksiObatDetail] ERROR when get next id transaksi obat detail. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.getNextTransaksiObatDetail] ERROR when get next id transaksi obat detail. ", e);
        }
        return id;
    }

    private String getNextPermintaanResepId() throws GeneralBOException {
        String id = "";
        try {
            id = permintaanResepDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[VerifikatorPembayaranBoImpl.getNextPermintaanResepId] ERROR when get next id resep. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.getNextPermintaanResepId] ERROR when get next id resep. ", e);
        }
        return id;
    }

    private String getNextApprovalObatId() throws GeneralBOException {
        String id = "";
        try {
            id = approvalTransaksiObatDao.getNextId();
        } catch (HibernateException e) {
            logger.error("[VerifikatorPembayaranBoImpl.getNextApprovalObatId] ERROR when get next id approval obat. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.getNextApprovalObatId] ERROR when get next id approval obat. ", e);
        }
        return id;
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
