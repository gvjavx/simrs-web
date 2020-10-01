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
        .dropbtn {
            background-color: #4CAF50;
            color: white;
            padding: 16px;
            font-size: 16px;
            border: none;
            cursor: pointer;
        }

        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            z-index: 1;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }
        #line-chart-tooltip{
          z-index: 10000;
        }
        #line-chart{
          width: 100%;
        }
        #line-chart-rm{
            width: 100%;
        }

        .bungkus {
            width: 600px;
            height: 570px;
            max-width: 100%;
            max-height: 100%;
            margin: auto;
            overflow: hidden;
        }

        .carousel {
            position: relative;
            width: 100%;
            height: 0;
            padding-top: 56.25%;
            background: #ddd;
        }

        /* Images */

        .carousel-img {
            position: absolute;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            max-width: 100%;
            -webkit-transition: opacity ease-out 0.5s;
            transition: opacity ease-out 0.5s;
        }

        .carousel-img-displayed {
            display: block;
            opacity: 1;
            z-index: 2;
        }

        .carousel-img-hidden {
            display: block;
            opacity: 0;
            z-index: 1;
        }

        .carousel-img-noDisplay {
            display: none;
        }

        /* Flèches de défilement */

        .carousel-arrow {
            z-index: 3;
            display: block;
            position: absolute;
            width: 36px;
            height: 36px;
            top: 50%;
            margin-top: 80px;
            border-radius: 50%;
            border: 0;
            background-color: #fff;
            background-image: url("http://res.cloudinary.com/dnqehhgmu/image/upload/v1509720334/blue-arrow_jk1ydw.svg");
            background-repeat: no-repeat;
            background-position: center;
            background-size: 16px 16px;
            cursor: pointer;
            -webkit-transition: background-size 0.15s ease-out;
            transition: background-size 0.15s ease-out;
        }

        .carousel-arrow:hover,
        .carousel-arrow:focus {
            background-size: 22px 22px;
        }

        .carousel-arrow-next {
            right: 20px;
        }

        .carousel-arrow-prev {
            left: 20px;
            -webkit-transform: rotateZ(180deg);
            -ms-transform: rotate(180deg);
            transform: rotateZ(180deg);
        }

        .custom02 input[type="radio"] {
            display: none;
        }
        .custom02 label {
            position: relative;
            display: inline-block;
            padding: 3px 3px 3px 20px;
            cursor: pointer;
        }
        .custom02 label::before,
        .custom02 label::after {
            position: absolute;
            content: '';
            top: 50%;
            border-radius: 100%;
            -webkit-transition: all .2s;
            transition: all .2s;
        }
        .custom02 label::before {
            left: 0;
            width: 14px;
            height: 14px;
            margin-top: -8px;
            background: #f3f3f3;
            border: 1px solid #ccc;
        }
        .custom02 label:hover::before {
            background: #fff;
        }
        .custom02 label::after {
            opacity: 0;
            left: 3px;
            width: 8px;
            height: 8px;
            margin-top: -5px;
            background: #3498db;
            -webkit-transform: scale(2);
            transform: scale(2);
        }
        .custom02 input[type="radio"]:checked + label::before {
            background: #fff;
            border: 1px solid #3498db;
        }
        .custom02 input[type="radio"]:checked + label::after {
            opacity: 1;
            -webkit-transform: scale(1);
            transform: scale(1);
        }

        .btn-trans {
            background-color: white;
            width: 200px;
            height: 168px;
            border-radius: 10px;
            opacity: 0.9;
            /*padding-right: 20px;
            padding-left: 20px;*/
            padding: 6px;
            float: left;
            margin: 5px;
            /*border: 1px solid #555;*/
            box-shadow: 5px 10px 18px #555;
            font-size: 12px;
            text-align: center;
            color: #fff;
        }

        .btn-trans:active {
            background-color: #4CAF50;
        }

        .btn-trans:hover {
            background-color: #4CAF50;
        }

    </style>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PasienAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PaketPeriksaAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PelayananAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LabAction.js"/>'></script>

    <script type='text/javascript'>

        function confirm() {

            var noBpjs = $('#no_bpjs').val();
            var idPasien = $('#id_pasien').val();
            var noKtp = $('#no_ktp').val();
            var namaPasien = $('#nama_pasien').val();
            var jenisKelamin = $('#jenis_kelamin').val();
            var tempatLahir = $('#tempat_lahir').val();
            var tglLahir = $('#tanggal_lahir').val();
            var jalan = $('#jalan').val();
            var suku = $('#suku').val();
            var profesi = $('#profesi').val();
            var agama = $('#agama').val();
            var poli = $('#poli').val();
            var dokter = $('#dokter').val();
            var penjamin = $('#penjamin').val();
            var provinsi = $('#provinsi11').val();
            var kota = $('#kabupaten11').val();
            var kecamatan = $('#kecamatan11').val();
            var desa = $('#desa11').val();
            var imgInp = $('#imgInp').val();

            var tipe = '<s:property value="tipe"/>';

            var pembayaran = $('#pembayaran').val();
            var uangMuka = $('#uang_muka').val();

            var diagnosaBpjs = $('#diagnosa_awal').val();
            var perujuk = $('#perujuk').val();
            var ketPerujuk = $('#intansi_perujuk').val();
            var noRujukan = $('#no_rujukan').val();
            var ppkRujukan = $('#ppk_rujukan').val();
            var tglRujukan = $('#tgl_rujukan').val();
            var fotoRujukan = $('#url_do').val();
            var statusBpjs = $('#status_bpjs').val();
            var statusRujukan = $('#status_rujukan').val();

            var isLaka = $('#is_laka').val();
            var asuransi = $('#id_asuransi').val();
            var noKartu = $('#no_kartu').val();
            var coverBiaya = $('#nominal_cover_biaya').val();

            var noKartuPtpn = $('#no_kartu_ptpn').val();
            var unitPtpn = $('#unit_ptpn').val();
            var unitPg = $('#unit_pg').val();
            var cekBpjs = $('#cek_is_bpjs').val();
            var poliUmum = $('#poli_umum').val();

            if (idPasien != '' && noKtp != '' && namaPasien != '' && jenisKelamin != '' && tempatLahir != ''
                && tglLahir != '' && agama != '' && poli != '' && dokter != '' && penjamin != ''
                && provinsi != '' && kota != '' && kecamatan != '' && desa != '') {


                if(tipe == "umum"){
                    if(pembayaran != '' && uangMuka != ''){
                        $('#confirm_dialog').dialog('open');
                    }else{
                        $("html, body").animate({scrollTop: 0}, 600);
                        $('#warning_pasien').show().fadeOut(10000);
                        $('#msg_pasien').text("Silahkan cek kembali data pembayaran...!");
                        if(pembayaran == ''){
                            $('#war_pembayaran').show();
                        }
                        if(uangMuka == ''){
                            $('#war_uang_muka').show();
                        }
                    }
                }

                if (tipe == "bpjs") {
                    if (diagnosaBpjs != '' && perujuk != '' && ketPerujuk != ''
                        && noRujukan != '' && ppkRujukan != '' && tglRujukan != '' && fotoRujukan != '' ) {

                        if(statusBpjs != '' && statusRujukan != ''){

                            if(statusBpjs == "aktif"){
                                // if(statusBpjs == "aktif" && statusRujukan == "aktif"){
                                $('#confirm_dialog').dialog('open');
                            }else{
                                var msg1 = "";
                                var msg2 = "";
                                $("html, body").animate({scrollTop: 0}, 600);
                                $('#warning_pasien').show().fadeOut(10000);
                                if(statusBpjs != "aktif"){
                                    msg1 = "No BPJS Tidak Aktif";
                                }
                                if(statusRujukan != "aktif"){
                                    msg2 = "No Rujukan Tidak Aktif";
                                }
                                $('#msg_pasien').text("Mohon maaf transaksi gagal, dikarenakan "+msg1+". "+msg2+"...!");
                            }
                        }else{
                            $("html, body").animate({scrollTop: 0}, 600);
                            $('#warning_pasien').show().fadeOut(10000);
                            $('#msg_pasien').text("Silahkan klik tombol check untuk melakukan validasi No BPJS dan No Rujukan...!");
                        }
                    } else {
                        $("html, body").animate({scrollTop: 0}, 600);
                        $('#warning_pasien').show().fadeOut(10000);
                        $('#msg_pasien').text("Silahkan cek kembali data diagnosa awal dan data rujukan...!");
                        if(diagnosaBpjs == ''){
                            $('#diagnosa_awal').css('border','solid 1px red');
                        }
                        if(perujuk == ''){
                            $('#war_perujuk').show();
                        }
                        if(ketPerujuk == ''){
                            $('#war_ket_perujuk').show();
                        }
                        if(noRujukan == ''){
                            $('#war_no_rujukan').show();
                        }
                        if(ppkRujukan == ''){
                            $('#war_ppk_rujukan').show();
                        }
                        if(tglRujukan == ''){
                            $('#war_tgl_rujukan').show();
                        }
                        if(fotoRujukan == ''){
                            $('#war_foto_rujukan').show();
                        }
                    }
                }

                if(tipe == "asuransi"){
                    // if(asuransi != '' && coverBiaya != ''){
                    if(asuransi != ''){

                        if(isLaka == "Y"){
                            $('#confirm_dialog').dialog('open');
                        }else{
                            if(noKartu != ''){
                                $('#confirm_dialog').dialog('open');
                            }else{
                                $("html, body").animate({scrollTop: 0}, 600);
                                $('#warning_pasien').show().fadeOut(10000);
                                $('#msg_pasien').text("Silahkan cek kembali data asuransi...!");
                                if(noKartu == ''){
                                    $('#war_no_asuransi').show();
                                }
                            }
                        }
                    }else{
                        $("html, body").animate({scrollTop: 0}, 600);
                        $('#warning_pasien').show().fadeOut(10000);
                        $('#msg_pasien').text("Silahkan cek kembali data asuransi...!");
                        if(asuransi == ''){
                            $('#war_asuransi').show();
                        }
                        if(noKartu == ''){
                            $('#war_no_asuransi').show();
                        }
                        if(coverBiaya == ''){
                            $('#war_jml_cover').show();
                        }
                    }
                }

                if (tipe == "rekanan") {
                    if(noKartuPtpn != '' && unitPtpn != ''){
                        if(cekBpjs == 'Y'){
                            if (diagnosaBpjs != '' && perujuk != '' && ketPerujuk != ''
                                && noRujukan != '' && ppkRujukan != '' && tglRujukan != '' && fotoRujukan != '' ) {

                                if(statusBpjs != '' && statusRujukan != ''){
                                    //&& statusRujukan == "aktif"
                                    if(statusBpjs == "aktif" ){
                                        $('#confirm_dialog').dialog('open');
                                    }else{
                                        var msg1 = "";
                                        var msg2 = "";
                                        $("html, body").animate({scrollTop: 0}, 600);
                                        $('#warning_pasien').show().fadeOut(10000);
                                        if(statusBpjs != "aktif"){
                                            msg1 = "No BPJS Tidak Aktif";
                                        }
                                        if(statusRujukan != "aktif"){
                                            msg2 = "No Rujukan Tidak Aktif";
                                        }
                                        $('#msg_pasien').text("Mohon maaf transaksi gagal, dikarenakan "+msg1+". "+msg2+"...!");
                                    }
                                }else{
                                    $("html, body").animate({scrollTop: 0}, 600);
                                    $('#warning_pasien').show().fadeOut(10000);
                                    $('#msg_pasien').text("Silahkan klik tombol check untuk melakukan validasi No BPJS dan No Rujukan...!");
                                }
                            } else {
                                $("html, body").animate({scrollTop: 0}, 600);
                                $('#warning_pasien').show().fadeOut(10000);
                                $('#msg_pasien').text("Silahkan cek kembali data diagnosa awal dan data rujukan...!");
                                if(diagnosaBpjs == ''){
                                    $('#diagnosa_awal').css('border','solid 1px red');
                                }
                                if(perujuk == ''){
                                    $('#war_perujuk').show();
                                }
                                if(ketPerujuk == ''){
                                    $('#war_ket_perujuk').show();
                                }
                                if(noRujukan == ''){
                                    $('#war_no_rujukan').show();
                                }
                                if(ppkRujukan == ''){
                                    $('#war_ppk_rujukan').show();
                                }
                                if(tglRujukan == ''){
                                    $('#war_tgl_rujukan').show();
                                }
                                if(fotoRujukan == ''){
                                    $('#war_foto_rujukan').show();
                                }
                            }
                        }else{
                            $('#confirm_dialog').dialog('open');
                        }
                    }else{
                        $("html, body").animate({scrollTop: 0}, 600);
                        $('#warning_pasien').show().fadeOut(10000);
                        $('#msg_pasien').text("Silahkan cek kembali data PTPN...!");
                        if(noKartuPtpn == ''){
                            $('#war_no_kartu_ptpn').show();
                        }
                        if(unitPtpn == ''){
                            $('#war_ptpn').show();
                        }
                    }
                }

                if(tipe == "paket_perusahaan" || tipe == "paket_individu"){
                    $('#confirm_dialog').dialog('open');
                }

            } else {

                $("html, body").animate({scrollTop: 0}, 600);
                $('#warning_pasien').show().fadeOut(10000);
                $('#msg_pasien').text("Silahkan cek kembali inputan data pasien...!");

                if (idPasien == '') {
                    $('#id_pasien').css('border', 'red solid 1px');
                }
                if (noKtp == '') {
                    $('#no_ktp').css('border', 'red solid 1px');
                }
                if (namaPasien == '') {
                    $('#nama_pasien').css('border', 'red solid 1px');
                }
                if (jenisKelamin == '') {
                    $('#jenis_kelamin').css('border', 'red solid 1px');
                }
                if (tempatLahir == '') {
                    $('#tempat_lahir').css('border', 'red solid 1px');
                }
                if (tglLahir == '') {
                    $('#st_tgl_lahir').css('border', 'red solid 1px');
                }
                if (agama == '') {
                    $('#agama').css('border', 'red solid 1px');
                }
                if (poli == '') {
                    $('#war_poli').show();
                }
                if (dokter == '' || dokter == null) {
                    $('#war_dokter').show();
                }
                if (penjamin == '') {
                    $('#war_penjamin').show();
                }
                if (provinsi == '') {
                    $('#provinsi').css('border', 'red solid 1px');
                }
                if (kota == '') {
                    $('#kabupaten').css('border', 'red solid 1px');
                }
                if (kecamatan == '') {
                    $('#kecamatan').css('border', 'red solid 1px');
                }
                if (desa == '') {
                    $('#desa').css('border', 'red solid 1px');
                }
                if (imgInp == '') {
                    $('#img_file').css('border', 'red solid 1px');
                }
            }


        }

        $.subscribe('beforeProcessSave', function (event, data) {
            event.originalEvent.options.submit = true;
            $('#confirm_dialog').dialog('close');
            $.publish('showDialog');
        });

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
                $('#no_bpjs, #id_pasien, #no_ktp, #nama_pasien, #jenis_kelamin, #tempat_lahir, #st_tgl_lahir, #jalan, #suku, #profesi, #agama, #poli, #dokter, #penjamin, #img_file, #provinsi, #kabupaten, #kecamatan, #desa').css('border', '');
                resetField();

            }
        });

        $.subscribe('errorDialog', function (event, data) {
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function resetField() {
            $('#no_bpjs, #id_pasien, #no_ktp, #nama_pasien, #jenis_kelamin, #tempat_lahir, #tanggal_lahir, #jalan, #suku, #profesi, #agama, #poli, #dokter, #penjamin, #provinsi11, #kabupaten11, #kecamatan11, #desa11, #provinsi, #kabupaten, #kecamatan, #desa, #nama_penanggung, #no_telp, #hubungan, #kunjungan, #perujuk').val('');
            var img = '<s:url value="/pages/images/ktp-default.jpg"/>';
            $('#img-upload').attr('src', img);
            $('#imgInp').attr('value', '');

        }

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

        function formatRupiah(angka) {
            if(angka != '' && angka != null && angka > 0){
                var reverse = angka.toString().split('').reverse().join(''),
                    ribuan = reverse.match(/\d{1,3}/g);
                ribuan = ribuan.join('.').split('').reverse().join('');
                return ribuan;
            }else{
                return 0;
            }
        }


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
            Tambah Rawat Pasien
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-user-plus"></i> Inputan Data Pasien</h3>
                            </div>
                            <div class="col-md-6">
                                <div class="btn-group dropdown pull-right">
                                    <button type="button" class="btn btn-default"><i class="fa fa-medkit"></i> Riwayat Pasien
                                    </button>
                                    <button type="button" class="btn btn-default dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu pull-left" role="menu" id="riwayat">

                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <s:form id="addCheckupForm" enctype="multipart/form-data" method="post" namespace="/checkup"
                            action="saveAdd_checkup.action" theme="simple">
                        <div class="box-body">
                            <div class="alert alert-danger alert-dismissible" id="warning_pasien" style="display: none">
                                <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                                <p id="msg_pasien"></p>
                            </div>
                            <div id="warn-bpjs"></div>
                            <input type="hidden" id="status_bpjs">
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                                    <%--<button class="btn btn-success pull-right"><i class="fa fa-plus"></i> Pasien Baru</button>--%>
                            </div>
                            <div id="alert-pasien" style="display: none;" class="alert alert-warning alert-dismissible">
                                <button type="button" class="close" onclick="closeAlert()" aria-hidden="true">×</button>
                                <div id="nama-pasien"></div>
                                <div id="tgl-periksa"></div>
                                <div id="tgl_checkup"></div>
                                <input type="hidden" id="date-periksa">
                                <hr>
                                <table style="color: #fff;">
                                    <tr>
                                        <td><strong>Alergi</strong></td>
                                        <td><strong>&nbsp;:&nbsp;</strong></td>
                                        <td>
                                            <div id="alergi"></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><strong>Diagnosa Terakhir</strong></td>
                                        <td><strong>&nbsp;:&nbsp;</strong></td>
                                        <td>
                                            <div id="diagnosa"></div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <s:if test='tipe == "bpjs" || tipe == "rekanan"'>
                                            <div class="form-group" id="form_no_bpjs">
                                                <label class="col-md-4">No BPJS</label>
                                                <div class="col-md-8">
                                                    <div class="input-group">
                                                        <s:textfield id="no_bpjs" name="headerCheckup.noBpjs"
                                                                     cssClass="form-control"/>
                                                        <div class="input-group-btn" onclick="checkBpjs()">
                                                            <a class="btn btn-success">
                                                                <span id="btn-cek"><i
                                                                        class="fa fa-search"></i> Check</span></a>
                                                                <%--<span id="btn-cek"><i class="fa fa-search"></i> </span>--%>
                                                        </div>
                                                    </div>
                                                    <script type="application/javascript">
                                                        var functions, mapped;
                                                        $('#no_bpjs').typeahead({
                                                            minLength: 1,
                                                            source: function (query, process) {
                                                                functions = [];
                                                                mapped = {};

                                                                var data = [];
                                                                dwr.engine.setAsync(false);

                                                                PasienAction.getListComboPasienByBpjs(query, function (listdata) {
                                                                    data = listdata;
                                                                });
                                                                if (data.length != 0) {
                                                                    $.each(data, function (i, item) {
                                                                        var labelItem = "";

                                                                        if (item.noBpjs != '' && item.noBpjs != null) {
                                                                            labelItem = item.idPasien + "-" + item.noBpjs  + "-" + item.nama;
                                                                        } else {
                                                                            labelItem = item.noBpjs + "-" + item.nama;
                                                                        }
                                                                        mapped[labelItem] = {
                                                                            id: item.idPasien,
                                                                            nama: item.nama,
                                                                            ktp: item.noKtp,
                                                                            bpjs: item.noBpjs,
                                                                            tempatlahir: item.tempatLahir,
                                                                            tgllahir: item.tglLahir,
                                                                            alamat: item.jalan,
                                                                            suku: item.suku,
                                                                            profesi: item.profesi,
                                                                            notelp: item.noTelp,
                                                                            urlktp: item.urlKtp,
                                                                            imgKtp: item.imgKtp,
                                                                            sex: item.jenisKelamin,
                                                                            agama: item.agama,
                                                                            idProv: item.provinsiId,
                                                                            idKota: item.kotaId,
                                                                            idKec: item.kecamatanId,
                                                                            idDesa: item.desaId,
                                                                            prov: item.provinsi,
                                                                            kota: item.kota,
                                                                            kec: item.kecamatan,
                                                                            desa: item.desa,
                                                                            isLama: item.isPasienLama
                                                                        };
                                                                        functions.push(labelItem);
                                                                    });
                                                                    process(functions);
                                                                }
//                                                            else{
//                                                                alert("No. BPJS belum terdaftar sebagai pasien");
//                                                                $('#no_bpjs').val("");
//                                                            }
                                                            },
                                                            updater: function (item) {
                                                                var selectedObj = mapped[item];

                                                                alertPasien(selectedObj.id);

                                                                $('#id_pasien').val(selectedObj.id);
                                                                $('#no_ktp').val(selectedObj.ktp);
                                                                $('#nama_pasien').val(selectedObj.nama);
                                                                $('#jenis_kelamin').val(selectedObj.sex);
                                                                $('#tempat_lahir').val(selectedObj.tempatlahir);
                                                                $('#tanggal_lahir').val(selectedObj.tgllahir);
                                                                $('#agama').val(selectedObj.agama);
                                                                $('#profesi').val(selectedObj.profesi);
                                                                $('#jalan').val(selectedObj.alamat);
                                                                $('#suku').val(selectedObj.suku);
                                                                $('#img_ktp').val(selectedObj.imgKtp);
                                                                $('#img-upload').attr('src', selectedObj.urlktp);
                                                                $('#provinsi').val(selectedObj.prov);
                                                                $('#kabupaten').val(selectedObj.kota);
                                                                $('#kecamatan').val(selectedObj.kec);
                                                                $('#desa').val(selectedObj.desa);
                                                                $('#provinsi11').val(selectedObj.idProv);
                                                                $('#kabupaten11').val(selectedObj.idKota);
                                                                $('#kecamatan11').val(selectedObj.idKec);
                                                                $('#desa11').val(selectedObj.idDesa);
                                                                $('#no_telp').val(selectedObj.notelp);
                                                                if (selectedObj.isLama) {
                                                                    $('#kunjungan').val("Lama").attr('disabled', true);
                                                                    $('#kunjungan_val').val("Lama");
                                                                } else {
                                                                    $('#kunjungan').val("Baru").attr('disabled', true);
                                                                    $('#kunjungan_val').val("Baru");
                                                                }
                                                                $('#no_ktp, #nama_pasien, #jenis_kelamin, #tempat_lahir, #st_tgl_lahir, #agama, #provinsi, #kabupaten, #kecamatan, #desa ').css('border', '');
                                                                return selectedObj.bpjs;
                                                            }
                                                        });
                                                    </script>
                                                </div>
                                            </div>
                                        </s:if>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">NO RM</label>
                                            <div class="col-md-8">
                                                <s:textfield id="id_pasien" name="headerCheckup.idPasien"
                                                             onkeypress="$(this).css('border','');"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
                                            </div>
                                            <script>
                                                function tesPasien(val) {
                                                    $('#isi').html('<a href="#">Link 1</a><a href="#">Link 2</a><a href="#">Link 3</a>');
                                                }
                                            </script>
                                            <script type="application/javascript">
                                                var functions, mapped;
                                                var tipe = '<s:property value="tipe"/>';
                                                $('#id_pasien').typeahead({
                                                    minLength: 1,
                                                    source: function (query, process) {
                                                        functions = [];
                                                        mapped = {};

                                                        var data = [];
                                                        dwr.engine.setAsync(false);

                                                        PasienAction.getListComboPasien(query, tipe, function (listdata) {
                                                            data = listdata;
                                                        });

                                                        $.each(data, function (i, item) {
                                                            var labelItem = "";

                                                            if (item.noBpjs != '' && item.noBpjs != null) {
                                                                labelItem = item.idPasien + "-" + item.noBpjs + "-" + item.nama;
                                                            } else {
                                                                labelItem = item.idPasien + "-" + item.nama;
                                                            }
                                                            mapped[labelItem] = {
                                                                id: item.idPasien,
                                                                nama: item.nama,
                                                                ktp: item.noKtp,
                                                                bpjs: item.noBpjs,
                                                                tempatlahir: item.tempatLahir,
                                                                tgllahir: item.tglLahir,
                                                                alamat: item.jalan,
                                                                suku: item.suku,
                                                                profesi: item.profesi,
                                                                notelp: item.noTelp,
                                                                imgKtp: item.imgKtp,
                                                                urlktp: item.urlKtp,
                                                                sex: item.jenisKelamin,
                                                                agama: item.agama,
                                                                noBpjs: item.noBpjs,
                                                                idProv: item.provinsiId,
                                                                idKota: item.kotaId,
                                                                idKec: item.kecamatanId,
                                                                idDesa: item.desaId,
                                                                prov: item.provinsi,
                                                                kota: item.kota,
                                                                kec: item.kecamatan,
                                                                desa: item.desa,
                                                                isLama: item.isPasienLama,
                                                                idPelayanan: item.idPelayanan,
                                                                idPaket: item.idPaket,
                                                                namaPaket: item.namaPaket,
                                                                tarif: item.tarif,
                                                                tglCkp: item.tglCheckup,
                                                                noCkpUlang: item.noCheckuoUlang,
                                                                lastIdDetail: item.idLastDetailCheckup,
                                                                isOrder: item.isOrderLab,
                                                                isCkp: item.isCheckupUlang,
                                                                isPeriksa: item.isDaftar
                                                            };
                                                            functions.push(labelItem);
                                                        });
                                                        process(functions);

                                                    },
                                                    updater: function (item) {
                                                        var selectedObj = mapped[item];
                                                        alertPasien(selectedObj.id);
                                                        getRiwayatPemeriksaan(selectedObj.id);
                                                        $('#no_bpjs').val(selectedObj.noBpjs);
                                                        $('#no_ktp').val(selectedObj.ktp);
                                                        $('#nama_pasien').val(selectedObj.nama);
                                                        $('#jenis_kelamin').val(selectedObj.sex);
                                                        $('#tempat_lahir').val(selectedObj.tempatlahir);
                                                        $('#tanggal_lahir').val(selectedObj.tgllahir);
                                                        $('#agama').val(selectedObj.agama);
                                                        $('#profesi').val(selectedObj.profesi);
                                                        $('#jalan').val(selectedObj.alamat);
                                                        $('#suku').val(selectedObj.suku);
                                                        $('#img_ktp').val(selectedObj.imgKtp);
                                                        $('#img-upload').attr('src', selectedObj.urlktp);
                                                        $('#provinsi').val(selectedObj.prov);
                                                        $('#kabupaten').val(selectedObj.kota);
                                                        $('#kecamatan').val(selectedObj.kec);
                                                        $('#desa').val(selectedObj.desa);
                                                        $('#provinsi11').val(selectedObj.idProv);
                                                        $('#kabupaten11').val(selectedObj.idKota);
                                                        $('#kecamatan11').val(selectedObj.idKec);
                                                        $('#desa11').val(selectedObj.idDesa);
                                                        $('#no_telp').val(selectedObj.notelp);
                                                        if (selectedObj.isLama) {
                                                            $('#kunjungan').val("Lama").attr('disabled', true);
                                                            $('#kunjungan_val').val("Lama");
                                                        } else {
                                                            $('#kunjungan').val("Baru").attr('disabled', true);
                                                            $('#kunjungan_val').val("Baru");
                                                        }
                                                        var tipe = '<s:property value="tipe"/>';
                                                        if("paket_perusahaan" == tipe || "paket_individu" == tipe){
                                                            if(selectedObj.idPelayanan != null){
                                                                $('#poli_paket').val(selectedObj.idPelayanan).trigger('change').attr('disabled', true);
                                                                $('#id_pelayanan_paket').val(selectedObj.idPelayanan);
                                                            }
                                                        }else{
                                                            if("Y" == selectedObj.isCkp){
                                                                $('#poli').val(selectedObj.idPelayanan).trigger('change').attr('disabled', true);
                                                                $('#id_pelayanan_poli').val(selectedObj.idPelayanan);
                                                            }else{
                                                                $('#poli').val('').trigger('change').attr('disabled', false);
                                                                $('#id_pelayanan_poli').val('');
                                                            }

                                                            $('#last_id_detail_checkup').val(selectedObj.lastIdDetail);
                                                            $('#is_order_lab').val(selectedObj.isOrder);
                                                            if(selectedObj.tglCkp != null){
                                                                $('#tgl_checkup').html("Tanggal Checkup Ulang : <b>"+selectedObj.tglCkp+"</b>");
                                                            }else{
                                                                $('#tgl_checkup').html("");
                                                            }
                                                        }

                                                        if(selectedObj.idPaket != null && selectedObj.idPaket != ''){
                                                            // $('#paket').val(selectedObj.idPaket).trigger('change').attr('disabled',true);
                                                            $('#id_paket').val(selectedObj.idPaket);
                                                            $('#paket_perusahaan').val(selectedObj.namaPaket);
                                                            $('#cover_biaya_paket').val(selectedObj.tarif);
                                                        }

                                                        if(selectedObj.isPeriksa == "Y"){
                                                            $('#btn-save').hide();
                                                            $('#warning_pasien').show();
                                                            $('#msg_pasien').text("Pasien Sudah melakukan pendafataran...!");
                                                        }else{
                                                            $('#btn-save').show();
                                                            $('#warning_pasien').hide();
                                                            $('#msg_pasien').text("");
                                                            alertObatKronis(selectedObj.id);
                                                        }

                                                        $('#no_ktp, #nama_pasien, #jenis_kelamin, #tempat_lahir, #st_tgl_lahir, #agama, #provinsi, #kabupaten, #kecamatan, #desa ').css('border', '');
                                                        return selectedObj.id;
                                                    }
                                                });
                                            </script>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">NIK Pasien</label>
                                            <div class="col-md-8">
                                                <s:textfield id="no_ktp" name="headerCheckup.noKtp"
                                                             onkeypress="$(this).css('border','')"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"
                                                             />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Nama Pasien</label>
                                            <div class="col-md-8">
                                                <s:textfield id="nama_pasien" name="headerCheckup.nama"
                                                             onkeypress="$(this).css('border','')"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Jenis Kelamin</label>
                                            <div class="col-md-8">
                                                <s:select list="#{'L':'Laki-Laki','P':'Perempuan'}"
                                                          cssStyle="margin-top: 7px" onchange="$(this).css('border','')"
                                                          id="jenis_kelamin" name="headerCheckup.jenisKelamin"
                                                          headerKey="" headerValue="[Select one]"
                                                          cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Tempat Lahir</label>
                                            <div class="col-md-8">
                                                <s:textfield id="tempat_lahir" name="headerCheckup.tempatLahir"
                                                             onkeypress="$(this).css('border','')"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
                                            </div>
                                        </div>

                                        <s:hidden name="headerCheckup.isOnline" id="tes_online"/>
                                        <s:hidden name="headerCheckup.tglAntian"/>

                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Tanggal Lahir</label>
                                            <div class="col-md-8">
                                                <div class="input-group date" style="margin-top: 7px" id="st_tgl_lahir">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <s:textfield id="tanggal_lahir" name="headerCheckup.stTglLahir"
                                                                 cssClass="form-control datemask"
                                                                 onchange="$('#st_tgl_lahir').css('border','')"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Agama</label>
                                            <div class="col-md-8">
                                                <s:select id="agama" name="headerCheckup.agama"
                                                          list="#{'Islam':'Islam','Kristen':'Kristen','Katolik':'Katolik','Hindu':'Hindu','Buddha':'Buddha','Konghucu':'Konghucu'}"
                                                          onchange="$(this).css('border','')"
                                                          headerKey="" headerValue="[Select One]"
                                                          cssStyle="margin-top: 7px" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Profesi</label>
                                            <div class="col-md-8">
                                                <s:textfield id="profesi" name="headerCheckup.profesi"
                                                             onkeypress="$(this).css('border','')"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Suku</label>
                                            <div class="col-md-8">
                                                <s:textfield id="suku" name="headerCheckup.suku"
                                                             onkeypress="$(this).css('border','')"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Alamat</label>
                                            <div class="col-md-8">
                                                <s:textarea id="jalan" rows="3" cssStyle="margin-top: 7px"
                                                            onkeypress="$(this).css('border','')"
                                                            name="headerCheckup.jalan" cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4">Provinsi</label>
                                            <div class="col-md-8">
                                                <s:textfield cssStyle="margin-top: 7px" id="provinsi" name="headerCheckup.namaProvinsi"
                                                             required="true" disabled="false"
                                                             onkeypress="$(this).css('border','')"
                                                             cssClass="form-control"/>
                                                <s:textfield cssStyle="display: none; margin-top: 7px" id="provinsi11"
                                                             name="headerCheckup.provinsiId" required="true"
                                                             disabled="false" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Kota</label>
                                            <div class="col-md-8">
                                                <s:textfield cssStyle="margin-top: 7px" id="kabupaten" name="headerCheckup.namaKota"
                                                             required="true" disabled="false"
                                                             onkeypress="$(this).css('border','')"
                                                             cssClass="form-control"/>
                                                <s:textfield cssStyle="display: none; margin-top: 7px" id="kabupaten11"
                                                             name="headerCheckup.kotaId" required="true"
                                                             disabled="false" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Kecamatan</label>
                                            <div class="col-md-8">
                                                <s:textfield cssStyle="margin-top: 7px" id="kecamatan" name="headerCheckup.namaKecamatan"
                                                             required="true" disabled="false"
                                                             onkeypress="$(this).css('border','')"
                                                             cssClass="form-control"/>
                                                <s:textfield cssStyle="display: none; margin-top: 7px" id="kecamatan11"
                                                             name="headerCheckup.kecamatanId" required="true"
                                                             disabled="false" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Kelurahan/Desa</label>
                                            <div class="col-md-8">
                                                <s:textfield cssStyle="margin-top: 7px" id="desa" name="headerCheckup.namaDesa"
                                                             required="true" disabled="false"
                                                             onkeypress="$(this).css('border','')"
                                                             cssClass="form-control"/>
                                                <s:textfield cssStyle="display: none; margin-top: 7px" id="desa11"
                                                             name="headerCheckup.desaId" required="true"
                                                             disabled="false" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Foto Identitas</label>
                                            <div class="col-md-8">
                                                <img id="img-upload" width="100%"
                                                     src="<s:url value="/pages/images/ktp-default.jpg"/>"
                                                     style="border: darkgray solid 1px; height: 170px; margin-top: 7px"/>
                                                <s:hidden name="headerCheckup.urlKtp" id="img_ktp"></s:hidden>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Diagnosa Awal</label>
                                            <div class="col-md-8">
                                                        <s:textfield id="diagnosa_awal" style="margin-top: 7px"
                                                                     name="headerCheckup.diagnosa" autocomplete="off"
                                                                     onkeypress="$(this).css('border','')"
                                                                     cssClass="form-control" required="false"/>
                                                        <s:hidden name="headerCheckup.jenisTransaksi"/>
                                                        <script>
                                                            var menus, mapped;
                                                            $('#diagnosa_awal').typeahead({
                                                                minLength: 3,
                                                                source: function (query, process) {
                                                                    menus = [];
                                                                    mapped = {};

                                                                    var data = [];
                                                                    dwr.engine.setAsync(false);
                                                                    CheckupAction.getICD10(query, function (listdata) {
                                                                        data = listdata;
                                                                    });

                                                                    $.each(data, function (i, item) {
                                                                        var labelItem = item.idDiagnosa +'-'+item.descOfDiagnosa;
                                                                        mapped[labelItem] = {
                                                                            id: item.idDiagnosa,
                                                                            label: labelItem,
                                                                            name: item.descOfDiagnosa
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
                                                        </script>
                                                        <s:textarea rows="4" id="diagnosa_ket"
                                                                    cssStyle="margin-top: 7px" readonly="true"
                                                                    name="headerCheckup.namaDiagnosa"
                                                                    cssClass="form-control"></s:textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Data Penanggung Jawab</h3>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4">Nama Penanggung</label>
                                            <div class="col-md-8">
                                                <s:textfield id="nama_penanggung" name="headerCheckup.namaPenanggung"
                                                             cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">No Telp.</label>
                                            <div class="col-md-8">
                                                <s:textfield id="no_telp" name="headerCheckup.noTelp"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Hubungan</label>
                                            <div class="col-md-8">
                                                <s:select
                                                        list="#{'Ayah':'Ayah','Ibu':'Ibu','Kakak':'Kakak','Adik':'Adik','Sepupu':'Sepupu','Ipar':'Ipar'}"
                                                        cssStyle="margin-top: 7px"
                                                        id="hubungan" name="headerCheckup.hubunganKeluarga"
                                                        headerKey="" headerValue="[Select one]"
                                                        cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <s:hidden name="headerCheckup.isOrderLab" id="is_order_lab"></s:hidden>
                            <s:hidden name="headerCheckup.lastIdDetailCheckup" id="last_id_detail_checkup"></s:hidden>

                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Data Kunjungan</h3>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <s:if test='tipe == "paket_perusahaan" || tipe == "paket_individu"'>
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 10px">Pelayanan</label>
                                                <div class="col-md-8">
                                                    <s:action id="initComboPoli1" namespace="/checkup"
                                                              name="getComboPelayanan_checkup"/>
                                                    <s:select cssStyle="margin-top: 7px; width: 100%"
                                                              list="#initComboPoli1.listOfPelayanan"
                                                              listKey="idPelayanan" id="poli_paket"
                                                              listValue="namaPelayanan"
                                                              onchange="$(this).css('border',''); listDokter(this.value); var warn =$('#war_poli').is(':visible'); if (warn){$('#cor_poli').show().fadeOut(3000);$('#war_poli').hide()}"
                                                              headerKey="" headerValue="[Select one]"
                                                              cssClass="form-control select2"/>
                                                    <span style="color: red; display: none" id="war_poli"><i
                                                            class="fa fa-times"></i> required</span>
                                                    <span style="color: green; display: none" id="con_poli"><i
                                                            class="fa fa-check"></i> correct</span>
                                                </div>
                                            </div>
                                            <s:hidden name="headerCheckup.idPelayanan" id="id_pelayanan_paket"></s:hidden>
                                        </s:if>
                                        <s:else>
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 10px">Pelayanan</label>
                                                <div class="col-md-8">
                                                    <s:action id="initComboPoli3" namespace="/checkup"
                                                              name="getComboPelayananWithLab_checkup"/>
                                                    <s:select cssStyle="margin-top: 7px; width: 100%"
                                                              list="#initComboPoli3.listOfPelayananWithLab" id="poli"
                                                              listKey="idPelayanan"
                                                              listValue="namaPelayanan"
                                                              onchange="$(this).css('border',''); listDokter(this.value); var warn =$('#war_poli').is(':visible'); if (warn){$('#cor_poli').show().fadeOut(3000);$('#war_poli').hide()}"
                                                              headerKey="" headerValue="[Select one]"
                                                              cssClass="form-control select2"/>
                                                    <span style="color: red; display: none" id="war_poli"><i
                                                            class="fa fa-times"></i> required</span>
                                                    <span style="color: green; display: none" id="con_poli"><i
                                                            class="fa fa-check"></i> correct</span>
                                                </div>
                                            </div>
                                            <s:hidden name="headerCheckup.idPelayanan" id="id_pelayanan_poli"></s:hidden>
                                        </s:else>
                                        <div class="form-group" id="form-lab" style="display: none">
                                            <label class="col-md-4" style="margin-top: 10px">Unit Pemeriksaan</label>
                                            <div class="col-md-8">
                                                <select id="id_lab" class="form-control select2"
                                                        name="headerCheckup.idLab"
                                                        style="margin-top: 7px; width: 100%"
                                                        onchange="var warn =$('#war_lab').is(':visible'); if (warn){$('#con_lab').show().fadeOut(3000);$('#war_lab').hide()}">
                                                    <option value=''>[Select One]</option>
                                                </select>
                                                <span style="color: red; display: none" id="war_lab"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_lab"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 10px">Dokter</label>
                                            <div class="col-md-8">
                                                <%--<select id="dokter" class="form-control select2"--%>
                                                        <%--name="headerCheckup.idDokter"--%>
                                                        <%--style="margin-top: 7px; width: 100%"--%>
                                                        <%--onchange="var warn =$('#war_dokter').is(':visible'); if (warn){$('#con_dokter').show().fadeOut(3000);$('#war_dokter').hide()}">--%>
                                                    <%--<option value=''>[Select One]</option>--%>
                                                <%--</select>--%>
                                                    <div class="input-group" style="margin-top: 7px;">
                                                        <input readonly class="form-control" id="nama_dokter" style="cursor: pointer" placeholder="*klik untuk mencari jadwal dokter">
                                                        <div class="input-group-btn">
                                                            <a class="btn btn-success">
                                                                <span id="btn-dokter"><i
                                                                        class="fa fa-search"></i> Dokter</span></a>
                                                        </div>
                                                    </div>
                                                    <s:hidden name="headerCheckup.idDokter" id="dokter"></s:hidden>
                                                <span style="color: red; display: none" id="war_dokter"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_dokter"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 10px">Jenis Pasien</label>
                                            <div class="col-md-8">
                                                <select id="penjamin"
                                                        class="form-control select2"
                                                        style="width: 100%"
                                                        onchange="var warn =$('#war_penjamin').is(':visible'); if (warn){$('#con_penjamin').show().fadeOut(3000);$('#war_penjamin').hide()}">
                                                    <option value="">[Select One]</option>
                                                </select>
                                                <span style="color: red; display: none" id="war_penjamin"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_penjamin"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>

                                        <s:hidden name="headerCheckup.idJenisPeriksaPasien" id="id_jenis_periksa"></s:hidden>

                                        <s:if test='tipe == "paket_individu"'>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 10px">Paket</label>
                                            <div class="col-md-8">
                                                <select id="paket"
                                                        class="form-control select2"
                                                        style="width: 100%"
                                                        onchange="var warn =$('#war_paket').is(':visible'); if (warn){$('#con_paket').show().fadeOut(3000);$('#war_paket').hide()}; selectPelayanan(this.value)">
                                                    <option value="">[Select One]</option>
                                                </select>
                                                <span style="color: red; display: none" id="warpaket"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_paket"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>
                                        </s:if>

                                        <s:if test='tipe == "paket_perusahaan"'>
                                            <div class="form-group">
                                                <label class="col-md-4" style="margin-top: 10px">Paket</label>
                                                <div class="col-md-8">
                                                    <input style="margin-top: 7px" class="form-control" id="paket_perusahaan" readonly>
                                                </div>
                                            </div>
                                        </s:if>

                                        <s:if test='tipe == "paket_perusahaan" || tipe == "paket_individu"'>
                                            <s:hidden name="headerCheckup.idPaket" id="id_paket"></s:hidden>
                                            <s:hidden name="headerCheckup.coverBiaya" id="cover_biaya_paket"></s:hidden>
                                        </s:if>

                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 10px">Kunjungan</label>
                                            <div class="col-md-8">
                                                <s:select cssStyle="margin-top: 7px" list="#{'Lama':'Lama','Baru':'Baru'}"
                                                          onchange="$(this).css('border','')"
                                                          id="kunjungan"
                                                          headerKey="" headerValue="[Select one]"
                                                          cssClass="form-control"/>
                                                <s:hidden name="headerCheckup.jenisKunjungan"
                                                          id="kunjungan_val"></s:hidden>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <s:if test='tipe == "asuransi"'>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Data Asuransi</h3>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 10px">Asuransi</label>
                                            <div class="col-md-8">
                                                <select id="asuransi"
                                                        class="form-control select2"
                                                        style="width: 100%"
                                                        onchange="var warn =$('#war_asuransi').is(':visible'); if (warn){$('#con_asuransi').show().fadeOut(3000);$('#war_asuransi').hide()}; showLaka(this.value)">
                                                    <option value="">[Select One]</option>
                                                </select>
                                                <span style="color: red; display: none" id="war_asuransi"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_asuransi"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                            <s:hidden name="headerCheckup.idAsuransi" id="id_asuransi"></s:hidden>
                                            <s:hidden id="is_laka"></s:hidden>
                                        </div>
                                        <div class="form-group" id="form_no_kartu">
                                            <label class="col-md-4" style="margin-top: 10px">No Kartu</label>
                                            <div class="col-md-8">
                                                <s:textfield autocomplete="off" id="no_kartu" name="headerCheckup.noKartuAsuransi" cssStyle="margin-top: 7px" cssClass="form-control" onkeypress="var warn =$('#war_no_asuransi').is(':visible'); if (warn){$('#con_no_asuransi').show().fadeOut(3000);$('#war_no_asuransi').hide()}"></s:textfield>
                                                <span style="color: red; display: none" id="war_no_asuransi"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_no_asuransi"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>
                                        <div class="form-group" id="form_jasaraharja_1" style="display: none">
                                            <label class="col-md-4" style="margin-top: 10px">No Polisi</label>
                                            <div class="col-md-8">
                                                <s:textfield name="headerCheckup.noRujukan" cssStyle="margin-top: 7px" cssClass="form-control" onkeypress="var warn =$('#war_no_asuransi').is(':visible'); if (warn){$('#con_no_asuransi').show().fadeOut(3000);$('#war_no_asuransi').hide()}"></s:textfield>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <s:hidden name="headerCheckup.coverBiaya" id="cover_biaya"></s:hidden>
                                        <div id="form_jasaraharja_2" style="display: none">
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 10px">Tanggal Kejadian</label>
                                            <div class="col-md-8">
                                                <s:textfield name="headerCheckup.tglRujukan" cssStyle="margin-top: 7px" cssClass="form-control datepicker" onkeypress="var warn =$('#war_no_asuransi').is(':visible'); if (warn){$('#con_no_asuransi').show().fadeOut(3000);$('#war_no_asuransi').hide()}"></s:textfield>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 10px">Foro Surat Polisi</label>
                                            <div class="col-md-8">
                                                <div class="input-group" style="margin-top: 7px">
                                                    <span class="input-group-btn">
                                                        <span class="btn btn-default btn-file">
                                                            Browse… <s:file accept=".jpg" name="fileUploadDocPolisi"></s:file>
                                                        </span>
                                                    </span>
                                                    <input type="text" class="form-control" readonly>
                                                </div>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </s:if>

                            <s:if test='tipe == "rekanan"'>
                            <s:hidden name="headerCheckup.idAsuransi" id="id_rekanan"></s:hidden>
                                <s:hidden name="headerCheckup.isRekananWithBpjs" id="cek_is_bpjs"></s:hidden>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Data Rekanan</h3>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 10px">Nama Rekanan</label>

                                            <div class="col-md-8">
                                                <select id="unit_ptpn" class="form-control select2"
                                                        onchange="var warn =$('#war_ptpn').is(':visible'); if (warn){$('#con_ptpn').show().fadeOut(3000);$('#war_ptpn').hide()}; cekPtpn(this.value)">
                                                </select>
                                                <span style="color: red; display: none" id="war_ptpn"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_ptpn"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>
                                        <div class="form-group" id="form_pg" style="display: none">
                                            <label class="col-md-4" style="margin-top: 10px">PG Unit</label>
                                            <div class="col-md-8">
                                                <input class="form-control" id="unit_pg" style="margin-top: 7px">
                                                <%--<select id="unit_pg" class="form-control select2"--%>
                                                        <%--onchange="var warn =$('#war_pg').is(':visible'); if (warn){$('#con_pg').show().fadeOut(3000);$('#war_pg').hide()}">--%>
                                                <%--</select>--%>
                                                <span style="color: red; display: none" id="war_pg"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_pg"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 10px">No Kartu Rekanan</label>
                                            <div class="col-md-8">
                                                <s:textfield id="no_kartu_ptpn" name="headerCheckup.noKartuAsuransi"
                                                             cssStyle="margin-top: 7px" cssClass="form-control"
                                                onkeypress="var warn =$('#war_no_kartu_ptpn').is(':visible'); if (warn){$('#con_no_kartu_ptpn').show().fadeOut(3000);$('#war_no_kartu_ptpn').hide()}"></s:textfield>
                                                <span style="color: red; display: none" id="war_no_kartu_ptpn"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_no_kartu_ptpn"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </s:if>

                            <s:if test='tipe == "bpjs" || tipe == "rekanan"'>
                                <div id="form_rujukan">
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Data Rujukan</h3>
                            </div>
                            <div class="box-body">
                                <div id="warn_rujukan"></div>
                                <input type="hidden" id="status_rujukan">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Perujuk/Asal</label>
                                            <div class="col-md-8">
                                                <s:select list="#{'2':'PPK 2 - RS Lain'}"
                                                        cssStyle="margin-top: 7px"
                                                        name="headerCheckup.rujuk"
                                                        onchange="changePlaceHolder(this); var warn =$('#war_perujuk').is(':visible'); if (warn){$('#con_perujuk').show().fadeOut(3000);$('#war_perujuk').hide()}"
                                                        id="perujuk"
                                                        headerKey="1" headerValue="PPK 1 - Puskesmas"
                                                        cssClass="form-control"/>
                                                <span style="color: red; display: none" id="war_perujuk"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_perujuk"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Keterangan Perujuk</label>
                                            <div class="col-md-8">
                                                <s:textfield id="intansi_perujuk" name="headerCheckup.ketPerujuk"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"
                                                onkeypress="var warn =$('#war_ket_perujuk').is(':visible'); if (warn){$('#con_ket_perujuk').show().fadeOut(3000);$('#war_ket_perujuk').hide()}"/>
                                                <span style="color: red; display: none" id="war_ket_perujuk"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_ket_perujuk"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">No Rujukan</label>
                                            <div class="col-md-8">
                                                <div class="input-group" style="margin-top: 7px">
                                                    <s:textfield id="no_rujukan" cssClass="form-control"
                                                                 name="headerCheckup.noRujukan"
                                                    onkeypress="var warn =$('#war_no_rujukan').is(':visible'); if (warn){$('#con_no_rujukan').show().fadeOut(3000);$('#war_no_rujukan').hide()}"></s:textfield>
                                                    <div class="input-group-btn">
                                                        <a class="btn btn-success" onclick="cekNoRujukan()">
                                                            <span id="btn-cek-rujukan"><i class="fa fa-search"></i> Check</span></a>
                                                    </div>
                                                </div>
                                                <span style="color: red; display: none" id="war_no_rujukan"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_no_rujukan"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">No PPK Rujukan</label>
                                            <div class="col-md-8">
                                                <s:textfield name="headerCheckup.noPpkRujukan" id="ppk_rujukan"
                                                             onkeypress="var warn =$('#war_ppk_rujukan').is(':visible'); if (warn){$('#con_ppk_rujukan').show().fadeOut(3000);$('#war_ppk_rujukan').hide()}"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
                                                <span style="color: red; display: none" id="war_ppk_rujukan"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_ppk_rujukan"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Tanggal Rujukan</label>
                                            <div class="col-md-8">
                                                <div class="input-group date" style="margin-top: 7px">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <s:textfield name="headerCheckup.tglRujukan" id="tgl_rujukan"
                                                                 cssClass="form-control datepicker datemask"
                                                                 onchange="var warn =$('#war_tgl_rujukan').is(':visible'); if (warn){$('#con_tgl_rujukan').show().fadeOut(3000);$('#war_tgl_rujukan').hide()}"
                                                                 />
                                                </div>
                                                <span style="color: red; display: none" id="war_tgl_rujukan"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_tgl_rujukan"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Foto Surat Rujuk</label>
                                            <div class="col-md-8">
                                                <div class="input-group" style="margin-top: 7px" id="img_url">
                                                    <span class="input-group-btn">
                                                        <span class="btn btn-default btn-file">
                                                            Browse… <s:file id="url_do" accept=".jpg" name="fileUploadDoc"></s:file>
                                                        </span>
                                                    </span>
                                                    <input type="text" class="form-control" readonly>
                                                </div>
                                                <span style="color: red; display: none" id="war_foto_rujukan"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_foto_rujukan"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                                </div>
                            </s:if>

                            <%--from bpjs--%>
                            <s:hidden name="headerCheckup.kelasPasien" id="kelas_pasien"></s:hidden>
                            <s:hidden name="headerCheckup.noMr" id="no_mr"></s:hidden>
                            <s:hidden name="headerCheckup.idPelayananBpjs" id="idPelayananBpjs"></s:hidden>
                            <s:hidden name="headerCheckup.noCheckupOnline"></s:hidden>

                            <s:if test='tipe == "umum"'>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-money"></i> Pembayaran</h3>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 10px">Metode pembayaran</label>
                                            <div class="col-md-8">
                                                <s:select
                                                        list="#{'tunai':'Tunai','non_tunai':'Non Tunai'}"
                                                        cssStyle="margin-top: 7px"
                                                        id="pembayaran"
                                                        onchange="var warn =$('#war_pembayaran').is(':visible'); if (warn){$('#con_pembayaran').show().fadeOut(3000);$('#war_pembayaran').hide()}"
                                                        name="headerCheckup.metodePembayaran"
                                                        headerKey="" headerValue="[Select one]"
                                                        cssClass="form-control"/>
                                                <span style="color: red; display: none" id="war_pembayaran"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_pembayaran"><i
                                                        class="fa fa-check"></i> correct</span>
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
                                                    <s:hidden name="headerCheckup.uangMuka" id="uang_muka_val"></s:hidden>
                                                    <s:textfield type="text" id="uang_muka" cssClass="form-control"
                                                                 onkeypress="var warn =$('#war_uang_muka').is(':visible'); if (warn){$('#con_uang_muka').show().fadeOut(3000);$('#war_uang_muka').hide()}"/>
                                                </div>
                                                <span style="color: red; display: none" id="war_uang_muka"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_uang_muka"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </s:if>
                            <div class="box-header with-border" id="pos_kronis"></div>
                            <div class="alert alert-warning alert-dismissible" id="warning_kronis" style="display: none">
                                <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                                <p id="msg_kronis"></p>
                            </div>
                            <div class="alert alert-danger alert-dismissible" id="warning_cetak" style="display: none">
                                <h4><i class="icon fa fa-warning"></i> Warning!</h4>
                                <p id="msg_cetak"></p>
                            </div>
                            <div class="alert alert-success alert-dismissible" id="success_kronis" style="display: none">
                                <h4><i class="icon fa fa-info"></i> Info!</h4>
                                <p id="msg_kronis2"></p>
                            </div>
                            <%--<div class="box-header with-border"></div>--%>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group" style="display: inline;">
                                            <div class="col-md-offset-2 col-md-8 text-center" style="margin-top: 7px">
                                                <button type="button" id="btn-save" class="btn btn-success" onclick="confirm()"><i
                                                        class="fa fa-check"></i> Save
                                                </button>

                                                <%--<a type="button" id="btn-rm" style="display:none;"--%>
                                                   <%--class="btn btn-primary"--%>
                                                   <%--onclick="initRekamMedic()">--%>
                                                    <%--<i class="fa fa-search"></i> View Rekam Medik--%>
                                                <%--</a>--%>

                                                <a type="button" id="btn-kronis" style="display:none;"
                                                   class="btn btn-info"
                                                   onclick="initKronis()">
                                                    <i class="fa fa-medkit"></i> Obat Kronis
                                                </a>

                                                <s:if test='tipe == "bpjs"'>
                                                    <div class="btn-group dropup">
                                                        <button type="button" class="btn btn-info"><i class="fa fa-print"></i> Print Gagal SEP
                                                        </button>
                                                        <button type="button" class="btn btn-info dropdown-toggle"
                                                                data-toggle="dropdown" style="height: 34px">
                                                            <span class="caret"></span>
                                                            <span class="sr-only">Toggle Dropdown</span>
                                                        </button>
                                                        <ul class="dropdown-menu" role="menu">
                                                            <li><a style="cursor: pointer" onclick="printGagalSEP('SP10')"><i class="fa fa-print"></i> Non Aktif</a></li>
                                                            <li><a style="cursor: pointer" onclick="printGagalSEP('SP11')"><i class="fa fa-print"></i> Kartu Tidak Dibawak</a></li>
                                                            <li><a style="cursor: pointer" onclick="printGagalSEP('SP12')"><i class="fa fa-print"></i> Kemauan Sendiri</a></li>
                                                            <li><a style="cursor: pointer" onclick="printGagalSEP('SP13')"><i class="fa fa-print"></i> Masa Pengurusan Denda</a></li>
                                                            <li><a style="cursor: pointer" onclick="printGagalSEP('SP14')"><i class="fa fa-print"></i> Penangguhan Pasien</a></li>
                                                        </ul>
                                                    </div>
                                                </s:if>
                                                <button type="button" class="btn btn-danger"
                                                        onclick="window.location.reload(true)">
                                                    <i class="fa fa-refresh"></i> Reset
                                                </button>
                                                <a type="button" class="btn btn-warning" href="initForm_checkup.action">
                                                    <i class="fa fa-arrow-left"></i> Back
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none">
                                    <sj:dialog id="confirm_dialog" modal="true" resizable="false" closeOnEscape="false"
                                               height="200" width="400" autoOpen="false" title="Confirmation Dialog">
                                        <center><img border="0 " style="height: 40px; width: 40px"
                                                     src="<s:url value="/pages/images/icon_warning.ico"/>"
                                                     name="icon_success">
                                            Do you want to save this record?
                                        </center>
                                        <br>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-warning"
                                                    onclick="$('#confirm_dialog').dialog('close')"><i
                                                    class="fa fa-times"></i> No
                                            </button>
                                            <sj:submit targets="crud" type="button" cssClass="btn btn-success"
                                                       formIds="addCheckupForm" id="save" name="save"
                                                       onBeforeTopics="beforeProcessSave"
                                                       onCompleteTopics="closeDialog,successDialog"
                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog">
                                                <i class="fa fa-arrow-right"></i>
                                                yes
                                            </sj:submit>
                                        </div>
                                    </sj:dialog>

                                    <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog"
                                               modal="true"
                                               resizable="false"
                                               height="250" width="600" autoOpen="false" title="Saving ...">
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
                                               resizable="false" closeOnEscape="false"
                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                               buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         window.location.href = 'initForm_checkup.action';
                                                                                     }
                                                                            }"
                                    >
                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                             name="icon_success">
                                        Record has been saved successfully.
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

                        </div>
                    </s:form>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-rekam-medic">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Rekam Medik Pasien <span id="nama_medik"></span></h4>
            </div>
            <div class="modal-body">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li><a href="#medik_baru" data-toggle="tab" onclick="getByTypeRekamMedic('baru')">Rekam Medik Baru</a></li>
                            <li><a href="#medik_lama" data-toggle="tab" onclick="getByTypeRekamMedic('lama')">Rekam Medik Lama</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="medik_baru">
                                <table class="table table-striped table-bordered">
                                <thead id="label-rekam-medic-baru">
                                </thead>
                                <tbody id="body-rekam-medic-baru">
                                </tbody>
                                </table>
                            </div>
                            <div class="tab-pane" id="medik_lama">
                                <div class="row">
                                    <div class="col-md-offset-2 col-md-8">
                                        <table class="table table-striped table-bordered">
                                            <thead id="label-rekam-medic-lama">
                                            </thead>
                                            <tbody id="body-rekam-medic-lama">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.tab-content -->
                    </div>
                    <%--<button class="btn btn-primary" onclick="getByTypeRekamMedic('baru')"><i class="fa fa-search"></i> Rekam Medic Baru</button>--%>
                    <%--<button class="btn btn-primary" onclick="getByTypeRekamMedic('lama')"><i class="fa fa-search"></i> Rekam Medic Lama</button>--%>
                    <%--<table class="table table-striped table-bordered" id="tabel_rese_detail">--%>
                        <%--<thead id="label-rekam-medic">--%>
                        <%--</thead>--%>
                        <%--<tbody id="body-rekam-medic">--%>
                        <%--</tbody>--%>
                    <%--</table>--%>
                </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-admisi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Form Admisi Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_pre">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_pre"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-7">Adakah hal yang berkaitan dengan keyakinan anda yang perlu kami
                            ketahui ?</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input type="radio" value="Ada" id="pre01-01" name="pre01" /><label for="pre01-01">Ada</label>
                                <input type="radio" value="Tidak Ada" id="pre01-02" name="pre01" /><label for="pre01-02">Tidak Ada</label>
                            </div>
                            <textarea rows="2" style="display: none" class="form-control" id="pre_keyakinan"></textarea>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-7">Apakah anda membutuhkan penerjemah bahasa
                            ?</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input type="radio" value="Ya" id="pre02-01" name="pre02" /><label for="pre02-01">Ya</label>
                                <input type="radio" value="Tidak" id="pre02-02" name="pre02" /><label for="pre02-02">Tidak</label>
                            </div>
                            <input class="form-control" style="display: none" id="pre_penerjemah">
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-7">Apakah anda memiliki masalah dalam berbicara,
                            pendengaran, penglihatan ?</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input type="radio" value="Ya" id="pre03-01" name="pre03" /><label for="pre03-01">Ya</label>
                                <input type="radio" value="Tidak" id="pre03-02" name="pre03" /><label for="pre03-02">Tidak</label>
                            </div>
                            <textarea style="display: none" class="form-control" id="pre_indra"></textarea>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-7">Apakah kontak yang diisi sudah benar ?</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input type="radio" value="Ya" id="pre04-01" name="pre04" /><label for="pre04-01">Ya</label>
                                <input type="radio" value="Tidak" id="pre04-02" name="pre04" /><label for="pre04-02">Tidak</label>
                            </div>
                            <input style="display: none" class="form-control" id="pre_kontak">
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-7" >Apakah anda membutuhkan alat bantu khusus
                            ?</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input type="radio" value="Ya" id="pre05-01" name="pre05" /><label for="pre05-01">Ya</label>
                                <input type="radio" value="Tidak" id="pre05-02" name="pre05" /><label for="pre05-02">Tidak</label>
                            </div>
                            <select style="display: none" class="form-control" id="pre_alat_bantu">
                                <option value="Kursi roda">Kursi roda</option>
                                <option value="Tongkat/kruk">Tongkak/kruk</option>
                                <option value="Brankar">Brankar</option>
                                <option value="Alat bantu dengar">Alat bantu dengar</option>
                                <option value="Asisten pribadi">Asisten pribadi</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 7px">
                    <div class="form-group">
                        <label class="col-md-7">Apakah anda mempunyai riwayat alergi ?</label>
                        <div class="col-md-4">
                            <div class="custom02">
                                <input type="radio" value="Ya" id="pre06-01" name="pre06" /><label for="pre06-01">Ya</label>
                                <input type="radio" value="Tidak" id="pre06-02" name="pre06" /><label for="pre06-02">Tidak</label>
                            </div>
                            <select style="display: none; width: 50%" class="form-control" id="pre_alergi">
                                <option value="Obat">Obat</option>
                                <option value="Lainnya">Lainnya</option>
                            </select>
                            <textarea style="display: none; margin-top: 7px" class="form-control" id="pre_ket_alergi"></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" onclick="saveFormAdmisi()"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-detail-rekam-medic">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Rekam Medik Pasien</h4>
            </div>
            <div class="modal-body">
                <div id="head-detail-rm"></div>
                <hr/>
                <!-- Custom Tabs -->
                <div class="nav-tabs-custom">
                  <ul class="nav nav-tabs">
                    <li id="tab_1"><a href="#" data-toggle="tab" onclick="viewDetailRekamMedicByKategori('tppri')">TPPRI</a></li>
                    <li><a href="#" data-toggle="tab" onclick="viewDetailRekamMedicByKategori('igd')">IGD</a></li>
                    <li><a href="#" data-toggle="tab" onclick="viewDetailRekamMedicByKategori('ri')">RAWAT INAP</a></li>
                    <li><a href="#" data-toggle="tab" onclick="viewDetailRekamMedicByKategori('mon')">MONITORING</a></li>
                  </ul>
                  <div class="tab-content" id="list-body-rekam-medic">
                    <%-- <table class="table">
                      <tbody id="list-body-rekam-medic">
                      </tbody>
                    </table> --%>
                    <!-- /.tab-pane -->
                  </div>
                  <input type="hidden" name="" id="rm-no-checkup">
                  <!-- /.tab-content -->
                </div>
                <!-- nav-tabs-custom -->

            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-detail-rekam-medic-lama">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Rekam Medik Lama Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="bungkus">
                    <div class="carousel">
                        <button onclick="carouselSwipe('carousel-arrow-prev')" type="button" id="carousel-arrow-prev" class="carousel-arrow carousel-arrow-prev" arial-label="Image précédente"></button>
                        <button onclick="carouselSwipe('carousel-arrow-next')" type="button" id="carousel-arrow-next" class="carousel-arrow carousel-arrow-next" arial-label="Image suivante"></button>
                        <div id="body-img-rm"></div>
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

<div class="modal fade" id="modal-obat-kronis">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Obat Kronis</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_list_kronis">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_list_kronis"></p>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-striped">
                            <tr>
                                <td><b>NO RM</b></td>
                                <td><span id="kron_no_rm"></span></td>
                            </tr>
                            <tr>
                                <td><b>Nama</b></td>
                                <td><span id="kron_nama"></span></td>
                            </tr>
                            <tr>
                                <td><b>Terakhir Periksa</b></td>
                                <td><span id="kron_tgl_periksa"></span></td>
                            </tr>
                            <tr>
                                <td><b>Diagnosa Terakhir</b></td>
                                <td><span id="kron_diagnosa_terakhir"></span></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <table class="table table-bordered table-striped" id="tabel-kronis">
                    <thead>
                    <td>ID Obat</td>
                    <td>Nama Obat</td>
                    <td>Stok Obat</td>
                    <td>Jenis Satuan</td>
                    <td>Keterangan</td>
                    <td>Hari Kronis</td>
                    <td>Qty</td>
                    </thead>
                    <tbody id="body_kronis">

                    </tbody>

                </table>

            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_kronis"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_kronis"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-jadwal-dokter">
    <div class="modal-dialog" style="width: 57%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Jadwal Dokter <span id="dokter_pelayanan"></span> Hari Ini</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="col-md-12" style="display:inline; padding-left: 6%">
                        <div class="btn-wrapper">
                            <div id="jadwal_dokter"></div>
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


<!-- /.content-wrapper -->
<script type='text/javascript' src='<s:url value="/pages/dist/js/rekammedic.js"/>'></script>
<script type='text/javascript'>

    var idPelayanan = $('#id_pelayanan_poli').val();
    var isOnline = '<s:property value="headerCheckup.isOnlne"/>';
    var contextPath = '<%= request.getContextPath() %>';

    $(document).ready(function () {

        $('#pendaftaran').addClass('active');
        initlistPenjamin();
        initListDokter();

        $(document).on('change', '.btn-file :file', function () {
            var input = $(this),
                    label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [label]);
        });

        var src = '<s:property value="headerCheckup.urlKtp"/>';

        if(src != ''){
            $('#img-upload').attr('src',src);
        }

        $('.btn-file :file').on('fileselect', function (event, label) {

            var input = $(this).parents('.input-group').find(':text'),
                    log = label;

            if (input.length) {
                input.val(log);
                var warn = $('#war_foto_rujukan').is(':visible');
                if (warn){
                    $('#con_foto_rujukan').show().fadeOut(3000);$('#war_foto_rujukan').hide()
                }
            } else {
                if (log) alert(log);
            }

        });

        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#img-upload').attr('src', e.target.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }

        $("#imgInp").change(function () {
            readURL(this);
        });

        var nominal = document.getElementById('uang_muka');
        var coverBiaya = document.getElementById("nominal_cover_biaya");
        if(nominal != null && nominal != ''){
            nominal.addEventListener('keyup', function (e) {
                nominal.value = formatRupiah2(this.value);
                var valBayar = nominal.value.replace(/[.]/g, '');

                if(valBayar != ''){
                    $('#uang_muka_val').val(valBayar);
                }else{
                    $('#uang_muka_val').val('');
                }
            });
        }

        if(coverBiaya != null && coverBiaya != ''){
            coverBiaya.addEventListener('keyup', function (e) {
                coverBiaya.value = formatRupiah2(this.value);
                var valCover = coverBiaya.value.replace(/[.]/g, '');
                if(valCover != ''){
                    $('#cover_biaya').val(valCover);
                }else{
                    $('#cover_biaya').val('');
                }
            });
        }
    });

    function setFormAdmisi() {
        $('#modal-admisi').modal({show: true, backdrop: 'static'});
    }

    function saveFormAdmisi() {

        var data = [];
        var check1 = $('[name=pre01]:checked').val();
        var check2 = $('[name=pre02]:checked').val();
        var check3 = $('[name=pre03]:checked').val();
        var check4 = $('[name=pre04]:checked').val();
        var check5 = $('[name=pre05]:checked').val();
        var check6 = $('[name=pre06]:checked').val();

        var keyakinan = $('#pre_keyakinan').val();
        var penerjemah = $('#pre_penerjemah').val();
        var indra = $('#pre_indra').val();
        var kontak = $('#pre_kontak').val();
        var alatBantu = $('#pre_alat_bantu').val();
        var alergi = $('#pre_alergi').val();
        var ketAlergi = $('#pre_ket_alergi').val();

        var hslAlergi = "";

        if(alergi != '' && ketAlergi != ''){
            hslAlergi = alergi+', '+ketAlergi;
        }else{
            if(alergi != ''){
                hslAlergi = alergi;
            }
        }

        data.push({'parameter':'Adakah hal yang berkaitan dengan keyakinan anda yang perlu kami ketahui ?', 'jawaban':check1, 'keterangan':keyakinan});
        data.push({'parameter':'Apakah anda membutuhkan penerjemah bahasa ?', 'jawaban':check2, 'keterangan':penerjemah});
        data.push({'parameter':'Apakah anda memiliki masalah dalam berbicara, pendengaran, penglihatan ?', 'jawaban':check3, 'keterangan':indra});
        data.push({'parameter':'Apakah kontak yang diisi sudah benar ?', 'jawaban':check4, 'keterangan':kontak});
        data.push({'parameter':'Apakah anda membutuhkan alat bantu khusus ?', 'jawaban':check5, 'keterangan':alatBantu});
        data.push({'parameter':'Apakah anda mempunyai riwayat alergi ?', 'jawaban':check6, 'keterangan':hslAlergi});
        var stringJson = JSON.stringify(data);
        $('#data_admisi').val(stringJson);
        var dataAdmisi = $('#data_admisi').val();
        var json = JSON.parse(dataAdmisi);

        if (check1 != '' && check2 != '' && check3 != '' && check4 != '' && check5 != '' && check6 != '') {
            $('#btn-admisi').addClass("btn btn-warning");
        } else {
            $('#btn-admisi').removeClass("btn btn-warning").addClass("btn btn-primary");
        }
        $('#modal-admisi').modal('hide');
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
                    var title = "";
                    var msg = "";
                    var icon = "";
                    var val = "";

                    if(response.status == "200"){
                        val = "aktif";
                        icon = "fa-info";
                        title = "Info!";
                        warnClass = "alert-success";
                        msg =   '<p>Nomor Rujukan Berhasil Diverifikasi..!</p>'+
                                '<p>Jenis Rawat  : '+response.namaPelayanan+'</p>'+
                                '<p>Poli Rujukan : '+response.namaPoliRujukan+'</p>';
                        $('#idPelayananBpjs').val(response.kodePoliRujukan);
                        $('#ppk_rujukan').val(response.kdProviderProvUmum);
                        $('#intansi_perujuk').val(response.namaProvPerujuk);
                        $('#tgl_rujukan').val(response.tglCetakKartu);
                    }else{
                        val = "tidak ditemukan";
                        icon = "fa-warning";
                        title = "Warning!";
                        warnClass = "alert-warning";
                        msg = response.message;
                        $('#idPelayananBpjs').val("IGD");
                    }

                    var warning = '<div class="alert ' + warnClass + ' alert-dismissible">' +
                        '<h4><i class="icon fa ' + icon + '"></i>' + title + '</h4>' + msg +
                        '</div>';

                    $('#status_rujukan').val(val);
                    $('#warn_rujukan').html(warning);
                    $('#btn-cek-rujukan').html('<i class="fa fa-search"></i> Check');
                }
            });
        }
    }

    function initlistPenjamin() {
        var tipe = '<s:property value="tipe"/>';
        var option = "";
        CheckupAction.getComboJenisPeriksaPasienWithBpjs(function (response) {
            if (response.length > 0) {
                option = "<option value=''>[Select One]</option>";
                $.each(response, function (i, item) {
                    option += '<option value="' + item.idJenisPeriksaPasien + '">' + item.keterangan + '</option>';
                });
                $('#penjamin').html(option);
            }
        });

        $('#penjamin').val(tipe).trigger('change').attr('disabled', true);

        if(tipe == 'asuransi'){
            listSelectAsuransi();
        }
        if(tipe == 'paket_perusahaan' || tipe == 'paket_individu'){
            listSelectPaket(tipe);
        }
        if(tipe == 'rekanan'){
            listSelectRekanan();
        }

        $('#id_jenis_periksa').val(tipe);
    }

    function listPenjaminNoBpjs() {
        var option = "";
        CheckupAction.getComboJenisPeriksaPasienNotBpjs(function (response) {
            if (response.length > 0) {
                option = "<option value=''>[Select One]</option>";
                $.each(response, function (i, item) {
                    option += '<option value="' + item.idJenisPeriksaPasien + '">' + item.keterangan + '</option>';
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
                    option += '<option value="' + item.idAsuransi +'|'+ item.isLaka +'">' + item.namaAsuransi + '</option>';
                });
                $('#asuransi').html(option);
            }else {
                $('#asuransi').html(option);
            }
        });
    }

    function listSelectPaket(tipe) {
        var option = "<option value=''>[Select One]</option>";
        PaketPeriksaAction.getListPaketPeriksaByTipe("rawat_jalan", function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idPaket +"|"+ item.idPelayanan +"|"+ item.tarif + "'>" + item.namaPaket + "</option>";
                });
                $('#paket').html(option);
            } else {
                $('#paket').html(option);
            }
        });
    }

    function listSelectRekanan(){
        var option = "<option value=''>[Select One]</option>";
        CheckupAction.getListRekananOps(function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idRekananOps +'|'+item.isBpjs+'|'+item.tipe+"'>" + item.namaRekanan + "</option>";
                });
                $('#unit_ptpn').html(option);
            } else {
                $('#unit_ptpn').html(option);
            }
        });
    }

    function cekPtpn(value){
        if(value != ''){
            var rekanan = value.split("|");
            var idRekananOps = rekanan[0];
            var isBpjs = rekanan[1];
            var tipeRekanan = rekanan[2];

            if(isBpjs == 'Y'){
                $('#form_no_bpjs, #form_rujukan').show();
            }else{
                $('#form_no_bpjs, #form_rujukan').hide();
            }

            if(tipeRekanan == 'ptpn'){
                $('#form_pg').show();
            }else{
                $('#form_pg').hide();
            }

            $('#id_rekanan').val(idRekananOps);
            $('#cek_is_bpjs').val(isBpjs);
        }
    }

    function selectPelayanan(value){
        if(value != ''){
            var isi = value.split("|");
            var pak = isi[0];
            var pel = isi[1];
            var cover = isi[2];
            $('#poli_paket').val(pel).trigger('change').attr('disabled', true);
            $('#id_pelayanan_paket').val(pel);
            $('#id_paket').val(pak);
            $('#cover_biaya_paket').val(cover);
        }
    }

    function showLaka(idAsuransi){
        if(idAsuransi != ''){
            var temp = idAsuransi.split("|");
            var id = temp[0];
            var isLaka = temp[1];
            if(id != 'null'){
                $('#id_asuransi').val(id);
                $('#is_laka').val(isLaka);
                if(isLaka == "Y"){
                    $('#form_jasaraharja_1, #form_jasaraharja_2').show();
                    $('#form_no_kartu').hide();
                    var cover = formatRupiah(20000000);
                    $('#nominal_cover_biaya').val(cover);
                    $('#cover_biaya').val('20000000');
                    $('#war_jml_cover').hide();
                }else{
                    $('#form_jasaraharja_1, #form_jasaraharja_2').hide();
                    $('#form_no_kartu').show();
                    $('#cover_biaya').val('');
                    $('#nominal_cover_biaya').val('');
                }
            }
        }
    }

    function checkBpjs() {
        var noBpjs = $('#no_bpjs').val();
        $('#btn-cek').html('<i class="fa fa-circle-o-notch fa-spin"></i> Loading...');
        dwr.engine.setAsync(true);
        CheckupAction.checkStatusBpjs(noBpjs, {
            callback: function (response) {
                var warnClass = "";
                var title = "";
                var msg = "";
                var icon = "";
                var val = "";

                if (response.keteranganStatusPeserta == "AKTIF") {

                    $('#kelas_pasien').val(response.kodeKelas);
                    $('#no_mr').val(response.noMr);
                    val = "aktif";
                    icon = "fa-info";
                    title = "Info!";
                    warnClass = "alert-success";
                    msg = "No BPJS berhasil diverifikasi dengan status AKTIF!";
                } else if (response.keteranganStatusPeserta == "TIDAK AKTIF") {
                    val = "tidak aktif";
                    icon = "fa-warning";
                    title = "Warning!";
                    warnClass = "alert-warning";
                    msg = "No BPJS berhasil diverifikasi dengan status TIDAK AKTIF!";
                } else {
                    val = "tidak ditemukan";
                    icon = "fa-warning";
                    title = "Warning!";
                    warnClass = "alert-danger";
                    msg = "No BPJS tidak ditemukan atau periksa kembali koneksi internet anda...!";
                }

                var warning = '<div class="alert ' + warnClass + ' alert-dismissible">' +
                        '<h4><i class="icon fa ' + icon + '"></i>' + title + '</h4>' + msg +
                        '</div>';

                $('#status_bpjs').val(val);
                $('#warn-bpjs').html(warning);
                $('#btn-cek').html('<i class="fa fa-search"></i> Check');

            }
        });

    }

    function listDokter(idPelayanan) {
        var option = "<option value=''>[Select One]</option>";
        if(idPelayanan != null && idPelayanan != ''){
            $('#nama_dokter').attr('onclick','showJadwalDokter(\''+idPelayanan+'\')');
            $('#btn-dokter').attr('onclick','showJadwalDokter(\''+idPelayanan+'\')');
            $('#id_pelayanan_poli').val(idPelayanan);
            PelayananAction.getDataPelayanan(idPelayanan, function (res) {
                var option2 = "<option value=''>[Select One]</option>";
                if(res.idPelayanan != null){
                    if(res.tipePelayanan == "lab" || res.tipePelayanan == "radiologi"){
                        $('#form-lab').show();
                        var idKategori = "";
                        if(res.tipePelayanan == "lab"){
                            idKategori = "KAL00000002";
                        }
                        if(res.tipePelayanan == "radiologi"){
                            idKategori = "KAL00000001";
                        }
                        LabAction.listLab(idKategori, function (response) {
                            if (response != null) {
                                $.each(response, function (i, item) {
                                    option2 += "<option value='" + item.idLab + "'>" + item.namaLab + "</option>";
                                });
                                $('#id_lab').html(option2);
                            } else {
                                $('#id_lab').html(option2);
                            }
                        });
                    }else{
                        $('#form-lab').hide();
                    }
                }
            });

        }else{
            $('#form-lab').hide();
        }
    }

    function initListDokter() {
        var idDokter = '<s:property value="headerCheckup.idDokter"></s:property>';
        if(idPelayanan != ''){
            var option = "";
            CheckupAction.listOfDokter(idPelayanan, function (response) {
                option = "<option value=''>[Select One]</option>";
                if (response.length > 0) {
                    $.each(response, function (i, item) {
                        // option += "<option value='" + item.idDokter + "'>" + item.namaDokter + "</option>";
                        if(idDokter == item.idDokter){
                            $('#dokter').val(idDokter);
                            $('#nama_dokter').val(item.namaDokter);
                        }
                    });
                }
            });
            $('#poli').val(idPelayanan).trigger('change');
        }

        // if(idDokter != ''){
        //     $('#dokter').val(idDokter).trigger('change');
        // }

        var isLama = '<s:property value="headerCheckup.jenisKunjungan"></s:property>';
        if(isLama != ''){

            if(isLama == "Lama"){
                $('#kunjungan').val("Lama").trigger('change');
            }else{
                $('#kunjungan').val("Baru").trigger('change');
            }
        }
    }

    function alertPasien(noPasien) {

        var namapasien = "";
        var diagnosa = "";
        var tglperiksa = "";
        var alergi = "";

//        alert(noPasien);
        CheckupAction.initAlertPasien(noPasien, function (response) {
            if (response != null && response.namaPasien != null) {

                namapasien = "<h4><i class=\"fa fa-user\"></i> " + response.namaPasien + "</h4>";
                diagnosa = response.diagnosa;
                tglperiksa = "Pemeriksaan terakhir pasien pada : <strong>" +  $.datepicker.formatDate('dd-mm-yy', new Date(response.stTglKeluar)) + "</strong>";

                if (response.listOfAlergi != null) {
                    $.each(response.listOfAlergi, function (i, item) {
                        if (alergi != "") {
                            alergi = alergi + ", " + item
                        } else {
                            alergi = item
                        }
                    });
                }

                $("#tgl-periksa").html(tglperiksa);
                $('#date-periksa').val(formatDate(response.stTglKeluar));
                $("#nama-pasien").html(namapasien);
                $("#alergi").html(alergi);
                $("#diagnosa").html(diagnosa);
                $("#alert-pasien").removeAttr("style");
                $("#btn-rm").removeAttr("style");
            } else {
                closeAlert();
            }
        });
    }

    function alertObatKronis(idPasien) {

        var namapasien = "";
        var diagnosa = "";
        var tglperiksa = "";
        var alergi = "";

        CheckupAction.findRiwayatObatKronis(idPasien, function (response) {
            if(response.idDetailCheckup != null){
                if(response.flagPengambilan == "Y"){
                    $('#btn-save').hide();
                    $('#btn-kronis').show();
                    $('#success_kronis').show();
                    $('#msg_kronis2').text("Pengambilan Obat Kronis, Silahkan tekan tombol Obat Kronis untuk melanjutkan");
                    $('#btn-kronis').attr('onclick','showObatKronis(\''+response.idDetailCheckup+'\',\''+response.idApprovalObat+'\')');
                    $('#btn-save').hide();
                }else{
                    if(response.flagKronisDiambil == "K"){
                        $('#btn-kronis').removeAttr('onclick');
                        $('#btn-kronis').hide();
                        $('#warning_kronis').show();
                        $('#msg_kronis').text(response.msg);
                    }else{
                        $('#btn-kronis').removeAttr('onclick');
                        $('#btn-kronis').hide();
                        $('#warning_kronis').show();
                        $('#msg_kronis').text(response.msg+", Tanggal Pemgambilan "+formatDate(response.tglPengambilan));
                    }
                }
                $('html, body').animate({
                    scrollTop: $('#pos_kronis').offset().top
                }, 2000);

            }else{
                $('#btn-kronis').removeAttr('onclick');
                $('#btn-save').show();
                $('#btn-kronis').hide();
                $('#success_kronis').hide();
                $('#warning_kronis').hide();
            }
        });
    }

    function showObatKronis(idDetailCheckup, idApproval){
        if(idDetailCheckup != '' && idApproval != ''){
            $('#kron_no_rm').html($('#id_pasien').val());
            $('#kron_nama').html($('#nama_pasien').val());
            $('#kron_tgl_periksa').html($('#date-periksa').val());
            $('#kron_diagnosa_terakhir').html($('#diagnosa').text());
            $('#modal-obat-kronis').modal({show:true, backdrop:'static'});
            var table = "";
            CheckupAction.getListObatKronis(idDetailCheckup, idApproval, function (response) {
                if(response.length > 0){

                    var idPelayanan = "";

                    $.each(response, function (i, item) {

                        var qtyTotal = 0;
                        var qtyBox = 0;
                        var qtyLembar = 0;
                        var qtyBiji = 0;
                        var hariKronis = 0;
                        var ket = "";

                        if(item.qtyBox != null){
                            qtyBox = item.qtyBox;
                        }

                        if(item.qtyLembar != null){
                            qtyLembar = item.qtyLembar;
                        }

                        if(item.qtyBiji != null){
                            qtyBiji = item.qtyBiji;
                        }

                        if(item.lembarPerBox != null && item.bijiPerLembar != null){
                            qtyTotal = parseInt(qtyBiji) + ((parseInt(item.lembarPerBox * parseInt(qtyBox))) * parseInt(item.bijiPerLembar));
                        }

                        if(item.keterangan != null){
                            ket = item.keterangan;
                        }

                        hariKronis = parseInt(30) - parseInt(item.hariKronis);

                        table += '<tr>' +
                            '<td>'+item.idObat+'<input type="hidden" id="trans_id'+i+'" value="'+item.idTransaksiObatDetail+'">'+'</td>' +
                            '<td>'+item.namaObat+'</td>' +
                            '<td>'+qtyTotal+'</td>' +
                            '<td>biji</td>' +
                            '<td>'+ket+'</td>' +
                            '<td>'+hariKronis+'</td>' +
                            '<td width="20%">'+'<input type="number" onchange="validasiInput(\''+qtyTotal+'\',\''+i+'\')" class="form-control" id="qty'+i+'">'+'</td>' +
                            '</tr>';
                        idPelayanan = item.idPelayanan;
                    });

                    $('#body_kronis').html(table);
                    $('#save_kronis').attr('onclick','savePengambilanObatKronis(\''+idDetailCheckup+'\',\''+idPelayanan+'\')');

                }else{
                    $('#body_kronis').html("");
                }
            })
        }
    }

    function savePengambilanObatKronis(idDetailCheckup, idPelayanan){
        if(idDetailCheckup != '' && idPelayanan != ''){
            var data = $('#tabel-kronis').tableToJSON();
            var result = [];
            var cek = false;
            var cekQty = false;

            $.each(data, function (i, item) {
                var idObat = data[i]["ID Obat"];
                var qty = $('#qty'+i).val();
                var qtyTotal = data[i]["Stok Obat"];
                var hariKronis = data[i]["Hari Kronis"];
                var keterangan = data[i]["Keterangan"];
                var transId = $('#trans_id'+i).val();

                if(qty != ""){
                    result.push({
                        'id_obat':idObat,
                        'jenis_satuan':'biji',
                        'qty':qty,
                        'keterangan':keterangan,
                        'hari_selanjutnya':hariKronis,
                        'trans_id':transId
                    });
                    cekQty = true;
                }

                if(parseInt(qty) > parseInt(qtyTotal)){
                    cek = true;
                }
            });

            var jsonString = JSON.stringify(result);

            if(cekQty){

                if(cek){
                    $('#warning_list_kronis').show().fadeOut(5000);
                    $('#msg_list_kronis').text("Qty request tidak boleh melebihi qty stok obat...!");
                }else{
                    $('#save_kronis').hide();
                    $('#load_kronis').show();
                    dwr.engine.setAsync(true);
                    CheckupAction.savePengambilanObatKronis(idDetailCheckup, jsonString, idPelayanan, {callback: function (response) {
                            if(response.status == "success"){
                                $('#modal-obat-kronis').modal('hide');
                                $('#info_dialog').dialog('open');
                                $('#save_kronis').show();
                                $('#load_kronis').hide();
                            }else{
                                $('#save_kronis').show();
                                $('#load_kronis').hide();
                                $('#warning_list_kronis').show().fadeOut(5000);
                                $('#msg_list_kronis').text(response.msg);
                            }
                        }} );
                }

            }else{
                $('#warning_list_kronis').show().fadeOut(5000);
                $('#msg_list_kronis').text("Silahkan masukkan qty obat...!");
            }
        }
    }

    function validasiInput(stok, i){
        if(stok != ''){
            var qty = $('#qty'+i).val();

            if(parseInt(qty) > parseInt(stok)){
                $('#warning_list_kronis').show().fadeOut(10000);
                $('#msg_list_kronis').text("Qty request tidak boleh melebihi qty stok obat..!");
            }
        }
    }

    function formatDate(tanggal){
        var tgl = "";
        if(tanggal != null && tanggal != ''){
            tgl = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
        }
        return tgl;
    }

    function closeAlert() {
        $("#alert-pasien").attr("style", "display:none");
        $("#btn-rm").attr("style", "display:none");
    }

    function showJadwalDokter(id){
        var pel = $('#poli option:selected').text();
        // var idPelayanan = $('#id_pelayanan_poli').val();
        var table = "";
        // console.log(pel);
        console.log(id);
        if(id != null && id != ''){
            CheckupAction.listOfDokter(id, function(res){
                if(res.length > 0){
                    $.each(res, function (i, item) {
                        var kuota = 0;
                        var sisa = 0;
                        var namaDokter = "";
                        var sip = "231321312";
                        var label = "";
                        var jamkerja = "";
                        if(item.jamAwal != null && item.jamAkhir != null){
                            jamkerja = item.jamAwal +" s/d "+ item.jamAkhir;
                        }
                        if(item.kuotaOnSite != null && item.kuotaOnSite != ''){
                            kuota = item.kuotaOnSite;
                        }
                        if(item.kuotaTerpakai != null && item.kuotaTerpakai != ''){
                            sisa = item.kuotaTerpakai;
                        }

                        label = sisa+"/"+kuota;
                        if(item.namaDokter != ''){
                            namaDokter = item.namaDokter;
                        }
                        if(item.idDokter != ''){
                            sip = item.idDokter;
                        }
                        table += '<div id="id_box_'+i+'" class="btn-trans" onclick="setDokter(\''+item.idDokter+'\', \''+item.namaDokter+'\')">\n' +
                            '<div style="text-align:left; cursor:pointer; font-size:11px;">\n' +
                            '    <table align="center" style="width:100%; border-radius:5px; margin-top:2px;">\n' +
                            '        <tr>\n' +
                            '            <td align="left" colspan="2">\n' +
                            '                <span style="color: white; background-color: #ec971f; padding: 2px; border-radius: 5px; padding: 5px; font-size: 11px">'+jamkerja+'</span>\n' +
                            '                <span class="pull-right" style="margin-top: -6px; color: white; background-color: #ec971f; padding: 2px; border-radius: 5px; padding: 5px; font-size: 11px">'+label+'</span>\n' +
                            '                <%--<img style="margin-top: -6px" class="pull-right" src="<s:url value="/pages/images/icon_failure.ico"/>">--%>\n' +
                            '            </td>\n' +
                            '        </tr>\n' +
                            '        <tr>\n' +
                            '            <td align="center" colspan="2">\n' +
                            '                <img class="img-circle" style="background-color:transparent; height:100px; padding-bottom: 2px; padding-top: 8px" src="<s:url value="/pages/images/guy-5.jpg"/>">\n' +
                            '            </td>\n' +
                            '        </tr>\n' +
                            '        <tr>\n' +
                            '            <td align="left" colspan="2" style="color: black; font-size: 11px; padding-top: 3px; border-bottom: black solid 1px; padding-bottom: 3px">\n' +
                            '                <i class="fa fa-user"></i> '+namaDokter+'\n' +
                            '            </td>\n' +
                            '        </tr>\n' +
                            '        <tr>\n' +
                            '            <td align="left" colspan="2" style="color: black; font-size: 11px; padding-top: 5px">\n' +
                            '                <i class="fa fa-square" style="font-size: 10px"></i> '+sip+'\n' +
                            '            </td>\n' +
                            '        </tr>\n' +
                            '    </table>\n' +
                            '</div>\n' +
                            '</div>';
                    });
                }else{
                    table = '<span class="text-center">Jadwal Dokter Tidak Ditemukan...!</span>'
                }
                $('#dokter_pelayanan').text(pel);
                $('#jadwal_dokter').html(table);
                $('#modal-jadwal-dokter').modal({show:true, backdrop:'static'});
            });
        }
    }

    function setDokter(idDokter, namaDokter) {
        $('#nama_dokter').val(namaDokter);
        $('#dokter').val(idDokter);
        $('#modal-jadwal-dokter').modal('hide');
    }

    var functions, mapped;
    $('#provinsi').typeahead({
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
            document.getElementById("provinsi11").value = selectedObj.id;
            prov = selectedObj.id;
            return namaAlat;
        }
    });

    function changePlaceHolder(n){

        var idx = n.selectedIndex;
        var perujuk = n.options[idx].value;

        if (perujuk == "dokter"){
            $("#intansi_perujuk").attr("placeholder","nama dokter");
        }
        if (perujuk == "1"){
            $("#intansi_perujuk").attr("placeholder","nama puskesmas");
        }
        if (perujuk == "2"){
            $("#intansi_perujuk").attr("placeholder","nama rumah sakit");
        }
        if (perujuk == "bidan"){
            $("#intansi_perujuk").attr("placeholder","nama bidan");
        }
        if (perujuk == "polisi"){
            $("#intansi_perujuk").attr("placeholder","nama instansi");
        }
        if (perujuk == "sendiri"){
            $("#intansi_perujuk").attr("disabled","true");
            $("#intansi_perujuk").removeAttr("placeholder");
        }

    }

</script>
<script type='text/javascript'>
    var functions, mapped;
    // var prov = document.getElementById("provinsi1").value;
    $('#kabupaten').typeahead({
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
            document.getElementById("kabupaten11").value = selectedObj.id;

            kab = selectedObj.id;
            return namaAlat;
        }
    });

    //
    //
</script>

<script type='text/javascript'>
    var functions, mapped;
    var kab = document.getElementById("kabupaten").value;
    $('#kecamatan').typeahead({
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
            document.getElementById("kecamatan11").value = selectedObj.id;

            kec = selectedObj.id;
            return namaAlat;
        }
    });
</script>

<script type='text/javascript'>
    var functions, mapped;
    $('#desa').typeahead({
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
            document.getElementById("desa11").value = selectedObj.id;

            desa = selectedObj.id;
            return namaAlat;
        }
    });


    function changePlaceHolder(n) {

        var idx = n.selectedIndex;
        var perujuk = n.options[idx].value;
        $("#intansi_perujuk").attr("disabled", false);

        if (perujuk == "dokter") {
            $("#intansi_perujuk").attr("placeholder", "nama dokter");
        }
        if (perujuk == "puskesmas") {
            $("#intansi_perujuk").attr("placeholder", "nama puskesmas");
        }
        if (perujuk == "rs") {
            $("#intansi_perujuk").attr("placeholder", "nama rumah sakit");
        }
        if (perujuk == "bidan") {
            $("#intansi_perujuk").attr("placeholder", "nama bidan");
        }
        if (perujuk == "polisi") {
            $("#intansi_perujuk").attr("placeholder", "nama instansi");
        }
        if (perujuk == "sendiri") {
            $("#intansi_perujuk").attr("disabled", true);
            $("#intansi_perujuk").removeAttr("placeholder");
        }
    }

    function showAdmisiPasien() {
        $("#modal-admisi").modal('show');
    }

    function changeBahasa(n) {
        var idx = n.selectedIndex;
        var val_bantuan = n.options[idx].value;

        if (val_bantuan == "Y") {
            $("#bahasa").removeAttr("readOnly");
            $("#bahasa").val("");
        } else {
            $("#bahasa").attr("readOnly", "true");
            $("#bahasa").val("indonesia");
        }
    }

    $('.pre01').click(function () {
    });

    var listAlergi = [];

    function addAlergi(alergi) {
        listAlergi.push({"alergi": alergi});
    }
    function printGagalSEP(kode){
        var idPasien = $('#id_pasien').val();
        if(idPasien != ''){
            window.open(contextPath+'/rekammedik/printSuratPernyataan_rekammedik?idPasien=' + idPasien + '&tipe=' + kode, '_blank');
        }else{
            $('#warning_cetak').show().fadeOut(5000);
            $('#msg_cetak').text("ID Pasien belum ada...!");
        }
    }

    function getRiwayatPemeriksaan(idPasien){
        var li = "";
        CheckupAction.getRiwayatPemeriksaan(idPasien, function (res) {
           if(res.length > 0){
               $.each(res, function (i, item) {
                   li += '<li><a style="cursor: pointer"><i class="fa fa-stethoscope"></i> '+item.namaPelayanan+' - '+item.diagnosa+' - '+item.namaDiagnosa+'</a></li>';
               });
               $('#riwayat').html(li);
           }
        });
    }
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>
