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
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LabAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/LabDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PeriksaLabAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PaketPeriksaAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TindakanAction.js"/>'></script>

    <style>
        .form-check {
            display: inline-block;
            padding-left: 2px;
        }

        .form-check input {
            padding: 0;
            height: initial;
            width: initial;
            margin-bottom: 0;
            display: none;
            cursor: pointer;
        }

        .form-check label {
            position: relative;
            cursor: pointer;
        }

        .form-check label:before {
            content: '';
            -webkit-appearance: none;
            background-color: transparent;
            border: 2px solid #0079bf;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05);
            padding: 10px;
            display: inline-block;
            position: relative;
            vertical-align: middle;
            cursor: pointer;
            margin-right: 5px;
        }

        .form-check input:checked + label:after {
            content: '';
            display: block;
            position: absolute;
            top: 2px;
            left: 9px;
            width: 6px;
            height: 14px;
            border: solid #0079bf;
            border-width: 0 2px 2px 0;
            transform: rotate(45deg);
        }

        .garis {
            color: #ddd;
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
            Tambah Paket Periksa
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <div class="row">
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-medkit"></i> Tindakan</h3>
                            </div>
                            <div class="col-md-6">
                                <h3 class="box-title"><i class="fa fa-hospital-o"></i> Penunjang Medis</h3>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
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

                            <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true"
                                       resizable="false"
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
                        <div class="row">
                            <div class="form-group">
                                <label class="col-md-3" style="margin-top: 5px">Diskon Tarif Tindakan (%)
                                    ?</label>
                                <div class="col-md-1">
                                    <div class="form-check" style="margin-top: 5px;">
                                        <input type="checkbox" name="persen" id="persen" value="Y"
                                               onclick="cekPersen(this.id, 'jml_persen')">
                                        <label for="persen"></label>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <input style="margin-left: -55px; width: 75%" class="form-control" id="jml_persen"
                                           type="number" disabled oninput="setTarifTinLab(this.value)">
                                </div>
                            </div>
                        </div>
                        <hr class="garis">
                        <div class="row">
                            <div class="col-md-6">
                                <%--<button class="btn btn-success btn-outline" style="margin-bottom: 10px;"--%>
                                <%--onclick="showModal(2)"><i class="fa fa-plus"></i> Tindakan--%>
                                <%--</button>--%>
                                <div class="alert alert-danger alert-dismissible" style="display: none"
                                     id="warning_tindakan">
                                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                                    <p id="msg_tin"></p>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <label class="col-md-3" style="margin-top: 7px">Poli</label>
                                        <div class="col-md-7">
                                            <s:action id="initComboPoli" namespace="/checkup"
                                                      name="getComboPelayananPaketPeriksa_checkup"/>
                                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                                      list="#initComboPoli.listOfPelayananPaket" id="poli"
                                                      name="headerCheckup.idPelayanan" listKey="idPelayanan"
                                                      listValue="namaPelayanan"
                                                      onchange="$(this).css('border',''); listKategori(this.value); var warn =$('#war_poli').is(':visible'); if (warn){$('#cor_poli').show().fadeOut(3000);$('#war_poli').hide()}"
                                                      headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control select2"/>
                                        </div>
                                        <div class="col-md-2">
                                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                               id="war_poli"><i class="fa fa-times"></i> required</p>
                                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                               id="cor_poli"><i class="fa fa-check"></i> correct</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3" style="margin-top: 7px">Kategori</label>
                                        <div class="col-md-7">
                                            <select style="margin-top: 7px" class="form-control select2"
                                                    id="tin_id_ketgori_tindakan"
                                                    onchange="listSelectTindakan(this.value); var warn =$('#war_kategori').is(':visible'); if (warn){$('#cor_kategori').show().fadeOut(3000);$('#war_kategori').hide()}">
                                                <option value="">[Select One]</option>
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
                                                    onchange="getTarifTindakan(this.value); var warn =$('#war_tindakan').is(':visible'); if (warn){$('#cor_tindakan').show().fadeOut(3000);$('#war_tindakan').hide();}">
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
                                            <input type="number" min="1" class="form-control" style="margin-top: 7px"
                                                   id="tin_qty"
                                                   oninput="$(this).css('border','')"
                                                   onchange="$(this).css('border','')" value="1">
                                        </div>
                                    </div>
                                    <div class="form-group" id="form_tarif_tindakan">
                                        <label class="col-md-3" style="margin-top: 7px">Tarif Tindakan</label>
                                        <div class="col-md-7">
                                            <input min="1" class="form-control" style="margin-top: 7px"
                                                   id="tin_tarif"
                                                   oninput="$(this).css('border','')"
                                                   onchange="$(this).css('border','')" disabled>
                                            <input type="hidden" id="h_tin_tarif">
                                        </div>
                                    </div>
                                    <div class="form-group" id="form_tarif_tindakan_paket">
                                        <label class="col-md-3" style="margin-top: 7px">Tarif Paket</label>
                                        <div class="col-md-7">
                                            <input min="1" class="form-control" style="margin-top: 7px"
                                                   id="tin_tarif_paket"
                                                   oninput="$(this).css('border',''); convertRp('tin_tarif_paket', this.value)"
                                                   onchange="$(this).css('border','')">
                                            <input type="hidden" id="h_tin_tarif_paket">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-offset-3 col-md-7" style="margin-top: 7px">
                                            <button onclick="window.location.reload(true)" class="btn btn-danger"><i class="fa fa-refresh"></i> Reset</button>
                                            <button class="btn btn-success" onclick="saveTindakan()"><i
                                                    class="fa fa-plus"></i> Tambah
                                            </button>
                                        </div>
                                    </div>
                                </div>

                                <table class="table table-bordered table-striped"
                                       style="margin-top: 20px; font-size: 12px" id="table_tindakan">
                                    <thead>
                                    <tr bgcolor="#90ee90">
                                        <td>Pelayanan</td>
                                        <td>Tindakan</td>
                                        <td align="center" width="15%">Qty</td>
                                        <td align="center" width="15%">Tarif Paket</td>
                                        <td align="center" width="10%">Action</td>
                                    </tr>
                                    </thead>
                                    <tbody id="body_tindakan">
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-md-6">
                                <%--<button class="btn btn-success btn-outline" style="margin-bottom: 10px;" onclick="showModal(4)"><i class="fa fa-plus"></i> Penunjang--%>
                                <%--</button>--%>
                                <div class="alert alert-danger alert-dismissible" style="display: none"
                                     id="warning_lab">
                                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                                    <p id="msg_lab"></p>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <label class="col-md-3" style="margin-top: 7px">Poli Untuk Lab</label>
                                        <div class="col-md-7">
                                            <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                                    id="lab_poli"
                                                    onchange="var warn =$('#war_poli_lab').is(':visible'); if (warn){$('#cor_poli_lab').show().fadeOut(3000);$('#war_poli_lab').hide()};">
                                                <option value=''>[Select One]</option>
                                            </select>
                                        </div>
                                        <div class="col-md-2">
                                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                               id="war_poli_lab"><i
                                                    class="fa fa-times"></i> required</p>
                                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                               id="cor_poli_lab"><i
                                                    class="fa fa-check"></i> correct</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3" style="margin-top: 7px">Kategori Lab</label>
                                        <div class="col-md-7">
                                            <s:action id="comboLab" namespace="/kategorilab"
                                                      name="getListKategoriLab_kategorilab"/>
                                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                                      onchange="var warn =$('#war_kategori_lab').is(':visible'); if (warn){$('#cor_kategori_lab').show().fadeOut(3000);$('#war_kategori_lab').hide()}; listSelectLab(this.value)"
                                                      list="#comboLab.listOfKategoriLab" id="lab_kategori"
                                                      listKey="idKategoriLab + '|' + namaKategori"
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
                                            <select class="form-control select2" style="margin-top: 7px; width: 100%"
                                                    id="lab_lab"
                                                    onchange="var warn =$('#war_lab').is(':visible'); if (warn){$('#cor_lab').show().fadeOut(3000);$('#war_lab').hide()}; listSelectParameter(this.value);">
                                                <option value=''>[Select One]</option>
                                            </select>
                                        </div>
                                        <div class="col-md-2">
                                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                               id="war_lab"><i
                                                    class="fa fa-times"></i> required</p>
                                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                               id="cor_lab"><i
                                                    class="fa fa-check"></i> correct</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3" style="margin-top: 7px">Parameter</label>
                                        <div class="col-md-7">
                                            <select class="form-control select2"
                                                    style="margin-top: 7px; width: 100%"
                                                    id="lab_parameter"
                                                    onchange="getTarifLab(this.value); var warn =$('#war_parameter').is(':visible'); if (warn){$('#cor_parameter').show().fadeOut(3000);$('#war_parameter').hide()}">
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
                                    <div class="form-group" id="form_lab_tarif">
                                        <label class="col-md-3" style="margin-top: 7px">Tarif Lab</label>
                                        <div class="col-md-7">
                                            <input min="1" class="form-control" style="margin-top: 7px"
                                                   id="lab_tarif"
                                                   oninput="$(this).css('border',''); convertRp('lab_tarif', this.value)"
                                                   onchange="$(this).css('border','')" disabled>
                                            <input type="hidden" id="h_lab_tarif">
                                        </div>
                                    </div>
                                    <div class="form-group" id="form_lab_tarif_paket">
                                        <label class="col-md-3" style="margin-top: 7px">Tarif Paket Lab</label>
                                        <div class="col-md-7">
                                            <input min="1" class="form-control" style="margin-top: 7px"
                                                   id="lab_tarif_paket"
                                                   oninput="$(this).css('border',''); convertRp('lab_tarif', this.value)"
                                                   onchange="$(this).css('border','')">
                                            <input type="hidden" id="h_lab_tarif_paket">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-offset-3 col-md-7" style="margin-top: 7px">
                                            <%--<button class="btn btn-danger"><i class="fa fa-refresh"></i> Reset</button>--%>
                                            <button class="btn btn-success" onclick="saveLab()"><i
                                                    class="fa fa-plus"></i> Tambah
                                            </button>
                                        </div>
                                    </div>
                                </div>

                                <table class="table table-bordered table-striped"
                                       style="margin-top: 20px; font-size: 12px" id="table_lab">
                                    <thead>
                                    <tr bgcolor="#90ee90">
                                        <td>Jenis Lab</td>
                                        <td>Pemeriksaan</td>
                                        <td>Item Periksa</td>
                                        <td>Tarif Asli</td>
                                        <td>Tarif Paket</td>
                                        <td align="center" width="10%">Action</td>
                                    </tr>
                                    </thead>
                                    <tbody id="body_lab">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="box-header with-border">
                    </div>
                    <div class="box-header with-border">
                        <div class="alert alert-danger alert-dismissible" style="display: none"
                             id="warning_paket">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_paket"></p>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <table class="table table-bordered table-striped"
                                       style="margin-top: 20px; font-size: 12px" id="table_pelayanan">
                                    <thead>
                                    <tr bgcolor="#90ee90">
                                        <td>Pelayanan</td>
                                        <td width="20%" align="center">Urutan</td>
                                        <%--<td width="20%" align="center">Action</td>--%>
                                    </tr>
                                    </thead>
                                    <tbody id="body_pelayanan">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-3">
                                    <label>Nama Paket</label>
                                    <input class="form-control" id="nama_paket"
                                           oninput="var warn =$('#war_paket').is(':visible'); if (warn){$('#cor_paket').show().fadeOut(3000);$('#war_paket').hide()}">
                                    <p style="color: red; display: none;"
                                       id="war_paket"><i class="fa fa-times"></i> required</p>
                                    <p style="color: green; display: none;"
                                       id="cor_paket"><i class="fa fa-check"></i> correct</p>
                                </div>
                                <div class="col-md-3">
                                    <label>Tarif Paket</label>
                                    <div class="input-group">
                                        <div class="input-group-addon">
                                            Rp.
                                        </div>
                                        <input type="hidden" id="tarif_paket">
                                        <input class="form-control" id="nominal_tarif_paket" disabled
                                               oninput="var warn =$('#war_tarif_paket').is(':visible'); if (warn){$('#cor_tarif_paket').show().fadeOut(3000);$('#war_tarif_paket').hide()}">
                                    </div>
                                    <p style="color: red; display: none;"
                                       id="war_tarif_paket"><i class="fa fa-times"></i> required</p>
                                    <p style="color: green; display: none;"
                                       id="cor_tarif_paket"><i class="fa fa-check"></i> correct</p>
                                </div>
                                <div class="col-md-4">
                                    <div style="margin-top: 23px">
                                        <a class="btn btn-warning" href="initForm_paketperiksa.action"><i
                                                class="fa fa-arrow-left"></i> Back</a>
                                        <a class="btn btn-success" id="save_paket"
                                           onclick="savePaket()"><i class="fa fa-check"></i> Save</a>
                                        <button style="display: none; cursor: no-drop;" type="button"
                                                class="btn btn-success" id="load_paket"><i
                                                class="fa fa-spinner fa-spin"></i>
                                            Sedang Menyimpan...
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
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

<div class="mask"></div>
<!-- /.content-wrapper -->
<script type='text/javascript'>

    var idDetailCheckup = "";
    var tempData = [];

    $(document).ready(function () {
        $('#paket_periksa').addClass('active');

        var tarif = document.getElementById('nominal_tarif_paket');
        tarif.addEventListener('keyup', function (e) {
            tarif.value = formatRupiah2(this.value);
            var val = tarif.value.replace(/[.]/g, '');

            if (val != '') {
                $('#tarif_paket').val(val);
            } else {
                $('#tarif_paket').val('');
            }
        });
    });

    function getTarifTindakan(var1) {
        var cekPersen = $('#persen').is(':checked');
        var jmlPersen = $('#jml_persen').val();
        if (var1 != '') {
            if ("all" == var1) {
                $('#form_tarif_tindakan').hide();
                $('#form_tarif_tindakan_paket').hide();
            } else {
                $('#form_tarif_tindakan').show();
                $('#form_tarif_tindakan_paket').show();
                var id = '';
                if (var1.split("|")[0] != 'null' && var1.split("|")[0] != '') {
                    id = var1.split("|")[0];
                }
                if (id != '') {
                    TindakanAction.getDataTindakanById(id, function (tindakan) {
                        if (tindakan != null && tindakan.idTindakan != null) {
                            var tarif = "";
                            if (cekPersen) {
                                if (jmlPersen != '' && parseInt(jmlPersen) > 0) {
                                    var persen = 100 - parseInt(jmlPersen);
                                    var hasil = persen / 100;
                                    tarif = hasil * tindakan.tarif;
                                    $('#persen').attr('disabled', true);
                                    $('#persen').attr('style', 'cursor:no-drop');
                                    $('#jml_persen').attr('disabled', true);
                                } else {
                                    $('#warning_tindakan').show().fadeOut(5000);
                                    $('#msg_tin').text("Diskon Tarif tidak boleh kosong dan 0...!");
                                }
                            } else {
                                tarif = tindakan.tarif;
                                $('#persen').attr('disabled', true);
                                $('#persen').attr('style', 'cursor:no-drop');
                                $('#jml_persen').attr('disabled', true);
                            }
                            $("#tin_tarif").val(formatRupiahAtas(tindakan.tarif));
                            $("#h_tin_tarif").val(tindakan.tarif);
                            $("#tin_tarif_paket").val(formatRupiahAtas(tarif));
                            $("#h_tin_tarif_paket").val(tarif);
                        }
                    });
                }
            }
        }
    }

    function getTarifLab(var1) {
        var cekPersen = $('#persen').is(':checked');
        var jmlPersen = $('#jml_persen').val();
        if (var1 != '') {
            if ("all" == var1) {
                $('#form_lab_tarif').hide();
                $('#form_lab_tarif_paket').hide();
            } else {
                $('#form_lab_tarif').show();
                $('#form_lab_tarif_paket').show();
                LabDetailAction.labDetailEntityByIdLab(var1, function (response) {
                    if (response != null && response.idLabDetail != null) {
                        var tarif = "";
                        if (cekPersen) {
                            if (jmlPersen != '' && parseInt(jmlPersen) > 0) {
                                var persen = 100 - parseInt(jmlPersen);
                                var hasil = persen / 100;
                                tarif = hasil * response.tarif;
                                $('#persen').attr('disabled', true);
                                $('#persen').attr('style', 'cursor:no-drop');
                                $('#jml_persen').attr('disabled', true);
                            } else {
                                $('#warning_lab').show().fadeOut(5000);
                                $('#msg_lab').text("Diskon Tarif tidak boleh kosong dan 0...!");
                            }
                        } else {
                            tarif = response.tarif;
                            $('#persen').attr('disabled', true);
                            $('#persen').attr('style', 'cursor:no-drop');
                            $('#jml_persen').attr('disabled', true);
                        }
                        $("#lab_tarif").val(formatRupiahAtas(response.tarif));
                        $("#lab_tarif_paket").val(formatRupiahAtas(tarif));
                        $("#h_lab_tarif").val(response.tarif);
                        $("#h_lab_tarif_paket").val(tarif);
                    }
                });
            }
        }
    }

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

    function toContent() {
        window.location.href = 'initForm_paketperiksa.action';
    }

    function listSelectTindakan(value) {
        var option = '<option value="">[Select One]</option>';
        var idPelayanan = $("#poli").val();
        if (value != '') {
            CheckupDetailAction.getListComboTindakan(value, null, null, idPelayanan, function (response) {
                if (response.length > 0) {
                    option = option + '<option value="all">Select All</option>';
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idTindakan + "|" + item.tindakan + "'>" + item.tindakan + "</option>";
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

    function listKategori(idPelayanan) {
        if (idPelayanan != null && idPelayanan != '') {
            $('#tin_tarif').val('');
            $('#h_tin_tarif').val('');
            $('#tin_tarif_paket').val('');
            $('#h_tin_tarif_paket').val('');
            $('#tin_id_tindakan').val('').trigger('change');
            var option = "<option value=''>[Select One]</option>";
            PaketPeriksaAction.getListKategoriTindakan(idPelayanan, function (response) {
                if (response.length > 0) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idKategoriTindakan + "'>" + item.kategoriTindakan + "</option>";
                    });
                    $('#tin_id_ketgori_tindakan').html(option);
                } else {
                    $('#tin_id_ketgori_tindakan').html('');
                }
            });
        } else {
            $('#tin_id_ketgori_tindakan').html('');
        }
    }

    function showModal(select) {

        var id = "";

        if (select == 2) {
            $('#tin_id_ketgori_tindakan, #tin_id_tindakan, #tin_id_perawat').val('').trigger('change');
            $('#tin_qty').val('1');
            $('#load_tindakan, #warning_tindakan, #war_kategori, #war_tindakan, #war_perawat').hide();
            $('#save_tindakan').attr('onclick', 'saveTindakan(\'' + id + '\')').show();
            // $('#modal-tindakan').modal('show');

        } else if (select == 4) {
            $('#lab_kategori, #lab_lab').val('').trigger('change');
            $('#load_lab, #warning_lab, #war_kategori_lab, #war_lab, #war_parameter').hide();
            $('#save_lab').attr('onclick', 'saveLab(\'' + id + '\')').show();
            // $('#modal-lab').modal('show');
        }
    }

    function saveTindakan() {

        var idPoli = $('#poli').val();
        var namaPoli = $('#poli option:selected').text();
        var idKategori = $('#tin_id_ketgori_tindakan').val();
        var idTindakan = $('#tin_id_tindakan').val();
        var tarifPaket = $("#h_tin_tarif_paket").val();
        var qty = $('#tin_qty').val();
        var data = $('#table_tindakan').tableToJSON();
        var cekPersen = $('#persen').is(':checked');
        var jmlPersen = $('#jml_persen').val();
        var row = data.length;
        var cek = false;

        if (idTindakan != '' && idTindakan != null && idKategori != null && qty > 0 && idKategori != '') {
            var id = "";
            var tin = "";

            var tindakan = idTindakan.split("|");
            if (tindakan[0] != 'null' && tindakan[0] != '') {
                id = tindakan[0];
            }

            if (tindakan[1] != 'null' && tindakan[1] != '') {
                tin = tindakan[1];
            }

            if (idTindakan == "all") {
                CheckupDetailAction.getListComboTindakan(idKategori, function (response) {
                    if (response.length > 0) {
                        var table = "";
                        $.each(response, function (ix, itemx) {
                            $.each(data, function (i, item) {
                                var tin2 = $('#tindakan_id' + i).val();
                                if (itemx.idTindakan == tin2) {
                                    cek = true;
                                }
                            });
                        });
                        if (cek) {
                            $('#warning_tindakan').show().fadeOut(5000);
                            $('#msg_tin').text("Data tindakan sudah ada dalam list...!");
                        } else {
                            var setAppend = false;
                            var isPersen = false;
                            if (cekPersen) {
                                if (jmlPersen != '' && parseInt(jmlPersen) > 0) {
                                    setAppend = false;
                                    isPersen = true;
                                } else {
                                    setAppend = true;
                                }
                            }

                            if (setAppend) {
                                $('#warning_tindakan').show().fadeOut(5000);
                                $('#msg_tin').text("Diskon Tarif tidak boleh kosong dan 0...!");
                            } else {
                                $.each(response, function (i, item) {
                                    if(data.length == 0){
                                        row = i;
                                    }else{
                                        if(i == 0){
                                            row = row;
                                        }else{
                                            row = row + 1;
                                        }
                                    }
                                    var tarif = item.tarif;
                                    if (isPersen) {
                                        var persen = 100 - parseInt(jmlPersen);
                                        var hasil = persen / 100;
                                        tarif = hasil * item.tarif;
                                    }
                                    table += '<tr id="row' + item.idTindakan + '">' +
                                        '<td>' + namaPoli + '<input type="hidden" value="' + idPoli + '" id="poli_id' + row + '">' + '</td>' +
                                        '<td>' + item.tindakan + '<input type="hidden" value="' + item.idTindakan + '" id="tindakan_id' + row + '">' + '</td>' +
                                        '<td align="center">' + qty + '<input type="hidden" value="' + idKategori + '" id="kategori_id' + row + '">' + '</td>' +
                                        '<td align="center">' + formatRupiahAtas(tarif) + '<input type="hidden" value="' + tarif + '" id="tin_tarif_id' + row + '">' + '</td>' +
                                        '<td align="center">' + '<img border="0" class="hvr-grow" onclick="delRow(\'' + item.idTindakan + '\')" src="<s:url value="/pages/images/icons8-cancel-25.png"/>" style="cursor: pointer;">' + '</td>' +
                                        '</tr>';

                                });
                                $('#body_tindakan').append(table);
                                $('#tin_id_tindakan').val('').trigger('change');
                                hitungTotal();
                                tempSelectPoli();
                                disabledDiskon();
                            }
                        }
                    }
                });
            } else {
                $.each(data, function (i, item) {
                    var tin2 = $('#tindakan_id' + i).val();
                    if (id == tin2) {
                        cek = true;
                    }
                });
                if (cek) {
                    $('#warning_tindakan').show().fadeOut(5000);
                    $('#msg_tin').text("Data tindakan sudah ada dalam list...!");
                } else {
                    var setAppend = false;
                    var isPersen = false;
                    if (cekPersen) {
                        if (jmlPersen != '' && parseInt(jmlPersen) > 0) {
                            setAppend = false;
                            isPersen = true;
                        } else {
                            setAppend = true;
                        }
                    }
                    if (setAppend) {
                        $('#warning_tindakan').show().fadeOut(5000);
                        $('#msg_tin').text("Diskon Tarif tidak boleh kosong dan 0...!");
                    }else{
                        var table = '<tr id="row' + id + '">' +
                            '<td>' + namaPoli + '<input type="hidden" value="' + idPoli + '" id="poli_id' + row + '">' + '</td>' +
                            '<td>' + tin + '<input type="hidden" value="' + id + '" id="tindakan_id' + row + '">' + '</td>' +
                            '<td align="center">' + qty + '<input type="hidden" value="' + idKategori + '" id="kategori_id' + row + '">' + '</td>' +
                            '<td align="center">' + formatRupiahAtas(tarifPaket) + '<input type="hidden" value="' + tarifPaket + '" id="tin_tarif_id' + row + '">' + '</td>' +
                            '<td align="center">' + '<img border="0" class="hvr-grow" onclick="delRow(\'' + id + '\')" src="<s:url value="/pages/images/icons8-cancel-25.png"/>" style="cursor: pointer;">' + '</td>' +
                            '</tr>';

                        $('#body_tindakan').append(table);
                        $('#tin_id_tindakan').val('').trigger('change');
                        hitungTotal();
                        tempSelectPoli();
                        disabledDiskon();
                    }
                }
            }

        } else {
            $('#warning_tindakan').show().fadeOut(5000);
            $('#msg_tin').text("Silahkan cek kembali data inputan anda...!");
            if (idKategori == '') {
                $('#war_kategori').show();
            }
            if (idTindakan == '') {
                $('#war_tindakan').show();
            }
            if (qty <= 0 || qty == '') {
                $('#tin_qty').css('border', 'red solid 1px');
            }
        }
    }

    function tempSelectPoli() {
        var data = $('#table_tindakan').tableToJSON();
        if (data.length > 0) {
            $.each(data, function (ix, itemx) {
                var idPel = $('#poli_id' + ix).val();
                var Pel = data[ix]["Pelayanan"];
                var cekPoint = false;
                if(tempData.length > 0){
                    $.each(tempData, function (id, itemd) {
                        if(idPel == itemd.id_pelayanan){
                            cekPoint = true;
                        }
                    });
                    if(!cekPoint){
                        tempData.push({
                            'id_pelayanan': idPel,
                            'pelayanan': Pel
                        });
                    }
                }else{
                    tempData.push({
                        'id_pelayanan': idPel,
                        'pelayanan': Pel
                    });
                }
            });
        } else {
            tempData = [];
        }

        var option = '<option value="">[Select One]</option>';
        var table = "";
        var long = tempData.length;
        if (long > 0) {
            $.each(tempData, function (i, item) {
                option += '<option value="' + item.id_pelayanan + '">' + item.pelayanan + '</option>';
                var urut = i + 1;
                table +=
                    '<tr>' +
                    '<td>' + item.pelayanan + '<input type="hidden" id="poli_id_' + urut + '" value="' + item.id_pelayanan + '">' + '</td>' +
                    '<td align="center">' + '<input id="urut_val_' + urut + '" onchange="setUrut(this.value, \'' + urut + '\')" class="form-control" type="number" min="1" max="' + long + '" value="' + urut + '" disabled>' +
                    '<input type="hidden" id="urut_' + urut + '">' + '</td>' +
                    <%--'<td align="center">' +--%>
                    <%--'<img id="btn_urut' + urut + '" onclick="setUrutanPelayanan(\'' + urut + '\')" class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">' + '</td>' +--%>
                    '</tr>';
            });
        }
        $('#body_pelayanan').html(table);
        $('#lab_poli').html(option);
    }

    function setUrutanPelayanan(i) {
        var data = $('#table_pelayanan').tableToJSON();
        var src = '<s:url value="/pages/images/icons8-save-25.png"/>';
        $('#urut_val_' + i).attr('disabled', false);
        $('#btn_urut' + i).removeAttr('src');
        $('#btn_urut' + i).attr('src', src);
        $('#btn_urut' + i).attr('onclick', 'saveUrutanPelayanan(\'' + i + '\')');
    }

    function saveUrutanPelayanan(i) {
        var data = $('#table_tindakan').tableToJSON();
        var nilai = $('#urut_val_' + i).val();
        var cek = false;
        $.each(data, function (i, item) {
            var pem = $('#urut_' + i).val();
            if (nilai == pem) {
                cek = true;
            }
        });
        if (cek) {
            $('#warning_paket').show().fadeOut(5000);
            $('#msg_paket').text("Nomor urutan pelayanan tidak boleh sama...!");
        } else {
            var src = '<s:url value="/pages/images/icons8-create-25.png"/>';
            $('#btn_urut' + i).removeAttr('src');
            $('#btn_urut' + i).attr('src', src);
            $('#btn_urut' + i).attr('onclick', 'setUrutanPelayanan(\'' + i + '\')');
            $('#urut_val_' + i).attr('disabled', true);
        }
    }

    function setUrut(val, id) {
        $('#urut_' + id).val(val);
    }

    function delRow(id) {
        $('#row' + id).remove();
        tempSelectPoli();
    }

    function listSelectLab(value) {
        if (value != '') {
            var kat = value.split("|");
            var id = "";
            var ktr = "";

            if (kat[0] != 'null' && kat[0] != '') {
                id = kat[0];
            }

            if (kat[1] != 'null' && kat[1] != '') {
                ktr = kat[1];
            }

            var option = "<option value=''>[Select One]</option>";
            LabAction.listLab(id, function (response) {
                if (response.length > 0) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idLab + '|' + item.namaLab + "'>" + item.namaLab + "</option>";
                    });
                    $('#lab_lab').html(option);
                } else {
                    $('#lab_lab').html(option);
                }
            });
        } else {
            $('#lab_lab').html(option);
        }
    }

    function listSelectParameter(value) {
        var option = '<option value="">[Select One]</option>';
        if (value != '') {
            var labId = value.split("|");
            if (labId[0] != 'null' && labId[0] != '') {
                LabDetailAction.getListComboParameter(labId[0], function (response) {
                    if (response.length > 0) {
                        option = option + '<option value="all">Select All</option>';
                        $.each(response, function (i, item) {
                            option += "<option value='" + item.idLabDetail + "'>" + item.namaDetailPeriksa + "</option>";
                        });
                        $('#lab_parameter').html(option);
                    } else {
                        $('#lab_parameter').html(option);
                    }
                });
            }
        } else {
            $('#lab_parameter').html(option);
        }
    }

    function saveLab() {
        var poli = $('#lab_poli').val();
        var idKategori = $('#lab_kategori').val();
        var idLab = $('#lab_lab').val();
        var idParameter = $('#lab_parameter').val();
        var tarifAsli = $('#h_lab_tarif').val();
        var tarifPaket = $('#h_lab_tarif_paket').val();
        var namaParameter = $('#lab_parameter option:selected').text();
        var data = $('#table_lab').tableToJSON();
        var cekPersen = $('#persen').is(':checked');
        var jmlPersen = $('#jml_persen').val();
        var row = data.length;
        var cek = false;

        if (idKategori != '' && idLab != '' && idParameter != '') {
            var idk = idKategori.split("|")[0];
            var idl = idLab.split("|")[0];

            $.each(data, function (i, item) {
                var jen = $('#parameter_id' + i).val();
                if (idParameter == jen) {
                    cek = true;
                }
            });

            if ("all" == idParameter) {
                LabDetailAction.getListComboParameter(idl, function (response) {
                    if (response.length > 0) {
                        var table = "";
                        $.each(response, function (ix, itemx) {
                            $.each(data, function (i, item) {
                                var jen = $('#parameter_id' + i).val();
                                if (itemx.idLabDetail == jen) {
                                    cek = true;
                                }
                            });
                        });
                        if (cek) {
                            $('#warning_lab').show().fadeOut(5000);
                            $('#msg_lab').text("Data sudah ada di dalam list...!");
                        } else {
                            var setAppend = false;
                            var isPersen = false;
                            if (cekPersen) {
                                if (jmlPersen != '' && parseInt(jmlPersen) > 0) {
                                    setAppend = false;
                                    isPersen = true;
                                } else {
                                    setAppend = true;
                                }
                            }

                            if (setAppend) {
                                $('#warning_lab').show().fadeOut(5000);
                                $('#msg_lab').text("Diskon Tarif tidak boleh kosong dan 0...!");
                            } else {
                                $.each(response, function (i, item) {
                                    if(data.length == 0){
                                        row = i;
                                    }else{
                                        if(i == 0){
                                            row = row;
                                        }else{
                                            row = row + 1;
                                        }
                                    }
                                    var tarif = item.tarif;
                                    if (isPersen) {
                                        var persen = 100 - parseInt(jmlPersen);
                                        var hasil = persen / 100;
                                        tarif = hasil * item.tarif;
                                    }
                                    table += '<tr id="row' + item.idLabDetail + '">' +
                                        '<td>' + idKategori.split("|")[1] +
                                        '<input type="hidden" id="kategori_lab' + row + '" value="' + idk + '">' +
                                        '<input type="hidden" id="poli_id' + row + '" value="' + poli + '">' +
                                        '</td>' +
                                        '<td>' + idLab.split("|")[1] + '<input type="hidden" id="lab_id' + row + '" value="' + idl + '">' + '</td>' +
                                        '<td>' + item.namaDetailPeriksa + '<input type="hidden" id="parameter_id' + row + '" value="' + item.idLabDetail + '"></td>' +
                                        '<td>' + formatRupiahAtas(item.tarif) + '</td>' +
                                        '<td>' + formatRupiahAtas(tarif) + '<input type="hidden" id="lab_tarif_id' + row + '" value="' + tarif + '"></td>' +
                                        '<td align="center">' + '<img border="0" class="hvr-grow" onclick="delRow(\'' + item.idLabDetail + '\')" src="<s:url value="/pages/images/icons8-cancel-25.png"/>" style="cursor: pointer;">' + '</td>' +
                                        '</tr>';
                                });
                                $('#body_lab').append(table);
                                $('#lab_parameter').val('').trigger('change');
                                hitungTotal();
                                disabledDiskon();
                            }
                        }
                    }
                });
            } else {
                if (cek) {
                    $('#warning_lab').show().fadeOut(5000);
                    $('#msg_lab').text("Data sudah ada di dalam list...!");
                } else {
                    var setAppend = false;
                    var isPersen = false;
                    if (cekPersen) {
                        if (jmlPersen != '' && parseInt(jmlPersen) > 0) {
                            setAppend = false;
                            isPersen = true;
                        } else {
                            setAppend = true;
                        }
                    }
                    if (setAppend) {
                        $('#warning_lab').show().fadeOut(5000);
                        $('#msg_lab').text("Diskon Tarif tidak boleh kosong dan 0...!");
                    } else {
                        var table = '<tr id="row' + idl + '">' +
                            '<td>' + idKategori.split("|")[1] +
                            '<input type="hidden" id="kategori_lab' + row + '" value="' + idk + '">' +
                            '<input type="hidden" id="poli_id' + row + '" value="' + poli + '">' +
                            '</td>' +
                            '<td>' + idLab.split("|")[1] + '<input type="hidden" id="lab_id' + row + '" value="' + idl + '">' + '</td>' +
                            '<td>' + namaParameter + '<input type="hidden" id="parameter_id' + row + '" value="' + idParameter + '"></td>' +
                            '<td>' + formatRupiahAtas(tarifAsli) + '</td>' +
                            '<td>' + formatRupiahAtas(tarifPaket) + '<input type="hidden" id="lab_tarif_id' + row + '" value="' + tarifPaket + '"></td>' +
                            '<td align="center">' + '<img border="0" class="hvr-grow" onclick="delRow(\'' + idl + '\')" src="<s:url value="/pages/images/icons8-cancel-25.png"/>" style="cursor: pointer;">' + '</td>' +
                            '</tr>';
                        $('#body_lab').append(table);
                        $('#lab_parameter').val('').trigger('change');
                        hitungTotal();
                        disabledDiskon();
                    }
                }

            }

        } else {
            $('#warning_lab').show().fadeOut(5000);
            $('#msg_lab').text("Silahkan cek kembali data inputan...!");
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

    function hitungTotal() {
        var tindakan = $('#table_tindakan').tableToJSON();
        var lab = $('#table_lab').tableToJSON();

        var totalTindakan = 0;
        $.each(tindakan, function (i, item) {
            var tarifTindakan = $('#tin_tarif_id' + i).val();
            totalTindakan = parseInt(totalTindakan) + parseInt(tarifTindakan);
        });

        var totalLab = 0;
        $.each(lab, function (i, item) {
            var tarifpaket = $('#lab_tarif_id' + i).val();
            totalLab = parseInt(totalLab) + parseInt(tarifpaket);
        });

        var total = 0;
        total = totalTindakan + totalLab;
        $('#tarif_paket').val(total);
        $('#nominal_tarif_paket').val(formatRupiahAtas(total));
    }

    function savePaket() {
        if(!cekSession()){
            var namaPaket = $('#nama_paket').val();
            var tarifPaket = $('#tarif_paket').val();
            var tindakan = $('#table_tindakan').tableToJSON();
            var lab = $('#table_lab').tableToJSON();
            var pelayanan = $('#table_pelayanan').tableToJSON();
            var result = [];
            var resultPel = [];

            $.each(tindakan, function (i, item) {
                var idPoli = $('#poli_id' + i).val();
                var idTindakan = $('#tindakan_id' + i).val();
                var idKategori = $('#kategori_id' + i).val();
                var tarifTindakan = $('#tin_tarif_id' + i).val();

                result.push({
                    'kategori_item': idKategori,
                    'id_item': idTindakan,
                    'jenis_item': 'tindakan',
                    'tarif': tarifTindakan,
                    'id_pelayanan': idPoli
                });
            });

            $.each(lab, function (i, item) {
                var idPoli = $('#poli_id' + i).val();
                var idKategori = $('#kategori_lab' + i).val();
                var idLab = $('#lab_id' + i).val();
                var idParameter = $('#parameter_id' + i).val();
                var jenisLab = lab[i]["Jenis Lab"];
                var tarifpaket = $('#lab_tarif_id' + i).val();

                result.push({
                    'kategori_item': idLab,
                    'id_item': idParameter,
                    'jenis_item': jenisLab.toLowerCase(),
                    'tarif': tarifpaket,
                    'id_pelayanan': idPoli
                });
            });

            $.each(pelayanan, function (i, item) {
                i = i + 1
                var idPoli = $('#poli_id_' + i).val();
                var urutan = $('#urut_val_' + i).val();
                resultPel.push({
                    'id_pelayanan': idPoli,
                    'urutan': urutan
                });
            });

            if (result.length > 0 && namaPaket != '' && tarifPaket != '') {
                $("#waiting_dialog").dialog('open');
                var jsonStinng = JSON.stringify(result);
                var jsonStinngPel = JSON.stringify(resultPel);
                dwr.engine.setAsync(true);
                PaketPeriksaAction.savePaket(namaPaket, jsonStinng, jsonStinngPel, {
                    callback: function (response) {
                        if (response.status == "success") {
                            $("#waiting_dialog").dialog('close');
                            $('#info_dialog').dialog('open');
                            $('body').scrollTop(0);
                        } else {
                            $("#waiting_dialog").dialog('close');
                            $('#error_dialog').dialog('open');
                            $('#errorMessage').text(response.msg);
                        }
                    }
                });
            } else {
                $('#warning_paket').show().fadeOut(5000);
                $('#msg_paket').text("Silahkan cek kembali data inputan anda..!");
                if (namaPaket == '') {
                    $('#war_paket').show();
                }
                if (tarifPaket == '') {
                    $('#war_tarif_paket').show();
                }
            }
        }
    }

    function convertRp(id, val) {
        $('#' + id).val(formatRupiahAtas2(val));
        val = val.replace(/[.]/g, '');
        var numbers = /^[0-9]+$/;
        if(val.match(numbers)){
            $('#h_' + id).val(val);
        }
    }

    function cekPersen(id, idTujuan) {
        var cek = $('#' + id).is(':checked');
        if (cek) {
            $('#' + idTujuan).removeAttr('disabled', false);
            $('#tin_tarif_paket, #lab_tarif_paket').attr('disabled', true);
        } else {
            $('#' + idTujuan).attr('disabled', true);
            $('#tin_tarif_paket, #lab_tarif_paket').removeAttr('disabled', false);
        }
    }

    function disabledDiskon(){
        $('#persen').attr('disabled', true);
        $('#persen').attr('style', 'cursor:no-drop');
        $('#jml_persen').attr('disabled', true);
    }

    function setTarifTinLab(diskon){
        if(diskon != ''){
            var tindakan = $('#h_tin_tarif').val();
            var lab = $('#h_lab_tarif').val();
            if(tindakan != ''){
                var persen = 100 - parseInt(diskon);
                var hasil = persen / 100;
                var tarif = hasil * tindakan;
                $('#tin_tarif_paket').val(formatRupiahAtas(tarif));
                $('#h_tin_tarif_paket').val(tarif);
            }

            if(lab != ''){
                var persen = 100 - parseInt(diskon);
                var hasil = persen / 100;
                var tarif = hasil * lab;
                $('#lab_tarif_paket').val(formatRupiahAtas(tarif));
                $('#h_lab_tarif_paket').val(tarif);
            }
        }else{
            $('#tin_tarif_paket, #lab_tarif_paket').val('0');
            $('#h_tin_tarif_paket, #h_lab_tarif_paket').val('0');
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>
