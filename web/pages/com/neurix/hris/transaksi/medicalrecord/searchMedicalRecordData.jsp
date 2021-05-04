<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <style>
        .pagebanner{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
        }
        .pagelinks{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }
    </style>

</head>

<body class="hold-transition skin-blue sidebar-mini" >
<%@ include file="/pages/common/headerNav.jsp" %>


<ivelincloud:mainMenu/>

<div class="content-wrapper">

    <section class="content-header">
        <h1>
            Medical Record Information
            <small>e-HEALTH</small>
        </h1>
    </section>

    <div class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Form</h3>
                    </div>
                    <s:url id="urlProcess" action="search_medicalrecord" includeContext="false"/>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="searchForm" method="post" action="%{urlProcess}" cssClass="well form-horizontal" theme="simple">
                                <table>
                                    <tr>
                                        <td width="10%" align="center">
                                            <%@ include file="/pages/common/message.jsp" %>
                                        </td>
                                    </tr>
                                </table>

                                <div class="form-group">
                                    <label class="control-label col-sm-5">Medical Record Id </label>
                                    <div class="col-sm-3">
                                        <s:textfield id="medicalRecordId" name="medicalRecord.medicalRecordId" cssClass="form-control"
                                                     required="false" cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-5">NIP :</label>
                                    <div class="col-sm-3">
                                        <s:textfield id="nip" name="medicalRecord.nip" cssClass="form-control"
                                                     required="false" cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-5">Tipe Pengobatan :</label>
                                    <div class="col-sm-3">
                                        <s:select list="#{'RI':'Rawat Inap','RJ':'Rawat Jalan'}" id="tipePengobatan" name="medicalRecord.tipePengobatan"
                                                  headerKey="" headerValue="[Select One]" cssClass="form-control" cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-5">Tanggal Berobat :</label>
                                    <div class="col-sm-3">
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tanggalBerobat" name="medicalRecord.stTanggalBerobat" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="errorLog.branchId">Unit :</label>
                                    <div class="col-sm-3">
                                        <s:textfield id="branchId" name="errorLog.branchId" cssClass="form-control"
                                                     required="false" cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                    </div>
                                </div>

                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-5" for="errorLog.stErrorTimestampFrom">Created Date :</label>--%>
                                    <%--<div class="col-sm-3">--%>

                                        <%--<div class="input-group date">--%>
                                            <%--<div class="input-group-addon">--%>
                                                <%--<i class="fa fa-calendar"></i>--%>
                                            <%--</div>--%>
                                            <%--<s:textfield id="errorTimestampFrom" name="errorLog.stErrorTimestampFrom" cssClass="form-control pull-right"--%>
                                                         <%--required="false"  cssStyle=""/>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-5" for="errorLog.stErrorTimestampTo">To :</label>--%>
                                    <%--<div class="col-sm-3">--%>
                                        <%--<div class="input-group date">--%>
                                            <%--<div class="input-group-addon">--%>
                                                <%--<i class="fa fa-calendar"></i>--%>
                                            <%--</div>--%>
                                            <%--<s:textfield id="errorTimestampTo" name="errorLog.stErrorTimestampTo" cssClass="form-control pull-right"--%>
                                                         <%--required="false"  cssStyle=""/>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="control-label col-sm-4" style="visibility: hidden">To :</label>
                                    <div style="padding-left: 140px" class="col-sm-3">
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="searchForm" id="search" name="search"
                                                   onClickTopics="showDialogSearch" onCompleteTopics="closeDialogSearch">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_errorlog"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </div>
                                </div>

                                <div id="actions" class="form-actions" align="center">
                                    <sj:dialog id="waiting_dialog_search" openTopics="showDialogSearch1" closeTopics="closeDialogSearch" modal="true"
                                               resizable="false" height="250" width="600" autoOpen="false" title="Searching...">
                                        Please don't close this window, server is processing your request ...
                                        </br>
                                        </br>
                                        </br>
                                        <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_read">
                                    </sj:dialog>
                                </div>
                            </s:form>
                            <br>

                            <center>
                                <table id="showdata" width="80%">
                                    <tr>
                                        <td align="center">
                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                       height="500" width="900" autoOpen="false"
                                                       title="Medical Record">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfResult" value="#session.listOfResult" scope="request" />
                                            <display:table name="listOfResult" class="table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_medicalrecord.action" export="true" id="row" pagesize="20" style="font-size:10">


                                                <display:column media="html" title="View" style="text-align:center;font-size:9">
                                                    <s:url var="urlViewDoc" namespace="/medicalrecord" action="initApprove_medicalrecord" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.medicalRecordId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                        <s:param name="view">Y</s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogMenu" href="%{urlViewDoc}">
                                                        <img border="0" src="<s:url value="/pages/images/view_detail_project.png"/>" name="icon_trash">
                                                    </sj:a>

                                                </display:column>

                                                <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                    <s:url var="urlViewDelete" value="edit_medicalrecord.action" >
                                                        <s:param name="id"><s:property value="#attr.row.medicalRecordId" /></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                        <s:param name="delete">Y</s:param>
                                                    </s:url>
                                                    <s:a href="%{urlViewDelete}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                    </s:a>

                                                </display:column>


                                                <display:column media="html" title="Edit">
                                                    <s:if test="%{#attr.row.edit}">
                                                        <s:url var="urlEdit" value="edit_medicalrecord.action">
                                                            <s:param name="id"><s:property value="#attr.row.medicalRecordId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <s:a href="%{urlEdit}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                        </s:a>
                                                    </s:if>
                                                    <s:else>
                                                        <img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">
                                                    </s:else>
                                                </display:column>

                                                <display:column media="html" title="Approve">
                                                    <s:if test="%{#attr.row.approve}">
                                                        <s:url var="urlApprove" namespace="/medicalrecord" action="initApprove_medicalrecord" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.medicalRecordId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenu" href="%{urlApprove}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                        </sj:a>
                                                    </s:if>
                                                    <s:else>
                                                        <img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">
                                                    </s:else>
                                                </display:column>
                                                <display:column media="html" title="Print<br> Surat Jaminan">
                                                    <s:if test="%{#attr.row.print}">
                                                        <s:url var="urlEdit" namespace="/shift" action="edit_shift" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.shiftId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                        </sj:a>
                                                    </s:if>
                                                    <s:else>
                                                        <img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">
                                                    </s:else>
                                                </display:column>
                                                <display:column property="medicalRecordId" sortable="true" title="Medical Record Id" />
                                                <display:column property="nip" sortable="true" title="NIP"  />
                                                <display:column property="namaBerobat" sortable="true" title="Nama Pegawai"  />
                                                <display:column property="tipePengobatanName" sortable="true" title="Tipe Pengobatan"  />
                                                <display:column property="tipeBerobatName" sortable="true" title="Tipe Berobat"  />
                                                <display:column property="iconApprove" sortable="true" title="Status"/>
                                                <display:column property="note" sortable="true" title="Note"  />
                                                <%--<display:column property="action" sortable="true" title="CreatedWho"/>--%>
                                                <display:setProperty name="paging.banner.item_name">MedicalRecordData</display:setProperty>
                                                <display:setProperty name="paging.banner.items_name">MedicalRecordData</display:setProperty>
                                                <display:setProperty name="export.excel.filename">MedicalRecordData.xls</display:setProperty>
                                                <display:setProperty name="export.csv.filename">MedicalRecordData.csv</display:setProperty>
                                                <display:setProperty name="export.pdf.filename">MedicalRecordData.pdf</display:setProperty>
                                            </display:table>
                                        </td>
                                    </tr>
                                </table>
                            </center>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>


</body>
</html>

<script>
    $(document).ready(function() {
        $('#tanggalBerobat').datepicker({
            dateFormat: 'yy-mm-dd'
        });
//        $('#errorTimestampFrom').datepicker({
//            dateFormat: 'dd-mm-yy'
//        });
    });
</script>


