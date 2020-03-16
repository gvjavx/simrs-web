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
            Error Log Information
            <small>HRIS</small>
        </h1>
    </section>

    <div class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Form</h3>
                    </div>
                    <s:url id="urlProcess" namespace="/admin/errorlog" action="search_errorlog" includeContext="false"/>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="errorLogForm" method="post" action="%{urlProcess}" cssClass="well form-horizontal" theme="simple">
                                <table>
                                    <tr>
                                        <td width="10%" align="center">
                                            <%@ include file="/pages/common/message.jsp" %>
                                        </td>
                                    </tr>
                                </table>

                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="errorLog.errorId">Error Id :</label>
                                    <div class="col-sm-3">
                                        <s:textfield id="errorId" name="errorLog.errorId" cssClass="form-control"
                                                     required="false" cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="errorLog.moduleMethod">Module Method :</label>
                                    <div class="col-sm-3">
                                        <s:textfield id="method" name="errorLog.moduleMethod" cssClass="form-control"
                                                     required="false" cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="errorLog.message">Message :</label>
                                    <div class="col-sm-3">
                                        <s:textfield id="message" name="errorLog.message" cssClass="form-control"
                                                     required="false" cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="errorLog.userId">User :</label>
                                    <div class="col-sm-3">
                                        <s:textfield id="userId" name="errorLog.userId" cssClass="form-control"
                                                     required="false" cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="errorLog.branchId">Unit :</label>
                                    <div class="col-sm-3">
                                        <s:textfield id="branchId" name="errorLog.branchId" cssClass="form-control"
                                                     required="false" cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="errorLog.stErrorTimestampFrom">Created Date :</label>
                                    <div class="col-sm-3">

                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="errorTimestampFrom" name="errorLog.stErrorTimestampFrom" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="errorLog.stErrorTimestampTo">To :</label>
                                    <div class="col-sm-3">
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="errorTimestampTo" name="errorLog.stErrorTimestampTo" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4" style="visibility: hidden">To :</label>
                                    <div style="padding-left: 140px" class="col-sm-3">
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="errorLogForm" id="search" name="search"
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

                            <table width="100%">
                                <tr>
                                    <td align="center">

                                        <sj:dialog id="view_dialog" openTopics="showDialog" modal="true" resizable="false" cssStyle="text-align:left;"
                                                   position="center" height="600" width="500" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfErrorLog" value="#session.listOfResult" scope="request"/>
                                        <display:table name="listOfErrorLog" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag.action" id="row" export="true" pagesize="30" style="font-size:11">

                                            <display:column media="html" title="View">
                                                <s:url var="urlView" namespace="/admin/errorlog" action="view_errorlog" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.errorId"/></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialog" href="%{urlView}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">
                                                </sj:a>
                                            </display:column>


                                            <display:column property="errorId" sortable="true" title="Error.Id"/>
                                            <display:column property="moduleMethod" sortable="true" title="Module Method"/>
                                            <display:column property="message" sortable="true" title="Message"/>
                                            <display:column property="userId" sortable="true" title="User"/>
                                            <display:column property="branchId" sortable="true" title="Unit"/>
                                            <display:column property="errorTimestamp" sortable="true" title="CreatedDate"
                                                            decorator="com.neurix.common.displaytag.LongDateWrapper"/>
                                            <display:setProperty name="paging.banner.item_name">ErrorLog</display:setProperty>
                                            <display:setProperty name="paging.banner.items_name">ErrorLogs</display:setProperty>
                                            <display:setProperty name="export.excel.filename">ErrorLogs.xls</display:setProperty>
                                            <display:setProperty name="export.csv.filename">ErrorLogs.csv</display:setProperty>
                                            <display:setProperty name="export.pdf.filename">ErrorLogs.pdf</display:setProperty>

                                        </display:table>

                                    </td>
                                </tr>
                            </table>
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
        $('#errorTimestampTo').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#errorTimestampFrom').datepicker({
            dateFormat: 'dd-mm-yy'
        });
    });
</script>


