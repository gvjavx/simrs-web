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
        .btn {
            margin-top: 7px;
        }

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

        .radio-margin{
            margin-top: -7px;
            margin-left: 1px;
        }

        .paint-canvas-ttd {
            border: 1px black solid;
            margin: 1rem;
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
            E-Rekam Medik
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
                                    <s:hidden id="jenis_kelamin" name="headerDetailCheckup.jenisKelamin"></s:hidden>

                                    <s:if test='headerDetailCheckup.idJenisPeriksaPasien == "bpjs"'>
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
                                    <s:if test='headerDetailCheckup.namaAsuransi != null && headerDetailCheckup.namaAsuransi != ""'>
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
                    </div>
                    <s:hidden name="headerDetailCheckup.idDetailCheckup" id="id_detail_checkup"></s:hidden>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-user"></i> Rekam Medik</h3>
                    </div>
                    <div class="box-body">
                        <div class="col-md-4">
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-print"></i> Cetakan</h3>
                            </div>
                            <div class="box-body">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-print"></i> Action
                                    </button>
                                    <button type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a target="_blank"
                                               href="printSuratPernyataan_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>&tipe=CK01">
                                            <i class="fa fa-print"></i>General Consent</a></li>
                                        <li><a target="_blank"
                                               href="printSuratPernyataan_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>&tipe=CK02">
                                            <i class="fa fa-print"></i>Pelepasan Informasi</a></li>
                                        <li><a target="_blank"
                                               href="printSuratPernyataan_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>&tipe=CK03">
                                            <i class="fa fa-print"></i>Lembar Konsultasi</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-file-o"></i> MPP </h3><small> (Manajemen Pelayanan Pasien)</small>
                            </div>
                            <div class="box-body">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-success"><i class="fa fa-edit"></i> Action
                                    </button>
                                    <button type="button" class="btn btn-success dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a style="cursor: pointer" onclick="showModalMpp('evaluasi_awal')"><i class="fa fa-circle-o"></i>Form-A Evaluasi Awal</a></li>
                                        <li><a style="cursor: pointer" onclick="showModalMpp('impementasi_mpp')"><i class="fa fa-circle-o"></i>Form-B Catatan Implementasi</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-print"></i> Surat Pernyataan </h3>
                            </div>
                            <div class="box-body">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-print"></i> Action
                                    </button>
                                    <button type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a target="_blank"
                                               href="printSuratPernyataan_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>&tipe=SP01">
                                            <i class="fa fa-print"></i>Surat Pernyataan Gagal SEP</a></li>
                                        <li><a target="_blank"
                                               href="printSuratPernyataan_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>&tipe=SP02">
                                            <i class="fa fa-print"></i>Surat Pernyataan Selisih Bayar</a></li>
                                        <li><a target="_blank"
                                               href="printSuratPernyataan_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>&tipe=SP03">
                                            <i class="fa fa-print"></i>Surat Penolakan Tindakan</a></li>
                                        <li><a target="_blank"
                                               href="printSuratPernyataan_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>&tipe=SP04">
                                            <i class="fa fa-print"></i>Surat Pernyataan Kematian</a></li>
                                        <li><a target="_blank"
                                               href="printSuratPernyataan_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>&tipe=SP05">
                                            <i class="fa fa-print"></i>Surat Pengantar Jenazah</a></li>
                                        <li><a target="_blank"
                                               href="printSuratPernyataan_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>&tipe=SP06">
                                            <i class="fa fa-print"></i>Surat Pernyataan Non Bpjs</a></li>
                                        <li><a target="_blank"
                                               href="printSuratPernyataan_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>&tipe=SP07">
                                            <i class="fa fa-print"></i>Surat Pernyataan Kronologi</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-print"></i> Cetak Keterangan </h3>
                            </div>
                            <div class="box-body">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-success"><i class="fa fa-print"></i> Action
                                    </button>
                                    <button type="button" class="btn btn-success dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a target="_blank"
                                               href="printSuratPernyataan_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>&tipe=SK01">
                                            <i class="fa fa-print"></i>Surat Keterangan Dokter</a></li>
                                        <li><a target="_blank"
                                               href="printSuratPernyataan_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>&tipe=SK02">
                                            <i class="fa fa-print"></i>Surat keterangan Kamar Penuh</a></li>
                                        <li><a target="_blank"
                                               href="printSuratPernyataan_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>&tipe=SK03">
                                            <i class="fa fa-print"></i>Surat Keterangan Sehat</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-stethoscope"></i> Ringkasan  </h3>
                            </div>
                            <div class="box-body">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Action
                                    </button>
                                    <button type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a target="_blank"
                                               href="printGeneralConcent_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-edit"></i>Ringkasan Pulang</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-edit"></i>Ringkasan Masuk Keluar</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-edit"></i>Resume Medis</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-stethoscope"></i> Fisioterapi </h3>
                            </div>
                            <div class="box-body">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Action
                                    </button>
                                    <button onclick="loadModalRM('fisioterapi')" type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="#" onclick="pengkajianFisioterapi('<s:property value="headerDetailCheckup.idDetailCheckup"/>')"><i class="fa fa-edit"></i>Pengkajian Pasien Fisioterapi</a></li>
                                        <li><a href="#" onclick="addMonitoringFisioterapi('<s:property value="headerDetailCheckup.idDetailCheckup"/>')"><i class="fa fa-television"></i>Kunjungan Fisioterapi</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-stethoscope"></i> HIV </h3><small> (Human Immunodeficiency Virus)</small>
                            </div>
                            <div class="box-body">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-success"><i class="fa fa-edit"></i> Action
                                    </button>
                                    <button type="button" class="btn btn-success dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a target="_blank"
                                               href="printSuratPernyataan_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>&tipe=HV01">
                                            <i class="fa fa-edit"></i>Persetujuan Test HIV</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-stethoscope"></i> Edukasi Pasien </h3>
                            </div>
                            <div class="box-body">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Action
                                    </button>
                                    <button type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a target="_blank"
                                               href="printGeneralConcent_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-edit"></i>Edukasi Pasien dan Keluarga</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-stethoscope"></i> Gigi </h3>
                            </div>
                            <div class="box-body">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-success"><i class="fa fa-edit"></i> Action
                                    </button>
                                    <button type="button" class="btn btn-success dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a target="_blank"
                                               href="printGeneralConcent_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-edit"></i>Resume Gigi</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-stethoscope"></i> Appendecitomy </h3>
                            </div>
                            <div class="box-body">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Action
                                    </button>
                                    <button type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a target="_blank"
                                               href="printGeneralConcent_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-edit"></i>Informed Consent Appendecitomy</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-print"></i> Rawat Inap </h3>
                            </div>
                            <div class="box-body">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-print"></i> Action
                                    </button>
                                    <button type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a target="_blank"
                                               href="printSuratPernyataan_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>&tipe=RI01">
                                            <i class="fa fa-print"></i>Surat Permintaan Rawat Inap</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Pengkajian Ulang Keperawatan dan Tindakan</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Asesmen Awal Medis Rawat Inap</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Rencana Asuhan Keperawatan</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Discharge Planing</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Catatan Perkembangan Pasien Terintegrasi</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Transfer Pasien Antar Ruangan</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Privasi Pasien</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Lembar Observasi Pediatric Early Warning Score</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Catatan Pemberian Obat</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Injeksi CPO / Jadwal Pemberian Terapi</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Rekonsiliasi Obat</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-print"></i> HD </h3><small> (Hemodialisa)</small>
                            </div>
                            <div class="box-body">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-success"><i class="fa fa-print"></i> Action
                                    </button>
                                    <button type="button" class="btn btn-success dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a target="_blank"
                                               href="printGeneralConcent_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Form-A Evaluasi Awal</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Form-B Catatan Implementasi</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-desktop"></i> Operasi </h3>
                            </div>
                            <div class="box-body">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Action
                                    </button>
                                    <button onclick="loadModalRM('operasi')" type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a style="cursor: pointer" onclick="showModalOperasi('ceklist_operasi')"><i class="fa fa-circle-o"></i>Ceklist Serah Terima Pasien Pre Operasi</a></li>
                                        <li><a style="cursor: pointer" onclick="showModalOperasi('penandaan_area')"><i class="fa fa-circle-o"></i>Penandaan Area Operasi Pasien</a></li>
                                        <li><a style="cursor: pointer" onclick="showModalOperasi('pra_anestesi')"><i class="fa fa-circle-o"></i>Asesmen Pra Anestesi</a></li>
                                        <li><a style="cursor: pointer" onclick="showModalOperasi('general_anestesi')"><i class="fa fa-circle-o"></i>Edukasi dan Persetujuan General Anestesi</a></li>
                                        <li><a style="cursor: pointer" onclick="showModalOperasi('regional_anestesi')"><i class="fa fa-circle-o"></i>Edukasi dan Persetujuan Regional Anestesi</a></li>
                                        <li><a style="cursor: pointer" onclick="showModalOperasi('pindah_rr')"><i class="fa fa-circle-o"></i>Kriteia Pindah dari RR</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-print"></i> Rawat Jalan </h3>
                            </div>
                            <div class="box-body">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-success"><i class="fa fa-print"></i> Action
                                    </button>
                                    <button type="button" class="btn btn-success dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a target="_blank"
                                               href="printGeneralConcent_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Profil Rekam Medis Rawat Jalan</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-plus-square"></i> UGD </h3>
                            </div>
                            <div class="box-body">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Action
                                    </button>
                                    <button onclick="loadModalRM('ugd')" type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a style="cursor: pointer" onclick="showAsesmenUgd()"><i class="fa fa-user-plus"></i>Asesmen Awal Gawat Darurat</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div id="conten-modal"></div>

<script type='text/javascript' src='<s:url value="/dwr/interface/FisioterapiAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenUgdAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenOperasiAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/MppAction.js"/>'></script>

<script type='text/javascript' src='<s:url value="/pages/dist/js/paintTtd.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/operasi.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/fisioterapi.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/asesmenUgd.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/mpp.js"/>'></script>

<script type='text/javascript'>

    function loadModalRM(jenis){
        var context = "";
        if(jenis == "fisioterapi"){
            context = '<%= request.getContextPath() %>/pages/modal/modalFisioterapi.jsp';
        }
        if(jenis == "operasi"){
            context = '<%= request.getContextPath() %>/pages/modal/modalOperasi.jsp';
        }
        if(jenis == "ugd"){
            context = '<%= request.getContextPath() %>/pages/modal/modalAsesmenUGD.jsp';
        }
        $('#conten-modal').load(context, function (res) {
            console.log(res);
        });
    }

    $(document).ready(function () {

        var tipe = '<s:property value="headerDetailCheckup.tipePelayanan"/>';
        if ("RJ" == tipe) {
            $('#rawat_jalan').addClass('active');
        }
        if ("RI" == tipe) {
            $('#rawat_inap').addClass('active');
        }
        if ("IGD" == tipe) {
            $('#igd').addClass('active');
        }
    });

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>