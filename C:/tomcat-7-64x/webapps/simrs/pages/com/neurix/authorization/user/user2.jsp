<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>

    <%@ include file="/pages/common/header.jsp" %>

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

                                <s:url id="urlProcess" namespace="/admin/user" action="search_user" includeContext="false"/>
                                <s:form id="userForm" method="post" action="%{urlProcess}" cssClass="well form-horizontal">

                                    <fieldset>
                                        <legend align="left">User Information</legend>

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
                                                        <s:textfield id="userId" name="users.userId" required="true"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="users.username">User Name :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:textfield id="username" name="users.username" required="true"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>

                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="users.positionId">Position :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                                                        <s:select list="#comboPosition.listOfComboPositions" id="positionId" name="users.positionId" required="true"
                                                                  listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>

                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="users.areaId">Area :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:action id="comboArea" namespace="/admin/user" name="initComboArea_user"/>
                                                        <s:select list="#comboArea.listOfComboAreas" id="areaId" name="users.areaId" required="true"
                                                                  listKey="areaId" listValue="areaName" headerKey="" headerValue="[Select one]" />
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>

                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="users.branchId">Branch :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                                        <s:select list="#comboBranch.listOfComboBranches" id="branchId" name="users.branchId" required="true"
                                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>

                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="users.roleId">Role :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:action id="comboRole" namespace="/admin/user" name="initComboRole_user"/>
                                                        <s:select list="#comboRole.listOfComboRoles" id="roleId" name="users.roleId" required="true"
                                                                  listKey="stRoleId" listValue="roleName" headerKey="" headerValue="[Select one]" />
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>

                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="users.email">Email :</label>
                                                </td>

                                                <td>
                                                    <table>
                                                        <s:textfield id="userName" name="users.email" required="true"/>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label" for="users.flag">Flag :</label>
                                                </td>

                                                <td>
                                                    <table>

                                                        <s:select list="#{'Y':'Active', 'N':'NonActive'}" id="flag" name="users.flag"
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

                                                        <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                                   resizable="false"
                                                                   position="center" height="250" width="600" autoOpen="false" title="Searching...">
                                                            Please don't close this window, server is processing your request ...
                                                            </br>
                                                            </br>
                                                            </br>
                                                            <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>"
                                                                 name="image_indicator_read">
                                                        </sj:dialog>
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="userForm" id="search"
                                                                   name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog">
                                                            <i class="icon-search icon-white"></i>
                                                            Search
                                                        </sj:submit>

                                                    </table>
                                                </td>


                                                <td>
                                                    <table>

                                                        <s:url id="urlAdd" namespace="/admin/user" action="add_user" escapeAmp="false"/>

                                                        <div class="btn-group">
                                                            <button class="btn dropdown-toggle" data-toggle="dropdown">
                                                                <i class="icon-briefcase"></i>
                                                                User
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
                                                        <button type="button" class="btn" onclick="window.location.href='<s:url action="initForm_user"/>'">
                                                            <i class="icon-repeat"></i> Reset
                                                        </button>
                                                    </table>
                                                </td>

                                            </tr>
                                        </table>
                                    </div>


                                    <table width="100%">
                                        <tr>
                                            <td align="center">

                                                <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true" resizable="false" cssStyle="text-align:left;"
                                                           position="center" height="650" width="700" autoOpen="false" title="View Detail"
                                                >
                                                    <center><img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" alt="Loading..."/></center>
                                                </sj:dialog>

                                                <s:set name="listOfUsers" value="#session.listOfResult" scope="request"/>
                                                <display:table name="listOfUsers" class="table table-condensed table-striped table-hover"
                                                               requestURI="paging_displaytag.action" id="row" export="true" pagesize="20" style="font-size:11">

                                                    <display:column media="html" title="View">
                                                        <s:url var="urlView" namespace="/admin/user" action="view_user" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.userId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <sj:a onClickTopics="showDialogUser" href="%{urlView}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">
                                                        </sj:a>
                                                    </display:column>
                                                    <display:column media="html" title="Edit">
                                                        <s:if test="#attr.row.flagYes">
                                                            <s:url var="urlView" namespace="/admin/user" action="edit_user" escapeAmp="false">
                                                                <s:param name="id"><s:property value="#attr.row.userId"/></s:param>
                                                                <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                            </s:url>
                                                            <sj:a onClickTopics="showDialogUser" href="%{urlView}">
                                                                <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                            </sj:a>
                                                        </s:if>
                                                    </display:column>
                                                    <display:column media="html" title="Delete">
                                                        <s:if test="#attr.row.flagYes">
                                                            <s:url var="urlView" namespace="/admin/user" action="delete_user" escapeAmp="false">
                                                                <s:param name="id"><s:property value="#attr.row.userId"/></s:param>
                                                                <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                            </s:url>
                                                            <sj:a onClickTopics="showDialogUser" href="%{urlView}">
                                                                <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                            </sj:a>
                                                        </s:if>
                                                    </display:column>

                                                    <display:column property="userId" sortable="true" title="Id"/>
                                                    <display:column property="username" sortable="true" title="Name"/>
                                                    <display:column property="positionName" sortable="true" title="Position"/>
                                                    <display:column property="roleName" sortable="true" title="Role"/>
                                                    <display:column property="areaName" sortable="true" title="Area"/>
                                                    <display:column property="branchName" sortable="true" title="Branch"/>
                                                    <display:column property="email" sortable="true" title="Email"/>
                                                    <display:column property="previewPhoto" media="html" title="Preview"/>
                                                    <display:column property="createdDate" sortable="true" title="CreatedDate"
                                                                    decorator="com.neurix.common.displaytag.LongDateWrapper" style="font-size:9"/>
                                                    <display:column property="createdWho" sortable="true" title="CreatedWho"/>
                                                    <display:column property="lastUpdate" sortable="true" title="Updated"
                                                                    decorator="com.neurix.common.displaytag.LongDateWrapper" style="font-size:9"/>
                                                    <display:column property="lastUpdateWho" sortable="true" title="UpdatedWho"/>
                                                    <display:column property="action" sortable="true" title="Action"/>
                                                    <display:column property="flag" sortable="true" title="Flag"/>
                                                    <display:setProperty name="paging.banner.item_name">User</display:setProperty>
                                                    <display:setProperty name="paging.banner.items_name">Users</display:setProperty>
                                                    <display:setProperty name="export.excel.filename">Users.xls</display:setProperty>
                                                    <display:setProperty name="export.csv.filename">Users.csv</display:setProperty>

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


