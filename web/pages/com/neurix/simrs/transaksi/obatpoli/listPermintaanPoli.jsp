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
                                <%--<div class="form-group" style="display: none">--%>
                                    <%--<sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"--%>
                                               <%--resizable="false"--%>
                                               <%--closeOnEscape="false"--%>
                                               <%--height="200" width="400" autoOpen="false" title="Infomation Dialog"--%>
                                               <%--buttons="{--%>
                                                                                <%--'OK':function() {--%>
                                                                                         <%--$('#info_dialog').dialog('close');--%>
                                                                                         <%--toContent();--%>
                                                                                     <%--}--%>
                                                                            <%--}"--%>
                                    <%-->--%>
                                        <%--<s:hidden id="close_pos"></s:hidden>--%>
                                        <%--<img border="0" src="<s:url value="/pages/images/icon_success.png"/>"--%>
                                             <%--name="icon_success">--%>
                                        <%--Record has been saved successfully.--%>
                                    <%--</sj:dialog>--%>

                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-5"></label>--%>
                                    <%--<div class="col-sm-5" style="display: none">--%>

                                        <%--<sj:dialog id="waiting_dialog" openTopics="showDialogLoading"--%>
                                                   <%--closeTopics="closeDialog" modal="true"--%>
                                                   <%--resizable="false"--%>
                                                   <%--height="250" width="600" autoOpen="false"--%>
                                                   <%--title="Searching ...">--%>
                                            <%--Please don't close this window, server is processing your request ...--%>
                                            <%--<br>--%>
                                            <%--<center>--%>
                                                <%--<img border="0" style="width: 150px; height: 150px"--%>
                                                     <%--src="<s:url value="/pages/images/spinner.gif"/>"--%>
                                                     <%--name="image_indicator_write">--%>
                                            <%--</center>--%>
                                        <%--</sj:dialog>--%>
                                        <%--<sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true"--%>
                                                   <%--resizable="false" cssStyle="text-align:left;"--%>
                                                   <%--height="650" width="900" autoOpen="false" title="View Detail"--%>
                                        <%-->--%>
                                            <%--<center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>"--%>
                                                         <%--alt="Loading..."/></center>--%>
                                        <%--</sj:dialog>--%>
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
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Obat</td>
                                <td>Nama Obat</td>
                                <td>Qty Request</td>
                                <td>Qty Approve</td>
                                <td>Jenis Satuan</td>
                                <td align="center">Action</td>
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
                                    <td align="center">
                                        <%--<img border="0" class="hvr-grow" onclick="showRequestReture(1,'<s:property value="idObat"/>','<s:property value="namaObat"/>','<s:property value="qty"/>')"--%>
                                                 <%--src="<s:url value="/pages/images/request.png"/>"--%>
                                                 <%--style="cursor: pointer; height: 25px; width: 25px;">--%>
                                        <%--<img border="0" class="hvr-grow" onclick="showRequestReture(2,'<s:property value="idObat"/>','<s:property value="namaObat"/>','<s:property value="qty"/>')"--%>
                                             <%--src="<s:url value="/pages/images/return.ico"/>"--%>
                                             <%--style="cursor: pointer; height: 25px; width: 25px;">--%>
                                        <a class="btn btn-success" id='btn<s:property value="idObat"/>' onclick="confirmApprove('<s:property value="idObat"/>','<s:property value="idTransaksiObatDetail"/>','<s:property value="namaObat"/>','<s:property value="qty"/>','<s:property value="jenisSatuan"/>')"><i class="fa fa-check"></i></a>
                                        <img id='load<s:property value="idObat"/>' src="<s:url value="/pages/images/spinner.gif"/>" style="height: 35px; width: 35px; display: none">
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

<div class="modal fade" id="modal-approve">
    <div class="modal-dialog modal-flat" style="width: 55%">
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
                        <table class="table table-striped table-bordered" id="tabel_approve">
                            <thead>
                            <td>Expired Date</td>
                            <td align="center">Qty Box</td>
                            <td align="center">Qty Lembar</td>
                            <td align="center">Qty Biji</td>
                            <td align="center">Qty Approve</td>
                            <td>Jenis Satuan</td>
                            <td>Action</td>
                            </thead>
                            <tbody id="body_approve">
                            </tbody>
                        </table>
                        <p id="loading_data" style="color: #00a65a; display: none"><img src="<s:url value="/pages/images/spinner.gif"/>" style="height: 40px; width: 40px;"> Sedang mengambil data...</p>
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

<%--<div class="modal fade" id="modal-add-request">--%>
    <%--<div class="modal-dialog modal-flat">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header" style="background-color: #00a65a">--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
                    <%--<span aria-hidden="true">&times;</span></button>--%>
                <%--<h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambah & Request Obat--%>
                <%--</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<div class="alert alert-danger alert-dismissible" style="display: none" id="warning_request">--%>
                    <%--<h4><i class="icon fa fa-ban"></i> Warning!</h4>--%>
                    <%--<p id="error_request"></p>--%>
                <%--</div>--%>
                <%--<div class="row">--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px">Jenis Obat</label>--%>
                        <%--<div class="col-md-7">--%>
                            <%--<s:action id="initJenis" namespace="/jenisobat"--%>
                                      <%--name="getListJenisObat_jenisobat"/>--%>
                            <%--<s:select cssStyle="margin-top: 7px; width: 100%"--%>
                                      <%--list="#initJenis.listOfJenisObat" id="req_jenis_obat"--%>
                                      <%--onchange="var warn =$('#war_req_jenis_obat').is(':visible'); if (warn){$('#cor_req_jenis_obat').show().fadeOut(3000);$('#war_req_jenis_obat').hide()}; listSelectObat(this)"--%>
                                      <%--listKey="idJenisObat"--%>
                                      <%--listValue="namaJenisObat"--%>
                                      <%--headerKey="" headerValue="[Select one]"--%>
                                      <%--cssClass="form-control select2"/>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                            <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="war_req_jenis_obat"><i class="fa fa-times"></i> required</p>--%>
                            <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="cor_req_jenis_obat"><i class="fa fa-check"></i> correct</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px">Nama Obat</label>--%>
                        <%--<div class="col-md-7">--%>
                            <%--<select class="form-control select2" style="margin-top: 7px; width: 100%" id="req_id_obat"--%>
                                    <%--onchange="var warn =$('#war_req_obat').is(':visible'); if (warn){$('#cor_req_obat').show().fadeOut(3000);$('#war_req_obat').hide()}">--%>
                                <%--<option value="">[select one]</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                            <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="war_req_obat"><i--%>
                                    <%--class="fa fa-times"></i> required</p>--%>
                            <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="cor_req_obat">--%>
                                <%--<i class="fa fa-check"></i> correct</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px">Jumlah Obat</label>--%>
                        <%--<div class="col-md-7">--%>
                            <%--<s:textfield value="1" type="number" min="1" cssClass="form-control"--%>
                                         <%--cssStyle="margin-top: 7px" id="req_qty"--%>
                                         <%--onkeypress="var warn =$('#war_req_qty').is(':visible'); if (warn){$('#cor_req_qty').show().fadeOut(3000);$('#war_req_qty').hide()}"></s:textfield>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                            <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="war_req_qty"><i class="fa fa-times"></i> required</p>--%>
                            <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="cor_req_qty"><i class="fa fa-check"></i> correct</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="modal-footer" style="background-color: #cacaca">--%>
                <%--<button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close--%>
                <%--</button>--%>
                <%--<button type="button" class="btn btn-success" id="save_req" onclick="saveAddRequest()"><i--%>
                        <%--class="fa fa-arrow-right"></i> Save--%>
                <%--</button>--%>
                <%--<button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_req"><i--%>
                        <%--class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...--%>
                <%--</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div class="modal fade" id="modal-request">--%>
    <%--<div class="modal-dialog modal-flat">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header" style="background-color: #00a65a">--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
                    <%--<span aria-hidden="true">&times;</span></button>--%>
                <%--<h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="judul"></span>--%>
                <%--</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<div class="alert alert-danger alert-dismissible" style="display: none" id="warning_request-2">--%>
                    <%--<h4><i class="icon fa fa-ban"></i> Warning!</h4>--%>
                    <%--<p id="error_request-2"></p>--%>
                <%--</div>--%>
                <%--<div class="row">--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px">Nama Obat</label>--%>
                        <%--<div class="col-md-7">--%>
                            <%--<input type="text" class="form-control" readonly="true" id="req-2_nama">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px">Stok Obat</label>--%>
                        <%--<div class="col-md-7">--%>
                            <%--<input type="text" style="margin-top: 7px" class="form-control" readonly="true" id="req-2_stok">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-3" style="margin-top: 7px"><span id="label_stok"></span>Jumlah Obat</label>--%>
                        <%--<div class="col-md-7">--%>
                            <%--<s:textfield type="number" min="1" cssClass="form-control"--%>
                                         <%--cssStyle="margin-top: 7px" id="req-2_qty"--%>
                                         <%--onchange="var warn =$('#war_req-2_qty').is(':visible'); if (warn){$('#cor_req-2_qty').show().fadeOut(3000);$('#war_req-2_qty').hide()}"--%>
                                         <%--onkeypress="var warn =$('#war_req-2_qty').is(':visible'); if (warn){$('#cor_req-2_qty').show().fadeOut(3000);$('#war_req-2_qty').hide()}"></s:textfield>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-2">--%>
                            <%--<p style="color: red; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="war_req-2_qty"><i class="fa fa-times"></i> required</p>--%>
                            <%--<p style="color: green; margin-top: 12px; display: none; margin-left: -20px"--%>
                               <%--id="cor_req-2_qty"><i class="fa fa-check"></i> correct</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="modal-footer" style="background-color: #cacaca">--%>
                <%--<button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close--%>
                <%--</button>--%>
                <%--<button type="button" class="btn btn-success" id="save_req-2"><i--%>
                        <%--class="fa fa-arrow-right"></i> Save--%>
                <%--</button>--%>
                <%--<button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_req-2"><i--%>
                        <%--class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...--%>
                <%--</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<!-- /.content-wrapper -->
<script type='text/javascript'>

    function confirmApprove(idObat, idTransaksi, nama, qtyReq, satuan){
        $('#body_approve').html('');
        $('#app_id').text(idObat);
        $('#app_nama').text(nama);
        $('#app_req').text(qtyReq);
        $('#modal-approve').modal('show');
        $('#loading_data').show();
        $('#btn'+idObat).hide();
        $('#load'+idObat).show();
        var table = "";
        dwr.engine.setAsync(true);
        PermintaanObatPoliAction.getListObatEntity(idObat, idTransaksi, {callback: function (response) {
            if(response != null){
                $('#btn'+idObat).show();
                $('#load'+idObat).hide();
                $('#loading_data').hide();
                $.each(response, function (i, item) {
                    var dateFormat = "";
                    if(item.expiredDate != null){
                        dateFormat = $.datepicker.formatDate('dd-mm-yy', new Date(item.expiredDate));
                    }
                    table += '<tr>' +
                            '<td>'+dateFormat+'</td>'+
                            '<td align="center">'+item.qtyBox+'</td>'+
                            '<td align="center">'+item.qtyLembar+'</td>'+
                            '<td align="center">'+item.qtyBiji+'</td>'+
                            '<td align="center">'+'<span id=qtyApp' + dateFormat + '></span>'+'</td>'+
                            '<td>'+satuan+'</td>'+
                            "<td align='center'>" + '<img border="0" id=img' + dateFormat + ' class="hvr-grow" onclick="editQty(\'' + dateFormat + '\',\'' + item.qtyBox + '\',\'' + item.qtyLembar + '\',\'' + item.qtyBiji + '\',\'' + item.lembarPerBox + '\',\'' + item.bijiPerLembar + '\',\'' + satuan + '\',\'' + qtyReq + '\')" src="<s:url value="/pages/images/edit-flat-new.png"/>" style="cursor: pointer; height: 25px; width: 25px;">' + "</td>" +
                            '</tr>';
                    $('#save_app').attr('onclick', 'saveApprove(\'' + idObat + '\',\'' + item.qtyBox + '\',\'' + item.qtyLembar + '\',\'' + item.qtyBiji + '\',\'' + item.lembarPerBox + '\',\'' + item.bijiPerLembar + '\',\'' + satuan + '\',\'' + qtyReq + '\')');
                });
                $('#body_approve').html(table);
            }else{
                $('#btn'+idObat).show();
                $('#load'+idObat).hide();
                $('#loading_data').hide();
            }
        }});
    }

    function editQty(id, qtyBox, qtyLembar, qtyBiji, lembar, biji, jenisSatuan, qtyReq) {
        if ($('#img' + id).attr('src') == '/simrs/pages/images/edit-flat-new.png') {
            var url = '<s:url value="/pages/images/save_flat.png"/>';
            $('#img' + id).attr('src', url);
            var qtyApp = $('#qtyApp'+id).text();
            $('#qtyApp' + id).html('<input oninput="removeWarn(\''+id+'\')" type="number" min="1" id=newQty' + id + ' class="form-control" style="width:90px;" value='+qtyApp+'>');
        } else {
            var url = '<s:url value="/pages/images/edit-flat-new.png"/>';
            var approveVal = $('#newQty' + id).val();
            var approve = 0;

            if(approveVal != ''){
                approve = approveVal;
            }

            var stok = 0;

            if ("box" == jenisSatuan) {
                stok = qtyBox;
            }
            if ("lembar" == jenisSatuan) {
                stok = parseInt(qtyLembar) + (parseInt(lembar * parseInt(qtyBox)));
            }
            if ("biji" == jenisSatuan) {
                stok = parseInt(qtyBiji) + ((parseInt(lembar * parseInt(qtyBox))) * parseInt(biji));
            }

//            if (approve != '') {
                if (parseInt(approve) <= parseInt(stok) && parseInt(approve) <= parseInt(qtyReq)) {
                    $('#img' + id).attr('src', url);
                    var newQty = $('#newQty' + id).val();
                    $('#qtyApp' + id).html('<span id=qtyApp' + id + '>' + newQty + '</span>');
                } else {
                    $('#warning_app').show().fadeOut(5000);
                    $('#msg_app').text("Qty Approve tidak boleh melebihi stok");
                }
//            } else {
//                $('#warning_app').show().fadeOut(5000);
//                $('#newQty'+id).css('border','red solid 1px')
//                $('#msg_app').text("Silahkan cek kembali data inputan");
//            }
        }
    }

    function removeWarn(id){
        $('#newQty'+id).css('border','');
    }

    function saveApprove(idObat, qtyBox, qtyLembar, qtyBiji, lembar, biji, jenisSatuan, qtyReq){
        var data = $('#tabel_approve').tableToJSON();
        var stringData  = JSON.stringify(data);
        var total = "";
        var qtyApp = 0;
        $.each(data, function (i, item) {
            var qty = data[i]["Qty Approve"];
            if(data[i]["Qty Approve"] == ""){
               qty =  0;
            }
            qtyApp = parseInt(qtyApp) + parseInt(qty);
        });

        var stok = 0;

        if ("box" == jenisSatuan) {
            stok = qtyBox;
        }
        if ("lembar" == jenisSatuan) {
            stok = parseInt(qtyLembar) + (parseInt(lembar * parseInt(qtyBox)));
        }
        if ("biji" == jenisSatuan) {
            stok = parseInt(qtyBiji) + ((parseInt(lembar * parseInt(qtyBox))) * parseInt(biji));
        }

        if(qtyApp > 0){
            if(parseInt(qtyApp) <= parseInt(stok) && parseInt(qtyApp) <= parseInt(qtyReq)){
                PermintaanObatPoliAction.saveApproveRequest(idObat, stringData, function (response) {

                });
            }else{
                $('#warning_app').show().fadeOut(5000);
                $('#msg_app').text("Qty Approve tidak boleh melebihi stok dan qty request..!");
            }
        }else{
            $('#warning_app').show().fadeOut(5000);
            $('#msg_app').text("Qty Approve tidak boleh kosong..!");
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>