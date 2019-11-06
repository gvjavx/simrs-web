package com.neurix.hris.master.dashboardAbsensi.action;

//import com.neurix.authorization.company.bo.AreaBo;
import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.dashboard.model.Dashboard;
import com.neurix.hris.master.dashboardAbsensi.bo.DashboardAbsensiBo;
import com.neurix.hris.master.dashboardAbsensi.model.DashboardAbsensi;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ferdi on 05/02/2015.
 */

public class DashboardAbsensiAction extends BaseMasterAction{
    protected static transient Logger logger = Logger.getLogger(DashboardAbsensiAction.class);
    private DashboardAbsensiBo dashboardAbsensiBoProxy;
    private DashboardAbsensi dashboardAbsensi;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        DashboardAbsensiAction.logger = logger;
    }

    public DashboardAbsensiBo getDashboardAbsensiBoProxy() {
        return dashboardAbsensiBoProxy;
    }

    public void setDashboardAbsensiBoProxy(DashboardAbsensiBo dashboardAbsensiBoProxy) {
        this.dashboardAbsensiBoProxy = dashboardAbsensiBoProxy;
    }

    public DashboardAbsensi getDashboardAbsensi() {
        return dashboardAbsensi;
    }

    public void setDashboardAbsensi(DashboardAbsensi dashboardAbsensi) {
        this.dashboardAbsensi = dashboardAbsensi;
    }

    @Override
    public String add() {
        logger.info("[DashboardAction.add] start process >>>");
        DashboardAbsensi addGolongan = new DashboardAbsensi();
        setDashboardAbsensi(addGolongan);
        setAddOrEdit(true);
        setAdd(true);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[DashboardAction.add] stop process >>>");
        return "init_add";
    }

    @Override
    public String edit() {
        return "";
    }

    @Override
    public String delete() {
        return "init_delete";
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
        return SUCCESS;
    }

    @Override
    public String initForm() {
        logger.info("[DashboardAbsensiAction.initForm] start process >>>");

        logger.info("[DashboardAbsensiAction.initForm] end process >>>");
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

    public List<DashboardAbsensi> getKaryawanAbsensiTelat(String tanggal){
        List<DashboardAbsensi> listHasil = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DashboardAbsensiBo dashboardAbsensiBo = (DashboardAbsensiBo) ctx.getBean("dashboardAbsensiBoProxy");

        listHasil = dashboardAbsensiBo.getKaryawanAbsensiTelat(tanggal);
        return listHasil;
    }

    public List<DashboardAbsensi> getKaryawanAbsensiMangkir(String tanggal){
        List<DashboardAbsensi> listHasil = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DashboardAbsensiBo dashboardAbsensiBo = (DashboardAbsensiBo) ctx.getBean("dashboardAbsensiBoProxy");

        listHasil = dashboardAbsensiBo.getKaryawanAbsensiMangkir(tanggal);
        return listHasil;
    }

    public List<DashboardAbsensi> getKaryawanAbsensiRajin(String tanggal){
        List<DashboardAbsensi> listHasil = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DashboardAbsensiBo dashboardAbsensiBo = (DashboardAbsensiBo) ctx.getBean("dashboardAbsensiBoProxy");

        listHasil = dashboardAbsensiBo.getKaryawanAbsensiRajin(tanggal);
        return listHasil;
    }

    public List<DashboardAbsensi> getKaryawanTelat(String bulan, String tahun){
        List<DashboardAbsensi> listHasil = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DashboardAbsensiBo dashboardAbsensiBo = (DashboardAbsensiBo) ctx.getBean("dashboardAbsensiBoProxy");

        listHasil = dashboardAbsensiBo.getKaryawanTelat(bulan, tahun);
        return listHasil;
    }

    public List<DashboardAbsensi> getKaryawanTelat(String bulan, String tahun, String nip){
        List<DashboardAbsensi> listHasil = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DashboardAbsensiBo dashboardAbsensiBo = (DashboardAbsensiBo) ctx.getBean("dashboardAbsensiBoProxy");

        listHasil = dashboardAbsensiBo.getKaryawanTelat(bulan, tahun, nip);
        return listHasil;
    }

    // medical records
    public List<DashboardAbsensi> getDashboardMedical(String bulan, String tahun){
        List<DashboardAbsensi> listHasil = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DashboardAbsensiBo dashboardAbsensiBo = (DashboardAbsensiBo) ctx.getBean("dashboardAbsensiBoProxy");

        listHasil = dashboardAbsensiBo.dashboardMedical(bulan, tahun);
        return listHasil;
    }

    // Lembur
    public List<DashboardAbsensi> getDashboardLembur(String bulan, String tahun){
        List<DashboardAbsensi> listHasil = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DashboardAbsensiBo dashboardAbsensiBo = (DashboardAbsensiBo) ctx.getBean("dashboardAbsensiBoProxy");

        listHasil = dashboardAbsensiBo.dashboardLembur(bulan, tahun);
        return listHasil;
    }

    // Lembur detail bidang / divisi
    public List<DashboardAbsensi> getDashboardLemburDetailDivisi(String bulan, String tahun, String divisiId){
        List<DashboardAbsensi> listHasil = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DashboardAbsensiBo dashboardAbsensiBo = (DashboardAbsensiBo) ctx.getBean("dashboardAbsensiBoProxy");

        listHasil = dashboardAbsensiBo.dashboardLembur(bulan, tahun, divisiId);
        return listHasil;
    }

    // medical record detail bidang / divisi
    public List<DashboardAbsensi> getDashboardMedicalDetailDivisi(String bulan, String tahun, String divisiId){
        List<DashboardAbsensi> listHasil = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DashboardAbsensiBo dashboardAbsensiBo = (DashboardAbsensiBo) ctx.getBean("dashboardAbsensiBoProxy");

        listHasil = dashboardAbsensiBo.dashboardMedical(bulan, tahun, divisiId);
        return listHasil;
    }

    // medical record detail top 10
    public List<DashboardAbsensi> dashboardMedicalRecordsTop(String bulan, String tahun, String divisiName){
        List<DashboardAbsensi> listHasil = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DashboardAbsensiBo dashboardAbsensiBo = (DashboardAbsensiBo) ctx.getBean("dashboardAbsensiBoProxy");

        listHasil = dashboardAbsensiBo.dashboardMedicalRecordsTop(bulan, tahun, divisiName);
        return listHasil;
    }

    // medical record detail top 10
    public List<DashboardAbsensi> dashboardLemburTop(String bulan, String tahun, String divisiName){
        List<DashboardAbsensi> listHasil = new ArrayList<>();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        DashboardAbsensiBo dashboardAbsensiBo = (DashboardAbsensiBo) ctx.getBean("dashboardAbsensiBoProxy");

        listHasil = dashboardAbsensiBo.dashboardLemburTop(bulan, tahun, divisiName);
        return listHasil;
    }


}
