package com.neurix.hris.master.biayaPerjalananDinas.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biayaPerjalananDinas.bo.BiayaPerjalananDinasBo;
import com.neurix.hris.master.biayaPerjalananDinas.model.BiayaPerjalananDinas;
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

public class BiayaPerjalananDinasAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(BiayaPerjalananDinasAction.class);
    private BiayaPerjalananDinasBo biayaPerjalananDinasBoProxy;
    private BiayaPerjalananDinas biayaPerjalananDinas;

    public List getListComboBiaya() {
        return listComboBiaya;
    }

    public void setListComboBiaya(List listComboBiaya) {
        this.listComboBiaya = listComboBiaya;
    }

    private List listComboBiaya = new ArrayList<BiayaPerjalananDinas>();

    public BiayaPerjalananDinasBo getBiayaPerjalananDinasBoProxy() {
        return biayaPerjalananDinasBoProxy;
    }

    public void setBiayaPerjalananDinasBoProxy(BiayaPerjalananDinasBo biayaPerjalananDinasBoProxy) {
        this.biayaPerjalananDinasBoProxy = biayaPerjalananDinasBoProxy;
    }

    public BiayaPerjalananDinas getBiayaPerjalananDinas() {
        return biayaPerjalananDinas;
    }

    public void setBiayaPerjalananDinas(BiayaPerjalananDinas biayaPerjalananDinas) {
        this.biayaPerjalananDinas = biayaPerjalananDinas;
    }

    private List<BiayaPerjalananDinas> initComboBiayaPerjalananDinas;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        BiayaPerjalananDinasAction.logger = logger;
    }


    public List<BiayaPerjalananDinas> getInitComboBiayaPerjalananDinas() {
        return initComboBiayaPerjalananDinas;
    }

    public void setInitComboBiayaPerjalananDinas(List<BiayaPerjalananDinas> initComboBiayaPerjalananDinas) {
        this.initComboBiayaPerjalananDinas = initComboBiayaPerjalananDinas;
    }

    public BiayaPerjalananDinas init(String kode, String flag){
        logger.info("[BiayaPerjalananDinasAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<BiayaPerjalananDinas> listOfResult = (List<BiayaPerjalananDinas>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (BiayaPerjalananDinas biayaPerjalananDinas: listOfResult) {
                    if(kode.equalsIgnoreCase(biayaPerjalananDinas.getBiayaId()) && flag.equalsIgnoreCase(biayaPerjalananDinas.getFlag())){
                        setBiayaPerjalananDinas(biayaPerjalananDinas);
                        break;
                    }
                }
            } else {
                setBiayaPerjalananDinas(new BiayaPerjalananDinas());
            }

            logger.info("[BiayaPerjalananDinasAction.init] end process >>>");
        }
        return getBiayaPerjalananDinas();
    }

    @Override
    public String add() {
        logger.info("[BiayaPerjalananDinasAction.add] start process >>>");
        BiayaPerjalananDinas addBiayaPerjalananDinas = new BiayaPerjalananDinas();
        setBiayaPerjalananDinas(addBiayaPerjalananDinas);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[BiayaPerjalananDinasAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[BiayaPerjalananDinasAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        BiayaPerjalananDinas editBiayaPerjalananDinas = new BiayaPerjalananDinas();

        if(itemFlag != null){
            try {
                editBiayaPerjalananDinas = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = biayaPerjalananDinasBoProxy.saveErrorMessage(e.getMessage(), "BiayaPerjalananDinasBO.getBiayaPerjalananDinasByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[BiayaPerjalananDinasAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[BiayaPerjalananDinasAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editBiayaPerjalananDinas != null) {
                setBiayaPerjalananDinas(editBiayaPerjalananDinas);
            } else {
                editBiayaPerjalananDinas.setFlag(itemFlag);
                editBiayaPerjalananDinas.setBiayaId(itemId);
                setBiayaPerjalananDinas(editBiayaPerjalananDinas);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editBiayaPerjalananDinas.setBiayaId(itemId);
            editBiayaPerjalananDinas.setFlag(getFlag());
            setBiayaPerjalananDinas(editBiayaPerjalananDinas);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[BiayaPerjalananDinasAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[BiayaPerjalananDinasAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        BiayaPerjalananDinas deleteBiayaPerjalananDinas = new BiayaPerjalananDinas();

        if (itemFlag != null ) {

            try {
                deleteBiayaPerjalananDinas = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = biayaPerjalananDinasBoProxy.saveErrorMessage(e.getMessage(), "BiayaPerjalananDinasBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[BiayaPerjalananDinasAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[BiayaPerjalananDinasAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteBiayaPerjalananDinas != null) {
                setBiayaPerjalananDinas(deleteBiayaPerjalananDinas);

            } else {
                deleteBiayaPerjalananDinas.setBiayaId(itemId);
                deleteBiayaPerjalananDinas.setFlag(itemFlag);
                setBiayaPerjalananDinas(deleteBiayaPerjalananDinas);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteBiayaPerjalananDinas.setBiayaId(itemId);
            deleteBiayaPerjalananDinas.setFlag(itemFlag);
            setBiayaPerjalananDinas(deleteBiayaPerjalananDinas);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[BiayaPerjalananDinasAction.delete] end process <<<");

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
        logger.info("[BiayaPerjalananDinasAction.saveEdit] start process >>>");
        try {

            BiayaPerjalananDinas editBiayaPerjalananDinas = getBiayaPerjalananDinas();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editBiayaPerjalananDinas.setLastUpdateWho(userLogin);
            editBiayaPerjalananDinas.setLastUpdate(updateTime);
            editBiayaPerjalananDinas.setAction("U");
            editBiayaPerjalananDinas.setFlag("Y");

            biayaPerjalananDinasBoProxy.saveEdit(editBiayaPerjalananDinas);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biayaPerjalananDinasBoProxy.saveErrorMessage(e.getMessage(), "BiayaPerjalananDinasBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[BiayaPerjalananDinasAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiayaPerjalananDinasAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[BiayaPerjalananDinasAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[BiayaPerjalananDinasAction.saveDelete] start process >>>");
        try {

            BiayaPerjalananDinas deleteBiayaPerjalananDinas = getBiayaPerjalananDinas();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteBiayaPerjalananDinas.setLastUpdate(updateTime);
            deleteBiayaPerjalananDinas.setLastUpdateWho(userLogin);
            deleteBiayaPerjalananDinas.setAction("U");
            deleteBiayaPerjalananDinas.setFlag("N");

            biayaPerjalananDinasBoProxy.saveDelete(deleteBiayaPerjalananDinas);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biayaPerjalananDinasBoProxy.saveErrorMessage(e.getMessage(), "BiayaPerjalananDinasBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[BiayaPerjalananDinasAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiayaPerjalananDinasAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[BiayaPerjalananDinasAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[BiayaPerjalananDinasAction.saveAdd] start process >>>");

        try {
            BiayaPerjalananDinas biayaPerjalananDinas = getBiayaPerjalananDinas();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            biayaPerjalananDinas.setCreatedWho(userLogin);
            biayaPerjalananDinas.setLastUpdate(updateTime);
            biayaPerjalananDinas.setCreatedDate(updateTime);
            biayaPerjalananDinas.setLastUpdateWho(userLogin);
            biayaPerjalananDinas.setAction("C");
            biayaPerjalananDinas.setFlag("Y");

            biayaPerjalananDinasBoProxy.saveAdd(biayaPerjalananDinas);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biayaPerjalananDinasBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[BiayaPerjalananDinasAction.search] start process >>>");

        BiayaPerjalananDinas searchBiayaPerjalananDinas = getBiayaPerjalananDinas();
        List<BiayaPerjalananDinas> listOfsearchBiayaPerjalananDinas = new ArrayList();

        try {
            listOfsearchBiayaPerjalananDinas = biayaPerjalananDinasBoProxy.getByCriteria(searchBiayaPerjalananDinas);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biayaPerjalananDinasBoProxy.saveErrorMessage(e.getMessage(), "BiayaPerjalananDinasBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BiayaPerjalananDinasAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiayaPerjalananDinasAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchBiayaPerjalananDinas);

        logger.info("[BiayaPerjalananDinasAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchBiaya() {
        logger.info("[BiayaPerjalananDinasAction.search] start process >>>");

        BiayaPerjalananDinas searchBiayaPerjalananDinas = new BiayaPerjalananDinas();
        searchBiayaPerjalananDinas.setFlag("Y");
        List<BiayaPerjalananDinas> listOfsearchBiayaPerjalananDinas = new ArrayList();

        try {
            listOfsearchBiayaPerjalananDinas = biayaPerjalananDinasBoProxy.getByCriteria(searchBiayaPerjalananDinas);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biayaPerjalananDinasBoProxy.saveErrorMessage(e.getMessage(), "BiayaPerjalananDinasBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BiayaPerjalananDinasAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiayaPerjalananDinasAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboBiaya.addAll(listOfsearchBiayaPerjalananDinas);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[BiayaPerjalananDinasAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[BiayaPerjalananDinasAction.initForm] end process >>>");
        return INPUT;
    }

    public String initBiayaPerjalananDinas() {
        logger.info("[BiayaPerjalananDinasAction.search] start process >>>");

        BiayaPerjalananDinas searchBiayaPerjalananDinas = new BiayaPerjalananDinas();
        searchBiayaPerjalananDinas.setFlag("Y");
        List<BiayaPerjalananDinas> listOfsearchBiayaPerjalananDinas = new ArrayList();

        try {
            listOfsearchBiayaPerjalananDinas = biayaPerjalananDinasBoProxy.getByCriteria(searchBiayaPerjalananDinas);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biayaPerjalananDinasBoProxy.saveErrorMessage(e.getMessage(), "BiayaPerjalananDinasBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BiayaPerjalananDinasAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[BiayaPerjalananDinasAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultBiayaPerjalananDinas");
        session.setAttribute("listOfResultBiayaPerjalananDinas", listOfsearchBiayaPerjalananDinas);

        logger.info("[BiayaPerjalananDinasAction.search] end process <<<");

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
