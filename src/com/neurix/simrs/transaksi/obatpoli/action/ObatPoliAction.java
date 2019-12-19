package com.neurix.simrs.transaksi.obatpoli.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
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
    private PermintaanObatPoli permintaanObatPoli;

    private List<ObatPoli> listOfObatPoli = new ArrayList<>();

    public List<ObatPoli> getListOfObatPoli() {
        return listOfObatPoli;
    }

    public void setListOfObatPoli(List<ObatPoli> listOfObatPoli) {
        this.listOfObatPoli = listOfObatPoli;
    }

    public PermintaanObatPoli getPermintaanObatPoli() {
        return permintaanObatPoli;
    }

    public void setPermintaanObatPoli(PermintaanObatPoli permintaanObatPoli) {
        this.permintaanObatPoli = permintaanObatPoli;
    }

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
        PermintaanObatPoli permintaanObatPoli = new PermintaanObatPoli();

        if(CommonConstant.ROLE_ADMIN_POLI.equalsIgnoreCase(CommonUtil.roleAsLogin())){
            permintaanObatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        }

        setPermintaanObatPoli(permintaanObatPoli);
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

    public String saveAddRequest(String request){
        logger.info("[TindakanRawatAction.saveAdd] start process >>>");
        try {
            String userLogin    = CommonUtil.userLogin();
            String userArea     = CommonUtil.userBranchLogin();
            String idPelayanan  = CommonUtil.userPelayananIdLogin();
            Timestamp updateTime= new Timestamp(Calendar.getInstance().getTimeInMillis());

            ObatPoli obatPoli = new ObatPoli();
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
            try {
                obatPoliBo.saveAddWithRequest(obatPoli, request);
            }catch (JSONException e){
                logger.error("[PermintaanResepAction.saveResepPasien] Error when sabe resep obat", e);
            }


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

    public String saveAddReture(String reture){
        logger.info("[TindakanRawatAction.saveAdd] start process >>>");
        try {
            String userLogin    = CommonUtil.userLogin();
            String userArea     = CommonUtil.userBranchLogin();
            String idPelayanan  = CommonUtil.userPelayananIdLogin();
            Timestamp updateTime= new Timestamp(Calendar.getInstance().getTimeInMillis());

            PermintaanObatPoli obatPoli = new PermintaanObatPoli();
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
            try {
                obatPoliBo.saveReture(obatPoli, reture);
            }catch (JSONException e){
                logger.error("[PermintaanResepAction.saveResepPasien] Error when sabe resep obat", e);
            }

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[TindakanRawatAction.saveTindakanRawat] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveKonfirmasiRequest(String idPermintaan){
        logger.info("[ObatPoliAction.saveKonfirmasiRequest] START process >>>");
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
            obatPoli.setBranchId(branchId);

            obatPoliBo.saveApproveRequest(obatPoli);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[ObatPoliAction.saveKonfirmasiRequest] ERROR when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            return e.getMessage();
        }
        logger.info("[ObatPoliAction.saveKonfirmasiRequest] END process <<<");

        return SUCCESS;
    }

    public String saveKonfirmasiDiterima(String idApproval, String idPermintaan, String idObat, BigInteger qty){
        logger.info("[ObatPoliAction.saveKonfirmasiDiterima] START process >>>");
        try {
            String userLogin        = CommonUtil.userLogin();
            Timestamp updateTime    = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String branchId         = CommonUtil.userBranchLogin();
            String idPelayanan      = CommonUtil.userPelayananIdLogin();
            ApplicationContext ctx  = ContextLoader.getCurrentWebApplicationContext();
            ObatPoliBo obatPoliBo   = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");


            PermintaanObatPoli obatPoli = new PermintaanObatPoli();
            obatPoli.setIdApprovalObat(idApproval);
            obatPoli.setIdPermintaanObatPoli(idPermintaan);
            obatPoli.setIdObat(idObat);
            obatPoli.setBranchId(branchId);
            obatPoli.setIdPelayanan(idPelayanan);
            obatPoli.setQty(qty);
            obatPoli.setLastUpdate(updateTime);
            obatPoli.setLastUpdateWho(userLogin);
            obatPoli.setAction("U");

            obatPoliBo.saveApproveDiterima(obatPoli);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[ObatPoliAction.saveKonfirmasiDiterima] ERROR when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            return e.getMessage();
        }
        logger.info("[ObatPoliAction.saveKonfirmasiDiterima] END process <<<");

        return SUCCESS;
    }

    public String saveKonfirmasiReture(String idPermintaan){
        logger.info("[TindakanRawatAction.saveKonfirmasiReture] START process >>>");
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
            obatPoli.setBranchId(branchId);

            obatPoliBo.saveApproveReture(obatPoli);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[TindakanRawatAction.saveKonfirmasiReture] ERROR when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            return e.getMessage();
        }
        logger.info("[TindakanRawatAction.saveKonfirmasiReture] END process <<<");

        return SUCCESS;
    }

    public String searchStok() {
        logger.info("[ObatPoliAction.searchStok] start process >>>");

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

        logger.info("[ObatPoliAction.searchStok] end process <<<");
        return "search";
    }

    public String searchPermintaanObatPoli() {
        logger.info("[ObatPoliAction.searchRequest] start process >>>");

        PermintaanObatPoli permintaanObatPoli = getPermintaanObatPoli();
        permintaanObatPoli.setBranchId(CommonUtil.userBranchLogin());
        permintaanObatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList();

        try {
            permintaanObatPoliList = obatPoliBoProxy.getSearchPermintaanObatPoli(permintaanObatPoli);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[ObatPoliAction.searchRequest] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", permintaanObatPoliList);

        logger.info("[ObatPoliAction.searchRequest] end process <<<");
        return "search";
    }

    public String searchPermintaanObatGudang() {
        logger.info("[ObatPoliAction.searchRequest] start process >>>");

        PermintaanObatPoli permintaanObatPoli = getPermintaanObatPoli();
        permintaanObatPoli.setBranchId(CommonUtil.userBranchLogin());
        permintaanObatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList();

        try {
            permintaanObatPoliList = obatPoliBoProxy.getSearchPermintaanObatPoli(permintaanObatPoli);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[ObatPoliAction.searchRequest] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", permintaanObatPoliList);

        logger.info("[ObatPoliAction.searchRequest] end process <<<");
        return "search";
    }

    public String getListObatPoli(){

        logger.info("[ObatPoliAction.getListObatPoli] start process >>>");

        List<ObatPoli> obatPoliList = new ArrayList<>();
        ObatPoli obatPoli = new ObatPoli();
        obatPoli.setBranchId(CommonUtil.userBranchLogin());
        obatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());

        try {
            obatPoliList = obatPoliBoProxy.getObatPoliByCriteria(obatPoli);
        }catch (GeneralBOException e){
            logger.error("[ObatPoliAction.getListObatPoli] Error when get poli obat ," + "Found problem when saving add data, please inform to your admin.", e);
        }

        listOfObatPoli.addAll(obatPoliList);
        logger.info("[ObatPoliAction.getListObatPoli] end process <<<");
        return SUCCESS;

    }

    public List<ObatPoli> getStokObatPoli(String idObat){

        logger.info("[ObatPoliAction.getStokObat] start process >>>");
        List<ObatPoli> obatPoliList = new ArrayList<>();

        String branchId = CommonUtil.userBranchLogin();
        ObatPoli obatpoli = new ObatPoli();
        obatpoli.setIdObat(idObat);
        obatpoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        obatpoli.setBranchId(branchId);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

        try {
            obatPoliList = obatPoliBo.getObatPoliByCriteria(obatpoli);
        }catch (GeneralBOException e){
            logger.error("[ObatPoliAction.getStokObat] Error when get data obat poli ," + "Found problem when searching data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[ObatPoliAction.getStokObat] end process >>>");
        return obatPoliList;

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
