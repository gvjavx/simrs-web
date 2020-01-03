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
<div class="se-pre-con"></div>
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
                                <table class="table table-striped" style="margin-top: 20px">
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
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <div style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px; text-align: center" class="card card-4 pull-right">
                                    <img border="2" id="img_ktp" src="<s:property value="periksaLab.urlKtp"/>" style="cursor: pointer; height: 90px; width: 190px; margin-top: 4px">
                                </div>
                                <table class="table table-striped">
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
                                    <tr>
                                        <td><b>Labotarium</b></td>
                                        <td>
                                            <table><label class="label label-success"><span id="lab_name"></span> </label></table>
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
                        <%--<button class="btn btn-success btn-outline" onclick="showModal(1)" style="margin-bottom: 10px"><i class="fa fa-plus"></i> Tambah Parameter--%>
                        <%--</button>--%>
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
                    <h3 class="box-title"><i class="fa fa-user-md"></i> Dokter Lab</h3>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_dok">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            Silahkan cek kembali data inputan!
                        </div>
                        <div class="alert alert-success alert-dismissible" style="display: none" id="success_dok">
                            <h4><i class="icon fa fa-info"></i> Info!</h4>
                            Data berhasil disimpan!
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label style="margin-bottom: -2px; width: 100%">Dokter</label>
                                    <select id="list_dokter" class="form-control select2"
                                            onchange="$(this).css('border','')">
                                        <option value=''>[Select One]</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <div class="form-group">
                                        <button class="btn btn-success" style="margin-top: 15px;" id="save_ket" onclick="saveDokterLab()"><i
                                                class="fa fa-arrow-right"></i> Save
                                        </button>
                                        <button style="display: none; cursor: no-drop; margin-top: 15px;" type="button"
                                                class="btn btn-success" id="load_ket"><i class="fa fa-spinner fa-spin"></i>
                                            Sedang Menyimpan...
                                        </button>
                                        <button class="btn btn-primary" onclick=""
                                                style="margin-top: 15px;" id="print_ket"><i
                                                class="fa fa-print"></i> Print
                                        </button>
                                        <a href="initForm_periksalab.action" class="btn btn-warning" onclick=""
                                           style="margin-top: 15px;" id="back_ket"><i
                                                class="fa fa-arrow-left"></i> Back
                                        </a>
                                    </div>
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
                            <input type="text" class="form-control" id="par_hasil" oninput="var warn =$('#war_hasil').is(':visible'); if (warn){$('#cor_hasil').show().fadeOut(3000);$('#war_hasil').hide()}">
                        </div>
                    </div>
                    <div class="col-md-2">
                        <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_hasil"><i
                                class="fa fa-times"></i> required</p>
                        <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_hasil">
                            <i class="fa fa-check"></i> correct</p>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Keterangan</label>
                        <div class="col-md-7">
                            <textarea id="par_ket" class="form-control" style="margin-top: 7px" oninput="var warn =$('#war_ket').is(':visible'); if (warn){$('#cor_ket').show().fadeOut(3000);$('#war_ket').hide()}"></textarea>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_ket"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_ket">
                                <i class="fa fa-check"></i> correct</p>
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
                            <s:select cssStyle="margin-top: 7px; width: 100%" onchange="var warn =$('#war_kategori').is(':visible'); if (warn){$('#cor_kategori').show().fadeOut(3000);$('#war_kategori').hide()}; listSelectLab(this)"
                                      list="#comboLab.listOfKategoriLab" id="lab_kategori"
                                      listKey="idKategoriLab"
                                      listValue="namaKategori"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_kategori"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_kategori">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Lab</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="lab_lab" onchange="var warn =$('#war_lab').is(':visible'); if (warn){$('#cor_lab').show().fadeOut(3000);$('#war_lab').hide()}; listSelectParameter(this);">
                                <option value=''>[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_lab"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_lab">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Parameter</label>
                        <div class="col-md-7">
                            <select class="form-control select2" multiple style="margin-top: 7px; width: 100%" id="lab_parameter" onchange="var warn =$('#war_param').is(':visible'); if (warn){$('#cor_param').show().fadeOut(3000);$('#war_param').hide()}">
                                <option value=''>[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_param"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_param">
                                <i class="fa fa-check"></i> correct</p>
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

<div class="mask"></div>
<!-- /.content-wrapper -->
<script type='text/javascript'>

    var idDetailCheckup = $('#no_detail_checkup').val();
    var idPoli          = $('#id_palayanan').val();
    var idPeriksaLab    = $('#id_periksa_lab').val();

    $(document).ready(function () {
        $('#periksa_lab').addClass('active');
        listParameter();
        listLab();
        listSelectDokter();

        $('#img_ktp').on('click', function(e){
            e.preventDefault();
            var src = $('#img_ktp').attr('src');

            if(src != null && src != ""){
                $('.mask').html('<div class="img-box"><img src="'+ src +'"><a class="close">&times;</a>');

                $('.mask').addClass('is-visible fadein').on('animationend', function(){
                    $(this).removeClass('fadein is-visible').addClass('is-visible');
                });

                $('.close').on('click', function(){
                    $(this).parents('.mask').addClass('fadeout').on('animationend', function(){
                        $(this).removeClass('fadeout is-visible')
                    });
                });
            }
        });
    });

    function listSelectDokter() {
        var option = "";
        CheckupAction.listOfDokter(idPoli, function (response) {
            option = "<option value=''>[Select One]</option>";
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idDokter + "'>" + item.namaDokter + "</option>";
                });
            } else {
                option = option;
            }
        });
        $('#list_dokter').html(option);
    }

    function saveDokterLab(){

        var idDokter = $('#list_dokter').val();

        if (idPeriksaLab != '' && idDokter != '') {

            $('#save_ket').hide();
            $('#load_ket').show();
            dwr.engine.setAsync(true);
            PeriksaLabAction.saveEditDokterLab(idPeriksaLab, idDokter, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#success_dok').show().fadeOut(5000);
                        $('#save_ket').show();
                        $('#load_ket').hide();
                    } else {

                    }
                }
            })
        } else {
            $('#warning_dok').show().fadeOut(5000);
            if (idDokter == '') {

            }
        }
    }

    function toContent() {
        var back = $('#close_pos').val();
        var desti = "";

        if (back == 1) {
            desti = "#pos_lab";
        } else if (back == 2) {
            window.location.href = 'search_checkupdetail.action';
        }

        $('html, body').animate({
            scrollTop: $(desti).offset().top
        }, 2000);
    }

    function showModal(select) {

        if (select == 1) {
            $('#lab_kategori, #lab_lab, #lab_parameter').val('').trigger('change');
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

        var option = "";
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
                        $('#close_pos').val(1);
                    } else {

                    }
                }
            })
        } else {
            $('#warning_lab').show().fadeOut(5000);
            if (idKategori == '') {
                $('#war_kategori').show();
            }
            if (idLab == '') {
                $('#war_lab').show();
            }
            if (idLab == '') {
                $('#war_param').show();
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
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editParameter(\''+item.idPeriksaLabDetail+'\',\''+hasil+'\',\''+keterangan+'\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
                            "</tr>"
                });
            }
        });

        $('#body_parameter').html(table);
    }
    function editParameter(id, hasil, keterangan){
        $('#save_par').show();
        $('#load_par').hide();
        $('#par_hasil').val(hasil);
        $('#par_ket').val(keterangan);
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
                        $('#close_pos').val(1);
                    } else {

                    }
                }
            })
        } else {
            $('#warning_edit_parameter').show().fadeOut(5000);
            if (hasil == '') {
                $('#war_hasil').show();
            }
            if (ket == '') {
                $('#war_ket').show();
            }
        }
    }

    function listLab() {

        var table   = "";
        var data    = [];
        var lab     = "";

        PeriksaLabAction.listOrderLab(idDetailCheckup, function (response) {
            data = response;
            if (data != null) {
                $.each(data, function (i, item) {
                    if(item.idKategoriLab == "02"){
                        if (item.labName != null) {
                            lab = item.labName;
                        }
                    }
                });
            }
        });

        $('#lab_name').html(lab);
    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>