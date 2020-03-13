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
    </style>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/VerifikatorAction.js"/>'></script>
    <script type='text/javascript'>

        $( document ).ready(function() {
            $('#verifikasi_rawat_inap').addClass('active');
        });


    </script>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>
<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Verifikasi Tindakan Rawat Inap
            <small>e-HEALTH</small>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Verifikasi Tindakan Rawat Inap</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="verifrawatinapForm" method="post" namespace="/verifrawatinap" action="searchRawatInap_verifrawatinap.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Pasien</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="rawatInap.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="rawatInap.namaPasien"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'2':'Rujuk','3':'Selesai'}" cssStyle="margin-top: 7px"
                                                  id="status" name="rawatInap.statusPeriksa"
                                                  headerKey="1" headerValue="Periksa"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal Masuk</label>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_from" name="rawatInap.stTglFrom" cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_to" name="rawatInap.stTglTo" cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="verifrawatinapForm" id="search" name="search"
                                                   onClickTopics="showDialogLoading" onCompleteTopics="closeDialogLoading" >
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_verifrawatinapForm.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                    </div>
                                </div>
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
                                                <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
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
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true" resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pasien</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>ID Detail Checkup</td>
                                <td>No SEP</td>
                                <td>Nama</td>
                                <td>Status Periksa</td>
                                <td >Status</td>
                                <td>Keterangan</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" status="listOfRawatJalan" var="row">
                                <tr>
                                    <td><s:property value="idDetailCheckup"/></td>
                                    <td><s:property value="noSep"/></td>
                                    <td><s:property value="namaPasien"/></td>
                                    <td><s:property value="statusPeriksaName"/></td>
                                    <td style="vertical-align: middle">
                                        <s:if test='#row.klaimBpjsFlag == "Y"'>
                                            <label class="label label-success"> sudah finasisasi</label>
                                        </s:if>
                                        <s:else>
                                            <s:if test='#row.cekApprove == false'>
                                                <label class="label label-info"> sudah diverifikasi</label>
                                            </s:if>
                                            <s:else>
                                                <label class="label label-warning"> belum diverifikasi</label>
                                            </s:else>
                                        </s:else>
                                    </td>
                                    <td><s:property value="keteranganSelesai"/></td>
                                    <td align="center">
                                        <s:if test='#row.klaimBpjsFlag == "Y"'>
                                        </s:if>
                                        <s:else>
                                            <s:if test='#row.cekApprove == false'>
                                                <s:if test='#row.statusPeriksa == "3"'>
                                                    <img id="t_<s:property value="noCheckup"/>" onclick="finalClaim('<s:property value="noCheckup"/>','<s:property value="idDetailCheckup"/>','<s:property value="idPasien"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>" style="cursor: pointer;">
                                                </s:if>
                                            </s:if>
                                            <s:else>
                                                <img id="v_<s:property value="noCheckup"/>" onclick="detailTindakan('<s:property value="noCheckup"/>','<s:property value="idDetailCheckup"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                            </s:else>
                                        </s:else>
                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-detail-pasien">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Detail Tindakan Rawat Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_tin">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_tin"></p>
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_tin">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_tin2"></p>
                </div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>No SEP</b></td>
                                    <td style="vertical-align: middle"><span class="label label-success" id="det_no_sep"></span></td>
                                </tr>
                                <tr>
                                    <td><b>No RM</b></td>
                                    <td><span id="det_no_rm"></span></td>
                                </tr>
                                <tr>
                                    <td><b>No Checkup</b></td>
                                    <td><span id="det_no_checkup"></span></td>
                                </tr>
                                <tr>
                                    <td><b>NIK</b></td>
                                    <td><span id="det_nik"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama</b></td>
                                    <td><span id="det_nama"></span></td>
                                </tr>
                            </table>
                        </div>
                        <!-- /.col -->
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>Jenis Kelamin</b></td>
                                    <td><span id="det_jenis_kelamin"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tempat, TGL Lahir</b></td>
                                    <td><span id="det_tgl"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Alamat</b></td>
                                    <td><span id="det_desa"></span>, <span id="det_kecamatan"></span></td>
                                </tr>
                                <%--<tr>--%>
                                <%--<td><b>Provinsi</b></td>--%>
                                <%--<td><span id="det_provinsi"></span></td>--%>
                                <%--</tr>--%>
                                <%--<tr>--%>
                                <%--<td><b>Kabupaten</b></td>--%>
                                <%--<td></span></td>--%>
                                <%--</tr>--%>
                                <%--<tr>--%>
                                <%--<td><b>Kecamatan</b></td>--%>
                                <%--<td><span id="det_kecamatan"></span></td>--%>
                                <%--</tr>--%>
                                <%--<tr>--%>
                                <%--<td><b>Desa</b></td>--%>
                                <%--<td></span></td>--%>
                                <%--</tr>--%>
                            </table>
                        </div>

                    </div>
                </div>
                <input type="hidden" id="tin_id_detail_checkup">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-8">
                            <h5>
                                Cover Biaya Bpjs
                                <small class="pull-right" style="margin-top: 7px">Rp. <span id="b_bpjs"></span>
                                </small>
                            </h5>
                            <div class="progress">
                                <div id="sts_cover_biaya">
                                </div>
                            </div>
                            <h5>
                                Total Biaya Tindakan
                                <small class="pull-right" style="margin-top: 7px">Rp. <span
                                        id="b_tindakan"></span></small>
                            </h5>
                            <div class="progress">
                                <div id="sts_biaya_tindakan">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <p style="margin-top: 20px">Keterangan</p>
                            <small>
                                <ul style="list-style-type: none">
                                    <li><i class="fa fa-square" style="color: #337ab7"></i> Total biaya cover Bpjs
                                    </li>
                                    <li><i class="fa fa-square" style="color: #5cb85c"></i> Total biaya tindakan <
                                        50%
                                    </li>
                                    <li><i class="fa fa-square" style="color: #f0ad4e"></i> Total biaya tindakan >
                                        50% dan < 70%
                                    </li>
                                    <li><i class="fa fa-square" style="color: #d9534f"></i> Total biaya tindakan >
                                        70%
                                    </li>
                                </ul>
                            </small>
                        </div>
                    </div>
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-medkit"></i> Daftar Tindakan Rawat</h3>
                </div>
                <div class="box-body">
                    <table class="table table-bordered table-striped" id="tabel_tindakan_ts">
                        <thead>
                        <tr bgcolor="#90ee90">
                            <td width="20%">Tanggal</td>
                            <td>Nama Tindakan</td>
                            <td align="center">Total Tarif (Rp.)</td>
                            <td align="center">Kategori</td>
                            <td align="center">Action</td>
                        </tr>
                        </thead>
                        <tbody id="body_tindakan">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_verif"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_verif"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-final-claim">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Finalisasi Detail Tindakan Rawat Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_fin">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_fin"></p>
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_fin">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_fin2"></p>
                </div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>No SEP</b></td>
                                    <td style="vertical-align: middle"><span class="label label-success" id="fin_no_sep"></span></td>
                                </tr>
                                <tr>
                                    <td><b>No RM</b></td>
                                    <td><span id="fin_no_rm"></span></td>
                                </tr>
                                <tr>
                                    <td><b>No Checkup</b></td>
                                    <td><span id="fin_no_checkup"></span></td>
                                </tr>
                                <tr>
                                    <td><b>NIK</b></td>
                                    <td><span id="fin_nik"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama</b></td>
                                    <td><span id="fin_nama"></span></td>
                                </tr>
                            </table>
                        </div>
                        <!-- /.col -->
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>Jenis Kelamin</b></td>
                                    <td><span id="fin_jenis_kelamin"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tempat, TGL Lahir</b></td>
                                    <td><span id="fin_tgl"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Alamat</b></td>
                                    <td><span id="fin_desa"></span>, <span id="fin_kecamatan"></span></td>
                                </tr>
                                <%--<tr>--%>
                                <%--<td><b>Provinsi</b></td>--%>
                                <%--<td><span id="fin_provinsi"></span></td>--%>
                                <%--</tr>--%>
                                <%--<tr>--%>
                                <%--<td><b>Kabupaten</b></td>--%>
                                <%--<td><span id="fin_kabupaten"></span></td>--%>
                                <%--</tr>--%>
                                <%--<tr>--%>
                                <%--<td><b>Kecamatan</b></td>--%>
                                <%--<td><span id="fin_kecamatan"></span></td>--%>
                                <%--</tr>--%>
                                <%--<tr>--%>
                                <%--<td><b>Desa</b></td>--%>
                                <%--<td><span id="fin_desa"></span></td>--%>
                                <%--</tr>--%>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <h5>
                                Cover Biaya Bpjs
                                <small class="pull-right" style="margin-top: 7px">Rp. <span id="fin_b_bpjs"></span>
                                </small>
                            </h5>
                            <div class="progress">
                                <div id="fin_sts_cover_biaya">
                                </div>
                            </div>
                            <h5>
                                Total Biaya Tindakan
                                <small class="pull-right" style="margin-top: 7px">Rp. <span
                                        id="fin_b_tindakan"></span></small>
                            </h5>
                            <div class="progress">
                                <div id="fin_sts_biaya_tindakan">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <p style="margin-top: 20px">Keterangan</p>
                            <small>
                                <ul style="list-style-type: none">
                                    <li><i class="fa fa-square" style="color: #337ab7"></i> Total biaya cover Bpjs
                                    </li>
                                    <li><i class="fa fa-square" style="color: #5cb85c"></i> Total biaya tindakan <
                                        50%
                                    </li>
                                    <li><i class="fa fa-square" style="color: #f0ad4e"></i> Total biaya tindakan >
                                        50% dan < 70%
                                    </li>
                                    <li><i class="fa fa-square" style="color: #d9534f"></i> Total biaya tindakan >
                                        70%
                                    </li>
                                </ul>
                            </small>
                        </div>
                    </div>
                </div>
                <input type="hidden" id="fin_id_detail_checkup">
                <div class="box-header with-border"></div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-medkit"></i> Daftar Tindakan Rawat</h3>
                </div>
                <div class="box-body">
                    <table class="table table-bordered table-striped" id="tabel_tindakan_fin">
                        <thead>
                        <tr bgcolor="#90ee90">
                            <td width="20%">Tanggal</td>
                            <td>Nama Tindakan</td>
                            <td>Kategori</td>
                            <td align="center">Total Tarif (Rp.)</td>
                        </tr>
                        </thead>
                        <tbody id="body_tindakan_fin">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_fin"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_fin"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-confirm-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Confirmation
                </h4>
            </div>
            <div class="modal-body">
                <h4>Do you want save this record?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes            </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    function formatRupiah(angka) {
        if(angka != ""){
            var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
            ribuan = ribuan.join('.').split('').reverse().join('');
            return ribuan;
        }

    }

    function hitungStatusBiaya(idDetailCheckup) {
        CheckupDetailAction.getStatusBiayaTindakan(idDetailCheckup, "RI", function (response) {
                if (response.tarifBpjs != null && response.tarifTindakan != null) {

                    var coverBiaya = response.tarifBpjs;
                    var biayaTindakan = response.tarifTindakan;

                    var persen = "";
                    if (coverBiaya != '' && biayaTindakan) {
                        persen = ((parseInt(biayaTindakan) / parseInt(coverBiaya)) * 100).toFixed(2);
                    } else {
                        persen = 0;
                    }

                    var barClass = "";
                    var barLabel = "";

                    if (parseInt(persen) > 70) {
                        barClass = 'progress-bar-danger';
                    } else if (parseInt(persen) > 50) {
                        barClass = 'progress-bar-warning';
                    } else {
                        barClass = 'progress-bar-success';
                    }

                    var barBpjs = '<div class="progress-bar progress-bar-primary" style="width: 100%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">' + "100.00%" + '</div>';

                    var barTindakan = '<div class="progress-bar ' + barClass + '" style="width: ' + persen + '%" role="progressbar" aria-valuenow="' + persen + '" aria-valuemin="0" aria-valuemax="100">' + persen + "%" + '</div>';

                    if (coverBiaya != '') {
                        $('#sts_cover_biaya').html(barBpjs);
                        $('#b_bpjs').html(formatRupiah(coverBiaya) + " (100%)");
                        $('#fin_sts_cover_biaya').html(barBpjs);
                        $('#fin_b_bpjs').html(formatRupiah(coverBiaya) + " (100%)");
                    }

                    if (biayaTindakan != '') {
                        $('#sts_biaya_tindakan').html(barTindakan);
                        $('#b_tindakan').html(formatRupiah(biayaTindakan) + " (" + persen + "%)");
                        $('#fin_sts_biaya_tindakan').html(barTindakan);
                        $('#fin_b_tindakan').html(formatRupiah(biayaTindakan) + " (" + persen + "%)");
                    }
                }

        });
    }

    function detailTindakan(idCheckup, idDetailCheckup) {
        $('#sts_cover_biaya').html('');
        $('#b_bpjs').html('');
        $('#sts_biaya_tindakan').html('');
        $('#b_tindakan').html('');
        hitungStatusBiaya(idDetailCheckup);
        var table = "";
        var dataTindakan = [];
        var dataPasien = [];
        var noCheckup = "";
        var nik = "";
        var namaPasien = "";
        var jenisKelamin = "";
        var tglLahir = "";
        var agama = "";
        var suku = "";
        var alamat = "";
        var provinsi = "";
        var kabupaten = "";
        var kecamatan = "";
        var desa = "";
        var noSep;
        var total = 0;
        var cekTindakan = false;

        var url = '<s:url value="/pages/images/spinner.gif"/>';
        $('#v_'+idCheckup).attr('src',url).css('width', '30px', 'height', '40px');


        setTimeout(function () {

            var url = '<s:url value="/pages/images/icons8-create-25.png"/>';
            $('#v_'+idCheckup).attr('src',url).css('width', '', 'height', '');

            CheckupAction.listDataPasien(idDetailCheckup, function (response) {
                // dataPasien = response;
                if (response != null) {
                    // $.each(dataPasien, function (i, item) {
                        var tanggal = response.tglLahir;
                        var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                        noCheckup = response.noCheckup;
                        nik = response.noKtp;
                        namaPasien = response.nama;

                        if (response.jenisKelamin == "L") {
                            jenisKelamin = "Laki-Laki";
                        } else {
                            jenisKelamin = "Perempuan";
                        }

                        tglLahir = response.tempatLahir + ", " + dateFormat;
                        agama = response.agama;
                        suku = response.suku;
                        alamat = response.jalan;
                        provinsi = response.namaProvinsi;
                        kabupaten = response.namaKota;
                        kecamatan = response.namaKecamatan;
                        desa = response.namaDesa;
                        noSep = response.noSep;
                        $('#det_no_rm').html(response.idPasien);
                    // });
                }
            });

            VerifikatorAction.getListTindakanRawat(idCheckup, idDetailCheckup, function (response) {
                dataTindakan = response;
                console.log(dataTindakan);
                if (dataTindakan != null) {
                    $.each(dataTindakan, function (i, item) {
                        var tarif = "";
                        var tgl = "";
                        var tindakan    = "";
                        var statusVal   = "";
                        var btn         = "<s:url value="/pages/images/icons8-edit-25.png"/>";
                        var onclick     = 'onclick="updateApproveFlag(\''+item.idRiwayatTindakan+'\',\''+i+'\')"';

                        var tindakanina = "";
                        VerifikatorAction.getListKategoriTindakanBpjs(function (restindakanina) {
                            $.each(restindakanina, function (i, itemTindakan) {
                                if (item.kategoriTindakanBpjs == itemTindakan.kategoriInaBpjs){
                                    tindakanina += "<option value=\""+itemTindakan.id+"\" selected>"+itemTindakan.nama+"</option>";
                                } else {
                                    tindakanina += "<option value=\""+itemTindakan.id+"\">"+itemTindakan.nama+"</option>";
                                }
                            })
                        });

                        var kategori =
                            '<select class="form-control" id="kategori'+i+'">' +
                            '<option value="">[Select One]</option>'+ tindakanina +
//                            '<option value="prosedur_non_bedah">Prosedur Non Bedah</option>'+
//                            '<option value="tenaga_ahli">Tenaga Ahli</option>'+
//                            '<option value="radiologi">Radiologi</option>'+
//                            '<option value="rehabilitasi">Rehabilitasi</option>'+
//                            '<option value="obat">Obat</option>'+
//                            '<option value="alkes">Alkes</option>'+
//
//                            '<option value="prosedur_bedah">Prosedur Bedah</option>'+
//                            '<option value="keperawatan">Keperawatan</option>'+
//                            '<option value="laboratorium">Laboratorium</option>'+
//                            '<option value="kamar_akomodasi">Kamar / Akomodasi</option>'+
//                            '<option value="obat_kronis">Obat Kronis</option>'+
//                            '<option value="bmhp">BMHP</option>'+
//
//                            '<option value="konsultasi">Konsultasi</option>'+
//                            '<option value="penunjang">Penunjang</option>'+
//                            '<option value="pelayanan_darah">Pelayanan Darah</option>'+
//                            '<option value="rawat_intensif">Rawat Intensif</option>'+
//                            '<option value="obat_kemoterapi">Obat Kemoterapi</option>'+
//                            '<option value="sewa_alat">Sewa Alat</option>'+
                            '</select>';

                        if (item.namaTindakan != null && item.namaTindakan !=  '') {
                            tindakan = item.namaTindakan;
                        }

                        if (item.kategoriTindakanBpjs != null && item.kategoriTindakanBpjs != '') {
                            kategori =  '<select class="form-control" id="kategori'+i+'" disabled>' +
                                '<option value="'+item.kategoriTindakanBpjs+'">'+item.kategoriTindakanBpjs+'</option>'
                            '</select>';
                            statusVal = 1;
                            btn = "<s:url value="/pages/images/icon_success.ico"/>";
                            onclick = "";
                        }

                        if(item.kategoriTindakanBpjs == null || item.kategoriTindakanBpjs == ''){
                            cekTindakan = true;
                        }

                        if(item.stTglTindakan != null){
                            tgl = item.stTglTindakan;
                        }

                        if(item.totalTarif != null){
                            tarif = item.totalTarif;
                            total = (parseInt(total) + parseInt(tarif));
                        }

                        table += "<tr>" +
                            "<td>" + tgl + "</td>" +
                            "<td>" + tindakan + "</td>" +
                            "<td align='right'>" + formatRupiah(tarif) + "</td>" +
                            "<td>" + kategori + "</td>" +
                            "<td align='center'>" +'<input value="'+statusVal+'" type="hidden" id="status'+i+'">'+ '<img id="btn'+i+'" class="hvr-grow" style="cursor: pointer" '+onclick+' src="'+btn+'">' + "</td>" +
                            "</tr>";
                    });

                    table = table + '<tr><td colspan="2">Total</td><td align="right">'+formatRupiah(total)+'</td><td></td><td></td></tr>'
                }
            });

            $('#det_no_sep').html(noSep);
            $('#det_no_checkup').html(noCheckup);
            $('#det_nik').html(nik);
            $('#det_nama').html(namaPasien);
            $('#det_jenis_kelamin').html(jenisKelamin);
            $('#det_tgl').html(tglLahir);
            $('#det_agama').html(agama);
            $('#det_suku').html(suku);
            $('#det_alamat').html(alamat);
            $('#det_provinsi').html(provinsi);
            $('#det_kabupaten').html(kabupaten);
            $('#det_kecamatan').html(kecamatan);
            $('#det_desa').html(desa);
            $('#body_tindakan').html(table);
            $('#tin_id_detail_checkup').val(idDetailCheckup);
            if(cekTindakan){
                $('#save_verif').show();
                $('#save_verif').attr('onclick','confirmSaveApproveTindakan(\''+idDetailCheckup+'\')');
            }else{
                $('#save_verif').hide();
            }
            $('#modal-detail-pasien').modal({show:true, backdrop:'static'});
        }, 100);
    }

    function toSelect2(i){
        $('#ts'+i).select2();
    }

    function updateApproveFlag(idTindakan, i){
        var kategoriBpjs = $('#kategori'+i).val();

        if(kategoriBpjs != ''){
            var url = '<s:url value="/pages/images/spinner.gif"/>'
            $('#btn'+i).attr('src',url).css('width', '30px', 'height', '40px');
            dwr.engine.setAsync(true);
            VerifikatorAction.updateApproveBpjsFlag(idTindakan, kategoriBpjs, {
                callback: function (response) {
                    if (response.status == "success"){
                        var url = "<s:url value="/pages/images/icon_success.ico"/>";
                        $('#btn'+i).attr('src',url).css('width', '', 'height', '');
                        $('#btn'+i).removeAttr("class");
                        $('#btn'+i).removeAttr("style");
                        $('#btn'+i).removeAttr("onclick");
                        $('#status'+i).val(1);
                        $('#msg_tin2').text(response.message);
                        $('#success_tin').show().fadeOut(5000);
                    }else{
                        var url = "<s:url value="/pages/images/icons8-edit-25.png"/>";
                        $('#btn'+i).attr('src',url).css('width', '', 'height', '');
                        $('#msg_tin').text(response.message);
                        $('#warning_tin').show().fadeOut(5000);
                    }
                }});
        }else{
            $('#msg_tin').text("Silahkan pilih kategori tindakan terlebih dahulu...!");
            $('#warning_tin').show().fadeOut(5000);
        }
    }

    function confirmSaveApproveTindakan(idDetailCheckup){
        var data = $('#tabel_tindakan_ts').tableToJSON();
        var cek = false;

        //cek select kategori tindakan
        $.each(data, function (i, item) {
            var kategori = $('#kategori' + i).val();
            var status   = $('#status'+i).val();
            if (kategori == "" || status == "") {
                cek = true;
            }
        });

        if (cek) {
            $('#msg_tin').text("Silahkan pilih kategori tindakan BPJS terlebih kemudian klik icon di pinggir untuk konfirmasi...!");
            $('#warning_tin').show().fadeOut(5000);
        } else {
            $('#save_con').attr('onclick','saveApproveTindakan(\''+idDetailCheckup+'\')');
            $('#modal-confirm-dialog').modal('show');
        }

    }

    function saveApproveTindakan(idDetailCheckup) {
        $('#modal-confirm-dialog').modal('hide');
        $('#load_verif').show();
        $('#save_verif').hide();
        dwr.engine.setAsync(true);
        VerifikatorAction.saveApproveTindakan(idDetailCheckup, {
            callback:function (response) {
                if(response.status == "200"){
                    $('#load_verif').hide();
                    $('#save_verif').show();
                    $('#modal-detail-pasien').modal('hide');
                    $('#info_dialog').dialog('open');
                }else{
                    $('#load_verif').hide();
                    $('#save_verif').show();
                    $('#msg_tin').text(response.message);
                    $('#warning_tin').show().fadeOut(5000);
                }
            }
        });
    }

    function finalClaim(idCheckup, idDetailCheckup, idPasien) {
        $('#fin_sts_cover_biaya').html('');
        $('#fin_b_bpjs').html('');
        $('#fin_sts_biaya_tindakan').html('');
        $('#fin_b_tindakan').html('');

        hitungStatusBiaya(idDetailCheckup);
        var table = "";
        var dataTindakan = [];
        var dataPasien = [];
        var noCheckup = "";
        var nik = "";
        var namaPasien = "";
        var jenisKelamin = "";
        var tglLahir = "";
        var agama = "";
        var suku = "";
        var alamat = "";
        var provinsi = "";
        var kabupaten = "";
        var kecamatan = "";
        var desa = "";
        var noSep;
        var total = 0;
        var cekTindakan = false;

        var url = '<s:url value="/pages/images/spinner.gif"/>';
        $('#t_'+idCheckup).attr('src',url).css('width', '30px', 'height', '40px');


        setTimeout(function () {

            var url = '<s:url value="/pages/images/icons8-test-passed-25-2.png"/>';
            $('#t_'+idCheckup).attr('src',url).css('width', '', 'height', '');

            CheckupAction.listDataPasien(idDetailCheckup, function (response) {
                // dataPasien = response;
                if (response != null) {
                    // $.each(dataPasien, function (i, item) {
                        var tanggal = response.tglLahir;
                        var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                        noCheckup = response.noCheckup;
                        nik = response.noKtp;
                        namaPasien = response.nama;

                        if (response.jenisKelamin == "L") {
                            jenisKelamin = "Laki-Laki";
                        } else {
                            jenisKelamin = "Perempuan";
                        }

                        tglLahir = response.tempatLahir + ", " + dateFormat;
                        agama = response.agama;
                        suku = response.suku;
                        alamat = response.jalan;
                        provinsi = response.namaProvinsi;
                        kabupaten = response.namaKota;
                        kecamatan = response.namaKecamatan;
                        desa = response.namaDesa;
                        noSep = response.noSep;
                        $('#fin_no_rm').html(response.idPasien);
                    // });
                }
            });

            VerifikatorAction.getListTindakanRawat(idCheckup, idDetailCheckup, function (response) {
                dataTindakan = response;
                if (dataTindakan != null) {
                    $.each(dataTindakan, function (i, item) {
                        var tindakan = "";
                        var tarif    = "";
                        var kategori = "";
                        var tgl = "";


                        if (item.namaTindakan != null && item.namaTindakan !=  '') {
                            tindakan = item.namaTindakan;
                        }

                        if (item.kategoriTindakanBpjs != null && item.kategoriTindakanBpjs != '') {
                            kategori = item.kategoriTindakanBpjs;
                        }

                        if(item.totalTarif != null && item.totalTarif != ''){
                            tarif = item.totalTarif;
                            total = (parseInt(total) + parseInt(tarif));
                        }

                        if(item.stTglTindakan != null){
                            tgl = item.stTglTindakan;
                        }

                        table += "<tr>" +
                            "<td>" + tgl + "</td>" +
                            "<td>" + tindakan + "</td>" +
                            "<td >" + kategori + "</td>" +
                            "<td align='right'>" +formatRupiah(tarif) + "</td>" +
                            "</tr>";
                    });

                    table = table + '<tr><td colspan="3">Total</td><td align="right">'+formatRupiah(total)+'</td></tr>';
                }
            });

            $('#fin_no_sep').html(noSep);
            $('#fin_no_checkup').html(noCheckup);
            $('#fin_nik').html(nik);
            $('#fin_nama').html(namaPasien);
            $('#fin_jenis_kelamin').html(jenisKelamin);
            $('#fin_tgl').html(tglLahir);
            $('#fin_agama').html(agama);
            $('#fin_suku').html(suku);
            $('#fin_alamat').html(alamat);
            $('#fin_provinsi').html(provinsi);
            $('#fin_kabupaten').html(kabupaten);
            $('#fin_kecamatan').html(kecamatan);
            $('#fin_desa').html(desa);
            $('#body_tindakan_fin').html(table);
            $('#fin_id_detail_checkup').val(idDetailCheckup);
            $('#save_fin').attr('onclick','confirmSaveFinalClaim(\''+idDetailCheckup+'\',\''+idPasien+'\')');
            $('#modal-final-claim').modal({show:true, backdrop:'static'});
        }, 100);
    }

    function confirmSaveFinalClaim(idDetailCheckup, idPasien){
        var data = $('#tabel_tindakan_fin').tableToJSON();
        if(data.length > 0){
            $('#modal-confirm-dialog').modal('show');
            $('#save_con').attr('onclick','saveFinalClaim(\''+idDetailCheckup+'\',\''+idPasien+'\')');
        }else{
            $('#msg_fin').text("Tidak ada data tindakan dalam tabel...!");
            $('#warning_fin').show().fadeOut(5000);
        }
    }

    function saveFinalClaim(idDetailCheckup, idPasien){
        $('#modal-confirm-dialog').modal('hide');
        $('#save_fin').hide();
        $('#load_fin').show();
        dwr.engine.setAsync(true);
        VerifikatorAction.finalClaim(idDetailCheckup,idPasien, function (response) {
            if(response.status == "200"){
                $('#load_fin').hide();
                $('#save_fin').show();
                $('#modal-final-claim').modal('hide');
                $('#info_dialog').dialog('open');
            }else{
                $('#load_fin').hide();
                $('#save_fin').show();
                $('#msg_fin').text(response.message);
                $('#warning_fin').show().fadeOut(5000);
            }

        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>