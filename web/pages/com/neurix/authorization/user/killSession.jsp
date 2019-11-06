<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>

    <%--<link href="<s:url value="/pages/bootstrap/css/bootstrap.css"/>" rel="stylesheet" media="screen">--%>

    <script type="text/javascript">


        $.subscribe('beforeProcessSave', function (event, data) {

            var sessionId = '<s:property value="userSessionLog.stId"/>';

            if (sessionId != '') {

                if (confirm('Do you want to Kill User Session ?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialogDelete');

                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }

            } else {

                event.originalEvent.options.submit = false;

                var msg = "";
                if (sessionId == '') {
                    msg = 'Field <strong>Session Id</strong> is required. Please check your data again.' + '<br/>';
                }

                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

            }
        });

        $.subscribe('successDialogDelete', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialogDelete');
            }
        });

        $.subscribe('errorDialogDelete', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function cancelDeleteBtn() {
            $('#view_dialog').dialog('close');
        };

        function callSearchFunction() {
            $('#info_dialog_delete').dialog('close');
            $('#view_dialog').dialog('close');
            document.userSessionLogForm.action='search_usersessionlog.action';
            document.userSessionLogForm.submit();
        };



    </script>
</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">

                <s:form id="killUserSessionForm" method="post" namespace="/admin/usersessionlog" action="save_usersessionlog"
                        cssClass="well form-horizontal" theme="simple">
                <fieldset>

                    <legend align="left">Kill User Session</legend>
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
                                <label class="control-label"><small>Id :</small></label>
                            </td>
                            <td>
                                <table>
                                    <s:textfield  id="stId" name="userSessionLog.stId" required="true" disabled="true" cssClass="form-control"/>
                                    <s:hidden name="userSessionLog.stId"/>

                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label class="control-label"><small>Session Id :</small></label>
                            </td>
                            <td>
                                <table>
                                    <s:textfield id="sessionId" name="userSessionLog.sessionId" required="true" disabled="true" cssClass="form-control"/>
                                    <s:hidden name="userSessionLog.sessionId"/>
                                </table>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label class="control-label"><small>User Id :</small></label>
                            </td>
                            <td>
                                <table>
                                    <s:textfield id="userId" name="userSessionLog.userName" required="true" disabled="true" cssClass="form-control"/>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label class="control-label"><small>Unit :</small></label>
                            </td>
                            <td>
                                <table>
                                    <s:textfield id="branchName" name="userSessionLog.branchName" required="true" disabled="true" cssClass="form-control"/>

                                </table>

                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label class="control-label"><small>Login Timestamp :</small></label>
                            </td>
                            <td>
                                <table>
                                    <s:textfield id="stLoginTimestamp" name="userSessionLog.stLoginTimestamp" required="true" disabled="true" cssClass="form-control"/>
                                </table>

                            </td>
                        </tr>

                    </table>
                    <br>
                    <div id="actions" class="form-actions">
                        <table>
                            <tr>
                                <td></td>
                                <td>
                                    <div id="crud">
                                        <table>

                                            <tr>
                                                <td align="center">

                                                    <table>

                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="killUserSessionForm" id="save" name="save"
                                                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialogDelete,successDialogDelete"
                                                                   onSuccessTopics="successDialogDelete" onErrorTopics="errorDialogDelete" >
                                                            <i class="icon-ok-sign icon-white"></i>
                                                            Kill Session
                                                        </sj:submit>

                                                        <sj:dialog id="waiting_dialog_delete" openTopics="showDialogDelete" closeTopics="closeDialogDelete" modal="true"
                                                                   resizable="false"
                                                                   height="250" width="600" autoOpen="false" title="Saving ...">
                                                            Please don't close this window, server is processing your request ...
                                                            </br>
                                                            </br>
                                                            </br>
                                                            <img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">
                                                        </sj:dialog>

                                                        <sj:dialog id="info_dialog_delete" openTopics="showInfoDialogDelete" modal="true" resizable="false" closeOnEscape="false"
                                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                                   buttons="{
                                                                    'OK':function() {
                                                                               callSearchFunction();
                                                                         }
                                                                 }"
                                                        >
                                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                                            Record has been saved successfully.
                                                        </sj:dialog>

                                                        <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                                   height="250" width="600" errorPosition="top" autoOpen="false" title="Error Dialog"
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

                                                        <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog" modal="true" resizable="false"
                                                                   height="280" width="500" autoOpen="false" title="Warning"
                                                                   buttons="{
                                                                    'OK':function() { $('#error_validation_dialog').dialog('close'); }
                                                                }"
                                                        >
                                                            <div class="alert alert-error fade in">
                                                                <label class="control-label" align="left">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                                    <br/>
                                                                    <center><div id="errorValidationMessage"></div></center>
                                                                </label>
                                                            </div>
                                                        </sj:dialog>

                                                    </table>
                                                </td>

                                                <td>
                                                    <button type="button" class="btn" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelDeleteBtn();">
                                                        <i class="icon-remove-circle "/> Cancel
                                                    </button>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </td>
                            </tr>

                        </table>
                    </div>
                </fieldset>

                </s:form>



        </td>
    </tr>
</table>

</body>
</html>

