package com.neurix.simrs.transaksi.verifikatorpembayaran.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.diagnosa.dao.DiagnosaDao;
import com.neurix.simrs.master.jenisperiksapasien.dao.AsuransiDao;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import com.neurix.simrs.master.tindakan.dao.HeaderTindakanDao;
import com.neurix.simrs.master.tindakan.dao.TindakanDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsHeaderTindakanEntity;
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
import com.neurix.simrs.transaksi.diagnosarawat.dao.DiagnosaRawatDao;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import com.neurix.simrs.transaksi.diagnosarawat.model.ItSimrsDiagnosaRawatEntity;
import com.neurix.simrs.transaksi.permintaanresep.dao.PermintaanResepDao;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.reseponline.dao.ResepOnlineDao;
import com.neurix.simrs.transaksi.reseponline.model.ItSimrsResepOnlineEntity;
import com.neurix.simrs.transaksi.riwayatbarang.dao.TransaksiStokDao;
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
import com.neurix.simrs.transaksi.verifikatorasuransi.dao.StrukAsuransiDao;
import com.neurix.simrs.transaksi.verifikatorasuransi.model.ItSimrsStrukAsuransiEntity;
import com.neurix.simrs.transaksi.verifikatorpembayaran.action.VerifikatorPembayaranAction;
import com.neurix.simrs.transaksi.verifikatorpembayaran.bo.VerifikatorPembayaranBo;
import com.neurix.simrs.transaksi.verifikatorpembayaran.dao.VerifikatorPembayaranDao;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.PembayaranOnline;
import io.agora.recording.common.Common;
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
    private AsuransiDao asuransiDao;
    private DiagnosaRawatDao diagnosaRawatDao;
    private TransaksiStokDao transaksiStokDao;
    private StrukAsuransiDao strukAsuransiDao;
    private HeaderTindakanDao headerTindakanDao;

    public void setHeaderTindakanDao(HeaderTindakanDao headerTindakanDao) {
        this.headerTindakanDao = headerTindakanDao;
    }

    public void setStrukAsuransiDao(StrukAsuransiDao strukAsuransiDao) {
        this.strukAsuransiDao = strukAsuransiDao;
    }

    public void setTransaksiStokDao(TransaksiStokDao transaksiStokDao) {
        this.transaksiStokDao = transaksiStokDao;
    }

    public void setDiagnosaRawatDao(DiagnosaRawatDao diagnosaRawatDao) {
        this.diagnosaRawatDao = diagnosaRawatDao;
    }

    public void setAsuransiDao(AsuransiDao asuransiDao) {
        this.asuransiDao = asuransiDao;
    }

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
        logger.info("[VerifikatorPembayaranBoImpl.getSearchByCriteria] START >>>");

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
                pembayaranOnline.setIdRekening(pembayaranOnlineEntity.getIdRekening());
                pembayaranOnline.setFlagUploadUlang(pembayaranOnlineEntity.getFlagUploadUlang());
                pembayaranOnline.setWaktuBayar(pembayaranOnlineEntity.getWaktuBayar());

                // mencari data pada antrian telemedic untuk mengetahui status pembayaran
                ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = telemedicDao.getById("id", bean.getIdAntrianTelemedic());
                if (antrianTelemedicEntity != null){

                    // jika asuransi maka mencari data authorization dan confirmation
                    // jika konsultasi dan
                    if ("asuransi".equalsIgnoreCase(antrianTelemedicEntity.getIdJenisPeriksaPasien())){

                        ItSimrsStrukAsuransiEntity simrsStrukAsuransiEntity = getStrukAsuransiDataByIdAntrianAndJenis(pembayaranOnlineEntity.getIdAntrianTelemedic(), "authorization");
                        if (simrsStrukAsuransiEntity != null)
                            if (simrsStrukAsuransiEntity.getApproveFlag() != null && simrsStrukAsuransiEntity.getUrlFotoStruk() != null)
                                pembayaranOnline.setFlagBayar("Y");

                        pembayaranOnline.setNoKartu(antrianTelemedicEntity.getNoKartu());
                        pembayaranOnline.setJumlahCover(antrianTelemedicEntity.getJumlahCover());
                        if (antrianTelemedicEntity.getIdAsuransi() != null && !"".equalsIgnoreCase(antrianTelemedicEntity.getIdAsuransi())){
                            ImSimrsAsuransiEntity asuransiEntity = asuransiDao.getById("idAsuransi", antrianTelemedicEntity.getIdAsuransi());
                            if (asuransiEntity != null){
                                pembayaranOnline.setNamaAsuransi(asuransiEntity.getNamaAsuransi());
                            }
                        }
                    } else {

                        // mencari apakah sudah di bayar melalui bank selain asuransi
                        pembayaranOnline.setNoSep(antrianTelemedicEntity.getNoSep());
                        pembayaranOnline.setFlagEresep(antrianTelemedicEntity.getFlagEresep());
                        if ("konsultasi".equalsIgnoreCase(pembayaranOnlineEntity.getKeterangan())){
                            if ("Y".equalsIgnoreCase(antrianTelemedicEntity.getFlagBayarKonsultasi())){
                                pembayaranOnline.setFlagBayar("Y");
                                pembayaranOnline.setUrutan(2);
                            } else {
                                pembayaranOnline.setUrutan(1);
                            }
                        } else if ("resep".equalsIgnoreCase(pembayaranOnlineEntity.getKeterangan())){
                            if ("Y".equalsIgnoreCase(antrianTelemedicEntity.getFlagBayarResep())){
                                pembayaranOnline.setFlagBayar("Y");
                                pembayaranOnline.setUrutan(2);
                            } else {
                                pembayaranOnline.setUrutan(1);
                            }
                        }
                        pembayaranOnline.setNoKartu(antrianTelemedicEntity.getNoKartu());
                        pembayaranOnline.setJumlahCover(antrianTelemedicEntity.getJumlahCover());
                        if (antrianTelemedicEntity.getIdAsuransi() != null && !"".equalsIgnoreCase(antrianTelemedicEntity.getIdAsuransi())){
                            ImSimrsAsuransiEntity asuransiEntity = asuransiDao.getById("idAsuransi", antrianTelemedicEntity.getIdAsuransi());
                            if (asuransiEntity != null){
                                pembayaranOnline.setNamaAsuransi(asuransiEntity.getNamaAsuransi());
                            }
                        }
                    }
                }


                pembayaranOnlines.add(pembayaranOnline);
            }
        }

        // sorting collection berdasrjan urutan pembayaran belum bayar dan tanggal upload Sorting
//        Collections.sort(pembayaranOnlines, PembayaranOnline.getUrutanPembayaranSorting());
//        Collections.sort(pembayaranOnlines, PembayaranOnline.getTanggalUploadSorting());

        logger.info("[VerifikatorPembayaranBoImpl.getSearchByCriteria] END <<<");
        return pembayaranOnlines;
    }

    private ItSimrsStrukAsuransiEntity getStrukAsuransiDataByIdAntrianAndJenis(String idAntrianTelemedic, String jenis) throws GeneralBOException{

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_antrian_telemedic", idAntrianTelemedic);
        hsCriteria.put("jenis", jenis);

        List<ItSimrsStrukAsuransiEntity> strukAsuransiEntities = new ArrayList<>();

        try {
            strukAsuransiEntities = strukAsuransiDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[VerifikatorPembayaranBoImpl.getStrukAsuransiDataByIdAntrianAndJenis] ERROR. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.getStrukAsuransiDataByIdAntrianAndJenis] ERROR. ", e);
        }

        if (strukAsuransiEntities.size() > 0)
            return  strukAsuransiEntities.get(0);
        return null;
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
        hsCriteria.put("keterangan", keterangan.equalsIgnoreCase("all") ? null : keterangan);

        List<ItSimrsPembayaranOnlineEntity> resultPembayaran = new ArrayList<>();

        try {
           resultPembayaran = verifikatorPembayaranDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
        }

        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        for (ItSimrsPembayaranOnlineEntity item : resultPembayaran) {
            item.setUrlFotoBukti(pathBukti);
            item.setLastUpdate(nowTime);
            item.setTanggalUpload(nowTime);

            try {
                verifikatorPembayaranDao.updateAndSave(item);
            } catch (HibernateException e){
                logger.error("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
                throw new GeneralBOException("[VerifikatorPembayaranBoImpl.updateBuktiTransfer] ERROR. ", e);
            }
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
        }
        if (keterangan.equalsIgnoreCase("resep")) {
            resultTelemedic.get(0).setFlagBayarResep("Y");
        }

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
                headerEntity.setTglKeluar(bean.getTglKeluar());

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
                        dokterTeam.setCreatedWho(bean.getCreatedWho());
                        saveTeamDokter(dokterTeam);
                    }

                    if (bean.getDiagnosa() != null && !"".equalsIgnoreCase(bean.getDiagnosa())){
                        if (bean.getDiagnosa() != null && !"".equalsIgnoreCase(bean.getDiagnosa()) && detailCheckupEntity.getIdDetailCheckup() != null && !"".equalsIgnoreCase(detailCheckupEntity.getIdDetailCheckup())) {
                            DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
                            diagnosaRawat.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                            diagnosaRawat.setIdDiagnosa(bean.getDiagnosa());
                            diagnosaRawat.setKeteranganDiagnosa(bean.getNamaDiagnosa());
                            diagnosaRawat.setJenisDiagnosa("0");
                            diagnosaRawat.setCreatedWho(bean.getCreatedWho());
                            saveDiagnosa(diagnosaRawat);
                        }
                    }

                    if (bean.getTindakanList() != null && bean.getTindakanList().size() > 0) {
                        for (Tindakan tindakan : bean.getTindakanList()) {
                            List<ImSimrsTindakanEntity> tindakanEntities = getListEntityTindakan(tindakan);
                            if (tindakanEntities.size() > 0) {

                                ImSimrsTindakanEntity tindakanEntity = tindakanEntities.get(0);

                                String namaTindakan = "";
                                if (tindakanEntity.getIdTindakan() != null){
                                    // mencari berdasarkan idHeaderTindakan yang diperoleh dari tindakanEnity. untuk mencari nama tindakan
                                    ImSimrsHeaderTindakanEntity headerTindakanEntity = headerTindakanDao.getById("idHeaderTindakan", tindakanEntity.getIdHeaderTindakan());
                                    if (headerTindakanEntity != null){
                                        namaTindakan = headerTindakanEntity.getNamaTindakan();
                                    }
                                }

                                ItSimrsTindakanRawatEntity tindakanRawatEntity = new ItSimrsTindakanRawatEntity();
                                tindakanRawatEntity.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                                tindakanRawatEntity.setIdTindakanRawat("TDR" + getNextTindakanRawatId());
                                tindakanRawatEntity.setIdTindakan(tindakanEntity.getIdTindakan());
                                tindakanRawatEntity.setNamaTindakan(namaTindakan);
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

    public void saveDiagnosa(DiagnosaRawat bean) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.saveDiagnosa] Start >>>>>>>>>");

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
            entity.setCreatedWho(!bean.getCreatedWho().equalsIgnoreCase("") ? bean.getCreatedWho() : CommonUtil.userLogin());
            entity.setLastUpdateWho(!bean.getCreatedWho().equalsIgnoreCase("") ? bean.getCreatedWho() : CommonUtil.userLogin());

            try {
                diagnosaRawatDao.addAndSave(entity);
            } catch (HibernateException e) {
                logger.error("[DiagnosaRawatBoImpl.saveAdd] Error when saving diagnosa ", e);
                throw new GeneralBOException("Error when saving diagnosa " + e.getMessage());
            }
        }

        logger.info("[VerifikatorPembayaranBoImpl.saveDiagnosa] End <<<<<<<<<");
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
        entity.setCreatedWho(!bean.getCreatedWho().equalsIgnoreCase("") ? bean.getCreatedWho() : CommonUtil.userLogin());
        entity.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        entity.setLastUpdateWho(!bean.getCreatedWho().equalsIgnoreCase("") ? bean.getCreatedWho() : CommonUtil.userLogin());

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
    public void saveAdd(ItSimrsPembayaranOnlineEntity bean) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.saveAdd] Start >>>>>>>>");

        try {
            verifikatorPembayaranDao.addAndSave(bean);
        } catch (GeneralBOException e){
            logger.error("[VerifikatorPembayaranBoImpl.saveAdd] Error when update ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.saveAdd] Error when update " + e.getMessage());
        }

        logger.info("[VerifikatorPembayaranBoImpl.saveAdd] End <<<<<<<<");
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
                ItSimrsHeaderDetailCheckupEntity checkTransaksiOnline = checkupDetailDao.getById("noCheckup", bean.getIdTransaksiOnline());
                if (checkTransaksiOnline == null){

                    Map hsCriteria  = new HashMap();
                    hsCriteria.put("no_checkup", noCheckup);

                    // jika sudah ada detail checkup maka update
                    List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntities = checkupDetailDao.getByCriteria(hsCriteria);
                    if (detailCheckupEntities.size() == 1){

                        // update detail checkup dengan resep
                        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", detailCheckupEntities.get(0).getIdDetailCheckup());
                        if (detailCheckupEntity != null){

                            detailCheckupEntity.setIdTransaksiOnline(detailCheckupEntity.getIdTransaksiOnline());
                            detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                            detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());

                            try {
                                checkupDetailDao.updateAndSave(detailCheckupEntity);
                            } catch (HibernateException e) {
                                logger.error("[VerifikatorPembayaranBoImpl.approveTransaksi] Error When Saving data detail checkup" + e.getMessage());
                                throw new GeneralBOException("[VerifikatorPembayaranBoImpl.approveTransaksi] Error When Saving data detail checkup");
                            }

                            String pelayananTujuan = "";
                            List<TransaksiObatDetail> detailList = new ArrayList<>();

                            String idDokter = "";
                            String urlttd = "";

                            hsCriteria = new HashMap();
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

                                    idDokter = resepOnlineEntity.getIdDokter();
                                    urlttd = resepOnlineEntity.getTtdDokter();
                                }
                            }

                            PermintaanResep permintaanResep = new PermintaanResep();
                            permintaanResep.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                            permintaanResep.setIdDokter(idDokter);
                            permintaanResep.setTtdDokter(urlttd);
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
                        }

                    } if (detailCheckupEntities == null || detailCheckupEntities.size() == 0){

                        // insert baru detail checkup
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

                        String idDokter = "";
                        String ttdDokter = "";
                        hsCriteria = new HashMap();
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

                                idDokter = resepOnlineEntity.getIdDokter();
                                ttdDokter = resepOnlineEntity.getTtdDokter();
                            }
                        }

                        PermintaanResep permintaanResep = new PermintaanResep();
                        permintaanResep.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                        permintaanResep.setIdDokter(idDokter);
                        permintaanResep.setTtdDokter(ttdDokter);
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
                    }


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

    @Override
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

    @Override
    public ItSimrsPembayaranOnlineEntity getPembayaranOnlineEntityByIdAntrianAndJenis(String idAntrian, String jenis) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.getPembayaranOnlineEntityByIdAntrianAndJenis] START >>>>>>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_antrian_telemedic", idAntrian);
        hsCriteria.put("keterangan", jenis);

        List<ItSimrsPembayaranOnlineEntity> pembayaranOnlineEntities = new ArrayList<>();
        try {
            pembayaranOnlineEntities = verifikatorPembayaranDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[VerifikatorPembayaranBoImpl.getPembayaranOnlineEntityByIdAntrianAndJenis]  ERROR. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.getPembayaranOnlineEntityByIdAntrianAndJenis]  ERROR. ", e);
        }

        logger.info("[VerifikatorPembayaranBoImpl.getPembayaranOnlineEntityByIdAntrianAndJenis] END <<<<<<<");
        if (pembayaranOnlineEntities != null){
            return pembayaranOnlineEntities.get(0);
        }
        return null;
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

    @Override
    public String generateIdSementara() {
        return transaksiStokDao.getNextSeq();
    }
}
