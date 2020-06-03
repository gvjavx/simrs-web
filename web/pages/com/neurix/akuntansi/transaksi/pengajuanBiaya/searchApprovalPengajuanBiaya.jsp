<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 15/02/2018
  Time: 16.59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KasirRawatJalanAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PengajuanBiayaAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/KodeRekeningAction.js"/>'></script>
    <style>
        .pagebanner{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
        }
        .pagelinks{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }
    </style>
    <script type='text/javascript'>
        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };

        function cancelBtn2() {
            $('#view_dialog_function2').dialog('close');
        };

        $('body').on('hidden.bs.modal', '.modal', function () {
            $(this).removeData('bs.modal');
        });

        function showLoadingDialog(){
            $('#myModalLoading').modal('show');
        }

        function showAlert(){
            var verif = document.getElementById('verif').value;
            var erVerif = document.getElementById('erVerif').value;
            if(verif !=  ""){
                document.getElementById('succesAlert').style.display = 'block';
                var sc = document.getElementById('succesAlert').value;
                if ( sc != ""){
                    sc = "";
                }
                $("#succesAlert").fadeTo(1000, 500).slideUp(500, function(){
                    $("succesAlert").slideUp(500);
                });
            }else if(erVerif != "") {
                document.getElementById('errorAlert').style.display = 'block';
                erVerif = null;
                $("#errorAlert").fadeTo(1000, 500).slideUp(500, function(){
                    $("errorAlert").slideUp(500);
                });
            }
        }

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" onload="showAlert()">

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
                        <h3 class="box-title">Search Approval Pengajuan Biaya</h3>
                    </div>

                    <form role="form" method="post" id="searchForm" action="viewNotifikasi_notifikasi.action?tipeNotif=TN01">
                        <div class="box-body">
                            <s:hidden id="verif" name="alat.verif"/>
                            <s:hidden id="erVerif" name="alat.erVerif"/>
                            <div id="errorAlert" style="display: none" class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.erVerif"/></center></div>
                            <div id="succesAlert" style="display: none" class="alert alert-success alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><center><s:property value="alat.verif"/></center></div>
                            <table width="100%" align="center">
                                <tr>
                                    <td align="center">
                                        <table>
                                            <tr>
                                                <td>
                                                    <label>Pengajuan Biaya ID </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <s:textfield id="pengajuanBiayaId" name="pengajuanBiaya.pengajuanBiayaId" cssClass="form-control" cssStyle="margin-top: -25px; margin-left: 20px" />
                                                </td>
                                            </tr>
                                        </table>
                                        <br><br>
                                        <table>
                                            <tr>
                                                <td>
                                                    <sj:submit type="button" cssClass="btn btn-primary" formIds="searchForm" id="search" name="search"
                                                               onClickTopics="showDialog" onCompleteTopics="closeDialog" onclick="showLoadingDialog();">
                                                        <i class="fa fa-search"></i>
                                                        Search
                                                    </sj:submit>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                            <center>
                                <table id="showdata" width="50%">
                                    <tr>
                                        <td align="center">
                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                       height="500" width="900" autoOpen="false"
                                                       title="Pengajuan Biaya">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfResultPB" value="#session.listOfResultPengajuanBiaya" scope="request" />
                                            <display:table name="listOfResultPB" class="table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag_Notifikasi.action" id="row" pagesize="20" style="font-size:10">

                                                <display:column media="html" title="Approve">
                                                    <s:if test="#attr.row.approvePengajuanBiaya">
                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                    </s:if>
                                                    <s:elseif test="#attr.row.notApprove">
                                                        <img border="0" src="<s:url value="/pages/images/icon_not_edit.png"/>" name="icon_edit">
                                                    </s:elseif>
                                                    <s:else>
                                                        <a href="javascript:;"  data="<s:property value="%{#attr.row.pengajuanBiayaId}"/>" href="javascript:;" class="item-edit" cssClass="item-edit">
                                                        <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                    </a>
                                                    </s:else>
                                                </display:column>
                                                <display:column property="pengajuanBiayaId" sortable="true" title="Pengajuan Biaya ID" />
                                            </display:table>
                                        </td>
                                    </tr>
                                </table>
                            </center>
                        </div>

                    </form>

                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<div id="modal-edit" class="modal fade" role="dialog">
    <div class="modal-dialog" style="width:1200px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Approve Pengajuan Biaya</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >ID : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="modPengajuanBiayaId">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit : </label>
                        <div class="col-sm-8">
                            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                            <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="mod_branch_id" required="true" disabled="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Divisi : </label>
                        <div class="col-sm-8">
                            <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                            <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="mod_divisi_id" name="pengajuanBiaya.divisiId" required="false" readonly="true"
                                      listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                        </div>
                    </div>
                </form>
            </div>

            <br>
            <center>
                <table id="showdata2" width="90%">
                    <tr>
                        <td align="center">
                            <table style="width: 100%;" class="pengajuanBiayaTabel table table-bordered">
                            </table>
                        </td>
                    </tr>
                </table>
            </center>
            <div class="modal-footer">
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-approve-keuangan" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Approve Pengajuan Biaya</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="formApprovalKeuangan">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >ID : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="modPengajuanBiayaDetailId">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit : </label>
                        <div class="col-sm-8">
                            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                            <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="mod_branch_id_keuangan" required="true" disabled="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Divisi : </label>
                        <div class="col-sm-8">
                            <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                            <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="mod_divisi_id_keuangan" name="pengajuanBiaya.divisiId" required="false" readonly="true"
                                      listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-3" >Transaksi : </label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<input type="text" readonly class="form-control" id="mod_transaksi_keuangan">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >No. Budgetting : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="mod_no_budgetting_keuangan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jumlah : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="mod_jumlah_keuangan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="mod_keterangan_keuangan">
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-3" >Budget RKAP : </label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<input type="text" readonly class="form-control" id="mod_budget_keuangan">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <input type="hidden" class="form-control" id="mod_status_approve">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Metode Pembiayaan : </label>
                        <div class="col-sm-8">
                            <s:select list="#{'A':'Kas','KP':'Kantor Pusat'}" onchange="changeKas(this.value)"
                                      id="mod_status_keuangan" headerKey="" headerValue="[Select One]" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group" id="kas_keuangan">
                        <label class="control-label col-sm-3" >Kas : </label>
                        <div class="col-sm-8">
                            <select class="form-control" id="mod_kas_keuangan">
                                <option value="" ></option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a id="btnApproveKeuangan" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div>
    </div>
</div>

<%@ include file="/pages/common/footer.jsp" %>

<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>

<script type="application/javascript">
    $('#btnApprove').click(function(){
        var who = $('#myForm').attr('action');
        var id = document.getElementById("modPengajuanBiayaId").value;
        var coaBank=document.getElementById("modCoaGiro").value;
        var coaRk=document.getElementById("modCoaRk").value;
        var jumlah=document.getElementById("modStJumlah").value;
        var transaksi=document.getElementById("modTransaksi").value;
        var branchId=document.getElementById("modBranchId").value;
        var keterangan=document.getElementById("modKeterangan").value;

        var tipeTransaksi ="";

        switch(transaksi) {
            case "SMK":
                tipeTransaksi="60";
                break;
            case "PDU":
                tipeTransaksi = "61";
                break;
        }

        if (coaBank!=""&&keterangan!=""){
            if (confirm('Are you sure you want to save this Record?')) {
                dwr.engine.setAsync(false);
                PengajuanBiayaAction.saveApprove(id,"Y",who,coaBank,coaRk,jumlah,tipeTransaksi,branchId,keterangan, function(listdata) {
                    alert('Data Successfully Updated');
                    $('#modal-edit').modal('hide');
                    $('#myForm')[0].reset();
                    location.reload();
                });
            }
        } else {
            var msg = "";
            if (coaBank == "") {
                msg += "Belum memilih kode rekening giro \n";
            }
            if (keterangan == "") {
                msg += "keterangan masih kosong";
            }
            alert(msg);
        }
    });

    $('.table').on('click', '.item-edit', function() {
        var pengajuanId = $(this).attr('data');
        $('#modPengajuanBiayaId').val(pengajuanId);
        PengajuanBiayaAction.approveAtasan(pengajuanId, function(listdata) {
            $.each(listdata, function (i, item) {
                loadPengajuan(item.pengajuanBiayaId);
            });
        });
        $('#modal-edit').find('.modal-title').text('Approve Pengajuan Biaya');
        $('#modal-edit').modal('show');
        $('#myForm').attr('action', 'atasan');
    });

    window.loadPengajuan =  function(pengajuanId){
        $('.pengajuanBiayaTabel').find('tbody').remove();
        $('.pengajuanBiayaTabel').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PengajuanBiayaAction.searchPengajuanDetail(pengajuanId,function(listdata){
            tmp_table = "<thead style='font-size: 10px; color: white' ><tr class='active'>"+
                "<th style='text-align: center; background-color:  #90ee90'>Approval Kasubdiv</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Approval Kadiv</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Approval Kepala RS / Kabid</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Approval Keuangan</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Approval Keuangan Kanpus</th>"+
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
                var approval ='<td align="center"><a href="javascript:;" data="'+item.pengajuanBiayaDetailId+'"  status="'+item.statusApproval+'" class="item-approve" >\n' +
                    '<img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">\n' +
                    '</a></td>';
                var approvalKeuangan ='<td align="center"><a href="javascript:;" data="'+item.pengajuanBiayaDetailId+'"  status="'+item.statusApproval+'" unit="'+item.branchId+'" divisi="'+item.divisiId+'"  keterangan="'+item.keterangan+'"  jumlah="'+item.stJumlah+'"  noBudgetting="'+item.noBudgeting+'" class="item-approve-keuangan" >\n' +
                    '<img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">\n' +
                    '</a></td>';
                var approvalKeuanganKp ='<td align="center"><a href="javascript:;" data="'+item.pengajuanBiayaDetailId+'"  status="'+item.statusApproval+'" unit="'+item.branchId+'" divisi="'+item.divisiId+'"  keterangan="'+item.keterangan+'"  jumlah="'+item.stJumlah+'"  noBudgetting="'+item.noBudgeting+'" class="item-approve-keuangan" >\n' +
                    '<img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">\n' +
                    '</a></td>';
                var success ='<td align="center"><a href="javascript:;">\n' +
                    '<img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">\n' +
                    '</a></td>';
                var approvalKS='<td ></td>';
                var approvalKD='<td ></td>';
                var approvalGM='<td ></td>';
                var approvalKE='<td ></td>';
                var approvalKEKP='<td ></td>';

                if (item.statusApproval=="KS"&&item.statusUserApproval=="KS"){
                    approvalKS = approval;
                }else if (item.statusApproval=="KD"&&item.statusUserApproval=="KD"){
                    approvalKD = approval;
                }else if (item.statusApproval=="GM"&&item.statusUserApproval=="GM"){
                    approvalGM = approval;
                }else if (item.statusApproval=="KE"&&item.statusUserApproval=="KE"){
                    approvalKE = approvalKeuangan;
                }else if (item.statusApproval=="KEKP"&&item.statusUserApproval=="KEKP"){
                    approvalKEKP = approvalKeuanganKp;
                }
                switch (item.statusApproval) {
                    case "KD":
                        approvalKS = success;
                        break;
                    case "GM":
                        approvalKS = success;
                        approvalKD = success;
                        break;
                    case "KE":
                        approvalKS = success;
                        approvalKD = success;
                        approvalGM = success;
                        break;
                    case "KEKP":
                        approvalKS = success;
                        approvalKD = success;
                        approvalGM = success;
                        approvalKE = success;
                        approvalKEKP = success;
                        break;
                    case "D":
                        approvalKS = success;
                        approvalKD = success;
                        approvalGM = success;
                        approvalKE = success;
                        approvalKEKP = success;
                        break;

                }

                tmp_table += '<tr style="font-size: 10px;" ">' +
                    approvalKS+
                    approvalKD+
                    approvalGM +
                    approvalKE +
                    approvalKEKP +
                    '<td align="center">' + item.stTanggal+ '</td>' +
                    '<td align="center">' + transaksi+ '</td>' +
                    '<td align="center">' + item.noBudgeting+ '</td>' +
                    '<td align="center">' + item.stJumlah+ '</td>' +
                    '<td align="center">' + item.stBudgetBiaya+ '</td>' +
                    '<td align="center">' + item.stBudgetTerpakai+ '</td>' +
                    '<td align="center">' + item.keperluan+ '</td>' +
                    '<td align="center">' + item.keterangan+ '</td>' +
                    "</tr>";

                $('#mod_branch_id').val(item.branchId);
                $('#mod_divisi_id').val(item.divisiId);
            });
            $('.pengajuanBiayaTabel').append(tmp_table);
        });
    };
    $('.pengajuanBiayaTabel').on('click', '.item-approve', function() {
        var pengajuanId = $(this).attr('data');
        var status = $(this).attr('status');
        if (confirm("Apakah anda ingin menyetujui pengajuan biaya ini ?")){
            PengajuanBiayaAction.saveApproveAtasanPengajuan(pengajuanId,status,function() {
                alert("Sukses Approve");
                window.location.reload();
            });
        }
    });

    $('#btnApproveKeuangan').click(function() {
        var pengajuanId = $('#modPengajuanBiayaDetailId').val();
        var status = $('#mod_status_approve').val();
        var approvalStatus = $('#mod_status_keuangan').val();
        var branchId = $('#mod_branch_id_keuangan').val();
        var divisiId = $('#mod_divisi_id_keuangan').val();

        var keterangan = $('#mod_keterangan_keuangan').val();
        var jumlah = $('#mod_jumlah_keuangan').val();
        var noBudgetting = $('#mod_no_budgetting_keuangan').val();
        var coaKas = $('#mod_kas_keuangan').val();
        if (confirm("Apakah anda ingin menyetujui pengajuan biaya ini ?")){
            PengajuanBiayaAction.saveApproveKeuanganPengajuan(pengajuanId,status,approvalStatus,branchId,keterangan,jumlah,noBudgetting,divisiId,coaKas,function() {
                alert("Sukses Approve");
                window.location.reload();
            });
        }
    });

    $('.pengajuanBiayaTabel').on('click', '.item-approve-keuangan', function() {
        selectPembayaran();
        $('#kas_keuangan').hide();
        $('#modPengajuanBiayaDetailId').val($(this).attr('data'));
        $('#mod_status_approve').val($(this).attr('status'));
        $('#mod_branch_id_keuangan').val($(this).attr('unit'));
        $('#mod_divisi_id_keuangan').val($(this).attr('divisi'));
        $('#mod_keterangan_keuangan').val($(this).attr('keterangan'));
        $('#mod_jumlah_keuangan').val($(this).attr('jumlah'));
        $('#mod_no_budgetting_keuangan').val($(this).attr('noBudgetting'));

        $('#modal-approve-keuangan').find('.modal-title').text('Approve Pengajuan Biaya Keuangan');
        $('#modal-approve-keuangan').modal('show');
    });

    function selectPembayaran(){
        var option = '<option value=""></option>';
        KasirRawatJalanAction.getListPembayaran(function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option += '<option value="'+item.coa+'">'+item.pembayaranName+'</option>';
                });
                $('#mod_kas_keuangan').html(option);
            }else{
                $('#mod_kas_keuangan').html(option);
            }
        });
    }
    function changeKas(values) {
        if (values=="A"){
            $('#kas_keuangan').show();
        }else{
            $('#kas_keuangan').hide();
        }
    }
</script>


