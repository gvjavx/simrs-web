package com.neurix.hris.transaksi.refreshLembur.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.lembur.bo.LemburBo;
import com.neurix.hris.transaksi.lembur.model.Lembur;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.refreshLembur.bo.RefreshLemburBo;
import com.neurix.hris.transaksi.refreshLembur.model.RefreshLembur;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;


/**
 * Created by Aji Noor on 22/07/2021
 */
public class RefreshLemburAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(RefreshLemburAction.class);

    private RefreshLembur refreshLembur;
    private RefreshLemburBo refreshLemburBoProxy;
    private BranchBo branchBoProxy;
    private LemburBo lemburBoProxy;
    private boolean admin = false;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public RefreshLembur getRefreshLembur() {
        return refreshLembur;
    }

    public void setRefreshLembur(RefreshLembur refreshLembur) {
        this.refreshLembur = refreshLembur;
    }

    public static Logger getLogger() {
        return logger;
    }

    public RefreshLemburBo getRefreshLemburBoProxy() {
        return refreshLemburBoProxy;
    }

    public void setRefreshLemburBoProxy(RefreshLemburBo refreshLemburBoProxy) {
        this.refreshLemburBoProxy = refreshLemburBoProxy;
    }

    public BranchBo getBranchBoProxy() {
        return branchBoProxy;
    }

    public void setBranchBoProxy(BranchBo branchBoProxy) {
        this.branchBoProxy = branchBoProxy;
    }

    public LemburBo getLemburBoProxy() {
        return lemburBoProxy;
    }

    public void setLemburBoProxy(LemburBo lemburBoProxy) {
        this.lemburBoProxy = lemburBoProxy;
    }

    @Override
    public String add() {
        logger.info("[RefreshLemburAction.add] start process >>>");
        RefreshLembur addRefreshLembur = new RefreshLembur();
        String branchId = CommonUtil.userBranchLogin();
        RefreshLembur data = new RefreshLembur();
        if (branchId != null) {
            data.setBranchId(branchId);
        } else {
            data.setBranchId("");
        }

        setRefreshLembur(data);
        setAddOrEdit(true);
        setAdd(true);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultRefreshLembur");

        logger.info("[RefreshLemburAction.add] stop process >>>");
        return "init_add";
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
        List<RefreshLembur> refreshLemburList = new ArrayList<>();
        RefreshLembur searchRefreshlembur = new RefreshLembur();
        searchRefreshlembur.setAbsensiPegawaiId(getId());
        searchRefreshlembur.setFlag(getFlag());
        try {
            refreshLemburList = refreshLemburBoProxy.getByCriteria(searchRefreshlembur);
        }catch (GeneralBOException e) {
            logger.error("[RefreshLemburAction.view] Error, " + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }
        for (RefreshLembur refreshLembur : refreshLemburList) {
            setRefreshLembur(refreshLembur);
        }
        return "init_view";
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public String search() {
        logger.info("[RefreshLemburAction.search] start process >>>");
        RefreshLembur searchRefreshLembur = getRefreshLembur();
        List<RefreshLembur> listOfSearchRefreshlembur = new ArrayList();
        String branchId = CommonUtil.userBranchLogin();
        searchRefreshLembur.setBranchId(branchId);
        String role = CommonUtil.roleAsLogin();

        if ("ADMIN".equalsIgnoreCase(role)){
            setAdmin(true);
        }

        try {
            listOfSearchRefreshlembur = refreshLemburBoProxy.getByCriteria(searchRefreshLembur);
        } catch (GeneralBOException e) {
            logger.error("[RefreshLemburAction.search] Error, " + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultRefreshLembur");
        session.setAttribute("listOfResultRefreshLembur", listOfSearchRefreshlembur);

        logger.info("[RefreshLemburAction.search] end process <<<");

        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[RefreshLemburAction.initForm] start process >>>");
        HttpSession session = ServletActionContext.getRequest().getSession();
        String branchId = CommonUtil.userBranchLogin();
        RefreshLembur data = new RefreshLembur();
        if (branchId != null) {
            data.setBranchId(branchId);
        } else {
            data.setBranchId("");
        }

        setRefreshLembur(data);
        session.removeAttribute("listOfResultRefreshLembur");
        session.removeAttribute("listOfResultRefreshLembur");
        logger.info("[RefreshLemburAction.initForm] end process >>>");
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

    public String saveAdd(){
        logger.info("[RefreshLemburAction.saveAdd] start process >>>");

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");

        RefreshLembur refreshLembur = getRefreshLembur();
        String userLogin = CommonUtil.userLogin();
        Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        java.sql.Date date = CommonUtil.convertToDate(refreshLembur.getStTanggal());

        Boolean chance = false;
        if(refreshLembur.getJmlChance() > 0){
            chance = true;
        }

        List<Lembur> lemburList = new ArrayList<>();
        Lembur lemburCriteria = new Lembur();

        lemburCriteria.setBranchId(refreshLembur.getBranchId());
        lemburCriteria.setStTanggalAwal(refreshLembur.getStTanggal());
        lemburCriteria.setStTanggalAkhir(refreshLembur.getStTanggal());
        lemburCriteria.setApprovalFlag("Y");
        lemburCriteria.setFlag("Y");

        try {
            lemburList = lemburBoProxy.getByCriteria(lemburCriteria);
        }catch (GeneralBOException e){
            logger.error("[RefreshLemburAction.saveAdd] Error, " + e.getMessage());
            throw new GeneralBOException(e.getMessage());
        }

        if(lemburList.size() > 0) {
            String resultRefresh = "";
            try {
                resultRefresh = refreshLemburBoProxy.refreshAbsensiLembur(lemburList,refreshLembur.getTanggal(), chance);
            } catch (GeneralBOException e) {
                logger.error("[RefreshLemburAction.saveAdd] Error, " + e.getMessage());
                throw new GeneralBOException(e.getMessage());
            }

            if("success".equalsIgnoreCase(resultRefresh)){
                try{
                    branchBoProxy.setChanceRefreshLembur(refreshLembur.getBranchId(),refreshLembur.getJmlChance()-1);
                }catch (GeneralBOException e) {
                    logger.error("[RefreshLemburAction.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException(e.getMessage());
                }
            }else if("needApprove".equalsIgnoreCase(resultRefresh)){
//                try {
//                    List<Notifikasi> notifRefreshLembur = refreshLembur.saveAddLembur(lembur);
//                    for (Notifikasi notifikasi : notifRefreshLembur) {
//                        notifikasiBo.sendNotif(notifikasi);
//                    }
//                } catch (GeneralBOException e) {
//                    logger.error("[RefreshLemburAction.saveAdd] Error, " + e.getMessage());
//                    throw new GeneralBOException(e.getMessage());
//                }
            }else {
                logger.error("[RefreshLemburAction.saveAdd] Tidak ada absensi lembur yang perlu diRefresh pada tanggal tersebut.");
                throw new GeneralBOException("Tidak ditemukan data absensi lembur yang perlu diRefresh pada tanggal tersebut.");
            }


        }else {
            logger.error("[RefreshLemburAction.saveAdd] Tidak ada pengajuan lembur terapprove pada tanggal tersebut.");
            throw new GeneralBOException("Tidak ditemukan data lembur terApprove pada tanggal tersebut.");
        }

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResultRefreshLembur");

        logger.info("[RefreshLemburAction.saveAdd] end process >>>");
        return "success_save_add";
    }
}
