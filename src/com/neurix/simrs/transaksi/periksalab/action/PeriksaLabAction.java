package com.neurix.simrs.transaksi.periksalab.action;

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
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLabDetail;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PeriksaLabAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(PeriksaLabAction.class);
    private PeriksaLab periksaLab;
    private PeriksaLabBo periksaLabBoProxy;
    private CheckupBo checkupBoProxy;
    private CheckupDetailBo checkupDetailBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;
    private String id;

    public void setJenisPriksaPasienBoProxy(JenisPriksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {

        this.checkupDetailBoProxy = checkupDetailBoProxy;
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

    @Override
    public String add() {
        logger.info("[PeriksaLabAction.add] start process >>>");

        //get data from session
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<PeriksaLab> listOfResult = (List) session.getAttribute("listOfResult");

        String id = getId();
        String jk = "";
        if (id != null && !"".equalsIgnoreCase(id)) {

            if (listOfResult != null) {

                for (PeriksaLab periksaLab : listOfResult) {
                    if (id.equalsIgnoreCase(periksaLab.getIdPeriksaLab())) {

                        HeaderDetailCheckup headerDetailCheckup = getDetailCheckup(periksaLab.getIdDetailCheckup());
                        periksaLab.setNoCheckup(headerDetailCheckup.getNoCheckup());

                        HeaderCheckup headerCheckup = getHeaderCheckup(headerDetailCheckup.getNoCheckup());
                        periksaLab.setIdPasien(headerCheckup.getIdPasien());
                        periksaLab.setNamaPasien(headerCheckup.getNama());
                        periksaLab.setAlamat(headerCheckup.getJalan());
                        periksaLab.setDesa(headerCheckup.getNamaDesa());
                        periksaLab.setKecamatan(headerCheckup.getNamaKecamatan());
                        periksaLab.setKota(headerCheckup.getNamaKota());
                        periksaLab.setProvinsi(headerCheckup.getNamaProvinsi());
                        periksaLab.setIdPelayanan(headerCheckup.getIdPelayanan());
                        periksaLab.setNamaPelayanan(headerCheckup.getNamaPelayanan());
                        if(headerCheckup.getJenisKelamin()!= null){
                            if("P".equalsIgnoreCase(headerCheckup.getJenisKelamin())){
                                jk = "Perempuan";
                            }else{
                                jk = "laki-Laki";
                            }
                        }
                        periksaLab.setJenisKelamin(jk);
                        periksaLab.setTempatLahir(headerCheckup.getTempatLahir());
                        periksaLab.setTglLahir(headerCheckup.getTglLahir() == null ? null : headerCheckup.getTglLahir().toString());
                        periksaLab.setTempatTglLahir(headerCheckup.getTempatLahir()+", "+headerCheckup.getTglLahir().toString());
                        periksaLab.setIdJenisPeriksa(headerCheckup.getIdJenisPeriksaPasien());
                        periksaLab.setNik(headerCheckup.getNoKtp());

                        JenisPriksaPasien jenisPriksaPasien = getListJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());
                        periksaLab.setJenisPeriksaPasien(jenisPriksaPasien.getKeterangan());

                        setPeriksaLab(periksaLab);

                        break;
                    }
                }

            } else {
                setPeriksaLab(new PeriksaLab());
            }
        } else {
            setPeriksaLab(new PeriksaLab());
        }

        logger.info("[PeriksaLabAction.add] end process <<<");
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
        logger.info("[PeriksaLabAction.search] start process >>>");

        PeriksaLab periksaLab = getPeriksaLab();
        List<PeriksaLab> listPeriksaLabList = new ArrayList();

        // hanya kategori lab laboratorium saja
        periksaLab.setIdKategoriLab("02");

        try {
            listPeriksaLabList = periksaLabBoProxy.getSearchLab(periksaLab);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PeriksaLabAction.save] Error when searching periksa lab by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
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

        PeriksaLab periksaLab = new PeriksaLab();
        setPeriksaLab(periksaLab);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PeriksaLabAction.initForm] end process <<<");

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

    public String saveOrderLab(String idDetailCheckup, String idLab, List<String> idParameter){
        logger.info("[PeriksaLabAction.saveOrderLab] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            PeriksaLab periksaLab = new PeriksaLab();



            periksaLab.setIdDetailCheckup(idDetailCheckup);
            periksaLab.setIdLab(idLab);
            periksaLab.setCreatedWho(userLogin);
            periksaLab.setLastUpdate(updateTime);
            periksaLab.setCreatedDate(updateTime);
            periksaLab.setLastUpdateWho(userLogin);
            periksaLab.setAction("C");
            periksaLab.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

            periksaLabBo.saveAddWithParameter(periksaLab, idParameter);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PeriksaLabAction.saveOrderLab] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PeriksaLabAction.saveOrderLab] End process >>>");
        return SUCCESS;
    }

    public List<PeriksaLab> listOrderLab(String idDetailCheckup){

        logger.info("[PeriksaLabAction.listOrderLab] start process >>>");
        List<PeriksaLab> periksaLabList = new ArrayList<>();
        PeriksaLab periksaLab = new PeriksaLab();
        periksaLab.setIdDetailCheckup(idDetailCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

        if(!"".equalsIgnoreCase(idDetailCheckup)){
            try {
                periksaLabList = periksaLabBo.getByCriteria(periksaLab);
            }catch (GeneralBOException e){
                logger.error("[PeriksaLabAction.listOrderLab] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[PeriksaLabAction.listOrderLab] start process >>>");
            return periksaLabList;

        }else{
            return null;
        }
    }
    public List<PeriksaLabDetail> listParameterPemeriksaan(String idPeriksaLab){

        logger.info("[PeriksaLabAction.listParameterPemeriksaan] start process >>>");
        List<PeriksaLabDetail> periksaLabDetailList = new ArrayList<>();

        PeriksaLabDetail periksaLabDetail = new PeriksaLabDetail();
        periksaLabDetail.setIdPeriksaLab(idPeriksaLab);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

        if(!"".equalsIgnoreCase(idPeriksaLab)){
            try {
                periksaLabDetailList = periksaLabBo.getListParameterLab(periksaLabDetail);
            }catch (GeneralBOException e){
                logger.error("[PeriksaLabAction.listParameterPemeriksaan] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[PeriksaLabAction.listParameterPemeriksaan] start process >>>");
            return periksaLabDetailList;

        }else{
            return null;
        }
    }

    public String saveEditParameterLab(String idPeriksaLabDetail, String hasil, String keterangan){
        logger.info("[PeriksaLabAction.saveEditParameterLab] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
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

            periksaLabBo.saveUpdateHasilLab(periksaLabDetail);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PeriksaLabAction.saveEditParameterLab] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PeriksaLabAction.saveEditParameterLab] End process >>>");
        return SUCCESS;
    }

    private HeaderCheckup getHeaderCheckup(String noCheckup){
        logger.info("[PeriksaLabAction.getHeaderCheckup] start process >>>");

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try {
            headerCheckupList = checkupBoProxy.getByCriteria(headerCheckup);
        } catch (GeneralBOException e){
            logger.error("[PeriksaLabAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
        }

        HeaderCheckup result = new HeaderCheckup();
        if (!headerCheckupList.isEmpty()){
            result = headerCheckupList.get(0);
        }

        logger.info("[PeriksaLabAction.getHeaderCheckup] end process <<<");
        return result;
    }

    private HeaderDetailCheckup getDetailCheckup(String idDetailCheckup){
        logger.info("[PeriksaLabAction.getDetailCheckup] start process >>>");

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
        detailCheckup.setIdDetailCheckup(idDetailCheckup);

        List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();
        try {
            detailCheckupList = checkupDetailBoProxy.getByCriteria(detailCheckup);
        } catch (GeneralBOException e){
            logger.error("[PeriksaLabAction.getDetailCheckup] Error When Get Header Checkup Data", e);
        }

        HeaderDetailCheckup result = new HeaderDetailCheckup();
        if (!detailCheckupList.isEmpty()){
            result = detailCheckupList.get(0);
        }

        logger.info("[PeriksaLabAction.getDetailCheckup] end process <<<");
        return result;
    }

    private JenisPriksaPasien getListJenisPeriksaPasien(String idJenisPeriksa){
        logger.info("[PeriksaLabAction.getListJenisPeriksaPasien] start process >>>");

        JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();
        jenisPriksaPasien.setIdJenisPeriksaPasien(idJenisPeriksa);

        List<JenisPriksaPasien> jenisPriksaPasienList = new ArrayList<>();
        try {
            jenisPriksaPasienList = jenisPriksaPasienBoProxy.getListAllJenisPeriksa(jenisPriksaPasien);
        } catch (GeneralBOException e){
            logger.error("[PeriksaLabAction.getListJenisPeriksaPasien] Error When Get Jenis Pasien Data", e);
        }

        JenisPriksaPasien result = new JenisPriksaPasien();
        if (!jenisPriksaPasienList.isEmpty()){
            result = jenisPriksaPasienList.get(0);
        }

        logger.info("[PeriksaLabAction.getListJenisPeriksaPasien] end process <<<");
        return result;
    }

    public String editOrderLab(String idPeriksaLab, String idLab, List<String> idParameter){
        logger.info("[PeriksaLabAction.editOrderLab] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            PeriksaLab periksaLab = new PeriksaLab();

            periksaLab.setIdPeriksaLab(idPeriksaLab);
            periksaLab.setIdLab(idLab);
            periksaLab.setLastUpdate(updateTime);
            periksaLab.setLastUpdateWho(userLogin);
            periksaLab.setAction("U");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            PeriksaLabBo periksaLabBo = (PeriksaLabBo) ctx.getBean("periksaLabBoProxy");

            periksaLabBo.saveEdit(periksaLab, idParameter);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PeriksaLabAction.editOrderLab] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[PeriksaLabAction.editOrderLab] End process >>>");
        return SUCCESS;
    }


}