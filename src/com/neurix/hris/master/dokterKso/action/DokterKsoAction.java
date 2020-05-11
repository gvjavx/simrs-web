package com.neurix.hris.master.dokterKso.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.dokterKso.bo.DokterKsoBo;
import com.neurix.hris.master.dokterKso.model.DokterKso;
import com.neurix.hris.master.dokterKsoTindakan.model.DokterKsoTindakan;
import com.neurix.simrs.master.dokter.bo.DokterBo;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.riwayattindakan.bo.RiwayatTindakanBo;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DokterKsoAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(DokterKsoAction.class);
    DokterKso dokterKso;
    DokterKsoBo dokterKsoBoProxy;
    private BranchBo branchBoProxy;
    private PositionBo positionBoProxy;
    private TindakanBo tindakanBoProxy;

    private List<Branch> listOfComboBranches = new ArrayList<Branch>();
    private List<Position> listOfComboPositions = new ArrayList<Position>();
    private List<Tindakan> listOfComboTindakans = new ArrayList<Tindakan>();



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

    public BranchBo getBranchBoProxy() {
        return branchBoProxy;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public List<Branch> getListOfComboBranches() {
        return listOfComboBranches;
    }

    public void setListOfComboBranches(List<Branch> listOfComboBranches) {
        this.listOfComboBranches = listOfComboBranches;
    }

    public DokterKso getDokterKso() {
        return dokterKso;
    }

    public void setDokterKso(DokterKso dokterKso) {
        this.dokterKso = dokterKso;
    }

    public DokterKsoBo getDokterKsoBoProxy() {
        return dokterKsoBoProxy;
    }

    public void setDokterKsoBoProxy(DokterKsoBo dokterKsoBoProxy) {
        this.dokterKsoBoProxy = dokterKsoBoProxy;
    }

    public DokterKso init(String kode, String flag){
        logger.info("[DokterKsoAction.init] start process >>>>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<DokterKso> listOfResult = (List<DokterKso>) session.getAttribute("listOfResultDokterKso");

        if (kode != null && !"".equalsIgnoreCase(kode)){
            if (listOfResult != null){
                for (DokterKso dokterKso : listOfResult){
                    if (kode.equalsIgnoreCase(dokterKso.getDokterKsoId()) && flag.equalsIgnoreCase(dokterKso.getFlag())){
                        String kodering = dokterKso.getKodering();
                        String[] arrOfStr = kodering.split("\\.");
                        String kode1 = arrOfStr[1];String kode2 = arrOfStr[2];String kode3 = arrOfStr[3];
                        String koder = kode1+"."+kode2+"."+kode3;
                        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                        Position position = new Position();
                        PositionBo positionBo = (PositionBo) context.getBean("positionBoProxy");
                        position.setKodering(koder);
                        position.setFlag("Y");
                        List<Position> positions = positionBo.getByCriteria(position);
                        String positionId = positions.get(0).getPositionId();
                        dokterKso.setPositionId(positionId);

                        Dokter dokter = new Dokter();
                        DokterBo dokterBo = (DokterBo) context.getBean("dokterBoProxy");
                        dokter.setIdDokter(dokterKso.getNip());
                        dokter.setFlag("Y");
                        List<Dokter> dokters = dokterBo.getByCriteria(dokter);
                        String dokterName = dokters.get(0).getNamaDokter();
                        dokterKso.setNamaDokter(dokterName);

                        setDokterKso(dokterKso);
                        break;
                    }
                }
            } else {
                setDokterKso(new DokterKso());
            }
            logger.info("[DokterKsoAction.init] end process >>>>>");
        }
        return getDokterKso();
    }

    @Override
    public String add() {
        logger.info("[DokterKsoAction.add] start process >>>");
        DokterKso addDokterKso = new DokterKso();
        setAddOrEdit(true);
        setAdd(true);

        String branchId = CommonUtil.userBranchLogin();
        if (branchId != null){
            addDokterKso.setBranchUser(branchId);
            addDokterKso.setBranchId(branchId);
        }else {
            addDokterKso.setBranchUser("");
            addDokterKso.setBranchId("");
        }
        dokterKso = addDokterKso;
        setDokterKso(addDokterKso);
//        HttpSession session = ServletActionContext.getRequest().getSession();
//        session.removeAttribute("listOfResult");

        logger.info("[DokterKSOAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        logger.info("[DokterKsoAction.edit] start process >>>");
        String itemId = getId();
        String itemFlag = getFlag();

        DokterKso editDokterKso = new DokterKso();

        if(itemFlag != null){
            try {
                editDokterKso = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = dokterKsoBoProxy.saveErrorMessage(e.getMessage(), "DokterKsoBO.getByCriteria");
                } catch (GeneralBOException e1) {
                    logger.error("[DokterKsoAction.edit] Error when retrieving edit data,", e1);
                }
                logger.error("[DokterKsoAction.edit] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for edit, please inform to your admin.");
                return "failure";
            }

            if(editDokterKso != null) {
                String branchId = CommonUtil.userBranchLogin();
//                Ruangan data = new Ruangan();
                if (branchId != null){
                    editDokterKso.setBranchUser(branchId);
                }else {
                    editDokterKso.setBranchUser("");
                }

                setDokterKso(editDokterKso);
            } else {
                editDokterKso.setFlag(itemFlag);
                //editPayrollSkalaGaji.getSkalaGajiId(itemId);
                setDokterKso(editDokterKso);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //editPayrollSkalaGaji.getSkalaGajiId(itemId);
            editDokterKso.setFlag(getFlag());
            setDokterKso(editDokterKso);
            addActionError("Error, Unable to edit again with flag = N.");
            return "failure";
        }

        setAddOrEdit(true);
        logger.info("[TindakanAction.edit] end process >>>");
        return "init_edit";
    }

    @Override
    public String delete() {
        logger.info("[DokterKsoAction.delete] start process >>>");

        String itemId = getId();
        String itemFlag = getFlag();
        DokterKso deleteDokterKso = new DokterKso();

        if (itemFlag != null ) {

            try {
                deleteDokterKso = init(itemId, itemFlag);
            } catch (GeneralBOException e) {
                Long logId = null;
                try {
                    logId = dokterKsoBoProxy.saveErrorMessage(e.getMessage(), "DokterKsoBO.getAlatById");
                } catch (GeneralBOException e1) {
                    logger.error("[DokterKsoAction.delete] Error when retrieving delete data,", e1);
                }
                logger.error("[DokterKsoAction.delete] Error when retrieving item," + "[" + logId + "] Found problem when retrieving data, please inform to your admin.", e);
                addActionError("Error, " + "[code=" + logId + "] Found problem when retrieving data for delete, please inform to your admin.");
                return "failure";
            }

            if (deleteDokterKso != null) {
                setDokterKso(deleteDokterKso);

            } else {
                //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
                deleteDokterKso.setFlag(itemFlag);
                setDokterKso(deleteDokterKso);
                addActionError("Error, Unable to find data with id = " + itemId);
                return "failure";
            }
        } else {
            //deletePayrollSkalaGaji.getSkalaGajiId(itemId);
            deleteDokterKso.setFlag(itemFlag);
            setDokterKso(deleteDokterKso);
            addActionError("Error, Unable to delete again with flag = N.");
            return "failure";
        }

        logger.info("[DokterKsoAction.delete] end process <<<");

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

    public String saveAdd(){
        logger.info("[DokterKsoAction.saveAdd] start process >>>");

        try {
            DokterKso dokterKso = getDokterKso();
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            dokterKso.setCreatedWho(userLogin);
            dokterKso.setLastUpdate(updateTime);
            dokterKso.setCreatedDate(updateTime);
            dokterKso.setLastUpdateWho(userLogin);
            dokterKso.setAction("C");
            dokterKso.setFlag("Y");

            dokterKsoBoProxy.saveAdd(dokterKso);
        }catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = dokterKsoBoProxy.saveErrorMessage(e.getMessage(), "tindakanBO.saveAdd");
            } catch (GeneralBOException e1) {
                logger.error("[tindakanAction.saveAdd] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[tindakanAction.saveAdd] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }


        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultDokterKso");

        logger.info("[tindakanAction.saveAdd] end process >>>");
        return "success_save_add";
    }

    public String saveEdit(){
        logger.info("[DokterKsoAction.saveEdit] start process >>>");
        try {

            DokterKso editDokterKso = getDokterKso();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            editDokterKso.setLastUpdateWho(userLogin);
            editDokterKso.setLastUpdate(updateTime);
            editDokterKso.setAction("U");
            editDokterKso.setFlag("Y");

            dokterKsoBoProxy.saveEdit(editDokterKso);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = dokterKsoBoProxy.saveErrorMessage(e.getMessage(), "DokterKsoBO.saveEdit");
            } catch (GeneralBOException e1) {
                logger.error("[DokterKsoAction.saveEdit] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[DokterKsoAction.saveEdit] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[DokterKsoAction.saveEdit] end process <<<");
        return "success_save_edit";
    }

    public String saveDelete(){
        logger.info("[DokterKsoAction.saveDelete] start process >>>");
        try {

            DokterKso deleteDokterKso = getDokterKso();

            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            deleteDokterKso.setLastUpdate(updateTime);
            deleteDokterKso.setLastUpdateWho(userLogin);
            deleteDokterKso.setAction("U");
            deleteDokterKso.setFlag("N");

            dokterKsoBoProxy.saveDelete(deleteDokterKso);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = dokterKsoBoProxy.saveErrorMessage(e.getMessage(), "DokterKsoBO.saveDelete");
            } catch (GeneralBOException e1) {
                logger.error("[DokterKsoAction.saveDelete] Error when saving error,", e1);
                throw new GeneralBOException(e1.getMessage());
            }
            logger.error("[DokterKsoAction.saveDelete] Error when editing item alat," + "[" + logId + "] Found problem when saving edit data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving edit data, please inform to your admin.\n" + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        logger.info("[DokterKsoAction.saveDelete] end process <<<");

        return "success_save_delete";
    }

    @Override
    public String search() {
        logger.info("[DokterKsoAction.search] start process >>>");

        DokterKso searchDokterKso = getDokterKso();
        List<DokterKso> listOfsearchDokterKso = new ArrayList();

        try {
            listOfsearchDokterKso = dokterKsoBoProxy.getByCriteria(searchDokterKso);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = dokterKsoBoProxy.saveErrorMessage(e.getMessage(), "DokterKsoBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[DokterKsoAction.search] Error when saving error,", e1);
                return ERROR;
            }
            logger.error("[PelayananAction.save] Error when searching alat by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
            return ERROR;
        }

        String branchId = CommonUtil.userBranchLogin();
        DokterKso data = new DokterKso();
        if (branchId != null){
            data.setBranchUser(branchId);
        }else {
            data.setBranchUser("");
        }
        dokterKso = data;

        HttpSession session = ServletActionContext.getRequest().getSession();

        session.removeAttribute("listOfResultDokterKso");
        session.setAttribute("listOfResultDokterKso", listOfsearchDokterKso);

        logger.info("[DokterKsoAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[PelayananAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        DokterKso data = new DokterKso();
        if (branchId != null){
            data.setBranchUser(branchId);
            data.setBranchId(branchId);
        }else {
            data.setBranchUser("");
            data.setBranchId("");
        }
        dokterKso = data;

        session.removeAttribute("listOfResultDokterKso");
        logger.info("[DokterKsoAction.initForm] end process >>>");
        return INPUT;
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
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

    public String initComboTindakan() {

        Tindakan tindakan = new Tindakan();
        tindakan.setFlag("Y");

        List<Tindakan> listOfTindakan = new ArrayList<Tindakan>();
        try {
            listOfTindakan = tindakanBoProxy.getByCriteria(tindakan);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = tindakanBoProxy.saveErrorMessage(e.getMessage(), "TindakanBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[DokterAction.initComboTindakan] Error when saving error,", e1);
            }
            logger.error("[TindakanAction.initComboTindakan] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }

        listOfComboTindakans.addAll(listOfTindakan);

        return "init_combo_tindakan";
    }

    public List<RiwayatTindakan> initTypeaheadRiwayatTindakan(String namaRiwayatTindakan) {
        logger.info("[KodeRekeningAction.initTypeaheadKodeRekening] start process >>>");
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        RiwayatTindakanBo riwayatTindakanBoProxy = (RiwayatTindakanBo) ctx.getBean("riwayatTindakanBoProxy");
        List<RiwayatTindakan> riwayatTindakanList = new ArrayList();
        try {
            riwayatTindakanList = riwayatTindakanBoProxy.typeaheadRiwayatTindakan(namaRiwayatTindakan);
        } catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[KodeRekeningAction.initTypeaheadKodeRekening] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin" );
        }
        return riwayatTindakanList;
    }

    public void saveRiwayatKsoTindakanSession(String idRiwayatTindakan, String namaRiwayatTindakan, BigDecimal persenKsoTindakan){
        logger.info("[DokterKsoAction.saveKoderingSession] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<DokterKsoTindakan> listOfResult = (List<DokterKsoTindakan>) session.getAttribute("listOfResultDokterKsoTindakan");
        List<DokterKsoTindakan> listOfResultEdit = (List<DokterKsoTindakan>) session.getAttribute("listOfResultDokterKsoTindakanEdit");

        if (listOfResult==null){
            listOfResult= new ArrayList<>();
        }

        DokterKsoTindakan result = new DokterKsoTindakan();
        result.setTindakanId(idRiwayatTindakan);
        result.setTindakanName(namaRiwayatTindakan);
        result.setPersenKso(persenKsoTindakan);
        listOfResult.add(result);

        if (listOfResultEdit != null){
            DokterKsoTindakan result1 = new DokterKsoTindakan();
            result1.setTindakanId(idRiwayatTindakan);
            result1.setTindakanName(namaRiwayatTindakan);
            result1.setPersenKso(persenKsoTindakan);
            listOfResultEdit.add(result1);
        }else {
            listOfResultEdit = new ArrayList<>();
            DokterKsoTindakan result1 = new DokterKsoTindakan();
            result1.setTindakanId(idRiwayatTindakan);
            result1.setTindakanName(namaRiwayatTindakan);
            result1.setPersenKso(persenKsoTindakan);
            listOfResultEdit.add(result1);
        }

        session.setAttribute("listOfResultTindakanDetail",listOfResult);
        session.setAttribute("listOfResultTindakanDetailEdit",listOfResultEdit);
        logger.info("[SettingReportKeuanganKonsolAction.saveKonsolDetailSession] end process <<<");
    }

    public List<DokterKsoTindakan> searchTindakanDetailSession() {
        logger.info("[DokterKsoAction.searchTindakanDetailSession] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<DokterKsoTindakan> listOfsearch= (List<DokterKsoTindakan>) session.getAttribute("listOfResultTindakanDetail");
        return listOfsearch;
    }

    public String deleteSessionTindakanDetail(String tindakanId) {
        logger.info("[DokterKsoAction.deleteSessionTindakanDetail] start process >>>");
        String status="";
        HttpSession session = ServletActionContext.getRequest().getSession();
        List<DokterKsoTindakan> tindakanDetailList = (List<DokterKsoTindakan>) session.getAttribute("listOfResultTindakanDetail");
        List<DokterKsoTindakan> tindakanDetailArrayList = new ArrayList<>();
        List<DokterKsoTindakan> tindakanDetailArrayListEdit = new ArrayList<>();

        for (DokterKsoTindakan tindakanDetail:tindakanDetailList){
            if (tindakanDetail.getTindakanId().equalsIgnoreCase(tindakanId)){
                tindakanDetail.setFlag("N");
            }else{
                tindakanDetail.setFlag("Y");
                tindakanDetailArrayList.add(tindakanDetail);
            }

//            AkunSettingReportKeuanganKonsolDetail detail = new AkunSettingReportKeuanganKonsolDetail();
//            keuanganKonsolDetail.setRekeningId(keuanganKonsolDetail.getRekeningId());
//            keuanganKonsolDetail.setOperator(keuanganKonsolDetail.getOperator());
            tindakanDetailArrayListEdit.add(tindakanDetail);
        }

        session.setAttribute("listOfResultTindakanDetailEdit",tindakanDetailArrayListEdit);
        session.setAttribute("listOfResultTindakanDetail",tindakanDetailArrayList);
        logger.info("[DokterKsoAction.deleteSessionTindakanDetail] end process >>>");
        return status;
    }
}