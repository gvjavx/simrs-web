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
    <script type='text/javascript' src='<s:url value="/dwr/interface/HeaderPelayananAction.js"/>'></script>

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
            Data Header Pelayanan
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Header Pelayanan</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="headerForm" method="post" namespace="/headerpelayanan"
                                    action="search_headerpelayanan.action"
                                    theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Header Pelayanan</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_tindakan" name="headerPelayanan.idPelayanan"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Pelayanan</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_tindakan" name="headerPelayanan.namaPelayanan"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="headerPelayanan.flag"
                                                  headerKey="Y" headerValue="Active" cssClass="form-control select2"
                                                  cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="headerForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_headerpelayanan.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a onclick="showModal('add')" class="btn btn-primary"><i class="fa fa-plus"></i>
                                            Tambah Pelayanan</a>
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Header Pelayanan</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td width="15%">ID</td>
                                <td>Pelayanan</td>
                                <td width="15%">Tipe pelayanan</td>
                                <td>Divisi</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idHeaderPelayanan"/></td>
                                    <td><s:property value="namaPelayanan"/></td>
                                    <td><s:property value="tipePelayanan"/></td>
                                    <td><s:property value="divisiName"/></td>
                                    <td align="center" width="10%">
                                        <img class="hvr-grow"
                                             onclick="showModal('detail', '<s:property value="idHeaderPelayanan"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-view-25.png"/>">
                                        <img class="hvr-grow"
                                             onclick="showModal('edit', '<s:property value="idHeaderPelayanan"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-create-25.png"/>">
                                        <img class="hvr-grow"
                                             onclick="showModal('delete', '<s:property value="idHeaderPelayanan"/>')"
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
            <div class="modal-body" id="temp_scrol">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3">Nama Pelayanan</label>
                        <div class="col-md-7">
                            <input class="form-control" id="set_nama_pelayanan"
                                    oninput="inputWarning('war_set_nama_pelayanan', 'cor_set_nama_pelayanan')">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_nama_pelayanan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_nama_pelayanan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3 jarak_atas">Tipe</label>
                        <div class="col-md-7">
                            <s:select list="#{'igd':'IGD', 'rawat_jalan' : 'Rawat Jalan',
                                                                'apotek' : 'Instalasi Farmasi RJ',
                                                                 'apotek_ri' : 'Instalasi Farmasi RI',
                                                                'rawat_inap' : 'Rawat Inap',
                                                                 'radiologi' : 'Radiologi', 'lab' : 'Laboratorium', 'gizi':'Instalasi Gizi'}"
                                      id="set_tipe_pelayanan" cssStyle="width: 100%"
                                      headerKey="" headerValue="[Select one]" cssClass="form-control select2"
                                      onchange="inputWarning('war_set_tipe_pelayanan', 'cor_set_tipe_pelayanan'); cekTipe(this.value)"></s:select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_tipe_pelayanan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_tipe_pelayanan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row" style="display: none" id="form_kategori">
                    <div class="form-group">
                        <label class="col-md-3 jarak_atas">Kategori</label>
                        <div class="col-md-7">
                            <s:select  list="#{'spesialis_nephrologi':'Poli Spesialis Nephrologi','spesilais_bedah_sarah':'Poli Spesialis Bedah Saraf',
                                'spesialis_btkv':'Poli Spesialis BTKV','klinik_nyeri':'Klinik Nyeri',
                                'spesialis_ortopedi':'Poli Spesialis Orthopaedi','spesialis_urologi':'Poli Spesialis Urologi',
                                'spesialis_jiwa':'Poli Spesialis Kedokteran Jiwa','spesialis_penyakit_dalam':'Poli Spesialis Penyakit Dalam',
                                'spesialis_jantung':'Poli Spesialis Jantung','spesialis_paru':'Poli Spesialis Paru',
                                'hemodialisa':'Poli Hemodialisa','fisioterapi':'Poli Fisioterapi','spesialis_onkologi':'Poli Spesialis Onkologi',
                                'rehab_medik':'Poli Spesialis Rehabilitasi Medis','dokter_umum':'Poli Umum','spesialis_gigi':'Poli Spesialis Bedah Gigi dan Mulut',
                                'spesialis_obstetri':'Poli Spesialis Kandungan','spesialis_neurologi':'Poli Spesialis Saraf',
                                'spesialis_tht':'Poli Spesialis THT','spesialis_anak':'Poli Spesialis Anak',
                                'spesialis_bedah':'Poli Spesialis Bedah Umum','spesialis_gigi':'Poli Gigi','spesialis_mata':'Poli Spesialis Mata','spesialis_kulit_kelamin':'Poli Spesialis Kulit dan Kelamin'}"
                                      id="set_kategori_pelayanan" cssStyle="width: 100%"
                                      headerKey="" headerValue="[Select one]" cssClass="form-control select2"
                                      onchange="inputWarning('war_set_kategori_pelayanan', 'cor_set_kategori_pelayanan')"></s:select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_kategori_pelayanan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_kategori_pelayanan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3 jarak_atas">Divisi</label>
                        <div class="col-md-7">
                            <s:action id="initComboPosition" namespace="/pelayanan" name="initComboPosition_pelayanan"/>
                            <s:select list="#initComboPosition.listOfComboPositions" id="set_divisi"
                                      listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2" cssStyle="width: 100%" onchange="inputWarning('war_set_divisi', 'cor_set_divisi')" />
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_divisi">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_divisi"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3 jarak_atas">Kode Vclaim</label>
                        <div class="col-md-7">
                           <input class="form-control" id="set_kode_vclaim" oninput="inputWarning('war_set_kode_vclaim', 'cor_set_kode_vclaim')">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_kode_vclaim">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_kode_vclaim"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3 jarak_atas">Menerima Vaksin</label>
                        <div class="col-md-7">
                            <div class="form-check" style="margin-top: 7px">
                                <input type="checkbox" id="set_vaksin" value="yes">
                                <label for="set_vaksin"></label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" id="set_id_header_pelayanan">
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Detail Data Pelayanan</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-striped" style="font-size: 12px">
                                <tr>
                                    <td width="30%"><b>ID</b></td>
                                    <td><span id="v_id"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama pelayanan</b></td>
                                    <td><span id="v_nama_pelayanan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tipe Pelayanan</b></td>
                                    <td><span id="v_tipe_pelayanan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Divisi</b></td>
                                    <td><span id="v_divisi"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kode Vclaim</b></td>
                                    <td><span id="v_kode_vclaim"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Menerima Vaksin</b></td>
                                    <td><span id="v_vaksin"></span></td>
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
            $('#set_id_header_pelayanan, #set_nama_pelayanan, #set_tipe_pelayanan').attr('disabled', false);
            $('#set_kategori_pelayanan, #set_kode_vclaim, #set_divisi').attr('disabled', false);
            $('#set_nama_pelayanan, #set_kode_vclaim, #set_id_header_pelayanan').val('');
            $('#set_tipe_pelayanan, #set_kategori_pelayanan, #set_divisi').val('').trigger('change');
            $('#set_vaksin').prop('checked', false);
            $('#save_add').attr('onclick', 'savePelayanan(\''+tipe+'\')');
            $('#set_judul').text("Tambah Pelayanan");
            $('#modal-add').modal({show: true, backdrop: 'static'});
        }
        if ('detail' == tipe) {
            $('#set_id_header_pelayanan, #set_nama_pelayanan, #set_tipe_pelayanan').attr('disabled', false);
            $('#set_kategori_pelayanan, #set_kode_vclaim, #set_divisi').attr('disabled', false);
            getDataPelayanan(id);
            $('#modal-view').modal({show: true, static: 'backdrop'});
        }
        if ('edit' == tipe) {
            $('#set_judul').text("Edit Pelayanan");
            $('#save_add').attr('onclick', 'savePelayanan(\''+tipe+'\')');
            $('#modal-add').modal({show: true, static: 'backdrop'});
            getDataPelayanan(id);
        }
        if ('delete' == tipe) {
            $('#set_id_header_pelayanan, #set_nama_pelayanan, #set_tipe_pelayanan').attr('disabled', true);
            $('#set_kategori_pelayanan, #set_kode_vclaim, #set_divisi').attr('disabled', true);
            $('#set_judul').text("Delete Pelayanan");
            $('#save_add').attr('onclick', 'savePelayanan(\''+tipe+'\')');
            $('#modal-add').modal({show: true, static: 'backdrop'});
            getDataPelayanan(id);
        }
    }

    function savePelayanan(tipe) {
        if(!cekSession()){
            var idHeaderPelayanan = $('#set_id_header_pelayanan').val();
            var namaPelayanan = $('#set_nama_pelayanan').val();
            var tipePelayanan = $('#set_tipe_pelayanan').val();
            var kategoriPelayanan = $('#set_kategori_pelayanan').val();
            var kodeVclaim = $('#set_kode_vclaim').val();
            var divisi = $('#set_divisi').val();

            var kategori = undefined;
            if(tipePelayanan == 'rawat_jalan'){
                if(kategoriPelayanan != ''){
                    kategori = kategoriPelayanan;
                }
            }else{
                kategori = "";
            }

            var isVaksin = "N";
            if($('#set_vaksin').is(':checked')){
                isVaksin = "Y";
            }

            var del = false;
            if(tipe == "delete"){
                del = true;
            }
            if (namaPelayanan && tipePelayanan && kodeVclaim && divisi != '' || del) {
                $('#save_add').hide()
                $('#load_add').show();
                var data = {
                    'tipe': tipe,
                    'id_header_pelayanan': idHeaderPelayanan,
                    'nama_pelayanan': namaPelayanan,
                    'tipe_pelayanan': tipePelayanan,
                    'kategori_pelayanan': kategori,
                    'kode_vclaim': kodeVclaim,
                    'divisi_id': divisi,
                    'is_vaksin': isVaksin
                };
                var dataString = JSON.stringify(data);
                dwr.engine.setAsync(true);
                HeaderPelayananAction.save(dataString, {
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
                $('#warning_add').show().fadeOut(5000);
                $('#msg_add').text("Silahkan cek kembali data inputan berikut...!");

                if (namaPelayanan == '') {
                    $('#war_set_nama_pelayanan').show();
                }
                if (tipePelayanan == '') {
                    $('#war_set_tipe_pelayanan').show();
                }
                if (kategoriPelayanan == '') {
                    $('#war_set_kategori_pelayanan').show();
                }
                if (kodeVclaim == '') {
                    $('#war_set_kode_vclaim').show();
                }
                if (divisi == '') {
                    $('#war_set_divisi').show();
                }
            }
        }
    }

    function getDataPelayanan(id) {
        if(!cekSession()){
            HeaderPelayananAction.init(id, function (res) {
                if (res.idHeaderPelayanan != null) {
                    $('#v_id').text(res.idHeaderPelayanan);
                    $('#v_nama_pelayanan').text(res.namaPelayanan);
                    $('#v_tipe_pelayanan').text(res.tipePelayanan);
                    $('#v_kategori_pelayanan').text(res.kategoriPelayanan);
                    $('#v_divisi').text(res.divisiName);
                    $('#v_kode_vclaim').text(res.kodeVclaim);

                    $('#set_id_header_pelayanan').val(res.idHeaderPelayanan);
                    $('#set_nama_pelayanan').val(res.namaPelayanan);
                    $('#set_tipe_pelayanan').val(res.tipePelayanan).trigger('change');
                    $('#set_kategori_pelayanan').val(res.kategoriPelayanan).trigger('change');
                    $('#set_kode_vclaim').val(res.kodeVclaim);
                    $('#set_divisi').val(res.divisiId).trigger('change');
                    if(res.isVaksin == 'Y'){
                        $('#set_vaksin').attr('checked', true);
                        $('#v_vaksin').text("Ya");
                    }else{
                        $('#set_vaksin').attr('checked', false);
                        $('#v_vaksin').text("Tidak");
                    }
                }else{
                    $('#headerForm').empty();
                }
            });
        }
    }

    function cekTipe(value){
        if(value == 'rawat_jalan'){
            $('#form_kategori').show();
        }else{
            $('#form_kategori').hide();
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>