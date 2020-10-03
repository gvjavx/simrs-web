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
        .jarak_atas {
            margin-top: 7px
        }
    </style>

    <link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/radio_checkbox.css"/>">
    <script type='text/javascript' src='<s:url value="/dwr/interface/TindakanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/HeaderTindakanAction.js"/>'></script>

    <script type='text/javascript'>

        $(document).ready(function () {
            getBranch();
            $('#tindakan').addClass('active');
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
            Data Tindakan
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Data Tindakan</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="tindakanForm" method="post" namespace="/tindakan"
                                    action="search_tindakan.action"
                                    theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Unit</label>
                                    <div class="col-sm-4">
                                        <select class="form-control select2" id="branch" name="tindakan.branchId"
                                                onchange="getPelayanan(this.value)">
                                            <option value="">[Select One]</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Pelayanan</label>
                                    <div class="col-sm-4">
                                        <select class="form-control select2" id="pelayanan" name="tindakan.idPelayanan">
                                            <option value="">[Select One]</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Kategori Tindakan</label>
                                    <div class="col-sm-4">
                                        <s:action id="initComboKategori" namespace="/tindakan"
                                                  name="initComboKategori_tindakan"/>
                                        <s:select list="#initComboKategori.listOfComboKategoriTindakan"
                                                  id="idKategoriTindakan" name="tindakan.idKategoriTindakan"
                                                  listKey="idKategoriTindakan" listValue="kategoriTindakan" headerKey=""
                                                  headerValue="[Select one]" cssClass="form-control select2"
                                                  cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Tindakan</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_tindakan" name="tindakan.idTindakan"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Tindakan</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_tindakan" name="tindakan.tindakan"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="tindakan.flag"
                                                  headerKey="Y" headerValue="Active" cssClass="form-control select2"
                                                  cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="tindakanForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_tindakan.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a onclick="showModal('add')" class="btn btn-primary"><i class="fa fa-plus"></i>
                                            Tambah Tindakan</a>
                                    </div>
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
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true"
                                                   resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                                         alt="Loading..."/></center>
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
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Tindakan</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped" style="font-size: 12px">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Tindakan</td>
                                <td>Pelayanan</td>
                                <td>Kategori Tindakan</td>
                                <td>Nama Tindakan</td>
                                <td>Tarif (Rp.)</td>
                                <td>Tarif BPJS (Rp.)</td>
                                <td>Diskon (%)</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idTindakan"/></td>
                                    <td><s:property value="namaPelayanan"/></td>
                                    <td><s:property value="namaKategoriTindakan"/></td>
                                    <td><s:property value="tindakan"/></td>
                                    <td>
                                        <script>
                                            converterRupiah('<s:property value="tarif"/>');
                                        </script>
                                    </td>
                                    <td>
                                        <script>
                                            converterRupiah('<s:property value="tarifBpjs"/>');
                                        </script>
                                    </td>
                                    <td><s:property value="diskon"/></td>
                                    <td align="center" width="10%">
                                        <img class="hvr-grow"
                                             onclick="showModal('detail', '<s:property value="idTindakan"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-view-25.png"/>">
                                        <img class="hvr-grow"
                                             onclick="showModal('edit', '<s:property value="idTindakan"/>', '<s:property
                                                     value="branchId"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-create-25.png"/>">
                                        <img class="hvr-grow"
                                             onclick="showModal('delete', '<s:property value="idTindakan"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/cancel-flat-new.png"/>">
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
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-add">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Tambah Tindakan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Unit</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="set_nama_unit" style="width: 100%"
                                    onchange="var warn =$('#war_set_nama_unit').is(':visible'); if (warn){$('#cor_set_nama_unit').show().fadeOut(3000);$('#war_set_nama_unit').hide()};getPelayanan(this.value)"></select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_nama_unit">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_nama_unit"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Pelayanan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="set_nama_pelayanan" style="width: 100%"
                                    onchange="var warn =$('#war_set_nama_pelayanan').is(':visible'); if (warn){$('#cor_set_nama_pelayanan').show().fadeOut(3000);$('#war_set_nama_pelayanan').hide()}">
                                <option value="">[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_nama_pelayanan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_nama_pelayanan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Kategori Tindakan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="width: 100%" id="set_kategori_tindakan"
                                    onchange="var warn =$('#war_set_kategori_tindakan').is(':visible'); if (warn){$('#cor_set_kategori_tindakan').show().fadeOut(3000);$('#war_set_kategori_tindakan').hide()}">
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_kategori_tindakan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_kategori_tindakan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Tindakan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="set_nama_tindakan" style="width: 100%"
                                    onchange="var warn =$('#war_set_nama_tindakan').is(':visible'); if (warn){$('#cor_set_nama_tindakan').show().fadeOut(3000);$('#war_set_nama_tindakan').hide()}"></select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_nama_tindakan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_nama_tindakan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="col-md-offset-3 col-md-6">
                        <a class="btn btn-success" onclick="addToList()"><i class="fa fa-plus"></i> Tambah</a>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="col-md-12">
                        <table id="table_pelayanan" class="table table-bordered table-striped" style="font-size: 12px">
                            <thead>
                            <tr style="font-weight: bold">
                                <td width="30%">Nama Tindakan</td>
                                <td>Tarif (Rp.)</td>
                                <td>Tarif Bpjs (Rp.)</td>
                                <td>Diskon (%)</td>
                                <td>Elektif</td>
                                <td>Ina</td>
                                <td>Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_tindakan"></tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_add"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_add"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Detail Data Tindakan</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-striped" style="font-size: 12px">
                                <tr>
                                    <td width="30%"><b>Unit</b></td>
                                    <td><span id="v_unit"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Pelayanan</b></td>
                                    <td><span id="v_nama_pelayanan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>ID Tindakan</b></td>
                                    <td><span id="v_id_tindakan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama Tindakan</b></td>
                                    <td><span id="v_nama_tindakan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kategori Tindakan</b></td>
                                    <td><span id="v_kategori_tindakan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tarif</b></td>
                                    <td><span id="v_tarif"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tarif BPJS</b></td>
                                    <td><span id="v_tarif_bpjs"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Diskon</b></td>
                                    <td><span id="v_diskon"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kategori INA BPJS</b></td>
                                    <td><span id="v_kategori_ina_bpjs"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Is Ina</b></td>
                                    <td><span id="v_is_ina"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Is Elektif</b></td>
                                    <td><span id="v_is_elektif"></span></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-edit">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Edit Tindakan
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_edit">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_edit"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Unit</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="edit_nama_unit" style="width: 100%"
                                    onchange="var warn =$('#war_edit_nama_unit').is(':visible'); if (warn){$('#cor_edit_nama_unit').show().fadeOut(3000);$('#war_edit_nama_unit').hide()}"></select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_nama_unit">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_nama_unit"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Pelayanan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="edit_nama_pelayanan" style="width: 100%"
                                    onchange="var warn =$('#war_edit_nama_pelayanan').is(':visible'); if (warn){$('#cor_edit_nama_pelayanan').show().fadeOut(3000);$('#war_edit_nama_pelayanan').hide()}"></select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_nama_pelayanan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_nama_pelayanan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Kategori Tindakan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" style="width: 100%" id="edit_kategori_tindakan"
                                    onchange="var warn =$('#war_edit_kategori_tindakan').is(':visible'); if (warn){$('#cor_edit_kategori_tindakan').show().fadeOut(3000);$('#war_edit_kategori_tindakan').hide()}">
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_kategori_tindakan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_kategori_tindakan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Tindakan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="edit_nama_tindakan" style="width: 100%"
                                    onchange="var warn =$('#war_edit_nama_tindakan').is(':visible'); if (warn){$('#cor_edit_nama_tindakan').show().fadeOut(3000);$('#war_edit_nama_tindakan').hide()}"></select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_nama_tindakan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_nama_tindakan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif</label>
                        <div class="col-md-7">
                            <div class="input-group" style="margin-top: 7px">
                                <div class="input-group-addon">
                                    Rp.
                                </div>
                                <input class="form-control" id="edit_tarif"
                                       oninput="var warn =$('#war_edit_tarif').is(':visible'); if (warn){$('#cor_edit_tarif').show().fadeOut(3000);$('#war_edit_tarif').hide()}; convertRpAtas(this.id, this.value, 'h_tarif')">
                                <input type="hidden" id="h_tarif">
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_tarif">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_tarif"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Tarif BPJS</label>
                        <div class="col-md-7">
                            <div class="input-group" style="margin-top: 7px">
                                <div class="input-group-addon">
                                    Rp.
                                </div>
                                <input class="form-control" id="edit_tarif_bpjs"
                                       oninput="var warn =$('#war_edit_tarif_bpjs').is(':visible'); if (warn){$('#cor_edit_tarif_bpjs').show().fadeOut(3000);$('#war_edit_tarif_bpjs').hide()}; convertRpAtas(this.id, this.value, 'h_tarif_bpjs')">
                                <input type="hidden" id="h_tarif_bpjs">
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_tarif_bpjs">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_tarif_bpjs"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Diskon (%)</label>
                        <div class="col-md-7">
                            <input class="form-control jarak_atas" id="edit_diskon" type="number"
                                   oninput="var warn =$('#war_edit_diskon').is(':visible'); if (warn){$('#cor_edit_diskon').show().fadeOut(3000);$('#war_edit_diskon').hide()};">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_diskon">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_diskon"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3">Is Ina</label>
                        <div class="col-md-7">
                            <div class="form-check">
                                <input type="checkbox" name="ina" id="edit_is_ina" value="Y">
                                <label for="edit_is_ina"></label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3">Is Elektif</label>
                        <div class="col-md-7">
                            <div class="form-check">
                                <input type="checkbox" name="elektif" id="edit_is_elektif" value="Y">
                                <label for="edit_is_elektif"></label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_edit"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_edit"><i
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
                <h4 id="pesan"></h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    function showModal(tipe, id, branchId) {
        if ('add' == tipe) {
            $('#set_nama_unit').attr('disabled', false);
            $('#set_nama_pelayanan').attr('disabled', false);
            $('#set_kategori_tindakan').attr('disabled', false);
            $('#save_add').attr('onclick', 'saveTindakan("")');
            $('#set_judul').text("Tambah Tindakan");
            $('#modal-add').modal({show: true, backdrop: 'static'});
            getKategoriTindakan();
            getTindakan();
        }
        if ('detail' == tipe) {
            getDataTindakan(id);
            $('#modal-view').modal({show: true, static: 'backdrop'});
        }
        if ('edit' == tipe) {
            getPelayanan(branchId);
            getKategoriTindakan();
            getTindakan();
            $('#modal-edit').modal({show: true, static: 'backdrop'});
            getDataTindakan(id);
            $('#save_edit').attr('onclick', 'saveTindakan(\'' + id + '\')');
        }
        if ('delete' == tipe) {
            $('#pesan').text('Do you want delete this record?');
            $('#modal-confirm-dialog').modal({show: true, static: 'static'});
            $('#save_con').attr('onclick','saveDelete(\''+id+'\')');
        }
    }

    function addToList() {
        var data = $('#table_pelayanan').tableToJSON();
        var branch = $('#set_nama_unit').val();
        var pelayanan = $('#set_nama_pelayanan').val();
        var kategori = $('#set_kategori_tindakan').val();
        var tindakan = $('#set_nama_tindakan').val();
        var namaTindakan = $('#set_nama_tindakan option:selected').text();
        var id = data.length;

        if (branch && pelayanan && kategori && tindakan) {
            var cek = false;
            $.each(data, function (i, item) {
                var idTindakan = $('#id_tindakan_' + i).val();
                if (idTindakan == tindakan) {
                    cek = true;
                }
            });
            if (cek) {
                $('#warning_add').show().fadeOut(5000);
                $('#msg_add').text("Data sudah ada dalam list...!");
            } else {
                HeaderTindakanAction.initHeaderTindakan(tindakan, function (res) {
                    if (res.idHeaderTindakan != null) {
                        var table = '<tr id="row_' + tindakan + '">' +
                            '<td style="vertical-align: middle">' + namaTindakan +
                            '<input id="id_tindakan_' + id + '" type="hidden" value="' + tindakan + '"></td>' +
                            '<td>' +
                            '<input id="tarif_' + id + '" class="form-control" value="' + formatRupiahAtas(res.standardCost) + '" oninput="convertRpAtas(this.id, this.value, \'h_tarif_' + id + '\')">' +
                            '<input type="hidden" id="h_tarif_' + id + '" class="form-control" value="' + res.standardCost + '">' +
                            '</td>' +
                            '<td>' +
                            '<input id="tarif_bpjs_' + id + '" class="form-control" value="' + formatRupiahAtas(res.standardCost) + '" oninput="convertRpAtas(this.id, this.value, \'h_tarif_bpjs_' + id + '\')">' +
                            '<input type="hidden" id="h_tarif_bpjs_' + id + '" class="form-control" value="' + res.standardCost + '">' +
                            '</td>' +
                            '<td>' +
                            '<input min="0" id="diskon_' + id + '" type="number" class="form-control" value="0">' +
                            '</td>' +
                            '<td style="vertical-align: middle">' +
                            '<div class="form-check">\n' +
                            '<input type="checkbox" name="elektif" id="is_elektif_' + id + '" value="Y">\n' +
                            '<label for="is_elektif_' + id + '"></label>\n' +
                            '</div>' +
                            '</td>' +
                            '<td style="vertical-align: middle">' +
                            '<div class="form-check">\n' +
                            '<input type="checkbox" name="ina" id="is_ina_' + id + '" value="Y">\n' +
                            '<label for="is_ina_' + id + '"></label>\n' +
                            '</div>' +
                            '</td>' +
                            '<td style="vertical-align: middle" align="center">' +
                            '<img class="hvr-grow" onclick="delTindakan(\'' + tindakan + '\')" src="<s:url value='/pages/images/cancel-flat-new.png'/>">' +
                            '</td>'
                        '</tr>';
                        $('#body_tindakan').append(table);
                        $('#set_nama_unit').attr('disabled', true);
                        $('#set_nama_pelayanan').attr('disabled', true);
                        $('#set_kategori_tindakan').attr('disabled', true);
                    }
                });
            }
        } else {
            $('#warning_add').show().fadeOut(5000);
            $('#msg_add').text("Silahkan cek kembali data inputan berikut...!");

            if (branch == '') {
                $('#war_set_nama_unit').show();
            }
            if (pelayanan == '') {
                $('#war_set_nama_pelayanan').show();
            }
            if (kategori == '') {
                $('#war_set_kategori_tindakan').show();
            }
            if (tindakan == '') {
                $('#war_set_nama_tindakan').show();
            }
        }
    }

    function delTindakan(id) {
        $('#row_' + id).remove();
    }

    function saveTindakan(id) {
        var data = "";
        if (id != '') {
            var namaUnit = $('#edit_nama_unit').val();
            var namaPelayanan = $('#edit_nama_pelayanan').val();
            var idKategori = $('#edit_kategori_tindakan').val();
            var namaTindakan = $('#edit_nama_tindakan').val();
            var tarif = $('#h_tarif').val();
            var tarifBpjs = $('#h_tarif_bpjs').val();
            var diskon = $('#edit_diskon').val();
            if (namaUnit && namaPelayanan && idKategori && tarif && tarifBpjs && namaTindakan != '') {
                $('#save_edit').hide();
                $('#load_edit').show();
                var isEleftif = $('#edit_is_elektif').is(':checked');
                var isIna = $('#edit_is_ina').is(':checked');
                var ina = "N";
                var elektif = "N";
                if (isEleftif) {
                    elektif = "Y";
                }
                if (isIna) {
                    ina = "Y";
                }
                if(diskon == ''){
                    diskon = '0';
                }
                data = {
                    'id_tindakan': id,
                    'id_kategori_tindakan': idKategori,
                    'id_header_tindakan': namaTindakan,
                    'id_pelayanan': namaPelayanan,
                    'branch_id': namaUnit,
                    'tarif': tarif,
                    'tarif_bpjs': tarifBpjs,
                    'diskon': diskon,
                    'is_ina': ina,
                    'is_elektif': elektif,
                };
                var dataString = JSON.stringify(data);
                dwr.engine.setAsync(true);
                TindakanAction.saveEdit(dataString, {
                    callback: function (response) {
                        if (response.status == "success") {
                            $('#modal-edit').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#save_edit').show();
                            $('#load_edit').hide();
                            $('body').scrollTop(0);

                        } else {
                            $('#warning_edit').show().fadeOut(5000);
                            $('#msg_edit').text(response.msg);
                            $('#save_edit').show();
                            $('#load_edit').hide();
                        }
                    }
                });
            } else {
                $('#warning_edit').show().fadeOut(5000);
                $('#msg_edit').text("Silahkan cek kembali data inputan berikut...!");

                if (namaUnit == '') {
                    $('#war_edit_nama_unit').show();
                }
                if (namaPelayanan == '' || namaPelayanan == null) {
                    $('#war_edit_nama_pelayanan').show();
                }
                if (idKategori == '') {
                    $('#war_edit_kategori_tindakan').show();
                }
                if (namaTindakan == '') {
                    $('#war_edit_nama_tindakan').show();
                }
                if (tarif == '') {
                    $('#war_edit_tarif').show();
                }
                if (tarifBpjs == '') {
                    $('#war_edit_tarif_bpjs').show();
                }
            }
        } else {
            var dataPelayanan = $('#table_pelayanan').tableToJSON();
            var namaUnit = $('#set_nama_unit').val();
            var namaPelayanan = $('#set_nama_pelayanan').val();
            var idKategori = $('#set_kategori_tindakan').val();
            var namaTindakan = $('#set_nama_tindakan').val();
            var tarif = $('#h_tarif').val();
            var tarifBpjs = $('#h_tarif_bpjs').val();
            var diskon = $('#h_diskon').val();

            if (namaUnit && namaPelayanan && idKategori && namaTindakan != '' && dataPelayanan.length > 0) {
                if (dataPelayanan.length > 0) {
                    var dataSave = [];
                    $('#save_add').hide();
                    $('#load_add').show();
                    $.each(dataPelayanan, function (i, item) {
                        var idHeaderTindakan = $('#id_tindakan_' + i).val();
                        var tarif = $('#h_tarif_' + i).val();
                        var tarifBpjs = $('#h_tarif_bpjs_' + i).val();
                        var diskon = $('#diskon_' + i).val();
                        var isEleftif = $('#is_elektif_' + i).is(':checked');
                        var isIna = $('#is_ina_' + i).is(':checked');
                        var ina = "N";
                        var elektif = "N";
                        if (isEleftif) {
                            elektif = "Y";
                        }
                        if (isIna) {
                            ina = "Y";
                        }
                        dataSave.push({
                            'id_kategori_tindakan': idKategori,
                            'id_header_tindakan': idHeaderTindakan,
                            'id_pelayanan': namaPelayanan,
                            'branch_id': namaUnit,
                            'tarif': tarif,
                            'tarif_bpjs': tarifBpjs,
                            'diskon': diskon,
                            'is_ina': ina,
                            'is_elektif': elektif,
                        });
                    });
                    var dataString = JSON.stringify(dataSave);
                    dwr.engine.setAsync(true);
                    TindakanAction.saveAdd(dataString, {
                        callback: function (response) {
                            if (response.status == "success") {
                                $('#modal-add').modal('hide');
                                $('#info_dialog').dialog('open');
                                $('#save_add').show();
                                $('#load_add').hide();
                                $('body').scrollTop(0);

                            } else {
                                $('#warning_add').show().fadeOut(5000);
                                $('#msg_add').text(response.msg);
                                $('#save_add').show();
                                $('#load_add').hide();
                            }
                        }
                    });
                } else {
                    $('#warning_add').show().fadeOut(5000);
                    $('#msg_add').text("Silahkan cek kembali data inputan berikut...!");
                }
            } else {
                $('#warning_add').show().fadeOut(5000);
                $('#msg_add').text("Silahkan cek kembali data inputan berikut...!");

                if (namaUnit == '') {
                    $('#war_set_nama_unit').show();
                }
                if (namaPelayanan == '') {
                    $('#war_set_nama_pelayanan').show();
                }
                if (idKategori == '') {
                    $('#war_set_kategori_tindakan').show();
                }
                if (namaTindakan == '') {
                    $('#war_set_nama_tindakan').show();
                }
            }
        }
    }

    function getKategoriTindakan() {
        var option = '<option value="">[Select One]</option>';
        TindakanAction.getComboKategoriTindakan(function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idKategoriTindakan + '">' + item.kategoriTindakan + '</option>';
                });
                $('#set_kategori_tindakan').html(option);
                $('#edit_kategori_tindakan').html(option);
            }
        });
    }

    function getBranch() {
        var option = '<option value="">[Select One]</option>';
        TindakanAction.getComboBranch(function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.branchId + '">' + item.branchName + '</option>'
                });
            }
            $('#set_nama_unit').html(option);
            $('#edit_nama_unit').html(option);
            $('#branch').html(option);
        });
    }

    function getPelayanan(idPelayanan) {
        var option = '<option value="">[Select One]</option>';
        TindakanAction.getComboPelayanan(idPelayanan, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idPelayanan + '">' + item.namaPelayanan + '</option>'
                })
            }
            $('#set_nama_pelayanan').html(option);
            $('#edit_nama_pelayanan').html(option);
            $('#pelayanan').html(option);
        });
    }

    function getTindakan() {
        var option = '<option value="">[Select One]</option>';
        TindakanAction.getComboTindakan(function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idHeaderTindakan + '">' + item.namaTindakan + '</option>'
                });
            }
            $('#set_nama_tindakan').html(option);
            $('#edit_nama_tindakan').html(option);
        });
    }

    function cekScrol(id, idTujuan) {
        if ($('#' + id).is(':checked')) {
            $('#' + idTujuan).attr('style', 'height: 450px;overflow-y: scroll;');
        } else {
            $('#' + idTujuan).removeAttr('style');
        }
    }

    function getDataTindakan(id) {
        TindakanAction.initTindakan(id, function (res) {
            if (res.idTindakan != null) {
                $('#v_unit').text(res.branchName);
                $('#v_id_tindakan').text(res.idTindakan);
                $('#v_nama_tindakan').text(res.tindakan);
                $('#v_diskon').text(res.diskon + " %");
                $('#v_nama_pelayanan').text(res.namaPelayanan);
                $('#v_kategori_ina_bpjs').text(res.namaKategoriTindakanIna);
                $('#v_kategori_tindakan').text(res.namaKategoriTindakan);
                $('#v_tarif').text("Rp. " + formatRupiahAtas(res.tarif));
                $('#v_tarif_bpjs').text("Rp. " + formatRupiahAtas(res.tarifBpjs));
                $('#v_is_ina').text(res.isIna);
                $('#v_is_elektif').text(res.isElektif);

                $('#edit_nama_unit').val(res.branchId).trigger('change');
                $('#edit_nama_pelayanan').val(res.idPelayanan).trigger('change');
                $('#edit_kategori_tindakan').val(res.idKategoriTindakan).trigger('change');
                $('#edit_nama_tindakan').val(res.idHeaderTindakan).trigger('change');
                $('#edit_tarif').val(formatRupiahAtas(res.tarif));
                $('#h_tarif').val(res.tarif);
                $('#edit_tarif_bpjs').val(formatRupiahAtas(res.tarifBpjs));
                $('#h_tarif_bpjs').val(res.tarifBpjs);
                $('#edit_diskon').val(res.diskon);

                if (res.isIna == "Y") {
                    $('#edit_is_ina').attr('checked', true);
                } else {
                    $('#edit_is_ina').attr('checked', false);
                }
                if (res.isElektif == "Y") {
                    $('#edit_is_elektif').attr('checked', true);
                } else {
                    $('#edit_is_elektif').attr('checked', false);
                }
            }
        });
    }

    function saveDelete(id) {
        $('#modal-confirm-dialog').modal('hide');
        $('#waiting_dialog').dialog('open');
        dwr.engine.setAsync(true);
        TindakanAction.saveDelete(id, {
            callback: function (res) {
                if (res.status == "success") {
                    $('#waiting_dialog').dialog('close');
                    $('#info_dialog').dialog('open');
                    $('body').scrollTop(0);
                } else {
                    $('#waiting_dialog').dialog('close');
                    $('#error_dialog').dialog('open');
                    $('#errorMessage').text(res.msg);
                    $('body').scrollTop(0);
                }
            }
        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>