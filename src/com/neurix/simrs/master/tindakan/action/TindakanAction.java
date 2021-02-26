package com.neurix.simrs.master.tindakan.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import com.neurix.simrs.master.kategoritindakanina.bo.KategoriTindakanInaBo;
import com.neurix.simrs.master.kategoritindakanina.model.KategoriTindakanIna;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.master.tindakan.bo.HeaderTindakanBo;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.HeaderTindakan;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.CrudResponse;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TindakanAction extends BaseTransactionAction {
    protected static transient Logger logger = Logger.getLogger(TindakanAction.class);
    private Tindakan tindakan;
    private TindakanBo tindakanBoProxy;
    private KategoriTindakanBo kategoriTindakanBoProxy;
    private BranchBo branchBoProxy;
    private KategoriTindakanInaBo kategoriTindakanInaBoProxy;

    private List<KategoriTindakan> listOfComboKategoriTindakan = new ArrayList<KategoriTindakan>();
    private List<Branch> listOfComboBranches = new ArrayList<Branch>();
    private List<KategoriTindakanIna> listOfComboKategoriTindakanIna = new ArrayList<KategoriTindakanIna>();

    public List<KategoriTindakanIna> getListOfComboKategoriTindakanIna() {
        return listOfComboKategoriTindakanIna;
    }

    public void setListOfComboKategoriTindakanIna(List<KategoriTindakanIna> listOfComboKategoriTindakanIna) {
        this.listOfComboKategoriTindakanIna = listOfComboKategoriTindakanIna;
    }

    public KategoriTindakanInaBo getKategoriTindakanInaBoProxy() {
        return kategoriTindakanInaBoProxy;
    }

    public void setKategoriTindakanInaBoProxy(KategoriTindakanInaBo kategoriTindakanInaBoProxy) {
        this.kategoriTindakanInaBoProxy = kategoriTindakanInaBoProxy;
    }

    public List<Branch> getListOfComboBranches() {
        return listOfComboBranches;
    }

    public void setListOfComboBranches(List<Branch> listOfComboBranches) {
        this.listOfComboBranches = listOfComboBranches;
    }

    public List<KategoriTindakan> getListOfComboKategoriTindakan() {
        return listOfComboKategoriTindakan;
    }

    public void setListOfComboKategoriTindakan(List<KategoriTindakan> listOfComboKategoriTindakan) {
        this.listOfComboKategoriTindakan = listOfComboKategoriTindakan;
    }

    public void setKategoriTindakanBoProxy(KategoriTindakanBo kategoriTindakanBoProxy) {
        this.kategoriTindakanBoProxy = kategoriTindakanBoProxy;
    }

    public void setTindakanBoProxy(TindakanBo tindakanBoProxy) {
        this.tindakanBoProxy = tindakanBoProxy;
    }

    public Tindakan getTindakan() {
        return tindakan;
    }

    public void setTindakan(Tindakan tindakan) {
        this.tindakan = tindakan;
    }

    public BranchBo getBranchBoProxy() {
        return branchBoProxy;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public KategoriTindakanBo getKategoriTindakanBoProxy() {
        return kategoriTindakanBoProxy;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TindakanAction.logger = logger;
    }

    public TindakanBo getTindakanBoProxy() {
        return tindakanBoProxy;
    }

    public Tindakan initTindakan(String idTindakan){
        logger.info("[TindakanAction.initTindakan] start process >>>>>");
        List<Tindakan> tindakanList = new ArrayList<>();
        Tindakan tindakanRes = new Tindakan();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");
        Tindakan tindakan = new Tindakan();
        tindakan.setIdTindakan(idTindakan);
        tindakan.setFlag("Y");
        try {
            tindakanList = tindakanBo.getDataTindakan(tindakan);
        }catch (GeneralBOException e){
            logger.error(e.getMessage());
        }
        if(tindakanList.size() > 0){
            tindakanRes = tindakanList.get(0);
        }
        logger.info("[TindakanAction.initTindakan] end process >>>>>");
        return tindakanRes;
    }

    @Override
    public String search() {
        logger.info("[TindakanAction.search] start process >>>");
        Tindakan searchTindakan = getTindakan();
        List<Tindakan> listOfsearchTindakan = new ArrayList();

        try {
            listOfsearchTindakan = tindakanBoProxy.getDataTindakan(searchTindakan);
        } catch (GeneralBOException e) {
            logger.error("[TindakanAction.save] Error when searching alat by criteria, Found problem when searching data by criteria, please inform to your admin."+ e.getMessage());
        }

        String branchId = CommonUtil.userBranchLogin();
        Tindakan data = new Tindakan();
        if (branchId != null){
            data.setBranchUser(branchId);
        }else {
            data.setBranchUser("");
        }
        setTindakan(data);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchTindakan);
        logger.info("[TindakanAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[TindakanAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        Tindakan data = new Tindakan();
        if (branchId != null){
            data.setBranchUser(branchId);
            data.setBranchId(branchId);
        }else {
            data.setBranchUser("");
            data.setBranchId("");
        }
        setTindakan(data);
        session.removeAttribute("listOfResult");
        logger.info("[TindakanAction.initForm] end process >>>");

        return "search";
    }

    public String initComboKategori() {

        KategoriTindakan kategoriTindakan = new KategoriTindakan();
        kategoriTindakan.setFlag("Y");

        List<KategoriTindakan> listOfKategoriTidakans = new ArrayList<KategoriTindakan>();
        try {
            listOfKategoriTidakans = kategoriTindakanBoProxy.getDataByCriteria(kategoriTindakan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kategoriTindakanBoProxy.saveErrorMessage(e.getMessage(), "KategoriTindakanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TindakanAction.initComboKategori] Error when saving error,", e1);
            }
            logger.error("[TindakanAction.initComboKategori] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboKategoriTindakan.addAll(listOfKategoriTidakans);

        return "init_combo";
    }

    public List<KategoriTindakan> getComboKategoriTindakan() {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        List<KategoriTindakan> listOfKategoriTidakans = new ArrayList<>();
        KategoriTindakanBo kategoriTindakanBo = (KategoriTindakanBo) ctx.getBean("kategoriTindakanBoProxy");
        KategoriTindakan kategoriTindakan = new KategoriTindakan();
        kategoriTindakan.setFlag("Y");

        try {
            listOfKategoriTidakans = kategoriTindakanBo.getDataByCriteria(kategoriTindakan);
        } catch (GeneralBOException e) {
            logger.error("[TindakanAction.initComboKategori] Error when searching data by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
        }
        return listOfKategoriTidakans;
    }

    public List<HeaderTindakan> getComboTindakan() {
        List<HeaderTindakan> tindakanList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HeaderTindakanBo headerTindakanBo = (HeaderTindakanBo) ctx.getBean("headerTindakanBoProxy");
        HeaderTindakan headerTindakan = new HeaderTindakan();
        headerTindakan.setFlag("Y");

        try {
            tindakanList = headerTindakanBo.getByCriteria(headerTindakan);
        } catch (GeneralBOException e) {
            logger.error("[TindakanAction.initComboKategori] Error when searching data by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
        }
        return tindakanList;
    }

    public List<Branch> getComboBranch() {
        List<Branch> branchList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        Branch branch = new Branch();
        branch.setFlag("Y");
        branch.setNotLike(CommonConstant.BRANCH_KP);
        branch.setRoleName(CommonUtil.roleAsLogin());
        if(!"ADMIN KP".equalsIgnoreCase(CommonUtil.roleAsLogin())){
            branch.setBranchId(CommonUtil.userBranchLogin());
        }
        try {
            branchList = branchBo.getByCriteria(branch);
        } catch (GeneralBOException e) {
            logger.error("[TindakanAction.getComboBranch] Error when searching data by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException("[TindakanAction.getComboBranch] Error when searching data by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
        }
        return branchList;
    }

    public List<Pelayanan> getComboPelayanan(String branchId) {
        List<Pelayanan> branchList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");

        if(branchId != null && !"".equalsIgnoreCase(branchId)){
            try {
                branchList = pelayananBo.getJustPelayananByBranch(branchId);
            } catch (GeneralBOException e) {
                logger.error("[TindakanAction.initComboKategori] Error when searching data by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
            }
        }
        return branchList;
    }

    public String initComboKategoriIna() {

        KategoriTindakanIna kategoriTindakanIna = new KategoriTindakanIna();
        kategoriTindakanIna.setFlag("Y");

        List<KategoriTindakanIna> listOfKategoriIna = new ArrayList<KategoriTindakanIna>();
        try {
            listOfKategoriIna = kategoriTindakanInaBoProxy.getByCriteria(kategoriTindakanIna);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = kategoriTindakanInaBoProxy.saveErrorMessage(e.getMessage(), "KategoriTindakanInaBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TindakanAction.initComboKategoriTindakanIna] Error when saving error,", e1);
            }
            logger.error("[KategoriTindakanInaAction.initComboKategoriTindakanIna] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboKategoriTindakanIna.addAll(listOfKategoriIna);

        return "init_combo";
    }

    public String initComboBranch() {

        Branch braches = new Branch();
        braches.setFlag("Y");

        List<Branch> listOfBranches = new ArrayList<Branch>();
        try {
            listOfBranches = branchBoProxy.getByCriteria(braches);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = branchBoProxy.saveErrorMessage(e.getMessage(), "BranchBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[TindakanAction.initComboBranch] Error when saving error,", e1);
            }
            logger.error("[TindakanAction.initComboBranch] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboBranches.addAll(listOfBranches);

        return "init_combo";
    }

    public CrudResponse saveAdd(String data){
        logger.info("[TindakanAction.saveAdd] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");
        CrudResponse response = new CrudResponse();

        try {
            JSONArray json = new JSONArray(data);
            List<Tindakan> tindakanList = new ArrayList<>();
            if(json != null){
                for (int i = 0; i < json.length(); i++){
                    JSONObject object = json.getJSONObject(i);
                    Tindakan tindakan = new Tindakan();
                    tindakan.setIdKategoriTindakan(object.getString("id_kategori_tindakan"));
                    tindakan.setIdHeaderTindakan(object.getString("id_header_tindakan"));
                    tindakan.setIdPelayanan(object.getString("id_pelayanan"));
                    tindakan.setBranchId(object.getString("branch_id"));
                    tindakan.setTarif(new BigInteger(object.getString("tarif")));
                    tindakan.setTarifBpjs(new BigInteger(object.getString("tarif_bpjs")));
                    tindakan.setDiskon(new BigDecimal(object.getString("diskon")));
                    tindakan.setIsIna(object.getString("is_ina"));
                    tindakan.setIsElektif(object.getString("is_elektif"));
                    if(object.has("id_kelas")){
                        if("empty".equalsIgnoreCase(object.getString("id_kelas"))){
                            tindakan.setFlagIdKelasRuangan("N");
                        }else{
                            tindakan.setFlagIdKelasRuangan("Y");
                            tindakan.setIdKelasRuangan(object.getString("id_kelas"));
                        }
                    }
                    tindakan.setCreatedWho(userLogin);
                    tindakan.setLastUpdate(updateTime);
                    tindakan.setCreatedDate(updateTime);
                    tindakan.setLastUpdateWho(userLogin);
                    tindakan.setAction("C");
                    tindakan.setFlag("Y");
                    List<ImSimrsTindakanEntity> entityList = new ArrayList<>();
                    try {
                        entityList = tindakanBo.cekTindakan(object.getString("id_header_tindakan"), object.getString("id_pelayanan"), object.getString("id_kelas"));
                        response.setStatus("success");
                        response.setMsg("Berhasil...!");
                    }catch (HibernateException e){
                        response.setStatus("error");
                        response.setMsg("Kesalahan dalam mencari data tindakan...!");
                        return response;
                    }
                    if(entityList.size() > 0){
                        response.setStatus("error");
                        response.setMsg("Mohon Maaf Data "+object.getString("nama_header_tindakan")+" di "+object.getString("nama_pelayanan")+" sudah ada...!");
                        return response;
                    }else{
                        tindakanList.add(tindakan);
                        response.setStatus("success");
                        response.setMsg("Berhasil...!");
                    }
                }

                if(tindakanList.size() > 0 && "success".equalsIgnoreCase(response.getStatus())){
                    response = tindakanBo.saveAdd(tindakanList);
                }else{
                    response.setStatus("error");
                    response.setMsg("Mohon maaf data tindakan tidak ada...!");
                }
            }else{
                response.setStatus("error");
                response.setMsg("Mohon maaf data tindakan tidak ada...!");
            }
        }catch (JSONException e) {
            response.setStatus("error");
            response.setMsg("Mohon maaf tidak bisa dilanjutkan dikarenakan, "+e.getMessage());
        }
        logger.info("[tindakanAction.saveAdd] end process >>>");
        return response;
    }

    public CrudResponse saveEdit(String data){
        logger.info("[TindakanAction.saveAdd] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");
        CrudResponse response = new CrudResponse();

        try {
            JSONObject object = new JSONObject(data);
            if(object != null){
                Tindakan tindakan = new Tindakan();
                tindakan.setIdTindakan(object.getString("id_tindakan"));
                tindakan.setIdHeaderTindakan(object.getString("id_header_tindakan"));
                tindakan.setIdKategoriTindakan(object.getString("id_kategori_tindakan"));
                tindakan.setTarif(new BigInteger(object.getString("tarif")));
                tindakan.setTarifBpjs(new BigInteger(object.getString("tarif_bpjs")));
                tindakan.setDiskon(new BigDecimal(object.getString("diskon")));
                tindakan.setIsIna(object.getString("is_ina"));
                tindakan.setIsElektif(object.getString("is_elektif"));
                tindakan.setLastUpdate(updateTime);
                tindakan.setLastUpdateWho(userLogin);
                tindakan.setAction("U");
                tindakan.setFlag("Y");
                response = tindakanBo.saveEdit(tindakan);
            }else{
                response.setStatus("error");
                response.setMsg("Mohon maaf data tindakan tidak ada...!");
            }
        }catch (JSONException e) {
            response.setStatus("error");
            response.setMsg("Mohon maaf tidak bisa dilanjutkan dikarenakan, "+e.getMessage());
        }
        logger.info("[tindakanAction.saveAdd] end process >>>");
        return response;
    }

    public CrudResponse saveDelete(String idTindakan){
        logger.info("[TindakanAction.saveDelete] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");
        CrudResponse response = new CrudResponse();
        try {
            Tindakan tindakan = new Tindakan();
            tindakan.setIdTindakan(idTindakan);
            tindakan.setLastUpdate(updateTime);
            tindakan.setLastUpdateWho(userLogin);
            tindakan.setAction("D");
            tindakan.setFlag("N");
            response = tindakanBo.saveEdit(tindakan);
        }catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("Mohon maaf tidak bisa dilanjutkan dikarenakan, "+e.getMessage());
        }
        logger.info("[tindakanAction.saveAdd] end process >>>");
        return response;
    }

    public ImSimrsTindakanEntity getDataTindakanById(String id){

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");
        return tindakanBo.getEntityTindakanById(id);
    }

    public List<Tindakan> getComboTindakanApotek(String idTindakan) {
        List<Tindakan> branchList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");
        try {
            branchList = tindakanBo.getTindakanApotek(CommonUtil.userBranchLogin(), CommonUtil.userPelayananIdLogin(), idTindakan);
        } catch (GeneralBOException e) {
            logger.error("[TindakanAction.initComboKategori] Error when searching data by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
        }
        return branchList;
    }

    public List<Pelayanan> getComboJustPelayanan(String branchId) {
        List<Pelayanan> branchList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");

        if(branchId != null && !"".equalsIgnoreCase(branchId)){
            try {
                branchList = pelayananBo.getJustPelayananOnly(branchId);
            } catch (GeneralBOException e) {
                logger.error("[TindakanAction.initComboKategori] Error when searching data by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
            }
        }
        return branchList;
    }

    public List<Tindakan> getComboAmbulance() {
        List<Tindakan> branchList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TindakanBo tindakanBo = (TindakanBo) ctx.getBean("tindakanBoProxy");
        String branchId = CommonUtil.userBranchLogin();

        if(branchId != null && !"".equalsIgnoreCase(branchId)){
            try {
                branchList = tindakanBo.getComboAmbulance(branchId);
            } catch (GeneralBOException e) {
                logger.error("[TindakanAction.initComboKategori] Error when searching data by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
            }
        }
        return branchList;
    }
}