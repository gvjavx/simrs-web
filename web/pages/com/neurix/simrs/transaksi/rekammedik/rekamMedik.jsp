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
                                               href="printGeneralConcent_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>General Consent</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Pelepasan Informasi</a></li>
                                        <li><a target="_blank"
                                               href="printLembarkonsultasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Lembar Konsultasi</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="box-header with-border"></div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-print"></i> MPP </h3><small> (Manajemen Pelayanan Pasien)</small>
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
                                               href="printGeneralConcent_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Surat Pernyataan Gagal SEP</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Surat Pernyataan Selisih Bayar</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Surat Penolakan Tindakan</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Surat Pernyataan Kematian</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Surat Pengantar Jenazah</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Surat Pernyataan Non Bpjs</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
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
                                               href="printGeneralConcent_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Surat Keterangan Dokter</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Surat keterangan Kamar Penuh</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
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
                                    <button type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a target="_blank"
                                               href="printGeneralConcent_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-edit"></i>Pengkajian Pasien Fisioterapi</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-edit"></i>Kunjungan Fisioterapi</a></li>
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
                                               href="printGeneralConcent_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
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
                                               href="printGeneralConcent_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
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
                                <h3 class="box-title"><i class="fa fa-print"></i> Operasi </h3>
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
                                               href="printGeneralConcent_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Ceklist Serah Terima Pasien Pre Operasi</a></li>
                                        <li><a href="#" onclick="penandaAreaOperasi()">
                                            <i class="fa fa-print"></i>Penandaan Area Operasi Pasien Laki - Laki</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Penandaan Area Operasi Pasien Perempuan</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Asesmen Pra Anestesi</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Edukasi dan Persetujuan General Anestesi</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Edukasi dan Persetujuan Regional Anestesi</a></li>
                                        <li><a target="_blank"
                                               href="printPelepasanInformasi_rekammedik.action?id=<s:property value="headerDetailCheckup.idDetailCheckup"/>">
                                            <i class="fa fa-print"></i>Kriteia Pindah dari RR</a></li>
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
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<%@ include file="/pages/com/neurix/simrs/transaksi/rekammedik/modalOperasi.jsp" %>

<script type='text/javascript' src='<s:url value="/pages/dist/js/operasi.js"/>'></script>
<script type='text/javascript'>

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
    });

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>