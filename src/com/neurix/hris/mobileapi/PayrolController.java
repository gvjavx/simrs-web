package com.neurix.hris.mobileapi;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.mobileapi.model.PayrollPayment;
import com.neurix.hris.transaksi.payroll.bo.PayrollBo;
import com.neurix.hris.transaksi.payroll.model.ItHrisPayrollEntity;
import com.neurix.hris.transaksi.payroll.model.ItPayrollEntity;
import com.opensymphony.xwork2.ModelDriven;
import io.agora.recording.common.Common;
import net.sf.jasperreports.engine.*;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.ContextLoader;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gondok
 * Sunday, 31/03/19 21:27
 */
public class PayrolController implements ModelDriven<PayrollPayment> {

    private DataSource dataSource;
    private String idPayroll;
    private String tipePayroll;
    private String branchId;
    private PayrollPayment model;
    private PayrollBo payrollBoProxy;

    public PayrollBo getPayrollBoProxy() {
        return payrollBoProxy;
    }

    public void setPayrollBoProxy(PayrollBo payrollBoProxy) {
        this.payrollBoProxy = payrollBoProxy;
    }

    public void setDataSource(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public HttpHeaders create() {
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ItHrisPayrollEntity payroll = new ItHrisPayrollEntity(); //RAKA-30APR2021==> merubah ItPayrollEntity -> ItHrisPayrollEntity
        com.neurix.authorization.company.model.Branch branch = new Branch();
        Map reportParams=new HashMap();

        String stTanggal = CommonUtil.convertDateToString( new java.util.Date());
        String path= "";

        try{
            PayrollBo payrollBo = (PayrollBo) ctx.getBean("payrollBoProxy");
            payroll = payrollBo.getPayrollById(idPayroll);
            BranchBo branchBo = (BranchBo) ctx.getBean("branchBoProxy");
            branch = branchBo.getBranchById(payroll.getBranchId(),"Y");
        }catch( HibernateException e){

        }

        String logo ="";
        if (branchId.equalsIgnoreCase(CommonConstant.BRANCH_RS01)){
            logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS01;
        }else if (branchId.equalsIgnoreCase(CommonConstant.BRANCH_RS02)){
            logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS02;
        }else if (branchId.equalsIgnoreCase(CommonConstant.BRANCH_RS03)){
            logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_RS03;
        }else if(branchId.equalsIgnoreCase(CommonConstant.BRANCH_KP)){
            logo= CommonConstant.RESOURCE_PATH_IMG_ASSET+"/"+CommonConstant.APP_NAME+CommonConstant.LOGO_NMU;
        }

        switch (tipePayroll){
            case CommonConstant.CODE_PAYROLL :
                reportParams.put("reportParams.urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
                reportParams.put("reportParams.payrollId", idPayroll);
                reportParams.put("reportParams.branchId", payroll.getBranchId());
                reportParams.put("reportParams.bulan", payroll.getBulan());
                reportParams.put("reportParams.tahun", payroll.getTahun());
                reportParams.put("reportParams.titleReport", "Slip Gaji");
                reportParams.put("reportParams.date", stTanggal);
                reportParams.put("reportParams.areaId","PT. Nusantara Medika Utama");

                path = generateJasper(reportParams, idPayroll, CommonConstant.REPORT_PAYROLL);
                model = new PayrollPayment();
                model.setUrl(path);

                break;

            case CommonConstant.CODE_THR :
                reportParams.put("reportParams.urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
                reportParams.put("reportParams.payrollId", idPayroll);
                reportParams.put("reportParams.branchId", payroll.getBranchId());
                reportParams.put("reportParams.bulan", payroll.getBulan());
                reportParams.put("reportParams.tahun", payroll.getTahun());
                reportParams.put("reportParams.titleReport", "Slip Gaji");
                reportParams.put("reportParams.date", stTanggal);
                reportParams.put("reportParams.areaId","PT. Nusantara Medika Utama");

                path = generateJasper(reportParams, idPayroll, CommonConstant.REPORT_PAYROLL_THR);
                model = new PayrollPayment();
                model.setUrl(path);

                break;

            case CommonConstant.CODE_CUTI_TAHUNAN:

                reportParams.put("reportParams.urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
                reportParams.put("reportParams.payrollId", idPayroll);
                reportParams.put("reportParams.branchId", payroll.getBranchId());
                reportParams.put("reportParams.bulan", payroll.getBulan());
                reportParams.put("reportParams.tahun", payroll.getTahun());
                reportParams.put("reportParams.titleReport", "Slip Gaji");
                reportParams.put("reportParams.date", stTanggal);
                reportParams.put("reportParams.areaId","PT. Nusantara Medika Utama");

                path = generateJasper(reportParams, idPayroll, CommonConstant.REPORT_PAYROLL_CUTI_TAHUNAN);
                model = new PayrollPayment();
                model.setUrl(path);

                break;

            case CommonConstant.CODE_CUTI_PANJANG:

                reportParams.put("reportParams.urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
                reportParams.put("reportParams.payrollId", idPayroll);
                reportParams.put("reportParams.branchId", payroll.getBranchId());
                reportParams.put("reportParams.bulan", payroll.getBulan());
                reportParams.put("reportParams.tahun", payroll.getTahun());
                reportParams.put("reportParams.titleReport", "Slip Gaji");
                reportParams.put("reportParams.date", stTanggal);
                reportParams.put("reportParams.areaId","PT. Nusantara Medika Utama");

                path = generateJasper(reportParams, idPayroll, CommonConstant.REPORT_PAYROLL_CUTI_PANJANG);
                model = new PayrollPayment();
                model.setUrl(path);

                break;

            case CommonConstant.CODE_INSENTIF:

                reportParams.put("reportParams.urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
                reportParams.put("reportParams.payrollId", idPayroll);
                reportParams.put("reportParams.branchId", payroll.getBranchId());
                reportParams.put("reportParams.bulan", payroll.getBulan());
                reportParams.put("reportParams.tahun", payroll.getTahun());
                reportParams.put("reportParams.titleReport", "Slip Gaji");
                reportParams.put("reportParams.date", stTanggal);
                reportParams.put("reportParams.areaId","PT. Nusantara Medika Utama");

                path = generateJasper(reportParams, idPayroll, CommonConstant.REPORT_PAYROLL_INSENTIF);
                model = new PayrollPayment();
                model.setUrl(path);

                break;

            case CommonConstant.CODE_JASOP:
                reportParams.put("reportParams.urlLogo", CommonConstant.URL_LOGO_REPORT+branch.getLogoName());
                reportParams.put("reportParams.payrollId", idPayroll);
                reportParams.put("reportParams.branchId", payroll.getBranchId());
                reportParams.put("reportParams.bulan", payroll.getBulan());
                reportParams.put("reportParams.tahun", payroll.getTahun());
                reportParams.put("reportParams.titleReport", "Slip Gaji");
                reportParams.put("reportParams.date", stTanggal);
                reportParams.put("reportParams.areaId","PT. Nusantara Medika Utama");

                path = generateJasper(reportParams, idPayroll, CommonConstant.REPORT_PAYROLL_JASPROD);
                model = new PayrollPayment();
                model.setUrl(path);

                break;
        }

        return new DefaultHttpHeaders("show");
    }

    private String generateJasper(Map parameters, String idPayroll, String template)  {

        String pathDocument = null;
        try {
            Connection connection = dataSource.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            JasperReport jasperReport = JasperCompileManager.compileReport(template);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, connection);

            pathDocument = CommonConstant.RESOURCE_DOCUMENT_PAYROLL + idPayroll +".pdf";
            JasperExportManager.exportReportToPdfFile(print, pathDocument);
        } catch (SQLException | JRException e) {
            e.printStackTrace();
        }


        // return disamakan dengan configurasi tomcat, lihat tomcat/conf/server.xml
        return "images" + CommonConstant.RESOURCE_PATH_PAYROLL + idPayroll + ".pdf";
    }
    @Override
    public PayrollPayment getModel() {
        return model;
    }

    public String getIdPayroll() {
        return idPayroll;
    }

    public void setIdPayroll(String idPayroll) {
        this.idPayroll = idPayroll;
    }

    public String getTipePayroll() {
        return tipePayroll;
    }

    public void setTipePayroll(String tipePayroll) {
        this.tipePayroll = tipePayroll;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public void setModel(PayrollPayment model) {
        this.model = model;
    }
}
