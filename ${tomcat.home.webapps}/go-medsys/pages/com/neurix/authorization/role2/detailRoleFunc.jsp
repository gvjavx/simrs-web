<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <link href="<s:url value="/pages/bootstrap/css/bootstrap.css"/>" rel="stylesheet" media="screen">
    <script type='text/javascript' src='<s:url value="/dwr/interface/RoleFuncAction.js"/>'></script>

    <script type="text/javascript">

        function selectAll(box, nameElement) {
            var checked = box.checked;
            var listOfMenu=document.getElementsByName(nameElement);
            for (var i = 0; i < listOfMenu.length; i++) {
                document.getElementsByName(nameElement)[i].checked=checked;
                setEnableLinkFunction(checked,document.getElementsByName(nameElement)[i].value);
                setCheckedFunction(checked,document.getElementsByName(nameElement)[i].value);
            }
        }

        function setReadOnlyCheckbox(){
            var listOfMenu=document.getElementsByName('menuIdChecked');
            for (var i = 0; i < listOfMenu.length; i++) {
                document.getElementsByName('menuIdChecked')[i].disabled=true;
            }
        }

        function checkedHasChild(menuId, checked, listOfMenu, parent, nameElement, nameElementOther) {

            for (var i = 0; i < listOfMenu.length; i++) {
                if (parent == document.getElementsByName(nameElementOther)[i].innerHTML) {
                    document.getElementsByName(nameElement)[i].checked=checked;
                    setEnableLinkFunction(checked, document.getElementsByName(nameElement)[i].value);
                    setCheckedFunction(checked,document.getElementsByName(nameElement)[i].value);
                    checkedHasChild(menuId, checked, listOfMenu, document.getElementsByName(nameElement)[i].value, nameElement, nameElementOther);
                }
            }
        }

        function selectItem(menuId, box, nameElement, nameElementOther) {
            var checked = box.checked;
            setEnableLinkFunction(checked, menuId);
            setCheckedFunction(checked,menuId);
            var listOfMenu=document.getElementsByName(nameElementOther);
            for (var i = 0; i < listOfMenu.length; i++) {
                if (menuId == document.getElementsByName(nameElementOther)[i].innerHTML) {
                    document.getElementsByName(nameElement)[i].checked=checked;
                    setEnableLinkFunction(checked, document.getElementsByName(nameElement)[i].value);
                    setCheckedFunction(checked,menuId);
                    checkedHasChild(menuId, checked, listOfMenu, document.getElementsByName(nameElement)[i].value, nameElement, nameElementOther);
                }
            }
        }

        function setEnableLink() {

            var listOfMenu=document.getElementsByName('menuIdChecked');
            for (var i = 0; i < listOfMenu.length; i++) {
                var menuId = document.getElementsByName('menuIdChecked')[i].value;
                if (document.getElementsByName('menuIdChecked')[i].checked) {
                    if (document.getElementById(menuId) != null) {
                        document.getElementById(menuId).style.visibility = 'visible';
                        document.getElementById(menuId + '_').style.visibility = 'hidden';
                    }
                } else {
                    if (document.getElementById(menuId) != null) {
                        document.getElementById(menuId).style.visibility = 'hidden';
                        document.getElementById(menuId + '_').style.visibility = 'visible';
                    }
                }
            }

        }

        function setEnableLinkFunction(checked, menuId) {
            if (checked) {
                if (document.getElementById(menuId)!= null) {
                    document.getElementById(menuId).style.visibility = 'visible';
                    document.getElementById(menuId + '_').style.visibility = 'hidden';
                }
            } else {
                if (document.getElementById(menuId)!= null) {
                    document.getElementById(menuId).style.visibility = 'hidden';
                    document.getElementById(menuId + '_').style.visibility = 'visible';
                }
            }
        }

        function setCheckedFunction(checked, menuId) {
            var addFlag = false;
            var editFlag = true;
            var roleId = '<s:property value="roleFunc.stRoleId"/>';

            dwr.engine.setAsync(false);
            RoleFuncAction.saveDetailFunction(checked,addFlag,editFlag,roleId,menuId, function (message) {
                if (message!='00') {

                    document.getElementById('errorMessage').innerHTML = msg;

                    $.publish('showErrorDialog');

                }
            });
        }

        $.subscribe('beforeProcessSave', function (event, data) {
            var roleId = '<s:property value="roleFunc.stRoleId"/>';
            var listOfMenu = document.getElementsByName('menuIdChecked');
            var foundCheck = false;
            for (var i = 0; i < listOfMenu.length && !foundCheck; i++) {
                if (document.getElementsByName('menuIdChecked')[i].checked) {
                    foundCheck = true;
                }
            }

            if (roleId != '' && foundCheck ) {

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
                if (roleId == '') {
                    msg = 'Field <strong>RoleId</strong> is required.' + '<br/>';
                }

                if (!foundCheck) {
                    msg = msg + '<strong>List of Menu</strong> is required. Please checked minimum one.' + '<br/>';
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
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $.subscribe('errorDialog', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
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


                <s:form id="modifyRolefuncForm" method="post" namespace="/admin/rolefunction" action="save_rolefunc" cssClass="well form-horizontal">

                    <s:hidden name="addOrEdit"/>
                    <s:hidden name="delete"/>

                    <fieldset>

                        <s:if test="isDelete()">
                            <legend align="left">Delete Role-Function</legend>
                        </s:if>
                        <s:elseif test="isAddOrEdit()">
                            <legend align="left">Update Role-Function</legend>
                        </s:elseif>
                        <s:else>
                            <legend align="left">View Role-Function</legend>
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
                                    <label class="control-label" for="roleFunc.stRoleId">Role Id :</label>
                                </td>

                                <td>
                                    <table>
                                        <s:textfield id="roleId" name="roleFunc.stRoleId" required="false" disabled="true"/>
                                        <s:hidden name="roleId"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label" for="roleFunc.roleName">Role Name :</label>
                                </td>

                                <td>
                                    <table>
                                        <s:textfield id="roleName" name="roleFunc.roleName" required="false" disabled="true"/>
                                    </table>
                                </td>
                            </tr>

                        </table>

                        <table width="100%">
                            <tr>
                                <td align="center">

                                    <sj:dialog id="view_dialog_function" openTopics="showDialogFunction" modal="true" resizable="false" cssStyle="text-align:left;"
                                               position="center" height="600" width="750" autoOpen="false" title="View Detail Function"
                                            >
                                        <center><img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" alt="Loading..."/></center>
                                    </sj:dialog>

                                    <s:set name="listOfMenu" value="roleFunc.listOfMenu"/>

                                    <s:if test="isAddOrEdit()">
                                        <display:table name="listOfMenu" class="table table-condensed table-striped table-hover"
                                                       requestURI="" id="row" style="font-size:11">
                                            <display:column property="displayObjectCheck.checkBox"
                                                            title="Check <center><input type='checkbox' name='selectall' onClick='selectAll(this, \"menuIdChecked\")' /></center>"
                                                            media="html" style="text-align:center;"/>

                                            <display:column property="displayObjectDiv.div"
                                                            title=""
                                                            media="html" style="text-align:center;"/>

                                            <display:column media="html" title="Detail">
                                                <s:if test="#attr.row.initForm">
                                                    <s:url var="urlView" namespace="/admin/rolefunction" action="viewDetail_rolefunc" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.funcId"/></s:param>
                                                        <s:param name="edit">true</s:param>
                                                        <s:param name="roleId"><s:property value="roleFunc.stRoleId"/></s:param>
                                                    </s:url>
                                                    <s:div id="%{#attr.row.funcId}" cssStyle="visibility:hidden">
                                                        <sj:a onClickTopics="showDialogFunction" href="%{urlView}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                        </sj:a>
                                                    </s:div>
                                                    <s:div id="%{#attr.row.funcId}_"/>
                                                </s:if>
                                            </display:column>

                                            <display:column property="funcId" title="Id" />
                                            <display:column property="funcName" title="Name" />
                                            <display:column property="url" title="URL" />
                                            <display:column property="parent" title="Parent"/>
                                            <display:column property="funcLevel" title="Level"/>
                                            <display:column property="statusPath" title="Status Path"/>
                                            <display:caption style="font-weight:bold;font-size:17px">List Of Menu</display:caption>
                                        </display:table>
                                        <script>setEnableLink()</script>
                                    </s:if>
                                    <s:else>
                                        <display:table name="listOfMenu" class="table table-condensed table-striped table-hover"
                                                       requestURI="" id="row" style="font-size:11">

                                            <display:column property="displayObjectCheck.checkBox"
                                                            title="Check <center><input type='checkbox' name='selectall' disabled='true' /></center>"
                                                            media="html" style="text-align:center;"/>

                                            <display:column property="displayObjectDiv.div"
                                                            title=""
                                                            media="html" style="text-align:center;"/>

                                            <display:column media="html" title="Detail">
                                                <s:if test="#attr.row.initForm">
                                                    <s:url var="urlView" namespace="/admin/rolefunction" action="viewDetail_rolefunc" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.funcId"/></s:param>
                                                        <s:param name="edit">false</s:param>
                                                        <s:param name="roleId"><s:property value="roleFunc.stRoleId"/></s:param>
                                                    </s:url>
                                                    <s:div id="%{#attr.row.funcId}" cssStyle="visibility:hidden">
                                                        <sj:a onClickTopics="showDialogFunction" href="%{urlView}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">
                                                        </sj:a>
                                                    </s:div>
                                                    <s:div id="%{#attr.row.funcId}_"/>
                                                </s:if>
                                            </display:column>

                                            <display:column property="funcId" title="Id" />
                                            <display:column property="funcName" title="Name" />
                                            <display:column property="url" title="URL" />
                                            <display:column property="parent" title="Parent"/>
                                            <display:column property="funcLevel" title="Level"/>
                                            <display:column property="statusPath" title="Status Path"/>
                                            <display:caption style="font-weight:bold;font-size:17px">List Of Menu</display:caption>
                                        </display:table>
                                        <script>setReadOnlyCheckbox();
                                                setEnableLink();</script>
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
                                                <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="modifyRolefuncForm" id="save" name="save"
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
                                                                      $('#view_dialog_menu').dialog('close');
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
                                                <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="modifyRolefuncForm"
                                                           id="delete" name="delete"
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
                                                                            $('#view_dialog_menu').dialog('close');
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

