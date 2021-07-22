package com.neurix.akuntansi.transaksi.kas.action;

import com.neurix.akuntansi.master.akunMataUang.bo.AkunMataUangBo;
import com.neurix.akuntansi.master.akunMataUang.model.AkunMataUang;
import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.mappingJurnal.bo.MappingJurnalBo;
import com.neurix.akuntansi.master.tipeJurnal.bo.TipeJurnalBo;
import com.neurix.akuntansi.master.trans.model.ImTransEntity;
import com.neurix.akuntansi.master.trans.model.Trans;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.billingSystem.model.MappingDetail;
import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalEntity;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.akuntansi.transaksi.kas.bo.KasBo;
import com.neurix.akuntansi.transaksi.kas.bo.KasDetailBo;
import com.neurix.akuntansi.transaksi.kas.model.EfakturDTO;
import com.neurix.akuntansi.transaksi.kas.model.Kas;
import com.neurix.akuntansi.transaksi.kas.model.KasDetail;
import com.neurix.akuntansi.transaksi.kas.model.Lampiran;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.bo.CompanyBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.company.model.Company;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class KasAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(KasAction.class);
    private KasBo kasBoProxy;
    private KasDetailBo kasDetailBoProxy;
    private BillingSystemBo billingSystemBoProxy;
    private MappingJurnalBo mappingJurnalBoProxy;
    private TipeJurnalBo tipeJurnalBoProxy;
    private KodeRekeningBo kodeRekeningBoProxy;
    private Kas kas;
    private AkunMataUangBo akunMataUangBoProxy;
    private List<AkunMataUang> listOfComboMataUang = new ArrayList<AkunMataUang>();
    private String kasId;


    public String getKasId() {
        return kasId;
    }

    public void setKasId(String kasId) {
        this.kasId = kasId;
    }

    public KasDetailBo getKasDetailBoProxy() {
        return kasDetailBoProxy;
    }

    public void setKasDetailBoProxy(KasDetailBo kasDetailBoProxy) {
        this.kasDetailBoProxy = kasDetailBoProxy;
    }

    public AkunMataUangBo getAkunMataUangBoProxy() {
        return akunMataUangBoProxy;
    }

    public List<AkunMataUang> getListOfComboMataUang() {
        return listOfComboMataUang;
    }

    public void setAkunMataUangBoProxy(AkunMataUangBo akunMataUangBoProxy) {
        this.akunMataUangBoProxy = akunMataUangBoProxy;
    }

    public void setListOfComboMataUang(List<AkunMataUang> listOfComboMataUang) {
        this.listOfComboMataUang = listOfComboMataUang;
    }

    private List<Kas> listOfComboKas = new ArrayList<Kas>();

    public KodeRekeningBo getKodeRekeningBoProxy() {
        return kodeRekeningBoProxy;
    }

    public void setKodeRekeningBoProxy(KodeRekeningBo kodeRekeningBoProxy) {
        this.kodeRekeningBoProxy = kodeRekeningBoProxy;
    }

    public MappingJurnalBo getMappingJurnalBoProxy() {
        return mappingJurnalBoProxy;
    }

    public void setMappingJurnalBoProxy(MappingJurnalBo mappingJurnalBoProxy) {
        this.mappingJurnalBoProxy = mappingJurnalBoProxy;
    }

    public TipeJurnalBo getTipeJurnalBoProxy() {
        return tipeJurnalBoProxy;
    }

    public void setTipeJurnalBoProxy(TipeJurnalBo tipeJurnalBoProxy) {
        this.tipeJurnalBoProxy = tipeJurnalBoProxy;
    }

    public BillingSystemBo getBillingSystemBoProxy() {
        return billingSystemBoProxy;
    }

    public void setBillingSystemBoProxy(BillingSystemBo billingSystemBoProxy) {
        this.billingSystemBoProxy = billingSystemBoProxy;
    }

    public List<Kas> getListOfComboKas() {
        return listOfComboKas;
    }

    public void setListOfComboKas(List<Kas> listOfComboKas) {
        this.listOfComboKas = listOfComboKas;
    }


    public KasBo getKasBoProxy() {
        return kasBoProxy;
    }

    public void setKasBoProxy(KasBo kasBoProxy) {
        this.kasBoProxy = kasBoProxy;
    }

    public Kas getKas() {
        return kas;
    }

    public void setKas(Kas kas) {
        this.kas = kas;
    }


    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        KasAction.logger = logger;
    }


    public String initComboKas() {
        logger.info("[KasAction.initComboKas] start process >>>");

        Kas search = new Kas();
        List<Kas> kasList;
        search.setFlag("Y");
        try {
            kasList = kasBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kasBoProxy.saveErrorMessage(e.getMessage(), "kasBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KasAction.initComboKas] Error when saving error,", e1);
            }
            logger.error("[KasAction.initComboKas] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }
        listOfComboKas.addAll(kasList);
        logger.info("[KasAction.initComboKas] end process <<<");
        return SUCCESS;
    }

    public Kas init(String kode, String flag) {
        logger.info("[KasAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Kas> listOfResult = (List<Kas>) session.getAttribute("listOfResult");

        if (kode != null && !"".equalsIgnoreCase(kode)) {
            if (listOfResult != null) {
                for (Kas kas : listOfResult) {
                    if (kode.equalsIgnoreCase(kas.getKasId()) && flag.equalsIgnoreCase(kas.getFlag())) {
                        setKas(kas);
                        break;
                    }
                }
            } else {
                setKas(new Kas());
            }

            logger.info("[KasAction.init] end process >>>");
        }
        return getKas();
    }

//    public Kas initEdit(String kode, String tipeKas) {
//        String kasId = getKasId();
//        List<KasDetail> kasDetails = null;
//        List<Lampiran> lampirans = null;
//        Kas editKas = null;
//        logger.info("[KasAction.initEdit] start process >>>");
//        try {
//            editKas = kasBoProxy.getKasById(kasId);
//            if (editKas != null) {
//                setKas(editKas);
//            }
//        } catch (Exception e) {
//            logger.error("[KasAction.editKasMasuk] Error when load data ,", e);
//        }
//
//        try {
//            kasDetails = kasBoProxy.getKasDetail(kasId);
//        } catch (Exception e) {
//            logger.error("[KasAction.editKasMasuk] Error when load data detail,", e);
//        }
//
//        try {
//            lampirans = kasBoProxy.getLampiranList(kasId);
//        } catch (Exception e) {
//            logger.error("[KasAction.editKasMasuk] Error when load data lampiran,", e);
//        }
//
//        setAddOrEdit(true);
//        setAdd(false);
//
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");
//        session.removeAttribute("listOfResultLampiran");
//        session.removeAttribute("listOfResultKasDetail");
//
//        session.setAttribute("listOfResultLampiran", lampirans);
//        session.setAttribute("listOfResultKasDetail", kasDetails);
//        return getKas();
//    }

    @Override
    public String add() {
        logger.info("[KasAction.add] start process >>>");
        Kas addKas = new Kas();
        String branchId = CommonUtil.userBranchLogin();
        if (branchId != null) {
            addKas.setBranchId(branchId);
        } else {
            addKas.setBranchId("");
        }
        setKas(addKas);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultLampiran");
        session.removeAttribute("listOfResultKasDetail");

        logger.info("[KasAction.add] stop process >>>");
        return "init_add";
    }

    public String addPemasukan() {
        logger.info("[KasAction.addPemasukan] start process >>>");
        Kas addKas = new Kas();
        String branchId = CommonUtil.userBranchLogin();
        if (branchId != null) {
            addKas.setBranchId(branchId);
        } else {
            addKas.setBranchId("");
        }
        setKas(addKas);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultLampiran");
        session.removeAttribute("listOfResultKasDetail");

        logger.info("[KasAction.addPemasukan] stop process >>>");
        return "init_add_pemasukan";
    }

    @Override
    public String edit() {
        String kasId = getKasId();
        List<KasDetail> kasDetails = null;
        List<Lampiran> lampirans = null;
        Kas editKas = null;

        try {
            editKas = kasBoProxy.getKasById(kasId);
            if (editKas != null) {
                setKas(editKas);
            }
        } catch (Exception e) {
            logger.error("[KasAction.editKasMasuk] Error when load data ,", e);
        }

        try {
            kasDetails = kasBoProxy.getKasDetail(kasId);
        } catch (Exception e) {
            logger.error("[KasAction.editKasMasuk] Error when load data detail,", e);
        }

        try {
            lampirans = kasBoProxy.getLampiranList(kasId);
        } catch (Exception e) {
            logger.error("[KasAction.editKasMasuk] Error when load data lampiran,", e);
        }

        setAddOrEdit(true);
        setAdd(false);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultLampiran");
        session.removeAttribute("listOfResultKasDetail");

        session.setAttribute("listOfResultLampiran", lampirans);
        session.setAttribute("listOfResultKasDetail", kasDetails);

        logger.info("[KasAction.editKasMasuk] stop process >>>");
        if (editKas.getTipeKas().equalsIgnoreCase("KM")) {
            return "init_edit_kas_masuk";
        }
        else {
            return "init_edit_kas_keluar";
        }
    }

    @Override
    public String delete() {
        logger.info("[KasAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Kas deleteKas = new Kas();

        if (itemFlag != null) {

            try {
                deleteKas = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = kasBoProxy.saveErrorMessage(e.getMessage(), "KasAction.init");
                } catch (GeneralBOException e1) {
                    logger.error("[KasAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[KasAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteKas != null) {
                setKas(deleteKas);

            } else {
                deleteKas.setKasId(itemId);
                deleteKas.setFlag(itemFlag);
                setKas(deleteKas);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteKas.setKasId(itemId);
            deleteKas.setFlag(itemFlag);
            setKas(deleteKas);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[KasAction.delete] end process <<<");
//        HttpSession session = ServletActionContext.getRequest().getSession();
        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[KasAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Kas viewKas = new Kas();

        if (itemFlag != null) {
            try {
                viewKas = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = kasBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[KasAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[KasAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (viewKas != null) {
                setKas(viewKas);

            } else {
                viewKas.setKasId(itemId);
                viewKas.setFlag(itemFlag);
                setKas(viewKas);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            viewKas.setKasId(itemId);
            viewKas.setFlag(itemFlag);
            setKas(viewKas);
            addActionError("Error, Unable to view data.");
            return "failure";
        }
        logger.info("[KasAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveDelete() {
        logger.info("[KasAction.saveDelete] start process >>>");
        try {
            Kas deleteKas = getKas();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteKas.setLastUpdate(updateTime);
            deleteKas.setLastUpdateWho(userLogin);
            deleteKas.setAction("U");
            deleteKas.setFlag("N");

            kasBoProxy.saveDelete(deleteKas);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kasBoProxy.saveErrorMessage(e.getMessage(), "KasBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[KasAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KasAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        logger.info("[KasAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd() {
        logger.info("[KasAction.saveAdd] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KasDetail> kasDetailList = (List<KasDetail>) session.getAttribute("listOfResultKasDetail");
        List<Lampiran> lampiranList = (List<Lampiran>) session.getAttribute("listOfResultLampiran");

        Kas kas = getKas();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        BigDecimal bayar = CommonUtil.StringDenganFormatToBigDecimal(kas.getStBayar());

        if ("pengajuan_biaya".equalsIgnoreCase(kas.getTipeMaster())) {
            try {
                //get parameter pembayaran
                String parameter = billingSystemBoProxy.getParameterPembayaran(kas.getTipeTransaksi());
                String rekeningIdBayar = kodeRekeningBoProxy.getRekeningIdByKodeRekening(kas.getCoaKas());

                //Jika pembayaran berhasil
                //Membuat Billing
                //List<Map> dataMap = new ArrayList<>();
                List<MappingDetail> dataMap = new ArrayList<>();
                String kasDetailId = "";
                String sumberDana = "";
                MappingDetail mapPph = new MappingDetail();
                MappingDetail mapPpn = new MappingDetail();

                for (KasDetail kasDetail : kasDetailList) {
                    String rekeningId = kodeRekeningBoProxy.getRekeningIdByKodeRekening(kasDetail.getRekeningId());
                    BigDecimal jumlahPembayaran = new BigDecimal(kasDetail.getStJumlahPembayaran().replace(".", ""));
                    BigDecimal ppn = new BigDecimal(kasDetail.getStPpn().replace(".", ""));
                    BigDecimal pph = new BigDecimal(kasDetail.getStPph().replace(".", ""));
                    jumlahPembayaran = jumlahPembayaran.add(pph);
                    jumlahPembayaran = jumlahPembayaran.subtract(ppn);
                    MappingDetail hs = new MappingDetail();
                    hs.setBukti(kasDetail.getNoNota());
                    hs.setNilai(jumlahPembayaran);
                    hs.setMasterId(kasDetail.getMasterId());
                    hs.setDivisiId(kasDetail.getDivisiId());
                    //hs.put("rekening_id", rekeningId);
                    hs.setCoa(kasDetail.getRekeningId());
                    dataMap.add(hs);
                    kasDetailId = kasDetail.getPengajuanBiayaDetailId();
                    sumberDana = kasDetail.getNoBugetting();

                    mapPph.setMasterId(kasDetail.getMasterId());
                    mapPph.setNilai(pph);

                    mapPpn.setMasterId(kasDetail.getMasterId());
                    mapPpn.setNilai(ppn);
                }

                MappingDetail kasMap = new MappingDetail();
                kasMap.setNilai(bayar);
                //kasMap.put("rekening_id", rekeningIdBayar);
                kasMap.setCoa(kas.getCoaKas());

                Map data = new HashMap();
                data.put(parameter, dataMap);
                data.put("pph", mapPph);
                data.put("ppn", mapPpn);
                data.put("metode_bayar", kasMap);
                data.put("currency_id", kas.getCurrencyId());

                if ("Y".equalsIgnoreCase(kas.getTipePengajuanBiaya())) {
                    List<ItJurnalEntity> jurnalEntityList = billingSystemBoProxy.getJurnalByPengajuanId(kasDetailId);
                    if (jurnalEntityList.size() != 0) {
                        throw new GeneralBOException("Pengajuan biaya dengan ID ini sudah di dilakukan pengeluaran kas");
                    }
                    data.put("pengajuan_id", kasDetailId);
                    data.put("sumber_dana", sumberDana);
                }

                kas.setBayar(bayar);
                kas.setTanggal(CommonUtil.convertStringToDate2(kas.getStTanggal()));
//                kas.setCurrencyId();
                kas.setCreatedWho(userLogin);
                kas.setLastUpdate(updateTime);
                kas.setCreatedDate(updateTime);
                kas.setLastUpdateWho(userLogin);
                kas.setAction("C");
                kas.setFlag("Y");

                //pembuatan Jurnal Kas
                String noJurnal = billingSystemBoProxy.createJurnal(kas.getTipeTransaksi(), data, kas.getBranchId(), kas.getKeterangan(), "N").getNoJurnal();
                kas.setNoJurnal(noJurnal);

                kasBoProxy.saveAddKas(kas, kasDetailList, lampiranList);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = kasBoProxy.saveErrorMessage(e.getMessage(), "KasAction.saveAdd");
                } catch (GeneralBOException e1) {
                    logger.error("[KasAction.saveAdd] Error when saving error,", e1);
                    throw new GeneralBOException(e1.getMessage());
                }
                logger.error("[KasAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                throw new GeneralBOException(e.getMessage());
            }
        }
        //Kas Bank Keluar/Masuk
        else {
            try {
                //get parameter pembayaran
                String parameter = billingSystemBoProxy.getParameterPembayaran(kas.getTipeTransaksi());
                String rekeningIdBayar = kodeRekeningBoProxy.getRekeningIdByKodeRekening(kas.getCoaKas());
                String pengajuanBiayaDetailId = "";
                //Jika pembayaran berhasil
                //Membuat Billing
                List<MappingDetail> dataMap = new ArrayList<>();
                for (KasDetail kasDetail : kasDetailList) {
                    String rekeningId = kodeRekeningBoProxy.getRekeningIdByKodeRekening(kasDetail.getRekeningId());
//                    String rekeningId = kasDetail.getRekeningId();
                    BigDecimal jumlahPembayaran = new BigDecimal(kasDetail.getStJumlahPembayaran().replace(".", ""));
                    pengajuanBiayaDetailId = kasDetail.getPengajuanBiayaDetailId();
                    MappingDetail hs = new MappingDetail();
                    hs.setBukti(kasDetail.getNoNota());
                    hs.setNilai(jumlahPembayaran);
                    hs.setMasterId(kasDetail.getMasterId());
                    hs.setDivisiId(kasDetail.getDivisiId());
                    hs.setCoa(kasDetail.getRekeningId());

                    dataMap.add(hs);
                }

                List<MappingDetail> listMapKas = new ArrayList<>();

                MappingDetail kasMap = new MappingDetail();
                kasMap.setNilai(bayar);
                kasMap.setCoa(kas.getCoaKas());
                listMapKas.add(kasMap);

                Map data = new HashMap();
                data.put(parameter, dataMap);
                data.put("metode_bayar", listMapKas);
                data.put("currency_id", kas.getCurrencyId());
                data.put("pengajuan_id", pengajuanBiayaDetailId); //sementara karena di BoImpl diparsing cara ini

                kas.setBayar(bayar);
                kas.setTanggal(CommonUtil.convertStringToDate2(kas.getStTanggal()));

                kas.setCreatedWho(userLogin);
                kas.setLastUpdate(updateTime);
                kas.setCreatedDate(updateTime);
                kas.setLastUpdateWho(userLogin);
                kas.setAction("C");
                kas.setFlag("Y");

                //pembuatan Jurnal Kas
                String noJurnal = billingSystemBoProxy.createJurnal(kas.getTipeTransaksi(), data, kas.getBranchId(), kas.getKeterangan(), "N").getNoJurnal();
                kas.setNoJurnal(noJurnal);

                kasBoProxy.saveAddKas(kas, kasDetailList, lampiranList);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = kasBoProxy.saveErrorMessage(e.getMessage(), "KasAction.saveAdd");
                } catch (GeneralBOException e1) {
                    logger.error("[KasAction.saveAdd] Error when saving error,", e1);
                    throw new GeneralBOException(e1.getMessage());
                }
                logger.error("[KasAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                throw new GeneralBOException(e.getMessage());
            }
        }

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKasDetail");

        logger.info("[KasAction.saveAdd] end process >>>");
        if ("KK".equalsIgnoreCase(kas.getTipeKas())) {
            return "success_save_add";
        } else {
            return "success_save_add_pemasukan";
        }
    }

    public String saveEditKas() {
        logger.info("[KasAction.saveAdd] start process >>>");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        HttpSession session = ServletActionContext.getRequest().getSession();
        Kas kas = getKas();
        List<KasDetail> kasDetailListBaru = new ArrayList<>();
        List<Lampiran> lampiranDeleted = new ArrayList<>();
        List<Lampiran> lampiranListBaru = new ArrayList<>();

        Map<String, Lampiran> lampiranMap = kasBoProxy.getListLampiran(kas.getKasId());

//        tempat simpan data awal
        Map<String, KasDetail> kasDetailMap = kasDetailBoProxy.getListKasDetail(kas.getKasId());
        List<KasDetail> kasDetailList = (List<KasDetail>) session.getAttribute("listOfResultKasDetail");
        List<Lampiran> lampiranList = (List<Lampiran>) session.getAttribute("listOfResultLampiran");

        lampiranDeleted.addAll(lampiranList);


    // Pengecekan list kas detail
        for (KasDetail dataBaru : kasDetailList) {
            KasDetail old = kasDetailMap.get(dataBaru.getKasDetailId());
            if(old == null){
                dataBaru.setKasId(kas.getKasId());
                dataBaru.setAction("C");
                dataBaru.setFlag("Y");
                dataBaru.setCreatedWho(userLogin);
                dataBaru.setCreatedDate(updateTime);

                kasDetailListBaru.add(dataBaru);
            }else {
                if (dataBaru.getKasDetailId().equalsIgnoreCase(old.getKasDetailId())) {
                    dataBaru.setKasId(kas.getKasId());
                    dataBaru.setAction("U");
                    dataBaru.setFlag("Y");
                    dataBaru.setLastUpdateWho(userLogin);
                    dataBaru.setLastUpdate(updateTime);

                    kasDetailListBaru.add(dataBaru);

                    kasDetailMap.remove(dataBaru.getKasDetailId());
                }
            }

        }
            //sisa data yg telah dihapus
            kasDetailMap.forEach((k, datalama) -> {
                KasDetail old = null;
                try {
                    old = kasDetailMap.get(datalama.getKasDetailId());
                    old.setStJumlahPembayaran(datalama.getStJumlahPembayaran());
                    old.setLastUpdateWho(userLogin);
                    old.setLastUpdate(updateTime);
                    old.setMasterId(datalama.getMasterId());
                    old.setPosisiCoa(datalama.getPosisiCoa());
                    old.setAction("D");
                    old.setRekeningId(datalama.getKodeRekening());
                    old.setFlag("N");
                    kasDetailListBaru.add(old);
                } catch (Exception e) {
                    logger.error("[KasAction.saveEditKasMasuk] Error when edit data, please inform to your admin.", e);
                }
            });

        //        Pengecekan list lampiran
        for (Lampiran lampiranBaru : lampiranList) {
            Lampiran old = lampiranMap.get(lampiranBaru.getLampiranId());
            if(old == null){
                lampiranBaru.setTransaksiId(kas.getKasId());
                lampiranBaru.setAction("C");
                lampiranBaru.setFlag("Y");
                lampiranBaru.setCreatedWho(userLogin);
                lampiranBaru.setCreatedDate(updateTime);

                lampiranListBaru.add(lampiranBaru);
            }else {
                if (lampiranBaru.getLampiranId().equalsIgnoreCase(old.getLampiranId())) {
                    lampiranBaru.setLampiranId(kas.getKasId());
                    lampiranBaru.setAction("U");
                    lampiranBaru.setFlag("Y");
                    lampiranBaru.setLastUpdateWho(userLogin);
                    lampiranBaru.setLastUpdate(updateTime);

                    lampiranListBaru.add(lampiranBaru);
                    lampiranMap.remove(lampiranBaru.getLampiranId());
                }
            }
        }
        //sisa data lampiran yg telah dihapus
        lampiranMap.forEach((k, datalama) -> {
            Lampiran old = null;
            try {
                old = lampiranMap.get(datalama.getLampiranId());
                old.setLastUpdateWho(userLogin);
                old.setLastUpdate(updateTime);
                old.setAction("D");
                old.setFlag("N");
                lampiranListBaru.add(old);
            } catch (Exception e) {
                logger.error("[KasAction.saveEditKasMasuk] Error when edit data, please inform to your admin.", e);
            }
        });


        BigDecimal bayar = CommonUtil.StringDenganFormatToBigDecimal(kas.getStBayar());

        if ("pengajuan_biaya".equalsIgnoreCase(kas.getTipeMaster())) {
            try {
                //get parameter pembayaran
                String parameter = billingSystemBoProxy.getParameterPembayaran(kas.getTipeTransaksi());
                String rekeningIdBayar = kodeRekeningBoProxy.getRekeningIdByKodeRekening(kas.getCoaKas());

                //Jika pembayaran berhasil
                //Membuat Billing
                List<Map> dataMap = new ArrayList<>();
                String kasDetailId = "";
                String sumberDana = "";
                Map mapPph = new HashMap();
                Map mapPpn = new HashMap();

                for (KasDetail kasDetail : kasDetailListBaru) {
                    String rekeningId = kodeRekeningBoProxy.getRekeningIdByKodeRekening(kasDetail.getRekeningId());
                    BigDecimal jumlahPembayaran = new BigDecimal(kasDetail.getStJumlahPembayaran().replace(".", ""));
                    BigDecimal ppn = new BigDecimal(kasDetail.getStPpn().replace(".", ""));
                    BigDecimal pph = new BigDecimal(kasDetail.getStPph().replace(".", ""));
                    jumlahPembayaran = jumlahPembayaran.add(pph);
                    jumlahPembayaran = jumlahPembayaran.subtract(ppn);
                    Map hs = new HashMap();
                    hs.put("bukti", kasDetail.getNoNota());
                    hs.put("nilai", jumlahPembayaran);
                    hs.put("master_id", kasDetail.getMasterId());
                    hs.put("divisi_id", kasDetail.getDivisiId());
                    hs.put("rekening_id", rekeningId);
                    dataMap.add(hs);
                    kasDetailId = kasDetail.getPengajuanBiayaDetailId();
                    sumberDana = kasDetail.getNoBugetting();

                    mapPph.put("master_id", kasDetail.getMasterId());
                    mapPph.put("nilai", pph);

                    mapPpn.put("master_id", kasDetail.getMasterId());
                    mapPpn.put("nilai", ppn);
                }

                Map kasMap = new HashMap();
                kasMap.put("nilai", bayar);
                kasMap.put("rekening_id", rekeningIdBayar);

                Map data = new HashMap();
                data.put(parameter, dataMap);
                data.put("pph", mapPph);
                data.put("ppn", mapPpn);
                data.put("metode_bayar", kasMap);
                data.put("currency_id", kas.getCurrencyId());

                if ("Y".equalsIgnoreCase(kas.getTipePengajuanBiaya())) {
                    List<ItJurnalEntity> jurnalEntityList = billingSystemBoProxy.getJurnalByPengajuanId(kasDetailId);
                    if (jurnalEntityList.size() != 0) {
                        throw new GeneralBOException("Pengajuan biaya dengan ID ini sudah di dilakukan pengeluaran kas");
                    }
                    data.put("pengajuan_id", kasDetailId);
                    data.put("sumber_dana", sumberDana);
                }

                kas.setBayar(bayar);
                kas.setTanggal(CommonUtil.convertStringToDate2(kas.getStTanggal()));

                kas.setCreatedWho(userLogin);
                kas.setLastUpdate(updateTime);
                kas.setCreatedDate(updateTime);
                kas.setLastUpdateWho(userLogin);
                kas.setAction("U");
                kas.setFlag("Y");

                //pembuatan Jurnal Kas
                String noJurnal = billingSystemBoProxy.createJurnal(kas.getTipeTransaksi(), data, kas.getBranchId(), kas.getKeterangan(), "N").getNoJurnal();
                kas.setNoJurnal(noJurnal);

                kasBoProxy.saveEditKas(kas, kasDetailListBaru, lampiranListBaru);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = kasBoProxy.saveErrorMessage(e.getMessage(), "KasAction.saveAdd");
                } catch (GeneralBOException e1) {
                    logger.error("[KasAction.saveAdd] Error when saving error,", e1);
                    throw new GeneralBOException(e1.getMessage());
                }
                logger.error("[KasAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                throw new GeneralBOException(e.getMessage());
            }
        }
        //Jurnal Keluar/Masuk
        else {
            try {
                //get parameter pembayaran
                String parameter = billingSystemBoProxy.getParameterPembayaran(kas.getTipeTransaksi());
                String rekeningIdBayar = kodeRekeningBoProxy.getRekeningIdByKodeRekening(kas.getCoaKas());
                String pengajuanBiayaDetailId = "";
                //Jika pembayaran berhasil
                //Membuat Billing
                List<Map> dataMap = new ArrayList<>();
                for (KasDetail kasDetail : kasDetailList) {
                    String rekeningId = kodeRekeningBoProxy.getRekeningIdByKodeRekening(kasDetail.getRekeningId());
//                    String rekeningId = kasDetail.getRekeningId();
                    BigDecimal jumlahPembayaran = new BigDecimal(kasDetail.getStJumlahPembayaran().replace(".", ""));
                    pengajuanBiayaDetailId = kasDetail.getKasId();
                    Map hs = new HashMap();
                    hs.put("bukti", kasDetail.getNoNota());
                    hs.put("nilai", jumlahPembayaran);
                    hs.put("master_id", kasDetail.getMasterId());
                    hs.put("divisi_id", kasDetail.getDivisiId());
                    hs.put("rekening_id", rekeningId);

                    dataMap.add(hs);
                }

                Map kasMap = new HashMap();
                kasMap.put("nilai", bayar);
                kasMap.put("rekening_id", rekeningIdBayar);

                Map data = new HashMap();
                data.put(parameter, dataMap);
                data.put("metode_bayar", kasMap);
                data.put("currency_id", kas.getCurrencyId());
                data.put("pengajuan_id", pengajuanBiayaDetailId); //sementara karena di BoImpl diparsing cara ini

                kas.setBayar(bayar);
                kas.setTanggal(CommonUtil.convertStringToDate2(kas.getStTanggal()));

                kas.setCreatedWho(userLogin);
                kas.setLastUpdate(updateTime);
                kas.setCreatedDate(updateTime);
                kas.setLastUpdateWho(userLogin);
                kas.setAction("U");
                kas.setFlag("Y");

                //pembuatan Jurnal Kas
                String noJurnal = billingSystemBoProxy.createJurnal(kas.getTipeTransaksi(), data, kas.getBranchId(), kas.getKeterangan(), "N").getNoJurnal();
                kas.setNoJurnal(noJurnal);

                kasBoProxy.saveEditKas(kas, kasDetailListBaru, lampiranListBaru);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = kasBoProxy.saveErrorMessage(e.getMessage(), "KasAction.saveAdd");
                } catch (GeneralBOException e1) {
                    logger.error("[KasAction.saveAdd] Error when saving error,", e1);
                    throw new GeneralBOException(e1.getMessage());
                }
                logger.error("[KasAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                throw new GeneralBOException(e.getMessage());
            }
        }

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKasDetail");

        logger.info("[KasAction.saveAdd] end process >>>");
        if ("KK".equalsIgnoreCase(kas.getTipeKas())) {
            return "success_save_add";
        } else {
            return "success_save_add_pemasukan";
        }
    }

    public String saveAddKoreksiPengajuan(String pengajuanId, String kodeRekeningLawan, String metodeBayar, String stJumlahPembayaran,
                                          String stNetto, String stPpn, String stPph, String kodeVendor, String stUangMuka, String nip,
                                          String buktiUangMuka, String noFakturPajak, String uploadFakturPajak, String keterangan, String branchId,
                                          String divisiId, String sumberDana) {
        logger.info("[KasAction.saveAddKoreksiPengajuan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String noJurnal = "";
        List<KasDetail> kasDetailList = (List<KasDetail>) session.getAttribute("listOfResultKasDetail");
        List<Lampiran> lampiranList = (List<Lampiran>) session.getAttribute("listOfResultLampiran");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KasBo kasBo = (KasBo) ctx.getBean("kasBoProxy");
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");

        //convert ke bigdecimal
        BigDecimal netto = new BigDecimal(stNetto.replace(".", ""));
        BigDecimal ppn = BigDecimal.ZERO;
        BigDecimal pph = BigDecimal.ZERO;
        BigDecimal nilaiUangMuka = new BigDecimal(stUangMuka.replace(".", ""));
        BigDecimal jumlahPembayaran = new BigDecimal(stJumlahPembayaran.replace(".", ""));

        if (!"".equalsIgnoreCase(stPpn)) {
            ppn = new BigDecimal(stPpn.replace(".", ""));
        }

        if (!"".equalsIgnoreCase(stPph)) {
            pph = new BigDecimal(stPph.replace(".", ""));
        }

        String tipeTransaksi = "";///////>>>>>>>>>>>>>>>>>>>
        if (nilaiUangMuka.add(pph).compareTo(jumlahPembayaran.add(ppn)) < 0) {
            tipeTransaksi = CommonConstant.TRANSAKSI_ID_PEMBAYARAN_PENGAJUAN_BIAYA_UM_KURANG; // TIPE TRANSAKSI UANG MUKA KURANG
        } else {
            tipeTransaksi = CommonConstant.TRANSAKSI_ID_PEMBAYARAN_PENGAJUAN_BIAYA_UM_LEBIH; // TIPE TRANSAKSI UANG MUKA LEBIH ATAU PAS
        }

        Kas kasKoreksi = new Kas();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        kasKoreksi.setTipeTransaksi(tipeTransaksi);
        kasKoreksi.setBayar(jumlahPembayaran);
        kasKoreksi.setCoaKas(metodeBayar);
        kasKoreksi.setNoSlipBank("");
        kasKoreksi.setBranchId(branchId);
        kasKoreksi.setKeterangan(keterangan);
        kasKoreksi.setTipeKas("KR");
        kasKoreksi.setTanggal(new Date(new java.util.Date().getTime()));

        kasKoreksi.setCreatedWho(userLogin);
        kasKoreksi.setLastUpdate(updateTime);
        kasKoreksi.setCreatedDate(updateTime);
        kasKoreksi.setLastUpdateWho(userLogin);
        kasKoreksi.setAction("C");
        kasKoreksi.setFlag("Y");

        try {
            //get parameter pembayaran
            Map dataPostingJurnal = new HashMap();
            Map kas = new HashMap();
            String rekeningIdBayar = kodeRekeningBo.getRekeningIdByKodeRekening(metodeBayar);
            kas.put("rekening_id", rekeningIdBayar);
            kas.put("nilai", netto);

            Map mapPpn = new HashMap();
            mapPpn.put("master_id", kodeVendor);
            mapPpn.put("nilai", ppn);

            Map mapPph = new HashMap();
            mapPph.put("master_id", kodeVendor);
            mapPph.put("nilai", pph);

            Map uangMuka = new HashMap();
            uangMuka.put("master_id", nip);
            uangMuka.put("bukti", buktiUangMuka);
            uangMuka.put("nilai", nilaiUangMuka);

            Map bebanPokok = new HashMap();
            String rekeningId = kodeRekeningBo.getRekeningIdByKodeRekening(kodeRekeningLawan);
            bebanPokok.put("nilai", jumlahPembayaran);
            bebanPokok.put("rekening_id", rekeningId);
            bebanPokok.put("divisi_id", divisiId);

            dataPostingJurnal.put("metode_bayar", kas);
            dataPostingJurnal.put("ppn_masukan", mapPpn);
            dataPostingJurnal.put("pph", mapPph);
            dataPostingJurnal.put("beban", bebanPokok);
            dataPostingJurnal.put("uang_muka", uangMuka);
            dataPostingJurnal.put("pengajuan_id", pengajuanId);
            dataPostingJurnal.put("sumber_dana", sumberDana);

            //Pembuatan Jurnal
            Jurnal jurnal = billingSystemBo.createJurnal(kasKoreksi.getTipeTransaksi(), dataPostingJurnal, branchId, keterangan, "N");
            noJurnal = jurnal.getNoJurnal();
            kasKoreksi.setNoJurnal(noJurnal);
            kasBo.saveAddKas(kasKoreksi, kasDetailList, lampiranList);
        } catch (GeneralBOException e) {
            Long logId;
            try {
                logId = kasBo.saveErrorMessage(e.getMessage(), "KasAction.saveAddKoreksiPengajuan");
            } catch (GeneralBOException e1) {
                logger.error("[KasAction.saveAddKoreksiPengajuan] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[KasAction.saveAddKoreksiPengajuan] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[KasAction.saveAddKoreksiPengajuan] end process >>>");
        return "success menambahkan koreksi pengajuan";
    }

    @Override
    public String search() {
        logger.info("[KasAction.search] start process >>>");
        Kas searchKas = getKas();
        List<Kas> listOfsearchKas;
        try {
            listOfsearchKas = kasBoProxy.getByCriteria(searchKas);
        } catch (GeneralBOException e) {
            Long logId;
            try {
                logId = kasBoProxy.saveErrorMessage(e.getMessage(), "KasBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KasAction.search] Error when saving error,", e1);
                return "input_pemasukan";
            }
            logger.error("[KasAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "input_pemasukan";
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");
        session.removeAttribute("listKasDetailModal");
        session.setAttribute("listOfResult", listOfsearchKas);

        searchKas.setBranchIdUser(CommonUtil.userBranchLogin());

        setKas(searchKas);
        logger.info("[KasAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchPemasukan() {
        logger.info("[KasAction.searchPemasukan] start process >>>");
        Kas searchKas = getKas();
        List<Kas> listOfsearchKas = new ArrayList();
        try {
            listOfsearchKas = kasBoProxy.getByCriteria(searchKas); //ubah aji
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kasBoProxy.saveErrorMessage(e.getMessage(), "KasAction.searchPemasukan");
            } catch (GeneralBOException e1) {
                logger.error("[KasAction.searchPemasukan] Error when saving error,", e1);
                return "input_pemasukan";
            }
            logger.error("[KasAction.searchPemasukan] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "input_pemasukan";
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");
        session.removeAttribute("listKasDetailModal");
        session.setAttribute("listOfResult", listOfsearchKas);

        searchKas.setBranchIdUser(CommonUtil.userBranchLogin());

        setKas(searchKas);
        logger.info("[KasAction.searchPemasukan] end process <<<");

        return "success_pemasukan";
    }

    public String searchKoreksiPengajuan() {
        logger.info("[KasAction.searchKoreksiPengajuan] start process >>>");
        Kas searchKas = getKas();
//        searchKas.setTipeTransaksi(CommonConstant.TRANSAKSI_ID_KOREKSI_PENGAJUAN_BIAYA);
        searchKas.setTipeKas("KR");
        List<Kas> listOfsearchKas = new ArrayList();
        try {
            listOfsearchKas = kasBoProxy.getByCriteria(searchKas);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kasBoProxy.saveErrorMessage(e.getMessage(), "KasBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KasAction.searchKoreksiPengajuan] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KasAction.searchKoreksiPengajuan] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKodeRekening");
        session.removeAttribute("listKasDetailModal");
        session.setAttribute("listOfResult", listOfsearchKas);

        searchKas.setBranchIdUser(CommonUtil.userBranchLogin());

        setKas(searchKas);
        logger.info("[KasAction.searchKoreksiPengajuan] end process <<<");

        return "success_koreksi_pengajuan";
    }

    @Override
    public String initForm() {
        logger.info("[KasAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        Kas data = new Kas();
        if (branchId != null) {
            data.setBranchId(branchId);
            data.setBranchIdUser(branchId);
        } else {
            data.setBranchId("");
        }
        setKas(data);
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKasDetail");
        logger.info("[KasAction.initForm] end process >>>");
        return INPUT;
    }

    public String initFormPemasukan() {
        logger.info("[KasAction.initFormPemasukan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        Kas data = new Kas();
        if (branchId != null) {
            data.setBranchId(branchId);
            data.setBranchIdUser(branchId);
        } else {
            data.setBranchId("");
        }
        setKas(data);
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKasDetail");
        logger.info("[KasAction.initFormPemasukan] end process >>>");
        return "input_pemasukan";
    }

    //RAKA-26APR2021 ==> menambahkan initFormKoreksi (kenapa tidak ada method ini padahal di xml & jsp ada (input_koreksi))
    public String initFormKoreksi() {
        logger.info("[KasAction.initFormKoreksi] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        Kas data = new Kas();
        if (branchId != null) {
            data.setBranchId(branchId);
            data.setBranchIdUser(branchId);
        } else {
            data.setBranchId("");
        }
        setKas(data);
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKasDetail");
        logger.info("[KasAction.initFormKoreksi] end process >>>");
        return "input_koreksi";
    }

    public String initFormKoreksiPengajuan() {
        logger.info("[KasAction.initFormKoreksiPengajuan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        Kas data = new Kas();
        if (branchId != null) {
            data.setBranchId(branchId);
            data.setBranchIdUser(branchId);
        } else {
            data.setBranchId("");
        }
        setKas(data);
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultKasDetail");
        logger.info("[KasAction.initFormKoreksiPengajuan] end process >>>");
        return "input_koreksi_pengajuan";
    }



    private String getNamaRekeningByCoa(String coa){
        logger.info("[KasAction.getNamaRekeningByCoa] start process <<<");
        String result = null;
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
            ImKodeRekeningEntity rekeningEntity = kodeRekeningBo.getKodeRekeningByCoa(coa);
            if (rekeningEntity != null) {
                result = rekeningEntity.getNamaKodeRekening();
            }
        }
        catch (Exception e){
            logger.error("[KasAction.getNamaRekeningByCoa] Error when saving error,", e);
            throw new GeneralBOException("Error saat mengambil data approval ");
        }
        logger.info("[KasAction.getNamaRekeningByCoa] end process >>>");
        return result;
    }

    //saveDetailPembayaran to session,
    public String saveKasDetail(String kodeVendor, String namaVendor, String noNota, String jumlahPembayaran,
                                String rekeningId, String divisiId, String divisiName, String currency, String tipePengajuanBiaya,
                                String pengajuanBiayaDetailId, String noBudgetting, String pph, String ppn, String noFakturPajak,
                                String fileUpload) {
        logger.info("[KasAction.saveKasDetail] start process >>>");
        String status = "";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KasDetail> kasDetailList = (List<KasDetail>) session.getAttribute("listOfResultKasDetail");
        List<KasDetail> piutangDetailArrayList = new ArrayList<>();
        boolean ada = false;
        if (kasDetailList == null) {
            KasDetail newData = new KasDetail();
            newData.setStJumlahPembayaran(jumlahPembayaran);
            newData.setMasterId(kodeVendor);
            newData.setNoNota(noNota);
            newData.setCurrency(currency);
            newData.setMasterName(namaVendor);

            newData.setRekeningId(rekeningId);
            if(!rekeningId.isEmpty()){
                String namaRekening = getNamaRekeningByCoa(rekeningId);
                newData.setNamaRekening(namaRekening == null ? "" : namaRekening);
            }
            newData.setDivisiId(divisiId);
            newData.setDivisiName(divisiName);
            newData.setPengajuanBiayaDetailId(pengajuanBiayaDetailId);
            newData.setNoBugetting(noBudgetting);
            newData.setStPpn(ppn);
            newData.setStPph(pph);
            newData.setNoFakturPajak(noFakturPajak);
            newData.setStFileUpload(fileUpload);
            piutangDetailArrayList.add(newData);
            session.setAttribute("listOfResultKasDetail", piutangDetailArrayList);
        } else {
            piutangDetailArrayList.addAll(kasDetailList);
            for (KasDetail kasDetail : kasDetailList) {
                if (kasDetail.getMasterId().equalsIgnoreCase(kodeVendor) && kasDetail.getNoNota().equalsIgnoreCase(noNota) && kasDetail.getRekeningId().equalsIgnoreCase(rekeningId)) {
                    ada = true;
                    status = "Kas Keluar/Kas Masuk dengan data ini ( kode rekening,vendor , no nota ) sudah ada.";
                    break;
                }
            }
            if (!ada) {
                KasDetail newData = new KasDetail();
                newData.setStJumlahPembayaran(jumlahPembayaran);
                newData.setMasterId(kodeVendor);
                newData.setNoNota(noNota);
                newData.setMasterName(namaVendor);
                newData.setRekeningId(rekeningId);
                if(!rekeningId.isEmpty()){
                    String namaRekening = getNamaRekeningByCoa(rekeningId);
                    newData.setNamaRekening(namaRekening == null ? "" : namaRekening);
                }
                newData.setDivisiId(divisiId);
                newData.setDivisiName(divisiName);
                newData.setPengajuanBiayaDetailId(noNota);
                newData.setNoBugetting(noBudgetting);
                newData.setStPpn(ppn);
                newData.setStPph(pph);
                newData.setNoFakturPajak(noFakturPajak);
                newData.setStFileUpload(fileUpload);

                piutangDetailArrayList.add(newData);
                session.setAttribute("listOfResultKasDetail", piutangDetailArrayList);
            }
        }
        logger.info("[KasAction.saveKasDetail] end process >>>");
        return status;
    }

    public void deleteDetailPembayaran(String rekeningId, String divisiId, String vendor, String noNota, String biaya) {
        logger.info("[KasAction.deleteDetailPembayaran] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KasDetail> piutangDetailList = (List<KasDetail>) session.getAttribute("listOfResultKasDetail");
        List<KasDetail> piutangDetailArrayList = new ArrayList<>();
        for (KasDetail kasDetail : piutangDetailList) {
            if (kasDetail.getNoNota().equalsIgnoreCase(noNota) && kasDetail.getRekeningId().equalsIgnoreCase(rekeningId) &&
                    kasDetail.getMasterId().equalsIgnoreCase(vendor) && kasDetail.getStJumlahPembayaran().equalsIgnoreCase(biaya) && kasDetail.getDivisiId().equalsIgnoreCase(divisiId)) {
            } else {
                piutangDetailArrayList.add(kasDetail);
            }
        }
        session.setAttribute("listOfResultKasDetail", piutangDetailArrayList);
        logger.info("[KasAction.deleteDetailPembayaran] end process >>>");
    }

    public List<KasDetail> searchDetailPembayaran() {
        logger.info("[KasAction.searchDetailPembayaran] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KasDetail> kasDetailList = (List<KasDetail>) session.getAttribute("listOfResultKasDetail");
        logger.info("[KasAction.searchDetailPembayaran] end process >>>");
        return kasDetailList;
    }

    public String cekBeforeSave(String currencyId, String tipeTransaksi, String tanggal, String metodeBayar, String bayar, String keterangan, String noslipBank, String branchId) {
        String status = "";
        logger.info("[KasAction.cekBeforeSave] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KasDetail> kasDetailList = (List<KasDetail>) session.getAttribute("listOfResultKasDetail");

        if (currencyId.equalsIgnoreCase("") || tipeTransaksi.equalsIgnoreCase("") || tanggal.equalsIgnoreCase("") || metodeBayar.equalsIgnoreCase("") || bayar.equalsIgnoreCase("") || branchId.equalsIgnoreCase("")) {
            if (currencyId.equalsIgnoreCase("")) {
                status += "Tipe Currency masih kosong <br>";
            }
            if (tipeTransaksi.equalsIgnoreCase("")) {
                status += "Tipe transaksi masih kosong <br>";
            }
            if (tanggal.equalsIgnoreCase("")) {
                status += "Tanggal masih kosong <br>";
            }
            if (metodeBayar.equalsIgnoreCase("")) {
                status += "Kode rekening kas masih kosong <br>";
            }
            if (bayar.equalsIgnoreCase("")) {
                status += "Jumlah pembayaran masih kosong <br>";
            }
            if (branchId.equalsIgnoreCase("")) {
                status += "Unit masih kosong <br>";
            }
            /*if (keterangan.equalsIgnoreCase("")){
                status+="Keterangan masih kosong <br>";
            }
            if (noslipBank.equalsIgnoreCase("")){
                status+="No. Slip masih kosong <br>";
            }*/
        } else if (kasDetailList == null || kasDetailList.size() == 0) {
            status += "Detail pembayaran belum ada <br>";
        } else {
            BigDecimal totalBayar = BigDecimal.valueOf(Double.valueOf(bayar.replace(".", "").replace(",", "")));
            BigDecimal bayarDetail = BigDecimal.ZERO;
            for (KasDetail data : kasDetailList) {
                bayarDetail = bayarDetail.add(new BigDecimal(data.getStJumlahPembayaran().replace(".", "").replace(",", "")));
            }
            if (!totalBayar.equals(bayarDetail)) {
                if (totalBayar.compareTo(bayarDetail) > 0) {
                    status = "Jumlah pembayaran masih kurang dari total bayar";
                } else if (totalBayar.compareTo(bayarDetail) < 0) {
                    status = "Jumlah pembayaran melebihi total bayar";
                }
            }
        }
        logger.info("[KasAction.cekBeforeSave] end process >>>");
        return status;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<KasDetail> searchNotaPembayaran(String masterId, String transaksiId, String branchId, String divisiId, String coa) {
        logger.info("[KasAction.searchNotaPembayaran] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KasBo kasBo = (KasBo) ctx.getBean("kasBoProxy");
        HttpSession session = ServletActionContext.getRequest().getSession();

        List<KasDetail> listDataDb = kasBo.getSearchNotaKas(masterId, transaksiId, branchId, divisiId, coa);
        List<KasDetail> listDataSession = (List<KasDetail>) session.getAttribute("listOfResultKasDetail");
        List<KasDetail> result = new ArrayList<>();
        if (listDataSession == null) {
            result.addAll(listDataDb);
        } else {
            //jika sudah ada pada session tidak usah ditampilkan
            for (KasDetail dataDb : listDataDb) {
                boolean ada = false;
                for (KasDetail dataSession : listDataSession) {
                    if (dataDb.getNoNota().equalsIgnoreCase(dataSession.getNoNota())) {
                        ada = true;
                        break;
                    }
                }
                if (!ada) {
                    result.add(dataDb);
                }
            }
        }
        logger.info("[KasAction.searchNotaPembayaran] end process >>>");
        return result;
    }

    public List<KasDetail> searchPengajuanBiaya(String branchId) {
        logger.info("[KasAction.searchPengajuanBiaya] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KasBo kasBo = (KasBo) ctx.getBean("kasBoProxy");
        HttpSession session = ServletActionContext.getRequest().getSession();

        List<KasDetail> listDataDb = kasBo.searchPengajuanBiaya(branchId);
        List<KasDetail> listDataSession = (List<KasDetail>) session.getAttribute("listOfResultKasDetail");

//        tes aji
//        Map<String, KasDetail> mapDataSession  = listDataSession.stream().collect(Collectors.toMap(KasDetail::getNoNota, dataSession -> dataSession));

        List<KasDetail> result = new ArrayList<>();
        if (listDataSession == null) {
            result.addAll(listDataDb);
        } else {
            //jika sudah ada pada session tidak usah ditampilkan
            for (KasDetail dataDb : listDataDb) {
                boolean ada = false;
                for (KasDetail dataSession : listDataSession) {
                    if (dataDb.getNoNota().equalsIgnoreCase(dataSession.getNoNota())) {
                        ada = true;
                        break;
                    }
                }
                if (!ada) {
                    result.add(dataDb);
                }
            }
        }
        logger.info("[KasAction.searchPengajuanBiaya] end process >>>");
        return result;
    }

    public String getTipeMaster(String transaksiId) {
        logger.info("[KasAction.getTipeMaster] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KasBo kasBo = (KasBo) ctx.getBean("kasBoProxy");
        String master = "";

        ImTransEntity transEntity = kasBo.getTipeMaster(transaksiId);
        master = transEntity != null ? transEntity.getMaster() : master;
        logger.info("[KasAction.getTipeMaster] end process >>>");
        return master;
    }

    public String postingJurnal(String kasId) {
        logger.info("[KasAction.postingJurnal] start process >>>");
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            KasBo kasBo = (KasBo) ctx.getBean("kasBoProxy");
            Kas data = new Kas();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            data.setKasId(kasId);
            data.setRegisteredDate(updateTime);
            data.setLastUpdateWho(userLogin);
            data.setLastUpdate(updateTime);
            data.setAction("U");
            data.setFlag("Y");
            kasBo.postingJurnal(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kasBoProxy.saveErrorMessage(e.getMessage(), "PembayaranUtangPiutangBO.postingJurnal");
            } catch (GeneralBOException e1) {
                logger.error("[KasAction.postingJurnal] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KasAction.postingJurnal] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[KasAction.postingJurnal] end process <<<");

        return "Sukses Posting Jurnal";
    }

    public String printReportBuktiPosting() {
        logger.info("[KasAction.printReportBuktiPosting] start process >>>");
        List<Kas> kasList;
        String tipeTransaksi = "";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KasBo kasBo = (KasBo) ctx.getBean("kasBoProxy");
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");

        Kas data = getKas();
        Branch branch = branchBo.getBranchById(data.getBranchId(), "Y");
        Kas search = new Kas();
        search.setKasId(data.getKasId());
        search.setFlag("Y");
        kasList = kasBo.getByCriteria(search);

        //menambah printCount
        kasBo.addPrintCount(data.getNoJurnal());
        String kodeRekeningKasJurnal = kasBo.getNamaRekeningKasJurnal(data.getNoJurnal());

        for (Kas result : kasList) {
            reportParams.put("terbilang", CommonUtil.angkaToTerbilang(result.getBayar().longValue()));
            reportParams.put("uraian", result.getKeterangan());
            reportParams.put("totalBayar", CommonUtil.numbericFormat(result.getBayar(), "###,###.00"));
            reportParams.put("noSlipBank", result.getNoSlipBank());
            reportParams.put("coaKas", kodeRekeningKasJurnal);
            tipeTransaksi = result.getTipeKas();
        }
        String reportName = "";
        switch (tipeTransaksi) {
            case ("KK"):
                reportName = "BUKTI KAS KELUAR";
                break;
            case ("KM"):
                reportName = "BUKTI KAS MASUK";
                break;
            case ("KR"):
                reportName = "BUKTI KOREKSI";
                break;
            case ("UM"):
                reportName = "BUKTI UANG MUKA";
                break;
        }

        reportParams.put("reportTitle", reportName);
        reportParams.put("reportName", reportName);
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT + branch.getLogoName());
        reportParams.put("branchId", data.getBranchId());
        java.util.Date now = new java.util.Date();
        reportParams.put("tanggal", CommonUtil.convertDateToString(now));
        reportParams.put("kota", branch.getBranchName());
        reportParams.put("alamatSurat", branch.getAlamatSurat());
        reportParams.put("noJurnal", data.getNoJurnal());
        reportParams.put("kasId", data.getKasId());
        reportParams.put("urlLogo", CommonConstant.URL_LOGO_REPORT + branch.getLogoName());
        reportParams.put("areaId", CommonUtil.userAreaName());
        reportParams.put("subreport_dir", CommonUtil.getUploadFolderValue() + CommonConstant.PATH_REPORT_AKUNTANSI);
        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = kasBo.saveErrorMessage(e.getMessage(), "printReportBuktiPosting");
            } catch (GeneralBOException e1) {
                logger.error("[KasAction.printReportBuktiPosting] Error when downloading ,", e1);
            }
            logger.error("[KasAction.printReportBuktiPosting] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
        }
        logger.info("[KasAction.printReportBuktiPosting] end process <<<");
        if ("KR".equalsIgnoreCase(tipeTransaksi)) {
            return "print_report_bukti_posting_koreksi";
        } else if ("KM".equalsIgnoreCase(tipeTransaksi)) {
            return "print_report_bukti_posting_masuk";
        } else {
            return "print_report_bukti_posting";
        }
    }

    public Kas getForModalPopUp(String kasId) {
        logger.info("[KasAction.getForModalPopUp] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KasBo kasBo = (KasBo) ctx.getBean("kasBoProxy");
        String itemId = kasId;
        String itemFlag = "Y";
        Kas modalPopUp = new Kas();
        List<KasDetail> listDetail;
        List<Lampiran> lampiranList;
        HttpSession session = ServletActionContext.getRequest().getSession();
        try {
            modalPopUp = init(itemId, itemFlag);
            if (modalPopUp != null) {
                listDetail = kasBo.getKasDetail(kasId);
                lampiranList = kasBo.getLampiranList(kasId);
                session.setAttribute("listKasDetailModal", listDetail);
                session.setAttribute("listOfResultLampiran", lampiranList);
            }
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kasBoProxy.saveErrorMessage(e.getMessage(), "kasBO.getForModalPopUp");
            } catch (GeneralBOException e1) {
                logger.error("[KasAction.getForModalPopUp] Error when retrieving delete data,", e1);
            }
            logger.error("[KasAction.getForModalPopUp] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
        }
        logger.info("[KasAction.getForModalPopUp] end process >>>");
        return modalPopUp;
    }

    public List<KasDetail> searchPembayaranDetail() {
        logger.info("[KasAction.searchPembayaranDetail] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KasDetail> result = (List<KasDetail>) session.getAttribute("listKasDetailModal");
        logger.info("[KasAction.searchPembayaranDetail] end process >>>");
        return result;
    }

    public Trans getDisableTrans(String transaksiId, String coaLawan) {
        logger.info("[KasAction.getDisableTrans] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KasBo kasBo = (KasBo) ctx.getBean("kasBoProxy");
        Trans data = kasBo.getDisableTrans(transaksiId, coaLawan);
        logger.info("[KasAction.getDisableTrans] end process >>>");
        return data;
    }

    public EfakturDTO generateQrEfaktur(String url) {
        EfakturDTO efakturDTO = null;
        try {
            System.out.println(url);
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            if (responseCode == 200) {
            }
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print in String
            System.out.println(response.toString());
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(response.toString())));
            NodeList errNodes = doc.getElementsByTagName("resValidateFakturPm");
            efakturDTO = new EfakturDTO();
            if (errNodes.getLength() > 0) {
                Element err = (Element) errNodes.item(0);
                efakturDTO.setKdJenisTransaksi(err.getElementsByTagName("kdJenisTransaksi").item(0).getTextContent());
                efakturDTO.setFgPengganti(err.getElementsByTagName("fgPengganti").item(0).getTextContent());
                efakturDTO.setNomorFaktur(err.getElementsByTagName("nomorFaktur").item(0).getTextContent());
                efakturDTO.setTanggalFaktur(err.getElementsByTagName("tanggalFaktur").item(0).getTextContent());
                efakturDTO.setNpwpPenjual(err.getElementsByTagName("npwpPenjual").item(0).getTextContent());
                efakturDTO.setNamaPenjual(err.getElementsByTagName("namaPenjual").item(0).getTextContent());
                efakturDTO.setAlamatPenjual(err.getElementsByTagName("alamatPenjual").item(0).getTextContent());
                efakturDTO.setNpwpLawanTransaksi(err.getElementsByTagName("npwpLawanTransaksi").item(0).getTextContent());
                efakturDTO.setNamaLawanTransaksi(err.getElementsByTagName("namaLawanTransaksi").item(0).getTextContent());
                efakturDTO.setAlamatLawanTransaksi(err.getElementsByTagName("alamatLawanTransaksi").item(0).getTextContent());
                efakturDTO.setJumlahDpp(err.getElementsByTagName("jumlahDpp").item(0).getTextContent());
                efakturDTO.setJumlahPpn(err.getElementsByTagName("jumlahPpn").item(0).getTextContent());
                efakturDTO.setJumlahPpnBm(err.getElementsByTagName("jumlahPpnBm").item(0).getTextContent());
                efakturDTO.setStatusApproval(err.getElementsByTagName("statusApproval").item(0).getTextContent());
                efakturDTO.setStatusFaktur(err.getElementsByTagName("statusFaktur").item(0).getTextContent());
                efakturDTO.setReferensi(err.getElementsByTagName("referensi").item(0).getTextContent());

                //mengambil data company
                ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                CompanyBo companyBo = (CompanyBo) ctx.getBean("companyBoProxy");
                String companyId = CommonUtil.companyIdLogin();
                Company company = companyBo.getById(companyId);
                efakturDTO.setNpwpPerusahaan(company.getNpwp());
            } else {
                // success
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return efakturDTO;
    }

    public String approvePembayaran(String kasId, String who, String flag) {
        logger.info("[KasAction.approvePembayaran] start process >>>");
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            KasBo kasBo = (KasBo) ctx.getBean("kasBoProxy");
            Kas data = new Kas();
            String userLogin = CommonUtil.userLogin();
            String userIdLogin = CommonUtil.userIdLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            data.setKasId(kasId);
            data.setLastUpdateWho(userLogin);
            data.setLastUpdate(updateTime);
            data.setAction("U");

            if ("keu".equalsIgnoreCase(who)) {
                data.setApprovalKeuanganFlag(flag);
                data.setApprovalKeuanganId(userIdLogin);
                data.setApprovalKeuanganName(userLogin);
                data.setApprovalKeuanganDate(updateTime);
            } else if ("kasub".equalsIgnoreCase(who)) {
                data.setApprovalKasubKeuanganFlag(flag);
                data.setApprovalKasubKeuanganId(userIdLogin);
                data.setApprovalKasubKeuanganName(userLogin);
                data.setApprovalKasubKeuanganDate(updateTime);
            }
            kasBo.approveKas(data);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kasBoProxy.saveErrorMessage(e.getMessage(), "kasBO.approveKas");
            } catch (GeneralBOException e1) {
                logger.error("[KasAction.approvePembayaran] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KasAction.approvePembayaran] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[KasAction.approvePembayaran] end process <<<");

        if ("Y".equalsIgnoreCase(flag)) {
            return "Sukses Approve";
        } else {
            return "Sukses Not Approve";
        }
    }

    public String saveSessionLampiran(String namaLampiran, String fileUpload) {
        logger.info("[KasAction.saveSessionLampiran] start process >>>");
        String status = "";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Lampiran> lampiranList = (List<Lampiran>) session.getAttribute("listOfResultLampiran");

        if (lampiranList == null) {
            lampiranList = new ArrayList<>();
        }

        Lampiran newData = new Lampiran();
        newData.setNamaLampiran(namaLampiran);
        newData.setUploadFile(fileUpload);
        lampiranList.add(newData);
        session.setAttribute("listOfResultLampiran", lampiranList);

        logger.info("[KasAction.saveSessionLampiran] end process >>>");
        return status;
    }

    public String deleteSessionLampiran(String namaLampiran) {
        logger.info("[KasAction.deleteSessionLampiran] start process >>>");
        String status = "";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Lampiran> lampiranList = (List<Lampiran>) session.getAttribute("listOfResultLampiran");
        List<Lampiran> lampirans = new ArrayList<>();
        for (Lampiran data : lampiranList) {
            if (!namaLampiran.equalsIgnoreCase(data.getNamaLampiran())) {
                lampirans.add(data);
            }
        }
        session.setAttribute("listOfResultLampiran", lampirans);

        logger.info("[KasAction.deleteSessionLampiran] end process >>>");
        return status;
    }

    public String loadImageSessionLaporan(String namaLampiran) {
        logger.info("[KasAction.loadImageSessionLaporan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Lampiran> lampiranList = (List<Lampiran>) session.getAttribute("listOfResultLampiran");

        String images = "";
        for (Lampiran lampiran : lampiranList) {
            if (namaLampiran.equalsIgnoreCase(lampiran.getNamaLampiran())) {
                images = lampiran.getUploadFile();
            }
        }
        logger.info("[KasAction.loadImageSessionLaporan] end process >>>");
        return images;
    }

    public List<Lampiran> loadSessionLampiran() {
        logger.info("[KasAction.loadSessionLampiran] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Lampiran> lampiranList = (List<Lampiran>) session.getAttribute("listOfResultLampiran");
        logger.info("[KasAction.loadSessionLampiran] end process >>>");
        return lampiranList;
    }

    public Kas getViewApproval(String kasId) {
        logger.info("[KasAction.getViewApproval] start process >>>");
        String itemFlag = "Y";
        Kas modalApproval;
        try {
            modalApproval = init(kasId, itemFlag);
        } catch (GeneralBOException e) {
            throw new GeneralBOException("Error saat mengambil data approval ");
        }
        logger.info("[KasAction.getViewApproval] end process >>>");
        return modalApproval;
    }

    /*public void initComboCurrency() {

        List<ImAkunMataUangEntity> listOfMataUangs = new ArrayList<>();
        try {
            listOfMataUangs = akunMataUangBoProxy.getCurrencyActive();
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunMataUangBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboBranch] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboBranch] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
        }
        for (ImAkunMataUangEntity currency : listOfMataUangs) {
            AkunMataUang akunMataUang = new AkunMataUang();
            akunMataUang.setNamaMataUang(currency.getNamaMataUang());
            akunMataUang.setMataUangId(currency.getMataUangId());
            akunMataUang.setKodeMataUang(currency.getKodeMataUang());
            listOfComboMataUang.add(akunMataUang);
        }
    }*/


    public String paging() {
        return SUCCESS;
    }

    @Override
    public String downloadPdf() {
        return SUCCESS;
    }

    @Override
    public String downloadXls() {
        return SUCCESS;
    }
}
