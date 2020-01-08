package com.neurix.simrs.transaksi.obatpoli.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
    private List<ObatPoli> listOfTujuanPelayanan = new ArrayList<>();

    public List<ObatPoli> getListOfTujuanPelayanan() {
        return listOfTujuanPelayanan;
    }

    public void setListOfTujuanPelayanan(List<ObatPoli> listOfTujuanPelayanan) {
        this.listOfTujuanPelayanan = listOfTujuanPelayanan;
    }

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
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
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

        if (CommonConstant.ROLE_ADMIN_POLI.equalsIgnoreCase(CommonUtil.roleAsLogin())) {
            permintaanObatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        }

        if (CommonConstant.ROLE_ADMIN_APOTEK.equalsIgnoreCase(CommonUtil.roleAsLogin())) {
            permintaanObatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        }

        setPermintaanObatPoli(permintaanObatPoli);
        setObatPoli(obatPoli);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[ObatPoliAction.initForm] end process <<<");
        return "search";

    }

    public String saveAddRequest(String request, String idTujuan) {
        logger.info("[TindakanRawatAction.saveAdd] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            String idPelayanan = CommonUtil.userPelayananIdLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            PermintaanObatPoli obatPoli = new PermintaanObatPoli();
            obatPoli.setIdPelayanan(idPelayanan);
            obatPoli.setBranchId(userArea);
            obatPoli.setTujuanPelayanan(idTujuan);

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

            obatPoli.setCreatedWho(userLogin);
            obatPoli.setLastUpdate(updateTime);
            obatPoli.setCreatedDate(updateTime);
            obatPoli.setLastUpdateWho(userLogin);
            obatPoli.setAction("C");
            obatPoli.setFlag("Y");

            List<TransaksiObatDetail> obatDetailList = new ArrayList<>();
            TransaksiObatDetail obatDetail;

            ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");
            try {

                if (request != null && !"".equalsIgnoreCase(request)) {
                    JSONArray json = new JSONArray(request);
                    for (int i = 0; i < json.length(); i++) {
                        obatDetail = new TransaksiObatDetail();
                        JSONObject obj = json.getJSONObject(i);
                        obatDetail.setIdObat(obj.getString("ID"));
                        obatDetail.setQty(new BigInteger(obj.getString("Qty")));
                        obatDetail.setJenisSatuan(obj.getString("Jenis Satuan"));
                        obatDetailList.add(obatDetail);
                    }
                }

                obatPoliBo.saveRequest(obatPoli, obatDetailList);
            } catch (JSONException e) {
                logger.error("[PermintaanResepAction.saveResepPasien] Error when sabe resep obat", e);
            }


        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[TindakanRawatAction.saveTindakanRawat] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveAddReture(String reture, String idTujuan) throws JSONException {
        logger.info("[TindakanRawatAction.saveAdd] start process >>>");
        try {

            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            String idPelayanan = CommonUtil.userPelayananIdLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            PermintaanObatPoli obatPoli = new PermintaanObatPoli();
            obatPoli.setIdPelayanan(idPelayanan);
            obatPoli.setBranchId(userArea);
            obatPoli.setTujuanPelayanan(idTujuan);

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

            obatPoli.setCreatedWho(userLogin);
            obatPoli.setLastUpdate(updateTime);
            obatPoli.setCreatedDate(updateTime);
            obatPoli.setLastUpdateWho(userLogin);
            obatPoli.setAction("C");
            obatPoli.setFlag("Y");

            ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

            List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList<>();
            if (reture != null && !"".equalsIgnoreCase(reture)) {
                JSONArray json = new JSONArray(reture);

                PermintaanObatPoli permintaanObatPoli;
                for (int i = 0; i < json.length(); i++) {
                    JSONObject obj = json.getJSONObject(i);

                    permintaanObatPoli = new PermintaanObatPoli();
                    permintaanObatPoli.setIdObat(obj.getString("ID"));
                    permintaanObatPoli.setIdPelayanan(idPelayanan);
                    permintaanObatPoli.setBranchId(userArea);
                    permintaanObatPoli.setQty(new BigInteger(obj.getString("Qty")));
                    permintaanObatPoliList.add(permintaanObatPoli);
                }
            }

            try {
                obatPoliBo.saveReture(obatPoli, permintaanObatPoliList);
            } catch (JSONException e) {
                logger.error("[PermintaanResepAction.saveResepPasien] Error when sabe resep obat", e);
            }

        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[TindakanRawatAction.saveTindakanRawat] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveKonfirmasiDiterima(String idApproval, String idPermintaan, String request) {
        logger.info("[ObatPoliAction.saveKonfirmasiDiterima] START process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String branchId = CommonUtil.userBranchLogin();
            String idPelayanan = CommonUtil.userPelayananIdLogin();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");


            PermintaanObatPoli obatPoli = new PermintaanObatPoli();
            obatPoli.setIdApprovalObat(idApproval);
            obatPoli.setIdPermintaanObatPoli(idPermintaan);
            obatPoli.setBranchId(branchId);
            obatPoli.setIdPelayanan(idPelayanan);
            obatPoli.setLastUpdate(updateTime);
            obatPoli.setLastUpdateWho(userLogin);
            obatPoli.setAction("U");
            try {
                List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();
                if (request != null && !"".equalsIgnoreCase(request)) {
                    JSONArray json = new JSONArray(request);
                    TransaksiObatDetail detail;
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject obj = json.getJSONObject(i);
                        detail = new TransaksiObatDetail();
                        detail.setIdObat(obj.getString("ID"));
                        detail.setQtyApprove(new BigInteger(obj.getString("Approve")));
                        transaksiObatDetails.add(detail);
                    }
                }
                obatPoliBo.saveApproveDiterima(obatPoli, transaksiObatDetails);
            } catch (JSONException e) {
                logger.error("[PermintaanResepAction.saveResepPasien] Error when sabe resep obat", e);
            }

        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[ObatPoliAction.saveKonfirmasiDiterima] ERROR when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            return e.getMessage();
        }
        logger.info("[ObatPoliAction.saveKonfirmasiDiterima] END process <<<");

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
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
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
        permintaanObatPoli.setTujuanPelayanan("GDG");
        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList();
        boolean isPoli = true;

        try {
            permintaanObatPoliList = obatPoliBoProxy.getSearchPermintaanObatPoli(permintaanObatPoli, isPoli);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[ObatPoliAction.searchRequest] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
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
        permintaanObatPoli.setTujuanPelayanan("GDG");
        boolean isPoli = false;
        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList();

        try {
            permintaanObatPoliList = obatPoliBoProxy.getSearchPermintaanObatPoli(permintaanObatPoli, isPoli);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[ObatPoliAction.searchRequest] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", permintaanObatPoliList);

        logger.info("[ObatPoliAction.searchRequest] end process <<<");
        return "search";
    }

    public String searchPenerimaanObat() {
        logger.info("[ObatPoliAction.searchPenerimaanObat] start process >>>");

        PermintaanObatPoli permintaanObatPoli = getPermintaanObatPoli();
        permintaanObatPoli.setBranchId(CommonUtil.userBranchLogin());
        permintaanObatPoli.setTujuanPelayanan(CommonUtil.userPelayananIdLogin());
        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList();
        boolean isPoli = false;

        try {
            permintaanObatPoliList = obatPoliBoProxy.getSearchPermintaanObatPoli(permintaanObatPoli, isPoli);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[ObatPoliAction.searchPenerimaanObat] Error when searching pasien by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return ERROR;
        }

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", permintaanObatPoliList);

        logger.info("[ObatPoliAction.searchPenerimaanObat] end process <<<");
        return "search";
    }

    public String getListObatPoli() {

        logger.info("[ObatPoliAction.getListObatPoli] start process >>>");

        List<ObatPoli> obatPoliList = new ArrayList<>();
        ObatPoli obatPoli = new ObatPoli();
        obatPoli.setBranchId(CommonUtil.userBranchLogin());
        obatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());

        try {
            obatPoliList = obatPoliBoProxy.getObatPoliByCriteria(obatPoli);
        } catch (GeneralBOException e) {
            logger.error("[ObatPoliAction.getListObatPoli] Error when get poli obat ," + "Found problem when saving add data, please inform to your admin.", e);
        }

        listOfObatPoli.addAll(obatPoliList);
        logger.info("[ObatPoliAction.getListObatPoli] end process <<<");
        return SUCCESS;

    }

    public List<ObatPoli> getStokObatPoli(String idObat) {

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
        } catch (GeneralBOException e) {
            logger.error("[ObatPoliAction.getStokObat] Error when get data obat poli ," + "Found problem when searching data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[ObatPoliAction.getStokObat] end process >>>");
        return obatPoliList;

    }

    public List<ObatPoli> getSelectOptionObatByPoli(String idPelayanan) {

        logger.info("[ObatPoliAction.getStokObat] start process >>>");
        List<ObatPoli> obatPoliList = new ArrayList<>();

        String branchId = CommonUtil.userBranchLogin();
        ObatPoli obatpoli = new ObatPoli();
        obatpoli.setIdPelayanan(idPelayanan);
        obatpoli.setBranchId(branchId);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

        try {
            obatPoliList = obatPoliBo.getObatPoliByCriteria(obatpoli);
        } catch (GeneralBOException e) {
            logger.error("[ObatPoliAction.getStokObat] Error when get data obat poli ," + "Found problem when searching data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[ObatPoliAction.getStokObat] end process >>>");
        return obatPoliList;

    }

    public String getTujuanPelayanan() {

        logger.info("[ObatPoliAction.getTujuanPleyanan] start process >>>");

        List<ObatPoli> obatPoliList = new ArrayList<>();
        ObatPoli obatPoli = new ObatPoli();
        obatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());

        try {
            obatPoliList = obatPoliBoProxy.getTujuanPelayanan(obatPoli);
        } catch (GeneralBOException e) {
            logger.error("[ObatPoliAction.getTujuanPleyanan] Error when get poli obat ," + "Found problem when saving add data, please inform to your admin.", e);
        }

        listOfTujuanPelayanan.addAll(obatPoliList);
        logger.info("[ObatPoliAction.getTujuanPleyanan] end process <<<");
        return SUCCESS;

    }

    public CheckObatResponse checkStockLamaByIdPabrikan(String idPabrik){

        CheckObatResponse checkObatResponse = new CheckObatResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

        String branchId = CommonUtil.userBranchLogin();

        try {
            checkObatResponse = obatPoliBo.checkObatStockLama(idPabrik, branchId);
        } catch (HibernateException e){

        }

        return checkObatResponse;
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
