package com.neurix.hris.master.ijin.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.ijin.bo.IjinBo;
import com.neurix.hris.master.ijin.model.Ijin;
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

public class IjinAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(IjinAction.class);
    private IjinBo ijinBoProxy;
    private Ijin ijin;
    private List<Ijin> listOfComboIjin = new ArrayList<Ijin>();

    public List<Ijin> getListOfComboIjin() {
        return listOfComboIjin;
    }

    public void setListOfComboIjin(List<Ijin> listOfComboIjin) {
        this.listOfComboIjin = listOfComboIjin;
    }


    public IjinBo getIjinBoProxy() {
        return ijinBoProxy;
    }

    public void setIjinBoProxy(IjinBo ijinBoProxy) {
        this.ijinBoProxy = ijinBoProxy;
    }

    public Ijin getIjin() {
        return ijin;
    }

    public void setIjin(Ijin ijin) {
        this.ijin = ijin;
    }

    private List<Ijin> initComboIjin;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        IjinAction.logger = logger;
    }


    public List<Ijin> getInitComboIjin() {
        return initComboIjin;
    }

    public void setInitComboIjin(List<Ijin> initComboIjin) {
        this.initComboIjin = initComboIjin;
    }

    public Ijin init(String kode, String flag){
        logger.info("[IjinAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Ijin> listOfResult = (List<Ijin>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Ijin ijin: listOfResult) {
                    if(kode.equalsIgnoreCase(ijin.getIjinId()) && flag.equalsIgnoreCase(ijin.getFlag())){
                        setIjin(ijin);
                        break;
                    }
                }
            } else {
                setIjin(new Ijin());
            }

            logger.info("[IjinAction.init] end process >>>");
        }
        return getIjin();
    }

    @Override
    public String add() {
        logger.info("[IjinAction.add] start process >>>");
        Ijin addIjin = new Ijin();
        setIjin(addIjin);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[IjinAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[IjinAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Ijin editIjin = new Ijin();

        if(itemFlag != null){
            try {
                editIjin = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = ijinBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.getIjinByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[IjinAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[IjinAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editIjin != null) {
                setIjin(editIjin);
            } else {
                editIjin.setFlag(itemFlag);
                editIjin.setIjinId(itemId);
                setIjin(editIjin);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editIjin.setIjinId(itemId);
            editIjin.setFlag(getFlag());
            setIjin(editIjin);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[IjinAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[IjinAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Ijin deleteIjin = new Ijin();

        if (itemFlag != null ) {

            try {
                deleteIjin = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = ijinBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[IjinAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[IjinAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteIjin != null) {
                setIjin(deleteIjin);

            } else {
                deleteIjin.setIjinId(itemId);
                deleteIjin.setFlag(itemFlag);
                setIjin(deleteIjin);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteIjin.setIjinId(itemId);
            deleteIjin.setFlag(itemFlag);
            setIjin(deleteIjin);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[IjinAction.delete] end process <<<");

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

    public String saveEdit(){
        logger.info("[IjinAction.saveEdit] start process >>>");
        try {

            Ijin editIjin = getIjin();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editIjin.setLastUpdateWho(userLogin);
            editIjin.setLastUpdate(updateTime);
            editIjin.setAction("U");
            editIjin.setFlag("Y");

            ijinBoProxy.saveEdit(editIjin);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[IjinAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[IjinAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[IjinAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[IjinAction.saveDelete] start process >>>");
        try {

            Ijin deleteIjin = getIjin();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteIjin.setLastUpdate(updateTime);
            deleteIjin.setLastUpdateWho(userLogin);
            deleteIjin.setAction("U");
            deleteIjin.setFlag("N");

            ijinBoProxy.saveDelete(deleteIjin);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[IjinAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[IjinAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[IjinAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[IjinAction.saveAdd] start process >>>");

        try {
            Ijin ijin = getIjin();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            ijin.setCreatedWho(userLogin);
            ijin.setLastUpdate(updateTime);
            ijin.setCreatedDate(updateTime);
            ijin.setLastUpdateWho(userLogin);
            ijin.setAction("C");
            ijin.setFlag("Y");

            ijinBoProxy.saveAdd(ijin);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[IjinAction.search] start process >>>");

        Ijin searchIjin = getIjin();
        List<Ijin> listOfsearchIjin = new ArrayList();

        try {
            listOfsearchIjin = ijinBoProxy.getByCriteria(searchIjin);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[IjinAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[IjinAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchIjin);

        logger.info("[IjinAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[IjinAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[IjinAction.initForm] end process >>>");
        return INPUT;
    }

    public String initIjin() {
        logger.info("[IjinAction.search] start process >>>");

        Ijin searchIjin = new Ijin();
        searchIjin.setFlag("Y");
        List<Ijin> listOfsearchIjin = new ArrayList();

        try {
            listOfsearchIjin = ijinBoProxy.getByCriteria(searchIjin);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[IjinAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[IjinAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultIjin");
        session.setAttribute("listOfResultIjin", listOfsearchIjin);

        logger.info("[IjinAction.search] end process <<<");

        return "";
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

    public String initComboIjin() {
        logger.info("[IjinAction.search] start process >>>");

        Ijin searchIjin = new Ijin();
        List<Ijin> listOfSearchIjin = new ArrayList();
        searchIjin.setFlag("Y");
        try {
            listOfSearchIjin = ijinBoProxy.getByCriteria(searchIjin);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinBoProxy.saveErrorMessage(e.getMessage(), "IjinBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[IjinAction.search] Error when saving error,", e1);
            }
            logger.error("[IjinAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listOfComboIjin.addAll(listOfSearchIjin);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
    }

    public List initComboLamaIjin(String query) {
        logger.info("[IjinKeluarAction.initComboLamaCuti] start process >>>");

        List<Ijin> listOfAlat = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IjinBo ijinBo = (IjinBo) ctx.getBean("ijinBoProxy");

        try {
            listOfAlat = ijinBo.getComboLamaIjinWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinBo.saveErrorMessage(e.getMessage(), "IjinBo.getComboLamaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[IjinAction.initComboLamaIjin] Error when saving error,", e1);
            }
            logger.error("[IjinAction.initComboLokasiKebun] Error when get combo ijin keluar," + "[" + logId + "] Found problem when retrieving combo ijin keluar data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfAlat;
    }

    public List initComboIjinId(String query) {
        logger.info("[UserAction.initComboUser] start process >>>");
        List<Ijin> listOfIjin = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IjinBo ijinBo = (IjinBo) ctx.getBean("ijinBoProxy");

        try {
            listOfIjin = ijinBo.getComboIjinIdWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinBo.saveErrorMessage(e.getMessage(), "IjinBO.getComboIjinWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[IjinAction.initComboIjin] Error when saving error,", e1);
            }
            logger.error("[IjinAction.initComboIjin] Error when get combo Ijin," + "[" + logId + "] Found problem when retrieving combo Ijin data, please inform to your admin.", e);
        }
        logger.info("[IjinAction.initComboIjin] end process <<<");
        return listOfIjin;
    }
    public List searchIjin(String nip) {
        logger.info("[UserAction.searchIjin] start process >>>");
        List<Ijin> listOfIjin = new ArrayList();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        IjinBo ijinBo = (IjinBo) ctx.getBean("ijinBoProxy");
        try {
            listOfIjin = ijinBo.getComboIjinIdWithKelamin(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = ijinBo.saveErrorMessage(e.getMessage(), "IjinBO.getComboIjinWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[IjinAction.searchIjin] Error when saving error,", e1);
            }
            logger.error("[IjinAction.searchIjin] Error when get combo Ijin," + "[" + logId + "] Found problem when retrieving combo Ijin data, please inform to your admin.", e);
        }
        logger.info("[IjinAction.searchIjin] end process <<<");
        return listOfIjin;
    }
}
