<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
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
    <script type='text/javascript'>

        function link(){
            window.location.href="<s:url action='initForm_reportKeuanganKonsol'/>";
        }

    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Setting Report Keuangan Konsol
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Form Setting Report Keuangan Konsol </h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="reportKeuanganKonsolForm" method="post"  theme="simple" namespace="/reportKeuanganKonsol" action="search_reportKeuanganKonsol.action" cssClass="form-horizontal">
                                        <table>
                                            <tr>
                                                <td width="10%" align="center">
                                                    <%@ include file="/pages/common/message.jsp" %>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Report Konsol ID :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="settingReportKonsolId" name="akunSettingReportKeuanganKonsol.settingReportKonsolId" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Kode Rekening Alias :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="kodeRekeningAlias" name="akunSettingReportKeuanganKonsol.kodeRekeningAlias" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nama Kode Rekening Alias :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="namaKodeRekeningAlias" name="akunSettingReportKeuanganKonsol.namaKodeRekeningAlias" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Flag :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="akunSettingReportKeuanganKonsol.flag"
                                                                  headerKey="Y" headerValue="Active" cssClass="form-control" />
                                                    </table>

                                                </td>
                                            </tr>

                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="reportKeuanganKonsolForm" id="search" name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <a href="add_reportKeuanganKonsol.action" class="btn btn-success" ><i class="fa fa-plus"></i>Add Report Keuangan Konsol</a>
                                                        <%--<s:url var="urlAdd" namespace="/reportKeuanganKonsol" action="add_reportKeuanganKonsol" escapeAmp="false">--%>
                                                        <%--</s:url>--%>
                                                        <%--<sj:a cssClass="btn btn-success" onClickTopics="showDialogMenu" href="%{urlAdd}">--%>
                                                            <%--<i class="fa fa-plus"></i>--%>
                                                            <%--Add Report Keuangan Konsol--%>
                                                        <%--</sj:a>--%>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_reportKeuanganKonsol"/>'">
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
                                                        <sj:dialog id="waiting_dialog_loading" openTopics="showDialog"
                                                                   closeTopics="closeDialog" modal="true"
                                                                   resizable="false"
                                                                   height="250" width="600" autoOpen="false"
                                                                   title="Search Data ...">
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
                                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                                   height="400" width="600" autoOpen="false"
                                                                   title="Report Keuangan Konsol ">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>

                                                        <sj:dialog id="view_dialog_menu_pendapatan" openTopics="showDialogMenuView" modal="true"
                                                                   height="570" width="700" autoOpen="false"
                                                                   title="Report Keuangan Konsol">
                                                        </sj:dialog>
                                                        <sj:dialog id="view_dialog_keterangan" openTopics="showDialogMenuKeterangan" modal="true"
                                                                   height="680" width="700" autoOpen="false"
                                                                   title="Report Keuangan Konsol">
                                                        </sj:dialog>

                                                        <s:set name="listOfsearchAkunSettingReportKeuanganKonsol" value="#session.listOfResultKeuanganKonsol" scope="request" />
                                                        <display:table name="listOfsearchAkunSettingReportKeuanganKonsol" class="table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_reportKeuanganKonsol.action" export="true" id="row" pagesize="14" style="font-size:10">
                                                            <display:column media="html" title="View">
                                                                <s:if test='#attr.row.flag == "Y"'>
                                                                    <s:url var="urlView" namespace="/reportKeuanganKonsol" action="view_reportKeuanganKonsol" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.settingReportKonsolId"/></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                    </s:url>
                                                                    <s:a href="%{urlView}">
                                                                        <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_edit">
                                                                    </s:a>
                                                                </s:if>
                                                            </display:column>

                                                            <display:column media="html" title="Edit">
                                                                <s:if test='#attr.row.flag == "Y"'>
                                                                    <s:url var="urlEdit" namespace="/reportKeuanganKonsol" action="edit_reportKeuanganKonsol" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.settingReportKonsolId"/></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                    </s:url>
                                                                    <s:a href="%{urlEdit}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                    </s:a>
                                                                </s:if>
                                                            </display:column>

                                                            <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                                <s:if test='#attr.row.flag == "Y"'>
                                                                    <%--<s:url var="urlViewDelete" namespace="/reportKeuanganKonsol" action="delete_reportKeuanganKonsol" escapeAmp="false">--%>
                                                                        <%--<s:param name="id"><s:property value="#attr.row.settingReportKonsolId" /></s:param>--%>
                                                                        <%--<s:param name="flag"><s:property value="#attr.row.flag" /></s:param>--%>
                                                                    <%--</s:url>--%>
                                                                    <%--<sj:a href="%{urlViewDelete}">--%>
                                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">--%>
                                                                    <%--</sj:a>--%>

                                                                    <s:url var="urlDelete" namespace="/reportKeuanganKonsol" action="delete_reportKeuanganKonsol" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.settingReportKonsolId"/></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                    </s:url>
                                                                    <s:a href="%{urlDelete}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_delete">
                                                                    </s:a>
                                                                </s:if>
                                                            </display:column>
                                                            <display:column property="settingReportKonsolId" sortable="true" title="Report Konsol Id" />
                                                            <display:column property="kodeRekeningAlias" sortable="true" title="Kode Rek. Alias"  />
                                                            <display:column property="namaKodeRekeningAlias" sortable="true" title="Nama Kode Rek. Alias"/>
                                                            <display:column property="flagLabel" sortable="true" title="Label"/>
                                                            <display:column property="flag" sortable="true" title="flag"  />
                                                            <display:column property="action" sortable="true" title="action"  />
                                                            <display:column property="stCreatedDate" sortable="true" title="Created date"  />
                                                            <display:column property="createdWho" sortable="true" title="Created who"  />
                                                            <display:column property="stLastUpdate" sortable="true" title="Last update"  />
                                                            <display:column property="lastUpdateWho" sortable="true" title="Last update who"  />
                                                        </display:table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </center>
                                        <div id="actions" class="form-actions">
                                            <table>
                                                <tr>
                                                    <div id="crud">
                                                        <td>
                                                            <table>
                                                                <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog" modal="true" resizable="false"
                                                                           height="280" width="500" autoOpen="false" title="Warning"
                                                                           buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }"
                                                                >
                                                                    <div class="alert alert-error fade in">
                                                                        <label class="control-label" align="left">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                                            <br/>
                                                                            <center><div id="errorValidationMessage"></div></center>
                                                                        </label>
                                                                    </div>
                                                                </sj:dialog>
                                                            </table>
                                                        </td>
                                                    </div>
                                                </tr>
                                            </table>
                                        </div>
                                    </s:form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- Your Page Content Here -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>