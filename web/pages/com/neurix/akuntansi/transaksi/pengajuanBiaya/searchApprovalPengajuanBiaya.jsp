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
    <script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>
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
                                <table id="showdata" width="80%">
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
                                                        <a href="javascript:;"  data="<s:property value="%{#attr.row.pengajuanBiayaId}"/>" branchId="<s:property value="%{#attr.row.branchId}"/>" totalBiaya="<s:property value="%{#attr.row.stTotalBiaya}"/>" href="javascript:;" class="item-edit" cssClass="item-edit">
                                                        <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                    </a>
                                                    </s:else>
                                                </display:column>
                                                <display:column property="pengajuanBiayaId" sortable="true" title="Pengajuan Biaya ID" />
                                                <display:column property="branchName" sortable="true" title="Unit" />
                                                <display:column property="divisiName" sortable="true" title="Divisi" />
                                                <display:column property="stTanggal" sortable="true" title="Tanggal" />
                                                <display:column property="stTotalBiaya" sortable="true" title="Total Biaya ( RP )" />
                                                <display:column property="keterangan" sortable="true" title="Keterangan" />
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
    <div class="modal-dialog modal-flat" style="width:1300px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Approve Pengajuan Biaya</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <br>
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
                                <s:select cssClass="form-control mod_branch_id_class" list="#comboBranch.listOfComboBranches" id="mod_branch_id" required="true" disabled="true"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3" >Divisi : </label>
                            <div class="col-sm-8">
                                <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                                <s:select cssClass="form-control mod_divisi_id_class" list="#comboPosition.listOfComboPositions" id="mod_divisi_id" disabled="true" name="pengajuanBiaya.divisiId" required="false" readonly="true"
                                          listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3" >Total Biaya ( RP ) : </label>
                            <div class="col-sm-8">
                                <input type="text" readonly class="form-control" id="mod_total_biaya">
                            </div>
                        </div>
                    </form>
                    <br>
                    <center>
                        <table id="showdata2" width="95%">
                            <tr>
                                <td align="center">
                                    <table style="width: 100%;" class="pengajuanBiayaTabel table table-bordered">
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </center>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <a id="btnApproveRk" style="display: none" type="button" class="btn btn-default btn-success"><i class="fa fa-arrow-right"></i> Buat Transaksi RK </a>
                <a id="btnTerimaRk" style="display: none" type="button" class="btn btn-default btn-success"><i class="fa fa-arrow-right"></i> Terima RK</a>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>

<div id="modal-approve-atasan" class="modal fade" role="dialog">
    <div class="modal-dialog modal-flat modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Approve Pengajuan Biaya</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="formApprovalAtasan">
                    <div class="form-group">
                        <label class="control-label col-sm-4" >ID : </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="modPengajuanBiayaDetailIdAtasan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Unit : </label>
                        <div class="col-sm-6">
                            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                            <s:select cssClass="form-control mod_branch_id_class" list="#comboBranch.listOfComboBranches" id="mod_branch_id_atasan" required="true" disabled="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Divisi : </label>
                        <div class="col-sm-6">
                            <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                            <s:select cssClass="form-control mod_divisi_id_class" list="#comboPosition.listOfComboPositions" id="mod_divisi_id_atasan" disabled="true" name="pengajuanBiaya.divisiId" required="false" readonly="true"
                                      listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tipe : </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="mod_tipe_atasan">
                        </div>
                    </div>
                    <div class="form-group" id="view_no_kontrak_atasan">
                        <label class="control-label col-sm-4" >No. Kontrak: </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="mod_no_kontrak_atasan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Keperluan: </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="mod_keperluan_atasan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >No. Budgetting : </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="mod_no_budgetting_atasan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal : </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control mod_tanggal_class" id="mod_tanggal_atasan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jumlah (RP) : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="mod_jumlah_atasan" onkeyup="formatRupiah2(this)" style="text-align: right">
                        </div>
                        <div class="col-md-1" style="margin-top: 7px">
                            <a href="javascript:;" class="btnViewStok">
                                <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" name="icon_view">
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" id="txt_budget_atasan" >Budgeting Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_budget_atasan" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group" id="view_budget_sd_atasan">
                        <label class="control-label col-sm-4" >Budgeting s/d Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_budget_sd_atasan" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" id="txt_budget_terpakai_atasan" >Budgeting terpakai Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_budget_terpakai_atasan" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group" id="view_budget_terpakai_sd_atasan">
                        <label class="control-label col-sm-4" >Budgeting terpakai s/d Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_budget_terpakai_sd_atasan" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" id="txt_sisa_budget_atasan">Sisa Budget Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_sisa_budget_atasan" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group" id="view_sisa_budget_sd_atasan">
                        <label class="control-label col-sm-4" >Sisa Budget s/d Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_sisa_budget_sd_atasan" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Keterangan : </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="mod_keterangan_atasan">
                        </div>
                    </div>
                    <input type="hidden" class="form-control" id="mod_status_approve_atasan">
                </form>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <a id="btnApproveAtasan" type="button" class="btn btn-default btn-success"><i class="fa fa-arrow-right"></i> Approve</a>
                <a id="btnNotApproveAtasan" type="button" class="btn btn-default btn-danger"><i class="fa fa-close"></i> Not Approve</a>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>

<div id="modal-approve-keuangan" class="modal fade" role="dialog">
    <div class="modal-dialog modal-flat modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Approve Pengajuan Biaya</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="formApprovalKeuangan">
                    <div class="form-group">
                        <label class="control-label col-sm-4" >ID : </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="modPengajuanBiayaDetailId">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Unit : </label>
                        <div class="col-sm-6">
                            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                            <s:select cssClass="form-control mod_branch_id_class" list="#comboBranch.listOfComboBranches" id="mod_branch_id_keuangan" required="true" disabled="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Divisi : </label>
                        <div class="col-sm-6">
                            <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                            <s:select cssClass="form-control mod_divisi_id_class" list="#comboPosition.listOfComboPositions" id="mod_divisi_id_keuangan" disabled="true" name="pengajuanBiaya.divisiId" required="false" readonly="true"
                                      listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tipe : </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="mod_tipe_keuangan">
                        </div>
                    </div>
                    <div class="form-group" id="view_no_kontrak_keuangan">
                        <label class="control-label col-sm-4" >No. Kontrak: </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="mod_no_kontrak_keuangan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Keperluan: </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="mod_keperluan_keuangan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >No. Budgetting : </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="mod_no_budgetting_keuangan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal : </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control mod_tanggal_class" id="mod_tanggal_keuangan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal Realisasi: </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="mod_tanggal_realisasi_keuangan">
                        </div>
                    </div>
                    <script>
                        $("#mod_tanggal_realisasi_keuangan").datepicker({
                            autoclose: true,
                            changeMonth: true,
                            changeYear:true,
                            dateFormat:'dd-mm-yy'
                        });
                    </script>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jumlah ( RP ) : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="mod_jumlah_keuangan" onkeyup="formatRupiah2(this)" style="text-align: right">
                        </div>
                        <div class="col-md-1" style="margin-top: 7px">
                            <a href="javascript:;" class="btnViewStok">
                                <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" name="icon_view">
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" id="txt_budget_keuangan" >Budgeting Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_budget_keuangan" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group" id="view_budget_sd_keuangan">
                        <label class="control-label col-sm-4" >Budgeting s/d Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_budget_sd_keuangan" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" id="txt_budget_terpakai_keuangan" >Budgeting terpakai Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_budget_terpakai_keuangan" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group" id="view_budget_terpakai_sd_keuangan">
                        <label class="control-label col-sm-4" >Budgeting terpakai s/d Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_budget_terpakai_sd_keuangan" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" id="txt_sisa_budget_keuangan" >Sisa Budget Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_sisa_budget_keuangan" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group" id="view_sisa_budget_sd_keuangan">
                        <label class="control-label col-sm-4" >Sisa Budget s/d Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_sisa_budget_sd_keuangan" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Keterangan : </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="mod_keterangan_keuangan">
                        </div>
                    </div>

                    <input type="hidden" class="form-control" id="mod_status_approve">
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Metode Pembiayaan : </label>
                        <div class="col-sm-6">
                            <s:select list="#{'A':'Unit','KP':'Kantor Pusat'}" onchange="changeKas(this.value)"
                                      id="mod_status_keuangan" headerKey="" headerValue="[Select One]" cssClass="form-control" />
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <a id="btnApproveKeuangan" type="button" class="btn btn-default btn-success"><i class="fa fa-arrow-right"></i> Approve</a>
                <a id="btnNotApproveKeuangan" type="button" class="btn btn-default btn-danger"><i class="fa fa-close"></i> Not Approve</a>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>

<div id="modal-approve-keuangan-kp" class="modal fade" role="dialog">
    <div class="modal-dialog modal-flat modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Approve Pengajuan Biaya</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="formApprovalKeuanganKp">
                    <div class="form-group">
                        <label class="control-label col-sm-4" >ID : </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="modPengajuanBiayaDetailIdKp">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Unit : </label>
                        <div class="col-sm-6">
                            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                            <s:select cssClass="form-control mod_branch_id_class" list="#comboBranch.listOfComboBranches" id="mod_branch_id_keuangan_kp" required="true" disabled="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Divisi : </label>
                        <div class="col-sm-6">
                            <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                            <s:select cssClass="form-control mod_divisi_id_class" list="#comboPosition.listOfComboPositions" id="mod_divisi_id_keuangan_kp" disabled="true" name="pengajuanBiaya.divisiId" required="false" readonly="true"
                                      listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tipe : </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="mod_tipe_keuangan_kp">
                        </div>
                    </div>
                    <div class="form-group" id="view_no_kontrak_keuangan_kp">
                        <label class="control-label col-sm-4" >No. Kontrak: </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="mod_no_kontrak_keuangan_kp">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Keperluan: </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="mod_keperluan_keuangan_kp">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >No. Budgetting : </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="mod_no_budgetting_keuangan_kp">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tanggal : </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control mod_tanggal_class" id="mod_tanggal_keuangan_kp">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Jumlah ( RP ) : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="mod_jumlah_keuangan_kp" onkeyup="formatRupiah2(this)" style="text-align: right">
                        </div>
                        <div class="col-md-1" style="margin-top: 7px">
                            <a href="javascript:;" class="btnViewStok">
                                <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" name="icon_view">
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" id="txt_budget_keuangan_kp">Budgeting Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_budget_keuangan_kp" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group" id="view_budget_sd_keuangan_kp">
                        <label class="control-label col-sm-4" >Budgeting s/d Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_budget_sd_keuangan_kp" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" id="txt_budget_terpakai_keuangan_kp" >Budgeting terpakai Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_budget_terpakai_keuangan_kp" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group" id="view_budget_terpakai_sd_keuangan_kp">
                        <label class="control-label col-sm-4" >Budgeting terpakai s/d Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_budget_terpakai_sd_keuangan_kp" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" id="txt_sisa_budget_keuangan_kp" >Sisa Budget Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_sisa_budget_keuangan_kp" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group" id="view_sisa_budget_sd_keuangan_kp">
                        <label class="control-label col-sm-4" >Sisa Budget s/d Bulan Ini (RP) : </label>
                        <div class="col-md-6">
                            <input type="text" readonly class="form-control" id="mod_sisa_budget_sd_keuangan_kp" style="text-align: right">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Keterangan : </label>
                        <div class="col-sm-6">
                            <input type="text" readonly class="form-control" id="mod_keterangan_keuangan_kp">
                        </div>
                    </div>
                    <input type="hidden" class="form-control" id="mod_status_approve_kp">
                    <%--<div class="form-group" id="kas_keuangan_kp">--%>
                        <%--<label class="control-label col-sm-3" >Kas : </label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<select class="form-control" id="mod_kas_keuangan_kp">--%>
                                <%--<option value="" ></option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </form>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <a id="btnApproveKeuanganKp" type="button" class="btn btn-default btn-success"><i class="fa fa-arrow-right"></i> Approve</a>
                <a id="btnNotApproveKeuanganKp" type="button" class="btn btn-default btn-danger"><i class="fa fa-close"></i> Not Approve</a>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>

<div id="modal-not-approve" class="modal fade" role="dialog">
    <div class="modal-dialog modal-flat modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Not Approve Pengajuan Biaya</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="formNotApprove">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >ID : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="modPengajuanBiayaDetailIdNotApprove">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan : </label>
                        <div class="col-sm-8">
                            <textarea class="form-control" id="mod_keterangan_not_approve" rows="3"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <a id="btnNotApprove" type="button" class="btn btn-default btn-danger"><i class="fa fa-close"></i> Not Approve</a>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>

<div id="modal-rk" class="modal fade" role="dialog">
    <div class="modal-dialog modal-flat modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Approve Pengajuan Biaya</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="formPengirimanRk">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >ID : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="modPengajuanBiayaIdRk">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit : </label>
                        <div class="col-sm-8">
                            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                            <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="mod_branch_id_rk" required="true" disabled="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Divisi : </label>
                        <div class="col-sm-8">
                            <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                            <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="mod_divisi_id_rk" disabled="true" name="pengajuanBiaya.divisiId" required="false" readonly="true"
                                      listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Total Biaya ( RP ) : </label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="mod_jumlah_rk" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan : </label>
                        <div class="col-sm-8">
                            <textarea class="form-control" id="mod_keterangan_rk" rows="5"></textarea>
                        </div>
                    </div>
                    <input type="hidden" class="form-control" id="mod_status_rk">
                    <div class="form-group" id="kas_rk">
                        <label class="control-label col-sm-3" >Kas : </label>
                        <div class="col-sm-8">
                            <select class="form-control" id="mod_kas_rk">
                                <option value="" ></option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <a id="btnRk" type="button" class="btn btn-default btn-success"><i class="fa fa-arrow-right"></i> Buat Jurnal RK</a>
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

<%@ include file="/pages/common/footer.jsp" %>

<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>

<script type="application/javascript">
    $('.table').on('click', '.item-edit', function() {
        var pengajuanId = $(this).attr('data');
        var branchId = $(this).attr('branchId');
        var totalBiaya = $(this).attr('totalBiaya');
        $('#modPengajuanBiayaId').val(pengajuanId);
        $('#mod_total_biaya').val(totalBiaya);
        PengajuanBiayaAction.approveAtasan(pengajuanId, function(listdata) {
            $.each(listdata, function (i, item) {
                loadPengajuan(item.pengajuanBiayaId,branchId);
            });
        });
        $('#modal-edit').find('.modal-title').text('Approve Pengajuan Biaya');
        $('#modal-edit').modal('show');
        $('#myForm').attr('action', 'atasan');
    });

    window.loadPengajuan =  function(pengajuanId,branchId){
        $('.pengajuanBiayaTabel').find('tbody').remove();
        $('.pengajuanBiayaTabel').find('thead').remove();
        var namaApproveKasubdiv = "";
        var namaApproveKadiv = "";
        var namaApproveKaRs = "";
        var namaApproveKeuKp = "";
        var namaApproveKeu = "";

        if (branchId=="KP"){
            namaApproveKasubdiv = "<th style='text-align: center; background-color:  #90ee90'>App. Kasubid</th>";
            namaApproveKadiv = "<th style='text-align: center; background-color:  #90ee90'>App. Kabid </th>";
            namaApproveKaRs = "<th style='text-align: center; background-color:  #90ee90'>App. Dirkeu</th>";
        }  else{
            namaApproveKasubdiv = "<th style='text-align: center; background-color:  #90ee90'>App. Kasubdiv</th>";
            namaApproveKadiv = "<th style='text-align: center; background-color:  #90ee90'>App. Kadiv </th>";
            namaApproveKaRs = "<th style='text-align: center; background-color:  #90ee90'>App. Ka RS</th>";
            namaApproveKeuKp = "<th style='text-align: center; background-color:  #90ee90'>App. Keu. Kanpus</th>";
            // namaApproveKeu = "<th style='text-align: center; background-color:  #90ee90'>Terima RK</th>";
        }

        dwr.engine.setAsync(false);
        var tmp_table = "";

        PengajuanBiayaAction.searchPengajuanDetail(pengajuanId,function(listdata){
            tmp_table = "<thead style='font-size: 9px;' ><tr class='active'>"+
                namaApproveKasubdiv +
                namaApproveKadiv +
                namaApproveKaRs +
                "<th style='text-align: center; background-color:  #90ee90'>App. Keu.</th>"+
                namaApproveKeuKp+
                "<th style='text-align: center; background-color:  #90ee90'>Tanggal</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Tipe Budget</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>No. Budget</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Jumlah ( RP )</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Budget RKAP ( RP )</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Budget Terpakai ( RP )</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Keperluan</th>"+
                "<th style='text-align: center; background-color:  #90ee90'>Keterangan</th>"+
                // namaApproveKeu+
                "</tr></thead>";
            var i = i ;
            var jumlah = 0;
            $.each(listdata, function (i, item) {
                jumlah = jumlah+item.jumlah;
                var transaksi ="";
                switch (item.transaksi) {
                    case "R":
                        transaksi="Rutin";
                        break;
                    case "I":
                        transaksi="Investasi";
                        break;
                }
                var approval ='<td align="center"><a href="javascript:;" data="'+item.pengajuanBiayaDetailId+'" tanggal="'+item.stTanggal+'" status="'+item.statusApproval+'" unit="'+item.branchId+'" divisi="'+item.divisiId+'"  keterangan="'+item.keterangan+'"  jumlah="'+item.stJumlah+'" budget="'+item.stBudgetBiaya+'" budgetsd="'+item.stBudgetBiayaSdBulanIni+'" budgetterpakai="'+item.stBudgetTerpakai+'" budgetterpakaisd="'+item.stBudgetTerpakaiSdBulanIni+'" sisabudget="'+item.stSisaBudget+'" sisabudgetsd="'+item.stSisaBudgetSdBulanIni+'" noBudgetting="'+item.noBudgeting+'" tipe="'+item.transaksi+'" keperluan="'+item.keperluanName+'" noKontrak="'+item.noKontrak+'" class="item-approve-atasan" >\n' +
                    '<img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">\n' +
                    '</a></td>';
                var approvalKeuangan ='<td align="center"><a href="javascript:;" data="'+item.pengajuanBiayaDetailId+'" tanggal="'+item.stTanggal+'" status="'+item.statusApproval+'" unit="'+item.branchId+'" divisi="'+item.divisiId+'"  keterangan="'+item.keterangan+'"  jumlah="'+item.stJumlah+'" budget="'+item.stBudgetBiaya+'" budgetsd="'+item.stBudgetBiayaSdBulanIni+'" budgetterpakai="'+item.stBudgetTerpakai+'" budgetterpakaisd="'+item.stBudgetTerpakaiSdBulanIni+'" sisabudget="'+item.stSisaBudget+'" sisabudgetsd="'+item.stSisaBudgetSdBulanIni+'" noBudgetting="'+item.noBudgeting+'" tipe="'+item.transaksi+'" keperluan="'+item.keperluanName+'" noKontrak="'+item.noKontrak+'" class="item-approve-keuangan" >\n' +
                    '<img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">\n' +
                    '</a></td>';
                var approvalKeuanganKp ='<td align="center"><a href="javascript:;" data="'+item.pengajuanBiayaDetailId+'" tanggal="'+item.stTanggal+'" status="'+item.statusApproval+'" unit="'+item.branchId+'" divisi="'+item.divisiId+'"  keterangan="'+item.keterangan+'"  jumlah="'+item.stJumlah+'" budget="'+item.stBudgetBiaya+'" budgetsd="'+item.stBudgetBiayaSdBulanIni+'" budgetterpakai="'+item.stBudgetTerpakai+'" budgetterpakaisd="'+item.stBudgetTerpakaiSdBulanIni+'" sisabudget="'+item.stSisaBudget+'" sisabudgetsd="'+item.stSisaBudgetSdBulanIni+'" noBudgetting="'+item.noBudgeting+'" tipe="'+item.transaksi+'" keperluan="'+item.keperluanName+'" noKontrak="'+item.noKontrak+'" class="item-approve-keuangan-kp" >\n' +
                    '<img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">\n' +
                    '</a></td>';
                var terimaKeuangan ='<td align="center"><a href="javascript:;" data="'+item.pengajuanBiayaDetailId+'"  status="'+item.statusApproval+'" unit="'+item.branchId+'" divisi="'+item.divisiId+'"  keterangan="'+item.keterangan+'"  jumlah="'+item.stJumlah+'" budget="'+item.stBudgetBiaya+'" budgetsd="'+item.stBudgetBiayaSdBulanIni+'" budgetterpakai="'+item.stBudgetTerpakai+'" budgetterpakaisd="'+item.stBudgetTerpakaiSdBulanIni+'" sisabudget="'+item.stSisaBudget+'" sisabudgetsd="'+item.stSisaBudgetSdBulanIni+'" noBudgetting="'+item.noBudgeting+'" class="item-terima-keuangan-kp" tipe="'+item.transaksi+'"  keperluan="'+item.keperluanName+'" noKontrak="'+item.noKontrak+'" >\n' +
                    '<img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">\n' +
                    '</a></td>';
                var success ='<td align="center"><a href="javascript:;">\n' +
                    '<img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">\n' +
                    '</a></td>';
                var failed ='<td align="center"><a href="javascript:;">\n' +
                    '<img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">\n' +
                    '</a></td>';
                var approvalKS='<td ></td>';
                var approvalKD='<td ></td>';
                var approvalGM='<td ></td>';
                var approvalKE='<td ></td>';
                var approvalKEKP='<td ></td>';
                var terimaKE='<td ></td>';

                if (item.statusApproval=="KS"&&item.statusUserApproval=="KS"){
                    approvalKS = approval;
                }else if (item.statusApproval=="KD"&&item.statusUserApproval=="KD"){
                    approvalKD = approval;
                }else if (item.statusApproval=="GM"&&item.statusUserApproval=="GM"){
                    approvalGM = approval;
                }else if (item.statusApproval=="KE"&&item.statusUserApproval=="KE"){
                    approvalKE = approvalKeuangan;
                }else if (item.statusApproval=="KE"&&item.statusUserApproval=="KEKP"){
                    approvalKE = approvalKeuangan;
                }else if (item.statusApproval=="KEKP"&&item.statusUserApproval=="KEKP"){
                    approvalKEKP = approvalKeuanganKp;
                }else if (item.statusApproval=="TKE"&&item.statusUserApproval=="TKE"){
                    terimaKE = terimaKeuangan;
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
                        break;
                    case "TKE":
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
                        terimaKE = success;
                        break;
                    case "NKS":
                        approvalKS = failed;
                        break;
                    case "NKD":
                        approvalKS = success;
                        approvalKD = failed;
                        break;
                    case "NGM":
                        approvalKS = success;
                        approvalKD = success;
                        approvalGM = failed;
                        break;
                    case "NKE":
                        approvalKS = success;
                        approvalKD = success;
                        approvalGM = success;
                        approvalKE = failed;
                        break;
                    case "NKEKP":
                        approvalKS = success;
                        approvalKD = success;
                        approvalGM = success;
                        approvalKE = success;
                        approvalKEKP = failed;
                        break;
                }

                if (branchId =="KP"){
                    approvalKEKP = "";
                    terimaKE = "";
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
                    '<td align="center">' + item.keperluanName+ '</td>' +
                    '<td align="center">' + item.keterangan+ '</td>' +
                    // terimaKE +
                    "</tr>";

                $('#mod_branch_id').val(item.branchId);
                $('#mod_divisi_id').val(item.divisiId);
            });

            PengajuanBiayaAction.cekApakahBolehRk(pengajuanId,function(data){
                if (data.showBuatRk){
                    $('#btnApproveRk').show();
                }else if (data.showTerimaRk) {
                    $('#btnTerimaRk').show();
                }
            });
            $('#mod_total_biaya').val(formatRupiahAngka(jumlah.toString()));
            $('.pengajuanBiayaTabel').append(tmp_table);
        });
    };
    $('.pengajuanBiayaTabel').on('click', '.item-approve-atasan', function() {
        var jumlah = $(this).attr('jumlah');
        jumlah = jumlah.replace(",",".");
        var tipe= $(this).attr('tipe');

        var tipeName ="";
        if (tipe=="I"){
            tipeName="Investasi";
        } else{
            tipeName="Rutin";
        }

        $('#mod_jumlah_atasan').val(jumlah);
        $('#modPengajuanBiayaDetailIdAtasan').val($(this).attr('data'));
        $('#mod_status_approve_atasan').val($(this).attr('status'));
        $('#mod_branch_id_atasan').val($(this).attr('unit'));
        $('#mod_divisi_id_atasan').val($(this).attr('divisi'));
        $('#mod_keterangan_atasan').val($(this).attr('keterangan'));
        $('#mod_tanggal_atasan').val($(this).attr('tanggal'));
        $('#mod_no_budgetting_atasan').val($(this).attr('noBudgetting'));
        $('#mod_budget_atasan').val($(this).attr('budget'));
        $('#mod_budget_sd_atasan').val($(this).attr('budgetsd'));
        $('#mod_budget_terpakai_atasan').val($(this).attr('budgetterpakai'));
        $('#mod_budget_terpakai_sd_atasan').val($(this).attr('budgetterpakaisd'));
        $('#mod_sisa_budget_atasan').val($(this).attr('sisabudget'));
        $('#mod_sisa_budget_sd_atasan').val($(this).attr('sisabudgetsd'));
        $('#mod_keperluan_atasan').val($(this).attr('keperluan'));
        $('#mod_no_kontrak_atasan').val($(this).attr('noKontrak'));
        $('#mod_tipe_atasan').val(tipeName);

        if (tipe=="I"){
            $('#txt_budget_atasan').text("Nilai Kontrak (RP) :");
            $('#txt_budget_terpakai_atasan').text("Nilai Kontrak Realisasi (RP) :");
            $('#txt_sisa_budget_atasan').text("Sisa Nilai Kontrak (RP) :");
            $('#view_budget_sd_atasan').hide();
            $('.btnViewStok').hide();
            $('#view_budget_terpakai_sd_atasan').hide();
            $('#view_sisa_budget_sd_atasan').hide();
            $('#view_no_kontrak_atasan').show();
        } else{
            $('#txt_budget_atasan').text("Budgeting Bulan Ini (RP) : ");
            $('#txt_budget_terpakai_atasan').text("Budgeting terpakai Bulan Ini (RP) : ");
            $('#txt_sisa_budget_atasan').text("Sisa Budget Bulan Ini (RP) : ");
            $('#view_budget_sd_atasan').show();
            $('.btnViewStok').show();
            $('#view_budget_terpakai_sd_atasan').show();
            $('#view_sisa_budget_sd_atasan').show();
            $('#view_no_kontrak_atasan').hide();
        }

        $('#modal-approve-atasan').modal('show');

    });

    $('#btnApproveAtasan').click(function() {
        var id = $('#modPengajuanBiayaId').val();
        var pengajuanId = $('#modPengajuanBiayaDetailIdAtasan').val();
        var branchId = $('#mod_branch_id_atasan').val();
        var status = $('#mod_status_approve_atasan').val();
        var jumlah = $('#mod_jumlah_atasan').val();

        var bolehDiubah = false;
        PengajuanBiayaAction.cekApakahPengajuanBisaDiubah(pengajuanId,jumlah,function(response) {
            bolehDiubah=response;
        });
        if (bolehDiubah){
            if (confirm("Apakah anda ingin menyetujui pengajuan biaya ini ?")){
                PengajuanBiayaAction.saveApproveAtasanPengajuan(pengajuanId,status,jumlah,function() {
                    alert("Sukses Approve");
                    loadPengajuan(id,branchId);
                    $('#modal-approve-atasan').modal('hide');
                });
            }
        } else{
            alert("Tidak bisa diapprove karena jumlah yang di approve melebihi jumlah yang diajukan");
        }

    });
    $('#btnNotApproveAtasan').click(function() {
        $('#modPengajuanBiayaDetailIdNotApprove').val($('#modPengajuanBiayaDetailIdAtasan').val());
        $('#modal-not-approve').modal('show');
    });

    $('#btnApproveKeuangan').click(function() {
        var id = $('#modPengajuanBiayaId').val();
        var pengajuanId = $('#modPengajuanBiayaDetailId').val();
        var status = $('#mod_status_approve').val();
        var approvalStatus = $('#mod_status_keuangan').val();
        var branchId = $('#mod_branch_id_keuangan').val();
        var divisiId = $('#mod_divisi_id_keuangan').val();

        var keterangan = $('#mod_keterangan_keuangan').val();
        var jumlah = $('#mod_jumlah_keuangan').val();
        var noBudgetting = $('#mod_no_budgetting_keuangan').val();
        var tanggalRealisasi = $('#mod_tanggal_realisasi_keuangan').val();

        var from = tanggalRealisasi.split('-');
        var f = new Date(from[2], from[1] - 1, from[0]);
        var d = new Date();
        d.setDate(d.getDate()-1);
        if (f<d){
            alert("tanggal realisasi tidak boleh kurang dari tanggal sekarang");
        }else{
            if (approvalStatus==""||tanggalRealisasi==""){
                var msg="";
                if (approvalStatus==""){
                    msg+="Belum memilih metode pembiayaan";
                }
                if (tanggalRealisasi==""){
                    msg+="Belum memilih tanggal Realisasi";
                }
                alert(msg);
            }else {
                if (confirm("Apakah anda ingin menyetujui pengajuan biaya ini ?")){
                    PengajuanBiayaAction.saveApproveKeuanganPengajuan(pengajuanId,status,approvalStatus,branchId,keterangan,jumlah,noBudgetting,divisiId,tanggalRealisasi,function(response) {
                        alert("Sukses Approve");
                        if (response=="Y"){
                            window.location.reload();
                        } else{
                            loadPengajuan(id,branchId);
                            $('#modal-approve-keuangan').modal('hide');
                        }
                    });
                }
            }
        }
    });
    $('#btnNotApproveKeuangan').click(function() {
        $('#modPengajuanBiayaDetailIdNotApprove').val($('#modPengajuanBiayaDetailId').val());
        $('#modal-not-approve').modal('show');
    });

    $('.pengajuanBiayaTabel').on('click', '.item-approve-keuangan', function() {
        selectPembayaran();
        $('#kas_keuangan').hide();
        var jumlah = $(this).attr('jumlah');
        jumlah = jumlah.replace(",",".");

        var tipe= $(this).attr('tipe');

        var tipeName ="";
        if (tipe=="I"){
            tipeName="Investasi";
        } else{
            tipeName="Rutin";
        }

        var branchId = $(this).attr('unit');

        if (branchId=="KP") {
            var option = '<option value="A">Kantor Pusat</option>';
            $('#mod_status_keuangan').html(option);
        }
        $('#mod_jumlah_keuangan').val(jumlah);
        $('#modPengajuanBiayaDetailId').val($(this).attr('data'));
        $('#mod_status_approve').val($(this).attr('status'));
        $('#mod_branch_id_keuangan').val($(this).attr('unit'));
        $('#mod_divisi_id_keuangan').val($(this).attr('divisi'));
        $('#mod_keterangan_keuangan').val($(this).attr('keterangan'));
        $('#mod_no_budgetting_keuangan').val($(this).attr('noBudgetting'));
        $('#mod_tanggal_keuangan').val($(this).attr('tanggal'));
        $('#mod_tanggal_realisasi_keuangan').val($(this).attr('tanggal'));
        $('#mod_budget_keuangan').val($(this).attr('budget'));
        $('#mod_budget_sd_keuangan').val($(this).attr('budgetsd'));
        $('#mod_budget_terpakai_keuangan').val($(this).attr('budgetterpakai'));
        $('#mod_budget_terpakai_sd_keuangan').val($(this).attr('budgetterpakaisd'));
        $('#mod_sisa_budget_keuangan').val($(this).attr('sisabudget'));
        $('#mod_sisa_budget_sd_keuangan').val($(this).attr('sisabudgetsd'));

        $('#mod_keperluan_keuangan').val($(this).attr('keperluan'));
        $('#mod_no_kontrak_keuangan').val($(this).attr('noKontrak'));
        $('#mod_tipe_keuangan').val(tipeName);

        if (tipe=="I"){
            $('#txt_budget_keuangan').text("Nilai Kontrak (RP) :");
            $('#txt_budget_terpakai_keuangan').text("Nilai Kontrak Realisasi (RP) :");
            $('#txt_sisa_budget_keuangan').text("Sisa Nilai Kontrak (RP) :");
            $('#view_budget_sd_keuangan').hide();
            $('#view_budget_terpakai_sd_keuangan').hide();
            $('#view_sisa_budget_sd_keuangan').hide();
            $('#view_no_kontrak_keuangan').show();
            $('.btnViewStok').hide();
        } else{
            $('#txt_budget_keuangan').text("Budgeting Bulan Ini (RP) : ");
            $('#txt_budget_terpakai_keuangan').text("Budgeting terpakai Bulan Ini (RP) : ");
            $('#txt_sisa_budget_keuangan').text("Sisa Budget Bulan Ini (RP) : ");
            $('#view_budget_sd_keuangan').show();
            $('#view_budget_terpakai_sd_keuangan').show();
            $('#view_sisa_budget_sd_keuangan').show();
            $('#view_no_kontrak_keuangan').hide();
            $('.btnViewStok').show();
        }

        $('#modal-approve-keuangan').find('.modal-title').text('Approve Pengajuan Biaya Keuangan');
        $('#modal-approve-keuangan').modal('show');
    });

    $('.pengajuanBiayaTabel').on('click', '.item-approve-keuangan-kp', function() {
        selectPembayaranKp();
        var jumlah = $(this).attr('jumlah');
        jumlah = jumlah.replace(",",".");

        var tipe= $(this).attr('tipe');

        var tipeName ="";
        if (tipe=="I"){
            tipeName="Investasi";
        } else{
            tipeName="Rutin";
        }

        $('#mod_jumlah_keuangan_kp').val(jumlah);
        $('#modPengajuanBiayaDetailIdKp').val($(this).attr('data'));
        $('#mod_status_approve_kp').val($(this).attr('status'));
        $('#mod_branch_id_keuangan_kp').val($(this).attr('unit'));
        $('#mod_divisi_id_keuangan_kp').val($(this).attr('divisi'));
        $('#mod_keterangan_keuangan_kp').val($(this).attr('keterangan'));
        $('#mod_no_budgetting_keuangan_kp').val($(this).attr('noBudgetting'));
        $('#mod_tanggal_keuangan_kp').val($(this).attr('tanggal'));
        $('#mod_budget_keuangan_kp').val($(this).attr('budget'));
        $('#mod_budget_sd_keuangan_kp').val($(this).attr('budgetsd'));
        $('#mod_budget_terpakai_keuangan_kp').val($(this).attr('budgetterpakai'));
        $('#mod_budget_terpakai_sd_keuangan_kp').val($(this).attr('budgetterpakaisd'));
        $('#mod_sisa_budget_keuangan_kp').val($(this).attr('sisabudget'));
        $('#mod_sisa_budget_sd_keuangan_kp').val($(this).attr('sisabudgetsd'));

        $('#mod_keperluan_keuangan_kp').val($(this).attr('keperluan'));
        $('#mod_no_kontrak_keuangan_kp').val($(this).attr('noKontrak'));
        $('#mod_tipe_keuangan_kp').val(tipeName);

        if (tipe=="I"){
            $('#txt_budget_keuangan_kp').text("Nilai Kontrak (RP) :");
            $('#txt_budget_terpakai_keuangan_kp').text("Nilai Kontrak Realisasi (RP) :");
            $('#txt_sisa_budget_keuangan_kp').text("Sisa Nilai Kontrak (RP) :");
            $('#view_budget_sd_keuangan_kp').hide();
            $('#view_budget_terpakai_sd_keuangan_kp').hide();
            $('#view_sisa_budget_sd_keuangan_kp').hide();
            $('#view_no_kontrak_keuangan_kp').show();
            $('.btnViewStok').hide();
        } else{
            $('#txt_budget_keuangan_kp').text("Budgeting Bulan Ini (RP) : ");
            $('#txt_budget_terpakai_keuangan_kp').text("Budgeting terpakai Bulan Ini (RP) : ");
            $('#txt_sisa_budget_keuangan_kp').text("Sisa Budget Bulan Ini (RP) : ");
            $('#view_budget_sd_keuangan_kp').show();
            $('#view_budget_terpakai_sd_keuangan_kp').show();
            $('#view_sisa_budget_sd_keuangan_kp').show();
            $('#view_no_kontrak_keuangan_kp').hide();
            $('.btnViewStok').show();
        }

        $('#modal-approve-keuangan-kp').find('.modal-title').text('Approve Pengajuan Biaya Keuangan Kantor Pusat');
        $('#modal-approve-keuangan-kp').modal('show');
    });

    $('.pengajuanBiayaTabel').on('click', '.item-terima-keuangan-kp', function() {
        selectPembayaranKp();
        $('#modPengajuanBiayaDetailIdKp').val($(this).attr('data'));
        $('#mod_status_approve_kp').val($(this).attr('status'));
        $('#mod_branch_id_keuangan_kp').val($(this).attr('unit'));
        $('#mod_divisi_id_keuangan_kp').val($(this).attr('divisi'));
        $('#mod_keterangan_keuangan_kp').val($(this).attr('keterangan'));
        $('#mod_jumlah_keuangan_kp').val($(this).attr('jumlah'));
        $('#mod_budget_keuangan_kp').val($(this).attr('budget'));
        $('#mod_no_budgetting_keuangan_kp').val($(this).attr('noBudgetting'));

        $('#modal-approve-keuangan-kp').find('.modal-title').text('Terima Uang Dari Kantor Pusat');
        $('#modal-approve-keuangan-kp').modal('show');
    });

    function selectPembayaran(){
        var option = '<option value=""></option>';
        KasirRawatJalanAction.getListPembayaran(function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option += '<option value="'+item.coa+'">'+item.pembayaranName+'</option>';
                });
                $('#mod_kas_rk').html(option);
            }else{
                $('#mod_kas_rk').html(option);
            }
        });
    }
    function selectPembayaranKp(){
        var option = '<option value=""></option>';
        KasirRawatJalanAction.getListPembayaran(function (res) {
            if(res.length > 0){
                $.each(res, function (i, item) {
                    option += '<option value="'+item.coa+'">'+item.pembayaranName+'</option>';
                });
                $('#mod_kas_keuangan_kp').html(option);
            }else{
                $('#mod_kas_keuangan_kp').html(option);
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
    $('#btnApproveKeuanganKp').click(function() {
        var id = $('#modPengajuanBiayaId').val();
        var pengajuanId = $('#modPengajuanBiayaDetailIdKp').val();
        var status = $('#mod_status_approve_kp').val();
        var approvalStatus = $('#mod_status_keuangan_kp').val();
        var branchId = $('#mod_branch_id_keuangan_kp').val();
        var divisiId = $('#mod_divisi_id_keuangan_kp').val();

        var keterangan = $('#mod_keterangan_keuangan_kp').val();
        var jumlah = $('#mod_jumlah_keuangan_kp').val();
        var noBudgetting = $('#mod_no_budgetting_keuangan_kp').val();
        if (confirm("Apakah anda ingin menyetujui pengajuan biaya ini ?")){
            PengajuanBiayaAction.saveApproveKeuanganKpPengajuan(pengajuanId,status,approvalStatus,branchId,keterangan,jumlah,noBudgetting,divisiId,function(response) {
                alert("Sukses Approve");
                loadPengajuan(id,branchId);
                $('#modal-approve-keuangan-kp').modal('hide');
            });
        }
    });
    $('#btnNotApproveKeuanganKp').click(function() {
        $('#modPengajuanBiayaDetailIdNotApprove').val($('#modPengajuanBiayaDetailIdKp').val());
        $('#modal-not-approve').modal('show');
    });
    $('#btnNotApprove').click(function() {
        var id = $('#modPengajuanBiayaId').val();
        var branchId = $('#mod_branch_id').val();
        var pengajuanId = $('#modPengajuanBiayaDetailIdNotApprove').val();
        var keterangan = $('#mod_keterangan_not_approve').val();

        if (keterangan==""){
            alert("Keterangan masih kosong");
        } else{
            if (confirm("Apakah anda tidak ingin menyetujui pengajuan ini ?")){
                PengajuanBiayaAction.saveNotApprovePengajuanBiaya(pengajuanId,keterangan,function() {
                    alert("Not Approve");
                    loadPengajuan(id,branchId);
                    $('#modal-not-approve').modal('hide');
                    $('#modal-approve-keuangan').modal('hide');
                    $('#modal-approve-atasan').modal('hide');
                    $('#modal-approve-keuangan-kp').modal('hide');
                });
            }
        }
    });

    $('#btnApproveRk').click(function() {
        selectPembayaran();
        var pengajuanId = $('#modPengajuanBiayaId').val();
        $('#modPengajuanBiayaIdRk').val(pengajuanId);
        $('#mod_branch_id_rk').val($('#mod_branch_id').val());
        $('#mod_divisi_id_rk').val($('#mod_divisi_id').val());
        $('#mod_jumlah_rk').val($('#mod_total_biaya').val());
        PengajuanBiayaAction.getKeteranganPembuatanRk(pengajuanId,"K",function(result) {
            $('#mod_keterangan_rk').val(result.keterangan);
        });
        $('#mod_status_rk').val("K");

        $('#modal-rk').find('.modal-title').text('Pengiriman RK ke unit');
        $('#modal-rk').modal('show');
    });

    $('#btnTerimaRk').click(function() {
        var pengajuanId = $('#modPengajuanBiayaId').val();
        $('#modPengajuanBiayaIdRk').val(pengajuanId);
        $('#mod_branch_id_rk').val($('#mod_branch_id').val());
        $('#mod_divisi_id_rk').val($('#mod_divisi_id').val());
        $('#mod_jumlah_rk').val($('#mod_total_biaya').val());
        PengajuanBiayaAction.getKeteranganPembuatanRk(pengajuanId,"T",function(data) {
            $('#mod_keterangan_rk').val(data.keterangan);

            var option = '<option value="'+data.coaTarget+'">'+data.namaCoa+'</option>';
            $('#mod_kas_rk').html(option);

        });
        $('#mod_status_rk').val("T");


        $('#modal-rk').find('.modal-title').text('Penerimaan RK dari Kantor Pusat');
        $('#modal-rk').modal('show');
    });

    $('#btnRk').click(function() {
        var id = $('#modPengajuanBiayaIdRk').val();
        var branchId = $('#mod_branch_id_rk').val();
        var jumlah = $('#mod_jumlah_rk').val();
        var status = $('#mod_status_rk').val();
        var coaKas = $('#mod_kas_rk').val();
        var keterangan = $('#mod_keterangan_rk').val();
        if (coaKas!=""){
            if (confirm("Apakah anda ingin membuat jurnal RK ?")){
                PengajuanBiayaAction.rkPengajuanBiaya(id,coaKas,jumlah,keterangan,branchId,status,function(result) {
                    alert(result);
                    window.location.reload();
                });
            }
        } else{
            alert("Belum memilih kas")
        }
    });

    window.loadStok =  function(namaObat){
        var tanggal = $('.mod_tanggal_class').val();
        var branchId = $('#mod_branch_id').val();
        var divisiId = $('#mod_divisi_id').val();
        $('.tabelDaftarStok').find('tbody').remove();
        $('.tabelDaftarStok').find('thead').remove();
        dwr.engine.setAsync(false);
        console.log(tanggal);
        var tmp_table = "";
        if (tanggal!=""&&branchId!=""&&divisiId!=""&&namaObat!=""){
            PengajuanBiayaAction.getStockPerDivisi(branchId,divisiId,tanggal,namaObat,function (result) {
                tmp_table = "<thead style='font-size: 12px;' ><tr class='active'>"+
                    "<th style='text-align: center; background-color:  #90ee90'>No</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>ID Obat</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Nama Barang</th>"+
                    "<th style='text-align: center; background-color:  #90ee90'>Qty</th>"+
                    "</tr></thead>";
                var i = i ;
                $.each(result, function (i, item) {
                    var saldo = item.subTotalSaldo.replace(/[,]/g,"");
                    tmp_table += '<tr style="font-size: 11px;" ">' +
                        '<td align="center">' + (i + 1) + '</td>' +
                        '<td align="center">' + item.idBarang+ '</td>' +
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

    $('.btnViewStok').click(function () {
        $("#modal-daftar-stok").modal('show');
    });
    $('#btnSearchStok').click(function () {
        var namaObat = $('#nama_obat').val();
        loadStok(namaObat);
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
</script>


