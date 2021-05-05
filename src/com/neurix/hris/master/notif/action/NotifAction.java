package com.neurix.hris.master.notif.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.group.bo.GroupBo;
import com.neurix.hris.master.group.model.Group;
import com.neurix.hris.master.notif.bo.NotifBo;
import com.neurix.hris.master.notif.model.ImNotif;
import com.neurix.hris.master.notif.model.Notif;
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
 * Created by thinkpad on 19/03/2018.
 */
public class NotifAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(NotifAction.class);

    private Notif notif;
    private NotifBo notifBoProxy;
    private String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        NotifAction.logger = logger;
    }

    public Notif getNotif() {
        return notif;
    }

    public void setNotif(Notif notif) {
        this.notif = notif;
    }

    public NotifBo getNotifBoProxy() {
        return notifBoProxy;
    }

    public void setNotifBoProxy(NotifBo notifBoProxy) {
        this.notifBoProxy = notifBoProxy;
    }

//    public TrainingDocDao init(String kode, String flag){
//        logger.info("[GroupShiftAction.init] start process >>>");
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        List<TrainingDocDao> listOfResult = (List<TrainingDocDao>) session.getAttribute("listOfResult");
//
//        if(kode != null && !"".equalsIgnoreCase(kode)){
//            if(listOfResult != null){
//                for (TrainingDocDao groupShift: listOfResult) {
//                    if(kode.equalsIgnoreCase(groupShift.getGroupId()) && flag.equalsIgnoreCase(groupShift.getFlag())){
//                        setGroup(groupShift);
//                        break;
//                    }
//                }
//            } else {
//                setGroup(new TrainingDocDao());
//            }
//
//            logger.info("[GroupShiftAction.init] end process >>>");
//        }
//        return getGroup();
//    }

    @Override
    public String add() {
        return "init_add";
    }

    @Override
    public String edit() {
        return "init_edit";
    }

    @Override
    public String delete() {
        return "init_delete";
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        logger.info("[BiayaPengobatanAction.saveAdd] start process >>>");
        return "save_add";
    }

    public String saveEdit(){
        logger.info("[BiayaPengobatanAction.saveEdit] start process >>>");
        return "save_edit";
    }

    public String saveDelete(){
        return "save_delete";
    }

    @Override
    public String search() {
        logger.info("[BiayaPengobatanAction.search] start process >>>");

//        TrainingDocDao searchShift = getGroup();
//        List<TrainingDocDao> listOfSearchGroupShift = new ArrayList();
//
//        try {
//            listOfSearchGroupShift = groupBoProxy.getByCriteria(searchShift);
//        } catch (GeneralBOException e) {
//            Long logId = null;
//            try {
//                logId = groupBoProxy.saveErrorMessage(e.getMessage(), "GroupShift.getByCriteria");
//            } catch (GeneralBOException e1) {
//                logger.error("[GroupShiftAction.search] Error when saving error,", e1);
//                return ERROR;
//            }
//            logger.error("[GroupShiftAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
//            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
//            return ERROR;
//        }
//
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");
//        session.setAttribute("listOfResult", listOfSearchGroupShift);

        logger.info("[BiayaPengobatanAction.search] end process <<<");
        return SUCCESS;
    }

    public List<Notif> searchNotif(){

        List<Notif> listOfResult = new ArrayList<Notif>();
        Notif notif = new Notif();
        notif.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifBo notifBo = (NotifBo) ctx.getBean("notifBoProxy");

        try {
            listOfResult = notifBo.getByCriteria(notif);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifBo.saveErrorMessage(e.getMessage(), "GroupShift.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[GroupShiftAction.search] Error when saving error,", e1);
//                return ERROR;
            }
            logger.error("[GroupShiftAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
//            return ERROR;
        }

        return listOfResult;
    }

    public List<Notif> getCountNotif(){
        String jml = "";
        List<Notif> listOfResult = new ArrayList<Notif>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifBo notifBo = (NotifBo) ctx.getBean("notifBoProxy");

        try {
            listOfResult = notifBo.getCount();
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifBo.saveErrorMessage(e.getMessage(), "GroupShift.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[NotifAction.getCountNotif] Error when saving error,", e1);
//                return ERROR;
            }
            logger.error("[NotifAction.getCountNotif] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
//            return ERROR;
        }
        return listOfResult;
    }

    @Override
    public String initForm(){
        logger.info("[BiayaPengobatanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[BiayaPengobatanAction.initForm] end process >>>");
        return INPUT;
    }

    public String viewAndReadNotif(String id){
        logger.info("[NotifAction.viewAndReadNotif] start process >>>");
        boolean flag = false;
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        Notif notif = new Notif();
        notif.setNotifId(id);
        notif.setFlag("Y");
        notif.setLastUpdate(updateTime);
        notif.setLastUpdateWho(userLogin);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifBo notifBo = (NotifBo) ctx.getBean("notifBoProxy");

        List<Notif> notifList = new ArrayList<Notif>();

        try {
            notifList = notifBo.getByCriteria(notif);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifBo.saveErrorMessage(e.getMessage(), "GroupShift.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[GroupShiftAction.search] Error when saving error,", e1);
                flag = false;
            }
            logger.error("[GroupShiftAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            flag = false;
        }

        if (notifList != null){
            Notif dataNotif = new Notif();
            for (Notif listNotif : notifList){
                dataNotif.setNotifId(listNotif.getNotifId());
                dataNotif.setNotifName(listNotif.getNotifName());
                dataNotif.setUrl(listNotif.getUrl());
                dataNotif.setTypeNotif(listNotif.getTypeNotif());
                dataNotif.setCreatedDate(listNotif.getCreatedDate());
                dataNotif.setCreatedWho(listNotif.getCreatedWho());
                dataNotif.setLastUpdate(listNotif.getLastUpdate());
                dataNotif.setLastUpdate(updateTime);
                dataNotif.setLastUpdateWho(userLogin);
                dataNotif.setFlag("N");
                dataNotif.setAction("U");
            }
            try {
                notifBo.getReadNotif(dataNotif);
                flag = true;
            } catch (GeneralBOException e){
                Long logId = null;
                try {
                    logId = notifBo.saveErrorMessage(e.getMessage(), "GroupShift.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[GroupShiftAction.search] Error when saving error,", e1);
                    flag = false;
                }
                logger.error("[GroupShiftAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
                flag = false;
            }
        }

        if (flag){
            return "00";
        } else {
            return "01";
        }
    }

    public String viewNotif(){
        logger.info("[NotifAction.viewAndReadNotif] start process >>>");

        List<Notif> listOfResult = new ArrayList<Notif>();
        String id = getId();

        Notif notif = new Notif();
        notif.setNotifId(id);
        notif.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifBo notifBo = (NotifBo) ctx.getBean("notifBoProxy");

        try {
            listOfResult = notifBo.getByCriteria(notif);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = notifBo.saveErrorMessage(e.getMessage(), "GroupShift.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[NotifAction.search] Error when saving error,", e1);
//                return ERROR;
            }
            logger.error("[NotifAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
//            return ERROR;
        }

        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        if(listOfResult != null){
            for (Notif notifData : listOfResult){
                if (id.equalsIgnoreCase(notifData.getNotifId())){
                    setNotif(notifData);
                    try {
                        Notif notifRead = new Notif();
                        notifRead.setNotifId(notifData.getNotifId());
                        notifRead.setNotifName(notifData.getNotifName());
                        notifRead.setTypeNotif(notifData.getTypeNotif());
                        notifRead.setUrl(notifData.getUrl());
                        notifRead.setCreatedDate(notifData.getCreatedDate());
                        notifRead.setCreatedWho(notifData.getCreatedWho());
                        notifRead.setLastUpdate(updateTime);
                        notifRead.setLastUpdateWho(userLogin);
                        notifRead.setFlag("N");
                        notifRead.setAction("U");
                        notifBo.getReadNotif(notifRead);
                    } catch (GeneralBOException e){
                        Long logId = null;
                        try {
                            logId = notifBo.saveErrorMessage(e.getMessage(), "NotifAction.getByCriteria");
                        } catch (GeneralBOException e1) {
                            logger.error("[NotifAction.search] Error when saving error,", e1);
                        }
                        logger.error("[NotifAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                        addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
                    }
                }
            }
        }else{
            setNotif(new Notif());
        }

        logger.info("[NotifAction.viewAndReadNotif] end process >>>");
        return "view_notif";
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
