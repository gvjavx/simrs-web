package com.neurix.hris.master.smkKategoriAspek.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.smkIndikatorKinerja.model.SmkIndikatorKinerja;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.bo.SmkIndikatorPenilaianAspekBo;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.dao.SmkIndikatorPenilaianAspekDao;
import com.neurix.hris.master.smkIndikatorPenilaianAspek.model.SmkIndikatorPenilaianAspek;
import com.neurix.hris.master.smkKategoriAspek.bo.SmkKategoriAspekBo;
import com.neurix.hris.master.smkKategoriAspek.model.SmkKategoriAspek;
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

public class SmkKategoriAspekAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(SmkKategoriAspekAction.class);
    private SmkKategoriAspekBo smkKategoriAspekBoProxy;
    private SmkIndikatorPenilaianAspekBo smkIndikatorPenilaianAspekBoProxy;
    private SmkKategoriAspek smkKategoriAspek;
    private SmkIndikatorPenilaianAspek smkIndikatorPenilaianAspek;

    public SmkIndikatorPenilaianAspekBo getSmkIndikatorPenilaianAspekBoProxy() {
        return smkIndikatorPenilaianAspekBoProxy;
    }

    public void setSmkIndikatorPenilaianAspekBoProxy(SmkIndikatorPenilaianAspekBo smkIndikatorPenilaianAspekBoProxy) {
        this.smkIndikatorPenilaianAspekBoProxy = smkIndikatorPenilaianAspekBoProxy;
    }

    public SmkIndikatorPenilaianAspek getSmkIndikatorPenilaianAspek() {
        return smkIndikatorPenilaianAspek;
    }

    public void setSmkIndikatorPenilaianAspek(SmkIndikatorPenilaianAspek smkIndikatorPenilaianAspek) {
        this.smkIndikatorPenilaianAspek = smkIndikatorPenilaianAspek;
    }

    public SmkKategoriAspekBo getSmkKategoriAspekBoProxy() {
        return smkKategoriAspekBoProxy;
    }

    public void setSmkKategoriAspekBoProxy(SmkKategoriAspekBo smkKategoriAspekBoProxy) {
        this.smkKategoriAspekBoProxy = smkKategoriAspekBoProxy;
    }

    public SmkKategoriAspek getSmkKategoriAspek() {
        return smkKategoriAspek;
    }

    public void setSmkKategoriAspek(SmkKategoriAspek smkKategoriAspek) {
        this.smkKategoriAspek = smkKategoriAspek;
    }

    private List<SmkKategoriAspek> listComboSmkKategoriAspek = new ArrayList<SmkKategoriAspek>();

    public List<SmkKategoriAspek> getListComboSmkKategoriAspek() {
        return listComboSmkKategoriAspek;
    }

    public void setListComboSmkKategoriAspek(List<SmkKategoriAspek> listComboSmkKategoriAspek) {
        this.listComboSmkKategoriAspek = listComboSmkKategoriAspek;
    }

    public SmkKategoriAspekBo getKategoriAspekIdBoProxy() {
        return smkKategoriAspekBoProxy;
    }

    public void setKategoriAspekIdBoProxy(SmkKategoriAspekBo smkKategoriAspekBoProxy) {
        this.smkKategoriAspekBoProxy = smkKategoriAspekBoProxy;
    }

    public SmkKategoriAspek getKategoriAspekId() {
        return smkKategoriAspek;
    }

    public void setKategoriAspekId(SmkKategoriAspek smkKategoriAspek) {
        this.smkKategoriAspek = smkKategoriAspek;
    }

    private List<SmkKategoriAspek> initComboSmkKategoriAspekB;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        SmkKategoriAspekAction.logger = logger;
    }


    public List<SmkKategoriAspek> getInitComboSmkKategoriAspekB() {
        return initComboSmkKategoriAspekB;
    }

    public void setInitComboSmkKategoriAspekB(List<SmkKategoriAspek> initComboSmkKategoriAspekB) {
        this.initComboSmkKategoriAspekB = initComboSmkKategoriAspekB;
    }

    public SmkKategoriAspek init(String kode, String flag){
        logger.info("[SmkKategoriAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkKategoriAspek> listOfResult = (List<SmkKategoriAspek>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (SmkKategoriAspek smkKategoriAspek : listOfResult) {
                    if(kode.equalsIgnoreCase(smkKategoriAspek.getKategoriAspekId()) && flag.equalsIgnoreCase(smkKategoriAspek.getFlag())){
                        setKategoriAspekId(smkKategoriAspek);
                        break;
                    }
                }
            } else {
                setKategoriAspekId(new SmkKategoriAspek());
            }

            logger.info("[SmkKategoriAction.init] end process >>>");
        }
        return getKategoriAspekId();
    }

    public SmkIndikatorPenilaianAspek initIndikator(String id){
        logger.info("[SmkKategoriAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkIndikatorPenilaianAspek> listOfResult = (List<SmkIndikatorPenilaianAspek>) session.getAttribute("kategoriAspekIndikator");

        if(id != null && !"".equalsIgnoreCase(id)){
            if(listOfResult != null){
                for (SmkIndikatorPenilaianAspek smkIndikatorPenilaianAspek1 : listOfResult) {
                    if(id.equalsIgnoreCase(smkIndikatorPenilaianAspek1.getIndikatorPenilaianAspekId())){
                        setSmkIndikatorPenilaianAspek(smkIndikatorPenilaianAspek1);
                        break;
                    }
                }
            } else {
                setSmkIndikatorPenilaianAspek(new SmkIndikatorPenilaianAspek());
            }

            logger.info("[SmkKategoriAction.init] end process >>>");
        }
        return getSmkIndikatorPenilaianAspek();
    }

    @Override
    public String add() {
        logger.info("[SmkKategoriAction.add] start process >>>");
        SmkKategoriAspek addSmkKategoriAspekB = new SmkKategoriAspek();
        setKategoriAspekId(addSmkKategoriAspekB);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkIndikatorPenilaianAspek> smkKategoriAspekList = (List<SmkIndikatorPenilaianAspek>) session.getAttribute("kategoriAspekIndikator");
        if(smkKategoriAspekList != null){
            session.removeAttribute("kategoriAspekIndikator");
            session.setAttribute("kategoriAspekIndikator", smkKategoriAspekList);
        }else{
            session.removeAttribute("kategoriAspekIndikator");
        }


        logger.info("[SmkKategoriAction.add] stop process >>>");
        return "init_add";
    }

    public String modalAdd() {
        SmkKategoriAspek addSmkKategoriAspek = new SmkKategoriAspek();
        setKategoriAspekId(addSmkKategoriAspek);
        setAddOrEdit(true);
        setAdd(true);
        return "init_addIndikator";
    }

    public String addIndikator() {
        logger.info("[SmkKategoriAspekAction.saveAdd] start process >>>");
        List<SmkIndikatorPenilaianAspek> smkIndikatorPenilaianAspekList = null;
        HttpSession session = ServletActionContext.getRequest().getSession();
        String idIndikator ;
        try {
            SmkIndikatorPenilaianAspek smkIndikatorPenilaianAspek = getSmkIndikatorPenilaianAspek();
            idIndikator = smkKategoriAspekBoProxy.getIdIndikator();
            //HttpSession session = ServletActionContext.getRequest().getSession();
            smkIndikatorPenilaianAspek.setIndikatorPenilaianAspekId(idIndikator);
            smkIndikatorPenilaianAspekList = (List<SmkIndikatorPenilaianAspek>) session.getAttribute("kategoriAspekIndikator");
            if(smkIndikatorPenilaianAspekList != null){
                smkIndikatorPenilaianAspekList.add(smkIndikatorPenilaianAspek);
            }else{
                smkIndikatorPenilaianAspekList = new ArrayList();
                smkIndikatorPenilaianAspekList.add(smkIndikatorPenilaianAspek);
            }


        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkKategoriAspekBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        setAddOrEdit(true);
        setAdd(true);
        session.setAttribute("kategoriAspekIndikator", smkIndikatorPenilaianAspekList);
        return "init_addIndikator";
    }

    public String deleteIndikator() {
        logger.info("[SmkKategoriAction.delete] start process >>>");

        String itemId = getId();
        SmkIndikatorPenilaianAspek smkIndikatorPenilaianAspek = new SmkIndikatorPenilaianAspek();

        try {
            smkIndikatorPenilaianAspek = initIndikator(itemId);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkKategoriAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkKategoriBO.getAlatById");
            } catch (GeneralBOException e1) {
                logger.error("[SmkKategoriAction.delete] Error when retrieving delete data,", e1);
            }
            logger.error("[SmkKategoriAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
            return "failure";
        }

        if (smkIndikatorPenilaianAspek != null) {
            setSmkIndikatorPenilaianAspek(smkIndikatorPenilaianAspek);

        } else {
            smkIndikatorPenilaianAspek.setKategoriAspekId(itemId);
            setSmkIndikatorPenilaianAspek(smkIndikatorPenilaianAspek);
            addActionError("Error, Unable to find data with id = " + itemId);
            return "failure";
        }

        logger.info("[SmkKategoriAction.delete] end process <<<");

        return "deleteIndikator";
    }

    public String SavedeleteIndikator() {
        logger.info("[SpddAction.reroute] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<SmkIndikatorPenilaianAspek> smkIndikatorPenilaianAspekList = (List<SmkIndikatorPenilaianAspek>) session.getAttribute("kategoriAspekIndikator");
        List<SmkIndikatorPenilaianAspek> listHasil =  new ArrayList();
        SmkIndikatorPenilaianAspek smkIndikatorPenilaianAspek = getSmkIndikatorPenilaianAspek();

        if(smkIndikatorPenilaianAspek.getIndikatorPenilaianAspekId() != null && !"".equalsIgnoreCase(smkIndikatorPenilaianAspek.getIndikatorPenilaianAspekId())){
            if(smkIndikatorPenilaianAspekList != null){
                for (SmkIndikatorPenilaianAspek smkIndikatorPenilaianAspek1: smkIndikatorPenilaianAspekList) {
                    if(!smkIndikatorPenilaianAspek.getIndikatorPenilaianAspekId().equalsIgnoreCase(smkIndikatorPenilaianAspek1.getIndikatorPenilaianAspekId())){
                        listHasil.add(smkIndikatorPenilaianAspek1);
                    }else{
                    }
                }
            }
            logger.info("[SppdAction.init] end process >>>");
        }
        session.removeAttribute("kategoriAspekIndikator");
        session.setAttribute("kategoriAspekIndikator", listHasil);
        return "init_addIndikator";
    }


    @Override
    public String edit() {
        logger.info("[SmkKategoriAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        SmkKategoriAspek editSmkKategoriAspekB = new SmkKategoriAspek();
        List<SmkIndikatorPenilaianAspek> smkIndikatorPenilaianAspekList = new ArrayList();
        SmkIndikatorPenilaianAspek smkIndikatorPenilaianAspek = new SmkIndikatorPenilaianAspek();
        smkIndikatorPenilaianAspek.setFlag("Y");
        smkIndikatorPenilaianAspek.setKategoriAspekId(itemId);

        if(itemFlag != null){
            try {
                editSmkKategoriAspekB = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkKategoriAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkKategoriBO.getSmkKategoriByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkKategoriAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SmkKategoriAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editSmkKategoriAspekB != null) {
                setKategoriAspekId(editSmkKategoriAspekB);
                setAddOrEdit(true);
                smkIndikatorPenilaianAspekList = smkIndikatorPenilaianAspekBoProxy.getByCriteria(smkIndikatorPenilaianAspek);
                HttpSession session = ServletActionContext.getRequest().getSession();
                //session.removeAttribute("kategoriAspekIndikator");
                List<SmkIndikatorPenilaianAspek> smkKategoriAspekList = (List<SmkIndikatorPenilaianAspek>) session.getAttribute("kategoriAspekIndikator");
                if(smkKategoriAspekList != null){
                    session.removeAttribute("kategoriAspekIndikator");
                    session.setAttribute("kategoriAspekIndikator", smkKategoriAspekList);
                }else{
                    session.removeAttribute("kategoriAspekIndikator");
                    session.setAttribute("kategoriAspekIndikator", smkIndikatorPenilaianAspekList);
                }


            } else {
                editSmkKategoriAspekB.setFlag(itemFlag);
                editSmkKategoriAspekB.setKategoriAspekId(itemId);
                setKategoriAspekId(editSmkKategoriAspekB);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editSmkKategoriAspekB.setKategoriAspekId(itemId);
            editSmkKategoriAspekB.setFlag(getFlag());
            setKategoriAspekId(editSmkKategoriAspekB);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        logger.info("[SmkKategoriAction.edit] end process >>>");
        return "init_add";
    }

    public String cancelIndikator(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("kategoriAspekIndikator");
        return INPUT;
    }

    public String detail(){
        logger.info("[SmkKategoriAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        SmkKategoriAspek smkKategoriAspek = new SmkKategoriAspek();
        SmkIndikatorPenilaianAspek smkIndikatorPenilaianAspek = new SmkIndikatorPenilaianAspek();
        List<SmkIndikatorPenilaianAspek> smkIndikatorPenilaianAspekList = new ArrayList();

        smkIndikatorPenilaianAspek.setFlag("Y");
        smkIndikatorPenilaianAspek.setKategoriAspekId(itemId);

        if(itemFlag != null){
            try {
                smkKategoriAspek = init(itemId, itemFlag);
                smkIndikatorPenilaianAspekList = smkIndikatorPenilaianAspekBoProxy.getByCriteria(smkIndikatorPenilaianAspek);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkKategoriAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkKategoriBO.getSmkKategoriByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkKategoriAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SmkKategoriAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(smkKategoriAspek != null) {
                setKategoriAspekId(smkKategoriAspek);
            } else {
                smkKategoriAspek.setFlag(itemFlag);
                smkKategoriAspek.setKategoriAspekId(itemId);
                setKategoriAspekId(smkKategoriAspek);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            smkKategoriAspek.setKategoriAspekId(itemId);
            smkKategoriAspek.setFlag(getFlag());
            setKategoriAspekId(smkKategoriAspek);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("kategoriAspekIndikator");
        session.setAttribute("kategoriAspekIndikator", smkIndikatorPenilaianAspekList);

        setAddOrEdit(true);
        logger.info("[SmkKategoriAction.edit] end process >>>");

        return "init_detail";
    }

    @Override
    public String delete() {
        logger.info("[SmkKategoriAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        SmkKategoriAspek editSmkKategoriAspekB = new SmkKategoriAspek();
        List<SmkIndikatorPenilaianAspek> smkIndikatorPenilaianAspekList = new ArrayList();
        SmkIndikatorPenilaianAspek smkIndikatorPenilaianAspek = new SmkIndikatorPenilaianAspek();
        smkIndikatorPenilaianAspek.setFlag("Y");
        smkIndikatorPenilaianAspek.setKategoriAspekId(itemId);

        if(itemFlag != null){
            try {
                editSmkKategoriAspekB = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkKategoriAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkKategoriBO.getSmkKategoriByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkKategoriAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[SmkKategoriAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editSmkKategoriAspekB != null) {
                setKategoriAspekId(editSmkKategoriAspekB);
                setDelete(true);
                smkIndikatorPenilaianAspekList = smkIndikatorPenilaianAspekBoProxy.getByCriteria(smkIndikatorPenilaianAspek);
                HttpSession session = ServletActionContext.getRequest().getSession();
                //session.removeAttribute("kategoriAspekIndikator");
                session.setAttribute("kategoriAspekIndikator", smkIndikatorPenilaianAspekList);

            } else {
                editSmkKategoriAspekB.setFlag(itemFlag);
                editSmkKategoriAspekB.setKategoriAspekId(itemId);
                setKategoriAspekId(editSmkKategoriAspekB);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editSmkKategoriAspekB.setKategoriAspekId(itemId);
            editSmkKategoriAspekB.setFlag(getFlag());
            setKategoriAspekId(editSmkKategoriAspekB);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        logger.info("[SmkKategoriAction.edit] end process >>>");
        return "init_add";
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        if (isAddOrEdit()) {
            if (!isAdd()) {
                logger.info("[SmkKategoriAction.saveEdit] start process >>>");
                try {
                    SmkKategoriAspek editSmkKategoriAspekB = getKategoriAspekId();
                    SmkIndikatorPenilaianAspek smkIndikatorPenilaianAspek = new SmkIndikatorPenilaianAspek();

                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    editSmkKategoriAspekB.setLastUpdateWho(userLogin);
                    editSmkKategoriAspekB.setLastUpdate(updateTime);
                    editSmkKategoriAspekB.setAction("U");
                    editSmkKategoriAspekB.setFlag("Y");

                    smkIndikatorPenilaianAspek.setFlag("Y");
                    smkIndikatorPenilaianAspek.setKategoriAspekId(editSmkKategoriAspekB.getKategoriAspekId());

                    List<SmkIndikatorPenilaianAspek> smkIndikatorPenilaianAspekList = new ArrayList();
                    smkIndikatorPenilaianAspekList = smkIndikatorPenilaianAspekBoProxy.getByCriteria(smkIndikatorPenilaianAspek);

                    smkKategoriAspekBoProxy.saveEdit(editSmkKategoriAspekB, smkIndikatorPenilaianAspekList);
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = smkKategoriAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkKategoriBO.saveEdit");
                    } catch (GeneralBOException e1) {
                        logger.error("[SmkKategoriAction.saveEdit] Error when saving error,", e1);
                        return ERROR;
                    }
                    logger.error("[SmkKategoriAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                    return ERROR;
                }
                logger.info("[SmkKategoriAction.saveEdit] end process <<<");
            }else{
                logger.info("[saveAdd] start process >>>");
                try {
                    SmkKategoriAspek smkKategoriAspek = getSmkKategoriAspek();

                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    smkKategoriAspek.setCreatedWho(userLogin);
                    smkKategoriAspek.setLastUpdate(updateTime);
                    smkKategoriAspek.setCreatedDate(updateTime);
                    smkKategoriAspek.setLastUpdateWho(userLogin);
                    smkKategoriAspek.setAction("C");
                    smkKategoriAspek.setFlag("Y");
                    smkKategoriAspekBoProxy.saveAdd(smkKategoriAspek);
                }catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = smkKategoriAspekBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
                    } catch (GeneralBOException e1) {
                        logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                        return ERROR;
                    }
                    logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                    return ERROR;
                }
                logger.info("[SmkKategoriAspek.saveAdd] end process >>>");
            }
        }else if (isDelete()) {
            logger.info("[SmkKategoriAction.saveDelete] start process >>>");
            try {
                SmkKategoriAspek deleteSmkKategoriAspekB = getKategoriAspekId();
                String userLogin = CommonUtil.userLogin();
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                deleteSmkKategoriAspekB.setLastUpdate(updateTime);
                deleteSmkKategoriAspekB.setLastUpdateWho(userLogin);
                deleteSmkKategoriAspekB.setAction("U");
                deleteSmkKategoriAspekB.setFlag("N");

                smkKategoriAspekBoProxy.saveDelete(deleteSmkKategoriAspekB);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = smkKategoriAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkKategoriBO.saveDelete");
                } catch (GeneralBOException e1) {
                    logger.error("[SmkKategoriAction.saveDelete] Error when saving error,", e1);
                    return ERROR;
                }
                logger.error("[SmkKategoriAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                return ERROR;
            }
            logger.info("[SmkKategoriAction.saveDelete] end process <<<");
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("kategoriAspekIndikator");
        return "init_addIndikator";
    }

    public String saveEdit(){
        logger.info("[SmkKategoriAction.saveEdit] start process >>>");
        try {

            SmkKategoriAspek editSmkKategoriAspekB = getKategoriAspekId();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editSmkKategoriAspekB.setLastUpdateWho(userLogin);
            editSmkKategoriAspekB.setLastUpdate(updateTime);
            editSmkKategoriAspekB.setAction("U");
            editSmkKategoriAspekB.setFlag("Y");

            smkKategoriAspekBoProxy.saveEdit(editSmkKategoriAspekB);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkKategoriAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkKategoriBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[SmkKategoriAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkKategoriAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkKategoriAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[SmkKategoriAction.saveDelete] start process >>>");
        try {

            SmkKategoriAspek deleteSmkKategoriAspekB = getKategoriAspekId();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteSmkKategoriAspekB.setLastUpdate(updateTime);
            deleteSmkKategoriAspekB.setLastUpdateWho(userLogin);
            deleteSmkKategoriAspekB.setAction("U");
            deleteSmkKategoriAspekB.setFlag("N");

            smkKategoriAspekBoProxy.saveDelete(deleteSmkKategoriAspekB);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkKategoriAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkKategoriBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[SmkKategoriAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkKategoriAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[SmkKategoriAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[SmkKategoriAction.saveAdd] start process >>>");

        try {
            SmkKategoriAspek smkKategoriAspek = getKategoriAspekId();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            smkKategoriAspek.setCreatedWho(userLogin);
            smkKategoriAspek.setLastUpdate(updateTime);
            smkKategoriAspek.setCreatedDate(updateTime);
            smkKategoriAspek.setLastUpdateWho(userLogin);
            smkKategoriAspek.setAction("C");
            smkKategoriAspek.setFlag("Y");

            smkKategoriAspekBoProxy.saveAdd(smkKategoriAspek);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkKategoriAspekBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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

    public String initComboKategoriAspek() {
        logger.info("[BranchAction.search] start process >>>");

        SmkKategoriAspek kategoriAspek = new SmkKategoriAspek();
        List<SmkKategoriAspek> smkKategoriAspekList = new ArrayList();
        kategoriAspek.setFlag("Y");
        try {
            smkKategoriAspekList = smkKategoriAspekBoProxy.getByCriteria(kategoriAspek);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkKategoriAspekBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BranchAction.search] Error when saving error,", e1);
            }
            logger.error("[BranchAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listComboSmkKategoriAspek.addAll(smkKategoriAspekList);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String search() {
        logger.info("[SmkKategoriAction.search] start process >>>");

        SmkKategoriAspek searchSmkKategoriAspekB = getKategoriAspekId();
        List<SmkKategoriAspek> listOfsearchSmkKategoriAspekB = new ArrayList();

        try {
            listOfsearchSmkKategoriAspekB = smkKategoriAspekBoProxy.getByCriteria(searchSmkKategoriAspekB);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkKategoriAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkKategoriBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkKategoriAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkKategoriAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchSmkKategoriAspekB);

        logger.info("[SmkKategoriAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchSmkKategori() {
        logger.info("[SmkKategoriAction.search] start process >>>");

        SmkKategoriAspek searchSmkKategoriAspekB = new SmkKategoriAspek();
        searchSmkKategoriAspekB.setFlag("Y");
        List<SmkKategoriAspek> listOfsearchSmkKategoriAspekB = new ArrayList();

        try {
            listOfsearchSmkKategoriAspekB = smkKategoriAspekBoProxy.getByCriteria(searchSmkKategoriAspekB);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkKategoriAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkKategoriBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkKategoriAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkKategoriAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboSmkKategoriAspek.addAll(listOfsearchSmkKategoriAspekB);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[SmkKategoriAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[SmkKategoriAction.initForm] end process >>>");
        return INPUT;
    }

    public String initSmkKategori() {
        logger.info("[SmkKategoriAction.search] start process >>>");

        SmkKategoriAspek searchSmkKategoriAspekB = new SmkKategoriAspek();
        searchSmkKategoriAspekB.setFlag("Y");
        List<SmkKategoriAspek> listOfsearchSmkKategoriAspekB = new ArrayList();

        try {
            listOfsearchSmkKategoriAspekB = smkKategoriAspekBoProxy.getByCriteria(searchSmkKategoriAspekB);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkKategoriAspekBoProxy.saveErrorMessage(e.getMessage(), "SmkKategoriBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[SmkKategoriAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[SmkKategoriAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultSmkKategori");
        session.setAttribute("listOfResultSmkKategori", listOfsearchSmkKategoriAspekB);

        logger.info("[SmkKategoriAction.search] end process <<<");

        return "";
    }

    public String initComboKategori() {
        logger.info("[BranchAction.search] start process >>>");

        SmkKategoriAspek smkKategoriAspek = new SmkKategoriAspek();
        List<SmkKategoriAspek> smkKategoriAspekList = new ArrayList();
        smkKategoriAspek.setFlag("Y");
        try {
            smkKategoriAspekList = smkKategoriAspekBoProxy.getByCriteria(smkKategoriAspek);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = smkKategoriAspekBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[BranchAction.search] Error when saving error,", e1);
            }
            logger.error("[BranchAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listComboSmkKategoriAspek.addAll(smkKategoriAspekList);
        logger.info("[BranchAction.search] end process <<<");

        return SUCCESS;
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
