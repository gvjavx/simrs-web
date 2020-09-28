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
    <script type='text/javascript' src='<s:url value="/dwr/interface/AbsensiAction.js"/>'></script>
    <script type="text/javascript">
        function callSearch2() {
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
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
                window.location.href = "<s:url action='search_absensi.action'/>";
            };
            window.close2 = function () {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
            };
            $.subscribe('beforeProcessSaveAbsensi', function (event, data) {
                if (confirm('Do you want to proses this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');
                } else {
                    event.originalEvent.options.submit = false;
                }
            });
            $.subscribe('beforeProcessInquiryAbsensi', function (event, data) {
                var tanggal2 = $('#tanggal2').val();
                var tanggal1 = $('#tanggal1').val();

                if (tanggal1 != ''&&tanggal2 !='') {
                    if (confirm('Do you want to Inquiry this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialogInquiry');
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
            $.subscribe('successDialogDocument', function (event, data) {
                loadAbsensi();
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
            Inquiry Absensi
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
                                <s:form id="inquiryAbsensi" method="post" theme="simple" namespace="/absensi" action="cronInquiry_absensi" cssClass="form-horizontal">
                                    <table >
                                        <tr>
                                            <td>
                                                <label class="control-label"><small>Unit :</small></label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:if test='absensiPegawai.branchId == "KP"'>
                                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="absensiPegawai.branchId"
                                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                    </s:if>
                                                    <s:else>
                                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="absensiPegawai.branchId" disabled="true"
                                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        <s:hidden id="branchId" name="absensiPegawai.branchId"/>
                                                    </s:else>

                                                </table>
                                            </td>
                                        </tr>
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
                                                        <button id="btnProses" type="button" class="btn btn-success">
                                                            <i class="fa fa-save"></i> Save
                                                        </button>
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
                                <s:form id="prosesAbsensi" method="post" theme="simple" namespace="/absensi" action="saveAdd_absensi" cssClass="form-horizontal">
                                    <s:hidden name="addOrEdit"/>
                                    <s:hidden name="delete"/>
                                    <table>
                                        <tr>
                                            <td width="10%" align="center">
                                                <%@ include file="/pages/common/message.jsp" %>
                                            </td>
                                        </tr>
                                    </table>
                                    <div class="form-group">
                                        <br>
                                        <div class="col-sm-offset-1 col-sm-10">
                                            <sj:submit targets="crud" type="button" cssClass="btn btn-success"
                                                       formIds="prosesAbsensi" id="btnProsesSave" name="save" cssStyle="display: none"
                                                       onBeforeTopics="beforeProcessSaveAbsensi"
                                                       onCompleteTopics="closeDialog,successDialog2"
                                                       onSuccessTopics="successDialog2"
                                                       onErrorTopics="errorDialog">
                                                <i class="fa fa-refresh"></i>
                                                Proses
                                            </sj:submit>
                                        </div>
                                    </div>
                                    <br>
                                    <br>
                                    <center>
                                        <table id="showdata" width="95%">
                                            <tr>
                                                <td align="center">
                                                    <table style="width: 100%;" class="absensiDetailTable table table-bordered" id="absensiDetailTable">
                                                    </table>
                                                </td>
                                            </tr>

                                        </table>
                                    </center>
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

                                                            <sj:dialog id="waiting_dialog_inquiry" openTopics="showDialogInquiryg"
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
<div id="modal-list" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Detail Absensi</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormListAbsensi">
                    <center>
                        <table id="showdata2" width="80%">
                            <tr>
                                <td align="center">
                                    <table style="width: 100%;" class="listAbsensiTable table table-bordered" id="listAbsensiTable">
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
<script>
    window.saveTmp = function(){
            var values = new Array();
            var status ;
            $.each($("input[name='checkApprove[]']:checked"), function() {
                values.push($(this).val());
            });
            if(values.length > 0){
                dwr.engine.setAsync(false);
                $.each($("input[name='checkApprove[]']:checked"), function() {
                    AbsensiAction.saveTmp($(this).val(),function(listdata) {
                        if (listdata == '00'){
                            status ="sukses";
                        }else{
                            status ="failed";
                            return false;
                        }
                    });
                });
                if(status=="sukses"){
                    $('#saveAdd').show();
                    $('#cancel').show();
                    alert("Proses Sukses");
                    loadFinal();
                    $('#save').hide();
                }else{
                    $('#error_dialog').dialog('open');
                    $('#saveAdd').hide();
                    $('#cancel').hide();
                }
            }else{
                alert('Silahkan Centang Salah Satu Absensi !');
            }
    };
    window.loadFinal =  function() {
        var tanggal = $('#tanggal').val();
        $('.absensiTable').find('tr').remove();
        $('.absensiDetailTable').find('tbody').remove();
        $('.absensiDetailTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        AbsensiAction.searchAbsensiFinal(function (listdata) {
            tmp_table = "<thead style='font-size: 10px' ><tr class='active'>" +
                // "<th rowspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90'>View</th>" +
                // "<th rowspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90'><input type='checkbox' id='checkAll'></th>" +
                "<th rowspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90'>Tanggal</th>" +
                "<th rowspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90'>Hari</th>" +
                "<th rowspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90'>PIN</th>" +
                "<th rowspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90'>NIP</th>" +
                "<th rowspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90'>Nama</th>" +
                "<th rowspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90'>Bidang/Divisi</th>" +
                "<th colspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90'>On Call</th>" +
                "<th colspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90'>Jam Kerja</th>" +
                "<th colspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90'>Jam Kerja 2</th>" +
                // "<th colspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90'>Lembur</th>" +
                "<th rowspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90''>Pengajuan Lembur</th>" +
                "<th rowspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90''>Realisasi Lembur</th>" +
                "<th rowspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90''>Perhitungan Lembur</th>" +
                "<th rowspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90''>Biaya Lembur</th>" +
                "<th rowspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90''>Status On Call</th>" +
                "<th rowspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90''>Status</th>" +
                "<th rowspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90''>Status 2</th>" +
                "<th rowspan='2' style='text-align: center; vertical-align: middle; background-color:  #90ee90''>Cuti Ganti Hari</th>" +
                "</tr>" +
                "<tr class='active'>" +
                "<th style='text-align: center; vertical-align: middle; background-color:  #90ee90'>In</th>" +
                "<th style='text-align: center; vertical-align: middle; background-color:  #90ee90'>Out</th>" +
                "<th style='text-align: center; vertical-align: middle; background-color:  #90ee90'>In</th>" +
                "<th style='text-align: center; vertical-align: middle; background-color:  #90ee90'>Out</th>" +
                "<th style='text-align: center; vertical-align: middle; background-color:  #90ee90'>In</th>" +
                "<th style='text-align: center; vertical-align: middle; background-color:  #90ee90'>Out</th>" +
                // "<th style='text-align: center; vertical-align: middle; background-color:  #90ee90'>Start</th>" +
                // "<th style='text-align: center; vertical-align: middle; background-color:  #90ee90'>End</th>" +
                "</tr>" +
                "</thead>";
            var i = i;
            $.each(listdata, function (i, item) {
                var combo = '<input type="checkbox" id="check" name="checkApprove[]" value="' + item.pin + '" class="check" checked >';

                tmp_table += '<tr style="font-size: 9px;" ">' +
                    <%--'<td align="center">' +--%>
                    <%--"<a href='javascript:;' class ='item-view-detail' data ='" + item.pin + "' tanggal ='" + item.stTanggal + "' nip ='" + item.nip + "' lemburAwal ='" + item.awalLembur + "' lemburAkhir ='" + item.selesaiLembur + "'>" +--%>
                    <%--"<img border='0' src='<s:url value='/pages/images/view.png'/>' name='icon_view'>" +--%>
                    <%--'</a>' +--%>
                    <%--'</td>' +--%>
                    // '<td align="center">' + combo + '</td>' +
                    '<td align="center">' + item.stTanggal + '</td>' +
                    '<td align="center">' + item.tipeHariName + '</td>' +
                    '<td align="center">' + item.pin + '</td>' +
                    '<td align="center">' + item.nip + '</td>' +
                    '<td align="center">' + item.nama + '</td>' +
                    '<td align="center" class="ceknull">' + item.divisi + '</td>' +
                    '<td align="center" class="ceknull">' + item.jamMasukOnCall + '</td>' +
                    '<td align="center" class="ceknull">' + item.jamPulangOnCall + '</td>' +
                    '<td align="center" class="ceknull">' + item.jamMasuk + '</td>' +
                    '<td align="center" class="ceknull">' + item.jamPulang + '</td>' +
                    '<td align="center" class="ceknull">' + item.jamMasuk2 + '</td>' +
                    '<td align="center" class="ceknull">' + item.jamPulang2 + '</td>' +
                    // '<td align="center" class="ceknull">' + item.awalLembur + '</td>' +
                    // '<td align="center" class="ceknull">' + item.selesaiLembur + '</td>' +
                    '<td align="center">' + item.pengajuanLembur + '</td>' +
                    '<td align="center">' + item.realisasiJamLembur + '</td>' +
                    '<td align="center">' + item.jamLembur + '</td>' +
                    '<td align="center" class="ceknull">' + item.stBiayaLembur + '</td>' +
                    '<td align="center" class="ceknull">' + item.statusNameOnCall + '</td>' +
                    '<td align="center" class="ceknull">' + item.statusName + '</td>' +
                    '<td align="center" class="ceknull">' + item.statusName2 + '</td>' +
                    '<td align="center" class="ceknull">' + item.flagCutiGantiHari + '</td>' +
                    "</tr>";
            });
            $('.absensiDetailTable').append(tmp_table);
            $("#checkAll").change(function () {
                $('input:checkbox').not(this).prop('checked', this.checked);
            });
            $('#cancel').show();
            $('#saveAdd').show();
            $("#absensiDetailTable .ceknull:contains('null')").html("-");
        });
    };
    $(document).ready(function(){
        loadFinal();
        $('#tanggal1').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#tanggal2').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $("#btnProses").click(function() {
            $("#btnProsesSave").trigger( "click" );
        });
        $('input:checkbox').removeAttr('checked');
    });
</script>