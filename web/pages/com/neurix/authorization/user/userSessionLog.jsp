<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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


    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script>
        function urlSubmit(){
            var id = $('#userId').val();
            var from = $('#loginTimestampFrom').val();
            var to = $('#loginTimestampTo').val();
            //alert(id + ' ' + from + ' ' + to);
            var url = "searchCount_usersessionlog?userName="+id+"&stLoginTimestampFrom="+from+"&stLoginTimestampTo="+to ;
            //alert(url);
            location.href = url;
        }

    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >
<%@ include file="/pages/common/headerNav.jsp" %>


<ivelincloud:mainMenu/>

<div class="content-wrapper">

    <section class="content-header">
        <h1>
            User Session Log Information
            <small>e-HEALTH</small>
        </h1>
    </section>

    <div class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Form</h3>
                    </div>
                    <s:url id="urlProcess" namespace="/admin/usersessionlog" action="search_usersessionlog" includeContext="false"/>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="userSessionLogForm" cssStyle="align-items: center" method="post" action="%{urlProcess}" theme="simple"  cssClass="well form-horizontal">
                                <table>
                                    <tr>
                                        <td width="10%" align="center">
                                            <%@ include file="/pages/common/message.jsp" %>
                                        </td>
                                    </tr>
                                </table>

                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="userSessionLog.flag">Status Session :</label>
                                    <div class="col-sm-3">
                                        <s:select list="#{'N':'Non-Active'}" id="flag" name="userSessionLog.flag"
                                                  headerKey="Y" headerValue="Active" cssClass="form-control" cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="userSessionLog.flag">User :</label>
                                    <div class="col-sm-3">
                                        <s:textfield id="userId" name="userSessionLog.userName" cssClass="form-control"
                                                     required="false" cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
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
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="userSessionLog.stLoginTimestampFrom">From :</label>
                                    <div class="col-sm-3">

                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="loginTimestampFrom" name="userSessionLog.stLoginTimestampFrom" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                                <%--<input type="text" class="form-control pull-right" id="loginTimestampFrom" name="userSessionLog.stLoginTimestampFrom">--%>
                                        </div>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="userSessionLog.stLoginTimestampTo">To :</label>
                                    <div class="col-sm-3">
                                        <div class="input-group date">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <s:textfield id="loginTimestampTo" name="userSessionLog.stLoginTimestampTo" cssClass="form-control pull-right"
                                                         required="false"  cssStyle=""/>
                                                <%--<input type="text" format="dd-mm-yyyy" class="form-control pull-right" id="loginTimestampTo" name="userSessionLog.stLoginTimestampTo">--%>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4" style="visibility: hidden">To :</label>
                                    <div style="padding-left: 140px" class="col-sm-4">
                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="userSessionLogForm" id="search" name="search"
                                                   onClickTopics="showDialogSearch" onCompleteTopics="closeDialogSearch">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>

                                        <s:url var="ajax" namespace="/admin/usersessionlog" action="searchCount_usersessionlog" escapeAmp="false">
                                            <s:param name="userName" value="<script> $('#userId').val(); </script>"></s:param>
                                            <s:param name="stLoginTimestampFrom" value="aaa"></s:param>
                                            <s:param name="stLoginTimestampTo" value="aaaaaa"></s:param>
                                        </s:url>
                                        <a onclick="urlSubmit()" class="btn btn-success">
                                            <i class="fa fa-search"></i>
                                            Count Session
                                        </a>

                                        <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_usersessionlog"/>'">
                                            <i class="fa fa-refresh"></i> Reset
                                        </button>
                                    </div>
                                </div>

                                <div id="actions" class="form-actions" align="center">
                                    <sj:dialog id="waiting_dialog_search" openTopics="showDialogSearch1" closeTopics="closeDialogSearch" modal="true"
                                               resizable="false" height="250" width="600" autoOpen="false" title="Searching...">
                                        Please don't close this window, server is processing your request ...
                                        </br>
                                        </br>
                                        </br>
                                        <img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" name="image_indicator_read">
                                    </sj:dialog>
                                </div>
                            </s:form>
                            <br>
                            <s:if test="isSearch()">
                                <table width="100%">
                                    <tr>
                                        <td align="center">

                                            <sj:dialog id="view_dialog" openTopics="showDialog" modal="true" resizable="false" cssStyle="text-align:left;"
                                                       position="center" height="500" width="700" autoOpen="false" title="Kill Session"
                                            >
                                                <center><img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfUserSessionLog" value="#session.listOfResult" scope="request"/>
                                            <display:table name="listOfUserSessionLog" class="table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag.action" id="row" pagesize="30" export="true" style="font-size:11">

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
                            </s:if>
                            <s:else>
                                <table align="center" width="40%">
                                    <tr>
                                        <td align="center">
                                            <s:set name="listOfUserSessionLogCount" value="#session.listOfResultCount" scope="request"/>
                                            <display:table name="listOfUserSessionLogCount" class="table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag.action" id="row" pagesize="30" export="false" style="font-size:13">

                                                <display:column property="userName" sortable="true" title="id"/>
                                                <display:column property="name" sortable="true" title="User Name"/>
                                                <display:column property="jumlah" sortable="true" title="Jumlah Login"/>

                                                <display:column property="stLoginTimestampFrom" sortable="true" title="Last Login"/>


                                            </display:table>

                                        </td>
                                    </tr>
                                </table>
                            </s:else>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>


</body>
</html>

<script>
    $(document).ready(function() {
        $('#loginTimestampTo').datepicker({
            dateFormat: 'dd-mm-yy'
        });
        $('#loginTimestampFrom').datepicker({
            dateFormat: 'dd-mm-yy'
        });
    });
</script>


