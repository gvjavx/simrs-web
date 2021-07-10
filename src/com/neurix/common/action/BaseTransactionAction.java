package com.neurix.common.action;

import com.neurix.common.constant.CommonConstant;
import com.opensymphony.xwork2.ActionSupport;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ferdi on 16/09/2014.
 */
public abstract class BaseTransactionAction extends ActionSupport {

    protected Connection connection;
    protected DataSource dataSource;
    protected Map reportParams=new HashMap();

    protected InputStream excelStream;
    protected String contentDisposition;
    protected String documentFormat = CommonConstant.EXCEL;

    public InputStream getExcelStream() {
        return excelStream;
    }

    public void setExcelStream(InputStream excelStream) {
        this.excelStream = excelStream;
    }

    public String getContentDisposition() {
        return contentDisposition;
    }

    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    public String getDocumentFormat() {
        return documentFormat;
    }

    public void setDocumentFormat(String documentFormat) {
        this.documentFormat = documentFormat;
    }

    public Map getReportParams() {
        return reportParams;
    }

    public void setReportParams(Map reportParams) {
        this.reportParams = reportParams;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String paging() {
        return SUCCESS;
    }

    public void preDownload() throws SQLException {
        connection=dataSource.getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    }

    public abstract String search();
    public abstract String initForm();

}
