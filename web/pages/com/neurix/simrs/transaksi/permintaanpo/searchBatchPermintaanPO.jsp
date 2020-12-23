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
        .bungkus {
            width: 600px;
            /*height: 100px;*/
            max-width: 100%;
            max-height: 100%;
            margin: auto;
            overflow: hidden;
            margin-bottom: 70px;
        }

        .carousel {
            position: relative;
            width: 100%;
            height: 0;
            padding-top: 56.25%;
            background: #ddd;
        }

        /* Images */

        .carousel-img {
            position: absolute;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            max-width: 100%;
            -webkit-transition: opacity ease-out 0.5s;
            transition: opacity ease-out 0.5s;
        }

        .carousel-img-displayed {
            display: block;
            opacity: 1;
            z-index: 2;
        }

        .carousel-img-hidden {
            display: block;
            opacity: 0;
            z-index: 1;
        }

        .carousel-img-noDisplay {
            display: none;
        }

        /* Flèches de défilement */

        .carousel-arrow {
            z-index: 3;
            display: block;
            position: absolute;
            width: 36px;
            height: 36px;
            top: 50%;
            margin-top: 80px;
            border-radius: 50%;
            border: 0;
            background-color: #fff;
            background-image: url("http://res.cloudinary.com/dnqehhgmu/image/upload/v1509720334/blue-arrow_jk1ydw.svg");
            background-repeat: no-repeat;
            background-position: center;
            background-size: 16px 16px;
            cursor: pointer;
            -webkit-transition: background-size 0.15s ease-out;
            transition: background-size 0.15s ease-out;
        }

        .carousel-arrow:hover,
        .carousel-arrow:focus {
            background-size: 22px 22px;
        }

        .carousel-arrow-next {
            right: 20px;
        }

        .carousel-arrow-prev {
            left: 20px;
            -webkit-transform: rotateZ(180deg);
            -ms-transform: rotate(180deg);
            transform: rotateZ(180deg);
        }

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
        <h1 id="label-head">
            Batch Purchase Order (PO)
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
                                <td width="17%"><b>No. PO</b></td>
                                <td>
                                    <table>
                                        <label id="no_po"></label>
                                    </table>
                                </td>
                            </tr>
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
                            <tr>
                                <td><b>Scan DO</b></td>
                                <td>
                                    <table><input type="text" onchange="searchDo(this.value)" placeholder="Scan No. DO Here !" class="form-control" style="width: 30%;"/></table>
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
                        <div class="row">
                            <div class="col-md-offset-2 col-md-8" >
                                <div class="box-header with-border">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h3 class="box-title"><i class="fa fa-file-text-o"></i> Daftar Batch</h3>
                                        </div>
                                        <div class="col-md-6">
                                            <a class="btn btn-success pull-right" onclick="addBatch()"><i class="fa fa-plus"></i> Tambah Batch</a>
                                            <s:if test='tipe == "reture"'>
                                                <a href="<%= request.getContextPath() %>/returobat/initForm_returobat.action" class="btn btn-warning pull-right" style="margin-right: 5px"><i class="fa fa-arrow-left"></i> Back</a>
                                            </s:if>
                                            <s:else>
                                                <a href="initForm_permintaanpo.action" class="btn btn-warning pull-right" style="margin-right: 5px"><i class="fa fa-arrow-left"></i> Back</a>
                                            </s:else>
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
                                                    <img onclick="showDetailListObat('<s:property value="noBatch"/>',
                                                            '<s:property value="urlDoc"/>',
                                                            '<s:property value="noFaktur"/>',
                                                            '<s:property value="stTanggakFaktur"/>',
                                                            '<s:property value="noInvoice"/>',
                                                            '<s:property value="noDo"/>',
                                                            '<s:property value="stTglInvoice"/>',
                                                            '<s:property value="stTglDo" />'
                                                            )"
                                                         class="hvr-grow" src="<s:url value="/pages/images/icons8-print-25.png"/>" style="cursor: pointer;">
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
    <div class="modal-dialog">
        <div class="modal-content" style="width: 1000px; margin-left: -40%;">
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
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_approve">
                        <thead>
                        <td>Nama Obat</td>
                        <td align="center">Qty Request</td>
                        <td align="center">Qty Approve</td>
                        <td>Jenis Satuan</td>
                        <td align="center">Diskon</td>
                        <td align="center">Bruto</td>
                        <td align="center">Netto</td>
                        </thead>
                        <tbody id="body_approve">
                        </tbody>
                        <input id="app_no_batch" type="hidden">
                    </table>
                    <p id="loading_data" style="color: #00a65a; display: none"><img src="<s:url value="/pages/images/spinner.gif"/>" style="height: 40px; width: 40px;"> Sedang mengambil data...</p>
                </div>
                <div class="box-header with-border"></div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td width="40%">No. Faktur Pajak</td>
                                    <td><input type="text" class="form-control" id="app_no_faktur" onchange="checkAvail(this.value, 'faktur')"/></td>
                                    <input type="hidden" id="avail-no-faktur" value=""/>
                                </tr>
                                <tr>
                                    <td>Tanggal Faktur</td>
                                    <td><input type="date" class="form-control" id="app_tgl_faktur"/></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><div class="alert alert-danger" id="alert-faktur" style="display: none">No Faktur Telah Dipakai</div></td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-md-6">
                            <div style="float: left">Upload Faktur</div>
                            <button class="btn btn-sm btn-warning" style="float: right;" onclick="addUploadFaktur('faktur')"><i class="fa fa-plus"></i></button>
                            <%--<button class="btn btn-sm btn-info" style="float: right;" onclick="viewUpload()"><i class="fa fa-image"></i></button>--%>
                            <input type="file" class="form-control" name="uploadFaktur" id="upload-faktur-0" onchange="uploadDoc('faktur', '0')"/>
                            <canvas id="canvas-faktur-0" style="border: solid 1px #ccc; display: none" ></canvas>
                            <div id="body-upload-faktur-0"></div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td width="40%">No. Invoice</td>
                                    <td><input type="text" class="form-control" id="app_no_invoice" onchange="checkAvail(this.value, 'invoice')" /></td>
                                    <input type="hidden" id="avail-no-invoice" value=""/>
                                </tr>
                                <tr>
                                    <td width="40%">Tgl Jatuh Tempo</td>
                                    <td><input type="date" class="form-control" id="tgl-invoice"/></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><div class="alert alert-danger" id="alert-invoice" style="display: none">No Invoice Telah Dipakai</div></td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-md-6">
                            <div style="float: left">Upload Invoice</div> <button class="btn btn-sm btn-warning" style="float: right;" onclick="addUploadInvoice()"><i class="fa fa-plus"></i></button>
                            <%--<button class="btn btn-sm btn-info" style="float: right;" onclick="viewUpload('invoice')"><i class="fa fa-image"></i></button>--%>
                            <input type="file" class="form-control" name="uploadInvoice" id="upload-invoice-0" onchange="uploadDoc('invoice', '0')"/>
                            <canvas id="canvas-invoice-0" style="border: solid 1px #ccc; display: none" ></canvas>
                            <div id="body-upload-invoice-0"></div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td width="40%">No. DO</td>
                                    <td><input type="text" class="form-control" id="app_no_do" onchange="checkAvail(this.value, 'do')"/></td>
                                    <input type="hidden" id="avail-no-do" value=""/>
                                </tr>
                                <tr>
                                    <td width="40%">Tgl Do</td>
                                    <td><input type="date" class="form-control" id="tgl-do"/></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><div class="alert alert-danger" id="alert-do" style="display: none">No DO Telah Dipakai</div></td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-md-6">
                            <div style="float: left">Upload DO</div> <button class="btn btn-sm btn-warning" style="float: right;" onclick="addUploadDo('do')"><i class="fa fa-plus"></i></button>
                            <%--<button class="btn btn-sm btn-info" style="float: right;" onclick="viewUpload('do')"><i class="fa fa-image"></i></button>--%>
                            <input type="file" class="form-control" name="uploadInvoice" id="upload-do-0"  onchange="uploadDoc('do', '0')"/>
                            <canvas id="canvas-do-0" style="border: solid 1px #ccc; display: none" ></canvas>
                            <div id="body-upload-do-0"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td width="40%"></td>
                                    <td>
                                        <button class="btn btn-sm btn-info" style="float: right;" onclick="viewUpload()"><i class="fa fa-image"></i> View Uploaded Document</button>
                                        <%--<input type="text" class="form-control" id="app_no_do"/>--%>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>

                    <div class="alert alert-danger" id="alert-panel" style="display: none;"></div>
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
    <div class="modal-dialog modal-lg">
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
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td width="40%">Nomor Faktur</td>
                                    <td><p id="det_no_faktur"></p></td>
                                    <td><button class="btn btn-sm btn-info" style="float: right"><i class="fa fa-image"></i></button></td>
                                </tr>
                                <tr>
                                    <td>Tanggal Faktur</td>
                                    <td><p id="det_tlg_faktur"></p></td>
                                </tr>
                                <%--<tr>--%>
                                    <%--<td>Foto Doc</td>--%>
                                    <%--<td><button id="det_img" class="btn btn-primary"><i class="fa fa-image"></i></button></td>--%>
                                <%--</tr>--%>
                            </table>
                        </div>
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td width="40%">Nomor Invoice</td>
                                    <td><p id="det_no_invoice"></p></td>
                                    <td><button class="btn btn-sm btn-info" style="float: right"><i class="fa fa-image"></i></button></td>
                                </tr>
                                <tr>
                                    <td width="40%">Tgl Jatuh Tempo Invoice</td>
                                    <td><p id="view-tgl-invoice"></p></td>
                                </tr>
                                <tr>
                                    <td>No DO</td>
                                    <td><p id="det_no_do"></p></td>
                                    <td><button class="btn btn-sm btn-info" style="float: right"><i class="fa fa-image"></i></button></td>
                                </tr>
                                <tr>
                                    <td width="40%">Tgl Do</td>
                                    <td><p id="view-tgl-do"></p></td>
                                </tr>
                            </table>
                        </div>
                    </div>
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

<div class="modal fade" id="modal-doc">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-file-archive-o"></i> Surat PO</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <img id="img_surat_po" style="height: 500px; width: 100%">
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
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes            </button>
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

<div class="modal fade" id="modal-view-img">
    <div class="modal-dialog modal-fade modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> View Uplaoded Documment
                </h4>
            </div>
            <div class="modal-body">
                <div id="body-img">

                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>


<%--<div class="modal fade" id="modal-detail-rekam-medic-lama">--%>
    <%--<div class="modal-dialog modal-lg">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header" style="background-color: #00a65a">--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
                    <%--<span aria-hidden="true">&times;</span></button>--%>
                <%--<h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Rekam Medik Lama Pasien</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<div class="bungkus">--%>
                    <%--<div class="carousel">--%>
                        <%--<button onclick="carouselSwipe('carousel-arrow-prev')" type="button" id="carousel-arrow-prev" class="carousel-arrow carousel-arrow-prev" arial-label="Image précédente"></button>--%>
                        <%--<button onclick="carouselSwipe('carousel-arrow-next')" type="button" id="carousel-arrow-next" class="carousel-arrow carousel-arrow-next" arial-label="Image suivante"></button>--%>
                        <%--<div id="body-img-rm"></div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="modal-footer" style="background-color: #cacaca">--%>
                <%--<button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close--%>
                <%--</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>


<!-- /.content-wrapper -->
<script type='text/javascript'>

    var idApprovalObat  = $('#id_approval').val();
    var idpermintaanPo  = $('#id_permintaan_vendor').val();
    var noPo            = '<s:property value="id" />';
    var jenis           = '<s:property value="tipe" />';
    var nFaktur         = 0;
    var nInvoice        = 0;
    var nDo             = 0;
    var n               = 0;

    $(document).ready(function () {

        if (jenis == "reture"){
            $("#label-head").text("List Batch Pengganti Barang Reture");
        } else {
            jenis = "req";
        }

        $("#no_po").text(noPo);

        var tipe = '<s:property value="tipe"/>';
        if(tipe == "reture"){
            $('#retur_obat').addClass('active');
        }else{
            $('#permintaan_po').addClass('active');
        }

        listDocument();

        var canvas = document.getElementById('img_canvas');
        var ctx = canvas.getContext('2d');

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
                var reader = new FileReader();
                reader.onload = function(event){
                    var img = new Image();
                    img.onload = function(){
                        canvas.width = img.width;
                        canvas.height = img.height;
                        ctx.clearRect(0,0,canvas.width,canvas.height);
                        ctx.drawImage(img,0,0);
                    }
                    img.src = event.target.result;
                }
                reader.readAsDataURL(event.target.files[0]);
            } else {
                if (log) alert(log);
            }

        });

    });

    function formatRupiah(angka) {
        var reverse = angka.toString().split('').reverse().join(''),
                ribuan = reverse.match(/\d{1,3}/g);
        ribuan = ribuan.join('.').split('').reverse().join('');
        return ribuan;
    }

    function addBatch(){
        var tipe = '<s:property value="tipe"/>';
        if(tipe == "reture"){
            window.location.href = 'edit_permintaanpo.action?id='+idpermintaanPo+'&isBatch=Y&newBatch=Y&tipe=reture';
        }else{
            window.location.href = 'edit_permintaanpo.action?id='+idpermintaanPo+'&isBatch=Y&newBatch=Y';
        }
    }

    function updateBatch(noBatch){
        var tipe = '<s:property value="tipe"/>';
        if(tipe == "reture"){
            window.location.href = 'edit_permintaanpo.action?id='+idpermintaanPo+'&isBatch=Y&newBatch=N&noBatch='+noBatch+'&tipe=reture';
        }else{
            window.location.href = 'edit_permintaanpo.action?id='+idpermintaanPo+'&isBatch=Y&newBatch=N&noBatch='+noBatch;
        }
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

    function searchDo(noDo) {
        n           = 0;
        nFaktur     = 0;
        nInvoice    = 0;
        nDo         = 0;

        $("#avail-no-faktur").val("");
        $("#avail-no-invoice").val("");
        $("#avail-no-do").val("");

        $('#modal-approve').modal({show:true, backdrop:'static'});
        var table = [];
        var noBatch = null;
        $('#body_approve').html('');
        $('#app_no_batch').val('');
        $('#app'+noBatch).hide();
        $('#load'+noBatch).show();
        $('#loading_data').show();
        dwr.engine.setAsync(true);
        PermintaanVendorAction.initApprovalByNoDo(idpermintaanPo, noDo, {
            callback: function (response) {
                if (response != null) {
                    var dataDo = null;
                    var ppn = 0.1;
                    var total = 0;
                    var subTotal = 0;
                    var totalPpn = 0;
                    $.each(response, function (i, item) {

                        if (noBatch == null){
                            noBatch = item.noBatch;
                            $('#app_no_batch').val(item.noBatch);
                        }

                        var netto = (parseInt(item.bruto) - parseInt(item.diskon)) * parseInt(item.qtyApprove);

                        table += "<tr>" +
                            //                                "<td>" + item.idObat + "</td>" +
                            "<td>" + item.namaObat + "</td>" +
                            "<td align='center'>" + item.qty + "</td>" +
                            "<td align='center'>" + item.qtyApprove + "</td>" +
                            "<td>" + item.jenisSatuan + "</td>" +
                            //                                "<td align='right'>" + formatRupiah(item.hargaPo) + "</td>" +
                            "<td align='right'>" + formatRupiah(item.diskon) + "</td>" +
                            "<td align='right'>" + formatRupiah(item.bruto) + "</td>" +
                            "<td align='right'>" + formatRupiah(netto) + "</td>" +
                            "</tr>";

                        if (dataDo == null){
                            PermintaanVendorAction.getTransaksiObatByIdTrans(item.idTransaksiObatDetail, noBatch, function (res) {
                                if (res != null){
//                                    console.log(res);
                                    $("#app_no_faktur").val(res.noFaktur);
                                    $("#app_tgl_faktur").val(res.stTglFaktur);
                                    $("#app_no_invoice").val(res.noInvoice);
                                    $("#app_no_do").val(res.noDo);
                                    $("#tgl-invoice").val(res.stTglInvoice);
                                    $("#tgl-do").val(res.stTglDo);
                                }
                            });
                        }

                        total = parseInt(total) + parseInt( nullEscape(netto) );
                    });

                    totalPpn = parseInt(total) * parseFloat(ppn);
                    subTotal = parseInt(total) - parseInt(totalPpn);

                    table += "<tr>" +
                        "<td colspan='6' align='right'>Total</td>" +
                        "<td align='right'>"+formatRupiah(total)+"</td>" +
                        "</tr>";

                    table += "<tr>" +
                        "<td colspan='6' align='right'>PPN Masukan</td>" +
                        "<td align='right'>"+formatRupiah(totalPpn)+"</td>" +
                        "</tr>";

                    table += "<tr>" +
                        "<td colspan='6' align='right'>Sub Total</td>" +
                        "<td align='right'>"+formatRupiah(subTotal)+"</td>" +
                        "</tr>";

                    $('#app'+noBatch).show();
                    $('#load'+noBatch).hide();
                    $('#loading_data').hide();
                } else {
                    alert("Data Tidak Ditemukan");
//                    $('#app'+noBatch).show();
//                    $('#load'+noBatch).hide();
//                    $('#loading_data').hide();
                }
                $('#mod_batch').html(noBatch);
                $('#body_approve').html(table);
            }
        });
    }

    function checkAvail(idItem, jenis) {
        console.log("idItem -> "+ idItem);
        console.log("jenis -> "+ jenis);

        var batch = $("#mod_batch").text();
        var idTransakasi = $("#no_po").text();

        PermintaanVendorAction.getListBatchByJenisItem(idItem, jenis, idTransakasi, batch, function (res) {
            if (res != null && res.length > 0){
                $.each(res, function (i,item) {

                    if (jenis == "faktur"){
                        $("#avail-no-faktur").val(item.noFaktur);
                        $("#alert-faktur").show();
                    } else {
                        $("#avail-no-faktur").val("");
                        $("#alert-faktur").hide();
                    }
                    if (jenis == "invoice"){
                        $("#avail-no-invoice").val(item.noInvoice);
                        $("#alert-invoice").show();
                    } else {
                        $("#avail-no-invoice").val("");
                        $("#alert-invoice").hide();
                    }
                    if (jenis == "do"){
                        $("#avail-no-do").val(item.noDo);
                        $("#alert-do").show();
                    } else {
                        $("#avail-no-do").val("");
                        $("#alert-do").hide();
                    }
                });
            } else {
                if (jenis == "faktur"){
                    $("#avail-no-faktur").val("");
                    $("#alert-faktur").hide();
                }
                if (jenis == "invoice"){
                    $("#avail-no-invoice").val("");
                    $("#alert-invoice").hide();
                }
                if (jenis == "do"){
                    $("#avail-no-do").val("");
                    $("#alert-do").hide();
                }
            }
        });
    }

    function confirmBatch(noBatch){
        n           = 0;
        nFaktur     = 0;
        nInvoice    = 0;
        nDo         = 0;

        $("#avail-no-faktur").val("");
        $("#avail-no-invoice").val("");
        $("#avail-no-do").val("");

        $('#modal-approve').modal({show:true, backdrop:'static'});
        var table = [];
        $('#body_approve').html('');
        $('#app_no_batch').val('');
        $('#app'+noBatch).hide();
        $('#load'+noBatch).show();
        $('#loading_data').show();
        dwr.engine.setAsync(true);
        PermintaanVendorAction.initApproval(idpermintaanPo, noBatch, {
            callback: function (response) {
                if (response != null) {
                    $('#app_no_batch').val(noBatch);
                    var dataDo = null;
                    var ppn = 0.1;
                    var total = 0;
                    var subTotal = 0;
                    var totalPpn = 0;
                    $.each(response, function (i, item) {

                        var netto = (parseInt(item.bruto) - parseInt(item.diskon)) * parseInt(item.qtyApprove);

                        table += "<tr>" +
//                                "<td>" + item.idObat + "</td>" +
                                "<td>" + item.namaObat + "</td>" +
                                "<td align='center'>" + item.qty + "</td>" +
                                "<td align='center'>" + item.qtyApprove + "</td>" +
                                "<td>" + item.jenisSatuan + "</td>" +
//                                "<td align='right'>" + formatRupiah(item.hargaPo) + "</td>" +
                                "<td align='right'>" + formatRupiah(item.diskon) + "</td>" +
                                "<td align='right'>" + formatRupiah(item.bruto) + "</td>" +
                                "<td align='right'>" + formatRupiah(netto) + "</td>" +
                                "</tr>";
                        if (dataDo == null){
                            PermintaanVendorAction.getTransaksiObatByIdTrans(item.idTransaksiObatDetail, noBatch, function (res) {
                                if (res != null){
//                                    console.log(res);
                                    $("#app_no_faktur").val(res.noFaktur);
                                    $("#app_tgl_faktur").val(res.stTglFaktur);
                                    $("#app_no_invoice").val(res.noInvoice);
                                    $("#app_no_do").val(res.noDo);
                                    $("#tgl-invoice").val(res.stTglInvoice);
                                    $("#tgl-do").val(res.stTglDo);
                                }
                            });
                        }

                        total = parseInt(total) + parseInt( nullEscape(netto) );
                    });

                    totalPpn = parseInt(total) * parseFloat(ppn);
                    subTotal = parseInt(total) - parseInt(totalPpn);

                    table += "<tr>" +
                        "<td colspan='6' align='right'>Total</td>" +
                        "<td align='right'>"+formatRupiah(total)+"</td>" +
                            "</tr>";

                    table += "<tr>" +
                        "<td colspan='6' align='right'>PPN Masukan</td>" +
                        "<td align='right'>"+formatRupiah(totalPpn)+"</td>" +
                        "</tr>";

                    table += "<tr>" +
                        "<td colspan='6' align='right'>Sub Total</td>" +
                        "<td align='right'>"+formatRupiah(subTotal)+"</td>" +
                        "</tr>";

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

    function nullEscape(val){
        if (val == null)
            return 0;
        return val;
    }

    function confirmDialog(){
        var noBatch     = $('#app_no_batch').val();
        var noFaktur    = $('#app_no_faktur').val();
        var tgl         = $('#app_tgl_faktur').val();
        var tglFaktur   = tgl.split("-").reverse().join("-");
        var noInvoice   = $('#app_no_invoice').val();
        var noDo        = $('#app_no_do').val();

        var tglInvoice  = $('#tgl-invoice').val();
        var tglDo       = $('#tgl-do').val();

        var availFaktur     = $("#avail-no-faktur").val();
        var availInvoice    = $("#avail-no-invoice").val();
        var availDo         = $("#avail-no-do").val();

        if (availDo != "" || availFaktur != "" || availInvoice != ""){
            $("#alert-panel").show().fadeOut(5000);
            $("#alert-panel").html("Nomor Telah Ada.");
            return false;
        }

//        if (tgl == null || tgl == ""){
//            return alert("tanggal tidak ditemukan");
//        } else {
//            return alert(tgl);
//        }
//        var canvas = document.getElementById('img_canvas');
//        var dataURL = canvas.toDataURL("image/png"),
//            dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
        var data = "";
        var listOfimg = [];
        if(noFaktur != '' && tglFaktur != '' && noInvoice != '' && noDo != ''){
            data = {
                'no_batch':noBatch,
                'no_faktur':noFaktur,
                'tgl_faktur':tgl,
                'no_invoice':noInvoice,
                'no_do':noDo,
                'img_url':"",
                'tgl_invoice':tglInvoice,
                'tgl_do':tglDo
            }

            console.log(data);

            for (i=0 ; i <= nFaktur ; i++){
                var canvas = document.getElementById('canvas-faktur-'+i);
                var input = document.getElementById('upload-faktur-'+i);
                if (input.files.length != 0){
                    var dataURL = canvas.toDataURL("image/png"), dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
                    listOfimg.push({"jenisnomor":"faktur", "batch":noBatch, "iditem":noFaktur, "img":dataURL});
                }

            }

            for (i=0 ; i <= nInvoice ; i++){
                var canvas = document.getElementById('canvas-invoice-'+i);
                var input = document.getElementById('upload-invoice-'+i);
                if (input.files.length != 0) {
                    var dataURL = canvas.toDataURL("image/png"), dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
                    listOfimg.push({"jenisnomor":"invoice", "batch":noBatch, "iditem":noInvoice, "img":dataURL});
                }

            }

            for (i=0 ; i <= nDo ; i++){
                var canvas = document.getElementById('canvas-do-'+i);
                var input = document.getElementById('upload-do-'+i);
                if (input.files.length != 0) {
                    var dataURL = canvas.toDataURL("image/png"), dataURL = dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
                    listOfimg.push({"jenisnomor":"do", "batch":noBatch, "iditem":noDo, "img":dataURL});
                }

            }

            console.log(listOfimg);
            var result = JSON.stringify(data);
            var listimg = JSON.stringify(listOfimg);
            $('#modal-confirm-dialog').modal('show');
            $('#save_con').attr('onclick', 'approveBatch(\''+result+'\', \''+listimg+'\')');
        }else{
            $('#warning_app').show().fadeOut(5000);
            $('#msg_app').text("Silahkan cek kembali data inputan anda...!");
        }

    }
    function approveBatch(data, listimg){
        $('#modal-confirm-dialog').modal('hide');
        $('#save_approve').hide();
        $('#load_approve').show();

        dwr.engine.setAsync(true);
        PermintaanVendorAction.saveApproveBatch(idpermintaanPo, data, jenis, listimg, {
            callback: function (response) {
                if(response.status = "success"){
                    $('#info_dialog').dialog('open');
                    $('#modal-approve').modal('hide');
                    $('#save_approve').show();
                    $('#load_approve').hide();
                    $('body').scrollTop(0);
                }else{
                    $('#warning_app').show().fadeOut(5000);
                    $('#msg_app').text(response.message);
                    $('#save_approve').show();
                    $('#load_approve').hide();
                }
            }});
    }

    function showDetailListObat(noBatch, img, noFaktur, tglFaktur, noInvoice, noDo, tglInvoice, tglDo){

        console.log(img);
        $('#det_img').attr('onclick','showDoc(\''+img+'\')');
        $('#det_no_faktur').text(noFaktur);
        // var tgl = converterDate(new Date(tglFaktur));
        // console.log(tgl);
        $('#det_tlg_faktur').text(tglFaktur);
        $('#det_no_invoice').text(noInvoice);
        $('#det_no_do').text(noDo);
        $('#modal-detail').modal({show:true, backdrop:'static'});
        $('#loading_detail').show();
        $('#detail_batch').text(noBatch);
        $('#view-tgl-invoice').text(tglInvoice);
        $('#view-tgl-do').text(tglDo);

        dwr.engine.setAsync(true);
        var table = "";
        $('#det_img').val(img);
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

    function showDoc(img){
        console.log(img);
        $('#img_surat_po').attr('src',img);
        $('#modal-doc').modal('show');
    }

    function addUploadFaktur() {
        nFaktur ++;
        str = '<input type="file" class="form-control" name="uploadFaktur" id="upload-faktur-'+nFaktur+'" onchange="uploadDoc(\'faktur\', \''+nFaktur+'\')"/>' +
            '<canvas id="canvas-faktur-'+nFaktur+'" style="border: solid 1px #ccc; display: none" ></canvas>' +
            '<div id="body-upload-faktur-'+nFaktur+'"></div>';
        var i = nFaktur - 1;
        $("#body-upload-faktur-"+i).html(str);
    }

    function addUploadInvoice() {
        nInvoice ++;
        str = '<input type="file" class="form-control" name="uploadInvoice" id="upload-invoice-'+nInvoice+'"  onchange="uploadDoc(\'invoice\', \''+nInvoice+'\')"/>' +
            '<canvas id="canvas-invoice-'+nInvoice+'" style="border: solid 1px #ccc; display: none" ></canvas>' +
            '<div id="body-upload-invoice-'+nInvoice+'"></div>';
        var i = nInvoice - 1;
        $("#body-upload-invoice-"+i).html(str);
    }

    function addUploadDo() {
        nDo ++;
        str = '<input type="file" class="form-control" name="uploadDo" id="upload-do-'+nDo+'" onchange="uploadDoc(\'do\', \''+nDo+'\')"/>' +
            '<canvas id="canvas-do-'+nDo+'" style="border: solid 1px #ccc; display: none" ></canvas>' +
            '<div id="body-upload-do-'+nDo+'"></div>';
        var i = nDo - 1;
        $("#body-upload-do-"+i).html(str);
    }

    function uploadDoc(tipe, ind){
        var canvas = document.getElementById("canvas-"+tipe+"-"+ind);
        var ctx = canvas.getContext('2d');
        var reader = new FileReader();
        reader.onload = function(event){
            var img = new Image();
            img.onload = function(){
                canvas.width = img.width;
                canvas.height = img.height;
                ctx.clearRect(0,0,canvas.width,canvas.height);
                ctx.drawImage(img,0,0);
            }
            img.src = event.target.result;
        }
        reader.readAsDataURL(event.target.files[0]);
    }

    function firstpath() {
        var pathArray = window.location.pathname.split('/');
        var first = pathArray[1];
        return "/" + first;
    }

    function viewUpload() {
        $("#modal-view-img").modal('show');
//        $("#modal-detail-rekam-medic-lama").modal("show");
        var batch = $("#mod_batch").text();
        $("#body-img").html("");
        PermintaanVendorAction.getListItemDoc(idpermintaanPo, batch, function (list) {

            var str = '';
            $.each(list, function (i, item) {

                str += '<div class="row">' +
                        '<div class="col-md-12" align="center">' +
                        '<h5>'+item.jenisNomor.toUpperCase()+' - '+item.idItem+'</h5>' +
                            '<div class="bungkus">'+
                                '<div class="carousel">' +
                                    '<button onclick="carouselSwipe(\'carousel-arrow-prev\', \''+item.idItem+'\')" type="button" id="carousel-arrow-prev" class="carousel-arrow carousel-arrow-prev" arial-label="Image précédente"></button>'+
                                    '<button onclick="carouselSwipe(\'carousel-arrow-next\', \''+item.idItem+'\')" type="button" id="carousel-arrow-next" class="carousel-arrow carousel-arrow-next" arial-label="Image suivante"></button>'+
                                    '<div id="body-img-'+item.idItem+'"></div>'+
                                '</div>' +
                            '</div>' +
                        '</div>' +
                    '</div>';

                showImg(item.idItem);
            });

         $("#body-img").html(str);
        });
    }

    function showImg(idItem){
        var first = firstpath();
        var indicator = "";
        PermintaanVendorAction.getListImg(idItem, function (listimg) {
            console.log(listimg);
//            var str = '<div class="row">';
            var str = '';
            $.each(listimg, function (n, img) {

//                str += '<div class="col-md-4" align="center">' +
//                    '<img src="'+ first + '/images/upload/surat_po/'+img.urlImg+'" style="width: 300px"/>' +
//                    '</div>';

                if(n == 0){
                    str += '<img id="carousel-'+n+'-'+idItem+'" class="iditem-'+idItem+' carousel-img carousel-img-displayed" src="'+ first + '/images/upload/surat_po/'+img.urlImg+'" alt="Foto Document PO" />';
                }else{
                    str += '<img id="carousel-'+n+'-'+idItem+'" class="iditem-'+idItem+' carousel-img carousel-img-noDisplay" src="'+ first + '/images/upload/surat_po/'+img.urlImg+'" alt="Foto Document PO" />';
                }
            });
//            str += '</div>' +
//                '<br>';
//            $("#body-img-"+idItem).html(str);
            $("#indicator-img").html(indicator);
            $("#body-img-"+idItem).html(str);
        });
    }

    function carouselSwipe(id, idItem) {

        var currentImg = document.getElementsByClassName('iditem-'+idItem+' carousel-img-displayed')[0].id.substring(9);
        console.log("currentImg -> " + currentImg);

        var splitId = currentImg.split("-");
        console.log("split id : ");
        console.log(splitId);

        //var newImg = parseInt(currentImg);
        var newImg = parseInt(splitId[0]);
        if (id == 'carousel-arrow-next') {
            newImg++;
//            if (newImg >= document.getElementsByClassName('carousel-img').length) {
//                newImg = 0;
//            }

            if (newImg >= document.getElementsByClassName('iditem-'+splitId[1]).length) {
                newImg = 0;
            }
        } else if (id == 'carousel-arrow-prev') {
            newImg--;
//            if (newImg<0) {
//                newImg = document.getElementsByClassName('carousel-img').length-1;
//            }
            if (newImg<0) {
                newImg = document.getElementsByClassName('iditem-'+splitId[1]).length-1;
            }
        }

        document.getElementById('carousel-'+currentImg).className = 'iditem-'+splitId[1]+' carousel-img carousel-img-hidden';
        var displayedCarousel = document.getElementById('carousel-'+newImg+'-'+idItem);
//        console.log("id caurosel hidden --> " + 'carousel-'+newImg+'-'+idItem );
//        console.log("display carousel --> ");
//        console.log(displayedCarousel);
        displayedCarousel.className = 'iditem-'+splitId[1]+' carousel-img carousel-img-hidden';
        setTimeout(function() {
            displayedCarousel.className = 'iditem-'+splitId[1]+' carousel-img carousel-img-displayed';
        },20);

        setTimeout(function() {
            document.getElementById('carousel-'+currentImg).className = 'iditem-'+splitId[1]+' carousel-img carousel-img-noDisplay';
        },520);
    }

    function viewDetailRekamMedicLama(headId) {
        console.log("getByTypeRekamMedic ==> "+ headId);
        var str = "";
        var indicator = "";
        CheckupAction.getListUploadRekamMedic(headId, function (response) {
            if (response.length > 0){
                $.each(response, function (i, item) {
                    if(i == 0){
                        str += '<img id="carousel-'+i+'" class="carousel-img carousel-img-displayed" src="'+item.urlImg+'" alt="Foto Rekam Medik" />';
                    }else{
                        str += '<img id="carousel-'+i+'" class="carousel-img carousel-img-noDisplay" src="'+item.urlImg+'" alt="Foto Rekam Medik" />';
                    }
                });
                $("#modal-detail-rekam-medic-lama").modal("show");
                $("#indicator-img").html(indicator);
                $("#body-img-rm").html(str);
            }
        })
    }



</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>