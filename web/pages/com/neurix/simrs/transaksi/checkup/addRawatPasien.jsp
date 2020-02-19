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

        /*.dropdown-content a:hover {background-color: #f1f1f1}*/

        /*.dropdown:hover .dropdown-content {*/
        /*display: block;*/
        /*}*/

        /*.dropdown:hover .dropbtn {*/
        /*background-color: #3e8e41;*/
        /*}*/
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
                                            <label class="col-md-4" style="margin-top: 7px">ID Pasien</label>
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
                                                             data-inputmask="'mask': ['9999999999999999']"
                                                             data-mask=""/>
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
                                                <s:textfield cssStyle="margin-top: 7px" id="provinsi" name=""
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
                                                <s:textfield cssStyle="margin-top: 7px" id="kabupaten" name=""
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
                                                <s:textfield cssStyle="margin-top: 7px" id="kecamatan" name=""
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
                                                <s:textfield cssStyle="margin-top: 7px" id="desa" name=""
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
                                                <s:select
                                                        list="#{'sendiri':'Sendiri','dokter':'Dokter','puskesmas':'Puskesmas','rs':'RS Lain','bidan':'Bidan','polis':'Polisi'}"
                                                        cssStyle="margin-top: 7px"
                                                        name="headerCheckup.rujuk"
                                                        onchange="changePlaceHolder(this)"
                                                        id="perujuk"
                                                        headerKey="" headerValue="[Select one]"
                                                        cssClass="form-control"/>
                                            </div>
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
                                                <s:textfield name="headerCheckup.noPpkRujukan"
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
                                                    <s:textfield name="headerCheckup.tglRujukan"
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

                            <div class="box-header with-border"></div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group" style="display: inline;">
                                            <div class="col-md-offset-3 col-md-7" style="margin-top: 7px">
                                                <button type="button" class="btn btn-success" onclick="confirm()"><i
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
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_rese_detail">
                        <thead>
                        <td>No Checkup</td>
                        <td>Diagnosa Terakhir</td>
                        <td>Tanggal Masuk</td>
                        <td>Tanggal Keluar</td>
                        <td>View Details RM</td>
                        </thead>
                        <tbody id="body-rekam-medic">
                        </tbody>
                    </table>
                </div>
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
                        <label class="col-md-6">Adakah hal hal yang berkaitan dengan keyakinan anda yang perlu kami
                            ketahui ?</label>
                        <div class="col-md-6"><input class="form-control" id="pre_keyakinan"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-6" style="margin-top: 7px">Apakah anda membutuhkan penerjemah bahasa
                            ?</label>
                        <div class="col-md-6">
                            <label>
                                <input type="radio" name="r3" class="flat-red"> Ya
                            </label>
                            <label>
                                <input type="radio" name="r3" class="flat-red" checked> Tidak
                            </label>
                            <input class="form-control" style="margin-top: 7px" id="pre_penerjemah">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-6" style="margin-top: 7px">Apakah anda memiliki masalah dalam berbicara,
                            pendengaran, penglihatan ?</label>
                        <div class="col-md-6"><input class="form-control" style="margin-top: 7px" id="pre_indra"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-6" style="margin-top: 7px">Apakah kontak yang diisi sudah benar ?</label>
                        <div class="col-md-6"><input class="form-control" style="margin-top: 7px" id="pre_kontak"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-6" style="margin-top: 7px">Apakah anda membutuhkan alat bantu khusus
                            ?</label>
                        <div class="col-md-6"><input class="form-control" style="margin-top: 7px" id="pre_alat_bantu">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-6" style="margin-top: 7px">Apakah anda mempunyai riwayat alergi ?</label>
                        <div class="col-md-6"><input class="form-control" style="margin-top: 7px" id="pre_alergi"></div>
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Rekam Medic Pasien</h4>
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

<!-- /.content-wrapper -->
<script type='text/javascript'>
    $(document).ready(function () {
        $('#pendaftaran').addClass('active');

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

        initlistPenjamin();
    });

    function setFormAdmisi() {
        $('#modal-admisi').modal({show: true, backdrop: 'static'});
        var dataAdmisi = $('#data_admisi').val();
        if (json != null) {
            var json = JSON.parse(dataAdmisi);
            $('#pre_keyakinan').val(json.keyakinan);
            $('#pre_penerjemah').val(json.penerjemah);
            $('#pre_indra').val(json.indra);
            $('#pre_kontak').val(json.kontak);
            $('#pre_alat_bantu').val(json.alatBantu);
            $('#pre_alergi').val(json.alergi);
        }
    }

    function saveFormAdmisi() {

        var data = "";
        var keyakinan = $('#pre_keyakinan').val();
        var penerjemah = $('#pre_penerjemah').val();
        var indra = $('#pre_indra').val();
        var kontak = $('#pre_kontak').val();
        var alatBantu = $('#pre_alat_bantu').val();
        var alergi = $('#pre_alergi').val();

        data = {
            'keyakinan': keyakinan,
            'penerjemah': penerjemah,
            'indra': indra,
            'kontak': kontak,
            'alatBantu': alatBantu,
            'alergi': alergi
        };

        var stringJson = JSON.stringify(data);
        $('#data_admisi').val(stringJson);
        var dataAdmisi = $('#data_admisi').val();
        var json = JSON.parse(dataAdmisi);

        if (keyakinan != '' && penerjemah != '' && indra != '' && kontak != '' && alatBantu != '' && alergi != '') {
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
            if (perujuk == 'puskesmas') {
                jenisRujukan = "P";
            }else if (perujuk == 'rs') {
                jenisRujukan = "R";
            }

            $('#btn-cek-rujukan').html('<i class="fa fa-circle-o-notch fa-spin"></i> Loading...')
            dwr.engine.setAsync(true);
            CheckupAction.checkSuratRujukan(noRujukan, "R", {
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
                        msg = response.message;
                    }else{
                        val = "tidak ditemukan";
                        icon = "fa-warning";
                        title = "Warning!";
                        warnClass = "alert-warning";
                        msg = response.message;
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
        var url_string = window.location.href;
        var url = new URL(url_string);
        var tipe = url.searchParams.get("tipe");

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

    function initRekamMedic() {
      console.log("initRekamMedic ==> klik");
        var idPasien = $("#id_pasien").val();
        var table = "";
        var namaPasien = "";

        CheckupAction.listRekamMedic(idPasien, function (response) {
            // console.log(response);
            $.each(response, function (i, item) {
                var noCheckup = "";
                var dignosa = "";
                var tanggal = "";
                var dateFormatMasuk = "";
                var dateFormatKeluar = "";

                if(item.noCheckup != null){
                    noCheckup = item.noCheckup;
                }
                if(item.diagnosa != null){
                    dignosa = item.diagnosa;
                }
                if(item.stTglMasuk != null && item.stTglKeluar != null){
                    tanggalMasuk = item.stTglMasuk;
                    tanggalKeluar = item.stTglKeluar;
                    dateFormatMasuk = $.datepicker.formatDate('dd-mm-yy', new Date(tanggalMasuk));
                    dateFormatKeluar = $.datepicker.formatDate('dd-mm-yy', new Date(tanggalKeluar));
                }
                table += "<tr>" +
                        "<td>" + noCheckup + "</td>" +
                        "<td>" + dignosa + "</td>" +
                        "<td align='center'>" + dateFormatMasuk + "</td>" +
                        "<td align='center'>" + dateFormatKeluar + "</td>" +
                        "<td align='center'><button class=\"btn btn-primary\" onclick=\"viewDetailRekamMedic('"+item.noCheckup+"')\">View</button></td>" +
                        "</tr>";

                namaPasien = item.namaPasien;
            });

            $("#modal-rekam-medic").modal('show');
            $('#nama_medik').html(namaPasien);
            $("#body-rekam-medic").html(table);
        });
    }

    function viewDetailRekamMedic(noCheckup){
      console.log("viewDetailRekamMedic ==> klik");
      var urlImg = $("#img_ktp").val();
      <%--var surl = "<s:url value='/pages/images/unknown-person.png'>";--%>
      var str = "";
      CheckupAction.getDataCheckupPasien(noCheckup, function(response){
        var item = response;
        str = "<p style='font-weight:bold'>"+item.namaPasien+"</p><div class=\"row\">"+
              "<div class='col-md-3'><img src='/simrs/pages/images/nobody_m.original.jpg' style='width:100%;max-height:150px'/></div>"+
              "<div class='col-md-6'>"+
              "<table class='table table-bordered table-striped' style='font-size:12px'><tbody>"+
              "<tr><td>Diagnosa terakhir :</td><td>"+item.diagnosa+"</td></tr>"+
              "<tr><td>Tanggal Masuk :</td><td>"+item.stTglMasuk+"</td></tr>"+
              "<tr><td>Tiagnosa Keluar :</td><td>"+item.stTglKeluar+"</td></tr>"+
              "</tbody></table>"+
              "</div>"+
              "</div>";
        $("#list-body-rekam-medic").html("");
//        $("#tab_1").attr("class","active");
        $("#head-detail-rm").html("");
        $("#head-detail-rm").html(str);
      });

      $("#rm-no-checkup").val(noCheckup);
      console.log(noCheckup);
      $("#modal-detail-rekam-medic").modal("show");
    }

    function viewDetailRekamMedicByKategori(kategori){
      var noCheckup =   $("#rm-no-checkup").val();
      if (kategori == "ri") {
        CheckupAction.getListKategoriSkorRanapByHead(kategori, function (response) {
          // console.log(response);
          var top = "";
          var str = "";
          var btn = "";
          var bottom = "";
          $.each(response, function(i, item) {
              top = "<div class='row' style='margin-top:10px'>"+
                    "<div class='col-md-8'>"+item.namaKategori+"</div>";
                    if (item.type == "skor") {
                      btn = "<div class='col-md-4 pull-right'><button class=\"btn btn-primary\" onclick=\"showSkorRanapRm(this.id, '"+noCheckup+"','"+item.idKategori+"','skor')\" id=\"mon-rm-"+i+"\"> View</button></div>";
                    } else {
                      btn = "<div class='col-md-4 pull-right'><button class=\"btn btn-primary\" onclick=\"showSkorRanapRm(this.id, '"+noCheckup+"','"+item.idKategori+"','asesmen')\" id=\"mon-rm-"+i+"\"> View</button></div>";
                    }
                    bottom = "</div>"+
                            "<div id=\"body-mon-rm-"+i+"\"></div>";
              str += top+btn+bottom;
          });
          $("#list-body-rekam-medic").html("");
          $("#list-body-rekam-medic").html(str);
        });
      }
      else {
        $("#list-body-rekam-medic").html("");
        var par = [];
        var str = "";
        if (kategori == "mon") {

          par.push({'label': "Observasi Cairan", 'kat': "cairan"},
                   {'label': "Observasi Vital Sign", 'kat':"vitalsign"},
                   {'label': "Observasi pemberian obat parenteral", 'kat':"parenteral"},
                   {'label': "Observasi pemberian obat nonparenteral", 'kat':"nonparenteral"}
                  );
          $.each(par, function(i, item) {
            str += "<div class='row' style='margin-top:10px'>"+
                    "<div class='col-md-8'>"+item.label+"</div>"+
                    "<div class='col-md-4 pull-right'><button class=\"btn btn-primary\" onclick=\"showDetailMonitoringRm(this.id, '"+noCheckup+"','"+item.kat+"')\" id=\"mon-rm-"+i+"\"> View</button></div>"+
                    "</div>"+
                    "<div id=\"graf-mon-rm-"+i+"\"></div>"+
                    "<div id=\"body-mon-rm-"+i+"\"></div>";
          });
          $("#list-body-rekam-medic").html(str);
        }
        else if (kategori == "igd") {

          par.push({'label': "Pemeriksaan Fisik", 'kat': "fisik"},
                   {'label': "Psikosial", 'kat':"psikososial"},
                   {'label': "Rencana Keperawatan", 'kat':"rencana"},
                   {'label': "Resiko Jatuh", 'kat':"resikojatuh"},
                   {'label': "Rekonsiliasi Obat", 'kat':"rekonsiliasi"}
                  );

          $.each(par, function(i, item) {
            str += "<div class='row' style='margin-top:10px'>"+
                    "<div class='col-md-8'>"+item.label+"</div>"+
                    "<div class='col-md-4 pull-right'><button class=\"btn btn-primary\" onclick=\"showDetailIgdRm(this.id, '"+noCheckup+"','"+item.kat+"')\" id=\"mon-rm-"+i+"\"> View</button></div>"+
                    "</div>"+
                    "<div id=\"body-mon-rm-"+i+"\"></div>";
          });
          $("#list-body-rekam-medic").html(str);
        }
        else {

        }
      }
    }

    function showDetailMonitoringRm(id, noCheckup, kategori){
      console.log("showDetailMonitoringRm ==> klik");
      console.log(id+"-"+noCheckup+"-"+kategori);

      $("#"+id+"").hide();

      var str = "";
      var headstr = "";
      var tablehead = "<table class='table table-bordered table-striped' style='font-size:11px;margin-top:10px;'>";
      var tabledown = "</table><hr/>";
      var upthead = "<thead style='font-weight: bold;'>";
      var downthead = "</thead>";
      var uptbody = "<tbody>";
      var downtbody = "</tbody>";
      if (kategori == "cairan") {
        RawatInapAction.getListMonCairan(noCheckup, "", "", function(response){
          console.log(response);
          if (response.length > 0) {

            strhead = upthead +
                      "<tr>"+
                      "<td>Created Who</td>"+
                      "<td>tgl</td>"+
                      "<td>Macam Cairan</td>"+
                      "<td>Melalui</td>"+
                      "<td>jumlah</td>"+
                      "<td>Jam Mulai</td>"+
                      "<td>Jam Selesai</td>"+
                      "<td>Cek Tambahan Obat</td>"+
                      "<td>Sisa</td>"+
                      "<td>Jam Ukur Buang</td>"+
                      "<td>Dari</td>"+
                      "<td>Balance Cairan</td>"+
                      "<td>Keterangan</td>"+
                      "</tr>" + downthead;

            $.each(response, function(i, item) {
              str += "<tr>"+
                    "<td>"+item.createdWho+"</td>"+
                    "<td>"+item.stDate+"</td>"+
                    "<td>"+item.macamCairan+"</td>"+
                    "<td>"+item.melalui+"</td>"+
                    "<td>"+item.jumlah+"</td>"+
                    "<td>"+item.jamMulai+"</td>"+
                    "<td>"+item.jamSelesai+"</td>"+
                    "<td>"+item.cekTambahanObat+"</td>"+
                    "<td>"+item.sisa+"</td>"+
                    "<td>"+item.jamUkurBuang+"</td>"+
                    "<td>"+item.dari+"</td>"+
                    "<td>"+item.balanceCairan+"</td>"+
                    "<td>"+item.keterangan+"</td>"+
                    "</tr>";
            });
            $("#body-"+id+"").html(tablehead+strhead+uptbody+str+downtbody+tabledown);
          } else {
              $("#"+id+"").show();
              $("#"+id+"").removeAttr("class");
              $("#"+id+"").removeAttr("onclick");
              $("#"+id+"").attr("class", "btn btn-secondary");
              $("#"+id+"").html("Tidak Ada Rekam Medic");
          }
        });
      }

      if (kategori == "vitalsign") {
        RawatInapAction.getListMonVitalSign(noCheckup, "", "", function(response){
          console.log(response);
          if (response.length > 0) {
            strhead = upthead +
                      "<tr>"+
                      "<td>Created Who</td>"+
                      "<td>Created Date</td>"+
                      "<td>Jam</td>"+
                      "<td>Nafas</td>"+
                      "<td>Nadi</td>"+
                      "<td>Suhu</td>"+
                      "<td>Tensi</td>"+
                      "<td>Berat Badan (Kg)</td>"+
                      "<td>Tinggi Badan (cm)</td>"+
                      "</tr>" + downthead;

            $.each(response, function(i, item) {
              str += "<tr>"+
                    "<td>"+item.createdWho+"</td>"+
                    "<td>"+item.stDate+"</td>"+
                    "<td>"+item.jam+"</td>"+
                    "<td>"+item.nafas+"</td>"+
                    "<td>"+item.nadi+"</td>"+
                    "<td>"+item.suhu+"</td>"+
                    "<td>"+item.tensi+"</td>"+
                    "<td>"+item.bb+"</td>"+
                    "<td>"+item.tb+"</td>"+
                    "</tr>";
            });

            $("#body-"+id+"").html(tablehead+strhead+uptbody+str+downtbody+tabledown);
            $("#graf-"+id+"").html('<div id="line-chart" style="height:300px;"></div>'+
                                    '<p style="margin-top:50px;"><i class="fa fa-circle" style="color:#3a4dc9"></i> Suhu  '+
                                    '<i class="fa fa-circle" style="color:#eb4034"></i> Nadi  '+
                                    '<i class="fa fa-circle" style="color:#6b6b6b"></i> Nafas</p>');

              var suhu = [], nadi = [], nafas = [], label = [];
              RawatInapAction.getListGraf("", noCheckup, function(grafs){
                console.log(grafs)
                $.each(grafs, function(i, item){
                  suhu.push([i,item.suhu]);
                  nadi.push([i,item.nadi]);
                  nafas.push([i,item.nafas]);
                  label.push([i,item.stDate]);
                });

                /*
               * LINE CHART
               * ----------
               */
              var line_data1 = { data : suhu, color: '#3a4dc9' }
              var line_data2 = { data : nadi, color: '#eb4034' }
              var line_data3 = { data : nafas, color: '#6b6b6b' }

              $.plot("#line-chart", [line_data1, line_data2, line_data3], {
                grid  : { hoverable  : true, borderColor: '#f3f3f3', borderWidth: 1, tickColor  : '#f3f3f3'},
                series: { shadowSize: 0, lines : { show: true }, points : { show: true } },
                lines : { fill : false, color: ['#3c8dbc', '#f56954'] },
                yaxis : { show: true },
                xaxis : { show: true, ticks: label }
              })
              //Initialize tooltip on hover
              $('<div class="tooltip-inner" id="line-chart-tooltip"></div>').css({
                position: 'absolute',
                display : 'none',
                opacity : 0.8
              }).appendTo('body')
              $("#line-chart").bind('plothover', function (event, pos, item) {

                if (item) {
                  var x = item.datapoint[0].toFixed(2),
                      y = item.datapoint[1].toFixed(2)

                  $('#line-chart-tooltip').html(parseInt(y))
                    .css({ top: item.pageY + 5, left: item.pageX + 5})
                    .fadeIn(200)
                } else {
                  $('#line-chart-tooltip').hide()
                }
              })
            /* END LINE CHART */
            });
          } else {
              $("#"+id+"").show();
              $("#"+id+"").removeAttr("class");
              $("#"+id+"").removeAttr("onclick");
              $("#"+id+"").attr("class", "btn btn-secondary");
              $("#"+id+"").html("Tidak Ada Rekam Medic");

          }
        });
      } if (kategori == "parenteral") {
        RawatInapAction.getListMonPemberianObat(noCheckup, "", kategori, "",  function(response){
          console.log(response);
          if (response.length > 0) {
            strhead = upthead +
                      "<tr>"+
                      "<td>Created Who</td>"+
                      "<td>Created Date</td>"+
                      "<td>Nama Obat</td>"+
                      "<td>Cara Pemberian</td>"+
                      "<td>Dosis</td>"+
                      "<td>Skin Tes</td>"+
                      "<td>Waktu</td>"+
                      "<td>Keterangan</td>"+
                      "</tr>" + downthead;

            $.each(response, function(i, item){
                str += "<tr>"+
                      "<td>"+item.createdWho+"</td>"+
                      "<td>"+item.stDate+"</td>"+
                      "<td>"+item.namaObat+"</td>"+
                      "<td>"+item.caraPemberian+"</td>"+
                      "<td>"+item.dosis+"</td>"+
                      "<td>"+item.skinTes+"</td>"+
                      "<td>"+item.waktu+"</td>"+
                      "<td>"+item.keterangan+"</td>"+
                      "</tr>";
          });
          $("#body-"+id+"").html(tablehead+strhead+uptbody+str+downtbody+tabledown);
        } else {
              $("#"+id+"").show();
              $("#"+id+"").removeAttr("class");
              $("#"+id+"").removeAttr("onclick");
              $("#"+id+"").attr("class", "btn btn-secondary");
              $("#"+id+"").html("Tidak Ada Rekam Medic");
          }
        });
      }
      if (kategori == "nonparenteral") {
        RawatInapAction.getListMonPemberianObat(noCheckup, "", kategori, "",  function(response){
          console.log(response);
          if (response.length > 0) {
            strhead = upthead +
                      "<tr>"+
                      "<td>Created Who</td>"+
                      "<td>Created Date</td>"+
                      "<td>Nama Obat</td>"+
                      "<td>Dosis</td>"+
                      "<td>Waktu</td>"+
                      "<td>Keterangan</td>"+
                      "</tr>" + downthead;

            $.each(response, function(i, item){
                str += "<tr>"+
                      "<td>"+item.createdWho+"</td>"+
                      "<td>"+item.stDate+"</td>"+
                      "<td>"+item.namaObat+"</td>"+
                      "<td>"+item.dosis+"</td>"+
                      "<td>"+item.waktu+"</td>"+
                      "<td>"+item.keterangan+"</td>"+
                      "</tr>";
            });
            $("#body-"+id+"").html(tablehead+strhead+uptbody+str+downtbody+tabledown);
        } else {
              $("#"+id+"").show();
              $("#"+id+"").removeAttr("class");
              $("#"+id+"").removeAttr("onclick");
              $("#"+id+"").attr("class", "btn btn-secondary");
              $("#"+id+"").html("Tidak Ada Rekam Medic");
          }
      });
    }
    }

    function showSkorRanapRm(id, noCheckup, idKategori, type){
      console.log("showDetailMonitoringRm ==> "+id+"-"+noCheckup+"-"+idKategori+"-"+type);
      $("#"+id+"").hide();

      var str = "";
      var table = "";
      var headstr = "";
      var tablehead = "<table class='table table-bordered table-striped' style='font-size:11px;margin-top:10px;'>";
      var tabledown = "</table>";
      var upthead = "<thead style='font-weight: bold;'>";
      var downthead = "</thead>";
      var uptbody = "<tbody>";
      var downtbody = "</tbody>";
      RawatInapAction.getListGroupSkorRanap(noCheckup, "", idKategori, function(response){

        if (response.length > 0) {
          if (type == "skor"){
            strhead = upthead +
                      "<tr>"+
                      "<td>Created Who</td>"+
                      "<td>Created Date</td>"+
                      "<td>Asesmen</td>"+
                      "<td>Total Skor</td>"+
                      "<td>View Detail</td>"+
                      "</tr>" + downthead;

            $.each(response, function(i, item){
                str = "<tr>"+
                      "<td>"+item.createdWho+"</td>"+
                      "<td>"+item.stDate+"</td>"+
                      "<td>"+item.namaKategori+"</td>"+
                      "<td>"+item.skor+"</td>"+
                      "<td align=\"center\"><button id=\""+idKategori+"-"+i+"\" class='btn btn-primary' onclick=\"viewDetailSkorRm('"+item.groupId+"', '"+type+"', this.id)\"><i class=\"fa fa-search\"></i></button></td>"+
                      "</tr>";

                table += tablehead+strhead+uptbody+str+downtbody+tabledown+"<div id=\"body-"+idKategori+"-"+i+"\"></div>";
            });
          } else {
            strhead = upthead +
                      "<tr>"+
                      "<td>Created Who</td>"+
                      "<td>Created Date</td>"+
                      "<td>Asesmen</td>"+
                      "<td>View Detail</td>"+
                      "</tr>" + downthead;

            $.each(response, function(i, item){
                str = "<tr>"+
                      "<td>"+item.createdWho+"</td>"+
                      "<td>"+item.stDate+"</td>"+
                      "<td>"+item.namaKategori+"</td>"+
                      "<td align=\"center\"><button id=\""+idKategori+"-"+i+"\" class='btn btn-primary' onclick=\"viewDetailSkorRm('"+item.groupId+"', '"+type+"', this.id)\"><i class=\"fa fa-search\"></i></button></td>"+
                      "</tr>";

                table += tablehead+strhead+uptbody+str+downtbody+tabledown+"<div id=\"body-"+idKategori+"-"+i+"\"></div>";
            });
          }
          $("#body-"+id+"").html(table+"<hr/>");
      } else {
            $("#"+id+"").show();
            $("#"+id+"").removeAttr("class");
            $("#"+id+"").removeAttr("onclick");
            $("#"+id+"").attr("class", "btn btn-secondary");
            $("#"+id+"").html("Tidak Ada Rekam Medic");
        }
        // console.log(response);
      });
    }

    function viewDetailSkorRm(groupId, type, id){
      console.log("viewDetailSkorRm ==> "+groupId+"-"+type+"-"+id);
      $("#"+id+"").removeAttr("class");
      $("#"+id+"").removeAttr("onclick");
      $("#"+id+"").attr("class","btn btn-secondary");

      var tablehead = "<p>Detail :</p><table class='table table-bordered table-striped' style='font-size:11px;margin-top:10px;'>";
      var tabledown = "</table>";
      var upthead = "<thead style='font-weight: bold;'>";
      var downthead = "</thead>";
      var uptbody = "<tbody>";
      var downtbody = "</tbody>";
      RawatInapAction.getListViewSkorRanapByGrupId(groupId, function(response){
        console.log(response);
        var str = "";
        if (response.length > 0) {
          if (type == "skor") {
            $.each(response, function(i,item){
              str += "<tr>"+
                    "<td>"+item.namaParameter+"</td>"+
                    "<td>"+item.skor+"</td>"+
                    "<td>"+item.keterangan+"</td>"+
                    "</tr>";
            });
          } else {
            $.each(response, function(i,item){
              str += "<tr>"+
                    "<td>"+item.namaParameter+"</td>"+
                    "<td>"+item.keterangan+"</td>"+
                    "</tr>";
            });
          }
          $("#body-"+id+"").html(tablehead+uptbody+str+downtbody+tabledown);
        }
      });
    }

    function showDetailIgdRm(id, noCheckup, kategori){
      console.log("showDetailIgdRm ==> "+id+" | "+noCheckup+" | "+kategori);
      $("#"+id+"").hide();
      var head = "";
      var isi = [];
      var str = "";
      var tbodyhead = "<table class='table table-bordered table-striped' style='font-size:11px;margin-top:10px;'><tbody>";
      var tbodybottom = "</tbody></table>";
      if (kategori == "fisik"){
        CheckupAction.getPemeriksaanFisikByNoCheckup(noCheckup, function(response){
          // console.log(response);
            if(response.id != null){

                head = "<table class='table table-bordered table-striped' width='60%' style='font-size:11px;margin-top:10px;'><tbody>"+
                          "<tr><td>Created Who</td><td>"+response.createdWho+"</td></tr>"+
                          "<tr><td>Created Date</td><td>"+response.createdDate+"</td></tr>"+
                          "</tbody></table>";

                isi.push(
                  {"label": "Tinggi Badan (cm)", "nilai":response.tinggiBadan },
                  {"label": "Berat Badan (Kg)", "nilai":response.beratBadan },
                  {"label": "Nadi", "nilai":response.nadi },
                  {"label": "Respiration Rate", "nilai":response.respirationRate },
                  {"label": "Tekanan Darah", "nilai":response.tekananDarah },
                  {"label": "Suhu ", "nilai":response.suhu },
                  {"label": "Kepala", "nilai":response.kepala },
                  {"label": "Mata", "nilai":response.mata },
                  {"label": "Leher", "nilai":response.leher },
                  {"label": "Thorak", "nilai":response.thorak },
                  {"label": "Thorak Chor", "nilai":response.thorakChor },
                  {"label": "Thorak Pulmo", "nilai":response.thorakPulmo },
                  {"label": "Abdoman", "nilai":response.abdoman },
                  {"label": "Extremitas", "nilai":response.extrimitas },
                );


                $.each(isi,function(i, item) {
                  str += "<tr><td>"+item.label+"</td><td>"+item.nilai+"</td></tr>";
                });
                $("#body-"+id+"").html(head+tbodyhead+str+tbodybottom+"<hr/>");
            } else {
                $("#"+id+"").show();
                $("#"+id+"").removeAttr("class");
                $("#"+id+"").removeAttr("onclick");
                $("#"+id+"").attr("class", "btn btn-secondary");
                $("#"+id+"").html("Tidak Ada Rekam Medic");
            }
        });
      }
      if (kategori == "psikososial"){
        CheckupAction.getPsikososial(noCheckup, function (response) {
            if(response.id != null){
                head = "<table class='table table-bordered table-striped' width='60%' style='font-size:11px;margin-top:10px;'><tbody>"+
                          "<tr><td>Created Who</td><td>"+response.createdWho+"</td></tr>"+
                          "<tr><td>Created Date</td><td>"+response.createdDate+"</td></tr>"+
                          "</tbody></table>";

                isi.push(
                  {"label": "Komunikasi", "nilai":response.komunikasi },
                  {"label": "Kemampuan Bicara", "nilai":response.kemampuanBicara },
                  {"label": "Konsep Diri", "nilai":response.konsepDiri },
                  {"label": "Pernah Dirawat", "nilai":response.pernahDirawat },
                  {"label": "Tahu Tentang SakitNya", "nilai":response.tahuTentangSakitNya },
                  {"label": "Obat Dari Rumah ", "nilai":response.obatDariRumah },
                  {"label": "Nyeri", "nilai":response.nyeri },
                  {"label": "Intensitas Nyeri", "nilai":response.intensitasNyeri },
                  {"label": "Jenis Intensitas Nyeri", "nilai":response.jenisIntensitasNyeri },
                  {"label": "Numeric Rating Scale", "nilai":response.numericRatingScale },
                  {"label": "Wong Baker Pain Scale", "nilai":response.wongBakerPainScale }
              );

              $.each(isi,function(i, item) {
                str += "<tr><td>"+item.label+"</td><td>"+item.nilai+"</td></tr>";
              });
              $("#body-"+id+"").html(head+tbodyhead+str+tbodybottom+"<hr/>");
            } else {
                $("#"+id+"").show();
                $("#"+id+"").removeAttr("class");
                $("#"+id+"").removeAttr("onclick");
                $("#"+id+"").attr("class", "btn btn-secondary");
                $("#"+id+"").html("Tidak Ada Rekam Medic");
            }
        });
      }
      if (kategori == "resikojatuh"){
        CheckupAction.getListResikoJatuh(noCheckup, "", function(response){
          console.log(response);
          if (response.status == "success"){
              if (response.resikoJatuhEntityList[0].id != null){
                  var createdDate = "";
                  var createdWho = "";
                  var sumSkor = 0;

                  $.each(response.resikoJatuhEntityList, function (i, item) {
                      str += "<tr><td>"+item.namaParameter+"</td><td>"+item.skor+"</td></tr>";
                      sumSkor += item.skor;
                      createdWho  = item.createdWho;
                      createdDate = item.createdDate;
                  });

                  head = "<table class='table table-bordered table-striped' width='60%' style='font-size:11px;margin-top:10px;'><tbody>"+
                      "<tr><td>Created Who</td><td>"+createdWho+"</td></tr>"+
                      "<tr><td>Created Date</td><td>"+createdDate+"</td></tr>"+
                      "</tbody></table>";

                  str += "<tr style='font-weight:bold;'><td>Total Skor</td><td>"+sumSkor+"</td></tr>";
                  $("#body-"+id+"").html(head+tbodyhead+str+tbodybottom+"<hr/>");
              } else {
                  $("#"+id+"").show();
                  $("#"+id+"").removeAttr("class");
                  $("#"+id+"").removeAttr("onclick");
                  $("#"+id+"").attr("class", "btn btn-secondary");
                  $("#"+id+"").html("Tidak Ada Rekam Medic");
              }
          } else {
              $("#"+id+"").show();
              $("#"+id+"").removeAttr("class");
              $("#"+id+"").removeAttr("onclick");
              $("#"+id+"").attr("class", "btn btn-secondary");
              $("#"+id+"").html("Tidak Ada Rekam Medic");
          }
        });
      }
      if (kategori == "rencana") {
          CheckupAction.getListRencanaRawat(noCheckup, "", "rigd", function(response){
            console.log(response);
            if (response[0].idRencana != null) {

              $.each(response, function (i, item) {
                var upline = "<tr><td>"+item.namaParameter+"</td><td align='center'>";
                var opt = "";
                  if (item.check == "Y") {
                    opt = "<i class='fa fa-check'></i>"
                  }
                var downline = "</td></tr>";
                str += upline+opt+downline;
                createdWho  = item.createdWho;
                createdDate = item.createdDate;
              });

              head = "<table class='table table-bordered table-striped' width='60%' style='font-size:11px;margin-top:10px;'><tbody>"+
                        "<tr><td>Created Who</td><td>"+createdWho+"</td></tr>"+
                        "<tr><td>Created Date</td><td>"+createdDate+"</td></tr>"+
                        "</tbody></table>";

              $("#body-"+id+"").html(head+tbodyhead+str+tbodybottom+"<hr/>");
            } else {
                $("#"+id+"").show();
                $("#"+id+"").removeAttr("class");
                $("#"+id+"").removeAttr("onclick");
                $("#"+id+"").attr("class", "btn btn-secondary");
                $("#"+id+"").html("Tidak Ada Rekam Medic");
            }
          });
      }
      if (kategori == "rekonsiliasi") {
        CheckupAction.getListRekonsiliasiObat(noCheckup, function (response) {
          if (response.length > 0) {
            head = "<table class='table table-bordered table-striped' style='font-size:11px;margin-top:10px;'>"+
                  "<thead>"+
                  "<tr>"+
                  "<td>Nama Obat</td>"+
                  "<td>Bentuk</td>"+
                  "<td>Dosis</td>"+
                  "<td>Frekuensi</td>"+
                  "<td>Rute</td>"+
                  "<td>Permintaan Obat di Berikan Saat Masuk</td>"+
                  "<td>Obat Dari Rumah Dilanjutkan Saat Pulang</td>"+
                  "</tr></thead>";

            str += "<tbody>";
            $.each(response, function(i,item){
              str += "<tr>"+
                    "<td>"+item.namaObat+"</td>"+
                    "<td>"+item.bentuk+"</td>"+
                    "<td>"+item.dosis+" "+item.satuanDosis+"</td>"+
                    "<td>"+item.frekuensi+"</td>"+
                    "<td>"+item.rute+"</td>"+
                    "<td align='center'>"+convertToCheck(item.obatMasukFlag)+"</td>"+
                    "<td align='center'>"+convertToCheck(item.obatDariRumahFlag)+"</td>"+
                    "</tr>";
            });
            str += "</tbody></table>";
            $("#body-"+id+"").html(head+str);
          } else {
              $("#"+id+"").show();
              $("#"+id+"").removeAttr("class");
              $("#"+id+"").removeAttr("onclick");
              $("#"+id+"").attr("class", "btn btn-secondary");
              $("#"+id+"").html("Tidak Ada Rekam Medic");
          }
        });
      }
    }

    function convertToCheck(item){
      if (item == "Y") {
        return "<i class='fa fa-check'></i>";
      }
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

    var listAlergi = [];
    function addAlergi(alergi) {
        listAlergi.push({"alergi": alergi});
    }
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>
