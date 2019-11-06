package com.neurix.hris.mobileapi;

import com.neurix.common.constant.CommonConstant;
import com.neurix.hris.mobileapi.model.PayrollPayment;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.jasperreports.engine.*;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gondok
 * Sunday, 31/03/19 21:27
 */
public class PayrolController implements ModelDriven<PayrollPayment> {

    private DriverManagerDataSource dataSource;
    private String idPayroll;
    private PayrollPayment model;

    public void setDataSource(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public HttpHeaders create() {

        Map reportParams=new HashMap();
        reportParams.put("reportParams.urlLogo", CommonConstant.URL_IMAGE_LOGO_REPORT);
        reportParams.put("reportParams.titleReport", "Report Payroll");
        reportParams.put("reportParams.payrollId", idPayroll);

        String path = generateJasper(reportParams, idPayroll);
        model = new PayrollPayment();
        model.setUrl(path);

        return new DefaultHttpHeaders("show");
    }

    private String generateJasper(Map parameters, String idPayroll) {

        String pathDocument = null;
        try {
            Connection connection = dataSource.getConnection();

            JasperReport jasperReport = JasperCompileManager.compileReport("/opt/tomcat/webapps/hris/pages/report/com/neurix/hris/reportPayroll.jrxml");
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, connection);

            pathDocument = CommonConstant.RESOURCE_DOCUMENT_PAYROLL + idPayroll +".pdf";
            JasperExportManager.exportReportToPdfFile(print, pathDocument);
        } catch (SQLException | JRException e) {
            e.printStackTrace();
        }


        // return disamakan dengan configurasi tomcat, lihat tomcat/conf/server.xml
        return "documents/" + idPayroll + ".pdf";
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
}
