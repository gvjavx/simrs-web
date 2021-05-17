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
    <script type='text/javascript' src='<s:url value="/dwr/interface/KoreksiAction.js"/>'></script>
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
            window.location.href="<s:url action='initFormKoreksi_koreksi'/>";
        }

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>


<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Koreksi/Penyesuaian Kas
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Search Koreksi/Penyesuaian Kas</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="koreksiForm" method="post"  theme="simple" namespace="/koreksi" action="searchKoreksi_koreksi.action" cssClass="form-horizontal">
                                <s:hidden name="koreksi.tipeKas" value="KR" />
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Unit </label>
                                    <div class="col-sm-4">
                                        <s:if test='koreksi.branchIdUser == "KP"'>
                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="koreksi.branchId"
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control select2"/>
                                        </s:if>
                                        <s:else>
                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="koreksi.branchId" disabled="true"
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control select2"/>
                                            <s:hidden id="branchId" name="koreksi.branchId" />
                                        </s:else>

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Koreksi/Penyesuaian Kas/Bank ID</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="koreksiId" name="koreksi.koreksiId" cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">No. Jurnal</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="noJurnal" name="koreksi.noJurnal" cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tipe Transaksi</label>
                                    <div class="col-sm-4">
                                        <s:action id="comboTrans" namespace="/trans" name="initComboTransaksi_trans">
                                            <s:param name="tipe">JKR</s:param>
                                            <s:param name="isOtomatis">N</s:param>
                                        </s:action>
                                        <s:select list="#comboTrans.listOfComboTrans" id="tipe_transaksi" name="koreksi.tipeTransaksi" cssStyle="margin-top: 7px"
                                                  listKey="transId" listValue="transName" headerKey="" headerValue="[ Select One ]" cssClass="form-control select2" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Tanggal</label>
                                    <div class="col-sm-4">
                                        <div class="input-group date"  style="margin-top: 7px">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl1" name="koreksi.stTanggalDari" cssClass="form-control pull-right"
                                            />
                                            <div class="input-group-addon">
                                                s/d
                                            </div>
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="tgl2" name="koreksi.stTanggalSelesai" cssClass="form-control pull-right"
                                            />
                                        </div>
                                        <script>
                                            $('#tgl1').datepicker({
                                                dateFormat: 'dd-mm-yy'
                                            });
                                            $('#tgl2').datepicker({
                                                dateFormat: 'dd-mm-yy'
                                            });
                                        </script>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="koreksiForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>

                                        <a href="addKoreksi_koreksi.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add Koreksi Kas/Bank</a>

                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initFormKoreksi_koreksi"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-5"></label>
                                    <div class="col-sm-5" style="display: none">

                                        <sj:dialog id="waiting_dialog" openTopics="showDialogLoading"
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
                                                <img class="spin" border="0"
                                                     style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                     name="image_indicator_write">
                                            </center>
                                        </sj:dialog>
                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                                   resizable="false"
                                                   closeOnEscape="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         window.location.reload(true);
                                                                                     }
                                                                            }"
                                        >
                                            <s:hidden id="close_pos"></s:hidden>
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                                 name="icon_success">
                                            Record has been saved successfully.
                                        </sj:dialog>
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true"
                                                   resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                                         alt="Loading..."/></center>
                                        </sj:dialog>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div style="text-align: left !important;">
                        <div class="box-header with-border"></div>
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Koreksi/Penyesuaian Kas/Bank</h3>
                        </div>
                        <div class="box-body">
                            <table id="tableKoreksi" class="tableKoreksi table table-bordered table-striped" style="font-size: 11px">
                                <thead >
                                <tr bgcolor="#90ee90" style="text-align: center">
                                    <td>ID</td>
                                    <td>Unit</td>
                                    <td>No. Jurnal</td>
                                    <td>Transaksi</td>
                                    <td>Tanggal</td>
                                    <%--<td>Total Bayar (RP) </td>--%>
                                    <td align="center">View</td>
                                    <td align="center">Approval Keu.</td>
                                    <td align="center">Approval Kasub.</td>
                                    <td align="center">Posting</td>
                                    <td align="center">Bukti</td>
                                </tr>
                                </thead>
                                <tbody>
                                <s:iterator value="#session.listOfResult" var="row">
                                    <tr>
                                        <td style="text-align: center"><s:property value="koreksiId"/></td>
                                        <td style="text-align: center"><s:property value="branchName"/></td>
                                        <td style="text-align: center"><s:property value="noJurnal"/></td>
                                        <td><s:property value="stTipeTransaksi"/></td>
                                        <td style="text-align: center"><s:property value="stTanggalKoreksi"/></td>
                                        <%--<td style="text-align: right"><s:property value="stBayar"/></td>--%>
                                        <td align="center">
                                            <%--<script>
                                                console.log('<s:property value="approvalKeuanganFlag"/>');
                                            </script>--%>
                                            <a href="javascript:;" data="<s:property value="%{#attr.row.koreksiId}"/>" class="item-view">
                                                <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" >
                                            </a>
                                                <%--untuk edit--%>
                                            <s:if test='#row.approvalKeuanganFlag != "Y"'>
                                                <s:url var="urlEdit" namespace="/koreksi"
                                                       action="editKoreksi_koreksi"
                                                       escapeAmp="false">
                                                    <s:param name="koreksiId"><s:property
                                                            value="#attr.row.koreksiId"/></s:param>
                                                    <%--<s:param name="flag"><s:property
                                                            value="#attr.row.flag"/></s:param>--%>
                                                </s:url>
                                                <s:a href="%{urlEdit}">
                                                    <img border="0"
                                                         src="<s:url value="/pages/images/icons8-edit-25.png"/>" name="icon_edit">
                                                </s:a>

                                                <%--<a href="javascript:;" data="<s:property value="%{#attr.row.koreksiId}"/>" who="keu" class="item-edit">
                                                    <img border="0" src="<s:url value="/pages/images/icons8-edit-25.png"/>" >
                                                </a>--%>
                                            </s:if>
                                        </td>
                                        <td align="center">
                                            <s:if test='#row.approvalKeuanganFlag == "Y"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                                <a href="javascript:;" data="<s:property value="%{#attr.row.koreksiId}"/>" who="keu" class="item-view-approval">
                                                    <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" >
                                                </a>
                                            </s:if>
                                            <s:elseif test='#row.approvalKasubKeuanganFlag == "Y"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                            </s:elseif>
                                            <s:elseif test='#row.registeredFlag == "Y"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                            </s:elseif>
                                            <s:elseif test='#row.approvalKeuanganFlag == "N"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>">
                                            </s:elseif>
                                            <s:elseif test='#row.jabatan == "keu"'>
                                                <a href="javascript:;" data="<s:property value="%{#attr.row.koreksiId}"/>" class="item-approve-keu">
                                                    <img border="0" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>">
                                                </a>
                                            </s:elseif>
                                        </td>
                                        <td align="center">
                                            <s:if test='#row.approvalKasubKeuanganFlag == "Y"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                                <a href="javascript:;" data="<s:property value="%{#attr.row.koreksiId}"/>" who="keu" class="item-view-approval">
                                                    <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" >
                                                </a>
                                            </s:if>
                                            <s:elseif test='#row.registeredFlag == "Y"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                            </s:elseif>
                                            <s:elseif test='#row.approvalKasubKeuanganFlag == "N"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>">
                                            </s:elseif>
                                            <s:elseif test='#row.jabatan == "kasub" && #row.approvalKeuanganFlag == "Y"'>
                                                <a href="javascript:;" data="<s:property value="%{#attr.row.koreksiId}"/>" class="item-approve-kasub-keu">
                                                    <img border="0" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>">
                                                </a>
                                            </s:elseif>
                                        </td>
                                        <td align="center">
                                            <s:if test='#row.registeredFlag == "Y"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                                <a href="javascript:;" data="<s:property value="%{#attr.row.koreksiId}"/>" who="keu" class="item-view-approval">
                                                    <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" >
                                                </a>
                                            </s:if>
                                            <s:elseif test='#row.jabatan == "ka" && #row.approvalKasubKeuanganFlag == "Y"'>
                                                <a href="javascript:;" data="<s:property value="%{#attr.row.koreksiId}"/>" class="item-posting">
                                                    <img border="0" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>">
                                                </a>
                                            </s:elseif>
                                        </td>
                                        <td align="center">
                                            <s:if test='#row.registeredFlag == "Y"'>
                                                <a href="javascript:;" data="<s:property value="%{#attr.row.noJurnal}"/>" unit="<s:property value="%{#attr.row.branchId}"/>" pembayaranId="<s:property value="%{#attr.row.koreksiId}"/>" class="item-cetak-bukti">
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
    <div class="modal-dialog modal-flat" style="width: 1300px">
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
                                <label class="col-md-4" style="margin-top: 7px">Koreksi/Penyesuaian Kas/Bank Id</label>
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
                            <%--<div class="form-group">
                                <label class="col-md-4">Metode Pembayaran</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_metode_bayar" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>--%>
                            <%--<div class="form-group">
                                <label class="col-md-4">Total Bayar ( RP )</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_total_bayar" onkeypress="$(this).css('border','')" readonly="true" cssStyle="text-align: right"
                                                 cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>--%>
                           <%-- <div class="form-group">
                                <label class="col-md-4">No. Referensi</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_no_slip_bank" onkeypress="$(this).css('border','')" readonly="true"
                                                 cssClass="form-control" />
                                    <br>
                                </div>--%>
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
                <button type="button" class="btn btn-primary" id="btnLampiran"><i class="fa fa-file"></i> Lampiran</button>
                <script>
                    $('#btnLampiran').click(function () {
                        $('#modal-lampiran').modal('show');
                        loadLampiran();
                    })
                </script>

                <%--<script>
                    $('#btnLampiran').click(function () {
                        dwr.engine.setAsync(false);
                        var id = $('#mod_pembayaran_id').val();
                        var pengajuanId = $('#modPengajuanBiayaDetailIdAtasan').val();
                        KoreksiAction.searchPengajuanDetailImage(pengajuanId,function(data){
                            $("#my-image").attr("src", data);
                        });
                        $('#modal-lampiran').modal('show');
                    })
                </script>--%>


                <button type="button" class="btn btn-success" id="btnApproveKeu" data-dismiss="modal"><i class="fa fa-arrow-right"></i> Approve Keu.</button>
                <button type="button" class="btn btn-danger" id="btnNotApproveKeu" data-dismiss="modal"><i class="fa fa-close"></i> Not Approve Keu.</button>
                <button type="button" class="btn btn-success" id="btnApproveKasub" data-dismiss="modal"><i class="fa fa-arrow-right"></i> Approve Kasub.</button>
                <button type="button" class="btn btn-danger" id="btnNotApproveKasub" data-dismiss="modal"><i class="fa fa-close"></i> Not Approve Kasub.</button>
                <button type="button" class="btn btn-success" id="btnPostingJurnal" data-dismiss="modal"><i class="fa fa-arrow-right"></i> Posting</button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>
<div id="modal-view-faktur" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">View Faktur</h4>
            </div>
            <div class="modal-body">
                <img src="" class="img-responsive" id="my-image">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-lampiran">
    <div class="modal-dialog modal-flat modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Daftar Lampiran</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="col-md-offset-1 col-md-10">
                        <table style="width: 100%;"
                               class="tabelLampiran table table-bordered">
                        </table>
                    </div>
                </div>

            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div id="modal-view-lampiran" class="modal fade" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">View Lampiran</h4>
            </div>
            <div class="modal-body">
                <img src="" class="img-responsive" id="my-image2">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div id="modal-view-approval" class="modal fade" role="dialog">
    <div class="modal-dialog modal-flat modal-md">
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
                                <label class="col-md-4" style="margin-top: 7px">Approve By</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_approve_by" readonly="true" cssStyle="margin-top: 7px" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Approve Date</label>
                                <div class="col-md-6">
                                    <s:textfield id="mod_approve_date" readonly="true" cssStyle="margin-top: 7px" cssClass="form-control" />
                                    <br>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-close"></i> Close</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('.tableKoreksi').on('click', '.item-view-approval', function() {
            var pembayaranId = $(this).attr('data');
            var who = $(this).attr('who');
            var title ="View Keterangan Approval";

            KoreksiAction.getViewApproval(pembayaranId,function (data) {
                if (who=="keu"){
                    title += " Keuangan";
                    $('#mod_approve_by').val(data.approvalKeuanganName);
                    $('#mod_approve_date').val(data.stApprovalKeuanganDate);
                } else if (who=="kasubkeu"){
                    title += " Kasubdiv/Kasubid Keuangan";
                    $('#mod_approve_by').val(data.approvalKasubKeuanganName);
                    $('#mod_approve_date').val(data.stApprovalKasubKeuanganDate);
                } else{
                    title += " Kadiv/Kabid Keuangan";
                    $('#mod_approve_by').val(data.registeredWho);
                    $('#mod_approve_date').val(data.stRegisteredDate);
                }
            });
            $("#modal-view-approval").find('.modal-title').text(title);
            $("#modal-view-approval").modal('show');
        });

        $('#tableKoreksi').DataTable({
            "pageLength": 50,
            "order": [[0, "desc"]]
        });

        $('.tableKoreksi').on('click', '.item-view', function() {
            var pembayaranId = $(this).attr('data');
            $('#mod_pembayaran_id').val(pembayaranId);
            KoreksiAction.getForModalPopUp(pembayaranId,function (data) {
                $('#mod_no_jurnal').val(data.noJurnal);
                $('#mod_tipe_transaksi').val(data.stTipeTransaksi);
                $('#mod_tanggal').val(data.stTanggalKoreksi);

                $('#mod_metode_bayar').val(data.metodePembayaranName);
                $('#mod_no_slip_bank').val(data.noSlipBank);
                $('#mod_keterangan').val(data.keterangan);
                $('#mod_total_bayar').val(data.stBayar);
            });
            loadLampiran();
            loadPembayaran();
            $("#btnPostingJurnal").hide();
            $("#btnApproveKeu").hide();
            $("#btnNotApproveKeu").hide();
            $("#btnApproveKasub").hide();
            $("#btnNotApproveKasub").hide();
            $("#modal-posting-jurnal").find('.modal-title').text('View Koreksi Kas/Bank');
            $("#modal-posting-jurnal").modal('show');
        });

        $('.tableKoreksi').on('click', '.item-edit', function() {
            var pembayaranId = $(this).attr('data');
            $('#mod_pembayaran_id').val(pembayaranId);
            KoreksiAction.getForModalPopUp(pembayaranId,function (data) {
                $('#mod_no_jurnal').val(data.noJurnal);
                $('#mod_tipe_transaksi').val(data.stTipeTransaksi);
                $('#mod_tanggal').val(data.stTanggalKoreksi);

                $('#mod_metode_bayar').val(data.metodePembayaranName);
                $('#mod_no_slip_bank').val(data.noSlipBank);
                $('#mod_keterangan').val(data.keterangan);
                $('#mod_total_bayar').val(data.stBayar);
            });
            loadLampiran();
            loadPembayaran();
            $("#btnPostingJurnal").hide();
            $("#btnApproveKeu").hide();
            $("#btnNotApproveKeu").hide();
            $("#btnApproveKasub").hide();
            $("#btnNotApproveKasub").hide();
            $("#modal-posting-jurnal").find('.modal-title').text('View Koreksi Kas/Bank');
            $("#modal-posting-jurnal").modal('show');
        });



        $('.tableKoreksi').on('click', '.item-posting', function() {
            var pembayaranId = $(this).attr('data');
            $('#mod_pembayaran_id').val(pembayaranId);
            KoreksiAction.getForModalPopUp(pembayaranId,function (data) {
                $('#mod_no_jurnal').val(data.noJurnal);
                $('#mod_tipe_transaksi').val(data.stTipeTransaksi);
                $('#mod_tanggal').val(data.stTanggalKoreksi);
                $('#mod_metode_bayar').val(data.metodePembayaranName);
                $('#mod_no_slip_bank').val(data.noSlipBank);
                $('#mod_keterangan').val(data.keterangan);
                $('#mod_total_bayar').val(data.stBayar);
            });
            loadPembayaran();
            $("#modal-posting-jurnal").find('.modal-title').text('Posting Jurnal');
            $("#modal-posting-jurnal").modal('show');
            $("#btnPostingJurnal").show();
            $("#btnApproveKeu").hide();
            $("#btnNotApproveKeu").hide();
            $("#btnApproveKasub").hide();
            $("#btnNotApproveKasub").hide();
        });

        $('.tableKoreksi').on('click', '.item-approve-keu', function() {
            var pembayaranId = $(this).attr('data');
            $('#mod_pembayaran_id').val(pembayaranId);
            KoreksiAction.getForModalPopUp(pembayaranId,function (data) {
                $('#mod_no_jurnal').val(data.noJurnal);
                $('#mod_tipe_transaksi').val(data.stTipeTransaksi);
                $('#mod_tanggal').val(data.stTanggalKoreksi);
                $('#mod_metode_bayar').val(data.metodePembayaranName);
                $('#mod_no_slip_bank').val(data.noSlipBank);
                $('#mod_keterangan').val(data.keterangan);
                $('#mod_total_bayar').val(data.stBayar);
            });
            loadPembayaran();
            $("#modal-posting-jurnal").find('.modal-title').text('Approval Admin Keuangan');
            $("#modal-posting-jurnal").modal('show');
            $("#btnPostingJurnal").hide();
            $("#btnApproveKeu").show();
            $("#btnNotApproveKeu").show();
            $("#btnApproveKasub").hide();
            $("#btnNotApproveKasub").hide();
        });

        $('.tableKoreksi').on('click', '.item-approve-kasub-keu', function() {
            var pembayaranId = $(this).attr('data');
            $('#mod_pembayaran_id').val(pembayaranId);
            KoreksiAction.getForModalPopUp(pembayaranId,function (data) {
                $('#mod_no_jurnal').val(data.noJurnal);
                $('#mod_tipe_transaksi').val(data.stTipeTransaksi);
                $('#mod_tanggal').val(data.stTanggalKoreksi);
                $('#mod_metode_bayar').val(data.metodePembayaranName);
                $('#mod_no_slip_bank').val(data.noSlipBank);
                $('#mod_keterangan').val(data.keterangan);
                $('#mod_total_bayar').val(data.stBayar);
            });
            loadPembayaran();
            $("#modal-posting-jurnal").find('.modal-title').text('Approval Kasub. Keuangan');
            $("#modal-posting-jurnal").modal('show');
            $("#btnPostingJurnal").hide();
            $("#btnApproveKeu").hide();
            $("#btnNotApproveKeu").hide();
            $("#btnApproveKasub").show();
            $("#btnNotApproveKasub").show();
        });

        $('.tableKoreksi').on('click', '.item-cetak-bukti', function() {
            var noJurnal = $(this).attr('data');
            var branchId = $(this).attr('unit');
            var pembayaranId = $(this).attr('pembayaranId');
            var url = "printReportBuktiPosting_koreksi.action?koreksi.noJurnal="+noJurnal+"&koreksi.branchId="+branchId+"&koreksi.koreksiId="+pembayaranId;
            window.open(url,'_blank');
        });

        $('#btnApproveKeu').click(function () {
            var koreksiId =  $('#mod_pembayaran_id').val();
            var who = "keu";
            var flag ="Y";
            if (confirm("apakah anda ingin melakukan approve Koreksi/Penyesuaian dengan koreksi id "+koreksiId +" ?")){
                KoreksiAction.approveKoreksi(koreksiId,who,flag,function (listdata) {
                    alert(listdata);
                    window.location.reload();
                })
            }
        });

        $('#btnNotApproveKeu').click(function () {
            var pembayaranId =  $('#mod_pembayaran_id').val();
            var who = "keu";
            var flag ="N";
            if (confirm("apakah anda ingin melakukan not approve Koreksi/Penyesuaian dengan pembayaran id "+pembayaranId +" ?")){
                KoreksiAction.approveKoreksi(pembayaranId,who,flag,function (listdata) {
                    alert(listdata);
                    window.location.reload();
                })
            }
        });

        $('#btnApproveKasub').click(function () {
            var pembayaranId =  $('#mod_pembayaran_id').val();
            var who = "kasub";
            var flag ="Y";
            if (confirm("apakah anda ingin melakukan approve Koreksi/Penyesuaian dengan pembayaran id "+pembayaranId +" ?")){
                KoreksiAction.approveKoreksi(pembayaranId,who,flag,function (listdata) {
                    alert(listdata);
                    window.location.reload();
                })
            }
        });

        $('#btnNotApproveKasub').click(function () {
            var pembayaranId =  $('#mod_pembayaran_id').val();
            var who = "kasub";
            var flag ="N";
            if (confirm("apakah anda ingin melakukan not approve Koreksi/Penyesuaian dengan pembayaran id "+pembayaranId +" ?")){
                KoreksiAction.approveKoreksi(pembayaranId,who,flag,function (listdata) {
                    alert(listdata);
                    window.location.reload();
                })
            }
        });

        $('#btnPostingJurnal').click(function () {
            var pembayaranId =  $('#mod_pembayaran_id').val();
            if (confirm("apakah anda ingin memposting Koreksi/Penyesuaian dengan pembayaran id "+pembayaranId +" ?")){
                KoreksiAction.postingJurnal(pembayaranId,function (listdata) {
                    alert(listdata);
                    window.location.reload();
                })
            }
        })
    });
    window.loadPembayaran = function () {
        $('.pembayaranTable').find('tbody').remove();
        $('.pembayaranTable').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        KoreksiAction.searchPembayaranDetail(function (listdata) {
            tmp_table = "<thead style='font-size: 14px' ><tr class='active'>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196'>No</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>COA</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>No. Vendor</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Divisi</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>No. Nota</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Jumlah Debit</th>" +
                "<th style='text-align: center; color: #fff; background-color:  #30d196''>Jumlah Kredit</th>" +
                "</tr></thead>";
            var i = i;
            var totalDebit = 0;
            var totalKredit = 0;
            $.each(listdata, function (i, item) {
                console.log(item);
                var stDebit = "0";
                var stKredit = "0";
                if (item.posisiCoa=="D"){
                    stDebit = item.stJumlahPembayaran;
                    totalDebit=totalDebit+parseInt(stDebit.replace(/[,]/g,""));
                }else {
                    stKredit = item.stJumlahPembayaran;
                    totalKredit=totalKredit+parseInt(stKredit.replace(/[,]/g,""));
                }
                var kodeVendor = item.kodeVendor == null || item.kodeVendor == "" ? "" : item.kodeVendor;
                var divisi = item.divisiId != "" ? item.divisiId +" | "+item.divisiName : "";
                var coa = item.kodeCoa != "" ? item.kodeCoa +" | "+item.namaCoa : "";

                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center">' + (i + 1) + '</td>' +
                    '<td align="left">' + coa + '</td>' +
                    '<td align="center">' +  kodeVendor + '</td>' +
                    '<td align="left">' + divisi + '</td>' +
                    '<td align="center">' + item.noNota + '</td>' +
                    '<td align="center">' + stDebit+ '</td>' +
                    '<td align="center">' + stKredit+ '</td>' +
                    "</tr>";
            });
            tmp_table += '<tr style="font-size: 12px;" ">' +
                '<td align="center" colspan="5"> Jumlah </td>' +
                '<td align="center">' + formatRupiahAtas(totalDebit)+ '</td>' +
                '<td align="center">' + formatRupiahAtas(totalKredit)+ '</td>' +
                "</tr>";
            $('.pembayaranTable').append(tmp_table);
        });
    };


    $('.pembayaranTable').on('click', '.item-view-faktur', function(){
        var id = $(this).attr('data');
        var judul = $(this).attr('judul');
        dwr.engine.setAsync(false);
        $("#my-image").attr("src", id);
        $('#modal-view-faktur').find('.modal-title').text(judul);
        $('#modal-view-faktur').modal('show');
    });
    window.loadLampiran = function () {
        $('.tabelLampiran').find('tbody').remove();
        $('.tabelLampiran').find('thead').remove();
        dwr.engine.setAsync(false);
        var tmp_table = "";
        KoreksiAction.loadSessionLampiran(function (listdata) {
            tmp_table = "<thead style='font-size: 14px; color: white;' ><tr class='active'>" +
                "<th style='text-align: center; background-color:  #30d196'>No</th>" +
                "<th style='text-align: center; background-color:  #30d196'>Nama Lampiran</th>" +
                "<th style='text-align: center; background-color:  #30d196'>View</th>" +
                "</tr></thead>";
            var i = i;
            $.each(listdata, function (i, item) {
                tmp_table += '<tr style="font-size: 12px;" ">' +
                    '<td align="center" >' + (i + 1) + '</td>' +
                    '<td align="center">' + item.namaLampiran + '</td>' +
                    '<td align="center">' +
                    "<a href='javascript:;' class ='item-view-lampiran' nama ='" + item.namaLampiran + "' url ='" + item.uploadFile + "'>" +
                    "<img border='0' src='<s:url value='/pages/images/icons8-search-25.png'/>'>" +
                    '</a>' +
                    '</td>' +
                    '</tr>';
            });
            $('.tabelLampiran').append(tmp_table);
        });
    };

    $('.tabelLampiran').on('click', '.item-view-lampiran', function(){
        var judul = $(this).attr('nama');
        var url = $(this).attr('url');
        console.log(url);
        $('#modal-view-lampiran').find('.modal-title').text(judul);
        $("#my-image2").attr("src", url);
        $('#modal-view-lampiran').modal('show');
    });
</script>

