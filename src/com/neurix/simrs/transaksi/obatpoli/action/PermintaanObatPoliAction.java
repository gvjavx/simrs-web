package com.neurix.simrs.transaksi.obatpoli.action;

import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import com.neurix.simrs.transaksi.permintaanvendor.model.PermintaanVendor;
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
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Toshiba on 12/12/2019.
 */
public class PermintaanObatPoliAction extends BaseTransactionAction {
    private static transient Logger logger = Logger.getLogger(PermintaanObatPoliAction.class);
    private ObatPoliBo obatPoliBoProxy;
    private ObatBo obatBoProxy;
    private PermintaanObatPoli permintaanObatPoli;
    private ObatPoli obatPoli;
    private String idPermintaan;
    private String idApproval;

    public String getIdApproval() {
        return idApproval;
    }

    public void setIdApproval(String idApproval) {
        this.idApproval = idApproval;
    }

    public String getIdPermintaan() {
        return idPermintaan;
    }

    public void setIdPermintaan(String idPermintaan) {
        this.idPermintaan = idPermintaan;
    }

    @Override
    public String search() {
        logger.info("[PermintaanObatPoliAction.search] START >>>>>>>");

        PermintaanObatPoli permintaanObatPoli = getPermintaanObatPoli();
        permintaanObatPoli.setBranchId(CommonUtil.userBranchLogin());
        permintaanObatPoli.setTujuanPelayanan(CommonUtil.userPelayananIdLogin());
        boolean isPoli = false;

        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList<>();

        try {
            permintaanObatPoliList = obatPoliBoProxy.getSearchPermintaanObatPoli(permintaanObatPoli, isPoli);
        } catch (HibernateException e) {
            logger.error("[PermintaanObatPoliAction.search] ERROR when get data list obat, ", e);
            addActionError("[PermintaanObatPoliAction.search] ERROR when get data list obat, " + e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", permintaanObatPoliList);

        logger.info("[PermintaanObatPoliAction.search] END <<<<<<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[PermintaanObatPoliAction.initForm] start process >>>");
        PermintaanObatPoli permintaanObatPoli = new PermintaanObatPoli();

        setPermintaanObatPoli(permintaanObatPoli);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PermintaanObatPoliAction.initForm] end process <<<");
        return "search";
    }

    public List<PermintaanObatPoli> listDetailPermintaan(String idPermintaan, boolean isPoli, String idTujuan, String flag) {

        logger.info("[PermintaanObatPoliAction.listDetailPermintaan] start process >>>");
        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList<>();
        PermintaanObatPoli permintaanObatPoli = new PermintaanObatPoli();
        permintaanObatPoli.setIdPermintaanObatPoli(idPermintaan);
        permintaanObatPoli.setTujuanPelayanan(idTujuan);
        permintaanObatPoli.setFlag(flag);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

        if (!"".equalsIgnoreCase(idPermintaan)) {
            try {
                permintaanObatPoliList = obatPoliBo.getDetailLitsPermintaan(permintaanObatPoli, isPoli);
            } catch (GeneralBOException e) {
                logger.error("[PermintaanObatPoliAction.listDetailPermintaan] Error when search data detail permintaan ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when search data detail permintaan, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[PermintaanObatPoliAction.listDetailPermintaan] start process >>>");
            return permintaanObatPoliList;

        } else {
            return null;
        }
    }

    public String saveKonfirmasiRequest(String request, String idPermintaan, boolean isPoli) throws JSONException{
        logger.info("[PermintaanObatPoliAction.saveKonfirmasiRequest] START process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String branchId = CommonUtil.userBranchLogin();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");


            PermintaanObatPoli obatPoli = new PermintaanObatPoli();
            obatPoli.setIdPermintaanObatPoli(idPermintaan);
            obatPoli.setLastUpdate(updateTime);
            obatPoli.setLastUpdateWho(userLogin);
            obatPoli.setBranchId(branchId);
            obatPoli.setTujuanPelayanan(CommonUtil.userPelayananIdLogin());
            obatPoli.setBranchId(CommonUtil.userBranchLogin());

            List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();

            JSONArray json = new JSONArray(request);

            TransaksiObatDetail transaksiObatDetail;
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                transaksiObatDetail = new TransaksiObatDetail();
                transaksiObatDetail.setIdObat(obj.getString("ID"));
                transaksiObatDetail.setQtyApprove(new BigInteger(obj.getString("Qty Approve")));
                transaksiObatDetails.add(transaksiObatDetail);
            }

            try {
                obatPoliBo.saveApproveRequest(obatPoli, transaksiObatDetails, isPoli);
            }catch (JSONException e){
                logger.error("[PermintaanResepAction.saveKonfirmasiRequest] Error when sabe resep obat", e);
            }

        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PermintaanObatPoliAction.saveKonfirmasiRequest] ERROR when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            return e.getMessage();
        }

        logger.info("[PermintaanObatPoliAction.saveKonfirmasiRequest] END process <<<");

        return SUCCESS;
    }

    public String saveKonfirmasiReture(String idPermintaan, boolean isPoli) {
        logger.info("[PermintaanObatPoliAction.saveKonfirmasiReture] START process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String branchId = CommonUtil.userBranchLogin();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");


            PermintaanObatPoli obatPoli = new PermintaanObatPoli();
            obatPoli.setIdPermintaanObatPoli(idPermintaan);
            obatPoli.setLastUpdate(updateTime);
            obatPoli.setLastUpdateWho(userLogin);
            obatPoli.setBranchId(branchId);
            obatPoli.setTujuanPelayanan(CommonUtil.userPelayananIdLogin());

            obatPoliBo.saveApproveReture(obatPoli, isPoli);

        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PermintaanObatPoliAction.saveKonfirmasiReture] ERROR when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            return e.getMessage();
        }

        logger.info("[PermintaanObatPoliAction.saveKonfirmasiRequest] END process <<<");

        return SUCCESS;
    }

    public String printPermintaanObat() {

        String idPermintaan = getIdPermintaan();

        boolean isPoli = false;

        PermintaanObatPoli permintaanObatPoli = new PermintaanObatPoli();
        permintaanObatPoli.setIdPermintaanObatPoli(idPermintaan);
        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList<>();

        try {
            permintaanObatPoliList = obatPoliBoProxy.getSearchPermintaanObatPoli(permintaanObatPoli, isPoli);
        } catch (HibernateException e) {
            logger.error("[PermintaanObatPoliAction.search] ERROR when get data list obat, ", e);
            addActionError("[PermintaanObatPoliAction.search] ERROR when get data list obat, " + e.getMessage());
        }

        if(!permintaanObatPoliList.isEmpty()){
            PermintaanObatPoli entity = permintaanObatPoliList.get(0);
            if(entity != null){

                reportParams.put("permintaanId", idPermintaan);
                reportParams.put("logo", CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_NMU);
                reportParams.put("namaPelayanan", entity.getNamaTujuanPelayanan());
                reportParams.put("dariPelayanan", entity.getNamaPelayanan());
            }
        }

        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ReportAction.printCard] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        return "print_permintaan_obat";
    }

    public String initApprovePermintaan(){
        logger.info("[PermintaanObatPoliAction.initApprovePermintaan] START process >>>");

        String id = getIdApproval();

        TransaksiObatDetail obatDetail = new TransaksiObatDetail();
        obatDetail.setIdApprovalObat(id);

        List<TransaksiObatDetail> obatDetails = new ArrayList<>();

        try {
            obatDetails = obatPoliBoProxy.getListTransObatDetail(obatDetail);
        } catch (HibernateException e){
            logger.error("[PermintaanObatPoliAction.search] ERROR when get data list obat, ", e);
            addActionError("[PermintaanObatPoliAction.search] ERROR when get data list obat, " + e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfObatDetail");
        session.setAttribute("listOfObatDetail", obatDetails);

        logger.info("[PermintaanObatPoliAction.initApprovePermintaan] END process <<<");
        return "init_approve";
    }

    public List<Obat> getListObatEntity(String idObat, String idTransObatDetail){
        logger.info("[PermintaanObatPoliAction.initApprovePermintaan] START process >>>");
        List<Obat> obatList = new ArrayList<>();

        Obat obat = new Obat();
        obat.setIdObat(idObat);
        obat.setIdTransaksiDetail(idTransObatDetail);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        try {
            obatList = obatBo.getByCriteria(obat);
        } catch (GeneralBOException e){
            logger.error("[PermintaanObatPoliAction.getListObatEntity] ERROR when get data list obat, ", e);
            addActionError("[PermintaanObatPoliAction.getListObatEntity] ERROR when get data list obat, " + e.getMessage());
        }

        List<Obat> obatSorted = new ArrayList<>();

        if (obatList.size() > 0){
            try {
                obatSorted = obatBo.sortedListObat(obatList);
            } catch (GeneralBOException e){
                logger.error("[PermintaanObatPoliAction.getListObatEntity] ERROR when get data list obat, ", e);
                addActionError("[PermintaanObatPoliAction.getListObatEntity] ERROR when get data list obat, " + e.getMessage());
            }
        }

        logger.info("[PermintaanObatPoliAction.initApprovePermintaan] END process <<<");
        return obatList;
    }

    public String saveApproveRequest(String idObat, String request) throws JSONException{
        logger.info("[PermintaanObatPoliAction.saveKonfirmasiRequest] START process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String branchId = CommonUtil.userBranchLogin();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");


            PermintaanObatPoli obatPoli = new PermintaanObatPoli();
            obatPoli.setIdPermintaanObatPoli(idPermintaan);
            obatPoli.setLastUpdate(updateTime);
            obatPoli.setLastUpdateWho(userLogin);
            obatPoli.setBranchId(branchId);
            obatPoli.setTujuanPelayanan(CommonUtil.userPelayananIdLogin());
            obatPoli.setBranchId(CommonUtil.userBranchLogin());

            List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();

            JSONArray json = new JSONArray(request);

            TransaksiObatDetail transaksiObatDetail;
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                if(!"".equalsIgnoreCase(obj.getString("Qty Approve"))){
                    transaksiObatDetail = new TransaksiObatDetail();
                    transaksiObatDetail.setIdObat(idObat);
                    transaksiObatDetail.setIdTransaksiObatDetail(obj.getString("Id Transaksi"));
                    transaksiObatDetail.setExpDate(Date.valueOf(obj.getString("Expired Date")));
                    transaksiObatDetail.setQtyApprove(new BigInteger(obj.getString("Qty Approve")));
                    transaksiObatDetail.setJenisSatuan(obj.getString("Jenis Satuan"));
                    transaksiObatDetails.add(transaksiObatDetail);
                }
            }

            Boolean isPoli = false;

            try {
                obatPoliBo.saveApproveRequest(obatPoli, transaksiObatDetails, isPoli);
            }catch (JSONException e){
                logger.error("[PermintaanResepAction.saveKonfirmasiRequest] Error when sabe resep obat", e);
            }

            logger.info("[PermintaanObatPoliAction.saveKonfirmasiRequest] LIST DATA >> "+transaksiObatDetails);

        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PermintaanObatPoliAction.saveKonfirmasiRequest] ERROR when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            return e.getMessage();
        }

        logger.info("[PermintaanObatPoliAction.saveKonfirmasiRequest] END process <<<");

        return SUCCESS;
    }

    public String saveVerifikasiObatPoli(String idObat, String idTrans, String request) throws JSONException{
        logger.info("[PermintaanObatPoliAction.saveKonfirmasiRequest] START process >>>");

        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String branchId = CommonUtil.userBranchLogin();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

            List<Obat> obats = new ArrayList<>();

            JSONArray json = new JSONArray(request);

            Obat obat;
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                if(!"".equalsIgnoreCase(obj.getString("Qty Approve"))){
                    obat = new Obat();
                    obat.setIdObat(idObat);
                    obat.setIdTransaksiDetail(idTrans);
                    obat.setExpiredDate(Date.valueOf(obj.getString("Expired Date")));
                    obat.setQtyApprove(new BigInteger(obj.getString("Qty Approve")));
                    obat.setJenisSatuan(obj.getString("Jenis Satuan"));
                    obat.setIdBarang(obj.getString("Id Obat"));
                    obat.setCreatedDate(updateTime);
                    obat.setCreatedWho(userLogin);
                    obat.setLastUpdate(updateTime);
                    obat.setLastUpdateWho(userLogin);
                    obats.add(obat);
                }
            }

            Boolean isPoli = false;

            try {
                obatPoliBo.saveVerifikasiObat(obats);
            }catch (GeneralBOException e){
                logger.error("[PermintaanResepAction.saveKonfirmasiRequest] Error when sabe resep obat", e);
            }

        } catch (JSONException e) {
            Long logId = null;
            logger.error("[PermintaanObatPoliAction.saveKonfirmasiRequest] ERROR when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            return e.getMessage();
        }

        logger.info("[PermintaanObatPoliAction.saveKonfirmasiRequest] END process <<<");
        return SUCCESS;
    }

    public void setObatPoliBoProxy(ObatPoliBo obatPoliBoProxy) {
        this.obatPoliBoProxy = obatPoliBoProxy;
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

    public void setObatBoProxy(ObatBo obatBoProxy) {
        this.obatBoProxy = obatBoProxy;
    }
}
