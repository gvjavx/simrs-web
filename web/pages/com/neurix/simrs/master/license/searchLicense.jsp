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
            $('#lisence').addClass('active');
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
            Data License
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Data License</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="lisenceForm" method="post" namespace="/licenseapps"
                                    action="searchLicense_licenseapps.action"
                                    theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Lisence ID</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="lisence_id" name="licenseZebra.licenseId"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Lisence Key</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="license_key" name="licenseZebra.licenseKey"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Device ID</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="device_id" name="licenseZebra.deviceId"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="licenseZebra.flag"
                                                  headerKey="Y" headerValue="Active" cssClass="form-control select2"
                                                  cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="lisenceForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initLicense_licenseapps.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a onclick="showModal('add')" class="btn btn-primary"><i class="fa fa-plus"></i>
                                            Tambah Lisence</a>
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Lisence</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped tablePasien">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>Lisence ID</td>
                                <td>Device ID</td>
                                <td align="center">Status</td>
                                <td align="center" width="10%">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfLicense" var="row">
                                <tr>
                                    <td><s:property value="licenseId"/></td>
                                    <td><s:property value="deviceId"/></td>
                                    <td align="center">
                                        <s:if test='#row.status == "0"'>
                                            <span class="span-warning">menunggu aktivasi</span>
                                        </s:if>
                                        <s:elseif test='#row.status == "1"'>
                                            <span class="span-success">aktif</span>
                                        </s:elseif>
                                        <s:elseif test='#row.status == "2'>
                                            <span class="span-danger">ditolak</span>
                                        </s:elseif>
                                        <s:else>
                                            <span class="span-kuning">tidak ditemukan</span>
                                        </s:else>
                                    </td>
                                    <td align="center">
                                        <img class="hvr-grow"
                                             onclick="showModal('detail', '<s:property value="licenseId"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-view-25.png"/>">
                                        <%--<img class="hvr-grow"--%>
                                             <%--onclick="showModal('edit', '<s:property value="licenseId"/>')"--%>
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
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add"></p>
                </div>
                <div id="for_edit" style="display: none">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-3">Lisence ID</label>
                            <div class="col-md-7">
                                <input class="form-control" id="set_license_id" disabled>
                            </div>
                        </div>
                    </div>
                    <%--<div class="row jarak_atas">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="col-md-3">Lisence Key</label>--%>
                            <%--<div class="col-md-7">--%>
                                <%--<input class="form-control" id="set_license_key" disabled>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3">Device ID</label>
                        <div class="col-md-7">
                            <input class="form-control" id="set_device_id"
                                   oninput="var warn =$('#war_set_device_id').is(':visible'); if (warn){$('#cor_set_device_id').show().fadeOut(3000);$('#war_set_device_id').hide()}">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_device_id">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_device_id"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Detail Data Lisence</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-striped" style="font-size: 12px">
                                <tr>
                                    <td><b>Lisence ID</b></td>
                                    <td><span id="v_license_id"></span></td>
                                </tr>
                                <%--<tr>--%>
                                    <%--<td><b>Lisence Key</b></td>--%>
                                    <%--<td><span id="v_license_key"></span></td>--%>
                                <%--</tr>--%>
                                <tr>
                                    <td><b>Device ID</b></td>
                                    <td><span id="v_device_id"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Status</b></td>
                                    <td><span id="v_status"></span></td>
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
            $('#set_device_id').val('');
            $('#save_add').attr('onclick', 'saveLicense(\''+tipe+'\')');
            $('#set_judul').text("Tambah Lisence");
            $('#modal-add').modal({show: true, backdrop: 'static'});
        }
        if ('detail' == tipe) {
            $('#modal-view').modal({show: true, backdrop: 'static'});
            getDataLicense(id);
        }
        if ('edit' == tipe) {
            $('#save_add').attr('onclick', 'saveLicense(\'' + tipe + '\')');
            $('#set_judul').text("Edit Lisence");
            $('#modal-add').modal({show: true, backdrop: 'static'});
            getDataLicense(id);
        }
        if ('delete' == tipe) {
            $('#pesan').text("Do you want delete this record?");
            $('#modal-confirm-dialog').modal({show: true, backdrop: 'static'});
            $('#save_con').attr('onclick', 'saveDelete(\'' + tipe + '\')');
        }
    }

    function saveLicense(tipe) {
        if(!cekSession()){
            var licenseId = $('#set_license_id').val();
            var deviceId = $('#set_device_id').val();

            if (deviceId != '') {
                var data = {
                    'tipe': tipe,
                    'license_id': licenseId,
                    'device_id': deviceId
                };
                var dataString = JSON.stringify(data);
                $('#save_add').hide();
                $('#load_add').show();
                dwr.engine.setAsync(true);
                LicenseAction.saveLicense(dataString, {callback: function (res) {
                    if(res.status == "success"){
                        $('#modal-add').modal('hide');
                        $('#info_dialog').dialog('open');
                        $('#save_add').show();
                        $('#load_add').hide();
                    }else{
                        $('#warning_add').show().fadeOut(5000);
                        $('#msg_add').text(res.msg);
                        $('#save_add').show();
                        $('#load_add').hide();
                    }
                }});
            } else {
                $('#warning_add').show().fadeOut(5000);
                $('#msg_add').text("Silahkan cek kembali data inputan berikut...!");

                if (deviceId == '') {
                    $('#war_set_device_id').show();
                }
            }
        }
    }

    function getDataLicense(id) {
        LicenseAction.getDataLicense(id, function (res) {
            if (res.deviceId != null) {
                $('#v_license_id').text(res.licenseId);
                $('#v_license_key').text(res.licenseKey);
                $('#v_device_id').text(res.deviceId);
                $('#v_created_who').text(res.createdWho);
                $('#v_created_date').text(converterDateYmdHms(res.createdDate));
                $('#v_last_update').text(converterDateYmdHms(res.lastUpdate));
                $('#v_last_update_who').text(res.lastUpdateWho);
                var sts = '';
                if(res.status == "0"){
                    sts = '<span class="span-warning">menunggu aktivasi</span>';
                }else if(res.status == "1"){
                    sts = '<span class="span-success">aktif</span>';
                }else if(res.status == "2"){
                    sts = '<span class="span-danger">ditolak</span>';
                }else{
                    sts = '<span class="span-kuning">tidak ditemukan</span>';
                }
                $('#v_status').html(sts);

                $('#for_edit').show();
                $('#set_license_id').val(res.licenseId);
                $('#set_license_key').val(res.licenseKey);
                $('#set_device_id').val(res.deviceId);
            }else{
                $('#v_license_id, #v_license_key, #v_device_id, #v_created_who').text('');
                $('#v_created_date, #v_last_update, #v_last_update_who').text('');
                $('#v_status').html('');

                $('#set_license_id').val('');
                $('#set_license_key').val('');
                $('#set_device_id').val('');
            }
        });
    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>