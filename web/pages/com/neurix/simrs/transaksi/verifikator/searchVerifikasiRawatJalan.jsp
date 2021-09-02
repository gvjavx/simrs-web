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
    <script type='text/javascript' src='<s:url value="/dwr/interface/DiagnosaRawatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TindakanRawatICD9Action.js"/>'></script>

    <script type='text/javascript'>

        $(document).ready(function () {
            $('#bayar_rawat_jalan, #verif_bpjs_active').addClass('active');
            $('#verif_bpjs_open').addClass('menu-open');
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
            Verifikasi Transaksi Pasien BPJS / BPJS Rekanan Rawat Jalan
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Verifikasi Tindakan Rawat Jalan
                        </h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="verifikatorForm" method="post" namespace="/verifrawatjalan"
                                    action="search_verifrawatjalan.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">No RM</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="headerDetailCheckup.idPasien" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="headerDetailCheckup.nama"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Poli</label>
                                    <div class="col-sm-4">
                                        <s:action id="initComboPoli" namespace="/checkup"
                                                  name="getComboPelayanan_checkup"/>
                                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                                  list="#initComboPoli.listOfPelayanan"
                                                  name="headerDetailCheckup.idPelayanan" listKey="idPelayanan"
                                                  listValue="namaPelayanan"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2" theme="simple"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'2':'Rujuk','3':'Selesai'}" cssStyle="margin-top: 7px"
                                                  id="status"
                                                  headerKey="3" headerValue="Selesai"
                                                  cssClass="form-control select2" disabled="true"/>
                                        <s:hidden name="headerDetailCheckup.statusPeriksa" value="3"></s:hidden>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal Masuk</label>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_from" name="headerDetailCheckup.stDateFrom"
                                                         cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_to" name="headerDetailCheckup.stDateTo"
                                                         cssClass="form-control"
                                                         required="false"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="verifikatorForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_verifrawatjalan.action">
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
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pasien</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Detail Checkup</td>
                                <td>No SEP</td>
                                <td>Nama</td>
                                <td>Status Periksa</td>
                                <td>Status</td>
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
                                            <span class="span-success"> sudah finasisasi</span>
                                        </s:if>
                                        <s:else>
                                            <s:if test='#row.cekApprove == false'>
                                                <span class="span-biru"> sudah diverifikasi</span>
                                            </s:if>
                                            <s:else>
                                                <span class="span-warning"> belum diverifikasi</span>
                                            </s:else>
                                        </s:else>
                                    </td>
                                    <td><s:property value="keteranganSelesai"/></td>
                                    <td align="center">
                                        <s:if test='#row.klaimBpjsFlag == "Y"'>
                                            <a target="_blank"
                                               href="printFinalClaim_verifrawatjalan.action?id=<s:property value="idDetailCheckup"/>">
                                                <img class="hvr-grow"
                                                     src="<s:url value="/pages/images/icons8-print-25.png"/>"
                                                     style="cursor: pointer;">
                                            </a>
                                        </s:if>
                                        <s:else>
                                            <img id="v_<s:property value="idDetailCheckup"/>"
                                                 onclick="detailTindakan('<s:property value="noCheckup"/>','<s:property
                                                         value="idDetailCheckup"/>')" class="hvr-grow"
                                                 src="<s:url value="/pages/images/icons8-create-25.png"/>"
                                                 style="cursor: pointer;">
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Detail Tindakan Rawat Pasien
                </h4>
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
                                <tr style="display: none" id="form_no_sep">
                                    <td><b>No SEP</b></td>
                                    <td style="vertical-align: middle">
                                        <span class="span-success"
                                              id="det_no_sep"></span></td>
                                </tr>
                                <tr>
                                    <td><b>No RM</b></td>
                                    <td><span id="det_no_rm"></span></td>
                                </tr>
                                <tr>
                                    <td><b>ID Detail Checkup</b></td>
                                    <td><span id="det_id_detail_checkup"></span></td>
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
                        <input type="hidden" id="h_no_checkup">
                        <input type="hidden" id="h_jenis_pasien">
                        <input type="hidden" id="h_id_pasien">
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>Jenis Kelamin</b></td>
                                    <td><span id="det_jenis_kelamin"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tempat, Tgl Lahir</b></td>
                                    <td><span id="det_tgl"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Alamat</b></td>
                                    <td><span id="det_desa"></span>, <span id="det_kecamatan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama Poli</b></td>
                                    <td><span id="det_nama_poli"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Jenis Pasien</b></td>
                                    <td><span class="span-success" id="det_jenis_pasien"></span></td>
                                </tr>
                            </table>
                        </div>

                    </div>
                </div>

                <input type="hidden" id="tin_id_detail_checkup">
                <div class="box-body" style="display: none" id="form_status">
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
                <div class="box-header with-border" id="pos_nosa"></div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-stethoscope"></i> Diagnosa</h3>
                </div>
                <div class="box-body">
                    <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                            onclick="showDiagnosa()"><i class="fa fa-plus"></i> Tambah Diagnosa
                    </button>
                    <table class="table table-bordered table-striped table-hover" id="tabel_diagnosa">
                        <thead>
                        <tr bgcolor="#90ee90">
                            <td width="14%">Waktu</td>
                            <td>Kode ICD10</td>
                            <td>Keterangan</td>
                            <td>Jenis Diagnosa</td>
                            <td align="center">Action</td>
                        </tr>
                        </thead>
                        <tbody id="body_diagnosa">

                        </tbody>
                    </table>
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border">
                    <h3 class="box-title" ><i class="fa fa-stethoscope"></i> ICD9</h3>
                </div>
                <div class="box-header with-border">
                    <button class="btn btn-success btn-outline" onclick="showICD9('')"><i class="fa fa-plus"></i> Tambah ICD9</button>
                </div>
                <div class="box-body">
                    <table class="table table-bordered table-striped" id="tabel_icd9" >
                        <thead>
                        <tr bgcolor="#90ee90">
                            <td width="20%">Waktu</td>
                            <td>Kode ICD9</td>
                            <td>Keterangan</td>
                            <td align="center">Action</td>
                        </tr>
                        </thead>
                        <tbody id="body_icd9">
                        </tbody>
                    </table>
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-medkit"></i> Daftar Tindakan Rawat</h3>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_pending">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_pending"></p>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_jen">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_jen"></p>
                </div>
                <div class="box-body" id="form-tindakan" style="display: none">
                    <table class="table table-bordered table-striped" id="tabel_tindakan_ts" style="font-size: 12px">
                        <thead>
                        <tr bgcolor="#90ee90">
                            <td width="19%">Tanggal</td>
                            <td>Nama Tindakan</td>
                            <td align="center">Total Tarif (Rp.)</td>
                            <td align="center" style="display:none;" id="form_kategori">Kategori</td>
                            <td align="center" id="jp" style="display: none">Jenis Cover</td>
                            <%--<td align="center">Action</td>--%>
                        </tr>
                        </thead>
                        <tbody id="body_tindakan">
                        </tbody>
                    </table>
                    <table class="table table-bordered table-striped" style="font-size: 12px">
                        <tbody id="body_tindakan_total">
                        </tbody>
                    </table>
                    <p id="loading_page" style="color: #0F9E5E; display: none"><img style="width: 50px; height: 50px" src="<s:url value="/pages/images/spinner.gif"/>"><b>Sedang mencari data traksaksi...</b></p>
                </div>

            </div>

            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_verif"><i class="fa fa-check"></i> Verifikasi
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_verif"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-send-online">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-ambulance"></i> Send Claim Online</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_online">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_online"></p>
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_online">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_online2"></p>
                </div>
                <div class="box-body">
                    <h4 class="text-center">Kirim Klaim Online ?</h4>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" id="tidak_online"><i class="fa fa-times"></i> Tidak
                </button>
                <button type="button" class="btn btn-success" id="save_online"><i class="fa fa-check"></i> Kirim
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_online"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<%@ include file="/pages/modal/modal-general.jsp" %>

<%--<div class="modal fade" id="modal-diagnosa">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-stethoscope"></i> <span id="t_diagnosa"></span></h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_diagnosa">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Silahkan cek kembali data inputan!
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3">Diagnosa</label>
                        <div class="col-md-7">
                            <s:textfield id="nosa_id_diagnosa" style="margin-top: 7px"
                                         name="headerCheckup.diagnosa" autocomplete="off"
                                         onkeypress="var warn =$('#war_diagnosa_bpjs').is(':visible'); if (warn){$('#cor_diagnosa_bpjs').show().fadeOut(3000);$('#war_diagnosa_bpjs').hide()}; searchDiagnosa(this.id)"
                                         cssClass="form-control" required="false"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_diagnosa_bpjs"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_diagnosa_bpjs"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <input type="hidden" id="val_jenis_diagnosa">
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-7">
                            <s:textarea rows="4" id="nosa_ket_diagnosa"
                                        cssStyle="margin-top: 7px" readonly="true"
                                        name="headerCheckup.namaDiagnosa"
                                        cssClass="form-control"></s:textarea>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3">Jenis Diagnosa</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                    id="nosa_jenis_diagnosa"
                                    onchange="var warn =$('#war_jenis_diagnosa').is(':visible'); if (warn){$('#cor_jenis_diagnosa').show().fadeOut(3000);$('#war_jenis_diagnosa').hide()}">
                                <option value=""> - </option>
                                <option value="diagnosa_primer">Diagnosa Primer</option>
                                <option value="diagnosa_sekunder">Diagnosa Sekunder</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_jenis_diagnosa"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_jenis_diagnosa"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_diagnosa"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_diagnosa">
                    <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>--%>

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
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal" id="modal-loading">
    <div class="vertical-alignment-helper">
        <div class="modal-dialog vertical-align-center">
            <div class="modal-body">
                <div style="text-align: center; color: white;">
                    <img border="0" style="width: 130px; height: 110px;"
                         src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                         name="image_indicator_write">
                    <br>
                    <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -67px; margin-left: 45px"
                         src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                         name="image_indicator_write">
                    <p style="margin-top: -3px">Sedang menyimpan data...</p>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal" id="modal-success">
    <div class="vertical-alignment-helper">
        <div class="modal-dialog vertical-align-center">
            <div class="modal-body">
                <div style="text-align: center; color: white;">
                    <img border="0" style="width: 120px; height: 150px;"
                         src="<s:url value="/pages/images/cek_success.png"/>">
                    <br style="margin-top: -10px">Data berhasil disimpan</br>
                </div>
            </div>
        </div>
    </div>
</div>


<script type='text/javascript'>

    var idDetailCheckup = '';
    var jenisPeriksaPasien = '';
    var contextPath = '<%= request.getContextPath() %>';

    function formatRupiah(angka) {
        if (angka != "" && angka > 0) {
            var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
            ribuan = ribuan.join('.').split('').reverse().join('');
            return ribuan;
        } else {
            return 0;
        }

    }

    function hitungStatusBiaya(idDetailCheckup) {
        dwr.engine.setAsync(true);
        CheckupDetailAction.getStatusBiayaTindakan(idDetailCheckup, "RJ", {
            callback: function (response) {
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
            }
        });
    }

    function detailTindakan(idCheckup, iddetail) {
        if(!cekSession()){
            idDetailCheckup = iddetail;
            $('#sts_cover_biaya').html('');
            $('#b_bpjs').html('');
            $('#sts_biaya_tindakan').html('');
            $('#b_tindakan').html('');
            $('#body_tindakan').html('');
            var table = "";
            var total = 0;
            var cek = "";
            dwr.engine.setAsync(true);
            startSpinner('v_', idDetailCheckup);
            CheckupAction.listDataPasien(idDetailCheckup, {
                callback: function (response) {
                    if (response.idPasien != null) {
                        stopSpinner('v_', idDetailCheckup);
                        var tanggal = response.tglLahir;
                        var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                        var jenisKelamin = "";

                        if (response.jenisKelamin == "L") {
                            jenisKelamin = "Laki-Laki";
                        } else {
                            jenisKelamin = "Perempuan";
                        }

                        if (response.noSep != '' && response.noSep != null) {
                            $('#form_no_sep').show();
                            $('#form_kategori').show();
                            $('#form_status').show();
                            hitungStatusBiaya(idDetailCheckup);
                        } else {
                            $('#form_no_sep').hide();
                            $('#form_kategori').hide();
                            $('#form_status').hide();
                        }

                        $('#det_no_sep').html(response.noSep);
                        $('#det_id_detail_checkup').html(idDetailCheckup);
                        $('#det_nik').html(response.noKtp);
                        $('#det_nama').html(response.nama);
                        $('#det_jenis_kelamin').html(jenisKelamin);
                        $('#det_tgl').html(response.tempatLahir + ", " + dateFormat);
                        $('#det_agama').html(response.agama);
                        $('#det_suku').html(response.suku);
                        $('#det_alamat').html(response.jalan);
                        $('#det_provinsi').html(response.namaProvinsi);
                        $('#det_kabupaten').html(response.namaKota);
                        $('#det_kecamatan').html(response.namaKecamatan);
                        $('#det_desa').html(response.namaDesa);
                        $('#tin_id_detail_checkup').val(idDetailCheckup);
                        $('#det_no_rm').html(response.idPasien);
                        $('#det_nama_poli').html(response.namaPelayanan);
                        if (response.idJenisPeriksaPasien == "rekanan") {
                            CheckupAction.cekRekananOpsByDetail(idDetailCheckup, function (res) {
                                if (res.namaRekanan != null && res.namaRekanan != '') {
                                    $('#det_jenis_pasien').html(changeJenisPasien(response.idJenisPeriksaPasien, response.statusPeriksaName+" "+res.namaRekanan));
                                    $('#h_id_paket').val(res.idPaket);
                                    $('#h_is_bpjs').val(res.isBpjs);
                                }
                            });
                        } else {
                            $('#det_jenis_pasien').html(changeJenisPasien(response.idJenisPeriksaPasien, response.statusPeriksaName));
                        }
                        $('#h_no_checkup').val(response.noCheckup);
                        $('#h_jenis_pasien').val(response.idJenisPeriksaPasien);
                        $('#h_id_pasien').val(response.idPasien);
                        jenisPeriksaPasien = response.idJenisPeriksaPasien;
                        startLoad();
                        listTindakan(idCheckup, idDetailCheckup, response.idJenisPeriksaPasien, response.idPasien, response.noSep);
                        $('#modal-detail-pasien').modal({show: true, backdrop: 'static'});
                    }
                }
            });
            listDiagnosa(idDetailCheckup);
            listICD9(idDetailCheckup);
        }
    }

    function listTindakan(idCheckup, idDetailCheckup, jenisPasien, idPasien, sep) {
        if(!cekSession()){
            var table = "";
            var total = 0;
            var cekPending = false;
            var cekTindakan = false;
            dwr.engine.setAsync(true);
            VerifikatorAction.getListTindakanRawat(idCheckup, idDetailCheckup, jenisPasien, {
                    callback: function (response) {
                        if (response.length > 0) {
                            dwr.engine.setAsync(false);
                            stopLoad();
                            $.each(response, function (i, item) {
                                if (i == 0) {
                                    if (item.isPendingTindakan == "Y") {
                                        $('#form-tindakan').hide();
                                        $('#warning_pending').show();
                                        $('#msg_pending').text(item.msg);
                                        cekPending = true;
                                    } else {
                                        $('#form-tindakan').show();
                                        $('#warning_pending').hide();
                                        $('#msg_pending').text('');
                                    }
                                }
                                var tarif = "";
                                var tgl = "";
                                var tindakan = "";
                                var statusVal = "";
                                var btn = "<s:url value="/pages/images/icons8-edit-25.png"/>";
                                var onclick = 'onclick="updateApproveFlag(\'' + item.idRiwayatTindakan + '\',\'' + i + '\', \'' + idDetailCheckup + '\')"';
                                var disabledJenis = "";

                                var tindakanina = "";
                                VerifikatorAction.getListKategoriTindakanBpjs(function (restindakanina) {
                                    $.each(restindakanina, function (i, itemTindakan) {
                                        if (item.kategoriInaBpjs == itemTindakan.id) {
                                            tindakanina += "<option value=\"" + itemTindakan.id + "\" selected>" + itemTindakan.nama + "</option>";
                                        } else {
                                            tindakanina += "<option value=\"" + itemTindakan.id + "\">" + itemTindakan.nama + "</option>";
                                        }
                                    })
                                });

                                var kategori =
                                    '<select style="width: 100%;" class="form-control select-2" id="kategori_' + i + '">' +
                                    '<option value="">[Select One]</option>' +
                                    tindakanina +
                                    '</select>';

                                if (item.namaTindakan != null && item.namaTindakan != '') {
                                    tindakan = item.namaTindakan;
                                }

                                if (item.kategoriTindakanBpjs != null && item.kategoriTindakanBpjs != '') {
                                    kategori = '<select class="form-control" id="kategori_' + i + '" disabled>' +
                                        '<option value="' + item.kategoriTindakanBpjs + '">' + item.kategoriTindakanBpjs + '</option>';
                                    '</select>';
                                    statusVal = 1;
                                    btn = "<s:url value="/pages/images/icon_success.ico"/>";
                                    onclick = "";
                                    disabledJenis = "disabled";
                                }

                                if (item.flagUpdateKlaim == '' || item.flagUpdateKlaim == null) {
                                    cekTindakan = true;
                                }

                                if (item.stTglTindakan != null) {
                                    tgl = item.stTglTindakan;
                                }

                                if (item.totalTarif != null) {
                                    tarif = item.totalTarif;
                                    total = (parseInt(total) + parseInt(tarif));
                                }

                                if (jenisPasien == "bpjs_rekanan") {
                                    $('#jp').show();
                                    var choice1 = "";
                                    var choice2 = "";
                                    if (item.jenisPasien == "rekanan") {
                                        choice1 = "selected";
                                    }
                                    if (item.jenisPasien == "bpjs") {
                                        choice2 = "selected";
                                    }
                                    var kater = "";
                                    var disab = "";
                                    if (sep != null && sep != '') {
                                        kater = '<td>' + kategori + '</td>';
                                    } else {
                                        disabledJenis = 'disabled';
                                    }
                                    table += "<tr>" +
                                        "<td>" + tgl + '<input type="hidden" id="id_riwayat_' + i + '" value="' + item.idRiwayatTindakan + '">' + "</td>" +
                                        "<td>" + tindakan + "</td>" +
                                        "<td align='right'>" + formatRupiah(tarif) + "</td>" +
                                        kater +
                                        "<td>" +
                                        '<select style="width: 100%;" class="form-control select-2" id="jenis_pasien_' + i + '" onchange="updateApproveFlag(\'' + item.idRiwayatTindakan + '\', \'' + i + '\', \'' + idDetailCheckup + '\', this.value)" ' + disabledJenis + '>' +
                                        '<option value="bpjs" ' + choice2 + '>BPJS</option>' +
                                        '<option value="rekanan" ' + choice1 + '>REKANAN</option>' +
                                        '</select>' +
                                        "</td>" +
                                        "</tr>";
                                } else {
                                    $('#jp').show();
                                    var choice1 = "";
                                    var choice2 = "";
                                    if (item.jenisPasien == "umum") {
                                        choice1 = "selected";
                                    }
                                    if (item.jenisPasien == "bpjs") {
                                        choice2 = "selected";
                                    }
                                    table += "<tr>" +
                                        "<td>" + tgl + '<input type="hidden" id="id_riwayat_' + i + '" value="' + item.idRiwayatTindakan + '">' + "</td>" +
                                        "<td>" + tindakan + "</td>" +
                                        "<td align='right'>" + formatRupiah(tarif) + "</td>" +
                                        "<td>" + kategori + "</td>" +
                                        "<td>" +
                                        '<select style="width: 100%;" class="form-control select-2" id="jenis_pasien_' + i + '" onchange="updateApproveFlag(\'' + item.idRiwayatTindakan + '\', \'' + i + '\', \'' + idDetailCheckup + '\', this.value)" ' + disabledJenis + '>' +
                                        '<option value="bpjs" ' + choice2 + '>BPJS</option>' +
                                        '<option value="umum" ' + choice1 + '>UMUM</option>' +
                                        '</select>' +
                                        "</td>" +
                                        "</tr>";
                                }
                            });
                            table = table + '<tr><td colspan="2">Total Semua Tindakan</td><td align="right">' + formatRupiah(total) + '</td><td></td><td></td></tr>';
                            $('#body_tindakan').html(table);
                        }
                        if (cekPending) {
                            $('#save_verif').hide();
                        } else {
                            if (cekTindakan) {
                                $('#save_verif').show();
                                $('#save_verif').html("<i class='fa fa-check'></i> Verifikasi");
                                $('#save_verif').attr('onclick', 'confirmSaveApproveTindakan(\'' + idDetailCheckup + '\')');
                            } else {
                                $('#save_verif').show();
                                $('#save_verif').html("<i class='fa fa-check'></i> Final Claim");
                                $('#save_verif').attr('onclick', 'confirmSaveFinalClaim(\'' + idDetailCheckup + '\', \'' + idPasien + '\')');
                            }
                        }
                        var select2 = $('.select-2').length;
                        if (select2 > 0) {
                            $('.select-2').select2({});
                        }
                    }
                });
        }
    }

    function updateApproveFlag(idTindakan, i, idDetailCheckup, jenisPasien) {
        if(!cekSession()){
            var kategoriBpjs = $('#kategori_' + i).val();
            if (kategoriBpjs != '' && jenisPasien != '') {
                dwr.engine.setAsync(true);
                VerifikatorAction.updateApproveBpjsFlag(idTindakan, kategoriBpjs, jenisPasien, {
                    callback: function (response) {
                        if (response.status == "success") {
                            hitungStatusBiaya(idDetailCheckup);
                        } else {
                            $('#msg_jen').text(response.message);
                            $('#warning_jen').show().fadeOut(5000);
                        }
                    }
                });
            } else {
                $('#msg_jen').text("Silahkan pilih kategori tindakan terlebih dahulu...!");
                $('#warning_jen').show().fadeOut(5000);
            }
        }
    }

    function confirmSaveApproveTindakan(idDetailCheckup) {
        var data = $('#tabel_tindakan_ts').tableToJSON();
        var cek = false;

        $.each(data, function (i, item) {
            var idRiwayat = $('#id_riwayat_' + i).val();
            var kategori = $('#kategori_' + i).val();
            var jenisPasien = $('#jenis_pasien_' + i).val();
            if (kategori == "") {
                cek = true;
            }
        });

        if (cek) {
            $('#msg_tin').text("Silahkan pilih kategori tindakan BPJS terlebih dahulu...!");
            $('#warning_tin').show().fadeOut(5000);
            $('#modal-detail-pasien').scrollTop(0);
        } else {
            $('#save_con').attr('onclick', 'saveApproveTindakan(\'' + idDetailCheckup + '\')');
            $('#modal-confirm-dialog').modal('show');
        }

    }

    function saveApproveTindakan(idDetailCheckup) {
        $('#modal-confirm-dialog').modal('hide');
        if(!cekSession()){
            var data = $('#tabel_tindakan_ts').tableToJSON();
            var dataRiwayat = [];
            var jml = data.length - 1;

            $.each(data, function (i, item) {
                var idRiwayat = $('#id_riwayat_' + i).val();
                var kategori = $('#kategori_' + i).val();
                var jenisPasien = $('#jenis_pasien_' + i).val();
                if (jml != i) {
                    dataRiwayat.push({
                        'id_riwayat_tindakan': idRiwayat,
                        'kategori': kategori,
                        'jenis_pasien': jenisPasien
                    });
                }
            });

            $('#load_verif').show();
            $('#save_verif').hide();
            var result = JSON.stringify(dataRiwayat);
            var noCheckup = $('#h_no_checkup').val();
            var jenisPasien = $('#h_jenis_pasien').val();
            var idPasien = $('#h_id_pasien').val();
            dwr.engine.setAsync(true);
            VerifikatorAction.saveApproveTindakan(idDetailCheckup, result, {
                callback: function (response) {
                    if (response.status == "200") {
                        hitungStatusBiaya(idDetailCheckup);
                        $('#load_verif').hide();
                        $('#save_verif').show();
                        $('#success_tin').show().fadeOut(5000);
                        $('#msg_tin2').text("Berhasil Verifikasi tindakan pasien..., Silahkan lanjutkan Menekan Tombol Final Claim Dibawah..!");
                        $('#save_verif').html("<i class='fa fa-check'></i> Final Claim");
                        $('#save_verif').attr('onclick', 'confirmSaveFinalClaim(\'' + idDetailCheckup + '\', \'' + idPasien + '\')');
                        $('#modal-detail-pasien').scrollTop(0);
                    } else {
                        $('#load_verif').hide();
                        $('#save_verif').show();
                        $('#msg_tin').text(response.message);
                        $('#warning_tin').show().fadeOut(5000);
                        $('#modal-detail-pasien').scrollTop(0);
                    }
                }
            });
        }
    }

    function confirmSaveFinalClaim(idDetailCheckup, idPasien) {
        var data = $('#tabel_tindakan_ts').tableToJSON();
        if (data.length > 0) {
            $('#modal-confirm-dialog').modal('show');
            $('#save_con').attr('onclick', 'saveFinalClaim(\'' + idDetailCheckup + '\', \'' + idPasien + '\')');
        } else {
            $('#msg_tin').text("Tidak ada data tindakan dalam tabel...!");
            $('#warning_tin').show().fadeOut(5000);
        }
    }

    function saveFinalClaim(idDetailCheckup, idPasien) {
        $('#modal-confirm-dialog').modal('hide');
        if(!cekSession()){
            $('#save_verif').hide();
            $('#load_verif').show();
            dwr.engine.setAsync(true);
            VerifikatorAction.finalClaim(idDetailCheckup, idPasien, {
                callback:
                    function (response) {
                        if (response.status == "200") {
                            $('#load_verif').hide();
                            $('#save_verif').show();
                            $('#tidak_online').attr('onclick', 'closeModal()');
                            $('#save_online').attr('onclick', 'sendOnline(\'' + idDetailCheckup + '\')');
                            $('#modal-send-online').modal({show: true, backdrop: 'static'});
                        } else {
                            $('#load_verif').hide();
                            $('#save_verif').show();
                            $('#msg_tin').text(response.message);
                            $('#warning_tin').show().fadeOut(5000);
                            $('#modal-detail-pasien').scrollTop(0);
                        }
                    }
            });
        }
    }

    function sendOnline(idDetailCheckup) {
        if(!cekSession()){
            $('#load_online').show();
            $('#save_online').hide();
            dwr.engine.setAsync(true);
            VerifikatorAction.sendClaimOnline(idDetailCheckup, {
                callback : function (res) {
                    if (res.status == "200") {
                        $('#load_online').show();
                        $('#save_online').hide();
                        $('#modal-send-online').modal('hide');
                        $('#modal-detail-pasien').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('body').scrollTop(0);
                    } else {
                        $('#load_online').hide();
                        $('#save_online').show();
                        $('#msg_online').text(res.message);
                        $('#warning_online').show().fadeOut(5000);
                    }
                }
            });
        }
    }

    function closeModal() {
        $('#modal-send-online').modal('hide');
        $('#modal-detail-pasien').modal('hide');
        window.location.reload(true);
    }

    function convertSentenceCase(myString) {
        if (myString != null && myString != '') {
            var rg = /(^\w{1}|\ \s*\w{1})/gi;
            myString = myString.replace(rg, function (toReplace) {
                return toReplace.toUpperCase();
            });
            return myString;
        } else {
            return "";
        }
    }

    function showDiagnosa(){
        var id = "";
        $('#t_diagnosa').text("Tambah Diagnosa");
        $('#nosa_id_diagnosa, #nosa_ket_diagnosa').val('');
        $('#nosa_jenis_diagnosa').val('').trigger('change');
        $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
        $('#save_diagnosa').attr('onclick', 'saveDiagnosa(\'' + id + '\')').show();
        $('#modal-diagnosa').modal({show: true, backdrop: 'static'});
    }

    function editDiagnosa(id, idDiagnosa, jenis, ket) {
        $('#t_diagnosa').text("Edit Diagnosa");
        $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
        $('#nosa_id_diagnosa').val(idDiagnosa);
        $('#nosa_ket_diagnosa').val(ket);
        $('#nosa_jenis_diagnosa').val(jenis).trigger('change');
        $('#save_diagnosa').attr('onclick', 'saveDiagnosa(\'' + id + '\')').show();
        $('#modal-diagnosa').modal({show: true, backdrop: 'static'});
    }

    function showICD9(id){
       $('#id_icd9, #ket_icd9').val('');
       $('#load_icd9').hide();
       $('#save_icd9').attr('onclick', 'saveICD9(\'' + id + '\')').show();
       $('#modal-icd9').modal({show: true, backdrop: 'static'});
    }

    function editICD9(id, idIcd9, ketIcd9) {
       $('#load_icd9, #warning_icd9, #war_id_icd9').hide();
       $('#id_icd9').val(idIcd9);
       $('#id_edit_icd9').val(idIcd9);
       $('#ket_icd9').val(ketIcd9);
       $('#save_icd9').attr('onclick', 'saveICD9(\'' + id + '\')').show();
       $('#modal-icd9').modal({show: true, backdrop: 'static'});
    }


    function listDiagnosa(idDetailCheckup) {
        var table = "";
        var data = [];
        DiagnosaRawatAction.listDiagnosa(idDetailCheckup, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    var id = "-";
                    var ket = "-";
                    var jen = "-";
                    var tanggal = item.createdDate;
                    var dateFormat = converterDateTime(new Date(tanggal));

                    if (item.idDiagnosa != null) {
                        id = item.idDiagnosa;
                    }

                    var blink = "";
                    if(item.isWarning == "Y"){
                        blink = 'class="blink_me_atas" style="color: red"';
                    }

                    if (item.keteranganDiagnosa != null) {
                        ket = item.keteranganDiagnosa;
                    }
                    if (item.jenisDiagnosa != null) {
                        jen = item.jenisDiagnosa.replace("_"," ");
                        jen = convertSentenceCaseUp(jen);
                    }
                    table += '<tr '+blink+'>' +
                        "<td>" + dateFormat + "</td>" +
                        "<td>" + id + "</td>" +
                        "<td>" + ket + "</td>" +
                        "<td>" + jen + "</td>" +
                        "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDiagnosa(\'' + item.idDiagnosaRawat + '\',\'' + item.idDiagnosa + '\',\'' + item.jenisDiagnosa + '\', \'' + item.keteranganDiagnosa + '\')" src="' + contextPathHeader + '/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
                        "</tr>"
                });
            }
            $('#body_diagnosa').html(table);
        });
    }

    function listICD9(idDetail) {

       var table = "";
       var data = [];

       TindakanRawatICD9Action.getListICD9(idDetail, function (response) {
          data = response;
          if (data != null) {
             $.each(data, function (i, item) {
                var id = "-";
                var ket = "-";
                var tanggal = item.createdDate;
                var dateFormat = converterDateTime(new Date(tanggal));

                if (item.idIcd9 != null) {
                   id = item.idIcd9;
                }
                if (item.namaIcd9 != null) {
                   ket = item.namaIcd9;
                }
                table += "<tr>" +
                    "<td>" + dateFormat + "</td>" +
                    "<td>" + id + "</td>" +
                    "<td>" + ket + "</td>" +
                    "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editICD9(\'' + item.idTindakanRawatIcd9 + '\',\'' + item.idIcd9 + '\',\'' + item.namaIcd9 + '\')" src="' + contextPath + '/pages/images/icons8-create-25.png" style="cursor: pointer;">' + "</td>" +
                    "</tr>"
             });
             $('#body_icd9').html(table);
          }
       });
    }

    function saveDiagnosa(id) {
        var idDiag = $('#nosa_id_diagnosa').val();
        var ketDiagnosa = $('#nosa_ket_diagnosa').val();
        var jenisDiagnosa = $('#nosa_jenis_diagnosa').val();

        if (idDetailCheckup != '' && idDiag != '' && jenisDiagnosa != '' && ketDiagnosa != '') {
            if (!cekSession()) {
                $('#save_diagnosa').hide();
                $('#load_diagnosa').show();
                if (id != '') {
                    dwr.engine.setAsync(true);
                    DiagnosaRawatAction.editDiagnosa(id, idDiag, jenisDiagnosa, ketDiagnosa, jenisPeriksaPasien, idDetailCheckup, {
                        callback: function (response) {
                            console.log(response);
                            if (response.status == "success") {
                                dwr.engine.setAsync(false);
                                listDiagnosa(idDetailCheckup);
                                hitungStatusBiaya(idDetailCheckup);
                                $('#modal-diagnosa').modal('hide');
                            } else {
                                $('#warning_diagnosa').show().fadeOut(5000);
                                $('#msg_diagnosa').text(response.msg);
                                $('#save_diagnosa').show();
                                $('#load_diagnosa').hide();
                            }
                        }
                    })
                } else {
                    dwr.engine.setAsync(true);
                    DiagnosaRawatAction.saveDiagnosa(idDetailCheckup, idDiag, jenisDiagnosa, ketDiagnosa, jenisPeriksaPasien, {
                        callback: function (response) {
                            console.log(response.list);
                            if (response.status == "success") {
                                dwr.engine.setAsync(false);
                                listDiagnosa(idDetailCheckup);
                                hitungStatusBiaya(idDetailCheckup);
                                $('#modal-diagnosa').modal('hide');
                            } else {
                                $('#warning_diagnosa').show().fadeOut(5000);
                                $('#msg_diagnosa').text(response.msg);
                                $('#save_diagnosa').show();
                                $('#load_diagnosa').hide();
                            }
                        }
                    });
                }
            }
        } else {
            $('#warning_diagnosa').show().fadeOut(5000);
            $('#msg_diagnosa').text('Silahkan cek kembali data inputan...!');
            if (idDiag == '') {
                $('#war_diagnosa_bpjs').show();
            }
            if (jenisDiagnosa == '') {
                $('#war_jenis_diagnosa').show();
            }
        }
    }

    function saveICD9(id) {

       var idIcd9 = $('#id_icd9').val();
       var ketIcd9 = $('#ket_icd9').val();
       var idIcd9Edit = $('#id_edit_icd9').val();
       var jenisPasien = $('#h_jenis_pasien').val();
       var data = "";

       if (idDetailCheckup != '' && idIcd9 != '' && ketIcd9 != '') {
          if (!cekSession()) {
             data = {
                'id_detail_checkup': idDetailCheckup,
                'jenis_pasien': jenisPasien,
                'id_icd9': idIcd9,
                'nama_icd9': ketIcd9,
                'id_tindakan_rawat_icd9': id,
                'id_edit_icd9': idIcd9Edit
             }

             $('#save_icd9').hide();
             $('#load_icd9').show();
             var result = JSON.stringify(data);

             if (id != '') {
                dwr.engine.setAsync(true);
                TindakanRawatICD9Action.edit(result, {
                   callback: function (response) {
                      if (response.status == "success") {
                         dwr.engine.setAsync(false);
                         listICD9(idDetailCheckup);
                         hitungStatusBiaya();
                         $('#modal-icd9').modal('hide');
                         //$('#info_dialog').dialog('open');
                         //$('#close_pos').val(11);
                      } else {
                         $('#save_icd9').show();
                         $('#load_icd9').hide();
                         $('#warning_icd9').show().fadeOut(5000);
                         $('#msg_icd9').text(response.msg);
                      }
                   }
                })
             } else {
                dwr.engine.setAsync(true);
                TindakanRawatICD9Action.save(result, {
                   callback: function (response) {
                      if (response.status == "success") {
                         dwr.engine.setAsync(false);
                         listICD9(idDetailCheckup);
                         hitungStatusBiaya();
                         $('#modal-icd9').modal('hide');
                         //$('#info_dialog').dialog('open');
                         //$('#close_pos').val(11);
                      } else {
                         $('#save_icd9').show();
                         $('#load_icd9').hide();
                         $('#warning_icd9').show().fadeOut(5000);
                         $('#msg_icd9').text(response.msg);
                      }
                   }
                })
             }
          }
       } else {
          $('#warning_icd9').show().fadeOut(5000);
          $('#msg_icd9').text("Silahkan cek kembali inputan anda...!");
          if (id == '') {
             $('#war_id_icd9').show();
          }
       }
    }

    function searchDiagnosa(id) {
        var menus, mapped;
        $('#' + id).typeahead({
            minLength: 3,
            source: function (query, process) {
                menus = [];
                mapped = {};

                var data = [];
                dwr.engine.setAsync(false);
                CheckupAction.getICD10(query, function (listdata) {
                    data = listdata;
                });

                $.each(data, function (i, item) {
                    var labelItem = item.idDiagnosa + '-' + item.descOfDiagnosa;
                    mapped[labelItem] = {
                        id: item.idDiagnosa,
                        label: labelItem,
                        name: item.descOfDiagnosa
                    };
                    menus.push(labelItem);
                });

                process(menus);
            },
            updater: function (item) {
                var selectedObj = mapped[item];
                // insert to textarea diagnosa_ket
                $("#nosa_ket_diagnosa").val(selectedObj.name);
                return selectedObj.id;
            }
        });
    }


    function searchICD9(id) {
       var menus, mapped;
       $('#' + id).typeahead({
          minLength: 3,
          source: function (query, process) {
             menus = [];
             mapped = {};

             var data = [];
             dwr.engine.setAsync(false);
             CheckupAction.getICD9(query, function (listdata) {
                data = listdata;
             });

             $.each(data, function (i, item) {
                var labelItem = item.idIcd9 + '-' + item.namaIcd9;
                mapped[labelItem] = {
                   id: item.idIcd9,
                   label: labelItem,
                   name: item.namaIcd9
                };
                menus.push(labelItem);
             });

             process(menus);
          },
          updater: function (item) {
             var selectedObj = mapped[item];
             $("#ket_icd9").val(selectedObj.name);
             return selectedObj.id;
          }
       });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>
