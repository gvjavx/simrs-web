package com.neurix.simrs.transaksi.diagnosarawat.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.diagnosa.bo.DiagnosaBo;
import com.neurix.simrs.master.diagnosa.model.Diagnosa;
import com.neurix.simrs.transaksi.diagnosarawat.bo.DiagnosaRawatBo;
import com.neurix.simrs.transaksi.diagnosarawat.model.DiagnosaRawat;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DiagnosaRawatAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(DiagnosaRawatAction.class);
    private DiagnosaRawatBo diagnosaRawatBoProxy;
    private DiagnosaRawat diagnosaRawat;
    private DiagnosaBo diagnosaBoProxy;

    public DiagnosaBo getDiagnosaBoProxy() {
        return diagnosaBoProxy;
    }

    public void setDiagnosaBoProxy(DiagnosaBo diagnosaBoProxy) {
        this.diagnosaBoProxy = diagnosaBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        DiagnosaRawatAction.logger = logger;
    }

    public DiagnosaRawatBo getDiagnosaRawatBoProxy() {
        return diagnosaRawatBoProxy;
    }

    public void setDiagnosaRawatBoProxy(DiagnosaRawatBo diagnosaRawatBoProxy) {
        this.diagnosaRawatBoProxy = diagnosaRawatBoProxy;
    }

    public DiagnosaRawat getDiagnosaRawat() {
        return diagnosaRawat;
    }

    public void setDiagnosaRawat(DiagnosaRawat diagnosaRawat) {
        this.diagnosaRawat = diagnosaRawat;
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
        return null;
    }

    @Override
    public String initForm() {
        return null;
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    public String saveDiagnosa(String idDetailCheckup, String idDiagnosa, String jenisDiagnosa){
        logger.info("[DiagnosaRawatAction.saveDiagnosa] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
            List<Diagnosa> diagnosaList = new ArrayList<>();
            Diagnosa diagnosa = new Diagnosa();
            diagnosa.setIdDiagnosa(idDiagnosa);

            try {
                diagnosaList = diagnosaBoProxy.getByCriteria(diagnosa);
            }catch (GeneralBOException e){
                logger.error("[DiagnosaRawatAction.saveDiagnosa] Error when search dec diagnosa by id ," + "Found problem when saving add data, please inform to your admin.", e);
            }
            if (!diagnosaList.isEmpty()){

            }
            diagnosaRawat.setIdDetailCheckup(idDetailCheckup);
            diagnosaRawat.setIdDiagnosa(idDiagnosa);
            diagnosaRawat.setJenisDiagnosa(jenisDiagnosa);
            diagnosaRawat.setCreatedWho(userLogin);
            diagnosaRawat.setLastUpdate(updateTime);
            diagnosaRawat.setCreatedDate(updateTime);
            diagnosaRawat.setLastUpdateWho(userLogin);
            diagnosaRawat.setAction("C");
            diagnosaRawat.setFlag("Y");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            DiagnosaRawatBo diagnosaRawatBo = (DiagnosaRawatBo) ctx.getBean("diagnosaRawatBoProxy");

            diagnosaRawatBo.saveAdd(diagnosaRawat);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[DiagnosaRawatAction.saveDiagnosa] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        logger.info("[DiagnosaRawatAction.saveDiagnosa] end process >>>");

        return SUCCESS;
    }

    public List<DiagnosaRawat> listDiagnosa(String idDetailCheckup){

        logger.info("[TeamDokterAction.listDiagnosa] start process >>>");
        List<DiagnosaRawat> diagnosaRawatList = new ArrayList<>();
        DiagnosaRawat diagnosaRawat = new DiagnosaRawat();
        diagnosaRawat.setIdDetailCheckup(idDetailCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DiagnosaRawatBo diagnosaRawatBo = (DiagnosaRawatBo) ctx.getBean("diagnosaRawatBoProxy");

        try {
            diagnosaRawatList = diagnosaRawatBo.getByCriteria(diagnosaRawat);
        }catch (GeneralBOException e){
            logger.error("[TeamDokterAction.listDiagnosa] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[TeamDokterAction.listDiagnosa] start process >>>");
        return diagnosaRawatList;
    }
}