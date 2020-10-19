package com.neurix.hris.master.studyJurusan.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.studyJurusan.bo.StudyJurusanBo;
import com.neurix.hris.master.studyJurusan.model.StudyJurusan;
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

public class StudyJurusanAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(StudyJurusanAction.class);
    private StudyJurusanBo studyJurusanBoProxy;
    private StudyJurusan studyJurusan;

    private List<StudyJurusan> comboListOfStudyJurusan = new ArrayList<StudyJurusan>();

    public List<StudyJurusan> getComboListOfStudyJurusan() {
        return comboListOfStudyJurusan;
    }

    public void setComboListOfStudyJurusan(List<StudyJurusan> comboListOfStudyJurusan) {
        this.comboListOfStudyJurusan = comboListOfStudyJurusan;
    }

    public StudyJurusanBo getStudyJurusanBoProxy() {
        return studyJurusanBoProxy;
    }

    public void setStudyJurusanBoProxy(StudyJurusanBo studyJurusanBoProxy) {
        this.studyJurusanBoProxy = studyJurusanBoProxy;
    }

    public StudyJurusan getStudyJurusan() {
        return studyJurusan;
    }

    public void setStudyJurusan(StudyJurusan studyJurusan) {
        this.studyJurusan = studyJurusan;
    }

    private List<StudyJurusan> initComboStudyJurusan;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        StudyJurusanAction.logger = logger;
    }


    public List<StudyJurusan> getInitComboStudyJurusan() {
        return initComboStudyJurusan;
    }

    public void setInitComboStudyJurusan(List<StudyJurusan> initComboStudyJurusan) {
        this.initComboStudyJurusan = initComboStudyJurusan;
    }

    public StudyJurusan init(String kode, String flag){
        logger.info("[StudyJurusanAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<StudyJurusan> listOfResult = (List<StudyJurusan>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (StudyJurusan studyJurusan: listOfResult) {
                    if(kode.equalsIgnoreCase(studyJurusan.getJurusanId()) && flag.equalsIgnoreCase(studyJurusan.getFlag())){
                        setStudyJurusan(studyJurusan);
                        break;
                    }
                }
            } else {
                setStudyJurusan(new StudyJurusan());
            }

            logger.info("[StudyJurusanAction.init] end process >>>");
        }
        return getStudyJurusan();
    }

    @Override
    public String add() {
        logger.info("[StudyJurusanAction.add] start process >>>");
        StudyJurusan addStudyJurusan = new StudyJurusan();
        setStudyJurusan(addStudyJurusan);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[StudyJurusanAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[StudyJurusanAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        StudyJurusan editStudyJurusan = new StudyJurusan();

        if(itemFlag != null){
            try {
                editStudyJurusan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = studyJurusanBoProxy.saveErrorMessage(e.getMessage(), "StudyJurusanBO.getStudyJurusanByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[StudyJurusanAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[StudyJurusanAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editStudyJurusan != null) {
                setStudyJurusan(editStudyJurusan);
            } else {
                editStudyJurusan.setFlag(itemFlag);
                editStudyJurusan.setJurusanId(itemId);
                setStudyJurusan(editStudyJurusan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editStudyJurusan.setJurusanId(itemId);
            editStudyJurusan.setFlag(getFlag());
            setStudyJurusan(editStudyJurusan);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[StudyJurusanAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[StudyJurusanAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        StudyJurusan deleteStudyJurusan = new StudyJurusan();

        if (itemFlag != null ) {

            try {
                deleteStudyJurusan = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = studyJurusanBoProxy.saveErrorMessage(e.getMessage(), "StudyJurusanBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[StudyJurusanAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[StudyJurusanAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteStudyJurusan != null) {
                setStudyJurusan(deleteStudyJurusan);

            } else {
                deleteStudyJurusan.setJurusanId(itemId);
                deleteStudyJurusan.setFlag(itemFlag);
                setStudyJurusan(deleteStudyJurusan);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteStudyJurusan.setJurusanId(itemId);
            deleteStudyJurusan.setFlag(itemFlag);
            setStudyJurusan(deleteStudyJurusan);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[StudyJurusanAction.delete] end process <<<");

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
        logger.info("[StudyJurusanAction.saveEdit] start process >>>");
        try {

            StudyJurusan editStudyJurusan = getStudyJurusan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editStudyJurusan.setLastUpdateWho(userLogin);
            editStudyJurusan.setLastUpdate(updateTime);
            editStudyJurusan.setAction("U");
            editStudyJurusan.setFlag("Y");

            studyJurusanBoProxy.saveEdit(editStudyJurusan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyJurusanBoProxy.saveErrorMessage(e.getMessage(), "StudyJurusanBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[StudyJurusanAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[StudyJurusanAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[StudyJurusanAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[StudyJurusanAction.saveDelete] start process >>>");
        try {

            StudyJurusan deleteStudyJurusan = getStudyJurusan();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteStudyJurusan.setLastUpdate(updateTime);
            deleteStudyJurusan.setLastUpdateWho(userLogin);
            deleteStudyJurusan.setAction("U");
            deleteStudyJurusan.setFlag("N");

            studyJurusanBoProxy.saveDelete(deleteStudyJurusan);
        } catch (GeneralBOException e) {
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[StudyJurusanAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[StudyJurusanAction.saveAdd] start process >>>");

        try {
            StudyJurusan studyJurusan = getStudyJurusan();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            studyJurusan.setCreatedWho(userLogin);
            studyJurusan.setLastUpdate(updateTime);
            studyJurusan.setCreatedDate(updateTime);
            studyJurusan.setLastUpdateWho(userLogin);
            studyJurusan.setAction("C");
            studyJurusan.setFlag("Y");

            studyJurusanBoProxy.saveAdd(studyJurusan);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyJurusanBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[StudyJurusanAction.search] start process >>>");

        StudyJurusan searchStudyJurusan = getStudyJurusan();
        List<StudyJurusan> listOfsearchStudyJurusan = new ArrayList();

        try {
            listOfsearchStudyJurusan = studyJurusanBoProxy.getByCriteria(searchStudyJurusan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyJurusanBoProxy.saveErrorMessage(e.getMessage(), "StudyJurusanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StudyJurusanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StudyJurusanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchStudyJurusan);

        logger.info("[StudyJurusanAction.search] end process <<<");

        return SUCCESS;
    }



    public String searchStudyJurusan() {
        logger.info("[StudyJurusanAction.search] start process >>>");

        StudyJurusan searchStudyJurusan = new StudyJurusan();

        searchStudyJurusan.setFlag("Y");
        List<StudyJurusan> listOfsearchStudyJurusan = new ArrayList();

        try {
            listOfsearchStudyJurusan = studyJurusanBoProxy.getByCriteria(searchStudyJurusan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyJurusanBoProxy.saveErrorMessage(e.getMessage(), "StudyJurusanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StudyJurusanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StudyJurusanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        comboListOfStudyJurusan.addAll(listOfsearchStudyJurusan);

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[StudyJurusanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[StudyJurusanAction.initForm] end process >>>");
        return INPUT;
    }

    public String initStudyJurusan() {
        logger.info("[StudyJurusanAction.search] start process >>>");

        StudyJurusan searchStudyJurusan = new StudyJurusan();
        searchStudyJurusan.setFlag("Y");
        List<StudyJurusan> listOfsearchStudyJurusan = new ArrayList();

        try {
            listOfsearchStudyJurusan = studyJurusanBoProxy.getByCriteria(searchStudyJurusan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyJurusanBoProxy.saveErrorMessage(e.getMessage(), "StudyJurusanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StudyJurusanAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StudyJurusanAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultStudyJurusan");
        session.setAttribute("listOfResultStudyJurusan", listOfsearchStudyJurusan);

        logger.info("[StudyJurusanAction.search] end process <<<");

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

    public List<StudyJurusan> getAllData(){
        List<StudyJurusan> studyJurusanList = new ArrayList<>();
        StudyJurusan studyJurusan = new StudyJurusan();

        studyJurusan.setFlag("Y");

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            StudyJurusanBo studyJurusanBo = (StudyJurusanBo) ctx.getBean("studyJurusanBoProxy");
            studyJurusanList = studyJurusanBo.getByCriteria(studyJurusan);
        } catch (GeneralBOException e) {
            logger.error("[StudyJurusanAction.save] Error when searching Study Jurusan by criteria," + "[] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=] Found problem when searching data by criteria, please inform to your admin" );
        }

        return studyJurusanList;
    }
}
