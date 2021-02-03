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

    <script type='text/javascript' src='<s:url value="/dwr/interface/HeaderTindakanAction.js"/>'></script>
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
            Data All Tindakan
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Data All Tindakan</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="tindakanForm" method="post" namespace="/headertindakan"
                                    action="search_headertindakan.action"
                                    theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Tindakan</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_tindakan" name="headerTindakan.idHeaderTindakan"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Tindakan</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_tindakan" name="headerTindakan.namaTindakan"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Kategori INA BPJS</label>
                                    <div class="col-sm-4">
                                        <s:action id="initComboKategoriIna" namespace="/tindakan"
                                                  name="initComboKategoriIna_tindakan"/>
                                        <s:select list="#initComboKategoriIna.listOfComboKategoriTindakanIna"
                                                  id="idKategoriTindakanIna" name="headerTindakan.kategoriInaBpjs"
                                                  listKey="id" listValue="nama" headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2" cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="headerTindakan.flag"
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
                                        <a type="button" class="btn btn-danger" href="initForm_headertindakan.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a onclick="showModal('add')" class="btn btn-primary"><i class="fa fa-plus"></i>
                                            Tambah Tindakan</a>
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Tindakan</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped tablePasien">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Tindakan</td>
                                <td>Nama Tindakan</td>
                                <td>Kategori BPJS</td>
                                <td>Flag Konsul</td>
                                <td align="center">Tarif (Rp.)</td>
                                <td align="center" width="10%">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idHeaderTindakan"/></td>
                                    <td><s:property value="namaTindakan"/></td>
                                    <td><s:property value="namaKategoriBpjs"/></td>
                                    <td><s:property value="flagKonsulTele"/></td>
                                    <td align="right">
                                        <script>
                                            converterRupiah('<s:property value="standardCost"/>');
                                        </script>
                                    </td>
                                    <td align="center">
                                        <img class="hvr-grow"
                                             onclick="showModal('detail', '<s:property value="idHeaderTindakan"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-view-25.png"/>">
                                        <img class="hvr-grow"
                                             onclick="showModal('edit', '<s:property value="idHeaderTindakan"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-create-25.png"/>">
                                        <img class="hvr-grow"
                                             onclick="showModal('delete', '<s:property value="idHeaderTindakan"/>')"
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
    <div class="modal-dialog modal-md">
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
                        <label class="col-md-3" style="margin-top: 7px">Nama Tindakan</label>
                        <div class="col-md-7">
                            <input class="form-control" id="set_nama_tindakan"
                                   oninput="var warn =$('#war_set_nama_tindakan').is(':visible'); if (warn){$('#cor_set_nama_tindakan').show().fadeOut(3000);$('#war_set_nama_tindakan').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_nama_tindakan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_nama_tindakan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Kategori Ina BPJS</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="width: 100%" id="set_kategori_ina_bpjs"
                                    onchange="var warn =$('#war_set_kategori_ina_bpjs').is(':visible');
                                    if (warn){$('#cor_set_kategori_ina_bpjs').show().fadeOut(3000);$('#war_set_kategori_ina_bpjs').hide()}">
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_kategori_ina_bpjs">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_kategori_ina_bpjs"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Konsul Tele</label>
                        <div class="col-md-7">
                            <s:select list="#{'Y':'Ya'}" id="flagKonsulTele"
                                      headerKey="N" headerValue="Tidak" cssClass="form-control select2"
                                      cssStyle="width: 100%" onchange="
                                  var warn =$('#war_konsul_tele').is(':visible');
                                    if (warn){
                                    $('#cor_konsul_tele').show().fadeOut(3000);
                                    $('#war_konsul_tele').hide()
                                    }"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_konsul_tele">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_konsul_tele"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Is Vaksin</label>
                        <div class="col-md-7">
                            <s:select list="#{'Y':'Ya'}" id="set_vaksin"
                                      headerKey="N" headerValue="Tidak" cssClass="form-control select2"
                                      cssStyle="width: 100%" onchange="
                                  var warn =$('#war_set_vaksin').is(':visible');
                                    if (warn){
                                    $('#cor_set_vaksin').show().fadeOut(3000);
                                    $('#war_set_vaksin').hide()
                                    }"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_vaksin">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_vaksin"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Standart Cost</label>
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Detail Data Tindakan</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>ID Tindakan</b></td>
                                    <td><span id="v_id_tindakan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama Tindakan</b></td>
                                    <td><span id="v_nama_tindakan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kategori Ina Bpjs</b></td>
                                    <td><span id="v_kategori_tindakan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Flag Konsultasi</b></td>
                                    <td><span id="v_konsul"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Vaksin</b></td>
                                    <td><span id="v_vaksin"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Standart Cost</b></td>
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
            getKategoriIna();
            $('#set_nama_tindakan, #h_tarif, #set_tarif').val('');
            $('#set_kategori_ina_bpjs').val('').trigger('change');
            $('#flagKonsulTele, #set_vaksin').val('N').trigger('change');
            $('#save_add').attr('onclick', 'saveTindakan("")');
            $('#set_judul').text("Tambah Tindakan");
            $('#modal-add').modal({show: true, backdrop: 'static'});
        }
        if ('detail' == tipe) {
            $('#modal-view').modal({show: true, backdrop: 'static'});
            getDataTindakan(id);
        }
        if ('edit' == tipe) {
            getKategoriIna();
            $('#save_add').attr('onclick', 'saveTindakan(\'' + id + '\')');
            $('#set_judul').text("Edit Tindakan");
            $('#modal-add').modal({show: true, backdrop: 'static'});
            getDataTindakan(id);
        }
        if ('delete' == tipe) {
            $('#pesan').text("Do you want delete this record?");
            $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
            $('#save_con').attr('onclick', 'saveDelete(\'' + id + '\')');
        }
    }

    function saveTindakan(id) {
        var data = "";
        if(!cekSession()){
            var nama = $('#set_nama_tindakan').val();
            var idKategori = $('#set_kategori_ina_bpjs').val();
            var tarif = $('#h_tarif').val();
            var flagTele = $('#flagKonsulTele').val();
            var flagVaksin = $('#set_vaksin').val();
            if (nama != '' && idKategori != '' && tarif != '' && flagTele && flagVaksin != '') {
                $('#save_add').hide();
                $('#load_add').show();
                if (id != '') {
                    data = {
                        'id_tindakan': id,
                        'nama_tindakan': nama,
                        'kategori_ina_bpjs': idKategori,
                        'tarif': tarif,
                        'flag_tele': flagTele,
                        'flag_vaksin': flagVaksin
                    };
                    var dataString = JSON.stringify(data);
                    dwr.engine.setAsync(true);
                    HeaderTindakanAction.saveEdit(dataString, {
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
                } else {
                    data = {
                        'nama_tindakan': nama,
                        'kategori_ina_bpjs': idKategori,
                        'tarif': tarif,
                        'flag_tele': flagTele,
                        'flag_vaksin': flagVaksin
                    };
                    var dataString = JSON.stringify(data);
                    dwr.engine.setAsync(true);
                    HeaderTindakanAction.saveAdd(dataString, {
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
            } else {
                $('#warning_add').show().fadeOut(5000);
                $('#msg_add').text("Silahkan cek kembali data inputan berikut...!");

                if (nama == '') {
                    $('#war_set_nama_tindakan').show();
                }
                if (idKategori == '') {
                    $('#war_set_kategori_ina_bpjs').show();
                }
                if (tarif == '') {
                    $('#war_set_tarif').show();
                }
                if (flagTele == '') {
                    $('#war_konsul_tele').show();
                }
                if (flagVaksin == '') {
                    $('#war_set_vaksin').show();
                }
            }
        }
    }

    function getKategoriIna() {
        var option = '<option value="">[Select One]</option>';
        HeaderTindakanAction.getComboInaBpjs(function (res) {
            $.each(res, function (i, item) {
                if (res.length > 0) {
                    option += '<option value="' + item.id + '">' + item.nama + '</option>'
                }
            })
            $('#set_kategori_ina_bpjs').html(option);
        });
    }

    function getDataTindakan(id) {
        HeaderTindakanAction.initHeaderTindakan(id, function (res) {
            if (res.idHeaderTindakan != null) {
                $('#v_id_tindakan').text(res.idHeaderTindakan);
                $('#v_nama_tindakan').text(res.namaTindakan);
                $('#v_kategori_tindakan').text(res.namaKategoriBpjs);
                $('#v_konsul').text(res.flagKonsulTele);
                $('#v_vaksin').text(res.flagVaksin);
                $('#v_tarif').text("Rp. " + formatRupiahAtas(res.standardCost));

                $('#set_nama_tindakan').val(res.namaTindakan);
                $('#set_kategori_ina_bpjs').val(res.kategoriInaBpjs).trigger('change');
                $('#set_tarif').val(formatRupiahAtas(res.standardCost));
                $('#flagKonsulTele').val(res.flagKonsulTele).trigger('change');
                $('#set_vaksin').val(res.flagVaksin).trigger('change');
                $('#h_tarif').val(res.standardCost);
            }
        });
    }

    function saveDelete(id) {
        $('#modal-confirm-dialog').modal('hide');
        $('#waiting_dialog').dialog('open');
        if(!cekSession()){
            dwr.engine.setAsync(true);
            HeaderTindakanAction.saveDelete(id, {
                callback: function (res) {
                    if(res.status == "success"){
                        $('#waiting_dialog').dialog('close');
                        $('#info_dialog').dialog('open');
                        $('body').scrollTop(0);
                    }else{
                        $('#waiting_dialog').dialog('close');
                        $('#error_dialog').dialog('open');
                        $('#errorMessage').text(res.msg);
                        $('body').scrollTop(0);
                    }
                }
            });
        }
    }

    function setHargaDiskon(value) {
        var tarif = $('#h_tarif').val();
        if (value != '' && tarif != '') {
            var persen = (100 - parseFloat(value)) / 100;
            var hasil = persen * tarif;
            $('#set_tarif_diskon').val(formatRupiahAtas(hasil));
            $('#h_tarif_diskon').val(hasil);
        } else {
            $('#set_tarif_diskon').val('');
            $('#h_tarif_diskon').val('');
        }
    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>