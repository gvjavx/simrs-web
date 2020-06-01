package com.neurix.hris.master.mappingpersengaji.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.mappingpersengaji.bo.MappingPersenGajiBo;
import com.neurix.hris.master.mappingpersengaji.model.MappingPersenGaji;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MappingPersenGajiAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(MappingPersenGajiAction.class);
    MappingPersenGaji mappingPersenGaji;
    private MappingPersenGajiBo mappingPersenGajiBoProxy;

    public MappingPersenGaji getMappingPersenGaji() {
        return mappingPersenGaji;
    }

    public void setMappingPersenGaji(MappingPersenGaji mappingPersenGaji) {
        this.mappingPersenGaji = mappingPersenGaji;
    }

    public MappingPersenGajiBo getMappingPersenGajiBoProxy() {
        return mappingPersenGajiBoProxy;
    }

    public void setMappingPersenGajiBoProxy(MappingPersenGajiBo mappingPersenGajiBoProxy) {
        this.mappingPersenGajiBoProxy = mappingPersenGajiBoProxy;
    }

    public MappingPersenGaji init(String kode, String flag){
        logger.info("[LabAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MappingPersenGaji> listOfResult = (List<MappingPersenGaji>) session.getAttribute("listOfResultMappingPersen");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (MappingPersenGaji mappingPersenGaji: listOfResult) {
                    if(kode.equalsIgnoreCase(mappingPersenGaji.getMappingPersenGajiId()) && flag.equalsIgnoreCase(mappingPersenGaji.getFlag())){
                        setMappingPersenGaji(mappingPersenGaji);
                        break;
                    }
                }
            } else {
                setMappingPersenGaji(new MappingPersenGaji());
            }
            logger.info("[LabAction.init] end process >>>");
        }
        return getMappingPersenGaji();
    }

    @Override
    public String add() {
        logger.info("[DokterAction.add] start process >>>");
        MappingPersenGaji addMapping = new MappingPersenGaji();
        setMappingPersenGaji(addMapping);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[DokterAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[DokterAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        MappingPersenGaji editMapping = new MappingPersenGaji();

        if(itemFlag != null){
            try {
                editMapping = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = mappingPersenGajiBoProxy.saveErrorMessage(e.getMessage(), "DokterBO.getDokterByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[DokterAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[DokterAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editMapping != null) {
                setMappingPersenGaji(editMapping);
            } else {
                editMapping.setFlag(itemFlag);
                //editPayrollSkalaGaji.getSkalaGajiId(itemId);
                setMappingPersenGaji(editMapping);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollSkalaGaji.getSkalaGajiId(itemId);
            editMapping.setFlag(getFlag());
            setMappingPersenGaji(editMapping);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[DokterAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[DokterAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        MappingPersenGaji deleteMapping = new MappingPersenGaji();

        if (itemFlag != null ) {

            try {
                deleteMapping = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = mappingPersenGajiBoProxy.saveErrorMessage(e.getMessage(), "DokterBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[DokterAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[DokterAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteMapping != null) {
                setMappingPersenGaji(deleteMapping);

            } else {
                //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
                deleteMapping.setFlag(itemFlag);
                setMappingPersenGaji(deleteMapping);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteMapping.setFlag(itemFlag);
            setMappingPersenGaji(deleteMapping);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[DokterAction.delete] end process <<<");

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
        logger.info("[PelayananAction.search] start process >>>");

        MappingPersenGaji searchMappingGaji = getMappingPersenGaji();
        List<MappingPersenGaji> listOfsearchMappingPersenGaji = new ArrayList();

        try {
            listOfsearchMappingPersenGaji = mappingPersenGajiBoProxy.getByCriteria(searchMappingGaji);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingPersenGajiBoProxy.saveErrorMessage(e.getMessage(), "PayrollSkalaGajiBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PelayananAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PelayananAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultMappingPersen");
        session.setAttribute("listOfResultMappingPersen", listOfsearchMappingPersenGaji);

        logger.info("[MappingPersenGajiAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[MappingPersenGajiAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultMappingPersen");
        logger.info("[MappingPersenGajiAction.initForm] end process >>>");
        return INPUT;
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    public String saveAdd(){
        logger.info("[DokterAction.saveAdd] start process >>>");

        try {
            MappingPersenGaji mappingPersenGaji = getMappingPersenGaji();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            mappingPersenGaji.setCreatedWho(userLogin);
            mappingPersenGaji.setLastUpdate(updateTime);
            mappingPersenGaji.setCreatedDate(updateTime);
            mappingPersenGaji.setLastUpdateWho(userLogin);
            mappingPersenGaji.setAction("C");
            mappingPersenGaji.setFlag("Y");

            mappingPersenGajiBoProxy.saveAdd(mappingPersenGaji);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingPersenGajiBoProxy.saveErrorMessage(e.getMessage(), "DokterBo.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[DokterAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[DokterAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultMappingPersen");

        logger.info("[MappingPersenGajiAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public String saveEdit(){
        logger.info("[PayrollSkalaGajiAction.saveEdit] start process >>>");
        try {

            MappingPersenGaji editMapping = getMappingPersenGaji();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editMapping.setLastUpdateWho(userLogin);
            editMapping.setLastUpdate(updateTime);
            editMapping.setAction("U");
            editMapping.setFlag("Y");

            mappingPersenGajiBoProxy.saveEdit(editMapping);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingPersenGajiBoProxy.saveErrorMessage(e.getMessage(), "DokterBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[DokterAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[DokterAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[DokterAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[DokterAction.saveDelete] start process >>>");
        try {

            MappingPersenGaji deleteMapping = getMappingPersenGaji();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteMapping.setLastUpdate(updateTime);
            deleteMapping.setLastUpdateWho(userLogin);
            deleteMapping.setAction("U");
            deleteMapping.setFlag("N");

            mappingPersenGajiBoProxy.saveDelete(deleteMapping);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingPersenGajiBoProxy.saveErrorMessage(e.getMessage(), "DokterBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[DokterAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[DokterAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[DokterAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

}