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

    <link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/radio_checkbox.css"/>">
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PeriksaRadiologiAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PeriksaLabAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LabDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/paintTtd.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/historypenunjang.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/datapasien.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TindakanRawatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TindakanAction.js"/>'></script>

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
                                        <td width="40%"><b>No Checkup</b></td>
                                        <td>
                                            <s:hidden id="id_periksa_lab" name="periksaLab.idPeriksaLab"></s:hidden>
                                            <s:hidden id="no_checkup" name="periksaLab.noCheckup"></s:hidden>
                                            <s:hidden id="no_detail_checkup" name="periksaLab.idDetailCheckup"></s:hidden>
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
                                        <td><b>Umur</b></td>
                                        <td>
                                            <table><s:property value="periksaLab.umur"></s:property> Tahun</table>
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
                                    <s:if test='periksaLab.isCito == "Y"'>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <table>
                                                    <span class="span-warning">CITO</span>
                                                </table>
                                            </td>
                                        </tr>
                                    </s:if>
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <script>
                                    document.write(imagesDefault('<s:property value="periksaLab.urlKtp"/>'));
                                </script>
                                <table class="table table-striped">
                                    <tr>
                                        <td width="40%"><b>Pelayanan</b></td>
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
                                        <td><b>Dokter Pengirim</b></td>
                                        <td>
                                            <table>
                                                <s:label name="periksaLab.namaDokterPengirim"></s:label>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Diagnosa</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.diagnosa"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Catatan Klinis</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.catatanKlinis"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Penyakit Dahulu</b></td>
                                        <td>
                                            <table><s:label name="periksaLab.penyakitDahulu"></s:label></table>
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
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div id="asesmen_radiolgi" class="btn-group dropdown" style="margin-bottom: -20px; display: none">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-edit"></i> Asesmen
                                    </button>
                                    <button onclick="getListRekamMedis()" type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown" style="height: 34px">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu" id="asesmen_rj">
                                    </ul>
                                </div>
                                <button id="btn-add-parameter" class="btn btn-success"
                                        style="margin-bottom: -20px; display: none" onclick="showModal(1)"><i
                                        class="fa fa-plus"></i>
                                    Tambah Parameter
                                </button>
                                <button class="btn btn-primary" onclick="printPeriksaLab()"
                                        style="margin-bottom: -20px;"><i class="fa fa-print"></i> Print Label
                                </button>
                                <button type="button" onclick="viewHistory()" style="margin-bottom: -20px;"
                                        class="btn btn-info hvr-icon-spin"><i
                                        class="fa fa-history hvr-icon"></i> All History Radiologi
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
                                <h3 class="box-title"><i class="fa fa-upload"></i> Upload Hasil Radiologi Luar</h3>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_rad">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_rad"></p>
                        </div>
                        <div id="body_parameter" id="form_hasil_lab_isi">

                        </div>
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
                                                 Browse… <input accept="image/*" id="hasil_luar_0" class="hasil_luar"
                                                                onchange="parseToByte('hasil_luar_0', 'label_hasil_luar_0', 'hasil_luar0', '', '', 'luar')"
                                                                type="file">
                                            </span>
                                        </span>
                                                    <input type="text" class="form-control" readonly
                                                           id="label_hasil_luar_0">
                                                </div>
                                            </div>
                                            <div class="col-md-2">
                                                <button onclick="addUpload('hasil_luar', 'set_luar', '', '', 'luar')"
                                                        class="btn btn-success"
                                                        style="margin-left: -20px; margin-top: 3px"><i
                                                        class="fa fa-plus"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="set_luar">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border" id="pos_tindakan">
                        <h3 class="box-title"><i class="fa fa-hospital-o"></i> HR Dokter</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showTindakan()"><i class="fa fa-plus"></i> Tambah HR
                        </button>
                        <table class="table table-bordered table-striped table-hover table-responsive" id="tabel_tindakan">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td width="14%">Waktu</td>
                                <td>Tindakan</td>
                                <td>Dokter</td>
                                <td align="center">Tarif (Rp.)</td>
                                <td align="center">Qty</td>
                                <td align="center">Total (Rp.)</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_tindakan">
                            </tbody>
                        </table>
                    </div>
                    <hr class="garis">
                    <div class="row" style="display: none">
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
                    <div class="row" style="margin-top: 10px; display: none" id="form_ttd_pemeriksa">
                        <div class="col-md-offset-4 col-md-4 text-center">
                            <span>TTD Dokter Pemeriksa</span>
                            <canvas style="margin-left: -7px; cursor: pointer" class="ttd-paint-canvas" id="ttd_dokter"
                                    width="355" height="250"
                                    onmouseover="paintTtd(this.id)"></canvas>
                            <input oninput="$(this).css('border', '')" class="form-control nama_petugas" id="nama_dokter" placeholder="Nama Terang">
                            <input oninput="$(this).css('border', '')" class="form-control nip_petugas" id="sip_dokter" style="margin-top: 5px" placeholder="SIP/NIP">
                            <button style="margin-top: 5px" type="button" class="btn btn-danger"
                                    onclick="removePaint('ttd_dokter')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                    </div>
                    <hr class="garis">
                    <div class="row" style="margin-top: -30px">
                        <div class="col-md-offset-4 col-md-4 text-center">
                            <a href="initForm_radiologi.action" style="margin-top: 25px" class="btn btn-warning"><i
                                    class="fa fa-times"></i> Cancel</a>
                            <button onclick="conSavePeriksaLab()" style="margin-top: 25px" class="btn btn-success">
                                <i class="fa fa-check"></i> Selesai
                            </button>
                        </div>
                    </div>
                    <br>
                </div>
            </div>
        </div>
    </section>
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
                        class="fa fa-check"></i> Save
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
                        <label class="col-md-3" style="margin-top: 7px">Jenis Pemeriksaan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="width: 100%"
                                    id="jenis_pemeriksaan"
                                    onchange="inputWarning('war_jenis_pemeriksaan', 'cor_jenis_pemeriksaan'); listSelectParameter(this.value)">
                                <option value=''>-</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_jenis_pemeriksaan"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_jenis_pemeriksaan">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Parameter</label>
                        <div class="col-md-7">
                            <select class="form-control select2" multiple style="margin-top: 7px; width: 100%"
                                    id="lab_parameter"
                                    onchange="var warn =$('#war_param').is(':visible'); if (warn){$('#cor_param').show().fadeOut(3000);$('#war_param').hide()}">
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
                <div class="row" style="margin-top: 10px">
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-9">
                            <button onclick="addOrderListPemeriksaan()" class="btn btn-success"><i class="fa fa-plus"></i> Tambah</button>
                            <button onclick="resetOrderPemeriksaan()" class="btn btn-danger"><i class="fa fa-refresh"></i> Reset</button>
                        </div>
                    </div>
                </div>
                <hr class="garis">
                <div class="row">
                    <div class="form-group">
                        <div class="col-md-12">
                            <table class="table table-bordered" style="font-size: 13px" id="tabel_order_pemeriksaan">
                                <thead>
                                <tr>
                                    <td>Jenis Pemeriksaan</td>
                                    <td>Parameter</td>
                                    <td>Action</td>
                                </tr>
                                </thead>
                                <tbody id="body_order_pemeriksaan">
                                </tbody>
                            </table>
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

<div id="modal-temp"></div>

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
                    <p id="msg_tindakan"></p>
                </div>
                <div class="alert alert-info alert-dismissible" style="display: none" id="warning_konsul">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p id="msg_konsul"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3">Dokter</label>
                        <div class="col-md-7">
                            <input class="form-control nama_dokter" id="tin_id_dokter_dpjp" oninput="var warn =$('#war_dpjp').is(':visible'); if (warn){$('#cor_dpjp').show().fadeOut(3000);$('#war_dpjp').hide()}">
                            <input type="hidden" class="sip_dokter" id="h_id_dokter_dpjp">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_dpjp"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_dpjp"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Kategori Tindakan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                    id="tin_id_ketgori_tindakan"
                                    onchange="listSelectTindakan(this.value); var warn =$('#war_kategori').is(':visible'); if (warn){$('#cor_kategori').show().fadeOut(3000);$('#war_kategori').hide()}">
                                <option value=''>[Select One]</option>
                            </select>
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
                                    onchange="var warn =$('#war_tindakan').is(':visible'); if (warn){$('#cor_tindakan').show().fadeOut(3000);$('#war_tindakan').hide()}; setDiskonHarga(this.value)">
                                <option value=''> - </option>
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
                        <label class="col-md-3" style="margin-top: 7px">Diskon (%)</label>
                        <div class="col-md-7">
                            <input style="margin-top: 7px" class="form-control" readonly id="h_diskon">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Harga (Rp.)</label>
                        <div class="col-md-3">
                            <input style="margin-top: 7px" class="form-control" readonly id="h_harga">
                        </div>
                        <div class="col-md-1">
                            <i class="fa fa-arrow-right" style="margin-top: 15px"></i>
                        </div>
                        <div class="col-md-3">
                            <input style="margin-top: 7px" class="form-control" readonly id="h_harga_after">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah</label>
                        <div class="col-md-7">
                            <input type="number" min="1" class="form-control" style="margin-top: 7px" id="tin_qty"
                                   oninput="$(this).css('border','')" onchange="$(this).css('border','')" value="1">
                        </div>
                    </div>
                    <input type="hidden" id="is_edit">
                    <input type="hidden" id="is_elektif">
                    <div class="form-group" style="display: none" id="form_elektif">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah Jam</label>
                        <div class="col-md-7">
                            <input type="number" min="1" class="form-control" style="margin-top: 7px" id="tin_qty_elektif"
                                   oninput="$(this).css('border','')" onchange="$(this).css('border','')">
                        </div>
                    </div>
                    <div class="form-group" id="form-btn-add" style="display: none">
                        <div class="col-md-offset-3 col-md-9">
                            <button onclick="addToListTindakan()" class="btn btn-success"><i class="fa fa-plus"></i> Tambah</button>
                            <button onclick="resetListTindakan()" class="btn btn-danger"><i class="fa fa-refresh"></i> Reset</button>
                        </div>
                    </div>
                    <div class="form-group" id="form-list" style="display: none">
                        <label class="col-md-12">
                            <table id="table_list_tindakan" class="table table-bordered table-hover" style="font-size: 12px; margin-top: 20px; width: 100%">
                                <thead>
                                <tr>
                                    <td>Dokter</td>
                                    <td>Tindakan</td>
                                    <td align="center">Qty</td>
                                    <td align="right">Tarif (Rp.)</td>
                                    <td align="right">Total (Rp.)</td>
                                    <td align="center">Action</td>
                                </tr>
                                </thead>
                                <tbody id="body_temp_tindakan">

                                </tbody>
                            </table>
                        </label>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_tindakan"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_tindakan">
                    <i class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-confirm-rm">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Konfirmasi
                </h4>
            </div>
            <div class="modal-body">
                <h4 class="text-center" id="tanya"></h4>
                <h4 class="text-center" id="print_form"></h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Tidak
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con_rm"><i class="fa fa-check"></i> Ya            </button>
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

<script type='text/javascript' src='<s:url value="/dwr/interface/AsesmenRawatInapAction.js"/>'></script>
<script type='text/javascript' src='<s:url value="/dwr/interface/EdukasiPasienAction.js"/>'></script>

<script type='text/javascript' src='<s:url value="/pages/dist/js/paintTtd.js"/>'></script>
<script type='text/javascript' src='<s:url value="/pages/dist/js/asesmenrawatinap.js"/>'></script>
<script type='text/javascript'>

    var idHeaderPemeriksaan = '<s:property value="periksaLab.idHeaderPemeriksaan"/>';
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
    var jenisKelamin = '<s:property value="periksaLab.jenisKelamin"/>';
    var isReadRM = false;
    var namaPasien = "";
    var contextPath = '<%= request.getContextPath() %>';
    var jenisPeriksaPasien = '<s:property value="periksaLab.idJenisPeriksa"/>';
    var idRuangan = '<s:property value="periksaLab.idRuangan"/>';

    $(document).ready(function () {
        $('#penunjang_active, #periksa_radiologi').addClass('active');
        $('#penunjang_open').addClass('menu-open');
        listSelectDokter();

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

        if (keterangan == "Y") {
            $('#btn-add-parameter').show();
        } else {
            $('#btn-add-parameter').hide();
        }

        var isLuar = '<s:property value="periksaLab.isPeriksaLuar"/>';
        if ("Y" == isLuar) {
            getlistParams();
            $('#form_hasil_lab_title, #form_ttd_pemeriksa').hide();
            $('#form_lab_luar_title').show();
            $('#form_hasil_lab_isi').hide();
            $('#tabel_radiologi').hide();
            $('#form_params').show();
        } else {
            listRadiologi();
            $('#form_hasil_lab_title, #form_ttd_pemeriksa').show();
            $('#form_lab_luar_title').hide();
            $('#form_hasil_lab_isi').show();
            $('#tabel_radiologi').show();
            $('#form_params').hide();
        }

        $('.carousel').carousel({
            interval: false,
            ride: false,
            pause: false
        });

        cekLogin();
        listTindakan();
    });

    function showModal(select) {
        if (select == 1) {
            $('#body_order_pemeriksaan').html("");
            $('#jenis_pemeriksaan, #lab_lab, #lab_parameter').val('').trigger('change');
            $('#save_lab').show();
            $('#load_lab, #warning_lab').hide();
            $('#lab_kategori, #lab_lab').css('border', '');
            getJenisPemeirksaan();
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
        if (ref == 3) {
            $('html, body').animate({
                scrollTop: $('#pos_tindakan').offset().top
            }, 100);
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
                        listRadiologi();
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
        var data = $('#tabel_order_pemeriksaan').tableToJSON();
        if (data.length > 0) {
            var namaPemeriksaan = $('.nama_order_jenis_pemeriksaan');
            var idPemeriksaan = $('.id_order_jenis_pemeriksaan');
            var parameterPemeriksaan = $('.nama_order_parameter_pemeriksaan');
            var idParameter = $('.id_order_parameter_pemeriksaan');

            var pemeriksan = [];
            $.each(namaPemeriksaan, function (i, item) {
                if(item.value){
                    var tempItem = [];
                    var tmep = parameterPemeriksaan[i].value;
                    var imep = idParameter[i].value;
                    if(tmep != '' && imep != ''){
                        var pp = tmep.split("#");
                        var mp = imep.split("#");
                        $.each(pp, function (i, item) {
                            tempItem.push({
                                'id_parameter': mp[i],
                                'nama_parameter': item
                            })
                        });
                    }
                    pemeriksan.push({
                        'id_pemeriksaan': idPemeriksaan[i].value,
                        'nama_pemeriksaan': item.value,
                        'list_parameter': JSON.stringify(tempItem)
                    });
                }
            });

            var result = JSON.stringify(pemeriksan);

            $('#save_lab').hide();
            $('#load_lab').show();
            dwr.engine.setAsync(true);
            PeriksaLabAction.saveUpdatePemeriksaan(idHeaderPemeriksaan, result, {
                callback: function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        listRadiologi();
                        $('#modal-lab').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(2);
                        $('body').scrollTop(0);
                        $('#save_lab').show();
                        $('#load_lab').hide();
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
        }
    }

    function listRadiologi() {
        PeriksaLabAction.listParameterPemeriksaan(idHeaderPemeriksaan, function (response) {
            if (response.length > 0) {
                var table = "";
                var asesmen = "N";

                $.each(response, function (i, item) {
                    var nomor = i + 1;
                    var margin = "";
                    if (i > 0) {
                        margin = 'style="margin-top: 50px"';
                    }

                    var pemeriksaan = "";
                    if(i == 0){
                        pemeriksaan = item.namaPemeriksaan;
                    }else{
                        if(response[i-1]["namaPemeriksaan"].toLowerCase() == item.namaPemeriksaan.toLowerCase()){
                            pemeriksaan = "";
                        }else{
                            pemeriksaan = item.namaPemeriksaan;
                        }
                    }

                    var btn = '<a class="btn btn-success" onclick="savePemeriksaan(\'' + item.idPeriksaLabDetail + '\', \'keterangan_' + i + '\', \'' + item.namaDetailPemeriksaan + '\')"><i class="fa fa-check"></i> Save Hasil</a>';
                    var isi = '<ul>\n' +
                        '      <li><strong>Hasil</strong></li>\n' +
                        '        </ul>\n' +
                        '        <p>&nbsp;</p>\n' +
                        '        <p>&nbsp;</p>\n' +
                        '        <ul>\n' +
                        '            <li><strong>Kesimpulan</strong></li>\n' +
                        '        </ul>\n';
                    var status = "N";
                    var dis = 'disabled="false"';
                    if(item.hasil != '' && item.hasil != null){
                        btn = '<a class="btn btn-warning" onclick="changeHasil(\''+item.idPeriksaLabDetail+'\', \'keterangan_'+i+'\', \''+item.namaDetailPemeriksaan+'\')"><i class="fa fa-edit"></i> Edit Hasil</a>';
                        isi = item.hasil;
                        status = "Y";
                        dis = 'disabled="true"';
                    }

                    table += '<div class="row" ' + margin + '>\n' +
                        '<div class="col-md-12">\n' +
                        '    <h4>' + pemeriksaan + '</h4>\n' +
                        '    <h4>' + nomor + '. ' + item.namaDetailPemeriksaan + '</h4>\n' +
                        '<textarea class="editors" id="keterangan_' + i + '" rows="30">' + isi +
                        '</textarea>' +
                        '</div>' +
                        '<input type="hidden" id="h_'+item.idPeriksaLabDetail+'" class="status_selesai" value="'+status+'">'+
                        '</div>' +
                        '<div class="row" style="margin-top: 10px">\n' +
                        '<div class="col-md-6">\n' +
                        '    <div class="row">\n' +
                        '        <div class="form-group">\n' +
                        '            <div class="col-md-10">\n' +
                        '                <div class="input-group">\n' +
                        '            <span class="input-group-btn">\n' +
                        '                <span class="btn btn-default btn-file">\n' +
                        '                     Browse… <input accept="image/*" id="hasil_lab_'+i+'" \n' +
                        '        onchange="parseToByte(\'hasil_lab_' + i + '\', \'label_hasil_lab_' + i + '\', \'hasil_lab'+i+'\', \'' + item.idPeriksaLabDetail + '\', \''+item.namaDetailPemeriksaan+'\', \'dalam\')"\n' +
                        '        type="file">\n' +
                        '                </span>\n' +
                        '            </span>\n' +
                        '                    <input style="margin-top: 7px" type="text" class="form-control" readonly id="label_hasil_lab_' + i + '">\n' +
                        '                </div>\n' +
                        '            </div>\n' +
                        '            <div class="col-md-2">\n' +
                        '                <button onclick="addUpload(\'hasil_lab' + i + '\', \'set_hasil' + i + '\', \''+item.idPeriksaLabDetail+'\', \''+item.namaDetailPemeriksaan+'\', \'dalam\')"\n' +
                        '                        class="btn btn-success" style="margin-left: -20px; margin-top: 9px">\n' +
                        '                    <i class="fa fa-plus"></i></button>\n' +
                        '            </div>\n' +
                        '        </div>\n' +
                        '    </div>\n' +
                        '    <div id="set_hasil' + i + '">\n' +
                        '    </div>\n' +
                        '    <div class="row top_jarak">\n' +
                        '        <div class="col-md-12">\n' +
                        '<div id="btn_save_'+item.idPeriksaLabDetail+'">' + btn +
                        '</div>'+
                        '        </div>\n' +
                        '    </div>\n' +
                        '</div>\n' +
                        '</div>';

                    if("Y" == item.isAsesmen){
                        asesmen = "Y";
                    }
                });
                $('#body_parameter').html(table);
                $('.editors').each(function () {
                    CKEDITOR.replace($(this).attr('id'));
                });
                $('#asesmen_radiolgi').show();
                // if("Y" == asesmen){
                //     $('#asesmen_radiolgi').show();
                // }else{
                //     $('#asesmen_radiolgi').hide();
                // }
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
        var data = $('.status_selesai');
        var totalTarif = $('#h_total_tarif').val();
        var cekIsKeluar = '<s:property value="periksaLab.isPeriksaLuar"/>';

        var cek = false;
        $.each(data, function (i, item) {
            if (item.value == "N") {
                cek = true;
            }
        });

        var tempHasilLuar = $('.hasil_luar');
        var cekLabLuar = false;
        $.each(tempHasilLuar, function (i, item) {
            var canvas = document.getElementById('hasil_luar_' + i).files;
            if (canvas.length > 0) {
                cekLabLuar = true;
            }
        });

        if ("Y" == cekIsKeluar) {
            if (cekLabLuar) {
                $('#modal-confirm-dialog').modal('show');
                $('#save_con').attr('onclick', 'savePeriksaLab()');
            } else {
                $('#warning_rad').show().fadeOut(5000);
                $('#msg_rad').text("Silahkan cek kembali data hasil Radiologi...!");
                $(window).scrollTop($("#pos_lab").offset().top);
            }
        } else {
            if (!cek) {
                var dokter = document.getElementById("ttd_dokter");
                var cekDokter = isCanvasBlank(dokter);
                var nama = $('#nama_dokter').val();
                var sip = $('#sip_dokter').val();
                if(!cekDokter && nama && sip != ''){
                    $('#modal-confirm-dialog').modal('show');
                    $('#save_con').attr('onclick', 'savePeriksaLab()');
                }else{
                    $('#warning_rad').show().fadeOut(5000);
                    $('#msg_rad').text("Silahkan ttd terlebih dahulu dan masukkan nama dan sip...!");
                    $(window).scrollTop($("#pos_lab").offset().top);
                }
            } else {
                $('#warning_rad').show().fadeOut(5000);
                $('#msg_rad').text("Silahkan cek kembali data hasil Radiologi, data petugas, dan data Validator...!");
                $(window).scrollTop($("#pos_lab").offset().top);
            }
        }
    }

    function savePeriksaLab() {
        $('#modal-confirm-dialog').modal('hide');
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
        var isiParam = $('#tabel_radiologi').tableToJSON();
        var totalTarif = "";
        var cekIsKeluar = '<s:property value="periksaLab.isPeriksaLuar"/>';
        var tempDataFinal = "";
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

        if(cekIsKeluar == "Y"){
            tempDataFinal = {
                'id_header_pemeriksaan': idHeaderPemeriksaan,
                'keterangan': keterangan,
                'data': JSON.stringify(data),
                'total_tarif': totalTarif
            }
        }else{
            var dokter = document.getElementById("ttd_dokter");
            var cekDokter = isCanvasBlank(dokter);
            var dokterFinal = convertToDataURL(dokter);
            var nama = $('#nama_dokter').val();
            var sip = $('#sip_dokter').val();
            tempDataFinal = {
                'id_header_pemeriksaan': idHeaderPemeriksaan,
                'keterangan': keterangan,
                'data': JSON.stringify(data),
                'nama_petugas': nama,
                'nip_petugas': sip,
                'ttd_petugas': dokterFinal
            }
        }

        var result = JSON.stringify(tempDataFinal);
        $('#waiting_dialog').dialog('open');
        dwr.engine.setAsync(true);
        PeriksaLabAction.selesaiPemeriksaan(result, {
            callback: function (response) {
                if (response.status == "success") {
                    $('#waiting_dialog').dialog('close');
                    $('#save_ket').show();
                    $('#load_ket').hide();
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(1);
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

    function printPeriksaLab() {
        window.open('printRadiologi_radiologi.action?id=' + idDetailCheckup + '&lab=' + idHeaderPemeriksaan + '&ket=label', "_blank");
    }

    function addUpload(jen, idset, idDetail, namaDetail, tipe) {
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
            '         Browse… <input class="'+jen+'" accept="image/*" id="'+jenis+'" onchange="parseToByte(\'' + jenis + '\', \'' + label + '\', \''+idRow+'\', \''+idDetail+'\', \''+namaDetail+'\', \''+tipe+'\')" type="file">\n' +
            '    </span>\n' +
            '</span>\n' +
            '            <input style="margin-top: 7px" type="text" class="form-control" readonly id="' + label + '">\n' +
            '        </div>\n' +
            '    </div>\n' +
            '    <div class="col-md-2">\n' +
            '        <button id="'+idRow+'" onclick="delUpload(\'' + idRow + '\')" class="btn btn-danger" style="margin-left: -20px; margin-top: 9px"><i class="fa fa-trash"></i></button>\n' +
            '    </div>\n' +
            '</div>\n' +
            '</div>';
        $('#' + idset).append(set);
    }

    function delUpload(id, idDetail) {
        $('#' + id).remove();
        if(idDetail != ''){
            if(!cekSession()){
                dwr.engine.setAsync(true);
                PeriksaLabAction.deleteUploadFilePemeriksaan(idDetail, {
                    callback: function (res) {
                    }
                });
            }
        }
    }

    function viewUpload(jenis) {
        if (jenis == 'hasil_lab') {
            $('#title_' + jenis).html("View Radiologi Hasil Radiologi");
        } else {
            $('#title_' + jenis).html("View Radiologi Hasil Radiologi Luar");
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

    function getlistParams() {
        PeriksaLabAction.listParameterPemeriksaan(idHeaderPemeriksaan, function (response) {
            if (response.length > 0) {
                var set = "";
                $.each(response, function (i, item) {
                    set += '<li>' + item.namaDetailPemeriksaan + '</li>';
                });
                $('#params').html(set);
            }
        });
    }

    function savePemeriksaan(id, text, namaPeriksa) {
        var hasil = CKEDITOR.instances[text].getData();
        var data = {
            'id_header_pemeriksaan': idHeaderPemeriksaan,
            'id_periksa_detail': id,
            'nama_periksa_detail': namaPeriksa,
            'hasil': hasil
        }

        var result = JSON.stringify(data);
        $('#btn_save_'+id).html('<i class="fa fa-circle-o-notch fa-spin"></i> Sedang menyimpan...');
        dwr.engine.setAsync(true);
        PeriksaLabAction.saveDetailParameter(result, {
            callback: function (res) {
                if(res.status == "success"){
                    $('#btn_save_'+id).html('<a class="btn btn-warning" onclick="changeHasil(\''+id+'\', \''+text+'\', \''+namaPeriksa+'\')"><i class="fa fa-edit"></i> Edit Hasil</a>');
                    $('#h_'+id).val("Y");
                    CKEDITOR.instances[text].setReadOnly(true);
                    // listRadiologi();
                }else{
                    $('#warning_ttd').show().fadeOut(5000);
                    $('#msg_ttd').text(res.msg);
                }
            }
        });
    }

    function changeHasil(id, text, namaPeriksa){
        CKEDITOR.instances[text].setReadOnly(false);
        $('#btn_save_'+id).html('<a class="btn btn-success" onclick="savePemeriksaan(\''+id+'\', \''+text+'\', \''+namaPeriksa+'\')"><i class="fa fa-check"></i> Save Hasil</a>');
    }

    function conRadioLogi(){
        $('#modal-confirm-dialog').modal('show');
        $('#save_con').attr('onclick', 'saveSelesaiRadiologi()');
    }

    function saveSelesaiRadiologi(){
        if(!cekSession()){
            $('#modal-confirm-dialog').modal('hide');
            $('#waiting_dialog').dialog('open');
            dwr.engine.setAsync(true);
            PeriksaLabAction.saveSelesaiRadiologi(idPeriksaLab, {
                callback: function (res) {
                    if(res.status == "success"){
                        $('#waiting_dialog').dialog('close');
                        $('#save_ket').show();
                        $('#load_ket').hide();
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(1);
                        $('body').scrollTop(0);
                    }else{
                        $('#save_ket').show();
                        $('#load_ket').hide();
                        $('#waiting_dialog').dialog('close');
                        $('#warning_rad').show().fadeOut(5000);
                        $('#msg_rad').text(response.message);
                    }
                }
            });
        }
    }

    function parseToByte(id, label, idRow, idPerikDetail, namaDetail, tipe) {
        if(!cekSession()){
            if(tipe == "luar"){
                namaDetail = label;
            }
            var files = document.getElementById(id).files;
            if (files.length > 0) {
                var fileToLoad = files[0];
                var fileReader = new FileReader();
                var base64File;
                fileReader.onload = function(event) {
                    base64File = event.target.result;
                    var eks = cekEks(base64File);
                    var base = replaceFile(base64File);
                    var data = {
                        'id_header_pemeriksaan': idHeaderPemeriksaan,
                        'byte': base,
                        'id_periksa_detail': idPerikDetail,
                        'nama_periksa': namaDetail,
                        'tipe': tipe,
                        'eks': eks,
                        'file_name': id
                    }
                    if(eks == "pdf" || eks == "jpg" || eks == "png"){
                        var result = JSON.stringify(data);
                        $('#'+label).val("Sedang menyimpan...");
                        dwr.engine.setAsync(true);
                        PeriksaLabAction.uploadFilePemeriksaan(result, {
                            callback: function (res) {
                                if(res.status == "success"){
                                    $('#'+label).val(files[0].name);
                                    $('#'+label).css('border-bottom','solid 5px #5cb85c');
                                    $('#del_'+idRow).attr('onclick', 'delUpload(\''+idRow+'\',\''+res.msg+'\')')
                                }else{
                                    $('#'+label).val(res.msg);
                                    $('#'+label).css('border-bottom','solid 5px #c9302c');
                                }
                            }
                        });
                    }else{
                        $('#'+label).val("File harus .jpg, .png, .pdf");
                        $('#'+label).css('border-bottom','solid 5px #c9302c');
                    }
                };
                fileReader.readAsDataURL(fileToLoad);
            }
        }
    }

    function replaceFile(byte){
        var conditon = byte.split(",")[0]+',';
        var res = byte.replace(conditon, "");
        return res;
    }

    function cekEks(byte){
        var res = "";
        var conditon = byte.split(",")[0];
        if(conditon == "data:image/jpeg;base64"){
            res = "jpg";
        }else if(conditon == "data:image/png;base64"){
            res = "png";
        }else if(conditon == "data:application/pdf;base64"){
            res = "pdf";
        }
        return res;
    }

    function addOrderListPemeriksaan(){
        var count = $('#tabel_order_pemeriksaan').tableToJSON().length;
        var namaPemeriksaan = $('#jenis_pemeriksaan option:selected').text();
        var idPemeriksaan = $('#jenis_pemeriksaan').val();

        var namaParameter = $('#lab_parameter option:selected');
        var idParameter = $('#lab_parameter').val();

        var tempPemeriksaan = "";
        var tempIdPemeriksan= "";

        var tempParameter = "";
        var tempIdParameter = "";
        var tempParameterLi = "";

        if(idPemeriksaan != ''){
            tempPemeriksaan = namaPemeriksaan;
            tempIdPemeriksan = idPemeriksaan;
        }
        if(idParameter != '' && idParameter != null){
            $.each(namaParameter, function (i, item) {
                if(tempIdParameter != ''){
                    tempIdParameter = tempIdParameter+'#'+item.value;
                }else{
                    tempIdParameter = item.value;
                }

                if(tempParameter != ''){
                    tempParameter = tempParameter+'#'+item.innerHTML;
                }else{
                    tempParameter = item.innerHTML;
                }
                tempParameterLi += '<li>'+item.innerHTML+'</li>';
            });
        }

        if(tempPemeriksaan && tempParameter != ''){
            var cek = false;
            $.each($('.nama_order_jenis_pemeriksaan'), function (i, item) {
                if(item.value != ''){
                    if(item.value.toLowerCase() == tempPemeriksaan.toLowerCase()){
                        cek = true;
                    }
                }
            });
            if(!cek){
                var row =
                    '<tr id="row_'+count+'">' +
                    '<td>'+
                    tempPemeriksaan +
                    '<input type="hidden" class="nama_order_jenis_pemeriksaan" value="'+tempPemeriksaan+'">'+
                    '<input type="hidden" class="id_order_jenis_pemeriksaan" value="'+tempIdPemeriksan+'">'+
                    '<input type="hidden" class="nama_order_parameter_pemeriksaan" value="'+tempParameter+'">'+
                    '<input type="hidden" class="id_order_parameter_pemeriksaan" value="'+tempIdParameter+'">'+
                    '</td>'+
                    '<td><ul style="margin-left: 20px">'+tempParameterLi+'</ul></td>'+
                    '<td align="center"><img onclick="delOrderPemeriksaan(\'row_'+count+'\')" style="cursor: pointer" src="'+contextPathHeader+'/pages/images/cancel-flat-new.png" class="hvr-row"></td>'+
                    '</tr>';
                $('#body_order_pemeriksaan').append(row);
                $('#jenis_pemeriksaan').val(null).trigger('change');
                $('#lab_parameter').val(null).trigger('change');
            }else{
                $('#warning_lab').show().fadeOut(5000);
                $('#msg_lab').text("Pemeriksaan "+tempPemeriksaan+" sudah ada dalam ist");
            }
        }else{
            $('#warning_lab').show().fadeOut(5000);
            $('#msg_lab').text("Silahkan cek kembali inputan berikut...!");
            if (idPemeriksaan == '') {
                $('#war_jenis_pemeriksaan').show();
            }
            if (idParameter == '' || idParameter == null) {
                $('#war_param').show();
            }
        }
    }

    function delOrderPemeriksaan(id){
        $('#'+id).remove();
        var table = $('#tabel_order_pemeriksaan').tableToJSON().length;
        if(table == 0){
            $('#ckp_kategori').attr('disabled', false);
        }
    }

    function resetOrderPemeriksaan(){
        $('#body_order_pemeriksaan').html('');
        $('#ckp_kategori').attr('disabled', false);
        $('#ckp_kategori').val(null).trigger('change');
        $('#ckp_unit').val(null).trigger('change');
        $('#ckp_parameter').val(null).trigger('change');
    }

    function getJenisPemeirksaan(){
        var option = '<option value="">-</option>';
        PeriksaLabAction.getPemeriksaanById(idHeaderPemeriksaan ,function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option += '<option value="'+item.idPemeriksaan+'">'+item.namaPemeriksaan+'</option>';
                });
            }
            $('#jenis_pemeriksaan').html(option);
        });
    }

    function cekLogin() {
        CheckupAction.cekLogin({
            callback: function (res) {
                if (res != '') {
                    $('.nama_petugas').val(res.msg);
                    $('.nip_petugas').val(res.status);

                    $('.nama_dokter').val(res.msg);
                    $('.sip_dokter').val(res.status);
                }
            }
        });
    }

    function loadModalRM(jenis, method, parameter, idRM, flag, flagHide, flagCheck) {
        var context = contextPathHeader + '/pages/modal/modal-default.jsp';
        if (jenis != "") {
            context = contextPathHeader + '/pages/modal/modal-'+jenis+'.jsp';
        }
        $('#modal-temp').load(context, function (res, status, xhr) {
            if(status == "success"){
                var func = new Function(method+'(\''+parameter+'\', \''+idRM+'\', \''+flag+'\', \''+flagHide+'\', \''+flagCheck+'\')');
                func();
            }
        });
    }

    function getListRekamMedis() {
        var li = "";
        var jenisRm = "";
        CheckupAction.getListRekammedisPasien("radiologi", jenisRm, idDetailCheckup, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    var cek = "";
                    var tgl = "";
                    var icons = '<i class="fa fa-file-o"></i>';
                    var icons2 = '<i class="fa fa-print"></i>';
                    var tol = "";
                    var tolText = "";
                    var labelTerisi = "";
                    var constan = 0;
                    var terIsi = 0;
                    var labelPrint = "";
                    var terIsiPrint = "";
                    var enter = '';

                    if (item.jumlahKategori != null) {
                        constan = item.jumlahKategori;
                    }
                    if (item.terisi != null && item.terisi != '') {
                        terIsi = item.terisi;
                        terIsiPrint = item.terisi;
                    }

                    if (constan == terIsi || parseInt(terIsi) > parseInt(constan)) {
                        var conver = "";
                        if (item.createdDate != null) {
                            conver = converterDate(new Date(item.createdDate));
                            tgl = '<label class="label label-success">' + conver + '</label>';
                            tol = 'class="box-rm"';
                            tolText = '<span class="box-rmtext">Tanggal mengisi ' + conver + '</span>';
                        }
                        icons = '<i class="fa fa-check" style="color: #449d44"></i>';
                        icons2 = '<i class="fa fa-check" style="color: #449d44"></i>';
                        enter = '<br>';
                    }

                    labelTerisi = '<span style="color: #367fa9; font-weight: bold">' + terIsi + '/' + constan + '</span>';
                    labelPrint = '<span style="color: #367fa9; font-weight: bold">' + terIsiPrint + '</span>';

                    if (item.jenis == 'ringkasan_rj') {
                        li += '<li><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')' + '"><i class="fa fa-television"></i>' + item.namaRm + '</a></li>'
                    } else {
                        if (item.function == 'addMonitoringFisioterapi') {
                            li += '<li><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')' + '"><i class="fa fa-television"></i>' + item.namaRm + '</a></li>'
                        } else {
                            if (item.keterangan == 'form') {
                                li += '<li ' + tol + '><a style="cursor: pointer" onclick="loadModalRM(\'' + item.jenis + '\', \'' + item.function + '\', \'' + item.parameter + '\', \'' + item.idRekamMedisPasien + '\', \'Y\')">' + icons + item.namaRm + ' ' + labelTerisi + tolText + '</a></li>' + enter;
                            } else if (item.keterangan == "surat") {
                                li += '<li ' + tol + '><a style="cursor: pointer" onclick="' + item.function + '(\'' + item.jenis + '\', \'' + item.idRekamMedisPasien + '\', \'Y\',\'' + item.namaRm + '\')' + '">' + icons2 + item.namaRm + ' ' + labelPrint + tolText + '</a></li>' + enter;
                            }
                        }
                    }
                });
                $('#asesmen_rj').html(li);
            }
        });
    }

    function showTindakan(){
        listSelectTindakanKategori();
        setDataPasien();
        cekLogin();
        var id = '';
        $('#form-btn-add, #form-list').show();
        $('#h_harga, #h_diskon').val(null);
        $('#is_edit').val("N");
        $('#form_elektif').hide();
        $('#tin_id_ketgori_tindakan, #tin_id_tindakan, #tin_id_perawat').val('').trigger('change');
        $('#tin_qty').val('1');
        $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
        $('#save_tindakan').attr('onclick', 'saveTindakan(\'' + id + '\')').show();
        $('#modal-tindakan').modal({show: true, backdrop: 'static'});
    }

    function saveTindakan(id) {
        var idKategori = $('#tin_id_ketgori_tindakan').val();
        var idTindakan = $('#tin_id_tindakan').val();
        var idDokter = $('#h_id_dokter_dpjp').val();
        var qty = $('#tin_qty').val();
        var idJenisPeriksa = $('#jenis_pasien').val();
        var idDok = "";
        var idPelayanan = "";
        var count = $('#table_list_tindakan').tableToJSON().length;
        if(id != ''){
            if (idDetailCheckup != '' && idTindakan != '' && idTindakan != null && idDokter != '' && qty > 0 && idKategori != '' && idKategori != null) {
                if (!cekSession()) {
                    idDok = idDokter.split("|")[0];
                    idPelayanan = idDokter.split("|")[1];
                    $('#save_tindakan').hide();
                    $('#load_tindakan').show();
                    dwr.engine.setAsync(true);
                    TindakanRawatAction.editTindakanRawat(id, idDetailCheckup, idTindakan, idDok, "RJ", qty, jenisPeriksaPasien, idPelayanan, {
                        callback: function (response) {
                            if (response.status == "success") {
                                dwr.engine.setAsync(false);
                                listTindakan();
                                $('#modal-tindakan').modal('hide');
                                $('#info_dialog').dialog('open');
                                $('#close_pos').val(3);
                                $('#save_tindakan').show();
                                $('#load_tindakan').hide();
                            } else {
                                $('#warning_tindakan').show().fadeOut(5000);
                                $('#msg_tindakan').text(response.msg);
                                $('#save_tindakan').show();
                                $('#load_tindakan').hide();
                            }
                        }
                    });
                }
            } else {
                $('#warning_tindakan').show().fadeOut(5000);
                $('#msg_tindakan').text('Silahkan cek kembali data inputan dan jumlah harus lebih dari 0..!');

                if (idDokter == '') {
                    $('#war_dpjp').show();
                }
                if (idKategori == '' || idKategori == null) {
                    $('#war_kategori').show();
                }
                if (idTindakan == '' || idTindakan == null) {
                    $('#war_tindakan').show();
                }
                if (qty <= 0 || qty == '') {
                    $('#tin_qty').css('border', 'red solid 1px');
                }
            }
        }else{
            if(count > 0){
                var idDokter = $('.tindakan_dokter');
                var idPelayanan = $('.tindakan_id_pelayanan');
                var idTindakan = $('.tindakan_id_tindakan');
                var qty = $('.tindakan_qty');
                var data = [];
                $.each(idDokter, function (i, item) {
                    data.push({
                        'id_detail_checkup': idDetailCheckup,
                        'id_tindakan': idTindakan[i].value,
                        'id_dokter': item.value,
                        'qty': qty[i].value,
                        'jenis_pasien': jenisPeriksaPasien,
                        'id_pelayanan': idPelayanan[i].value,
                        'id_ruangan': idRuangan,
                        'is_pelayanan': 'N'
                    });
                });
                $('#save_tindakan').hide();
                $('#load_tindakan').show();
                var result = JSON.stringify(data);
                dwr.engine.setAsync(true);
                TindakanRawatAction.saveTindakanRawat(result, {
                    callback: function (response) {
                        if (response.status == "success") {
                            dwr.engine.setAsync(false);
                            listTindakan();
                            $('#modal-tindakan').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#close_pos').val(3);
                            $('#save_tindakan').show();
                            $('#load_tindakan').hide();
                        } else {
                            $('#warning_tindakan').show().fadeOut(5000);
                            $('#msg_tindakan').text(response.msg);
                            $('#save_tindakan').show();
                            $('#load_tindakan').hide();
                        }
                    }
                });
            }else{
                $('#warning_tindakan').show().fadeOut(5000);
                $('#msg_tindakan').text("Silahkan cek kembali inputan tindakan...!");
            }
        }
    }

    function addToListTindakan(id) {
        var idKategori = $('#tin_id_ketgori_tindakan').val();
        var idTindakan = $('#tin_id_tindakan').val();
        var namaTindakan = $('#tin_id_tindakan option:selected').text();
        var idDokter = $('#h_id_dokter_dpjp').val();
        var namaDokter = $('#tin_id_dokter_dpjp').val();
        var qty = $('#tin_qty').val();
        var tarif = $('#h_harga').val();
        var total = $('#h_harga_after').val();
        var count = $('#table_list_tindakan').tableToJSON().length;

        if (idDetailCheckup != '' && idTindakan != '' && idTindakan != null && idDokter != '' && qty > 0 && idKategori != '' && idKategori != null) {
            if (!cekSession()) {
                var idPelayanan = "";
                var temp = '<tr id="rowTindakan_'+count+'">' +
                    '<td>'+namaDokter+
                    '<input class="tindakan_dokter" type="hidden" value="'+idDokter+'">'+
                    '<input class="tindakan_id_pelayanan" type="hidden" value="'+idPelayanan+'">'+
                    '<input class="tindakan_id_tindakan" type="hidden" value="'+idTindakan+'">'+
                    '<input class="tindakan_qty" type="hidden" value="'+qty+'">'+
                    '</td>' +
                    '<td>'+namaTindakan+'</td>' +
                    '<td align="center">'+qty+'</td>' +
                    '<td align="right">'+tarif+'</td>' +
                    '<td align="right">'+formatRupiahAtas((parseInt(replaceTitik(total))*qty))+'</td>' +
                    '<td align="center">'+'<img onclick="delListTindakan(\'rowTindakan_'+count+'\')" style="cursor: pointer" src="'+contextPath+'/pages/images/cancel-flat-new.png" class="hvr-row">'+'</td>' +
                    '</tr>';
                if(temp != ''){
                    $('#body_temp_tindakan').append(temp);
                }
            }
        } else {
            $('#warning_tindakan').show().fadeOut(5000);
            $('#msg_tindakan').text('Silahkan cek kembali data inputan dan jumlah harus lebih dari 0..!');

            if (idDokter == '') {
                $('#war_dpjp').show();
            }
            if (idKategori == '' || idKategori == null) {
                $('#war_kategori').show();
            }
            if (idTindakan == '' || idTindakan == null) {
                $('#war_tindakan').show();
            }
            if (qty <= 0 || qty == '') {
                $('#tin_qty').css('border', 'red solid 1px');
            }
        }
    }

    function delListTindakan(id){
        $('#'+id).remove();
    }

    function resetListTindakan(){
        $('#body_temp_tindakan').html('');
        $('#tin_id_tindakan').val('').trigger('change');
        $('#tin_qty').val('1');
        $('#h_harga').val('');
        $('#h_harga_after').val('');
    }

    function listSelectTindakan(idKategori) {
        var option = "<option value=''> - </option>";
        if (idKategori != '') {
            dwr.engine.setAsync(true);
            CheckupDetailAction.getListComboTindakan(idKategori, "", "N", null, {
                callback:function (response) {
                    if (response != null) {
                        $.each(response, function (i, item) {
                            option += "<option value='" + item.idTindakan + "'>" + item.tindakan + "</option>";
                        });
                        $('#tin_id_tindakan').html(option);
                    } else {
                        $('#tin_id_tindakan').html('');
                    }
                }
            });
        } else {
            $('#tin_id_tindakan').html('');
        }
    }

    function setDiskonHarga(id) {
        if (id != '') {
            TindakanAction.initTindakan(id, function (res) {
                if (res.idTindakan != '') {
                    var disk = 0;
                    var tarif = 0;
                    if (res.diskon != '' && res.diskon != null) {
                        disk = res.diskon;
                    }
                    if (jenisPeriksaPasien == "bpjs") {
                        tarif = res.tarifBpjs;
                    } else if (jenisPeriksaPasien == "bpjs_rekanan" || jenisPeriksaPasien == "rekanan"){

                        if (jenisPeriksaPasien == "rekanan"){
                            tarif = res.tarif;
                        } else {
                            tarif = res.tarifBpjs;
                        }

                        TindakanRawatAction.getTarifDetailRekanaOps(idDetailCheckup, id, function (res2) {
                            if  (res2 != null){
                                if (jenisPeriksaPasien == "bpjs_rekanan"){
                                    disk = res2.diskonBpjs;
                                    tarif = res2.tarifBpjs;
                                }

                                if (jenisPeriksaPasien == "rekanan"){
                                    disk = res2.diskonNonBpjs;
                                    tarif = res2.tarif;
                                }
                            }
                        });

                    } else {
                        tarif = res.tarif;
                    }

                    var persen = (100 - disk);
                    var after  = persen/100;
                    var total  = after*tarif;
                    $('#h_harga').val(formatRupiahAtas(tarif));
                    $('#h_harga_after').val(formatRupiahAtas(total));
                    $('#h_diskon').val(disk);

                    if ("Y" == res.isElektif) {
                        $('#form_elektif').show();
                        $('#is_elektif').val("Y");
                    } else {
                        $('#form_elektif').hide();
                        $('#is_elektif').val("N");
                    }

                    if ("Y" == res.flagKonsulGizi) {
                        $('#warning_konsul').fadeIn(1000);
                        $('#msg_konsul').text("Anda memilih tindakan Konsultasi Gizi. Setelah menambahkan tindakan ini, infokan kepada Pasien untuk melakukan Konsultasi Gizi ke Unit Gizi...!");
                    } else {
                        $('#warning_konsul').hide();
                    }
                }
            });
        }
    }

    function listSelectTindakanKategori() {
        var option = '<option value=""> - </option>';
        var def = '';
        var isEdit = $('#is_edit').val();
        CheckupDetailAction.getListComboTindakanKategori("PL", "radiologi", function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    if (i == 0) {
                        def = item.idKategoriTindakan;
                    }
                    option += '<option value="' + item.idKategoriTindakan + '">' + item.kategoriTindakan + '</option>';
                });
                $('#tin_id_ketgori_tindakan').html(option);
                if (isEdit != "Y") {
                    setTimeout(function () {
                        $('#tin_id_ketgori_tindakan').val(def).trigger('change');
                    }, 100);
                }
            } else {
                $('#tin_id_ketgori_tindakan').html('');
            }
        });
    }

    function listTindakan() {
        var table = "";
        var table2 = "";
        var data = [];
        var trfTtl = 0;
        TindakanRawatAction.listTindakanRawat(idDetailCheckup, "N", function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    var tanggal = item.createdDate;
                    var dateFormat = converterDateTime(new Date(tanggal));
                    var tarif = "-";
                    var tarifTotal = "-";
                    var trfTotal = 0;
                    var qtyTotal = 0;
                    var perawat = "";
                    var btn = '<img border="0" class="hvr-grow" onclick="editTindakan(\'' + item.idTindakanRawat + '\',\'' + item.idTindakan + '\',\'' + item.idKategoriTindakan + '\',\'' + item.idPerawat + '\',\'' + item.qty + '\', \'' + item.idDokter + '\', \'' + item.idPelayanan + '\')" src="' + contextPath + '/pages/images/icons8-create-25.png" style="cursor: pointer;">';

                    if (item.tarif != null) {
                        tarif = formatRupiahAtas(item.tarif);
                        trfTotal += item.tarif;
                    }
                    if (item.tarifTotal != null) {
                        tarifTotal = formatRupiahAtas(item.tarifTotal);
                        trfTtl += item.tarifTotal;
                    }
                    if (item.qty != null) {
                        qtyTotal += item.qty;
                    }
                    if (item.idPerawat != null) {
                        perawat = item.idPerawat;
                    }

                    if ("Y" == item.approveFlag) {
                        btn = "";
                    }

                    table += "<tr>" +
                        "<td>" + dateFormat + "</td>" +
                        "<td>" + item.namaTindakan + "</td>" +
                        '<td>' + item.namaDokter + '</td>' +
                        "<td align='right'>" + tarif + "</td>" +
                        "<td align='center'>" + item.qty + "</td>" +
                        "<td align='right'>" + tarifTotal + "</td>" +
                        "<td align='center'>" + btn + "</td>" +
                        "</tr>";

                    table2 += "<tr>" +
                        "<td>" + dateFormat + "</td>" +
                        "<td>" + item.namaTindakan + "</td>" +
                        "<td align='center'></td>" +
                        "</tr>";

                });

                if ("paket_perusahaan" == jenisPeriksaPasien || "paket_individu" == jenisPeriksaPasien) {
                    $('#body_tindakan_paket').html(table2);
                } else {
                    table = table + "<tr>" +
                        "<td colspan='5'>Total</td>" +
                        "<td align='right'>" + formatRupiahAtas(trfTtl) + "</td>" +
                        "<td></td>" +
                        "</tr>";
                    $('#body_tindakan').html(table);
                }
            }
        });

    }

    function editTindakan(id, idTindakan, idKategori, idPerawat, qty, idDokter, idPelayanan) {
        listSelectTindakanKategori();
        $('#form-btn-add, #form-list').hide();
        $('#is_edit').val('Y');
        $('#form_elektif').hide();
        $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
        setDataPasien();
        cekLogin();
        $('#tin_id_ketgori_tindakan').val(idKategori).trigger('change');
        setTimeout(function () {
            $('#tin_id_tindakan').val(idTindakan).trigger('change');
        },500);
        $('#tin_qty').val(qty);
        $('#save_tindakan').attr('onclick', 'saveTindakan(\'' + id + '\')').show();
        $('#modal-tindakan').modal({show: true, backdrop: 'static'});
    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>