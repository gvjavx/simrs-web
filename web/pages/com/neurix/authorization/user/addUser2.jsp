<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript'>

        $.subscribe('beforeProcessSave', function (event, data) {

            if (document.getElementById("userid").value != '' &&
                    document.getElementById("name").value != '' &&
                    document.getElementById("password").value != '' &&
                    document.getElementById("confirmPassword").value != '' &&
                    document.getElementById("positionid").value != '' &&
                    document.getElementById("roleid").value != '' &&
                    document.getElementById("areaid").value != '' &&
                    document.getElementById("branchid").value != '' &&
                    document.getElementById("fileUpload").value != '' &&
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

                if (document.getElementById("positionid").value != '') {
                    msg = msg + 'Field <strong>Position Id</strong> is required.' + '<br/>';
                }

                if (document.getElementById("aread").value != '') {
                    msg = msg + 'Field <strong>Area Id</strong> is required.' + '<br/>';
                }

                if (document.getElementById("branchid").value != '') {
                    msg = msg + 'Field <strong>Branch Id</strong> is required.' + '<br/>';
                }

                if (document.getElementById("roleid").value != '') {
                    msg = msg + 'Field <strong>Role Id</strong> is required.' + '<br/>';
                }

                if (document.getElementById("fileUpload").value != '') {
                    msg = msg + 'Field <strong>File Upload</strong> is required.' + '<br/>';
                }

                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

            }
        });

        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');

            }
        });

        $.subscribe('errorDialog', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function resetField() {

            document.getElementById("userid").value = '';
            document.getElementById("name").value = '';
            document.getElementById("password").value = '';
            document.getElementById("email").value = '';
            document.getElementById("fileUpload").value = '';
            document.getElementById("confirmPassword").value = '';
            document.getElementById("positionid").value = '';
            document.getElementById("roleid").value = '';
            document.getElementById("areaid").value = '';
            document.getElementById("branchid").value = '';

        }


    </script>
</head>

<body class="sidebar-push  sticky-footer">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<div class="container-fluid">
    <div id="main">
        <div class="media">
             <div class="media-body">
                <table width="100%" align="center">
                    <tr>
                        <td align="center">
                            <div id="box">

                            <s:url id="urlProcess" namespace="/admin/user" action="save_user" includeContext="false"/>
                            <s:form id="addUserForm" enctype="multipart/form-data" method="post" action="%{urlProcess}" cssClass="well form-horizontal">

                                <s:hidden name="addOrEdit"/>
                                <s:hidden name="add"/>

                                <fieldset>
                                    <legend align="left">Add User</legend>

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
                                                    <s:textfield id="userid" name="users.userId" required="true"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label" for="users.username">User Name :</label>
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
                                                    <s:password id="password" name="users.password" required="true"/>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label" for="users.confirmPassword">Confirmation Password :</label>
                                            </td>

                                            <td>
                                                <table>
                                                    <s:password id="confirmPassword" name="users.confirmPassword" required="true"/>
                                                </table>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label" for="users.email">Email :</label>
                                            </td>

                                            <td>
                                                <table>
                                                    <s:textfield id="email" name="users.email" required="true"/>
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
                                                        listKey="stRoleId" listValue="roleName" headerKey="" headerValue="[Select one]" />
                                                </table>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <label class="control-label" for="fileUpload">Upload Photo :</label>
                                            </td>

                                            <td>
                                                <table>
                                                    <s:file id="fileUpload" name="fileUpload" cssClass="btn-mini" cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>

                                </fieldset>

                                <div id="actions" class="form-actions">
                                    <table>
                                        <tr>
                                            <div id="crud">
                                                <td>
                                                    <table>

                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addUserForm" id="save" name="save"
                                                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                            <i class="icon-ok-sign icon-white"></i>
                                                            Save
                                                        </sj:submit>

                                                        <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
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
                                                                                         resetField();
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

                                                    </table>
                                                </td>
                                            </div>

                                            <td>
                                                <table>
                                                    <button type="button" class="btn" onclick="window.location.href='<s:url action="initForm_user"/>'">
                                                        <i class="icon-repeat"></i> Reset
                                                    </button>
                                                </table>
                                            </td>

                                        </tr>
                                    </table>
                                </div>

                            </s:form>

                            </div>
                        </td>
                    </tr>
                </table>
             </div>
        </div>
    </div>
    <%@ include file="/pages/common/footer.jsp" %>
</div>

<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>


