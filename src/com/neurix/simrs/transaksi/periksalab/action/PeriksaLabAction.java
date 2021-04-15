package com.neurix.simrs.transaksi.periksalab.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.action.CheckupDetailAction;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.model.*;
import com.neurix.simrs.transaksi.profilrekammedisrj.bo.RekamMedisRawatJalanBo;
import com.neurix.simrs.transaksi.profilrekammedisrj.model.RekamMedisRawatJalan;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PeriksaLabAction extends BaseTransactionAction {
    protected static transient Logger logger = Logger.getLogger(PeriksaLabAction.class);
    private PeriksaLab periksaLab;
    private PeriksaLabBo periksaLabBoProxy;
    private CheckupBo checkupBoProxy;
    private CheckupDetailBo checkupDetailBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private BranchBo branchBoProxy;
    private String id;
    private String lab;
    private String ket;

    public String add() {
        logger.info("[PeriksaLabAction.add] start process >>>");
        String id = getId();
        String lab = getLab();
        String keterangan = getKet();
        String jk = "";
        String userArea = CommonUtil.userBranchLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        if (id != null && !"".equalsIgnoreCase(id)) {

            HeaderCheckup checkup = new HeaderCheckup();

            try {
                checkup = checkupBoProxy.getDataDetailPasien(id);
            } catch (GeneralBOException e) {
                logger.error("Found error when searc data detail " + e.getMessage());
            }

            if (checkup != null) {
                PeriksaLab periksaLab = new PeriksaLab();
                periksaLab.setNoCheckup(checkup.getNoCheckup());
                periksaLab.setIdDetailCheckup(checkup.getIdDetailCheckup());
                periksaLab.setIdPasien(checkup.getIdPasien());
                periksaLab.setNamaPasien(checkup.getNama());
                periksaLab.setAlamat(checkup.getJalan());
                periksaLab.setDesa(checkup.getNamaDesa());
                periksaLab.setKecamatan(checkup.getNamaKecamatan());
                periksaLab.setKota(checkup.getNamaKota());
                periksaLab.setProvinsi(checkup.getNamaProvinsi());
                periksaLab.setIdPelayanan(checkup.getIdPelayanan());
                periksaLab.setNamaPelayanan(checkup.getNamaPelayanan());
                if (checkup.getJenisKelamin() != null) {
                    if ("P".equalsIgnoreCase(checkup.getJenisKelamin())) {
                        jk = "Perempuan";
                    } else {
                        jk = "Laki-Laki";
                    }
                }
                periksaLab.setJenisKelamin(jk);
                periksaLab.setTempatLahir(checkup.getTempatLahir());
                periksaLab.setTglLahir(checkup.getTglLahir() == null ? null : checkup.getTglLahir().toString());
                if(checkup.getTglLahir() != null){
                    String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
                    periksaLab.setTempatTglLahir(checkup.getTempatLahir() + ", " + formatDate);
                    periksaLab.setUmur(CommonUtil.calculateAge(checkup.getTglLahir(), true));
                }
                periksaLab.setIdJenisPeriksa(checkup.getIdJenisPeriksaPasien());
                periksaLab.setNik(checkup.getNoKtp());
                periksaLab.setUrlKtp(checkup.getUrlKtp());
                periksaLab.setJenisPeriksaPasien(checkup.getStatusPeriksaName());
                periksaLab.setDiagnosa(checkup.getDiagnosa() + "-" + checkup.getNamaDiagnosa());
                periksaLab.setMetodePembayaran(checkup.getMetodePembayaran());

                List<PeriksaLab> periksaLabList = new ArrayList<>();
                PeriksaLab labData = new PeriksaLab();
                labData.setIdHeaderPemeriksaan(lab);
                try {
                    periksaLabList = periksaLabBoProxy.getByCriteriaHeaderPemeriksaan(labData);
                } catch (HibernateException e) {
                    logger.error("Found Error " + e.getMessage());
                }

                if(periksaLabList.size() > 0){
                    labData = periksaLabList.get(0);
                }
                periksaLab.setIsPeriksaLuar(labData.getIsPeriksaLuar());
                periksaLab.setIdHeaderPemeriksaan(lab);
                periksaLab.setKeterangan(labData.getIsJustLab());
                periksaLab.setIsJustLab(labData.getIsJustLab());
                periksaLab.setNamaDokterPengirim(labData.getNamaDokterPengirim());
                setPeriksaLab(periksaLab);

                PeriksaLab periksa = new PeriksaLab();
                periksa.setIdHeaderPemeriksaan(lab);
                periksa.setLastUpdate(updateTime);
                periksa.setLastUpdateWho(userArea);
                periksa.setTanggalMasukLab(new Timestamp(Calendar.getInstance().getTimeInMillis()));

                try {
                    periksaLabBoProxy.saveEditStatusPeriksa(periksa);
                } catch (GeneralBOException e) {
                    logger.error("Found Error when " + e.getMessage());
                }

            } else {
                setPeriksaLab(new PeriksaLab());
            }
        }

        logger.info("[PeriksaLabAction.add] end process <<<");
        return "init_add";
    }

    @Override
    public String search() {
        logger.info("[PeriksaLabAction.search] start process >>>");
        PeriksaLab periksaLab = getPeriksaLab();
        List<PeriksaLab> listPeriksaLabList = new ArrayList();
        periksaLab.setIdKategoriLab("lab");
        periksaLab.setBranchId(CommonUtil.userBranchLogin());
        try {
            listPeriksaLabList = periksaLabBoProxy.getSearchLab(periksaLab);
        } catch (GeneralBOException e) {
            logger.error("[PeriksaLabAction.save] Error when searching periksa lab by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listPeriksaLabList);
        logger.info("[PeriksaLabAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[PeriksaLabAction.initForm] start process >>>");
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("dd-MM-yyyy").format(date);
        PeriksaLab periksaLab = new PeriksaLab();
        periksaLab.setStDateFrom(tglToday);
        periksaLab.setStDateTo(tglToday);
        setPeriksaLab(periksaLab);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        logger.info("[PeriksaLabAction.initForm] end process <<<");
        return "search";
    }

    public CrudResponse saveOrderLab(String data) {
        logger.info("[PeriksaLabAction.saveOrderLab] start process >>>");
        CrudResponse response = new CrudResponse();
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
            if (data != null && !"".equalsIgnoreCase(data)) {
                JSONObject obj = new JSONObject(data);
                if (obj != null) {
                    String idDetailCheckup = obj.getString("id_detail_checkup");
                    String listPemeriksaan = obj.getString("list_pemeriksaan");
                    String isLuar = obj.getString("is_luar");
                    String idDokterPengirim = obj.getString("id_dokter_pengirim");
                    String namaDokterPengirim = obj.getString("nama_dokter_pengirim");
                    String idKategori = obj.getString("id_kategori_lab");
                    String waktuPending = obj.getString("waktu_pending");
                    String ttdPengirim = obj.getString("ttd_pengirim");
                    String jenisPemeriksaan = obj.getString("jenis_pemeriksaan");
                    List<PeriksaLab> periksaLabList = new ArrayList<>();

                    if (listPemeriksaan != null) {
                        JSONArray json = new JSONArray(listPemeriksaan);
                        for (int i = 0; i < json.length(); i++) {
                            JSONObject ob = json.getJSONObject(i);
                            PeriksaLab periksa = new PeriksaLab();
                            List<PeriksaLabDetail> detailList = new ArrayList<>();
                            periksa.setIdLab(ob.getString("id_pemeriksaan"));
                            periksa.setNamaLab(ob.getString("nama_pemeriksaan"));
                            String listParameter = ob.getString("list_parameter");
                            if (listParameter != null) {
                                JSONArray jsn = new JSONArray(listParameter);
                                for (int j = 0; j < jsn.length(); j++) {
                                    JSONObject oc = jsn.getJSONObject(j);
                                    PeriksaLabDetail detail = new PeriksaLabDetail();
                                    detail.setIdLabDetail(oc.getString("id_parameter"));
                                    detail.setNamaDetailPeriksa(oc.getString("nama_parameter"));
                                    detailList.add(detail);
                                }
                            } else {
                                response.setStatus("error");
                                response.setMsg("Data order jenis parameter pemeriksaan tidak ditemukan");
                                return response;
                            }
                            periksa.setDetailLab(detailList);
                            periksaLabList.add(periksa);
                        }
                    } else {
                        response.setStatus("error");
                        response.setMsg("Data order jenis pemeriksaan tidak ditemukan");
                        return response;
                    }

                    PeriksaLab periksaLab = new PeriksaLab();
                    periksaLab.setIdDetailCheckup(idDetailCheckup);
                    periksaLab.setAction("C");
                    periksaLab.setFlag("Y");
                    periksaLab.setIdDokterPengirim(idDokterPengirim);
                    periksaLab.setNamaDokterPengirim(namaDokterPengirim);
                    periksaLab.setIdKategoriLab(idKategori);
                    periksaLab.setIsLuar(isLuar);
                    periksaLab.setIsJustLab("N");
                    periksaLab.setJenisPeriksaPasien(jenisPemeriksaan);

                    if (waktuPending != null && !"".equalsIgnoreCase(waktuPending)) {
                        periksaLab.setIsPending("Y");
                        periksaLab.setLastUpdate(Timestamp.valueOf(waktuPending));
                        periksaLab.setCreatedDate(Timestamp.valueOf(waktuPending));
                        periksaLab.setCreatedWho(userLogin);
                        periksaLab.setLastUpdateWho(userLogin);
                    } else {
                        periksaLab.setIsPending("N");
                        periksaLab.setCreatedWho(userLogin);
                        periksaLab.setLastUpdate(updateTime);
                        periksaLab.setCreatedDate(updateTime);
                        periksaLab.setLastUpdateWho(userLogin);
                    }

                    if (ttdPengirim != null && !"".equalsIgnoreCase(ttdPengirim)) {
                        BASE64Decoder decoder = new BASE64Decoder();
                        byte[] decodedBytes = decoder.decodeBuffer(ttdPengirim);
                        String wkt = updateTime.toString();
                        String patten = wkt.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                        String fileName = idDokterPengirim + "-" + patten + ".png";
                        String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_DOKTER + fileName;
                        BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                        if (image == null) {
                            logger.error("Buffered Image is null");
                            response.setStatus("error");
                            response.setMsg("Buffered Image is null");
                            return response;
                        } else {
                            File f = new File(uploadFile);
                            ImageIO.write(image, "png", f);
                            periksaLab.setTtdPengirim(fileName);
                        }

                        if (periksaLabList.size() > 0) {
                            periksaLab.setListLab(periksaLabList);
                            periksaLabBo.saveOrderPemeriksaan(periksaLab);
                            insertProfilRJ(idDetailCheckup);
                            response.setStatus("success");
                            response.setMsg("OK");
                        } else {
                            response.setStatus("error");
                            response.setMsg("Data order lab atau radiologi tidak ditemukan");
                        }
                    }
                } else {
                    response.setStatus("error");
                    response.setMsg("Data order lab atau radiologi tidak ditemukan");
                }
            } else {
                response.setStatus("error");
                response.setMsg("Data order lab atau radiologi tidak ditemukan");
            }

        } catch (Exception e) {
            response.setMsg(e.getMessage());
            logger.error("[PeriksaLabAction.saveOrderLab] Error when adding item ,Found problem when saving add data, please inform to your admin." + e.getMessage());
        }
        logger.info("[PeriksaLabAction.saveOrderLab] End process >>>");
        return response;
    }

    public CrudResponse insertProfilRJ(String idDetailCheckup) {
        CrudResponse response = new CrudResponse();
        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            CheckupBo checkupBo = (CheckupBo) ctx.getBean("checkupBoProxy");
            RekamMedisRawatJalanBo rekamMedisRawatJalanBo = (RekamMedisRawatJalanBo) ctx.getBean("rekamMedisRawatJalanBoProxy");
            List<RekamMedisRawatJalan> rekamMedisRawatJalanList = new ArrayList<>();
            try {
                RekamMedisRawatJalan rekamMedisRawatJalan = new RekamMedisRawatJalan();
                rekamMedisRawatJalan.setIdDetailCheckup(idDetailCheckup);
                rekamMedisRawatJalanList = rekamMedisRawatJalanBo.getByCriteria(rekamMedisRawatJalan);
                if (rekamMedisRawatJalanList.size() > 0) {
                    RekamMedisRawatJalan rawatJalan = new RekamMedisRawatJalan();
                    rawatJalan.setWaktu(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setPemeriksaanFisik(checkupBo.getPenunjangMedis(idDetailCheckup, null));
                    rawatJalan.setDiagnosa(checkupBo.getDiagnosaPasien(idDetailCheckup));
                    rawatJalan.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setLastUpdateWho(CommonUtil.userLogin());
                    rawatJalan.setAction("U");
                    response = rekamMedisRawatJalanBo.saveEdit(rawatJalan);
                } else {
                    RekamMedisRawatJalan rawatJalan = new RekamMedisRawatJalan();
                    rawatJalan.setWaktu(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setPemeriksaanFisik(checkupBo.getPenunjangMedis(idDetailCheckup, null));
                    rawatJalan.setDiagnosa(checkupBo.getDiagnosaPasien(idDetailCheckup));
                    rawatJalan.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setLastUpdateWho(CommonUtil.userLogin());
                    rawatJalan.setCreatedWho(CommonUtil.userLogin());
                    rawatJalan.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                    rawatJalan.setIdDetailCheckup(idDetailCheckup);
                    rawatJalan.setAction("C");
                    rawatJalan.setFlag("Y");
                    response = rekamMedisRawatJalanBo.saveAdd(rawatJalan);
                }
            } catch (GeneralBOException e) {
                response.setStatus("error");
                response.setMsg("error");
            }
        }
        return response;
    }

    public List<PeriksaLab> listOrderLab(String idDetailCheckup) {
        logger.info("[PeriksaLabAction.listOrderLab] start process >>>");
        List<PeriksaLab> periksaLabList = new ArrayList<>();
        PeriksaLab periksaLab = new PeriksaLab();
        periksaLab.setIdDetailCheckup(idDetailCheckup);
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
        if (!"".equalsIgnoreCase(idDetailCheckup)) {
            try {
                periksaLabList = periksaLabBo.getByCriteriaHeaderPemeriksaan(periksaLab);
            } catch (GeneralBOException e) {
                logger.error("[PeriksaLabAction.listOrderLab] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            }
        }
        logger.info("[PeriksaLabAction.listOrderLab] start process >>>");
        return periksaLabList;
    }

    public List<Dokter> getListDokterTeamByNoDetail(String idDetailCheckup) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        return checkupDetailBo.getListDokterByDetailCheckup(idDetailCheckup);
    }

    public List<Dokter> getListDokterLabRadiologi(String tipe) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
        return periksaLabBo.getListDokterLabRadiologi(tipe);
    }

    public List<PeriksaLab> listParameterPemeriksaan(String idHeader) {
        logger.info("[PeriksaLabAction.listParameterPemeriksaan] start process >>>");
        List<PeriksaLab> periksaLabDetailList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
        if (idHeader != null && !"".equalsIgnoreCase(idHeader)) {
            try {
                periksaLabDetailList = periksaLabBo.getListParameterLab(idHeader);
            } catch (GeneralBOException e) {
                logger.error("[PeriksaLabAction.listParameterPemeriksaan] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            }
        }
        logger.info("[PeriksaLabAction.listParameterPemeriksaan] start process >>>");
        return periksaLabDetailList;
    }

    public CrudResponse saveEditParameterLab(String idPeriksaLabDetail, String hasil, String keterangan) {
        logger.info("[PeriksaLabAction.saveEditParameterLab] start process >>>");
        CrudResponse response = new CrudResponse();
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            PeriksaLabDetail periksaLabDetail = new PeriksaLabDetail();

            periksaLabDetail.setIdPeriksaLabDetail(idPeriksaLabDetail);
            periksaLabDetail.setHasil(hasil);
            periksaLabDetail.setKeteranganPeriksa(keterangan);
            periksaLabDetail.setCreatedWho(userLogin);
            periksaLabDetail.setLastUpdate(updateTime);
            periksaLabDetail.setLastUpdateWho(userLogin);
            periksaLabDetail.setAction("U");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

            response = periksaLabBo.saveUpdateHasilLab(periksaLabDetail);

        } catch (GeneralBOException e) {
            logger.error("[PeriksaLabAction.saveEditParameterLab] Error when adding item Found problem when saving add data, please inform to your admin.", e);
        }

        logger.info("[PeriksaLabAction.saveEditParameterLab] End process >>>");
        return response;
    }

    private HeaderCheckup getHeaderCheckup(String noCheckup) {
        logger.info("[PeriksaLabAction.getHeaderCheckup] start process >>>");

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try {
            headerCheckupList = checkupBoProxy.getByCriteria(headerCheckup);
        } catch (GeneralBOException e) {
            logger.error("[PeriksaLabAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
        }

        HeaderCheckup result = new HeaderCheckup();
        if (!headerCheckupList.isEmpty()) {
            result = headerCheckupList.get(0);
        }

        logger.info("[PeriksaLabAction.getHeaderCheckup] end process <<<");
        return result;
    }

    private HeaderDetailCheckup getDetailCheckup(String idDetailCheckup) {
        logger.info("[PeriksaLabAction.getDetailCheckup] start process >>>");

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        detailCheckup.setIdDetailCheckup(idDetailCheckup);

        List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();
        try {
            detailCheckupList = checkupDetailBoProxy.getByCriteria(detailCheckup);
        } catch (GeneralBOException e) {
            logger.error("[PeriksaLabAction.getDetailCheckup] Error When Get Header Checkup Data", e);
        }

        HeaderDetailCheckup result = new HeaderDetailCheckup();
        if (!detailCheckupList.isEmpty()) {
            result = detailCheckupList.get(0);
        }

        logger.info("[PeriksaLabAction.getDetailCheckup] end process <<<");
        return result;
    }

    private JenisPriksaPasien getListJenisPeriksaPasien(String idJenisPeriksa) {
        logger.info("[PeriksaLabAction.getListJenisPeriksaPasien] start process >>>");

        JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();
        jenisPriksaPasien.setIdJenisPeriksaPasien(idJenisPeriksa);

        List<JenisPriksaPasien> jenisPriksaPasienList = new ArrayList<>();
        try {
            jenisPriksaPasienList = jenisPriksaPasienBoProxy.getListAllJenisPeriksa(jenisPriksaPasien);
        } catch (GeneralBOException e) {
            logger.error("[PeriksaLabAction.getListJenisPeriksaPasien] Error When Get Jenis Pasien Data", e);
        }

        JenisPriksaPasien result = new JenisPriksaPasien();
        if (!jenisPriksaPasienList.isEmpty()) {
            result = jenisPriksaPasienList.get(0);
        }

        logger.info("[PeriksaLabAction.getListJenisPeriksaPasien] end process <<<");
        return result;
    }

    public CrudResponse editOrderLab(String data) {
        logger.info("[PeriksaLabAction.editOrderLab] start process >>>");
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
        try {
            if (data != null && !"".equalsIgnoreCase(data)) {
                JSONObject obj = new JSONObject(data);
                if (obj != null) {
                    String idHeaderPemeriksaan = obj.getString("id_header_pemeriksaan");
                    String listPemeriksaan = obj.getString("list_pemeriksaan");
                    String isPeriksaLuar = obj.getString("is_luar");
                    String idKategoriLab = obj.getString("id_kategori_lab");
                    String jenisPemeriksaan = obj.getString("jenis_pemeriksaan");
                    List<PeriksaLab> periksaLabList = new ArrayList<>();

                    if (listPemeriksaan != null) {
                        JSONArray json = new JSONArray(listPemeriksaan);
                        for (int i = 0; i < json.length(); i++) {
                            JSONObject ob = json.getJSONObject(i);
                            PeriksaLab periksa = new PeriksaLab();
                            List<PeriksaLabDetail> detailList = new ArrayList<>();
                            periksa.setIdLab(ob.getString("id_pemeriksaan"));
                            periksa.setNamaLab(ob.getString("nama_pemeriksaan"));
                            String listParameter = ob.getString("list_parameter");
                            if (listParameter != null) {
                                JSONArray jsn = new JSONArray(listParameter);
                                for (int j = 0; j < jsn.length(); j++) {
                                    JSONObject oc = jsn.getJSONObject(j);
                                    PeriksaLabDetail detail = new PeriksaLabDetail();
                                    detail.setIdLabDetail(oc.getString("id_parameter"));
                                    detail.setNamaDetailPeriksa(oc.getString("nama_parameter"));
                                    detailList.add(detail);
                                }
                            } else {
                                response.setStatus("error");
                                response.setMsg("Data order jenis parameter pemeriksaan tidak ditemukan");
                                return response;
                            }
                            periksa.setDetailLab(detailList);
                            periksaLabList.add(periksa);
                        }
                    } else {
                        response.setStatus("error");
                        response.setMsg("Data order jenis pemeriksaan tidak ditemukan");
                        return response;
                    }

                    PeriksaLab periksaLab = new PeriksaLab();
                    periksaLab.setIdHeaderPemeriksaan(idHeaderPemeriksaan);
                    periksaLab.setIsLuar(isPeriksaLuar);
                    periksaLab.setFlag("Y");
                    periksaLab.setAction("U");
                    periksaLab.setCreatedWho(userLogin);
                    periksaLab.setCreatedDate(updateTime);
                    periksaLab.setLastUpdateWho(userLogin);
                    periksaLab.setLastUpdate(updateTime);
                    periksaLab.setIdKategoriLab(idKategoriLab);
                    periksaLab.setJenisPeriksaPasien(jenisPemeriksaan);

                    if (periksaLabList.size() > 0) {
                        periksaLab.setListLab(periksaLabList);
                        periksaLabBo.editOrderPemeriksaan(periksaLab);
                        response.setStatus("success");
                        response.setMsg("OK");
                    } else {
                        response.setStatus("error");
                        response.setMsg("Data order lab atau radiologi tidak ditemukan");
                    }

                } else {
                    response.setStatus("error");
                    response.setMsg("Tidak ada data order lab");
                }
            } else {
                response.setStatus("error");
                response.setMsg("Tidak ada data order lab");
            }
        } catch (Exception e) {
            response.setStatus("error");
            response.setMsg("Error ketika edit lab...!, " + e.getMessage());
            logger.error("[PeriksaLabAction.editOrderLab] Error when adding item , Found problem when saving add data, please inform to your admin.", e);
        }
        logger.info("[PeriksaLabAction.editOrderLab] End process >>>");
        return response;
    }

    public CheckResponse selesaiPemeriksaan(String data) {
        logger.info("[PeriksaLabAction.selesaiPemeriksaan] start process >>>");
        CheckResponse response = new CheckResponse();
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

            if (data != null && !"".equalsIgnoreCase(data)) {
                JSONObject obj = new JSONObject(data);
                if (obj != null) {
                    List<ItSimrsUploadHasilPemeriksaanEntity> uploadHasilPemeriksaanEntityList = new ArrayList<>();
                    String idHeaderPemeriksaan = null;
                    String idPeriksaLab = null;
                    String keterangan = null;
                    String namaPetugas = null;
                    String nipPetugas = null;
                    String namaValidator = null;
                    String nipValidator = null;
                    String ttdPetugas = null;
                    String ttdValidator = null;

                    if(obj.has("id_header_pemeriksaan")){
                        idHeaderPemeriksaan = obj.getString("id_header_pemeriksaan");
                    }

                    if(obj.has("id_periksa_lab")){
                        idPeriksaLab = obj.getString("id_periksa_lab");
                    }

                    if(obj.has("keterangan")){
                        keterangan = obj.getString("keterangan");
                    }

                    if (obj.has("nama_petugas")) {
                        namaPetugas = obj.getString("nama_petugas");
                    }
                    if (obj.has("nip_petugas")) {
                        nipPetugas = obj.getString("nip_petugas");
                    }
                    if (obj.has("nama_validator")) {
                        namaValidator = obj.getString("nama_validator");
                    }
                    if (obj.has("nip_validator")) {
                        nipValidator = obj.getString("nip_validator");
                    }
                    if (obj.has("ttd_petugas")) {
                        ttdPetugas = obj.getString("ttd_petugas");
                    }
                    if (obj.has("ttd_validator")) {
                        ttdValidator = obj.getString("ttd_validator");
                    }

                    String jsonParams = "";
                    if (obj.has("hasil_pemeriksaan")) {
                        jsonParams = obj.getString("hasil_pemeriksaan");
                    }
                    String dataJustLab = obj.getString("data");
                    BigDecimal totalTarif = null;
                    if (obj.has("total_tarif")) {
                        if (obj.getString("total_tarif") != null && !"".equalsIgnoreCase(obj.getString("total_tarif"))) {
                            totalTarif = new BigDecimal(String.valueOf(obj.getString("total_tarif")));
                        }
                    }

                    PeriksaLab periksaLab = new PeriksaLab();
                    periksaLab.setIdHeaderPemeriksaan(idHeaderPemeriksaan);
                    periksaLab.setNamaPetugas(namaPetugas);
                    periksaLab.setIdPetugas(nipPetugas);
                    periksaLab.setNamaValidator(namaValidator);
                    periksaLab.setIdValidator(nipValidator);
                    periksaLab.setLastUpdate(updateTime);
                    periksaLab.setLastUpdateWho(userLogin);
                    periksaLab.setAction("U");
                    if (obj.has("keterangan_hasil")) {
                        if (obj.getString("keterangan_hasil") != null && !"".equalsIgnoreCase(obj.getString("keterangan_hasil"))) {
                            periksaLab.setCatatan(obj.getString("keterangan_hasil"));
                        }
                    }

                    if (ttdValidator != null && !"".equalsIgnoreCase(ttdValidator)) {
                        try {
                            BASE64Decoder decoder = new BASE64Decoder();
                            byte[] decodedBytes = decoder.decodeBuffer(ttdValidator);
                            String patten = updateTime.toString().replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                            String fileName = idHeaderPemeriksaan + "-ttd_validator-" + patten + ".png";
                            String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_DOKTER + fileName;
                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                            if (image == null) {
                                logger.error("Buffered Image is null");
                                response.setStatus("error");
                                response.setMessage("Buffered Image is null");
                            } else {
                                File f = new File(uploadFile);
                                ImageIO.write(image, "png", f);
                                periksaLab.setTtdValidator(fileName);
                            }
                        } catch (IOException e) {
                            response.setStatus("error");
                            response.setMessage("IO Error" + e.getMessage());
                            return response;
                        }
                    }
                    if (ttdPetugas != null && !"".equalsIgnoreCase(ttdPetugas)) {
                        try {
                            BASE64Decoder decoder = new BASE64Decoder();
                            byte[] decodedBytes = decoder.decodeBuffer(ttdPetugas);
                            String patten = updateTime.toString().replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                            String fileName = idHeaderPemeriksaan + "-ttd_petugas-" + patten + ".png";
                            String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_DOKTER + fileName;
                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                            if (image == null) {
                                logger.error("Buffered Image is null");
                                response.setStatus("error");
                                response.setMessage("Buffered Image is null");
                            } else {
                                File f = new File(uploadFile);
                                ImageIO.write(image, "png", f);
                                periksaLab.setTtdPetugas(fileName);
                            }
                        } catch (IOException e) {
                            response.setStatus("error");
                            response.setMessage("IO Error" + e.getMessage());
                            return response;
                        }
                    }
                    List<PeriksaLabDetail> detailList = new ArrayList<>();
                    if (jsonParams != null && !"".equalsIgnoreCase(jsonParams)) {
                        try {
                            JSONArray json = new JSONArray(jsonParams);
                            if (json != null) {
                                for (int i = 0; i < json.length(); i++) {
                                    JSONObject object = json.getJSONObject(i);
                                    PeriksaLabDetail detail = new PeriksaLabDetail();
                                    detail.setIdPeriksaLabDetail(object.getString("id_periksa_lab"));
                                    detail.setHasil(object.getString("hasil"));
                                    detail.setKeteranganPeriksa(object.getString("kesan"));
                                    detailList.add(detail);
                                }
                            }
                        } catch (JSONException e) {
                            response.setStatus("error");
                            response.setMessage("Error" + e.getMessage());
                        }
                    }

                    periksaLab.setUploadHasil(uploadHasilPemeriksaanEntityList);
                    periksaLab.setTarifLabLuar(totalTarif);
                    periksaLabBo.selesaiPemeriksaan(periksaLab);
                    response.setStatus("success");
                    response.setMessage("Berhasil");
                    if ("Y".equalsIgnoreCase(keterangan)) {
                        if ("success".equalsIgnoreCase(response.getStatus())) {
                            CheckupDetailAction detailAction = new CheckupDetailAction();
                            CrudResponse res = new CrudResponse();
                            res = detailAction.closeTraksaksiPasien(dataJustLab);
                            if ("success".equalsIgnoreCase(res.getStatus())) {
                                response.setStatus("success");
                                response.setMessage("Berhasil");
                            } else {
                                response.setStatus("error");
                                response.setMessage("Error" + res.getMsg());
                            }
                        }
                    }
                } else {
                    response.setStatus("error");
                    response.setMessage("Error, Data yang dikirim tidak lengkap...!");
                }
            } else {
                response.setStatus("error");
                response.setMessage("Error, Data yang dikirim tidak lengkap...!");
            }
        } catch (Exception e) {
            response.setStatus("error");
            response.setMessage("Error" + e.getMessage());
        }
        logger.info("[PeriksaLabAction.selesaiPemeriksaan] End process >>>");
        return response;
    }

    public String printPeriksaLab() {

        String id = getId();
        String jk = "";

        HeaderDetailCheckup headerDetailCheckup = getDetailCheckup(id);

        String branch = CommonUtil.userBranchLogin();
        String logo = "";

        String branchName = CommonUtil.userBranchNameLogin();
        Branch branches = new Branch();

        try {
            branches = branchBoProxy.getBranchById(branch, "Y");
        } catch (GeneralBOException e) {
            logger.error("Found Error when searhc branch logo");
        }

        if (branches != null) {
            logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
        }

        HeaderCheckup headerCheckup = getHeaderCheckup(headerDetailCheckup.getNoCheckup());
        JenisPriksaPasien jenisPriksaPasien = getListJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());
        if ("L".equalsIgnoreCase(headerCheckup.getJenisKelamin())) {
            jk = "Laki-Laki";
        } else {
            jk = "Perempuan";
        }
        reportParams.put("logo", logo);
        reportParams.put("nama", headerCheckup.getNama());
        String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(headerCheckup.getTglLahir());
        reportParams.put("tglLahir", headerCheckup.getTempatLahir() + ", " + formatDate);
        reportParams.put("namaDokter", "");
        reportParams.put("diagnosa", jk);
        String alamat = "Desa " + headerCheckup.getNamaDesa() + ", Kec. " + headerCheckup.getNamaKecamatan() + "\n" + "Kab. " + headerCheckup.getNamaKota() + ", " + headerCheckup.getNamaProvinsi();
        reportParams.put("alamat", alamat);
        reportParams.put("noLab", "");
        reportParams.put("noRegistrasi", "");
        reportParams.put("noRm", "");
        reportParams.put("tglPeriksa", "");
        reportParams.put("tglSelesai", "");
        reportParams.put("ruangKelas", "");
        reportParams.put("idDetailCheckup", id);
        reportParams.put("noCheckup", headerCheckup.getNoCheckup());
        reportParams.put("namaRs", "Rumah Sakit Toeloengredjo");
        reportParams.put("jalanRs", "Jln. Kusuma Bangsa No. 01 Kanigoro Blitar. Telepon (0549) 7878 89890 900");
        reportParams.put("fox", "Fox : (0354) 383883");


        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        return "print_periksa_lab";
    }

    public String printLab() {

        HeaderCheckup checkup = new HeaderCheckup();
        String lab = getLab();
        String id = getId();
        String jk = "";

        String branch = CommonUtil.userBranchLogin();
        String logo = "";
        Branch branches = new Branch();

        try {
            branches = branchBoProxy.getBranchById(branch, "Y");
        } catch (GeneralBOException e) {
            logger.error("Found Error when searhc branch logo");
        }

        if (branches != null) {
            logo = CommonConstant.RESOURCE_PATH_IMG_ASSET + "/" + CommonConstant.APP_NAME + CommonConstant.RESOURCE_PATH_IMAGES + branches.getLogoName();
        }

        try {
            checkup = checkupBoProxy.getDataDetailPasien(id);
        } catch (GeneralBOException e) {
            logger.error("Found Error when search data detail pasien " + e.getMessage());
        }

        if (checkup != null) {

            List<PeriksaLab> periksaLabList = new ArrayList<>();
            PeriksaLab labData = new PeriksaLab();
            labData.setIdHeaderPemeriksaan(lab);
            try {
                periksaLabList = periksaLabBoProxy.getByCriteriaHeaderPemeriksaan(labData);
            } catch (HibernateException e) {
                logger.error("Found Error " + e.getMessage());
            }

            if(periksaLabList.size() > 0){
                labData = periksaLabList.get(0);
            }

            reportParams.put("title", "Hasil Periksa Lab");
            reportParams.put("area", CommonUtil.userAreaName());
            reportParams.put("unit", CommonUtil.userBranchNameLogin());
            reportParams.put("idPasien", checkup.getIdPasien());
            reportParams.put("idPeriksaLab", lab);
            reportParams.put("id", lab);
            reportParams.put("logo", logo);
            reportParams.put("nik", checkup.getNoKtp());
            reportParams.put("nama", checkup.getNama());
            if (checkup.getTglLahir() != null) {
                String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
                reportParams.put("tglLahir", checkup.getTempatLahir() + ", " + formatDate);
                reportParams.put("tgllahir", formatDate);
                reportParams.put("divisi", "Laboratorium");
            }
            if (labData.getCreatedDate() != null) {
                String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(labData.getCreatedDate());
                reportParams.put("tglFoto", formatDate);
            }

            if ("L".equalsIgnoreCase(checkup.getJenisKelamin())) {
                jk = "Laki-Laki";
            } else {
                jk = "Perempuan";
            }
            reportParams.put("jenisKelamin", jk);
            reportParams.put("jenisPasien", checkup.getStatusPeriksaName());
            reportParams.put("poli", checkup.getNamaPelayanan());
            reportParams.put("provinsi", checkup.getNamaProvinsi());
            reportParams.put("kabupaten", checkup.getNamaKota());
            reportParams.put("kecamatan", checkup.getNamaKecamatan());
            reportParams.put("desa", checkup.getNamaDesa());
            reportParams.put("diagnosa", checkup.getNamaDiagnosa());

            reportParams.put("idDokterPengirim", labData.getIdDokterPengirim());
            reportParams.put("dokterPengirim", labData.getDokterPengirim());
            reportParams.put("idPetugas", labData.getIdPetugas());
            reportParams.put("namaPetugas", labData.getNamaPetugas());
            reportParams.put("idValidator", labData.getIdValidator());
            reportParams.put("namaValidator", labData.getNamaValidator());
            reportParams.put("ttdPetugas", labData.getTtdPetugas());
            reportParams.put("ttdValidator", labData.getTtdValidator());
            reportParams.put("umur", CommonUtil.calculateAge(checkup.getTglLahir(), true));

            String namaLab = "";
            List<PeriksaLab> periksaLabs = new ArrayList<>();
            PeriksaLab periksa = new PeriksaLab();
            periksa.setIdHeaderPemeriksaan(lab);
            try {
                periksaLabs = periksaLabBoProxy.getByCriteria(periksa);
            } catch (HibernateException e) {
                logger.error("[CheckupDetailAction.saveAddToRiwayatTindakan] Found error when search riwayat tindakan :" + e.getMessage());
            }

            if(periksaLabs.size() > 0){
                for (PeriksaLab pb: periksaLabs){
                    if("".equalsIgnoreCase(namaLab)){
                        namaLab = pb.getNamaPemeriksaan();
                    }else{
                        namaLab = namaLab+", "+pb.getNamaPemeriksaan();
                    }
                }
            }
            reportParams.put("pemeriksaan", namaLab);


            try {
                preDownload();
            } catch (SQLException e) {
                logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
                return "search";
            }
        }

        if ("label".equalsIgnoreCase(getKet())) {
            return "print_label";
        } else {
            return "print_lab";
        }
    }

    public CrudResponse saveUpdatePemeriksaan(String idHeaderPemeriksaan, String listPemeriksaan) {
        CrudResponse response = new CrudResponse();
        logger.info("[PeriksaLabAction.saveUpdatePemeriksaan] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
            List<PeriksaLab> periksaLabList = new ArrayList<>();

            if (listPemeriksaan != null) {
                JSONArray json = new JSONArray(listPemeriksaan);
                for (int i = 0; i < json.length(); i++) {
                    JSONObject ob = json.getJSONObject(i);
                    PeriksaLab periksa = new PeriksaLab();
                    List<PeriksaLabDetail> detailList = new ArrayList<>();
                    periksa.setIdLab(ob.getString("id_pemeriksaan"));
                    periksa.setNamaLab(ob.getString("nama_pemeriksaan"));
                    String listParameter = ob.getString("list_parameter");
                    if (listParameter != null) {
                        JSONArray jsn = new JSONArray(listParameter);
                        for (int j = 0; j < jsn.length(); j++) {
                            JSONObject oc = jsn.getJSONObject(j);
                            PeriksaLabDetail detail = new PeriksaLabDetail();
                            detail.setIdLabDetail(oc.getString("id_parameter"));
                            detail.setNamaDetailPeriksa(oc.getString("nama_parameter"));
                            detailList.add(detail);
                        }
                    } else {
                        response.setStatus("error");
                        response.setMsg("Data order jenis parameter pemeriksaan tidak ditemukan");
                        return response;
                    }
                    periksa.setDetailLab(detailList);
                    periksaLabList.add(periksa);
                }
            } else {
                response.setStatus("error");
                response.setMsg("Data order jenis pemeriksaan tidak ditemukan");
                return response;
            }

            PeriksaLab periksaLab = new PeriksaLab();
            periksaLab.setIdHeaderPemeriksaan(idHeaderPemeriksaan);
            periksaLab.setCreatedWho(userLogin);
            periksaLab.setLastUpdate(updateTime);
            periksaLab.setCreatedDate(updateTime);
            periksaLab.setLastUpdateWho(userLogin);
            periksaLab.setAction("C");
            periksaLab.setFlag("Y");
            periksaLab.setListLab(periksaLabList);

            periksaLabBo.saveUpdateParameter(periksaLab);
            response.setStatus("success");
            response.setMsg("OK");

        } catch (Exception e) {
            response.setStatus("error");
            response.setMsg("Found error");
        }

        logger.info("[PeriksaLabAction.saveUpdatePemeriksaan] End process >>>");
        return response;
    }

    public List<PeriksaLab> getListLab(String noChekcup, String jenis) {
        logger.info("[PeriksaLabAction.getListLab] start process >>>");
        List<PeriksaLab> periksaLabList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

        if (!"".equalsIgnoreCase(noChekcup) && noChekcup != null) {
            try {
                periksaLabList = periksaLabBo.getListLab(noChekcup, jenis);
            } catch (GeneralBOException e) {
                logger.error("[PeriksaLabAction.getListLab] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            }

        }
        logger.info("[PeriksaLabAction.getListLab] end process >>>");
        return periksaLabList;
    }

    public List<PeriksaLab> pushNotifLab(String kategori) {
        logger.info("[PeriksaLabAction.getListLab] start process >>>");
        List<PeriksaLab> periksaLabList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
        String branchId = CommonUtil.userBranchLogin();

        if (!"".equalsIgnoreCase(kategori) && kategori != null) {
            try {
                periksaLabList = periksaLabBo.pushListLab(kategori, branchId);
            } catch (GeneralBOException e) {
                logger.error("[PeriksaLabAction.getListLab] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            }

        }
        logger.info("[PeriksaLabAction.getListLab] end process >>>");
        return periksaLabList;
    }

    public List<PeriksaLab> getListHistoryLabRadiologi(String idPasien) {
        logger.info("[PeriksaLabAction.getListHistoryLabRadiologi] start process >>>");
        List<PeriksaLab> periksaLabList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

        if (!"".equalsIgnoreCase(idPasien) && idPasien != null) {
            try {
                periksaLabList = periksaLabBo.getHistoryLabRadiologi(idPasien);
            } catch (GeneralBOException e) {
                logger.error("[PeriksaLabAction.getListLab] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            }

        }
        logger.info("[PeriksaLabAction.getListHistoryLabRadiologi] end process >>>");
        return periksaLabList;
    }

    public CrudResponse saveDetailParameter(String data) {
        logger.info("[PeriksaLabAction.saveDetailParameter] start process >>>");
        CrudResponse response = new CrudResponse();
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

            if (data != null && !"".equalsIgnoreCase(data)) {
                JSONObject obj = new JSONObject(data);
                if (obj != null) {

                    String idHeaderPemeriksaan = null;
                    String idPeriksaDetail = null;
                    String namaDokter = null;
                    String sipDokter = null;
                    String ttdDokter = null;
                    String hasil = null;
                    String keteranganHasil = null;

                    if(obj.has("id_header_pemeriksaan")){
                        idHeaderPemeriksaan = obj.getString("id_header_pemeriksaan");
                    }
                    if(obj.has("id_periksa_detail")){
                        idPeriksaDetail = obj.getString("id_periksa_detail");
                    }
                    if(obj.has("nama_dokter")){
                        namaDokter = obj.getString("nama_dokter");
                    }
                    if(obj.has("sip_dokter")){
                        sipDokter = obj.getString("sip_dokter");
                    }
                    if(obj.has("ttd_dokter")){
                        ttdDokter = obj.getString("ttd_dokter");
                    }
                    if(obj.has("hasil")){
                        hasil = obj.getString("hasil");
                    }
                    if(obj.has("keterangan")){
                        keteranganHasil = obj.getString("keterangan");
                    }


                    PeriksaLab periksaLab = new PeriksaLab();
                    periksaLab.setIdHeaderPemeriksaan(idHeaderPemeriksaan);
                    periksaLab.setIdPeriksaLabDetail(idPeriksaDetail);
                    periksaLab.setNamaPetugas(namaDokter);
                    periksaLab.setIdPetugas(sipDokter);
                    periksaLab.setTtdPetugas(ttdDokter);
                    periksaLab.setLastUpdate(updateTime);
                    periksaLab.setLastUpdateWho(userLogin);
                    periksaLab.setAction("U");
                    periksaLab.setHasil(hasil);
                    periksaLab.setKeteranganHasil(keteranganHasil);

                    if (ttdDokter != null && !"".equalsIgnoreCase(ttdDokter)) {
                        try {
                            BASE64Decoder decoder = new BASE64Decoder();
                            byte[] decodedBytes = decoder.decodeBuffer(ttdDokter);
                            String patten = updateTime.toString().replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
                            String fileName = idHeaderPemeriksaan + "-ttd_petugas-" + patten + ".png";
                            String uploadFile = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_DOKTER + fileName;
                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));

                            if (image == null) {
                                logger.error("Buffered Image is null");
                                response.setStatus("error");
                                response.setMsg("Buffered Image is null");
                            } else {
                                File f = new File(uploadFile);
                                ImageIO.write(image, "png", f);
                                periksaLab.setTtdPetugas(fileName);
                            }
                        } catch (IOException e) {
                            response.setStatus("error");
                            response.setMsg("IO Error" + e.getMessage());
                            return response;
                        }
                    }

                    periksaLabBo.saveDetailParameter(periksaLab);
                    response.setStatus("success");
                    response.setMsg("OK");
                } else {
                    response.setStatus("error");
                    response.setMsg("Error, Data yang dikirim tidak lengkap...!");
                }
            } else {
                response.setStatus("error");
                response.setMsg("Error, Data yang dikirim tidak lengkap...!");
            }
        } catch (Exception e) {
            response.setStatus("error");
            response.setMsg("Error" + e.getMessage());
        }
        logger.info("[PeriksaLabAction.saveDetailParameter] End process >>>");
        return response;
    }

    public CrudResponse saveSelesaiRadiologi(String id) {
        logger.info("[PeriksaLabAction.saveSelesaiRadiologi] start process >>>");
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
        try {
            PeriksaLab periksaLab = new PeriksaLab();
            periksaLab.setIdPeriksaLab(id);
            periksaLab.setLastUpdate(updateTime);
            periksaLab.setLastUpdateWho(userLogin);
            periksaLab.setStatusPeriksa("3");
            periksaLab.setApproveFlag("Y");
            periksaLabBo.saveSelesaiRadiologi(periksaLab);
            response.setStatus("success");
            response.setMsg("OK");
        } catch (Exception e) {
            response.setStatus("error");
            response.setMsg("Error" + e.getMessage());
        }
        logger.info("[PeriksaLabAction.saveSelesaiRadiologi] end process >>>");
        return response;
    }

    public CrudResponse uploadFilePemeriksaan(String data) {
        logger.info("[PeriksaLabAction.uploadFilePemeriksaan] start process >>>");
        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
        if (data != null && !"".equalsIgnoreCase(data)) {
            try {
                JSONObject obj = new JSONObject(data);
                String idHeaderPemeriksaan = obj.getString("id_header_pemeriksaan");
                String stringByte = obj.getString("byte");
                String idPeriksaDetail = obj.getString("id_periksa_detail");
                String namaPeriksaDetail = obj.getString("nama_periksa");
                String tipe = obj.getString("tipe");
                String eks = obj.getString("eks");
                String name = obj.getString("file_name");
                String fileName = "";

                BASE64Decoder decoder = new BASE64Decoder();
                byte[] decodedBytes = decoder.decodeBuffer(stringByte);
                String cekPath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_PEMERIKSAAN;
                File theDir = new File(cekPath);
                if (!theDir.exists()) {
                    theDir.mkdirs();
                }

                if ("pdf".equalsIgnoreCase(eks)) {
                    fileName = idHeaderPemeriksaan + "-" + name + ".pdf";
                    File file = new File(cekPath + fileName);
                    FileOutputStream fop = new FileOutputStream(file);
                    fop.write(decodedBytes);
                    fop.flush();
                    fop.close();
                } else {
                    fileName = idHeaderPemeriksaan + "-" + name + ".jpg";
                    String uploadFile = cekPath + fileName;
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                    if (image == null) {
                        logger.error("Buffered Image is null");
                    } else {
                        response = CommonUtil.compressImage(image, "png", uploadFile);
                    }
                }

                ItSimrsUploadHasilPemeriksaanEntity entity = new ItSimrsUploadHasilPemeriksaanEntity();
                entity.setUrlImg(fileName);
                entity.setTipe(tipe);
                entity.setIdHeaderPemeriksaan(idHeaderPemeriksaan);
                entity.setIdPeriksaLabDetail(idPeriksaDetail);
                entity.setNamaDetailPeriksa(namaPeriksaDetail);
                entity.setFlag("Y");
                entity.setAction("C");
                entity.setCreatedWho(userLogin);
                entity.setCreatedDate(updateTime);
                entity.setLastUpdate(updateTime);
                entity.setLastUpdateWho(userLogin);

                response = periksaLabBo.saveUpload(entity);
                response.setStatus("success");
            } catch (Exception e) {
                logger.error("[PeriksaLabAction.uploadFilePemeriksaan] Error, " + e.getMessage());
                response.setStatus("error");
                response.setMsg("ERROR, " + e.getMessage());
            }
        } else {
            response.setStatus("error");
            response.setMsg("Data yang dikirim tidak lengkap");
        }
        logger.info("[PeriksaLabAction.uploadFilePemeriksaan] end process >>>");
        return response;
    }

    public CrudResponse deleteUploadFilePemeriksaan(String id) {
        logger.info("[PeriksaLabAction.uploadFilePemeriksaan] start process >>>");
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
        if (id != null && !"".equalsIgnoreCase(id)) {
            try {
                periksaLabBo.deleteUpload(id);
                response.setStatus("success");
                response.setMsg("OK");
            } catch (Exception e) {
                logger.error(e.getMessage());
                response.setStatus("error");
                response.setMsg("[PeriksaLabAction.uploadFilePemeriksaan] ERROR, " + e.getMessage());
            }
        }
        logger.info("[PeriksaLabAction.uploadFilePemeriksaan] end process >>>");
        return response;
    }

    public ItSimrsHeaderPemeriksaanEntity getEntityHeaderpemeriksaan(String id) {
        logger.info("[PeriksaLabAction.getEntityHeaderpemeriksaan] start process >>>");
        ItSimrsHeaderPemeriksaanEntity response = new ItSimrsHeaderPemeriksaanEntity();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
        if (id != null && !"".equalsIgnoreCase(id)) {
            try {
                response = periksaLabBo.getEntityHeaderpemeriksaan(id);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        logger.info("[PeriksaLabAction.getEntityHeaderpemeriksaan] end process >>>");
        return response;
    }

    public List<PeriksaLab> getPemeriksaanById(String idHeader) {
        logger.info("[PeriksaLabAction.getPemeriksaanById] start process >>>");
        List<PeriksaLab> periksaLabList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");
        PeriksaLab periksaLab = new PeriksaLab();
        periksaLab.setIdHeaderPemeriksaan(idHeader);
        if (!"".equalsIgnoreCase(idHeader) && idHeader != null) {
            try {
                periksaLabList = periksaLabBo.getByCriteria(periksaLab);
            } catch (GeneralBOException e) {
                logger.error("[PeriksaLabAction.getPemeriksaanById] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            }

        }
        logger.info("[PeriksaLabAction.getPemeriksaanById] end process >>>");
        return periksaLabList;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public void setJenisPriksaPasienBoProxy(JenisPriksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {

        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPeriksaLabBoProxy(PeriksaLabBo periksaLabBoProxy) {
        this.periksaLabBoProxy = periksaLabBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PeriksaLabAction.logger = logger;
    }

    public PeriksaLab getPeriksaLab() {
        return periksaLab;
    }

    public void setPeriksaLab(PeriksaLab periksaLab) {
        this.periksaLab = periksaLab;
    }

}