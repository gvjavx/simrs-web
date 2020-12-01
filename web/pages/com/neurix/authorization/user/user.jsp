<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 15/02/2018
  Time: 16.59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <style>
        .pagebanner{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
        }
        .pagelinks{
            background-color: #ededed;
            width: 100%;
            font-size: 14px;
            margin-bottom: 30px;
        }
    </style>

</head>

<body class="hold-transition skin-blue sidebar-mini" onload="showAlert()">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            User Information
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Form</h3>
                    </div>

                    <s:url id="urlProcess" namespace="/admin/user" action="search_user" includeContext="false"/>
                        <div class="box-body">
                            <div class="form-group">
                                <s:form id="userForm" method="post" theme="simple" action="%{urlProcess}" cssClass="well form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="users.userId">User Id :</label>
                                        <div class="col-sm-3">
                                            <s:textfield id="userId" name="users.userId" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="users.username">Username :</label>
                                        <div class="col-sm-3">
                                            <s:textfield id="userId" name="users.username" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="users.positionId">Area :</label>
                                        <div class="col-sm-3">
                                            <s:action id="comboArea" namespace="/admin/user" name="initComboArea_user"/>
                                            <s:select list="#comboArea.listOfComboAreas" id="areaId" name="users.areaId"
                                                      listKey="areaId" listValue="areaName" headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="users.branchId">Unit :</label>
                                        <div class="col-sm-3">
                                            <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                            <s:select list="#comboBranch.listOfComboBranches" id="branchid" name="users.branchId" onchange="listPosisi()"
                                                      listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="users.divisiId">Divisi/Bidang :</label>
                                        <div class="col-sm-3">
                                            <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                            <s:select list="#comboDivisi.listComboDepartment" id="users.divisiId" name="users.divisiId"
                                                      listKey="departmentId" listValue="departmentName" headerKey="" headerValue="" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="users.positionId">Posisi/Jabatan :</label>
                                        <div class="col-sm-3">
                                            <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                                            <s:select list="#comboPosition.listOfComboPositions" id="positionId" name="users.positionId"
                                                      listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="users.roleId">Role :</label>
                                        <div class="col-sm-3">
                                            <s:action id="comboRole" namespace="/admin/user" name="initComboRole_user"/>
                                            <s:select list="#comboRole.listOfComboRoles" id="roleId" name="users.roleId"
                                                      listKey="stRoleId" listValue="roleName" headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="users.email">Email :</label>
                                        <div class="col-sm-3">
                                            <s:textfield id="email" name="users.email" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="users.roleId">Flag :</label>
                                        <div class="col-sm-3">
                                            <s:select list="#{'Y':'Active', 'N':'NonActive'}" id="flag" name="users.flag"
                                                      headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                        </div>
                                    </div>

                                    <div id="crud"></div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4" style="visibility: hidden"></label>

                                        <div style="padding-left: 140px" class="col-sm-4">
                                            <sj:dialog id="waiting_dialog" openTopics="showDialog1" closeTopics="closeDialog" modal="true"
                                                       resizable="false"
                                                       height="250" width="600" autoOpen="false" title="Searching...">
                                                Please don't close this window, server is processing your request ...
                                                </br>
                                                </br>
                                                </br>
                                                <center>
                                                    <img border="0" src="<s:url value="/pages/images/loading11.gif"/>" name="image_indicator_read">
                                                </center>
                                            </sj:dialog>
                                            <sj:submit type="button" cssClass="btn btn-primary" formIds="userForm" id="search"
                                                       name="search"
                                                       onClickTopics="showDialog" onCompleteTopics="closeDialog">
                                                <i class="fa fa-search"></i>
                                                Search
                                            </sj:submit>

                                            <div class="btn-group">
                                                <s:url id="urlAdd" namespace="/admin/user" action="add_user" escapeAmp="false"/>
                                                <button class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                                                    <i class="fa fa-edit"></i>
                                                    User
                                                    <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu">
                                                    <li><s:a href="%{urlAdd}"><i class="fa fa-plus"></i> Add</s:a></li>
                                                </ul>
                                            </div>

                                            <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_user"/>'">
                                                <i class="fa fa-refresh"></i> Reset
                                            </button>
                                        </div>

                                    </div>

                                    <br><br>

                                    <table width="100%">
                                        <tr>
                                            <td align="center">

                                                <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true" resizable="false" cssStyle="text-align:left;"
                                                           height="650" width="700" autoOpen="false" title="View Detail"
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
                                                    <display:column property="positionName" sortable="true" title="Posisi"/>
                                                    <display:column property="roleName" sortable="true" title="Role"/>
                                                    <display:column property="areaName" sortable="true" title="Area"/>
                                                    <display:column property="branchName" sortable="true" title="Unit"/>
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

                        </div>


                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@ include file="/pages/common/footer.jsp" %>


<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>



