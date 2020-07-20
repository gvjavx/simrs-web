<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>
<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PengajuanSetorAction.js"/>'></script>
    <script type="text/javascript">
        function callSearch2() {
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
        }
        function link(){
            window.location.href="<s:url action='initFormPengajuanSetorPph21_pengajuanSetor.action'/>";
        }

        $(document).ready(function () {
            window.close2 = function () {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');
            };
            $.subscribe('beforeProcessSave', function (event, data) {
                var tanggal = $('#tanggal_pengajuan_setor').val();
                var keterangan = $('#keterangan').val();
                if (tanggal!=''&&keterangan!='') {
                    if (confirm('Do you want to proses this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');
                    } else {
                        event.originalEvent.options.submit = false;
                    }
                }else{
                    event.originalEvent.options.submit = false;
                    var msg="";
                    if ( tanggal == '') {
                        msg += 'Field <strong>Tanggal</strong> is required.' + '<br/>';
                    }
                    if ( keterangan == '') {
                        msg += 'Field <strong>Keterangan</strong> is required.' + '<br/>';
                    }
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
            Hasil Pencarian Pengajuan Setor PPH21
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-save"></i> Pengajuan Setor</h3>
                    </div>
                    <s:form id="saveAddPengajuanSetor" enctype="multipart/form-data" method="post" namespace="/pengajuanSetor"
                            action="saveAddPengajuanSetorPph21_pengajuanSetor.action" theme="simple">
                        <s:hidden name="pengajuanSetor.branchId" />
                        <s:hidden name="pengajuanSetor.jumlahPph21Payroll" id="jumlahPph21Payroll" />
                        <s:hidden name="pengajuanSetor.jumlahPph21Kso" id="jumlahPph21Kso"/>
                        <s:hidden name="pengajuanSetor.jumlahPph21Pengajuan" id="jumlahPph21Pengajuan" />
                        <s:hidden name="pengajuanSetor.jumlahSeluruhnya" id="jumlahSeluruhnya" />
                        <s:hidden name="pengajuanSetor.bulan" />
                        <s:hidden name="pengajuanSetor.tahun" />

                        <div class="box-body">
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-sm-offset-2 col-sm-3" style="margin-top: 14px">Tanggal Pengajuan Setor</label>
                                    <div class="col-md-4">
                                        <div class="input-group date" style="margin-top: 7px" id="stTanggalPengajuanSetor">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input class="form-control datepicker2" id="tanggal_pengajuan_setor" name="pengajuanSetor.stRegisteredDate">
                                            <script>
                                                $("#tanggal_pengajuan_setor").datepicker({
                                                    setDate: new Date(),
                                                    autoclose: true,
                                                    changeMonth: true,
                                                    changeYear:true,
                                                    dateFormat:'dd-mm-yy'
                                                });
                                                $("#tanggal_pengajuan_setor").datepicker("setDate", new Date());
                                            </script>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-offset-2 col-sm-3" style="margin-top: 7px">Keterangan</label>
                                    <div class="col-md-4">
                                        <s:textarea id="keterangan" rows="3" cssStyle="margin-top: 7px" onkeypress="$(this).css('border','')"
                                                    name="pengajuanSetor.keterangan" cssClass="form-control"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box-header with-border"></div>
                                    <div class="box-header with-border">
                                        <h3 class="box-title"><i class="fa fa-th-list"></i> Data Summary</h3>
                                    </div>
                                    <div class="box-body">
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="control-label col-sm-offset-2 col-sm-3" style="margin-top: 7px">( X ) Jumlah PPH21 Payroll (RP) </label>
                                                <div class="col-sm-4">
                                                    <s:textfield name="pengajuanSetor.stJumlahPph21Payroll" id="jumlah_pph_21_payroll" cssStyle="text-align: right"
                                                                 readonly="true" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="control-label col-sm-offset-2 col-sm-3" style="margin-top: 14px">( Y ) Jumlah PPH21 Dokter KSO (RP)</label>
                                                <div class="col-sm-4">
                                                    <s:textfield name="pengajuanSetor.stJumlahPph21Kso" cssStyle="margin-top: 7px;text-align: right" id="jumlah_pph_21_kso"
                                                                 readonly="true" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="control-label col-sm-offset-2 col-sm-3" style="margin-top: 14px">( Z ) Jumlah PPH21 Rekanan Dipotong (RP)</label>
                                                <div class="col-sm-4">
                                                    <s:textfield name="pengajuanSetor.stJumlahPph21Pengajuan" cssStyle="margin-top: 7px;text-align: right" id="jumlah_pph_21_pengajuan"
                                                                 readonly="true" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="control-label col-sm-offset-2 col-sm-3" style="margin-top: 14px">( X+Y+Z ) Jumlah Seluruhnya (RP) </label>
                                                <div class="col-sm-4">
                                                    <s:textfield name="pengajuanSetor.stJumlahSeluruhnya" cssStyle="margin-top: 7px;text-align: right" id="jumlah_seluruhnya"
                                                                 readonly="true" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-offset-5">
                                <br>
                                <sj:submit targets="crud" type="button" cssClass="btn btn-primary"
                                           formIds="saveAddPengajuanSetor" id="save" name="save"
                                           onBeforeTopics="beforeProcessSave"
                                           onCompleteTopics="closeDialog,successDialog"
                                           onSuccessTopics="successDialog"
                                           onErrorTopics="errorDialog">
                                    <i class="fa fa-save"></i>
                                    Save
                                </sj:submit>
                                <a type="button" class="btn btn-danger" href="addPengajuanSetorPph21_pengajuanSetor.action">
                                    <i class="fa fa-arrow-left"></i> Go Back
                                </a>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box-header with-border"></div>
                                    <div class="box-header with-border">
                                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar PPH21</h3>
                                    </div>
                                    <div class="box-body">
                                        <ul class="nav nav-pills" id="tabPane">
                                            <li class="active"><a href="#payroll" data-toggle="tab">PPH21 Payroll</a></li>
                                            <li><a href="#kso" data-toggle="tab">PPH21 Dokter KSO</a></li>
                                            <li><a href="#pengajuan" data-toggle="tab">PPH21 Rekanan yang Dipotong</a></li>
                                        </ul>
                                        <br>
                                        <br>
                                        <div class="tab-content clearfix">
                                            <div id="payroll" class="tab-pane active col-md-12">
                                                <table id="table1" class="table table-bordered table-striped sortTable">
                                                </table>
                                            </div>
                                            <div id="kso" class="tab-pane fade">
                                                <table id="table2" class="table table-bordered table-striped sortTable">
                                                </table>
                                            </div>
                                            <div id="pengajuan" class="tab-pane fade">
                                                <table id="table3" class="table table-bordered table-striped sortTable">
                                                </table>
                                            </div>
                                        </div>
                                        <div class="box-header with-border"></div>
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

                                    <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                               buttons="{
                                                              'OK':function() {
                                                                        callSearch2();
                                                                      link();
                                                                   }
                                                            }"
                                    >
                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                        Pengajuan Setor has been successfully created.
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
<script>
    window.loadSessionPayrollPph= function(){
        $('#table1').find('tbody').remove();
        $('#table1').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PengajuanSetorAction.searchDataSessionPph21Payroll(function (listdata) {

            tmp_table = "<thead style='font-size: 14px;' ><tr class='active'>" +
                "<th style='text-align: center; background-color:  #90ee90'>ID</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Tipe</th>" +
                "<th style='text-align: center; background-color:  #90ee90''>Posisi</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>PPH (RP)</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Keterangan</th>" +
                // "<th style='text-align: center; background-color:  #90ee90'>Status</th>" +
                "</tr></thead>";
            $.each(listdata, function (i, item) {
                var check = "";
                if (item.dibayar=="Y"){
                    check = "<input type='checkbox' class='checkboxPayroll' id='"+ item.transaksiId +"' checked>";
                } else{
                    check = "<input type='checkbox' class='checkboxPayroll' id='"+ item.transaksiId +"'>";
                }

                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center">' + item.transaksiId + '</td>' +
                    '<td align="center">' + item.tipe + '</td>' +
                    '<td align="left">' + item.posisiName + '</td>' +
                    '<td align="right">' + item.stJumlah + '</td>' +
                    '<td align="left">' + item.note + '</td>' +
                    // '<td align="center">'+check + '</td>' +
                    "</tr>";
            });
            $('#table1').append(tmp_table);
        });
    };
    window.loadSessionKso= function(){
        $('#table2').find('tbody').remove();
        $('#table2').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PengajuanSetorAction.searchDataSessionPph21Kso(function (listdata) {
            tmp_table = "<thead style='font-size: 14px;' ><tr class='active'>" +
                "<th style='text-align: center; background-color:  #90ee90'>ID</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Tipe</th>" +
                "<th style='text-align: center; background-color:  #90ee90''>Nama Dokter</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>PPH (RP)</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Keterangan</th>" +
                // "<th style='text-align: center; background-color:  #90ee90'>Status</th>" +
                "</tr></thead>";
            $.each(listdata, function (i, item) {
                var check = "";
                if (item.dibayar=="Y"){
                    check = "<input type='checkbox' class='checkboxKso' id='"+ item.transaksiId +"' checked>";
                } else{
                    check = "<input type='checkbox' class='checkboxKso' id='"+ item.transaksiId +"'>";
                }

                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center" >' + item.transaksiId + '</td>' +
                    '<td align="center">' + item.tipe + '</td>' +
                    '<td align="left">' + item.nama + '</td>' +
                    '<td align="right">' + item.stJumlah + '</td>' +
                    '<td align="left">' + item.note + '</td>' +
                    // '<td align="center">'+check + '</td>' +
                    "</tr>";
            });
            $('#table2').append(tmp_table);
        });
    };
    window.loadSessionPengajuan= function(){
        $('#table3').find('tbody').remove();
        $('#table3').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PengajuanSetorAction.searchDataSessionPph21Pengajuan(function (listdata) {
            tmp_table = "<thead style='font-size: 14px;' ><tr class='active'>" +
                "<th style='text-align: center; background-color:  #90ee90'>ID</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Tipe</th>" +
                "<th style='text-align: center; background-color:  #90ee90''>Posisi</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>PPH (RP)</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Keterangan</th>" +
                // "<th style='text-align: center; background-color:  #90ee90'>Status</th>" +
                "</tr></thead>";
            $.each(listdata, function (i, item) {
                var check = "";
                if (item.dibayar=="Y"){
                    check = "<input type='checkbox' class='checkboxPengajuan' id='"+ item.transaksiId +"' checked>";
                } else{
                    check = "<input type='checkbox' class='checkboxPengajuan' id='"+ item.transaksiId +"'>";
                }
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center">' + item.transaksiId + '</td>' +
                    '<td align="center">' + item.tipe + '</td>' +
                    '<td align="left">' + item.posisiName + '</td>' +
                    '<td align="right">' + item.stJumlah + '</td>' +
                    '<td align="left">' + item.note + '</td>' +
                    // '<td align="center">'+check + '</td>' +
                    "</tr>";
            });
            $('#table3').append(tmp_table);
        });
    };
    $('document').ready(function () {
        loadSessionPayrollPph();
        loadSessionKso();
        loadSessionPengajuan();

        $('.checkboxPayroll').change(function () {
            var id = $(this).prop('id');
            var check = $(this).prop('checked');
            PengajuanSetorAction.editSessionPayroll(id,check,function (data) {
                $('#jumlah_pph_21_payroll').val(data.stJumlahPph21Payroll);
                $('#jumlah_seluruhnya').val(data.stJumlahSeluruhnya);
                $('#jumlahPph21Payroll').val(data.jumlahPph21Payroll);
                $('#jumlahSeluruhnya').val(data.jumlahSeluruhnya);
            });
        });
        $('.checkboxKso').change(function () {
            var id = $(this).prop('id');
            var check = $(this).prop('checked');
            PengajuanSetorAction.editSessionKso(id,check,function (data) {
                $('#jumlah_pph_21_kso').val(data.stJumlahPph21Kso);
                $('#jumlah_seluruhnya').val(data.stJumlahSeluruhnya);
                $('#jumlahPph21Kso').val(data.jumlahPph21Kso);
                $('#jumlahSeluruhnya').val(data.jumlahSeluruhnya);
            });
        });
        $('.checkboxPengajuan').change(function () {
            var id = $(this).prop('id');
            var check = $(this).prop('checked');
            PengajuanSetorAction.editSessionPengajuan(id,check,function (data) {
                $('#jumlah_pph_21_pengajuan').val(data.stJumlahPph21Pengajuan);
                $('#jumlah_seluruhnya').val(data.stJumlahSeluruhnya);
                $('#jumlahPph21Pengajuan').val(data.jumlahPph21Pengajuan);
                $('#jumlahSeluruhnya').val(data.jumlahSeluruhnya);
            });
        });

        $('#table1').DataTable({
            paging: false,
            "order": [[3, "asc"]]
        });
        $('#table2').DataTable({
            paging: false,
            "order": [[1, "asc"]]
        });
        $('#table3').DataTable({
            paging: false,
            "order": [[1, "asc"]]
        })
    });
</script>