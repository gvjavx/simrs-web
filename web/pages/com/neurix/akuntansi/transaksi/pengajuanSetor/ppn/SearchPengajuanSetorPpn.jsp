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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PengajuanSetorAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>
    <style>
        .modal-backdrop {
            z-index: -1;
        }
    </style>
    <style>
        .pagebanner{
            width: 100%;
            font-size: 14px;
        }
        .pagelinks{
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }
    </style>
    <script type='text/javascript'>
        function link(){
            window.location.href="<s:url action='initFormPengajuanSetorPpn_pengajuanSetor'/>";
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
            Pengajuan Setor PPN
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pengajuan Setor PPN </h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="pengajuanSetorForm" method="post"  theme="simple" namespace="/pengajuanSetor" action="searchPengajuanSetorPpn_pengajuanSetor.action" cssClass="form-horizontal">
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
                                                        <s:if test='pengajuanSetor.branchId == "KP"'>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="pengajuanSetor.branchId"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="pengajuanSetor.branchId" disabled="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden id="branchId" name="pengajuanSetor.branchId" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Pengajuan Setor ID :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield  id="pengajuanSetorId" name="pengajuanSetor.pengajuanSetorId" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Keterangan :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield  id="keterangan" name="pengajuanSetor.keterangan" cssClass="form-control"/>
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
                                                        <s:textfield id="tgl1" name="pengajuanSetor.stTanggalDari" cssClass="form-control pull-right"
                                                                     required="false"/>
                                                        <div class="input-group-addon">
                                                            s/d
                                                        </div>
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="tgl2" name="pengajuanSetor.stTanggalSelesai" cssClass="form-control pull-right"
                                                                     required="false"/>
                                                    </div>
                                                    <script>
                                                        $("#tgl1").datepicker({
                                                            setDate: new Date(),
                                                            autoclose: true,
                                                            changeMonth: true,
                                                            changeYear:true,
                                                            dateFormat:'dd-mm-yy'
                                                        });
                                                        $("#tgl1").datepicker("setDate", new Date());
                                                        $("#tgl2").datepicker({
                                                            setDate: new Date(),
                                                            autoclose: true,
                                                            changeMonth: true,
                                                            changeYear:true,
                                                            dateFormat:'dd-mm-yy'
                                                        });
                                                        $("#tgl2").datepicker("setDate", new Date());
                                                    </script>
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="pengajuanSetorForm" id="search" name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <a href="addPengajuanSetorPpn_pengajuanSetor.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add Pengajuan Setor</a>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initFormPengajuanSetorPpn_pengajuanSetor"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>

                                        <br>
                                        <br>
                                        <div style="text-align: left !important;">
                                            <div class="box-header with-border"></div>
                                            <div class="box-header with-border">
                                                <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pengajuan Setor PPN</h3>
                                            </div>
                                            <div class="box-body">
                                                <table id="myTable" class="tablePengajuanSetor table table-bordered table-striped">
                                                    <thead >
                                                    <tr bgcolor="#90ee90">
                                                        <td>ID</td>
                                                        <td>Tanggal Pengajuan</td>
                                                        <td>PPN Keluaran (RP)</td>
                                                        <td>PPN Masukan B2(RP)</td>
                                                        <td>PPN Masukan B3 (RP)</td>
                                                        <td>Total (RP)</td>
                                                        <td>Keterangan</td>
                                                        <td align="center">View</td>
                                                        <td align="center">Batal</td>
                                                        <td align="center">Posting</td>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <s:iterator value="#session.listOfResult" var="row">
                                                        <tr>
                                                            <td><s:property value="pengajuanSetorId"/></td>
                                                            <td><s:property value="stRegisteredDate"/></td>
                                                            <td style="text-align: right"><s:property value="stJumlahPpnKeluaran"/></td>
                                                            <td style="text-align: right"><s:property value="stJumlahPpnMasukanB2"/></td>
                                                            <td style="text-align: right"><s:property value="stJumlahPpnMasukanB3"/></td>
                                                            <td style="text-align: right"><s:property value="stJumlahSeluruhnya"/></td>
                                                            <td><s:property value="keterangan"/></td>
                                                            <td align="center">
                                                                <a href="javascript:;" data="<s:property value="%{#attr.row.pengajuanSetorId}"/>" class="item-view">
                                                                    <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" name="icon_view">
                                                                </a>
                                                            </td>
                                                            <td align="center">
                                                                <s:if test='#row.cancelFlag == "Y"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_batal">
                                                                </s:if>
                                                                <s:elseif test='#row.approvalFlag == "Y"'>
                                                                </s:elseif>
                                                                <s:else>
                                                                    <a href="javascript:;" data="<s:property value="%{#attr.row.pengajuanSetorId}"/>" class="item-delete">
                                                                        <img border="0" src="<s:url value="/pages/images/icons8-trash-can-25.png"/>" name="icon_delete">
                                                                    </a>
                                                                </s:else>
                                                            </td>
                                                            <td align="center">
                                                                <s:if test='#row.cancelFlag == "Y"'>
                                                                </s:if>
                                                                <s:elseif test='#row.approvalFlag == "Y"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_batal">
                                                                </s:elseif>
                                                                <s:else>
                                                                    <s:if test='#row.branchId == "KP"'>
                                                                        <a href="javascript:;" data="<s:property value="%{#attr.row.pengajuanSetorId}"/>" class="item-posting">
                                                                            <img border="0" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>" name="icon_posting">
                                                                        </a>
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
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>
<div class="modal fade" id="modal-setor">
    <div class="modal-dialog modal-flat" style="width:1300px;">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-money"></i> Pengajuan Setor</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <div class="row">
                        <div class="col-md-12">
                            <br>
                            <br>
                            <div class="form-group">
                                <label class="col-md-4">Pengajuan Setor ID</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_pengajuan_setor_id" onkeypress="$(this).css('border','')" readonly="true" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Unit </label>
                                <div class="col-md-6">
                                    <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                    <s:select list="#initComboBranch.listOfComboBranch" id="mod_branch_id" disabled="true"
                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">Tanggal Pengajuan</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_tanggal_pengajuan" onkeypress="$(this).css('border','')" readonly="true" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">Total PPN Keluaran (RP)</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_total_ppn_keluaran" cssStyle="text-align: right" onkeypress="$(this).css('border','')" readonly="true" cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">Total PPN Masukan B2(RP)</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_total_ppn_masukan_b2" cssStyle="text-align: right" onkeypress="$(this).css('border','')" readonly="true" cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">Total PPN Masukan B3 (RP)</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_total_ppn_masukan_b3" cssStyle="text-align: right" onkeypress="$(this).css('border','')" readonly="true" cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">Total Seluruhnya (RP)</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_total_ppn_seluruhnya" cssStyle="text-align: right" onkeypress="$(this).css('border','')" readonly="true" cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <br>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <div class="box-header with-border"></div>
                                    <div class="box-header with-border">
                                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar PPN</h3>
                                    </div>
                                    <div class="box-body">
                                        <ul class="nav nav-pills" id="tabPane">
                                            <li class="active"><a href="#keluaran" data-toggle="tab">PPN Keluaran</a></li>
                                            <li><a href="#masukanb2" data-toggle="tab">PPN Masukan B2</a></li>
                                            <li><a href="#masukanb3" data-toggle="tab">PPN Masukan B3</a></li>
                                        </ul>
                                        <br>
                                        <br>
                                        <div class="tab-content clearfix">
                                            <div id="keluaran" class="tab-pane active col-md-12 ">
                                                <table id="table1" class="table table-bordered table-striped sortTable">
                                                </table>
                                            </div>
                                            <div id="masukanb2" class="tab-pane fade">
                                                <table id="table2" class="table table-bordered table-striped sortTable">
                                                </table>
                                            </div>
                                            <div id="masukanb3" class="tab-pane fade">
                                                <table id="table3" class="table table-bordered table-striped sortTable">
                                                </table>
                                            </div>
                                        </div>
                                        <div class="box-header with-border"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" id="btnBatalPengajuan" data-dismiss="modal"><i class="fa fa-arrow-right"></i> Batal</button>
                <button type="button" class="btn btn-success" id="btnPostingPengajuan" data-dismiss="modal"><i class="fa fa-arrow-right"></i> Posting</button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>

<script>
    window.loadSessionPpnMasukanB2= function(){
        $('#table2').find('tbody').remove();
        $('#table2').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PengajuanSetorAction.searchDataSessionPpnMasukanB2(function (listdata) {

            tmp_table = "<thead style='font-size: 12px;' ><tr class='active'>" +
                "<th style='text-align: center; background-color:  #90ee90'>ID</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Tipe</th>" +
                "<th style='text-align: center; background-color:  #90ee90''>Sumber Jurnal</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>PPN (RP)</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Keterangan</th>" +
                "</tr></thead>";
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 10px;" ">' +
                    '<td >' + item.transaksiId + '</td>' +
                    '<td align="center">' + item.tipe + '</td>' +
                    '<td align="left">' + item.personId+ '</td>' +
                    '<td align="right">' + item.stJumlah + '</td>' +
                    '<td align="left">' + item.note + '</td>' +
                    "</tr>";
            });
            $('#table2').append(tmp_table);
        });
    };
    window.loadSessionPpnMasukanB3= function(){
        $('#table3').find('tbody').remove();
        $('#table3').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PengajuanSetorAction.searchDataSessionPpnMasukanB3(function (listdata) {

            tmp_table = "<thead style='font-size: 12px;' ><tr class='active'>" +
                "<th style='text-align: center; background-color:  #90ee90'>ID</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Tipe</th>" +
                "<th style='text-align: center; background-color:  #90ee90''>Sumber Jurnal</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>PPN (RP)</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Keterangan</th>" +
                "</tr></thead>";
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 10px;" ">' +
                    '<td >' + item.transaksiId + '</td>' +
                    '<td align="center">' + item.tipe + '</td>' +
                    '<td align="left">' + item.personId+ '</td>' +
                    '<td align="right">' + item.stJumlah + '</td>' +
                    '<td align="left">' + item.note + '</td>' +
                    "</tr>";
            });
            $('#table3').append(tmp_table);
        });
    };
    window.loadSessionPpnKeluaran= function(){
        $('#table1').find('tbody').remove();
        $('#table1').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PengajuanSetorAction.searchDataSessionPpnKeluaran(function (listdata) {
            tmp_table = "<thead style='font-size: 14px;' ><tr class='active'>" +
                "<th style='text-align: center; background-color:  #90ee90'>ID</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Tipe</th>" +
                "<th style='text-align: center; background-color:  #90ee90''>Sumber Jurnal</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>PPN (RP)</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Keterangan</th>" +
                "</tr></thead>";
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td >' + item.transaksiId + '</td>' +
                    '<td align="center">' + item.tipe + '</td>' +
                    '<td align="left">' + item.personId+ '</td>' +
                    '<td align="right">' + item.stJumlah + '</td>' +
                    '<td align="left">' + item.note + '</td>' +
                    "</tr>";
            });
            $('#table1').append(tmp_table);
        });
    };
    $('.tablePengajuanSetor').on('click', '.item-posting', function() {
        var pengajuanId = $(this).attr('data');
        $('#mod_pengajuan_setor_id').val(pengajuanId);
        PengajuanSetorAction.getForModalPopUp(pengajuanId,function (data) {
            $('#mod_branch_id').val(data.branchId);
            $('#mod_tanggal_pengajuan').val(data.stRegisteredDate);
            $('#mod_total_ppn_keluaran').val(data.stJumlahPpnKeluaran);
            $('#mod_total_ppn_masukan_b2').val(data.stJumlahPpnMasukanB2);
            $('#mod_total_ppn_masukan_b3').val(data.stJumlahPpnMasukanB3);
            $('#mod_total_ppn_seluruhnya').val(data.stJumlahSeluruhnya);
        });
        loadSessionPpnMasukanB2();
        loadSessionPpnKeluaran();
        loadSessionPpnMasukanB3();
        $("#modal-setor").find('.modal-title').text('Posting Pengajuan Setor PPN');
        $("#modal-setor").modal('show');
        $("#btnPostingPengajuan").show();
        $("#btnBatalPengajuan").hide();
        $('#table1').DataTable({
            paging: false,
            "bDestroy": true,
            "order": [[1, "asc"]]
        });
        $('#table2').DataTable({
            paging: false,
            "bDestroy": true,
            "order": [[1, "asc"]]
        });
        $('#table3').DataTable({
            paging: false,
            "bDestroy": true,
            "order": [[1, "asc"]]
        });
    });
    $('.tablePengajuanSetor').on('click', '.item-view', function() {
        var pengajuanId = $(this).attr('data');
        $('#mod_pengajuan_setor_id').val(pengajuanId);
        PengajuanSetorAction.getForModalPopUp(pengajuanId,function (data) {
            $('#mod_branch_id').val(data.branchId);
            $('#mod_tanggal_pengajuan').val(data.stRegisteredDate);
            $('#mod_total_ppn_keluaran').val(data.stJumlahPpnKeluaran);
            $('#mod_total_ppn_masukan_b2').val(data.stJumlahPpnMasukanB2);
            $('#mod_total_ppn_masukan_b3').val(data.stJumlahPpnMasukanB3);
            $('#mod_total_ppn_seluruhnya').val(data.stJumlahSeluruhnya);
        });
        loadSessionPpnMasukanB2();
        loadSessionPpnKeluaran();
        loadSessionPpnMasukanB3();
        $("#modal-setor").find('.modal-title').text('View Pengajuan Setor PPN');
        $("#modal-setor").modal('show');
        $("#btnPostingPengajuan").hide();
        $("#btnBatalPengajuan").hide();
        $('#table1').DataTable({
            paging: false,
            "bDestroy": true,
            "order": [[1, "asc"]]
        });
        $('#table2').DataTable({
            paging: false,
            "bDestroy": true,
            "order": [[1, "asc"]]
        });
        $('#table3').DataTable({
            paging: false,
            "bDestroy": true,
            "order": [[1, "asc"]]
        });
    });
    $('.tablePengajuanSetor').on('click', '.item-delete', function() {
        var pengajuanId = $(this).attr('data');
        $('#mod_pengajuan_setor_id').val(pengajuanId);
        PengajuanSetorAction.getForModalPopUp(pengajuanId,function (data) {
            $('#mod_branch_id').val(data.branchId);
            $('#mod_tanggal_pengajuan').val(data.stRegisteredDate);
            $('#mod_total_ppn_keluaran').val(data.stJumlahPpnKeluaran);
            $('#mod_total_ppn_masukan_b2').val(data.stJumlahPpnMasukanB2);
            $('#mod_total_ppn_masukan_b3').val(data.stJumlahPpnMasukanB3);
            $('#mod_total_ppn_seluruhnya').val(data.stJumlahSeluruhnya);
        });
        loadSessionPpnMasukanB2();
        loadSessionPpnKeluaran();
        loadSessionPpnMasukanB3();
        $("#modal-setor").find('.modal-title').text('Batalkan Pengajuan Setor PPN');
        $("#modal-setor").modal('show');
        $("#btnPostingPengajuan").hide();
        $("#btnBatalPengajuan").show();
        $('#table1').DataTable({
            paging: false,
            "bDestroy": true,
            "order": [[1, "asc"]]
        });
        $('#table2').DataTable({
            paging: false,
            "bDestroy": true,
            "order": [[1, "asc"]]
        });
        $('#table3').DataTable({
            paging: false,
            "bDestroy": true,
            "order": [[1, "asc"]]
        });
    });

    $('#btnPostingPengajuan').click(function () {
        var pengajuanSetorId =  $('#mod_pengajuan_setor_id').val();
        if (confirm("apakah anda ingin memposting pengajuan setor PPN dengan ID Pengajuan Setor "+pengajuanSetorId +" ?")){
            PengajuanSetorAction.postingJurnal(pengajuanSetorId,function (listdata) {
                alert(listdata);
                window.location.reload();
            })
        }
    });
    $('#btnBatalPengajuan').click(function () {
        var pengajuanSetorId =  $('#mod_pengajuan_setor_id').val();
        if (confirm("apakah anda ingin membatalkan pengajuan setor PPN dengan ID Pengajuan Setor "+pengajuanSetorId +" ?")){
            PengajuanSetorAction.batalkanPengajuan(pengajuanSetorId,function (listdata) {
                alert(listdata);
                window.location.reload();
            })
        }
    })
</script>