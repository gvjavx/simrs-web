<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <link href="<s:url value="/pages/bootstrap/css/bootstrap.css"/>" rel="stylesheet" media="screen">
    <script type="text/javascript">

        function selectAll(box, nameElement) {

            var checked = box.checked;
            var listOfUser=document.getElementsByName(nameElement);
            for (var i = 0; i < listOfUser.length; i++) {
                document.getElementsByName(nameElement)[i].checked=checked;
            }
        }

        function setReadOnlyCheckbox(){
            var listOfUser=document.getElementsByName('userIdChecked');
            for (var i = 0; i < listOfUser.length; i++) {
                document.getElementsByName('userIdChecked')[i].disabled=true;
            }
        }

        $.subscribe('beforeProcessSave', function (event, data) {

            var areaId = '<s:property value="areaBranchUser.areaId"/>';
            var branchId = '<s:property value="areaBranchUser.branchId"/>';
            var listOfUser=document.getElementsByName('userIdChecked');
            var foundCheck = false;
            for (var i = 0; i < listOfUser.length && !foundCheck; i++) {
                if (document.getElementsByName('userIdChecked')[i].checked) {
                    foundCheck = true;
                }
            }

            if (areaId != '' && branchId != '' && foundCheck ) {
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
                if (areaId == '') {
                    msg = 'Field <strong>AreaId</strong> is required.' + '<br/>';
                }

                if (branchId == '') {
                    msg = 'Field <strong>BranchId</strong> is required.' + '<br/>';
                }

                if (!foundCheck) {
                    msg = msg + '<strong>List of User</strong> is required. Please checked minimum one.' + '<br/>';
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


            <s:form id="modifyAreaBranchUserForm" method="post" namespace="/admin/areabranchuser" action="save_areabranchuser" cssClass="well form-horizontal">

            <s:hidden name="addOrEdit"/>
            <s:hidden name="delete"/>

            <fieldset>

                <s:if test="isDelete()">
                    <legend align="left">Delete Area-Branch-User</legend>
                </s:if>
                <s:elseif test="isAddOrEdit()">
                    <legend align="left">Update Area-Branch-User</legend>
                </s:elseif>
                <s:else>
                    <legend align="left">View Area-Branch-User</legend>
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
                            <label class="control-label" for="areaBranchUser.areaId">Area Id :</label>
                        </td>

                        <td>
                            <table>
                                <s:textfield id="areaId" name="areaBranchUser.areaId" required="false" readonly="true"/>
                                <s:hidden name="areaId"/>
                            </table>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="areaName" name="areaBranchUser.areaName" required="false" readonly="true"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label" for="areaBranchUser.branchId">Branch Id :</label>
                        </td>

                        <td>
                            <table>
                                <s:textfield id="branchId" name="areaBranchUser.branchId" required="false" readonly="true"/>
                                <s:hidden name="branchId"/>
                            </table>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="branchName" name="areaBranchUser.branchName" required="false" readonly="true"/>
                            </table>
                        </td>
                    </tr>

                </table>

                <table width="80%">
                    <tr>
                        <td align="center">

                            <s:set name="listOfUser" value="areaBranchUser.listOfUser"/>

                            <s:if test="isAddOrEdit()">
                                <display:table name="listOfUser" class="table table-condensed table-striped table-hover"
                                               requestURI="" id="row" style="font-size:11">

                                    <display:column property="displayObjectCheck.checkBox"
                                                    title="Check <center><input type='checkbox' name='selectall' onClick='selectAll(this, \"userIdChecked\")' /></center>"
                                                    media="html" style="text-align:center;"/>

                                    <display:column property="userId" title="Id" />
                                    <display:column property="username" title="Name" />
                                    <display:column property="email" title="Email" />
                                    <display:column property="positionName" title="Position.Name"/>
                                    <display:column property="previewPhoto" title="Preview Photo" />
                                    <display:caption style="font-weight:bold;font-size:17px">List Of User</display:caption>
                                </display:table>
                            </s:if>
                            <s:else>
                                <display:table name="listOfUser" class="table table-condensed table-striped table-hover"
                                               requestURI="" id="row" style="font-size:11">

                                    <display:column property="displayObjectCheck.checkBox"
                                                    title="Check <center><input type='checkbox' name='selectall' disabled='true' /></center>"
                                                    media="html" style="text-align:center;"/>

                                    <display:column property="userId" title="Id" />
                                    <display:column property="username" title="Name" />
                                    <display:column property="email" title="Email" />
                                    <display:column property="positionName" title="Position.Name"/>
                                    <display:column property="previewPhoto" title="Preview Photo" />
                                    <display:caption style="font-weight:bold;font-size:17px">List Of User</display:caption>
                                </display:table>
                                <script>setReadOnlyCheckbox();</script>
                            </s:else>
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
                                    <s:if test="isAddOrEdit()">
                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="modifyAreaBranchUserForm" id="save" name="save"
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

                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                   position="center" height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                               'OK':function() {
                                                                        $('#info_dialog').dialog('close');
                                                                        $('#view_dialog_user').dialog('close');
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
                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="modifyAreaBranchUserForm" id="delete" name="delete"
                                                   onBeforeTopics="beforeProcessDelete" onCompleteTopics="closeDialog,successDialog"
                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog">
                                            <i class="icon-trash icon-white"></i>
                                            Delete
                                        </sj:submit>

                                        <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
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

