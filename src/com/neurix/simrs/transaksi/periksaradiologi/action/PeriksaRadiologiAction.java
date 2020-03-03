package com.neurix.simrs.transaksi.periksaradiologi.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.periksalab.bo.PeriksaLabBo;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import com.neurix.simrs.transaksi.periksaradiologi.bo.PeriksaRadiologiBo;
import com.neurix.simrs.transaksi.periksaradiologi.model.PeriksaRadiologi;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PeriksaRadiologiAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(PeriksaRadiologiAction.class);
    private PeriksaRadiologi periksaRadiologi;
    private PeriksaLab periksaLab;
    private PeriksaLabBo periksaLabBoProxy;
    private String id;
    private CheckupBo checkupBoProxy;
    private CheckupDetailBo checkupDetailBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private PeriksaRadiologiBo periksaRadiologiBoProxy;

    private String lab;

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public void setPeriksaRadiologiBoProxy(PeriksaRadiologiBo periksaRadiologiBoProxy) {
        this.periksaRadiologiBoProxy = periksaRadiologiBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    public void setJenisPriksaPasienBoProxy(JenisPriksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setPeriksaLabBoProxy(PeriksaLabBo periksaLabBoProxy) {
        this.periksaLabBoProxy = periksaLabBoProxy;
    }

    public PeriksaLab getPeriksaLab() {
        return periksaLab;
    }

    public void setPeriksaLab(PeriksaLab periksaLab) {
        this.periksaLab = periksaLab;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PeriksaRadiologiAction.logger = logger;
    }

    public PeriksaRadiologi getPeriksaRadiologi() {
        return periksaRadiologi;
    }

    public void setPeriksaRadiologi(PeriksaRadiologi periksaRadiologi) {
        this.periksaRadiologi = periksaRadiologi;
    }


    @Override
    public String add() {
        logger.info("[PeriksaRadiologiAction.add] start process >>>");

        //get data from session
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        List<PeriksaLab> listOfResult = (List) session.getAttribute("listOfResult");

        String id = getId();
        String lab = getLab();
        String jk = "";
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
                        jk = "laki-Laki";
                    }
                }
                periksaLab.setJenisKelamin(jk);
                periksaLab.setTempatLahir(checkup.getTempatLahir());
                periksaLab.setTglLahir(checkup.getTglLahir() == null ? null : checkup.getTglLahir().toString());
                String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(checkup.getTglLahir());
                periksaLab.setTempatTglLahir(checkup.getTempatLahir() + ", " + formatDate);
                periksaLab.setIdJenisPeriksa(checkup.getIdJenisPeriksaPasien());
                periksaLab.setNik(checkup.getNoKtp());
                periksaLab.setUrlKtp(checkup.getUrlKtp());
                periksaLab.setJenisPeriksaPasien(checkup.getStatusPeriksaName());
                periksaLab.setIdPeriksaLab(lab);
                setPeriksaLab(periksaLab);

            } else {
                setPeriksaLab(new PeriksaLab());
            }
//            if (listOfResult != null) {
//
//                for (PeriksaLab periksaLab : listOfResult) {
//                    if (id.equalsIgnoreCase(periksaLab.getIdPeriksaLab())) {
//
//                        HeaderDetailCheckup headerDetailCheckup = getDetailCheckup(periksaLab.getIdDetailCheckup());
//                        periksaLab.setNoCheckup(headerDetailCheckup.getNoCheckup());
//
//                        HeaderCheckup headerCheckup = getHeaderCheckup(headerDetailCheckup.getNoCheckup());
//                        periksaLab.setIdPasien(headerCheckup.getIdPasien());
//                        periksaLab.setNamaPasien(headerCheckup.getNama());
//                        periksaLab.setAlamat(headerCheckup.getJalan());
//                        periksaLab.setDesa(headerCheckup.getNamaDesa());
//                        periksaLab.setKecamatan(headerCheckup.getNamaKecamatan());
//                        periksaLab.setKota(headerCheckup.getNamaKota());
//                        periksaLab.setProvinsi(headerCheckup.getNamaProvinsi());
//                        periksaLab.setIdPelayanan(headerCheckup.getIdPelayanan());
//                        periksaLab.setNamaPelayanan(headerCheckup.getNamaPelayanan());
//                        if(headerCheckup.getJenisKelamin()!= null){
//                            if("P".equalsIgnoreCase(headerCheckup.getJenisKelamin())){
//                                jk = "Perempuan";
//                            }else{
//                                jk = "laki-Laki";
//                            }
//                        }
//                        periksaLab.setJenisKelamin(jk);
//                        periksaLab.setTempatLahir(headerCheckup.getTempatLahir());
//                        periksaLab.setTglLahir(headerCheckup.getTglLahir() == null ? null : headerCheckup.getTglLahir().toString());
//                        String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(headerCheckup.getTglLahir());
//                        periksaLab.setTempatTglLahir(headerCheckup.getTempatLahir()+", "+formatDate);
//                        periksaLab.setIdJenisPeriksa(headerCheckup.getIdJenisPeriksaPasien());
//                        periksaLab.setNik(headerCheckup.getNoKtp());
//                        periksaLab.setUrlKtp(headerCheckup.getUrlKtp());
//
//                        JenisPriksaPasien jenisPriksaPasien = getListJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());
//                        periksaLab.setJenisPeriksaPasien(jenisPriksaPasien.getKeterangan());
//
//                        setPeriksaLab(periksaLab);
//
//                        break;
//                    }
//                }
//
//            } else {
//                setPeriksaLab(new PeriksaLab());
//            }
//        } else {
//            setPeriksaLab(new PeriksaLab());
        }

        logger.info("[PeriksaRadiologiAction.add] end process <<<");
        return "init_add";
    }

    @Override
    public String edit() {
        return "init_edit";
    }

    @Override
    public String delete() {
        return "init_delete";
    }

    @Override
    public String view() {
        return "init_view";
    }

    @Override
    public String save() {
        return "init_save";
    }

    @Override
    public String search() {
        logger.info("[PeriksaRadiologiAction.search] start process >>>");

        PeriksaLab periksaLab = getPeriksaLab();
        List<PeriksaLab> listPeriksaLabList = new ArrayList();

        // hanya kategori lab radiologi saja
        periksaLab.setIdKategoriLab("01");

        try {
            listPeriksaLabList = periksaLabBoProxy.getSearchLab(periksaLab);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PeriksaRadiologiAction.search] Error when searching periksa radilogi by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listPeriksaLabList);

        logger.info("[PeriksaRadiologiAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[RawatInapAction.initForm] start process >>>");

        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        String tglToday = new SimpleDateFormat("dd-MM-yyyy").format(date);

        PeriksaLab periksaLab = new PeriksaLab();
        periksaLab.setStTglTo(tglToday);
        periksaLab.setStTglFrom(tglToday);
        setPeriksaLab(periksaLab);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[RawatInapAction.initForm] end process <<<");

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

    private HeaderCheckup getHeaderCheckup(String noCheckup){
        logger.info("[PeriksaRadiologiAction.getHeaderCheckup] start process >>>");

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try {
            headerCheckupList = checkupBoProxy.getByCriteria(headerCheckup);
        } catch (GeneralBOException e){
            logger.error("[PeriksaRadiologiAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
        }

        HeaderCheckup result = new HeaderCheckup();
        if (!headerCheckupList.isEmpty()){
            result = headerCheckupList.get(0);
        }

        logger.info("[PeriksaRadiologiAction.getHeaderCheckup] end process <<<");
        return result;
    }

    private HeaderDetailCheckup getDetailCheckup(String idDetailCheckup){
        logger.info("[PeriksaRadiologiAction.getDetailCheckup] start process >>>");

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        detailCheckup.setIdDetailCheckup(idDetailCheckup);

        List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();
        try {
            detailCheckupList = checkupDetailBoProxy.getByCriteria(detailCheckup);
        } catch (GeneralBOException e){
            logger.error("[PeriksaRadiologiAction.getDetailCheckup] Error When Get Header Checkup Data", e);
        }

        HeaderDetailCheckup result = new HeaderDetailCheckup();
        if (!detailCheckupList.isEmpty()){
            result = detailCheckupList.get(0);
        }

        logger.info("[PeriksaRadiologiAction.getDetailCheckup] end process <<<");
        return result;
    }

    private JenisPriksaPasien getListJenisPeriksaPasien(String idJenisPeriksa){
        logger.info("[PeriksaRadiologiAction.getListJenisPeriksaPasien] start process >>>");

        JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();
        jenisPriksaPasien.setIdJenisPeriksaPasien(idJenisPeriksa);

        List<JenisPriksaPasien> jenisPriksaPasienList = new ArrayList<>();
        try {
            jenisPriksaPasienList = jenisPriksaPasienBoProxy.getListAllJenisPeriksa(jenisPriksaPasien);
        } catch (GeneralBOException e){
            logger.error("[PeriksaRadiologiAction.getListJenisPeriksaPasien] Error When Get Jenis Pasien Data", e);
        }

        JenisPriksaPasien result = new JenisPriksaPasien();
        if (!jenisPriksaPasienList.isEmpty()){
            result = jenisPriksaPasienList.get(0);
        }

        logger.info("[PeriksaRadiologiAction.getListJenisPeriksaPasien] end process <<<");
        return result;
    }

    public String saveRadiologi(String idPeriksaRadiologi, String idDokter, String kesimpulan){

        logger.info("[PeriksaRadiologiAction.saveRadiologi] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            PeriksaRadiologi periksaRadiologi = new PeriksaRadiologi();
            periksaRadiologi.setIdPeriksaRadiologi(idPeriksaRadiologi);
            periksaRadiologi.setIdDokterRadiologi(idDokter);
            periksaRadiologi.setKesimpulan(kesimpulan);
            periksaRadiologi.setLastUpdate(updateTime);
            periksaRadiologi.setLastUpdateWho(userLogin);
            periksaRadiologi.setAction("U");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PeriksaRadiologiBo periksaRadiologiBo = (PeriksaRadiologiBo) ctx.getBean("periksaRadiologiBoProxy");

            periksaRadiologiBo.saveEdit(periksaRadiologi);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PeriksaRadiologiAction.saveRadiologi] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        logger.info("[PeriksaRadiologiAction.saveRadiologi] end process >>>");
        return SUCCESS;
    }

    public List<PeriksaRadiologi> getIdPemeriksaRadiologi(String idPeriksaLab){

        logger.info("[PeriksaLabAction.listParameterPemeriksaan] start process >>>");
        List<PeriksaRadiologi> periksaRadiologilList = new ArrayList<>();

        PeriksaRadiologi periksaRadiologi = new PeriksaRadiologi();
        periksaRadiologi.setIdPeriksaLab(idPeriksaLab);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaRadiologiBo periksaRadiologiBo = (PeriksaRadiologiBo) ctx.getBean("periksaRadiologiBoProxy");

        if(!"".equalsIgnoreCase(idPeriksaLab)){
            try {
                periksaRadiologilList = periksaRadiologiBo.getListPeriksaRadioLogiByCriteria(periksaRadiologi);
            }catch (GeneralBOException e){
                logger.error("[PeriksaLabAction.listParameterPemeriksaan] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[PeriksaLabAction.listParameterPemeriksaan] start process >>>");
            return periksaRadiologilList;

        }else{
            return null;
        }
    }


    public PeriksaRadiologiBo getPeriksaRadiologiBoProxy() {
        return periksaRadiologiBoProxy;
    }
}