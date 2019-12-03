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
                                <table class="table table-striped" style="margin-top: 11px">
                                    <s:hidden id="no_checkup" name="headerDetailCheckup.noCheckup"></s:hidden>
                                    <s:hidden id="id_palayanan" name="headerDetailCheckup.idPelayanan"></s:hidden>
                                    <s:hidden id="no_detail_checkup" name="headerDetailCheckup.idDetailCheckup"/>
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
                                            <table><s:label name="headerDetailCheckup.idDetailCheckup"></s:label></table>
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
                                <img border="2" class="pull-right" src="<s:url value="/pages/images/ktp-tes.jpg"/>"
                                     style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px;">
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
                    <div class="box-header with-border" id="pos_lab">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Order Lab</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(4)"><i class="fa fa-plus"></i> Tambah Lab
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
                                                      onchange="var warn =$('#war_kolom-2').is(':visible'); if (warn){$('#col_kolom-2').show().fadeOut(3000);$('#war_kolom-2').hide()}"
                                                      headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control select2"/>
                                        </div>
                                    </div>

                                <div id="kamar" style="display: none;">
                                    <div class="form-group">
                                        <label style="margin-top: 7px">Kelas</label>
                                        <s:action id="initComboKelas" namespace="/checkupdetail"
                                                  name="getListComboKelasRuangan_checkupdetail"/>
                                        <s:select onchange="var warn =$('#war_kolom-2').is(':visible'); if (warn){$('#col_kolom-2').show().fadeOut(3000);$('#war_kolom-2').hide()}; listSelectRuangan(this)"
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
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label style="margin-top: 7px">&nbsp;</label>
                                    <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_catatan"><i class="fa fa-times"></i>required</p>
                                    <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_catatan"><i class="fa fa-check"></i> correct</p>
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">&nbsp;</label>
                                    <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_kolom-2"><i class="fa fa-times"></i> required</p>
                                    <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_kolom-2"><i class="fa fa-check"></i> correct</p>
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">&nbsp;</label>
                                    <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_kolom-3"><i class="fa fa-times"></i> required</p>
                                    <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_kolom-3"><i class="fa fa-check"></i> correct</p>
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
                            <select id="dok_id_dokter" style="width: 100%" class="form-control select2" onchange="var warn =$('#war_dok').is(':visible'); if (warn){$('#cor_dok').show().fadeOut(3000);$('#war_dok').hide()}">
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_dok"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_dok"><i class="fa fa-check"></i> correct</p>
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
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_kategori"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_kategori"><i class="fa fa-check"></i> correct</p>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Tindakan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="tin_id_tindakan"
                                    onchange="var warn =$('#war_tindakan').is(':visible'); if (warn){$('#cor_tindakan').show().fadeOut(3000);$('#war_tindakan').hide()}">
                                <option value=''>[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_tindakan"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_tindakan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Perawat</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="tin_id_perawat"
                                    onchange="var warn =$('#war_perawat').is(':visible'); if (warn){$('#cor_perawat').show().fadeOut(3000);$('#war_perawat').hide()}">
                                <option value="">[select one]</option>
                                <option value="1">Angel</option>
                                <option value="2">Anya</option>
                                <option value="3">Ayu</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_perawat"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_perawat"><i class="fa fa-check"></i> correct</p>
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
                            <s:select cssStyle="margin-top: 7px; width: 100%" onchange="var warn =$('#war_diagnosa').is(':visible'); if (warn){$('#cor_diagnosa').show().fadeOut(3000);$('#war_diagnosa').hide()}"
                                      list="#initComboDiagnosa.listOfComboDiagnosa" id="nosa_id_diagnosa"
                                      name="headerDetailCheckup.idPelayanan" listKey="idDiagnosa"
                                      listValue="descOfDiagnosa"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_diagnosa"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_diagnosa"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3">Jenis Diagnosa</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="nosa_jenis_diagnosa"
                                    onchange="var warn =$('#war_jenis_diagnosa').is(':visible'); if (warn){$('#cor_jenis_diagnosa').show().fadeOut(3000);$('#war_jenis_diagnosa').hide()}">
                                <option value="">[select one]</option>
                                <option value="0">Diagnosa Awal</option>
                                <option value="1">Diagnosa Akhir</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_jenis_diagnosa"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_jenis_diagnosa"><i class="fa fa-check"></i> correct</p>
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
                            <s:select cssStyle="margin-top: 7px; width: 100%" onchange="var warn =$('#war_kategori_lab').is(':visible'); if (warn){$('#cor_kategori_lab').show().fadeOut(3000);$('#war_kategori_lab').hide()}; listSelectLab(this)"
                                      list="#comboLab.listOfKategoriLab" id="lab_kategori"
                                      listKey="idKategoriLab"
                                      listValue="namaKategori"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_kategori_lab"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_kategori_lab"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Lab</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="lab_lab" onchange="var warn =$('#war_lab').is(':visible'); if (warn){$('#cor_lab').show().fadeOut(3000);$('#war_lab').hide()}; listSelectParameter(this);">
                                <option value=''>[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_lab"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_lab"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Parameter</label>
                        <div class="col-md-7">
                            <select class="form-control select2" multiple style="margin-top: 7px; width: 100%" id="lab_parameter" onchange="var warn =$('#war_parameter').is(':visible'); if (warn){$('#cor_parameter').show().fadeOut(3000);$('#war_parameter').hide()}">
                                <option value=''>[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_parameter"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_parameter"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_lab"><i class="fa fa-arrow-right"></i> Save</button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_lab">
                    <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>
<!-- /.content-wrapper -->
<script type='text/javascript'>

    var idDetailCheckup = $('#no_detail_checkup').val();
    var idPoli          = $('#id_palayanan').val();

    $(document).ready(function () {
        $('#rawat_jalan').addClass('active');
        listDokter();
        listTindakan();
        listDiagnosa();
        listSelectDokter();
        listLab();
    });

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
        var idx     = id.selectedIndex;
        var idKtg   = id.options[idx].value;

        if (idKtg == "pindah") {
            $("#form-poli").attr('style', 'display:block');
            $("#kamar").attr('style', 'display:none');
            $("#form-selesai").hide();
        }
        if (idKtg == "rujuk") {
            $("#kamar").attr('style', 'display:block');
            $("#form-poli").attr('style', 'display:none');
            $("#form-selesai").hide();
        }
        if (idKtg == "selesai" || idKtg == "") {
            $("#kamar").attr('style', 'display:none');
            $("#form-poli").attr('style', 'display:none');
            $("#form-selesai").show();
        }
    }

    function listSelectRuangan(id){
        var idx     = id.selectedIndex;
        var idKelas = id.options[idx].value;
        var flag    = true;

        var option = "";
        CheckupDetailAction.listRuangan(idKelas, flag,function (response) {
            option = "<option value=''>[Select One]</option>";
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idRuangan + "'>" + item.noRuangan+"-"+item.namaRuangan + "</option>";
                });
            } else {
                option = option;
            }
        });

        $('#kamar_detail').html(option);
    }

    function saveKeterangan() {
        var idKtg       = $("#keterangan").val();
        var noCheckup   = $("#no_checkup").val();
        var poli        = "";
        var kelas       = "";
        var kamar       = "";
        var idDokter    = "";
        var ket_selesai = "";

        if (idKtg != '') {
            if (idKtg == "pindah") {
                poli = $("#poli_lain").val();
                idDokter = $("#list_dokter").val();
                if (poli != '' && idDokter != '') {
                    $('#save_ket').hide();
                    $('#load_ket').show();
                    dwr.engine.setAsync(true);
                    CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, function (response) {
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(5);
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
                    CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter,ket_selesai, function (response) {
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(5);
                        $('#save_ket').show();
                        $('#load_ket').hide();
                    });
                }
                else {
                    $('#warning_ket').show().fadeOut(5000);
                    if(kelas == ''){
                        $('#war_kolom-2').show();
                    }
                    if(kamar == ''){
                        $('#war_kolom-3').show();
                    }
                }
            }

            if(idKtg == "selesai"){
                ket_selesai = $('#ket_selesai').val();
                if(ket_selesai != ''){
                    $('#save_ket').hide();
                    $('#load_ket').show();
                    dwr.engine.setAsync(true);
                    CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, function (response) {
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(5);
                        $('#save_ket').show();
                        $('#load_ket').hide();
                    });
                }else{
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
            window.location.href = 'search_checkupdetail.action';
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
            $('#save_dokter').attr('onclick','saveDokter(\''+id+'\')').show();
            $('#modal-dokter').modal('show');

        } else if (select == 2) {
            $('#tin_id_ketgori_tindakan, #tin_id_tindakan, #tin_id_perawat').val('').trigger('change');
            $('#tin_qty').val('1');
            $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
            $('#save_tindakan').attr('onclick','saveTindakan(\''+id+'\')').show();
            $('#modal-tindakan').modal('show');

        } else if (select == 3) {
            $('#nosa_id_diagnosa, #nosa_jenis_diagnosa').val('').trigger('change');
            $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
            $('#save_diagnosa').attr('onclick','saveDiagnosa(\''+id+'\')').show();
            $('#modal-diagnosa').modal('show');

        } else if (select == 4) {
            $('#lab_kategori, #lab_lab').val('').trigger('change');
            $('#load_lab, #warning_lab, #war_kategori_lab, #war_lab, #war_parameter').hide();
            $('#save_lab').attr('onclick','saveLab(\''+id+'\')').show();
            $('#modal-lab').modal('show');
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
            if(id != ''){
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
            }else{
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
                            "<td align='center'>" + '<img border="0" onclick="editDokter(\''+item.idTeamDokter+'\',\''+item.idDokter+'\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
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

        var idKategori  = $('#tin_id_ketgori_tindakan').val();
        var idTindakan  = $('#tin_id_tindakan').val();
        var idDokter    = $('#tin_id_dokter').val();
        var idPerawat   = $('#tin_id_perawat').val();
        var qty         = $('#tin_qty').val();

        if (idDetailCheckup != '' && idTindakan != '' && idDokter != '' && idPerawat != '' && qty > 0 && idKategori != '') {

            $('#save_tindakan').hide();
            $('#load_tindakan').show();

            if(id != ''){
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
            }else {
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

        var table   = "";
        var data    = [];
        var trfTtl  = 0;
        TindakanRawatAction.listTindakanRawat(idDetailCheckup, function (response) {
            data = response;
            if (data != null) {
                $.each(data, function (i, item) {

                    var tanggal      = item.createdDate;
                    var dateFormat   = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                    var tarif        = "-";
                    var tarifTotal   = "-";
                    var trfTotal     = 0;
                    var qtyTotal     = 0;

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
                            "<td align='right'>" + "Rp. " + tarif+",-" + "</td>" +
                            "<td align='center'>" + item.qty + "</td>" +
                            "<td align='right'>" + "Rp. " + tarifTotal+",-" + "</td>" +
                            "<td align='center'>" + '<img border="0" onclick="editTindakan(\''+item.idTindakanRawat+'\',\''+item.idTindakan+'\',\''+item.idKategoriTindakan+'\',\''+item.idPerawat+'\',\''+item.qty+'\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
                            "</tr>";

                });
                table = table + "<tr>" +
                        "<td colspan='6'>Total</td>" +
                        "<td align='right'>" + "Rp. " + formatRupiah(trfTtl)+",-" + "</td>" +
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

            if(id != ''){
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
            }else{
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
                    var id          = "-";
                    var ket         = "-";
                    var jen         = "-";
                    var tanggal     = item.createdDate;
                    var dateFormat  = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));

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
                            "<td align='center'>" + '<img border="0" onclick="editDiagnosa(\''+item.idDiagnosaRawat+'\',\''+item.idDiagnosa+'\',\''+item.jenisDiagnosa+'\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
                            "</tr>"
                });
            }
        });

        $('#body_diagnosa').html(table);
    }

    function listSelectLab(select){
        var idx          = select.selectedIndex;
        var idKategori   = select.options[idx].value;

        var option = "<option value=''>[Select One]</option>";
        if(idKategori != ''){
            LabAction.listLab(idKategori, function(response){
                if (response != null){
                    $.each(response, function (i, item) {
                        option += "<option value='"+item.idLab+"'>" +item.namaLab+ "</option>";
                    });
                }else{
                    option = option;
                }
            });
        }else{
            option = option;
        }

        $('#lab_lab').html(option);
    }

    function listSelectParameter(select){
        var idx     = select.selectedIndex;
        var idLab   = select.options[idx].value;

        var option = "";
        if(idLab != ''){
            LabDetailAction.listLabDetail(idLab, function(response){
                if (response != null){
                    $.each(response, function (i, item) {
                        option += "<option value='"+item.idLabDetail+"'>" +item.namaDetailPeriksa+ "</option>";
                    });
                }else{
                    option = option;
                }
            });
        }else{
            option = option;
        }

        $('#lab_parameter').html(option);
    }

    function saveLab(id) {

        var idKategori  = $('#lab_kategori').val();
        var idLab       = $('#lab_lab').val();
        var idParameter = $('#lab_parameter').val();

        if (idDetailCheckup != '' && idKategori != '' && idLab != '' && idParameter) {

            $('#save_lab').hide();
            $('#load_lab').show();

            if(id != ''){
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
            }else{
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
                    var status      = "-";
                    var lab         = "-";
                    var tanggal     = item.createdDate;
                    var dateFormat  = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));

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
                            "<td align='center'>" + '<img border="0" onclick="editLab(\''+item.idPeriksaLab+'\',\''+item.idLab+'\',\''+item.idKategoriLab+'\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
                            "</tr>"
                });
            }
        });

        $('#body_lab').html(table);
    }

    function editDokter(id, idDokter){
        $('#load_dokter, #war_dok').hide();
        $('#save_dokter').attr('onclick','saveDokter(\''+id+'\')').show();
        $('#dok_id_dokter').val(idDokter).trigger('change');
        $('#modal-dokter').modal('show');
    }

    function editTindakan(id, idTindakan, idKategori, idPerawat, qty){
        $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
        $('#tin_id_ketgori_tindakan').val(idKategori).trigger('change');
        $('#tin_id_tindakan').val(idTindakan).trigger('change');
        $('#tin_id_perawat').val(idPerawat).trigger('change');
        $('#tin_qty').val(qty);
        $('#save_tindakan').attr('onclick','saveTindakan(\''+id+'\')').show();
        $('#modal-tindakan').modal('show');
    }

    function editDiagnosa(id, idDiagnosa, jenis){
        $('#load_diagnosa, #warning_diagnosa, #war_diagnosa, #war_jenis_diagnosa').hide();
        $('#save_diagnosa').attr('onclick','saveDiagnosa(\''+id+'\')').show();
        $('#nosa_id_diagnosa').val(idDiagnosa).trigger('change');
        $('#nosa_jenis_diagnosa').val(jenis).trigger('change');
        $('#modal-diagnosa').modal('show');
    }

    function editLab(id, idLab, idKategoriLab){
        $('#load_lab, #warning_lab, #war_kategori_lab, #war_lab, #war_parameter').hide();
        $('#save_lab').attr('onclick','saveLab(\''+id+'\')').show();
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

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>