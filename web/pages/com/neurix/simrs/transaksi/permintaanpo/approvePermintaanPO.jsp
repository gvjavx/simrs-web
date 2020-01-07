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
            Approval Permintaan PO
            <small>e-HEALTH</small>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <s:form id="approvePoForm" enctype="multipart/form-data" method="post" namespace="/permintaanpo"
                    action="saveApprove_permintaanpo.action" theme="simple">
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
                        </table>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-file-text-o"></i> Daftar Permintaan PO</h3>
                    </div>
                    <div class="box-body">
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
                                <td align="center">Satuan Jenis</td>
                                <td align="right">Harga</td>
                                <td align="center">Status Obat</td>
                                <td align="center">Status Approve</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfObatDetail" var="row">
                                <tr id='row<s:property value="idObat"/>'>
                                    <td><s:property value="idObat"/></td>
                                    <td><s:property value="namaObat"/></td>
                                    <td align="center"><s:property value="qty"/></td>
                                    <td align="center"><s:property value="qtyApprove"/></td>
                                    <td align="center"><s:property value="jenisSatuan"/></td>
                                    <td align="right"><s:property value="hargaPo"/></td>
                                    <td align="center">
                                        <s:if test='#row.flagDiterima == "Y"'>
                                            <span class="label label-success">Sesuai</span>
                                        </s:if>
                                        <s:if test='#row.flagDiterima == "N"'>
                                            <span class="label label-danger">Tidak Sesuai</span>
                                        </s:if></td>
                                    <td align="center">
                                        <s:if test='#row.flagDiterima == "Y"'>
                                            <span class="label label-success">Setuju</span>
                                        </s:if>
                                        <s:if test='#row.flagDiterima == "N"'>
                                            <span class="label label-danger">Tidak Setuju</span>
                                        </s:if></td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                        <div class="box-header with-border"></div>
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-file-text-o"></i> Daftar Obat dengan ID pabrik baru
                            </h3>
                        </div>
                        <div class="box-body">
                            <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_new_po">
                                <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                                <p id="msg_new_po"></p>
                            </div>
                            <table class="table table-bordered table-striped" id="tabel_new_po">
                                <thead>
                                <tr bgcolor="#90ee90">
                                    <td>Nama Obat</td>
                                    <td align="center">Qty Request</td>
                                    <td align="center">Qty Approve</td>
                                    <td align="center">Satuan Jenis</td>
                                    <td align="right">Harga</td>
                                    <td align="center">Lembar/Box</td>
                                    <td align="center">Biji/Lembar</td>
                                </tr>
                                </thead>
                                <tbody>
                                <s:iterator value="#session.listOfObatDetailNew" var="row">
                                    <tr>
                                        <td><s:property value="namaObat"/></td>
                                        <td align="center"><s:property value="qty"/></td>
                                        <td align="center"><s:property value="qtyApprove"/></td>
                                        <td align="center"><s:property value="jenisSatuan"/></td>
                                        <td align="right"><s:property value="hargaPo"/></td>
                                        <td align="center"><s:property value="lembarPerBox"/></td>
                                        <td align="center"><s:property value="bijiPerLembar"/></td>
                                    </tr>
                                </s:iterator>
                                </tbody>
                            </table>
                        </div>
                    <div class="box-header with-border"></div>
                    <div class="box-body">
                        <div class="row">
                            <div class="form-group">
                                <div class="col-md-offset-5">
                                    <a type="button" class="btn btn-success" onclick="confirm()"><i class="fa fa-arrow-right"></i> Approve</a>
                                    <a type="button" class="btn btn-warning"><i class="fa fa-arrow-left"></i> Back</a>
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
                                        <button type="button" class="btn btn-warning"
                                                onclick="$('#confirm_dialog').dialog('close')"><i
                                                class="fa fa-times"></i> No
                                        </button>
                                        <sj:submit targets="crud" type="button" cssClass="btn btn-success"
                                                   formIds="approvePoForm" id="save" name="save"
                                                   onBeforeTopics="beforeProcessSave"
                                                   onCompleteTopics="closeDialog,successDialog"
                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog">
                                            <i class="fa fa-arrow-right"></i>
                                            yes
                                        </sj:submit>
                                    </div>
                                </sj:dialog>
                                <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                           height="250" width="600" autoOpen="false" title="Error Dialog"
                                           buttons="{
                                                                                'OK':function() { $('#error_dialog').dialog('close'); }
                                                                            }"
                                >
                                    <div class="alert alert-danger alert-dismissible">
                                        <label class="control-label" align="left">
                                            <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage"></p>
                                        </label>
                                    </div>
                                </sj:dialog>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </s:form>
        </div>
    </section>
</div>

<!-- /.content-wrapper -->
<script type='text/javascript'>

    $(document).ready(function () {
        $('#permintaan_po').addClass('active');
    });

    function confirm(){
        $('#confirm_dialog').dialog('open');
    }

    $.subscribe('beforeProcessSave', function (event, data) {
        event.originalEvent.options.submit = true;
        $('#confirm_dialog').dialog('close');
        $.publish('showDialog');
    });

    $.subscribe('successDialog', function (event, data) {
        if (event.originalEvent.request.status == 200) {
            jQuery(".ui-dialog-titlebar-close").hide();
            $.publish('showInfoDialog');
        }
    });

    $.subscribe('errorDialog', function (event, data) {
        document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
        $.publish('showErrorDialog');
    });

</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>