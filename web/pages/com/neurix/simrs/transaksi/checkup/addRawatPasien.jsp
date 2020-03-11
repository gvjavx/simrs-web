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

    </style>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ProvinsiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PasienAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RawatInapAction.js"/>'></script>
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
            var status = $('#status').val();
            var url_string = window.location.href;
            var url = new URL(url_string);
            var tipe = url.searchParams.get("tipe");
            var diagnosa = $('#diagnosa_awal').val();

            if (idPasien != ''
                    && noKtp != ''
                    && namaPasien != ''
                    && jenisKelamin != ''
                    && tempatLahir != ''
                    && tglLahir != ''
                    && agama != ''
                    && poli != ''
                    && dokter != ''
                    && penjamin != ''
                    && provinsi != ''
                    && kota != ''
                    && kecamatan != ''
                    && desa != '') {

                if (tipe == "bpjs") {
//
//                    if(diagnosa != ''){
                    if (status != '') {
                        $('#confirm_dialog').dialog('open');
                    } else {
                        $("html, body").animate({scrollTop: 0}, 600);
                        $('#warning_pasien').show().fadeOut(10000);
                        $('#msg_pasien').text("Silahkan cek status BPJS terlebih dahulu...!");
                    }
//                    }else{
//                        $("html, body").animate({scrollTop: 0}, 600);
//                        $('#warning_pasien').show().fadeOut(10000);
//                        $('#msg_pasien').text("Silahkan cek kembali inputan data pasien...!");
//                        $('#diagnosa_awal').css('border','red solid 1px');
//                    }
                } else {
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
                        <h3 class="box-title"><i class="fa fa-user-plus"></i> Inputan Data Pasien</h3>
                    </div>
                    <s:form id="addCheckupForm" enctype="multipart/form-data" method="post" namespace="/checkup"
                            action="saveAdd_checkup.action" theme="simple">
                        <div class="box-body">
                            <div class="alert alert-danger alert-dismissible" id="warning_pasien" style="display: none">
                                <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                                <p id="msg_pasien"></p>
                            </div>
                            <div id="warn-bpjs"></div>
                            <input type="hidden" id="status">
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Data Pasien</h3>
                                    <%--<button class="btn btn-success pull-right"><i class="fa fa-plus"></i> Pasien Baru</button>--%>
                            </div>
                            <div id="alert-pasien" style="display: none;" class="alert alert-warning alert-dismissible">
                                <button type="button" class="close" onclick="closeAlert()" aria-hidden="true">×</button>
                                <div id="nama-pasien"></div>
                                <div id="tgl-periksa"></div>
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
                                        <s:if test='tipe == "bpjs"'>
                                            <div class="form-group">
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
                                                                            labelItem = item.noBpjs + "-" + item.noKtp + "-" + item.nama;
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
//                                                            $('#penjamin').val("002");
                                                                $('#provinsi').val(selectedObj.prov);
                                                                $('#kabupaten').val(selectedObj.kota);
                                                                $('#kecamatan').val(selectedObj.kec);
                                                                $('#desa').val(selectedObj.desa);
                                                                $('#provinsi11').val(selectedObj.idProv);
                                                                $('#kabupaten11').val(selectedObj.idKota);
                                                                $('#kecamatan11').val(selectedObj.idKec);
                                                                $('#desa11').val(selectedObj.idDesa);
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
                                                $('#id_pasien').typeahead({
                                                    minLength: 1,
                                                    source: function (query, process) {
                                                        functions = [];
                                                        mapped = {};

                                                        var data = [];
                                                        dwr.engine.setAsync(false);

                                                        PasienAction.getListComboPasien(query, function (listdata) {
                                                            data = listdata;
                                                        });

                                                        $.each(data, function (i, item) {
                                                            var labelItem = "";

                                                            if (item.noBpjs != '' && item.noBpjs != null) {
                                                                labelItem = item.noKtp + "-" + item.noBpjs + "-" + item.nama;
                                                            } else {
                                                                labelItem = item.noKtp + "-" + item.nama;
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
                                                                isLama: item.isPasienLama
                                                            };
                                                            functions.push(labelItem);
                                                        });
                                                        process(functions);

                                                    },
                                                    updater: function (item) {
                                                        var selectedObj = mapped[item];

                                                        alertPasien(selectedObj.id);
                                                        alertObatKronis(selectedObj.id);
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
                                                        if (selectedObj.isLama) {
                                                            $('#kunjungan').val("Lama").attr('disabled', true);
                                                            $('#kunjungan_val').val("Lama");
                                                        } else {
                                                            $('#kunjungan').val("Baru").attr('disabled', true);
                                                            $('#kunjungan_val').val("Baru");
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
                                            <label class="col-md-4" style="margin-top: 7px">Foto KTP</label>
                                            <div class="col-md-8">
                                                    <%--<div class="input-group" style="margin-top: 7px" id="img_file">--%>
                                                    <%--<span class="input-group-btn">--%>
                                                    <%--<span class="btn btn-default btn-file">--%>
                                                    <%--&lt;%&ndash;<input type="file" id="imgInp" accept=".jpg" name="fileUploadKtpPasien" onchange="$('#img_file').css('border','')">&ndash;%&gt;--%>
                                                    <%--Browse… <s:file id="imgInp" accept=".jpg" name="fileUpload" onchange="$('#img_file').css('border','')"></s:file>--%>
                                                    <%--</span>--%>
                                                    <%--</span>--%>
                                                    <%--<input type="text" class="form-control" readonly>--%>
                                                    <%--</div>--%>
                                                <img id="img-upload" width="100%"
                                                     src="<s:url value="/pages/images/ktp-default.jpg"/>"
                                                     style="border: darkgray solid 1px; height: 170px; margin-top: 7px"/>
                                                <s:hidden name="headerCheckup.urlKtp" id="img_ktp"></s:hidden>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Diagnosa Awal</label>
                                            <div class="col-md-8">
                                                <div id="diag_bpjs">
                                                    <s:if test='tipe == "bpjs"'>
                                                        <s:textfield id="diagnosa_awal" style="margin-top: 7px"
                                                                     name="headerCheckup.diagnosa"
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
                                                        </script>
                                                        <s:textarea rows="4" id="diagnosa_ket"
                                                                    cssStyle="margin-top: 7px" readonly="true"
                                                                    name="headerCheckup.namaDiagnosa"
                                                                    cssClass="form-control"></s:textarea>

                                                    </s:if>
                                                </div>

                                                <s:else>

                                                    <s:action id="initComboDiagnosa" namespace="/checkupdetail"
                                                              name="getListComboDiagnosa_checkupdetail"/>
                                                    <s:select cssStyle="margin-top: 7px; width: 100%"
                                                              onchange="var warn =$('#war_diagnosa').is(':visible'); if (warn){$('#cor_diagnosa').show().fadeOut(3000);$('#war_diagnosa').hide()}"
                                                              list="#initComboDiagnosa.listOfComboDiagnosa"
                                                              id="nosa_id_diagnosa_1"
                                                              name="headerCheckup.diagnosa" listKey="idDiagnosa"
                                                              listValue="descOfDiagnosa"
                                                              headerKey="" headerValue="[Select one]"
                                                              cssClass="form-control select2"/>

                                                </s:else>
                                                <div id="diag_umum" style="display:none">
                                                    <s:action id="initComboDiagnosa" namespace="/checkupdetail"
                                                              name="getListComboDiagnosa_checkupdetail"/>
                                                    <s:select cssStyle="margin-top: 7px; width: 100%"
                                                              onchange="var warn =$('#war_diagnosa').is(':visible'); if (warn){$('#cor_diagnosa').show().fadeOut(3000);$('#war_diagnosa').hide()}"
                                                              list="#initComboDiagnosa.listOfComboDiagnosa"
                                                              id="nosa_id_diagnosa_2"
                                                              name="headerCheckup.diagnosa" listKey="idDiagnosa"
                                                              listValue="descOfDiagnosa"
                                                              headerKey="" headerValue="[Select one]"
                                                              cssClass="form-control select2" disabled="true"/>
                                                </div>
                                            </div>
                                        </div>
                                        <%--<div class="form-group">--%>
                                            <%--<label class="col-md-4" style="margin-top: 7px">Uang Muka</label>--%>
                                            <%--<div class="col-md-8">--%>
                                                <%--<s:textfield type="number" cssStyle="margin-top: 7px" name="headerCheckup.uangMuka" cssClass="form-control"/>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
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
                                                             cssClass="form-control" cssStyle="margin-top: 7px"
                                                             data-inputmask="'mask': ['+62 999-9999-9999']"
                                                             data-mask=""/>
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

                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Data Kunjungan</h3>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4">Poli</label>
                                            <div class="col-md-8">
                                                <s:action id="initComboPoli" namespace="/checkup"
                                                          name="getComboPelayanan_checkup"/>
                                                <s:select cssStyle="margin-top: 7px; width: 100%"
                                                          list="#initComboPoli.listOfPelayanan" id="poli"
                                                          name="headerCheckup.idPelayanan" listKey="idPelayanan"
                                                          listValue="namaPelayanan"
                                                          onchange="$(this).css('border',''); listDokter(this); var warn =$('#war_poli').is(':visible'); if (warn){$('#cor_poli').show().fadeOut(3000);$('#war_poli').hide()}"
                                                          headerKey="" headerValue="[Select one]"
                                                          cssClass="form-control select2"/>
                                                <span style="color: red; display: none" id="war_poli"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_poli"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Dokter</label>
                                            <div class="col-md-8">
                                                <select id="dokter" class="form-control select2"
                                                        name="headerCheckup.idDokter"
                                                        style="margin-top: 7px; width: 100%"
                                                        onchange="var warn =$('#war_dokter').is(':visible'); if (warn){$('#con_dokter').show().fadeOut(3000);$('#war_dokter').hide()}">
                                                    <option value=''>[Select One]</option>
                                                </select>
                                                <span style="color: red; display: none" id="war_dokter"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_dokter"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Penjamin</label>
                                            <div class="col-md-8">
                                                <select style="margin-top: 7px" id="penjamin"
                                                        class="form-control select2"
                                                        name="headerCheckup.idJenisPeriksaPasien"
                                                        style="margin-top: 7px; width: 100%"
                                                        onchange="var warn =$('#war_penjamin').is(':visible'); if (warn){$('#con_penjamin').show().fadeOut(3000);$('#war_penjamin').hide()}">
                                                    <option value="">[Select One]</option>
                                                </select>
                                                <span style="color: red; display: none" id="war_penjamin"><i
                                                        class="fa fa-times"></i> required</span>
                                                <span style="color: green; display: none" id="con_penjamin"><i
                                                        class="fa fa-check"></i> correct</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4">Kunjungan</label>
                                            <div class="col-md-8">
                                                <s:select list="#{'Lama':'Lama','Baru':'Baru'}"
                                                          cssStyle="margin-top: 7px" onchange="$(this).css('border','')"
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

                            <s:if test='tipe == "bpjs"'>
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
                                                <s:select list="#{'1':'PPK 1 - Puskesmas','2':'PPK 2 - RS Lain'}"
                                                        cssStyle="margin-top: 7px"
                                                        name="headerCheckup.rujuk"
                                                        onchange="changePlaceHolder(this)"
                                                        id="perujuk"
                                                        headerKey="" headerValue="[Select one]"
                                                        cssClass="form-control"/>
                                            </div>
                                            <%--list="#{'sendiri':'Sendiri','dokter':'Dokter','puskesmas':'Puskesmas','rs':'RS Lain','bidan':'Bidan','polis':'Polisi'}--%>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Keterangan Perujuk</label>
                                            <div class="col-md-8">
                                                <s:textfield id="intansi_perujuk" name="headerCheckup.ketPerujuk"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">No Rujukan</label>
                                            <div class="col-md-8">
                                                <div class="input-group" style="margin-top: 7px">
                                                    <s:textfield id="no_rujukan" cssClass="form-control"
                                                                 name="headerCheckup.noRujukan"></s:textfield>
                                                    <div class="input-group-btn">
                                                        <a class="btn btn-success" onclick="cekNoRujukan()">
                                                            <span id="btn-cek-rujukan"><i class="fa fa-search"></i> Check</span></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">No PPK Rujukan</label>
                                            <div class="col-md-8">
                                                <s:textfield name="headerCheckup.noPpkRujukan" id="ppk_rujukan"
                                                             cssClass="form-control" cssStyle="margin-top: 7px"/>
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
                                                                 cssClass="form-control datepicker"
                                                                 onchange="$('#st_tgl_lahir').css('border','')"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Foto Surat Rujuk</label>
                                            <div class="col-md-8">
                                                <div class="input-group" style="margin-top: 7px" id="img_url">
                                                    <span class="input-group-btn">
                                                        <span class="btn btn-default btn-file">
                                                            <%--<input type="file" id="imgInp" accept=".jpg" name="fileUploadKtpPasien" onchange="$('#img_file').css('border','')">--%>
                                                            Browse… <s:file id="url_do" accept=".jpg"
                                                                            name="fileUploadDoc"
                                                                            onchange="$('#img_file').css('border','')"></s:file>
                                                        </span>
                                                    </span>
                                                    <input type="text" class="form-control" readonly>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </s:if>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-user"></i> Form Inputan</h3>
                            </div>
                            <div class="box-body">
                                <s:textfield type="hidden" id="data_admisi" name="headerCheckup.admisi"></s:textfield>
                                <div class="row">
                                    <div class="col-md-12">
                                    <div class="form-group">
                                        <a class="btn btn-primary" id="btn-admisi" onclick="setFormAdmisi()"><i class="fa fa-edit"></i> Form Pre-Admisi</a>
                                    </div>
                                    <div class="form-group" style="margin-top: 20px">
                                        <i class="fa fa-square" style="color: #286090"></i> Belum diisi
                                        <i class="fa fa-square" style="color: #ec971f"></i> Sudah diisi
                                    </div>
                                    </div>
                                </div>
                            </div>

                            <%--from bpjs--%>
                            <s:hidden name="headerCheckup.kelasPasien" id="kelas_pasien"></s:hidden>
                            <s:hidden name="headerCheckup.noMr" id="no_mr"></s:hidden>
                            <s:hidden name="headerCheckup.idPelayananBpjs" id="idPelayananBpjs"></s:hidden>
                            <s:hidden name="headerCheckup.noCheckupOnline"></s:hidden>

                            <s:if test='tipe != "bpjs"'>
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
                                                        name="headerCheckup.metodePembayaran"
                                                        headerKey="" headerValue="[Select one]"
                                                        cssClass="form-control"/>
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
                                                    <s:textfield type="text" id="uang_muka" cssClass="form-control"/>
                                                </div>
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
                            <div class="alert alert-success alert-dismissible" id="success_kronis" style="display: none">
                                <h4><i class="icon fa fa-info"></i> Info!</h4>
                                <p id="msg_kronis2"></p>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group" style="display: inline;">
                                            <div class="col-md-offset-3 col-md-7" style="margin-top: 7px">
                                                <button type="button" id="btn-save" class="btn btn-success" onclick="confirm()"><i
                                                        class="fa fa-arrow-right"></i> Save
                                                </button>
                                                <button type="button" class="btn btn-danger"
                                                        onclick="window.location.reload(true)">
                                                    <i class="fa fa-refresh"></i> Reset
                                                </button>
                                                <a type="button" class="btn btn-warning" href="initForm_checkup.action">
                                                    <i class="fa fa-arrow-left"></i> Back
                                                </a>
                                                <a type="button" id="btn-rm" style="display:none;"
                                                   class="btn btn-primary"
                                                   onclick="initRekamMedic()">
                                                    <i class="fa fa-search"></i> View Rekam Medik
                                                </a>

                                                <a type="button" id="btn-kronis" style="display:none;"
                                                   class="btn btn-info"
                                                   onclick="initKronis()">
                                                    <i class="fa fa-medkit"></i> Obat Kronis
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
                    <%--<div class="col-md-6">--%>
                        <%--<table class="table table-striped">--%>
                           <%----%>
                        <%--</table>--%>
                    <%--</div>--%>
                </div>
                <table class="table table-bordered table-striped" id="tabel-kronis">
                    <thead>
                    <td>ID Obat</td>
                    <td>Nama Obat</td>
                    <td>Stok Obat</td>
                    <td>Jenis Satuan</td>
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
                        class="fa fa-arrow-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_kronis"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript' src='<s:url value="/pages/dist/js/rekammedic.js"/>'></script>
<script type='text/javascript'>

    var idPelayanan = $('#poli').val();
    var isOnline = '<s:property value="headerCheckup.isOnlne"/>';

    $(document).ready(function () {

        $("#bahasa").val("indonesia");
        $("#bahasa").attr("readOnly","true");
        $('#pendaftaran').addClass('active');
        initlistPenjamin();
        initListDokter();

        $('[name=pre01],[name=pre02],[name=pre03],[name=pre04],[name=pre05],[name=pre06]').click(function(){

            var check1 = $('[name=pre01]:checked').val();

            if(check1 == "Ada"){
                $('#pre_keyakinan').show();
            }else{
                $('#pre_keyakinan').hide();
            }

            var check2 = $('[name=pre02]:checked').val();
            if(check2 == "Ya"){
                $('#pre_penerjemah').show();
            }else{
                $('#pre_penerjemah').hide();
            }

            var check3 = $('[name=pre03]:checked').val();
            if(check3 == "Ya"){
                $('#pre_indra').show();
            }else{
                $('#pre_indra').hide();
            }

            var check4 = $('[name=pre04]:checked').val();
            if(check4 == "Tidak"){
                $('#pre_kontak').show();
            }else{
                $('#pre_kontak').hide();
            }

            var check5 = $('[name=pre05]:checked').val();
            if(check5 == "Ya"){
                $('#pre_alat_bantu').show();
            }else{
                $('#pre_alat_bantu').hide();
            }

            var check6 = $('[name=pre06]:checked').val();
            if(check6 == "Ya"){
                $('#pre_alergi').show();
                $('#pre_ket_alergi').show();
            }else{
                $('#pre_alergi').hide();
                $('#pre_ket_alergi').hide();
            }



        });

        $(document).on('change', '.btn-file :file', function () {
            var input = $(this),
                    label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [label]);
        });

        var src = '<s:property value="headerCheckup.urlKtp"/>';
        console.log(src);
        if(src != ''){
            $('#img-upload').attr('src',src);
        }

        $('.btn-file :file').on('fileselect', function (event, label) {

            var input = $(this).parents('.input-group').find(':text'),
                    log = label;

            if (input.length) {
                input.val(log);
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

        console.log(isOnline);

        var nominal = document.getElementById('uang_muka');
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
        console.log(data);
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

            console.log(perujuk);
            console.log(jenisRujukan);

            $('#btn-cek-rujukan').html('<i class="fa fa-circle-o-notch fa-spin"></i> Loading...')
            dwr.engine.setAsync(true);
            CheckupAction.checkSuratRujukan(noRujukan, jenisRujukan, {
                callback: function (response) {
                    console.log(response);
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
        // var url_string = window.location.href;
        // var url = new URL(url_string);
        // var tipe = url.searchParams.get("tipe");

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
        if (tipe == "bpjs") {
            $('#penjamin').val('bpjs').trigger('change');
        }
        if (tipe == "umum") {
            listPenjaminNoBpjs()
            $('#penjamin').val('umum').trigger('change');
        }
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

    function checkBpjs() {
        var noBpjs = $('#no_bpjs').val();
        $('#btn-cek').html('<i class="fa fa-circle-o-notch fa-spin"></i> Loading...');
        dwr.engine.setAsync(true);
        CheckupAction.checkStatusBpjs(noBpjs, {
            callback: function (response) {
                console.log(response);
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
                    msg = "No Bpjs berhasil diverifikasi dengan status AKTIF!";
                    $('#diag_bpjs').show();
                    $('#diag_umum').hide();
                    $('#nosa_id_diagnosa_2').attr('disabled', true);
                } else if (response.keteranganStatusPeserta == "TIDAK AKTIF") {
                    val = "tidak aktif";
                    icon = "fa-warning";
                    title = "Warning!";
                    warnClass = "alert-warning";
                    msg = "No Bpjs berhasil diverifikasi dengan status TIDAK AKTIF!";
                    $('#diag_bpjs').hide();
                    $('#diag_umum').show();
                    $('#diagnosa_awal').attr('disabled', true);
                    listPenjaminNoBpjs();
                    $('#penjamin').val('umum').trigger('change');
                } else {
                    val = "tidak ditemukan";
                    icon = "fa-warning";
                    title = "Warning!";
                    warnClass = "alert-warning";
                    msg = "No Bpjs tidak ditemukan!";
                    $('#diag_bpjs').hide();
                    $('#diag_umum').show();
                    $('#diagnosa_awal').attr('disabled', true);
                    listPenjaminNoBpjs();
                    $('#penjamin').val('umum').trigger('change');
                }
                var warning = '<div class="alert ' + warnClass + ' alert-dismissible">' +
                        '<h4><i class="icon fa ' + icon + '"></i>' + title + '</h4>' + msg +
                        '</div>';

                $('#status').val(val);
                $('#warn-bpjs').html(warning);
                $('#btn-cek').html('<i class="fa fa-search"></i> Check');

            }
        });

    }

    function listDokter(idPelayanan) {
        var idx = idPelayanan.selectedIndex;
        var idPoli = idPelayanan.options[idx].value;
        var option = "";
        CheckupAction.listOfDokter(idPoli, function (response) {
            option = "<option value=''>[Select One]</option>";
            if (response != null) {
                console.log(response);
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idDokter + "'>" + item.namaDokter + "</option>";
                });

                $('#dokter').html(option);
            } else {
                option = option;
            }
        });
    }

    function initListDokter() {
        if(idPelayanan != ''){
            var option = "";
            CheckupAction.listOfDokter(idPelayanan, function (response) {
                option = "<option value=''>[Select One]</option>";
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idDokter + "'>" + item.namaDokter + "</option>";
                    });

                    $('#dokter').html(option);
                } else {
                    option = option;
                }
            });
        }

        var idDokter = '<s:property value="headerCheckup.idDokter"></s:property>';
        if(idDokter != ''){
            $('#dokter').val(idDokter).trigger('change');
        }

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
                console.log(response);

                if(response.flagPengambilan == "Y"){
                    $('#btn-save').hide();
                    $('#btn-kronis').show();
                    $('#success_kronis').show();
                    $('#msg_kronis2').text("Pengambilan Obat Kronis, Silahkan tekan tombol Obat Kronis untuk melanjutkan");
                    $('#btn-kronis').attr('onclick','showObatKronis(\''+response.idDetailCheckup+'\',\''+response.idApprovalObat+'\')');
                }else{
                    $('#btn-kronis').removeAttr('onclick');
                    $('#btn-kronis').hide();
                    $('#warning_kronis').show();
                    $('#msg_kronis').text(response.msg+", Tanggal Pemgambilan "+formatDate(response.tglPengambilan));
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

                    $.each(response, function (i, item) {

                        var qtyTotal = 0;
                        var qtyBox = 0;
                        var qtyLembar = 0;
                        var qtyBiji = 0;

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

                        table += '<tr>' +
                            '<td>'+item.idObat+'</td>' +
                            '<td>'+item.namaObat+'</td>' +
                            '<td>'+qtyTotal+'<input type="hidden" id="hari_kronis'+i+'" value="'+item.hariKronis+'">'+'</td>' +
                            '<td>biji</td>' +
                            '<td width="20%">'+'<input type="number" onchange="validasiInput(\''+qtyTotal+'\',\''+i+'\')" class="form-control" id="qty'+i+'">'+'</td>' +
                            '</tr>'
                    });

                    $('#body_kronis').html(table);
                    $('#save_kronis').attr('onclick','savePengambilanObatKronis(\''+idDetailCheckup+'\')');
                }else{

                }
            })
        }
    }

    function savePengambilanObatKronis(idDetailCheckup){
        if(idDetailCheckup != ''){
            var data = $('#tabel-kronis').tableToJSON();
            var result = [];
            var cek = false;
            var cekQty = false;

            $.each(data, function (i, item) {
                var idObat = data[i]["ID Obat"];
                var qty = $('#qty'+i).val();
                var qtyTotal = data[i]["Stok Obat"];
                var hariKronis = $('#hari_kronis'+i).val();
                var hariNext = parseInt(30) - parseInt(hariKronis);

                if(qty != ""){
                    result.push({'id_obat':idObat, 'jenis_satuan':'biji', 'qty':qty, 'hari_kronis':hariKronis, 'hari_selanjutnya':hariNext});
                    cekQty = true;
                }

                if(parseInt(qty) > parseInt(qtyTotal)){
                    cek = true;
                }
            });

            console.log(result);
            var jsonString = JSON.stringify(result);

            if(cekQty){

                if(cek){
                    $('#warning_list_kronis').show().fadeOut(5000);
                    $('#msg_list_kronis').text("Qty request tidak boleh melebihi qty stok obat...!");
                }else{
                    $('#save_kronis').hide();
                    $('#load_kronis').show();
                    dwr.engine.setAsync(true);
                    CheckupAction.savePengambilanObatKronis(idDetailCheckup, jsonString, {callback: function (response) {
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
        console.log(stok);
        console.log(i);
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
        console.log(val_bantuan);

        if (val_bantuan == "Y") {
            $("#bahasa").removeAttr("readOnly");
            $("#bahasa").val("");
        } else {
            $("#bahasa").attr("readOnly", "true");
            $("#bahasa").val("indonesia");
        }
    }

    $('.pre01').click(function () {
        console.log('tes');
    });

    var listAlergi = [];

    function addAlergi(alergi) {
        listAlergi.push({"alergi": alergi});
    }
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>
