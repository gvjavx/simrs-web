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
    <script type='text/javascript' src='<s:url value="/dwr/interface/KodeRekeningAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PengajuanBiayaAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/pages/dist/js/akuntansi.js"/>'></script>
    <style>
        .modal-backdrop {
            z-index: -1;
        }
    </style>
    <style>
        .pagebanner{
            width: 100%;
            font-size: 14px;
        }
        .pagelinks{
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }
    </style>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

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
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pengajuan Biaya </h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="pengajuanBiayaForm" method="post"  theme="simple" namespace="/pengajuanBiaya" action="searchPengajuanAdmin_pengajuanBiaya.action" cssClass="form-horizontal">
                                        <s:hidden name="pengajuanBiaya.tipePembayaran" value="KK" />
                                        <table>
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
                                                        <s:if test='laporanAkuntansi.unit == "KP"'>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="pengajuanBiayaDetail.branchId"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="pengajuanBiayaDetail.branchId" disabled="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden id="branchId" name="pengajuanBiayaDetail.branchId" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Divisi :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                                                        <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="divisiIdView" name="pengajuanBiayaDetail.divisiId"
                                                                  listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Pengajuan Biaya Detail ID :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield  id="pengajuanBiayaId" name="pengajuanBiayaDetail.pengajuanBiayaDetailId" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tanggal :</small></label>
                                                </td>
                                                <td>
                                                    <div class="input-group date">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="tgl1" name="pengajuanBiayaDetail.stTanggalDari" cssClass="form-control pull-right"
                                                                     required="false"/>
                                                        <div class="input-group-addon">
                                                            s/d
                                                        </div>
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="tgl2" name="pengajuanBiayaDetail.stTanggalSelesai" cssClass="form-control pull-right"
                                                                     required="false"/>
                                                    </div>
                                                    <script>
                                                        $("#tgl1").datepicker({
                                                            setDate: new Date(),
                                                            autoclose: true,
                                                            changeMonth: true,
                                                            changeYear:true,
                                                            dateFormat:'dd-mm-yy'
                                                        });
                                                        $("#tgl1").datepicker("setDate", new Date());
                                                        $("#tgl2").datepicker({
                                                            setDate: new Date(),
                                                            autoclose: true,
                                                            changeMonth: true,
                                                            changeYear:true,
                                                            dateFormat:'dd-mm-yy'
                                                        });
                                                        $("#tgl2").datepicker("setDate", new Date());
                                                    </script>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tanggal Realisasi:</small></label>
                                                </td>
                                                <td>
                                                    <div class="input-group date">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="tglR1" name="pengajuanBiayaDetail.stTanggalDariRealisasi" cssClass="form-control pull-right"
                                                                     required="false"/>
                                                        <div class="input-group-addon">
                                                            s/d
                                                        </div>
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="tglR2" name="pengajuanBiayaDetail.stTanggalSelesaiRealisasi" cssClass="form-control pull-right"
                                                                     required="false"/>
                                                    </div>
                                                    <script>
                                                        $("#tglR1").datepicker({
                                                            autoclose: true,
                                                            changeMonth: true,
                                                            changeYear:true,
                                                            dateFormat:'dd-mm-yy'
                                                        });
                                                        $("#tglR2").datepicker({
                                                            autoclose: true,
                                                            changeMonth: true,
                                                            changeYear:true,
                                                            dateFormat:'dd-mm-yy'
                                                        });
                                                    </script>
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="pengajuanBiayaForm" id="search" name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initFormPengajuan_pengajuanBiaya"/>'">
                                                            <i class="fa fa-refresh"></i> Reset
                                                        </button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <br>
                                        <br>
                                        <div style="text-align: left !important;">
                                            <div class="box-header with-border"></div>
                                            <div class="box-header with-border">
                                                <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pengajuan Biaya</h3>
                                            </div>
                                            <div class="box-body">
                                                <table id="myTableAllRows" class="tablePengajuanBiaya table table-bordered table-striped">
                                                    <thead >
                                                    <tr bgcolor="#90ee90">
                                                        <td>ID Pengajuan Biaya</td>
                                                        <td>Nama Unit</td>
                                                        <td>Nama Divisi</td>
                                                        <td>Tanggal Pengajuan</td>
                                                        <td>Tanggal Realisasi</td>
                                                        <td>Jumlah (RP)</td>
                                                        <td>Keperluan</td>
                                                        <td>Status Saat Ini</td>
                                                        <td align="center">View</td>
                                                        <td align="center">Dibayar</td>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <s:iterator value="#session.listOfResult" var="row">
                                                        <tr>
                                                            <td><s:property value="pengajuanBiayaDetailId"/></td>
                                                            <td><s:property value="branchName"/></td>
                                                            <td><s:property value="divisiName"/></td>
                                                            <td><s:property value="stTanggal"/></td>
                                                            <td><s:property value="stTanggalRealisasi"/></td>
                                                            <td style="text-align: right"><s:property value="stJumlah"/></td>
                                                            <td><s:property value="keperluan"/></td>
                                                            <td><s:property value="statusSaatIni"/></td>
                                                            <td align="center">
                                                                <s:if test='#row.canView'>
                                                                <a href="javascript:;" data="<s:property value="%{#attr.row.pengajuanBiayaDetailId}"/>" class="item-view">
                                                                    <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" name="icon_view">
                                                                </a>
                                                                </s:if>
                                                            </td>
                                                            <td align="center">
                                                                <s:if test='#row.sudahDibayar'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_dibayar">
                                                                </s:if>
                                                            </td>
                                                        </tr>
                                                    </s:iterator>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </s:form>
                                </td>
                            </tr>
                        </table>
                    </div>
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
<div id="modal-detail" class="modal fade" role="dialog">
    <div class="modal-dialog modal-flat modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Detail Pengajuan Biaya</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="formDetail">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >ID : </label>
                        <div class="col-sm-7">
                            <input type="text" readonly class="form-control" id="modPengajuanBiayaDetailIdDetail">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit : </label>
                        <div class="col-sm-7">
                            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                            <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="mod_branch_id_detail" required="true" disabled="true"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Divisi : </label>
                        <div class="col-sm-7">
                            <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                            <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="mod_divisi_id_detail" disabled="true" name="pengajuanBiaya.divisiId" required="false" readonly="true"
                                      listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Pengajuan: </label>
                        <div class="col-sm-7">
                            <input type="text" readonly class="form-control" id="mod_tanggal_detail">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tanggal Realisasi: </label>
                        <div class="col-sm-7">
                            <input type="text" readonly class="form-control" id="mod_tanggal_realisasi_detail">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >No. Budgetting : </label>
                        <div class="col-sm-7">
                            <input type="text" readonly class="form-control" id="mod_no_budgetting_detail">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Jumlah ( RP ) : </label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="mod_jumlah_detail" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Budget RKAP ( RP ) : </label>
                        <div class="col-sm-7">
                            <input type="text" readonly class="form-control" id="mod_budget_detail">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan : </label>
                        <div class="col-sm-7">
                            <input type="text" readonly class="form-control" id="mod_keterangan_detail">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Status : </label>
                        <div class="col-sm-7">
                            <input type="text" readonly class="form-control" id="mod_status_detail">
                        </div>
                    </div>
                    <div class="form-group" id="not_approval_note_detail">
                        <label class="control-label col-sm-3" >Keterangan Not Approve : </label>
                        <div class="col-sm-7">
                            <input type="text" readonly class="form-control" id="mod_not_approval_note_detail">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('.tablePengajuanBiaya').on('click', '.item-view', function() {
            var pengajuanBiayaId = $(this).attr('data');
            PengajuanBiayaAction.getForModalPopUpDetail(pengajuanBiayaId,function (data) {
                $('#mod_jumlah_detail').val(data.stJumlah);
                $('#modPengajuanBiayaDetailIdDetail').val(data.pengajuanBiayaDetailId);
                $('#mod_branch_id_detail').val(data.branchId);
                $('#mod_divisi_id_detail').val(data.divisiId);
                $('#mod_keterangan_detail').val(data.keterangan);
                $('#mod_no_budgetting_detail').val(data.noBudgeting);
                $('#mod_tanggal_detail').val(data.stTanggal);
                $('#mod_tanggal_realisasi_detail').val(data.stTanggalRealisasi);
                $('#mod_budget_detail').val(data.stBudgetBiayaSdBulanIni);
                $('#mod_status_detail').val(data.statusSaatIni);
                if (data.notApprovalNote!="null"){
                    $('#mod_not_approval_note_detail').val(data.notApprovalNote);
                } else{
                    $('#not_approval_note_detail').hide();
                }
                $('#modal-detail').modal('show');
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
        $('.pengajuanTable').on('click', '.item-print', function() {
            var id = $(this).attr('data');
            var url = "cetakSurat_pengajuanBiaya.action?id="+id;
            window.open(url,'_blank');
        });
    });
</script>
