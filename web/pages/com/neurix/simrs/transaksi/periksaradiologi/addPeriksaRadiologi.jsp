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
        .paint-canvas-ttd {
            border: 1px black solid;
            margin: 1rem;
            cursor: pointer;
        }

        .garis {
            color: #ddd;
        }
    </style>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PeriksaRadiologiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PeriksaLabAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LabDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/paintTtd.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/historypenunjang.js"/>'></script>

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
            Periksa Radiologi Pasien
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
                                    <tr>
                                        <td><b>No RM</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.idPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="45%"><b>No Checkup</b></td>
                                        <td>
                                            <s:hidden id="id_periksa_lab" name="periksaLab.idPeriksaLab"></s:hidden>
                                            <s:hidden id="no_checkup" name="periksaLab.noCheckup"></s:hidden>
                                            <s:hidden id="no_detail_checkup"
                                                      name="periksaLab.idDetailCheckup"></s:hidden>
                                            <s:hidden id="id_palayanan" name="periksaLab.idPelayanan"></s:hidden>
                                            <table><s:label name="periksaLab.noCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>No Checkup Detail</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.idDetailCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>NIK</b></td>
                                        <td>
                                            <table><s:label id="nik" name="periksaLab.nik"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Nama</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.namaPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Kelamin</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.jenisKelamin"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Tempat, Tanggal Lahir</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.tempatTglLahir"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Pasien</b></td>
                                        <td>
                                            <table>
                                                <s:if test='periksaLab.idJenisPeriksa == "asuransi"'>
                                                <span style="background-color: #ffff00; color: black; border-radius: 5px; border: 1px solid black; padding: 5px">
                                                </s:if>
                                                <s:elseif test='periksaLab.idJenisPeriksa == "umum"'>
                                                    <span style="background-color: #4d4dff; color: white; border-radius: 5px; border: 1px solid black; padding: 5px">
                                                </s:elseif>
                                                <s:elseif test='periksaLab.idJenisPeriksa == "bpjs"'>
                                                    <span style="background-color: #00b300; color: white; border-radius: 5px; border: 1px solid black; padding: 5px">
                                                </s:elseif>
                                                <s:elseif test='periksaLab.idJenisPeriksa == "ptpn"'>
                                                    <span style="background-color: #66ff33; color: black; border-radius: 5px; border: 1px solid black; padding: 5px">
                                                </s:elseif>
                                                <s:else>
                                                    <span style="background-color: #cc3399; color: white; border-radius: 5px; border: 1px solid black; padding: 5px">
                                                </s:else>
                                                    <s:property value="periksaLab.jenisPeriksaPasien"></s:property>
                                                </span>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Diagnosa</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.diagnosa"></s:label></table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <script>
                                    var cek = cekImages('<s:property value="periksaLab.urlKtp"/>');
                                    var url = '';
                                    if(cek){
                                        url = '<s:property value="periksaLab.urlKtp"/>';
                                    }else{
                                        url = contextPathHeader+'/pages/images/no-images.png';
                                    }
                                    var set = '<div style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px; text-align: center"\n' +
                                        'class="card card-4 pull-right">\n' +
                                        '<img border="2" id="img_ktp" src="'+url+'"\n' +
                                        'style="cursor: pointer; height: 90px; width: 190px; margin-top: 4px">\n' +
                                        '</div>';
                                    document.write(set);
                                </script>
                                <table class="table table-striped">
                                    <tr>
                                        <td><b>Pelayanan</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.namaPelayanan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Alamat</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.alamat"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Desa</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.desa"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kecamatan</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.kecamatan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kabupaten</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.kota"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Provinsi</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.provinsi"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Radiologi</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.labName"
                                                            class="label label-success"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td><button type="button" onclick="viewHistory()" class="btn btn-info hvr-icon-spin"><i class="fa fa-history hvr-icon"></i> All History Radiologi
                                        </button></td>
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

                                <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
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
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-4">
                                    <div class="input-group">
                                        <span class="input-group-btn">
                                            <span class="btn btn-default btn-file">
                                                 Browse… <input id="url_img" accept=".jpg" type="file">
                                            </span>
                                        </span>
                                        <input type="text" class="form-control" readonly id="label_img">
                                    </div>
                                    <span style="color: red">* Upload hasil radiologi luar</span>
                                    <canvas id="temp_canvas" style="display: none"></canvas>
                                </div>
                            </div>
                        </div>
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_rad">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_rad"></p>
                        </div>
                    </div>
                    <div class="box-header with-border" id="pos_lab">
                    </div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> Catatan Pemeriksaan</h3>
                    </div>
                    <div class="box-body">
                        <button id="btn-add-parameter" class="btn btn-success" style="margin-bottom: 10px; display: none" onclick="showModal(1)"><i class="fa fa-plus"></i>
                            Tambah Parameter
                        </button>
                        <button class="btn btn-primary" onclick="printPeriksaLab()" style="margin-bottom: 10px;"><i class="fa fa-print"></i> Print Label
                        </button>
                        <table class="table table-bordered table-striped" id="tabel_radiologi">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td width="40%">Pemeriksaan</td>
                                <td>Hasil</td>
                                <td>Kesan</td>
                            </tr>
                            </thead>
                            <tbody id="body_parameter">

                            </tbody>
                        </table>
                    </div>
                    <hr class="garis">
                    <div class="box-body">
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-3">
                                   <span>TTD Petugas</span>
                                    <button class="btn btn-danger" onclick="removePaint('ttd_petugas')">
                                        <i class="fa fa-trash"></i>
                                    </button>
                                </div>
                                <div class="col-md-3">
                                    <span>TTD Dokter</span>
                                    <button class="btn btn-danger" onclick="removePaint('ttd_dokter')">
                                        <i class="fa fa-trash"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-3">
                                    <canvas class="paint-canvas-ttd" id="ttd_petugas" width="250"
                                            onmouseover="paintTtd('ttd_petugas')"></canvas>
                                </div>
                                <div class="col-md-3">
                                    <canvas class="paint-canvas-ttd" id="ttd_dokter" width="250"
                                            onmouseover="paintTtd('ttd_dokter')"></canvas>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-offset-6 col-md-3">
                                <select class="form-control select2" id="id_dokter" style="width: 100%;"
                                        onchange="var warn =$('#war_catatan').is(':visible'); if (warn){$('#cor_catatan').show().fadeOut(3000);$('#war_catatan').hide()};">
                                    <option value=''>[Select One]</option>
                                </select>
                            </div>
                        </div>
                        <hr class="garis">
                        <div class="row" style="margin-top: -40px">
                            <div class="col-md-offset-4 col-md-4 text-center">
                                <a href="initForm_radiologi.action" style="margin-top: 25px" class="btn btn-warning"><i
                                        class="fa fa-times"></i> Cancel</a>
                                <button onclick="conSavePeriksaLab()" style="margin-top: 25px" class="btn btn-success">
                                    <i class="fa fa-check"></i> Save
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<div class="mask"></div>


<div class="modal fade" id="modal-edit-parameter">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Edit Parameter Pemeriksaan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_par">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_par"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-2">Hasil</label>
                        <div class="col-md-8">
                            <textarea rows="8" type="text" class="form-control" id="par_hasil"
                                      oninput="var warn =$('#war_hasil').is(':visible'); if (warn){$('#cor_hasil').show().fadeOut(3000);$('#war_hasil').hide()}"></textarea>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_hasil"><i
                                class="fa fa-times"></i> required</p>
                        <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_hasil">
                            <i class="fa fa-check"></i> correct</p>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_par"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_par"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambah Pemeriksaan</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_lab">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_lab"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Parameter</label>
                        <div class="col-md-7">
                            <select class="form-control select2" multiple style="margin-top: 7px; width: 100%"
                                    id="lab_parameter"
                                    onchange="var warn =$('#war_param').is(':visible'); if (warn){$('#cor_param').show().fadeOut(3000);$('#war_param').hide()}">
                                <option value=''>[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_param"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_param">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_lab" onclick="saveLab()"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_lab">
                    <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
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
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-ttd">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-pencil"></i> Tanda Tangan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_ttd">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_ttd"></p>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-7">
                            <div class="form-group" style="padding-top: 10px; padding-bottom: 10px">
                                <div class="col-md-1">
                                    <input type="color" style="margin-left: -6px; margin-top: -8px"
                                           class="js-color-picker  color-picker pull-left">
                                </div>
                                <div class="col-md-9">
                                    <input type="range" style="margin-top: -8px" class="js-line-range" min="1" max="72"
                                           value="1">
                                </div>
                                <div class="col-md-2">
                                    <div style="margin-top: -8px;" class="js-range-value">1 px</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 10px">
                    <div class="col-md-12">
                        <div class="col-md-6">
                            <b style="margin-left: 8px">Tanda Tangan Penanggun Jawab</b>
                            <canvas class="ttd-paint-canvas" id="ttd_penanggung_jawab" width="380" height="300"
                                    onmouseover="paintTtd(this.id)"></canvas>
                            <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                    onclick="removePaint('ttd_penanggung_jawab')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                        <div class="col-md-6">
                            <b style="margin-left: 8px">Tanda Tangan Dokter</b>
                            <canvas class="ttd-paint-canvas" id="ttd_dokter-1" width="380" height="300"
                                    onmouseover="paintTtd(this.id)"></canvas>
                            <button style="margin-left: 8px" type="button" class="btn btn-danger"
                                    onclick="removePaint('ttd_dokter')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" onclick="uploadCanvas()"><i class="fa fa-check"></i> Save
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
                <h4 class="modal-title"><i class="fa fa-user-md"></i> All History Radiologi
                </h4>
            </div>
            <div class="modal-body" style="height: 450px;overflow-y: scroll;">
                <div class="box-body">
                    <table class="table table-bordered" style="font-size: 12px;">
                        <thead>
                        <tr style="font-weight: bold">
                            <td width="30%">Pelayanan</td>
                            <td width="15%">Waktu</td>
                            <td>Keterangan</td>
                            <td width="16%">Catatan</td>
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

<div class="modal fade" id="modal-lab_luar">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="title_lab_luar"></span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <img id="img_lab_luar" style="width: 100%">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript'>

    var idPeriksaRadiologi = "";
    var noCheckup = $('#no_checkup').val();
    var idDetailCheckup = $('#no_detail_checkup').val();
    var idPoli = $('#id_palayanan').val();
    var idPasien = '<s:property value="periksaLab.idPasien"/>';
    var idPeriksaLab = $('#id_periksa_lab').val();
    var idLabPemeriksaan = '<s:property value="periksaLab.idLab"/>';
    var jenisPasien = '<s:property value="periksaLab.idJenisPeriksa"/>';
    var metodePembayaran = '<s:property value="periksaLab.metodePembayaran"/>';
    var keterangan = '<s:property value="periksaLab.keterangan"/>';
    var tipeLab = 'radiologi';

    $(document).ready(function () {
        $('#penunjang_active, #periksa_radiologi').addClass('active');
        $('#penunjang_open').addClass('menu-open');
        listSelectDokter();
        getIdRadiologi();

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

        $(document).on('change', '.btn-file :file', function () {
            var input = $(this),
                label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [label]);
        });

        var canvas = document.getElementById('temp_canvas');
        var ctx = canvas.getContext('2d');

        $('.btn-file :file').on('fileselect', function (event, label) {

            var input = $(this).parents('.input-group').find(':text'),
                log = label;

            if (input.length) {
                input.val(log);
                var reader = new FileReader();
                reader.onload = function (event) {
                    var img = new Image();
                    img.onload = function () {
                        canvas.width = img.width;
                        canvas.height = img.height;
                        ctx.clearRect(0, 0, canvas.width, canvas.height);
                        ctx.drawImage(img, 0, 0);
                    }
                    img.src = event.target.result;
                }
                reader.readAsDataURL(event.target.files[0]);
            } else {
                if (log) alert(log);
            }

        });

        if (keterangan == "just_lab") {
            $('#btn-add-parameter').show();
        } else {
            $('#btn-add-parameter').hide();
        }
    });

    function showModal(select) {
        if (select == 1) {
            $('#lab_kategori, #lab_lab, #lab_parameter').val('').trigger('change');
            $('#save_lab').show();
            $('#load_lab, #warning_lab').hide();
            $('#lab_kategori, #lab_lab').css('border', '');
            listSelectParameter(idLabPemeriksaan);
            $('#modal-lab').modal({show: true, backdrop: 'static'});
        }
    }

    function toContent() {
        var ref = $('#close_pos').val();
        if (ref == 1) {
            window.location.href = 'initForm_radiologi.action';
        }
        if (ref == 2) {
            $('html, body').animate({
                scrollTop: $('#pos_lab').offset().top
            }, 2000);
        }
    }

    function listSelectParameter(idLab) {
        var option = "";
        if (idLab != '') {
            LabDetailAction.getListComboParameter(idLab, function (response) {
                if (response.length > 0) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idLabDetail + "'>" + item.namaDetailPeriksa + "</option>";
                    });
                    $('#lab_parameter').html(option);
                } else {
                    $('#lab_parameter').html(option);
                }
            });
        }
    }

    function listSelectDokter() {
        var option = "<option value=''>[Select One]</option>";
        PeriksaLabAction.getListDokterLabRadiologi("radiologi", function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idDokter + "'>" + item.namaDokter + "</option>";
                });
                $('#id_dokter').html(option);
            } else {
                $('#id_dokter').html(option);
            }
        });
    }

    function saveRadiologi(id) {

        var idDokter = $('#id_dokter').val();
        var kesimpulan = $('#par_hasil').val();
        var ket = "";

        if (id != '' && kesimpulan != '') {
            $('#save_par').hide();
            $('#load_par').show();
            dwr.engine.setAsync(true);
            PeriksaLabAction.saveEditParameterLab(id, kesimpulan, ket, {
                callback: function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        getIdRadiologi();
                        $('#modal-edit-parameter').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(2);
                        $('body').scrollTop(0);
                    } else {
                        $('#save_par').show();
                        $('#load_par').hide();
                        $('#warning_par').show().fadeOut(5000);
                        $('#msg_par').text(response.msg);
                    }
                }
            });
        } else {
            $('#warning_par').show().fadeOut(5000);
            $('#msg_par').text("Silahkan cek kembali data inputan!");
            $('#war_hasil').show();
        }
    }


    function saveLab() {
        var idParameter = $('#lab_parameter').val();
        if (idDetailCheckup != '' && idPeriksaLab != '' && idParameter != null) {
            $('#save_lab').hide();
            $('#load_lab').show();
            dwr.engine.setAsync(true);
            PeriksaLabAction.saveUpdatePemeriksaan(idPeriksaLab, idParameter, "radiologi", {
                callback: function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        getIdRadiologi();
                        $('#modal-lab').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(2);
                        $('body').scrollTop(0);
                    } else {
                        $('#save_lab').show();
                        $('#load_lab').hide();
                        $('#warning_lab').show().fadeOut(5000);
                        $('#msg_lab').text(response.msg);
                    }
                }
            });
        } else {
            $('#msg_lab').text("Silahkan cek kembali inputan anda..!");
            $('#warning_lab').show().fadeOut(5000);
            if (idParameter == '' || idParameter == null) {
                $('#war_param').show();
            }
        }
    }

    function getIdRadiologi() {
        PeriksaLabAction.listParameterPemeriksaan(idPeriksaLab, function (response) {
            if (response.length > 0) {
                var table = "";
                $.each(response, function (i, item) {
                    var hasil = "";
                    var kesan = "";
                    if (item.hasil != null && item.hasil != '') {
                        hasil = item.hasil;
                    }
                    if (item.keteranganPeriksa != null && item.keteranganPeriksa != '') {
                        kesan = item.keteranganPeriksa;
                    }
                    table += '<tr>' +
                        '<td>' + item.namaDetailPeriksa +
                        '<input id="id_periksa_lab_'+i+'" type="hidden" value="'+item.idPeriksaLabDetail+'">' +
                        '</td>' +
                        '<td>' + '<textarea id="hasil_'+i+'" class="form-control" value="'+hasil+'"/>' + '</td>' +
                        '<td>' + '<textarea id="kesan_'+i+'" class="form-control" value="'+kesan+'"/>' + '</td>' +
                        '</tr>';
                });
                $('#body_parameter').html(table);
            }
        });
    }

    function editParameter(id, hasil) {
        $('#save_par').show();
        $('#load_par').hide();
        $('#par_hasil').val(hasil);
        $('#save_par').attr('onclick', 'saveRadiologi(\'' + id + '\')');
        $('#modal-edit-parameter').modal('show');
    }

    function conSavePeriksaLab() {
        var idDokter = $('#id_dokter').val();
        var data = $('#tabel_radiologi').tableToJSON();
        var img = $('#url_img').val();
        var petugas = document.getElementById("ttd_petugas");
        var dokter = document.getElementById("ttd_dokter");
        var cekPetugas = isCanvasBlank(petugas);
        var cekDokter = isCanvasBlank(dokter);

        var cek = false;
        $.each(data, function (i, item) {
            var hasil = $('#hasil_'+i).val();
            if (hasil == "") {
                cek = true;
            }
        });
        if (!cek && idDokter != '' && !cekPetugas && !cekDokter || img != '' && data.length > 0) {
            $('#modal-confirm-dialog').modal('show');
            $('#save_con').attr('onclick', 'savePeriksaLab(\'' + idDokter + '\')');
        } else {
            $('#warning_rad').show().fadeOut(5000);
            $('#msg_rad').text("Silahkan cek kembali data hasil radiologi dan nama dokter...!");
        }
    }

    function savePeriksaLab(idDokter) {
        $('#modal-confirm-dialog').modal('hide');

        var idPeriksaLab = '<s:property value="periksaLab.idPeriksaLab"/>';
        var url = document.getElementById("temp_canvas");
        var petugas = document.getElementById("ttd_petugas");
        var dokter = document.getElementById("ttd_dokter");
        var hasil = url.toDataURL("image/png"),
            hasil = hasil.replace(/^data:image\/(png|jpg);base64,/, "");
        var idPasien = '<s:property value="periksaLab.idPasien"/>';
        var idPelayanan = '<s:property value="periksaLab.idPelayanan"/>';
        var metodePembayaran = '<s:property value="periksaLab.metodePembayaran"/>';
        var jenisPasien = '<s:property value="periksaLab.idJenisPeriksa"/>';
        var idDetailCheckup = '<s:property value="periksaLab.idDetailCheckup"/>';
        var noCheckup = '<s:property value="periksaLab.noCheckup"/>';
        var isiParam = $('#tabel_radiologi').tableToJSON();
        var jsonData = [];
        $.each(isiParam, function (i, item) {
            var idLab = $('#id_periksa_lab_'+i).val();
            var hasil = $('#hasil_'+i).val();
            var kesan = $('#kesan_'+i).val();
            if(hasil != ''){
                jsonData.push({
                    'id_periksa_lab':idLab,
                    'hasil':hasil,
                    'kesan':kesan,
                })
            }
        });

        var data = {
            'no_checkup':noCheckup,
            'id_pasien': idPasien,
            'id_detail_checkup': idDetailCheckup,
            'jenis_pasien': jenisPasien,
            'id_pelayanan': idPelayanan,
            'metode_bayar': metodePembayaran,
            'is_resep':'N',
            'just_lab': "Y"
        }

        var img = $("#url_img").val();
        var finalImg = "";
        var finalPetugas = convertToDataURL(petugas);
        var finalDokter = convertToDataURL(dokter);

        if (img != '') {
            finalImg = hasil;
        }
        var result = JSON.stringify(data);
        var jsonResult = JSON.stringify(jsonData);
        $('#waiting_dialog').dialog('open');
        dwr.engine.setAsync(true);
        PeriksaLabAction.saveEditDokterLab(idPeriksaLab, idDokter, finalImg, keterangan, result, finalDokter, finalPetugas, jsonResult, {
            callback: function (response) {
                if (response.status == "success") {
                    $('#waiting_dialog').dialog('close');
                    $('#save_ket').show();
                    $('#load_ket').hide();
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(1);
                    $('body').scrollTop(0);
                } else {
                    $('#warning_rad').show().fadeOut(5000);
                    $('#msg_rad').text(res.message);
                    $('#waiting_dialog').dialog('close');
                }
            }
        });
    }

    function printPeriksaLab(){
        window.open('printRadiologi_radiologi.action?id='+idDetailCheckup+'&lab='+idPeriksaLab+'&ket=label', "_blank");
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>