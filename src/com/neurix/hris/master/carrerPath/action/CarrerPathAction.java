package com.neurix.hris.master.carrerPath.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.carrerPath.bo.CarrerPathBo;
import com.neurix.hris.master.carrerPath.model.CarrerPath;
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

public class CarrerPathAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(CarrerPathAction.class);
    private CarrerPathBo carrerPathBoProxy;
    private CarrerPath carrerPath;

    private List<CarrerPath> comboListOfCarrerPath = new ArrayList<CarrerPath>();

    public List<CarrerPath> getComboListOfCarrerPath() {
        return comboListOfCarrerPath;
    }

    public void setComboListOfCarrerPath(List<CarrerPath> comboListOfCarrerPath) {
        this.comboListOfCarrerPath = comboListOfCarrerPath;
    }

    public CarrerPathBo getCarrerPathBoProxy() {
        return carrerPathBoProxy;
    }

    public void setCarrerPathBoProxy(CarrerPathBo carrerPathBoProxy) {
        this.carrerPathBoProxy = carrerPathBoProxy;
    }

    public CarrerPath getCarrerPath() {
        return carrerPath;
    }

    public void setCarrerPath(CarrerPath carrerPath) {
        this.carrerPath = carrerPath;
    }

    private List<CarrerPath> initComboCarrerPath;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        CarrerPathAction.logger = logger;
    }


    public List<CarrerPath> getInitComboCarrerPath() {
        return initComboCarrerPath;
    }

    public void setInitComboCarrerPath(List<CarrerPath> initComboCarrerPath) {
        this.initComboCarrerPath = initComboCarrerPath;
    }

    public CarrerPath init(String kode, String flag){
        logger.info("[CarrerPathAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<CarrerPath> listOfResult = (List<CarrerPath>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (CarrerPath carrerPath: listOfResult) {
                    if(kode.equalsIgnoreCase(carrerPath.getCarrerPathId()) && flag.equalsIgnoreCase(carrerPath.getFlag())){
                        carrerPath.setJurusanIdAdd(carrerPath.getJurusanId());
                        setCarrerPath(carrerPath);
                        break;
                    }
                }
            } else {
                setCarrerPath(new CarrerPath());
            }

            logger.info("[CarrerPathAction.init] end process >>>");
        }
        return getCarrerPath();
    }

    @Override
    public String add() {
        logger.info("[CarrerPathAction.add] start process >>>");
        CarrerPath addCarrerPath = new CarrerPath();
        setCarrerPath(addCarrerPath);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[CarrerPathAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[CarrerPathAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        CarrerPath editCarrerPath = new CarrerPath();

        if(itemFlag != null){
            try {
                editCarrerPath = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = carrerPathBoProxy.saveErrorMessage(e.getMessage(), "CarrerPathBO.getCarrerPathByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[CarrerPathAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[CarrerPathAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editCarrerPath != null) {
                setCarrerPath(editCarrerPath);
            } else {
                editCarrerPath.setFlag(itemFlag);
                editCarrerPath.setCarrerPathId(itemId);
                setCarrerPath(editCarrerPath);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editCarrerPath.setCarrerPathId(itemId);
            editCarrerPath.setFlag(getFlag());
            setCarrerPath(editCarrerPath);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[CarrerPathAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[CarrerPathAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        CarrerPath deleteCarrerPath = new CarrerPath();

        if (itemFlag != null ) {

            try {
                deleteCarrerPath = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = carrerPathBoProxy.saveErrorMessage(e.getMessage(), "CarrerPathBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[CarrerPathAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[CarrerPathAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteCarrerPath != null) {
                setCarrerPath(deleteCarrerPath);

            } else {
                deleteCarrerPath.setCarrerPathId(itemId);
                deleteCarrerPath.setFlag(itemFlag);
                setCarrerPath(deleteCarrerPath);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteCarrerPath.setCarrerPathId(itemId);
            deleteCarrerPath.setFlag(itemFlag);
            setCarrerPath(deleteCarrerPath);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[CarrerPathAction.delete] end process <<<");

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
        logger.info("[CarrerPathAction.saveEdit] start process >>>");
        try {

            CarrerPath editCarrerPath = getCarrerPath();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editCarrerPath.setLastUpdateWho(userLogin);
            editCarrerPath.setLastUpdate(updateTime);
            editCarrerPath.setAction("U");
            editCarrerPath.setFlag("Y");

            carrerPathBoProxy.saveEdit(editCarrerPath);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = carrerPathBoProxy.saveErrorMessage(e.getMessage(), "CarrerPathBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[CarrerPathAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CarrerPathAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[CarrerPathAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[CarrerPathAction.saveDelete] start process >>>");
        try {

            CarrerPath deleteCarrerPath = getCarrerPath();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteCarrerPath.setLastUpdate(updateTime);
            deleteCarrerPath.setLastUpdateWho(userLogin);
            deleteCarrerPath.setAction("D");
            deleteCarrerPath.setFlag("N");

            carrerPathBoProxy.saveDelete(deleteCarrerPath);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = carrerPathBoProxy.saveErrorMessage(e.getMessage(), "CarrerPathBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[CarrerPathAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CarrerPathAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[CarrerPathAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[CarrerPathAction.saveAdd] start process >>>");

        try {
            CarrerPath carrerPath = getCarrerPath();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            carrerPath.setCreatedWho(userLogin);
            carrerPath.setLastUpdate(updateTime);
            carrerPath.setCreatedDate(updateTime);
            carrerPath.setLastUpdateWho(userLogin);
            carrerPath.setAction("C");
            carrerPath.setFlag("Y");

            carrerPathBoProxy.saveAdd(carrerPath);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = carrerPathBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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

    @Override
    public String search() {
        logger.info("[CarrerPathAction.search] start process >>>");

        CarrerPath searchCarrerPath = getCarrerPath();
        List<CarrerPath> listOfsearchCarrerPath = new ArrayList();

        try {
            listOfsearchCarrerPath = carrerPathBoProxy.getByCriteria(searchCarrerPath);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = carrerPathBoProxy.saveErrorMessage(e.getMessage(), "CarrerPathBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[CarrerPathAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CarrerPathAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchCarrerPath);

        logger.info("[CarrerPathAction.search] end process <<<");

        return SUCCESS;
    }

    public CarrerPath cekSessionPendidikan(String id){
        CarrerPath carrerPath = new CarrerPath();
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<CarrerPath> listComboPendidikan = (List<CarrerPath>) session.getAttribute("listCarrerPendidikan");

        if(listComboPendidikan != null){
            for(CarrerPath carrerPathLoop: listComboPendidikan){
                if(id.equalsIgnoreCase(carrerPathLoop.getCarrerPathId())){
                    setCarrerPath(carrerPathLoop);
                    break;
                }
            }
        }
        return getCarrerPath();
    }

    public void saveAddPendidikan(String id, String pendidikanId, String pendidikanName, String jurusanId, String jurusanName){
        logger.info("[CarrerPathAction.saveAddPendidikan] start process >>>");
        List<CarrerPath> listComboPendidikan = null;
        HttpSession session = ServletActionContext.getRequest().getSession();

        try {
            CarrerPath carrerPath = new CarrerPath();

            carrerPath.setCarrerPathId(id);
            carrerPath.setPendidikan(pendidikanId);
            carrerPath.setPendidikanName(pendidikanName);
            carrerPath.setJurusanId(jurusanId);
            carrerPath.setJurusanName(jurusanName);

            listComboPendidikan = (List<CarrerPath>) session.getAttribute("listCarrerPendidikan");
            if(listComboPendidikan != null){
                listComboPendidikan.add(carrerPath);
            }else{
                listComboPendidikan= new ArrayList();
                listComboPendidikan.add(carrerPath);
            }

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = carrerPathBoProxy.saveErrorMessage(e.getMessage(), "carrerPathBO.saveAddPendidikan");
            } catch (GeneralBOException e1) {
                logger.error("[CarrerPathAction.saveAddPendidikan] Error when saving error,", e1);
            }
            logger.error("[CarrerPathAction.saveAddPendidikan] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        session.setAttribute("listCarrerPendidikan", listComboPendidikan);
    }

    public void saveEditPendidikan(String id, String pendidikanId, String pendidikanName, String jurusanId, String jurusanName){
        logger.info("[CarrerPathAction.saveAddPendidikan] start process >>>");
        List<CarrerPath> listComboPendidikan = null;
        List<CarrerPath> listHasil = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();

        try {
            CarrerPath carrerPath = new CarrerPath();
            listComboPendidikan = (List<CarrerPath>) session.getAttribute("listCarrerPendidikan");
            if(listComboPendidikan != null){
                for(CarrerPath carrerPathLoop: listComboPendidikan){
                    if(id.equalsIgnoreCase(carrerPathLoop.getCarrerPathId())){
                        carrerPath.setCarrerPathId(pendidikanId + "-" + jurusanId);
                        carrerPath.setPendidikan(pendidikanId);
                        carrerPath.setPendidikanName(pendidikanName);
                        carrerPath.setJurusanId(jurusanId);
                        carrerPath.setJurusanName(jurusanName);

                        listHasil.add(carrerPath);
                    }else{
                        listHasil.add(carrerPathLoop);
                    }
                }
            }

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = carrerPathBoProxy.saveErrorMessage(e.getMessage(), "carrerPathBO.saveAddPendidikan");
            } catch (GeneralBOException e1) {
                logger.error("[CarrerPathAction.saveAddPendidikan] Error when saving error,", e1);
            }
            logger.error("[CarrerPathAction.saveAddPendidikan] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        session.removeAttribute("listCarrerPendidikan");
        session.setAttribute("listCarrerPendidikan", listHasil);
    }

    public void saveDeletePendidikan(String id){
        logger.info("[CarrerPathAction.saveDeletePendidikan] start process >>>");
        List<CarrerPath> listComboPendidikan = null;
        List<CarrerPath> listHasil = new ArrayList<>();
        HttpSession session = ServletActionContext.getRequest().getSession();

        try {
            listComboPendidikan = (List<CarrerPath>) session.getAttribute("listCarrerPendidikan");
            if(listComboPendidikan != null){
                for(CarrerPath carrerPathLoop: listComboPendidikan){
                    if(id.equalsIgnoreCase(carrerPathLoop.getCarrerPathId())){

                    }else{
                        listHasil.add(carrerPathLoop);
                    }
                }
            }

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = carrerPathBoProxy.saveErrorMessage(e.getMessage(), "carrerPathBO.saveAddPendidikan");
            } catch (GeneralBOException e1) {
                logger.error("[CarrerPathAction.saveDeletePendidikan] Error when saving error,", e1);
            }
            logger.error("[CarrerPathAction.saveDeletePendidikan] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        session.removeAttribute("listCarrerPendidikan");
        session.setAttribute("listCarrerPendidikan", listHasil);
    }

    public List<CarrerPath> searchCarrerPendidikan() {
        logger.info("[CarrerPathAction.searchCarrerPendidikan] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<CarrerPath> listCarrerPendidikan = (List<CarrerPath>) session.getAttribute("listCarrerPendidikan");

        logger.info("[CarrerPathAction.searchCarrerPendidikan] end process >>>");
        return listCarrerPendidikan;
    }

    public String searchKelompok() {
        logger.info("[CarrerPathAction.search] start process >>>");

        CarrerPath searchCarrerPath = new CarrerPath();

        searchCarrerPath.setFlag("Y");
        List<CarrerPath> listOfsearchCarrerPath = new ArrayList();

        try {
            listOfsearchCarrerPath = carrerPathBoProxy.getByCriteria(searchCarrerPath);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = carrerPathBoProxy.saveErrorMessage(e.getMessage(), "CarrerPathBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[CarrerPathAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CarrerPathAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        comboListOfCarrerPath.addAll(listOfsearchCarrerPath);

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[CarrerPathAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[CarrerPathAction.initForm] end process >>>");
        return INPUT;
    }

    public String initCarrerPath() {
        logger.info("[CarrerPathAction.search] start process >>>");

        CarrerPath searchCarrerPath = new CarrerPath();
        searchCarrerPath.setFlag("Y");
        List<CarrerPath> listOfsearchCarrerPath = new ArrayList();

        try {
            listOfsearchCarrerPath = carrerPathBoProxy.getByCriteria(searchCarrerPath);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = carrerPathBoProxy.saveErrorMessage(e.getMessage(), "CarrerPathBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[CarrerPathAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[CarrerPathAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultCarrerPath");
        session.setAttribute("listOfResultCarrerPath", listOfsearchCarrerPath);

        logger.info("[CarrerPathAction.search] end process <<<");

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
