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
                        <h3 class="box-title"><i class="fa fa-user"></i> Tinggi & Berat Badan</h3>
                    </div>
                    <div class="box-body">
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
                                        <div class="input-group-addon btn btn-success">
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
                                        <div class="input-group-addon btn btn-success">
                                            Kg
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>&nbsp</label>
                                    <div>
                                        <button style="width: 150px" id="save_penunjang" onclick="savePenunjangPasien()" class="btn btn-success"><i
                                                class="fa fa-check"></i>
                                            Save
                                        </button>
                                        <button style="display: none; cursor: no-drop" type="button" class="btn btn-success"
                                                id="load_penunjang">
                                            <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                            <%--<br>--%>

                        <%--</div>--%>
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
                                <td>Dokter</td>
                                <td>Perawat</td>
                                <td align="right">Tarif</td>
                                <td align="center">Qty</td>
                                <td align="right">Total</td>
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
                                onclick="showModal(4)"><i class="fa fa-plus"></i> Tambah Penunjang
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
                        <h3 class="box-title"><i class="fa fa-plus-square"></i> Obat Penunjang</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(5)"><i class="fa fa-plus"></i> Obat Penunjang
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
                        <h3 class="box-title"><i class="fa fa-navicon"></i> Keterangan</h3>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_ket">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            Silahkan cek kembali data inputan!
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label style="margin-top: 7px">Keterangan</label>
                                    <select class="form-control select2" id="keterangan" style="width: 100%"
                                            onchange="var warn =$('#war_catatan').is(':visible'); if (warn){$('#cor_catatan').show().fadeOut(3000);$('#war_catatan').hide()}; selectKeterangan(this)">
                                        <option value=''>[Select One]</option>
                                        <option value='selesai'>Selesai</option>
                                        <option value='pindah'>Pindah Poli Lain</option>
                                        <option value='rujuk'>Rujuk Rawat Inap</option>
                                    </select>
                                </div>
                                <div id="form-poli" style="display: none">
                                    <div class="form-group">
                                        <label style="margin-top: 7px">Poli</label>
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

                                    <div class="form-group">
                                        <label style="margin-top: 7px">Dokter</label>
                                        <select id="list_dokter" class="form-control select2" style="width: 100%"
                                                onchange="var warn =$('#war_kolom-3').is(':visible'); if (warn){$('#col_kolom-3').show().fadeOut(3000);$('#war_kolom-3').hide()}">
                                            <option value=''>[Select One]</option>
                                        </select>

                                    </div>
                                </div>

                                <div id="form-selesai" style="display: none">
                                    <div class="form-group">
                                        <label style="margin-top: 7px">Keterangan Selesai</label>
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

                                <div id="kamar" style="display: none;">
                                    <div class="form-group">
                                        <label style="margin-top: 7px">Kelas</label>
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
                                    <div class="form-group">
                                        <label style="margin-top: 7px">Kamar</label>
                                        <select class="form-control select2" id="kamar_detail" style="width: 100%"
                                                onchange="var warn =$('#war_kolom-3').is(':visible'); if (warn){$('#col_kolom-3').show().fadeOut(3000);$('#war_kolom-3').hide()}">
                                            <option value=''>[Select One]</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <button class="btn btn-success" onclick="saveKeterangan()"
                                            style="margin-top: 15px; width: 150px" id="save_ket"><i
                                            class="fa fa-arrow-right"></i> Save
                                    </button>
                                    <button style="display: none; cursor: no-drop; margin-top: 15px;" type="button"
                                            class="btn btn-success" id="load_ket"><i class="fa fa-spinner fa-spin"></i>
                                        Sedang Menyimpan...
                                    </button>
                                </div>
                            </div>
                            <%--<div class="col-md-2">--%>
                            <%--<div class="form-group">--%>
                            <%--<label style="margin-top: 7px">&nbsp;</label>--%>
                            <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_catatan"><i class="fa fa-times"></i>required</p>--%>
                            <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_catatan"><i class="fa fa-check"></i> correct</p>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                            <%--<label style="margin-top: 7px">&nbsp;</label>--%>
                            <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_kolom-2"><i class="fa fa-times"></i> required</p>--%>
                            <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_kolom-2"><i class="fa fa-check"></i> correct</p>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                            <%--<label style="margin-top: 7px">&nbsp;</label>--%>
                            <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_kolom-3"><i class="fa fa-times"></i> required</p>--%>
                            <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_kolom-3"><i class="fa fa-check"></i> correct</p>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <div class="col-md-4" id="form-cekup" style="display: none;">
                                <div class="form-group">
                                    <label style="margin-top: 7px">Tanggal Checkup Ulang</label>
                                    <div class="input-group date" style="margin-top: 7px">
                                        <div class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                        </div>
                                        <s:textfield id="tgl_cekup" cssClass="form-control datepicker"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">Catatan</label>
                                    <s:textarea cssClass="form-control" rows="5" id="cekup_ket"
                                                style="margin-top: 7px"></s:textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
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
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Perawat</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                    id="tin_id_perawat"
                                    onchange="var warn =$('#war_perawat').is(':visible'); if (warn){$('#cor_perawat').show().fadeOut(3000);$('#war_perawat').hide()}">
                                <option value="">[select one]</option>
                                <option value="1">Angel</option>
                                <option value="2">Anya</option>
                                <option value="3">Ayu</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_perawat">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_perawat"><i class="fa fa-check"></i> correct</p>
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
                    <%--<div class="form-group">--%>
                    <%--<label class="col-md-3" style="margin-top: 7px">Jenis Obat</label>--%>
                    <%--<div class="col-md-7">--%>
                    <%--<s:action id="initJenis" namespace="/jenisobat"--%>
                    <%--name="getListJenisObat_jenisobat"/>--%>
                    <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                    <%--list="#initJenis.listOfJenisObat" id="resep_jenis"--%>
                    <%--listKey="idJenisObat"--%>
                    <%--onchange="var warn =$('#war_rep_jenis').is(':visible'); if (warn){$('#cor_rep_jenis').show().fadeOut(3000);$('#war_rep_jenis').hide()}; listSelectObat(this)"--%>
                    <%--listValue="namaJenisObat"--%>
                    <%--headerKey="" headerValue="[Select one]"--%>
                    <%--cssClass="form-control select2"/>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-2">--%>
                    <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                    <%--id="war_rep_jenis"><i class="fa fa-times"></i> required</p>--%>
                    <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                    <%--id="cor_rep_jenis"><i class="fa fa-check"></i> correct</p>--%>
                    <%--</div>--%>
                    <%--</div>--%>
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
                            <%--<s:action id="initObat" namespace="/obat"--%>
                            <%--name="getListObat_obat"/>--%>
                            <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                            <%--list="#initObat.listOfObat" id="resep_nama_obat"--%>
                            <%--listKey="idObat + '|' + namaObat + '|' + qty"--%>
                            <%--onchange="var warn =$('#war_rep_obat').is(':visible'); if (warn){$('#cor_rep_obat').show().fadeOut(3000);$('#war_rep_obat').hide()}; setStokObatApotek(this)"--%>
                            <%--listValue="namaObat"--%>
                            <%--headerKey="" headerValue="[Select one]"--%>
                            <%--cssClass="form-control select2"/>--%>
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
                            <%--<select onchange="var warn =$('#war_rep_ket').is(':visible'); if (warn){$('#cor_rep_ket').show().fadeOut(3000);$('#war_rep_ket').hide()}"--%>
                            <%--class="form-control" id="resep_keterangan" style="margin-top: 7px">--%>
                            <%--<option value="">[Select One]</option>--%>
                            <%--<option value="2 x 1 /Hari">2 x 1 /Hari</option>--%>
                            <%--<option value="3 x 1 /Hari">3 x 1 /Hari</option>--%>
                            <%--</select>--%>
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
<div class="mask"></div>
<!-- /.content-wrapper -->
<script type='text/javascript'>

    var idDetailCheckup = $('#no_detail_checkup').val();
    var idPoli = $('#id_palayanan').val();
    var idPasien = $('#id_pasien').val();
    var noCheckup = $('#no_checkup').val();

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


    });

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
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editAlergi(\'' + item.idAlergi + '\',\'' + item.alergi + '\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
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

        if (idKtg == "pindah") {
            $("#form-poli").attr('style', 'display:block');
            $("#kamar").attr('style', 'display:none');
            $("#form-selesai").hide();
            $("#form-cekup").hide();
        }
        if (idKtg == "rujuk") {
            $("#kamar").attr('style', 'display:block');
            $("#form-poli").attr('style', 'display:none');
            $("#form-selesai").hide();
            $("#form-cekup").hide();
        }
        if (idKtg == "selesai" || idKtg == "") {
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

    function saveKeterangan() {
        var idKtg = $("#keterangan").val();
        var noCheckup = $("#no_checkup").val();
        var poli = "";
        var kelas = "";
        var kamar = "";
        var idDokter = "";
        var ket_selesai = "";
        var tgl_cekup = "";
        var ket_cekup = "";

        if (idKtg != '') {
            if (idKtg == "pindah") {
                poli = $("#poli_lain").val();
                idDokter = $("#list_dokter").val();
                if (poli != '' && idDokter != '') {
                    $('#save_ket').hide();
                    $('#load_ket').show();
                    dwr.engine.setAsync(true);
                    CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, function (response) {
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(6);
                        $('#save_ket').show();
                        $('#load_ket').hide();
                    });
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

                if (kelas != '' && kamar != '') {
                    $('#save_ket').hide();
                    $('#load_ket').show();
                    dwr.engine.setAsync(true);
                    CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, function (response) {
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(6);
                        $('#save_ket').show();
                        $('#load_ket').hide();
                    });
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
                    $('#save_ket').hide();
                    $('#load_ket').show();
                    dwr.engine.setAsync(true);
                    CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, tgl_cekup, ket_cekup, function (response) {
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(6);
                        $('#save_ket').show();
                        $('#load_ket').hide();
                    });
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
                } else {
                    option = option;
                }
            });
        } else {
            option = option;
        }

        $('#tin_id_tindakan').html(option);
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
//            $('#obat_jenis_obat').attr("onchange", "var warn =$('#war_jenis_obat').is(':visible'); if (warn){$('#cor_jenis_obat').show().fadeOut(3000);$('#war_jenis_obat').hide()}; listSelectObat(this);");
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
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDokter(\'' + item.idTeamDokter + '\',\'' + item.idDokter + '\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
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
        var idPerawat = $('#tin_id_perawat').val();
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
                            "<td>" + item.namaDokter + "</td>" +
                            "<td>" + item.idPerawat + "</td>" +
                            "<td align='right'>" + tarif + "</td>" +
                            "<td align='center'>" + item.qty + "</td>" +
                            "<td align='right'>" + tarifTotal + "</td>" +
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editTindakan(\'' + item.idTindakanRawat + '\',\'' + item.idTindakan + '\',\'' + item.idKategoriTindakan + '\',\'' + item.idPerawat + '\',\'' + item.qty + '\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
                            "</tr>";

                });
                table = table + "<tr>" +
                        "<td colspan='6'>Total</td>" +
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
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDiagnosa(\'' + item.idDiagnosaRawat + '\',\'' + item.idDiagnosa + '\',\'' + item.jenisDiagnosa + '\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
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
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editLab(\'' + item.idPeriksaLab + '\',\'' + item.idLab + '\',\'' + item.idKategoriLab + '\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
                            "</tr>"
                });
            }
        });

        $('#body_lab').html(table);
    }

    function saveObat(idInap) {

//        var idJenis = $('#obat_jenis_obat').val();
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
                    if (item.jenisSatuan != null) {
                        jenis = item.jenisSatuan;
                    }

                    table += "<tr>" +
                            "<td>" + dateFormat + "</td>" +
                            "<td>" + id + "</td>" +
                            "<td>" + obat + "</td>" +
                            "<td align='center'>" + qty + "</td>" +
                            "<td>" + jenis + "</td>" +
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editObat(\'' + item.idObatInap + '\',\'' + id + '\',\'' + qty + '\',\'' + jenis + '\',\'' + obat + '\',\'' + item.qtyBox + '\',\'' + item.qtyLembar + '\',\'' + item.qtyBiji + '\',\'' + item.lembarPerBox + '\',\'' + item.bijiPerLembar + '\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
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

//        if (idObat != '') {
//            ObatAction.getStokObat(idObat, function (response) {
//                if (response != null) {
//                    $.each(response, function (i, item) {
//                        if (item.idObat == idObat) {
//                            if (item.qty != null) {
//                                stok = item.qty;
//                            }
//                        }
//                    });
//                }
//            });
//        }
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

            console.log(jenisSatuan);
            console.log(obat);
            console.log(stok);
            console.log(lembarPerBox);
            console.log(bijiPerLembar);
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
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="detailResep(\'' + item.idApprovalObat + '\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' +
                            ' <img onclick="printResep(\'' + idResep + '\')" class="hvr-grow" src="<s:url value="/pages/images/print_flat.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' +
                            "</td>" +
                            "</tr>"
                });
            }
        });

        $('#body_resep').html(table);
    }

    function printResep(id) {

        window.open('printResepPasien_checkupdetail.action?id=' + noCheckup + '&idResep=' + id, '_blank');
//        window.location.href = 'printResepPasien_checkupdetail.action?id=' + noCheckup + '&idResep=' + id;
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
                            "<td align='center'>" + '<img border="0" id=' + idObat + ' class="hvr-grow" onclick="editObatResep(\'' + item.idTransaksiObatDetail + '\',\'' + idObat + '\',\'' + qty + '\',\'' + ket + '\',\'' + namaObat + '\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' +
                            '<img border="0" id=save' + idObat + ' class="hvr-grow" onclick="saveDetailResep(\'' + item.idTransaksiObatDetail + '\',\'' + idObat + '\',\'' + item.idApprovalObat + '\')" src="<s:url value="/pages/images/save_flat.png"/>" style="cursor: pointer; height: 25px; width: 25px; display: none">' + "</td>" +
                            "</tr>"
                });
            }
        });

        $('#body_detail_rep').html(table);
    }

    function editObatResep(id, idObat, qty, ket, namaObat) {

        if ($('#' + idObat).attr('src') == '/simrs/pages/images/edit-flat-new.png') {
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
            var url = '<s:url value="/pages/images/edit-flat-new.png"/>';
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


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>