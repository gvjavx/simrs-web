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
    <script type='text/javascript'>
        function cekAvailableCoa(nilai){
            var coa = nilai.value;
            var length = nilai.length;
            if (length!=0){
                dwr.engine.setAsync(false);
                KodeRekeningAction.cekAvailableCoa(coa, function(listdata) {
                    if (listdata.length==0){
                        alert("COA tidak ada");
                        $('#kodeRekening').val("");
                        $('#namaKodeRekening').val("");
                    }
                });
            }
        }
        function link(){
            window.location.href="<s:url action='initForm_pengajuanBiaya'/>";
        }

    </script>
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
                                    <s:form id="pengajuanBiayaForm" method="post"  theme="simple" namespace="/pengajuanBiaya" action="searchPengajuan_pengajuanBiaya.action" cssClass="form-horizontal">
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
                                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="pengajuanBiaya.branchId" disabled="true"
                                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        <s:hidden id="branchId" name="pengajuanBiaya.branchId" />
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
                                                        <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="divisiIdView" disabled="true" name="pengajuanBiaya.divisiId" required="false" readonly="true"
                                                                  listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                                                        <s:hidden  id="divisiId" name="pengajuanBiaya.divisiId" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Pengajuan Biaya ID :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield  id="pengajuanBiayaId" name="pengajuanBiaya.pengajuanBiayaId" cssClass="form-control"/>
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
                                                        <s:textfield id="tgl1" name="pengajuanBiaya.stTanggalDari" cssClass="form-control pull-right"
                                                                     required="false"/>
                                                        <div class="input-group-addon">
                                                            s/d
                                                        </div>
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="tgl2" name="pengajuanBiaya.stTanggalSelesai" cssClass="form-control pull-right"
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
                                                    <label class="control-label"><small>Keterangan :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield  id="keterangan" name="pengajuanBiaya.keterangan" cssClass="form-control"/>
                                                    </table>
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
                                                        <a href="addPengajuan_pengajuanBiaya.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add Pengajuan Biaya</a>
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
                                        <center>
                                            <table id="showdata" width="80%">
                                                <tr>
                                                    <td align="center">
                                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                                   height="500" width="600" autoOpen="false"
                                                                   title="Pengajuan Biaya ">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>

                                                        <s:set name="listOfPengajuanBiaya" value="#session.listOfResult" scope="request" />
                                                        <display:table name="listOfPengajuanBiaya" class="table table-condensed table-striped table-hover tablePengajuanBiaya"
                                                                       requestURI="paging_displaytag_pengajuanBiaya.action" export="true" id="row" pagesize="20" style="font-size:10">
                                                            <display:column media="html" title="View">
                                                                <a href="javascript:;" data="<s:property value="%{#attr.row.pengajuanBiayaId}"/>" class="item-view">
                                                                    <img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_view">
                                                                </a>
                                                            </display:column>
                                                            <display:column property="pengajuanBiayaId" sortable="true" title="Pengajuan Biaya ID" />
                                                            <display:column property="branchName" sortable="true" title="Nama Unit" />
                                                            <display:column property="divisiName" sortable="true" title="Nama Divisi" />
                                                            <display:column property="stTanggal" sortable="true" title="Tanggal Pengajuan" />
                                                            <display:column property="stTotalBiaya" sortable="true" title="Total Biaya (RP)" />
                                                            <display:column property="keterangan" sortable="true" title="Keterangan" />
                                                            <display:column property="statusSaatIni" sortable="true" title="Status Saat Ini" />
                                                        </display:table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </center>
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

<div class="modal fade" id="modal-view-pengajuan">
    <div class="modal-dialog modal-flat" style="width: 1200px">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> View Pengajuan Biaya</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Pengajuan Biaya ID</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_pengajuan_biaya" onkeypress="$(this).css('border','')" readonly="true" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Unit</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_branch" onkeypress="$(this).css('border','')" readonly="true" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Divisi</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_divisi" onkeypress="$(this).css('border','')" readonly="true" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">Tanggal</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_tanggal" onkeypress="$(this).css('border','')" readonly="true" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Keterangan</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_keterangan" onkeypress="$(this).css('border','')" readonly="true" cssClass="form-control"  />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">Status Saat Ini</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_status_saat_ini" onkeypress="$(this).css('border','')" readonly="true" cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">Total Biaya ( RP )</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_total" onkeypress="$(this).css('border','')" readonly="true" cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <br>
                            <div class="form-group">
                                <table style="width: 100%;"
                                       class="pengajuanTable table table-bordered">
                                </table>
                            </div>
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

<script>
    window.loadDetailPengajuan = function () {
        $('.pengajuanTable').find('tbody').remove();
        $('.pengajuanTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PengajuanBiayaAction.detailPengajuanBiayaSession(function (listdata) {
            tmp_table = "<thead style='font-size: 12px' ><tr class='active'>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196'>No</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>ID</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>No. Budgeting</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Tanggal</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Tipe Transaksi</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Keperluan</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Jumlah ( RP )</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Budgeting ( RP )</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Keterangan</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Status</th>" +
                "</tr></thead>";
            var i = i;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 11px;" ">' +
                    '<td align="center">' + (i + 1) + '</td>' +
                    '<td align="center">' + item.pengajuanBiayaDetailId + '</td>' +
                    '<td align="center">' + item.noBudgeting + '</td>' +
                    '<td align="center">' + item.stTanggal + '</td>' +
                    '<td align="center">' + item.transaksiName + '</td>' +
                    '<td align="center">' + item.keperluanName + '</td>' +
                    '<td align="center">' + item.stJumlah + '</td>' +
                    '<td align="center">' + item.stBudgetBiaya + '</td>' +
                    '<td align="center">' + item.keterangan + '</td>' +
                    '<td align="center">' + item.statusSaatIni + '</td>' +
                    "</tr>";
            });
            $('.pengajuanTable').append(tmp_table);
        });
    };

    $(document).ready(function () {
        $('.tablePengajuanBiaya').on('click', '.item-view', function() {
            var pengajuanBiayaId = $(this).attr('data');
            $('#mod_pengajuan_biaya').val(pengajuanBiayaId);
            PengajuanBiayaAction.getForModalPopUp(pengajuanBiayaId,function (data) {
                $('#mod_branch').val(data.branchName);
                $('#mod_divisi').val(data.divisiName);
                $('#mod_tanggal').val(data.stTanggal);
                $('#mod_keterangan').val(data.keterangan);
                $('#mod_status_saat_ini').val(data.statusSaatIni);
                $('#mod_total').val(data.stTotalBiaya);
            });
            loadDetailPengajuan();
            $("#modal-view-pengajuan").modal('show');
        });
    });
</script>
