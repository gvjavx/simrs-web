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
            var jumlahRow = document.getElementById('pengajuanBiayaTabel').rows.length;
            jumlahRow = jumlahRow-1;

            if ( unit != ''&&divisi!=""&&jumlahRow>0) {
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
                                            <label class="col-md-4" style="margin-top: 7px">Transaksi</label>
                                            <div class="col-md-8" style="margin-top: 7px">
                                                <s:select list="#{'R':'Rutin','I':'Investasi'}" onchange="initNoBudget(this.value)"
                                                          id="transaksi_view" name="pengajuanBiaya.transaksi"
                                                          headerKey="" headerValue="[Select One]" cssClass="form-control" />
                                                <s:hidden id="transaksi" />
                                                <s:hidden name="pengajuanBiaya.tipeTransaksi" id="tipe_transaksi" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">No. Budget </label>
                                            <div class="col-md-8">
                                                <select class="form-control" id="no_budget" onchange="isiKeteterangan(),getSisaBudget(this.value)" style="margin-top: 7px" name="pengajuanBiaya.noBudget">
                                                    <option value="" ></option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4" style="margin-top: 7px">Keperluan</label>
                                            <div class="col-md-8 keperluanText">
                                                <s:textfield id="keperluanText" name="pengajuanBiaya.keperluan"
                                                             cssClass="form-control" cssStyle="margin-top: 7px" />
                                            </div>
                                            <div class="col-md-8 keperluanCombo" style="display: none">
                                                <select class="form-control" id="keperluanCombo" onchange="getSisaBudgetInvestasi(this.value)" style="margin-top: 7px" name="pengajuanBiaya.keperluan">
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
                                            <label class="col-md-4" style="margin-top: 7px">Budgeting</label>
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
                                            <label class="col-md-4" style="margin-top: 7px">Sisa Budget</label>
                                            <div class="col-md-8">
                                                <s:textfield id="sisa_budget_saat_ini" name="pengajuanBiaya.stSisaBudget" readonly="true"
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
                                        <div class="form-group">
                                            <div class="col-md-offset-4 col-md-8">
                                                <button type="button" class="btn btn-primary" style="margin-top: 40px" id="btnTambahPengajuan">
                                                    <i class="fa fa-plus"></i> Tambahkan
                                                </button>
                                            </div>
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
            BudgetingAction.getBudgetSaatIni(branch_id,divisi_Id,tanggal,value,function (res) {
                $('#sisa_budget').val(res);
                $('#budget_terpakai').val(0);
                $('#sisa_budget_saat_ini').val(0);
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

    function getSisaBudgetInvestasi(value) {
        var transaksi=$('#transaksi_view').val();
        var divisi_Id=$('#divisi_id').val();
        var branch_id=$('#branch_id').val();
        var tanggal=$('#tanggal').val();
        if (transaksi=="I"){
            BudgetingAction.getBudgetInvestasiSaatIni(branch_id,divisi_Id,tanggal,value,function (res) {
                $('#sisa_budget').val(res);
                $('#budget_terpakai').val(0);
                $('#sisa_budget_saat_ini').val(0);
            });
        }
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

    function initNoBudget(value){
        if (value=="R"){
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
                "<th style='text-align: center; background-color:  #90ee90'>Tanggal</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Transaksi</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>No. Budget</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Jumlah</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Budget RKAP</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Budget Terpakai</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Keperluan</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Keterangan</th>"+
                "</tr></thead>";
            var i = i ;
            $.each(listdata, function (i, item) {
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
                    '<td align="center">' + item.stTanggal+ '</td>' +
                    '<td align="center">' + transaksi + '</td>' +
                    '<td align="center">' + item.noBudgeting+ '</td>' +
                    '<td align="center">' + item.stJumlah+ '</td>' +
                    '<td align="center">' + item.stBudgetBiaya+ '</td>' +
                    '<td align="center">' + item.stBudgetTerpakai+ '</td>' +
                    '<td align="center">' + item.keperluan+ '</td>' +
                    '<td align="center">' + item.keterangan+ '</td>' +
                    "</tr>";
            });
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
            var stBudgetTerpakai=$('#budget_terpakai').val();
            var keperluan = "";
            if (transaksi=="R"){
                keperluan=$('#keperluanText').val();
            } else if (transaksi=="I"){
                keperluan=$('#keperluanCombo').val();
            }
            var keterangan=$('#keterangan').val();

            if (branchId!=""&&divisiId!=""&&stTanggal!=""&&transaksi!=""&&noBudgeting!=""&&stJumlah!=""&&stBudgetBiaya!=""&&stBudgetTerpakai!=""&&keterangan!=""&&keperluan!=""){
                PengajuanBiayaAction.saveSessionPengajuan(branchId,divisiId,stTanggal,transaksi,noBudgeting,stJumlah,stBudgetBiaya,stBudgetTerpakai,keperluan,keterangan,function(result){
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
    })
</script>
<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>

