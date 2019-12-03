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
                                <table class="table table-striped" style="margin-top: 11px">
                                    <s:hidden id="no_checkup" name="rawatInap.noCheckup"/>
                                    <s:hidden id="id_palayanan" name="rawatInap.idPelayanan"/>
                                    <s:hidden id="no_detail_checkup" name="rawatInap.idDetailCheckup"/>
                                    <s:hidden id="id_rawat_inap" name="rawatInap.idRawatInap"/>
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
                                        <td><b>Jenis Pasien</b></td>
                                        <td>
                                            <table>
                                                <s:label name="rawatInap.jenisPeriksaPasien"></s:label>
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
                                                <s:label name="rawatInap.namaPelayanan"></s:label></table>
                                        </td>
                                    </tr>
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
                                            <table><label class="label label-success"> <span id="no_ruang"></span> - <span id="name_ruang"></span></label></table>
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
                                <td rowspan="2">Tanggal</td>
                                <td colspan="2" align="center">Pagi</td>
                                <td colspan="2" align="center">Siang</td>
                                <td colspan="2" align="center">Malam</td>
                                <td align="center" rowspan="2">Action</td>
                            </tr>
                            <tr bgcolor="#90ee90">
                                <td>Jenis</td>
                                <td>Bentuk</td>
                                <td>Jenis</td>
                                <td>Bentuk</td>
                                <td>Jenisi</td>
                                <td>Bentuk</td>
                            </tr>
                            </thead>
                            <tbody id="body_diet">

                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border" id="pos_obat">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-stethoscope"></i> Order Obat</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(6)"><i class="fa fa-plus"></i> Order Obat
                        </button>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Tanggal</td>
                                <td>ID Obat</td>
                                <td>Obat</td>
                                <td>Jenis Obat</td>
                                <td>Qty</td>
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
                        <h3 class="box-title"><i class="fa fa-stethoscope"></i> Ruangan</h3>
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
                                    <label style="margin-top: 7px; margin-bottom: -2px;">Keterangan Selesai</label>
                                    <s:action id="initComboKet" namespace="/checkupdetail"
                                              name="getListComboKeteranganKeluar_checkupdetail"/>
                                    <s:select list="#initComboKet.listOfKeterangan" id="ket_selesai"
                                              name="headerCheckup.idPelayanan" listKey="keterangan"
                                              listValue="keterangan" cssStyle="width: 100%"
                                              onchange="var warn =$('#war_catatan').is(':visible'); if (warn){$('#cor_catatan').show().fadeOut(3000);$('#war_catatan').hide()}"
                                              headerKey="" headerValue="[Select one]"
                                              cssClass="form-control select2"/>
                                </div>
                                <div class="form-group">
                                    <button class="btn btn-success" onclick="saveKeterangan()"
                                            style="margin-top: 15px;" id="save_ket"><i
                                            class="fa fa-arrow-right"></i> Save
                                    </button>
                                    <button style="display: none; cursor: no-drop; margin-top: 15px;" type="button"
                                            class="btn btn-success" id="load_ket"><i class="fa fa-spinner fa-spin"></i>
                                        Sedang Menyimpan...
                                    </button>
                                    <a href="initForm_rawatinap.action" class="btn btn-warning"
                                       style="margin-top: 15px;"><i
                                            class="fa fa-arrow-left"></i> Back
                                    </a>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label style="margin-top: 7px">&nbsp;</label>
                                    <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                       id="war_catatan"><i class="fa fa-times"></i>required</p>
                                    <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                       id="cor_catatan"><i class="fa fa-check"></i> correct</p>
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
                        <label class="col-md-3">Jenis Diet Pagi</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="diet_pagi"
                                   oninput="var warn =$('#war_pagi1').is(':visible'); if (warn){$('#cor_pagi1').show().fadeOut(3000);$('#war_pagi1').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_pagi1"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_pagi1">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Bentuk Diet Pagi</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" style="margin-top: 7px" id="bentuk_pagi"
                                   oninput="var warn =$('#war_pagi2').is(':visible'); if (warn){$('#cor_pagi2').show().fadeOut(3000);$('#war_pagi2').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_pagi2"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_pagi2">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Diet Siang</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" style="margin-top: 7px" id="diet_siang"
                                   oninput="var warn =$('#war_siang1').is(':visible'); if (warn){$('#cor_siang1').show().fadeOut(3000);$('#war_siang1').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_siang1">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_siang1"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Bentuk Diet Siang</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" style="margin-top: 7px" id="bentuk_siang"
                                   oninput="var warn =$('#war_siang2').is(':visible'); if (warn){$('#cor_siang2').show().fadeOut(3000);$('#war_siang2').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_siang2">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_siang2"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Diet Malam</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" style="margin-top: 7px" id="diet_malam"
                                   oninput="var warn =$('#war_malam1').is(':visible'); if (warn){$('#cor_malam1').show().fadeOut(3000);$('#war_malam1').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_malam1">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_malam1"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Bentuk Diet Malam</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" style="margin-top: 7px" id="bentuk_malam"
                                   oninput="var warn =$('#war_malam2').is(':visible'); if (warn){$('#cor_malam2').show().fadeOut(3000);$('#war_malam2').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_malam2">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_malam2"><i class="fa fa-check"></i> correct</p>
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
                    Silahkan cek kembali data inputan!
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Obat</label>
                        <div class="col-md-7">
                            <s:action id="initJenis" namespace="/jenisobat"
                                      name="getListJenisObat_jenisobat"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      onchange="var warn =$('#war_jenis_obat').is(':visible'); if (warn){$('#cor_jenis_obat').show().fadeOut(3000);$('#war_jenis_obat').hide()}; listSelectObat(this);"
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
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%" id="ob_id_obat"
                                    onchange="var warn =$('#war_obat').is(':visible'); if (warn){$('#cor_obat').show().fadeOut(3000);$('#war_obat').hide()}">
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

<script type='text/javascript'>

    var idDetailCheckup = $('#no_detail_checkup').val();
    var idPoli = $('#id_palayanan').val();
    var idRawatInap = $('#id_rawat_inap').val();

    $(document).ready(function () {
        $('#rawat_inap').addClass('active');
        listDokter();
        listTindakan();
        listDiagnosa();
        listSelectDokter();
        listLab();
        listObat();
        listDiet();
        listRuanganInap();
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

    function saveKeterangan() {

        var idKtg = "selesai";
        var noCheckup = $("#no_checkup").val();
        var poli = "";
        var kelas = "";
        var kamar = "";
        var idDokter = "";
        var ket_selesai = $('#ket_selesai').val();

        if (idKtg != '' && ket_selesai != '') {
            $('#save_ket').hide();
            $('#load_ket').show();
            dwr.engine.setAsync(true);
            CheckupDetailAction.saveKeterangan(noCheckup, idDetailCheckup, idKtg, poli, kelas, kamar, idDokter, ket_selesai, function (response) {
                $('#info_dialog').dialog('open');
                $('#close_pos').val(8);
                $('#save_ket').show();
                $('#load_ket').hide();
            });
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
            desti = "#pos_diet";
        } else if (back == 6) {
            desti = "#pos_obat";
        } else if (back == 7) {
            desti = "#pos_ruangan";
        } else if (back == 8) {
            window.location.href = 'search_rawatinap.action';
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
            $('#diet_pagi, #bentuk_pagi, #diet_siang, #bentuk_siang, #diet_malam, #bentuk_malam').val('');
            $('#save_diet').attr('onclick', 'saveDiet(\'' + id + '\')').show();
            $('#load_diet, #warning_diet, #war_pagi1, #war_pagi2, #war_siang1, #war_siang2, #war_malam1, #war_malam2').hide();
            $('#modal-diet').modal('show');
        } else if (select == 6) {
            $('#obat_jenis_obat, #ob_id_obat').val('').trigger('change');
            $('#ob_jumlah').val('1');
            $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\')').show();
            $('#load_obat, #warning_obat, #war_jenis_obat, #war_obat, #war_qty_obat').hide();
            $('#modal-obat').modal('show');
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
                            "<td align='right'>" + "Rp. " + tarif + ",-" + "</td>" +
                            "<td align='center'>" + item.qty + "</td>" +
                            "<td align='right'>" + "Rp. " + tarifTotal + ",-" + "</td>" +
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editTindakan(\'' + item.idTindakanRawat + '\',\'' + item.idTindakan + '\',\'' + item.idKategoriTindakan + '\',\'' + item.idPerawat + '\',\'' + item.qty + '\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
                            "</tr>";

                });
                table = table + "<tr>" +
                        "<td colspan='6'>Total</td>" +
                        "<td align='right'>" + "Rp. " + formatRupiah(trfTtl) + ",-" + "</td>" +
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
    }

    function saveObat(id) {

        var idJenis = $('#obat_jenis_obat').val();
        var idObat = $('#ob_id_obat').val();
        var qty = $('#ob_qty').val();

        if (idDetailCheckup != '' && idJenis != '' && idObat != '' && qty > 0) {
            $('#save_obat').hide();
            $('#load_obat').show();

            if (id != '') {
                dwr.engine.setAsync(true);
                ObatInapAction.editObatInap(id, idDetailCheckup, idObat, qty, function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        listObat();
                        $('#modal-obat').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(6);
                    } else {

                    }
                })
            } else {
                dwr.engine.setAsync(true);
                ObatInapAction.saveObatInap(idDetailCheckup, idObat, qty, function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        listObat();
                        $('#modal-obat').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(6);
                    } else {

                    }
                })
            }
        } else {
            $('#warning_obat').show().fadeOut(5000);

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
                            "<td>" + jenis + "</td>" +
                            "<td>" + qty + "</td>" +
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editObat(\'' + item.idObatInap + '\',\'' + item.idJenisObat + '\',\'' + item.idObat + '\',\'' + item.qty + '\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
                            "</tr>"
                });
            }
        });

        $('#body_obat').html(table);
    }

    function saveDiet(id) {

        var dietPagi = $('#diet_pagi').val();
        var bentukPagi = $('#bentuk_pagi').val();
        var dietSiang = $('#diet_siang').val();
        var bentukSiang = $('#bentuk_siang').val();
        var dietMalam = $('#diet_malam').val();
        var bentukMalam = $('#bentuk_malam').val();


        if (dietPagi != '' && bentukPagi != '' && dietSiang != '' && bentukSiang != '' && dietMalam != '' && bentukMalam != '') {
            $('#save_diet').hide();
            $('#load_diet').show();

            if (id != '') {
                dwr.engine.setAsync(true);
                OrderGiziAction.editOrderGizi(id, idRawatInap, dietPagi, bentukPagi, dietSiang, bentukSiang, dietMalam, bentukMalam, function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        listDiet();
                        $('#modal-diet').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(5);
                    } else {

                    }
                })
            } else {
                dwr.engine.setAsync(true);
                OrderGiziAction.saveOrderGizi(idRawatInap, dietPagi, bentukPagi, dietSiang, bentukSiang, dietMalam, bentukMalam, function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        listDiet();
                        $('#modal-diet').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(5);
                    } else {

                    }
                })
            }
        } else {
            $('#warning_diet').show().fadeOut(5000);
            if (dietPagi == '') {
                $('#war_pagi1').show();
            }
            if (bentukPagi == '') {
                $('#war_pagi2').show();
            }
            if (dietSiang == '') {
                $('#war_siang1').show();
            }
            if (bentukSiang == '') {
                $('#war_siang2').show();
            }
            if (dietMalam == '') {
                $('#war_malam1').show();
            }
            if (bentukMalam == '') {
                $('#war_malam2').show();
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
                    var tanggal = item.createdDate;
                    var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                    table += "<tr>" +
                            "<td>" + dateFormat + "</td>" +
                            "<td>" + item.dietPagi + "</td>" +
                            "<td>" + item.bentukMakanPagi + "</td>" +
                            "<td>" + item.dietSiang + "</td>" +
                            "<td>" + item.bentukMakanSiang + "</td>" +
                            "<td>" + item.dietMalam + "</td>" +
                            "<td>" + item.bentukMakanMalam + "</td>" +
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDiet(\'' + item.idOrderGizi + '\',\'' + item.dietPagi + '\',\'' + item.bentukMakanPagi + '\',\'' + item.dietSiang + '\',\'' + item.bentukMakanSiang + '\',\'' + item.dietMalam + '\',\'' + item.bentukMakanMalam + '\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
                            "</tr>"
                });
            }
        });

        $('#body_diet').html(table);
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

    function editDiet(id, pagi1, pagi2, siang1, siang2, malam1, malam2) {
        $('#load_diet, #warning_diet, #war_pagi1, #war_pagi2, #war_siang1, #war_siang2, #war_malam1, #war_malam2').hide();
        $('#diet_pagi').val(pagi1);
        $('#bentuk_pagi').val(pagi2);
        $('#diet_siang').val(siang1);
        $('#bentuk_siang').val(siang2);
        $('#diet_malam').val(malam1);
        $('#bentuk_malam').val(malam2);
        $('#save_diet').attr('onclick', 'saveDiet(\'' + id + '\')').show();
        $('#modal-diet').modal('show');
    }

    function editObat(id, jenis, obat, qty) {
        $('#load_obat, #warning_obat, #war_jenis_obat, #war_obat, #war_qty_obat').hide();
        $('#obat_jenis_obat').val(jenis).trigger('change');
        $('#ob_id_obat').val(obat).trigger('change');
        ;
        $('#ob_jumlah').val(qty);
        $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\')').show();
        $('#modal-obat').modal('show');
    }

    function editRuangan(kelas, ruang) {
        $('#load_ruangan, #war_ruangan_kelas, #war_ruangan_ruang').hide();
        $('#ruangan_kelas').val(kelas).trigger('change');
        $('#ruangan_ruang').val(ruang).trigger('change');
        $('#modal-ruangan').modal('show');
    }

    function listRuanganInap() {

        var table   = "";
        var data    = [];
        var no      = "";
        var name    = "";
        var kelas   = "";

        CheckupDetailAction.getListRuangInapByIdDetailCheckup(idDetailCheckup, function (response) {
            data = response;
            if (data != null) {
                $.each(data, function (i, item) {
                    if(item.namaRangan != null){
                        name = item.namaRangan;
                    }
                    if(item.noRuangan != null){
                        no  = item.noRuangan;
                    }
                    if(item.kelasRuanganName != null){
                        kelas = item.kelasRuanganName;
                    }
                    table += "<tr>" +
                            "<td>" + name + "</td>" +
                            "<td>" + no + "</td>" +
                            "<td>" + kelas + "</td>" +
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editRuangan(\'' + item.idKelas + '\',\'' + item.idRuangan + '\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
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


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>