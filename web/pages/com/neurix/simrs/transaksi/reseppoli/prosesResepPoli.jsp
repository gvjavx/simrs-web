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
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatPoliAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ObatAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TransaksiObatAction.js"/>'></script>
    <script type='text/javascript'>

        function formatRupiah(angka) {
            if (angka != '' && angka != null) {
                var reverse = angka.toString().split('').reverse().join(''),
                        ribuan = reverse.match(/\d{1,3}/g);
                ribuan = ribuan.join('.').split('').reverse().join('');
                return ribuan;
            }else{
                return 0;
            }
        }

        function formatRupiah2(angka, prefix) {
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
            return prefix == undefined ? rupiah : (rupiah ? 'Rp. ' + rupiah : '');
        }

        $(document).ready(function () {

            $('#resep_poli').addClass('active');
            var total = $('#total_bayar').val();
            $('#show_nominal').val("Rp. " + formatRupiah(total));

            var nominal = document.getElementById('nominal_dibayar');
            nominal.addEventListener('keyup', function (e) {
                nominal.value = formatRupiah2(this.value, 'Rp. ');

                var bayar       = nominal.value.replace('Rp. ', '');
                var valBayar    = bayar.replace(/[.]/g, '');
                $('#total_dibayar').val(valBayar);

                var a = parseInt(total);
                var b = parseInt(valBayar);

                if(b >= a){
                    var kembalian = valBayar - total;
                    $('#kembalian').val(""+kembalian);
                    $('#nominal_kembalian').val('Rp. '+formatRupiah(kembalian));
                }else{
                    $('#kembalian').val('');
                    $('#nominal_kembalian').val('');
                }
            });
        });

        function confirm(){

            var total       = $('#total_bayar').val();
            var bayar       = $('#total_dibayar').val();
            var kembalian   = $('#kembalian').val();

            if(bayar != ''){
                if(parseInt(bayar) >= parseInt(total)){
                    $("html, body").animate({ scrollTop: 0 }, 600);
                    $('#confirm_dialog').dialog('open');
                }else{
                    $('#warning_bayar').show().fadeOut(5000);
                    $('#msg_error').text("Jumlah bayar tidak boleh kurang dari total bayar..!");
                }
            }else{
                $('#warning_bayar').show().fadeOut(5000);
                $('#msg_error').text("Silahkan cek kembali data inputan..!");
            }
        }

        $.subscribe('beforeProcessSave', function (event, data) {
            event.originalEvent.options.submit = true;
            $('#confirm_dialog').dialog('close');
            $("html, body").animate({ scrollTop: 0 }, 600);
            $.publish('showDialog');
        });

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $("html, body").animate({ scrollTop: 0 }, 600);
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
            Transaksi Obat Apotek
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
                                <table class="table table-striped" style="margin-top: 20px">
                                    <s:hidden id="no_checkup" name="permintaanResep.noCheckup"></s:hidden>
                                    <s:hidden id="id_palayanan" name="permintaanResep.idPelayanan"></s:hidden>
                                    <s:hidden id="no_detail_checkup" name="permintaanResep.idDetailCheckup"/>
                                    <s:hidden id="id_pasien" name="permintaanResep.idPasien"/>
                                    <tr>
                                        <td width="45%"><b>No Checkup</b></td>
                                        <td>
                                            <table>
                                                <s:label name="permintaanResep.noCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>No Checkup Detail</b></td>
                                        <td>
                                            <table><s:label
                                                    name="permintaanResep.idDetailCheckup"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>NIK</b></td>
                                        <td>
                                            <table><s:label name="permintaanResep.nik"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Nama</b></td>
                                        <td>
                                            <table><s:label name="permintaanResep.namaPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Kelamin</b></td>
                                        <td>
                                            <table><s:label name="permintaanResep.jenisKelamin"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Tempat, Tanggal Lahir</b></td>
                                        <td>
                                            <table><s:label name="permintaanResep.tempatTglLahir"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Jenis Pasien</b></td>
                                        <td>
                                            <table>
                                                <s:label name="permintaanResep.jenisPeriksaPasien"></s:label>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <div style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px; text-align: center"
                                     class="card card-4 pull-right">
                                    <img border="2" id="img_ktp" src="<s:property value="permintaanResep.urlKtp"/>"
                                         style="cursor: pointer; height: 90px; width: 190px; margin-top: 4px">
                                </div>
                                <%--<img border="2" class="card card-4 pull-right" src="<s:url value="/pages/images/ktp-tes.jpg"/>"--%>
                                <%--style="cursor: pointer; margin-top: -90px; height: 100px; width: 200px;">--%>
                                <table class="table table-striped">
                                    <tr>
                                        <td><b>Poli</b></td>
                                        <td>
                                            <table>
                                                <s:label name="permintaanResep.namaPelayanan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Alamat</b></td>
                                        <td>
                                            <table><s:label name="permintaanResep.alamat"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Provinsi</b></td>
                                        <td>
                                            <table><s:label name="permintaanResep.provinsi"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kabupaten</b></td>
                                        <td>
                                            <table><s:label name="permintaanResep.kota"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kecamatan</b></td>
                                        <td>
                                            <table><s:label name="permintaanResep.kecamatan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Desa</b></td>
                                        <td>
                                            <table><s:label name="permintaanResep.desa"></s:label></table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Obat Resep</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Obat</td>
                                <td align="center">Qty</td>
                                <td align="right">Harga Satuan</td>
                                <td align="right">Harga Total</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResultResep" id="listOfResultResep">
                                <tr>
                                    <td><s:property value="namaObat"/></td>
                                    <td align="center"><s:property value="qty"/></td>
                                    <td align="right"><script>var val = <s:property value="harga"/>;
                                    if (val != null && val != '') {
                                        document.write("Rp. " + formatRupiah(val) + ",-")
                                    }</script></td>
                                    <td align="right"> <script>var val = <s:property value="totalHarga"/>;
                                    if (val != null && val != '') {
                                        document.write("Rp. " + formatRupiah(val) + ",-")
                                    }</script></td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Obat Tambahan</h3>
                    </div>
                    <div class="box-body">
                        <button class="btn btn-success btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="showModal(5)"><i class="fa fa-plus"></i> Tambah Obat
                        </button>
                        <button class="btn btn-danger btn-outline" style="margin-bottom: 10px; width: 150px"
                                onclick="resetobat()">
                            <i class="fa fa-refresh"></i> Reset
                        </button>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Obat</td>
                                <td align="center">Qty</td>
                                <td align="right">Harga Satuan</td>
                                <td align="right">Harga Total</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResultObat" id="listOfResultObat">
                                <tr>
                                    <td><s:property value="namaObat"/></td>
                                    <td align="center"><s:property value="qty"/></td>
                                    <td align="right">
                                        <script>var val = <s:property value="harga"/>;
                                        if (val != null && val != '') {
                                            document.write("Rp. " + formatRupiah(val) + ",-")
                                        }</script>
                                    </td>
                                    <td align="right">
                                        <script>var val = <s:property value="totalHarga"/>;
                                        if (val != null && val != '') {
                                            document.write("Rp. " + formatRupiah(val) + ",-")
                                        }</script>
                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>

                    <div class="box-header with-border"></div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_bayar">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_error"></p>
                        </div>
                        <div class="form-group">
                            <s:form id="pembayaranForm" method="post" namespace="/transaksi"
                                    action="pembayaran_transaksi.action"
                                    theme="simple" cssClass="form-horizontal">

                                <div class="form-group">
                                    <label class="control-label col-sm-4">Total</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="show_nominal" cssStyle="margin-top: 7px"
                                                     cssClass="form-control" readOnly="true"/>
                                        <s:hidden name="transaksiObatDetail.idPermintaanResep"/>
                                        <s:hidden name="transaksiObatDetail.totalBayar" id="total_bayar"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Total yang dibayar</label>
                                    <div class="col-sm-4">
                                            <%--<s:textfield id="nominal_dibayar" cssStyle="margin-top: 7px"--%>
                                            <%--required="false"--%>
                                            <%--readonly="false" cssClass="form-control" onkeypress="showChanges()"/>--%>
                                        <input class="form-control" style="margin-top: 7px" id="nominal_dibayar">
                                    </div>
                                    <s:hidden name="transaksiObatDetail.nominal" id="total_dibayar"></s:hidden>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Kembalian</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nominal_kembalian" cssStyle="margin-top: 7px"
                                                     required="false"
                                                     readonly="false" cssClass="form-control" readOnly="true"/>
                                    </div>
                                    <s:hidden name="transaksiObatDetail.kembalian" id="kembalian"></s:hidden>
                                </div>

                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <button type="button" class="btn btn-success" onclick="confirm()"><i
                                                class="fa fa-arrow-right"></i> Bayar
                                        </button>
                                        <a href="initForm_transaksi.action" type="button" class="btn btn-warning"><i
                                                class="fa fa-arrow-left"></i> Back
                                        </a>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-5"></label>
                                    <div class="col-sm-5" style="display: none">

                                        <sj:dialog id="confirm_dialog" modal="true" resizable="false" closeOnEscape="false"
                                                   height="200" width="400" autoOpen="false" title="Confirmation Dialog">
                                            <center><img border="0" style="height: 40px; width: 40px"
                                                         src="<s:url value="/pages/images/icon_warning.ico"/>"
                                                         name="icon_success">
                                                Do you want to save this record?
                                            </center>
                                            <br>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-warning"
                                                        onclick="$('#confirm_dialog').dialog('close')"><i
                                                        class="fa fa-times"></i> No
                                                </button>
                                                <sj:submit targets="crud" type="button" cssClass="btn btn-success"
                                                           formIds="pembayaranForm" id="save" name="save"
                                                           onBeforeTopics="beforeProcessSave"
                                                           onCompleteTopics="closeDialog,successDialog"
                                                           onSuccessTopics="successDialog" onErrorTopics="errorDialog">
                                                    <i class="fa fa-arrow-right"></i>
                                                    yes
                                                </sj:submit>
                                            </div>
                                        </sj:dialog>

                                        <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog"
                                                   modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Saving ...">
                                            Please don't close this window, server is processing your request ...
                                            <br>
                                            <center>
                                                <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                     src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                     name="image_indicator_write">
                                                <br>
                                                <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                     name="image_indicator_write">
                                            </center>
                                        </sj:dialog>

                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false" closeOnEscape="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         window.location.reload(true);
                                                                                     }
                                                                            }"
                                        >
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                            Record has been saved successfully.
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
                            </s:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-obat">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambahkan Obat</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_obat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="obat_error"></p>
                </div>
                <div class="row">
                    <%--<div class="form-group" id="jenis_form">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px">Jenis Obat</label>--%>
                        <%--<div class="col-md-7">--%>
                            <%--<s:action id="initJenis" namespace="/jenisobat"--%>
                                      <%--name="getListJenisObat_jenisobat"/>--%>
                            <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                      <%--list="#initJenis.listOfJenisObat" id="obat_jenis_obat"--%>
                                      <%--listKey="idJenisObat"--%>
                                      <%--listValue="namaJenisObat"--%>
                                      <%--headerKey="" headerValue="[Select one]"--%>
                                      <%--cssClass="form-control select2"/>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                            <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="war_jenis_obat"><i class="fa fa-times"></i> required</p>--%>
                            <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="cor_jenis_obat"><i class="fa fa-check"></i> correct</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group" id="nama_form">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <%--<select class="form-control select2" style="margin-top: 7px; width: 100%" id="ob_id_obat"--%>
                                    <%--onchange="var warn =$('#war_obat').is(':visible'); if (warn){$('#cor_obat').show().fadeOut(3000);$('#war_obat').hide()}; setStokObat(this);">--%>
                                <%--<option value="">[select one]</option>--%>
                            <%--</select>--%>
                                <s:action id="initObatPoli" namespace="/obatpoli"
                                          name="getListObatPoli_obatpoli"/>
                                <s:select cssStyle="margin-top: 7px; width: 100%"
                                          list="#initObatPoli.listOfObatPoli" id="ob_id_obat"
                                          listKey="idObat + '|' + namaObat + '|' + qty"
                                          listValue="namaObat"
                                          onchange="var warn =$('#war_obat').is(':visible'); if (warn){$('#cor_obat').show().fadeOut(3000);$('#war_obat').hide()}; setStokObat(this);"
                                          headerKey="" headerValue="[Select one]"
                                          cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_obat"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_obat">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group" id="nama_obat_form">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <s:textfield readonly="true" type="text" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="nama_obat"></s:textfield>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Stok Obat</label>
                        <div class="col-md-7">
                            <s:textfield readonly="true" type="text" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="ob_stok"></s:textfield>
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
            <input type="hidden" id="set_id_obat">
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_obat" onclick="saveObat()"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_obat"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>


<script type='text/javascript'>

    function showModal(select) {
//        var id = "";
        if (select == 5) {
//            $('#obat_jenis_obat').attr("onchange", "var warn =$('#war_jenis_obat').is(':visible'); if (warn){$('#cor_jenis_obat').show().fadeOut(3000);$('#war_jenis_obat').hide()}; listSelectObat(this);");
//            $('#obat_jenis_obat, #ob_id_obat').val('').trigger('change');
            $('#jenis_form').show();
            $('#nama_form').show();
            $('#nama_obat_form').hide();
            $('#ob_stok').val('');
            $('#ob_qty').val('1');
//            $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\')').show();
            $('#load_obat, #warning_obat, #war_obat, #war_qty_obat').hide();
            $('#modal-obat').modal('show');
        }
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

    function saveObat() {

//        var idJenis = $('#obat_jenis_obat').val();
        var idObat = $('#ob_id_obat').val();
        var qty = $('#ob_qty').val();
        var stok = $('#ob_stok').val();

        if (idObat != '' && qty > 0) {

            if (parseInt(qty) <= parseInt(stok)) {

                var id = idObat.split('|')[0];
                var nama = idObat.split('|')[1];

                $('#save_obat').hide();
                $('#load_obat').show();

                console.log(idObat);
                console.log(id);
                console.log(nama);

                dwr.engine.setAsync(true);
                TransaksiObatAction.saveAddObat(id, qty, function (response) {
                    if (response == "success") {
                        $('#modal-obat').modal('hide');
                        window.location.reload(true);
                    } else {

                    }
                })
            } else {
                $('#warning_obat').show().fadeOut(5000);
                $('#obat_error').text("Jumlah obat tidak boleh melebihi stok..!");
            }
        } else {
            $('#warning_obat').show().fadeOut(5000);
            $('#obat_error').text("Silahkan cek kembali data inputan..!");
            if (idObat == '') {
                $('#war_obat').show();
            }
            if (qty == '' || qty < 1) {
                $('#war_qty_obat').show();
            }
        }
    }

    function resetobat() {
        window.location.href = "/simrs/transaksi/resetobat_transaksi.action";
    }

    function setStokObat(select) {

        if(select != '' && select != null){
            var idx = select.selectedIndex;
            var idObat = select.options[idx].value;
            var stok = 0;

//        if (idObat != '') {
//            ObatAction.getStokObat(idObat, function (response) {
//                if (response != null) {
//                    $.each(response, function (i, item) {
//                        if (item.idObat == idObat) {
//                            if (item.qty != null) {
//                                stok = item.qty;
//                            }
//                        }
//                    });
//                }
//            });
//        }
            var id = idObat.split('|')[0];
            var nama = idObat.split('|')[1];
            var stokOb = idObat.split('|')[2];
            $('#ob_stok').val(stokOb);
        }
        }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>