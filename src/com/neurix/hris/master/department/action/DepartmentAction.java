package com.neurix.hris.master.department.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.department.bo.DepartmentBo;
import com.neurix.hris.master.department.model.Department;
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

public class DepartmentAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(DepartmentAction.class);
    private DepartmentBo departmentBoProxy;
    private Department department;

    private List<Department> listComboDepartment = new ArrayList<Department>();

    public List<Department> getListComboDepartment() {
        return listComboDepartment;
    }

    public void setListComboDepartment(List<Department> listComboDepartment) {
        this.listComboDepartment = listComboDepartment;
    }

    public DepartmentBo getDepartmentBoProxy() {
        return departmentBoProxy;
    }

    public void setDepartmentBoProxy(DepartmentBo departmentBoProxy) {
        this.departmentBoProxy = departmentBoProxy;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    private List<Department> initComboDepartment;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        DepartmentAction.logger = logger;
    }


    public List<Department> getInitComboDepartment() {
        return initComboDepartment;
    }

    public void setInitComboDepartment(List<Department> initComboDepartment) {
        this.initComboDepartment = initComboDepartment;
    }

    public Department init(String kode, String flag){
        logger.info("[DepartmentAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Department> listOfResult = (List<Department>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Department department: listOfResult) {
                    if(kode.equalsIgnoreCase(department.getDepartmentId()) && flag.equalsIgnoreCase(department.getFlag())){
                        setDepartment(department);
                        break;
                    }
                }
            } else {
                setDepartment(new Department());
            }

            logger.info("[DepartmentAction.init] end process >>>");
        }
        return getDepartment();
    }

    @Override
    public String add() {
        logger.info("[DepartmentAction.add] start process >>>");
        Department addDepartment = new Department();
        setDepartment(addDepartment);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[DepartmentAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[DepartmentAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Department editDepartment = new Department();

        if(itemFlag != null){
            try {
                editDepartment = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = departmentBoProxy.saveErrorMessage(e.getMessage(), "DepartmentBO.getDepartmentByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[DepartmentAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[DepartmentAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editDepartment != null) {
                setDepartment(editDepartment);
            } else {
                editDepartment.setFlag(itemFlag);
                editDepartment.setDepartmentId(itemId);
                setDepartment(editDepartment);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editDepartment.setDepartmentId(itemId);
            editDepartment.setFlag(getFlag());
            setDepartment(editDepartment);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[DepartmentAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[DepartmentAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Department deleteDepartment = new Department();

        if (itemFlag != null ) {

            try {
                deleteDepartment = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = departmentBoProxy.saveErrorMessage(e.getMessage(), "DepartmentBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[DepartmentAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[DepartmentAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteDepartment != null) {
                setDepartment(deleteDepartment);

            } else {
                deleteDepartment.setDepartmentId(itemId);
                deleteDepartment.setFlag(itemFlag);
                setDepartment(deleteDepartment);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteDepartment.setDepartmentId(itemId);
            deleteDepartment.setFlag(itemFlag);
            setDepartment(deleteDepartment);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[DepartmentAction.delete] end process <<<");

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
        logger.info("[DepartmentAction.saveEdit] start process >>>");
        try {

            Department editDepartment = getDepartment();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editDepartment.setLastUpdateWho(userLogin);
            editDepartment.setLastUpdate(updateTime);
            editDepartment.setAction("U");
            editDepartment.setFlag("Y");

            departmentBoProxy.saveEdit(editDepartment);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = departmentBoProxy.saveErrorMessage(e.getMessage(), "DepartmentBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[DepartmentAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[DepartmentAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[DepartmentAction.saveEdit] end process <<<");
        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[DepartmentAction.saveDelete] start process >>>");
        try {

            Department deleteDepartment = getDepartment();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteDepartment.setLastUpdate(updateTime);
            deleteDepartment.setLastUpdateWho(userLogin);
            deleteDepartment.setAction("U");
            deleteDepartment.setFlag("N");

            departmentBoProxy.saveDelete(deleteDepartment);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = departmentBoProxy.saveErrorMessage(e.getMessage(), "DepartmentBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[DepartmentAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[DepartmentAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[DepartmentAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[DepartmentAction.saveAdd] start process >>>");

        try {
            Department department = getDepartment();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());


            department.setCreatedWho(userLogin);
            department.setLastUpdate(updateTime);
            department.setCreatedDate(updateTime);
            department.setLastUpdateWho(userLogin);
            department.setAction("C");
            department.setFlag("Y");

            departmentBoProxy.saveAdd(department);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = departmentBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
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
        logger.info("[DepartmentAction.search] start process >>>");

        Department searchDepartment = getDepartment();
        List<Department> listOfsearchDepartment = new ArrayList();

        try {
            listOfsearchDepartment = departmentBoProxy.getByCriteria(searchDepartment);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = departmentBoProxy.saveErrorMessage(e.getMessage(), "DepartmentBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[DepartmentAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[DepartmentAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchDepartment);

        logger.info("[DepartmentAction.search] end process <<<");

        return SUCCESS;
    }

    public String searchDepartment() {
        logger.info("[DepartmentAction.search] start process >>>");

        Department searchDepartment = new Department();
        searchDepartment.setFlag("Y");
        List<Department> listOfsearchDepartment = new ArrayList();

        try {
            listOfsearchDepartment = departmentBoProxy.getByCriteria(searchDepartment);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = departmentBoProxy.saveErrorMessage(e.getMessage(), "DepartmentBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[DepartmentAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[DepartmentAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }
        listComboDepartment.addAll(listOfsearchDepartment);
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[DepartmentAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[DepartmentAction.initForm] end process >>>");
        return INPUT;
    }

    public String initDepartment() {
        logger.info("[DepartmentAction.search] start process >>>");

        Department searchDepartment = new Department();
        searchDepartment.setFlag("Y");
        List<Department> listOfsearchDepartment = new ArrayList();

        try {
            listOfsearchDepartment = departmentBoProxy.getByCriteria(searchDepartment);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = departmentBoProxy.saveErrorMessage(e.getMessage(), "DepartmentBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[DepartmentAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[DepartmentAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultDepartment");
        session.setAttribute("listOfResultDepartment", listOfsearchDepartment);

        logger.info("[DepartmentAction.search] end process <<<");

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
