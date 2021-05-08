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
                                                        <s:if test='pengajuanBiaya.branchIdUser == "01"'>
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
                                                    <s:if test='pengajuanBiaya.branchIdUser == "01"'>
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
                                        <div style="text-align: left !important;">
                                            <div class="box-header with-border"></div>
                                            <div class="box-header with-border">
                                                <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Transaksi RK</h3>
                                            </div>
                                            <div class="box-body">
                                                <table id="tablePengajuanBiaya" class="tablePengajuanBiaya table table-bordered table-striped" style="font-size: 11px">
                                                    <thead >
                                                    <tr bgcolor="#90ee90" style="text-align: center">
                                                        <td>ID</td>
                                                        <td>Unit</td>
                                                        <td>Tanggal</td>
                                                        <td>No. Jurnal</td>
                                                        <td>Keterangan</td>
                                                        <td>Total Biaya (RP) </td>
                                                        <td>COA RK </td>
                                                        <td>COA Giro </td>
                                                        <td align="center">View</td>
                                                        <td align="center">Posting</td>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <s:iterator value="#session.listOfResult" var="row">
                                                        <tr>
                                                            <td style="text-align: center"><s:property value="pengajuanBiayaId"/></td>
                                                            <td style="text-align: center"><s:property value="branchName"/></td>
                                                            <td style="text-align: center"><s:property value="stTanggal"/></td>
                                                            <td style="text-align: center"><s:property value="noJurnal"/></td>
                                                            <td><s:property value="keterangan"/></td>
                                                            <td style="text-align: right"><s:property value="stTotalBiaya"/></td>
                                                            <td><s:property value="coaAjuanName"/></td>
                                                            <td><s:property value="coaTargetName"/></td>
                                                            <td align="center">
                                                                <a href="javascript:;" data="<s:property value="%{#attr.row.pengajuanBiayaId}"/>" class="item-view">
                                                                    <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" >
                                                                </a>
                                                            </td>
                                                            <td align="center">
                                                                <s:if test="#attr.row.flagPosting">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:if>
                                                                <s:else>
                                                                    <a href="javascript:;" data="<s:property value="%{#attr.row.pengajuanBiayaId}"/>" class="item-posting">
                                                                        <img border="0" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>" name="icon_edit" style="width: 30px">
                                                                    </a>
                                                                </s:else>
                                                                <s:if test='#row.registeredFlag == "Y"'>
                                                                    <a href="javascript:;" data="<s:property value="%{#attr.row.noJurnal}"/>" unit="<s:property value="%{#attr.row.branchId}"/>" pembayaranId="<s:property value="%{#attr.row.pembayaranUtangPiutangId}"/>" class="item-cetak-bukti">
                                                                        <img border="0" src="<s:url value="/pages/images/icons8-print-25.png"/>">
                                                                    </a>
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

<div class="modal fade" id="modal-posting-jurnal">
    <div class="modal-dialog modal-flat modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> </h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 14px">ID Transaksi RK</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_id" readonly="true" cssStyle="margin-top: 7px" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 14px">Unit</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_unit" readonly="true" cssStyle="margin-top: 7px" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 14px" >Tipe Transaksi</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_tipe_transaksi" readonly="true" cssStyle="margin-top: 7px" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 14px">Tanggal</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_tanggal" readonly="true" cssStyle="margin-top: 7px" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 14px" >No. Jurnal</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_no_jurnal" readonly="true" cssStyle="margin-top: 7px" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 14px" >COA RK</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_coa_rk" readonly="true" cssStyle="margin-top: 7px" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 14px" >COA Giro</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_coa_kas" readonly="true" cssStyle="margin-top: 7px" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 14px" >Total Biaya</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_total_biaya" readonly="true" cssStyle="margin-top: 7px;text-align: right" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 14px" >Keterangan</label>
                                <div class="col-md-6">
                                    <s:textarea id="mod_keterangan" rows="3" readonly="true" cssStyle="margin-top: 7px" cssClass="form-control" />
                                    <br>
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
        $('#tablePengajuanBiaya').DataTable({
            "pageLength": 50,
            "order": [[0, "desc"]]
        });

        $('.tablePengajuanBiaya').on('click', '.item-view', function() {
            var id = $(this).attr('data');
            $('#mod_id').val(id);
            PengajuanBiayaAction.getForModalPopUp(id,function (data) {
                $('#mod_unit').val(data.branchName);
                $('#mod_tipe_transaksi').val(data.transaksiName);
                $('#mod_tanggal').val(data.stTanggal);
                $('#mod_no_jurnal').val(data.noJurnal);
                $('#mod_coa_rk').val(data.coaAjuanName);
                $('#mod_coa_kas').val(data.coaTargetName);
                $('#mod_total_biaya').val(data.stTotalBiaya);
                $('#mod_keterangan').val(data.keterangan);

            });
            $("#btnPostingJurnal").hide();
            $("#modal-posting-jurnal").find('.modal-title').text('View Transaksi RK');
            $("#modal-posting-jurnal").modal('show');
        });

        $('.tablePengajuanBiaya').on('click', '.item-posting', function() {
            var id = $(this).attr('data');
            $('#mod_id').val(id);
            PengajuanBiayaAction.getForModalPopUp(id,function (data) {
                $('#mod_unit').val(data.branchName);
                $('#mod_tipe_transaksi').val(data.transaksiName);
                $('#mod_tanggal').val(data.stTanggal);
                $('#mod_no_jurnal').val(data.noJurnal);
                $('#mod_coa_rk').val(data.coaAjuanName);
                $('#mod_coa_kas').val(data.coaTargetName);
                $('#mod_total_biaya').val(data.stTotalBiaya);
                $('#mod_keterangan').val(data.keterangan);

            });
            $("#btnPostingJurnal").show();
            $("#modal-posting-jurnal").find('.modal-title').text('Posting Transaksi RK');
            $("#modal-posting-jurnal").modal('show');
        });
        $('#btnPostingJurnal').click(function () {
            var id =  $('#mod_id').val();
            if (confirm("apakah anda ingin memposting pengeluaran dengan pembayaran id "+id +" ?")){
                PengajuanBiayaAction.postingJurnal(id,function (listdata) {
                    alert(listdata);
                    window.location.reload();
                })
            }
        })
    });
</script>

