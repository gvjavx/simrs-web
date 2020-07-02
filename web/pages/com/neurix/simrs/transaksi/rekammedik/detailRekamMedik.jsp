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

        .radio-margin {
            margin-top: -7px;
            margin-left: 1px;
        }

        .paint-canvas-ttd {
            border: 1px black solid;
            margin: 1rem;
        }

        .garis {
            border: solid 0.5px #ddd;
        }

        .bungkus {
            width: 100%;
            height: 420px;
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
            margin-top: -50px;
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

    </style>
    
    <link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/radio_checkbox.css"/>">

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
            Detail Rekam Medik
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
                        <div style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px; text-align: center"
                             class="card card-4 pull-right">
                            <img border="2" id="img_ktp" src="<s:property value="imgKtp"/>"
                                 style="cursor: pointer; height: 90px; width: 190px; margin-top: 4px">
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6">
                                <table class="table table-striped">
                                    <s:hidden id="no_checkup" name="detailCheckup.noCheckup"></s:hidden>
                                    <s:hidden id="id_palayanan" name="detailCheckup.idPelayanan"></s:hidden>
                                    <s:hidden id="no_detail_checkup" name="detailCheckup.idDetailCheckup"/>
                                    <s:hidden id="id_pasien" name="detailCheckup.idPasien"/>
                                    <s:hidden id="jenis_pasien" name="detailCheckup.idJenisPeriksaPasien"/>
                                    <s:hidden id="jenis_bayar" name="detailCheckup.metodePembayaran"/>
                                    <tr>
                                        <td><b>No RM</b></td>
                                        <td>
                                            <table><s:label
                                                    name="detailCheckup.idPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="45%"><b>No Checkup</b></td>
                                        <td>
                                            <table>
                                                <s:label name="detailCheckup.noCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>No Checkup Detail</b></td>
                                        <td>
                                            <table><s:label
                                                    name="detailCheckup.idDetailCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>NIK</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.nik"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Nama</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.namaPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Kelamin</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.jenisKelamin"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Tempat, Tanggal Lahir</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.tempatTglLahir"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Pasien</b></td>
                                        <td>
                                            <table>
                                                <s:label id="jenis_periksa"
                                                         name="detailCheckup.jenisPeriksaPasien"></s:label>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <table class="table table-striped">
                                    <tr>
                                        <td><b>Poli</b></td>
                                        <td>
                                            <table>
                                                <s:label name="detailCheckup.namaPelayanan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Alamat</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.alamat"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Desa</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.desa"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kecamatan</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.kecamatan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kabupaten</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.kota"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Provinsi</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.provinsi"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Terakhir Periksa</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.tglKeluar"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Diagnosa Terakhir</b></td>
                                        <td>
                                            <table><s:label name="detailCheckup.diagnosa"></s:label></table>
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
                        <div class="box-header with-border"></div>
                        <div class="row" style="padding: 5px">
                            <div class="col-md-12 text-center">
                                <a class="btn btn-warning" href="initForm_rekammedis.action"><i class="fa fa-times"></i>
                                    Back</a>
                                <a class="btn btn-danger"
                                   href="detail_rekammedis.action?idPasien=<s:property value="detailCheckup.idPasien"/>"><i
                                        class="fa fa-refresh"></i> Reset</a>
                                <%--<a class="btn btn-info" id="btn-vidio-rm2" onclick="viewTelemedic()"><i class="fa fa-film"></i>--%>
                                    <%--Telemedic</a>--%>
                                <%--<a class="btn btn-info" onclick="viewRiwayat()"><i class="fa fa-history"></i> Riwayat Pasien</a>--%>
                                <a class="btn btn-info" onclick="viewHistory()"><i class="fa fa-history"></i> All History</a>
                            </div>
                        </div>
                        <div class="box-header with-border"></div>
                    </div>
                    <div class="box-body">
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-user"></i> Rekam Medik</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="dropup">
                    <button style="margin-right: 5px; margin-top: 5px" class="btn btn-default dropdown-toggle pull-right" type="button" data-toggle="dropdown">
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right">
                        <li><a style="cursor: pointer" onclick="showModalRj('ringkasan_rj')"><i class="fa fa-circle-o"></i>Profil Rekam Medis Rawat Jalan</a></li>
                    </ul>
                </div>
                <div class="info-box" style="cursor: pointer">
                    <span class="info-box-icon bg-aqua"><i class="fa fa fa-book" style="margin-top: 20px"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">SPESIALIS</span>
                        <span class="info-box-number"><small>(Asesmen Spesialis)</small></span>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="dropup">
                    <button onclick="loadModalRM('hemodialisa');" style="margin-right: 5px; margin-top: 5px" class="btn btn-default dropdown-toggle pull-right" type="button" data-toggle="dropdown">
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right">
                        <li><a style="cursor: pointer" onclick="showModalHD('monitoring_hd')"><i class="fa fa-circle-o"></i>Monitoring HD</a></li>
                        <li><a style="cursor: pointer" onclick="showModalHD('perencanaan_hemodialisa')"><i class="fa fa-circle-o"></i>Perencanaan HD</a></li>
                        <li><a style="cursor: pointer" onclick="showModalHD('asesmen_hd')"><i class="fa fa-circle-o"></i>Asesmen Awal HD</a></li>
                        <li><a style="cursor: pointer" onclick="showModalHD('tranfusi_hd')"><i class="fa fa-circle-o"></i>Tindakan Medis Transfusi Darah</a></li>
                        <li><a style="cursor: pointer" onclick="showModalHD('catatan_tranfusi_darah')"><i class="fa fa-circle-o"></i>Catatan Pemantauan Tranfusi Darah</a></li>
                        <li><a style="cursor: pointer" onclick="showModalHD('persetujuan_hd')"><i class="fa fa-circle-o"></i>Persetujuan HD</a></li>
                        <li><a style="cursor: pointer" onclick="showModalHD('travelling')"><i class="fa fa-circle-o"></i>Travelling Dialysis</a></li>
                    </ul>
                </div>
                <div class="info-box" style="cursor: pointer">
                    <span class="info-box-icon bg-aqua"><i class="fa fa fa-book" style="margin-top: 20px"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">HD</span>
                        <span class="info-box-number"><small>(Hemodialisa)</small></span>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="dropup">
                    <button onclick="loadModalRM('fisioterapi');" style="margin-right: 5px; margin-top: 5px" class="btn btn-default dropdown-toggle pull-right" type="button" data-toggle="dropdown">
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right">
                        <li><a style="cursor: pointer" onclick="pengkajianFisioterapi('<s:property value="headerDetailCheckup.idDetailCheckup"/>')"><i class="fa fa-circle-o"></i>Pengkajian Pasien Fisioterapi</a></li>
                        <li><a style="cursor: pointer" onclick="addMonitoringFisioterapi('<s:property value="headerDetailCheckup.idDetailCheckup"/>')"><i class="fa fa-circle-o"></i>Kunjungan Fisioterapi</a></li>
                    </ul>
                </div>
                <div class="info-box" style="cursor: pointer">
                    <span class="info-box-icon bg-aqua"><i class="fa fa fa-book" style="margin-top: 20px"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">FISIOTERAPI</span>
                        <span class="info-box-number"><small>&nbsp;</small></span>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="dropup">
                    <button style="margin-right: 5px; margin-top: 5px" class="btn btn-default dropdown-toggle pull-right" type="button" data-toggle="dropdown">
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right">
                        <li><a style="cursor: pointer" onclick="showAsesmenUgd()"><i class="fa fa-circle-o"></i> Asesmen Awal Gawat Darurat Anak</a></li>
                        <li><a style="cursor: pointer" onclick="showAsesmenUgd()"><i class="fa fa-circle-o"></i> Asesmen Awal Gawat Darurat Dewasa</a></li>
                        <li><a style="cursor: pointer" onclick="showAsesmenUgd()"><i class="fa fa-circle-o"></i> Asesmen Awal Gawat Darurat Geriatri</a></li>
                    </ul>
                </div>
                <div class="info-box" style="cursor: pointer">
                    <span class="info-box-icon bg-aqua"><i class="fa fa fa-book" style="margin-top: 20px"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">UGD</span>
                        <span class="info-box-number"><small>(Unit Gawat Darurat)</small></span>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="dropup">
                    <button style="margin-right: 5px; margin-top: 5px" class="btn btn-default dropdown-toggle pull-right" type="button" data-toggle="dropdown">
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right">
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('asesmen_rawat_inap')"
                               onclick="showModalAsesmenRawatInap('asesmen')"><i
                                class="fa fa-circle-o"></i>Asesmen Awal Medis Rawat Inap</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('catatan_integrasi')"
                               onclick="showModalAsesmenRawatInap('catatan_integrasi')"><i
                                class="fa fa-circle-o"></i>Catatan Perkembangan Pasien Terintegrasi</a>
                        </li>
                        <li><a style="cursor: pointer"
                               onmouseover="loadModalRM('pengkajian_keperawatan')"
                               onclick="showModalPengkajianKep('pengkajian')"><i
                                class="fa fa-circle-o"></i>Pengkajian Ulang Keperawatan dan Tindakan</a>
                        </li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('asuhan')"
                               onclick="showModalAsesmenRawatInap('asuhan')"><i
                                class="fa fa-circle-o"></i>Rencana Asuhan Keperawatan</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('mpp')" onclick="showModalMpp('evaluasi_awal')"><i class="fa fa-circle-o"></i>Form-A Evaluasi Awal</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('mpp')" onclick="showModalMpp('impementasi_mpp')"><i class="fa fa-circle-o"></i>Form-B Catatan Implementasi</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('edukasi_pasien')" onclick="showModalAsesmenRawatInap('edukasi_pasien')"><i class="fa fa-circle-o"></i>Edukasi Pasien dan Keluarga</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('edukasi_pasien_integrasi')" onclick="showModalAsesmenRawatInap('edukasi_pasien_terintegrasi')"><i class="fa fa-circle-o"></i>Edukasi Pasien dan Keluarga Terintegrasi</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('discharge_planing')"
                               onclick="showModalAsesmenRawatInap('discharge_planing')"><i
                                class="fa fa-circle-o"></i>Discharge Planing</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('transfer_pasien')"
                               onclick="showModalAsesmenRawatInap('transfer_pasien')"><i
                                class="fa fa-circle-o"></i>Transfer Pasien Antar Ruangan</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('privasi')"
                               onclick="showModalAsesmenRawatInap('privasi')"><i
                                class="fa fa-circle-o"></i>Privasi Pasien</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('early_warning')"
                               onclick="showModalAsesmenRawatInap('early_warning')"><i
                                class="fa fa-circle-o"></i>Lembar Observasi Pediatric Early Warning
                            Score</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('pemberian_obat')"
                               onclick="showModalAsesmenRawatInap('catatan_pemberian')"><i
                                class="fa fa-circle-o"></i>Catatan Pemberian Obat</a></li>
                        </li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('rekonsiliasi')"
                               onclick="showModalAsesmenRawatInap('rekonsiliasi')"><i
                                class="fa fa-circle-o"></i>Rekonsiliasi Obat</a></li>
                    </ul>
                </div>
                <div class="info-box" style="cursor: pointer">
                    <span class="info-box-icon bg-aqua"><i class="fa fa fa-book" style="margin-top: 20px"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">RAWAT INAP</span>
                        <span class="info-box-number"><small>&nbsp;</small></span>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="dropup">
                    <button style="margin-right: 5px; margin-top: 5px" class="btn btn-default dropdown-toggle pull-right" type="button" data-toggle="dropdown">
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right">
                        <li><a style="cursor: pointer" onclick="showModalOperasi('ceklist_operasi')"><i class="fa fa-circle-o"></i>Ceklist Serah Terima Pasien Pre Operasi</a></li>
                        <li><a style="cursor: pointer" onclick="showModalOperasi('penandaan_area')"><i class="fa fa-circle-o"></i>Penandaan Area Operasi Pasien</a></li>
                        <li><a style="cursor: pointer" onclick="showModalOperasi('pra_anestesi')"><i class="fa fa-circle-o"></i>Asesmen Pra Anestesi</a></li>
                        <li><a style="cursor: pointer" onclick="showModalOperasi('general_anestesi')"><i class="fa fa-circle-o"></i>Edukasi dan Persetujuan General Anestesi</a></li>
                        <li><a style="cursor: pointer" onclick="showModalOperasi('regional_anestesi')"><i class="fa fa-circle-o"></i>Edukasi dan Persetujuan Regional Anestesi</a></li>
                        <li><a style="cursor: pointer" onclick="showModalOperasi('pindah_rr')"><i class="fa fa-circle-o"></i>Kriteia Pindah dari RR</a></li>
                    </ul>
                </div>
                <div class="info-box" style="cursor: pointer">
                    <span class="info-box-icon bg-aqua"><i class="fa fa fa-book" style="margin-top: 20px"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">OK</span>
                        <span class="info-box-number"><small>(Operasi Kamar)</small></span>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="dropup">
                    <button style="margin-right: 5px; margin-top: 5px" class="btn btn-default dropdown-toggle pull-right" type="button" data-toggle="dropdown">
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right">
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('icu')"  onclick="showModalICU('asesmen_icu')"><i class="fa fa-circle-o"></i>Asesmen ICU</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('hemodinamika')"  onclick="showModalICU('hemodinamika')"><i class="fa fa-circle-o"></i>Hemodinamika</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('respirasi')"  onclick="showModalICU('respirasi')"><i class="fa fa-circle-o"></i>Respirasi</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('keseimbangan')"  onclick="showModalICU('keseimbangan')"><i class="fa fa-circle-o"></i>Keseimbangan</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('obat-obatan')"  onclick="showModalICU('obat-obatan')"><i class="fa fa-circle-o"></i>Obat Obatan/ Intakea/ Output</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('asuhan_keperawatan')"  onclick="showModalICU('asuhan_keperawatan')"><i class="fa fa-circle-o"></i>Asuhan Keperawatan</a></li>
                    </ul>
                </div>
                <div class="info-box" style="cursor: pointer">
                    <span class="info-box-icon bg-aqua"><i class="fa fa fa-book" style="margin-top: 20px"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">ICU</span>
                        <span class="info-box-number"><small>(Intensive Care Unit)</small></span>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="dropup">
                    <button style="margin-right: 5px; margin-top: 5px" class="btn btn-default dropdown-toggle pull-right" type="button" data-toggle="dropdown">
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right">
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('identifikasi_bayi')"  onclick="showModalRB('identifikasi_bayi')"><i class="fa fa-circle-o"></i>Identifikasi Bayi</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('asesmen_ponek')"  onclick="showModalRB('asesmen_ponek')"><i class="fa fa-circle-o"></i>Asesmen Ponek</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('asesmen_awal_bayi')"  onclick="showModalRB('asesmen_awal_bayi')"><i class="fa fa-circle-o"></i>Asesmen Awal Bayi</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('laporan_pembedahan')"  onclick="showModalRB('laporan_pembedahan')"><i class="fa fa-circle-o"></i>Laporan Pembedahan</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('laporan_persalinan')"  onclick="showModalRB('laporan_persalinan')"><i class="fa fa-circle-o"></i>Laporan Persalinan</a></li>
                        <li><a style="cursor: pointer" onmouseover="loadModalRM('tindakan_rb')"  onclick="showModalRB('tindakan_rb')"><i class="fa fa-circle-o"></i>Persetujuan Tindakan Kedokteran</a></li>
                    </ul>
                </div>
                <div class="info-box" style="cursor: pointer">
                    <span class="info-box-icon bg-aqua"><i class="fa fa fa-book" style="margin-top: 20px"></i></span>
                    <div class="info-box-content">
                        <span class="info-box-text">RB</span>
                        <span class="info-box-number"><small>(Ruang Bersalin)</small></span>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-riwayat">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Riwayat Pasien
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <table id="tableRM" class="table table-bordered table-striped">
                        <thead>
                        <tr bgcolor="#90ee90">
                            <td>ID Detail Checkup</td>
                            <td>Tanggal Masuk</td>
                            <td>Tanggal Keluar</td>
                            <td>Keterangan</td>
                            <td>Pelayanan</td>
                            <td align="center">Action</td>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="#session.listOfRekamMedis" var="row">
                            <tr>
                                <td><s:property value="idDetailCheckup"/></td>
                                <td><s:property value="stTanggalMasuk"/></td>
                                <td><s:property value="stTanggalKeluar"/></td>
                                <td><s:property value="keteranganSelesai"/></td>
                                <td><s:property value="namaPelayanan"/></td>
                                <td align="center">
                                    <a href="detail_rekammedis.action?idPasien=<s:property value="detailCheckup.idPasien"/>&id=<s:property value="idDetailCheckup"/>">
                                        <img class="hvr-grow"
                                             src="<s:url value="/pages/images/icons8-search-25.png"/>"
                                             style="cursor: pointer;">
                                    </a>
                                </td>
                            </tr>
                        </s:iterator>
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

<div class="modal fade" id="modal-history">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> All History
                </h4>
            </div>
            <div class="modal-body" style="height: 450px;overflow-y: scroll;">
                <div class="box-body">
                    <table class="table table-bordered" style="font-size: 12px;">
                        <thead>
                        <tr style="font-weight: bold">
                            <td>Pelayanan</td>
                            <td>No Transaksi</td>
                            <td>Waktu</td>
                            <td>Keterangan</td>
                            <td>Catatan</td>
                            <%--<td width="5%" align="center">Detail</td>--%>
                            <td width="8%">Telemedic</td>
                        </tr>
                        </thead>
                        <tbody id="body_history">
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

<div class="modal fade" id="modal-telemedic">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-user-md"></i> Telemedic Pasien Pada Tanggal <span id="tanggal_tele"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <video controls width="100%" height="420px" id="body-video-rm"></video>
                    <%--<div class="bungkus">--%>
                    <%--<div class="carousel">--%>
                    <%--<button onclick="carouselSwipe('carousel-arrow-prev')" type="button" id="carousel-arrow-prev" class="carousel-arrow carousel-arrow-prev" arial-label="Image précédente"></button>--%>
                    <%--<button onclick="carouselSwipe('carousel-arrow-next')" type="button" id="carousel-arrow-next" class="carousel-arrow carousel-arrow-next" arial-label="Image suivante"></button>--%>
                    <%--<div id="body-video-rm">--%>

                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div id="modal-temp"></div>
<%@ include file="/pages/modal/modalRingkasanRawatJalan.jsp" %>

<script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/FisioterapiAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/HemodialisaAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/RekamMedisRawatJalanAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/MonitoringTransfusiDarahAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenSpesialisAction.js"/>'></script>

<script type='text/javascript' src='<s:url value="/pages/dist/js/datapasien.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/fisioterapi.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/hd.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/rj.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/addrawatjalan.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/paintTtd.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/spesialis.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/nyeri.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/history.js"/>'></script>

<script type='text/javascript'>

    var isReadRM = true;
    var idDetailCheckup = '<s:property value="detailCheckup.idDetailCheckup"/>';
    var contextPath = '<%= request.getContextPath() %>';
    var idPasien = $('#id_pasien').val();

    var tglLhr = '<s:property value="headerDetailCheckup.tglLahir"/>';
    var tglLahir = tglLhr.split("-").reverse().join("-");
    var namaPasien = '<s:property value="headerDetailCheckup.namaPasien"/>';
    var anamnese = '<s:property value="headerDetailCheckup.anamnese"/>';
    var penunjangMedis = '<s:property value="headerDetailCheckup.penunjangMedis"/>';
    var keluhanUtama = '<s:property value="headerDetailCheckup.keluhanUtama"/>';
    var suhu = '<s:property value="headerDetailCheckup.suhu"/>';
    var nadi = '<s:property value="headerDetailCheckup.nadi"/>';
    var tensi = '<s:property value="headerDetailCheckup.tensi"/>';
    var pernafasan = '<s:property value="headerDetailCheckup.pernafasan"/>';
    var alergi = '<s:property value="headerDetailCheckup.alergi"/>';
    var beratBadan = '<s:property value="headerDetailCheckup.berat"/>';
    var tinggiBadan = '<s:property value="headerDetailCheckup.tinggi"/>';
    var diagnosa = '<s:property value="headerDetailCheckup.namaDiagnosa"/>';
    var umur = '<s:property value="headerDetailCheckup.umur"/>';
    var alamatLengkap = '<s:property value="headerDetailCheckup.alamatLengkap"/>';
    var noBpjs = '<s:property value="headerDetailCheckup.noBpjs"/>';
    var jenisKelamin = '<s:property value="headerDetailCheckup.jenisKelamin"/>';

    function loadModalRM(jenis) {
        var context = "";
        if (jenis == "fisioterapi") {
            context = contextPath + '/pages/modal/modalFisioterapi.jsp';
        } else if (jenis == "hemodialisa") {
            context = contextPath + '/pages/modal/modalHD.jsp';
        } else {
            var kat = '<s:property value="headerDetailCheckup.kategoriPelayanan"/>';
            context = contextPath + '/pages/modal/modal-' + kat + '.jsp';
        }

        $('#modal-temp').load(context, function (res) {
        });
    }

    $(document).ready(function () {

        $('#tableRM').DataTable({
            "order": [[0, "desc"]],
            "pageLength": 5
        });

        $('#rekam_medik').addClass('active');

        var option = '<option value="5">5</option>' +
            '<option value="10">10</option>' +
            '<option value="25">25</option>' +
            '<option value="50">50</option>' +
            '<option value="75">75</option>' +
            '<option value="100">100</option>';

        $('[name=tableRM_length]').html(option);
        // if (idDetailCheckup != null && idDetailCheckup != '') {
        //     $('[type=search]').val(idDetailCheckup).trigger('input');
        //     $('#btn-vidio-rm').show();
        // }
        $('.dropup').on('show.bs.dropdown', function(e){
            $(this).find('.dropdown-menu').first().stop(true, true).slideDown(350);
        });

        $('.dropup').on('hide.bs.dropdown', function(e){
            $(this).find('.dropdown-menu').first().stop(true, true).slideUp(350);
        });
    });

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>