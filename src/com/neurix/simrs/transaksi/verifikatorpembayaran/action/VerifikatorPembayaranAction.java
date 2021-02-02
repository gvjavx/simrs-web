package com.neurix.simrs.transaksi.verifikatorpembayaran.action;

import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.master.bo.MasterBo;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.ImPosition;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.download.excel.CellDetail;
import com.neurix.common.download.excel.DownloadUtil;
import com.neurix.common.download.excel.RowData;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.master.shift.bo.ShiftBo;
import com.neurix.hris.master.shift.model.ImHrisShiftEntity;
import com.neurix.hris.master.shift.model.Shift;
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
import com.neurix.simrs.master.obat.bo.ObatBo;
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
import com.neurix.simrs.transaksi.bataltelemedic.bo.BatalTelemedicBo;
import com.neurix.simrs.transaksi.bataltelemedic.model.BatalTelemedic;
import com.neurix.simrs.transaksi.bataltelemedic.model.ItSimrsBatalTelemedicEntity;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.notifikasiadmin.bo.NotifikasiAdminBo;
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
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueObjectException;
import org.joda.time.DateTime;
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
import java.math.MathContext;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by reza on 10/06/20.
 */
public class VerifikatorPembayaranAction extends BaseMasterAction{
    private final static transient Logger logger = Logger.getLogger(VerifikatorPembayaranAction.class);

    private VerifikatorPembayaranBo verifikatorPembayaranBoProxy;
    private TelemedicBo telemedicBoProxy;
    private NotifikasiFcmBo notifikasiFcmBoProxy;
    private PembayaranOnline pembayaranOnline;
    public AntrianTelemedic antrianTelemedic;
    private NotifikasiBo notifikasiBoProxy;
    private String id;
    private String tipe;
    private String notifid;

    public String getNotifid() {
        return notifid;
    }

    public void setNotifid(String notifid) {
        this.notifid = notifid;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

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

        String dateNow = CommonUtil.convertDateToString2(new Date(System.currentTimeMillis()));
        setPembayaranOnline(new PembayaranOnline());
        AntrianTelemedic antrianTelemedic = new AntrianTelemedic();
        antrianTelemedic.setStDateFrom(dateNow);
        antrianTelemedic.setStDateTo(dateNow);
        setAntrianTelemedic(antrianTelemedic);

        logger.info("[VerifikatorPembayaranAction.initForm] END <<<");
        if ("print".equalsIgnoreCase(tipe))
            return "init_print";

        return "search";
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    @Override
    public String add() {
        return null;
    }

    @Override
    public String edit() {
        return null;
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        return null;
    }

    public String search(){
        logger.info("[VerifikatorPembayaranAction.search] START >>>");

        String userName = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String branchId = CommonUtil.userBranchLogin();

        AntrianTelemedic antrianTelemedic = getAntrianTelemedic();
        AntrianTelemedic searchAntrian = new AntrianTelemedic();
        if ("notif".equalsIgnoreCase(this.tipe) && this.id != null && this.notifid != null){

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            NotifikasiAdminBo notifikasiAdminBo = (NotifikasiAdminBo) ctx.getBean("notifikasiAdminBoProxy");

            try {
                notifikasiAdminBo.readNotifikasiAdmin(this.notifid, userName, time);
            } catch (GeneralBOException e){
                logger.error("[VerifikatorPembayaranAction.search] ERROR Notif. ",e);
            }

            searchAntrian.setId(this.id);

        } else {
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
        }

        // Sigit 2021-01-26, Pencarian Untuk Hari Ini
        searchAntrian.setStDateFrom(getStCurrentDate());
        searchAntrian.setStDateTo(getStCurrentDate());
        searchAntrian.setFlagDateNow(getStCurrentDate());

        searchAntrian.setFlagKasir(checkKasirIfAvailableShift() == true ? "Y" : "N");


        List<AntrianTelemedic> listResults = new ArrayList<>();
        try {
            listResults = telemedicBoProxy.getSearchByCriteria(searchAntrian);
        } catch (GeneralBOException e){
            logger.error("[VerifikatorPembayaranAction.search] ERROR. ",e);
            throw new GeneralBOException("[VerifikatorPembayaranAction.search] ERROR. ",e);
        }

//        if (searchAntrian.getStDateFrom() != null && !"".equalsIgnoreCase(searchAntrian.getStDateFrom()))
//            searchAntrian.setStDateFrom(getStCurrentDate());
//        if (searchAntrian.getStDateTo() != null && !"".equalsIgnoreCase(searchAntrian.getStDateTo()))
//            searchAntrian.setStDateTo(getStCurrentDate());

        setAntrianTelemedic(searchAntrian);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResults");
        session.setAttribute("listOfResults", listResults);

        logger.info("[VerifikatorPembayaranAction.search] END <<<");
        return "search";
    }

    private String getStCurrentDate(){
        Date date = new Date(System.currentTimeMillis());
        return CommonUtil.convertDateToString2(date);
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
        logger.info("[VerifikatorPembayaranAction.approveEresep] START >>>");

        Timestamp time = CommonUtil.getCurrentDateTimes();
        String userLogin = CommonUtil.userLogin();

        CheckResponse response = new CheckResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");
        ResepOnlineBo resepOnlineBo = (ResepOnlineBo) ctx.getBean("resepOnlineBoProxy");
        NotifikasiFcmBo notifikasiFcmBo = (NotifikasiFcmBo) ctx.getBean("notifikasiFcmBoProxy");
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

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

                    List<NotifikasiFcm> notifikasiFcm = new ArrayList<>();
                    NotifikasiFcm bean = new NotifikasiFcm();
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

        // Sigit 2021-02-02, Penambahan Pencarian Shift kasir telemedicine
        String idUser       = user == null ? CommonUtil.userIdLogin() : user;
        String unitLogin    = branch == null ? CommonUtil.userBranchLogin() : branch;
        String shiftId      = getShiftIdByNip(idUser, unitLogin);
        // END

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

                        if (!"".equalsIgnoreCase(shiftId)){
                            pembayaranOnlineEntity.setShiftId(shiftId);
                        }

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
                    response = saveApproveAllTindakanRawatJalan(idDetailCheckup, idJenisPeriksaPasien, userLogin);
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
                        antrianTelemedic.setId(firstOrderAntrian.getId());
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
                if (!"Y".equalsIgnoreCase(antrianTelemedicEntity.getFlagEresep()) && "umum".equalsIgnoreCase(antrianTelemedicEntity.getIdJenisPeriksaPasien())){
                    jurnalResponse = closingJurnalNonTunaiTelemedic(idDetailCheckup, idTransaksi, antrianTelemedicEntity.getIdPelayanan(), antrianTelemedicEntity.getIdPasien(), flagResep, userLogin, branchId);
                }

                // --- update flag; jika success pada prosess membuat jurnal;
                if ("success".equalsIgnoreCase(jurnalResponse.getStatus()) || "asuransi".equalsIgnoreCase(antrianTelemedicEntity.getIdJenisPeriksaPasien())){

                    pembayaranOnlineEntity.setIdRiwayatTindakan(idRiwayatTindakan);
                    pembayaranOnlineEntity.setApprovedFlag("Y");
                    pembayaranOnlineEntity.setApprovedWho(userLogin);
                    pembayaranOnlineEntity.setLastUpdate(time);
                    pembayaranOnlineEntity.setLastUpdateWho(userLogin);
                    pembayaranOnlineEntity.setAction("U");
                    if (!"".equalsIgnoreCase(shiftId)){
                        pembayaranOnlineEntity.setShiftId(shiftId);
                    }

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

    public CheckResponse saveApproveAllTindakanRawatJalan(String idDetailCheckup, String jenisPasien, String user) {

        logger.info("[VerifikatorPembayaranAction.saveApproveAllTindakanRawatJalan] START process >>>");
        CheckResponse response = new CheckResponse();
        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {

            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
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
                saveAddToRiwayatTindakan(idDetailCheckup, jenisPasien, user);
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

    public void saveAddToRiwayatTindakan(String idDetail, String jenisPasien, String user) {
        logger.info("[VerifikatorPembayaranAction.saveAddToRiwayatTindakan] START process >>>");
        if (idDetail != null && !"".equalsIgnoreCase(idDetail)) {

            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
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

                        BigDecimal totalTarif = null;

                        try {
                            totalTarif = periksaLabBo.getTarifTotalPemeriksaan(entity.getIdPeriksaLab());
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
                                riwayatTindakan.setTotalTarif(totalTarif);
                            }
                        } else {

                            // jika bukan paket maka pakai tarif asli
                            riwayatTindakan.setTotalTarif(totalTarif);
                        }

                        riwayatTindakan.setKeterangan(entity.getKategori());
                        riwayatTindakan.setJenisPasien(jenPasien);
                        riwayatTindakan.setAction("C");
                        riwayatTindakan.setFlag("Y");
                        riwayatTindakan.setCreatedWho(user);
                        riwayatTindakan.setCreatedDate(updateTime);
                        riwayatTindakan.setLastUpdate(updateTime);
                        riwayatTindakan.setLastUpdateWho(user);
                        riwayatTindakan.setTanggalTindakan(entity.getCreatedDate());
                        String ktb = "";
                        if("lab".equalsIgnoreCase(entity.getKategori())){
                            ktb = "laboratorium";
                        }else{
                            ktb = "radiologi";
                        }
                        riwayatTindakan.setKategoriTindakanBpjs(ktb);

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

    private JurnalResponse closingJurnalNonTunaiTelemedic(String idDetailCheckup, String idTransaksiOnline, String idPoli, String idPasien, String flagResep, String userLogin, String branchId) {

        JurnalResponse response = new JurnalResponse();
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
        String kodeBank = "1.1.01.02.01";
        String idJenisPeriksaPasien = "";
        String noRekening = CommonConstant.REK_BANK_BRI_TELE;
        BigDecimal biayaCover = new BigDecimal(0);
        BigDecimal tarifTele = new BigDecimal(0);
        BigDecimal nominalUnik = new BigDecimal(0);
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
                tarifTele = pembayaranOnlineEntity.getNominal();

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
                logger.error("[VerifikatorPembayaranAction.closingJurnalNonTunaiTelemedic] Error Asuransi tidak ditemukan");
                response.setStatus("error");
                response.setMsg("[VerifikatorPembayaranAction.closingJurnalNonTunaiTelemedic] Error Asuransi tidak ditemukan");
                return response;
            }
        }

        // MAP ALL TINDAKAN BY KETERANGAN
        List<Map> listOfTindakan = new ArrayList<>();
        Map mapTindakan = new HashMap();

        if ("asuransi".equalsIgnoreCase(idJenisPeriksaPasien)){
            List<String> listOfKeteranganRiwayat = riwayatTindakanBo.getListKeteranganByIdDetailCheckup(idDetailCheckup);
            if (listOfKeteranganRiwayat.size() > 0) {

                for (String keteranganRiwayat : listOfKeteranganRiwayat) {
                    mapTindakan = new HashMap();
                    mapTindakan.put("master_id", masterId);
                    mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, idJenisPeriksaPasien, keteranganRiwayat));
                    mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, idJenisPeriksaPasien, keteranganRiwayat));
                    mapTindakan.put("activity", getAcitivityList(idDetailCheckup, idJenisPeriksaPasien, keteranganRiwayat, kode));
                    listOfTindakan.add(mapTindakan);
                }
            }
        } else {
            //BigDecimal jumlahBiaya = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, idJenisPeriksaPasien, keterangan);
            //BigDecimal tarifLain = tarifTele.subtract(jumlahBiaya);
            BigDecimal tarifTindakan =  getJumlahNilaiBiayaByKeterangan(idDetailCheckup, idJenisPeriksaPasien, keterangan);
            nominalUnik = tarifTele.subtract(tarifTindakan);
            mapTindakan.put("master_id", masterId);
            mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, idJenisPeriksaPasien, keterangan));
            mapTindakan.put("nilai", tarifTindakan);
            mapTindakan.put("activity", getAcitivityList(idDetailCheckup, idJenisPeriksaPasien, keterangan, kode));
            listOfTindakan.add(mapTindakan);
        }

        // pendapatan lain dari nominal unik
        Map mapPendapatanLain = new HashMap();
        mapPendapatanLain.put("nilai", nominalUnik);


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
//                mapPajakObat.put("bukti", invoice);
                mapPajakObat.put("nilai", ppnObat);
                mapPajakObat.put("master_id", CommonConstant.MASTER_PAJAK_OBAT);

                if ("umum".equalsIgnoreCase(idJenisPeriksaPasien)){

                    // create list map piutang
                    Map mapkas = new HashMap();
                    mapkas.put("nilai",  jumlah.add(ppnObat).add(nominalUnik));
                    mapkas.put("metode_bayar", "transfer");
                    mapkas.put("bank", kodeBank);
                    mapkas.put("nomor_rekening", noRekening);

                    mapJurnal.put("ppn_keluaran", mapPajakObat);
                    mapJurnal.put("pendapatan_rawat_jalan_umum", listOfTindakan);
                    mapJurnal.put("pendapatan_lain", mapPendapatanLain);
                    mapJurnal.put("kas", mapkas);
                    transId = "91";

                } else if ("asuransi".equalsIgnoreCase(idJenisPeriksaPasien)){

                    // create list map piutang
                    Map mapPiutang = new HashMap();
//                    mapPiutang.put("bukti", invoice);
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
                Map mapkas = new HashMap();
                mapkas.put("nilai", jumlah.add(nominalUnik));
                mapkas.put("metode_bayar", "transfer");
                mapkas.put("bank", kodeBank);
                mapkas.put("nomor_rekening", noRekening);

                mapJurnal.put("pendapatan_rawat_jalan_umum", listOfTindakan);
                mapJurnal.put("pendapatan_lain", mapPendapatanLain);
                mapJurnal.put("kas", mapkas);
                transId = "90";
            } else if ("asuransi".equalsIgnoreCase(idJenisPeriksaPasien)){
                invoice = billingSystemBo.createInvoiceNumber("JRJ", branchId);

                // create list map piutang
                Map mapPiutang = new HashMap();
//                mapPiutang.put("bukti", invoice);
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

            Jurnal noJurnal = billingSystemBo.createJurnal(transId, mapJurnal, branchId, catatan, "Y");

            // --- update no invoice;
            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setIdDetailCheckup(idDetailCheckup);
            detailCheckup.setInvoice(noJurnal.getNoJurnal());
            detailCheckup.setNoJurnal(noJurnal.getNoJurnal());

            checkupDetailBo.saveUpdateNoJuran(detailCheckup);
            // ---

            response.setStatus("success");
            response.setMsg("[Berhasil]");

        } catch (GeneralBOException e) {
            logger.error("[VerifikatorPembayaranAction.closingJurnalNonTunaiTelemedic] Error, ", e);
            response.setStatus("error");
            response.setMsg("[VerifikatorPembayaranAction.closingJurnalNonTunaiTelemedic] Error, " + e);
            return response;
        }

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
//        mapPPN.put("bukti", billingSystemBo.createInvoiceNumber("JPD", branchId));
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
            Jurnal jurnal = billingSystemBo.createJurnal("29", hsCriteria, branchId, "Penjualan Obat Apotik Langsung E-Obat " + branchId, "Y");
            noJurnal = jurnal.getNoJurnal();
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
                } else {
                    PembayaranOnline pembayaranOnline = new PembayaranOnline();
                    pembayaranOnline.setIdAntrianTelemedic(id);

                    List<ItSimrsPembayaranOnlineEntity> pembayaranOnlineEntities = verifikatorPembayaranBo.getSearchEntityByCriteria(pembayaranOnline);
                    if (pembayaranOnlineEntities.size() > 0){
                        ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = pembayaranOnlineEntities.get(pembayaranOnlineEntities.size() - 1);
                        if (pembayaranOnlineEntity.getUrlFotoBukti() != null){
                            antrianTelemedic.setFlagViewApproveConfirm("Y");
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

                        if ("Y".equalsIgnoreCase(antrianTelemedic.getFlagApproveConfirm()) || "Y".equalsIgnoreCase(antrianTelemedic.getFlagViewApproveConfirm())){
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

    private String getKodeRekeningPositionByIdPelayanan(String idPelayanan){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        PositionBo positionBo = (PositionBo) ctx.getBean("positionBoProxy");

        ImSimrsPelayananEntity pelayananEntity = pelayananBo.getPelayananById(idPelayanan);
        ImPosition position = positionBo.getPositionEntityById(pelayananEntity.getDivisiId());
        if (position != null) {
            return position.getKodering();
        }
        return "";
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
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
        VerifikatorAsurasiBo verifikatorAsurasiBo = (VerifikatorAsurasiBo) ctx.getBean("verifikatorAsurasiBoProxy");
        NotifikasiFcmBo notifikasiFcmBo = (NotifikasiFcmBo) ctx.getBean("notifikasiFcmBoProxy");
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

        String fileName = "";
        if (!"".equalsIgnoreCase(uploadString)){
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decodedBytes = decoder.decodeBuffer(uploadString);
            logger.info("Decoded upload data : " + decodedBytes.length);
            fileName = branchId + "_" + jenis + "_"+idStruk+".png";
            String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_BUKTI_TRANSFER + "/" + fileName;
            logger.info("File save path : " + uploadFile);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

            if (image == null) {
                logger.error("Buffered Image is null");
            }else{

//                write the image
//                File fileCreate = new File(CommonUtil.getPropertyParams("upload.folder")+CommonConstant.RESOURCE_PATH_BUKTI_TRANSFER, fileName);
//                try {
//                    FileUtils.copyFile(f, fileCreate);
//                }catch (IOException e){
//                    e.printStackTrace();
//                }


                File f = new File(uploadFile);

                try {
                    ImageIO.write(image, "png", f);
                }catch (IOException e){
                    logger.error("[VerifikatorPembayaranAction.uploadStruk] ERROR ", e);

                    response.setMsg("[VerifikatorPembayaranAction.uploadStruk] ERROR " + e);
                    response.setStatus("error");
                    return response;
                }
            }
        }

        StrukAsuransi strukAsuransi = new StrukAsuransi();
        strukAsuransi.setId(idStruk);
//        strukAsuransi.setUrlFotoStruk(jenis + ".jpg");
        strukAsuransi.setUrlFotoStruk(fileName);
        strukAsuransi.setLastUpdate(time);
        strukAsuransi.setLastUpdateWho(userLogin);

        if ("confirmation".equalsIgnoreCase(jenis)){
            strukAsuransi.setJumlahCover(jumlahCover == null || "".equalsIgnoreCase(jumlahCover) ? new BigDecimal(0) : new BigDecimal(jumlahCover));
            strukAsuransi.setDibayarPasien(dibayarPasien == null || "".equalsIgnoreCase(dibayarPasien) ? new BigDecimal(0) : new BigDecimal(dibayarPasien));
        }

        String idAntrian = "";
        try {
            idAntrian = verifikatorAsurasiBo.saveUploadStrukAsuransi(strukAsuransi);
            response.setStatus("success");
        } catch (GeneralBOException e){
            logger.error("[VerifikatorPembayaranAction.uploadStruk] ERROR ", e);
            response.setStatus("error");
            response.setMsg("[VerifikatorPembayaranAction.uploadStruk] ERROR " + e);
            return response;
        }

        List<ItSimrsStrukAsuransiEntity> strukAsuransiList = new ArrayList<>();
        List<AntrianTelemedic> antrianTelemedicList = new ArrayList<>();
        List<NotifikasiFcm> notifikasiFcm = new ArrayList<>();

        StrukAsuransi strukAsuransi1 = new StrukAsuransi();
        strukAsuransi1.setId(idStruk);

        try {
           strukAsuransiList = verifikatorAsurasiBo.getListStrukAsurasiEntity(strukAsuransi1);
        } catch (GeneralBOException e){
            logger.error("[VerifikatorPembayaranAction.uploadStruk] Error, " + e);
        }

        AntrianTelemedic antrianTelemedic = new AntrianTelemedic();
        if  (strukAsuransiList.size() != 0) {
            antrianTelemedic.setId(strukAsuransiList.get(0).getIdAntrianTelemedic());

            try {
                antrianTelemedicList = telemedicBo.getSearchByCriteria(antrianTelemedic);
            } catch (GeneralBOException e){
                logger.error("[VerifikatorPembayaranAction.uploadStruk] Error, " + e);
            }

            Notifikasi notifBean = new Notifikasi();
            NotifikasiFcm bean = new NotifikasiFcm();

            notifBean.setNip(antrianTelemedicList.get(0).getIdPasien());
            notifBean.setNamaPegawai("admin");
            notifBean.setTipeNotifId("TN10");
            notifBean.setNote("Struk asuransi telah di upload");
            notifBean.setTo(antrianTelemedicList.get(0).getIdPasien());
            notifBean.setFromPerson("admin");
            notifBean.setNoRequest(antrianTelemedicList.get(0).getId());
            notifBean.setFlag("Y");
            notifBean.setRead("N");
            notifBean.setAction("C");
            notifBean.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            notifBean.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            notifBean.setCreatedWho("admin");
            notifBean.setLastUpdateWho("admin");

            notifikasiBo.saveAdd(notifBean);

            //Push Notif ketika upload struk
            bean.setUserId(antrianTelemedicList.get(0).getIdPasien());
            notifikasiFcm = notifikasiFcmBo.getByCriteria(bean);
            FirebasePushNotif.sendNotificationFirebase(notifikasiFcm.get(0).getTokenFcm(),"Telemedic", "Struk Asuransi telah di upload", "WL", notifikasiFcm.get(0).getOs(), null);

        }


        // create jurnal asuransi untuk tidak ada selisih
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        if (!"".equalsIgnoreCase(idAntrian)){
            ItSimrsHeaderChekupEntity headerChekupEntity = verifikatorPembayaranBo.getHeaderCheckupByIdAntrinTelemedic(idAntrian);
            if (headerChekupEntity != null){
                HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                detailCheckup.setNoCheckup(headerChekupEntity.getNoCheckup());

                List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntities = checkupDetailBo.getListEntityByCriteria(detailCheckup);
                if (detailCheckupEntities.size() > 0){
                    for (ItSimrsHeaderDetailCheckupEntity detailCheckupEntity : detailCheckupEntities){
                        if (detailCheckupEntity.getDibayarPasien().compareTo(new BigDecimal(0)) == 0){
                            if (detailCheckupEntity.getIdTransaksiOnline() != null){
                                ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranBo.getPembayaranOnlineById(detailCheckupEntity.getIdTransaksiOnline());

                                ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(pembayaranOnlineEntity.getIdAntrianTelemedic());
                                boolean withResep = antrianTelemedicEntity != null && "Y".equalsIgnoreCase(antrianTelemedicEntity.getFlagResep());

                                if (withResep){
                                    JurnalResponse jurnalResponse = closingJurnalNonTunaiTelemedic(detailCheckupEntity.getIdDetailCheckup(), detailCheckupEntity.getIdTransaksiOnline(), detailCheckupEntity.getIdPelayanan(), headerChekupEntity.getIdPasien(), "Y", userLogin, branchId);
                                    if ("error".equalsIgnoreCase(jurnalResponse.getStatus())){
                                        logger.error("[VerifikatorPembayaranAction.approveConfirmAsuransi] ERROR. " + response.getMsg());
                                        response.setStatus("error");
                                        response.setMsg("[VerifikatorPembayaranAction.approveConfirmAsuransi] ERROR. " + response.getMsg());
                                        return response;
                                    }
                                } else {
                                    JurnalResponse jurnalResponse = closingJurnalNonTunaiTelemedic(detailCheckupEntity.getIdDetailCheckup(), detailCheckupEntity.getIdTransaksiOnline(), detailCheckupEntity.getIdPelayanan(), headerChekupEntity.getIdPasien(), "N", userLogin, branchId);
                                    if ("error".equalsIgnoreCase(jurnalResponse.getStatus())){
                                        logger.error("[VerifikatorPembayaranAction.approveConfirmAsuransi] ERROR. " + response.getMsg());
                                        response.setStatus("error");
                                        response.setMsg("[VerifikatorPembayaranAction.approveConfirmAsuransi] ERROR. " + response.getMsg());
                                        return response;
                                    }
                                }

                            } else {
                                logger.error("[VerifikatorPembayaranAction.approveConfirmAsuransi] ERROR, tidak ditemukan id transaksi online. ");
                                response.setStatus("error");
                                response.setMsg("[VerifikatorPembayaranAction.approveConfirmAsuransi] ERROR, tidak ditemukan id transaksi online. ");
                                return response;
                            }
                        }
                    }
                }
            }
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
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        AntrianTelemedic antrianTelemedic = new AntrianTelemedic();
        antrianTelemedic.setId(idAntrian);
        antrianTelemedic.setFlagApproveConfirm("Y");
        antrianTelemedic.setLastUpdate(times);
        antrianTelemedic.setLastUpdateWho(userLogin);

        // mapping riwayat tindakan jika ada yang dibayar pasien
        mappingBiayaSelisihDiBayarPasienAsuransi(idAntrian);

        ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(idAntrian);
        if (antrianTelemedicEntity != null){
            ItSimrsHeaderChekupEntity headerChekupEntity = verifikatorPembayaranBo.getHeaderCheckupByIdAntrinTelemedic(idAntrian);
            if (headerChekupEntity != null){

                HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                headerDetailCheckup.setNoCheckup(headerChekupEntity.getNoCheckup());

                List<ItSimrsHeaderDetailCheckupEntity> headerDetailCheckupEntities = checkupDetailBo.getListEntityByCriteria(headerDetailCheckup);
                if (headerDetailCheckupEntities.size() > 0){
                    ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = headerDetailCheckupEntities.get(0);
                    JurnalResponse jurnalResponse = closingJurnalAsuransidanUmum(detailCheckupEntity.getIdDetailCheckup(), antrianTelemedicEntity.getFlagResep(), antrianTelemedicEntity.getKodeBank());
                    if ("error".equalsIgnoreCase(jurnalResponse.getStatus())){
                        logger.error("[VerifikatorPembayaranAction.approveConfirmAsuransi] ERROR " + jurnalResponse.getMsg());
                        response.setStatus("error");
                        response.setMsg("[VerifikatorPembayaranAction.approveConfirmAsuransi] ERROR " + jurnalResponse.getMsg());
                        return response;
                    }
                }
            }
        }


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

    public CrudResponse approveConfirmKembaliDana(String idBatalTelemedic, String jenis, String nominal){

        String userLogin = CommonUtil.userLogin();
        Timestamp times = new Timestamp(System.currentTimeMillis());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");

        JurnalResponse jurnalResponse = new JurnalResponse();
        CrudResponse response = new CrudResponse();

        BigDecimal jumlahNominal = nominal == null || nominal == "" ? new BigDecimal(0) : new BigDecimal(nominal);
        boolean foundAntrian = false;
        boolean successJurnal = false;
        ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = new ItSimrsAntrianTelemedicEntity();
        ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = new ItSimrsPembayaranOnlineEntity();

        ItSimrsBatalTelemedicEntity batalTelemedicEntity = telemedicBo.getEnitityBatalTelemedicById(idBatalTelemedic);
        if (batalTelemedicEntity != null){
            antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(batalTelemedicEntity.getIdAntrianTelemedic());
            foundAntrian = antrianTelemedicEntity.getId() != null;
        }

        BatalTelemedic batalTelemedic = new BatalTelemedic();
        batalTelemedic.setId(idBatalTelemedic);
        if ("konsultasi".equalsIgnoreCase(jenis)){
            batalTelemedic.setFlagKembaliKonsultasi("Y");
            batalTelemedic.setKembaliKonsultasi(jumlahNominal);
        }
        if ("resep".equalsIgnoreCase(jenis)){
            batalTelemedic.setFlagKembaliResep("Y");
            batalTelemedic.setKembaliResep(jumlahNominal);
        }

        if (foundAntrian){
            pembayaranOnlineEntity = verifikatorPembayaranBo.getPembayaranOnlineEntityByIdAntrianAndJenis(antrianTelemedicEntity.getId(), jenis);
            if (pembayaranOnlineEntity != null){
                jurnalResponse = closingJurnalRefundDana(antrianTelemedicEntity, pembayaranOnlineEntity, idBatalTelemedic,jenis, jumlahNominal);
                successJurnal = "success".equalsIgnoreCase(jurnalResponse.getStatus());
            }
        }

        batalTelemedic.setLastUpdate(times);
        batalTelemedic.setLastUpdateWho(userLogin);

        if (successJurnal){
            try {
                telemedicBo.confirmKembalian(batalTelemedic);
                response.setStatus("success");
            } catch (GeneralBOException e){
                logger.error("[VerifikatorPembayaranAction.approveConfirmKembaliDana] ERROR ", e);
                response.setStatus("error");
                response.setMsg("[VerifikatorPembayaranAction.approveConfirmKembaliDana] ERROR " + e);
                return response;
            }
        } else {
            logger.error(jurnalResponse.getMsg());
            response.setStatus("error");
            response.setMsg(jurnalResponse.getMsg());
            return response;
        }

        return response;
    }

    private void mappingBiayaSelisihDiBayarPasienAsuransi(String idAntrian){

        String userLogin = CommonUtil.userIdLogin();
        Timestamp times = new Timestamp(System.currentTimeMillis());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");

        ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(idAntrian);

        if (antrianTelemedicEntity != null && "asuransi".equalsIgnoreCase(antrianTelemedicEntity.getIdJenisPeriksaPasien())){
            ItSimrsHeaderChekupEntity headerChekupEntity = verifikatorPembayaranBo.getHeaderCheckupByIdAntrinTelemedic(idAntrian);
            if (headerChekupEntity != null){

                HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                detailCheckup.setNoCheckup(headerChekupEntity.getNoCheckup());
                List<ItSimrsHeaderDetailCheckupEntity> detailCheckupEntities = checkupDetailBo.getListEntityByCriteria(detailCheckup);

                if (detailCheckupEntities.size() > 0){
                    ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = detailCheckupEntities.get(0);

                    if (detailCheckupEntity != null) {

                        RiwayatTindakan riwayatTindakan = new RiwayatTindakan();
                        riwayatTindakan.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                        riwayatTindakan.setJenisPasien("umum");

                        // cari apakah sudah dibuatkan jenis umum;
                        List<ItSimrsRiwayatTindakanEntity> riwayatTindakanEntities = riwayatTindakanBo.getListEntityRiwayatTindakan(riwayatTindakan);
                        if (riwayatTindakanEntities.size() == 0){
                            // jika tidak ada maka prosess selanjutnya
                            BigDecimal cover = detailCheckupEntity.getCoverBiaya() == null ? new BigDecimal(0) : detailCheckupEntity.getCoverBiaya();
                            BigDecimal dibayarPasien = detailCheckupEntity.getDibayarPasien() == null ? new BigDecimal(0) : detailCheckupEntity.getDibayarPasien();

                            BigDecimal total = cover.add(dibayarPasien);
                            BigDecimal biayaTindakan = new BigDecimal(0);
                            BigDecimal biayaResep = new BigDecimal(0);

                            riwayatTindakan = new RiwayatTindakan();
                            riwayatTindakan.setIdDetailCheckup(detailCheckupEntity.getIdDetailCheckup());
                            List<ItSimrsRiwayatTindakanEntity> tindakanEntities = riwayatTindakanBo.getListEntityRiwayatTindakan(riwayatTindakan);
                            if (tindakanEntities.size() > 0){

                                BigDecimal persenDibayar = dibayarPasien.divide(total, BigDecimal.ROUND_HALF_UP, 2).multiply(new BigDecimal(100));
                                BigDecimal dibayarDariTindakan = new BigDecimal(0);
                                BigDecimal dibayarDariResep = new BigDecimal(0);

                                for (ItSimrsRiwayatTindakanEntity riwayatTindakanEntity : tindakanEntities){
                                    if ("tindakan".equalsIgnoreCase(riwayatTindakanEntity.getKeterangan())){
                                        biayaTindakan = biayaTindakan.add(riwayatTindakanEntity.getTotalTarif());
                                        dibayarDariTindakan = biayaTindakan.multiply(persenDibayar.divide(new BigDecimal(100), BigDecimal.ROUND_HALF_UP, 2));
                                    }
                                    if ("resep".equalsIgnoreCase(riwayatTindakanEntity.getKeterangan())){
                                        biayaResep = biayaResep.add(riwayatTindakanEntity.getTotalTarif());
                                        dibayarDariResep = biayaResep.multiply(persenDibayar.divide(new BigDecimal(100), BigDecimal.ROUND_HALF_UP, 2));
                                    }
                                }

                                Map mapBiayaTindakan = new HashMap();

                                BigDecimal selisih = new BigDecimal(0);
                                BigDecimal totalHasilBagi = dibayarDariTindakan.add(dibayarDariResep);

                                MathContext m = new MathContext(3);

                                if (dibayarPasien.compareTo(totalHasilBagi) == 1){
                                    selisih = dibayarPasien.subtract(totalHasilBagi);
                                    selisih = selisih.divide(new BigDecimal(2), BigDecimal.ROUND_HALF_UP, 2);
                                    dibayarDariTindakan = dibayarDariTindakan.add(selisih);
                                    dibayarDariResep = dibayarDariResep.add(selisih);
                                    mapBiayaTindakan.put("tindakan", dibayarDariTindakan.round(m));
                                    mapBiayaTindakan.put("resep", dibayarDariResep.round(m));
                                }  else {
                                    selisih = totalHasilBagi.subtract(dibayarPasien);
                                    selisih = selisih.divide(new BigDecimal(2), BigDecimal.ROUND_HALF_UP, 2);
                                    dibayarDariTindakan = dibayarDariTindakan.subtract(selisih);
                                    dibayarDariResep = dibayarDariResep.subtract(selisih);
                                    mapBiayaTindakan.put("tindakan", dibayarDariTindakan.round(m));
                                    mapBiayaTindakan.put("resep", dibayarDariResep.round(m));
                                }


                                for (ItSimrsRiwayatTindakanEntity riwayatTindakanEntity : tindakanEntities){
                                    riwayatTindakanEntity.setLastUpdate(times);
                                    riwayatTindakanEntity.setLastUpdateWho(userLogin);

                                    // membuat versi umum dari dibayar pasien;
                                    saveAddSelisihAsuransiKeUmum(riwayatTindakanEntity, mapBiayaTindakan);

                                    // update biaya masing-masing tindakan;
                                    BigDecimal tindakanDibayar = (BigDecimal) mapBiayaTindakan.get(riwayatTindakanEntity.getKeterangan());
                                    riwayatTindakanEntity.setTotalTarif(riwayatTindakanEntity.getTotalTarif().subtract(tindakanDibayar));
                                    try {
                                        riwayatTindakanBo.updateByEntity(riwayatTindakanEntity);
                                    } catch (GeneralBOException e){
                                        logger.error("[VerifikatorPembayaranAction.mappingBiayaSelisihDiBayarPasienAsuransi] ERROR. ", e);
                                        throw new GeneralBOException("[VerifikatorPembayaranAction.mappingBiayaSelisihDiBayarPasienAsuransi] ERROR. ", e);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void saveAddSelisihAsuransiKeUmum(ItSimrsRiwayatTindakanEntity bean, Map mapTindakan){
        logger.info("[VerifikatorPembayaranAction.saveAddSelisihAsuransiKeUmum] START >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");

        RiwayatTindakan riwayatTindakanEntityNew = new RiwayatTindakan();
        riwayatTindakanEntityNew.setIdTindakan(bean.getIdTindakan());
        riwayatTindakanEntityNew.setNamaTindakan(bean.getNamaTindakan());
        riwayatTindakanEntityNew.setKeterangan(bean.getKeterangan());
        riwayatTindakanEntityNew.setJenisPasien("umum");
        riwayatTindakanEntityNew.setTotalTarif((BigDecimal) mapTindakan.get(bean.getKeterangan()));
        riwayatTindakanEntityNew.setTanggalTindakan(bean.getTanggalTindakan());
        riwayatTindakanEntityNew.setIdDetailCheckup(bean.getIdDetailCheckup());
        riwayatTindakanEntityNew.setKategoriTindakanBpjs(bean.getKategoriTindakanBpjs());
        riwayatTindakanEntityNew.setApproveBpjsFlag(bean.getApproveBpjsFlag());
        riwayatTindakanEntityNew.setFlag("Y");
        riwayatTindakanEntityNew.setAction("C");
        riwayatTindakanEntityNew.setCreatedDate(bean.getLastUpdate());
        riwayatTindakanEntityNew.setCreatedWho(bean.getLastUpdateWho());
        riwayatTindakanEntityNew.setLastUpdate(bean.getLastUpdate());
        riwayatTindakanEntityNew.setLastUpdateWho(bean.getLastUpdateWho());

        try {
            riwayatTindakanBo.saveAdd(riwayatTindakanEntityNew);
        } catch (GeneralBOException e) {
            logger.error("[VerifikatorPembayaranAction.saveAddSelisihAsuransiKeUmum] ERROR. ", e);
            throw new GeneralBOException("[VerifikatorPembayaranAction.saveAddSelisihAsuransiKeUmum] ERROR. ", e);
        }

        logger.info("[VerifikatorPembayaranAction.saveAddSelisihAsuransiKeUmum] END <<<");
    }

    private JurnalResponse closingJurnalAsuransidanUmum(String idDetailCheckup, String flagResep, String kodeBank) {
        logger.info("[VerifikatorPembayaranAction.closingJurnalAsuransidanUmum] START >>>");

        JurnalResponse response = new JurnalResponse();
        String branchId = CommonUtil.userBranchLogin();
        String invoice = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        AsuransiBo asuransiBo = (AsuransiBo) ctx.getBean("asuransiBoProxy");
        CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");
        RiwayatTindakanBo riwayatTindakanBo = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");

        String kode = "";
        String transId = "";
        String jenisPasien = "Umum ";
        String noRekening = CommonConstant.REK_BANK_BRI_TELE;
        String idJenisPeriksaPasien = "";
        String noKartu = "";
        BigDecimal biayaCover = new BigDecimal(0);
        BigDecimal tarifTele = new BigDecimal(0);
        String withResep = "N";
        String keterangan = "";

        ItSimrsHeaderDetailCheckupEntity detailCheckupEntity = checkupDetailBo.getEntityDetailCheckupByIdDetail(idDetailCheckup);
        if (detailCheckupEntity != null){
            idJenisPeriksaPasien = detailCheckupEntity.getIdJenisPeriksaPasien();
        }

        String masterId = jenisPriksaPasienBo.getJenisPerikasEntityById(detailCheckupEntity.getIdJenisPeriksaPasien()).getMasterId();
        if ("asuransi".equalsIgnoreCase(idJenisPeriksaPasien)){

            ImSimrsAsuransiEntity asuransiEntity = asuransiBo.getEntityAsuransiById(detailCheckupEntity.getIdAsuransi());
            if (asuransiEntity != null) {
                masterId = asuransiEntity.getNoMaster();
                jenisPasien = "Asuransi " + asuransiEntity.getNamaAsuransi() + " No. Kartu " + detailCheckupEntity.getNoKartuAsuransi();
            } else {
                logger.error("[VerifikatorPembayaranAction.closingJurnalAsuransidanUmum] Error Asuransi tidak ditemukan");
                response.setStatus("error");
                response.setMsg("[VerifikatorPembayaranAction.closingJurnalAsuransidanUmum] Error Asuransi tidak ditemukan");
                return response;
            }
        }



        // MAP ALL TINDAKAN BY KETERANGAN
        List<Map> listOfTindakan = new ArrayList<>();
        List<Map> listOfTindakanUmum = new ArrayList<>();
        Map mapTindakan = new HashMap();

        List<String> listOfKeteranganRiwayat = riwayatTindakanBo.getListKeteranganByIdDetailCheckup(idDetailCheckup);
        if (listOfKeteranganRiwayat.size() > 0) {

            for (String keteranganRiwayat : listOfKeteranganRiwayat) {
                mapTindakan = new HashMap();
                mapTindakan.put("master_id", masterId);
                mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, idJenisPeriksaPasien, keteranganRiwayat));
                mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, idJenisPeriksaPasien, keteranganRiwayat));
                mapTindakan.put("activity", getAcitivityList(idDetailCheckup, idJenisPeriksaPasien, keteranganRiwayat, kode));
                listOfTindakan.add(mapTindakan);

                mapTindakan = new HashMap();
                mapTindakan.put("master_id", jenisPriksaPasienBo.getJenisPerikasEntityById("umum").getMasterId());
                mapTindakan.put("divisi_id", getDivisiId(idDetailCheckup, "umum", keteranganRiwayat));
                mapTindakan.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "umum", keteranganRiwayat));
                mapTindakan.put("activity", getAcitivityList(idDetailCheckup, "umum", keteranganRiwayat, kode));
                listOfTindakanUmum.add(mapTindakan);
            }
        }

        Map mapKas = new HashMap();
        mapKas.put("metode_bayar", "transfer");
        mapKas.put("bank", kodeBank);
        mapKas.put("nilai", getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "umum", ""));
        mapKas.put("nomor_rekening", noRekening);


        // MENDAPATKAN SEMUA BIAYA RAWAT;
        BigDecimal jumlah = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, idJenisPeriksaPasien, "");
        BigDecimal ppnObat = new BigDecimal(0);
        Map mapJurnal = new HashMap();
        if ("Y".equalsIgnoreCase(flagResep)){
            keterangan = " dengan Resep ";

            BigDecimal jumlahResep = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, idJenisPeriksaPasien, "resep");
            BigDecimal jumlahResepUmum = getJumlahNilaiBiayaByKeterangan(idDetailCheckup, "umum", "resep");
            if (jumlahResep != null && jumlahResep.compareTo(new BigDecimal(0)) == 1){

                if (jumlahResep.compareTo(new BigDecimal(0)) == 1) {
                    BigDecimal ppnObatUmum = jumlahResepUmum.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    ppnObat = jumlahResep.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    ppnObat = ppnObat.add(ppnObatUmum);
                }

                Map mapPajakObat = new HashMap();
//                mapPajakObat.put("bukti", invoice);
                mapPajakObat.put("nilai", ppnObat);
                mapPajakObat.put("master_id", CommonConstant.MASTER_PAJAK_OBAT);

                invoice = billingSystemBo.createInvoiceNumber("JRJ", branchId);

                // create list map piutang
                Map mapPiutang = new HashMap();
//                mapPiutang.put("bukti", invoice);
                mapPiutang.put("nilai", jumlah.add(ppnObat));
                mapPiutang.put("master_id", masterId);

                // debit piutang pasien asuransi
                mapJurnal.put("kas", mapKas);
                mapJurnal.put("piutang_pasien_asuransi", mapPiutang);
                mapJurnal.put("pendapatan_rawat_jalan_asuransi", listOfTindakan);
                mapJurnal.put("pendapaatan_rawat_jalan_umum", listOfTindakanUmum);
                mapJurnal.put("ppn_keluaran", mapPajakObat);
                transId = "19";
            }

        } else {
            invoice = billingSystemBo.createInvoiceNumber("JRJ", branchId);

            // create list map piutang
            Map mapPiutang = new HashMap();
//            mapPiutang.put("bukti", invoice);
            mapPiutang.put("nilai", jumlah);
            mapPiutang.put("master_id", masterId);

            // debit piutang pasien asuransi
            mapJurnal.put("kas", mapKas);
            mapJurnal.put("piutang_pasien_asuransi", mapPiutang);
            mapJurnal.put("pendapatan_rawat_jalan_asuransi", listOfTindakan);
            mapJurnal.put("pendapatan_rawat_jalan_umum", listOfTindakanUmum);
            transId = "11";
        }


        String catatan = "Closing Jurnal Telemedic "+jenisPasien+" Id Detail Checkup " + idDetailCheckup + keterangan;

        try {

            Jurnal noJurnal = billingSystemBo.createJurnal(transId, mapJurnal, branchId, catatan, "Y");

            // --- update no invoice;
            HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
            detailCheckup = new HeaderDetailCheckup();
            detailCheckup.setIdDetailCheckup(idDetailCheckup);
            detailCheckup.setInvoice(noJurnal.getNoJurnal());
            detailCheckup.setNoJurnal(noJurnal.getNoJurnal());

            checkupDetailBo.saveUpdateNoJuran(detailCheckup);

            response.setStatus("success");
            response.setMsg("[Berhasil]");

        } catch (GeneralBOException e) {
            logger.error("[VerifikatorPembayaranAction.closingJurnalAsuransidanUmum] Error, ", e);
            response.setStatus("error");
            response.setMsg("[VerifikatorPembayaranAction.closingJurnalAsuransidanUmum] Error, " + e);
            return response;
        }

        response.setInvoice(invoice);
        logger.info("[VerifikatorPembayaranAction.closingJurnalAsuransidanUmum] END <<<");
        return response;
    }

    private JurnalResponse closingJurnalRefundDana(ItSimrsAntrianTelemedicEntity antrianTelemedicEntity, ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity, String idBatalDokter, String jenisItem, BigDecimal nominal) {
        logger.info("[VerifikatorPembayaranAction.closingJurnalRefundDana] START >>>");

        JurnalResponse response = new JurnalResponse();
        String branchId = CommonUtil.userBranchLogin();
        String invoice = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
        JenisPriksaPasienBo jenisPriksaPasienBo = (JenisPriksaPasienBo) ctx.getBean("jenisPriksaPasienBoProxy");
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");

        String transId = "";
        String jenisPasien = "Umum ";
        String kodeBank = pembayaranOnlineEntity.getKodeBank();
        String noRekening = CommonConstant.REK_BANK_BRI_TELE;
        String keterangan = "";
        boolean approved = "Y".equalsIgnoreCase(pembayaranOnlineEntity.getApprovedFlag());

        String masterId = jenisPriksaPasienBo.getJenisPerikasEntityById(antrianTelemedicEntity.getIdJenisPeriksaPasien()).getMasterId();

        // MAP ALL TINDAKAN BY KETERANGAN
        List<Map> listOfTindakan = new ArrayList<>();
        Map mapJurnal = new HashMap();

        if (approved){
            Map mapTindakan = new HashMap();
            mapTindakan = new HashMap();
            mapTindakan.put("master_id", masterId);
            mapTindakan.put("divisi_id", getKodeRekeningPositionByIdPelayanan(antrianTelemedicEntity.getIdPelayanan()));
            mapTindakan.put("nilai", nominal);
            listOfTindakan.add(mapTindakan);

            Map mapKas = new HashMap();
            mapKas.put("metode_bayar", "transfer");
            mapKas.put("bank", kodeBank);
            mapKas.put("nilai", nominal);
            mapKas.put("nomor_rekening", noRekening);

            // MENDAPATKAN SEMUA BIAYA RAWAT;
            mapJurnal.put("kas", mapKas);
            mapJurnal.put("pendapatan_rawat_jalan_umum", listOfTindakan);
            transId = "92";
        } else {

            Map mapKasMasuk = new HashMap();
//            mapKasMasuk.put("metode_bayar", "transfer");
            mapKasMasuk.put("rekening_id", kodeRekeningBo.getRekeningIdByKodeRekening(kodeBank));
            mapKasMasuk.put("nilai", nominal);
            mapKasMasuk.put("nomor_rekening", noRekening);

            Map mapKasKeluar = new HashMap();
//            mapKasKeluar.put("metode_bayar", "transfer");
            mapKasKeluar.put("rekening_id", kodeRekeningBo.getRekeningIdByKodeRekening(kodeBank));
            mapKasKeluar.put("nilai", nominal);
            mapKasKeluar.put("nomor_rekening", noRekening);

            // MENDAPATKAN SEMUA BIAYA RAWAT;
            mapJurnal.put("kas_masuk", mapKasMasuk);
            mapJurnal.put("kas_keluar", mapKasKeluar);
            transId = "93";
        }

        String catatan = "Closing Jurnal Refund Dana Telemedic " + jenisItem + " " + jenisPasien +" No Transaksi " + pembayaranOnlineEntity.getId() + keterangan;

        try {

            Jurnal noJurnal = billingSystemBo.createJurnal(transId, mapJurnal, branchId, catatan, "Y");

            // --- update no jurnal;
            telemedicBo.updateNoJurnalBatalDokter(idBatalDokter, noJurnal.getNoJurnal());

            response.setStatus("success");
            response.setMsg("[Berhasil]");

        } catch (GeneralBOException e) {
            logger.error("[VerifikatorPembayaranAction.closingJurnalRefundDana] Error, ", e);
            response.setStatus("error");
            response.setMsg("[VerifikatorPembayaranAction.closingJurnalRefundDana] Error, " + e);
            return response;
        }

        response.setInvoice(invoice);
        logger.info("[VerifikatorPembayaranAction.closingJurnalRefundDana] END <<<");
        return response;
    }

    public String printBuktiRefund(){
        logger.info("[VerifikatorPembayaranAction.printBuktiRefund] START >>>");

        String branchId = CommonUtil.userBranchLogin();
        String areaName = CommonUtil.userAreaName();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");

        ImSimrsPasienEntity pasienEntity = new ImSimrsPasienEntity();
        ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(this.id);
        if (antrianTelemedicEntity != null){
            pasienEntity = pasienBo.getPasienById(antrianTelemedicEntity.getIdPasien());
        }

        Branch branches = new Branch();
        try {
            branches = branchBo.getBranchById(branchId, "Y");
        } catch (GeneralBOException e) {
            logger.error("Found Error when searhc branch logo");
        }

        String logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
        String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(new Timestamp(System.currentTimeMillis()));

        reportParams.put("printDate", formatDate);
        reportParams.put("unit", branches.getBranchName());
        reportParams.put("logo", logo);
        reportParams.put("idAntrian", this.id);
        reportParams.put("namaPasien", pasienEntity.getNama());
        reportParams.put("unit", branches.getBranchName());
        reportParams.put("area", areaName);

        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[VerifikatorPembayaranAction.printBuktiRefund] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        logger.info("[VerifikatorPembayaranAction.printBuktiRefund] END <<<");
        return "print_bukti_refund";
    }

    public CrudResponse kembalikanBukti(String idTransaksi){
        logger.info("[VerifikatorPembayaranAction.kembalikanBukti] START >>>");

        CrudResponse response = new CrudResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");
        TelemedicBo telemedicBo = (TelemedicBo) ctx.getBean("telemedicBoProxy");

        ItSimrsPembayaranOnlineEntity pembayaranOnlineEntity = verifikatorPembayaranBo.getPembayaranOnlineById(idTransaksi);
        if (pembayaranOnlineEntity != null){

            pembayaranOnlineEntity.setUrlFotoBukti("");
            pembayaranOnlineEntity.setWaktuBayar(null);
            pembayaranOnlineEntity.setFlagUploadUlang("Y");

            try {
                verifikatorPembayaranBo.saveEdit(pembayaranOnlineEntity);
            } catch (HibernateException e){
                logger.error("[VerifikatorPembayaranAction.kembalikanBukti] Error, save edit verikator pembayaran ", e);
                response.setStatus("error");
                response.setMsg("[VerifikatorPembayaranAction.kembalikanBukti] Error,  save edit verikator pembayaran" + e);
                return response;
            }

            ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = telemedicBo.getAntrianTelemedicEntityById(pembayaranOnlineEntity.getIdAntrianTelemedic());
            if (antrianTelemedicEntity != null){

                AntrianTelemedic antrianTelemedic = new AntrianTelemedic();
                antrianTelemedic.setId(antrianTelemedicEntity.getId());
                if ("konsultasi".equalsIgnoreCase(pembayaranOnlineEntity.getKeterangan())){
                    antrianTelemedic.setFlagBayarKonsultasi("");
                }
                if ("resep".equalsIgnoreCase(pembayaranOnlineEntity.getKeterangan())){
                    antrianTelemedic.setFlagResep("");
                }

                try {
                    telemedicBo.saveEdit(antrianTelemedic, "", "");
                } catch (HibernateException e){
                    logger.error("[VerifikatorPembayaranAction.kembalikanBukti] Error,  save edit antrian telemedic", e);
                    response.setStatus("error");
                    response.setMsg("[VerifikatorPembayaranAction.kembalikanBukti] Error, save edit antrian telemedic" + e);
                    return response;
                }
                response.setStatus("success");
                response.setMsg("berhasil");
            }
        }

        logger.info("[VerifikatorPembayaranAction.kembalikanBukti] END <<<");
        return response;
    }

    // Sigit 2021-01-02, Check Ijin Edit Jika dia admin tele dan staff tele yg punya shift
    public Boolean checkKasirIfAvailableShift(){
        logger.info("[VerifikatorPembayaranAction.checkKasirIfAvailableShift] START >>>");

        String tipeRole = CommonUtil.getRoleTipePelayanan();
        Boolean found   = false;

        // jika admin tele langsung return true
        if (CommonConstant.TIPE_PELAYANAN_ADMIN_TELE.equalsIgnoreCase(tipeRole)){
            logger.info("[VerifikatorPembayaranAction.checkKasirIfAvailableShift] END <<<");
            return true;
        }

        // jika bukan admin tele check tipe role
        // jika bukan staf kasir tele return false / tidak boleh edit
        if (!CommonConstant.TIPE_PELAYANAN_KASIR_TELE.equalsIgnoreCase(tipeRole)){
            logger.info("[VerifikatorPembayaranAction.checkKasirIfAvailableShift] END <<<");
            return false;
        }

        // jika staf kasir tele check apakah ada shift;
        String userId   = CommonUtil.userIdLogin();
        String stDate   = CommonUtil.convertDateToString2(new Date(System.currentTimeMillis()));
        String stTime   = getStHoursAndMinutes();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");

        try {
            found = verifikatorPembayaranBo.checkIfAvailableShiftOfKasir(userId, stDate, stTime);
        } catch (GeneralBOException e){
            logger.error("[VerifikatorPembayaranAction.checkKasirIfAvailableShift] Error", e);
        }

        logger.info("[VerifikatorPembayaranAction.checkKasirIfAvailableShift] END <<<");
        return found;
    }

    private String getStHoursAndMinutes(){
        logger.info("[VerifikatorPembayaranAction.getStHoursAndMinutes] START >>>");

        DateTime dateTime = new DateTime(System.currentTimeMillis());
        String hours   = dateTime.getHourOfDay() + "";
        String minutes = dateTime.getMinuteOfHour() + "";

        if (hours.length() == 1)
            hours = "0" + hours;

        if (minutes.length() == 1)
            minutes = "0" + minutes;


        logger.info("[VerifikatorPembayaranAction.checkKasirIfAvailableShift] END >>>");
        return hours + ":" + minutes;
    }

    private String getShiftIdByNip(String nip, String branch){
        logger.info("[VerifikatorPembayaranAction.getShiftIdByNip] START >>>");

        String tanggal  = CommonUtil.convertDateToString2(new Date(System.currentTimeMillis()));
        String tipeRole = CommonConstant.TIPE_PELAYANAN_KASIR_TELE;
        String shiftId  = "";

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");

        try {
            shiftId = verifikatorPembayaranBo.getShifIdByNipAndTanggal(nip, tanggal, tipeRole, branch);
        } catch (GeneralBOException e){
            logger.error("[VerifikatorPembayaranAction.getShiftIdByNip] Error", e);
        }

        logger.info("[VerifikatorPembayaranAction.getShiftIdByNip] START >>>");
        return shiftId;
    }

    public List<Shift> getListShiftByTanggalShift(String stDate){
        logger.info("[VerifikatorPembayaranAction.getListShiftByTanggalShift] START >>>");

        String branchId = CommonUtil.userBranchLogin();
        List<Shift> shiftList = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        VerifikatorPembayaranBo verifikatorPembayaranBo = (VerifikatorPembayaranBo) ctx.getBean("verifikatorPembayaranBoProxy");

        try {
            shiftList = verifikatorPembayaranBo.getListShiftByIdTanggal(branchId, stDate, "");
        } catch (GeneralBOException e){
            logger.error("[VerifikatorPembayaranAction.getListShiftByTanggalShift] Error", e);
        }

        logger.info("[VerifikatorPembayaranAction.getListShiftByTanggalShift] END <<<");
        return shiftList;
    }

    public String print(){
        logger.info("[VerifikatorPembayaranAction.print] START >>>");

        AntrianTelemedic antrianTelemedic = getAntrianTelemedic();
        String stDate   = antrianTelemedic.getStDateFrom();
        String shiftId  = antrianTelemedic.getShiftId();
        String branchId = CommonUtil.userBranchLogin();
        String status   = antrianTelemedic.getStatusTransaksi();
        String currTime = new Timestamp(System.currentTimeMillis()).toString();
        String jenis    = antrianTelemedic.getIdJenisPeriksaPasien();

        String branchName = CommonUtil.userBranchNameLogin();
        String shiftName  = getShiftName(shiftId);

        List<AntrianTelemedic> antrianTelemedicList = new ArrayList<>();
        try {
            antrianTelemedicList = verifikatorPembayaranBoProxy.getListKasMasukByShift(shiftId, stDate, branchId, status, jenis);
        } catch (GeneralBOException e){
            logger.error("[VerifikatorPembayaranAction.print] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
        }

        CellDetail cellDetail;
        RowData rowData;
        List listOfData = new ArrayList();
        List listOfCell;
        List listOfColumn = new ArrayList();
        String titleReport = "Kasir Telemedicine " + branchName + " : " + shiftName +". Tanggal " + stDate ;

        listOfColumn.add("No. Trans");
        listOfColumn.add("Jenis Trans");
        listOfColumn.add("Keterangan");
        listOfColumn.add("Nama Bank");
        listOfColumn.add("No. RM");
        listOfColumn.add("Nama Pasien");
        listOfColumn.add("Nominal");
        listOfColumn.add("Last Update");
        listOfColumn.add("Last Update Who");

        for (AntrianTelemedic data : antrianTelemedicList){
            rowData = new RowData();
            listOfCell = new ArrayList();

            //no.trans
            cellDetail = new CellDetail();
            cellDetail.setCellID(0);
            cellDetail.setValueCell(data.getId());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //jenis Trans
            cellDetail = new CellDetail();
            cellDetail.setCellID(1);
            cellDetail.setValueCell(data.getIdJenisPeriksaPasien().toUpperCase());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Keterangan
            cellDetail = new CellDetail();
            cellDetail.setCellID(2);
            cellDetail.setValueCell(data.getKeterangan().toUpperCase());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Nama Bank
            cellDetail = new CellDetail();
            cellDetail.setCellID(3);
            cellDetail.setValueCell(data.getNamaBank());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //No. RM
            cellDetail = new CellDetail();
            cellDetail.setCellID(4);
            cellDetail.setValueCell(data.getIdPasien());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Nama Pasien
            cellDetail = new CellDetail();
            cellDetail.setCellID(5);
            cellDetail.setValueCell(data.getNamaPasien());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Nominal
            cellDetail = new CellDetail();
            cellDetail.setCellID(6);
            cellDetail.setValueCell(data.getNominal().toString());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_RIGHT);
            listOfCell.add(cellDetail);

            //Last Update
            cellDetail = new CellDetail();
            cellDetail.setCellID(7);
            cellDetail.setValueCell(data.getLastUpdate().toString());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            //Last Update Who
            cellDetail = new CellDetail();
            cellDetail.setCellID(8);
            cellDetail.setValueCell(data.getLastUpdateWho());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            rowData.setListOfCell(listOfCell);
            listOfData.add(rowData);
        }

        HSSFWorkbook wb = DownloadUtil.generateExcelOutput(titleReport, currTime, listOfColumn, listOfData, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            wb.write(baos);
        } catch (IOException e) {
            logger.error("[FunctionAction.downloadXls] Error when downloading data of telemedicine, Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "Found problem when downloding data, please inform to your admin.");
            return ERROR;
        }

        branchName = branchName.replaceAll(" ", "_").toLowerCase();
        shiftName = shiftName.replaceAll(" ", "_").toLowerCase();
        jenis = jenis.replaceAll(" ", "_").toLowerCase();

        setExcelStream(new ByteArrayInputStream(baos.toByteArray()));
        setContentDisposition("filename=\"LaporanTransTeleKasir_"+branchName+"_"+shiftName+"_"+jenis+".${documentFormat}\"");

        logger.info("[VerifikatorPembayaranAction.print] END <<<");
        return "downloadXls";
    }

    private String getShiftName(String shiftId){
        logger.info("[VerifikatorPembayaranAction.getShiftName] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ShiftBo shiftBo = (ShiftBo) ctx.getBean("shiftBoProxy");

        ImHrisShiftEntity shiftEntity = new ImHrisShiftEntity();
        try {
            shiftEntity = shiftBo.getById(shiftId);
        } catch (GeneralBOException e){
            logger.error("[VerifikatorPembayaranAction.getShiftName] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
        }

        logger.info("[VerifikatorPembayaranAction.getShiftName] END <<<");
        if (shiftEntity != null && shiftEntity.getShiftId() != null)
            return shiftEntity.getShiftName();
        return "";
    }

}
