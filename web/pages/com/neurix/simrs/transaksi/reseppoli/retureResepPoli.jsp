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
        #sketch-container {
            background: #EAEAEA;
            width: 100%;
            height: 350px;
        }

        .paint-canvas {
            border: 1px black solid;
            margin: 1rem;
        }

        .color-picker {
            margin: 1rem 1rem 0 1rem;
        }
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

        function uploadCanvas() {
            var idResep = $('#id_resep').val();
            var canvas1 = document.getElementById('ttd_pasien');
            var canvas2 = document.getElementById('ttd_apoteker');
            var dataURL1 = canvas1.toDataURL("image/png"),
                dataURL1 = dataURL1.replace(/^data:image\/(png|jpg);base64,/, "");
            var dataURL2 = canvas2.toDataURL("image/png"),
                dataURL2 = dataURL2.replace(/^data:image\/(png|jpg);base64,/, "");
            var cek1 = isBlank(canvas1);
            var cek2 = isBlank(canvas2);
            if(!cek1 && !cek2){
                $('#waiting_dialog').dialog('open');
                dwr.engine.setAsync(true);
                TransaksiObatAction.saveTtdPasien(dataURL1, idResep, dataURL2, {callback: function (response) {
                    if(response.status == "success"){
                        $('#waiting_dialog').dialog('close');
                        $('#info_dialog').dialog('open');
                        $('#ref').val(1);
                        $('#modal-ttd').modal('hide');
                        $('body').scrollTop(0);
                    }else{
                        $('#waiting_dialog').dialog('close');
                        $('#error_dialog').dialog('open');
                        $('#errorMessage').text(response.message);
                        $('#modal-ttd').modal('hide');
                    }
                }});
            }else{
                $('#waiting_dialog').dialog('close');
                $('#info_dialog').dialog('open');
                $('#ref').val(1);
                $('#modal-ttd').modal('hide');
                $('body').scrollTop(0);
                // $('#warning_ttd').show().fadeOut(5000);
                // $('#msg_ttd').text("Silahkan lakukan ttd pada canvas berikut...!");
            }
        }

        function clearConvas(id){
            var canvas = document.getElementById(id);
            const context = canvas.getContext('2d');
            context.clearRect(0, 0, canvas.width, canvas.height);
        }

        function isBlank(canvas){
            const blank = document.createElement("canvas");
            blank.width = canvas.width;
            blank.height = canvas.height;
            return canvas.toDataURL() === blank.toDataURL();
        }

        $(document).ready(function () {

            $('#resep_poli').addClass('active');

            cekListObat();

            const paintCanvas1 = document.querySelector("#ttd_pasien");
            const paintCanvas2 = document.querySelector("#ttd_apoteker");
            const context1 = paintCanvas1.getContext("2d");
            const context2 = paintCanvas2.getContext("2d");
            context1.lineCap = "round";
            context2.lineCap = "round";

            const colorPicker = document.querySelector(".js-color-picker");

            colorPicker.addEventListener("change", function (evt) {
                context.strokeStyle = evt.target.value;
            });

            const lineWidthRange = document.querySelector(".js-line-range");
            const lineWidthLabel = document.querySelector(".js-range-value");

            lineWidthRange.addEventListener("input", function (evt) {
                const width = evt.target.value;
                lineWidthLabel.innerHTML = width+" px";
                context1.lineWidth = width;
                context2.lineWidth = width;
            });

            let x = 0,
                y = 0;
            let isMouseDown = false;

            const stopDrawing = function () {
                isMouseDown = false;
            };

            const startDrawing = function (evt) {
                isMouseDown = true;
                [x, y] = [evt.offsetX, evt.offsetY];
            };

            const drawLine1 = function (evt) {
                if (isMouseDown) {
                    const newX = evt.offsetX;
                    const newY = evt.offsetY;
                    context1.beginPath();
                    context1.moveTo(x, y);
                    context1.lineTo(newX, newY);
                    context1.stroke();
                    x = newX;
                    y = newY;
                }
            };

            const drawLine2 = function (evt) {
                if (isMouseDown) {
                    const newX = evt.offsetX;
                    const newY = evt.offsetY;
                    context2.beginPath();
                    context2.moveTo(x, y);
                    context2.lineTo(newX, newY);
                    context2.stroke();
                    x = newX;
                    y = newY;
                }
            };

            paintCanvas1.addEventListener("mousedown", startDrawing);
            paintCanvas1.addEventListener("mousemove", drawLine1);
            paintCanvas1.addEventListener("mouseup", stopDrawing);
            paintCanvas1.addEventListener("mouseout", stopDrawing);

            paintCanvas2.addEventListener("mousedown", startDrawing);
            paintCanvas2.addEventListener("mousemove", drawLine2);
            paintCanvas2.addEventListener("mouseup", stopDrawing);
            paintCanvas2.addEventListener("mouseout", stopDrawing);

        });

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

        var n = 1;

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
            Resep Poli
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
                                    <s:hidden id="id_approve" name="transaksiObatDetail.idApprovalObat"/>
                                    <s:hidden id="id_resep" name="permintaanResep.idPermintaanResep"></s:hidden>
                                    <tr>
                                        <td width="45%"><b>No RM</b></td>
                                        <td>
                                            <table>
                                                <s:label name="permintaanResep.idPasien"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="45%"><b>No Resep</b></td>
                                        <td>
                                            <table>
                                                <s:label name="permintaanResep.idPermintaanResep"></s:label></table>
                                        </td>
                                    </tr>

                                    <s:if test='permintaanResep.flagEresep != "Y"'>
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
                                    </s:if>

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
                                <table class="table table-striped">
                                    <s:if test='permintaanResep.flagEresep != "Y"'>
                                        <tr>
                                            <td><b>Poli</b></td>
                                            <td>
                                                <table>
                                                    <s:label name="permintaanResep.namaPelayanan"></s:label></table>
                                            </td>
                                        </tr>
                                    </s:if>
                                    <tr>
                                        <td><b>Alamat</b></td>
                                        <td>
                                            <table><s:label name="permintaanResep.alamat"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Desa</b></td>
                                        <td>
                                            <table><s:label name="permintaanResep.desa"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kecamatan</b></td>
                                        <td>
                                            <table><s:label name="permintaanResep.kecamatan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Kabupaten</b></td>
                                        <td>
                                            <table><s:label name="permintaanResep.kota"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Provinsi</b></td>
                                        <td>
                                            <table><s:label name="permintaanResep.provinsi"></s:label></table>
                                        </td>
                                    </tr>
                                    <s:if test='permintaanResep.flagEresep == "Y"'>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <table>
                                                    <label class="label label-success">E-Obat Telemedic</label>
                                                </table>
                                            </td>
                                        </tr>
                                    </s:if>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Obat Resep</h3>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_list_obat">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            Silahkan lakukan konfirmasi qty untuk masing masing obat...!
                        </div>
                        <table class="table table-bordered table-striped" id="tabel_list_obat">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Nama Obat</td>
                                <td align="center">Qty Request</td>
                                <%--<td align="center">Qty Approve</td>--%>
                                <td align="center">Harga Satuan (Rp.)</td>
                                <td align="center">Harga Total (Rp.)</td>
                                <td width="21%">Scan ID Obat</td>
                                <td width="21%">Qty Reture</td>
                                <td>Jenis Satuan</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResultRetureResep" id="listOfResultResep" var="row">
                                <tr>
                                    <td><s:property value="namaObat"/></td>
                                    <td align="center"><s:property value="qty"/></td>
                                        <%--<td align="center"><span id='qtyAppove<s:property value="idObat"/>'><s:property value="qtyApprove"/></span></td>--%>
                                    <td align="right"><script>var val = <s:property value="harga"/>;
                                    if (val != null && val != '') {
                                        document.write(formatRupiah(val))
                                    }</script></td>
                                    <td align="right"> <script>var val = <s:property value="totalHarga"/>;
                                    if (val != null && val != '') {
                                        document.write(formatRupiah(val))
                                    }</script></td>
                                    <td>
                                        <div class="input-group">
                                            <s:if test='#row.flagVerifikasi == "Y"'>
                                                <input type="text" disabled class="form-control" onchange="confirmObat(this.value,'<s:property value="idObat"/>','<s:property value="namaObat"/>','<s:property value="qty"/>','<s:property value="jenisSatuan"/>','<s:property value="idTransaksiObatDetail"/>')">
                                                <div class="input-group-addon">
                                                    <img src="<s:url value="/pages/images/icon_success.ico"/>" style="height: 20px; width: 20px;">
                                                </div>
                                            </s:if>
                                            <s:else>
                                                <input type="text" id='input<s:property value="idObat"/>' class="form-control" onchange="confirmObat(this.value,'<s:property value="idObat"/>','<s:property value="namaObat"/>','<s:property value="qty"/>','<s:property value="jenisSatuan"/>','<s:property value="idTransaksiObatDetail"/>')">
                                                <div class="input-group-addon">
                                                    <span id='status<s:property value="idObat"/>'></span>
                                                </div>
                                            </s:else>
                                        </div>
                                    </td>
                                    <td>
                                        <input type="number" class="form-control" id='qty-reture-<s:property value="idBarang"/>' />
                                        <input type="hidden" id='id-obat-<s:property value="idBarang"/>' value='<s:property value="idObat" />' />
                                        <input type="hidden" id='id-barang-<s:property value="idBarang"/>' value='<s:property value="idBarang" />' />
                                    </td>
                                    <td>
                                        <s:property value="jenisSatuan"/>
                                        <input type="hidden" id='jenis-satuan-<s:property value="idBarang"/>' value='<s:property value="jenisSatuan" />' />
                                    </td>
                                    <script>
                                        var idbarang = '<s:property value="idBarang" />';
                                        $("#id-barang-"+idbarang).attr("class", 'id-barang-'+n);
                                        n++;
                                    </script>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>

                    <div class="box-header with-border"></div>
                    <div class="box-body">
                        <a href="initForm_reseppoli.action" class="btn btn-warning"><i class="fa fa-arrow-left"></i> Back</a>
                        <a onclick="confirm()" class="btn btn-success"><i class="fa fa-arrow-right"></i> Save</a>
                        <%--<a onclick="printStrukResep()" class="btn btn-primary"><i class="fa fa-print"></i> Print</a>--%>
                        <div class="form-group">
                            <s:form id="pembayaranForm" method="post" namespace="/transaksi"
                                    action="pembayaran_transaksi.action"
                                    theme="simple" cssClass="form-horizontal">

                                <br>
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
                                                <button class="btn btn-success" onclick="saveApproveResep()"><i class="fa fa-arrow-right"></i> Yes</button>
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
                                                                                         toContent();
                                                                                     }
                                                                            }"
                                        >
                                            <s:hidden id="ref"></s:hidden>
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
                <div class="alert alert-warning alert-dismissible" style="display: none" id="warning_exp">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_exp"></p>
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
                    <div class="col-md-4"><i class="fa fa-square" style="color: #eea236"></i> Expired Date Kurang dari 30 hari</div>
                    <div class="col-md-4"><i class="fa fa-square" style="color: #dd4b39"></i> Expired Date Kurang dari 10 hari</div>
                    <div class="col-md-4"><i class="fa fa-square" style="color: #ccc"></i> Expired Date Telah Habis</div>
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
                                    <input type="color" style="margin-left: -6px; margin-top: -8px" class="js-color-picker  color-picker pull-left">
                                </div>
                                <div class="col-md-9">
                                    <input type="range" style="margin-top: -8px" class="js-line-range" min="1" max="72" value="1">
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
                            <b style="margin-left: 8px">Tanda Tangan Pasien</b>
                            <canvas class="js-paint  paint-canvas" id="ttd_pasien" width="380" height="300"></canvas>
                            <button style="margin-left: 8px" type="button" class="btn btn-danger" onclick="clearConvas('ttd_pasien')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                        <div class="col-md-6">
                            <b style="margin-left: 8px">Tanda Tangan Apoteker</b>
                            <canvas class="js-paint  paint-canvas" id="ttd_apoteker" width="380" height="300"></canvas>
                            <button style="margin-left: 8px" type="button" class="btn btn-danger" onclick="clearConvas('ttd_apoteker')"><i class="fa fa-trash"></i> Clear
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button class="btn btn-success pull-right" onclick="uploadCanvas()"><i class="fa fa-check"></i> Save</button>
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

<script type='text/javascript'>

    var id_approve = $('#id_approve').val();
    var idResep = $('#id_resep').val();
    var idCheckup = $('#no_checkup').val();
    var idDetailCheckup = $('#no_detail_checkup').val();

    function drawTtd() {
        $('#modal-ttd').modal({show:true, backdrop:'static'});
    }
    function printStrukResep(){
        window.open('printStrukResepPasien_reseppoli.action?id='+idDetailCheckup+'&idResep='+idResep+'&idApprove='+id_approve, "_blank");
    }

    function show(){
        $('#modal-ttd').modal({show:true, backdrop:'static'});
    }

    function cekListObat() {

        setInterval(function () {
            TransaksiObatAction.getListResepPasien(idResep, function (response) {
                if(response.length > 0) {
                    $.each(response, function (i, item) {
                        if(item.flagVerifikasi == "Y"){
                            $('#input'+item.idObat).attr('disabled','true');
                            $('#status'+item.idObat).html('<img src="<s:url value="/pages/images/icon_success.ico"/>" style="height: 20px; width: 20px;">');
                        }else {
                            $('#input'+item.idObat).removeAttr('disabled');
                            $('#status'+item.idObat).html('<span id="status'+item.idObat+'"></span>');
                        }
                    })
                }
            });
        },1000);
    }
    function toContent(){
        var ref = $('#ref').val();
        if(ref == 1){
            window.location.href = 'initForm_reseppoli.action';
        }else if(ref == 2){
            $('#modal-ttd').modal({show:true, backdrop:'static'});
        }
    }

    function formaterDateDD(dateTime) {

        var today = "";
        if (dateTime != '' && dateTime != null) {

            today = new Date(dateTime);
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
            var yyyy = today.getFullYear();
            var hh = ((today.getHours() < 10 ? '0' : '') + today.getHours());
            var min = ((today.getMinutes() < 10 ? '0' : '') + today.getMinutes());
            var sec = today.getSeconds();
            today = dd + '-' + mm + '-' + yyyy;
        }
        return today;
    }

    function formaterDateMM(dateTime) {

        var today = "";
        if (dateTime != '' && dateTime != null) {

            today = new Date(dateTime);
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
            var yyyy = today.getFullYear();
            var hh = ((today.getHours() < 10 ? '0' : '') + today.getHours());
            var min = ((today.getMinutes() < 10 ? '0' : '') + today.getMinutes());
            var sec = today.getSeconds();
            today = mm + '-' + dd + '-' + yyyy;
        }
        return today;
    }

    function confirmObat(idObatVal, idObat, namaObat, qtyReq, jenisSatuan, idTransaksi) {

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
        today = mm + '-' + dd + '-' + yyyy;
        var lembarPerBox = "";
        var bijiPerLembar = "";
        if (idObatVal != "") {
            TransaksiObatAction.listObatPoliEntity(idObatVal, {
                callback: function (response) {
                    console.log(" response length = " + response.length + ", id obat = " + idObat + ", id obat val = " + idObatVal);
                    if (response.length > 0 && idObat == idObatVal) {

                        $('#loading_data').show();
                        $('#modal-approve').modal({show: true, backdrop: 'static'});

                        $.each(response, function (i, item) {
                            var qtyBox = "";
                            var qtyLembar = "";
                            var qtyBiji = "";

                            var dateFormat = formaterDateDD(new Date(item.expiredDate));

                            var dateExpired = formaterDateMM(new Date(item.expiredDate));

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
                                '<td>' + idBarang +
                                '<input type="hidden" id=id_barang' + i + ' value='+item.idBarang+'>'+
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
                                '<td><input style="display: none" id=newQty' + i + ' type="number" class="form-control" onchange="validasiInput(this.value,\''+qtyReq+'\', \''+qtyBox+'\',\''+qtyLembar+'\',\''+qtyBiji+'\',\''+item.lembarPerBox+'\',\''+item.bijiPerLembar+'\',\''+jenisSatuan+'\',\''+dateFormat+'\')"></td>' +
                                '<td>' + jenisSatuan + '</td>' +
                                '</tr>';

                            lembarPerBox = item.lembarPerBox;
                            bijiPerLembar = item.bijiPerLembar;
                            $('#loading_data').hide();
                        });
                        $('#save_app').attr('onclick', 'confirmSaveApprove(\'' + idObat + '\',\'' + qtyReq + '\',\'' + idTransaksi + '\',\'' + lembarPerBox + '\',\'' + bijiPerLembar + '\',\'' + jenisSatuan + '\')');
                        $('#body_approve').html(table);
                    } else {
                        $('#status' + idObat).html('<img src="<s:url value="/pages/images/icon_failure.ico"/>" style="height: 20px; width: 20px;">');
                        $('#error_dialog').dialog('open');
                        $('body').scrollTop(0);
                        $('#errorMessage').html("ID obat tidak cocok dengan list resep...!")
                        $('#loading_data').hide();
                    }
                }
            });
        }else{
            $('#loading_data').show();
            $('#status'+idObat).html('');
        }
    }

    function validasiInput(value, qtyReq, qtyBox, qtyLembar, qtyBiji, lembarPerBox, bijiPerLembar, jenisSatuan, dateFormat){

        var data = $('#tabel_approve').tableToJSON();
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
            $('#warning_app').show().fadeOut(5000);
            $('#msg_app').text("Qty Approve tidak boleh melebihi stok dan qty request..!");
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
                $('#save_con').attr('onclick','saveApprove(\'' + idObat + '\',\'' + idTransaksi + '\',\'' + stringData + '\',\'' + qtyApp + '\')');
            } else {
                $('#warning_app').show().fadeOut(5000);
                $('#msg_app').text("Qty Approve tidak boleh melebihi stok dan qty request..!");
            }
        } else {
            $('#warning_app').show().fadeOut(5000);
            $('#msg_app').text("Qty Approve tidak boleh kosong..!");
        }
    }

    function saveApprove(idObat, idTransaksi, stringData, qtyApp){
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
                $('#qtyAppove'+idObat).text(qtyApp);
                $('#status'+idObat).html('<img src="<s:url value="/pages/images/icon_success.ico"/>" style="height: 20px; width: 20px;">');
                $('body').scrollTop(0);
            } else {
                $('#load_app').hide();
                $('#save_app').show();
                $('#warning_app').show().fadeOut(5000);
                $('#msg_app').text("terjadi Kesalahan saat menyimpan ke database..!");
            }
        }});
    }

    function confirm(){

        // var data = $('#tabel_list_obat').tableToJSON();
        // var cek = false;
        // $.each(data, function (i, item) {
        //     var qtyApp = data[i]["Scan ID Obat"];
        //     if (qtyApp == ""){
        //         cek = true;
        //     }
        //     cek = parseInt(cek) + parseInt(qtyApp);
        // });

        $('#confirm_dialog').dialog('open');
        $('body').scrollTop(0);
        // if(cek > 0){
        //     $('#confirm_dialog').dialog('open');
        // }else{
        //     $('#warning_list_obat').show().fadeOut(5000);
        // }
        // console.log(cek);

    }

    function saveApproveResep(){

        $('#confirm_dialog').dialog('close');
        $('#waiting_dialog').dialog('open');

        var arrReture = [];
        for (i = 1 ; i < n; i++){
            var idBarang        = $(".id-barang-"+i).val();
            var qty             = $("#qty-reture-"+idBarang).val();
            var idObat          = $("#id-obat-"+idBarang).val();
            var jenissatuan     = $("#jenis-satuan-"+idBarang).val();
            arrReture.push({"idobat" : idObat, "idbarang" : idBarang, "qtyreture" : qty, "jenissatuan":jenissatuan});
        }

        console.log(arrReture);
        console.log(n);
        $('#waiting_dialog').dialog('close');

        var strReture = JSON.stringify(arrReture);
        dwr.engine.setAsync(true);
        TransaksiObatAction.retureResep( idResep, id_approve, strReture, {
            callback: function (response) {
                if (response.status == "success") {
                    $('#ref').val(2);
                    $('#info_dialog').dialog('open');
                    $('#waiting_dialog').dialog('close');
                    $('body').scrollTop(0);
                } else {
                    $('#ref').val(1);
                    $('#info_dialog').dialog('close');
                    $('#waiting_dialog').dialog('close');
                    $('#error_dialog').dialog('open');
                    $('#errorMessage').text(response.msg);
                }
            }
        });
    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>