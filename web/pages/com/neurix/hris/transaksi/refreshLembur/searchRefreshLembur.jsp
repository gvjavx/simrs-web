<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 23/07/2021
  Time: 1:43
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
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LemburAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RefreshLemburAction.js"/>'></script>

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
    <script type='text/javascript'>

        function link(){
            window.location.href="<s:url action='initForm_lembur'/>";
        }
        var unit = '<s:property value="Lembur.unitId"/>'
    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini">
<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Refresh Data Lembur

        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Form</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="refreshLemburForm" method="post"  theme="simple" namespace="/refreshLembur" action="search_refreshLembur.action" cssClass=" form-horizontal">
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
                                                    <label class="control-label"><small>Group Refresh Lembur Id :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="groupLemburId" name="refreshLembur.groupRefreshId" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Unit :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                                        <s:if test="isAdmin()">
                                                            <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid" name="refreshLembur.branchId" required="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid" name="refreshLembur.branchId" required="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="" disabled="true"/>
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tanggal :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <div class="input-group date">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </div>
                                                            <s:textfield id="loginTimestampFrom" name="refreshLembur.stTglAwalLembur" size="7" cssClass="form-control pull-right"
                                                                         required="false" cssStyle=""/>
                                                            <div class="input-group-addon">
                                                                s/d
                                                            </div>
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-calendar"></i>
                                                            </div>
                                                            <s:textfield id="loginTimestampTo" name="refreshLembur.stTglAkhirLembur" size="7" cssClass="form-control pull-right"
                                                                         required="false" cssStyle=""/>
                                                        </div>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Status Approval :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'0':'Menunggu Approve','Y':'Telah di Approve','N':'Tidak di Approve'}" id="statusAproval" name="refreshLembur.flagApprove"
                                                                  headerKey="" headerValue="Semua Status" cssClass="form-control" />
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="refreshLemburForm" id="search" name="search"
                                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <s:url var="urlAdd" namespace="/refreshLembur" action="add_refreshLembur" escapeAmp="false">
                                                        </s:url>
                                                        <sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">
                                                            <i class="fa fa-plus"></i>
                                                            Add Lembur
                                                        </sj:a>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_refreshLembur"/>'">
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
                                                        <sj:dialog id="waiting_dialog_loading" openTopics="showDialogLoading"
                                                                   closeTopics="closeDialogLoading" modal="true"
                                                                   resizable="false"
                                                                   height="250" width="600" autoOpen="false"
                                                                   title="Save Data ...">
                                                            Please don't close this window, server is processing your request ...
                                                            <br>
                                                            <center>
                                                                <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                                     src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                                     name="image_indicator_write">
                                                                <br>
                                                                <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                                     name="image_indicator_write">
                                                            </center>
                                                        </sj:dialog>

                                                        <sj:dialog id="dialog_menu_refresh_lembur" openTopics="showDialogMenu" modal="true"
                                                                   height="670" width="900" autoOpen="false"
                                                                   title="Refresh Lembur">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>
                                                        <sj:dialog id="view_dialog_menu_refresh_lembur" openTopics="showDialogMenuView" modal="true"
                                                                   height="570" width="900" autoOpen="false"
                                                                   title="Refresh Lembur">
                                                        </sj:dialog>
                                                        <s:set name="listOfRefreshLembur" value="#session.listOfResultRefreshLembur" scope="request" />
                                                        <display:table name="listOfRefreshLembur" class="tableLembur table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_refresh_lembur.action" export="true" id="row" pagesize="14" style="font-size:10">
                                                            <display:column media="html" title="View">
                                                                <s:url var="urlView" namespace="/refreshLembur" action="view_refreshLembur" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.groupRefreshId"/></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                    <s:param name="approve"><s:property value="#attr.row.flagApprove"/></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenu" href="%{urlView}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">
                                                                </sj:a>
                                                            </display:column>
                                                            <%--<display:column media="html" title="Edit">--%>
                                                                    <%--<s:if test="#attr.row.flagApprove == 'N'">--%>
                                                                        <%--<s:url var="urlEdit" namespace="/refreshLembur" action="edit_refreshLembur" escapeAmp="false">--%>
                                                                            <%--<s:param name="id"><s:property value="#attr.row.groupRefreshId"/></s:param>--%>
                                                                            <%--<s:param name="flag"><s:property value="#attr.row.flag"/></s:param>--%>
                                                                        <%--</s:url>--%>
                                                                        <%--<sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">--%>
                                                                            <%--<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">--%>
                                                                        <%--</sj:a>--%>
                                                                    <%--</s:if>--%>
                                                            <%--</display:column>--%>
                                                            <display:column property="groupRefreshId" sortable="true" title="Group Refresh Id" />
                                                            <display:column property="stTanggal" sortable="true" title="Tanggal" />
                                                            <display:column property="nip" sortable="true" title="NIP"  />
                                                            <display:column property="nama" sortable="true" title="Nama"  />
                                                            <display:column property="jamAwalLembur" sortable="true" title="Jam Awal"  />
                                                            <display:column property="jamAkhirLembur" sortable="true" title="Jam Akhir"  />
                                                            <display:column property="stLamaLembur" sortable="true" title="Lama Lembur (Jam)"  />
                                                            <display:column property="stJamLembur" sortable="true" title="Faktor Lembur"  />
                                                            <display:column media="html" title="Approve Atasan">
                                                                <s:if test="#attr.row.flagApprove == 'Y'">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:if>
                                                                <s:elseif test="#attr.row.flagApprove == 'N'">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                                </s:elseif>
                                                            </display:column>

                                                            <display:setProperty name="paging.banner.item_name">RefreshLembur</display:setProperty>
                                                            <display:setProperty name="paging.banner.items_name">RefreshLembur</display:setProperty>
                                                            <display:setProperty name="export.excel.filename">RefreshLembur.xls</display:setProperty>
                                                            <display:setProperty name="export.csv.filename">RefreshLembur.csv</display:setProperty>
                                                            <display:setProperty name="export.pdf.filename">RefreshLembur.pdf</display:setProperty>
                                                        </display:table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </center>
                                    </s:form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
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

<%@ include file="/pages/common/footer.jsp" %>


<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>

<script>
    $(document).ready(function() {
        $('#loginTimestampTo').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#loginTimestampFrom').datepicker({
            dateFormat: 'dd-mm-yy'
        });
    });
</script>

