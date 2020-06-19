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
                                        <div style="text-align: left !important;">
                                            <div class="box-header with-border"></div>
                                            <div class="box-header with-border">
                                                <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Pengajuan Biaya</h3>
                                            </div>
                                            <div class="box-body">
                                                <table id="myTable" class="tablePengajuanBiaya table table-bordered table-striped">
                                                    <thead >
                                                    <tr bgcolor="#90ee90">
                                                        <td>ID Pengajuan Biaya</td>
                                                        <td>Nama Unit</td>
                                                        <td>Nama Divisi</td>
                                                        <td>Tanggal Pengajuan</td>
                                                        <td>Total Biaya (RP)</td>
                                                        <td>Keterangan</td>
                                                        <td>Status Saat Ini</td>
                                                        <td align="center">View</td>
                                                        <td align="center">Batal</td>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <s:iterator value="#session.listOfResult" var="row">
                                                        <tr>
                                                            <td><s:property value="pengajuanBiayaId"/></td>
                                                            <td><s:property value="branchName"/></td>
                                                            <td><s:property value="divisiName"/></td>
                                                            <td><s:property value="stTanggal"/></td>
                                                            <td style="text-align: right"><s:property value="stTotalBiaya"/></td>
                                                            <td><s:property value="keterangan"/></td>
                                                            <td><s:property value="statusSaatIni"/></td>
                                                            <td align="center">
                                                                <a href="javascript:;" data="<s:property value="%{#attr.row.pengajuanBiayaId}"/>" class="item-view">
                                                                    <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" name="icon_view">
                                                                </a>
                                                            </td>
                                                            <td align="center">
                                                                <s:if test='#row.aprovalFlag == null && #row.flagBatal == "N"'>
                                                                    <a href="javascript:;" data="<s:property value="%{#attr.row.pengajuanBiayaId}"/>" class="item-batal">
                                                                        <img border="0" src="<s:url value="/pages/images/icons8-trash-can-25.png"/>" name="icon_batal">
                                                                    </a>
                                                                </s:if>
                                                                <s:elseif test='#row.flagBatal == "Y"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_batal">
                                                                </s:elseif>
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
                            <div class="form-group" id="mod_tanggal_realisasi_view">
                                <label class="col-md-4">Tanggal Realisasi</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_tanggal_realisasi" onkeypress="$(this).css('border','')" readonly="true" cssClass="form-control" />
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
                            <div class="form-group" id="mod_keterangan_batal_view_grp">
                                <label class="col-md-4">Keterangan Batal</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_keterangan_batal_view" onkeypress="$(this).css('border','')" readonly="true" cssClass="form-control"/>
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
                <a id="btnBatalPengajuan" type="button" class="btn btn-default btn-danger"><i class="fa fa-trash"></i> Batalkan</a>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>

<div id="modal-batal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-flat modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Batalkan Pengajuan Biaya</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="formBatal">
                    <div class="form-group">
                        <label class="control-label col-sm-3" >ID : </label>
                        <div class="col-sm-8">
                            <input type="text" readonly class="form-control" id="modPengajuanBiayaIdBatal">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >Keterangan : </label>
                        <div class="col-sm-8">
                            <textarea class="form-control" id="mod_keterangan_batal" rows="3"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <a id="btnBatal" type="button" class="btn btn-default btn-danger"><i class="fa fa-close"></i> Batalkan</a>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>
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
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Tipe Budget</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Keperluan</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Jumlah ( RP )</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Status</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Detail</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Print</th>" +
                "</tr></thead>";
            var i = i;
            $.each(listdata, function (i, item) {
                var view ='<td align="center"><a href="javascript:;" data="'+item.pengajuanBiayaDetailId+'"  status="'+item.statusApproval+'" unit="'+item.branchId+'" divisi="'+item.divisiId+'"  keterangan="'+item.keterangan+'"  jumlah="'+item.stJumlah+'" budget="'+item.stBudgetBiaya+'" noBudgetting="'+item.noBudgeting+'" notApprovalNote="'+item.notApprovalNote+'" statusSaatIni="'+item.statusSaatIni+'" tanggal="'+item.stTanggal+'" tanggalRealisasi="'+item.stTanggalRealisasi+'" class="item-detail" >\n' +
                    '<img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" name="icon_edit">\n' +
                    '</a></td>';
                var print ='<td></td>';
                if (item.canPrint){
                    print ='<td align="center"><a href="javascript:;" data="'+item.pengajuanBiayaDetailId+'" class="item-print" >\n' +
                        '<img border="0" src="<s:url value="/pages/images/icons8-print-25.png"/>" name="icon_edit">\n' +
                        '</a></td>';
                }
                tmp_table += '<tr style="font-size: 11px;" ">' +
                    '<td align="center">' + (i + 1) + '</td>' +
                    '<td align="center">' + item.pengajuanBiayaDetailId + '</td>' +
                    '<td align="center">' + item.noBudgeting + '</td>' +
                    '<td align="center">' + item.stTanggal + '</td>' +
                    '<td align="center">' + item.transaksiName + '</td>' +
                    '<td align="center">' + item.keperluanName + '</td>' +
                    '<td align="center">' + item.stJumlah + '</td>' +
                    '<td align="center">' + item.statusSaatIni + '</td>' +
                        view+
                        print+
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
                if (data.stTanggalRealisasi!=null){
                    $('#mod_tanggal_realisasi').val(data.stTanggalRealisasi);
                } else{
                    $('#mod_tanggal_realisasi_view').hide();
                }
                if (data.flagBatal=="Y"){
                    $('#mod_keterangan_batal_view').val(data.keteranganBatal);
                } else{
                    $('#mod_keterangan_batal_view_grp').hide();
                }
            });
            loadDetailPengajuan();
            $('#btnBatalPengajuan').hide();
            $("#modal-view-pengajuan").modal('show');
        });
        $('.tablePengajuanBiaya').on('click', '.item-batal', function() {
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
            $('#btnBatalPengajuan').show();
            $("#modal-view-pengajuan").modal('show');
        });
        $('#btnBatalPengajuan').click(function() {
            $('#modPengajuanBiayaIdBatal').val($('#mod_pengajuan_biaya').val());
            $('#modal-batal').modal('show');
        });
        $('#btnBatal').click(function() {
            var pengajuanId = $('#modPengajuanBiayaIdBatal').val();
            var keterangan = $('#mod_keterangan_batal').val();

            if (keterangan==""){
                alert("Keterangan masih kosong");
            } else{
                if (confirm("Apakah anda ingin membatalkan pengajuan ini ?")){
                    PengajuanBiayaAction.batalkanPengajuanBiaya(pengajuanId,keterangan,function(result) {
                        if (result==""){
                            alert("Dibatalkan");
                            window.location.reload();
                        } else{
                            alert(result)
                        }
                    });
                }
            }
        });
        $('.pengajuanTable').on('click', '.item-detail', function() {
            var jumlah = $(this).attr('jumlah');
            jumlah = jumlah.replace(",",".");
            $('#mod_jumlah_detail').val(jumlah);
            $('#modPengajuanBiayaDetailIdDetail').val($(this).attr('data'));
            $('#mod_status_approve_detail').val($(this).attr('status'));
            $('#mod_branch_id_detail').val($(this).attr('unit'));
            $('#mod_divisi_id_detail').val($(this).attr('divisi'));
            $('#mod_keterangan_detail').val($(this).attr('keterangan'));
            $('#mod_no_budgetting_detail').val($(this).attr('noBudgetting'));
            $('#mod_tanggal_detail').val($(this).attr('tanggal'));
            $('#mod_tanggal_realisasi_detail').val($(this).attr('tanggalRealisasi'));
            $('#mod_budget_detail').val($(this).attr('budget'));
            $('#mod_status_detail').val($(this).attr('statusSaatIni'));
            if ($(this).attr('notApprovalNote')!="null"){
                $('#mod_not_approval_note_detail').val($(this).attr('notApprovalNote'));
            } else{
                $('#not_approval_note_detail').hide();
            }

            $('#modal-detail').modal('show');

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
