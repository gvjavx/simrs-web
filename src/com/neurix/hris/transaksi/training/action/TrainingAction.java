package com.neurix.hris.transaksi.training.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.biayapengobatan.bo.BiayaPengobatanBo;
import com.neurix.hris.master.biayapengobatan.model.BiayaPengobatan;
import com.neurix.hris.master.biodata.bo.BiodataBo;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.master.department.bo.DepartmentBo;
import com.neurix.hris.master.department.model.Department;
import com.neurix.hris.transaksi.medicalrecord.bo.MedicalRecordBo;
import com.neurix.hris.transaksi.medicalrecord.model.BuktiPengobatan;
import com.neurix.hris.transaksi.medicalrecord.model.MedicalRecord;
import com.neurix.hris.transaksi.medicalrecord.model.Pengobatan;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.training.bo.TrainingBo;
import com.neurix.hris.transaksi.training.model.Training;
import com.neurix.hris.transaksi.training.model.TrainingDoc;
import com.neurix.hris.transaksi.training.model.TrainingPerson;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by thinkpad on 19/03/2018.
 */
public class TrainingAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(TrainingAction.class);

    private Training training;
    private TrainingPerson trainingPerson;
    private TrainingDoc trainingDoc;
    private TrainingBo trainingBoProxy;
    private NotifikasiBo notifikasiBoProxy;

    private File fileUploadDoc;
    private String fileUploadDocContentType;
    private String fileUploadDocFileName;

    private String id;
    private String view;
    private String delete;
    private String project;
    private String unit;
    private String notif;

    public NotifikasiBo getNotifikasiBoProxy() {
        return notifikasiBoProxy;
    }

    public void setNotifikasiBoProxy(NotifikasiBo notifikasiBoProxy) {
        this.notifikasiBoProxy = notifikasiBoProxy;
    }

    public String getNotif() {
        return notif;
    }

    public void setNotif(String notif) {
        this.notif = notif;
    }

    public TrainingBo getTrainingBoProxy() {
        return trainingBoProxy;
    }

    public void setTrainingBoProxy(TrainingBo trainingBoProxy) {
        this.trainingBoProxy = trainingBoProxy;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public TrainingPerson getTrainingPerson() {
        return trainingPerson;
    }

    public void setTrainingPerson(TrainingPerson trainingPerson) {
        this.trainingPerson = trainingPerson;
    }

    public TrainingDoc getTrainingDoc() {
        return trainingDoc;
    }

    public void setTrainingDoc(TrainingDoc trainingDoc) {
        this.trainingDoc = trainingDoc;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public File getFileUploadDoc() {
        return fileUploadDoc;
    }

    public void setFileUploadDoc(File fileUploadDoc) {
        this.fileUploadDoc = fileUploadDoc;
    }

    public String getFileUploadDocContentType() {
        return fileUploadDocContentType;
    }

    public void setFileUploadDocContentType(String fileUploadDocContentType) {
        this.fileUploadDocContentType = fileUploadDocContentType;
    }

    public String getFileUploadDocFileName() {
        return fileUploadDocFileName;
    }

    public void setFileUploadDocFileName(String fileUploadDocFileName) {
        this.fileUploadDocFileName = fileUploadDocFileName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    private List<BiayaPengobatan> listOfComboBiayaPengobatan = new ArrayList<BiayaPengobatan>();

    public List<BiayaPengobatan> getListOfComboBiayaPengobatan() {
        return listOfComboBiayaPengobatan;
    }

    public void setListOfComboBiayaPengobatan(List<BiayaPengobatan> listOfComboBiayaPengobatan) {
        this.listOfComboBiayaPengobatan = listOfComboBiayaPengobatan;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TrainingAction.logger = logger;
    }


    @Override
    public String add() {
        logger.info("[TrainingAction.add] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultPerson");
        session.removeAttribute("listOfResulDoc");
        logger.info("[TrainingAction.add] end process <<<");
        return "init_add";
    }

    public String initAdd() {
        logger.info("[TrainingAction.initAdd] start process >>>");

        logger.info("[TrainingAction.initAdd] end process <<<");
        return "init_add";
    }

    public TrainingPerson initPerson(String kode) {
        logger.info("[AlatAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TrainingPerson> listOfResultTrainingPerson = (List<TrainingPerson>) session.getAttribute("listOfResultPerson");

        if (kode != null && !"".equalsIgnoreCase(kode)) {
            if (listOfResultTrainingPerson != null) {
                for (TrainingPerson trainingPerson : listOfResultTrainingPerson) {
                    if (kode.equalsIgnoreCase(trainingPerson.getTrainingPersonId())) {
                        setTrainingPerson(trainingPerson);
                        break;
                    }
                }
            } else {
                setTrainingPerson(new TrainingPerson());
            }

            logger.info("[AlatAction.init] end process >>>");
        }
        return getTrainingPerson();
    }

    @Override
    public String edit() {
        logger.info("[TrainingAction.edit] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Training> listOfTraining = (List<Training>) session.getAttribute("listOfResult");
        List<TrainingPerson> listOfTrainingPeson = new ArrayList<TrainingPerson>();
        List<TrainingDoc> listOfTrainingDoc = new ArrayList<TrainingDoc>();

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        String id = getId();
        String delete = getDelete();
        if (listOfTraining != null){
            for (Training training : listOfTraining){
                if (id.equalsIgnoreCase(training.getTrainingId())){
                    training.setTrainingId(id);

                    if (training.getTrainingStartDate() != null){
                        String stDate = df.format(training.getTrainingStartDate());
                        training.setStTanggalStart(stDate);
                    }

                    if (training.getTrainingEndDate() != null){
                        String stDate = df.format(training.getTrainingEndDate());
                        training.setStTanggalEnd(stDate);
                    }

                    setTraining(training);
                }
            }

            TrainingPerson trainingPerson = new TrainingPerson();
            trainingPerson.setTrainingId(id);
            trainingPerson.setFlag("Y");
            try {
                listOfTrainingPeson = trainingBoProxy.searchTrainingPerson(trainingPerson);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "TrainingBo.searchPengobatan");
                } catch (GeneralBOException e1) {
                    logger.error("[TrainingAction.initApprove] Error when saving error,", e1);
                }
                logger.error("[TrainingAction.initApprove] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                return "failure";
            }


            if (listOfTrainingPeson != null){
                for (TrainingPerson listData : listOfTrainingPeson){
                    TrainingDoc trainingDoc = new TrainingDoc();
                    trainingDoc.setTrainingId(listData.getTrainingId());
                    trainingDoc.setTrainingPersonId(listData.getTrainingPersonId());
                    trainingDoc.setFlag("Y");

                    List<TrainingDoc> trainingDocList = new ArrayList<TrainingDoc>();

                    try {
                        trainingDocList = trainingBoProxy.searchTrainingDoc(trainingDoc);
                    } catch (GeneralBOException e) {
                        Long logId = null;
                        try {
                            logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "TrainingBo.searchPengobatan");
                        } catch (GeneralBOException e1) {
                            logger.error("[TrainingAction.initApprove] Error when saving error,", e1);
                        }
                        logger.error("[TrainingAction.initApprove] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                        addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                        return "failure";
                    }

                    if (trainingDocList != null){
                        for (TrainingDoc listDoc : trainingDocList){
                            listOfTrainingDoc.add(listDoc);
                        }
                    }
                }
            }

        } else {
            setTraining(new Training());
        }

        session.removeAttribute("listOfResultPerson");
        session.setAttribute("listOfResultPerson",listOfTrainingPeson);
        session.removeAttribute("listOfResultDoc");
        session.setAttribute("listOfResultDoc",listOfTrainingDoc);

        if("Y".equalsIgnoreCase(delete)){
            logger.info("[TrainingAction.delete] end process >>>");
            return "init_delete";
        }else {
            logger.info("[TrainingAction.edit] end process >>>");
            return "init_edit";
        }
    }

    public String initEdit(){
        logger.info("[TrainingAction.initEdit] start process >>>");

        logger.info("[TrainingAction.initEdit] end process <<<");
        return "init_edit";
    }

    public List<TrainingPerson> searchData(String nip) {
        logger.info("[TrainingAction.search] start process >>>");

        List<TrainingPerson> listOfsearchTraining = new ArrayList();

        try {
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TrainingBo trainingBo = (TrainingBo) ctx.getBean("trainingBoProxy");
            listOfsearchTraining = trainingBo.searchTrainingPerson(nip);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "TrainingBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TrainingAction.search] Error when saving error,", e1);
            }
            logger.error("[TrainingAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return listOfsearchTraining;
    }
    
    @Override
    public String delete() {
        return null;
    }

    @Override
    public String view() {
        return null;
    }


    @Override
    public String save() {
        logger.info("[TrainingAction.save] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Notifikasi> notifikasiList = new ArrayList<>();
        List<TrainingPerson> listOfResultPerson = (List<TrainingPerson>) session.getAttribute("listOfResultPerson");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());

//        String thn = date.substring(0,4);
//        String bln = date.substring(5,7);
        String id = date.substring(2,4);


        Training training = getTraining();
        training.setTrainingId("T"+id);

        Date dateStart = CommonUtil.convertToDate(training.getStTanggalStart());
        Date dateEnd = CommonUtil.convertToDate(training.getStTanggalEnd());

        training.setTrainingStartDate(dateStart);
        training.setTrainingEndDate(dateEnd);
        training.setCreatedDate(updateTime);
        training.setCreatedWho(userLogin);
        training.setLastUpdate(updateTime);
        training.setLastUpdateWho(userLogin);
        training.setFlag("Y");
        training.setAction("C");

        try {
            notifikasiList=trainingBoProxy.saveAddTraining(training, listOfResultPerson);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "TrainingBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TrainingAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TrainingAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        for (Notifikasi notifikasi : notifikasiList){
            notifikasiBo.sendNotif(notifikasi);
        }

        logger.info("[TrainingAction.save] end process <<<");
        return "success_add";
    }


    public String saveEdit(){
        logger.info("[TrainingAction.saveEdit] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TrainingPerson> listOfResultPerson = (List<TrainingPerson>) session.getAttribute("listOfResultPerson");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        Training training = getTraining();

        Date dateStart = CommonUtil.convertToDate(training.getStTanggalStart());
        Date dateEnd = CommonUtil.convertToDate(training.getStTanggalEnd());

        training.setTrainingStartDate(dateStart);
        training.setTrainingEndDate(dateEnd);
        training.setCreatedDate(updateTime);
        training.setCreatedWho(userLogin);
        training.setLastUpdate(updateTime);
        training.setLastUpdateWho(userLogin);
        training.setFlag("Y");
        training.setAction("U");

        for (TrainingPerson listPerson : listOfResultPerson){
            if (listPerson.isAdd()){
                listPerson.setCreatedDate(updateTime);
                listPerson.setLastUpdate(updateTime);
                listPerson.setCreatedWho(userLogin);
                listPerson.setLastUpdateWho(userLogin);
                listPerson.setFlag("Y");
                listPerson.setAction("C");
            } else {
                listPerson.setLastUpdate(updateTime);
                listPerson.setLastUpdateWho(userLogin);
                listPerson.setAction("U");
            }
        }


        try {
            trainingBoProxy.saveUpdateTraining(training, listOfResultPerson);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "TrainingBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TrainingAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TrainingAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        logger.info("[TrainingAction.saveEdit] end process <<<");
        return "success_edit";
    }

    public String saveDelete(){
        logger.info("[TrainingAction.saveDelete] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TrainingPerson> listOfResultPerson = (List<TrainingPerson>) session.getAttribute("listOfResultPerson");
        List<TrainingDoc> listOfResultDoc = (List<TrainingDoc>) session.getAttribute("listOfResultDoc");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        Training training = getTraining();

        Date dateStart = CommonUtil.convertToDate(training.getStTanggalStart());
        Date dateEnd = CommonUtil.convertToDate(training.getStTanggalEnd());

        training.setTrainingStartDate(dateStart);
        training.setTrainingEndDate(dateEnd);
        training.setCreatedDate(updateTime);
        training.setCreatedWho(userLogin);
        training.setLastUpdate(updateTime);
        training.setLastUpdateWho(userLogin);
        training.setFlag("N");
        training.setAction("U");

        for (TrainingPerson listPerson : listOfResultPerson){
                listPerson.setCreatedDate(updateTime);
                listPerson.setLastUpdate(updateTime);
                listPerson.setCreatedWho(userLogin);
                listPerson.setLastUpdateWho(userLogin);
                listPerson.setFlag("N");
                listPerson.setAction("U");
        }

        try {
            trainingBoProxy.saveUpdateTraining(training, listOfResultPerson);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "TrainingBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TrainingAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TrainingAction.saveDelete] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        if (listOfResultDoc != null){
            for (TrainingDoc listDoc : listOfResultDoc){
                listDoc.setLastUpdate(updateTime);
                listDoc.setLastUpdateWho(userLogin);
                listDoc.setFlag("N");
                listDoc.setAction("U");
            }
            try {
                trainingBoProxy.saveDocTraining(listOfResultDoc);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "TrainingBo.saveDelete");
                } catch (GeneralBOException e1) {
                    logger.error("[TrainingAction.saveDelete] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[TrainingAction.saveDelete] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
                return ERROR;
            }
        }

        logger.info("[TrainingAction.saveDelete] end process <<<");
        return "success_delete";
    }

    @Override
    public String search() {
        logger.info("[TrainingAction.search] start process >>>");

        Training search = getTraining();
        List<Training> listOfSearch = new ArrayList();

        try {
            listOfSearch = trainingBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "TrainingBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TrainingAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[TrainingAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearch);

        logger.info("[BiayaPengobatanAction.search] end process <<<");
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[TrainingAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[BiayaPengobatanAction.initForm] end process >>>");
        return INPUT;
    }

    public List initComboPersonil(String query, String branchId) {
        logger.info("[TrainingAction.initComboLokasiKebun] start process >>>");

        List<Biodata> listOfUser = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
//        HttpSession session = WebContextFactory.get().getSession();


        try {
            listOfUser = biodataBo.getListOfPersonil(query,branchId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = biodataBo.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[PermohonanLahanAction.initComboLokasiKebun] end process <<<");

        return listOfUser;
    }

    public String addPerson(){
        logger.info("[TrainingAction.addPerson] start process >>>");
        TrainingPerson trainingPerson = new TrainingPerson();
//        String unit = getUnit();
//
//        if (unit != null){
//            trainingPerson.setUnitId(unit);
//        }

        setTrainingPerson(trainingPerson);
        logger.info("[addPerson.addPerson] end process >>>");
        return "init_add_person";
    }

    public String editAddPerson(){
        logger.info("[TrainingAction.editAddPerson] start process >>>");
        TrainingPerson trainingPerson = new TrainingPerson();

        trainingPerson = initPerson(getId());
        setTrainingPerson(trainingPerson);
        logger.info("[addPerson.editAddPerson] end process >>>");
        return "init_delete_person";
    }
    public String addPersonEdit(){
        logger.info("[TrainingAction.addPerson] start process >>>");
        TrainingPerson trainingPerson = new TrainingPerson();
        Training training = getTraining();
        String unit = getUnit();
        String project = getProject();

        if (project != null){
            trainingPerson.setProject(project);
        }

        setTrainingPerson(trainingPerson);

        logger.info("[addPerson.addPerson] end process >>>");
        return "init_add_person";
    }

    public String saveAddPersonAdd(String personId, String personName, String project){
        boolean isNew = false;
        boolean flag = false;
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TrainingPerson> listOfResultPerson = (List<TrainingPerson>) session.getAttribute("listOfResultPerson");
        List<TrainingPerson> listOfResultPersonNew = new ArrayList<TrainingPerson>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TrainingBo trainingBo = (TrainingBo) ctx.getBean("trainingBoProxy");
        BiodataBo biodataBo = (BiodataBo) ctx.getBean("biodataBoProxy");
        DepartmentBo departmentBo = (DepartmentBo) ctx.getBean("departmentBoProxy");

        String divisiId = "";
        String divisiName = "";

        if (personId != null){

            List<Biodata> biodatas = new ArrayList<Biodata>();
            Biodata biodata = new Biodata();
            biodata.setFlag("Y");
            biodata.setNip(personId);
            biodatas = biodataBo.getByCriteria(biodata);

            if (biodatas != null){
                for (Biodata listBiodata : biodatas){
                    if (listBiodata.getDivisi() != null){
                        List<Department> departments = new ArrayList<Department>();
                        Department department = new Department();
                        department.setDepartmentId(listBiodata.getDivisi());
                        departments = departmentBo.getByCriteria(department);
                        if (departments!=null){
                            for(Department listDepartment : departments){
                                divisiId = listDepartment.getDepartmentId();
                                divisiName = listDepartment.getDepartmentName();
                            }
                        }
                    }
                }
            }

            if (listOfResultPerson != null){
                String id = "";
                id = trainingBo.getNextPersonId();
                if (id!= null){
                    TrainingPerson trainingPerson = new TrainingPerson();
                    trainingPerson.setTrainingPersonId("TPS"+id);
                    trainingPerson.setPersonId(personId);
                    trainingPerson.setPersonName(personName);
                    trainingPerson.setDivisiId(divisiId);
                    trainingPerson.setDivisiName(divisiName);
                    trainingPerson.setAdd(true);
                    trainingPerson.setStatus("ACTIVE");
                    listOfResultPerson.add(trainingPerson);
                    flag = true;
                }
            } else {
                String id = "";
                id = trainingBo.getNextPersonId();
                if (id!= null){
                    TrainingPerson trainingPerson = new TrainingPerson();
                    trainingPerson.setTrainingPersonId("TPS"+id);
                    trainingPerson.setPersonId(personId);
                    trainingPerson.setPersonName(personName);
                    trainingPerson.setDivisiId(divisiId);
                    trainingPerson.setDivisiName(divisiName);
                    trainingPerson.setAdd(true);
                    trainingPerson.setStatus("ACTIVE");
                    listOfResultPersonNew.add(trainingPerson);
                    isNew = true;
                    flag = true;
                }
            }
        }
        if (flag){
            if (isNew){
                session.removeAttribute("listOfResultPerson");
                session.setAttribute("listOfResultPerson", listOfResultPersonNew);
                if ("edit".equalsIgnoreCase(project)){
                    return "02";
                } else {
                    return "00";
                }

            } else {
                session.removeAttribute("listOfResultPerson");
                session.setAttribute("listOfResultPerson", listOfResultPerson);
                if ("edit".equalsIgnoreCase(project)){
                    return "02";
                } else {
                    return "00";
                }
            }
        } else {
            return "01";
        }
    }

    public String saveAddPersonDelete(String personId){
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TrainingPerson> listOfResultPerson = (List<TrainingPerson>) session.getAttribute("listOfResultPerson");
        List<TrainingPerson> listOfResultPersonTmp = new ArrayList<>();
        if (!("").equalsIgnoreCase(personId)){
            for (TrainingPerson trainingPerson : listOfResultPerson){
                if (!personId.equalsIgnoreCase(trainingPerson.getPersonId())){
                    listOfResultPersonTmp.add(trainingPerson);
                }
            }
            session.removeAttribute("listOfResultPerson");
            session.setAttribute("listOfResultPerson",listOfResultPersonTmp);
            return "00";
        }else{
            return "01";
        }
    }

    public String approveAtasan(){
        logger.info("[TrainingAction.approveAtasan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TrainingPerson> listOfResultPerson = (List<TrainingPerson>) session.getAttribute("listOfResultPerson");
        String id = getId();
        if (listOfResultPerson != null){
            for (TrainingPerson trainingPerson:listOfResultPerson){
                if (id.equalsIgnoreCase(trainingPerson.getTrainingPersonId())){
                    trainingPerson.setStTrainingStartdate(CommonUtil.convertDateToString(trainingPerson.getTrainingStartdate()));
                    trainingPerson.setStTrainingEndDate(CommonUtil.convertDateToString(trainingPerson.getTrainingEndDate()));
                    setTrainingPerson(trainingPerson);
                }
            }
        } else {
            setTrainingPerson(new TrainingPerson());
        }
        logger.info("[TrainingAction.approveAtasan] end process >>>");
        return "init_approve_atasan";
    }

    public String saveApprove(){
        logger.info("[TrainingAction.saveApprove] start process >>>");

        boolean atasan = false;

        String userLogin = CommonUtil.userLogin();
        String branchId = CommonUtil.userBranchLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        TrainingPerson trainingPerson = getTrainingPerson();

        if (trainingPerson != null){
            trainingPerson.setLastUpdate(updateTime);
            trainingPerson.setLastUpdateWho(userLogin);

            if (trainingPerson.getFlagApprove().equalsIgnoreCase("N")){
                if (trainingPerson.getApprovalFlag().equalsIgnoreCase("")){
                    trainingPerson.setApprovalBosFlag("N");
                    trainingPerson.setNotApprovalBosNote(trainingPerson.getNote());
                } else {
                    trainingPerson.setApprovalFlag("N");
                    trainingPerson.setNotApprovalNote(trainingPerson.getNote());
                }
            } else {
                if (trainingPerson.getApprovalFlag().equalsIgnoreCase("")){
                    trainingPerson.setApprovalFlag("Y");
                    trainingPerson.setNotApprovalNote("");
                    trainingPerson.setApprovalDate(updateTime);
                    trainingPerson.setApprovalName(userLogin);
                    trainingPerson.setUnitId(branchId);
                    atasan = true;

                } else {

                    trainingPerson.setApprovalBosFlag("Y");
                    trainingPerson.setNotApprovalBosNote("");
                    trainingPerson.setApprovalBosDate(updateTime);
                    trainingPerson.setApprovalBosName(userLogin);
                    trainingPerson.setUnitId(branchId);
                }
            }
            trainingPerson.setLastUpdate(updateTime);
            trainingPerson.setLastUpdateWho(userLogin);
        }

        if (atasan){
            try {
                trainingBoProxy.saveApproveAtasan(trainingPerson);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
                }
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
            }
        } else{
            try {
                trainingBoProxy.saveApproveKepala(trainingPerson);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
                }
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
            }
        }

        logger.info("[TrainingAction.saveApprove] end process >>>");
        return "save_approve_atasan";
    }

    public String initUpload(){
        logger.info("[TrainingAction.initUpload] start process >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TrainingPerson> listOfResultPerson = (List<TrainingPerson>) session.getAttribute("listOfResultPerson");
        String id = getId();
        if (listOfResultPerson != null){
            for (TrainingPerson trainingPerson:listOfResultPerson){
                if (id.equalsIgnoreCase(trainingPerson.getTrainingPersonId())){
                    if (trainingPerson.getTrainingStartdate()!=null){
                        trainingPerson.setStTrainingStartdate(CommonUtil.convertDateToString(trainingPerson.getTrainingStartdate()));
                    }
                    if (trainingPerson.getTrainingEndDate()!=null){
                        trainingPerson.setStTrainingEndDate(CommonUtil.convertDateToString(trainingPerson.getTrainingEndDate()));
                    }
                    setTrainingPerson(trainingPerson);
                }
            }
        } else {
            setTrainingPerson(new TrainingPerson());
        }

        logger.info("[TrainingAction.initUpload] end process >>>");
        return "init_upload";
    }

    public String saveUpload(){
        logger.info("[TrainingAction.saveUpload] start process >>>");

        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        List<TrainingDoc> trainingDocList = new ArrayList<TrainingDoc>();

        TrainingPerson trainingPerson = getTrainingPerson();
        TrainingDoc trainingDoc = new TrainingDoc();

        if (trainingPerson != null){
            trainingDoc.setTrainingId(trainingPerson.getTrainingId());
            trainingDoc.setTrainingPersonId(trainingPerson.getTrainingPersonId());
            trainingDoc.setNote(trainingPerson.getNote());
            trainingDoc.setCreatedDate(updateTime);
            trainingDoc.setCreatedWho(userLogin);
            trainingDoc.setLastUpdate(updateTime);
            trainingDoc.setLastUpdateWho(userLogin);
            trainingDoc.setFlag("Y");
            trainingDoc.setAction("C");
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TrainingBo trainingBo = (TrainingBo) ctx.getBean("trainingBoProxy");

        String fileContentType = this.fileUploadDocContentType;
        String fileName = this.fileUploadDocFileName;
        File fileUpload = this.fileUploadDoc;

        if (fileUpload != null){



            String idDoc = "";
            idDoc = trainingBo.getDocId();
            trainingDoc.setTrainingDocId("DT"+idDoc);

            File fileToCreate = null;
            String filePath = CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_DIRECTORY + ServletActionContext.getRequest().getContextPath() + CommonConstant.RESOURCE_PATH_USER_UPLOAD_DOC;

            if ("image/jpeg".equalsIgnoreCase(fileContentType)) {
                String fileDocName = "IMG_" + trainingDoc.getTrainingDocId() + "_" + fileName;
                fileToCreate = new File(filePath, fileDocName);
                try {
                    FileUtils.copyFile(fileUpload, fileToCreate);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                trainingDoc.setFileUploadDoc(fileDocName);
            }

            if ("application/pdf".equalsIgnoreCase(fileContentType)) {
                String fileDocName = "PDF_" + trainingDoc.getTrainingDocId() + "_" + fileName;
                fileToCreate = new File(filePath, fileDocName);
                try {
                    FileUtils.copyFile(fileUpload, fileToCreate);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                trainingDoc.setFileUploadDoc(fileDocName);
            }

            if ("image/png".equalsIgnoreCase(fileContentType)) {
                String fileDocName = "IMG_" + trainingDoc.getTrainingDocId() + "_" + fileName;
                fileToCreate = new File(filePath, fileDocName);
                try {
                    FileUtils.copyFile(fileUpload, fileToCreate);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                trainingDoc.setFileUploadDoc(fileDocName);
            }
            trainingDoc.setAdd(true);
        }

        trainingDocList.add(trainingDoc);

        try {
            trainingBoProxy.saveDocTraining(trainingDocList);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "DesaBo.getComboDesaWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when saving error,", e1);
            }
            logger.error("[PermohonanLahanAction.initComboLokasiKebun] Error when get combo lokasi kebun," + "[" + logId + "] Found problem when retrieving combo lokasi kebun data, please inform to your admin.", e);
        }

        logger.info("[TrainingAction.saveUpload] end process >>>");
        return "init_upload";
    }

    public String initClose(){
        logger.info("[TrainingAction.initClose] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Training> listOfTraining = (List<Training>) session.getAttribute("listOfResult");
        List<TrainingPerson> listOfTrainingPeson = new ArrayList<TrainingPerson>();
        List<TrainingDoc> listOfTrainingDoc = new ArrayList<TrainingDoc>();

        String view = getView();
        String id = getId();
        String delete = getDelete();
        if (listOfTraining != null){
            for (Training training : listOfTraining){
                if (id.equalsIgnoreCase(training.getTrainingId())){
                    training.setTrainingId(id);

                    if (training.getTrainingStartDate() != null){
                        String stDate = CommonUtil.convertDateToString(training.getTrainingStartDate());
                        training.setStTanggalStart(stDate);
                    }

                    if (training.getTrainingEndDate() != null){
                        String stDate = CommonUtil.convertDateToString(training.getTrainingEndDate());
                        training.setStTanggalEnd(stDate);
                    }

                    setTraining(training);
                }
            }

            TrainingPerson trainingPerson = new TrainingPerson();
            trainingPerson.setTrainingId(id);
            trainingPerson.setFlag("Y");
            try {
                listOfTrainingPeson = trainingBoProxy.searchTrainingPerson(trainingPerson);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "TrainingBo.searchPengobatan");
                } catch (GeneralBOException e1) {
                    logger.error("[TrainingAction.initApprove] Error when saving error,", e1);
                }
                logger.error("[TrainingAction.initApprove] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                return "failure";
            }


            if (listOfTrainingPeson != null){
                for (TrainingPerson listData : listOfTrainingPeson){
                    listData.setStatus("ACTIVE");

                    TrainingDoc trainingDoc = new TrainingDoc();
                    trainingDoc.setTrainingId(listData.getTrainingId());
                    trainingDoc.setTrainingPersonId(listData.getTrainingPersonId());
                    trainingDoc.setFlag("Y");

                    List<TrainingDoc> trainingDocList = new ArrayList<TrainingDoc>();

                    try {
                        trainingDocList = trainingBoProxy.searchTrainingDoc(trainingDoc);
                    } catch (GeneralBOException e) {
                        Long logId = null;
                        try {
                            logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "TrainingBo.searchPengobatan");
                        } catch (GeneralBOException e1) {
                            logger.error("[TrainingAction.initApprove] Error when saving error,", e1);
                        }
                        logger.error("[TrainingAction.initApprove] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                        addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
                        return "failure";
                    }

                    if (trainingDocList != null){
                        for (TrainingDoc listDoc : trainingDocList){
                            listOfTrainingDoc.add(listDoc);
                        }
                    }
                }
            }
        } else {
            setTraining(new Training());
        }

        session.removeAttribute("listOfResultPerson");
        session.setAttribute("listOfResultPerson",listOfTrainingPeson);
        session.removeAttribute("listOfResultDoc");
        session.setAttribute("listOfResultDoc",listOfTrainingDoc);

        logger.info("[TrainingAction.initClose] end process >>>");

        if ("APR".equalsIgnoreCase(view)){
            return "init_approve";
        } else if ("Y".equalsIgnoreCase(view)){
            return "init_view";
        } else {
            return "init_closed";
        }
    }

    public String saveClose(){
        logger.info("[TrainingAction.saveClose] start process >>>");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        Training training = getTraining();
        training.setClosed("Y");
        training.setLastUpdate(updateTime);
        training.setLastUpdateWho(userLogin);

        try {
            trainingBoProxy.saveCloseTraining(training);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "TrainingBo.searchPengobatan");
            } catch (GeneralBOException e1) {
                logger.error("[TrainingAction.initApprove] Error when saving error,", e1);
            }
            logger.error("[TrainingAction.initApprove] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        logger.info("[TrainingAction.saveClose] end process >>>");
        return "init_closed";
    }

    public String viewDoc(){
        logger.info("[BiayaPengobatanAction.viewDoc] start process >>>");
        String id = getId();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TrainingDoc> listOfDoc = (List<TrainingDoc>) session.getAttribute("listOfResultDoc");

        boolean isImg = false;

        for (TrainingDoc listData : listOfDoc){
            if (id.equalsIgnoreCase(listData.getTrainingDocId())){

                String fileName = listData.getFileUploadDoc();
                String subFileName = fileName.substring(0,3);

                if ("IMG".equalsIgnoreCase(subFileName)){
                    listData.setFilePath("/hris/pages/upload/doc/"+fileName);
                    isImg = true;
                }
                if ("PDF".equalsIgnoreCase(subFileName)){
                    listData.setFilePath("/hris/pages/upload/doc/"+fileName);
                }
                setTrainingDoc(listData);
            }
        }

        logger.info("[BiayaPengobatanAction.viewDoc] start process >>>");
        if (isImg){
            return "init_view_img";
        } else {
            return "init_view_pdf";
        }
    }

    public String initApproveSdm(){
        logger.info("[TrainingAction.initApproveSdm] start process >>>");

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<TrainingPerson> listOfResultPerson = (List<TrainingPerson>) session.getAttribute("listOfResultPerson");
        String id = getId();
        if (listOfResultPerson != null){
            for (TrainingPerson trainingPerson:listOfResultPerson){
                if (id.equalsIgnoreCase(trainingPerson.getTrainingPersonId())){

                    List<Training> trainings = new ArrayList<>();
                    Training training = new Training();
                    training.setTrainingId(trainingPerson.getTrainingId());

                    trainings = trainingBoProxy.getByCriteria(training);
                    if (trainings != null){
                        for (Training listTraining : trainings){
                            trainingPerson.setTrainingName(listTraining.getTrainingName());
                            trainingPerson.setTrainingStartdate(listTraining.getTrainingStartDate());
                            trainingPerson.setTrainingEndDate(listTraining.getTrainingEndDate());
                            trainingPerson.setStTrainingStartdate(CommonUtil.convertDateToString(trainingPerson.getTrainingStartdate()));
                            trainingPerson.setStTrainingEndDate(CommonUtil.convertDateToString(trainingPerson.getTrainingEndDate()));
                        }
                    }

                    setTrainingPerson(trainingPerson);
                }
            }
        } else {
            setTrainingPerson(new TrainingPerson());
        }

        logger.info("[TrainingAction.initApproveSdm] end process >>>");
        return "init_approve_sdm";
    }

    public String saveApproveSdm(){
        logger.info("[TrainingAction.saveApproveSdm] start process >>>");
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        List<Notifikasi> notifikasiList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

        TrainingPerson trainingPerson = getTrainingPerson();
        trainingPerson.setApprovalSdm("Y");
        trainingPerson.setApprovalSdmDate(updateTime);

        try {
            notifikasiList=trainingBoProxy.saveApproveSdm(trainingPerson);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = trainingBoProxy.saveErrorMessage(e.getMessage(), "TrainingBo.searchPengobatan");
            } catch (GeneralBOException e1) {
                logger.error("[TrainingAction.initApprove] Error when saving error,", e1);
            }
            logger.error("[TrainingAction.initApprove] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }
        for (Notifikasi notifikasi : notifikasiList){
            notifikasiBo.sendNotif(notifikasi);
        }
        logger.info("[TrainingAction.saveApproveSdm] start process >>>");
        return "init_approve_sdm";
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
