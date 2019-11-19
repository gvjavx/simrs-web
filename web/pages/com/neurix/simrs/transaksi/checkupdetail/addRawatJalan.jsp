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
    <script type='text/javascript' src='<s:url value="/dwr/interface/TindakanRawatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TeamDokterAction.js"/>'></script>
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
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>No Checkup</label>
                                    <s:textfield id="no_checkup"
                                                 name="headerDetailCheckup.noCheckup" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                    <s:hidden id="id_pelayanan" name="headerDetailCheckup.idPelayanan"></s:hidden>
                                </div>
                                <!-- /.form-group -->
                                <div class="form-group">
                                    <label style="margin-top: 7px">No Detail Checkup</label>
                                    <s:textfield id="no_detail_checkup"
                                                 name="headerDetailCheckup.idDetailCheckup" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                </div>

                                <div class="form-group">
                                    <label style="margin-top: 7px">Nama</label>
                                    <s:textfield id="nama"
                                                 name="headerDetailCheckup.namaPasien" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                </div>

                                <div class="form-group">
                                    <label style="margin-top: 7px">Jenis Kelamin</label>
                                    <s:textfield id="jenis_kelamin"
                                                 name="headerDetailCheckup.jenisKelamin" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">Tempat Lahir</label>
                                    <s:textfield id="tempat_lahir"
                                                 name="headerDetailCheckup.tempatLahir" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                </div>
                                <!-- /.form-group -->
                            </div>
                            <!-- /.col -->
                            <div class="col-md-4">

                                <div class="form-group">
                                    <label>Tanggal Lahir</label>
                                    <s:textfield id="tgl_lahir"
                                                 name="headerDetailCheckup.tglLahir" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">Jenis Pasien</label>
                                    <s:textfield id="jenis_pasien"
                                                 name="headerDetailCheckup.idJenisPeriksaPasien" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">Poli</label>
                                    <s:textfield id="poli"
                                                 name="headerDetailCheckup.namaPelayanan" required="false"
                                                 readonly="true" cssClass="form-control"/>
                                </div>

                                <div class="form-group">
                                    <label style="margin-top: 7px">Alamat</label>
                                    <s:textarea id="jalan" rows="4"
                                                name="headerDetailCheckup.jalan" required="false"
                                                readonly="true" cssClass="form-control"/>
                                </div>

                            </div>

                            <div class="col-md-4">

                                <!-- /.form-group -->
                                <div class="form-group">
                                    <label>Provinsi</label>
                                    <s:textfield id="provinsi" name="headerDetailCheckup.provinsi" required="true" readonly="true"
                                                 cssClass="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">Kota</label>
                                    <s:textfield id="kota" name="headerDetailCheckup.kota" required="true" readonly="true"
                                                 cssClass="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">Kecamatan</label>
                                    <s:textfield id="kecamatan" name="headerDetailCheckup.kecamatan" required="true" readonly="true"
                                                 cssClass="form-control"/>
                                </div>
                                <div class="form-group">
                                    <label style="margin-top: 7px">Keluahan/Desa</label>
                                    <s:textfield id="desa" name="headerDetailCheckup.desa" required="true" readonly="true"
                                                 cssClass="form-control"/>
                                </div>
                                <!-- /.form-group -->
                            </div>
                            <!-- /.col -->
                            <div class="form-group" style="display: none">
                                <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false" closeOnEscape="false"
                                           height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                           buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                     }
                                                                            }"
                                >
                                    <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                    Record has been saved successfully.
                                </sj:dialog>

                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
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
                                <td>Poli</td>
                                <td>Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_dokter">

                            </tbody>
                        </table>
                    </div>

                    <div class="box-header with-border">
                    </div>

                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-medkit"></i> Tindakan</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px" onclick="showModal(2)"><i class="fa fa-plus"></i> Tambah Tindakan</button>
                        <table class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>Tindakan</td>
                                <td>Tanggal</td>
                                <td>Dokter</td>
                                <td>Perawat</td>
                                <td>Tarif</td>
                                <td>Qty</td>
                                <td>Total</td>
                                <td>Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_tindakan">
                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-stethoscope"></i> Diagnosa</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px" onclick="showModal(3)"><i class="fa fa-plus"></i> Tambah Diagnosa</button>
                        <table class="table table-bordered table-striped">
                            <thead >
                            <tr bgcolor="#90ee90">
                                <td>ID Diagnosa</td>
                                <td>Deskripsi</td>
                                <td>Keterangan</td>
                                <td>Tanggal</td>
                                <td>Status</td>
                                <td>Poli</td>
                                <td>Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_diagnosa">

                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border">
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

                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-navicon"></i> Keterangan</h3>
                    </div>
                    <div class="box-body">
                        <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>Waktu Selesai Periksa</label>
                                <s:textfield id=""
                                             name="headerDetailCheckup.noCheckup" required="false"
                                             cssClass="form-control"/>
                            </div>
                            <!-- /.form-group -->
                            <div class="form-group">
                                <label style="margin-top: 7px">Keterangan Selesai Periksa</label>
                                <s:textfield id="e" name="headerDetailCheckup.idDetailCheckup" required="false"
                                             cssClass="form-control"/>
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
                <div class="row">
                <div class="form-group">
                    <label class="col-md-3">Nama Dokter</label>
                    <div class="col-md-7">
                        <select id="dok_id_dokter" class="form-control" style="margin-top: 7px">
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
                <button type="button" class="btn btn-success" onclick="saveDokter()"><i class="fa fa-arrow-right"></i> Save</button>
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
                        <label class="col-md-3" style="margin-top: 7px">Nama Tindakan</label>
                        <div class="col-md-7">
                            <select class="form-control" id="tin_id_tindakan" onchange="$(this).css('border','')">
                                <option value="">[select one]</option>
                                <option value="1">Suntik</option>
                                <option value="2">Pil</option>
                                <option value="3">Obat</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Dokter</label>
                        <div class="col-md-7">
                            <select class="form-control" style="margin-top: 7px" id="tin_id_dokter" onchange="$(this).css('border','')">
                                <option value="">[select one]</option>
                                <option value="1">Dr. Sutikno</option>
                                <option value="2">Dr. Julio</option>
                                <option value="3">Dr. Turnomo</option>
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
                            <select class="form-control" id="nosa_id_diagnosa" onchange="$(this).css('border','')">
                                <option value="">[select one]</option>
                                <option value="1">Ablasi Retina</option>
                                <option value="2">Abses Gigi</option>
                                <option value="3">Abses Paru</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3">Jenis Diagnosa</label>
                        <div class="col-md-7">
                            <select class="form-control" style="margin-top: 7px" id="nosa_jenis_diagnosa" onchange="$(this).css('border','')">
                                <option value="">[select one]</option>
                                <option value="1">Diagnosa Awal</option>
                                <option value="2">Diagnosa Akhir</option>
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


    $( document ).ready(function() {
        $('#rawat_jalan').addClass('active');
        listTindakan();
        listDokter();
    });

    var idDetailCheckup = $('#no_detail_checkup').val();

    function showModal(select){

        if (select == 1){
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
            $('#modal-diagnosa').modal('show');
        }else if(select == 4){
            $('#modal-lab').modal('show');
        }
    }


    function saveDokter(){
        var idDokter        = $('#dok_id_dokter').val();
        if(idDetailCheckup != '' && idDokter != ''){
            dwr.engine.setAsync(true);
            TeamDokterAction.saveDokter(idDetailCheckup, idDokter, function (response) {
                if(response == "success"){
                    dwr.engine.setAsync(false);
                    listDokter();
                    $('#modal-dokter').modal('hide');
                    $('#info_dialog').dialog('open');
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
                            "<td>" + item.namaPleyanan + "</td>" +
                            "<td>"+ '<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" style="cursor: pointer">'+ "</td>"+
                            "</tr>"
                });
            }
        });

        $('#body_dokter').html(table);
    }

    function saveTindakan(){

        var idTindakan      = $('#tin_id_tindakan').val();
        var idDokter        = $('#tin_id_dokter').val();
        var idPerawat       = $('#tin_id_perawat').val();
        var qty             = $('#tin_qty').val();

        if (idDetailCheckup != '' && idTindakan !='' && idDokter != '' && idPerawat != '' && qty > 0){
            $('#save_tindakan').hide();
            $('#load_tindakan').show();
            dwr.engine.setAsync(true);
            TindakanRawatAction.saveTindakanRawat(idDetailCheckup, idTindakan, idDokter, idPerawat, qty, { callback : function (response) {
                if (response == "success"){
                    dwr.engine.setAsync(false);
                    listTindakan();
                    $('#modal-tindakan').modal('hide');
                    $('#info_dialog').dialog('open');
                }else{
                    $('#eror_dialog').dialog('open');
                    $('#save_tindakan').show();
                    $('#load_tindakan').hide();
                }
            }
            });
        }else {
            $('#warning_tindakan').show().fadeOut(5000);

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

    function listTindakan(){

        var table           = "";
        var data            = [];
        TindakanRawatAction.listTindakanRawat(idDetailCheckup, function (response) {
            data = response;
            if (data != null){
                $.each(data, function (i,item) {
                    var tanggal = item.createdDate;
                    var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                    table += "<tr>" +
                            "<td>" + item.idTindakan + "</td>" +
                            "<td>" + dateFormat + "</td>" +
                            "<td>" + item.idDokter + "</td>" +
                            "<td>" + item.idPerawat + "</td>" +
                            "<td>" + item.tarif + "</td>" +
                            "<td>" + item.qty + "</td>" +
                            "<td>" + item.tarifTotal + "</td>" +
                            "<td>"+ '<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" style="cursor: pointer">'+ "</td>"+
                            "</tr>"
                });
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
            TeamDokterAction.saveDokter(idDetailCheckup, idDiagnosa, jenisDiagnosa, { callback : function (response) {
                if(response == "success"){
                    dwr.engine.setAsync(false);
                    listDiagnosa();
                    $('#modal-diagnosa').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#save_diagnosa').show();
                    $('#load_diagnosa').hide();
                }else{

                }
            }})
        }else{
            $('#warning_diagnosa').show().fadeOut(5000);
            if(idDiagnosa == ''){
                $('#nosa_id_diagnosa').css('border','red solid 1px');
            }
            if(jenisDiagnosa){
                $('#nosa_jenis_diagnosa').css('border','red solid 1px');
            }
        }
    }

    function listDiagnosa(){

        var table           = "";
        var data            = [];
        DiagnosaRawatAction.listTindakanRawat(idDetailCheckup, function (response) {
            data = response;
            if (data != null){
                $.each(data, function (i,item) {
                    var tanggal = item.createdDate;
                    var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(tanggal));
                    table += "<tr>" +
                            "<td>" + item.idDiagnosa + "</td>" +
                            "<td>" + dateFormat + "</td>" +
                            "<td>" + item.idDokter + "</td>" +
                            "<td>" + item.idPerawat + "</td>" +
                            "<td>" + item.tarif + "</td>" +
                            "<td>" + item.qty + "</td>" +
                            "<td>" + item.tarifTotal + "</td>" +
                            "<td>"+ '<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" style="cursor: pointer">'+ "</td>"+
                            "</tr>"
                });
            }
        });

        $('#body_tindakan').html(table);
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>