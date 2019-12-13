package com.neurix.simrs.transaksi.obatpoli.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Toshiba on 12/12/2019.
 */
public class ObatPoliAction extends BaseMasterAction {
    private static transient Logger logger = Logger.getLogger(ObatPoliAction.class);
    private ObatPoli obatPoli;
    private ObatPoliBo obatPoliBoProxy;

    public ObatPoli getObatPoli() {
        return obatPoli;
    }

    public void setObatPoli(ObatPoli obatPoli) {
        this.obatPoli = obatPoli;
    }

    public void setObatPoliBoProxy(ObatPoliBo obatPoliBoProxy) {
        this.obatPoliBoProxy = obatPoliBoProxy;
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
        logger.info("[ObatPoliAction.search] start process >>>");

        ObatPoli obatPoli = getObatPoli();
        List<ObatPoli> listObatPoli = new ArrayList();
        obatPoli.setBranchId(CommonUtil.userBranchLogin());
        obatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());

        try {
            listObatPoli = obatPoliBoProxy.getObatPoliByCriteria(obatPoli);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[ObatPoliAction.search] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listObatPoli);

        logger.info("[ObatPoliAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {

        logger.info("[ObatPoliAction.initForm] start process >>>");
        ObatPoli obatPoli = new ObatPoli();
        setObatPoli(obatPoli);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[ObatPoliAction.initForm] end process <<<");
        return "search";

    }

    public String saveAdd(String idObat){
        logger.info("[TindakanRawatAction.saveAdd] start process >>>");
        try {
            String userLogin    = CommonUtil.userLogin();
            String userArea     = CommonUtil.userBranchLogin();
            String idPelayanan  = CommonUtil.userPelayananIdLogin();
            Timestamp updateTime= new Timestamp(Calendar.getInstance().getTimeInMillis());

            ObatPoli obatPoli = new ObatPoli();
            obatPoli.setIdObat(idObat);
            obatPoli.setIdPelayanan(idPelayanan);
            obatPoli.setBranchId(userArea);

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

            obatPoli.setCreatedWho(userLogin);
            obatPoli.setLastUpdate(updateTime);
            obatPoli.setCreatedDate(updateTime);
            obatPoli.setLastUpdateWho(userLogin);
            obatPoli.setAction("C");
            obatPoli.setFlag("Y");

            ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

            obatPoliBo.saveAdd(obatPoli);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[TindakanRawatAction.saveTindakanRawat] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveAddRequest(String idObat, BigInteger qty){
        logger.info("[TindakanRawatAction.saveAdd] start process >>>");
        try {
            String userLogin    = CommonUtil.userLogin();
            String userArea     = CommonUtil.userBranchLogin();
            String idPelayanan  = CommonUtil.userPelayananIdLogin();
            Timestamp updateTime= new Timestamp(Calendar.getInstance().getTimeInMillis());

            ObatPoli obatPoli = new ObatPoli();
            obatPoli.setIdObat(idObat);
            obatPoli.setQty(qty);
            obatPoli.setIdPelayanan(idPelayanan);
            obatPoli.setBranchId(userArea);

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

            obatPoli.setCreatedWho(userLogin);
            obatPoli.setLastUpdate(updateTime);
            obatPoli.setCreatedDate(updateTime);
            obatPoli.setLastUpdateWho(userLogin);
            obatPoli.setAction("C");
            obatPoli.setFlag("Y");

            ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

            obatPoliBo.saveAddWithRequest(obatPoli);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[TindakanRawatAction.saveTindakanRawat] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveRequest(String idObat, BigInteger qty){
        logger.info("[TindakanRawatAction.saveAdd] start process >>>");
        try {
            String userLogin    = CommonUtil.userLogin();
            String userArea     = CommonUtil.userBranchLogin();
            String idPelayanan  = CommonUtil.userPelayananIdLogin();
            Timestamp updateTime= new Timestamp(Calendar.getInstance().getTimeInMillis());

            PermintaanObatPoli obatPoli = new PermintaanObatPoli();
            obatPoli.setIdObat(idObat);
            obatPoli.setQty(qty);
            obatPoli.setIdPelayanan(idPelayanan);
            obatPoli.setBranchId(userArea);

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

            obatPoli.setCreatedWho(userLogin);
            obatPoli.setLastUpdate(updateTime);
            obatPoli.setCreatedDate(updateTime);
            obatPoli.setLastUpdateWho(userLogin);
            obatPoli.setAction("C");
            obatPoli.setFlag("Y");

            ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

            obatPoliBo.saveRequest(obatPoli);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[TindakanRawatAction.saveTindakanRawat] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveReture(String idObat, BigInteger qty){
        logger.info("[TindakanRawatAction.saveAdd] start process >>>");
        try {
            String userLogin    = CommonUtil.userLogin();
            String userArea     = CommonUtil.userBranchLogin();
            String idPelayanan  = CommonUtil.userPelayananIdLogin();
            Timestamp updateTime= new Timestamp(Calendar.getInstance().getTimeInMillis());

            PermintaanObatPoli obatPoli = new PermintaanObatPoli();
            obatPoli.setIdObat(idObat);
            obatPoli.setQty(qty);
            obatPoli.setIdPelayanan(idPelayanan);
            obatPoli.setBranchId(userArea);

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

            obatPoli.setCreatedWho(userLogin);
            obatPoli.setLastUpdate(updateTime);
            obatPoli.setCreatedDate(updateTime);
            obatPoli.setLastUpdateWho(userLogin);
            obatPoli.setAction("C");
            obatPoli.setFlag("Y");

            ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

            obatPoliBo.saveReture(obatPoli);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[TindakanRawatAction.saveTindakanRawat] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveKonfirmasiRequest(String idPermintaan){
        logger.info("[TindakanRawatAction.saveKonfirmasiRequest] START process >>>");
        try {
            String userLogin        = CommonUtil.userLogin();
            Timestamp updateTime    = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String branchId         = CommonUtil.userBranchLogin();
            ApplicationContext ctx  = ContextLoader.getCurrentWebApplicationContext();
            ObatPoliBo obatPoliBo   = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");


            PermintaanObatPoli obatPoli = new PermintaanObatPoli();
            obatPoli.setIdPermintaanObatPoli(idPermintaan);
            obatPoli.setCreatedWho(userLogin);
            obatPoli.setLastUpdate(updateTime);
            obatPoli.setCreatedDate(updateTime);
            obatPoli.setLastUpdateWho(userLogin);

            obatPoliBo.saveApproveRequest(obatPoli);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[TindakanRawatAction.saveKonfirmasiRequest] ERROR when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            return e.getMessage();
        }
        logger.info("[TindakanRawatAction.saveKonfirmasiRequest] END process <<<");

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
