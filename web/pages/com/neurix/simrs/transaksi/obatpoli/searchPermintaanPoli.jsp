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
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatPoliAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanObatPoliAction.js"/>'></script>
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#permintaan_obat').addClass('active');
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
            Permintaan Obat Poli
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Permintaan Obat Poli</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="permintaanForm" method="post" namespace="/permintaangudang" action="search_permintaangudang.action"
                                    theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tipe Permintaan</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'003':'Reture'}"
                                                  cssStyle="margin-top: 7px"
                                                  id="tipePermintaan" name="permintaanObatPoli.tipePermintaan"
                                                  headerKey="002" headerValue="Request"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Poli</label>
                                    <div class="col-sm-4">
                                        <s:action id="initComboPoli" namespace="/checkup"
                                                  name="getComboPelayanan_checkup"/>
                                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                                  list="#initComboPoli.listOfPelayanan" id="poli"
                                                  name="permintaanObatPoli.idPelayanan" listKey="idPelayanan"
                                                  listValue="namaPelayanan"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'N':'Non Active'}"
                                                  cssStyle="margin-top: 7px"
                                                  id="flag" name="permintaanObatPoli.flag"
                                                  headerKey="Y" headerValue="Active"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="permintaanForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <%--<a type="button" class="btn btn-primary" href="add_checkup.action"><i--%>
                                                <%--class="fa fa-plus"></i> Tambah Rawat Pasien</a>--%>
                                        <a type="button" class="btn btn-danger" href="initForm_permintaangudang.action">
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
                                                                                         window.location.reload(true);
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
                                                <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Permintaan Obat Poli</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped" style="width: 100%">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Permintaan</td>
                                <td>Tanggal</td>
                                <td>Nama Pelayanan</td>
                                <td>Status</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idPermintaanObatPoli"/></td>
                                    <td><s:property value="stCreatedDate"/></td>
                                    <td><s:property value="namaPelayanan"/></td>
                                    <td><s:if test='#row.keterangan == "Menunggu Konfirmasi"'>
                                        <label class="label label-warning"><s:property value="keterangan"/></label>
                                    </s:if><s:else>
                                        <label class="label label-success"><s:property value="keterangan"/></label>
                                    </s:else></td></td>
                                    <td align="center">
                                        <s:if test='#row.approvalFlag == null'>
                                            <s:if test='#row.request == true'>
                                                <s:url var="init_permintaan" namespace="/permintaangudang" action="initApprovePermintaan_permintaangudang" escapeAmp="false">
                                                    <s:param name="idApproval"><s:property value="idApprovalObat"/></s:param>
                                                    <s:param name="idPermintaan"><s:property value="idPermintaanObatPoli"/></s:param>
                                                </s:url>
                                                <s:a href="%{init_permintaan}">
                                                    <img class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer">
                                                </s:a>
                                            </s:if>
                                            <s:else>
                                                <img onclick="showReture('<s:property value="idPermintaanObatPoli"/>','<s:property value="stCreatedDate"/>','<s:property value="tujuanPelayanan"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer">
                                            </s:else>
                                        </s:if>
                                        <s:elseif test='#row.approvalFlag == "Y" '>
                                            <s:url var="print_permintaan" namespace="/permintaangudang" action="printPermintaanObat_permintaangudang" escapeAmp="false">
                                                <s:param name="idPermintaan"><s:property value="idPermintaanObatPoli"/></s:param>
                                            </s:url>
                                            <s:a target="_blank" href="%{print_permintaan}">
                                                <img class="hvr-grow" src="<s:url value="/pages/images/icons8-print-25.png"/>" style="cursor: pointer">
                                            </s:a>
                                        </s:elseif>


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

<div class="modal fade" id="modal-request">
    <div class="modal-dialog modal-flat" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span
                        id="judul_req"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_request">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_request"></p>
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
                    <input id="req_tujuan_pelayanan" type="hidden">
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border"><i class="fa fa-file-o"></i> Detail Request Obat
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_request">
                        <thead>
                        <tr>
                            <td>ID</td>
                            <td>Nama Obat</td>
                            <td align="center">Qty Box</td>
                            <td align="center">Qty Lembar</td>
                            <td align="center">Qty Biji</td>
                            <td align="center">Qty Request</td>
                            <td align="center">Jenis Satuan</td>
                            <td align="center">Qty Approve</td>
                            <td align="center">Action</td>
                        </tr>
                        </thead>
                        <tbody id="body_request">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_req" onclick="saveRequest()"><i
                        class="fa fa-check"></i> Konfirmasi
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_req"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-reture">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="judul_ret"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_reture">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_reture"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-2" style="margin-top: 7px">ID Permintaan</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" readonly="true" id="ret_id_permintaan" style="margin-top: 7px">
                        </div>
                        <label class="col-md-2" style="margin-top: 7px">Tanggal Reture</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" readonly="true" id="ret_tanggal" style="margin-top: 7px">
                        </div>
                    </div>
                </div>
                <div class="box-header with-border"></div>
                <div class="box-header with-border"><i class="fa fa-file-o"></i> Detail Reture Obat
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_request_detail">
                        <thead>
                        <td>ID Barang</td>
                        <td>Nama Obat</td>
                        <td align="center">Expired Date</td>
                        <td align="center">Qty Reture</td>
                        <td width="25%" align="center">Scan ID Barang</td>
                        <td>Jenis Satuan</td>
                        </thead>
                        <tbody id="body_reture">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_ret" onclick="confirmSaveReture()"><i
                        class="fa fa-check"></i> Konfirmasi
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_ret"><i
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
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-check"></i> Yes            </button>
            </div>
        </div>
    </div>
</div>


<script type='text/javascript'>

    function showRequest(id, tanggal, tujuan) {
        $('#modal-request').modal('show');
        $('#req_tanggal').val(tanggal);
        $('#req_id_permintaan').val(id);
        $('#judul_req').html("Konfirmasi Permintaan Obat");
        var table = "";
        var data = [];
        PermintaanObatPoliAction.listDetailPermintaan(id, false, tujuan, "Y", {
            callback: function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {

                        var qtyBox = "";
                        var qtyLembar = "";
                        var qtyBiji = "";

                        if (item.qtyBox != null) {
                            qtyBox = item.qtyBox;
                        }
                        if (item.qtyLembar != null) {
                            qtyLembar = item.qtyLembar;
                        }
                        if (item.qtyBiji != null) {
                            qtyBiji = item.qtyBiji;
                        }

                        table += "<tr id=" + item.idObat + ">" +
                                "<td>" + item.idObat + "</td>" +
                                "<td>" + item.namaObat + "</td>" +
                                "<td align='center'>" + '<span id=qtyBox' + item.idObat + '>' + qtyBox + '</span>' + "</td>" +
                                "<td align='center'>" + '<span id=qtyLembar' + item.idObat + '>' + qtyLembar + '</span>' + "</td>" +
                                "<td align='center'>" + '<span id=qtyBiji' + item.idObat + '>' + qtyBiji + '</span>' + "</td>" +
                                "<td align='center'>" + '<span id=qtyReq' + item.idObat + '>' + item.qty + '</span>' + "</td>" +
                                "<td align='center'>" + '<span id=jenisSatuan' + item.idObat + '>' + item.jenisSatuan + '</span>' + "</td>" +
                                "<td align='center'>" + '<span id=qtyApp' + item.idObat + '>' + item.qty + '</span>' + "</td>" +
                                "<td align='center'>" + '<img border="0" id=img' + item.idObat + ' class="hvr-grow" onclick="editQty(\'' + item.idObat + '\',\'' + item.lembarPerBox + '\',\'' + item.bijiPerLembar + '\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
                                "</tr>";
                    });
                }
            }
        });
        $('#body_request').html(table);
    }

    function showReture(id, tanggal, tujuan){
        $('#modal-reture').modal({show:true, backdrop:'static'});
        $('#ret_tanggal').val(tanggal);
        $('#ret_id_permintaan').val(id);
        $('#judul_ret').html("Konfirmasi Reture Obat");
        var table = "";
        var data = [];
        PermintaanObatPoliAction.listDetailObatRequest(id, {callback:function(response){
            if (response != null) {
                $.each(response, function (i, item) {

                    var idBar = item.idBarang;
                    var str = idBar.substring(8, 15);
                    var idBarang = idBar.replace(str, '*******');

                    var expired = $.datepicker.formatDate('dd-mm-yy', new Date(item.expiredDate));
                    table += "<tr>" +
                            "<td>" + idBarang+
                            '<input type="hidden" id=id_barang' + i + ' value=' + item.idBarang + '>'+
                            '<input type="hidden" id=id_transaksi' + i + ' value=' + item.idTransaksiObatDetail + '>' + "</td>" +
                            "<td>" + item.namaObat + "</td>" +
                            "<td align='center'>" + expired + "</td>" +
                            "<td align='center'>" + item.qtyApprove + "</td>" +
                            "<td align='center'>" +
                            '<div class="input-group">'+
                            '<input autocomplete="off" class="form-control" onchange="cekIdBarang(\''+i+'\',this.value, \''+item.idBatch+'\')" id=cek_id_barang'+i+'>' +
                            '<div class="input-group-addon">'+
                            '<span id=loading'+i+'></span> '+
                            '</div>'+
                            '</div>'+
                            "</td>" +
                            "<td >" + item.jenisSatuan + "</td>" +

                            "</tr>";
                });
            }
        }});

        $('#body_reture').html(table);
    }

    function cekIdBarang(id, valueIdBarang, idBatch){
        var idBarang = $('#id_barang'+id).val();
        var flag = "";
        var load = "";
        if(valueIdBarang != ''){
            $('#loading'+id).html('<i style="color: #00a65a" class="fa fa-circle-o-notch fa-spin"></i>');
            setTimeout(function () {

                if(idBarang == valueIdBarang){
                    flag = "Y";
                    load = '<img src="<s:url value="/pages/images/icon_success.ico"/>" style="height: 20px; width: 20px;">';
                }else{
                    flag = "N";
                    load = '<img src="<s:url value="/pages/images/icon_failure.ico"/>" style="height: 20px; width: 20px;">';
                }
                if(idBatch != ''){
                    ObatPoliAction.updateDiterimaFlagBatch(idBatch, flag, {callback: function (response) {
                        if(response == "success"){
                            $('#loading'+id).html(load);
                        }else{
                            $('#loading'+id).html('<img src="<s:url value="/pages/images/icon_warning.ico"/>" style="height: 20px; width: 20px;">');
                        }
                    }});
                }else{
                    $('#loading'+id).html('<img src="<s:url value="/pages/images/icon_warning.ico"/>" style="height: 20px; width: 20px;">');
                }

            },100);
        }else{
            $('#loading' + id).html('');
        }
    }


    <%--function editQty(id, lembar, biji) {--%>
        <%--if ($('#img' + id).attr('src') == '/simrs/pages/images/edit-flat-new.png') {--%>
            <%--var url = '<s:url value="/pages/images/save_flat.png"/>';--%>
            <%--$('#img' + id).attr('src', url);--%>
            <%--var qtyApp = $('#qtyApp'+id).text();--%>
            <%--$('#qtyApp' + id).html('<input type="number" min="1" id=newQty' + id + ' class="form-control" value=' + qtyApp + ' style="width:80px;">');--%>
        <%--} else {--%>
            <%--var url = '<s:url value="/pages/images/edit-flat-new.png"/>';--%>

            <%--var jenisSatuan = $('#jenisSatuan' + id).text();--%>
            <%--var qtyBox = $('#qtyBox' + id).text();--%>
            <%--var qtyLembar = $('#qtyLembar' + id).text();--%>
            <%--var qtyBiji = $('#qtyBiji' + id).text();--%>
            <%--var qtyReq = $('#qtyReq' + id).text();--%>
            <%--var approve = $('#newQty' + id).val();--%>

            <%--var stok = 0;--%>

            <%--if ("box" == jenisSatuan) {--%>
                <%--stok = qtyBox;--%>
            <%--}--%>
            <%--if ("lembar" == jenisSatuan) {--%>
                <%--stok = parseInt(qtyLembar) + (parseInt(lembar * parseInt(qtyBox)));--%>
            <%--}--%>
            <%--if ("biji" == jenisSatuan) {--%>
                <%--stok = parseInt(qtyBiji) + ((parseInt(lembar * parseInt(qtyBox))) * parseInt(biji));--%>
            <%--}--%>

            <%--if (approve != '' && parseInt(approve) > 0) {--%>
                <%--if (parseInt(approve) <= parseInt(stok) && parseInt(approve) <= parseInt(qtyReq)) {--%>
                    <%--$('#img' + id).attr('src', url);--%>
                    <%--var newQty = $('#newQty' + id).val();--%>
                    <%--$('#qtyApp' + id).html('<span id=qtyApp' + id + '>' + newQty + '</span>');--%>
                <%--} else {--%>
                    <%--$('#warning_request').show().fadeOut(5000);--%>
                    <%--$('#msg_request').text("Qty Approve tidak boleh melebihi stok");--%>
                <%--}--%>
            <%--} else {--%>
                <%--$('#warning_request').show().fadeOut(5000);--%>
                <%--$('#msg_request').text("Silahkan cek kembali data inputan");--%>
            <%--}--%>
        <%--}--%>
    <%--}--%>

    function saveRequest() {
        var data = $('#tabel_request').tableToJSON();
        var idPermintaan = $('#req_id_permintaan').val();
        var stringData  = JSON.stringify(data);
        var cek = false;
        var app = "";
        $.each(data, function (i, item) {
            if ("" == data[i]["Qty Approve"]) {
                cek = true;
            }
        });
        if (cek) {
            $('#warning_request').show().fadeOut(5000);
            $('#msg_request').text("Silahkan klik tombol save untuk menyimpan qty Approve..!");
        }else{
            if(stringData != '[]'){
                $('#save_req').hide();
                $('#load_req').show();
                dwr.engine.setAsync(true);
                PermintaanObatPoliAction.saveKonfirmasiRequest(stringData, idPermintaan, false, { callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#modal-request').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#save_req').show();
                        $('#load_req').hide();
                        $('body').scrollTop(0);
                    } else {
                        $('#warning_request').show().fadeOut(5000);
                        $('#msg_request').text(response);
                        $('#save_req').show();
                        $('#load_req').hide();
                    }
                }
                });
            }else{
                $('#warning_request').show().fadeOut(5000);
                $('#msg_request').text("Silahkan cek kembali data inputan berikut...!");
            }
        }
    }

    function confirmSaveReture() {
        var data = $('#tabel_request_detail').tableToJSON();
        var idPermintaan = $('#ret_id_permintaan').val();
        var stringData  = JSON.stringify(data);
        var result = [];
        var cek = false;

        $.each(data, function (i, item) {
           var scan = $('#cek_id_barang'+i).val();
            if(scan == ""){
                cek = true;
            }
        });

        if(cek){
            $('#warning_reture').show().fadeOut(5000);
            $('#msg_reture').text("Silahkan lakukan konfirmasi pada masing-masing id barang..!");
        }else{
           $('#modal-confirm-dialog').modal('show');
            $('#save_con').attr('onclick','saveReture(\''+idPermintaan+'\')');
        }
    }

    function saveReture(idPermintaan){
        $('#modal-confirm-dialog').modal('hide');
        $('#save_ret').hide();
        $('#load_ret').show();
        dwr.engine.setAsync(true);
        PermintaanObatPoliAction.saveKonfirmasiReture(idPermintaan, false, { callback: function (response) {
            if (response == "success") {
                dwr.engine.setAsync(false);
                $('#modal-reture').modal('hide');
                $('#info_dialog').dialog('open');
                $('#save_ret').show();
                $('#load_ret').hide();
                $('body').scrollTop(0);
            } else {
                $('#warning_request').show().fadeOut(5000);
                $('#msg_reture').text(response);
                $('#save_ret').show();
                $('#load_ret').hide();
            }
        }
        });
    }
    function printRequest(idApp, idPermin){
        PermintaanObatPoliAction.printPermintaanObat(idApp, idPermin, { callback: function (response) {
            if (response == "success") {
                dwr.engine.setAsync(false);
                $('#modal-reture').modal('hide');
                $('#info_dialog').dialog('open');
                $('#save_ret').show();
                $('#load_ret').hide();
                $('body').scrollTop(0);
            } else {
                $('#warning_request').show().fadeOut(5000);
                $('#msg_reture').text(response);
                $('#save_ret').show();
                $('#load_ret').hide();
            }
        }
        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>