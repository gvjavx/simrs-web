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
        .pendapatanTable th{
            text-align: center;
            vertical-align: middle;
        }
        .kv-reqd {
            color: red;
            font-family: monospace;
            font-weight: normal;
        }
        /*.modal-dialog{*/
            /*width: 1150px;*/
            /*margin-top: auto;*/
        /*}*/
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
        function formaterDate(dateTime) {

            var today = "";
            if (dateTime != '' && dateTime != null) {

                today = new Date(dateTime);
                var dd = String(today.getDate()).padStart(2, '0');
                var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
                var yyyy = today.getFullYear();
                var hh = today.getHours();
                var min = today.getMinutes();
                var sec = today.getSeconds();
                today = dd + '-' + mm + '-' + yyyy + ' '+ hh +':'+ min;
            }
            return today;
        }

        function callSearch2() {
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
        }

        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        function link(){
            window.location.href="<s:url action='initForm_pendapatanDokter'/>";
        }
        function link2(){
            window.location.href="<s:url action='goToResult_pendapatanDokter'/>";
        }
        $(document).ready(function () {
            <%--window.close = function () {--%>
                <%--$('#view_dialog_menu').dialog('close');--%>
                <%--$('#info_dialog').dialog('close');--%>
                <%--window.location.href = "<s:url action='search_pendapatanDokter.action'/>";--%>
            <%--};--%>
            <%--window.close2 = function () {--%>
                <%--//$('#waiting_dialog').dialog('close');--%>
                <%--$('#view_dialog_menu').dialog('close');--%>
                <%--$('#info_dialog').dialog('close');--%>
            <%--};--%>
            $.subscribe('beforeProcessSavePendapatan', function (event, data) {
                var values;
                values = $('input:checkbox:checked').length;
                if (values!=0) {
                    if (confirm('Do you want to proses this record?')) {
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
            $.subscribe('beforeProcessInquiryPendapatan', function (event, data) {
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
            Hasil Pendapatan Dokter
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
                                <s:form id="inquiryPendapatanDokter" method="post" theme="simple" namespace="/pendapatanDokter" action="pendapatan_pendapatanDokter" cssClass="form-horizontal">
                                    <table >
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
                                                    <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                    <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="pendapatanDokter.branchId" disabled="true"
                                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                    <s:hidden id="branchId" name="pendapatanDokter.branchId" />
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
                                                              id="periodeBulan" name="pendapatanDokter.bulan" disabled="true"
                                                              headerKey="" headerValue="[Select One]" cssClass="form-control" />
                                                    <s:hidden id="periodeBulan" name="pendapatanDokter.bulan" />
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
                                                    <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="periodeTahun" disabled="true"
                                                              name="pendapatanDokter.tahun" required="true" headerKey=""
                                                              headerValue="[Select one]"/>
                                                    <s:hidden id="periodeTahun" name="pendapatanDokter.tahun" />
                                                </table>
                                            </td>
                                            <script>
                                                var dt = new Date();
                                                $('#periodeBulan').val(("0" + (dt.getMonth() + 1)).slice(-2));
                                                $('#periodeTahun').val(dt.getFullYear());
                                            </script>
                                        </tr>
                                        <tr>
                                            <td align="center" colspan="2">
                                                <br>
                                                <div class="form-group">
                                                    <br>
                                                    <div class="form-actions">
                                                        <%--<sj:submit targets="crud" type="button" cssClass="btn btn-primary"--%>
                                                                   <%--formIds="inquiryPendapatanDokter" id="inquiry" name="inquiry"--%>
                                                                   <%--onBeforeTopics="beforeProcessInquiryPendapatan"--%>
                                                                   <%--onCompleteTopics="closeDialogInquiry,successDialogInquiry"--%>
                                                                   <%--onSuccessTopics="successDialogInquiry"--%>
                                                                   <%--onErrorTopics="errorDialog">--%>
                                                            <%--<i class="fa fa-refresh"></i>--%>
                                                            <%--Search--%>
                                                        <%--</sj:submit>--%>
                                                        <a href="add_pendapatanDokter.action" class="btn btn-danger"><i class="fa fa-arrow-left"></i> Back</a>
                                                        <button id="btnProses" type="button" class="btn btn-success">
                                                            <i class="fa fa-check"></i> Save
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
                                <s:form id="prosesSavePendapatan" method="post" theme="simple" namespace="/pendapatanDokter" action="save_pendapatanDokter" cssClass="form-horizontal">
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
                                                       formIds="prosesSavePendapatan" id="btnProsesSave" name="save" cssStyle="display: none"
                                                       onBeforeTopics="beforeProcessSavePendapatan"
                                                       onCompleteTopics="closeDialog,successDialog2"
                                                       onSuccessTopics="successDialog2"
                                                       onErrorTopics="errorDialog">
                                                <i class="fa fa-refresh"></i>
                                                Save
                                            </sj:submit>
                                        </div>
                                    </div>
                                    <br>
                                    <br>
                                    <center>
                                        <table id="showdata" width="95%">
                                            <tr>
                                                <td align="center">
                                                    <table style="width: 100%;" class="pendapatanTable table table-bordered" id="pendapatanTable">
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
                                                                Record has been process successfully.
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
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Detail Pendapatan Dokter</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myFormListAbsensi">
                    <center>
                        <table id="showdata2" width="80%">
                            <tr>
                                <td align="center">
                                    <table style="width: 100%;" class="listPendapatanTable table table-bordered" id="listPendapatanTable">
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

<div class="modal fade" id="modal-detail-pendapatan">
    <div class="modal-dialog" style="width: 1300px">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Detail Pendapatan</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-striped">
                            <tr>
                                <td><b>Dokter Id</b></td>
                                <td><span id="id_dokter"></span></td>
                            </tr>
                            <tr>
                                <td><b>Nama</b></td>
                                <td><span id="nama_dokter"></span></td>
                            </tr>
                            <tr>
                                <td><b>Unit</b></td>
                                <td><span id="nama_unit"></span></td>
                            </tr>
                            <tr>
                                <td><b>Kode Dokter</b></td>
                                <td><span id="kode_jabatan"></span></td>
                            </tr>
                            <tr>
                                <td><b>Bulan</b></td>
                                <td><span id="bulan"></span></td>
                            </tr>
                            <tr>
                                <td><b>Tahun</b></td>
                                <td><span id="tahun"></span></td>
                            </tr>
                            <tr style="display: none">
                                <td><b>Bruto</b></td>
                                <td align="right"><span id="bruto"></span></td>
                            </tr>
                            <tr>
                                <td><b>Pendapatan Rs</b></td>
                                <td align="right"><span id="pendapatan_rs"></span></td>
                            </tr>
                            <tr>
                                <td><b>Hr Bruto</b></td>
                                <td align="right"><span id="hr_bruto"></span></td>
                            </tr>
                            <tr>
                                <td><b>Dpp Pph 21</b></td>
                                <td align="right"><span id="dpp_pph_21"></span></td>
                                <td>
                                    <a id="btn" href="javascript:void(0)">
                                        <img sizes="30" id="btnViewPphLebih" border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_trash">
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td><b>Dpp Pph Komulatif</b></td>
                                <td align="right"><span id="dpp_pph_komulatif"></span></td>
                            </tr>
                            <tr>
                                <td><b>Pajak</b></td>
                                <td align="right"><span id="pajak"></span></td>
                            </tr>
                            <tr>
                                <td><b>Pot. Pajak</b></td>
                                <td align="right"><span id="pot_pajak"></span></td>
                            </tr>
                            <tr>
                                <td><b>Hr Aktifitas Netto</b></td>
                                <td align="right"><span id="hr_aktifitas_netto"></span></td>
                            </tr>
                            <tr>
                                <td><b>Pot. Ks</b></td>
                                <td align="right"><span id="pot_ks"></span></td>
                            </tr>
                            <tr>
                                <td><b>Hr Netto</b></td>
                                <td align="right"><span id="hr_netto"></span></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <td>Nama Pasien</td>
                    <td>Tanggal</td>
                    <td>Master Id</td>
                    <td>Nama Poli</td>
                    <td>Nama Activity Id</td>
                    <td>Biaya</td>
                    </thead>
                    <tbody id="body_detail">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div id="modal-pph" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Detail PPH Lebih</h4>
            </div>
            <div class="modal-body">
                <table style="width: 100%;" class="detailPphTable table table-bordered">
                </table>
            </div>
            <div class="modal-body">
                <table style="width: 100%;" class="detailPphLebihTable table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
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
    window.loadPendapatan =  function(){
        $('.pendapatanTable').find('tr').remove();
        $('.pendapatanTable').find('tbody').remove();
        $('.pendapatanTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        console.log("Test");
        PendapatanDokterAction.loadResultsPendapatan(function(listdata){
            if (listdata != ""){
                tmp_table = "<thead style='font-size: 12px' ><tr class='active'>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Detail</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Unit</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Id Dokter</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nama Dokter</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Kode Dokter</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Bulan</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc''>Tahun</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc''>Bruto</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc''>Pph Dipungut</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc''>Pot. Ks</th>"+
                        "<th style='text-align: center; color: #fff; background-color:  #3c8dbc''>Total Pendapatan</th>"+
                        "</tr></thead>";
                var i = i ;
                $.each(listdata, function (i, item) {
                    tmp_table += '<tr style="font-size: 11px;" ">' +
                            '<td align="center">' +
                            "<a href='javascript:;' class ='item-view-pendapatan' unit ='"+item.branchName+"' nama = '"+item.dokterName+"' kodeJabatan = '"+item.kodeJabatan+"' bulan ='"+item.bulan+"' " +
                            "tahun ='"+item.tahun+"' dokter ='"+item.dokterId+"' bruto = '"+item.stTotalBruto+"' pdptnRs = '"+item.stTotalPendapatanRs+"' " +
                            "hrBruto = '"+item.stTotalHrBruto+"' pph21 = '"+item.stTotalDppPph50+"' pphLebih = '"+item.stTotalPphLebih+"' komulatifLebih = '"+item.stTotalDppPph21KomulatifLebih+"' " +
                            "komulatif = '"+item.stTotalDppPph21Komulatif+"' pajak = '"+item.stTarif+"' potPjk = '"+item.stTotalPphDipungut+"' komulatifLebih = '"+item.stTotalDppPph21KomulatifLebih+"'" +
                            "hrAktifitas = '"+item.stTotalHrAktifitasNetto+"' potKs = '"+item.stTotalPotKs+"' gajiBersih = '"+item.stTotalGajiBersih+"' dokterId = '"+item.dokterId+"'>" +
                            "<img border='0' src='<s:url value='/pages/images/view.png'/>' name='icon_view'>"+
                            '</a>' +
                            '</td>' +
                            '<td style="text-align: center">' + item.branchName + '</td>' +
                            '<td style="text-align: center">' + item.dokterId + '</td>' +
                            '<td style="text-align: center">' + item.dokterName + '</td>' +
                            '<td style="text-align: center">' + item.kodeJabatan + '</td>' +
                            '<td style="text-align: center">' + item.bulan + '</td>' +
                            '<td align="center" class="ceknull">' + item.tahun + '</td>' +
                            '<td align="right" class="ceknull">' + item.stTotalHrBruto + '</td>' +
                            '<td align="right" class="ceknull">' + item.stTotalPphDipungut + '</td>' +
                            '<td align="right" class="ceknull">' + item.stTotalPotKs + '</td>' +
                            '<td align="right" class="ceknull">' + item.stTotalGajiBersih + '</td>' +
                            "</tr>";

//                    var myDate = new Date(item.tanggalLahir);
                    $('#branchId').val(item.branchId);
                    $('#periodeBulan').val(item.bulan);
                    $('#periodeTahun').val(item.tahun);
                });
            }else {
                alert("Data pada bulan dan tahun ini tidak ada");
                window.location.href = "<s:url action='add_pendapatanDokter.action'/>";
            }

            $('.pendapatanTable').append(tmp_table);
            $('#save').show();
            $("#pendapatanTable .ceknull:contains('null')").html("-");
        });
    };
    $(document).ready(function(){
        loadPendapatan();
//        $('#tanggal1').datepicker({
//            dateFormat: 'dd-mm-yy'
//        });
//        $('#tanggal2').datepicker({
//            dateFormat: 'dd-mm-yy'
//        });
        $("#btnProses").click(function() {
            $("#btnProsesSave").trigger( "click" );
        });
//        $('input:checkbox').removeAttr('checked');
//        $('#tanggal').change(function() {
//            var val= $('#tanggal').val();
//            AbsensiAction.searchStatusHari(val,function(listdata){
//                if (listdata == 'kerja') {
//                    $('#statusHari').val("hari_kerja");
//                } else if (listdata == 'libur'){
//                    $('#statusHari').val("hari_libur");
//                }
//            });
//        });
    });

    $('.pendapatanTable').on('click', '.item-view-pendapatan', function () {
//        var branchId = $(this).attr('data');
//        var bulan = $(this).attr('bulan');
//        var tahun = $(this).attr('tahun');
        var dokterId = $(this).attr('dokter');
        var pphLebih = $(this).attr('pphLebih');
        if (pphLebih == '0' || pphLebih == ''){
            $('#btn').hide();
        }else {
            $('#btn').show();
        }
        $('#bulan').text($(this).attr('bulan'));
        $('#tahun').text($(this).attr('tahun'));
        $('#id_dokter').text($(this).attr('dokter'));
        $('#nama_dokter').text($(this).attr('nama'));
        $('#kode_jabatan').text($(this).attr('kodeJabatan'));
        $('#nama_unit').text($(this).attr('unit'));
        $('#bruto').text($(this).attr('bruto'));
        $('#pendapatan_rs').text($(this).attr('pdptnRs'));
        $('#hr_bruto').text($(this).attr('hrbruto'));
        console.log($(this).attr('komulatifLebih'));
        if ($(this).attr('komulatifLebih') == 'null'){
            $('#dpp_pph_21').text($(this).attr('pph21'));
            $('#dpp_pph_komulatif').text($(this).attr('komulatif'));
            $('#pajak').text($(this).attr('pajak'));
            $('#pot_pajak').text($(this).attr('potPjk'));
            $('#hr_aktifitas_netto').text($(this).attr('hrAktifitas'));
            $('#pot_ks').text($(this).attr('potKs'));
            $('#hr_netto').text($(this).attr('gajiBersih'));
        }else {
            $('#dpp_pph_21').text('-');
            $('#dpp_pph_komulatif').text('-');
            $('#pajak').text('-');
            $('#pot_pajak').text('-');
        }
        $('#hr_aktifitas_netto').text($(this).attr('hrAktifitas'));
        $('#pot_ks').text($(this).attr('potKs'));
        $('#hr_netto').text($(this).attr('gajiBersih'));
        $('#body_detail').html('');

//        $('.listPendapatanTable').find('tbody').remove();
//        $('.listPendapatanTable').find('thead').remove();
//        dwr.engine.setAsync(false);
        var tmp_table = "";
        PendapatanDokterAction.searchDetailPendapatan(dokterId, function(response) {
            if(response.length > 0){
                $.each(response, function (i, item) {
                    tmp_table += '<tr>' +
                            '<td>'+item.namaPasien+'</td>'+
                            '<td>'+formaterDate(item.tanggal)+'</td>'+
                            '<td>'+item.masterId+'</td>'+
                            '<td>'+item.poliName+'</td>'+
                            '<td>'+item.activityName+'</td>'+
                            '<td align="right">'+item.bruto+'</td>'+
                            '</tr>'
                });

                $('#body_detail').html(tmp_table);
            }else{

            }
//            tmp_table = "<thead style='font-size: 12px' ><tr class='active'>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No Reg</th>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Jenis Rawat</th>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>KDJNSPAS</th>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Nama Pasien</th>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tanggal</th>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Keterangan</th>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tar.INACBG</th>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Tarif</th>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pend. RS</th>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>HR. Bruto</th>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Dpp PPh 21</th>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Komulatif</th>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pajak</th>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pot.Pajak</th>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Hr.Netto</th>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pot.Ks</th>"+
//                    "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Hr.Netto</th>"+
//                    "</tr></thead>";
//            var i = i;
//            $.each(listdata, function (i, item) {
//                tmp_table += '<tr style="font-size: 10px;" ">' +
//                        '<td align="center">' + (i + 1) + '</td>' +
//                        '<td align="center">' + item.noReg + '</td>' +
//                        '<td align="center">' + item.jenisRawat + '</td>' +
//                        '<td align="center">' + item.kdjnspas + '</td>' +
//                        '<td>' + item.namaPasien + '</td>' +
//                        '<td>' + formaterDate(item.tanggal) + '</td>' +
//                        '<td>' + item.keterangan + '</td>' +
//                        '<td align="right">' + item.tarifInacbg + '</td>' +
//                        '<td align="right">' + item.bruto + '</td>' +
//                        '<td align="right">' + item.pendapatanRs + '</td>' +
//                        '<td align="right">' + item.hrBruto + '</td>' +
//                        '<td align="right">' + item.dppPph21 + '</td>' +
//                        '<td align="right">' + item.dppPph21Komulatif + '</td>' +
//                        '<td align="right">' + item.tarif + '</td>' +
//                        '<td align="right">' + item.pphDipungut + '</td>' +
//                        '<td align="right">' + item.hrAktifitasNetto + '</td>' +
//                        '<td align="right">' + item.potKs + '</td>' +
//                        '<td align="right">' + item.gajiBersih + '</td>' +
//                        '<td align="center">' + item.namaPasien + '</td>' +
//                        '<td align="center">' + formaterDate(item.tanggal) + '</td>' +
//                        '<td align="center">' + item.keterangan + '</td>' +
//                        '<td align="center">' + item.bruto + '</td>' +
//                        "</tr>";
//            });
//            $('.listPendapatanTable').append(tmp_table);
//            $("#listPendapatanTable td:contains('null')").html("-");
        });
        $('#modal-detail-pendapatan').find('.modal-title').text('View Detail Pendapatan');
        $('#modal-detail-pendapatan').modal('show');
//        $('#modal-list').find('.modal-title').text('View Detail Pendapatan');
//        $('#modal-list').modal('show');
    });

    $('#btnViewPphLebih').click(function () {
        var nip = document.getElementById('id_dokter').innerHTML;;

        if (nip!=""){
            $('.detailPphTable').find('tbody').remove();
            $('.detailPphTable').find('thead').remove();
//            dwr.engine.setAsync(false);
//            var table = "";
//            PendapatanDokterAction.loadResultsPphLebih(nip,function(listdata) {
//                if (listdata!=""){
//                    console.log("Tes");
//                    table = "<thead style='font-size: 14px' >" +
////                            "<tr>" +
////                            "<th colspan='6' align='center' style='outline: 0px;text-align: center'>Pendapatan Pph Lebih</th>" +
////                            "</tr>"+
//                            "<tr class='active'>"+
////                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
//                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pph Komulatif</th>"+
//                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pph 21</th>"+
//                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pajak</th>"+
//                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pph Dipungut</th>"+
//                            "</tr></thead>";
//                    var i = i;
//                    $.each(listdata, function (i, item) {
//                        tmp_table += '<tr style="font-size: 12px;" ">' +
////                                '<td align="center">' + (i + 1) + '</td>' +
//                                '<td align="right">' + item.stTotalDppPph50 + '</td>' +
//                                '<td align="right">' + item.stTotalDppPph21Komulatif + '</td>' +
//                                '<td align="right">' + item.stTarif + '</td>' +
//                                '<td align="right">' + item.stTotalPphDipungut + '</td>' +
//                                "</tr>";
//                    });
//                    $('.detailPphTable').append(tmp_table);
//                    $("#detailPphTable").find("td:contains('null')").html("-");

            $('.detailPphLebihTable').find('tbody').remove();
            $('.detailPphLebihTable').find('thead').remove();
            dwr.engine.setAsync(false);
            var table = "";
            var tmp_table = "";
            PendapatanDokterAction.loadResultsPphLebih(nip,function(listdata) {
                if (listdata!=""){
                    console.log("Tes");
                    table = "<thead style='font-size: 14px' >" +
//                            "<tr>" +
//                            "<th colspan='6' align='center' style='outline: 0px;text-align: center'>Pendapatan Pph Lebih</th>" +
//                            "</tr>"+
                            "<tr class='active'>"+
//                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pph Komulatif</th>"+
                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pph 21</th>"+
                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pajak</th>"+
                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pph Dipungut</th>"+
                            "</tr></thead>";
                    var i = i;
                    $.each(listdata, function (i, item) {
                        table += '<tr style="font-size: 12px;" ">' +
//                                '<td align="center">' + (i + 1) + '</td>' +
                                '<td align="right">' + item.stTotalDppPph21Komulatif + '</td>' +
                                '<td align="right">' + item.stTotalDppPph50 + '</td>' +
                                '<td align="right">' + item.stTarif + '</td>' +
                                '<td align="right">' + item.stTotalPphDipungut + '</td>' +
                                "</tr>";
                    });

                    $('.detailPphTable').append(table);
                    $("#detailPphTable").find("td:contains('null')").html("-");

                    tmp_table = "<thead style='font-size: 14px' >" +
//                            "<tr>" +
//                            "<th colspan='6' align='center' style='outline: 0px;text-align: center'>Pendapatan Pph Lebih</th>" +
//                            "</tr>"+
                            "<tr class='active'>"+
//                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>No</th>"+
                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pph Komulatif Lebih</th>"+
                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pph 21 Lebih</th>"+
                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pajak</th>"+
                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pph Dipungut Lebih</th>"+
                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pph Lebih</th>"+
                            "<th style='text-align: center; color: #fff; background-color:  #3c8dbc'>Pph Final</th>"+
                            "</tr></thead>";
                    var i = i;
                    $.each(listdata, function (i, item) {
                        tmp_table += '<tr style="font-size: 12px;" ">' +
//                                '<td align="center">' + (i + 1) + '</td>' +
                                '<td align="right">' + item.stTotalDppPph21KomulatifLebih + '</td>' +
                                '<td align="right">' + item.stTotalDppPph21Lebih + '</td>' +
                                '<td align="right">' + item.stTarifLebih + '</td>' +
                                '<td align="right">' + item.stTotalPphDipungutLebih + '</td>' +
                                '<td align="right">' + item.stTotalPphLebih + '</td>' +
                                '<td align="right">' + item.stTotalPphFinal + '</td>' +
                                "</tr>";
                    });
                    $('.detailPphLebihTable').append(tmp_table);
                    $("#detailPphLebihTable").find("td:contains('null')").html("-");

                    $('#modal-pph').find('.modal-title').text('View Detail PPH Lebih');
                    $('#modal-pph').modal('show');
                }else {
                    alert("data PPH Lebih Kosong")
                }
            });

        }else{
            alert("Isi NIP terlebih dahulu");
        }
    });
</script>