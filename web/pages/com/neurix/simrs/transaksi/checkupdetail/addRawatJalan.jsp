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
        .paint-canvas {
            border: 1px black solid;
            display: block;
            margin: 1rem;
        }

        .color-picker {
            margin: 1rem 1rem 0 1rem;
        }
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
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatInapAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanResepAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatPoliAction.js"/>'></script>

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

        function convertSentenceCase(myString){
            if(myString != null && myString != ''){
                var rg = /(^\w{1}|\ \s*\w{1})/gi;
                myString = myString.replace(rg, function(toReplace) {
                    return toReplace.toUpperCase();
                });
                return myString;
            }else{
                return "";
            }
        }

    </script>
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
            content:'';
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
            Rawat Jalan Pasien
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
                                    <s:hidden id="id_asuransi" name="headerDetailCheckup.idAsuransi"/>
                                    <s:hidden id="nama_asuransi" name="headerDetailCheckup.namaAsuransi"/>

                                    <s:hidden id="no_rujukan" name="headerDetailCheckup.noRujukan"/>
                                    <s:hidden id="tgl_rujukan" name="headerDetailCheckup.tglRujukan"/>
                                    <s:hidden id="surat_rujukan" name="headerDetailCheckup.suratRujukan"/>
                                    <s:hidden id="is_laka" name="headerDetailCheckup.isLaka"/>

                                    <s:if test='headerDetailCheckup.idJenisPeriksaPasien == "bpjs" || headerDetailCheckup.idJenisPeriksaPasien == "ptpn"'>
                                        <tr>
                                            <td width="45%"><b>No SEP</b></td>
                                            <td style="vertical-align: middle;">
                                                <table>
                                                    <s:label cssClass="label label-success" name="headerDetailCheckup.noSep"></s:label>
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
                                    <s:if test='headerDetailCheckup.metodePembayaran != null && headerDetailCheckup.metodePembayaran != ""'>
                                        <tr>
                                            <td><b>Metode Pembayaran</b></td>
                                            <td>
                                                <table>
                                                    <script>
                                                        var metode = '<s:property value="headerDetailCheckup.metodePembayaran"/>';
                                                        var met = metode.replace("_", " ");
                                                        var meto = convertSentenceCase(met);
                                                        document.write(meto);
                                                    </script>
                                                </table>
                                            </td>
                                        </tr>
                                    </s:if>
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <div style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px; text-align: center"
                                     class="card card-4 pull-right">
                                    <img border="2" id="img_ktp" src="<s:property value="headerDetailCheckup.urlKtp"/>"
                                         style="cursor: pointer; height: 90px; width: 190px; margin-top: 4px">
                                </div>
                                <%--<img border="2" class="card card-4 pull-right" src="<s:url value="/pages/images/ktp-tes.jpg"/>"--%>
                                <%--style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px;">--%>
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
                                    <s:if test='headerDetailCheckup.idJenisPeriksaPasien == "paket_perusahaan" || headerDetailCheckup.idJenisPeriksaPasien == "paket_individu"'>
                                        <tr>
                                            <td><b>Tarif Paket</b></td>
                                            <td>
                                                <table>
                                                    <script>
                                                        var tar = '<s:property value="headerDetailCheckup.coverBiaya"/>';
                                                        if(tar != null){
                                                            document.write("Rp. "+formatRupiah(tar));
                                                        }
                                                    </script>
                                                </table>
                                            </td>
                                        </tr>
                                    </s:if>
                                    <s:if test='headerDetailCheckup.idJenisPeriksaPasien == "asuransi"'>
                                        <tr>
                                            <td><b>Nama Asuransi</b></td>
                                            <td>
                                                <table>
                                                    <s:label id="nama_asuransi"
                                                             name="headerDetailCheckup.namaAsuransi"></s:label>
                                                </table>
                                            </td>
                                        </tr>
                                    </s:if>
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
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-12">
                                    <a href="<%= request.getContextPath() %>/rekammedik/initRekamMedik_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>&tipe=RJ" class="btn btn-primary pull-left"><i class="fa fa-user-plus"></i> E-Rekam Medik</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border" id="pos_alergi">
                    </div>

                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-user"></i> Tinggi & Berat Badan</h3>
                            </div>
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-heartbeat"></i> Hasil BMI (Body Mass Index)</h3>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_penunjang">
                                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                                    Silahkan cek kembali data inputan!
                                </div>
                                <div class="alert alert-success alert-dismissible" style="display: none" id="success_penunjang">
                                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                                    Data berhasil disimpan!
                                </div>
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label>Tinggi</label>
                                            <div class="input-group date">
                                                <s:textfield id="tinggi" name="headerDetailCheckup.tinggi"
                                                             cssClass="form-control" type="number"/>
                                                <div class="input-group-addon">
                                                    cm
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label>Berat</label>
                                            <div class="input-group date">
                                                <s:textfield id="berat" name="headerDetailCheckup.berat"
                                                             cssClass="form-control" type="number"/>
                                                <div class="input-group-addon">
                                                    Kg
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>&nbsp</label>
                                            <div>
                                                <button id="save_penunjang" onclick="savePenunjangPasien()"
                                                        class="btn btn-success"><i
                                                        class="fa fa-check"></i>
                                                    Save
                                                </button>
                                                <button style="display: none; cursor: no-drop" type="button"
                                                        class="btn btn-success"
                                                        id="load_penunjang">
                                                    <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div style="padding-top: 15px">
                                    <div class="progress">
                                        <div id="bar_bmi"></div>
                                    </div>
                                </div>
                                <%--<small>--%>
                                    <%--<ul style="list-style-type: none;">--%>
                                        <%--<li style="float: left"><i class="fa fa-square" style="color: #5bc0de"></i> Kurus berat--%>
                                        <%--</li>--%>
                                        <%--<li style="float: left; padding-left: 10px"><i class="fa fa-square" style="color: #337ab7"></i> Kurus ringan--%>
                                        <%--</li>--%>
                                        <%--<li style="float: left; padding-left: 10px"><i class="fa fa-square" style="color: #5cb85c"></i> Normal </li>--%>
                                        <%--<li style="float: left; padding-left: 10px"><i class="fa fa-square" style="color: #f0ad4e"></i> Gemuk ringan  </li>--%>
                                        <%--<li style="float: left; padding-left: 10px"><i class="fa fa-square" style="color: #d9534f"></i> Gemuk berat    </li>--%>
                                    <%--</ul>--%>
                                <%--</small>--%>
                                <small>
                                    <ul style="list-style-type: none;">
                                        <li style="float: left; padding-left: 10px"><i class="fa fa-square" style="color: #337ab7"></i> Berat badan kurang
                                        </li>
                                        <li style="float: left; padding-left: 10px"><i class="fa fa-square" style="color: #5cb85c"></i> Berat badan normal </li>
                                        <li style="float: left; padding-left: 10px"><i class="fa fa-square" style="color: #f0ad4e"></i> Berat badan berlebih  </li>
                                        <li style="float: left; padding-left: 10px"><i class="fa fa-square" style="color: #d9534f"></i> Obesitas  </li>
                                    </ul>
                                </small>
                            </div>
                        </div>
                    </div>

                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-user"></i> Alergi</h3>
                            </div>
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-user-md"></i> Dokter</h3>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6">
                                <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                        onclick="showModal(8)"><i class="fa fa-plus"></i> Tambah Alergi
                                </button>
                                <table class="table table-bordered table-striped">
                                    <thead>
                                    <tr bgcolor="#90ee90">
                                        <td>Alergi</td>
                                        <td align="center" width="20%">Action</td>
                                    </tr>
                                    </thead>
                                    <tbody id="body_alergi">
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-md-6">
                                <s:if test='headerDetailCheckup.idJenisPeriksaPasien != "paket_individu" && headerDetailCheckup.idJenisPeriksaPasien != "paket_perusahaan"'>
                                    <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                            onclick="showModal(1)"><i class="fa fa-plus"></i> Tambah Dokter
                                    </button>
                                </s:if>
                                <table class="table table-bordered table-striped">
                                    <thead>
                                    <tr bgcolor="#90ee90">
                                        <td>ID Dokter</td>
                                        <td>Nama</td>
                                        <%--<td>Spesialis</td>--%>
                                        <td align="center">Action</td>
                                    </tr>
                                    </thead>
                                    <tbody id="body_dokter">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <%--<div class="box-header with-border"></div>--%>
                    <%--<div class="box-header with-border">--%>
                    <%----%>
                    <%--</div>--%>
                    <%--<div class="box-body">--%>

                    <%--</div>--%>
                    <div class="box-header with-border" id="pos_nosa">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-stethoscope"></i> Diagnosa</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(3)"><i class="fa fa-plus"></i> Tambah Diagnosa
                        </button>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tanggal</td>
                                <td>ID Diagnosa</td>
                                <td>Keterangan</td>
                                <td>Jenis Diagnosa</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_diagnosa">

                            </tbody>
                        </table>
                    </div>

                    <div id="status_bpjs" style="display: none">
                        <div class="box-header with-border">
                        </div>
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-line-chart"></i> Status Biaya Tindakan</h3>
                        </div>
                        <div class="box-body">
                            <div class="row">
                                <div class="col-md-offset-2 col-md-8">
                                    <h5>
                                        Cover Biaya Bpjs dengan kode CBG <b id="kode_cbg"></b>
                                        <small class="pull-right" style="margin-top: 7px">Rp. <span id="b_bpjs"></span>
                                        </small>
                                    </h5>
                                    <div class="progress">
                                        <div id="sts_cover_biaya">
                                        </div>
                                    </div>
                                    <h5>
                                        Total Biaya Tindakan
                                        <small class="pull-right" style="margin-top: 7px">Rp. <span
                                                id="b_tindakan"></span></small>
                                    </h5>
                                    <div class="progress">
                                        <div id="sts_biaya_tindakan">
                                        </div>
                                    </div>
                                    <ul style="list-style-type: none">
                                        <li><i class="fa fa-square" style="color: #337ab7"></i> Total biaya cover Bpjs
                                        </li>
                                        <li><i class="fa fa-square" style="color: #5cb85c"></i> Total biaya tindakan <
                                            50% dari cover biaya Bpjs
                                        </li>
                                        <li><i class="fa fa-square" style="color: #f0ad4e"></i> Total biaya tindakan >
                                            50% dan < 70% dari cover biaya Bpjs
                                        </li>
                                        <li><i class="fa fa-square" style="color: #d9534f"></i> Total biaya tindakan >
                                            70% dari cover biaya Bpjs
                                        </li>
                                    </ul>
                                </div>
                                <div class="col-md-2">

                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="status_asuransi" style="display: none">
                        <div class="box-header with-border">
                        </div>
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-line-chart"></i> Status Biaya Tindakan</h3>
                        </div>
                        <div class="box-body">
                            <div class="row">
                                <div class="col-md-offset-2 col-md-8">
                                    <h5>
                                        Cover Biaya Asuransi
                                        <small class="pull-right" style="margin-top: 7px">Rp. <span id="b_asuransi"></span>
                                        </small>
                                    </h5>
                                    <div class="progress">
                                        <div id="sts_cover_biaya_asuransi">
                                        </div>
                                    </div>
                                    <h5>
                                        Total Biaya Tindakan
                                        <small class="pull-right" style="margin-top: 7px">Rp. <span
                                                id="b_tindakan_asuransi"></span></small>
                                    </h5>
                                    <div class="progress">
                                        <div id="sts_biaya_tindakan_asuransi">
                                        </div>
                                    </div>
                                    <ul style="list-style-type: none">
                                        <li><i class="fa fa-square" style="color: #337ab7"></i> Total biaya cover Asuransi
                                        </li>
                                        <li><i class="fa fa-square" style="color: #5cb85c"></i> Total biaya tindakan <
                                            50% dari cover biaya Asuransi
                                        </li>
                                        <li><i class="fa fa-square" style="color: #f0ad4e"></i> Total biaya tindakan >
                                            50% dan < 70% dari cover biaya Asuransi
                                        </li>
                                        <li><i class="fa fa-square" style="color: #d9534f"></i> Total biaya tindakan >
                                            70% dari cover biaya Asuransi
                                        </li>
                                    </ul>
                                </div>
                                <div class="col-md-2">

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="box-header with-border" id="pos_tin">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-medkit"></i> Tindakan</h3>
                    </div>
                    <div class="box-body">
                        <input type="hidden" id="tin_id_dokter">
                        <s:if test='headerDetailCheckup.idJenisPeriksaPasien != "paket_individu" && headerDetailCheckup.idJenisPeriksaPasien != "paket_perusahaan"'>
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(2)"><i class="fa fa-plus"></i> Tambah Tindakan
                        </button>
                            <table class="table table-bordered table-striped">
                                <thead>
                                <tr bgcolor="#90ee90">
                                    <td>Tanggal</td>
                                    <td>Tindakan</td>
                                        <%--<td>Dokter</td>--%>
                                        <%--<td>Perawat</td>--%>
                                    <td align="center">Tarif (Rp.)</td>
                                    <td align="center">Qty</td>
                                    <td align="center">Total (Rp.)</td>
                                    <td align="center">Action</td>
                                </tr>
                                </thead>
                                <tbody id="body_tindakan">
                                </tbody>
                            </table>
                        </s:if>
                        <s:else>
                            <table class="table table-bordered table-striped">
                                <thead>
                                <tr bgcolor="#90ee90">
                                    <td>Tanggal</td>
                                    <td>Tindakan</td>
                                    <td align="center" width="10%">Action</td>
                                </tr>
                                </thead>
                                <tbody id="body_tindakan_paket">
                                </tbody>
                            </table>
                        </s:else>
                    </div>
                    <div class="box-header with-border" id="pos_lab">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Penunjang Medis</h3>
                    </div>
                    <div class="box-body">
                        <s:if test='headerDetailCheckup.idJenisPeriksaPasien != "paket_individu" && headerDetailCheckup.idJenisPeriksaPasien != "paket_perusahaan"'>
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(4)"><i class="fa fa-plus"></i> Penunjang Medis
                        </button>
                        </s:if>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tanggal Order</td>
                                <td>Pemeriksaan</td>
                                <td>Status</td>
                                <td>Jenis Lab</td>
                                <td align="center" width="10%">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_lab">

                            </tbody>
                        </table>
                    </div>

                    <%--<div class="box-header with-border" id="pos_obat">--%>
                    <%--</div>--%>
                    <%--<div class="box-header with-border">--%>
                        <%--<h3 class="box-title"><i class="fa fa-plus-square"></i> Obat Penunjang</h3>--%>
                    <%--</div>--%>
                    <%--<div class="box-body">--%>
                        <%--<button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"--%>
                                <%--onclick="showModal(5)"><i class="fa fa-plus"></i> Obat Penunjang--%>
                        <%--</button>--%>
                        <%--<table class="table table-bordered table-striped">--%>
                            <%--<thead>--%>
                            <%--<tr bgcolor="#90ee90">--%>
                                <%--<td>Tanggal</td>--%>
                                <%--<td>ID Obat</td>--%>
                                <%--<td>Obat</td>--%>
                                <%--<td align="center">Qty</td>--%>
                                <%--<td>Jenis Satuan</td>--%>
                                <%--<td align="center">Action</td>--%>
                            <%--</tr>--%>
                            <%--</thead>--%>
                            <%--<tbody id="body_obat">--%>

                            <%--</tbody>--%>
                        <%--</table>--%>
                    <%--</div>--%>

                    <div class="box-header with-border" id="pos_rssep">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-medkit"></i> Order Resep Obat</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(7)"><i class="fa fa-plus"></i> Tambah Resep
                        </button>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tanggal</td>
                                <td>ID Resep</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_resep">

                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_all">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_all_war"></p>
                        </div>
                        <div class="alert alert-success alert-dismissible" style="display: none" id="success_all">
                            <h4><i class="icon fa fa-info"></i> Info!</h4>
                            <p id="msg_all_suc"></p>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-4 text-center">
                                        <a class="btn btn-success" id="save_all" onclick="confirmSaveAllTindakan()"><i class="fa fa-check"></i> Save All Tindakan</a>
                                        <button style="display: none; cursor: no-drop;" type="button"
                                                class="btn btn-success" id="load_all"><i class="fa fa-spinner fa-spin"></i>
                                            Sedang Menyimpan...
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-navicon"></i> Keterangan (Jika sudah pulang / selesai pemeriksaan)</h3>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_ket">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            Silahkan cek kembali data inputan!
                        </div>
                        <div class="row">
                            <div class="col-md-offset-1 col-md-5">
                                <div class="form-group">
                                    <label class="col-md-3" style="margin-top: 10px">Keterangan</label>
                                    <div class="col-md-9">
                                        <select class="form-control select2" id="keterangan" style="width: 100%"
                                                onchange="var warn =$('#war_catatan').is(':visible'); if (warn){$('#cor_catatan').show().fadeOut(3000);$('#war_catatan').hide()}; selectKeterangan(this.value)">
                                            <option value=''>[Select One]</option>
                                            <option value='selesai'>Selesai</option>
                                            <option value='pindah'>Pindah Poli Lain</option>
                                            <%--<option value='rujuk'>Rujuk Rawat Inap</option>--%>
                                            <option value='lanjut_biaya'>Lanjut Biaya</option>
                                            <option value='rujuk_rs_lain'>Rujuk RS Lain</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div id="form-selesai" style="display: none">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Catatan</label>
                                        <div class="col-md-8">
                                        <s:action id="initComboKet" namespace="/checkupdetail"
                                                  name="getListComboKeteranganKeluar_checkupdetail"/>
                                        <s:select list="#initComboKet.listOfKeterangan" id="ket_selesai"
                                                  name="headerCheckup.idPelayanan" listKey="keterangan"
                                                  listValue="keterangan" cssStyle="width: 100%"
                                                  onchange="var warn =$('#war_kolom-2').is(':visible'); if (warn){$('#col_kolom-2').show().fadeOut(3000);$('#war_kolom-2').hide()}; showFormCekup(this);"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                        </div>
                                    </div>
                                </div>
                                <div id="form-cekup" style="display: none;">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Tgl Ckp Ulang</label>
                                        <div class="col-md-8">
                                        <div class="input-group date" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_cekup" cssClass="form-control datepicker"/>
                                        </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Catatan</label>
                                        <div class="col-md-8">
                                        <s:textarea cssClass="form-control" rows="5" id="cekup_ket"
                                                    style="margin-top: 7px"></s:textarea>
                                        </div>
                                    </div>
                                </div>
                                <div id="kamar" style="display: none;">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Kelas</label>
                                        <div class="col-md-8">
                                        <s:action id="initComboKelas" namespace="/checkupdetail"
                                                  name="getListComboKelasRuangan_checkupdetail"/>
                                        <s:select
                                                onchange="var warn =$('#war_kolom-2').is(':visible'); if (warn){$('#col_kolom-2').show().fadeOut(3000);$('#war_kolom-2').hide()}; listSelectRuangan(this)"
                                                list="#initComboKelas.listOfKelasRuangan" id="kelas_kamar"
                                                listKey="idKelasRuangan" cssStyle="width: 100%"
                                                listValue="namaKelasRuangan"
                                                headerKey="" headerValue="[Select one]"
                                                cssClass="form-control select2"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Kamar</label>
                                        <div class="col-md-8">
                                        <select class="form-control select2" id="kamar_detail" style="width: 100%"
                                                onchange="var warn =$('#war_kolom-3').is(':visible'); if (warn){$('#col_kolom-3').show().fadeOut(3000);$('#war_kolom-3').hide()}">
                                            <option value=''>[Select One]</option>
                                        </select>
                                        </div>
                                    </div>
                                </div>
                                <div id="pembayaran" style="display: none;">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Metode Bayar</label>
                                        <div class="col-md-8">
                                            <s:select
                                                    id="metode_bayar"
                                                    list="#{'tunai':'Tunai','non_tunai':'Non Tunai'}"
                                                    cssStyle="margin-top: 7px"
                                                    headerKey="" headerValue="[Select one]"
                                                    cssClass="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Uang Muka</label>
                                        <div class="col-md-8">
                                            <div class="input-group" style="margin-top: 7px">
                                                <div class="input-group-addon">
                                                    Rp.
                                                </div>
                                                <s:hidden id="uang_muka_val"></s:hidden>
                                                <s:textfield type="text" id="uang_muka" cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="form-asuransi" style="display: none">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Nama Asuransi</label>
                                        <div class="col-md-8">
                                            <input style="margin-top: 7px" class="form-control" id="ri_nama_asuransi" disabled>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 7px">Cover Biaya</label>
                                        <div class="col-md-8">
                                            <div class="input-group" style="margin-top: 7px">
                                                <div class="input-group-addon">
                                                    Rp.
                                                </div>
                                                <s:hidden id="rj_cover_biaya_val"></s:hidden>
                                                <s:textfield type="text" id="rj_cover_biaya" cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="form-poli" style="display: none">
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Poli</label>
                                        <div class="col-md-8">
                                        <s:action id="initComboPoli" namespace="/checkup"
                                                  name="getComboPelayanan_checkup"/>
                                        <s:select
                                                list="#initComboPoli.listOfPelayanan" id="poli_lain"
                                                name="headerCheckup.idPelayanan" listKey="idPelayanan"
                                                listValue="namaPelayanan" cssStyle="width: 100%"
                                                onchange="var warn =$('#war_kolom-2').is(':visible'); if (warn){$('#col_kolom-2').show().fadeOut(3000);$('#war_kolom-2').hide()}; listDokterKeterangan(this)"
                                                headerKey="" headerValue="[Select one]"
                                                cssClass="form-control select2"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4" style="margin-top: 10px">Dokter</label>
                                        <div class="col-md-8">
                                        <select id="list_dokter" class="form-control select2" style="width: 100%"
                                                onchange="var warn =$('#war_kolom-3').is(':visible'); if (warn){$('#col_kolom-3').show().fadeOut(3000);$('#war_kolom-3').hide()}">
                                            <option value=''>[Select One]</option>
                                        </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-4 text-center">
                                        <a class="btn btn-warning" href="initForm_checkupdetail.action"><i class="fa fa-arrow-left"></i> Back</a>
                                        <%--<a class="btn btn-primary" onclick="printGelangPasien()"><i class="fa fa-print"></i> Print</a>--%>
                                        <a class="btn btn-success" id="save_ket" onclick="confirmSaveKeterangan()"><i class="fa fa-check"></i> Close</a>
                                        <button style="display: none; cursor: no-drop;" type="button"
                                                class="btn btn-success" id="load_ket"><i class="fa fa-spinner fa-spin"></i>
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

<div class="modal fade" id="modal-alergi">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Tambah Alergi</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_alergi">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Silahkan cek kembali data inputan!
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Alergi</label>
                        <div class="col-md-7">
                            <input class="form-control" id="alergi"
                                   oninput="var warn =$('#war_alergi').is(':visible'); if (warn){$('#cor_alergi').show().fadeOut(3000);$('#war_alergi').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_alergi">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_alergi"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_alergi"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_alergi"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="modal-dokter">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Tambah Dokter</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_dokter">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Silahkan cek kembali data inputan!
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Dokter</label>
                        <div class="col-md-7">
                            <select id="dok_id_dokter" style="width: 100%" class="form-control select2"
                                    onchange="var warn =$('#war_dok').is(':visible'); if (warn){$('#cor_dok').show().fadeOut(3000);$('#war_dok').hide()}">
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_dok"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_dok"><i
                                    class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_dokter"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_dokter"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-tindakan">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-medkit"></i> Tambah Tindakan</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_tindakan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Silahkan cek kembali data inputan dan jumlah harus lebih dari 0
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Kategori Tindakan</label>
                        <div class="col-md-7">
                            <%--<s:action id="initComboKategoriTindakan" namespace="/checkupdetail"--%>
                                      <%--name="getListComboKategoriTindakan_checkupdetail"/>--%>
                            <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                      <%--onchange="listSelectTindakan(this.value); var warn =$('#war_kategori').is(':visible'); if (warn){$('#cor_kategori').show().fadeOut(3000);$('#war_kategori').hide()}"--%>
                                      <%--list="#initComboKategoriTindakan.listOfKategoriTindakan"--%>
                                      <%--id="tin_id_ketgori_tindakan"--%>
                                      <%--listKey="idKategoriTindakan"--%>
                                      <%--listValue="kategoriTindakan"--%>
                                      <%--headerKey="" headerValue="[Select one]"--%>
                                      <%--cssClass="form-control select2"/>--%>
                                <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                        id="tin_id_ketgori_tindakan"
                                        onchange="listSelectTindakan(this.value); var warn =$('#war_kategori').is(':visible'); if (warn){$('#cor_kategori').show().fadeOut(3000);$('#war_kategori').hide()}">
                                    <option value=''>[Select One]</option>
                                </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_kategori"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_kategori"><i class="fa fa-check"></i> correct</p>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Tindakan</label>
                        <%--<label style="margin-top: 7px"><s:property value="headerDetailCheckup.idJenisPeriksaPasien"/> </label>--%>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                    id="tin_id_tindakan"
                                    onchange="var warn =$('#war_tindakan').is(':visible'); if (warn){$('#cor_tindakan').show().fadeOut(3000);$('#war_tindakan').hide()}">
                                <option value=''>[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_tindakan"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_tindakan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                    <%--<label class="col-md-3" style="margin-top: 7px">Nama Perawat</label>--%>
                    <%--<div class="col-md-7">--%>
                    <%--<select class="form-control select2" style="margin-top: 7px; width: 100%"--%>
                    <%--id="tin_id_perawat"--%>
                    <%--onchange="var warn =$('#war_perawat').is(':visible'); if (warn){$('#cor_perawat').show().fadeOut(3000);$('#war_perawat').hide()}">--%>
                    <%--<option value="">[select one]</option>--%>
                    <%--<option value="1">Angel</option>--%>
                    <%--<option value="2">Anya</option>--%>
                    <%--<option value="3">Ayu</option>--%>
                    <%--</select>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-2">--%>
                    <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_perawat">--%>
                    <%--<i class="fa fa-times"></i> required</p>--%>
                    <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                    <%--id="cor_perawat"><i class="fa fa-check"></i> correct</p>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah</label>
                        <div class="col-md-7">
                            <input type="number" min="1" class="form-control" style="margin-top: 7px" id="tin_qty"
                                   oninput="$(this).css('border','')" onchange="$(this).css('border','')" value="1">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_tindakan"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_tindakan">
                    <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-diagnosa">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-stethoscope"></i> Tambah Diagnosa</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_diagnosa">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Silahkan cek kembali data inputan!
                </div>
                <div class="row">
                    <s:if test='headerDetailCheckup.idJenisPeriksaPasien == "bpjs" || headerDetailCheckup.idJenisPeriksaPasien == "ptpn"'>
                        <div class="form-group">
                        <label class="col-md-3">Diagnosa</label>
                        <div class="col-md-7">
                        <s:textfield id="nosa_id_diagnosa_bpjs" style="margin-top: 7px"
                                     name="headerCheckup.diagnosa"
                                     onkeypress="var warn =$('#war_diagnosa_bpjs').is(':visible'); if (warn){$('#cor_diagnosa_bpjs').show().fadeOut(3000);$('#war_diagnosa_bpjs').hide()}"
                                     cssClass="form-control" required="false"/>
                        <script>
                            var menus, mapped;
                            $('#nosa_id_diagnosa_bpjs').typeahead({
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
                                    $("#nosa_ket_diagnosa").val(selectedObj.name);
                                    return selectedObj.id;
                                }
                            });
                        </script>
                        </div>
                            <div class="col-md-2">
                                <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                   id="war_diagnosa_bpjs"><i class="fa fa-times"></i> required</p>
                                <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                   id="cor_diagnosa_bpjs"><i class="fa fa-check"></i> correct</p>
                            </div>
                        </div>
                        <div class="form-group">
                        <div class="col-md-offset-3 col-md-7">
                        <s:textarea rows="4" id="nosa_ket_diagnosa"
                                    cssStyle="margin-top: 7px" readonly="true"
                                    name="headerCheckup.namaDiagnosa"
                                    cssClass="form-control"></s:textarea>
                        </div>
                        </div>
                    </s:if>
                    <s:else>
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa</label>
                            <div class="col-md-7">
                                <s:action id="initComboDiagnosa" namespace="/checkupdetail"
                                          name="getListComboDiagnosa_checkupdetail"/>
                                <s:select cssStyle="margin-top: 7px; width: 100%"
                                          onchange="var warn =$('#war_diagnosa').is(':visible'); if (warn){$('#cor_diagnosa').show().fadeOut(3000);$('#war_diagnosa').hide()}"
                                          list="#initComboDiagnosa.listOfComboDiagnosa" id="nosa_id_diagnosa"
                                          name="headerDetailCheckup.idPelayanan" listKey="idDiagnosa"
                                          listValue="descOfDiagnosa"
                                          headerKey="" headerValue="[Select one]"
                                          cssClass="form-control select2"/>
                            </div>
                            <div class="col-md-2">
                                <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                   id="war_diagnosa"><i class="fa fa-times"></i> required</p>
                                <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                   id="cor_diagnosa"><i class="fa fa-check"></i> correct</p>
                            </div>
                        </div>
                    </s:else>
                    <div class="form-group">
                        <label class="col-md-3">Jenis Diagnosa</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                    id="nosa_jenis_diagnosa"
                                    onchange="var warn =$('#war_jenis_diagnosa').is(':visible'); if (warn){$('#cor_jenis_diagnosa').show().fadeOut(3000);$('#war_jenis_diagnosa').hide()}">
                                <option value="">[select one]</option>
                                <option value="0">Diagnosa Awal</option>
                                <option value="1">Diagnosa Akhir</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_jenis_diagnosa"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_jenis_diagnosa"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_diagnosa"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_diagnosa">
                    <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Penunjang Medis</h4>
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
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      onchange="var warn =$('#war_kategori_lab').is(':visible'); if (warn){$('#cor_kategori_lab').show().fadeOut(3000);$('#war_kategori_lab').hide()}; listSelectLab(this.value)"
                                      list="#comboLab.listOfKategoriLab" id="lab_kategori"
                                      listKey="idKategoriLab"
                                      listValue="namaKategori"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_kategori_lab"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_kategori_lab"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Lab</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="lab_lab"
                                    onchange="var warn =$('#war_lab').is(':visible'); if (warn){$('#cor_lab').show().fadeOut(3000);$('#war_lab').hide()}; listSelectParameter(this);">
                                <option value=''>[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_lab"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_lab"><i
                                    class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <input type="hidden" id="jenis_lab">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Parameter</label>
                        <div class="col-md-7">
                            <select class="form-control select2" multiple style="margin-top: 7px; width: 100%"
                                    id="lab_parameter"
                                    onchange="var warn =$('#war_parameter').is(':visible'); if (warn){$('#cor_parameter').show().fadeOut(3000);$('#war_parameter').hide()}">
                                <option value=''>[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_parameter"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_parameter"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_lab"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_lab">
                    <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-obat">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Obat Penunjang</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_obat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="obat_error"></p>
                </div>
                <div class="row">
                    <%--<div class="form-group" id="jenis_form">--%>
                    <%--<label class="col-md-3" style="margin-top: 7px">Jenis Obat</label>--%>
                    <%--<div class="col-md-7">--%>
                    <%--<s:action id="initJenis" namespace="/jenisobat"--%>
                    <%--name="getListJenisObat_jenisobat"/>--%>
                    <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                    <%--list="#initJenis.listOfJenisObat" id="obat_jenis_obat"--%>
                    <%--listKey="idJenisObat"--%>
                    <%--listValue="namaJenisObat"--%>
                    <%--headerKey="" headerValue="[Select one]"--%>
                    <%--cssClass="form-control select2"/>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-2">--%>
                    <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                    <%--id="war_jenis_obat"><i class="fa fa-times"></i> required</p>--%>
                    <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                    <%--id="cor_jenis_obat"><i class="fa fa-check"></i> correct</p>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group" id="nama_form">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <s:action id="initObatPoli" namespace="/obatpoli"
                                      name="getListObatPoli_obatpoli"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#initObatPoli.listOfObatPoli" id="ob_id_obat"
                                      listKey="idObat + '|' + namaObat + '|' + qtyBox + '|' + qtyLembar + '|' + qtyBiji + '|' + lembarPerBox + '|' + bijiPerLembar"
                                      listValue="namaObat"
                                      onchange="var warn =$('#war_obat').is(':visible'); if (warn){$('#cor_obat').show().fadeOut(3000);$('#war_obat').hide()}; setStokObat(this);"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                            <%--<select class="form-control select2" style="margin-top: 7px; width: 100%" id="ob_id_obat"--%>
                            <%--onchange="var warn =$('#war_obat').is(':visible'); if (warn){$('#cor_obat').show().fadeOut(3000);$('#war_obat').hide()}; setStokObat(this);">--%>
                            <%--<option value="">[select one]</option>--%>
                            <%--</select>--%>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_obat"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_obat">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group" id="nama_obat_form">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <s:textfield readonly="true" type="text" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="nama_obat"></s:textfield>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Stok Obat</label>
                        <div class="col-md-2">
                            <label style="margin-top: 7px">Box</label>
                            <s:textfield readonly="true" type="text" min="1" cssClass="form-control"
                                         id="ob_stok_box"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <label style="margin-top: 7px">Lembar</label>
                            <s:textfield readonly="true" type="text" min="1" cssClass="form-control"
                                         id="ob_stok_lembar"></s:textfield>
                        </div>
                        <div class="col-md-3">
                            <label style="margin-top: 7px">Biji</label>
                            <s:textfield readonly="true" type="text" min="1" cssClass="form-control"
                                         id="ob_stok_biji"></s:textfield>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Satuan</label>
                        <div class="col-md-7">
                            <s:select list="#{'box':'Box','lembar':'Lembar','biji':'Biji'}"
                                      cssStyle="margin-top: 7px; width: 100%"
                                      onchange="var warn = $('#war_ob_jenis_satuan').is(':visible'); if (warn){$('#cor_ob_jenis_satuan').show().fadeOut(3000);$('#war_ob_jenis_satuan').hide()}"
                                      id="ob_jenis_satuan"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_ob_jenis_satuan"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_ob_jenis_satuan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah</label>
                        <div class="col-md-7">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="ob_qty"
                                         onkeypress="var warn =$('#war_qty_obat').is(':visible'); if (warn){$('#cor_qty_obat').show().fadeOut(3000);$('#war_qty_obat').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_qty_obat"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_qty_obat"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" id="set_id_obat">
            <input type="hidden" id="set_lembar_perbox">
            <input type="hidden" id="set_biji_perlembar">
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_obat"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_obat"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-resep-head">
    <div class="modal-dialog modal-flat" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambah Resep Pasien</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_resep_head">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_resep"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Apotek</label>
                        <div class="col-md-7">
                            <s:action id="initApotek" namespace="/checkup"
                                      name="getComboApotek_checkup"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#initApotek.listOfApotek" id="resep_apotek"
                                      listKey="idPelayanan + '|' + namaPelayanan"
                                      listValue="namaPelayanan"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_apotek"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_apotek"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                    id="resep_nama_obat">
                                <option value="">[select one]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_obat"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_obat"><i class="fa fa-check"></i> correct</p>
                            <p style="margin-top: 17px; display: none; margin-left: -20px" id="label-kronis"><label class="label label-warning" >Obat Kronis</label></p>
                            <input type="hidden" id="val-kronis"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Stok Obat (Biji)</label>
                        <%--<div class="col-md-2">--%>
                        <%--<label style="margin-top: 7px">Box</label>--%>
                        <%--<input class="form-control" type="number" min="1" id="resep_stok_box"--%>
                        <%--readonly>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                        <%--<label style="margin-top: 7px">Lembar</label>--%>
                        <%--<input class="form-control" type="number" min="1" id="resep_stok_lembar"--%>
                        <%--readonly>--%>
                        <%--</div>--%>
                        <div class="col-md-7">
                            <%--<label style="margin-top: 7px">Stok (Biji)</label>--%>
                            <div class="input-group" style="margin-top: 7px; width: 40%">
                                <input class="form-control" type="number" min="1" id="resep_stok_biji" readonly>
                                <div class="input-group-addon">
                                    Biji
                                </div>
                            </div>
                        </div>
                        <input type="hidden" id="h-qty-default"/>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Satuan</label>
                        <div class="col-md-7">
                            <s:select list="#{'lembar':'Lembar','box':'Box'}"
                                      cssStyle="margin-top: 7px; width: 100%"
                                      onchange="var warn = $('#war_rep_jenis_satuan').is(':visible'); if (warn){$('#cor_rep_jenis_satuan').show().fadeOut(3000);$('#war_rep_jenis_satuan').hide()};defaultValByJenisSatuan(this.value)"
                                      id="resep_jenis_satuan"
                                      headerKey="biji" headerValue="Biji"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_jenis_satuan"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_jenis_satuan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Pemberian</label>
                        <div class="col-md-7">
                            <s:select list="#{'sebelum':'Sebelum'}"
                                      cssStyle="margin-top: 7px; width: 100%"
                                      onchange="var warn = $('#war_rep_jenis_satuan').is(':visible'); if (warn){$('#cor_rep_jenis_satuan').show().fadeOut(3000);$('#war_rep_jenis_satuan').hide()}"
                                      id="resep_waktu"
                                      headerKey="Sesudah" headerValue="Sesudah"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_waktu"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_waktu"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Waktu Minum</label>
                        <div class="col-md-7">
                            <div class="form-check" style="margin-top: 7px;">
                                <input type="checkbox" name="cek_waktu" id="pagi" value="Pagi" onclick="var warn = $('#war_rep_cek_waktu').is(':visible'); if (warn){$('#cor_rep_cek_waktu').show().fadeOut(3000);$('#war_rep_cek_waktu').hide()}">
                                <label for="pagi"></label> Pagi
                            </div>
                            <div class="form-check" style="margin-top: 7px; margin-left: 10px">
                                <input type="checkbox" name="cek_waktu" id="siang" value="Siang" onclick="var warn = $('#war_rep_cek_waktu').is(':visible'); if (warn){$('#cor_rep_cek_waktu').show().fadeOut(3000);$('#war_rep_cek_waktu').hide()}">
                                <label for="siang"></label> Siang
                            </div>
                            <div class="form-check" style="margin-top: 7px; margin-left: 10px">
                                <input type="checkbox" name="cek_waktu" id="malam" value="Malam" onclick="var warn = $('#war_rep_cek_waktu').is(':visible'); if (warn){$('#cor_rep_cek_waktu').show().fadeOut(3000);$('#war_rep_cek_waktu').hide()}">
                                <label for="malam"></label> Malam
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_cek_waktu"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_cek_waktu"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah</label>
                        <div class="col-md-7">
                            <input oninput="var warn =$('#war_rep_qty').is(':visible'); if (warn){$('#cor_rep_qty').show().fadeOut(3000);$('#war_rep_qty').hide()}"
                                   style="margin-top: 7px; width: 40%" value="1" class="form-control" type="number" min="1"
                                   id="resep_qty">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_qty"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_qty"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>

                    <div class="form-group" id="form-hari" style="display: none;">
                        <label class="col-md-3" style="margin-top: 7px; font-size:12px">Pengambilan Berikutnya(Hari)</label>
                        <div class="col-md-7">
                            <input oninput="var warn =$('#war_rep_qty').is(':visible'); if (warn){$('#cor_rep_hari').show().fadeOut(3000);$('#war_rep_hari').hide()}"
                                   style="margin-top: 7px; width: 40%;" value="7" class="form-control" type="number" min="1"
                                   id="hari-kronis">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_hari"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_hari"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Obat Racik</label>
                        <div class="col-md-7">

                            <select id="jenis_resep" class="form form-control" style="margin-top: 7px;width: 40%"
                                    onchange="var warn =$('#war_rep_qty').is(':visible'); if (warn){$('#cor_rep_racik').show().fadeOut(3000);$('#war_rep_racik').hide()}">
                                <option value=""> Tidak </option>
                                <option value="Y">Ya</option>
                            </select>
                            <%--<input oninput="var warn =$('#war_rep_qty').is(':visible'); if (warn){$('#cor_rep_qty').show().fadeOut(3000);$('#war_rep_qty').hide()}"--%>
                            <%--style="margin-top: 7px" value="1" class="form-control" type="number" min="1"--%>
                            <%--id="resep_qty">--%>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_racik"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_racik"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group" id="form-jenis-resep">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Resep</label>
                        <div class="col-md-7">
                            <select class="form-control" style="margin-top: 7px;width: 40%" id="select-jenis-resep">

                            </select>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                    <%--<label class="col-md-3" style="margin-top: 7px">Keterangan</label>--%>
                    <%--<div class="col-md-7">--%>
                    <%--<textarea id="resep_keterangan" rows="4" class="form-control" style="margin-top: 7px"--%>
                    <%--oninput="var warn =$('#war_rep_ket').is(':visible'); if (warn){$('#cor_rep_ket').show().fadeOut(3000);$('#war_rep_ket').hide()}"></textarea>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-2">--%>
                    <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                    <%--id="war_rep_ket"><i class="fa fa-times"></i> required</p>--%>
                    <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                    <%--id="cor_rep_ket"><i class="fa fa-check"></i> correct</p>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px"></label>
                        <div class="col-md-7">
                            <button class="btn btn-danger pull-right" style="margin-top: 7px" onclick="resetAll()"><i
                                    class="fa fa-refresh"></i> Reset
                            </button>
                            <button class="btn btn-success pull-right" style="margin-top: 7px; margin-right: 4px"
                                    onclick="addObatToList()"><i class="fa fa-plus"></i> Tambah
                            </button>
                        </div>
                    </div>
                </div>
                <div class="box-header with-border">
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_data_exits">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Data obat sudah tersedia..!
                </div>
                <div class="box-header with-border"><i class="fa fa-file-o"></i> Resep Obat, tujuan <b><span
                        id="desti_apotek"></span></b>
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_rese_detail" style="font-size: 13px;">
                        <thead>
                        <td>ID</td>
                        <td>Obat</td>
                        <td align="center">Qty</td>
                        <td align="center">Jenis Satuan</td>
                        <td>Keterangan</td>
                        <td>Racik</td>
                        <td>Kronis</td>
                        <td>Pengambilan Berikutnya</td>
                        <td>Harga</td>
                        <td align="center" width="5%">Action</td>
                        </thead>
                        <tbody id="body_detail">
                        </tbody>
                    </table>
                </div>
                <div class="row">
                    <div class="form-group">
                        <div class="col-md-4">
                            <label>Total Harga</label>
                            <div class="input-group">
                                <div class="input-group-addon">
                                    Rp.
                                </div>
                                <input class="form-control" id="total_harga_obat" readonly>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_resep_head" onclick="saveResepObatTtd()"><i
                        class="fa fa-arrow-right"></i> Buat Resep
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                        id="load_resep_head"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-resep-detail">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Detail Resep Obat</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_detail">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Silahkan cek kembali data inputan..!
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_detail">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    Data berhasil diupdate..!
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_rep">
                        <thead>
                        <td>Obat</td>
                        <td>Qty</td>
                        <td>Keterangan</td>
                        <td align="center" width="5%">Action</td>
                        </thead>
                        <tbody id="body_detail_rep">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <%--<button type="button" class="btn btn-success" id="save_resep_detail" onclick="saveResepDetail()"><i--%>
                <%--class="fa fa-arrow-right"></i> Save--%>
                <%--</button>--%>
                <%--<button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_resep_detail"><i--%>
                <%--class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...--%>
                <%--</button>--%>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-laka">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Data Asuransi</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_laka">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_laka"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">No Polisi</label>
                        <div class="col-md-7">
                            <input class="form-control" id="laka_no_polisi"
                                   oninput="var warn =$('#war_laka_no_polisi').is(':visible'); if (warn){$('#cor_laka_no_polisi').show().fadeOut(3000);$('#war_laka_no_polisi').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_laka_no_polisi"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_laka_no_polisi"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tgl Kejadian</label>
                        <div class="col-md-7">
                            <input class="form-control datepicker" style="margin-top: 7px" id="laka_tgl_kejadian"
                                   onchange="var warn =$('#war_laka_tgl_kejadian').is(':visible'); if (warn){$('#cor_laka_tgl_kejadian').show().fadeOut(3000);$('#war_laka_tgl_kejadian').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_laka_tgl_kejadian"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_laka_tgl_kejadian"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Foto Surat Polisi</label>
                        <div class="col-md-7">
                            <div class="input-group" style="margin-top: 7px" id="img_url">
                                  <span class="input-group-btn">
                                  <span class="btn btn-default btn-file">
                                  Browse… <s:file id="url_do" accept=".jpg"
                                  onchange="$('#img_file').css('border','')"></s:file>
                                  </span>
                                  </span>
                                <input type="text" class="form-control" readonly id="laka_surat_rujukan">
                            </div>
                        </div>
                        <div style="display: none;">
                            <canvas id="temp_surat_rujuk"></canvas>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_laka_surat_polisi"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_laka_surat_polisi"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_laka"><i
                class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_laka"><i
                class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ttd">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-pencil"></i> Tanda Tangan Dokter
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-group" style="padding-top: 10px; padding-bottom: 10px">
                    <div class="col-md-1">
                        <input type="color" style="margin-left: -6px; margin-top: -8px" class="js-color-picker  color-picker pull-left">
                    </div>
                    <div class="col-md-9">
                        <input type="range" style="margin-top: -8px" class="js-line-range" min="1" max="72" value="1">
                    </div>
                    <div class="col-md-2">
                        <div style="margin-top: -8px;" class="js-range-value">1 px</div>
                    </div>
                </div>
                <canvas class="js-paint  paint-canvas" id="ttd_canvas" width="550" height="300"></canvas>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-danger" onclick="clearConvas()"><i class="fa fa-pencil"></i> Clear
                </button>
                <button class="btn btn-success pull-right" onclick="saveResepObat()"><i class="fa fa-check"></i> Save</button>
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
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes            </button>
            </div>
        </div>
    </div>
</div>

<div class="mask"></div>
<!-- /.content-wrapper -->
<script type='text/javascript'>

    var idDetailCheckup = $('#no_detail_checkup').val();
    var idPoli = $('#id_palayanan').val();
    var idPasien = $('#id_pasien').val();
    var noCheckup = $('#no_checkup').val();
    var jenisPeriksa = $('#jenis_periksa').text();
    var jenisPeriksaPasien = $('#jenis_pasien').val();

    $(document).ready(function () {
        $('#rawat_jalan').addClass('active');
        listDokter();
        listTindakan();
        listDiagnosa();
        listSelectDokter();
        listLab();
        listObat();
        listResepPasien();
        listAlergi();
        hitungStatusBiaya();
        hitungBmi();
        listSelectTindakanKategori();
        hitungCoverBiaya();

        $('#img_ktp').on('click', function (e) {
            e.preventDefault();
            var src = $('#img_ktp').attr('src');

            if (src != null && src != "") {
                $('.mask').html('<div class="img-box"><img src="' + src + '"><a class="close">&times;</a>');

                $('.mask').addClass('is-visible fadein').on('animationend', function () {
                    $(this).removeClass('fadein is-visible').addClass('is-visible');
                });

                $('.close').on('click', function () {
                    $(this).parents('.mask').addClass('fadeout').on('animationend', function () {
                        $(this).removeClass('fadeout is-visible')
                    });
                });
            }

        });

        const paintCanvas = document.querySelector(".js-paint");
        const context = paintCanvas.getContext("2d");
        context.lineCap = "round";

        const colorPicker = document.querySelector(".js-color-picker");

        colorPicker.addEventListener("change", function (evt) {
            context.strokeStyle = evt.target.value;
        });

        const lineWidthRange = document.querySelector(".js-line-range");
        const lineWidthLabel = document.querySelector(".js-range-value");

        lineWidthRange.addEventListener("input", function (evt) {
            const width = evt.target.value;
            lineWidthLabel.innerHTML = width+" px";
            context.lineWidth = width;
        });

        let x = 0,
            y = 0;
        let isMouseDown = false;

        const stopDrawing = function () {
            isMouseDown = false;
        };

        const startDrawing = function (evt) {
            isMouseDown = true;
            [x, y] = [evt.offsetX, evt.offsetY];
        };

        const drawLine = function (evt) {
            if (isMouseDown) {
                const newX = evt.offsetX;
                const newY = evt.offsetY;
                context.beginPath();
                context.moveTo(x, y);
                context.lineTo(newX, newY);
                context.stroke();
                x = newX;
                y = newY;
            }
        };

        paintCanvas.addEventListener("mousedown", startDrawing);
        paintCanvas.addEventListener("mousemove", drawLine);
        paintCanvas.addEventListener("mouseup", stopDrawing);
        paintCanvas.addEventListener("mouseout", stopDrawing);

        var nominal = document.getElementById('uang_muka');
        if(nominal != ''){
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

        var cover = document.getElementById('rj_cover_biaya');
        if(cover != ''){
            cover.addEventListener('keyup', function (e) {
                cover.value = formatRupiah2(this.value);
                var valCover = cover.value.replace(/[.]/g, '');

                if(valCover != ''){
                    $('#rj_cover_biaya_val').val(valCover);
                }else{
                    $('#rj_cover_biaya_val').val('');
                }
            });
        }

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
                var warn =$('#war_laka_surat_polisi').is(':visible');
                if (warn){
                    $('#cor_laka_surat_polisi').show().fadeOut(3000);
                    $('#war_laka_surat_polisi').hide()
                }
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
    });

    function getJenisResep(){

        strSelect = "";
        var arBodyJenisResep = [];
        if(jenisPeriksaPasien == "ptpn"){
            arBodyJenisResep.push({"nilai":"bpjs", "label":"BPJS"},{"nilai": "ptpn", "label":"PTPN"});
        } else if (jenisPeriksaPasien == "asuransi"){
            arBodyJenisResep.push({"nilai":"asuransi", "label":"ASURANSI"},{"nilai": "umum", "label":"UMUM"});
        } else if (jenisPeriksaPasien == "bpjs") {
            arBodyJenisResep.push({"nilai": "bpjs", "label": "BPJS"});
        } else {
            arBodyJenisResep.push({"nilai": "umum", "label": "UMUM"});
        }

        var strSelect = "";
        $.each(arBodyJenisResep, function (i, item) {
            strSelect += "<option value='" + item.nilai + "'>" + item.label + "</option>";
        });
        $("#select-jenis-resep").html(strSelect);
    }

    function hitungBmi(){

        var berat = $('#berat').val();
        var tinggi = $('#tinggi').val();
        var persen = "";
        var bmi = "";
        var barClass = "";
        var barLabel = "";

        if (berat != '' && tinggi != '') {
            var tom = (parseInt(tinggi) * 0.01);
            var tes = (parseFloat(tom)) *  parseFloat(tom);
            bmi = (parseInt(berat) / (tom *  tom)).toFixed(2);
        }

        if (parseInt(bmi) < 18.5) {
            barClass = 'progress-bar-primary';
            persen = 25;
        } else if (parseInt(bmi) >= 18.5 && parseInt(bmi) <= 22.9) {
            barClass = 'progress-bar-success';
            persen = 50;
        } else if (parseInt(bmi) >= 23 && parseInt(bmi) <= 29.9) {
            barClass = 'progress-bar-warning';
            persen = 75;
        } else if (parseInt(bmi) > 30) {
            barClass = 'progress-bar-danger';
            persen = 100;
        }

        var barBmi = '<div class="progress-bar ' + barClass + '" style="width: ' + persen + '%" role="progressbar" aria-valuenow="' + persen + '" aria-valuemin="0" aria-valuemax="100">' + bmi +'</div>';

        $('#bar_bmi').html(barBmi);
    }

    function printGelangPasien() {
        window.open('printGelangPasien_checkupdetail.action?id=' + noCheckup, '_blank');
    }

    function hitungCoverBiaya() {
        var jenis = $('#jenis_pasien').val();
        if("asuransi" == jenis){
            CheckupDetailAction.getBiayaAsuransi(idDetailCheckup, function (response) {
                if (response.coverBiaya != null && response.coverBiaya != '') {
                    $('#status_asuransi').show();
                    if (response.coverBiaya != null) {

                        var coverBiaya = response.coverBiaya;
                        var biayaTindakan = response.tarifTindakan;

                        var persen = "";
                        if (coverBiaya != '' && biayaTindakan) {
                            persen = ((parseInt(biayaTindakan) / parseInt(coverBiaya)) * 100).toFixed(2);
                        } else {
                            persen = 0;
                        }

                        var barClass = "";
                        var barLabel = "";

                        if (parseInt(persen) > 70) {
                            barClass = 'progress-bar-danger';
                        } else if (parseInt(persen) > 50) {
                            barClass = 'progress-bar-warning';
                        } else {
                            barClass = 'progress-bar-success';
                        }

                        var barBpjs = '<div class="progress-bar progress-bar-primary" style="width: 100%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">' + "100%" + '</div>';

                        var barTindakan = '<div class="progress-bar ' + barClass + '" style="width: ' + persen + '%" role="progressbar" aria-valuenow="' + persen + '" aria-valuemin="0" aria-valuemax="100">' + persen + "%" + '</div>';

                        if (coverBiaya != '') {
                            $('#sts_cover_biaya_asuransi').html(barBpjs);
                            $('#b_asuransi').html(formatRupiah(coverBiaya) + " (100%)");
                        }

                        if (biayaTindakan != '') {
                            $('#sts_biaya_tindakan_asuransi').html(barTindakan);
                            $('#b_tindakan_asuransi').html(formatRupiah(biayaTindakan) + " (" + persen + "%)");
                        }
                    }
                } else {
                    $('#status_asuransi').hide();
                }
            });
        }
    }

    function hitungStatusBiaya() {
        var jenis = $('#jenis_pasien').val();
        if("bpjs" == jenis || "ptpn" == jenis){
            CheckupDetailAction.getStatusBiayaTindakan(idDetailCheckup, "RWJ", function (response) {
                    $('#status_bpjs').show();
                    if (response.tarifBpjs != null && response.tarifTindakan != null) {

                        var coverBiaya = response.tarifBpjs;
                        var biayaTindakan = response.tarifTindakan;
                        $('#kode_cbg').text(response.kodeCbg);

                        var persen = "";
                        if (coverBiaya != '' && biayaTindakan) {
                            persen = ((parseInt(biayaTindakan) / parseInt(coverBiaya)) * 100).toFixed(2);
                        } else {
                            persen = 0;
                        }

                        var barClass = "";
                        var barLabel = "";

                        if (parseInt(persen) > 70) {
                            barClass = 'progress-bar-danger';
                        } else if (parseInt(persen) > 50) {
                            barClass = 'progress-bar-warning';
                        } else {
                            barClass = 'progress-bar-success';
                        }

                        var barBpjs = '<div class="progress-bar progress-bar-primary" style="width: 100%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">' + "100.00%" + '</div>';

                        var barTindakan = '<div class="progress-bar ' + barClass + '" style="width: ' + persen + '%" role="progressbar" aria-valuenow="' + persen + '" aria-valuemin="0" aria-valuemax="100">' + persen + "%" + '</div>';

                        if (coverBiaya != '') {
                            $('#sts_cover_biaya').html(barBpjs);
                            $('#b_bpjs').html(formatRupiah(coverBiaya) + " (100%)");
                        }

                        if (biayaTindakan != '') {
                            $('#sts_biaya_tindakan').html(barTindakan);
                            $('#b_tindakan').html(formatRupiah(biayaTindakan) + " (" + persen + "%)");
                        }
                    }
            });
        }else{
            $('#status_bpjs').hide();
        }
    }

    function saveAlergi(id) {
        var alergi = $('#alergi').val();

        if (noCheckup != '' && alergi != '') {
            $('#save_alergi').hide();
            $('#load_alergi').show();
            if (id != '') {
                dwr.engine.setAsync(true);
                CheckupAction.saveEditAlergi(alergi, id, function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        listAlergi();
                        $('#modal-alergi').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(10);
                    } else {

                    }
                })
            } else {
                dwr.engine.setAsync(true);
                CheckupAction.saveAddAlergi(alergi, noCheckup, function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        listAlergi();
                        $('#modal-alergi').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(10);
                    } else {

                    }
                })
            }
        } else {
            $('#warning_alergi').show().fadeOut(5000);
            $('#war_alergi').show();
        }
    }

    function listAlergi() {

        var table = "";
        var noCheckup = $("#no_checkup").val();
        CheckupAction.getListAlergi(noCheckup, function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    table += "<tr>" +
                            "<td>" + item.alergi + "</td>" +
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editAlergi(\'' + item.idAlergi + '\',\'' + item.alergi + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">' + "</td>" +
                            "</tr>";
                });
            }
        });
        $('#body_alergi').html(table);
    }

    function editAlergi(id, alergi) {
        $('#load_alergi').hide();
        $('#modal-alergi').modal('show');
        $('#alergi').val(alergi);
        $('#save_alergi').attr('onclick', 'saveAlergi(\'' + id + '\')').show();

    }

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
        $('#dok_id_dokter').html(option);
    }

    function selectKeterangan(idKtg) {
        var jenisPasien = $('#jenis_pasien').val();

        if(idKtg != ''){
            if (idKtg == "pindah") {
                $('#pembayaran').hide();
                $("#form-poli").attr('style', 'display:block');
                $("#kamar").attr('style', 'display:none');
                $("#form-selesai").hide();
                $("#form-cekup").hide();

                if(jenisPasien == 'umum'){
                    $('#form-asuransi').hide();
                    $('#pembayaran').show();
                }else if (jenisPasien == 'asuransi'){
                    $('#ri_nama_asuransi').val($('#nama_asuransi').val());
                    $('#pembayaran').hide();
                    $('#form-asuransi').show();
                }else {
                    $('#form-asuransi').hide();
                    $('#pembayaran').hide();
                }
            }
            if (idKtg == "rujuk") {
                $("#kamar").attr('style', 'display:block');
                $("#form-poli").attr('style', 'display:none');
                $("#form-selesai").hide();
                $("#form-cekup").hide();

                if(jenisPasien == 'bpjs'){
                    $('#pembayaran').hide();
                }else{
                    $('#pembayaran').show();
                }
            }
            if (idKtg == "selesai") {
                $('#pembayaran').hide();
                $("#kamar").attr('style', 'display:none');
                $("#form-poli").attr('style', 'display:none');
                $("#form-selesai").show();
                $("#form-cekup").hide();
            }
            if (idKtg == "lanjut_biaya" || idKtg == "rujuk_rs_lain") {
                $('#pembayaran').hide();
                $("#kamar").attr('style', 'display:none');
                $("#form-poli").attr('style', 'display:none');
                $("#form-selesai").hide();
                $("#form-cekup").hide();
            }
            if(idKtg == ""){
                $('#pembayaran').hide();
                $("#kamar").hide();
                $("#form-poli").hide();
                $("#form-selesai").hide();
                $("#form-cekup").hide();
            }
        }
    }

    function listSelectRuangan(id) {
        var idx = id.selectedIndex;
        var idKelas = id.options[idx].value;
        var flag = true;

        var option = "";
        CheckupDetailAction.listRuangan(idKelas, flag, function (response) {
            option = "<option value=''>[Select One]</option>";
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idRuangan + "'>" + item.noRuangan + "-" + item.namaRuangan + "</option>";
                });
                $('#kamar_detail').html(option);
            } else {
                option = option;
            }
        });
    }

    function confirmSaveKeterangan(){
        var idKtg = $("#keterangan").val();
        var noCheckup = $("#no_checkup").val();
        var poli = $("#poli_lain").val();
        var kelas = $("#kelas_kamar").val();
        var kamar = $("#kamar_detail").val();
        var idDokter = $("#list_dokter").val();
        var ket_selesai = $('#ket_selesai').val();
        var tgl_cekup = $('#tgl_cekup').val();
        var ket_cekup = $('#cekup_ket').val();
        var jenisPasien = $('#jenis_pasien').val();
        var metodeBayar = $("#metode_bayar").val();
        var uangMuka = "";
        var uangUmum = $("#uang_muka_val").val();
        var uangAsuransi = $("#rj_cover_biaya_val").val();
        var idPasien = $('#id_pasien').val();
        var namaAsuransi = $('#nama_asuransi').val();
        var noRujukan = $('#no_rujukan').val();
        var tglRujukan = $('#tgl_rujukan').val();
        var suratRujukan = $('#surat_rujukan').val();
        var isLaka = $('#is_laka').val();

        if(jenisPasien == 'umum'){
            uangMuka = uangUmum;
        }
        if(jenisPasien == 'asuransi'){
            uangMuka = uangAsuransi;
        }

        if (idKtg != '') {

            if (idKtg == "pindah") {
                if (poli != '' && idDokter != '') {
                    if(isLaka == "Y"){
                        if(noRujukan != '' && tglRujukan != '' && suratRujukan != ''){
                            $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                            $('#modal-confirm-dialog').modal('show');
                        }else{
                            $('#laka_no_polisi').val(noRujukan);
                            $('#laka_tgl_kejadian').val(noRujukan);
                            $('#laka_surat_rujukan').val(suratRujukan);
                            $('#modal-laka').modal({show:true, backdrop:'static'});
                            var data  = {
                                'id_ktg':idKtg,
                                'poli':poli,
                                'kelas':kelas,
                                'kamar':kamar,
                                'ket_selesai':ket_selesai,
                                'tgl_cekup':tgl_cekup,
                                'ket_cekup':ket_cekup,
                                'jenis':jenisPasien,
                                'id_pasien':idPasien,
                                'metode_bayar':metodeBayar,
                                'uang_muka':uangMuka
                            }
                            var result = JSON.stringify(data);
                            $('#save_laka').attr('onclick','saveDataAsuransi(\''+result+'\')');
                        }
                    }else{
                        $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                        $('#modal-confirm-dialog').modal('show');
                    }
                } else {
                    $('#warning_ket').show().fadeOut(5000);
                    if (poli == '') {
                        $('#war_kolom-2').show();
                    }
                    if (idDokter == '') {
                        $('#war_kolom-3').show();
                    }
                }
            }

            if (idKtg == "rujuk") {
                if (kelas != '' && kamar != '') {
                    if(isLaka == "Y"){
                        if(noRujukan != '' && tglRujukan != '' && suratRujukan != ''){
                            $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                            $('#modal-confirm-dialog').modal('show');
                        }else{
                            $('#laka_no_polisi').val(noRujukan);
                            $('#laka_tgl_kejadian').val(noRujukan);
                            $('#laka_surat_rujukan').val(suratRujukan);
                            $('#modal-laka').modal({show:true, backdrop:'static'});
                            var data  = {
                                'id_ktg':idKtg,
                                'poli':poli,
                                'kelas':kelas,
                                'kamar':kamar,
                                'ket_selesai':ket_selesai,
                                'tgl_cekup':tgl_cekup,
                                'ket_cekup':ket_cekup,
                                'jenis':jenisPasien,
                                'id_pasien':idPasien,
                                'metode_bayar':metodeBayar,
                                'uang_muka':uangMuka
                            }
                            var result = JSON.stringify(data);
                            $('#save_laka').attr('onclick','saveDataAsuransi(\''+result+'\')');
                        }
                    }else{
                        $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                        $('#modal-confirm-dialog').modal('show');
                    }
                }else {
                    $('#warning_ket').show().fadeOut(5000);
                    if (kelas == '') {
                        $('#war_kolom-2').show();
                    }
                    if (kamar == '') {
                        $('#war_kolom-3').show();
                    }
                }
            }

            if (idKtg == "selesai") {

                metodeBayar = $("#jenis_bayar").val();

                if (ket_selesai != '') {
                    if(isLaka == "Y"){
                        if(noRujukan != '' && tglRujukan != '' && suratRujukan != ''){
                            $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                            $('#modal-confirm-dialog').modal('show');
                        }else{
                            $('#laka_no_polisi').val(noRujukan);
                            $('#laka_tgl_kejadian').val(noRujukan);
                            $('#laka_surat_rujukan').val(suratRujukan);
                            $('#modal-laka').modal({show:true, backdrop:'static'});
                            var data  = {
                                'id_ktg':idKtg,
                                'poli':poli,
                                'kelas':kelas,
                                'kamar':kamar,
                                'ket_selesai':ket_selesai,
                                'tgl_cekup':tgl_cekup,
                                'ket_cekup':ket_cekup,
                                'jenis':jenisPasien,
                                'id_pasien':idPasien,
                                'metode_bayar':metodeBayar,
                                'uang_muka':uangMuka
                            }
                            var result = JSON.stringify(data);
                            $('#save_laka').attr('onclick','saveDataAsuransi(\''+result+'\')');
                        }
                    }else{
                        $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                        $('#modal-confirm-dialog').modal('show');
                    }
                } else {
                    $('#warning_ket').show().fadeOut(5000);
                    $('#war_kolom-2').show();
                }
            }

            if(idKtg == "lanjut_biaya" || idKtg == "rujuk_rs_lain"){
                    if(isLaka == "Y"){
                        if(noRujukan != '' && tglRujukan != '' && suratRujukan != ''){
                            $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                            $('#modal-confirm-dialog').modal('show');
                        }else{
                            $('#laka_no_polisi').val(noRujukan);
                            $('#laka_tgl_kejadian').val(noRujukan);
                            $('#laka_surat_rujukan').val(suratRujukan);
                            $('#modal-laka').modal({show:true, backdrop:'static'});
                            var data  = {
                                'id_ktg':idKtg,
                                'poli':poli,
                                'kelas':kelas,
                                'kamar':kamar,
                                'ket_selesai':ket_selesai,
                                'tgl_cekup':tgl_cekup,
                                'ket_cekup':ket_cekup,
                                'jenis':jenisPasien,
                                'id_pasien':idPasien,
                                'metode_bayar':metodeBayar,
                                'uang_muka':uangMuka
                            }
                            var result = JSON.stringify(data);
                            $('#save_laka').attr('onclick','saveDataAsuransi(\''+result+'\')');
                        }
                    }else{
                        $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                        $('#modal-confirm-dialog').modal('show');
                    }
            }
        } else {
            $('#warning_ket').show().fadeOut(5000);
            $('#war_catatan').show();
        }
    }

    function saveDataAsuransi(data){
        var noPolisi = $('#laka_no_polisi').val();
        var tglKejadian = $('#laka_tgl_kejadian').val();
        var suratRujukan = $('#laka_surat_rujukan').val();
        var canvas = document.getElementById('temp_surat_rujuk');
        var dataURL = canvas.toDataURL("image/png"),
            dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
        var obj = JSON.parse(data);
        var idKtg = obj["id_ktg"];
        var poli = obj["poli"];
        var kelas = obj["kelas"];
        var kamar = obj["kamar"];
        var ket_selesai = obj["ket_selesai"];
        var tgl_cekup = obj["tgl_cekup"];
        var ket_cekup = obj["ket_cekup"];
        var jenisPasien = obj["jenis"];
        var idPasien = obj["id_pasien"];
        var metodeBayar = obj["metode_bayar"];
        var uangMuka = obj["uang_muka"];

        if(noPolisi != '' && tglKejadian != '' && dataURL != ''){

            $('#save_laka').hide();
            $('#load_laka').show();
            dwr.engine.setAsync(true);
            CheckupDetailAction.saveUpdateDataAsuransi(idDetailCheckup, noPolisi, tglKejadian, dataURL, function (response) {
                if(response.status == "success"){
                    $('#modal-laka').modal('hide');
                    $('#save_laka').show();
                    $('#load_laka').hide();
                    $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\',\''+metodeBayar+'\', \''+uangMuka+'\')');
                    $('#modal-confirm-dialog').modal('show');
                }else {
                    $('#warning_laka').show().fadeOut(5000);
                    $('#msg_laka').text(response.message);
                    $('#save_laka').show();
                    $('#load_laka').hide();
                }
            });

        }else{
            $('#warning_laka').show().fadeOut(5000);
            $('#msg_laka').text("Silahkan cek kembali inputan anda...!");
            if(noPolisi == ''){
                $('#war_laka_no_polisi').show();
            }
            if(tglKejadian == ''){
                $('#war_laka_tgl_kejadian').show();
            }
            if(suratRujukan == ''){
                $('#war_laka_surat_polisi').show();
            }
        }
    }

    function saveKeterangan(idKtg, poli, kelas, kamar, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, idPasien, metodeBayar, uangMuka) {
        $('#modal-confirm-dialog').modal('hide');
        var idDokter = $('#tin_id_dokter').val();
        var jenisBayar = $('#jenis_bayar').val();

        if(idKtg == "pindah"){
            $('#save_ket').hide();
            $('#load_ket').show();
            $('#waiting_dialog').dialog('open');
            dwr.engine.setAsync(true);
            CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, "", "", "", idPasien, metodeBayar, uangMuka, jenisBayar, function (response) {
                if(response.status == "success"){
                    $('#modal-laka').modal('hide');
                    $('#waiting_dialog').dialog('close');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(6);
                    $('#save_ket').show();
                    $('#load_ket').hide();
                }else{
                    $('#waiting_dialog').dialog('close');
                    $('#error_dialog').dialog('open');
                    $('#errorMessage').text(response.msg);
                    $('#save_ket').show();
                    $('#load_ket').hide();
                }
            });
        }
        if(idKtg == "rujuk"){
            $('#save_ket').hide();
            $('#load_ket').show();
            $('#waiting_dialog').dialog('open');
            dwr.engine.setAsync(true);
            CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, "", "", "", idPasien, metodeBayar, uangMuka, jenisBayar, function (response) {
                if(response.status == "success"){
                    $('#modal-laka').modal('hide');
                    $('#waiting_dialog').dialog('close');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(6);
                    $('#save_ket').show();
                    $('#load_ket').hide();
                }else{
                    $('#waiting_dialog').dialog('close');
                    $('#error_dialog').dialog('open');
                    $('#errorMessage').text(response.msg);
                    $('#save_ket').show();
                    $('#load_ket').hide();
                }
            });
        }
        if(idKtg == "selesai" || idKtg == "lanjut_biaya" || idKtg == "rujuk_rs_lain"){
            $('#save_ket').hide();
            $('#load_ket').show();
            $('#waiting_dialog').dialog('open');
            dwr.engine.setAsync(true);
            CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, "", "", "", idPasien, metodeBayar, uangMuka, jenisBayar, function (response) {
                if(response.status == "success"){
                    $('#modal-laka').modal('hide');
                    $('#waiting_dialog').dialog('close');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(6);
                    $('#save_ket').show();
                    $('#load_ket').hide();
                }else{
                    $('#waiting_dialog').dialog('close');
                    $('#error_dialog').dialog('open');
                    $('#errorMessage').text(response.msg);
                    $('#save_ket').show();
                    $('#load_ket').hide();
                }
            });
        }
    }

    function listSelectTindakan(idKategori) {
        // var idx = idKategori.selectedIndex;
        // var idKtg = idKategori.options[idx].value;
        var option = "<option value=''>[Select One]</option>";
        if (idKategori != '') {
            CheckupDetailAction.getListComboTindakan(idKategori, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idTindakan + "'>" + item.tindakan + "</option>";
                    });
                    $('#tin_id_tindakan').html(option);
                } else {
                    $('#tin_id_tindakan').html('');
                }
            });
        } else {
            $('#tin_id_tindakan').html('');
        }
    }

    function listSelectTindakanKategori() {
        var option = "<option value=''>[Select One]</option>";
            CheckupDetailAction.getListComboTindakanKategori(idPoli, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idKategoriTindakan + "'>" + item.kategoriTindakan + "</option>";
                    });
                    $('#tin_id_ketgori_tindakan').html(option);
                } else {
                    $('#tin_id_ketgori_tindakan').html('');
                }
            });
    }

    function toContent() {
        var back = $('#close_pos').val();
        var desti = "";

        if (back == 1) {
            desti = "#pos_dok";
        } else if (back == 2) {
            desti = "#pos_tin";
        } else if (back == 3) {
            desti = "#pos_nosa";
        } else if (back == 4) {
            desti = "#pos_lab";
        } else if (back == 5) {
            desti = "#pos_obat";
        } else if (back == 6) {
            window.location.href = 'initForm_checkupdetail.action';
        } else if (back == 9) {
            desti = '#pos_rssep';
        } else if (back == 10) {
            desti = '#pos_alergi';
        }

        $('html, body').animate({
            scrollTop: $(desti).offset().top
        }, 2000);
    }

    function showModal(select) {

        var id = "";

        if (select == 1) {
            $('#dok_id_dokter').val('').trigger('change');
            $('#load_dokter, #warning_dokter, #war_dok').hide();
            $('#save_dokter').attr('onclick', 'saveDokter(\'' + id + '\')').show();
            $('#modal-dokter').modal({show:true, backdrop:'static'});

        } else if (select == 2) {
            $('#tin_id_ketgori_tindakan, #tin_id_tindakan, #tin_id_perawat').val('').trigger('change');
            $('#tin_qty').val('1');
            $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
            $('#save_tindakan').attr('onclick', 'saveTindakan(\'' + id + '\')').show();
            $('#modal-tindakan').modal({show:true, backdrop:'static'});

        } else if (select == 3) {
            $('#nosa_id_diagnosa_bpjs, #nosa_ket_diagnosa').val('');
            $('#nosa_id_diagnosa, #nosa_jenis_diagnosa').val('').trigger('change');
            $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
            $('#save_diagnosa').attr('onclick', 'saveDiagnosa(\'' + id + '\')').show();
            $('#modal-diagnosa').modal({show:true, backdrop:'static'});

        } else if (select == 4) {
            $('#lab_kategori, #lab_lab').val('').trigger('change');
            $('#load_lab, #warning_lab, #war_kategori_lab, #war_lab, #war_parameter').hide();
            $('#save_lab').attr('onclick', 'saveLab(\'' + id + '\')').show();
            $('#modal-lab').modal({show:true, backdrop:'static'});
        } else if (select == 5) {
            $('#ob_id_obat').val('').trigger('change');
            $('#jenis_form').show();
            $('#nama_form').show();
            $('#nama_obat_form').hide();
            $('#ob_stok_box').val('');
            $('#ob_stok_lembar').val('');
            $('#ob_stok_biji').val('');
            $('#ob_jenis_satuan').val('').trigger('change');
            $('#ob_jenis_satuan').attr('disabled', false);
            $('#ob_qty').val('');
            $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\')').show();
            $('#load_obat, #warning_obat, #war_ob_jenis_obat, #war_obat, #war_qty_obat').hide();
            $('#modal-obat').modal({show:true, backdrop:'static'});
        } else if (select == 7) {
            $('#resep_apotek').val('').trigger('change').attr('disabled', false);
            $('#resep_nama_obat').val('').trigger('change');
            $('#resep_keterangan').val('');
            $('#resep_qty').val('');
            $('#resep_jenis_satuan').val('biji').trigger('change');
            $('#resep_stok_box, #resep_stok_lembar, #resep_stok_biji').val('');
            $('#body_detail').html('');
            $('#desti_apotek').html('');
            $('#save_resep_head').show();
            $('#load_resep_head').hide();
            $('#desti_apotek').html('');
            $('#resep_apotek').attr("onchange", "var warn =$('#war_rep_apotek').is(':visible'); if (warn){$('#cor_rep_apotek').show().fadeOut(3000);$('#war_rep_apotek').hide()}; setObatPoli(this)");
            $('#resep_nama_obat').attr("onchange", "var warn =$('#war_rep_obat').is(':visible'); if (warn){$('#cor_rep_obat').show().fadeOut(3000);$('#war_rep_obat').hide()}; setStokObatApotek(this)");
            $('#body_detail').html('');
            $('#modal-resep-head').modal({show:true, backdrop:'static'});
            getJenisResep();
        } else if (select == 8) {
            $('#alergi').val('');
            $('#load_alergi').hide();
            $('#save_alergi').attr('onclick', 'saveAlergi(\'' + id + '\')').show();
            $('#modal-alergi').modal({show:true, backdrop:'static'});
        }

    }


    function saveDokter(id) {
        var idDokter = $('#dok_id_dokter').val();

        if (idDetailCheckup != '' && idDokter != '') {
            $('#save_dokter').hide();
            $('#load_dokter').show();
            if (id != '') {
                dwr.engine.setAsync(true);
                TeamDokterAction.editDokter(id, idDokter, function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        listDokter();
                        $('#modal-dokter').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(1);
                    } else {

                    }
                })
            } else {
                dwr.engine.setAsync(true);
                TeamDokterAction.saveDokter(idDetailCheckup, idDokter, function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        listDokter();
                        $('#modal-dokter').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(1);
                    } else {

                    }
                })
            }
        } else {
            $('#warning_dokter').show().fadeOut(5000);
            $('#war_dok').show();
        }
    }

    function listDokter() {
        var table = "";
        var data = [];
        var dokter = "";
        TeamDokterAction.listDokter(idDetailCheckup, function (response) {
            data = response;
            console.log(data);
            if (data != null) {
                $.each(data, function (i, item) {
                    table += "<tr>" +
                            "<td>" + item.idDokter + "</td>" +
                            "<td>" + item.namaDokter + "</td>" +
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDokter(\'' + item.idTeamDokter + '\',\'' + item.idDokter + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer; ">' + "</td>" +
                            "</tr>";
                    dokter = item.idDokter;
                });
            }
        });
        $('#tin_id_dokter').val(dokter);
        $('#body_dokter').html(table);
    }

    function listDokterKeterangan(idPelayanan) {
        var idx = idPelayanan.selectedIndex;
        var idPoli = idPelayanan.options[idx].value;
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

    function saveTindakan(id) {

        var idKategori = $('#tin_id_ketgori_tindakan').val();
        var idTindakan = $('#tin_id_tindakan').val();
        var idDokter = $('#tin_id_dokter').val();
        var idPerawat = 1;
        var qty = $('#tin_qty').val();
        var idJenisPeriksa = $('#jenis_pasien').val();

        if (idDetailCheckup != '' && idTindakan != '' && idDokter != '' && idPerawat != '' && qty > 0 && idKategori != '') {

            $('#save_tindakan').hide();
            $('#load_tindakan').show();

            if (id != '') {
                dwr.engine.setAsync(true);
                TindakanRawatAction.editTindakanRawat(id, idDetailCheckup, idTindakan, "RJ", idPerawat, qty, idJenisPeriksa, {
                    callback: function (response) {
                        if (response == "success") {
                            dwr.engine.setAsync(false);
                            listTindakan();
                            hitungStatusBiaya();
                            hitungCoverBiaya();
                            $('#modal-tindakan').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#close_pos').val(2);
                        } else {
                            $('#eror_dialog').dialog('open');
                            $('#save_tindakan').show();
                            $('#load_tindakan').hide();
                        }
                    }
                });
            } else {
                dwr.engine.setAsync(true);
                TindakanRawatAction.saveTindakanRawat(idDetailCheckup, idTindakan, "RJ", idPerawat, qty, idJenisPeriksa, {
                    callback: function (response) {
                        if (response == "success") {
                            dwr.engine.setAsync(false);
                            listTindakan();
                            hitungStatusBiaya();
                            hitungCoverBiaya();
                            $('#modal-tindakan').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#close_pos').val(2);
                        } else {
                            $('#eror_dialog').dialog('open');
                            $('#save_tindakan').show();
                            $('#load_tindakan').hide();
                        }
                    }
                });
            }
        } else {
            $('#warning_tindakan').show().fadeOut(5000);

            if (idKategori == '') {
                $('#war_kategori').show();
            }
            if (idTindakan == '') {
                $('#war_tindakan').show();
            }
            if (idPerawat == '') {
                $('#war_perawat').show();
            }
            if (qty <= 0 || qty == '') {
                $('#tin_qty').css('border', 'red solid 1px');
            }
        }
    }

    function listDokterTindakan() {

        var idPelayanan = $("#id_pelayanan").val();

        var option = "";
        CheckupAction.listOfDokter(idPelayanan, function (response) {
            option = "<option value=''>[Select One]</option>";
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idDokter + "'>" + item.namaDokter + "</option>";
                });
            } else {
                option = option;
            }
        });
        $('#dokter_tindakan').html(option);
    }

    function listTindakan() {

        var table = "";
        var table2 = "";
        var data = [];
        var trfTtl = 0;
        TindakanRawatAction.listTindakanRawat(idDetailCheckup, function (response) {
            data = response;
            if (data != null) {
                $.each(data, function (i, item) {

                    var tanggal = item.createdDate;
                    var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                    var tarif = "-";
                    var tarifTotal = "-";
                    var trfTotal = 0;
                    var qtyTotal = 0;
                    var perawat = "";
                    var btn = '<img border="0" class="hvr-grow" onclick="editTindakan(\'' + item.idTindakanRawat + '\',\'' + item.idTindakan + '\',\'' + item.idKategoriTindakan + '\',\'' + item.idPerawat + '\',\'' + item.qty + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">';

                    if (item.tarif != null) {
                        tarif = formatRupiah(item.tarif);
                        trfTotal += item.tarif;
                    }
                    if (item.tarifTotal != null) {
                        tarifTotal = formatRupiah(item.tarifTotal);
                        trfTtl += item.tarifTotal;
                    }
                    if (item.qty != null) {
                        qtyTotal += item.qty;
                    }
                    if (item.idPerawat != null) {
                        perawat = item.idPerawat;
                    }

                    if("Y" == item.approveFlag){
                        btn = "";
                    }

                    table += "<tr>" +
                            "<td>" + dateFormat + "</td>" +
                            "<td>" + item.namaTindakan + "</td>" +
                            "<td align='right'>" + tarif + "</td>" +
                            "<td align='center'>" + item.qty + "</td>" +
                            "<td align='right'>" + tarifTotal + "</td>" +
                            "<td align='center'>" + btn + "</td>" +
                            "</tr>";

                    table2 += "<tr>" +
                        "<td>" + dateFormat + "</td>" +
                        "<td>" + item.namaTindakan + "</td>" +
                        "<td align='center'></td>" +
                        "</tr>";

                });

                if("paket_perusahaan" == jenisPeriksaPasien || "paket_individu" == jenisPeriksaPasien){
                    $('#body_tindakan_paket').html(table2);
                }else{
                    table = table + "<tr>" +
                        "<td colspan='4'>Total</td>" +
                        "<td align='right'>" + formatRupiah(trfTtl) + "</td>" +
                        "<td></td>" +
                        "</tr>";
                    $('#body_tindakan').html(table);
                }
            }
        });

    }

    function saveDiagnosa(id) {

        var idDiagnosa = $('#nosa_id_diagnosa').val();
        var idDiagnosaBpjs = $('#nosa_id_diagnosa_bpjs').val();
        var ketDiagnosa = $('#nosa_ket_diagnosa').val();
        var jenisDiagnosa = $('#nosa_jenis_diagnosa').val();
        var jenisPasien = $('#jenis_pasien').val();
        var idDiag = "";

        if(jenisPasien == "bpjs" || jenisPasien == "ptpn"){
            idDiag = idDiagnosaBpjs;
        }else{
            idDiag = idDiagnosa;
        }

        if (idDetailCheckup != '' && idDiag != '' && jenisDiagnosa != '') {

            $('#save_diagnosa').hide();
            $('#load_diagnosa').show();

            if (id != '') {
                dwr.engine.setAsync(true);
                DiagnosaRawatAction.editDiagnosa(id, idDiag, jenisDiagnosa, ketDiagnosa, jenisPasien, idDetailCheckup, {
                    callback: function (response) {
                        if (response == "success") {
                            dwr.engine.setAsync(false);
                            listDiagnosa();
                            hitungStatusBiaya();
                            $('#modal-diagnosa').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#close_pos').val(3);
                        } else {

                        }
                    }
                })
            } else {
                dwr.engine.setAsync(true);
                DiagnosaRawatAction.saveDiagnosa(idDetailCheckup, idDiag, jenisDiagnosa, ketDiagnosa, jenisPasien, {
                    callback: function (response) {
                        if (response == "success") {
                            dwr.engine.setAsync(false);
                            listDiagnosa();
                            hitungStatusBiaya();
                            $('#modal-diagnosa').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#close_pos').val(3);
                        } else {

                        }
                    }
                })
            }
        } else {
            $('#warning_diagnosa').show().fadeOut(5000);
            if (idDiagnosa == '') {
                $('#war_diagnosa').show();
            }
            if (jenisDiagnosa == '') {
                $('#war_jenis_diagnosa').show();
            }
        }
    }

    function listDiagnosa() {

        var table = "";
        var data = [];

        DiagnosaRawatAction.listDiagnosa(idDetailCheckup, function (response) {
            data = response;
            if (data != null) {
                $.each(data, function (i, item) {
                    var id = "-";
                    var ket = "-";
                    var jen = "-";
                    var tanggal = item.createdDate;
                    var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));

                    if (item.idDiagnosa != null) {
                        id = item.idDiagnosa;
                    }
                    if (item.keteranganDiagnosa != null) {
                        ket = item.keteranganDiagnosa;
                    }
                    if (item.jenisDiagnosa != null) {
                        if (item.jenisDiagnosa == 0) {
                            jen = "Diagnosa Awal";
                        } else {
                            jen = "Diagnosa Akhir";
                        }
                    }
                    table += "<tr>" +
                            "<td>" + dateFormat + "</td>" +
                            "<td>" + id + "</td>" +
                            "<td>" + ket + "</td>" +
                            "<td>" + jen + "</td>" +
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDiagnosa(\'' + item.idDiagnosaRawat + '\',\'' + item.idDiagnosa + '\',\'' + item.jenisDiagnosa + '\', \''+item.keteranganDiagnosa+'\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">' + "</td>" +
                            "</tr>"
                });
            }
        });

        $('#body_diagnosa').html(table);
    }

    function listSelectLab(idKategori) {
        var option = "<option value=''>[Select One]</option>";
        if (idKategori != '') {
            LabAction.listLab(idKategori, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idLab + "'>" + item.namaLab + "</option>";
                    });
                    $('#lab_lab').html(option);
                } else {
                    o$('#lab_lab').html(option);
                }
            });
        } else {
            $('#lab_lab').html(option);
        }
    }

    function listSelectParameter(select) {
        var idx = select.selectedIndex;
        var idLab = select.options[idx].value;

        var option = "";
        if (idLab != '') {
            LabDetailAction.listLabDetail(idLab, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idLabDetail + "'>" + item.namaDetailPeriksa + "</option>";
                    });
                } else {
                    option = option;
                }
            });
        } else {
            option = option;
        }

        $('#lab_parameter').html(option);
    }

    function saveLab(id) {

        var idKategori = $('#lab_kategori').val();
        var idLab = $('#lab_lab').val();
        var idParameter = $('#lab_parameter').val();

        if (idDetailCheckup != '' && idKategori != '' && idLab != '' && idParameter != null) {

            $('#save_lab').hide();
            $('#load_lab').show();

            if (id != '') {
                dwr.engine.setAsync(true);
                PeriksaLabAction.editOrderLab(id, idLab, idParameter, {
                    callback: function (response) {
                        if (response == "success") {
                            dwr.engine.setAsync(false);
                            listLab();
                            $('#modal-lab').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#close_pos').val(4);
                        } else {

                        }
                    }
                })
            } else {
                dwr.engine.setAsync(true);
                PeriksaLabAction.saveOrderLab(idDetailCheckup, idLab, idParameter, {
                    callback: function (response) {
                        if (response == "success") {
                            dwr.engine.setAsync(false);
                            listLab();
                            $('#modal-lab').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#close_pos').val(4);
                        } else {

                        }
                    }
                })
            }
        } else {
            $('#warning_lab').show().fadeOut(5000);
            if (idKategori == '') {
                $('#war_kategori_lab').show();
            }
            if (idLab == '') {
                $('#war_lab').show();
            }
            if (idParameter == '' || idParameter == null) {
                $('#war_parameter').show();
            }
        }
    }

    function listLab() {

        var table = "";
        var data = [];

        PeriksaLabAction.listOrderLab(idDetailCheckup, function (response) {
            data = response;
            if (data != null) {
                $.each(data, function (i, item) {
                    var pemeriksaan = "-";
                    var status = "-";
                    var lab = "-";
                    var tanggal = item.createdDate;
                    var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                    var btn = '<img border="0" class="hvr-grow" onclick="editLab(\'' + item.idPeriksaLab + '\',\'' + item.idLab + '\',\'' + item.idKategoriLab + '\',\''+item.kategoriLabName+'\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">';
                    var tipe = "";

                    if(item.kategoriLabName == "Radiologi"){
                        tipe = "radiologi";
                    }else{
                        tipe = "lab";
                    }

                    if (item.idLab != null) {
                        pemeriksaan = item.idLab;
                    }
                    if (item.statusPeriksaName != null) {
                        status = item.statusPeriksaName;
                    }
                    if (item.labName != null) {
                        lab = item.labName;
                    }
                    if(item.approveFlag == "Y"){
                       btn = '<a target="_blank" href="printLabRadiologi_checkupdetail.action?id='+idDetailCheckup+'&tipe='+tipe+'&lab='+item.idPeriksaLab+'"><img border="0" class="hvr-grow" src="<s:url value="/pages/images/icons8-print-25.png"/>" style="cursor: pointer;"></a>';
                    }

                    if("paket_perusahaan" == jenisPeriksaPasien || "paket_individu" == jenisPeriksaPasien){
                        table += "<tr>" +
                            "<td>" + dateFormat + "</td>" +
                            "<td>" + lab + "</td>" +
                            "<td>" + status + "</td>" +
                            "<td>" + item.kategoriLabName + "</td>" +
                            "<td align='center'></td>" +
                            "</tr>";
                    }else{
                        table += "<tr>" +
                            "<td>" + dateFormat + "</td>" +
                            "<td>" + lab + "</td>" +
                            "<td>" + status + "</td>" +
                            "<td>" + item.kategoriLabName + "</td>" +
                            "<td align='center'>" + btn + "</td>" +
                            "</tr>";
                    }

                });
            }
        });

        $('#body_lab').html(table);
    }

    function saveObat(idInap) {

        var jenisSatuan = $('#ob_jenis_satuan').val();
        var obat = $('#ob_id_obat').val();
        var qty = $('#ob_qty').val();
        var id = "";
        var nama = "";
        var qtyBox = 0;
        var qtyLembar = 0;
        var qtyBiji = 0;
        var lembarPerBox = 0;
        var bijiPerLembar = 0;
        var stok = 0;

        if (obat != '') {
            if (obat.split('|')[0] != 'null' && obat.split('|')[0] != '') {
                id = obat.split('|')[0];
            }
            if (obat.split('|')[1] != 'null' && obat.split('|')[1] != '') {
                nama = obat.split('|')[1];
            }
            if (obat.split('|')[2] != 'null' && obat.split('|')[2] != '') {
                qtyBox = obat.split('|')[2];
            }
            if (obat.split('|')[3] != 'null' && obat.split('|')[3] != '') {
                qtyLembar = obat.split('|')[3];
            }
            if (obat.split('|')[4] != 'null' && obat.split('|')[4] != '') {
                qtyBiji = obat.split('|')[4];
            }
            if (obat.split('|')[5] != 'null' && obat.split('|')[5] != '') {
                lembarPerBox = obat.split('|')[5];
            }
            if (obat.split('|')[6] != 'null' && obat.split('|')[6] != '') {
                bijiPerLembar = obat.split('|')[6];
            }
        }

        if (idInap != '') {

            var idObat = $('#set_id_obat').val();
            qtyBox = $('#ob_stok_box').val();
            qtyLembar = $('#ob_stok_lembar').val();
            qtyBiji = $('#ob_stok_biji').val();
            lembarPerBox = $('#set_lembar_perbox').val();
            bijiPerLembar = $('#set_biji_perlembar').val();

            if ("box" == jenisSatuan) {
                stok = qtyBox;
            }
            if ("lembar" == jenisSatuan) {
                stok = parseInt(qtyLembar) + (parseInt(lembarPerBox * parseInt(qtyBox)));
            }
            if ("biji" == jenisSatuan) {
                stok = parseInt(qtyBiji) + ((parseInt(lembarPerBox * parseInt(qtyBox))) * parseInt(bijiPerLembar));
            }

            if (parseInt(qty) <= parseInt(stok)) {

                $('#save_obat').hide();
                $('#load_obat').show();

                dwr.engine.setAsync(true);
                ObatInapAction.editObatInap(idInap, idDetailCheckup, idObat, qty, jenisSatuan, function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        listObat();
                        $('#modal-obat').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(5);
                    } else {

                    }
                })
            } else {
                $('#warning_obat').show().fadeOut(5000);
                $('#obat_error').text("Jumlah obat tidak boleh melebihi stok..!");
            }
        } else {
            if (idDetailCheckup != '' && obat != '' && parseInt(qty) > 0 && jenisSatuan != '') {

                if ("box" == jenisSatuan) {
                    stok = qtyBox;
                }
                if ("lembar" == jenisSatuan) {
                    stok = parseInt(qtyLembar) + (parseInt(lembarPerBox * parseInt(qtyBox)));
                }
                if ("biji" == jenisSatuan) {
                    stok = parseInt(qtyBiji) + ((parseInt(lembarPerBox * parseInt(qtyBox))) * parseInt(bijiPerLembar));
                }

                if (parseInt(qty) <= parseInt(stok)) {

                    $('#save_obat').hide();
                    $('#load_obat').show();

                    dwr.engine.setAsync(true);
                    ObatInapAction.saveObatInap(idDetailCheckup, id, qty, jenisSatuan, function (response) {
                        if (response == "success") {
                            dwr.engine.setAsync(false);
                            listObat();
                            $('#modal-obat').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#close_pos').val(5);
                        } else {

                        }
                    })
                } else {
                    $('#warning_obat').show().fadeOut(5000);
                    $('#obat_error').text("Jumlah obat tidak boleh melebihi stok..!");
                }
            } else {
                $('#warning_obat').show().fadeOut(5000);
                $('#obat_error').text("Silahkan cek kembali data inputan..!");
                if (jenisSatuan == '' || jenisSatuan == null) {
                    $('#war_ob_jenis_satuan').show();
                }
                if (obat == '' || obat == null) {
                    $('#war_obat').show();
                }
                if (qty == '' || qty < 1) {
                    $('#war_qty_obat').show();
                }
            }
        }
    }

    function listObat() {
        var table = "";
        var data = [];

        ObatInapAction.listObatInap(idDetailCheckup, function (response) {
            data = response;
            if (data != null) {
                $.each(data, function (i, item) {
                    var tanggal = item.createdDate;
                    var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                    var id = "-";
                    var obat = "-";
                    var qty = "-";
                    var jenis = "-";
                    if (item.idObat != null) {
                        id = item.idObat;
                    }
                    if (item.namaObat != null) {
                        obat = item.namaObat;
                    }
                    if (item.qty != null) {
                        qty = item.qty;
                    }
                    if (item.jenisSatuan != null) {
                        jenis = item.jenisSatuan;
                    }

                    table += "<tr>" +
                            "<td>" + dateFormat + "</td>" +
                            "<td>" + id + "</td>" +
                            "<td>" + obat + "</td>" +
                            "<td align='center'>" + qty + "</td>" +
                            "<td>" + jenis + "</td>" +
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editObat(\'' + item.idObatInap + '\',\'' + id + '\',\'' + qty + '\',\'' + jenis + '\',\'' + obat + '\',\'' + item.qtyBox + '\',\'' + item.qtyLembar + '\',\'' + item.qtyBiji + '\',\'' + item.lembarPerBox + '\',\'' + item.bijiPerLembar + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">' + "</td>" +
                            "</tr>"
                });
            }
        });

        $('#body_obat').html(table);
    }

    function setStokObat(select) {

        var idx = select.selectedIndex;
        var id = "";
        var nama = "";
        var qtyBox = "";
        var qtyLembar = "";
        var qtyBiji = "";
        var lembarPerBox = "";
        var bijiPerLembar = "";

        if (idx > 0) {

            var obat = select.options[idx].value;

            if (obat.split('|')[0] != 'null' && obat.split('|')[0] != '') {
                id = obat.split('|')[0];
            }
            if (obat.split('|')[1] != 'null' && obat.split('|')[1] != '') {
                nama = obat.split('|')[1];
            }
            if (obat.split('|')[2] != 'null' && obat.split('|')[2] != '') {
                qtyBox = obat.split('|')[2];
            }
            if (obat.split('|')[3] != 'null' && obat.split('|')[3] != '') {
                qtyLembar = obat.split('|')[3];
            }
            if (obat.split('|')[4] != 'null' && obat.split('|')[4] != '') {
                qtyBiji = obat.split('|')[4];
            }

            $('#ob_stok_box').val(qtyBox);
            $('#ob_stok_lembar').val(qtyLembar);
            $('#ob_stok_biji').val(qtyBiji);

        }
    }

    function editDokter(id, idDokter) {
        $('#load_dokter, #war_dok').hide();
        $('#save_dokter').attr('onclick', 'saveDokter(\'' + id + '\')').show();
        $('#dok_id_dokter').val(idDokter).trigger('change');
        $('#modal-dokter').modal({show:true, backdrop:'static'});
    }

    function editTindakan(id, idTindakan, idKategori, idPerawat, qty) {
        $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
        $('#tin_id_ketgori_tindakan').val(idKategori).trigger('change');
        $('#tin_id_tindakan').val(idTindakan).trigger('change');
        $('#tin_id_perawat').val(idPerawat).trigger('change');
        $('#tin_qty').val(qty);
        $('#save_tindakan').attr('onclick', 'saveTindakan(\'' + id + '\')').show();
        $('#modal-tindakan').modal({show:true, backdrop:'static'});
    }

    function editDiagnosa(id, idDiagnosa, jenis, ket) {
        var jenisPasien = $('#jenis_pasien').val();
        $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
        if(jenisPasien == "bpjs" || jenisPasien == "ptpn"){
            $('#nosa_id_diagnosa_bpjs').val(idDiagnosa);
            $('#nosa_ket_diagnosa').val(ket);
        }else{
            $('#nosa_id_diagnosa').val(idDiagnosa).trigger('change');
        }
        $('#nosa_jenis_diagnosa').val(jenis).trigger('change');
        $('#save_diagnosa').attr('onclick', 'saveDiagnosa(\'' + id + '\')').show();
        $('#modal-diagnosa').modal({show:true, backdrop:'static'});
    }

    function editLab(id, idLab, idKategoriLab, kategoriName) {
        $('#load_lab, #warning_lab, #war_kategori_lab, #war_lab, #war_parameter').hide();
        $('#save_lab').attr('onclick', 'saveLab(\'' + id + '\')').show();
        $('#lab_kategori').val(idKategoriLab).trigger('change');
        var idParameter = [];
        PeriksaLabAction.listParameterPemeriksaan(id, kategoriName, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    idParameter.push(item.idLabDetail);
                });
            }
        });
        $('#lab_lab').val(idLab).trigger('change');
        $('#lab_parameter').val(idParameter).trigger('change');
        $('#modal-lab').modal({show:true, backdrop:'static'});
    }

    function editObat(id, idobat, qty, jenis, namaObat, qtyBox, qtyLembar, qtyBiji, lembarPerBox, bijiPerLembar) {
        var qtyBox1 = "";
        var qtyLembar1 = "";
        var qtyBiji1 = "";

        if (qtyBox != 'null') {
            qtyBox1 = qtyBox;
        }

        if (qtyLembar != 'null') {
            qtyLembar1 = qtyLembar;
        }

        if (qtyBiji != 'null') {
            qtyBiji1 = qtyBiji;
        }
        $('#load_obat, #warning_obat, #war_ob_jenis_satuan, #war_obat, #war_qty_obat').hide();
        $('#jenis_form').hide();
        $('#nama_form').hide();
        $('#nama_obat_form').show();
        $('#nama_obat').val(namaObat);
        $('#ob_qty').val(qty);
        $('#ob_stok_box').val(qtyBox1);
        $('#ob_stok_lembar').val(qtyLembar1);
        $('#ob_stok_biji').val(qtyBiji1);
        $('#set_id_obat').val(idobat);
        $('#set_lembar_perbox').val(lembarPerBox);
        $('#set_biji_perlembar').val(bijiPerLembar);
        $('#ob_jenis_satuan').val(jenis).trigger('change').attr('disabled', true);
        $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\')').show();
        $('#modal-obat').modal({show:true, backdrop:'static'});
    }

    function listSelectObatEdit(select) {
        var idx = select.selectedIndex;
        var idJenis = select.options[idx].value;
        var option = "<option value=''>[Select One]</option>";
        if (idJenis != '') {
            ObatAction.listObatByJenis(idJenis, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idObat + "'>" + item.namaObat + "</option>";
                    });
                } else {
                    option = option;
                }
            });
        } else {
            option = option;
        }

        $('#ob_id_obat').html(option);
    }

    function listSelectObat(select) {
        var idx = select.selectedIndex;
        var idJenis = select.options[idx].value;
        var option = "<option value=''>[Select One]</option>";
        if (idJenis != '') {
            ObatAction.listObat(idJenis, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idObat + "'>" + item.namaObat + "</option>";
                    });
                    $('#ob_id_obat').html(option);
                    $('#resep_nama_obat').html(option);
                } else {
                    option = option;
                }
            });
        } else {
            option = option;
        }
    }

    function showFormCekup(select) {
        var idx = select.selectedIndex;
        var idKet = select.options[idx].value;
        if (idKet == "Checkup Ulang") {
            $('#form-cekup').show();
        } else {
            $('#form-cekup').hide();
        }
    }

    function addObatToList() {

        var apotek = $('#resep_apotek').val();
        var obat = $('#resep_nama_obat').val();
        var qty = $('#resep_qty').val();
        var jenisSatuan = $('#resep_jenis_satuan').val();
        var stokBox = $('#resep_stok_box').val();
        var stokLembar = $('#resep_stok_lembar').val();
        var stokBiji = $('#resep_stok_biji').val();
        var cek = false;
        var data = $('#tabel_rese_detail').tableToJSON();
        var id = "";
        var nama = "";
        var qtyBox = 0;
        var qtyLembar = 0;
        var qtyBiji = 0;
        var lembarPerBox = 0;
        var bijiPerLembar = 0;

        var listObat = $("[name=cek_waktu]:checked");
        var pemberian = $("#resep_waktu").val();
        var jenisResep = $("#jenis_resep").val();
        var flagKronis = $("#val-kronis").val();
        var hariKronis = "";
        var harga = "";

        if (flagKronis == "Y"){
            hariKronis = $("#hari-kronis").val();
        }

        var i = 0;
        var waktu = [];
        $.each(listObat, function (idx, item) {
            if(item.checked){
                waktu.push($(this).val());
                i = i+1;
            }
        });

        var ket = pemberian+" Makan. "+i+"x1. "+waktu.join(", ");

        if (obat != '' && ket != '' && qty != '' && apotek != '' && jenisSatuan != '' && waktu.length > 0) {

            var idPelayanan = apotek.split('|')[0];
            var namaPelayanan = apotek.split('|')[1];

            if (obat.split('|')[0] != 'null' && obat.split('|')[0] != '') {
                id = obat.split('|')[0];
            }
            if (obat.split('|')[1] != 'null' && obat.split('|')[1] != '') {
                nama = obat.split('|')[1];
            }
            if (obat.split('|')[2] != 'null' && obat.split('|')[2] != '') {
                qtyBox = obat.split('|')[2];
            }
            if (obat.split('|')[3] != 'null' && obat.split('|')[3] != '') {
                qtyLembar = obat.split('|')[3];
            }
            if (obat.split('|')[4] != 'null' && obat.split('|')[4] != '') {
                qtyBiji = obat.split('|')[4];
            }
            if (obat.split('|')[5] != 'null' && obat.split('|')[5] != '') {
                lembarPerBox = obat.split('|')[5];
            }
            if (obat.split('|')[6] != 'null' && obat.split('|')[6] != '') {
                bijiPerLembar = obat.split('|')[6];
            }
            if (obat.split('|')[8] != 'null' && obat.split('|')[8] != '') {
                harga = obat.split('|')[8];
            }


            var stok = 0;

            if ("box" == jenisSatuan) {
                stok = qtyBox;
            }
            if ("lembar" == jenisSatuan) {
                stok = parseInt(qtyLembar) + (parseInt(lembarPerBox * parseInt(qtyBox)));
            }
            if ("biji" == jenisSatuan) {
                stok = parseInt(qtyBiji) + ((parseInt(lembarPerBox * parseInt(qtyBox))) * parseInt(bijiPerLembar));
            }

            if (parseInt(qty) <= parseInt(stok)) {

                $.each(data, function (i, item) {
                    if (item.ID == id) {
                        cek = true;
                    }
                });

                if (cek) {
                    $('#warning_data_exits').show().fadeOut(5000);
                } else {
                    var totalHarga = parseInt(qty) * parseInt(harga);
                    $('#resep_apotek').attr('disabled', true);
                    $('#desti_apotek').html(namaPelayanan);
                    var row = '<tr id=' + id + '>' +
                            '<td>' + id + '</td>' +
                            '<td>' + nama + '</td>' +
                            '<td align="center">' + qty + '</td>' +
                            '<td align="center">' + jenisSatuan + '</td>' +
                            '<td>' + ket + '</td>' +
                            '<td>' + jenisResep + '</td>' +
                            '<td>' + labelKronis(flagKronis) + '</td>' +
                            '<td aling="center">' + hariKronis + '</td>' +
                            '<td aling="center">' + formatRupiah(totalHarga) + '</td>' +
                            '<td align="center"><img border="0" onclick="delRowObat(\'' + id + '\',\''+totalHarga+'\')" class="hvr-grow" src="<s:url value="/pages/images/delete-flat.png"/>" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                            '</tr>';
                    $('#body_detail').append(row);
                    var total = $('#total_harga_obat').val();
                    var tot = 0;
                    if(total != ""){
                        tot = total.replace(/[.]/g, '');
                    }
                    var jumlah = parseInt(totalHarga) + parseInt(tot);
                    $('#total_harga_obat').val(formatRupiah(jumlah));
                }
            } else {
                $('#warning_resep_head').show().fadeOut(5000);
                $('#msg_resep').text('Qty tidak boleh melebihi stok obat..!');
                $('#modal-resep-head').scrollTop(0);
            }

        } else {
            if (jenisSatuan == '' || jenisSatuan == null) {
                $('#war_rep_jenis_satuan').show();
            }
            if (apotek == '' || apotek == null) {
                $('#war_rep_apotek').show();
            }
            if (obat == '' || obat == null) {
                $('#war_rep_obat').show();
            }
            if (qty == '' || qty <= 0) {
                $('#war_rep_qty').show();
            }
            if (waktu.length == 0) {
                $('#war_rep_cek_waktu').show();
            }
            $('#warning_resep_head').show().fadeOut(5000);
            $('#msg_resep').text('Silahkan cek kembali data inputan!');
            $('#modal-resep-head').scrollTop(0);
        }
    }

    function delRowObat(id, harga) {
        $('#' + id).remove();
        var total = $('#total_harga_obat').val();
        var tot = 0;
        if(total != ""){
            tot = total.replace(/[.]/g, '');
        }
        var jumlah = parseInt(tot) - parseInt(harga);
        $('#total_harga_obat').val(formatRupiah(jumlah));
    }

    function saveResepObatTtd() {

        var idDokter = $('#tin_id_dokter').val();
        var data = $('#tabel_rese_detail').tableToJSON();
        var stringData = JSON.stringify(data);
        var idPelayanan = $('#resep_apotek').val();
        var apotek = $('#resep_apotek').val();

        if (stringData != '[]') {
            $('#modal-ttd').modal({show:true, backdrop:'static'});
        } else {
            $('#warning_resep_head').show().fadeOut(5000);
            $('#msg_resep').text("Silahkan cek kembali data inputan anda..!");
            $('#modal-resep-head').scrollTop(0);
        }
    }

    function clearConvas(){
        var canvas = document.getElementById('ttd_canvas');
        const context = canvas.getContext('2d');
        context.clearRect(0, 0, canvas.width, canvas.height);
    }

    function saveResepObat() {
        $('#modal-ttd').modal('hide');
        var idDokter = $('#tin_id_dokter').val();
        var jenisResep = $('#select-jenis-resep').val();
        var data = $('#tabel_rese_detail').tableToJSON();
        var stringData = JSON.stringify(data);
        var idPelayanan = $('#resep_apotek').val();
        var apotek = $('#resep_apotek').val();
        var canvas = document.getElementById('ttd_canvas');
        var dataURL = canvas.toDataURL("image/png"),
            dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
        var ttd  = isBlank(canvas);
        if (stringData != '[]' && !ttd) {
            var idPelayanan = apotek.split('|')[0];
            var namaPelayanan = apotek.split('|')[1];
            $('#save_resep_head').hide();
            $('#load_resep_head').show();
            dwr.engine.setAsync(true);
            PermintaanResepAction.saveResepPasien(idDetailCheckup, idPoli, idDokter, idPasien, stringData, idPelayanan, dataURL, jenisResep, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(9);
                        $('#save_resep_head').show();
                        $('#load_resep_head').hide();
                        $('#modal-resep-head').modal('hide');
                        listResepPasien();
                    } else {
                        $('#warning_resep_head').show().fadeOut(5000);
                        $('#msg_resep').text("Silahkan cek kembali data inputan anda..!");
                        $('#save_resep_head').show();
                        $('#load_resep_head').hide();
                    }
                }
            });
        } else {
            $('#warning_resep_head').show().fadeOut(5000);
            $('#msg_resep').text("Silahkan cek kembali data inputan anda..!");
            $('#modal-resep-head').scrollTop(0);
        }
    }

    function isBlank(canvas){
        const blank = document.createElement("canvas");
        blank.width = canvas.width;
        blank.height = canvas.height;
        return canvas.toDataURL() === blank.toDataURL();
    }

    function listResepPasien() {

        var table = "";
        var data = [];

        PermintaanResepAction.listResepPasien(idDetailCheckup, function (response) {
            data = response;
            if (data != null) {
                $.each(data, function (i, item) {
                    var idResep = "";
                    var tanggal = item.createdDate;
                    var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));

                    if (item.idPermintaanResep != null) {
                        idResep = item.idPermintaanResep;
                    }

                    table += "<tr>" +
                            "<td>" + dateFormat + "</td>" +
                            "<td>" + idResep + "</td>" +
                            "<td align='center'>" +
                        <%--'<img border="0" class="hvr-grow" onclick="detailResep(\'' + item.idApprovalObat + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">' +--%>
                            ' <img onclick="printResep(\'' + idResep + '\')" class="hvr-grow" src="<s:url value="/pages/images/icons8-print-25.png"/>" style="cursor: pointer;">' +
                            "</td>" +
                            "</tr>"
                });
            }
        });

        $('#body_resep').html(table);
    }

    function printResep(id) {
        window.open('printResepPasien_checkupdetail.action?id=' + idDetailCheckup + '&idResep=' + id, '_blank');
    }

    function detailResep(id) {
        $('#modal-resep-detail').modal({show:true, backdrop:'static'});
        listDetailResepPasien(id);
    }

    function listDetailResepPasien(idApprovalObat) {

        var table = "";
        var data = [];

        PermintaanResepAction.listDetail(idApprovalObat, function (response) {
            data = response;
            if (data != null) {
                $.each(data, function (i, item) {

                    var qty = "";
                    var namaObat = "";
                    var ket = "";
                    var idObat = "";

                    if (item.idObat != null) {
                        idObat = item.idObat;
                    }

                    if (item.qty != null) {
                        qty = item.qty;
                    }

                    if (item.namaObat != null) {
                        namaObat = item.namaObat;
                    }

                    if (item.keterangan != null) {
                        ket = item.keterangan;
                    }

                    table += "<tr>" +
                            "<td>" + '<span id=obat' + idObat + '>' + namaObat + '</span><input style="display:none; width: 120px;" type="text" id=newObat' + idObat + ' class="form-control"><input type="hidden" id=idObat' + idObat + '>' + "</td>" +
                            "<td>" + '<span id=qty' + idObat + '>' + qty + '</span>' + '<input type="hidden" id=newId' + idObat + ' value=' + idObat + '>' +
                            '<input style="display:none; width: 80px" type="number" id=newQty' + idObat + ' class="form-control">' + "</td>" +
                            "<td>" + '<span id=ket' + idObat + '>' + ket + '</span>' +
                            '<select class="form-control" id=newKet' + idObat + ' style="display:none"' +
                            '<option value="">[Select One]</option>' +
                            '<option value="2 x 1 /Hari">2 x 1 /Hari</option>' +
                            '<option value="3 x 1 /Hari">3 x 1 /Hari</option>' +
                            '</select>' + "</td>" +
                            "<td align='center'>" + '<img border="0" id=' + idObat + ' class="hvr-grow" onclick="editObatResep(\'' + item.idTransaksiObatDetail + '\',\'' + idObat + '\',\'' + qty + '\',\'' + ket + '\',\'' + namaObat + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">' +
                            '<img border="0" id=save' + idObat + ' class="hvr-grow" onclick="saveDetailResep(\'' + item.idTransaksiObatDetail + '\',\'' + idObat + '\',\'' + item.idApprovalObat + '\')" src="<s:url value="/pages/images/icons8-save-25.png"/>" style="cursor: pointer; display: none">' + "</td>" +
                            "</tr>"
                });
            }
        });

        $('#body_detail_rep').html(table);
    }

    function editObatResep(id, idObat, qty, ket, namaObat) {

        if ($('#' + idObat).attr('src') == '/simrs/pages/images/icons8-create-25.png') {
            var url = '<s:url value="/pages/images/cnacel-flat.png"/>';
            $('#' + idObat).attr('src', url);
            $('#obat' + idObat).hide();
            $('#qty' + idObat).hide();
            $('#ket' + idObat).hide();

            $('#newObat' + idObat).show().val(namaObat);
            $('#newQty' + idObat).show().val(qty);
            $('#newKet' + idObat).show().val(ket);
            $('#save' + idObat).show();

        } else {
            var url = '<s:url value="/pages/images/icons8-create-25.png"/>';
            $('#' + idObat).attr('src', url);
            $('#obat' + idObat).show();
            $('#qty' + idObat).show();
            $('#ket' + idObat).show();

            $('#newObat' + idObat).hide();
            $('#newQty' + idObat).hide();
            $('#newKet' + idObat).hide();
            $('#save' + idObat).hide();
        }
    }

    function saveDetailResep(id, idObat, idApp) {

        var obat = $('#newId' + idObat).val();
        var qty = $('#newQty' + idObat).val();
        var ket = $('#newKet' + idObat).val();

        if (obat != '' && qty != '' && ket != '') {
            $('#save_resep_head').hide();
            $('#load_resep_head').show();
            dwr.engine.setAsync(true);
            PermintaanResepAction.saveEditDetail(id, obat, qty, ket, {
                callback: function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#success_detail').show().fadeOut(5000);
                        listDetailResepPasien(idApp);
                    } else {
                        $('#warning_resep_head').show().fadeOut(5000);
                        $('#save_resep_head').show();
                        $('#load_resep_head').hide();
                    }
                }
            });
        } else {
            $('#warning_detail').show().fadeOut(5000);
        }
    }

    function setStokObatApotek(select) {

        var id = "";
        var nama = "";
        var qtyBox = "";
        var qtyLembar = "";
        var qtyBiji = "";
        var lembarPerBox = "";
        var bijiPerLembar = "";
        var flagKronis = "";
        var idx = select.selectedIndex;

        if (idx > 0) {
            var idObat = select.options[idx].value;
            if (idObat != null && idObat != '') {

                if (idObat.split('|')[0] != 'null' && idObat.split('|')[0] != '') {
                    id = idObat.split('|')[0];
                }
                if (idObat.split('|')[1] != 'null' && idObat.split('|')[1] != '') {
                    nama = idObat.split('|')[1];
                }
                if (idObat.split('|')[2] != 'null' && idObat.split('|')[2] != '') {
                    qtyBox = idObat.split('|')[2];
                }
                if (idObat.split('|')[3] != 'null' && idObat.split('|')[3] != '') {
                    qtyLembar = idObat.split('|')[3];
                }
                if (idObat.split('|')[4] != 'null' && idObat.split('|')[4] != '') {
                    qtyBiji = idObat.split('|')[4];
                }
                if (idObat.split('|')[5] != 'null' && idObat.split('|')[5] != '') {
                    lembarPerBox = idObat.split('|')[5];
                }
                if (idObat.split('|')[6] != 'null' && idObat.split('|')[6] != '') {
                    bijiPerLembar = idObat.split('|')[6];
                }
                if (idObat.split('|')[7] != 'null' && idObat.split('|')[7] != '') {
                    flagKronis = idObat.split('|')[7];
                }

                var total = parseInt(qtyBiji)+(parseInt(qtyBox)*parseInt(lembarPerBox))+(parseInt(qtyLembar)*parseInt(bijiPerLembar));

                if (flagKronis == "Y"){
                    labelKronis(flagKronis);
                    $("#form-hari").show();
                } else {
                    labelKronis(flagKronis);
                    $("#form-hari").hide();
                }

                $('#resep_stok_biji').val(total);
                $("#h-qty-default").val(bijiPerLembar);

                $('#resep_keterangan').val('');
                $('#resep_qty').val(bijiPerLembar);
                $('#resep_jenis_satuan').val('biji').trigger('change');
            }
        }
    }

    function savePenunjangPasien() {

        var tinggi = $('#tinggi').val();
        var berat = $('#berat').val();

        if (noCheckup != '' && tinggi != '' && berat != '') {
            $('#save_penunjang').hide();
            $('#load_penunjang').show();
            dwr.engine.setAsync(true);
            CheckupAction.savePenunjangPasien(tinggi, berat, noCheckup, function (response) {
                if (response == "success") {
                    dwr.engine.setAsync(false);
                    $('#success_penunjang').show().fadeOut(5000);
                    $('#save_penunjang').show();
                    $('#load_penunjang').hide();
                    hitungBmi();
                } else {
                    $('#save_penunjang').show();
                    $('#load_penunjang').hide();
                }
            })
        } else {
            $('#warning_penunjang').show().fadeOut(5000);
        }
    }

    function resetAll() {
        $('#resep_apotek').val('').trigger('change').attr('disabled', false);
        $('#resep_nama_obat, #resep_jenis_satuan').val('').trigger('change');
        $('#resep_keterangan').val('');
        $('#resep_qty').val('');
        $('#resep_stok_box, #resep_stok_lembar, #resep_stok_biji').val('');
        $('#body_detail').html('');
        $('#desti_apotek').html('');
    }

    function setObatPoli(select) {
        var idx = select.selectedIndex;
        var poli = select.options[idx].value;
        var idPel = poli.split('|')[0];
        var namePel = poli.split('|')[1];
        var option = "<option value=''>[Select One]</option>";
        var jenisPasien = $('#jenis_pasien').val();

        if (poli != '') {
            ObatPoliAction.getSelectOptionObatByPoli(idPel, jenisPasien, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idObat + "|" + item.namaObat + "|" + item.qtyBox + "|" + item.qtyLembar + "|" + item.qtyBiji + "|" + item.lembarPerBox + "|" + item.bijiPerLembar + "|" + item.flagKronis + "|" + item.harga + "'>" + item.namaObat + "</option>";
                    });
                    $('#resep_nama_obat').html(option);
                }
            });
        } else {
            option = "";
        }
    }

    function labelKronis(flag){
        if (flag == "Y"){
            $("#label-kronis").show();
            $("#val-kronis").val(flag);
            return 'Obat Kronis';
        } else {
            $("#label-kronis").hide();
            $("#val-kronis").val("");
            return "";
        }
    }

    function confirmSaveAllTindakan(){
        $('#modal-confirm-dialog').modal({show:true, backdrop:'static'});
        $('#save_con').attr('onclick','saveAllTindakan()');
    }

    function saveAllTindakan(){
        $('#modal-confirm-dialog').modal('hide');
        $('#save_all').hide();
        $('#load_all').show();
        var idJenisPeriksa = $('#jenis_pasien').val();
        dwr.engine.setAsync(true);
        CheckupDetailAction.saveApproveAllTindakanRawatJalan(idDetailCheckup, idJenisPeriksa, {
            callback : function (response) {
                if(response.status == "success"){
                    $('#success_all').show().fadeOut(5000);
                    $('#msg_all_suc').text(response.message);
                    $('#save_all').show();
                    $('#load_all').hide();
                    listTindakan();
                }else{
                    $('#warning_all').show().fadeOut(5000);
                    $('#msg_all_war').text(response.message);
                    $('#save_all').show();
                    $('#load_all').hide();
                }
            }});
    }

    function defaultValByJenisSatuan(name) {
        var nilai = "1";
        if (name == "biji"){
            nilai = $("#h-qty-default").val();
        }
        $("#resep_qty").val(nilai);

    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>