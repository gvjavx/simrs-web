<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <script type='text/javascript' src='<s:url value="/dwr/interface/AreaAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BranchAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
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

                            <s:url id="urlProcess" namespace="/admin/areabranchuser" action="search_areabranchuser" includeContext="false"/>
                            <s:form id="areabranchuserForm" method="post" action="%{urlProcess}" cssClass="well form-horizontal">

                            <fieldset>
                                <legend align="left">Area-Branch-User Information</legend>

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
                                                <s:textfield id="areaId" name="areaBranchUser.areaId" required="false"/>
                                                <script>
                                                    var functions, mapped;
                                                    $('#areaId').typeahead({
                                                        minLength: 2,
                                                        source: function (query, process) {
                                                            functions = [];
                                                            mapped = {};

                                                            var data = [];
                                                            dwr.engine.setAsync(false);
                                                            AreaAction.initComboArea(query, function (listdata) {
                                                                data = listdata;
                                                            });

                                                            $.each(data, function (i, item) {
                                                                var labelItem = item.areaId + '-' + item.areaName;
                                                                mapped[labelItem] = { id: item.areaId, label: labelItem };
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
                                            <label class="control-label" for="areaBranchUser.branchId">Branch Id :</label>
                                        </td>

                                        <td>
                                            <table>
                                                <s:textfield id="branchId" name="areaBranchUser.branchId" required="false"/>
                                                <script>
                                                    var functions, mapped;
                                                    $('#branchId').typeahead({
                                                        minLength: 2,
                                                        source: function (query, process) {
                                                            functions = [];
                                                            mapped = {};

                                                            var data = [];
                                                            dwr.engine.setAsync(false);
                                                            BranchAction.initComboBranch(query, function (listdata) {
                                                                data = listdata;
                                                            });

                                                            $.each(data, function (i, item) {
                                                                var labelItem = item.branchId + '-' + item.branchName;
                                                                mapped[labelItem] = { id: item.branchId, label: labelItem };
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
                                            <label class="control-label" for="areaBranchUser.userId">User Id :</label>
                                        </td>

                                        <td>
                                            <table>
                                                <s:textfield id="userId" name="areaBranchUser.userId" required="false"/>
                                                <script>
                                                    var functions, mapped;
                                                    $('#userId').typeahead({
                                                        minLength: 2,
                                                        source: function (query, process) {
                                                            functions = [];
                                                            mapped = {};

                                                            var data = [];
                                                            dwr.engine.setAsync(false);
                                                            UserAction.initComboUser(query, function (listdata) {
                                                                data = listdata;
                                                            });

                                                            $.each(data, function (i, item) {
                                                                var labelItem = item.userId + '-' + item.username;
                                                                mapped[labelItem] = { id: item.userId, label: labelItem };
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
                                            <label class="control-label" for="areaBranchUser.flag">Flag :</label>
                                        </td>

                                        <td>
                                            <table>
                                                <s:select list="#{'Y':'Active', 'N':'NonActive'}" id="flag" name="areabranchuser.flag"
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

                                                <sj:dialog id="waiting_dialog_search" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                           resizable="false"
                                                           position="center" height="250" width="600" autoOpen="false" title="Searching...">
                                                    Please don't close this window, server is processing your request ...
                                                    </br>
                                                    </br>
                                                    </br>
                                                    <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>"
                                                    name="image_indicator_read">
                                                </sj:dialog>
                                                <sj:submit type="button" cssClass="btn btn-primary" formIds="areabranchuserForm" id="search"
                                                           name="search"
                                                           onClickTopics="showDialog" onCompleteTopics="closeDialog">
                                                    <i class="icon-search icon-white"></i>
                                                    Search
                                                </sj:submit>

                                            </table>
                                        </td>


                                        <td>
                                            <table>

                                                <s:url id="urlAdd" namespace="/admin/areabranchuser" action="add_areabranchuser" escapeAmp="false"/>

                                                <div class="btn-group">
                                                    <button class="btn dropdown-toggle" data-toggle="dropdown">
                                                        <i class="icon-briefcase"></i>
                                                        Area-Branch-User
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
                                                <button type="button" class="btn" onclick="window.location.href='<s:url action="initForm_areabranchuser"/>'">
                                                    <i class="icon-repeat"></i> Reset
                                                </button>
                                            </table>
                                        </td>

                                    </tr>
                                </table>
                            </div>


                            <table width="60%">
                                <tr>
                                    <td align="center">

                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true" resizable="false" cssStyle="text-align:left;"
                                                   position="center" height="700" width="850" autoOpen="false" title="View Detail"
                                                >
                                            <center><img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfAreaBranchUser" value="#session.listOfResult" scope="request"/>
                                        <display:table name="listOfAreaBranchUser" class="table table-condensed table-striped table-hover"
                                                       requestURI="paging_displaytag.action" id="row" pagesize="20" style="font-size:12">

                                            <display:column media="html" title="View">
                                                <s:url var="urlView" namespace="/admin/areabranchuser" action="view_areabranchuser" escapeAmp="false">
                                                    <s:param name="id"><s:property value="#attr.row.areaId"/></s:param>
                                                    <s:param name="id2"><s:property value="#attr.row.branchId"/></s:param>
                                                    <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                </s:url>
                                                <sj:a onClickTopics="showDialogUser" href="%{urlView}">
                                                    <img border="0" src="<s:url value="/pages/images/icon_lup.ico"/>" name="icon_lup">
                                                </sj:a>
                                            </display:column>
                                            <display:column media="html" title="Edit">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlView" namespace="/admin/areabranchuser" action="edit_areabranchuser" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.areaId"/></s:param>
                                                        <s:param name="id2"><s:property value="#attr.row.branchId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogUser" href="%{urlView}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>
                                            <display:column media="html" title="Delete">
                                                <s:if test="#attr.row.flagYes">
                                                    <s:url var="urlView" namespace="/admin/areabranchuser" action="delete_areabranchuser" escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.areaId"/></s:param>
                                                        <s:param name="id2"><s:property value="#attr.row.branchId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogUser" href="%{urlView}">
                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                    </sj:a>
                                                </s:if>
                                            </display:column>

                                            <display:column property="areaId" sortable="true" title="Area.Id"/>
                                            <display:column property="areaName" sortable="true" title="Area.Name"/>
                                            <display:column property="branchId" sortable="true" title="Branch.Id"/>
                                            <display:column property="branchName" sortable="true" title="Branch.Name"/>
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


