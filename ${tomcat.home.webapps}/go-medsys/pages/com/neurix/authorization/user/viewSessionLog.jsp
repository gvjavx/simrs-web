<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%--<link href="<s:url value="/pages/bootstrap/css/bootstrap.css"/>" rel="stylesheet" media="screen">--%>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RoleFuncAction.js"/>'></script>
    <script type="text/javascript">

        function saveBtn() {

            var arrCheckedFuncId = new Array();
            var listOfFunc=document.getElementsByName('funcIdChecked');
            var j=0;
            for (var i = 0; i < listOfFunc.length; i++) {
                if (document.getElementsByName('funcIdChecked')[i].checked) {
                    arrCheckedFuncId[j]=document.getElementsByName('funcIdChecked')[i].value;
                    j++;
                }
            }

            var addFlag = document.getElementById('addFlag').value;
            var editFlag = document.getElementById('editFlag').value;
            var roleId = document.getElementById('roleId').value;
            if (roleId == '') { //handle anomali when get from popup edit (mis get)
                roleId = '<s:property value="roleId"/>';
            }

            var menuId = document.getElementById('menuId').value;

//            dwr.engine.setAsync(false);
            RoleFuncAction.saveListFunction(arrCheckedFuncId,addFlag,editFlag,roleId,menuId, function (message) {
                if (message=='00') {
                    $('#view_dialog_function').dialog('close');
                } else {
                    document.getElementById('errorMessageFunc').innerHTML = 'Error when saving detail funtion to session. Please inform this to your admin.';
                    $.publish('showErrorDialogFunc');
                }
            });

        };

        function cancelFuncBtn() {
            $('#view_dialog_function').dialog('close');
        };

        function setReadOnlyCheckbox(){
            var listOfMenu=document.getElementsByName('funcIdChecked');
            for (var i = 0; i < listOfMenu.length; i++) {
                document.getElementsByName('funcIdChecked')[i].disabled=true;
            }
        }

    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <div>
                <table>
                    <tr>
                        <td>
                            <table width="100%">
                                <tr><td></td></tr>
                                <tr><td></td></tr>
                                <tr>
                                    <td align="center">
                                        <sj:dialog id="view_dialog" openTopics="showDialog" modal="true" resizable="false" cssStyle="text-align:left;"
                                                   position="center" height="500" width="700" autoOpen="false" title="Kill Session"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                        <s:set name="listOfUserSessionLog" value="#session.listOfResult" scope="request"/>
                                        <display:table name="listOfUserSessionLog" class="table table-condensed table-striped table-hover"
                                                       requestURI="" id="row"  export="true" style="font-size:11">

                                            <display:column media="html" title="<small>Kill Session</small>" style="text-align:center;font-size:11">
                                                <s:if test = "%{#attr.row.enabledKill}" >
                                                    <s:url var="urlViewKill" namespace="/admin/usersessionlog" action="view_usersessionlog"
                                                           escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.stId" /></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialog" href="%{urlViewKill}">
                                                        <img border="0" src="<s:url value="/pages/images/kill_session.ico"/>" name="icon_kill">
                                                    </sj:a>
                                                </s:if>
                                                <s:else>
                                                    <img border="0" src="<s:url value="/pages/images/icon_trash_error.ico"/>" name="icon_error">
                                                </s:else>
                                            </display:column>

                                            <display:column property="stId" sortable="true" title="Id"/>
                                            <display:column property="sessionId" sortable="true" title="Session.Id"/>
                                            <display:column property="userName" sortable="true" title="User"/>
                                            <display:column property="companyName" sortable="true" title="Company"/>
                                            <display:column property="branchName" sortable="true" title="Branch"/>
                                            <display:column property="areaName" sortable="true" title="Area"/>
                                            <display:column property="ipAddress" sortable="true" title="Ip.Address"/>
                                            <display:column property="loginTimestamp" sortable="true" title="Login Time"
                                                            decorator="com.neurix.common.displaytag.LongDateWrapper"/>
                                            <display:column property="logoutTimestamp" sortable="true" title="Logout Time"
                                                            decorator="com.neurix.common.displaytag.LongDateWrapper"/>
                                            <display:setProperty name="paging.banner.item_name">UserSessionLog</display:setProperty>
                                            <display:setProperty name="paging.banner.items_name">UserSessionLogs</display:setProperty>
                                            <display:setProperty name="export.excel.filename">UserSessionLogs.xls</display:setProperty>
                                            <display:setProperty name="export.csv.filename">UserSessionLogs.csv</display:setProperty>
                                            <display:setProperty name="export.pdf.filename">UserSessionLogs.pdf</display:setProperty>

                                        </display:table>
                                    </td>
                                </tr>
                            </table>

                        </td>
                    </tr>

                </table>
            </div>
        </td>
    </tr>
</table>

</body>
</html>

