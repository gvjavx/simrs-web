package com.neurix.simrs.master.pelayanan.action;

import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.pelayanan.bo.HeaderPelayananBo;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.model.HeaderPelayanan;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PelayananAction extends BaseTransactionAction {
    protected static transient Logger logger = Logger.getLogger(PelayananAction.class);
    Pelayanan pelayanan;
    PelayananBo pelayananBoProxy;
    private PositionBo positionBoProxy;

    private List<Position> listOfComboPositions = new ArrayList<Position>();
    private List<Pelayanan> listOfComboFarmasi = new ArrayList<Pelayanan>();

    public List<Pelayanan> getListOfComboFarmasi() {
        return listOfComboFarmasi;
    }

    public void setListOfComboFarmasi(List<Pelayanan> listOfComboFarmasi) {
        this.listOfComboFarmasi = listOfComboFarmasi;
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

    public Pelayanan getPelayanan() {
        return pelayanan;
    }

    public void setPelayanan(Pelayanan pelayanan) {
        this.pelayanan = pelayanan;
    }

    public PelayananBo getPelayananBoProxy() {
        return pelayananBoProxy;
    }

    public void setPelayananBoProxy(PelayananBo pelayananBoProxy) {
        this.pelayananBoProxy = pelayananBoProxy;
    }

    public Pelayanan init(String id) {
        logger.info("[PelayananAction.init] start process >>>");
        Pelayanan result = new Pelayanan();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        if(id != null && !"".equalsIgnoreCase(id)){
            List<Pelayanan> list = new ArrayList<>();
            try {
                Pelayanan pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(id);
                list = pelayananBo.getListObjectPelayanan(pelayanan);
            }catch (Exception e){
                logger.error("[pelayananAction.saveAdd] Error when saving error,", e);
            }
            if(list.size() > 0){
                result = list.get(0);
            }
        }
        logger.info("[PelayananAction.init] end process >>>");
        return result;
    }

    public CrudResponse save(String data) {
        CrudResponse response = new CrudResponse();
        logger.info("[PelayananAction.saveAdd] start process >>>");
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        try {
            if(data != null && !"".equalsIgnoreCase(data)){
                JSONObject obj = new JSONObject(data);
                if(obj != null){
                    Pelayanan pelayanan = new Pelayanan();
                    String type = obj.getString("tipe");
                    pelayanan.setIdHeaderPelayanan(obj.getString("id_header_pelayanan"));
                    pelayanan.setBranchId(obj.getString("branch_id"));
                    pelayanan.setCreatedDate(updateTime);
                    pelayanan.setCreatedWho(userLogin);
                    pelayanan.setLastUpdate(updateTime);
                    pelayanan.setLastUpdateWho(userLogin);
                    pelayanan.setNamaPelayanan(obj.getString("nama_pelayanan"));
                    if("add".equalsIgnoreCase(type)){
                        pelayanan.setFlag("Y");
                        pelayanan.setAction("C");
                        pelayananBo.saveAdd(pelayanan);
                        response.setStatus("success");
                        response.setMsg("OK");
                    }else if("edit".equalsIgnoreCase(type)){
                        pelayanan.setIdPelayanan(obj.getString("id_pelayanan"));
                        pelayanan.setFlag("Y");
                        pelayanan.setAction("U");
                        pelayananBo.saveEdit(pelayanan);
                        response.setStatus("success");
                        response.setMsg("OK");
                    }else if("delete".equalsIgnoreCase(type)){
                        pelayanan.setIdPelayanan(obj.getString("id_pelayanan"));
                        pelayanan.setFlag("N");
                        pelayanan.setAction("D");
                        pelayananBo.saveDelete(pelayanan);
                        response.setStatus("success");
                        response.setMsg("OK");
                    }else{
                        response.setStatus("error");
                        response.setStatus("Type Method is not found @_@");
                    }
                }else{
                    response.setStatus("error");
                    response.setStatus("Data not found @_@");
                }
            }else{
                response.setStatus("error");
                response.setStatus("Data not found @_@");
            }
        } catch (Exception e) {
            response.setStatus("error");
            response.setMsg("Error when parse JSON object @_@, "+e.getMessage());
            logger.error("[pelayananAction.saveAdd] Error when saving error,", e);
        }
        logger.info("[pelayananAction.saveAdd] end process >>>");
        return response;
    }

    @Override
    public String search() {
        logger.info("[PelayananAction.search] start process >>>");
        Pelayanan searchPelayanan = getPelayanan();
        List<Pelayanan> listOfsearchPelayanan = new ArrayList();
        try {
            listOfsearchPelayanan = pelayananBoProxy.getListObjectPelayanan(searchPelayanan);
        } catch (GeneralBOException e) {
            logger.error("[PelayananAction.save] Error when searching alat by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
            throw new GeneralBOException("Error search pelayanan, " + e.getMessage());
        }
        setPelayanan(searchPelayanan);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultPelayanan");
        session.setAttribute("listOfResultPelayanan", listOfsearchPelayanan);
        logger.info("[PelayananAction.search] end process <<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[PelayananAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        Pelayanan pelayanan = new Pelayanan();
        if (!"01".equalsIgnoreCase(CommonUtil.roleAsLogin())) {
            pelayanan.setBranchId(CommonUtil.userBranchLogin());
        }
        setPelayanan(pelayanan);
        session.removeAttribute("listOfResultPelayanan");
        logger.info("[PelayananAction.initForm] end process >>>");
        return "search";
    }

    public String initComboPosition() {

        Position position = new Position();
        position.setFlag("Y");
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

    public String initComboPelayananFarmasi() {

        String branchId = CommonUtil.userBranchLogin();

        List<Pelayanan> pelayanans = new ArrayList<>();
        try {
            pelayanans = pelayananBoProxy.getListPelayananFarmasi(branchId);
        } catch (GeneralBOException e) {
            logger.error("[PelayananAction.initComboPelayananFarmasi] ERROR. ", e);
            throw new GeneralBOException("[PelayananAction.initComboPelayananFarmasi] ERROR. " + e.getMessage());
        }

        listOfComboFarmasi.addAll(pelayanans);
        return "init_combo_farmasi";
    }

    public Pelayanan getDataPelayanan(String idPelayanan) {

        logger.info("[CheckupAction.getListDokterByBranchId] START process >>>");
        Pelayanan response = new Pelayanan();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");

        if (idPelayanan != null && !"".equalsIgnoreCase(idPelayanan)) {
            List<Pelayanan> pelayananList = new ArrayList<>();
            Pelayanan pelayanan = new Pelayanan();
            pelayanan.setIdPelayanan(idPelayanan);
            pelayanan.setBranchId(CommonUtil.userBranchLogin());

            try {
                pelayananList = pelayananBo.getByCriteria(pelayanan);
            } catch (HibernateException e) {
                logger.error("[CheckupAction.saveAdd] Error when get data for pelayanan", e);
                throw new GeneralBOException("Error when pasien id pelayanan", e);
            }

            Pelayanan poli = new Pelayanan();
            if (pelayananList.size() > 0) {

                poli = pelayananList.get(0);

                if (poli.getIdPelayanan() != null) {
                    response = poli;
                }
            }
        }

        logger.info("[CheckupAction.getListDokterByBranchId] END process >>>");
        return response;
    }

    public List<Pelayanan> getListPelayananByBranchAndTipe(String branchId, String tipe) {
        logger.info("[PelayananAction.getListPelayananByBranchAndTipe] START process >>>");

        List<Pelayanan> pelayananList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PelayananBo pelayananBo = (PelayananBo) ctx.getBean("pelayananBoProxy");
        Pelayanan pelayanan = new Pelayanan();
        pelayanan.setTipePelayanan(tipe);
        pelayanan.setBranchId(branchId);

        if ("apotek".equalsIgnoreCase(tipe)) {
            try {
                pelayananList = pelayananBo.getListApotek(branchId, "");
            } catch (HibernateException e) {
                logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
                addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
            }
        } else {
            try {
                pelayananList = pelayananBo.getByCriteria(pelayanan);
            } catch (HibernateException e) {
                logger.error("[CheckupAction.getComboPelayanan] Error when get data for combo listOfPelayanan", e);
                addActionError(" Error when get data for combo listOfPelayanan" + e.getMessage());
            }

        }

        logger.info("[PelayananAction.getListPelayananByBranchAndTipe] END process <<<");
        return pelayananList;
    }

    public List<HeaderPelayanan> getHeaderPelayanan() {
        logger.info("[PelayananAction.getHeaderPelayanan] START process >>>");
        List<HeaderPelayanan> headerPelayananList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        HeaderPelayananBo headerPelayananBo = (HeaderPelayananBo) ctx.getBean("headerPelayananBoProxy");
        HeaderPelayanan pelayanan = new HeaderPelayanan();
        try {
            headerPelayananList = headerPelayananBo.getByCriteria(pelayanan);
        } catch (HibernateException e) {
            logger.error("[CheckupAction.saveAdd] Error when get data for pelayanan", e);
            throw new GeneralBOException("Error when pasien id pelayanan", e);
        }
        logger.info("[PelayananAction.getHeaderPelayanan] END process >>>");
        return headerPelayananList;
    }
}