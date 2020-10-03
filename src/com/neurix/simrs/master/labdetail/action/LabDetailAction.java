package com.neurix.simrs.master.labdetail.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.lab.bo.LabBo;
import com.neurix.simrs.master.lab.model.Lab;
import com.neurix.simrs.master.labdetail.bo.LabDetailBo;
import com.neurix.simrs.master.labdetail.model.ImSimrsLabDetailEntity;
import com.neurix.simrs.master.labdetail.model.LabDetail;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LabDetailAction extends BaseTransactionAction {

    protected static transient Logger logger = Logger.getLogger(LabDetailAction.class);
    private LabDetailBo labDetailBoProxy;
    private LabBo labBoProxy;
    private LabDetail labDetail;
    private List<Lab> listOfComboLab = new ArrayList<Lab>();
    private ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
    private LabDetailBo labDetailBo = (LabDetailBo) ctx.getBean("labDetailBoProxy");

    public LabDetail initParameter(String id){
        logger.info("[LabDetailAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        return getLabDetail();
    }

    @Override
    public String search() {
        logger.info("[LabAction.search] start process >>>");

        LabDetail searchLabDetail = getLabDetail();
        List<LabDetail> listOfsearchLabDetail = new ArrayList();

        try {
            listOfsearchLabDetail = labDetailBoProxy.getByCriteria(searchLabDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = labDetailBoProxy.saveErrorMessage(e.getMessage(), "LabDetailBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[LabDetailAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[LabDetailAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultLabDetail");
        session.setAttribute("listOfResultLabDetail", listOfsearchLabDetail);

        logger.info("[LabAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("LabDetailAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultLabDetail");
        logger.info("[LabDetailAction.initForm] end process >>>");
        return INPUT;
    }

    public String saveAdd(){
        logger.info("[LabDetailAction.saveAdd] start process >>>");

        try {
            LabDetail labDetail = getLabDetail();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            labDetail.setCreatedWho(userLogin);
            labDetail.setLastUpdate(updateTime);
            labDetail.setCreatedDate(updateTime);
            labDetail.setLastUpdateWho(userLogin);
            labDetail.setAction("C");
            labDetail.setFlag("Y");

            labDetailBoProxy.saveAdd(labDetail);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = labDetailBoProxy.saveErrorMessage(e.getMessage(), "labDetailBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[labDetailAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[labDetailAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultLabDetail");

        logger.info("[labDetailAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public String saveEdit(){
        logger.info("[LabDetailAction.saveEdit] start process >>>");
        try {

            LabDetail editLabDetail = getLabDetail();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editLabDetail.setLastUpdateWho(userLogin);
            editLabDetail.setLastUpdate(updateTime);
            editLabDetail.setAction("U");
            editLabDetail.setFlag("Y");

            labDetailBoProxy.saveEdit(editLabDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = labDetailBoProxy.saveErrorMessage(e.getMessage(), "LabDetailBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[LabDetailAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[LabDetailAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[LabDetailAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[LabDetailAction.saveDelete] start process >>>");
        try {

            LabDetail deleteLabDetail = getLabDetail();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteLabDetail.setLastUpdate(updateTime);
            deleteLabDetail.setLastUpdateWho(userLogin);
            deleteLabDetail.setAction("U");
            deleteLabDetail.setFlag("N");

            labDetailBoProxy.saveDelete(deleteLabDetail);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = labDetailBoProxy.saveErrorMessage(e.getMessage(), "LabDetailBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[LabDetailAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[LabDetailAction.saveDelete] Error when editing item labDetail," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[LabDetailAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public List<LabDetail> listLabDetail(String idLab){

        logger.info("[LabAction.listLabDetail] start process >>>");
        List<LabDetail> labDetailList = new ArrayList<>();
        LabDetail labDetail = new LabDetail();
        labDetail.setIdLab(idLab);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LabDetailBo labDetailBo = (LabDetailBo) ctx.getBean("labDetailBoProxy");

        try {
            labDetailList = labDetailBo.getByCriteria(labDetail);
        }catch (GeneralBOException e){
            logger.error("[LabDetailAction.listLabDetail] Error when get data lab detail ," + "Found problem when searching data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[LabDetailAction.listLabDetail] end process >>>");
        return labDetailList;

    }

    public String initComboLab() {

        Lab lab = new Lab();
        lab.setFlag("Y");

        List<Lab> listOfLab = new ArrayList<Lab>();
        try {
            listOfLab = labBoProxy.getByCriteria(lab);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = labBoProxy.saveErrorMessage(e.getMessage(), "LabBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[LabDetailAction.initComboLab] Error when saving error,", e1);
            }
            logger.error("[LabDetailAction.initComboLab] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboLab.addAll(listOfLab);

        return "init_combo";
    }

    public ImSimrsLabDetailEntity labDetailEntityByIdLab(String idParameter){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LabDetailBo labDetailBo = (LabDetailBo) ctx.getBean("labDetailBoProxy");
        return labDetailBo.getLabDetailEntityById(idParameter);
    }

    public List<LabDetail> getListComboParameter(String idLab){
        logger.info("[LabAction.getListComboParameter] start process >>>");
        List<LabDetail> labDetailList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LabDetailBo labDetailBo = (LabDetailBo) ctx.getBean("labDetailBoProxy");
        String branchId = CommonUtil.userBranchLogin();

        try {
            labDetailList = labDetailBo.getDetaillab(idLab, branchId);
        }catch (GeneralBOException e){
            logger.error("[LabDetailAction.getListComboParameter] Error when get data lab detail ," + "Found problem when searching data, please inform to your admin.", e);
        }

        logger.info("[LabDetailAction.getListComboParameter] end process >>>");
        return labDetailList;

    }

    public LabBo getLabBoProxy() {
        return labBoProxy;
    }

    public void setLabBoProxy(LabBo labBoProxy) {
        this.labBoProxy = labBoProxy;
    }

    public List<Lab> getListOfComboLab() {
        return listOfComboLab;
    }

    public void setListOfComboLab(List<Lab> listOfComboLab) {
        this.listOfComboLab = listOfComboLab;
    }

    public LabDetail getLabDetail() {
        return labDetail;
    }

    public void setLabDetail(LabDetail labDetail) {
        this.labDetail = labDetail;
    }

    public LabDetailBo getLabDetailBoProxy() {
        return labDetailBoProxy;
    }

    public void setLabDetailBoProxy(LabDetailBo labDetailBoProxy) {
        this.labDetailBoProxy = labDetailBoProxy;
    }

}