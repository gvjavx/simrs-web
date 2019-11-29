package com.neurix.simrs.transaksi.tindakanrawat.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.tindakanrawat.bo.TindakanRawatBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TindakanRawatAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(TindakanRawatAction.class);
    private TindakanRawatBo tindakanRawatBoProxy;
    private TindakanRawat tindakanRawat;
    private TindakanBo tindakanBoProxy;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TindakanRawatAction.logger = logger;
    }

    public TindakanBo getTindakanBoProxy() {
        return tindakanBoProxy;
    }

    public void setTindakanBoProxy(TindakanBo tindakanBoProxy) {
        this.tindakanBoProxy = tindakanBoProxy;
    }

    public TindakanRawatBo getTindakanRawatBoProxy() {
        return tindakanRawatBoProxy;
    }

    public void setTindakanRawatBoProxy(TindakanRawatBo tindakanRawatBoProxy) {
        this.tindakanRawatBoProxy = tindakanRawatBoProxy;
    }

    public TindakanRawat getTindakanRawat() {
        return tindakanRawat;
    }

    public void setTindakanRawat(TindakanRawat tindakanRawat) {
        this.tindakanRawat = tindakanRawat;
    }

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
        return "init_view";
    }

    @Override
    public String save() {
        return "init_add";
    }

    @Override
    public String search() {
        return "search";
    }

    @Override
    public String initForm() {
        return "search";
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
        return "success_add";

    }

    public String getComboJenisPeriksaPasien(){
        return "init_add";
    }

    public String getComboPelayanan(){
        return "init_add";
    }

    public String saveTindakanRawat(String idDetailCheckup, String idTindakan, String idDokter, String idPerawat, BigInteger qty){
        logger.info("[TindakanRawatAction.saveTindakanRawat] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdDetailCheckup(idDetailCheckup);
            tindakanRawat.setIdTindakan(idTindakan);

            List<Tindakan> tindakanList = new ArrayList<>();
            Tindakan tindakan = new Tindakan();
            tindakan.setIdTindakan(idTindakan);
            Tindakan tindakanResult = new Tindakan();

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");

            try {
                tindakanList = tindakanBo.getByCriteria(tindakan);
            }catch (GeneralBOException e){
                logger.error("[TindakanRawatAction.saveTindakanRawat] Error when search tarif dan decs tindakan by id ," + "Found problem when saving add data, please inform to your admin.", e);
            }
            if (!tindakanList.isEmpty()){
                tindakanResult = tindakanList.get(0);
            }

            tindakanRawat.setNamaTindakan(tindakanResult.getTindakan());
            tindakanRawat.setIdDokter(idDokter);
            tindakanRawat.setIdPerawat(idPerawat);
            tindakanRawat.setQty(qty);
            tindakanRawat.setTarif(tindakanResult.getTarif());
            tindakanRawat.setCreatedWho(userLogin);
            tindakanRawat.setLastUpdate(updateTime);
            tindakanRawat.setCreatedDate(updateTime);
            tindakanRawat.setLastUpdateWho(userLogin);
            tindakanRawat.setAction("C");
            tindakanRawat.setFlag("Y");

            TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");

            tindakanRawatBo.saveAdd(tindakanRawat);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[TindakanRawatAction.saveTindakanRawat] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public List<TindakanRawat> listTindakanRawat(String idDetailCheckup){

        logger.info("[TindakanRawatAction.listTindakanRawat] start process >>>");
        List<TindakanRawat> tindakanRawatList = new ArrayList<>();
        TindakanRawat tindakanRawat = new TindakanRawat();
        tindakanRawat.setIdDetailCheckup(idDetailCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");

        if(!"".equalsIgnoreCase(idDetailCheckup)){
            try {
                tindakanRawatList = tindakanRawatBo.getByCriteria(tindakanRawat);
            }catch (GeneralBOException e){
                logger.error("[TindakanRawatAction.listTindakanRawat] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[TindakanRawatAction.saveTindakanRawat] start process >>>");
            return tindakanRawatList;

        }else{
            return null;
        }
    }

    public String editTindakanRawat(String idTindakanRawat, String idTindakan, String idDokter, String idPerawat, BigInteger qty){
        logger.info("[TindakanRawatAction.saveTindakanRawat] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            TindakanRawat tindakanRawat = new TindakanRawat();
            tindakanRawat.setIdTindakanRawat(idTindakanRawat);

            List<Tindakan> tindakanList = new ArrayList<>();
            Tindakan tindakan = new Tindakan();
            tindakan.setIdTindakan(idTindakan);
            Tindakan tindakanResult = new Tindakan();

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");

            try {
                tindakanList = tindakanBo.getByCriteria(tindakan);
            }catch (GeneralBOException e){
                logger.error("[TindakanRawatAction.saveTindakanRawat] Error when search tarif dan decs tindakan by id ," + "Found problem when saving add data, please inform to your admin.", e);
            }
            if (!tindakanList.isEmpty()){
                tindakanResult = tindakanList.get(0);
            }

            tindakanRawat.setNamaTindakan(tindakanResult.getTindakan());
            tindakanRawat.setIdDokter(idDokter);
            tindakanRawat.setIdPerawat(idPerawat);
            tindakanRawat.setQty(qty);
            tindakanRawat.setTarif(tindakanResult.getTarif());
            tindakanRawat.setLastUpdate(updateTime);
            tindakanRawat.setLastUpdateWho(userLogin);
            tindakanRawat.setAction("U");

            TindakanRawatBo tindakanRawatBo = (TindakanRawatBo) ctx.getBean("tindakanRawatBoProxy");

            tindakanRawatBo.saveEdit(tindakanRawat);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[TindakanRawatAction.saveTindakanRawat] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
}