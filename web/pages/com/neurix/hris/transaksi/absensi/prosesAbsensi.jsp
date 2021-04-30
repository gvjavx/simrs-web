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
        .kv-avatar .krajee-default.file-preview-frame, .kv-avatar .krajee-default.file-preview-frame:hover {
            margin: 0;
            padding: 0;
            border: none;
            box-shadow: none;
            text-align: center;
        }
        .kv-avatar {
            display: inline-block;
        }
        .kv-avatar .file-input {
            display: table-cell;
            width: 213px;
        }
        .absensiTable th{
            text-align: center;
            vertical-align: middle;
        }
        .kv-reqd {
            color: red;
            font-family: monospace;
            font-weight: normal;
        }
        .pagebanner {
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
        }
        .pagelinks {
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }
        #tahunAwal { z-index: 2000 !important }
        #tahunAkhir {  z-index: 2000 !important }
    </style>
    <script type='text/javascript' src='<s:url value="/dwr/interface/AbsensiAction.js"/>'></script>
    <script type="text/javascript">
        function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
        }
        function link(){
            window.location.href="<s:url action='initForm_absensi'/>";
        }
        function link2(){
            window.location.href="<s:url action='goToInquiry_absensi'/>";
        }
        $(document).ready(function () {
            window.close = function () {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href = "<s:url action='search_absensi.action'/>";
            };
            window.close2 = function () {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
            };
            $.subscribe('beforeProcessInquiryAbsensi', function (event, data) {
                var tanggal2 = $('#tanggal2').val();
                var tanggal1 = $('#tanggal1').val();

                if (tanggal1 != ''&&tanggal2 !='') {
                    if (confirm('Do you want to Inquiry this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');
                    } else {
                        // Cancel Submit comes with 1.8.0
                        event.originalEvent.options.submit = false;
                    }
                } else {
                    event.originalEvent.options.submit = false;
                    var msg = "";
                    if (tanggal1 == '') {
                        msg += 'Field <strong>Tanggal Awal</strong> is required.' + '<br/>';
                    }
                    if (tanggal2 == '') {
                        msg += 'Field <strong>Tanggal Akhir</strong> is required.' + '<br/>';
                    }
                    document.getElementById('errorValidationMessage').innerHTML = msg;
                    $.publish('showErrorValidationDialog');
                }
            });
            $.subscribe('beforeProcessSaveAbsensi', function (event, data) {
                var values;
                values = $('input:checkbox:checked').length;
                if (values!=0) {
                    if (confirm('Do you want to save this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');
                    } else {
                        event.originalEvent.options.submit = false;
                    }
                }else{
                    event.originalEvent.options.submit = false;
                    var msg = "";
                    if (values == 0) {
                        msg += 'tidak ada absensi yang di checklist' + '<br/>';
                    }
                    document.getElementById('errorValidationMessage').innerHTML = msg;
                    $.publish('showErrorValidationDialog');
                }
            });
            $.subscribe('successDialogDocument', function (event, data) {
                loadAbsensi();
            });
            $.subscribe('beforeProcessDelete', function (event, data) {
                if (confirm('Do you want to delete this record ?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');

                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }
            });
            $.subscribe('successDialog2', function (event, data) {
                if (event.originalEvent.request.status == 200) {
                    jQuery(".ui-dialog-titlebar-close").hide();
                    $.publish('showInfoDialog');
                }
            });
            $.subscribe('successDialogInquiry', function (event, data) {
                if (event.originalEvent.request.status == 200) {
                    jQuery(".ui-dialog-titlebar-close").hide();
                    $.publish('showInfoDialogInquiry');
                }
            });
            $.subscribe('errorDialog', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
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
            Proses Absensi
        </h1>
    </section>
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <br>
                    <br>
                    <table width="100%" align="center">
                        <tr>
                            <td align="center">
                                <s:form id="inquiryAbsensi" method="post" theme="simple" namespace="/absensi" action="inquiry_absensi" cssClass="form-horizontal">
                                    <table >
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Tanggal :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <div class="input-group date">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="tanggal1" name="absensiPegawai.stTanggalDari" cssClass="form-control pull-right"
                                                                     required="false" size="7"  cssStyle=""/>
                                                        <div class="input-group-addon">
                                                            s/d
                                                        </div>
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="tanggal2" name="absensiPegawai.stTanggalAkhir" cssClass="form-control pull-right"
                                                                     required="false" size="7"  cssStyle=""/>
                                                    </div>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center" colspan="2">
                                                <br>
                                                <div class="form-group">
                                                    <br>
                                                    <div class="col-sm-offset-1 col-sm-10">
                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary"
                                                                   formIds="inquiryAbsensi" id="inquiry" name="inquiry"
                                                                   onBeforeTopics="beforeProcessInquiryAbsensi"
                                                                   onCompleteTopics="closeDialogInquiry,successDialogInquiry"
                                                                   onSuccessTopics="successDialogInquiry"
                                                                   onErrorTopics="errorDialog">
                                                            <i class="fa fa-refresh"></i>
                                                            Inquiry
                                                        </sj:submit>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </s:form>
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <s:form id="addFormAbsensi" method="post" theme="simple" namespace="/absensi" action="saveAdd_absensi" cssClass="form-horizontal">
                                    <s:hidden name="addOrEdit"/>
                                    <s:hidden name="delete"/>
                                    <table>
                                        <tr>
                                            <td width="10%" align="center">
                                                <%@ include file="/pages/common/message.jsp" %>
                                            </td>
                                        </tr>
                                    </table>
                                        <table id="showdata" width="95%">
                                            <tr>
                                                <td align="center">
                                                    <table style="width: 100%;" class="absensiTable table table-bordered" id="absensiTable">
                                                    </table>
                                                    <table style="width: 100%;" class="absensiDetailTable table table-bordered" id="absensiDetailTable">
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </center>
                                    <br>
                                    <br>
                                    <div id="actions" class="form-actions">
                                        <table align="center">
                                            <tr>
                                                <td>
                                                    <sj:submit targets="crud" type="button" cssClass="btn btn-primary"
                                                               formIds="addFormAbsensi" id="saveAdd" name="save"
                                                               onBeforeTopics="beforeProcessSaveAbsensi"
                                                               onCompleteTopics="closeDialog,successDialog2"
                                                               onSuccessTopics="successDialog2"
                                                               onErrorTopics="errorDialog">
                                                        <i class="fa fa-check"></i>
                                                        Save
                                                    </sj:submit>
                                                </td>
                                                <td>&nbsp;&nbsp;&nbsp;</td>
                                                <td>
                                                    <button type="button" class="btn btn-danger" id="cancel"
                                                            onclick="window.location.href='<s:url
                                                                    action="initForm_absensi.action"/>'">
                                                        <i class="fa fa-refresh"></i> Cancel
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div id="actions" class="form-actions">
                                        <table>
                                            <tr>
                                                <div id="crud">
                                                    <td>
                                                        <table>
                                                            <sj:dialog id="waiting_dialog" openTopics="showDialog"
                                                                       closeTopics="closeDialog" modal="true"
                                                                       resizable="false"
                                                                       height="250" width="600" autoOpen="false"
                                                                       title="Processing ...">
                                                                Please don't close this window, server is processing your request ...
                                                                <br>
                                                                <center>
                                                                    <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                                         src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                                         name="image_indicator_write">
                                                                    <br>
                                                                    <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                                         src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                                         name="image_indicator_write">
                                                                </center>
                                                            </sj:dialog>

                                                            <%--<sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"--%>
                                                                       <%--resizable="false"--%>
                                                                       <%--height="350" width="600" autoOpen="false" title="Saving ...">--%>
                                                                <%--Please don't close this window, server is processing your request ...--%>
                                                                <%--</br>--%>
                                                                <%--</br>--%>
                                                                <%--</br>--%>
                                                                <%--<center>--%>
                                                                    <%--<img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">--%>
                                                                <%--</center>--%>
                                                            <%--</sj:dialog>--%>
                                                            <sj:dialog id="waiting_dialog_inquiry" openTopics="showDialogInquiry"
                                                                       closeTopics="closeDialogInquiry" modal="true"
                                                                       resizable="false"
                                                                       height="250" width="600" autoOpen="false"
                                                                       title="Inquiring ...">
                                                                Please don't close this window, server is processing your request ...
                                                                <br>
                                                                <center>
                                                                    <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                                         src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                                         name="image_indicator_write">
                                                                    <br>
                                                                    <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                                         src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                                         name="image_indicator_write">
                                                                </center>
                                                            </sj:dialog>

                                                            <%--<sj:dialog id="waiting_dialog_inquiry" openTopics="showDialogInquiry" closeTopics="closeDialogInquiry" modal="true"--%>
                                                                       <%--resizable="false"--%>
                                                                       <%--height="350" width="600" autoOpen="false" title="Inquiring ...">--%>
                                                                <%--Please don't close this window, server is processing your request ...--%>
                                                                <%--</br>--%>
                                                                <%--</br>--%>
                                                                <%--</br>--%>
                                                                <%--<center>--%>
                                                                    <%--<img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">--%>
                                                                <%--</center>--%>
                                                            <%--</sj:dialog>--%>
                                                            <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                                       height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                                       buttons="{
                                                              'OK':function() {
                                                                    //$(this).dialog('close');
                                                                      callSearch2();
                                                                      link();
                                                                   }
                                                            }"
                                                            >
                                                                <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                                                Record has been saved successfully.
                                                            </sj:dialog>
                                                            <sj:dialog id="info_dialog" openTopics="showInfoDialogInquiry" modal="true" resizable="false"
                                                                       height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                                       buttons="{
                                                              'OK':function() {
                                                                    //$(this).dialog('close');
                                                                      callSearch2();
                                                                      link2();
                                                                   }
                                                            }"
                                                            >
                                                                <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                                                Record has been saved Inquiring.
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
                                                        </table>
                                                    </td>
                                                </div>
                                            </tr>
                                        </table>
                                    </div>
                                </s:form>
                            </td>
                        </tr>
                    </table>
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
<div id="modal-list-detail" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Detail Absensi</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormAbsensiDetail">
                    <center>
                        <table id="showdata3" width="80%">
                            <tr>
                                <td align="center" style="display: none" id="tdAbsensi">
                                    <table style="width: 100%;" class="listAbsensiTable table table-bordered" id="listAbsensiTable2">
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" style="display: none" id="tdIjinKeluar">
                                    <table style="width: 100%;" class="listIjinKeluarTable table table-bordered" id="listIjinKeluarTable">
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" style="display: none" id="tdLembur">
                                    <table style="width: 100%;" class="listLemburTable table table-bordered" id="listLemburTable">
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" style="display: none" id="tdSppd">
                                    <table style="width: 100%;" class="listSppdTable table table-bordered" id="listSppdTable">
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" style="display: none" id="tdCuti">
                                    <table style="width: 100%;" class="listCutiTable table table-bordered" id="listCutiTable">
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" style="display: none" id="tdIjinTidakMasuk">
                                    <table style="width: 100%;" class="listIjinTidakMasukTable table table-bordered" id="listIjinTidakMasukTable">
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </center>
                    <br>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div id="modal-add-indisipliner" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Indisipliner</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormAddIndisipliner">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >NIP : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" readonly id="Nip4" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" readonly id="Nama4" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >bidang : </label>
                        <div class="col-sm-8">
                            <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                            <s:select list="#comboDivisi.listComboDepartment" id="Divisi4" name="indisipliner.divisiId" disabled="true"
                                      listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jabatan : </label>
                        <div class="col-sm-8">
                            <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                            <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="Posisi4" name="indisipliner.posisiId" required="false"
                                      listKey="stPositionId" disabled="true" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit : </label>
                        <div class="col-sm-8">
                            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                            <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="Unit4" disabled="true" name="indisipliner.branchId" required="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Absensi : </label>
                        <div class="col-sm-8">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" class="form-control nip" id="Tanggal4" readonly name="nip">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Masa Berlaku : </label>
                        <div class="col-sm-8">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input readonly type="text" class="form-control nip" id="tglAwalPantau4" name="nip">
                                <div class="input-group-addon">
                                    s/d
                                </div>
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input readonly type="text" class="form-control nip" id="tglAkhirPantau4" name="nip">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Blokir Absensi : </label>
                        <div class="col-sm-8">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input readonly type="text" class="form-control nip" id="tglAwalBlokir4" name="nip">
                                <div class="input-group-addon">
                                    s/d
                                </div>
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input readonly type="text" class="form-control nip" id="tglAkhirBlokir4" name="nip">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tipe Indisipliner : </label>
                        <div class="col-sm-8">
                            <s:select list="#{'SP0':'Teguran','SP1':'SP1','SP2':'SP2','SP3':'SP3'}" id="tipeIndisipliner4" name="indisipliner.tipeIndisipliner"
                                      headerKey="" headerValue="[Select one]" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan Pelanggaran : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" id="keteranganPelanggaran4" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Dampak : </label>
                        <div class="col-sm-8">
                            <textarea id="dampak4" rows="4" class="form-control nip" ></textarea>
                        </div>
                    </div>
                </form>
                <br>
                <center>
                    <table style="width: 60%;" class="listIndisiplinerTable table table-bordered" id="listIndisiplinerTable"/>
                </center>
                </table>
                <br>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="btn_save_indisipliner">Save</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div id="modal-add-keterangan" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Keterangan</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormAddKeterangan">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >NIP : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" readonly id="Nip5" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Nama : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" readonly id="Nama5" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >bidang : </label>
                        <div class="col-sm-8">
                            <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                            <s:select list="#comboDivisi.listComboDepartment" id="Divisi5" name="indisipliner.divisiId" disabled="true"
                                      listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jabatan : </label>
                        <div class="col-sm-8">
                            <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                            <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="Posisi5" name="indisipliner.posisiId" required="false"
                                      listKey="stPositionId" disabled="true" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit : </label>
                        <div class="col-sm-8">
                            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                            <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="Unit5" disabled="true" name="indisipliner.branchId" required="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Absensi : </label>
                        <div class="col-sm-8">
                            <div class="input-group date">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" class="form-control nip" id="Tanggal5" readonly name="nip">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Uang Makan : </label>
                        <div class="col-sm-8">
                            <s:select list="#{'Y':'Iya','N':'Tidak'}" id="uangMakan5" name="absensiPegawai.flagUangMakan"
                                      headerKey="" headerValue="" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan : </label>
                        <div class="col-sm-8">
                            <textarea id="keterangan5" rows="4" class="form-control nip" ></textarea>
                        </div>
                    </div>
                </form>
                <br>
                </table>
                <br>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="btn_save_keterangan">Save</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
    window.cekKoneksi = function(){
        dwr.engine.setAsync(false);
        AbsensiAction.cekKoneksi(function(listdata) {
        })
    };
    window.loadFinal =  function(){
        var tanggal = $('#tanggal').val();
        $('.absensiTable').find('tr').remove();
        $('.absensiDetailTable').find('tbody').remove();
        $('.absensiDetailTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        AbsensiAction.searchAbsensiFinal(function(listdata) {
            tmp_table = "<thead style='font-size: 10px' ><tr class='active'>"+
                    "<th rowspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc'>View</th>"+
                    "<th rowspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc'><input type='checkbox' id='checkAll'></th>"+
                    "<th rowspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc'>Tanggal</th>"+
                    "<th rowspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc'>Hari</th>"+
                    "<th rowspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc'>PIN</th>"+
                    "<th rowspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc'>NIP</th>"+
                    "<th rowspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc'>Nama</th>"+
                    "<th rowspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc'>Bidang</th>"+
                    "<th colspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc'>Jam Kerja</th>"+
                    "<th colspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc'>Ijin Keluar</th>"+
                    "<th colspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc'>Lembur</th>"+
                    "<th rowspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc''>Pengajuan Lembur</th>"+
                    "<th rowspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc''>Realisasi Lembur</th>"+
                    "<th rowspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc''>Perhitungan Lembur</th>"+
                    "<th rowspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc''>Biaya Lembur</th>"+
                    "<th rowspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc''>Status</th>"+
//                    "<th rowspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc''>Indisipliner</th>"+
                    // "<th rowspan='2' style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc''>Keterangan</th>"+
                    "</tr>" +
                    "<tr class='active'>"+
                    "<th style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc'>In</th>"+
                    "<th style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc'>Out</th>"+
                    "<th style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc'>Out</th>"+
                    "<th style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc'>In</th>"+
                    "<th style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc'>Start</th>"+
                    "<th style='text-align: center; vertical-align: middle; color: #fff; background-color:  #3c8dbc'>End</th>"+
                    "</tr>"+
                    "</thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
                var combo = '<input type="checkbox" id="check" name="checkApprove[]" value="'+item.pin+'" class="check" checked >';
                var indisipliner="";
                // var note="";
                <%--if (item.keterangan!=null){--%>
                    <%--note="<img border='0' src='<s:url value='/pages/images/icon_success.ico'/>' name='icon_success'>";--%>
                <%--}--%>
                if (item.saved=="Y"){
                    indisipliner="<img border='0' src='<s:url value='/pages/images/icon_success.ico'/>' name='icon_success'>";
                }
                 else if (item.statusAbsensi=="01"){
                    indisipliner="";
                }else if(item.statusAbsensi=="04") {
                    indisipliner="";
                }else if(item.statusAbsensi=="08") {
                    indisipliner="";
                }else if(item.statusAbsensi=="10") {
                    indisipliner="";
                }else if(item.statusAbsensi=="05") {
                    indisipliner="";
                }else if(item.statusAbsensi=="09") {
                    indisipliner="";
                }else if(item.statusAbsensi=="11") {
                    indisipliner="";
                }else if(item.statusAbsensi=="12") {
                    indisipliner="";
                }else if(item.statusAbsensi=="15") {
                    indisipliner="";
                }
                else{
                    indisipliner = "<a href='javascript:;' class ='item-add-indisipliner' tanggal ='"+item.stTanggal+"' nip ='"+item.nip +"' status ='"+item.statusName+"'>"+
                            "<img border='0' src='<s:url value='/pages/images/issue.png'/>' name='icon_view'>"+
                            '</a>';
                    <%--if (item.keterangan==null){--%>
                        <%--note = "<a href='javascript:;' class ='item-add-keterangan' tanggal ='"+item.stTanggal+"' nip ='"+item.nip +"'>"+--%>
                            <%--"<img border='0' src='<s:url value='/pages/images/edit_task.png'/>' name='icon_view'>"+--%>
                            <%--'</a>';--%>
                    <%--}--%>
                }

                tmp_table += '<tr style="font-size: 9px;" ">' +
                        '<td align="center">' +
                        "<a href='javascript:;' class ='item-view-detail' data ='"+item.pin+"' tanggal ='"+item.stTanggal+"' nip ='"+item.nip+"' lemburAwal ='"+item.awalLembur+"' lemburAkhir ='"+item.selesaiLembur+"'>" +
                        "<img border='0' src='<s:url value='/pages/images/view.png'/>' name='icon_view'>"+
                        '</a>' +
                        '</td>' +
                        '<td align="center">' + combo + '</td>' +
                        '<td align="center">' + item.stTanggal + '</td>' +
                        '<td align="center">' + item.tipeHariName + '</td>' +
                        '<td align="center">' + item.pin + '</td>' +
                        '<td align="center">' + item.nip + '</td>' +
                        '<td align="center">' + item.nama + '</td>' +
                        '<td align="center" class="ceknull">' + item.divisi + '</td>' +
                        '<td align="center" class="ceknull">' + item.jamMasuk + '</td>' +
                        '<td align="center" class="ceknull">' + item.jamPulang + '</td>' +
                        '<td align="center" class="ceknull">' + item.mulaiIzin + '</td>' +
                        '<td align="center" class="ceknull">' + item.pulangIzin + '</td>' +
                        '<td align="center" class="ceknull">' + item.awalLembur + '</td>' +
                        '<td align="center" class="ceknull">' + item.selesaiLembur + '</td>' +
                        '<td align="center">' + item.pengajuanLembur + '</td>' +
                        '<td align="center">' + item.realisasiJamLembur + '</td>' +
                        '<td align="center">' + item.jamLembur + '</td>' +
                        '<td align="center" class="ceknull">' + item.biayaLemburName + '</td>' +
                        '<td align="center">' + item.statusName + '</td>' +
//                        '<td align="center">' +indisipliner+'</td>' +
                        // '<td align="center">' +note+'</td>' +
                        "</tr>";
            });
            $('.absensiDetailTable').append(tmp_table);
            $("#checkAll").change(function(){
                $('input:checkbox').not(this).prop('checked', this.checked);
            });
            $('#cancel').show();
            $('#saveAdd').show();
            $("#absensiDetailTable .ceknull:contains('null')").html("-");
    });
    };
    $(document).ready(function(){
        loadFinal();
        $('#tanggal1').datepicker({dateFormat: 'dd-mm-yy'});
        $('#tanggal2').datepicker({dateFormat: 'dd-mm-yy'});
    });
    $('.absensiDetailTable').on('click', '.item-view-detail', function () {
        var pin = $(this).attr('data');
        var tanggal = $(this).attr('tanggal');
        var nip = $(this).attr('nip');
        var awalLembur = $(this).attr('lemburawal');
        var selesaiLembur = $(this).attr('lemburakhir');
        $('.listAbsensiTable').find('tbody').remove();
        $('.listAbsensiTable').find('thead').remove();
        $('.listIjinKeluarTable').find('tbody').remove();
        $('.listIjinKeluarTable').find('thead').remove();
        $('.listLemburTable').find('tbody').remove();
        $('.listLemburTable').find('thead').remove();
        $('.listSppdTable').find('tbody').remove();
        $('.listSppdTable').find('thead').remove();
        $('.listCutiTable').find('tbody').remove();
        $('.listCutiTable').find('thead').remove();
        $('.listIjinTidakMasukTable').find('tbody').remove();
        $('.listIjinTidakMasukTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        var tmp_table2 = "";
        var tmp_table3 = "";
        var tmp_table4 = "";
        var tmp_table5 = "";
        var tmp_table6 = "";
        AbsensiAction.searchListAbsensiDetail(pin,tanggal,function(listdata) {
            if (listdata!=""){
                tmp_table = "<thead style='font-size: 14px' >" +
                        "<tr>" +
                        "<th colspan='4' align='center' style='outline: 0px;text-align: center'>Absensi</th>" +
                        "</tr>"+
                        "<tr class='active'>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tanggal</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>status</th>"+
                        "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i + 1) + '</td>' +
                            '<td align="center">' + item.tanggal + '</td>' +
                            '<td align="center">' + item.statusName + '</td>' +
                            "</tr>";
                });
                $('#tdAbsensi').css("display", "block");
                $('.listAbsensiTable').append(tmp_table);
                $("#listAbsensiTable").find("td:contains('null')").html("-");
            }
        });
        AbsensiAction.searchListAbsensiLembur(tanggal,nip,function(listdata) {
            if (listdata!=""){
                tmp_table2 = "<thead style='font-size: 14px' >" +
                        "<tr>" +
                        "<th colspan='4' align='center' style='outline: 0px;text-align: center'>Lembur</th>" +
                        "</tr>"+
                        "<tr class='active'>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Jenis Lembur</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Jam Awal Lembur</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Jam Selesai Lembur</th>"+
                        "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table2 += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i + 1) + '</td>' +
                            '<td align="center">' + item.tipeLemburName + '</td>' +
                            '<td align="center">' + awalLembur + '</td>' +
                            '<td align="center">' + selesaiLembur + '</td>' +
                            "</tr>";
                });
                $('#tdLembur').css("display", "block");
                $('.listLemburTable').append(tmp_table2);
                $("#listLemburTable td:contains('null')").html("-");
            }
        });
        AbsensiAction.searchListAbsensiIjinKeluar(tanggal,nip,function(listdata) {
            if (listdata!=""){
                tmp_table3 = "<thead style='font-size: 14px' >" +
                        "<tr>" +
                        "<th colspan='4' style='outline: 0px ; text-align: center'>Ijin Keluar</th>" +
                        "</tr>"+
                        "<tr class='active'>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Keterangan</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Jam keluar</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Jam kembali</th>"+
                        "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table3 += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i + 1) + '</td>' +
                            '<td align="center">' + item.keterangan + '</td>' +
                            '<td align="center">' + item.jamKeluar + '</td>' +
                            '<td align="center">' + item.jamKembali + '</td>' +
                            "</tr>";
                });
                $('#tdIjinKeluar').css("display", "block");
                $('.listIjinKeluarTable').append(tmp_table3);
                $("#listIjinKeluarTable td:contains('null')").html("-");
            }
        });
        AbsensiAction.searchListAbsensiCuti(tanggal,nip,function(listdata) {
            if (listdata!=""){
                tmp_table4 = "<thead style='font-size: 14px' >" +
                        "<tr>" +
                        "<th colspan='4' align='center' style='outline: 0px;text-align: center'>Cuti</th>" +
                        "</tr>"+
                        "<tr class='active'>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Jenis Cuti</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tanggal Awal</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tanggal Akhir</th>"+
                        "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table4 += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i + 1) + '</td>' +
                            '<td align="center">' + item.cutiName + '</td>' +
                            '<td align="center">' + item.stTanggalDari + '</td>' +
                            '<td align="center">' + item.stTanggalSelesai + '</td>' +
                            "</tr>";
                });
                $('#tdCuti').css("display", "block");
                $('.listCutiTable').append(tmp_table4);
                $("#listCutiTable td:contains('null')").html("-");
            }
        });
        AbsensiAction.searchListAbsensiSppd(tanggal,nip,function(listdata) {
            if (listdata!=""){
                tmp_table5 = "<thead style='font-size: 14px' >" +
                        "<tr>" +
                        "<th colspan='4' align='center' style='outline: 0px;text-align: center'>SPPD</th>" +
                        "</tr>"+
                        "<tr class='active'>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tanggal Berangkat</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tanggal kembali</th>"+
                        "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table5 += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i + 1) + '</td>' +
                            '<td align="center">' + item.stTanggalBerangkat + '</td>' +
                            '<td align="center">' + item.stTanggalKembali + '</td>' +
                            "</tr>";
                });
                $('#tdSppd').css("display", "block");
                $('.listSppdTable').append(tmp_table5);
                $("#listSppdTable td:contains('null')").html("-");
            }
        });
        AbsensiAction.searchListAbsensiIjinTidakMasuk(tanggal,nip,function(listdata) {
            if (listdata!=""){
                tmp_table6 = "<thead style='font-size: 14px' >" +
                        "<tr>" +
                        "<th colspan='4' align='center' style='outline: 0px;text-align: center'>Ijin Tidak Masuk</th>" +
                        "</tr>"+
                        "<tr class='active'>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Jenis Ijin</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tanggal Dari</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tanggal Selesai</th>"+
                        "</tr></thead>";
                var i = i;
                $.each(listdata, function (i, item) {
                    tmp_table6 += '<tr style="font-size: 12px;" ">' +
                            '<td align="center">' + (i + 1) + '</td>' +
                            '<td align="center">' + item.ijinName + '</td>' +
                            '<td align="center">' + item.stTanggalAwal + '</td>' +
                            '<td align="center">' + item.stTanggalAkhir + '</td>' +
                            "</tr>";
                });
                $('#tdIjinTidakMasuk').css("display", "block");
                $('.listIjinTidakMasukTable').append(tmp_table6);
                $("#listIjinTidakMasukTable td:contains('null')").html("-");
            }
        });
        $('#modal-list-detail').find('.modal-title').text('View Detail Absensi');
        $('#modal-list-detail').modal('show');
    });
    $('.absensiDetailTable').on('click', '.item-add-indisipliner', function () {
        var tanggal = $(this).attr('tanggal');
        var nip = $(this).attr('nip');
        var status =$(this).attr('status');

        $('.listAbsensiTable').find('tbody').remove();
        $('.listAbsensiTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        AbsensiAction.addIndisipliner(nip,tanggal,function(listdata) {
            if (listdata!=""){
                    $('#Nip4').val(nip);
                    $('#Nama4').val(listdata.nama);
                    $('#Divisi4').val(listdata.divisiId);
                    $('#Posisi4').val(listdata.positionId);
                    $('#Unit4').val(listdata.branchId);
                    $('#Tanggal4').val(listdata.stTanggal);
                    $('#dampak4').val(listdata.dampak);
                    $('#tipeIndisipliner4').val(listdata.tipeIndisipliner);
                    $('#tglAwalPantau4').val(listdata.stTanggalAwalPantau);
                    $('#tglAkhirPantau4').val(listdata.stTanggalAkhirPantau);
                    $('#tglAwalBlokir4').val(listdata.stTanggalAwalBlokirAbsensi);
                    $('#tglAkhirBlokir4').val(listdata.stTanggalAkhirBlokirAbsensi);
                    $('#keteranganPelanggaran4').val(status);
            }
        });
        $('.listIndisiplinerTable').find('tbody').remove();
        $('.listIndisiplinerTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        AbsensiAction.searchListIndisipliner(nip,tanggal,function(listdata) {
            tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>tipe Indisipliner</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>tanggal Awal Berlaku</th>"+
                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>tanggal Akhir Berlaku</th>"+
                    "</tr></thead>";
            var i = i;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center">' + item.tipeIndisipliner + '</td>' +
                        '<td align="center">' + item.stTanggalAwalPantau + '</td>' +
                        '<td align="center">' + item.stTanggalAkhirPantau + '</td>' +
                        "</tr>";
            });
            if(listdata!=""){
                $('.listIndisiplinerTable').append(tmp_table);
                $("#listIndisiplinerTable td:contains('null')").html("-");
            }
        });

        $('#modal-add-indisipliner').find('.modal-title').text('Add Indisipliner');
        $('#modal-add-indisipliner').modal('show');
    });
    $('#btn_save_indisipliner').click(function () {
        var nip = document.getElementById("Nip4").value;
        var nama = document.getElementById("Nama4").value;
        var tipeIndisipliner = document.getElementById("tipeIndisipliner4").value;
        var tanggal = document.getElementById("Tanggal4").value;
        var tanggalAwalPantau = document.getElementById("tglAwalPantau4").value;
        var tanggalAkhirPantau = document.getElementById("tglAkhirPantau4").value;
        var tanggalAwalBlokir = document.getElementById("tglAwalBlokir4").value;
        var tanggalAkhirBlokir = document.getElementById("tglAkhirBlokir4").value;
        var keteranganIndisipliner = document.getElementById("keteranganPelanggaran4").value;
        var dampak = document.getElementById("dampak4").value;
            if (nip != '') {
                if (confirm('Are you sure you want to save this Record?')) {
                    dwr.engine.setAsync(false);
                    AbsensiAction.saveAddIndisipliner(nip, nama, tipeIndisipliner, tanggal, keteranganIndisipliner, dampak,tanggalAwalPantau,tanggalAkhirPantau,tanggalAwalBlokir,tanggalAkhirBlokir, function (listdata) {
                        alert('Data Successfully Added');
                        $('#modal-add-indisipliner').modal('hide');
                        loadFinal();
                    });
                }
            } else {
                alert('NIP tidak boleh kosong ');
            }
    });
    $('.absensiDetailTable').on('click', '.item-add-keterangan', function () {
        $('#keterangan5').val();
        $('#uangMakan5').val();
        var tanggal = $(this).attr('tanggal');
        var nip = $(this).attr('nip');

        $('.listAbsensiTable').find('tbody').remove();
        $('.listAbsensiTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        AbsensiAction.addKeterangan(nip,tanggal,function(listdata) {
            if (listdata!=""){
                $('#Nip5').val(nip);
                $('#Nama5').val(listdata.nama);
                $('#Divisi5').val(listdata.divisiId);
                $('#Posisi5').val(listdata.positionId);
                $('#Unit5').val(listdata.branchId);
                $('#Tanggal5').val(listdata.stTanggal);
            }
        });
        $('#modal-add-keterangan').find('.modal-title').text('Add Keterangan');
        $('#modal-add-keterangan').modal('show');
    });
    $('#btn_save_keterangan').click(function () {
        var nip = document.getElementById("Nip5").value;
        var tanggal = document.getElementById("Tanggal5").value;
        var keterangan = document.getElementById("keterangan5").value;
        var flagUangMakan = document.getElementById("uangMakan5").value;
        if (nip != ""&&keterangan!=""&&flagUangMakan!="") {
            if (confirm('Are you sure you want to save this Record?')) {
                dwr.engine.setAsync(false);
                AbsensiAction.saveAddKeterangan(nip,tanggal,keterangan,flagUangMakan, function (status) {
                    if (status=="ok"){
                        alert('Data berhasil disimpan');
                    } else {
                        alert('Data gagal disimpan')
                    }
                    $('#modal-add-keterangan').modal('hide');
                    loadFinal();
                });
            }
        } else {
            var msg="";
            if (nip==""){
                msg+='NIP tidak boleh kosong\n';
            }
            if (keterangan==""){
                msg+="Keterangan masih kosong\n";
            }
            if (flagUangMakan=="") {
                msg+="Uang makan masih kosong\n";
            }
            alert(msg);
        }
    });
</script>