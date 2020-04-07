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
    <script type='text/javascript' src='<s:url value="/dwr/interface/StatusPeriksaAction.js"/>'></script>
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
            Edit Status Pasien
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
                                    <s:hidden id="no_checkup" name="headerDetailCheckup.noCheckup"></s:hidden>
                                    <s:hidden id="id_palayanan" name="headerDetailCheckup.idPelayanan"></s:hidden>
                                    <s:hidden id="no_detail_checkup" name="headerDetailCheckup.idDetailCheckup"/>
                                    <s:hidden id="id_pasien" name="headerDetailCheckup.idPasien"/>
                                    <s:hidden id="jenis_pasien" name="headerDetailCheckup.idJenisPeriksaPasien"/>
                                    <s:hidden id="jenis_bayar" name="headerDetailCheckup.metodePembayaran"/>
                                    <s:if test='headerDetailCheckup.idJenisPeriksaPasien == "bpjs"'>
                                        <tr>
                                            <td width="45%"><b>No SEP</b></td>
                                            <td style="vertical-align: middle;">
                                                <table>
                                                    <s:label cssClass="label label-success"
                                                             name="headerDetailCheckup.noSep"></s:label>
                                                </table>
                                            </td>
                                        </tr>
                                    </s:if>
                                    <tr>
                                        <td><b>No RM</b></td>
                                        <td>
                                            <table><s:label
                                                    name="headerDetailCheckup.idPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="45%"><b>No Checkup</b></td>
                                        <td>
                                            <table>
                                                <s:label name="headerDetailCheckup.noCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>No Checkup Detail</b></td>
                                        <td>
                                            <table><s:label
                                                    name="headerDetailCheckup.idDetailCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>NIK</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.nik"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Nama</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.namaPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Kelamin</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.jenisKelamin"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Tempat, Tanggal Lahir</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.tempatTglLahir"></s:label></table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <div style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px; text-align: center"
                                     class="card card-4 pull-right">
                                    <img border="2" id="img_ktp" src="<s:property value="headerDetailCheckup.urlKtp"/>"
                                         style="cursor: pointer; height: 90px; width: 190px; margin-top: 4px">
                                </div>
                                <table class="table table-striped">
                                    <tr>
                                        <td><b>Jenis Pasien</b></td>
                                        <td>
                                            <table>
                                                <s:label id="jenis_periksa"
                                                         name="headerDetailCheckup.jenisPeriksaPasien"></s:label>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Poli</b></td>
                                        <td>
                                            <table>
                                                <s:label name="headerDetailCheckup.namaPelayanan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Alamat</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.alamat"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Desa</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.desa"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kecamatan</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.kecamatan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kabupaten</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.kota"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Provinsi</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.provinsi"></s:label></table>
                                        </td>
                                    </tr>
                                </table>
                            </div>

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
                            <!-- /.col -->
                        </div>
                    </div>
                    <div class="box-body">

                        <div class="row">
                            <div class="col-md-6">
                                <div id="msg_bpjs"></div>
                            </div>
                            <div class="col-md-6">
                                <div id="msg_rujukan"></div>
                            </div>
                        </div>

                        <div class="alert alert-danger alert-dismissible" id="warning_jenis" style="display: none">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_jenis"></p>
                        </div>

                        <div class="box-header with-border"></div>
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-user"></i> Data Perubahan Status</h3>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 10px">Jenis Periksa</label>
                                    <div class="col-md-8">
                                        <select class="form-control select2" id="penjamin" onchange="selectJenisPeriksa(this.value)">
                                            <option value="">[Select One]</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="box-header with-border"></div>
                        <div class="row" style="margin-top: 20px; display: none" id="form-bpjs">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Diagnosa Awal</label>
                                    <div class="col-md-8">
                                        <input class="form-control" id="diagnosa_awal" oninput="$(this).css('border','')">
                                        <textarea readonly rows="3" id="diagnosa_ket" class="form-control" style="margin-top: 7px"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">No Bpjs</label>
                                    <div class="col-md-8">
                                        <div class="input-group" style="margin-top: 7px">
                                            <s:textfield onkeypress="$(this).css('border','')" name="headerDetailCheckup.noBpjs" cssClass="form-control" id="no_bpjs"></s:textfield>
                                            <div class="input-group-btn">
                                                <a class="btn btn-success" onclick="checkBpjs()">
                                                 <span id="btn-cek-bpjs"><i class="fa fa-search"></i> Check</span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Perujuk/Asal</label>
                                    <div class="col-md-8">
                                        <select onchange="$(this).css('border','')" class="form-control" style="margin-top: 7px; width: 100%" id="perujuk">
                                            <option value="">[Select One]</option>
                                            <option value="1">PPK 1 - Puskesmas</option>
                                            <option value="1">PPK 2 - Rumah Sakit</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Keterangan Perujuk</label>
                                    <div class="col-md-8">
                                        <input class="form-control" id="intansi_perujuk" oninput="$(this).css('border','')">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">No Rujukan</label>
                                    <div class="col-md-8">
                                        <div class="input-group" style="margin-top: 7px">
                                            <input class="form-control" id="no_rujukan" oninput="$(this).css('border','')">
                                            <div class="input-group-btn">
                                                <a class="btn btn-success" onclick="cekNoRujukan()">
                                                    <span id="btn-cek-rujukan"><i class="fa fa-search"></i> Check</span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px" onchange="$(this).css('border','')">No PPK Rujukan</label>
                                    <div class="col-md-8">
                                        <input class="form-control" style="margin-top: 7px" id="ppk_rujukan" oninput="$(this).css('border','')">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Tanggal Rujukan</label>
                                    <div class="col-md-8">
                                        <input class="form-control datepicker" style="margin-top: 7px" id="tgl_rujukan" onchange="$(this).css('border','')">
                                    </div>
                                </div>

                                <input type="hidden" id="kelas_pasien">
                                <input type="hidden" id="id_pelayanan_bpjs">
                                <input type="hidden" id="status_bpjs">
                                <input type="hidden" id="status_rujukan">

                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Foto Surat Rujuk</label>
                                    <div class="col-md-8">
                                        <div class="input-group" style="margin-top: 7px" id="img_surat_rujuk">
                                                    <span class="input-group-btn">
                                                        <span class="btn btn-default btn-file">
                                                            Browse… <s:file id="url_do" accept=".jpg"
                                                                            name="fileUploadDoc"
                                                                            onchange="$('#img_file').css('border','')"></s:file>
                                                        </span>
                                                    </span>
                                            <input type="text" class="form-control" readonly id="surat_rujuk">
                                        </div>
                                    </div>
                                    <canvas id="temp_surat_rujuk" style="display: none"></canvas>
                                </div>
                            </div>
                        </div>
                        <div class="row" id="form-umum" style="display: none">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 10px">Metode Pembayaran</label>
                                    <div class="col-md-8">
                                        <select style="width: 100%" class="form-control select2" id="metode_pembayaraan">
                                            <option value="">[Select One]</option>
                                            <option value="tunai">Tunai</option>
                                            <option value="non_tunai">Non Tunai</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 10px">Uang Muka</label>
                                    <div class="col-md-8">
                                        <div class="input-group" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                Rp.
                                            </div>
                                            <input type="hidden" class="form-control" id="uang_muka">
                                            <input class="form-control" id="nominal_uang_muka">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" id="form-asuransi" style="display: none">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 10px">Nama Asuransi</label>
                                    <div class="col-md-8">
                                        <select style="width: 100%" class="form-control select2" id="asuransi">
                                            <option value="">[Select One]</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 10px">No Kartu</label>
                                    <div class="col-md-8">
                                        <input class="form-control" style="margin-top: 7px" id="no_kartu">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 10px">Cover Biaya</label>
                                    <div class="col-md-8">
                                        <div class="input-group" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                Rp.
                                            </div>
                                            <input class="form-control" id="cover_biaya">
                                            <input type="hidden" id="val_cover_biaya">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="box-header with-border"></div>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-md-offset-3 col-md-5 text-center">
                                <button class="btn btn-warning"><i class="fa fa-times"></i> Close</button>
                                <button class="btn btn-success" onclick="savePerubahan()"><i class="fa fa-check"></i> Save</button>
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

<script type='text/javascript'>

    function formatRupiah2(angka) {
        var number_string = angka.replace(/[^,\d]/g, '').toString(),
            split = number_string.split(','),
            sisa = split[0].length % 3,
            rupiah = split[0].substr(0, sisa),
            ribuan = split[0].substr(sisa).match(/\d{3}/gi);

        if (ribuan) {
            separator = sisa ? '.' : '';
            rupiah += separator + ribuan.join('.');
        }

        rupiah = split[1] != undefined ? rupiah + ',' + split[1] : rupiah;
        return rupiah;
    }

    $(document).ready(function () {
        $('#status_pasien').addClass('active');
        initlistPenjamin();

        $(document).on('change', '.btn-file :file', function () {
            var input = $(this),
                label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [label]);
        });

        var canvas = document.getElementById('temp_surat_rujuk');
        var ctx = canvas.getContext('2d');

        $('.btn-file :file').on('fileselect', function (event, label) {

            var input = $(this).parents('.input-group').find(':text'),
                log = label;

            if (input.length) {
                $('#img_surat_rujuk').css('border','');
                input.val(log);
                var reader = new FileReader();
                reader.onload = function(event){
                    var img = new Image();
                    img.onload = function(){
                        canvas.width = img.width;
                        canvas.height = img.height;
                        ctx.clearRect(0,0,canvas.width,canvas.height);
                        ctx.drawImage(img,0,0);
                    }
                    img.src = event.target.result;
                }
                reader.readAsDataURL(event.target.files[0]);
            } else {
                if (log) alert(log);
            }

        });

        var nominal = document.getElementById('cover_biaya');
        if(nominal != null && nominal != ''){
            nominal.addEventListener('keyup', function (e) {
                nominal.value = formatRupiah2(this.value);
                var valCover = nominal.value.replace(/[.]/g, '');

                if(valCover != ''){
                    $('#val_cover_biaya').val(valCover);
                }else{
                    $('#val_cover_biaya').val('');
                }
            });
        }

        var uangMuka = document.getElementById('nominal_uang_muka');
        if(uangMuka != null && uangMuka != ''){
            uangMuka.addEventListener('keyup', function (e) {
                uangMuka.value = formatRupiah2(this.value);
                var valCover = uangMuka.value.replace(/[.]/g, '');

                if(valCover != ''){
                    $('#uang_muka').val(valCover);
                }else{
                    $('#uang_muka').val('');
                }
            });
        }

    });

    var menus, mapped;
    $('#diagnosa_awal').typeahead({
        minLength: 3,
        source: function (query, process) {
            menus = [];
            mapped = {};

            var data = [];
            dwr.engine.setAsync(false);
            CheckupAction.getListBpjsDiagnosaAwal(query, function (listdata) {
                data = listdata;
            });

            $.each(data, function (i, item) {
                var labelItem = item.namaDiagnosaBpjs;
                mapped[labelItem] = {
                    id: item.kodeDiagnosaBpjs,
                    label: labelItem,
                    name: item.namaDiagnosaBpjs
                };
                menus.push(labelItem);
            });

            process(menus);
        },
        updater: function (item) {
            var selectedObj = mapped[item];
            // insert to textarea diagnosa_ket
            $("#diagnosa_ket").val(selectedObj.name);
            return selectedObj.id;
        }
    });

    function toContent(){
        window.location.href = 'initForm_statuspasien.action';
    }

    function selectJenisPeriksa(jenis){
        if(jenis != ''){
            if(jenis == 'bpjs'){
                $('#form-bpjs').show();
                $('#form-umum').hide();
                $('#form-asuransi').hide();
            }
            if(jenis == "umum"){
                $('#form-umum').show();
                $('#form-bpjs').hide();
                $('#form-asuransi').hide();
            }
            if(jenis == "asuransi"){
                listSelectAsuransi();
                $('#form-asuransi').show();
                $('#form-umum').hide();
                $('#form-bpjs').hide();
            }
        }else{
            $('#form-bpjs').hide();
            $('#form-umum').hide();
            $('#form-asuransi').hide();
        }
    }

    function initlistPenjamin() {
        var option = "";
        var jenisPasien = $('#jenis_pasien').val();
        CheckupAction.getComboJenisPeriksaPasienWithBpjs(function (response) {
            if (response.length > 0) {
                console.log(response);
                option = "<option value=''>[Select One]</option>";
                $.each(response, function (i, item) {
                    if( item.idJenisPeriksaPasien != jenisPasien
                        && item.idJenisPeriksaPasien != "paket_perusahaan"
                        &&  item.idJenisPeriksaPasien != "paket_individu"
                        &&  item.idJenisPeriksaPasien != "ptpn"){
                        option += '<option value="' + item.idJenisPeriksaPasien + '">' + item.keterangan + '</option>';
                    }
                });
                $('#penjamin').html(option);
            }
        });
    }

    function listSelectAsuransi() {
        var option = "<option value=''>[Select One]</option>";
        CheckupAction.getComboAsuransi(function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += '<option value="' + item.idAsuransi + '">' + item.namaAsuransi + '</option>';
                });
                $('#asuransi').html(option);
            }else {
                $('#asuransi').html(option);
            }
        });
    }

    function checkBpjs() {
        var noBpjs = $('#no_bpjs').val();
        $('#btn-cek-bpjs').html('<i class="fa fa-circle-o-notch fa-spin"></i> Loading...');
        dwr.engine.setAsync(true);
        CheckupAction.checkStatusBpjs(noBpjs, {
            callback: function (response) {
                var warnClass = "";
                var msg = "";
                var val = "";

                if (response.keteranganStatusPeserta == "AKTIF") {

                    $('#kelas_pasien').val(response.kodeKelas);
                    val = "aktif";
                    warnClass = "alert-success";
                    msg = "No Bpjs berhasil diverifikasi dengan status AKTIF!";
                } else if (response.keteranganStatusPeserta == "TIDAK AKTIF") {
                    val = "tidak aktif";
                    warnClass = "alert-warning";
                    msg = "No Bpjs berhasil diverifikasi dengan status TIDAK AKTIF!";
                } else {
                    val = "tidak ditemukan";
                    warnClass = "alert-warning";
                    msg = "No Bpjs tidak ditemukan!";
                }

                var warning = '<div class="alert ' + warnClass + ' alert-dismissible">'+
                    '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>'
                    + msg +
                    '</div>';

                $('#status_bpjs').val(val);
                $('#msg_bpjs').html(warning);
                $('#btn-cek-bpjs').html('<i class="fa fa-search"></i> Check');

            }
        });

    }

    function cekNoRujukan() {
        var noRujukan = $('#no_rujukan').val();
        var perujuk = $('#perujuk').val();
        var jenisRujukan = "";

        if (noRujukan != '' && perujuk != '') {
            if (perujuk == '1') {
                jenisRujukan = "P";
            }else if (perujuk == '2') {
                jenisRujukan = "R";
            }

            $('#btn-cek-rujukan').html('<i class="fa fa-circle-o-notch fa-spin"></i> Loading...')
            dwr.engine.setAsync(true);
            CheckupAction.checkSuratRujukan(noRujukan, jenisRujukan, {
                callback: function (response) {

                    var warnClass = "";
                    var msg = "";
                    var val = "";

                    if(response.status == "200"){
                        val = "aktif";
                        warnClass = "alert-success";
                        msg =   '<p>Nomor Rujukan Berhasil Diverifikasi..!</p>'+
                            '<p>Jenis Rawat  : '+response.namaPelayanan+'</p>'+
                            '<p>Poli Rujukan : '+response.namaPoliRujukan+'</p>';
                        $('#id_pelayanan_bpjs').val(response.kodePoliRujukan);
                        $('#ppk_rujukan').val(response.kdProviderProvUmum);
                        $('#intansi_perujuk').val(response.namaProvPerujuk);
                        $('#tgl_rujukan').val(response.tglCetakKartu);
                    }else{
                        val = "tidak ditemukan";
                        warnClass = "alert-warning";
                        msg = response.message;
                        $('#id_pelayanan_bpjs').val("IGD");
                    }

                    var warning = '<div class="alert ' + warnClass + ' alert-dismissible">' +
                        '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' + msg +
                        '</div>';

                    $('#status_rujukan').val(val);
                    $('#msg_rujukan').html(warning);
                    $('#btn-cek-rujukan').html('<i class="fa fa-search"></i> Check');
                }
            });
        }
    }

    function savePerubahan(){

        var data = "";

        var jenisPeriksa    = $('#penjamin').val();
        var noBpjs          = $('#no_bpjs').val();
        var idDiagnosa      = $('#diagnosa_awal').val();
        var namaDiagnosa    = $('#diagnosa_ket').val();
        var perujuk         = $('#perujuk').val();
        var namaPerujuk     = $('#intansi_perujuk').val();
        var noRujukan       = $('#no_rujukan').val();
        var noPPK           = $('#ppk_rujukan').val();
        var tangalRujukan   = $('#tgl_rujukan').val();
        var idPasien        = $('#id_pasien').val();
        var idDetail        = $('#no_detail_checkup').val();
        var idkelas         = $('#kelas_pasien').val();
        var idPelayananBpjs = $('#id_pelayanan_bpjs').val();
        var metodePembayaran= $('#metode_pembayaraan').val();
        var uangMuka        = $('#uang_muka').val();
        var asuransi        = $('#asuransi').val();
        var noKartu         = $('#no_kartu').val();
        var coverBiaya      = $('#val_cover_biaya').val();
        var fotoSurat       = $('#surat_rujuk').val();
        var canvas = document.getElementById('temp_surat_rujuk');
        var dataURL = canvas.toDataURL("image/png"),
            dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
        var statusBpjs      = $('#status_bpjs').val();
        var statusRujukan   = $('#status_rujukan').val();

        if(jenisPeriksa != ''){

            if(jenisPeriksa == "bpjs"){

                if(idDiagnosa != '' && noBpjs != '' && noRujukan != '' && perujuk != ''
                && noRujukan != '' && noPPK != '' && tangalRujukan != '' && dataURL != '' && namaPerujuk != ''){
                    data = {
                        'jenis_periksa':jenisPeriksa,
                        'no_bpjs':noBpjs,
                        'id_diagnosa':idDiagnosa,
                        'nama_diagnosa':namaDiagnosa,
                        'perujuk':perujuk,
                        'nama_perujuk':namaPerujuk,
                        'no_rujukan':noRujukan,
                        'no_ppk':noPPK,
                        'tanggal_rujukan':tangalRujukan,
                        'id_pasien':idPasien,
                        'id_detail_checkup':idDetail,
                        'id_kelas':idkelas,
                        'id_pelayanan':idPelayananBpjs,
                        'foto_surat':dataURL
                    }

                    if(statusBpjs != '' && statusRujukan != ''){
                        var result = JSON.stringify(data);
                        $('#waiting_dialog').dialog('open');
                        dwr.engine.setAsync(true);
                        StatusPeriksaAction.savePerubahanStatus(result, {callback: function (response) {
                                if(response.status == "success"){
                                    $('#waiting_dialog').dialog('close');
                                    $('#info_dialog').dialog('open');
                                }else{
                                    $('#waiting_dialog').dialog('close');
                                    $('#error_dialog').dialog('open');
                                    $('#errorMessage').text(response.message);
                                }
                            }});
                    }else{
                        $('#warning_jenis').show().fadeOut(5000);
                        $('#msg_jenis').text("Silahkan lakukan pengecekan no bpjs dan no rujukan dahulu...!");
                    }
                }else{
                    $('#warning_jenis').show().fadeOut(5000);
                    $('#msg_jenis').text("Silahkan cek kembali data inputan anda...!");

                    if(idDiagnosa == ''){
                        $('#diagnosa_awal').css('border','solid 1px red');
                    }
                    if(noBpjs == ''){
                        $('#no_bpjs').css('border','solid 1px red');
                    }
                    if(noRujukan == ''){
                        $('#no_rujukan').css('border','solid 1px red');
                    }
                    if(noPPK == ''){
                        $('#ppk_rujukan').css('border','solid 1px red');
                    }
                    if(tangalRujukan == ''){
                        $('#tgl_rujukan').css('border','solid 1px red');
                    }
                    if(perujuk == ''){
                        $('#perujuk').css('border','solid 1px red');
                    }
                    if(perujuk == ''){
                        $('#perujuk').css('border','solid 1px red');
                    }
                    if(namaPerujuk == ''){
                        $('#intansi_perujuk').css('border','solid 1px red');
                    }
                    if(fotoSurat == ''){
                        $('#img_surat_rujuk').css('border', 'solid 1px red');
                    }
                }
            }

            if(jenisPeriksa == "umum"){

                if(metodePembayaran != '' && uangMuka != ''){
                    var uang = uangMuka.replace(/[.]/g, '');
                    data = {
                        'metode_pembayaran':metodePembayaran,
                        'uang_muka':uang,
                        'jenis_periksa':jenisPeriksa,
                        'id_pasien':idPasien,
                        'id_detail_checkup':idDetail
                    }

                    var result = JSON.stringify(data);
                    $('#waiting_dialog').dialog('open');
                    dwr.engine.setAsync(true);
                    StatusPeriksaAction.savePerubahanStatus(result, {callback: function (response) {
                            if(response.status == "success"){
                                $('#waiting_dialog').dialog('close');
                                $('#info_dialog').dialog('open');
                            }else{
                                $('#waiting_dialog').dialog('close');
                                $('#error_dialog').dialog('open');
                                $('#errorMessage').text(response.message);
                            }
                        }});
                }else{
                    $('#warning_jenis').show().fadeOut(5000);
                    $('#msg_jenis').text("Silahkan cek kembali data inputan anda...!");
                }
            }

            if(jenisPeriksa == "asuransi"){

                if(asuransi != '' && noKartu != ''){
                    data = {
                        'id_asuransi':asuransi,
                        'no_kartu':noKartu,
                        'cover_biaya':coverBiaya,
                        'jenis_periksa':jenisPeriksa,
                        'id_pasien':idPasien,
                        'id_detail_checkup':idDetail
                    }

                    var result = JSON.stringify(data);
                    $('#waiting_dialog').dialog('open');
                    dwr.engine.setAsync(true);
                    StatusPeriksaAction.savePerubahanStatus(result, {callback: function (response) {
                            if(response.status == "success"){
                                $('#waiting_dialog').dialog('close');
                                $('#info_dialog').dialog('open');
                            }else{
                                $('#waiting_dialog').dialog('close');
                                $('#error_dialog').dialog('open');
                                $('#errorMessage').text(response.message);
                            }
                        }});
                }else{
                    $('#warning_jenis').show().fadeOut(5000);
                    $('#msg_jenis').text("Silahkan cek kembali data inputan anda...!");
                }
            }

        }else{
            $('#warning_jenis').show().fadeOut(5000);
            $('#msg_jenis').text("Silahkan cek kembali data inputan anda...!");
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>