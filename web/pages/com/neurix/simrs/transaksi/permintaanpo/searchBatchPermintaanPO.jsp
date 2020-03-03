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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanVendorAction.js"/>'></script>
    <script>
        function formatRupiah(angka) {
            var reverse = angka.toString().split('').reverse().join(''),
                    ribuan = reverse.match(/\d{1,3}/g);
            ribuan = ribuan.join('.').split('').reverse().join('');
            return ribuan;
        }
    </script>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con">
    <%--<span style="align-content: center">--%>
    <%--<center>--%>
    <%--<img border="0" style="width: 130px; height: 120px; margin-top: 20px" src="<s:url value="/pages/images/sayap-logo-nmu.png"/>" name="image_indicator_write">--%>
    <%--<br>--%>
    <%--<img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px" src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>" name="image_indicator_write">--%>
    <%--</center>--%>
    <%--</span>--%>
</div>
<%--<div class="pulse"></div>--%>

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Batch Purchase Order (PO)
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
                        <h3 class="box-title"><i class="fa fa-ambulance"></i> Data Vendor</h3>
                    </div>
                    <div class="box-body">
                        <table class="table table-striped">
                            <tr>
                                <td width="17%"><b>ID Vendor</b></td>
                                <td>
                                    <table>
                                        <s:label name="vendor.idVendor"></s:label>
                                        <s:hidden name="permintaanVendor.idApprovalObat" id="id_approval"></s:hidden>
                                        <s:hidden name="permintaanVendor.idPermintaanVendor" id="id_permintaan_vendor"></s:hidden>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td><b>Nama Vendor</b></td>
                                <td>
                                    <table><s:label name="vendor.namaVendor"></s:label></table>
                                </td>
                            </tr>
                            <tr>
                                <td><b>No Telp.</b></td>
                                <td>
                                    <table><s:label name="vendor.noTelp"></s:label></table>
                                </td>
                            </tr>
                            <tr>
                                <td><b>Alamat</b></td>
                                <td>
                                    <table><s:label name="vendor.alamat"></s:label></table>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-body">
                        <div class="form-group" style="display: none">
                            <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                       resizable="false"
                                       closeOnEscape="false"
                                       height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                       buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         window.location.reload(true);
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
                                       title="Searching ...">
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

                            <sj:dialog id="confirm_dialog" modal="true" resizable="false" closeOnEscape="false"
                                       height="200" width="400" autoOpen="false" title="Confirmation Dialog">
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
                        </div>
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_po">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_po"></p>
                        </div>
                        <%--<div class="row">--%>
                            <%--<div class="col-md-offset-2 col-md-8" >--%>
                                <%--<div class="box-header with-border">--%>
                                    <%--<div class="row">--%>
                                        <%--<div class="col-md-6">--%>
                                            <%--<h3 class="box-title"><i class="fa fa-file-text-o"></i> Dokumen Vendor</h3>--%>
                                        <%--</div>--%>
                                        <%--<div class="col-md-6">--%>
                                            <%--<a class="btn btn-success pull-right" onclick="upload()"><i class="fa fa-upload"></i> Upload</a>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<table class="table table-bordered table-striped" id="tabel_doc">--%>
                                    <%--<thead>--%>
                                    <%--<tr bgcolor="#90ee90">--%>
                                        <%--<td>Nota Vendor</td>--%>
                                        <%--<td>Action</td>--%>
                                    <%--</tr>--%>
                                    <%--</thead>--%>
                                    <%--<tbody id="body-doc">--%>

                                    <%--</tbody>--%>
                                <%--</table>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="row">
                            <div class="col-md-offset-2 col-md-8" >
                                <div class="box-header with-border">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h3 class="box-title"><i class="fa fa-file-text-o"></i> Daftar Batch</h3>
                                        </div>
                                        <div class="col-md-6">
                                            <%--<s:url var="selesai" namespace="/permintaanpo" action="initForm_permintaanpo"--%>
                                                   <%--escapeAmp="false">--%>
                                            <%--</s:url>--%>
                                            <%--<s:a href="%{selesai}">--%>
                                                <%--<button class="btn btn-warning pull-right"><i class="fa fa-arrow-left"></i> Back</button>--%>
                                            <%--</s:a>--%>
                                            <a class="btn btn-success pull-right" onclick="addBatch()"><i class="fa fa-plus"></i> Tambah Batch</a>
                                            <a href="initForm_permintaanpo.action" class="btn btn-warning pull-right" style="margin-right: 5px"><i class="fa fa-arrow-left"></i> Back</a>
                                        </div>
                                    </div>
                                </div>
                                <table class="table table-bordered table-striped" id="tabel_po">
                                    <thead>
                                    <tr bgcolor="#90ee90">
                                        <td>No Batch</td>
                                        <td>Last Update</td>
                                        <td>Status</td>
                                        <td align="center">Action</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <s:iterator value="#session.listOfBatch" var="row">
                                        <tr id='row<s:property value="noBatch"/>'>
                                            <td align="center" width="15%">
                                        <span style="background-color: #00a65a;padding: 5px; color: #ffffff">
                                            <s:property value="noBatch"/>
                                        </span>
                                            </td>
                                            <td><s:property value="stLastUpdateWho"/></td>
                                            <td>
                                                <s:if test='#row.isApprove == "Y"'><span class="label label-warning">Prosess Approve</span></s:if>
                                                <s:else><span class="label label-success">Telah di Approve</span></s:else>
                                            </td>
                                            <td align="center">
                                                <s:if test='#row.isApprove == "Y"'>
                                                    <a onclick="updateBatch('<s:property value="noBatch"/>')">
                                                        <img class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                                    </a>
                                                    <a id='app<s:property value="noBatch"/>' onclick="confirmBatch('<s:property value="noBatch"/>')">
                                                        <img class="hvr-grow" src="<s:url value="/pages/images/icons8-test-passed-23.png"/>" style="cursor: pointer;">
                                                    </a>
                                                </s:if>
                                                <s:else>
                                                    <img onclick="showDetailListObat('<s:property value="noBatch"/>')" class="hvr-grow" src="<s:url value="/pages/images/icons8-print-25.png"/>" style="cursor: pointer;">
                                                </s:else>
                                                <img id='load<s:property value="noBatch"/>' src="<s:url value="/pages/images/spinner.gif"/>" style="height: 35px; width: 35px; display: none">
                                            </td>
                                        </tr>
                                    </s:iterator>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <br>
                </div>
            </div>
        </div>
    </section>
</div>

<div class="modal fade" id="modal-approve">
    <div class="modal-dialog modal-flat" style="width: 50%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Approve Permintaan PO dengan No Batch <span id="mod_batch"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none;" id="warning_app">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_app"></p>
                </div>
                <div class="box-header with-border"><i class="fa fa-file-o"></i> Detail Permintaan Po
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_approve">
                        <thead>
                        <td>ID</td>
                        <td>Nama Obat</td>
                        <td align="center">Qty Request</td>
                        <td align="center">Qty Total Approve</td>
                        <td>Jenis Satuan</td>
                        <td align="center">Harga</td>
                        </thead>
                        <tbody id="body_approve">
                        </tbody>
                        <input id="app_no_batch" type="hidden">
                    </table>
                    <p id="loading_data" style="color: #00a65a; display: none"><img src="<s:url value="/pages/images/spinner.gif"/>" style="height: 40px; width: 40px;"> Sedang mengambil data...</p>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_approve" onclick="confirmDialog()"><i class="fa fa-arrow-right"></i> Konfirmasi
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_approve"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-detail">
    <div class="modal-dialog modal-flat" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Print List Obat Permintaan dengan No Batch <span id="detail_batch"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none;" id="warning_detail">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_detail"></p>
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_detail">
                        <thead>
                        <td>ID Barang</td>
                        <td>Nama Obat</td>
                        <td align="center">Qty Approve</td>
                        <td align="center">Jenis Satuan</td>
                        <td align="center">Action</td>
                        </thead>
                        <tbody id="body_detail">
                        </tbody>
                    </table>
                    <p id="loading_detail" style="color: #00a65a; display: none"><img src="<s:url value="/pages/images/spinner.gif"/>" style="height: 40px; width: 40px;"> Sedang mengambil data...</p>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
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
                <button type="button" class="btn btn-sm btn-default" id="save_con" onclick="approveBatch()"><i class="fa fa-arrow-right"></i> Yes            </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-upload">
    <div class="modal-dialog modal-fade">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Upload Document From Vendor
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="row">
                        <label class="col-md-5">Nota Vendor</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="nota-vendor">
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-md-5">Upload Image</label>
                        <div class="col-md-7">
                            <input type="file" class="form-control" id="file-doc">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success"  onclick="saveUpload()"><i class="fa fa-arrow-right"></i> Save
                </button>
            </div>
        </div>
    </div>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript'>

    var idApprovalObat = $('#id_approval').val();
    var idpermintaanPo = $('#id_permintaan_vendor').val();

    $(document).ready(function () {
        $('#permintaan_po').addClass('active');
        listDocument();
    });

    function formatRupiah(angka) {
        var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
        ribuan = ribuan.join('.').split('').reverse().join('');
        return ribuan;
    }

    function addBatch(){
        window.location.href = 'edit_permintaanpo.action?id='+idpermintaanPo+'&isBatch=Y&newBatch=Y';
    }

    function updateBatch(noBatch){
        window.location.href = 'edit_permintaanpo.action?id='+idpermintaanPo+'&isBatch=Y&newBatch=N&noBatch='+noBatch;
    }

    function listDocument() {

        PermintaanVendorAction.getListPermintaanVendorDoc(idpermintaanPo, function(response){
            var str = "";
            if (response.length > 0){
                $.each(response, function(i, item){
                    str += '<tr>'+
                        '<td>'+item.notaVendor+'</td>'+
                        '<td align="center"><a href="#"><img src="<s:url value="/pages/images/icons8-search-25.png"/>"></a>'+
                        '</td></tr>';
                });

                $("#body-doc").html(str);
            }
        });
    }

    function saveUpload() {

        var file = $("#file-doc")[0].files[0];
        var notaVendor = $("#nota-vendor").val();
        PermintaanVendorAction.uploadDocVendor(file, notaVendor, idpermintaanPo, function(response){
            console.log(response);
        })
    }

    function upload() {
        $("#modal-upload").modal("show");
    }

    function confirmBatch(noBatch){
        $('#modal-approve').modal('show');
        var table = [];
        $('#body_approve').html('');
        $('#app_no_batch').val('');
        $('#app'+noBatch).hide();
        $('#load'+noBatch).show();
        $('#loading_data').show();
        dwr.engine.setAsync(true);
        PermintaanVendorAction.initApproval(idpermintaanPo, "Y", "N", noBatch, {
            callback: function (response) {
                if (response != null) {
                    $('#app_no_batch').val(noBatch);
                    $.each(response, function (i, item) {
                        table += "<tr>" +
                                "<td>" + item.idObat + "</td>" +
                                "<td>" + item.namaObat + "</td>" +
                                "<td align='center'>" + item.qty + "</td>" +
                                "<td align='center'>" + item.sumQtyApprove + "</td>" +
                                "<td>" + item.jenisSatuan + "</td>" +
                                "<td align='right'>" + formatRupiah(item.hargaPo) + "</td>" +
                                "</tr>";
                    });
                    $('#app'+noBatch).show();
                    $('#load'+noBatch).hide();
                    $('#loading_data').hide();
                } else {
                    $('#app'+noBatch).show();
                    $('#load'+noBatch).hide();
                    $('#loading_data').hide();
                }
                $('#mod_batch').html(noBatch);
                $('#body_approve').html(table);
            }
        });
    }

    function confirmDialog(){
        $('#modal-confirm-dialog').modal('show');
    }
    function approveBatch(){
        $('#modal-confirm-dialog').modal('hide');
        var noBatch = $('#app_no_batch').val();
        $('#save_approve').hide();
        $('#load_approve').show();

        dwr.engine.setAsync(true);
        PermintaanVendorAction.saveApproveBatch(idpermintaanPo, noBatch, {
            callback: function (response) {
                if(response.status = "success"){
                    $('#info_dialog').dialog('open');
                    $('#modal-approve').modal('hide');
                    $('#save_approve').show();
                    $('#load_approve').hide();
                }else{
                    $('#warning_app').show().fadeOut(5000);
                    $('#msg_app').text(response.message);
                    $('#save_approve').show();
                    $('#load_approve').hide();
                }
            }});
    }

    function showDetailListObat(noBatch){

        $('#modal-detail').modal({show:true, backdrop:'static'});
        $('#loading_detail').show();
        $('#detail_batch').text(noBatch);
        dwr.engine.setAsync(true);
        var table = "";
        PermintaanVendorAction.getListDetailObatApproved(idpermintaanPo, noBatch, {
            callback: function (response) {
                if (response != null) {
                    $.each(response, function (i, item) {
                        table += "<tr>" +
                                "<td>" + item.idBarang + "</td>" +
                                "<td>" + item.namaObat + "</td>" +
                                "<td align='center'>" + item.qtyApprove + "</td>" +
                                "<td align='center'>" + item.jenisSatuan + "</td>" +
                                "<td align='center'>" + '<a target="_blank" href="printBarcodeBarang_permintaanpo.action?id='+item.idBarang+'">' +
                                '<img class="hvr-grow" src="<s:url value="/pages/images/icons8-barcode-scanner-25.png"/>" style="cursor: pointer;"></a>'+
                                "</td>" +
                                "</tr>";
                    });
                    $('#loading_detail').hide();
                } else {
                    $('#loading_detail').hide();
                }
                $('#body_detail').html(table);
            }
        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>