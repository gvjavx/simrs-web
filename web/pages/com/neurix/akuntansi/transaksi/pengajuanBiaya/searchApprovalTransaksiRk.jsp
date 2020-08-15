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
                        <h3 class="box-title">Search Approval Transaksi RK</h3>
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
                                                    <label>Transaksi RK ID </label>
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
                                <table id="showdata" width="90%">
                                    <tr>
                                        <td align="center">
                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                       height="500" width="900" autoOpen="false"
                                                       title="Transaksi RK">
                                                <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfResultPBRK" value="#session.listOfResultPengajuanBiayaRk" scope="request" />
                                            <display:table name="listOfResultPBRK" class="table table-condensed table-striped table-hover"
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
                                                <display:column property="pengajuanBiayaId" sortable="true" title="Transaksi RK ID" />
                                                <display:column property="tanggal" sortable="true" title="Tanggal" />
                                                <display:column property="noJurnal" sortable="true" title="No. Jurnal" />
                                                <display:column property="keterangan" sortable="true" title="Keterangan" />
                                                <display:column property="stTotalBiaya" sortable="true" title="Total Biaya" />
                                                <display:column property="coaAjuan" sortable="true" title="Coa Ajuan" />
                                                <display:column property="coaTarget" sortable="true" title="Coa Target" />
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
    <div class="modal-dialog modal-lg">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Approve Transaksi RK</h4>
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
                        <label class="control-label col-sm-3" >Kode Rekening RK : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="modCoaRk">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Kode Rekening Giro : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="modCoaGiro">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jumlah : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="modStJumlah">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Transaksi : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="modTransaksi">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="modBranchId">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan : </label>
                        <div class="col-sm-8">
                            <textarea class="form-control" id="modKeterangan"> </textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a id="btnApprove" type="button" class="btn btn-default btn-primary"><i class="fa fa-check"></i> Approve</a>
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

    function getCoaGiro() {
        var option = '<option value=""></option>';
        var tipeTransaksi = $('#modTransaksi').val();
        var transaksi = "";
        var posisi ="";
        switch(tipeTransaksi) {
            case "SMK":
                transaksi = "60";
                posisi="D";
                break;
            case "PDU":
                transaksi = "61";
                posisi="D";
                break;
        }

        KodeRekeningAction.getKodeRekeningLawanByTransId(transaksi,posisi,function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option += '<option value="'+item.kodeRekening+'">'+item.tampilanCoa+'</option>';
                });
                $('#modCoaGiro').html(option);
            }else{
                $('#modCoaGiro').html(option);
            }
        });
    }
    $(document).ready(function() {
    });

    $('#btnApprove').click(function(){
        var who = $('#myForm').attr('action');
        var id = document.getElementById("modPengajuanBiayaId").value;
        var coaBank=document.getElementById("modCoaGiro").value;
        var coaRk=document.getElementById("modCoaRk").value;
        var jumlah=document.getElementById("modStJumlah").value;
        var transaksi=document.getElementById("modTransaksi").value;
        var branchId=document.getElementById("modBranchId").value;
        var keterangan=document.getElementById("modKeterangan").value;


        if (coaBank!=""&&keterangan!=""){
            if (confirm('Are you sure you want to save this Record?')) {
                dwr.engine.setAsync(false);
                PengajuanBiayaAction.saveApprove(id,"Y",who,coaBank,coaRk,jumlah,transaksi,branchId,keterangan, function(listdata) {
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
                console.log(item);
                $('#modCoaRk').val(item.coaAjuan);
                $('#modCoaGiro').val(item.coaTarget);
                $('#modStJumlah').val(item.stTotalBiaya);
                $('#modTipeTransaksi').val(item.tipeTransaksi);
                $('#modTransaksi').val(item.transaksi);
                $('#modBranchId').val(item.branchId);
                $('#modKeterangan').val(item.keterangan);
                if(item.approvePengajuanBiaya == true){
                    $('#btnApprove').hide();
                }else{
                    $('#btnApprove').show();
                }
            });
        });
        $('#modal-edit').find('.modal-title').text('Approve Transaksi RK');
        $('#modal-edit').modal('show');
        $('#myForm').attr('action', 'atasan');
        getCoaGiro();
    });

</script>


