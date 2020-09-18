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
    <script type='text/javascript' src='<s:url value="/dwr/interface/PayrollAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <style>
        .checkApprove {width: 20px; height: 20px;}
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

        function link(){
            window.location.href="<s:url action='initForm_alat'/>";
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
            Payroll
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i>Pencarian Payroll</h3>
                    </div>
                    <div class="box-body">
                        <table width="100%" align="center">
                            <tr>
                                <td align="center">
                                    <s:form id="payrollForm" method="post"  theme="simple" namespace="/payroll" action="search_payroll.action" cssClass="form-horizontal">
                                        <s:hidden name="addOrEdit"/>
                                        <s:hidden name="delete"/>
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
                                                    <label class="control-label"><small>Periode :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                                                  id="bulanPayroll1" name="payroll.bulan"
                                                                  headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                                    </table>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>
                                                        <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="tahunPayroll1"
                                                                  name="payroll.tahun" required="true" headerKey=""
                                                                  headerValue="[Select one]"/>
                                                    </table>
                                                </td>

                                                <td>
                                                    <table>
                                                        <h4>s/d </h4>
                                                    </table>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'01':'Januari', '02' : 'Februari', '03':'Maret', '04':'April', '05':'Mei', '06':'Juni', '07':'Juli',
                                '08': 'Agustus', '09' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}"
                                                                  id="bulanPayroll2" name="payroll.bulan1"
                                                                  headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                                    </table>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>
                                                        <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="tahunPayroll2"
                                                                  name="payroll.tahun1" required="true" headerKey=""
                                                                  headerValue="[Select one]"/>
                                                    </table>
                                                </td>
                                                <script>
                                                    var dt = new Date();
                                                    $('#bulanPayroll1').val("01");
                                                    $('#tahunPayroll1').val(dt.getFullYear());
                                                    $('#bulanPayroll2').val(("0" + (dt.getMonth() + 1)).slice(-2));
                                                    $('#tahunPayroll2').val(dt.getFullYear());
                                                </script>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Unit :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:if test="%{payroll.kantorPusat}">
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="payroll.branchId"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="unitTmp" name="payroll.branchId" disabled="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden id="branchId" name="payroll.branchId" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tipe :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'PR':'Payroll', 'T':'THR', 'CP':'Cuti Panjang', 'CT':'Cuti Tahunan',
                                        'JP':'Jasa Operassional', 'JB':'PMP', 'PN':'SHT', 'IN':'Insentif'}" id="tipe" name="payroll.tipe"
                                                                  cssClass="form-control" />
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <br>
                                        <div id="actions" class="form-actions">
                                            <table align="center">
                                                <tr>
                                                    <td>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="payrollForm" id="search" name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog" >
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <s:if test="%{payroll.sdm}">
                                                    <td>
                                                        <a href="add_payroll.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add Payroll</a>
                                                    </td>
                                                    </s:if>
                                                    <td>
                                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_payroll"/>'">
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
                                                                   title="Payroll ">
                                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>

                                                        <s:set name="listOfPayroll" value="#session.listOfResult" scope="request" />
                                                        <display:table name="listOfPayroll" class=" tablePayroll table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_payroll.action" export="true" id="row" pagesize="24" style="font-size:10">
                                                            <display:column media="html" title="Approve Keuangan Unit">
                                                                <s:if test='#attr.row.statusApprove=="RK"'>
                                                                    <a href="javascript:;" bulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                       tahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                       branchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                       branchName="<s:property value="%{#attr.row.branchName}"/>"
                                                                       tipe="<s:property value="%{#attr.row.tipe}"/>"
                                                                       class="item-approve-keu-unit">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>">
                                                                    </a>
                                                                </s:if>
                                                                <s:elseif test='#attr.row.statusApprove=="D"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:elseif>
                                                            </display:column>
                                                            <display:column media="html" title="Approve Keuangan Kanpus">
                                                                <s:if test='#attr.row.statusApprove=="K"'>
                                                                    <s:if test="%{payroll.kantorPusat}">
                                                                        <s:if test="%{payroll.keuanganKantorPusat}">
                                                                            <a href="javascript:;" bulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                               tahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                               branchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                               branchName="<s:property value="%{#attr.row.branchName}"/>"
                                                                               tipe="<s:property value="%{#attr.row.tipe}"/>"
                                                                               class="item-edit">
                                                                                <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                                            </a>
                                                                        </s:if>
                                                                    </s:if>
                                                                </s:if>
                                                                <s:elseif test='#attr.row.statusApprove=="D"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:elseif>
                                                                <s:elseif test='#attr.row.approvalAksFlag=="Y"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:elseif>
                                                            </display:column>
                                                            <display:column media="html" title="Approve SDM KP">
                                                                <s:if test='#attr.row.statusApprove=="S"'>
                                                                    <s:if test="%{payroll.kantorPusat}">
                                                                        <s:if test="%{payroll.sdm}">
                                                                            <a href="javascript:;" bulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                               tahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                               branchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                               branchName="<s:property value="%{#attr.row.branchName}"/>"
                                                                               tipe="<s:property value="%{#attr.row.tipe}"/>"
                                                                               class="item-approve-sdm">
                                                                                <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                                            </a>
                                                                        </s:if>
                                                                    </s:if>
                                                                </s:if>
                                                                <s:elseif test='#attr.row.statusApprove=="K"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:elseif>
                                                                <s:elseif test='#attr.row.statusApprove=="D"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:elseif>
                                                                <s:elseif test='#attr.row.approvalAksFlag=="Y"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:elseif>
                                                            </display:column>
                                                            <display:column media="html" title="Approve SDM Unit">
                                                                <s:if test="%{payroll.kantorPusat}">
                                                                    <s:if test='#attr.row.statusApprove=="K"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:if>
                                                                    <s:elseif test='#attr.row.statusApprove=="S"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                    <s:elseif test='#attr.row.statusApprove=="D"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                    <s:elseif test='#attr.row.approvalAksFlag=="Y"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:if test='#attr.row.approvalFlag=="N"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                                    </s:if>
                                                                    <s:elseif test='#attr.row.statusApprove=="U"'>
                                                                        <a href="javascript:;" bulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                           tahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                           branchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                           branchName="<s:property value="%{#attr.row.branchName}"/>"
                                                                           tipe="<s:property value="%{#attr.row.tipe}"/>"
                                                                           class="item-approve-unit">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                                        </a>
                                                                    </s:elseif>
                                                                    <s:elseif test='#attr.row.statusApprove=="K"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                    <s:elseif test='#attr.row.statusApprove=="S"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                    <s:elseif test='#attr.row.statusApprove=="D"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                    <s:elseif test='#attr.row.approvalAksFlag=="Y"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                </s:else>
                                                            </display:column>

                                                            <display:column media="html" title="View">
                                                                <s:url var="urlEdit" namespace="/payroll" action="view_payroll" escapeAmp="false">
                                                                    <s:param name="bulan"><s:property value="#attr.row.bulan"/></s:param>
                                                                    <s:param name="tahun"><s:property value="#attr.row.tahun"/></s:param>
                                                                    <s:param name="branchId"><s:property value="#attr.row.branchId"/></s:param>
                                                                    <s:param name="tipe"><s:property value="#attr.row.tipe"/></s:param>
                                                                </s:url>
                                                                <s:if test='#attr.row.statusApprove=="K"'>
                                                                    <s:a href="%{urlEdit}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" >
                                                                    </s:a>
                                                                </s:if>
                                                                <s:elseif test='#attr.row.statusApprove=="D"'>
                                                                    <s:a href="%{urlEdit}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" >
                                                                    </s:a>
                                                                </s:elseif>
                                                                <s:elseif test='#attr.row.statusApprove=="S"'>
                                                                    <s:a href="%{urlEdit}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" >
                                                                    </s:a>
                                                                </s:elseif>
                                                            </display:column>
                                                            <display:column media="html" title="Edit">
                                                                <s:url var="urlEdit" namespace="/payroll" action="edit_payroll" escapeAmp="false">
                                                                    <s:param name="bulan"><s:property value="#attr.row.bulan"/></s:param>
                                                                    <s:param name="tahun"><s:property value="#attr.row.tahun"/></s:param>
                                                                    <s:param name="branchId"><s:property value="#attr.row.branchId"/></s:param>
                                                                    <s:param name="tipe"><s:property value="#attr.row.tipe"/></s:param>
                                                                </s:url>
                                                                <s:if test='#attr.row.statusApprove=="U"'>
                                                                    <s:a href="%{urlEdit}" id="hrefEdit">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                    </s:a>
                                                                </s:if>
                                                                <s:elseif test='#attr.row.statusApprove=="S"'>
                                                                    <s:if test="%{payroll.kantorPusat}">
                                                                        <s:if test="%{payroll.sdm}">
                                                                            <s:a href="%{urlEdit}" id="hrefEdit">
                                                                                <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                            </s:a>
                                                                        </s:if>
                                                                    </s:if>
                                                                </s:elseif>
                                                            </display:column>
                                                            <display:column media="html" title="Print Slip">
                                                                <s:if test="#attr.row.flagEdit">
                                                                </s:if>
                                                                <s:else>
                                                                    <s:if test="#attr.row.flagSlip">
                                                                        <a href="javascript:;" draftBulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                           draftTahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                           draftBranchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                           draftBranchName="<s:property value="%{#attr.row.branchName}"/>"
                                                                           draftTipe="<s:property value="%{#attr.row.tipe}"/>"
                                                                           class="item-slip">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_printer_lama.ico"/>" name="icon_edit">
                                                                        </a>
                                                                    </s:if>
                                                                </s:else>
                                                            </display:column>
                                                            <display:column property="bulan" title="Bulan"  />
                                                            <display:column property="tahun" title="Tahun"  />
                                                            <display:column property="jumlahPegawai" title="Jumlah Pegawai"  />
                                                            <display:column style="text-align:right;" property="totalA" title="Jumlah Kotor"  />
                                                            <display:column style="text-align:right;" property="totalGajiBersih" title="Jumlah Bersih"  />
                                                            <display:column property="approvalFlag" title="Status Approve" />
                                                            <display:column property="stApprovalDate" title="Tanggal Approve" />
                                                            <display:setProperty name="paging.banner.item_name">Payroll</display:setProperty>
                                                            <display:setProperty name="paging.banner.items_name">Payroll</display:setProperty>
                                                            <display:setProperty name="export.excel.filename">Payroll.xls</display:setProperty>
                                                            <display:setProperty name="export.csv.filename">Payroll.csv</display:setProperty>
                                                            <display:setProperty name="export.pdf.filename">Payroll.pdf</display:setProperty>
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
<div id="modal-approve" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:400px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Bulan</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="approveBulan" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tahun</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="approveTahun" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Unit</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="approveBranch" name="nip">
                            <input readonly type="text" class="form-control nip" style="display: none" id="approveBranchId" name="nip">
                        </div>
                    </div>
                    <div class="form-group" style="display: none">
                        <label class="control-label col-sm-4" >Tipe</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip"  id="tipeId2" >
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a type="button" id="btnApprove" class="btn btn-success"><i class="fa fa-check"></i> Approve</a>
                <a type="button" id="btnNotApprove" class="btn btn-danger"><i class="fa fa-close"></i> Not Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-approve-unit" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:400px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myFormApproveUnit">
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Bulan</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="approveBulanUnit" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tahun</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="approveTahunUnit" name="nip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Unit</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="approveBranchUnit" name="nip">
                            <input readonly type="text" class="form-control nip" style="display: none" id="approveBranchIdUnit" name="nip">
                        </div>
                    </div>
                    <div class="form-group" style="display: none">
                        <label class="control-label col-sm-4" >Tipe</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip"  id="tipeId2Unit" >
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a type="button" id="btnApproveUnit" class="btn btn-success"><i class="fa fa-check"></i> Approve</a>
                <a type="button" id="btnNotApproveUnit" class="btn btn-danger"><i class="fa fa-close"></i> Not Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-approve-sdm" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:400px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myFormApproveSdm">
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Bulan</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="approveBulanSdm">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tahun</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="approveTahunSdm">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Unit</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="approveBranchSdm">
                            <input readonly type="text" class="form-control nip" style="display: none" id="approveBranchIdSdm">
                        </div>
                    </div>
                    <div class="form-group" style="display: none">
                        <label class="control-label col-sm-4" >Tipe</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip"  id="tipeId2Sdm" >
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a type="button" id="btnApproveSdm" class="btn btn-success"><i class="fa fa-check"></i> Approve</a>
                <a type="button" id="btnNotApproveSdm" class="btn btn-danger"><i class="fa fa-close"></i> Not Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
            </div>
        </div>
    </div>
</div>
<div id="modal-approve-keu-unit" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:400px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="formApprovalKeuUnit">
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Bulan</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="approveBulanKeuUnit">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tahun</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="approveTahunKeuUnit">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Unit</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="approveBranchKeuUnit">
                            <input readonly type="text" class="form-control nip" id="approveBranchIdKeuUnit" style="display: none">
                        </div>
                    </div>
                    <div class="form-group" style="display: none">
                        <label class="control-label col-sm-4" >Tipe</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip"  id="tipeId2KeuUnit" >
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a type="button" id="btnApproveKeuUnit" class="btn btn-success"><i class="fa fa-check"></i> Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        var tipe = document.getElementById("tipe").value;
        if(tipe != "PR" || tipe != "T" || tipe != "PD"){
            $('#printSlip').hide;
        }else{
            $('#printSlip').show;
        }

        $('#btnApprove').click(function(){
            if (confirm('Apakah Anda ingin Mesetujui Data Payroll?')) {
                var branchId = document.getElementById("approveBranchId").value;
                var bulan = document.getElementById("approveBulan").value;
                var tahun = document.getElementById("approveTahun").value;
                var tipe = document.getElementById("tipeId2").value;
                var statusApprove = "Y";

                PayrollAction.approvePayroll(branchId, bulan, tahun, statusApprove, tipe, function(listdata){
                    alert('Payroll Berhasil Disetujui');
                    $('#modal-approve').modal('hide');
                    $('#myForm')[0].reset();
                    window.location.href="<s:url action='initForm_payroll.action'/>";
                });
            }
        });

        $('#btnApproveKeuUnit').click(function(){
            if (confirm('Apakah Anda ingin Mesetujui Data Payroll?')) {
                var branchId = document.getElementById("approveBranchIdKeuUnit").value;
                var bulan = document.getElementById("approveBulanKeuUnit").value;
                var tahun = document.getElementById("approveTahunKeuUnit").value;
                var tipe = document.getElementById("tipeId2KeuUnit").value;
                var statusApprove = "Y";

                PayrollAction.approvePayrollAksUnit(branchId, bulan, tahun, statusApprove, tipe, function(listdata){
                    alert('Payroll Berhasil Disetujui');
                    $('#modal-approve-keu-unit').modal('hide');
                    $('#myForm')[0].reset();
                    window.location.href="<s:url action='initForm_payroll.action'/>";
                });
            }
        });

        $('#btnApproveUnit').click(function(){
            if (confirm('Apakah Anda ingin Mesetujui Data Payroll?')) {
                var branchId = document.getElementById("approveBranchIdUnit").value;
                var bulan = document.getElementById("approveBulanUnit").value;
                var tahun = document.getElementById("approveTahunUnit").value;
                var tipe = document.getElementById("tipeId2Unit").value;
                var statusApprove = "Y";

                PayrollAction.approvePayrollUnit(branchId, bulan, tahun, statusApprove, tipe, function(listdata){
                    alert('Payroll Berhasil Disetujui');
                    $('#modal-approve').modal('hide');
                    $('#myForm')[0].reset();
                    window.location.href="<s:url action='initForm_payroll.action'/>";
                });
            }
        });

        $('#btnApproveSdm').click(function(){
            if (confirm('Apakah Anda ingin Mesetujui Data Payroll?')) {
                var branchId = document.getElementById("approveBranchIdSdm").value;
                var bulan = document.getElementById("approveBulanSdm").value;
                var tahun = document.getElementById("approveTahunSdm").value;
                var tipe = document.getElementById("tipeId2Sdm").value;
                var statusApprove = "Y";

                PayrollAction.approvePayrollSdm(branchId, bulan, tahun, statusApprove, tipe, function(listdata){
                    alert('Payroll Berhasil Disetujui');
                    $('#modal-approve').modal('hide');
                    $('#myForm')[0].reset();
                    window.location.href="<s:url action='initForm_payroll.action'/>";
                });
            }
        });

        $('#btnNotApproveSdm').click(function(){
            if (confirm('Apakah Anda ingin menolak Data Payroll?')) {
                var branchId = document.getElementById("approveBranchIdSdm").value;
                var bulan = document.getElementById("approveBulanSdm").value;
                var tahun = document.getElementById("approveTahunSdm").value;
                var tipe = document.getElementById("tipeId2Sdm").value;
                var statusApprove = "N";

                PayrollAction.approvePayrollSdm(branchId, bulan, tahun, statusApprove, tipe, function(listdata){
                    alert('Payroll Berhasil Tidak Di Approve');
                    $('#modal-approve').modal('hide');
                    $('#myForm')[0].reset();
                    window.location.href="<s:url action='initForm_payroll.action'/>";
                });
            }
        });

        $('#btnNotApproveUnit').click(function(){
            if (confirm('Apakah Anda ingin menolak Data Payroll?')) {
                var branchId = document.getElementById("approveBranchIdUnit").value;
                var bulan = document.getElementById("approveBulanUnit").value;
                var tahun = document.getElementById("approveTahunUnit").value;
                var tipe = document.getElementById("tipeId2Unit").value;
                var statusApprove = "N";

                PayrollAction.approvePayrollUnit(branchId, bulan, tahun, statusApprove, tipe, function(listdata){
                    alert('Payroll Berhasil Tidak Di Approve');
                    $('#modal-approve').modal('hide');
                    $('#myForm')[0].reset();
                    window.location.href="<s:url action='initForm_payroll.action'/>";
                });
            }
        });

        $('#btnPrint').click(function(){
            if (confirm('Apakah Anda ingin mencetak draft Payroll?')) {
                var branchId = document.getElementById("draftBranchId").value;
                var bulan = document.getElementById("draftBulan").value;
                var tahun = document.getElementById("draftTahun").value;
                var tipe = document.getElementById("draftTipeId2").value;
                var tipePrint = document.getElementById("draftTipePrint").value;
                var statusPegawai = document.getElementById("draftStatusPegawai").value;

                window.location.href = 'searchReportDraft_payroll?branchId='+branchId+'&bulan='+bulan+'&tahun='+tahun+'&statusPegawai='+statusPegawai
                +'&tipe='+tipePrint;
            }
        });

        $('#btnNotApprove').click(function(){
            if (confirm('Apakah Anda ingin menolak Data Payroll?')) {
                var branchId = document.getElementById("approveBranchId").value;
                var bulan = document.getElementById("approveBulan").value;
                var tahun = document.getElementById("approveTahun").value;
                var tipe = document.getElementById("tipeId2").value;
                var statusApprove = "N";

                PayrollAction.approvePayroll(branchId, bulan, tahun, statusApprove, tipe, function(listdata){
                    alert('Payroll Berhasil Ditolak');
                    $('#modal-approve').modal('hide');
                    $('#myForm')[0].reset();
                    location.reload();
                });
            }
        });

        $('.tablePayroll').on('click', '.item-edit', function(){
            var tipe = $(this).attr('tipe');
            var bulan = $(this).attr('bulan');
            var tahun = $(this).attr('tahun');
            var branchId = $(this).attr('branchId');
            var branchName = $(this).attr('branchName');

            $('#approveBulan').val(bulan);
            $('#approveTahun').val(tahun);
            $('#approveBranch').val(branchName);
            $('#approveBranchId').val(branchId);
            $('#tipeId2').val(tipe);

            PayrollAction.getApproval(branchId, bulan, tahun, tipe, function(listdata){
                var hasil = "";

                if(listdata.approvalFlag == 'Y'){
                    hasil = "Disetujui";
                } else if(listdata.approvalFlag == 'N'){
                    hasil = "Tidak Disetujui";
                }


                $('#approveStatus').val(hasil);
                $('#approveTanggal').val(listdata.stApprovalDate);
                $('#modal-approve').find('.modal-title').text('Approve Payroll');
                $('#modal-approve').modal('show');
            });
        });
        $('.tablePayroll').on('click', '.item-approve-unit', function(){
            var tipe = $(this).attr('tipe');
            var bulan = $(this).attr('bulan');
            var tahun = $(this).attr('tahun');
            var branchId = $(this).attr('branchId');
            var branchName = $(this).attr('branchName');

            $('#approveBulanUnit').val(bulan);
            $('#approveTahunUnit').val(tahun);
            $('#approveBranchUnit').val(branchName);
            $('#approveBranchIdUnit').val(branchId);
            $('#tipeId2Unit').val(tipe);

            PayrollAction.getApprovalUnit(branchId, bulan, tahun, tipe, function(listdata){
                var hasil = "";
                if(listdata.approvalFlag == 'Y'){
                    hasil = "Disetujui";
                } else if(listdata.approvalFlag == 'N'){
                    hasil = "Tidak Disetujui";
                }
                $('#approveStatusUnit').val(hasil);
                $('#approveTanggalUnit').val(listdata.stApprovalDate);
                $('#modal-approve-unit').find('.modal-title').text('Approve Payroll Unit');
                $('#modal-approve-unit').modal('show');
            });
        });

        $('.tablePayroll').on('click', '.item-approve-sdm', function(){
            var tipe = $(this).attr('tipe');
            var bulan = $(this).attr('bulan');
            var tahun = $(this).attr('tahun');
            var branchId = $(this).attr('branchId');
            var branchName = $(this).attr('branchName');

            $('#approveBulanSdm').val(bulan);
            $('#approveTahunSdm').val(tahun);
            $('#approveBranchSdm').val(branchName);
            $('#approveBranchIdSdm').val(branchId);
            $('#tipeId2Sdm').val(tipe);

            PayrollAction.getApprovalUnit(branchId, bulan, tahun, tipe, function(listdata){
                var hasil = "";
                if(listdata.approvalFlag == 'Y'){
                    hasil = "Disetujui";
                } else if(listdata.approvalFlag == 'N'){
                    hasil = "Tidak Disetujui";
                }
                $('#approveStatusSdm').val(hasil);
                $('#approveTanggalSdm').val(listdata.stApprovalDate);
                $('#modal-approve-sdm').find('.modal-title').text('Approve Payroll SDM Kantor Pusat');
                $('#modal-approve-sdm').modal('show');
            });
        });
        $('.tablePayroll').on('click', '.item-approve-keu-unit', function(){
            var tipe = $(this).attr('tipe');
            var bulan = $(this).attr('bulan');
            var tahun = $(this).attr('tahun');
            var branchId = $(this).attr('branchId');
            var branchName = $(this).attr('branchName');

            $('#approveBulanKeuUnit').val(bulan);
            $('#approveTahunKeuUnit').val(tahun);
            $('#approveBranchKeuUnit').val(branchName);
            $('#approveBranchIdKeuUnit').val(branchId);
            $('#tipeId2KeuUnit').val(tipe);

            PayrollAction.getApproval(branchId, bulan, tahun, tipe, function(listdata){
                var hasil = "";
                if(listdata.approvalFlag == 'Y'){
                    hasil = "Disetujui";
                } else if(listdata.approvalFlag == 'N'){
                    hasil = "Tidak Disetujui";
                }
                $('#approveStatusKeuUnit').val(hasil);
                $('#approveTanggalKeuUnit').val(listdata.stApprovalDate);
                $('#modal-approve-keu-unit').find('.modal-title').text('Approve Payroll Keuangan Unit');
                $('#modal-approve-keu-unit').modal('show');
            });
        });

        $('.tablePayroll').on('click', '.item-draft', function(){
            var tipe = $(this).attr('draftTipe');
            var bulan = $(this).attr('draftBulan');
            var tahun = $(this).attr('draftTahun');
            var branchId = $(this).attr('draftBranchId');
            var branchName = $(this).attr('draftBranchName');

            $('#draftBulan').val(bulan);
            $('#draftTahun').val(tahun);
            $('#draftBranch').val(branchName);
            $('#draftBranchId').val(branchId);
            $('#draftTipeId2').val(tipe);

            $('#modal-draft').find('.modal-title').text('Print Draft');
            $('#modal-draft').modal('show');
        });

        $('.tablePayroll').on('click', '.item-slip', function(){
            var bulan = $(this).attr('draftBulan');
            var tahun = $(this).attr('draftTahun');
            var branchId = $(this).attr('draftBranchId');
            var tipe = $(this).attr('draftTipe');

            if (confirm('Apakah Anda ingin mencetak slip?')) {
                window.location.href = 'printReportPayrollByBranch_payroll?branchId='+branchId+'&tahun='+tahun+'&bulan='+bulan+'&tipe='+tipe;
            }
        });

        $('#printPayroll').click(function(){
            var periodeBln1 = document.getElementById("bulanPayroll1").value;
            var periodeThn1 = document.getElementById("tahunPayroll1").value;
            var periodeBln2 = document.getElementById("bulanPayroll2").value;
            var periodeThn2 = document.getElementById("tahunPayroll2").value;
            var unit = document.getElementById("unitId").value;

            window.location.href = 'printReportPayrollBulan_payroll?branchId='+unit+'&tahun='+periodeThn1+'&bulan='+periodeBln1
                    +'&tahun2='+periodeThn2+'&bulan2='+periodeBln2;
        });
    });
</script>
