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
        .jarak_atas {
            margin-top: 7px
        }
    </style>

    <link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/radio_checkbox.css"/>">
    <script type='text/javascript' src='<s:url value="/dwr/interface/TindakanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ParameterPemeriksaanAction.js"/>'></script>

    <script type='text/javascript'>

        $(document).ready(function () {
            $('#tindakan').addClass('active');
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
            Data All Parameter Pemeriksaan
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian All Parameter Pemeriksaan</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="tindakanForm" method="post" namespace="/pemeriksaan"
                                    action="search_pemeriksaan.action"
                                    theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Parameter</label>
                                    <div class="col-sm-4">
                                       <input name="pemeriksaan.idParameterPemeriksaan" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4" style="margin-top: 7px">Nama Pemeriksaan</label>
                                    <div class="col-sm-4">
                                        <input name="pemeriksaan.namaPemeriksaan" class="form-control" style="margin-top: 7px">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Kategori</label>
                                    <div class="col-sm-4">
                                        <s:action id="comboLab2" namespace="/kategorilab"
                                                  name="getListKategoriLab_kategorilab"/>
                                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                                  list="#comboLab2.listOfKategoriLab" id="kategori"
                                                  listKey="idKategoriLab"
                                                  listValue="namaKategori"
                                                  name="pemeriksaan.idKategoriLab"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="pemeriksaan.flag"
                                                  headerKey="Y" headerValue="Active" cssClass="form-control select2"
                                                  cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="tindakanForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_pemeriksaan.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a onclick="showModal('add')" class="btn btn-primary"><i class="fa fa-plus"></i>
                                            Tambah Paramater</a>
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
                                        <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                                'OK':function() { $('#error_dialog').dialog('close'); }
                                                                            }"
                                        >
                                            <div class="alert alert-danger alert-dismissible">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                         name="icon_error"> System Found : <p id="errorMessage"></p>
                                                </label>
                                            </div>
                                        </sj:dialog>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Parameter Pemeriksaan</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped" style="font-size: 12px">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Parameter</td>
                                <td>Kategori</td>
                                <td>Nama Pemeriksaan</td>
                                <td>Keterangan Acuan L</td>
                                <td>Keterangan Acuan P</td>
                                <td>Satuan</td>
                                <td>Tarif (Rp.)</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idParameterPemeriksaan"/></td>
                                    <td><s:property value="namaKategori"/></td>
                                    <td><s:property value="namaPemeriksaan"/></td>
                                    <td><s:property value="keteranganAcuanL"/></td>
                                    <td><s:property value="keteranganAcuanP"/></td>
                                    <td><s:property value="satuan"/></td>
                                    <td align="right">
                                        <script>
                                            converterRupiah('<s:property value="tarif"/>');
                                        </script>
                                    </td>
                                    <td align="center" width="10%">
                                        <img class="hvr-grow"
                                             onclick="showModal('detail', '<s:property value="idParameterPemeriksaan"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-view-25.png"/>">
                                        <img class="hvr-grow"
                                             onclick="showModal('edit', '<s:property value="idParameterPemeriksaan"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-create-25.png"/>">
                                        <img class="hvr-grow"
                                             onclick="showModal('delete', '<s:property value="idParameterPemeriksaan"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/cancel-flat-new.png"/>">
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

<div class="modal fade" id="modal-add">
    <div class="modal-dialog" style="width: 50%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> <span id="set_judul"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Pemeriksaan</label>
                        <div class="col-md-7">
                            <input class="form-control" id="set_nama_pemeriksaan"
                                    oninput="var warn =$('#war_set_nama_pemeriksaan').is(':visible'); if (warn){$('#cor_set_nama_pemeriksaan').show().fadeOut(3000);$('#war_set_nama_pemeriksaan').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_nama_pemeriksaan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_nama_pemeriksaan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Kategori</label>
                        <div class="col-md-7">
                            <s:action id="comboLab" namespace="/kategorilab"
                                      name="getListKategoriLab_kategorilab"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#comboLab.listOfKategoriLab" id="set_kategori"
                                      listKey="idKategoriLab"
                                      onchange="var warn =$('#war_set_kategori').is(':visible'); if (warn){$('#cor_set_kategori').show().fadeOut(3000);$('#war_set_kategori').hide()}"
                                      listValue="namaKategori"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_kategori">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_kategori"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Keterangan Acuan L</label>
                        <div class="col-md-7">
                            <input class="form-control" id="set_ket_acuan_l" style="margin-top: 7px"
                                    oninput="var warn =$('#war_set_ket_acuan_l').is(':visible'); if (warn){$('#cor_set_ket_acuan_l').show().fadeOut(3000);$('#war_set_ket_acuan_l').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_ket_acuan_l">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_ket_acuan_l"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Keterangan Acuan P</label>
                        <div class="col-md-7">
                            <input class="form-control" style="margin-top: 7px" id="set_ket_acuan_p"
                                    oninput="var warn =$('#war_set_ket_acuan_p').is(':visible'); if (warn){$('#cor_set_ket_acuan_p').show().fadeOut(3000);$('#war_set_ket_acuan_p').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_ket_acuan_p">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_ket_acuan_p"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Satuan</label>
                        <div class="col-md-7">
                            <input class="form-control" id="set_satuan" style="margin-top: 7px"
                                    oninput="var warn =$('#war_set_satuan').is(':visible'); if (warn){$('#cor_set_satuan').show().fadeOut(3000);$('#war_set_satuan').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_satuan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_satuan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif</label>
                        <div class="col-md-7">
                            <div class="input-group" style="margin-top: 7px">
                                <div class="input-group-addon">
                                    Rp.
                                </div>
                                <input class="form-control" id="set_tarif"
                                       oninput="var warn =$('#war_set_tarif').is(':visible'); if (warn){$('#cor_set_tarif').show().fadeOut(3000);$('#war_set_tarif').hide()}; convertRpAtas(this.id, this.value, 'h_tarif')">
                                <input type="hidden" id="h_tarif">
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_tarif">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_tarif"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_add"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_add"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Detail Data Parameter Pemeriksaan</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-striped" style="font-size: 12px">
                                <tr>
                                    <td width="30%"><b>ID Parameter</b></td>
                                    <td><span id="v_id_parameter"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama Pemeriksaan</b></td>
                                    <td><span id="v_nama_pemeriksaan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kategori</b></td>
                                    <td><span id="v_kategori"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Katerangan Acuan L</b></td>
                                    <td><span id="v_ket_acuan_l"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Katerangan Acuan P</b></td>
                                    <td><span id="v_ket_acuan_p"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Satuan</b></td>
                                    <td><span id="v_satuan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tarif</b></td>
                                    <td><span id="v_tarif"></span></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
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
                <h4 id="pesan"></h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-check"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    function showModal(tipe, id) {
        if ('add' == tipe) {
            $('#save_add').attr('onclick', 'saveParams("")');
            $('#set_judul').text("Tambah Parameter Pemeriksaan");
            $('#modal-add').modal({show: true, backdrop: 'static'});
        }
        if ('detail' == tipe) {
            getDataParams(id);
            $('#modal-view').modal({show: true, static: 'backdrop'});
        }
        if ('edit' == tipe) {
            getDataParams(id);
            $('#set_judul').text("Edit Parameter Pemeriksaan");
            $('#save_add').attr('onclick', 'saveParams(\'' + id + '\')');
            $('#modal-add').modal({show: true, static: 'backdrop'});
        }
        if ('delete' == tipe) {
            $('#pesan').text('Do you want delete this record?');
            $('#modal-confirm-dialog').modal({show: true, static: 'static'});
            $('#save_con').attr('onclick','saveDelete(\''+id+'\')');
        }
    }

    function saveParams(id) {
        var data = "";
        if(!cekSession()){
            var namaPemeriksaan = $('#set_nama_pemeriksaan').val();
            var kategori = $('#set_kategori').val();
            var ketAcuanL = $('#set_ket_acuan_l').val();
            var ketAcuanP = $('#set_ket_acuan_p').val();
            var satuan = $('#set_satuan').val();
            var tarif = $('#h_tarif').val();
            if (namaPemeriksaan && kategori && ketAcuanL && ketAcuanP && satuan && tarif != '') {
                data = {
                    'id_parameter_pemeriksaan':id,
                    'nama_pemeriksaan': namaPemeriksaan,
                    'id_kategori_lab': kategori,
                    'keterangan_acuan_p': ketAcuanP,
                    'keterangan_acuan_l': ketAcuanL,
                    'tarif': tarif,
                    'satuan': satuan
                }
                var dataString = JSON.stringify(data);
                $('#save_add').hide();
                $('#load_add').show();
                if(id != ''){
                    dwr.engine.setAsync(true);
                    ParameterPemeriksaanAction.saveEdit(dataString, {
                        callback: function (response) {
                            if (response.status == "success") {
                                $('#modal-add').modal('hide');
                                $('#info_dialog').dialog('open');
                                $('#save_add').show();
                                $('#load_add').hide();
                                $('body').scrollTop(0);

                            } else {
                                $('#warning_add').show().fadeOut(5000);
                                $('#msg_add').text(response.msg);
                                $('#save_add').show();
                                $('#load_add').hide();
                            }
                        }
                    });
                }else{
                    dwr.engine.setAsync(true);
                    ParameterPemeriksaanAction.saveAdd(dataString, {
                        callback: function (response) {
                            if (response.status == "success") {
                                $('#modal-add').modal('hide');
                                $('#info_dialog').dialog('open');
                                $('#save_add').show();
                                $('#load_add').hide();
                                $('body').scrollTop(0);

                            } else {
                                $('#warning_add').show().fadeOut(5000);
                                $('#msg_add').text(response.msg);
                                $('#save_add').show();
                                $('#load_add').hide();
                            }
                        }
                    });
                }
            }else {
                $('#warning_add').show().fadeOut(5000);
                $('#msg_add').text("Silahkan cek kembali data inputan berikut...!");

                if (namaPemeriksaan == '') {
                    $('#war_set_nama_pemeriksaan').show();
                }
                if (kategori == '') {
                    $('#war_set_kategori').show();
                }
                if (ketAcuanP == '') {
                    $('#war_set_ket_acuan_p').show();
                }
                if (ketAcuanL == '') {
                    $('#war_set_ket_acuan_l').show();
                }
                if (satuan == '') {
                    $('#war_set_satuan').show();
                }
                if (tarif == '') {
                    $('#war_set_tarif').show();
                }
            }
        }
    }

    function getDataParams(id) {
        ParameterPemeriksaanAction.initParameterPemeriksaan(id, function (res) {
            if (res.idParameterPemeriksaan != null) {
                $('#v_id_parameter').text(id);
                $('#v_nama_pemeriksaan').text(res.namaPemeriksaan);
                $('#v_kategori').text(res.namaKategori);
                $('#v_ket_acuan_l').text(res.keteranganAcuanL);
                $('#v_ket_acuan_p').text(res.keteranganAcuanP);
                $('#v_satuan').text(res.satuan);
                $('#v_tarif').text("Rp. " + formatRupiahAtas(res.tarif));

                $('#set_nama_pemeriksaan').val(res.namaPemeriksaan);
                $('#set_kategori').val(res.idKategoriLab).trigger('change');
                $('#set_ket_acuan_l').val(res.keteranganAcuanL);
                $('#set_ket_acuan_p').val(res.keteranganAcuanP);
                $('#set_satuan').val(res.satuan);
                $('#set_tarif').val(formatRupiahAtas(res.tarif));
                $('#h_tarif').val(res.tarif);
            }
        });
    }

    function saveDelete(id) {
        $('#modal-confirm-dialog').modal('hide');
        $('#waiting_dialog').dialog('open');
        if(!cekSession()){
            dwr.engine.setAsync(true);
            ParameterPemeriksaanAction.saveDelete(id, {
                callback: function (res) {
                    if (res.status == "success") {
                        $('#waiting_dialog').dialog('close');
                        $('#info_dialog').dialog('open');
                        $('body').scrollTop(0);
                    } else {
                        $('#waiting_dialog').dialog('close');
                        $('#error_dialog').dialog('open');
                        $('#errorMessage').text(res.msg);
                        $('body').scrollTop(0);
                    }
                }
            });
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>