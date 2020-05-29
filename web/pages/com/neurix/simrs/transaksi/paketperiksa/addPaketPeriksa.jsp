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
                                            <select style="margin-top: 7px" class="form-control select2" id="tin_id_ketgori_tindakan" onchange="listSelectTindakan(this); var warn =$('#war_kategori').is(':visible'); if (warn){$('#cor_kategori').show().fadeOut(3000);$('#war_kategori').hide()}">
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
                                                    onchange="getTarifTindakan(this); var warn =$('#war_tindakan').is(':visible'); if (warn){$('#cor_tindakan').show().fadeOut(3000);$('#war_tindakan').hide();}">
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
                                    <div class="form-group">
                                        <label class="col-md-3" style="margin-top: 7px">Tarif Tindakan</label>
                                        <div class="col-md-7">
                                            <input type="number" min="1" class="form-control" style="margin-top: 7px"
                                                   id="tin_tarif"
                                                   oninput="$(this).css('border','')"
                                                   onchange="$(this).css('border','')"  disabled>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3" style="margin-top: 7px">Tarif Paket</label>
                                        <div class="col-md-7">
                                            <input type="number" min="1" class="form-control" style="margin-top: 7px"
                                                   id="tin_tarif_paket"
                                                   oninput="$(this).css('border','')"
                                                   onchange="$(this).css('border','')">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-offset-3 col-md-7" style="margin-top: 7px">
                                            <button class="btn btn-danger"><i class="fa fa-refresh"></i> Reset</button>
                                            <button class="btn btn-success" onclick="saveTindakan()"><i class="fa fa-plus"></i> Tambah</button>
                                        </div>
                                    </div>
                                </div>

                                <table class="table table-bordered table-striped" style="margin-top: 20px" id="table_tindakan">
                                    <thead>
                                    <tr bgcolor="#90ee90">
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
                                    <div class="form-group" style="margin-top: 42px">
                                        <label class="col-md-3" style="margin-top: 7px">Kategori Lab</label>
                                        <div class="col-md-7">
                                            <s:action id="comboLab" namespace="/kategorilab"
                                                      name="getListKategoriLab_kategorilab"/>
                                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                                      onchange="var warn =$('#war_kategori_lab').is(':visible'); if (warn){$('#cor_kategori_lab').show().fadeOut(3000);$('#war_kategori_lab').hide()}; listSelectLab(this)"
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
                                                    onchange="var warn =$('#war_lab').is(':visible'); if (warn){$('#cor_lab').show().fadeOut(3000);$('#war_lab').hide()}; listSelectParameter(this);">
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
                                    <div class="form-group">
                                        <label class="col-md-3" style="margin-top: 7px">Tarif Lab</label>
                                        <div class="col-md-7">
                                            <input type="number" min="1" class="form-control" style="margin-top: 7px"
                                                   id="lab_tarif"
                                                   oninput="$(this).css('border','')"
                                                   onchange="$(this).css('border','')"  disabled>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3" style="margin-top: 7px">Tarif Paket Lab</label>
                                        <div class="col-md-7">
                                            <input type="number" min="1" class="form-control" style="margin-top: 7px"
                                                   id="lab_tarif_paket"
                                                   oninput="$(this).css('border','')"
                                                   onchange="$(this).css('border','')">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-offset-3 col-md-7" style="margin-top: 7px">
                                            <button class="btn btn-danger"><i class="fa fa-refresh"></i> Reset</button>
                                            <button class="btn btn-success" onclick="saveLab()"><i class="fa fa-plus"></i> Tambah</button>
                                        </div>
                                    </div>
                                </div>

                                <table class="table table-bordered table-striped" style="margin-top: 20px" id="table_lab">
                                    <thead>
                                    <tr bgcolor="#90ee90">
                                        <td>Jenis Lab</td>
                                        <td>Pemeriksaan</td>
                                        <td>Item Periksa</td>
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
                        <div class="row">
                            <div class="alert alert-danger alert-dismissible" style="display: none"
                                 id="warning_paket">
                                <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                                <p id="msg_paket"></p>
                            </div>
                                <div class="form-group">
                                    <div class="col-md-3">
                                        <label>Nama Paket</label>
                                        <input class="form-control" id="nama_paket" oninput="var warn =$('#war_paket').is(':visible'); if (warn){$('#cor_paket').show().fadeOut(3000);$('#war_paket').hide()}">
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
                                            <input class="form-control" id="nominal_tarif_paket" oninput="var warn =$('#war_tarif_paket').is(':visible'); if (warn){$('#cor_tarif_paket').show().fadeOut(3000);$('#war_tarif_paket').hide()}">
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

    $(document).ready(function () {
        $('#paket_periksa').addClass('active');

        var tarif = document.getElementById('nominal_tarif_paket');
        tarif.addEventListener('keyup', function (e) {
            tarif.value = formatRupiah2(this.value);
            var val = tarif.value.replace(/[.]/g, '');

            if(val != ''){
                $('#tarif_paket').val(val);
            }else{
                $('#tarif_paket').val('');
            }
        });
    });

    function getTarifTindakan(var1) {
        var idx = var1.selectedIndex;
        var idTindakan = var1.options[idx].value;

        var id = "";
        var tindakan = idTindakan.split("|");
        if(tindakan[0] != 'null' && tindakan[0] != ''){
            id = tindakan[0];
        }
        TindakanAction.getDataTindakanById(id, function (tindakan) {
            if (tindakan != null && tindakan.idTindakan != null){
                $("#tin_tarif").val(tindakan.tarif);
            }
        })
    }

    function getTarifLab(var1) {

//        console.log(id);
//        console.log(idParameter);
        console.log(var1);
        LabDetailAction.labDetailEntityByIdLab(var1, function (response) {
            console.log(response);
            if (response != null && response.idLabDetail != null){
                $("#lab_tarif").val(response.tarif);
            }
        });
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

    function toContent(){
        window.location.href = 'initForm_paketperiksa.action';
    }

    function listSelectTindakan(idKategori) {
        var idx = idKategori.selectedIndex
        if (idx > 0) {
            var option = "<option value=''>[Select One]</option>";
            var idKtg = idKategori.options[idx].value;
            CheckupDetailAction.getListComboTindakan(idKtg, function (response) {
                if (response != null) {
                    console.log(response);
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idTindakan + "|" + item.tindakan + "'>" + item.tindakan + "</option>";
                    });
                    $('#tin_id_tindakan').html(option);
                } else {
                    $('#tin_id_tindakan').html('');
                }
            });
        } else {
            $('#tin_id_tindakan').html('');
        }
    }

    function listKategori(idPelayanan){
        if(idPelayanan != null && idPelayanan != ''){
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
        }else{
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
            $('#modal-tindakan').modal('show');

        } else if (select == 4) {
            $('#lab_kategori, #lab_lab').val('').trigger('change');
            $('#load_lab, #warning_lab, #war_kategori_lab, #war_lab, #war_parameter').hide();
            $('#save_lab').attr('onclick', 'saveLab(\'' + id + '\')').show();
            $('#modal-lab').modal('show');
        }
    }

    function saveTindakan() {

        var idKategori = $('#tin_id_ketgori_tindakan').val();
        var idTindakan = $('#tin_id_tindakan').val();
        var tarifPaket = $("#tin_tarif_paket").val();
        var qty = $('#tin_qty').val();
        var cek = false;
        var data = $('#table_tindakan').tableToJSON();

        if (idTindakan != '' && idTindakan != null && idKategori != null && qty > 0 && idKategori != '' && tarifPaket != '') {


            var id = "";
            var tin = "";

            var tindakan = idTindakan.split("|");
            if(tindakan[0] != 'null' && tindakan[0] != ''){
                id = tindakan[0];
            }

            if(tindakan[1] != 'null' && tindakan[1] != ''){
                tin = tindakan[1];
            }

            var data = $('#table_tindakan').tableToJSON();
            var row = data.length;
            var cek = false;

            $.each(data, function (i, item) {
                var tin2 = data[i]["Tindakan"];
                if(tin == tin2){
                    cek = true;
                }
            });

            if(cek){
                $('#warning_tindakan').show().fadeOut(5000);
                $('#msg_tin').text("Data tindakan sudah ada dalam list...!");
            }else{

                var table = '<tr id="row'+id+'">' +
                    '<td>' + tin + '<input type="hidden" value="'+id+'" id="tindakan_id'+row+'">' + '</td>' +
                    '<td align="center">' + qty + '<input type="hidden" value="'+idKategori+'" id="kategori_id'+row+'">' +'</td>' +
                    '<td align="center">' + tarifPaket + '<input type="hidden" value="'+tarifPaket+'" id="tin_tarif_id'+row+'">' +'</td>' +
                    '<td align="center">' + '<img border="0" class="hvr-grow" onclick="delRow(\''+id+'\')" src="<s:url value="/pages/images/icons8-cancel-25.png"/>" style="cursor: pointer;">' + '</td>'
                '</tr>';

                $('#body_tindakan').append(table);
                $('#modal-tindakan').modal('hide');
                $('#poli').attr('disabled','');
                $('#tin_id_tindakan').val('').trigger('change');
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

    function delRow(id){
        $('#row'+id).remove();
    }

    function listSelectLab(select) {
        var idx = select.selectedIndex;
        if (idx > 0) {
            var idKategori = select.options[idx].value;
            var kat = idKategori.split("|");
            var id = "";
            var ktr = "";

            if(kat[0] != 'null' && kat[0] != ''){
                id = kat[0];
            }

            if(kat[1] != 'null' && kat[1] != ''){
                ktr = kat[1];
            }

            var option = "<option value=''>[Select One]</option>";
            LabAction.listLab(id, function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idLab +'|'+item.namaLab+ "'>" + item.namaLab + "</option>";
                    });
                    $('#lab_lab').html(option);
                } else {
                    $('#lab_lab').html('');
                }
            });
        } else {
            $('#lab_lab').html('');
        }
    }

    function listSelectParameter(select) {
        var idx = select.selectedIndex;
        if (idx > 0) {
            var idLab = select.options[idx].value;
            var option = "";
            if(idLab != ''){
                var labId = idLab.split("|");
                if(labId[0] != 'null' && labId[0] != ''){
                    LabDetailAction.listLabDetail(labId[0], function (response) {
                        if (response != null) {
                            $.each(response, function (i, item) {
                                option += "<option value='" + item.idLabDetail + "'>" + item.namaDetailPeriksa + "</option>";
                            });
                            $('#lab_parameter').html(option);
                        } else {
                            $('#lab_parameter').html('');
                        }
                    });
                }
            }
        } else {
            $('#lab_parameter').html('');
        }
    }

    function saveLab() {

        var idKategori = $('#lab_kategori').val();
        var idLab = $('#lab_lab').val();
        var idParameter = $('#lab_parameter').val();
        var tarif = $('#lab_tarif_paket').val();
        var namaParameter = $('#lab_parameter').text();

        if (idKategori != '' && idLab != '') {

            var idk = idKategori.split("|")[0];
            var idl = idLab.split("|")[0];

            var data = $('#table_lab').tableToJSON();
            var row = data.length;

            var cek = false;

            $.each(data, function (i, item) {
                var jen = data[i]["Pemeriksaan"];
                if(idLab.split("|")[1] == jen){
                    cek = true;
                }
            });

            if(cek){
                $('#warning_lab').show().fadeOut(5000);
                $('#msg_lab').text("Data sudah ada di dalam list...!");
            }else{
                var table = '<tr id="row'+idl+'">' +
                    '<td>'+idLab.split("|")[1]+'<input type="hidden" id="kategori_lab'+row+'" value="'+idk+'">'+'</td>' +
                    '<td>'+idKategori.split("|")[1]+
                    '<input type="hidden" id="lab_id'+row+'" value="'+idl+'">'+
                    '</td>' +
                    '<td>'+ namaParameter + '<input type="hidden" id="parameter_id'+row+'" value="'+idParameter+'"></td>'+
                    '<td align="center">' + '<img border="0" class="hvr-grow" onclick="delRow(\''+idl+'\')" src="<s:url value="/pages/images/icons8-cancel-25.png"/>" style="cursor: pointer;">' + '</td>'+
                    '</tr>';
                $('#body_lab').append(table);
                $('#lab_parameter').val('').trigger('change')
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

    function savePaket(){
        var idPelayanan = $('#poli').val();
        var namaPaket = $('#nama_paket').val();
        var tarifPaket = $('#tarif_paket').val();

        var tindakan = $('#table_tindakan').tableToJSON();
        var lab = $('#table_lab').tableToJSON();
        var result = [];

        $.each(tindakan, function (i, item) {
            var idTindakan = $('#tindakan_id'+i).val();
            var idKategori = $('#kategori_id'+i).val();
            var tarifTindakan = $('#tin_tarif_id'+i).val();
            result.push({'kategori_item':idKategori, 'id_item':idTindakan, 'jenis_item':'tindakan', 'tarif':tarifTindakan});
        });

        $.each(lab, function (i, item) {
            var idKategori = $('#kategori_lab'+i).val();
            var idLab = $('#lab_id'+i).val();
            var idParameter = $('#parameter_id'+i).val();
            var jenisLab = lab[i]["Jenis Lab"];

            if(idParameter != '' && idParameter != 'null'){
                var params = idParameter.split(",");
                for(i = 0; i < params.length; i++){
                    result.push({'kategori_item':idLab, 'id_item':params[i], 'jenis_item':jenisLab.toLowerCase()});
                }
            }else{
                result.push({'kategori_item':idLab, 'id_item':'', 'jenis_item':jenisLab.toLowerCase(), 'tarif':"0"});
            }
        });

        var jsonStinng = JSON.stringify(result);

        if(result.length > 0 && namaPaket != '' && tarifPaket != ''){
            $("#waiting_dialog").dialog('open');
            dwr.engine.setAsync(true);
            PaketPeriksaAction.savePaket(idPelayanan, namaPaket, tarifPaket, jsonStinng, {callback: function (response) {
                    if(response.status == "success"){
                        $("#waiting_dialog").dialog('close');
                        $('#info_dialog').dialog('open');
                        $('body').scrollTop(0);
                    }else{
                        $("#waiting_dialog").dialog('close');
                        $('#error_dialog').dialog('open');
                        $('#errorMessage').text(response.msg);

                    }
                }});
        }else{
            $('#warning_paket').show().fadeOut(5000);
            $('#msg_paket').text("Silahkan cek kembali data inputan anda..!");
            if(namaPaket == ''){
                $('#war_paket').show();
            }
            if(tarifPaket == ''){
                $('#war_tarif_paket').show();
            }
        }
    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>