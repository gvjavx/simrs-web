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
        .checkApprove {width: 20px; height: 20px;}
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
            window.location.href="<s:url action='initForm_rekruitmen'/>";
        }

    </script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Pasien
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Pasien Form</h3>
                    </div>
                    <table width="100%" align="center">
                        <tr>
                            <td align="center">
                                <s:form id="checkupForm" method="post"  theme="simple" namespace="/checkup" action="search_checkup.action" cssClass="form-horizontal">

                                    <s:hidden name="addOrEdit"/>
                                    <s:hidden name="delete"/>

                                    <table>
                                        <tr>
                                            <td width="10%" align="center">
                                                <%@ include file="/pages/common/message.jsp" %>
                                            </td>
                                        </tr>
                                    </table>
                                    <table>
                                        <tr >
                                            <td>
                                                <label class="control-label"><small>ID Pasien</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="id_pasien" name="pasien.idPasien" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>No KTP</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px"  id="no_ktp" name="pasien.noKtp" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Nama Pasien</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="nama_pasien" name="pasien.nama" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Poli</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:action id="initComboStatus" namespace="/rekruitmen" name="searchStatusRekruitmen_rekruitmen"/>
                                                    <s:select cssStyle="margin-top: 7px" list="#initComboStatus.listComboStatusRekruitmen" id="poli" name="pasien.poli" listKey="statusRekruitmentId" listValue="statusRekruitmentName"
                                                              headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Status</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:action id="initComboBranch" namespace="/admin/branch"
                                                              name="initComboBranch_branch"/>
                                                    <s:select cssStyle="margin-top: 7px" list="#initComboBranch.listOfComboBranch" id="status"
                                                              name="rekruitmen.branchId" onchange="listPosisi()"
                                                              listKey="branchId" listValue="branchName" headerKey=""
                                                              headerValue="[Select one]" cssClass="form-control"/>
                                                </table>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Alamat</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textarea cssStyle="margin-top: 7px"  id="alamat" name="pasien.alamat" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>

                                    </table>
                                    <br>
                                    <div id="actions" class="form-actions">
                                        <table align="center">
                                            <tr>
                                                <td>
                                                    <sj:submit type="button" cssClass="btn btn-primary" formIds="checkupForm" id="search" name="search"
                                                               onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                                        <i class="fa fa-search"></i>
                                                        Search
                                                    </sj:submit>
                                                </td>
                                                <td>
                                                    <a href="add_checkup.action" class="btn btn-success" ><i class="fa fa-plus"></i> Tambah Pasien</a>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_rekruitmen"/>'">
                                                        <i class="fa fa-refresh"></i> Reset
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>

                                    <br>
                                    <br>
                                    <center>
                                        <table id="showdata" width="90%">
                                            <tr>
                                                <td align="center">
                                                    <sj:dialog id="waiting_dialog_loading" openTopics="showDialogLoading" closeTopics="closeDialogLoading" modal="true"
                                                               resizable="false"
                                                               height="350" width="600" autoOpen="false" title="Loading ...">
                                                        Please don't close this window, server is processing your request ...
                                                        </br>
                                                        </br>
                                                        </br>
                                                        <center>
                                                            <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_read">
                                                        </center>
                                                    </sj:dialog>
                                                    <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                               height="800" width="1100" autoOpen="false"
                                                               title="Rekruitmen ">
                                                    </sj:dialog>
                                                    <sj:dialog id="view_dialog_menu" openTopics="showDialogMenuView" modal="true"
                                                               height="700" width="1100" autoOpen="false"
                                                               title="Rekruitmen">
                                                        <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                    </sj:dialog>
                                                    <s:set name="listOfRekruitmen" value="#session.listOfResult" scope="request" />
                                                    <display:table name="listOfRekruitmen" class=" tableRekruitmen table table-condensed table-striped table-hover"
                                                                   requestURI="paging_displaytag_rekruitmen.action" export="true" id="row" pagesize="14" style="font-size:12">

                                                        <display:column property="namaCalonPegawai" sortable="true" title="No Checkup"  />
                                                        <display:column property="noKtp" sortable="true" title="ID Pasien" />
                                                        <display:column property="noTelp" sortable="true" title="Nama" />
                                                        <display:column property="posisiName" sortable="true" title="Poli Terakhir" />
                                                        <display:column property="divisiName" sortable="true" title="Status Terakhir" />
                                                        <display:column property="branchName" sortable="true" title="Ruangan" />
                                                        <display:column property="statusSaatIni" sortable="true" title="No" />
                                                        <display:column media="html" title="Action" style="text-align:center;font-size:9">
                                                            <s:if test="#attr.row.rekruitmenClosed">
                                                                <%--<img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">--%>
                                                            </s:if>
                                                            <s:else>
                                                                <s:url var="urlAction" namespace="/rekruitmen" action="action_rekruitmen" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.calonPegawaiId" /></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenuView" href="%{urlAction}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_reassign.ico"/>" name="icon_reassign">
                                                                </sj:a>
                                                            </s:else>
                                                        </display:column>
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
    window.listPosisi = function(branch, divisi){
        var branch = document.getElementById("branchId").value;
        var divisi = document.getElementById("departmentId").value;
        $('#positionId').empty();
        PositionAction.searchPosition2(branch, divisi, function(listdata){
            $.each(listdata, function (i, item) {
                $('#positionId').append($("<option></option>")
                        .attr("value",item.positionId)
                        .text(item.positionName));
            });
        });
    }
</script>

