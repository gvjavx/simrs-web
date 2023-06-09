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
            console.log(angka);
            if (angka != '' && angka != null) {
                var reverse = angka.toString().split('').reverse().join(''),
                        ribuan = reverse.match(/\d{1,3}/g);
                ribuan = ribuan.join('.').split('').reverse().join('');
                return ribuan;
            } else {
                return 0;
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

        $(document).ready(function () {

            $('#transaksi_obat').addClass('active');
            var total = $('#total_bayar').val();
            var ppn = "";
            var totalPlusPpn = "";

            if(total != null && total != ''){
                ppn = (parseInt(total)*0.1);
                totalPlusPpn = parseInt(total) + parseInt(ppn);
                $('#ppn_bayar').val(ppn);
            }

            $('#show_nominal').val(formatRupiah(totalPlusPpn));
            $('#show_ppn').val(formatRupiah(ppn));

            var nominal = document.getElementById('nominal_dibayar');
            nominal.addEventListener('keyup', function (e) {
                nominal.value = formatRupiah2(this.value);
                var valBayar = nominal.value.replace(/[.]/g, '');
                $('#total_dibayar').val(valBayar);

                var a = parseInt(totalPlusPpn);
                var b = parseInt(valBayar);

                if (b >= a) {
                    var kembalian = valBayar - totalPlusPpn;
                    $('#kembalian').val("" + kembalian);
                    $('#nominal_kembalian').val(formatRupiah(kembalian));
                } else {
                    $('#kembalian').val('');
                    $('#nominal_kembalian').val('');
                }
            });
        });

        function confirm() {

            var total = $('#total_bayar').val();
            var bayar = $('#total_dibayar').val();
            var kembalian = $('#kembalian').val();

            if (bayar != '') {
                if (parseInt(bayar) >= parseInt(total)) {
                    $("html, body").animate({scrollTop: 0}, 600);
                    $('#confirm_dialog').dialog('open');
                } else {
                    $('#warning_bayar').show().fadeOut(5000);
                    $('#msg_error').text("Jumlah bayar tidak boleh kurang dari total bayar..!");
                }
            } else {
                $('#warning_bayar').show().fadeOut(5000);
                $('#msg_error').text("Silahkan cek kembali data inputan..!");
            }
        }

        $.subscribe('beforeProcessSave', function (event, data) {
            event.originalEvent.options.submit = true;
            $('#confirm_dialog').dialog('close');
            $("html, body").animate({scrollTop: 0}, 600);
            $.publish('showDialog');
        });

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $("html, body").animate({scrollTop: 0}, 600);
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
            Pembelian Obat Apotek
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar pembelian obat</h3>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-9">
                                <button class="btn btn-success btn-outline" style="margin-bottom: 10px;"
                                        onclick="showModal(5)"><i class="fa fa-plus"></i> Tambah Obat
                                </button>
                                <button class="btn btn-danger btn-outline" style="margin-bottom: 10px;"
                                        onclick="resetobat()">
                                    <i class="fa fa-refresh"></i> Reset
                                </button>
                            </div>
                            <div class="col-md-3">
                                <%--<div class="input-group">--%>
                                <%--<input type="text" class="form-control pull-right" onchange="showListExpired(this.value)">--%>
                                    <%--<div class="input-group-addon">--%>
                                        <%--<i class="fa fa-search"></i>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            </div>
                        </div>
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Nama Obat</td>
                                <td align="center">Qty</td>
                                <td align="center">Harga Satuan (Rp.)</td>
                                <td align="center">Harga Total (Rp.)</td>
                                <td>Jenis Satuan</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResultObat" id="listOfResultObat">
                                <tr>
                                    <td><s:property value="namaObat"/></td>
                                    <td align="center"><s:property value="qtyApprove"/></td>
                                    <td align="right"><script>var val = <s:property value="harga"/>;
                                    if (val != null && val != '') {
                                        document.write(formatRupiah(val))
                                    }</script></td>
                                    <td align="right"> <script>var val = <s:property value="totalHarga"/>;
                                    if (val != null && val != '') {
                                        document.write(formatRupiah(val))
                                    }</script></td>
                                    <td><s:property value="jenisSatuan"/></td>
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
                                    action="pembayaranObatBaru_transaksi.action"
                                    theme="simple" cssClass="form-horizontal">

                                <div class="form-group">
                                    <label class="control-label col-sm-4">Total</label>
                                    <div class="col-sm-4">
                                        <div class="input-group">
                                            <div class="input-group-addon">
                                                Rp.
                                            </div>
                                            <s:textfield  id="show_nominal" cssClass="form-control" readOnly="true"/>
                                        </div>
                                        <s:hidden name="transaksiObatDetail.idPermintaanResep"/>
                                        <s:hidden name="transaksiObatDetail.totalBayar" id="total_bayar"/>
                                        <s:hidden name="transaksiObatDetail.ppnBayar" id="ppn_bayar"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4" style="margin-top: 7px">Ppn</label>
                                    <div class="col-sm-4">
                                        <div class="input-group" style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                Rp.
                                            </div>
                                            <s:textfield id="show_ppn" cssClass="form-control" readOnly="true"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Total yang dibayar</label>
                                    <div class="col-sm-4">
                                        <div class="input-group date" style="margin-top: 7px" >
                                            <div class="input-group-addon">
                                                Rp.
                                            </div>
                                            <input class="form-control"id="nominal_dibayar">
                                        </div>
                                    </div>
                                    <s:hidden name="transaksiObatDetail.idApprovalObat" id="id_approve"/>
                                    <s:hidden name="transaksiObatDetail.nominal" id="total_dibayar"></s:hidden>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Kembalian</label>
                                    <div class="col-sm-4">
                                        <div class="input-group date" style="margin-top: 7px" >
                                            <div class="input-group-addon">
                                                Rp.
                                            </div>
                                            <s:textfield id="nominal_kembalian" required="false"
                                                         readonly="false" cssClass="form-control" readOnly="true"/>
                                        </div>
                                    </div>
                                    <s:hidden name="transaksiObatDetail.kembalian" id="kembalian"></s:hidden>
                                </div>

                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <a href="initForm_transaksi.action" type="button" class="btn btn-warning"><i
                                                class="fa fa-arrow-left"></i> Back
                                        </a>
                                        <button type="button" class="btn btn-success" onclick="confirm()"><i
                                                class="fa fa-arrow-right"></i> Bayar
                                        </button>
                                        <a type="button" class="btn btn-primary" onclick="printPembelian()"><i
                                                class="fa fa-print"></i> Print
                                        </a>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-5"></label>
                                    <div class="col-sm-5" style="display: none">

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
                                                <img class="spin" border="0"
                                                     style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                     name="image_indicator_write">
                                            </center>
                                        </sj:dialog>

                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                                   resizable="false" closeOnEscape="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         toContent();
                                                                                     }
                                                                            }"
                                        >
                                            <s:hidden id="ref"></s:hidden>
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                                 name="icon_success">
                                            Record has been saved successfully.
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
    <div class="modal-dialog modal-lg">
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
                <div class="alert alert-warning alert-dismissible" style="display: none" id="warning_exp">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_exp"></p>
                </div>
                <div class="row">
                    <div class="form-group" id="nama_form">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <s:action id="initObatPoli" namespace="/obatpoli"
                                      name="getListObatPoli_obatpoli"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#initObatPoli.listOfObatPoli" id="ob_id_obat"
                                      listKey="idObat + '|' + namaObat + '|' + qtyBox + '|' + qtyLembar + '|' + qtyBiji + '|' + lembarPerBox + '|' + bijiPerLembar"
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
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Stok Obat</label>
                        <div class="col-md-2">
                            <label style="margin-top: 7px">Box</label>
                            <s:textfield readonly="true" type="text" min="1" cssClass="form-control"
                                        id="ob_qtyBox"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <label style="margin-top: 7px">Lembar</label>
                            <s:textfield readonly="true" type="text" min="1" cssClass="form-control"
                                         id="ob_qtyLembar"></s:textfield>
                        </div>
                        <div class="col-md-3">
                            <label style="margin-top: 7px">Biji</label>
                            <s:textfield readonly="true" type="text" min="1" cssClass="form-control"
                                         id="ob_qtyBiji"></s:textfield>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Satuan</label>
                        <div class="col-md-7">
                            <s:select list="#{'box':'Box','lembar':'Lembar','biji':'Biji'}"
                                      cssStyle="margin-top: 7px; width: 100%"
                                      onchange="var warn = $('#war_ob_jenis_satuan').is(':visible'); if (warn){$('#cor_ob_jenis_satuan').show().fadeOut(3000);$('#war_ob_jenis_satuan').hide()}"
                                      id="ob_jenis_satuan"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_ob_jenis_satuan"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_ob_jenis_satuan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px">Jumlah</label>--%>
                        <%--<div class="col-md-7">--%>
                            <%--<s:textfield value="1" type="number" min="1" cssClass="form-control"--%>
                                         <%--cssStyle="margin-top: 7px" id="ob_qty"--%>
                                         <%--onkeypress="var warn =$('#war_qty_obat').is(':visible'); if (warn){$('#cor_qty_obat').show().fadeOut(3000);$('#war_qty_obat').hide()}"></s:textfield>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                            <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="war_qty_obat"><i class="fa fa-times"></i> required</p>--%>
                            <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="cor_qty_obat"><i class="fa fa-check"></i> correct</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </div>
                <div class="box-header with-border"></div>
                <div class="box">
                    <table class="table table-bordered" id="tabel_list_obat">
                        <thead>
                        <td>ID Barang</td>
                        <td>Expired Date</td>
                        <td align="center">Qty BX</td>
                        <td align="center">Qty LB</td>
                        <td align="center">Qty BJ</td>
                        <td align="center" width="25%">Scan ID Barang</td>
                        <td width="12%" align="center">Qty AP</td>
                        </thead>
                        <tbody id="body_list_obat">
                        </tbody>
                    </table>
                    <p id="loading_data_obat" style="color: #00a65a; display: none"><img src="<s:url value="/pages/images/spinner.gif"/>" style="height: 40px; width: 40px;"> Sedang mengambil data...</p>
                </div>
                <div class="box-header with-border"></div>
                <div class="row">
                    <div class="col-md-4"><i class="fa fa-square" style="color: #eea236"></i> Expired Date Kurang dari 30 hari</div>
                    <div class="col-md-4"><i class="fa fa-square" style="color: #dd4b39"></i> Expired Date Kurang dari 10 hari</div>
                </div>
            </div>
            <input type="hidden" id="set_id_obat">
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_obat"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_obat"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-approve">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Konfirmasi Qty Approve Obat</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_app">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_app"></p>
                </div>
                <table class="table table-striped">
                    <tr>
                        <td width="25%">ID Obat</td>
                        <td><span id="app_id"></span></td>
                    </tr>
                    <tr>
                        <td>Nama Obat</td>
                        <td><span id="app_nama"></span></td>
                    </tr>
                    <tr>
                        <td>Qty Request</td>
                        <td><span id="app_req"></span></td>
                    </tr>
                </table>
                <div class="box">
                    <table class="table table-bordered" id="tabel_approve">
                        <thead>
                        <td>ID Barang</td>
                        <td>Expired Date</td>
                        <td align="center">Qty BX</td>
                        <td align="center">Qty LB</td>
                        <td align="center">Qty BJ</td>
                        <td align="center" width="25%">Scan ID Barang</td>
                        <td width="12%" align="center">Qty AP</td>
                        <td>Jenis Satuan</td>
                        </thead>
                        <tbody id="body_approve">
                        </tbody>
                    </table>
                    <p id="loading_data" style="color: #00a65a; display: none"><img src="<s:url value="/pages/images/spinner.gif"/>" style="height: 40px; width: 40px;"> Sedang mengambil data...</p>
                </div>
                <div class="box-header with-border"></div>
                <div class="row">
                    <div class="col-md-3"><i class="fa fa-square" style="color: #eea236"></i> Kurang dari 30 hari</div>
                    <div class="col-md-3"><i class="fa fa-square" style="color: #dd4b39"></i> Kurang dari 10 hari</div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_app"><i
                        class="fa fa-arrow-right"></i> Konfirmasi
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_app"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
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
                <h4>Do you want save this record?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes            </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-warning">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #dd4b39; color: white">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-warning"></i> Warning
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible">
                    ID Pabrik tidak ditemukan...!
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-list-obat">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Konfirmasi Qty Approve Obat</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_list_barang">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_list_barang"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">ID Obat</label>
                        <div class="col-md-7">
                            <s:textfield readonly="true" type="text" cssClass="form-control"
                                         id="list_id" cssStyle="margin-top: 7px"></s:textfield>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <s:textfield readonly="true" type="text" cssClass="form-control"
                                         id="list_nama" cssStyle="margin-top: 7px"></s:textfield>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Satuan</label>
                        <div class="col-md-7">
                            <s:select list="#{'box':'Box','lembar':'Lembar','biji':'Biji'}"
                                      cssStyle="margin-top: 7px; width: 100%"
                                      onchange="var warn = $('#war_barang_jenis_satuan').is(':visible'); if (warn){$('#cor_barang_jenis_satuan').show().fadeOut(3000);$('#war_barang_jenis_satuan').hide()}"
                                      id="barang_jenis_satuan"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_barang_jenis_satuan"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_barang_jenis_satuan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <br>
                <div class="box">
                    <table class="table table-bordered" id="tabel_list_barang">
                        <thead>
                        <td>ID Barang</td>
                        <td>Expired Date</td>
                        <td align="center">Qty BX</td>
                        <td align="center">Qty LB</td>
                        <td align="center">Qty BJ</td>
                        <td align="center" width="25%">Scan ID Barang</td>
                        <td width="12%" align="center">Qty AP</td>
                        </thead>
                        <tbody id="body_list_barang">
                        </tbody>
                    </table>
                    <p id="loading_data_barang" style="color: #00a65a; display: none"><img src="<s:url value="/pages/images/spinner.gif"/>" style="height: 40px; width: 40px;"> Sedang mengambil data...</p>
                </div>
                <div class="box-header with-border"></div>
                <div class="row">#ccc
                    <div class="col-md-4"><i class="fa fa-square" style="color: #eea236"></i> Expired Date Kurang dari 30 hari</div>
                    <div class="col-md-4"><i class="fa fa-square" style="color: #dd4b39"></i> Expired Date Kurang dari 10 hari</div>
                    <div class="col-md-4"><i class="fa fa-square" style="color: #ccc"></i> Expired Date Telah Habis</div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_barang"><i
                        class="fa fa-arrow-right"></i> Konfirmasi
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_barang"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    var idApprove = $('#id_approve').val();

    function printPembelian() {
        window.open('printPembelianObat_transaksi.action?id='+idApprove,"_blank");
    }
    function toContent(){
        var ref = $('#ref').val();
        if(ref == 1){
            window.location.href = 'initForm_transaksi.action';
        }
    }
    function listExpiredDate(idObat){
        $('#body_list_obat').html('');
        var table = [];
        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        today = mm + '/' + dd + '/' + yyyy;
        var lembarPerBox = "";
        var bijiPerLembar = "";

        if (idObat != "") {
            $('#loading_data_obat').show();
            dwr.engine.setAsync(true);
            TransaksiObatAction.listObatPoliEntityByIdObat(idObat, {
                callback: function (response) {
                    if (response.length > 0) {
                        $.each(response, function (i, item) {
                            var qtyBox = "";
                            var qtyLembar = "";
                            var qtyBiji = "";

                            var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(item.expiredDate));

                            var dateExpired = $.datepicker.formatDate('mm-dd-yy', new Date(item.expiredDate));

                            const date1 = new Date(today);
                            const date2 = new Date(dateExpired);
                            const diffTime = Math.abs(date2 - date1);
                            const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

                            if (item.qtyBox != null) {
                                qtyBox = item.qtyBox;
                            }
                            if (item.qtyLembar != null) {
                                qtyLembar = item.qtyLembar;
                            }
                            if (item.qtyBiji != null) {
                                qtyBiji = item.qtyBiji;
                            }

                            var warna = "";
                            var color = "";
                            var disabled = "";

                            if(Math.abs(date1) > Math.abs(date2)){
                                warna = '#ccc';
                                color = '#fff';
                                disabled = 'disabled';

                            }else if (diffDays < 10) {
                                warna = '#dd4b39';
                                color = 'white';

                            } else if (diffDays < 30) {
                                warna = '#eea236';
                                color = 'white';
                            } else {
                                warna = '#fff';
                                color = '#333';
                            }

                            var idBar = item.idBarang;
                            var str = idBar.substring(8, 15);
                            var idBarang = idBar.replace(str, '*******');

                            table += '<tr bgcolor=' + warna + ' style="color: ' + color + '">' +
                                    '<td>' + '<span>' + idBarang + '</span>' +
                                    '<input type="hidden" id=id_barang'+ i +' value= '+ item.idBarang +'>'+
                                    '</td>' +
                                    '<td>' + dateFormat + '</td>' +
                                    '<td align="center">' + qtyBox + '</td>' +
                                    '<td align="center">' + qtyLembar + '</td>' +
                                    '<td align="center">' + qtyBiji + '</td>' +
                                    '<td>' +
                                    '<div class="input-group">' +
                                    '<input '+disabled+' class="form-control" onchange="cekIdBarang(\'' + i + '\',this.value)">' +
                                    '<div class="input-group-addon">' +
                                    '<span id=loading' + i + '></span> ' +
                                    '</div>' +
                                    '</div>' +
                                    '</td>' +
                                    '<td><input style="display: none" id=newQty' + i + ' type="number" class="form-control" onchange="validasiInput(this.value, \''+qtyBox+'\',\''+qtyLembar+'\',\''+qtyBiji+'\',\''+item.lembarPerBox+'\',\''+item.bijiPerLembar+'\',\''+dateFormat+'\')"></td>' +
                                    '</tr>';

                            lembarPerBox = item.lembarPerBox;
                            bijiPerLembar = item.bijiPerLembar;
                        });

                        $('#save_obat').attr('onclick', 'saveConfirmObat(\'' + idObat + '\',\'' + lembarPerBox + '\',\'' + bijiPerLembar + '\')');
                        $('#body_list_obat').html(table);
                        $('#loading_data_obat').hide();

                    } else {
                        $('#modal-warning').modal('show');
                        $('#loading_data_obat').hide();
                    }
                }
            });
        }else{
            $('#loading_data_obat').show();
        }
    }

    function saveConfirmObat(idObat, lembarPerBox, bijiPerlembar){
        var jenisSatuan = $('#ob_jenis_satuan').val();
        var namaObat = $('#ob_id_obat').val();
        var data = $('#tabel_list_obat').tableToJSON();
        var result = [];
        var qtyApp = 0;
        var qtyBox = 0;
        var qtyLembar = 0;
        var qtyBiji = 0;

        if(jenisSatuan != '' && data.length > 0 && namaObat != ''){
            $.each(data, function (i, item) {
                var expired = data[i]["Expired Date"];
                var expDate = expired.split("-").reverse().join("-");
                var qty = $('#newQty'+i).val();
                var idBarang = $('#id_barang'+i).val();
                result.push({'Expired Date': expDate, 'Qty Approve': qty, 'ID Barang':idBarang, 'Jenis Satuan':jenisSatuan, 'ID Obat':idObat});
            });

            $.each(data, function (i, item) {
                var id = data[i]["Expired Date"];
                var box = data[i]["Qty BX"];
                var lembar = data[i]["Qty LB"];
                var biji = data[i]["Qty BJ"];
                var qty = $('#newQty' + i).val();

                if (qty == "") {
                    qty = 0;
                }
                if (box == "") {
                    box = 0;
                }
                if (lembar == "") {
                    lembar = 0;
                }
                if (biji == "") {
                    biji = 0;
                }

                qtyBox = parseInt(qtyBox) + parseInt(box);
                qtyLembar = parseInt(qtyLembar) + parseInt(lembar);
                qtyBiji = parseInt(qtyBiji) + parseInt(biji);
                qtyApp = parseInt(qtyApp) + parseInt(qty);

            });

            var stok = 0;

            if ("box" == jenisSatuan) {
                stok = qtyBox;
            }
            if ("lembar" == jenisSatuan) {
                stok = parseInt(qtyLembar) + (parseInt(lembarPerBox * parseInt(qtyBox)));
            }
            if ("biji" == jenisSatuan) {
                stok = parseInt(qtyBiji) + ((parseInt(lembarPerBox * parseInt(qtyBox))) * parseInt(bijiPerLembar));
            }

            var stringData = JSON.stringify(result);

            if (qtyApp > 0) {

                if (parseInt(qtyApp) <= parseInt(stok)) {
                    $('#modal-confirm-dialog').modal('show');
                    $('#save_con').attr('onclick','saveBatchObat(\'' + idObat + '\',\'' + stringData + '\')');
                } else {
                    $('#warning_obat').show().fadeOut(5000);
                    $('#obat_error').text("Qty Approve tidak boleh melebihi stok dan qty request..!");
                }
            } else {
                $('#warning_obat').show().fadeOut(5000);
                $('#obat_error').text("Qty Approve tidak boleh kosong..!");
            }
        }else{
            $('#warning_obat').show().fadeOut(5000);
            $('#obat_error').text("Silahkan cek kembali inputan berikut..!");
            if(jenisSatuan == '' || jenisSatuan == null){
                $('#war_ob_jenis_satuan').show();
            }
            if(namaObat == '' || namaObat == null){
                $('#war_obat').show();
            }
        }
    }

    function saveBatchObat(idObat, stringData){
        $('#modal-confirm-dialog').modal('hide');
        $('#save_obat').hide();
        $('#load_obat').show();
        dwr.engine.setAsync(true);
        TransaksiObatAction.saveListObatPembelian(stringData, idApprove, {callback:function (response) {
            if(response.status = "success"){
                $('#save_obat').show();
                $('#load_obat').hide();
                $('#modal-obat').modal('hide');
                $('#info_dialog').dialog('open');
                $('body').scrollTop(0);
                window.location.href = 'pembelianObat_transaksi.action?id='+response.message;
            }else{
                $('#warning_obat').show().fadeOut(5000);
                $('#obat_error').text("Terjadi kesalahan saat menyimpan database...!");
            }
        }});
    }

    function showListExpired(idPabrik){
        $('#load_barang').hide();
        $('#save_barang').show();
        $('#body_list_barang').html('');
        var table = [];
        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        today = mm + '/' + dd + '/' + yyyy;
        var lembarPerBox = "";
        var bijiPerLembar = "";
        var idObat = "";
        var namaObat = "";

        if (idPabrik != "") {
            $('#loading_data_barang').show();
            TransaksiObatAction.listObatPoliEntityByIdPabrik(idPabrik, {
                callback: function (response) {
                    if (response.length > 0) {
                        console.log(response);
                        $('#modal-list-obat').modal({show:true, backdrop:'static'});
                        $.each(response, function (i, item) {
                            var qtyBox = "";
                            var qtyLembar = "";
                            var qtyBiji = "";

                            var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(item.expiredDate));

                            var dateExpired = $.datepicker.formatDate('mm-dd-yy', new Date(item.expiredDate));

                            const date1 = new Date(today);
                            const date2 = new Date(dateExpired);
                            const diffTime = Math.abs(date2 - date1);
                            const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

                            if (item.qtyBox != null) {
                                qtyBox = item.qtyBox;
                            }
                            if (item.qtyLembar != null) {
                                qtyLembar = item.qtyLembar;
                            }
                            if (item.qtyBiji != null) {
                                qtyBiji = item.qtyBiji;
                            }

                            var warna = "";
                            var color = "";

                            if (diffDays < 10) {
                                warna = '#dd4b39';
                                color = 'white';

                            } else if (diffDays < 30) {
                                warna = '#eea236';
                                color = 'white';
                            } else {
                                warna = '#fff';
                                color = '#333';
                            }
                            var idBar = item.idBarang;
                            var str = idBar.substring(8, 15);
                            var idBarang = idBar.replace(str, '*******');

                            table += '<tr bgcolor=' + warna + ' style="color: ' + color + '">' +
                                    '<td>' + '<span>' + idBarang + '</span>' +
                                    '<input type="hidden" id=id_barang'+ i +' value= '+ item.idBarang +'>'+'</td>' +
                                    '<td>' + dateFormat + '</td>' +
                                    '<td align="center">' + qtyBox + '</td>' +
                                    '<td align="center">' + qtyLembar + '</td>' +
                                    '<td align="center">' + qtyBiji + '</td>' +
                                    '<td>' +
                                    '<div class="input-group">' +
                                    '<input class="form-control" onchange="cekIdBarang(\'' + i + '\',this.value)">' +
                                    '<div class="input-group-addon">' +
                                    '<span id=loading' + i + '></span> ' +
                                    '</div>' +
                                    '</div>' +
                                    '</td>' +
                                    '<td><input style="display: none" id=newQty' + i + ' type="number" class="form-control" ></td>' +
                                    '</tr>';

                            lembarPerBox = item.lembarPerBox;
                            bijiPerLembar = item.bijiPerLembar;
                            namaObat = item.namaObat;
                            idObat   = item.idObat;
                        });

                        $('#save_app').attr('onclick', 'confirmSaveApprove(\'' + idObat + '\',\'' + qtyReq + '\',\'' + idTransaksi + '\',\'' + lembarPerBox + '\',\'' + bijiPerLembar + '\',\'' + jenisSatuan + '\')');
                        $('#body_list_barang').html(table);
                        $('#loading_data_barang').hide();
                        $('#list_id').val(idObat);
                        $('#list_nama').val(namaObat);

                    } else {
                        $('#modal-warning').modal('show');
                        $('#loading_data').hide();
                    }
                }
            });
        }else{
            $('#loading_data_barang').show();
        }
    }

    function validasiInput(value, qtyBox, qtyLembar, qtyBiji, lembarPerBox, bijiPerLembar, dateFormat){

        var jenisSatuan = $('#ob_jenis_satuan').val();

        if(jenisSatuan != ''){
            var data = $('#tabel_list_obat').tableToJSON();
            var choseDate = new Date(dateFormat.split("-").reverse().join("-"));
            var check = false;
            var result = [];

            $.each(data, function (i, item) {
                var expired = data[i]["Expired Date"];
                var qty = $('#newQty'+i).val();
                var expDate = new Date(expired.split("-").reverse().join("-"));
                if(qty == ""){
                    if(choseDate.getTime() != expDate.getTime()){
                        result.push({'expired':expDate});
                    }
                }
            });

            $.each(result, function (i, item) {
                var exp = new Date(result[i]["expired"]);
                if(choseDate.getTime() > exp.getTime()){
                    check = true;
                }
            });

            if(check){
                $('#warning_exp').show();
                $('#msg_exp').text("Silahkan pilih Expired Date yang mau habis dulu...!");
            }

            var stok = 0;

            if ("box" == jenisSatuan) {
                stok = qtyBox;
            }
            if ("lembar" == jenisSatuan) {
                stok = parseInt(qtyLembar) + (parseInt(lembarPerBox * parseInt(qtyBox)));
            }
            if ("biji" == jenisSatuan) {
                stok = parseInt(qtyBiji) + ((parseInt(lembarPerBox * parseInt(qtyBox))) * parseInt(bijiPerLembar));
            }

            if (parseInt(value) <= parseInt(stok) && parseInt(value) <= parseInt(qtyReq)){

            }else{
                $('#warning_obat').show().fadeOut(5000);
                $('#obat_error').text("Qty Approve tidak boleh melebihi stok dan qty request..!");
            }

        }else{
            $('#warning_obat').modal('show').fadeOut(5000);
            $('#obat_error').text("Pilih jenis satuan terlebih dahulu...!");
        }
    }

    function confirmSavePembelian(){

        var data = $('#tabel_approve').tableToJSON();
        var result = [];
        var qtyApp = 0;
        var qtyBox = 0;
        var qtyLembar = 0;
        var qtyBiji = 0;

        $.each(data, function (i, item) {
            var expired = data[i]["Expired Date"];
            var expDate = expired.split("-").reverse().join("-");
            var qty = $('#newQty'+i).val();
            var idBarang = $('#id_barang'+i).val();
            var jenisSatuan = data[i]["Jenis Satuan"];

            result.push({'Expired Date': expDate, 'Qty Approve': qty, 'ID Barang':idBarang, 'Jenis Satuan':jenisSatuan});
        });

        $.each(data, function (i, item) {
            var id = data[i]["Expired Date"];
            var box = data[i]["Qty BX"];
            var lembar = data[i]["Qty LB"];
            var biji = data[i]["Qty BJ"];
            var qty = $('#newQty' + i).val();

            if (qty == "") {
                qty = 0;
            }
            if (box == "") {
                box = 0;
            }
            if (lembar == "") {
                lembar = 0;
            }
            if (biji == "") {
                biji = 0;
            }

            qtyBox = parseInt(qtyBox) + parseInt(box);
            qtyLembar = parseInt(qtyLembar) + parseInt(lembar);
            qtyBiji = parseInt(qtyBiji) + parseInt(biji);
            qtyApp = parseInt(qtyApp) + parseInt(qty);

        });

        var stok = 0;

        if ("box" == jenisSatuan) {
            stok = qtyBox;
        }
        if ("lembar" == jenisSatuan) {
            stok = parseInt(qtyLembar) + (parseInt(lembarPerBox * parseInt(qtyBox)));
        }
        if ("biji" == jenisSatuan) {
            stok = parseInt(qtyBiji) + ((parseInt(lembarPerBox * parseInt(qtyBox))) * parseInt(bijiPerLembar));
        }

        var stringData = JSON.stringify(result);

        if (qtyApp > 0) {

            if (parseInt(qtyApp) <= parseInt(stok) && parseInt(qtyApp) <= parseInt(qtyReq)) {
                $('#modal-confirm-dialog').modal('show');
                $('#save_con').attr('onclick','saveApprove(\'' + idObat + '\',\'' + idTransaksi + '\',\'' + stringData + '\')');
            } else {
                $('#warning_app').show().fadeOut(5000);
                $('#msg_app').text("Qty Approve tidak boleh melebihi stok dan qty request..!");
            }
        } else {
            $('#warning_app').show().fadeOut(5000);
            $('#msg_app').text("Qty Approve tidak boleh kosong..!");
        }
    }

    function showModal(select) {
        if (select == 5) {
            $('#ob_id_obat').val('').trigger('change');
            $('#ob_jenis_satuan').val('').trigger('change');
            $('#ob_qtyBox, #ob_qtyLembar, #ob_qtyBiji').val('');
            $('#ob_qty').val('');
            $('#load_obat, #warning_obat, #war_obat, #war_qty_obat, #war_ob_jenis_satuan').hide();
            $('#modal-obat').modal({show:true, backdrop:'static'});
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

        var obat = $('#ob_id_obat').val();
        var qty = $('#ob_qty').val();
        var jenisSatuan = $('#ob_jenis_satuan').val();
        var id = "";
        var nama = "";
        var qtyBox = "";
        var qtyLembar = "";
        var qtyBiji = "";
        var lembarPerBox = "";
        var bijiPerLembar = "";

        if (obat != '' && parseInt(qty) > 0) {

            if (obat.split('|')[0] != 'null' && obat.split('|')[0] != '') {
                id = obat.split('|')[0];
            }
            if (obat.split('|')[1] != 'null' && obat.split('|')[1] != '') {
                nama = obat.split('|')[1];
            }
            if (obat.split('|')[2] != 'null' && obat.split('|')[2] != '') {
                qtyBox = obat.split('|')[2];
            }
            if (obat.split('|')[3] != 'null' && obat.split('|')[3] != '') {
                qtyLembar = obat.split('|')[3];
            }
            if (obat.split('|')[4] != 'null' && obat.split('|')[4] != '') {
                qtyBiji = obat.split('|')[4];
            }
            if (obat.split('|')[5] != 'null' && obat.split('|')[5] != '') {
                lembarPerBox = obat.split('|')[5];
            }
            if (obat.split('|')[6] != 'null' && obat.split('|')[6] != '') {
                bijiPerLembar = obat.split('|')[6];
            }

            var stok = 0;

            if ("box" == jenisSatuan) {
                stok = qtyBox;
            }
            if ("lembar" == jenisSatuan) {
                stok = parseInt(qtyLembar) + (parseInt(lembarPerBox * parseInt(qtyBox)));
            }
            if ("biji" == jenisSatuan) {
                stok = parseInt(qtyBiji) + ((parseInt(lembarPerBox * parseInt(qtyBox))) * parseInt(bijiPerLembar));
            }

            if (parseInt(qty) <= parseInt(stok)) {

                $('#save_obat').hide();
                $('#load_obat').show();

                dwr.engine.setAsync(true);
                TransaksiObatAction.saveAddObat(id, qty, jenisSatuan, function (response) {
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
            if (obat == '') {
                $('#war_obat').show();
            }
            if (qty == '' || qty < 1) {
                $('#war_qty_obat').show();
            }
            if (jenisSatuan == '' || jenisSatuan == null) {
                $('#war_ob_jenis_satuan').show();
            }
        }
    }

    function resetobat() {
        // var url_string = window.location.href;
        // var url = new URL(url_string);
        // var id = url.searchParams.get("id");
        window.location.href = "pembelianObat_transaksi.action";
    }

    function setStokObat(select) {
        var idx = select.selectedIndex;
        if (idx > 0) {

            var id = "";
            var nama = "";
            var qtyBox = "";
            var qtyLembar = "";
            var qtyBiji = "";
            var lembarPerBox = "";
            var bijiPerLembar = "";

            var obat = select.options[idx].value;

            if (obat.split('|')[0] != 'null' && obat.split('|')[0] != '') {
                id = obat.split('|')[0];
            }
            if (obat.split('|')[1] != 'null' && obat.split('|')[1] != '') {
                nama = obat.split('|')[1];
            }
            if (obat.split('|')[2] != 'null' && obat.split('|')[2] != '') {
                qtyBox = obat.split('|')[2];
            }
            if (obat.split('|')[3] != 'null' && obat.split('|')[3] != '') {
                qtyLembar = obat.split('|')[3];
            }
            if (obat.split('|')[4] != 'null' && obat.split('|')[4] != '') {
                qtyBiji = obat.split('|')[4];
            }
            if (obat.split('|')[5] != 'null' && obat.split('|')[5] != '') {
                lembarPerBox = obat.split('|')[5];
            }
            if (obat.split('|')[6] != 'null' && obat.split('|')[6] != '') {
                bijiPerLembar = obat.split('|')[6];
            }

            $('#ob_qtyBox').val(qtyBox);
            $('#ob_qtyLembar').val(qtyLembar);
            $('#ob_qtyBiji').val(qtyBiji);

            listExpiredDate(id);

        }else{
            $('#body_list_obat').html('');
        }
    }

    function confirmObat(idPabrik, idObat, namaObat, qtyReq, jenisSatuan, idTransaksi) {

        $('#load_app').hide();
        $('#save_app').show();
        $('#body_approve').html('');
        $('#app_id').text(idObat);
        $('#app_nama').text(namaObat);
        $('#app_req').text(qtyReq);
        var table = [];
        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        today = mm + '/' + dd + '/' + yyyy;
        var lembarPerBox = "";
        var bijiPerLembar = "";
        if (idPabrik != "") {
            $('#loading_data').show();
            TransaksiObatAction.listObatPoliEntity(idObat, idPabrik, {
                callback: function (response) {
                    if (response.length > 0) {
                        $('#modal-approve').modal({show: true, backdrop: 'static'});
                        $.each(response, function (i, item) {
                            var qtyBox = "";
                            var qtyLembar = "";
                            var qtyBiji = "";

                            var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(item.expiredDate));

                            var dateExpired = $.datepicker.formatDate('mm-dd-yy', new Date(item.expiredDate));

                            const date1 = new Date(today);
                            const date2 = new Date(dateExpired);
                            const diffTime = Math.abs(date2 - date1);
                            const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

                            if (item.qtyBox != null) {
                                qtyBox = item.qtyBox;
                            }
                            if (item.qtyLembar != null) {
                                qtyLembar = item.qtyLembar;
                            }
                            if (item.qtyBiji != null) {
                                qtyBiji = item.qtyBiji;
                            }

                            var warna = "";
                            var color = "";
                            var disabled = "";

                            if(Math.abs(date1) > Math.abs(date2)){
                                warna = '#ccc';
                                color = '#fff';
                                disabled = 'disabled';

                            } else if (diffDays < 10) {
                                warna = '#dd4b39';
                                color = '#fff';

                            } else if (diffDays < 30) {
                                warna = '#eea236';
                                color = '#fff';
                            } else {
                                warna = '#fff';
                                color = '#333';
                            }

                            var idBar = item.idBarang;
                            var str = idBar.substring(8, 15);
                            var idBarang = idBar.replace(str, '*******');

                            table += '<tr bgcolor=' + warna + ' style="color: ' + color + '">' +
                                    '<td>' + '<span>' + idBarang + '</span>' + '<input type="hidden" id=id_barang'+ i +' value= '+ item.idBarang +'>'+'</td>' +
                                    '<td>' + dateFormat + '</td>' +
                                    '<td align="center">' + qtyBox + '</td>' +
                                    '<td align="center">' + qtyLembar + '</td>' +
                                    '<td align="center">' + qtyBiji + '</td>' +
                                    '<td>' +
                                    '<div class="input-group">' +
                                    '<input '+disabled+' class="form-control" onchange="cekIdBarang(\'' + i + '\',this.value)">' +
                                    '<div class="input-group-addon">' +
                                    '<span id=loading' + i + '></span> ' +
                                    '</div>' +
                                    '</div>' +
                                    '</td>' +
                                    '<td><input style="display: none" id=newQty' + i + ' type="number" class="form-control"></td>' +
                                    '<td>' + jenisSatuan + '</td>' +
                                    '</tr>';

                            lembarPerBox = item.lembarPerBox;
                            bijiPerLembar = item.bijiPerLembar;
                            $('#loading_data').hide();
                        });

                        $('#save_app').attr('onclick', 'confirmSaveApprove(\'' + idObat + '\',\'' + qtyReq + '\',\'' + idTransaksi + '\',\'' + lembarPerBox + '\',\'' + bijiPerLembar + '\',\'' + jenisSatuan + '\')');
                        $('#body_approve').html(table);
                    } else {
                        $('#status'+idObat).html('<img src="<s:url value="/pages/images/icon_failure.ico"/>" style="height: 20px; width: 20px;">');
                        $('#modal-warning').modal('show');
                        $('#loading_data').hide();
                    }
                }
            });
        }else{
            $('#loading_data').show();
            $('#status'+idObat).html('');
        }
    }

    function cekIdBarang(id, valueIdBarang) {
        var idBarang = $('#id_barang' + id).val();
        if (valueIdBarang != '') {
            $('#loading' + id).html('<i style="color: #00a65a" class="fa fa-circle-o-notch fa-spin"></i>');
            setTimeout(function () {
                if (idBarang == valueIdBarang) {
                    $('#loading' + id).html('<img src="<s:url value="/pages/images/icon_success.ico"/>" style="height: 20px; width: 20px;">');
                    $('#newQty' + id).show().focus();
                } else {
                    $('#loading' + id).html('<img src="<s:url value="/pages/images/icon_failure.ico"/>" style="height: 20px; width: 20px;">');
                    $('#newQty' + id).hide();
                    $('#newQty' + id).val('');
                }
            }, 700);
        } else {
            $('#loading' + id).html('');
            $('#newQty' + id).val('');
            $('#newQty' + id).hide();
        }
    }
    function confirmSaveApprove(idObat, qtyReq, idTransaksi, lembarPerBox, bijiPerLembar, jenisSatuan){
        var data = $('#tabel_approve').tableToJSON();
        var result = [];
        var qtyApp = 0;
        var qtyBox = 0;
        var qtyLembar = 0;
        var qtyBiji = 0;

        $.each(data, function (i, item) {
            var expired = data[i]["Expired Date"];
            var expDate = expired.split("-").reverse().join("-");
            var qty = $('#newQty'+i).val();
            var idBarang = $('#id_barang'+i).val();
            var jenisSatuan = data[i]["Jenis Satuan"];

            result.push({'Expired Date': expDate, 'Qty Approve': qty, 'ID Barang':idBarang, 'Jenis Satuan':jenisSatuan});
        });

        $.each(data, function (i, item) {
            var id = data[i]["Expired Date"];
            var box = data[i]["Qty BX"];
            var lembar = data[i]["Qty LB"];
            var biji = data[i]["Qty BJ"];
            var qty = $('#newQty' + i).val();

            if (qty == "") {
                qty = 0;
            }
            if (box == "") {
                box = 0;
            }
            if (lembar == "") {
                lembar = 0;
            }
            if (biji == "") {
                biji = 0;
            }

            qtyBox = parseInt(qtyBox) + parseInt(box);
            qtyLembar = parseInt(qtyLembar) + parseInt(lembar);
            qtyBiji = parseInt(qtyBiji) + parseInt(biji);
            qtyApp = parseInt(qtyApp) + parseInt(qty);

        });

        var stok = 0;

        if ("box" == jenisSatuan) {
            stok = qtyBox;
        }
        if ("lembar" == jenisSatuan) {
            stok = parseInt(qtyLembar) + (parseInt(lembarPerBox * parseInt(qtyBox)));
        }
        if ("biji" == jenisSatuan) {
            stok = parseInt(qtyBiji) + ((parseInt(lembarPerBox * parseInt(qtyBox))) * parseInt(bijiPerLembar));
        }

        var stringData = JSON.stringify(result);

        if (qtyApp > 0) {

            if (parseInt(qtyApp) <= parseInt(stok) && parseInt(qtyApp) <= parseInt(qtyReq)) {
                $('#modal-confirm-dialog').modal('show');
                $('#save_con').attr('onclick','saveApprove(\'' + idObat + '\',\'' + idTransaksi + '\',\'' + stringData + '\')');
            } else {
                $('#warning_app').show().fadeOut(5000);
                $('#msg_app').text("Qty Approve tidak boleh melebihi stok dan qty request..!");
            }
        } else {
            $('#warning_app').show().fadeOut(5000);
            $('#msg_app').text("Qty Approve tidak boleh kosong..!");
        }
    }

    function saveApprove(idObat, idTransaksi, stringData){
        $('#modal-confirm-dialog').modal('hide');
        dwr.engine.setAsync(true);
        $('#load_app').show();
        $('#save_app').hide();
        TransaksiObatAction.saveVerifikasiResep(idTransaksi, stringData, {callback: function (response) {
            if (response.status == "success") {
                $('#load_app').hide();
                $('#save_app').show();
                $('#modal-approve').modal('hide');
                $('#info_dialog').dialog('open');
                $('body').scrollTop(0);
                $('#status'+idObat).html('<img src="<s:url value="/pages/images/icon_success.ico"/>" style="height: 20px; width: 20px;">');
            } else {
                $('#load_app').hide();
                $('#save_app').show();
                $('#warning_app').show().fadeOut(5000);
                $('#msg_app').text("terjadi Kesalahan saat menyimpan ke database..!");
            }
        }});
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>