package com.neurix.hris.mobileapi;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiFcmBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import com.neurix.simrs.master.kurir.bo.KurirBo;
import com.neurix.simrs.master.kurir.model.Kurir;
import com.neurix.simrs.master.license.model.Email;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.parameterketeranganobat.bo.ParameterKeteranganObatBo;
import com.neurix.simrs.master.parameterketeranganobat.bo.impl.ParameterKeteranganObatBoImpl;
import com.neurix.simrs.master.parameterketeranganobat.model.ParameterKeteranganObat;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.antriantelemedic.model.AntrianTelemedic;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import com.neurix.simrs.transaksi.kasirrawatjalan.action.KasirRawatJalanAction;
import com.neurix.simrs.transaksi.notifikasiadmin.bo.NotifikasiAdminBo;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.neurix.simrs.transaksi.verifikatorpembayaran.action.VerifikatorPembayaranAction;
import com.neurix.simrs.transaksi.verifikatorpembayaran.bo.VerifikatorPembayaranBo;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 11/06/20.
 */
public class TesTelemedicController implements ModelDriven<Object> {

    private static transient Logger logger = Logger.getLogger(TesTelemedicController.class);

    private String data;
    private String result;
    private String id;
    private String obat;
    private BigDecimal tindakan;
    private BigDecimal resep;
    private BigDecimal pasien;
    private String pembayaran;

    public String getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(String pembayaran) {
        this.pembayaran = pembayaran;
    }

    private TelemedicBo telemedicBoProxy;
    private KurirBo kurirBoProxy;
    private VerifikatorPembayaranBo verifikatorPembayaranBoProxy;
    private PasienBo pasienBoProxy;
    private PelayananBo pelayananBoProxy;
    private TeamDokterBo teamDokterBoProxy;
    private ParameterKeteranganObatBo parameterKeteranganObatBoProxy;
    private ObatBo obatBoProxy;
    private BillingSystemBo billingSystemBoProxy;

    public void setTeamDokterBoProxy(TeamDokterBo teamDokterBoProxy) {
        this.teamDokterBoProxy = teamDokterBoProxy;
    }

    public void setBillingSystemBoProxy(BillingSystemBo billingSystemBoProxy) {
        this.billingSystemBoProxy = billingSystemBoProxy;
    }

    public void setObatBoProxy(ObatBo obatBoProxy) {
        this.obatBoProxy = obatBoProxy;
    }

    public void setParameterKeteranganObatBoProxy(ParameterKeteranganObatBo parameterKeteranganObatBoProxy) {
        this.parameterKeteranganObatBoProxy = parameterKeteranganObatBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TesTelemedicController.logger = logger;
    }

    public BigDecimal getTindakan() {
        return tindakan;
    }

    public void setTindakan(BigDecimal tindakan) {
        this.tindakan = tindakan;
    }

    public BigDecimal getResep() {
        return resep;
    }

    public void setResep(BigDecimal resep) {
        this.resep = resep;
    }

    public BigDecimal getPasien() {
        return pasien;
    }

    public void setPasien(BigDecimal pasien) {
        this.pasien = pasien;
    }

    public TelemedicBo getTelemedicBoProxy() {
        return telemedicBoProxy;
    }

    public KurirBo getKurirBoProxy() {
        return kurirBoProxy;
    }

    public VerifikatorPembayaranBo getVerifikatorPembayaranBoProxy() {
        return verifikatorPembayaranBoProxy;
    }

    public PasienBo getPasienBoProxy() {
        return pasienBoProxy;
    }

    public PelayananBo getPelayananBoProxy() {
        return pelayananBoProxy;
    }

    public void setPelayananBoProxy(PelayananBo pelayananBoProxy) {
        this.pelayananBoProxy = pelayananBoProxy;
    }

    public void setPasienBoProxy(PasienBo pasienBoProxy) {
        this.pasienBoProxy = pasienBoProxy;
    }

    public String getObat() {
        return obat;
    }

    public void setObat(String obat) {
        this.obat = obat;
    }

    public void setVerifikatorPembayaranBoProxy(VerifikatorPembayaranBo verifikatorPembayaranBoProxy) {
        this.verifikatorPembayaranBoProxy = verifikatorPembayaranBoProxy;
    }

    public void setKurirBoProxy(KurirBo kurirBoProxy) {
        this.kurirBoProxy = kurirBoProxy;
    }

    public void setTelemedicBoProxy(TelemedicBo telemedicBoProxy) {
        this.telemedicBoProxy = telemedicBoProxy;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Object getModel() {
        return null;
    }

    public String index(){
        logger.info(data);
        switch (data){
            case "insert-umum-non-resep":
                insertDataTelemedic("umum", "");
                break;
            case "insert-umum-resep":
                insertDataTelemedic("umum","resep");
                break;
            case "insert-asuransi-non-resep":
                insertDataTelemedic("asuransi", "");
                break;
            case "insert-asuransi-resep":
                insertDataTelemedic("asuransi","resep");
                break;
            case "insert-bpjs-non-resep":
                insertDataTelemedic("bpjs","");
                break;
            case "insert-bpjs-resep":
                insertDataTelemedic("bpjs","resep");
                break;
            case "insert-resep":
                insertObat(this.id);
                break;
            case "insert-kurir":
                insertKurir(this.id);
                break;
            case "insert-e-resep":
                insertPemesananResep("umum");
                break;
            case "create-invoice-e-resep":
                createPembayaranResep(this.id, this.obat);
                break;
            case "bayar-resep-telemedic":
                bayar(this.id, "resep");
                break;
            case "bayar-konsultasi-telemedic":
                bayar(this.id, "konsultasi");
                break;
            case "pelayanan-telemedic":
                printLogTesListPelayananMedic();
                break;
            case "generate-num":
                generateRandom();
                break;
            case "batal-dokter":
                saveBatalDokter(this.id);
                break;
            case "hitung":
                hitiungselisi(this.tindakan, this.resep, this.pasien);
                break;
            case "bayar-tagihan-rj":
                savePembayaranTagihan(this.id);
                break;
            case "tes-team-dokter":
                tesDokterTeam(this.id);
                break;
            case "tes-keterangan-obat":
                searchParameterKeterangan();
                break;
            case "tes-sum-obat":
                testSumObat();
                break;
            case "tes-approve-va":
                testApproveVa();
                break;
            case "tes-fcm":
                sendNotifFcm();
                break;
            case "test-email":
                testSendMail();
                break;
            default:
                logger.info("==========NO ONE CARE============");
        }
        return result;
    }

    private void insertDataTelemedic(String tipe, String jenis){

        logger.info("[TesTelemedicController.insertDataTelemedic] START >>>");

        Timestamp time = new Timestamp(System.currentTimeMillis());
        String jenisPembayaran = this.pembayaran;

        ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = new ItSimrsAntrianTelemedicEntity();
        antrianTelemedicEntity.setBranchId("RS01");
        antrianTelemedicEntity.setIdPelayanan("PYN00000002");
        antrianTelemedicEntity.setIdDokter("DKR00000012");
        antrianTelemedicEntity.setKodeBank("1.1.01.02.01");
        antrianTelemedicEntity.setIdJenisPeriksaPasien(tipe);
        if ("resep".equalsIgnoreCase(jenis)){
            antrianTelemedicEntity.setFlagResep("Y");
        }
        if ("asuransi".equalsIgnoreCase(tipe)){
            antrianTelemedicEntity.setIdPasien("RS0104200035");
            antrianTelemedicEntity.setNoKartu("080780808");
            antrianTelemedicEntity.setIdAsuransi("ASN00000002");
        } else if ("bpjs".equalsIgnoreCase(tipe)){
            ImSimrsPasienEntity pasienEntity = pasienBoProxy.getPasienById("RS0104200033");
            if (pasienEntity != null){
                antrianTelemedicEntity.setIdPasien("RS0104200033");
                antrianTelemedicEntity.setNoKartu(pasienEntity.getNoBpjs());
            }
            antrianTelemedicEntity.setKeluhan("Tenggorokan Serak Selama 1 Minggu.");
        } else {
            antrianTelemedicEntity.setIdPasien("RS0104200035");
        }
        antrianTelemedicEntity.setCreatedDate(time);
        antrianTelemedicEntity.setCreatedWho("admin");
        antrianTelemedicEntity.setLastUpdate(time);
        antrianTelemedicEntity.setLastUpdateWho("admin");
        antrianTelemedicEntity.setAlamat("Kartika Mas Regenci No 60");
        antrianTelemedicEntity.setLat("-7.345948");
        antrianTelemedicEntity.setLon("112.746283");
        antrianTelemedicEntity.setNoTelp("089090909909");
        antrianTelemedicEntity.setJenisPengambilan("kirim");

        try {
            telemedicBoProxy.saveAdd(antrianTelemedicEntity, "03", "1.1.01.02.01", jenisPembayaran);
        } catch (GeneralBOException e){
            logger.error("[TesTelemedicController.insertDataTelemedic] ERROR. ",e);
            throw new GeneralBOException("[TesTelemedicController.insertDataTelemedic] ERROR. ", e);
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiAdminBo notifikasiAdminBo = (NotifikasiAdminBo) ctx.getBean("notifikasiAdminBoProxy");

        logger.info("[TesTelemedicController.insertDataTelemedic] END <<<");
    }

    private void insertPemesananResep(String tipe){

        logger.info("[TesTelemedicController.insertDataTelemedic] START >>>");

        Timestamp time = new Timestamp(System.currentTimeMillis());
        String jenisPembayaran = this.pembayaran;

        ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = new ItSimrsAntrianTelemedicEntity();
        antrianTelemedicEntity.setBranchId("RS01");
        antrianTelemedicEntity.setIdPasien("RS0104200035");
        antrianTelemedicEntity.setIdPelayanan("PYN00000024");
//        antrianTelemedicEntity.setIdDokter("DKR00000012");
//        antrianTelemedicEntity.setKodeBank("1.1.01.02.01");
        antrianTelemedicEntity.setIdJenisPeriksaPasien(tipe);
        antrianTelemedicEntity.setFlagResep("Y");
        antrianTelemedicEntity.setFlagEresep("Y");
        if ("ansuransi".equalsIgnoreCase(tipe)){
            antrianTelemedicEntity.setNoKartu("080780808");
            antrianTelemedicEntity.setIdAsuransi("ASN00000002");
        }
        antrianTelemedicEntity.setCreatedDate(time);
        antrianTelemedicEntity.setCreatedWho("admin");
        antrianTelemedicEntity.setLastUpdate(time);
        antrianTelemedicEntity.setLastUpdateWho("admin");
        antrianTelemedicEntity.setAlamat("Kartika Mas Regenci No 60");
        antrianTelemedicEntity.setLat("-7.345948");
        antrianTelemedicEntity.setLon("112.746283");
        antrianTelemedicEntity.setNoTelp("089090909909");
        antrianTelemedicEntity.setJenisPengambilan("kirim");

        try {
            telemedicBoProxy.saveAdd(antrianTelemedicEntity, "RS01", "", this.pembayaran);
        } catch (GeneralBOException e){
            logger.error("[TesTelemedicController.insertDataTelemedic] ERROR. ",e);
            throw new GeneralBOException("[TesTelemedicController.insertDataTelemedic] ERROR. ", e);
        }

        logger.info("[TesTelemedicController.insertDataTelemedic] END <<<");
    }

    private void bayar(String idAntrianTelemedic, String jenis){
        logger.info("[TesTelemedicController.bayarResep] START >>>");

        Timestamp time = CommonUtil.getCurrentDateTimes();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");

        AntrianTelemedic antrianTelemedic = new AntrianTelemedic();
        antrianTelemedic.setId(idAntrianTelemedic);
        if ("resep".equalsIgnoreCase(jenis)){
            antrianTelemedic.setFlagBayarResep("Y");
        } else {
            antrianTelemedic.setFlagBayarKonsultasi("Y");
        }
        antrianTelemedic.setAction("U");
        antrianTelemedic.setLastUpdate(time);
        antrianTelemedic.setLastUpdateWho("admin");

        try {
            telemedicBoProxy.saveEdit(antrianTelemedic, "", "");
        } catch (GeneralBOException e){
            logger.error("[TesTelemedicController.bayarResep] ERROR. ",e);
            throw new GeneralBOException("[TesTelemedicController.bayarResep] ERROR. ", e);
        }

        ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranBo.getPembayaranOnlineEntityByIdAntrianAndJenis(idAntrianTelemedic, jenis);
        if (pembayaranOnlineEntity != null){

            pembayaranOnlineEntity.setUrlFotoBukti("bukti.png");
            pembayaranOnlineEntity.setKodeBank("1.1.01.02.01");

            try {
                verifikatorPembayaranBo.saveEdit(pembayaranOnlineEntity);
            } catch (GeneralBOException e){
                logger.error("[TesTelemedicController.bayarResep] Update Pembayaran Online ERROR. ",e);
                throw new GeneralBOException("[TesTelemedicController.bayarResep] Update Pembayaran Online ERROR. ", e);
            }

        } else {
            logger.error("[TesTelemedicController.bayarResep] Tidak ditemukan data. ");
            throw new GeneralBOException("[TesTelemedicController.bayarResep] Tidak ditemukan data. ");
        }


        logger.info("[TesTelemedicController.bayarResep] END <<<");
    }

    private void insertObat(String idTransksiObat){
        logger.info("[TesTelemedicController.insertObat] START >>>");

        Timestamp time = new Timestamp(System.currentTimeMillis());

        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
        transaksiObatDetail.setIdObat("OBT00000030");
        transaksiObatDetail.setIdPelayanan("PYN00000024");
        transaksiObatDetail.setQty(new BigInteger("5"));
        transaksiObatDetail.setFlag("Y");
        transaksiObatDetail.setAction("C");
        transaksiObatDetail.setCreatedDate(time);
        transaksiObatDetail.setCreatedWho("admin");

        List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();
        transaksiObatDetails.add(transaksiObatDetail);
        try {
            telemedicBoProxy.insertResepOnline(idTransksiObat, transaksiObatDetails);
        } catch (GeneralBOException e){
            logger.error("[TesTelemedicController.insertObat] ERROR. ",e);
            throw new GeneralBOException("[TesTelemedicController.insertObat] ERROR. ", e);
        }

        logger.info("[TesTelemedicController.insertObat] END <<<");
    }

    private void insertKurir(String nama){
        logger.info("[TesTelemedicController.insertKurir] START >>>");

        Timestamp time = new Timestamp(System.currentTimeMillis());

        Kurir kurir = new Kurir();
        kurir.setNama(nama);
        kurir.setBranchId("RS01");
        kurir.setFlagReady("Y");
        kurir.setNoKtp("1212120012");
        kurir.setNoTelp("0089808008080");
        kurir.setFlag("Y");
        kurir.setAction("C");
        kurir.setCreatedDate(time);
        kurir.setCreatedWho("Admin");
        kurir.setLastUpdate(time);
        kurir.setLastUpdateWho("Admin");
        kurir.setPassword("123");
        kurir.setNoPolisi("S8908WD");

        try {
            kurirBoProxy.saveAdd(kurir);
        } catch (GeneralBOException e){
            logger.error("[TesTelemedicController.insertKurir] ERROR. ",e);
            throw new GeneralBOException("[TesTelemedicController.insertKurir] ERROR. ", e);
        }

        logger.info("[TesTelemedicController.insertKurir] END <<<");
    }

    private void createPembayaranResep(String idAntrianOnline, String idObat){
        logger.info("[TesTelemedicController.insertObat] START >>>");

        Timestamp time = new Timestamp(System.currentTimeMillis());

        AntrianTelemedic antrianTelemedic = new AntrianTelemedic();
        ItSimrsAntrianTelemedicEntity telemedicEntity = telemedicBoProxy.getAntrianTelemedicEntityById(idAntrianOnline);
        if (telemedicEntity != null){

            antrianTelemedic.setId(telemedicEntity.getId());
            antrianTelemedic.setCreatedDate(time);
            antrianTelemedic.setLastUpdate(time);
            antrianTelemedic.setCreatedWho("admin");
            antrianTelemedic.setLastUpdateWho("admin");

            List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();
            TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
            transaksiObatDetail.setIdObat(idObat);
            transaksiObatDetail.setIdPelayanan(telemedicEntity.getId());
            transaksiObatDetail.setQty(new BigInteger("5"));
            transaksiObatDetail.setFlag("Y");
            transaksiObatDetail.setAction("C");
            transaksiObatDetail.setCreatedDate(time);
            transaksiObatDetail.setCreatedWho("admin");

            transaksiObatDetails.add(transaksiObatDetail);

            try {
                telemedicBoProxy.createPembayaranResep(antrianTelemedic, transaksiObatDetails);
            } catch (GeneralBOException e){
                logger.error("[TesTelemedicController.insertObat] ERROR. ",e);
                throw new GeneralBOException("[TesTelemedicController.insertObat] ERROR. ", e);
            }
        }
        logger.info("[TesTelemedicController.insertObat] END <<<");
    }

    private void printLogTesListPelayananMedic(){
        Map hsCriteria = new HashMap();
        hsCriteria.put("branch_id", "RS01");
        hsCriteria.put("in_pelayanan_medic", "Y");
        hsCriteria.put("flag", "Y");
        List<ImSimrsPelayananEntity> pelayananEntities = pelayananBoProxy.getByCriteria(hsCriteria);
        logger.info(pelayananEntities);
    }

    private void generateRandom() {
        int min = 1;
        int max = 1000;
        //Generate random double value from 50 to 100
        logger.info("Random value in double from "+min+" to "+max+ ":");
        double random_double = Math.random() * (max - min + 1) + min;
        logger.info(random_double);

        //Generate random int value from 50 to 100
        logger.info("Random value in int from "+min+" to "+max+ ":");
        int random_int = (int)(Math.random() * (max - min + 1) + min);
        logger.info(random_int);
    }

    private void saveBatalDokter(String jenis){

        AntrianTelemedic antrianTelemedic = new AntrianTelemedic();
        antrianTelemedic.setIdPelayanan("PYN00000002");
        antrianTelemedic.setIdDokter("DKR00000012");
        antrianTelemedic.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        antrianTelemedic.setLastUpdateWho("admin");
        //antrianTelemedic.setIdJenisPeriksaPasien(jenis);

        try {
            telemedicBoProxy.processBatalDokter(antrianTelemedic, "Ada Keperluan Mendadak");
        } catch (GeneralBOException e){
            logger.error("[TesTelemedicController.insertObat] saveBatalDokter. ",e);
            throw new GeneralBOException("[TesTelemedicController.insertObat] saveBatalDokter. ", e);
        }
    }

    private void hitiungselisi(BigDecimal biayaTindakan, BigDecimal biayaResep, BigDecimal dibayarPasien){

        BigDecimal total = biayaTindakan.add(biayaResep);
        System.out.println("Biaya Tindakan : " + biayaTindakan);
        System.out.println("Biaya Resep : "+ biayaResep);
        System.out.println("Total Biaya : "+ total);
        System.out.println("================================");
        BigDecimal totalDiCover = total.subtract(dibayarPasien);
        System.out.println("Dicover Asuransi : "+ totalDiCover);
        System.out.println("Dibayar Pasien : "+ dibayarPasien);
//        BigDecimal persenDibayar = total.divide((dibayarPasien.multiply(new BigDecimal(100))), BigDecimal.ROUND_HALF_UP, 2);
        BigDecimal persenDibayar = dibayarPasien.divide(total, BigDecimal.ROUND_HALF_UP, 2).multiply(new BigDecimal(100));
        System.out.println("Persen dibayar Pasien dari Cover : " + persenDibayar);
        System.out.println("================================");
        BigDecimal dibayarDariTindakan = biayaTindakan.multiply(persenDibayar.divide(new BigDecimal(100), BigDecimal.ROUND_HALF_UP, 2));
        System.out.println("Tindakan dibayar Pasien dari Cover : " + dibayarDariTindakan);
        BigDecimal dibayarDariResep = biayaResep.multiply(persenDibayar.divide(new BigDecimal(100), BigDecimal.ROUND_HALF_UP, 2));
        System.out.println("Resep dibayar Pasien dari Cover : " + dibayarDariTindakan);

        BigDecimal selisih = new BigDecimal(0);
        BigDecimal totalHasilBagi = dibayarDariTindakan.add(dibayarDariResep);

        System.out.println("================================");

        // jika di olah
        if (dibayarPasien.compareTo(totalHasilBagi) == 1){
            selisih = dibayarPasien.subtract(totalHasilBagi);
            selisih = selisih.divide(new BigDecimal(2), BigDecimal.ROUND_HALF_UP, 2);
            dibayarDariTindakan = dibayarDariTindakan.add(selisih);
            dibayarDariResep = dibayarDariResep.add(selisih);
            System.out.println("Tindakan dibayar Pasien Dari Cover setelah Diolah (+) : " + dibayarDariTindakan);
            System.out.println("Resep dibayar Pasien Dari Cover setelah Diolah (+) : " + dibayarDariResep);
        }  else {
            selisih = totalHasilBagi.subtract(dibayarPasien);
            selisih = selisih.divide(new BigDecimal(2), BigDecimal.ROUND_HALF_UP, 2);
            dibayarDariTindakan = dibayarDariTindakan.subtract(selisih);
            dibayarDariResep = dibayarDariResep.subtract(selisih);
            System.out.println("Tindakan dibayar Pasien Dari Cover setelah Diolah (-) : " + dibayarDariTindakan);
            System.out.println("Resep dibayar Pasien Dari Cover setelah Diolah (-) : " + dibayarDariResep);
        }
        System.out.println("Total dibayar Pasien : " + dibayarDariTindakan.add(dibayarDariResep));

    }

    public void savePembayaranTagihan(String noCheckup) {

        // noCheckup = CKP00000125

        KasirRawatJalanAction kasirRawatJalanAction = new KasirRawatJalanAction();
        try {
//            kasirRawatJalanAction.savePembayaranTagihan(
//                    "",
//                    "RS0104200035",
//                    "",
//                    "Y",
//                    "transfer",
//                    "1.1.01.02.02",
//                    "JRJ",
//                    "tunai",
//                    "01010101010101",
//                    noCheckup
//            );
        } catch (GeneralBOException e){
            logger.error("[TesTelemedicController.savePembayaranTagihan] Save Pembayaran Tagihan TEST. ",e);
        }
//        catch (JSONException e) {
//            logger.error("[TesTelemedicController.savePembayaranTagihan] Save Pembayaran Tagihan TEST JSON. ",e);
//            e.printStackTrace();
//        }

    }

    public void tesDokterTeam(String id){

        DokterTeam dokterTeam = new DokterTeam();
        dokterTeam.setIdDetailCheckup(id);

        List<DokterTeam> dokterTeams = new ArrayList<>();
        DokterTeam resultDokter = new DokterTeam();


        if ("nama".equalsIgnoreCase(this.result)){
            try {
                dokterTeams = teamDokterBoProxy.getByCriteria(dokterTeam);
            } catch (GeneralBOException e){
                logger.error("[TesTelemedicController.tesDokterTeam] Test dokter team. pelayanan",e);
            }
        }

        if ("pelayanan".equalsIgnoreCase(this.result)){

            try {
                resultDokter  = teamDokterBoProxy.getNamaDokter(id, true);
            } catch (GeneralBOException e){
                logger.error("[TesTelemedicController.tesDokterTeam] Test dokter team. dokter",e);
            }
        }
    }

    public void searchParameterKeterangan(){
        logger.info("[TesTelemedicController.searchParameterKeterangan] START >>>");
        ParameterKeteranganObat param = new ParameterKeteranganObat();
        List listParam = new ArrayList();

        try {
            listParam = parameterKeteranganObatBoProxy.getByCriteria(param);
        } catch (GeneralBOException e){
            logger.error("[TesTelemedicController.searchParameterKeterangan] ERROR. ",e);
        }

        System.out.println("ISI -> "+listParam.size());
        logger.info("[TesTelemedicController.searchParameterKeterangan] END <<<");
    }

    public void testSumObat(){
        logger.info("[TesTelemedicController.testSumObat] START >>>");

        try {
            obatBoProxy.testSumPersediaanObat(this.id, "", "RS01");
        } catch (GeneralBOException e){
            logger.error("[TesTelemedicController.searchParameterKeterangan] ERROR. ",e);
        }

        logger.info("[TesTelemedicController.testSumObat] END <<<");
    }

    public void testApproveVa(){
        logger.info("[TesTelemedicController.testApproveVa] START >>>");

        String id = this.id;

        try {
            billingSystemBoProxy.testApproveVA(id);
        } catch (GeneralBOException e){
            logger.error("[TesTelemedicController.testApproveVa] ERROR. ",e);
        }

        logger.info("[TesTelemedicController.testApproveVa] END <<<");
    }

    public void sendNotifFcm(){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiFcmBo notifikasiFcmBo = (NotifikasiFcmBo) ctx.getBean("notifikasiFcmBoProxy");
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

        List<NotifikasiFcm> notifikasiFcm = null;

        String note = "Pembayaran Virtual Account Telah Berhasil. Anda telah memasuki Antrian Short List. Buka aplikasi untuk menunggu panggilan dokter";

        Notifikasi notifBean = new Notifikasi();
        notifBean.setTipeNotifId("TN10");
        notifBean.setNip("022020003408");
        notifBean.setNamaPegawai("admin");
        notifBean.setNote(note);
        notifBean.setTo("022020003408");
        notifBean.setFromPerson("admin");
        notifBean.setNoRequest("TMC2021051000403");
        notifBean.setFlag("Y");
        notifBean.setRead("N");
        notifBean.setAction("C");
        notifBean.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        notifBean.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        notifBean.setCreatedWho("admin");
        notifBean.setLastUpdateWho("admin");

        notifikasiBo.saveAdd(notifBean);

        NotifikasiFcm bean = new NotifikasiFcm();

        //Push Notif ke Pasien terkait perubahan status menjadi SL
        bean.setUserId("022020003408");
        notifikasiFcm = notifikasiFcmBo.getByCriteria(bean);
        FirebasePushNotif.sendNotificationFirebase(notifikasiFcm.get(0).getTokenFcm(),"Telemedic", note, "SL", notifikasiFcm.get(0).getOs(), null);
    }

    public void testSendMail(){

        Email email = new Email();
        email.setFrom(CommonConstant.EMAIL_USERNAME);
        email.setPassword(CommonConstant.EMAIL_PASSWORD);
        email.setTo(CommonConstant.LICENSE_EMAIL_TO);
        email.setSubject("License Key Verification");
        email.setMsg("<h2>License Key Verification</h2>\n" +
                "=========================================\n" +
                "<table width=\"100%\">\n" +
                "<tr>\n" +
                "<td width=\"20%\">EMAIL TEXT</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "=========================================\n" +
                "<br> \n" +
                "<br>\n" +
                "<span style=\"color: blue\">click this button for activation!</span>\n");
        CommonUtil.sendEmail(email);

    }
}
