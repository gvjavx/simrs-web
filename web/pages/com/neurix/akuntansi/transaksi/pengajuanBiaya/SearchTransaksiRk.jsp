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
            Transaksi RK
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Transaksi RK </h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="pengajuanBiayaForm" method="post"  theme="simple" namespace="/pengajuanBiaya" action="search_pengajuanBiaya.action" cssClass="form-horizontal">
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
                                                        <s:if test='pengajuanBiaya.branchId == "KP"'>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranchSelainKp_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="pengajuanBiaya.branchId"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="pengajuanBiaya.branchId" disabled="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden id="branchId" name="pengajuanBiaya.branchId" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tipe Transaksi :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'SMK':'Setoran Modal Kerja ke Unit'}"
                                                                  id="transaksi" name="pengajuanBiaya.transaksi"
                                                                  headerKey="PDU" headerValue="Swift Kas Unit ke Pusat" cssClass="form-control" />
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Transaksi RK ID :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield  id="pengajuanBiayaId" name="pengajuanBiaya.pengajuanBiayaId" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Nomor Jurnal :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield  id="nomorJurnal" name="pengajuanBiaya.noJurnal" cssClass="form-control"/>
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
                                                        $('#tgl1').datepicker({
                                                            dateFormat: 'dd-mm-yy'
                                                        });
                                                        $('#tgl2').datepicker({
                                                            dateFormat: 'dd-mm-yy'
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
                                                    <s:if test='pengajuanBiaya.branchId == "KP"'>
                                                    <td>
                                                        <a href="add_pengajuanBiaya.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add Transaksi RK</a>
                                                    </td>
                                                    </s:if>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_pengajuanBiaya"/>'">
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
                                                                   title="Transaksi RK ">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>

                                                        <s:set name="listOfPengajuanBiaya" value="#session.listOfResult" scope="request" />
                                                        <display:table name="listOfPengajuanBiaya" class="table table-condensed table-striped table-hover tablePengajuanBiaya"
                                                                       requestURI="paging_displaytag_pengajuanBiaya.action" export="true" id="row" pagesize="20" style="font-size:10">
                                                            <%--<display:column media="html" title="View">--%>
                                                                <%--<a href="javascript:;" data="<s:property value="%{#attr.row.pengajuanBiayaId}"/>" class="item-view">--%>
                                                                    <%--<img border="0" src="<s:url value="/pages/images/view.png"/>" name="icon_view">--%>
                                                                <%--</a>--%>
                                                            <%--</display:column>--%>
                                                            <display:column property="pengajuanBiayaId" sortable="true" title="Transaksi RK ID" />
                                                            <display:column property="tanggal" sortable="true" title="Tanggal" />
                                                            <display:column property="noJurnal" sortable="true" title="No. Jurnal" />
                                                            <display:column property="keterangan" sortable="true" title="Keterangan" />
                                                            <display:column property="stTotalBiaya" sortable="true" title="Total Biaya" />
                                                            <display:column property="coaAjuanName" sortable="true" title="Nama RK" />
                                                            <display:column property="coaTargetName" sortable="true" title="Nama Rekening Kas" />
                                                            <display:column media="html" title="Posting"  style="text-align:center">
                                                                <s:if test="#attr.row.flagPosting">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:if>
                                                                <s:else>
                                                                    <a href="javascript:;" data="<s:property value="%{#attr.row.pengajuanBiayaId}"/>" class="item-posting">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_closed.ico"/>" name="icon_edit" style="width: 30px">
                                                                    </a>
                                                                </s:else>
                                                            </display:column>
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

<div class="modal fade" id="modal-posting-jurnal">
    <div class="modal-dialog modal-flat modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Posting Jurnal</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Pengeluaran Kas/Bank Id</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_pembayaran_id" onkeypress="$(this).css('border','')" readonly="true" cssStyle="margin-top: 7px"
                                                 cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >No. Jurnal</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_no_jurnal" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">Tipe Transaksi</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_tipe_transaksi" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" >Tanggal</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_tanggal" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control"  />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">Metode Pembayaran</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_metode_bayar" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">Total Bayar</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_total_bayar" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">No. Referensi</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_no_slip_bank" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4">Keterangan</label>
                                <div class="col-md-6">
                                    <s:textarea id="mod_keterangan" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <br>
                            <div class="form-group">
                                <div class="col-md-offset-1 col-md-10">
                                    <table style="width: 100%;"
                                           class="pembayaranTable table table-bordered">
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-success" id="btnPostingJurnal" data-dismiss="modal"><i class="fa fa-arrow-right"></i> Posting</button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('.tablePengajuanBiaya').on('click', '.item-view', function() {
            var pembayaranId = $(this).attr('data');
            $('#mod_pembayaran_id').val(pembayaranId);
            PengajuanBiayaAction.getForModalPopUp(pembayaranId,function (data) {
                $('#mod_no_jurnal').val(data.noJurnal);
                $('#mod_tipe_transaksi').val(data.stTipeTransaksi);
                $('#mod_tanggal').val(data.stTanggal);
                $('#mod_metode_bayar').val(data.metodePembayaranName);
                $('#mod_no_slip_bank').val(data.noSlipBank);
                $('#mod_keterangan').val(data.keterangan);
                $('#mod_total_bayar').val(data.stBayar);
            });
            loadPembayaran();
            $("#btnPostingJurnal").hide();
            $("#modal-posting-jurnal").find('.modal-title').text('View Pembayaran Hutang Piutang');
            $("#modal-posting-jurnal").modal('show');
        });

        $('.tablePengajuanBiaya').on('click', '.item-posting', function() {
            var pembayaranId = $(this).attr('data');
            if (confirm("apakah anda ingin memposting pengeluaran dengan pembayaran id "+pembayaranId +" ?")){
                PengajuanBiayaAction.postingJurnal(pembayaranId,function (listdata) {
                    alert(listdata);
                    window.location.reload();
                })
            }
        });
        $('.tablePengajuanBiaya').on('click', '.item-cetak-bukti', function() {
            var noJurnal = $(this).attr('data');
            var branchId = $(this).attr('unit');
            var pembayaranId = $(this).attr('pembayaranId');
            var url = "printReportBuktiPosting_pengajuanBiaya.action?pengajuanBiaya.noJurnal="+noJurnal+"&pengajuanBiaya.branchId="+branchId+"&pengajuanBiaya.pengajuanBiayaId="+pembayaranId;
            window.open(url,'_blank');
        });
        $('#btnPostingJurnal').click(function () {
            var pembayaranId =  $('#mod_pembayaran_id').val();
            if (confirm("apakah anda ingin memposting pengeluaran dengan pembayaran id "+pembayaranId +" ?")){
                PengajuanBiayaAction.postingJurnal(pembayaranId,function (listdata) {
                    alert(listdata);
                    window.location.reload();
                })
            }
        })
    });
</script>
<script>
    window.loadPembayaran = function () {
        $('.pembayaranTable').find('tbody').remove();
        $('.pembayaranTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        PengajuanBiayaAction.searchPembayaranDetail(function (listdata) {
            tmp_table = "<thead style='font-size: 14px' ><tr class='active'>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196'>No</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Detail ID</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>No. Vendor</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Divisi ID</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>No. Nota</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Jml Pembayaran</th>" +
                "</tr></thead>";
            var i = i;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center">' + (i + 1) + '</td>' +
                    '<td align="center">' + item.pengajuanBiayaDetailId + '</td>' +
                    '<td align="center">' + item.masterId + '</td>' +
                    '<td align="center">' + item.divisiId + '</td>' +
                    '<td align="center">' + item.noNota + '</td>' +
                    '<td align="center">' + item.stJumlahPembayaran + '</td>' +
                    "</tr>";
            });
            $('.pembayaranTable').append(tmp_table);
        });
    };
</script>

