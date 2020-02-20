<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 15/02/2018
  Time: 16.59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
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

    <script type='text/javascript' src='<s:url value="/dwr/interface/FunctionAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RoleAction.js"/>'></script>

</head>

<body class="hold-transition skin-blue sidebar-mini" onload="showAlert()">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Role Function Information
            <small>e-HEALTH</small>
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

                    <s:url id="urlProcess" namespace="/admin/rolefunction" action="search_rolefunc" includeContext="false"/>


                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="rolefuncForm" method="post" action="%{urlProcess}" cssClass="well form-horizontal">

                                <table align="center">
                                    <tr>
                                        <td>
                                            <label>Role Id </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="roleId" name="roleFunc.stRoleId" required="false" cssClass="form-control"
                                                         cssStyle="margin-top: -30px; margin-left: 20px" />
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
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Function Id</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:textfield id="functionId" name="roleFunc.stFunctionId" required="false" cssClass="form-control"
                                                         cssStyle="margin-top: -30px; margin-left: 20px" />
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
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Flag </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:select list="#{'Y':'Active', 'N':'NonActive'}" id="flag" name="roleFunc.flag" cssClass="form-control"
                                                      headerKey="" headerValue="[Select one]" cssStyle="margin-top: -30px; margin-left: 20px"/>
                                        </td>
                                    </tr>
                                </table>
                                <br>

                                <table align="center">
                                    <tr>
                                        <td>
                                            <table>

                                                <sj:dialog id="waiting_dialog_search" openTopics="showDialogSearch1" closeTopics="closeDialogSearch" modal="true"
                                                           resizable="false" height="250" width="600" autoOpen="false" title="Searching...">
                                                    Please don't close this window, server is processing your request ...
                                                    </br>
                                                    </br>
                                                    </br>
                                                    <center>
                                                        <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_read">
                                                    </center>
                                                </sj:dialog>
                                                <sj:submit type="button" cssClass="btn btn-primary" formIds="rolefuncForm" id="search" name="search"
                                                           onClickTopics="showDialogSearch" onCompleteTopics="closeDialogSearch">
                                                    <i class="fa fa-search"></i>
                                                    Search
                                                </sj:submit>

                                            </table>
                                        </td>


                                        <td>
                                            <table>

                                                <s:url id="urlAdd" namespace="/admin/rolefunction" action="add_rolefunc" escapeAmp="false"/>

                                                <div class="btn-group">
                                                    <button class="btn dropdown-toggle btn-success" data-toggle="dropdown">
                                                        <i class="fa fa-edit"></i>
                                                        Role-Func
                                                        <span class="caret"></span>
                                                    </button>
                                                    <ul class="dropdown-menu">
                                                        <li><s:a href="%{urlAdd}"><i class="fa fa-plus"></i> Add</s:a></li>
                                                    </ul>
                                                </div>

                                            </table>
                                        </td>

                                        <td>
                                            <table>
                                                <a type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_rolefunc"/>'">
                                                    <i class="fa fa-refresh"></i> Reset
                                                </a>
                                            </table>
                                        </td>

                                    </tr>
                                </table>

                                <br><br>
                                <center>
                                    <table width="40%">
                                        <tr>
                                            <td align="center">

                                                <sj:dialog id="view_dialog_menu" openTopics="showDialogMenu" modal="true" resizable="false" cssStyle="text-align:left;"
                                                           height="800" width="1200" autoOpen="false" title="View Detail Menu"
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
                                </center>


                            </s:form>
                        </div>
                    </div>
                </div>
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



