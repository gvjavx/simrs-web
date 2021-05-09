<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript'>

        function selectAll(box, nameElement) {
            var areaId = document.getElementById('areaId').value;
            var branchId = document.getElementById('branchId').value;
            if (areaId != '' && branchId != '') {
                var checked = box.checked;
                var listOfUser=document.getElementsByName(nameElement);
                for (var i = 0; i < listOfUser.length; i++) {
                    document.getElementsByName(nameElement)[i].checked=checked;
                }
            } else {

                box.checked = false;

                var msg = 'Field <strong>AreaId and BranchId</strong> is required. You must entry that fields.' + '<br/>';

                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

            }

        }

        $.subscribe('beforeProcessSave', function (event, data) {
            var listOfUser=document.getElementsByName('userIdChecked');
            if (document.getElementById("areaId").value != '' &&
                    document.getElementById("branchId").value != '' &&
                    listOfUser.length != 0 ) {

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
                if (document.getElementById("areaId").value == '') {
                    msg = 'Field <strong>AreaId</strong> is required.' + '<br/>';
                }

                if (document.getElementById("branchId").value == '') {
                    msg = msg + 'Field <strong>BranchId</strong> is required.' + '<br/>';
                }

                if (listOfUser.length == 0) {
                    msg = msg + '<strong>List of User</strong> is required. Please checked minimum one.' + '<br/>';
                }

                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

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

        function resetField() {

            document.getElementById("areaId").value = '';
            document.getElementById("branchId").value = '';
            document.getElementById("selectall").checked = false;
            var listOfUser=document.getElementsByName('userIdChecked');
            for (var i = 0; i < listOfUser.length; i++) {
                document.getElementsByName('userIdChecked')[i].checked=false;
            }

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

                                <s:form id="addAreaBranchUserForm" method="post" namespace="/admin/areabranchuser" action="save_areabranchuser" cssClass="well form-horizontal">

                                <s:hidden name="add"/>
                                <s:hidden name="addOrEdit"/>

                                <fieldset>
                                    <legend align="left">Add Area-Branch-User</legend>

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

                                                    <s:action id="combo" namespace="/admin/areabranchuser" name="initComboArea_areabranchuser"/>
                                                    <s:select list="#combo.listOfComboArea" id="areaId" name="areaBranchUser.areaId" listKey="areaId" listValue="areaName"
                                                              headerKey="" headerValue="[Select One]"/>

                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="control-label" for="areaBranchUser.branchId">Branch Id :</label>
                                            </td>

                                            <td>
                                                <table>

                                                    <s:action id="combo" namespace="/admin/areabranchuser" name="initComboBranch_areabranchuser"/>
                                                    <s:select list="#combo.listOfComboBranch" id="branchId" name="areaBranchUser.branchId" listKey="branchId" listValue="branchName"
                                                              headerKey="" headerValue="[Select One]"/>

                                                </table>
                                            </td>
                                        </tr>

                                    </table>

                                    <table width="60%">
                                        <tr>
                                            <td align="center">

                                                <s:set name="listOfUsers" value="areaBranchUser.listOfUser" scope="request"/>
                                                <display:table name="listOfUsers" class="table table-condensed table-striped table-hover"
                                                               requestURI="" id="row" style="font-size:11">

                                                    <display:column property="displayObjectCheck.checkBox"
                                                                    title="Check <center><input type='checkbox' id='selectall' name='selectall' onClick='selectAll(this, \"userIdChecked\")'/></center>"
                                                                    media="html" style="text-align:center;"/>

                                                    <display:column property="userId" title="Id" />
                                                    <display:column property="username" title="Name" />
                                                    <display:column property="email" title="Email" />
                                                    <display:column property="positionName" title="Position.Name"/>
                                                    <display:column property="previewPhoto" title="Preview Photo" />
                                                    <display:caption style="font-weight:bold;font-size:17px">List Of User</display:caption>
                                                </display:table>

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

                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addAreaBranchUserForm" id="save" name="save"
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
                                                    <button type="button" class="btn" onclick="window.location.href='<s:url action="initForm_areabranchuser"/>'">
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


