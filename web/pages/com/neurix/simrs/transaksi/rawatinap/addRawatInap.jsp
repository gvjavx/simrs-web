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
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatInapAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/OrderGiziAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanResepAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RawatInapAction.js"/>'></script>
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


    </script>
    <style>
    .btn{
      margin-top: 7px;
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
            Rawat Inap Pasien
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
                                    <s:hidden id="no_checkup" name="rawatInap.noCheckup"/>
                                    <s:hidden id="id_palayanan" name="rawatInap.idPelayanan"/>
                                    <s:hidden id="no_detail_checkup" name="rawatInap.idDetailCheckup"/>
                                    <s:hidden id="id_rawat_inap" name="rawatInap.idRawatInap"/>
                                    <s:hidden id="id_pasien" name="rawatInap.idPasien"/>
                                    <s:hidden id="id_jenis_pasien" name="rawatInap.idJenisPeriksa"/>
                                    <s:if test='rawatInap.idJenisPeriksa == "bpjs"'>
                                        <tr>
                                            <td width="45%"><b>No SEP</b></td>
                                            <td style="vertical-align: middle;">
                                                <table>
                                                    <s:label cssClass="label label-success" name="rawatInap.noSep"></s:label></table>
                                            </td>
                                        </tr> 
                                    </s:if>
                                    <tr>
                                        <td width="45%"><b>No RM</b></td>
                                        <td>
                                            <table>
                                                <s:label name="rawatInap.idPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="45%"><b>No Checkup</b></td>
                                        <td>
                                            <table>
                                                <s:label name="rawatInap.noCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>No Checkup Detail</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.idDetailCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>NIK</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.nik"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Nama</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.namaPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Kelamin</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.jenisKelamin"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Tempat, Tanggal Lahir</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.tempatTglLahir"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Metode Pembayaran</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.metodePembayaran"></s:label></table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <div style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px; text-align: center" class="card card-4 pull-right">
                                    <img border="2" id="img_ktp" src="<s:property value="rawatInap.urlKtp"/>" style="cursor: pointer; height: 90px; width: 190px; margin-top: 4px">
                                </div>
                                <table class="table table-striped">
                                    <tr>
                                        <td><b>Jenis Pasien</b></td>
                                        <td>
                                            <table>
                                                <s:label name="rawatInap.jenisPeriksaPasien"></s:label>
                                            </table>
                                        </td>
                                    </tr>
                                    <%--<tr>--%>
                                        <%--<td><b>Poli</b></td>--%>
                                        <%--<td>--%>
                                            <%--<table>--%>
                                                <%--<s:label name="rawatInap.namaPelayanan"></s:label></table>--%>
                                        <%--</td>--%>
                                    <%--</tr>--%>
                                    <tr>
                                        <td><b>Alamat</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.alamat"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Provinsi</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.provinsi"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kabupaten</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.kota"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kecamatan</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.kecamatan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Desa</b></td>
                                        <td>
                                            <table><s:label name="rawatInap.desa"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Ruangan</b></td>
                                        <td>
                                            <table><label class="label label-success"> <span id="no_ruang"></span> -
                                                <span id="name_ruang"></span></label></table>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td></td>
                                        <td>
                                            <button class="btn btn-primary" onclick="viewDetailRekamMedic('<s:property value="rawatInap.noCheckup"></s:property>')"><i class="fa fa-search"></i> View Rekam Medic Saat Ini</button>
                                            <%--<button class="btn btn-success" onclick="formateDateTime('2020-02-24 17:27:15.792')">TESSSS</button>--%>
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
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Asesmen</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap10')">
                            <i class="fa fa-edit"></i> Form Riwayat Kesehatan
                        </button>
                        <button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap11')">
                            <i class="fa fa-edit"></i> Form Keadaan Umum
                        </button>
                        <button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap12')">
                            <i class="fa fa-edit"></i> Form Pemeriksaan Fisik
                        </button>
                        <button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap13')">
                            <i class="fa fa-edit"></i> Form Asesmen Nyeri
                        </button>
                        <button class="btn btn-primary" onclick="showModalResiko('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap1')">
                            <i class="fa fa-edit"></i> Form Resiko Dekubitus
                        </button>
                        <button class="btn btn-primary" onclick="showModalResiko('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap2')">
                            <i class="fa fa-edit"></i> Form Fungsional
                        </button>
                        <button class="btn btn-primary" onclick="showModalResiko('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap3')">
                            <i class="fa fa-edit"></i> Form Skrining Gizi Pasien Dewasa
                        </button>
                        <button class="btn btn-primary" onclick="showModalResiko('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap4')">
                            <i class="fa fa-edit"></i> Form Skrining Gizi Pasien Anak
                        </button>
                        <button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap5')">
                            <i class="fa fa-edit"></i> Form Seksual dan Reproduksi
                        </button>
                        <button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap9')">
                            <i class="fa fa-edit"></i> Form Kebutuhan Komunikasi. Kognisi dan Edukasi
                        </button>
                        <button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap6')">
                            <i class="fa fa-edit"></i> Form Psikososial dan Ekonomi
                        </button>
                        <button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap7')">
                            <i class="fa fa-edit"></i> Form Spritual
                        </button>
                        <button class="btn btn-primary" onclick="showModalAsesmen('<s:property value="rawatInap.noCheckup"/>','<s:property value="rawatInap.idDetailCheckup"/>','inap8')">
                            <i class="fa fa-edit"></i> Form Diagnose Keperawatan yang muncul
                        </button>
                    </div>

                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-laptop"></i> Monitoring</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-info" onclick="showModalCairan('<s:property value="rawatInap.idDetailCheckup"/>')">
                            <i class="fa fa-edit"></i> Form Observasi Cairan
                        </button>
                        <button class="btn btn-info" onclick="showModalMonVitalSign('<s:property value="rawatInap.idDetailCheckup"/>')">
                            <i class="fa fa-edit"></i> Form Observasi Vital Sign
                        </button>
                        <button class="btn btn-info" onclick="showModalPemberianObat('<s:property value="rawatInap.idDetailCheckup"/>','parenteral')">
                            <i class="fa fa-edit"></i> Form Pemberian Obat Parenteral
                        </button>
                        <button class="btn btn-info" onclick="showModalPemberianObat('<s:property value="rawatInap.idDetailCheckup"/>','nonparenteral')">
                            <i class="fa fa-edit"></i> Form Pemberian Obat Non Parenteral
                        </button>
                    </div>

                    <div class="box-header with-border" id="pos_dok">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-user-md"></i> Dokter</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(1)"><i class="fa fa-plus"></i> Tambah Dokter
                        </button>
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
                                        Cover Biaya Bpjs
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

                    <div class="box-header with-border" id="pos_tin">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-medkit"></i> Tindakan</h3>
                    </div>
                    <div class="box-body">
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
                                <input type="hidden" id="tin_id_dokter">
                            </tr>
                            </thead>
                            <tbody id="body_tindakan">
                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border" id="pos_lab">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Penunjang Medis</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(4)"><i class="fa fa-plus"></i> Penunjang Medis
                        </button>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tanggal Order</td>
                                <td>Pemeriksaan</td>
                                <td>Status</td>
                                <td>Jenis Lab</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_lab">

                            </tbody>
                        </table>
                    </div>

                    <div class="box-header with-border" id="pos_diet">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-stethoscope"></i> Order Diet</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(5)"><i class="fa fa-plus"></i> Order Diet
                        </button>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90" style="height: 20px">
                                <td >Tanggal</td>
                                <td >ID Diet Gizi</td>
                                <td >Bentuk Diet</td>
                                <td >Keterangan</td>
                                <td align="center"width="10%">Status</td>
                                <td align="center" rowspan="2" width="18%">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_diet">

                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border" id="pos_obat">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-plus-square"></i> Obat Penunjang</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(6)"><i class="fa fa-plus"></i> Obat Penunjang
                        </button>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tanggal</td>
                                <td>ID Obat</td>
                                <td>Obat</td>
                                <td align="center">Qty</td>
                                <td>Jenis Satuan</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_obat">

                            </tbody>
                        </table>
                    </div>

                    <div class="box-header with-border" id="pos_ruangan">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Ruangan</h3>
                    </div>
                    <div class="box-body">
                        <%--<button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px" onclick="showModal(6)"><i class="fa fa-plus"></i> Order Obat</button>--%>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Nama Ruangan</td>
                                <td>No Ruangan</td>
                                <td>Kelas</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_ruangan">

                            </tbody>
                        </table>
                    </div>

                    <div class="box-header with-border" id="pos_rssep">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-medkit"></i> Order Resep Obat</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px" onclick="showModal(7)"><i class="fa fa-plus"></i> Tambah Resep</button>
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
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-navicon"></i> Keterangan (Jika sudah pulang / selesai rawat inap)</h3>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_ket">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            Silahkan cek kembali data inputan!
                        </div>
                        <div class="row">
                            <div class="col-md-offset-1 col-md-5">
                                <div class="form-group">
                                    <label class="col-md-5" style="margin-top: 7px; margin-bottom: -2px;">Catatan Selesai</label>
                                    <div class="col-md-7">
                                    <s:action id="initComboKet" namespace="/checkupdetail"
                                              name="getListComboKeteranganKeluar_checkupdetail"/>
                                    <s:select list="#initComboKet.listOfKeterangan" id="ket_selesai"
                                              name="headerCheckup.idPelayanan" listKey="keterangan"
                                              listValue="keterangan" cssStyle="width: 100%"
                                              onchange="var warn =$('#war_catatan').is(':visible'); if (warn){$('#cor_catatan').show().fadeOut(3000);$('#war_catatan').hide()}; showFormCekup(this)"
                                              headerKey="" headerValue="[Select one]"
                                              cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-5" style="margin-top: 7px; margin-bottom: -2px;">Cara Pasien </label>
                                    <div class="col-md-7">
                                        <s:select list="#{'Jalan':'Jalan','Pakai kursi Roda':'Pakai kursi Roda','Pakai stretcher':'Pakai stretcher'}" cssStyle="margin-top: 7px"
                                                  id="ket_cara"
                                                  headerKey="" headerValue="[Select One]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-5" style="margin-top: 7px; margin-bottom: -2px;">Didampingi Oleh</label>
                                    <div class="col-md-7">
                                        <s:select list="#{'Petugas':'Petugas','Keluarga/teman':'Keluarga/teman'}" cssStyle="margin-top: 7px"
                                                  id="ket_pendamping"
                                                  headerKey="" headerValue="[Select One]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                      <label class="col-md-5" style="margin-top: 7px; margin-bottom: -2px;">Tempat Tujuan</label>
                                      <div class="col-md-7">
                                          <s:select list="#{'Rumah':'Rumah','Dirujuk Ke':'Dirujuk Ke','Rehabilitas':'Rehabilitas'}" cssStyle="margin-top: 7px"
                                                    id="ket_tujuan"
                                                    headerKey="" headerValue="[Select One]"
                                                    cssClass="form-control select2"/>
                                      </div>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-group" id="form-cekup" style="display: none">
                                    <div class="form-group">
                                        <label class="col-md-5" style="margin-top: 7px">Tanggal Chekup Ulang</label>
                                        <div class="col-md-7">
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl_cekup" cssClass="form-control datepicker"/>
                                        </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-5" style="margin-top: 7px">Catatan</label>
                                        <div class="col-md-7">
                                        <s:textarea cssStyle="margin-top: 7px" cssClass="form-control" rows="5" id="cekup_ket"></s:textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-4 text-center">
                                        <a href="initForm_rawatinap.action" class="btn btn-warning"
                                           style="margin-top: 15px;"><i
                                                class="fa fa-arrow-left"></i> Back
                                        </a>
                                        <button class="btn btn-success" onclick="confirmSaveKeterangan()"
                                                style="margin-top: 15px;" id="save_ket"><i
                                                class="fa fa-check"></i> Close
                                        </button>
                                        <button style="display: none; cursor: no-drop; margin-top: 15px;" type="button"
                                                class="btn btn-success" id="load_ket"><i class="fa fa-spinner fa-spin"></i>
                                            Sedang Menyimpan...
                                        </button>
                                    </div>
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
                            <s:action id="initComboKategoriTindakan" namespace="/checkupdetail"
                                      name="getListComboKategoriTindakan_checkupdetail"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      onchange="listSelectTindakan(this); var warn =$('#war_kategori').is(':visible'); if (warn){$('#cor_kategori').show().fadeOut(3000);$('#war_kategori').hide()}"
                                      list="#initComboKategoriTindakan.listOfKategoriTindakan"
                                      id="tin_id_ketgori_tindakan"
                                      listKey="idKategoriTindakan"
                                      listValue="kategoriTindakan"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
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
                    <s:if test='rawatInap.idJenisPeriksa == "bpjs"'>
                        <div class="form-group">
                            <label class="col-md-3">Diagnosa</label>
                            <div class="col-md-7">
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
                            </div>
                            <div class="col-md-offset-3 col-md-7">
                                <s:textarea rows="4" id="diagnosa_ket"
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
                                      onchange="var warn =$('#war_kategori_lab').is(':visible'); if (warn){$('#cor_kategori_lab').show().fadeOut(3000);$('#war_kategori_lab').hide()}; listSelectLab(this)"
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

<div class="modal fade" id="modal-diet">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Order Diet</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_diet">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Silahkan cek kembali data inputan!
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 10px">Diet Pagi</label>
                        <div class="col-md-7">
                                <s:action id="comboDiet1" namespace="/rawatinap"
                                          name="getComboBoxDietGizi_rawatinap"/>
                            <s:select list="#comboDiet1.listOfDietGizi" listKey="idDietGizi" listValue="namaDietGizi" id="diet_pagi"
                                      onchange="var warn =$('#war_pagi1').is(':visible'); if (warn){$('#cor_pagi1').show().fadeOut(3000);$('#war_pagi1').hide()}"
                            headerKey="" headerValue="[Select One]" cssClass="form-control select2" cssStyle="width: 100%"/>

                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_pagi1"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_pagi1">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 10px">Diet Siang</label>
                        <div class="col-md-7">
                                <s:select list="#comboDiet1.listOfDietGizi" listKey="idDietGizi" listValue="namaDietGizi" id="diet_siang"
                                          onchange="var warn =$('#war_siang1').is(':visible'); if (warn){$('#cor_siang1').show().fadeOut(3000);$('#war_siang1').hide()}"
                                          headerKey="" headerValue="[Select One]" cssClass="form-control select2" cssStyle="width: 100%"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_siang1">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_siang1"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 10px">Diet Malam</label>
                        <div class="col-md-7">
                                <s:select list="#comboDiet1.listOfDietGizi" listKey="idDietGizi" listValue="namaDietGizi" id="diet_malam"
                                          onchange="var warn =$('#war_malam1').is(':visible'); if (warn){$('#cor_malam1').show().fadeOut(3000);$('#war_malam1').hide()}"
                                          headerKey="" headerValue="[Select One]" cssClass="form-control select2" cssStyle="width: 100%"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_malam1">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_malam1"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_diet"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_diet"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-diet-edit">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Order Diet Edit</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_diet_edit">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Silahkan cek kembali data inputan!
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 10px">Bentuk Diet</label>
                        <div class="col-md-7">
                            <s:action id="comboDiet1" namespace="/rawatinap"
                                      name="getComboBoxDietGizi_rawatinap"/>
                            <s:select list="#comboDiet1.listOfDietGizi" listKey="idDietGizi" listValue="namaDietGizi" id="bentuk_diet"
                                      onchange="var warn =$('#war_bentuk_diet').is(':visible'); if (warn){$('#cor_bentuk_diet').show().fadeOut(3000);$('#war_bentuk_diet').hide()}"
                                      headerKey="" headerValue="[Select One]" cssClass="form-control select2" cssStyle="width: 100%"/>

                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_bentuk_diet"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_bentuk_diet">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_diet_edit"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_diet_edit"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
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

<div class="modal fade" id="modal-ruangan">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Edit Ruangan</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_ruangan">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Silahkan cek kembali data inputan!
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Kelas Ruangan</label>
                        <div class="col-md-7">
                            <s:action id="initKelas" namespace="/checkupdetail"
                                      name="getListComboKelasRuangan_checkupdetail"/>
                            <s:select
                                    onchange="var warn =$('#war_ruangan_kelas').is(':visible'); if (warn){$('#cor_ruangan_kelas').show().fadeOut(3000);$('#war_ruangan_kelas').hide()}; listSelectRuangan(this)"
                                    list="#initKelas.listOfKelasRuangan" id="ruangan_kelas"
                                    listKey="idKelasRuangan" cssStyle="width: 100%"
                                    listValue="namaKelasRuangan"
                                    headerKey="" headerValue="[Select one]"
                                    cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_ruangan_kelas"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_ruangan_kelas"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Ruangan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="ruangan_ruang"
                                    onchange="var warn =$('#war_ruangan_ruang').is(':visible'); if (warn){$('#cor_ruangan_ruang').show().fadeOut(3000);$('#war_ruangan_ruang').hide()}">
                                <option value="">[select one]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_ruangan_ruang"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_ruangan_ruang"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_ruang" onclick="saveEditRuang()"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_ruang"><i
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
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Stok Obat</label>
                        <div class="col-md-2">
                            <label style="margin-top: 7px">Box</label>
                            <input class="form-control" type="number" min="1" id="resep_stok_box"
                                   readonly>
                        </div>
                        <div class="col-md-2">
                            <label style="margin-top: 7px">Lembar</label>
                            <input class="form-control" type="number" min="1" id="resep_stok_lembar"
                                   readonly>
                        </div>
                        <div class="col-md-3">
                            <label style="margin-top: 7px">Biji</label>
                            <input class="form-control" type="number" min="1" id="resep_stok_biji"
                                   readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Satuan</label>
                        <div class="col-md-7">
                            <s:select list="#{'box':'Box','lembar':'Lembar','biji':'Biji'}"
                                      cssStyle="margin-top: 7px; width: 100%"
                                      onchange="var warn = $('#war_rep_jenis_satuan').is(':visible'); if (warn){$('#cor_rep_jenis_satuan').show().fadeOut(3000);$('#war_rep_jenis_satuan').hide()}"
                                      id="resep_jenis_satuan"
                                      headerKey="" headerValue="[Select one]"
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
                        <label class="col-md-3" style="margin-top: 7px">Jumlah</label>
                        <div class="col-md-7">
                            <input oninput="var warn =$('#war_rep_qty').is(':visible'); if (warn){$('#cor_rep_qty').show().fadeOut(3000);$('#war_rep_qty').hide()}"
                                   style="margin-top: 7px" value="1" class="form-control" type="number" min="1"
                                   id="resep_qty">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_qty"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_qty"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Keterangan</label>
                        <div class="col-md-7">
                            <textarea id="resep_keterangan" rows="4" class="form-control" style="margin-top: 7px"
                                      oninput="var warn =$('#war_rep_ket').is(':visible'); if (warn){$('#cor_rep_ket').show().fadeOut(3000);$('#war_rep_ket').hide()}"></textarea>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_rep_ket"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_rep_ket"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
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
                    <table class="table table-striped table-bordered" id="tabel_rese_detail">
                        <thead>
                        <td>ID</td>
                        <td>Obat</td>
                        <td align="center">Qty</td>
                        <td align="center">Jenis Satuan</td>
                        <td>Keterangan</td>
                        <td align="center" width="5%">Action</td>
                        </thead>
                        <tbody id="body_detail">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_resep_head" onclick="saveResepObat()"><i
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
            </div>
        </div>
    </div>
</div>



<div class="modal fade" id="modal-resiko">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i>  <span id="label-skor"> </span></h4>
            </div>
            <div class="modal-body">
                  <button type="button" style="margin-bottom: 10px" class="btn btn-success" id="add_resiko" onclick="addResiko('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')">
                    <i class="fa fa-plus"></i> Add
                  </button>
                  <table class="table table-bordered ">
                    <thead>
                      <td>Asesmen</td>
                      <td>Skor</td>
                      <td>Created Who</td>
                      <td>Created Date</td>
                      <td>Action</td>
                    </thead>
                    <tbody id="body-list-resiko">

                    </tbody>
                  </table>
                  <input type="hidden" id="kat_skor"/>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
              <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
              </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-add-resiko">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="label-add-resiko"> </span></h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <br>
                      <div id="body_resiko">
                      </div>
                    <input type="hidden" id="ind_resiko" class="form form-control"/>
                    <br>
                    <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_resiko">
                        <h4><i class="icon fa fa-info"></i> Success!</h4>
                        <p>Data Berhasil Tersimpan</p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_resiko">
                        <h4><i class="icon fa fa-ban"></i> Error !</h4>
                        <p id="error_ket_resiko"></p>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_resiko" onclick="saveResiko('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_resiko"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-asesmen">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="label-asesmen"></span> </h4>
            </div>
            <div class="modal-body">
                  <button type="button" style="margin-bottom: 10px" class="btn btn-success" id="add_asesmen" onclick="addAsesmen('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')">
                    <i class="fa fa-plus"></i> Add
                  </button>
                  <table class="table table-bordered">
                    <thead>
                      <td>Asesmen</td>
                      <td>Created Who</td>
                      <td>Created Date</td>
                      <td align="center" width="10%">Action</td>
                    </thead>
                    <tbody id="body-list-asesmen">

                    </tbody>
                  </table>
                  <input type="hidden" id="kat_asesmen"/>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
              <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
              </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-add-asesmen">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="label-add-asesmen"> </span></h4>
            </div>
            <div class="modal-body">
                      <div id="body_asesmen">
                      </div>
                      <div style="color:#fc0303">note : kosongkan jika tidak perlu</div>
                    <input type="hidden" id="ind_asesmen" class="form form-control"/>
                    <br>
                    <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_asesmen">
                        <h4><i class="icon fa fa-info"></i> Success!</h4>
                        <p>Data Berhasil Tersimpan</p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_asesmen">
                        <h4><i class="icon fa fa-ban"></i> Error !</h4>
                        <p id="error_ket_asesmen"></p>
                    </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="saveAsesmen('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_asesmen"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view-asesmen">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="label-view-asesmen"> </span></h4>
            </div>
            <div class="modal-body">
                      <div id="head-view-asesmen"></div>
                    <br>
                      <table class="table table-bordered">
                        <tbody id="body-view-asesmen">
                        </tbody>
                      </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
              <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
              </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view-skor">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="label-view-skor"> </span></h4>
            </div>
            <div class="modal-body">
                <div class="box">
                      <div id="head-view-skor"></div>
                    <br>
                      <table class="table table-bordered">
                        <tbody id="body-view-skor">
                        </tbody>
                      </table>
                    <br>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
              <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
              </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-vital-sign">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Observasi Vital Sign </h4>
            </div>
            <div class="modal-body">
                  <div style="margin-bottom:7px">
                    <button type="button" class="btn btn-success" onclick="addMonVitalSign()">
                      <i class="fa fa-plus"></i> Add
                    </button>
                    <button type="button" class="btn btn-info" onclick="showGrafVitalSign('<s:property value="rawatInap.idDetailCheckup"/>')">
                      <i class="fa fa-pie-chart"></i> View Graf
                    </button>
                  </div>
                  <table class="table table-bordered">
                    <thead>
                      <td>Jam</td>
                      <td>Nafas</td>
                      <td>Nadi</td>
                      <td>Suhu</td>
                      <td>Tensi</td>
                      <td>Berat Badan</td>
                      <td>Tinggi Badan</td>
                      <td>Created Who</td>
                      <td>Created Date</td>
                    </thead>
                    <tbody id="body-list-vital-sign">

                    </tbody>
                  </table>
                </div>
            <div class="modal-footer" style="background-color: #cacaca">
              <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
              </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-add-vital-sign">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Observasi Vital Sign </span></h4>
            </div>
            <div class="modal-body">
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-3">
                            <label>Created Date</label>
                          </div>
                          <div class="col-md-4">
                            <input type="date" name="" value="" class="date form-control" id="mvs_date"/>
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-3">
                            <label style="margin-top: 7px">Jam</label>
                          </div>
                          <div class="col-md-2">
                            <select style="margin-top: 7px" class="form-control" id="mvs_jam">
                              <option val='8'>8</option>
                              <option val='12'>12</option>
                              <option val='16'>16</option>
                              <option val='20'>20</option>
                            </select>
                          </div>
                        </div>
                      </div>
                      <hr style="color:#b0b0b0"/>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-3">
                            <label>Nafas</label>
                            <select class="form-control" id="mvs_nafas">
                              <option val='10'>10</option>
                              <option val='20'>20</option>
                              <option val='30'>30</option>
                              <option val='40'>40</option>
                              <option val='50'>50</option>
                              <option val='60'>60</option>
                            </select>
                          </div>
                          <div class="col-md-3">
                            <label>Nadi</label>
                            <select class="form-control" id="mvs_nadi">
                              <option val='40'>40</option>
                              <option val='60'>60</option>
                              <option val='80'>80</option>
                              <option val='100'>100</option>
                              <option val='120'>120</option>
                              <option val='140'>140</option>
                              <option val='160'>160</option>
                            </select>
                          </div>
                          <div class="col-md-3">
                            <label>Suhu</label>
                            <select class="form-control" id="mvs_suhu">
                              <option val='36'>36</option>
                              <option val='37'>37</option>
                              <option val='38'>38</option>
                              <option val='39'>39</option>
                              <option val='40'>40</option>
                              <option val='41'>41</option>
                            </select>
                          </div>
                          <div class="col-md-3">
                            <label>Tensi</label>
                            <input type="number" name="" value="" class="form-control" id="mvs_tensi">
                          </div>
                        </div>
                      </div>
                      <hr style="color:#b0b0b0"/>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-3">
                            <label>Tinggi badan</label>
                            <input type="number" name="" value="" class="form-control" placeholder="cm" id="mvs_tb">
                          </div>
                          <div class="col-md-3">
                            <label>Berat badan</label>
                            <input type="number" name="" value="" class="form-control" placeholder="Kg" id="mvs_bb">
                          </div>
                        </div>
                      </div>
                    <input type="hidden" id="ind_asesmen" class="form form-control"/>
                    <br>
                    <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_asesmen">
                        <h4><i class="icon fa fa-info"></i> Success!</h4>
                        <p>Data Berhasil Tersimpan</p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_asesmen">
                        <h4><i class="icon fa fa-ban"></i> Error !</h4>
                        <p id="error_ket_asesmen"></p>
                    </div>
                </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="saveVitalSign('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_asesmen"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-graf-vital-sign">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-pie-chart"></i> Graf Observasi Vital Sign </span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body" style="padding:50px;">
                  <div id="line-chart" style="height: 300px"></div>

                  <%-- <div class="box box-primary">
                    <div class="box-header with-border">
                      <i class="fa fa-bar-chart-o"></i>

                      <h3 class="box-title">Line Chart</h3>

                      <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                      </div>
                    </div>
                    <div class="box-body">
                      <div id="line-chart" style="height: 300px; width:600px;"></div>
                    </div>
                    <!-- /.box-body-->
                  </div> --%>
                  <br/>
                  <p style="margin-top:100px;"><i class="fa fa-circle" style="color:#3a4dc9"></i> Suhu  <i class="fa fa-circle" style="color:#eb4034"></i> Nadi  <i class="fa fa-circle" style="color:#6b6b6b"></i> Nafas</p>

                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-cairan">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Observasi Cairan </h4>
            </div>
            <div class="modal-body">
                  <div style="margin-bottom:7px">
                    <button type="button" class="btn btn-success" onclick="addObCairan()">
                      <i class="fa fa-plus"></i> Add
                    </button>
                    <%-- <button type="button" class="btn btn-info" onclick="showGrafVitalSign('<s:property value="rawatInap.idDetailCheckup"/>')">
                      <i class="fa fa-pie-chart"></i> View Graf
                    </button> --%>
                  </div>
                  <table class="table table-bordered" style="font-size:11px;">
                    <thead>
                      <td width="10%">Tgl</td>
                      <td>Macam Cairan</td>
                      <td>Melalui</td>
                      <td>Jumlah</td>
                      <td>Jam Mulai</td>
                      <td>Jam Selesai</td>
                      <td>Cek Tambahan Obat</td>
                      <td>Sisa</td>
                      <td>Jam Ukur Buang</td>
                      <td>Dari</td>
                      <td>Balance Cairan</td>
                      <td>Keterangan</td>
                      <td>Created Who</td>
                    </thead>
                    <tbody id="body-list-cairan">

                    </tbody>
                  </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
              <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
              </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-add-cairan">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Observasi Cairan </span></h4>
            </div>
            <div class="modal-body">
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label>Created Date</label>
                          </div>
                          <div class="col-md-4">
                            <input type="date" name="" value="" class="date form-control" id="mcr_date">
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Macam Cairan</label>
                          </div>
                          <div class="col-md-8">
                            <input type="text" style="margin-top: 7px" name="" value="" class="form-control" id="mcr_macam">
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Melalui</label>
                          </div>
                          <div class="col-md-8">
                            <input type="text" style="margin-top: 7px" name="" value="" class="form-control" id="mcr_melalui">
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Jumlah (dalam botol)</label>
                          </div>
                          <div class="col-md-4">
                            <input type="number" style="margin-top: 7px" name="" value="" class="form-control" id="mcr_jumlah">
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                          </div>
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Jam mulai</label>
                            <input type="text" name="" value="" class="time form-control" id="mcr_mulai">
                          </div>
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Jam selesai</label>
                            <input type="text" name="" value="" class="time form-control" id="mcr_selesai">
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Cek tambahan obat</label>
                          </div>
                          <div class="col-md-4">
                            <select style="margin-top: 7px" class="form-control" name="" id="mcr_cek">
                              <option value="Ya">Ya</option>
                              <option value="Tidak">Tidak</option>
                            </select>
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Sisa</label>
                          </div>
                          <div class="col-md-4">
                            <input style="margin-top: 7px" type="number" name="" value="" class="form-control" id="mcr_sisa">
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Jam ukur buang</label>
                          </div>
                          <div class="col-md-4">
                            <input style="margin-top: 7px" type="text" name="" value="" class="time form-control" id="mcr_buang">
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Dari</label>
                          </div>
                          <div class="col-md-4">
                            <select style="margin-top: 7px" class="form-control" id="mcr_dari">
                              <option val='Selang lambung'>Selang lambung</option>
                              <option val='Kandung kencing'>Kandung kencing</option>
                              <option val='Air seni biasa'>Air seni biasa</option>
                              <option val='Drainage'>Drainage</option>
                            </select>
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Balance Cairan</label>
                          </div>
                          <div class="col-md-4">
                            <input style="margin-top: 7px" type="number" name="" value="" class="form-control" id="mcr_balance">
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Keterangan</label>
                          </div>
                          <div class="col-md-8">
                            <textarea style="margin-top: 7px" class="form-control" id="mcr_ket"></textarea>
                          </div>
                        </div>
                      </div>

                    <br>
                    <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_asesmen">
                        <h4><i class="icon fa fa-info"></i> Success!</h4>
                        <p>Data Berhasil Tersimpan</p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_asesmen">
                        <h4><i class="icon fa fa-ban"></i> Error !</h4>
                        <p id="error_ket_asesmen"></p>
                    </div>
                </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="saveObCairan('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_asesmen"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-pemberian">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Observasi pemberian obat <span id="label_kat_pemberian"></span> </h4>
            </div>
            <div class="modal-body">
                  <div style="margin-bottom:7px">
                    <button type="button" class="btn btn-success" onclick="addPemberianObat()">
                      <i class="fa fa-plus"></i> Add
                    </button>
                    <%-- <button type="button" class="btn btn-info" onclick="showGrafVitalSign('<s:property value="rawatInap.idDetailCheckup"/>')">
                      <i class="fa fa-pie-chart"></i> View Graf
                    </button> --%>
                  </div>
                  <table class="table table-bordered">
                    <thead id="thead_pemberian">
                    </thead>
                    <tbody id="body-list-pemberian">

                    </tbody>
                  </table>
                <input type="hidden" id="kat_pemberian">
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
              <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
              </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-add-pemberian-non-parenteral">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Observasi pemberian obat non parenteral</span></h4>
            </div>
            <div class="modal-body">
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label>Created Date</label>
                          </div>
                          <div class="col-md-4">
                            <input type="date" name="" value="" class="date form-control" id="nonpar_date"/>
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Obat</label>
                          </div>
                          <div class="col-md-8">
                            <select style="margin-top: 7px" class="form-control" name="" id="select_obat_nonpar">

                            </select>
                          </div>
                        </div>
                      </div>

                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Dosis</label>
                          </div>
                          <div class="col-md-4">
                            <input type="text" style="margin-top: 7px" name="" value="" class="form-control" id="nonpar_dosis">
                          </div>
                        </div>
                      </div>

                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Waktu</label>
                          </div>
                          <div class="col-md-4">
                            <select style="margin-top: 7px" class="form-control" name="" id="select_waktu_nonpar">
                              <option value='pagi'>Pagi</option>
                              <option value='siang'>Siang</option>
                              <option value='sore'>Sore</option>
                              <option value='malam'>Malam</option>
                              <option value='bila perlu'>Bila Perlu</option>
                            </select>
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Keterangan</label>
                          </div>
                          <div class="col-md-8">
                            <textarea style="margin-top: 7px" class="form-control" name="name" rows="8" cols="80" id="nonpar_keterangan"></textarea>
                          </div>
                        </div>
                      </div>
                    <br>
                    <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_asesmen">
                        <h4><i class="icon fa fa-info"></i> Success!</h4>
                        <p>Data Berhasil Tersimpan</p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_asesmen">
                        <h4><i class="icon fa fa-ban"></i> Error !</h4>
                        <p id="error_ket_asesmen"></p>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="savePemberianObat('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_asesmen"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-add-pemberian-parenteral">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Observasi pemberian obat parenteral</span></h4>
            </div>
            <div class="modal-body">
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label>Created Date</label>
                          </div>
                          <div class="col-md-4">
                            <input type="date" name="" value="" class="date form-control" id="par_date"/>
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Obat</label>
                          </div>
                          <div class="col-md-8">
                            <select style="margin-top: 7px" class="form-control" name="" id="select_obat_par">

                            </select>
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Cara Pemberian</label>
                          </div>
                          <div class="col-md-8">
                            <input style="margin-top: 7px" type="text" name="" value="" class="form-control" id="par_cara">
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Dosis</label>
                          </div>
                          <div class="col-md-4">
                            <input style="margin-top: 7px" type="text" name="" value="" class="form-control" id="par_dosis">
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Skin Tes</label>
                          </div>
                          <div class="col-md-4">
                            <input style="margin-top: 7px" type="text" name="" value="" class="form-control" id="par_skintes">
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Waktu</label>
                          </div>
                          <div class="col-md-4">
                            <select style="margin-top: 7px" class="form-control" name="" id="select_waktu_par">
                              <option value='pagi'>Pagi</option>
                              <option value='siang'>Siang</option>
                              <option value='sore'>Sore</option>
                              <option value='malam'>Malam</option>
                              <option value='bila perlu'>Bila Perlu</option>
                            </select>
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="row">
                          <div class="col-md-4">
                            <label style="margin-top: 7px">Keterangan</label>
                          </div>
                          <div class="col-md-8">
                            <textarea style="margin-top: 7px" class="form-control" name="name" rows="8" cols="80" id="par_keterangan"></textarea>
                          </div>
                        </div>
                      </div>
                    <br>
                    <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_asesmen">
                        <h4><i class="icon fa fa-info"></i> Success!</h4>
                        <p>Data Berhasil Tersimpan</p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_asesmen">
                        <h4><i class="icon fa fa-ban"></i> Error !</h4>
                        <p id="error_ket_asesmen"></p>
                    </div>
                </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_asesmen" onclick="savePemberianObat('<s:property value="rawatInap.noCheckup"/>', '<s:property value="rawatInap.idDetailCheckup"/>')"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_asesmen"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-edukasi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Form Edukasi Pasien Dan Keluarga Terintregasi </h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <button type="button" class="btn btn-success" id="" onclick="addFormEdukasi('inap14')">
                        <i class="fa fa-plus"></i> Add Header Edukasi
                    </button>
                    <button type="button" class="btn btn-success" id="" onclick="addFormEdukasi('inap15')">
                        <i class="fa fa-plus"></i> Add Parameter Edukasi
                    </button>

                    <table class="table table-bordered">
                        <thead>
                        <td>Asesmen</td>
                        <td>Created Who</td>
                        <td>Created Date</td>
                        <td>Action</td>
                        </thead>
                        <tbody id="list-body-header-edukasi">

                        </tbody>
                    </table>

                    <table class="table table-bordered">
                        <thead>
                        <td>Asesmen</td>
                        <td>Created Who</td>
                        <td>Created Date</td>
                        <td>Action</td>
                        </thead>
                        <tbody id="list-body-edukasi">

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

<script type='text/javascript' src='<s:url value="/pages/dist/js/rekammedic.js"/>'></script>
<script type='text/javascript'>

    var idDetailCheckup = $('#no_detail_checkup').val();
    var idPoli          = $('#id_palayanan').val();
    var idRawatInap     = $('#id_rawat_inap').val();
    var idPasien        = $('#id_pasien').val();
    var noCheckup       = $('#no_checkup').val();
    var today           = new Date();
    var month           = ""+(today.getMonth()+1);
    var day             = ""+today.getDate();

    if (month.length < 2) {
      month = "0"+month;
    }
    if (day.length < 2) {
      day = "0"+day;
    }
    var date = today.getFullYear()+"-"+month+"-"+day;
    // console.log(date);

    function titleCase(string) {
        var sentence = string.toLowerCase().split(" ");
        for(var i = 0; i< sentence.length; i++){
           sentence[i] = sentence[i][0].toUpperCase() + sentence[i].slice(1);
        }
        // document.write(sentence.join(" "));
        return sentence;
     }

    $(document).ready(function () {
        $('#rawat_inap').addClass('active');
        $("#mcr_mulai").timepicker();
        $("#mcr_selesai").timepicker();
        $("#mcr_buang").timepicker();
        $(".date").val(date);
        listDokter();
        listTindakan();
        listDiagnosa();
        listSelectDokter();
        listLab();
        listObat();
        listDiet();
        listRuanganInap();
        listResepPasien();

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

        hitungStatusBiaya();
    });

    function hitungStatusBiaya() {
        var jenis = $("#id_jenis_pasien").val();
        CheckupDetailAction.getStatusBiayaTindakan(idDetailCheckup, "RI", function (response) {
            if (jenis == "bpjs") {
                $('#status_bpjs').show();
                if (response.tarifBpjs != null && response.tarifTindakan != null) {

                    var coverBiaya = response.tarifBpjs;
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
            } else {
                $('#status_bpjs').hide();
            }
        });
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
            } else {
                option = option;
            }
        });

        $('#kamar_detail').html(option);
        $('#ruangan_ruang').html(option);
    }

    function confirmSaveKeterangan() {

        var idKtg       = "selesai";
        var noCheckup   = $("#no_checkup").val();
        var poli        = "";
        var kelas       = "";
        var kamar       = "";
        var idDokter    = "";
        var ket_selesai = $('#ket_selesai').val();
        var tgl_cekup   = $('#tgl_cekup').val();
        var ket_cekup   = $('#cekup_ket').val();
        var cara        = $('#ket_cara').val();
        var pendamping  = $('#ket_pendamping').val();
        var tujuan      = $('#ket_tujuan').val();

        if (idKtg != '' && ket_selesai != ''){
            $('#modal-confirm-dialog').modal('show');
            $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+idDokter+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+cara+'\' , \''+pendamping+'\', \''+tujuan+'\')');
        } else {
            $('#warning_ket').show().fadeOut(5000);
            $('#war_catatan').show();
        }
    }

    function saveKeterangan(idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, cara, pendamping, tujuan){
        $('#modal-confirm-dialog').modal('hide');
        var jenisPasien = $('#id_jenis_pasien').val();
        $('#save_ket').hide();
        $('#load_ket').show();
        dwr.engine.setAsync(true);
        CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, cara, pendamping, tujuan, function (response) {
            $('#info_dialog').dialog('open');
            $('#close_pos').val(8);
            $('#save_ket').show();
            $('#load_ket').hide();
        });
    }

    function listSelectTindakan(idKategori) {
        var idx = idKategori.selectedIndex;
        var idKtg = idKategori.options[idx].value;
        var option = "<option value=''>[Select One]</option>";
        if (idKtg != '') {
            CheckupDetailAction.getListComboTindakan(idKtg, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idTindakan + "'>" + item.tindakan + "</option>";
                    });
                    $('#tin_id_tindakan').html(option);
                } else {
                    $('#tin_id_tindakan').html(option);
                }
            });
        } else {
            $('#tin_id_tindakan').html(option);
        }
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
            desti = "#pos_diet";
        } else if (back == 6) {
            desti = "#pos_obat";
        } else if (back == 7) {
            desti = "#pos_ruangan";
        } else if (back == 8) {
            window.location.href = 'initForm_rawatinap.action';
        } else if (back == 9){
            desti = '#pos_rssep';
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
            $('#modal-dokter').modal('show');

        } else if (select == 2) {
            $('#tin_id_ketgori_tindakan, #tin_id_tindakan, #tin_id_perawat').val('').trigger('change');
            $('#tin_qty').val('1');
            $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
            $('#save_tindakan').attr('onclick', 'saveTindakan(\'' + id + '\')').show();
            $('#modal-tindakan').modal('show');

        } else if (select == 3) {
            $('#nosa_id_diagnosa, #nosa_jenis_diagnosa').val('').trigger('change');
            $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
            $('#save_diagnosa').attr('onclick', 'saveDiagnosa(\'' + id + '\')').show();
            $('#modal-diagnosa').modal('show');

        } else if (select == 4) {
            $('#lab_kategori, #lab_lab').val('').trigger('change');
            $('#load_lab, #warning_lab, #war_kategori_lab, #war_lab, #war_parameter').hide();
            $('#save_lab').attr('onclick', 'saveLab(\'' + id + '\')').show();
            $('#modal-lab').modal('show');
        } else if (select == 5) {
            $('#diet_pagi, #diet_siang, #diet_malam').val('');
            $('#save_diet').attr('onclick', 'saveDiet(\'' + id + '\')').show();
            $('#load_diet, #warning_diet, #war_pagi1, #war_siang1, #war_malam1').hide();
            $('#modal-diet').modal('show');
        } else if (select == 6) {
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
            $('#modal-obat').modal('show');
        }else if (select == 7) {
            $('#resep_apotek').val('').trigger('change').attr('disabled', false);
            $('#resep_nama_obat').val('').trigger('change');
            $('#resep_keterangan').val('');
            $('#resep_qty').val('');
            $('#resep_jenis_satuan').val('').trigger('change');
            $('#resep_stok_box, #resep_stok_lembar, #resep_stok_biji').val('');
            $('#body_detail').html('');
            $('#desti_apotek').html('');
            $('#save_resep_head').show();
            $('#load_resep_head').hide();
            $('#desti_apotek').html('');
            $('#resep_apotek').attr("onchange", "var warn =$('#war_rep_apotek').is(':visible'); if (warn){$('#cor_rep_apotek').show().fadeOut(3000);$('#war_rep_apotek').hide()}; setObatPoli(this)");
            $('#resep_nama_obat').attr("onchange", "var warn =$('#war_rep_obat').is(':visible'); if (warn){$('#cor_rep_obat').show().fadeOut(3000);$('#war_rep_obat').hide()}; setStokObatApotek(this)");
            $('#body_detail').html('');
            $('#modal-resep-head').modal('show');
        }
    }

    function formatRupiah(angka) {
        var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
        ribuan = ribuan.join('.').split('').reverse().join('');
        return ribuan;
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
            console.log(response)
            data = response;
            if (data != null) {
                $.each(data, function (i, item) {
                    table += "<tr>" +
                            "<td>" + item.idDokter + "</td>" +
                            "<td>" + item.namaDokter + "</td>" +
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDokter(\'' + item.idTeamDokter + '\',\'' + item.idDokter + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">' + "</td>" +
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
        console.log(qty);

        if (idDetailCheckup != '' && idTindakan != '' && idDokter != '' && idPerawat != '' && qty > 0 && idKategori != '') {

            $('#save_tindakan').hide();
            $('#load_tindakan').show();

            if (id != '') {
                dwr.engine.setAsync(true);
                TindakanRawatAction.editTindakanRawat(id, idDetailCheckup, idTindakan, idDokter, idPerawat, qty, {
                    callback: function (response) {
                        if (response == "success") {
                            dwr.engine.setAsync(false);
                            listTindakan();
                            hitungStatusBiaya();
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
                TindakanRawatAction.saveTindakanRawat(idDetailCheckup, idTindakan, idDokter, idPerawat, qty, {
                    callback: function (response) {
                        if (response == "success") {
                            dwr.engine.setAsync(false);
                            listTindakan();
                            hitungStatusBiaya();
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

                    table += "<tr>" +
                            "<td>" + dateFormat + "</td>" +
                            "<td>" + item.namaTindakan + "</td>" +
                            "<td align='right'>" + tarif +"</td>" +
                            "<td align='center'>" + item.qty + "</td>" +
                            "<td align='right'>" + tarifTotal + "</td>" +
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editTindakan(\'' + item.idTindakanRawat + '\',\'' + item.idTindakan + '\',\'' + item.idKategoriTindakan + '\',\'' + item.idPerawat + '\',\'' + item.qty + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">' + "</td>" +
                            "</tr>";

                });
                table = table + "<tr>" +
                        "<td colspan='4'>Total</td>" +
                        "<td align='right'>" + formatRupiah(trfTtl) + "</td>" +
                        "<td></td>" +
                        "</tr>";
                $('#body_tindakan').html(table);
            }
        });



    }

    function saveDiagnosa(id) {

        var idDiagnosa = $('#nosa_id_diagnosa').val();
        var jenisDiagnosa = $('#nosa_jenis_diagnosa').val();

        if (idDetailCheckup != '' && idDiagnosa != '' && jenisDiagnosa != '') {

            $('#save_diagnosa').hide();
            $('#load_diagnosa').show();

            if (id != '') {
                dwr.engine.setAsync(true);
                DiagnosaRawatAction.editDiagnosa(id, idDiagnosa, jenisDiagnosa, {
                    callback: function (response) {
                        if (response == "success") {
                            dwr.engine.setAsync(false);
                            listDiagnosa();
                            $('#modal-diagnosa').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#close_pos').val(3);
                        } else {

                        }
                    }
                })
            } else {
                dwr.engine.setAsync(true);
                DiagnosaRawatAction.saveDiagnosa(idDetailCheckup, idDiagnosa, jenisDiagnosa, {
                    callback: function (response) {
                        if (response == "success") {
                            dwr.engine.setAsync(false);
                            listDiagnosa();
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
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDiagnosa(\'' + item.idDiagnosaRawat + '\',\'' + item.idDiagnosa + '\',\'' + item.jenisDiagnosa + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">' + "</td>" +
                            "</tr>"
                });
            }
        });

        $('#body_diagnosa').html(table);
    }

    function listSelectLab(select) {
        var idx = select.selectedIndex;
        var idKategori = select.options[idx].value;

        var option = "<option value=''>[Select One]</option>";
        if (idKategori != '') {
            LabAction.listLab(idKategori, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idLab + "'>" + item.namaLab + "</option>";
                    });
                } else {
                    option = option;
                }
            });
        } else {
            option = option;
        }

        $('#lab_lab').html(option);
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

        if (idDetailCheckup != '' && idKategori != '' && idLab != '' && idParameter != '') {

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

                    if (item.idLab != null) {
                        pemeriksaan = item.idLab;
                    }
                    if (item.statusPeriksaName != null) {
                        status = item.statusPeriksaName;
                    }
                    if (item.labName != null) {
                        lab = item.labName;
                    }
                    table += "<tr>" +
                            "<td>" + dateFormat + "</td>" +
                            "<td>" + lab + "</td>" +
                            "<td>" + status + "</td>" +
                            "<td>" + item.kategoriLabName + "</td>" +
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editLab(\'' + item.idPeriksaLab + '\',\'' + item.idLab + '\',\'' + item.idKategoriLab + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">' + "</td>" +
                            "</tr>"
                });
            }
        });

        $('#body_lab').html(table);
    }

    function listSelectObat(select) {
        var idx = select.selectedIndex;
        var idJenis = select.options[idx].value;
        var option = "<option value=''>[Select One]</option>";
        var stok = "";

        if (idJenis != '') {
            ObatAction.listObat(idJenis, function (response) {
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
        $('#resep_nama_obat').html(option);
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

                console.log(obat);

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
                    <%--if (item.namaJenisObat != null) {--%>
                    <%--jenis = item.namaJenisObat;--%>
                    <%--}--%>

                    <%--table += "<tr>" +--%>
                    <%--"<td>" + dateFormat + "</td>" +--%>
                    <%--"<td>" + id + "</td>" +--%>
                    <%--"<td>" + obat + "</td>" +--%>
                    <%--//                            "<td>" + jenis + "</td>" +--%>
                    <%--"<td>" + qty + "</td>" +--%>
                    <%--"<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editObat(\'' + item.idObatInap + '\',\'' + item.idObat + '\',\'' + item.qty + '\',\'' + item.stokMasterObat + '\',\'' + item.namaObat + '\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +--%>
                    <%--"</tr>"--%>
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

    function saveDiet(id) {

        var dietPagi = $('#diet_pagi').val();
        var dietSiang = $('#diet_siang').val();
        var dietMalam = $('#diet_malam').val();
        var bentukDiet = $('#bentuk_diet').val();

        if(id != ''){
            $('#save_diet_edit').hide();
            $('#load_diet_edit').show();
            if(bentukDiet != ''){
                dwr.engine.setAsync(true);
                OrderGiziAction.editOrderGizi(id, bentukDiet, function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        listDiet();
                        $('#modal-diet-edit').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(5);
                        $('#save_diet_edit').show();
                        $('#load_diet_edit').hide();
                    } else {
                        $('#save_diet_edit').show();
                        $('#load_diet_edit').hide();
                    }
                });
            }else{
                $('#warning_diet_edit').show().fadeOut(5000);
                $('#war_bentuk_diet').show();
            }
        }else{
            if (dietPagi != '' && dietSiang != '' && dietMalam != '') {
                $('#save_diet').hide();
                $('#load_diet').show();

                var result = [];
                result.push({'pagi':dietPagi});
                result.push({'siang':dietSiang});
                result.push({'malam':dietMalam});

                var data = JSON.stringify(result);

                dwr.engine.setAsync(true);
                OrderGiziAction.saveOrderGizi(idRawatInap, data, function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        listDiet();
                        $('#modal-diet').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(5);
                        $('#save_diet').show();
                        $('#load_diet').hide();
                    } else {
                        $('#save_diet').show();
                        $('#load_diet').hide();
                    }
                });
            } else {
                $('#warning_diet').show().fadeOut(5000);
                if (dietPagi == '') {
                    $('#war_pagi1').show();
                }
                if (dietSiang == '') {
                    $('#war_siang1').show();
                }
                if (dietMalam == '') {
                    $('#war_malam1').show();
                }
            }
        }
    }

    function listDiet() {
        var table = "";
        var data = [];

        OrderGiziAction.listOrderGizi(idRawatInap, function (response) {
            data = response;
            if (data != null) {
                $.each(data, function (i, item) {
                    console.log(data);

                    var tanggal = item.createdDate;
                    var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                    var label = "";
                    var btn = "";

                    if(item.approveFlag == "Y"){
                        btn = '<div class="input-group">' +
                            '<input class="form-control" onchange="cekBarcode(this.value, \''+item.idOrderGizi+'\')">' +
                            '<div class="input-group-addon">' +
                            '<span id="status'+item.idOrderGizi+'"></span>' +
                            '</div>' +
                            '</div>';
                        label = '<label class="label label-info"> telah dikonfirmasi</label>';
                    }else{
                        btn = '<img border="0" class="hvr-grow" onclick="editDiet(\'' + item.idOrderGizi + '\',\'' + item.idDietGizi + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">';
                        label = '<label class="label label-warning"> menunggu konfirmasi</label>'
                    }

                    if(item.diterimaFlag == "Y"){
                        btn = '<div class="input-group">' +
                            '<input class="form-control" value="'+item.idOrderGizi+'" disabled>' +
                            '<div class="input-group-addon">' +
                            '<img src="<s:url value="/pages/images/icon_success.ico"/>" style="height: 20px; width: 20px;">'+
                            '</div>' +
                            '</div>';
                        label = '<label class="label label-success"> telah diterima</label>';
                    }

                    table += "<tr>" +
                            "<td>" + dateFormat + "</td>" +
                            "<td>" + item.idDietGizi + "</td>" +
                            "<td>" + item.bentukDiet + "</td>" +
                            "<td>" + item.keterangan + "</td>" +
                            "<td style='vertical-align: middle' >" + label + "</td>" +
                            "<td align='center'>" + btn + "</td>" +
                            "</tr>"
                });
            }
        });

        $('#body_diet').html(table);
    }

    function cekBarcode(value, idOrderGizi){

        if(value != '' && idOrderGizi != ''){

            if(value == idOrderGizi){
                $('#status'+idOrderGizi).html('<i style="color: #00a65a" class="fa fa-circle-o-notch fa-spin"></i>');
                setTimeout(function () {
                    OrderGiziAction.updateDiterimaFlag(idOrderGizi, function (response) {
                        if(response.status == "success"){
                            listDiet();
                        }else{
                            $('#status' + idOrderGizi).html('<img src="<s:url value="/pages/images/icon_failure.ico"/>" style="height: 20px; width: 20px;">');
                        }
                    });
                },200);
            }else{
                $('#status'+idOrderGizi).html('<i style="color: #00a65a" class="fa fa-circle-o-notch fa-spin"></i>');
                setTimeout(function () {
                    $('#status' + idOrderGizi).html('<img src="<s:url value="/pages/images/icon_failure.ico"/>" style="height: 20px; width: 20px;">');
                },200);
            }
        }else{
            $('#status' + idOrderGizi).html('');
        }
    }

    function editDokter(id, idDokter) {
        $('#load_dokter, #war_dok').hide();
        $('#save_dokter').attr('onclick', 'saveDokter(\'' + id + '\')').show();
        $('#dok_id_dokter').val(idDokter).trigger('change');
        $('#modal-dokter').modal('show');
    }

    function editTindakan(id, idTindakan, idKategori, idPerawat, qty) {
        $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
        $('#tin_id_ketgori_tindakan').val(idKategori).trigger('change');
        $('#tin_id_tindakan').val(idTindakan).trigger('change');
        $('#tin_id_perawat').val(idPerawat).trigger('change');
        $('#tin_qty').val(qty);
        $('#save_tindakan').attr('onclick', 'saveTindakan(\'' + id + '\')').show();
        $('#modal-tindakan').modal('show');
    }

    function editDiagnosa(id, idDiagnosa, jenis) {
        $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
        $('#save_diagnosa').attr('onclick', 'saveDiagnosa(\'' + id + '\')').show();
        $('#nosa_id_diagnosa').val(idDiagnosa).trigger('change');
        $('#nosa_jenis_diagnosa').val(jenis).trigger('change');
        $('#modal-diagnosa').modal('show');
    }

    function editLab(id, idLab, idKategoriLab) {
        $('#load_lab, #warning_lab, #war_kategori_lab, #war_lab, #war_parameter').hide();
        $('#save_lab').attr('onclick', 'saveLab(\'' + id + '\')').show();
        $('#lab_kategori').val(idKategoriLab).trigger('change');
        var idParameter = [];
        PeriksaLabAction.listParameterPemeriksaan(id, function (response) {
            data = response;
            if (data != null) {
                $.each(data, function (i, item) {
                    idParameter.push(item.idLabDetail);
                });
            }
        });
        $('#lab_lab').val(idLab).trigger('change');
        $('#lab_parameter').val(idParameter).trigger('change');
        $('#modal-lab').modal('show');
    }

    function editDiet(id, idDietGizi) {
        $('#load_diet_edit, #warning_diet_edit, #war_bentuk_diet').hide();
        // $('#diet_pagi').val(pagi1);
        // $('#bentuk_pagi').val(pagi2);
        // $('#diet_siang').val(siang1);
        // $('#bentuk_siang').val(siang2);
        // $('#diet_malam').val(malam1);
        $('#bentuk_diet').val(idDietGizi).trigger('change');
        $('#save_diet_edit').attr('onclick', 'saveDiet(\'' + id + '\')').show();
        $('#modal-diet-edit').modal('show');
    }

    function editObat(id, idobat, qty, jenis, namaObat, qtyBox, qtyLembar, qtyBiji, lembarPerBox, bijiPerLembar) {
        var qtyBox1 = "";
        var qtyLembar1 = "";
        var qtyBiji1 = "";

        if(qtyBox != 'null'){
            qtyBox1 = qtyBox;
        }

        if(qtyLembar != 'null'){
            qtyLembar1 = qtyLembar;
        }

        if(qtyBiji != 'null'){
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
        $('#modal-obat').modal('show');
    }

    function editRuangan(kelas, ruang) {
        $('#load_ruangan, #war_ruangan_kelas, #war_ruangan_ruang').hide();
        $('#ruangan_kelas').val(kelas).trigger('change');
        $('#ruangan_ruang').val(ruang).trigger('change');
        $('#modal-ruangan').modal('show');
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


    function listRuanganInap() {

        var table = "";
        var data = [];
        var no = "";
        var name = "";
        var kelas = "";

        CheckupDetailAction.getListRuangInapByIdDetailCheckup(idDetailCheckup, function (response) {
            data = response;
            if (data != null) {
                $.each(data, function (i, item) {
                    if (item.namaRangan != null) {
                        name = item.namaRangan;
                    }
                    if (item.noRuangan != null) {
                        no = item.noRuangan;
                    }
                    if (item.kelasRuanganName != null) {
                        kelas = item.kelasRuanganName;
                    }
                    table += "<tr>" +
                            "<td>" + name + "</td>" +
                            "<td>" + no + "</td>" +
                            "<td>" + kelas + "</td>" +
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editRuangan(\'' + item.idKelas + '\',\'' + item.idRuangan + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">' + "</td>" +
                            "</tr>"
                });
            }
        });

        $('#no_ruang').html(no);
        $('#name_ruang').html(name);
        $('#body_ruangan').html(table);
    }

    function saveEditRuang() {

        var idKelas = $('#ruangan_kelas').val();
        var idRuang = $('#ruangan_ruang').val();

        if (idDetailCheckup != '' && idKelas != '' && idRuang) {
            $('#save_ruang').hide();
            $('#load_ruang').show();

            dwr.engine.setAsync(true);
            CheckupDetailAction.saveUpdateRuangan(idRuang, idDetailCheckup, {
                callback: function (response) {
                    if (response == "SUCCESS") {
                        dwr.engine.setAsync(false);
                        listRuanganInap();
                        $('#modal-ruangan').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(7);
                        $('#save_ruang').show();
                        $('#load_ruang').hide();
                    } else {

                    }
                }
            })

        } else {
            $('#warning_ruangan').show().fadeOut(5000);

            if (idKelas == '') {
                $('#war_ruangan_kelas').show();
            }
            if (idRuang == '') {
                $('#war_ruangan_ruang').show();
            }
        }
    }

    function showFormCekup(select){
        var idx = select.selectedIndex;
        var idKet = select.options[idx].value;
        if(idKet == "Checkup Ulang"){
            $('#form-cekup').show();
        }else{
            $('#form-cekup').hide();
        }
    }

    function addObatToList() {

        var apotek = $('#resep_apotek').val();
        var obat = $('#resep_nama_obat').val();
        var ket = $('#resep_keterangan').val();
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

        if (obat != '' && ket != '' && qty != '' && apotek != '' && jenisSatuan != '') {

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
                    $('#resep_apotek').attr('disabled', true);
                    $('#desti_apotek').html(namaPelayanan);
                    var row = '<tr id=' + id + '>' +
                        '<td>' + id + '</td>' +
                        '<td>' + nama + '</td>' +
                        '<td align="center">' + qty + '</td>' +
                        '<td align="center">' + jenisSatuan + '</td>' +
                        '<td>' + ket + '</td>' +
                        '<td align="center"><img border="0" onclick="delRowObat(\'' + id + '\')" class="hvr-grow" src="<s:url value="/pages/images/delete-flat.png"/>" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                        '</tr>';
                    $('#body_detail').append(row);
                }
            } else {
                $('#warning_resep_head').show().fadeOut(5000);
                $('#msg_resep').text('Qty tidak boleh melebihi stok obat..!');
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
            if (ket == '') {
                $('#war_rep_ket').show();
            }
            $('#warning_resep_head').show().fadeOut(5000);
            $('#msg_resep').text('Silahkan cek kembali data inputan!');
        }
    }

    function delRowObat(id){
        $('#'+id).remove();
    }

    function saveResepObat() {

        var idDokter = $('#tin_id_dokter').val();
        var data = $('#tabel_rese_detail').tableToJSON();
        var stringData = JSON.stringify(data);
        var idPelayanan = $('#resep_apotek').val();
        var apotek = $('#resep_apotek').val();

        if (stringData != '[]') {
            var idPelayanan = apotek.split('|')[0];
            var namaPelayanan = apotek.split('|')[1];
            $('#save_resep_head').hide();
            $('#load_resep_head').show();
            dwr.engine.setAsync(true);
            PermintaanResepAction.saveResepPasien(idDetailCheckup, idPoli, idDokter, idPasien, stringData, idPelayanan, {
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
                        $('#save_resep_head').show();
                        $('#load_resep_head').hide();
                    }
                }
            });
        } else {
            $('#warning_resep_head').show().fadeOut(5000);
        }
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
                        "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="detailResep(\'' + item.idApprovalObat + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">' +
                        ' <img onclick="printResep(\'' + idResep + '\')" class="hvr-grow" src="<s:url value="/pages/images/icons8-print-25.png"/>" style="cursor: pointer;">' +
                        "</td>" +
                        "</tr>"
                });
            }
        });

        $('#body_resep').html(table);
    }

    function detailResep(id){
        $('#modal-resep-detail').modal('show');
        listDetailResepPasien(id);
    }

    function listDetailResepPasien(idApprovalObat) {

        var table = "";
        var data = [];

        PermintaanResepAction.listDetail(idApprovalObat, function (response) {
            data = response;
            console.log(data);
            if (data != null) {
                $.each(data, function (i, item) {

                    var qty      = "";
                    var namaObat = "";
                    var ket      = "";
                    var idObat   = "";

                    if(item.idObat != null){
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
                            "<td>"+ '<span id=obat'+idObat+'>' + namaObat + '</span><input style="display:none; width: 120px;" type="text" id=newObat'+idObat+' class="form-control"><input type="hidden" id=idObat'+idObat+'>' + "</td>" +
                            "<td>"+'<span id=qty'+idObat+'>'+ qty + '</span>'+'<input type="hidden" id=newId'+idObat+' value='+idObat+'>'+
                            '<input style="display:none; width: 80px" type="number" id=newQty'+idObat+' class="form-control">'+ "</td>" +
                            "<td>"+'<span id=ket'+idObat+'>'+ ket + '</span>'+
                            '<select class="form-control" id=newKet'+idObat+' style="display:none"'+
                            '<option value="">[Select One]</option>'+
                            '<option value="2 x 1 /Hari">2 x 1 /Hari</option>'+
                            '<option value="3 x 1 /Hari">3 x 1 /Hari</option>'+
                            '</select>'+ "</td>" +
                            "<td align='center'>" + '<img border="0" id='+idObat+' class="hvr-grow" onclick="editObatResep(\'' + item.idTransaksiObatDetail + '\',\'' + idObat + '\',\'' + qty + '\',\'' + ket + '\',\'' + namaObat + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer; ">'+
                            '<img border="0" id=save'+idObat+' class="hvr-grow" onclick="saveDetailResep(\'' + item.idTransaksiObatDetail + '\',\'' + idObat + '\',\''+item.idApprovalObat+'\')" src="<s:url value="/pages/images/save_flat.png"/>" style="cursor: pointer; height: 25px; width: 25px; display: none">'+ "</td>" +
                            "</tr>"
                });
            }
        });

        $('#body_detail_rep').html(table);
    }

    function editObatResep(id, idObat, qty, ket, namaObat){

        if($('#'+idObat).attr('src') == '/simrs/pages/images/icons8-create-25.png'){
            var url = '<s:url value="/pages/images/cnacel-flat.png"/>';
            $('#'+idObat).attr('src',url);
            $('#obat'+idObat).hide();
            $('#qty'+idObat).hide();
            $('#ket'+idObat).hide();

            $('#newObat'+idObat).show().val(namaObat);
            $('#newQty'+idObat).show().val(qty);
            $('#newKet'+idObat).show().val(ket);
            $('#save'+idObat).show();

        }else{
            var url = '<s:url value="/pages/images/icons8-create-25.png"/>';
            $('#'+idObat).attr('src',url);
            $('#obat'+idObat).show();
            $('#qty'+idObat).show();
            $('#ket'+idObat).show();

            $('#newObat'+idObat).hide();
            $('#newQty'+idObat).hide();
            $('#newKet'+idObat).hide();
            $('#save'+idObat).hide();
        }
    }

    function saveDetailResep(id, idObat, idApp){

        var obat    = $('#newId'+idObat).val();
        var qty     = $('#newQty'+idObat).val();
        var ket     = $('#newKet'+idObat).val();

        if(obat != '' && qty != '' && ket != ''){
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
        }else{
            $('#warning_detail').show().fadeOut(5000);
        }
    }

    function getNamaObat(){
        ObatAction.getListNamaObat( function (response) {
            $.each(response ,function(i, item){

            })
        })
    }

    function printResep(id) {
        window.open('printResepPasien_rawatinap.action?id=' + noCheckup+'&idResep='+id, '_blank');
    }

    function showModalResiko(noCheckup, idDetail, kat){

      $("#modal-resiko").modal("show");
      $("#kat_skor").val(kat);
      dwr.engine.setAsync(true);
      RawatInapAction.getKategoriSkorRanap(kat, function(kategori){
        $("#label-skor").html("");
        $("#label-skor").html(kategori.namaKategori);

        $("#label-add-resiko").html("");
        $("#label-add-resiko").html(kategori.namaKategori);

        RawatInapAction.getListGroupSkorRanap(noCheckup, idDetail, kat, function(response){
          if (response != null) {
            var str = "";
            $.each(response, function(i, item){
              str += "<tr>"+
              "<td>"+item.namaKategori+"</td>"+
              "<td>"+item.skor+"</td>"+
              "<td>"+item.createdWho+"</td>"+
              "<td>"+formateDateTime(item.stDate)+"</td>"+
              "<td><button class='btn btn-primary' onclick=\"viewSkor('"+item.groupId+"')\">View</button></td>"+
              "</tr>";
            });

            $("#body-list-resiko").html(str);
          }
          // console.log(response);
        });
      });
    }

    function addResiko(noCheckup, idDetail){
      $("#modal-add-resiko").modal("show");
      var kategori = $("#kat_skor").val();

      dwr.engine.setAsync(true);
      RawatInapAction.getListParameterByKategori(noCheckup, idDetail, kategori, function(response){

        var str = "";
        if (response != null){
                var n = 0;
                var dateupline = "<div class='form-group'>"+
                                "<div class='row'>"+
                                "<div class='col-md-4'><label>Created Date</label></div>"+
                                "<div class='col-md-4'><input type='date' class='form-control' id='val_rsk_date' /></div>"
                                "</div>"+
                                "</div>";
                $.each(response, function (i, item) {
                    n = i;
                    var upline = "";
                    if (item.namaParameter.length > 25) {
                      upline ="<div class='form-group'>" +
                      "<div class='row'>"+
                      "<div class='col-md-8'>"+
                      "<label>"+item.namaParameter+"</label>"+
                      "</div>"+
                      "<div class='col-md-4'>";
                    } else {
                      upline ="<div class='form-group'>" +
                      "<div class='row'>"+
                      "<div class='col-md-4'>"+
                      "<label>"+item.namaParameter+"</label>"+
                      "</div>"+
                      "<div class='col-md-8'>";
                    }

                        var opt = "";
                        RawatInapAction.getListSkorRanapByParam(item.idParameter, function(skors){
                          var up_select = "<select class='form-control' id='val_rsk_"+i+"'>";
                          if (skors.length > 0) {
                            $.each(skors, function(i, itemSkor){
                              if (item.skor == itemSkor.skor){
                                  opt += "<option value="+itemSkor.skor+" selected> "+itemSkor.namaSkor+" </option>";
                              } else {
                                opt += "<option value="+itemSkor.skor+"> "+itemSkor.namaSkor+" </option>";
                              }
                            });
                          } else {
                            opt = "<input type='text' class='form-control' id='val_rsk_"+i+"'>";
                          }


                          // console.log(skors);

                          var down_select = "</select>";
                          var downline = "<input type='hidden' id='id_rsk_"+i+"' value='"+item.idParameter+"'>"+
                                        "<input type='hidden' id='name_rsk_"+i+"' value='"+item.namaParameter+"'>"+
                                        "</div>" +
                                        "</div>"+
                                        "<hr style='color:#b0b0b0;'/>"+
                                        "</div>";
                                        // "<div class='box-header with-border' style='margin-bottom: 7px;'></div>";

                          if (skors.length > 0) {
                            str += upline+up_select+opt+down_select+downline;
                          } else {
                            str += upline+opt+downline;
                          }

                          $("#ind_resiko").val(n);
                          $("#body_resiko").html(str);
                        });
                });
              }
      });
    }

    function saveResiko(noCheckup, idDetail){

        var jsonrq = [];
        var ind = $("#ind_resiko").val();

       for (i = 0; i <= ind; i++){

           var id_rsk = $("#id_rsk_"+i+"").val();
           var nilai = $("#val_rsk_"+i+"").val();
           var name_rsk = $("#name_rsk_"+i+"").val();
           var ket_rsk = "";
           var val_rsk = "0";

           var intNilai = parseInt(nilai);
           if (isNaN(intNilai)) {
             ket_rsk = nilai;
           } else {
             val_rsk = nilai;
           }
           // console.log(parseInt(nilai));
           // console.log(typeof nilai);
           // console.log(isNaN(intNilai));

           jsonrq.push({'id':id_rsk, 'val':val_rsk, 'name':name_rsk, 'ket':ket_rsk});
       }

       var kategori = $("#kat_skor").val();

      var jsonstr = JSON.stringify(jsonrq);
      dwr.engine.setAsync(true);
      RawatInapAction.saveSkorRanapByKategori(noCheckup, idDetail, kategori, jsonstr, function(response){
        if (response.status == "success") {
          alert("sukses");

          RawatInapAction.getListGroupSkorRanap(noCheckup, idDetail, kategori, function(response){
            if (response != null) {
              var str = "";
              $.each(response, function(i, item){
                str += "<tr>"+
                "<td>"+item.namaKategori+"</td>"+
                "<td>"+item.skor+"</td>"+
                "<td>"+item.createdWho+"</td>"+
                "<td>"+formateDate(item.stDate)+"</td>"+
                "<td><button class='btn btn-primary' onclick=\"viewSkor('"+item.groupId+"')\">View</button></td>"+
                "</tr>";
              });

              $("#modal-add-resiko").modal("hide");
              $("#body-list-resiko").html("");
              $("#body-list-resiko").html(str);
            }
            // console.log(response);
          });

        } else {
          alert(response.msg);
        }
      });
    }

    function showModalAsesmen(noCheckup, idDetail, kat){

      $("#modal-asesmen").modal("show");
      $("#kat_asesmen").val(kat);
      dwr.engine.setAsync(true);
      RawatInapAction.getKategoriSkorRanap(kat, function(kategori){
        $("#label-asesmen").html("");
        $("#label-asesmen").html(kategori.namaKategori);

        $("#label-add-asesmen").html("");
        $("#label-add-asesmen").html(kategori.namaKategori);

        RawatInapAction.getListGroupSkorRanap(noCheckup, idDetail, kat, function(response){
          if (response != null) {
            // console.log(response);
            var str = "";
            $.each(response, function(i, item){
              // console.log(response);
              str += "<tr>"+
              "<td>"+item.namaKategori+"</td>"+
              "<td>"+item.createdWho+"</td>"+
              "<td>"+formateDateTime(item.stDate)+"</td>"+
              "<td align='center'><button class='btn btn-primary' onclick=\"viewAsesmen('"+item.groupId+"')\"><i class='fa fa-search'></i></button></td>"+
              "</tr>";
            });

            $("#body-list-asesmen").html(str);
          }
        });
      });
    }

    function addAsesmen(noCheckup, idDetail){
      $("#modal-add-asesmen").modal("show");
      var kategori = $("#kat_asesmen").val();

      dwr.engine.setAsync(true);
      RawatInapAction.getListParameterByKategori(noCheckup, idDetail, kategori, function(response){

        var str = "";
        if (response != null){
                var n = 0;
                $.each(response, function (i, item) {
                    n = i;
                    var upline = "";
                    if (item.namaParameter.length > 25) {
                      upline ="<div class='form-group'>" +
                      "<div class='row'>"+
                      "<div class='col-md-8'>"+
                      "<label>"+item.namaParameter+"</label>"+
                      "</div>"+
                      "<div class='col-md-4'>";
                    } else {
                      upline ="<div class='form-group'>" +
                      "<div class='row'>"+
                      "<div class='col-md-4'>"+
                      "<label>"+item.namaParameter+"</label>"+
                      "</div>"+
                      "<div class='col-md-8'>";
                    }

                        var opt = "";
                        RawatInapAction.getListSkorRanapByParam(item.idParameter, function(skors){
                          var up_select = "<select class='form-control' id='val_rsk_"+i+"' onchange=\"showOtherInput(this.id)\">";
                          // var other_text = "<input type='text' class='form-control' id='ot_val_rsk_"+i+"' style='display:none'/>";
                          if (skors.length > 0) {
                            $.each(skors, function(i, itemSkor){
                              opt += "<option value="+itemSkor.ketSkor+">"+itemSkor.namaSkor+"</option>";
                            });
                          } else {
                            if (item.type == "date") {
                              opt = "<input type='date' class='form-control' id='val_rsk_"+i+"'>";
                            } else if (item.type == "number"){
                              opt = "<input type='number' class='form-control' id='val_rsk_"+i+"'>";
                            } else {
                              opt = "<input type='text' class='form-control' id='val_rsk_"+i+"'>";
                            }
                          }


                          // console.log(skors);

                          var down_select = "</select>";
                          var downline = "<input type='hidden' id='id_rsk_"+i+"' value='"+item.idParameter+"'>"+
                                        "<input type='hidden' id='name_rsk_"+i+"' value='"+item.namaParameter+"'>"+
                                        "<input type='text' class='form-control' id='ot_val_rsk_"+i+"' style='display:none' placeholder='sebutkan ...'/>"+
                                        "</div>" +
                                        "</div>"+
                                        "<hr style='color:#b0b0b0;'/>"+
                                        "</div>";
                                        // "<div class='box-header with-border' style='margin-bottom: 7px;'></div>";

                          if (skors.length > 0) {
                            str += upline+up_select+opt+down_select+downline;
                          } else {
                            str += upline+opt+downline;
                          }

                          $("#ind_asesmen").val(n);
                          $("#body_asesmen").html(str);
                        });
                });
              }
      });
    }

    function saveAsesmen(noCheckup, idDetail){

        var jsonrq = [];
        var ind = $("#ind_asesmen").val();

       for (i = 0; i <= ind; i++){

           var id_rsk = $("#id_rsk_"+i+"").val();

           var nilai = "";
           if ($("#val_rsk_"+i+"").is("select")) {
             nilai = $("#val_rsk_"+i+" option:selected").text();
           } else {
             nilai = $("#val_rsk_"+i+"").val();
           }

           // var nilai = $("#val_rsk_"+i+"").val();
           // var nilai = $("#val_rsk_"+i+" option:selected").text();
           var name_rsk = $("#name_rsk_"+i+"").val();
           var ket_rsk = "";
           var val_rsk = "0";

           if (nilai.toLowerCase() == "lain") {
             ket_rsk = $("#ot_val_rsk_"+i+"").val();
           } else {
             ket_rsk = nilai;
           }

           // var intNilai = parseInt(nilai);
           // if (isNaN(intNilai)) {
           //   ket_rsk = nilai;
           // } else {
           //   val_rsk = nilai;
           // }
           jsonrq.push({'id':id_rsk, 'val':val_rsk, 'name':name_rsk, 'ket':ket_rsk});
       }

       var kategori = $("#kat_asesmen").val();

      var jsonstr = JSON.stringify(jsonrq);
      dwr.engine.setAsync(true);
      RawatInapAction.saveSkorRanapByKategori(noCheckup, idDetail, kategori, jsonstr, function(response){
        if (response.status == "success") {
          alert("sukses");

          RawatInapAction.getListGroupSkorRanap(noCheckup, idDetail, kategori, function(response){
            if (response != null) {
              var str = "";
              $.each(response, function(i, item){
                str += "<tr>"+
                "<td>"+item.namaKategori+"</td>"+
                "<td>"+item.createdWho+"</td>"+
                "<td>"+formateDate(item.stDate)+"</td>"+
                "<td><button class='btn btn-primary' onclick=\"viewAsesmen('"+item.groupId+"')\">View</button></td>"+
                "</tr>";
              });

              $("#modal-add-asesmen").modal("hide");
              $("#body-list-asesmen").html("");
              $("#body-list-asesmen").html(str);
            }
            // console.log(response);
          });

        } else {
          alert(response.msg);
        }
      });
    }

    function viewAsesmen(noGroup){
      $("#modal-view-asesmen").modal("show");
      var kat = $("#kat_asesmen").val();

      dwr.engine.setAsync(true);
      RawatInapAction.getKategoriSkorRanap(kat, function(kategori){
        $("#label-view-asesmen").html("");
        $("#label-view-asesmen").html(kategori.namaKategori);

        RawatInapAction.getListViewSkorRanapByGrupId(noGroup, function(response){
          if (response != null) {
            var str = "";
            var person = "";
            $.each(response, function(i,item){
              str += "<tr>"+
                    "<td>"+item.namaParameter+"</td>"+
                    "<td>"+item.keterangan+"</td>"+
                    "</tr>";

              person = "<p>Diinput oleh : "+item.createdWho+"</p>"+
                      "<p>Diinput pada : "+formateDate(item.stDate)+"</p>";
            });

            $("#head-view-asesmen").html(person);
            $("#body-view-asesmen").html(str);
          }
        });
      });
    }

    function viewSkor(noGroup){
      $("#modal-view-skor").modal("show");
      var kat = $("#kat_skor").val();

      dwr.engine.setAsync(true);
      RawatInapAction.getKategoriSkorRanap(kat, function(kategori){
        $("#label-view-skor").html("");
        $("#label-view-skor").html(kategori.namaKategori);

        RawatInapAction.getListViewSkorRanapByGrupId(noGroup, function(response){
          if (response != null) {
            var str = "";
            var person = "";
            $.each(response, function(i,item){
              str += "<tr>"+
                    "<td>"+item.namaParameter+"</td>"+
                    "<td>"+item.skor+"</td>"+
                    "<td>"+item.keterangan+"</td>"+
                    "</tr>";

              person = "<p>Diinput oleh : "+item.createdWho+"</p>"+
                      "<p>Diinput pada : "+formateDate(item.stDate)+"</p>";
            });

            $("#head-view-skor").html(person);
            $("#body-view-skor").html(str);
          }
        });
      });
    }

    function confirmSaveAllTindakan(){
        $('#modal-confirm-dialog').modal('show');
        $('#save_con').attr('onclick','saveAllTindakan()');
    }

    function saveAllTindakan(){
        var jenisPasien = $('#id_jenis_pasien').val();
        $('#modal-confirm-dialog').modal('hide');
        $('#save_all').hide();
        $('#load_all').show();
        dwr.engine.setAsync(true);
        CheckupDetailAction.saveApproveAllTindakanRawatJalan(idDetailCheckup, jenisPasien, {
            callback : function (response) {
                if(response.status == "success"){
                    $('#success_all').show().fadeOut(5000);
                    $('#msg_all_suc').text("Berhasil menyimpan semua tindakan...!");
                    $('#save_all').show();
                    $('#load_all').hide();
                    hitungStatusBiaya();
                }else{
                    $('#warning_all').show().fadeOut(5000);
                    $('#msg_all_war').text(response.message);
                    $('#save_all').show();
                    $('#load_all').hide();
                }
            }});
    }

    function showOtherInput(id){
      var nilai = $("#"+id+"").val();
      // console.log(id+" - "+nilai);
      if (nilai.toLowerCase() == "lain") {
        $("#ot_"+id+"").removeAttr('style');
      } else {
        $("#ot_"+id+"").hide();
        $("#ot_"+id+"").val("");
      }
    }

    function setObatPoli(select) {
        var idx = select.selectedIndex;
        var poli = select.options[idx].value;
        var idPel = poli.split('|')[0];
        var namePel = poli.split('|')[1];
        var option = "<option value=''>[Select One]</option>";

        if (poli != '') {
            ObatPoliAction.getSelectOptionObatByPoli(idPel, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idObat + "|" + item.namaObat + "|" + item.qtyBox + "|" + item.qtyLembar + "|" + item.qtyBiji + "|" + item.lembarPerBox + "|" + item.bijiPerLembar + "'>" + item.namaObat + "</option>";
                    });
                    $('#resep_nama_obat').html(option);
                }
            });
        } else {
            option = "";
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

                $('#resep_stok_box').val(qtyBox);
                $('#resep_stok_lembar').val(qtyLembar);
                $('#resep_stok_biji').val(qtyBiji);

                $('#resep_keterangan').val('');
                $('#resep_qty').val('');
                $('#resep_jenis_satuan').val('').trigger('change');
            }
        }
    }

    function showModalMonVitalSign(idDetail){
      $("#modal-vital-sign").modal("show");

      // alert("klik");
      dwr.engine.setAsync(true);
      RawatInapAction.getListMonVitalSign("", idDetail, "", function(response){
        console.log(response);
        var str = "";
        $.each(response, function(i, item) {
          str += "<tr>"+
                "<td>"+item.jam+"</td>"+
                "<td>"+item.nafas+"</td>"+
                "<td>"+item.nadi+"</td>"+
                "<td>"+item.suhu+"</td>"+
                "<td>"+item.tensi+"</td>"+
                "<td>"+item.bb+"</td>"+
                "<td>"+item.tb+"</td>"+
                "<td>"+item.createdWho+"</td>"+
                "<td>"+formateDate(item.stDate)+"</td>"+
                "</tr>";
        });

        $("#body-list-vital-sign").html(str);
      });

    }
    function addMonVitalSign(){
      $("#modal-add-vital-sign").modal("show");
    }
    function showGrafVitalSign(idDetail){
      $("#modal-graf-vital-sign").modal("show");

      var suhu = [], nadi = [], nafas = [], label = [];
      dwr.engine.setAsync(true);
      RawatInapAction.getListGraf(idDetailCheckup, "", function(response){
        console.log(response)
        $.each(response, function(i, item){
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

      $.plot('#line-chart', [line_data1, line_data2, line_data3], {
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
      $('#line-chart').bind('plothover', function (event, pos, item) {

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
    }

    function saveVitalSign(noCheckup, idDetail){
      // alert("klik");
      var jsonrq = [];
      jsonrq.push({
        'jam': $("#mvs_jam").val(),
        'nafas': $("#mvs_nafas").val(),
        'nadi': $("#mvs_nadi").val(),
        'suhu': $("#mvs_suhu").val(),
        'tensi': $("#mvs_tensi").val(),
        'tb': $("#mvs_tb").val(),
        'bb': $("#mvs_bb").val()
      });

      var jsonstr = JSON.stringify(jsonrq);
      console.log(jsonstr);
      dwr.engine.setAsync(true);
      RawatInapAction.saveMonVitalSign(noCheckup, idDetail, jsonstr, function(response){
        if (response.status == "success") {
          alert("success");
          $("#modal-add-vital-sign").modal("hide");

          RawatInapAction.getListMonVitalSign("", idDetail, "", function(response){
            console.log(response);
            var str = "";
            $.each(response, function(i, item) {
              str += "<tr>"+
                    "<td>"+item.jam+"</td>"+
                    "<td>"+item.nafas+"</td>"+
                    "<td>"+item.nadi+"</td>"+
                    "<td>"+item.suhu+"</td>"+
                    "<td>"+item.tensi+"</td>"+
                    "<td>"+item.bb+"</td>"+
                    "<td>"+item.tb+"</td>"+
                    "<td>"+item.createdWho+"</td>"+
                    "<td>"+formateDate(item.stDate)+"</td>"+
                    "</tr>";
            });

            $("#body-list-vital-sign").html("");
            $("#body-list-vital-sign").html(str);
          });
        } else {
          alert(response.msg);
        }
      });
    }

    function showModalCairan(idDetail){

      $("#modal-cairan").modal("show");

      dwr.engine.setAsync(true);
      RawatInapAction.getListMonCairan("", idDetail, "", function(response){
        // console.log(response);
        var str = "";
        $.each(response, function(i, item) {
          str += "<tr>"+
                "<td>"+formateDate(item.stDate)+"</td>"+
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
                "<td>"+item.createdWho+"</td>"+
                "</tr>";
        });

        $("#body-list-cairan").html(str);
      });
    }

    function addObCairan(){
      $("#modal-add-cairan").modal("show");
    }

    function saveObCairan(noCheckup, idDetail){
      var jsonrq = [];
      jsonrq.push({
        'macam': $("#mcr_macam").val(),
        'melalui': $("#mcr_melalui").val(),
        'jumlah': $("#mcr_jumlah").val(),
        'mulai': $("#mcr_mulai").val(),
        'selesai': $("#mcr_selesai").val(),
        'cek': $("#mcr_cek").val(),
        'sisa': $("#mcr_sisa").val(),
        'jam_ukur_buang': $("#mcr_buang").val(),
        'dari': $("#mcr_dari").val(),
        'balance': $("#mcr_balance").val(),
        'ket': $("#mcr_ket").val(),
      });

      var jsonstr = JSON.stringify(jsonrq);
      dwr.engine.setAsync(true);
      RawatInapAction.saveMonCairan(noCheckup, idDetail, jsonstr, function(response){
        if (response.status == "success") {
          alert("success");
          $("#modal-add-cairan").modal("hide");

          RawatInapAction.getListMonCairan("", idDetail, "", function(response){
            // console.log(response);
            var str = "";
            $.each(response, function(i, item) {
              str += "<tr>"+
                    "<td>"+formateDate(item.stDate)+"</td>"+
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
                    "<td>"+item.createdWho+"</td>"+
                    "</tr>";
            });

          $("#body-list-cairan").html("");
          $("#body-list-cairan").html(str);
          });
        } else {
          alert(response.msg);
        }
      });
    }

    function showModalPemberianObat(idDetail, kategori){

      // alert(kategori);

      $("#modal-pemberian").modal("show");
      $("#kat_pemberian").val(kategori);
      $("#label_kat_pemberian").html(kategori);
      dwr.engine.setAsync(true);
      RawatInapAction.getListMonPemberianObat("", idDetail, kategori, "",  function(response){
        // console.log(response);
        var strhead = "";
        var str = "";
        if (kategori == "parenteral") {

          $.each(response, function(i, item){
              str += "<tr>"+
                    "<td>"+item.namaObat+"</td>"+
                    "<td>"+item.caraPemberian+"</td>"+
                    "<td>"+item.dosis+"</td>"+
                    "<td>"+item.skinTes+"</td>"+
                    "<td>"+item.waktu+"</td>"+
                    "<td>"+item.keterangan+"</td>"+
                    "<td>"+item.createdWho+"</td>"+
                    "<td>"+formateDate(item.stDate)+"</td>"+
                    "</tr>";
          });

          strhead = "<tr>"+
                    "<td>Nama Obat</td>"+
                    "<td>Cara Pemberian</td>"+
                    "<td>Dosis</td>"+
                    "<td>Skin Tes</td>"+
                    "<td>Waktu</td>"+
                    "<td>Keterangan</td>"+
                    "<td>Created Who</td>"+
                    "<td>Created Date</td>"+
                    "</tr>";
        } else {

          $.each(response, function(i, item) {
            str += "<tr>"+
                  "<td>"+item.namaObat+"</td>"+
                  "<td>"+item.dosis+"</td>"+
                  "<td>"+item.waktu+"</td>"+
                  "<td>"+item.keterangan+"</td>"+
                  "<td>"+item.createdWho+"</td>"+
                  "<td>"+formateDate(item.stDate)+"</td>"+
                  "</tr>";
          });

          strhead = "<tr>"+
                    "<td>Nama Obat</td>"+
                    "<td>Dosis</td>"+
                    "<td>Waktu</td>"+
                    "<td>Keterangan</td>"+
                    "<td>Created Who</td>"+
                    "<td>Created Date</td>"+
                    "</tr>";;
        }

        $("#thead_pemberian").html(strhead);
        $("#body-list-pemberian").html("");
        $("#body-list-pemberian").html(str);
      });
    }


  function addPemberianObat(){
    var kat = $("#kat_pemberian").val();

    var str="";
    dwr.engine.setAsync(true);
    if (kat == "parenteral") {
      RawatInapAction.getListObatParenteral(idPoli, function(response){
        console.log(response);
        $.each(response, function(i, item) {
          str += "<option val=\'"+item.namaObat+"\'>"+item.namaObat+"</option>";
        });
        $("#select_obat_par").html(str);
      });
      $("#modal-add-pemberian-parenteral").modal("show");
    } else {
      RawatInapAction.getListObatNonParenteral(idDetailCheckup, "%",  function(response){
        console.log(response);
        $.each(response, function(i, item) {
          str += "<option val=\'"+item.namaObat+"\'>"+item.namaObat+"</option>";
        });
        $("#select_obat_nonpar").html(str);
      });
      $("#modal-add-pemberian-non-parenteral").modal("show");
    }
  }

  function savePemberianObat(noCheckup, idDetail){
    var kat = $("#kat_pemberian").val();

    var jsonrq = [];
    var ispar = false;
    if (kat == "parenteral") {
      ispar = true;
      jsonrq.push({
        'name': $("#select_obat_par").val(),
        'cara': $("#par_cara").val(),
        'dosis': $("#par_dosis").val(),
        'tes': $("#par_skintes").val(),
        'waktu': $("#select_waktu_par").val(),
        'ket': $("#par_keterangan").val(),
        'kat': kat
      });
    } else {
      jsonrq.push({
        'name': $("#select_obat_nonpar").val(),
        'cara': "",
        'dosis': $("#nonpar_dosis").val(),
        'tes': "",
        'waktu': $("#select_waktu_nonpar").val(),
        'ket': $("#nonpar_keterangan").val(),
        'kat': kat
      });
    }
    var jsonstr = JSON.stringify(jsonrq);
    dwr.engine.setAsync(true);
    RawatInapAction.saveMonPemberianObat(noCheckup, idDetail, jsonstr, function(response){
      if (response.status == "success") {
        alert(response.status);
        dwr.engine.setAsync(true);
        RawatInapAction.getListMonPemberianObat("", idDetail, kat, "",  function(response){
          var strhead = "";
          var str = "";
          if (ispar) {
            $("#modal-add-pemberian-parenteral").modal("hide");
            $.each(response, function(i, item){
                str += "<tr>"+
                      "<td>"+item.namaObat+"</td>"+
                      "<td>"+item.caraPemberian+"</td>"+
                      "<td>"+item.dosis+"</td>"+
                      "<td>"+item.skinTes+"</td>"+
                      "<td>"+item.waktu+"</td>"+
                      "<td>"+item.keterangan+"</td>"+
                      "<td>"+item.createdWho+"</td>"+
                      "<td>"+formateDate(item.stDate)+"</td>"+
                      "</tr>";
            });
          } else {
            $("#modal-add-pemberian-non-parenteral").modal("hide");
            $.each(response, function(i, item) {
              str += "<tr>"+
                    "<td>"+item.namaObat+"</td>"+
                    "<td>"+item.dosis+"</td>"+
                    "<td>"+item.waktu+"</td>"+
                    "<td>"+item.keterangan+"</td>"+
                    "<td>"+item.createdWho+"</td>"+
                    "<td>"+formateDate(item.stDate)+"</td>"+
                    "</tr>";
            });
          }
          $("#body-list-pemberian").html("");
          $("#body-list-pemberian").html(str);
        });
      } else {
        alert(response.msg);
      }
    });
  };


    function showFormEdukasi(){
        $("#modal-edukasi").modal("show");

        dwr.engine.setAsync(true);
        RawatInapAction.getListGroupSkorRanap(noCheckup, idDetailCheckup, "inap14", function(response){
            if(response.length > 0){
                var strhead = "";
                $.each(response, function(i, itemHeader){
                    strhead += "<tr>"+
                        "<td>"+itemHeader.namaKategori+"</td>"+
                        "<td>"+itemHeader.createdWho+"</td>"+
                        "<td>"+itemHeader.stDate+"</td>"+
                        "<td><button class='btn btn-primary' onclick=\"viewAsesmen('"+itemHeader.groupId+"')\">View</button></td>"+
                        "</tr>";
                });
            }

            RawatInapAction.getListGroupSkorRanap(noCheckup, idDetailCheckup, "inap15", function(bodyresponse){
                if(response.length > 0){
                    var strbody = "";
                    $.each(bodyresponse, function(i, itemBody){
                        strbody += "<tr>"+
                            "<td>"+itemBody.namaKategori+"</td>"+
                            "<td>"+itemBody.createdWho+"</td>"+
                            "<td>"+itemBody.stDate+"</td>"+
                            "<td><button class='btn btn-primary' onclick=\"viewAsesmen('"+itemBody.groupId+"')\">View</button></td>"+
                            "</tr>";
                    });
                }

                $("#list-body-header-edukasi").html(strhead);
                $("#list-body-edukasi").html(strbody);
            });
        });
    };

    function addFormEdukasi(kategori){

        $("#btn-save-edukasi").html("");
        $("#btn-save-edukasi").html("<button class='btn btn-success' onclick=\"saveFormEdukasi('"+kategori+"')\"></button>");
        dwr.engine.setAsync(true);
        RawatInapAction.getListParameterByKategori(noCheckup, idDetailCheckup, kategori, function(response){

            var str = "";
            if (response != null){
                var n = 0;
                $.each(response, function (i, item) {
                    n = i;
                    var upline = "";
                    if (item.namaParameter.length > 25) {
                        upline ="<div class='form-group'>" +
                            "<div class='row'>"+
                            "<div class='col-md-8'>"+
                            "<label>"+item.namaParameter+"</label>"+
                            "</div>"+
                            "<div class='col-md-4'>";
                    } else {
                        upline ="<div class='form-group'>" +
                            "<div class='row'>"+
                            "<div class='col-md-4'>"+
                            "<label>"+item.namaParameter+"</label>"+
                            "</div>"+
                            "<div class='col-md-8'>";
                    }

                    var opt = "";
                    RawatInapAction.getListSkorRanapByParam(item.idParameter, function(skors){

                        var up_select = "<select class='form-control' id='val_rsk_"+i+"' onchange=\"showOtherInput(this.id)\">";
                        // var other_text = "<input type='text' class='form-control' id='ot_val_rsk_"+i+"' style='display:none'/>";
                        if (skors.length > 0) {
                            $.each(skors, function(i, itemSkor){
                                opt += "<option value="+itemSkor.ketSkor+">"+itemSkor.namaSkor+"</option>";
                            });
                        } else {
                            if (item.type == "date") {
                                opt = "<input type='date' class='form-control' id='val_rsk_"+i+"'>";
                            } else if (item.type == "number"){
                                opt = "<input type='number' class='form-control' id='val_rsk_"+i+"'>";
                            } else {
                                opt = "<input type='text' class='form-control' id='val_rsk_"+i+"'>";
                            }
                        }


                        // console.log(skors);

                        var down_select = "</select>";
                        var downline = "<input type='hidden' id='id_rsk_"+i+"' value='"+item.idParameter+"'>"+
                            "<input type='hidden' id='name_rsk_"+i+"' value='"+item.namaParameter+"'>"+
                            "<input type='text' class='form-control' id='ot_val_rsk_"+i+"' style='display:none' placeholder='sebutkan ...'/>"+
                            "</div>" +
                            "</div>"+
                            "<hr style='color:#b0b0b0;'/>"+
                            "</div>";
                        // "<div class='box-header with-border' style='margin-bottom: 7px;'></div>";

                        if (skors.length > 0) {
                            str += upline+up_select+opt+down_select+downline;
                        } else {
                            str += upline+opt+downline;
                        }

                        $("#ind_edukasi").val(n);
                        $("#body_edukasi").html(str);
                    });
                });
            };
        });
    }

    function saveFormEdukasi(kategori){

        var jsonrq = [];
        var ind = $("#ind_edukasi").val();

        for (i = 0; i <= ind; i++){

            var id_rsk = $("#id_rsk_"+i+"").val();

            var nilai = "";
            if ($("#val_rsk_"+i+"").is("select")) {
                nilai = $("#val_rsk_"+i+" option:selected").text();
            } else {
                nilai = $("#val_rsk_"+i+"").val();
            }

            var name_rsk = $("#name_rsk_"+i+"").val();
            var ket_rsk = "";
            var val_rsk = "0";

            if (nilai.toLowerCase() == "lain") {
                ket_rsk = $("#ot_val_rsk_"+i+"").val();
            } else {
                ket_rsk = nilai;
            }

            jsonrq.push({'id':id_rsk, 'val':val_rsk, 'name':name_rsk, 'ket':ket_rsk});
        }
        var jsonstr = JSON.stringify(jsonrq);
        dwr.engine.setAsync(true);
        RawatInapAction.saveSkorRanapByKategori(noCheckup, idDetailCheckup, kategori, jsonstr, function(response){
            if (response.status == "success") {
                alert("sukses");

                RawatInapAction.getListGroupSkorRanap(noCheckup, idDetailCheckup, kategori, function(response){
                    if (response != null) {
                        var str = "";
                        $.each(response, function(i, item){
                            str += "<tr>"+
                                "<td>"+item.namaKategori+"</td>"+
                                "<td>"+item.createdWho+"</td>"+
                                "<td>"+formateDate(item.stDate)+"</td>"+
                                "<td><button class='btn btn-primary' onclick=\"viewAsesmen('"+item.groupId+"')\">View</button></td>"+
                                "</tr>";
                        });

                        if(kategori == "inap14"){
                            $("#list-body-header-edukasi").html("");
                            $("#list-body-header-edukasi").html(str);
                        } else {
                            $("#list-body-edukasi").html("");
                            $("#list-body-edukasi").html(str);
                        }

                        $("#modal-add-edukasi").modal("hide");
                    }
                    // console.log(response);
                });

            } else {
                alert(response.msg);
            }
        });

    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>
