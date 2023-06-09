package com.neurix.simrs.master.dokter.action;

import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.dokter.model.DokterPelayanan;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DokterAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(DokterAction.class);
    private Dokter dokter;
    private DokterBo dokterBoProxy;
    private PelayananBo pelayananBoProxy;
    private PositionBo positionBoProxy;

    private List<Pelayanan> listOfComboPelayanan = new ArrayList<Pelayanan>();
    private List<Position> listOfComboPositions = new ArrayList<Position>();
    public Dokter getDokter() {
        return dokter;
    }

    public List<Position> getListOfComboPositions() {
        return listOfComboPositions;
    }

    public void setListOfComboPositions(List<Position> listOfComboPositions) {
        this.listOfComboPositions = listOfComboPositions;
    }

    public PositionBo getPositionBoProxy() {
        return positionBoProxy;
    }

    public void setPositionBoProxy(PositionBo positionBoProxy) {
        this.positionBoProxy = positionBoProxy;
    }

    public void setDokter(Dokter dokter) {
        this.dokter = dokter;
    }

    public void setDokterBoProxy(DokterBo dokterBoProxy) {
        this.dokterBoProxy = dokterBoProxy;
    }

    public void setPelayananBoProxy(PelayananBo pelayananBoProxy) {
        this.pelayananBoProxy = pelayananBoProxy;
    }

    public List<Pelayanan> getListOfComboPelayanan() {
        return listOfComboPelayanan;
    }

    public void setListOfComboPelayanan(List<Pelayanan> listOfComboPelayanan) {
        this.listOfComboPelayanan = listOfComboPelayanan;
    }

    public Dokter init(String kode, String flag){
        logger.info("[DokterAction.init] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<Dokter> listOfResult = (List<Dokter>) session.getAttribute("listOfResultDokter");

        if(kode != null && !"".equalsIgnoreCase(kode)){
            if(listOfResult != null){
                for (Dokter dokter: listOfResult) {
                    if(kode.equalsIgnoreCase(dokter.getIdDokter()) && flag.equalsIgnoreCase(dokter.getFlag())){
                        String kodering = dokter.getKodering();
//                        String[] arrOfStr = kodering.split("\\.");
//                        String kode1 = arrOfStr[1];String kode2 = arrOfStr[2];String kode3 = arrOfStr[3];
//                        String koder = kode1+"."+kode2+"."+kode3;
                        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                        Position position = new Position();
                        PositionBo positionBo = (PositionBo) context.getBean("positionBoProxy");
                        position.setKodering(kodering);
                        position.setFlag("Y");
                        String positionId = "";
                        List<Position> positions = positionBo.getByCriteria(position);
                        if(positions.size()> 0 ){
                           positionId = positions.get(0).getPositionId();
                        }
                        dokter.setPositionId(positionId);
                        setDokter(dokter);
                        break;
                    }
                }
            } else {
                setDokter(new Dokter());
            }

            logger.info("[DokterAction.init] end process >>>");
        }
        return getDokter();
    }

    @Override
    public String add() {
        logger.info("[DokterAction.add] start process >>>");
        Dokter addDokter = new Dokter();
        setDokter(addDokter);
        setAddOrEdit(true);
        setAdd(true);

//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[DokterAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[DokterAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        Dokter editDokter = new Dokter();

        if(itemFlag != null){
            try {
                editDokter = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = dokterBoProxy.saveErrorMessage(e.getMessage(), "DokterBO.getDokterByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[DokterAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[DokterAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editDokter != null) {
                setDokter(editDokter);
            } else {
                editDokter.setFlag(itemFlag);
                //editPayrollSkalaGaji.getSkalaGajiId(itemId);
                setDokter(editDokter);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollSkalaGaji.getSkalaGajiId(itemId);
            editDokter.setFlag(getFlag());
            setDokter(editDokter);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[DokterAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[DokterAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        Dokter deleteDokter = new Dokter();

        if (itemFlag != null ) {

            try {
                deleteDokter = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = dokterBoProxy.saveErrorMessage(e.getMessage(), "DokterBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[DokterAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[DokterAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteDokter != null) {
                setDokter(deleteDokter);

            } else {
                //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
                deleteDokter.setFlag(itemFlag);
                setDokter(deleteDokter);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteDokter.setFlag(itemFlag);
            setDokter(deleteDokter);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[DokterAction.delete] end process <<<");

        return "init_delete";
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
        logger.info("[DokterAction.search] start process >>>");
        Dokter searchDokter = getDokter();
        List<Dokter> listOfsearchDokter = new ArrayList();
        try {
            listOfsearchDokter = dokterBoProxy.searchByQuery(searchDokter);
        } catch (GeneralBOException e) {
            logger.error("[DokterAction.save] Error when searching alat by criteria," + "Found problem when searching data by criteria, please inform to your admin.", e);
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchDokter);
        logger.info("[DokterAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[DokterAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        setDokter(new Dokter());
        session.removeAttribute("listOfResult");
        logger.info("[DokterAction.initForm] end process >>>");
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

    public String initComboPelayanan() {

        Pelayanan pelayanan = new Pelayanan();
        pelayanan.setFlag("Y");

        List<Pelayanan> listOfPelayanan = new ArrayList<Pelayanan>();
        try {
            listOfPelayanan = pelayananBoProxy.getByCriteria(pelayanan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = pelayananBoProxy.saveErrorMessage(e.getMessage(), "PelayananBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[PelayananAction.initComboRole] Error when saving error,", e1);
            }
            logger.error("[PelayananAction.initComboRole] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboPelayanan.addAll(listOfPelayanan);

        return "init_combo_role";
    }

    public String saveAdd(){
        logger.info("[DokterAction.saveAdd] start process >>>");

        try {
            Dokter dokter = getDokter();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            dokter.setCreatedWho(userLogin);
            dokter.setLastUpdate(updateTime);
            dokter.setCreatedDate(updateTime);
            dokter.setLastUpdateWho(userLogin);
            dokter.setAction("C");
            dokter.setFlag("Y");

            dokterBoProxy.saveAdd(dokter);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = dokterBoProxy.saveErrorMessage(e.getMessage(), "DokterBo.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[DokterAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[DokterAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultDokter");

        logger.info("[DokterAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public String saveEdit(){
        logger.info("[PayrollSkalaGajiAction.saveEdit] start process >>>");
        try {

            Dokter editDokter = getDokter();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editDokter.setLastUpdateWho(userLogin);
            editDokter.setLastUpdate(updateTime);
            editDokter.setAction("U");
            editDokter.setFlag("Y");

            dokterBoProxy.saveEdit(editDokter);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = dokterBoProxy.saveErrorMessage(e.getMessage(), "DokterBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[DokterAction.saveEdit] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[DokterAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[DokterAction.saveEdit] end process <<<");

        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[DokterAction.saveDelete] start process >>>");
        try {

            Dokter deleteDokter = getDokter();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteDokter.setLastUpdate(updateTime);
            deleteDokter.setLastUpdateWho(userLogin);
            deleteDokter.setAction("U");
            deleteDokter.setFlag("N");

            dokterBoProxy.saveDelete(deleteDokter);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = dokterBoProxy.saveErrorMessage(e.getMessage(), "DokterBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[DokterAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[DokterAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[DokterAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    public String initComboPosition() {

        Position position = new Position();
        position.setFlag("Y");
        position.setKategori("dokter");

        List<Position> listOfPosition = new ArrayList<Position>();
        try {
            listOfPosition = positionBoProxy.getByCriteria(position);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = positionBoProxy.saveErrorMessage(e.getMessage(), "PositionBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[UserAction.initComboPosition] Error when saving error,", e1);
            }
            logger.error("[UserAction.initComboPosition] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboPositions.addAll(listOfPosition);

        return "init_combo_position";
    }

    public List<Dokter> initTypeaheadDokter(String dokterName) {
        logger.info("[KodeRekeningAction.initTypeaheadKodeRekening] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DokterBo dokterBoProxy = (DokterBo) ctx.getBean("dokterBoProxy");
        List<Dokter> dokterList = new ArrayList();
        try {
            dokterList = dokterBoProxy.typeaheadDokter(dokterName);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = dokterBoProxy.saveErrorMessage(e.getMessage(), "StrukturJabatanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[KodeRekeningAction.initTypeaheadKodeRekening] Error when saving error,", e1);
            }
            logger.error("[KodeRekeningAction.initTypeaheadKodeRekening] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return dokterList;
    }

    public List<ImSimrsPelayananEntity> getComboPelayanan() {
        logger.info("[DokterAction.initDokter] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        List<ImSimrsPelayananEntity> pelayanans = new ArrayList();
        String branchId = CommonUtil.userBranchLogin();
        if("ADMIN KP".equalsIgnoreCase(CommonUtil.roleAsLogin())){
            branchId = null;
        }
        try {
            pelayanans = pelayananBo.getJustPelayananAndLab(branchId);
        } catch (GeneralBOException e) {
            logger.error("[DokterAction.initDokter] Error when searching data by criteria," + "Found problem when searching data by criteria, please inform to your admin.", e);
        }
        return pelayanans;
    }

    public Dokter initDokter(String id) {
        logger.info("[DokterAction.initDokter] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DokterBo dokterBo = (DokterBo) ctx.getBean("dokterBoProxy");
        Dokter dokter = new Dokter();
        List<Dokter> dokterList = new ArrayList();
        if(id != null && !"".equalsIgnoreCase(id)){
            dokter.setIdDokter(id);
            try {
                dokterList = dokterBo.searchByQuery(dokter);
            } catch (GeneralBOException e) {
                logger.error("[DokterAction.initDokter] Error when searching data by criteria," + "Found problem when searching data by criteria, please inform to your admin.", e);
            }
            if(dokterList.size() > 0){
                dokter = dokterList.get(0);
            }
        }
        return dokter;
    }

    public CrudResponse saveEditDokter(String data) {
        logger.info("[DokterAction.saveEdit] start process >>>");
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DokterBo dokterBo = (DokterBo) ctx.getBean("dokterBoProxy");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        if(data != null && !"".equalsIgnoreCase(data)){
            try {
                JSONObject obj = new JSONObject(data);
                if(obj != null){
                    Dokter dokter = new Dokter();
                    String type = obj.getString("tipe");
                    dokter.setIdDokter(obj.getString("id_dokter"));
                    dokter.setNamaDokter(obj.getString("nama_dokter"));
                    dokter.setKuota(obj.getString("kuota_online"));
                    dokter.setKuotaTele(obj.getString("kuota_tele"));
                    if(obj.getString("kuota_on_site") != null && !"".equalsIgnoreCase(obj.getString("kuota_on_site"))){
                        dokter.setKuotaOnSite(new BigInteger(obj.getString("kuota_on_site")));
                    }
                    if(obj.getString("kuota_bpjs") != null && !"".equalsIgnoreCase(obj.getString("kuota_bpjs"))){
                        dokter.setKuotaBpjs(new BigInteger(obj.getString("kuota_bpjs")));
                    }
                    dokter.setKodeDpjp(obj.getString("kode_dpjp"));
                    dokter.setSip(obj.getString("sip"));
                    dokter.setFlagCall(obj.getString("flag_call"));
                    dokter.setFlagTele(obj.getString("flag_tele"));
                    dokter.setLastUpdateWho(userLogin);
                    dokter.setLastUpdate(updateTime);
                    if("delete".equalsIgnoreCase(type)){
                        dokter.setAction("D");
                        dokter.setFlag("N");
                    }else{
                        if(obj.getString("list_pelayanan") != null && !"".equalsIgnoreCase(obj.getString("list_pelayanan"))){
                            JSONArray json = new JSONArray(obj.getString("list_pelayanan"));
                            if(json != null){
                                List<DokterPelayanan> dokterPelayananList = new ArrayList<>();
                                for (int i=0; i < json.length(); i++){
                                    DokterPelayanan dokterPelayanan = new DokterPelayanan();
                                    JSONObject job = json.getJSONObject(i);
                                    dokterPelayanan.setIdDokter(obj.getString("id_dokter"));
                                    dokterPelayanan.setIdPelayanan(job.getString("id_pelayanan"));
                                    dokterPelayananList.add(dokterPelayanan);
                                }
                                if(dokterPelayananList.size() > 0){
                                    dokter.setPelayananList(dokterPelayananList);
                                }
                            }
                        }
                        dokter.setAction("U");
                        dokter.setFlag("Y");
                    }
                    try {
                        dokterBo.saveEditDokter(dokter);
                        response.setStatus("success");
                        response.setMsg("OK");
                    } catch (GeneralBOException e) {
                        response.setStatus("error");
                        response.setMsg("Error when save edit dokter, "+e.getMessage());
                        logger.error("[DokterAction.saveEdit] Error when searching data by criteria," + "Found problem when searching data by criteria, please inform to your admin.", e);
                    }
                }else{
                    response.setStatus("error");
                    response.setMsg("Error, Data JSON objact tidak ada...!");
                }
            }catch (Exception e){
                response.setStatus("error");
                response.setMsg("Error, JSON object Parse...!" +e.getMessage());
                logger.info("[DokterAction.saveEdit] end process >>>");
            }
        }else{
            response.setStatus("error");
            response.setMsg("Error, Data JSON objact tidak ada...!");
        }
        logger.info("[DokterAction.saveEdit] end process >>>");
        return response;
    }
}