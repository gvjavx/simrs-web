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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PengajuanBiayaAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/JurnalAction.js"/>'></script>
    <script type='text/javascript'>
        function callSearch2() {
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
        }
        function link(){
            window.location.href="<s:url action='initFormPengajuan_pengajuanBiaya'/>";
        }

        $.subscribe('beforeProcessSave', function (event, data) {
            var unit  = document.getElementById("branch_id").value;
            var divisi  = document.getElementById("divisi_id").value;
            var tanggal  = document.getElementById("tanggalId").value;
            var keterangan  = document.getElementById("keteranganId").value;
            var jumlahRow = document.getElementById('pengajuanBiayaTabel').rows.length;
            jumlahRow = jumlahRow-1;

            if ( unit != ''&&divisi!=""&&jumlahRow>0&&tanggal!=""&&keterangan!="") {
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
                if ( divisi == '') {
                    msg += 'Field <strong>Divisi</strong> is required.' + '<br/>';
                }
                if ( keterangan == '') {
                    msg += 'Field <strong>Keterangan</strong> is required.' + '<br/>';
                }
                if ( tanggal == '') {
                    msg += 'Field <strong>Tanggal</strong> is required.' + '<br/>';
                }
                if ( jumlahRow < 1) {
                    msg += 'Tabel <strong>Pengajuan Masih Kosong</strong> is required.' + '<br/>';
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
                                            <label class="col-md-4" style="margin-top: 7px">Tanggal</label>
                                            <div class="col-md-8">
                                                <div class="input-group date" style="margin-top: 7px" id="st_tgl">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <s:textfield id="tanggalId" name="pengajuanBiaya.stTanggal" cssClass="form-control datemask" onchange="$('#st_tgl').css('border','')"/>
                                                    <script>
                                                        $("#tanggalId").datepicker({
                                                            setDate: new Date(),
                                                            autoclose: true,
                                                            changeMonth: true,
                                                            changeYear:true,
                                                            dateFormat:'yy-mm-dd'
                                                        });
                                                        $("#tanggalId").datepicker("setDate", new Date());
                                                    </script>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Total </label>
                                            <div class="col-md-8">
                                                <s:textfield id="total" readonly="true" cssStyle="margin-top: 7px" onkeypress="$(this).css('border','')" name="pengajuanBiaya.stTotalBiaya" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Keterangan</label>
                                            <div class="col-md-8">
                                                <s:textarea id="keteranganId" rows="3" cssStyle="margin-top: 7px" onkeypress="$(this).css('border','')" name="pengajuanBiaya.keterangan" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-offset-4 col-md-8">
                                                <button type="button" class="btn btn-primary" style="margin-top: 40px" id="btnTambah">
                                                    <i class="fa fa-plus"></i> Tambah Data
                                                </button>
                                            </div>
                                            <script>
                                                $('#btnTambah').click(function () {
                                                    $('#transaksi_view').val("");
                                                    $('#no_budget').val("");
                                                    $('#bayar').val("");
                                                    $('#sisa_budget').val("");
                                                    $('#sisa_budget_sd_bulan_ini').val("");
                                                    $('#budget_terpakai').val("");
                                                    $('#budget_terpakai_sd_bulan_ini').val("");
                                                    $('#keperluanText').val("");
                                                    $('#keperluanCombo').val("");
                                                    $('#sisa_budget_saat_ini').val("");
                                                    $('#sisa_budget_saat_ini_sd').val("");
                                                    $('#budget_terpakai_transaksi_ini').val("");
                                                    $('#keterangan').val("");

                                                    PengajuanBiayaAction.getTipeBudgetInSession(function (data) {
                                                        if (data!=""){
                                                            if (data=="R"){
                                                                var option = '<option value="R">Rutin</option>';
                                                                initNoBudget(data);
                                                                $('#transaksi_view').html(option);
                                                            }else{
                                                                if (data=="I"){
                                                                    var option = '<option value="I">Investasi</option>';
                                                                    $('#transaksi_view').html(option);
                                                                    initNoBudget(data);
                                                                }
                                                            }
                                                        }else{
                                                            var option = '<option value=""></option>';
                                                            option += '<option value="R">Rutin</option>';
                                                            option += '<option value="I">Investasi</option>';
                                                            $('#transaksi_view').html(option);
                                                        }
                                                    });
                                                    $("#modal-tambah-data").modal('show');
                                                })
                                            </script>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="box-header with-border">
                                <h3 class="box-title"><i class="fa fa-bars"></i> Daftar Tabel Pengajuan </h3>
                            </div>
                            <center>
                                <table id="showdata" width="100%">
                                    <tr>
                                        <td align="center">
                                            <table style="width: 100%;margin-top: 40px" id="pengajuanBiayaTabel" class="pengajuanBiayaTabel table table-bordered">
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </center>
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
<div class="modal fade" id="modal-tambah-data">
    <div class="modal-dialog modal-flat modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambah Pengajuan Biaya</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Tanggal</label>
                                <div class="col-md-6">
                                    <s:textfield id="tanggal" onkeypress="$(this).css('border','')" cssStyle="margin-top: 7px"
                                                 cssClass="form-control" />
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
                            <div class="form-group">
                                <label class="col-md-4"  style="margin-top: 7px">Tipe Budget</label>
                                <div class="col-md-6">
                                    <s:select list="#{'R':'Rutin','I':'Investasi'}" onchange="initNoBudget(this.value)" cssStyle="margin-top: 7px"
                                              id="transaksi_view" headerKey="" headerValue="[Select One]" cssClass="form-control" />
                                    <s:hidden id="transaksi" />
                                    <s:hidden id="tipe_transaksi" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">No. Budget</label>
                                <div class="col-md-6">
                                    <select class="form-control" id="no_budget" onchange="isiKeteterangan(),getSisaBudget(this.value),getBudgetTerpakaiTransaksi(this.value)" style="margin-top: 7px">
                                        <option value="" ></option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4"  style="margin-top: 7px">Keperluan</label>
                                <div class="col-md-6">
                                    <div class="keperluanText">
                                        <s:textfield id="keperluanText" name="pengajuanBiaya.keperluan"
                                                     cssClass="form-control" cssStyle="margin-top: 7px" />
                                    </div>
                                    <div class="keperluanCombo" style="display: none">
                                        <select class="form-control" id="keperluanCombo" onchange="getSisaBudgetInvestasi(this.value),getInvestasiTerpakaiTransaksi(this.value),getTerminPembayaran(this.value)" style="margin-top: 7px">
                                            <option value="" ></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-2" style="margin-top: 7px">
                                    <a href="javascript:;" id="btnViewStok">
                                        <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" name="icon_view">
                                    </a>
                                </div>
                            </div>
                            <div class="form-group" id="view_termin" style="display: none">
                                <label class="col-md-4"  style="margin-top: 7px">Pembayaran </label>
                                <div class="col-md-6">
                                    <select class="form-control" id="termin" style="margin-top: 7px">
                                        <option value="" ></option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Jumlah ( RP )</label>
                                <div class="col-md-6">
                                    <s:textfield id="bayar" onkeyup="formatRupiah2(this)" cssClass="form-control" cssStyle="margin-top: 7px;text-align: right" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Budgeting Bulan Ini ( RP )</label>
                                <div class="col-md-6">
                                    <s:textfield id="sisa_budget" readonly="true" cssClass="form-control" cssStyle="margin-top: 7px;text-align: right" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Budgeting s/d Bulan Ini ( RP )</label>
                                <div class="col-md-6">
                                    <s:textfield id="sisa_budget_sd_bulan_ini" readonly="true" cssClass="form-control" cssStyle="margin-top: 7px;text-align: right" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Budget Terpakai Bulan Ini ( RP )</label>
                                <div class="col-md-6">
                                    <s:textfield id="budget_terpakai" readonly="true" cssClass="form-control" cssStyle="margin-top: 7px;text-align: right" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Budget Terpakai s/d Bulan Ini ( RP )</label>
                                <div class="col-md-6">
                                    <s:textfield id="budget_terpakai_sd_bulan_ini" readonly="true" cssClass="form-control" cssStyle="margin-top: 7px;text-align: right" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Sisa Budget Bulan Ini ( RP )</label>
                                <div class="col-md-6">
                                    <s:textfield id="sisa_budget_saat_ini" readonly="true" cssClass="form-control" cssStyle="margin-top: 7px;text-align: right" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Sisa Budget s/d Bulan Ini ( RP )</label>
                                <div class="col-md-6">
                                    <s:textfield id="sisa_budget_saat_ini_sd" readonly="true" cssClass="form-control" cssStyle="margin-top: 7px;text-align: right" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Transaksi Yang Masih On Proses ( RP )</label>
                                <div class="col-md-6">
                                    <s:textfield id="budget_terpakai_transaksi_ini" readonly="true" cssClass="form-control" cssStyle="margin-top: 7px;text-align: right" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Keterangan</label>
                                <div class="col-md-6">
                                    <s:textarea id="keterangan" rows="3" cssStyle="margin-top: 7px" onkeypress="$(this).css('border','')" cssClass="form-control"/>
                                </div>
                            </div>
                            <br>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" id="btnTambahPengajuan" data-dismiss="modal"><i class="fa fa-arrow-right"></i> Tambahkan</button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-daftar-stok">
    <div class="modal-dialog modal-flat modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Daftar Stok Divisi</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <br>
                    <br>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-md-2" style="margin-top: 7px">Nama : </label>
                            <div class="col-md-4">
                                <s:textfield id="nama_obat"  cssClass="form-control" />
                            </div>
                            <div class="col-md-3">
                                <button type="button" class="btn btn-primary" id="btnSearchStok">
                                    <i class="fa fa-search"></i> Cari data
                                </button>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <table style="width: 100%;" class="tabelDaftarStok table table-bordered">
                            </table>
                            <br>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
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

    function getSisaBudget(value) {
        var transaksi=$('#transaksi_view').val();
        var divisi_Id=$('#divisi_id').val();
        var branch_id=$('#branch_id').val();
        var tanggal=$('#tanggal').val();
        if (transaksi=="R"){
            BudgetingAction.getBudgetSaatIni(branch_id,divisi_Id,tanggal,value,function (data) {
                JurnalAction.getBudgetTerpakai(branch_id,divisi_Id,tanggal,value,data.stBudgetSaatIni,data.stBudgetSdSaatIni,function (item) {
                    $('#budget_terpakai').val(item.stBudgetTerpakai);
                    $('#budget_terpakai_sd_bulan_ini').val(item.stBudgetTerpakaiSdBulanIni);
                    $('#sisa_budget_saat_ini').val(item.stSisaBudget);
                    $('#sisa_budget_saat_ini_sd').val(item.stSisaBudgetSdBulanIni);
                });
                $('#sisa_budget').val(data.stBudgetSaatIni);
                $('#sisa_budget_sd_bulan_ini').val(data.stBudgetSdSaatIni);
            });
        }else if (transaksi=="I"){
            var option = '<option value=""></option>';
            BudgetingAction.getInvestasiByNoBudgeting(value,function (res) {
                if(res.length > 0){
                    $.each(res, function (i, item) {
                        option += '<option value="'+item.idPengadaan+'">'+item.namPengadaan+'</option>';
                    });
                    $('#keperluanCombo').html(option);
                }else{
                    $('#keperluanCombo').html(option);
                }
            });
        }
    }

    function getBudgetTerpakaiTransaksi(value) {
        var divisi_Id=$('#divisi_id').val();
        var branch_id=$('#branch_id').val();
        var tanggal=$('#tanggal').val();
        var tipeBudget = $('#transaksi_view').val();
        if (tipeBudget=="R"){
            PengajuanBiayaAction.getBudgetTerpakaiTransaksiSession(branch_id,divisi_Id,tanggal,value,function (data) {
                $('#budget_terpakai_transaksi_ini').val(data.stBudgetTerpakaiTransaksi);
            });
        }
    }

    function getInvestasiTerpakaiTransaksi(value) {
        var divisi_Id=$('#divisi_id').val();
        var branch_id=$('#branch_id').val();
        var tanggal=$('#tanggal').val();

        PengajuanBiayaAction.getInvestasiTerpakaiTransaksiSession(branch_id,divisi_Id,tanggal,value,function (data) {
            $('#budget_terpakai_transaksi_ini').val(data.stBudgetTerpakaiTransaksi);
        });
    }

    function getSisaBudgetInvestasi(value) {
        var transaksi=$('#transaksi_view').val();
        var divisi_Id=$('#divisi_id').val();
        var branch_id=$('#branch_id').val();
        var tanggal=$('#tanggal').val();
        if (transaksi=="I"){
            BudgetingAction.getBudgetInvestasiSaatIni(branch_id,divisi_Id,tanggal,value,function (data) {
                JurnalAction.getBudgetInvestasiTerpakai(branch_id,divisi_Id,tanggal,value,data.stBudgetSaatIni,data.stBudgetSdSaatIni,function (item) {
                    $('#budget_terpakai').val(item.stBudgetTerpakai);
                    $('#budget_terpakai_sd_bulan_ini').val(item.stBudgetTerpakaiSdBulanIni);
                    $('#sisa_budget_saat_ini').val(item.stSisaBudget);
                    $('#sisa_budget_saat_ini_sd').val(item.stSisaBudgetSdBulanIni);
                });
                $('#sisa_budget').val(data.stBudgetSaatIni);
                $('#sisa_budget_sd_bulan_ini').val(data.stBudgetSdSaatIni);
            });
        }
    }

    function initNoBudget(value){
        if (value=="R"){
            $('#view_termin').hide();
            var option = '<option value=""></option>';
            var divisi_Id=$('#divisi_id').val();
            var branch_id=$('#branch_id').val();
            var tanggal=$('#tanggal').val();
            BudgetingAction.getNoBudgetByDivisi(branch_id,divisi_Id,tanggal,function (res) {
                if(res.length > 0){
                    $.each(res, function (i, item) {
                        option += '<option value="'+item.noBudgeting+'">'+item.noBudgeting+'</option>';
                    });
                    $('#no_budget').html(option);
                }else{
                    $('#no_budget').html(option);
                }
            });
            $('.keperluanText').show();
            $('.keperluanCombo').hide();
        } else if (value=="I"){
            $('#view_termin').show();
            var option = '<option value=""></option>';
            var divisi_Id=$('#divisi_id').val();
            var branch_id=$('#branch_id').val();
            var tanggal=$('#tanggal').val();
            $('.keperluanText').hide();
            $('.keperluanCombo').show();
            BudgetingAction.getInvestasiByDivisi(branch_id,divisi_Id,tanggal,function (res) {
                if(res.length > 0){
                    $.each(res, function (i, item) {
                        option += '<option value="'+item.noBudgeting+'">'+item.noBudgeting+'</option>';
                    });
                    $('#no_budget').html(option);
                }else{
                    $('#no_budget').html(option);
                }
            });
        }
    }

    window.loadPengajuan =  function(){
        $('.pengajuanBiayaTabel').find('tbody').remove();
        $('.pengajuanBiayaTabel').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PengajuanBiayaAction.searchSessionPengajuan(function(listdata){
            tmp_table = "<thead style='font-size: 10px;' ><tr class='active'>"+
                "<th style='text-align: center; background-color:  #90ee90'>No</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Delete</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Tipe Budget</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>No. Budget</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Jumlah ( RP )</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Budgeting s/d Bln Ini (RP)</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Budget Terpakai s/d Bln Ini (RP)</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Sisa Budget s/d Bln Ini (RP)</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Keperluan</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Keterangan</th>"+
                "</tr></thead>";
            var i = i ;
            var total =0;
            $.each(listdata, function (i, item) {
                total = total+item.jumlah;
                var transaksi ="";
                switch (item.transaksi) {
                    case "R":
                        transaksi="Rutin";
                        break;
                    case "I":
                        transaksi="Investasi";
                        break;
                }
                tmp_table += '<tr style="font-size: 10px;" ">' +
                    '<td align="center">' + (i + 1) + '</td>' +
                    '<td align="center">' +
                    "<a href='javascript:;' class ='item-delete' data ='"+item.keperluan+"' >" +
                    "<img border='0' src='<s:url value='/pages/images/icon_trash.ico'/>' name='icon_edit'>"+
                    '</a>' +
                    '</td>' +
                    '<td align="center">' + transaksi + '</td>' +
                    '<td align="center">' + item.noBudgeting+ '</td>' +
                    '<td align="center">' + item.stJumlah+ '</td>' +
                    '<td align="center">' + item.stBudgetBiayaSdBulanIni+ '</td>' +
                    '<td align="center">' + item.stBudgetTerpakaiSdBulanIni+ '</td>' +
                    '<td align="center">' + item.stSisaBudgetSdBulanIni+ '</td>' +
                    '<td align="center">' + item.keperluanName+ '</td>' +
                    '<td align="center">' + item.keterangan+ '</td>' +
                    "</tr>";
            });
            $('#total').val(formatRupiahAngka(total.toString()));
            $('.pengajuanBiayaTabel').append(tmp_table);
        });
    };

    $(document).ready(function(){
        $('#btnTambahPengajuan').click(function () {
            var branchId=$('#branch_id').val();
            var divisiId=$('#divisi_id').val();
            var stTanggal=$('#tanggal').val();
            var transaksi=$('#transaksi_view').val();
            var noBudgeting=$('#no_budget').val();
            var stJumlah=$('#bayar').val();
            var stBudgetBiaya=$('#sisa_budget').val();
            var stBudgetBiayaSdBulanIni=$('#sisa_budget_sd_bulan_ini').val();
            var stBudgetTerpakai=$('#budget_terpakai').val();
            var stBudgetTerpakaiSdBulanIni=$('#budget_terpakai_sd_bulan_ini').val();
            var stSisaBudget=$('#sisa_budget_saat_ini').val();
            var stSisaBudgetSdBulanIni=$('#sisa_budget_saat_ini_sd').val();
            var stBudgetTerpakaiAplikasi=$('#budget_terpakai_transaksi_ini').val();
            var keperluan = "";
            var keperluanName = "";
            var termin = "";
            if (transaksi=="R"){
                keperluan=$('#keperluanText').val();
                keperluanName=$('#keperluanText').val();
            } else if (transaksi=="I"){
                keperluan=$('#keperluanCombo option:selected').val();
                keperluanName=$('#keperluanCombo option:selected').text();
                termin = $('#termin').val();
            }
            var keterangan=$('#keterangan').val();

            if (branchId!=""&&divisiId!=""&&stTanggal!=""&&transaksi!=""&&noBudgeting!=""&&stJumlah!=""&&stBudgetBiaya!=""&&stBudgetTerpakai!=""&&keterangan!=""&&keperluan!=""){
                PengajuanBiayaAction.saveSessionPengajuan(branchId,divisiId,stTanggal,transaksi,noBudgeting,stJumlah,stBudgetBiaya,stBudgetTerpakai,keperluan,keterangan,keperluanName,stBudgetBiayaSdBulanIni,stBudgetTerpakaiSdBulanIni,stSisaBudget,stSisaBudgetSdBulanIni,stBudgetTerpakaiAplikasi,termin,function(result){
                    if (result==""){
                        loadPengajuan();
                    } else{
                        alert(result);
                    }
                })
            }else{
                var msg="";
                if (branchId==""){
                    msg +="Unit masih kosong \n";
                }
                if (divisiId==""){
                    msg +="Divisi masih kosong \n";
                }
                if (stTanggal==""){
                    msg +="Tanggal masih kosong \n";
                }
                if (transaksi==""){
                    msg +="Transaksi masih kosong \n";
                }
                if (noBudgeting==""){
                    msg +="No. Budgetting masih kosong \n";
                }
                if (stJumlah==""){
                    msg +="Jumlah masih kosong \n";
                }
                if (stBudgetBiaya==""){
                    msg +="Budgeting masih kosong \n";
                }
                if (stBudgetTerpakai==""){
                    msg +="Budget terpakai masih kosong \n";
                }
                if (keterangan==""){
                    msg +="Keterangan masih kosong \n";
                }
                if (keperluan==""){
                    msg +="Keperluan masih kosong \n";
                }
                alert(msg);
            }
        });

        $('.pengajuanBiayaTabel').on('click', '.item-delete', function () {
            var id = $(this).attr('data');
            if (id!=''){
                PengajuanBiayaAction.deleteSessionPengajuan(id,function (result) {
                    alert("data berhasil dihapus");
                    loadPengajuan();
                });
            } else{
                var msg="";
                if (id==""){
                    msg+="data tidak ditemukan \n";
                }
                alert(msg);
            }
        });
        $('#total').val(0);

        window.loadStok =  function(namaObat){
            var tanggal = $('#tanggal').val();
            var branchId = $('#branch_id_view').val();
            var divisiId = $('#divisi_id_view').val();
            $('.tabelDaftarStok').find('tbody').remove();
            $('.tabelDaftarStok').find('thead').remove();
            dwr.engine.setAsync(false);
            var tmp_table = "";
            if (tanggal!=""&&branchId!=""&&divisiId!=""&&namaObat!=""){
                PengajuanBiayaAction.getStockPerDivisi(branchId,divisiId,tanggal,namaObat,function (result) {
                    tmp_table = "<thead style='font-size: 12px;' ><tr class='active'>"+
                        "<th style='text-align: center; background-color:  #90ee90'>No</th>"+
                        "<th style='text-align: center; background-color:  #90ee90'>Nama Barang</th>"+
                        "<th style='text-align: center; background-color:  #90ee90'>Qty</th>"+
                        "</tr></thead>";
                    var i = i ;
                    $.each(result, function (i, item) {
                        var saldo = item.subTotalSaldo.replace(/[,]/g,"");
                        tmp_table += '<tr style="font-size: 11px;" ">' +
                            '<td align="center">' + (i + 1) + '</td>' +
                            '<td align="center">' + item.namaBarang+ '</td>' +
                            '<td align="center">' + item.qty+ '</td>' +
                            "</tr>";
                    });
                    $('.tabelDaftarStok').append(tmp_table);
                    // $('.tabelDaftarStok').dataTable();
                })
            }else{
                var msg="";
                if (namaObat==""){
                    msg+="Input kan nama obat minimal 1 huruf";
                }
                alert(msg);
            }
        };

        $('#btnViewStok').click(function () {
            $("#modal-daftar-stok").modal('show');
        });
        $('#btnSearchStok').click(function () {
            var namaObat = $('#nama_obat').val();
            loadStok(namaObat);
        });
    });
    function formatRupiahAngka(angka) {
        var number_string = angka.replace(/[^,\d]/g, '').toString(),
            split = number_string.split(','),
            sisa = split[0].length % 3,
            rupiah = split[0].substr(0, sisa),
            ribuan = split[0].substr(sisa).match(/\d{3}/gi);

        if (ribuan) {
            separator = sisa ? ',' : '';
            rupiah += separator + ribuan.join(',');
        }

        rupiah = split[1] != undefined ? rupiah + ',' + split[1] : rupiah;
        return rupiah;
    }
    function getTerminPembayaran(value){
        var option = '<option value="Lunas">Lunas</option>';
        BudgetingAction.getTerminPembayaran(value,function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option += '<option value="'+item.pembayaran+'">'+item.pembayaran+'</option>';
                });
                $('#termin').html(option);
            }else{
                $('#termin').html(option);
            }
        });
    }
</script>
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>

