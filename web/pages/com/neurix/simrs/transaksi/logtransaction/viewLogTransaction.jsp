<%--
  Created by IntelliJ IDEA.
  User: mgi
  Date: 07/04/21
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>
<html>
<head>
    <script type="text/javascript">
        function callSearch() {
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {

            if (confirm('Do you want to delete this record?')) {
                event.originalEvent.options.submit = true;
                $.publish('showDialog');
            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
            }
        });

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $.subscribe('errorDialog', function (event, data) {
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };
    </script>

</head>

<body bgcolor="#FFFFFF">
<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="formViewTrxDelete" method="post" theme="simple" namespace="/logtransaction"
                    action="view_logtransaction" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>

                <legend align="left">Detail Log Transaction</legend>

                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>
                <%--<div id="panelView">--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-6">--%>
                            <table>
                                <tr>
                                    <td>
                                        <label class="control-label">
                                            <small>Log Id :</small>
                                        </label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield id="delLogTrxId" readonly="true"
                                                         name="logTransaction.pgLogTrxId" required="true"
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
                                            <s:textfield id="delTrxId" readonly="true" name="logTransaction.trxId"
                                                         required="true" cssClass="form-control"/>
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
                                            <s:textfield id="delTipeTrx" readonly="true" name="logTransaction.tipeTrx"
                                                         required="true" cssClass="form-control"/>
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
                                            <s:select list="#{'BNI':'BNI', 'BSI':'BSI'}" id="delBankName"
                                                      name="logTransaction.bankName"
                                                      readonly="true" headerKey="" headerValue="[all bank]"
                                                      cssClass="form-control"/>
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
                                            <s:textfield id="delNoVA" readonly="true"
                                                         name="logTransaction.noVirtualAccount" required="true"
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
                                            <s:textfield id="delNoRekamMedik" readonly="true"
                                                         name="logTransaction.noRekamMedik" required="true"
                                                         cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label">
                                            <small>Transaction Amount :</small>
                                        </label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield id="delTrxAmount" readonly="true"
                                                         name="logTransaction.trxAmount" required="true"
                                                         cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label">
                                            <small>Name Person :</small>
                                        </label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield id="delNamePerson" readonly="true"
                                                         name="logTransaction.namePerson" required="true"
                                                         cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label">
                                            <small>Address Person :</small>
                                        </label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield id="delAddress" readonly="true"
                                                         name="logTransaction.addressPerson" required="true"
                                                         cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label">
                                            <small>Phone Person :</small>
                                        </label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield id="delPhone" readonly="true" name="logTransaction.phonePerson"
                                                         required="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label">
                                            <small>Email Person :</small>
                                        </label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textfield id="delEmail" readonly="true" name="logTransaction.emailPerson"
                                                         required="true" cssClass="form-control"/>
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
                                            <s:select list="#{'in':'In', 'out':'Out'}" id="delStatusTrx"
                                                      name="logTransaction.status"
                                                      readonly="true" headerKey="" headerValue="[all status]"
                                                      cssClass="form-control" onchange="dateRange()"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label">
                                            <small>Message :</small>
                                        </label>
                                    </td>
                                    <td>
                                        <table>
                                            <s:textarea id="delMessage" readonly="true" name="logTransaction.message"
                                                        required="true" cssClass="form-control"/>
                                        </table>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label class="control-label">
                                            <small>Sent Date :</small>
                                        </label>
                                    </td>
                                    <td>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="delSentDate" readonly="true"
                                                         name="logTransaction.stSentDate"
                                                         cssClass="form-control pull-right"
                                                         required="false" cssStyle=""/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label">
                                            <small>Received Date :</small>
                                        </label>
                                    </td>
                                    <td>
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="delReceivedDate" readonly="true"
                                                         name="logTransaction.stReceivedDate"
                                                         cssClass="form-control pull-right"
                                                         required="false" cssStyle=""/>
                                        </div>
                                    </td>
                                </tr>

                            </table>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>


                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                            <%--<sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="formLogTrxDelete" id="save" name="save"--%>
                            <%--onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"--%>
                            <%--onSuccessTopics="successDialog" onErrorTopics="errorDialog" >--%>
                            <%--<i class="fa fa-trash"></i>--%>
                            <%--Delete--%>
                            <%--</sj:submit>--%>
                        <button type="button" id="cancel" class="btn btn-danger" onclick="cancelBtn();">
                            <i class="fa fa-refresh"/> Cancel
                        </button>
                    </div>
                </div>


                <div id="actions" class="form-actions">
                    <table>
                        <tr>
                            <div id="crud">
                                <td>
                                    <table>
                                        <sj:dialog id="waiting_dialog" openTopics="showDialog"
                                                   closeTopics="closeDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false"
                                                   title="Saving ...">
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
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                              'OK':function() {
                                                                    //$(this).dialog('close');
                                                                      callSearch();
                                                                   }
                                                            }"
                                        >
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                                 name="icon_success">
                                            Record has been saved successfully.
                                        </sj:dialog>

                                        <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close'); }
                                                                    }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                         name="icon_error"> System Found : <p id="errorMessage"></p>
                                                </label>
                                            </div>
                                        </sj:dialog>

                                        <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog"
                                                   modal="true" resizable="false"
                                                   height="280" width="500" autoOpen="false" title="Warning"
                                                   buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                    }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                         name="icon_error"> Please check this field :
                                                    <br/>
                                                    <center>
                                                        <div id="errorValidationMessage"></div>
                                                    </center>
                                                </label>
                                            </div>
                                        </sj:dialog>
                                    </table>
                                </td>
                            </div>
                        </tr>
                    </table>
                </div>
            </s:form>
        </td>
    </tr>
</table>
</body>
</html>
