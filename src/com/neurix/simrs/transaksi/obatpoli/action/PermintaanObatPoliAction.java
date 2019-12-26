package com.neurix.simrs.transaksi.obatpoli.action;

import com.neurix.common.action.BaseTransactionAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.obatpoli.bo.ObatPoliBo;
import com.neurix.simrs.transaksi.obatpoli.model.ObatPoli;
import com.neurix.simrs.transaksi.obatpoli.model.PermintaanObatPoli;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Toshiba on 12/12/2019.
 */
public class PermintaanObatPoliAction extends BaseTransactionAction{
    private static transient Logger logger = Logger.getLogger(PermintaanObatPoliAction.class);
    private ObatPoliBo obatPoliBoProxy;
    private PermintaanObatPoli permintaanObatPoli;
    private ObatPoli obatPoli;

    @Override
    public String search() {
        logger.info("[PermintaanObatPoliAction.search] START >>>>>>>");

        PermintaanObatPoli permintaanObatPoli = getPermintaanObatPoli();
        permintaanObatPoli.setBranchId(CommonUtil.userBranchLogin());
        permintaanObatPoli.setTujuanPelayanan(CommonUtil.userPelayananIdLogin());
        boolean isPoli = false;

        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList<>();

        try {
            permintaanObatPoliList = obatPoliBoProxy.getSearchPermintaanObatPoli(permintaanObatPoli, isPoli);
        } catch (HibernateException e){
            logger.error("[PermintaanObatPoliAction.search] ERROR when get data list obat, ", e);
            addActionError("[PermintaanObatPoliAction.search] ERROR when get data list obat, "+e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");
        session.setAttribute("listOfResult", permintaanObatPoliList);

        logger.info("[PermintaanObatPoliAction.search] END <<<<<<<");
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[PermintaanObatPoliAction.initForm] start process >>>");
        PermintaanObatPoli permintaanObatPoli = new PermintaanObatPoli();

        setPermintaanObatPoli(permintaanObatPoli);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PermintaanObatPoliAction.initForm] end process <<<");
        return "search";
    }

    public List<PermintaanObatPoli> listDetailPermintaan(String idPermintaan, boolean isPoli, String idTujuan){

        logger.info("[PermintaanObatPoliAction.listDetailPermintaan] start process >>>");
        List<PermintaanObatPoli> permintaanObatPoliList = new ArrayList<>();
        PermintaanObatPoli permintaanObatPoli = new PermintaanObatPoli();
        permintaanObatPoli.setIdPermintaanObatPoli(idPermintaan);
        permintaanObatPoli.setTujuanPelayanan(idTujuan);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatPoliBo obatPoliBo = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");

        if(!"".equalsIgnoreCase(idPermintaan)){
            try {
                permintaanObatPoliList = obatPoliBo.getDetailLitsPermintaan(permintaanObatPoli, isPoli);
            }catch (GeneralBOException e){
                logger.error("[PermintaanObatPoliAction.listDetailPermintaan] Error when search data detail permintaan ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when search data detail permintaan, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[PermintaanObatPoliAction.listDetailPermintaan] start process >>>");
            return permintaanObatPoliList;

        }else{
            return null;
        }
    }

    public String saveKonfirmasiRequest(String request, String idPermintaan, boolean isPoli){
        logger.info("[PermintaanObatPoliAction.saveKonfirmasiRequest] START process >>>");
        try {
            String userLogin        = CommonUtil.userLogin();
            Timestamp updateTime    = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String branchId         = CommonUtil.userBranchLogin();
            ApplicationContext ctx  = ContextLoader.getCurrentWebApplicationContext();
            ObatPoliBo obatPoliBo   = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");


            PermintaanObatPoli obatPoli = new PermintaanObatPoli();
            obatPoli.setIdPermintaanObatPoli(idPermintaan);
            obatPoli.setLastUpdate(updateTime);
            obatPoli.setLastUpdateWho(userLogin);
            obatPoli.setBranchId(branchId);
            try {
                obatPoliBo.saveApproveRequest(obatPoli, request, isPoli);
            }catch (JSONException e){
                logger.error("[PermintaanResepAction.saveResepPasien] Error when sabe resep obat", e);
            }

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PermintaanObatPoliAction.saveKonfirmasiRequest] ERROR when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            return e.getMessage();
        }

        logger.info("[PermintaanObatPoliAction.saveKonfirmasiRequest] END process <<<");

        return SUCCESS;
    }

    public String saveKonfirmasiReture(String idPermintaan, boolean isPoli){
        logger.info("[PermintaanObatPoliAction.saveKonfirmasiReture] START process >>>");
        try {
            String userLogin        = CommonUtil.userLogin();
            Timestamp updateTime    = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String branchId         = CommonUtil.userBranchLogin();
            ApplicationContext ctx  = ContextLoader.getCurrentWebApplicationContext();
            ObatPoliBo obatPoliBo   = (ObatPoliBo) ctx.getBean("obatPoliBoProxy");


            PermintaanObatPoli obatPoli = new PermintaanObatPoli();
            obatPoli.setIdPermintaanObatPoli(idPermintaan);
            obatPoli.setLastUpdate(updateTime);
            obatPoli.setLastUpdateWho(userLogin);
            obatPoli.setBranchId(branchId);

            obatPoliBo.saveApproveReture(obatPoli, isPoli);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[PermintaanObatPoliAction.saveKonfirmasiReture] ERROR when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            return e.getMessage();
        }

        logger.info("[PermintaanObatPoliAction.saveKonfirmasiRequest] END process <<<");

        return SUCCESS;
    }


    public void setObatPoliBoProxy(ObatPoliBo obatPoliBoProxy) {
        this.obatPoliBoProxy = obatPoliBoProxy;
    }

    public PermintaanObatPoli getPermintaanObatPoli() {
        return permintaanObatPoli;
    }

    public void setPermintaanObatPoli(PermintaanObatPoli permintaanObatPoli) {
        this.permintaanObatPoli = permintaanObatPoli;
    }

    public ObatPoli getObatPoli() {
        return obatPoli;
    }

    public void setObatPoli(ObatPoli obatPoli) {
        this.obatPoli = obatPoli;
    }
}
