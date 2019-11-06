package com.neurix.hris.master.belajar.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.belajar.bo.BelajarBo;
import com.neurix.hris.master.belajar.model.Belajar;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class BelajarAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(BelajarAction.class);
    private BelajarBo belajarBoProxy;
    private Belajar belajar;

    public BelajarBo getBelajarBoProxy() {
        return belajarBoProxy;
    }

    public void setBelajarBoProxy(BelajarBo belajarBoProxy) {
        this.belajarBoProxy = belajarBoProxy;
    }

    public Belajar getBelajar() {
        return belajar;
    }

    public void setBelajar(Belajar belajar) {
        this.belajar = belajar;
    }

    private List<Belajar> initComboBelajar;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        BelajarAction.logger = logger;
    }


    public List<Belajar> getInitComboBelajar() {
        return initComboBelajar;
    }

    public void setInitComboBelajar(List<Belajar> initComboBelajar) {
        this.initComboBelajar = initComboBelajar;
    }

    public Belajar init(String kode, String flag){
        logger.info("[BelajarAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Belajar> listOfResult = (List<Belajar>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Belajar belajar: listOfResult) {
                    if(kode.equalsIgnoreCase(belajar.getBelajarId()) && flag.equalsIgnoreCase(belajar.getFlag())){
                        setBelajar(belajar);
                        break;
                    }
                }
            } else {
                setBelajar(new Belajar());
            }

            logger.info("[BelajarAction.init] end process >>>");
        }
        return getBelajar();
    }

    @Override
    public String add() {
        logger.info("[BelajarAction.add] start process >>>");
        Belajar addBelajar = new Belajar();
        setBelajar(addBelajar);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[BelajarAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[BelajarAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Belajar editBelajar = new Belajar();

        if(itemFlag != null){
            try {
                editBelajar = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = belajarBoProxy.saveErrorMessage(e.getMessage(), "BelajarBO.getBelajarByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[BelajarAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[BelajarAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editBelajar != null) {
                setBelajar(editBelajar);
            } else {
                editBelajar.setFlag(itemFlag);
                editBelajar.setBelajarId(itemId);
                setBelajar(editBelajar);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editBelajar.setBelajarId(itemId);
            editBelajar.setFlag(getFlag());
            setBelajar(editBelajar);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[BelajarAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[BelajarAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Belajar deleteBelajar = new Belajar();

        if (itemFlag != null ) {

            try {
                deleteBelajar = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = belajarBoProxy.saveErrorMessage(e.getMessage(), "BelajarBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[BelajarAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[BelajarAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteBelajar != null) {
                setBelajar(deleteBelajar);

            } else {
                deleteBelajar.setBelajarId(itemId);
                deleteBelajar.setFlag(itemFlag);
                setBelajar(deleteBelajar);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteBelajar.setBelajarId(itemId);
            deleteBelajar.setFlag(itemFlag);
            setBelajar(deleteBelajar);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[BelajarAction.delete] end process <<<");

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
        logger.info("[BelajarAction.saveEdit] start process >>>");
        try {

            Belajar editBelajar = getBelajar();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editBelajar.setLastUpdateWho(userLogin);
            editBelajar.setLastUpdate(updateTime);
            editBelajar.setAction("U");
            editBelajar.setFlag("Y");

            belajarBoProxy.saveEdit(editBelajar);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = belajarBoProxy.saveErrorMessage(e.getMessage(), "BelajarBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[BelajarAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BelajarAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[BelajarAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[BelajarAction.saveDelete] start process >>>");
        try {

            Belajar deleteBelajar = getBelajar();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteBelajar.setLastUpdate(updateTime);
            deleteBelajar.setLastUpdateWho(userLogin);
            deleteBelajar.setAction("U");
            deleteBelajar.setFlag("N");

            belajarBoProxy.saveDelete(deleteBelajar);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = belajarBoProxy.saveErrorMessage(e.getMessage(), "BelajarBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[BelajarAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BelajarAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[BelajarAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[BelajarAction.saveAdd] start process >>>");

        try {
            Belajar belajar = getBelajar();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            belajar.setCreatedWho(userLogin);
            belajar.setLastUpdate(updateTime);
            belajar.setCreatedDate(updateTime);
            belajar.setLastUpdateWho(userLogin);
            belajar.setAction("C");
            belajar.setFlag("Y");

            belajarBoProxy.saveAdd(belajar);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = belajarBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[BelajarAction.search] start process >>>");

        Belajar searchBelajar = getBelajar();
        List<Belajar> listOfsearchBelajar = new ArrayList();

        try {
            listOfsearchBelajar = belajarBoProxy.getByCriteria(searchBelajar);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = belajarBoProxy.saveErrorMessage(e.getMessage(), "BelajarBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BelajarAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BelajarAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchBelajar);

        logger.info("[BelajarAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[BelajarAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[BelajarAction.initForm] end process >>>");
        return INPUT;
    }

    public String initBelajar() {
        logger.info("[BelajarAction.search] start process >>>");

        Belajar searchBelajar = new Belajar();
        searchBelajar.setFlag("Y");
        List<Belajar> listOfsearchBelajar = new ArrayList();

        try {
            listOfsearchBelajar = belajarBoProxy.getByCriteria(searchBelajar);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = belajarBoProxy.saveErrorMessage(e.getMessage(), "BelajarBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BelajarAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BelajarAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultBelajar");
        session.setAttribute("listOfResultBelajar", listOfsearchBelajar);

        logger.info("[BelajarAction.search] end process <<<");

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
}
