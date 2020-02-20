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

        $(document).ready(function () {
            $('#permintaan_obat').addClass('active');
        });

    </script>

    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanObatPoliAction.js"/>'></script>

</head>

<body class="hold-transition skin-blue fixed sidebar-mini">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Verifikasi Permintaan Obat
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
                        <h3 class="box-title"><i class="fa fa-user"></i> Data Request</h3>
                    </div>
                    <div class="box-body">
                                <table class="table table-striped">
                                    <tr>
                                        <td width="20%"><b>ID Permintaan</b></td>
                                        <td>
                                            <table>
                                                <s:label name="permintaanObatPoli.idPermintaanObatPoli"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Nama Pelayanan</b></td>
                                        <td>
                                            <table><s:label name="permintaanObatPoli.namaPelayanan"></s:label></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Tanggal Permintaan</b></td>
                                        <td>
                                            <table><s:label name="permintaanObatPoli.stCreatedDate"></s:label></table>
                                        </td>
                                    </tr>
                                </table>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Verifikasi Permintaan Obat Poli</h3>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_save">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_save"></p>
                        </div>
                        <div class="form-group" style="display: none">
                            <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                       resizable="false"
                                       closeOnEscape="false"
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


                            <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true"
                                       resizable="false"
                                       height="250" width="600" autoOpen="false" title="Error Dialog"
                                       buttons="{
                                                                                'OK':function() { $('#error_dialog').dialog('close');
                                                                      }

                                                                            }"
                            >
                                <div class="alert alert-danger alert-dismissible">
                                    <label class="control-label" align="left">
                                        <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                             name="icon_error"> System Found : <p id="errorMessage"></p>
                                    </label>
                                </div>
                            </sj:dialog>
                            <sj:dialog id="confirm_dialog" modal="true" resizable="false" closeOnEscape="false"
                                       height="200" width="400" autoOpen="false" title="Confirmation Dialog">
                                <center><img border="0" style="height: 40px; width: 40px"
                                             src="<s:url value="/pages/images/icon_warning.ico"/>"
                                             name="icon_success">
                                    Do you want to save this record?
                                </center>
                                <br>
                                <div class="modal-footer">
                                    <a style="color: white" type="button" class="btn btn-warning"
                                       onclick="$('#confirm_dialog').dialog('close')"><i
                                            class="fa fa-times"></i> No
                                    </a>
                                    <a style="color: white" type="button" class="btn btn-success"
                                       onclick="saveRequestApprove()"><i class="fa fa-arrow-right"></i>
                                        Yes</a>
                                </div>
                            </sj:dialog>
                        </div>
                        <table class="table table-bordered table-striped" id="tabel_request">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Obat</td>
                                <td>Nama Obat</td>
                                <td align="center">Qty Request</td>
                                <td align="center">Qty Approve</td>
                                <td align="center">Scan ID Pabrik</td>
                                <td>Jenis Satuan</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfObatDetail" status="listOfObatDetail">
                                <tr>
                                    <td>
                                        <s:property value="idObat"/>
                                        <input type="hidden" id='idTrans<s:property value="idObat"/>'
                                               value='<s:property value="idTransaksiObatDetail"/>'>
                                    </td>
                                    <td><s:property value="namaObat"/></td>
                                    <td align="center"><s:property value="qty"/></td>
                                    <td align="center"><span id='qtyApp<s:property value="idObat"/>'><s:property
                                            value="qtyApprove"/></span></td>
                                    <td width="21%">
                                            <%--<div class="col-md-8">--%>
                                            <%--<input class="form-control" id='pabrik<s:property value="idObat"/>'--%>
                                            <%--onchange="confirmObat('<s:property value="idObat"/>', this.value, '<s:property value="namaObat"/>', '<s:property value="qty"/>','<s:property value="jenisSatuan"/>','<s:property value="idTransaksiObatDetail"/>')" style="width: 170px">--%>
                                            <%--</div>--%>
                                            <%--<div class="col-md-4"><span id='status<s:property value="idObat"/>'></span></div>--%>
                                        <div class="input-group">
                                            <input class="form-control" id='pabrik<s:property value="idObat"/>'
                                                   onchange="confirmObat('<s:property value="idObat"/>', this.value,
                                                            '<s:property value="namaObat"/>', '<s:property value="qty"/>',
                                                            '<s:property value="jenisSatuan"/>','<s:property value="idTransaksiObatDetail"/>')">
                                            <div class="input-group-addon">
                                                <span id='status<s:property value="idObat"/>'></span>
                                            </div>
                                        </div>
                                    </td>
                                    <td><s:property value="jenisSatuan"/></td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <a href="initForm_permintaangudang.action" class="btn btn-warning"><i class="fa fa-arrow-left"></i> Back</a>
                                    <button class="btn btn-success" onclick="confirm()"><i
                                            class="fa fa-arrow-right"></i> Save
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<div class="modal fade" id="modal-approve">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Konfirmasi Qty Approve
                    Obat</h4>
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
                        <td width="25%" align="center">Scan ID Barang</td>
                        <td width="12%" align="center">Qty AP</td>
                        <td>Jenis Satuan</td>
                        </thead>
                        <tbody id="body_approve">
                        </tbody>
                    </table>
                    <p id="loading_data" style="color: #00a65a; display: none"><img
                            src="<s:url value="/pages/images/spinner.gif"/>" style="height: 40px; width: 40px;"> Sedang
                        mengambil data...</p>
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

<!-- /.content-wrapper -->
<script type='text/javascript'>

    function confirmObat(idObat, idPabrik, nama, qtyReq, satuan, idTransaksi) {

        $('#load_app').hide();
        $('#save_app').show();
        $('#body_approve').html('');
        $('#app_id').text(idObat);
        $('#app_nama').text(nama);
        $('#app_req').text(qtyReq);
        var table = [];
        var lembarPerBox = "";
        var bijiPerLembar = "";
        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        today = mm + '-' + dd + '-' + yyyy;

        if (idPabrik != "") {
            PermintaanObatPoliAction.listObatEntity(idObat, idPabrik, function (response) {
                if (response.length > 0) {
                    $.each(response, function (i, item) {
                        $('#modal-approve').modal({show: true, backdrop: 'static'});
                        var dateExp = $.datepicker.formatDate('mm-dd-yy', new Date(item.expiredDate));

                        const date1 = new Date(today);
                        const date2 = new Date(dateExp);
                        const diffTime = Math.abs(date2 - date1);
                        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

                        var qtyBox = "";
                        var qtyLembar = "";
                        var qtyBiji = "";

                        if (item.qtyBox != null) {
                            qtyBox = item.qtyBox;
                        }
                        if (item.qtyLembar != null) {
                            qtyLembar = item.qtyLembar;
                        }
                        if (item.qtyBiji != null) {
                            qtyBiji = item.qtyBiji;
                        }

                        var dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(item.expiredDate));

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
                                '<td>' + '<input onchange="validasiInput(this.value,\''+qtyReq+'\', \''+qtyBox+'\',\''+qtyLembar+'\',\''+qtyBiji+'\',\''+item.lembarPerBox+'\',\''+item.bijiPerLembar+'\',\''+satuan+'\',\''+dateFormat+'\')" type="number" style="display: none" class="form-control" id=newQty' + i + '>' + '</td>' +
                                '<td>' + satuan + '</td>' +
                                '</tr>';

                        lembarPerBox = item.lembarPerBox;
                        bijiPerLembar = item.bijiPerLembar;
                    });

                    $('#save_app').attr('onclick', 'confirmSaveApprove(\'' + idObat + '\',\'' + qtyReq + '\',\'' + idTransaksi + '\',\'' + lembarPerBox + '\',\'' + bijiPerLembar + '\',\'' + satuan + '\')');
                    $('#body_approve').html(table);
                }else {
                    $('#modal-warning').modal('show');
                    $('#status' + idObat).html('<img src="<s:url value="/pages/images/icon_failure.ico"/>" style="height: 20px; width: 20px;">');
                }
            });
        }else{
            $('#status' + idObat).html('');
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

    function confirmSaveApprove(idObat, qtyReq, idTransaksi, lembarPerBox, bijiPerLembar, jenisSatuan) {

        var data = $('#tabel_approve').tableToJSON();
        var total = "";
        var qtyApp = 0;
        var qtyBox = 0;
        var qtyLembar = 0;
        var qtyBiji = 0;

        var result = [];

        $.each(data, function (i, item) {
            var expDate = data[i]["Expired Date"];
            var expired = expDate.split("-").reverse().join("-");
            var qty = $('#newQty' + i).val();
            var idBarang = $('#id_barang'+i).val();
            result.push({
                'Expired Date': expired,
                'Qty Approve': qty,
                'Jenis Satuan': jenisSatuan,
                'ID Barang': idBarang
            });
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
                $('#save_con').attr('onclick','saveApprove(\'' + idObat + '\',\'' + idTransaksi + '\',\'' + stringData + '\',\''+qtyApp+'\')');
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
        PermintaanObatPoliAction.saveVerifikasiObatPoli(idObat, idTransaksi, stringData, {
            callback: function (response) {
                if (response == "success") {
                    $('#load_app').hide();
                    $('#save_app').show();
                    $('#modal-approve').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#qtyApp' + idObat).text(qtyApp);
                    $('#status' + idObat).html('<img src="<s:url value="/pages/images/icon_success.ico"/>" style="height: 20px; width: 20px;">');
                } else {
                    $('#load_app').hide();
                    $('#save_app').show();
                    $('#warning_app').show().fadeOut(5000);
                    $('#msg_app').text("terjadi Kesalahan saat menyimpan ke database..!");
                }
            }
        });
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
            }, 500);
        } else {
            $('#loading' + id).html('');
            $('#newQty' + id).val('');
            $('#newQty' + id).hide();
        }
    }

    function confirm() {
        var data = $('#tabel_request').tableToJSON();
        var qtyApp = 0;
        $.each(data, function (i, item) {
            var id = data[i]["ID Obat"];
            var idTransaksi = $('#idTrans' + id).val();
            var qty = data[i]["Qty Approve"];
            if (qty == "") {
                qty = 0;
            }
            qtyApp = parseInt(qtyApp) + parseInt(qty);
        });

        if (qtyApp > 0) {
            $('#confirm_dialog').dialog('open');
        } else {
            $('#warning_save').show().fadeOut(5000);
            $('#msg_save').text("Silahkan konfirmasi terlebih dahulu untuk qty Approvenya...!");
        }

    }

    function saveRequestApprove() {
        $('#confirm_dialog').dialog('close');
        var data = $('#tabel_request').tableToJSON();
        var result = [];
        var url_string = window.location.href;
        var url = new URL(url_string);
        var idApp = url.searchParams.get("idApproval");

        $.each(data, function (i, item) {
            var id = data[i]["ID Obat"];
            var idTransaksi = $('#idTrans' + id).val();
            var qty = data[i]["Qty Approve"];
            result.push({'ID Obat': id, 'ID Transkasi': idTransaksi, 'Qty Approve': qty});
        });

        var stringData = JSON.stringify(result);
        $('#waiting_dialog').dialog('open');
        dwr.engine.setAsync(true);
        PermintaanObatPoliAction.saveApproveRequest(idApp, stringData, {
            callback: function (response) {
                if (response == "success") {
                    $('#ref').val(1);
                    $('#info_dialog').dialog('open');
                    $('#confirm_dialog').dialog('close');
                    $('#waiting_dialog').dialog('close');
                } else {
                }
            }
        });
    }

    function toContent() {
        var ref = $('#ref').val();
        if (ref == 1) {
            window.location.href = 'initForm_permintaangudang.action';
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>