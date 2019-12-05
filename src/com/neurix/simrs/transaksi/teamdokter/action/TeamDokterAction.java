package com.neurix.simrs.transaksi.teamdokter.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
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

    public String saveDokter(String idDetailCheckup, String idDokter){
        logger.info("[TeamDokterAction.saveTindakanRawat] start process >>>");
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

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TeamDokterBo dokterBo = (TeamDokterBo) ctx.getBean("teamDokterBoProxy");

            dokterBo.savaAdd(dokterTeam);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[TeamDokterAction.saveDokter] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        logger.info("[TeamDokterAction.saveDokter] end process >>>");
        return SUCCESS;
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
    public String editDokter(String idTeamDokter, String idDoKter){
        logger.info("[TeamDokterAction.editDokter] start process >>>");
        try {
            String userLogin = CommonUtil.userLogin();
            Timestamp updateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
            DokterTeam dokterTeam = new DokterTeam();

            dokterTeam.setIdTeamDokter(idTeamDokter);
            dokterTeam.setIdDokter(idDoKter);
            dokterTeam.setLastUpdate(updateTime);
            dokterTeam.setLastUpdateWho(userLogin);
            dokterTeam.setAction("U");

            ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            TeamDokterBo dokterBo = (TeamDokterBo) ctx.getBean("teamDokterBoProxy");

            dokterBo.saveEdit(dokterTeam);

        }catch (GeneralBOException e) {
            Long logId = null;
            logger.error("[TeamDokterAction.editDokter] Error when adding item ," + "[" + logId + "] Found problem when saving add data, please inform to your admin.", e);
            addActionError("Error, " + "[code=" + logId + "] Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
            return ERROR;
        }
        logger.info("[TeamDokterAction.editDokter] end process >>>");
        return SUCCESS;
    }
}