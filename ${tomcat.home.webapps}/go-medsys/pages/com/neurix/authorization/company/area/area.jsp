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

    <script type='text/javascript'>

        $.subscribe('beforeProcessSave', function (event, data) {

            if (document.getElementById("areaName").value != '') {

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
                if (document.getElementById("areaName").value == '') {
                    msg = 'Field <strong>Area Name</strong> is required.' + '<br/>';
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
            document.getElementById("areaId").value = '';
            document.getElementById("areaName").value = '';
        }


    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini" >
<%@ include file="/pages/common/headerNav.jsp" %>


<ivelincloud:mainMenu/>

<div class="content-wrapper">

    <section class="content-header">
        <h1>
            Area Information
        </h1>
    </section>

    <div class="content">
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Search Form</h3>
                    </div>

                    <s:if test="isAddOrEdit() || isDelete()">
                        <s:url id="urlProcess" namespace="/admin/area" action="save_area" includeContext="false"/>
                    </s:if>
                    <s:else>
                        <s:url id="urlProcess" namespace="/admin/area" action="search_area" includeContext="false"/>
                    </s:else>

                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="areaForm" method="post" action="%{urlProcess}" theme="simple" cssClass="well form-horizontal">
                                <s:hidden name="addOrEdit"/>
                                <s:hidden id="add" name="add"/>
                                <s:hidden name="delete"/>

                                <table>
                                    <tr>
                                        <td width="10%" align="center">
                                            <%@ include file="/pages/common/message.jsp" %>
                                        </td>
                                    </tr>
                                </table>

                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="area.areaId">Area Id :</label>
                                    <s:if test="isAddOrEdit()">
                                        <div class="col-sm-3">
                                            <s:textfield id="areaId" name="area.areaId" required="true" readonly="true" cssClass="form-control"
                                                         cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                        </div>
                                    </s:if>
                                    <s:elseif test="isDelete()">
                                        <div class="col-sm-3">
                                            <s:textfield id="areaId" readonly="true" name="area.areaId" cssClass="form-control"
                                                         cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                        </div>
                                    </s:elseif>
                                    <s:else>
                                        <div class="col-sm-3">
                                            <s:textfield id="areaId" name="area.areaId" required="false" cssClass="form-control"
                                                         cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                        </div>
                                    </s:else>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-5" for="area.areaName">Area Name :</label>
                                    <s:if test="isAddOrEdit()">
                                        <s:if test="isAdd()">
                                            <div class="col-sm-3">
                                                <s:textfield id="areaName" name="area.areaName" required="true" cssClass="form-control"
                                                             cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                            </div>
                                        </s:if>
                                        <s:else>
                                            <div class="col-sm-3">
                                                <s:textfield id="areaName" name="area.areaName" required="true" cssClass="form-control"
                                                             cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                            </div>
                                        </s:else>
                                    </s:if>
                                    <s:elseif test="isDelete()">
                                        <div class="col-sm-3">
                                            <s:textfield id="areaName" readonly="true" name="area.areaName" cssClass="form-control"
                                                         cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                        </div>
                                    </s:elseif>
                                    <s:else>
                                        <div class="col-sm-3">
                                            <s:textfield id="areaName" name="area.areaName" required="false" cssClass="form-control"
                                                         cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                        </div>
                                    </s:else>
                                </div>

                                <s:if test="!(isAddOrEdit() || isDelete())">
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="area.areaName">Flag :</label>
                                        <div class="col-sm-3">
                                            <s:select list="#{'Y':'Active', 'N':'NonActive'}" cssClass="form-control" id="flag" name="area.flag"
                                                      headerKey="" headerValue="[Select one]"/>
                                        </div>
                                    </div>
                                </s:if>

                                    <div id="crud"></div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4" style="visibility: hidden"></label>

                                        <div style="padding-left: 140px" class="col-sm-4">
                                            <s:if test="isAddOrEdit()">
                                                <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="areaForm" id="save" name="save"
                                                           onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                           onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                    <i class="fa fa-check"></i>
                                                    Save
                                                </sj:submit>

                                                <sj:dialog id="waiting_dialog" openTopics="showDialog"
                                                           closeTopics="closeDialog" modal="true"
                                                           resizable="false"
                                                           height="250" width="600" autoOpen="false"
                                                           title="Saving ...">
                                                    Please don't close this window, server is processing your request ...
                                                    <br>
                                                    <center>
                                                        <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                             src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                             name="image_indicator_write">
                                                        <br>
                                                        <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                             src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                             name="image_indicator_write">
                                                    </center>
                                                </sj:dialog>

                                                <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                           height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                           buttons="{
                                                                              'OK':function() {
                                                                                        $('#info_dialog').dialog('close');
                                                                                        if (document.getElementById('add').value == 'true') {
                                                                                            resetField();
                                                                                        }
                                                                                   }
                                                                            }"
                                                >
                                                    <center>
                                                        <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                                    </center>
                                                    Record has been saved successfully.
                                                </sj:dialog>

                                                <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                           height="250" width="600" autoOpen="false" title="Error Dialog"
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
                                                           height="280" width="500" autoOpen="false" title="Warning"
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

                                                <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_area"/>'">
                                                    <i class="fa fa-refresh"></i> Reset
                                                </button>
                                            </s:if>

                                            <s:elseif test="isDelete()">
                                                <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="areaForm"
                                                           id="delete" name="delete"
                                                           onBeforeTopics="beforeProcessDelete" onCompleteTopics="closeDialog,successDialog"
                                                           onSuccessTopics="successDialog" onErrorTopics="errorDialog">
                                                    <i class="fa fa-trash"></i>
                                                    Delete
                                                </sj:submit>

                                                <sj:dialog id="waiting_dialog" openTopics="showDialog"
                                                           closeTopics="closeDialog" modal="true"
                                                           resizable="false"
                                                           height="250" width="600" autoOpen="false"
                                                           title="Saving ...">
                                                    Please don't close this window, server is processing your request ...
                                                    <br>
                                                    <center>
                                                        <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                             src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                             name="image_indicator_write">
                                                        <br>
                                                        <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                             src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                             name="image_indicator_write">
                                                    </center>
                                                </sj:dialog>

                                                <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                           height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                           buttons="{
                                                                                'OK':function() { $('#info_dialog').dialog('close'); }
                                                                            }"
                                                >
                                                    <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                                    Record has been deleted successfully.
                                                </sj:dialog>

                                                <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                           height="250" width="600" autoOpen="false" title="Error Dialog"
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

                                                <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_area"/>'">
                                                    <i class="fa fa-refresh"></i> Reset
                                                </button>
                                            </s:elseif>

                                            <s:else>
                                                <sj:dialog id="waiting_dialog" openTopics="showDialog1"
                                                           closeTopics="closeDialog" modal="true"
                                                           resizable="false"
                                                           height="250" width="600" autoOpen="false"
                                                           title="Saving ...">
                                                    Please don't close this window, server is processing your request ...
                                                    <br>
                                                    <center>
                                                        <img border="0" style="width: 130px; height: 120px; margin-top: 20px"
                                                             src="<s:url value="/pages/images/sayap-logo-nmu.png"/>"
                                                             name="image_indicator_write">
                                                        <br>
                                                        <img class="spin" border="0" style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                             src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                             name="image_indicator_write">
                                                    </center>
                                                </sj:dialog>

                                                <sj:submit type="button" cssClass="btn btn-primary" formIds="areaForm" id="search"
                                                           name="search"
                                                           onClickTopics="showDialog" onCompleteTopics="closeDialog">
                                                    <i class="fa fa-search"></i>
                                                    Search
                                                </sj:submit>

                                                <div class="btn-group">
                                                    <s:url id="urlAdd" namespace="/admin/area" action="add_area" escapeAmp="false"/>
                                                    <button class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                                                        <i class="fa fa-edit"></i>
                                                        Area
                                                        <span class="caret"></span>
                                                    </button>
                                                    <ul class="dropdown-menu">
                                                        <li><s:a href="%{urlAdd}"><i class="fa fa-plus"></i> Add</s:a></li>
                                                    </ul>
                                                </div>

                                                <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_area"/>'">
                                                    <i class="fa fa-refresh"></i> Reset
                                                </button>
                                            </s:else>
                                        </div>

                                    </div>
                                    <br>
                                    <center>
                                        <s:if test="!isAddOrEdit() && !isDelete()">
                                            <table align="center">
                                                <tr>
                                                    <td align="center">

                                                        <s:set name="listOfAreas" value="#session.listOfResult" scope="request"/>
                                                        <display:table name="listOfAreas" class="table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag.action" id="row" export="true" pagesize="20" style="font-size:12">

                                                            <display:column media="html" title="Edit">
                                                                <s:if test="#attr.row.flagYes">
                                                                    <s:url var="urlView" namespace="/admin/area" action="edit_area" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.areaId"/></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                    </s:url>
                                                                    <s:a href="%{urlView}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                    </s:a>
                                                                </s:if>
                                                            </display:column>

                                                            <display:column media="html" title="Delete">
                                                                <s:if test="#attr.row.flagYes">
                                                                    <s:url var="urlView" namespace="/admin/area" action="delete_area" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.areaId"/></s:param>
                                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                                    </s:url>
                                                                    <s:a href="%{urlView}">
                                                                        <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                                    </s:a>
                                                                </s:if>
                                                            </display:column>

                                                            <display:column property="areaId" sortable="true" title="Id"/>
                                                            <display:column property="areaName" sortable="true" title="Area.Name"/>
                                                            <display:column property="createdDate" sortable="true" title="CreatedDate"
                                                                            decorator="com.neurix.common.displaytag.LongDateWrapper"/>
                                                            <display:column property="createdWho" sortable="true" title="CreatedWho"/>
                                                            <display:column property="lastUpdate" sortable="true" title="Updated"
                                                                            decorator="com.neurix.common.displaytag.LongDateWrapper"/>
                                                            <display:column property="lastUpdateWho" sortable="true" title="UpdatedWho"/>
                                                            <display:column property="action" sortable="true" title="Action"/>
                                                            <display:column property="flag" sortable="true" title="Flag"/>
                                                            <display:setProperty name="paging.banner.item_name">Area</display:setProperty>
                                                            <display:setProperty name="paging.banner.items_name">Areas</display:setProperty>
                                                            <display:setProperty name="export.excel.filename">Areas.xls</display:setProperty>
                                                            <display:setProperty name="export.csv.filename">Areas.csv</display:setProperty>
                                                            <display:setProperty name="export.pdf.filename">Areas.pdf</display:setProperty>
                                                        </display:table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:if>
                                    </center>
                            </s:form>
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



