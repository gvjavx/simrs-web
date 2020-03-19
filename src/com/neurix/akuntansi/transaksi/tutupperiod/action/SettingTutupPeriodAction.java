package com.neurix.akuntansi.transaksi.tutupperiod.action;

import com.neurix.akuntansi.transaksi.tutupperiod.bo.TutupPeriodBo;
import com.neurix.akuntansi.transaksi.tutupperiod.model.BatasTutupPeriod;
import com.neurix.akuntansi.transaksi.tutupperiod.model.ItSimrsBatasTutupPeriodEntity;
import com.neurix.akuntansi.transaksi.tutupperiod.model.TutupPeriod;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.transaksiobat.model.ImtSimrsTransaksiObatDetailEntity;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by reza on 18/03/20.
 */
public class SettingTutupPeriodAction extends BaseTransactionAction {
    private static transient Logger logger = Logger.getLogger(SettingTutupPeriodAction.class);

    private TutupPeriodBo tutupPeriodBoProxy;

    private TutupPeriod tutupPeriod;

    public void setTutupPeriodBoProxy(TutupPeriodBo tutupPeriodBoProxy) {
        this.tutupPeriodBoProxy = tutupPeriodBoProxy;
    }

    public TutupPeriod getTutupPeriod() {
        return tutupPeriod;
    }

    public void setTutupPeriod(TutupPeriod tutupPeriod) {
        this.tutupPeriod = tutupPeriod;
    }

    @Override
    public String search() {
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[SettingTutupPeriodAction.initForm] START >>>");
        logger.info("[SettingTutupPeriodAction.initForm] END <<<");
        return SUCCESS;
    }

    public CrudResponse saveBatasTutupPeriod(String jsonString) throws JSONException{

        CrudResponse response = new CrudResponse();
        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        JSONArray json = new JSONArray(jsonString);

        List<ItSimrsBatasTutupPeriodEntity> batasTutupPeriodEntities = new ArrayList<>();
        ItSimrsBatasTutupPeriodEntity batasTutupPeriodEntity;
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            batasTutupPeriodEntity = new ItSimrsBatasTutupPeriodEntity();
            batasTutupPeriodEntity.setTahun(obj.getString("tahun"));
            batasTutupPeriodEntity.setBulan(String.valueOf(obj.getInt("bulan")));
            batasTutupPeriodEntity.setUnit(obj.getString("unit"));
            if (!"".equalsIgnoreCase(obj.getString("tgl"))){
                Date tgl = Date.valueOf(obj.getString("tgl"));
                batasTutupPeriodEntity.setTglBatas(tgl);
            }
            batasTutupPeriodEntity.setFlag("Y");
            batasTutupPeriodEntity.setAction("C");
            batasTutupPeriodEntity.setCreatedDate(time);
            batasTutupPeriodEntity.setCreatedWho(userLogin);
            batasTutupPeriodEntity.setLastUpdate(time);
            batasTutupPeriodEntity.setLastUpdateWho(userLogin);
            batasTutupPeriodEntities.add(batasTutupPeriodEntity);
        }

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TutupPeriodBo tutupPeriodBo = (TutupPeriodBo) ctx.getBean("tutupPeriodBoProxy");

        try {
            tutupPeriodBo.saveSettingPeriod(batasTutupPeriodEntities);
            response.setStatus("success");
        } catch (GeneralBOException e){
            logger.error("[SettingTutupPeriodAction.saveBatasTutupPeriod] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("[SettingTutupPeriodAction.saveBatasTutupPeriod] ERROR. "+e);
            return response;
        }

        return response;
    }

    public BatasTutupPeriod getBatasTutupPeriod(String unit, String tahun, String bulan){

        BatasTutupPeriod batasTutupPeriod = new BatasTutupPeriod();
        batasTutupPeriod.setUnit(unit);
        batasTutupPeriod.setTahun(tahun);
        batasTutupPeriod.setBulan(bulan);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TutupPeriodBo tutupPeriodBo = (TutupPeriodBo) ctx.getBean("tutupPeriodBoProxy");

        List<ItSimrsBatasTutupPeriodEntity> batasTutupPeriodEntities = tutupPeriodBo.getListEntityBatasTutupPeriode(batasTutupPeriod);
        if (batasTutupPeriodEntities.size() > 0){

            ItSimrsBatasTutupPeriodEntity periodEntity = batasTutupPeriodEntities.get(0);

            batasTutupPeriod = new BatasTutupPeriod();
            batasTutupPeriod.setId(periodEntity.getId());
            batasTutupPeriod.setUnit(periodEntity.getUnit());
            batasTutupPeriod.setBulan(periodEntity.getBulan());
            batasTutupPeriod.setTahun(periodEntity.getTahun());
            batasTutupPeriod.setTglBatas(periodEntity.getTglBatas());
            batasTutupPeriod.setFlag(periodEntity.getFlag());
            batasTutupPeriod.setAction(periodEntity.getAction());
            batasTutupPeriod.setLastUpdate(periodEntity.getLastUpdate());
            batasTutupPeriod.setLastUpdateWho(periodEntity.getLastUpdateWho());
            batasTutupPeriod.setCreatedDate(periodEntity.getCreatedDate());
            batasTutupPeriod.setCreatedWho(periodEntity.getCreatedWho());
            batasTutupPeriod.setStTglBatas(periodEntity.getTglBatas() == null ? "" : periodEntity.getTglBatas().toString());
            batasTutupPeriod.setFlagTutup(periodEntity.getFlagTutup());
            return batasTutupPeriod;
        }

        return new BatasTutupPeriod();
    }
}
