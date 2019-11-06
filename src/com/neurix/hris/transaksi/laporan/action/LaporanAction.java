package com.neurix.hris.transaksi.laporan.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.laporan.bo.LaporanBo;
import com.neurix.hris.transaksi.laporan.model.Laporan;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class LaporanAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(LaporanAction.class);
    private LaporanBo laporanBoProxy;
    private Laporan laporan;

    private File fileUpload;
    private File fileUpload1;
    private String fileUpload1ContentType;
    private String fileUpload1FileName;

    private String fileUploadContentType;
    private String fileUploadFileName;
    private List<Laporan> listOfComboLaporan = new ArrayList<Laporan>();

    public List<Laporan> getListOfComboLaporan() {
        return listOfComboLaporan;
    }

    public void setListOfComboLaporan(List<Laporan> listOfComboLaporan) {
        this.listOfComboLaporan = listOfComboLaporan;
    }

    public File getFileUpload1() {
        return fileUpload1;
    }

    public void setFileUpload1(File fileUpload1) {
        this.fileUpload1 = fileUpload1;
    }

    public String getFileUpload1ContentType() {
        return fileUpload1ContentType;
    }

    public void setFileUpload1ContentType(String fileUpload1ContentType) {
        this.fileUpload1ContentType = fileUpload1ContentType;
    }

    public String getFileUpload1FileName() {
        return fileUpload1FileName;
    }

    public void setFileUpload1FileName(String fileUpload1FileName) {
        this.fileUpload1FileName = fileUpload1FileName;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    private List<Laporan> listComboLaporan = new ArrayList<Laporan>();

    public List<Laporan> getListComboLaporan() {
        return listComboLaporan;
    }

    public void setListComboLaporan(List<Laporan> listComboLaporan) {
        this.listComboLaporan = listComboLaporan;
    }

    public LaporanBo getLaporanBoProxy() {
        return laporanBoProxy;
    }

    public void setLaporanBoProxy(LaporanBo laporanBoProxy) {
        this.laporanBoProxy = laporanBoProxy;
    }

    public Laporan getLaporan() {
        return laporan;
    }

    public void setLaporan(Laporan laporan) {
        this.laporan = laporan;
    }

    private List<Laporan> initComboLaporan;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        LaporanAction.logger = logger;
    }


    public List<Laporan> getInitComboLaporan() {
        return initComboLaporan;
    }

    public void setInitComboLaporan(List<Laporan> initComboLaporan) {
        this.initComboLaporan = initComboLaporan;
    }

    public Laporan init(String kode, String flag){
       /* logger.info("[LaporanAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Laporan> listOfResult = (List<Laporan>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Laporan laporan: listOfResult) {
                    if(kode.equalsIgnoreCase(laporan.getLaporanId()) && flag.equalsIgnoreCase(laporan.getFlag())){
                        setLaporan(laporan);
                        break;
                    }
                }
            } else {
                setLaporan(new Laporan());
            }

            logger.info("[LaporanAction.init] end process >>>");
        }*/
        return getLaporan();
    }

    @Override
    public String add() {
        logger.info("[LaporanAction.add] start process >>>");
        Laporan addLaporan = new Laporan();
        setLaporan(addLaporan);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[LaporanAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[LaporanAction.edit] start process >>>");

        return "init_edit";
    }

    @Override
    public String delete() {


        logger.info("[LaporanAction.delete] end process <<<");

        return "init_delete";
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        return null;
    }

    public String saveLaporan(){
        logger.info("[LaporanAction.saveAdd] start process >>>");
        try {
            Laporan laporan = getLaporan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = laporanBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[laporanAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            //logger.error("[laporanAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            //addActionError("Error, mohon periksa inputan anda kembali");
            laporan.setErrorMessage("Error, " + "[code=" + logId + "] Found problem when saving delete data, please inform to your admin.\n" + e.getMessage());
            //addActionMessage("Error, mohon periksa inputan anda kembali");
            return "input";
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfLaporan");

        return INPUT;
    }

    public String searchLaporan() {
        logger.info("[LaporanAction.search] start process >>>");

        Laporan searchLaporan = new Laporan();
        searchLaporan.setFlag("Y");
        List<Laporan> listOfSearchLaporan = new ArrayList();
        try {
            listOfSearchLaporan = laporanBoProxy.getByCriteria(searchLaporan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = laporanBoProxy.saveErrorMessage(e.getMessage(), "laporanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PositionAction.search] Error when saving error,", e1);
            }
            logger.error("[LaporanAction.save] Error when searching position by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listComboLaporan.addAll(listOfSearchLaporan);
        return SUCCESS;
    }

    @Override
    public String search() {
        logger.info("[LaporanAction.search] start process >>>");

        Laporan searchLaporan = getLaporan();
        List<Laporan> listOfsearchLaporan = new ArrayList();

        try {
            listOfsearchLaporan = laporanBoProxy.getByCriteria(searchLaporan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = laporanBoProxy.saveErrorMessage(e.getMessage(), "LaporanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[LaporanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LaporanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );

            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchLaporan);

        logger.info("[LaporanAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[LaporanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[LaporanAction.initForm] end process >>>");
        return INPUT;
    }

    public String paging(){
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
