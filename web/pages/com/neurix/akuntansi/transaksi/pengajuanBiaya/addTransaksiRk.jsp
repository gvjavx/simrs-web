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
    <script type='text/javascript' src='<s:url value="/dwr/interface/MasterAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasirRawatJalanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
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
            var tanggal  = document.getElementById("tanggal").value;
            var coaRk  = document.getElementById("coa_rk").value;
            var coaGiro  = document.getElementById("coa_giro").value;
            var jumlah  = document.getElementById("bayar").value;
            var keterangan  = document.getElementById("keterangan").value;
            if ( unit != ''&& tanggal != ''&& coaRk != '' && coaGiro != ''&&jumlah!=""&&keterangan!="") {
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
                if ( coaRk == '') {
                    msg += 'Field <strong>COA RK</strong> is required.' + '<br/>';
                }
                if ( coaGiro == '') {
                    msg += 'Field <strong>COA Giro</strong> is required.' + '<br/>';
                }
                if ( jumlah == '') {
                    msg += 'Field <strong>Jumlah</strong> is required.' + '<br/>';
                }
                if ( keterangan != '') {
                    msg += 'Field <strong>Keterangan</strong> is required.' + '<br/>';
                }
                console.log(msg);
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
            Transaksi RK
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-user-plus"></i> Input Transaksi RK</h3>
                    </div>
                    <s:form id="addPengajuanBiayaForm" enctype="multipart/form-data" method="post" namespace="/pengajuanBiaya"
                            action="saveAdd_pengajuanBiaya.action" theme="simple">
                        <s:hidden name="pengajuanBiaya.tipePembayaran" value="KK" />
                        <div class="box-body"pengajuanBiaya>
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
                                            <label class="col-md-4" style="margin-top: 7px">Kantor Pusat</label>
                                            <div class="col-md-8">
                                                <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                                <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branch_id_kanpus_view" name="pengajuanBiaya.branchIdKanpus" required="true" disabled="true"
                                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="" />

                                                <s:hidden name="pengajuanBiaya.branchIdKanpus" id="branch_id_kanpus" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Unit</label>
                                            <div class="col-md-8"  style="margin-top: 7px">
                                                <s:action id="comboBranchSelainKp" namespace="/admin/user" name="initComboBranchSelainKp_user"/>
                                                <s:select cssClass="form-control" list="#comboBranchSelainKp.listOfComboBranches" id="branch_id"  onchange="isiKeteterangan(),initCoa()" name="pengajuanBiaya.branchId" required="true"
                                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Transaksi</label>
                                            <div class="col-md-8" style="margin-top: 7px">
                                                <s:select list="#{'PDU':'Swift Kas Unit ke Pusat','SMK':'Setoran Modal Kerja ke Unit'}" onchange="initCoa()"
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
                                            <label class="col-md-4" style="margin-top: 7px">COA RK </label>
                                            <div class="col-md-8">
                                                <select class="form-control" id="coa_rk" onchange="isiKeteterangan()" style="margin-top: 7px" name="pengajuanBiaya.coaAjuan">
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
        var coaGiro = $('#coa_giro option:selected').text();
        var coa_rk = $('#coa_rk option:selected').text();
        var unit = $('#branch_id option:selected').text();
        var keterangan ="";
        if (transaksiId == "SMK"){
            keterangan= transaksi +" "+unit+" pada giro "+coaGiro;
        } else if (transaksiId == "PDU"){
            keterangan= transaksi +" "+unit+" ke Kantor Pusat pada giro "+coaGiro;
        }
        $('#keterangan').val(keterangan);
    }

    function getCoaGiro(value) {
        var option = '<option value=""></option>';
        var transaksi = "";
        var posisi ="";
        switch(value) {
            case "SMK":
                transaksi = "69";
                posisi="K";
                break;
            case "PDU":
                transaksi = "68";
                posisi="D";
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

    function getCoaRk(transaksiId,unit) {
        var option = '<option value=""></option>';
        var transaksi = "";
        var posisi ="";
        switch(transaksiId) {
            case "SMK":
                transaksi = "69";
                posisi="D";
                break;
            case "PDU":
                transaksi = "68";
                posisi="K";
                break;
        }
        KodeRekeningAction.getKodeRekeningLawanByTransIdRk(transaksi,posisi,unit,function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option = '<option value="'+item.kodeRekening+'">'+item.tampilanCoa+'</option>';
                });
                $('#coa_rk').html(option);
            }else{
                $('#coa_rk').html(option);
            }
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

    function initCoa(){
        var unit = $('#branch_id').val();
        var transaksi = $('#transaksi_view').val();
        if (unit!=''&&transaksi!=""){
            getCoaGiro(transaksi);
            getCoaRk(transaksi,unit);
            switch(transaksi) {
                case "SMK":
                    $('#transaksi').val("SMK");
                    $('#tipe_transaksi').val("69");
                    break;
                case "PDU":
                    $('#transaksi').val("PDU");
                    $('#tipe_transaksi').val("68");
                    break;
            }
        }
    }

    $(document).ready(function(){

    })
</script>
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>

