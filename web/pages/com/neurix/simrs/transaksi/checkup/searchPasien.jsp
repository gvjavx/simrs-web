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
                                                    <s:textfield cssStyle="margin-top: 7px" id="id_pasien" name="headerCheckup.idPasien" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>No KTP</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px"  id="no_ktp" name="headerCheckup.noKtp" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Nama Pasien</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield cssStyle="margin-top: 7px" id="nama_pasien" name="headerCheckup.nama" required="false" readonly="false" cssClass="form-control"/>
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
                                                    <s:select cssStyle="margin-top: 7px" list="#initComboStatus.listComboStatusRekruitmen" id="poli" name="headerCheckup.idPelayanan" listKey="statusRekruitmentId" listValue="statusRekruitmentName"
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
                                                              name="headerCheckup.statusPeriksa" onchange="listPosisi()"
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
                                    <%--<center>--%>
                                        <%--<table id="showdata" width="90%">--%>
                                            <%--<tr>--%>
                                                <%--<td align="center">--%>
                                                    <%--<sj:dialog id="waiting_dialog_loading" openTopics="showDialogLoading" closeTopics="closeDialogLoading" modal="true"--%>
                                                               <%--resizable="false"--%>
                                                               <%--height="350" width="600" autoOpen="false" title="Loading ...">--%>
                                                        <%--Please don't close this window, server is processing your request ...--%>
                                                        <%--</br>--%>
                                                        <%--</br>--%>
                                                        <%--</br>--%>
                                                        <%--<center>--%>
                                                            <%--<img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_read">--%>
                                                        <%--</center>--%>
                                                    <%--</sj:dialog>--%>
                                                    <%--<sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"--%>
                                                               <%--height="800" width="1100" autoOpen="false"--%>
                                                               <%--title="Rekruitmen ">--%>
                                                    <%--</sj:dialog>--%>
                                                    <%--<sj:dialog id="view_dialog_menu" openTopics="showDialogMenuView" modal="true"--%>
                                                               <%--height="700" width="1100" autoOpen="false"--%>
                                                               <%--title="Rekruitmen">--%>
                                                        <%--<center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>--%>
                                                    <%--</sj:dialog>--%>
                                                    <%--<s:set name="listOfRekruitmen" value="#session.listOfResult" scope="request" />--%>
                                                    <%--<display:table name="listOfRekruitmen" class=" tableRekruitmen table table-condensed table-striped table-hover"--%>
                                                                   <%--requestURI="paging_displaytag_pasien.action" export="true" id="row" pagesize="14" style="font-size:12">--%>

                                                        <%--<display:column property="noCheckup" sortable="true" title="No Checkup"  />--%>
                                                        <%--<display:column property="idPasien" sortable="true" title="ID Pasien" />--%>
                                                        <%--<display:column property="nama" sortable="true" title="Nama" />--%>
                                                        <%--<display:column property="namaPelayanan" sortable="true" title="Poli Terakhir" />--%>
                                                        <%--<display:column property="statusPeriksa" sortable="true" title="Status Terakhir" />--%>
                                                        <%--<display:column property="noCheckup" sortable="true" title="Ruangan" />--%>
                                                        <%--<display:column property="noCheckup" sortable="true" title="No" />--%>
                                                        <%--<display:column media="html" title="Action" style="text-align:center;font-size:9">--%>

                                                        <%--</display:column>--%>
                                                    <%--</display:table>--%>
                                                <%--</td>--%>
                                            <%--</tr>--%>
                                        <%--</table>--%>
                                    <%--</center>--%>

                                </s:form>
                            </td>
                        </tr>
                    </table>

                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="myTable" class="table table-bordered table-striped">
                                <thead >
                                <tr bgcolor="#00ced1">
                                    <td>No Checkup</td>
                                    <td>ID Pasien</td>
                                    <td>Nama</td>
                                    <td>Poli Terakhir</td>
                                    <td>Status Terakhir</td>
                                    <td>Ruangan</td>
                                    <td>No</td>
                                    <td>Action</td>
                                </tr>
                                </thead>
                                <tbody>
                                <s:iterator value="#session.listOfResult" status="listOfUsers">
                                    <tr>
                                        <td><s:property value="noCheckup"/></td>
                                        <td><s:property value="idPasien"/></td>
                                        <td><s:property value="nama"/></td>
                                        <td><s:property value="namaPelayanan"/></td>
                                        <td><s:property value="statusPeriksa"/></td>
                                        <td><s:property value="namaRuangan"/></td>
                                        <td><s:property value="noRuangan"/></td>
                                        <td><img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup"></td>
                                    </tr>
                                </s:iterator>
                                </tbody>
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
