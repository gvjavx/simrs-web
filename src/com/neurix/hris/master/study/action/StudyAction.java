package com.neurix.hris.master.study.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.study.bo.StudyBo;
import com.neurix.hris.master.study.model.Study;
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

public class StudyAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(StudyAction.class);
    private StudyBo studyBoProxy;
    private Study study;

    private List<Study> listComboStudy = new ArrayList<Study>();

    public List<Study> getListComboStudy() {
        return listComboStudy;
    }

    public void setListComboStudy(List<Study> listComboStudy) {
        this.listComboStudy = listComboStudy;
    }

    public StudyBo getStudyBoProxy() {
        return studyBoProxy;
    }

    public void setStudyBoProxy(StudyBo studyBoProxy) {
        this.studyBoProxy = studyBoProxy;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    private List<Study> initComboStudy;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        StudyAction.logger = logger;
    }


    public List<Study> getInitComboStudy() {
        return initComboStudy;
    }

    public void setInitComboStudy(List<Study> initComboStudy) {
        this.initComboStudy = initComboStudy;
    }

    public Study init(String kode, String flag){
        logger.info("[StudyAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Study> listOfResult = (List<Study>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Study study: listOfResult) {
                    if(kode.equalsIgnoreCase(study.getStudyId()) && flag.equalsIgnoreCase(study.getFlag())){
                        setStudy(study);
                        break;
                    }
                }
            } else {
                setStudy(new Study());
            }

            logger.info("[StudyAction.init] end process >>>");
        }
        return getStudy();
    }

    @Override
    public String add() {
        logger.info("[StudyAction.add] start process >>>");
        Study addStudy = new Study();
        setStudy(addStudy);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[StudyAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[StudyAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Study editStudy = new Study();

        if(itemFlag != null){
            try {
                editStudy = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = studyBoProxy.saveErrorMessage(e.getMessage(), "StudyBO.getStudyByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[StudyAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[StudyAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editStudy != null) {
                setStudy(editStudy);
            } else {
                editStudy.setFlag(itemFlag);
                editStudy.setStudyId(itemId);
                setStudy(editStudy);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editStudy.setStudyId(itemId);
            editStudy.setFlag(getFlag());
            setStudy(editStudy);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[StudyAction.edit] end process >>>");
        return "init_edit";
    }


    @Override
    public String delete() {
        logger.info("[StudyAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Study deleteStudy = new Study();

        if (itemFlag != null ) {

            try {
                deleteStudy = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = studyBoProxy.saveErrorMessage(e.getMessage(), "StudyBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[StudyAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[StudyAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteStudy != null) {
                setStudy(deleteStudy);

            } else {
                deleteStudy.setStudyId(itemId);
                deleteStudy.setFlag(itemFlag);
                setStudy(deleteStudy);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteStudy.setStudyId(itemId);
            deleteStudy.setFlag(itemFlag);
            setStudy(deleteStudy);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[StudyAction.delete] end process <<<");

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

    public String saveEdit(String id, String nip, String typeId, String name, String awal, String akhir, String fakultasId, String programStudy){
        logger.info("[StudyAction.saveEdit] start process >>>");
        try {

            Study editStudy = new Study();
            editStudy.setStudyId(id);
            editStudy.setNip(nip);
            editStudy.setTypeStudy(typeId);
            editStudy.setStudyName(name);
            editStudy.setTahunAwal(awal);
            editStudy.setTahunAkhir(akhir);
            editStudy.setProgramStudy(programStudy);
            editStudy.setFakultasId(fakultasId);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editStudy.setLastUpdateWho(userLogin);
            editStudy.setLastUpdate(updateTime);
            editStudy.setAction("U");
            editStudy.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            StudyBo studyBo = (StudyBo) ctx.getBean("studyBoProxy");
            studyBo.saveEdit(editStudy);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "StudyBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[StudyAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StudyAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[StudyAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String initEdit(String id, String typeStudy, String studyName, String tahunAwal, String tahunAkhir){
        logger.info("[StudyAction.saveEdit] start process >>>");
        Study editStudy = new Study();

        editStudy.setStudyId(id);
        editStudy.setTypeStudy(typeStudy);
        editStudy.setStudyName(studyName);
        editStudy.setTahunAwal(tahunAwal);
        editStudy.setTahunAkhir(tahunAkhir);

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Study> listStudy = new ArrayList<>();
        List<Study> listOfResult = (List<Study>) session.getAttribute("listStudy");

        if (id != null && !"".equalsIgnoreCase(id)) {
            if (listOfResult != null) {
                for (Study study : listOfResult) {
                    if (id.equalsIgnoreCase(study.getStudyId())) {
                        listStudy.add(editStudy);
                    } else {
                        listStudy.add(study);
                    }
                }
            }
            logger.info("[StudyAction.init] end process >>>");
        }
        session.removeAttribute("listStudy");
        session.setAttribute("listStudy", listStudy);
        return "";
    }

    public void initDelete(String kode){
        logger.info("[StudyAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Study> listStudy = new ArrayList<>();
        List<Study> listOfResult = (List<Study>) session.getAttribute("listStudy");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Study study: listOfResult) {
                    if(kode.equalsIgnoreCase(study.getStudyId())){
                    }else{
                        listStudy.add(study);
                    }
                }
            }
            logger.info("[KeluargaAction.init] end process >>>");
        }
        session.removeAttribute("listStudy");
        session.setAttribute("listStudy", listStudy);
    }

    public String saveDelete(String id){
        logger.info("[StudyAction.saveDelete] start process >>>");
        try {

            Study deleteStudy = new Study();

            deleteStudy.setStudyId(id);
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteStudy.setLastUpdate(updateTime);
            deleteStudy.setLastUpdateWho(userLogin);
            deleteStudy.setAction("U");
            deleteStudy.setFlag("N");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            StudyBo studyBo = (StudyBo) ctx.getBean("studyBoProxy");

            studyBo.saveDelete(deleteStudy);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "StudyBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[StudyAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StudyAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[StudyAction.saveDelete] end process <<<");

        return "success_save_delete";
    }


    public Study initSearch(String kode){
        logger.info("[StudyAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Study> listOfResult = (List<Study>) session.getAttribute("listStudy");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Study study: listOfResult) {
                    if(kode.equalsIgnoreCase(study.getStudyId())){
                        setStudy(study);
                        break;
                    }
                }
            } else {
                setStudy(new Study());
            }

            logger.info("[KeluargaAction.init] end process >>>");
        }
        return getStudy();
    }

    public String saveAdd(String typeStudy, String studyName, String tahunAwal, String tahunAkhir, String programStudy){
        logger.info("[StudyAction.saveAdd] start process >>>");

        try {
            Study study = new Study();
            study.setTypeStudy(typeStudy);
            study.setStudyName(studyName);
            study.setTahunAwal(tahunAwal);
            study.setTahunAkhir(tahunAkhir);
            study.setProgramStudy(programStudy);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            study.setCreatedWho(userLogin);
            study.setLastUpdate(updateTime);
            study.setCreatedDate(updateTime);
            study.setLastUpdateWho(userLogin);
            study.setAction("C");
            study.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            StudyBo studyBo = (StudyBo) ctx.getBean("studyBoProxy");

            int id = 0;
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<Study> listOfResult = (List<Study>) session.getAttribute("listStudy");
            if(listOfResult != null){
                for(Study study1: listOfResult){
                    id = Integer.parseInt(study1.getStudyId());
                }
                id++;
                study.setStudyId(id + "");
                listOfResult.add(study);
            }else{
                listOfResult = new ArrayList<>();
                study.setStudyId(id + "");
                listOfResult.add(study);
            }
            session.removeAttribute("listStudy");
            session.setAttribute("listStudy", listOfResult);

            //studyBo.saveAdd(study);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        return "";
    }

    public String saveAddData(String nip, String typeStudy, String studyName, String tahunAwal, String tahunAkhir, String fakultasId, String programStudy){
        logger.info("[StudyAction.saveAdd] start process >>>");

        try {
            Study study = new Study();
            study.setNip(nip);
            study.setTypeStudy(typeStudy);
            study.setStudyName(studyName);
            study.setTahunAwal(tahunAwal);
            study.setTahunAkhir(tahunAkhir);
            study.setProgramStudy(programStudy);
            study.setFakultasId(fakultasId);

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            study.setCreatedWho(userLogin);
            study.setLastUpdate(updateTime);
            study.setCreatedDate(updateTime);
            study.setLastUpdateWho(userLogin);
            study.setAction("C");
            study.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            StudyBo studyBo = (StudyBo) ctx.getBean("studyBoProxy");
            studyBo.saveAdd(study);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        return "";
    }

    @Override
    public String search() {
        logger.info("[StudyAction.search] start process >>>");

        Study searchStudy = getStudy();
        List<Study> listOfsearchStudy = new ArrayList();

        try {
            listOfsearchStudy = studyBoProxy.getByCriteria(searchStudy);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "StudyBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StudyAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StudyAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchStudy);

        logger.info("[StudyAction.search] end process <<<");

        return SUCCESS;
    }

    public List<Study> searchData(String nip) {
        logger.info("[StudyAction.search] start process >>>");

        Study searchStudy = new Study();
        searchStudy.setNip(nip);
        searchStudy.setFlag("Y");

        List<Study> listOfsearchStudy = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            StudyBo studyBo = (StudyBo) ctx.getBean("studyBoProxy");
            listOfsearchStudy = studyBo.getByCriteria(searchStudy);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "StudyBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StudyAction.search] Error when saving error,", e1);
            }
            logger.error("[StudyAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return listOfsearchStudy;
    }

    public List<Study> searchDataSession() {
        logger.info("[StudyAction.search] start process >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Study> listOfResult = (List<Study>) session.getAttribute("listStudy");

        return listOfResult;
    }

    public List<Study> searchDataEdit(String id) {
        logger.info("[StudyAction.search] start process >>>");

        Study searchStudy = new Study();
        searchStudy.setStudyId(id);
        searchStudy.setFlag("Y");

        List<Study> listOfsearchStudy = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            StudyBo studyBo = (StudyBo) ctx.getBean("studyBoProxy");
            listOfsearchStudy = studyBo.getByCriteria(searchStudy);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "StudyBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StudyAction.search] Error when saving error,", e1);
            }
            logger.error("[StudyAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return listOfsearchStudy;
    }

    public String searchStudy() {
        logger.info("[StudyAction.search] start process >>>");

        Study searchStudy = new Study();
        searchStudy.setFlag("Y");
        List<Study> listOfsearchStudy = new ArrayList();

        try {
            listOfsearchStudy = studyBoProxy.getByCriteria(searchStudy);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "StudyBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StudyAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StudyAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboStudy.addAll(listOfsearchStudy);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[StudyAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[StudyAction.initForm] end process >>>");
        return INPUT;
    }

    public String initStudy() {
        logger.info("[StudyAction.search] start process >>>");

        Study searchStudy = new Study();
        searchStudy.setFlag("Y");
        List<Study> listOfsearchStudy = new ArrayList();

        try {
            listOfsearchStudy = studyBoProxy.getByCriteria(searchStudy);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = studyBoProxy.saveErrorMessage(e.getMessage(), "StudyBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[StudyAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[StudyAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultStudy");
        session.setAttribute("listOfResultStudy", listOfsearchStudy);

        logger.info("[StudyAction.search] end process <<<");

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
