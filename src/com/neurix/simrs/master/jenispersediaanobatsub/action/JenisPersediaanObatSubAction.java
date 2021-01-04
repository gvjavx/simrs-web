package com.neurix.simrs.master.jenispersediaanobatsub.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;

import com.neurix.common.util.CommonUtil;

import com.neurix.simrs.master.jenispersediaanobatsub.bo.JenisPersediaanObatSubBo;
import com.neurix.simrs.master.jenispersediaanobatsub.model.JenisPersediaanObatSub;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JenisPersediaanObatSubAction extends BaseMasterAction {
    private static transient Logger logger = Logger.getLogger(JenisPersediaanObatSubAction.class);
    private JenisPersediaanObatSubBo jenisPersediaanObatSubBoProxy;
    private JenisPersediaanObatSub jenisPersediaanObatsub;

    public JenisPersediaanObatSub getJenisPersediaanObatsub() {
        return jenisPersediaanObatsub;
    }

    public void setJenisPersediaanObatsub(JenisPersediaanObatSub jenisPersediaanObatsub) {
        this.jenisPersediaanObatsub = jenisPersediaanObatsub;
    }

    public void setJenisPersediaanObatSubBoProxy(JenisPersediaanObatSubBo jenisPersediaanObatSubBoProxy) {
        this.jenisPersediaanObatSubBoProxy = jenisPersediaanObatSubBoProxy;
    }

    public List<JenisPersediaanObatSub> getListJenisObatSubByIdJenis(String idJenis){
        logger.info("[JenisPersediaanObatSubAction.getListJenisObatSubByIdJenis] START >>>");
        JenisPersediaanObatSub jenisPersediaanObatSub = new JenisPersediaanObatSub();
        jenisPersediaanObatSub.setIdJenisObat(idJenis);
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        JenisPersediaanObatSubBo jenisPersediaanObatSubBo = (JenisPersediaanObatSubBo) ctx.getBean("jenisPersediaanObatSubBoProxy");
        List<JenisPersediaanObatSub> jenisPersediaanObatSubs = new ArrayList<>();
        if(idJenis != null && !"".equalsIgnoreCase(idJenis)){
            try {
                jenisPersediaanObatSubs = jenisPersediaanObatSubBo.getSearchByCriteria(jenisPersediaanObatSub);
            } catch (GeneralBOException e){
                logger.error("[JenisPersediaanObatSubAction.getListJenisObatSubByIdJenis] Error when get data jenis obat ," + "Found problem when searching data, please inform to your admin.", e);
            }
        }
        logger.info("[JenisPersediaanObatSubAction.getListJenisObatSubByIdJenis] END <<<");
        return jenisPersediaanObatSubs;
    }

    public JenisPersediaanObatSub init(String kode, String flag){
        logger.info("[JenisPersediaanObatSubAction.init] start process >>>>>");
        branch();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<JenisPersediaanObatSub> listOfResult = (List<JenisPersediaanObatSub>) session.getAttribute("listOfResultJenisPersediaanObatSub");

        if (kode != null && !"".equalsIgnoreCase(kode)){
            if (listOfResult != null){
                for (JenisPersediaanObatSub jenisPersediaanObatsub : listOfResult){
                    if (kode.equalsIgnoreCase(jenisPersediaanObatsub.getId()) && flag.equalsIgnoreCase(jenisPersediaanObatsub.getFlag())){
                        setJenisPersediaanObatsub(jenisPersediaanObatsub);
                        break;
                    }
                }
            } else {
                setJenisPersediaanObatsub(new JenisPersediaanObatSub());
            }
            logger.info("[JenisPersediaanObatSubAction.init] end process >>>>>");
        }
        return getJenisPersediaanObatsub();
    }

    @Override
    public String add() {
        logger.info("[JenisObatJenisPersediaanObatAction.add] start process >>>");

        JenisPersediaanObatSub addJenisPersediaanObatSub = new JenisPersediaanObatSub();
        setJenisPersediaanObatsub(addJenisPersediaanObatSub);
        setAddOrEdit(true);
        setAdd(true);
        logger.info("[JenisObatJenisPersediaanObatSub Action.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[JenisPersediaanObatSub Action.edit] start process >>>");
        String itemFlag = getFlag();
        String itemId = getId();
        JenisPersediaanObatSub editJenisObatJenisPersediaanObatSub = new JenisPersediaanObatSub();
        if(itemFlag != null){
            try {
                editJenisObatJenisPersediaanObatSub = init (itemId,itemFlag);
            } catch (GeneralBOException e) {
                logger.error("edit action"+e.getMessage());
                throw new GeneralBOException("edit action, "+e.getMessage());
            }

            if(editJenisObatJenisPersediaanObatSub != null) {
                setJenisPersediaanObatsub(editJenisObatJenisPersediaanObatSub);
            } else {
                editJenisObatJenisPersediaanObatSub.setFlag(itemFlag);
                setJenisPersediaanObatsub(editJenisObatJenisPersediaanObatSub);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editJenisObatJenisPersediaanObatSub.setFlag(getFlag());
            setJenisPersediaanObatsub(editJenisObatJenisPersediaanObatSub);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[JenisPersediaanObatSubAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[JenisPersediaanObatSubAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        JenisPersediaanObatSub deleteJenisPersediaanObatSub = new JenisPersediaanObatSub();

        if (itemFlag != null ) {
            try {
                deleteJenisPersediaanObatSub = init (itemId, itemFlag);
            } catch (GeneralBOException e) {
                logger.error("ini error, "+e.getMessage());
                throw new GeneralBOException("ini error, "+e.getMessage());
            }

            if (deleteJenisPersediaanObatSub != null) {
                setJenisPersediaanObatsub(deleteJenisPersediaanObatSub);

            } else {
                deleteJenisPersediaanObatSub.setFlag(itemFlag);
                setJenisPersediaanObatsub(deleteJenisPersediaanObatSub);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteJenisPersediaanObatSub.setFlag(itemFlag);
            setJenisPersediaanObatsub(deleteJenisPersediaanObatSub);
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
        logger.info("[JenisPersediaanObatSubSubAction.search] start process >>>");


        JenisPersediaanObatSub searchJenisPersediaanObatSub = getJenisPersediaanObatsub();
        List<JenisPersediaanObatSub> listOfsearchJenisPersediaanObatSub = new ArrayList();
        try {
            listOfsearchJenisPersediaanObatSub = jenisPersediaanObatSubBoProxy.getSearchByCriteria(searchJenisPersediaanObatSub);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultJenisPersediaanObatSub");
        session.setAttribute("listOfResultJenisPersediaanObatSub", listOfsearchJenisPersediaanObatSub);
        setJenisPersediaanObatsub(searchJenisPersediaanObatSub);
        logger.info("[JenisPersediaanObatSubAction.search] end process <<<");
        branch();
        return SUCCESS;

    }

    public  void branch(){
        String userBranch = CommonUtil.userBranchLogin();
        setJenisPersediaanObatsub(new JenisPersediaanObatSub());
        if (CommonConstant.BRANCH_KP.equalsIgnoreCase(userBranch)){
            jenisPersediaanObatsub.setIsKp("Y");
        }
    }
    @Override
    public String initForm() {

        logger.info("[JenisPersediaanObatSubAction.initForm] start process >>>");
        String userBranch = CommonUtil.userBranchLogin();


        HttpSession session = ServletActionContext.getRequest().getSession();
        setJenisPersediaanObatsub(new JenisPersediaanObatSub());
        if (CommonConstant.BRANCH_KP.equalsIgnoreCase(userBranch)){
            jenisPersediaanObatsub.setIsKp("Y");
        }
        session.removeAttribute("listOfResultJenisPersediaanObatSub");
        logger.info("[JenisPersediaanObatSubAction.initForm] end process >>>");
        return INPUT;
    }
    public String saveAdd(){
        logger.info("[JenisPersediaanObatSubAction.saveAdd] start process >>>");

        try {
            JenisPersediaanObatSub jenisPersediaanObatSub = getJenisPersediaanObatsub();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            jenisPersediaanObatSub.setCreatedWho(userLogin);
            jenisPersediaanObatSub.setLastUpdate(updateTime);
            jenisPersediaanObatSub.setCreatedDate(updateTime);
            jenisPersediaanObatSub.setLastUpdateWho(userLogin);
            jenisPersediaanObatSub.setAction("C");
            jenisPersediaanObatSub.setFlag("Y");

            jenisPersediaanObatSubBoProxy.saveAdd(jenisPersediaanObatSub);
        }catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultJenisPersediaanObatSub");

        logger.info("[JenisPersediaanObatSubAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public String saveEdit(){
        logger.info("[JenisPersediaanObatSubAction.saveEdit] start process >>>");
        try {
            JenisPersediaanObatSub editJenisPersediaanObatSub = getJenisPersediaanObatsub();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editJenisPersediaanObatSub.setLastUpdateWho(userLogin);
            editJenisPersediaanObatSub.setLastUpdate(updateTime);
            editJenisPersediaanObatSub.setAction("U");
            editJenisPersediaanObatSub.setFlag("Y");

            jenisPersediaanObatSubBoProxy.saveEdit(editJenisPersediaanObatSub);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[JenisPersediaanObatSubAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[JenisPersediaanObatSubAction.saveDelete] start process >>>");
        try {

            JenisPersediaanObatSub deleteJenisPersediaanObatSub = getJenisPersediaanObatsub();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteJenisPersediaanObatSub.setLastUpdate(updateTime);
            deleteJenisPersediaanObatSub.setLastUpdateWho(userLogin);
            deleteJenisPersediaanObatSub.setAction("D");
            deleteJenisPersediaanObatSub.setFlag("N");

            jenisPersediaanObatSubBoProxy.saveDelete(deleteJenisPersediaanObatSub);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }

        logger.info("[deleteJenisPersediaanObatSubAction.saveDelete] end process <<<");

        return "success_save_delete";
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
