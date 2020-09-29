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
<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/JadwalShiftKerjaAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CutiPegawaiAction.js"/>'></script>
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
            window.location.href="<s:url action='initForm_jadwalShiftKerja'/>";
        }
        var unit = '<s:property value="JadwalShiftKerja.unitId"/>'
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
            Jadwal Shift Kerja
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
                                    <s:form id="jadwalShiftKerjaForm" method="post"  theme="simple" namespace="/jadwalShiftKerja" action="search_jadwalShiftKerja.action" cssClass=" form-horizontal">
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
                                                    <label class="control-label"><small>Unit :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test='jadwalShiftKerja.adminHcm'>
                                                            <s:if test='jadwalShiftKerja.branchIdUser=="KP"'>
                                                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                                <s:select list="#initComboBranch.listOfComboBranch" id="branchid" name="jadwalShiftKerja.branchId"
                                                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            </s:if>
                                                            <s:else>
                                                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                                <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="jadwalShiftKerja.branchId" disabled="true"
                                                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                                <s:hidden id="branchid" name="jadwalShiftKerja.branchId" />
                                                            </s:else>
                                                        </s:if>
                                                        <s:else>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="jadwalShiftKerja.branchId" disabled="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden id="branchid" name="jadwalShiftKerja.branchId" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Grup :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test='jadwalShiftKerja.adminHcm'>
                                                            <s:action id="comboSubDiv" namespace="/positionBagian" name="searchPositionBagian_positionBagian"/>
                                                            <s:select list="#comboSubDiv.comboListOfPositionBagian" id="profesiId" name="jadwalShiftKerja.groupId"
                                                                      listKey="bagianId" listValue="bagianName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:action id="comboSubDiv" namespace="/positionBagian" name="searchPositionBagian_positionBagian"/>
                                                            <s:select list="#comboSubDiv.comboListOfPositionBagian" id="profesiIdView" name="jadwalShiftKerja.groupId" disabled="true"
                                                                      listKey="bagianId" listValue="bagianName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                                            <s:hidden name="jadwalShiftKerja.groupId" id="profesiId" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>NIP :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="nip" name="jadwalShiftKerja.nip" required="true" disabled="false" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <script type='text/javascript'>
                                                var functions, mapped;
                                                $('#nip').typeahead({
                                                    minLength: 1,
                                                    source: function (query, process) {
                                                        functions = [];
                                                        mapped = {};
                                                        var data = [];
                                                        var unit = $('#branchid').val();
                                                        if (unit==""){
                                                            alert("unit is empty");
                                                            $('#namaId').val("");
                                                            $('#nip').val("");
                                                        }else {
                                                            dwr.engine.setAsync(false);
                                                            CutiPegawaiAction.initComboPersonil(query, unit, function (listdata) {
                                                                data = listdata;
                                                            });
                                                            $.each(data, function (i, item) {
                                                                var labelItem = item.nip+" "+item.namaPegawai;
                                                                mapped[labelItem] = {id: item.nip, label: labelItem,nama:item.namaPegawai};
                                                                functions.push(labelItem);
                                                            });
                                                            process(functions);
                                                        }
                                                    },
                                                    updater: function (item) {
                                                        var selectedObj = mapped[item];
                                                        var namaMember = selectedObj.label;
                                                        document.getElementById("namaId").value = selectedObj.nama;
                                                        return selectedObj.id;
                                                    }
                                                });
                                            </script>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nama :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield id="namaId" name="" required="true" readonly="true" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tanggal :</small></label>
                                                </td>
                                                <td>
                                                    <div class="input-group date">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="loginTimestampFrom" name="jadwalShiftKerja.stTanggalAwal" readonly="true" size="12" cssClass="form-control pull-right"
                                                                     required="false" cssStyle="background-color: white"/>
                                                        <div class="input-group-addon">
                                                            s/d
                                                        </div>
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="loginTimestampTo" name="jadwalShiftKerja.stTanggalAkhir" size="12" cssClass="form-control pull-right" readonly="true"
                                                                     required="false" cssStyle="background-color: white"/>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="jadwalShiftKerjaForm" id="search" name="search"
                                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <a href="add_jadwalShiftKerja.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add Jadwal Shift Kerja</a>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_jadwalShiftKerja"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </td>
                                                </tr>
                                            </table>
                                            <div class="form-group">
                                                <label class="control-label col-sm-5"></label>
                                                <div class="col-sm-5" style="display: none">

                                                    <sj:dialog id="waiting_dialog" openTopics="showDialogLoading"
                                                               closeTopics="closeDialog" modal="true"
                                                               resizable="false"
                                                               height="250" width="600" autoOpen="false"
                                                               title="Searching ...">
                                                        Please don't close this window, server is processing your request ...
                                                        <br>
                                                        <center>
                                                            <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                                 src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                                 name="image_indicator_write">
                                                            <br>
                                                            <img class="spin" border="0"
                                                                 style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                                 src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                                 name="image_indicator_write">
                                                        </center>
                                                    </sj:dialog>
                                                    <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                                               resizable="false"
                                                               closeOnEscape="false"
                                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                               buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         window.location.reload(true);
                                                                                     }
                                                                            }"
                                                    >
                                                        <s:hidden id="close_pos"></s:hidden>
                                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                                             name="icon_success">
                                                        Record has been saved successfully.
                                                    </sj:dialog>
                                                    <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true"
                                                               resizable="false" cssStyle="text-align:left;"
                                                               height="650" width="900" autoOpen="false" title="View Detail"
                                                    >
                                                        <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                                                     alt="Loading..."/></center>
                                                    </sj:dialog>
                                                </div>
                                            </div>
                                        </div>
                                        <br>
                                        <br>
                                        <div style="text-align: left !important;">
                                            <div class="box-header with-border"></div>
                                            <div class="box-header with-border">
                                                <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Jadwal Shift Kerja</h3>
                                            </div>
                                            <div class="box-body">
                                                <table id="tableJadwalShiftKerja" class="tableJadwalShiftKerja table table-bordered table-striped" style="font-size: 11px">
                                                    <thead >
                                                    <tr bgcolor="#90ee90" style="text-align: center">
                                                        <td>ID </td>
                                                        <td>Unit </td>
                                                        <td>Tanggal </td>
                                                        <td>Shift </td>
                                                        <td>Nama Shift </td>
                                                        <td>Nama </td>
                                                        <td>Posisi </td>
                                                        <td>Group </td>
                                                        <td align="center">Edit</td>
                                                        <td align="center">On Call</td>
                                                        <td align="center">Panggil</td>
                                                        <td align="center">Libur</td>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <s:iterator value="#session.listOfResultJadwalShiftKerja" var="row">
                                                        <tr>
                                                            <td style="text-align: center"><s:property value="jadwalShiftKerjaId"/></td>
                                                            <td style="text-align: center"><s:property value="branchName"/></td>
                                                            <td><s:property value="stTanggal"/></td>
                                                            <td><s:property value="shiftName"/></td>
                                                            <td><s:property value="shiftName2"/></td>
                                                            <td><s:property value="namaPegawai"/></td>
                                                            <td><s:property value="positionName"/></td>
                                                            <td><s:property value="profesiName"/></td>
                                                            <td align="center">
                                                                <s:if test="#attr.row.tamp">
                                                                    <s:if test="#attr.row.flagYes">
                                                                        <s:url var="urlEdit" namespace="/jadwalShiftKerja" action="edit_jadwalShiftKerja" escapeAmp="false">
                                                                            <s:param name="id"><s:property value="#attr.row.jadwalShiftKerjaId"/></s:param>
                                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                        </s:url>
                                                                        <s:a href="%{urlEdit}">
                                                                            <img border="0" src="<s:url value="/pages/images/icons8-create-25.png"/>" name="icon_edit">
                                                                        </s:a>
                                                                    </s:if>
                                                                </s:if>
                                                            </td>
                                                            <td align="center">
                                                                <s:if test='#row.onCall == "Y"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                                                </s:if>
                                                            </td>
                                                            <td align="center">
                                                                <s:if test='#attr.row.hariIni'>
                                                                    <s:if test='#attr.row.onCall=="Y"'>
                                                                        <s:if test='#attr.row.flagPanggil=="Y"'>
                                                                            <img border="0"
                                                                                 src="<s:url value="/pages/images/icon_success.ico"/>"
                                                                                 name="icon_edit">
                                                                        </s:if>
                                                                        <s:else>
                                                                        <a href="javascript:;"
                                                                           id="<s:property value="%{#attr.row.jadwalShiftKerjaDetailId}"/>"
                                                                           tanggal="<s:property value="%{#attr.row.stTanggal}"/>"
                                                                           nama="<s:property value="%{#attr.row.namaPegawai}"/>"
                                                                           posisi="<s:property value="%{#attr.row.positionName}"/>"
                                                                           grup="<s:property value="%{#attr.row.profesiName}"/>"
                                                                           href="javascript:;" class="item-panggil">
                                                                            <img border="0"
                                                                                 src="<s:url value="/pages/images/icons8-call-25.png"/>">
                                                                        </s:else>
                                                                    </s:if>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:if test='#attr.row.flagPanggil=="Y"'>
                                                                        <img border="0"
                                                                             src="<s:url value="/pages/images/icon_success.ico"/>"
                                                                             name="icon_edit">
                                                                    </s:if>
                                                                </s:else>
                                                            </td>
                                                            <td align="center">
                                                                <s:if test='#row.flagLibur == "Y"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                                                </s:if>
                                                                <s:else>
                                                                    <s:if test='#row.flagDokterKso=="Y"'>
                                                                    <a href="javascript:;"
                                                                       id="<s:property value="%{#attr.row.jadwalShiftKerjaDetailId}"/>"
                                                                       tanggal="<s:property value="%{#attr.row.stTanggal}"/>"
                                                                       nama="<s:property value="%{#attr.row.namaPegawai}"/>"
                                                                       posisi="<s:property value="%{#attr.row.positionName}"/>"
                                                                       grup="<s:property value="%{#attr.row.profesiName}"/>"
                                                                       href="javascript:;" class="item-libur">
                                                                        <img border="0"
                                                                             src="<s:url value="/pages/images/icons8-weekend-25.png"/>">
                                                                    </s:if>
                                                                </s:else>
                                                            </td>
                                                        </tr>
                                                    </s:iterator>
                                                    </tbody>
                                                </table>
                                            </div>
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
<div id="modal-add-auto" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Tambah Otomatis Jadwal Shift Kerja</h4>
            </div>
            <div class="modal-body">
                <s:form id="saveAddJadwalAuto" method="post" theme="simple" namespace="/jadwalShiftKerja" action="saveAddJadwalAuto_jadwalShiftKerja" cssClass="form-horizontal">
                <center>
                    <div class="container">
                        <div class="">
                            <div class="row">
                                <div class="col-md-7">
                                    <div class="col-md-3"><label class="control-label"><small>Tanggal Awal Jadwal :</small></label> </div>
                                    <div class="col-md-4">
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tglAwalJadwal" name="jadwalShiftKerja.stTanggalAwal" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-7">
                                    <div class="col-md-3"><label class="control-label"><small>Tanggal Akhir Jadwal :</small></label> </div>
                                    <div class="col-md-4">
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tglAkhirJadwal" name="jadwalShiftKerja.stTanggalAkhir" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <button type="button" id="btnProses" class="btn btn-success">
                        <i class="fa fa-check"></i> Proses
                    </button>
                </center>
            </div>
            <br>
            </s:form>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-panggil">
    <div class="modal-dialog modal-flat modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Panggil Pegawai On Call</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">ID Jadwal</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_id" onkeypress="$(this).css('border','')" readonly="true" cssStyle="margin-top: 7px"
                                                 cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Tanggal</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_tanggal" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control"  />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Nama</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_nama" onkeypress="$(this).css('border','')" readonly="true" cssStyle="margin-top: 7px"
                                                 cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Posisi</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_posisi" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">Grup</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_grup" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <br>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" id="btnPanggil" data-dismiss="modal"><i class="fa fa-arrow-right"></i> Panggil</button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-libur">
    <div class="modal-dialog modal-flat modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Liburkan Pegawai</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">ID Jadwal</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_id_libur" onkeypress="$(this).css('border','')" readonly="true" cssStyle="margin-top: 7px"
                                                 cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Tanggal</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_tanggal_libur" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control"  />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Nama</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_nama_libur" onkeypress="$(this).css('border','')" readonly="true" cssStyle="margin-top: 7px"
                                                 cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Posisi</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_posisi_libur" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">Grup</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_grup_libur" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <br>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" id="btnLibur" data-dismiss="modal"><i class="fa fa-arrow-right"></i> Libur</button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-loading-dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Saving ...
                </h4>
            </div>
            <div class="modal-body">
                <div id="waiting-content" style="text-align: center">
                    <h4>Please don't close this window, server is processing your request ...</h4>
                    <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                         src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                         name="image_indicator_write">
                    <br>
                    <img class="spin" border="0"
                         style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                         src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                         name="image_indicator_write">
                </div>

                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_fin_waiting">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_fin_error_waiting"></p>
                </div>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-success-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Success
                </h4>
            </div>
            <div class="modal-body" style="text-align: center">
                <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                     name="icon_success">
                Record has been saved successfully.
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No--%>
                <%--</button>--%>
                <button type="button" class="btn btn-sm btn-success" id="ok_con"><i class="fa fa-check"></i> Ok
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        function showDialog(tipe) {
            if (tipe == "loading"){
                $("#modal-loading-dialog").modal('show');
            }
            if (tipe == "error"){
                $("#modal-loading-dialog").modal('show');
                $("#waiting-content").hide();
                $("#warning_fin_waiting").show();
//            $("#msg_fin_error_waiting").text("Error. perbaikan");
            }
            if (tipe == "success"){
                $("#modal-loading-dialog").modal('hide');
                $("#modal-success-dialog").modal('show');
            }
        }

        $('#btnAddJadwalOtomatis').on('click', function () {
            $('#modal-add-auto').find('.modal-title').text('Tambah Otomatis Jadwal Shift Kerja');
            $('#modal-add-auto').modal('show');
        });
        $('#btnProses').on('click', function () {
            var tglAwal =$('#loginTimestampFrom').val();
            var tglAkhir =$('#loginTimestampTo').val();
            if (tglAwal!=""&&tglAkhir!=""){
                if (confirm("Apakah anda ingin melakukan proses penambahan jadwal ?")){
                    dwr.engine.setAsync(false);
                    JadwalShiftKerjaAction.saveAddJadwalAuto(tglAwal,tglAkhir,function(data) {
                        if (data!=""){
                            alert(data);
                        } else{
                            alert("jadwal berhasil ditambahkan");
                            $('#modal-add-auto').modal('hide');
                            $('#loginTimestampFrom').val("");
                            $('#loginTimestampTo').val("");
                        }
                    });
                }
            }else{
                var msg = "";
                if (tglAwal == "") {
                    msg+='tanggal awal masih kosong \n';
                }
                if (tglAkhir == "") {
                    msg+='tanggal akhir masih kosong \n';
                }
                alert(msg);
            }
        });
        $('#btnCetakJadwal').click(function(){
            var tglFrom = document.getElementById("loginTimestampFrom").value;
            var tglTo = document.getElementById("loginTimestampTo").value;
            if (tglFrom!=""&&tglTo!=""){
                if (confirm('Apakah Anda ingin mencetak jadwal kerja pada tanggal '+tglFrom+' sampai tanggal '+tglTo+' ?')) {
                    window.location.href = "printJadwalShiftKerja_jadwalShiftKerja.action?tglFrom="+tglFrom+"&tglTo="+tglTo;
                }
            }else{
                alert("ada tanggal yang masih kosong");
            }
        });

        $('.tableJadwalShiftKerja').on('click', '.item-panggil', function() {
            $('#mod_id').val($(this).attr('id'));
            $('#mod_tanggal').val($(this).attr('tanggal'));
            $('#mod_nama').val($(this).attr('nama'));
            $('#mod_posisi').val($(this).attr('posisi'));
            $('#mod_grup').val($(this).attr('grup'));

            $("#modal-panggil").modal('show');
        });

        $('.tableJadwalShiftKerja').on('click', '.item-libur', function() {
            $('#mod_id_libur').val($(this).attr('id'));
            $('#mod_tanggal_libur').val($(this).attr('tanggal'));
            $('#mod_nama_libur').val($(this).attr('nama'));
            $('#mod_posisi_libur').val($(this).attr('posisi'));
            $('#mod_grup_libur').val($(this).attr('grup'));

            $("#modal-libur").modal('show');
        });

        $('#btnPanggil').click(function () {
            var id = $('#mod_id').val();
            if (confirm("Apakah anda ingin memanggil pegawai ini ?")){
                showDialog("loading");
                dwr.engine.setAsync(true);
                JadwalShiftKerjaAction.savePanggilBerdasarkanId(id,function() {
                    dwr.engine.setAsync(false);
                    $('#modal-panggil').modal('hide');
                    showDialog("success");
                });
            }
        });
        $('#btnLibur').click(function () {
            var id = $('#mod_id_libur').val();
            if (confirm("Apakah anda ingin liburkan jadwal pegawai ini ?")){
                showDialog("loading");
                dwr.engine.setAsync(true);
                JadwalShiftKerjaAction.saveLiburBerdasarkanId(id,function() {
                    dwr.engine.setAsync(false);
                    $('#modal-libur').modal('hide');
                    showDialog("success");
                });
            }
        });
        $('#ok_con').click(function () {
            window.location.reload();
        });
        $('#tableJadwalShiftKerja').DataTable({
            "pageLength": 100,
            "order": []
        });
    });
</script>