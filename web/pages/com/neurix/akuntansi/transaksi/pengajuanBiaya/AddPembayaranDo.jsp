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
    <style>
        *, *:before, *:after {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        .new {
            padding: 50px;
        }

        .form-check {
            display: block;
        }

        .form-check input {
            padding: 0;
            height: initial;
            width: initial;
            margin-bottom: 0;
            display: none;
            cursor: pointer;
        }

        .form-check label {
            position: relative;
            cursor: pointer;
        }

        .form-check label:before {
            content:'';
            -webkit-appearance: none;
            background-color: transparent;
            border: 2px solid #0079bf;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05);
            padding: 10px;
            display: inline-block;
            position: relative;
            vertical-align: middle;
            cursor: pointer;
            margin-right: 5px;
        }

        .form-check input:checked + label:after {
            content: '';
            display: block;
            position: absolute;
            top: 2px;
            left: 9px;
            width: 6px;
            height: 14px;
            border: solid #0079bf;
            border-width: 0 2px 2px 0;
            transform: rotate(45deg);
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
            Pengajuan Pembayaran DO Ke Kantor Pusat
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i>Add Pengajuan Pembayaran DO Ke Kantor Pusat </h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="pengajuanBiayaRkForm" method="post"  theme="simple" namespace="/pengajuanBiaya" action="searchPembayaranDo_pengajuanBiaya.action" cssClass="form-horizontal">
                                        <s:hidden name="pengajuanBiayaRk.tipe" value="add" />
                                        <table>
                                            <tr>
                                                <td width="10%" align="center">
                                                    <%@ include file="/pages/common/message.jsp" %>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Unit :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:hidden name="pengajuanBiayaRk.branchIdUser" id="branchIdUser" />
                                                        <s:if test='pengajuanBiayaRk.branchIdUser == "01"'>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranchSelainKp_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="pengajuanBiayaRk.branchId"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="pengajuanBiayaRk.branchId" disabled="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden name="pengajuanBiayaRk.branchId" id="branchId" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Master :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield  id="masterId" name="pengajuanBiayaRk.masterId" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Pengajuan Pembayaran DO ID :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield  id="pengajuanBiayaRkId" name="pengajuanBiayaRk.pengajuanBiayaRkId" cssClass="form-control"/>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>RK ID :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:textfield  id="rk" name="pengajuanBiayaRk.rkId" cssClass="form-control"/>
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
                                                        <s:textfield id="tgl1" name="pengajuanBiayaRk.stTanggalDari" cssClass="form-control pull-right"
                                                                     required="false"/>
                                                        <div class="input-group-addon">
                                                            s/d
                                                        </div>
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </div>
                                                        <s:textfield id="tgl2" name="pengajuanBiayaRk.stTanggalSelesai" cssClass="form-control pull-right"
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
                                                    <label class="control-label"><small>Status Pembayaran :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test='pengajuanBiayaRk.branchIdUser == "01"'>
                                                            <s:select list="#{'R' : 'Sudah Di RK ( Belum Dibayar )'}"
                                                                      id="statusPembayaran" name="pengajuanBiayaRk.status"
                                                                      headerKey="K" headerValue="Menunggu Kantor Pusat" cssClass="form-control" />
                                                        </s:if>
                                                        <s:else>
                                                            <s:select list="#{'B' : 'Belum Pengajuan'}"
                                                                      id="statusPembayaran" name="pengajuanBiayaRk.status"
                                                                       cssClass="form-control" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="pengajuanBiayaRkForm" id="search" name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <td>
                                                        <s:if test='pengajuanBiayaRk.branchIdUser == "01"'>
                                                            <a class="btn btn-success" id="btn_create" style="display: none" onclick="createRk()"><i class="fa fa-plus"></i>
                                                                Create RK</a>
                                                            <a class="btn btn-success" id="btn_bayar" style="display: none" onclick="bayarRk()"><i class="fa fa-plus"></i>
                                                                Bayar DO</a>
                                                        </s:if>
                                                        <s:else>
                                                            <a class="btn btn-success" id="btn_create" style="display: none" onclick="createRk()"><i class="fa fa-plus"></i>
                                                                Pengajuan Pembayaran DO</a>
                                                        </s:else>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initFormPembayaranDo_pengajuanBiaya"/>'">
                                                            <i class="fa fa-arrow-left"></i> Back
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
                                                <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Delivery Order</h3>
                                            </div>
                                            <div class="box-body">
                                                <table id="tablePengajuanBiaya" class="tablePengajuanBiaya table table-bordered table-striped" style="font-size: 11px;">
                                                    <thead>
                                                    <tr bgcolor="#90ee90">
                                                        <td align="center" width="7%">
                                                            <div class="form-check">
                                                                <input type="checkbox" id="selectall">
                                                                <label for="selectall"></label>
                                                            </div>
                                                        </td>
                                                        <td>ID</td>
                                                        <td>Unit</td>
                                                        <td>No DO</td>
                                                        <td>Jatuh Tempo</td>
                                                        <td>Vendor ID</td>
                                                        <td>Vendor</td>
                                                        <td>Jumlah (RP)</td>
                                                        <td>ID RK</td>
                                                        <td>No Jurnal</td>
                                                        <td align="center">View</td>
                                                        <td align="center">Dibayar</td>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <s:iterator value="#session.listOfResult" var="row">
                                                        <s:if test='pengajuanBiayaRk.branchIdUser == "01"'>
                                                            <td align="center">
                                                                <s:if test='#row.status == "D"'>
                                                                </s:if>
                                                                <s:else>
                                                                    <div class="form-check">
                                                                        <input type="checkbox" class="selectedId" name="selectedId" id="check_<s:property value="pengajuanBiayaRkId"/>">
                                                                        <label for="check_<s:property value="pengajuanBiayaRkId"/>"></label>
                                                                    </div>
                                                                </s:else>
                                                            </td>
                                                        </s:if>
                                                        <s:else>
                                                            <td align="center">
                                                                <s:if test='#row.pengajuanBiayaRkId != null||#row.rkId!= null'>
                                                                </s:if>
                                                                <s:else>
                                                                    <div class="form-check">
                                                                        <input type="checkbox" class="selectedId" name="selectedId" id="check_<s:property value="noTransaksi"/>">
                                                                        <label for="check_<s:property value="noTransaksi"/>"></label>
                                                                    </div>
                                                                </s:else>
                                                            </td>
                                                        </s:else>
                                                        <td><s:property value="pengajuanBiayaRkId"/></td>
                                                        <td><s:property value="branchName"/></td>
                                                        <td><s:property value="noTransaksi"/></td>
                                                        <td><s:property value="stTanggalInvoice"/></td>
                                                        <td><s:property value="masterId"/></td>
                                                        <td><s:property value="masterName"/></td>
                                                        <td style="text-align: right"><s:property value="stJumlah"/></td>
                                                        <td><s:property value="rkId"/></td>
                                                        <td><s:property value="noJurnal"/></td>
                                                        <td align="center">
                                                            <a href="javascript:;" data="<s:property value="%{#attr.row.pengajuanBiayaRkId}"/>" class="item-view">
                                                                <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" name="icon_view">
                                                            </a>
                                                        </td>
                                                        <td align="center">
                                                            <s:if test='#row.status== "D"'>
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
                    <div class="form-group view-ipa">
                        <label class="control-label col-sm-3" >Ijin Prinsip Atasan : </label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" readonly id="namaFileUpload">
                        </div>
                        <div class="col-sm-1">
                            <a href="javascript:;" id="btnViewIpaUploaded">
                                <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" name="icon_view">
                            </a>
                        </div>
                        <script>
                            $('#btnViewIpaUploaded').click(function () {
                                dwr.engine.setAsync(false);
                                var pengajuanId = $('#modPengajuanBiayaDetailIdDetail').val();
                                PengajuanBiayaAction.searchPengajuanDetailImage(pengajuanId,function(data){
                                    $("#my-image").attr("src", data);
                                });
                                $('#modal-view-ipa').modal('show');
                            })
                        </script>
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

<div class="modal fade" id="modal-view-ipa">
    <div class="modal-dialog modal-flat modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Ijin Prinsip Atasan</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <br>
                    <br>
                    <div class="row">
                        <div class="form-group">
                            <img src="" class="img-responsive" id="my-image">
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
<div class="modal fade" id="modal-create-pengajuan">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Konfirmasi
                </h4>
            </div>
            <div class="modal-body">
                <h4>Apakah anda ingin menerima pengajuan pembayaran DO dari unit dan membuat RK ?</h4>
                <input id="data_pengajuan" type="hidden">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_pengajuan"><i class="fa fa-arrow-right"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-create-pengajuan-pembayaran-do">
    <div class="modal-dialog modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Create RK</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_rk">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3" style="margin-top: 7px">Metode Pembayaran</label>
                        <div class="col-md-7">
                            <select class="form-control" id="mod_metode_bayar" style="margin-top: 7px">
                                <option value="" ></option>
                            </select>
                        </div>
                    </div>
                    <script>
                        function getCoaBayar() {
                            var option = '<option value=""></option>';
                            var tipeTransaksi = "47";
                            KodeRekeningAction.getKodeRekeningLawanByTransId(tipeTransaksi,"K",function (res) {
                                if(res.length > 0){
                                    $.each(res, function (i, item) {
                                        option += '<option value="'+item.kodeRekening+'">'+item.namaKodeRekening+'</option>';
                                    });
                                    $('#mod_metode_bayar').html(option);
                                }else{
                                    $('#mod_metode_bayar').html(option);
                                }
                            });
                        }
                        $(document).ready(function () {
                            getCoaBayar();
                        })
                    </script>
                    <input id="data_pengajuan_pembayaran_do" type="hidden">
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="btnSaveRk" onclick="confirmsSaveRk()"><i
                        class="fa fa-arrow-right"></i> Save
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-confirm-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Confirmation
                </h4>
            </div>
            <div class="modal-body">
                <h4>Do you want save this record?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-create-pengajuan-pembayaran-rk">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Konfirmasi
                </h4>
            </div>
            <div class="modal-body">
                <h4>Apakah anda ingin melakukan pengajuan pembayaran DO ini ke kantor pusat ?</h4>
                <input id="data_pengajuan_pembayaran_rk" type="hidden">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_pengajuan_pembayaran_do"><i class="fa fa-arrow-right"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-loading-dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Saving ...
                </h4>
            </div>
            <div class="modal-body">
                <div id="waiting-content" style="text-align: center">
                    <h4>Please don't close this window, server is processing your request ...</h4>
                    <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                         src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                         name="image_indicator_write">
                    <br>
                    <img class="spin" border="0"
                         style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                         src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                         name="image_indicator_write">
                </div>

                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_fin_waiting">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_fin_error_waiting"></p>
                </div>
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No--%>
                <%--</button>--%>
                <%--<button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes--%>
                <%--</button>--%>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-success-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Success
                </h4>
            </div>
            <div class="modal-body" style="text-align: center">
                <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                     name="icon_success">
                Record has been saved successfully.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-success" id="ok_con"><i class="fa fa-check"></i> Ok
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    function showDialog(tipe) {
        if (tipe == "loading"){
            $("#modal-loading-dialog").modal('show');
        }
        if (tipe == "error"){
            $("#modal-loading-dialog").modal('show');
            $("#waiting-content").hide();
            $("#warning_fin_waiting").show();
//            $("#msg_fin_error_waiting").text("Error. perbaikan");
        }
        if (tipe == "success"){
            $("#modal-loading-dialog").modal('hide');
            $("#modal-success-dialog").modal('show');
        }
    };

    $(document).ready(function () {
        $('#ok_con').click(function () {
            window.location.reload();
        });

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
                $('#namaFileUpload').val(data.fileName);
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

        $('#tablePengajuanBiaya').DataTable({
            "order": [[4, "asc"]],
            "pageLength": 100,
            "columnDefs": [
                { "orderable": false, "targets": 0 }
            ]
        });

        $('#selectall').click(function () {
            $('.selectedId').prop('checked', this.checked);
            var status = $('#statusPembayaran').val();
            var branchIdUser = $('#branchIdUser').val();
            var checkbox = document.getElementsByName('selectedId');
            var ln = 0;
            for(var i=0; i< checkbox.length; i++) {
                if(checkbox[i].checked)
                    ln++
            }
            if(ln > 0){
                if (branchIdUser!="01"){
                    $('#btn_create').show();
                    $('#btn_bayar').hide();
                } else{
                    if (status=="R"){
                        $('#btn_bayar').show();
                        $('#btn_create').hide();
                    } else if (status=="K") {
                        $('#btn_create').show();
                        $('#btn_bayar').hide();
                    }else{
                        $('#btn_create').hide();
                        $('#btn_bayar').hide();
                    }
                }
            }else{
                $('#btn_create').hide();
                $('#btn_bayar').hide();
            }
        });

        $('.selectedId').change(function () {
            var check = ($('.selectedId').filter(":checked").length == $('.selectedId').length);
            $('#selectall').prop("checked", check);
            var status = $('#statusPembayaran').val();
            var branchIdUser = $('#branchIdUser').val();

            var checkbox = document.getElementsByName('selectedId');

            var ln = 0;
            for(var i=0; i< checkbox.length; i++) {
                if(checkbox[i].checked)
                    ln++
            }
            if(ln > 0){
                if (branchIdUser!="01"){
                    $('#btn_create').show();
                    $('#btn_bayar').hide();
                } else{
                    if (status=="R"){
                        $('#btn_bayar').show();
                        $('#btn_create').hide();
                    } else if (status=="K") {
                        $('#btn_create').show();
                        $('#btn_bayar').hide();
                    }else{
                        $('#btn_create').hide();
                        $('#btn_bayar').hide();
                    }
                }
            }else{
                $('#btn_create').hide();
                $('#btn_bayar').hide();
            }
        });
    });
    function createRk() {
        var branchId = $('#branchIdUser').val();
        if (branchId!="01"){
            var data = $('#tablePengajuanBiaya').tableToJSON();
            var result = [];
            $.each(data, function (i, item) {
                var noDo = data[i]["No DO"];
                var jumlah = data[i]["Jumlah (RP)"];
                var masterId = data[i]["Vendor ID"];
                if ($('#check_' + noDo).prop("checked") == true) {
                    result.push({'noDo': noDo,'jumlah': jumlah,'masterId': masterId});
                }
            });
            var jsonString = JSON.stringify(result);
            $('#data_pengajuan_pembayaran_rk').val(jsonString);
            $('#modal-create-pengajuan-pembayaran-rk').modal('show');
        } else{
            var data = $('#tablePengajuanBiaya').tableToJSON();
            var result = [];
            $.each(data, function (i, item) {
                var id = data[i]["ID"];
                var noDo = data[i]["No DO"];
                var jumlah = data[i]["Jumlah (RP)"];
                var masterId = data[i]["Vendor ID"];
                if ($('#check_' + id).prop("checked") == true) {
                    result.push({'noDo': noDo,'jumlah': jumlah,'masterId': masterId});
                }
            });
            var jsonString = JSON.stringify(result);
            console.log(jsonString);
            $('#data_pengajuan').val(jsonString);
            $('#modal-create-pengajuan').modal('show');
        }
    }

    function bayarRk() {
        var data = $('#tablePengajuanBiaya').tableToJSON();
        var result = [];
        $.each(data, function (i, item) {
            var id = data[i]["ID"];
            var jumlah = data[i]["Jumlah (RP)"];
            var masterId = data[i]["Vendor ID"];
            var noDo = data[i]["No DO"];
            if ($('#check_' + id).prop("checked") == true) {
                result.push({'noDo': noDo,'jumlah': jumlah,'masterId': masterId});
            }
        });
        var jsonString = JSON.stringify(result);
        $('#data_pengajuan_pembayaran_do').val(jsonString);
        $('#modal-create-pengajuan-pembayaran-do').modal('show');
    }

    $('#save_pengajuan_pembayaran_do').click(function () {
        var data = $('#data_pengajuan_pembayaran_rk').val();
        var branchId = $('#branchId').val();
        var branchIdUser = $('#branchIdUser').val();
        showDialog("loading");
        dwr.engine.setAsync(true);
        PengajuanBiayaAction.kirimPengajuanPembayaranDoRk(data,branchId,branchIdUser,function() {
            dwr.engine.setAsync(false);
            showDialog("success");
        });
    });
    $('#save_pengajuan').click(function () {
        var data = $('#data_pengajuan').val();
        var branchId = $('#branchId').val();
        var branchIdUSer = $('#branchIdUser').val();
        showDialog("loading");
        dwr.engine.setAsync(true);
        PengajuanBiayaAction.terimaPengajuanPembayaranDoRk(data,branchId,branchIdUSer,function() {
            dwr.engine.setAsync(false);
            showDialog("success");
        });
    });
    function confirmsSaveRk(){
        var data = $('#data_pengajuan_pembayaran_do').val();
        var metodeBayar = $('#mod_metode_bayar').val();
        if (data != '[]' && metodeBayar != '') {
            $('#modal-confirm-dialog').modal('show');
        }else{
            alert("Metode Bayar masih kosong");
        }
    }
    $('#save_con').click(function () {
        var data = $('#data_pengajuan_pembayaran_do').val();
        var metodeBayar = $('#mod_metode_bayar').val();
        var branchIdUser = $('#branchIdUser').val();
        PengajuanBiayaAction.pembayaranDo(data,metodeBayar,branchIdUser,function(result) {
            showDialog("success");
        });
    });
</script>
