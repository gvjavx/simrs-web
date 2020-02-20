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

            if (document.getElementById("roleName").value != '') {

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
                if (document.getElementById("roleName").value == '') {
                    msg = 'Field <strong>Role Name</strong> is required.' + '<br/>';
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

        function resetField() {
            document.getElementById("roleId").value = '';
            document.getElementById("roleName").value = '';
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

                            <s:if test="isAddOrEdit() || isDelete()">
                                <s:url id="urlProcess" namespace="/admin/role" action="save_role" includeContext="false"/>
                            </s:if>
                            <s:else>
                                <s:url id="urlProcess" namespace="/admin/role" action="search_role" includeContext="false"/>
                            </s:else>

                            <s:form id="roleForm" method="post" action="%{urlProcess}"
                                    cssClass="well form-horizontal">

                            <s:hidden name="addOrEdit"/>
                            <s:hidden id="add" name="add"/>
                            <s:hidden name="delete"/>

                            <fieldset>
                                <legend align="left">Role Information</legend>

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
                                            <label class="control-label" for="roles.stRoleId">Role Id :</label>
                                        </td>

                                        <td>
                                            <table>
                                                <s:if test="isAddOrEdit()">
                                                    <s:textfield id="roleId" name="roles.stRoleId" required="true" readonly="true"/>
                                                </s:if>
                                                <s:elseif test="isDelete()">
                                                    <s:textfield id="roleId" cssClass="disabled" readonly="true" name="roles.stRoleId"/>
                                                </s:elseif>
                                                <s:else>
                                                    <s:textfield id="roleId" name="roles.stRoleId" required="false"/>
                                                </s:else>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                                <table>
                                    <tr>
                                        <td>
                                            <label class="control-label" for="roles.roleName">Role Name :</label>
                                        </td>

                                        <td>
                                            <table>
                                                <s:if test="isAddOrEdit()">
                                                    <s:if test="isAdd()">
                                                        <s:textfield id="roleName" name="roles.roleName" required="true"/>
                                                    </s:if>
                                                    <s:else>
                                                        <s:textfield id="roleName" name="roles.roleName" required="true"/>
                                                    </s:else>
                                                </s:if>
                                                <s:elseif test="isDelete()">
                                                    <s:textfield id="roleName" cssClass="disabled" readonly="true" name="roles.roleName"/>
                                                </s:elseif>
                                                <s:else>
                                                    <s:textfield id="roleName" name="roles.roleName" required="false"/>
                                                </s:else>
                                            </table>
                                        </td>
                                    </tr>
                                </table>

                                <s:if test="!(isAddOrEdit() || isDelete())">
                                    <table>
                                        <tr>
                                            <td>
                                                <label class="control-label" for="roles.flag">Flag :</label>
                                            </td>

                                            <td>
                                                <table>
                                                    <s:select list="#{'Y':'Active', 'N':'NonActive'}" id="flag" name="roles.flag"
                                                              headerKey="" headerValue="[Select one]"/>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </s:if>

                            </fieldset>


                            <div id="actions" class="form-actions">
                                <table>
                                    <tr>
                                        <div id="crud">
                                            <td>
                                                <table>

                                                    <s:if test="isAddOrEdit()">

                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="roleForm" id="save" name="save"
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
                                                            <img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>"
                                                            name="image_indicator_write">
                                                        </sj:dialog>

                                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                                   position="center" height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                                   buttons="{
                                                                              'OK':function() {
                                                                                     $('#info_dialog').dialog('close');
                                                                                     if (document.getElementById('add').value == 'true') {
                                                                                        resetField();
                                                                                     }

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
                                                                <label class="control-label" align="left"> <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                                    name="icon_error"> System Found : <p id="errorMessage"></p></label>
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
                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="roleForm"
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
                                                            <img border="0" src="<s:url value="/pages/images/indicator-trash.gif"/>"
                                                            name="image_indicator_trash">
                                                        </sj:dialog>

                                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                                   position="center" height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                                   buttons="{
                                                                              'OK':function() { $('#info_dialog').dialog('close'); }
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
                                                                <label class="control-label" align="left"> <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                                    name="icon_error"> System Found : <p id="errorMessage"></p></label>
                                                            </div>
                                                        </sj:dialog>
                                                    </s:elseif>
                                                    <s:else>

                                                        <sj:dialog id="waiting_dialog" openTopics="showDialog1" closeTopics="closeDialog" modal="true"
                                                                   resizable="false"
                                                                   position="center" height="250" width="600" autoOpen="false" title="Searching...">
                                                            Please don't close this window, server is processing your request ...
                                                            </br>
                                                            </br>
                                                            </br>
                                                            <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>"
                                                            name="image_indicator_read">
                                                        </sj:dialog>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="roleForm" id="search"
                                                                   name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog">
                                                            <i class="icon-search icon-white"></i>
                                                            Search
                                                        </sj:submit>

                                                    </s:else>

                                                </table>
                                            </td>


                                            <td>
                                                <table>

                                                    <s:url id="urlAdd" namespace="/admin/role" action="add_role" escapeAmp="false"/>
                                                    <%--<s:url id="urlEdit" namespace="/admin/role" action="edit_role" escapeAmp="false"/>--%>
                                                    <%--<s:url id="urlDelete" namespace="/admin/role" action="delete_role" escapeAmp="false" />--%>

                                                    <s:if test="!isAddOrEdit() && !isDelete()">
                                                        <div class="btn-group">
                                                            <button class="btn dropdown-toggle" data-toggle="dropdown">
                                                                <i class="icon-briefcase"></i>
                                                                Role
                                                                <span class="caret"></span>
                                                            </button>
                                                            <ul class="dropdown-menu">
                                                                <li><s:a href="%{urlAdd}"><i class="icon-plus"></i> Add</s:a></li>
                                                                <%--<li><s:a id="actionEdit" href="%{urlEdit}"><i class="icon-pencil"></i> Edit</s:a></li>--%>
                                                                <%--<li><s:a id="actionDelete" href="%{urlDelete}"><i class="icon-trash"></i> Delete</s:a></li>--%>

                                                            </ul>
                                                        </div>
                                                    </s:if>
                                                </table>
                                            </td>

                                            <td>
                                                <table>
                                                    <button type="button" class="btn" onclick="window.location.href='<s:url action="initForm_role"/>'">
                                                        <i class="icon-repeat"></i> Reset
                                                    </button>
                                                </table>
                                            </td>
                                        </div>
                                    </tr>
                                </table>
                            </div>

                            <s:if test="!isAddOrEdit() && !isDelete()">
                                <table>
                                    <tr>
                                        <td align="center">

                                            <s:set name="listOfRoles" value="#session.listOfResult" scope="request"/>
                                            <display:table name="listOfRoles" class="table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag.action" id="row" export="true" pagesize="20" style="font-size:12">

                                                <display:column media="html" title="Edit">
                                                    <s:if test="#attr.row.flagYes">
                                                        <s:url var="urlView" namespace="/admin/role" action="edit_role" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.stRoleId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <s:a href="%{urlView}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                        </s:a>
                                                    </s:if>
                                                </display:column>

                                                <display:column media="html" title="Delete">
                                                    <s:if test="#attr.row.flagYes">
                                                        <s:url var="urlView" namespace="/admin/role" action="delete_role" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.stRoleId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <s:a href="%{urlView}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                        </s:a>
                                                    </s:if>
                                                </display:column>

                                                <display:column property="stRoleId" sortable="true" title="Id"/>
                                                <display:column property="roleName" sortable="true" title="Role.Name"/>
                                                <display:column property="createdDate" sortable="true" title="CreatedDate"
                                                                decorator="com.neurix.common.displaytag.LongDateWrapper"/>
                                                <display:column property="createdWho" sortable="true" title="CreatedWho"/>
                                                <display:column property="lastUpdate" sortable="true" title="Updated"
                                                                decorator="com.neurix.common.displaytag.LongDateWrapper"/>
                                                <display:column property="lastUpdateWho" sortable="true" title="UpdatedWho"/>
                                                <display:column property="action" sortable="true" title="Action"/>
                                                <display:column property="flag" sortable="true" title="Flag"/>
                                                <display:setProperty name="paging.banner.item_name">Role</display:setProperty>
                                                <display:setProperty name="paging.banner.items_name">Roles</display:setProperty>
                                                <display:setProperty name="export.excel.filename">Roles.xls</display:setProperty>
                                                <display:setProperty name="export.csv.filename">Roles.csv</display:setProperty>
                                                <display:setProperty name="export.pdf.filename">Roles.pdf</display:setProperty>
                                            </display:table>
                                        </td>
                                    </tr>
                                </table>
                            </s:if>

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


