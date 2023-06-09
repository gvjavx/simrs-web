package com.neurix.simrs.transaksi.initdashboard.action;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.initdashboard.bo.InitDashboardBo;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class InitDashboardAction {
    public static transient Logger logger = Logger.getLogger(InitDashboardAction.class);
    public HeaderCheckup getCountAll(String bulan, String tahun){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        InitDashboardBo initDashboardBo = (InitDashboardBo) ctx.getBean("initDashboardBoProxy");
        HeaderCheckup headerCheckupList = new HeaderCheckup();
        try {
            headerCheckupList = initDashboardBo.getCountAll(bulan, tahun, null);
        }catch (GeneralBOException e){
            logger.error(e.getMessage());
        }
        return headerCheckupList;
    }

    public List<HeaderCheckup> getKunjuganRJ(String bulan, String tahun, String branch, String jenisKunjungan){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        InitDashboardBo initDashboardBo = (InitDashboardBo) ctx.getBean("initDashboardBoProxy");
        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        if(bulan != null && !"".equalsIgnoreCase(bulan) && tahun != null && !"".equalsIgnoreCase(tahun)){
            try {
                headerCheckupList = initDashboardBo.getKunjunganRJ(bulan, tahun, branch, jenisKunjungan);
            }catch (GeneralBOException e){
                logger.error(e.getMessage());
            }

        }
        return headerCheckupList;
    }

    public List<HeaderCheckup> getDetailKunjuganRJ(String bulan, String tahun, String branch, String jenisKunjungan){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        InitDashboardBo initDashboardBo = (InitDashboardBo) ctx.getBean("initDashboardBoProxy");
        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        if(bulan != null && !"".equalsIgnoreCase(bulan) && tahun != null && !"".equalsIgnoreCase(tahun)){
            try {
                headerCheckupList = initDashboardBo.getDetailKunjunganRJ(bulan, tahun, branch, jenisKunjungan);
            }catch (GeneralBOException e){
                logger.error(e.getMessage());
            }

        }
        return headerCheckupList;
    }

    public List<Branch> getComboBranch() {
        List<Branch> branchList = new ArrayList<>();
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
        Branch branch = new Branch();
        if(!"ADMIN KP".equalsIgnoreCase(CommonUtil.roleAsLogin())){
            branch.setBranchId(CommonUtil.userBranchLogin());
        }
        branch.setFlag("Y");
        branch.setNotLike("KP");

        try {
            branchList = branchBo.getByCriteria(branch);
        } catch (GeneralBOException e) {
            logger.error("[InitDashboardAction.getComboBranch] Error when searching data by criteria, Found problem when searching data by criteria, please inform to your admin.", e);
        }
        return branchList;
    }

    public List<HeaderCheckup> getTahunPeriksa(){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        InitDashboardBo initDashboardBo = (InitDashboardBo) ctx.getBean("initDashboardBoProxy");
        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        try {
            headerCheckupList = initDashboardBo.getTahunPeriksa();
        }catch (GeneralBOException e){
            logger.error(e.getMessage());
        }
        return headerCheckupList;
    }

    public List<HeaderCheckup> getKamarTerpakai(String bulan, String tahun, String branch){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        InitDashboardBo initDashboardBo = (InitDashboardBo) ctx.getBean("initDashboardBoProxy");
        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        if(bulan != null && !"".equalsIgnoreCase(bulan) && tahun != null && !"".equalsIgnoreCase(tahun)){
            try {
                headerCheckupList = initDashboardBo.getKamarTerpakai(bulan, tahun, branch);
            }catch (GeneralBOException e){
                logger.error(e.getMessage());
            }

        }
        return headerCheckupList;
    }

    public List<HeaderCheckup> getDetailKunjuganJK(String bulan, String tahun, String branch, String jenisKunjungan){
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        InitDashboardBo initDashboardBo = (InitDashboardBo) ctx.getBean("initDashboardBoProxy");
        List<HeaderCheckup> headerCheckupList = new ArrayList<>();
        if(bulan != null && !"".equalsIgnoreCase(bulan) && tahun != null && !"".equalsIgnoreCase(tahun)){
            try {
                headerCheckupList = initDashboardBo.getDetailKunjunganJK(bulan, tahun, branch, jenisKunjungan);
            }catch (GeneralBOException e){
                logger.error(e.getMessage());
            }

        }
        return headerCheckupList;
    }
    public static Logger getLogger() {
        return logger;
    }

    public String initForm(){
        return "init";
    }
}
