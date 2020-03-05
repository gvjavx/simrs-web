package com.neurix.akuntansi.master.kodeRekening.action;
import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
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

public class KodeRekeningAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(KodeRekeningAction.class);
    private KodeRekeningBo kodeRekeningBoProxy;
    private KodeRekening kodeRekening;
    private List<KodeRekening> listOfComboKodeRekening = new ArrayList<KodeRekening>();

    @Override
    public String add() {
        logger.info("[KodeRekeningAction.add] start process >>>");
        KodeRekening addKodeRekening = new KodeRekening();
        setKodeRekening(addKodeRekening);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[KodeRekeningAction.add] stop process >>>");
        return "init_add";
    }

    public String saveAdd(){
        logger.info("[KodeRekeningAction.saveAdd] start process >>>");

        try {
            KodeRekening kodeRekening = getKodeRekening();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            kodeRekening.setCreatedWho(userLogin);
            kodeRekening.setLastUpdate(updateTime);
            kodeRekening.setCreatedDate(updateTime);
            kodeRekening.setLastUpdateWho(userLogin);
            kodeRekening.setAction("C");
            kodeRekening.setFlag("Y");

            kodeRekeningBoProxy.saveAdd(kodeRekening);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kodeRekeningBoProxy.saveErrorMessage(e.getMessage(), "kodeRekeningBo.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[KodeRekeningAction.saveAdd] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KodeRekeningAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[KodeRekeningAction.saveAdd] end process >>>");
        return "success_save_add";
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
        logger.info("[KodeRekeningAction.search] start process >>>");

        KodeRekening searchKodeRekening = getKodeRekening();
        List<KodeRekening> listOfsearchKodeRekening = new ArrayList();

        try {
            listOfsearchKodeRekening = kodeRekeningBoProxy.getByCriteria(searchKodeRekening);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kodeRekeningBoProxy.saveErrorMessage(e.getMessage(), "kodeRekeningBo.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KodeRekeningAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[KodeRekeningAction.save] Error when searching by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchKodeRekening);

        logger.info("[KodeRekeningAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[KodeRekeningAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        logger.info("[KodeRekeningAction.initForm] end process >>>");
        return INPUT;
    }

    public List<KodeRekening> initKodeRekeningList(String nilai) {
        logger.info("[KodeRekeningAction.initKodeRekeningList] start process >>>");

        KodeRekening searchKodeRekening = new KodeRekening();
        searchKodeRekening.setFlag("Y");
        searchKodeRekening.setLevel(Long.valueOf(nilai));

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        List<KodeRekening> kodeRekeningList = new ArrayList();

        try {
            kodeRekeningList = kodeRekeningBo.getByCriteria(searchKodeRekening);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kodeRekeningBo.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KodeRekeningAction.initKodeRekeningList] Error when saving error,", e1);
            }
            logger.error("[KodeRekeningAction.initKodeRekeningList] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return kodeRekeningList;
    }
    public List<KodeRekening> cekAvailableCoa(String nilai) {
        logger.info("[KodeRekeningAction.cekAvailableCoa] start process >>>");

        KodeRekening searchKodeRekening = new KodeRekening();
        searchKodeRekening.setFlag("Y");
        searchKodeRekening.setKodeRekening(nilai);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        List<KodeRekening> kodeRekeningList = new ArrayList();
        try {
            kodeRekeningList = kodeRekeningBo.getByCriteria(searchKodeRekening);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kodeRekeningBo.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KodeRekeningAction.cekAvailableCoa] Error when saving error,", e1);
            }
            logger.error("[KodeRekeningAction.cekAvailableCoa] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        return kodeRekeningList;
    }

    public boolean cekAvailableParent(String nilai) {
        logger.info("[KodeRekeningAction.cekAvailableParent] start process >>>");
        boolean adaParent=true;

        String[] coa=nilai.split("\\.");
        String coaParent="";
        for (int i=0;i<coa.length;i++){
            if (i==0){
                coaParent=coaParent+coa[i];
            }else{
                KodeRekening searchKodeRekening = new KodeRekening();
                searchKodeRekening.setFlag("Y");
                searchKodeRekening.setKodeRekening(coaParent);

                ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
                KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
                List<KodeRekening> kodeRekeningList = new ArrayList();
                try {
                    kodeRekeningList = kodeRekeningBo.getByCriteria(searchKodeRekening);
                } catch (GeneralBOException e) {
                    Long logId = null;
                    try {
                        logId = kodeRekeningBo.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getByCriteria");
                    } catch (GeneralBOException e1) {
                        logger.error("[KodeRekeningAction.cekAvailableParent] Error when saving error,", e1);
                    }
                    logger.error("[KodeRekeningAction.cekAvailableParent] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
                    addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
                }
                if (kodeRekeningList.size()==0){
                    adaParent=false;
                    break;
                }
                coaParent=coaParent+"."+coa[i];
            }

        }
        return adaParent;
    }
    public List<KodeRekening> initTypeaheadKodeRekening(String coa) {
        logger.info("[KodeRekeningAction.initTypeaheadKodeRekening] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");
        List<KodeRekening> kodeRekeningList = new ArrayList();
        try {
            kodeRekeningList = kodeRekeningBo.typeaheadKodeRekening(coa);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kodeRekeningBo.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KodeRekeningAction.initTypeaheadKodeRekening] Error when saving error,", e1);
            }
            logger.error("[KodeRekeningAction.initTypeaheadKodeRekening] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return kodeRekeningList;
    }

    public List<KodeRekening> initKodeRekeningSearch(String rekeningId, String namaRekening, String coa) {
        logger.info("[KodeRekeningAction.initKodeRekeningSearch] start process >>>");

        List<KodeRekening> listOfsearchKodeRekening = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KodeRekeningBo kodeRekeningBo= (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");

        KodeRekening search = new KodeRekening();
        search.setKodeRekening(coa);
        search.setRekeningId(rekeningId);
        search.setNamaKodeRekening(namaRekening);
        search.setFlag("Y");

        try {
            listOfsearchKodeRekening = kodeRekeningBo.getByCriteria(search);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kodeRekeningBo.saveErrorMessage(e.getMessage(), "kodeRekeningBo.getDataStrukturCoa");
            } catch (GeneralBOException e1) {
                logger.error("[KodeRekeningAction.initKodeRekeningSearch] Error when saving error,", e1);
            }
            logger.error("[KodeRekeningAction.initKodeRekeningSearch] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }

        logger.info("[KodeRekeningAction.initKodeRekeningSearch] end process <<<");
        return listOfsearchKodeRekening;
    }

    public String saveEdit(String id, String kodeRekeningName,String coa, String tipeRekeningId,String tipeEdit){
        logger.info("[KodeRekeningAction.saveEdit] start process >>>");
        String status="";
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        KodeRekening edit = new KodeRekening();
        edit.setRekeningId(id);
        edit.setKodeRekening(coa);
        edit.setNamaKodeRekening(kodeRekeningName);
        edit.setTipeRekeningId(tipeRekeningId);
        edit.setLastUpdateWho(userLogin);
        edit.setLastUpdate(updateTime);
        edit.setAction("U");
        if ("edit".equalsIgnoreCase(tipeEdit)){
            edit.setFlag("Y");
        }else if ("delete".equalsIgnoreCase(tipeEdit)){
            edit.setFlag("N");
        }
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");

        try {
            kodeRekeningBo.saveEdit(edit);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kodeRekeningBo.saveErrorMessage(e.getMessage(), "kodeRekeningBoProxy.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[KodeRekeningAction.saveEdit] Error when saving error,", e1);
                return status;
            }
            logger.error("[KodeRekeningAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return status;
        }
        logger.info("[KodeRekeningAction.saveEdit] end process <<<");
        return status;
    }
    public List<KodeRekening> searchKodeRekening(String coa) {
        logger.info("[KodeRekeningAction.searchKodeRekening] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KodeRekening> kodeRekeningList = (List<KodeRekening>) session.getAttribute("listOfResultKodeRekening");
        if (("").equalsIgnoreCase(coa)){
            session.setAttribute("listOfResultKodeRekening",kodeRekeningList);
        }else{
            List<KodeRekening> kodeRekenings = new ArrayList<>();
            for (KodeRekening kodeRekening:kodeRekenings){
                if (coa.equalsIgnoreCase(kodeRekening.getKodeRekening())){
                    kodeRekenings.add(kodeRekening);
                    session.setAttribute("listOfResultKodeRekening",kodeRekenings);
                }
            }
        }
        logger.info("[KodeRekeningAction.searchKodeRekening] end process >>>");
        return kodeRekeningList;
    }
    public String cekStatusBeforeSave() {
        logger.info("[KodeRekeningAction.cekStatusBeforeSave] start process >>>");
        String status="";
        boolean adaKredit=false;
        boolean adaDebet=false;
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KodeRekening> kodeRekeningList = (List<KodeRekening>) session.getAttribute("listOfResultKodeRekening");
        if (kodeRekeningList==null){
            status="Kode Rekening masih kosong tambahkan minimal 1 Kode Rekening Debet dan 1 Kode Rekening Kredit";
        }else{
            if (kodeRekeningList.size()==0){
                status="Kode Rekening masih kosong tambahkan minimal 1 Kode Rekening Debet dan 1 Kode Rekening Kredit";
            }else{
                for (KodeRekening kodeRekening : kodeRekeningList){
                    if (kodeRekening.getPosisi().equalsIgnoreCase("D")){
                        adaDebet=true;
                    }
                    if (kodeRekening.getPosisi().equalsIgnoreCase("K")){
                        adaKredit=true;
                    }
                }
                if (!adaDebet){
                    status="Kode Rekening debet belum ada ";
                }
                if (!adaKredit){
                    status="Kode Rekening kredit belum ada ";
                }
            }
        }

        logger.info("[KodeRekeningAction.cekStatusBeforeSave] end process >>>");
        return status;
    }
    public void saveAddKodeRekening(String kodeRekening,String namaKodeRekening,String posisi) {
        logger.info("[KodeRekeningAction.saveAddKodeRekening] start process >>>");
        String posisiName = "";
        if ("D".equalsIgnoreCase(posisi)){
            posisiName="Debet";
        }else{
            posisiName="Kredit";
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KodeRekening> kodeRekeningList = (List<KodeRekening>) session.getAttribute("listOfResultKodeRekening");
        List<KodeRekening> kodeRekeningArrayList = new ArrayList<>();
        boolean ada=false;
        if (kodeRekeningList==null){
            KodeRekening newData = new KodeRekening();
            newData.setKodeRekening(kodeRekening);
            newData.setNamaKodeRekening(namaKodeRekening);
            newData.setPosisi(posisi);
            newData.setPosisiName(posisiName);

            kodeRekeningArrayList.add(newData);
            session.setAttribute("listOfResultKodeRekening",kodeRekeningArrayList);
        }else{
            kodeRekeningArrayList.addAll(kodeRekeningList);
            for (KodeRekening kodeRekening1:kodeRekeningList){
                if (kodeRekening1.getKodeRekening().equalsIgnoreCase(kodeRekening)){
                    ada=true;
                    break;
                }
            }
            if (!ada){
                KodeRekening newData = new KodeRekening();
                newData.setKodeRekening(kodeRekening);
                newData.setNamaKodeRekening(namaKodeRekening);
                newData.setPosisi(posisi);
                newData.setPosisiName(posisiName);
                kodeRekeningArrayList.add(newData);
                session.setAttribute("listOfResultKodeRekening",kodeRekeningArrayList);
            }
        }

        logger.info("[KodeRekeningAction.saveAddKodeRekening] end process >>>");
    }
    public void saveDeleteKodeRekening(String coa) {
        logger.info("[KodeRekeningAction.saveDeleteAnggota] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<KodeRekening> kodeRekeningList = (List<KodeRekening>) session.getAttribute("listOfResultKodeRekening");
        List<KodeRekening> kodeRekeningArrayList = new ArrayList<>();
        for (KodeRekening kodeRekening:kodeRekeningList){
            if (kodeRekening.getKodeRekening().equalsIgnoreCase(coa)){
            }else{
                kodeRekeningArrayList.add(kodeRekening);
            }
        }
        session.setAttribute("listOfResultKodeRekening",kodeRekeningArrayList);
        logger.info("[KodeRekeningAction.saveDeleteAnggota] end process >>>");
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

    public List<KodeRekening> getListOfComboKodeRekening() {
        return listOfComboKodeRekening;
    }

    public void setListOfComboKodeRekening(List<KodeRekening> listOfComboKodeRekening) {
        this.listOfComboKodeRekening = listOfComboKodeRekening;
    }


    public KodeRekeningBo getKodeRekeningBoProxy() {
        return kodeRekeningBoProxy;
    }

    public void setKodeRekeningBoProxy(KodeRekeningBo kodeRekeningBoProxy) {
        this.kodeRekeningBoProxy = kodeRekeningBoProxy;
    }

    public KodeRekening getKodeRekening() {
        return kodeRekening;
    }

    public void setKodeRekening(KodeRekening kodeRekening) {
        this.kodeRekening = kodeRekening;
    }

    private List<KodeRekening> initComboKodeRekening;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        KodeRekeningAction.logger = logger;
    }


    public List<KodeRekening> getInitComboKodeRekening() {
        return initComboKodeRekening;
    }

    public void setInitComboKodeRekening(List<KodeRekening> initComboKodeRekening) {
        this.initComboKodeRekening = initComboKodeRekening;
    }
}
