package com.neurix.simrs.transaksi.antriantelemedic.bo.impl;

import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.dokter.dao.DokterDao;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterEntity;
import com.neurix.simrs.master.kurir.dao.KurirDao;
import com.neurix.simrs.master.kurir.model.ImSimrsKurirEntity;
import com.neurix.simrs.master.pasien.dao.PasienDao;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.tindakan.dao.TindakanDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.mobileapi.antrian.model.Antrian;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.antriantelemedic.dao.TelemedicDao;
import com.neurix.simrs.transaksi.antriantelemedic.model.AntrianTelemedic;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import com.neurix.simrs.transaksi.antriantelemedic.model.StatusAntrianTelemedic;
import com.neurix.simrs.transaksi.hargaobat.dao.HargaObatDao;
import com.neurix.simrs.transaksi.hargaobat.model.MtSimrsHargaObatEntity;
import com.neurix.simrs.transaksi.reseponline.dao.PengirimanObatDao;
import com.neurix.simrs.transaksi.reseponline.dao.ResepOnlineDao;
import com.neurix.simrs.transaksi.reseponline.model.ItSimrsPengirimanObatEntity;
import com.neurix.simrs.transaksi.reseponline.model.ItSimrsResepOnlineEntity;
import com.neurix.simrs.transaksi.reseponline.model.PengirimanObat;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.neurix.simrs.transaksi.verifikatorpembayaran.dao.VerifikatorPembayaranDao;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.PembayaranOnline;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
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

                if (telemedicEntity.getIdPelayanan() != null && !"".equalsIgnoreCase(telemedicEntity.getIdPelayanan())) {
                    antrianTelemedic.setNamaPelayanan(getPelayananById(telemedicEntity.getIdPelayanan()).getNamaPelayanan());
                }
                if (telemedicEntity.getIdPasien() != null && !"".equalsIgnoreCase(telemedicEntity.getIdPasien())) {
                    antrianTelemedic.setNamaPasien(getPasienById(telemedicEntity.getIdPasien()).getNama());
                }
                if (telemedicEntity.getIdDokter() != null && !"".equalsIgnoreCase(telemedicEntity.getIdDokter())) {
                    antrianTelemedic.setNamaDokter(getDokterById(telemedicEntity.getIdDokter()).getNamaDokter());
                }

                antrianTelemedic.setKetStatus(ketStatus(telemedicEntity.getStatus()));
                antrianTelemedic.setKetFlagResep(ketResep(telemedicEntity.getFlagResep()));
                antrianTelemedic.setKodeBank(telemedicEntity.getKodeBank());
                antrianTelemedic.setBranchId(telemedicEntity.getBranchId());
                antrianTelemedic.setKeluhan(telemedicEntity.getKeluhan());
                antrianTelemedic.setFlagEresep(telemedicEntity.getFlagEresep());

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

                if (antrianTelemedic.getFlagResep() == null && antrianTelemedic.getApproveKonsultasi() != null) {
                    antrianTelemedic.setStatusTransaksi("finish");
                } else if ("Y".equalsIgnoreCase(antrianTelemedic.getFlagResep()) && antrianTelemedic.getApproveKonsultasi() != null && antrianTelemedic.getApproveResep() != null) {
                    antrianTelemedic.setStatusTransaksi("finish");
                } else if ("Y".equalsIgnoreCase(antrianTelemedic.getApproveResep()) && "Y".equalsIgnoreCase(telemedicEntity.getFlagBayarResep())){
                    antrianTelemedic.setStatusTransaksi("finish");
                } else {
                    antrianTelemedic.setStatusTransaksi("exist");
                }
                results.add(antrianTelemedic);
            }
        }

        logger.info("[TelemedicBoImpl.getSearchByCriteria] END <<<");
        final String statusTransaksi = bean.getStatusTransaksi();
        if ("finish".equalsIgnoreCase(statusTransaksi) || "exist".equalsIgnoreCase(statusTransaksi)){
            return results.stream().filter(p->p.getStatusTransaksi().equalsIgnoreCase(statusTransaksi)).collect(Collectors.toList());
        }

        return results;
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
                    return "Antrian Long List . . .";
                case "WL":
                    return "Waiting List . . .";
                case "SL":
                    return "Antrian Short List . . .";
                case "ER":
                    return "Pembayaran E Resep . . .";
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

    @Override
    public ItSimrsAntrianTelemedicEntity getAntrianTelemedicEntityById(String id) throws GeneralBOException {
        return telemedicDao.getById("id", id);
    }

    private List<ItSimrsAntrianTelemedicEntity> getListEntityByCriteria(AntrianTelemedic bean) throws GeneralBOException{
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
            generateListPembayaran(bean, branchId, "konsultasi", kodeBank);
        }
        logger.info("[TelemedicBoImpl.saveAdd] END <<<");
        return bean.getId();
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

                telemedicEntity.setFlag(bean.getFlag() == null ? telemedicEntity.getFlag() : bean.getFlag());
                telemedicEntity.setFlagResep(bean.getFlagResep() == null ? telemedicEntity.getFlagResep() : bean.getFlagResep());
                telemedicEntity.setFlagBayarResep(bean.getFlagBayarResep() == null ? telemedicEntity.getFlagBayarResep() : bean.getFlagBayarResep());
                telemedicEntity.setFlagBayarKonsultasi(bean.getFlagBayarKonsultasi() == null  ? telemedicEntity.getFlagBayarKonsultasi() : bean.getFlagBayarKonsultasi());
                telemedicEntity.setStatus(bean.getStatus() == null ? statusSebelum : bean.getStatus());
                telemedicEntity.setAction("U");
                telemedicEntity.setLastUpdate(bean.getLastUpdate());
                telemedicEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    telemedicDao.updateAndSave(telemedicEntity);
                } catch (HibernateException e){
                    logger.info("[TelemedicBoImpl.getListEntityByCriteria] ERROR. ", e);
                    throw new GeneralBOException("[TelemedicBoImpl.getListEntityByCriteria] ERROR. "+ e.getMessage());
                }

                // jika waiting list maka generate daftar pembayaran jika perubahan dari LL ke WL;
                if ("LL".equalsIgnoreCase(statusSebelum) && "WL".equalsIgnoreCase(telemedicEntity.getStatus())){
                    generateListPembayaran(telemedicEntity, branchId, "konsultasi", kodeBank);
                }
            }
        }
    }

    private void generateListPembayaran(ItSimrsAntrianTelemedicEntity bean, String branchId, String tipe, String kodeBank) throws GeneralBOException{
        logger.info("[TelemedicBoIml.generateListPembayaran] START >>>");

        // mencari tindakan konsultasi;
        Map hsCriteria = new HashMap();
        hsCriteria.put("branch_id", branchId);
        hsCriteria.put("kategori_ina_bpjs", "konsultasi");
        List<ImSimrsTindakanEntity> tindakanEntities = tindakanDao.getByCriteria(hsCriteria);
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
            if (!"bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
                pembayaranOnlineEntity.setIdItem(tindakanEntity.getIdTindakan());
                pembayaranOnlineEntity.setNominal(new BigDecimal(tindakanEntity.getTarif()));
            }
        }

        pembayaranOnlineEntity.setFlag(bean.getFlag());
        pembayaranOnlineEntity.setCreatedDate(bean.getCreatedDate());
        pembayaranOnlineEntity.setCreatedWho(bean.getCreatedWho());
        pembayaranOnlineEntity.setLastUpdate(bean.getLastUpdate());
        pembayaranOnlineEntity.setLastUpdateWho(bean.getLastUpdateWho());
        pembayaranOnlineEntity.setKeterangan(tipe);
        pembayaranOnlineEntity.setKodeBank(kodeBank);

        try {
            verifikatorPembayaranDao.addAndSave(pembayaranOnlineEntity);
        } catch (HibernateException e){
            logger.error("[TelemedicBoIml.generateListPembayaran] ERROR. ",e);
            throw new GeneralBOException("[TelemedicBoIml.generateListPembayaran] ERROR. ",e);
        }

        if ("Y".equalsIgnoreCase(bean.getFlagResep())){
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

                hargaTotal = hargaTotal.add(resepOnlineEntity.getSubTotal());
            }
        }

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
            newPembayaran.setNominal(hargaTotal);
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

            resultReturn.add(pengirimanObat);
        }


        logger.info("[TelemedicBoImpl.getPembayaranByCriteria] END <<<");
        return  resultReturn;

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
        logger.info("[TelemedicBoImpl.saveEditPengirimanObat] END <<<");

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
}
