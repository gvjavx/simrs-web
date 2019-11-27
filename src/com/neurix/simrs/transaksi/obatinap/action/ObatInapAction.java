package com.neurix.simrs.transaksi.obatinap.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.Obat;
import com.neurix.simrs.transaksi.obatinap.bo.ObatInapBo;
import com.neurix.simrs.transaksi.obatinap.model.ObatInap;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ObatInapAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(ObatInapAction.class);
    private ObatInapBo obatInapBoProxy;
    private ObatBo obatBoProxy;

    public static Logger getLogger() {
        return logger;
    }

    public void setObatInapBoProxy(ObatInapBo obatInapBoProxy) {
        this.obatInapBoProxy = obatInapBoProxy;
    }

    public void setObatBoProxy(ObatBo obatBoProxy) {
        this.obatBoProxy = obatBoProxy;
    }

    @Override
    public String add() {
        return null;
    }

    @Override
    public String edit() {
        return null;
    }

    @Override
    public String delete() {
        return null;
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
        return null;
    }

    @Override
    public String initForm() {
        return null;
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }

    public String saveObatInap(String idDetailCheckup,String idObat, BigInteger qty){
        logger.info("[ObatInapAction.saveObatInap] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            String userArea = CommonUtil.userBranchLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());

            List<Obat> obatList = new ArrayList<>();
            Obat obat = new Obat();
            obat.setIdObat(idObat);

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

            try {
                obatList = obatBo.getByCriteria(obat);
            }catch (GeneralBOException e){
                logger.error("[ObatInapAction.saveObatInap] Error when search tarif dan decs tindakan by id ," + "Found problem when saving add data, please inform to your admin.", e);
            }

            Obat obatResult = new Obat();
            if (!obatList.isEmpty()){
                obatResult = obatList.get(0);
            }

            ObatInap obatInap = new ObatInap();
            obatInap.setIdObat(idObat);
            obatInap.setIdDetailCheckup(idDetailCheckup);
            obatInap.setNamaObat(obatResult.getNamaObat());
            obatInap.setQty(qty);
            obatInap.setHarga(obatResult.getHarga());
            obatInap.setTotalHarga(obatResult.getHarga().multiply(qty));
            obatInap.setCreatedWho(userLogin);
            obatInap.setLastUpdate(updateTime);
            obatInap.setCreatedDate(updateTime);
            obatInap.setLastUpdateWho(userLogin);
            obatInap.setAction("C");
            obatInap.setFlag("Y");

            ObatInapBo obatInapBo = (ObatInapBo) ctx.getBean("obatInapBoProxy");
            obatInapBo.saveAdd(obatInap);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[ObatInapAction.saveObatInap] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }

        logger.info("[ObatInapAction.saveObatInap] end process >>>");
        return SUCCESS;
    }

    public List<ObatInap> listObatInap(String idDetailCheckup){

        logger.info("[ObatInapAction.listObatInap] start process >>>");
        List<ObatInap> obatInapList = new ArrayList<>();

        ObatInap obatInap = new ObatInap();
        obatInap.setIdDetailCheckup(idDetailCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatInapBo obatInapBo = (ObatInapBo) ctx.getBean("obatInapBoProxy");

        if(!"".equalsIgnoreCase(idDetailCheckup)){
            try {
                obatInapList = obatInapBo.getByCriteria(obatInap);
            }catch (GeneralBOException e){
                logger.info("[ObatInapAction.saveObatInap] error when search list of obat inap, "+ e.getMessage());
            }
            logger.info("[ObatInapAction.listObatInap] end process >>>");
            return obatInapList;

        }else{
            return null;
        }
    }
}