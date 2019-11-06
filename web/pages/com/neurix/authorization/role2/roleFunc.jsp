<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/FunctionAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RoleAction.js"/>'></script>
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

                            <s:url id="urlProcess" namespace="/admin/rolefunction" action="search_rolefunc" includeContext="false"/>
                            <s:form id="rolefuncForm" method="post" action="%{urlProcess}" cssClass="well form-horizontal">

                            <fieldset>
                                <legend align="left">Role-Function Information</legend>

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
                                                <s:textfield id="roleId" name="roleFunc.stRoleId" required="false"/>
                                                <script>
                                                    var functions, mapped;
                                                    $('#roleId').typeahead({
                                                        minLength: 2,
                                                        source: function (query, process) {
                                                            functions = [];
                                                            mapped = {};

                                                            var data = [];
                                                            dwr.engine.setAsync(false);
                                                            RoleAction.initComboRole(query, function (listdata) {
                                                                data = listdata;
                                                            });

                                                            $.each(data, function (i, item) {
                                                                var labelItem = item.stRoleId + '-' + item.roleName;
                                                                mapped[labelItem] = { id: item.stRoleId, label: labelItem };
                                                                functions.push(labelItem);
                                                            });

                                                            process(functions);
                                                        },
                                                        updater: function (item) {
                                                            var selectedObj = mapped[item];
                                                            return selectedObj.id;
                                                        }
                                                    });
                                                </script>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                                <table>
                                    <tr>
                                        <td>
                                            <label class="control-label" for="roleFunc.stFunctionId">Function Id :</label>
                                        </td>

                                        <td>
                                            <table>
                                                <s:textfield id="functionId" name="roleFunc.stFunctionId" required="false"/>
                                                <script>
                                                    var functions, mapped;
                                                    $('#functionId').typeahead({
                                                        minLength: 2,
                                                        source: function (query, process) {
                                                            functions = [];
                                                            mapped = {};

                                                            var data = [];
                                                            dwr.engine.setAsync(false);
                                                            FunctionAction.initComboFunction(query, function (listdata) {
                                                                data = listdata;
                                                            });

                                                            $.each(data, function (i, item) {
                                                                var labelItem = item.stFuncId + '-' + item.funcName;
                                                                mapped[labelItem] = { id: item.stFuncId, label: labelItem };
                                                                functions.push(labelItem);
                                                            });

                                                            process(functions);
                                                        },
                                                        updater: function (item) {
                                                            var selectedObj = mapped[item];
                                                            return selectedObj.id;
                                                        }
                                                    });
                                                </script>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                                <table>
                                    <tr>
                                        <td>
                                            <label class="control-label" for="roleFunc.flag">Flag :</label>
                                        </td>

                                        <td>
                                            <table>
                                                <s:select list="#{'Y':'Active', 'N':'NonActive'}" id="flag" name="roleFunc.flag"
                                                          headerKey="" headerValue="[Select one]"/>
                                            </table>
                                        </td>
                                    </tr>
                                </table>

                            </fieldset>


                            <div id="actions" class="form-actions">
                                <table>
                                    <tr>
                                            <td>
                                                <table>

                                                    <sj:dialog id="waiting_dialog_search" openTopics="showDialogSearch" closeTopics="closeDialogSearch" modal="true"
                                                               resizable="false" position="center" height="250" width="600" autoOpen="false" title="Searching...">
                                                        Please don't close this window, server is processing your request ...
                                                        </br>
                                                        </br>
                                                        </br>
                                                        <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_read">
                                                    </sj:dialog>
                                                    <sj:submit type="button" cssClass="btn btn-primary" formIds="rolefuncForm" id="search" name="search"
                                                               onClickTopics="showDialogSearch" onCompleteTopics="closeDialogSearch">
                                                        <i class="icon-search icon-white"></i>
                                                        Search
                                                    </sj:submit>

                                                </table>
                                            </td>


                                            <td>
                                                <table>

                                                    <s:url id="urlAdd" namespace="/admin/rolefunction" action="add_rolefunc" escapeAmp="false"/>

                                                    <div class="btn-group">
                                                        <button class="btn dropdown-toggle" data-toggle="dropdown">
                                                             <i class="icon-briefcase"></i>
                                                             Role-Func
                                                             <span class="caret"></span>
                                                        </button>
                                                        <ul class="dropdown-menu">
                                                             <li><s:a href="%{urlAdd}"><i class="icon-plus"></i> Add</s:a></li>
                                                        </ul>
                                                    </div>

                                                </table>
                                            </td>

                                            <td>
                                                <table>
                                                    <button type="button" class="btn" onclick="window.location.href='<s:url action="initForm_rolefunc"/>'">
                                                        <i class="icon-repeat"></i> Reset
                                                    </button>
                                                </table>
                                            </td>

                                    </tr>
                                </table>
                            </div>


                            <table width="40%">
                                <tr>
                                    <td align="center">

                                            <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true" resizable="false" cssStyle="text-align:left;"
                                                       position="center" height="800" width="1200" autoOpen="false" title="View Detail Menu"
                                                    >
                                                <center><img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfRoleFunc" value="#session.listOfResult" scope="request"/>
                                            <display:table name="listOfRoleFunc" class="table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag.action" id="row" pagesize="20" style="font-size:12">

                                                <display:column media="html" title="View">
                                                        <s:url var="urlView" namespace="/admin/rolefunction" action="view_rolefunc" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.roleId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenu" href="%{urlView}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">
                                                        </sj:a>
                                                </display:column>
                                                <display:column media="html" title="Edit">
                                                    <s:if test="#attr.row.flagYes">
                                                        <s:url var="urlEdit" namespace="/admin/rolefunction" action="edit_rolefunc" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.roleId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenu" href="%{urlEdit}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                        </sj:a>
                                                    </s:if>
                                                </display:column>
                                                <display:column media="html" title="Delete">
                                                    <s:if test="#attr.row.flagYes">
                                                        <s:url var="urlDelete" namespace="/admin/rolefunction" action="delete_rolefunc" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.roleId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogMenu" href="%{urlDelete}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                        </sj:a>
                                                    </s:if>
                                                </display:column>

                                                <display:column property="roleId" sortable="true" title="Role.Id"/>
                                                <display:column property="roleName" sortable="true" title="Role.Name"/>
                                                <display:column property="flag" title="Flag"/>
                                            </display:table>

                                    </td>
                                </tr>
                            </table>

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


