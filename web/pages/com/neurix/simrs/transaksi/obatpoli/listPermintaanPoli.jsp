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
            Daftar Permintaan Obat
            <small>e-HEALTH</small>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <%--<div class="box-header with-border">--%>
                    <%--<h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Obat Inap</h3>--%>
                    <%--</div>--%>
                    <%--<div class="box-body">--%>
                    <%--<div class="form-group">--%>
                    <%--<s:form id="obatPoliForm" method="post" namespace="/obatpoli"--%>
                    <%--action="search_obatpoli.action" theme="simple" cssClass="form-horizontal">--%>
                    <%--<div class="form-group">--%>
                    <%--<label class="control-label col-sm-4">ID Obat</label>--%>
                    <%--<div class="col-sm-4">--%>
                    <%--<s:textfield id="id_pasien" cssStyle="margin-top: 7px"--%>
                    <%--name="obatPoli.idObat" required="false"--%>
                    <%--readonly="false" cssClass="form-control"/>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--&lt;%&ndash;<div class="form-group">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<label class="control-label col-sm-4">Jenis Obat</label>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<div class="col-sm-4">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<s:action id="initJenis" namespace="/jenisobat"&ndash;%&gt;--%>
                    <%--&lt;%&ndash;name="getListJenisObat_jenisobat"/>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<s:select cssStyle="margin-top: 7px; width: 100%"&ndash;%&gt;--%>
                    <%--&lt;%&ndash;list="#initJenis.listOfJenisObat" id="obat_jenis_obat"&ndash;%&gt;--%>
                    <%--&lt;%&ndash;listKey="idJenisObat"&ndash;%&gt;--%>
                    <%--&lt;%&ndash;listValue="namaJenisObat"&ndash;%&gt;--%>
                    <%--&lt;%&ndash;name="obatPoli.idJenisObat"&ndash;%&gt;--%>
                    <%--&lt;%&ndash;headerKey="" headerValue="[Select one]"&ndash;%&gt;--%>
                    <%--&lt;%&ndash;cssClass="form-control select2"/>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--<div class="form-group">--%>
                    <%--<label class="control-label col-sm-4">Nama Obat</label>--%>
                    <%--<div class="col-sm-4">--%>
                    <%--<s:textfield id="nama_pasien" name="obatPoli.namaObat"--%>
                    <%--required="false" readonly="false"--%>
                    <%--cssClass="form-control" cssStyle="margin-top: 7px"/>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--<br>--%>
                    <%--<div class="form-group">--%>
                    <%--<label class="control-label col-sm-4"></label>--%>
                    <%--<div class="col-sm-8" style="margin-top: 7px">--%>
                    <%--<sj:submit type="button" cssClass="btn btn-success" formIds="obatPoliForm"--%>
                    <%--id="search" name="search"--%>
                    <%--onClickTopics="showDialogLoading"--%>
                    <%--onCompleteTopics="closeDialogLoading">--%>
                    <%--<i class="fa fa-search"></i>--%>
                    <%--Search--%>
                    <%--</sj:submit>--%>
                    <%--<a type="button" class="btn btn-danger" href="initForm_rawatinap.action">--%>
                    <%--<i class="fa fa-refresh"></i> Reset--%>
                    <%--</a>--%>
                    <%--<div class="btn-group">--%>
                    <%--<button type="button" class="btn btn-primary">Action</button>--%>
                    <%--<button type="button" class="btn btn-primary dropdown-toggle"--%>
                    <%--data-toggle="dropdown" style="height: 34px">--%>
                    <%--<span class="caret"></span>--%>
                    <%--<span class="sr-only">Toggle Dropdown</span>--%>
                    <%--</button>--%>
                    <%--<ul class="dropdown-menu" role="menu">--%>
                    <%--<li onclick="showModal(1)"><a href="#"><i class="fa fa-plus"></i> Tambah--%>
                    <%--Obat</a></li>--%>
                    <%--<li onclick="showModal(2)"><a href="#"><i class="fa fa-plus"></i> Tambah--%>
                    <%--& Request Obat</a></li>--%>
                    <%--<li class="divider"></li>--%>
                    <%--<li><a href="monitoringRequest_obatpoli.action"><i class="fa fa-tv"></i> Monitoring Request</a></li>--%>
                    <%--</ul>--%>
                    <%--</div>--%>
                    <%--<a type="button" class="btn btn-info" href="monitoringRequest_obatpoli.action">--%>
                    <%--<i class="fa fa-tv"></i> Monitoring Permintaan--%>
                    <%--</a>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--</s:form>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Permintaan Obat Poli</h3>
                    </div>
                    <div class="box-body">
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
                                <s:hidden id="close_pos"></s:hidden>
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
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Obat</td>
                                <td>Nama Obat</td>
                                <td>Qty Request</td>
                                <td>Qty Approve</td>
                                <td>Jenis Satuan</td>
                                <td>Scan ID Pabrik</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfObatDetail" status="listOfObatDetail">
                                <tr>
                                    <td><s:property value="idObat"/></td>
                                    <td><s:property value="namaObat"/></td>
                                    <td><s:property value="qty"/></td>
                                    <td><s:property value="qtyApprove"/></td>
                                    <td><s:property value="jenisSatuan"/></td>
                                    <td>
                                        <input class="form-control" id=pabrik'<s:property value="idObat"/>'
                                               onchange="confirmObat('<s:property value="idObat"/>', this.value, '<s:property value="namaObat"/>', '<s:property value="qty"/>','<s:property value="jenisSatuan"/>','<s:property value="idTransaksiObatDetail"/>')" style="width: 170px">
                                    </td>
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
                                    <button class="btn btn-warning"><i class="fa fa-arrow-left"></i> Back</button>
                                    <button class="btn btn-success"><i class="fa fa-arrow-right"></i> Save</button>
                                </div>
                            </div>
                        </div></div>
                </div>
            </div>
        </div>
    </section>
</div>

<div class="modal fade" id="modal-approve">
    <div class="modal-dialog modal-flat" style="width: 55%">
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
                        <td>Expired Date</td>
                        <td align="center">Qty Box</td>
                        <td align="center">Qty Lembar</td>
                        <td align="center">Qty Biji</td>
                        <td width="20%">Qty Approve</td>
                        <td>Jenis Satuan</td>
                        </thead>
                        <tbody id="body_approve">
                        </tbody>
                    </table>
                    <p id="loading_data" style="color: #00a65a; display: none"><img
                            src="<s:url value="/pages/images/spinner.gif"/>" style="height: 40px; width: 40px;"> Sedang
                        mengambil data...</p>
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

<!-- /.content-wrapper -->
<script type='text/javascript'>

    function confirmObat(idObat, idPabrik, nama, qtyReq, satuan, idTransaksi) {
        $('#body_approve').html('');
        $('#app_id').text(idObat);
        $('#app_nama').text(nama);
        $('#app_req').text(qtyReq);
        $('#modal-approve').modal('show');

        var table = [];
        var lembarPerBox = "";
        var bijiPerLembar = "";
        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        today = mm + '-' + dd + '-' + yyyy;

        PermintaanObatPoliAction.listObatEntity(idObat, idPabrik, function (response) {
            if (response != null) {

                $.each(response, function (i, item) {
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

                    var dateFormat = $.datepicker.formatDate('yy-mm-dd', new Date(item.expiredDate));

                    var warna = "";
                    var color = "";

                    if (diffDays < 10) {
                        warna = 'red';
                        color = 'white';

                    } else if (diffDays < 30) {
                        warna = 'orange';
                        color = 'white';
                    }

                    table += '<tr bgcolor=' + warna + ' style="color: ' + color + '">' +
                            '<td>' + dateFormat + '</td>' +
                            '<td align="center">' + qtyBox + '</td>' +
                            '<td align="center">' + qtyLembar + '</td>' +
                            '<td align="center">' + qtyBiji + '</td>' +
                            '<td>' + '<input id=newQty' + dateFormat + ' type="number" class="form-control" style="widht:50px">' + '</td>' +
                            '<td>' + satuan + '</td>' +
                            '</tr>';

                    lembarPerBox = item.lembarPerBox;
                    bijiPerLembar = item.bijiPerLembar;
                });

                $('#save_app').attr('onclick', 'saveApprove(\'' + idObat + '\',\'' + qtyReq + '\',\'' + idTransaksi + '\',\'' + lembarPerBox + '\',\'' + bijiPerLembar + '\',\'' + satuan + '\')');
                $('#body_approve').html(table);
            }
        });
    }

    function saveApprove(idObat, qtyReq, idTransaksi, lembarPerBox, bijiPerLembar, jenisSatuan) {

        var data = $('#tabel_approve').tableToJSON();
        var total = "";
        var qtyApp = 0;
        var qtyBox = 0;
        var qtyLembar = 0;
        var qtyBiji = 0;

        var result = [];

        $.each(data, function (i, item) {
            var id = data[i]["Expired Date"];
            var expired = $.datepicker.formatDate('yy-mm-dd', new Date(id));
            var qty = $('#newQty' + id).val();
            result.push({'Expired Date': id, 'qty': qty, 'Jenis Satuan': jenisSatuan});
        });

        $.each(data, function (i, item) {
            var id = data[i]["Expired Date"];
            var box = data[i]["Qty Box"];
            var lembar = data[i]["Qty Lembar"];
            var biji = data[i]["Qty Biji"];
            var qty = $('#newQty' + id).val();

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

                dwr.engine.setAsync(true);
                $('#load_app').show();
                $('#save_app').hide();
                PermintaanObatPoliAction.saveApproveRequest(idObat, stringData, function (response) {
                    if (response == "success") {
                        $('#load_app').hide();
                        $('#save_app').show();
                    } else {
                        $('#load_app').hide();
                        $('#save_app').show();
                        $('#warning_app').show().fadeOut(5000);
                        $('#msg_app').text("terjadi Kesalahan saat menyimpan ke database..!");
                    }
                });
                console.log('success');
            } else {
                $('#warning_app').show().fadeOut(5000);
                $('#msg_app').text("Qty Approve tidak boleh melebihi stok dan qty request..!");
            }
        } else {
            $('#warning_app').show().fadeOut(5000);
            $('#msg_app').text("Qty Approve tidak boleh kosong..!");
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>