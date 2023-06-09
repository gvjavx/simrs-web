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
        .not-allowed {
            cursor: not-allowed !important;
        }
    </style>
    <script type='text/javascript'>

        function link(){
            window.location.href="<s:url action='initForm_payroll'/>";
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
            Penggajian
        </h1>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i>Pencarian Penggajian</h3>
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
                                        'JP':'Jasa Operasional', 'JB':'PMP', 'PN':'SHT', 'IN':'Insentif'}" id="tipe" name="payroll.tipe"
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
                                        <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"
                                                   height="500" width="600" autoOpen="false"
                                                   title="Payroll ">
                                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>
                                        <div style="text-align: left !important;">
                                            <div class="box-header with-border"></div>
                                            <div class="box-header with-border">
                                                <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar Payroll</h3>
                                            </div>
                                            <div class="box-body">
                                                <table id="tablePayroll" class="tablePayroll table table-bordered table-striped" style="font-size: 11px">
                                                    <thead>
                                                        <tr bgcolor="#90ee90" style="text-align: center">
                                                            <td>Unit</td>
                                                            <td>Bulan</td>
                                                            <td>Tahun</td>
                                                            <td>Tipe</td>
                                                            <td>Jumlah Pegawai</td>
                                                            <td>Jumlah Kotor (RP)</td>
                                                            <td>Jumlah Bersih (RP)</td>
                                                            <td>Status Approve</td>
                                                            <td>Tanggal Approve</td>
                                                            <td align="center">Action</td>
                                                            <td align="center">App. SDM Unit</td>
                                                            <td align="center">App. SDM KP</td>
                                                            <td align="center">App. Keu. KP</td>
                                                            <td align="center">App. Keu. Unit</td>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <s:iterator value="#session.listOfResult" var="row">
                                                        <tr>
                                                            <td style="text-align: center"><s:property value="branchName"/></td>
                                                            <td style="text-align: center"><s:property value="bulan"/></td>
                                                            <td style="text-align: center"><s:property value="tahun"/></td>
                                                            <td style="text-align: center"><s:property value="tipeName"/></td>
                                                            <td style="text-align: center"><s:property value="jumlahPegawai"/></td>
                                                            <td style="text-align: center"><s:property value="totalA"/></td>
                                                            <td style="text-align: center"><s:property value="totalGajiBersih"/></td>
                                                            <td style="text-align: center"><s:property value="approvalFlag"/></td>
                                                            <td style="text-align: center"><s:property value="stApprovalDate"/></td>
                                                            <td align="center">
                                                                <%--VIEW--%>
                                                                <s:url var="urlView" namespace="/payroll" action="view_payroll" escapeAmp="false">
                                                                    <s:param name="bulan"><s:property value="#attr.row.bulan"/></s:param>
                                                                    <s:param name="tahun"><s:property value="#attr.row.tahun"/></s:param>
                                                                    <s:param name="branchId"><s:property value="#attr.row.branchId"/></s:param>
                                                                    <s:param name="tipe"><s:property value="#attr.row.tipe"/></s:param>
                                                                </s:url>
                                                                <s:a href="%{urlView}">
                                                                    <img border="0" src="<s:url value="/pages/images/icons8-search-25.png"/>" >
                                                                </s:a>
                                                                <%--EDIT--%>
                                                                <s:url var="urlEdit" namespace="/payroll" action="edit_payroll" escapeAmp="false">
                                                                    <s:param name="bulan"><s:property value="#attr.row.bulan"/></s:param>
                                                                    <s:param name="tahun"><s:property value="#attr.row.tahun"/></s:param>
                                                                    <s:param name="branchId"><s:property value="#attr.row.branchId"/></s:param>
                                                                    <s:param name="tipe"><s:property value="#attr.row.tipe"/></s:param>
                                                                </s:url>
                                                                <s:if test='#row.statusApprove == "U"'>
                                                                    <s:if test='%{payroll.sdm}'>
                                                                        <s:a href="%{urlEdit}" id="hrefEdit">
                                                                            <img border="0" src="<s:url value="/pages/images/icons8-create-25.png"/>" name="icon_edit">
                                                                        </s:a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <img border="0" class="not-allowed" src="<s:url value="/pages/images/icons8-create-orange-25.png"/>" >
                                                                    </s:else>
                                                                </s:if>
                                                                <s:elseif test='#row.statusApprove=="S"'>
                                                                    <s:if test='%{payroll.kantorPusat}'>
                                                                        <s:if test='%{payroll.sdm}'>
                                                                            <s:a href="%{urlEdit}" id="hrefEdit">
                                                                                <img border="0" src="<s:url value="/pages/images/icons8-create-25.png"/>" name="icon_edit">
                                                                            </s:a>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <img border="0" class="not-allowed" src="<s:url value="/pages/images/icons8-create-orange-25.png"/>" >
                                                                        </s:else>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <img border="0" class="not-allowed" src="<s:url value="/pages/images/icons8-create-orange-25.png"/>" >
                                                                    </s:else>
                                                                </s:elseif>
                                                                <s:else>
                                                                    <img border="0" class="not-allowed" src="<s:url value="/pages/images/icons8-create-orange-25.png"/>" >
                                                                </s:else>
                                                                <%--PRINT SLIP--%>
                                                                <s:if test="#row.flagEdit">
                                                                    <img border="0" class="not-allowed" src="<s:url value="/pages/images/icons8-print-25-yellow.png"/>" >
                                                                </s:if>
                                                                <s:else>
                                                                    <s:if test="#row.flagSlip">
                                                                        <a href="javascript:;" draftBulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                           draftTahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                           draftBranchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                           draftBranchName="<s:property value="%{#attr.row.branchName}"/>"
                                                                           draftTipe="<s:property value="%{#attr.row.tipe}"/>"
                                                                           class="item-slip">
                                                                            <img border="0" src="<s:url value="/pages/images/icons8-print-25.png"/>" name="icon_edit">
                                                                        </a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <img border="0" class="not-allowed" src="<s:url value="/pages/images/icons8-print-25-yellow.png"/>" >
                                                                    </s:else>
                                                                </s:else>
                                                                <%--Reproses--%>
                                                                    <s:if test='#row.statusApprove == "U"'>
                                                                        <s:if test='%{payroll.sdm}'>
                                                                            <a href="javascript:;"
                                                                               bulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                               tahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                               branchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                               branchName="<s:property value="%{#attr.row.branchName}"/>"
                                                                               tipe="<s:property value="%{#attr.row.tipe}"/>"
                                                                               class="item-reproses">
                                                                                <img border="0" src="<s:url value="/pages/images/icons8-transaction-25.png"/>" >
                                                                            </a>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <img border="0" class="not-allowed" src="<s:url value="/pages/images/icons8-transaction-25-yellow.png"/>" >
                                                                        </s:else>
                                                                    </s:if>
                                                                    <s:elseif test='#row.statusApprove=="S"'>
                                                                        <s:if test='%{payroll.kantorPusat}'>
                                                                            <s:if test='%{payroll.sdm}'>
                                                                                <a href="javascript:;"
                                                                                   bulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                                   tahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                                   branchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                                   branchName="<s:property value="%{#attr.row.branchName}"/>"
                                                                                   tipe="<s:property value="%{#attr.row.tipe}"/>"
                                                                                   class="item-reproses">
                                                                                    <img border="0" src="<s:url value="/pages/images/icons8-transaction-25.png"/>" >
                                                                                </a>
                                                                            </s:if>
                                                                            <s:else>
                                                                                <img border="0" class="not-allowed" src="<s:url value="/pages/images/icons8-transaction-25-yellow.png"/>" >
                                                                            </s:else>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <img border="0" class="not-allowed" src="<s:url value="/pages/images/icons8-transaction-25-yellow.png"/>" >
                                                                        </s:else>
                                                                    </s:elseif>
                                                                    <s:else>
                                                                        <img border="0" class="not-allowed" src="<s:url value="/pages/images/icons8-transaction-25-yellow.png"/>" >
                                                                    </s:else>
                                                            </td>
                                                            <td align="center">
                                                                <s:if test="%{payroll.kantorPusat}">
                                                                    <s:if test='#row.statusApprove=="K"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:if>
                                                                    <s:elseif test='#row.statusApprove=="S"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                    <s:elseif test='#row.statusApprove=="D"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                    <s:elseif test='#row.approvalAksFlag=="Y"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:if test='#row.approvalFlag=="N"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">
                                                                    </s:if>
                                                                    <s:elseif test='#row.statusApprove=="U"'>
                                                                        <a href="javascript:;" bulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                           tahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                           branchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                           branchName="<s:property value="%{#attr.row.branchName}"/>"
                                                                           tipe="<s:property value="%{#attr.row.tipe}"/>"
                                                                           class="item-approve-unit">
                                                                            <img border="0" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>" name="icon_edit">
                                                                        </a>
                                                                    </s:elseif>
                                                                    <s:elseif test='#row.statusApprove=="K"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                    <s:elseif test='#row.statusApprove=="S"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                    <s:elseif test='#row.statusApprove=="D"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                    <s:elseif test='#row.approvalAksFlag=="Y"'>
                                                                        <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                    </s:elseif>
                                                                </s:else>
                                                            </td>
                                                            <td align="center">
                                                                <s:if test='#row.statusApprove=="S"'>
                                                                    <s:if test="%{payroll.kantorPusat}">
                                                                        <s:if test="%{payroll.sdm}">
                                                                            <a href="javascript:;" bulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                               tahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                               branchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                               branchName="<s:property value="%{#attr.row.branchName}"/>"
                                                                               tipe="<s:property value="%{#attr.row.tipe}"/>"
                                                                               class="item-approve-sdm">
                                                                                <img border="0" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>" name="icon_edit">
                                                                            </a>
                                                                        </s:if>
                                                                    </s:if>
                                                                </s:if>
                                                                <s:elseif test='#row.statusApprove=="K"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:elseif>
                                                                <s:elseif test='#row.statusApprove=="D"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:elseif>
                                                                <s:elseif test='#row.approvalAksFlag=="Y"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:elseif>
                                                            </td>
                                                            <td align="center">
                                                                <s:if test='#row.statusApprove=="K"'>
                                                                    <s:if test="%{payroll.kantorPusat}">
                                                                        <s:if test="%{payroll.keuanganKantorPusat}">
                                                                            <a href="javascript:;" bulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                               tahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                               branchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                               branchName="<s:property value="%{#attr.row.branchName}"/>"
                                                                               tipe="<s:property value="%{#attr.row.tipe}"/>"
                                                                               class="item-edit">
                                                                                <img border="0" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>" name="icon_edit">
                                                                            </a>
                                                                        </s:if>
                                                                    </s:if>
                                                                </s:if>
                                                                <s:elseif test='#row.statusApprove=="D"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:elseif>
                                                                <s:elseif test='#row.approvalAksFlag=="Y"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
                                                                </s:elseif>
                                                            </td>
                                                            <td align="center">
                                                                <s:if test='#row.statusApprove=="RK"'>
                                                                    <a href="javascript:;" bulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                       tahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                       branchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                       branchName="<s:property value="%{#attr.row.branchName}"/>"
                                                                       tipe="<s:property value="%{#attr.row.tipe}"/>"
                                                                       class="item-approve-keu-unit">
                                                                        <img border="0" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>">
                                                                    </a>
                                                                </s:if>
                                                                <s:elseif test='#row.statusApprove=="D"'>
                                                                    <img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">
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
<div id="modal-approve" class="modal fade modal2" role="dialog">
    <div class="modal-dialog modal-flat" style="width:400px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-tasks"></i></h4>
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
            <div class="modal-footer" style="background-color: #cacaca">
                <a type="button" id="btnApprove" class="btn btn-success"><i class="fa fa-check"></i> Approve</a>
                <a type="button" id="btnNotApprove" class="btn btn-danger"><i class="fa fa-close"></i> Not Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-approve-unit" class="modal fade modal2" role="dialog">
    <div class="modal-dialog modal-flat" style="width:400px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-tasks"></i></h4>
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
            <div class="modal-footer" style="background-color: #cacaca">
                <a type="button" id="btnApproveUnit" class="btn btn-success"><i class="fa fa-check"></i> Approve</a>
                <a type="button" id="btnNotApproveUnit" class="btn btn-danger"><i class="fa fa-close"></i> Not Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-approve-sdm" class="modal fade" role="dialog">
    <div class="modal-dialog modal-flat" style="width:400px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-tasks"></i></h4>
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
            <div class="modal-footer" style="background-color: #cacaca">
                <a type="button" id="btnApproveSdm" class="btn btn-success"><i class="fa fa-check"></i> Approve</a>
                <a type="button" id="btnNotApproveSdm" class="btn btn-danger"><i class="fa fa-close"></i> Not Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
            </div>
        </div>
    </div>
</div>
<div id="modal-approve-keu-unit" class="modal fade" role="dialog">
    <div class="modal-dialog modal-flat" style="width:400px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-tasks"></i></h4>
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
            <div class="modal-footer" style="background-color: #cacaca">
                <a type="button" id="btnApproveKeuUnit" class="btn btn-success"><i class="fa fa-check"></i> Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
            </div>
        </div>
    </div>
</div>
<div id="modal-reproses" class="modal fade" role="dialog">
    <div class="modal-dialog modal-flat" style="width:400px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-tasks"></i></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="formReproses">
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Bulan</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="reprosesBulan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tahun</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="reprosesTahun">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Unit</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="reprosesBranch">
                            <input readonly type="text" class="form-control nip" id="reprosesBranchId" style="display: none">
                        </div>
                    </div>
                    <div class="form-group" style="display: none">
                        <label class="control-label col-sm-4" >Tipe</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip"  id="reprosestipeId" >
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <a type="button" id="btnReproses" class="btn btn-danger"><i class="fa fa-refresh"></i> Reproses</a>
                <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
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
                <%--<button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No--%>
                <%--</button>--%>
                <button type="button" class="btn btn-sm btn-success" id="ok_con"><i class="fa fa-check"></i> Ok
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#tablePayroll').DataTable({
            "order": [[2, "asc"],[1, "asc"]],
            "columnDefs": [
                {
                    "targets": 9,
                    "width": "100px"}
            ],
        });

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
        }

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
                showDialog("loading");
                dwr.engine.setAsync(true);
                PayrollAction.approvePayroll(branchId, bulan, tahun, statusApprove, tipe, function(listdata){
                    dwr.engine.setAsync(false);
                    $('#modal-approve').modal('hide');
                    showDialog("success");
                });
            }
        });

        $('#ok_con').click(function () {
            window.location.href="<s:url action='initForm_payroll.action'/>";
        })

        $('#btnApproveKeuUnit').click(function(){
            if (confirm('Apakah Anda ingin Mesetujui Data Payroll?')) {
                var branchId = document.getElementById("approveBranchIdKeuUnit").value;
                var bulan = document.getElementById("approveBulanKeuUnit").value;
                var tahun = document.getElementById("approveTahunKeuUnit").value;
                var tipe = document.getElementById("tipeId2KeuUnit").value;
                var statusApprove = "Y";
                showDialog("loading");
                dwr.engine.setAsync(true);
                PayrollAction.approvePayrollAksUnit(branchId, bulan, tahun, statusApprove, tipe, function(listdata){
                    dwr.engine.setAsync(false);
                    $('#modal-approve-keu-unit').modal('hide');
                    showDialog("success");
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
                    dwr.engine.setAsync(false);
                    $('#modal-approve').modal('hide');
                    showDialog("success");
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
                showDialog("loading");
                dwr.engine.setAsync(true);
                PayrollAction.approvePayrollSdm(branchId, bulan, tahun, statusApprove, tipe, function(listdata){
                    dwr.engine.setAsync(false);
                    showDialog("success");
                    $('#modal-approve').modal('hide');
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

        $('.tablePayroll').on('click', '.item-reproses', function(){
            var tipe = $(this).attr('tipe');
            var bulan = $(this).attr('bulan');
            var tahun = $(this).attr('tahun');
            var branchId = $(this).attr('branchId');
            var branchName = $(this).attr('branchName');

            $('#reprosesBulan').val(bulan);
            $('#reprosesTahun').val(tahun);
            $('#reprosesBranch').val(branchName);
            $('#reprosesBranchId').val(branchId);
            $('#reprosestipeId').val(tipe);

            $('#modal-reproses').find('.modal-title').text('Reproses');
            $('#modal-reproses').modal('show');
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

        $('#btnReproses').click(function(){
            if (confirm('Peringatan : Jika anda melakukan reproses data payroll ini , data yang sebelumnya akan hilang dan diganti dengan data baru , \n \n Apakah anda ingin melanjutkan ?')) {
                var branchId = document.getElementById("reprosesBranchId").value;
                var bulan = document.getElementById("reprosesBulan").value;
                var tahun = document.getElementById("reprosesTahun").value;
                var tipe = document.getElementById("reprosestipeId").value;

                showDialog("loading");
                dwr.engine.setAsync(true);
                PayrollAction.reprosesPayroll(branchId, bulan, tahun, tipe, function(listdata){
                    dwr.engine.setAsync(false);
                    showDialog("success");
                    $('#modal-reproses').modal('hide');
                });
            }
        });
    });
</script>
