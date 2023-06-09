package com.neurix.simrs.transaksi.antriantelemedic.bo.impl;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import com.googlecode.mp4parser.authoring.container.mp4.MovieCreator;
import com.googlecode.mp4parser.authoring.tracks.AppendTrack;
import com.lowagie.text.BadElementException;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.master.notif.dao.NotifikasiDao;
import com.neurix.hris.master.tipeNotif.model.ImTipeNotifEntity;
import com.neurix.hris.transaksi.notifikasi.model.ImNotifikasiEntity;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import com.neurix.simrs.master.dokter.dao.DokterDao;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterEntity;
import com.neurix.simrs.master.jenisperiksapasien.bo.AsuransiBo;
import com.neurix.simrs.master.jenisperiksapasien.dao.AsuransiDao;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import com.neurix.simrs.master.kurir.dao.KurirDao;
import com.neurix.simrs.master.kurir.model.ImSimrsKurirEntity;
import com.neurix.simrs.master.pasien.dao.PasienDao;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.tindakan.dao.HeaderTindakanDao;
import com.neurix.simrs.master.tindakan.dao.TindakanDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsHeaderTindakanEntity;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.mobileapi.antrian.model.Antrian;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.antriantelemedic.dao.TelemedicDao;
import com.neurix.simrs.transaksi.antriantelemedic.dao.VideoRmDao;
import com.neurix.simrs.transaksi.antriantelemedic.model.*;
import com.neurix.simrs.transaksi.bataltelemedic.dao.BatalDokterTelemedicDao;
import com.neurix.simrs.transaksi.bataltelemedic.dao.BatalTelemedicDao;
import com.neurix.simrs.transaksi.bataltelemedic.model.BatalTelemedic;
import com.neurix.simrs.transaksi.bataltelemedic.model.ItSimrsBatalTelemedicEntity;
import com.neurix.simrs.transaksi.bataltelemedic.model.ItSimrsDokterBatalTelemedicEntity;
import com.neurix.simrs.transaksi.checkup.dao.HeaderCheckupDao;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderDetailCheckupLogEntity;
import com.neurix.simrs.transaksi.checkupdetail.dao.CheckupDetailDao;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.hargaobat.dao.HargaObatDao;
import com.neurix.simrs.transaksi.hargaobat.model.MtSimrsHargaObatEntity;
import com.neurix.simrs.transaksi.notifikasiadmin.bo.impl.NotifikasiAdminBoImpl;
import com.neurix.simrs.transaksi.notifikasiadmin.dao.NotifikasiAdminTelemedicDao;
import com.neurix.simrs.transaksi.notifikasiadmin.model.ItNotifikasiAdminTelemedicEntity;
import com.neurix.simrs.transaksi.reseponline.dao.PengirimanObatDao;
import com.neurix.simrs.transaksi.reseponline.dao.ResepOnlineDao;
import com.neurix.simrs.transaksi.reseponline.model.ItSimrsPengirimanObatEntity;
import com.neurix.simrs.transaksi.reseponline.model.ItSimrsResepOnlineEntity;
import com.neurix.simrs.transaksi.reseponline.model.PengirimanObat;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.neurix.simrs.transaksi.verifikatorasuransi.dao.StrukAsuransiDao;
import com.neurix.simrs.transaksi.verifikatorasuransi.model.ItSimrsStrukAsuransiEntity;
import com.neurix.simrs.transaksi.verifikatorasuransi.model.StrukAsuransi;
import com.neurix.simrs.transaksi.verifikatorpembayaran.dao.VerifikatorPembayaranDao;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.PembayaranOnline;
import io.agora.recording.common.Common;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.formula.functions.Time;
import org.hibernate.HibernateException;
import org.springframework.security.access.method.P;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by reza on 08/06/20.
 */
public class TelemedicBoImpl implements TelemedicBo {
    private final static Logger logger = Logger.getLogger(TelemedicBoImpl.class);
    private PelayananDao pelayananDao;
    private DokterDao dokterDao;
    private PasienDao pasienDao;
    private TelemedicDao telemedicDao;
    private VerifikatorPembayaranDao verifikatorPembayaranDao;
    private TindakanDao tindakanDao;
    private ResepOnlineDao resepOnlineDao;
    private HargaObatDao hargaObatDao;
    private PengirimanObatDao pengirimanObatDao;
    private KurirDao kurirDao;
    private BranchDao branchDao;
    private AsuransiDao asuransiDao;
    private StrukAsuransiDao strukAsuransiDao;
    private BatalTelemedicDao batalTelemedicDao;
    private BatalDokterTelemedicDao batalDokterTelemedicDao;
    private HeaderCheckupDao headerCheckupDao;
    private CheckupDetailDao checkupDetailDao;
    private NotifikasiAdminTelemedicDao notifikasiAdminTelemedicDao;
    private VideoRmDao videoRmDao;
    private HeaderTindakanDao headerTindakanDao;

    public void setHeaderTindakanDao(HeaderTindakanDao headerTindakanDao) {
        this.headerTindakanDao = headerTindakanDao;
    }

    public void setVideoRmDao(VideoRmDao videoRmDao) {
        this.videoRmDao = videoRmDao;
    }

    public void setNotifikasiAdminTelemedicDao(NotifikasiAdminTelemedicDao notifikasiAdminTelemedicDao) {
        this.notifikasiAdminTelemedicDao = notifikasiAdminTelemedicDao;
    }

    public void setHeaderCheckupDao(HeaderCheckupDao headerCheckupDao) {
        this.headerCheckupDao = headerCheckupDao;
    }

    public void setCheckupDetailDao(CheckupDetailDao checkupDetailDao) {
        this.checkupDetailDao = checkupDetailDao;
    }

    public void setBatalTelemedicDao(BatalTelemedicDao batalTelemedicDao) {
        this.batalTelemedicDao = batalTelemedicDao;
    }

    public void setBatalDokterTelemedicDao(BatalDokterTelemedicDao batalDokterTelemedicDao) {
        this.batalDokterTelemedicDao = batalDokterTelemedicDao;
    }

    public void setStrukAsuransiDao(StrukAsuransiDao strukAsuransiDao) {
        this.strukAsuransiDao = strukAsuransiDao;
    }

    public void setAsuransiDao(AsuransiDao asuransiDao) {
        this.asuransiDao = asuransiDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    public void setKurirDao(KurirDao kurirDao) {
        this.kurirDao = kurirDao;
    }

    public void setPengirimanObatDao(PengirimanObatDao pengirimanObatDao) {
        this.pengirimanObatDao = pengirimanObatDao;
    }

    public void setHargaObatDao(HargaObatDao hargaObatDao) {
        this.hargaObatDao = hargaObatDao;
    }

    public void setResepOnlineDao(ResepOnlineDao resepOnlineDao) {
        this.resepOnlineDao = resepOnlineDao;
    }

    public void setTindakanDao(TindakanDao tindakanDao) {
        this.tindakanDao = tindakanDao;
    }

    public void setVerifikatorPembayaranDao(VerifikatorPembayaranDao verifikatorPembayaranDao) {
        this.verifikatorPembayaranDao = verifikatorPembayaranDao;
    }

    public PelayananDao getPelayananDao() {
        return pelayananDao;
    }

    public void setPelayananDao(PelayananDao pelayananDao) {
        this.pelayananDao = pelayananDao;
    }

    public DokterDao getDokterDao() {
        return dokterDao;
    }

    public void setDokterDao(DokterDao dokterDao) {
        this.dokterDao = dokterDao;
    }

    public PasienDao getPasienDao() {
        return pasienDao;
    }

    public void setPasienDao(PasienDao pasienDao) {
        this.pasienDao = pasienDao;
    }

    public TelemedicDao getTelemedicDao() {
        return telemedicDao;
    }

    public void setTelemedicDao(TelemedicDao telemedicDao) {
        this.telemedicDao = telemedicDao;
    }

    @Override
    public List<AntrianTelemedic> getSearchByCriteria(AntrianTelemedic bean) throws GeneralBOException {
        logger.info("[TelemedicBoImpl.getSearchByCriteria] START >>>");

        // untuk pencarian berdasarkan id transaksi
        if (bean.getIdTransaksi() != null && !"".equalsIgnoreCase(bean.getIdTransaksi())){
            ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranDao.getById("id", bean.getIdTransaksi());
            if (pembayaranOnlineEntity != null){
                bean.setIdTransaksi(bean.getIdTransaksi());
            }
        }

        List<AntrianTelemedic> results = new ArrayList<>();
        List<ItSimrsAntrianTelemedicEntity> antrianTelemedicEntities = getListEntityByCriteria(bean);
        if (antrianTelemedicEntities.size() > 0){

            AntrianTelemedic antrianTelemedic;
            for (ItSimrsAntrianTelemedicEntity telemedicEntity : antrianTelemedicEntities) {
                antrianTelemedic = new AntrianTelemedic();
                antrianTelemedic.setId(telemedicEntity.getId());
                antrianTelemedic.setIdPasien(telemedicEntity.getIdPasien());
                antrianTelemedic.setIdPelayanan(telemedicEntity.getIdPelayanan());
                antrianTelemedic.setIdDokter(telemedicEntity.getIdDokter());
                antrianTelemedic.setIdJenisPeriksaPasien(telemedicEntity.getIdJenisPeriksaPasien());
                antrianTelemedic.setAsuransi(telemedicEntity.getIdAsuransi());
                antrianTelemedic.setIdAsuransi(telemedicEntity.getIdAsuransi());

                antrianTelemedic.setNoKartu(telemedicEntity.getNoKartu());
                antrianTelemedic.setStatus(telemedicEntity.getStatus());
                antrianTelemedic.setCreatedDate(telemedicEntity.getCreatedDate());
                antrianTelemedic.setCreatedWho(telemedicEntity.getCreatedWho());
                antrianTelemedic.setLastUpdate(telemedicEntity.getLastUpdate());
                antrianTelemedic.setLastUpdateWho(telemedicEntity.getLastUpdateWho());
                antrianTelemedic.setBiayaKonsultasi(telemedicEntity.getBiayaKonsultasi());
                antrianTelemedic.setFlagResep(telemedicEntity.getFlagResep());
                antrianTelemedic.setFlagBayarKonsultasi(telemedicEntity.getFlagBayarKonsultasi());

                antrianTelemedic.setFlagBayarResep(telemedicEntity.getFlagBayarResep());
                antrianTelemedic.setFlag(telemedicEntity.getFlag());
                antrianTelemedic.setAction(telemedicEntity.getAction());
                antrianTelemedic.setJenisPengambilan(telemedicEntity.getJenisPengambilan());
                antrianTelemedic.setJumlahCover(telemedicEntity.getJumlahCover());
                antrianTelemedic.setNoSep(telemedicEntity.getNoSep());
                antrianTelemedic.setNoRujukan(telemedicEntity.getNoRujukan());
                antrianTelemedic.setJenisRujukan(telemedicEntity.getJenisRujukan());
                antrianTelemedic.setIdDiagnosa(telemedicEntity.getIdDiagnosa());
                antrianTelemedic.setKetDiagnosa(telemedicEntity.getKetDiagnosa());
                antrianTelemedic.setStCreatedDate(telemedicEntity.getCreatedDate() != null ? telemedicEntity.getCreatedDate().toString() : "");

                if (telemedicEntity.getIdPelayanan() != null && !"".equalsIgnoreCase(telemedicEntity.getIdPelayanan())) {
                    antrianTelemedic.setNamaPelayanan(getPelayananById(telemedicEntity.getIdPelayanan()).getNamaPelayanan());
                }
                if (telemedicEntity.getIdPasien() != null && !"".equalsIgnoreCase(telemedicEntity.getIdPasien())) {
                    antrianTelemedic.setNamaPasien(getPasienById(telemedicEntity.getIdPasien()).getNama());
                }
                if (telemedicEntity.getIdDokter() != null && !"".equalsIgnoreCase(telemedicEntity.getIdDokter())) {
                    antrianTelemedic.setNamaDokter(getDokterById(telemedicEntity.getIdDokter()).getNamaDokter());
                }
                if (telemedicEntity.getIdAsuransi() != null && !"".equalsIgnoreCase(telemedicEntity.getIdAsuransi())) {
                    antrianTelemedic.setNamaAsuransi(getAsuransiById(telemedicEntity.getIdAsuransi()).getNamaAsuransi());
                }

                antrianTelemedic.setKetStatus(ketStatus(telemedicEntity.getStatus()));
                antrianTelemedic.setKetFlagResep(ketResep(telemedicEntity.getFlagResep()));
                antrianTelemedic.setKodeBank(telemedicEntity.getKodeBank());
                antrianTelemedic.setBranchId(telemedicEntity.getBranchId());
                antrianTelemedic.setKeluhan(telemedicEntity.getKeluhan());
                antrianTelemedic.setFlagEresep(telemedicEntity.getFlagEresep());
                antrianTelemedic.setUrlResep(telemedicEntity.getUrlFotoResep());

                // cari pembayaran online konsultasi;
                PembayaranOnline pembayaranOnline = new PembayaranOnline();
                pembayaranOnline.setIdAntrianTelemedic(telemedicEntity.getId());
                pembayaranOnline.setKeterangan("konsultasi");

                List<PembayaranOnline> listKonsultasi = getListPembayaranOnline(pembayaranOnline);
                if (listKonsultasi.size() > 0) {
                    for (PembayaranOnline konsultasi : listKonsultasi) {
                        antrianTelemedic.setApproveKonsultasi(konsultasi.getApprovedFlag());
                    }
                }
                // end;

                // cari pembayaran online resep;
                pembayaranOnline = new PembayaranOnline();
                pembayaranOnline.setIdAntrianTelemedic(telemedicEntity.getId());
                pembayaranOnline.setKeterangan("resep");
                List<PembayaranOnline> listResep = getListPembayaranOnline(pembayaranOnline);
                if (listResep.size() > 0) {
                    for (PembayaranOnline resep : listResep) {
                        antrianTelemedic.setApproveResep(resep.getApprovedFlag());
                    }
                }
                // end;

                // filter status transaksi
                if ("asuransi".equalsIgnoreCase(antrianTelemedic.getIdJenisPeriksaPasien())){
                    if ("Y".equalsIgnoreCase(antrianTelemedic.getFlagBayarKonsultasi())){
                        antrianTelemedic.setStatusTransaksi("finish");
                        antrianTelemedic.setLabelStatusAsuransi("Terverifikasi");
                    }
                    if (antrianTelemedic.getFlagBayarKonsultasi() == null) {
                        if ("Y".equalsIgnoreCase(antrianTelemedic.getApproveKonsultasi()) && "Y".equalsIgnoreCase(antrianTelemedic.getApproveResep())){
                            antrianTelemedic.setStatusTransaksi("confirmation");
                        } else {
                            antrianTelemedic.setStatusTransaksi("exist");
                        }
                        antrianTelemedic.setLabelStatusAsuransi(labelStatusAsuransi(antrianTelemedic.getId()));
                    }
                } else {
                    if (antrianTelemedic.getFlagResep() == null && antrianTelemedic.getApproveKonsultasi() != null) {
                        antrianTelemedic.setStatusTransaksi("finish");
                    } else if ("Y".equalsIgnoreCase(antrianTelemedic.getFlagResep()) && antrianTelemedic.getApproveKonsultasi() != null && antrianTelemedic.getApproveResep() != null) {
                        antrianTelemedic.setStatusTransaksi("finish");
                    } else if ("Y".equalsIgnoreCase(antrianTelemedic.getApproveResep()) && "Y".equalsIgnoreCase(telemedicEntity.getFlagBayarResep())){
                        antrianTelemedic.setStatusTransaksi("finish");
                    } else {
                        antrianTelemedic.setStatusTransaksi("exist");
                    }
                }

                // flag batal dokter;
                BatalTelemedic batalTelemedic = getBatalTemedicByIdAntrian(telemedicEntity.getId(), telemedicEntity.getIdDokter(), telemedicEntity.getIdPelayanan(), telemedicEntity.getCreatedDate());
                if (batalTelemedic != null){

                    if ("Y".equalsIgnoreCase(antrianTelemedic.getFlagResep()) && "Y".equalsIgnoreCase(antrianTelemedic.getFlagBayarResep())){
                        if ("Y".equalsIgnoreCase(batalTelemedic.getFlagKembaliKonsultasi()) && "Y".equalsIgnoreCase(batalTelemedic.getFlagKembaliResep())){
                            antrianTelemedic.setFlagDanaDikembaLikan("Y");
                        } else {
                            antrianTelemedic.setFlagDanaDikembaLikan("N");
                        }
                    } else if ("Y".equalsIgnoreCase(antrianTelemedic.getFlagBayarKonsultasi())){
                        if ("Y".equalsIgnoreCase(batalTelemedic.getFlagKembaliKonsultasi())){
                            antrianTelemedic.setFlagDanaDikembaLikan("Y");
                        } else {
                            antrianTelemedic.setFlagDanaDikembaLikan("N");
                        }
                    } else {
                        antrianTelemedic.setFlagDanaDikembaLikan("N");
                    }

                    antrianTelemedic.setFlagBatalDokter("Y");
                    antrianTelemedic.setIdBatalDokterTelemedic(batalTelemedic.getId());
                    antrianTelemedic.setAlasanBatal(batalTelemedic.getAlasan());
                    antrianTelemedic.setStatusTransaksi("canceled");
                }

                // untuk batal;
                if (telemedicDao.foundIfAllFlagNotActive(antrianTelemedic.getId()))
                    antrianTelemedic.setStatusTransaksi("canceled");

                results.add(antrianTelemedic);
            }
        }

        logger.info("[TelemedicBoImpl.getSearchByCriteria] END <<<");
        if (bean.getStatusTransaksi() == null) {
            return results;
        } else {
            final String statusTransaksi = bean.getStatusTransaksi();
            return results.stream().filter(p->p.getStatusTransaksi().equalsIgnoreCase(statusTransaksi)).collect(Collectors.toList());
        }

//        if ("finish".equalsIgnoreCase(statusTransaksi) || "exist".equalsIgnoreCase(statusTransaksi) || "confirmation".equalsIgnoreCase(statusTransaksi)){
//            return results.stream().filter(p->p.getStatusTransaksi().equalsIgnoreCase(statusTransaksi)).collect(Collectors.toList());
//        }

//        return results;
    }

    private BatalTelemedic getBatalTemedicByIdAntrian(String idAntrian, String idDokter, String idPelayanan, Timestamp createdDate){

        java.sql.Date date = new java.sql.Date(createdDate.getTime());

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_antrian_telemedic", idAntrian);
        hsCriteria.put("flag", "Y");

        List<ItSimrsBatalTelemedicEntity> batalTelemedicEntities = new ArrayList<>();

        try {
            batalTelemedicEntities = batalTelemedicDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.info("[TelemedicBoImpl.getBatalTemedicEntityByIdAntrian] ERROR. when search Telemedic Batal. ", e);
            throw new GeneralBOException("[TelemedicBoImpl.getBatalTemedicEntityByIdAntrian] ERROR. when search Telemedic Batal. "+ e.getMessage());
        }

        if (batalTelemedicEntities.size() > 0){
            ItSimrsBatalTelemedicEntity batalTelemedicEntity = batalTelemedicEntities.get(0);

            hsCriteria = new HashMap();
            hsCriteria.put("id", batalTelemedicEntity.getIdDokterBatal());
            hsCriteria.put("id_dokter", idDokter);
            hsCriteria.put("id_pelayanan", idPelayanan);
            hsCriteria.put("flag", "Y");
            hsCriteria.put("batal_date", date);

            List<ItSimrsDokterBatalTelemedicEntity> dokterBatalTelemedicEntities = new ArrayList<>();
            try {
                dokterBatalTelemedicEntities = batalDokterTelemedicDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.info("[TelemedicBoImpl.getBatalTemedicEntityByIdAntrian] ERROR. when search Dokter Batal Telemedic. ", e);
                throw new GeneralBOException("[TelemedicBoImpl.getBatalTemedicEntityByIdAntrian] ERROR. when search Dokter Batal Telemedic. "+ e.getMessage());
            }

            String alasan = "";
            if (dokterBatalTelemedicEntities.size() > 0){
                ItSimrsDokterBatalTelemedicEntity dokterBatalTelemedicEntity = dokterBatalTelemedicEntities.get(0);
                alasan = dokterBatalTelemedicEntity.getAlasan();
            }

            BatalTelemedic batalTelemedic = new BatalTelemedic();
            batalTelemedic.setId(batalTelemedicEntity.getId());
            batalTelemedic.setIdAntrianTelemedic(batalTelemedicEntity.getIdAntrianTelemedic());
            batalTelemedic.setIdDokterBatal(batalTelemedicEntity.getIdDokterBatal());
            batalTelemedic.setFlag(batalTelemedicEntity.getFlag());
            batalTelemedic.setAction(batalTelemedicEntity.getAction());
            batalTelemedic.setCreatedDate(batalTelemedicEntity.getCreatedDate());
            batalTelemedic.setCreatedWho(batalTelemedicEntity.getCreatedWho());
            batalTelemedic.setNoJurnal(batalTelemedicEntity.getNoJurnal());
            batalTelemedic.setAlasan(alasan);
            batalTelemedic.setKembaliKonsultasi(batalTelemedicEntity.getKembaliKonsultasi() == null ? new BigDecimal(0) : batalTelemedicEntity.getKembaliKonsultasi());
            batalTelemedic.setFlagKembaliKonsultasi(batalTelemedicEntity.getFlagKembaliKonsultasi());
            batalTelemedic.setKembaliResep(batalTelemedicEntity.getKembaliResep() == null ? new BigDecimal(0) : batalTelemedicEntity.getKembaliResep());
            batalTelemedic.setFlagKembaliResep(batalTelemedicEntity.getFlagKembaliResep());
            return batalTelemedic;
        }

        return null;
    }

    private String labelStatusAsuransi(String idAntrian) throws GeneralBOException{

        String label = "";
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_antrian_telemedic", idAntrian);
        List<ItSimrsStrukAsuransiEntity> strukAsuransiEntities = strukAsuransiDao.getByCriteria(hsCriteria);
        if (strukAsuransiEntities.size() > 0){
            for (ItSimrsStrukAsuransiEntity strukAsuransiEntity : strukAsuransiEntities){

                if ("authorization".equalsIgnoreCase(strukAsuransiEntity.getJenis())){
                    if ("Y".equalsIgnoreCase(strukAsuransiEntity.getApproveFlag())){
                        label = "Authorization by Pasien";
                    } else if (strukAsuransiEntity.getUrlFotoStruk() != null) {
                        label = "Menunggu Authorization";
                    } else {
                        label = "Upload Authorization";
                    }
                }

                if ("confirmation".equalsIgnoreCase(strukAsuransiEntity.getJenis())){
                    if ("Y".equalsIgnoreCase(strukAsuransiEntity.getApproveFlag())){
                        label = "Confirmation by Pasien";
                    } else if (strukAsuransiEntity.getUrlFotoStruk() != null) {
                        label = "Menunggu Confirmation";
                    } else {
                        label = "Upload Confirmation";
                    }
                }
            }
        }

        return label;
    }

    @Override
    public List<AntrianTelemedic> getListAntrianByCriteria(AntrianTelemedic bean) throws GeneralBOException {
        logger.info("[TelemedicBoImpl.getListAntrianByCriteria] START >>>");

        // getListAntrianByCriteria method khusus mencari pasien yang sedang antri;
        // properti yang dibutuhkan : idDokter, idPelayanan, status, flag = Y;
        // jika mencari berdasarkan status antrian tertentu maka isi properti status;
        // jika mencari berdasarkan status antrian semuanya maka properti status haruslah null / "";
        // apa saja yang termasuk status antrian ? lihat pada StatusAntrianTelemedic.java;
        // jika properti status ada namun bukan status antrian maka tidak mendapatkan data;

        // cek apakah properti status = status antrian;
        List<AntrianTelemedic> results = new ArrayList<>();
        StatusAntrianTelemedic statusAntrian = new StatusAntrianTelemedic();
        boolean isStatusAntrian = false;
        if (bean.getStatus() != null && !"".equalsIgnoreCase(bean.getStatus())){
            List<StatusAntrianTelemedic> statusAntrianTelemedics = statusAntrian.getStatusAntrianOrder().stream().filter(p -> p.getStatus().equalsIgnoreCase(bean.getStatus())).collect(Collectors.toList());
            if (statusAntrianTelemedics.size() > 0){
                isStatusAntrian = true;
            }
        }

        if (isStatusAntrian){

            // jika properti status = status antrian;
           results.addAll(getSearchByCriteria(bean));
        } else {

            // jika properti status null / "", maka mencari semua berdasarkan order status;
            if (bean.getStatus() == null || "".equalsIgnoreCase(bean.getStatus())){
                for (StatusAntrianTelemedic statusAntrianTelemedic : statusAntrian.getStatusAntrianOrder()){

                    bean.setStatus(statusAntrianTelemedic.getStatus());
                    List<AntrianTelemedic> antrianTelemedics = getSearchByCriteria(bean);
                    if (antrianTelemedics.size() > 0){
                        results.addAll(antrianTelemedics);
                    }
                }
            }
        }

        logger.info("[TelemedicBoImpl.getListAntrianByCriteria] END <<<");
        return results;
    }

    private String ketStatus(String statusId){
        if (statusId != null){
            switch (statusId){
                case "PD":
                    return "Dokter Memanggil Anda . . .";
                case "SK":
                    return "Selesai Konsultasi";
                case "LL":
                    return "Antrian Long List";
                case "WL":
                    return "Waiting List";
                case "SL":
                    return "Antrian Short List";
                case "ER":
                    return "Pembayaran E Resep";
                case "FN":
                    return "Selesai";
                default:
                    return "";
            }
        }
       return "";
    }
    private String ketResep(String flagResep){
        if (flagResep != null){
            switch (flagResep){
                case "Y":
                    return "Resep";
                default:
                    return "";
            }
        }
        return "";
    }

    private ImSimrsPelayananEntity getPelayananById(String idPelayanan) throws GeneralBOException{
        return pelayananDao.getById("idPelayanan", idPelayanan);
    }

    private ImSimrsDokterEntity getDokterById(String idDokter) throws GeneralBOException{
        return dokterDao.getById("idDokter", idDokter);
    }

    private ImSimrsPasienEntity getPasienById(String idPasien) throws GeneralBOException{
        return pasienDao.getById("idPasien", idPasien);
    }

    private ImSimrsAsuransiEntity getAsuransiById(String idAsuransi) throws GeneralBOException{
        return asuransiDao.getById("idAsuransi", idAsuransi);
    }

    @Override
    public ItSimrsAntrianTelemedicEntity getAntrianTelemedicEntityById(String id) throws GeneralBOException {
        return telemedicDao.getById("id", id);
    }

    @Override
    public List<ItSimrsAntrianTelemedicEntity> getListEntityByCriteria(AntrianTelemedic bean) throws GeneralBOException{
        logger.info("[TelemedicBoImpl.getListEntityByCriteria] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getId() != null && !"".equalsIgnoreCase(bean.getId())){
            hsCriteria.put("id", bean.getId());
        }
        if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())){
            hsCriteria.put("id_pasien", bean.getIdPasien());
        }
        if (bean.getIdDokter() != null && !"".equalsIgnoreCase(bean.getIdDokter())){
            hsCriteria.put("id_dokter", bean.getIdDokter());
        }
        if (bean.getIdPelayanan() != null  && !"".equalsIgnoreCase(bean.getIdPelayanan()) ){
            hsCriteria.put("id_pelayanan", bean.getIdPelayanan());
        }
        if (bean.getStatus() != null && !"".equalsIgnoreCase(bean.getStatus())){
            hsCriteria.put("status", bean.getStatus());
        }
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())){
            hsCriteria.put("flag", bean.getFlag());
        }
        if (bean.getIdJenisPeriksaPasien() != null && !"".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
            hsCriteria.put("jenis_pasien", bean.getIdJenisPeriksaPasien());
        }
        if (bean.getFlagResep() != null && !"".equalsIgnoreCase(bean.getFlagResep())){
            hsCriteria.put("flag_resep", bean.getFlagResep());
        }
        if (bean.getFlagBayarResep() != null && !"".equalsIgnoreCase(bean.getFlagBayarResep())) {
            hsCriteria.put("flag_bayar_resep", bean.getFlagBayarResep());
        }
        if (bean.getFlagEresep() != null && !"".equalsIgnoreCase(bean.getFlagEresep())) {
            hsCriteria.put("flag_eresep", bean.getFlagEresep());
        }
        if (bean.getFlagDateNow() != null && !"".equalsIgnoreCase(bean.getFlagDateNow())) {
            hsCriteria.put("created_date_to_date", bean.getFlagDateNow());
        }
        if (bean.getStDateFrom() != null && !"".equalsIgnoreCase(bean.getStDateFrom())) {
            hsCriteria.put("date_from", bean.getStDateFrom());
        }
        if (bean.getStDateTo() != null && !"".equalsIgnoreCase(bean.getStDateTo())) {
            hsCriteria.put("date_to", bean.getStDateTo());
        }

        List<ItSimrsAntrianTelemedicEntity> antrianTelemedicEntities = new ArrayList<>();

        try {
            antrianTelemedicEntities = telemedicDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.info("[TelemedicBoImpl.getListEntityByCriteria] ERROR. ", e);
            throw new GeneralBOException("[TelemedicBoImpl.getListEntityByCriteria] ERROR. "+ e.getMessage());
        }

        logger.info("[TelemedicBoImpl.getListEntityByCriteria] END <<<");
        return antrianTelemedicEntities;
    }

    @Override
    public String saveAdd(ItSimrsAntrianTelemedicEntity bean, String branchId, String kodeBank) throws GeneralBOException {
        logger.info("[TelemedicBoImpl.saveAdd] START >>>");

        // semua property entiy yang dibutuhkan di set pada action / controller. kecuali status dan id;
        // status akan ditentukan dengan mencari slot waiting list.
        // apa saja properti yang dibutuhkan ? lihat ItSimrsAntrianTelemedicEntity.java

        bean.setId("TMC"+CommonUtil.stDateSeq()+getSeqTelemedic());
        bean.setBranchId(branchId);
        bean.setKodeBank(kodeBank);
        Map hsCriteria = new HashMap();
        hsCriteria.put("id_dokter", bean.getIdDokter());
        hsCriteria.put("id_pelayanan", bean.getIdPelayanan());
        hsCriteria.put("status", "WL");
        hsCriteria.put("flag", "Y");

        // cek jika ada slot untuk waiting list
        List<ItSimrsAntrianTelemedicEntity> telemedicEntities = telemedicDao.getByCriteria(hsCriteria);
        if (telemedicEntities.size() >= 3){
            bean.setStatus("LL");
        } else {
            bean.setStatus("WL");
        }

        // jika ERESEP
        if ("Y".equalsIgnoreCase(bean.getFlagEresep())){
            bean.setStatus("ER");
        }

        try {
            telemedicDao.addAndSave(bean);
        } catch (HibernateException e){
            logger.info("[TelemedicBoImpl.getListEntityByCriteria] ERROR. ", e);
            throw new GeneralBOException("[TelemedicBoImpl.getListEntityByCriteria] ERROR. "+ e.getMessage());
        }



        // jika waiting list maka generate daftar pembayaran;
        if ("WL".equalsIgnoreCase(bean.getStatus())){
            generateListPembayaran(bean, branchId, "konsultasi", kodeBank, bean.getIdJenisPeriksaPasien());

            if ("Y".equalsIgnoreCase(bean.getFlagResep())){
                generateListPembayaran(bean, branchId, "resep", kodeBank, bean.getIdJenisPeriksaPasien());
            }

            // jika asuransi maka create data untuk struk authirization asuransi
            if ("asuransi".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()))
                createStrukAsuransi(bean, "authorization");

            // notifikasi
            createNotifikasiAdmin(bean.getId(), "tele", bean.getBranchId(), bean.getCreatedWho(), bean.getCreatedDate(), "Waiting List Baru. ID "+bean.getId());
        }
        logger.info("[TelemedicBoImpl.saveAdd] END <<<");
        return bean.getId();
    }

    @Override
    public void createStrukAsuransi(ItSimrsAntrianTelemedicEntity bean, String jenis) throws GeneralBOException{
        logger.info("[TelemedicBoImpl.createStrukAsuransi] START >>>");

        ItSimrsStrukAsuransiEntity simrsStrukAsuransiEntity = new ItSimrsStrukAsuransiEntity();
        simrsStrukAsuransiEntity.setId(generateIdStrukAsuransi(bean.getBranchId()));
        simrsStrukAsuransiEntity.setIdAntrianTelemedic(bean.getId());
        simrsStrukAsuransiEntity.setBranchId(bean.getBranchId());
        simrsStrukAsuransiEntity.setJenis(jenis);
        simrsStrukAsuransiEntity.setFlag("Y");
        simrsStrukAsuransiEntity.setAction("C");
        simrsStrukAsuransiEntity.setCreatedDate(bean.getCreatedDate());
        simrsStrukAsuransiEntity.setCreatedWho(bean.getCreatedWho());
        simrsStrukAsuransiEntity.setLastUpdate(bean.getLastUpdate());
        simrsStrukAsuransiEntity.setLastUpdateWho(bean.getLastUpdateWho());

        try {
            strukAsuransiDao.addAndSave(simrsStrukAsuransiEntity);
        } catch (HibernateException e){
            logger.info("[TelemedicBoImpl.createStrukAsuransi] ERROR. ", e);
            throw new GeneralBOException("[TelemedicBoImpl.createStrukAsuransi] ERROR. "+ e.getMessage());
        }

        logger.info("[TelemedicBoImpl.createStrukAsuransi] END <<<");
    }

    @Override
    public void saveEdit(AntrianTelemedic bean, String branchId, String kodeBank) throws GeneralBOException {

        // save edit hanya untuk pergantian status / flag pembayaran;
        // properti yang wajib ada adalah id
        // properti yang diisi di action / controler hanya seperti dibawah;

        if (bean.getId() != null){
            String statusSebelum = "";
            ItSimrsAntrianTelemedicEntity telemedicEntity = getAntrianTelemedicEntityById(bean.getId());
            if (telemedicEntity != null){

                statusSebelum = telemedicEntity.getStatus();

                if ("Y".equalsIgnoreCase(bean.getFlagApproveConfirm())){
                    // jika prosess approve confirm dari asuransi

                    telemedicEntity.setFlagBayarKonsultasi("Y");
                    if ("Y".equalsIgnoreCase(telemedicEntity.getFlagResep())){
                        telemedicEntity.setFlagBayarResep("Y");
                    }
                    telemedicEntity.setAction("U");
                    telemedicEntity.setLastUpdate(bean.getLastUpdate());
                    telemedicEntity.setLastUpdateWho(bean.getLastUpdateWho());

                } else {

                    // jika untuk yang lain
                    telemedicEntity.setFlag(bean.getFlag() == null ? telemedicEntity.getFlag() : bean.getFlag());
                    telemedicEntity.setFlagResep(bean.getFlagResep() == null ? telemedicEntity.getFlagResep() : bean.getFlagResep());
                    telemedicEntity.setFlagBayarResep(bean.getFlagBayarResep() == null ? telemedicEntity.getFlagBayarResep() : bean.getFlagBayarResep());
                    telemedicEntity.setFlagBayarKonsultasi(bean.getFlagBayarKonsultasi() == null  ? telemedicEntity.getFlagBayarKonsultasi() : bean.getFlagBayarKonsultasi());
                    telemedicEntity.setStatus(bean.getStatus() == null ? statusSebelum : bean.getStatus());
                    telemedicEntity.setJumlahCover(bean.getJumlahCover() == null ? telemedicEntity.getJumlahCover() : bean.getJumlahCover());
                    telemedicEntity.setNoJurnal(bean.getNoJurnal() == null ? telemedicEntity.getNoJurnal() : bean.getNoJurnal());
                    telemedicEntity.setNoSep(bean.getNoSep() == null ? telemedicEntity.getNoSep() : bean.getNoSep());
                    telemedicEntity.setIdDiagnosa(bean.getIdDiagnosa() == null ? telemedicEntity.getIdDiagnosa() : bean.getIdDiagnosa());
                    telemedicEntity.setKetDiagnosa(bean.getKetDiagnosa() == null ? telemedicEntity.getKetDiagnosa() : bean.getKetDiagnosa());
                    telemedicEntity.setAction("U");
                    telemedicEntity.setLastUpdate(bean.getLastUpdate());
                    telemedicEntity.setLastUpdateWho(bean.getLastUpdateWho());
                }

                try {
                    telemedicDao.updateAndSave(telemedicEntity);
                } catch (HibernateException e){
                    logger.info("[TelemedicBoImpl.getListEntityByCriteria] ERROR. ", e);
                    throw new GeneralBOException("[TelemedicBoImpl.getListEntityByCriteria] ERROR. "+ e.getMessage());
                }

                // jika waiting list maka generate daftar pembayaran jika perubahan dari LL ke WL;
                if ("LL".equalsIgnoreCase(statusSebelum) && "WL".equalsIgnoreCase(telemedicEntity.getStatus())){
                    generateListPembayaran(telemedicEntity, branchId, "konsultasi", kodeBank, telemedicEntity.getIdJenisPeriksaPasien());
                    if ("Y".equalsIgnoreCase(telemedicEntity.getFlagResep())){
                        generateListPembayaran(telemedicEntity, branchId, "resep", kodeBank, telemedicEntity.getIdJenisPeriksaPasien());
                    }
                }
            }
        }
    }

    @Override
    public void generateListPembayaran(ItSimrsAntrianTelemedicEntity bean, String branchId, String tipe, String kodeBank, String jenisPeriksa) throws GeneralBOException{
        logger.info("[TelemedicBoIml.generateListPembayaran] START >>>");

        if ("konsultasi".equalsIgnoreCase(tipe)){

            // mencari tindakan konsultasi;
            Map hsCriteria = new HashMap();
            hsCriteria.put("konsul_tele", "Y");
            hsCriteria.put("branch_id", branchId);

            List<ImSimrsHeaderTindakanEntity> headerTindakanEntities = headerTindakanDao.getByCriteria(hsCriteria);

            if (headerTindakanEntities.size() > 0){

                if (headerTindakanEntities.size() > 1) {
                    logger.error("[TelemedicBoIml.generateListPembayaran] ERROR. Tindakan Konsultasi Tele Redundan. Tentukan Yang Dipakai ");
                    throw new GeneralBOException("[TelemedicBoIml.generateListPembayaran] ERROR. Tindakan Konsultasi Tele Redundan. Tentukan Yang Dipakai.");
                }

                ImSimrsHeaderTindakanEntity headerTindakanEntity = headerTindakanEntities.get(0);

                // mengambil tindakan konsultasi dokter berdasarkan branch, id_header_tindakan, id_pelayanan;
                hsCriteria = new HashMap();
                hsCriteria.put("branch_id", branchId);
                hsCriteria.put("id_header_tindakan", headerTindakanEntity.getIdHeaderTindakan());
                hsCriteria.put("id_pelayanan", bean.getIdPelayanan());

                List<ImSimrsTindakanEntity> tindakanEntities = tindakanDao.getByCriteria(hsCriteria);

                if (tindakanEntities.size() > 0){

                    ImSimrsTindakanEntity tindakanEntity = null;
                    if (tindakanEntities.size() > 0){
                        tindakanEntity = new ImSimrsTindakanEntity();
                        tindakanEntity = tindakanEntities.get(0);
                    }

                    ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = new ItSimrsPembayaranOnlineEntity();
                    pembayaranOnlineEntity.setId(getSeqPembayaranOnline(bean.getId()));
                    pembayaranOnlineEntity.setIdAntrianTelemedic(bean.getId());
                    pembayaranOnlineEntity.setLastUpdate(bean.getLastUpdate());

                    if (tindakanEntity != null){
                        pembayaranOnlineEntity.setIdItem(tindakanEntity.getIdTindakan());

                        // jika umum generate 3 digit jenis angka untuk penambahan pada nominal;
                        if ("umum".equalsIgnoreCase(jenisPeriksa))
                            pembayaranOnlineEntity.setNominal(new BigDecimal(tindakanEntity.getTarif()).add(generateRandomNumBigDecimal()));
                        else
                            pembayaranOnlineEntity.setNominal(new BigDecimal(tindakanEntity.getTarif()));
                        // END

                    }

                    pembayaranOnlineEntity.setFlag(bean.getFlag());
                    pembayaranOnlineEntity.setCreatedDate(bean.getCreatedDate());
                    pembayaranOnlineEntity.setCreatedWho(bean.getCreatedWho());
                    pembayaranOnlineEntity.setLastUpdate(bean.getLastUpdate());
                    pembayaranOnlineEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    pembayaranOnlineEntity.setKeterangan(tipe);
                    pembayaranOnlineEntity.setKodeBank(kodeBank);
                    pembayaranOnlineEntity.setIdRekening(bean.getIdRekening());

                    try {
                        verifikatorPembayaranDao.addAndSave(pembayaranOnlineEntity);
                    } catch (HibernateException e){
                        logger.error("[TelemedicBoIml.generateListPembayaran] ERROR. ",e);
                        throw new GeneralBOException("[TelemedicBoIml.generateListPembayaran] ERROR. ",e);
                    }
                } else {
                    logger.error("[TelemedicBoIml.generateListPembayaran] ERROR. tidak ditemukan tindakan konsultasi pada unit tersebut ");
                    throw new GeneralBOException("[TelemedicBoImpl.generateListPembayaran] ERROR. tidak ditemukan tindakan konsultasi pada unit tersebut ");
                }
            } else {
                logger.error("[TelemedicBoIml.generateListPembayaran] ERROR. tidak ditemukan tindakan konsultasi ");
                throw new GeneralBOException("[TelemedicBoImpl.generateListPembayaran] ERROR. tidak ditemukan tindakan konsultasi ");
            }

        } else if ("resep".equalsIgnoreCase(tipe)){
            // for resep
            ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = new ItSimrsPembayaranOnlineEntity();
            pembayaranOnlineEntity.setId(getSeqPembayaranOnline(bean.getId()));
            pembayaranOnlineEntity.setIdAntrianTelemedic(bean.getId());
            pembayaranOnlineEntity.setLastUpdate(bean.getLastUpdate());
            pembayaranOnlineEntity.setFlag(bean.getFlag());
            pembayaranOnlineEntity.setCreatedDate(bean.getCreatedDate());
            pembayaranOnlineEntity.setCreatedWho(bean.getCreatedWho());
            pembayaranOnlineEntity.setLastUpdate(bean.getLastUpdate());
            pembayaranOnlineEntity.setLastUpdateWho(bean.getLastUpdateWho());
            pembayaranOnlineEntity.setKeterangan("resep");

            try {
                verifikatorPembayaranDao.addAndSave(pembayaranOnlineEntity);
            } catch (HibernateException e){
                logger.error("[TelemedicBoIml.generateListPembayaran] ERROR. ",e);
                throw new GeneralBOException("[TelemedicBoIml.generateListPembayaran] ERROR. ",e);
            }
        }

        logger.info("[TelemedicBoIml.generateListPembayaran] END <<<");
    }

    private List<PembayaranOnline> getListPembayaranOnline(PembayaranOnline bean) throws GeneralBOException {

        List<PembayaranOnline> pembayaranOnlines = new ArrayList<>();
        List<ItSimrsPembayaranOnlineEntity> pembayaranOnlineEntities = getPembayaranOnlineEntityByCriteria(bean);
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

    private List<ItSimrsPembayaranOnlineEntity> getPembayaranOnlineEntityByCriteria(PembayaranOnline bean) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.getSearchEntityByCriteria] START >>>");

        Map hsCriteria = new HashMap();
        if (bean.getIdAntrianTelemedic() != null){
            hsCriteria.put("id_antrian_telemedic", bean.getIdAntrianTelemedic());
        }
        if (bean.getKeterangan() != null){
            hsCriteria.put("keterangan", bean.getKeterangan());
        }
        if (bean.getApprovedFlag() != null){
            hsCriteria.put("approve_flag", bean.getApprovedFlag());
        }
        if (bean.getFlag() != null){
            hsCriteria.put("flag", bean.getFlag());
        }
        if (bean.getId() != null)
            hsCriteria.put("id", bean.getId());

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
    public ItSimrsAntrianTelemedicEntity getAntrianTelemedicFirstOrder(String idPelayanan, String idDokter, String status) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.ItSimrsAntrianTelemedicEntity] START >>>");

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_pelayanan", idPelayanan);
        hsCriteria.put("id_dokter", idDokter);
        hsCriteria.put("status", status);
        hsCriteria.put("flag", "Y");
        hsCriteria.put("acs_limit_1", "Y");

        List<ItSimrsAntrianTelemedicEntity> antrianTelemedicEntities = new ArrayList<>();
        try {
            antrianTelemedicEntities = telemedicDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[VerifikatorPembayaranBoImpl.ItSimrsAntrianTelemedicEntity] ERROR. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.getSearchEntityByCriteria] ERROR. ", e);
        }

        if (antrianTelemedicEntities.size() > 0){
            logger.info("[VerifikatorPembayaranBoImpl.ItSimrsAntrianTelemedicEntity] END <<<");
            return antrianTelemedicEntities.get(0);
        }

        logger.info("[VerifikatorPembayaranBoImpl.ItSimrsAntrianTelemedicEntity] END <<<");
        return null;
    }

    @Override
    public BigDecimal insertResepOnline(String idTransaksiOnline, List<TransaksiObatDetail> listObat) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.insertResepOnline] START >>>");
        BigDecimal hargaTotal = new BigDecimal(0);
        Timestamp now = new Timestamp(System.currentTimeMillis());

        for (TransaksiObatDetail obatDetail : listObat){


            Map hsCriteria = new HashMap();
            hsCriteria.put("id_obat", obatDetail.getIdObat());
            List<MtSimrsHargaObatEntity> hargaObatEntities = hargaObatDao.getByCriteria(hsCriteria);
            BigDecimal harga = new BigDecimal(0);

            if (hargaObatEntities.size() > 0){

                MtSimrsHargaObatEntity hargaObatEntity = hargaObatEntities.get(0);
                if (hargaObatEntity != null){
                    harga = hargaObatEntity.getHargaJual();
                }

                ItSimrsResepOnlineEntity resepOnlineEntity = new ItSimrsResepOnlineEntity();
                resepOnlineEntity.setId(getSeqResepOnline());
                resepOnlineEntity.setIdObat(obatDetail.getIdObat());
                resepOnlineEntity.setIdTransaksiOnline(idTransaksiOnline);
                resepOnlineEntity.setQty(obatDetail.getQty());
                resepOnlineEntity.setHarga(harga);
                resepOnlineEntity.setSubTotal(harga.multiply(new BigDecimal(resepOnlineEntity.getQty())));
                resepOnlineEntity.setIdPelayanan(obatDetail.getIdPelayanan());
                resepOnlineEntity.setFlag("Y");
                resepOnlineEntity.setAction("C");
                resepOnlineEntity.setCreatedDate(obatDetail.getCreatedDate());
                resepOnlineEntity.setCreatedWho(obatDetail.getCreatedWho());
                resepOnlineEntity.setLastUpdate(obatDetail.getLastUpdate());
                resepOnlineEntity.setLastUpdateWho(obatDetail.getLastUpdateWho());
                resepOnlineEntity.setTtdDokter(obatDetail.getTtdDokter());
                resepOnlineEntity.setIdDokter(obatDetail.getCreatedWho());
                resepOnlineEntity.setKeterangan(obatDetail.getKeterangan());

                hargaTotal = hargaTotal.add(resepOnlineEntity.getSubTotal());

                try {
                    resepOnlineDao.addAndSave(resepOnlineEntity);
                } catch (HibernateException e){
                    logger.error("[VerifikatorPembayaranBoImpl.insertResepOnline] ERROR. ", e);
                    throw new GeneralBOException("[VerifikatorPembayaranBoImpl.insertResepOnline] ERROR. ", e);
                }
            }
        }

        // create struk jika asuransi
        PembayaranOnline pembayaranOnline = new PembayaranOnline();
        pembayaranOnline.setId(idTransaksiOnline);
        ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = new ItSimrsAntrianTelemedicEntity();
        List<ItSimrsPembayaranOnlineEntity> pembayaranOnlineEntities = getPembayaranOnlineEntityByCriteria(pembayaranOnline);
        if (pembayaranOnlineEntities.size() > 0){
            ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = pembayaranOnlineEntities.get(0);
            antrianTelemedicEntity = getAntrianTelemedicEntityById(pembayaranOnlineEntity.getIdAntrianTelemedic());

            if (antrianTelemedicEntity != null){
                if ("asuransi".equalsIgnoreCase(antrianTelemedicEntity.getIdJenisPeriksaPasien())){
                    ItSimrsStrukAsuransiEntity strukAsuransiEntity = new ItSimrsStrukAsuransiEntity();
                    strukAsuransiEntity.setId(generateIdStrukAsuransi(antrianTelemedicEntity.getBranchId()));
                    strukAsuransiEntity.setJenis("confirmation");
                    strukAsuransiEntity.setIdAntrianTelemedic(antrianTelemedicEntity.getId());
                    strukAsuransiEntity.setBranchId(antrianTelemedicEntity.getBranchId());
                    strukAsuransiEntity.setFlag("Y");
                    strukAsuransiEntity.setAction("C");
                    strukAsuransiEntity.setCreatedDate(now);
                    strukAsuransiEntity.setCreatedWho(pembayaranOnlineEntity.getCreatedWho());
                    strukAsuransiEntity.setLastUpdate(now);
                    strukAsuransiEntity.setLastUpdateWho(pembayaranOnlineEntity.getLastUpdateWho());

                    try {
                        strukAsuransiDao.addAndSave(strukAsuransiEntity);
                    } catch (HibernateException e){
                        logger.error("[VerifikatorPembayaranBoImpl.insertResepOnline] ERROR When Update StrukAsuransi. ", e);
                        throw new GeneralBOException("[VerifikatorPembayaranBoImpl.insertResepOnline] ERROR When Update StrukAsuransi.. ", e);
                    }
                }
            }
        }

        // notifikasi
        createNotifikasiAdmin(antrianTelemedicEntity.getId(), "tele", antrianTelemedicEntity.getBranchId(), antrianTelemedicEntity.getCreatedWho(), now, "Resep telah dipuat Oleh Dokter. ID "+antrianTelemedicEntity.getId());

        List<ItSimrsPembayaranOnlineEntity> listPembayaran;
        Map hsCriteria = new HashMap();
        hsCriteria.put("id", idTransaksiOnline);

        try{
           listPembayaran = verifikatorPembayaranDao.getByCriteria(hsCriteria);
        } catch (GeneralBOException e) {
            logger.error("[VerifikatorPembayaranBoImpl.insertResepOnline] ERROR. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.insertResepOnline] ERROR. ", e);
        }

        if (listPembayaran.size() > 0){
            ItSimrsPembayaranOnlineEntity newPembayaran = listPembayaran.get(0);

            // jika pasien umum ditambahakan generate 3 digit pada nominal
            boolean isUmum = false;
            antrianTelemedicEntity = getAntrianTelemedicEntityById(newPembayaran.getIdAntrianTelemedic());
            if (antrianTelemedicEntity != null)
                isUmum = "umum".equalsIgnoreCase(antrianTelemedicEntity.getIdJenisPeriksaPasien());

            if (isUmum)
                newPembayaran.setNominal(hargaTotal.add(generateRandomNumBigDecimal()));
            else
                newPembayaran.setNominal(hargaTotal);

            // END;

            newPembayaran.setLastUpdate(now);
            newPembayaran.setLastUpdateWho("admin");

            try {
                verifikatorPembayaranDao.updateAndSave(newPembayaran);
            } catch (GeneralBOException e) {
                logger.error("[VerifikatorPembayaranBoImpl.insertResepOnline] ERROR. ", e);
                throw new GeneralBOException("[VerifikatorPembayaranBoImpl.insertResepOnline] ERROR. ", e);
            }
        }

        logger.info("[VerifikatorPembayaranBoImpl.insertResepOnline] END <<<");
        return hargaTotal;
    }

    @Override
    public void createPembayaranResep(AntrianTelemedic bean, List<TransaksiObatDetail> listObat) throws GeneralBOException {
        logger.info("[VerifikatorPembayaranBoImpl.createPembayaranResep] START >>>");

        ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = new ItSimrsPembayaranOnlineEntity();
        pembayaranOnlineEntity = new ItSimrsPembayaranOnlineEntity();
        pembayaranOnlineEntity.setId(getSeqPembayaranOnline(bean.getId()));
        pembayaranOnlineEntity.setIdAntrianTelemedic(bean.getId());
        pembayaranOnlineEntity.setLastUpdate(bean.getLastUpdate());
        pembayaranOnlineEntity.setFlag(bean.getFlag());
        pembayaranOnlineEntity.setCreatedDate(bean.getCreatedDate());
        pembayaranOnlineEntity.setCreatedWho(bean.getCreatedWho());
        pembayaranOnlineEntity.setLastUpdate(bean.getLastUpdate());
        pembayaranOnlineEntity.setLastUpdateWho(bean.getLastUpdateWho());
        pembayaranOnlineEntity.setKeterangan("resep");
        pembayaranOnlineEntity.setNominal(new BigDecimal(0));

        if (listObat.size() > 0){
            BigDecimal harga = insertResepOnline(pembayaranOnlineEntity.getId(), listObat);
            pembayaranOnlineEntity.setNominal(harga);
        }

        try {
            verifikatorPembayaranDao.addAndSave(pembayaranOnlineEntity);
        } catch (HibernateException e){
            logger.error("[TelemedicBoIml.createPembayaranResep] ERROR. ",e);
            throw new GeneralBOException("[TelemedicBoIml.createPembayaranResep] ERROR. ",e);
        }

        logger.info("[VerifikatorPembayaranBoImpl.createPembayaranResep] START >>>");
    }

    @Override
    public List<PengirimanObat> getHistoryPengiriman(String idKurir) {
        logger.info("[TelemedicBoImpl.getHistoryPengiriman] START <<<");

        List<PengirimanObat> listOfResult = new ArrayList<>();

        try {
            listOfResult = pengirimanObatDao.getHistoryPengiriman(idKurir);
        }catch (GeneralBOException e) {
            logger.error("[TelemedicBoImpl.getListPengirimanById] ERROR. ", e);
            throw new GeneralBOException("[TelemedicBoImpl.getListPengirimanById] ERROR. ", e);
        }

        logger.info("[TelemedicBoImpl.getHistoryPengiriman] END <<<");
        return listOfResult;

    }


    @Override
    public List<PengirimanObat> getPengirimanByCriteria(PengirimanObat bean) throws GeneralBOException{
        logger.info("[TelemedicBoImpl.getPembayaranByCriteria] START <<<");

        List<ItSimrsPengirimanObatEntity> result = new ArrayList<>();
        List<PengirimanObat> resultReturn = new ArrayList<>();

        Map hsCriteria = new HashMap();
        if (bean.getId() != null) {
            hsCriteria.put("id", bean.getId());
        }
        if (bean.getIdKurir() != null) {
            hsCriteria.put("id_kurir", bean.getIdKurir());
        }
        if (bean.getIdPasien() != null) {
            hsCriteria.put("id_pasien", bean.getIdPasien());
        }
        if (bean.getIdPelayanan() != null) {
            hsCriteria.put("id_pelayanan", bean.getIdPelayanan());
        }
        if (bean.getIdResep() != null) {
            hsCriteria.put("id_resep", bean.getIdResep());
        }
        hsCriteria.put("flag", "Y");

        try {
            result = pengirimanObatDao.getByCriteria(hsCriteria);

        } catch (GeneralBOException e) {
            logger.error("[VerifikatorPembayaranBoImpl.insertResepOnline] ERROR. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranBoImpl.insertResepOnline] ERROR. ", e);
        }

        for (ItSimrsPengirimanObatEntity item : result) {
            PengirimanObat pengirimanObat = new PengirimanObat();
            pengirimanObat.setId(item.getId());
            pengirimanObat.setIdPasien(item.getIdPasien());
            pengirimanObat.setIdPelayanan(item.getIdPelayanan());
            pengirimanObat.setIdResep(item.getIdResep());
            pengirimanObat.setIdKurir(item.getIdKurir());
            pengirimanObat.setBranchId(item.getBranchId());
            pengirimanObat.setDesaId(item.getDesaId());
            pengirimanObat.setNoTelp(item.getNoTelp());
            pengirimanObat.setFlagDiterimaPasien(item.getFlagDiterimaPasien());
            pengirimanObat.setFlagPickup(item.getFlagPickup());
            pengirimanObat.setFlag(item.getFlag());
            pengirimanObat.setAction(item.getAction());
            pengirimanObat.setCreatedWho(item.getCreatedWho());
            pengirimanObat.setCreatedDate(item.getCreatedDate());
            pengirimanObat.setLastUpdate(item.getLastUpdate());
            pengirimanObat.setLastUpdateWho(item.getLastUpdateWho());
            pengirimanObat.setAlamat(item.getAlamat());
            pengirimanObat.setLat(item.getLat());
            pengirimanObat.setLon(item.getLon());
            resultReturn.add(pengirimanObat);
        }


        logger.info("[TelemedicBoImpl.getPembayaranByCriteria] END <<<");
        return resultReturn;

    }

    @Override
    public List<PengirimanObat> getListPengirimanById(String idKurir, String idPasien) throws GeneralBOException {
        logger.info("[TelemedicBoImpl.getListPengirimanById] START <<<");

        List<PengirimanObat> listOfResult = new ArrayList<>();

        try {
            listOfResult = pengirimanObatDao.getPengirimanById(idKurir, idPasien);
        }catch (GeneralBOException e) {
            logger.error("[TelemedicBoImpl.getListPengirimanById] ERROR. ", e);
            throw new GeneralBOException("[TelemedicBoImpl.getListPengirimanById] ERROR. ", e);
        }

        logger.info("[TelemedicBoImpl.getListPengirimanById] END <<<");
        return listOfResult;
    }

    @Override
    public void saveEditPengirimanObat(PengirimanObat bean) throws GeneralBOException{
        logger.info("[TelemedicBoImpl.saveEditPengirimanObat] END <<<");

        ItSimrsPengirimanObatEntity entity = new ItSimrsPengirimanObatEntity();

        entity.setId(bean.getId());
        entity.setIdKurir(bean.getIdKurir());
        entity.setIdPasien(bean.getIdPasien());
        entity.setIdPelayanan(bean.getIdPelayanan());
        entity.setIdResep(bean.getIdResep());
        entity.setBranchId(bean.getBranchId());
        entity.setDesaId(bean.getDesaId());
        entity.setAlamat(bean.getAlamat());
        entity.setFlagDiterimaPasien(bean.getFlagDiterimaPasien());
        entity.setFlagPickup(bean.getFlagPickup());
        entity.setFlag(bean.getFlag());
        entity.setAction(bean.getAction());
        entity.setNoTelp(bean.getNoTelp());
        entity.setCreatedDate(bean.getCreatedDate());
        entity.setCreatedWho(bean.getCreatedWho());
        entity.setLastUpdate(bean.getLastUpdate());
        entity.setLastUpdateWho(bean.getLastUpdateWho());
        entity.setLat(bean.getLat());
        entity.setLon(bean.getLon());
        entity.setFotoKirim(bean.getFotoKirim());
        entity.setKeterangan(bean.getKeterangan());

        try {
            pengirimanObatDao.updateAndSave(entity);
        } catch (GeneralBOException e){
            logger.error("[TelemedicBoImpl.saveEditPengirimanObat] ERROR. ", e);
            throw new GeneralBOException("[TelemedicBoImpl.saveEditPengirimanObat] ERROR. ", e);
        }

        logger.info("[TelemedicBoImpl.saveEditPengirimanObat] END <<<");
    }

    @Override
    public void saveAddPengirimanObat(PengirimanObat bean) throws GeneralBOException{
        logger.info("[TelemedicBoImpl.saveEditPengirimanObat] Start >>>");

        ItSimrsPengirimanObatEntity entity = new ItSimrsPengirimanObatEntity();

        entity.setId("PIO" + pengirimanObatDao.getNextSeq());
        entity.setIdKurir(bean.getIdKurir());
        entity.setIdPasien(bean.getIdPasien());
        entity.setIdPelayanan(bean.getIdPelayanan());
        entity.setIdResep(bean.getIdResep());
        entity.setBranchId(bean.getBranchId());
        entity.setDesaId(bean.getDesaId());
        entity.setAlamat(bean.getAlamat());
        entity.setFlagDiterimaPasien(bean.getFlagDiterimaPasien());
        entity.setFlagPickup(bean.getFlagPickup());
        entity.setFlag(bean.getFlag());
        entity.setAction(bean.getAction());
        entity.setNoTelp(bean.getNoTelp());
        entity.setCreatedDate(bean.getCreatedDate());
        entity.setCreatedWho(bean.getCreatedWho());
        entity.setLastUpdate(bean.getLastUpdate());
        entity.setLastUpdateWho(bean.getLastUpdateWho());
        entity.setLat(bean.getLat());
        entity.setLon(bean.getLon());

        try {
            pengirimanObatDao.addAndSave(entity);
        } catch (GeneralBOException e){
            logger.error("[TelemedicBoImpl.saveEditPengirimanObat] ERROR. ", e);
            throw new GeneralBOException("[TelemedicBoImpl.saveEditPengirimanObat] ERROR. ", e);
        }

        logger.info("[TelemedicBoImpl.saveEditPengirimanObat] END <<<");
    }

    private BigDecimal generateRandomNumBigDecimal() {
        int min = 1;
        int max = 500;
        int random_int = (int)(Math.random() * (max - min + 1) + min);
        return new BigDecimal(random_int);
    }

    private String getSeqResepOnline(){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return "RO" + df.format(new Date()) + resepOnlineDao.getNextId();

    }
    private String getSeqTelemedic(){
        return telemedicDao.getNextSeq();
    }
    private String getSeqPembayaranOnline(String id){
        return id + "INV" + verifikatorPembayaranDao.getNextSeq();
    }

    @Override
    public List<AntrianTelemedic> getHistoryByIdPasien(String idPasien, String flagEresep) throws GeneralBOException {
        logger.info("[TelemedicBoImpl.getHistoryByIdPasien] START <<<");
        List<AntrianTelemedic> listAntrianTelemedic = new ArrayList<>();

        if  (flagEresep == null || !flagEresep.equalsIgnoreCase("Y")) {
            try {
                listAntrianTelemedic = telemedicDao.getHistoryByIdPasien(idPasien);
            } catch (GeneralBOException e) {
                logger.error("[TelemedicBoImpl.getHistoryByIdPasien] ERROR. ", e);
                throw new GeneralBOException("[TelemedicBoImpl.getHistoryByIdPasien] ERROR. ", e);
            }

            for (AntrianTelemedic item : listAntrianTelemedic){
                BatalTelemedic batalTelemedic = getBatalTemedicByIdAntrian(item.getId(), item.getIdDokter(), item.getIdPelayanan(), item.getCreatedDate());
                if (batalTelemedic != null){
                    item.setFlagBatalDokter("Y");
                    item.setIdBatalDokterTelemedic(batalTelemedic.getIdDokterBatal());
                    ItSimrsDokterBatalTelemedicEntity dokterBatalTelemedicEntity = batalDokterTelemedicDao.getById("id",batalTelemedic.getIdDokterBatal());
                    item.setAlasan(dokterBatalTelemedicEntity.getAlasan());
                }
            }
        } else {
            try {
                listAntrianTelemedic = telemedicDao.getHistoryEobatByIdPasien(idPasien);
            } catch (GeneralBOException e) {
                logger.error("[TelemedicBoImpl.getHistoryByIdPasien] ERROR. ", e);
                throw new GeneralBOException("[TelemedicBoImpl.getHistoryByIdPasien] ERROR. ", e);
            }
        }

        logger.info("[TelemedicBoImpl.getHistoryByIdPasien] END <<<");
        return listAntrianTelemedic;
    }
    private String generateIdStrukAsuransi(String branchId) throws GeneralBOException {
        return "STA"+branchId+strukAsuransiDao.getNextSeq();
    }

    @Override
    public void updateFlagApproveStrukAsuransi(StrukAsuransi bean) throws GeneralBOException {
        logger.info("[TelemedicBoImpl.saveEditStrukAsuransi] START <<<");

        List<ItSimrsStrukAsuransiEntity> list;

        try {
          list = getStrukAsuransi(bean);
        } catch (GeneralBOException e) {
            logger.error("[TelemedicBoImpl.saveEditStrukAsuransi] ERROR. ", e);
            throw new GeneralBOException("[TelemedicBoImpl.saveEditStrukAsuransi] ERROR. ", e);
        }

        if (list != null) {
            for (ItSimrsStrukAsuransiEntity item : list) {
                if (item.getJenis().equalsIgnoreCase("authorization")) {
                    item.setApproveFlag("Y");

                    try {
                        strukAsuransiDao.updateAndSave(item);
                    } catch (GeneralBOException e){
                        logger.error("[TelemedicBoImpl.saveEditStrukAsuransi] ERROR. ", e);
                        throw new GeneralBOException("[TelemedicBoImpl.saveEditStrukAsuransi] ERROR. ", e);
                    }
                }

                if (item.getJenis().equalsIgnoreCase("confirmation")) {
                    item.setApproveFlag("Y");

                    try {
                        strukAsuransiDao.updateAndSave(item);
                    } catch (GeneralBOException e){
                        logger.error("[TelemedicBoImpl.saveEditStrukAsuransi] ERROR. ", e);
                        throw new GeneralBOException("[TelemedicBoImpl.saveEditStrukAsuransi] ERROR. ", e);
                    }
                }
            }


        }

        logger.info("[TelemedicBoImpl.saveEditStukAsuransi] END <<<");
    }

    @Override
    public List<ItSimrsStrukAsuransiEntity> getStrukAsuransi(StrukAsuransi bean) throws GeneralBOException {
        logger.info("[TelemedicBoImpl.getStrukAsuransi] START <<<");

        List<ItSimrsStrukAsuransiEntity> list;

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_antrian_telemedic", bean.getIdAntrianTelemedic());

        try {
            list = strukAsuransiDao.getByCriteria(hsCriteria);
        } catch (GeneralBOException e) {
            logger.error("[TelemedicBoImpl.saveEditStrukAsuransi] ERROR. ", e);
            throw new GeneralBOException("[TelemedicBoImpl.saveEditStrukAsuransi] ERROR. ", e);
        }

        logger.info("[TelemedicBoImpl.getStrukAsuransi] END <<<");
        return list;
    }
    private String getSeqIdDokterBatal(){
        return "BTD" + CommonUtil.stDateSeq() + batalDokterTelemedicDao.getNextSeq();
    }
    private String getSeqIdBatalTelemedic(){
        return "BTT" + CommonUtil.stDateSeq() + batalDokterTelemedicDao.getNextSeq();
    }

    // parameter dalam bean : idPelayanan, idDokter, lastUpdate, lastUpdateWho
    @Override
    public List<ItSimrsAntrianTelemedicEntity> processBatalDokter(AntrianTelemedic bean, String alasan) throws GeneralBOException{
        logger.info("[TelemedicBoImpl.processBatalDokter] Start >>>");

        List<ItSimrsAntrianTelemedicEntity> listReturn = new ArrayList<>();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        // insert into batal dokter telemedic
        ItSimrsDokterBatalTelemedicEntity dokterBatalTelemedicEntity = new ItSimrsDokterBatalTelemedicEntity();
        dokterBatalTelemedicEntity.setId(getSeqIdDokterBatal());
        dokterBatalTelemedicEntity.setIdPelayanan(bean.getIdPelayanan());
        dokterBatalTelemedicEntity.setIdDokter(bean.getIdDokter());
        dokterBatalTelemedicEntity.setAlasan(alasan);
        dokterBatalTelemedicEntity.setFlag("Y");
        dokterBatalTelemedicEntity.setAction("C");
        dokterBatalTelemedicEntity.setCreatedDate(bean.getLastUpdate());
        dokterBatalTelemedicEntity.setCreatedWho(bean.getLastUpdateWho());
        dokterBatalTelemedicEntity.setLastUpdate(bean.getLastUpdate());
        dokterBatalTelemedicEntity.setLastUpdateWho(bean.getLastUpdateWho());
        dokterBatalTelemedicEntity.setBatalDate(CommonUtil.convertTimestampToDate(bean.getLastUpdate()));
        insertIntoBatalDokter(dokterBatalTelemedicEntity);

        List<ItSimrsAntrianTelemedicEntity> antrianTelemedicEntities = getListEntityByCriteria(bean);
        if (antrianTelemedicEntities.size() > 0){

            // update flag N antrian telemedic
            for (ItSimrsAntrianTelemedicEntity  antrianTelemedicEntity : antrianTelemedicEntities){

                //cek tanggal, hanya batal telemedic untuk tanggal hari ini
                String dateNow = CommonUtil.convertTimestampToString(now);
                String dateTele = CommonUtil.convertTimestampToString(antrianTelemedicEntity.getCreatedDate());

                if(!"SK".equalsIgnoreCase(antrianTelemedicEntity.getStatus()) && dateNow.equalsIgnoreCase(dateTele)) {
                    antrianTelemedicEntity.setFlag("N");
                    antrianTelemedicEntity.setAction("U");
                    antrianTelemedicEntity.setLastUpdate(bean.getLastUpdate());
                    antrianTelemedicEntity.setLastUpdateWho(bean.getLastUpdateWho());

                    try {
                        telemedicDao.updateAndSave(antrianTelemedicEntity);
                    } catch (HibernateException e){
                        logger.error("[TelemedicBoImpl.insertIntoBatalDokter] ERROR. update antrian telemedic. ", e);
                        throw new GeneralBOException("[TelemedicBoImpl.insertIntoBatalDokter] ERROR. update antrian telemedic. ", e);
                    }

                    // update flag N pembayaran online
                    PembayaranOnline pembayaranOnline = new PembayaranOnline();
                    pembayaranOnline.setIdAntrianTelemedic(antrianTelemedicEntity.getId());
                    List<ItSimrsPembayaranOnlineEntity> pembayaranOnlineEntities = getPembayaranOnlineEntityByCriteria(pembayaranOnline);
                    if (pembayaranOnlineEntities.size() > 0){
                        for (ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity : pembayaranOnlineEntities){

                            pembayaranOnlineEntity.setFlag("N");
                            pembayaranOnlineEntity.setAction("U");
                            pembayaranOnlineEntity.setLastUpdate(bean.getLastUpdate());
                            pembayaranOnlineEntity.setLastUpdateWho(bean.getLastUpdateWho());

                            try {
                                verifikatorPembayaranDao.updateAndSave(pembayaranOnlineEntity);
                            } catch (HibernateException e){
                                logger.error("[TelemedicBoImpl.insertIntoBatalDokter] ERROR. update pembayaran online. ", e);
                                throw new GeneralBOException("[TelemedicBoImpl.insertIntoBatalDokter] ERROR. update pembayaran online. ", e);
                            }
                        }
                    }

                    // insert into batal dokter telemedic
                    ItSimrsBatalTelemedicEntity batalTelemedicEntity = new ItSimrsBatalTelemedicEntity();
                    batalTelemedicEntity.setId(getSeqIdBatalTelemedic());
                    batalTelemedicEntity.setIdDokterBatal(dokterBatalTelemedicEntity.getId());
                    batalTelemedicEntity.setIdAntrianTelemedic(antrianTelemedicEntity.getId());
                    batalTelemedicEntity.setFlag("Y");
                    batalTelemedicEntity.setAction("C");
                    batalTelemedicEntity.setCreatedDate(bean.getLastUpdate());
                    batalTelemedicEntity.setCreatedWho(bean.getLastUpdateWho());
                    batalTelemedicEntity.setLastUpdate(bean.getLastUpdate());
                    batalTelemedicEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    insertIntoBatalTelemedic(batalTelemedicEntity);

                    // push notif
                    listReturn.add(antrianTelemedicEntity);
                }
            }
        }
        logger.info("[TelemedicBoImpl.processBatalDokter] END <<<");
        return listReturn;

    }

    private void insertIntoBatalDokter(ItSimrsDokterBatalTelemedicEntity dokterBatalTelemedicEntity) throws GeneralBOException{
        try {
            batalDokterTelemedicDao.addAndSave(dokterBatalTelemedicEntity);
        } catch (HibernateException e){
            logger.error("[TelemedicBoImpl.insertIntoBatalDokter] ERROR. ", e);
            throw new GeneralBOException("[TelemedicBoImpl.insertIntoBatalDokter] ERROR. ", e);
        }
    }

    private void insertIntoBatalTelemedic(ItSimrsBatalTelemedicEntity batalTelemedicEntity) throws GeneralBOException{
        try {
            batalTelemedicDao.addAndSave(batalTelemedicEntity);
        } catch (HibernateException e){
            logger.error("[TelemedicBoImpl.insertIntoBatalTelemedic] ERROR. ", e);
            throw new GeneralBOException("[TelemedicBoImpl.insertIntoBatalTelemedic] ERROR. ", e);
        }
    }

    @Override
    public void confirmKembalian(BatalTelemedic bean) throws GeneralBOException {
        logger.info("[TelemedicBoImpl.confirmKembalian] START >>>");

        ItSimrsBatalTelemedicEntity batalTelemedicEntity = batalTelemedicDao.getById("id", bean.getId());
        if (batalTelemedicEntity != null){

            // update status 4 to detail checkup
            if (batalTelemedicEntity.getIdAntrianTelemedic() != null){
                ItSimrsHeaderChekupEntity headerChekupEntity = headerCheckupDao.getById("idAntrianOnline", batalTelemedicEntity.getIdAntrianTelemedic());
                if (headerChekupEntity != null){

                    Map hsCriteria = new HashMap();
                    hsCriteria.put("no_checkup", headerChekupEntity.getNoCheckup());

                    List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntities = checkupDetailDao.getByCriteria(hsCriteria);
                    if (detailCheckupEntities.size() > 0){
                        for (ItSimrsHeaderDetailCheckupEntity detailCheckupEntity : detailCheckupEntities){
                            detailCheckupEntity.setStatusPeriksa("4");
                            detailCheckupEntity.setAction("Y");
                            detailCheckupEntity.setLastUpdate(bean.getLastUpdate());
                            detailCheckupEntity.setLastUpdateWho(bean.getLastUpdateWho());
                            detailCheckupEntity.setKeteranganSelesai("selesai");

                            try {
                                checkupDetailDao.updateAndSave(detailCheckupEntity);
                            } catch (HibernateException e){
                                logger.error("[TelemedicBoImpl.confirmKembalian] ERROR. update batal telemedic to detail checkup. ", e);
                                throw new GeneralBOException("[TelemedicBoImpl.confirmKembalian] ERROR. update batal telemedic to detail checkup. ", e);
                            }
                        }
                    }
                }
            }

            batalTelemedicEntity.setKembaliKonsultasi(bean.getKembaliKonsultasi() == null ? batalTelemedicEntity.getKembaliKonsultasi() : bean.getKembaliKonsultasi());
            batalTelemedicEntity.setKembaliResep(bean.getKembaliResep() == null ? batalTelemedicEntity.getKembaliResep() : bean.getKembaliResep());
            batalTelemedicEntity.setFlagKembaliKonsultasi(bean.getFlagKembaliKonsultasi());
            batalTelemedicEntity.setFlagKembaliResep(bean.getFlagKembaliResep());
            batalTelemedicEntity.setAction("U");
            batalTelemedicEntity.setLastUpdate(bean.getLastUpdate());
            batalTelemedicEntity.setLastUpdateWho(bean.getLastUpdateWho());

            try {
                batalTelemedicDao.updateAndSave(batalTelemedicEntity);
            } catch (HibernateException e){
                logger.error("[TelemedicBoImpl.confirmKembalian] ERROR. update batal telemedic. ", e);
                throw new GeneralBOException("[TelemedicBoImpl.confirmKembalian] ERROR. update batal telemedic. ", e);
            }
        }
        logger.info("[TelemedicBoImpl.confirmKembalian] END <<<");
    }

    @Override
    public void updateNoJurnalBatalDokter(String idBatalDokter, String noJurnal) {
        logger.info("[TelemedicBoImpl.updateNoJurnalBatalDokter] START >>>");
        ItSimrsBatalTelemedicEntity batalTelemedicEntity = batalTelemedicDao.getById("id", idBatalDokter);
        if (batalTelemedicEntity != null){

            batalTelemedicEntity.setNoJurnal(noJurnal);
            try {
                batalTelemedicDao.updateAndSave(batalTelemedicEntity);
            } catch (HibernateException e){
                logger.error("[TelemedicBoImpl.updateNoJurnalBatalDokter] ERROR. update batal telemedic. ", e);
                throw new GeneralBOException("[TelemedicBoImpl.updateNoJurnalBatalDokter] ERROR. update batal telemedic. ", e);
            }
        }
        logger.info("[TelemedicBoImpl.updateNoJurnalBatalDokter] END <<<");
    }

    @Override
    public ItSimrsBatalTelemedicEntity getEnitityBatalTelemedicById(String idBatalTelemedic) {
        logger.info("[TelemedicBoImpl.getEnitityBatalTelemedicById] START >>>");
        return batalTelemedicDao.getById("id", idBatalTelemedic);
    }

    @Override
    public void updateBankCoa(String idTele, String bankCoa) {
        logger.info("[TelemedicBoImpl.updateBankCoa] START >>>");

        List<ItSimrsAntrianTelemedicEntity> entities = new ArrayList<>();

        Map hsCriteria = new HashMap();
        hsCriteria.put("id", idTele);

        try {
           entities = telemedicDao.getByCriteria(hsCriteria);
        } catch (GeneralBOException e){
            logger.error("[TelemedicBoImpl.updateBankCoa] ERROR. ", e);
        }

        if  (entities.size() > 0) {

            entities.get(0).setKodeBank(bankCoa);
            try {
                telemedicDao.updateAndSave(entities.get(0));
            } catch (GeneralBOException e){
                logger.error("[TelemedicBoImpl.updateBankCoa] ERROR. ", e);
            }

        }
        logger.info("[TelemedicBoImpl.updateNoJurnalBatalDokter] END <<<");
    }

    // notifikasi
    @Override
    public void createNotifikasiAdmin(String idItem, String roleId, String branchId, String userName, Timestamp createdDate, String keterangan) {

        ItNotifikasiAdminTelemedicEntity notif = new ItNotifikasiAdminTelemedicEntity();
        notif.setId(generateIdNotif(branchId));
        notif.setIdItem(idItem);
        notif.setRoleId(roleId);
        notif.setBranchId(branchId);
        notif.setKeterangan(keterangan);
        notif.setFlag("Y");
        notif.setAction("C");
        notif.setCreatedWho(userName);
        notif.setLastUpdateWho(userName);
        notif.setCreatedDate(createdDate);
        notif.setLastUpdate(createdDate);

        try {
            notifikasiAdminTelemedicDao.addAndSave(notif);
        } catch (HibernateException e){
            logger.error("[NotifikasiAdminBoImpl.createNotifikasiAdmin] ERROR. ", e);
            throw new GeneralBOException("[NotifikasiAdminBoImpl.createNotifikasiAdmin] ERROR. ", e);
        }
    }

    @Override
    public CrudResponse insertVideoRm(String idDetailCheckup, String path, String tipe) {

        CrudResponse crudResponse = new CrudResponse();

        Timestamp now = new Timestamp(System.currentTimeMillis());

        ItSimrsVideoRmEntity itSimrsVideoRmEntity = new ItSimrsVideoRmEntity();
        itSimrsVideoRmEntity.setIdVideoRm("VDO"+videoRmDao.getNextSeq());
        itSimrsVideoRmEntity.setIdAntrianTelemedic("");
        itSimrsVideoRmEntity.setIdDetailCheckup(idDetailCheckup);
        itSimrsVideoRmEntity.setPath(path);
        itSimrsVideoRmEntity.setAction("C");
        itSimrsVideoRmEntity.setFlag("Y");
        itSimrsVideoRmEntity.setCreatedDate(now);
        itSimrsVideoRmEntity.setLastUpdate(now);
        itSimrsVideoRmEntity.setCreatedWho("admin");
        itSimrsVideoRmEntity.setLastUpdateWho("admin");
        itSimrsVideoRmEntity.setTipeVideo(tipe);

        try {
            videoRmDao.addAndSave(itSimrsVideoRmEntity);
            crudResponse.setMsg("Success");
        } catch (GeneralBOException e){
            logger.error("[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
            throw new GeneralBOException("[[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
        }

        return crudResponse;
    }

    private List<ItSimrsVideoRmEntity> getVideoRm(String idDetailCheckup) {
        List<ItSimrsVideoRmEntity> list;

        Map hsCriteria = new HashMap();
        hsCriteria.put("id_detail_checkup", idDetailCheckup);

        try {
            list = videoRmDao.getByCriteria(hsCriteria);
        } catch (GeneralBOException e){
            logger.error("[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
            throw new GeneralBOException("[[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
        }

        return list;

    }

    @Override
    public CrudResponse updateVideoRmOnDetailCheckup(String idDetailCheckup, String path) {
        CrudResponse crudResponse = new CrudResponse();

        List<ItSimrsVideoRmEntity> itSimrsVideoRmEntityList = null;

        itSimrsVideoRmEntityList = getVideoRm(idDetailCheckup);
        if (itSimrsVideoRmEntityList != null) {

            //check jika terdapat lebih dari satu video, maka video digabung
            if (itSimrsVideoRmEntityList.size() > 1) {

               path = gabungVideo(itSimrsVideoRmEntityList);
               insertVideoRm(idDetailCheckup, path, "joined");

            }

            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = null;

            try {
                detailCheckupEntity = checkupDetailDao.getById("idDetailCheckup", idDetailCheckup, "Y");
            } catch (GeneralBOException e) {
                logger.info("[CheckupDetailBoImpl.editVideoRm] Error when editVideoRm ", e);
            }

            if (detailCheckupEntity != null) {
                detailCheckupEntity.setVideoRm(path);
                try {
                    checkupDetailDao.updateAndSave(detailCheckupEntity);
                    crudResponse.setMsg("Success");
                } catch (GeneralBOException e) {
                    logger.info("[CheckupDetailBoImpl.editVideoRm] Error when editVideoRm ", e);
                }
            }

        }

        return crudResponse;
    }

    @Override
    public String cobaGabung(String path1, String path2) {

        List<String> listPath = new ArrayList<>();
        listPath.add(path1);
        listPath.add(path2);

        List<Movie> listMovie = new ArrayList<>();


        for (int i = 0; i < listPath.size(); i++) {
            Movie movie = new Movie();
            String path = listPath.get(i);
            try {
                movie = MovieCreator.build(CommonUtil.getPropertyParams("upload.folder") + path);
            } catch (IOException e){
                logger.error("[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
                throw new GeneralBOException("[[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
            }

            listMovie.add(movie);
        }

        Movie newMovie = listMovie.get(0);

        for (int i = 1; i < listMovie.size(); i++) {
            List<Track> tracks = listMovie.get(i).getTracks();
            for (Track item : tracks) {
                try {
                    newMovie.addTrack(new AppendTrack(item, item));
                } catch (IOException e){
                    logger.error("[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
                    throw new GeneralBOException("[[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
                }
            }
        }

        Container out = new DefaultMp4Builder().build(newMovie);

        String path = "/upload/video_rm/coba.mp4";

        try {
            FileOutputStream fos = new FileOutputStream(new File(CommonUtil.getPropertyParams("upload.folder")+path));
            for ( Box item : out.getBoxes()) {
                try {
                    item.getBox(fos.getChannel());
                } catch (IOException e){
                    logger.error("[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
                    throw new GeneralBOException("[[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
                }
            }

            try {
                fos.close();
            } catch (IOException e){
                logger.error("[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
                throw new GeneralBOException("[[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
            }
        } catch (FileNotFoundException e) {
            logger.error("[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
            throw new GeneralBOException("[[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
        }
        return path;


    }

    private String gabungVideo(List<ItSimrsVideoRmEntity> listVideoRm) {

        List<Movie> listMovie = new ArrayList<>();

        for (int i = 0; i < listVideoRm.size(); i++) {
            Movie movie = new Movie();
            String path = listVideoRm.get(i).getPath();
            try {
               movie = MovieCreator.build(CommonUtil.getPropertyParams("upload.folder") + path);
            } catch (IOException e){
                logger.error("[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
                throw new GeneralBOException("[[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
            }

            listMovie.add(movie);
        }

        Movie newMovie = listMovie.get(0);

       for (int i = 1; i < listMovie.size(); i++) {
           List<Track> tracks = listMovie.get(i).getTracks();
           for (Track item : tracks) {
               try {
                   newMovie.addTrack(new AppendTrack(item, item));
               } catch (IOException e){
                   logger.error("[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
                   throw new GeneralBOException("[[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
               }
           }
       }

       Container out = new DefaultMp4Builder().build(newMovie);

       String path = "/upload/video_rm/GB_"+listVideoRm.get(0).getIdDetailCheckup()+".mp4";

        try {
            FileOutputStream fos = new FileOutputStream(new File(CommonUtil.getPropertyParams("upload.folder")+path));
            for ( Box item : out.getBoxes()) {
                try {
                    item.getBox(fos.getChannel());
                } catch (IOException e){
                    logger.error("[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
                    throw new GeneralBOException("[[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
                }
            }

            try {
                fos.close();
            } catch (IOException e){
                logger.error("[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
                throw new GeneralBOException("[[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
            }
        } catch (FileNotFoundException e) {
            logger.error("[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
            throw new GeneralBOException("[[TelemedicBoImpl.insertVideoRm] ERROR. ", e);
        }
        return path;
    }

    private String generateIdNotif(String branchId){
        String stDate = CommonUtil.stDateSeq();
        return "NTF"+ branchId + stDate + notifikasiAdminTelemedicDao.getNextSeq();
    }
}
