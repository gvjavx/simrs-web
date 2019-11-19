package com.neurix.simrs.transaksi.checkupdetail.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.diagnosa.bo.DiagnosaBo;
import com.neurix.simrs.master.diagnosa.model.Diagnosa;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.bo.CheckupDetailBo;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

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

    private String id;

    private List<Diagnosa> listOfComboDiagnosa = new ArrayList<>();

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
                        detailCheckup.setNamaPelayanan(headerCheckup.getNamaPelayanan());
                        detailCheckup.setJenisKelamin(headerCheckup.getJenisKelamin());
                        detailCheckup.setTempatLahir(headerCheckup.getTempatLahir());
                        detailCheckup.setTglLahir(headerCheckup.getTglLahir() == null ? null : headerCheckup.getTglLahir().toString());

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
        logger.info("[TeamDokterAction.getListComboDiagnosa] start process >>>");

        List<Diagnosa> diagnosaList = new ArrayList<>();
        Diagnosa diagnosa = new Diagnosa();

        try {
            diagnosaList = diagnosaBoProxy.getByCriteria(diagnosa);
        }catch (GeneralBOException e){
            logger.error("[TeamDokterAction.getListComboDiagnosa] Error when get diagnosa ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when get diagnosa , please inform to your admin.\n" + e.getMessage());
        }

        listOfComboDiagnosa.addAll(diagnosaList);
        logger.info("[TeamDokterAction.getListComboDiagnosa] end process <<<");
        return SUCCESS;
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