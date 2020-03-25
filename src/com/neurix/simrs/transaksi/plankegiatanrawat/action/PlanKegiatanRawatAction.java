package com.neurix.simrs.transaksi.plankegiatanrawat.action;

import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.plankegiatanrawat.bo.PlanKegiatanRawatBo;
import com.neurix.simrs.transaksi.plankegiatanrawat.model.PlanKegiatanRawat;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

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

    public List<PlanKegiatanRawat> getListPlanKegiatan(String diDetail){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PlanKegiatanRawatBo planKegiatanRawatBo = (PlanKegiatanRawatBo) ctx.getBean("planKegiatanRawatBoProxy");
        return null;
    }
}
