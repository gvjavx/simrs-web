package com.neurix.simrs.transaksi.verifikatorpembayaran.action;

import com.neurix.akuntansi.master.master.bo.MasterBo;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiFcmBo;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import com.neurix.simrs.master.jenisperiksapasien.bo.AsuransiBo;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.ImJenisPeriksaPasienEntity;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.mobileapi.antrian.model.Antrian;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.JurnalResponse;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.antriantelemedic.model.AntrianTelemedic;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import com.neurix.simrs.transaksi.paketperiksa.bo.PaketPeriksaBo;
import com.neurix.simrs.transaksi.paketperiksa.model.ItSimrsPaketPasienEntity;
import com.neurix.simrs.transaksi.paketperiksa.model.ItemPaket;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsItemPaketEntity;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsPaketEntity;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.ImSimrsPermintaanResepEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsTindakanTransitorisEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.teamdokter.model.ItSimrsDokterTeamEntity;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.neurix.simrs.transaksi.verifikatorpembayaran.bo.VerifikatorPembayaranBo;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.PembayaranOnline;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by reza on 10/06/20.
 */
public class VerifikatorPembayaranAction {
    private final static transient Logger logger = Logger.getLogger(VerifikatorPembayaranAction.class);

    private VerifikatorPembayaranBo verifikatorPembayaranBoProxy;
    private TelemedicBo telemedicBoProxy;
    private NotifikasiFcmBo notifikasiFcmBoProxy;
    private PembayaranOnline pembayaranOnline;
    public AntrianTelemedic antrianTelemedic;

    public NotifikasiFcmBo getNotifikasiFcmBoProxy() {
        return notifikasiFcmBoProxy;
    }

    public void setNotifikasiFcmBoProxy(NotifikasiFcmBo notifikasiFcmBoProxy) {
        this.notifikasiFcmBoProxy = notifikasiFcmBoProxy;
    }

    public AntrianTelemedic getAntrianTelemedic() {
        return antrianTelemedic;
    }

    public void setAntrianTelemedic(AntrianTelemedic antrianTelemedic) {
        this.antrianTelemedic = antrianTelemedic;
    }

    public PembayaranOnline getPembayaranOnline() {
        return pembayaranOnline;
    }

    public void setPembayaranOnline(PembayaranOnline pembayaranOnline) {
        this.pembayaranOnline = pembayaranOnline;
    }

    public void setVerifikatorPembayaranBoProxy(VerifikatorPembayaranBo verifikatorPembayaranBoProxy) {
        this.verifikatorPembayaranBoProxy = verifikatorPembayaranBoProxy;
    }

    public void setTelemedicBoProxy(TelemedicBo telemedicBoProxy) {
        this.telemedicBoProxy = telemedicBoProxy;
    }

    public String initForm(){
        logger.info("[VerifikatorPembayaranAction.initForm] START >>>");

        setPembayaranOnline(new PembayaranOnline());
        setAntrianTelemedic(new AntrianTelemedic());
        logger.info("[VerifikatorPembayaranAction.initForm] END <<<");
        return "search";
    }

    public String search(){
        logger.info("[VerifikatorPembayaranAction.search] START >>>");

        String branchId = CommonUtil.userBranchLogin();
        AntrianTelemedic antrianTelemedic = getAntrianTelemedic();
        AntrianTelemedic searchAntrian = new AntrianTelemedic();
        searchAntrian.setBranchId(branchId);

        if (antrianTelemedic != null){
            searchAntrian.setStatus(antrianTelemedic.getStatus());
            searchAntrian.setIdPelayanan(antrianTelemedic.getIdPelayanan());
        }


        List<AntrianTelemedic> listResults = new ArrayList<>();
        try {
            listResults = telemedicBoProxy.getSearchByCriteria(searchAntrian);
        } catch (GeneralBOException e){
            logger.error("[VerifikatorPembayaranAction.search] ERROR. ",e);
            throw new GeneralBOException("[VerifikatorPembayaranAction.search] ERROR. ",e);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResults");
        session.setAttribute("listOfResults", listResults);

        logger.info("[VerifikatorPembayaranAction.search] END <<<");
        return "search";
    }

    public List<PembayaranOnline> listDetailPembayaran(String idAntrianTelemedic){
        logger.info("[VerifikatorPembayaranAction.listDetailPembayaran] START >>>");

        List<PembayaranOnline> pembayaranOnlines = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");

        PembayaranOnline pembayaranOnline = new PembayaranOnline();
        pembayaranOnline.setIdAntrianTelemedic(idAntrianTelemedic);

        try {
            pembayaranOnlines = verifikatorPembayaranBo.getSearchByCriteria(pembayaranOnline);
        } catch (GeneralBOException e){
            logger.error("[VerifikatorPembayaranAction.listDetailPembayaran] ERROR. ",e);
            throw new GeneralBOException("[VerifikatorPembayaranAction.listDetailPembayaran] ERROR. ",e);
        }

        logger.info("[VerifikatorPembayaranAction.listDetailPembayaran] END <<<");
        return pembayaranOnlines;
    }

    public CheckResponse approveTransaksi(String idTransaksi){
        logger.info("[VerifikatorPembayaranAction.approveTransaksi] START >>>");

        String userLogin = CommonUtil.userIdLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        CheckResponse response = new CheckResponse();

        ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranBo.getPembayaranOnlineById(idTransaksi);
        if (pembayaranOnlineEntity != null){

            String idJenisPeriksaPasien = "";
            String idDetailCheckup = "";

            ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(pembayaranOnlineEntity.getIdAntrianTelemedic());
            if (antrianTelemedicEntity != null){

                List<NotifikasiFcm> result = new ArrayList<>();
                NotifikasiFcm bean = new NotifikasiFcm();

                idJenisPeriksaPasien = antrianTelemedicEntity.getIdJenisPeriksaPasien();
                HeaderCheckup headerCheckup = new HeaderCheckup();
                String flagResep = "N";

                // jika resep
                if ("resep".equalsIgnoreCase(pembayaranOnlineEntity.getKeterangan())){
                    flagResep = "Y";
                    response.setStatus("success");
                    return response;
                } else {
                    // set data headerCheckup;
                    headerCheckup.setNoCheckup("CKP"+checkupBo.getNextHeaderId());
                    headerCheckup.setIdPasien(antrianTelemedicEntity.getIdPasien());
                    headerCheckup.setIdPelayanan(antrianTelemedicEntity.getIdPelayanan());
                    headerCheckup.setIdDokter(antrianTelemedicEntity.getIdDokter());

                    // mendapatkan data pasien;
                    ImSimrsPasienEntity pasienEntity = pasienBo.getPasienById(antrianTelemedicEntity.getIdPasien());
                    if (pasienEntity != null){

                        headerCheckup.setNama(pasienEntity.getNama());
                        headerCheckup.setJenisKelamin(pasienEntity.getJenisKelamin());
                        headerCheckup.setNoKtp(pasienEntity.getNoKtp());
                        headerCheckup.setTempatLahir(pasienEntity.getTempatLahir());
                        headerCheckup.setTglLahir(new Date(pasienEntity.getTglLahir().getTime()));
                        headerCheckup.setDesaId(pasienEntity.getDesaId());
                        headerCheckup.setJalan(pasienEntity.getJalan());
                        headerCheckup.setSuku(pasienEntity.getSuku());
                        headerCheckup.setAgama(pasienEntity.getAgama());
                        headerCheckup.setProfesi(pasienEntity.getProfesi());
                        headerCheckup.setNoTelp(pasienEntity.getNoTelp());
                        headerCheckup.setIdJenisPeriksaPasien(idJenisPeriksaPasien);
                        headerCheckup.setFlag("Y");
                        headerCheckup.setAction("C");
                        headerCheckup.setCreatedDate(time);
                        headerCheckup.setCreatedWho(userLogin);
                        headerCheckup.setLastUpdate(time);
                        headerCheckup.setLastUpdateWho(userLogin);
                        headerCheckup.setJenisKunjungan("Lama");
                        headerCheckup.setIdPelayanan(antrianTelemedicEntity.getIdPelayanan());
                        headerCheckup.setStatusPeriksa("3");
                        headerCheckup.setStTglLahir(pasienEntity.getTglLahir().toString());
                        headerCheckup.setMetodePembayaran("non_tunai");
                        headerCheckup.setIdAntrianOnline(antrianTelemedicEntity.getId());
                        headerCheckup.setIdTransaksiOnline(idTransaksi);

                        Tindakan tindakan = new Tindakan();
                        List<Tindakan> tindakans = new ArrayList<>();
                        tindakan.setIdTindakan(pembayaranOnlineEntity.getIdItem());
                        tindakans.add(tindakan);

                        headerCheckup.setTindakanList(tindakans);
                    }
                }

                try {
                    idDetailCheckup = verifikatorPembayaranBo.approveTransaksi(headerCheckup);
                } catch (GeneralBOException e){
                    logger.error("[VerifikatorPembayaranAction.approveTransaksi] ERROR. ",e);
                    response.setStatus("error");
                    response.setMessage("[VerifikatorPembayaranAction.approveTransaksi] ERROR. " + e);
                    return response;
                }

                // approve All tindakan and save
                String idRiwayatTindakan = "";
                if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(idJenisPeriksaPasien)){
                    response = saveApproveAllTindakanRawatJalan(idDetailCheckup, idJenisPeriksaPasien);
                    if ("success".equalsIgnoreCase(response.getStatus())){

                        RiwayatTindakan tindakan = new RiwayatTindakan();
                        tindakan.setIdDetailCheckup(idDetailCheckup);
                        if ("konsultasi".equalsIgnoreCase(pembayaranOnlineEntity.getKeterangan())){
                            tindakan.setKeterangan("tindakan");
                        } else {
                            tindakan.setKeterangan("resep");
                        }
                        List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = riwayatTindakanBo.getListEntityRiwayatTindakan(tindakan);
                        if (riwayatTindakanEntities.size() > 0){
                            ItSimrsRiwayatTindakanEntity tindakanEntity = riwayatTindakanEntities.get(0);
                            if (tindakanEntity != null){
                                idRiwayatTindakan = tindakanEntity.getIdRiwayatTindakan();
                            }
                        }
                    }
                }

                // jika selesai approve all tindakan berarti antrian WL berkurang 1;
                // cari antrian status LL order by createdDate ASCENDING
                // dimasukan ke antrian WL

                if ("WL".equalsIgnoreCase(antrianTelemedicEntity.getStatus())){
                    ItSimrsAntrianTelemedicEntity firstOrderAntrian = telemedicBo.getAntrianTelemedicFirstOrder(antrianTelemedicEntity.getIdPelayanan(), antrianTelemedicEntity.getIdDokter(), "LL");

                    if (firstOrderAntrian != null){

                        AntrianTelemedic antrianTelemedic = new AntrianTelemedic();
                        antrianTelemedic.setId(antrianTelemedic.getId());
                        antrianTelemedic.setStatus("WL");
                        antrianTelemedic.setLastUpdate(time);
                        antrianTelemedic.setLastUpdateWho(userLogin);

                        try {
                            telemedicBo.saveEdit(antrianTelemedic, firstOrderAntrian.getBranchId(), firstOrderAntrian.getKodeBank());

                            //Push Notif ke Pasien terkait perubahan status menjadi WL
                            bean.setUserId(firstOrderAntrian.getIdPasien());
                            result = notifikasiFcmBoProxy.getByCriteria(bean);
                            FirebasePushNotif.sendNotificationFirebase(result.get(0).getTokenFcm(),"Telemedic", "Anda telah memasuki Antrian Waiting List. Silahkan lakukan pembayaran", "WL", bean.getOs());

                        } catch (GeneralBOException e){
                            logger.error("[VerifikatorPembayaranAction.approveTransaksi] ERROR. ",e);
                            response.setStatus("error");
                            response.setMessage("[VerifikatorPembayaranAction.approveTransaksi] ERROR. " + e);
                            return response;
                        }
                    }
                }

                // --- create jurnal;
                JurnalResponse jurnalResponse = closingJurnalNonTunai(idDetailCheckup, idTransaksi, antrianTelemedicEntity.getIdPelayanan(), antrianTelemedicEntity.getIdPasien(), flagResep);

                // --- update flag; jika success pada prosess membuat jurnal;
                if ("success".equalsIgnoreCase(jurnalResponse.getStatus())){

                    pembayaranOnlineEntity.setIdRiwayatTindakan(idRiwayatTindakan);
                    pembayaranOnlineEntity.setApprovedFlag("Y");
                    pembayaranOnlineEntity.setApprovedWho(userLogin);
                    pembayaranOnlineEntity.setLastUpdate(time);
                    pembayaranOnlineEntity.setLastUpdateWho(userLogin);
                    pembayaranOnlineEntity.setAction("U");

                    try {
                        verifikatorPembayaranBo.saveEdit(pembayaranOnlineEntity);
                        response.setStatus("success");

                        //Push Notif ke Pasien terkait perubahan status menjadi SL
                        bean.setUserId(antrianTelemedicEntity.getIdPasien());
                        result = notifikasiFcmBoProxy.getByCriteria(bean);
                        FirebasePushNotif.sendNotificationFirebase(result.get(0).getTokenFcm(),"Telemedic", "Anda telah memasuki Antrian Short List. Buka aplikasi untuk menunggu panggilan dokter", "SL", bean.getOs());
                    } catch (GeneralBOException e){
                        logger.error("[VerifikatorPembayaranAction.approveTransaksi] ERROR. ",e);
                        response.setStatus("error");
                        response.setMessage("[VerifikatorPembayaranAction.approveTransaksi] ERROR. " + e);
                        return response;
                    }

                    // --- Update WL to SL
                    if (antrianTelemedicEntity != null && "WL".equalsIgnoreCase(antrianTelemedicEntity.getStatus())){

                        AntrianTelemedic antrianTelemedic = new AntrianTelemedic();
                        antrianTelemedic.setId(antrianTelemedicEntity.getId());
                        antrianTelemedic.setStatus("SL");
                        antrianTelemedic.setLastUpdate(time);
                        antrianTelemedic.setLastUpdateWho(userLogin);

                        try {
                            telemedicBo.saveEdit(antrianTelemedic, "", "");
                            response.setStatus("success");
                        } catch (GeneralBOException e){
                            logger.error("[VerifikatorPembayaranAction.approveTransaksi] ERROR. ",e);
                            response.setStatus("error");
                            response.setMessage("[VerifikatorPembayaranAction.approveTransaksi] ERROR. " + e);
                            return response;
                        }
                    }
                    // --- END

                } else {
                    logger.error("[VerifikatorPembayaranAction.approveTransaksi] ERROR. " + jurnalResponse.getMsg());
                    response.setStatus("error");
                    response.setMessage("[VerifikatorPembayaranAction.approveTransaksi] ERROR. " + jurnalResponse.getMsg());
                    return response;
                }

            } else {
                logger.error("[VerifikatorPembayaranAction.approveTransaksi] ERROR. tidak ditemukan data transaksi.");
                response.setStatus("error");
                response.setMessage("[VerifikatorPembayaranAction.approveTransaksi] ERROR. tidak ditemukan data transaksi.");
                return response;
            }
        } else {
            logger.error("[VerifikatorPembayaranAction.approveTransaksi] ERROR. tidak ditemukan data transaksi.");
            response.setStatus("error");
            response.setMessage("[VerifikatorPembayaranAction.approveTransaksi] ERROR. tidak ditemukan data transaksi.");
            return response;
        }




        logger.info("[VerifikatorPembayaranAction.approveTransaksi] END <<<");
        return response;
    }

    public CheckResponse saveApproveAllTindakanRawatJalan(String idDetailCheckup, String jenisPasien) {

        logger.info("[VerifikatorPembayaranAction.saveApproveAllTindakanRawatJalan] START process >>>");
        CheckResponse response = new CheckResponse();
        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {

            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String user = CommonUtil.userLogin();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
            RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");

            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
            headerDetailCheckup.setIdDetailCheckup(idDetailCheckup);
            headerDetailCheckup.setLastUpdate(updateTime);
            headerDetailCheckup.setLastUpdateWho(user);

            try {
                response = checkupDetailBo.saveApproveAllTindakanRawatJalan(headerDetailCheckup);
            } catch (GeneralBOException e) {
                logger.error("[VerifikatorPembayaranAction.saveApproveAllTindakanRawatJalan] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            }

            if ("success".equalsIgnoreCase(response.getStatus())) {
                saveAddToRiwayatTindakan(idDetailCheckup, jenisPasien);
            }

            if ("asuransi".equalsIgnoreCase(jenisPasien)) {

                ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
                if (detailCheckupEntity != null) {
                    BigDecimal cover = detailCheckupEntity.getCoverBiaya();
                    BigDecimal jumlahAllTindakanAsuransi = checkupDetailBo.getSumJumlahTindakanByJenis(idDetailCheckup, jenisPasien, "");
                    if (jumlahAllTindakanAsuransi.compareTo(cover) == 1) {
                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                        riwayatTindakan.setIdDetailCheckup(idDetailCheckup);
                        riwayatTindakan.setJenisPasien(jenisPasien);
//                        riwayatTindakan.setNotResep("Y");
                        List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = riwayatTindakanBo.getListEntityRiwayatTindakan(riwayatTindakan);

                        if (riwayatTindakanEntities.size() > 0) {
                            BigDecimal jumlahBiaya = new BigDecimal(0);
                            for (ItSimrsRiwayatTindakanEntity riwayatTindakanEntity : riwayatTindakanEntities) {

                                jumlahBiaya = jumlahBiaya.add(riwayatTindakanEntity.getTotalTarif());

                                // jika jumlahBiaya Lebih besar dari pada yg di cover maka;
                                // tindakan dialihkan ke umum;
                                if (jumlahBiaya.compareTo(cover) == 1) {

                                    // newTarif = cover - (total tarif melebihi - tarif tindakan)
                                    BigDecimal newTarif = cover.subtract(jumlahBiaya.subtract(riwayatTindakanEntity.getTotalTarif()));

                                    // jika newTarif lebih besar dari 0
                                    // maka update tindakan dengan tarif tindakan sisa (newTarif)
                                    // membuat tindakan umum baru dari tindakan tarif - newTarif
                                    if (newTarif.compareTo(BigDecimal.ZERO) == 1) {

                                        BigDecimal tarifAwal = riwayatTindakanEntity.getTotalTarif();

                                        riwayatTindakanEntity.setTotalTarif(newTarif);
                                        riwayatTindakanEntity.setAction("U");
                                        riwayatTindakanEntity.setLastUpdate(updateTime);
                                        riwayatTindakanEntity.setLastUpdateWho(user);

                                        try {
                                            riwayatTindakanBo.updateByEntity(riwayatTindakanEntity);
                                        } catch (GeneralBOException e) {
                                            logger.error("[VerifikatorPembayaranAction.saveApproveAllTindakanRawatJalan] ERROR. ", e);
                                            response.setStatus("error");
                                            response.setMessage("[VerifikatorPembayaranAction.saveApproveAllTindakanRawatJalan] ERROR. " + e);
                                        }

                                        // sisa tarif masuk ke umum adalah tindakan asli / tarifAwal - newTarif
                                        BigDecimal newTarifTindakanUmum = tarifAwal.subtract(newTarif);

                                        RiwayatTindakan riwayatTindakanEntityNew = new RiwayatTindakan();
                                        riwayatTindakanEntityNew.setIdTindakan(riwayatTindakanEntity.getIdTindakan());
                                        riwayatTindakanEntityNew.setNamaTindakan(riwayatTindakanEntity.getNamaTindakan());
                                        riwayatTindakanEntityNew.setKeterangan(riwayatTindakanEntity.getKeterangan());
                                        riwayatTindakanEntityNew.setJenisPasien("umum");
                                        riwayatTindakanEntityNew.setTotalTarif(newTarifTindakanUmum);
                                        riwayatTindakanEntityNew.setTanggalTindakan(riwayatTindakanEntity.getTanggalTindakan());
                                        riwayatTindakanEntityNew.setIdDetailCheckup(riwayatTindakanEntity.getIdDetailCheckup());
                                        riwayatTindakanEntityNew.setKategoriTindakanBpjs(riwayatTindakanEntity.getKategoriTindakanBpjs());
                                        riwayatTindakanEntityNew.setApproveBpjsFlag(riwayatTindakanEntity.getApproveBpjsFlag());
                                        riwayatTindakanEntityNew.setFlag("Y");
                                        riwayatTindakanEntityNew.setAction("C");
                                        riwayatTindakanEntityNew.setCreatedDate(updateTime);
                                        riwayatTindakanEntityNew.setCreatedWho(user);
                                        riwayatTindakanEntityNew.setLastUpdate(updateTime);
                                        riwayatTindakanEntityNew.setLastUpdateWho(user);

                                        try {
                                            riwayatTindakanBo.saveAdd(riwayatTindakanEntityNew);
                                        } catch (GeneralBOException e) {
                                            logger.error("[VerifikatorPembayaranAction.saveApproveAllTindakanRawatJalan] ERROR. ", e);
                                            response.setStatus("error");
                                            response.setMessage("[VerifikatorPembayaranAction.saveApproveAllTindakanRawatJalan] ERROR. " + e);
                                        }
                                    } else {

                                        // jika tindakan newTarif == tindakan tarif || newTarif > tindakan tarif
                                        // maka hanya mengupdate jenis pasien menjadi umum

                                        riwayatTindakanEntity.setJenisPasien("umum");
                                        riwayatTindakanEntity.setAction("U");
                                        riwayatTindakanEntity.setLastUpdate(updateTime);
                                        riwayatTindakanEntity.setLastUpdateWho(user);

                                        try {
                                            riwayatTindakanBo.updateByEntity(riwayatTindakanEntity);
                                        } catch (GeneralBOException e) {
                                            logger.error("[VerifikatorPembayaranAction.saveApproveAllTindakanRawatJalan] ERROR. ", e);
                                            response.setStatus("error");
                                            response.setMessage("[VerifikatorPembayaranAction.saveApproveAllTindakanRawatJalan] ERROR. " + e);
                                        }
                                    }
                                }
                            }

//                            throw new GeneralBOException("[Loop Selesai]");
                        }
                    }
                }
            }
        }

        logger.info("[VerifikatorPembayaranAction.saveApproveAllTindakanRawatJalan] END process >>>");
        return response;
    }

    public void saveAddToRiwayatTindakan(String idDetail, String jenisPasien) {
        logger.info("[VerifikatorPembayaranAction.saveAddToRiwayatTindakan] START process >>>");
        if (idDetail != null && !"".equalsIgnoreCase(idDetail)) {

            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String user = CommonUtil.userLogin();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");
            PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
            RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
            PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
            TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
            RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
            OrderGiziBo orderGiziBo = (OrderGiziBo) ctx.getBean("orderGiziBoProxy");
            CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

            String jenPasien = "";
            if ("ptpn".equalsIgnoreCase(jenisPasien)) {
                jenPasien = "bpjs";
            } else {
                jenPasien = jenisPasien;
            }

            String idPaket = "";
            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetail);
            if (detailCheckupEntity != null){
                idPaket = detailCheckupEntity.getIdPaket();
            }

            List<TindakanRawat> listTindakan = new ArrayList<>();
            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdDetailCheckup(idDetail);
            tindakanRawat.setApproveFlag("Y");

            try {
                listTindakan = tindakanRawatBo.getByCriteria(tindakanRawat);
            } catch (GeneralBOException e) {
                logger.error("[VerifikatorPembayaranAction.saveAddToRiwayatTindakan] Found error when search tindakan :" + e.getMessage());
            }

            if (listTindakan.size() > 0) {
                for (TindakanRawat entity : listTindakan) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdTindakanRawat());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[VerifikatorPembayaranAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                    }

                    if (riwayatTindakanList.isEmpty()) {
                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                        riwayatTindakan.setIdTindakan(entity.getIdTindakanRawat());
                        riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                        riwayatTindakan.setNamaTindakan(entity.getNamaTindakan());

                        if (!"".equalsIgnoreCase(idPaket)){

                            // mengambil berdasarkan idPaket dan idTindakan;
                            MtSimrsItemPaketEntity itemPaketEntity = riwayatTindakanBo.getItemPaketEntity(idPaket, entity.getIdTindakan());
                            if (itemPaketEntity != null){

                                // jika ada paket;
                                riwayatTindakan.setTotalTarif(new BigDecimal(itemPaketEntity.getHarga()));
                            } else {

                                // jika tidak ada item paket namun golongan paket, maka tarif tindakan asli yang dipakai
                                riwayatTindakan.setTotalTarif(new BigDecimal(entity.getTarifTotal()));
                            }

                        } else {

                            // jika bukan paket maka tarif tindakan asli
                            riwayatTindakan.setTotalTarif(new BigDecimal(entity.getTarifTotal()));
                        }

                        riwayatTindakan.setKeterangan("tindakan");
                        riwayatTindakan.setJenisPasien(jenPasien);
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
                            logger.error("[VerifikatorPembayaranAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
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
                logger.error("[VerifikatorPembayaranAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
            }

            if (periksaLabList.size() > 0) {
                for (PeriksaLab entity : periksaLabList) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdPeriksaLab());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[VerifikatorPembayaranAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                    }

                    if (riwayatTindakanList.isEmpty()) {

                        PeriksaLab lab = new PeriksaLab();

                        try {
                            lab = periksaLabBo.getTarifTotalPemeriksaan(entity.getIdLab(), entity.getIdPeriksaLab());
                        } catch (HibernateException e) {
                            logger.error("Found Error " + e.getMessage());
                        }

                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                        riwayatTindakan.setIdTindakan(entity.getIdPeriksaLab());
                        riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                        riwayatTindakan.setNamaTindakan("Periksa Lab " + entity.getLabName());

                        // paket lab
                        if (!"".equalsIgnoreCase(idPaket)){

                            // mencari berdasarkan id paket dan id lab
                            ItemPaket itemPaket = riwayatTindakanBo.getTarifPaketLab(idPaket, entity.getIdLab());
                            if (itemPaket != null){

                                // jika terdapat tarif paket maka menggunakan tarif paket
                                riwayatTindakan.setTotalTarif(itemPaket.getTarif());
                            } else {

                                // jika tidak ada tarif paket menggunakan tarif asli
                                riwayatTindakan.setTotalTarif(lab.getTarif());
                            }
                        } else {

                            // jika bukan paket maka pakai tarif asli
                            riwayatTindakan.setTotalTarif(lab.getTarif());
                        }

                        riwayatTindakan.setKeterangan(lab.getKategoriLabName());
                        riwayatTindakan.setJenisPasien(jenPasien);
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
                            logger.error("[VerifikatorPembayaranAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                        }
                    }
                }
            }

            List<PermintaanResep> resepList = new ArrayList<>();
            PermintaanResep resep = new PermintaanResep();
            resep.setIdDetailCheckup(idDetail);

            try {
                resepList = permintaanResepBo.getByCriteria(resep);
            } catch (GeneralBOException e) {
                logger.error("[VerifikatorPembayaranAction.saveAddToRiwayatTindakan] Found error when search tindakan :" + e.getMessage());
            }

            if (resepList.size() > 0) {
                for (PermintaanResep entity : resepList) {

                    List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                    RiwayatTindakan tindakan = new RiwayatTindakan();
                    tindakan.setIdTindakan(entity.getIdPermintaanResep());

                    try {
                        riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                    } catch (HibernateException e) {
                        logger.error("[VerifikatorPembayaranAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                    }

                    if (riwayatTindakanList.isEmpty()) {

                        TransaksiObatDetail obatDetailList = new TransaksiObatDetail();

                        try {
                            obatDetailList = transaksiObatBo.getTotalHargaResep(entity.getIdPermintaanResep());
                        } catch (HibernateException e) {
                            logger.error("[VerifikatorPembayaranAction.saveAddToRiwayatTindakan] Found error when search list detail obat :" + e.getMessage());
                        }

                        if (obatDetailList.getTotalHarga() != null && !"".equalsIgnoreCase(obatDetailList.getTotalHarga().toString())) {
                            RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                            riwayatTindakan.setIdTindakan(entity.getIdPermintaanResep());
                            riwayatTindakan.setIdDetailCheckup(entity.getIdDetailCheckup());
                            riwayatTindakan.setNamaTindakan("Tarif Resep dengan No. Resep " + entity.getIdPermintaanResep());
                            riwayatTindakan.setTotalTarif(new BigDecimal(obatDetailList.getTotalHarga()));
                            riwayatTindakan.setKeterangan("resep");
                            riwayatTindakan.setJenisPasien(obatDetailList.getJenisResep());
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
                                logger.error("[VerifikatorPembayaranAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
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
                    }

                    if (giziList.size() > 0) {

                        for (OrderGizi gizi : giziList) {

                            List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
                            RiwayatTindakan tindakan = new RiwayatTindakan();
                            tindakan.setIdTindakan(gizi.getIdOrderGizi());

                            try {
                                riwayatTindakanList = riwayatTindakanBo.getByCriteria(tindakan);
                            } catch (HibernateException e) {
                                logger.error("[VerifikatorPembayaranAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
                            }

                            if (riwayatTindakanList.isEmpty()) {

                                RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                                riwayatTindakan.setIdTindakan(gizi.getIdOrderGizi());
                                riwayatTindakan.setIdDetailCheckup(rawatInap.getIdDetailCheckup());
                                riwayatTindakan.setNamaTindakan("Tarif Gizi dengan No. Gizi " + gizi.getIdOrderGizi());
                                riwayatTindakan.setTotalTarif(gizi.getTarifTotal());
                                riwayatTindakan.setKeterangan("gizi");
                                riwayatTindakan.setJenisPasien(jenPasien);
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
                                    logger.error("[VerifikatorPembayaranAction.saveAddToRiwayatTindakan] Found error when insert riwayat tindakan :" + e.getMessage());
                                }
                            }
                        }
                    }
                }
            }
        }
        logger.info("[VerifikatorPembayaranAction.saveAddToRiwayatTindakan] END process >>>");
    }

    private JurnalResponse closingJurnalNonTunai(String idDetailCheckup, String idTransaksiOnline, String idPoli, String idPasien, String flagResep) {

        JurnalResponse response = new JurnalResponse();
        String branchId = CommonUtil.userBranchLogin();
        String invoice = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        AsuransiBo asuransiBo = (AsuransiBo) ctx.getBean("asuransiBoProxy");
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        PaketPeriksaBo paketPeriksaBo = (PaketPeriksaBo) ctx.getBean("paketPeriksaBoProxy");
        MasterBo masterBo = (MasterBo) ctx.getBean("masterBoProxy");
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");

        String kode = "";
        String transId = "";
        String masterId = "";
        String jenisPasien = "Umum ";
        String kodeBank = "";
        String idJenisPeriksaPasien = "";
        String noKartu = "";
        BigDecimal biayaCover = new BigDecimal(0);
        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
        if (detailCheckupEntity != null){
            idJenisPeriksaPasien = detailCheckupEntity.getIdJenisPeriksaPasien();
        }

        ItSimrsHeaderChekupEntity checkupEntity = checkupBo.getEntityCheckupById(detailCheckupEntity.getNoCheckup());
        if (checkupEntity != null) {
            idPasien = checkupEntity.getIdPasien();
        }

        if (idTransaksiOnline != null && !"".equalsIgnoreCase(idTransaksiOnline)){
            ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranBo.getPembayaranOnlineById(idTransaksiOnline);
            if (pembayaranOnlineEntity != null){
                kodeBank = pembayaranOnlineEntity.getKodeBank();
            }
        }

        // mencari no master;
        if ("asuransi".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())) {

            biayaCover = detailCheckupEntity.getCoverBiaya();

            ImSimrsAsuransiEntity asuransiEntity = asuransiBo.getEntityAsuransiById(detailCheckupEntity.getIdAsuransi());
            if (asuransiEntity != null) {
                masterId = asuransiEntity.getNoMaster();
                jenisPasien = "Asuransi " + asuransiEntity.getNamaAsuransi() + " ";
            } else {
                logger.error("[CheckupDetailAction.closingJurnalNonTunai] Error Asuransi tidak ditemukan");
                response.setStatus("error");
                response.setMsg("[CheckupDetailAction.closingJurnalNonTunai] Error Asuransi tidak ditemukan");
                return response;
            }

            noKartu = " No. Kartu Asuransi " + detailCheckupEntity.getNoKartuAsuransi();

        } else if ("bpjs".equalsIgnoreCase(detailCheckupEntity.getIdJenisPeriksaPasien())){

        } else {
            masterId = jenisPriksaPasienBo.getJenisPerikasEntityById(detailCheckupEntity.getIdJenisPeriksaPasien()).getMasterId();
        }

        // MAP ALL TINDAKAN BY KETERANGAN
        List<Map> listOfTindakan = new ArrayList<>();
        List<String> listOfKeteranganRiwayat = riwayatTindakanBo.getListKeteranganByIdDetailCheckup(idDetailCheckup);
        if (listOfKeteranganRiwayat.size() > 0) {

            for (String keterangan : listOfKeteranganRiwayat) {
                Map mapTindakan = new HashMap();
                mapTindakan.put("master_id", masterId);
                mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, idJenisPeriksaPasien, keterangan));
                mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, idJenisPeriksaPasien, keterangan));
                mapTindakan.put("activity", getAcitivityList(idDetailCheckup, idJenisPeriksaPasien, keterangan, kode));
                listOfTindakan.add(mapTindakan);
            }
        }

        // MENDAPATKAN SEMUA BIAYA RAWAT;
        BigDecimal jumlah = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "", "");

        Map mapJurnal = new HashMap();
        mapJurnal.put("pendapatan_rawat_jalan_umum", listOfTindakan);

        if ("Y".equalsIgnoreCase(flagResep)){

        } else {

            invoice = billingSystemBo.createInvoiceNumber("JRJ", branchId);

            // create list map piutang
            Map mapPiutang = new HashMap();
            mapPiutang.put("bukti", invoice);
            mapPiutang.put("nilai", jumlah);
            mapPiutang.put("pasien_id", idPasien);

            mapJurnal.put("piutang_pasien_umum", mapPiutang);
            transId = "61";
        }

        String catatan = "closing jurnal telemedic "+idJenisPeriksaPasien+" Id Detail Checkup " + idDetailCheckup;

        try {

            billingSystemBo.createJurnal(transId, mapJurnal, branchId, catatan, "Y");

            // --- update no invoice;
            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setIdDetailCheckup(idDetailCheckup);
            detailCheckup.setInvoice(invoice);

            checkupDetailBo.saveUpdateNoJuran(detailCheckup);
            // ---

            // --- create jurnal pembayaran
            if ("umum".equalsIgnoreCase(idJenisPeriksaPasien)){

                transId = "02";
                catatan = "Pembayaran Piutang Pasien Telemedic "+idJenisPeriksaPasien+" Id Detail Checkup " + idDetailCheckup;

                // MAPPING KAS
                Map mapKas = new HashMap();
                mapKas.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, idJenisPeriksaPasien, ""));
                mapKas.put("metode_bayar", "transfer");
                mapKas.put("bank", kodeBank);

                mapJurnal = new HashMap();
                Map mapPiutang = new HashMap();
                mapPiutang.put("bukti", invoice);
                mapPiutang.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, idJenisPeriksaPasien, ""));
                mapPiutang.put("master_id", getMasterIdByTipe(idDetailCheckup, idJenisPeriksaPasien));

                mapJurnal.put("kas",mapKas);
                mapJurnal.put("piutang_pasien_non_bpjs", mapPiutang);

                String noJurnal = billingSystemBo.createJurnal(transId, mapJurnal, branchId, catatan, "Y");

                // --- update no jurnal;
                detailCheckup = new HeaderDetailCheckup();
                detailCheckup.setIdDetailCheckup(idDetailCheckup);
                detailCheckup.setNoJurnal(noJurnal);

                checkupDetailBo.saveUpdateNoJuran(detailCheckup);
            }

            response.setStatus("success");
            response.setMsg("[Berhasil]");

        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.closingJurnalNonTunai] Error, ", e);
            response.setStatus("error");
            response.setMsg("[CheckupDetailAction.closingJurnalNonTunai] Error, " + e);
            return response;
        }

        response.setInvoice(invoice);
        return response;
    }

    private String getMasterIdByTipe(String idDetailCheckup, String jenis){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        AsuransiBo asuransiBo = (AsuransiBo) ctx.getBean("asuransiBoProxy");
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");

        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);
        String masterId = "";
        if ("bpjs".equalsIgnoreCase(jenis)){

            ImJenisPeriksaPasienEntity jenisPeriksaPasienEntity = jenisPriksaPasienBo.getJenisPerikasEntityById(jenis);
            if (jenisPeriksaPasienEntity != null){
                masterId = jenisPeriksaPasienEntity.getMasterId();
            }
        } else if ("asuransi".equalsIgnoreCase(jenis)){
            // jika asuransi
            ImSimrsAsuransiEntity asuransiEntity = asuransiBo.getEntityAsuransiById(detailCheckupEntity.getIdAsuransi());
            if (asuransiEntity != null){
                masterId = asuransiEntity.getNoMaster();
            } else {
            }

        } else if ("ptpn".equalsIgnoreCase(jenis)){
            masterId =  detailCheckupEntity.getIdAsuransi();
        } else {
            ImJenisPeriksaPasienEntity jenisPeriksaPasienEntity = jenisPriksaPasienBo.getJenisPerikasEntityById("umum");
            if (jenisPeriksaPasienEntity != null){
                masterId = jenisPeriksaPasienEntity.getMasterId();
            }
        }

        return masterId;
    }


    public String getDivisiId(String idDetailCheckup, String jenisPasien, String keterangan) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");
        PermintaanResepBo permintaanResepBo = (PermintaanResepBo) ctx.getBean("permintaanResepBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        KelasRuanganBo kelasRuanganBo = (KelasRuanganBo) ctx.getBean("kelasRuanganBoProxy");
        RuanganBo ruanganBo = (RuanganBo) ctx.getBean("ruanganBoProxy");
        RawatInapBo rawatInapBo = (RawatInapBo) ctx.getBean("rawatInapBoProxy");
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

        String divisiId = "";

        if ("resep".equalsIgnoreCase(keterangan)) {
            ItSimrsRiwayatTindakanEntity riwayatTindakanEntity = riwayatTindakanBo.getRiwayatTindakanResep(idDetailCheckup, jenisPasien);
            if (riwayatTindakanEntity != null) {
                ImSimrsPermintaanResepEntity permintaanResepEntity = permintaanResepBo.getEntityPermintaanResepById(riwayatTindakanEntity.getIdTindakan());
                if (permintaanResepEntity != null) {
                    ImSimrsPelayananEntity pelayananEntity = pelayananBo.getPelayananById(permintaanResepEntity.getTujuanPelayanan());
                    if (pelayananEntity != null) {
                        ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                        if (position != null) {
                            divisiId = position.getKodering();
                        }
                    }
                }
            }
        } else if ("laboratorium".equalsIgnoreCase(keterangan) || "radiologi".equalsIgnoreCase(keterangan)) {
            divisiId = periksaLabBo.getDivisiIdKodering(idDetailCheckup, keterangan);
        } else if ("gizi".equalsIgnoreCase(keterangan)) {

            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
            if (detailCheckupEntity != null) {

                Pelayanan pelayanan = new Pelayanan();
                pelayanan.setBranchId(detailCheckupEntity.getBranchId());
                pelayanan.setTipePelayanan("gizi");

                List<Pelayanan> pelayananList = pelayananBo.getByCriteria(pelayanan);
                if (pelayananList.size() > 0) {
                    Pelayanan pelayananData = pelayananList.get(0);

                    ImPosition position = positionBo.getPositionEntityById(pelayananData.getDivisiId());
                    if (position != null) {
                        divisiId = position.getKodering();
                    }
                }
            }

        } else {

            ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getDetailCheckupById(idDetailCheckup);
            if (detailCheckupEntity != null && detailCheckupEntity.getIdPelayanan() != null) {
                try {
                    ImSimrsPelayananEntity pelayananEntity = pelayananBo.getPelayananById(detailCheckupEntity.getIdPelayanan());

                    // jika poli selain rawat inap maka mengambil kodering dari pelayanan
                    // jika poli rawat rawat inap maka mengambil kodering dari kelas ruangan , Sigit
                    if (pelayananEntity != null && !"rawat_inap".equalsIgnoreCase(pelayananEntity.getTipePelayanan())) {

                        ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
                        if (position != null) {
                            divisiId = position.getKodering();
                        }

                    } else {

                        RawatInap lastRuangan = rawatInapBo.getLastUsedRoom(idDetailCheckup);
                        if (lastRuangan != null) {
                            MtSimrsRuanganEntity ruanganEntity = ruanganBo.getEntityRuanganById(lastRuangan.getIdRuang());
                            if (ruanganEntity != null) {
                                ImSimrsKelasRuanganEntity kelasRuanganEntity = kelasRuanganBo.getKelasRuanganById(ruanganEntity.getIdKelasRuangan());
                                if (kelasRuanganEntity != null) {
                                    ImPosition position = positionBo.getPositionEntityById(kelasRuanganEntity.getDivisiId());
                                    if (position != null) {
                                        divisiId = position.getKodering();
                                    }
                                }
                            }
                        }
                    }
                } catch (GeneralBOException e) {
                    throw new GeneralBOException("[getDivisiId] ERROR " + e);
                }
            } else {
                throw new GeneralBOException("[getDivisiId] ERROR gagal mendapakatkan divisi_id atau data detail checkup");
            }
        }
        return divisiId;
    }

    private BigDecimal getJumlahNilaiBiayaByKeterangan(String idDetailCheckup, String jenisPasien, String keterangan) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        BigDecimal nilai = new BigDecimal(0);
        try {
            nilai = checkupDetailBo.getSumJumlahTindakanByJenis(idDetailCheckup, jenisPasien, keterangan);
        } catch (GeneralBOException e) {
            logger.error("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR ", e);
            throw new GeneralBOException("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR " + e);

        }
        return nilai;
    }

    private List<Map> getAcitivityList(String idDetailCheckup, String jenisPasien, String ket, String type) {
        logger.info("[CheckupDetailAction.getAcitivityList] START >>>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TeamDokterBo teamDokterBo = (TeamDokterBo) ctx.getBean("teamDokterBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");

        //** mencari tindakan dan dimasukan ke jurnal detail activity. START **//
        // dokter team

        List<Map> activityList = new ArrayList<>();

        String idDokter = "";
        DokterTeam dokterTeam = new DokterTeam();
        dokterTeam.setIdDetailCheckup(idDetailCheckup);
        List<ItSimrsDokterTeamEntity> dokterTeamEntities = teamDokterBo.getListEntityTeamDokter(dokterTeam);
        if (dokterTeamEntities.size() > 0) {
            ItSimrsDokterTeamEntity dokterTeamEntity = dokterTeamEntities.get(0);
            idDokter = dokterTeamEntity.getIdDokter();
        }

        // riwayat tindakan list
        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
        riwayatTindakan.setIdDetailCheckup(idDetailCheckup);
        riwayatTindakan.setJenisPasien(jenisPasien);

        if ("".equalsIgnoreCase(ket)) {
            riwayatTindakan.setNotResep("Y");
        } else {
            riwayatTindakan.setKeterangan(ket);
        }

        List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = riwayatTindakanBo.getListEntityRiwayatTindakan(riwayatTindakan);
        if (riwayatTindakanEntities.size() > 0) {
            for (ItSimrsRiwayatTindakanEntity riwayatTindakanEntity : riwayatTindakanEntities) {

                // jika selain JRJ
                // maka obat dikenakan PPN
                BigDecimal ppn = new BigDecimal(0);

                // mencari apakah tindakan transitoris
                boolean nonTransitoris = true;
                if ("JRI".equalsIgnoreCase(type)) {
                    ItSimrsTindakanTransitorisEntity transitorisEntity = riwayatTindakanBo.getTindakanTransitorisById(riwayatTindakanEntity.getIdRiwayatTindakan());
                    if (transitorisEntity != null) {
                        // jika ditemukan transitoris
                        // maka transitoris;
                        nonTransitoris = false;
                    }
                }

                // jika bukan Transitoris
                // maka ditambahkan activity
                if (nonTransitoris) {
                    Map activityMap = new HashMap();
                    activityMap.put("activity_id", riwayatTindakanEntity.getIdTindakan());
                    activityMap.put("person_id", idDokter);
                    activityMap.put("nilai", riwayatTindakanEntity.getTotalTarif().add(ppn));
                    activityMap.put("no_trans", riwayatTindakanEntity.getIdDetailCheckup());
                    activityMap.put("tipe", riwayatTindakanEntity.getJenisPasien());
                    activityList.add(activityMap);
                }
            }
        }
        //** mencari tindakan dan dimasukan ke jurnal detail activity. END **//
        logger.info("[CheckupDetailAction.getAcitivityList] END <<<");
        return activityList;
    }

}
