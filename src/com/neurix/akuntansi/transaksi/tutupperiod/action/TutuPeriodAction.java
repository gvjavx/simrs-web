package com.neurix.akuntansi.transaksi.tutupperiod.action;

import com.neurix.akuntansi.transaksi.tutupperiod.bo.TutupPeriodBo;
import com.neurix.akuntansi.transaksi.tutupperiod.model.BatasTutupPeriod;
import com.neurix.akuntansi.transaksi.tutupperiod.model.ItSimrsBatasTutupPeriodEntity;
import com.neurix.akuntansi.transaksi.tutupperiod.model.TutupPeriod;
import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by reza on 18/03/20.
 */
public class TutuPeriodAction extends BaseTransactionAction {
    private static transient Logger logger = Logger.getLogger(TutuPeriodAction.class);

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
        logger.info("[TutuPeriodAction.initForm] START >>>");
        logger.info("[TutuPeriodAction.initForm] END >>>");
        return SUCCESS;
    }

    public BatasTutupPeriod getDataBatasTutupPeriod(String unit, String tahun, String bulan){

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

    public CrudResponse saveTutupPeriod(String unit, String tahun, String bulan){
        CrudResponse response = new CrudResponse();

        String userLogin = CommonUtil.userLogin();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TutupPeriodBo tutupPeriodBo = (TutupPeriodBo) ctx.getBean("tutupPeriodBoProxy");

        TutupPeriod tutupPeriod = new TutupPeriod();
        tutupPeriod.setUnit(unit);
        tutupPeriod.setTahun(tahun);
        tutupPeriod.setBulan(bulan);
        tutupPeriod.setFlagTutup("Y");
        tutupPeriod.setFlag("Y");
        tutupPeriod.setCreatedDate(time);
        tutupPeriod.setCreatedWho(userLogin);
        tutupPeriod.setLastUpdate(time);
        tutupPeriod.setLastUpdateWho(userLogin);

        try {
            tutupPeriodBo.saveUpdateTutupPeriod(tutupPeriod);
            response.setStatus("success");
        } catch (GeneralBOException e){
            logger.error("[TutupPeriodAction.saveTutupPeriod] ERROR. ", e);
            response.setStatus("error");
            response.setMsg("[TutupPeriodAction.saveTutupPeriod] ERROR. "+e);
            return response;
        }
        return response;
    }
}
