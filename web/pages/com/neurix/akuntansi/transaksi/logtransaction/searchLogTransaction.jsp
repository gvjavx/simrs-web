<%--
  Created by IntelliJ IDEA.
  User: mgi
  Date: 07/04/21
  Time: 10:37
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
</head>

<body class="hold-transition skin-blue sidebar-mini">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Log Transaction Payment Gateway
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <table width="100%" align="center">
            <tr>
                <td align="center">
                    <s:form id="logTransactionForm" method="post" theme="simple" namespace="/logtransaction"
                            action="search_logtransaction.action" cssClass="well form-horizontal">

                        <s:hidden name="addOrEdit"/>
                        <s:hidden name="delete"/>

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
                    <label class="control-label">
                        <small>Log ID :</small>
                    </label>
                </td>
                <td>
                    <table>
                        <s:textfield type="number" id="logTrxId" name="logTransaction.pgLogTrxId" required="true"
                                     cssClass="form-control"/>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label">
                        <small>Transaction ID :</small>
                    </label>
                </td>
                <td>
                    <table>
                        <s:textfield id="trxId" name="logTransaction.trxId" required="true" cssClass="form-control"/>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label">
                        <small>Tipe Transaction :</small>
                    </label>
                </td>
                <td>
                    <table>
                        <s:select list="#{'create':'Create', 'inquiry':'Inquiry', 'payment' : 'Payment'}" id="tipeTrx" name="logTransaction.tipeTrx"
                                  headerKey="" headerValue="[all type]" cssClass="form-control"/>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label">
                        <small>Bank Name :</small>
                    </label>
                </td>
                <td>
                    <table>
                        <s:select list="#{'BNI':'BNI', 'BSI':'BSI'}" id="bankName" name="logTransaction.bankName"
                                  headerKey="" headerValue="[all bank]" cssClass="form-control"/>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label">
                        <small>No Virtual Account :</small>
                    </label>
                </td>
                <td>
                    <table>
                        <s:textfield id="noVA" name="logTransaction.noVirtualAccount" required="true"
                                     cssClass="form-control"/>
                    </table>
                </td>
            </tr>

            <tr>
                <td>
                    <label class="control-label">
                        <small>No Rekam Medik :</small>
                    </label>
                </td>
                <td>
                    <table>
                        <s:textfield id="noRekamMedik" name="logTransaction.noRekamMedik" required="true"
                                     cssClass="form-control"/>
                    </table>
                </td>
            </tr>


            <tr>
                <td>
                    <label class="control-label">
                        <small>Channel :</small>
                    </label>
                </td>
                <td>
                    <table>
                        <s:textfield id="channel" name="logTransaction.channel" required="true"
                                     cssClass="form-control"/>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label">
                        <small>Invoice Date :</small>
                    </label>
                </td>
                <td>
                    <div class="input-group date">
                        <div class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </div>
                        <s:textfield id="invDateFrom" name="logTransaction.stInvDateFrom"
                                     cssClass="form-control pull-right"
                                     required="false" size="7" cssStyle="" onchange="rangeInvDate()"/>
                        <div class="input-group-addon">
                            s/d
                        </div>
                        <div class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </div>
                        <s:textfield id="invDateTo" name="logTransaction.stInvDateTo" cssClass="form-control pull-right"
                                     required="false" size="7" cssStyle="" onchange="rangeInvDate()"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label">
                        <small>Invoice Number :</small>
                    </label>
                </td>
                <td>
                    <table>
                        <s:textfield id="invoiceNumber" name="logTransaction.invoiceNumber" required="true"
                                     cssClass="form-control"/>
                    </table>
                </td>
            </tr>

            <tr>
                <td>
                    <label class="control-label">
                        <small>Status :</small>
                    </label>
                </td>
                <td>
                    <table>
                        <s:select list="#{'in':'In', 'out':'Out'}" id="statusTrx" name="logTransaction.status"
                                  headerKey="" headerValue="[all status]" cssClass="form-control" onchange="labelSrcDate()"/>
                    </table>
                </td>
            </tr>

            <tr>
                <td>
                    <label class="control-label">
                        <small id="searchDate">Sent/Received Date :</small>
                    </label>
                </td>
                <td>
                    <div class="input-group date">
                        <div class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </div>
                        <s:textfield id="dateStr" name="logTransaction.stDateStr" cssClass="form-control pull-right"
                                     required="false" size="7" cssStyle="" onchange="validateRange()"/>
                        <div class="input-group-addon">
                            s/d
                        </div>
                        <div class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </div>
                        <s:textfield id="dateEnd" name="logTransaction.stDateEnd" cssClass="form-control pull-right"
                                     required="false" size="7" cssStyle="" onchange="validateRange()"/>
                    </div>
                </td>
            </tr>

        </table>

        <br>

        <div id="actions" class="form-actions">
            <table align="center">
                <tr>
                    <td>
                        <sj:submit type="button" cssClass="btn btn-primary" formIds="logTransactionForm" id="search"
                                   name="search"
                                   onClickTopics="showDialog" onCompleteTopics="closeDialog">
                            <i class="fa fa-search"></i>
                            Search
                        </sj:submit>
                    </td>

                    <td>
                        <button type="button" class="btn btn-danger"
                                onclick="window.location.href='<s:url action="initForm_logtransaction"/>'">
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
                                   height="600" width="900" autoOpen="false"
                                   title="Log Transaction">
                            <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>"
                                         alt="Loading..."/></center>
                        </sj:dialog>

                        <s:set name="listOfLogTransaction" value="#session.listOfResult" scope="request"/>
                        <display:table name="listOfLogTransaction"
                                       class="table table-condensed table-striped table-hover"
                                       requestURI="paging_displaytag_logtransaction.action" export="true" id="row"
                                       pagesize="14" style="font-size:10">

                            <display:column media="html" title="View" style="text-align:center;font-size:9">
                                <s:url var="urlView" namespace="/logtransaction" action="view_logtransaction"
                                       escapeAmp="false">
                                    <s:param name="id"><s:property value="#attr.row.pgLogTrxId"/></s:param>
                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                </s:url>
                                <sj:a onClickTopics="showDialogMenu" href="%{urlView}">
                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">
                                </sj:a>

                            </display:column>
                            <display:column property="pgLogTrxId" sortable="true" title="Log ID"/>
                            <display:column property="trxId" sortable="true" title="Trx ID"/>
                            <display:column property="tipeTrx" sortable="true" title="Tipe Transaction"/>
                            <display:column property="bankName" sortable="true" title="Bank Name"/>
                            <display:column property="noVirtualAccount" sortable="true" title="No Virtual Account"/>
                            <display:column property="noRekamMedik" sortable="true" title="No Rekam Medik"/>
                            <display:column property="trxAmount" sortable="true" title="Transaction Amount"/>
                            <display:column property="namePerson" sortable="true" title="Person Name"/>
                            <display:column property="status" sortable="true" title="Status"/>
                            <display:column property="statusBank" sortable="true" title="Status Bank"/>
                            <display:column property="channel" sortable="true" title="Channel"/>
                            <display:column property="invoiceDate" sortable="true" title="Inv. Date"/>
                            <display:column property="invoiceNumber" sortable="true" title="Inv. Number"/>
                            <display:column property="sentDate" sortable="true" title="Send Date"/>
                            <display:column property="receivedDate" sortable="true" title="Received Date"/>
                            <display:setProperty name="paging.banner.item_name">LogTransaction</display:setProperty>
                            <display:setProperty name="paging.banner.items_name">LogTransaction</display:setProperty>
                            <display:setProperty name="export.excel.filename">LogTransaction.xls</display:setProperty>
                            <display:setProperty name="export.csv.filename">LogTransaction.csv</display:setProperty>
                            <display:setProperty name="export.pdf.filename">LogTransaction.pdf</display:setProperty>
                        </display:table>
                    </td>
                </tr>
            </table>
        </center>
        </s:form>
        </td>
        </tr>
        </table>

        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

</body>
</html>

<script>
    $(document).ready(function () {
        $('#dateStr').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#dateEnd').datepicker({
            dateFormat: 'dd-mm-yy'
        });

        $('#invDateFrom').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#invDateTo').datepicker({
            dateFormat: 'dd-mm-yy'
        });
    })

    function validateRange() {
        var start = $('#dateStr').val().split('-');
        var end = $('#dateEnd').val().split('-');

        var dateStr = new Date(start[2], start[1] - 1, start[0]);
        var dateEnd = new Date(end[2], end[1] - 1, end[0]);

        if (start != '' && end != '') {
            if (dateStr > dateEnd) {
                alert("Range of Date is Wrong!");
                $('#dateEnd').val('');
            }
        }
    }

    function rangeInvDate() {
        var start = $('#invDateFrom').val().split('-');
        var end = $('#invDateTo').val().split('-');

        var dateFrom = new Date(start[2], start[1] - 1, start[0]);
        var dateTo = new Date(end[2], end[1] - 1, end[0]);

        if (start != '' && end != '') {
            if (dateFrom > dateTo) {
                alert("Range of Invoice Date is Wrong!");
                $('#invDateTo').val('');
            }
        }
    }

    function labelSrcDate(){
        var status = $('#statusTrx').val()

        if(status == 'in'){
            $('#searchDate').text("Received Date :")
        }else if(status == 'out'){
            $('#searchDate').text("Sent Date :");
        }else{
            $('#searchDate').text("Sent/Received Date :");
        }
    }
</script>