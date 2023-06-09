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
            <span id="label-head">
                Verifikasi Permintaan Reture
            </span>
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
                                <td><b>No. Batch</b></td>
                                <td>
                                    <table><s:label name="permintaanVendor.noBatch"></s:label></table>
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
                                                                                         toContent()
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
                                <td align="center">Qty Request</td>
                                <td align="center">Qty Approve</td>
                                <td align="center">Harga (Rp.)</td>
                                <td width="21%" align="center">Scan ID Pabrikan</td>
                                <td>Jenis Satuan</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfObatDetail" var="row">
                                <tr id='row<s:property value="idObat"/>'>
                                    <td><s:property value="idObat"/></td>
                                    <td>
                                        <s:property value="namaObat"/>
                                        <s:if test='#row.namaObatBaru != null && #row.namaObatBaru != ""'>
                                            <small style="margin-left: 2px"><label class="label label-success">new</label></small>
                                        </s:if>
                                    </td>
                                    <td align="center"><span id='qty<s:property value="idObat"/>'><s:property
                                            value="qty"/></span></td>
                                    <td align="center"><span id='qtyApprove<s:property value="idObat"/>'><s:property
                                            value="sumQtyApprove"/></span></td>
                                    <td align="right">
                                        <script> document.write(formatRupiah('<s:property value="hargaPo"/>'));</script>
                                    </td>
                                    <td>
                                        <%--<s:if test='#row.idPabrik != null && #row.idPabrik != "" '>--%>
                                        <%--<span id='idPabrik<s:property value="idObat"/>'><s:property--%>
                                                <%--value="idPabrik"/></span>--%>
                                    <%--</s:if>--%>
                                        <%--<s:else>--%>
                                            <s:if test='#row.isFullOfQty == "Y"'>

                                                <input onchange="verify('<s:property value="idObat"/>', this.value,'<s:property value="qty"/>', '<s:property value="idTransaksiObatDetail"/>','<s:property value="namaObat"/>', '<s:property value="jenisSatuan"/>', '<s:property value="hargaPo"/>', '<s:property value="idApprovalObat"/>', '<s:property value="lembarPerBox"/>', '<s:property value="bijiPerLembar"/>', '<s:property value="noBatch"/>')" class="form-control"
                                                       disabled id='pabrik<s:property value="idObat"/>'>
                                            </s:if>
                                            <s:else>
                                            <div class="input-group">
                                                <input oninput="cekObat(this.value, '<s:property value="idObat"/>', '<s:property value="qty"/>', '<s:property value="idTransaksiObatDetail"/>','<s:property value="namaObat"/>', '<s:property value="jenisSatuan"/>', '<s:property value="hargaPo"/>', '<s:property value="idApprovalObat"/>', '<s:property value="lembarPerBox"/>', '<s:property value="bijiPerLembar"/>', '<s:property value="noBatch"/>', '<s:property value="sumQtyApprove"/>', '<s:property value="diskon"/>', '<s:property value="bruto"/>', '<s:property value="netto"/>')" class="form-control"
                                                       id='pabrik<s:property value="idObat"/>'>
                                                    <%--<input id="cek_obat_<s:property value="idObat"/>" class="form-control" oninput="cekObat(this.value, '<s:property value="idObat"/>')">--%>
                                                    <div class="input-group-addon">
                            <span id='status<s:property value="idObat"/>'></span>
                        </div>
                    </div>
                                            </s:else>
                                        <%--</s:else>--%>
                                    </td>
                                    <td><s:property value="jenisSatuan"/></td>
                                    <%--<td align="center">--%>
                                        <%--<s:if test='#row.flagDiterima == "Y"'>--%>
                                            <%--<span class="label label-success">Sesuai</span>--%>
                                        <%--</s:if>--%>
                                        <%--<s:if test='#row.flagDiterima == "N"'>--%>
                                            <%--<span class="label label-danger">Tidak Sesuai</span>--%>
                                        <%--</s:if>--%>
                                        <%--<span id='status<s:property value="idObat"/>'></span></td>--%>
                                    <%--<td align="center">--%>
                                        <%--<s:if test='#row.flagDiterima == "Y"'>--%>
                                            <%--<span class="label label-success">Setuju</span>--%>
                                        <%--</s:if>--%>
                                        <%--<s:if test='#row.flagDiterima == "N"'>--%>
                                            <%--<span class="label label-warning">Dibuatkan obat baru</span>--%>
                                        <%--</s:if>--%>
                                        <%--<s:if test='#row.flagDiterima == "X"'>--%>
                                            <%--<span class="label label-danger">Tidak Setuju</span>--%>
                                        <%--</s:if>--%>
                                    <%--</td>--%>
                                        <%--<td align="center"><span id='qtyDefault<s:property value="idObat"/>'></span><input--%>
                                        <%--value='<s:property value="qty"/>' type="number" class="form-control"--%>
                                        <%--style="width: 150px; display: none"--%>
                                        <%--id='qtyApprove<s:property value="idObat"/>'></td>--%>
                                        <%--<td align="center"><img border="0" id='tombol<s:property value="idObat"/>'--%>
                                        <%--class="hvr-grow"--%>
                                        <%--src="<s:url value="/pages/images/edit-flat-new.png"/>"--%>
                                        <%--style="cursor: pointer; height: 25px; width: 25px; display: none">--%>
                                        <%--<img border="0" onclick="delRowObat('<s:property value="idObat"/>')"--%>
                                        <%--id='hapus<s:property value="idObat"/>' class="hvr-grow"--%>
                                        <%--src="<s:url value="/pages/images/cnacel-flat.png"/>"--%>
                                        <%--style="cursor: pointer; height: 25px; width: 25px; display: none"></td>--%>
                                    <%--<td>--%>
                                        <%--<s:property value="noBatch"/>--%>
                                    <%--</td>--%>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                    <%--<div style="display: none" id="new_obat">--%>
                        <%--<div class="box-header with-border"></div>--%>
                        <%--<div class="box-header with-border">--%>
                            <%--<h3 class="box-title"><i class="fa fa-file-text-o"></i> Daftar Obat dengan ID pabrik baru--%>
                            <%--</h3>--%>
                        <%--</div>--%>
                        <%--<div class="box-body">--%>
                            <%--<div class="alert alert-danger alert-dismissible" style="display: none" id="warning_new_po">--%>
                                <%--<h4><i class="icon fa fa-ban"></i> Warning!</h4>--%>
                                <%--<p id="msg_new_po"></p>--%>
                            <%--</div>--%>
                            <%--<table class="table table-bordered table-striped" id="tabel_new_po">--%>
                                <%--<thead>--%>
                                <%--<tr bgcolor="#90ee90">--%>
                                    <%--<td>Nama Obat</td>--%>
                                    <%--<td align="center">Qty Request</td>--%>
                                    <%--<td align="center">Qty Approve</td>--%>
                                    <%--<td align="center">Satuan Jenis</td>--%>
                                    <%--<td align="center">Lembar/Box</td>--%>
                                    <%--<td align="center">Biji/Lembar</td>--%>
                                    <%--<td align="center">Action</td>--%>
                                <%--</tr>--%>
                                <%--</thead>--%>
                                <%--<tbody id="body_new_pabrik">--%>
                                <%--</tbody>--%>
                            <%--</table>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="box-header with-border"></div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:if test='tipe == "reture"'>
                                <s:url var="selesai" namespace="/permintaanpo" action="edit_permintaanpo" escapeAmp="false">
                                    <s:param name="id"><s:property value="id"/></s:param>
                                    <s:param name="tipe"><s:property value="tipe"/></s:param>
                                </s:url>
                            </s:if>
                            <s:else>
                                <s:url var="selesai" namespace="/permintaanpo" action="edit_permintaanpo" escapeAmp="false">
                                    <s:param name="id"><s:property value="id"/></s:param>
                                </s:url>
                            </s:else>
                            <s:a href="%{selesai}">
                                <button class="btn btn-success"><i class="fa fa-check"></i> Selesai</button>
                            </s:a>
                        </div>
                    </div>
                    <br>
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
                        <label class="col-md-3" style="margin-top: 7px">Lembar/Box</label>
                        <div class="col-md-7">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_lembar_box"
                                         onkeypress="var warn =$('#war_lembar_box').is(':visible'); if (warn){$('#cor_lembar_box').show().fadeOut(3000);$('#war_lembar_box').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_lembar_box"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_lembar_box"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Biji/Lembar</label>
                        <div class="col-md-7">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="add_biji_lembar"
                                         onkeypress="var warn =$('#war_biji_lembar').is(':visible'); if (warn){$('#cor_biji_lembar').show().fadeOut(3000);$('#war_biji_lembar').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_biji_lembar"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_biji_lembar"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Qty Request</label>
                        <div class="col-md-7">
                            <s:textfield type="number" min="1" cssClass="form-control" disabled="true"
                                         cssStyle="margin-top: 7px" id="add_qty_request"
                                         onkeypress="var warn =$('#war_qty_request').is(':visible'); if (warn){$('#cor_qty_request').show().fadeOut(3000);$('#war_qty_request').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_qty_request"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_qty_request"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px">Qty Approve</label>--%>
                        <%--<div class="col-md-7">--%>
                            <%--<s:textfield type="number" min="1" cssClass="form-control"--%>
                                         <%--cssStyle="margin-top: 7px" id="add_qty_approve"--%>
                                         <%--onkeypress="var warn =$('#war_qty_approve').is(':visible'); if (warn){$('#cor_qty_approve').show().fadeOut(3000);$('#war_qty_approve').hide()}"></s:textfield>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                            <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="war_qty_approve"><i class="fa fa-times"></i> required</p>--%>
                            <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="cor_qty_approve"><i class="fa fa-check"></i> correct</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jenis Satuan</label>
                        <div class="col-md-7">
                            <s:select list="#{'box':'Box','bembar':'Lembar','biji':'Biji'}"
                                      cssStyle="margin-top: 7px; width: 100%"
                                      onchange="var warn =$('#war_jenis_satuan').is(':visible'); if (warn){$('#cor_jenis_satuan').show().fadeOut(3000);$('#war_jenis_satuan').hide()}"
                                      id="add_jenis_satuan"
                                      headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2" disabled="true"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_jenis_satuan"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_jenis_satuan"><i class="fa fa-check"></i> correct</p>
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Konfirmasi Qty Approve
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none;" id="warning_app">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_app"></p>
                </div>
                <div class="alert alert-success alert-dismissible" id="warning_approve">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    Data Obat berhasil di verifikasi...!
                </div>
                <div id="warning_fisik"></div>
                <div class="row">
                    <input type="hidden" id="kon_lembar">
                    <input type="hidden" id="kon_biji">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jml Lembar/Box</label>
                        <div class="col-md-7">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="app_lembar_perbox"
                                         onchange="cekFisik()"
                                         onkeypress="var warn =$('#war_app_lembar_perbox').is(':visible'); if (warn){$('#cor_app_lembar_perbox').show().fadeOut(3000);$('#war_app_lembar_perbox').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_app_lembar_perbox"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_app_lembar_perbox"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Jml Biji/lembar</label>
                        <div class="col-md-7">
                            <s:textfield type="number" min="1" cssClass="form-control"
                                         cssStyle="margin-top: 7px" id="app_biji_perlembar"
                                         onchange="cekFisik()"
                                         onkeypress="var warn =$('#war_app_biji_perlembar').is(':visible'); if (warn){$('#cor_app_biji_perlembar').show().fadeOut(3000);$('#war_app_biji_perlembar').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_app_biji_perlembar"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_app_biji_perlembar"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Qty Request</label>
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
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Expired Date</label>
                        <div class="col-md-7">
                            <s:textfield cssClass="form-control datepicker2 datemask2"
                                         cssStyle="margin-top: 7px" id="app_expired"
                                         onchange="var warn =$('#war_app_expired').is(':visible'); if (warn){$('#cor_app_expired').show().fadeOut(3000);$('#war_app_expired').hide()}"></s:textfield>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_app_expired"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_app_expired"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Diskon</label>
                        <div class="col-md-7">
                            <div class="input-group" style="margin-top: 7px">
                                <div class="input-group-addon">
                                    Rp.
                                </div>
                                <s:textfield cssClass="form-control"
                                             id="app_diskon"
                                             onkeypress="var warn =$('#war_app_diskon').is(':visible'); if (warn){$('#cor_app_diskon').show().fadeOut(3000);$('#war_app_diskon').hide()}"></s:textfield>
                                <input type="hidden" id="val_diskon">
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_app_diskon"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_app_diskon"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Bruto</label>
                        <div class="col-md-7">
                            <div class="input-group" style="margin-top: 7px">
                                <div class="input-group-addon">
                                    Rp.
                                </div>
                                <s:textfield cssClass="form-control"
                                             id="app_bruto"
                                             onkeypress="var warn =$('#war_app_bruto').is(':visible'); if (warn){$('#cor_app_bruto').show().fadeOut(3000);$('#war_app_bruto').hide()}"></s:textfield>
                                <input type="hidden" id="val_bruto">
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_app_bruto"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_app_bruto"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Netto</label>
                        <div class="col-md-7">
                            <div class="input-group" style="margin-top: 7px">
                                <div class="input-group-addon">
                                    Rp.
                                </div>
                                <s:textfield cssClass="form-control"
                                             id="app_netto" disabled="true"
                                             onkeypress="var warn =$('#war_app_netto').is(':visible'); if (warn){$('#cor_app_netto').show().fadeOut(3000);$('#war_app_netto').hide()}"></s:textfield>
                                <input type="hidden" id="val_netto">
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_app_netto"><i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_app_netto"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_approve"><i class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_approve">
                    <i
                            class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-confirm">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Konfirmasi ID Pabrik baru
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    Data Obat tidak ditemukan...!
                </div>
                <div class="alert alert-info alert-dismissible">
                    <h4><i class="icon fa fa-info"></i> Info!</h4>
                    <p>Tekan tombol <b>Tambah obat</b> untuk menambahkan obat tersebut.</p>
                    <%--<p>Tekan tombol <b>Cancel obat</b> untuk membatalkan obat tersebut.</p>--%>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_confirm"><i class="fa fa-plus"></i> Tambah
                    Obat
                </button>
                <%--<button type="button" class="btn btn-danger" id="cancel_confirm"><i class="fa fa-times"></i> Cancel Obat--%>
                <%--</button>--%>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-danger" id="load_confirm"><i
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

    function tes() {
        $('#modal-confirm-dialog').modal('show');
    }

    var idApprovalObat = $('#id_approval').val();

    $(document).ready(function () {

        console.log(idApprovalObat);

        var tipe = '<s:property value="tipe"/>';
        if(tipe == "reture"){
//            $("#label-head").text("Verifikasi Permintaan Reture");
            $('#retur_obat').addClass('active');
        }else{
            $('#permintaan_po').addClass('active');
        }

        listNewObat(idApprovalObat);

        var nominal1 = document.getElementById('app_diskon');
        if(nominal1 != ''){
            nominal1.addEventListener('keyup', function (e) {
                nominal1.value = formatRupiah2(this.value);
                var valBayar1 = nominal1.value.replace(/[.]/g, '');

                if(valBayar1 != ''){
                    $('#val_diskon').val(valBayar1);
                    var bruto = $('#val_bruto').val();
                    if(bruto != '' && parseInt(bruto) > parseInt(valBayar1)){
                        var hasil = parseInt(bruto) - parseInt(valBayar1);
                        $('#app_netto').val(formatRupiah(hasil));
                        $('#val_netto').val(hasil);
                    }else{
                        $('#app_netto').val('');
                        $('#val_netto').val('');
                    }
                }else{
                    $('#val_diskon').val('');
                }
            });
        }
        var nominal2 = document.getElementById('app_bruto');
        if(nominal2 != ''){
            nominal2.addEventListener('keyup', function (e) {
                nominal2.value = formatRupiah2(this.value);
                var valBayar2 = nominal2.value.replace(/[.]/g, '');

                if(valBayar2 != ''){
                    $('#val_bruto').val(valBayar2);
                    var diskon = $('#val_diskon').val();
                    if(diskon != '' && parseInt(valBayar2) > parseInt(diskon)){
                        var hasil = parseInt(valBayar2) - parseInt(diskon);
                        $('#app_netto').val(formatRupiah(hasil));
                        $('#val_netto').val(hasil);
                    }else{
                        $('#app_netto').val('');
                        $('#val_netto').val('');
                    }
                }else{
                    $('#val_bruto').val('');
                }
            });
        }
    });

    function cekObat(value, idObat, qty, idDetail, nama, jenis, harga, idApp, lembarPerBox, bijiPerlembar,noBt, sumQty, diskon, bruto, netto){
        var functions, mapped;
        $('#pabrik'+idObat).typeahead({
            minLength: 5,
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
                        var labelItem =  item.idObat+"-"+item.idPabrik +"-" + item.namaObat;
                        mapped[labelItem] = {
                            id: item.idObat,
                            nama: item.namaObat,
                            idPabrik: item.idPabrik
                        };
                        functions.push(labelItem);
                    });
                    process(functions);
                }else{
                    $('#cancel_confirm').show();
                    $('#load_confirm').hide();
                    $('#modal-confirm').modal({show:true, backdrop:'static'});
                    $('#save_confirm').attr('onclick', 'showModal(\'' +  idObat + '\',\'' + value + '\',\'' + idDetail + '\',\'' + nama + '\',\'' + qty + '\',\'' + jenis + '\',\'' + harga + '\',\'' + idApp + '\')');
                    $('#cancel_confirm').attr('onclick', 'saveNotApprove(\'' +  idObat + '\', \'' + idDetail + '\')');
                }
            },
            updater: function (item) {
                var selectedObj = mapped[item];
                if(selectedObj.id == idObat){
                    var jml = "";
                    if(sumQty != null && sumQty != ''){
                        jml = parseInt(qty) - parseInt(sumQty);
                    }

                    $('#app_expired').val('');
                    $('#app_lembar_perbox, #kon_lembar').val(lembarPerBox);
                    $('#app_biji_perlembar, #kon_biji').val(bijiPerlembar);
                    $('#app_qty').val(qty);
                    $('#app_qty_app').val(jml);
                    if(diskon != null && diskon != ''
                        && bruto != null && bruto != ''
                        && netto != null && netto != ''){
                        $('#val_diskon').val(diskon);
                        $('#app_diskon').val(formatRupiah(diskon));
                        $('#val_bruto').val(bruto);
                        $('#app_bruto').val(formatRupiah(bruto));
                        $('#val_netto').val(netto);
                        $('#app_netto').val(formatRupiah(netto));
                    }else{
                        $('#val_diskon').val('');
                        $('#app_diskon').val('');
                        $('#val_bruto').val('');
                        $('#app_bruto').val('');
                        $('#val_netto').val('');
                        $('#app_netto').val('');
                    }
                    $('#save_approve').attr('onclick', 'confirmSaveApprove(\'' + selectedObj.id + '\', \'' + idDetail + '\', \'' + selectedObj.idPabrik + '\', \'' + noBt + '\')').show();
                    $('#save_approve').show();
                    $('#load_approve').hide();
                    $('#modal-approve').modal({show:true, backdrop:'static'});
                }else{
                    $('#cancel_confirm').show();
                    $('#load_confirm').hide();
                    $('#modal-confirm').modal({show:true, backdrop:'static'});
                    $('#save_confirm').attr('onclick', 'showModal(\'' +  selectedObj.id + '\',\'' + value + '\',\'' + idDetail + '\',\'' + nama + '\',\'' + qty + '\',\'' + jenis + '\',\'' + harga + '\',\'' + idApp + '\')');
                    $('#cancel_confirm').attr('onclick', 'saveNotApprove(\'' +  selectedObj.id + '\', \'' + idDetail + '\')');
                }
                return selectedObj.id;
            }
        });
    }

    function toContent(){
        var ref = $('#close_pos').val();
        if(ref == 1){
            window.location.reload(true);
        }
    }

    function formatRupiah2(angka) {
        console.log(angka);
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

    function showModal(idObat, pabrik, idDetail, namaObat, qty, satuan, harga, idApp) {

        $('#add_merek, #add_lembar_box, #add_biji_lembar').val('');
        $('#add_jenis_obat').val('').trigger('change');
        $('#add_pabrik').val('');
        $('#add_nama_obat').val('');
        $('#add_jenis_satuan').val(satuan).trigger('change');
        var id = "";
        $('#load_obat, #war_nama, #war_jenis, #war_harga, #war_pabrik, #war_merek').hide();
        $('#add_box, #add_lembar_box, #add_lembar, #add_biji_lembar').css('border', '');
        $('#add_qty_request, #add_qty_approve').val(qty);
        $('#add_harga').val(harga);
        $('#save_obat').attr('onclick', 'saveObat(\'' + id + '\',\'' + idDetail + '\',\'' + qty + '\',\'' + satuan + '\',\'' + idObat + '\',\'' + idApp + '\')').show();
        $('#modal-obat').modal({show:true, backdrop:'static'});
        $('#modal-confirm').modal('hide');
    }

    function verify(id, value, qty, idDetail, nama, jenis, harga, idApp, lembarPerBox, bijiPerlembar,noBt, sumQty) {
        var status = false;
        $('#warning_fisik').html('');
        if (id != '' && value != '') {
            $('#status' + id).html('<i style="color: #00a65a" class="fa fa-circle-o-notch fa-spin"></i>');
            dwr.engine.setAsync(true);
            PermintaanVendorAction.checkFisikObat(id, value, lembarPerBox, bijiPerlembar, {
                callback: function (response) {
                    if (response.status == "success") {
                        dwr.engine.setAsync(false);
                        $('#app_expired').val('');
                        $('#app_lembar_perbox, #kon_lembar').val(lembarPerBox);
                        $('#app_biji_perlembar, #kon_biji').val(bijiPerlembar);
                        $('#app_qty').val(qty);
                        $('#app_qty_app').val(sumQty);
                        $('#save_approve').attr('onclick', 'confirmSaveApprove(\'' + id + '\', \'' + idDetail + '\', \'' + value + '\', \'' + noBt + '\')').show();
                        $('#save_approve').show();
                        $('#load_approve').hide();
                        $('#modal-approve').modal({show:true, backdrop:'static'});
                    } else if (response.status == "new") {
                        $('#cancel_confirm').show();
                        $('#load_confirm').hide();
                        $('#modal-confirm').modal({show:true, backdrop:'static'});
                        $('#save_confirm').attr('onclick', 'showModal(\'' + id + '\',\'' + value + '\',\'' + idDetail + '\',\'' + nama + '\',\'' + qty + '\',\'' + jenis + '\',\'' + harga + '\',\'' + idApp + '\')');
                        $('#cancel_confirm').attr('onclick', 'saveNotApprove(\'' + id + '\', \'' + idDetail + '\')');
                    } else if(response.status == "warning"){
                        var warn =  '<div class="alert alert-warning alert-dismissible">'+
                                    '<h4><i class="icon fa fa-ban"></i> Warning!</h4>'+
                                        response.message +
                                    '</div>';
                        <%--$('#status' + id).html('<img src="<s:url value="/pages/images/icon_success.ico"/>" style="height: 20px; width: 20px;">');--%>
                        $('#app_expired').val('');
                        $('#app_lembar_perbox, #kon_lembar').val(lembarPerBox);
                        $('#app_biji_perlembar, #kon_biji').val(bijiPerlembar);
                        $('#app_qty').val(qty);
                        $('#app_qty_app').val(qty);
                        $('#save_approve').attr('onclick', 'confirmSaveApprove(\'' + id + '\', \'' + idDetail + '\', \'' + value + '\', \'' + noBt + '\')').show();
                        $('#save_approve').show();
                        $('#warning_fisik').html(warn);
                        $('#load_approve').hide();
                        $('#modal-approve').modal({show:true, backdrop:'static'});
                    }
                }
            });
        }else{
            $('#status' + id).html('');
        }
    }

    function confirmSaveApprove(id, idDetail, idPabrik, noBt) {

        var qtyReq = $('#app_qty').val();
        var qty = $('#app_qty_app').val();
        var lembarPerBox = $('#app_lembar_perbox').val();
        var bijiPerLembar = $('#app_biji_perlembar').val();
        var expiredValue = $('#app_expired').val();
        var diskon = $('#app_diskon').val();
        var bruto = $('#app_bruto').val();
        var expired = expiredValue.split("-").reverse().join("-");

        var qtyApproveValue = $('#qtyApprove'+id).text();
        var qtyApprove = 0;
        var totalQtyApp= 0;
        var limitQty = qtyReq;

        if(qtyApproveValue != '') {
            qtyApprove = qtyApproveValue;
            limitQty = parseInt(qtyReq) - parseInt(qtyApprove);

        }

        if(qty != '' && lembarPerBox != '' && bijiPerLembar != '' && expired != '' && diskon != '' && bruto != '' ){
            if(parseInt(qty) <= parseInt(limitQty)) {
                $('#modal-confirm-dialog').modal('show');
                $('#save_con').attr('onclick','saveApprove(\'' + id + '\',\'' + idDetail + '\',\'' + qty + '\',\'' + idPabrik + '\',\'' + lembarPerBox + '\',\'' + bijiPerLembar + '\',\'' + noBt + '\',\'' + expired + '\',\'' + totalQtyApp + '\',\'' + qtyReq + '\',\'' + qtyApprove + '\')');
            }else{
                $('#warning_app').show().fadeOut(5000);
                $('#msg_app').text('Qty Approve tidak boleh melebihi qty request');
            }
        }else{
            $('#warning_app').show().fadeOut(5000);
            $('#msg_app').text('Silahkan cek kembali data inputan..!');
            if(expired == ''){
                $('#war_app_expired').show();
            }
            if(qty == ''){
                $('#war_app_qty_app').show();
            }
            if(lembarPerBox == ''){
                $('#war_app_lembar_perbox').show();
            }
            if(bijiPerLembar == ''){
                $('#war_app_biji_perlembar').show();
            }
            if(diskon == ''){
                $('#war_app_diskon').show();
            }
            if(bruto == ''){
                $('#war_app_bruto').show();
            }
        }
    }

    function saveApprove(id, idDetail, qty, idPabrik, lembarPerBox, bijiPerLembar, noBt, expired, totalQtyApp, qtyReq, qtyApprove){
        $('#modal-confirm-dialog').modal('hide');
        var diskon = $('#val_diskon').val();
        var bruto = $('#val_bruto').val();
        var netto = $('#val_netto').val();
        if(diskon == ''){
            diskon = 0;
        }
        if(bruto == ''){
            bruto = 0;
        }
        if(netto == ''){
            netto = 0;
        }
        $('#save_approve').hide();
        $('#load_approve').show();
        dwr.engine.setAsync(true);
        PermintaanVendorAction.saveUpdateListObat(idDetail, qty, idPabrik, "Y", lembarPerBox, bijiPerLembar, noBt, expired, diskon, bruto, netto, function (response) {
            if (response.status == "success") {
                dwr.engine.setAsync(false);
                $('#modal-approve').modal('hide');
                $('#status' + id).html('<img src="<s:url value="/pages/images/icon_success.ico"/>" style="height: 20px; width: 20px;">');
//                $('#approve' + id).html("Setuju").addClass("label label-success");

                totalQtyApp = parseInt(qtyApprove) + parseInt(qty);
                if(parseInt(totalQtyApp) == parseInt(qtyReq)){
                    $('#pabrik' + id).attr('readonly', true).blur();
                }else{
                    $('#pabrik' + id).val('');
                }
                $('#qtyApprove' + id).text(totalQtyApp);
                $('#info_dialog').dialog('open');
                $('body').scrollTop(0);
            } else {
                $('#save_obat').show();
                $('#load_obat').hide();
                $('#warning_obat').show().fadeOut(5000);
                $('#obat_error').text(response.message);
            }
        })
    }

    function saveNotApprove(id, idDetail, idPabrik) {
        $('#cancel_confirm').hide();
        $('#load_confirm').show();
        dwr.engine.setAsync(true);
        PermintaanVendorAction.saveUpdateListObat(idDetail, 0, idPabrik, "X", function (response) {
            if (response == "success") {
                $('#cancel_confirm').show();
                $('#load_confirm').hide();
                dwr.engine.setAsync(true);
                dwr.engine.setAsync(false);
                $('#modal-confirm').modal('hide');
                $('#status' + id).html('<img src="<s:url value="/pages/images/icon_failure.ico"/>" style="height: 20px; width: 20px;">');
//                $('#approve' + id).html("Dibatalkan").addClass("label label-warning");
            }
        })
    }

    function saveObat(id, idDetail, qty, satuan, idObat, idApp) {

        var nama = $('#add_nama_obat').val();
        var jenis = $('#add_jenis_obat').val();
        var merek = $('#add_merek').val();
        var pabrik = $('#add_pabrik').val();
        var lembarBox = $('#add_lembar_box').val();
        var bijiLembar = $('#add_biji_lembar').val();
        var qtyApp = $('#add_qty_approve').val();
        var harga = $('#add_harga').val();
        var flag = $('#add_flag').val();
        var jenisSatuan = $('#add_jenis_satuan').val();

        if (nama != '' && jenis != null && harga != '' && lembarBox != '' && bijiLembar != '' && merek != '' && pabrik != '' && qtyApp != '') {

            $('#save_obat').hide();
            $('#load_obat').show();

            dwr.engine.setAsync(true);
            PermintaanVendorAction.saveNewPabrik(idDetail, nama, jenis, merek, pabrik, lembarBox, bijiLembar, harga, qty, qtyApp, satuan, idApp, function (response) {
                if (response.status == "success") {
                    dwr.engine.setAsync(false);
                    $('#modal-obat').modal('hide');
                    $('#info_dialog').dialog('open');
                    $('#close_pos').val(1);
                    $('body').scrollTop(0);
                    // listNewObat(idApp);
                    // $('#approve' + idObat).html("Dibuatkan obat baru").addClass("label label-warning");
                } else {
                    $('#save_obat').show();
                    $('#load_obat').hide();
                    $('#warning_obat').show().fadeOut(5000);
                    $('#obat_error').text(response.message);
                }
            });

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
            if (lembarBox == '') {
                $('#war_lembar_box').show();
            }
            if (bijiLembar == '') {
                $('#war_biji_lembar').show();
            }
            if (jenisSatuan == '' || jenisSatuan == null) {
                $('#war_jenis').show();
            }
            if (pabrik == '') {
                $('#war_pabrik').show();
            }
            if (qtyApp == '') {
                $('#war_qty_approve').show();
            }
        }
    }

    function listNewObat(idApproval) {
        var table = "";
        PermintaanVendorAction.searchNewListObat(idApproval, function (response) {
            if (response != null) {
                $('#new_obat').show();
                $.each(response, function (i, item) {
                    table += "<tr>" +
                            "<td>" + item.namaObat + "</td>" +
                            "<td align='center'>" + item.qty + "</td>" +
                            "<td align='center'>" + item.qtyApprove + "</td>" +
                            "<td align='center'>" + item.jenisSatuan + "</td>" +
                            "<td align='center'>" + item.lembarPerBox + "</td>" +
                            "<td align='center'>" + item.bijiPerLembar + "</td>" +
                            "<td align='center'>" + '<img border="0" class="hvr-grow" onclick="editDokter(\'' + item.idTeamDokter + '\',\'' + item.idDokter + '\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
                            "</tr>";
                });
                $('#body_new_pabrik').html(table);
            }
        });
    }

    function cekFisik(){
        var kode = "";
        var nama = "";
        var merek = "";
        var konLembar = $('#kon_lembar').val();
        var konBiji = $('#kon_biji').val();
        var lembarPerBox = $('#app_lembar_perbox').val();
        var bijiPerLembar = $('#app_biji_perlembar').val();
        var lembarTo = "";
        var bijiTo ="";

        if(konLembar != lembarPerBox && konBiji != bijiPerLembar){
            lembarTo = "Menjadi "+lembarPerBox;
            bijiTo = "Menjadi "+bijiPerLembar;
        }else{
            if(konLembar != lembarPerBox){
                lembarTo = "Menjadi "+lembarPerBox;
            }
            if(konBiji != bijiPerLembar){
                bijiTo = "Menjadi "+bijiPerLembar;
            }
        }

        var warn = '<div class="alert alert-warning alert-dismissible">'+
                '<h4><i class="icon fa fa-ban"></i> Warning!</h4>'+
                'Obat teridentifikasi berubah fisik..!'+'<br>'+
                'Kode Produksi &nbsp;&nbsp;'+': '+kode+'<br>'+
                'Nama Obat &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+': '+nama+'<br>'+
                'Mrek   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+': '+merek+'<br>'+
                'Lembar/Box   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+': '+konLembar+' '+lembarTo+'<br>'+
                'Biji/Lembar  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+': '+konBiji+' '+bijiTo+'<br>'+
                '</div>';

        if(konBiji == bijiPerLembar && konLembar == lembarPerBox){
            $('#warning_fisik').html('');
        }else{
            $('#warning_fisik').html(warn);
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>