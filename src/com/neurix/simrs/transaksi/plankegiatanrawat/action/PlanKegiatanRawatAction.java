package com.neurix.simrs.transaksi.plankegiatanrawat.action;

import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.moncairan.model.ItSimrsMonCairanEntity;
import com.neurix.simrs.transaksi.moncairan.model.MonCairan;
import com.neurix.simrs.transaksi.monpemberianobat.model.ItSimrsMonPemberianObatEntity;
import com.neurix.simrs.transaksi.monpemberianobat.model.MonPemberianObat;
import com.neurix.simrs.transaksi.monvitalsign.model.ItSimrsMonVitalSignEntity;
import com.neurix.simrs.transaksi.monvitalsign.model.MonVitalSign;
import com.neurix.simrs.transaksi.plankegiatanrawat.bo.PlanKegiatanRawatBo;
import com.neurix.simrs.transaksi.plankegiatanrawat.model.ItSimrsPlanKegiatanRawatEntity;
import com.neurix.simrs.transaksi.plankegiatanrawat.model.PlanKegiatanRawat;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 06/03/20.
 */
public class PlanKegiatanRawatAction extends BaseTransactionAction {
    Logger logger = Logger.getLogger(PlanKegiatanRawatAction.class);
    private PlanKegiatanRawatBo planKegiatanRawatBoProxy;

    @Override
    public String search() {
        return SUCCESS;
    }

    @Override
    public String initForm() {
        return SUCCESS;
    }

    public void setPlanKegiatanRawatBoProxy(PlanKegiatanRawatBo planKegiatanRawatBoProxy) {
        this.planKegiatanRawatBoProxy = planKegiatanRawatBoProxy;
    }

    public List<PlanKegiatanRawat> getSearchKegiatanRawat(String jsonString) throws JSONException{

        PlanKegiatanRawat plan = new PlanKegiatanRawat();
        plan.setBranchId(CommonUtil.userBranchLogin());

        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            if (!"".equalsIgnoreCase(obj.getString("id_pasien"))){
                plan.setIdPasien(obj.getString("id_pasien"));
            }
//            if (!"".equalsIgnoreCase(obj.getString("id_detail_checkup"))){
//                plan.setIdDetailCheckup(obj.getString("id_detail_checkup"));
//            }
            if (!"".equalsIgnoreCase(obj.getString("id_pelayanan"))){
                plan.setIdDetailCheckup(obj.getString("id_pelayanan"));
            }
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PlanKegiatanRawatBo planKegiatanRawatBo = (PlanKegiatanRawatBo) ctx.getBean("planKegiatanRawatBoProxy");

        return planKegiatanRawatBo.getListKegiatanRawat(plan);
    }

    public List<PlanKegiatanRawat> getListPlanKegiatan(String idDetail){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PlanKegiatanRawatBo planKegiatanRawatBo = (PlanKegiatanRawatBo) ctx.getBean("planKegiatanRawatBoProxy");

        PlanKegiatanRawat planKegiatanRawat = new PlanKegiatanRawat();
        planKegiatanRawat.setIdDetailCheckup(idDetail);

        return planKegiatanRawatBo.getListPlanKegitatanRawat(planKegiatanRawat);
    }

    public List<PlanKegiatanRawat> getListPlanKegiatanRawatByTglMulai(String idDetailCheckup, String tglPlan){

        PlanKegiatanRawat planKegiatanRawat = new PlanKegiatanRawat();
        planKegiatanRawat.setIdDetailCheckup(idDetailCheckup);
        planKegiatanRawat.setTglMulai(Date.valueOf(tglPlan));

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PlanKegiatanRawatBo planKegiatanRawatBo = (PlanKegiatanRawatBo) ctx.getBean("planKegiatanRawatBoProxy");
        return planKegiatanRawatBo.getSearchByCritria(planKegiatanRawat);
    }

    public CrudResponse savePlanKegiatanRawat(String idDetailCheckup, String tglPlan, String listOfVitalSign, String listOfCairan, String listOfParenteral, String listOfNonParenteral) throws JSONException{

        String userLogin = CommonUtil.userLogin();
        String branchId = CommonUtil.userBranchLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        CrudResponse response = new CrudResponse();

        List<MonVitalSign> monVitalSigns = new ArrayList<>();
        List<MonCairan> monCairans = new ArrayList<>();
        List<MonPemberianObat> monPemberianObats = new ArrayList<>();

        PlanKegiatanRawat planKegiatanRawat = new PlanKegiatanRawat();
        planKegiatanRawat.setIdDetailCheckup(idDetailCheckup);
        planKegiatanRawat.setTglMulai(Date.valueOf(tglPlan));
        planKegiatanRawat.setTglSelesai(Date.valueOf(tglPlan));
        planKegiatanRawat.setBranchId(branchId);
        planKegiatanRawat.setFlag("Y");
        planKegiatanRawat.setAction("C");
        planKegiatanRawat.setCreatedDate(time);
        planKegiatanRawat.setCreatedWho(userLogin);
        planKegiatanRawat.setLastUpdate(time);
        planKegiatanRawat.setLastUpdateWho(userLogin);

        if (!"".equalsIgnoreCase(listOfVitalSign)){
            JSONArray json = new JSONArray(listOfVitalSign);
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                MonVitalSign monVitalSign = new MonVitalSign();
                monVitalSign.setWaktu(obj.getString("waktu"));
                monVitalSign.setCatatanDokter(obj.getString("ket"));

                monVitalSign.setJam(Integer.valueOf(obj.getString("jam")));
                monVitalSigns.add(monVitalSign);
            }
        }


        if (!"".equalsIgnoreCase(listOfCairan)){
            JSONArray json = new JSONArray(listOfCairan);
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);
                MonCairan monCairan = new MonCairan();
                monCairan.setWaktu(obj.getString("waktu"));
                monCairan.setCatatanDokter(obj.getString("ket"));

                monCairan.setMacamCairan(obj.getString("macam"));
                monCairan.setMelalui(obj.getString("melalui"));
                monCairan.setJumlah(obj.getString("jumlah"));
                monCairan.setJamMulai(obj.getString("mulai"));
                monCairan.setJamSelesai(obj.getString("selesai"));
                monCairan.setCekTambahanObat(obj.getString("cek"));
                monCairan.setSisa(obj.getString("sisa"));
                monCairan.setJamUkurBuang(obj.getString("buang"));
                monCairan.setDari(obj.getString("dari"));
                monCairan.setBalanceCairan(obj.getString("balance"));
                monCairans.add(monCairan);
            }
        }


        if (!"".equalsIgnoreCase(listOfParenteral)){
            JSONArray json = new JSONArray(listOfParenteral);
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);

                MonPemberianObat monPemberianObat = new MonPemberianObat();
                monPemberianObat.setWaktu(obj.getString("waktu"));
                monPemberianObat.setCatatanDokter(obj.getString("ket"));

                monPemberianObat.setNamaObat(obj.getString("nama"));
                monPemberianObat.setCaraPemberian(obj.getString("cara"));
                monPemberianObat.setDosis(obj.getString("dosis"));
                monPemberianObat.setSkinTes(obj.getString("skintes"));
                monPemberianObat.setWaktuPemberian(obj.getString("waktupemberian"));
                monPemberianObat.setKeterangan(obj.getString("ket"));
                monPemberianObat.setKategori(obj.getString("kat"));
                monPemberianObats.add(monPemberianObat);
            }
        }
        if (!"".equalsIgnoreCase(listOfNonParenteral)){
            JSONArray json = new JSONArray(listOfNonParenteral);
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);

                MonPemberianObat monPemberianObat = new MonPemberianObat();
                monPemberianObat.setWaktu(obj.getString("waktu"));
                monPemberianObat.setCatatanDokter(obj.getString("ket"));

                monPemberianObat.setNamaObat(obj.getString("nama"));
                monPemberianObat.setDosis(obj.getString("dosis"));
                monPemberianObat.setWaktuPemberian(obj.getString("waktupemberian"));
                monPemberianObat.setKeterangan(obj.getString("ket"));
                monPemberianObat.setKategori(obj.getString("kat"));
                monPemberianObats.add(monPemberianObat);

            }
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PlanKegiatanRawatBo planKegiatanRawatBo = (PlanKegiatanRawatBo) ctx.getBean("planKegiatanRawatBoProxy");
        try {
            planKegiatanRawatBo.saveAddPlanKegiatan(planKegiatanRawat, monVitalSigns, monCairans, monPemberianObats);
            response.setMsg("success");
        } catch (GeneralBOException e){
            logger.error("[PlanKegiatanAction.savePlanKegiatanRawat] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("[PlanKegiatanAction.savePlanKegiatanRawat] ERROR. "+ e);
            return response;
        }

        return response;
    }
}
