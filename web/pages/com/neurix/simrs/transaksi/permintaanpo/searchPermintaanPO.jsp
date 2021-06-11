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
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#permintaan_po').addClass('active');
        });

        function reture() {
            $('#tipePermintaan').val('002').trigger('change');
            $('#flag').val('N').trigger('change');
            document.obatGudangForm.action = 'searchPermintaanObatGudang_obatgudang.action';
            document.obatGudangForm.submit();
        }

    </script>

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
            Purchase Order (PO)
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian PO</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="permintaanPOForm" method="post" namespace="/permintaanpo"
                                    action="search_permintaanpo.action" theme="simple"
                                    cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID PO</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_permintaan" cssStyle="margin-top: 7px"
                                                     name="permintaanVendor.idPermintaanVendor" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">ID Approval</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:textfield id="nama_obat" name="permintaanVendor.idApprovalObat"--%>
                                                     <%--required="false" readonly="false"--%>
                                                     <%--cssClass="form-control" cssStyle="margin-top: 7px"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama PBF</label>
                                    <div class="col-sm-4">
                                        <s:action id="initVendor" namespace="/permintaanpo"
                                                  name="getComboVendor_permintaanpo"/>
                                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                                  list="#initVendor.listOfVendor" id="nama_vendor"
                                                  name="permintaanVendor.idVendor" listKey="idVendor"
                                                  listValue="namaVendor"
                                                  headerKey="" headerValue=" - "
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Status</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'process':'Proses Verifikasi','dikonfirmasi':'Telah Dikonfirmasi'}"
                                                  cssStyle="margin-top: 7px"
                                                  id="status" name="permintaanVendor.status"
                                                  headerKey="" headerValue=" - "
                                                  cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'N':'Non Active'}"
                                                  cssStyle="margin-top: 7px"
                                                  id="flag" name="permintaanVendor.flag"
                                                  headerKey="Y" headerValue="Active"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-8" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="permintaanPOForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-primary" href="add_permintaanpo.action">
                                            <i class="fa fa-plus"></i> Tambah Permintaan PO
                                        </a>
                                        <a type="button" class="btn btn-danger" href="initForm_permintaanpo.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
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
                                                                                         toContent();
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
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true"
                                                   resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                                         alt="Loading..."/></center>
                                        </sj:dialog>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar PO</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID PO</td>
                                <td>Nama PBF</td>
                                <td>Tanggal Permintaan</td>
                                <td>Jenis PO</td>
                                <td align="center">Jumlah Obat</td>
                                <td>Status</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody style="font-size: 13px">
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idPermintaanVendor"/></td>
                                    <td><s:property value="namaVendor"/></td>
                                    <td><s:property value="stCreatedDate"/></td>
                                    <td><s:property value="jenisPo"/></td>
                                    <td align="center"><span style="padding: 6px; background-color: #fbec88; color: black; border-radius: 20px"><s:property value="jumlahObat"/></span></td>
                                    <td><s:if test='#row.keterangan == "Telah Dikonfirmasi"'>
                                        <label class="label label-success"><s:property value="keterangan"/></label>
                                    </s:if><s:else>
                                        <label class="label label-warning"><s:property value="keterangan"/></label>
                                    </s:else></td>
                                    <td align="center">
                                        <s:if test='#row.keterangan == "Telah Dikonfirmasi"'>
                                            <%--<s:url var="print_po" namespace="/permintaanpo" action="printPermintaanPO_permintaanpo" escapeAmp="false">--%>
                                                <%--<s:param name="id"><s:property value="idPermintaanVendor"/></s:param>--%>
                                            <%--</s:url>--%>
                                            <%--<s:a href="%{print_po}" target="_blink">--%>
                                                <%--<img class="hvr-grow" src="<s:url value="/pages/images/icons8-print-25.png"/>" style="cursor: pointer;">--%>
                                            <%--</s:a>--%>
                                            <s:if test='#row.flag == "N"'>
                                                <img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-test-passed-25-orange.png"/>" onclick="showBatch('<s:property value="idPermintaanVendor"/>','<s:property value="flag"/>','<s:property value="idApprovalObat"/>')">
                                            </s:if>
                                            <s:else>
                                                <img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>" onclick="showBatch('<s:property value="idPermintaanVendor"/>','<s:property value="flag"/>','<s:property value="idApprovalObat"/>')">
                                            </s:else>
                                        </s:if>
                                        <s:else>
                                            <s:url var="verify_po" namespace="/permintaanpo" action="edit_permintaanpo" escapeAmp="false">
                                                <s:param name="id"><s:property value="idPermintaanVendor"/></s:param>
                                            </s:url>
                                            <s:a href="%{verify_po}">
                                                <img class="hvr-grow" src="<s:url value="/pages/images/icons8-create-25.png"/>" style="cursor: pointer;">
                                            </s:a>
                                        </s:else>
                                        <img class="hvr-grow" style="cursor: pointer" src="<s:url value="/pages/images/icons8-print-25.png"/>" onclick="printPo('<s:property value="idPermintaanVendor"/>','<s:property value="idApprovalObat"/>')">

                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<div class="modal fade" id="modal-batch">
    <div class="modal-dialog modal-flat" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Purchase Order Obat</span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-success alert-dismissible" style="display: none;" id="success_bat">
                    <h4><i class="icon fa fa-info"></i> Success!</h4>
                    <p id="msg_success"></p>
                </div>
                <div class="alert alert-danger alert-dismissible" style="display: none;" id="warning_bat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_bat"></p>
                </div>
                <div class="box">
                    <table class="table table-striped table-bordered" id="tabel_bat">
                        <thead>
                        <td align="center">No Batch</td>
                        <td>Last Update</td>
                        <td align="center" width="100px">Action</td>
                        </thead>
                        <tbody id="body_bat" style="font-size: 13px;vertical-align: middle">
                        </tbody>
                    </table>
                </div>
                <input type="hidden" id="bat_id_permintaan_vendor">
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <%--<button type="button" class="btn btn-success" id="save_bat" onclick="confirmSaveTutup()"><i--%>
                        <%--class="fa fa-arrow-right"></i> Save--%>
                <%--</button>--%>
                <%--<button style="display: none; cursor: no-drop" type="button" class="btn btn-success"--%>
                        <%--id="load_bat"><i--%>
                        <%--class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...--%>
                <%--</button>--%>
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Purchase Order Obat <span id="detail_batch"></span>
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
                                </tr>
                                <tr>
                                    <td>Tanggal Faktur</td>
                                    <td><p id="det_tlg_faktur"></p></td>
                                </tr>
                                <tr>
                                    <td>Foto Doc</td>
                                    <td><button id="det_img" class="btn btn-primary"><i class="fa fa-image"></i></button></td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-md-6">
                            <table class="table table-striped">
                                <tr>
                                    <td width="40%">Nomor Invoice</td>
                                    <td><p id="det_no_invoice"></p></td>
                                </tr>
                                <tr>
                                    <td>No DO</td>
                                    <td><p id="det_no_do"></p></td>
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
                        <tbody id="body_detail" style="font-size: 13px">
                        </tbody>
                    </table>
                    <p id="loading_detail" style="color: #00a65a; display: none"><img src="<s:url value="/pages/images/spinner.gif"/>" style="height: 40px; width: 40px;"> Sedang mengambil data...</p>
                </div>
                <input type="hidden" id="id_permintaan_vendor">
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <%--<button type="button" class="btn btn-success" id="save_detail" onclick="confirmSaveTutup()"><i--%>
                        <%--class="fa fa-arrow-right"></i> Save--%>
                <%--</button>--%>
                <%--<button style="display: none; cursor: no-drop" type="button" class="btn btn-success"--%>
                        <%--id="load_detail"><i--%>
                        <%--class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...--%>
                <%--</button>--%>
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

<div class="modal fade" id="modal-view-img">
    <div class="modal-dialog modal-fade modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-info"></i> View Uplaoded Documment
                </h4>
            </div>
            <div class="modal-body">
                <div id="body-img">
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-upload">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Upload Document Batch <span id="mod_batch"></span>
                </h4>
                <input type="hidden" id="no_po"/>
            </div>
            <div class="modal-body" id="back_top">
                <div class="alert alert-danger alert-dismissible" style="display: none;" id="warning_upload">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_upload"></p>
                </div>
                <div class="box-body">
                    <hr>
                    <span style="font-weight: bold">Faktur Pajak</span>
                    <br>
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table">
                                <tr>
                                    <td width="40%" style="font-size: 13px">Nomor</td>
                                    <td><input type="text" class="form-control" id="app_no_faktur"
                                               onchange="checkAvail(this.value, 'faktur')"/></td>
                                    <input type="hidden" id="avail-no-faktur" value=""/>
                                </tr>
                                <tr>
                                    <td style="font-size: 13px">Tanggal</td>
                                    <td>
                                        <div class="input-group">
                                            <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                            <input readonly placeholder="yyyy-mm-dd *klik untuk tgl"
                                                   style="cursor: pointer" class="form-control datepicker"
                                                   id="app_tgl_faktur"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td style="font-size: 13px">
                                        <div class="alert alert-danger" id="alert-faktur" style="display: none">No
                                            Faktur Telah Dipakai
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <div>Upload Document</div>
                                <div class="input-group">
                                    <input type="file" class="form-control" name="uploadFaktur" id="upload-faktur-0"
                                           onchange="uploadDoc('faktur', '0')"/>
                                    <span id="warning-faktur-0" style="font-size: 12px; color: red; display: none"><i class="fa fa-warning"></i> File harus .jpg, .png .pdf</span>
                                    <div class="input-group-btn">
                                        <a class="btn btn-warning" style="padding: 9px" onclick="addUpload('faktur')"><i
                                                class="fa fa-plus"></i></a>
                                    </div>
                                </div>
                                <canvas id="canvas-faktur-0" class="faktur" style="display: none"></canvas>
                                <div id="set_faktur"></div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <span style="font-weight: bold">Invoice</span>
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table">
                                <tr>
                                    <td width="40%" style="font-size: 13px">Nomor</td>
                                    <td><input type="text" class="form-control" id="app_no_invoice"
                                               onchange="checkAvail(this.value, 'invoice')"/></td>
                                    <input type="hidden" id="avail-no-invoice" value=""/>
                                </tr>
                                <tr>
                                    <td width="40%" style="font-size: 13px">Tanggal</td>
                                    <td>
                                        <div class="input-group">
                                            <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                            <input readonly placeholder="yyyy-mm-dd *klik untuk tgl"
                                                   style="cursor: pointer" class="form-control datepicker"
                                                   id="tgl-invoice"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td style="font-size: 13px">
                                        <div class="alert alert-danger" id="alert-invoice" style="display: none">No
                                            Invoice Telah Dipakai
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-md-6">
                            <div>Upload Document</div>
                            <div class="input-group">
                                <input type="file" class="form-control" name="uploadInvoice" id="upload-invoice-0"
                                       onchange="uploadDoc('invoice', '0')"/>
                                <span id="warning-invoice-0" style="font-size: 12px; color: red; display: none"><i class="fa fa-warning"></i> File harus .jpg, .png .pdf</span>
                                <div class="input-group-btn">
                                    <a class="btn btn-warning" style="padding: 9px" onclick="addUpload('invoice')"><i
                                            class="fa fa-plus"></i></a>
                                </div>
                            </div>
                            <canvas id="canvas-invoice-0" class="invoice" style="display: none"></canvas>
                            <div id="set_invoice"></div>
                        </div>
                    </div>
                    <hr>
                    <span style="font-weight: bold">Delivery Order</span>
                    <div class="row">
                        <div class="col-md-6">
                            <table class="table">
                                <tr>
                                    <td width="40%" style="font-size: 13px">Nomor</td>
                                    <td><input type="text" class="form-control" id="app_no_do"
                                               onchange="checkAvail(this.value, 'do')"/></td>
                                    <input type="hidden" id="avail-no-do" value=""/>
                                </tr>
                                <tr>
                                    <td width="40%" style="font-size: 13px">Tanggal</td>
                                    <td>
                                        <div class="input-group">
                                            <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                            <input readonly placeholder="yyyy-mm-dd *klik untuk tgl"
                                                   style="cursor: pointer" class="form-control datepicker" id="tgl-do"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td style="font-size: 13px">
                                        <div class="alert alert-danger" id="alert-do" style="display: none">No DO Telah
                                            Dipakai
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-md-6">
                            <div>Upload Document</div>
                            <div class="input-group">
                                <input type="file" class="form-control" name="uploadDo" id="upload-do-0"
                                       onchange="uploadDoc('do', '0')"/>
                                <span id="warning-do-0" style="font-size: 12px; color: red; display: none"><i class="fa fa-warning"></i> File harus .jpg, .png .pdf</span>
                                <div class="input-group-btn">
                                    <a class="btn btn-warning" style="padding: 9px" onclick="addUpload('do')"><i
                                            class="fa fa-plus"></i></a>
                                </div>
                            </div>
                            <canvas id="canvas-do-0" class="do" style="display: none"></canvas>
                            <div id="set_do"></div>
                        </div>
                    </div>
                    <div class="alert alert-danger" id="alert-panel" style="display: none;"></div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_approve" onclick="saveUploadDoc()"><i
                        class="fa fa-check"></i> Upload
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_approve">
                    <i
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

<!-- /.content-wrapper -->
<script type='text/javascript'>

    function showBatch(idPermintaan, flag, idApproval){
        $('#body_bat').html('');
        $('#modal-batch').modal({show:true, backdrop:'static'});
        PermintaanVendorAction.listBatch(idApproval, function (response) {
            console.log(response);
            var table = "";
            if(response.length > 0){
                $.each(response, function (i, item) {
                    var tgl = $.datepicker.formatDate('dd-mm-yy', new Date(item.tanggalFaktur));
                    table += '<tr style="vertical-align: middle">' +
                        '<td align="center" width="100px" style="vertical-align: middle">'+item.noBatch+'</td>' +
                        '<td  style="vertical-align: middle">'+item.stLastUpdateWho+'</td>' +
                        '<td align="center" width="120px">' +
                        '<div style="float:left;margin-right:10px">' +
                        '<img style="cursor: pointer" onclick="showUploadDoc(\''+idPermintaan+'\',\''+item.noBatch+'\')" src="<s:url value="/pages/images/icons8-pictures-folder-25.png"/>">'+
                        '<div style="font-size: 10px;">upload</div>' +
                        '</div>' +
                        '<div style="float:left;margin-right:10px">' +
                        '<img style="cursor: pointer" onclick="showDetailListObat(\''+idPermintaan+'\',\''+item.noBatch+'\',\''+item.noFaktur+'\',\''+tgl+'\',\''+item.noInvoice+'\',\''+item.noDo+'\',\''+item.urlDoc+'\')" src="<s:url value="/pages/images/icons8-search-25.png"/>">'+
                        '<div style="font-size: 10px;">detail</div>' +
                        '</div>' +
                        '<div style="float:left">' +
                        '<a target="_blank" href="printPermintaanPO_permintaanpo?id='+idPermintaan+'&noBatch='+item.noBatch+'">' +
                        '<img src="<s:url value="/pages/images/icons8-print-25.png"/>">'+
                        '</a>'+
                        '<div style="font-size: 10px;">print</div>' +
                        '</div>' +
                        '</td>' +
                        '</tr>';
                });
                $('#body_bat').html(table);
            }
        })
    }

    function showUploadDoc(idPermintaan, noBatch){

        $("#avail-no-faktur").val("");
        $("#avail-no-invoice").val("");
        $("#avail-no-do").val("");

        $("#app_no_faktur").val("");
        $("#app_tgl_faktur").val("");
        $("#app_no_invoice").val("");
        $("#app_no_do").val("");
        $("#tgl-invoice").val("");
        $("#tgl-do").val("");

        $("#set_faktur").html("");
        $("#set_invoice").html("");
        $("#set_do").html("");

        $("#upload-faktur-0").val("");
        $("#upload-invoice-0").val("");
        $("#upload-do-0").val("");

        $("#modal-upload").modal('show');

        $("#no_po").val(idPermintaan);
        $("#mod_batch").text(noBatch);
    }

    function addUpload(tipe) {
        var cekTipe = $('.' + tipe).length;
        var remove = 'remove_' + tipe + cekTipe;
        var set = '<div id="' + remove + '">' +
            '<div class="input-group" style="margin-top: 7px">\n' +
            '<input type="file" class="form-control" id="upload-' + tipe + '-' + cekTipe + '" onchange="uploadDoc(\'' + tipe + '\', \'' + cekTipe + '\')"/>\n' +
            '<span id="warning-'+tipe+'-'+cekTipe+'" style="font-size: 12px; color: red; display: none"><i class="fa fa-warning"></i> File harus .jpg, .png .pdf</span>'+
            '<div class="input-group-btn">\n' +
            '    <a class="btn btn-danger" style="padding: 9px" onclick="delUpload(\'' + remove + '\')"><i class="fa fa-trash"></i></a>\n' +
            '</div>\n' +
            '</div>' +
            '<canvas id="canvas-' + tipe + '-' + cekTipe + '" class="' + tipe + '" style="display: none"></canvas>' +
            '</div>';
        $('#set_' + tipe).append(set);
    }

    function delUpload(id) {
        $('#' + id).remove();

    }

    function checkAvail(idItem, jenis) {
        var batch = $("#mod_batch").text();
        var idTransakasi = $("#no_po").val();

        PermintaanVendorAction.getListBatchByJenisItem(idItem, jenis, idTransakasi, batch, function (res) {
            if (res != null && res.length > 0) {
                $.each(res, function (i, item) {

                    if (jenis == "faktur") {
                        $("#avail-no-faktur").val(item.noFaktur);
                        $("#alert-faktur").show();
                    } else {
                        $("#avail-no-faktur").val("");
                        $("#alert-faktur").hide();
                    }
                    if (jenis == "invoice") {
                        $("#avail-no-invoice").val(item.noInvoice);
                        $("#alert-invoice").show();
                    } else {
                        $("#avail-no-invoice").val("");
                        $("#alert-invoice").hide();
                    }
                    if (jenis == "do") {
                        $("#avail-no-do").val(item.noDo);
                        $("#alert-do").show();
                    } else {
                        $("#avail-no-do").val("");
                        $("#alert-do").hide();
                    }
                });
            } else {
                if (jenis == "faktur") {
                    $("#avail-no-faktur").val("");
                    $("#alert-faktur").hide();
                }
                if (jenis == "invoice") {
                    $("#avail-no-invoice").val("");
                    $("#alert-invoice").hide();
                }
                if (jenis == "do") {
                    $("#avail-no-do").val("");
                    $("#alert-do").hide();
                }
            }
        });
    }

    function saveUploadDoc() {
        removeLocalStorageAtas("img_list");
        var idPermintaan    = $("#no_po").val();
        var noBatch         = $("#mod_batch").text();

        var availFaktur = $("#avail-no-faktur").val();
        var availInvoice = $("#avail-no-invoice").val();
        var availDo = $("#avail-no-do").val();

        if (availDo != "" || availFaktur != "" || availInvoice != "") {
            $("#alert-panel").show().fadeOut(5000);
            $("#alert-panel").html("Nomor Telah Ada.");
            return false;
        }

        var noFaktur    = $('#app_no_faktur').val();
        var noInvoice   = $('#app_no_invoice').val();
        var noDo        = $('#app_no_do').val();

        var tgl         = $('#app_tgl_faktur').val();
        var tglInvoice  = $('#tgl-invoice').val();
        var tglDo       = $('#tgl-do').val();

        var listOfimg   = [];

        if (noFaktur != '' && noInvoice != '' && noDo != '') {
            var data = {
                'no_faktur': noFaktur,
                'no_invoice': noInvoice,
                'no_do': noDo,
                'tgl_faktur': tgl,
                'tgl_invoice': tglInvoice,
                'tgl_do': tglDo
            };

            var cekFaktur = $('.faktur');
            var cekInvoice = $('.invoice');
            var cekDo = $('.do');

            if (cekFaktur.length > 0) {
                $.each(cekFaktur, function (i, item) {
                    var files = document.getElementById('upload-faktur-' + i).files;
                    if (files.length > 0) {
                        var fileToLoad = files[0];
                        var fileReader = new FileReader();
                        var base64File = "";
                        fileReader.onload = function(event) {
                            base64File = event.target.result;
                            var eks = cekEks(base64File);
                            var place = replaceFile(base64File);
                            if(eks != ""){
                                listOfimg.push({
                                    "jenisnomor": "faktur",
                                    "batch": noBatch,
                                    "iditem": noFaktur,
                                    "img": place,
                                    "eks": eks
                                });
                                setLocalStoregeAtas('img_list', JSON.stringify(listOfimg));
                            }
                        }
                        fileReader.readAsDataURL(fileToLoad);
                    }
                });
            }

            if (cekInvoice.length > 0) {
                $.each(cekInvoice, function (i, item) {
                    var files = document.getElementById('upload-invoice-' + i).files;
                    if (files.length > 0) {
                        var fileToLoad = files[0];
                        var fileReader = new FileReader();
                        var base64File;
                        fileReader.onload = function(event) {
                            base64File = event.target.result;
                            var eks = cekEks(base64File);
                            var place = replaceFile(base64File);
                            if(eks != ""){
                                listOfimg.push({
                                    "jenisnomor": "invoice",
                                    "batch": noBatch,
                                    "iditem": noInvoice,
                                    "img": place,
                                    "eks": eks
                                });
                                setLocalStoregeAtas('img_list', JSON.stringify(listOfimg));
                            }
                        };
                        fileReader.readAsDataURL(fileToLoad);
                    }
                })
            }

            if (cekDo.length > 0) {
                $.each(cekDo, function (i, item) {
                    var files = document.getElementById('upload-do-' + i).files;
                    if (files.length > 0) {
                        var fileToLoad = files[0];
                        var fileReader = new FileReader();
                        var base64File;
                        fileReader.onload = function(event) {
                            base64File = event.target.result;
                            var eks = cekEks(base64File);
                            var place = replaceFile(base64File);
                            if(eks != ""){
                                listOfimg.push({
                                    "jenisnomor": "do",
                                    "batch": noBatch,
                                    "iditem": noDo,
                                    "img": place,
                                    "eks": eks
                                });
                                setLocalStoregeAtas('img_list', JSON.stringify(listOfimg));
                            }
                        }
                        fileReader.readAsDataURL(fileToLoad);
                    }
                });
            }
            var stData  = JSON.stringify(data);
            $('#modal-confirm-dialog').modal('show');
            $('#save_con').attr('onclick', 'saveUploaded(\''+idPermintaan+'\', \''+noBatch+'\', \'' + stData + '\')');
        }else{
            $('#warning_upload').show().fadeOut(5000);
            $('#msg_upload').text("Silahkan cek kembali inputan anda...!");
            $("#modal-upload").scrollTop(0);
        }
    }

    function saveUploaded(idPermintaan, noBatch, stData){
        $('#modal-confirm-dialog').modal('hide');
        var listimg = getLocalStorageAtas("img_list");
        $('#save_approve').hide();
        $('#load_approve').show();
        dwr.engine.setAsync(true);
        PermintaanVendorAction.saveDocPo(idPermintaan, noBatch, listimg, stData, {
            callback:function (res) {
                if (res.status == "success"){
                    $("#modal-upload").modal('hide');
                    $('#success_bat').show().fadeOut(5000);
                    $('#msg_success').text("Berhasil meyimpan data...!");
                    $('#save_approve').show();
                    $('#load_approve').hide();
                }else{
                    $('#warning_upload').show().fadeOut(5000);
                    $('#msg_upload').text(res.message);
                    $('#save_approve').show();
                    $('#load_approve').hide();
                }
            }
        });
    }

    function showDetailListObat(idpermintaanPo, noBatch, noFaktur, tglFaktur, noInvoice, noDo, img){
        $('#modal-detail').modal({show:true, backdrop:'static'});
        $('#det_no_faktur').text(noFaktur);
        $('#det_tlg_faktur').text(tglFaktur);
        $('#det_no_invoice').text(noInvoice);
        $('#det_no_do').text(noDo);
        $('#det_img').attr('onclick', 'viewUpload(\''+idpermintaanPo+'\', \''+noBatch+'\')')
        $('#loading_detail').show();
        $('#save_detail').show();
        $('#id_permintaan_vendor').val(idpermintaanPo);
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

    function confirmSaveTutup(){
        $('#modal-confirm-dialog').modal('show');
        $('#save_con').attr('onclick','saveTutup()');
    }

    function saveTutup() {
        $('#modal-confirm-dialog').modal('hide');
        var idPermintaan = $('#id_permintaan_vendor').val();
        $('#save_detail').hide();
        $('#load_detail').show();
        dwr.engine.setAsync(true);
        PermintaanVendorAction.tutupPurchaseOrder(idPermintaan, {callback: function (response) {
                if (response.status == "success") {
                    $('#modal-detail').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#save_detail').show();
                    $('#load_detail').hide();
                    $('body').scrollTop(0);
                } else {
                    $('#save_detail').show();
                    $('#load_detail').hide();
                    $('#warning_detail').show().fadeOut(5000);
                    $('#msg_detail').text(response.msg);
                }
            }
        });
    }

    function toContent() {
        window.location.reload(true);
    }

    function showDoc(img){
        $('#img_surat_po').attr('src',img);
        $('#modal-doc').modal('show');
    }

    function post(path, params) {

        var method='post';
        // The rest of this code assumes you are not using a library.
        // It can be made less wordy if you use one.
        const form = document.createElement('form');
        form.method = method;
        form.action = path;
        form.target = "_blank";

        for (const key in params) {
            if (params.hasOwnProperty(key)) {
                const hiddenField = document.createElement('input');
                hiddenField.type = 'hidden';
                hiddenField.name = key;
                hiddenField.value = params[key];

                form.appendChild(hiddenField);
            }
        }

        document.body.appendChild(form);
        form.submit();
    }

    function firstpath() {
        var pathArray = window.location.pathname.split('/');
        var first = pathArray[1];
        return "/" + first;
    }


    function printPo(idPermintaan, idApproval) {
        var form = { "permintaanVendor.idPermintaanVendor":idPermintaan, "permintaanVendor.idApprovalObat":idApproval };
        var host = firstpath()+"/permintaanpo/printPo_permintaanpo.action";
        post(host, form);
    }

    function viewUpload(idpermintaanPo, batch) {
        $("#modal-view-img").modal('show');
        $("#body-img").html("");
        PermintaanVendorAction.getListItemDoc(idpermintaanPo, batch, function (list) {
            var str = '';
            if(list.length > 0){
                $.each(list, function (i, item) {
                    var id = 'carousel-example-generic_'+item.idItem;
                    str += '<h5>'+item.jenisNomor.toUpperCase()+' - '+item.idItem+'</h5><div id="'+id+'" class="carousel slide" data-ride="carousel">\n' +
                        '<ol class="carousel-indicators" id="li_'+item.idItem+item.jenisNomor+'">\n' +
                        '</ol>\n' +
                        '<div class="carousel-inner" id="item_'+item.idItem+item.jenisNomor+'">\n' +
                        '</div>\n' +
                        '<a class="left carousel-control" href="#'+id+'" data-slide="prev">\n' +
                        '    <span class="fa fa-angle-left"></span>\n' +
                        '</a>\n' +
                        '<a class="right carousel-control" href="#'+id+'" data-slide="next">\n' +
                        '    <span class="fa fa-angle-right"></span>\n' +
                        '</a>\n' +
                        '</div><hr>';
                    showImg(item.idItem, item.jenisNomor);
                });
            }else{
                str = '<b style="text-align: center">Foto tidak ada..!</b>'
            }
            $("#body-img").html(str);
        });
    }

    function showImg(idItem, jenis){
        PermintaanVendorAction.getListImg(idItem, function (listimg) {
            var str = '';
            var li = '';
            $.each(listimg, function (n, img) {
                var aktive = '';
                var liAcktive = '';
                if(n == 0){
                    aktive = 'active';
                    liAcktive = 'class="active"';
                }

                if(img.tipe == "PDF"){
                    str += '<div class="item ' + aktive + '">\n' +
                        '<embed src="'+contextPathHeader + '/images/upload/surat_po/'+img.urlImg+'" style="width: 100%; height: 400px"/>'+
                        '</div>';
                }else{
                    str += '<div class="item '+aktive+'">\n' +
                        '<img style="height: 300px; width: 100%" src="'+contextPathHeader+'/images/upload/surat_po/'+img.urlImg+'" alt="Slide'+img.urlImg+'">\n' +
                        '<div class="carousel-caption">\n' +img.urlImg +
                        '</div>\n' +
                        '</div>';
                }
                li += '<li data-target="#carousel-example-generic_'+idItem+'" data-slide-to="'+n+'" '+liAcktive+'></li>';
            });
            $("#item_"+idItem+jenis).html(str);
            $("#li_"+idItem+jenis).html(li);
        });
    }

    function uploadDoc(tipe, ind) {
        var files = document.getElementById('upload-' + tipe + '-' + ind).files;
        if (files.length > 0) {
            var fileToLoad = files[0];
            var fileReader = new FileReader();
            var base64File;
            fileReader.onload = function(event) {
                base64File = event.target.result;
                var eks = cekEks(base64File);
                if(eks == ""){
                    $('#upload-' + tipe + '-' + ind).css('border-bottom','solid 5px #c9302c');
                    $('#warning-'+tipe+'-'+ind).show();
                }else{
                    $('#upload-' + tipe + '-' + ind).css('border-bottom','solid 5px #5cb85c');
                    $('#warning-'+tipe+'-'+ind).hide();
                }
            };
            fileReader.readAsDataURL(fileToLoad);
        }
    }

    function cekFile(id) {
        return $('#' + id).get(0).files.length === 0;
    }

    function replaceFile(byte){
        var conditon = byte.split(",")[0]+',';
        var res = byte.replace(conditon, "");
        return res;
    }

    function cekEks(byte) {
        var res = "";
        var conditon = byte.split(",")[0];
        if (conditon == "data:image/jpeg;base64") {
            res = "jpg";
        } else if (conditon == "data:image/png;base64") {
            res = "png";
        } else if (conditon == "data:application/pdf;base64") {
            res = "pdf";
        }
        return res;
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>