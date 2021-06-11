package com.neurix.simrs.master.labdetail.action;

import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.lab.bo.LabBo;
import com.neurix.simrs.master.lab.model.Lab;
import com.neurix.simrs.master.labdetail.bo.LabDetailBo;
import com.neurix.simrs.master.labdetail.model.ImSimrsLabDetailEntity;
import com.neurix.simrs.master.labdetail.model.LabDetail;
import com.neurix.simrs.master.parampemeriksaan.bo.ParameterPemeriksaanBo;
import com.neurix.simrs.master.parampemeriksaan.model.ParameterPemeriksaan;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LabDetailAction extends BaseTransactionAction {

    protected static transient Logger logger = Logger.getLogger(LabDetailAction.class);
    private LabDetailBo labDetailBoProxy;
    private LabBo labBoProxy;
    private LabDetail labDetail;
    private List<Lab> listOfComboLab = new ArrayList<Lab>();

    public LabDetail initParameter(String id) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LabDetailBo labDetailBo = (LabDetailBo) ctx.getBean("labDetailBoProxy");
        LabDetail detail = new LabDetail();
        List<LabDetail> labDetailList = new ArrayList<>();
        if (id != null && !"".equalsIgnoreCase(id)) {
            LabDetail lab = new LabDetail();
            lab.setIdLabDetail(id);
            try {
                labDetailList = labDetailBo.getDataParameterPemeriksaan(lab);
            } catch (GeneralBOException e) {
                logger.error(e.getMessage());
            }
        }
        if (labDetailList.size() > 0) {
            detail = labDetailList.get(0);
        }
        return detail;
    }

    @Override
    public String search() {
        LabDetail detail = getLabDetail();
        List<LabDetail> listOfsearchLabDetail = new ArrayList();
        try {
            listOfsearchLabDetail = labDetailBoProxy.getDataParameterPemeriksaan(detail);
        } catch (GeneralBOException e) {
            logger.error(e.getMessage());
        }
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", listOfsearchLabDetail);
        setLabDetail(detail);
        return "search";
    }

    @Override
    public String initForm() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        setLabDetail(new LabDetail());
        return "search";
    }

    public CrudResponse saveAdd(String data, String isNew) {
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LabDetailBo labDetailBo = (LabDetailBo) ctx.getBean("labDetailBoProxy");
        CrudResponse response = new CrudResponse();
        try {
            JSONArray json = new JSONArray(data);
            List<LabDetail> labDetailList = new ArrayList<>();
            if (json != null) {
                for (int i = 0; i < json.length(); i++) {
                    JSONObject obj = json.getJSONObject(i);
                    LabDetail detail = new LabDetail();
                    String idLab = "";
                    if (obj.has("id_lab")) {
                        detail.setIdLab(obj.getString("id_lab"));
                        idLab = obj.getString("id_lab");
                    }
                    if (obj.has("nama_lab")) {
                        detail.setNamaLab(obj.getString("nama_lab"));
                    }
                    detail.setIdParameterPemeriksaan(obj.getString("id_parameter_pemeriksaan"));
                    detail.setTarif(new BigDecimal(obj.getString("tarif")));
                    detail.setBranchId(obj.getString("branch_id"));
                    detail.setCreatedWho(userLogin);
                    detail.setLastUpdate(updateTime);
                    detail.setCreatedDate(updateTime);
                    detail.setLastUpdateWho(userLogin);
                    detail.setAction("C");
                    detail.setFlag("Y");
                    List<ImSimrsLabDetailEntity> labDetailEntityList = new ArrayList<>();
                    try {
                        labDetailEntityList = labDetailBo.cekDataParameterPemeriksaan(obj.getString("id_parameter_pemeriksaan"), idLab);
                        response.setStatus("success");
                        response.setMsg("Berhasil...!");
                    }catch (HibernateException e){
                        response.setStatus("error");
                        response.setMsg("Mohon maaf tidak dapat menukan data...!");
                        return response;
                    }
                    if(labDetailEntityList.size() > 0){
                        response.setStatus("error");
                        response.setMsg("Mohon Maaf Data "+obj.getString("nama_parameter_pemeriksaan")+" di paket "+obj.getString("nama_paket")+" sudah ada...!");
                        return response;
                    }else{
                        labDetailList.add(detail);
                        response.setStatus("success");
                        response.setMsg("Berhasil...!");
                    }
                }
                if (labDetailList.size() > 0 && "success".equalsIgnoreCase(response.getStatus())) {
                    response = labDetailBo.saveAdd(labDetailList, isNew);
                } else {
                    response.setStatus("error");
                    response.setMsg("Mohon maaf tidak dapat menukan data...!");
                }
            } else {
                response.setStatus("error");
                response.setMsg("Mohon maaf tidak dapat menukan data...!");
            }
        } catch (JSONException e) {
            response.setStatus("error");
            response.setMsg("Mohon maaf data json yang dikirim bermasalah...!" + e.getMessage());
        }
        return response;
    }

    public CrudResponse saveEdit(String data) {
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LabDetailBo labDetailBo = (LabDetailBo) ctx.getBean("labDetailBoProxy");
        CrudResponse response = new CrudResponse();
        try {
            JSONObject object = new JSONObject(data);
            if (object != null) {
                LabDetail detail = new LabDetail();
                detail.setIdLabDetail(object.getString("id_lab_detail"));
                detail.setIdLab(object.getString("id_lab"));
                detail.setNamaLab(object.getString("nama_lab"));
                detail.setIdParameterPemeriksaan(object.getString("id_parameter_pemeriksaan"));
                detail.setTarif(new BigDecimal(object.getString("tarif")));
                detail.setLastUpdate(updateTime);
                detail.setLastUpdateWho(userLogin);
                detail.setAction("U");
                detail.setFlag("Y");
                response = labDetailBo.saveEdit(detail);
            } else {
                response.setStatus("error");
                response.setMsg("Data json yang dikirim tidak dapat ditemukan...!");
            }
        } catch (JSONException e) {
            response.setStatus("error");
            response.setMsg("Data json yang dikirim bermasalaah...!" + e.getMessage());
        }
        return response;
    }

    public CrudResponse saveDelete(String id) {
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LabDetailBo labDetailBo = (LabDetailBo) ctx.getBean("labDetailBoProxy");
        CrudResponse response = new CrudResponse();
        if (id != null && !"".equalsIgnoreCase(id)) {
            try {
                LabDetail detail = new LabDetail();
                detail.setIdLabDetail(id);
                detail.setLastUpdate(updateTime);
                detail.setLastUpdateWho(userLogin);
                detail.setAction("D");
                detail.setFlag("N");
                response = labDetailBo.saveEdit(detail);
            } catch (GeneralBOException e) {
                logger.error(e.getMessage());
                response.setStatus("error");
                response.setMsg("error saat delete data...!" + e.getMessage());
            }
        }
        return response;
    }

    public List<LabDetail> listLabDetail(String idLab) {
        logger.info("[LabAction.listLabDetail] start process >>>");
        List<LabDetail> labDetailList = new ArrayList<>();
        if(idLab != null && !"".equalsIgnoreCase(idLab)){
            LabDetail labDetail = new LabDetail();
            labDetail.setIdLab(idLab);
            labDetail.setBranchId(CommonUtil.userBranchLogin());
            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            LabDetailBo labDetailBo = (LabDetailBo) ctx.getBean("labDetailBoProxy");
            try {
                labDetailList = labDetailBo.getDataParameterPemeriksaan(labDetail);
            } catch (GeneralBOException e) {
                logger.error("[LabDetailAction.listLabDetail] Error when get data lab detail ," + "Found problem when searching data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }
        }
        logger.info("[LabDetailAction.listLabDetail] end process >>>");
        return labDetailList;

    }

    public String initComboLab() {
        Lab lab = new Lab();
        lab.setFlag("Y");
        List<Lab> listOfLab = new ArrayList<Lab>();
        try {
            listOfLab = labBoProxy.getByCriteria(lab);
        } catch (GeneralBOException e) {
            Long logId = null;
            try {
                logId = labBoProxy.saveErrorMessage(e.getMessage(), "LabBO.getByCriteria");
            } catch (GeneralBOException e1) {
                logger.error("[LabDetailAction.initComboLab] Error when saving error,", e1);
            }
            logger.error("[LabDetailAction.initComboLab] Error when searching data by criteria," + "[" + logId + "] Found problem when searching data by criteria, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when searching data by criteria, please inform to your admin");
            return "failure";
        }
        listOfComboLab.addAll(listOfLab);
        return "init_combo";
    }

    public List<Lab> getLab() {
        List<Lab> labList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LabBo labBo = (LabBo) ctx.getBean("labBoProxy");
        Lab lab = new Lab();
        lab.setFlag("Y");
        try {
            labList = labBo.getByCriteria(lab);
        } catch (GeneralBOException e) {
            logger.error(e.getMessage());
        }
        return labList;
    }

    public List<Lab> getLabByKategori(String id, String branch) {
        List<Lab> labList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LabBo labBo = (LabBo) ctx.getBean("labBoProxy");
        try {
            labList = labBo.getDataLab(id, branch);
        } catch (GeneralBOException e) {
            logger.error(e.getMessage());
        }
        return labList;
    }

    public List<ParameterPemeriksaan> getComboParameter(String kategori) {
        List<ParameterPemeriksaan> labList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ParameterPemeriksaanBo parameterPemeriksaanBo = (ParameterPemeriksaanBo) ctx.getBean("parameterPemeriksaanBoProxy");
        ParameterPemeriksaan pemeriksaan = new ParameterPemeriksaan();
        pemeriksaan.setIdKategoriLab(kategori);
        pemeriksaan.setFlag("Y");
        try {
            labList = parameterPemeriksaanBo.getByCriteria(pemeriksaan);
        } catch (GeneralBOException e) {
            logger.error(e.getMessage());
        }
        return labList;
    }

    public ImSimrsLabDetailEntity labDetailEntityByIdLab(String idParameter) {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LabDetailBo labDetailBo = (LabDetailBo) ctx.getBean("labDetailBoProxy");
        return labDetailBo.getLabDetailEntityById(idParameter);
    }

    public List<LabDetail> getListComboParameter(String idLab) {
        logger.info("[LabAction.getListComboParameter] start process >>>");
        List<LabDetail> labDetailList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        LabDetailBo labDetailBo = (LabDetailBo) ctx.getBean("labDetailBoProxy");
        LabDetail labDetail = new LabDetail();
        labDetail.setIdLab(idLab);
        labDetail.setBranchId(CommonUtil.userBranchLogin());

        try {
            labDetailList = labDetailBo.getDataParameterPemeriksaan(labDetail);
        } catch (GeneralBOException e) {
            logger.error("[LabDetailAction.getListComboParameter] Error when get data lab detail ," + "Found problem when searching data, please inform to your admin.", e);
        }

        logger.info("[LabDetailAction.getListComboParameter] end process >>>");
        return labDetailList;

    }

    public LabBo getLabBoProxy() {
        return labBoProxy;
    }

    public void setLabBoProxy(LabBo labBoProxy) {
        this.labBoProxy = labBoProxy;
    }

    public List<Lab> getListOfComboLab() {
        return listOfComboLab;
    }

    public void setListOfComboLab(List<Lab> listOfComboLab) {
        this.listOfComboLab = listOfComboLab;
    }

    public LabDetail getLabDetail() {
        return labDetail;
    }

    public void setLabDetail(LabDetail labDetail) {
        this.labDetail = labDetail;
    }

    public LabDetailBo getLabDetailBoProxy() {
        return labDetailBoProxy;
    }

    public void setLabDetailBoProxy(LabDetailBo labDetailBoProxy) {
        this.labDetailBoProxy = labDetailBoProxy;
    }

}