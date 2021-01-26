package com.neurix.simrs.transaksi.obatpoli.action;

import com.neurix.akuntansi.master.kodeRekening.bo.KodeRekeningBo;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.transaksi.checkup.model.CheckResponse;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.model.MtSimrsPermintaanObatPoliEntity;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import com.neurix.simrs.transaksi.permintaanvendor.model.PermintaanVendor;
import com.neurix.simrs.transaksi.transaksiobat.bo.TransaksiObatBo;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsApprovalTransaksiObatEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.MtSimrsTransaksiObatDetailBatchEntity;
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
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Toshiba on 12/12/2019.
 */
public class PermintaanObatPoliAction extends BaseTransactionAction {
    private static transient Logger logger = Logger.getLogger(PermintaanObatPoliAction.class);
    private ObatPoliBo obatPoliBoProxy;
    private ObatBo obatBoProxy;
    private BranchBo branchBoProxy;
    private PermintaanObatPoli permintaanObatPoli;
    private ObatPoli obatPoli;
    private String idPermintaan;
    private String idApproval;

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

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

                reportParams.put("unit", CommonUtil.userBranchNameLogin());
                reportParams.put("area", CommonUtil.userAreaName());
                reportParams.put("permintaanId", idPermintaan);
                reportParams.put("logo", logo);
                reportParams.put("namaPelayanan", "Gudang "+CommonUtil.userBranchNameLogin());
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
        String idPermintaan = getIdPermintaan();

        TransaksiObatDetail obatDetail = new TransaksiObatDetail();
        obatDetail.setIdApprovalObat(id);

        List<TransaksiObatDetail> obatDetails = new ArrayList<>();

        try {
            obatDetails = obatPoliBoProxy.getListTransObatDetail(obatDetail);
        } catch (HibernateException e){
            logger.error("[PermintaanObatPoliAction.search] ERROR when get data list obat, ", e);
            addActionError("[PermintaanObatPoliAction.search] ERROR when get data list obat, " + e.getMessage());
        }

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
                setPermintaanObatPoli(entity);
            }
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

    public String saveApproveRequest(String idApprovalObat, String request) throws JSONException{
        logger.info("[PermintaanObatPoliAction.saveKonfirmasiRequest] START process >>>");

        CheckResponse response = new CheckResponse();

        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String branchId = CommonUtil.userBranchLogin();
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");
            TransaksiObatBo transaksiObatBo = (TransaksiObatBo) ctx.getBean("transaksiObatBoProxy");
            ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");
            BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
            PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
            BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");
            KodeRekeningBo kodeRekeningBo = (KodeRekeningBo) ctx.getBean("kodeRekeningBoProxy");

            PermintaanObatPoli obatPoli = new PermintaanObatPoli();
            obatPoli.setIdApprovalObat(idApprovalObat);
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
                    transaksiObatDetail.setIdObat(obj.getString("ID Obat"));
                    transaksiObatDetail.setIdTransaksiObatDetail(obj.getString("ID Transkasi"));
                    transaksiObatDetails.add(transaksiObatDetail);
                }
            }

            String branchTujuan = "";
            String branchAsal = "";
            String branchAsalName = "";
            String branchTujuanName = "";
            String rekeningId = "";
            String pelayananAsal = "";
            String pelayananTujuan = "";
            Boolean isPoli = false;
            boolean otherBranch = false;
            MtSimrsPermintaanObatPoliEntity permintaanObatPoliEntity = obatPoliBo.getPermintaanObatPolyByIdApproval(idApprovalObat);
            ImtSimrsApprovalTransaksiObatEntity approvalTransaksiObatEntity = obatPoliBo.getApprovalEntityById(idApprovalObat);
            if (permintaanObatPoliEntity != null && approvalTransaksiObatEntity != null){
                otherBranch     = !permintaanObatPoliEntity.getBranchId().equalsIgnoreCase(approvalTransaksiObatEntity.getBranchId());
                branchTujuan    = approvalTransaksiObatEntity.getBranchId();
                branchAsal      = permintaanObatPoliEntity.getBranchId();

                ImSimrsPelayananEntity pelayananAsalEntity = pelayananBo.getPelayananById(permintaanObatPoliEntity.getIdPelayanan());
                ImSimrsPelayananEntity pelayananTujuanEntity = pelayananBo.getPelayananById(permintaanObatPoliEntity.getTujuanPelayanan());

                Branch branch = branchBo.getBranchById(branchTujuan, "Y");
                if (branch != null){
                    branchTujuanName = branch.getBranchName();
                }

                Branch branchAsalData = branchBo.getBranchById(branchAsal, "Y");
                if (branchAsalData != null){
                    branchAsalName = branch.getBranchName();
                    rekeningId = kodeRekeningBo.getRekeningIdByKodeRekening(branchAsalData.getCoaRk());
                }

                if (pelayananAsal != null && pelayananTujuan != null){
                    pelayananAsal = pelayananAsalEntity.getNamaPelayanan();
                    pelayananTujuan = pelayananTujuanEntity.getNamaPelayanan();
                }
            }

            // jika other branch maka membuat jurnal
            List<Map> listOfObat = new ArrayList<>();
            List<Map> listOfObatRk = new ArrayList<>();
            if (otherBranch){

                TransaksiObatDetail obatDetail = new TransaksiObatDetail();
                obatDetail.setIdApprovalObat(idApprovalObat);
                obatDetail.setTipePermintaan("002");

                List<ImtSimrsTransaksiObatDetailEntity> listTransaksiObatDetail = transaksiObatBo.getListEntityTransObatDetail(obatDetail);
                if (listTransaksiObatDetail.size() > 0){

                    List<TransaksiObatDetail> batchEntities = transaksiObatBo.getListPermintaanBatch(idApprovalObat, "Y");
                    for (TransaksiObatDetail detail : batchEntities){
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

                            Map mapPersedianRK = new HashMap();
                            mapPersedianRK.put("kd_barang", detail.getIdBarang());
                            mapPersedianRK.put("nilai", hargaRata.multiply(new BigDecimal(detail.getQtyApprove())));
                            mapPersedianRK.put("rekening_id", rekeningId);
                            listOfObatRk.add(mapPersedianRK);

                        }
                    }
                }


                // create jurnal
                Map jurnalMap = new HashMap();
                jurnalMap.put("persediaan_gudang", listOfObat);
                jurnalMap.put("rk_tujuan", listOfObatRk);

                String catatan = "RK Pengiriman Barang dari "+pelayananTujuan+" ke "+pelayananAsal+" Unit " +branchAsalName+ " No. Permintaan " + permintaanObatPoliEntity.getIdPermintaanObatPoli();

                try {
                    billingSystemBo.createJurnal(CommonConstant.TRANSAKSI_ID_RK_PERSEDIAAN_PENGIRIM, jurnalMap, branchId, catatan, "Y");
                    obatPoliBo.saveApproveRequest(obatPoli, transaksiObatDetails, isPoli);
                    response.setStatus("success");
                    response.setMessage("Oke");
                } catch (GeneralBOException e){
                    logger.error("[PermintaanObatPoliAction.saveKonfirmasiRequest] Error when create jurnal obat", e);
                    response.setStatus("error");
                    response.setMessage("Found Error "+e.getMessage());
                }
            }


            try {
                obatPoliBo.saveApproveRequest(obatPoli, transaksiObatDetails, isPoli);
                response.setStatus("success");
                response.setMessage("Oke");
            } catch (GeneralBOException e){
                logger.error("[PermintaanObatPoliAction.saveKonfirmasiRequest] Error when save approve obat", e);
                response.setStatus("error");
                response.setMessage("Found Error "+e.getMessage());
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

    public List<Obat> listObatEntity(String idObat) {
        logger.info("[PermintaanObatPoliAction.listObatEntity] START process >>>");
        List<Obat> obatList = new ArrayList<>();

        Obat obat = new Obat();
        obat.setIdObat(idObat);
        obat.setBranchId(CommonUtil.userBranchLogin());
        obat.setFlag("Y");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        if(idObat != null && !"".equalsIgnoreCase(idObat)){
            try {
                obatList = obatBo.getEntityObatByCriteria(obat);
            } catch (GeneralBOException e) {
                logger.error("[PermintaanObatPoliAction.listObatEntity] ERROR when get data list obat, ", e);
                addActionError("[PermintaanObatPoliAction.listObatEntity] ERROR when get data list obat, " + e.getMessage());
            }
            logger.info("[PermintaanObatPoliAction.listObatEntity] END process <<<");
            return obatList;
        }else{
            return null;
        }
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
                    obat.setIdBarang(obj.getString("ID Barang"));
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

    public List<PermintaanObatPoli> listDetailObatRequest(String idPermintaan) {

        logger.info("[PermintaanObatPoliAction.listDetailObatRequest] START process >>>");
        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList<>();
        PermintaanObatPoli permintaanObatPoli = new PermintaanObatPoli();
        permintaanObatPoli.setIdPermintaanObatPoli(idPermintaan);
        permintaanObatPoli.setBranchId(CommonUtil.userBranchLogin());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

        if (!"".equalsIgnoreCase(idPermintaan)) {
            try {
                permintaanObatPoliList = obatPoliBo.getListObatDetailRequest(permintaanObatPoli);
            } catch (GeneralBOException e) {
                logger.error("[PermintaanObatPoliAction.listDetailObatRequest] Error when search data detail permintaan ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when search data detail permintaan, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[PermintaanObatPoliAction.listDetailObatRequest] END process >>>");
            return permintaanObatPoliList;

        } else {
            return null;
        }
    }

    // ================================== Approve Permintaan Unit =============================================//

    public String searchApproveUnit() {
        logger.info("[ApprovePermintaanUnit.searchApproveUnit] START >>>>>>>");

        PermintaanObatPoli permintaanObatPoli = getPermintaanObatPoli();
        permintaanObatPoli.setBranchId(CommonUtil.userBranchLogin());
        permintaanObatPoli.setTujuanPelayanan(CommonUtil.userPelayananIdLogin());
        permintaanObatPoli.setFlagReqPelayanan("Y");
        boolean isPoli = false;

        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList<>();

        try {
            permintaanObatPoliList = obatPoliBoProxy.getSearchPermintaanObatPoli(permintaanObatPoli, isPoli);
        } catch (HibernateException e) {
            logger.error("[ApprovePermintaanUnit.searchApproveUnit] ERROR when get data list obat, ", e);
            addActionError("[ApprovePermintaanUnit.searchApproveUnit] ERROR when get data list obat, " + e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", permintaanObatPoliList);

        logger.info("[ApprovePermintaanUnit.searchApproveUnit] END <<<<<<<");
        return "search";
    }

    public String initFormApproveUnit() {
        logger.info("[ApprovePermintaanUnit.initFormApproveUnit] start process >>>");
        PermintaanObatPoli permintaanObatPoli = new PermintaanObatPoli();

        setPermintaanObatPoli(permintaanObatPoli);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[ApprovePermintaanUnit.initFormApproveUnit] end process <<<");
        return "search";
    }

    public String initApprovePermintaanUnit(){
        logger.info("[ApprovePermintaanUnit.initApprovePermintaanUnit] START process >>>");

        String id = getIdApproval();
        String idPermintaan = getIdPermintaan();

        TransaksiObatDetail obatDetail = new TransaksiObatDetail();
        obatDetail.setIdApprovalObat(id);

        List<TransaksiObatDetail> obatDetails = new ArrayList<>();

        try {
            obatDetails = obatPoliBoProxy.getListTransObatDetail(obatDetail);
        } catch (HibernateException e){
            logger.error("[ApprovePermintaanUnit.search] ERROR when get data list obat, ", e);
            addActionError("[ApprovePermintaanUnit.search] ERROR when get data list obat, " + e.getMessage());
        }

        boolean isPoli = false;

        PermintaanObatPoli permintaanObatPoli = new PermintaanObatPoli();
        permintaanObatPoli.setIdPermintaanObatPoli(idPermintaan);
        permintaanObatPoli.setFlagReqPelayanan("Y");
        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList<>();

        try {
            permintaanObatPoliList = obatPoliBoProxy.getSearchPermintaanObatPoli(permintaanObatPoli, isPoli);
        } catch (HibernateException e) {
            logger.error("[ApprovePermintaanUnit.printReturePermintaanObat] ERROR when get data list obat, ", e);
            addActionError("[ApprovePermintaanUnit.printReturePermintaanObat] ERROR when get data list obat, " + e.getMessage());
        }

        if(!permintaanObatPoliList.isEmpty()){
            PermintaanObatPoli entity = permintaanObatPoliList.get(0);
            if(entity != null){
                setPermintaanObatPoli(entity);
            }
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfObatDetail");
        session.setAttribute("listOfObatDetail", obatDetails);

        logger.info("[ApprovePermintaanUnit.initApprovePermintaanUnit] END process <<<");
        return "init_approve";
    }

    public String printPermintaanObatApproveUnit() {

        String idPermintaan = getIdPermintaan();

        boolean isPoli = false;

        PermintaanObatPoli permintaanObatPoli = new PermintaanObatPoli();
        permintaanObatPoli.setIdPermintaanObatPoli(idPermintaan);
        permintaanObatPoli.setFlagReqPelayanan("Y");
        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList<>();

        try {
            permintaanObatPoliList = obatPoliBoProxy.getSearchPermintaanObatPoli(permintaanObatPoli, isPoli);
        } catch (HibernateException e) {
            logger.error("[ApprovePermintaanUnit.printPermintaanObatApproveUnit] ERROR when get data list obat, ", e);
            addActionError("[ApprovePermintaanUnit.printPermintaanObatApproveUnit] ERROR when get data list obat, " + e.getMessage());
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

                reportParams.put("unit", CommonUtil.userBranchNameLogin());
                reportParams.put("area", CommonUtil.userAreaName());
                reportParams.put("permintaanId", idPermintaan);
                reportParams.put("logo", logo);
                reportParams.put("namaPelayanan", "Gudang "+CommonUtil.userBranchNameLogin());
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
