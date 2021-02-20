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

    <script type='text/javascript' src='<s:url value="/dwr/interface/LicenseAction.js"/>'></script>
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#version').addClass('active');
        });

        $.subscribe('beforeProcessSave', function (event, data) {
            var versionId = $('#set_version_id').val();
            var tipe = $('#set_tipe').val();
            var os = $('#set_os').val();
            var versionName = $('#set_name_version').val();
            var deskripsi = $('#set_deskripsi').val();
            var cekFile = cekFileAtas('set_upload_apk');
            if(tipe && os && deskripsi != ''){
                if("zebra" == tipe){
                    if(!cekFile){
                        event.originalEvent.options.submit = true;
                        $('#save').hide();
                        $('#load_add').show();
                    }else{
                        event.originalEvent.options.submit = false;
                        $('#warning_add').show().fadeOut(5000);
                        $('#msg_add').text("Silahkan cek kembali data inputan berikut...!");
                        if (cekFile) {
                            $('#war_set_upload_apk').show();
                        }
                    }
                }else{
                    if(versionName != ''){
                        event.originalEvent.options.submit = true;
                        $('#save').hide();
                        $('#load_add').show();
                    }else{
                        event.originalEvent.options.submit = false;
                        $('#warning_add').show().fadeOut(5000);
                        $('#msg_add').text("Silahkan cek kembali data inputan berikut...!");
                        if(versionName == ''){
                            $('#war_set_name_version').show();
                        }
                    }
                }
            }else{
                event.originalEvent.options.submit = false;
                $('#warning_add').show().fadeOut(5000);
                $('#msg_add').text("Silahkan cek kembali data inputan berikut...!");
                if (tipe == '') {
                    $('#war_set_tipe').show();
                }
                if (os == '') {
                    $('#war_set_os').show();
                }
                if (deskripsi == '') {
                    $('#war_set_deskripsi').show();
                }
            }
        });

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $('#modal-add').modal('hide');
                $.publish('showInfoDialog');
                $('#save').show();
                $('#load_add').hide();
            }
        });

        $.subscribe('errorDialog', function (event, data) {
            $('#warning_add').show().fadeOut(5000);
            $('#msg_add').text(event.originalEvent.request.getResponseHeader('message'));
            $('#save').show();
            $('#load_add').hide();
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
            Data Version
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Data Version</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="versionForm" method="post" namespace="/version"
                                    action="searchVersion_version.action"
                                    theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Version ID</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="lisence_id" name="version.idVersion"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Version Name</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="license_key" name="version.versionName"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tipe Version</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'mobile':'Mobile', 'zebra':'Zebra'}" id="flag" name="version.tipe"
                                                  headerKey="" headerValue="[Select One]" cssClass="form-control select2"
                                                  cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="version.flag"
                                                  headerKey="Y" headerValue="Active" cssClass="form-control select2"
                                                  cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="versionForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initVersion_version.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a onclick="showModal('add')" class="btn btn-primary"><i class="fa fa-plus"></i>
                                            Tambah Version</a>
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Version</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped tablePasien">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Version ID</td>
                                <td>Name Version</td>
                                <td>Tipe</td>
                                <td align="center" width="10%">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfVersion" var="row">
                                <tr>
                                    <td><s:property value="idVersion"/></td>
                                    <td><s:property value="versionName"/></td>
                                    <td><s:property value="tipe"/></td>
                                    <td align="center">
                                        <img class="hvr-grow"
                                             onclick="showModal('detail', '<s:property value="idVersion"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-view-25.png"/>">
                                            <%--<img class="hvr-grow"--%>
                                            <%--onclick="showModal('edit', '<s:property value="idVersion"/>')"--%>
                                            <%--style="cursor: pointer"--%>
                                            <%--src="<s:url value="/pages/images/icons8-create-25.png"/>">--%>
                                            <%--<img class="hvr-grow"--%>
                                            <%--onclick="showModal('delete', '<s:property value="idHeaderTindakan"/>')"--%>
                                            <%--style="cursor: pointer"--%>
                                            <%--src="<s:url value="/pages/images/cancel-flat-new.png"/>">--%>
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-desktop"></i> <span id="set_judul"></span>
                </h4>
            </div>
            <div class="modal-body">
                <s:form id="addForm" enctype="multipart/form-data" namespace="/pasien"
                        action="saveVersion_version.action" method="post">
                    <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                        <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                        <p id="msg_add"></p>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 jarak_atas">Tipe Version</label>
                            <div class="col-md-7">
                                <select id="set_tipe" style="width: 100%" onchange="inputWarning('war_set_tipe', 'cor_set_tipe'); setTipe(this.value)" class="form-control select2" name="version.tipe">
                                    <option value="">[Select One]</option>
                                    <option value="mobile">Mobile</option>
                                    <option value="zebra">Zebra</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                   id="war_set_tipe">
                                    <i class="fa fa-times"></i> required</p>
                                <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                   id="cor_set_tipe"><i class="fa fa-check"></i> correct</p>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak_atas" style="display: none" id="from_upload">
                        <div class="form-group">
                            <label class="col-md-3">Upload APK</label>
                            <div class="col-md-7">
                                <div class="input-group">
                                <span class="input-group-btn">
                                    <span class="btn btn-default btn-file">Browse…
                                        <input type="file" id="set_upload_apk" name="fileUpload"
                                               onchange="setNameFile('nama_file'); inputWarning('war_set_upload_apk', 'cor_set_upload_apk')">
                                    </span>
                                </span>
                                    <input type="text" class="form-control" readonly id="nama_file">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                   id="war_set_upload_apk">
                                    <i class="fa fa-times"></i> required</p>
                                <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                   id="cor_set_upload_apk"><i class="fa fa-check"></i> correct</p>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak_atas" style="display: none;" id="form_name_version">
                        <div class="form-group">
                            <label class="col-md-3">Version Name</label>
                            <div class="col-md-7">
                                <input name="version.versionName"
                                          oninput="inputWarning('war_set_name_version', 'cor_set_name_version')"
                                          class="form-control" id="set_name_version">
                            </div>
                            <div class="col-md-2">
                                <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                   id="war_set_name_version">
                                    <i class="fa fa-times"></i> required</p>
                                <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                   id="cor_set_name_version"><i class="fa fa-check"></i> correct</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3 jarak_atas">Operating System</label>
                            <div class="col-md-7">
                                <select id="set_os" style="width: 100%" onchange="inputWarning('war_set_os', 'cor_set_os');" class="form-control select2" name="version.os">
                                    <option value="">[Select One]</option>
                                    <option value="android">Android</option>
                                    <option value="ios">IOS</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                   id="war_set_os">
                                    <i class="fa fa-times"></i> required</p>
                                <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                   id="cor_set_os"><i class="fa fa-check"></i> correct</p>
                            </div>
                        </div>
                    </div>
                    <div class="row jarak_atas">
                        <div class="form-group">
                            <label class="col-md-3">Deskripsi</label>
                            <div class="col-md-7">
                                <textarea name="version.description"
                                          oninput="inputWarning('war_set_deskripsi', 'cor_set_deskripsi')"
                                          class="form-control" rows="10" id="set_deskripsi"></textarea>
                            </div>
                            <div class="col-md-2">
                                <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                                   id="war_set_deskripsi">
                                    <i class="fa fa-times"></i> required</p>
                                <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                                   id="cor_set_deskripsi"><i class="fa fa-check"></i> correct</p>
                            </div>
                        </div>
                    </div>
                    <sj:submit cssStyle="margin-left: 147px; margin-top: 20px" targets="crud" type="button"
                               cssClass="btn btn-success"
                               formIds="addForm" id="save" name="save"
                               onBeforeTopics="beforeProcessSave"
                               onCompleteTopics="closeDialog,successDialog"
                               onSuccessTopics="successDialog" onErrorTopics="errorDialog">
                        <i class="fa fa-check"></i>
                        Save
                    </sj:submit>
                    <button style="display: none; cursor: no-drop; margin-left: 147px; margin-top: 20px" type="button"
                            class="btn btn-success" id="load_add"><i
                            class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                    </button>
                </s:form>
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-desktop"></i> Edit Verison</span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_edit">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_edit"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3">Verison ID</label>
                        <div class="col-md-7">
                            <input class="form-control" id="edit_version_id" disabled>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3">Version Name</label>
                        <div class="col-md-7">
                            <input class="form-control" id="edit_version_name" disabled>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3">Deskripsi</label>
                        <div class="col-md-7">
                            <textarea name="version.description"
                                      oninput="inputWarning('war_edit_deskripsi', 'cor_edit_deskripsi')"
                                      class="form-control" rows="10" id="edit_deskripsi"></textarea>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_edit_deskripsi">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_edit_deskripsi"><i class="fa fa-check"></i> correct</p>
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


<div class="modal fade" id="modal-view">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Detail Data Lisence</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-striped" style="font-size: 12px">
                                <tr>
                                    <td><b>Version ID</b></td>
                                    <td><span id="v_version_id"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Version Name</b></td>
                                    <td><span id="v_version_name"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Tipe</b></td>
                                    <td><span id="v_tipe"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Operating System</b></td>
                                    <td><span id="v_os"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Deskripsi</b></td>
                                    <td><span id="v_deskripsi"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Created Who</b></td>
                                    <td><span id="v_created_who"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Created Date</b></td>
                                    <td><span id="v_created_date"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Last Update Who</b></td>
                                    <td><span id="v_last_update_who"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Last Update</b></td>
                                    <td><span id="v_last_update"></span></td>
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

    function showModal(tipe, id) {
        if ('add' == tipe) {
            $('#for_edit').hide();
            $('#for_edit-2').show();
            $('#set_device_id').val('');
            $('#set_judul').text("Tambah Version");
            $('#modal-add').modal({show: true, backdrop: 'static'});
        }
        if ('detail' == tipe) {
            $('#modal-view').modal({show: true, backdrop: 'static'});
            getDataVersion(id);
        }
        if ('edit' == tipe) {
            $('#for_edit-2').hide();
            $('#save_edit').attr('onclick', 'saveVersion(\'' + tipe + '\')');
            $('#modal-edit').modal({show: true, backdrop: 'static'});
            getDataVersion(id);
        }
        if ('delete' == tipe) {
            $('#pesan').text("Do you want delete this record?");
            $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
            $('#save_con').attr('onclick', 'saveVersion(\'' + tipe + '\')');
        }
    }

    function saveVersion(tipe) {
        if (!cekSession()) {
            var versionId = $('#edit_version_id').val();
            var deskripsi = $('#edit_deskripsi').val();
            var cekFile = false;
            if (tipe == 'add') {
                cekFile = cekFileAtas('set_upload_apk');
            }
            if (!cekFile && deskripsi != '') {
                var data = {
                    'tipe': tipe,
                    'version_id': versionId,
                    'deskripsi': deskripsi
                };
                var dataString = JSON.stringify(data);
                $('#save_edit').hide();
                $('#load_edit').show();
                dwr.engine.setAsync(true);
                LicenseAction.saveVersion(dataString, {
                    callback: function (res) {
                        if (res.status == "success") {
                            $('#modal-edit').modal('hide');
                            $('#info_dialog').dialog('open');
                            $('#save_edit').show();
                            $('#load_edit').hide();
                        } else {
                            $('#warning_edit').show().fadeOut(5000);
                            $('#msg_edit').text(res.msg);
                            $('#save_edit').show();
                            $('#load_edit').hide();
                        }
                    }
                });
            } else {
                $('#warning_edit').show().fadeOut(5000);
                $('#msg_edit').text("Silahkan cek kembali data inputan berikut...!");
                if (cekFile) {
                    $('#war_edit_upload_apk').show();
                }
                if (deskripsi == '') {
                    $('#war_edit_deskripsi').show();
                }
            }
        }
    }

    function getDataVersion(id) {
        LicenseAction.getDataVersion(id, function (res) {
            if (res.idVersion != null) {
                $('#v_version_id').text(res.idVersion);
                $('#v_version_name').text(res.versionName);
                $('#v_tipe').text(res.tipe.toUpperCase());
                $('#v_os').text(res.os.toUpperCase());
                $('#v_deskripsi').text(res.description);
                $('#v_created_who').text(res.createdWho);
                $('#v_created_date').text(converterDateYmdHms(res.createdDate));
                $('#v_last_update').text(converterDateYmdHms(res.lastUpdate));
                $('#v_last_update_who').text(res.lastUpdateWho);

                $('#edit_version_id').val(res.idVersion);
                $('#edit_version_name').val(res.versionName);
                $('#edit_deskripsi').val(res.description);
            }
        });
    }

    function setNameFile(id) {
        var reader = new FileReader();
        reader.readAsDataURL(event.target.files[0]);
        $('#' + id).val(event.target.files[0].name);
    }

    function setTipe(val){
        if(val != ''){
            if("zebra" == val){
                $('#form_name_version').hide();
                $('#from_upload').show();
                $('#set_name_version').val('');
            }else{
                $('#form_name_version').show();
                $('#from_upload').hide();
                $('#set_upload_apk').val('');
            }
        }else{
            $('#form_name_version').hide();
            $('#from_upload').hide();
            $('#set_upload_apk').val('');
        }
    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>