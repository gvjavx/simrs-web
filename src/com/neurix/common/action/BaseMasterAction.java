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
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 11/03/13
 * Time: 17:41
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseMasterAction extends ActionSupport {

    protected boolean delete = false;
    protected boolean addOrEdit = false;
    protected boolean add = false;
    protected String id;
    protected String periode;
    protected String flag;
    protected Connection connection;
    protected DataSource dataSource;
    protected Map reportParams=new HashMap();

    protected InputStream excelStream;
    protected String contentDisposition;
    protected String documentFormat = CommonConstant.EXCEL;
    protected String excelContentType;

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

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

    public String getExcelContentType() {
        return documentFormat == CommonConstant.EXCEL ? "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" : "application/vnd.ms-excel";
    }

    public void setExcelContentType(String excelContentType) {
        this.excelContentType = excelContentType;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isAddOrEdit() {
        return addOrEdit;
    }

    public void setAddOrEdit(boolean addOrEdit) {
        this.addOrEdit = addOrEdit;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public String paging() {
        return SUCCESS;
    }

    public void preDownload() throws SQLException {
        connection=dataSource.getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    }

    public abstract String add();
    public abstract String edit();
    public abstract String delete();
    public abstract String view();
    public abstract String save();
    public abstract String search();
    public abstract String initForm();
    public abstract String downloadPdf();
    public abstract String downloadXls();


}
