    <%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 15/02/2018
  Time: 16.59
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
            window.location.href="<s:url action='initForm_alat'/>";
        }

        function getReportURL(selectReport) {
            var reportId = selectReport.value;
            document.reportForm.action= "<s:property value="appname" />/laporanAkuntansi/" + reportId;
            document.reportForm.submit();
        };

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>
<script type='text/javascript' src='<s:url value="/dwr/interface/MedicalRecordAction.js"/>'></script>
<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Laporan Akuntansi
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pilih Laporan</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="reportForm" method="post"  theme="simple" namespace="/report" action="search_report.action" cssClass="form-horizontal">

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
                                                    <label class="control-label"><small>Nama Laporan :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboLaporan" namespace="/laporanAkuntansi" name="searchLaporanAkuntansi_laporanAkuntansi"/>
                                                        <s:select list="#comboLaporan.listComboLaporanAkuntansi" id="positionId" onchange="getReportURL(this);"
                                                                  listKey="url" listValue="laporanAkuntansiName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                    </table>

                                                </td>
                                            </tr>


                                        </table>

                                        <sj:dialog id="info_dialog_error" openTopics="showInfoDialogUpdateSession" modal="true" resizable="false"
                                                   position="center" height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                          'OK':function() { $('#info_dialog_error').dialog('close'); }
                                                        }"
                                        >
                                            <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error">
                                            Found failure when open report, please try again or call your admin.
                                        </sj:dialog>

                                    </s:form>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
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

