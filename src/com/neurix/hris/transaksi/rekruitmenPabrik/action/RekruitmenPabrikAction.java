package com.neurix.hris.transaksi.rekruitmenPabrik.action;

//import com.neurix.authorization.company.bo.AreaBo;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.positionBagian.bo.PositionBagianBo;
import com.neurix.hris.master.positionBagian.model.PositionBagian;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.hris.transaksi.rekruitmenPabrik.bo.RekruitmenPabrikBo;
import com.neurix.hris.transaksi.rekruitmenPabrik.model.RekruitmenPabrik;
import com.neurix.hris.transaksi.rekruitmenPabrik.model.RekruitmenPabrikDetail;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class RekruitmenPabrikAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(RekruitmenPabrikAction.class);
    private RekruitmenPabrikBo rekruitmenPabrikBoProxy;
    private RekruitmenPabrik rekruitmenPabrik;
    private BiodataBo biodataBoProxy;
    private PositionBagianBo positionBagianBoProxy;
    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;
    private RekruitmenPabrikDetail rekruitmenPabrikDetail;
    private Biodata biodata;
    boolean admin=false;

    public PositionBagianBo getPositionBagianBoProxy() {
        return positionBagianBoProxy;
    }

    public void setPositionBagianBoProxy(PositionBagianBo positionBagianBoProxy) {
        this.positionBagianBoProxy = positionBagianBoProxy;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Biodata getBiodata() {
        return biodata;
    }

    public void setBiodata(Biodata biodata) {
        this.biodata = biodata;
    }

    public BiodataBo getBiodataBoProxy() {
        return biodataBoProxy;
    }

    public void setBiodataBoProxy(BiodataBo biodataBoProxy) {
        this.biodataBoProxy = biodataBoProxy;
    }

    public RekruitmenPabrik getRekruitmenPabrik() {
        return rekruitmenPabrik;
    }

    public void setRekruitmenPabrik(RekruitmenPabrik rekruitmenPabrik) {
        this.rekruitmenPabrik = rekruitmenPabrik;
    }

    public RekruitmenPabrikDetail getRekruitmenPabrikDetail() {
        return rekruitmenPabrikDetail;
    }

    public void setRekruitmenPabrikDetail(RekruitmenPabrikDetail rekruitmenPabrikDetail) {
        this.rekruitmenPabrikDetail = rekruitmenPabrikDetail;
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

    private List<RekruitmenPabrik> initComboRekruitmen;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RekruitmenPabrikAction.logger = logger;
    }


    public List<RekruitmenPabrik> getInitComboRekruitmen() {
        return initComboRekruitmen;
    }

    public void setInitComboRekruitmen(List<RekruitmenPabrik> initComboRekruitmen) {
        this.initComboRekruitmen = initComboRekruitmen;
    }

    public String paging(){
        return SUCCESS;
    }

    public RekruitmenPabrik init(String kode, String flag){
        logger.info("[RekruitmenPabrikAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RekruitmenPabrik> listOfResult = (List<RekruitmenPabrik>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (RekruitmenPabrik rekruitmenPabrik: listOfResult) {
                    if(kode.equalsIgnoreCase(rekruitmenPabrik.getRekruitmenPabrikId()) && flag.equalsIgnoreCase(rekruitmenPabrik.getFlag())){
                        setRekruitmenPabrik(rekruitmenPabrik);
                        break;
                    }
                }
            } else {
                setRekruitmenPabrik(new RekruitmenPabrik());
            }

            logger.info("[RekruitmenPabrikAction.init] end process >>>");
        }
        return getRekruitmenPabrik();
    }

    @Override
    public String add() {
        logger.info("[RekruitmenAction.add] start process >>>");
        RekruitmenPabrik searchRekruitmenPabrik = new RekruitmenPabrik();
        RekruitmenPabrik addRekruitmenPabrik = new RekruitmenPabrik();
        searchRekruitmenPabrik.setRekruitmenPabrikId(getId());
        List<RekruitmenPabrik> rekruitmenPabrikList = rekruitmenPabrikBoProxy.getByCriteria(searchRekruitmenPabrik);
        for (RekruitmenPabrik rekruitmenPabrik : rekruitmenPabrikList){
            addRekruitmenPabrik.setBranchId(rekruitmenPabrik.getBranchId());
            addRekruitmenPabrik.setMt(rekruitmenPabrik.getMt());
            addRekruitmenPabrik.setBagianId(rekruitmenPabrik.getBagianId());
            addRekruitmenPabrik.setKuota(rekruitmenPabrik.getKuota());
            addRekruitmenPabrik.setRekruitmenPabrikId(rekruitmenPabrik.getRekruitmenPabrikId());
        }
        setRekruitmenPabrik(addRekruitmenPabrik);
        setAddOrEdit(true);
        setAdd(true);
        logger.info("[RekruitmenAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[RekruitmenPabrikAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        RekruitmenPabrik editRekruitmenPabrik = new RekruitmenPabrik();

        if(itemFlag != null){
            try {
                editRekruitmenPabrik = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "rekruitmenBoImpl.getRekruitmenPabrikByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[IjinAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[IjinAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editRekruitmenPabrik != null) {
                setRekruitmenPabrik(editRekruitmenPabrik);
            } else {
                editRekruitmenPabrik.setFlag(itemFlag);
                editRekruitmenPabrik.setRekruitmenPabrikId(itemId);
                setRekruitmenPabrik(editRekruitmenPabrik);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editRekruitmenPabrik.setRekruitmenPabrikId(itemId);
            editRekruitmenPabrik.setFlag(getFlag());
            setRekruitmenPabrik(editRekruitmenPabrik);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        HttpSession session = ServletActionContext.getRequest().getSession();

        List<RekruitmenPabrikDetail> listOfsearchRekruitmenPabrikDetail = new ArrayList();
        listOfsearchRekruitmenPabrikDetail = (List<RekruitmenPabrikDetail>) session.getAttribute("ListOfResultRekruitmenPabrikDetail");

        if (listOfsearchRekruitmenPabrikDetail==null){
            listOfsearchRekruitmenPabrikDetail = rekruitmenPabrikBoProxy.getByCriteriaDetail(editRekruitmenPabrik);
            session.removeAttribute("ListOfResultRekruitmenPabrikDetail");
            session.setAttribute("ListOfResultRekruitmenPabrikDetail", listOfsearchRekruitmenPabrikDetail);
        }

        logger.info("[IjinAction.edit] end process >>>");
        return "init_edit";
    }


    @Override
    public String delete() {
        logger.info("[RekruitmenPabrikAction.delete] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        RekruitmenPabrik editRekruitmenPabrik = new RekruitmenPabrik();

        if(itemFlag != null){
            try {
                editRekruitmenPabrik = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "rekruitmenBoImpl.getRekruitmenPabrikByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[IjinAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[IjinAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editRekruitmenPabrik != null) {
                setRekruitmenPabrik(editRekruitmenPabrik);
            } else {
                editRekruitmenPabrik.setFlag(itemFlag);
                editRekruitmenPabrik.setRekruitmenPabrikId(itemId);
                setRekruitmenPabrik(editRekruitmenPabrik);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editRekruitmenPabrik.setRekruitmenPabrikId(itemId);
            editRekruitmenPabrik.setFlag(getFlag());
            setRekruitmenPabrik(editRekruitmenPabrik);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        List<RekruitmenPabrikDetail> listOfsearchRekruitmenPabrikDetail = new ArrayList();
        listOfsearchRekruitmenPabrikDetail = rekruitmenPabrikBoProxy.getByCriteriaDetail(editRekruitmenPabrik);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("ListOfResultRekruitmenPabrikDetail");
        session.setAttribute("ListOfResultRekruitmenPabrikDetail", listOfsearchRekruitmenPabrikDetail);

        logger.info("[IjinAction.edit] end process >>>");
        return "init_delete";
    }
    public String saveDelete(){
        logger.info("[RekruitmenPabrikAction.saveDelete] start process >>>");
        try {

            RekruitmenPabrik deleteRekruitmenPabrik = getRekruitmenPabrik();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteRekruitmenPabrik.setLastUpdate(updateTime);
            deleteRekruitmenPabrik.setLastUpdateWho(userLogin);
            deleteRekruitmenPabrik.setAction("U");
            deleteRekruitmenPabrik.setFlag("N");

            rekruitmenPabrikBoProxy.saveDelete(deleteRekruitmenPabrik);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenPabrikAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RekruitmenPabrikAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[RekruitmenPabrikAction.saveDelete] end process <<<");

        return "success_save_delete";
    }
    @Override
    public String view() {
            logger.info("[RekruitmenPabrikAction.edit] start process >>>");
            String itemId = getId();
            String itemFlag = getFlag();

            RekruitmenPabrik editRekruitmenPabrik = new RekruitmenPabrik();

            if(itemFlag != null){
                try {
                    editRekruitmenPabrik = init(itemId, itemFlag);
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "rekruitmenBoImpl.getRekruitmenPabrikByCriteria");
                    } catch (GeneralBOException e1) {
                        logger.error("[IjinAction.edit] Error when retrieving edit data,", e1);
                    }
                    logger.error("[IjinAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                    return "failure";
                }

                if(editRekruitmenPabrik != null) {
                    setRekruitmenPabrik(editRekruitmenPabrik);
                } else {
                    editRekruitmenPabrik.setFlag(itemFlag);
                    editRekruitmenPabrik.setRekruitmenPabrikId(itemId);
                    setRekruitmenPabrik(editRekruitmenPabrik);
                    addActionError("Error, Unable to find data with id = " + itemId);
                    return "failure";
                }
            } else {
                editRekruitmenPabrik.setRekruitmenPabrikId(itemId);
                editRekruitmenPabrik.setFlag(getFlag());
                setRekruitmenPabrik(editRekruitmenPabrik);
                addActionError("Error, Unable to edit again with flag = N.");
                return "failure";
            }

            setAddOrEdit(true);
            List<RekruitmenPabrikDetail> listOfsearchRekruitmenPabrikDetail = new ArrayList();
            listOfsearchRekruitmenPabrikDetail = rekruitmenPabrikBoProxy.getByCriteriaDetail(editRekruitmenPabrik);
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.removeAttribute("ListOfResultRekruitmenPabrikDetail");
            session.setAttribute("ListOfResultRekruitmenPabrikDetail", listOfsearchRekruitmenPabrikDetail);

            logger.info("[IjinAction.edit] end process >>>");
            return "init_view";
    }
    public String cetakKontrak() {
        logger.info("[RekruitmenPabrikAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        RekruitmenPabrik editRekruitmenPabrik = new RekruitmenPabrik();

        if(itemFlag != null){
            try {
                editRekruitmenPabrik = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "rekruitmenBoImpl.getRekruitmenPabrikByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[IjinAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[IjinAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editRekruitmenPabrik != null) {
                setRekruitmenPabrik(editRekruitmenPabrik);
            } else {
                editRekruitmenPabrik.setFlag(itemFlag);
                editRekruitmenPabrik.setRekruitmenPabrikId(itemId);
                setRekruitmenPabrik(editRekruitmenPabrik);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editRekruitmenPabrik.setRekruitmenPabrikId(itemId);
            editRekruitmenPabrik.setFlag(getFlag());
            setRekruitmenPabrik(editRekruitmenPabrik);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        List<RekruitmenPabrikDetail> listOfsearchRekruitmenPabrikDetail = new ArrayList();
        listOfsearchRekruitmenPabrikDetail = rekruitmenPabrikBoProxy.getByCriteriaDetail(editRekruitmenPabrik);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("ListOfResultRekruitmenPabrikDetail");
        session.setAttribute("ListOfResultRekruitmenPabrikDetail", listOfsearchRekruitmenPabrikDetail);

        logger.info("[IjinAction.edit] end process >>>");
        return "init_cetak";
    }
    @Override
    public String save() {
        logger.info("[saveAdd] start process >>>");
        RekruitmenPabrikDetail rekruitmenPabrikDetail = getRekruitmenPabrikDetail();
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            rekruitmenPabrikDetail.setCreatedWho(userLogin);
            rekruitmenPabrikDetail.setLastUpdate(updateTime);
            rekruitmenPabrikDetail.setCreatedDate(updateTime);
            rekruitmenPabrikDetail.setLastUpdateWho(userLogin);
            rekruitmenPabrikDetail.setAction("C");
            rekruitmenPabrikDetail.setFlag("Y");
            rekruitmenPabrikBoProxy.saveAdd(rekruitmenPabrikDetail);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("ListOfResultRekruitmenPabrikDetail");

        logger.info("[liburAction.saveAdd] end process >>>");
        return null;
    }

    public String saveKuota(String masaTanam,String unit ,String bagian , String kuota) {
        logger.info("[saveKuota] start process >>>");
        try {
            RekruitmenPabrik rekruitmenPabrik = new RekruitmenPabrik();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            rekruitmenPabrik.setMt(masaTanam);
            rekruitmenPabrik.setBranchId(unit);
            rekruitmenPabrik.setBagianId(bagian);
            rekruitmenPabrik.setKuota(Integer.parseInt(kuota));
            rekruitmenPabrik.setCreatedWho(userLogin);
            rekruitmenPabrik.setLastUpdate(updateTime);
            rekruitmenPabrik.setCreatedDate(updateTime);
            rekruitmenPabrik.setLastUpdateWho(userLogin);
            rekruitmenPabrik.setAction("C");
            rekruitmenPabrik.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            RekruitmenPabrikBo rekruitmenPabrikBo = (RekruitmenPabrikBo) ctx.getBean("rekruitmenPabrikBoProxy");
            rekruitmenPabrikBo.saveAdd(rekruitmenPabrik);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("ListOfResultRekruitmenPabrikDetail");

        logger.info("[liburAction.saveAdd] end process >>>");
        return null;
    }
    public String saveEdit() {
        logger.info("[saveEdit] start process >>>");

        try {
            RekruitmenPabrik rekruitmenPabrik = getRekruitmenPabrik();
            RekruitmenPabrikDetail rekruitmenPabrikDetail = new RekruitmenPabrikDetail();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            rekruitmenPabrik.setCreatedWho(userLogin);
            rekruitmenPabrik.setLastUpdate(updateTime);
            rekruitmenPabrik.setCreatedDate(updateTime);
            rekruitmenPabrik.setLastUpdateWho(userLogin);
            rekruitmenPabrik.setAction("C");
            rekruitmenPabrik.setFlag("Y");

            rekruitmenPabrikDetail.setCreatedWho(userLogin);
            rekruitmenPabrikDetail.setLastUpdate(updateTime);
            rekruitmenPabrikDetail.setCreatedDate(updateTime);
            rekruitmenPabrikDetail.setLastUpdateWho(userLogin);
            rekruitmenPabrikDetail.setAction("C");
            rekruitmenPabrikDetail.setFlag("Y");
            rekruitmenPabrikBoProxy.saveEdit(rekruitmenPabrik, rekruitmenPabrikDetail);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("ListOfResultRekruitmenPabrikDetail");

        logger.info("[liburAction.saveAdd] end process >>>");
        return null;
    }

    public String saveApprove(){
        logger.info("[IjinAction.saveEdit] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RekruitmenPabrikDetail> listOfsearchRekruitmenDetail = (List<RekruitmenPabrikDetail>) session.getAttribute("ListOfResultRekruitmenPabrikDetail");
        if(listOfsearchRekruitmenDetail != null){
            for (RekruitmenPabrikDetail rekruitmenPabrikDetail: listOfsearchRekruitmenDetail) {
                String userLogin = CommonUtil.userLogin();
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                rekruitmenPabrikDetail.setLastUpdateWho(userLogin);
                rekruitmenPabrikDetail.setLastUpdate(updateTime);
                rekruitmenPabrikDetail.setAction("U");
                rekruitmenPabrikDetail.setFlag("Y");

                try {
                    rekruitmenPabrikBoProxy.saveApprove(rekruitmenPabrikDetail);
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.saveEdit");
                    } catch (GeneralBOException e1) {
                        logger.error("[IjinAction.saveEdit] Error when saving error,", e1);
                        return ERROR;
                    }
                    logger.error("[IjinAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                    return ERROR;
                }
            }
        }
        logger.info("[IjinAction.saveEdit] end process <<<");

        return null;
    }
    public String saveTmpPegawai(String nip){
        logger.info("[RekruimentPabrikAction.saveAddStudy] start process >>>");
        List<RekruitmenPabrikDetail> listOfResult = new ArrayList();
        List<RekruitmenPabrikDetail> listComboRekruitmenPabrikDetail = null;
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Biodata> listOfsearchBiodata = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        Biodata searchBiodata = new Biodata();
        searchBiodata.setNip(nip);
        searchBiodata.setFlag("Y");
        try {
            listOfsearchBiodata = biodataBo.getByCriteria(searchBiodata);
            for (Biodata biodata : listOfsearchBiodata){
                RekruitmenPabrikDetail returnRekruitmenPabrikDetail = new RekruitmenPabrikDetail();
                returnRekruitmenPabrikDetail.setNip(nip);
                returnRekruitmenPabrikDetail.setPosisiLama(biodata.getPositionId());
                returnRekruitmenPabrikDetail.setPosisiLamaName(biodata.getPositionName());
                returnRekruitmenPabrikDetail.setNamaPegawai(biodata.getNamaPegawai());
                returnRekruitmenPabrikDetail.setPosisiBaru(biodata.getPositionId());
                returnRekruitmenPabrikDetail.setPosisiBaruName(biodata.getPositionName());
                if (biodata.getMasaGiling()==null){
                    returnRekruitmenPabrikDetail.setStatusGiling("");
                }
                returnRekruitmenPabrikDetail.setStatusGiling(biodata.getMasaGiling());
                returnRekruitmenPabrikDetail.setDivisi(biodata.getDivisi());
                returnRekruitmenPabrikDetail.setTipePegawaiName(biodata.getTipePegawaiName());
                returnRekruitmenPabrikDetail.setStatusPegawaiName(biodata.getStatusPegawaiName());
                returnRekruitmenPabrikDetail.setDivisiName(biodata.getDivisiName());
                returnRekruitmenPabrikDetail.setBagianId(biodata.getBagianId());
                returnRekruitmenPabrikDetail.setBagianName(biodata.getBagianName());
                listOfResult.add(returnRekruitmenPabrikDetail);
            }
            // creating object entity serializable
            listComboRekruitmenPabrikDetail = (List<RekruitmenPabrikDetail>) session.getAttribute("ListOfResultRekruitmenPabrikDetail");
            if(listComboRekruitmenPabrikDetail != null){
                listComboRekruitmenPabrikDetail.addAll(listOfResult);
            }else{
                listComboRekruitmenPabrikDetail = new ArrayList();
                listComboRekruitmenPabrikDetail.addAll(listOfResult);
            }

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "RekruitmenBo.saveAddStudy");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenBo.saveAddStudy] Error when saving error,", e1);
            }
            logger.error("[RekruitmenBo.saveAddStudy] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        session.setAttribute("ListOfResultRekruitmenPabrikDetail", listComboRekruitmenPabrikDetail);

        logger.info("[SppdAction.saveEdit] end process <<<");

        return "init_add";
    }
    public List<RekruitmenPabrikDetail> searchRekruitmenPabrikPerson(String nip) {
        logger.info("[RekruitmenPabrikAction.searchRekruitmenPabrikPerson] start process >>>");

        List<RekruitmenPabrikDetail> rekruitmenPabrikDetailsPersons = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            RekruitmenPabrikBo rekruitmenPabrikBo = (RekruitmenPabrikBo) ctx.getBean("rekruitmenPabrikBoProxy");

            rekruitmenPabrikDetailsPersons = rekruitmenPabrikBo.getComboRekruitmenPabrikPerson2(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "KeluargaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KeluargaAction.search] Error when saving error,", e1);
            }
            logger.error("[KeluargaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return rekruitmenPabrikDetailsPersons;
    }
    public List<RekruitmenPabrikDetail> searchRekruitmenPabrikPersonGm(String nip,String rekruitmenPabrikDetailId) {
        logger.info("[RekruitmenPabrikAction.searchRekruitmenPabrikPerson] start process >>>");

        List<RekruitmenPabrikDetail> rekruitmenPabrikDetailsPersons = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            RekruitmenPabrikBo rekruitmenPabrikBo = (RekruitmenPabrikBo) ctx.getBean("rekruitmenPabrikBoProxy");

            rekruitmenPabrikDetailsPersons = rekruitmenPabrikBo.getComboRekruitmenPabrikPerson3(nip,rekruitmenPabrikDetailId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "KeluargaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KeluargaAction.search] Error when saving error,", e1);
            }
            logger.error("[KeluargaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return rekruitmenPabrikDetailsPersons;
    }
    public List<RekruitmenPabrikDetail> editRekruitmenPabrikPerson(String nip,String posisiBaru) {
        logger.info("[RekruitmenPabrikAction.editRekruitmenPabrikPerson] start process >>>");
        List<RekruitmenPabrikDetail> rekruitmenPabrikDetailsPersons = new ArrayList();
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            RekruitmenPabrikBo rekruitmenPabrikBo = (RekruitmenPabrikBo) ctx.getBean("rekruitmenPabrikBoProxy");

            rekruitmenPabrikDetailsPersons = rekruitmenPabrikBo.editRekruitmenPabrikPerson(nip,posisiBaru);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "KeluargaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KeluargaAction.search] Error when saving error,", e1);
            }
            logger.error("[KeluargaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return null;
    }
    public List<RekruitmenPabrikDetail> deleteRekruitmenPabrikPerson(String nip) {
        logger.info("[RekruitmenPabrikAction.editRekruitmenPabrikPerson] start process >>>");

        List<RekruitmenPabrikDetail> rekruitmenPabrikDetailsPersons = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            RekruitmenPabrikBo rekruitmenPabrikBo = (RekruitmenPabrikBo) ctx.getBean("rekruitmenPabrikBoProxy");

            rekruitmenPabrikDetailsPersons = rekruitmenPabrikBo.hapusRekruitmenPabrikPerson(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "KeluargaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KeluargaAction.search] Error when saving error,", e1);
            }
            logger.error("[KeluargaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return null;
    }
    public void approveRekruitmenPabrikPerson(String divisi,String rekId,String approveWho,String approvalFlag) {
        logger.info("[RekruitmenPabrikAction.editRekruitmenPabrikPerson] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");
        RekruitmenPabrikBo rekruitmenPabrikBo = (RekruitmenPabrikBo) ctx.getBean("rekruitmenPabrikBoProxy");
        List<Notifikasi> notif = new ArrayList<>();
        try {
            notif = rekruitmenPabrikBo.approveRekruitmenPabrikPerson(divisi,rekId,approveWho,approvalFlag);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "KeluargaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KeluargaAction.search] Error when saving error,", e1);
            }
            logger.error("[KeluargaAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        for (Notifikasi notifikasi : notif ){
            notifikasiBo.sendNotif(notifikasi);
        }
    }

    public void approveRekruitmenPabrikPersonGm(String rekId,String approveWho,String approvalFlag) {
        logger.info("[RekruitmenPabrikAction.approveRekruitmenPabrikPersonGm] start process >>>");
        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            RekruitmenPabrikBo rekruitmenPabrikBo = (RekruitmenPabrikBo) ctx.getBean("rekruitmenPabrikBoProxy");
            rekruitmenPabrikBo.approveRekruitmenPabrikPersonGm(rekId,approveWho,approvalFlag);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "RekruitmenPabrikAction.approveRekruitmenPabrikPersonGm");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenPabrikAction.approveRekruitmenPabrikPersonGm] Error when saving error,", e1);
            }
            logger.error("[RekruitmenPabrikAction.approveRekruitmenPabrikPersonGm] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
    }

    public List<RekruitmenPabrikDetail> searchPegawaiCalon(String rekruitmenPabrikId) {
        logger.info("[RekruitmenPabrikAction.search] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RekruitmenPabrikDetail> listOfsearchRekruitmenPabrikDetail = (List<RekruitmenPabrikDetail>) session.getAttribute("ListOfResultRekruitmenPabrikDetail");
        if (listOfsearchRekruitmenPabrikDetail==null){
            RekruitmenPabrik search = new RekruitmenPabrik();
            search.setRekruitmenPabrikId(rekruitmenPabrikId);
            search.setFlag("Y");
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            RekruitmenPabrikBo rekruitmenPabrikBo = (RekruitmenPabrikBo) ctx.getBean("rekruitmenPabrikBoProxy");
            listOfsearchRekruitmenPabrikDetail=rekruitmenPabrikBo.getByCriteriaDetail(search);

            session.removeAttribute("ListOfResultRekruitmenPabrikDetail");
            session.setAttribute("ListOfResultRekruitmenPabrikDetail",listOfsearchRekruitmenPabrikDetail);
        }
        return listOfsearchRekruitmenPabrikDetail;
    }
    public Biodata searchBiodataUser(String nip) {
        logger.info("[RekruitmenPabrikAction.searchBiodataUser] start process >>>");
        Biodata searchBiodata = new Biodata();
        Biodata result = new Biodata();
        searchBiodata.setNip(nip);
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        result = biodataBo.getBiodataRekruitmen(searchBiodata);

        return result;
    }
    public String saveClosedRekruitmenPabrik(String id) {
        logger.info("[RekruitmenPabrikAction.saveClosedRekruitmenPabrik] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RekruitmenPabrikBo rekruitmenPabrikBo = (RekruitmenPabrikBo) ctx.getBean("rekruitmenPabrikBoProxy");
        rekruitmenPabrikBo.saveClosedRekruitmenPabrik(id);
        return null;
    }
    public List<Biodata> searchPegawai(String nip,String branchid,String masagiling) {
        logger.info("[RekruitmenPabrikAction.search] start process >>>");

        Biodata searchBiodata = new Biodata();
        if (!Objects.equals(nip, "")){
            searchBiodata.setNip(nip);
        }
        if (!Objects.equals(masagiling, "")){
            searchBiodata.setMasaGiling(masagiling);
        }
        searchBiodata.setBranch(branchid);
        searchBiodata.setFrom("rekruitmenPabrik");
        List<Biodata> listOfsearchBiodata = new ArrayList();
        List<Biodata> listOfResult = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        try {
            listOfsearchBiodata = biodataBo.getByCriteriaForRekruitmenPabrik(searchBiodata);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.search] Error when saving error,", e1);
                return null;
            }
            logger.error("[BiodataAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return null;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RekruitmenPabrikDetail> listOfsearchRekruitmenPabrikDetail = (List<RekruitmenPabrikDetail>) session.getAttribute("ListOfResultRekruitmenPabrikDetail");
        for (Biodata biodata : listOfsearchBiodata){
            boolean ada = false;
            for (RekruitmenPabrikDetail rekruitmenPabrikDetail : listOfsearchRekruitmenPabrikDetail ){
                if (biodata.getNip().equalsIgnoreCase(rekruitmenPabrikDetail.getNip())){
                    ada=true;
                    break;
                }
            }
            if (!ada){
                listOfResult.add(biodata);
            }
        }

        logger.info("[BiodataAction.search] end process <<<");
        return listOfResult;
    }
    public List<PersonilPosition> searchHistoriPegawai(String nip) {
        logger.info("[RekruitmenPabrikAction.searchHistoriPegawai] start process >>>");

        PersonilPosition searchPersonilPosition = new PersonilPosition();
        searchPersonilPosition.setNip(nip);

        List<PersonilPosition> listOfsearchPersonilPosition = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RekruitmenPabrikBo rekruitmenPabrikBo = (RekruitmenPabrikBo) ctx.getBean("rekruitmenPabrikBoProxy");
        try {
            listOfsearchPersonilPosition = rekruitmenPabrikBo.getByCriteriaHistoriPegawai(searchPersonilPosition);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BiodataAction.search] Error when saving error,", e1);
                return null;
            }
            logger.error("[BiodataAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return null;
        }
        logger.info("[BiodataAction.search] end process <<<");

        return listOfsearchPersonilPosition;
    }
    @Override
    public String search() {
        logger.info("[RekruitmenPabrikAction.search] start process >>>");

        RekruitmenPabrik searchRekruitmenPabrik = getRekruitmenPabrik();
        RekruitmenPabrikDetail searchRekruitmenPabrikDetail = getRekruitmenPabrikDetail();
        List<RekruitmenPabrik> listOfsearchRekruitmenPabrik = new ArrayList();
        List<RekruitmenPabrikDetail> listOfsearchRekruitmenPabrikDetail = new ArrayList();

        if (("ADMIN").equalsIgnoreCase(CommonUtil.roleAsLogin())){
            setAdmin(true);
        }
        if ("Admin Bagian".equalsIgnoreCase(CommonUtil.roleAsLogin())){
            PositionBagian searchBagian = new PositionBagian();
            searchBagian.setBagianName(CommonUtil.userLogin());
            List<PositionBagian> positionBagianList = positionBagianBoProxy.getByCriteria(searchBagian);
            for (PositionBagian bagian : positionBagianList){
                if (searchRekruitmenPabrik==null){
                    searchRekruitmenPabrik=new RekruitmenPabrik();
                }
                searchRekruitmenPabrik.setBagianId(bagian.getBagianId());
            }
        }
        try {
            listOfsearchRekruitmenPabrik = rekruitmenPabrikBoProxy.getByCriteria(searchRekruitmenPabrik);
            listOfsearchRekruitmenPabrikDetail = rekruitmenPabrikBoProxy.getByCriteriaDetail(searchRekruitmenPabrikDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "PersonalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[RekruitmenAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("ListOfResultRekruitmenPabrikDetail");
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchRekruitmenPabrik);
        session.setAttribute("ListOfResultRekruitmenPabrikDetail", listOfsearchRekruitmenPabrikDetail);

        logger.info("[RekruitmenAction.search] end process <<<");

        return SUCCESS;    }

    @Override
    public String initForm() {
        logger.info("[RekruitmenPabrikAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (("ADMIN").equalsIgnoreCase(CommonUtil.roleAsLogin())){
            setAdmin(true);
        }
        session.removeAttribute("listOfResult");
        session.removeAttribute("ListOfResultRekruitmenPabrikDetail");
        logger.info("[RekruitmenPabrikAction.initForm] end process >>>");
        return INPUT;
    }
    public String approveGm() {
        logger.info("[RekruitmenPabrikAction.approveGm] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        RekruitmenPabrik editRekruitmenPabrik = new RekruitmenPabrik();

        if(itemFlag != null){
            try {
                editRekruitmenPabrik = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "rekruitmenBoImpl.getRekruitmenPabrikByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[IjinAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[IjinAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editRekruitmenPabrik != null) {
                setRekruitmenPabrik(editRekruitmenPabrik);
            } else {
                editRekruitmenPabrik.setFlag(itemFlag);
                editRekruitmenPabrik.setRekruitmenPabrikId(itemId);
                setRekruitmenPabrik(editRekruitmenPabrik);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editRekruitmenPabrik.setRekruitmenPabrikId(itemId);
            editRekruitmenPabrik.setFlag(getFlag());
            setRekruitmenPabrik(editRekruitmenPabrik);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        List<RekruitmenPabrikDetail> listOfsearchRekruitmenPabrikDetail = new ArrayList();
        listOfsearchRekruitmenPabrikDetail = rekruitmenPabrikBoProxy.getByCriteriaDetail(editRekruitmenPabrik);
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RekruitmenPabrikDetail> listOfResult = (List<RekruitmenPabrikDetail>) session.getAttribute("ListOfResultRekruitmenPabrikDetailApproval");
        if (listOfResult!=null){
            session.removeAttribute("ListOfResultRekruitmenPabrikDetail");
            session.setAttribute("ListOfResultRekruitmenPabrikDetail", listOfResult);
        }
        else {
            session.removeAttribute("ListOfResultRekruitmenPabrikDetail");
            session.setAttribute("ListOfResultRekruitmenPabrikDetail", listOfsearchRekruitmenPabrikDetail);
        }

        logger.info("[RekruitmenPabrikDetail.approveSdm] end process >>>");
        return "init_approve_gm";
    }

    public String approveSdm() {
        logger.info("[RekruitmenPabrikAction.approveSdm] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        RekruitmenPabrik editRekruitmenPabrik = new RekruitmenPabrik();

        if(itemFlag != null){
            try {
                editRekruitmenPabrik = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "rekruitmenBoImpl.getRekruitmenPabrikByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[IjinAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[IjinAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editRekruitmenPabrik != null) {
                setRekruitmenPabrik(editRekruitmenPabrik);
            } else {
                editRekruitmenPabrik.setFlag(itemFlag);
                editRekruitmenPabrik.setRekruitmenPabrikId(itemId);
                setRekruitmenPabrik(editRekruitmenPabrik);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editRekruitmenPabrik.setRekruitmenPabrikId(itemId);
            editRekruitmenPabrik.setFlag(getFlag());
            setRekruitmenPabrik(editRekruitmenPabrik);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        List<RekruitmenPabrikDetail> listOfsearchRekruitmenPabrikDetail = new ArrayList();
        listOfsearchRekruitmenPabrikDetail = rekruitmenPabrikBoProxy.getByCriteriaDetail(editRekruitmenPabrik);
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<RekruitmenPabrikDetail> listOfResult = (List<RekruitmenPabrikDetail>) session.getAttribute("ListOfResultRekruitmenPabrikDetailApproval");
        if (listOfResult!=null){
            session.removeAttribute("ListOfResultRekruitmenPabrikDetail");
            session.setAttribute("ListOfResultRekruitmenPabrikDetail", listOfResult);
        }
        else {
            session.removeAttribute("ListOfResultRekruitmenPabrikDetail");
            session.setAttribute("ListOfResultRekruitmenPabrikDetail", listOfsearchRekruitmenPabrikDetail);
        }

        logger.info("[RekruitmenPabrikDetail.approveSdm] end process >>>");
        return "init_approve_sdm";
    }
    public String cetakKontrakDetail(){
        logger.info("[RekruitmenPabrikDetail.cetakKontrak] end process >>>");

        String id = getId();
        RekruitmenPabrik rekruitmenPabrik = new RekruitmenPabrik();
        rekruitmenPabrik.setRpbId(id);
        List<RekruitmenPabrikDetail> rekruitmenPabrikDetailList = new ArrayList<>();
        try {
            rekruitmenPabrikDetailList = rekruitmenPabrikBoProxy.getByCriteriaDetail(rekruitmenPabrik);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "RekruitmenPabrikDetail.cetakKontrak");
            } catch (GeneralBOException e1) {
                logger.error("[RekruitmenPabrikDetail.cetakKontrak] Error when saving error,", e1);
            }
            logger.error("[RekruitmenPabrikDetail.cetakKontrak] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }
        if (rekruitmenPabrikDetailList != null){
            for (RekruitmenPabrikDetail rekruitmenPabrikDetail1 : rekruitmenPabrikDetailList){
                ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
                Biodata direkturUtama = biodataBo.getDirekturUtama();
                Biodata biodataOrang = biodataBo.detailBiodataSys(rekruitmenPabrikDetail1.getNip());
                String par1Direktur = "Direktur Utama PT Pabrik Gula Rajawali I, berdasarkan Surat Keputusan Pemegang Saham di Luar Rapat Umum Pemegang Saham PT Pabrik Gula Rajawali I Nomor: 36/Kep.PS/RNI.01/V/2018 tanggal 14 Mei 2018 tentang Pengukuhan Pelaksana Tugas Direktur Utama Direktur Utama PT Pabrik Gula Rajawali I, dalam hal ini sah bertindak dan mewakili untuk dan atas nama PT Pabrik Gula Rajawali I, yang berkedudukan di Jakarta Selatan dan berkantor di Jl. Undaan Kulon no. 57-59, Surabaya, 60274, Jawa Timur untuk selanjutnya disebut sebagai <b>Pihak Pertama</b>.";
                String par1Nama="Alamat : "+biodataOrang.getAlamat()+" "+biodataOrang.getRtRw()+" "+biodataOrang.getDesaName()+" "+biodataOrang.getKecamatanName()+" "+biodataOrang.getKotaName()+" "+biodataOrang.getProvinsiName()+"\n" +
                        "\n" +
                        "\n" +
                        "Jenis Kelamin : "+biodataOrang.getGenderName()+" \n" +
                        "Tempat/ tanggal lahir : "+biodataOrang.getStTanggalLahir()+"\n" +
                        "Nomor KTP : "+biodataOrang.getNoKtp()+"\n" +
                        "Bertindak untuk dan atas nama diri sendiri, selanjutnya disebut <b>Pihak Kedua</b>.";
//                String par1Perkenalan="Pada hari ini, Sabtu..tanggal.12 Mei 2018. di PG.Rejo Agung Baru, Madiun bahwa kedua belah pihak sepakat untuk mengikatkan diri dalam Perjanjian Kerja Karyawan Kampanye pada Musim Giling 2017, dengan ketentuan-ketentuan sebagai berikut :";

                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                Date dataDate = new java.sql.Date(updateTime.getTime());
                SimpleDateFormat dt1 = new SimpleDateFormat("dd/MMM/yyyy");
                String stDate = dt1.format(dataDate);
                DateFormatSymbols symbols = DateFormatSymbols.getInstance();

                reportParams.put("urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
                reportParams.put("id", id);
                reportParams.put("nip", rekruitmenPabrikDetail1.getNip());
                reportParams.put("nama", rekruitmenPabrikDetail1.getNamaPegawai());
                reportParams.put("namaDirektur", direkturUtama);
                reportParams.put("par1Direktur", par1Direktur);
                reportParams.put("par1Nama", par1Nama);
            }
            try {
                preDownload();
            } catch (SQLException e) {
                Long logId = null;
                try {
                    logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "printSuratJaminan");
                } catch (GeneralBOException e1) {
                    logger.error("[TrainingAction.printSuratJaminan] Error when downloading ,", e1);
                }
                logger.error("[TrainingAction.printReport] Error when print report ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
                return "failure_print";
            }

        } else {
            logger.error("[TrainingAction.printSuratJaminan] Error when print report realiassi bibit, data musim tanam is empty , Found problem when downloading data, please inform to your admin.");
            addActionError("Error, Found problem when downloading data, list notification detail is empty, please inform to your admin.");
            return "failure_print";
        }

        logger.info("[TrainingAction.printSuratJaminan] end process <<<");
        return "print_kontrak_rekruitmen_detail";
    }
    public String closed() {
        logger.info("[RekruitmenPabrikAction.closed] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        RekruitmenPabrik editRekruitmenPabrik = new RekruitmenPabrik();

        if(itemFlag != null){
            try {
                editRekruitmenPabrik = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = rekruitmenPabrikBoProxy.saveErrorMessage(e.getMessage(), "rekruitmenBoImpl.getRekruitmenPabrikByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[IjinAction.closed] Error when retrieving edit data,", e1);
                }
                logger.error("[IjinAction.closed] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editRekruitmenPabrik != null) {
                setRekruitmenPabrik(editRekruitmenPabrik);
            } else {
                editRekruitmenPabrik.setFlag(itemFlag);
                editRekruitmenPabrik.setRekruitmenPabrikId(itemId);
                setRekruitmenPabrik(editRekruitmenPabrik);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editRekruitmenPabrik.setRekruitmenPabrikId(itemId);
            editRekruitmenPabrik.setFlag(getFlag());
            setRekruitmenPabrik(editRekruitmenPabrik);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        List<RekruitmenPabrikDetail> listOfsearchRekruitmenPabrikDetail = new ArrayList();
        listOfsearchRekruitmenPabrikDetail = rekruitmenPabrikBoProxy.getByCriteriaDetail(editRekruitmenPabrik);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("ListOfResultRekruitmenPabrikDetail");
        session.setAttribute("ListOfResultRekruitmenPabrikDetail", listOfsearchRekruitmenPabrikDetail);

        logger.info("[IjinAction.closed] end process >>>");
        return "init_close";
    }
    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    public void setRekruitmenPabrikBoProxy(RekruitmenPabrikBo rekruitmenPabrikBoProxy) {
        this.rekruitmenPabrikBoProxy = rekruitmenPabrikBoProxy;
    }

    public RekruitmenPabrikBo getRekruitmenPabrikBoProxy() {
        return rekruitmenPabrikBoProxy;
    }
}