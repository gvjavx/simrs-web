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
    <script type='text/javascript' src='<s:url value="/dwr/interface/LabDetailAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/TindakanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/ParameterPemeriksaanAction.js"/>'></script>

    <script type='text/javascript'>

        $(document).ready(function () {
            getBranch();
            getLab();
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
            Data Parameter Pemeriksaan Penunjang Medis
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Data Parameter Pemeriksaan
                            Penunjang Medis</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="labdetailForm" method="post" namespace="/labdetail"
                                    action="search_labdetail.action"
                                    theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Unit</label>
                                    <div class="col-sm-4">
                                        <select class="form-control select2" id="branch">
                                            <option value="">[Select One]</option>
                                        </select>
                                        <s:hidden id="h_branch_id" name="labDetail.branchId"></s:hidden>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Kategori</label>
                                    <div class="col-sm-4">
                                        <s:action id="comboLab2" namespace="/kategorilab"
                                                  name="getListKategoriLab_kategorilab"/>
                                        <s:select cssStyle="margin-top: 7px; width: 100%"
                                                  list="#comboLab2.listOfKategoriLab" id="kategori"
                                                  listKey="idKategoriLab"
                                                  listValue="namaKategori"
                                                  name="labDetail.idKategoriLab"
                                                  headerKey="" headerValue="[Select one]"
                                                  cssClass="form-control select2"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Paket</label>
                                    <div class="col-sm-4">
                                        <select class="form-control select2" id="paket" style="width: 100%">
                                            <option value="">[Select One]</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Parameter</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_labdetail" name="labdetail.idTindakan"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Parameter</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_labdetail" name="labdetail.namaDetailPeriksa"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="labdetail.flag"
                                                  headerKey="Y" headerValue="Active" cssClass="form-control select2"
                                                  cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="labdetailForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_labdetail.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a onclick="showModal('add')" class="btn btn-primary"><i class="fa fa-plus"></i>
                                            Tambah Parameter</a>
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
                                <td>ID Parameter</td>
                                <td>Paket</td>
                                <td>Nama Pemeriksaan</td>
                                <td>Ket Acuan L</td>
                                <td>Ket Acuan P</td>
                                <td align="center">Tarif (Rp.)</td>
                                <td>Satuan</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idLabDetail"/></td>
                                    <td><s:property value="namaLab"/></td>
                                    <td><s:property value="namaDetailPeriksa"/></td>
                                    <td><s:property value="keteranganAcuanL"/></td>
                                    <td><s:property value="keteranganAcuanP"/></td>
                                    <td align="right">
                                        <script>
                                            converterRupiah('<s:property value="tarif"/>');
                                        </script>
                                    </td>
                                    <td><s:property value="satuan"/></td>
                                    <td align="center" width="10%">
                                        <img class="hvr-grow"
                                             onclick="showModal('detail', '<s:property value="idLabDetail"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-view-25.png"/>">
                                        <img class="hvr-grow"
                                             onclick="showModal('edit', '<s:property value="idLabDetail"/>', '<s:property value="idKategoriLab"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-create-25.png"/>">
                                        <img class="hvr-grow"
                                             onclick="showModal('delete', '<s:property value="idLabDetail"/>')"
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
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Tambah Parameter Pemeriksaan
                </h4>
            </div>
            <div class="modal-body" id="temp_scrol">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Unit</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="set_nama_unit" style="width: 100%"
                                    onchange="var warn =$('#war_set_nama_unit').is(':visible'); if (warn){$('#cor_set_nama_unit').show().fadeOut(3000);$('#war_set_nama_unit').hide()}"></select>
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
                        <label class="col-md-3" style="margin-top: 7px">Kategori</label>
                        <div class="col-md-7">
                            <s:action id="comboLab2" namespace="/kategorilab"
                                      name="getListKategoriLab_kategorilab"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#comboLab2.listOfKategoriLab" id="set_kategori_lab"
                                      listKey="idKategoriLab"
                                      listValue="namaKategori"
                                      name="labDetail.idKategoriLab"
                                      headerKey="" headerValue="[Select one]"
                                      onchange="var warn =$('#war_set_kategori_lab').is(':visible'); if (warn){$('#cor_set_kategori_lab').show().fadeOut(3000);$('#war_set_kategori_lab').hide()}; getParameter(this.value); getLabKategori(this.value)"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_kategori_lab">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_kategori_lab"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Paket</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="set_paket" style="width: 100%"
                                    onchange="var warn =$('#war_set_paket').is(':visible'); if (warn){$('#cor_set_paket').show().fadeOut(3000);$('#war_set_paket').hide()}; cekPaket(this.value)">
                                <option value="">[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_paket">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_paket"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas" style="display: none" id="form_set_paket_baru">
                    <div class="form-group">
                        <label class="col-md-3">Paket Baru</label>
                        <div class="col-md-7">
                            <input class="form-control" id="set_paket_baru"
                                   oninput="var warn =$('#war_set_paket_baru').is(':visible'); if (warn){$('#cor_set_paket_baru').show().fadeOut(3000);$('#war_set_paket_baru').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_paket_baru">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_paket_baru"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Parameter</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="set_nama_parameter" style="width: 100%"
                                    onchange="var warn =$('#war_set_nama_parameter').is(':visible'); if (warn){$('#cor_set_nama_parameter').show().fadeOut(3000);$('#war_set_nama_parameter').hide()}">
                                <option value="">[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_nama_parameter">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_nama_parameter"><i class="fa fa-check"></i> correct</p>
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
                        <table id="table_lab" class="table table-bordered table-striped" style="font-size: 12px">
                            <thead>
                            <tr style="font-weight: bold">
                                <td width="50%">Nama Parameter</td>
                                <td align="center">Tarif (Rp.)</td>
                                <td align="center" width="15%">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_lab"></tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <span onclick="cekScrol('fa_scrol', 'temp_scrol')" class="pull-left hvr-grow"
                      style="color: black; margin-top: 11px">
                    <i id="fa_scrol" class="fa fa-unlock"></i>
                </span>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_add"><i
                        class="fa fa-check"></i> Save
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
                                    <td width="40%"><b>ID Lab Detail</b></td>
                                    <td><span id="v_id_lab_detail"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama Parameter</b></td>
                                    <td><span id="v_nama"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kategori</b></td>
                                    <td><span id="v_kategori"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama Paket</b></td>
                                    <td><span id="v_paket"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Keterangan Acuan L</b></td>
                                    <td><span id="v_ket_acuan_l"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Keterangan Acuan P</b></td>
                                    <td><span id="v_ket_acuan_p"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Satuan</b></td>
                                    <td><span id="v_satuan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tarif</b></td>
                                    <td><span id="v_tarif"></span></td>
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Edit Parameter pemeriksaan
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
                        <label class="col-md-3" style="margin-top: 7px">Kategori</label>
                        <div class="col-md-7">
                            <s:action id="comboLab2" namespace="/kategorilab"
                                      name="getListKategoriLab_kategorilab"/>
                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                      list="#comboLab2.listOfKategoriLab" id="edit_kategori_lab"
                                      listKey="idKategoriLab"
                                      listValue="namaKategori"
                                      name="labDetail.idKategoriLab"
                                      headerKey="" headerValue="[Select one]"
                                      onchange="var warn =$('#war_edit_kategori_lab').is(':visible'); if (warn){$('#cor_edit_kategori_lab').show().fadeOut(3000);$('#war_edit_kategori_lab').hide()}; getParameter(this.value); getLabKategori(this.value)"
                                      cssClass="form-control select2"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_kategori_lab">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_kategori_lab"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Paket</label>
                        <div class="col-md-7">
                            <input type="hidden" id="h_edit_paket">
                            <input class="form-control" id="edit_paket" style="margin-top: 7px"
                                   onchange="var warn =$('#war_edit_paket').is(':visible'); if (warn){$('#cor_edit_paket').show().fadeOut(3000);$('#war_edit_paket').hide()};">
                            <%--<select class="form-control select2" id="edit_paket" style="width: 100%"--%>
                            <%--onchange="var warn =$('#war_edit_paket').is(':visible'); if (warn){$('#cor_edit_paket').show().fadeOut(3000);$('#war_edit_paket').hide()}; cekPaket(this.value)">--%>
                            <%--<option value="">[Select One]</option>--%>
                            <%--</select>--%>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_paket">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_paket"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Nama Parameter</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="edit_nama_parameter" style="width: 100%"
                                    onchange="var warn =$('#war_edit_nama_parameter').is(':visible'); if (warn){$('#cor_edit_nama_parameter').show().fadeOut(3000);$('#war_edit_nama_parameter').hide()}; setTarifParams(this.value)">
                                <option value="">[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_nama_parameter">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_nama_parameter"><i class="fa fa-check"></i> correct</p>
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
                                       oninput="var warn =$('#war_edit_tarif').is(':visible'); if (warn){$('#cor_edit_tarif').show().fadeOut(3000);$('#war_edit_tarif').hide()}; convertRpAtas(this.id, this.value, 'edit_h_tarif')">
                                <input type="hidden" id="edit_h_tarif">
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
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_edit"><i
                        class="fa fa-check"></i> Save
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
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-check"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    function showModal(tipe, id, idKategori) {
        if ('add' == tipe) {
            $('#set_nama_unit').attr('disabled', false);
            $('#set_paket').attr('disabled', false);
            $('#set_paket_baru').attr('disabled', false);
            $('#set_kategori_lab').attr('disabled', false);
            $('#save_add').attr('onclick', 'saveLab("")');
            $('#set_judul').text("Tambah Tindakan");
            $('#modal-add').modal({show: true, backdrop: 'static'});
            $('#body_lab').html('');
        }
        if ('detail' == tipe) {
            getDataParameter(id);
            $('#modal-view').modal({show: true, static: 'backdrop'});
        }
        if ('edit' == tipe) {
            getParameter(idKategori);
            $('#save_edit').attr('onclick', 'saveLab(\'' + id + '\')');
            $('#modal-edit').modal({show: true, static: 'backdrop'});
            getDataParameter(id);
        }
        if ('delete' == tipe) {
            $('#pesan').text('Do you want delete this record?');
            $('#modal-confirm-dialog').modal({show: true, static: 'static'});
            $('#save_con').attr('onclick', 'saveDelete(\'' + id + '\')');
        }
    }

    function addToList() {
        var data = $('#table_lab').tableToJSON();
        var id = data.length;
        var branch = $('#set_nama_unit').val();
        var paket = $('#set_paket').val();
        var kategori = $('#set_kategori_lab').val();
        var parameter = $('#set_nama_parameter').val();
        var namaParameter = $('#set_nama_parameter option:selected').text();
        var paketLab = "";
        if (paket == "new") {
            var ket = $('#set_paket_baru').val();
            if (ket != '') {
                paketLab = ket;
            }
        } else {
            paketLab = paket;
        }

        if (branch && paketLab && kategori && parameter) {
            var cek = false;
            $.each(data, function (i, item) {
                var idParameter = $('#id_parameter_' + i).val();
                if (parameter == idParameter) {
                    cek = true;
                }
            });
            if (cek) {
                $('#warning_add').show().fadeOut(5000);
                $('#msg_add').text("Data sudah ada dalam list...!");
            } else {
                ParameterPemeriksaanAction.initParameterPemeriksaan(parameter, function (res) {
                    if (res.idParameterPemeriksaan != null) {
                        var table = '<tr id="row_' + parameter + '">' +
                            '<td style="vertical-align: middle">' + namaParameter +
                            '<input id="id_parameter_' + id + '" type="hidden" value="' + parameter + '"></td>' +
                            '<input id="nama_parameter_' + id + '" type="hidden" value="' + namaParameter + '"></td>' +
                            '<td>' +
                            '<input id="tarif_' + id + '" class="form-control" value="' + formatRupiahAtas(res.tarif) + '" oninput="convertRpAtas(this.id, this.value, \'h_tarif_' + id + '\')">' +
                            '<input type="hidden" id="h_tarif_' + id + '" class="form-control" value="' + res.tarif + '">' +
                            '</td>' +
                            '<td style="vertical-align: middle" align="center">' +
                            '<img class="hvr-grow" onclick="delLabDetail(\'' + parameter + '\')" src="<s:url value='/pages/images/cancel-flat-new.png'/>">' +
                            '</td>'
                        '</tr>';
                        $('#body_lab').append(table);
                        $('#set_nama_unit').attr('disabled', true);
                        $('#set_paket').attr('disabled', true);
                        $('#set_paket_baru').attr('disabled', true);
                        $('#set_kategori_lab').attr('disabled', true);
                    }
                });
            }
        } else {
            $('#warning_add').show().fadeOut(5000);
            $('#msg_add').text("Silahkan cek kembali data inputan berikut...!");
            if (branch == '') {
                $('#war_set_nama_unit').show();
            }
            if (paketLab == '') {
                if (paket == 'new') {
                    $('#war_set_paket_baru').show();
                } else {
                    $('#war_set_paket').show();
                }
            }
            if (kategori == '') {
                $('#war_set_kategori_lab').show();
            }
            if (parameter == '') {
                $('#war_set_nama_parameter').show();
            }
        }
    }

    function delLabDetail(id) {
        $('#row_' + id).remove();
        var data = $('#table_lab').tableToJSON().length;
        if (data == 0) {
            $('#set_nama_unit').attr('disabled', false);
            $('#set_paket').attr('disabled', false);
            $('#set_paket_baru').attr('disabled', false);
            $('#set_kategori_lab').attr('disabled', false);
        }
    }

    function saveLab(id) {
        var data = "";
        if(!cekSession()){
            if (id != '') {
                var paket = $('#edit_paket').val();
                var idPaket = $('#h_edit_paket').val();
                var kategori = $('#edit_kategori_lab').val();
                var parameter = $('#edit_nama_parameter').val();
                var tarif = $('#edit_h_tarif').val();
                if (paket && kategori && parameter && tarif != '') {
                    $('#save_edit').hide();
                    $('#load_edit').show();
                    data = {
                        'id_lab_detail': id,
                        'id_lab': idPaket,
                        'nama_lab': paket,
                        'id_parameter_pemeriksaan': parameter,
                        'tarif': tarif,
                    };
                    var dataString = JSON.stringify(data);
                    dwr.engine.setAsync(true);
                    LabDetailAction.saveEdit(dataString, {
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

                    if (paket == '') {
                        $('#war_edit_paket').show();
                    }
                    if (kategori == '') {
                        $('#war_edit_kategori_lab').show();
                    }
                    if (parameter == '') {
                        $('#war_edit_nama_parameter').show();
                    }
                    if (tarif == '') {
                        $('#war_edit_tarif').show();
                    }
                }
            } else {
                var dataLab = $('#table_lab').tableToJSON();
                var branch = $('#set_nama_unit').val();
                var paket = $('#set_paket').val();
                var paketName = $('#set_paket option:selected').text();
                var kategori = $('#set_kategori_lab').val();
                var parameter = $('#set_nama_parameter').val();
                var paketLab = "";
                var isNew = "N";
                var newPaket = "";

                if (paket == "new") {
                    var ket = $('#set_paket_baru').val();
                    if (ket != '') {
                        paketLab = ket;
                        isNew = "Y";
                        newPaket = ket;
                    }
                } else {
                    paketLab = paket;
                }

                if (branch && paketLab && kategori && parameter != '' && dataLab.length > 0) {
                    var dataSave = [];
                    $('#save_add').hide();
                    $('#load_add').show();
                    $.each(dataLab, function (i, item) {
                        var idParameter = $('#id_parameter_' + i).val();
                        var namaParameter = $('#nama_parameter_' + i).val();
                        var tarif = $('#h_tarif_' + i).val();
                        dataSave.push({
                            'id_lab': paketLab,
                            'nama_lab': newPaket,
                            'nama_paket':paketName,
                            'id_parameter_pemeriksaan': idParameter,
                            'nama_parameter_pemeriksaan': namaParameter,
                            'tarif': tarif,
                            'branch_id': branch
                        });
                    });
                    var dataString = JSON.stringify(dataSave);
                    dwr.engine.setAsync(true);
                    LabDetailAction.saveAdd(dataString, isNew, {
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
                    if (branch == '') {
                        $('#war_set_nama_unit').show();
                    }
                    if (paketLab == '') {
                        if (paket == 'new') {
                            $('#war_set_paket_baru').show();
                        } else {
                            $('#war_set_paket').show();
                        }
                    }
                    if (kategori == '') {
                        $('#war_set_kategori_lab').show();
                    }
                    if (parameter == '') {
                        $('#war_set_nama_parameter').show();
                    }
                }
            }
        }
    }

    function getBranch() {
        var selectOne = '<option value="">[Select One]</option>';
        var option = '<option value="">[Select One]</option>';
        var idDef = "";
        var isDis = "";
        TindakanAction.getComboBranch(function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    if(i == 0){
                        idDef = item.branchId;
                    }
                    if(item.isDisabled == "Y"){
                        isDis = "Y";
                    }
                    option += '<option value="' + item.branchId + '">' + item.branchName + '</option>'
                });
            }
            if(isDis == "Y"){
                $('#set_nama_unit').html(option);
                $('#branch').html(option);
                $('#set_nama_unit').val(idDef).trigger('change');
                $('#branch').val(idDef).trigger('change');
                $('#set_nama_unit, #branch').attr('disabled', true);
                $('#h_branch_id').val(idDef);
            }else{
                $('#set_nama_unit').html(selectOne+option);
                $('#branch').html(selectOne+option);
            }
            $('#edit_nama_unit').html(option);
        });
    }

    function getLab() {
        var option = '<option value="">[Select One]</option>';
        LabDetailAction.getLab(function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idLab + '">' + item.namaLab + '</option>'
                });
            }
            $('#paket').html(option);
            // $('#edit_paket').html(option);
        });
    }

    function getLabKategori(id) {
        var branch = $('#set_nama_unit').val();
        var option = '<option value="">[Select One]</option>';
        LabDetailAction.getLabByKategori(id, branch, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idLab + '">' + item.namaLab + '</option>'
                });
            }
            option = option + '<option value="new">Lainnya</option>';
            $('#set_paket').html(option);
        });
    }

    function getParameter(kategori) {
        var option = '<option value="">[Select One]</option>';
        LabDetailAction.getComboParameter(kategori, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idParameterPemeriksaan + '">' + item.namaPemeriksaan + '</option>'
                });
            }
            $('#set_nama_parameter').html(option);
            $('#edit_nama_parameter').html(option);
        });
    }

    function getDataParameter(id) {
        LabDetailAction.initParameter(id, function (res) {
            if (res.idLabDetail != null) {
                $('#v_id_lab_detail').text(res.idLabDetail);
                $('#v_nama').text(res.namaDetailPeriksa);
                $('#v_kategori').text(res.namaKategoriLab);
                $('#v_paket').text(res.namaLab);
                $('#v_ket_acuan_l').text(res.keteranganAcuanL);
                $('#v_ket_acuan_p').text(res.keteranganAcuanP);
                $('#v_satuan').text(res.satuan);
                $('#v_tarif').text("Rp. " + formatRupiahAtas(res.tarif));

                $('#edit_nama_unit, #edit_kategori_lab').attr('disabled', true);
                $('#edit_nama_unit').val(res.branchId).trigger('change');
                $('#edit_paket').val(res.namaLab);
                $('#h_edit_paket').val(res.idLab);
                $('#edit_kategori_lab').val(res.idKategoriLab).trigger('change');
                $('#edit_nama_parameter').val(res.idParameterPemeriksaan).trigger('change');
                $('#edit_tarif').val(formatRupiahAtas(res.tarif));
                $('#edit_h_tarif').val(res.tarif);
            }
        });
    }

    function saveDelete(id) {
        $('#modal-confirm-dialog').modal('hide');
        $('#waiting_dialog').dialog('open');
        if(!cekSession()){
            dwr.engine.setAsync(true);
            LabDetailAction.saveDelete(id, {
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
    }

    function cekPaket(val) {
        if (val == 'new') {
            $('#form_set_paket_baru').show();
        } else {
            $('#form_set_paket_baru').hide();
        }
    }

    function setTarifParams(id) {
        ParameterPemeriksaanAction.initParameterPemeriksaan(id, function (res) {
            if (res.idParameterPemeriksaan != null) {
                $('#edit_tarif').val(formatRupiahAtas(res.tarif));
                $('#edit_h_tarif').val(res.tarif);
            }
        });
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>