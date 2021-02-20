package com.neurix.akuntansi.master.parameterbudgeting.action;

import com.neurix.akuntansi.master.parameterbudgeting.bo.JenisBudgetingBo;
import com.neurix.akuntansi.master.parameterbudgeting.bo.KategoriParameterBudgetingBo;
import com.neurix.akuntansi.master.parameterbudgeting.model.JenisBudgeting;
import com.neurix.akuntansi.master.parameterbudgeting.model.KategoriParameterBudgeting;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class KategoriParameterBudgetingAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(KategoriParameterBudgetingAction.class);
    private KategoriParameterBudgetingBo kategoriParameterBudgetingBoProxy;
    private KategoriParameterBudgeting kategoriParameterBudgeting;
    private JenisBudgeting jenisBudgeting;
    private JenisBudgetingBo jenisBudgetingBoProxy;
    private List<JenisBudgeting> listOfComboJenisBudgeting = new ArrayList<JenisBudgeting>();

    public List<JenisBudgeting> getListOfComboJenisBudgeting() {
        return listOfComboJenisBudgeting;
    }

    public void setListOfComboJenisBudgeting(List<JenisBudgeting> listOfComboJenisBudgeting) {
        this.listOfComboJenisBudgeting = listOfComboJenisBudgeting;
    }

    public JenisBudgetingBo getJenisBudgetingBoProxy() {
        return jenisBudgetingBoProxy;
    }

    public void setJenisBudgetingBoProxy(JenisBudgetingBo jenisBudgetingBoProxy) {
        this.jenisBudgetingBoProxy = jenisBudgetingBoProxy;
    }

    public JenisBudgeting getJenisBudgeting() {
        return jenisBudgeting;
    }

    public void setJenisBudgeting(JenisBudgeting jenisBudgeting) {
        this.jenisBudgeting = jenisBudgeting;
    }

    private List<KategoriParameterBudgeting> listOfKategoriParameterBudgeting = new ArrayList<>();
    public List<KategoriParameterBudgeting> getListOfKategoriParameterBudgeting() {
        return listOfKategoriParameterBudgeting;
    }

    public void setListOfKategoriParameterBudgeting(List<KategoriParameterBudgeting> listOfKategoriParameterBudgeting) {
        this.listOfKategoriParameterBudgeting = listOfKategoriParameterBudgeting;
    }

    public KategoriParameterBudgetingBo getKategoriParameterBudgetingBoProxy() {
        return kategoriParameterBudgetingBoProxy;
    }

    public void setKategoriParameterBudgetingBoProxy(KategoriParameterBudgetingBo kategoriParameterBudgetingBoProxy) {
        this.kategoriParameterBudgetingBoProxy = kategoriParameterBudgetingBoProxy;
    }

    public KategoriParameterBudgeting getKategoriParameterBudgeting() {
        return kategoriParameterBudgeting;
    }

    public void setKategoriParameterBudgeting(KategoriParameterBudgeting kategoriParameterBudgeting) {
        this.kategoriParameterBudgeting = kategoriParameterBudgeting;
    }

    public KategoriParameterBudgeting init(String kode, String flag){
        logger.info("[KategoriParameterBudgetingAction.init] start process >>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KategoriParameterBudgeting> listOfResult = (List<KategoriParameterBudgeting>) session.getAttribute("listOfResultKategoriParameterBudgeting");
        if (kode != null && !"".equalsIgnoreCase(kode)){
            if (listOfResult != null){
                for (KategoriParameterBudgeting kategoriParameterBudgeting : listOfResult){
                    if (kode.equalsIgnoreCase(kategoriParameterBudgeting.getId()) && flag.equalsIgnoreCase(kategoriParameterBudgeting.getFlag())){
                        setKategoriParameterBudgeting(kategoriParameterBudgeting);
                        break;
                    }
                }
            } else {
                setKategoriParameterBudgeting(new KategoriParameterBudgeting());
            }
            logger.info("[KategoriParameterBudgetingAction.init] end process >>>>>");
        }
        return getKategoriParameterBudgeting();
    }

    @Override
    public String add() {
        logger.info("[KategoriParameterBudgetingAction.add] start process >>>");

        KategoriParameterBudgeting addKategoriParameterBudgeting = new KategoriParameterBudgeting();
        setKategoriParameterBudgeting(addKategoriParameterBudgeting);
        setAddOrEdit(true);
        setAdd(true);
        logger.info("[KategoriParameterBudgeting Action.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[KategoriParameterBudgeting Action.edit] start process >>>");
        String itemFlag = getFlag();
        String itemId = getId();
        KategoriParameterBudgeting editKategoriParameterBudgeting = new KategoriParameterBudgeting();
        if(itemFlag != null){
            try {
                editKategoriParameterBudgeting = init (itemId,itemFlag);
            } catch (GeneralBOException e) {
                logger.error("KategoriParameterBudgeting edit action"+e.getMessage());
                throw new GeneralBOException("KategoriParameterBudgeting edit action, "+e.getMessage());
            }

            if(editKategoriParameterBudgeting != null) {
                setKategoriParameterBudgeting(editKategoriParameterBudgeting);
            } else {
                editKategoriParameterBudgeting.setFlag(itemFlag);
                setKategoriParameterBudgeting(editKategoriParameterBudgeting);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editKategoriParameterBudgeting.setFlag(getFlag());
            setKategoriParameterBudgeting(editKategoriParameterBudgeting);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[KategoriParameterBudgetingAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[KategoriParameterBudgetingAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        KategoriParameterBudgeting deleteKategoriParameterBudgeting = new KategoriParameterBudgeting();

        if (itemFlag != null ) {
            try {
                deleteKategoriParameterBudgeting = init (itemId, itemFlag);
            } catch (GeneralBOException e) {
                logger.error("ini error, "+e.getMessage());
                throw new GeneralBOException("ini error, "+e.getMessage());
            }

            if (deleteKategoriParameterBudgeting != null) {
                setKategoriParameterBudgeting(deleteKategoriParameterBudgeting);

            } else {
                deleteKategoriParameterBudgeting.setFlag(itemFlag);
                setKategoriParameterBudgeting(deleteKategoriParameterBudgeting);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteKategoriParameterBudgeting.setFlag(itemFlag);
            setKategoriParameterBudgeting(deleteKategoriParameterBudgeting);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[deleteKategoriParameterBudgetingAction.delete] end process <<<");
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
        logger.info("[KategoriParameterBudgetingAction.search] start process >>>");

        KategoriParameterBudgeting searchKategoriParameterBudgeting = getKategoriParameterBudgeting();
        List<KategoriParameterBudgeting> listOfsearchKategoriParameterBudgeting = new ArrayList();
        try {
            listOfsearchKategoriParameterBudgeting = kategoriParameterBudgetingBoProxy.getByCriteria(searchKategoriParameterBudgeting);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultKategoriParameterBudgeting");
        session.setAttribute("listOfResultKategoriParameterBudgeting", listOfsearchKategoriParameterBudgeting);
        setKategoriParameterBudgeting(searchKategoriParameterBudgeting);
        logger.info("[KategoriParameterBudgetingAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[kategoriParameterBudgetingAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        setKategoriParameterBudgeting(new KategoriParameterBudgeting());
        session.removeAttribute("listOfResultKategoriParameterBudgeting");
        logger.info("[kategoriParameterBudgetingAction.initForm] end process >>>");
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

    public String getListKategoriParameterBudgeting(){
        logger.info("[CheckupDetailAction.getListKategoriParameterBudgeting] start process >>>");
        List<KategoriParameterBudgeting> kategoriParameterBudgetingList = new ArrayList<>();
        KategoriParameterBudgeting kategoriParameterBudgeting = new KategoriParameterBudgeting();
        try {
            kategoriParameterBudgetingList = kategoriParameterBudgetingBoProxy.getByCriteria(kategoriParameterBudgeting);
        }catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getListKategoriParameterBudgeting] Error when get jenis obat ," + "Found problem when saving add data, please inform to your admin.", e);
        }
        listOfKategoriParameterBudgeting.addAll(kategoriParameterBudgetingList);
        logger.info("[CheckupDetailAction.getListKategoriParameterBudgeting] end process <<<");
        return SUCCESS;
    }

    public String saveAdd(){
        logger.info("[KategoriParameterBudgetingAction.saveAdd] start process >>>");

        try {
            KategoriParameterBudgeting kategoriParameterBudgeting = getKategoriParameterBudgeting();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            kategoriParameterBudgeting.setCreatedWho(userLogin);
            kategoriParameterBudgeting.setLastUpdate(updateTime);
            kategoriParameterBudgeting.setCreatedDate(updateTime);
            kategoriParameterBudgeting.setLastUpdateWho(userLogin);
            kategoriParameterBudgeting.setAction("C");
            kategoriParameterBudgeting.setFlag("Y");

            kategoriParameterBudgetingBoProxy.saveAdd(kategoriParameterBudgeting);
        }catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultKategoriParameterBudgeting");

        logger.info("[KategoriParameterBudgetingAction.saveAdd] end process >>>");
        return "success_save_add";
    }


    public String saveDelete(){
        logger.info("[KategoriParameterBudgetingAction.saveDelete] start process >>>");
        try {

            KategoriParameterBudgeting deleteKategoriParameterBudgeting = getKategoriParameterBudgeting();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteKategoriParameterBudgeting.setLastUpdate(updateTime);
            deleteKategoriParameterBudgeting.setLastUpdateWho(userLogin);
            deleteKategoriParameterBudgeting.setAction("D");
            deleteKategoriParameterBudgeting.setFlag("N");

            kategoriParameterBudgetingBoProxy.saveDelete(deleteKategoriParameterBudgeting);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[KategoriParameterBudgetingAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveEdit(){
        logger.info("[KategoriParameterBudgetingAction.saveEdit] start process >>>");
        try {
            KategoriParameterBudgeting editKategoriParameterBudgeting = getKategoriParameterBudgeting();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editKategoriParameterBudgeting.setLastUpdateWho(userLogin);
            editKategoriParameterBudgeting.setLastUpdate(updateTime);
            editKategoriParameterBudgeting.setAction("U");
            editKategoriParameterBudgeting.setFlag("Y");

            kategoriParameterBudgetingBoProxy.saveEdit(editKategoriParameterBudgeting);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[KategoriParameterBudgetingAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String initComboJenisBudgeting(){
        JenisBudgeting searchJenisBudgeting = new JenisBudgeting();
        searchJenisBudgeting.setFlag("Y");
        List<JenisBudgeting> listOfsearchJenisBudgeting = new ArrayList();
        try {
            listOfsearchJenisBudgeting = jenisBudgetingBoProxy.getByCriteria(searchJenisBudgeting);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }
        listOfComboJenisBudgeting.addAll(listOfsearchJenisBudgeting);
        return "init_combo_jenis_budgeting";
    }


}