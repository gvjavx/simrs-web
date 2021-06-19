package com.neurix.akuntansi.master.akunMataUang.action;

import com.neurix.akuntansi.master.akunMataUang.bo.AkunMataUangBo;
import com.neurix.akuntansi.master.akunMataUang.bo.impl.AkunMataUangBoImpl;
import com.neurix.akuntansi.master.akunMataUang.model.AkunMataUang;
import com.neurix.akuntansi.master.akunMataUang.model.ImAkunMataUangEntity;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aji Noor on 05/02/2021.
 */


public class AkunMataUangAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(AkunMataUangAction.class);
    private AkunMataUangBo akunMataUangBoProxy;
    private AkunMataUang akunMataUang;
    private List<AkunMataUang> listOfComboCurrency = new ArrayList<AkunMataUang>();

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        AkunMataUangAction.logger = logger;
    }

    public AkunMataUangBo getAkunMataUangBoProxy() {
        return akunMataUangBoProxy;
    }

    public void setAkunMataUangBoProxy(AkunMataUangBo akunMataUangBoProxy) {
        this.akunMataUangBoProxy = akunMataUangBoProxy;
    }

    public AkunMataUang getAkunMataUang() {
        return akunMataUang;
    }

    public void setAkunMataUang(AkunMataUang akunMataUang) {
        this.akunMataUang = akunMataUang;
    }

    public List<AkunMataUang> getListOfComboCurrency() {
        return listOfComboCurrency;
    }

    public void setListOfComboCurrency(List<AkunMataUang> listOfComboCurrency) {
        this.listOfComboCurrency = listOfComboCurrency;
    }

    @Override
    public String add() {
        return null;
    }
    @Override
    public String edit() {
        return null;
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
        return null;
    }

    @Override
    public String search() {
        logger.info("[AkunMataUangAction.search] start process >>>");
        AkunMataUang searchKodeRekening = getAkunMataUang();
        List<AkunMataUang> listOfsearchKodeRekening = new ArrayList();

        try {
            listOfsearchKodeRekening = akunMataUangBoProxy.getByCriteria(searchKodeRekening);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunMataUangBoProxy.saveErrorMessage(e.getMessage(), "AkunMataUangBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[AkunMataUangAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[AkunMataUangAction.save] Error when searching by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchKodeRekening);

        logger.info("[AkunMataUangAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[AkunMataUangAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        logger.info("[AkunMataUangAction.initForm] end process >>>");
        return INPUT;
    }


    @Override
    public String downloadPdf() {
        return SUCCESS;
    }

    @Override
    public String downloadXls() {
        return SUCCESS;
    }

    public String paging(){
        return SUCCESS;
    }

    public List<AkunMataUang> getCurrency() {
        AkunMataUang akunMataUang = new AkunMataUang();
        akunMataUang.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AkunMataUangBo akunMataUangBo = (AkunMataUangBo) ctx.getBean("akunMataUangBoProxy");
        List<AkunMataUang> listOfMataUang = new ArrayList<>();
        try {
            listOfMataUang = akunMataUangBo.getByCriteria(akunMataUang);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunMataUangBo.saveErrorMessage(e.getMessage(), "akunMataUangBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[AkunMataUangAction.initComboBranch] Error when saving error,", e1);
            }
            logger.error("[AkunMataUangAction.initComboBranch] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
        }

        return listOfMataUang;
    }

    public List<AkunMataUang> getCurrencyActive() {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        AkunMataUangBo akunMataUangBo = (AkunMataUangBo) ctx.getBean("akunMataUangBoProxy");
        List<AkunMataUang> listOfMataUang = new ArrayList<>();
        try {
            List<ImAkunMataUangEntity> imAkunMataUangEntityList = akunMataUangBo.getCurrencyActive();

            if(imAkunMataUangEntityList != null){
                AkunMataUang returnAkunMataUang;
                // Looping from dao to object and save in collection
                for(ImAkunMataUangEntity akunMataUangEntity : imAkunMataUangEntityList){
                    returnAkunMataUang = new AkunMataUang();
                    returnAkunMataUang.setFlag(akunMataUangEntity.getFlag());
                    returnAkunMataUang.setKodeMataUang(akunMataUangEntity.getKodeMataUang());
                    returnAkunMataUang.setNamaMataUang(akunMataUangEntity.getNamaMataUang());
                    returnAkunMataUang.setMataUangId(akunMataUangEntity.getMataUangId());

                    listOfMataUang.add(returnAkunMataUang);
                }
            }
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = akunMataUangBo.saveErrorMessage(e.getMessage(), "akunMataUangBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[AkunMataUangAction.initComboBranch] Error when saving error,", e1);
            }
            logger.error("[AkunMataUangAction.initComboBranch] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
        }

        return listOfMataUang;
    }

    public String initComboCurrency(){
        logger.error("[AkunMataUangAction.initComboCurrency] start initComboCurrency >>>");

        AkunMataUang mataUang = new AkunMataUang();
        mataUang.setFlag("Y");
        List<AkunMataUang> listMataUang = new ArrayList();
        try {
            listMataUang = akunMataUangBoProxy.getByCriteria(mataUang);
        } catch (GeneralBOException e) {
            logger.error("ini error, "+e.getMessage());
            throw new GeneralBOException("ini error, "+e.getMessage());
        }
        listOfComboCurrency.addAll(listMataUang);
        return "init_combo_mata_uang";
    }

}
