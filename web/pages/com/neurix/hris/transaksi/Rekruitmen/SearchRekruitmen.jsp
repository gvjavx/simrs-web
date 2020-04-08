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
            Rekruitmen
            <small>e-HEALTH</small>
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
                    <table width="100%" align="center">
                        <tr>
                            <td align="center">
                                <s:form id="rekruitmenForm" method="post"  theme="simple" namespace="/rekruitmen" action="search_rekruitmen.action" cssClass="form-horizontal">

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
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Calon Pegawai Id :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:textfield  id="calonPegawaiId" name="rekruitmen.calonPegawaiId" required="false" readonly="false" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Nama Calon Pegawai :</small></label>
                                            </td>
                                            <td>
                                            <table>
                                                <s:textfield  id="namaCalonPegawai" name="rekruitmen.namaCalonPegawai" required="false" readonly="false" cssClass="form-control"/>
                                            </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Status Rekruitmen :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:action id="initComboStatus" namespace="/rekruitmen" name="searchStatusRekruitmen_rekruitmen"/>
                                                    <s:select list="#initComboStatus.listComboStatusRekruitmen" id="statusRekruitmen12" name="rekruitmen.status" listKey="statusRekruitmentId" listValue="statusRekruitmentName"
                                                              headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Unit :</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:action id="initComboBranch" namespace="/admin/branch"
                                                              name="initComboBranch_branch"/>
                                                    <s:select list="#initComboBranch.listOfComboBranch" id="branchId"
                                                              name="rekruitmen.branchId" onchange="listPosisi()"
                                                              listKey="branchId" listValue="branchName" headerKey=""
                                                              headerValue="[Select one]" cssClass="form-control"/>
                                                </table>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Bidang :</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:action id="comboDivisi" namespace="/department"
                                                              name="searchDepartment_department"/>
                                                    <s:select list="#comboDivisi.listComboDepartment" id="departmentId"
                                                              name="rekruitmen.departmentId" onchange="listPosisi()"
                                                              listKey="departmentId" listValue="departmentName"
                                                              headerKey="" headerValue="[Select one]"
                                                              cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label">
                                                    <small>Calon Jabatan :</small>
                                                </label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:action id="comboPosition" namespace="/admin/position"
                                                              name="searchPosition_position"/>
                                                    <s:select list="#comboPosition.listOfComboPosition" id="positionId"
                                                              name="rekruitmen.positionId"
                                                              listKey="positionId" listValue="positionName" headerKey=""
                                                              headerValue="[Select one]" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    <br>
                                    <div id="actions" class="form-actions">
                                        <table align="center">
                                            <tr>
                                                <td>
                                                    <sj:submit type="button" cssClass="btn btn-primary" formIds="rekruitmenForm" id="search" name="search"
                                                               onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                                        <i class="fa fa-search"></i>
                                                        Search
                                                    </sj:submit>
                                                </td>
                                                <td>
                                                    <a href="add_rekruitmen.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add Rekruitmen</a>
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
                                                               title="Rekruitmen ">
                                                        <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                    </sj:dialog>
                                                    <s:set name="listOfRekruitmen" value="#session.listOfResult" scope="request" />
                                                    <display:table name="listOfRekruitmen" class=" tableRekruitmen table table-condensed table-striped table-hover"
                                                                   requestURI="paging_displaytag_rekruitmen.action" export="true" id="row" pagesize="14" style="font-size:12">
                                                        <display:column media="html" title="<center>Cetak Kontrak</center>" style="text-align:center">
                                                            <s:if test="#attr.row.rekruitmenClosed">
                                                                <s:url var="urlCetakKontrak" namespace="/rekruitmen" action="cetakKontrak_rekruitmen" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.calonPegawaiId"/></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                </s:url>
                                                                <s:a href="%{urlCetakKontrak}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                                </s:a>
                                                            </s:if>
                                                            <s:else>
                                                                <%--<img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">--%>
                                                            </s:else>
                                                        </display:column>
                                                        <display:column media="html" title="<center>Cetak Report</center>" style="text-align:center">
                                                            <s:if test="#attr.row.readyclosed">
                                                                <s:url var="urlCetakKontrak" namespace="/rekruitmen" action="printReportRekruitmen_rekruitmen" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.calonPegawaiId"/></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                </s:url>
                                                                <s:a href="%{urlCetakKontrak}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_printer_new.ico"/>" name="icon_edit">
                                                                </s:a>
                                                            </s:if>
                                                            <s:else>
                                                                <%--<img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">--%>
                                                            </s:else>
                                                        </display:column>
                                                        <display:column media="html" title="Closed" style="text-align:center;font-size:9">
                                                            <s:if test="#attr.row.rekruitmenClosed">
                                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                            </s:if>
                                                            <s:elseif test="#attr.row.readyclosed">
                                                                <s:url var="urlAction" namespace="/rekruitmen" action="closed_rekruitmen" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.calonPegawaiId" /></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenuView" href="%{urlAction}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon approval">
                                                                </sj:a>
                                                            </s:elseif>
                                                            <s:else>
                                                                <%--<img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">--%>
                                                            </s:else>
                                                        </display:column>
                                                        <display:column media="html" title="Delete" style="text-align:center;font-size:9">
                                                            <s:if test="#attr.row.rekruitmenClosed">
                                                                <%--<img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">--%>
                                                            </s:if>
                                                            <s:else>
                                                                <s:url var="urlViewDelete" namespace="/rekruitmen" action="delete_rekruitmen" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.calonPegawaiId" /></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenuView" href="%{urlViewDelete}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                                </sj:a>
                                                            </s:else>
                                                        </display:column>
                                                        <display:column media="html" title="Edit" style="text-align:center;font-size:9">
                                                            <s:if test="#attr.row.rekruitmenClosed">
                                                                <%--<img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">--%>
                                                            </s:if>
                                                            <s:else>
                                                                <s:url var="urlUploadDoc" namespace="/rekruitmen" action="uploadDocument_rekruitmen" escapeAmp="false">
                                                                    <s:param name="id"><s:property value="#attr.row.calonPegawaiId" /></s:param>
                                                                    <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                                </s:url>
                                                                <sj:a onClickTopics="showDialogMenuView" href="%{urlUploadDoc}">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                </sj:a>
                                                            </s:else>
                                                        </display:column>
                                                        <display:column media="html" title="View" style="text-align:center;font-size:9">
                                                            <s:url var="urlViewDelete" namespace="/rekruitmen" action="viewRekruitmen_rekruitmen" escapeAmp="false">
                                                                <s:param name="id"><s:property value="#attr.row.calonPegawaiId" /></s:param>
                                                                <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                                            </s:url>
                                                            <sj:a onClickTopics="showDialogMenuView" href="%{urlViewDelete}">
                                                                <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">
                                                            </sj:a>
                                                        </display:column>
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
                                                        <%--<display:column property="calonPegawaiId" sortable="true" title="Cal Peg ID"  />--%>
                                                        <display:column property="namaCalonPegawai" sortable="true" title="Cal Peg Name"  />
                                                        <display:column property="noKtp" sortable="true" title="No. KTP" />
                                                        <display:column property="noTelp" sortable="true" title="No. Telp." />
                                                        <display:column property="posisiName" sortable="true" title="Jabatan" />
                                                        <display:column property="divisiName" sortable="true" title="Bidang" />
                                                        <display:column property="branchName" sortable="true" title="Unit" />
                                                        <display:column property="statusSaatIni" sortable="true" title="Status" />
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
    <div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:1400px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Approve Rekruitmen</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Dinas : </label>
                        <div class="col-sm-3">
                            <s:select list="#{'LN':'Luar Negeri'}" id="dinas1" name="rekruitmen.dinas" disabled="true"
                                      headerKey="DN" headerValue="Dalam Negeri" cssClass="form-control" />

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Berangkat : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="tanggalBerangkat1" name="nip">
                        </div>
                        <label class="control-label col-sm-2" >Tanggal Kembali : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="tanggalKembali1" name="nip">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-3" >Berangkat Dari: </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="berangkatDari1" name="nip">
                        </div>
                        <label class="control-label col-sm-2" >Tujuan : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="tujuan1" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Berangkat Via : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="berangkatVia1" name="nip">
                        </div>
                        <label class="control-label col-sm-2" >Kembali Via : </label>
                        <div class="col-sm-3">
                            <input readonly type="text" class="form-control nip" id="kembaliVia1" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keperluan : </label>
                        <div class="col-sm-8">
                            <textarea rows="3" readonly class="form-control" id="keperluan1" name="nip"></textarea>

                        </div>
                    </div>

                </form>
                <table style="width: 100%;" id="rekruitmenPersonTable" class="rekruitmenPersonTable table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
    <div id="modal-approve" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:1400px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Approve Rekruitmen</h4>
            </div>

            <div class="modal-body">
                <form class="form-horizontal" id="formApprove">
                    <div class="form-group">
                        <label class="control-label col-sm-2" >Rekruitmen Id : </label>
                        <div class="col-sm-4">
                            <input readonly type="text" class="form-control nip" id="rekruitmenIdApprove" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" >Note For Not Approve : </label>
                        <div class="col-sm-4">
                            <textarea rows="4" class="form-control" id="notApprove"></textarea>
                        </div>
                    </div>
                </form>
                <table style="width: 100%;" id="rekruitmenPersonTableApprove" class="rekruitmenPersonTableApprove table table-bordered">
                </table>
            </div>

            <div class="modal-footer">
                <a id="btnApprove" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Approve</a>
                <a id="btnNotApprove" data="not" type="button" class="btn btn-default btn-danger"><i class="fa fa-close"></i> Not Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>
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

