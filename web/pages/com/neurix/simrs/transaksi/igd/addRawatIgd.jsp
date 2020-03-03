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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanResepAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RawatInapAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatPoliAction.js"/>'></script>
    <%--<script type='text/javascript' src='<s:url value="/pages/dist/js/rekammedic.js"/>'></script>--%>

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

    </script>
    <style>

        .btn{
            margin-top: 7px;
        }
        .form-control{
            margin-bottom: 7px;
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
            Rawat IGD Pasien
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
                                                    <s:label cssClass="label label-success" name="headerDetailCheckup.noSep"></s:label>
                                                </table>
                                            </td>
                                        </tr>
                                    </s:if>
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
                                    <tr>
                                        <td><b>Jenis Pasien</b></td>
                                        <td>
                                            <table>
                                                <s:label name="headerDetailCheckup.jenisPeriksaPasien"></s:label>
                                            </table>
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
                                <%--<img border="2" class="card card-4 pull-right" src="<s:url value="/pages/images/ktp-tes.jpg"/>"--%>
                                <%--style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px;">--%>
                                <table class="table table-striped">
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
                                        <td><b>Provinsi</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.provinsi"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kabupaten</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.kota"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kecamatan</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.kecamatan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Desa</b></td>
                                        <td>
                                            <table><s:label name="headerDetailCheckup.desa"></s:label></table>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td></td>
                                        <td>
                                            <button class="btn btn-primary" onclick="viewDetailRekamMedic('<s:property value="headerDetailCheckup.noCheckup"></s:property>')"><i class="fa fa-search"></i> View Rekam Medik Saat Ini</button>
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

                            </div>
                            <!-- /.col -->
                        </div>
                    </div>
                    <div class="box-header with-border" id="pos_alergi">
                    </div>

                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Asesmen</h3>
                    </div>

                    <div class="box-body">

                        <button class="btn btn-primary" onclick="showModalCheckFisik('<s:property value="headerDetailCheckup.noCheckup"/>')">
                            <i class="fa fa-edit"></i> Form Pemeriksaan Fisik
                        </button>
                        <button class="btn btn-primary" onclick="showModalPsikosial('<s:property value="headerDetailCheckup.noCheckup"/>')">
                            <i class="fa fa-edit"></i> Form Psikosial
                        </button>
                        <button class="btn btn-primary" onclick="showModalRencanaRawat('<s:property value="headerDetailCheckup.noCheckup"/>','<s:property value="headerDetailCheckup.idDetailCheckup"/>','rigd')">
                            <i class="fa fa-edit"></i> Form Rencana Keperawatan
                        </button>
                        <button class="btn btn-primary" onclick="showModalResikoJatuh('<s:property value="headerDetailCheckup.noCheckup"/>', '<s:property value="headerDetailCheckup.tglLahir"/>')">
                            <i class="fa fa-edit"></i> Form Resiko Jatuh
                        </button>
                        <button class="btn btn-primary" onclick="showModalRekonObat('<s:property value="headerDetailCheckup.noCheckup"/>', '<s:property value="headerDetailCheckup.tglLahir"/>')">
                            <i class="fa fa-edit"></i> Form Rekonsiliasi Obat
                        </button>
                        <button class="btn btn-primary" onclick="showModalTranfusi('<s:property value="headerDetailCheckup.noCheckup"/>')">
                            <i class="fa fa-edit"></i> Form Tranfusi
                        </button>
                        <button class="btn btn-primary" onclick="showModalPatrus('<s:property value="headerDetailCheckup.noCheckup"/>')">
                            <i class="fa fa-edit"></i> Form Patrus
                        </button>

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
                        <div class="row">
                            <div class="col-md-6">
                                <div class="box-body">
                                    <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                            onclick="showModal(8)"><i class="fa fa-plus"></i> Tambah Alergi
                                    </button>
                                    <table class="table table-bordered table-striped">
                                        <thead>
                                        <tr bgcolor="#90ee90">
                                            <td>Alergi</td>
                                            <td align="center">Action</td>
                                        </tr>
                                        </thead>
                                        <tbody id="body_alergi">
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col-md-6">
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
                            </div>
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
                                    <td align="right">Tarif (Rp.)</td>
                                    <td align="center">Qty</td>
                                    <td align="right">Total (Rp.)</td>
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
                                    onclick="showModal(4)"><i class="fa fa-plus"></i> Penunjang
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

                        <div class="box-header with-border" id="pos_obat">
                        </div>
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-plus-square"></i> Order Obat</h3>
                        </div>
                        <div class="box-body">
                            <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                    onclick="showModal(5)"><i class="fa fa-plus"></i> Order Obat
                            </button>
                            <table class="table table-bordered table-striped">
                                <thead>
                                <tr bgcolor="#90ee90">
                                    <td>Tanggal</td>
                                    <td>ID Obat</td>
                                    <td>Obat</td>
                                    <%--<td>Jenis Obat</td>--%>
                                    <td>Qty</td>
                                    <td align="center">Action</td>
                                </tr>
                                </thead>
                                <tbody id="body_obat">

                                </tbody>
                            </table>
                        </div>

                        <div class="box-header with-border" id="pos_rssep">
                        </div>
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-medkit"></i> Resep Obat</h3>
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
                                                    onchange="var warn =$('#war_catatan').is(':visible'); if (warn){$('#cor_catatan').show().fadeOut(3000);$('#war_catatan').hide()}; selectKeterangan(this)">
                                                <option value=''>[Select One]</option>
                                                <option value='selesai'>Selesai</option>
                                                <option value='pindah'>Pindah Poli Lain</option>
                                                <option value='rujuk'>Rujuk Rawat Inap</option>
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
                    </div>
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
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-3">Diagnosa</label>--%>
                        <%--<div class="col-md-7">--%>
                            <%--<s:action id="initComboDiagnosa" namespace="/checkupdetail"--%>
                                      <%--name="getListComboDiagnosa_checkupdetail"/>--%>
                            <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                      <%--onchange="var warn =$('#war_diagnosa').is(':visible'); if (warn){$('#cor_diagnosa').show().fadeOut(3000);$('#war_diagnosa').hide()}"--%>
                                      <%--list="#initComboDiagnosa.listOfComboDiagnosa" id="nosa_id_diagnosa"--%>
                                      <%--name="headerDetailCheckup.idPelayanan" listKey="idDiagnosa"--%>
                                      <%--listValue="descOfDiagnosa"--%>
                                      <%--headerKey="" headerValue="[Select one]"--%>
                                      <%--cssClass="form-control select2"/>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                            <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="war_diagnosa"><i class="fa fa-times"></i> required</p>--%>
                            <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="cor_diagnosa"><i class="fa fa-check"></i> correct</p>--%>
                        <%--</div>--%>
                        <s:if test='headerDetailCheckup.idJenisPeriksaPasien == "bpjs"'>
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Order Lab</h4>
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

<div class="modal fade" id="modal-obat">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Order Obat</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_obat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="obat_error"></p>
                </div>
                <div class="row">
                    <div class="form-group" id="jenis_form">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Obat</label>
                        <div class="col-md-7">
                            <s:action id="initJenis" namespace="/jenisobat"
                                      name="getListJenisObat_jenisobat"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#initJenis.listOfJenisObat" id="obat_jenis_obat"
                                      listKey="idJenisObat"
                                      listValue="namaJenisObat"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_jenis_obat"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_jenis_obat"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group" id="nama_form">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="ob_id_obat"
                                    onchange="var warn =$('#war_obat').is(':visible'); if (warn){$('#cor_obat').show().fadeOut(3000);$('#war_obat').hide()}; setStokObat(this);">
                                <option value="">[select one]</option>
                            </select>
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
                        <div class="col-md-7">
                            <s:textfield readonly="true" type="text" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="ob_stok"></s:textfield>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah</label>
                        <div class="col-md-7">
                            <s:textfield value="1" type="number" min="1" cssClass="form-control"
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

<div class="modal fade" id="modal-fisik">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Form Pemeriksaan Fisik Pasien</h4>
            </div>
            <div class="modal-body">

                <div class="row">
                    <div class="form-group">

                        <div class="col-md-2">
                            <label style="font-size: 11px;">Tinggi badan</label>
                            <input type="number" name="" id="tb" class="form-control" placeholder="cm">
                        </div>

                        <div class="col-md-2">
                            <label style="font-size: 11px;">Berat badan</label>
                            <input type="number" name="" id="bb" class="form-control" placeholder="Kg">
                        </div>

                        <div class="col-md-2">
                            <label style="font-size: 11px;">Nadi</label>
                            <input type="number" name="" id="nadi" class="form-control">
                        </div>

                        <div class="col-md-2">
                            <label style="font-size: 11px;">Resp rate</label>
                            <input type="number" name="" id="rr" class="form-control">
                        </div>

                        <div class="col-md-2">
                            <label style="font-size: 11px;">Tknn darah</label>
                            <input type="number" name="" id="td" class="form-control">
                        </div>

                        <div class="col-md-2">
                            <label style="font-size: 11px;">Suhu</label>
                            <input type="number" name="" id="suhu" class="form-control">
                        </div>
                    </div>
                </div>

                <hr>
                <div class="row">
                    <div class="form-group">

                        <div class="col-md-4">
                            <label>Kepala</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="" id="kepala" class="form-control">
                        </div>
                        <div class="col-md-4">
                            <label>Mata</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="" id="mata" class="form-control">
                        </div>

                        <div class="col-md-4">
                            <label>Leher/Spine</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="" id="leher" class="form-control">
                        </div>

                        <div class="col-md-4">
                            <label>Thorak</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="" id="thorak" class="form-control">
                        </div>

                        <div class="col-md-4">
                            <label>Thorak Cor</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="" id="thorakchor" class="form-control">
                        </div>

                        <div class="col-md-4">
                            <label>Thorak Pulmo</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="" id="thorakpulmo" class="form-control">
                        </div>

                        <div class="col-md-4">
                            <label>Abdomen</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="" id="abdomen" class="form-control">
                        </div>

                        <div class="col-md-4">
                            <label>Extrimitas</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="" id="extrimitas" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_fisik">
                    <h4><i class="icon fa fa-info"></i> Success!</h4>
                    <p>Data Berhasil Tersimpan</p>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_fisik">
                    <h4><i class="icon fa fa-ban"></i> Error !</h4>
                    <p id="error_ket_fisik"></p>
                </div>
            </div>

            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal" id="close_fisik"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_fisik" onclick="savePemeriksaanFisik('<s:property value="headerDetailCheckup.noCheckup"/>')"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_fisik"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-resiko-jatuh">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Resiko Jatuh</h4>
            </div>
            <div class="modal-body">
                <%--<div class="alert alert-danger alert-dismissible" style="display: none" id="warning_resiko">--%>
                    <%--<h4><i class="icon fa fa-ban"></i> Warning!</h4>--%>
                    <%--Silahkan cek kembali data inputan..!--%>
                <%--</div>--%>
                <%--<div class="alert alert-success alert-dismissible" style="display: none" id="success_resiko">--%>
                    <%--<h4><i class="icon fa fa-info"></i> Info!</h4>--%>
                    <%--Data berhasil diupdate..!--%>
                <%--</div>--%>
                    <input type="hidden" id="id-kat-jatuh" class="form form-control"/>
                    <div class="form-group">
                        <div class="row">
                                <label class="col-md-2" style="margin-top: 10px">Kategori</label>
                            <div class="col-md-4">
                                <input class="form-control" id="label-kat-resiko-jatuh" readonly>
                                <%--<span id="label-kat-resiko-jatuh"></span>--%>
                            </div>
                        </div>
                        <div class="box-header with-border"></div>
                        <div style="padding-top: 10px" class="row" id="body_resiko_jatuh">
                        </div>
                        <div class="box-header with-border"></div>
                        <div class="row" style="padding-top: 10px">
                            <label class="col-md-2" style="margin-top: 10px">Skor</label>
                            <div class="col-md-4">
                                <input class="form-control" id="sum-resiko-jatuh" readonly>
                                <%--<span id="sum-resiko-jatuh"></span>--%>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" id="ind_resiko_jatuh" class="form form-control"/>
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
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_resiko" onclick="saveResikoJatuh('<s:property value="headerDetailCheckup.noCheckup"/>')"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_resiko"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-rencana">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Rencana Awal Rawat <div id="label-rencana"> </div></h4>
            </div>
            <div class="modal-body">
                    <br>
                    <div class="form-group">
                        <div class="row" id="body_rencana">
                        </div>
                    </div>
                    <input type="hidden" id="ind_rencana" class="form form-control"/>
                    <br>
                    <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_rencana">
                        <h4><i class="icon fa fa-ban"></i> Success!</h4>
                        <p>Data Berhasil Tersimpan</p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_rencana">
                        <h4><i class="icon fa fa-ban"></i> Error !</h4>
                        <p id="error_ket_rencana"></p>
                    </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_rencana" onclick="saveRencanaRawat('<s:property value="headerDetailCheckup.noCheckup"/>', '<s:property value="headerDetailCheckup.idDetailCheckup"/>','rigd')"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_rencana"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-psikososial">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Form Data Psikososial</h4>
            </div>
            <div class="modal-body">
                <hr>
                <div class="row">
                    <div class="form-group">
                        <%--<input type="hidden" id="id_psikososial"/>--%>
                        <div class="col-md-4">
                            <label>Komunikasi</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="" id="komunikasi" class="form-control">
                        </div>
                        <div class="col-md-4">
                            <label>Kemampuan Bicara</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="" id="kemampuanBicara" class="form-control">
                        </div>

                        <div class="col-md-4">
                            <label>Konsep Diri</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="" id="konsepDiri" class="form-control">
                        </div>

                        <div class="col-md-4">
                            <label>Pernah Dirawat</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="" id="pernahDirawat" class="form-control">
                        </div>

                        <div class="col-md-4">
                            <label>Tahun Tentang Sakitnya</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="" id="tahuTentangSakitNya" class="form-control">
                        </div>

                        <div class="col-md-4">
                            <label>Obat Dari Rumah</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="" id="obatDariRumah" class="form-control">
                        </div>

                        <div class="col-md-4">
                            <label>Nyeri</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="" id="nyeri" class="form-control">
                        </div>

                        <div class="col-md-4">
                            <label>Intensitas Nyeri</label>
                        </div>
                        <div class="col-md-8">
                            <input type="number" name="" id="intensitasNyeri" max="10" min="0" class="form-control">
                            <%-- <input type="text" name="" id="intensitasNyeri" class="form-control"> --%>
                        </div>

                        <div class="col-md-4">
                            <label>Jenis Intensitas Nyeri</label>
                        </div>
                        <div class="col-md-8">
                            <input type="number" name="" id="jenisIntensitasNyeri" max="10" min="0" class="form-control">
                            <%-- <input type="text" name="" id="jenisIntensitasNyeri" class="form-control"> --%>
                        </div>

                        <div class="col-md-4">
                            <label>Numeric Rating Scale</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="" id="numericRatingScale" class="form-control">
                        </div>

                        <div class="col-md-4">
                            <label>Wong Baker Pain Scale</label>
                        </div>
                        <div class="col-md-8">
                            <input type="text" name="" id="wongBakerPainScale" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_psikososial">
                    <h4><i class="icon fa fa-ban"></i> Success!</h4>
                    <p>Data Berhasil Tersimpan</p>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_psikososial">
                    <h4><i class="icon fa fa-ban"></i> Error !</h4>
                    <p id="error_ket_psikososial"></p>
                </div>
            </div>

            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal" id="close_psikososial"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_psikososial" onclick="saveDataPsikososial('<s:property value="headerDetailCheckup.noCheckup"/>')"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_psikososial"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-rekonsiliasi">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Rekonsiliasi Obat <div id="label-rencana"> </div></h4>
            </div>
            <div class="modal-body">
                    <div class="form-group">
                        <div class="row">
                                <div class="col-md-5">
                                    <label>Nama obat</label>
                                </div>
                                <div class="col-md-6">
                                    <input type="text" name="" id="namaObatRekon" class="form-control">
                                </div>
                                <div class="col-md-5">
                                    <label>Bentuk Sediaan</label>
                                </div>
                                <div class="col-md-6">
                                    <input type="text" name="" id="bentukSediaanRekon" class="form-control">
                                </div>

                                <div class="col-md-5">
                                    <label>Dosis</label>
                                </div>
                                <div class="col-md-4">
                                    <input type="text" name="" id="dosisRekon" class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <select class="form-control" id="satuanRekon">
                                        <option value="mg">mg</option>
                                        <option value="mL">mL</option>
                                        <option value="mcg">mcg</option>
                                        <option value="unit">unit</option>
                                    </select>
                                </div>

                                <div class="col-md-5">
                                    <label>Frekuensi</label>
                                </div>
                                <div class="col-md-6">
                                    <input type="text" name="" id="frekuensiRekon" class="form-control">
                                </div>
                                <div class="col-md-5">
                                    <label>Rute</label>
                                </div>
                                <div class="col-md-6">
                                    <input type="text" name="" id="ruteRekon" class="form-control">
                                </div>
                                <div class="col-md-5">
                                    <label>Permintaan Obat yang Diberikan Saat Masuk</label>
                                </div>
                                <div class="col-md-2 col-md-offset-4">
                                    <select class="form-control" id="obatMasukRekon">
                                        <option value="Y">Ya</option>
                                        <option value="N">Tidak</option>
                                    </select>
                                </div>
                                <div class="col-md-5">
                                    <label>Obat dari Rumah dianjutkan saat pulang</label>
                                </div>
                                <div class="col-md-2 col-md-offset-4">
                                    <select class="form-control" id="obatRumahRekon">
                                        <option value="Y">Ya</option>
                                        <option value="N">Tidak</option>
                                    </select>
                                </div>
                                <div class="col-md-offset-5 col-md-6">
                                    <button type="button" style="float: right" class="btn btn-success" id="save_rekon" onclick="saveRekonObat('<s:property value="headerDetailCheckup.noCheckup"/>')"><i class="fa fa-arrow-right"></i> Save
                                    </button>
                                    <button style="display: none; cursor: no-drop; float: right" type="button" class="btn btn-success" id="load_rekon"><i
                                            class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                                    </button>
                                </div>
                        </div>
                    </div>
                    <br>
                    <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_rekon">
                        <h4><i class="icon fa fa-info"></i> Success!</h4>
                        <p>Data Berhasil Tersimpan</p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_rekon">
                        <h4><i class="icon fa fa-ban"></i> Error !</h4>
                        <p id="error_ket_rekon"></p>
                    </div>
                    <table class="table table-striped table-bordered" style="font-size: 11px">
                        <thead>
                        <td>Nama Obat</td>
                        <td>Bentuk sediaan</td>
                        <td>Dosis</td>
                        <td>Frekuensi</td>
                        <td>Rute</td>
                        <td>Permintaan Obat yang diberikan saat masuk</td>
                        <td>Obat dari rumah dilanjutkan saat pulang</td>
                        </thead>
                        <tbody id="body_rekon">
                        </tbody>
                    </table>
                    <%--<input type="hidden" id="ind_rencana" class="form form-control"/>--%>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-patrus">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Form Patrus </h4>
            </div>
            <div class="modal-body">
                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-4">
                                <label >Katerangan Patrus</label>
                            </div>
                            <div class="col-md-4">
                                <select class="form-control" id="inp_patrus">
                                    <option value="Lahir Hidup">Lahir Hidup</option>
                                    <option value="Lahir Mati">Lahir Mati</option>
                                    <option value="Lahir < 2500 gram">Lahir < 2500 gram</option>
                                    <option value="Abortus">Abortus</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <button type="button" style="margin-top: 1px" class="btn btn-success" id="save_patrus" onclick="saveDataPatrus('<s:property value="headerDetailCheckup.noCheckup"/>')"><i class="fa fa-arrow-right"></i>
                                    Add & Save
                                </button>
                                <button style="margin-top: 1px; display: none; cursor: no-drop; float: right" type="button" class="btn btn-success" id="load_patrus"><i
                                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                                </button>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_patrus">
                        <h4><i class="icon fa fa-info"></i> Success!</h4>
                        <p>Data Berhasil Tersimpan</p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_patrus">
                        <h4><i class="icon fa fa-ban"></i> Error !</h4>
                        <p id="error_ket_patrus"></p>
                    </div>
                    <table class="table table-striped table-bordered">
                        <thead>
                        <td>Keterangan Patrus</td>
                        </thead>
                        <tbody id="body_patrus">
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
<div class="modal fade" id="modal-tranfusi">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Form Tranfus </h4>
            </div>
            <div class="modal-body">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <select class="form-control" id="name_tranfusi">
                                        <option value="Whole Blood">Whole Blood</option>
                                        <option value="Packed Red Cells">Packed Red Cells</option>
                                        <option value="Liquid Plasma">Liquid Plasma</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <input type="number" class="form-control" id="cc_tranfusi" placeholder="CC"/>
                                </div>

                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <a type="button" style="margin-top: -1px" class="btn btn-success" id="save_tranfusi" onclick="saveTranfusi('<s:property value="headerDetailCheckup.noCheckup"/>')"><i class="fa fa-arrow-right"></i>
                                        Add & Save
                                    </a>
                                    <button style="margin-top: -1px; display: none; cursor: no-drop; float: right" type="button" class="btn btn-success" id="load_tranfusi"><i
                                            class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                                    </button>
                                </div>
                            </div>
                            </div>
                    <br>
                    <div class="alert alert-success alert-dismissible" style="display: none" id="success_save_tranfusi">
                        <h4><i class="icon fa fa-info"></i> Success!</h4>
                        <p>Data Berhasil Tersimpan</p>
                    </div>
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="error_save_tranfusi">
                        <h4><i class="icon fa fa-ban"></i> Error !</h4>
                        <p id="error_ket_tranfusi"></p>
                    </div>
                    <table class="table table-striped table-bordered">
                        <thead>
                        <td>Keterangan Tranfusi</td>
                        <td>CC</td>
                        <td width="20%">Tanggal</td>
                        </thead>
                        <tbody id="body_tranfusi">
                        </tbody>
                    </table>
                    <%--<input type="hidden" id="ind_rencana" class="form form-control"/>--%>
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
<!-- /.content-wrapper -->

<script type='text/javascript' src='<s:url value="/pages/dist/js/rekammedic.js"/>'></script>
<script type='text/javascript'>

    var idDetailCheckup = $('#no_detail_checkup').val();
    var idPoli = $('#id_palayanan').val();
    var idPasien = $('#id_pasien').val();
    var noCheckup = $('#no_checkup').val();

    $(document).ready(function () {
        $('#igd').addClass('active');
        listDokter();
        listTindakan();
        listDiagnosa();
        listSelectDokter();
        listLab();
        listObat();
        listResepPasien();
        listAlergi();

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

        var nominal = document.getElementById('uang_muka');
        nominal.addEventListener('keyup', function (e) {
            nominal.value = formatRupiah2(this.value);
            var valBayar = nominal.value.replace(/[.]/g, '');

            if(valBayar != ''){
                $('#uang_muka_val').val(valBayar);
            }else{
                $('#uang_muka_val').val('');
            }
        });

        hitungStatusBiaya();
    });

    function hitungStatusBiaya() {
        CheckupDetailAction.getStatusBiayaTindakan(idDetailCheckup, function (response) {
            console.log(response);
            if (response.idJenisPeriksaPasien == "bpjs") {
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
                        "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editAlergi(\'' + item.idAlergi + '\',\'' + item.alergi + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
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

    function selectKeterangan(id) {
        var idx = id.selectedIndex;
        var idKtg = id.options[idx].value;
        var jenisPasien = $('#jenis_pasien').val();

        if (idKtg == "pindah") {
            $('#pembayaran').hide();
            $("#form-poli").attr('style', 'display:block');
            $("#kamar").attr('style', 'display:none');
            $("#form-selesai").hide();
            $("#form-cekup").hide();
        }
        if (idKtg == "rujuk") {
            $('#pembayaran').hide();
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
        if (idKtg == "selesai" || idKtg == "") {
            $('#pembayaran').hide();
            $("#kamar").attr('style', 'display:none');
            $("#form-poli").attr('style', 'display:none');
            $("#form-selesai").show();
            $("#form-cekup").hide();
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
            } else {
                option = option;
            }
        });

        $('#kamar_detail').html(option);
    }

    function confirmSaveKeterangan(){
        var idKtg = $("#keterangan").val();
        var noCheckup = $("#no_checkup").val();
        var poli = "";
        var kelas = "";
        var kamar = "";
        var idDokter = "";
        var ket_selesai = "";
        var tgl_cekup = "";
        var ket_cekup = "";
        var jenisPasien = $('#jenis_pasien').val();
        var metodeBayar = "";
        var uangMuka = "";
        var idPasien = $('#id_pasien').val();

        if (idKtg != '') {
            if (idKtg == "pindah") {
                poli = $("#poli_lain").val();
                idDokter = $("#list_dokter").val();
                if (poli != '' && idDokter != '') {
                    $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\', \''+metodeBayar+'\', \''+uangMuka+'\')');
                    $('#modal-confirm-dialog').modal('show');
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
                kelas = $("#kelas_kamar").val();
                kamar = $("#kamar_detail").val();
                metodeBayar = $("#metode_bayar").val();
                uangMuka = $("#uang_muka_val").val();

                if (kelas != '' && kamar != '') {
                    $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\', \''+metodeBayar+'\', \''+uangMuka+'\')');
                    $('#modal-confirm-dialog').modal('show');
                }
                else {
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

                ket_selesai = $('#ket_selesai').val();
                tgl_cekup = $('#tgl_cekup').val();
                ket_cekup = $('#cekup_ket').val();

                if (ket_selesai != '') {
                    $('#save_con').attr('onclick','saveKeterangan(\''+idKtg+'\', \''+poli+'\', \''+kelas+'\', \''+kamar+'\', \''+ket_selesai+'\', \''+tgl_cekup+'\', \''+ket_cekup+'\', \''+jenisPasien+'\',\''+idPasien+'\', \''+metodeBayar+'\', \''+uangMuka+'\')');
                    $('#modal-confirm-dialog').modal('show');
                } else {
                    $('#warning_ket').show().fadeOut(5000);
                    $('#war_kolom-2').show();
                }
            }
        } else {
            $('#warning_ket').show().fadeOut(5000);
            $('#war_catatan').show();
        }
    }

    function saveKeterangan(idKtg, poli, kelas, kamar, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, idPasien, metodeBayar, uangMuka) {
        $('#modal-confirm-dialog').modal('hide');
        var idDokter = $('#tin_id_dokter').val();
        var jenisBayar = $('#jenis_bayar').val();

        if(idKtg == "pindah"){
            $('#save_ket').hide();
            $('#load_ket').show();
            dwr.engine.setAsync(true);
            CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, "", "", "", idPasien, metodeBayar, uangMuka, jenisBayar, function (response) {
                if(response == "success"){
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(6);
                    $('#save_ket').show();
                    $('#load_ket').hide();
                }else{
                    $('#save_ket').show();
                    $('#load_ket').hide();
                }
            });
        }
        if(idKtg == "rujuk"){
            $('#save_ket').hide();
            $('#load_ket').show();
            dwr.engine.setAsync(true);
            CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, "", "", "", idPasien, metodeBayar, uangMuka, jenisBayar, function (response) {
                $('#info_dialog').dialog('open');
                $('#close_pos').val(6);
                $('#save_ket').show();
                $('#load_ket').hide();
            });
        }
        if(idKtg == "selesai"){
            $('#save_ket').hide();
            $('#load_ket').show();
            dwr.engine.setAsync(true);
            CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, jenisPasien, "", "", "", idPasien, metodeBayar, uangMuka, jenisBayar, function (response) {
                $('#info_dialog').dialog('open');
                $('#close_pos').val(6);
                $('#save_ket').show();
                $('#load_ket').hide();
            });
        }
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
                    option = option;
                }
            });
        } else {
            option = option;
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
            desti = "#pos_obat";
        } else if (back == 6) {
            window.location.href = 'initForm_igd.action';
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
            $('#obat_jenis_obat').attr("onchange", "var warn =$('#war_jenis_obat').is(':visible'); if (warn){$('#cor_jenis_obat').show().fadeOut(3000);$('#war_jenis_obat').hide()}; listSelectObat(this);");
            $('#obat_jenis_obat, #ob_id_obat').val('').trigger('change');
            $('#jenis_form').show();
            $('#nama_form').show();
            $('#nama_obat_form').hide();
            $('#ob_stok').val('');
            $('#ob_qty').val('1');
            $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\')').show();
            $('#load_obat, #warning_obat, #war_jenis_obat, #war_obat, #war_qty_obat').hide();
            $('#modal-obat').modal('show');
        } else if (select == 7) {
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
        } else if (select == 8) {
            $('#load_alergi').hide();
            $('#save_alergi').attr('onclick', 'saveAlergi(\'' + id + '\')').show();
            $('#modal-alergi').modal('show');
        }

    }

    function formatRupiah(angka) {
        var reverse = angka.toString().split('').reverse().join(''),
            ribuan = reverse.match(/\d{1,3}/g);
        ribuan = ribuan.join('.').split('').reverse().join('');
        return ribuan;
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
                }
            });
        } else {
            option = "";
        }
        $('#resep_nama_obat').html(option);
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
                        // "<td>" + item.namaDokter + "</td>" +
                        // "<td>" + item.idPerawat + "</td>" +
                        "<td align='right'>"+ tarif +"</td>" +
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
            }
        });

        $('#body_tindakan').html(table);

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

        if (idDetailCheckup != '' && idKategori != '' && idLab != '' && idParameter) {

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

    function saveObat(id) {

        var idJenis = $('#obat_jenis_obat').val();
        var idObat = $('#ob_id_obat').val();
        var qty = $('#ob_qty').val();
        var stok = $('#ob_stok').val();

        if (id != '') {

            if (qty <= stok) {
                var obat = $('#set_id_obat').val();

                $('#save_obat').hide();
                $('#load_obat').show();

                dwr.engine.setAsync(true);
                ObatInapAction.editObatInap(id, idDetailCheckup, obat, qty, function (response) {
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
            if (idDetailCheckup != '' && idJenis != '' && idObat != '' && qty > 0) {

                if (qty <= stok) {
                    $('#save_obat').hide();
                    $('#load_obat').show();

                    dwr.engine.setAsync(true);
                    ObatInapAction.saveObatInap(idDetailCheckup, idObat, qty, function (response) {
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
                if (idJenis == '') {
                    $('#war_jenis_obat').show();
                }
                if (idObat == '') {
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
                    if (item.namaJenisObat != null) {
                        jenis = item.namaJenisObat;
                    }

                    table += "<tr>" +
                        "<td>" + dateFormat + "</td>" +
                        "<td>" + id + "</td>" +
                        "<td>" + obat + "</td>" +
                        //                            "<td>" + jenis + "</td>" +
                        "<td>" + qty + "</td>" +
                        "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editObat(\'' + item.idObatInap + '\',\'' + item.idObat + '\',\'' + item.qty + '\',\'' + item.stokMasterObat + '\',\'' + item.namaObat + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">' + "</td>" +
                        "</tr>"
                });
            }
        });

        $('#body_obat').html(table);
    }

    function setStokObat(select) {

        var idx = select.selectedIndex;
        var idObat = select.options[idx].value;
        var stok = "";

        if (idObat != '') {
            ObatAction.getStokObat(idObat, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        if (item.idObat == idObat) {
                            if (item.qty != null) {
                                stok = item.qty;
                            }
                        }
                    });
                }
            });
        }
        $('#ob_stok').val(stok);
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
    function editObat(id, idobat, qty, stok, namaObat) {
        $('#load_obat, #warning_obat, #war_jenis_obat, #war_obat, #war_qty_obat').hide();
        $('#jenis_form').hide();
        $('#nama_form').hide();
        $('#nama_obat_form').show();
        $('#nama_obat').val(namaObat);
        $('#ob_qty').val(qty);
        $('#ob_stok').val(stok);
        $('#set_id_obat').val(idobat);
        $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\')').show();
        $('#modal-obat').modal('show');
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

    function showFormCekup(select) {
        var idx = select.selectedIndex;
        var idKet = select.options[idx].value;
        if (idKet == "Checkup Ulang") {
            $('#form-cekup').show();
        } else {
            $('#form-cekup').hide();
        }
    }

    //    function showFormCekup(select) {
    //        var idx = select.selectedIndex;
    //        var idKet = select.options[idx].value;
    //        if (idKet == "Cekup Ulang") {
    //            $('#form-cekup').show();
    //        } else {
    //            $('#form-cekup').hide();
    //        }
    //    }

    <%--function addObatToList() {--%>
<%--//        var jenis = $('#resep_jenis').val();--%>
        <%--var obat = $('#resep_nama_obat').val();--%>
        <%--var ket = $('#resep_keterangan').val();--%>
        <%--var qty = $('#resep_qty').val();--%>
        <%--var cek = false;--%>
        <%--var id = obat.split('|')[0];--%>
        <%--var nama = obat.split('|')[1];--%>
        <%--var stokApotek = obat.split('|')[2];--%>
        <%--var data = $('#tabel_rese_detail').tableToJSON();--%>

        <%--if (obat != '' && ket != '' && qty != '') {--%>
            <%--$.each(data, function (i, item) {--%>
                <%--if (item.ID == id) {--%>
                    <%--cek = true;--%>
                <%--}--%>
            <%--});--%>

            <%--if (cek) {--%>
                <%--$('#warning_data_exits').show().fadeOut(5000);--%>
            <%--} else {--%>
                <%--var row = '<tr id=' + id + '>' +--%>
                    <%--'<td>' + id + '</td>' +--%>
                    <%--'<td>' + nama + '</td>' +--%>
                    <%--'<td>' + qty + '</td>' +--%>
                    <%--'<td>' + ket + '</td>' +--%>
                    <%--'<td align="center"><img border="0" onclick="delRowObat(\'' + id + '\')" class="hvr-grow" src="<s:url value="/pages/images/delete-flat.png"/>" style="cursor: pointer; height: 25px; width: 25px;"></td>' +--%>
                    <%--'</tr>';--%>
                <%--$('#body_detail').append(row);--%>
            <%--}--%>
        <%--} else {--%>
            <%--if (jenis == '') {--%>
                <%--$('#war_rep_jenis').show();--%>
            <%--}--%>
            <%--if (obat == '') {--%>
                <%--$('#war_rep_obat').show();--%>
            <%--}--%>
            <%--if (qty == '' || qty <= 0) {--%>
                <%--$('#war_rep_qty').show();--%>
            <%--}--%>
            <%--if (ket == '') {--%>
                <%--$('#war_rep_ket').show();--%>
            <%--}--%>
            <%--$('#warning_resep_head').show().fadeOut(5000);--%>
        <%--}--%>
    <%--}--%>

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

    function delRowObat(id) {
        $('#' + id).remove();
    }

    // function saveResepObat() {
    //
    //     var idDokter = $('#tin_id_dokter').val();
    //     var data = $('#tabel_rese_detail').tableToJSON();
    //     var stringData = JSON.stringify(data);
    //
    //     if (stringData != '[]') {
    //         $('#save_resep_head').hide();
    //         $('#load_resep_head').show();
    //         dwr.engine.setAsync(true);
    //         PermintaanResepAction.saveResepPasien(idDetailCheckup, idPoli, idDokter, idPasien, stringData, {
    //             callback: function (response) {
    //                 if (response == "success") {
    //                     dwr.engine.setAsync(false);
    //                     $('#info_dialog').dialog('open');
    //                     $('#close_pos').val(9);
    //                     $('#save_resep_head').show();
    //                     $('#load_resep_head').hide();
    //                     $('#modal-resep-head').modal('hide');
    //                     listResepPasien();
    //                 } else {
    //                     $('#warning_resep_head').show().fadeOut(5000);
    //                     $('#save_resep_head').show();
    //                     $('#load_resep_head').hide();
    //                 }
    //             }
    //         });
    //     } else {
    //         $('#warning_resep_head').show().fadeOut(5000);
    //     }
    // }

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
            $('#msg_resep').show();
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
                        "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="detailResep(\'' + item.idApprovalObat + '\')" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">'+
                        ' <img onclick="printResep(\'' + idResep + '\')" class="hvr-grow" src="<s:url value="/pages/images/icons8-print-25.png"/>" style="cursor: pointer;">' +
                        "</td>" +
                        "</tr>"
                });
            }
        });

        $('#body_resep').html(table);
    }

    function printResep(id) {
        window.open('printResepPasien_igd.action?id=' + noCheckup + '&idResep=' + id, '_blank');
    }

    function detailResep(id) {
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
                        '<img border="0" id=save' + idObat + ' class="hvr-grow" onclick="saveDetailResep(\'' + item.idTransaksiObatDetail + '\',\'' + idObat + '\',\'' + item.idApprovalObat + '\')" src="<s:url value="/pages/images/save_flat.png"/>" style="cursor: pointer; height: 25px; width: 25px; display: none">' + "</td>" +
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

    function getNamaObat() {
        ObatAction.getListNamaObat(function (response) {
            $.each(response, function (i, item) {

            })
        })
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

    function savePenunjangPasien() {

        var tinggi  = $('#tinggi').val();
        var berat   = $('#berat').val();

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
                } else {
                    $('#save_penunjang').show();
                    $('#load_penunjang').hide();
                }
            })
        } else {
            $('#warning_penunjang').show().fadeOut(5000);
        }
    }

    function showModalCheckFisik(noCheckup){

        $("#save_fisik").show();
        $("#load_fisik").hide();
        $("#close_fisik").show();
        $("#success_save_fisik").hide();
        $("#error_save_fisik").hide();
        $("#error_ket_fisik").html("");

        $("#modal-fisik").modal("show");

        dwr.engine.setAsync(true);
        CheckupAction.getPemeriksaanFisikByNoCheckup(noCheckup, function(response){
            if(response != null){

                var item = response;
                $("#tb").val(item.tinggiBadan)
                $("#bb").val(item.beratBadan)
                $("#nadi").val(item.nadi)
                $("#rr").val(item.respirationRate)
                $("#td").val(item.tekananDarah)
                $("#suhu").val(item.suhu)

                $("#kepala").val(item.kepala)
                $("#mata").val(item.mata)
                $("#leher").val(item.leher)
                $("#thorak").val(item.thorak)
                $("#thorakchor").val(item.thorakChor)
                $("#thorakpulmo").val(item.thorakPulmo)
                $("#abdomen").val(item.abdoman)
                $("#extrimitas").val(item.extrimitas)
//                console.log(response);
            }
        });

    }

    function savePemeriksaanFisik(noCheckup) {

//        alert(noCheckup);
        $("#save_fisik").hide();
        $("#load_fisik").show();
        $("#close_fisik").hide();

        var jsonrq = [];
        jsonrq.push({
            'nocheckup': noCheckup,
            'tb': $("#tb").val(),
            'bb': $("#bb").val(),
            'nadi': $("#nadi").val(),
            'rr': $("#rr").val(),
            'td': $("#td").val(),
            'suhu': $("#suhu").val(),
            'kepala': $("#kepala").val(),
            'mata': $("#mata").val(),
            'leher': $("#leher").val(),
            'thorak': $("#thorak").val(),
            'thorakchor': $("#thorakchor").val(),
            'thorakpulmo': $("#thorakpulmo").val(),
            'abdomen': $("#abdomen").val(),
            'extrimitas': $("#extrimitas").val()
        });

        var jsonstr = JSON.stringify(jsonrq);

        dwr.engine.setAsync(true);
        CheckupAction.savePemeriksaanFisik(jsonstr, function(response){
            if(response != null){
                if(response == "success"){
                    $("#close_fisik").show();
                    $("#load_fisik").hide();
                    $("#success_save_fisik").show();
                    $("#error_save_fisik").hide();
                } else {
                    $("#load_fisik").hide();
                    $("#close_fisik").show();
                    $("#success_save_fisik").hide();
                    $("#error_save_fisik").show();
                    $("#error_ket_fisik").html(response);
                }
            }
        });
    }

    function showModalResikoJatuh(noCheckup, tgl) {
        $("#modal-resiko-jatuh").modal("show");

        $("#error_ket_resiko").hide();
        $("#success_save_resiko").hide();
        $("#error_save_resiko").hide();
        $("#load_resiko").hide();
        $("#save_resiko").show();

        var str = "";
        dwr.engine.setAsync(true);
        CheckupAction.getKategoriResiko( tgl, function(kategori){

            $("#label-kat-resiko-jatuh").val(kategori.namaKategori);
            $("#id-kat-jatuh").val(kategori.idKategori);

            CheckupAction.getListResikoJatuh(noCheckup, tgl, function(response){
                if(response != null){
//                console.log(response)
                    if(response.status == "success"){

                        var n = 0;
                        $.each(response.resikoJatuhEntityList, function (i, item) {
                            n=i;

                            var topline = "";
                            topline = "<div class='col-md-6'>"+
                                "<label>"+item.namaParameter+"</label>"+
                                "</div>"+
                                "<div class='col-md-6'>"+
                                "<select class='form-control' id='val_rjh_"+i+"'>";

                            var skor = "";
                            CheckupAction.getListResikoJatuh(item.idParameter, function(skors){
                                $.each(skors, function (n, itemSkor) {
                                    if (itemSkor.skor == item.skor){
                                        skor += "<option value='"+itemSkor.skor+"' selected>"+itemSkor.namaSkor+"</option>";
                                    } else {
                                        skor += "<option value='"+itemSkor.skor+"'>"+itemSkor.namaSkor+"</option>";
                                    }
                                });


                                var bottomline = "";
                                bottomline = "</select>"+
                                    "</div>" +
                                    "<input type='hidden' id='id_rjh_"+i+"' value='"+item.idParameter+"'>" +
                                    "<input type='hidden' id='name_rjh_"+i+"' value='"+item.namaParameter+"'>" +
                                    "<input type='hidden' id='kat_rjh_"+i+"' value='"+item.idKategori+"'>";

                                str += topline+skor+bottomline;
                                console.log(str);
                                $("#body_resiko_jatuh").html(str);
                            });
                        });
                        $("#ind_resiko_jatuh").val(n);
                    }
                }

//                var idKategori = $("#id-kat-jatuh").val();
                CheckupAction.getSumResikoJatuh(noCheckup, kategori.idKategori, function(sum){
                    $("#sum-resiko-jatuh").val(sum);
                });

            });

        });


    }

    function showModalRencanaRawat(noCheckup, idDetailCheckup) {
        $("#modal-rencana").modal("show");

        $("#error_ket_rencana").hide();
        $("#success_save_rencana").hide();
        $("#error_save_rencana").hide();
        $("#load_rencana").hide();
        $("#save_rencana").show();

        var str = "";
        dwr.engine.setAsync(true);
        CheckupAction.getListRencanaRawat(noCheckup, idDetailCheckup, "rigd", function(response){

            if (response != null){
                var n = 0;
                $.each(response, function (i, item) {
                    n = i;

                    var upline ="<div class='form-group'>" +
                        "<div class='col-md-8'>"+
                        "<label>"+item.namaParameter+"</label>"+
                        "</div>"+
                        "<div class='col-md-4'>"+
                        "<select class='form-control' id='val_rcn_"+i+"'>";

                    var opt = "";

                    if (item.check == "Y"){
                        opt = "<option value=\"N\"> - </option>"+
                            "<option value=\"Y\" selected> Ya </option>";
                    } else {
                        opt = "<option value=\"N\" selected> - </option>"+
                            "<option value=\"Y\"> Ya </option>";
                    }


                    var downline = "</select>" +
                        "<input type='hidden' id='id_rcn_"+i+"' value='"+item.idParameter+"'>"+
                        "<input type='hidden' id='name_rcn_"+i+"' value='"+item.namaParameter+"'>"+
                        "</div>" +
                        "</div>";

                    str += upline+opt+downline;
                });

                $("#ind_rencana").val(n);
                $("#body_rencana").html(str);

//                console.log(str);
            }
        });
    }

    function saveRencanaRawat(noCheckup, idDetail, kategori) {

        var jsonrq = [];
        var ind = $("#ind_rencana").val();

        for (i = 0; i <= ind; i++){

            var id_rcn = $("#id_rcn_"+i+"").val();
            var val_rcn = $("#val_rcn_"+i+"").val();
            var name_rcn = $("#name_rcn_"+i+"").val();

            jsonrq.push({'id':id_rcn, 'val':val_rcn, 'name':name_rcn});
        }

        var jsonstr = JSON.stringify(jsonrq);
        $("#load_rencana").show();

        dwr.engine.setAsync(true);
        CheckupAction.saveRencanaRawat(noCheckup, idDetail, jsonstr, function(response){
            if (response.status == "success"){
                $("#error_ket_rencana").hide();
                $("#error_ket_rencana").val("");
                $("#success_save_rencana").show();
                $("#error_save_rencana").hide();
                $("#load_rencana").hide();
            } else {
                $("#error_ket_rencana").hide();
                $("#error_ket_rencana").val("");
                $("#success_save_rencana").show();
                $("#error_save_rencana").hide();
                $("#load_rencana").hide();
            }
        });
    }

    function saveResikoJatuh(noCheckup) {

        var jsonrq = [];
        var ind = $("#ind_resiko_jatuh").val();

        for (i = 0; i <= ind; i++){

            var id_rjh = $("#id_rjh_"+i+"").val();
            var val_rjh = $("#val_rjh_"+i+"").val();
            var name_rjh = $("#name_rjh_"+i+"").val();
            var kat_rjh = $("#kat_rjh_"+i+"").val();

            jsonrq.push({'id':id_rjh, 'val':val_rjh, 'name':name_rjh, 'kat':kat_rjh});
        }

        var jsonstr = JSON.stringify(jsonrq);
        $("#load_rencana").show();

        dwr.engine.setAsync(true);
        CheckupAction.saveResikoJatuh(noCheckup, jsonstr, function(response){
            if (response.status == "success"){
                $("#error_ket_resiko").hide();
                $("#error_ket_resiko").val("");
                $("#success_save_resiko").show();
                $("#error_save_resiko").hide();
                $("#load_resiko").hide();


            } else {
                $("#error_ket_resiko").hide();
                $("#error_ket_resiko").val(response.msg);
                $("#success_save_resiko").show();
                $("#error_save_resiko").hide();
                $("#load_resiko").hide();
            }
        });
    }

    function showModalPsikosial(noCheckup){
        $("#modal-psikososial").modal("show");

        dwr.engine.setAsync(true);
        CheckupAction.getPsikososial(noCheckup, function (response) {
            if(response != null){
                var item = response;
//                $("#id_psikososial").val(item.idPsikososial);
                $("#komunikasi").val(item.komunikasi);
                $("#kemampuanBicara").val(item.kemampuanBicara);
                $("#konsepDiri").val(item.konsepDiri);
                $("#pernahDirawat").val(item.pernahDirawat);
                $("#tahuTentangSakitNya").val(item.tahuTentangSakitNya);
                $("#obatDariRumah").val(item.obatDariRumah);
                $("#nyeri").val(item.nyeri);
                $("#intensitasNyeri").val(item.intensitasNyeri);
                $("#jenisIntensitasNyeri").val(item.jenisIntensitasNyeri);
                $("#numericRatingScale").val(item.numericRatingScale);
                $("#wongBakerPainScale").val(item.wongBakerPainScale);

            }
        });
    }


    function saveDataPsikososial(noCheckup){
        $("#load_psikososial").show();

        var jsonrq = [];
        jsonrq.push({
            'komunikasi': $("#komunikasi").val(),
            'kemampuanBicara': $("#kemampuanBicara").val(),
            'tahuTentangSakitNya': $("#tahuTentangSakitNya").val(),
            'konsepDiri':   $("#konsepDiri").val(),
            'pernahDirawat' : $("#pernahDirawat").val(),
            'obatDariRumah': $("#obatDariRumah").val(),
            'nyeri': $("#nyeri").val(),
            'intensitasNyeri': $("#intensitasNyeri").val(),
            'jenisIntensitasNyeri': $("#jenisIntensitasNyeri").val(),
            'numericRatingScale': $("#numericRatingScale").val(),
            'wongBakerPainScale': $("#wongBakerPainScale").val()
        });

        var jsonstr = JSON.stringify(jsonrq);
        dwr.engine.setAsync(true);
        CheckupAction.saveDataPsikososial(noCheckup, jsonstr, function (response) {
            if (response.status == "success") {
                $("#error_ket_psikososial").val("");
                $("#success_save_psikososial").show();
                $("#error_save_psikososial").hide();
                $("#load_psikososial").hide();
            } else {
                $("#error_ket_psikososial").val(response.msg);
                $("#success_save_psikososial").hide();
                $("#error_save_psikososial").show();
                $("#load_psikososial").hide();
            }
        });
    }

    function showModalRekonObat(noCheckup){
        $("#modal-rekonsiliasi").modal("show");
        $("#load_rekon").hide();
        $("#save_rekon").show();
        $("#error_ket_rekon").val("");
        $("#success_save_rekon").hide();
        $("#error_save_rekon").hide();

        $("#namaObatRekon").val("");
        $("#bentukSediaanRekon").val("");
        $("#satuanRekon").val("");
        $("#dosisRekon").val("");
        $("#frekuensiRekon").val("");
        $("#ruteRekon").val("");
        $("#obatMasukRekon").val("");
        $("#obatRumahRekon").val("");

        dwr.engine.setAsync(true);
        CheckupAction.getListRekonsiliasiObat(noCheckup, function (response) {
            if (response != null) {
                // console.log(response);
                var str = "";
                $.each(response, function(i,item){
                    str += "<tr>"+
                        "<td>"+item.namaObat+"</td>"+
                        "<td>"+item.bentuk+"</td>"+
                        "<td>"+item.dosis+" "+item.satuanDosis+"</td>"+
                        "<td>"+item.frekuensi+"</td>"+
                        "<td>"+item.rute+"</td>"+
                        "<td>"+item.obatMasukFlag+"</td>"+
                        "<td>"+item.obatDariRumahFlag+"</td>"+
                        "</tr>";
                });
                $("#body_rekon").html(str);
            }
        });
    }

    function saveRekonObat(noCheckup){
        $("#load_rekon").show();
        $("#save_rekon").hide();

        var jsonrq = [];
        jsonrq.push({
            'nama': $("#namaObatRekon").val(),
            'bentuk': $("#bentukSediaanRekon").val(),
            'satuan': $("#satuanRekon").val(),
            'dosis': $("#dosisRekon").val(),
            'frekuensi': $("#frekuensiRekon").val(),
            'rute': $("#ruteRekon").val(),
            'obatmasuk': $("#obatMasukRekon").val(),
            'obatrumah': $("#obatRumahRekon").val()
        });

        var jsonstr = JSON.stringify(jsonrq);
        dwr.engine.setAsync(true);
        CheckupAction.saveRekonsilisasiObat(noCheckup, jsonstr, function (response) {
            if (response.status == "success") {
                $("#error_ket_rekon").val("");
                $("#success_save_rekon").show();
                $("#error_save_rekon").hide();
                $("#load_rekon").hide();
                $("#save_rekon").show();


                $("#namaObatRekon").val("");
                $("#bentukSediaanRekon").val("");
                $("#satuanRekon").val("");
                $("#dosisRekon").val("");
                $("#frekuensiRekon").val("");
                $("#ruteRekon").val("");
                $("#obatMasukRekon").val("");
                $("#obatRumahRekon").val("");

                CheckupAction.getListRekonsiliasiObat(noCheckup, function (response) {
                    if (response != null) {
                        // console.log(response);
                        var str = "";
                        $.each(response, function(i,item){
                            str += "<tr>"+
                                "<td>"+item.namaObat+"</td>"+
                                "<td>"+item.bentuk+"</td>"+
                                "<td>"+item.dosis+" "+item.satuanDosis+"</td>"+
                                "<td>"+item.frekuensi+"</td>"+
                                "<td>"+item.rute+"</td>"+
                                "<td>"+item.obatMasukFlag+"</td>"+
                                "<td>"+item.obatDariRumahFlag+"</td>"+
                                "</tr>";
                        });
                        $("#body_rekon").html("");
                        $("#body_rekon").html(str);
                    }
                });

            } else {
                $("#error_ket_rekon").val(response.msg);
                $("#success_save_rekon").hide();
                $("#error_save_rekon").show();
                $("#load_rekon").hide();
            }
        });
    }

    function showModalPatrus(noCheckup){
        $("#modal-patrus").modal("show");
        $("#error_ket_patrus").html("");
        $("#success_save_patrus").hide();
        $("#error_save_patrus").hide();
        $("#load_patrus").hide();
        $("#save_patrus").show();

        dwr.engine.setAsync(true);
        CheckupAction.getListPatrus(noCheckup, function (response) {
            if(response != null){
                var str = "";
                $.each(response, function(i,item){
                    str += "<tr>"+
                        "<td>"+item.ketPatrus+"</td>"+
                        "</tr>";
                });

                $("#body_patrus").html(str);
            }
        });
    }

    function saveDataPatrus(noCheckup){
        $("#load_patrus").show();
        $("#save_patrus").hide();

        var ket = $("#inp_patrus").val();
        dwr.engine.setAsync(true);
        CheckupAction.savePatrus(noCheckup, ket, function (response) {
            if (response.status == "success") {

                $("#error_ket_patrus").html("");
                $("#success_save_patrus").show();
                $("#error_save_patrus").hide();
                $("#load_patrus").hide();
                $("#save_patrus").show();

                CheckupAction.getListPatrus(noCheckup, function (listResponse) {
                    if(listResponse != null){
                        var str = "";
                        $.each(listResponse, function(i,item){
                            str += "<tr>"+
                                "<td>"+item.ketPatrus+"</td>"+
                                "</tr>";
                        });

                        $("#body_patrus").html("");
                        $("#body_patrus").html(str);
                    }
                });
            } else {
                $("#error_ket_patrus").html(response.msg);
                $("#success_save_patrus").hide();
                $("#error_save_patrus").show();
                $("#load_patrus").hide();
                $("#save_patrus").show();
            }

        });
    }

    function showModalTranfusi(noCheckup){
        $("#modal-tranfusi").modal("show");
        $("#error_ket_tranfusi").html("");
        $("#success_save_tranfusi").hide();
        $("#error_save_tranfusi").hide();
        $("#load_tranfusi").hide();
        $("#save_tranfusi").show();

        dwr.engine.setAsync(true);
        CheckupAction.getListTranfusi(noCheckup, function (response) {
            if (response != null) {
                // console.log(response);
                var str = "";
                $.each(response, function(i,item){
                    str += "<tr>"+
                        "<td>"+item.ketTransfusi+"</td>"+
                        "<td>"+item.cc+"</td>"+
                        "<td>"+formateDate(item.createdDate)+"</td>"+
                        "</tr>";
                });
                $("#body_tranfusi").html(str);
            }
        });
    }

    function saveTranfusi(noCheckup){
        $("#load_tranfusi").show();
        $("#save_tranfusi").hide();

        var ket = $("#name_tranfusi").val();
        var cc = $("#cc_tranfusi").val();

        dwr.engine.setAsync(true);
        CheckupAction.saveTranfusi(noCheckup, ket, cc, function (response) {
            if (response.status == "success") {

                $("#error_ket_tranfusi").html("");
                $("#success_save_tranfusi").show();
                $("#error_save_tranfusi").hide();
                $("#load_tranfusi").hide();
                $("#save_tranfusi").show();

                CheckupAction.getListTranfusi(noCheckup, function (listResponse) {
                    if (listResponse != null) {
                        // console.log(response);
                        var str = "";
                        $.each(listResponse, function(i,item){
                            str += "<tr>"+
                                "<td>"+item.ketTransfusi+"</td>"+
                                "<td>"+item.cc+"</td>"+
                                "<td>"+item.createdDate+"</td>"+
                                "</tr>";
                        });
                        $("#body_tranfusi").html("");
                        $("#body_tranfusi").html(str);
                    }
                });
            } else {
                $("#error_ket_tranfusi").html(response.msg);
                $("#success_save_tranfusi").hide();
                $("#error_save_tranfusi").show();
                $("#load_tranfusi").hide();
                $("#save_tranfusi").show();
            }
        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>
