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

    <script type='text/javascript' src='<s:url value="/dwr/interface/TindakanRawatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LabAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LabDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PeriksaLabAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PaketPeriksaAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>

    <style>
        .form-check {
            display: inline-block;
            padding-left: 2px;
        }

        .form-check input {
            padding: 0;
            height: initial;
            width: initial;
            margin-bottom: 0;
            display: none;
            cursor: pointer;
        }

        .form-check label {
            position: relative;
            cursor: pointer;
        }

        .form-check label:before {
            content: '';
            -webkit-appearance: none;
            background-color: transparent;
            border: 2px solid #0079bf;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05);
            padding: 10px;
            display: inline-block;
            position: relative;
            vertical-align: middle;
            cursor: pointer;
            margin-right: 5px;
        }

        .form-check input:checked + label:after {
            content: '';
            display: block;
            position: absolute;
            top: 2px;
            left: 9px;
            width: 6px;
            height: 14px;
            border: solid #0079bf;
            border-width: 0 2px 2px 0;
            transform: rotate(45deg);
        }
    </style>
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
            Tambah Paket Periksa Pasien
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

                            <sj:dialog id="waiting_dialog" openTopics="showDialogLoading"
                                       closeTopics="closeDialog" modal="true"
                                       resizable="false"
                                       height="250" width="600" autoOpen="false"
                                       title="Saving ...">
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
                        <div class="alert alert-danger alert-dismissible" style="display: none"
                             id="warning_tindakan">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg"></p>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-md-2" style="margin-top: 7px">NIK</label>
                                <div class="col-md-4">
                                    <input type="number" class="form-control" id="nik"
                                           oninput="var warn =$('#war_nik').is(':visible'); if (warn){$('#cor_nik').show().fadeOut(3000);$('#war_nik').hide()}">
                                </div>
                                <div class="col-md-2">
                                    <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                       id="war_nik"><i class="fa fa-times"></i> required</p>
                                    <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                       id="cor_nik"><i class="fa fa-check"></i> correct</p>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label class="col-md-2" style="margin-top: 7px">Nama</label>
                                <div class="col-md-4">
                                    <input style="margin-top: 7px" class="form-control" id="nama"
                                           oninput="var warn =$('#war_nama').is(':visible'); if (warn){$('#cor_nama').show().fadeOut(3000);$('#war_nama').hide()}">
                                </div>
                                <div class="col-md-2">
                                    <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                       id="war_nama"><i class="fa fa-times"></i> required</p>
                                    <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                       id="cor_nama"><i class="fa fa-check"></i> correct</p>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-4" style="margin-top: 7px">
                                    <button class="btn btn-danger" onclick="window.location.reload(true)"><i
                                            class="fa fa-refresh"></i> Reset
                                    </button>
                                    <button class="btn btn-success" onclick="savePasien()" id="save_pasien"><i
                                            class="fa fa-plus"></i> Tambah
                                    </button>
                                    <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                                            id="load_pasien">
                                        <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                                    </button>
                                </div>
                            </div>
                        </div>

                        <table class="table table-bordered table-striped" style="margin-top: 20px" id="table_pasien">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>NO RM</td>
                                <td>NIK</td>
                                <td>Nama</td>
                                <td width="12%">Status</td>
                                <td align="center" width="10%">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_pasien">
                            </tbody>
                        </table>
                    </div>

                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-4">
                                    <label>Perusahaan</label>
                                    <select class="form-control select2" id="perusahanan" onchange="var warn =$('#war_perusahaan').is(':visible'); if (warn){$('#cor_perusahaan').show().fadeOut(3000);$('#war_perusahaan').hide()}">
                                        <option value="">[Select One]</option>
                                    </select>
                                    <p style="color: red; display: none;"
                                       id="war_perusahaan"><i class="fa fa-times"></i> required</p>
                                    <p style="color: green; display: none;"
                                       id="cor_perusahaan"><i class="fa fa-check"></i> correct</p>
                                </div>
                                <div class="col-md-4">
                                    <label>Paket</label>
                                    <select class="form-control select2" id="paket" onchange="var warn =$('#war_paket').is(':visible'); if (warn){$('#cor_paket').show().fadeOut(3000);$('#war_paket').hide()}">
                                        <option value="">[Select One]</option>
                                    </select>
                                    <p style="color: red; display: none;"
                                       id="war_paket"><i class="fa fa-times"></i> required</p>
                                    <p style="color: green; display: none;"
                                       id="cor_paket"><i class="fa fa-check"></i> correct</p>
                                </div>
                                <div class="col-md-4">
                                    <div style="margin-top: 30px">
                                        <a class="btn btn-warning" href="initForm_checkupdetail.action"><i
                                                class="fa fa-arrow-left"></i> Back</a>
                                        <a class="btn btn-success" id="save_paket"
                                           onclick="savePaketPasien()"><i class="fa fa-check"></i> Save</a>
                                        <button style="display: none; cursor: no-drop;" type="button"
                                                class="btn btn-success" id="load_paket"><i
                                                class="fa fa-spinner fa-spin"></i>
                                            Sedang Menyimpan...
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-daftar-pasien">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Daftar Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add"></p>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label>NIK</label>
                            <input class="form-control" id="add_nik" oninput="$(this).css('border','')">
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px">Nama</label>
                            <input class="form-control" id="add_nama" oninput="$(this).css('border','')">
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px">Jenis Kelamin</label>
                            <select class="form-control" id="add_jk" onchange="$(this).css('border','')">
                                <option value="">[Select One]</option>
                                <option value="L">Laki-Laki</option>
                                <option value="P">Perempuan</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px">Tempat Lahir</label>
                            <input class="form-control" id="add_tempat_lahir" oninput="$(this).css('border','')">
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px">Tanggal Lahir</label>
                            <input class="form-control datepicker datemask" id="add_tanggal_lahir" onchange="$(this).css('border','')">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label>Agama</label>
                            <select class="form-control" id="add_agama" onchange="$(this).css('border','')">
                                <option value="">[Select One]</option>
                                <option value="Islam">Islam</option>
                                <option value="Kristen">Kristen</option>
                                <option value="Katolik">Katolik</option>
                                <option value="Hindu">Hindu</option>
                                <option value="Konguchu">Konguchu</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px">Profesi</label>
                            <input class="form-control" id="add_profesi">
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px">Suku</label>
                            <input class="form-control" id="add_suku">
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px">Alamat</label>
                            <input class="form-control" id="add_alamat">
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px">No Telp</label>
                            <input class="form-control" id="add_no_telp">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label>Provinsi</label>
                            <input class="form-control" id="add_provinsi" oninput="$(this).css('border','')">
                            <input type="hidden" id="add_id_provinsi">
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px">Kota</label>
                            <input class="form-control" id="add_kota" oninput="$(this).css('border','')">
                            <input type="hidden" id="add_id_kota">
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px">Kecamatan</label>
                            <input class="form-control" id="add_kecamatan" oninput="$(this).css('border','')">
                            <input type="hidden" id="add_id_kecamatan">
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px">Kelurahan/Desa</label>
                            <input class="form-control" id="add_desa" oninput="$(this).css('border','')">
                            <input type="hidden" id="add_id_desa">
                        </div>
                        <div class="form-group">
                            <label style="margin-top: 7px">Foto KTP</label>
                            <div class="input-group" id="img_file">
                              <span class="input-group-btn">
                              <span class="btn btn-default btn-file">
                               Browse… <s:file id="imgInp" accept=".jpg" name="fileUpload"
                                               onchange="$('#img_file').css('border','')"></s:file>
                                                    </span>
                                                    </span>
                                <input type="text" class="form-control" readonly>
                            </div>
                            <%--<img id="img-upload" width="100%"--%>
                            <%--src="<s:url value="/pages/images/ktp-default.jpg"/>"--%>
                            <%--style="border: darkgray solid 1px; height: 170px; margin-top: 7px"/>--%>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_add" onclick="saveNewPasien()"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_add"><i
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
                <h4 class="text-center">Do you want save this record?</h4>
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

<div class="mask"></div>
<!-- /.content-wrapper -->
<script type='text/javascript'>

    $(document).ready(function () {
        $('#daftar_paket').addClass('active');
        listSelectMaster();
        listPaketPeriksa();

        $(document).on('change', '.btn-file :file', function () {
            var input = $(this),
                label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [label]);
        });

        $('.btn-file :file').on('fileselect', function (event, label) {

            var input = $(this).parents('.input-group').find(':text'),
                log = label;

            if (input.length) {
                input.val(log);
            } else {
                if (log) alert(log);
            }

        });
    });

    function toContent(){
        var pos = $('#close_pos').val();
        if(pos == 1){
            window.location.href = 'initForm_daftarpaket.action';
        }
    }

    function listSelectMaster() {
        var option = "<option value=''>[Select One]</option>";
        PaketPeriksaAction.getListMasterVendor(function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.nomorMaster + "'>" + item.nama + "</option>";
                });
                $('#perusahanan').html(option);
            } else {
                $('#perusahanan').html('');
            }
        });
    }

    function listPaketPeriksa() {
        var option = "<option value=''>[Select One]</option>";
        PaketPeriksaAction.getListPaketPeriksa(function (response) {
            if (response.length > 0) {
                console.log(response);
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idPaket + "'>" + item.namaPaket + "</option>";
                });
                $('#paket').html(option);
            } else {
                $('#paket').html('');
            }
        });
    }

    function savePasien() {

        var nik = $('#nik').val();
        var nama = $('#nama').val();
        var cek = false;
        var data = $('#table_pasien').tableToJSON();

        if (nik != '' && nama != '') {

            $.each(data, function (i, item) {
                var noKtp = data[i]["NIK"];
                if (noKtp == nik) {
                    cek = true;
                }
            });

            if (cek) {
                $('#warning_tindakan').show().fadeOut(5000);
                $('#msg').text("Data pasien sudah ada dengan NIK " + nik);
            } else {
                $('#save_pasien').hide();
                $('#load_pasien').show();
                dwr.engine.setAsync(true);
                PaketPeriksaAction.cekRMPasien(nik, {
                    callback: function (response) {
                        if (response.idPasien != null && response.idPasien != '') {
                            $('#save_pasien').show();
                            $('#load_pasien').hide();
                            var table = '<tr id="row' + nik + '">' +
                                '<td>' + response.idPasien + '</td>' +
                                '<td>' + nik + '</td>' +
                                '<td>' + nama + '</td>' +
                                '<td style="vertical-align: middle"><label class="label label-success">No RM sudah ada</label></td>' +
                                '<td align="center">' +
                                '<img border="0" class="hvr-grow" onclick="delRow(\'' + nik + '\')" src="<s:url value="/pages/images/icons8-cancel-25.png"/>" style="cursor: pointer;">' +
                                '</td>'+
                                '</tr>';
                            $('#body_pasien').append(table);
                            $('#nik').val('');
                            $('#nama').val('');
                        } else {
                            $('#save_pasien').show();
                            $('#load_pasien').hide();
                            var table = '<tr id="row' + nik + '">' +
                                '<td >' + '<span id="no_rm' + nik + '">' + '</td>' +
                                '<td>' + nik + '</td>' +
                                '<td>' + nama + '</td>' +
                                '<td style="vertical-align: middle"><label class="label label-warning" id="lbl'+nik+'"><span id="sts'+nik+'">No RM tidak ada</span></label></td>' +
                                '<td align="center">' +
                                '<img border="0" class="hvr-grow" onclick="delRow(\'' + nik + '\')" src="<s:url value="/pages/images/icons8-cancel-25.png"/>" style="cursor: pointer;">' +
                                '<img border="0" id="btn_new'+nik+'" class="hvr-grow" onclick="daftarBaru(\'' + nik + '\',\'' + nama + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">' +
                                '</td>'+
                                '</tr>';
                            $('#body_pasien').append(table);
                            $('#nik').val('');
                            $('#nama').val('');
                        }
                    }
                });

            }

        } else {
            $('#warning_tindakan').show().fadeOut(5000);
            $('#msg').text("Silahkan cek kembali data inputan...!");
            if (nik == '') {
                $('#war_nik').show();
            }
            if (nama == '') {
                $('#war_nama').show();
            }
        }
    }

    function delRow(id) {
        $('#row' + id).remove();
    }

    function daftarBaru(nik, nama) {
        $('#modal-daftar-pasien').modal({show: true, backdrop: 'static'});
        $('#add_nik').val(nik);
        $('#add_nama').val(nama);
    }

    function saveNewPasien(){

        var data = "";
        var nik = $('#add_nik').val();
        var nama = $('#add_nama').val();
        var jk = $('#add_jk').val();
        var tempatLahir = $('#add_tempat_lahir').val();
        var tanggalLahir = $('#add_tanggal_lahir').val();
        var agama = $('#add_agama').val();
        var profesi = $('#add_profesi').val();
        var suku = $('#add_suku').val();
        var alamat = $('#add_alamat').val();
        var provinsi = $('#add_id_provinsi').val();
        var kota = $('#add_id_kota').val();
        var kecamatan = $('#add_id_kecamatan').val();
        var desa = $('#add_id_desa').val();

        if( nik != '' && nama != '' && jk != '' && tempatLahir != '' && tanggalLahir != '' &&
            agama != '' && provinsi != '' && kota != '' && kecamatan != '' && desa != ''){

            data = {
                'nik':nik,
                'nama':nama,
                'jk':jk,
                'tempat_lahir':tempatLahir,
                'tanggal_lahir':tanggalLahir,
                'agama':agama,
                'profesi':profesi,
                'suku':suku,
                'alamat':alamat,
                'desa_id':desa
            };

            var objectString = JSON.stringify(data);
            $('#save_add').hide();
            $('#load_add').show();
            dwr.engine.setAsync(true);
            PaketPeriksaAction.saveNewPasien(objectString, {callback: function (response) {
                    if(response.status == "success"){
                        $('#save_add').show();
                        $('#load_add').hide();
                        $('#info_dialog').dialog('open');
                        $('#modal-daftar-pasien').modal('hide');
                        $('#no_rm'+response.noKtp).text(response.idPasien);
                        $('#btn_new'+response.noKtp).hide();
                        $('#lbl'+response.noKtp).removeClass("label label-warning").addClass("label label-success");
                        $('#sts'+response.noKtp).text("Berhasil membuat no RM");
                    }else{
                        $('#save_add').show();
                        $('#load_add').hide();
                        $('#warning_add').show();
                        $('#msg_add').text(response.msg).fadeOut(5000);
                    }
                }});
        }else{
            $('#warning_add').show().fadeOut(5000);
            $('#msg_add').text("Silahkan cek kembali data inputan anda...!");
            if(nik == ''){
                $('#add_nik').css('border','solid 1px red');
            }
            if(nama == ''){
                $('#add_nama').css('border','solid 1px red');
            }
            if(jk == ''){
                $('#add_jk').css('border','solid 1px red');
            }
            if(tempatLahir == ''){
                $('#add_tempat_lahir').css('border','solid 1px red');
            }
            if(tanggalLahir == ''){
                $('#add_tanggal_lahir').css('border','solid 1px red');
            }
            if(agama == ''){
                $('#add_agama').css('border','solid 1px red');
            }
            if(provinsi == ''){
                $('#add_provinsi').css('border','solid 1px red');
            }
            if(kota == ''){
                $('#add_kota').css('border','solid 1px red');
            }
            if(kecamatan == ''){
                $('#add_kecamatan').css('border','solid 1px red');
            }
            if(desa == ''){
                $('#add_desa').css('border','solid 1px red');
            }
        }

    }

    function savePaketPasien() {

        var perusahaan = $('#perusahanan').val();
        var paket = $('#paket').val();
        var data = $('#table_pasien').tableToJSON();
        var cek = false;

        var result = [];

        $.each(data, function (i, item) {
            var idPasien = data[i]["NO RM"];
            if(idPasien == ""){
                cek = true;
            }
            result.push({'id_pasien': idPasien, 'id_paket': paket, 'id_perusahaan': perusahaan});
        });

        var jsonStinng = JSON.stringify(result);

        if(perusahaan != '' && paket != '' && jsonStinng != '[]'){

            if(cek){
                $('#warning_tindakan').show().fadeOut(5000);
                $('#msg').text("Terdapat No RM yang masih kosong, silahkan daftarkan dulu...!");
            }else{
                $("#waiting_dialog").dialog('open');
                dwr.engine.setAsync(true);
                PaketPeriksaAction.savePaketPasien(jsonStinng, {
                    callback: function (response) {
                        if (response.status == "success") {
                            $("#waiting_dialog").dialog('close');
                            $('#info_dialog').dialog('open');
                            $('#close_pos').val(1);
                        } else {
                            $("#waiting_dialog").dialog('close');
                            $('#error_dialog').dialog('open');
                            $('#errorMessage').text(response.msg);

                        }
                    }
                });
            }

        }else{
            $('#warning_tindakan').show().fadeOut(5000);
            $('#msg').text("Silahkan cek kembali data inputan...!");
            if(perusahaan == ''){
                $('#war_perusahaan').show();
            }
            if(paket == ''){
                $('#war_paket').show();
            }
        }
    }

    var functions, mapped;
    $('#add_provinsi').typeahead({
        minLength: 1,
        source: function (query, process) {
            functions = [];
            mapped = {};

            var data = [];
            dwr.engine.setAsync(false);
            ProvinsiAction.initComboProvinsi(query, function (listdata) {
                data = listdata;
            });

            $.each(data, function (i, item) {
                var labelItem = item.provinsiName;
                mapped[labelItem] = {id: item.provinsiId, label: labelItem};
                functions.push(labelItem);
            });

            process(functions);
        },
        updater: function (item) {
            var selectedObj = mapped[item];
            var namaAlat = selectedObj.label;
            document.getElementById("add_id_provinsi").value = selectedObj.id;
            prov = selectedObj.id;
            return namaAlat;
        }
    });

    $('#add_kota').typeahead({
        minLength: 1,
        source: function (query, process) {
            functions = [];
            mapped = {};

            var data = [];
            dwr.engine.setAsync(false);
            ProvinsiAction.initComboKota(query, prov, function (listdata) {
                data = listdata;
            });
            //alert(prov);
            $.each(data, function (i, item) {
                //alert(item.kotaName);
                var labelItem = item.kotaName;
                mapped[labelItem] = {id: item.kotaId, label: labelItem};
                functions.push(labelItem);
            });

            process(functions);
        },
        updater: function (item) {
            var selectedObj = mapped[item];
            var namaAlat = selectedObj.label;
            document.getElementById("add_id_kota").value = selectedObj.id;

            kab = selectedObj.id;
            return namaAlat;
        }
    });

    $('#add_kecamatan').typeahead({
        minLength: 1,
        source: function (query, process) {
            functions = [];
            mapped = {};

            var data = [];
            dwr.engine.setAsync(false);
            ProvinsiAction.initComboKecamatan(query, kab, function (listdata) {
                data = listdata;
            });
            //alert(prov);
            $.each(data, function (i, item) {
                //alert(item.kotaName);
                var labelItem = item.kecamatanName;
                mapped[labelItem] = {id: item.kecamatanId, label: labelItem};
                functions.push(labelItem);
            });

            process(functions);
        },
        updater: function (item) {
            var selectedObj = mapped[item];
            var namaAlat = selectedObj.label;
            document.getElementById("add_id_kecamatan").value = selectedObj.id;

            kec = selectedObj.id;
            return namaAlat;
        }
    });

    $('#add_desa').typeahead({
        minLength: 1,
        source: function (query, process) {
            functions = [];
            mapped = {};

            var data = [];
            dwr.engine.setAsync(false);
            ProvinsiAction.initComboDesa(query, kec, function (listdata) {
                data = listdata;
            });
            //alert(prov);
            $.each(data, function (i, item) {
                //alert(item.kotaName);
                var labelItem = item.desaName;
                mapped[labelItem] = {id: item.desaId, label: labelItem};
                functions.push(labelItem);
            });

            process(functions);
        },
        updater: function (item) {
            var selectedObj = mapped[item];
            var namaAlat = selectedObj.label;
            document.getElementById("add_id_desa").value = selectedObj.id;

            desa = selectedObj.id;
            return namaAlat;
        }
    });

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>