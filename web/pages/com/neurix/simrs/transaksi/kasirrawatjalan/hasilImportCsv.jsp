<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>
<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type="text/javascript">
        $(document).ready(function () {
            function callSearch2() {
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
            }
            function link(){
                window.location.href="<s:url action='initForm_createfpk'/>";
            }

            $('#sortTable2').DataTable({
                "order": [[1, "desc"]],
                "columnDefs": [
                    { "orderable": false, "targets": 0 }
                ]
            });
            window.close = function () {
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href = "<s:url action='search_absensi.action'/>";
            };
            window.close2 = function () {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
            };
            $.subscribe('beforeProcessSave', function (event, data) {
                if (confirm('Do you want to proses this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');
                } else {
                    event.originalEvent.options.submit = false;
                    var msg="";
                    document.getElementById('errorValidationMessage').innerHTML = msg;
                    $.publish('showErrorValidationDialog');
                }
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

            function cancelBtn() {
                $('#view_dialog_menu').dialog('close');
            }
        });
    </script>

</head>

<body class="hold-transition skin-blue sidebar-mini">
<%@ include file="/pages/common/headerNav.jsp" %>
<ivelincloud:mainMenu/>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            FPK (Form Pengajuan Klaim) Pasien BPJS
            <small>GO-MEDSYS</small>
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-upload"></i> Hasil Import CSV BPJS Kesehatan</h3>
                    </div>
                    <s:form id="saveImportCsv" enctype="multipart/form-data" method="post" namespace="/createfpk"
                            action="saveImportCsv_createfpk.action" theme="simple">
                        <div class="box-body">
                            <div class="row">
                                <div class="form-group">
                                    <label class="control-label col-sm-offset-2 col-sm-2" style="margin-top: 7px">Bank</label>
                                    <div class="col-md-4">
                                        <select class="form-control" id="bank" name="headerDetailCheckup.bank">
                                            <option value="" >[Select One]</option>
                                            <option value="bri">BRI</option>
                                            <option value="bni">BNI</option>
                                            <option value="bca">BCA</option>
                                            <option value="mandiri">Mandiri</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label class="control-label col-sm-offset-2 col-sm-2" style="margin-top: 7px">No. FPK</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="npFpk" cssStyle="margin-top: 7px"
                                                     name="headerDetailCheckup.noFpk" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label class="control-label col-sm-offset-2 col-sm-2" style="margin-top: 7px">No. Slip</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="noSlipBank" cssStyle="margin-top: 7px"
                                                     name="headerDetailCheckup.noSlipBank" required="false"
                                                     readonly="false" cssClass="form-control"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-sm-offset-2 col-sm-2" style="margin-top: 7px">Tanggal</label>
                                    <div class="col-md-4">
                                        <div class="input-group date" style="margin-top: 7px" id="stTanggalFpk">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input class="form-control datepicker" id="tanggal_fpk" name="headerDetailCheckup.stTanggalFpk">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box-header with-border"></div>
                                    <div class="box-header with-border">
                                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar SEP Klaim</h3>
                                    </div>
                                    <div class="box-body">
                                        <table id="sortTable2" class="table table-bordered table-striped">
                                            <thead>
                                            <tr bgcolor="#90ee90">
                                                <td>ID</td>
                                                <td>No. SEP</td>
                                                <td>Total Biaya ( dari RS )</td>
                                                <td>Total Biaya ( dari BPJS )</td>
                                                <td>Keterangan</td>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <s:iterator value="#session.listOfImportCsv" var="row">
                                                <tr>
                                                    <td><s:property value="idDetailCheckup"/></td>
                                                    <td><s:property value="NoSep"/></td>
                                                    <td style="text-align: right"><s:property value="totalBiaya" /></td>
                                                    <td style="text-align: right"><s:property value="totalBiayaDariBpjs"/></td>
                                                    <td>
                                                        <s:if test='#row.statusBayar == "SB"'>
                                                            <label class="label label-danger"> Data SEP sudah di klaim</label>
                                                        </s:if>
                                                        <s:elseif test='#row.statusBayar == "N"'>
                                                            <label class="label label-danger"> Data SEP tidak ada</label>
                                                        </s:elseif>
                                                        <s:elseif test='#row.statusBayar == "KB"'>
                                                            <label class="label label-warning"> Biaya BPJS < Biaya RS</label>
                                                        </s:elseif>
                                                        <s:elseif test='#row.statusBayar == "LB"'>
                                                            <label class="label label-success"> Biaya BPJS > Biaya RS</label>
                                                        </s:elseif>
                                                        <s:elseif test='#row.statusBayar == "P"'>
                                                            <label class="label label-success"> Biaya BPJS = Biaya RS</label>
                                                        </s:elseif>
                                                        <s:else>
                                                            <label class="label label-default"> Kesalahan data</label>
                                                        </s:else>
                                                    </td>
                                                </tr>
                                            </s:iterator>
                                            </tbody>
                                        </table>
                                        <div class="box-header with-border"></div>
                                    </div>
                                    <br>
                                    <br>
                                    <div class="form-group col-md-offset-4">
                                        <br>
                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary"
                                                   formIds="saveImportCsv" id="save" name="save"
                                                   onBeforeTopics="beforeProcessSave"
                                                   onCompleteTopics="closeDialog,successDialog"
                                                   onSuccessTopics="successDialog"
                                                   onErrorTopics="errorDialog">
                                            <i class="fa fa-save"></i>
                                            Save
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_createfpk.action">
                                            <i class="fa fa-arrow-left"></i> Go Back
                                        </a>
                                    </div>
                                    <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog"
                                               modal="true"
                                               resizable="false"
                                               height="250" width="600" autoOpen="false" title="Importing ...">
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

                                    <sj:dialog id="info_dialog" openTopics="showInfoDialog45" modal="true" resizable="false"
                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                               buttons="{
                                                              'OK':function() {
                                                                      callSearch2();
                                                                      link();
                                                                   }
                                                            }"
                                    >
                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                        Inquiring has been successfully.
                                    </sj:dialog>

                                    <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                               height="250" width="600" autoOpen="false" title="Error Dialog"
                                               buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close'); }
                                                                    }"
                                    >
                                        <div class="alert alert-error fade in">
                                            <label class="control-label" align="left">
                                                <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage"></p>
                                            </label>
                                        </div>
                                    </sj:dialog>

                                    <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog" modal="true" resizable="false"
                                               height="280" width="500" autoOpen="false" title="Warning"
                                               buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }"
                                    >
                                        <div class="alert alert-error fade in">
                                            <label class="control-label" align="left">
                                                <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                <br/>
                                                <center><div id="errorValidationMessage"></div></center>
                                            </label>
                                        </div>
                                    </sj:dialog>
                                </div>
                            </div>
                        </div>
                    </s:form>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>
</body>
</html>