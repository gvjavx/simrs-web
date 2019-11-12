package com.neurix.simrs.transaksi.checkup.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPerksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.transaksi.checkup.bo.CheckupBo;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CheckupAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(CheckupAction.class);
    private CheckupBo checkupBoProxy;
    private PelayananBo pelayananBoProxy;
    private JenisPerksaPasienBo jenisPriksaPasienBoProxy;

    private List<JenisPriksaPasien> listOfJenisPriksaPasien = new ArrayList<>();
    private List<Pelayanan> listOfPelayanan = new ArrayList<>();

    private HeaderCheckup headerCheckup;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        CheckupAction.logger = logger;
    }

    public CheckupBo getCheckupBoProxy() {
        return checkupBoProxy;
    }

    public void setCheckupBoProxy(CheckupBo checkupBoProxy) {
        this.checkupBoProxy = checkupBoProxy;
    }

    public HeaderCheckup getHeaderCheckup() {
        return headerCheckup;
    }

    public void setHeaderCheckup(HeaderCheckup headerCheckup) {
        this.headerCheckup = headerCheckup;
    }

    public void setPelayananBoProxy(PelayananBo pelayananBoProxy) {
        this.pelayananBoProxy = pelayananBoProxy;
    }

    public void setJenisPriksaPasienBoProxy(JenisPerksaPasienBo jenisPriksaPasienBoProxy) {
        this.jenisPriksaPasienBoProxy = jenisPriksaPasienBoProxy;
    }

    public PelayananBo getPelayananBoProxy() {
        return pelayananBoProxy;
    }

    public JenisPerksaPasienBo getJenisPriksaPasienBoProxy() {
        return jenisPriksaPasienBoProxy;
    }

    public List<JenisPriksaPasien> getListOfJenisPriksaPasien() {
        return listOfJenisPriksaPasien;
    }

    public void setListOfJenisPriksaPasien(List<JenisPriksaPasien> listOfJenisPriksaPasien) {
        this.listOfJenisPriksaPasien = listOfJenisPriksaPasien;
    }

    public List<Pelayanan> getListOfPelayanan() {
        return listOfPelayanan;
    }

    public void setListOfPelayanan(List<Pelayanan> listOfPelayanan) {
        this.listOfPelayanan = listOfPelayanan;
    }

    @Override
    public String add() {
        return "add";
    }

    @Override
    public String edit() {
        return "edit";
    }

    @Override
    public String delete() {
        return "delete";
    }

    @Override
    public String view() {
        return "view";
    }

    @Override
    public String save() {
        return "save";
    }

    @Override
    public String search() {
        logger.info("[CheckupAction.search] start process >>>");

        HeaderCheckup headerCheckup = getHeaderCheckup();
        List<HeaderCheckup> listOfsearchHeaderCheckup = new ArrayList();

        try {
            listOfsearchHeaderCheckup = checkupBoProxy.getByCriteria(headerCheckup);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = checkupBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[CheckupAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CheckupAction.save] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchHeaderCheckup);

        logger.info("[CheckupAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[CheckupAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[CheckupAction.initForm] end process >>>");
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

    public String saveAdd(){

        logger.info("[CheckupAction.saveAdd] start process >>>");
        try {
            HeaderCheckup checkup = getHeaderCheckup();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            checkup.setCreatedWho(userLogin);
            checkup.setLastUpdate(updateTime);
            checkup.setCreatedDate(updateTime);
            checkup.setLastUpdateWho(userLogin);
            checkup.setAction("C");
            checkup.setFlag("Y");

            checkupBoProxy.saveAdd(checkup);
        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[CheckupAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[CheckupAction.saveAdd] end process >>>");
        return "search";

    }

    public String getComboJenisPeriksaPasien(){
        List<JenisPriksaPasien> lisJenisPeriksa = new ArrayList<>();

        try {
            lisJenisPeriksa = jenisPriksaPasienBoProxy.getListAllJenisPeriksa();
        } catch (HibernateException e){
            logger.error("[CheckupAction.getComboJenisPeriksaPasien] Error when get data for combo listOfJenisPriksaPasien", e);
            addActionError(" Error when get data for combo listOfJenisPriksaPasien" + e.getMessage());
        }

        listOfJenisPriksaPasien.addAll(lisJenisPeriksa);
        return "add";
    }

    public String getComboPelayanan(){
        List<Pelayanan> pelayananList = new ArrayList<>();

        try {
            pelayananList = pelayananBoProxy.getListAllPelayanan();
        } catch (HibernateException e){
            logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
            addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
        }

        listOfPelayanan.addAll(pelayananList);
        return "add";
    }
}