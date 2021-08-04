package com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.action;

import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.transaksi.billingSystem.bo.BillingSystemBo;
import com.neurix.akuntansi.transaksi.billingSystem.model.MappingDetail;
import com.neurix.akuntansi.transaksi.jurnal.model.Jurnal;
import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.bo.PendaftaranJasaRekananBo;
import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.model.PendaftaranJasa;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.JurnalResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.engine.Mapping;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PendaftaranJasaRekananAction extends BaseTransactionAction{
    public static transient Logger logger = Logger.getLogger(PendaftaranJasaRekananAction.class);

    private PendaftaranJasaRekananBo pendaftatanJasaRekananBoProxy;
    private PendaftaranJasa pendaftaranJasa;
    private CrudResponse crudResponse;
    private String jenisJabatan;

    public String getJenisJabatan() {
        return jenisJabatan;
    }

    public void setJenisJabatan(String jenisJabatan) {
        this.jenisJabatan = jenisJabatan;
    }

    public PendaftaranJasa getPendaftaranJasa() {
        return pendaftaranJasa;
    }

    public void setPendaftaranJasa(PendaftaranJasa pendaftaranJasa) {
        this.pendaftaranJasa = pendaftaranJasa;
    }

    public void setPendaftatanJasaRekananBoProxy(PendaftaranJasaRekananBo pendaftatanJasaRekananBoProxy) {
        this.pendaftatanJasaRekananBoProxy = pendaftatanJasaRekananBoProxy;
    }

    public void PendaftaranJasaRekananAction(){
        this.crudResponse = new CrudResponse();
    }

    @Override
    public String search() {
        logger.info("[PendaftaranJasaRekananAction.initForm] Start >>>");

        PendaftaranJasa dataPendaftaranJasa = getPendaftaranJasa();

        setJenisJabatan(tentukanJenisJabatanbyRole(CommonUtil.roleIdAsLogin()));

        List<PendaftaranJasa> pendaftaranJasaList = new ArrayList<>();
        try {
            pendaftaranJasaList = pendaftatanJasaRekananBoProxy.getSearchByCriteria(dataPendaftaranJasa);
        } catch (GeneralBOException e){
            logger.error("[PendaftaranJasaRekananAction.initForm] Error ", e);
            addActionError("[PendaftaranJasaRekananAction.initForm] Error "+ e.getCause());
            return "success";
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", pendaftaranJasaList);
        logger.info("[PendaftaranJasaRekananAction.initForm] End <<<");
        return "success";
    }

    private String tentukanJenisJabatanbyRole(String roleId){

        if (roleId.equalsIgnoreCase(CommonConstant.ROLE_ID_ADMIN_AKS)){
            return "keu";
        } else if (roleId.equalsIgnoreCase(CommonConstant.ROLE_ID_KASUB_KEU)){
            return "kasubkeu";
        } else if (roleId.equalsIgnoreCase(CommonConstant.ROLE_ID_KA_KEU)){
            return "kakeu";
        } else {
            return "unit";
        }
    }

    @Override
    public String initForm() {
        logger.info("[PendaftaranJasaRekananAction.initForm] Start >>>");

        setPendaftaranJasa(new PendaftaranJasa());
        setJenisJabatan(tentukanJenisJabatanbyRole(CommonUtil.roleIdAsLogin()));

        logger.info("[PendaftaranJasaRekananAction.initForm] End <<<");
        return "success";
    }

    public CrudResponse saveAdd(String stJSON) throws JSONException{
        logger.info("[PendaftaranJasaRekananAction.saveAdd] Start >>>");

        CrudResponse response = new CrudResponse();

        if (stJSON == null || "".equalsIgnoreCase(stJSON)){
            logger.error("[PendaftaranJasaRekananAction.saveAdd] JSON is NULL");
            response.hasError("JSON Is NULL");
            return response;
        }

        PendaftaranJasa pendaftaranJasa = new PendaftaranJasa();

        JSONObject obj = new JSONObject(stJSON);

        if (obj.has("nama") == true)
            pendaftaranJasa.setNamaJasa(obj.getString("nama"));

        if (obj.has("novendor") == true)
            pendaftaranJasa.setIdVendor(obj.getString("novendor"));

        if (obj.has("koderingjasa") == true)
            pendaftaranJasa.setKodeRekeningJasa(obj.getString("koderingjasa"));

        if (obj.has("koderingdivisi") == true)
            pendaftaranJasa.setKodeRekeningJasa(obj.getString("koderingdivisi"));

        pendaftaranJasa.setStatus("1");
        pendaftaranJasa.setFlag("Y");
        pendaftaranJasa.setAction("C");

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        pendaftaranJasa.setCreatedDate(time);
        pendaftaranJasa.setLastUpdate(time);
        pendaftaranJasa.setCreatedWho(userLogin);
        pendaftaranJasa.setCreatedWho(userLogin);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PendaftaranJasaRekananBo pendaftaranJasaRekananBo = (PendaftaranJasaRekananBo) ctx.getBean("pendaftaranJasaRekananBoProxy");

        try {
            pendaftaranJasaRekananBo.saveAdd(pendaftaranJasa);
        } catch (GeneralBOException e){
            logger.error("[PendaftaranJasaRekananAction.saveAdd] Error ", e);
            response.hasError(e.getCause().toString());
            return response;
        }

        logger.info("[PendaftaranJasaRekananAction.saveAdd] End <<<");
        return response;
    }

    public CrudResponse saveEdit(String stJSON) throws JSONException{
        logger.info("[PendaftaranJasaRekananAction.saveEdit] Start >>>");

        CrudResponse response = new CrudResponse();

        if (stJSON == null || "".equalsIgnoreCase(stJSON)){
            logger.error("[PendaftaranJasaRekananAction.saveEdit] JSON is NULL");
            response.hasError("JSON Is NULL");
            return response;
        }

        PendaftaranJasa pendaftaranJasa = new PendaftaranJasa();

        JSONObject obj = new JSONObject(stJSON);

        if (obj.has("id") == true)
            pendaftaranJasa.setId(obj.getString("id"));

        if (obj.has("nama") == true)
            pendaftaranJasa.setNamaJasa(obj.getString("nama"));

        if (obj.has("biaya") == true)
            pendaftaranJasa.setBiaya(new BigDecimal(obj.getString("biaya")));

        if (obj.has("status") == true)
            pendaftaranJasa.setStatus(obj.getString("status"));

        if (obj.has("flag") == true)
            pendaftaranJasa.setFlag(obj.getString("flag"));

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        pendaftaranJasa.setLastUpdate(time);
        pendaftaranJasa.setLastUpdateWho(userLogin);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PendaftaranJasaRekananBo pendaftaranJasaRekananBo = (PendaftaranJasaRekananBo) ctx.getBean("pendaftaranJasaRekananBoProxy");

        try {
            pendaftaranJasaRekananBo.saveEdit(pendaftaranJasa);
        } catch (GeneralBOException e){
            logger.error("[PendaftaranJasaRekananAction.saveEdit] Error ", e);
            response.hasError(e.getCause().toString());
            return response;
        }

        logger.info("[PendaftaranJasaRekananAction.saveEdit] End <<<");
        return response;
    }

    public CrudResponse saveApprove(String stJSON) throws JSONException{
        logger.info("[PendaftaranJasaRekananAction.saveApprove] Start >>>");

        CrudResponse response = new CrudResponse();

        if (stJSON == null || "".equalsIgnoreCase(stJSON) ){
            logger.error("[PendaftaranJasaRekananAction.saveApprove] JSON is NULL");
            response.hasError("JSON Is NULL");
            return response;
        }

        PendaftaranJasa pendaftaranJasa = new PendaftaranJasa();

        JSONObject obj = new JSONObject(stJSON);

        if (obj.has("id") == true)
            pendaftaranJasa.setId(obj.getString("id"));

        if (obj.has("biaya") == true)
            pendaftaranJasa.setBiaya(new BigDecimal(obj.getString("biaya")));

        if (obj.has("flagapprove") == true)
            pendaftaranJasa.setFlagApprove(obj.getString("flagapprove"));

        if (obj.has("alasanbatal") == true)
            pendaftaranJasa.setAlasanBatal(obj.getString("alasanbatal"));

        if (obj.has("jenisjabatan") == true){
            pendaftaranJasa.setJenisJabatan(obj.getString("jenisjabatan"));

            // jika kepala keuangan maka postin jurnal pembayaran jasa;
            if ("kakeu".equalsIgnoreCase(pendaftaranJasa.getJenisJabatan())){

                if (obj.has("jenisbayar") == false){
                    logger.error("[PendaftaranJasaRekananAction.saveApprove] Jenis Bayar is NULL");
                    response.hasError("Jenis Bayar is NULL\"");
                    return response;
                }

                if (obj.has("coakas") == false || "".equalsIgnoreCase(obj.getString("coakas"))){
                    logger.error("[PendaftaranJasaRekananAction.saveApprove] Missing Property of Account");
                    response.hasError("Missing Property of Account");
                    return response;
                }

                // jika bank validasi nomor bank
                if (!"kas".equalsIgnoreCase(obj.getString("jenisbayar"))){
                    if (obj.has("nomorbank") == false || "".equalsIgnoreCase(obj.getString("nomorbank"))){
                        logger.error("[PendaftaranJasaRekananAction.saveApprove] Missing Property Bank Number");
                        response.hasError("Missing Property Bank Number");
                        return response;
                    }

                    pendaftaranJasa.setNoBank(obj.getString("nomorbank"));
                }

                pendaftaranJasa.setCoaKas(obj.getString("coakas"));
            }

        }


        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        pendaftaranJasa.setLastUpdate(time);
        pendaftaranJasa.setLastUpdateWho(userLogin);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PendaftaranJasaRekananBo pendaftaranJasaRekananBo = (PendaftaranJasaRekananBo) ctx.getBean("pendaftaranJasaRekananBoProxy");

        JurnalResponse jurnalResponse = createJurnalPendaftaranJasa(pendaftaranJasa);
        // jika gagal membuat jurnal

        if ("error".equalsIgnoreCase(jurnalResponse.getStatus())){
            response.hasError(jurnalResponse.getMsg());
            return response;
        }

        pendaftaranJasa.setNoJurnal(jurnalResponse.getNoJurnal());

        try {
            pendaftaranJasaRekananBo.saveApprove(pendaftaranJasa);
        } catch (GeneralBOException e){
            logger.error("[PendaftaranJasaRekananAction.saveApprove] Error ", e);
            response.hasError(e.getCause().toString());
            return response;
        }

        logger.info("[PendaftaranJasaRekananAction.saveApprove] End <<<");
        return response;
    }

    private JurnalResponse createJurnalPendaftaranJasa(PendaftaranJasa bean){

        logger.info("[PendaftaranJasaRekananAction.createJurnalPendaftaranJasa] Start >>>");

        JurnalResponse response = new JurnalResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PendaftaranJasaRekananBo pendaftaranJasaRekananBo = (PendaftaranJasaRekananBo) ctx.getBean("pendaftaranJasaRekananBoProxy");
        BillingSystemBo billingSystemBo = (BillingSystemBo) ctx.getBean("billingSystemBoProxy");

        PendaftaranJasa dataJasa = pendaftaranJasaRekananBo.getSearchByCriteria(bean).get(0);

        // beban jasa;
        MappingDetail bebanJasa = new MappingDetail();
        bebanJasa.setCoa(dataJasa.getKodeRekeningJasa());
        bebanJasa.setDivisiId(dataJasa.getKoderingDivisi());
        bebanJasa.setNilai(bean.getBiaya());

        List<MappingDetail> listBebanJasa = new ArrayList<>();
        listBebanJasa.add(bebanJasa);
        // END;

        // PPH
        BigDecimal totalPPH = (bean.getBiaya().multiply(new BigDecimal(2))).divide(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_UP);

        MappingDetail pph = new MappingDetail();
        pph.setCoa(CommonConstant.REKENING_PPH21);
        pph.setNilai(totalPPH);
        pph.setMasterId(dataJasa.getIdVendor());

        List<MappingDetail> listPPH = new ArrayList<>();
        listPPH.add(pph);
        // END;

        // Kas
        MappingDetail kas = new MappingDetail();
        kas.setCoa(bean.getCoaKas());
        kas.setNilai(bean.getBiaya().subtract(totalPPH));

        List<MappingDetail> listKas = new ArrayList<>();
        listKas.add(kas);
        KodeRekening kodeRekeningCoaKas = pendaftaranJasaRekananBo.getKodeRekeningPropsByKodeRekening(bean.getCoaKas());

        String namaCoaKas = kodeRekeningCoaKas == null ? "" : kodeRekeningCoaKas.getNamaKodeRekening();
        // END


        String keterangan = "";
        if (bean.getNoBank() != null){
            keterangan = "Pembayaran JASA "+ dataJasa.getNamaJasa() + "dari Vendor "+ dataJasa.getNamaVendor() + " Dengan " + namaCoaKas + " Nomor : " + bean.getNoBank();
        } else {
            keterangan = "Pembayaran JASA "+ dataJasa.getNamaJasa() + "dari Vendor "+ dataJasa.getNamaVendor() + " Dengan " + namaCoaKas;
        }

        Map mapJurnal = new HashMap();
        mapJurnal.put("beban_jasa", listBebanJasa);
        mapJurnal.put("pph_21", listPPH);
        mapJurnal.put("kas", listKas);

        try {
            Jurnal jurnal = billingSystemBo.createJurnal(CommonConstant.TRANS_ID_PEMBAYARAN_JASA, mapJurnal, dataJasa.getBranchId(), keterangan, "Y");
            response.hasSuccess(jurnal.getNoJurnal(), jurnal.getSumber());
        } catch (GeneralBOException e){
            logger.error("[PendaftaranJasaRekananAction.createJurnalPendaftaranJasa] ERROR. ", e);
            response.hasError("[PendaftaranJasaRekananAction.createJurnalPendaftaranJasa] ERROR. "+ e.getCause());
            return response;
        }

        logger.info("[PendaftaranJasaRekananAction.createJurnalPendaftaranJasa] End <<<");
        return response;
    }
}
