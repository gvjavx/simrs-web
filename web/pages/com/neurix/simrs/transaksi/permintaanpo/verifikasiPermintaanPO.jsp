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
            $('#permintaan_po').addClass('active');

        });

    </script>

    <script type='text/javascript' src='<s:url value="/dwr/interface/PermintaanVendorAction.js"/>'></script>

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
            Verifikasi Permintaan PO
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
                                    <table><s:label name="vendor.idVendor"></s:label></table>
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
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-file-text-o"></i> Daftar Permintaan PO</h3>
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
                                                                                         window.location.href = 'initForm_permintaanpo.action';
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
                        <table class="table table-bordered table-striped" id="tabel_po">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID</td>
                                <td>Nama Obat</td>
                                <td align="center">Jumlah</td>
                                <td align="center">Satuan Jenis</td>
                                <td align="right">Harga</td>
                                <td align="center">Verify</td>
                                <td align="center">Status Obat</td>
                                <td align="center">Status Approve</td>
                                <%--<td align="center">Approve</td>--%>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfObatDetail" var="row">
                                <tr id='row<s:property value="idObat"/>'>
                                    <td><s:property value="idObat"/></td>
                                    <td><s:property value="namaObat"/></td>
                                    <td align="center"><span id='qty<s:property value="idObat"/>'><s:property
                                            value="qty"/></span></td>
                                    <td align="center"><s:property value="jenisSatuan"/></td>
                                    <td align="right"><s:property value="hargaPo"/></td>
                                    <td align="center"><input
                                            onchange="verify('<s:property value="idObat"/>', this.value, '<s:property
                                                    value="qty"/>')" class="form-control" style="width: 150px"
                                            id='pabrik<s:property value="idObat"/>'></td>
                                    <td align="center"><span id='status<s:property value="idObat"/>'></span></td>
                                    <td align="center"><span id='approve<s:property value="idObat"/>'></span></td>
                                    <%--<td align="center"><span id='qtyDefault<s:property value="idObat"/>'></span><input--%>
                                            <%--value='<s:property value="qty"/>' type="number" class="form-control"--%>
                                            <%--style="width: 150px; display: none"--%>
                                            <%--id='qtyApprove<s:property value="idObat"/>'></td>--%>
                                    <td align="center"><img border="0" id='tombol<s:property value="idObat"/>'
                                                            class="hvr-grow"
                                                            src="<s:url value="/pages/images/edit-flat-new.png"/>"
                                                            style="cursor: pointer; height: 25px; width: 25px; display: none">
                                        <img border="0" onclick="delRowObat('<s:property value="idObat"/>')"
                                             id='hapus<s:property value="idObat"/>' class="hvr-grow"
                                             src="<s:url value="/pages/images/cnacel-flat.png"/>"
                                             style="cursor: pointer; height: 25px; width: 25px; display: none"></td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                    <div style="display: none" id="new_obat">
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-file-text-o"></i> Daftar Obat dengan ID pabrik baru</h3>
                    </div>
                    <div class="box-body">
                        <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_new_po">
                            <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                            <p id="msg_new_po"></p>
                        </div>
                        <table class="table table-bordered table-striped" id="tabel_new_po">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID</td>
                                <td>Nama Obat</td>
                                <td align="center">Jumlah</td>
                                <td align="center">Satuan Jenis</td>
                                <td align="right">Jml Box</td>
                                <td align="center">Jml Lembar</td>
                                <td align="center">Lembar/Box</td>
                                <td align="center">Biji/Lembar</td>
                                <td align="center">Biji</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_new_pabrik">
                            </tbody>
                        </table>
                    </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-body">
                        <div class="row">
                            <div class="col-md-4">
                                <a type="button" class="btn btn-success" onclick="confirm()"><i
                                        class="fa fa-arrow-right"></i> Save</a>
                                <a type="button" class="btn btn-warning" href="initForm_permintaanpo.action"><i
                                        class="fa fa-arrow-left"></i>
                                    Back</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<div class="modal fade" id="modal-obat">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambah Obat</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_obat">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="obat_error"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Obat</label>
                        <div class="col-md-7">
                            <s:textfield
                                    onkeypress="var warn =$('#war_nama').is(':visible'); if (warn){$('#cor_nama').show().fadeOut(3000);$('#war_nama').hide()}"
                                    type="text" cssClass="form-control" id="add_nama_obat"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px" id="war_nama"><i
                                    class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px" id="cor_nama">
                                <i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Obat</label>
                        <div class="col-md-7">
                            <s:action id="initJenisObat" namespace="/jenisobat"
                                      name="getListJenisObat_jenisobat"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      onchange="var warn =$('#war_jenis').is(':visible'); if (warn){$('#cor_jenis').show().fadeOut(3000);$('#war_jenis').hide()}"
                                      list="#initJenisObat.listOfJenisObat" id="add_jenis_obat"
                                      listKey="idJenisObat"
                                      listValue="namaJenisObat"
                                      cssClass="form-control select2" multiple="true"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_jenis"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_jenis"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">ID Pabrik</label>
                        <div class="col-md-7">
                            <s:textfield type="text" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_pabrik"
                                         onkeypress="var warn =$('#war_pabrik').is(':visible'); if (warn){$('#cor_pabrik').show().fadeOut(3000);$('#war_pabrik').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_pabrik"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_pabrik"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Merek</label>
                        <div class="col-md-7">
                            <s:textfield type="text" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_merek"
                                         onkeypress="var warn =$('#war_merek').is(':visible'); if (warn){$('#cor_merek').show().fadeOut(3000);$('#war_merek').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_merek"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_merek"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah Box</label>
                        <div class="col-md-3">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_box"
                                         onkeypress="$(this).css('border', '')"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="margin-top: 10px">Lembar/Box</p>
                        </div>
                        <div class="col-md-3">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_lembar_box"
                                         onkeypress="$(this).css('border', '')"></s:textfield>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jumlah Lembar</label>
                        <div class="col-md-3">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_lembar"
                                         onkeypress="$(this).css('border', '')"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="margin-top: 10px">Biji/Lembar</p>
                        </div>
                        <div class="col-md-3">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_biji_lembar"
                                         onkeypress="$(this).css('border', '')"></s:textfield>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jml Biji</label>
                        <div class="col-md-7">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_biji"
                                         onkeypress="var warn =$('#war_biji').is(':visible'); if (warn){$('#cor_biji').show().fadeOut(3000);$('#war_biji').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_biji"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_biji"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Harga Obat</label>
                        <div class="col-md-7">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_harga"
                                         onkeypress="var warn =$('#war_harga').is(':visible'); if (warn){$('#cor_harga').show().fadeOut(3000);$('#war_harga').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_harga"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_harga"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_obat"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_obat"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-approve">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Konfirmasi Qty Approve</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_approve">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_approve"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Qty Permintaan</label>
                        <div class="col-md-7">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="app_qty" readonly="true"
                                         onkeypress="var warn =$('#war_app_qty').is(':visible'); if (warn){$('#cor_app_qty').show().fadeOut(3000);$('#war_app_qty').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_app_qty"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_app_qty"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Qty Approve</label>
                        <div class="col-md-7">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="app_qty_app"
                                         onkeypress="var warn =$('#war_app_qty_app').is(':visible'); if (warn){$('#cor_app_qty_app').show().fadeOut(3000);$('#war_app_qty_app').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_app_qty_app"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_app_qty_app"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_approve"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_approve"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript'>

    function reset() {
        window.location.reload(true);
    }

    function showModal(idObat) {
        $('#add_nama_obat, #add_harga, #add_merek, #add_pabrik, #add_box, #add_lembar_box, #add_lembar, #add_biji_lembar, #add_biji').val('');
        $('#add_jenis_obat').val('').trigger('change');
        var id = "";
        $('#load_obat, #war_nama, #war_jenis, #war_harga, #war_pabrik, #war_merek, #war_biji').hide();
        $('#add_box, #add_lembar_box, #add_lembar, #add_biji_lembar').css('border', '');
        $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\')').show();
        $('#modal-obat').modal('show');
    }
    function verify(id, value, qty) {
        var status = false;
        if (id != '' && value != '') {
            $('#status' + id).html('<img src="<s:url value="/pages/images/spinner.gif"/>" style="height: 35px; width: 35px;">');
            dwr.engine.setAsync(true);
            PermintaanVendorAction.checkIdPabrikan(id, value, {
                callback: function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        $('#pabrik' + id).attr('readonly', true).blur();
                        $('#status' + id).html("Sesuai").addClass("label label-success");
                        $('#app_qty').val(qty);
                        $('#app_qty_app').val(qty);
                        $('#app_qty_app').focusin();
                        $('#modal-approve').modal('show');
//                        $('#qtyDefault' + id).html(qty);
//                        $('#tombol' + id).show().attr('onclick', 'editQty(\'' + id + '\')');
                    } else {
                        $('#pabrik' + id).attr('readonly', true).blur();
                        $('#status' + id).html("Tidak Sesuai").addClass("label label-danger");
                        var url = '<s:url value="/pages/images/new-flat-plus.png"/>';
                        $('#tombol' + id).attr('onclick', 'showModal(\'' + id + '\')');
                        $('#tombol' + id).show().attr('src', url);
                        $('#hapus' + id).show();
                    }
                }
            });
        }
    }

    function editQty(id) {
        if ($('#tombol' + id).attr('src') == '/simrs/pages/images/edit-flat-new.png') {
            var url = '<s:url value="/pages/images/save_flat.png"/>';
            $('#tombol' + id).attr('src', url);
            $('#qtyDefault' + id).hide();
            $('#qtyApprove' + id).show();
        } else {
            var url = '<s:url value="/pages/images/edit-flat-new.png"/>';
            var qty = $('#qty' + id).text();
            var approve = $('#qtyApprove' + id).val();

            if (approve != '') {
                if (parseInt(approve) <= parseInt(qty)) {
                    $('#tombol' + id).attr('src', url);
                    $('#qtyDefault' + id).html($('#qtyApprove' + id).val()).show();
                    $('#qtyApprove' + id).hide();
                } else {
                    $('#warning_po').show().fadeOut(5000);
                    $('#msg_po').text("Qty Approve tidak boleh melebihi qty permintaan...!");
                }
            } else {
                $('#warning_po').show().fadeOut(5000);
                $('#msg_po').text("Silahkan cek kembali data inputan...!");
            }
        }
    }

    function confirm() {
        var data = $('#tabel_po').tableToJSON();
        var stringData = JSON.stringify(data);
        console.log(data);
        var vendor = $('#nama_vendor').val();
        if (stringData != '[]' && vendor != '') {
            $('#confirm_dialog').dialog('open');
        } else {
            $('#warning_po').show().fadeOut(5000);
            $('#msg_po').text('Silahkan cek kembali data inputan...!');
        }
    }

    function resetField() {
        $('#box, #lembar_box, #lembar, #biji_lembar, #biji, #harga').val('');
    }

    function addToListPo() {

        var vendor = $('#nama_vendor').val();
        var obat = $('#nama_obat').val();
        var box = $('#box').val();
        var lembarBox = $('#lembar_box').val();
        var lembar = $('#lembar').val();
        var bijiLembar = $('#biji_lembar').val();
        var biji = $('#biji').val();
        var harga = $('#harga').val();
        var data = $('#tabel_po').tableToJSON();

        var idObat = "";
        var namaObat = "";
        var qtyObat = "";

        var cek = false;

        if (obat != '' && vendor != '') {
            idObat = obat.split('|')[0];
            namaObat = obat.split('|')[1];
            qtyObat = obat.split('|')[2];

//            if (parseInt(qty) <= parseInt(stok)) {
            $.each(data, function (i, item) {
                if (item.ID == idObat) {
                    cek = true;
                }
            });

            if (cek) {
                $('#warning_po').show().fadeOut(5000);
                $('#msg_po').text('Data sudah tersedia dalam list...!');
            } else {
                var row = '<tr id=' + idObat + '>' +
                        '<td>' + idObat + '</td>' +
                        '<td>' + namaObat + '</td>' +
                        '<td>' + box + '</td>' +
                        '<td>' + lembarBox + '</td>' +
                        '<td>' + lembar + '</td>' +
                        '<td>' + bijiLembar + '</td>' +
                        '<td>' + biji + '</td>' +
                        '<td align="right">' + harga + '</td>' +
                        '<td align="center"><img border="0" onclick="delRowObat(\'' + idObat + '\')" class="hvr-grow" src="<s:url value="/pages/images/delete-flat.png"/>" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                        '</tr>';

                $('#body_po').append(row);
                $('#nama_vendor').attr('disabled', true);
            }
//            } else {
//                $('#warning_request').show().fadeOut(5000);
//                $('#msg_request').text('Jumlah Request tidak boleh melebihi stok obat...!');
//            }
        } else {
            if (obat == '') {
//                $('#war_req_obat').show();
            }
            if (vendor == '') {
//                $('#war_req_qty').show();
            }
            $('#warning_po').show().fadeOut(5000);
            $('#msg_po').text('Silahkan cek kembali data inputan...!');
        }
    }

    function delRowObat(id) {
        $('#row' + id).remove();
    }

    function saveObat(id){

        var nama        = $('#add_nama_obat').val();
        var jenis       = $('#add_jenis_obat').val();
        var merek       = $('#add_merek').val();
        var pabrik      = $('#add_pabrik').val();
        var box         = $('#add_box').val();
        var lembarBox   = $('#add_lembar_box').val();
        var lembar      = $('#add_lembar').val();
        var bijiLembar  = $('#add_biji_lembar').val();
        var biji        = $('#add_biji').val();
        var harga       = $('#add_harga').val();
        var flag        = $('#add_flag').val();

        if (nama != '' && jenis != null && harga != '' && parseInt(biji) > 0 && box != ''
                && lembarBox != '' && lembar != '' && bijiLembar != '' && biji != '' && pabrik != ''
                && merek != '' && pabrik != '') {

            $('#save_obat').hide();
            $('#load_obat').show();

            if (id != '') {
                dwr.engine.setAsync(true);
                ObatAction.editObat(id, nama, jenis, harga, biji, flag, function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#modal-obat').modal('hide');
//                        $('#info_dialog').dialog('open');

                        var row = '<tr id=' + idObat + '>' +
                                '<td>' + idObat + '</td>' +
                                '<td>' + namaObat + '</td>' +
                                '<td>' + box + '</td>' +
                                '<td>' + lembarBox + '</td>' +
                                '<td>' + lembar + '</td>' +
                                '<td>' + bijiLembar + '</td>' +
                                '<td>' + biji + '</td>' +
                                '<td align="right">' + harga + '</td>' +
                                '<td align="center"><img border="0" onclick="delRowObat(\'' + idObat + '\')" class="hvr-grow" src="<s:url value="/pages/images/delete-flat.png"/>" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                                '</tr>';

                        $('#body_po').append(row);

                    } else {
                        $('#save_obat').show();
                        $('#load_obat').hide();
                        $('#warning_obat').show().fadeOut(5000);
                        $('#obat_error').text("Terjadi kesalahan ketika proses simpan ke database..!");
                    }
                })
            } else {
                dwr.engine.setAsync(true);
                ObatAction.saveObat(nama, jenis, merek, pabrik, box, lembarBox, lembar, bijiLembar, biji, harga, function (response) {
                    if (response == "success") {
                        dwr.engine.setAsync(false);
                        $('#modal-obat').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#close_pos').val(1);
                    } else {
                        $('#save_obat').show();
                        $('#load_obat').hide();
                        $('#warning_obat').show().fadeOut(5000);
                        $('#obat_error').text("Terjadi kesalahan ketika proses simpan ke database..!");
                    }
                })
            }
        } else {
            $('#warning_obat').show().fadeOut(5000);
            $('#obat_error').text("Silahkan cek kembali data inputan..!");

            if (nama == '') {
                $('#war_nama').show();
            }
            if (jenis == '' || jenis == null) {
                $('#war_jenis').show();
            }
            if (harga == '') {
                $('#war_harga').show();
            }
            if (merek == '') {
                $('#war_merek').show();
            }
            if (box == '') {
                $('#add_box').css('border','red solid 1px');
            }
            if (lembarBox == '') {
                $('#add_lembar_box').css('border','red solid 1px');
            }
            if (lembar == '') {
                $('#add_lembar').css('border','red solid 1px');
            }
            if (bijiLembar == '') {
                $('#add_biji_lembar').css('border','red solid 1px');
            }
            if (biji == '') {
                $('#war_biji').show();
            }
            if (pabrik == '') {
                $('#war_pabrik').show();
            }
        }
    }





    function savePermintaanPO() {
        $('#confirm_dialog').dialog('close');
        var data = $('#tabel_po').tableToJSON();
        var stringData = JSON.stringify(data);
        var vendor = $('#nama_vendor').val();
        $('#waiting_dialog').dialog('open');
        dwr.engine.setAsync(true);
        PermintaanVendorAction.savePermintaanPO(vendor, stringData, {
            callback: function (response) {
                if (response == "success") {
                    dwr.engine.setAsync(false);
                    $('#waiting_dialog').dialog('close');
                    $('#info_dialog').dialog('open');
                } else {
                    $('#warning_po').show().fadeOut(5000);
                    $('#msg_po').text('Terjadi kesalahan saat penyimpanan data...!');
                }
            }
        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>