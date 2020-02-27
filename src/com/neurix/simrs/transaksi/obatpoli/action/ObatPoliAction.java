package com.neurix.simrs.transaksi.obatpoli.action;

import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import com.neurix.simrs.transaksi.permintaanvendor.model.CheckObatResponse;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatBatch;
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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Toshiba on 12/12/2019.
 */
public class ObatPoliAction extends BaseMasterAction {
    private static transient Logger logger = Logger.getLogger(ObatPoliAction.class);
    private ObatPoli obatPoli;
    private ObatPoliBo obatPoliBoProxy;
    private PermintaanObatPoli permintaanObatPoli;
    private String idPermintaan;

    private List<ObatPoli> listOfObatPoli = new ArrayList<>();
    private List<ObatPoli> listOfTujuanPelayanan = new ArrayList<>();

    public String getIdPermintaan() {
        return idPermintaan;
    }

    public void setIdPermintaan(String idPermintaan) {
        this.idPermintaan = idPermintaan;
    }

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

    public String saveAddReture(String reture, String idTujuan, String idPermintaan) throws JSONException {
        logger.info("[TindakanRawatAction.saveAdd] start process >>>");
        try {

            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            String idPelayanan = CommonUtil.userPelayananIdLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            PermintaanObatPoli obatPoli = new PermintaanObatPoli();

            String tujuanPelayanan = "";
            if ("GDG".equalsIgnoreCase(idPelayanan)){
                tujuanPelayanan = idTujuan+"_"+userArea;
            } else {
                tujuanPelayanan = idTujuan;
            }

            obatPoli.setIdPelayanan(idPelayanan);
            obatPoli.setBranchId(userArea);
            obatPoli.setTujuanPelayanan(tujuanPelayanan);
            obatPoli.setCreatedWho(userLogin);
            obatPoli.setLastUpdate(updateTime);
            obatPoli.setCreatedDate(updateTime);
            obatPoli.setLastUpdateWho(userLogin);
            obatPoli.setAction("C");
            obatPoli.setFlag("Y");
            obatPoli.setIdPermintaanObatPoli(idPermintaan);

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

            List<TransaksiObatDetail> obatDetailList = new ArrayList<>();
            if (reture != null && !"".equalsIgnoreCase(reture)) {
                JSONArray json = new JSONArray(reture);

                TransaksiObatDetail obatDetail;
                for (int i = 0; i < json.length(); i++) {
                    JSONObject obj = json.getJSONObject(i);
                    if(!"".equalsIgnoreCase(obj.getString("Qty Reture"))){
                        obatDetail = new TransaksiObatDetail();
                        obatDetail.setIdBarang(obj.getString("ID Barang"));
                        obatDetail.setIdObat(obj.getString("ID Obat"));
                        obatDetail.setIdPelayanan(idPelayanan);
                        obatDetail.setBranchId(userArea);
                        obatDetail.setJenisSatuan(obj.getString("Jenis Satuan"));
                        obatDetail.setQtyApprove(new BigInteger(obj.getString("Qty Reture")));
                        obatDetailList.add(obatDetail);
                    }
                }
            }

            try {
                obatPoliBo.saveReture(obatPoli, obatDetailList);
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
            ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");
            BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");

            PermintaanObatPoli obatPoli = new PermintaanObatPoli();
            obatPoli.setIdApprovalObat(idApproval);
            obatPoli.setIdPermintaanObatPoli(idPermintaan);
            obatPoli.setBranchId(branchId);
            obatPoli.setIdPelayanan(idPelayanan);
            obatPoli.setLastUpdate(updateTime);
            obatPoli.setLastUpdateWho(userLogin);
            obatPoli.setAction("U");


            List<Map> listOfObat = new ArrayList<>();
            try {
                List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();
                if (request != null && !"".equalsIgnoreCase(request)) {
                    JSONArray json = new JSONArray(request);
                    TransaksiObatDetail detail;
                    for (int i = 0; i < json.length(); i++) {

                        JSONObject obj = json.getJSONObject(i);

                        detail = new TransaksiObatDetail();
                        detail.setIdBarang(obj.getString("ID Barang"));
                        detail.setIdObat(obj.getString("ID Obat"));
                        detail.setIdTransaksiObatDetail(obj.getString("ID Transkasi"));
                        detail.setQtyApprove(new BigInteger(obj.getString("Qty Approve")));
                        detail.setJenisSatuan(obj.getString("Jenis Satuan"));

                        ImSimrsObatEntity obatEntity = obatBo.getObatByIdBarang(detail.getIdBarang());
                        if (obatEntity != null){

                            BigDecimal hargaRata = new BigDecimal(0);
                            if ("box".equalsIgnoreCase(detail.getJenisSatuan()))
                                hargaRata = obatEntity.getAverageHargaBox();
                            if ("lembar".equalsIgnoreCase(detail.getJenisSatuan()))
                                hargaRata = obatEntity.getAverageHargaLembar();
                            if ("biji".equalsIgnoreCase(detail.getJenisSatuan()))
                                hargaRata = obatEntity.getAverageHargaBiji();

                            Map mapPersedianGudang = new HashMap();
                            mapPersedianGudang.put("kd_barang", detail.getIdBarang());
                            mapPersedianGudang.put("nilai", hargaRata.multiply(new BigDecimal(detail.getQtyApprove())));
                            listOfObat.add(mapPersedianGudang);
                        }

                        transaksiObatDetails.add(detail);
                    }

                    // create jurnal
                    Map jurnalMap = new HashMap();
                    jurnalMap.put("persediaan_apotik", listOfObat);
                    jurnalMap.put("persediaan_gudang", listOfObat);

                    String catatan = "Pengiriman Barang Gudang ke Apotik";
                    try {
                        billingSystemBo.createJurnal("15", jurnalMap, branchId, catatan, "Y", "");
                    } catch (GeneralBOException e){
                        logger.error("[PermintaanResepAction.saveResepPasien] Error when sabe resep obat", e);
                        return e.getMessage();
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
//        ObatPoli obatPoli = new ObatPoli();
//        obatPoli.setBranchId(CommonUtil.userBranchLogin());
//        obatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());

        try {
            obatPoliList = obatPoliBoProxy.getListObatPoliGroup(CommonUtil.userPelayananIdLogin(), CommonUtil.userBranchLogin());
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
//        ObatPoli obatpoli = new ObatPoli();
//        obatpoli.setIdPelayanan(idPelayanan);
//        obatpoli.setBranchId(branchId);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

        try {
            obatPoliList = obatPoliBo.getListObatPoliGroup(idPelayanan, branchId);
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
        logger.info("[ObatPoliAction.checkStockLamaByIdPabrikan] START process <<<");
        CheckObatResponse checkObatResponse = new CheckObatResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

        String branchId = CommonUtil.userBranchLogin();

        idPabrik = "";
        if(!"".equalsIgnoreCase(idPabrik) && idPabrik != null){
            try {
                checkObatResponse = obatPoliBo.checkObatStockLama(idPabrik, branchId);
            } catch (HibernateException e){
                logger.error("[ObatPoliAction.checkStockLamaByIdPabrikan] Error when get poli obat ," + "Found problem when saving add data, please inform to your admin.", e);
            }
            logger.info("[ObatPoliAction.checkStockLamaByIdPabrikan] END process <<<");
            return checkObatResponse;
        }else{
            logger.info("[ObatPoliAction.checkStockLamaByIdPabrikan] END process <<<");
            return null;
        }
    }

    public CheckObatResponse checkTransaksiObat(String idObat){
        logger.info("[ObatPoliAction.checkTransaksiObat] START process <<<");
        CheckObatResponse checkObatResponse = new CheckObatResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

        List<PermintaanObatPoli> listObatEntity = new ArrayList<>();
        PermintaanObatPoli permintaanObatPoli = new PermintaanObatPoli();
        permintaanObatPoli.setIdObat(idObat);
        permintaanObatPoli.setIdPelayanan(CommonUtil.userPelayananIdLogin());
        permintaanObatPoli.setBranchId(CommonUtil.userBranchLogin());

        if(!"".equalsIgnoreCase(idObat) && idObat != null){

            try {
                listObatEntity = obatPoliBo.getCekListEntityObatPoli(permintaanObatPoli);
            } catch (HibernateException e){
                logger.error("[ObatPoliAction.checkTransaksiObat] Error when get transaksi obat ," + "Found problem when saving add data, please inform to your admin.", e);
            }

            if(listObatEntity.size() > 0){
                checkObatResponse.setStatus("error");
                checkObatResponse.setMessage("Transaksi dengan ID obat tersebut sudah ada..!");
            }else{
                checkObatResponse.setStatus("success");
                checkObatResponse.setMessage("Silahkan dilanjutkan..!");
            }

            logger.info("[ObatPoliAction.checkTransaksiObat] END process <<<");
            return checkObatResponse;
        }else{
            logger.info("[ObatPoliAction.checkTransaksiObat] END process <<<");
            return null;
        }
    }

    public List<TransaksiObatDetail> listDetailOldPermintaan(String idPermintaan) {
        logger.info("[PermintaanObatPoliAction.listDetailPermintaan] START process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

        List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();

        if (!"".equalsIgnoreCase(idPermintaan)) {
            try {
                transaksiObatDetails = obatPoliBo.getListObatTelahDiterima(idPermintaan);
            } catch (GeneralBOException e) {
                logger.error("[PermintaanObatPoliAction.listDetailPermintaan] Error when search data detail permintaan ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when search data detail permintaan, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[PermintaanObatPoliAction.listDetailPermintaan] END process >>>");
            return transaksiObatDetails;

        } else {
            return null;
        }
    }

    public String printReturePermintaanObat() {

        String idPermintaan = getIdPermintaan();

        boolean isPoli = false;

        PermintaanObatPoli permintaanObatPoli = new PermintaanObatPoli();
        permintaanObatPoli.setIdPermintaanObatPoli(idPermintaan);
        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList<>();

        try {
            permintaanObatPoliList = obatPoliBoProxy.getSearchPermintaanObatPoli(permintaanObatPoli, isPoli);
        } catch (HibernateException e) {
            logger.error("[PermintaanObatPoliAction.printReturePermintaanObat] ERROR when get data list obat, ", e);
            addActionError("[PermintaanObatPoliAction.printReturePermintaanObat] ERROR when get data list obat, " + e.getMessage());
        }

        if(!permintaanObatPoliList.isEmpty()){
            PermintaanObatPoli entity = permintaanObatPoliList.get(0);
            if(entity != null){

                String branch = CommonUtil.userBranchLogin();
                String logo = "";

                switch (branch){
                    case CommonConstant.BRANCH_RS01 :
                        logo = CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS01;
                        break;
                    case CommonConstant.BRANCH_RS02 :
                        logo = CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS02;
                        break;
                    case CommonConstant.BRANCH_RS03 :
                        logo = CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS03;
                        break;
                }

                reportParams.put("area", CommonUtil.userAreaName());
                reportParams.put("unit", CommonUtil.userBranchNameLogin());
                reportParams.put("permintaanId", idPermintaan);
                reportParams.put("logo", logo);
                reportParams.put("namaPelayanan", entity.getNamaPelayanan());
                reportParams.put("dariPelayanan", "Gudang "+CommonUtil.userBranchNameLogin());
            }
        }

        try {
            preDownload();
        } catch (SQLException e) {
            logger.error("[ObatPoliAction.printReturePermintaanObat] Error when print report ," + "[" + e + "] Found problem when downloading data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + e + "] Found problem when downloading data, please inform to your admin.");
            return "search";
        }

        return "print_reture_permintaan_obat";
    }

    public String updateDiterimaFlagBatch(BigInteger idBatch, String flag){

        logger.info("[ObatPoliAction.updateDiterimaFlagBatch] START process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

        if (!"".equalsIgnoreCase(idBatch.toString()) && !"".equalsIgnoreCase(flag)) {

            try {
                TransaksiObatBatch batch = new TransaksiObatBatch();
                batch.setId(idBatch);
                batch.setDiterimaFlag(flag);
                obatPoliBo.updateDiterimaFlagBatch(batch);
            } catch (GeneralBOException e) {
                logger.error("[ObatPoliAction.updateDiterimaFlagBatch] Error when search data detail permintaan batch," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when search data detail permintaan, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[ObatPoliAction.updateDiterimaFlagBatch] END process >>>");
            return SUCCESS;

        } else {
            return null;
        }
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
