package com.neurix.hris.master.strukturJabatan.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.positionBagian.bo.PositionBagianBo;
import com.neurix.hris.master.positionBagian.model.PositionBagian;
import com.neurix.hris.master.strukturJabatan.bo.StrukturJabatanBo;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class StrukturJabatanAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(StrukturJabatanAction.class);
    private StrukturJabatanBo strukturJabatanBoProxy;
    private StrukturJabatan strukturJabatan;
    private List<StrukturJabatan> listComboStrukturJabatan = new ArrayList<StrukturJabatan>();

    private List<PositionBagian> listComboPositionBagian = new ArrayList<>();

    public StrukturJabatanBo getStrukturJabatanBoProxy() {
        return strukturJabatanBoProxy;
    }

    public void setStrukturJabatanBoProxy(StrukturJabatanBo strukturJabatanBoProxy) {
        this.strukturJabatanBoProxy = strukturJabatanBoProxy;
    }

    public List<StrukturJabatan> getListComboStrukturJabatan() {
        return listComboStrukturJabatan;
    }

    public void setListComboStrukturJabatan(List<StrukturJabatan> listComboStrukturJabatan) {
        this.listComboStrukturJabatan = listComboStrukturJabatan;
    }

    public StrukturJabatan getStrukturJabatan() {
        return strukturJabatan;
    }

    public void setStrukturJabatan(StrukturJabatan strukturJabatan) {
        this.strukturJabatan = strukturJabatan;
    }

    private List<StrukturJabatan> initComboStrukturJabatan;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        StrukturJabatanAction.logger = logger;
    }


    public List<StrukturJabatan> getInitComboStrukturJabatan() {
        return initComboStrukturJabatan;
    }

    public void setInitComboStrukturJabatan(List<StrukturJabatan> initComboStrukturJabatan) {
        this.initComboStrukturJabatan = initComboStrukturJabatan;
    }

    public List<PositionBagian> getListComboPositionBagian() {
        return listComboPositionBagian;
    }

    public void setListComboPositionBagian(List<PositionBagian> listComboPositionBagian) {
        this.listComboPositionBagian = listComboPositionBagian;
    }

    public StrukturJabatan init(String kode, String flag){
        logger.info("[StrukturJabatanAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<StrukturJabatan> listOfResult = (List<StrukturJabatan>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (StrukturJabatan strukturJabatan: listOfResult) {
                    if(kode.equalsIgnoreCase(strukturJabatan.getStrukturJabatanId()) && flag.equalsIgnoreCase(strukturJabatan.getFlag())){
                        setStrukturJabatan(strukturJabatan);
                        break;
                    }
                }
            } else {
                setStrukturJabatan(new StrukturJabatan());
            }

            logger.info("[StrukturJabatanAction.init] end process >>>");
        }
        return getStrukturJabatan();
    }

    @Override
    public String add() {
        logger.info("[StrukturJabatanAction.add] start process >>>");
        StrukturJabatan addStrukturJabatan = new StrukturJabatan();
        setStrukturJabatan(addStrukturJabatan);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[StrukturJabatanAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[StrukturJabatanAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        StrukturJabatan editStrukturJabatan = new StrukturJabatan();

        if(itemFlag != null){
            try {
                editStrukturJabatan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = strukturJabatanBoProxy.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getStrukturJabatanByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[StrukturJabatanAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[StrukturJabatanAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editStrukturJabatan != null) {
                setStrukturJabatan(editStrukturJabatan);
            } else {
                editStrukturJabatan.setFlag(itemFlag);
                editStrukturJabatan.setStrukturJabatanId(itemId);
                setStrukturJabatan(editStrukturJabatan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editStrukturJabatan.setStrukturJabatanId(itemId);
            editStrukturJabatan.setFlag(getFlag());
            setStrukturJabatan(editStrukturJabatan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[StrukturJabatanAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[StrukturJabatanAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        StrukturJabatan deleteStrukturJabatan = new StrukturJabatan();

        if (itemFlag != null ) {

            try {
                deleteStrukturJabatan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = strukturJabatanBoProxy.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[StrukturJabatanAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[StrukturJabatanAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteStrukturJabatan != null) {
                setStrukturJabatan(deleteStrukturJabatan);

            } else {
                deleteStrukturJabatan.setStrukturJabatanId(itemId);
                deleteStrukturJabatan.setFlag(itemFlag);
                setStrukturJabatan(deleteStrukturJabatan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteStrukturJabatan.setStrukturJabatanId(itemId);
            deleteStrukturJabatan.setFlag(itemFlag);
            setStrukturJabatan(deleteStrukturJabatan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[StrukturJabatanAction.delete] end process <<<");

        return "init_delete";
    }

    public void getPerBagian(){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        StrukturJabatanBo strukturBo = (StrukturJabatanBo) ctx.getBean("strukturJabatanBoProxy");
        strukturBo.getPerBagianSys();
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEditStruktur(String strukturId, String Branch, String position, String Parent){
        logger.info("[StrukturJabatanAction.saveEdit] start process >>>");
        try {

            StrukturJabatan editStrukturJabatan = new StrukturJabatan();

            editStrukturJabatan.setStrukturJabatanId(strukturId);
            editStrukturJabatan.setBranchId(Branch);
            editStrukturJabatan.setPositionId(position.toString());
            editStrukturJabatan.setParentId(Parent);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editStrukturJabatan.setLastUpdateWho(userLogin);
            editStrukturJabatan.setLastUpdate(updateTime);
            editStrukturJabatan.setAction("U");
            editStrukturJabatan.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            StrukturJabatanBo strukturBo = (StrukturJabatanBo) ctx.getBean("strukturJabatanBoProxy");
            strukturBo.saveEdit(editStrukturJabatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = strukturJabatanBoProxy.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[StrukturJabatanAction.saveEdit] Error when saving error,", e1);
//                return false;
                return ERROR;
            }
            logger.error("[StrukturJabatanAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
//            return false;
            return ERROR;
        }

        logger.info("[StrukturJabatanAction.saveEdit] end process <<<");

//        return true;
        return "success_save_add";
    }



    public String saveDelete(String strukturId, String Branch, String position, String Parent){
        logger.info("[StrukturJabatanAction.saveDelete] start process >>>");
        String status;
        try {

            StrukturJabatan deleteStrukturJabatan = new StrukturJabatan();

            deleteStrukturJabatan.setStrukturJabatanId(strukturId);
            deleteStrukturJabatan.setBranchId(Branch);
            deleteStrukturJabatan.setPositionId(position);
            deleteStrukturJabatan.setParentId(Parent);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteStrukturJabatan.setLastUpdate(updateTime);
            deleteStrukturJabatan.setLastUpdateWho(userLogin);
            deleteStrukturJabatan.setAction("U");
            deleteStrukturJabatan.setFlag("N");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            StrukturJabatanBo strukturBo = (StrukturJabatanBo) ctx.getBean("strukturJabatanBoProxy");
            status = strukturBo.saveDeleteStruktur(deleteStrukturJabatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = strukturJabatanBoProxy.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[StrukturJabatanAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StrukturJabatanAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[StrukturJabatanAction.saveDelete] end process <<<");

        return status;
    }

    public String saveAdd(){
        logger.info("[StrukturJabatanAction.saveAdd] start process >>>");

        try {
            StrukturJabatan strukturJabatan = getStrukturJabatan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            strukturJabatan.setCreatedWho(userLogin);
            strukturJabatan.setLastUpdate(updateTime);
            strukturJabatan.setCreatedDate(updateTime);
            strukturJabatan.setLastUpdateWho(userLogin);
            strukturJabatan.setAction("C");
            strukturJabatan.setFlag("Y");

            strukturJabatanBoProxy.saveAdd(strukturJabatan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = strukturJabatanBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[StrukturJabatanAction.search] start process >>>");

        StrukturJabatan searchStrukturJabatan = getStrukturJabatan();
        List<StrukturJabatan> listOfsearchStrukturJabatan = new ArrayList();

        try {
            listOfsearchStrukturJabatan = strukturJabatanBoProxy.getByCriteria(searchStrukturJabatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = strukturJabatanBoProxy.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StrukturJabatanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StrukturJabatanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchStrukturJabatan);

        logger.info("[StrukturJabatanAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[StrukturJabatanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        String branchId = CommonUtil.userBranchLogin();
        StrukturJabatan data = new StrukturJabatan();
        if (branchId != null){
            data.setBranchId(branchId);
        }else {
            data.setBranchId("");
        }

        strukturJabatan = data;

        session.removeAttribute("listOfResult");
        logger.info("[StrukturJabatanAction.initForm] end process >>>");
        return INPUT;
    }


    public String initStrukturJabatan() {
        logger.info("[StrukturJabatanAction.search] start process >>>");

        StrukturJabatan searchStrukturJabatan = new StrukturJabatan();
        searchStrukturJabatan.setFlag("Y");
        List<StrukturJabatan> listOfsearchStrukturJabatan = new ArrayList();

        try {
            listOfsearchStrukturJabatan = strukturJabatanBoProxy.getByCriteria(searchStrukturJabatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = strukturJabatanBoProxy.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StrukturJabatanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StrukturJabatanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultStrukturJabatan");
        session.setAttribute("listOfResultStrukturJabatan", listOfsearchStrukturJabatan);

        logger.info("[StrukturJabatanAction.search] end process <<<");

        return "";
    }

    public List<StrukturJabatan> initStrukturJabatanList(String branch) {
        logger.info("[StrukturJabatanAction.search] start process >>>");

        StrukturJabatan searchStrukturJabatan = new StrukturJabatan();
        searchStrukturJabatan.setFlag("Y");
        searchStrukturJabatan.setBranchId(branch);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        StrukturJabatanBo strukturBo = (StrukturJabatanBo) ctx.getBean("strukturJabatanBoProxy");

        List<StrukturJabatan> listOfsearchStrukturJabatan = new ArrayList();

        try {
            listOfsearchStrukturJabatan = strukturBo.getByCriteria(searchStrukturJabatan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = strukturJabatanBoProxy.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StrukturJabatanAction.search] Error when saving error,", e1);
            }
            logger.error("[StrukturJabatanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return listOfsearchStrukturJabatan;
    }

    //public List<StrukturJabatan> initStrukturJabatanSearch(String id, String position, String flag, String branch) {
    public List<StrukturJabatan> initStrukturJabatanSearch(String branch, String position, String nip) {
        logger.info("[StrukturJabatanAction.search] start process >>>");

        StrukturJabatan searchStrukturJabatan = new StrukturJabatan();
        searchStrukturJabatan.setStrukturJabatanId(id);
        if(position != null){
            searchStrukturJabatan.setPositionId(position.toString());
        }
        if(branch != null){
            searchStrukturJabatan.setBranchId(branch);
        }
        searchStrukturJabatan.setFlag(flag);
        List<StrukturJabatan> listOfsearchStrukturJabatan = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        StrukturJabatanBo strukturBo = (StrukturJabatanBo) ctx.getBean("strukturJabatanBoProxy");

        try {
            //listOfsearchStrukturJabatan = strukturBo.getByCriteria(searchStrukturJabatan);
            listOfsearchStrukturJabatan = strukturBo.getDataStrukturJab(branch, position, nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = strukturJabatanBoProxy.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StrukturJabatanAction.search] Error when saving error,", e1);
            }
            logger.error("[StrukturJabatanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        logger.info("[StrukturJabatanAction.search] end process <<<");
        return listOfsearchStrukturJabatan;
    }

    public StrukturJabatan initStrukturJabatanSearch(String id) {
        logger.info("[StrukturJabatanAction.search] start process >>>");

        StrukturJabatan searchStrukturJabatan = new StrukturJabatan();
        searchStrukturJabatan.setStrukturJabatanId(id);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        StrukturJabatanBo strukturBo = (StrukturJabatanBo) ctx.getBean("strukturJabatanBoProxy");

        try {
            searchStrukturJabatan = strukturBo.getDataStrukturJab(id);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = strukturJabatanBoProxy.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StrukturJabatanAction.search] Error when saving error,", e1);
            }
            logger.error("[StrukturJabatanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        logger.info("[StrukturJabatanAction.search] end process <<<");
        return searchStrukturJabatan;
    }

    public String searchBagian() {
        logger.info("[StrukturJabatan.searchBagian] start process >>>");

        List<StrukturJabatan> listOfsearchBagian = new ArrayList();

        try {
            listOfsearchBagian = strukturJabatanBoProxy.getPerBagianDropDown();
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = strukturJabatanBoProxy.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getPerBagianDropDown");
            } catch (GeneralBOException e1) {
                logger.error("[StrukturJabatan.searchBagian] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StrukturJabatan.searchBagian] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboStrukturJabatan.addAll(listOfsearchBagian);
        return SUCCESS;
    }
    public String searchBagianUnit() {
        logger.info("[StrukturJabatan.searchBagianUnit] start process >>>");

        List<StrukturJabatan> listOfsearchBagian = new ArrayList();

        try {
            listOfsearchBagian = strukturJabatanBoProxy.getPerBagianDropDownUnit();
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = strukturJabatanBoProxy.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getPerBagianDropDown");
            } catch (GeneralBOException e1) {
                logger.error("[StrukturJabatan.searchBagianUnit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StrukturJabatan.searchBagianUnit] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboStrukturJabatan.addAll(listOfsearchBagian);
        return SUCCESS;
    }

    public String initComboSearchBagian() {
        logger.info("[PositionBagianAction.searchBagian] start process >>>");

        List<PositionBagian> listOfsearchBagian = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PositionBagianBo positionBagianBo = (PositionBagianBo) ctx.getBean("positionBagianBoProxy");

        try {
            PositionBagian positionBagian = new PositionBagian();
            positionBagian.setFlag("Y");
            listOfsearchBagian = positionBagianBo.getByCriteria(positionBagian);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBagianBo.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getPerBagianDropDown");
            } catch (GeneralBOException e1) {
                logger.error("[PositionBagianAction.searchBagian] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PositionBagianAction.searchBagian] Error when searching by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboPositionBagian.addAll(listOfsearchBagian);
        return "success_combo_bagian";
    }

    public List<PositionBagian> searchBagianBranch(String branchId) {
        logger.info("[strukturJabatanAction.searchBagianBranch] start process >>>");

        List<PositionBagian> listOfsearchBagian = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PositionBagianBo positionBagianBo = (PositionBagianBo) ctx.getBean("positionBagianBoProxy");

        try {
            PositionBagian positionBagian = new PositionBagian();
            positionBagian.setFlag("Y");
            positionBagian.setBranchId(branchId);
            if (("").equalsIgnoreCase(branchId)){
                positionBagian.setBranchId(null);
                listOfsearchBagian = positionBagianBo.getByCriteria(positionBagian);
            }else{
                listOfsearchBagian = positionBagianBo.getBagian(positionBagian);
            }
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBagianBo.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getPerBagianDropDown");
            } catch (GeneralBOException e1) {
                logger.error("[PositionBagianAction.searchBagianBranch] Error when saving error,", e1);
            }
            logger.error("[PositionBagianAction.searchBagianBranch] Error when searching by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return listOfsearchBagian;
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
