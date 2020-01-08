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
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#permintaan_po').addClass('active');
        });

        function reture() {
            $('#tipePermintaan').val('002').trigger('change');
            $('#flag').val('N').trigger('change');
            document.obatGudangForm.action = 'searchPermintaanObatGudang_obatgudang.action';
            document.obatGudangForm.submit();
        }

    </script>

</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>
<%--<div class="pulse"></div>--%>

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Permintaan PO
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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Permintaan PO</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="permintaanPOForm" method="post" namespace="/permintaanpo"
                                    action="search_permintaanpo.action" theme="simple"
                                    cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Permintaan Vendor</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_obat" cssStyle="margin-top: 7px"
                                                     name="permintaanVendor.idPermintaanVendor" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Approval</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_obat" name="permintaanVendor.idApprovalObat"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'N':'Non Active'}"
                                                  cssStyle="margin-top: 7px"
                                                  id="flag" name="permintaanVendor.flag"
                                                  headerKey="Y" headerValue="Active"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-8" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="permintaanPOForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-primary" href="add_permintaanpo.action">
                                            <i class="fa fa-plus"></i> Tambah Permintaan PO
                                        </a>
                                        <a type="button" class="btn btn-danger" href="initForm_permintaanpo.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                    </div>
                                </div>
                                <div class="form-group" style="display: none">
                                    <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                               resizable="false"
                                               closeOnEscape="false"
                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                               buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         toContent();
                                                                                     }
                                                                            }"
                                    >
                                        <s:hidden id="close_pos"></s:hidden>
                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                             name="icon_success">
                                        Record has been saved successfully.
                                    </sj:dialog>

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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Permintaan PO</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Permintaan PO</td>
                                <td>Tanggal Permintaan</td>
                                <td>Status</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <%--transaksiObatDetails--%>
                                    <td><s:property value="idPermintaanVendor"/></td>
                                    <td><s:property value="stCreatedDate"/></td>
                                    <td><s:if test='#row.keterangan == "Menunggu Konfirmasi"'>
                                        <label class="label label-warning"><s:property value="keterangan"/></label>
                                    </s:if><s:else>
                                        <label class="label label-success"><s:property value="keterangan"/></label>
                                    </s:else></td>
                                    <td align="center">
                                        <s:if test='#row.enableApprove == false '>
                                            <s:url var="verify_po" namespace="/permintaanpo" action="edit_permintaanpo" escapeAmp="false">
                                                <s:param name="id"><s:property value="idPermintaanVendor"/></s:param>
                                            </s:url>
                                            <s:a href="%{verify_po}">
                                                <button class="btn btn-primary"><i class="fa fa-edit"></i></button>
                                            </s:a>
                                        </s:if>
                                        <s:else>
                                            <s:url var="approve_po" namespace="/permintaanpo" action="initApproval_permintaanpo" escapeAmp="false">
                                                <s:param name="id"><s:property value="idPermintaanVendor"/></s:param>
                                            </s:url>
                                            <s:a href="%{print_doc}">
                                                <button class="btn btn-success"><i class="fa fa-print"></i></button>
                                            </s:a>
                                            <s:url var="approve_po" namespace="/permintaanpo" action="initApproval_permintaanpo" escapeAmp="false">
                                                <s:param name="id"><s:property value="idPermintaanVendor"/></s:param>
                                            </s:url>
                                            <s:a href="%{approve_po}">
                                                <button class="btn btn-success"><i class="fa fa-check"></i></button>
                                            </s:a>
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
</div>


<div class="modal fade" id="modal-request-obat">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Request Obat Gudang</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_request">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_request"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <s:action id="initObat" namespace="/obat"
                                      name="getListObat_obat"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#initObat.listOfObat" id="req_nama_obat"
                                      listKey="idObat + '|' + namaObat + '|' + qty"
                                      onchange="var warn =$('#war_req_obat').is(':visible'); if (warn){$('#cor_req_obat').show().fadeOut(3000);$('#war_req_obat').hide()}; setStokObatPoli(this)"
                                      listValue="namaObat"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_req_obat"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_req_obat"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Stok Obat Tujuan</label>
                        <div class="col-md-7">
                            <input class="form-control" style="margin-top: 7px" readonly id="req_stok_apotek">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Stok Obat Poli</label>
                        <div class="col-md-7">
                            <input class="form-control" style="margin-top: 7px" readonly id="req_stok">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah Request</label>
                        <div class="col-md-7">
                            <input oninput="var warn =$('#war_req_qty').is(':visible'); if (warn){$('#cor_req_qty').show().fadeOut(3000);$('#war_req_qty').hide()}"
                                   style="margin-top: 7px" class="form-control" type="number" min="1"
                                   id="req_qty">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_req_qty"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_req_qty"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px"></label>
                        <div class="col-md-7">
                            <button class="btn btn-success pull-right" style="margin-top: 7px"
                                    onclick="addObatToList(1)"><i class="fa fa-plus"></i> Tambah
                            </button>
                        </div>
                    </div>
                </div>
                <div class="box-header with-border">
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_data_exits">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Data obat sudah tersedia..!
                </div>
                <div class="box-header with-border"><i class="fa fa-file-o"></i> Request Obat
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_request">
                        <thead>
                        <td>ID</td>
                        <td>Nama Obat</td>
                        <td>Qty</td>
                        <td align="center" width="5%">Action</td>
                        </thead>
                        <tbody id="body_request">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_request" onclick="saveAddRequest()"><i
                        class="fa fa-arrow-right"></i> Request
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_request"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="modal-request-detail">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span
                        id="judul_req"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_request_detail">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_request_detail"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tanggal Request</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" readonly="true" id="req_tanggal"
                                   style="margin-top: 7px">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">ID Permintaan</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" readonly="true" id="req_id_permintaan"
                                   style="margin-top: 7px">
                        </div>
                    </div>
                    <input type="hidden" id="req_id_approve">
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border"><i class="fa fa-file-o"></i> Detail Request Obat
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_request_detail">
                        <thead>
                        <td>ID</td>
                        <td>Nama Obat</td>
                        <td align="center">Request</td>
                        <td align="center">Approve</td>
                        </thead>
                        <tbody id="body_request_detail">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_req_detail" onclick="saveConfirm()"><i
                        class="fa fa-arrow-right"></i> Konfirmasi
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_req_detail"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="modal-reture-detail">
    <div class="modal-dialog modal-flat" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Reture Obat Gudang
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_reture_detail">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_reture_detail"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tanggal Request</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" readonly="true" id="ret_tanggal"
                                   style="margin-top: 7px">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">ID Permintaan</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" readonly="true" id="ret_id_permintaan"
                                   style="margin-top: 7px">
                        </div>
                    </div>
                    <input type="hidden" id="ret_id_approve">
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border"><i class="fa fa-file-o"></i> Detail Request Obat
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_reture_head">
                        <thead>
                        <td>ID</td>
                        <td>Nama Obat</td>
                        <td align="center">Stok Obat</td>
                        <td align="center">Request</td>
                        <td align="center">Approve</td>
                        <td align="center">Reture</td>
                        <td align="center">Action</td>
                        </thead>
                        <tbody id="body_reture_head">
                        </tbody>
                    </table>
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border"><i class="fa fa-file-o"></i> Detail Reture Obat
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_reture_detail">
                        <thead>
                        <td>ID</td>
                        <td>Nama Obat</td>
                        <td align="center">Qty</td>
                        <td align="center">Action</td>
                        </thead>
                        <tbody id="body_reture_detail">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_ret_detail" onclick="saveAddReture()"><i
                        class="fa fa-arrow-right"></i> Reture
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_ret_detail"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>


<!-- /.content-wrapper -->
<script type='text/javascript'>

    function toContent() {
        window.location.reload(true);
    }

    function showModal() {
            $('#req_nama_obat').val('').trigger('change');
            $('#req_qty').val('');
            $('#req_stok').val('');
            $('#req_stok_apotek').val('');
            $('#req_qty').val('');
            $('#body_request').html('');
            $('#modal-request-obat').modal('show');
    }

    function setStokObatPoli(select) {

        var idx = select.selectedIndex;
        var idObat = select.options[idx].value;
        console.log(idObat);
        var stok = 0;
        var id = idObat.split('|')[0];
        var nama = idObat.split('|')[1];
        var stokApotek = idObat.split('|')[2];

        if (idObat != '') {
            ObatPoliAction.getStokObatPoli(id, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        if (item.qty != null) {
                            if (item.idObat == id) {
                                stok = item.qty;
                            }
                        } else {
                            stok = 0;
                        }
                    });
                }
            });
        }

        $('#req_stok').val(stok);
        $('#req_stok_apotek').val(stokApotek);
    }

    function setStokPoli(select) {
        var idx = select.selectedIndex;
        var idObat = select.options[idx].value;
        var id = idObat.split('|')[0];
        var nama = idObat.split('|')[1];
        var stok = idObat.split('|')[2];

        $('#ret_stok').val(stok);
    }

    function addObatToList() {

            var obat = $('#req_nama_obat').val();
            var qty = $('#req_qty').val();
            var stok = $('#req_stok_apotek').val();
            var data = $('#tabel_request').tableToJSON();

            var id = obat.split('|')[0];
            var nama = obat.split('|')[1];

            var cek = false;

            if (obat != '' && qty != '') {

                if (parseInt(qty) <= parseInt(stok)) {
                    $.each(data, function (i, item) {
                        if (item.ID == id) {
                            cek = true;
                        }
                    });

                    if (cek) {
                        $('#warning_data_exits').show().fadeOut(5000);
                    } else {
                        var row = '<tr id=' + id + '>' +
                                '<td>' + id + '</td>' +
                                '<td>' + nama + '</td>' +
                                '<td>' + qty + '</td>' +
                                '<td align="center"><img border="0" onclick="delRowObat(\'' + id + '\')" class="hvr-grow" src="<s:url value="/pages/images/delete-flat.png"/>" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                                '</tr>';
                        $('#body_request').append(row);
                    }
                } else {
                    $('#warning_request').show().fadeOut(5000);
                    $('#msg_request').text('Jumlah Request tidak boleh melebihi stok obat...!');
                }
            } else {
                if (obat == '') {
                    $('#war_req_obat').show();
                }
                if (qty == '' || qty <= 0) {
                    $('#war_req_qty').show();
                }
                $('#warning_request').show().fadeOut(5000);
                $('#msg_request').text('Silahkan cek kembali data inputan...!');
            }
    }

    function delRowObat(id) {
        $('#' + id).remove();
        $('#btn' + id).show();
        $('#new_qty'+id).attr('disabled',false);
    }

    function saveAddRequest() {

            var data = $('#tabel_request').tableToJSON();
            var stringData = JSON.stringify(data);

            if (stringData != '[]') {

                $('#save_request').hide();
                $('#load_request').show();

                dwr.engine.setAsync(true);
                ObatPoliAction.saveAddRequest(stringData, "GDG", {
                    callback: function (response) {
                        if (response == "success") {
                            dwr.engine.setAsync(false);
                            $('#modal-request-obat').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#save_request').show();
                            $('#load_request').hide();
                        } else {
                            $('#warning_request').show().fadeOut(5000);
                            $('#save_request').show();
                            $('#load_request').hide();
                        }
                    }
                });

            } else {
                $('#warning_request').show().fadeOut(5000);
            }
    }

    function confirm(idApp, idPermin, tanggal, tujuan) {
        $('#judul_req').html('Konfirmasi request diterima');
        $('#req_id_permintaan').val(idPermin);
        $('#req_tanggal').val(tanggal);
        $('#req_id_approve').val(idApp);
        $('#modal-request-detail').modal('show');
        var table = "";
        PermintaanObatPoliAction.listDetailPermintaan(idPermin, false, tujuan, "Y", {
            callback: function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        table += "<tr>" +
                                "<td>" + item.idObat + "</td>" +
                                "<td>" + item.namaObat + "</td>" +
                                "<td align='center'>" + item.qty + "</td>" +
                                "<td align='center'>" + item.qtyApprove + "</td>" +
                                "</tr>";
                    });
                }
            }
        });
        $('#body_request_detail').html(table);
    }

    function saveConfirm() {
        var data = $('#tabel_request_detail').tableToJSON();
        var stringData = JSON.stringify(data);
        var idApp = $('#req_id_approve').val();
        var idPermin = $('#req_id_permintaan').val();

        if (stringData != '[]') {

            $('#save_req_detail').hide();
            $('#load_req_detail').show();

            dwr.engine.setAsync(true);
            ObatPoliAction.saveKonfirmasiDiterima(idApp, idPermin, stringData, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#modal-request-detail').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#save_req_detail').show();
                        $('#load_req_detail').hide();
                    } else {
                        $('#warning_reture').show().fadeOut(5000);
                        $('#save_reture').show();
                        $('#load_reture').hide();
                    }
                }
            });

        } else {
            $('#warning_reture').show().fadeOut(5000);
        }
    }

    function printRequest(idApp, idPermin) {
        PermintaanObatPoliAction.printPermintaanObat(idApp, idPermin, {
            callback: function (response) {
                if (response == "success") {
                    dwr.engine.setAsync(false);
                    $('#modal-reture').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#save_ret').show();
                    $('#load_ret').hide();
                } else {
                    $('#warning_request').show().fadeOut(5000);
                    $('#msg_reture').text(response);
                    $('#save_ret').show();
                    $('#load_ret').hide();
                }
            }
        });
    }

    function showReture(idPermin, tanggal, idPelayanan) {
        $('#ret_id_permintaan').val(idPermin);
        $('#ret_tanggal').val(tanggal);
        $('#modal-reture-detail').modal('show');
        var table = "";
        PermintaanObatPoliAction.listDetailPermintaan(idPermin, true, idPelayanan, "N", {
            callback: function (response) {
                console.log(response);
                if (response != null) {
                    $.each(response, function (i, item) {
                        table += "<tr>" +
                                "<td>" + '<span id=obat' + item.idObat + '>' + item.idObat + '</span>' + "</td>" +
                                "<td>" + '<span id=nama_obat' + item.idObat + '>' + item.namaObat + '</span>' + "</td>" +
                                "<td align='center'>" + '<span id=qty_poli' + item.idObat + '>' + item.qtyPoli + '</span>' + "</td>" +
                                "<td align='center'>" + item.qty + "</td>" +
                                "<td align='center'>" + '<span id=qty_approve' + item.idObat + '>' + item.qtyApprove + '</span>' + "</td>" +
                                "<td align='center'>" + '<input type="number" id=new_qty' + item.idObat + ' style="width: 80px" class="form-control">' + "</td>" +
                                "<td align='center'>" + '<a type="button" id=btn' + item.idObat + ' onclick="addToListReture(\'' + item.idObat + '\')" class="btn btn-success"><i class="fa fa-plus"></i></a>' + "</td>" +
                                "</tr>";
                    });
                }
            }
        });
        $('#body_reture_head').html(table);
    }

    function addToListReture(id) {
        var idObat      = $('#obat'+id).text();
        var namaObat    = $('#nama_obat'+id).text();
        var qty         = $('#new_qty'+id).val();
        var qtyPoli     = $('#qty_poli'+ id).text();
        var qtyApprove  = $('#qty_approve'+id).text();

        if (qty != '' && parseInt(qty) > 0) {
            if (parseInt(qty) <= parseInt(qtyPoli) && parseInt(qty) <= parseInt(qtyApprove)) {
                var row = '<tr id=' + id + '>' +
                        '<td>' + idObat + '</td>' +
                        '<td>' + namaObat + '</td>' +
                        '<td align="center">' + qty + '</td>' +
                        '<td align="center"><img border="0" onclick="delRowObat(\'' + id + '\')" class="hvr-grow" src="<s:url value="/pages/images/delete-flat.png"/>" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                        '</tr>';
                $('#body_reture_detail').append(row);
                $('#btn'+id).hide();
                $('#new_qty'+id).attr('disabled',true);
            } else {
                $('#warning_reture_detail').show().fadeOut(5000);
                $('#msg_reture_detail').text('Qty tidak boleh melebihi qty stok dan approve...!');
            }
        } else {
            $('#warning_reture_detail').show().fadeOut(5000);
            $('#msg_reture_detail').text('Qty reture tidak boleh kosong...!');
        }
    }

    function saveAddReture(){
        var data = $('#tabel_reture_detail').tableToJSON();
        var stringData = JSON.stringify(data);
        console.log(data);

        if (stringData != '[]') {

            $('#save_req_detail').hide();
            $('#load_ret_detail').show();

            dwr.engine.setAsync(true);
            ObatPoliAction.saveAddReture(stringData, "GDG", {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#modal-reture-detail').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#save_req_detail').show();
                        $('#load_ret_detail').hide();
                    } else {
                        $('#warning_reture_detail').show().fadeOut(5000);
                        $('#msg_reture_detail').text('Terjadi kesalahan saat menyimpan data...!');
                        $('#save_req_detail').show();
                        $('#load_ret_detail').hide();
                    }
                }
            });

        } else {
            $('#warning_reture_detail').show().fadeOut(5000);
            $('#msg_reture_detail').text('Silahkan cek kembali data inputan berikut...!');
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>