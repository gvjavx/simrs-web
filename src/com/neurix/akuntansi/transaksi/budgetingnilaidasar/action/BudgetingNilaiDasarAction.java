package com.neurix.akuntansi.transaksi.budgetingnilaidasar.action;

import com.neurix.akuntansi.transaksi.budgeting.bo.BudgetingBo;
import com.neurix.akuntansi.transaksi.budgeting.model.Budgeting;
import com.neurix.akuntansi.transaksi.budgetingnilaidasar.bo.BudgetingNilaiDasarBo;
import com.neurix.akuntansi.transaksi.budgetingnilaidasar.model.BudgetingNilaiDasar;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by reza on 10/08/20.
 */
public class BudgetingNilaiDasarAction {
    private static transient Logger logger = Logger.getLogger(BudgetingNilaiDasarAction.class);
    private BudgetingNilaiDasarBo budgetingNilaiDasarBoProxy;
    private BudgetingNilaiDasar budgetingNilaiDasar;

    public BudgetingNilaiDasar getBudgetingNilaiDasar() {
        return budgetingNilaiDasar;
    }

    public void setBudgetingNilaiDasar(BudgetingNilaiDasar budgetingNilaiDasar) {
        this.budgetingNilaiDasar = budgetingNilaiDasar;
    }

    public void setBudgetingNilaiDasarBoProxy(BudgetingNilaiDasarBo budgetingNilaiDasarBoProxy) {
        this.budgetingNilaiDasarBoProxy = budgetingNilaiDasarBoProxy;
    }

    public String initForm() {
        setBudgetingNilaiDasar(new BudgetingNilaiDasar());
        return "search";
    }

    public CrudResponse searchByTahun(String tahun) {
        logger.info("[BudgetingNilaiDasarAction.searchByTahun] START >>>");
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingNilaiDasarBo budgetingNilaiDasarBo = (BudgetingNilaiDasarBo) ctx.getBean("budgetingNilaiDasarBoProxy");

        List<BudgetingNilaiDasar> budgetingNilaiDasars = new ArrayList<>();
        try {
            budgetingNilaiDasars = budgetingNilaiDasarBo.searchByTahun(tahun);
            response.setStatus("success");
        } catch (GeneralBOException e){
            logger.error("[BudgetingNilaiDasarAction.searchByTahun] ERROR. ", e);
            response.setMsg("[BudgetingNilaiDasarAction.searchByTahun] ERROR. "+ e);
            response.setStatus("error");
            return response;
        }
        response.setList(budgetingNilaiDasars);
        logger.info("[BudgetingNilaiDasarAction.searchByTahun] END <<<");
        return response;
    }

    public List<BudgetingNilaiDasar> getListDetailByTahun(String tahun) {
        logger.info("[BudgetingNilaiDasarAction.getListDetailByTahun] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingNilaiDasarBo budgetingNilaiDasarBo = (BudgetingNilaiDasarBo) ctx.getBean("budgetingNilaiDasarBoProxy");

        BudgetingNilaiDasar budgetingNilaiDasar = new BudgetingNilaiDasar();
        budgetingNilaiDasar.setTahun(tahun);

        List<BudgetingNilaiDasar> budgetingNilaiDasars = new ArrayList<>();
        try {
            budgetingNilaiDasars = budgetingNilaiDasarBo.getSearchByCriteria(budgetingNilaiDasar);
        } catch (GeneralBOException e){
            logger.error("[BudgetingNilaiDasarAction.getListDetailByTahun] ERROR. ", e);
        }

        logger.info("[BudgetingNilaiDasarAction.getListDetailByTahun] END <<<");
        return budgetingNilaiDasars;
    }

    public CrudResponse saveBudgetingNilaiDasar(String jsonString) throws JSONException {
        logger.info("[BudgetingAction.saveBudgetingNilaiDasar] START >>>");

        String userLogin = CommonUtil.userLogin();
        Timestamp times = new Timestamp(System.currentTimeMillis());
        CrudResponse response = new CrudResponse();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingNilaiDasarBo budgetingNilaiDasarBo = (BudgetingNilaiDasarBo) ctx.getBean("budgetingNilaiDasarBoProxy");

        List<BudgetingNilaiDasar> budgetingNilaiDasars = new ArrayList<>();
        JSONArray json = new JSONArray(jsonString);
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            BudgetingNilaiDasar budgetingNilaiDasar = new BudgetingNilaiDasar();

            if (!"".equalsIgnoreCase(obj.get("tahun").toString())){
                budgetingNilaiDasar.setTahun(obj.get("tahun").toString());
            }
            if (!"".equalsIgnoreCase(obj.get("id_nilai_dasar").toString())){
                budgetingNilaiDasar.setIdNilaiDasar(obj.get("id_nilai_dasar").toString());
            }
            if (obj.get("nilai") != null && !"".equalsIgnoreCase(obj.get("nilai").toString())){
                budgetingNilaiDasar.setNilai(new BigDecimal(obj.get("nilai").toString()));
            } else {
                budgetingNilaiDasar.setNilai(new BigDecimal(0));
            }
            budgetingNilaiDasar.setLastUpdate(times);
            budgetingNilaiDasar.setLastUpdateWho(userLogin);
            budgetingNilaiDasars.add(budgetingNilaiDasar);
        }

        try {
            budgetingNilaiDasarBo.saveTransBudgeting(budgetingNilaiDasars);
            response.setStatus("success");
        } catch (GeneralBOException e){
            logger.error("[BudgetingNilaiDasarAction.saveBudgetingNilaiDasar] ERROR. ", e);
            response.setMsg("[BudgetingNilaiDasarAction.saveBudgetingNilaiDasar] ERROR. "+ e);
            response.setStatus("error");
        }

        logger.info("[BudgetingAction.saveBudgetingNilaiDasar] END <<<");
        return response;
    }

    public List<BudgetingNilaiDasar> getListNilaiDasarAdd() {
        logger.info("[BudgetingNilaiDasarAction.getListNilaiDasarAdd] START >>>");

        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        String tahun = CommonUtil.getDateParted(date, "YEAR");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingNilaiDasarBo budgetingNilaiDasarBo = (BudgetingNilaiDasarBo) ctx.getBean("budgetingNilaiDasarBoProxy");

        BudgetingNilaiDasar budgetingNilaiDasar = new BudgetingNilaiDasar();
        budgetingNilaiDasar.setTahun(tahun);
        budgetingNilaiDasar.setFlag("Y");

        List<BudgetingNilaiDasar> budgetingNilaiDasars = new ArrayList<>();
        try {
            budgetingNilaiDasars = budgetingNilaiDasarBo.getListMasterNilaiDasarAdd(budgetingNilaiDasar);
        } catch (GeneralBOException e){
            logger.error("[BudgetingNilaiDasarAction.getListNilaiDasarAdd] ERROR. ", e);
        }

        logger.info("[BudgetingNilaiDasarAction.getListNilaiDasarAdd] END <<<");
        return budgetingNilaiDasars;
    }

    public List<BudgetingNilaiDasar> getListNilaiDasarEdit(String tahun) {
        logger.info("[BudgetingNilaiDasarAction.getListNilaiDasarEdit] START >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BudgetingNilaiDasarBo budgetingNilaiDasarBo = (BudgetingNilaiDasarBo) ctx.getBean("budgetingNilaiDasarBoProxy");

        BudgetingNilaiDasar budgetingNilaiDasar = new BudgetingNilaiDasar();
        budgetingNilaiDasar.setTahun(tahun);
        budgetingNilaiDasar.setFlag("Y");

        List<BudgetingNilaiDasar> budgetingNilaiDasars = new ArrayList<>();
        try {
            budgetingNilaiDasars = budgetingNilaiDasarBo.getTransNilaiDasarByTahun(budgetingNilaiDasar);
        } catch (GeneralBOException e){
            logger.error("[BudgetingNilaiDasarAction.getListNilaiDasarEdit] ERROR. ", e);
        }

        logger.info("[BudgetingNilaiDasarAction.getListNilaiDasarEdit] END <<<");
        return budgetingNilaiDasars;
    }
}
