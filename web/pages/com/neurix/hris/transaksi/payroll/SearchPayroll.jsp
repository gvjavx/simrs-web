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
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserLoginAction.js"/>'></script>
    <%--<script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>--%>
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

        <%--function link(){--%>
            <%--window.location.href="<s:url action='initForm_alat'/>";--%>
        <%--}--%>

        $.subscribe('errorDialogSearch', function (event, data) {
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

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
                                        <%--<s:hidden name="addOrEdit"/>--%>
                                        <%--<s:hidden name="delete"/>--%>
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
                                                                  id="bulanPayroll1" name="payrollHeader.bulan"
                                                                  headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                                    </table>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:action id="comboPeriode" namespace="/payroll" name="initComboPeriodeTahunKurang10_payroll"/>
                                                        <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="tahunPayroll1"
                                                                  name="payrollHeader.tahun" required="true" headerKey=""
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
                                                                  id="bulanPayroll2" name="payrollHeader.bulansd"
                                                                  headerKey="0" headerValue="Bulan" cssClass="form-control" />
                                                    </table>
                                                </td>
                                                <td>
                                                    <table>
                                                        <%--<s:action id="comboPeriode" namespace="/rekruitmen" name="initComboPeriodeTahunSekarang10_rekruitmen"/>--%>
                                                        <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="tahunPayroll2"
                                                                  name="payrollHeader.tahunsd" required="true" headerKey=""
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
                                                        <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                                        <s:if test="%{payrollHeader.kantorPusat}">
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="payrollHeader.branchId"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                        </s:if>
                                                        <s:else>
                                                            <%--<s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>--%>
                                                            <s:select list="#initComboBranch.listOfComboBranch" id="unitTmp" name="payrollHeader.branchId" disabled="true"
                                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                                            <s:hidden id="branchId" name="payrollHeader.branchId" />
                                                        </s:else>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label class="control-label"><small>Tipe Payroll :</small></label>
                                                </td>
                                                <td>
                                                    <table>
                                                        <s:select list="#{'PY':'Payroll', 'TH':'THR', 'CP':'Cuti Panjang', 'CT':'Cuti Tahunan', 'JP':'Jasa Operasional', 'JB':'PMP',
                                                        'PN':'SHT(Pensiun)', 'IN':'Insentif','RP':'Rapel'}" id="tipe" name="payrollHeader.tipePayroll" cssClass="form-control" />
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
                                                                   onClickTopics="showDialogSearch" onCompleteTopics="closeDialog" onErrorTopics="errorDialogSearch">
                                                            <i class="fa fa-search"></i>
                                                            Search
                                                        </sj:submit>
                                                    </td>
                                                    <s:if test="%{payrollHeader.sdm}">
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
                                            <table id="showdata" width="100%">
                                                <tr>
                                                    <td align="center">

                                                        <%--<sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true"--%>
                                                                   <%--height="500" width="600" autoOpen="false"--%>
                                                                   <%--title="Payroll ">--%>
                                                            <%--<center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>--%>
                                                        <%--</sj:dialog>--%>

                                                        <s:set name="listOfPayroll" value="#session.listOfResult" scope="request" />
                                                        <display:table name="listOfPayroll" class=" tablePayroll table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag_payroll.action" export="true" id="row" pagesize="24" style="font-size:9">

                                                            <%--<display:column media="html" title="Approve Keuangan Unit">--%>
                                                                <%--<s:if test='#attr.row.statusApprove=="RK"'>--%>
                                                                    <%--<a href="javascript:;" bulan="<s:property value="%{#attr.row.bulan}"/>"--%>
                                                                       <%--tahun="<s:property value="%{#attr.row.tahun}"/>"--%>
                                                                       <%--branchId="<s:property value="%{#attr.row.branchId}"/>"--%>
                                                                       <%--branchName="<s:property value="%{#attr.row.branchName}"/>"--%>
                                                                       <%--tipe="<s:property value="%{#attr.row.tipe}"/>"--%>
                                                                       <%--class="item-approve-keu-unit">--%>
                                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>">--%>
                                                                    <%--</a>--%>
                                                                <%--</s:if>--%>
                                                                <%--<s:elseif test='#attr.row.statusApprove=="D"'>--%>
                                                                    <%--<img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">--%>
                                                                <%--</s:elseif>--%>
                                                            <%--</display:column>--%>
                                                            <%--<display:column media="html" title="Approve Keuangan Kanpus">--%>
                                                                <%--<s:if test='#attr.row.statusApprove=="K"'>--%>
                                                                    <%--<s:if test="%{payroll.kantorPusat}">--%>
                                                                        <%--<s:if test="%{payroll.keuanganKantorPusat}">--%>
                                                                            <%--<a href="javascript:;" bulan="<s:property value="%{#attr.row.bulan}"/>"--%>
                                                                               <%--tahun="<s:property value="%{#attr.row.tahun}"/>"--%>
                                                                               <%--branchId="<s:property value="%{#attr.row.branchId}"/>"--%>
                                                                               <%--branchName="<s:property value="%{#attr.row.branchName}"/>"--%>
                                                                               <%--tipe="<s:property value="%{#attr.row.tipe}"/>"--%>
                                                                               <%--class="item-edit">--%>
                                                                                <%--<img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">--%>
                                                                            <%--</a>--%>
                                                                        <%--</s:if>--%>
                                                                    <%--</s:if>--%>
                                                                <%--</s:if>--%>
                                                                <%--<s:elseif test='#attr.row.statusApprove=="D"'>--%>
                                                                    <%--<img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">--%>
                                                                <%--</s:elseif>--%>
                                                                <%--<s:elseif test='#attr.row.approvalAksFlag=="Y"'>--%>
                                                                    <%--<img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">--%>
                                                                <%--</s:elseif>--%>
                                                            <%--</display:column>--%>
                                                            <%--<display:column media="html" title="Approve SDM KP">--%>
                                                                <%--<s:if test='#attr.row.statusApprove=="S"'>--%>
                                                                    <%--<s:if test="%{payroll.kantorPusat}">--%>
                                                                        <%--<s:if test="%{payroll.sdm}">--%>
                                                                            <%--<a href="javascript:;" bulan="<s:property value="%{#attr.row.bulan}"/>"--%>
                                                                               <%--tahun="<s:property value="%{#attr.row.tahun}"/>"--%>
                                                                               <%--branchId="<s:property value="%{#attr.row.branchId}"/>"--%>
                                                                               <%--branchName="<s:property value="%{#attr.row.branchName}"/>"--%>
                                                                               <%--tipe="<s:property value="%{#attr.row.tipe}"/>"--%>
                                                                               <%--class="item-approve-sdm">--%>
                                                                                <%--<img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">--%>
                                                                            <%--</a>--%>
                                                                        <%--</s:if>--%>
                                                                    <%--</s:if>--%>
                                                                <%--</s:if>--%>
                                                                <%--<s:elseif test='#attr.row.statusApprove=="K"'>--%>
                                                                    <%--<img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">--%>
                                                                <%--</s:elseif>--%>
                                                                <%--<s:elseif test='#attr.row.statusApprove=="D"'>--%>
                                                                    <%--<img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">--%>
                                                                <%--</s:elseif>--%>
                                                                <%--<s:elseif test='#attr.row.approvalAksFlag=="Y"'>--%>
                                                                    <%--<img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">--%>
                                                                <%--</s:elseif>--%>
                                                            <%--</display:column>--%>
                                                            <%--<display:column media="html" title="Approve SDM Unit">--%>
                                                                <%--<s:if test="%{payroll.kantorPusat}">--%>
                                                                    <%--<s:if test='#attr.row.statusApprove=="K"'>--%>
                                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">--%>
                                                                    <%--</s:if>--%>
                                                                    <%--<s:elseif test='#attr.row.statusApprove=="S"'>--%>
                                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">--%>
                                                                    <%--</s:elseif>--%>
                                                                    <%--<s:elseif test='#attr.row.statusApprove=="D"'>--%>
                                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">--%>
                                                                    <%--</s:elseif>--%>
                                                                    <%--<s:elseif test='#attr.row.approvalAksFlag=="Y"'>--%>
                                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">--%>
                                                                    <%--</s:elseif>--%>
                                                                <%--</s:if>--%>
                                                                <%--<s:else>--%>
                                                                    <%--<s:if test='#attr.row.approvalFlag=="N"'>--%>
                                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_failure.ico"/>" name="icon_edit">--%>
                                                                    <%--</s:if>--%>
                                                                    <%--<s:elseif test='#attr.row.statusApprove=="U"'>--%>
                                                                        <%--<a href="javascript:;" bulan="<s:property value="%{#attr.row.bulan}"/>"--%>
                                                                           <%--tahun="<s:property value="%{#attr.row.tahun}"/>"--%>
                                                                           <%--branchId="<s:property value="%{#attr.row.branchId}"/>"--%>
                                                                           <%--branchName="<s:property value="%{#attr.row.branchName}"/>"--%>
                                                                           <%--tipe="<s:property value="%{#attr.row.tipe}"/>"--%>
                                                                           <%--class="item-approve-unit">--%>
                                                                            <%--<img border="0" src="<s:url value="/pages/images/icon_approval.ico"/>" name="icon_edit">--%>
                                                                        <%--</a>--%>
                                                                    <%--</s:elseif>--%>
                                                                    <%--<s:elseif test='#attr.row.statusApprove=="K"'>--%>
                                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">--%>
                                                                    <%--</s:elseif>--%>
                                                                    <%--<s:elseif test='#attr.row.statusApprove=="S"'>--%>
                                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">--%>
                                                                    <%--</s:elseif>--%>
                                                                    <%--<s:elseif test='#attr.row.statusApprove=="D"'>--%>
                                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">--%>
                                                                    <%--</s:elseif>--%>
                                                                    <%--<s:elseif test='#attr.row.approvalAksFlag=="Y"'>--%>
                                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_success.ico"/>" name="icon_edit">--%>
                                                                    <%--</s:elseif>--%>
                                                                <%--</s:else>--%>
                                                            <%--</display:column>--%>

                                                            <display:column media="html" title="SDM">
                                                                <s:if test="#attr.row.enableApprovalSdm">
                                                                    <s:if test="%{payrollHeader.sdm}">

                                                                        <a href="javascript:;"
                                                                           id="<s:property value="%{#attr.row.payrollHeaderId}"/>"
                                                                           bulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                           tahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                           branchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                           branchName="<s:property value="%{#attr.row.branchName}"/>"
                                                                           tipePayroll="<s:property value="%{#attr.row.tipePayroll}"/>"
                                                                           class="item-approve-sdm">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_approval_sdm.png"/>" name="icon_approval">
                                                                        </a>

                                                                    </s:if>
                                                                </s:if>
                                                            </display:column>

                                                            <display:column media="html" title="Keu">
                                                                <s:if test="#attr.row.enableApprovalAks">
                                                                    <s:if test="%{payrollHeader.keuangan}">

                                                                        <a href="javascript:;"
                                                                           id="<s:property value="%{#attr.row.payrollHeaderId}"/>"
                                                                           bulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                           tahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                           branchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                           branchName="<s:property value="%{#attr.row.branchName}"/>"
                                                                           tipePayroll="<s:property value="%{#attr.row.tipePayroll}"/>"
                                                                           class="item-approve-keu">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_approval_keu.png"/>" name="icon_approval">
                                                                        </a>

                                                                    </s:if>
                                                                </s:if>
                                                            </display:column>

                                                            <display:column media="html" title="View">
                                                                <s:if test="#attr.row.flagView">
                                                                    <s:url var="urlView" namespace="/payroll" action="view_payroll" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.payrollHeaderId"/></s:param>
                                                                        <s:param name="bulan"><s:property value="#attr.row.bulan"/></s:param>
                                                                        <s:param name="tahun"><s:property value="#attr.row.tahun"/></s:param>
                                                                        <s:param name="branchId"><s:property value="#attr.row.branchId"/></s:param>
                                                                        <s:param name="tipePayroll"><s:property value="#attr.row.tipePayroll"/></s:param>
                                                                        <s:param name="approvedSdm"><s:property value="#attr.row.approvalSdmFlag"/></s:param>
                                                                        <s:param name="approvedAks"><s:property value="#attr.row.approvalAksFlag"/></s:param>
                                                                    </s:url>
                                                                    <s:a href="%{urlView}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_search.png"/>" >
                                                                    </s:a>
                                                                </s:if>
                                                            </display:column>

                                                            <display:column media="html" title="Edit">
                                                                <s:if test="#attr.row.flagEdit">
                                                                    <s:if test="%{payrollHeader.sdm}">
                                                                        <s:url var="urlEdit" namespace="/payroll" action="edit_payroll" escapeAmp="false">
                                                                            <s:param name="id"><s:property value="#attr.row.payrollHeaderId"/></s:param>
                                                                            <s:param name="bulan"><s:property value="#attr.row.bulan"/></s:param>
                                                                            <s:param name="tahun"><s:property value="#attr.row.tahun"/></s:param>
                                                                            <s:param name="branchId"><s:property value="#attr.row.branchId"/></s:param>
                                                                            <s:param name="tipePayroll"><s:property value="#attr.row.tipePayroll"/></s:param>
                                                                        </s:url>
                                                                        <s:a href="%{urlEdit}" id="hrefEdit">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_edit.png"/>" name="icon_edit">
                                                                        </s:a>
                                                                    </s:if>
                                                                </s:if>

                                                                <%--<s:if test='#attr.row.statusApprove=="U"'>--%>
                                                                    <%--<s:a href="%{urlEdit}" id="hrefEdit">--%>
                                                                        <%--<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">--%>
                                                                    <%--</s:a>--%>
                                                                <%--</s:if>--%>
                                                                <%--<s:elseif test='#attr.row.statusApprove=="S"'>--%>
                                                                    <%--<s:if test="%{payroll.kantorPusat}">--%>
                                                                        <%--<s:if test="%{payroll.sdm}">--%>
                                                                            <%--<s:a href="%{urlEdit}" id="hrefEdit">--%>
                                                                                <%--<img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">--%>
                                                                            <%--</s:a>--%>
                                                                        <%--</s:if>--%>
                                                                    <%--</s:if>--%>
                                                                <%--</s:elseif>--%>
                                                            </display:column>

                                                            <%--<display:column media="html" title="Refresh">--%>
                                                                <%--<s:if test="#attr.row.flagRefresh">--%>
                                                                    <%--<s:if test="%{payrollHeader.sdm}">--%>
                                                                        <%--<s:url var="urlRefresh" namespace="/payroll" action="refresh_payroll" escapeAmp="false">--%>
                                                                            <%--<s:param name="id"><s:property value="#attr.row.payrollHeaderId"/></s:param>--%>
                                                                            <%--<s:param name="bulan"><s:property value="#attr.row.bulan"/></s:param>--%>
                                                                            <%--<s:param name="tahun"><s:property value="#attr.row.tahun"/></s:param>--%>
                                                                            <%--<s:param name="branchId"><s:property value="#attr.row.branchId"/></s:param>--%>
                                                                            <%--<s:param name="tipePayroll"><s:property value="#attr.row.tipePayroll"/></s:param>--%>
                                                                        <%--</s:url>--%>
                                                                        <%--<s:a href="%{urlRefresh}" id="hrefRefresh">--%>
                                                                            <%--<img border="0" src="<s:url value="/pages/images/icon_refresh.png"/>" name="icon_refresh">--%>
                                                                        <%--</s:a>--%>
                                                                    <%--</s:if>--%>
                                                                <%--</s:if>--%>
                                                            <%--</display:column>--%>

                                                            <display:column media="html" title="Print.Slip">
                                                                <s:if test="#attr.row.flagSlip">
                                                                    <a href="javascript:;"
                                                                       draftId="<s:property value="%{#attr.row.payrollHeaderId}"/>"
                                                                       draftBulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                       draftTahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                       draftBranchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                       draftBranchName="<s:property value="%{#attr.row.branchName}"/>"
                                                                       draftTipe="<s:property value="%{#attr.row.tipePayroll}"/>"
                                                                       class="item-slip">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_print.png"/>" name="icon_slip">
                                                                    </a>
                                                                </s:if>
                                                            </display:column>
                                                            <display:column media="html" title="Rekap Payroll">
                                                                <%--<s:if test="#attr.row.flagSlip">--%>
                                                                    <s:if test='#attr.row.tipePayroll == "PY"'>
                                                                        <a href="javascript:;"
                                                                           draftId="<s:property value="%{#attr.row.payrollHeaderId}"/>"
                                                                           draftBulan="<s:property value="%{#attr.row.bulan}"/>"
                                                                           draftTahun="<s:property value="%{#attr.row.tahun}"/>"
                                                                           draftBranchId="<s:property value="%{#attr.row.branchId}"/>"
                                                                           draftBranchName="<s:property value="%{#attr.row.branchName}"/>"
                                                                           draftTipe="<s:property value="%{#attr.row.tipePayroll}"/>"
                                                                           class="item-rekap">
                                                                            <img border="0" src="<s:url value="/pages/images/icons8-test-passed-25-2.png"/>" name="icon_slip">
                                                                        </a>
                                                                    </s:if>
                                                                <%--</s:if>--%>
                                                            </display:column>
                                                            <%--<display:column property="payrollHeaderId" title="Id"  />--%>
                                                            <display:column property="branchName" title="Unit"  />
                                                            <display:column property="bulan" title="Bulan"  />
                                                            <display:column property="tahun" title="Tahun"  />
                                                            <display:column property="tipePayrollName" title="Tipe"  />
                                                            <display:column property="stJumlahPegawai" title="Jml.Peg"  />
                                                            <%--<display:column style="text-align:right;" property="gajiKotor" title="Gaji.Kotor"  />--%>
                                                            <display:column style="text-align:right;" property="gajiBersih" title="Gaji.Bersih"  />
                                                            <display:column property="approvalSdmFlag" title="Appv.Sdm" />
                                                            <display:column property="stApprovalSdmDate" title="Tgl.Appv.Sdm " />
                                                            <display:column property="approvalAksFlag" title="Appv.Keu" />
                                                            <display:column property="stApprovalAksDate" title="Tgl.Appv.Keu " />
                                                            <display:column property="noJurnal" title="No.Jurnal" />
                                                            <display:column property="statusPayroll" title="Status" />
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

                                        <sj:dialog id="waiting_dialog_search" openTopics="showDialogSearch" closeTopics="closeDialog" modal="true"
                                                   resizable="false"
                                                   height="350" width="600" autoOpen="false" title="Searching ...">
                                            Please don't close this window, server is processing your request ...
                                            </br>
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

                                        <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                            'OK':function() { $('#error_dialog').dialog('close'); }
                                                                        }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage"></p>
                                                </label>
                                            </div>
                                        </sj:dialog>

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
<%--<div id="modal-approve" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:400px;">--%>
        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<form class="form-horizontal" id="myForm">--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Bulan</label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<input readonly type="text" class="form-control nip" id="approveBulan" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tahun</label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<input readonly type="text" class="form-control nip" id="approveTahun" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Unit</label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<input readonly type="text" class="form-control nip" id="approveBranch" name="nip">--%>
                            <%--<input readonly type="text" class="form-control nip" style="display: none" id="approveBranchId" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group" style="display: none">--%>
                        <%--<label class="control-label col-sm-4" >Tipe</label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<input readonly type="text" class="form-control nip"  id="tipeId2" >--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</form>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" id="btnApprove" class="btn btn-success"><i class="fa fa-check"></i> Approve</a>--%>
                <%--<a type="button" id="btnNotApprove" class="btn btn-danger"><i class="fa fa-close"></i> Not Approve</a>--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div id="modal-approve-unit" class="modal fade modal2" role="dialog">--%>
    <%--<div class="modal-dialog " style="width:400px;">--%>
        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title"></h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body" >--%>
                <%--<form class="form-horizontal" id="myFormApproveUnit">--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Bulan</label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<input readonly type="text" class="form-control nip" id="approveBulanUnit" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Tahun</label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<input readonly type="text" class="form-control nip" id="approveTahunUnit" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-4" >Unit</label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<input readonly type="text" class="form-control nip" id="approveBranchUnit" name="nip">--%>
                            <%--<input readonly type="text" class="form-control nip" style="display: none" id="approveBranchIdUnit" name="nip">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group" style="display: none">--%>
                        <%--<label class="control-label col-sm-4" >Tipe</label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<input readonly type="text" class="form-control nip"  id="tipeId2Unit" >--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</form>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<a type="button" id="btnApproveUnit" class="btn btn-success"><i class="fa fa-check"></i> Approve</a>--%>
                <%--<a type="button" id="btnNotApproveUnit" class="btn btn-danger"><i class="fa fa-close"></i> Not Approve</a>--%>
                <%--<a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

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
                        <label class="control-label col-sm-4" >Pay.Header Id</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="approveIdPayrollHeaderSdm">
                        </div>
                    </div>
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
                        <label class="control-label col-sm-4" >Tipe Payroll</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip"  id="tipePayrollSdm" >
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a type="button" id="btnApproveSdm" class="btn btn-success"><i class="fa fa-check"></i> Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
            </div>
        </div>
    </div>
</div>

<div id="modal-approve-keu" class="modal fade" role="dialog">
    <div class="modal-dialog " style="width:400px;">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" >
                <form class="form-horizontal" id="formApprovalKeu">
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Pay.Header Id</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="approveIdPayrollHeaderKeu">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Bulan</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="approveBulanKeu">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tahun</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="approveTahunKeu">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Unit</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip" id="approveBranchKeu">
                            <input readonly type="text" class="form-control nip" id="approveBranchIdKeu" style="display: none">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Tipe Payroll</label>
                        <div class="col-sm-8">
                            <input readonly type="text" class="form-control nip"  id="tipePayrollKeu" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" >Keterangan</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control nip" id="keteranganKeu" >
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <a type="button" id="btnApproveKeu" class="btn btn-success"><i class="fa fa-check"></i> Approve</a>
                <a type="button" id="btnNotApproveKeu" class="btn btn-danger"><i class="fa fa-close"></i> Not Approve</a>
                <a type="button" class="btn btn-default" data-dismiss="modal"> Close</a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-loading-dialog-search-payroll">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Saving ...
                </h4>
            </div>
            <div class="modal-body">
                <div id="waiting-content-search-payroll" style="text-align: center">
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

                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_fin_waiting_search_payroll">
                    <h4><i class="icon fa fa-ban"></i> Warning! Found Error when saving, please check to your admin...</h4>
                    <p id="msg_fin_error_waiting_search_payroll"></p>
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

<div class="modal fade" id="modal-success-search-payroll">
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
                <button type="button" class="btn btn-sm btn-success" data-dismiss="modal" id="ok_con"><i class="fa fa-check"></i> Ok
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        function showDialogPy(tipe) {
            if (tipe == "loading"){
                $("#modal-loading-dialog-search-payroll").modal('show');
            }
            if (tipe == "error"){
                $("#modal-loading-dialog-search-payroll").modal('show');
                $("#waiting-content-search-payroll").hide();
                $("#warning_fin_waiting_search_payroll").show();
                $("#msg_fin_error_waiting_search_payroll").text("");
            }
            if (tipe == "success"){
                $("#modal-loading-dialog-search-payroll").modal('hide');
                $("#modal-success-search-payroll").modal('show');
            }
        }

        var tipe = document.getElementById("tipe").value;
        if(tipe != "PR" || tipe != "T" || tipe != "PD"){
            $('#printSlip').hide;
        }else{
            $('#printSlip').show;
        }

        // $('#btnApprove').click(function(){
        //     if (confirm('Apakah Anda ingin Mesetujui Data Payroll?')) {
        //         var branchId = document.getElementById("approveBranchId").value;
        //         var bulan = document.getElementById("approveBulan").value;
        //         var tahun = document.getElementById("approveTahun").value;
        //         var tipe = document.getElementById("tipeId2").value;
        //         var statusApprove = "Y";
        //         showDialog("loading");
        //         dwr.engine.setAsync(true);
        //         PayrollAction.approvePayroll(branchId, bulan, tahun, statusApprove, tipe, function(listdata){
        //             dwr.engine.setAsync(false);
        //             $('#modal-approve').modal('hide');
        //             showDialog("success");
        //         });
        //     }
        // });

        $('#ok_con').click(function () {
            window.location.href="<s:url action='initForm_payroll.action'/>";
        })

        $('#btnApproveKeu').click(function(){
            if (confirm('Apakah Anda ingin Setuju dengan Data Payroll ?')) {
                var payrollHeaderId = document.getElementById("approveIdPayrollHeaderKeu").value;
                var branchId = document.getElementById("approveBranchIdKeu").value;
                var bulan = document.getElementById("approveBulanKeu").value;
                var tahun = document.getElementById("approveTahunKeu").value;
                var tipe = document.getElementById("tipePayrollKeu").value;
                var statusApprove = "Y";
                var keteranganKeu = "";

                showDialogPy("loading");
                dwr.engine.setAsync(true);
                PayrollAction.approvePayrollKeu(payrollHeaderId, branchId, bulan, tahun, statusApprove, tipe, keteranganKeu, function(status){
                    dwr.engine.setAsync(false);
                    if (status == '00') {
                        $('#modal-approve-keu').modal('hide');
                        showDialogPy("success");
                    } else {
                        showDialogPy("error");
                    }
                });
            }
        });

        $('#btnNotApproveKeu').click(function(){
            if (confirm('Apakah Anda TIDAK Setuju dengan Data Payroll ?')) {
                var payrollHeaderId = document.getElementById("approveIdPayrollHeaderKeu").value;
                var branchId = document.getElementById("approveBranchIdSdm").value;
                var bulan = document.getElementById("approveBulanSdm").value;
                var tahun = document.getElementById("approveTahunSdm").value;
                var tipe = document.getElementById("tipePayrollSdm").value;
                var keteranganKeu = document.getElementById("keteranganKeu").value;
                var statusApprove = "N";

                showDialogPy("loading");
                dwr.engine.setAsync(true);
                PayrollAction.approvePayrollKeu(payrollHeaderId, branchId, bulan, tahun, statusApprove, tipe, keteranganKeu, function(status){
                    dwr.engine.setAsync(false);
                    if (status == '00') {
                        alert('Payroll Berhasil Tidak Di Approve');
                        $('#modal-approve-keu').modal('hide');
                        showDialogPy("success");
                        $('#myForm')[0].reset();
                        window.location.href="<s:url action='initForm_payroll.action'/>";
                    } else {
                        showDialogPy("error");
                    }

                });
            }
        });

        // $('#btnApproveUnit').click(function(){
        //     if (confirm('Apakah Anda ingin Mesetujui Data Payroll?')) {
        //         var branchId = document.getElementById("approveBranchIdUnit").value;
        //         var bulan = document.getElementById("approveBulanUnit").value;
        //         var tahun = document.getElementById("approveTahunUnit").value;
        //         var tipe = document.getElementById("tipeId2Unit").value;
        //         var statusApprove = "Y";
        //
        //         PayrollAction.approvePayrollUnit(branchId, bulan, tahun, statusApprove, tipe, function(listdata){
        //             dwr.engine.setAsync(false);
        //             $('#modal-approve').modal('hide');
        //             showDialog("success");
        //         });
        //     }
        // });

        $('#btnApproveSdm').click(function(){
            if (confirm('Apakah Anda ingin Mesetujui Data Payroll?')) {
                var payrollHeaderId = document.getElementById("approveIdPayrollHeaderSdm").value;
                var branchId = document.getElementById("approveBranchIdSdm").value;
                var bulan = document.getElementById("approveBulanSdm").value;
                var tahun = document.getElementById("approveTahunSdm").value;
                var tipe = document.getElementById("tipePayrollSdm").value;

                showDialogPy("loading");
                dwr.engine.setAsync(true);
                PayrollAction.approvePayrollSdm(payrollHeaderId, branchId, bulan, tahun, tipe, function(status){
                    dwr.engine.setAsync(false);
                    if (status == '00') {
                        $('#modal-approve-sdm').modal('hide');
                        showDialogPy("success");
                    } else {
                        showDialogPy("error");
                    }

                });
            }
        });


        <%--$('#btnNotApproveUnit').click(function(){--%>
            <%--if (confirm('Apakah Anda ingin menolak Data Payroll?')) {--%>
                <%--var branchId = document.getElementById("approveBranchIdUnit").value;--%>
                <%--var bulan = document.getElementById("approveBulanUnit").value;--%>
                <%--var tahun = document.getElementById("approveTahunUnit").value;--%>
                <%--var tipe = document.getElementById("tipeId2Unit").value;--%>
                <%--var statusApprove = "N";--%>

                <%--PayrollAction.approvePayrollUnit(branchId, bulan, tahun, statusApprove, tipe, function(listdata){--%>
                    <%--alert('Payroll Berhasil Tidak Di Approve');--%>
                    <%--$('#modal-approve').modal('hide');--%>
                    <%--$('#myForm')[0].reset();--%>
                    <%--window.location.href="<s:url action='initForm_payroll.action'/>";--%>
                <%--});--%>
            <%--}--%>
        <%--});--%>

        // $('#btnPrint').click(function(){
        //     if (confirm('Apakah Anda ingin mencetak draft Payroll?')) {
        //         var branchId = document.getElementById("draftBranchId").value;
        //         var bulan = document.getElementById("draftBulan").value;
        //         var tahun = document.getElementById("draftTahun").value;
        //         var tipe = document.getElementById("draftTipeId2").value;
        //         var tipePrint = document.getElementById("draftTipePrint").value;
        //         var statusPegawai = document.getElementById("draftStatusPegawai").value;
        //
        //         window.location.href = 'searchReportDraft_payroll?branchId='+branchId+'&bulan='+bulan+'&tahun='+tahun+'&statusPegawai='+statusPegawai
        //         +'&tipe='+tipePrint;
        //     }
        // });

        // $('#btnNotApprove').click(function(){
        //     if (confirm('Apakah Anda ingin menolak Data Payroll?')) {
        //         var branchId = document.getElementById("approveBranchId").value;
        //         var bulan = document.getElementById("approveBulan").value;
        //         var tahun = document.getElementById("approveTahun").value;
        //         var tipe = document.getElementById("tipeId2").value;
        //         var statusApprove = "N";
        //
        //         PayrollAction.approvePayroll(branchId, bulan, tahun, statusApprove, tipe, function(listdata){
        //             alert('Payroll Berhasil Ditolak');
        //             $('#modal-approve').modal('hide');
        //             $('#myForm')[0].reset();
        //             location.reload();
        //         });
        //     }
        // });

        // $('.tablePayroll').on('click', '.item-edit', function(){
        //     var tipe = $(this).attr('tipe');
        //     var bulan = $(this).attr('bulan');
        //     var tahun = $(this).attr('tahun');
        //     var branchId = $(this).attr('branchId');
        //     var branchName = $(this).attr('branchName');
        //
        //     $('#approveBulan').val(bulan);
        //     $('#approveTahun').val(tahun);
        //     $('#approveBranch').val(branchName);
        //     $('#approveBranchId').val(branchId);
        //     $('#tipeId2').val(tipe);
        //
        //     PayrollAction.getApproval(branchId, bulan, tahun, tipe, function(listdata){
        //         var hasil = "";
        //
        //         if(listdata.approvalFlag == 'Y'){
        //             hasil = "Disetujui";
        //         } else if(listdata.approvalFlag == 'N'){
        //             hasil = "Tidak Disetujui";
        //         }
        //
        //
        //         $('#approveStatus').val(hasil);
        //         $('#approveTanggal').val(listdata.stApprovalDate);
        //         $('#modal-approve').find('.modal-title').text('Approve Payroll');
        //         $('#modal-approve').modal('show');
        //     });
        // });

        // $('.tablePayroll').on('click', '.item-approve-sdm', function(){
        //     var tipe = $(this).attr('tipe');
        //     var bulan = $(this).attr('bulan');
        //     var tahun = $(this).attr('tahun');
        //     var branchId = $(this).attr('branchId');
        //     var branchName = $(this).attr('branchName');
        //
        //     $('#approveBulanUnit').val(bulan);
        //     $('#approveTahunUnit').val(tahun);
        //     $('#approveBranchUnit').val(branchName);
        //     $('#approveBranchIdUnit').val(branchId);
        //     $('#tipeId2Unit').val(tipe);
        //
        //     PayrollAction.getApprovalUnit(branchId, bulan, tahun, tipe, function(listdata){
        //         var hasil = "";
        //         if(listdata.approvalFlag == 'Y'){
        //             hasil = "Disetujui";
        //         } else if(listdata.approvalFlag == 'N'){
        //             hasil = "Tidak Disetujui";
        //         }
        //         $('#approveStatusUnit').val(hasil);
        //         $('#approveTanggalUnit').val(listdata.stApprovalDate);
        //         $('#modal-approve-unit').find('.modal-title').text('Approve Payroll SDM');
        //         $('#modal-approve-unit').modal('show');
        //     });
        // });

        $('.tablePayroll').on('click', '.item-approve-sdm', function(){
            var idPayrollHeader = $(this).attr('id');
            var tipePayroll = $(this).attr('tipePayroll');
            var bulan = $(this).attr('bulan');
            var tahun = $(this).attr('tahun');
            var branchId = $(this).attr('branchId');
            var branchName = $(this).attr('branchName');

            $('#approveIdPayrollHeaderSdm').val(idPayrollHeader);
            $('#approveBulanSdm').val(bulan);
            $('#approveTahunSdm').val(tahun);
            $('#approveBranchSdm').val(branchName);
            $('#approveBranchIdSdm').val(branchId);
            $('#tipePayrollSdm').val(tipePayroll);
            $('#modal-approve-sdm').find('.modal-title').text('Approve Payroll SDM');
            $('#modal-approve-sdm').modal('show');

            // PayrollAction.getApprovalSdm(branchId, bulan, tahun, tipe, function(listdata){
            //     var hasil = "";
            //     if(listdata.approvalFlag == 'Y'){
            //         hasil = "Disetujui";
            //     } else if(listdata.approvalFlag == 'N'){
            //         hasil = "Tidak Disetujui";
            //     }
            //     $('#approveStatusSdm').val(hasil);
            //     $('#approveTanggalSdm').val(listdata.stApprovalDate);
            // });
        });

        $('.tablePayroll').on('click', '.item-approve-keu', function(){
            var idPayrollHeader = $(this).attr('id');
            var tipePayroll = $(this).attr('tipePayroll');
            var bulan = $(this).attr('bulan');
            var tahun = $(this).attr('tahun');
            var branchId = $(this).attr('branchId');
            var branchName = $(this).attr('branchName');

            $('#approveIdPayrollHeaderKeu').val(idPayrollHeader);
            $('#approveBulanKeu').val(bulan);
            $('#approveTahunKeu').val(tahun);
            $('#approveBranchKeu').val(branchName);
            $('#approveBranchIdKeu').val(branchId);
            $('#tipePayrollKeu').val(tipePayroll);
            $('#modal-approve-keu').find('.modal-title').text('Approve Payroll Keuangan');
            $('#modal-approve-keu').modal('show');

            // PayrollAction.getApproval(branchId, bulan, tahun, tipe, function(listdata){
            //     var hasil = "";
            //     if(listdata.approvalFlag == 'Y'){
            //         hasil = "Disetujui";
            //     } else if(listdata.approvalFlag == 'N'){
            //         hasil = "Tidak Disetujui";
            //     }
            //     $('#approveStatusKeuUnit').val(hasil);
            //     $('#approveTanggalKeuUnit').val(listdata.stApprovalDate);
            // });
        });

        // $('.tablePayroll').on('click', '.item-draft', function(){
        //     var tipe = $(this).attr('draftTipe');
        //     var bulan = $(this).attr('draftBulan');
        //     var tahun = $(this).attr('draftTahun');
        //     var branchId = $(this).attr('draftBranchId');
        //     var branchName = $(this).attr('draftBranchName');
        //
        //     $('#draftBulan').val(bulan);
        //     $('#draftTahun').val(tahun);
        //     $('#draftBranch').val(branchName);
        //     $('#draftBranchId').val(branchId);
        //     $('#draftTipeId2').val(tipe);
        //
        //     $('#modal-draft').find('.modal-title').text('Print Draft');
        //     $('#modal-draft').modal('show');
        // });

        $('.tablePayroll').on('click', '.item-slip', function(){
            var idPayrollHeader = $(this).attr('draftId');
            var bulan = $(this).attr('draftBulan');
            var tahun = $(this).attr('draftTahun');
            var branchId = $(this).attr('draftBranchId');
            var branchName = $(this).attr('draftBranchName');
            var tipePayroll = $(this).attr('draftTipe');

            if (confirm('Apakah Anda ingin mencetak slip?')) {
                window.location.href = 'printReportPayrollByBranch_payroll?branchId='+branchId+'&tahun='+tahun+'&bulan='+bulan+'&tipePayroll='+tipePayroll+'&branchName='+branchName+'&idPayrollHeader='+idPayrollHeader;
            }
        });

        $('.tablePayroll').on('click', '.item-rekap', function(){
            var idPayrollHeader = $(this).attr('draftId');
            var bulan = $(this).attr('draftBulan');
            var tahun = $(this).attr('draftTahun');
            var branchId = $(this).attr('draftBranchId');
            var branchName = $(this).attr('draftBranchName');
            var tipePayroll = $(this).attr('draftTipe');

            if (confirm('Apakah Anda ingin mencetak detail rekap payroll?')) {
                window.location.href = 'payrollRekapReportExcel_payroll?branchId='+branchId+'&tahun='+tahun+'&bulan='+bulan;
            }
        });

        // $('#printPayroll').click(function(){
        //     var periodeBln1 = document.getElementById("bulanPayroll1").value;
        //     var periodeThn1 = document.getElementById("tahunPayroll1").value;
        //     var periodeBln2 = document.getElementById("bulanPayroll2").value;
        //     var periodeThn2 = document.getElementById("tahunPayroll2").value;
        //     var unit = document.getElementById("unitId").value;
        //
        //     window.location.href = 'printReportPayrollBulan_payroll?branchId='+unit+'&tahun='+periodeThn1+'&bulan='+periodeBln1
        //             +'&tahun2='+periodeThn2+'&bulan2='+periodeBln2;
        // });
    });
</script>
