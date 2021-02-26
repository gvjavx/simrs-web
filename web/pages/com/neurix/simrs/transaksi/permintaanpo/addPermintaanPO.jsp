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
    <script type='text/javascript'>

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

        function formatRupiah(angka) {
            var reverse = angka.toString().split('').reverse().join(''),
                    ribuan = reverse.match(/\d{1,3}/g);
            ribuan = ribuan.join('.').split('').reverse().join('');
            return ribuan;
        }

        $(document).ready(function () {

            $('#permintaan_po').addClass('active');

            $(document).on('change', '.btn-file :file', function () {
                var input = $(this),
                        label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
                input.trigger('fileselect', [label]);
            });

            $('.btn-file :file').on('fileselect', function (event, label) {

                var input = $(this).parents('.input-group').find(':text'),
                        log = label;

                if (input.length) {
                    input.val(log);
                    $('#saveProfil').click();
                } else {
                    if (log) alert(log);
                }

            });

            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        $('#img-upload').attr('src', e.target.result);
                    }

                    reader.readAsDataURL(input.files[0]);
                }
            }

            $("#imgInp").change(function () {
                readURL(this);
            });

            var nominal = document.getElementById('harga');
            nominal.addEventListener('keyup', function (e) {
                nominal.value = formatRupiah2(this.value);
            });
        });

    </script>

    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanVendorAction.js"/>'></script>

</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>
<%--<div class="pulse"></div>--%>

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Tambah Purchase Order (PO)
        </h1>
    </section>

    <!-- Main content -->
    <section class="content" >
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <%--<div class="box box-primary" style="box-shadow: 5px 10px 18px #555; border-radius: 10px;">--%>
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-plus-square"></i> Data Input PO</h3>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_po">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_po"></p>
                        </div>
                        <div id="warning_fisik"></div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Nama Vendor</label>
                                    <div class="col-md-8">
                                        <s:action id="initVendor" namespace="/permintaanpo"
                                                  name="getComboVendor_permintaanpo"/>
                                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                                  list="#initVendor.listOfVendor" id="nama_vendor"
                                                  name="headerCheckup.idPelayanan" listKey="idVendor"
                                                  listValue="namaVendor"
                                                  onchange="var warn =$('#war_po_vendor').is(':visible'); if (warn){$('#cor_po_vendor').show().fadeOut(3000);$('#war_po_vendor').hide()};"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                        <p style="color: red; display: none;"
                                           id="war_po_vendor"><i class="fa fa-times"></i> required</p>
                                        <p style="color: green; display: none;"
                                           id="cor_po_vendor"><i class="fa fa-check"></i> correct</p>
                                    </div>
                                </div>
                                <input id="id_obat" type="hidden">
                                <input id="id_pabrik" type="hidden">
                                <input id="lb_bx" type="hidden">
                                <input id="bj_lb" type="hidden">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Nama Obat</label>
                                    <div class="col-md-8">
                                        <%--<s:action id="initObat" namespace="/obat"--%>
                                                  <%--name="getListObat_obat"/>--%>
                                        <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                                  <%--list="#initObat.listOfObat" id="nama_obat"--%>
                                                  <%--listKey="idObat + '|' + namaObat + '|' + lembarPerBox + '|' + bijiPerLembar + '|' + idPabrik"--%>
                                                  <%--onchange="var warn =$('#war_po_obat').is(':visible'); if (warn){$('#cor_po_obat').show().fadeOut(3000);$('#war_po_obat').hide()}; resetField(this);"--%>
                                                  <%--listValue="idPabrik +' | '+ namaObat +' | '+'LB/BX:'+lembarPerBox+' | '+'BJ/LB:'+bijiPerLembar"--%>
                                                  <%--headerKey="" headerValue="[Select one]"--%>
                                                  <%--cssClass="form-control select2"/>--%>
                                            <input class="form-control" style="margin-top: 7px" id="nama_obat" oninput="var warn =$('#war_po_obat').is(':visible'); if (warn){$('#cor_po_obat').show().fadeOut(3000);$('#war_po_obat').hide()}; resetField(this);">
                                        <p style="color: red; display: none;"
                                           id="war_po_obat"><i class="fa fa-times"></i> required</p>
                                        <p style="color: green; display: none;"
                                           id="cor_po_obat"><i class="fa fa-check"></i> correct</p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Tipe Obat</label>
                                    <div class="col-md-8">
                                        <s:select list="#{'bpjs':'BPJS','umum':'UMUM'}"
                                                  cssStyle="margin-top: 7px; width: 100%"
                                                  onchange="var warn =$('#war_po_tipe').is(':visible'); if (warn){$('#cor_po_tipe').show().fadeOut(3000);$('#war_po_tipe').hide()};"
                                                  id="tipe_obat"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                        <p style="color: red; display: none;"
                                           id="war_po_tipe"><i class="fa fa-times"></i> required</p>
                                        <p style="color: green; display: none;"
                                           id="cor_po_tipe"><i class="fa fa-check"></i> correct</p>
                                    </div>
                                </div>
                                <div class="form-group" style="display: none">
                                    <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                               resizable="false"
                                               closeOnEscape="false"
                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                               buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         window.location.href = 'initForm_permintaanpo.action';
                                                                                     }
                                                                            }"
                                    >
                                        <s:hidden id="close_pos"></s:hidden>
                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                             name="icon_success">
                                        Record has been saved successfully.
                                    </sj:dialog>

                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-5"></label>
                                    <div class="col-sm-5" style="display: none">

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
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true"
                                                   resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                                         alt="Loading..."/></center>
                                        </sj:dialog>

                                        <sj:dialog id="confirm_dialog" modal="true" resizable="false"
                                                   closeOnEscape="false"
                                                   height="200" width="400" autoOpen="false"
                                                   title="Confirmation Dialog">
                                            <center><img border="0" style="height: 40px; width: 40px"
                                                         src="<s:url value="/pages/images/icon_warning.ico"/>"
                                                         name="icon_success">
                                                Do you want to save this record?
                                            </center>
                                            <br>
                                            <div class="modal-footer">
                                                <a type="button" class="btn btn-warning" style="color: white;"
                                                   onclick="$('#confirm_dialog').dialog('close')">
                                                    <i class="fa fa-times"></i> No
                                                </a>
                                                <a type="button" class="btn btn-success" style="color: white;"
                                                   onclick="savePermintaanPO()">
                                                    <i class="fa fa-arrow-right"></i> Yes
                                                </a>
                                            </div>
                                        </sj:dialog>

                                        <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                                'OK':function() { $('#error_dialog').dialog('close'); }
                                                                            }"
                                        >
                                            <div class="alert alert-danger alert-dismissible">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage"></p>
                                                </label>
                                            </div>
                                        </sj:dialog>

                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Jml Lembar/Box</label>
                                    <div class="col-md-8">
                                        <input class="form-control" id="lembar_perbox" type="number" value="1"
                                               style="margin-top: 7px"
                                               oninput="var warn =$('#war_po_lembar_perbox').is(':visible'); if (warn){$('#cor_po_lembar_perbox').show().fadeOut(3000);$('#war_po_lembar_perbox').hide()};"
                                               onchange="cekFisik()"/>
                                        <p style="color: red; display: none;"
                                           id="war_po_lembar_perbox"><i class="fa fa-times"></i> required</p>
                                        <p style="color: green; display: none;"
                                           id="cor_po_lembar_perbox"><i class="fa fa-check"></i> correct</p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Jml Biji/Lembar</label>
                                    <div class="col-md-8">
                                        <input class="form-control" id="biji_perlembar" type="number"
                                               style="margin-top: 7px" onchange="cekFisik()" value="1"
                                               oninput="var warn =$('#war_po_biji_perlembar').is(':visible'); if (warn){$('#cor_po_biji_perlembar').show().fadeOut(3000);$('#war_po_biji_perlembar').hide()};"/>
                                        <p style="color: red; display: none;"
                                           id="war_po_biji_perlembar"><i class="fa fa-times"></i> required</p>
                                        <p style="color: green; display: none;"
                                           id="cor_po_biji_perlembar"><i class="fa fa-check"></i> correct</p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Pabrik Obat</label>
                                    <div class="col-md-8">
                                        <select class="form-control" id="combo-pabrik"
                                               style="margin-top: 7px"
                                               oninput="var warn =$('#war_combo_pabrik').is(':visible'); if (warn){$('#cor_combo_pabrik').show().fadeOut(3000);$('#war_combo_pabrik').hide()};">
                                        </select>
                                        <p style="color: red; display: none;"
                                           id="war_combo_pabrik"><i class="fa fa-times"></i> required</p>
                                        <p style="color: green; display: none;"
                                           id="cor_combo_pabrik"><i class="fa fa-check"></i> correct</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="box-header with-border"></div>
                        <div class="row" style="margin-top: 20px">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Jenis Satuan</label>
                                    <div class="col-md-8">
                                        <s:select list="#{'box':'Box'}"
                                                  cssStyle="margin-top: 7px; width: 100%"
                                                  onchange="var warn =$('#war_po_jenis').is(':visible'); if (warn){$('#cor_po_jenis').show().fadeOut(3000);$('#war_po_jenis').hide()};"
                                                  id="jenis_satuan"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                        <p style="color: red; display: none;"
                                           id="war_po_jenis"><i class="fa fa-times"></i> required</p>
                                        <p style="color: green; display: none;"
                                           id="cor_po_jenis"><i class="fa fa-check"></i> correct</p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Jumlah</label>
                                    <div class="col-md-8">
                                        <input class="form-control" id="jumlah" type="number"
                                               style="margin-top: 7px"
                                               oninput="var warn =$('#war_po_jumlah').is(':visible'); if (warn){$('#cor_po_jumlah').show().fadeOut(3000);$('#war_po_jumlah').hide()};"/>
                                        <p style="color: red; display: none;"
                                           id="war_po_jumlah"><i class="fa fa-times"></i> required</p>
                                        <p style="color: green; display: none;"
                                           id="cor_po_jumlah"><i class="fa fa-check"></i> correct</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Harga Awal / Item</label>
                                    <div class="col-md-8">
                                        <div class="input-group date">
                                            <div class="input-group-addon" style="margin-top: 7px">
                                                Rp.
                                            </div>
                                            <input class="form-control" id="harga"
                                                   oninput="var warn =$('#war_po_harga').is(':visible'); if (warn){$('#cor_po_harga').show().fadeOut(3000);$('#war_po_harga').hide()};"/>
                                        </div>
                                        <p style="color: red; display: none;"
                                           id="war_po_harga"><i class="fa fa-times"></i> required</p>
                                        <p style="color: green; display: none;"
                                           id="cor_po_harga"><i class="fa fa-check"></i> correct</p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Tanggal Penyerahan</label>
                                    <div class="col-md-8">
                                        <div class="input-group" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input class="form-control datepicker2 datemask2" id="tgl_cair"
                                                   onchange="var warn =$('#war_po_cair').is(':visible'); if (warn){$('#cor_po_cair').show().fadeOut(3000);$('#war_po_cair').hide()};"/>
                                        </div>
                                        <p style="color: red; display: none;"
                                           id="war_po_cair"><i class="fa fa-times"></i> required</p>
                                        <p style="color: green; display: none;"
                                           id="cor_po_cair"><i class="fa fa-check"></i> correct</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="col-md-5 col-md-offset-4">
                                        <a type="button" class="btn btn-success" onclick="addToListPo()"><i
                                                class="fa fa-arrow-down"></i> Insert</a>
                                        <a type="button" class="btn btn-danger" onclick="reset()"><i
                                                class="fa fa-refresh"></i>
                                            Reset</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <div class="col-md-12 ">
                                        <ul class="pull-right">
                                            <li>LB/BX = Jml Lembar/Box</li>
                                            <li>BJ/LB = Jml Biji/Lembar</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-file-text-o"></i> Daftar PO</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered table-striped" id="tabel_po">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID</td>
                                <td>Obat</td>
                                <td align="center">Jumlah</td>
                                <td align="center">Jenis Satuan</td>
                                <td align="center">Jml Lembar/Box</td>
                                <td align="center">Jml Biji/Lembar</td>
                                <td align="center">Harga (Rp.)</td>
                                <td align="center">Tipe</td>
                                <td align="center" width="10%">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_po">
                            </tbody>
                        </table>
                        <br>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-body">
                        <div class="row">
                            <%--<s:form id="uploadForm" enctype="multipart/form-data" method="post" namespace="/permintaanpo" action="uploadImage_permintaanpo.action" theme="simple">--%>
                            <%--<div class="col-md-4">--%>
                                <%--<div class="input-group" id="img_file">--%>
                                                    <%--<span class="input-group-btn">--%>
                                                        <%--<span class="btn btn-default btn-file">--%>
                                                            <%--Browse… <s:file id="imgInp" accept=".jpg" name="fileUpload"--%>
                                                                            <%--onchange="$('#img_file').css('border','')" disabled="true" cssStyle="cursor: no-drop"></s:file>--%>
                                                        <%--</span>--%>
                                                    <%--</span>--%>
                                    <%--<input type="text" class="form-control" readonly placeholder="Upload Dokumen PO">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                                <%--<s:hidden name="permintaanVendor.idVendor" id="id_vendor"></s:hidden>--%>
                                <%--<div style="display: none">--%>
                                <%--<sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="uploadForm"--%>
                                           <%--id="saveProfil" name="save" onCompleteTopics="loadfoto" onSuccessTopics="loadfoto">--%>
                                    <%--<i class="fa fa-save"></i>--%>
                                <%--</sj:submit>--%>
                                <%--</div>--%>
                            <%--</s:form>--%>

                            <div class="col-md-offset-4 col-md-4 text-center">
                                <a type="button" class="btn btn-warning" href="initForm_permintaanpo.action"><i
                                        class="fa fa-arrow-left"></i>
                                    Back</a>
                                <a type="button" class="btn btn-success" onclick="confirm()"><i
                                        class="fa fa-arrow-right"></i> Save</a>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                </div>
            </div>
        </div>
    </section>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript'>

    var functions, mapped;
    $('#nama_obat').typeahead({
        minLength: 3,
        source: function (query, process) {
            functions = [];
            mapped = {};

            var data = [];
            dwr.engine.setAsync(false);

            PermintaanVendorAction.searchObat(query, function (listdata) {
                data = listdata;
            });

            if (data.length > 0) {
                console.log(data);
                $.each(data, function (i, item) {
                    var labelItem =  item.idObat+"-"+item.idPabrik +"-" + item.namaObat;
                    mapped[labelItem] = {
                        id: item.idObat,
                        nama: item.namaObat,
                        idPabrik: item.idPabrik,
                        lb: item.lembarPerBox,
                        bj: item.bijiPerLembar,
                        isBpjs: item.flagBpjs
                    };
                    functions.push(labelItem);
                });
                process(functions);
            }
        },
        updater: function (item) {
            var selectedObj = mapped[item];
            var warn1 = $('#war_po_lembar_perbox').is(':visible');
            if (warn1) {
                $('#cor_po_lembar_perbox').show().fadeOut(3000);
                $('#war_po_lembar_perbox').hide()
            }

            var warn2 = $('#war_po_biji_perlembar').is(':visible');
            if (warn2) {
                $('#cor_po_biji_perlembar').show().fadeOut(3000);
                $('#war_po_biji_perlembar').hide()
            }
            if(selectedObj.isBpjs == "Y"){
                $('#tipe_obat').val('bpjs').trigger('change');
            }else{
                $('#tipe_obat').val('umum').trigger('change');
            }
            $('#lembar_perbox, #lb_bx').val(selectedObj.lb);
            $('#biji_perlembar, #bj_lb').val(selectedObj.bj);
            $('#jenis_satuan').val('').trigger('change');
            $('#jumlah, #harga').val('');
            $('#warning_fisik').html('');
            $('#id_obat').val(selectedObj.id);
            $('#id_pabrik').val(selectedObj.idPabrik);
            return selectedObj.nama;
        }
    });

    function reset() {
        window.location.reload(true);
    }

    function confirm() {
        var data = $('#tabel_po').tableToJSON();
        var stringData = JSON.stringify(data);
        var vendor = $('#nama_vendor').val();
        if (stringData != '[]' && vendor != '') {
            $('#confirm_dialog').dialog('open');
        } else {
            $('#warning_po').show().fadeOut(5000);
            $('#msg_po').text('Silahkan cek kembali data inputan...!');
        }
    }

    function resetField(select) {
        var lembarPerBox = "";
        var bijiPerLembar = "";
        var idx = select.selectedIndex;

        if (idx > 0) {

            var obat = select.options[idx].value;
            if (obat.split('|')[2] != 'null' && obat.split('|')[2] != '') {
                lembarPerBox = obat.split('|')[2];
            }
            if (obat.split('|')[3] != 'null' && obat.split('|')[3] != '') {
                bijiPerLembar = obat.split('|')[3];
            }
            $('#lembar_perbox').val(lembarPerBox);
            $('#biji_perlembar').val(bijiPerLembar);

            var warn1 = $('#war_po_lembar_perbox').is(':visible');
            if (warn1) {
                $('#cor_po_lembar_perbox').show().fadeOut(3000);
                $('#war_po_lembar_perbox').hide()
            }

            var warn2 = $('#war_po_biji_perlembar').is(':visible');
            if (warn2) {
                $('#cor_po_biji_perlembar').show().fadeOut(3000);
                $('#war_po_biji_perlembar').hide()
            }

        }
        $('#jenis_satuan').val('').trigger('change');
        $('#jumlah, #harga').val('');
        $('#warning_fisik').html('');
    }

    function addToListPo() {

        var vendor = $('#nama_vendor').val();
        var namaObat = $('#nama_obat').val();
        var jenis = $('#jenis_satuan').val();
        var jumlah = $('#jumlah').val();
        var harga = $('#harga').val();
        var data = $('#tabel_po').tableToJSON();
        var lembarPerBox = $('#lembar_perbox').val();
        var bijiPerLembar = $('#biji_perlembar').val();
        var tipe = $('#tipe_obat').val();
        var tgl = $('#tgl_cair').val();

        var idObat = $('#id_obat').val();

        var cek = false;

        if (namaObat != '' && vendor != '' && jenis != '' && parseInt(jumlah) > 0 && harga != '' && lembarPerBox != '' && bijiPerLembar != '' && tipe != '' && tgl != '') {
            $.each(data, function (i, item) {
                if (item.ID == idObat) {
                    cek = true;
                }
            });

            if (cek) {
                $('#warning_po').show().fadeOut(5000);
                $('#msg_po').text('Data sudah tersedia dalam list...!');
            } else {
                $('#imgInp').attr('disabled',false).removeAttr('style');
                $('#id_vendor').val(vendor);
                var row = '<tr id=' + idObat + '>' +
                        '<td>' + idObat + '</td>' +
                        '<td>' + namaObat + '</td>' +
                        '<td align="center">' + jumlah + '</td>' +
                        '<td align="center">' + jenis + '</td>' +
                        '<td align="center">' + lembarPerBox + '</td>' +
                        '<td align="center">' + bijiPerLembar + '</td>' +
                        '<td align="right">' + harga + '</td>' +
                        '<td align="center">' + tipe + '</td>' +
                        '<td align="center"><img border="0" onclick="delRowObat(\'' + idObat + '\')" class="hvr-grow" src="<s:url value="/pages/images/icons8-trash-can-25.png"/>" style="cursor: pointer;"></td>' +
                        '</tr>';

                $('#body_po').append(row);
                $('#nama_vendor').attr('disabled', true);
                $('#tgl_cair').attr('disabled',true);
            }
        } else {
            if (namaObat == '') {
                $('#war_po_obat').show();
            }
            if (vendor == '' || vendor == null) {
                $('#war_po_vendor').show();
            }
            if (jenis == '' || jenis == null) {
                $('#war_po_jenis').show();
            }
            if (jumlah <= 0) {
                $('#war_po_jumlah').show();
            }
            if (harga == '') {
                $('#war_po_harga').show();
            }
            if (lembarPerBox == '') {
                $('#war_po_lembar_perbox').show();
            }
            if (bijiPerLembar == '') {
                $('#war_po_biji_perlembar').show();
            }
            if (tipe == '') {
                $('#war_po_tipe').show();
            }
            if (tgl == '') {
                $('#war_po_cair').show();
            }

            $('#warning_po').show().fadeOut(5000);
            $('#msg_po').text('Silahkan cek kembali data inputan...!');
        }
    }

    function delRowObat(id) {
        $('#' + id).remove();
    }

    function savePermintaanPO() {
        $('#confirm_dialog').dialog('close');
//        var data = $('#tabel_po').tableToJSON();
        var result = [];

        $.each(data, function (i, item) {

            var tipe = data[i]["Tipe"];
            console.log(tipe);
            var tipeObat = "";

            if(tipe == "bpjs"){
                tipeObat = "Y";
            }else{
                tipeObat = "N";
            }

            var harga = data[i]["Harga (Rp.)"].replace(/[.]/g, '');

            result.push({
                'id_obat':data[i]["ID"],
                'nama_obat':data[i]["Obat"],
                'qty':data[i]["Jumlah"],
                'jenis_satuan':data[i]["Jenis Satuan"],
                'lembar_per_box':data[i]["Jml Lembar/Box"],
                'biji_per_lembar':data[i]["Jml Biji/Lembar"],
                'harga':harga,
                'tipe_obat':tipeObat
            });
        });

        var stringData = JSON.stringify(result);
        var vendor = $('#nama_vendor').val();
        var tgLCair = $('#tgl_cair').val();
        var tanggal = tgLCair.split("-").reverse().join("-");

        $('#waiting_dialog').dialog('open');
        dwr.engine.setAsync(true);
        PermintaanVendorAction.savePermintaanPO(vendor, tanggal, stringData, {
            callback: function (response) {
                if (response.status == "success") {
                    $('#waiting_dialog').dialog('close');
                    $('#info_dialog').dialog('open');
                    $('body').scrollTop(0);
                } else {
                    $('#error_dialog').dialog('open');
                    $('#errorMessage').text(response.message);
                }
            }
        });
    }

    function cekFisik() {

        var namaObat = $('#nama_obat').val();
        var lembar = $('#lembar_perbox').val();
        var biji = $('#biji_perlembar').val();
        var idObat = $('#id_obat').val();
        var lembarperBox =  $('#lb_bx').val();
        var bijiPerLembar =  $('#bj_lb').val();
        var idPabrik =  $('#id_pabrik').val();

        if (namaObat != '') {
            // if (obat.split('|')[0] != 'null' && obat.split('|')[0] != '') {
            //     idObat = obat.split('|')[0];
            // }
            // if (obat.split('|')[1] != 'null' && obat.split('|')[1] != '') {
            //     namaObat = obat.split('|')[1];
            // }
            // if (obat.split('|')[2] != 'null' && obat.split('|')[2] != '') {
            //     lembarperBox = obat.split('|')[2];
            // }
            // if (obat.split('|')[3] != 'null' && obat.split('|')[3] != '') {
            //     bijiPerLembar = obat.split('|')[3];
            // }
            // if (obat.split('|')[4] != 'null' && obat.split('|')[4] != '') {
            //     idPabrik = obat.split('|')[4];
            // }

            var newLembar = '<p>&nbsp;&nbsp;</p>';
            var newBiji = '<p>&nbsp;&nbsp;</p>';

            if (lembarperBox != lembar && bijiPerLembar != biji) {
                newLembar = '<p> Menjadi ' + '<span style="margin-left: 50px">' + lembar + '</span>' + '</p>';
                newBiji = '<p>Menjadi ' + '<span style="margin-left: 50px">' + biji + '</span>' + '</p>';
            } else {
                if (lembarperBox != lembar) {
                    newLembar = '<p> Menjadi ' + '<span style="margin-left: 50px">' + lembar + '</span>' + '</p>';
                }

                if (bijiPerLembar != biji) {
                    newBiji = '<p>Menjadi ' + '<span style="margin-left: 50px">' + biji + '</span>' + '</p>';
                }
            }

            var warning = '<div class="alert alert-warning alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' +
                    '<h4><i class="icon fa fa-ban"></i> Warning!</h4>' +
                    '<div class="row">' +
                    '<div class="col-md-6">' +
                    '<div class="form-group">' +
                    '<div class="col-md-12">' +
                    '<p><b>Obat teridentifikasi berubah fisik..!</b></p>' +
                    '</div>' +
                    '</div>' +
                    '<div class="form-group">' +
                    '<div class="col-md-4">' +
                    '<p>Kode Produksi</p>' +
                    '</div>' +
                    '<div class="col-md-8">' +
                    '<p>' + idPabrik + '</p>' +
                    '</div>' +
                    '</div>' +
                    '<div class="form-group">' +
                    '<div class="col-md-4" style="margin-top: -8px">' +
                    '<p>Nama Obat</p>' +
                    '</div>' +
                    '<div class="col-md-8" style="margin-top: -8px">' +
                    '<p>' + namaObat + '</p>' +
                    '</div>' +
                    '</div>' +
                    '<div class="form-group">' +
                    '<div class="col-md-4" style="margin-top: -8px">' +
                    '<p>Lembar/Box</p>' +
                    '</div>' +
                    '<div class="col-md-4" style="margin-top: -8px">' +
                    '<p>' + lembarperBox + '</p>' +
                    '</div>' +
                    '<div class="col-md-4" style="margin-top: -8px">' +
                    newLembar +
                    '</div>' +
                    '</div>' +
                    '<div class="form-group">' +
                    '<div class="col-md-4" style="margin-top: -8px">' +
                    '<p>BijiLembar</p>' +
                    '</div>' +
                    '<div class="col-md-4" style="margin-top: -8px">' +
                    '<p>' + bijiPerLembar + '</p>' +
                    '</div>' +
                    '<div class="col-md-4" style="margin-top: -8px">' +
                    newBiji +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>';

            if(lembarperBox == lembar && bijiPerLembar == biji){
                $('#warning_fisik').html('');
            }else{
                $('#warning_fisik').html(warning);
            }

        }
    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>