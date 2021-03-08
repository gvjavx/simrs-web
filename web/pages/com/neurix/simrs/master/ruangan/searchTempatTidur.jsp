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
    <script type='text/javascript' src='<s:url value="/dwr/interface/TempatTidurAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupDetailAction.js"/>'></script>

    <script type='text/javascript'>

        $(document).ready(function () {
            getKelasKamar();
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
            Data Tempat Tidur Ruangan
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Tempat Tidur Ruangan</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="tempattidurForm" method="post" namespace="/tempattidur"
                                    action="search_tempattidur.action"
                                    theme="simple" cssClass="form-horizontal">
                                <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-4">Unit</label>--%>
                                <%--<div class="col-sm-4">--%>
                                <%--<select class="form-control select2" id="branch" name="labDetail.branchId"--%>
                                <%--onchange="getPelayanan(this.value)">--%>
                                <%--<option value="">[Select One]</option>--%>
                                <%--</select>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Kelas Ruangan</label>
                                    <div class="col-sm-4">
                                        <select style="margin-top: 7px" class="form-control select2" id="kelas_kamar"
                                                name="tempatTidur.idKelasRuangan"
                                                onchange="listSelectRuangan(this.value)">
                                            <option value=''>[Select One]</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Ruangan</label>
                                    <div class="col-sm-4">
                                        <select class="form-control select2" id="ruangan" name="tempatTidur.idRuangan"
                                                style="width: 100%">
                                            <option value="">[Select One]</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Tempat Tidur</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_tt" name="tempatTidur.idTempatTidur"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Tempat Tidur</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_tt" name="tempatTidur.namaTempatTidur"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="tempatTidur.flag"
                                                  headerKey="Y" headerValue="Active" cssClass="form-control select2"
                                                  cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="tempattidurForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_tempattidur.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a onclick="showModal('add')" class="btn btn-primary"><i class="fa fa-plus"></i>
                                            Tambah Tempat Tidur</a>
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Tempat Tidur Ruangan</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Tempat Tidur</td>
                                <td>Kelas Ruangan</td>
                                <td>Ruangan</td>
                                <td>Nama Tempat Tidur</td>
                                <td align="center">Status</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idTempatTidur"/></td>
                                    <td><s:property value="namaKelasRuangan"/></td>
                                    <td><s:property value="noRuangan"/> <s:property value="namaRuangan"/></td>
                                    <td><s:property value="namaTempatTidur"/></td>
                                    <td align="center">
                                        <s:if test='#row.status == "Y"'>
                                            <span style="color: white; background-color: #0F9E5E; padding: 4px; border-radius: 5px">
                                                Tersedia
                                            </span>
                                        </s:if>
                                        <s:else>
                                            <span style="color: white; background-color: #d33724; padding: 4px; border-radius: 5px">
                                                Tidak Tersedia
                                            </span>
                                        </s:else>
                                    </td>
                                    <td align="center" width="10%">
                                        <img class="hvr-grow"
                                             onclick="showModal('detail', '<s:property value="idTempatTidur"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-view-25.png"/>">
                                        <img class="hvr-grow"
                                             onclick="showModal('edit', '<s:property value="idTempatTidur"/>', '<s:property value="idKelasRuangan"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-create-25.png"/>">
                                        <img class="hvr-grow"
                                             onclick="showModal('delete', '<s:property value="idTempatTidur"/>')"
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Tambah Tempat Tidur Ruangan
                </h4>
            </div>
            <div class="modal-body" id="temp_scrol">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Kelas Ruangan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="set_id_kelas" style="width: 100%"
                                    onchange="var warn =$('#war_set_id_kelas').is(':visible'); if (warn){$('#cor_set_id_kelas').show().fadeOut(3000);$('#war_set_id_kelas').hide()}; listSelectRuangan(this.value, 'Y')"></select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_id_kelas">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_id_kelas"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Ruangan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="set_id_ruangan" style="width: 100%"
                                    onchange="var warn =$('#war_set_id_ruangan').is(':visible'); if (warn){$('#cor_set_id_ruangan').show().fadeOut(3000);$('#war_set_id_ruangan').hide()}; cekRuangan(this.value)">
                                <option value="">[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_id_ruangan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_id_ruangan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div id="form_set_ruangan_baru" style="display: none">
                    <div class="row jarak_atas">
                        <div class="form-group">
                            <label class="col-md-3">No Ruangan</label>
                            <div class="col-md-7">
                                <input class="form-control" id="set_no_ruangan"
                                       oninput="var warn =$('#war_set_no_ruangan').is(':visible'); if (warn){$('#cor_set_no_ruangan').show().fadeOut(3000);$('#war_set_no_ruangan').hide()}">
                            </div>
                            <div class="col-md-2">
                                <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                   id="war_set_no_ruangan">
                                    <i class="fa fa-times"></i> required</p>
                                <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                   id="cor_set_no_ruangan"><i class="fa fa-check"></i> correct</p>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak_atas">
                        <div class="form-group">
                            <label class="col-md-3">Ruangan Baru</label>
                            <div class="col-md-7">
                                <input class="form-control" id="set_ruangan_baru"
                                       oninput="var warn =$('#war_set_ruangan_baru').is(':visible'); if (warn){$('#cor_set_ruangan_baru').show().fadeOut(3000);$('#war_set_ruangan_baru').hide()}">
                            </div>
                            <div class="col-md-2">
                                <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                   id="war_set_ruangan_baru">
                                    <i class="fa fa-times"></i> required</p>
                                <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                   id="cor_set_ruangan_baru"><i class="fa fa-check"></i> correct</p>
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
                                    <input class="form-control" id="set_tarif"
                                           oninput="var warn =$('#war_set_tarif').is(':visible'); if (warn){$('#cor_set_tarif').show().fadeOut(3000);$('#war_set_tarif').hide()}; convertRpAtas(this.id, this.value, 'h_tarif')">
                                    <input type="hidden" id="h_tarif">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                   id="war_set_tarif">
                                    <i class="fa fa-times"></i> required</p>
                                <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                   id="cor_set_tarif"><i class="fa fa-check"></i> correct</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3">Nama Tempat Tidur</label>
                        <div class="col-md-7">
                            <input class="form-control" id="set_nama_tt"
                                   oninput="var warn =$('#war_set_nama_tt').is(':visible'); if (warn){$('#cor_set_nama_tt').show().fadeOut(3000);$('#war_set_nama_tt').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_nama_tt">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_nama_tt"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-offset-3 col-md-6">
                        <a class="btn btn-success" onclick="addToList()"><i class="fa fa-plus"></i> Tambah</a>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="col-md-12">
                        <table id="table_tempat_tidur" class="table table-bordered table-striped" style="font-size: 12px">
                            <thead>
                            <tr style="font-weight: bold">
                                <td >Nama Tempat Tidur</td>
                                <td align="center" width="15%">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_tt"></tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <span onclick="cekScrol('fa_scrol', 'temp_scrol')" class="pull-left hvr-grow" style="color: black; margin-top: 11px">
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Detail Tempat Tidur Ruangan</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-striped" style="font-size: 12px">
                                <tr>
                                    <td width="40%"><b>ID Tempat Tidur</b></td>
                                    <td><span id="v_id"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama Tempat Tidur</b></td>
                                    <td><span id="v_tt"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama Ruangan</b></td>
                                    <td><span id="v_ruangan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kelas Ruangan</b></td>
                                    <td><span id="v_kelas"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Status</b></td>
                                    <td><span id="v_status"></span></td>
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Edit Tempat Tidur
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_edit">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_edit"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Kelas Ruangan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="edit_id_kelas" style="width: 100%"
                                    onchange="var warn =$('#war_edit_id_kelas').is(':visible'); if (warn){$('#cor_edit_id_kelas').show().fadeOut(3000);$('#war_edit_id_kelas').hide()}; listSelectRuangan(this.value, 'Y')"></select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_id_kelas">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_id_kelas"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Ruangan</label>
                        <div class="col-md-7">
                            <select class="form-control select2" id="edit_id_ruangan" style="width: 100%"
                                    onchange="var warn =$('#war_edit_id_ruangan').is(':visible'); if (warn){$('#cor_edit_id_ruangan').show().fadeOut(3000);$('#war_edit_id_ruangan').hide()}; cekRuangan(this.value)">
                                <option value="">[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_id_ruangan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_id_ruangan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3">Nama Tempat Tidur</label>
                        <div class="col-md-7">
                            <input class="form-control" id="edit_nama_tt"
                                   oninput="var warn =$('#war_edit_nama_tt').is(':visible'); if (warn){$('#cor_edit_nama_tt').show().fadeOut(3000);$('#war_edit_nama_tt').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_nama_tt">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_nama_tt"><i class="fa fa-check"></i> correct</p>
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

    function showModal(tipe, id, idkelas) {
        if ('add' == tipe) {
            $('#set_id_kelas').attr('disabled', false);
            $('#set_id_ruangan').attr('disabled', false);
            $('#set_no_ruangan').attr('disabled', false);
            $('#set_ruangan_baru').attr('disabled', false);
            $('#set_tarif').attr('disabled', false);
            $('#save_add').attr('onclick', 'saveTT(\'\')');
            $('#modal-add').modal({show: true, backdrop: 'static'});
            $('#body_tt').html('');
            $('#save_add').show();
            $('#load_add').hide();
        }
        if ('detail' == tipe) {
            getDataTT(id);
            $('#modal-view').modal({show: true, static: 'backdrop'});
        }
        if ('edit' == tipe) {
            listSelectRuangan(idkelas);
            $('#save_edit').attr('onclick', 'saveTT(\'' + id + '\')');
            $('#modal-edit').modal({show: true, static: 'backdrop'});
            getDataTT(id);
        }
        if ('delete' == tipe) {
            $('#pesan').text('Do you want delete this record?');
            $('#modal-confirm-dialog').modal({show: true, static: 'static'});
            $('#save_con').attr('onclick', 'saveDelete(\'' + id + '\')');
        }
    }

    function addToList() {
        var data = $('#table_tempat_tidur').tableToJSON();
        var id = data.length;
        var idKelas = $('#set_id_kelas').val();
        var idRuangan = $('#set_id_ruangan').val();
        var namaTT = $('#set_nama_tt').val();
        var no = $('#set_no_ruangan').val();
        var nama = $('#set_ruangan_baru').val();
        var tarif = $('#h_tarif').val();
        var ruangan = "";
        if (idRuangan == "new") {
            if (no && nama && tarif != '') {
                ruangan = nama;
            }
        } else {
            ruangan = idRuangan;
        }

        if (idKelas && ruangan && namaTT != '') {
            var cek = false;
            $.each(data, function (i, item) {
                var TT = $('#id_tt_' + i).val();
                if (namaTT == TT) {
                    cek = true;
                }
            });
            if (cek) {
                $('#warning_add').show().fadeOut(5000);
                $('#msg_add').text("Data sudah ada dalam list...!");
            } else {
                var lengTT = namaTT.length;
                var charTT = "";
                if(lengTT > 2){
                    charTT = namaTT.substring(0,2);
                }else{
                    charTT = namaTT;
                }
                var table = '<tr id="row_' + charTT+id + '">' +
                    '<td style="vertical-align: middle">' + namaTT +
                    '<input id="id_tt_' + id + '" type="hidden" value="' + namaTT + '"></td>' +
                    '<td style="vertical-align: middle" align="center">' +
                    '<img class="hvr-grow" onclick="delTT(\'' + charTT+id + '\')" src="<s:url value='/pages/images/cancel-flat-new.png'/>">' +
                    '</td>'
                '</tr>';
                $('#body_tt').append(table);
                $('#set_id_kelas').attr('disabled', true);
                $('#set_id_ruangan').attr('disabled', true);
                $('#set_no_ruangan').attr('disabled', true);
                $('#set_ruangan_baru').attr('disabled', true);
                $('#set_tarif').attr('disabled', true);
            }
        } else {
            $('#warning_add').show().fadeOut(5000);
            $('#msg_add').text("Silahkan cek kembali data inputan berikut...!");
            if (idKelas == '') {
                $('#war_set_id_kelas').show();
            }
            if (ruangan == '') {
                if (idRuangan == 'new') {
                    if(no == ''){
                        $('#war_set_no_ruangan').show();
                    }
                    if(nama == ''){
                        $('#war_set_ruangan_baru').show();
                    }
                    if(tarif == ''){
                        $('#war_set_tarif').show();
                    }
                } else {
                    $('#war_set_id_ruangan').show();
                }
            }
            if (namaTT == '') {
                $('#war_set_nama_tt').show();
            }
        }
    }

    function delTT(id) {
        $('#row_' + id).remove();
        var data = $('#table_tempat_tidur').tableToJSON().length;
        if (data == 0) {
            $('#set_id_kelas').attr('disabled', false);
            $('#set_id_ruangan').attr('disabled', false);
            $('#set_no_ruangan').attr('disabled', false);
            $('#set_ruangan_baru').attr('disabled', false);
            $('#set_tarif').attr('disabled', false);
        }
    }

    function saveTT(id) {
        var data = "";
        if (id != '') {
            var idKelas = $('#edit_id_kelas').val();
            var idRuangan = $('#edit_id_ruangan').val();
            var namaTT = $('#edit_nama_tt').val();
            if (idKelas && idRuangan && namaTT != '') {
                $('#save_edit').hide();
                $('#load_edit').show();
                data = {
                    'id_tempat_tidur': id,
                    'nama_tempat_tidur': namaTT
                };
                var dataString = JSON.stringify(data);
                dwr.engine.setAsync(true);
                TempatTidurAction.saveEdit(dataString, {
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

                if (idKelas == '') {
                    $('#war_edit_id_kelas').show();
                }
                if (idRuangan == '') {
                    $('#war_edit_id_ruangan').show();
                }
                if (namaTT == '') {
                    $('#war_edit_nama_tt').show();
                }
            }
        } else {
            var data = $('#table_tempat_tidur').tableToJSON();
            var id = data.length;
            var idKelas = $('#set_id_kelas').val();
            var idRuangan = $('#set_id_ruangan').val();
            var namaTT = $('#set_nama_tt').val();
            var no = $('#set_no_ruangan').val();
            var nama = $('#set_ruangan_baru').val();
            var tarif = $('#h_tarif').val();
            var ruangan = "";
            var isNew = "N";
            if (idRuangan == "new") {
                if (no && nama && tarif != '') {
                    ruangan = nama;
                    isNew = "Y";
                }
            } else {
                ruangan = idRuangan;
            }

            if (idKelas && idRuangan && namaTT != '' && data.length > 0) {
                var dataSave = [];
                $('#save_add').hide();
                $('#load_add').show();
                $.each(data, function (i, item) {
                    var TT = $('#id_tt_' + i).val();
                    dataSave.push({
                        'nama_tempat_tidur': TT,
                        'no_ruangan':no,
                        'id_kelas_ruangan':idKelas,
                        'id_ruangan': ruangan,
                        'nama_ruangan': nama,
                        'tarif': tarif
                    });
                });
                var dataString = JSON.stringify(dataSave);
                dwr.engine.setAsync(true);
                TempatTidurAction.saveAdd(dataString, isNew, {
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
                if (idKelas == '') {
                    $('#war_set_id_kelas').show();
                }
                if (ruangan == '') {
                    if (idRuangan == 'new') {
                        if(no == ''){
                            $('#war_set_no_ruangan').show();
                        }
                        if(nama == ''){
                            $('#war_set_ruangan_baru').show();
                        }
                        if(tarif == ''){
                            $('#war_set_tarif').show();
                        }
                    } else {
                        $('#war_set_id_ruangan').show();
                    }
                }
                if (namaTT == '') {
                    $('#war_set_nama_tt').show();
                }
            }
        }
    }

    function getKelasKamar() {
        var option = '<option value="">[Select One]</option>';
        var kelas = '<s:property value="tempatTidur.idKelasRuangan"/>';
        dwr.engine.setAsync(true);
        CheckupDetailAction.getListKelasKamar(null, function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idKelasRuangan + '">' + item.namaKelasRuangan + '</option>';
                });
                $('#kelas_kamar').html(option);
                $('#kelas_kamar').val(kelas).trigger('change');
                $('#set_id_kelas').html(option);
                $('#edit_id_kelas').html(option);
            } else {
                $('#kelas_kamar').html(option);
            }
        });
    }

    function listSelectRuangan(id, add) {
        var option = "<option value=''>[Select One]</option>";
        var ruang = '<s:property value="tempatTidur.idRuangan"/>';
        TempatTidurAction.getRuanganByBranch(id, {
            callback: function (response) {
                if (response.length > 0) {
                    $.each(response, function (i, item) {
                        option += "<option value='" + item.idRuangan + "'>" + item.namaRuangan + "</option>";
                    });
                    if(add == "Y"){
                        option = option + "<option value='new'>Lainnya</option>";
                        $('#set_id_ruangan').html(option);
                    }else{
                        $('#ruangan').html(option);
                        $('#ruangan').val(ruang).trigger('change');
                        $('#edit_id_ruangan').html(option);
                    }
                } else {
                    $('#ruangan').html(option);
                }
            }
        });
    }

    function getDataTT(id) {
        TempatTidurAction.initTempatTidur(id, function (res) {
            if (res.idTempatTidur != null) {
                $('#v_id').text(res.idTempatTidur);
                $('#v_tt').text(res.namaTempatTidur);
                $('#v_ruangan').text(res.namaRuangan);
                $('#v_kelas').text(res.namaKelasRuangan);
                if(res.status == "Y"){
                    $('#v_status').attr('style','color: white; background-color: #0F9E5E; padding: 4px; border-radius: 5px');
                    $('#v_status').text('Tersedia');
                }else{
                    $('#v_status').attr('style','color: white; background-color: #d33724; padding: 4px; border-radius: 5px');
                    $('#v_status').text('Tidak Tersedia');
                }

                $('#edit_id_kelas, #edit_id_ruangan, #edit_kategori_lab').attr('disabled', true);
                $('#edit_id_kelas').val(res.idKelasRuangan).trigger('change');
                $('#edit_id_ruangan').val(res.idRuangan).trigger('change');
                $('#edit_nama_tt').val(res.namaTempatTidur);
            }
        });
    }

    function saveDelete(id) {
        $('#modal-confirm-dialog').modal('hide');
        $('#waiting_dialog').dialog('open');
        dwr.engine.setAsync(true);
        TempatTidurAction.saveDelete(id, {
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

    function cekRuangan(val) {
        if (val == 'new') {
            $('#form_set_ruangan_baru').show();
        } else {
            $('#form_set_ruangan_baru').hide();
        }
    }

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>