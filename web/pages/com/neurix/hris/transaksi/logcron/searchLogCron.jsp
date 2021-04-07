<%--
  Created by IntelliJ IDEA.
  User: mgi
  Date: 05/04/21
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
</head>

<body class="hold-transition skin-blue sidebar-mini">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Log Cron
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="logCronForm" method="post"  theme="simple" namespace="/logcron" action="search_logcron.action" cssClass="well form-horizontal">

                        <s:hidden name="addOrEdit"/>
                        <s:hidden name="delete"/>

                        <table>
                            <tr>
                                <td width="10%" align="center">
                                    <%@ include file="/pages/common/message.jsp" %>
                                </td>
                            </tr>
                        </table>

                        <table >

                            <tr>
                                <td>
                                    <label class="control-label"><small>Log Cron Id :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="logId" name="logCron.logCronId" required="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label"><small>Cron Name :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield  id="cronName" name="logCron.cronName" required="true" cssClass="form-control"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Cron Date :</small></label>
                                </td>
                                <td>
                                    <div class="input-group date">
                                        <div class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </div>
                                        <s:textfield id="cronDateStr" name="logCron.stCronDateStr" cssClass="form-control pull-right"
                                                     required="false" size="7"  cssStyle="" onchange="validateRange()"/>
                                        <div class="input-group-addon">
                                            s/d
                                        </div>
                                        <div class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </div>
                                        <s:textfield id="cronDateEnd" name="logCron.stCronDateEnd" cssClass="form-control pull-right"
                                                     required="false" size="7"  cssStyle="" onchange="validateRange()"/>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label"><small>Status :</small></label>
                                </td>
                                <td>
                                    <table>
                                        <s:select list="#{'success':'Success', 'connection_problem':'Connection Has Problem', 'program_problem':'Program Has Problem', 'other':'Others'}" id="statusLog" name="logCron.status"
                                                  headerKey="" headerValue="[all status]" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>

                        </table>

                        <br>

                        <div id="actions" class="form-actions">
                            <table align="center">
                                <tr>
                                    <td>
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="logCronForm" id="search" name="search"
                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                    </td>
                                    <%--RAKA-05APR2021 ==> Seharusnya tak perlu add log (tak bisa add atau edit)--%>
                                    <%--<td>--%>
                                        <%--<s:url var="urlAdd" namespace="/logcron" action="add_logcron" escapeAmp="false">--%>
                                        <%--</s:url>--%>
                                        <%--<sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">--%>
                                            <%--<i class="fa fa-plus"></i>--%>
                                            <%--Add Notifikasi--%>
                                        <%--</sj:a>--%>
                                    <%--</td>--%>
                                    <%--RAKA-end--%>
                                    <td>
                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_logcron"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <br>
                        <br>
                        <center>
                            <table id="showdata" width="80%">
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="500" width="550" autoOpen="false"
                                                   title="Log Transaction">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfLogCron" value="#session.listOfResult" scope="request" />
                                        <display:table name="listOfLogCron" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag_master_logcron.action" export="true" id="row" pagesize="14" style="font-size:10">
                                            <%--<display:column media="html" title="Edit">--%>
                                                <%--<s:if test="#attr.row.flagYes">--%>
                                                    <%--<s:url var="urlEdit" namespace="/notifikasi" action="edit_notifikasi" escapeAmp="false">--%>
                                                        <%--<s:param name="id"><s:property value="#attr.row.notifId"/></s:param>--%>
                                                        <%--<s:param name="flag"><s:property value="#attr.row.flag"/></s:param>--%>
                                                    <%--</s:url>--%>
                                                    <%--<sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">--%>
                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">--%>
                                                    <%--</sj:a>--%>
                                                <%--</s:if>--%>
                                            <%--</display:column>--%>

                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                <s:url var="urlViewDelete" namespace="/logcron" action="delete_logcron" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.logCronId" /></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialogMenu" href="%{urlViewDelete}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                </sj:a>

                                            </display:column>
                                            <display:column property="logCronId" sortable="true" title="Log Cron Id"  />
                                            <display:column property="cronName" sortable="true" title="Cron Name"  />
                                            <display:column property="cronDate" sortable="true" title="Cron Date"  />
                                            <display:column property="status" sortable="true" title="Status"  />
                                            <display:column property="note" sortable="true" title="Note" />
                                            <display:setProperty name="paging.banner.item_name">LogCron</display:setProperty>
                                            <display:setProperty name="paging.banner.items_name">LogCron</display:setProperty>
                                            <display:setProperty name="export.excel.filename">LogCron.xls</display:setProperty>
                                            <display:setProperty name="export.csv.filename">LogCron.csv</display:setProperty>
                                            <display:setProperty name="export.pdf.filename">LogCron.pdf</display:setProperty>
                                        </display:table>
                                    </td>
                                </tr>
                            </table>
                        </center>
                    </s:form>
                </td>
            </tr>
        </table>

        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

</body>
</html>

<script>
    $(document).ready(function() {
        $('#cronDateStr').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#cronDateEnd').datepicker({
            dateFormat: 'dd-mm-yy'
        });
    })

    function validateRange() {
        var start = $('#cronDateStr').val().split('-');
        var end   = $('#cronDateEnd').val().split('-');

        var dateStr = new Date(start[2],start[1]-1,start[0]);
        var dateEnd = new Date(end[2],end[1]-1,end[0]);

        console.log

        if(start!='' && end!='') {
            if (dateStr > dateEnd) {
                alert("Range of Date is Wrong!");
                $('#cronDateEnd').val('');
            }
        }
    }
</script>