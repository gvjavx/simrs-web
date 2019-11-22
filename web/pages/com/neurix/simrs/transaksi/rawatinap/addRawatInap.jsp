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
                                <table class="table table-striped">
                                    <tr>
                                        <td width="45%"><b>No Checkup</b></td>
                                        <td><table><s:label id="no_checkup" name="rawatInap.noCheckup"></s:label></table></td>
                                    </tr>
                                    <tr>
                                        <td><b>No Checkup Detail</b></td>
                                        <td ><table><s:label id="no_detail_checkup" name="rawatInap.idDetailCheckup"></s:label></table></td>
                                    </tr>
                                    <tr>
                                        <td><b>NIK</b></td>
                                        <td><table><s:label id="nik" name="rawatInap.nik"></s:label></table></td>
                                    </tr>
                                    <tr>
                                        <td><b>Nama</b></td>
                                        <td><table><s:label id="nama" name="rawatInap.namaPasien"></s:label></table></td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Kelamin</b></td>
                                        <td><table><s:label id="jenis_kelamin" name="rawatInap.jenisKelamin"></s:label></table></td>
                                    </tr>
                                    <tr>
                                        <td><b>Tempat, Tanggal Lahir</b></td>
                                        <td><table><s:label name="rawatInap.tempatTglLahir"></s:label></table></td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Pasien</b></td>
                                        <td><table>
                                            <s:label name="rawatInap.jenisPeriksaPasien"></s:label>
                                        </table></td>
                                    </tr>
                                    <tr>
                                        <td><b>Poli</b></td>
                                        <td><table>
                                            <s:hidden id="id_palayanan" name="rawatInap.idPelayanan"></s:hidden>
                                            <s:label id="nama_pelayanan" name="rawatInap.namaPelayanan"></s:label></table></td>
                                    </tr>
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <table class="table table-striped">
                                    <tr>
                                        <td><b>Alamat</b></td>
                                        <td><table><s:label name="rawatInap.alamat"></s:label></table></td>
                                    </tr>
                                    <tr>
                                        <td><b>Provinsi</b></td>
                                        <td><table><s:label name="rawatInap.provinsi"></s:label></table></td>
                                    </tr>
                                    <tr>
                                        <td><b>Kabupaten</b></td>
                                        <td><table><s:label name="rawatInap.kota"></s:label></table></td>
                                    </tr>
                                    <tr>
                                        <td><b>Kecamatan</b></td>
                                        <td><table><s:label name="rawatInap.kecamatan"></s:label></table></td>
                                    </tr>
                                    <tr>
                                        <td><b>Desa</b></td>
                                        <td><table><s:label name="rawatInap.desa"></s:label></table></td>
                                    </tr>
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="form-group" style="display: none">
                                <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false" closeOnEscape="false"
                                           height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                           buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         toContent();
                                                                                     }
                                                                            }"
                                >
                                    <s:hidden id="close_pos"></s:hidden>
                                    <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
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
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px" onclick="showModal(1)"><i class="fa fa-plus"></i> Tambah Dokter</button>
                        <table class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>ID Dokter</td>
                                <td>Nama</td>
                                <td>Action</td>
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
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px" onclick="showModal(2)"><i class="fa fa-plus"></i> Tambah Tindakan</button>
                        <table class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>Tanggal</td>
                                <td>Tindakan</td>
                                <td>Dokter</td>
                                <td>Perawat</td>
                                <td align="right">Tarif</td>
                                <td align="center">Qty</td>
                                <td align="right">Total</td>
                                <td>Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_tindakan">
                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border" id="pos_nosa">
                    </div>
                    <div class="box-header with-border" >
                        <h3 class="box-title"><i class="fa fa-stethoscope"></i> Diagnosa</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px" onclick="showModal(3)"><i class="fa fa-plus"></i> Tambah Diagnosa</button>
                        <table class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>Tanggal</td>
                                <td>ID Diagnosa</td>
                                <td>Keterangan</td>
                                <td>Jenis Diagnosa</td>
                                <td>Action</td>
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
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px" onclick="showModal(4)"><i class="fa fa-plus"></i> Tambah Lab</button>
                        <table class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>Tanggal Order</td>
                                <td>Pemeriksaan</td>
                                <td>Status</td>
                                <td>Jenis Lab</td>
                                <td>Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_lab">

                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border" id="pos_diet">
                    </div>
                    <div class="box-header with-border" >
                        <h3 class="box-title"><i class="fa fa-stethoscope"></i> Order Diet</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px" onclick="showModal(5)"><i class="fa fa-plus"></i> Order </button>
                        <table class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>Tanggal</td>
                                <td>Jenis Diet Pagi</td>
                                <td>Bentuk Diet Pagi</td>
                                <td>Jenis Diet Pagi</td>
                                <td>Bentuk Diet Pagi</td>
                                <td>Jenis Diet Pagi</td>
                                <td>Bentuk Diet Pagi</td>
                                <td>Keterangan</td>
                                <td>Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_diet">

                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border" id="pos_obat">
                    </div>
                    <div class="box-header with-border" >
                        <h3 class="box-title"><i class="fa fa-stethoscope"></i> Order Diet</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px" onclick="showModal(5)"><i class="fa fa-plus"></i> Order </button>
                        <table class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>Tanggal</td>
                                <td>ID Obat</td>
                                <td>Obat</td>
                                <td>Jenis Obat</td>
                                <td>Qty</td>
                                <td>Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_obat">

                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-navicon"></i> Keterangan</h3>
                    </div>
                    <div class="box-body">
                        <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label style="margin-top: 7px">Keterangan Selesai Periksa</label>
                                <select class="form-control">
                                    <option>Pulang</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-success" style="margin-top: 10px; width: 150px"><i class="fa fa-arrow-right"></i> Save</button>
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
                    <label class="col-md-3">Nama Dokter</label>
                    <div class="col-md-7">
                        <select id="dok_id_dokter" class="form-control" onchange="$(this).css('border','')">
                        </select>
                    </div>
                </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button type="button" class="btn btn-success" onclick="saveDokter()" id="save_dokter"><i class="fa fa-arrow-right"></i> Save</button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_dokter"><i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...</button>
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
                            <s:select cssStyle="margin-top: 7px" onchange="$(this).css('border',''); listSelectTindakan(this);"
                                      list="#initComboKategoriTindakan.listOfKategoriTindakan" id="tin_id_ketgori_tindakan"
                                      listKey="idKategoriTindakan"
                                      listValue="kategoriTindakan"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Tindakan</label>
                        <div class="col-md-7">
                            <select class="form-control" style="margin-top: 7px" id="tin_id_tindakan" onchange="$(this).css('border','')">
                                <option value=''>[Select One]</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Dokter</label>
                        <div class="col-md-7">
                            <select class="form-control" style="margin-top: 7px" id="tin_id_dokter" onchange="$(this).css('border','')">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Perawat</label>
                        <div class="col-md-7">
                            <select class="form-control" style="margin-top: 7px" id="tin_id_perawat" onchange="$(this).css('border','')">
                                <option value="">[select one]</option>
                                <option value="1">Angel</option>
                                <option value="2">Anya</option>
                                <option value="3">Ayu</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" >
                        <label class="col-md-3" style="margin-top: 7px">Jumlah</label>
                        <div class="col-md-7">
                           <input type="number" min="1" class="form-control" style="margin-top: 7px" id="tin_qty" oninput="$(this).css('border','')" onchange="$(this).css('border','')">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button type="button" class="btn btn-success" onclick="saveTindakan()" id="save_tindakan"><i class="fa fa-arrow-right"></i> Save</button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_tindakan"><i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...</button>
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
                                <s:select cssStyle="margin-top: 7px" onchange="$(this).css('border','')"
                                          list="#initComboDiagnosa.listOfComboDiagnosa" id="nosa_id_diagnosa"
                                          name="headerDetailCheckup.idPelayanan" listKey="idDiagnosa"
                                          listValue="descOfDiagnosa"
                                          headerKey="" headerValue="[Select one]"
                                          cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3">Jenis Diagnosa</label>
                        <div class="col-md-7">
                            <select class="form-control" style="margin-top: 7px" id="nosa_jenis_diagnosa" onchange="$(this).css('border','')">
                                <option value="">[select one]</option>
                                <option value="0">Diagnosa Awal</option>
                                <option value="1">Diagnosa Akhir</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button type="button" class="btn btn-success" onclick="saveDiagnosa()" id="save_diagnosa"><i class="fa fa-arrow-right"></i> Save</button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_diagnosa"><i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...</button>
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambah Order Lab</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3">Poli</label>
                        <div class="col-md-7">
                            <select class="form-control">
                                <option value="">[select one]</option>
                                <option value="1">Dr. Sutikno</option>
                                <option value="2">Dr. Julio</option>
                                <option value="3">Dr. Turnomo</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3">Nama Dokter</label>
                        <div class="col-md-7">
                            <select class="form-control" style="margin-top: 7px">
                                <option value="">[select one]</option>
                                <option value="1">Dr. Sutikno</option>
                                <option value="2">Dr. Julio</option>
                                <option value="3">Dr. Turnomo</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
                <button type="button" class="btn btn-success"><i class="fa fa-arrow-right"></i> Save</button>
            </div>
        </div>
    </div>
</div>
<!-- /.content-wrapper -->
<script type='text/javascript'>

    var idDetailCheckup = $('#no_detail_checkup').text();
    var idPoli          = $('#id_palayanan').val();

    $( document ).ready(function() {
        $('#rawat_inap').addClass('active');
        listDokter();
        listTindakan();
        listDiagnosa();
        listSelectDokter();
    });

    function listSelectDokter(){
        var option = "";
        CheckupAction.listOfDokter(idPoli, function(response){
            option = "<option value=''>[Select One]</option>";
            if (response != null){
                $.each(response, function (i, item) {
                    option += "<option value='"+item.idDokter+"'>" +item.namaDokter+ "</option>";
                });
            }else{
                option = option;
            }
        });
        $('#dok_id_dokter').html(option);
        $('#tin_id_dokter').html(option);
    }

    function listSelectTindakan(idKategori){
        var idx     = idKategori.selectedIndex;
        var idKtg   = idKategori.options[idx].value;
        var option = "<option value=''>[Select One]</option>";
        if(idKtg != ''){
            CheckupDetailAction.getListComboTindakan(idKtg, function(response){
                if (response != null){
                    $.each(response, function (i, item) {
                        option += "<option value='"+item.idTindakan+"'>" +item.tindakan+ "</option>";
                    });
                }else{
                    option = option;
                }
            });
        }else{
            alert("kosong");
            option = option;
        }

        $('#tin_id_tindakan').html(option);
    }

    function toContent(){
        var back = $('#close_pos').val();
        var desti = "";

        if(back == 1){
            desti = "#pos_dok";
        }else if(back == 2){
            desti = "#pos_tin";
        }else if(back == 3){
            desti = "#pos_nosa";
        }else if (back == 4){
            desti = "#pos_lab";
        }

        $('html, body').animate({
            scrollTop: $(desti).offset().top
        }, 2000);
    }

    function showModal(select){

        if (select == 1){
            $('#save_dokter').show();
            $('#load_dokter, #warning_dokter').hide();
            $('#dok_id_dokter').css('border','');
            $('#modal-dokter').modal('show');
        }else if(select == 2){
            $('#tin_id_tindakan').val('');
            $('#tin_id_dokter').val('');
            $('#tin_id_perawat').val('');
            $('#tin_qty').val('');
            $('#save_tindakan').show();
            $('#load_tindakan, #warning_tindakan').hide();
            $('#tin_id_tindakan, #tin_id_dokter, #tin_id_perawat, #tin_qty').css('border','');
            $('#modal-tindakan').modal('show');

        }else if(select == 3){
            $('#nosa_id_diagnosa').val('');
            $('#nosa_jenis_diagnosa').val('');
            $('#save_diagnosa').show();
            $('#load_diagnosa, #warning_diagnosa').hide();
            $('#nosa_id_diagnosa, #nosa_jenis_diagnosa').css('border','');
            $('#modal-diagnosa').modal('show');
        }else if(select == 4){
            $('#modal-lab').modal('show');
        }
    }

    function formatRupiah(angka){
        var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
        ribuan = ribuan.join('.').split('').reverse().join('');
        return ribuan;
    }


    function saveDokter(){
        var idDokter        = $('#dok_id_dokter').val();
        if(idDetailCheckup != '' && idDokter != ''){
            $('#save_dokter').hide();
            $('#load_dokter').show();
            dwr.engine.setAsync(true);
            TeamDokterAction.saveDokter(idDetailCheckup, idDokter, function (response) {
                if(response == "success"){
                    dwr.engine.setAsync(false);
                    listDokter();
                    $('#modal-dokter').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(1);
                }else{

                }
            })
        }else{
            $('#warning_dokter').show().fadeOut(5000);
            $('#dok_id_dokter').css('border','red solid 1px');
        }
    }

    function listDokter(){
        var table           = "";
        var data            = [];
        TeamDokterAction.listDokter(idDetailCheckup, function (response) {
            data = response;
            console.log(data);
            if (data != null){
                $.each(data, function (i,item) {
                    table += "<tr>" +
                            "<td>" + item.idDokter + "</td>" +
                            "<td>" + item.namaDokter + "</td>" +
                            "<td>"+ '<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" style="cursor: pointer">'+ "</td>"+
                            "</tr>"
                });
            }
        });

        $('#body_dokter').html(table);
    }

    function saveTindakan(){

        var idKategori      = $('#tin_id_ketgori_tindakan').val();
        var idTindakan      = $('#tin_id_tindakan').val();
        var idDokter        = $('#tin_id_dokter').val();
        var idPerawat       = $('#tin_id_perawat').val();
        var qty             = $('#tin_qty').val();

        if (idDetailCheckup != '' && idTindakan !='' && idDokter != '' && idPerawat != '' && qty > 0 && idKategori != ''){
            $('#save_tindakan').hide();
            $('#load_tindakan').show();
            dwr.engine.setAsync(true);
            TindakanRawatAction.saveTindakanRawat(idDetailCheckup, idTindakan, idDokter, idPerawat, qty, { callback : function (response) {
                if (response == "success"){
                    dwr.engine.setAsync(false);
                    listTindakan();
                    $('#modal-tindakan').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(2);
                }else{
                    $('#eror_dialog').dialog('open');
                    $('#save_tindakan').show();
                    $('#load_tindakan').hide();
                }
            }
            });
        }else {
            $('#warning_tindakan').show().fadeOut(5000);

            if(idKategori == ''){
                $('#tin_id_ketgori_tindakan').css('border','red solid 1px');
            }
            if(idTindakan == ''){
                $('#tin_id_tindakan').css('border','red solid 1px');
            }
            if(idDokter == ''){
                $('#tin_id_dokter').css('border','red solid 1px');
            }
            if(idPerawat == ''){
                $('#tin_id_perawat').css('border','red solid 1px');
            }
            if(qty <= 0 || qty == ''){
                $('#tin_qty').css('border','red solid 1px');
            }
        }
    }

    function listDokterTindakan(){

        var idPelayanan = $("#id_pelayanan").val();

        var option = "";
        CheckupAction.listOfDokter(idPelayanan, function(response){
            option = "<option value=''>[Select One]</option>";
            if (response != null){
                $.each(response, function (i, item) {
                    option += "<option value='"+item.idDokter+"'>" +item.namaDokter+ "</option>";
                });
            }else{
                option = option;
            }
        });
        $('#dokter_tindakan').html(option);
    }

    function listTindakan(){

        var table           = "";
        var data            = [];
        var tarif           = "";
        var tarifTotal      = "";
        var trfTotal        = 0;
        var qtyTotal        = 0;
        var trfTtl          = 0;

        TindakanRawatAction.listTindakanRawat(idDetailCheckup, function (response) {
            data = response;
            if (data != null){
                $.each(data, function (i,item) {
                    var tanggal = item.createdDate;
                    var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                    if(item.tarif != null){
                        tarif = formatRupiah(item.tarif);
                        trfTotal += item.tarif;
                    }
                    if(item.tarifTotal != null){
                        tarifTotal = formatRupiah(item.tarifTotal);
                        trfTtl += item.tarifTotal;
                    }
                    if(item.qty != null){
                        qtyTotal += item.qty;
                    }

                    table += "<tr>" +
                            "<td>" + dateFormat + "</td>" +
                            "<td>" + item.namaTindakan + "</td>" +
                            "<td>" + item.namaDokter + "</td>" +
                            "<td>" + item.idPerawat + "</td>" +
                            "<td align='right'>" + "Rp. "+ tarif+",-" + "</td>" +
                            "<td align='center'>" + item.qty + "</td>" +
                            "<td align='right'>" + "Rp. "+ tarifTotal+",-" + "</td>" +
                            "<td>"+ '<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" style="cursor: pointer">'+ "</td>"+
                            "</tr>";

                });
                table = table + "<tr>"+
                                "<td colspan='6'>Total</td>"+
//                                "<td align='right'>" + "Rp. " + formatRupiah(trfTotal) + "</td>"+
//                                "<td align='center'>"+ qtyTotal +"</td>"+
                                "<td align='right'>" + "Rp. " + formatRupiah(trfTtl)+",-" + "</td>"+
                                "<td></td>"+
                                "</tr>";
            }
        });

        $('#body_tindakan').html(table);

    }

    function saveDiagnosa(){
        var idDiagnosa      = $('#nosa_id_diagnosa').val();
        var jenisDiagnosa   = $('#nosa_jenis_diagnosa').val();

        if(idDetailCheckup != '' && idDiagnosa != '' && jenisDiagnosa != ''){
            $('#save_diagnosa').hide();
            $('#load_diagnosa').show();
            dwr.engine.setAsync(true);
            DiagnosaRawatAction.saveDiagnosa(idDetailCheckup, idDiagnosa, jenisDiagnosa, { callback : function (response) {
                if(response == "success"){
                    dwr.engine.setAsync(false);
                    listDiagnosa();
                    $('#modal-diagnosa').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(3);
                }else{

                }
            }})
        }else{
            $('#warning_diagnosa').show().fadeOut(5000);
            if(idDiagnosa == ''){
                $('#nosa_id_diagnosa').css('border','red solid 1px');
            }
            if(jenisDiagnosa == ''){
                $('#nosa_jenis_diagnosa').css('border','red solid 1px');
            }
        }
    }

    function listDiagnosa(){

        var table = "";
        var data  = [];
        var id    = "";
        var ket   = "";
        var jen   = "";

        DiagnosaRawatAction.listDiagnosa(idDetailCheckup, function (response) {
            data = response;
            console.log('tes');
            console.log(data);
            if (data != null){
                $.each(data, function (i,item) {
                    var tanggal = item.createdDate;
                    var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                    if(item.idDiagnosa != null){
                        id = item.idDiagnosa;
                    }
                    if(item.keteranganDiagnosa != null){
                        ket = item.keteranganDiagnosa;
                    }
                    if(item.jenisDiagnosa != null){
                        if(item.jenisDiagnosa == 0){
                            jen = "Diagnosa Awal";
                        }else{
                            jen = "Diagnosa Akhir";
                        }
                    }
                    table += "<tr>" +
                            "<td>" +  dateFormat + "</td>" +
                            "<td>" + id + "</td>" +
                            "<td>" + ket + "</td>" +
                            "<td>" + jen + "</td>" +
                            "<td>"+ '<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" style="cursor: pointer">'+ "</td>"+
                            "</tr>"
                });
            }
        });

        $('#body_diagnosa').html(table);
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>