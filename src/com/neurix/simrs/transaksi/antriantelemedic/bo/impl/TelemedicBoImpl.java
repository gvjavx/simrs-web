package com.neurix.simrs.transaksi.antriantelemedic.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.dokter.dao.DokterDao;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterEntity;
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
import com.neurix.simrs.transaksi.verifikatorpembayaran.dao.VerifikatorPembayaranDao;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        List<AntrianTelemedic> results = new ArrayList<>();
        List<ItSimrsAntrianTelemedicEntity> antrianTelemedicEntities = getListEntityByCriteria(bean);
        if (antrianTelemedicEntities.size() > 0){

            AntrianTelemedic antrianTelemedic;
            for (ItSimrsAntrianTelemedicEntity telemedicEntity : antrianTelemedicEntities){
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
                antrianTelemedic.setFlag(telemedicEntity.getFlag());
                antrianTelemedic.setAction(telemedicEntity.getAction());
                antrianTelemedic.setNamaPelayanan(getPelayananById(telemedicEntity.getIdPelayanan()).getNamaPelayanan());
                antrianTelemedic.setNamaDokter(getDokterById(telemedicEntity.getIdDokter()).getNamaDokter());
                antrianTelemedic.setNamaPasien(getPasienById(telemedicEntity.getIdPasien()).getNama());
                antrianTelemedic.setKetStatus(ketStatus(telemedicEntity.getStatus()));
                antrianTelemedic.setKetFlagResep(ketResep(telemedicEntity.getFlagResep()));
                antrianTelemedic.setKodeBank(telemedicEntity.getKodeBank());
                results.add(antrianTelemedic);
            }
        }

        logger.info("[TelemedicBoImpl.getSearchByCriteria] END <<<");
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
            default:
                return "";
        }
    }
    private String ketResep(String flagResep){

        switch (flagResep){
            case "Y":
                return "Resep";
            default:
                return "";
        }
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
        if (bean.getId() != null){
            hsCriteria.put("id", bean.getId());
        }
        if (bean.getIdPasien() != null){
            hsCriteria.put("id_pasien", bean.getIdPasien());
        }
        if (bean.getIdDokter() != null){
            hsCriteria.put("id_dokter", bean.getIdDokter());
        }
        if (bean.getIdPelayanan() != null){
            hsCriteria.put("id_pelayanan", bean.getIdPelayanan());
        }
        if (bean.getStatus() != null){
            hsCriteria.put("status", bean.getStatus());
        }
        if (bean.getFlag() != null){
            hsCriteria.put("flag", bean.getFlag());
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

                telemedicEntity.setFlag(bean.getFlag());
                telemedicEntity.setFlagResep(bean.getFlagResep());
                telemedicEntity.setFlagBayarResep(bean.getFlagBayarResep());
                telemedicEntity.setFlagBayarKonsultasi(bean.getFlagBayarKonsultasi());
                telemedicEntity.setStatus(bean.getStatus());
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
        pembayaranOnlineEntity.setId(bean.getId()+"INV"+getSeqPembayaranOnline());
        pembayaranOnlineEntity.setIdAntrianTelemedic(bean.getId());
        pembayaranOnlineEntity.setLastUpdate(bean.getLastUpdate());

        if (tindakanEntity != null){
            if (!"bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
                pembayaranOnlineEntity.setIdItem(tindakanEntity.getIdTindakan());
                pembayaranOnlineEntity.setNominal(tindakanEntity.getTarif());
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

        logger.info("[TelemedicBoIml.generateListPembayaran] END <<<");
    }

    private String getSeqTelemedic(){
        return telemedicDao.getNextSeq();
    }
    private String getSeqPembayaranOnline(){
        return verifikatorPembayaranDao.getNextSeq();
    }
}
