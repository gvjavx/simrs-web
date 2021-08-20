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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PendaftaranJasaRekananAction.js"/>'></script>
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
            window.location.href="<s:url action='initForm_kas'/>";
        }

        function formatRupiah(angka) {
            if(angka != ''){
                var reverse = angka.toString().split('').reverse().join(''),
                    ribuan = reverse.match(/\d{1,3}/g);
                ribuan = ribuan.join('.').split('').reverse().join('');
                return ribuan;
            }
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
            Pengeluaran Kas
        </h1>
    </section>
    <!-- Main content -->
    <s:hidden name="jenisJabatan"/> 
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Search Pengeluaran Kas</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="jasaRekananForm" method="post"  theme="simple" namespace="/jasarekanan" action="search_jasarekanan.action" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Unit </label>
                                    <div class="col-sm-4">
                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                        <s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="pendaftaranJasa.branchId" disabled="true"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control select2"/>
                                        <s:hidden id="branchId" name="pendaftaranJasa.branchId" />
                                        <s:hidden id="jenisJabatan" name="pendaftaranJasa.jenisJabatan" />
                                        <%--<s:if test='pendaftaranJasa.branchId == "01"'>--%>
                                            <%--<s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>--%>
                                            <%--<s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="pendaftaranJasa.branchId"--%>
                                                      <%--listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control select2"/>--%>
                                        <%--</s:if>--%>
                                        <%--<s:else>--%>
                                            <%--<s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>--%>
                                            <%--<s:select list="#initComboBranch.listOfComboBranch" id="branchIdView" name="kas.branchId" disabled="true"--%>
                                                      <%--listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control select2"/>--%>
                                            <%--<s:hidden id="branchId" name="pendaftaranJasa.branchId" />--%>
                                        <%--</s:else>--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">ID</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="idJasa" name="pendaftaranJasa.id" cssClass="form-control" cssStyle="margin-top: 7px"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4" style="margin-top: 7px">
                                        <s:select list="#{'Y':'Active','N':'Non Active'}" id="flag" name="pendaftaranJasa.flag" cssClass="form-control"></s:select>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">No. Jurnal</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:textfield id="noJurnal" name="kas.noJurnal" cssClass="form-control" cssStyle="margin-top: 7px"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                                <%--<div class="form-group">--%>
                                    <%--<label class="control-label col-sm-4">Tipe Transaksi</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                        <%--<s:action id="comboTrans" namespace="/trans" name="initComboTransaksi_trans">--%>
                                            <%--<s:param name="tipe">KK</s:param>--%>
                                        <%--</s:action>--%>
                                        <%--<s:select list="#comboTrans.listOfComboTrans" id="tipe_transaksi" name="kas.tipeTransaksi" cssStyle="margin-top: 7px"--%>
                                                  <%--listKey="transId" listValue="transName" headerKey="" headerValue="[ Select One ]" cssClass="form-control select2" />--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <br>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="jasaRekananForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>

                                        <%--<a href="add_kas.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add Jasa</a>--%>
                                        <s:if test='pendaftaranJasa.jenisJabatan != "kasubkeu" && pendaftaranJasa.jenisJabatan != "kakeu"'>
                                            <buttion onclick='showModalAdd()' class="btn btn-success" ><i class="fa fa-plus"></i> Add Jasa</buttion>
                                        </s:if>

                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_jasarekanan"/>'">
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
                            <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Jasa</h3>
                        </div>
                        <div class="box-body">
                            <table id="tableKas" class="tableKas table table-bordered table-striped" style="font-size: 11px">
                                <thead >
                                <tr bgcolor="#90ee90" style="text-align: center">
                                    <td>ID</td>
                                    <td>Nama Jasa</td>
                                    <td>Divisi</td>
                                    <td>Vendor</td>
                                    <td>Status</td>
                                    <td align="right">Biaya</td>
                                    <td align="center">Edit</td>
                                    <td align="center">Approval Keu.</td>
                                    <td align="center">Approval Kasub.</td>
                                    <td align="center">Posting</td>
                                    <td align="center">Bukti</td>
                                </tr>
                                </thead>
                                <tbody>
                                <s:iterator value="#session.listOfResult" var="row">
                                    <tr>
                                        <td style="text-align: center"><s:property value="id"/></td>
                                        <td style="text-align: center"><s:property value="namaJasa"/></td>
                                        <td style="text-align: center"><s:property value="namaDivisi"/></td>
                                        <td style="text-align: center"><s:property value="namaVendor"/></td>
                                        <td><s:property value="keteranganStatus"/></td>
                                        <td align="right"><script>document.write(formatRupiah('<s:property value="biaya"/>'))</script></td>
                                        <%--<td ><s:property value="stBayar"/></td>--%>
                                        <td align="center">
                                            <a href="javascript:;"  data="<s:property value="%{#attr.row.kasId}"/>" class="item-view">
                                                <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" >
                                            </a>
                                            <s:if test='#row.status == "1"'>
                                                <a href="javascript:;" src="#">
                                                    <img border="0" src="<s:url value="/pages/images/icons8-edit-25.png"/>" name="icon_edit" id="btn_edit_unit" onclick="showModalEditUnit('<s:property value="id"/>')">
                                                </a>

                                                <%--<s:url var="urlEdit" namespace="/kas" action="edit_kas"--%>
                                                       <%--escapeAmp="false">--%>
                                                    <%--<s:param name="kasId"><s:property value="#attr.row.kasId"/></s:param>--%>
                                                <%--</s:url>--%>
                                                <%--<s:a href="%{urlEdit}">--%>
                                                    <%--<img border="0"--%>
                                                         <%--src="<s:url value="/pages/images/icons8-edit-25.png"/>" name="icon_edit">--%>
                                                <%--</s:a>--%>
                                            </s:if>
                                        </td>
                                        <td align="center">
                                            <s:if test='#row.approveKeu == "Y"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                            </s:if>
                                            <s:elseif test='#row.approveKeu == "N"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>">
                                            </s:elseif>
                                            <s:elseif test='pendaftaranJasa.jenisJabatan == "keu" && #row.status == "2"'>
                                                <a href="javascript:;" class="item-approve-keu">
                                                    <img border="0" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>" onclick="showModalApprove('<s:property value="id"/>')">
                                                </a>
                                            </s:elseif>
                                        </td>
                                        <td align="center">
                                            <s:if test='#row.approveKasubKeu == "Y"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                            </s:if>
                                            <s:elseif test='#row.approveKasubKeu == "N"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>">
                                            </s:elseif>
                                            <s:elseif test='pendaftaranJasa.jenisJabatan == "kasubkeu"'>
                                                <a href="javascript:;" class="item-approve-keu">
                                                    <img border="0" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>" onclick="showModalApprove('<s:property value="id"/>')">
                                                </a>
                                            </s:elseif>
                                        </td>
                                        <td align="center">
                                            <s:if test='#row.approveKaKeu == "Y"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>">
                                            </s:if>
                                            <s:elseif test='#row.approveKaKeu == "N"'>
                                                <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>">
                                            </s:elseif>
                                            <s:elseif test='pendaftaranJasa.jenisJabatan == "kakeu"'>
                                                <a href="javascript:;" class="item-approve-keu">
                                                    <img border="0" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>" onclick="showModalApprove('<s:property value="id"/>')">
                                                </a>
                                            </s:elseif>
                                        </td>
                                        <td align="center">
                                            <s:if test='#row.noJurnal != null'>
                                                <a href="javascript:;" data="<s:property value="%{#attr.row.noJurnal}"/>" unit="<s:property value="%{#attr.row.branchId}"/>" pembayaranId="<s:property value="%{#attr.row.kasId}"/>" class="item-cetak-bukti">
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

<div class="modal fade" id="modal-add-jasa">
    <div class="modal-dialog modal-lg modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Tambah Jasa</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <div class="row">
                        <div class="col-md-12">

                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Keterangan Jasa</label>
                                <div class="col-md-6">
                                    <s:textarea id="nama-jasa-add" onkeypress="$(this).css('border','')" cssStyle="margin-top: 7px"
                                                 cssClass="form-control" />
                                    <br>
                                </div>
                            </div>

                        <!--     <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Vendor</label>
                                <div class="col-md-6">
                                      <select class="form-control sel-vendor" id="sel-vendor-add" >
                                    </select>
                                    <br>
                                </div>
                            </div> -->

                            <%--<div class="form-group">--%>
                                <%--<label class="col-md-4" style="margin-top: 7px">Pengeluaran dari Kas/Bank</label>--%>
                                <%--<div class="col-md-6">--%>
                                    <%--<select class="form-control sel-setara-kas" id="sel-setara-kas-add" >--%>
                                    <%--</select>--%>

                                    <%--&lt;%&ndash;<s:textfield id="mod_pembayaran_id" onkeypress="$(this).css('border','')"  cssStyle="margin-top: 7px"&ndash;%&gt;--%>
                                                 <%--&lt;%&ndash;cssClass="form-control" />&ndash;%&gt;--%>
                                    <%--<br>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Jenis Jasa</label>
                                <div class="col-md-6">
                                    <select class="form-control sel-beban-jasa" id="sel-beban-jasa-add" >
                                    </select>

                                    <%--<s:textfield id="mod_pembayaran_id" onkeypress="$(this).css('border','')"  cssStyle="margin-top: 7px"--%>
                                    <%--cssClass="form-control" />--%>
                                    <br>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Divisi</label>
                                <div class="col-md-6">
                                    <select class="form-control sel-divisi" id="sel-divisi-add" >
                                    </select>

                                    <%--<s:textfield id="mod_pembayaran_id" onkeypress="$(this).css('border','')"  cssStyle="margin-top: 7px"--%>
                                    <%--cssClass="form-control" />--%>
                                    <br>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Vendor</label>
                                <div class="col-md-6">
                                    <select class="form-control sel-vendor" id="sel-vendor-add" >
                                    </select>

                                    <%--<s:textfield id="mod_pembayaran_id" onkeypress="$(this).css('border','')"  cssStyle="margin-top: 7px"--%>
                                    <%--cssClass="form-control" />--%>
                                    <br>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4">Total Bayar ( RP )</label>
                                <div class="col-md-6">
                                    <s:textfield id="biaya-jasa-add" onkeypress="$(this).css('border','')" cssStyle="text-align: right"
                                                 cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-md-4">No. Referensi</label>--%>
                                <%--<div class="col-md-6">--%>
                                    <%--<s:textfield id="mod_no_slip_bank" onkeypress="$(this).css('border','')" readonly="true"--%>
                                                 <%--cssClass="form-control" />--%>
                                    <%--<br>--%>
                                <%--</div>--%>
                            <%--</div>--%>

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
                <button type="button" class="btn btn-success" id="btn-save-add" onclick="saveAdd()"><i class="fa fa-check"></i> Save</button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-edit-jasa">
    <div class="modal-dialog modal-lg modal-flat">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> Edit / Approve Jasa</h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <div class="row">
                        <div class="col-md-12">

                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Keterangan Jasa</label>
                                <div class="col-md-6">
                                    <s:textarea id="nama-jasa-edit" onkeypress="$(this).css('border','')" cssStyle="margin-top: 7px"
                                                cssClass="form-control" />
                                    <input type="hidden" id="id-jasa-edit" />
                                    <br>
                                </div>
                            </div>

                            <!--     <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Vendor</label>
                                    <div class="col-md-6">
                                          <select class="form-control sel-vendor" id="sel-vendor-add" >
                                        </select>
                                        <br>
                                    </div>
                                </div> -->

                            <%--<div class="form-group">--%>
                                <%--<label class="col-md-4" style="margin-top: 7px">Pengeluaran dari Kas/Bank</label>--%>
                                <%--<div class="col-md-6">--%>
                                    <%--<select class="form-control sel-setara-kas" id="sel-setara-kas-edit" readonly="true">--%>
                                    <%--</select>--%>

                                    <%--&lt;%&ndash;<s:textfield id="mod_pembayaran_id" onkeypress="$(this).css('border','')"  cssStyle="margin-top: 7px"&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;cssClass="form-control" />&ndash;%&gt;--%>
                                    <%--<br>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Jenis Jasa</label>
                                <div class="col-md-6">
                                    <select class="form-control sel-beban-jasa" id="sel-beban-jasa-edit" readonly="true">
                                    </select>

                                    <%--<s:textfield id="mod_pembayaran_id" onkeypress="$(this).css('border','')"  cssStyle="margin-top: 7px"--%>
                                    <%--cssClass="form-control" />--%>
                                    <br>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Divisi</label>
                                <div class="col-md-6">
                                    <select class="form-control sel-divisi" id="sel-divisi-edit" readonly="true">
                                    </select>

                                    <%--<s:textfield id="mod_pembayaran_id" onkeypress="$(this).css('border','')"  cssStyle="margin-top: 7px"--%>
                                    <%--cssClass="form-control" />--%>
                                    <br>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Vendor</label>
                                <div class="col-md-6">
                                    <select class="form-control sel-vendor" id="sel-vendor-edit" readonly="true">
                                    </select>

                                    <%--<s:textfield id="mod_pembayaran_id" onkeypress="$(this).css('border','')"  cssStyle="margin-top: 7px"--%>
                                    <%--cssClass="form-control" />--%>
                                    <br>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4">Total Bayar ( RP )</label>
                                <div class="col-md-6">
                                    <s:textfield id="biaya-jasa-edit" onkeypress="$(this).css('border','')" cssStyle="text-align: right"
                                                 cssClass="form-control"/>
                                    <br>
                                </div>
                            </div>
                            <%--<div class="form-group">--%>
                            <%--<label class="col-md-4">No. Referensi</label>--%>
                            <%--<div class="col-md-6">--%>
                            <%--<s:textfield id="mod_no_slip_bank" onkeypress="$(this).css('border','')" readonly="true"--%>
                            <%--cssClass="form-control" />--%>
                            <%--<br>--%>
                            <%--</div>--%>
                            <%--</div>--%>

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
                <button type="button" class="btn btn-success" id="btn-save-edit" onclick="saveEdit('1')"><i class="fa fa-check"></i> Save</button>
                <button type="button" class="btn btn-success" id="btn-save-approve-edit" onclick="saveEdit('2')"><i class="fa fa-check"></i> Approve</button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-posting-jurnal">
    <div class="modal-dialog modal-flat" style="width: 1300px">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-hospital-o"></i> <span id="title-approve"></span></h4>
            </div>
            <div class="modal-body">
                <div class="box">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Keterangan Jasa</label>
                                <div class="col-md-6">
                                    <s:textarea id="nama-jasa-approve" onkeypress="$(this).css('border','')" cssStyle="margin-top: 7px"
                                                cssClass="form-control" readonly="true"/>
                                    <input type="hidden" id="id-jasa-approve" />
                                    <br>
                                </div>
                            </div>


                            <s:if test='pendaftaranJasa.jenisJabatan == "keu"'>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Pengeluaran dari Kas/Bank</label>
                                    <div class="col-md-6">
                                        <select class="form-control sel-setara-kas" id="sel-setara-kas-approve">
                                        </select>
                                        <br>
                                    </div>
                                </div>
                            </s:if>
                            <s:else>
                                <div class="form-group">
                                    <label class="col-md-4" style="margin-top: 7px">Pengeluaran dari Kas/Bank</label>
                                    <div class="col-md-6">
                                        <select class="form-control sel-setara-kas" id="sel-setara-kas-approve" readonly="true">
                                        </select>
                                        <br>
                                    </div>
                                </div>
                            </s:else>


                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Jenis Jasa</label>
                                <div class="col-md-6">
                                    <select class="form-control sel-beban-jasa" id="sel-beban-jasa-approve" readonly="true">
                                    </select>
                                    <br>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Divisi</label>
                                <div class="col-md-6">
                                    <select class="form-control sel-divisi" id="sel-divisi-approve" readonly="true">
                                    </select>
                                    <br>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Vendor</label>
                                <div class="col-md-6">
                                    <select class="form-control sel-vendor" id="sel-vendor-approve" readonly="true">
                                    </select>
                                    <br>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4">Total Biaya ( RP )</label>
                                <div class="col-md-6">
                                    <s:textfield id="biaya-jasa-approve" onkeypress="$(this).css('border','')" cssStyle="text-align: right"
                                                 cssClass="form-control" onchange="hitungPPH();"/>
                                    <br>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-md-4">Jumlah yg Dibayar ( RP )</label>
                                <div class="col-md-6">
                                    <s:textfield id="dibayar-jasa-approve" onkeypress="$(this).css('border','')" cssStyle="text-align: right"
                                                 cssClass="form-control" readonly="true"/>
                                    <br>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4">PPH 21 ( RP )</label>
                                <div class="col-md-6">
                                    <s:textfield id="pph-jasa-approve" onkeypress="$(this).css('border','')" cssStyle="text-align: right"
                                                 cssClass="form-control" readonly="true"/>
                                    <br>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4" style="margin-top: 7px">Alasan Batal</label>
                                <div class="col-md-6">
                                    <s:textarea id="alasan-batal" onkeypress="$(this).css('border','')" cssStyle="margin-top: 7px"
                                                cssClass="form-control"/>
                                    <input type="hidden" id="id-jasa-approve" />
                                    <span style="color: red; font-size: 11px">* Hanya Wajib Diisi Ketika Batal</span>
                                    <br>
                                </div>
                            </div>
                            <%--<div class="form-group">--%>
                            <%--<label class="col-md-4">No. Referensi</label>--%>
                            <%--<div class="col-md-6">--%>
                            <%--<s:textfield id="mod_no_slip_bank" onkeypress="$(this).css('border','')" readonly="true"--%>
                            <%--cssClass="form-control" />--%>
                            <%--<br>--%>
                            <%--</div>--%>
                            <%--</div>--%>

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
                <%--<button type="button" class="btn btn-primary" id="btnLampiran"><i class="fa fa-file"></i> Lampiran</button>--%>
                <%--<script>--%>
                    <%--$('#btnLampiran').click(function () {--%>
                        <%--$('#modal-lampiran').modal('show');--%>
                    <%--})--%>
                <%--</script>--%>
                <button type="button" class="btn btn-success" onclick="saveApprove('Y')" id="btnApproveKeu" data-dismiss="modal"><i class="fa fa-arrow-right"></i> <span id="label-btn-approve" >Approve</span></button>
                <button type="button" class="btn btn-danger" onclick="saveApprove('N')" id="btnApproveKeu" data-dismiss="modal"><i class="fa fa-times"></i> Batal</button>
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close </button>
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

        PendaftaranJasaRekananAction.getListKodeRekeningSetaraKas(function (res) {

            var str = "";
            $.each(res, function (i, item) {
                str += "<option value='"+item.kodeRekening+"'>"+item.namaKodeRekening+"</option>";
            });

            $(".sel-setara-kas").html(str);

        });

        PendaftaranJasaRekananAction.getListKodeRekeningBebanJasa(function (res) {

            var str = "";
            $.each(res, function (i, item) {
                str += "<option value='"+item.kodeRekening+"'>"+item.namaKodeRekening+"</option>";
            });

            $(".sel-beban-jasa").html(str);

        });

        PendaftaranJasaRekananAction.getListPosition(function (res) {

            var str = "";
            $.each(res, function (i, item) {
                str += "<option value='"+item.kodering+"'>"+item.positionName+"</option>";
            });

            $(".sel-divisi").html(str);

        });

        PendaftaranJasaRekananAction.getListMaster(function (res) {

            var str = "";
            $.each(res, function (i, item) {
                str += "<option value='"+item.noMaster+"'>"+item.nama+"</option>";
            });

            $(".sel-vendor").html(str);

        });

        $('.tableKas').on('click', '.item-view-approval', function() {
            var pembayaranId = $(this).attr('data');
            var who = $(this).attr('who');
            var title ="View Keterangan Approval";

            KasAction.getViewApproval(pembayaranId,function (data) {
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

        $('#tableKas').DataTable({
            "pageLength": 50,
            "order": [[0, "desc"]]
        });

        $('.tableKas').on('click', '.item-view', function() {
            var pembayaranId = $(this).attr('data');
            $('#mod_pembayaran_id').val(pembayaranId);
            KasAction.getForModalPopUp(pembayaranId,function (data) {
                $('#mod_no_jurnal').val(data.noJurnal);
                $('#mod_tipe_transaksi').val(data.stTipeTransaksi);
                $('#mod_tanggal').val(data.stTanggal);
                $('#mod_metode_bayar').val(data.namaKas);
                $('#mod_no_slip_bank').val(data.noSlipBank);
                $('#mod_keterangan').val(data.keterangan);
                $('#mod_total_bayar').val(data.stBayar);
            });
            loadPembayaran();
            loadLampiran();
            $("#btnPostingJurnal").hide();
            $("#btnApproveKeu").hide();
            $("#btnNotApproveKeu").hide();
            $("#btnApproveKasub").hide();
            $("#btnNotApproveKasub").hide();
            $("#modal-posting-jurnal").find('.modal-title').text('View Pengeluaran Kas/Bank');
            $("#modal-posting-jurnal").modal('show');
        });

        $('.tableKas').on('click', '.item-posting', function() {
            var pembayaranId = $(this).attr('data');
            $('#mod_pembayaran_id').val(pembayaranId);
            KasAction.getForModalPopUp(pembayaranId,function (data) {
                $('#mod_no_jurnal').val(data.noJurnal);
                $('#mod_tipe_transaksi').val(data.stTipeTransaksi);
                $('#mod_tanggal').val(data.stTanggal);
                $('#mod_metode_bayar').val(data.metodePembayaranName);
                $('#mod_no_slip_bank').val(data.noSlipBank);
                $('#mod_keterangan').val(data.keterangan);
                $('#mod_total_bayar').val(data.stBayar);
            });
            loadPembayaran();
            loadLampiran();
            $("#modal-posting-jurnal").find('.modal-title').text('Posting Jurnal');
            $("#modal-posting-jurnal").modal('show');
            $("#btnPostingJurnal").show();
            $("#btnApproveKeu").hide();
            $("#btnNotApproveKeu").hide();
            $("#btnApproveKasub").hide();
            $("#btnNotApproveKasub").hide();
        });

        $('.tableKas').on('click', '.item-approve-keu', function() {
            var pembayaranId = $(this).attr('data');
            $('#mod_pembayaran_id').val(pembayaranId);
            KasAction.getForModalPopUp(pembayaranId,function (data) {
                $('#mod_no_jurnal').val(data.noJurnal);
                $('#mod_tipe_transaksi').val(data.stTipeTransaksi);
                $('#mod_tanggal').val(data.stTanggal);
                $('#mod_metode_bayar').val(data.metodePembayaranName);
                $('#mod_no_slip_bank').val(data.noSlipBank);
                $('#mod_keterangan').val(data.keterangan);
                $('#mod_total_bayar').val(data.stBayar);
            });
            loadPembayaran();
            loadLampiran();
            $("#modal-posting-jurnal").find('.modal-title').text('Approval Admin Keuangan');
            $("#modal-posting-jurnal").modal('show');
            $("#btnPostingJurnal").hide();
            $("#btnApproveKeu").show();
            $("#btnNotApproveKeu").show();
            $("#btnApproveKasub").hide();
            $("#btnNotApproveKasub").hide();
        });

        $('.tableKas').on('click', '.item-approve-kasub-keu', function() {
            var pembayaranId = $(this).attr('data');
            $('#mod_pembayaran_id').val(pembayaranId);
            KasAction.getForModalPopUp(pembayaranId,function (data) {
                $('#mod_no_jurnal').val(data.noJurnal);
                $('#mod_tipe_transaksi').val(data.stTipeTransaksi);
                $('#mod_tanggal').val(data.stTanggal);
                $('#mod_metode_bayar').val(data.metodePembayaranName);
                $('#mod_no_slip_bank').val(data.noSlipBank);
                $('#mod_keterangan').val(data.keterangan);
                $('#mod_total_bayar').val(data.stBayar);
            });
            loadPembayaran();
            loadLampiran();
            $("#modal-posting-jurnal").find('.modal-title').text('Approval Kasub. Keuangan');
            $("#modal-posting-jurnal").modal('show');
            $("#btnPostingJurnal").hide();
            $("#btnApproveKeu").hide();
            $("#btnNotApproveKeu").hide();
            $("#btnApproveKasub").show();
            $("#btnNotApproveKasub").show();
        });

        $('.tableKas').on('click', '.item-cetak-bukti', function() {
            var noJurnal = $(this).attr('data');
            var branchId = $(this).attr('unit');
            var pembayaranId = $(this).attr('pembayaranId');
            var url = "printReportBuktiPosting_kas.action?kas.noJurnal="+noJurnal+"&kas.branchId="+branchId+"&kas.kasId="+pembayaranId;
            window.open(url,'_blank');
        });

//        $('#btnApproveKeu').click(function () {
//            var pembayaranId =  $('#mod_pembayaran_id').val();
//            var who = "keu";
//            var flag ="Y";
//            if (confirm("apakah anda ingin melakukan approve pengeluaran dengan pembayaran id "+pembayaranId +" ?")){
//                KasAction.approvePembayaran(pembayaranId,who,flag,function (listdata) {
//                    alert(listdata);
//                    window.location.reload();
//                })
//            }
//        });
//
//        $('#btnNotApproveKeu').click(function () {
//            var pembayaranId =  $('#mod_pembayaran_id').val();
//            var who = "keu";
//            var flag ="N";
//            if (confirm("apakah anda ingin melakukan not approve pengeluaran dengan pembayaran id "+pembayaranId +" ?")){
//                KasAction.approvePembayaran(pembayaranId,who,flag,function (listdata) {
//                    alert(listdata);
//                    window.location.reload();
//                })
//            }
//        });
//
//        $('#btnApproveKasub').click(function () {
//            var pembayaranId =  $('#mod_pembayaran_id').val();
//            var who = "kasub";
//            var flag ="Y";
//            if (confirm("apakah anda ingin melakukan approve pengeluaran dengan pembayaran id "+pembayaranId +" ?")){
//                KasAction.approvePembayaran(pembayaranId,who,flag,function (listdata) {
//                    alert(listdata);
//                    window.location.reload();
//                })
//            }
//        });
//
//        $('#btnNotApproveKasub').click(function () {
//            var pembayaranId =  $('#mod_pembayaran_id').val();
//            var who = "kasub";
//            var flag ="N";
//            if (confirm("apakah anda ingin melakukan not approve pengeluaran dengan pembayaran id "+pembayaranId +" ?")){
//                KasAction.approvePembayaran(pembayaranId,who,flag,function (listdata) {
//                    alert(listdata);
//                    window.location.reload();
//                })
//            }
//        });
//
//        $('#btnPostingJurnal').click(function () {
//            var pembayaranId =  $('#mod_pembayaran_id').val();
//            if (confirm("apakah anda ingin memposting pengeluaran dengan pembayaran id "+pembayaranId +" ?")){
//                KasAction.postingJurnal(pembayaranId,function (listdata) {
//                    alert(listdata);
//                    window.location.reload();
//                })
//            }
//        });
    });

    function showModalAdd() {
        $("#modal-add-jasa").modal('show');
    };

    function showModalApprove(id) {

        $("#modal-posting-jurnal").modal('show');
        PendaftaranJasaRekananAction.getPendaftaranJasaFromSession(id, function(res){

            $("#id-jasa-approve").val(res.id);
            $("#nama-jasa-approve").text(res.namaJasa);
            $("#sel-setara-kas-approve").val(res.coaKas);
            $("#sel-vendor-approve").val(res.idVendor);
            $("#sel-divisi-approve").val(res.koderingDivisi);
            $("#sel-beban-jasa-approve").val(res.kodeRekeningJasa);
            $("#biaya-jasa-approve").val(res.biaya);
        });

        var jenisJabatan = '<s:property value="pendaftaranJasa.jenisJabatan"/>';

        if (jenisJabatan == "keu"){
            $("#title-approve").text("Approval Keuangan");
            $("#label-btn-approve").text("Approve");

        }
        if (jenisJabatan == "kasubkeu"){
            $("#title-approve").text("Approval Kasub Keuangan");
            $("#label-btn-approve").text("Approve");
        }
        if (jenisJabatan == "kakeu"){
            $("#title-approve").text("Approval dan Posting Kepala Keuangan");
            $("#label-btn-approve").text("Approve dan Posting");
        }

        hitungPPH();
    }

    function saveApprove(flagapprove){

        var jenisJabatan    = '<s:property value="pendaftaranJasa.jenisJabatan"/>';
        var id              = $("#id-jasa-approve").val();
        var okapprove       = false;
        var isapprove       = flagapprove == "Y";

        if (jenisJabatan == "kakeu"){
            if (isapprove){
                if (confirm("apakah anda ingin melakukan approve dan posting pengeluaran dengan pembayaran id " + id + " ? ")){
                    okapprove = true;
                }
            } else {
                if (confirm("apakah anda ingin melakukan pembatalan pengeluaran dengan pembayaran id " + id + " ? ")){
                    okapprove = true;
                }
            }
        } else {
            if (isapprove){
                if (confirm("apakah anda ingin melakukan approve pengeluaran dengan pembayaran id " + id + " ? ")){
                    okapprove = true;
                }
            } else {
                if (confirm("apakah anda ingin melakukan pembatalan pengeluaran dengan pembayaran id " + id + " ? ")){
                    okapprove = true;
                }
            }
        }

        if (okapprove){

            var obj = {
                'flagapprove' : "Y",
                'id' : id,
                'biaya' : $("#biaya-jasa-approve").val(),
                'flagapprove' : flagapprove,
                'alasanbatal' : $("#alasan-batal").val(),
                'jenisjabatan' : jenisJabatan,
                'coakas' : $("#sel-setara-kas-approve option:selected").val(),
                'jenisbayar' : "kas"
            };

            var objJson = JSON.stringify(obj);

            PendaftaranJasaRekananAction.saveApprove(objJson, function(res){

                alert(res.msg);

                if(res.status == "success"){
                    $("#jasaRekananForm").submit();
                }

            });
        }
    }

    function hitungPPH() {
        var totalBiaya = $("#biaya-jasa-approve").val();
        var pph = parseInt(totalBiaya) * 2 / 100
        var nilaiDibayar = parseInt(totalBiaya) - parseInt(pph);

        $("#dibayar-jasa-approve").val(formatRupiah(nilaiDibayar));
        $("#pph-jasa-approve").val(formatRupiah(pph));
    }

    function showModalEditUnit(id) {

        $("#modal-edit-jasa").modal('show');

        PendaftaranJasaRekananAction.getPendaftaranJasaFromSession(id, function(res){

            $("#id-jasa-edit").val(res.id);
            $("#nama-jasa-edit").text(res.namaJasa);
            $("#sel-vendor-edit").val(res.idVendor);
            $("#sel-divisi-edit").val(res.koderingDivisi);
            $("#sel-beban-jasa-edit").val(res.kodeRekeningJasa);
            $("#biaya-jasa-edit").val(res.biaya);

        });
    };

    function saveEdit(status) {

        var obj = {
            'id' : $("#id-jasa-edit").val(),
            'nama' : $("#nama-jasa-edit").text(),
            'status' : status,
            'biaya' : $("#biaya-jasa-edit").val(),
            'flag' : "Y"
        };

        var objJson = JSON.stringify(obj);

        PendaftaranJasaRekananAction.saveEdit(objJson,function (res) {

            alert(res.msg);

            if(res.status == "success"){
                $("#jasaRekananForm").submit();
            }
        });
    }

    function saveAdd(){

        var obj = {
            'nama' : $("#nama-jasa-add").val(),
            'novendor' : $("#sel-vendor-add option:selected").val(),
            'koderingjasa' : $("#sel-beban-jasa-add option:selected").val(),
            'koderingdivisi' : $("#sel-divisi-add option:selected").val(),
            'biaya' : $("#biaya-jasa-add").val()
        };

        var objJson = JSON.stringify(obj);

        PendaftaranJasaRekananAction.saveAdd(objJson,function (res) {

            alert(res.msg);

            if(res.status == "success"){
                $("#jasaRekananForm").submit();
            }
        });

    };
    
    
</script>

