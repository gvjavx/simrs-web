package com.neurix.simrs.transaksi.rawatinap.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPriksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class RawatInapAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(RawatInapAction.class);
    private RawatInap rawatInap;
    private RawatInapBo rawatInapBoProxy;
    private CheckupBo checkupBoProxy;
    private JenisPriksaPasienBo jenisPriksaPasienBoProxy;

    public void setJenisPriksaPasienBoProxy(JenisPriksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RawatInapAction.logger = logger;
    }

    public RawatInap getRawatInap() {
        return rawatInap;
    }

    public void setRawatInap(RawatInap rawatInap) {
        this.rawatInap = rawatInap;
    }

    public RawatInapBo getRawatInapBoProxy() {
        return rawatInapBoProxy;
    }

    public void setRawatInapBoProxy(RawatInapBo rawatInapBoProxy) {
        this.rawatInapBoProxy = rawatInapBoProxy;
    }

    @Override
    public String add() {
        logger.info("[RawatInapAction.add] start process >>>");

        //get data from session
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RawatInap> listOfResult = (List) session.getAttribute("listOfResult");

        String id = getId();
        String jk = "";
        if (id != null && !"".equalsIgnoreCase(id)) {

            if (listOfResult != null) {

                for (RawatInap rawatInap : listOfResult) {
                    if (id.equalsIgnoreCase(rawatInap.getNoCheckup())) {

                        HeaderCheckup headerCheckup = getHeaderCheckup(rawatInap.getNoCheckup());
                        rawatInap.setIdPasien(headerCheckup.getIdPasien());
                        rawatInap.setNamaPasien(headerCheckup.getNama());
                        rawatInap.setAlamat(headerCheckup.getJalan());
                        rawatInap.setDesa(headerCheckup.getNamaDesa());
                        rawatInap.setKecamatan(headerCheckup.getNamaKecamatan());
                        rawatInap.setKota(headerCheckup.getNamaKota());
                        rawatInap.setProvinsi(headerCheckup.getNamaProvinsi());
                        rawatInap.setIdPelayanan(headerCheckup.getIdPelayanan());
                        rawatInap.setNamaPelayanan(headerCheckup.getNamaPelayanan());
                        if(headerCheckup.getJenisKelamin()!= null){
                            if("P".equalsIgnoreCase(headerCheckup.getJenisKelamin())){
                                jk = "Perempuan";
                            }else{
                                jk = "laki-Laki";
                            }
                        }
                        rawatInap.setJenisKelamin(jk);
                        rawatInap.setTempatLahir(headerCheckup.getTempatLahir());
                        rawatInap.setTglLahir(headerCheckup.getTglLahir() == null ? null : headerCheckup.getTglLahir().toString());
                        rawatInap.setTempatTglLahir(headerCheckup.getTempatLahir()+", "+headerCheckup.getTglLahir().toString());
                        rawatInap.setIdJenisPeriksa(headerCheckup.getIdJenisPeriksaPasien());
                        rawatInap.setNik(headerCheckup.getNoKtp());
                        rawatInap.setUrlKtp(headerCheckup.getUrlKtp());

                        JenisPriksaPasien jenisPriksaPasien = getListJenisPeriksaPasien(headerCheckup.getIdJenisPeriksaPasien());
                        rawatInap.setJenisPeriksaPasien(jenisPriksaPasien.getKeterangan());

                        setRawatInap(rawatInap);

                        break;
                    }
                }

            } else {
                setRawatInap(new RawatInap());
            }
        } else {
            setRawatInap(new RawatInap());
        }

        logger.info("[RawatInapAction.add] end process <<<");
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

        logger.info("[RawatInapAction.search] start process >>>");

        RawatInap rawatInap = getRawatInap();
        List<RawatInap> listOfRawatInap = new ArrayList();

        try {
            listOfRawatInap = rawatInapBoProxy.getSearchRawatInap(rawatInap);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[RawatInapAction.save] Error when searching rawat inap by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfRawatInap);

        logger.info("[RawatInapAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[RawatInapAction.initForm] start process >>>");

        RawatInap rawatInap = new RawatInap();
        setRawatInap(rawatInap);

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
        logger.info("[RawatInapAction.getHeaderCheckup] start process >>>");

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try {
            headerCheckupList = checkupBoProxy.getByCriteria(headerCheckup);
        } catch (GeneralBOException e){
            logger.error("[RawatInapAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
        }

        HeaderCheckup result = new HeaderCheckup();
        if (!headerCheckupList.isEmpty()){
            result = headerCheckupList.get(0);
        }

        logger.info("[RawatInapAction.getHeaderCheckup] end process <<<");
        return result;
    }

    private JenisPriksaPasien getListJenisPeriksaPasien(String idJenisPeriksa){
        logger.info("[RawatInapAction.getListJenisPeriksaPasien] start process >>>");

        JenisPriksaPasien jenisPriksaPasien = new JenisPriksaPasien();
        jenisPriksaPasien.setIdJenisPeriksaPasien(idJenisPeriksa);

        List<JenisPriksaPasien> jenisPriksaPasienList = new ArrayList<>();
        try {
            jenisPriksaPasienList = jenisPriksaPasienBoProxy.getListAllJenisPeriksa(jenisPriksaPasien);
        } catch (GeneralBOException e){
            logger.error("[RawatInapAction.getListJenisPeriksaPasien] Error When Get Jenis Pasien Data", e);
        }

        JenisPriksaPasien result = new JenisPriksaPasien();
        if (!jenisPriksaPasienList.isEmpty()){
            result = jenisPriksaPasienList.get(0);
        }

        logger.info("[RawatInapAction.getListJenisPeriksaPasien] end process <<<");
        return result;
    }
}