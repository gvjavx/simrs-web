package com.neurix.simrs.transaksi.verifikatorpembayaran.action;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.mobileapi.antrian.model.Antrian;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.antriantelemedic.model.AntrianTelemedic;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.ordergizi.bo.OrderGiziBo;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import com.neurix.simrs.transaksi.paketperiksa.model.ItemPaket;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsItemPaketEntity;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.permintaanresep.bo.PermintaanResepBo;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by reza on 10/06/20.
 */
public class VerifikatorPembayaranAction {
    private final static transient Logger logger = Logger.getLogger(VerifikatorPembayaranAction.class);

    private VerifikatorPembayaranBo verifikatorPembayaranBoProxy;
    private TelemedicBo telemedicBoProxy;
    private PembayaranOnline pembayaranOnline;
    public AntrianTelemedic antrianTelemedic;

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
        CheckResponse response = new CheckResponse();

        ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranBo.getPembayaranOnlineById(idTransaksi);
        if (pembayaranOnlineEntity != null){
            String idJenisPeriksaPasien = "";

            ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(pembayaranOnlineEntity.getIdAntrianTelemedic());
            if (antrianTelemedicEntity != null){

                idJenisPeriksaPasien = antrianTelemedicEntity.getIdJenisPeriksaPasien();

                // set data headerCheckup;
                HeaderCheckup headerCheckup = new HeaderCheckup();
                headerCheckup.setIdDetailCheckup("CKP"+checkupBo.getNextHeaderId());
                headerCheckup.setIdPasien(antrianTelemedicEntity.getIdPasien());

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

                String idDetailCheckup = "";
                try {
                    idDetailCheckup = verifikatorPembayaranBo.approveTransaksi(headerCheckup);
                } catch (GeneralBOException e){
                    logger.error("[VerifikatorPembayaranAction.approveTransaksi] ERROR. ",e);
                    throw new GeneralBOException("[VerifikatorPembayaranAction.approveTransaksi] ERROR. ",e);
                }


                // approve All tindakan and save
                if (!"".equalsIgnoreCase(idDetailCheckup) && !"".equalsIgnoreCase(idJenisPeriksaPasien)){
                    response = saveApproveAllTindakanRawatJalan(idDetailCheckup, idJenisPeriksaPasien);
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
                        } catch (GeneralBOException e){
                            logger.error("[VerifikatorPembayaranAction.approveTransaksi] ERROR. ",e);
                            throw new GeneralBOException("[VerifikatorPembayaranAction.approveTransaksi] ERROR. ",e);
                        }
                    }
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

}
