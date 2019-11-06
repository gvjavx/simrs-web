package com.neurix.authorization.function.action;

import com.neurix.authorization.function.bo.FunctionBo;
import com.neurix.authorization.function.model.Functions;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.download.excel.CellDetail;
import com.neurix.common.download.excel.DownloadUtil;
import com.neurix.common.download.excel.RowData;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 16/02/13
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */
public class FunctionAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(FunctionAction.class);

    private FunctionBo functionBoProxy;
    private List<Functions> functionList;
    private List<Functions> listOfComboParent = new ArrayList();
    private Functions functions;

    public List<Functions> getListOfComboParent() {
        return listOfComboParent;
    }

    public void setListOfComboParent(List<Functions> listOfComboParent) {
        this.listOfComboParent = listOfComboParent;
    }

    public List<Functions> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(List<Functions> functionList) {
        this.functionList = functionList;
    }

    public void setFunctionBoProxy(FunctionBo functionBoProxy) {
        this.functionBoProxy = functionBoProxy;
    }

    public Functions getFunctions() {
        return functions;
    }

    public void setFunctions(Functions functions) {
        this.functions = functions;
    }

    @Override
    public void validate() {

//        if (getFunctions() == null) {
//            addActionError("Function is required");
//        }
//        if (getFunctions() != null && isAddOrEdit()) {
//            if (getFunctions().getFuncName().length() == 0) {
//                addActionError("Function Name is required");
//            }
//
//        } else if (getFunctions() != null && !(isAddOrEdit() || isDelete())) {
//            try {
//                if (getFunctions().getStFuncId().length() > 0) {
//                    Long.parseLong(getFunctions().getStFuncId());
//                }
//            } catch (NumberFormatException e) {
//                addActionError("Function Id field must number");
//            }
//
//            try {
//                if (getFunctions().getStParent().length() > 0) {
//                    Long.parseLong(getFunctions().getStParent());
//                }
//            } catch (NumberFormatException e) {
//                addActionError("Parent field must number");
//            }
//
//            try {
//                if (getFunctions().getStMenu().length() > 0) {
//                    Long.parseLong(getFunctions().getStMenu());
//                }
//            } catch (NumberFormatException e) {
//                addActionError("Menu field must number");
//            }
//
//            try {
//                if (getFunctions().getStFuncLevel().length() > 0) {
//                    Long.parseLong(getFunctions().getStFuncLevel());
//                }
//            } catch (NumberFormatException e) {
//                addActionError("Func.Level field must number");
//            }
//        }
    }


    private Functions init(String id, String flag) throws NumberFormatException, GeneralBOException {

        logger.info("[FunctionAction.init] start process >>>");

        Functions initFunctions = new Functions();
        if (id != null && !"".equalsIgnoreCase(id)) {
            long lId = Long.parseLong(id);
            initFunctions = functionBoProxy.getFunctionById(lId, flag);
            if (initFunctions != null) {
                if (initFunctions.getFuncId() != null) initFunctions.setStFuncId(initFunctions.getFuncId().toString());
                if (initFunctions.getMenu() != null) initFunctions.setStMenu(initFunctions.getMenu().toString());
                if (initFunctions.getFuncLevel() != null)
                    initFunctions.setStFuncLevel(initFunctions.getFuncLevel().toString());
                if (initFunctions.getParent() != null) initFunctions.setStParent(initFunctions.getParent().toString());
                if (initFunctions.getFlag() != null) initFunctions.setStFlag(initFunctions.getFlag().toString());
            }
        }

        logger.info("[FunctionAction.init] end process <<<");

        return initFunctions;
    }

    @Override
    public String edit() {

        logger.info("[FunctionAction.edit] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Functions editFunctions = new Functions();

        if (itemFlag != null && ("Y".equalsIgnoreCase(itemFlag) || "".equalsIgnoreCase(itemFlag))) {

            try {
                editFunctions = init(itemId, itemFlag);
            } catch (NumberFormatException e) {
                logger.error("[FunctionAction.edit] error parsing long.", e);
                addFieldError("functions.funcId", "Fuction Id must number.");
                return "failure";
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = functionBoProxy.saveErrorMessage(e.getMessage(), "FunctionBO.getFunctionById");
                } catch (GeneralBOException e1) {
                    logger.error("[FunctionAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[FunctionAction.edit] Error when retrieving item function," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if (editFunctions != null) {
                if (editFunctions.getFlag().equalsIgnoreCase("Y")) {
                    if (editFunctions.getMenu() != null && editFunctions.getMenu() > 0) {
                        editFunctions.setStatusMenuFunc(true);
                    }
                    setAddOrEdit(true);
                    setFunctions(editFunctions);

                    HttpSession session = ServletActionContext.getRequest().getSession();
                    session.removeAttribute("listOfResult");

                } else {
                    editFunctions.setStFuncId(itemId);
                    if (itemFlag.equalsIgnoreCase("Y"))
                        editFunctions.setStFlag(itemFlag);
                    else
                        editFunctions.setStFlag("N");

                    editFunctions.setFlag(itemFlag);
                    setFunctions(editFunctions);
                    addActionError("Error, Unable to edit again with flag = N.");
                    return "failure";
                }
            } else {
                editFunctions.setStFuncId(itemId);
                if (itemFlag.equalsIgnoreCase("Y"))
                    editFunctions.setStFlag(itemFlag);
                else
                    editFunctions.setStFlag("N");

                editFunctions.setFlag(itemFlag);
                setFunctions(editFunctions);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editFunctions.setStFuncId(itemId);
            if (itemFlag.equalsIgnoreCase("Y"))
                editFunctions.setStFlag(itemFlag);
            else
                editFunctions.setStFlag("N");

            editFunctions.setFlag(itemFlag);
            setFunctions(editFunctions);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        logger.info("[FunctionAction.edit] end process <<<");

        return INPUT;
    }

    @Override
    public String add() {
        logger.info("[FunctionAction.add] start process >>>");
        Functions addFunctions = new Functions();
        setFunctions(addFunctions);
        setAdd(true);
        setAddOrEdit(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[FunctionAction.add] end process <<<");
        return INPUT;
    }

    @Override
    public String delete() {
        logger.info("[FunctionAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Functions deleteFunctions = new Functions();

        if (itemFlag != null && ("Y".equalsIgnoreCase(itemFlag) || "".equalsIgnoreCase(itemFlag))) {

            try {
                deleteFunctions = init(itemId, itemFlag);
            } catch (NumberFormatException e) {
                logger.error("[FunctionAction.delete] error parsing long.", e);
                addFieldError("functions.funcId", "Fuction Id must number.");
                return "failure";
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = functionBoProxy.saveErrorMessage(e.getMessage(), "FunctionBO.getFunctionById");
                } catch (GeneralBOException e1) {
                    logger.error("[FunctionAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[FunctionAction.delete] Error when retrieving item function," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteFunctions != null) {
                if (deleteFunctions.getFlag().equalsIgnoreCase("Y")) {
                    setDelete(true);
                    setFunctions(deleteFunctions);

                    HttpSession session = ServletActionContext.getRequest().getSession();
                    session.removeAttribute("listOfResult");

                } else {
                    deleteFunctions.setStFuncId(itemId);
                    deleteFunctions.setFlag(itemFlag);
                    setFunctions(deleteFunctions);
                    addActionError("Error, Unable to delete again with flag = N.");
                    return "failure";
                }
            } else {
                deleteFunctions.setStFuncId(itemId);
                deleteFunctions.setFlag(itemFlag);
                setFunctions(deleteFunctions);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteFunctions.setStFuncId(itemId);
            deleteFunctions.setFlag(itemFlag);
            setFunctions(deleteFunctions);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[FunctionAction.delete] end process <<<");

        return INPUT;
    }

    @Override
    public String view() {
        logger.info("[FunctionAction.view] start process >>>");

        //get data from session
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Functions> listOfResult = (List) session.getAttribute("listOfResult");

        if (id != null && !"".equalsIgnoreCase(id)) {
            long lId = Long.parseLong(id);

            if (listOfResult != null) {
                for (Functions functions : listOfResult) {
                    if (functions.getFuncId() == lId && functions.getFlag().equalsIgnoreCase(flag)) {

                        if (functions.getFuncId() != null) functions.setStFuncId(functions.getFuncId().toString());
                        if (functions.getMenu() != null) functions.setStMenu(functions.getMenu().toString());
                        if (functions.getFuncLevel() != null)
                            functions.setStFuncLevel(functions.getFuncLevel().toString());
                        if (functions.getParent() != null) functions.setStParent(functions.getParent().toString());
                        if (functions.getFlag() != null) {
                            if ("Y".equalsIgnoreCase(functions.getFlag())) {
                                functions.setStFlag("Y");
                            } else {
                                functions.setStFlag("N");
                            }
                        }
                        setFunctions(functions);
                    }
                }
            } else {
                setFunctions(new Functions());
            }
        } else {
            setFunctions(new Functions());
        }

        logger.info("[FunctionAction.view] end process <<<");

        return "view_detail";
    }

    @Override
    public String save() {
        logger.info("[FunctionAction.save] start process >>>");

        if (isAddOrEdit()) {

            if (!isAdd()) {

                String itemId = getFunctions().getStFuncId();

                //edit
                try {

                    String userLogin = CommonUtil.userLogin();
                    Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    Functions editFunctions = getFunctions();
                    Long lItemId = Long.parseLong(itemId);
                    editFunctions.setFuncId(lItemId);
                    if (editFunctions.getStMenu() != null && !"".equalsIgnoreCase(editFunctions.getStMenu()))
                        editFunctions.setMenu(Long.parseLong(editFunctions.getStMenu()));

                    if (editFunctions.getStParent() != null && !"".equalsIgnoreCase(editFunctions.getStParent()))
                        editFunctions.setParent(Long.parseLong(editFunctions.getStParent()));

                    if (editFunctions.getStFuncLevel() != null && !"".equalsIgnoreCase(editFunctions.getStFuncLevel()))
                        editFunctions.setFuncLevel(Long.parseLong(editFunctions.getStFuncLevel()));

                    editFunctions.setCreatedDate(updateTime);
                    editFunctions.setCreatedWho(userLogin);
                    editFunctions.setLastUpdate(updateTime);
                    editFunctions.setLastUpdateWho(userLogin);
                    editFunctions.setAction("U");

                    functionBoProxy.saveEdit(editFunctions);

                } catch (NumberFormatException e) {
                    logger.error("[FunctionAction.save] Error when editing item function,", e);
                    addActionError("Error, " + "Fuction Id must number.");
                    return ERROR;
                } catch (UsernameNotFoundException e) {
                    logger.error("[FunctionAction.save] Error when editing item function,", e);
                    addActionError("Error, " + e.getMessage());
                    return ERROR;
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = functionBoProxy.saveErrorMessage(e.getMessage(), "FunctionBO.saveEditFunctions");
                    } catch (GeneralBOException e1) {
                        logger.error("[FunctionAction.save] Error when saving error,", e1);
                    }
                    logger.error("[FunctionAction.save] Error when editing item function," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
                    return ERROR;
                }

            } else {
                //add
                try {
                    String userLogin = CommonUtil.userLogin();
                    Timestamp createTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                    Functions entryFunctions = getFunctions();

                    if (entryFunctions.getStFuncId() != null && !"".equalsIgnoreCase(entryFunctions.getStFuncId()))
                        entryFunctions.setFuncId(Long.parseLong(entryFunctions.getStFuncId()));

                    if (entryFunctions.getStMenu() != null && !"".equalsIgnoreCase(entryFunctions.getStMenu()))
                        entryFunctions.setMenu(Long.parseLong(entryFunctions.getStMenu()));

                    if (entryFunctions.getStParent() != null && !"".equalsIgnoreCase(entryFunctions.getStParent()))
                        entryFunctions.setParent(Long.parseLong(entryFunctions.getStParent()));

                    if (entryFunctions.getStFuncLevel() != null && !"".equalsIgnoreCase(entryFunctions.getStFuncLevel()))
                        entryFunctions.setFuncLevel(Long.parseLong(entryFunctions.getStFuncLevel()));

                    entryFunctions.setCreatedDate(createTime);
                    entryFunctions.setCreatedWho(userLogin);
                    entryFunctions.setLastUpdate(createTime);
                    entryFunctions.setLastUpdateWho(userLogin);
                    entryFunctions.setAction("C");

                    Functions savedFunction = functionBoProxy.saveAdd(entryFunctions);
                    setFunctions(savedFunction);
//                    addActionMessage("Save Add is success.");
                } catch (UsernameNotFoundException e) {
                    logger.error("[FunctionAction.save] Error when adding item function,", e);
                    addActionError("Error, " + e.getMessage());
                    return ERROR;
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = functionBoProxy.saveErrorMessage(e.getMessage(), "FunctionBO.saveAddFunctions");
                    } catch (GeneralBOException e1) {
                        logger.error("[FunctionAction.save] Error when saving error,", e1);
                    }
                    logger.error("[FunctionAction.save] Error when adding item function," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
                    return ERROR;
                }

            }

        } else if (isDelete()) {

            try {
                String userLogin = CommonUtil.userLogin();
                Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

                Functions deleteFunctions = getFunctions();

                if (deleteFunctions.getStFuncId() != null && !"".equalsIgnoreCase(deleteFunctions.getStFuncId()))
                    deleteFunctions.setFuncId(Long.parseLong(deleteFunctions.getStFuncId()));

                if (deleteFunctions.getStMenu() != null && !"".equalsIgnoreCase(deleteFunctions.getStMenu()))
                    deleteFunctions.setMenu(Long.parseLong(deleteFunctions.getStMenu()));

                if (deleteFunctions.getStParent() != null && !"".equalsIgnoreCase(deleteFunctions.getStParent()))
                    deleteFunctions.setParent(Long.parseLong(deleteFunctions.getStParent()));

                if (deleteFunctions.getStFuncLevel() != null && !"".equalsIgnoreCase(deleteFunctions.getStFuncLevel()))
                    deleteFunctions.setFuncLevel(Long.parseLong(deleteFunctions.getStFuncLevel()));

                deleteFunctions.setCreatedDate(updateTime);
                deleteFunctions.setCreatedWho(userLogin);
                deleteFunctions.setLastUpdate(updateTime);
                deleteFunctions.setLastUpdateWho(userLogin);
                deleteFunctions.setAction("D");

                functionBoProxy.saveDelete(deleteFunctions);

            } catch (UsernameNotFoundException e) {
                logger.error("[FunctionAction.save] Error when deleting item function,", e);
                addActionError("Error, " + e.getMessage());
                return ERROR;
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = functionBoProxy.saveErrorMessage(e.getMessage(), "FunctionBO.saveDeleteFunctions");
                } catch (GeneralBOException e1) {
                    logger.error("[FunctionAction.save] Error when saving error,", e1);
                }
                logger.error("[FunctionAction.save] Error when deleting item function," + "[" + logId + "] Found problem when saving delete data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when saving delete data, please inform to your admin.\n" + e.getMessage());
                return ERROR;
            }

        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[FunctionAction.save] end process <<<");

        return "success_save";
    }

    @Override
    public String initForm() {

        clearMessages();
        clearActionErrors();
        Functions initFunctions = new Functions();
        setFunctions(initFunctions);
        setDelete(false);
        setAdd(false);
        setAddOrEdit(false);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        return INPUT;

    }

    @Override
    public String search() {
        logger.info("[FunctionAction.search] start process >>>");

        Functions searchFunctions = getFunctions();
        List<Functions> listOfSearchFunctions = new ArrayList();
        try {
            listOfSearchFunctions = functionBoProxy.getByCriteria(searchFunctions);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = functionBoProxy.saveErrorMessage(e.getMessage(), "FunctionBO.getFunctionByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[FunctionAction.search] Error when saving error,", e1);
            }
            logger.error("[FunctionAction.save] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfSearchFunctions);

        logger.info("[FunctionAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String downloadPdf() {
        logger.info("[FunctionAction.downloadPdf] start process >>>");

        reportParams.put("urlImage", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("companyName", CommonUtil.companyNameLogin());
        reportParams.put("titleReport", "Report Master Function");
        reportParams.put("userName", CommonUtil.userLogin());
        reportParams.put("keyCode", CommonUtil.companyIdLogin());

        try {
            preDownload();
        } catch (SQLException e) {
            Long logId = null;
            try {
                logId = functionBoProxy.saveErrorMessage(e.getMessage(), "downloadPdfReport");
            } catch (GeneralBOException e1) {
                logger.error("[FunctionAction.downloadPdf] Error when downloading ,", e1);
            }
            logger.error("[FunctionAction.downloadPdf] Error when downloading function ," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloading data, please inform to your admin.");
            return ERROR;

        }

        logger.info("[FunctionAction.downloadPdf] end process <<<");

        return "downloadPdf";
    }

    @Override
    public String downloadXls() {
        logger.info("[FunctionAction.downloadXls] start process >>>");

        List<Functions> listOfAllFunctions = new ArrayList();
        try {
            listOfAllFunctions = functionBoProxy.getAll();
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = functionBoProxy.saveErrorMessage(e.getMessage(), "FunctionBO.downloadXls");
            } catch (GeneralBOException e1) {
                logger.error("[FunctionAction.downloadXls] Error when retreiving data for download,", e1);
            }
            logger.error("[FunctionAction.downloadXls] Error when retreiving data for download," + "[" + logId + "] Found problem when retreiving data for download, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when retreiving data for download, please inform to your admin.");
            return ERROR;
        }

        CellDetail cellDetail;
        RowData rowData;
        List listOfData = new ArrayList();
        List listOfCell;
        List listOfColumn = new ArrayList();
        String titleReport = "Report Master Fuction";

        listOfColumn.add("Function Id");
        listOfColumn.add("Function Name");
        listOfColumn.add("URL");
        listOfColumn.add("Parent");
        listOfColumn.add("Func. Level");
        listOfColumn.add("Menu");
        listOfColumn.add("Created Date");
        listOfColumn.add("Created Who");
        listOfColumn.add("Last Update");
        listOfColumn.add("Last Update Who");
        listOfColumn.add("Flag");
        listOfColumn.add("Action");


        for (Functions data : listOfAllFunctions) {
            rowData = new RowData();
            listOfCell = new ArrayList();

            //Function Id
            cellDetail = new CellDetail();
            cellDetail.setCellID(0);
            cellDetail.setValueCell(data.getFuncId());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_CENTER);
            listOfCell.add(cellDetail);
            //Function Name
            cellDetail = new CellDetail();
            cellDetail.setCellID(1);
            cellDetail.setValueCell(data.getFuncName());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);
            //URL
            cellDetail = new CellDetail();
            cellDetail = new CellDetail();
            cellDetail.setCellID(2);
            cellDetail.setValueCell(data.getUrl());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);
            //Parent
            cellDetail = new CellDetail();
            cellDetail.setCellID(3);
            cellDetail.setValueCell(data.getParent());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);
            //Func.Level
            cellDetail = new CellDetail();
            cellDetail.setCellID(4);
            cellDetail.setValueCell(data.getFuncLevel());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);
            //Menu
            cellDetail = new CellDetail();
            cellDetail.setCellID(5);
            cellDetail.setValueCell(data.getMenu());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);
            //Created Date
            cellDetail = new CellDetail();
            cellDetail.setCellID(6);
            cellDetail.setValueCell(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(data.getCreatedDate()));
            cellDetail.setAlignmentCell(CellDetail.ALIGN_RIGHT);
            listOfCell.add(cellDetail);
            //Created Who
            cellDetail = new CellDetail();
            cellDetail.setCellID(7);
            cellDetail.setValueCell(data.getCreatedWho());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);
            //Last Update
            cellDetail = new CellDetail();
            cellDetail.setCellID(8);
            cellDetail.setValueCell(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(data.getLastUpdate()));
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);
            //Last UPdate who
            cellDetail = new CellDetail();
            cellDetail.setCellID(9);
            cellDetail.setValueCell(data.getLastUpdateWho());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);
            //Flag
            cellDetail = new CellDetail();
            cellDetail.setCellID(10);
            cellDetail.setValueCell(data.getFlag());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);
            //Action
            cellDetail = new CellDetail();
            cellDetail.setCellID(11);
            cellDetail.setValueCell(data.getAction());
            cellDetail.setAlignmentCell(CellDetail.ALIGN_LEFT);
            listOfCell.add(cellDetail);

            rowData.setListOfCell(listOfCell);
            listOfData.add(rowData);
        }


        HSSFWorkbook wb = DownloadUtil.generateExcelOutput(titleReport, null, listOfColumn, listOfData, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            wb.write(baos);
        } catch (IOException e) {
            Long logId = null;
            try {
                logId = functionBoProxy.saveErrorMessage(e.getMessage(), "FunctionBO.downloadXls");
            } catch (GeneralBOException e1) {
                logger.error("[FunctionAction.downloadXls] Error when downloading,", e1);
            }
            logger.error("[FunctionAction.downloadXls] Error when downloading data of function," + "[" + logId + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when downloding data, please inform to your admin.");
            return ERROR;
        }

        setExcelStream(new ByteArrayInputStream(baos.toByteArray()));
        setContentDisposition("filename=\"" + "MasterFunction.${documentFormat}\"");

        logger.info("[FunctionAction.downloadXls] end process <<<");

        return "downloadXls";
    }

    public List initComboParent(String query) {
        logger.info("[FunctionAction.initComboParent] start process >>>");

        List<Functions> listOfParent = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        FunctionBo functionBo = (FunctionBo) ctx.getBean("functionBoProxy");

        try {
            listOfParent = functionBo.getComboParentWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = functionBo.saveErrorMessage(e.getMessage(), "FunctionBO.getComboParentWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[FunctionAction.initComboParent] Error when saving error,", e1);
            }
            logger.error("[FunctionAction.initComboParent] Error when get combo parent," + "[" + logId + "] Found problem when retrieving combo parent data, please inform to your admin.", e);
        }

        logger.info("[FunctionAction.initComboParent] end process <<<");

        return listOfParent;
    }

    public List initComboFunction(String query) {
        logger.info("[FunctionAction.initComboFunction] start process >>>");

        List<Functions> listOfFunction = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        FunctionBo functionBo = (FunctionBo) ctx.getBean("functionBoProxy");

        try {
            listOfFunction = functionBo.getComboFunctionWithCriteria(query);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = functionBo.saveErrorMessage(e.getMessage(), "FunctionBO.getComboFunctionWithCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[FunctionAction.initComboFunction] Error when saving error,", e1);
            }
            logger.error("[FunctionAction.initComboFunction] Error when get combo function," + "[" + logId + "] Found problem when retrieving combo function data, please inform to your admin.", e);
        }

        logger.info("[FunctionAction.initComboFunction] end process <<<");

        return listOfFunction;
    }

    public String getLevelParent(String idParent) {
        logger.info("[FunctionAction.getLevelParent] start process >>>");

        String retVal = "1";
        Long parentLvl = null;

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        FunctionBo functionBo = (FunctionBo) ctx.getBean("functionBoProxy");
        Long lIdParent = Long.parseLong(idParent);
        try {
            parentLvl = functionBo.getParentLevel(lIdParent);
        } catch (HibernateException e) {
            logger.error("[FunctionAction.getLevelParent] Error when get level parent", e);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = functionBoProxy.saveErrorMessage(e.getMessage(), "FunctionBO.getLevelParent");
            } catch (GeneralBOException e1) {
                logger.error("[FunctionAction.getLevelParent] Error when saving error,", e1);
            }
            logger.error("[FunctionAction.getLevelParent] Error when get level parent," + "[" + logId + "] Found problem when get level parent, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when get level parent, please inform to your admin.");
            return ERROR;
        }

        if (parentLvl != null) {
            parentLvl = parentLvl + 1;
            retVal = parentLvl.toString();
        }

        logger.info("[FunctionAction.getLevelParent] end process <<<");

        return retVal;
    }

}
