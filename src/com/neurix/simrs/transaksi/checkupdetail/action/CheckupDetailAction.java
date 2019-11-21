package com.neurix.simrs.transaksi.checkupdetail.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.diagnosa.bo.DiagnosaBo;
import com.neurix.simrs.master.diagnosa.model.Diagnosa;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.ActionError;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CheckupDetailAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(CheckupDetailAction.class);
    private HeaderDetailCheckup headerDetailCheckup;
    private CheckupDetailBo checkupDetailBoProxy;
    private CheckupBo checkupBoProxy;
    private DiagnosaBo diagnosaBoProxy;
    private KategoriTindakanBo kategoriTindakanBoProxy;
    private TindakanBo tindakanBoProxy;

    public TindakanBo getTindakanBoProxy() {
        return tindakanBoProxy;
    }

    public void setTindakanBoProxy(TindakanBo tindakanBoProxy) {
        this.tindakanBoProxy = tindakanBoProxy;
    }

    public KategoriTindakanBo getKategoriTindakanBoProxy() {
        return kategoriTindakanBoProxy;
    }

    public void setKategoriTindakanBoProxy(KategoriTindakanBo kategoriTindakanBoProxy) {
        this.kategoriTindakanBoProxy = kategoriTindakanBoProxy;
    }

    private String id;

    private List<Diagnosa> listOfComboDiagnosa = new ArrayList<>();
    private List<KategoriTindakan> listOfKategoriTindakan = new ArrayList<>();

    public List<KategoriTindakan> getListOfKategoriTindakan() {
        return listOfKategoriTindakan;
    }

    public void setListOfKategoriTindakan(List<KategoriTindakan> listOfKategoriTindakan) {
        this.listOfKategoriTindakan = listOfKategoriTindakan;
    }

    public List<Diagnosa> getListOfComboDiagnosa() {
        return listOfComboDiagnosa;
    }

    public void setListOfComboDiagnosa(List<Diagnosa> listOfComboDiagnosa) {
        this.listOfComboDiagnosa = listOfComboDiagnosa;
    }


    public void setDiagnosaBoProxy(DiagnosaBo diagnosaBoProxy) {
        this.diagnosaBoProxy = diagnosaBoProxy;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public HeaderDetailCheckup getHeaderDetailCheckup() {
        return headerDetailCheckup;
    }

    public void setHeaderDetailCheckup(HeaderDetailCheckup headerDetailCheckup) {
        this.headerDetailCheckup = headerDetailCheckup;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        CheckupDetailAction.logger = logger;
    }

    public CheckupDetailBo getCheckupDetailBoProxy() {
        return checkupDetailBoProxy;
    }

    public void setCheckupDetailBoProxy(CheckupDetailBo checkupDetailBoProxy) {
        this.checkupDetailBoProxy = checkupDetailBoProxy;
    }

    @Override
    public String add() {
        logger.info("[CheckupDetailAction.add] start process >>>");

        //get data from session
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<HeaderDetailCheckup> listOfResult = (List) session.getAttribute("listOfResult");
        List<HeaderDetailCheckup> listOfsearchDetailCheckup = new ArrayList();
        String id = getId();
        String jk = "";
        if (id != null && !"".equalsIgnoreCase(id)) {

            if (listOfResult != null) {

                for (HeaderDetailCheckup detailCheckup : listOfResult) {
                    if (id.equalsIgnoreCase(detailCheckup.getNoCheckup())) {

                        detailCheckup.setStatusPeriksa("1");
                        detailCheckup.setFlag("Y");
                        detailCheckup.setAction("U");
                        detailCheckup.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                        detailCheckup.setLastUpdateWho(CommonUtil.userLogin());

                        try {
                            checkupDetailBoProxy.saveEdit(detailCheckup);
                        } catch (GeneralBOException e){
                            logger.error("[CheckupDetailAction.add] Error when update checkup detail");
                        }

                        HeaderCheckup headerCheckup = getHeaderCheckup(detailCheckup.getNoCheckup());
                        detailCheckup.setIdPasien(headerCheckup.getIdPasien());
                        detailCheckup.setNamaPasien(headerCheckup.getNama());
                        detailCheckup.setAlamat(headerCheckup.getJalan());
                        detailCheckup.setDesa(headerCheckup.getNamaDesa());
                        detailCheckup.setKecamatan(headerCheckup.getNamaKecamatan());
                        detailCheckup.setKota(headerCheckup.getNamaKota());
                        detailCheckup.setProvinsi(headerCheckup.getNamaProvinsi());
                        detailCheckup.setIdPelayanan(headerCheckup.getIdPelayanan());
                        detailCheckup.setNamaPelayanan(headerCheckup.getNamaPelayanan());
                        if(headerCheckup.getJenisKelamin()!= null){
                            if("P".equalsIgnoreCase(headerCheckup.getJenisKelamin())){
                                jk = "Perempuan";
                            }else{
                                jk = "laki-Laki";
                            }
                        }
                        detailCheckup.setJenisKelamin(jk);
                        detailCheckup.setTempatLahir(headerCheckup.getTempatLahir());
                        detailCheckup.setTglLahir(headerCheckup.getTglLahir() == null ? null : headerCheckup.getTglLahir().toString());
                        detailCheckup.setTempatTglLahir(headerCheckup.getTempatLahir()+", "+headerCheckup.getTglLahir().toString());

                        setHeaderDetailCheckup(detailCheckup);

                        break;
                    }
                }

            } else {
                setHeaderDetailCheckup(new HeaderDetailCheckup());
            }
        } else {
            setHeaderDetailCheckup(new HeaderDetailCheckup());
        }

        session.removeAttribute("listOfDataPasien");
        session.setAttribute("listOfDataPasien", listOfsearchDetailCheckup);
        logger.info("[CheckupDetailAction.add] end process <<<");

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
        logger.info("[CheckupAction.search] start process >>>");

        HeaderDetailCheckup headerDetailCheckup = getHeaderDetailCheckup();
        List<HeaderDetailCheckup> listOfsearchHeaderDetailCheckup = new ArrayList();

        try {
            listOfsearchHeaderDetailCheckup = checkupDetailBoProxy.getSearchRawatJalan(headerDetailCheckup);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[CheckupAction.save] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchHeaderDetailCheckup);

        logger.info("[CheckupAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[CheckupDetailAction.initForm] start process >>>");

        HeaderDetailCheckup checkupdetail = new HeaderDetailCheckup();
        setHeaderDetailCheckup(checkupdetail);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[CheckupDetailAction.initForm] end process <<<");

        return "search";
    }

    private HeaderCheckup getHeaderCheckup(String noCheckup){
        logger.info("[CheckupDetailAction.getHeaderCheckup] start process >>>");

        HeaderCheckup headerCheckup = new HeaderCheckup();
        headerCheckup.setNoCheckup(noCheckup);

        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try {
            headerCheckupList = checkupBoProxy.getByCriteria(headerCheckup);
        } catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getHeaderCheckup] Error When Get Header Checkup Data", e);
        }

        HeaderCheckup result = new HeaderCheckup();
        if (!headerCheckupList.isEmpty()){
            result = headerCheckupList.get(0);
        }

        logger.info("[CheckupDetailAction.getHeaderCheckup] end process <<<");
        return result;
    }

    public String getListComboDiagnosa(){
        logger.info("[CheckupDetailAction.getListComboDiagnosa] start process >>>");

        List<Diagnosa> diagnosaList = new ArrayList<>();
        Diagnosa diagnosa = new Diagnosa();

        try {
            diagnosaList = diagnosaBoProxy.getByCriteria(diagnosa);
        }catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getListComboDiagnosa] Error when get diagnosa ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when get diagnosa , please inform to your admin.\n" + e.getMessage());
        }

        listOfComboDiagnosa.addAll(diagnosaList);
        logger.info("[CheckupDetailAction.getListComboDiagnosa] end process <<<");
        return SUCCESS;
    }

    public String getListComboKategoriTindakan(){
        logger.info("[CheckupDetailAction.getListComboKategoriTIndakan] start process >>>");

        List<KategoriTindakan> kategoriTindakanList = new ArrayList<>();
        KategoriTindakan kategoriTindakan = new KategoriTindakan();

        try {
            kategoriTindakanList = kategoriTindakanBoProxy.getByCriteria(kategoriTindakan);
        }catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getListComboKategoriTIndakan] Error when get kategori tindakan ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when get kategori tindakan , please inform to your admin.\n" + e.getMessage());
        }

        listOfKategoriTindakan.addAll(kategoriTindakanList);
        logger.info("[CheckupDetailAction.getListComboKategoriTIndakan] end process <<<");
        return SUCCESS;
    }

    public List<Tindakan> getListComboTindakan(String idKategoriTindakan){
        logger.info("[CheckupDetailAction.listOfDokter] start process >>>");

        List<Tindakan> tindakanList = new ArrayList<>();
        Tindakan tindakan = new Tindakan();
        tindakan.setIdKategoriTindakan(idKategoriTindakan);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");

        try {
            tindakanList = tindakanBo.getByCriteria(tindakan);
        }catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.listOfDokter] Error when searching data, Found problem when searching data, please inform to your admin.", e);
        }

        logger.info("[CheckupDetailAction.listOfDokter] end process >>>");
        return tindakanList;
    }

    public String saveKeterangan (String noCheckup, String idDetailCheckup, String idKtg, String poli, String kelas, String kamar, String idDokter){
        logger.info("[CheckupDetailAction.saveKeterangan] start process >>>");

        String status = "error";
        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
        headerDetailCheckup.setIdDetailCheckup(idDetailCheckup);
        headerDetailCheckup.setStatusPeriksa("3");
        headerDetailCheckup.setFlag("Y");
        headerDetailCheckup.setAction("U");
        headerDetailCheckup.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        headerDetailCheckup.setLastUpdateWho(CommonUtil.userLogin());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("CheckupDetailBoProxy");

        try {
            checkupDetailBo.saveEdit(headerDetailCheckup);
        } catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.saveKeterangan] Error when saving data detail checkup, ", e);
        }

        if ("pindah".equalsIgnoreCase(idKtg)){
            pindahPoli(noCheckup, poli, idDokter);
            status = "sukses";
        } else if ("rujuk".equalsIgnoreCase(idKtg)){
            status = "sukses";
        } else {
            status = "sukses";
        }

        logger.info("[CheckupDetailAction.saveKeterangan] end process >>>");
        return status;
    }

    public void pindahPoli(String noCheckup, String idPoli, String idDokter){
        logger.info("[CheckupDetailAction.pindahPoli] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        CheckupDetailBo checkupDetailBo = (CheckupDetailBo) ctx.getBean("checkupDetailBoProxy");

        Timestamp now = new Timestamp(System.currentTimeMillis());
        String user = CommonUtil.userLogin();

        if (!"".equalsIgnoreCase(noCheckup) &&
                !"".equalsIgnoreCase(idPoli) &&
                !"".equalsIgnoreCase(idDokter)){

            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
            headerDetailCheckup.setNoCheckup(noCheckup);
            headerDetailCheckup.setIdPelayanan(idPoli);
            headerDetailCheckup.setStatusPeriksa("1");
            headerDetailCheckup.setCreatedDate(now);
            headerDetailCheckup.setCreatedWho(user);
            headerDetailCheckup.setLastUpdate(now);
            headerDetailCheckup.setLastUpdateWho(user);
            headerDetailCheckup.setIdDokter(idDokter);

            try {
                checkupDetailBo.saveAdd(headerDetailCheckup);
            } catch (GeneralBOException e){
                logger.error("[CheckupDetailAction.pindahPoli] Error when saving add new detail poli, ", e);
            }
        }
        logger.info("[CheckupDetailAction.pindahPoli] end process >>>");
    }




    @Override
    public String downloadPdf() {
return null;
    }

    @Override
    public String downloadXls() {
return null;
    }
}