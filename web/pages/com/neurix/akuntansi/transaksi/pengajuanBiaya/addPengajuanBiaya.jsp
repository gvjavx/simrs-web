<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KodeRekeningAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BudgetingAction.js"/>'></script>
    <script type='text/javascript'>
        function callSearch2() {
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
        }
        function link(){
            window.location.href="<s:url action='initForm_pengajuanBiaya'/>";
        }

        $.subscribe('beforeProcessSave', function (event, data) {
            var unit  = document.getElementById("branch_id").value;
            var divisi  = document.getElementById("divisi_id").value;
            var tanggal  = document.getElementById("tanggal").value;
            var coaBiaya  = document.getElementById("coa_biaya").value;
            var coaGiro  = document.getElementById("coa_giro").value;
            var jumlah  = document.getElementById("bayar").value;
            var sisaBudget  = document.getElementById("sisa_budget").value;
            var keterangan  = document.getElementById("keterangan").value;
            if ( unit != ''&& tanggal != ''&& coaBiaya != '' && coaGiro != ''&&jumlah!=""&&keterangan!=""&&divisi!=""&&sisaBudget!="") {
                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');
                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }
            } else {
                event.originalEvent.options.submit = false;
                var msg = "";
                if ( unit == '') {
                    msg += 'Field <strong>Unit</strong> is required.' + '<br/>';
                }
                if ( tanggal == '') {
                    msg += 'Field <strong>Tanggal</strong> is required.' + '<br/>';
                }
                if ( coaBiaya == '') {
                    msg += 'Field <strong>COA Biaya</strong> is required.' + '<br/>';
                }
                if ( coaGiro == '') {
                    msg += 'Field <strong>COA Giro</strong> is required.' + '<br/>';
                }
                if ( divisi == '') {
                    msg += 'Field <strong>Divisi</strong> is required.' + '<br/>';
                }
                if ( sisaBudget == '') {
                    msg += 'Field <strong>Sisa Budget</strong> is required.' + '<br/>';
                }
                if ( jumlah == '') {
                    msg += 'Field <strong>Jumlah</strong> is required.' + '<br/>';
                }
                if ( keterangan != '') {
                    msg += 'Field <strong>Keterangan</strong> is required.' + '<br/>';
                }
                document.getElementById('errorMessage').innerHTML = msg;
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

        function resetField() {
            window.location.reload()
        }
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
            Pengajuan Biaya
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-user-plus"></i> Input Pengajuan Biaya</h3>
                    </div>
                    <s:form id="addPengajuanBiayaForm" enctype="multipart/form-data" method="post" namespace="/pengajuanBiaya"
                            action="saveAddPengajuan_pengajuanBiaya.action" theme="simple">
                        <div class="box-body">
                            <div class="alert alert-danger alert-dismissible" id="warning_pembayaran" style="display: none">
                                <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                                <span id="errorText"></span>
                            </div>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-money"></i> Form </h3>
                            </div>
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-md-8">
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Unit</label>
                                            <div class="col-md-8"  style="margin-top: 7px">
                                                <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                                <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branch_id_view" name="pengajuanBiaya.branchId" required="true" disabled="true"
                                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="" />
                                                <s:hidden name="pengajuanBiaya.branchId" id="branch_id" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Divisi</label>
                                            <div class="col-md-8"  style="margin-top: 7px">
                                                <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                                                <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="divisi_id_view" disabled="true" name="pengajuanBiaya.divisiId" required="false" readonly="true"
                                                          listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                                                <s:hidden  id="divisi_id" name="pengajuanBiaya.divisiId" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Transaksi</label>
                                            <div class="col-md-8" style="margin-top: 7px">
                                                <s:select list="#{'PB':'Pengajuan Biaya'}" onchange="initCoa(this.value)"
                                                          id="transaksi_view" name="pengajuanBiaya.transaksi"
                                                          headerKey="" headerValue="[Select One]" cssClass="form-control" />
                                                <s:hidden id="transaksi" />
                                                <s:hidden name="pengajuanBiaya.tipeTransaksi" id="tipe_transaksi" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Tanggal</label>
                                            <div class="col-md-8">
                                                <div class="input-group date" style="margin-top: 7px" id="st_tgl">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <s:textfield id="tanggal" name="pengajuanBiaya.stTanggal"
                                                                 cssClass="form-control datemask" onchange="$('#st_tgl').css('border','')"/>
                                                    <script>
                                                        $("#tanggal").datepicker({
                                                            setDate: new Date(),
                                                            autoclose: true,
                                                            changeMonth: true,
                                                            changeYear:true,
                                                            dateFormat:'yy-mm-dd'
                                                        });
                                                        $("#tanggal").datepicker("setDate", new Date());
                                                    </script>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">COA Biaya </label>
                                            <div class="col-md-8">
                                                <select class="form-control" id="coa_biaya" onchange="isiKeteterangan(),getSisaBudget(this.value)" style="margin-top: 7px" name="pengajuanBiaya.coaAjuan">
                                                    <option value="" ></option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">COA Giro </label>
                                            <div class="col-md-8">
                                                <select class="form-control" id="coa_giro" onchange="isiKeteterangan()" style="margin-top: 7px" name="pengajuanBiaya.coaTarget">
                                                    <option value="" ></option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Jumlah</label>
                                            <div class="col-md-8">
                                                <s:textfield id="bayar" name="pengajuanBiaya.stTotalBiaya"  onkeyup="formatRupiah2(this)"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Budget Biaya</label>
                                            <div class="col-md-8">
                                                <s:textfield id="sisa_budget" name="pengajuanBiaya.stBudgetSaatIni" readonly="true"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Budget Terpakai</label>
                                            <div class="col-md-8">
                                                <s:textfield id="budget_terpakai" name="pengajuanBiaya.stBudgetTerpakai" readonly="true"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Keterangan</label>
                                            <div class="col-md-8">
                                                <s:textarea id="keterangan" rows="3" cssStyle="margin-top: 7px" onkeypress="$(this).css('border','')"
                                                            name="pengajuanBiaya.keterangan" cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="box-header with-border"></div>
                            <div class="box-body col-md-offset-4">
                                    <%--<div class="row">--%>
                                    <%--<div class="col-md-6">--%>
                                <div class="form-group" style="display: inline;">
                                        <%--<div class="col-sm-10 col-md-offset-4" style="margin-top: 7px">--%>
                                            <sj:submit targets="crud" type="button" cssClass="btn btn-success" formIds="addPengajuanBiayaForm" id="save" name="save"
                                                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                <i class="fa fa-save"></i>
                                                Save
                                            </sj:submit>
                                    <button type="button" class="btn btn-danger" onclick="resetField()">
                                        <i class="fa fa-refresh"></i> Reset
                                    </button>
                                    <a type="button" class="btn btn-warning" href="initForm_pengajuanBiaya.action">
                                        <i class="fa fa-arrow-left"></i> Back
                                    </a>
                                </div>
                                <div>
                                    <sj:dialog id="waiting_dialog" openTopics="showDialog"
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
<script type='text/javascript'>
    function isiKeteterangan() {
        var transaksi = $('#transaksi_view option:selected').text();
        var transaksiId = $('#transaksi').val();
        var divisi = $('#divisi_id_view option:selected').text();
        var coaBiaya = $('#coa_biaya option:selected').text();
        var coaGiro = $('#coa_giro option:selected').text();
        var unit = $('#branch_id_view option:selected').text();
        var keterangan ="";
        if (transaksiId == "PB"){
            keterangan= transaksi+" "+coaBiaya+" "+unit+" divisi "+divisi+" pada giro "+coaGiro;
        }
        $('#keterangan').val(keterangan);
    }

    function getCoaGiro(value) {
        var option = '<option value=""></option>';
        var transaksi = "";
        var posisi ="";
        switch(value) {
            case "PB":
                transaksi = "59";
                posisi="K";
                break;
        }
        KodeRekeningAction.getKodeRekeningLawanByTransId(transaksi,posisi,function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option += '<option value="'+item.kodeRekening+'">'+item.namaKodeRekening+'</option>';
                });
                $('#coa_giro').html(option);
            }else{
                $('#coa_giro').html(option);
            }
        });
    }

    function getCoaBiaya(value) {
        var option = '<option value=""></option>';
        var transaksi = "";
        var posisi ="";
        switch(value) {
            case "PB":
                transaksi = "59";
                posisi="D";
                break;
        }
        KodeRekeningAction.getKodeRekeningLawanByTransId(transaksi,posisi,function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option += '<option value="'+item.kodeRekening+'">'+item.namaKodeRekening+'</option>';
                });
                $('#coa_biaya').html(option);
            }else{
                $('#coa_biaya').html(option);
            }
        });
    }

    function getSisaBudget(value) {
        var divisi_Id=$('#divisi_id').val();
        var branch_id=$('#branch_id').val();
        var tanggal=$('#st_tgl').val();
        BudgetingAction.getBudgetSaatIni(branch_id,divisi_Id,tanggal,value,function (res) {
            $('#sisa_budget').val(res);
            $('#budget_terpakai').val(res);
        });
    }

    function formatRupiahAngka(angka) {
        var number_string = angka.replace(/[^,\d]/g, '').toString(),
            split = number_string.split(','),
            sisa = split[0].length % 3,
            rupiah = split[0].substr(0, sisa),
            ribuan = split[0].substr(sisa).match(/\d{3}/gi);

        if (ribuan) {
            separator = sisa ? '.' : '';
            rupiah += separator + ribuan.join('.');
        }

        rupiah = split[1] != undefined ? rupiah + ',' + split[1] : rupiah;
        return rupiah;
    }

    function initCoa(value){
        getCoaGiro(value);
        getCoaBiaya(value);
        switch(value) {
            case "PB":
                $('#transaksi').val("PB");
                $('#tipe_transaksi').val("59");
                break;
        }
    }
    $(document).ready(function(){

    })
</script>
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>

