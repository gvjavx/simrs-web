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
            <small>GO-MEDSYS</small>
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
                                                    $('#bulanPayroll1').val(("0" + (dt.getMonth() + 1)).slice(-2));
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
                                                    <td>
                                                        <a href="add_payroll.action" class="btn btn-success" ><i class="fa fa-plus"></i> Add Payroll</a>
                                                    </td>
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
                                            <table id="showdata" width="100%">
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
                                                                <display:column media="html" title="Approve Kanpus" style="text-align:center;">
                                                                    <s:if test='#attr.row.statusApprove=="K"'>
                                                                        <s:if test="%{payroll.kantorPusat}">
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
                                                                    <s:elseif test='#attr.row.statusApprove=="D"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                </display:column>
                                                            <display:column media="html" title="Approve Unit" style="text-align:center;">
                                                                <s:if test='#attr.row.statusApprove=="U"'>
                                                                    <a href="javascript:;" bulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                       tahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                       branchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                       branchName="<s:property value="%{#attr.row.branchName}"/>"
                                                                       tipe="<s:property value="%{#attr.row.tipe}"/>"
                                                                       class="item-approve-unit">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">
                                                                    </a>
                                                                </s:if>
                                                                <s:elseif test='#attr.row.statusApprove=="K"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:elseif>
                                                                <s:elseif test='#attr.row.statusApprove=="D"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:elseif>
                                                            </display:column>

                                                            <display:column media="html" title="View">
                                                                <s:url var="urlEdit" namespace="/payroll" action="view_payroll" escapeAmp="false">
                                                                    <s:param name="bulan"><s:property value="#attr.row.bulan"/></s:param>
                                                                    <s:param name="tahun"><s:property value="#attr.row.tahun"/></s:param>
                                                                    <s:param name="branchId"><s:property value="#attr.row.branchId"/></s:param>
                                                                    <s:param name="tipe"><s:property value="#attr.row.tipe"/></s:param>
                                                                </s:url>
                                                                <s:if test='#attr.row.statusApprove=="D"'>
                                                                    <s:a href="%{urlEdit}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" >
                                                                    </s:a>
                                                                </s:if>
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
                                                                <s:else>
                                                                </s:else>
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
                                                            <display:column property="totalA" title="Jumlah Kotor"  />
                                                            <display:column property="totalGajiBersih" title="Jumlah Bersih"  />
                                                            <display:column property="approvalFlag" title="Status Approve" />
                                                            <display:column property="stApprovalDate" title="Tanggal Approve" />
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
                <%--<a type="button" id="btnNotApproveUnit" class="btn btn-danger"><i class="fa fa-close"></i> Not Approve</a>--%>
                <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-draft" class="modal fade modal2" role="dialog">
    <div class="modal-dialog " style="width:500px;">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Bulan</label>
                        <div class="col-sm-7">
                            <input readonly type="text" class="form-control nip" id="draftBulan" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tahun</label>
                        <div class="col-sm-7">
                            <input readonly type="text" class="form-control nip" id="draftTahun" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit</label>
                        <div class="col-sm-7">
                            <input readonly type="text" class="form-control nip" id="draftBranch" name="nip">
                            <input readonly type="text" class="form-control nip" style="display: none" id="draftBranchId" name="nip">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Tipe</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="draftTipePrint">
                                <option value="PK">Penghasilan Kotor</option>
                                <option value="PD">Potongan Dinas</option>
                                <option value="PL">Potongan Lain</option>
                                <option value="R">Rekapitulasi Penghasilan Karyawan</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Status Peg.</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="draftStatusPegawai">
                                <option value="KS">Karyawan Staff</option>
                                <option value="KNS">Karyawan Non Staff</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group" style="display: none">
                        <label class="control-label col-sm-4" >Tipe</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip"  id="draftTipeId2" >
                        </div>
                    </div>

                </form>
                <%--<font size="1" face="Courier New" >--%>
                <table style="width: 100%;" id="sppdPersonTable" class="sppdPersonTable table table-bordered">
                </table>
            </div>
            <div class="modal-footer">
                <a type="button" id="btnPrint" class="btn btn-success"><i class="fa fa-print"></i> Print</a>
                <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
            </div>
        </div>
    </div>
</div>
<div id="modal-addPayroll" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add Data</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="myForm">

                    <div class="form-group">
                        <label class="control-label col-sm-3" >Unit : </label>
                        <div class="col-sm-6">
                            <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="sppd.branchId"
                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-3" style="text-align: right">
                            <input type="checkbox" id="checkApprove" name="checkApprove[]" class="checkApprove" >
                            <label class="control-label ">Payroll : </label>
                        </div>
                        <div class="col-sm-3">
                            <s:select list="#{'1':'Januari', '2' : 'Februari', '3':'Maret', '4':'April', '5':'Mei', '6':'Juni', '7':'Juli',
                                '8': 'Agustus', '9' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}" id="flag" name="department.flag"
                                      headerKey="0" headerValue="Bulan" cssClass="form-control" />
                        </div>
                        <div class="col-sm-3">
                            <s:select list="#{'2017':'2017', '2018' : '2018', '2019':'2019', '2020':'2020', '2021':'2021', '2022':'2022', '2023':'2023'}" id="flag" name="department.flag"
                                      headerKey="0" headerValue="Tahun" cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-3" style="text-align: right">
                            <input type="checkbox" id="checkApprove" name="checkApprove[]" class="checkApprove" >
                            <label class="control-label " >Rapel: </label>
                        </div>
                        <div class="col-sm-3">
                            <s:select list="#{'1':'Januari', '2' : 'Februari', '3':'Maret', '4':'April', '5':'Mei', '6':'Juni', '7':'Juli',
                                '8': 'Agustus', '9' : 'September', '10' : 'Oktober', '11' : 'November', '12' : 'Desember'}" id="flag" name="department.flag"
                                      headerKey="0" headerValue="Bulan" cssClass="form-control" />
                        </div>
                        <div class="col-sm-3">
                            <s:select list="#{'2017':'2017', '2018' : '2018', '2019':'2019', '2020':'2020', '2021':'2021', '2022':'2022', '2023':'2023'}" id="flag" name="department.flag"
                                      headerKey="0" headerValue="Tahun" cssClass="form-control" />
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSave" type="button" class="btn btn-default btn-success">Add</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
                    //location.reload();
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
        $('#btnNotApproveUnit').click(function(){
            if (confirm('Apakah Anda ingin menolak Data Payroll?')) {
                var branchId = document.getElementById("approveBranchId").value;
                var bulan = document.getElementById("approveBulan").value;
                var tahun = document.getElementById("approveTahun").value;
                var tipe = document.getElementById("tipeId2").value;
                var statusApprove = "N";

                PayrollAction.approvePayrollUnit(branchId, bulan, tahun, statusApprove, tipe, function(listdata){
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
