<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <link href="<s:url value="/pages/bootstrap/css/bootstrap.css"/>" rel="stylesheet" media="screen">
    <script type="text/javascript">

        function setReadOnlyField(){
            document.getElementById("userid").disabled = true;
            document.getElementById("name").disabled = true;
            document.getElementById("password").disabled = true;
            document.getElementById("emailid").disabled = true;
            document.getElementById("positionid").disabled = true;
            document.getElementById("roleid").disabled = true;
            document.getElementById("areaid").disabled = true;
            document.getElementById("branchid").disabled = true;
        }

        $.subscribe('beforeProcessSave', function (event, data) {
            if (document.getElementById("userid").value != '' &&
                    document.getElementById("name").value != '' &&
                    document.getElementById("password").value != '' &&
                    document.getElementById("confirmPassword").value != '' &&
                    document.getElementById("positionid").value != '' &&
                    document.getElementById("roleid").value != '' &&
                    document.getElementById("areaid").value != '' &&
                    document.getElementById("branchid").value != '' &&
                    document.getElementById("password").value == document.getElementById("confirmPassword").value) {

                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');

                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }

            } else {

                event.originalEvent.options.submit = false;

                var msg = "";
                if (document.getElementById("userid").value == '') {
                    msg = 'Field <strong>User Id</strong> is required.' + '<br/>';
                }

                if (document.getElementById("name").value == '') {
                    msg = msg + 'Field <strong>UserName</strong> is required.' + '<br/>';
                }

                if (document.getElementById("password").value == '') {
                    msg = msg + 'Field <strong>Password</strong> is required.' + '<br/>';
                }

                if (document.getElementById("confirmPassword").value == '') {
                    msg = msg + 'Field <strong>ConfirmPassword</strong> is required.' + '<br/>';
                }

                if (document.getElementById("password").value != document.getElementById("confirmPassword").value) {
                    msg = msg + 'Field <strong>Password</strong> and <strong>confirmPassword</strong> not match.' + '<br/>';
                }

                if (document.getElementById("positionid").value == '') {
                    msg = msg + 'Field <strong>Position Id</strong> is required.' + '<br/>';
                }

                if (document.getElementById("roleid").value == '') {
                    msg = msg + 'Field <strong>Role Id</strong> is required.' + '<br/>';
                }

                if (document.getElementById("areaid").value == '') {
                    msg = msg + 'Field <strong>Area Id</strong> is required.' + '<br/>';
                }

                if (document.getElementById("branchid").value == '') {
                    msg = msg + 'Field <strong>Branch Id</strong> is required.' + '<br/>';
                }

                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

            }
        });

        $.subscribe('beforeProcessDelete', function (event, data) {
            if (confirm('Do you want to delete this record ?')) {
                event.originalEvent.options.submit = true;
                $.publish('showDialog');

            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
            }
        });


        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                $.publish('showInfoDialog');
                jQuery(".ui-dialog-titlebar-close").hide();
            }
        });

        $.subscribe('errorDialog', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function cancelBtn() {
            $('#view_dialog_user').dialog('close');
        };


    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">

            <s:form id="modifyUserForm" method="post" enctype="multipart/form-data" namespace="/admin/user" action="save_user" cssClass="well form-horizontal">
                <s:hidden id="addOrEditFlag" name="addOrEdit"/>
                <s:hidden id="deleteFlag" name="delete"/>

                <fieldset>

                    <s:if test="isDelete()">
                        <legend align="left">Delete User</legend>
                    </s:if>
                    <s:elseif test="isAddOrEdit()">
                        <legend align="left">Update User</legend>
                    </s:elseif>
                    <s:else>
                        <legend align="left">View User</legend>
                    </s:else>

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
                                <label class="control-label" for="users.userId">User Id :</label>
                            </td>

                            <td>
                                <table>
                                    <s:textfield id="userid" name="users.userId" required="true" disabled="true"/>
                                    <s:hidden name="userId"/>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label class="control-label" for="users.userName">User Name :</label>
                            </td>

                            <td>
                                <table>
                                    <s:textfield id="name" name="users.username" required="true"/>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label class="control-label" for="users.password">Password :</label>
                            </td>

                            <td>
                                <table>
                                    <s:password id="password" name="users.password" required="true" showPassword="true"/>
                                </table>
                            </td>
                        </tr>

                        <s:if test="isAddOrEdit()">
                            <tr>
                                <td>
                                    <label class="control-label" for="users.confirmPassword">Confirmation Password :</label>
                                </td>

                                <td>
                                    <table>
                                        <s:password id="confirmPassword" name="users.confirmPassword" required="true" showPassword="true"/>
                                    </table>
                                </td>
                            </tr>
                        </s:if>
                        <tr>
                            <td>
                                <label class="control-label" for="users.email">Email :</label>
                            </td>

                            <td>
                                <table>
                                    <s:textfield id="emailid" name="users.email" required="true"/>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label class="control-label" for="users.positionId">Position :</label>
                            </td>

                            <td>
                                <table>
                                    <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                                    <s:select list="#comboPosition.listOfComboPositions" id="positionid" name="users.positionId" required="false"
                                              listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label class="control-label" for="users.areaId">Area :</label>
                            </td>

                            <td>
                                <table>
                                    <s:action id="comboArea" namespace="/admin/user" name="initComboArea_user"/>
                                    <s:select list="#comboArea.listOfComboAreas" id="areaid" name="users.areaId" required="true"
                                              listKey="areaId" listValue="areaName" headerKey="" headerValue="[Select one]" />
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label class="control-label" for="users.branchId">Branch :</label>
                            </td>

                            <td>
                                <table>
                                    <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                    <s:select list="#comboBranch.listOfComboBranches" id="branchid" name="users.branchId" required="true"
                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                                </table>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label class="control-label" for="users.roleId">Role :</label>
                            </td>

                            <td>
                                <table>
                                    <s:action id="comboRole" namespace="/admin/user" name="initComboRole_user"/>
                                    <s:select list="#comboRole.listOfComboRoles" id="roleid" name="users.roleId" required="false"
                                              listKey="stRoleId" listValue="roleName" headerKey="" headerValue="[Select one]"/>
                                </table>
                            </td>
                        </tr>

                        <s:if test="isAddOrEdit()">
                            <tr>
                                <td>
                                    <label class="control-label" for="fileUpload">Upload Photo :</label>
                                </td>

                                <td>
                                    <table>
                                        <s:file name="fileUpload" cssClass="btn-mini" cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                    </table>
                                </td>
                            </tr>
                        </s:if>
                        <s:if test="!isDelete() && !isAddOrEdit()">
                            <tr>
                                <td>
                                    <label class="control-label" for="users.stCreatedDate">Created Date :</label>
                                </td>

                                <td>
                                    <table>
                                        <s:textfield name="users.stCreatedDate" disabled="true"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label" for="users.createdWho">Created Who :</label>
                                </td>

                                <td>
                                    <table>
                                        <s:textfield name="users.createdWho" disabled="true"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label" for="users.stLastUpdate">Last Update :</label>
                                </td>

                                <td>
                                    <table>
                                        <s:textfield name="users.stLastUpdate" disabled="true"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label" for="users.lastUpdateWho">Last Update Who :</label>
                                </td>

                                <td>
                                    <table>
                                        <s:textfield name="users.lastUpdateWho" disabled="true"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label" for="users.flag">Flag :</label>
                                </td>

                                <td>
                                    <table>
                                        <s:textfield name="users.flag" disabled="true"/>
                                    </table>
                                </td>
                            </tr>
                        </s:if>
                    </table>

                    <s:if test="isDelete() || !isAddOrEdit()">
                        <script>setReadOnlyField();</script>
                    </s:if>

                </fieldset>

                <div id="actions" class="form-actions">
                    <table>
                        <tr>
                            <div id="crud">
                                <td>
                                    <table>
                                        <s:if test="isAddOrEdit()">
                                            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="modifyUserForm" id="save" name="save"
                                                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                <i class="icon-ok-sign icon-white"></i>
                                                Save
                                            </sj:submit>

                                            <sj:dialog id="waiting_dialog_saveuser" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                       resizable="false"
                                                       height="250" width="600" autoOpen="false" title="Saving ...">
                                                Please don't close this window, server is processing your request ...
                                                </br>
                                                </br>
                                                </br>
                                                <img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">
                                            </sj:dialog>

                                            <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false" closeOnEscape="false"
                                                       position="center" height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                       buttons="{
                                                                    'OK':function() {
                                                                               $('#info_dialog').dialog('close');
                                                                               $('#view_dialog_user').dialog('close');
                                                                               document.userForm.action='search_user.action';
                                                                               document.userForm.submit();
                                                                         }
                                                                 }"
                                                    >
                                                <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                                Record has been saved successfully.
                                            </sj:dialog>

                                            <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                       position="center" height="250" width="600" autoOpen="false" title="Error Dialog"
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
                                                       position="center" height="280" width="500" autoOpen="false" title="Warning"
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
                                        </s:if>
                                        <s:elseif test="isDelete()">
                                            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="modifyUserForm" id="delete" name="delete"
                                                       onBeforeTopics="beforeProcessDelete" onCompleteTopics="closeDialog,successDialog"
                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog">
                                                <i class="icon-trash icon-white"></i>
                                                Delete
                                            </sj:submit>

                                            <sj:dialog id="waiting_dialog_saveuser" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                       resizable="false"
                                                       height="250" width="600" autoOpen="false" title="Deleting ...">
                                                Please don't close this window, server is processing your request ...
                                                </br>
                                                </br>
                                                </br>
                                                <img border="0" src="<s:url value="/pages/images/indicator-trash.gif"/>" name="image_indicator_trash">
                                            </sj:dialog>

                                            <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                       position="center" height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                       buttons="{
                                                                     'OK':function() {
                                                                              $('#info_dialog').dialog('close');
                                                                              $('#view_dialog_user').dialog('close');
                                                                              document.userForm.action='search_user.action';
                                                                              document.userForm.submit();

                                                                          }
                                                                }"
                                                    >
                                                <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                                Record has been deleted successfully.
                                            </sj:dialog>

                                            <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                       position="center" height="250" width="600" autoOpen="false" title="Error Dialog"
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
                                        </s:elseif>
                                    </table>
                                </td>
                            </div>

                            <td>
                                <table>
                                    <button type="button" id="cancel" class="btn" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelBtn();">
                                        <i class="icon-remove-circle"/> Cancel
                                    </button>
                                </table>
                            </td>

                        </tr>
                    </table>
                </div>
            </s:form>


        </td>
    </tr>
</table>
</body>
</html>

