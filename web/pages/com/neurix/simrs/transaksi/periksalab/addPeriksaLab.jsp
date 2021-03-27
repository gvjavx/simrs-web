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

        .top_jarak {
            margin-top: 7px;
        }
    </style>

    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LabAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LabDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PeriksaLabAction.js"/>'></script>
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
            Periksa Lab Pasien
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
                                                <script>
                                                    document.write(changeJenisPasien('<s:property value="periksaLab.idJenisPeriksa"/>', '<s:property value="periksaLab.jenisPeriksaPasien"/>'));
                                                </script>
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
                                    document.write(imagesDefault('<s:property value="periksaLab.urlKtp"/>'));
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
                                        <td><b>Laboratorium</b></td>
                                        <td>
                                            <table>
                                                <s:if test='periksaLab.isLuar == "Y"'>
                                                    <s:property value="periksaLab.namaLabLuar"></s:property>
                                                    <label class="span-warning">Luar</label>
                                                </s:if>
                                                <s:else>
                                                    <s:property value="periksaLab.labName"></s:property>
                                                </s:else>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <button type="button" onclick="viewHistory()"
                                                    class="btn btn-info hvr-icon-spin"><i
                                                    class="fa fa-history hvr-icon"></i> All History Laboratorium
                                            </button>
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

                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-12">
                                <button id="btn-add-parameter" class="btn btn-success btn-outline"
                                        onclick="showModal(1)" style="margin-bottom: -20px; display: none"><i
                                        class="fa fa-plus"></i> Tambah Parameter
                                </button>
                                <button class="btn btn-primary" onclick="printPeriksaLab()"
                                        style="margin-bottom: -20px;"><i class="fa fa-print"></i> Print Label
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border" id="pos_lab">
                    </div>
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-hospital-o"></i> Catatan Pemeriksaan</h3>
                            </div>
                            <div class="col-md-6" id="form_lab_luar_title" style="display: none">
                                <h3 class="box-title"><i class="fa fa-upload"></i> Upload Hasil Laboratorium Luar</h3>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_rad">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_rad"></p>
                        </div>
                        <table style="display: none" class="table table-bordered table-striped" id="tabel_lab">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Pemeriksaan</td>
                                <td>Hasil</td>
                                <td>Keterangan</td>
                                <td>Nilai Normal</td>
                                <td>Satuan</td>
                            </tr>
                            </thead>
                            <tbody id="body_parameter">

                            </tbody>
                        </table>
                        <div class="row" id="form_params" style="display: none">
                            <div class="form-group">
                                <div class="col-md-6">
                                    <p>List Parameter Pemeriksaan</p>
                                    <ul style="margin-left: 20px; margin-top: -6px" id="params">
                                    </ul>
                                </div>
                                <div class="col-md-6">
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-md-10">
                                                <div class="input-group">
                                        <span class="input-group-btn">
                                            <span class="btn btn-default btn-file">
                                                 Browse… <input accept="image/*"
                                                                onchange="setCanvasWithText('hasil_luar_0', 'label_hasil_luar_0', 'img_hasil_luar_0')"
                                                                type="file">
                                            </span>
                                        </span>
                                                    <input type="text" class="form-control" readonly
                                                           id="label_hasil_luar_0">
                                                </div>
                                                <canvas id="hasil_luar_0" class="hasil_luar"
                                                        style="display: none"></canvas>
                                            </div>
                                            <div class="col-md-2">
                                                <button onclick="addUpload('hasil_luar', 'set_luar')"
                                                        class="btn btn-success"
                                                        style="margin-left: -20px; margin-top: 3px"><i
                                                        class="fa fa-plus"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="set_luar">

                                    </div>
                                    <div class="row top_jarak">
                                        <div class="col-md-3">
                                            <a class="btn btn-success" onclick="viewUpload('hasil_luar')"><i
                                                    class="fa fa-image"></i> View Upload</a>
                                        </div>
                                        <div class="col-md-7">
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    Rp.
                                                </div>
                                                <input oninput="convertRpAtas(this.id, this.value, 'h_total_tarif')"
                                                       id="total_tarif" class="form-control" placeholder="Total Tarif">
                                                <input type="hidden" id="h_total_tarif">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-sticky-note-o"></i> Keterangan
                            <small>(Optional)</small>
                        </h3>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-12">
                                 <textarea name="editor1" id="keterangan_hasil_lab" rows="5">
                                 </textarea>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-6" id="form_hasil_lab_title">
                                <h3 class="box-title"><i class="fa fa-upload"></i> Upload Hasil Laboratorium
                                    <small>(Optional)</small>
                                </h3>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6" id="form_hasil_lab_isi" style="display: none">
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-md-10">
                                            <div class="input-group">
                                        <span class="input-group-btn">
                                            <span class="btn btn-default btn-file">
                                                 Browse… <input accept="image/*"
                                                                onchange="setCanvasWithText('hasil_lab_0', 'label_hasil_lab_0', 'img_hasil_lab_0')"
                                                                type="file">
                                            </span>
                                        </span>
                                                <input type="text" class="form-control" readonly id="label_hasil_lab_0">
                                            </div>
                                            <canvas id="hasil_lab_0" class="hasil_lab" style="display: none"></canvas>
                                        </div>
                                        <div class="col-md-2">
                                            <button onclick="addUpload('hasil_lab', 'set_hasil')"
                                                    class="btn btn-success" style="margin-left: -20px; margin-top: 3px">
                                                <i class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>
                                <div id="set_hasil">

                                </div>
                                <div class="row top_jarak">
                                    <div class="col-md-12">
                                        <a class="btn btn-success" onclick="viewUpload('hasil_lab')"><i
                                                class="fa fa-image"></i> View Upload</a>
                                    </div>
                                </div>
                            </div>
                        </div>
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
                                    <span>TTD Validator</span>
                                    <button class="btn btn-danger" onclick="removePaint('ttd_dokter')">
                                        <i class="fa fa-trash"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-3">
                                    <canvas class="paint-canvas-ttd" id="ttd_petugas" width="260"
                                            onmouseover="paintTtd('ttd_petugas')" style="margin-left: -8px"></canvas>
                                    <input class="form-control nama_petugas" placeholder="Nama Petugas"
                                           id="nama_petugas">
                                    <input class="form-control nip_petugas" placeholder="NIP/SIP"
                                           style="margin-top: 5px"
                                           id="nip_petugas">
                                </div>
                                <div class="col-md-3">
                                    <canvas class="paint-canvas-ttd" id="ttd_dokter" width="260"
                                            onmouseover="paintTtd('ttd_dokter')" style="margin-left: -8px"></canvas>
                                    <input class="form-control" placeholder="Nama Validator" id="nama_validator">
                                    <input class="form-control" placeholder="NIP/SIP" style="margin-top: 5px"
                                           id="nip_validator">
                                </div>
                            </div>
                        </div>
                        <hr class="garis">
                        <div class="row" style="margin-top: -40px">
                            <div class="col-md-offset-4 col-md-4 text-center">
                                <a href="initForm_periksalab.action" style="margin-top: 25px" class="btn btn-warning"><i
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
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_edit_parameter">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_par"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3">Hasil</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="par_hasil"
                                   oninput="var warn =$('#war_hasil').is(':visible'); if (warn){$('#cor_hasil').show().fadeOut(3000);$('#war_hasil').hide()}">
                        </div>
                    </div>
                    <div class="col-md-2">
                        <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_hasil"><i
                                class="fa fa-times"></i> required</p>
                        <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_hasil">
                            <i class="fa fa-check"></i> correct</p>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Keterangan</label>
                        <div class="col-md-7">
                            <textarea id="par_ket" class="form-control" style="margin-top: 7px"
                                      oninput="var warn =$('#war_ket').is(':visible'); if (warn){$('#cor_ket').show().fadeOut(3000);$('#war_ket').hide()}"></textarea>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_ket"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_ket">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
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
                        class="fa fa-check"></i> Save
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
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-check"></i> Yes
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
                <h4 class="modal-title"><i class="fa fa-user-md"></i> All History Laboratorium
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span
                        id="title_lab_luar"></span></h4>
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

<div class="modal fade" id="modal-hasil_lab">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-image"></i> <span
                        id="title_hasil_lab"></span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div id="carousel-hasil_lab" class="carousel slide" data-ride="carousel">
                                <ol class="carousel-indicators" id="li_hasil_lab">
                                    <li data-target="#carousel-hasil_lab" data-slide-to="0" class="active"></li>
                                </ol>
                                <div class="carousel-inner" id="item_hasil_lab">
                                    <div class="item active" id="item_hasil_lab_0">
                                        <img id="img_hasil_lab_0" style="width: 100%">
                                    </div>
                                </div>
                                <a class="left carousel-control" href="#carousel-hasil_lab" data-slide="prev">
                                    <span class="fa fa-angle-left"></span>
                                </a>
                                <a class="right carousel-control" href="#carousel-hasil_lab" data-slide="next">
                                    <span class="fa fa-angle-right"></span>
                                </a>
                            </div>
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

<div class="modal fade" id="modal-hasil_luar">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-image"></i> <span
                        id="title_hasil_luar"></span></h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div id="carousel-hasil_luar" class="carousel slide" data-ride="carousel">
                                <ol class="carousel-indicators" id="li_hasil_luar">
                                    <li data-target="#carousel-hasil_luar" data-slide-to="0" class="active"></li>
                                </ol>
                                <div class="carousel-inner" id="item_hasil_luar">
                                    <div class="item active" id="item_hasil_luar_0">
                                        <img id="img_hasil_luar_0" style="width: 100%">
                                    </div>
                                </div>
                                <a class="left carousel-control" href="#carousel-hasil_luar" data-slide="prev">
                                    <span class="fa fa-angle-left"></span>
                                </a>
                                <a class="right carousel-control" href="#carousel-hasil_luar" data-slide="next">
                                    <span class="fa fa-angle-right"></span>
                                </a>
                            </div>
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

<div class="mask"></div>
<!-- /.content-wrapper -->
<script type='text/javascript'>

    var noCheckup = $('#no_checkup').val();
    var idDetailCheckup = $('#no_detail_checkup').val();
    var idPoli = $('#id_palayanan').val();
    var idPasien = '<s:property value="periksaLab.idPasien"/>';
    var idPeriksaLab = $('#id_periksa_lab').val();
    var idLabPemeriksaan = '<s:property value="periksaLab.idLab"/>';
    var jenisPasien = '<s:property value="periksaLab.idJenisPeriksa"/>';
    var metodePembayaran = '<s:property value="periksaLab.metodePembayaran"/>';
    var keterangan = '<s:property value="periksaLab.keterangan"/>';
    var tipeLab = 'laboratorium';

    $(document).ready(function () {
        $('#penunjang_active, #periksa_lab').addClass('active');
        $('#penunjang_open').addClass('menu-open');

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

        if (keterangan == "just_lab") {
            $('#btn-add-parameter').show();
        } else {
            $('#btn-add-parameter').hide();
        }

        var isLuar = '<s:property value="periksaLab.isLuar"/>';
        if ("Y" == isLuar) {
            getlistParams();
            $('#form_hasil_lab_title').hide();
            $('#form_lab_luar_title').show();
            $('#form_hasil_lab_isi').hide();
            $('#tabel_lab').hide();
            $('#form_params').show();
        } else {
            listParameter();
            $('#form_hasil_lab_title').show();
            $('#form_lab_luar_title').hide();
            $('#form_hasil_lab_isi').show();
            $('#tabel_lab').show();
            $('#form_params').hide();
        }

        $('.carousel').carousel({
            interval: false,
            ride: false,
            pause: false
        });

        cekLogin();
    });

    function listSelectDokter() {
        var option = "<option value=''>[Select One]</option>";
        PeriksaLabAction.getListDokterLabRadiologi("lab", function (response) {
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idDokter + "'>" + item.namaDokter + "</option>";
                });
                $('#list_dokter').html(option);
            } else {
                $('#list_dokter').html(option);
            }
        });
    }

    function conSavePeriksaLab() {
        var data = $('#tabel_lab').tableToJSON();
        var nama1 = $('#nama_petugas').val();
        var nip1 = $('#nip_petugas').val();
        var nama2 = $('#nama_validator').val();
        var nip2 = $('#nip_validator').val();
        var petugas = document.getElementById("ttd_petugas");
        var dokter = document.getElementById("ttd_dokter");
        var cekPetugas = isCanvasBlank(petugas);
        var cekDokter = isCanvasBlank(dokter);
        var totalTarif = $('#h_total_tarif').val();
        var cekIsKeluar = '<s:property value="periksaLab.isLuar"/>';

        var cek = false;
        $.each(data, function (i, item) {
            var hasil = $('#hasil_' + i).val();
            if (hasil == "") {
                cek = true;
            }
        });

        var tempHasilLuar = $('.hasil_luar');
        var cekLabLuar = false;
        $.each(tempHasilLuar, function (i, item) {
            var canvas = document.getElementById('hasil_luar_' + i);
            var cekCanvas = isCanvasBlank(canvas);
            var toDecode = canvas.toDataURL("image/png");
            if (!cekCanvas) {
                cekLabLuar = true;
            }
        });

        if ("Y" == cekIsKeluar) {
            if (cekLabLuar && totalTarif != '') {
                $('#modal-confirm-dialog').modal('show');
                $('#save_con').attr('onclick', 'savePeriksaLab()');
            } else {
                $('#warning_rad').show().fadeOut(5000);
                $('#msg_rad').text("Silahkan cek kembali data hasil Laboratorium, data petugas, dan data Validator...!");
                $(window).scrollTop($("#pos_lab").offset().top);
            }
        } else {
            if (nama1 && nama2 && nip1 && nip2 != '' && !cek && !cekPetugas && !cekDokter || cekLabLuar && data.length > 0) {
                $('#modal-confirm-dialog').modal('show');
                $('#save_con').attr('onclick', 'savePeriksaLab()');
            } else {
                $('#warning_rad').show().fadeOut(5000);
                $('#msg_rad').text("Silahkan cek kembali data hasil Laboratorium, data petugas, dan data Validator...!");
                $(window).scrollTop($("#pos_lab").offset().top);
            }
        }
    }

    function savePeriksaLab() {
        $('#modal-confirm-dialog').modal('hide');
        var idPeriksaLab = '<s:property value="periksaLab.idPeriksaLab"/>';
        var petugas = document.getElementById("ttd_petugas");
        var dokter = document.getElementById("ttd_dokter");
        var idPasien = '<s:property value="periksaLab.idPasien"/>';
        var idPelayanan = '<s:property value="periksaLab.idPelayanan"/>';
        var metodePembayaran = '<s:property value="periksaLab.metodePembayaran"/>';
        var jenisPasien = '<s:property value="periksaLab.idJenisPeriksa"/>';
        var idDetailCheckup = '<s:property value="periksaLab.idDetailCheckup"/>';
        var noCheckup = '<s:property value="periksaLab.noCheckup"/>';
        var nama1 = $('#nama_petugas').val();
        var nip1 = $('#nip_petugas').val();
        var nama2 = $('#nama_validator').val();
        var nip2 = $('#nip_validator').val();
        var isiParam = $('#tabel_lab').tableToJSON();
        var totalTarif = $('#h_total_tarif').val();
        var keteranganHasil = $('#keterangan_hasil_lab').val();

        var jsonData = [];
        $.each(isiParam, function (i, item) {
            var idLab = $('#id_periksa_lab_' + i).val();
            var hasil = $('#hasil_' + i).val();
            var kesan = $('#kesan_' + i).val();
            if (hasil != '') {
                jsonData.push({
                    'id_periksa_lab': idLab,
                    'hasil': hasil,
                    'kesan': kesan,
                });
            }
        });

        var finalPetugas = convertToDataURL(petugas);
        var finalDokter = convertToDataURL(dokter);

        var tempHasilLab = $('.hasil_lab');
        var tempImgHasilLab = [];
        $.each(tempHasilLab, function (i, item) {
            var canvas = document.getElementById('hasil_lab_' + i);
            var cekCanvas = isCanvasBlank(canvas);
            var toDecode = canvas.toDataURL("image/png");
            if (!cekCanvas) {
                tempImgHasilLab.push({
                    'img_hasil_lab': convertToDataURLAtas(canvas)
                });
            }
        });

        var tempHasilLuar = $('.hasil_luar');
        var tempImgHasilLuar = [];
        $.each(tempHasilLuar, function (i, item) {
            var canvas = document.getElementById('hasil_luar_' + i);
            var cekCanvas = isCanvasBlank(canvas);
            var toDecode = canvas.toDataURL("image/png");
            if (!cekCanvas) {
                tempImgHasilLuar.push({
                    'img_hasil_luar': convertToDataURLAtas(canvas)
                });
            }
        });

        var data = {
            'no_checkup': noCheckup,
            'id_pasien': idPasien,
            'id_detail_checkup': idDetailCheckup,
            'jenis_pasien': jenisPasien,
            'id_pelayanan': idPelayanan,
            'metode_bayar': metodePembayaran,
            'is_resep': 'N',
            'just_lab': "Y"
        }

        var tempDataFinal = {
            'id_periksa_lab': idPeriksaLab,
            'keterangan': keterangan,
            'data': JSON.stringify(data),
            'nama_petugas': nama1,
            'nip_petugas': nip1,
            'nama_validator': nama2,
            'nip_validator': nip2,
            'ttd_petugas': finalPetugas,
            'ttd_validator': finalDokter,
            'hasil_pemeriksaan': JSON.stringify(jsonData),
            'upload_hasil': JSON.stringify(tempImgHasilLab),
            'upload_hasil_luar': JSON.stringify(tempImgHasilLuar),
            'total_tarif': totalTarif,
            'keterangan_hasil': keteranganHasil
        }

        var result = JSON.stringify(tempDataFinal);
        $('#waiting_dialog').dialog('open');
        dwr.engine.setAsync(true);
        PeriksaLabAction.saveEditDokterLab(result, {
            callback: function (response) {
                if (response.status == "success") {
                    $('#waiting_dialog').dialog('close');
                    $('#save_ket').show();
                    $('#load_ket').hide();
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(2);
                    $('body').scrollTop(0);
                } else {
                    $('#save_ket').show();
                    $('#load_ket').hide();
                    $('#waiting_dialog').dialog('close');
                    $('#warning_rad').show().fadeOut(5000);
                    $('#msg_rad').text(response.message);
                    $(window).scrollTop($("#pos_lab").offset().top);
                }
            }
        });
    }

    function toContent() {
        var back = $('#close_pos').val();
        var desti = "";

        if (back == 1) {
            desti = "#pos_lab";
        } else if (back == 2) {
            window.location.href = 'initForm_periksalab.action';
        }

        $('html, body').animate({
            scrollTop: $(desti).offset().top
        }, 2000);
    }

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

    function saveLab() {
        var idParameter = $('#lab_parameter').val();
        if (idDetailCheckup != '' && idPeriksaLab != '' && idParameter != null) {
            $('#save_lab').hide();
            $('#load_lab').show();
            dwr.engine.setAsync(true);
            PeriksaLabAction.saveUpdatePemeriksaan(idPeriksaLab, idParameter, "lab", {
                callback: function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        listParameter();
                        $('#modal-lab').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(1);
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

    function listParameter() {

        var table = "";
        var data = [];
        var jenisKelamin = '<s:property value="periksaLab.jenisKelamin"/>';

        PeriksaLabAction.listParameterPemeriksaan(idPeriksaLab, function (response) {
            data = response;
            if (data.length > 0) {
                $.each(data, function (i, item) {

                    var pemeriksaan = "";
                    var hasil = "";
                    var satuan = "";
                    var acuan = "";
                    var keterangan = "";

                    if (item.namaDetailPeriksa != null) {
                        pemeriksaan = item.namaDetailPeriksa;
                    }
                    if (item.hasil != null) {
                        hasil = item.hasil;
                    }
                    if (item.satuan != null) {
                        satuan = item.satuan;
                    }
                    if (jenisKelamin == "Perempuan") {
                        if (item.keteranganAcuanP != null) {
                            acuan = item.keteranganAcuanP;
                        }
                    } else {
                        if (item.keteranganAcuanL != null) {
                            acuan = item.keteranganAcuanL;
                        }
                    }
                    if (item.keteranganPeriksa != null) {
                        keterangan = item.keteranganPeriksa;
                    }
                    table += "<tr>" +
                        "<td>" + pemeriksaan +
                        '<input id="id_periksa_lab_' + i + '" type="hidden" value="' + item.idPeriksaLabDetail + '">' +
                        "</td>" +
                        '<td>' + '<textarea id="hasil_' + i + '" class="form-control" value="' + hasil + '"/>' + '</td>' +
                        '<td>' + '<textarea id="kesan_' + i + '" class="form-control" value="' + keterangan + '"/>' + '</td>' +
                        "<td>" + acuan + "</td>" +
                        "<td>" + satuan + "</td>" +
                        "</tr>"
                });
                $('#body_parameter').html(table);
            }
        });
    }

    function editParameter(id, hasil, keterangan) {
        $('#save_par').show();
        $('#load_par').hide();
        $('#par_hasil').val(hasil);
        $('#par_ket').val(keterangan);
        $('#save_par').attr('onclick', 'saveEditParameter(\'' + id + '\')');
        $('#modal-edit-parameter').modal({show: true, backdrop: 'static'});
    }

    function saveEditParameter(id) {

        var hasil = $('#par_hasil').val();
        var ket = $('#par_ket').val();

        if (id != '' && hasil != '' && ket != '') {
            $('#save_par').hide();
            $('#load_par').show();
            dwr.engine.setAsync(true);
            PeriksaLabAction.saveEditParameterLab(id, hasil, ket, {
                callback: function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        listParameter();
                        $('#modal-edit-parameter').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(1);
                        $('body').scrollTop(0);
                    } else {
                        $('#save_par').show();
                        $('#load_par').hide();
                        $('#warning_edit_parameter').show().fadeOut(5000);
                        $('#msg_par').text(response.msg);
                    }
                }
            });
        } else {
            $('#warning_edit_parameter').show().fadeOut(5000);
            $('#msg_par').text("Silahkan cek kembali data inputan...!");
            if (hasil == '') {
                $('#war_hasil').show();
            }
            if (ket == '') {
                $('#war_ket').show();
            }
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

    function printPeriksaLab() {
        window.open('printLab_periksalab.action?id=' + idDetailCheckup + '&lab=' + idPeriksaLab + '&ket=label', "_blank");
    }

    function addUpload(jen, idset) {
        var valJen = $('.' + jen);
        var count = valJen.length;
        var idRow = jen + count;
        var jenis = jen + "_" + count;
        var label = 'label_' + jen + "_" + count;
        var set = '<div class="row top_jarak" id="' + idRow + '">\n' +
            '<div class="form-group">\n' +
            '    <div class="col-md-10">\n' +
            '        <div class="input-group">\n' +
            '<span class="input-group-btn">\n' +
            '    <span class="btn btn-default btn-file">\n' +
            '         Browse… <input accept="image/*" onchange="setCanvasWithText(\'' + jenis + '\', \'' + label + '\', \'img_' + jenis + '\')" type="file">\n' +
            '    </span>\n' +
            '</span>\n' +
            '            <input type="text" class="form-control" readonly id="' + label + '">\n' +
            '        </div>\n' +
            '        <canvas id="' + jenis + '" class="' + jen + '" style="display: none"></canvas>\n' +
            '    </div>\n' +
            '    <div class="col-md-2">\n' +
            '        <button onclick="delUpload(\'' + idRow + '\',\'item_' + jenis + '\', \'li_' + jenis + '\')" class="btn btn-danger" style="margin-left: -20px; margin-top: 3px"><i class="fa fa-trash"></i></button>\n' +
            '    </div>\n' +
            '</div>\n' +
            '</div>';
        var imgCanvas = '<div class="item" id="item_' + jenis + '">\n' +
            '<img id="img_' + jenis + '" style="width: 100%">\n' +
            '</div>';
        $('#item_' + jen).append(imgCanvas);
        $('#li_' + jen).append('<li id="li_' + jenis + '" data-target="#carousel-' + jen + '" data-slide-to="' + count + '"></li>');
        $('#' + idset).append(set);
    }

    function delUpload(id, hidden, li) {
        $('#' + id).remove();
        $('#' + hidden).remove();
        $('#' + li).remove();
    }

    function viewUpload(jenis) {
        if (jenis == 'hasil_lab') {
            $('#title_' + jenis).html("View Laboratorium Hasil Laboratorium");
        } else {
            $('#title_' + jenis).html("View Laboratorium Hasil Laboratorium Luar");
        }
        var item = $('.' + jenis);
        $.each(item, function (i, item) {
            if (i == 0) {
                $('#item_' + jenis + '_' + i).addClass("active");
            } else {
                $('#item_' + jenis + '_' + i).removeClass("active");
            }
        });
        $('#modal-' + jenis).modal({show: true, backdrop: 'static'});
    }

    function setCanvasWithText(id, tujuan, idHidden) {
        var canvas = document.getElementById(id);
        var ctx = canvas.getContext('2d');
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
        $('#' + tujuan).val(event.target.files[0].name);

        var fr = new FileReader();
        fr.onload = function (event) {
            document.getElementById(idHidden).src = event.target.result;
        }
        fr.readAsDataURL(event.target.files[0]);
    }

    function getlistParams() {
        PeriksaLabAction.listParameterPemeriksaan(idPeriksaLab, function (response) {
            if (response.length > 0) {
                var set = "";
                $.each(response, function (i, item) {
                    set += '<li>' + item.namaDetailPeriksa + '</li>';
                });
                $('#params').html(set);
            }
        });
    }

    function cekLogin() {
        CheckupAction.cekLogin({
            callback: function (res) {
                if (res != '') {
                    $('.nama_petugas').val(res.msg);
                    $('.nip_petugas').val(res.status);
                }
            }
        });
    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>