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
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasirRawatJalanAction.js"/>'></script>
    <script type="text/javascript">
        function callSearch2() {
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
        }

        function link() {
            window.location.href = "<s:url action='initFormPengajuanSetorPpn_pengajuanSetor.action'/>";
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
                var kas = $('#bank').val();

                if (tanggal != '' && keterangan != ''&&kas!='') {
                    if (confirm('Do you want to proses this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');
                    } else {
                        event.originalEvent.options.submit = false;
                    }
                } else {
                    event.originalEvent.options.submit = false;
                    var msg = "";
                    if (tanggal == '') {
                        msg += 'Field <strong>Tanggal</strong> is required.' + '<br/>';
                    }
                    if (keterangan == '') {
                        msg += 'Field <strong>Keterangan</strong> is required.' + '<br/>';
                    }
                    if ( kas == '') {
                        msg += 'Field <strong>Kas</strong> is required.' + '<br/>';
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
            Pengajuan Setor
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-save"></i> Hasil Pembagian PPN Masukan B2 dan B3</h3>
                    </div>
                    <s:form id="saveAddPengajuanSetor" enctype="multipart/form-data" method="post"
                            namespace="/pengajuanSetor"
                            action="saveAddPengajuanSetorPpn_pengajuanSetor.action" theme="simple">
                        <s:hidden name="pengajuanSetor.branchId" />
                        <s:hidden name="pengajuanSetor.jumlahPpnKeluaran" id="jumlahPpnKeluaran" />
                        <s:hidden name="pengajuanSetor.jumlahPpnMasukanB2" id="jumlahPpnMasukanB2"/>
                        <s:hidden name="pengajuanSetor.jumlahPpnMasukanB3" id="jumlahPpnMasukanB3" />
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
                                    <label class="col-sm-offset-2 col-sm-3" style="margin-top: 7px">Keterangan Untuk Jurnal</label>
                                    <div class="col-md-4">
                                        <s:textarea id="keterangan" rows="3" cssStyle="margin-top: 7px" onkeypress="$(this).css('border','')"
                                                    name="pengajuanSetor.keterangan" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-offset-2 col-sm-3" style="margin-top: 7px">Kas </label>
                                    <div class="col-md-4">
                                        <s:select list="#{'':''}" cssStyle="margin-top: 7px"
                                                  id="bank" name="pengajuanSetor.kas" cssClass="form-control" />
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
                                                <label class="control-label col-sm-offset-2 col-sm-3" style="margin-top: 7px">Jumlah PPN Masukan B2 (RP) </label>
                                                <div class="col-sm-4">
                                                    <s:textfield name="pengajuanSetor.stJumlahPpnMasukanB2" id="jumlah_ppn_masukan_b2" cssStyle="text-align: right"
                                                                 readonly="true" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="control-label col-sm-offset-2 col-sm-3" style="margin-top: 14px">Jumlah PPN Masukan B3 (RP)</label>
                                                <div class="col-sm-4">
                                                    <s:textfield name="pengajuanSetor.stJumlahPpnMasukanB3" cssStyle="margin-top: 7px;text-align: right" id="jumlah_ppn_masukan_b3"
                                                                 readonly="true" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="control-label col-sm-offset-2 col-sm-3" style="margin-top: 14px">Jumlah PPn Keluaran (RP)</label>
                                                <div class="col-sm-4">
                                                    <s:textfield name="pengajuanSetor.stJumlahPpnKeluaran" cssStyle="margin-top: 7px;text-align: right" id="jumlah_ppn_keluaran"
                                                                 readonly="true" cssClass="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="control-label col-sm-offset-2 col-sm-3" style="margin-top: 14px">Jumlah Seluruhnya (RP) </label>
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
                                <a type="button" class="btn btn-danger"
                                   href="addPengajuanSetorPpn_pengajuanSetor.action">
                                    <i class="fa fa-arrow-left"></i> Go Back
                                </a>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box-header with-border"></div>
                                    <div class="box-header with-border">
                                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar PPN</h3>
                                    </div>
                                    <div class="box-body">
                                        <ul class="nav nav-pills" id="tabPane">
                                            <li class="active"><a href="#masukanB2" data-toggle="tab">PPN Masukan B2</a></li>
                                            <li><a href="#masukanB3" data-toggle="tab">PPN Masukan B3</a></li>
                                            <li><a href="#keluaran" data-toggle="tab">PPN Keluaran</a></li>
                                        </ul>
                                        <br>
                                        <br>
                                        <div class="tab-content clearfix">
                                            <div id="masukanB2" class="tab-pane active col-md-12">
                                                <table id="table1" class="table table-bordered table-striped sortTable">
                                                </table>
                                            </div>
                                            <div id="masukanB3" class="tab-pane fade">
                                                <table id="table2" class="table table-bordered table-striped sortTable">
                                                </table>
                                            </div>
                                            <div id="keluaran" class="tab-pane fade">
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

                                    <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                               resizable="false"
                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                               buttons="{
                                                              'OK':function() {
                                                                        callSearch2();
                                                                      link();
                                                                   }
                                                            }"
                                    >
                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                             name="icon_success">
                                        Pengajuan Setor has been successfully created.
                                    </sj:dialog>

                                    <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true"
                                               resizable="false"
                                               height="250" width="600" autoOpen="false" title="Error Dialog"
                                               buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close'); }
                                                                    }"
                                    >
                                        <div class="alert alert-error fade in">
                                            <label class="control-label" align="left">
                                                <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                     name="icon_error"> System Found : <p id="errorMessage"></p>
                                            </label>
                                        </div>
                                    </sj:dialog>

                                    <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog"
                                               modal="true" resizable="false"
                                               height="280" width="500" autoOpen="false" title="Warning"
                                               buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }"
                                    >
                                        <div class="alert alert-error fade in">
                                            <label class="control-label" align="left">
                                                <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                     name="icon_error"> Please check this field :
                                                <br/>
                                                <center>
                                                    <div id="errorValidationMessage"></div>
                                                </center>
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
    window.loadSessionPpnMasukanB2 = function () {
        $('#table1').find('tbody').remove();
        $('#table1').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PengajuanSetorAction.searchDataSessionPpnMasukanB2(function (listdata) {

            tmp_table = "<thead style='font-size: 14px;' ><tr class='active'>" +
                "<th style='text-align: center; background-color:  #90ee90'>ID</th>" +
                "<th style='text-align: center; background-color:  #90ee90''>Sumber Jurnal</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>PPN (RP)</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Keterangan</th>" +
                "</tr></thead>";
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td >' + item.transaksiId + '</td>' +
                    '<td align="left">' + item.personId + '</td>' +
                    '<td align="right">' + item.stJumlah + '</td>' +
                    '<td align="left">' + item.note + '</td>' +
                    "</tr>";
            });
            $('#table1').append(tmp_table);
        });
    };
    window.loadSessionPpnMasukanB3 = function () {
        $('#table2').find('tbody').remove();
        $('#table2').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PengajuanSetorAction.searchDataSessionPpnMasukanB3(function (listdata) {

            tmp_table = "<thead style='font-size: 14px;' ><tr class='active'>" +
                "<th style='text-align: center; background-color:  #90ee90'>ID</th>" +
                "<th style='text-align: center; background-color:  #90ee90''>Sumber Jurnal</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>PPN (RP)</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Keterangan</th>" +
                "</tr></thead>";
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td >' + item.transaksiId + '</td>' +
                    '<td align="left">' + item.personId + '</td>' +
                    '<td align="right">' + item.stJumlah + '</td>' +
                    '<td align="left">' + item.note + '</td>' +
                    "</tr>";
            });
            $('#table2').append(tmp_table);
        });
    };
    window.loadSessionPpnKeluaran = function () {
        $('#table3').find('tbody').remove();
        $('#table3').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PengajuanSetorAction.searchDataSessionPpnKeluaran(function (listdata) {
            tmp_table = "<thead style='font-size: 14px;' ><tr class='active'>" +
                "<th style='text-align: center; background-color:  #90ee90'>ID</th>" +
                "<th style='text-align: center; background-color:  #90ee90''>Sumber Jurnal</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>PPN (RP)</th>" +
                "<th style='text-align: center; background-color:  #90ee90'>Keterangan</th>" +
                "</tr></thead>";
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td >' + item.transaksiId + '</td>' +
                    '<td align="left">' + item.personId + '</td>' +
                    '<td align="right">' + item.stJumlah + '</td>' +
                    '<td align="left">' + item.note + '</td>' +
                    "</tr>";
            });
            $('#table3').append(tmp_table);

        });
    };

    $('document').ready(function () {
        loadSessionPpnMasukanB2();
        loadSessionPpnMasukanB3();
        loadSessionPpnKeluaran();
        selectPembayaran();

        $('#table1').DataTable({
            paging: false,
            "bDestroy": true,
            "order": [[1, "asc"]]
        });
        $('#table2').DataTable({
            paging: false,
            "bDestroy": true,
            "order": [[1, "asc"]]
        });
        $('#table3').DataTable({
            paging: false,
            "bDestroy": true,
            "order": [[1, "asc"]]
        });
    });
    function selectPembayaran(){
        var option = '<option value="">[Select One]</option>';
        KasirRawatJalanAction.getListPembayaran(function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option += '<option value="'+item.coa+'">'+item.pembayaranName+'</option>';
                });
                $('#bank').html(option);
            }else{
                $('#bank').html(option);
            }
        });
    }
</script>