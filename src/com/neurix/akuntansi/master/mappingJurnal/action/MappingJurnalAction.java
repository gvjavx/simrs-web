package com.neurix.akuntansi.master.mappingJurnal.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.akuntansi.master.mappingJurnal.bo.MappingJurnalBo;
import com.neurix.akuntansi.master.mappingJurnal.model.MappingJurnal;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
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

public class MappingJurnalAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(MappingJurnalAction.class);
    private MappingJurnalBo mappingJurnalBoProxy;
    private MappingJurnal mappingJurnal;
    private List<MappingJurnal> listOfComboMappingJurnal = new ArrayList<MappingJurnal>();

    public List<MappingJurnal> getListOfComboMappingJurnal() {
        return listOfComboMappingJurnal;
    }

    public void setListOfComboMappingJurnal(List<MappingJurnal> listOfComboMappingJurnal) {
        this.listOfComboMappingJurnal = listOfComboMappingJurnal;
    }


    public MappingJurnalBo getMappingJurnalBoProxy() {
        return mappingJurnalBoProxy;
    }

    public void setMappingJurnalBoProxy(MappingJurnalBo mappingJurnalBoProxy) {
        this.mappingJurnalBoProxy = mappingJurnalBoProxy;
    }

    public MappingJurnal getMappingJurnal() {
        return mappingJurnal;
    }

    public void setMappingJurnal(MappingJurnal mappingJurnal) {
        this.mappingJurnal = mappingJurnal;
    }

    private List<MappingJurnal> initComboMappingJurnal;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MappingJurnalAction.logger = logger;
    }


    public List<MappingJurnal> getInitComboMappingJurnal() {
        return initComboMappingJurnal;
    }

    public void setInitComboMappingJurnal(List<MappingJurnal> initComboMappingJurnal) {
        this.initComboMappingJurnal = initComboMappingJurnal;
    }

    public MappingJurnal init(String kode, String flag){
        logger.info("[MappingJurnalAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MappingJurnal> listOfResult = (List<MappingJurnal>) session.getAttribute("listOfResult");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (MappingJurnal mappingJurnal: listOfResult) {
                    if(kode.equalsIgnoreCase(mappingJurnal.getMappingJurnalId()) && flag.equalsIgnoreCase(mappingJurnal.getFlag())){
                        setMappingJurnal(mappingJurnal);
                        break;
                    }
                }
            } else {
                setMappingJurnal(new MappingJurnal());
            }

            logger.info("[MappingJurnalAction.init] end process >>>");
        }
        return getMappingJurnal();
    }

    @Override
    public String add() {
        logger.info("[MappingJurnalAction.add] start process >>>");
        MappingJurnal addMappingJurnal = new MappingJurnal();
        setMappingJurnal(addMappingJurnal);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.removeAttribute("listOfResultMapping");

        logger.info("[MappingJurnalAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[MappingJurnalAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        MappingJurnal editMappingJurnal = new MappingJurnal();

        if(itemFlag != null){
            try {
                editMappingJurnal = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "MappingJurnalBO.getMappingJurnalByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[MappingJurnalAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[MappingJurnalAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editMappingJurnal != null) {
                setMappingJurnal(editMappingJurnal);
            } else {
                editMappingJurnal.setFlag(itemFlag);
                editMappingJurnal.setMappingJurnalId(itemId);
                setMappingJurnal(editMappingJurnal);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            editMappingJurnal.setMappingJurnalId(itemId);
            editMappingJurnal.setFlag(getFlag());
            setMappingJurnal(editMappingJurnal);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }
        setAddOrEdit(true);
        logger.info("[MappingJurnalAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[MappingJurnalAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        MappingJurnal deleteMappingJurnal = new MappingJurnal();

        if (itemFlag != null ) {

            try {
                deleteMappingJurnal = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "MappingJurnalBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[MappingJurnalAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[MappingJurnalAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteMappingJurnal != null) {
                setMappingJurnal(deleteMappingJurnal);

            } else {
                deleteMappingJurnal.setMappingJurnalId(itemId);
                deleteMappingJurnal.setFlag(itemFlag);
                setMappingJurnal(deleteMappingJurnal);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteMappingJurnal.setMappingJurnalId(itemId);
            deleteMappingJurnal.setFlag(itemFlag);
            setMappingJurnal(deleteMappingJurnal);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[MappingJurnalAction.delete] end process <<<");
        return "init_delete";
    }

    @Override
    public String view() {
        logger.info("[MappingJurnalAction.view] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        MappingJurnal deleteMappingJurnal = new MappingJurnal();

        if (itemFlag != null ) {
            try {
                deleteMappingJurnal = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "MappingJurnalBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[MappingJurnalAction.view] Error when retrieving delete data,", e1);
                }
                logger.error("[MappingJurnalAction.view] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteMappingJurnal != null) {
                setMappingJurnal(deleteMappingJurnal);

            } else {
                deleteMappingJurnal.setMappingJurnalId(itemId);
                deleteMappingJurnal.setFlag(itemFlag);
                setMappingJurnal(deleteMappingJurnal);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            deleteMappingJurnal.setMappingJurnalId(itemId);
            deleteMappingJurnal.setFlag(itemFlag);
            setMappingJurnal(deleteMappingJurnal);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }
        logger.info("[MappingJurnalAction.view] end process <<<");
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    public String saveEdit(){
        logger.info("[MappingJurnalAction.saveEdit] start process >>>");
        try {
            MappingJurnal editMappingJurnal = getMappingJurnal();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editMappingJurnal.setLastUpdateWho(userLogin);
            editMappingJurnal.setLastUpdate(updateTime);
            editMappingJurnal.setAction("U");
            editMappingJurnal.setFlag("Y");

            mappingJurnalBoProxy.saveEdit(editMappingJurnal);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "MappingJurnalBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[MappingJurnalAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MappingJurnalAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[MappingJurnalAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[MappingJurnalAction.saveDelete] start process >>>");
        try {
            MappingJurnal deleteMappingJurnal = getMappingJurnal();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteMappingJurnal.setLastUpdate(updateTime);
            deleteMappingJurnal.setLastUpdateWho(userLogin);
            deleteMappingJurnal.setAction("U");
            deleteMappingJurnal.setFlag("N");

            mappingJurnalBoProxy.saveDelete(deleteMappingJurnal);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "MappingJurnalBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[MappingJurnalAction.saveDelete] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MappingJurnalAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[MappingJurnalAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String saveAdd(){
        logger.info("[MappingJurnalAction.saveAdd] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MappingJurnal> listOfResult = (List<MappingJurnal>) session.getAttribute("listOfResultMapping");
        MappingJurnal data = getMappingJurnal();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

        try {
            for (MappingJurnal mappingJurnal : listOfResult){
                mappingJurnal.setTipeJurnalId(data.getTipeJurnalId());
                mappingJurnal.setTransId(data.getTransId());
                mappingJurnal.setCreatedWho(userLogin);
                mappingJurnal.setLastUpdate(updateTime);
                mappingJurnal.setCreatedDate(updateTime);
                mappingJurnal.setLastUpdateWho(userLogin);
                mappingJurnal.setAction("C");
                mappingJurnal.setFlag("Y");

                mappingJurnalBoProxy.saveAdd(mappingJurnal);
            }

        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "liburBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[liburAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[liburAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        session.removeAttribute("listOfResult");

        logger.info("[liburAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    @Override
    public String search() {
        logger.info("[MappingJurnalAction.search] start process >>>");
        MappingJurnal searchMappingJurnal = getMappingJurnal();
        List<MappingJurnal> listOfsearchMappingJurnal = new ArrayList();
        try {
            listOfsearchMappingJurnal = mappingJurnalBoProxy.getByCriteria(searchMappingJurnal);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "MappingJurnalBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MappingJurnalAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[MappingJurnalAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultMapping");
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchMappingJurnal);

        logger.info("[MappingJurnalAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[MappingJurnalAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");

        logger.info("[MappingJurnalAction.initForm] end process >>>");
        return INPUT;
    }
    public String initComboMappingJurnal() {
        logger.info("[MappingJurnalAction.initComboMappingJurnal] start process >>>");

        MappingJurnal search = new MappingJurnal();
        List<MappingJurnal> listOfSearchMappingJurnal = new ArrayList();
        search.setFlag("Y");
        try {
            listOfSearchMappingJurnal = mappingJurnalBoProxy.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = mappingJurnalBoProxy.saveErrorMessage(e.getMessage(), "mappingJurnalBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[MappingJurnalAction.initComboMappingJurnal] Error when saving error,", e1);
            }
            logger.error("[MappingJurnalAction.initComboMappingJurnal] Error when searching function by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return "failure";
        }

        listOfComboMappingJurnal.addAll(listOfSearchMappingJurnal);
        logger.info("[MappingJurnalAction.initComboMappingJurnal] end process <<<");

        return SUCCESS;
    }

    public void saveKodeRekeningSession(String kodeRekening , String posisi,String master,String bukti, String kodeBarang,String listKirim,String parameter) {
        logger.info("[MappingJurnalAction.savePegawaiShift] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MappingJurnal> listOfResult = (List<MappingJurnal>) session.getAttribute("listOfResultMapping");
        boolean save = true;
        if (listOfResult==null){
            listOfResult= new ArrayList<>();
        }else{
            for (MappingJurnal mappingJurnal : listOfResult){
                if (kodeRekening.equalsIgnoreCase(mappingJurnal.getKodeRekening())){
                    save=false;
                }
            }
        }
        if (save){
            MappingJurnal result = new MappingJurnal();
            result.setKodeRekening(kodeRekening);
            result.setPosisi(posisi);
            result.setMasterId(master);
            result.setBukti(bukti);
            result.setKodeBarang(kodeBarang);
            result.setKirimList(listKirim);
            result.setKeterangan(parameter);
            listOfResult.add(result);
        }

        session.setAttribute("listOfResultMapping",listOfResult);
        logger.info("[MappingJurnalAction.savePegawaiShift] end process <<<");
    }

    public List<MappingJurnal> searchKodeRekeningSession() {
        logger.info("[MappingJurnalAction.searchKodeRekeningSession] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<MappingJurnal> listOfsearch= (List<MappingJurnal>) session.getAttribute("listOfResultMapping");
        return listOfsearch;
    }

    public String cekBeforeAdd(String tipeJurnal,String transId){
        String status="";
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        MappingJurnalBo mappingJurnalBo= (MappingJurnalBo) ctx.getBean("mappingJurnalBoProxy");
        List<MappingJurnal> mappingJurnalList = new ArrayList<>();
        MappingJurnal search = new MappingJurnal();
        search.setFlag("Y");
        search.setTransId(transId);
        try {
            mappingJurnalList=mappingJurnalBo.getByCriteria(search);
        } catch (GeneralBOException e1) {
            logger.error("[MappingJurnalAction.initComboMappingJurnal] Error when saving error,", e1);
        }
        if (mappingJurnalList.size()!=0){
            status="Transaksi Billing ini sudah ada , gunakan edit";
        }else{
            HttpSession session = ServletActionContext.getRequest().getSession();
            List<MappingJurnal> listOfsearch= (List<MappingJurnal>) session.getAttribute("listOfResultMapping");
            boolean adaDebit=false;
            boolean adaKredit=false;

            if (listOfsearch==null){
                status="Belum ada kode rekening, silahkan ditambahkan";
            }else{
                for(MappingJurnal mappingJurnal:listOfsearch){
                    if (("D").equalsIgnoreCase(mappingJurnal.getPosisi())){
                        adaDebit=true;
                    }else if (("K").equalsIgnoreCase(mappingJurnal.getPosisi())){
                        adaKredit=true;
                    }
                }
                if (!adaDebit){
                    status="Belum ada kode rekening dengan posisi debit";
                }else if (!adaKredit){
                    status="belum ada kode rekening dengan posisi kredit";
                }
            }
        }
        return status;
    }

    public String paging(){
        return SUCCESS;
    }

    @Override
    public String downloadPdf() {
        return SUCCESS;
    }

    @Override
    public String downloadXls() {
        return SUCCESS;
    }
}
