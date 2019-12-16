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
    <script type='text/javascript' src='<s:url value="/dwr/interface/TindakanRawatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TeamDokterAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/DiagnosaRawatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LabAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LabDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PeriksaLabAction.js"/>'></script>

    <script type='text/javascript'>

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');

            }
        });

        $.subscribe('errorDialog', function (event, data) {
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });


    </script>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Periksa Lab Pasien
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
                        <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                    </div>
                    <div class="box-body">
                        <div class="row">

                            <div class="col-md-6">
                                <img border="0" src="<s:url value="/pages/images/ktp-tes.jpg"/>" style="cursor: pointer; margin-bottom: 20px; height: 100px; width: 200px;">
                                <table class="table table-striped">
                                    <tr>
                                        <td width="45%"><b>No Checkup</b></td>
                                        <td>
                                            <s:hidden id="id_periksa_lab" name="periksaLab.idPeriksaLab"></s:hidden>
                                            <s:hidden id="no_checkup" name="periksaLab.noCheckup"></s:hidden>
                                            <s:hidden id="no_detail_checkup" name="periksaLab.idDetailCheckup"></s:hidden>
                                            <s:hidden id="id_palayanan" name="periksaLab.idPelayanan"></s:hidden>
                                            <table><s:label name="periksaLab.noCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>No Checkup Detail</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.idDetailCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>NIK</b></td>
                                        <td>
                                            <table><s:label id="nik" name="periksaLab.nik"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Nama</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.namaPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Kelamin</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.jenisKelamin"></s:label></table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <table class="table table-striped">
                                    <tr>
                                        <td><b>Tempat, Tanggal Lahir</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.tempatTglLahir"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Pasien</b></td>
                                        <td>
                                            <table>
                                                <s:label name="periksaLab.jenisPeriksaPasien"></s:label>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Poli</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.namaPelayanan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Alamat</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.alamat"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Provinsi</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.provinsi"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kabupaten</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.kota"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kecamatan</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.kecamatan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Desa</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.desa"></s:label></table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="form-group" style="display: none">
                                <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
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
                        </div>
                    </div>
                    <div class="box-header with-border" id="pos_lab">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Parameter Pemeriksaan</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" onclick="showModal(1)" style="margin-bottom: 10px"><i class="fa fa-plus"></i> Tambah Parameter
                        </button>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Pemeriksaan</td>
                                <td>Hasil</td>
                                <td>Satuan</td>
                                <td>Keterangan Acuan</td>
                                <td>Keterangan</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_parameter">

                            </tbody>
                        </table>
                    </div>

                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                    <h3 class="box-title"><i class="fa fa-navicon"></i> Keterangan</h3>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_ket">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            Silahkan cek kembali data inputan!
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label style="margin-top: 7px">Keterangan</label>
                                    <select class="form-control" id="keterangan"
                                            onchange="$(this).css('border',''); selectKeterangan(this)">
                                        <option value=''>[Select One]</option>
                                        <option value='selesai'>Selesai</option>
                                        <option value='pindah'>Pindah Poli Lain</option>
                                        <option value='rujuk'>Rujuk Rawat Inap</option>
                                    </select>
                                </div>
                                <div id="form-poli" style="display: none">
                                    <div class="form-group">
                                        <label style="margin-top: 7px">Poli</label>
                                        <s:action id="initComboPoli" namespace="/checkup"
                                                  name="getComboPelayanan_checkup"/>
                                        <s:select
                                                  list="#initComboPoli.listOfPelayanan" id="poli_lain"
                                                  name="headerCheckup.idPelayanan" listKey="idPelayanan"
                                                  listValue="namaPelayanan"
                                                  onchange="$(this).css('border',''); listDokterKeterangan(this)"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control"/>
                                    </div>

                                    <div class="form-group">
                                        <label style="margin-top: 7px">Dokter</label>
                                        <select id="list_dokter" class="form-control"
                                                onchange="$(this).css('border','')">
                                            <option value=''>[Select One]</option>
                                        </select>

                                    </div>
                                </div>

                                    <div id="form-selesai" style="display: none">
                                        <div class="form-group">
                                            <label style="margin-top: 7px">Keterangan Selesai</label>
                                            <s:action id="initComboKet" namespace="/checkupdetail"
                                                      name="getListComboKeteranganKeluar_checkupdetail"/>
                                            <s:select list="#initComboKet.listOfKeterangan" id="ket_selesai"
                                                      name="headerCheckup.idPelayanan" listKey="keterangan"
                                                      listValue="keterangan"
                                                      onchange="$(this).css('border','')"
                                                      headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control"/>
                                        </div>
                                    </div>

                                <div id="kamar" style="display: none;">
                                    <div class="form-group">
                                        <label style="margin-top: 7px">Kelas</label>
                                        <s:action id="initComboKelas" namespace="/checkupdetail"
                                                  name="getListComboKelasRuangan_checkupdetail"/>
                                        <s:select onchange="$(this).css('border',''); listSelectRuangan(this)"
                                                  list="#initComboKelas.listOfKelasRuangan" id="kelas_kamar"
                                                  listKey="idKelasRuangan"
                                                  listValue="namaKelasRuangan"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control"/>
                                    </div>
                                    <div class="form-group">
                                        <label style="margin-top: 7px">Kamar</label>
                                        <select class="form-control" id="kamar_detail"
                                                onchange="$(this).css('border','')">
                                            <option value=''>[Select One]</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <button class="btn btn-success" onclick="saveKeterangan()"
                                            style="margin-top: 15px; width: 150px" id="save_ket"><i
                                            class="fa fa-arrow-right"></i> Save
                                    </button>
                                    <button style="display: none; cursor: no-drop; margin-top: 15px;" type="button"
                                            class="btn btn-success" id="load_ket"><i class="fa fa-spinner fa-spin"></i>
                                        Sedang Menyimpan...
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-edit-parameter">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Edit Parameter Pemeriksaan</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_edit_parameter">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Silahkan cek kembali data inputan!
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3">Hasil</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="par_hasil">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Keterangan</label>
                        <div class="col-md-7">
                            <textarea id="par_ket" class="form-control" style="margin-top: 7px"></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_par"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_par"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-lab">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Order Lab</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_lab">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Silahkan cek kembali data inputan!
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Kategori Lab</label>
                        <div class="col-md-7">
                            <s:action id="comboLab" namespace="/kategorilab"
                                      name="getListKategoriLab_kategorilab"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%" onchange="$(this).css('border',''); listSelectLab(this)"
                                      list="#comboLab.listOfKategoriLab" id="lab_kategori"
                                      listKey="idKategoriLab"
                                      listValue="namaKategori"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Lab</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="lab_lab" onchange="$(this).css('border',''); listSelectParameter(this);">
                                <option value=''>[Select One]</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Parameter</label>
                        <div class="col-md-7">
                            <select class="form-control select2" multiple style="margin-top: 7px; width: 100%" id="lab_parameter" onchange="$(this).css('border','')">
                                <option value=''>[Select One]</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_lab" onclick="saveLab()"><i class="fa fa-arrow-right"></i> Save</button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_lab">
                    <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>
<!-- /.content-wrapper -->
<script type='text/javascript'>

    var idDetailCheckup = $('#no_detail_checkup').val();
    var idPoli          = $('#id_palayanan').val();
    var idPeriksaLab    = $('#id_periksa_lab').val();

    $(document).ready(function () {
        $('#periksa_lab').addClass('active');
        listParameter();
    });

    function selectKeterangan(id) {
        var idx = id.selectedIndex;
        var idKtg = id.options[idx].value;

        if (idKtg == "pindah") {
            $("#form-poli").attr('style', 'display:block');
            $("#kamar").attr('style', 'display:none');
            $("#form-selesai").hide();
        }
        if (idKtg == "rujuk") {
            $("#kamar").attr('style', 'display:block');
            $("#form-poli").attr('style', 'display:none');
            $("#form-selesai").hide();
        }
        if (idKtg == "selesai" || idKtg == "") {
            $("#kamar").attr('style', 'display:none');
            $("#form-poli").attr('style', 'display:none');
            $("#form-selesai").show();
        }
    }

    function saveKeterangan() {
        var idKtg = $("#keterangan").val();
        var noCheckup = $("#no_checkup").val();
        var poli = "";
        var kelas = "";
        var kamar = "";
        var idDokter = "";
        var ket_selesai = "";

        if (idKtg != '') {
            if (idKtg == "pindah") {
                poli = $("#poli_lain").val();
                idDokter = $("#list_dokter").val();
                if (poli != '' && idDokter != '') {
                    $('#save_ket').hide();
                    $('#load_ket').show();
                    dwr.engine.setAsync(true);
                    CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, function (response) {
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(5);
                        $('#save_ket').show();
                        $('#load_ket').hide();
                    });
                } else {
                    $('#warning_ket').show().fadeOut(5000);
                    if (poli == '') {
                        $('#poli_lain').css('border', 'red solid 1px');
                    }
                    if (idDokter == '') {
                        $('#list_dokter').css('border', 'red solid 1px');
                    }
                }
            }

            if (idKtg == "rujuk") {
                kelas = $("#kelas_kamar").val();
                kamar = $("#kamar_detail").val();

                if (kelas != '' && kamar != '') {
                    $('#save_ket').hide();
                    $('#load_ket').show();
                    dwr.engine.setAsync(true);
                    CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter,ket_selesai, function (response) {
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(5);
                        $('#save_ket').show();
                        $('#load_ket').hide();
                    });
                }
                else {
                    $('#warning_ket').show().fadeOut(5000);
                    if(kelas == ''){
                        $('#kelas_kamar').css('border', 'red solid 1px');
                    }
                    if(kamar == ''){
                        $('#kamar_detail').css('border', 'red solid 1px');
                    }
                }
            }

            if(idKtg == "selesai"){
                ket_selesai = $('#ket_selesai').val();
                if(ket_selesai != ''){
                    $('#save_ket').hide();
                    $('#load_ket').show();
                    dwr.engine.setAsync(true);
                    CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, function (response) {
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(5);
                        $('#save_ket').show();
                        $('#load_ket').hide();
                    });
                }else{
                    $('#warning_ket').show().fadeOut(5000);
                    $('#ket_selesai').css('border', 'red solid 1px');
                }
            }
        } else {
            $('#warning_ket').show().fadeOut(5000);
            $('#keterangan').css('border', 'red solid 1px');
        }
    }

    function listSelectTindakan(idKategori) {
        var idx = idKategori.selectedIndex;
        var idKtg = idKategori.options[idx].value;
        var option = "<option value=''>[Select One]</option>";
        if (idKtg != '') {
            CheckupDetailAction.getListComboTindakan(idKtg, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idTindakan + "'>" + item.tindakan + "</option>";
                    });
                } else {
                    option = option;
                }
            });
        } else {
            option = option;
        }

        $('#tin_id_tindakan').html(option);
    }

    function toContent() {
        var back = $('#close_pos').val();
        var desti = "";

        if (back == 1) {
            desti = "#pos_tin";
        } else if (back == 2) {
            desti = "#pos_nosa";
        } else if (back == 3) {
            desti = "#pos_lab";
        } else if (back == 4) {
            window.location.href = 'search_checkupdetail.action';
        }

        $('html, body').animate({
            scrollTop: $(desti).offset().top
        }, 2000);
    }

    function showModal(select) {

        if (select == 1) {
            $('#lab_kategori, #lab_lab').val('');
            $('#save_lab').show();
            $('#load_lab, #warning_lab').hide();
            $('#lab_kategori, #lab_lab').css('border', '');
            $('#modal-lab').modal('show');
        }
    }

    function listSelectLab(select){
        var idx          = select.selectedIndex;
        var idKategori   = select.options[idx].value;

        var option = "<option value=''>[Select One]</option>";
        if(idKategori != ''){
            LabAction.listLab(idKategori, function(response){
                if (response != null){
                    $.each(response, function (i, item) {
                        option += "<option value='"+item.idLab+"'>" +item.namaLab+ "</option>";
                    });
                }else{
                    option = option;
                }
            });
        }else{
            option = option;
        }

        $('#lab_lab').html(option);
    }

    function listSelectParameter(select){
        var idx     = select.selectedIndex;
        var idLab   = select.options[idx].value;

        var option = "<option value=''>[Select One]</option>";
        if(idLab != ''){
            LabDetailAction.listLabDetail(idLab, function(response){
                if (response != null){
                    $.each(response, function (i, item) {
                        option += "<option value='"+item.idLabDetail+"'>" +item.namaDetailPeriksa+ "</option>";
                    });
                }else{
                    option = option;
                }
            });
        }else{
            option = option;
        }

        $('#lab_parameter').html(option);
    }

    function saveLab() {

        var idKategori  = $('#lab_kategori').val();
        var idLab       = $('#lab_lab').val();
        var idParameter = $('#lab_parameter').val();

        if (idDetailCheckup != '' && idKategori != '' && idLab != '') {
            $('#save_lab').hide();
            $('#load_lab').show();
            dwr.engine.setAsync(true);
            PeriksaLabAction.saveOrderLab(idDetailCheckup, idLab, idParameter, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        listParameter();
                        $('#modal-lab').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(3);
                    } else {

                    }
                }
            })
        } else {
            $('#warning_lab').show().fadeOut(5000);
            if (idKategori == '') {
                $('#lab_kategori').css('border', 'red solid 1px');
            }
            if (idLab == '') {
                $('#lab_lab').css('border', 'red solid 1px');
            }
        }
    }

    function listParameter() {

        var table       = "";
        var data        = [];

        PeriksaLabAction.listParameterPemeriksaan(idPeriksaLab, function (response) {
            data = response;
            console.log(data);
            if (data != null) {
                $.each(data, function (i, item) {

                    var pemeriksaan = "-";
                    var hasil       = "-";
                    var satuan      = "-";
                    var acuan       = "-";
                    var keterangan  = "-";

                    if(item.namaDetailPeriksa != null){
                        pemeriksaan = item.namaDetailPeriksa;
                    }
                    if(item.hasil != null){
                        hasil = item.hasil;
                    }
                    if(item.satuan != null){
                        satuan = item.satuan;
                    }
                    if(item.keteranganAcuan != null){
                        acuan = item.keteranganAcuan;
                    }
                    if(item.keteranganPeriksa != null){
                        keterangan = item.keteranganPeriksa;
                    }
                    table += "<tr>" +
                            "<td>" + pemeriksaan + "</td>" +
                            "<td>" + hasil + "</td>" +
                            "<td>" + satuan + "</td>" +
                            "<td>" + acuan + "</td>" +
                            "<td>" + keterangan + "</td>" +
                            "<td align='center'>" + '<img border="0" onclick="editParameter(\''+item.idPeriksaLabDetail+'\')" src="<s:url value="/pages/images/icon_edit.ico"/>" style="cursor: pointer">' + "</td>" +
                            "</tr>"
                });
            }
        });

        $('#body_parameter').html(table);
    }
    function editParameter(id){
        $('#save_par').show();
        $('#load_par').hide();
        $('#par_hasil, #par_ket');
        $('#save_par').attr('onclick','saveEditParameter(\''+id+'\')');
        $('#modal-edit-parameter').modal('show');
    }

    function saveEditParameter(id) {

        var hasil  = $('#par_hasil').val();
        var ket    = $('#par_ket').val();

        if (id != '' && hasil != '' && ket != '') {
            $('#save_par').hide();
            $('#load_par').show();
            dwr.engine.setAsync(true);
            PeriksaLabAction.saveEditParameterLab(id, hasil, ket, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        listParameter();
                        $('#modal-edit-parameter').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(3);
                    } else {

                    }
                }
            })
        } else {
            $('#warning_edit_parameter').show().fadeOut(5000);
//            if (idKategori == '') {
//                $('#lab_kategori').css('border', 'red solid 1px');
//            }
//            if (idLab == '') {
//                $('#lab_lab').css('border', 'red solid 1px');
//            }
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>