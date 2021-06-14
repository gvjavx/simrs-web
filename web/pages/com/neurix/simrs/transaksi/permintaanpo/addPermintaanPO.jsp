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

        var branchLogin = '<s:property value="permintaanVendor.branchId" />';

        function formatRupiah2(angka) {
            if (angka != ''){
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
        }

        function formatRupiah(angka) {
            if(angka != ''){
                var reverse = angka.toString().split('').reverse().join(''),
                    ribuan = reverse.match(/\d{1,3}/g);
                ribuan = ribuan.join('.').split('').reverse().join('');
                return ribuan;
            }
        }

        $(document).ready(function () {
            status_n = [];
            nilai_n = 0;

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
                                                  onchange="var warn =$('#war_po_vendor').is(':visible'); if (warn){$('#cor_po_vendor').show().fadeOut(3000);$('#war_po_vendor').hide()};showListObat()"
                                                  headerKey="" headerValue=" - "
                                                  cssClass="form-control select2"/>
                                        <span style="color: red; display: none;"
                                           id="war_po_vendor"><i class="fa fa-times"></i> required</span>
                                        <span style="color: green; display: none;"
                                           id="cor_po_vendor"><i class="fa fa-check"></i> correct</span>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-md-4" style="margin-top: 7px">Nama Obat</label>--%>
                                    <%--<div class="col-md-8">--%>
                                        <%--<input placeholder="masukkan minimal 3 karakter nama obat" class="form-control" style="margin-top: 7px" id="nama_obat" oninput="var warn =$('#war_po_obat').is(':visible'); if (warn){$('#cor_po_obat').show().fadeOut(3000);$('#war_po_obat').hide()}; resetField(this.value);">--%>
                                        <%--<span style="color: red; display: none;"--%>
                                              <%--id="war_po_obat"><i class="fa fa-times"></i> required</span>--%>
                                        <%--<span style="color: green; display: none;"--%>
                                              <%--id="cor_po_obat"><i class="fa fa-check"></i> correct</span>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <input id="id_obat" type="hidden">
                                <input id="id_pabrik" type="hidden">
                                <input id="lb_bx" type="hidden">
                                <input id="bj_lb" type="hidden">
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-md-4" style="margin-top: 7px">Tipe Obat</label>--%>
                                    <%--<div class="col-md-8">--%>
                                        <%--<s:select list="#{'bpjs':'BPJS','umum':'UMUM'}"--%>
                                                  <%--cssStyle="margin-top: 7px; width: 100%"--%>
                                                  <%--onchange="var warn =$('#war_po_tipe').is(':visible'); if (warn){$('#cor_po_tipe').show().fadeOut(3000);$('#war_po_tipe').hide()};"--%>
                                                  <%--id="tipe_obat"--%>
                                                  <%--headerKey="" headerValue="[Select one]"--%>
                                                  <%--cssClass="form-control select2"/>--%>
                                        <%--<span style="color: red; display: none;"--%>
                                           <%--id="war_po_tipe"><i class="fa fa-times"></i> required</span>--%>
                                        <%--<span style="color: green; display: none;"--%>
                                           <%--id="cor_po_tipe"><i class="fa fa-check"></i> correct</span>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-md-4" style="margin-top: 7px">Nomor Produksi</label>--%>
                                    <%--<div class="col-md-8">--%>
                                        <%--<input class="form-control" style="margin-top: 7px" id="kode_produksi" oninput="var warn =$('#war_kode_produksi').is(':visible'); if (warn){$('#cor_kode_produksi').show().fadeOut(3000);$('#war_kode_produksi').hide()};">--%>
                                        <%--<span style="color: red; display: none;"--%>
                                              <%--id="war_kode_produksi"><i class="fa fa-times"></i> required</span>--%>
                                        <%--<span style="color: green; display: none;"--%>
                                              <%--id="cor_kode_produksi"><i class="fa fa-check"></i> correct</span>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
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
                                                    <i class="fa fa-check"></i> Yes
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
                                <label class="col-md-4" style="margin-top: 7px">Tipe Obat</label>
                                <div class="col-md-8">
                                <s:select list="#{'bpjs':'BPJS'}"
                                cssStyle="margin-top: 7px; width: 100%"
                                onchange="var warn =$('#war_po_tipe').is(':visible'); if (warn){$('#cor_po_tipe').show().fadeOut(3000);$('#war_po_tipe').hide()};setTipeObat()"
                                id="tipe_obat"
                                headerKey="umum" headerValue="UMUM"
                                cssClass="form-control"/>
                                <span style="color: red; display: none;"
                                id="war_po_tipe"><i class="fa fa-times"></i> required</span>
                                <span style="color: green; display: none;"
                                id="cor_po_tipe"><i class="fa fa-check"></i> correct</span>
                                </div>
                                    <input type="hidden" id="h_tipe_obat"/>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-md-4" style="margin-top: 7px">Jml Lembar/Box</label>--%>
                                    <%--<div class="col-md-8">--%>
                                        <%--<input class="form-control" id="lembar_perbox" type="number" value="1"--%>
                                               <%--style="margin-top: 7px"--%>
                                               <%--oninput="var warn =$('#war_po_lembar_perbox').is(':visible'); if (warn){$('#cor_po_lembar_perbox').show().fadeOut(3000);$('#war_po_lembar_perbox').hide()};"--%>
                                               <%--onchange="cekFisik()"/>--%>
                                        <%--<span style="color: red; display: none;"--%>
                                           <%--id="war_po_lembar_perbox"><i class="fa fa-times"></i> required</span>--%>
                                        <%--<span style="color: green; display: none;"--%>
                                           <%--id="cor_po_lembar_perbox"><i class="fa fa-check"></i> correct</span>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-md-4" style="margin-top: 7px">Jml Biji/Lembar</label>--%>
                                    <%--<div class="col-md-8">--%>
                                        <%--<input class="form-control" id="biji_perlembar" type="number"--%>
                                               <%--style="margin-top: 7px" onchange="cekFisik()" value="1"--%>
                                               <%--oninput="var warn =$('#war_po_biji_perlembar').is(':visible'); if (warn){$('#cor_po_biji_perlembar').show().fadeOut(3000);$('#war_po_biji_perlembar').hide()};"/>--%>
                                        <%--<span style="color: red; display: none;"--%>
                                           <%--id="war_po_biji_perlembar"><i class="fa fa-times"></i> required</span>--%>
                                        <%--<span style="color: green; display: none;"--%>
                                           <%--id="cor_po_biji_perlembar"><i class="fa fa-check"></i> correct</span>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-md-4" style="margin-top: 7px">Pabrik Obat</label>--%>
                                    <%--<div class="col-md-8">--%>
                                        <%--<select class="form-control" id="combo-pabrik"--%>
                                               <%--style="margin-top: 7px"--%>
                                               <%--oninput="var warn =$('#war_combo_pabrik').is(':visible'); if (warn){$('#cor_combo_pabrik').show().fadeOut(3000);$('#war_combo_pabrik').hide()};">--%>
                                        <%--</select>--%>
                                        <%--<span style="color: red; display: none;"--%>
                                           <%--id="war_combo_pabrik"><i class=R"fa fa-times"></i> required</span>--%>
                                        <%--<span style="color: green; display: none;"--%>
                                           <%--id="cor_combo_pabrik"><i class="fa fa-check"></i> correct</span>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            </div>
                        </div>
                        <div class="box-header with-border"></div>
                        <div class="row" style="margin-top: 20px">
                            <div class="col-md-6">
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-md-4" style="margin-top: 7px">Jenis Satuan</label>--%>
                                    <%--<div class="col-md-8">--%>
                                        <%--<s:select list="#{'lembar':'Lembar'}"--%>
                                                  <%--cssStyle="margin-top: 7px; width: 100%"--%>
                                                  <%--onchange="var warn =$('#war_po_jenis').is(':visible'); if (warn){$('#cor_po_jenis').show().fadeOut(3000);$('#war_po_jenis').hide()};"--%>
                                                  <%--id="jenis_satuan"--%>
                                                  <%--headerKey="biji" headerValue="Biji"--%>
                                                  <%--cssClass="form-control select2" disabled="true"/>--%>
                                        <%--<span style="color: red; display: none;"--%>
                                           <%--id="war_po_jenis"><i class="fa fa-times"></i> required</span>--%>
                                        <%--<span style="color: green; display: none;"--%>
                                           <%--id="cor_po_jenis"><i class="fa fa-check"></i> correct</span>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-md-4" style="margin-top: 7px">Jumlah</label>--%>
                                    <%--<div class="col-md-8">--%>
                                        <%--<input class="form-control" id="jumlah" type="number"--%>
                                               <%--style="margin-top: 7px"--%>
                                               <%--oninput="var warn =$('#war_po_jumlah').is(':visible'); if (warn){$('#cor_po_jumlah').show().fadeOut(3000);$('#war_po_jumlah').hide()};"/>--%>
                                        <%--<span style="color: red; display: none;"--%>
                                           <%--id="war_po_jumlah"><i class="fa fa-times"></i> required</span>--%>
                                        <%--<span style="color: green; display: none;"--%>
                                           <%--id="cor_po_jumlah"><i class="fa fa-check"></i> correct</span>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            </div>
                            <input type="hidden" id="h_harga_awal">
                            <div class="col-md-6">
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-md-4" style="margin-top: 7px">Harga Awal / Item</label>--%>
                                    <%--<div class="col-md-8">--%>
                                        <%--<div class="input-group" style="margin-top: 7px; width: 100%">--%>
                                            <%--<div class="input-group-addon" style="width: 15%">--%>
                                                <%--Rp.--%>
                                            <%--</div>--%>
                                            <%--<input class="form-control" id="harga"--%>
                                                   <%--oninput="var warn =$('#war_po_harga').is(':visible'); if (warn){$('#cor_po_harga').show().fadeOut(3000);$('#war_po_harga').hide()}; convertRpAtas(this.id, this.value, 'h_harga_awal');"/>--%>
                                        <%--</div>--%>
                                        <%--<span style="color: red; display: none;"--%>
                                           <%--id="war_po_harga"><i class="fa fa-times"></i> required</span>--%>
                                        <%--<span style="color: green; display: none;"--%>
                                           <%--id="cor_po_harga"><i class="fa fa-check"></i> correct</span>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Tanggal Penyerahan</label>
                                    <div class="col-md-8">
                                        <div class="input-group" style="margin-top: 7px; width: 100%">
                                            <div class="input-group-addon" style="width: 15%">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input readonly style="cursor: pointer" placeholder="dd-mm-yyyy *klik untuk pilih tanggal" class="form-control datepicker2 datemask2" id="tgl_cair"
                                                   onchange="var warn =$('#war_po_cair').is(':visible'); if (warn){$('#cor_po_cair').show().fadeOut(3000);$('#war_po_cair').hide()};"/>
                                        </div>
                                        <span style="color: red; display: none;"
                                           id="war_po_cair"><i class="fa fa-times"></i> required</span>
                                        <span style="color: green; display: none;"
                                           id="cor_po_cair"><i class="fa fa-check"></i> correct</span>
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
                                        <%--<a type="button" class="btn btn-success" onclick="addToListPo()"><i--%>
                                                <%--class="fa fa-arrow-down"></i> Insert</a>--%>
                                        <%--<a type="button" class="btn btn-danger" onclick="reset()"><i--%>
                                                <%--class="fa fa-refresh"></i> Reset</a>--%>
                                    </div>
                                </div>
                            </div>
                            <%--<div class="col-md-6">--%>
                                <%--<div class="form-group">--%>
                                    <%--<div class="col-md-12 ">--%>
                                        <%--<ul class="pull-right">--%>
                                            <%--<li>LB/BX = Jml Lembar/Box</li>--%>
                                            <%--<li>BJ/LB = Jml Biji/Lembar</li>--%>
                                        <%--</ul>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-file-text-o"></i> Daftar PO <span id="title_tipe_obat"></span></h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered table-striped" style="font-size: 13px" id="tabel_po">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td align="center" width="5%">Pilih</td>
                                <td>ID Obat</td>
                                <td>Nama Obat</td>
                                <td align="center">Jenis Satuan</td>
                                <td align="center">Qty</td>
                                <td align="center">Harga Awal/Item (Rp.)</td>
                                <td align="center">Total Harga (Rp.)</td>
                            </tr>
                            </thead>
                            <tbody id="body_po">
                            </tbody>
                            <tbody>
                                <tr>
                                    <td colspan="6" align="right">Total : </td>
                                    <td id="total-request"></td>
                                </tr>
                            </tbody>
                        </table>
                        <div id="paging_po" style="float:right">

                        </div>
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
                                <a type="button" class="btn btn-danger" onclick="reset()"><i
                                        class="fa fa-refresh"></i> Reset</a>
                                <a type="button" class="btn btn-success" onclick="confirm()"><i
                                        class="fa fa-check"></i> Save</a>
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
                $.each(data, function (i, item) {
                    var labelItem =  item.idObat+"-"+ item.namaObat;
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
            // if(selectedObj.isBpjs == "Y"){
            //     $('#tipe_obat').val('bpjs').trigger('change').attr('disabled', true);
            // }else{
            //     $('#tipe_obat').val('umum').trigger('change').attr('disabled', true);
            // }
            $('#lembar_perbox, #lb_bx').val(selectedObj.lb);
            $('#biji_perlembar, #bj_lb').val(selectedObj.bj);
            $('#jumlah, #harga').val('');
            $('#warning_fisik').html('');
            $('#id_obat').val(selectedObj.id);
            $('#id_pabrik').val(selectedObj.idPabrik);
            // showComboPabrikObat(selectedObj.id);
            return selectedObj.nama;
        }
    });

    function reset() {
        window.location.reload(true);
    }

    function confirm() {
//        var data = $('#tabel_po').tableToJSON();
//        var stringData = JSON.stringify(data);

        var vendor = $('#nama_vendor').val();
        var ditemukan = false;
        $.each(arrListRequest, function(i, item){
            if (item.qty == '' || parseInt(item.qty) == 0){
                detemukan = true;
            }

            if (item.hargaitem == '' || parseInt(item.hargaitem) == 0){
                detemukan = true;
            }
        });

        if (!ditemukan && vendor != '') {
            $('#confirm_dialog').dialog('open');
        } else {
            $('#warning_po').show().fadeOut(5000);
            $('#msg_po').text('Silahkan cek kembali data inputan...!');
        }
    }

    function resetField(obat) {
        var lembarPerBox = "";
        var bijiPerLembar = "";

        if (obat != '') {
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
        $('#jumlah, #harga').val('');
        $('#warning_fisik').html('');
    }

    var nilai_n = 0;
    var status_n = [];
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
        var kodeProduksi = $('#kode_produksi').val();

        var idObat = $('#id_obat').val();
        var idpabrik = $("#combo-pabrik").val();
        var namapabrik = $("#combo-pabrik option:selected").text();
        var hHargaAwal = $('#h_harga_awal').val();

        var cek = false;

        if (namaObat != '' && vendor != '' && jenis != '' && parseInt(jumlah) > 0 && harga != '' && tipe != '' && tgl != '') {
            $.each(data, function (i, item) {
                if(data[i]["ID Obat"] == idObat){
                    cek = true;
                }
            });

            if (cek) {
                $('#warning_po').show().fadeOut(5000);
                $('#msg_po').text('Nama Obat '+namaObat+' sudah tersedia dalam list...!');
            } else {
                $('#imgInp').attr('disabled',false).removeAttr('style');
                $('#id_vendor').val(vendor);

                var tempTotal = replaceTitik(harga);
                var total = tempTotal * jumlah;
                var valTotal = formatRupiahAtas(total);

                var row = '<tr id=' + idObat + "-"+nilai_n+'>' +
                        '<td>' + idObat + '' +
                            '<input type="hidden" id="ind-'+nilai_n+'" value="'+nilai_n+'"/>' +
                            '<input type="hidden" id="id-obat-'+nilai_n+'" value="'+idObat+'">' +
                            // '<input type="hidden" id="kode-produksi-'+nilai_n+'" value="'+kodeProduksi+'">' +
                        '</td>' +
                        '<td>' + namaObat + '<input type="hidden" id="nama-obat-'+nilai_n+'" value="'+namaObat+'"></td>' +
                        '<td align="center">' + jumlah + '<input type="hidden" id="jumlah-'+nilai_n+'" value="'+jumlah+'"></td>' +
                        '<td align="center">' + jenis + '<input type="hidden" id="jenis-'+nilai_n+'" value="'+jenis+'"></td>' +
                        // '<td align="center">' + lembarPerBox + '<input type="hidden" id="lembar-per-box-'+nilai_n+'" value="'+lembarPerBox+'"></td>' +
                        // '<td align="center">' + bijiPerLembar + '<input type="hidden" id="biji-per-lembar-'+nilai_n+'" value="'+bijiPerLembar+'"></td>' +
                        '<td align="right">' + harga + '<input type="hidden" id="harga-'+nilai_n+'" value="'+harga+'"></td>' +
                        '<td align="center">' + valTotal + '<input type="hidden" id="harga-total-'+nilai_n+'" value="'+valTotal+'"></td>' +
                        // '<td align="center">' + namapabrik + '<input type="hidden" id="id-pabrik-'+nilai_n+'" value="'+idpabrik+'"></td>' +
                        '<td align="center"><img border="0" onclick="delRowObat(\'' + idObat + '\', \''+nilai_n+'\')" class="hvr-grow" src="<s:url value="/pages/images/cancel-flat-new.png"/>" style="cursor: pointer;"></td>' +
                        '</tr>';

                        // push status & index;
                        status_n.push({ "id": nilai_n, "status":"add"});
                        nilai_n = nilai_n + 1;

                $('#body_po').append(row);
                if(tipe == "bpjs"){
                    $('#title_tipe_obat').text("dengan tipe Obat BPJS");
                }else{
                    $('#title_tipe_obat').text("dengan tipe Obat UMUM");
                }
                $('#nama_vendor').attr('disabled', true);
                $('#tgl_cair').attr('disabled',true);
                $('#tipe_obat').attr('disabled',true);

                $('#nama_obat').val('');
                $('#jumlah').val('');
                $('#harga').val('');
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
            if (idpabrik == '') {
                $('#war_combo_pabrik').show();
            }
            if (kodeProduksi == '') {
                $('#war_kode_produksi').show();
            }

            $('#warning_po').show().fadeOut(5000);
            $('#msg_po').text('Silahkan cek kembali data inputan...!');
        }
    }

    function setTipeObat() {
        var tipe = $("#tipe_obat option:selected").val();
        $("#h_tipe_obat").val(tipe);
    }

    function updateStatusForDelete(id) {
        //Find index of specific object using findIndex method.
        var objIndex = status_n[id];
        objIndex.status = "delete";
    }

    function delRowObat(id, ind) {
        updateStatusForDelete(ind);
        $('#' + id + "-" + ind).remove();
        var cek = $('#tabel_po').tableToJSON();
        if(cek == 0){
            $('#nama_vendor').attr('disabled', false);
            $('#tgl_cair').attr('disabled', false);
            $('#tipe_obat').attr('disabled', false);
            $('#title_tipe_obat').text("");
        }
    }

    function savePermintaanPO() {
        $('#confirm_dialog').dialog('close');
        var result = [];

        //var tipe = $("#h_tipe_obat").val();
        var tipe = $("#tipe_obat option:selected").val();
        var tipeObat = "";
        if (tipe == "bpjs"){
            tipeObat = "Y";
        } else {
            tipeObat = "N";
        }

        var list_aktif = [];
//        $.each(status_n, function (i, item) {
//
//            var statusobj = status_n[i];
//            if (statusobj.status != "delete"){
//
//                var hargaRaw = $("#harga-"+i).val();
//                var harga = replaceTitik(hargaRaw);
//                var totalHarga = replaceTitik($("#harga-total-"+i).val());
//                var idObat = $("#id-obat-"+i).val();
//                var namaObat = $("#nama-obat-"+i).val();
//                var qty = $("#jumlah-"+i).val();
//                var jenis = $("#jenis-"+i).val();
//                var lembarPerBox = $("#lembar-per-box-"+i).val();
//                var bijiPerLembar = $("#biji-per-lembar-"+i).val();
//                var idPabrikObat = $("#id-pabrik-"+i).val();
//                var kodeProduksi = $('#kode-produksi-'+i).val();
//
//                result.push({
//                    'id_obat':idObat,
//                    'nama_obat':namaObat,
//                    'qty':qty,
//                    'jenis_satuan':jenis,
//                    'lembar_per_box':lembarPerBox,
//                    'biji_per_lembar':bijiPerLembar,
//                    'harga':harga,
//                    'tipe_obat':tipeObat,
//                    'id_pabrik_obat':idPabrikObat,
//                    'nomor_produksi':kodeProduksi
//                });
//            }
//        });

        $.each(arrListRequest, function (i, item) {

            if (item.pilih == "Y"){
                result.push({
                    "id_obat":item.idobat,
                    "nama_obat":item.namaobat,
                    "jenis_satuan":"biji",
                    "qty":item.qty,
                    "harga":item.hargaitem,
                    "tipe_obat":tipeObat
                });
            }
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


    // combo pabrik
    function showComboPabrikObat(id){
        PermintaanVendorAction.getListPabrikObatForPo(id, "specific", function (data) {
            if (data.length == 0){
                showAllPabrik(id);
            } else {
                var str = '';
                $.each(data, function(i, item){
                   str += "<option value='"+item.id+"'>"+item.nama+"</option>";
                });
                str += "<option value='lain'> - Show Other - </option>";
                $("#combo-pabrik").html(str);
            }
        });
    }

    $("#combo-pabrik").change(function () {
        var selected = $("#combo-pabrik option:selected").val();
        if ( selected ==  "lain" ){
            showAllPabrik("");
        }
    })

    function showAllPabrik(id) {
        PermintaanVendorAction.getListPabrikObatForPo(id, "all", function(data){
            var str = "";
            $.each(data, function (i, item) {
                str += "<option value='"+item.id+"'>"+item.nama+"</option>";
            });
            $("#combo-pabrik").html(str);
        });
    }
    // END

    var arrObat = [];
    var arrPaging = [];
    var arrListRequest = [];

    function showListObat(){
        $("#body_po").html("");
        $("#paging_po").html("");
        arrListRequest = [];
        generateListObat();
        selectPage(1);
        generatePaging(1, 'normal');
    }

    function generateListObat() {

        arrObat = [];
        arrPaging = [];

        var idVendor = $("#nama_vendor option:selected").val();
        var step = 10;

        PermintaanVendorAction.getListObatByVendor(branchLogin, idVendor, function(res){

            if (res.length > 0){

                var fromnum         = 0;
                var tonum           = 0;
                $.each(res, function(i, item){

                    if (arrObat.length == 0){
                        fromnum = i;
                    }

                    arrObat.push({"ind":i, "idobat": item.idObat, "nama":item.namaObat, "idvendor":item.idVendor});
                    var x = arrObat.length;
                    var z = x % step;

                    if (z == 0){
                        tonum = i;
                        arrPaging.push({"nomor":parseInt(arrPaging.length) + 1, "fromnum":fromnum, "tonum":tonum});
                        fromnum = parseInt(i) + 1;
                    }
                });

                var x = res.length;
                var z = x % step;

                if (z > 0){
                    arrPaging.push({"nomor":parseInt(arrPaging.length) + 1, "fromnum":x - z, "tonum":x - 1});
                }
            };
        });
    }

    function generatePaging(nomor, jenis) {

        var step    = 7;
        var str     = "";
        if (parseInt(nomor) < step && arrPaging.length > step && jenis == "normal"){

            for (var i = 1 ; i <= step ; i++){
                if (parseInt(nomor) == i){
                    str += "<button class='btn btn-sm btn-primary' id='btn-page-"+i+"' onclick=\"generatePaging(\'"+i+"\', \'normal\')\">"+i+"</button>";
                } else {
                    str += "<button class='btn btn-sm btn-default' id='btn-page-"+i+"' onclick=\"generatePaging(\'"+i+"\', \'normal\')\">"+i+"</button>";
                }
            }

            str += "<button class='btn btn-sm btn-default' id='btn-page-"+arrPaging.length+"' onclick=\"generatePaging(\'"+arrPaging.length+"\', \'last\')\" style='padding-top: 8px; padding-bottom: 8px'><i class='fa fa-angle-double-right'></i></button>";

        } else if (parseInt(nomor) >= step && arrPaging.length > step && jenis == "normal"){

            var tigaKebelakang  = parseInt(nomor) - 3;
            var tigaKedepan     = parseInt(nomor) + 4;

            str += "<button class='btn btn-sm btn-default' id='btn-page-"+1+"' onclick=\"generatePaging(\'1\', \'normal\')\" style='padding-top: 8px; padding-bottom: 8px'><i class='fa fa-angle-double-left'></i></button>";

            for (var i = tigaKebelakang ; i < nomor ; i++){
                str += "<button class='btn btn-sm btn-default' id='btn-page-"+i+"' onclick=\"generatePaging(\'"+i+"\', \'normal\')\">"+i+"</button>";
            }

            str += "<button class='btn btn-sm btn-primary' id='btn-page-"+i+"' onclick=\"generatePaging(\'"+i+"\', \'normal\')\">"+i+"</button>";

            for (var i = parseInt(nomor) + 1 ; i < tigaKedepan ; i++){
                if (i <= arrPaging.length){
                    str += "<button class='btn btn-sm btn-default' id='btn-page-"+i+"' onclick=\"generatePaging(\'"+i+"\', \'normal\')\">"+i+"</button>";
                }
            }

            if (tigaKedepan <= arrPaging.length){
                str += "<button class='btn btn-sm btn-default' id='btn-page-"+arrPaging.length+"' onclick=\"generatePaging(\'"+arrPaging.length+"\', \'last\')\" style='padding-top: 8px; padding-bottom: 8px'><i class='fa fa-angle-double-right'></i></button>";
            }

        } else if (arrPaging.length < step){

            for (var i = 1 ; i < arrPaging.length ; i++){
                str += "<button class='btn btn-sm btn-default' id='btn-page-"+i+"' onclick=\"generatePaging(\'"+i+"\', \'normal\')\">"+i+"</button>";
            }

        } else if (arrPaging.length == nomor || jenis == "last"){
            str += "<button class='btn btn-sm btn-default' id='btn-page-"+1+"' onclick=\"generatePaging(\'1\', \'normal\')\" style='padding-top: 8px; padding-bottom: 8px'><i class='fa fa-angle-double-left'></i></button>";

            var tigaKebelakang  = parseInt(nomor) - 3;

            for (var i = tigaKebelakang ; i <= nomor ; i++){

                if (i == nomor){
                    str += "<button class='btn btn-sm btn-primary' id='btn-page-"+i+"' onclick=\"generatePaging(\'"+i+"\', \'normal\')\">"+i+"</button>";
                } else {
                    str += "<button class='btn btn-sm btn-default' id='btn-page-"+i+"' onclick=\"generatePaging(\'"+i+"\', \'normal\')\">"+i+"</button>";
                }

            }
        }

        selectPage(nomor);
        $("#paging_po").html(str);

    }

    function selectPage(nomor){

        $("#body_po").html("");

        $.each(arrPaging, function(i, page){
            if (parseInt(page.nomor) == parseInt(nomor)){
                $.each(arrObat, function(n, obat){

                    if (parseInt(obat.ind) >= parseInt(page.fromnum) && parseInt(obat.ind) <= parseInt(page.tonum)){
                        var str = "<tr>" +
                            "<td align='center'><input type='checkbox' id='pilih-"+obat.idobat+"' value='Y' onclick=\"addToListRequest(\'"+obat.idobat+"\')\"/></td>" +
                            "<td>"+obat.idobat+"</td>" +
                            "<td>"+obat.nama+"</td>" +
                            "<td>Satuan Terkecil (Per Item)</td>" +
                            "<td align='right'><input type='number' id='qty-"+obat.idobat+"' class='form-control' oninput=\"updateValueList(\'"+obat.idobat+"\')\" /></td>" +
                            "<td align='right'><input id='harga-item-"+obat.idobat+"' class='form-control' oninput=\"updateValueList(\'"+obat.idobat+"\')\" /></td>" +
                            "<td align='right'><input id='harga-total-"+obat.idobat+"' class='form-control' readonly/></td>" +
                            "</tr>";

                        $("#body_po").append(str);
                        getValueFromArrRequest(obat.idobat);
                    }
                });
            }
        });
    }

    function unselectAll(){
        $.each(arrObat, function(i,item){
            $("#pilih-"+item.idobat).prop('checked', true);
            $("#qty-"+item.idobat).prop('disabled', true);
            $("#harga-item-"+item.idobat).prop('disabled', true);
            $("#harga-total-"+item.idobat).prop('disabled', true);
        });
    }

    function addToListRequest(idobat){
        var dicentang = $("#pilih-"+idobat).is(':checked');
        if (dicentang){

            var listFoundByIdObat = arrListRequest.filter(p => p.idobat == idobat);

            if (listFoundByIdObat == '' || listFoundByIdObat.length == 0){

                arrListRequest.push({"idobat":idobat, "pilih":"Y", "qty":"", "hargaitem":"", "hargatotal":""});

            } else {
                $.each(arrListRequest, function (i, item) {
                    if (item.idobat == idobat){
                        item.pilih = "Y";
                    }
                });
            }

        } else {

            var qty         = $("#qty-"+idobat).val();
            var hargaitem   = $("#harga-item-"+idobat).val();
            var hargatotal  = $("#harga-total-"+idobat).val();

            $.each(arrListRequest, function (i, item) {
                if (item.idobat == idobat){
                    item.pilih      = "N";
                    item.qty        = qty;
                    item.hargaitem  = hargaitem;
                    item.hargatotal = hargatotal;
                }
            });
        }

        getValueFromArrRequest(idobat);
    }

    function getValueFromArrRequest(idobat){

        var listFoundByIdObat = arrListRequest.filter(p => p.idobat == idobat);

        if (listFoundByIdObat.length > 0){
            $.each(listFoundByIdObat, function(i,item){
                $("#qty-"+idobat).val(item.qty);
                $("#harga-item-"+idobat).val(formatRupiah(item.hargaitem));
                $("#harga-total-"+idobat).val(formatRupiah(item.hargatotal));

                if (item.pilih == "Y"){
                    $("#pilih-"+idobat).prop("checked", "true");
                    $("#qty-"+idobat).removeAttr("readOnly");
                    $("#harga-item-"+idobat).removeAttr("readOnly");
                } else {
                    $("#pilih-"+idobat).removeAttr("checked");
                    $("#qty-"+idobat).prop("readOnly", "true");
                    $("#harga-item-"+idobat).prop("readOnly", "true");
                }
            })
        } else {
            $("#pilih-"+idobat).removeAttr("checked");
            $("#qty-"+idobat).prop("readOnly", "true");
            $("#harga-item-"+idobat).prop("readOnly", "true");
        }
    }

    function updateValueList(idobat){
        var qty     = $("#qty-"+idobat).val();
        var harga   = parseRibuan($("#harga-item-"+idobat).val());
        var numbers = /^[0-9]+$/;

        if(harga != undefined){
            if(harga.match(numbers)){
                if (qty == null || qty == '')
                    qty = 0;

                if (harga == null || harga == '')
                    harga = 0;

                var total = parseInt(harga) * parseInt(qty);

                $.each(arrListRequest, function (i, item) {
                    if (item.idobat == idobat){
                        item.qty        = qty;
                        item.hargaitem  = harga;
                        item.hargatotal = total.toString();
                    }
                });

                $("#harga-item-"+idobat).val(formatRupiah2(harga));
                $("#harga-total-"+idobat).val(formatRupiah(total));

                hitungTotal();
            }else{
                $("#harga-item-"+idobat).val('');
                $("#harga-total-"+idobat).val('');
            }
        }else{
            $("#harga-item-"+idobat).val('');
            $("#harga-total-"+idobat).val('');
        }
    }

    function parseRibuan(angka){
        if (angka != ''){

            var spangka = angka.split('.');
            var stTotal = "";
            $.each(spangka, function (i, item) {
                stTotal = stTotal + item;
            });

            return stTotal;
        }
    }

    function hitungTotal(){
        var total = 0;
        $.each(arrListRequest, function(i, item){
            total = parseInt(total) + parseInt(item.hargatotal);
        });
        $("#total-request").html(formatRupiah(total));
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>