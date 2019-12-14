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
            $('#obat_poli').addClass('active');
        });

    </script>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatPoliAction.js"/>'></script>

</head>

<body class="hold-transition skin-blue fixed sidebar-mini">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Obat Poli
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
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Obat Inap</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="obatPoliForm" method="post" namespace="/obatpoli"
                                    action="search_obatpoli.action" theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Obat</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_pasien" cssStyle="margin-top: 7px"
                                                     name="obatPoli.idObat" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Jenis Obat</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:action id="initJenis" namespace="/jenisobat"--%>
                                                  <%--name="getListJenisObat_jenisobat"/>--%>
                                        <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                                  <%--list="#initJenis.listOfJenisObat" id="obat_jenis_obat"--%>
                                                  <%--listKey="idJenisObat"--%>
                                                  <%--listValue="namaJenisObat"--%>
                                                  <%--name="obatPoli.idJenisObat"--%>
                                                  <%--headerKey="" headerValue="[Select one]"--%>
                                                  <%--cssClass="form-control select2"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Obat</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_pasien" name="obatPoli.namaObat"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-8" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="obatPoliForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_rawatinap.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-primary">Action</button>
                                            <button type="button" class="btn btn-primary dropdown-toggle"
                                                    data-toggle="dropdown" style="height: 34px">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li onclick="showModal(1)"><a href="#"><i class="fa fa-plus"></i> Tambah
                                                    Obat</a></li>
                                                <li onclick="showModal(2)"><a href="#"><i class="fa fa-plus"></i> Tambah
                                                    & Request Obat</a></li>
                                                <li class="divider"></li>
                                                <li><a href="monitoringRequest_obatpoli.action"><i class="fa fa-tv"></i> Monitoring Request</a></li>
                                            </ul>
                                        </div>
                                        <a type="button" class="btn btn-info" href="monitoringRequest_obatpoli.action">
                                            <i class="fa fa-tv"></i> Monitoring Permintaan
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
                                                <img border="0" style="width: 150px; height: 150px"
                                                     src="<s:url value="/pages/images/spinner.gif"/>"
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Obat Poli</h3>
                    </div>
                    <div class="box-body">
                        <table id="myTable" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Obat</td>
                                <td>Nama Obat</td>
                                <td>Stok</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" status="listOfRawatInap">
                                <tr>
                                    <td><s:property value="idObat"/></td>
                                    <td><s:property value="namaObat"/></td>
                                    <td><s:property value="qty"/></td>
                                    <td align="center">
                                        <img border="0" class="hvr-grow" onclick="showRequestReture(1,'<s:property value="idObat"/>','<s:property value="namaObat"/>','<s:property value="qty"/>')"
                                                 src="<s:url value="/pages/images/request.png"/>"
                                                 style="cursor: pointer; height: 25px; width: 25px;">
                                        <img border="0" class="hvr-grow" onclick="showRequestReture(2,'<s:property value="idObat"/>','<s:property value="namaObat"/>','<s:property value="qty"/>')"
                                             src="<s:url value="/pages/images/return.ico"/>"
                                             style="cursor: pointer; height: 25px; width: 25px;">
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

<div class="modal fade" id="modal-add">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambah Obat</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="error_add"></p>
                </div>
                <div class="row">
                    <div class="form-group" id="jenis_form">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Obat</label>
                        <div class="col-md-7">
                            <s:action id="initJenis" namespace="/jenisobat"
                                      name="getListJenisObat_jenisobat"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#initJenis.listOfJenisObat" id="add_jenis_obat"
                                      listKey="idJenisObat"
                                      onchange="var warn =$('#war_add_jenis_obat').is(':visible'); if (warn){$('#cor_add_jenis_obat').show().fadeOut(3000);$('#war_add_jenis_obat').hide()}; listSelectObat(this)"
                                      listValue="namaJenisObat"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_add_jenis_obat"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_add_jenis_obat"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group" id="nama_form">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="add_id_obat"
                                    onchange="var warn =$('#war_add_obat').is(':visible'); if (warn){$('#cor_add_obat').show().fadeOut(3000);$('#war_add_obat').hide()};">
                                <option value="">[select one]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_add_obat"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_add_obat">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" id="set_id_obat">
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_add" onclick="saveAdd()"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_add"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-add-request">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambah & Request Obat
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_request">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="error_request"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Obat</label>
                        <div class="col-md-7">
                            <s:action id="initJenis" namespace="/jenisobat"
                                      name="getListJenisObat_jenisobat"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#initJenis.listOfJenisObat" id="req_jenis_obat"
                                      onchange="var warn =$('#war_req_jenis_obat').is(':visible'); if (warn){$('#cor_req_jenis_obat').show().fadeOut(3000);$('#war_req_jenis_obat').hide()}; listSelectObat(this)"
                                      listKey="idJenisObat"
                                      listValue="namaJenisObat"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_req_jenis_obat"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_req_jenis_obat"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="req_id_obat"
                                    onchange="var warn =$('#war_req_obat').is(':visible'); if (warn){$('#cor_req_obat').show().fadeOut(3000);$('#war_req_obat').hide()}">
                                <option value="">[select one]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_req_obat"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_req_obat">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah Obat</label>
                        <div class="col-md-7">
                            <s:textfield value="1" type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="req_qty"
                                         onkeypress="var warn =$('#war_req_qty').is(':visible'); if (warn){$('#cor_req_qty').show().fadeOut(3000);$('#war_req_qty').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_req_qty"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_req_qty"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_req" onclick="saveAddRequest()"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_req"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-request">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="judul"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_request-2">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="error_request-2"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" readonly="true" id="req-2_nama">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah Obat</label>
                        <div class="col-md-7">
                            <s:textfield value="1" type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="req-2_qty"
                                         onkeypress="var warn =$('#war_req-2_qty').is(':visible'); if (warn){$('#cor_req-2_qty').show().fadeOut(3000);$('#war_req-2_qty').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_req-2_qty"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_req-2_qty"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_req-2"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_req-2"><i
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

    function listSelectObat(select) {
        var idx = select.selectedIndex;
        var idJenis = select.options[idx].value;
        var option = "<option value=''>[Select One]</option>";
        var stok = "";

        if (idJenis != '') {
            ObatAction.listObat(idJenis, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idObat + "'>" + item.namaObat + "</option>";
                    });
                } else {
                    option = option;
                }
            });
        } else {
            option = option;
        }

        $('#add_id_obat').html(option);
        $('#req_id_obat').html(option);
    }

    function showModal(select) {
        if (select == 1) {
            $('#modal-add').modal('show');
        } else if (select == 2) {
            $('#modal-add-request').modal('show');
        }
    }

    function saveAdd() {

        var idJenis = $('#add_jenis_obat').val();
        var idObat = $('#add_id_obat').val();

        if (idJenis != '' && idObat != '') {

            $('#save_add').hide();
            $('#load_add').show();

            dwr.engine.setAsync(true);
            ObatPoliAction.saveAdd(idObat, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#modal-add').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#save_add').show();
                        $('#load_add').hide();
                    } else {
                        $('#warning_add').show().fadeOut(5000);
                        $('#error_add').text('Data obat sudah tersedia..!');
                        $('#save_add').show();
                        $('#load_add').hide();
                    }
                }
            });

        } else {
            $('#warning_add').show().fadeOut(5000);
            $('#error_add').text('Silahkan cek kembali data inputan..!');
            if (idJenis == '') {
                $('#war_add_jenis_obat').show();
            }
            if (idObat == '') {
                $('#war_add_obat').show();
            }
        }
    }

    function saveAddRequest() {

        var idJenis = $('#req_jenis_obat').val();
        var idObat = $('#req_id_obat').val();
        var qty = $('#req_qty').val();

        if (idJenis != '' && idObat != '' && qty > 0) {

            $('#save_req').hide();
            $('#load_req').show();

            dwr.engine.setAsync(true);
            ObatPoliAction.saveAddRequest(idObat, qty, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#modal-add-request').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#save_req').show();
                        $('#load_req').hide();
                    } else {
                        $('#warning_request').show().fadeOut(5000);
                        $('#error_request').text('Data obat sudah tersedia..!');
                        $('#save_req').show();
                        $('#load_req').hide();
                    }
                }
            });

        } else {
            $('#warning_request').show().fadeOut(5000);
            $('#error_request').text('Silahkan cek kembali data inputan..!');
            if (idJenis == '') {
                $('#war_req_jenis_obat').show();
            }
            if (idObat == '') {
                $('#war_req_obat').show();
            }
            if (qty == '' || qty <= 0) {
                $('#war_req_qty').show();
            }
        }
    }

    function showRequestReture(select, id, obat, qty){
        if(select == 1){
            $('#judul').html('Request Obat');
        }else  if(select == 2){
            $('#judul').html('Reture Obat');
        }
            $('#modal-request').modal('show');
            $('#req-2_nama').val(obat);
            $('#save_req-2').attr('onclick', 'saveRequest(\'' + select + '\', \'' + id + '\')').show();
    }

    function saveRequest(select, id) {

        var qty     = $('#req-2_qty').val();

        if (qty > 0) {

            $('#save_req-2').hide();
            $('#load_req-2').show();

            if(select == 1){
                dwr.engine.setAsync(true);
                ObatPoliAction.saveRequest(id, qty, {
                    callback: function (response) {
                        if (response == "success") {
                            dwr.engine.setAsync(false);
                            $('#modal-request').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#save_req-2').show();
                            $('#load_req-2').hide();
                        } else {
                            $('#warning_request-2').show().fadeOut(5000);
                            $('#error_request-2').text('Transaksi untuk obat tersebut sudah ada..!');
                            $('#save_req-2').show();
                            $('#load_req-2').hide();
                        }
                    }
                });
            }else if(select == 2 ){
                dwr.engine.setAsync(true);
                ObatPoliAction.saveReture(id, qty, {
                    callback: function (response) {
                        if (response == "success") {
                            dwr.engine.setAsync(false);
                            $('#modal-request').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#save_req-2').show();
                            $('#load_req-2').hide();
                        } else {
                            $('#warning_request-2').show().fadeOut(5000);
                            $('#error_request-2').text('Transaksi untuk obat tersebut sudah ada..!');
                            $('#save_req-2').show();
                            $('#load_req-2').hide();
                        }
                    }
                });
            }
        } else {
            $('#warning_request-2').show().fadeOut(5000);
            $('#error_request-2').text('Silahkan cek kembali data inputan..!');
            if (qty == '' || qty <= 0) {
                $('#war_req_qty').show();
            }
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>