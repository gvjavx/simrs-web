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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PelayananAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/DokterAction.js"/>'></script>

    <script type='text/javascript'>

        $(document).ready(function () {
            $('#tindakan').addClass('active');
            var branchId = '<s:property value="dokter.branchId"/>';
            listPelayanan(branchId);
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
            Data Dokter
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Data Dokter</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="dokterForm" method="post"  theme="simple" namespace="/dokter"
                                    action="search_dokter.action" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID Dokter</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="id_dokter" name="dokter.idDokter"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Nama Dokter</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="nama_dokter" name="dokter.namaDokter"
                                                     required="false" readonly="false"
                                                     cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-4">Branch / Unit</label>
                                    <div class="col-sm-4">
                                        <s:if test='dokter.branchId != null'>
                                            <s:action id="initComboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                            <s:select list="#initComboBranch.listOfComboBranches" disabled="true" name="dokter.branchId"
                                                      listKey="branchId" listValue="branchName" onchange="listPelayanan(this.value)"
                                                      headerKey="" headerValue=" - " cssClass="form-control select2"/>
                                            <s:hidden name="dokter.branchId"></s:hidden>
                                        </s:if>
                                        <s:else>
                                            <s:action id="initComboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                            <s:select list="#initComboBranch.listOfComboBranches" id="branch-id"
                                                      listKey="branchId" listValue="branchName" onchange="listPelayanan(this.value)"
                                                      headerKey="" headerValue=" - " cssClass="form-control select2"/>
                                        </s:else>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-4">Pelayanan</label>
                                    <div class="col-sm-4">
                                        <select class="form-control select2" id="list-pelayanan" name="dokter.idPelayanan">
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="dokter.flag"
                                                  headerKey="Y" headerValue="Active" cssClass="form-control select2"
                                                  cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="dokterForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_dokter.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
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
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Dokter</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped" style="font-size: 13px;">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>ID Dokter</td>
                                <td>Nama Dokter</td>
                                <td>Kode DPJP</td>
                                <td>SIP</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="idDokter"/></td>
                                    <td><s:property value="namaDokter"/></td>
                                    <td><s:property value="kodeDpjp"/></td>
                                    <td><s:property value="sip"/></td>
                                    <td align="center" width="10%">
                                        <img class="hvr-grow"
                                             onclick="showModal('detail', '<s:property value="idDokter"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-view-25.png"/>">
                                        <img class="hvr-grow"
                                             onclick="showModal('edit', '<s:property value="idDokter"/>', '<s:property
                                                     value="branchId"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-create-25.png"/>">
                                        <%--<img class="hvr-grow"--%>
                                             <%--onclick="showModal('delete', '<s:property value="idDokter"/>')"--%>
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

<div class="modal fade" id="modal-edit">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Edit Data Dokter
                </h4>
            </div>
            <div class="modal-body" id="temp_scrol">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_edit">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_edit"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3">ID Dokter</label>
                        <div class="col-md-7">
                            <input readonly id="set_id_dokter" class="form-control" oninput="inputWarning('war_set_id_dokter','cor_set_id_doktert')">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_id_dokter">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_id_doktert"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3">Kode DPJP</label>
                        <div class="col-md-7">
                            <input  id="set_kode_dpjp" class="form-control" oninput="inputWarning('war_set_kode_dpjp','cor_set_kode_dpjpt')">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_kode_dpjp">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_kode_dpjpt"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3">SIP</label>
                        <div class="col-md-7">
                            <input  id="set_sip" class="form-control" oninput="inputWarning('war_set_sip','cor_set_sipt')">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_sip">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_sipt"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3">Nama Dokter</label>
                        <div class="col-md-7">
                            <input  id="set_nama_dokter" class="form-control" oninput="inputWarning('war_set_nama_dokter','cor_set_nama_doktert')">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_nama_dokter">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_nama_doktert"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3">Kuota Online</label>
                        <div class="col-md-7">
                            <input type="number" id="set_kuota_online" class="form-control" oninput="inputWarning('war_set_kuota_online','cor_set_kuota_onlinet')">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_kuota_online">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_kuota_onlinet"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3">Kuota Telemedic</label>
                        <div class="col-md-7">
                            <input type="number" id="set_kuota_telemedic" class="form-control" oninput="inputWarning('war_set_kuota_telemedic','cor_set_kuota_telemedict')">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_kuota_telemedic">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_kuota_telemedict"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3">Kuota Non BPJS</label>
                        <div class="col-md-7">
                            <input type="number" id="set_kuota_no_bpjs" class="form-control" oninput="inputWarning('war_set_kuota_no_bpjs','cor_set_kuota_no_bpjst')">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_kuota_no_bpjs">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_kuota_no_bpjst"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3">Kuota BPJS</label>
                        <div class="col-md-7">
                            <input type="number" id="set_kuota_bpjs" class="form-control" oninput="inputWarning('war_set_kuota_bpjs','cor_set_kuota_bpjst')">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_kuota_bpjs">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_kuota_bpjst"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3">Flag Call</label>
                        <div class="col-md-1">
                            <div class="custom02">
                                <input class="radio_remove" onclick="inputWarning('war_set_flag_call', 'cor_set_flag_call')" type="radio" value="Y" id="set_flag_call1" name="set_flag_call" /><label for="set_flag_call1" >Ya</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="custom02" >
                                <input class="radio_remove" onclick="inputWarning('war_set_flag_call', 'cor_set_flag_call')" type="radio" value="N" id="set_flag_call2" name="set_flag_call" /><label for="set_flag_call2" >Tidak</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_flag_call">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_flag_callt"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3">Flag Telemedic</label>
                        <div class="col-md-1">
                            <div class="custom02">
                                <input class="radio_remove" onclick="inputWarning('war_set_flag_tele', 'cor_set_flag_tele')" type="radio" value="Y" id="set_flag_tele1" name="set_flag_tele" /><label for="set_flag_tele1" >Ya</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="custom02" >
                                <input class="radio_remove" onclick="inputWarning('war_set_flag_tele', 'cor_set_flag_tele')" type="radio" value="N" id="set_flag_tele2" name="set_flag_tele" /><label for="set_flag_tele2" >Tidak</label>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 12px; display: none; margin-left: -20px"
                               id="war_set_flag_tele">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 12px; display: none; margin-left: -20px"
                               id="cor_set_flag_telet"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="row jarak_atas">
                    <div class="form-group">
                        <label class="col-md-3 jarak_atas">Pelayanan</label>
                        <div class="col-md-7">
                            <select style="width: 100%" class="form-control select2" id="set_pelayanan"></select>
                        </div>
                        <div class="col-md-2">
                            <a class="btn btn-success" onclick="addToList()"><i class="fa fa-plus"></i> Add</a>
                        </div>
                    </div>
                </div>
                <div class="row jarak_atas">
                    <div class="col-md-12">
                        <table id="table_pelayanan" class="table table-bordered table-striped" style="font-size: 12px">
                            <thead>
                            <tr style="font-weight: bold">
                                <td>ID Pelayanan</td>
                                <td>Nama Pelayanan</td>
                                <td width="10%">Action</td>
                            </tr>
                            </thead>
                            <tbody id="body_pelayanan"></tbody>
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
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-md"></i> Detail Data Dokter</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-striped" style="font-size: 12px">
                                <tr>
                                    <td width="40%"><b>ID Dokter</b></td>
                                    <td><span id="v_id_dokter"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kode DPJP</b></td>
                                    <td><span id="v_kode_dpjp"></span></td>
                                </tr>
                                <tr>
                                    <td><b>SIP</b></td>
                                    <td><span id="v_sip"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Nama Dokter</b></td>
                                    <td><span id="v_nama_dokter"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kuota Online</b></td>
                                    <td><span id="v_kuota_online"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kuota Telemedic</b></td>
                                    <td><span id="v_kuota_telemedic"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kuota On Site Non BPJS</b></td>
                                    <td><span id="v_kuota_onsite_nonbpjs"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Kuota On Site BPJS</b></td>
                                    <td><span id="v_kuota_onsite_bpjs"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Flag Call</b></td>
                                    <td><span id="v_flag_call"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Flag Telemedic</b></td>
                                    <td><span id="v_flag_telemdic"></span></td>
                                </tr>
                            </table>
                            <hr>
                            <label style="font-size: 12px">Daftar Pelayanan Dokter</label>
                            <table class="table table-bordered table-striped" style="font-size: 12px">
                                <thead>
                                <tr>
                                    <td width="10%">No</td>
                                    <td>Nama Pelayanan</td>
                                </tr>
                                </thead>
                                <tbody id="view_pelayanan"></tbody>
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
        getDataDokter(id);
        if ('detail' == tipe) {
            $('#modal-view').modal({show: true, static: 'backdrop'});
        }
        if ('edit' == tipe || 'delete' == tipe) {
            getPelayanan();
            $('#modal-edit').modal({show: true, static: 'backdrop'});
            $('#save_edit').attr('onclick', 'saveDokter(\'' + tipe + '\')');
        }
    }

    function addToList() {
        var data = $('#table_pelayanan').tableToJSON();
        var idPelayanan = $('#set_pelayanan').val();
        var namaPelayanan = $('#set_pelayanan option:selected').text();
        var idCount = data.length;
        if (idPelayanan != '') {
            var cek = false;
            $.each(data, function (i, item) {
                var pelayananId = $('#id_pelayanan_' + i).val();
                if (idPelayanan == pelayananId) {
                    cek = true;
                }
            });
            if (cek) {
                $('#warning_edit').show().fadeOut(5000);
                $('#msg_edit').text("Data "+namaPelayanan+" sudah ada dalam list...!");
                $('#modal-edit').scrollTop(0)
            } else {
                var row = 'row_'+idPelayanan;
                var table = '<tr id="'+row+'">' +
                            '<td>' +
                            idPelayanan+
                            '</td>'+
                            '<td>' +
                            '<input type="hidden" id="id_pelayanan_'+idCount+'" value="'+idPelayanan+'">'+
                             namaPelayanan+
                            '</td>'+
                            '<td align="center"><img border="0" onclick="delRow(\'' + row + '\')" class="hvr-grow" src="' + contextPathHeader + '/pages/images/cancel-flat-new.png" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                            '</tr>';
                '</tr>';
                $('#body_pelayanan').append(table);
            }
        } else {
            $('#warning_edit').show().fadeOut(5000);
            $('#msg_edit').text("Silahkan cek kembali data inputan berikut...!");
            $('#modal-edit').scrollTop(0)
            if (idPelayanan == '') {
                $('#war_set_pelayanan').show();
            }
        }
    }

    function delRow(id) {
        $('#' + id).remove();
    }

    function saveDokter(tipe) {
        var data = "";
        if(!cekSession()){
            var dataPelayanan = $('#table_pelayanan').tableToJSON();
            var idDokter = $('#set_id_dokter').val();
            var kodeDpjp = $('#set_kode_dpjp').val();
            var sip = $('#set_sip').val();
            var namaDokter = $('#set_nama_dokter').val();
            var kuotaOnline = $('#set_kuota_online').val();
            var kuotaTelemedic = $('#set_kuota_telemedic').val();
            var kuotaNonBpjs = $('#set_kuota_no_bpjs').val();
            var kuotaBpjs = $('#set_kuota_bpjs').val();
            var flagCall = $('[name=set_flag_call]:checked').val();
            var flagTele = $('[name=set_flag_tele]:checked').val();

            if (idDokter && kodeDpjp && sip && namaDokter && kuotaOnline && kuotaTelemedic &&
                kuotaNonBpjs && kuotaBpjs != '' && flagCall && flagTele != undefined && dataPelayanan.length > 0) {
                var tempPelayanan = [];
                $('#save_edit').hide();
                $('#load_edit').show();
                $.each(dataPelayanan, function (i, item) {
                    tempPelayanan.push({
                        'id_pelayanan': dataPelayanan[i]["ID Pelayanan"]
                    });
                });
                var stringPelayanan = JSON.stringify(tempPelayanan);
                data = {
                    'tipe': tipe,
                    'id_dokter': idDokter,
                    'kode_dpjp': kodeDpjp,
                    'sip': sip,
                    'nama_dokter': namaDokter,
                    'kuota_online': kuotaOnline,
                    'kuota_tele': kuotaTelemedic,
                    'kuota_on_site': kuotaNonBpjs,
                    'kuota_bpjs': kuotaBpjs,
                    'flag_call': flagCall,
                    'flag_tele': flagTele,
                    'list_pelayanan': stringPelayanan
                }
                var dataString = JSON.stringify(data);
                dwr.engine.setAsync(true);
                DokterAction.saveEditDokter(dataString, {
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
                            $('#modal-edit').scrollTop(0);
                        }
                    }
                });
            } else {
                $('#warning_edit').show().fadeOut(5000);
                $('#msg_edit').text("Silahkan cek kembali data inputan berikut...!");
                $('#modal-edit').scrollTop(0);

                if (idDokter == '') {
                    $('#war_set_id_dokter').show();
                }
                if (kodeDpjp == '') {
                    $('#war_set_kode_dpjp').show();
                }
                if (sip == '') {
                    $('#war_set_sip').show();
                }
                if (namaDokter == '') {
                    $('#war_set_nama_dokter').show();
                }
                if (kuotaOnline == '') {
                    $('#war_set_kuota_online').show();
                }
                if (kuotaTelemedic == '') {
                    $('#war_set_kuota_telemedic').show();
                }
                if (kuotaNonBpjs == '') {
                    $('#war_set_kuota_no_bpjs').show();
                }
                if (kuotaBpjs == '') {
                    $('#war_set_kuota_bpjs').show();
                }
                if (flagCall == '') {
                    $('#war_set_flag_call').show();
                }
                if (flagTele == '') {
                    $('#war_set_flag_tele').show();
                }
            }
        }
    }

    function getPelayanan() {
        var option = '<option value="">[Select One]</option>';
        DokterAction.getComboPelayanan(function (res) {
            if (res.length > 0) {
                $.each(res, function (i, item) {
                    option += '<option value="' + item.idPelayanan + '">' + item.namaPelayanan + '</option>'
                });
            }
            $('#set_pelayanan').html(option);
        });
    }

    function getDataDokter(id) {
        if(!cekSession()){
            DokterAction.initDokter(id, function (res) {
                if (res.idDokter != null) {
                    $('#v_id_dokter').text(res.idDokter);
                    $('#v_kode_dpjp').text(res.kodeDpjp);
                    $('#v_sip').text(res.sip);
                    $('#v_nama_dokter').text(res.namaDokter);
                    $('#v_kuota_online').text(res.kuota);
                    $('#v_kuota_telemedic').text(res.kuotaTele);
                    $('#v_kuota_onsite_nonbpjs').text(res.kuotaOnSite);
                    $('#v_kuota_onsite_bpjs').text(res.kuotaBpjs);
                    $('#v_flag_call').text(res.flagCall);
                    $('#v_flag_telemdic').text(res.flagTele);

                    $('#set_id_dokter').val(res.idDokter);
                    $('#set_kode_dpjp').val(res.kodeDpjp);
                    $('#set_sip').val(res.sip);
                    $('#set_nama_dokter').val(res.namaDokter);
                    $('#set_kuota_online').val(res.kuota);
                    $('#set_kuota_telemedic').val(res.kuotaTele);
                    $('#set_kuota_no_bpjs').val(res.kuotaOnSite);
                    $('#set_kuota_bpjs').val(res.kuotaBpjs);

                    if(res.pelayananList.length > 0){
                        var table = '';
                        var view = '';
                        $.each(res.pelayananList, function (i, item) {
                            var row = 'row_'+i;
                            table += '<tr id="'+row+'">' +
                                '<td>' + item.idPelayanan+ '</td>'+
                                '<td>' +
                                '<input type="hidden" id="id_pelayanan_'+i+'" value="'+item.idPelayanan+'">'+ item.namaPelayanan+
                                '</td>'+
                                '<td align="center"><img border="0" onclick="delRow(\'' + row + '\')" class="hvr-grow" src="' + contextPathHeader + '/pages/images/cancel-flat-new.png" style="cursor: pointer; height: 25px; width: 25px;"></td>' +
                                '</tr>';

                            var nomor = i+1;
                            view += '<tr>' +
                                '<td>' + nomor + '</td>'+
                                '<td>' + item.namaPelayanan+ '</td>'+
                                '</tr>';
                        });
                        $('#view_pelayanan').html(view);
                        $('#body_pelayanan').html(table);
                    }else{
                        $('#body_pelayanan').html('');
                        $('#view_pelayanan').html('');
                    }

                    $('[name=set_flag_call]').filter("[value='"+res.flagCall+"']").attr('checked', true);
                    $('[name=set_flag_tele]').filter("[value='"+res.flagTele+"']").attr('checked', true);
                }
            });
        }
    }

    function listPelayanan(branchId){
        PelayananAction.getListPelayananByBranch(branchId, function (res) {
            var str = "<option value=''> - </option>";
            $.each(res, function (i, item) {
               str += "<option value='"+item.idPelayanan+"'>"+item.namaPelayanan+"</option>"
            });

            $("#list-pelayanan").html(str);
        });
    }
</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>