package com.neurix.simrs.master.jenispersediaanobat.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;

import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.jenispersediaanobat.bo.JenisPersediaanObatBo;
import com.neurix.simrs.master.jenispersediaanobat.model.JenisPersediaanObat;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JenisPersediaanObatAction extends BaseMasterAction {
    private static transient Logger logger = Logger.getLogger(JenisPersediaanObatAction.class);
    private JenisPersediaanObatBo jenisPersediaanObatBoProxy;
    private JenisPersediaanObat jenisPersediaanObat;
    private List<JenisPersediaanObat> listOfComboJenisPersediaanObat = new ArrayList<JenisPersediaanObat>();

    public List<JenisPersediaanObat> getListOfComboJenisPersediaanObat() {
        return listOfComboJenisPersediaanObat;
    }

    public void setListOfComboJenisPersediaanObat(List<JenisPersediaanObat> listOfComboJenisPersediaanObat) {
        this.listOfComboJenisPersediaanObat = listOfComboJenisPersediaanObat;
    }

    public void setJenisPersediaanObatBoProxy(JenisPersediaanObatBo jenisPersediaanObatBoProxy) {
        this.jenisPersediaanObatBoProxy = jenisPersediaanObatBoProxy;
    }

    public JenisPersediaanObat getJenisPersediaanObat() {
        return jenisPersediaanObat;
    }

    public void setJenisPersediaanObat(JenisPersediaanObat jenisPersediaanObat) {
        this.jenisPersediaanObat = jenisPersediaanObat;
    }

    public  void branch(){
        String userBranch = CommonUtil.userBranchLogin();
        setJenisPersediaanObat(new JenisPersediaanObat());
        if (CommonConstant.BRANCH_KP.equalsIgnoreCase(userBranch)){
            jenisPersediaanObat.setIsKp("Y");
        }
    }

    public JenisPersediaanObat init(String kode, String flag){
        logger.info("[JenisPersediaanObatAction.init] start process >>>>>");
        branch();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<JenisPersediaanObat> listOfResult = (List<JenisPersediaanObat>) session.getAttribute("listOfResultJenisPersediaanObat");

        if (kode != null && !"".equalsIgnoreCase(kode)){
            if (listOfResult != null){
                for (JenisPersediaanObat jenisPersediaanObat : listOfResult){
                    if (kode.equalsIgnoreCase(jenisPersediaanObat.getId()) && flag.equalsIgnoreCase(jenisPersediaanObat.getFlag())){
                        setJenisPersediaanObat(jenisPersediaanObat);
                        break;
                    }
                }
            } else {
                setJenisPersediaanObat(new JenisPersediaanObat());
            }
            logger.info("[JenisPersediaanObatAction.init] end process >>>>>");
        }
        return getJenisPersediaanObat();
    }

    public List<JenisPersediaanObat> getJenisPersediaanAll(){
        logger.info("[JenisPersediaanObatAction.getJenisPersediaanAll] START >>> ");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JenisPersediaanObatBo jenisPersediaanObatBo = (JenisPersediaanObatBo) ctx.getBean("jenisPersediaanObatBoProxy");

        List<JenisPersediaanObat> jenisPersediaanObats = new ArrayList<>();
        try {
            jenisPersediaanObats = jenisPersediaanObatBo.getAll();
        } catch (GeneralBOException e){
            logger.error("[JenisPersediaanObatAction.getJenisPersediaanAll] Error when get data jenis obat ," + "Found problem when searching data, please inform to your admin.", e);
        }

        logger.info("[JenisPersediaanObatAction.getJenisPersediaanAll] END <<< ");
        return jenisPersediaanObats;

    }


    @Override
    public String add() {
        logger.info("[JenisObatJenisPersediaanObatAction.add] start process >>>");

        JenisPersediaanObat addJenisPersediaanObat = new JenisPersediaanObat();
        setJenisPersediaanObat(addJenisPersediaanObat);
        setAddOrEdit(true);
        setAdd(true);
        logger.info("[JenisObatJenisPersediaanObat Action.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[JenisPersediaanObat Action.edit] start process >>>");
        String itemFlag = getFlag();
        String itemId = getId();
        JenisPersediaanObat editJenisObatJenisPersediaanObat = new JenisPersediaanObat();
        if(itemFlag != null){
            try {
                editJenisObatJenisPersediaanObat = init (itemId,itemFlag);
            } catch (GeneralBOException e) {
                logger.error("edit action"+e.getMessage());
                throw new GeneralBOException("edit action, "+e.getMessage());
            }

            if(editJenisObatJenisPersediaanObat != null) {
                setJenisPersediaanObat(editJenisObatJenisPersediaanObat);
            } else {
                editJenisObatJenisPersediaanObat.setFlag(itemFlag);
                setJenisPersediaanObat(editJenisObatJenisPersediaanObat);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editJenisObatJenisPersediaanObat.setFlag(getFlag());
            setJenisPersediaanObat(editJenisObatJenisPersediaanObat);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[JenisPersediaanObatAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[JenisPersediaanObatAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        JenisPersediaanObat deleteJenisPersediaanObat = new JenisPersediaanObat();

        if (itemFlag != null ) {
            try {
                deleteJenisPersediaanObat = init (itemId, itemFlag);
            } catch (GeneralBOException e) {
                logger.error("ini error, "+e.getMessage());
                throw new GeneralBOException("ini error, "+e.getMessage());
            }

            if (deleteJenisPersediaanObat != null) {
                setJenisPersediaanObat(deleteJenisPersediaanObat);

            } else {
                deleteJenisPersediaanObat.setFlag(itemFlag);
                setJenisPersediaanObat(deleteJenisPersediaanObat);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteJenisPersediaanObat.setFlag(itemFlag);
            setJenisPersediaanObat(deleteJenisPersediaanObat);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[deleteJenisPersediaanObatAction.delete] end process <<<");
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
        logger.info("[JenisPersediaanObatAction.search] start process >>>");

        JenisPersediaanObat searchJenisPersediaanObat = getJenisPersediaanObat();
        List<JenisPersediaanObat> listOfsearchJenisPersediaanObat = new ArrayList();
        try {
            listOfsearchJenisPersediaanObat = jenisPersediaanObatBoProxy.getSearchByCriteria(searchJenisPersediaanObat);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultJenisPersediaanObat");
        session.setAttribute("listOfResultJenisPersediaanObat", listOfsearchJenisPersediaanObat);
        setJenisPersediaanObat(searchJenisPersediaanObat);
        logger.info("[JenisPersediaanObatAction.search] end process <<<");
        branch();
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[JenisPersediaanObatAction.initForm] start process >>>");
        String userBranch = CommonUtil.userBranchLogin();

        HttpSession session = ServletActionContext.getRequest().getSession();
        setJenisPersediaanObat(new JenisPersediaanObat());
        if (CommonConstant.BRANCH_KP.equalsIgnoreCase(userBranch)){
            jenisPersediaanObat.setIsKp("Y");
        }

        session.removeAttribute("listOfResultJenisPersediaanObat");
        logger.info("[JenisPersediaanObatAction.initForm] end process >>>");
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

    public String saveAdd(){
        logger.info("[JenisPersediaanObatAction.saveAdd] start process >>>");

        try {
            JenisPersediaanObat jenisPersediaanObat = getJenisPersediaanObat();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            jenisPersediaanObat.setCreatedWho(userLogin);
            jenisPersediaanObat.setLastUpdate(updateTime);
            jenisPersediaanObat.setCreatedDate(updateTime);
            jenisPersediaanObat.setLastUpdateWho(userLogin);
            jenisPersediaanObat.setAction("C");
            jenisPersediaanObat.setFlag("Y");

            jenisPersediaanObatBoProxy.saveAdd(jenisPersediaanObat);
        }catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultJenisPersediaanObat");

        logger.info("[JenisPersediaanObatAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public String saveEdit(){
        logger.info("[JenisPersediaanObatAction.saveEdit] start process >>>");
        try {
            JenisPersediaanObat editJenisPersediaanObat = getJenisPersediaanObat();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editJenisPersediaanObat.setLastUpdateWho(userLogin);
            editJenisPersediaanObat.setLastUpdate(updateTime);
            editJenisPersediaanObat.setAction("U");
            editJenisPersediaanObat.setFlag("Y");

            jenisPersediaanObatBoProxy.saveEdit(editJenisPersediaanObat);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[JenisPersediaanObatAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[JenisPersediaanObatAction.saveDelete] start process >>>");
        try {

            JenisPersediaanObat deleteJenisPersediaanObat = getJenisPersediaanObat();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteJenisPersediaanObat.setLastUpdate(updateTime);
            deleteJenisPersediaanObat.setLastUpdateWho(userLogin);
            deleteJenisPersediaanObat.setAction("D");
            deleteJenisPersediaanObat.setFlag("N");

            jenisPersediaanObatBoProxy.saveDelete(deleteJenisPersediaanObat);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[deleteJenisPersediaanObatAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String initComboJenisPerseidaanObat(){
        JenisPersediaanObat searchJenisPersediaanObat = new JenisPersediaanObat();
        List<JenisPersediaanObat> listOfsearchJenisPersediaanObat = new ArrayList();
        try {
            listOfsearchJenisPersediaanObat = jenisPersediaanObatBoProxy.getSearchByCriteria(searchJenisPersediaanObat);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        listOfComboJenisPersediaanObat.addAll(listOfsearchJenisPersediaanObat);
        return "init_combo_jenis_persediaan_obat";
    }

}
