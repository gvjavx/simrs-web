package com.neurix.simrs.master.jenisobat.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenisobat.bo.JenisObatBo;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JenisObatAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(JenisObatAction.class);
    private JenisObatBo jenisObatBoProxy;
    private JenisObat jenisObat;
    private List<JenisObat> listOfJenisObat = new ArrayList<>();

    public List<JenisObat> getListOfJenisObat() {
        return listOfJenisObat;
    }

    public void setListOfJenisObat(List<JenisObat> listOfJenisObat) {
        this.listOfJenisObat = listOfJenisObat;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JenisObatAction.logger = logger;
    }

    public JenisObatBo getJenisObatBoProxy() {
        return jenisObatBoProxy;
    }

    public void setJenisObatBoProxy(JenisObatBo jenisObatBoProxy) {
        this.jenisObatBoProxy = jenisObatBoProxy;
    }

    public JenisObat getJenisObat() {
        return jenisObat;
    }

    public void setJenisObat(JenisObat jenisObat) {
        this.jenisObat = jenisObat;
    }


    public JenisObat init(String kode, String flag){
        logger.info("[JenisObatAction.init] start process >>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<JenisObat> listOfResult = (List<JenisObat>) session.getAttribute("listOfResultJenisObat");

        if (kode != null && !"".equalsIgnoreCase(kode)){
            if (listOfResult != null){
                for (JenisObat jenisObat : listOfResult){
                    if (kode.equalsIgnoreCase(jenisObat.getIdJenisObat()) && flag.equalsIgnoreCase(jenisObat.getFlag())){
                        setJenisObat(jenisObat);
                        break;
                    }
                }
            } else {
                setJenisObat(new JenisObat());
            }
            logger.info("[KelasRuanganAction.init] end process >>>>>");
        }
        return getJenisObat();
    }

    @Override
    public String add() {
        logger.info("[JenisObatAction.add] start process >>>");

        JenisObat addJenisObat = new JenisObat();
        setJenisObat(addJenisObat);
        setAddOrEdit(true);
        setAdd(true);
        logger.info("[Jenis Obat Action.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[Jenis Obat Action.edit] start process >>>");
        String itemFlag = getFlag();
        String itemId = getId();
        JenisObat editJenisObat = new JenisObat();
        if(itemFlag != null){
            try {
                editJenisObat = init (itemId,itemFlag);
            } catch (GeneralBOException e) {
                logger.error("edit action"+e.getMessage());
                throw new GeneralBOException("edit action, "+e.getMessage());
            }

            if(editJenisObat != null) {
                setJenisObat(editJenisObat);
            } else {
                editJenisObat.setFlag(itemFlag);
                setJenisObat(editJenisObat);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editJenisObat.setFlag(getFlag());
            setJenisObat(editJenisObat);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[JenisObatAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[JenisObatAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        JenisObat deleteJenisObat = new JenisObat();

        if (itemFlag != null ) {
            try {
                deleteJenisObat = init (itemId, itemFlag);
            } catch (GeneralBOException e) {
                logger.error("ini error, "+e.getMessage());
                throw new GeneralBOException("ini error, "+e.getMessage());
            }

            if (deleteJenisObat != null) {
                setJenisObat(deleteJenisObat);

            } else {
                deleteJenisObat.setFlag(itemFlag);
                setJenisObat(deleteJenisObat);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteJenisObat.setFlag(itemFlag);
            setJenisObat(deleteJenisObat);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[deleteJenisObatAction.delete] end process <<<");
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


    @Override
    public String search() {
        logger.info("[JenisObatAction.search] start process >>>");

        JenisObat searchJenisObat = getJenisObat();
        List<JenisObat> listOfsearchJenisObat = new ArrayList();
        try {
            listOfsearchJenisObat = jenisObatBoProxy.getByCriteria(searchJenisObat);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultJenisObat");
        session.setAttribute("listOfResultJenisObat", listOfsearchJenisObat);
        setJenisObat(searchJenisObat);
        logger.info("[JenisObatAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[jenisObatAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        setJenisObat(new JenisObat());
        session.removeAttribute("listOfResultJenisObat");
        logger.info("[jenisObatAction.initForm] end process >>>");
        return INPUT;
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    public String getListJenisObat(){
        logger.info("[CheckupDetailAction.getListJenisObat] start process >>>");
        List<JenisObat> jenisObatList = new ArrayList<>();
        JenisObat jenisObat = new JenisObat();
        try {
            jenisObatList = jenisObatBoProxy.getByCriteria(jenisObat);
        }catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getListJenisObat] Error when get jenis obat ," + "Found problem when saving add data, please inform to your admin.", e);
        }
        listOfJenisObat.addAll(jenisObatList);
        logger.info("[CheckupDetailAction.getListJenisObat] end process <<<");
        return SUCCESS;
    }

    public String saveAdd(){
        logger.info("[JenisObatAction.saveAdd] start process >>>");

        try {
            JenisObat jenisObat = getJenisObat();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            jenisObat.setCreatedWho(userLogin);
            jenisObat.setLastUpdate(updateTime);
            jenisObat.setCreatedDate(updateTime);
            jenisObat.setLastUpdateWho(userLogin);
            jenisObat.setAction("C");
            jenisObat.setFlag("Y");

            jenisObatBoProxy.saveAdd(jenisObat);
        }catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultJenisObat");

        logger.info("[JenisObatAction.saveAdd] end process >>>");
        return "success_save_add";
    }


    public String saveDelete(){
        logger.info("[JenisObatAction.saveDelete] start process >>>");
        try {

            JenisObat deleteJenisObat = getJenisObat();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteJenisObat.setLastUpdate(updateTime);
            deleteJenisObat.setLastUpdateWho(userLogin);
            deleteJenisObat.setAction("D");
            deleteJenisObat.setFlag("N");

            jenisObatBoProxy.saveDelete(deleteJenisObat);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[JenisObatAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveEdit(){
        logger.info("[JenisObatAction.saveEdit] start process >>>");
        try {
            JenisObat editJenisObat = getJenisObat();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editJenisObat.setLastUpdateWho(userLogin);
            editJenisObat.setLastUpdate(updateTime);
            editJenisObat.setAction("U");
            editJenisObat.setFlag("Y");

            jenisObatBoProxy.saveEdit(editJenisObat);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[JenisObatAction.saveEdit] end process <<<");

        return "success_save_edit";
    }




}