package com.neurix.simrs.transaksi.teamdokter.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.common.util.FirebasePushNotif;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiBo;
import com.neurix.hris.transaksi.notifikasi.bo.NotifikasiFcmBo;
import com.neurix.hris.transaksi.notifikasi.bo.impl.NotifikasiBoImpl;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.notifikasi.model.NotifikasiFcm;
import com.neurix.simrs.transaksi.CrudResponse;
import com.neurix.simrs.transaksi.teamdokter.bo.TeamDokterBo;
import com.neurix.simrs.transaksi.teamdokter.model.DokterTeam;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TeamDokterAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(TeamDokterAction.class);
    private TeamDokterBo teamDokterBoProxy;
    private DokterTeam dokterTeam;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TeamDokterAction.logger = logger;
    }

    public TeamDokterBo getTeamDokterBoProxy() {
        return teamDokterBoProxy;
    }

    public void setTeamDokterBoProxy(TeamDokterBo teamDokterBoProxy) {
        this.teamDokterBoProxy = teamDokterBoProxy;
    }

    public DokterTeam getDokterTeam() {
        return dokterTeam;
    }

    public void setDokterTeam(DokterTeam dokterTeam) {
        this.dokterTeam = dokterTeam;
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

    public CrudResponse saveDokter(String idDetailCheckup, String idDokter, String pelayanan){
        logger.info("[TeamDokterAction.saveTindakanRawat] start process >>>");
        CrudResponse response = new CrudResponse();
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            DokterTeam dokterTeam = new DokterTeam();

            dokterTeam.setIdDetailCheckup(idDetailCheckup);
            dokterTeam.setIdDokter(idDokter);
            dokterTeam.setCreatedWho(userLogin);
            dokterTeam.setLastUpdate(updateTime);
            dokterTeam.setCreatedDate(updateTime);
            dokterTeam.setLastUpdateWho(userLogin);
            dokterTeam.setAction("C");
            dokterTeam.setFlag("Y");
            dokterTeam.setIdPelayanan(pelayanan);

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TeamDokterBo dokterBo = (TeamDokterBo) ctx.getBean("teamDokterBoProxy");

            response = dokterBo.savaAdd(dokterTeam);

        }catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("Error"+e.getMessage());
        }
        logger.info("[TeamDokterAction.saveDokter] end process >>>");
        return response;
    }

    public List<DokterTeam> listDokter(String idDetailCheckup){

        logger.info("[TeamDokterAction.listDokter] start process >>>");
        List<DokterTeam> dokterTeamList = new ArrayList<>();
        DokterTeam dokterTeam = new DokterTeam();
        dokterTeam.setIdDetailCheckup(idDetailCheckup);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TeamDokterBo dokterBo = (TeamDokterBo) ctx.getBean("teamDokterBoProxy");

        if(!"".equalsIgnoreCase(idDetailCheckup)){
            try {
                dokterTeamList = dokterBo.getByCriteria(dokterTeam);
            }catch (GeneralBOException e){
                logger.error("[TeamDokterAction.listDokter] Error when adding item ," + "Found problem when saving add data, please inform to your admin.", e);
                addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            }

            logger.info("[TeamDokterAction.listDokter] start process >>>");
            return dokterTeamList;
        }else{
            return null;
        }
    }
    public CrudResponse editDokter(String idTeamDokter, String idDoKter, String pelayanan){
        logger.info("[TeamDokterAction.editDokter] start process >>>");
        CrudResponse response = new CrudResponse();
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            DokterTeam dokterTeam = new DokterTeam();

            dokterTeam.setIdTeamDokter(idTeamDokter);
            dokterTeam.setIdDokter(idDoKter);
            dokterTeam.setLastUpdate(updateTime);
            dokterTeam.setLastUpdateWho(userLogin);
            dokterTeam.setAction("U");
            dokterTeam.setIdPelayanan(pelayanan);

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TeamDokterBo dokterBo = (TeamDokterBo) ctx.getBean("teamDokterBoProxy");

            response = dokterBo.saveEdit(dokterTeam);

        }catch (GeneralBOException e) {
            logger.error(e.getMessage());
            response.setMsg(e.getMessage());
            response.setStatus("error");

        }
        logger.info("[TeamDokterAction.editDokter] end process >>>");
        return response;
    }

    public CrudResponse saveDokterRequest(String idDetailCheckup, String idDokter, String pelayanan, String jenisDpjp){
        CrudResponse response = new CrudResponse();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        TeamDokterBo dokterBo = (TeamDokterBo) ctx.getBean("teamDokterBoProxy");
        NotifikasiBo notifikasiBo = (NotifikasiBo) ctx.getBean("notifikasiBoProxy");
        NotifikasiFcmBo notifikasiFcmBo = (NotifikasiFcmBo) ctx.getBean("notifikasiFcmBoProxy");
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            DokterTeam dokterTeam = new DokterTeam();
            dokterTeam.setIdDetailCheckup(idDetailCheckup);
            dokterTeam.setIdDokter(idDokter);
            dokterTeam.setCreatedWho(userLogin);
            dokterTeam.setLastUpdate(updateTime);
            dokterTeam.setCreatedDate(updateTime);
            dokterTeam.setLastUpdateWho(userLogin);
            dokterTeam.setAction("C");
            dokterTeam.setFlag("Y");
            dokterTeam.setIdPelayanan(pelayanan);
            dokterTeam.setJenisDpjp(jenisDpjp);
            response = dokterBo.saveDokterTeam(dokterTeam);

            //PUSH NOTIF

            List<NotifikasiFcm> resultNotif = new ArrayList<>();
            NotifikasiFcm beanNotif = new NotifikasiFcm();
            beanNotif.setUserId(idDokter);

            resultNotif = notifikasiFcmBo.getByCriteria(beanNotif);
            FirebasePushNotif.sendNotificationFirebase(resultNotif.get(0).getTokenFcm(), "Persetujuan " + jenisDpjp, "dr. meminta persetujuan untuk " + jenisDpjp, "SK", resultNotif.get(0).getOs(), null);

        }catch (GeneralBOException e) {
            response.setStatus("error");
            response.setMsg("Error"+e.getMessage());
        }
        return response;
    }
}