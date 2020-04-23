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
    </style>
    <style>
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

        #tahunAwal {
            z-index: 2000 !important
        }

        #tahunAkhir {
            z-index: 2000 !important
        }
    </style>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PendapatanDokterAction.js"/>'></script>
    <script type="text/javascript">
        function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
        }
        function link(){
            window.location.href="<s:url action='goToResult_pendapatanDokter'/>";
        }
        $(document).ready(function () {
            window.close = function () {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href = "<s:url action='search_pendapatanDokter.action'/>";
            };
            window.close2 = function () {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
            };
            $.subscribe('beforeProcessSavePendapatan', function (event, data) {
                var unit = $('#branchId').val();
                var bulan = $('#periodeBulan').val();
                var tahun = $('#periodeTahun').val();

                if (unit != '' && bulan != '' && tahun != '') {
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
                    if (unit == ''){
                        msg += 'Field <strong>Unit</strong> is required.' + '<br/>'
                    }
                    if (bulan == '') {
                        msg += 'Field <strong>Bulan</strong> is required.' + '<br/>';
                    }
                    if (tahun == '') {
                        msg += 'Field <strong>Tahun</strong> is required.' + '<br/>';
                    }
                    document.getElementById('errorValidationMessage').innerHTML = msg;
                    $.publish('showErrorValidationDialog');
                }
            });
            $.subscribe('successDialogDocument', function (event, data) {
                loadPendapatan();
            });
            $.subscribe('beforeProcessDelete', function (event, data) {
                if (confirm('Do you want to delete this record ?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');

                } else {
                    event.originalEvent.options.submit = false;
                }
            });
            $.subscribe('successDialog2', function (event, data) {
                if (event.originalEvent.request.status == 200) {
                    jQuery(".ui-dialog-titlebar-close").hide();
                    $.publish('showInfoDialog45');
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
            Add Pendapatan Dokter

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
                                <s:form id="inquryPendapatanDokter" method="post" theme="simple" namespace="/pendapatanDokter" action="pendapatan_pendapatanDokter" cssClass="form-horizontal">
                                    <s:hidden name="addOrEdit"/>
                                    <s:hidden name="delete"/>
                                    <table>
                                        <tr>
                                            <td width="10%" align="center">
                                                <%@ include file="/pages/common/message.jsp" %>
                                            </td>
                                        </tr>
                                    </table>
                                    <table >
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Unit :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:if test='pendapatanDokter.branchId == "KP"'>
                                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="pendapatanDokter.branchId"
                                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                    </s:if>
                                                    <s:else>
                                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="pendapatanDokter.branchId" disabled="true"
                                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        <s:hidden id="branchId" name="pendapatanDokter.branchId" />
                                                    </s:else>
                                                </table>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Bulan :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                                        '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                                              id="periodeBulan" name="pendapatanDokter.bulan"
                                                              headerKey="" headerValue="[Select One]" cssClass="form-control" />
                                                </table>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Tahun :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>
                                                    <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="periodeTahun"
                                                              name="pendapatanDokter.tahun" required="true" headerKey=""
                                                              headerValue="[Select one]"/>
                                                </table>
                                            </td>
                                            <script>
                                                var dt = new Date();
                                                $('#periodeBulan').val(("0" + (dt.getMonth() + 1)).slice(-2));
                                                $('#periodeTahun').val(dt.getFullYear());
                                            </script>
                                        </tr>

                                    </table>
                                    <br>
                                    <div id="actions" class="form-actions">
                                        <table align="center">
                                            <tr>
                                                <td>
                                                    <sj:submit targets="crud" type="button" cssClass="btn btn-primary"
                                                               formIds="inquryPendapatanDokter" id="saveAdd" name="save"
                                                               onBeforeTopics="beforeProcessSavePendapatan"
                                                               onCompleteTopics="closeDialog,successDialog2"
                                                               onSuccessTopics="successDialog2"
                                                               onErrorTopics="errorDialog">
                                                        <i class="fa fa-refresh"></i>
                                                        Search
                                                    </sj:submit>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="add_pendapatanDokter"/>'">
                                                        <i class="fa fa-refresh"></i> Reset
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
                                                                       title="Save Data ...">
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
<script>
    window.cekKoneksi = function(){
        dwr.engine.setAsync(false);
        AbsensiAction.cekKoneksi(function(listdata) {
        })
    };
    $(document).ready(function(){
        $('#tanggal1').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#tanggal2').datepicker({
            dateFormat: 'dd-mm-yy'
        });
    });
</script>