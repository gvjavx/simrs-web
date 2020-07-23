package com.neurix.simrs.transaksi.verifikatorpembayaran.action;

import com.neurix.akuntansi.master.master.bo.MasterBo;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiFcmBo;
import com.neurix.hris.transaksi.notifikasi.model.ItNotifikasiFcmEntity;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import com.neurix.simrs.bpjs.BpjsService;
import com.neurix.simrs.bpjs.eklaim.bo.EklaimBo;
import com.neurix.simrs.bpjs.eklaim.model.*;
import com.neurix.simrs.bpjs.vclaim.bo.BpjsBo;
import com.neurix.simrs.bpjs.vclaim.model.SepRequest;
import com.neurix.simrs.bpjs.vclaim.model.SepResponse;
import com.neurix.simrs.master.diagnosa.model.Diagnosa;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.jenisperiksapasien.bo.AsuransiBo;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.ImJenisPeriksaPasienEntity;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import com.neurix.simrs.master.kelasruangan.bo.KelasRuanganBo;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.ImSimrsPasienEntity;
import com.neurix.simrs.master.pasien.model.Pasien;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.ruangan.bo.RuanganBo;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
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
import com.neurix.simrs.transaksi.reseponline.bo.ResepOnlineBo;
import com.neurix.simrs.transaksi.reseponline.model.ResepOnline;
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
import com.neurix.simrs.transaksi.verifikatorasuransi.bo.VerifikatorAsurasiBo;
import com.neurix.simrs.transaksi.verifikatorasuransi.model.ItSimrsStrukAsuransiEntity;
import com.neurix.simrs.transaksi.verifikatorasuransi.model.StrukAsuransi;
import com.neurix.simrs.transaksi.verifikatorpembayaran.bo.VerifikatorPembayaranBo;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.PembayaranOnline;
import io.agora.recording.common.Common;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    private NotifikasiBo notifikasiBoProxy;

    public static Logger getLogger() {
        return logger;
    }

    public VerifikatorPembayaranBo getVerifikatorPembayaranBoProxy() {
        return verifikatorPembayaranBoProxy;
    }

    public TelemedicBo getTelemedicBoProxy() {
        return telemedicBoProxy;
    }

    public NotifikasiBo getNotifikasiBoProxy() {
        return notifikasiBoProxy;
    }

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

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
        searchAntrian.setStatusTransaksi(antrianTelemedic.getStatusTransaksi());
        searchAntrian.setIdPasien(antrianTelemedic.getIdPasien());
        searchAntrian.setIdJenisPeriksaPasien(antrianTelemedic.getIdJenisPeriksaPasien());

        if (antrianTelemedic != null){
            searchAntrian.setStatus(antrianTelemedic.getStatus());
            searchAntrian.setIdPelayanan(antrianTelemedic.getIdPelayanan());
            searchAntrian.setId(antrianTelemedic.getId());
            searchAntrian.setIdTransaksi(antrianTelemedic.getIdTransaksi());
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

    public CheckResponse approveEresep(String idTransaksi){
        logger.info("[VerifikatorPembayaranAction.approveTransaksi] START >>>");

        Timestamp time = CommonUtil.getCurrentDateTimes();
        String userLogin = CommonUtil.userLogin();

        CheckResponse response = new CheckResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");
        ResepOnlineBo resepOnlineBo = (ResepOnlineBo) ctx.getBean("resepOnlineBoProxy");

        ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranBo.getPembayaranOnlineById(idTransaksi);
        if (pembayaranOnlineEntity != null){

            ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(pembayaranOnlineEntity.getIdAntrianTelemedic());
            if (antrianTelemedicEntity != null){

                PermintaanResep permintaanResep = new PermintaanResep();
                permintaanResep.setIdPelayanan(antrianTelemedicEntity.getId());
                permintaanResep.setTujuanPelayanan(antrianTelemedicEntity.getIdPelayanan());
                permintaanResep.setIdPasien(antrianTelemedicEntity.getIdPasien());
                permintaanResep.setCreatedWho(userLogin);
                permintaanResep.setLastUpdate(time);
                permintaanResep.setCreatedDate(time);
                permintaanResep.setLastUpdateWho(userLogin);
                permintaanResep.setAction("C");
                permintaanResep.setFlag("Y");
                permintaanResep.setBranchId(antrianTelemedicEntity.getBranchId());
                permintaanResep.setJenisResep(antrianTelemedicEntity.getIdJenisPeriksaPasien());
                permintaanResep.setIdTransaksiOnline(idTransaksi);

                List<TransaksiObatDetail> obatDetails = new ArrayList<>();
                ResepOnline resepOnline = new ResepOnline();
                resepOnline.setIdTransaksiOnline(idTransaksi);
                List<ResepOnline> resepOnlines = resepOnlineBo.getByCriteria(resepOnline);
                if (resepOnlines.size() > 0){
                    // set to obat details
                    for (ResepOnline resep : resepOnlines){

                        TransaksiObatDetail detail = new TransaksiObatDetail();
                        detail.setIdObat(resep.getIdObat());
                        detail.setKeterangan(resep.getKeterangan());
                        detail.setQty(resep.getQty());
                        detail.setJenisSatuan("biji");
                        obatDetails.add(detail);
                    }
                } else {
                    String errorMsg = "[VerifikatorPembayaranAction.approveEresep] Tidak Ditemukan Resep";
                    logger.error(errorMsg);
                    response.setStatus("error");
                    response.setMessage(errorMsg);
                    return response;
                }

                try {

                    String idPermintaanResep = verifikatorPembayaranBo.saveAddResep(permintaanResep, obatDetails);
                    if (!"".equalsIgnoreCase(idPermintaanResep)){

                        // Update Apporve Flag
                        pembayaranOnlineEntity.setIdItem(idPermintaanResep);
                        pembayaranOnlineEntity.setApprovedFlag("Y");
                        pembayaranOnlineEntity.setAction("U");
                        pembayaranOnlineEntity.setApprovedWho(userLogin);
                        pembayaranOnlineEntity.setLastUpdate(time);
                        pembayaranOnlineEntity.setLastUpdateWho(userLogin);
                        try {
                            verifikatorPembayaranBo.saveEdit(pembayaranOnlineEntity);
                        } catch (GeneralBOException e){
                            logger.error("[VerifikatorPembayaranAction.approveEresep] ERROR. ",e);
                            response.setStatus("error");
                            response.setMessage("[VerifikatorPembayaranAction.approveEresep] ERROR. " + e);
                            return response;
                        }

                        // jika E-Obat maka create Jurnal Pembelian Obat langsung
                        if ("Y".equalsIgnoreCase(antrianTelemedicEntity.getFlagEresep())){

                            TransaksiObatDetail trans = new TransaksiObatDetail();
                            trans.setTotalBayar( new BigInteger(pembayaranOnlineEntity.getNominal().toString()));
                            trans.setIdPelayanan(antrianTelemedicEntity.getIdPelayanan());

                            JurnalResponse jurnalResponse = createJurnalPembayaranObatBaruEObat(trans);
                            if ("success".equalsIgnoreCase(jurnalResponse.getStatus())){

                                // update no_jurnal pada antrian telemedics
                                AntrianTelemedic antrian = new AntrianTelemedic();
                                antrian.setId(antrianTelemedicEntity.getId());
                                antrian.setNoJurnal(jurnalResponse.getNoJurnal());
                                antrian.setLastUpdate(time);
                                antrian.setLastUpdateWho(userLogin);
                                antrian.setStatus("FN");

                                try {
                                    telemedicBo.saveEdit(antrian,"","");
                                } catch (GeneralBOException e){
                                    logger.error("[VerifikatorPembayaranAction.approveEresep] ERROR. ",e);
                                    response.setStatus("error");
                                    response.setMessage("[VerifikatorPembayaranAction.approveEresep] ERROR. " + e);
                                    return response;
                                }

                            } else {
                                response.setStatus("error");
                                response.setMessage(jurnalResponse.getMsg());
                                return response;
                            }
                        }

                        // Update Status FN / Finish to Antrian
//                        AntrianTelemedic antrianTelemedic = new AntrianTelemedic();
//                        antrianTelemedic.setId(antrianTelemedicEntity.getId());
//                        antrianTelemedic.setStatus("FN");
//                        antrianTelemedic.setAction("U");
//                        antrianTelemedic.setLastUpdate(time);
//                        antrianTelemedic.setLastUpdateWho(userLogin);
//                        try {
//                            telemedicBo.saveEdit(antrianTelemedic, "", "");
//                        } catch (GeneralBOException e){
//                            logger.error("[VerifikatorPembayaranAction.approveEresep] ERROR. ",e);
//                            response.setStatus("error");
//                            response.setMessage("[VerifikatorPembayaranAction.approveEresep] ERROR. " + e);
//                            return response;
//                        }
                    }

                    response.setStatus("success");
                } catch (GeneralBOException e){
                    String errorMsg = "[VerifikatorPembayaranAction.approveEresep] ERROR. ";
                    logger.error(errorMsg,e);
                    response.setStatus("error");
                    response.setMessage(errorMsg+ e);
                    return response;
                }
            }
        }

        logger.info("[VerifikatorPembayaranAction.approveEresep] END <<<");
        return response;
    }

    public CheckResponse approveTransaksi(String idTransaksi, String user, String branch){
        logger.info("[VerifikatorPembayaranAction.approveTransaksi] START >>>");

        Timestamp time = new Timestamp(System.currentTimeMillis());
        String userLogin = "";
        String branchId = "";

        if (user == null || "".equalsIgnoreCase(user)){
            userLogin = CommonUtil.userIdLogin();
        } else {
            userLogin = user;
        }
        if (branch == null || "".equalsIgnoreCase(branch)){
            branchId = CommonUtil.userBranchLogin();
        } else {
            branchId = branch;
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        NotifikasiFcmBo notifikasiFcmBo = (NotifikasiFcmBo) ctx.getBean("notifikasiFcmBoProxy");
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");
        CheckResponse response = new CheckResponse();

        ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranBo.getPembayaranOnlineById(idTransaksi);
        if (pembayaranOnlineEntity != null){

            String idJenisPeriksaPasien = "";
            String idDetailCheckup = "";

            ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(pembayaranOnlineEntity.getIdAntrianTelemedic());
            if (antrianTelemedicEntity != null){

                List<NotifikasiFcm> notifikasiFcm = null;
                NotifikasiFcm bean = new NotifikasiFcm();

                idJenisPeriksaPasien = antrianTelemedicEntity.getIdJenisPeriksaPasien();
                HeaderCheckup headerCheckup = new HeaderCheckup();
                String flagResep = "N";

                String noCheckup = "";
                ItSimrsHeaderChekupEntity checkAntrianOnline = checkupBo.getById("idAntrianOnline", antrianTelemedicEntity.getId());
                if (checkAntrianOnline != null){
                    noCheckup = checkAntrianOnline.getNoCheckup();
                } else {
                    noCheckup = "CKP"+checkupBo.getNextHeaderId();
                }

                // jika resep
                if ("resep".equalsIgnoreCase(pembayaranOnlineEntity.getKeterangan())){
                    flagResep = "Y";

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
                        headerCheckup.setNoCheckup(noCheckup);
                        headerCheckup.setBranchId(branchId);
                        headerCheckup.setIdPasien(antrianTelemedicEntity.getIdPasien());

                        if ("asuransi".equalsIgnoreCase(antrianTelemedicEntity.getIdJenisPeriksaPasien())){
                            headerCheckup.setIdAsuransi(antrianTelemedicEntity.getIdAsuransi());
                            headerCheckup.setNoKartuAsuransi(antrianTelemedicEntity.getNoKartu());
                            headerCheckup.setCoverBiaya(antrianTelemedicEntity.getJumlahCover() == null ? new BigDecimal(0) : antrianTelemedicEntity.getJumlahCover());
                        } else if ("bpjs".equalsIgnoreCase(antrianTelemedicEntity.getIdJenisPeriksaPasien())){
                            headerCheckup.setNoBpjs(antrianTelemedicEntity.getNoKartu());
                            headerCheckup.setTarifBpjs(antrianTelemedicEntity.getJumlahCover());
                            headerCheckup.setCoverBiaya(antrianTelemedicEntity.getJumlahCover());
                        }
                    }

                    String idPermintaanResep = "";
                    try {
                        idPermintaanResep = verifikatorPembayaranBo.approveTransaksiResep(headerCheckup, idTransaksi);
                        response.setStatus("success");
                    } catch (GeneralBOException e){
                        logger.error("[VerifikatorPembayaranAction.approveTransaksi] ERROR. ",e);
                        response.setStatus("error");
                        response.setMessage("[VerifikatorPembayaranAction.approveTransaksi] ERROR. " + e);
                        return response;
                    }

                    if (!"".equalsIgnoreCase(idPermintaanResep)){
                        pembayaranOnlineEntity.setIdItem(idPermintaanResep);
                        pembayaranOnlineEntity.setApprovedFlag("Y");
                        pembayaranOnlineEntity.setAction("U");
                        pembayaranOnlineEntity.setApprovedWho(userLogin);
                        pembayaranOnlineEntity.setLastUpdate(time);
                        pembayaranOnlineEntity.setLastUpdateWho(userLogin);

                        try {
                            verifikatorPembayaranBo.saveEdit(pembayaranOnlineEntity);

                            Notifikasi notifBean = new Notifikasi();

                            if(antrianTelemedicEntity.getFlagEresep() != null) {
                                if(antrianTelemedicEntity.getFlagEresep().equalsIgnoreCase("Y")){
                                    notifBean.setTipeNotifId("TN11");
                                } else notifBean.setTipeNotifId("TN10");
                            } else notifBean.setTipeNotifId("TN10");

                            notifBean.setNip(antrianTelemedicEntity.getIdPasien());
                            notifBean.setNamaPegawai("admin");
                            notifBean.setNote("Pembayaran resep telah dikonfirmasi");
                            notifBean.setTo(antrianTelemedicEntity.getIdPasien());
                            notifBean.setFromPerson("admin");
                            notifBean.setNoRequest(antrianTelemedicEntity.getId());
                            notifBean.setFlag("Y");
                            notifBean.setRead("N");
                            notifBean.setAction("C");
                            notifBean.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                            notifBean.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                            notifBean.setCreatedWho("admin");
                            notifBean.setLastUpdateWho("admin");

                            notifikasiBo.saveAdd(notifBean);

                            //Push Notif ke Pasien terkait perubahan status menjadi WL
                            bean.setUserId(antrianTelemedicEntity.getIdPasien());
                            notifikasiFcm = notifikasiFcmBo.getByCriteria(bean);
                            FirebasePushNotif.sendNotificationFirebase(notifikasiFcm.get(0).getTokenFcm(),"Resep", "Pembayaran resep telah dikonfirmasi", "WL", notifikasiFcm.get(0).getOs(), null);

                            response.setStatus("success");
                            return response;
                        } catch (GeneralBOException e){
                            logger.error("[VerifikatorPembayaranAction.approveTransaksi] ERROR. ",e);
                            response.setStatus("error");
                            response.setMessage("[VerifikatorPembayaranAction.approveTransaksi] ERROR. " + e);
                            return response;
                        }
                    }

                } else {


                    // set data headerCheckup;
                    headerCheckup.setNoCheckup(noCheckup);
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
                        headerCheckup.setNoCheckup(noCheckup);
                        headerCheckup.setBranchId(branchId);
                        headerCheckup.setIdPasien(antrianTelemedicEntity.getIdPasien());
                        headerCheckup.setTglKeluar(time);

                        if ("asuransi".equalsIgnoreCase(antrianTelemedicEntity.getIdJenisPeriksaPasien())){
                            headerCheckup.setIdAsuransi(antrianTelemedicEntity.getIdAsuransi());
                            headerCheckup.setNoKartuAsuransi(antrianTelemedicEntity.getNoKartu());
                            headerCheckup.setCoverBiaya(antrianTelemedicEntity.getJumlahCover() == null ? new BigDecimal(0) : antrianTelemedicEntity.getJumlahCover());
                        } else if ("bpjs".equalsIgnoreCase(antrianTelemedicEntity.getIdJenisPeriksaPasien())){
                            headerCheckup.setNoBpjs(antrianTelemedicEntity.getNoKartu());
                            headerCheckup.setNoSep(antrianTelemedicEntity.getNoSep());
                            headerCheckup.setDiagnosa(antrianTelemedicEntity.getIdDiagnosa());
                            headerCheckup.setNamaDiagnosa(antrianTelemedicEntity.getKetDiagnosa());
                            headerCheckup.setTarifBpjs(antrianTelemedicEntity.getJumlahCover());
                            headerCheckup.setCoverBiaya(antrianTelemedicEntity.getJumlahCover());
                        }

                        Tindakan tindakan = new Tindakan();
                        List<Tindakan> tindakans = new ArrayList<>();
                        tindakan.setIdTindakan(pembayaranOnlineEntity.getIdItem());
                        tindakans.add(tindakan);

                        headerCheckup.setTindakanList(tindakans);
                    }

                    try {
                        idDetailCheckup = verifikatorPembayaranBo.approveTransaksi(headerCheckup);
                    } catch (GeneralBOException e){
                        logger.error("[VerifikatorPembayaranAction.approveTransaksi] ERROR. ",e);
                        response.setStatus("error");
                        response.setMessage("[VerifikatorPembayaranAction.approveTransaksi] ERROR. " + e);
                        return response;
                    }
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
                // cari antrian status LL order by createdDate ASCENDING;
                // dimasukan ke antrian WL;

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

                            Notifikasi notifBean = new Notifikasi();
                            notifBean.setTipeNotifId("TN10");
                            notifBean.setNip(firstOrderAntrian.getIdPasien());
                            notifBean.setNamaPegawai("admin");
                            notifBean.setNote("Anda telah memasuki Antrian Waiting List. Silahkan lakukan pembayaran");
                            notifBean.setTo(firstOrderAntrian.getIdPasien());
                            notifBean.setFromPerson("admin");
                            notifBean.setNoRequest(firstOrderAntrian.getId());
                            notifBean.setFlag("Y");
                            notifBean.setRead("N");
                            notifBean.setAction("C");
                            notifBean.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                            notifBean.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                            notifBean.setCreatedWho("admin");
                            notifBean.setLastUpdateWho("admin");

                            notifikasiBo.saveAdd(notifBean);

//                            Push Notif ke Pasien terkait perubahan status menjadi WL
                            bean.setUserId(firstOrderAntrian.getIdPasien());
                            notifikasiFcm = notifikasiFcmBo.getByCriteria(bean);
                            FirebasePushNotif.sendNotificationFirebase(notifikasiFcm.get(0).getTokenFcm(),"Telemedic", "Anda telah memasuki Antrian Waiting List. Silahkan lakukan pembayaran", "WL", notifikasiFcm.get(0).getOs(), null);

                        } catch (GeneralBOException e){
                            logger.error("[VerifikatorPembayaranAction.approveTransaksi] ERROR. ",e);
                            response.setStatus("error");
                            response.setMessage("[VerifikatorPembayaranAction.approveTransaksi] ERROR. " + e);
                            return response;
                        }
                    }
                }

                // --- create jurnal;
                JurnalResponse jurnalResponse = new JurnalResponse();
                if (!"Y".equalsIgnoreCase(antrianTelemedicEntity.getFlagEresep())){
                    jurnalResponse = closingJurnalNonTunaiTelemedic(idDetailCheckup, idTransaksi, antrianTelemedicEntity.getIdPelayanan(), antrianTelemedicEntity.getIdPasien(), flagResep);
                }

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

                        Notifikasi notifBean = new Notifikasi();
                        notifBean.setTipeNotifId("TN10");
                        notifBean.setNip(antrianTelemedicEntity.getIdPasien());
                        notifBean.setNamaPegawai("admin");
                        notifBean.setNote("Anda telah memasuki Antrian Short List. Buka aplikasi untuk menunggu panggilan dokter");
                        notifBean.setTo(antrianTelemedicEntity.getIdPasien());
                        notifBean.setFromPerson("admin");
                        notifBean.setNoRequest(antrianTelemedicEntity.getId());
                        notifBean.setFlag("Y");
                        notifBean.setRead("N");
                        notifBean.setAction("C");
                        notifBean.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                        notifBean.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                        notifBean.setCreatedWho("admin");
                        notifBean.setLastUpdateWho("admin");

                        notifikasiBo.saveAdd(notifBean);

                        //Push Notif ke Pasien terkait perubahan status menjadi SL
                        bean.setUserId(antrianTelemedicEntity.getIdPasien());
                        notifikasiFcm = notifikasiFcmBo.getByCriteria(bean);
                        FirebasePushNotif.sendNotificationFirebase(notifikasiFcm.get(0).getTokenFcm(),"Telemedic", "Anda telah memasuki Antrian Short List. Buka aplikasi untuk menunggu panggilan dokter", "SL", notifikasiFcm.get(0).getOs(), null);
//
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
                        // jika resep dan status SELESAI Konsutasi Maka Update Status FN / Finish
                    } else if ("Y".equalsIgnoreCase(flagResep) && "SK".equalsIgnoreCase(antrianTelemedicEntity.getStatus())){

                        AntrianTelemedic antrianTelemedic = new AntrianTelemedic();
                        antrianTelemedic.setId(antrianTelemedicEntity.getId());
                        antrianTelemedic.setStatus("FN");
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

//            if ("asuransi".equalsIgnoreCase(jenisPasien)) {
//
//                ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
//                if (detailCheckupEntity != null) {
//                    BigDecimal cover = detailCheckupEntity.getCoverBiaya() == null ? new BigDecimal(0) : detailCheckupEntity.getCoverBiaya();
//                    BigDecimal jumlahAllTindakanAsuransi = checkupDetailBo.getSumJumlahTindakanByJenis(idDetailCheckup, jenisPasien, "");
//                    if (jumlahAllTindakanAsuransi.compareTo(cover) == 1) {
//                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
//                        riwayatTindakan.setIdDetailCheckup(idDetailCheckup);
//                        riwayatTindakan.setJenisPasien(jenisPasien);
////                        riwayatTindakan.setNotResep("Y");
//                        List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = riwayatTindakanBo.getListEntityRiwayatTindakan(riwayatTindakan);
//
//                        if (riwayatTindakanEntities.size() > 0) {
//                            BigDecimal jumlahBiaya = new BigDecimal(0);
//                            for (ItSimrsRiwayatTindakanEntity riwayatTindakanEntity : riwayatTindakanEntities) {
//
//                                jumlahBiaya = jumlahBiaya.add(riwayatTindakanEntity.getTotalTarif());
//
//                                // jika jumlahBiaya Lebih besar dari pada yg di cover maka;
//                                // tindakan dialihkan ke umum;
//                                if (jumlahBiaya.compareTo(cover) == 1) {
//
//                                    // newTarif = cover - (total tarif melebihi - tarif tindakan)
//                                    BigDecimal newTarif = cover.subtract(jumlahBiaya.subtract(riwayatTindakanEntity.getTotalTarif()));
//
//                                    // jika newTarif lebih besar dari 0
//                                    // maka update tindakan dengan tarif tindakan sisa (newTarif)
//                                    // membuat tindakan umum baru dari tindakan tarif - newTarif
//                                    if (newTarif.compareTo(BigDecimal.ZERO) == 1) {
//
//                                        BigDecimal tarifAwal = riwayatTindakanEntity.getTotalTarif();
//
//                                        riwayatTindakanEntity.setTotalTarif(newTarif);
//                                        riwayatTindakanEntity.setAction("U");
//                                        riwayatTindakanEntity.setLastUpdate(updateTime);
//                                        riwayatTindakanEntity.setLastUpdateWho(user);
//
//                                        try {
//                                            riwayatTindakanBo.updateByEntity(riwayatTindakanEntity);
//                                        } catch (GeneralBOException e) {
//                                            logger.error("[VerifikatorPembayaranAction.saveApproveAllTindakanRawatJalan] ERROR. ", e);
//                                            response.setStatus("error");
//                                            response.setMessage("[VerifikatorPembayaranAction.saveApproveAllTindakanRawatJalan] ERROR. " + e);
//                                        }
//
//                                        // sisa tarif masuk ke umum adalah tindakan asli / tarifAwal - newTarif
//                                        BigDecimal newTarifTindakanUmum = tarifAwal.subtract(newTarif);
//
//                                        RiwayatTindakan riwayatTindakanEntityNew = new RiwayatTindakan();
//                                        riwayatTindakanEntityNew.setIdTindakan(riwayatTindakanEntity.getIdTindakan());
//                                        riwayatTindakanEntityNew.setNamaTindakan(riwayatTindakanEntity.getNamaTindakan());
//                                        riwayatTindakanEntityNew.setKeterangan(riwayatTindakanEntity.getKeterangan());
//                                        riwayatTindakanEntityNew.setJenisPasien("umum");
//                                        riwayatTindakanEntityNew.setTotalTarif(newTarifTindakanUmum);
//                                        riwayatTindakanEntityNew.setTanggalTindakan(riwayatTindakanEntity.getTanggalTindakan());
//                                        riwayatTindakanEntityNew.setIdDetailCheckup(riwayatTindakanEntity.getIdDetailCheckup());
//                                        riwayatTindakanEntityNew.setKategoriTindakanBpjs(riwayatTindakanEntity.getKategoriTindakanBpjs());
//                                        riwayatTindakanEntityNew.setApproveBpjsFlag(riwayatTindakanEntity.getApproveBpjsFlag());
//                                        riwayatTindakanEntityNew.setFlag("Y");
//                                        riwayatTindakanEntityNew.setAction("C");
//                                        riwayatTindakanEntityNew.setCreatedDate(updateTime);
//                                        riwayatTindakanEntityNew.setCreatedWho(user);
//                                        riwayatTindakanEntityNew.setLastUpdate(updateTime);
//                                        riwayatTindakanEntityNew.setLastUpdateWho(user);
//
//                                        try {
//                                            riwayatTindakanBo.saveAdd(riwayatTindakanEntityNew);
//                                        } catch (GeneralBOException e) {
//                                            logger.error("[VerifikatorPembayaranAction.saveApproveAllTindakanRawatJalan] ERROR. ", e);
//                                            response.setStatus("error");
//                                            response.setMessage("[VerifikatorPembayaranAction.saveApproveAllTindakanRawatJalan] ERROR. " + e);
//                                        }
//                                    } else {
//
//                                        // jika tindakan newTarif == tindakan tarif || newTarif > tindakan tarif
//                                        // maka hanya mengupdate jenis pasien menjadi umum
//
//                                        riwayatTindakanEntity.setJenisPasien("umum");
//                                        riwayatTindakanEntity.setAction("U");
//                                        riwayatTindakanEntity.setLastUpdate(updateTime);
//                                        riwayatTindakanEntity.setLastUpdateWho(user);
//
//                                        try {
//                                            riwayatTindakanBo.updateByEntity(riwayatTindakanEntity);
//                                        } catch (GeneralBOException e) {
//                                            logger.error("[VerifikatorPembayaranAction.saveApproveAllTindakanRawatJalan] ERROR. ", e);
//                                            response.setStatus("error");
//                                            response.setMessage("[VerifikatorPembayaranAction.saveApproveAllTindakanRawatJalan] ERROR. " + e);
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
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

    private JurnalResponse closingJurnalNonTunaiTelemedic(String idDetailCheckup, String idTransaksiOnline, String idPoli, String idPasien, String flagResep) {

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
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");

        String kode = "";
        String transId = "";
        String jenisPasien = "Umum ";
        String kodeBank = "";
        String idJenisPeriksaPasien = "";
        String noKartu = "";
        BigDecimal biayaCover = new BigDecimal(0);
        String withResep = "N";

        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
        if (detailCheckupEntity != null){
            idJenisPeriksaPasien = detailCheckupEntity.getIdJenisPeriksaPasien();
        }

        ItSimrsHeaderChekupEntity checkupEntity = checkupBo.getEntityCheckupById(detailCheckupEntity.getNoCheckup());
        if (checkupEntity != null) {
            idPasien = checkupEntity.getIdPasien();
        }

        String keterangan = "tindakan";
        if (idTransaksiOnline != null && !"".equalsIgnoreCase(idTransaksiOnline)){
            ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranBo.getPembayaranOnlineById(idTransaksiOnline);
            if (pembayaranOnlineEntity != null){
                kodeBank = pembayaranOnlineEntity.getKodeBank();

                if ("resep".equalsIgnoreCase(pembayaranOnlineEntity.getKeterangan())){
                    keterangan = pembayaranOnlineEntity.getKeterangan();
                }

                ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(pembayaranOnlineEntity.getIdAntrianTelemedic());
                if (antrianTelemedicEntity != null){
                    if ("Y".equalsIgnoreCase(antrianTelemedicEntity.getFlagResep())){
                        withResep = "Y";
                    }
                }
            }
        }

        String masterId = jenisPriksaPasienBo.getJenisPerikasEntityById(detailCheckupEntity.getIdJenisPeriksaPasien()).getMasterId();
        if ("asuransi".equalsIgnoreCase(idJenisPeriksaPasien)){

            ImSimrsAsuransiEntity asuransiEntity = asuransiBo.getEntityAsuransiById(detailCheckupEntity.getIdAsuransi());
            if (asuransiEntity != null) {
                masterId = asuransiEntity.getNoMaster();
                jenisPasien = "Asuransi " + asuransiEntity.getNamaAsuransi() + " No. Kartu " + detailCheckupEntity.getNoKartuAsuransi();
            } else {
                logger.error("[VerifikatorPembayaranAction.closingJurnalNonTunai] Error Asuransi tidak ditemukan");
                response.setStatus("error");
                response.setMsg("[VerifikatorPembayaranAction.closingJurnalNonTunai] Error Asuransi tidak ditemukan");
                return response;
            }
        }

        // MAP ALL TINDAKAN BY KETERANGAN
        List<Map> listOfTindakan = new ArrayList<>();
        Map mapTindakan = new HashMap();
        mapTindakan.put("master_id", masterId);
        mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, idJenisPeriksaPasien, keterangan));
        mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, idJenisPeriksaPasien, keterangan));
        mapTindakan.put("activity", getAcitivityList(idDetailCheckup, idJenisPeriksaPasien, keterangan, kode));
        listOfTindakan.add(mapTindakan);

        // MENDAPATKAN SEMUA BIAYA RAWAT;
        BigDecimal jumlah = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "", "");
        BigDecimal ppnObat = new BigDecimal(0);
        Map mapJurnal = new HashMap();
        if ("Y".equalsIgnoreCase(flagResep)){

            BigDecimal jumlahResep = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, idJenisPeriksaPasien, "resep");
            if (jumlahResep != null && jumlahResep.compareTo(new BigDecimal(0)) == 1){

                if (jumlahResep.compareTo(new BigDecimal(0)) == 1) {
                    ppnObat = jumlahResep.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
                }

                Map mapPajakObat = new HashMap();
                mapPajakObat.put("bukti", invoice);
                mapPajakObat.put("nilai", ppnObat);
                mapPajakObat.put("master_id", CommonConstant.MASTER_PAJAK_OBAT);

                if ("umum".equalsIgnoreCase(idJenisPeriksaPasien)){
                    invoice = billingSystemBo.createInvoiceNumber("JRJ", branchId);

                    // create list map piutang
                    Map mapPiutang = new HashMap();
                    mapPiutang.put("bukti", invoice);
                    mapPiutang.put("nilai",  jumlah.add(ppnObat));
                    mapPiutang.put("pasien_id", idPasien);

                    mapJurnal.put("ppn_keluaran", mapPajakObat);
                    mapJurnal.put("pendapatan_rawat_jalan_umum", listOfTindakan);
                    mapJurnal.put("piutang_pasien_umum", mapPiutang);
                    transId = "62";

                } else if ("asuransi".equalsIgnoreCase(idJenisPeriksaPasien)){
                    invoice = billingSystemBo.createInvoiceNumber("JRJ", branchId);

                    // create list map piutang
                    Map mapPiutang = new HashMap();
                    mapPiutang.put("bukti", invoice);
                    mapPiutang.put("nilai", jumlah.add(ppnObat));
                    mapPiutang.put("master_id", masterId);
//                                mapPiutang.put("pasien_id", idPasien);
                    // debit piutang pasien asuransi
                    mapJurnal.put("ppn_keluaran", mapPajakObat);
                    mapJurnal.put("pendapatan_rawat_jalan_asuransi", listOfTindakan);
                    mapJurnal.put("piutang_pasien_asuransi", mapPiutang);
                    transId = "17";
                }
            }

        } else {
            if ("umum".equalsIgnoreCase(idJenisPeriksaPasien)){
                invoice = billingSystemBo.createInvoiceNumber("JRJ", branchId);

                // create list map piutang
                Map mapPiutang = new HashMap();
                mapPiutang.put("bukti", invoice);
                mapPiutang.put("nilai", jumlah);
                mapPiutang.put("pasien_id", idPasien);

                mapJurnal.put("pendapatan_rawat_jalan_umum", listOfTindakan);
                mapJurnal.put("piutang_pasien_umum", mapPiutang);
                transId = "61";
            } else if ("asuransi".equalsIgnoreCase(idJenisPeriksaPasien)){
                invoice = billingSystemBo.createInvoiceNumber("JRJ", branchId);

                // create list map piutang
                Map mapPiutang = new HashMap();
                mapPiutang.put("bukti", invoice);
                mapPiutang.put("nilai", jumlah);
                mapPiutang.put("master_id", masterId);
//                                mapPiutang.put("pasien_id", idPasien);
                // debit piutang pasien asuransi
                mapJurnal.put("pendapatan_rawat_jalan_asuransi", listOfTindakan);
                mapJurnal.put("piutang_pasien_asuransi", mapPiutang);
                transId = "09";
            } else {

                // BPJS only konsultasi
                if ("N".equalsIgnoreCase(withResep)){
                    jenisPasien = "BPJS No. SEP " + detailCheckupEntity.getNoSep();
                    invoice = billingSystemBo.createInvoiceNumber("JRJ", branchId);

                    Map mapPiutang = new HashMap();
                    mapPiutang.put("bukti", detailCheckupEntity.getNoSep());
                    mapPiutang.put("nilai", jumlah );
                    mapPiutang.put("master_id", getMasterIdByTipe(idDetailCheckup, "bpjs"));


                    mapJurnal.put("pendapatan_rawat_jalan_bpjs", listOfTindakan);
                    mapJurnal.put("piutang_pasien_bpjs", mapPiutang);
                    transId = "06";
                } else {

                    // kembalikan jika dengan resep BPJS
                    response.setStatus("success");
                    response.setMsg("[Berhasil]");
                    return response;
                }
            }
        }


        String catatan = "Closing Jurnal Telemedic "+jenisPasien+" Id Detail Checkup " + idDetailCheckup;

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
                mapPiutang.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, idJenisPeriksaPasien, "").add(ppnObat));
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
            logger.error("[VerifikatorPembayaranAction.closingJurnalNonTunai] Error, ", e);
            response.setStatus("error");
            response.setMsg("[VerifikatorPembayaranAction.closingJurnalNonTunai] Error, " + e);
            return response;
        }

        response.setInvoice(invoice);
        return response;
    }

    private JurnalResponse createJurnalPembayaranObatBaruEObat(TransaksiObatDetail trans) {

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");

        String branchId = CommonUtil.userBranchLogin();
        String pelayananId = trans.getIdPelayanan();

        BigDecimal pendapatan = new BigDecimal(trans.getTotalBayar().toString());
        BigDecimal ppn = pendapatan.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
        pendapatan = pendapatan.add(ppn);

        JurnalResponse jurnalResponse = new JurnalResponse();

        String divisiId = "";
        String masterId = "";
        ImSimrsPelayananEntity pelayananEntity = pelayananBo.getPelayananById(pelayananId);
        if (pelayananEntity != null) {

            ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());

            if (position != null) {
                divisiId = position.getKodering();
            }

        } else {
            jurnalResponse.setStatus("error");
            jurnalResponse.setMsg("[VerifikatorPembayaranAction.createJurnalPembayaranObatbaru] ERROR. tidak dapat divisi_id. ");
            return jurnalResponse;
        }

        ImJenisPeriksaPasienEntity jenisPeriksaPasienEntity = jenisPriksaPasienBo.getJenisPerikasEntityById("umum");
        if (jenisPeriksaPasienEntity != null) {
            masterId = jenisPeriksaPasienEntity.getMasterId();
        }

        Map mapPPN = new HashMap();
        mapPPN.put("bukti", billingSystemBo.createInvoiceNumber("JPD", branchId));
        mapPPN.put("nilai", ppn);

        Map mapKas = new HashMap();
        mapKas.put("metode_bayar", "tunai");
        mapKas.put("nilai", pendapatan);

        // create jurnal
        Map hsCriteria = new HashMap();
        hsCriteria.put("metode_bayar", "tunai");
        hsCriteria.put("kas", mapKas);

        Map mapPendapatan = new HashMap();
        mapPendapatan.put("nilai", new BigDecimal(trans.getTotalBayar()));
        mapPendapatan.put("master_id", masterId);
        mapPendapatan.put("divisi_id", divisiId);

        hsCriteria.put("pendapatan_obat_umum", mapPendapatan);
        hsCriteria.put("ppn_keluaran", mapPPN);

        String noJurnal = "";
        try {
            noJurnal = billingSystemBo.createJurnal("29", hsCriteria, branchId, "Penjualan Obat Apotik Langsung E-Obat " + branchId, "Y");
            jurnalResponse.setStatus("success");
            jurnalResponse.setNoJurnal(noJurnal);
        } catch (GeneralBOException e) {
            jurnalResponse.setStatus("error");
            jurnalResponse.setMsg("[VerifikatorPembayaranAction.createJurnalPembayaranObatbaru] ERROR. " + e);
            return jurnalResponse;
        }

        return jurnalResponse;
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

    public AntrianTelemedic getSessionAntrianTelemedic(String id){
        logger.info("[CheckupDetailAction.getSessionAntrianTelemedic] START >>>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<AntrianTelemedic> sessionTelemedic = (List<AntrianTelemedic>) session.getAttribute("listOfResults");
        List<AntrianTelemedic> antrianTelemedics = sessionTelemedic.stream().filter(p->p.getId().equalsIgnoreCase(id)).collect(Collectors.toList());
        if (antrianTelemedics.size() > 0){
            AntrianTelemedic antrianTelemedic = antrianTelemedics.get(0);

            if ("asuransi".equalsIgnoreCase(antrianTelemedic.getIdJenisPeriksaPasien())){
                // mencari apakah sedang approve pembayaran dari konsultasi
                if (antrianTelemedic.getFlagBayarKonsultasi() == null){

                    PembayaranOnline pembayaranOnline = new PembayaranOnline();
                    pembayaranOnline.setIdAntrianTelemedic(id);

                    List<ItSimrsPembayaranOnlineEntity> pembayaranOnlineEntities = verifikatorPembayaranBo.getSearchEntityByCriteria(pembayaranOnline);
                    if (pembayaranOnlineEntities.size() > 0){
                        ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = pembayaranOnlineEntities.get(pembayaranOnlineEntities.size() - 1);
                        if (pembayaranOnlineEntity.getUrlFotoBukti() != null){
                            antrianTelemedic.setFlagApproveConfirm("Y");
                            antrianTelemedic.setUrlFotoStruk(pembayaranOnlineEntity.getUrlFotoBukti());
                        }
                    }
                }


                ItSimrsHeaderChekupEntity headerChekupEntity = verifikatorPembayaranBo.getHeaderCheckupByIdAntrinTelemedic(id);
                if (headerChekupEntity != null){

                    ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = getDetailCheckupByNoCheckup(headerChekupEntity.getNoCheckup());
                    if (detailCheckupEntity != null){
                        if ("Y".equalsIgnoreCase(antrianTelemedic.getFlagResep())){
                            RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                            riwayatTindakan.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                            riwayatTindakan.setKeterangan("resep");

                            List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = riwayatTindakanBo.getListEntityRiwayatTindakan(riwayatTindakan);
                            if (riwayatTindakanEntities == null || riwayatTindakanEntities.size() == 0)
                                antrianTelemedic.setFlagBelumBayar("Y");
                        }

                        if ("Y".equalsIgnoreCase(antrianTelemedic.getFlagApproveConfirm())){
                            antrianTelemedic.setDibayarPasien(detailCheckupEntity.getDibayarPasien() == null ? new BigDecimal(0) : detailCheckupEntity.getDibayarPasien());
                            antrianTelemedic.setJumlahCover(detailCheckupEntity.getCoverBiaya() == null ? new BigDecimal(0) : detailCheckupEntity.getCoverBiaya());
                        }
                    }
                }
            }


            logger.info("[CheckupDetailAction.getSessionAntrianTelemedic] END <<<");
            return antrianTelemedic;
        }
        logger.info("[CheckupDetailAction.getSessionAntrianTelemedic] END <<<");
        return null;
    }

    private ItSimrsHeaderDetailCheckupEntity getDetailCheckupByNoCheckup(String noCheckup){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        detailCheckup.setNoCheckup(noCheckup);
        List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntities = checkupDetailBo.getListEntityByCriteria(detailCheckup);
        return detailCheckupEntities != null && detailCheckupEntities.size() > 0 ? detailCheckupEntities.get(0) : null;
    }

    public CrudResponse saveCoverAsuransi(String idAntrianTelemedic, String jumlahCover, String idTransksi){
        logger.info("[CheckupDetailAction.saveTransaksi] START >>>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");

        Timestamp time = CommonUtil.getCurrentDateTimes();
        String userLogin = CommonUtil.userLogin();
        String branchId = CommonUtil.userBranchLogin();

        CrudResponse response = new CrudResponse();

        BigDecimal coverAsuransi = new BigDecimal(0);
        if (jumlahCover != null && !"".equalsIgnoreCase(jumlahCover)){
            coverAsuransi = new BigDecimal(jumlahCover);

            ItSimrsAntrianTelemedicEntity telemedicEntity = telemedicBo.getAntrianTelemedicEntityById(idAntrianTelemedic);
            if (telemedicEntity != null){

                ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranBo.getPembayaranOnlineById(idTransksi);
                if (pembayaranOnlineEntity != null){

                    AntrianTelemedic antrianTelemedic = new AntrianTelemedic();
                    antrianTelemedic.setId(idAntrianTelemedic);
                    if (telemedicEntity.getJumlahCover() == null || telemedicEntity.getJumlahCover().compareTo(new BigDecimal(0)) == 0){
                        antrianTelemedic.setJumlahCover(coverAsuransi);
                    }
                    if ("resep".equalsIgnoreCase(pembayaranOnlineEntity.getKeterangan())){
                        antrianTelemedic.setFlagBayarResep("Y");
                    }
                    if ("konsultasi".equalsIgnoreCase(pembayaranOnlineEntity.getKeterangan())){
                        antrianTelemedic.setFlagBayarKonsultasi("Y");
                    }
                    antrianTelemedic.setLastUpdate(time);
                    antrianTelemedic.setLastUpdateWho(userLogin);
                    try {
                        telemedicBo.saveEdit(antrianTelemedic,branchId,  "");
                        response.setStatus("success");
                    } catch (GeneralBOException e) {
                        logger.error("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR ", e);
                        response.setStatus("error");
                        response.setMsg("[CheckupDetailAction.getJumlahNilaiBiayaByKeterangan] ERROR " + e);
                        return response;
                    }
                }
            }
        }

        logger.info("[CheckupDetailAction.saveTransaksi] END <<<");
        return response;
    }

    public CrudResponse saveVerifikasiBpjs(String idAntrian, String noKartu, String idDiagnosa, String ketDiagnosa, String kelasPasienBpjs){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        String userLogin = CommonUtil.userLogin();
        Timestamp time = CommonUtil.getCurrentDateTimes();
        CrudResponse response = new CrudResponse();

        String flagResep = "N";
        ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(idAntrian);
        if (antrianTelemedicEntity != null){
            if (antrianTelemedicEntity.getFlagResep() != null && "Y".equalsIgnoreCase(antrianTelemedicEntity.getFlagResep())){
                flagResep = "Y";
            }

            HeaderCheckup checkup = new HeaderCheckup();
            checkup.setIdPasien(antrianTelemedicEntity.getIdPasien());
            checkup.setIdDokter(antrianTelemedicEntity.getIdDokter());
            checkup.setDiagnosa(idDiagnosa);
            checkup.setIdJenisPeriksaPasien(antrianTelemedicEntity.getIdJenisPeriksaPasien());
            checkup.setKelasPasien(kelasPasienBpjs);
            checkup.setRujuk("");
            checkup.setTglRujukan("");
            checkup.setNoRujukan("");
            checkup.setIdPelayananBpjs("");
            checkup.setNoPpkRujukan("");

            CheckResponse checkResponse = generateCoverBpjs(checkup);
            if ("success".equalsIgnoreCase(checkResponse.getStatus())){

                // update no SEP
                AntrianTelemedic antrianTelemedic = new AntrianTelemedic();
                antrianTelemedic.setId(idAntrian);
                antrianTelemedic.setNoKartu(noKartu);
                antrianTelemedic.setIdDiagnosa(idDiagnosa);
                antrianTelemedic.setKetDiagnosa(ketDiagnosa);
                antrianTelemedic.setJumlahCover(checkResponse.getJumlahCover());
                antrianTelemedic.setNoSep(checkResponse.getNoSep());
                antrianTelemedic.setFlagBayarKonsultasi("Y");
                if ("Y".equalsIgnoreCase(flagResep)){
                    antrianTelemedic.setFlagBayarResep("Y");
                }
                antrianTelemedic.setLastUpdate(time);
                antrianTelemedic.setLastUpdateWho(userLogin);

                try {
                    telemedicBo.saveEdit(antrianTelemedic, "", "");
                } catch (GeneralBOException e){
                    logger.error("[VerifikatorPembayaranAction.saveVerifikasiBpjs] ERROR ", e);
                    response.setStatus("error");
                    response.setMsg("[VerifikatorPembayaranAction.saveVerifikasiBpjs] ERROR " + e);
                    return response;
                }

                PembayaranOnline pembayaranOnline = new PembayaranOnline();
                pembayaranOnline.setIdAntrianTelemedic(idAntrian);
                List<ItSimrsPembayaranOnlineEntity> pembayaranOnlineEntities = verifikatorPembayaranBo.getSearchEntityByCriteria(pembayaranOnline);
                if (pembayaranOnlineEntities.size() > 0){

                    for (ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity : pembayaranOnlineEntities){

                        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdTransaksi(pembayaranOnlineEntity.getId());
                        if (detailCheckupEntity != null){

                            // update detail checkup with no SEP and Jumlah Cover
                            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                            detailCheckup.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                            detailCheckup.setNoSep(checkResponse.getNoSep());
                            detailCheckup.setIdPelayananBpjs(checkResponse.getIdPelayananBpjs());
                            detailCheckup.setTarifBpjs(checkResponse.getJumlahCover());
                            detailCheckup.setKodeCbg(checkResponse.getKodeCbg());
                            detailCheckup.setLastUpdateWho(userLogin);
                            detailCheckup.setLastUpdate(time);
                            detailCheckup.setAction("U");

                            try {
                                checkupDetailBo.saveEdit(detailCheckup);
                            } catch (GeneralBOException e){
                                logger.error("[VerifikatorPembayaranAction.saveVerifikasiBpjs] ERROR ", e);
                                response.setStatus("error");
                                response.setMsg("[VerifikatorPembayaranAction.saveVerifikasiBpjs] ERROR " + e);
                                return response;
                            }
                        }
                    }
                }
            } else {
                logger.error(checkResponse.getMessage());
                response.setStatus("error");
                response.setMsg(checkResponse.getMessage());
                return response;
            }
        }
        return response;
    }

    public CheckResponse generateCoverBpjs(HeaderCheckup checkup) {

        logger.info("[VerifikatorPembayaranAction.generateCoverBpjs] start process >>>");
        CheckResponse response = new CheckResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        DokterBo dokterBo = (DokterBo) ctx.getBean("dokterBoProxy");
        BpjsBo bpjsBo = (BpjsBo) ctx.getBean("bpjsBoProxy");
        EklaimBo eklaimBo = (EklaimBo) ctx.getBean("eklaimBoProxy");
        TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");

        String genNoSep = "";
        String userLogin = CommonUtil.userLogin();
        String userArea = CommonUtil.userBranchLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        long millis = System.currentTimeMillis();
        java.util.Date dateNow = new java.util.Date(millis);
        String dateToday = new SimpleDateFormat("yyyy-MM-dd").format(dateNow);

        //jika bpjs dan ptpn
        if ("bpjs".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien()) || "ptpn".equalsIgnoreCase(checkup.getIdJenisPeriksaPasien())) {

            List<Pasien> pasienList = new ArrayList<>();
            List<Branch> branchList = new ArrayList<>();
            Pasien pasien = new Pasien();
            pasien.setIdPasien(checkup.getIdPasien());
            pasien.setFlag("Y");

            try {
                pasienList = pasienBo.getByCriteria(pasien);
            } catch (GeneralBOException e) {
                Long logId = null;
                logger.error("[VerifikatorPembayaranAction.generateCoverBpjs] Error when search item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                response.setStatus("error");
                response.setMessage("[VerifikatorPembayaranAction.generateCoverBpjs] Error when search item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin."+ e);
                return response;
            }

            Branch branch = new Branch();
            branch.setBranchId(userArea);
            branch.setFlag("Y");

            try {
                branchList = branchBo.getByCriteria(branch);
            } catch (GeneralBOException e) {
                Long logId = null;
                response.setStatus("error");
                response.setMessage("[VerifikatorPembayaranAction.generateCoverBpjs] Error when search item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin."+ e);
                return response;
            }

            Branch getBranch = new Branch();

            if (branchList.size() > 0) {
                getBranch = branchList.get(0);

                if (getBranch.getPpkPelayanan() != null) {
                    if (pasienList.size() > 0) {

                        Pasien getPasien = pasienList.get(0);

                        List<Dokter> dokterList = new ArrayList<>();
                        Dokter dokter = new Dokter();
                        dokter.setIdDokter(checkup.getIdDokter());
                        dokter.setFlag("Y");
                        try {
                            dokterList = dokterBo.getByCriteria(dokter);
                        } catch (GeneralBOException e) {
                            throw new GeneralBOException("Error when search idDokter " + e.getMessage());
                        }

                        String namaDokter = "";
                        String kodeDpjp = "";

                        if (dokterList.size() > 0) {
                            namaDokter = dokterList.get(0).getNamaDokter();
                            kodeDpjp = dokterList.get(0).getKodeDpjp();
                        }

                        SepRequest sepRequest = new SepRequest();
                        sepRequest.setNoKartu(getPasien.getNoBpjs());
                        sepRequest.setTglSep(dateToday);
                        sepRequest.setPpkPelayanan(getBranch.getPpkPelayanan());//cons id rumah sakit
                        sepRequest.setJnsPelayanan("2");//jenis rawat inap apa jalan
                        sepRequest.setKlsRawat(checkup.getKelasPasien());//kelas rawat dari bpjs
                        sepRequest.setNoMr(checkup.getIdPasien());//id pasien

                        if (checkup.getRujuk() != null && !"".equalsIgnoreCase(checkup.getRujuk())) {
                            sepRequest.setAsalRujukan(checkup.getRujuk());
                        } else {
                            sepRequest.setAsalRujukan("2");
                        }

                        if (checkup.getTglRujukan() != null && !"".equalsIgnoreCase(checkup.getTglRujukan())) {
                            sepRequest.setTglRujukan(checkup.getTglRujukan());
                        } else {
                            sepRequest.setTglRujukan("");
                        }

                        if (checkup.getNoRujukan() != null && !"".equalsIgnoreCase(checkup.getNoRujukan())) {
                            sepRequest.setNoRujukan(checkup.getNoRujukan());
                        } else {
                            sepRequest.setNoRujukan("");
                        }

                        if (checkup.getIdPelayananBpjs() != null && !"".equalsIgnoreCase(checkup.getIdPelayananBpjs())) {
                            sepRequest.setPoliTujuan(checkup.getIdPelayananBpjs());
                        } else {
                            sepRequest.setPoliTujuan("IGD");
                        }

                        if (checkup.getNoPpkRujukan() != null && !"".equalsIgnoreCase(checkup.getNoPpkRujukan())) {
                            sepRequest.setPpkRujukan(checkup.getNoPpkRujukan());
                        } else {
                            sepRequest.setPpkRujukan("");
                        }

                        sepRequest.setCatatan("");
                        sepRequest.setDiagAwal(checkup.getDiagnosa());
                        sepRequest.setPoliEksekutif("0");
                        sepRequest.setCob("0");
                        sepRequest.setKatarak("0");
                        sepRequest.setLakaLantas("0");
                        sepRequest.setPenjamin("");
                        sepRequest.setTglKejadian("");
                        sepRequest.setKeterangan("");
                        sepRequest.setSuplesi("0");
                        sepRequest.setNoSepSuplesi("");
                        sepRequest.setKdProvinsiLakaLantas("");
                        sepRequest.setKdKecamatanLakaLantas("");
                        sepRequest.setKdKabupatenLakaLantas("");
                        sepRequest.setNoSuratSkdp(getBranch.getSuratSkdp());
                        sepRequest.setKodeDpjp(kodeDpjp);
                        sepRequest.setNoTelp(getPasien.getNoTelp());
                        sepRequest.setUserPembuatSep(userLogin);

                        SepResponse sepResponse = new SepResponse();

                        try {
                            sepResponse = bpjsBo.insertSepBpjs(sepRequest, userArea);
                        } catch (Exception e) {
                            logger.error("[VerifikatorPembayaranAction.generateCoverBpjs] Error when insert SEP ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
//                            throw new GeneralBOException("Error when new insert SEP", e);
                            response.setStatus("error");
                            response.setMessage("Error when new insert SEP"+ e);
                            return response;
                        }

                        if (sepResponse.getNoSep() != null) {

                            genNoSep = sepResponse.getNoSep();
                            response.setNoSep(sepResponse.getNoSep());

                            logger.info("[VerifikatorPembayaranAction.generateCoverBpjs] NO. SEP : " + genNoSep);

                            KlaimRequest klaimRequest = new KlaimRequest();
                            klaimRequest.setNoSep(genNoSep);
                            klaimRequest.setNoKartu(getPasien.getNoBpjs());
                            klaimRequest.setNoRm(getPasien.getIdPasien());
                            klaimRequest.setNamaPasien(getPasien.getNama());
                            klaimRequest.setTglLahir(getPasien.getTglLahir());
                            String jk = "";

                            if ("L".equalsIgnoreCase(getPasien.getJenisKelamin())) {
                                jk = "1";
                            } else {
                                jk = "2";
                            }

                            klaimRequest.setGender(jk);
                            klaimRequest.setCoderNik(getBranch.getCoderNik());

                            KlaimResponse responseNewClaim = new KlaimResponse();

                            try {
                                responseNewClaim = eklaimBo.insertNewClaimEklaim(klaimRequest, userArea);
                            } catch (GeneralBOException e) {
                                logger.error("[VerifikatorPembayaranAction.generateCoverBpjs] Error when new claim ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
//                                throw new GeneralBOException("Error when new claim", e);
                                response.setStatus("error");
                                response.setMessage("Error when new claim"+ e);
                                return response;
                            }

                            List<Tindakan> tindakanList = new ArrayList<>();
                            Tindakan tindakan = new Tindakan();
                            tindakan.setBranchId(CommonUtil.userBranchLogin());
                            tindakan.setIsIna("Y");

                            try {
                                tindakanList = tindakanBo.getByCriteria(tindakan);
                            } catch (GeneralBOException e) {
                                logger.error("[VerifikatorPembayaranAction.generateCoverBpjs] Error when tindakan ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
//                                throw new GeneralBOException("Error when new tindakan", e);
                                response.setStatus("error");
                                response.setMessage("Error when new tindakan"+ e);
                                return response;
                            }

                            BigInteger tarifRsProsedurNonBedah = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsTenagaAhli = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsRadiologi = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsRehabilitasi = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsObat = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsAlkes = new BigInteger(String.valueOf(0));

                            BigInteger tarifRsProsedurBedah = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsKeperawatan = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsLaboratorium = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsKamar = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsObatKronis = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsBmhp = new BigInteger(String.valueOf(0));

                            BigInteger tarifRsKonsultasi = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsPenunjang = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsPelayananDarah = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsRawatIntensif = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsObatKemoterapi = new BigInteger(String.valueOf(0));
                            BigInteger tarifRsSewaAlat = new BigInteger(String.valueOf(0));


                            if (tindakanList.size() > 0) {
                                List<Tindakan> tindakans = new ArrayList<>();

                                for (Tindakan entity : tindakanList) {

                                    if ("prosedur_non_bedah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsProsedurNonBedah = tarifRsProsedurNonBedah.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }
                                    if ("tenaga_ahli".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsTenagaAhli = tarifRsTenagaAhli.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }
                                    if ("radiologi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsRadiologi = tarifRsRadiologi.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }
                                    if ("rehabilitasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsRehabilitasi = tarifRsRehabilitasi.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }
                                    if ("obat".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsObat = tarifRsObat.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }
                                    if ("alkes".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsAlkes = tarifRsAlkes.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }

                                    //--------------
                                    if ("prosedur_bedah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsProsedurBedah = tarifRsProsedurBedah.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }
                                    if ("keperawatan".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsKeperawatan = tarifRsKeperawatan.add(new BigInteger(entity.getTarifBpjs().toString()));

                                    }
                                    if ("laboratorium".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsLaboratorium = tarifRsLaboratorium.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }
                                    if ("kamar_akomodasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsKamar = tarifRsKamar.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }
                                    if ("obat_kronis".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsObatKronis = tarifRsObatKronis.add(new BigInteger(entity.getTarifBpjs().toString()));

                                    }
                                    if ("bmhp".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsBmhp = tarifRsBmhp.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }

                                    //--------------
                                    if ("konsultasi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsKonsultasi = tarifRsKonsultasi.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }
                                    if ("penunjang".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsPenunjang = tarifRsPenunjang.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }
                                    if ("pelayanan_darah".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsPelayananDarah = tarifRsPelayananDarah.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }
                                    if ("rawat_intensif".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsRawatIntensif = tarifRsRawatIntensif.add(new BigInteger(entity.getTarifBpjs().toString()));

                                    }
                                    if ("obat_kemoterapi".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsObatKemoterapi = tarifRsObatKemoterapi.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }
                                    if ("sewa_alat".equalsIgnoreCase(entity.getKategoriInaBpjs())) {
                                        tarifRsSewaAlat = tarifRsSewaAlat.add(new BigInteger(entity.getTarifBpjs().toString()));
                                    }

                                    Tindakan tin = new Tindakan();
                                    tin.setIdTindakan(entity.getIdTindakan());
                                    tin.setKategoriInaBpjs(entity.getKategoriInaBpjs());
                                    tindakans.add(tin);
                                }

                                checkup.setTindakanList(tindakans);
                            }

                            if (responseNewClaim.getPatientId() != null) {

                                KlaimDetailRequest klaimDetailRequest = new KlaimDetailRequest();
                                klaimDetailRequest.setNomorSep(genNoSep);
                                klaimDetailRequest.setNomorKartu(getPasien.getNoKtp());
                                klaimDetailRequest.setTglMasuk(updateTime.toString());
                                klaimDetailRequest.setTglPulang(updateTime.toString());
                                klaimDetailRequest.setJenisRawat("2");
                                klaimDetailRequest.setKelasRawat(checkup.getKelasPasien());
                                klaimDetailRequest.setAdlChronic("");
                                klaimDetailRequest.setIcuIndikator("");
                                klaimDetailRequest.setIcuLos("");
                                klaimDetailRequest.setVentilatorHour("");
                                klaimDetailRequest.setUpgradeClassInd("");
                                klaimDetailRequest.setUpgradeClassClass("");
                                klaimDetailRequest.setUpgradeClassLos("");
                                klaimDetailRequest.setAddPaymentPct("");
                                klaimDetailRequest.setBirthWeight("0");
                                klaimDetailRequest.setDischargeStatus("1");
                                klaimDetailRequest.setDiagnosa(checkup.getDiagnosa());
                                klaimDetailRequest.setProcedure("");


                                //set tindakan untuk mendapatkan cover bpjs

                                klaimDetailRequest.setTarifRsNonBedah(tarifRsProsedurNonBedah.toString());
                                klaimDetailRequest.setTarifRsProsedurBedah(tarifRsProsedurBedah.toString());
                                klaimDetailRequest.setTarifRsKonsultasi(tarifRsKonsultasi.toString());
                                klaimDetailRequest.setTarifRsTenagaAhli(tarifRsTenagaAhli.toString());
                                klaimDetailRequest.setTarifRsKeperawatan(tarifRsKeperawatan.toString());
                                klaimDetailRequest.setTarifRsPenunjang(tarifRsPenunjang.toString());
                                klaimDetailRequest.setTarifRsRadiologi(tarifRsRadiologi.toString());
                                klaimDetailRequest.setTarifRsLaboratorium(tarifRsLaboratorium.toString());
                                klaimDetailRequest.setTarifRsPelayananDarah(tarifRsPelayananDarah.toString());
                                klaimDetailRequest.setTarifRsRehabilitasi(tarifRsRehabilitasi.toString());
                                klaimDetailRequest.setTarifRsKamar(tarifRsKamar.toString());
                                klaimDetailRequest.setTarifRsRawatIntensif(tarifRsRawatIntensif.toString());
                                klaimDetailRequest.setTarifRsObat(tarifRsObat.toString());
                                klaimDetailRequest.setTarifRsObatKronis(tarifRsObatKronis.toString());
                                klaimDetailRequest.setTarifRsObatKemoterapi(tarifRsObatKemoterapi.toString());
                                klaimDetailRequest.setTarifRsAlkes(tarifRsAlkes.toString());
                                klaimDetailRequest.setTarifRsBmhp(tarifRsBmhp.toString());
                                klaimDetailRequest.setTarifRsSewaAlat(tarifRsSewaAlat.toString());

                                //end set tindakan

                                klaimDetailRequest.setTarifPoliEks("");
                                klaimDetailRequest.setNamaDokter(namaDokter);
                                klaimDetailRequest.setKodeTarif(getBranch.getKodeTarif());
                                klaimDetailRequest.setTarifRsPayorId(getBranch.getTarifPayorId());
                                klaimDetailRequest.setPayorCd(getBranch.getPayorCd());
                                klaimDetailRequest.setCobCd("");
                                klaimDetailRequest.setCoderNik(getBranch.getCoderNik());

                                KlaimDetailResponse claimEklaimResponse = new KlaimDetailResponse();
                                try {
                                    claimEklaimResponse = eklaimBo.updateDataClaimEklaim(klaimDetailRequest, userArea);
                                } catch (GeneralBOException e) {
                                    logger.error("[VerifikatorPembayaranAction.generateCoverBpjs] Error when update claim ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
//                                    throw new GeneralBOException("Error when update claim, [" + claimEklaimResponse.getMessage() + "]", e);
                                    response.setStatus("error");
                                    response.setMessage("Error when update claim, [" + claimEklaimResponse.getMessage() + "]"+ e);
                                    return response;
                                }

                                if ("200".equalsIgnoreCase(claimEklaimResponse.getStatus())) {

                                    Grouping1Response grouping1Response = new Grouping1Response();

                                    try {
                                        grouping1Response = eklaimBo.groupingStage1Eklaim(genNoSep, userArea);
                                    } catch (GeneralBOException e) {
                                        logger.error("[VerifikatorPembayaranAction.generateCoverBpjs] Error when adding item ," + "[" + e + "] Found problem when saving add data, please inform to your admin.");
//                                        throw new GeneralBOException("Error when get cover biaya BPJS, [" + grouping1Response.getMessage() + "]", e);
                                        response.setStatus("error");
                                        response.setMessage("Error Error when get cover biaya BPJS, [" + grouping1Response.getMessage() + "]");
                                        return response;
                                    }

                                    // jika mendapatkan cbgCode dan tarif cbg maka update ke table checkup untuk mengisi total tarif
                                    if (grouping1Response.getCbgCode() != null && grouping1Response.getCbgTarif() != null) {

                                        BigDecimal tarifCbg = new BigDecimal(0);
                                        if (!"".equalsIgnoreCase(grouping1Response.getCbgTarif()) && grouping1Response.getCbgTarif() != null) {
                                            if (!"0".equalsIgnoreCase(grouping1Response.getCbgTarif())) {

                                                tarifCbg = new BigDecimal(grouping1Response.getCbgTarif());

                                                //=====kode CBG INA=======
                                                response.setKodeCbg(grouping1Response.getCbgCode());

                                                //======START SET TARIF BPJS=========

                                                response.setJumlahCover(tarifCbg);

                                                //======END SET TARIF BPJS=========
                                            } else {
                                                logger.error("[VerifikatorPembayaranAction.generateCoverBpjs] Error when get cover biaya BPJS " + grouping1Response.getMessage());
//                                                throw new GeneralBOException("Error Error when get cover biaya BPJS, [" + grouping1Response.getMessage() + "]");
                                                response.setStatus("error");
                                                response.setMessage("Error Error when get cover biaya BPJS, [" + grouping1Response.getMessage() + "]");
                                                return response;
                                            }
                                        } else {
                                            logger.error("[VerifikatorPembayaranAction.generateCoverBpjs] Error when get cover biaya BPJS " + grouping1Response.getMessage());
//                                            throw new GeneralBOException("Error when get cover biaya BPJS, [" + grouping1Response.getMessage() + "]");
                                            response.setStatus("error");
                                            response.setMessage("Error when get cover biaya BPJS, [" + grouping1Response.getMessage() + "]");
                                            return response;
                                        }


                                        // jika ada special cmg maka proses grouping stage 2
                                        if (grouping1Response.getSpecialCmgResponseList().size() > 0) {

                                            for (Grouping1SpecialCmgResponse specialCmgResponse : grouping1Response.getSpecialCmgResponseList()) {

                                                Grouping2Response grouping2Response = new Grouping2Response();
                                                try {
                                                    grouping2Response = eklaimBo.groupingStage2Eklaim(genNoSep, specialCmgResponse.getCode(), userArea);
                                                } catch (GeneralBOException e) {
                                                    Long logId = null;
                                                    logger.error("[VerifikatorPembayaranAction.generateCoverBpjs] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                                                    response.setStatus("error");
                                                    response.setMessage("[VerifikatorPembayaranAction.generateCoverBpjs] Error when search item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin."+ e);
                                                    return response;
                                                }
                                            }
                                        }
                                    }

                                } else {
                                    logger.error("[VerifikatorPembayaranAction.generateCoverBpjs] Error when adding item ,update claim not success " + claimEklaimResponse.getMessage());
//                                    throw new GeneralBOException("Error when adding item ,update claim not success, [" + claimEklaimResponse.getMessage() + "]");
                                    response.setStatus("error");
                                    response.setMessage("Error when adding item ,update claim not success, [" + claimEklaimResponse.getMessage() + "]");
                                    return response;
                                }
                            } else {
                                logger.error("[VerifikatorPembayaranAction.generateCoverBpjs] Error when get pastien Eklaim, " + responseNewClaim.getMsg());
//                                throw new GeneralBOException("Error when get pastien Eklaim, [" + responseNewClaim.getMsg() + "]");
                                response.setStatus("error");
                                response.setMessage("Error when get pastien Eklaim, [" + responseNewClaim.getMsg() + "]");
                                return response;
                            }
                        } else {
                            logger.error("[VerifikatorPembayaranAction.generateCoverBpjs] Error when generate SEP, " + response.getMessage());
//                            throw new GeneralBOException("Error when generate SEP, [" + response.getMessage() + "]");
                            response.setStatus("error");
                            response.setMessage("Error when generate SEP, [" + response.getMessage() + "]");
                            return response;
                        }

                    }
                } else {
                    logger.error("[VerifikatorPembayaranAction.generateCoverBpjs] Error when search PPK pelayanan");
//                    throw new GeneralBOException("Error when search PPK pelayanan");
                    response.setStatus("error");
                    response.setMessage("Error when search PPK pelayanan");
                    return response;
                }
            }

            if (checkup.getIdPelayananBpjs() != null && !"".equalsIgnoreCase(checkup.getIdPelayananBpjs())) {
                response.setIdPelayananBpjs(checkup.getIdPelayananBpjs());
            } else {
                response.setIdPelayananBpjs("IGD");
            }
        }

        response.setStatus("success");
        response.setMessage("Berhasil Mendapatkan Cover");

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[CheckupDetailAction.generateCoverBpjs] end process >>>");
        return response;
    }

    public List<ItSimrsStrukAsuransiEntity> getListStrukAsuransi(String idAntrianTelemedic){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorAsurasiBo verifikatorAsurasiBo = (VerifikatorAsurasiBo) ctx.getBean("verifikatorAsurasiBoProxy");

        StrukAsuransi strukAsuransi = new StrukAsuransi();
        strukAsuransi.setIdAntrianTelemedic(idAntrianTelemedic);

        return verifikatorAsurasiBo.getListStrukAsurasiEntity(strukAsuransi);
    }
    public ItSimrsStrukAsuransiEntity getStrukAsuransiByIdAntrianAndJenis(String idAntrianTelemedic, String jenis){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorAsurasiBo verifikatorAsurasiBo = (VerifikatorAsurasiBo) ctx.getBean("verifikatorAsurasiBoProxy");

        StrukAsuransi strukAsuransi = new StrukAsuransi();
        strukAsuransi.setIdAntrianTelemedic(idAntrianTelemedic);
        strukAsuransi.setJenis(jenis);

        List<ItSimrsStrukAsuransiEntity> strukAsuransiEntityList = verifikatorAsurasiBo.getListStrukAsurasiEntity(strukAsuransi);
        if (strukAsuransiEntityList.size() > 0)
            return strukAsuransiEntityList.get(0);
        return null;
    }
    public CrudResponse uploadStruk(String uploadString, String jenis, String idStruk, String jumlahCover, String dibayarPasien) throws IOException{

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String branchId = CommonUtil.userBranchLogin();

        CrudResponse response = new CrudResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorAsurasiBo verifikatorAsurasiBo = (VerifikatorAsurasiBo) ctx.getBean("verifikatorAsurasiBoProxy");

//        String fileName = "";
//        if (!"".equalsIgnoreCase(uploadString)){
//            BASE64Decoder decoder = new BASE64Decoder();
//            byte[] decodedBytes = decoder.decodeBuffer(uploadString);
//            logger.info("Decoded upload data : " + decodedBytes.length);
//            fileName = branchId + "_" + jenis + "_"+idStruk+".png";
//            String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_BUKTI_TRANSFER + "/" + fileName;
//            logger.info("File save path : " + uploadFile);
//            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
//
//            if (image == null) {
//                logger.error("Buffered Image is null");
//            }else{
//
////                write the image
////                File fileCreate = new File(CommonUtil.getPropertyParams("upload.folder")+CommonConstant.RESOURCE_PATH_BUKTI_TRANSFER, fileName);
////                try {
////                    FileUtils.copyFile(f, fileCreate);
////                }catch (IOException e){
////                    e.printStackTrace();
////                }
//
//
//                File f = new File(uploadFile);
//
//                try {
//                    ImageIO.write(image, "png", f);
//                }catch (IOException e){
//                    response.setMsg("[VerifikatorPembayaranAction.uploadStruk] ERROR " + e);
//                    response.setStatus("error");
//                    return response;
//                }
//            }
//
//        }

        StrukAsuransi strukAsuransi = new StrukAsuransi();
        strukAsuransi.setId(idStruk);
        strukAsuransi.setUrlFotoStruk(jenis + ".jpg");
//        strukAsuransi.setUrlFotoStruk(fileName);
        strukAsuransi.setLastUpdate(time);
        strukAsuransi.setLastUpdateWho(userLogin);

        if ("confirmation".equalsIgnoreCase(jenis)){
            strukAsuransi.setJumlahCover(jumlahCover == null || "".equalsIgnoreCase(jumlahCover) ? new BigDecimal(0) : new BigDecimal(jumlahCover));
            strukAsuransi.setDibayarPasien(dibayarPasien == null || "".equalsIgnoreCase(dibayarPasien) ? new BigDecimal(0) : new BigDecimal(dibayarPasien));
        }

        try {
            verifikatorAsurasiBo.saveUploadStrukAsuransi(strukAsuransi);
            response.setStatus("success");
        } catch (GeneralBOException e){
            logger.error("[VerifikatorPembayaranAction.uploadStruk] ERROR ", e);
            response.setStatus("error");
            response.setMsg("[VerifikatorPembayaranAction.uploadStruk] ERROR " + e);
            return response;
        }

        return response;
    }

    public List<ItSimrsRiwayatTindakanEntity> getListRiwayatTindakanByIdAntrian(String idAntrian) throws GeneralBOException{

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");

        ItSimrsHeaderChekupEntity chekupEntity = verifikatorPembayaranBo.getHeaderCheckupByIdAntrinTelemedic(idAntrian);
        if (chekupEntity != null){
            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setNoCheckup(chekupEntity.getNoCheckup());
            List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntities = checkupDetailBo.getListEntityByCriteria(detailCheckup);
            if (detailCheckupEntities.size() > 0){
                ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = detailCheckupEntities.get(0);

                RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                riwayatTindakan.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());

                List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = riwayatTindakanBo.getListEntityRiwayatTindakan(riwayatTindakan);
                return riwayatTindakanEntities.size() > 0 ? riwayatTindakanEntities : null;
            }
        }
        return null;
    }

    public CrudResponse approveConfirmAsuransi(String idAntrian){
        String userLogin = CommonUtil.userLogin();
        Timestamp times = new Timestamp(System.currentTimeMillis());

        CrudResponse response = new CrudResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");

        AntrianTelemedic antrianTelemedic = new AntrianTelemedic();
        antrianTelemedic.setId(idAntrian);
        antrianTelemedic.setFlagApproveConfirm("Y");
        antrianTelemedic.setLastUpdate(times);
        antrianTelemedic.setLastUpdateWho(userLogin);

        try {
            telemedicBo.saveEdit(antrianTelemedic, "", "");
            response.setStatus("success");
        } catch (GeneralBOException e){
            logger.error("[VerifikatorPembayaranAction.approveConfirmAsuransi] ERROR ", e);
            response.setStatus("error");
            response.setMsg("[VerifikatorPembayaranAction.approveConfirmAsuransi] ERROR " + e);
            return response;
        }
        return response;
    }
}
