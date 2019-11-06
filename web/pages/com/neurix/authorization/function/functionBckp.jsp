<%--
&lt;%&ndash;
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 15/02/2018
  Time: 16.59
  To change this template use File | Settings | File Templates.
&ndash;%&gt;
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
    <script type='text/javascript'>

        function setStatusMenu() {
            var chckBoxStatus = document.getElementById("statusMenuFunc");

            if (chckBoxStatus.checked) {
                document.getElementById("trLevel").style.visibility = "visible";
                //document.getElementById("trParent").style.visibility = "visible";

                <s:if test = "isAddOrEdit()" >
                document.getElementById("funcLevel").value = '<s:property value="functions.stFuncLevel"/>';
                //document.getElementById("parent").value = '<s:property value="functions.stParent"/>';
                </s:if>

            } else {
                document.getElementById("trLevel").style.visibility = "hidden";
                //document.getElementById("trParent").style.visibility = "hidden";

                document.getElementById("funcLevel").value = "";
                //document.getElementById("parent").value = "";
            }
        }

        function createLevel(idParent) {

            document.getElementById("funcLevel").value = '';
            if (!isNaN(idParent)) {
                <s:if test = "isAddOrEdit()" >
                if (document.getElementById("statusMenuFunc").checked) {
                    FunctionAction.getLevelParent(idParent, function (nilai) {
                        document.getElementById("funcLevel").value = nilai;
                    });
                }
                </s:if>
            }
        }

        function setRealFlag(selectedObj) {

            document.getElementById("flag").value = selectedObj.options[selectedObj.selectedIndex].value;
        }

        $.subscribe('beforeProcessSave', function (event, data) {

            if (document.getElementById("funcId").value != '' &&
                    document.getElementById("funcName").value != '' &&
                    document.getElementById("parent").value != '') {

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
                if (document.getElementById("funcId").value == '') {
                    msg = 'Field <strong>FunctionId</strong> is required.' + '<br/>';
                }

                if (document.getElementById("funcName").value == '') {
                    msg = msg + 'Field <strong>Function Name</strong> is required.' + '<br/>';
                }

                if (document.getElementById("parent").value == '') {
                    msg = msg + 'Field <strong>Parent</strong> is required.' + '<br/>';
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
            document.getElementById("funcId").value = '';
            document.getElementById("funcName").value = '';
            document.getElementById("parent").value = '';
            document.getElementById("url").value = '';
            document.getElementById("statusMenuFunc").checked=false;
            setStatusMenu();
        }

        //        function okSuccessButton() {
        //            //alert('OK Button pressed!');
        //            $('#info_dialog').dialog('close');
        //        };

    </script>

</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Function Information
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

                    <s:if test="isAddOrEdit() || isDelete()">
                        <s:url id="urlProcess" namespace="/admin/function" action="save_function" includeContext="false"/>
                    </s:if>
                    <s:else>
                        <s:url id="urlProcess" namespace="/admin/function" action="search_function" includeContext="false"/>
                    </s:else>


                    <s:hidden name="addOrEdit"/>
                    <s:hidden id="add" name="add"/>
                    <s:hidden name="delete"/>
                        <div class="box-body">
                            <div class="form-group">
                                <s:form id="functionForm" method="post" action="%{urlProcess}"
                                        cssClass="well form-horizontal">

                                    <table align="center">
                                        <tr>
                                            <td>
                                                <label>Function Id </label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <s:if test="isAddOrEdit()">
                                                    <s:if test="isAdd()">
                                                        <s:textfield id="funcId" name="functions.stFuncId" required="true" cssClass="form-control"
                                                                     cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                    </s:if>
                                                    <s:else>
                                                        <s:textfield id="funcId" name="functions.stFuncId" required="true" readonly="true" cssClass="form-control"
                                                                     cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                    </s:else>
                                                </s:if>
                                                <s:elseif test="isDelete()">
                                                    <s:textfield id="funcId" readonly="true" name="functions.stFuncId" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                </s:elseif>
                                                <s:else>
                                                    <s:textfield id="funcId" name="functions.stFuncId" required="false" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                </s:else>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label>Function Name </label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <s:if test="isAddOrEdit()">
                                                    <s:textfield id="funcName" name="functions.funcName" required="true" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                </s:if>
                                                <s:elseif test="isDelete()">
                                                    <s:textfield id="funcName" readonly="true" name="functions.funcName" required="false" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                </s:elseif>
                                                <s:else>
                                                    <s:textfield id="funcName" name="functions.funcName" required="false" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                </s:else>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label>URL </label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <s:if test="isDelete()">
                                                    <s:textfield id="url" readonly="true" name="functions.url" required="false" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                </s:if>
                                                <s:else>
                                                    <s:textfield id="url" name="functions.url" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                </s:else>
                                            </td>
                                        </tr>

                                        <s:if test="isAddOrEdit()">
                                            <tr>
                                                <td>
                                                    <label>Is Menu ? </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <s:checkbox name="functions.statusMenuFunc" id="statusMenuFunc" onclick="setStatusMenu()" cssClass="form-control"
                                                                cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                </td>
                                            </tr>
                                        </s:if>
                                        <s:else>
                                            <tr>
                                                <td>
                                                    <label>Menu  </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <s:if test="isDelete()">
                                                        <s:textfield id="menu" readonly="true" name="functions.stMenu"
                                                                     required="false" cssClass="form-control"
                                                                     cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                    </s:if>
                                                    <s:else>
                                                        <s:textfield id="menu" name="functions.stMenu" required="false" cssClass="form-control"
                                                                     cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                        <script>
                                                            var menus, mapped;
                                                            $('#menu').typeahead({
                                                                minLength: 2,
                                                                source: function (query, process) {
                                                                    menus = [];
                                                                    mapped = {};

                                                                    var data = [];
                                                                    dwr.engine.setAsync(false);
                                                                    FunctionAction.initComboParent(query, function (listdata) {
                                                                        data = listdata;
                                                                    });

                                                                    $.each(data, function (i, item) {
                                                                        var labelItem = item.stFuncId + '-' + item.funcName;
                                                                        mapped[labelItem] = { id: item.stFuncId, label: labelItem };
                                                                        menus.push(labelItem);
                                                                    });

                                                                    process(menus);
                                                                },
                                                                updater: function (item) {
                                                                    var selectedObj = mapped[item];
                                                                    return selectedObj.id;
                                                                }
                                                            });
                                                        </script>
                                                    </s:else>
                                                </td>
                                            </tr>
                                        </s:else>
                                        <tr id="trParent">
                                            <td>
                                                <label>Parent </label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <s:if test="isDelete()">
                                                    <s:textfield id="parent" readonly="true" name="functions.stParent" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                </s:if>
                                                <s:else>
                                                    <s:textfield id="parent" name="functions.stParent" required="true" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                    <script>
                                                        var functions, mapped;
                                                        $('#parent').typeahead({
                                                            minLength: 2,
                                                            source: function (query, process) {
                                                                functions = [];
                                                                mapped = {};

                                                                var data = [];
                                                                dwr.engine.setAsync(false);
                                                                FunctionAction.initComboParent(query, function (listdata) {
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
                                                                createLevel(selectedObj.id);
                                                                return selectedObj.id;
                                                            }
                                                        });
                                                    </script>
                                                </s:else>
                                            </td>
                                        </tr>
                                        <tr id="trLevel">
                                            <td>
                                                <label>Function Level</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <s:if test="isAddOrEdit() || isDelete()">
                                                    <s:textfield id="funcLevel" readonly="true" name="functions.stFuncLevel" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                </s:if>
                                                <s:else>
                                                    <s:textfield id="funcLevel" name="functions.stFuncLevel" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                </s:else>
                                            </td>
                                        </tr>
                                        <script>setStatusMenu()</script>
                                        <s:if test="!(isAddOrEdit() || isDelete())">
                                            <tr>
                                                <td>
                                                    <label>Flag </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <s:hidden id="flag" name="functions.flag"/>

                                                    <s:select list="#{'Y':'Active', 'N':'NonActive'}" id="stFlag" name="functions.stFlag"
                                                              headerKey="" headerValue="[Select one]" onchange="setRealFlag(this);" cssClass="form-control"
                                                              cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                </td>
                                            </tr>
                                        </s:if>

                                    </table>
                                    <br>

                                    <table align="center">
                                        <tr>
                                            <div id="crud">
                                                <td>
                                                    <table>

                                                        <s:if test="isAddOrEdit()">


                                                            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="functionForm" id="save" name="save"
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
                                                                <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
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

                                                        </s:if>
                                                        <s:elseif test="isDelete()">
                                                            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="functionForm"
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
                                                                    <label class="control-label" align="left"> <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                                                                                    name="icon_error"> System Found : <p id="errorMessage"></p></label>
                                                                </div>
                                                            </sj:dialog>
                                                        </s:elseif>
                                                        <s:else>

                                                            <sj:dialog id="waiting_dialog" openTopics="showDialog1" closeTopics="closeDialog" modal="true"
                                                                       resizable="false"
                                                                       height="250" width="600" autoOpen="false" title="Searching...">
                                                                Please don't close this window, server is processing your request ...
                                                                </br>
                                                                </br>
                                                                </br>
                                                                <center>
                                                                    <img border="0" src="<s:url value="/pages/images/loading11.gif"/>"
                                                                         name="image_loading11">
                                                                </center>

                                                            </sj:dialog>
                                                            <sj:submit type="button" cssClass="btn btn-primary" formIds="functionForm" id="search"
                                                                       name="search"
                                                                       onClickTopics="showDialog" onCompleteTopics="closeDialog">
                                                                <i class="fa fa-search"></i>
                                                                Search
                                                            </sj:submit>

                                                        </s:else>

                                                    </table>
                                                </td>

                                                <td>
                                                    <table>

                                                        <s:url id="urlAdd" namespace="/admin/function" action="add_function" escapeAmp="false"/>
                                                        <s:url id="urlDownloadPdf" namespace="/admin/function" action="downloadPdf_function" escapeAmp="false"></s:url>
                                                        <s:url id="urlDownloadXls" namespace="/admin/function" action="downloadXls_function" escapeAmp="false"></s:url>

                                                        <s:if test="!isAddOrEdit() && !isDelete()">

                                                            <div class="btn-group">
                                                                <button class="btn dropdown-toggle" data-toggle="dropdown">
                                                                    <i class="fa fa-briefcase"></i>
                                                                    Function
                                                                    <span class="caret"></span>
                                                                </button>
                                                                <ul class="dropdown-menu">
                                                                    <li><s:a href="%{urlAdd}"><i class="icon-plus"></i> Add</s:a></li>
                                                                        &lt;%&ndash;<li><s:a id="actionEdit" href="%{urlEdit}"><i class="icon-pencil"></i> Edit</s:a></li>&ndash;%&gt;
                                                                        &lt;%&ndash;<li><s:a id="actionDelete" href="%{urlDelete}"><i class="icon-trash"></i> Delete</s:a></li>&ndash;%&gt;
                                                                    <li><s:a href="%{urlDownloadPdf}"><i class="icon-download-alt"></i> Download PDF</s:a></li>
                                                                    <li><s:a href="%{urlDownloadXls}"><i class="icon-download-alt"></i> Download XLS</s:a></li>

                                                                </ul>
                                                            </div>
                                                        </s:if>
                                                    </table>
                                                </td>
                                            </div>
                                            <td>
                                                <table>
                                                    <a type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_function"/>'">
                                                        <i class="fa fa-refresh"></i> Reset
                                                    </a>
                                                </table>
                                            </td>

                                        </tr>
                                    </table>
                                    <br><br>
                                    <center>
                                        <s:if test="!isAddOrEdit() && !isDelete()">

                                            <sj:dialog id="view_dialog_detail" openTopics="showDialogDetail" modal="true" resizable="false" cssStyle="text-align:left;"
                                                       height="450" width="700" autoOpen="false" title="View Detail"
                                                       buttons="{ 'Close':function() { $('#view_dialog_detail').dialog('close'); } }"
                                            >
                                                <center><img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" alt="Loading..."/></center>
                                            </sj:dialog>

                                            <s:set name="listOfFunctions" value="#session.listOfResult" scope="request"/>
                                            <display:table name="listOfFunctions" class="table table-condensed table-striped table-hover"
                                                           requestURI="paging_displaytag.action" id="row" export="true" pagesize="20" style="font-size:10">

                                                &lt;%&ndash;<display:column property="funcId" sortable="true" href="view_function.action"&ndash;%&gt;
                                                &lt;%&ndash;media="html"&ndash;%&gt;
                                                &lt;%&ndash;paramId="id" paramProperty="funcId" title="Id"/>&ndash;%&gt;

                                                <display:column media="html" title="Edit">
                                                    <s:if test="#attr.row.flagYes">
                                                        <s:url var="urlView" namespace="/admin/function" action="edit_function" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.funcId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <s:a href="%{urlView}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                        </s:a>
                                                    </s:if>
                                                </display:column>

                                                <display:column media="html" title="Delete">
                                                    <s:if test="#attr.row.flagYes">
                                                        <s:url var="urlView" namespace="/admin/function" action="delete_function" escapeAmp="false">
                                                            <s:param name="id"><s:property value="#attr.row.funcId"/></s:param>
                                                            <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                        </s:url>
                                                        <s:a href="%{urlView}">
                                                            <img border="0" src="<s:url value="/pages/images/icon_trash.ico"/>" name="icon_trash">
                                                        </s:a>
                                                    </s:if>
                                                </display:column>

                                                <display:column sortable="true" media="html" title="Id">
                                                    <s:url var="urlView" namespace="/admin/function" action="view_function"
                                                           escapeAmp="false">
                                                        <s:param name="id"><s:property value="#attr.row.funcId"/></s:param>
                                                        <s:param name="flag"><s:property value="#attr.row.flag"/></s:param>
                                                    </s:url>
                                                    <sj:a onClickTopics="showDialogDetail" href="%{urlView}">
                                                        <s:property value="#attr.row.funcId"/>
                                                    </sj:a>
                                                </display:column>

                                                <display:column property="funcId" media="csv excel xml pdf" title="Id"/>
                                                <display:column property="funcName" sortable="true" title="Func.Name" style="font-size:11"/>
                                                <display:column property="url" sortable="true" title="URL" />
                                                <display:column property="menu" sortable="true" title="Menu"/>
                                                <display:column property="parent" sortable="true" title="Parent"/>
                                                <display:column property="funcLevel" sortable="true" title="Level"/>
                                                <display:column property="createdDate" sortable="true" title="Created"
                                                                decorator="com.neurix.common.displaytag.LongDateWrapper"/>
                                                <display:column property="createdWho" sortable="true" title="CreatedWho" />
                                                <display:column property="lastUpdate" sortable="true" title="Updated"
                                                                decorator="com.neurix.common.displaytag.LongDateWrapper"/>
                                                <display:column property="lastUpdateWho" sortable="true" title="UpdatedWho" />
                                                <display:column property="action" sortable="true" title="Action"/>
                                                <display:column property="flag" sortable="true" title="Flag"/>
                                                <display:setProperty name="paging.banner.item_name">Function</display:setProperty>
                                                <display:setProperty name="paging.banner.items_name">Functions</display:setProperty>
                                                <display:setProperty name="export.excel.filename">Functions.xls</display:setProperty>
                                                <display:setProperty name="export.csv.filename">Functions.csv</display:setProperty>
                                                <display:setProperty name="export.pdf.filename">Functions.pdf</display:setProperty>
                                            </display:table>
                                        </s:if>
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



--%>


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
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
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

    <style>
        /*--body { background-color:#fafafa; font-family:'Open Sans';}*/
        .container { margin:150px auto;}
        .treegrid-collapsed {
            background-color: #bfbfbf;
        }
        .treegrid-expanded {
            background-color: #e6e6e6;
        }

        .treegrid-indent {width:16px; height: 16px; display: inline-block; position: relative;}

        .treegrid-expander { width:16px; height: 16px; display: inline-block; position: relative; cursor: pointer;}

        /*.treegrid-expander-expanded{background-image: url(collapse.png); }
        .treegrid-expander-collapsed{background-image: url(expand.png);}*/

    </style>


    <script type='text/javascript' src='<s:url value="/dwr/interface/FunctionAction.js"/>'></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.bootstrap3.js"/>"></script>
    <script src="<s:url value="/pages/plugins/tree/jquery.treegrid.js"/>"></script>

    <script>

        $(document).ready(function () {
            //f1();
            $('.tree').treegrid({
                expanderExpandedClass: 'glyphicon glyphicon-minus',
                expanderCollapsedClass: 'glyphicon glyphicon-plus'
            });
        });

//        tambahan irfan
        function setStatusMenu() {
            var chckBoxStatus = document.getElementById("statusMenuFunc");

            if (chckBoxStatus.checked == true) {
                document.getElementById("trLevel").style.visibility = "visible";
                //document.getElementById("trParent").style.visibility = "visible";

                <s:if test = "isAddOrEdit()" >
                document.getElementById("funcLevel").value = '<s:property value="functions.stFuncLevel"/>';
                //document.getElementById("parent").value = '<s:property value="functions.stParent"/>';
                </s:if>

            } else {
                document.getElementById("trLevel").style.visibility = "hidden";
                //document.getElementById("trParent").style.visibility = "hidden";

                document.getElementById("funcLevel").value = "";
                //document.getElementById("parent").value = "";
            }
        }



        function searchFunc(){
            $('.tree').find('tbody').remove();
            $('.tree').find('thead').remove();
            f1();
            $('.tree').treegrid({
                expanderExpandedClass: 'glyphicon glyphicon-chevron-down',
                expanderCollapsedClass: 'glyphicon glyphicon-chevron-right'
            });
        }

        function f1() {

            var tmp_table = "";
            var data = [];
            var data2 = [];
            var funcId = document.getElementById("funcId").value;
            dwr.engine.setAsync(false);
            FunctionAction.searchFunc(funcId, function(listdata){
                data = listdata;
                data2 = new Array();
                data2_hasil = new Array();
                data2_tmp = new Array();

                $.each(data, function(i,item){
                    data2.push([item.funcId, item.funcName, item.url, item.menu, item.parent, item.funcLevel, 'Y']);
                });

                dataTmp = new Array();
                data2_tmp = data2 ;
                var index = 1 ;
                var indexTmp = 0 ;
                var jumlah = 0 ;
                var param = new Array();
                param[0] = data2[0][6];
                data2_hasil[0] = data2[0];
                var indParam = 0 ;
                while(jumlah < data2.length){
                    for(i = 0 ; i < data2.length ; i++){
                        if(data2[i][6] == 'Y'){
                            if(param[indParam] == data2[i][4]){
                                data2_hasil[index] = data2[i];
                                data2[i][6] = 'N';
                                index++;
                                jumlah++;
                                indParam++;
                                param[indParam] = data2[i][4];
                                i = 0 ;
                            }
                        }
                    }
                    //indParam--;
                    //param[indParam];
                    //jumlah++;
                }
                /*for(i = 0 ; i < data2.length ; i++){
                    if(data2[i][6] == 'Y'){
                        data2_hasil[index] = data2[i] ;
                        data2[i][6] = 'N';
                        index++;
                        console.log(data2[i]);
                        for(x = 0 ; x < data2.length ; x++){
                            if(data2[x][6] == 'Y'){
                                if(data2[i][0] == data2[x][4]){
                                    data2_hasil[index] = data2[x];
                                    index++;
                                    data2[x][6] = 'N';
                                    x = 0 ;
                                }
                            }
                        }
                        i = 0 ;
                    }
                }*/

                tmp_table = "<thead style='font-size: 14px' ><tr class='active'>"+
                        "<th style='text-align: center; background-color:  #76a0ef'>Func Id</th>"+
                        "<th style='text-align: center; background-color:  #76a0ef''>Func Name</th>"+
                        "<th style='text-align: center; background-color:  #76a0ef''>URL</th>"+
                        "<th style='text-align: center; background-color:  #76a0ef''>Parent</th>"+
                        "</tr></thead>";
                for(i = 0 ; i < data2_hasil.length ; i++){
                    //data2.push([item.funcId, item.funcName, item.url, item.menu, item.parent, item.funcLevel, 'Y']);
                    if(data2_hasil[i][4] == 0){
                        tmp_table += '<tr style="font-size: 12px;" class=" treegrid-' + data2_hasil[i][0] + '">' +
                                + '<td >data2_hasil[i][1]</td>' +
                                '<td >' + data2_hasil[i][0] + '</td>' +
                                '<td >' + data2_hasil[i][1] + '</td>' +
                                '<td >' + data2_hasil[i][2] + '</td>' +
                                '<td >' + data2_hasil[i][4] + '</td>' +
                                "</tr>";
                    } else {
                        tmp_table += '<tr style="font-size: 12px" class=" treegrid-' + data2_hasil[i][0] + ' treegrid-parent-' + data2_hasil[i][4] + '">' +
                                + '<td style="border: 2px solid black;">data2_hasil[0][1]</td>' +
                                '<td >' + data2_hasil[i][0] + '</td>' +
                                '<td >' + data2_hasil[i][1] + '</td>' +
                                '<td >' + data2_hasil[i][2] + '</td>' +
                                '<td >' + data2_hasil[i][4] + '</td>' +
                                "</tr>";
                    }
                }
                $('.tree').append(tmp_table);
                console.log(data2_hasil);
            });

        }


    </script>

    <script type='text/javascript'>

        /*function searchFunc(){
            var tmp_table = "";
            var data = [];
            var funcId = document.getElementById("funcId").value;
            dwr.engine.setAsync(false);
            /!*FunctionAction.searchFunc(funcId, function(listdata){
                data = listdata;
                $.each(data, function(i,item){
                    if(item.funcLevel != '0'){
                        tmp_table += "<tr data-id="+item.funcId +" data-parent='0' data-level='1'>" +
                                "<td data-column='name'>"+item.funcId+"</td>" +
                                "<td>"+item.funcName+"</td>" +
                                "<td>"+item.url+"</td>" +
                                "<td>"+item.menu+"</td>" +
                                "<td>"+item.parent+"</td>" +
                                "<td>"+item.funcLevel+"</td>" +
                                "</tr>";
                    } else {
                        tmp_table += "<tr data-id="+item.funcId+" data-parent="+item.parent+" data-level='2'>" +
                                "<td data-column='name'>"+item.funcId+"</td>" +
                                "<td>"+item.funcName+"</td>" +
                                "<td>"+item.url+"</td>" +
                                "<td>"+item.menu+"</td>" +
                                "<td>"+item.parent+"</td>" +
                                "<td>"+item.funcLevel+"</td>" +
                                "</tr>";
                    }
                })

            });*!/

           /!* var string2 = "<th> # </th>" +
                    "<th>Test</th>"+
                    "<tbody>"+
                    "<tr data-id='1' data-parent='0' data-level='1'>"+
                    "<td data-column='name'>Node 0</td>"+
                    "<td>Additional info 0</td>" +
                    "</tr>" +
                    "<tr data-id='2' data-parent='1' data-level='1'>"+
                    "<td data-column='name'>Node 1</td>"+
                    "<td>Additional info 1</td>" +
                    "</tr>" +
                    "<tr data-id='3' data-parent='1' data-level='1'>"+
                    "<td data-column='name'>Node 2</td>"+
                    "<td>Additional info 2</td>" +
                    "</tr>" +
                    "<tr data-id='4' data-parent='3' data-level='1'>"+
                    "<td data-column='name'>Node 3</td>"+
                    "<td>Additional info 3</td>" +
                    "</tr>" +
                    "<tr data-id='5' data-parent='3' data-level='1'>"+
                    "<td data-column='name'>Node 4</td>"+
                    "<td>Additional info 4</td>" +
                    "</tr>" +
                    "</tbody>";*!/
            tmp_table += "<tr data-id='1' data-parent='0' data-level='1'>" +
                    "<td data-column='name'>Node 0</td>" +
                    "<td>Additional info 0</td>" +
                    "</tr>";
            tmp_table += "<tr data-id='2' data-parent='1' data-level='2'>" +
                    "<td data-column='name'>Node 1</td>" +
                    "<td>Additional info 1</td>" +
                    "</tr>";
            tmp_table += "<tr data-id='3' data-parent='1' data-level='2'>" +
                    "<td data-column='name'>Node2</td>" +
                    "<td>Additional info 2</td>" +
                    "</tr>";
            tmp_table += "<tr data-id='4' data-parent='3' data-level='3'>" +
                    "<td data-column='name'>Node 3</td>" +
                    "<td>Additional info 3</td>" +
                    "</tr>";
            tmp_table += "<tr data-id='5' data-parent='3' data-level='3'>" +
                    "<td data-column='name'>Node 4</td>" +
                    "<td>Additional info 4</td>" +
                    "</tr>";

            $('#anu').append(tmp_table);
            //$('#myTable').html('<tr>ndal ndul</tr><tr>sekali</tr>');
            //alert(string2);
        }*/


    </script>

</head>

<body class="hold-transition skin-blue sidebar-mini" >

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Function Information
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

                    <s:if test="isAddOrEdit() || isDelete()">
                        <s:url id="urlProcess" namespace="/admin/function" action="save_function" includeContext="false"/>
                    </s:if>
                    <s:else>
                        <s:url id="urlProcess" namespace="/admin/function" action="" includeContext="false"/>
                    </s:else>


                    <s:hidden name="addOrEdit"/>
                    <s:hidden id="add" name="add"/>
                    <s:hidden name="delete"/>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="functionForm" method="post" action="%{urlProcess}"
                                    cssClass="well form-horizontal">

                                <table align="center">
                                    <tr>
                                        <td>
                                            <label>Function Id </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:if test="isAddOrEdit()">
                                                <s:if test="isAdd()">
                                                    <s:textfield id="funcId" name="functions.stFuncId"  cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                </s:if>
                                                <s:else>
                                                    <s:textfield id="funcId" name="functions.stFuncId"  readonly="true" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                </s:else>
                                            </s:if>
                                            <s:elseif test="isDelete()">
                                                <s:textfield id="funcId" readonly="true" name="functions.stFuncId" cssClass="form-control"
                                                             cssStyle="margin-top: -30px; margin-left: 20px"/>
                                            </s:elseif>
                                            <s:else>
                                                <s:textfield id="funcId" name="functions.stFuncId" cssClass="form-control"
                                                             cssStyle="margin-top: -30px; margin-left: 20px"/>
                                            </s:else>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>Function Name </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:if test="isAddOrEdit()">
                                                <s:textfield id="funcName" name="functions.funcName"  cssClass="form-control"
                                                             cssStyle="margin-top: -30px; margin-left: 20px"/>
                                            </s:if>
                                            <s:elseif test="isDelete()">
                                                <s:textfield id="funcName" readonly="true" name="functions.funcName" required="false" cssClass="form-control"
                                                             cssStyle="margin-top: -30px; margin-left: 20px"/>
                                            </s:elseif>
                                            <s:else>
                                                <s:textfield id="funcName" name="functions.funcName" cssClass="form-control"
                                                             cssStyle="margin-top: -30px; margin-left: 20px"/>
                                            </s:else>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label>URL </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:if test="isDelete()">
                                                <s:textfield id="url" readonly="true" name="functions.url" required="false" cssClass="form-control"
                                                             cssStyle="margin-top: -30px; margin-left: 20px"/>
                                            </s:if>
                                            <s:else>
                                                <s:textfield id="url" name="functions.url" cssClass="form-control"
                                                             cssStyle="margin-top: -30px; margin-left: 20px"/>
                                            </s:else>
                                        </td>
                                    </tr>

                                    <s:if test="isAddOrEdit()">
                                        <tr>
                                            <td>
                                                <label>Is Menu ? </label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <s:checkbox name="functions.statusMenuFunc" id="statusMenuFunc" onclick="setStatusMenu()" cssClass="form-control"
                                                            cssStyle="margin-top: -30px; margin-left: 20px"/>
                                            </td>
                                        </tr>
                                    </s:if>
                                    <s:else>
                                        <tr>
                                            <td>
                                                <label>Menu  </label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <s:if test="isDelete()">
                                                    <s:textfield id="menu" readonly="true" name="functions.stMenu"
                                                                 required="false" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                </s:if>
                                                <s:else>
                                                    <s:textfield id="menu" name="functions.stMenu" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                    <script>
                                                        var menus, mapped;
                                                        $('#menu').typeahead({
                                                            minLength: 2,
                                                            source: function (query, process) {
                                                                menus = [];
                                                                mapped = {};

                                                                var data = [];
                                                                dwr.engine.setAsync(false);
                                                                FunctionAction.initComboParent(query, function (listdata) {
                                                                    data = listdata;
                                                                });

                                                                $.each(data, function (i, item) {
                                                                    var labelItem = item.stFuncId + '-' + item.funcName;
                                                                    mapped[labelItem] = { id: item.stFuncId, label: labelItem };
                                                                    menus.push(labelItem);
                                                                });

                                                                process(menus);
                                                            },
                                                            updater: function (item) {
                                                                var selectedObj = mapped[item];
                                                                return selectedObj.id;
                                                            }
                                                        });
                                                    </script>
                                                </s:else>
                                            </td>
                                        </tr>
                                    </s:else>
                                    <tr id="trParent">
                                        <td>
                                            <label>Parent </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:if test="isDelete()">
                                                <s:textfield id="parent" readonly="true" name="functions.stParent" cssClass="form-control"
                                                             cssStyle="margin-top: -30px; margin-left: 20px"/>
                                            </s:if>
                                            <s:else>
                                                <s:textfield id="parent" name="functions.stParent"  cssClass="form-control"
                                                             cssStyle="margin-top: -30px; margin-left: 20px"/>
                                                <script>
                                                    var functions, mapped;
                                                    $('#parent').typeahead({
                                                        minLength: 2,
                                                        source: function (query, process) {
                                                            functions = [];
                                                            mapped = {};

                                                            var data = [];
                                                            dwr.engine.setAsync(false);
                                                            FunctionAction.initComboParent(query, function (listdata) {
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
                                                            createLevel(selectedObj.id);
                                                            return selectedObj.id;
                                                        }
                                                    });
                                                </script>
                                            </s:else>
                                        </td>
                                    </tr>
                                    <tr id="trLevel">
                                        <td>
                                            <label>Function Level</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <s:if test="isAddOrEdit() || isDelete()">
                                                <s:textfield id="funcLevel" readonly="true" name="functions.stFuncLevel" cssClass="form-control"
                                                             cssStyle="margin-top: -30px; margin-left: 20px"/>
                                            </s:if>
                                            <s:else>
                                                <s:textfield id="funcLevel" name="functions.stFuncLevel" cssClass="form-control"
                                                             cssStyle="margin-top: -30px; margin-left: 20px"/>
                                            </s:else>
                                        </td>
                                    </tr>
                                    <script>setStatusMenu()</script>
                                    <s:if test="!(isAddOrEdit() || isDelete())">
                                        <tr>
                                            <td>
                                                <label>Flag </label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <s:hidden id="flag" name="functions.flag"/>

                                                <s:select list="#{'Y':'Active', 'N':'NonActive'}" id="stFlag" name="functions.stFlag"
                                                          headerKey="" headerValue="[Select one]" onchange="setRealFlag(this);" cssClass="form-control"
                                                          cssStyle="margin-top: -30px; margin-left: 20px"/>
                                            </td>
                                        </tr>
                                    </s:if>

                                </table>
                                <br>

                                <table align="center">
                                    <tr>
                                        <div id="crud">
                                            <td>
                                                <table>

                                                    <s:if test="isAddOrEdit()">


                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="functionForm" id="save" name="save"
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
                                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
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

                                                    </s:if>
                                                    <s:elseif test="isDelete()">
                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="functionForm"
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
                                                                <label class="control-label" align="left"> <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                                                                                name="icon_error"> System Found : <p id="errorMessage"></p></label>
                                                            </div>
                                                        </sj:dialog>
                                                    </s:elseif>
                                                    <s:else>

                                                        <sj:dialog id="waiting_dialog" openTopics="showDialog1" closeTopics="closeDialog" modal="true"
                                                                   resizable="false"
                                                                   height="250" width="600" autoOpen="false" title="Searching...">
                                                            Please don't close this window, server is processing your request ...
                                                            </br>
                                                            </br>
                                                            </br>
                                                            <center>
                                                                <img border="0" src="<s:url value="/pages/images/loading11.gif"/>"
                                                                     name="image_loading11">
                                                            </center>

                                                        </sj:dialog>
                                                        <%--<sj:submit type="button" cssClass="btn btn-primary" formIds="functionForm" id="search"--%>
                                                        <%--name="search"--%>
                                                        <%--onClickTopics="showDialog" onCompleteTopics="closeDialog">--%>
                                                        <%--<i class="fa fa-search"></i>--%>
                                                        <%--Search--%>
                                                        <%--</sj:submit>--%>
                                                        <a href="#" class="btn btn-primary" onclick="searchFunc()">
                                                            <i class="fa fa-search"> Search</i>
                                                        </a>

                                                    </s:else>

                                                </table>
                                            </td>

                                            <td>
                                                <table>

                                                    <s:url id="urlAdd" namespace="/admin/function" action="add_function" escapeAmp="false"/>
                                                    <s:url id="urlDownloadPdf" namespace="/admin/function" action="downloadPdf_function" escapeAmp="false"></s:url>
                                                    <s:url id="urlDownloadXls" namespace="/admin/function" action="downloadXls_function" escapeAmp="false"></s:url>

                                                    <s:if test="!isAddOrEdit() && !isDelete()">

                                                        <div class="btn-group">
                                                            <button class="btn dropdown-toggle" data-toggle="dropdown">
                                                                <i class="fa fa-briefcase"></i>
                                                                Function
                                                                <span class="caret"></span>
                                                            </button>
                                                            <ul class="dropdown-menu">
                                                                <li><s:a href="%{urlAdd}"><i class="icon-plus"></i> Add</s:a></li>
                                                                    <%--<li><s:a id="actionEdit" href="%{urlEdit}"><i class="icon-pencil"></i> Edit</s:a></li>--%>
                                                                    <%--<li><s:a id="actionDelete" href="%{urlDelete}"><i class="icon-trash"></i> Delete</s:a></li>--%>
                                                                <li><s:a href="%{urlDownloadPdf}"><i class="icon-download-alt"></i> Download PDF</s:a></li>
                                                                <li><s:a href="%{urlDownloadXls}"><i class="icon-download-alt"></i> Download XLS</s:a></li>

                                                            </ul>
                                                        </div>
                                                    </s:if>
                                                </table>
                                            </td>
                                        </div>
                                        <td>
                                            <table>
                                                <a type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_function"/>'">
                                                    <i class="fa fa-refresh"></i> Reset
                                                </a>
                                            </table>
                                        </td>

                                    </tr>
                                </table>
                                <br><br>
                                <center>
                                    <s:if test="!isAddOrEdit() && !isDelete()">

                                        <sj:dialog id="view_dialog_detail" openTopics="showDialogDetail" modal="true" resizable="false" cssStyle="text-align:left;"
                                                   height="450" width="700" autoOpen="false" title="View Detail"
                                                   buttons="{ 'Close':function() { $('#view_dialog_detail').dialog('close'); } }"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" alt="Loading..."/></center>
                                        </sj:dialog>

                                      <%--  <table id="example-basic" class="table table-hover table-bordered">
                                            <th>#</th>
                                            <th>Test</th>
                                            <tbody id="anu">
                                            <tr data-id="1" data-parent="0" data-level="1">
                                                <td data-column="name">Node 1</td>
                                                <td>Additional info</td>
                                            </tr>
                                            <tr data-id="2" data-parent="1" data-level="2">
                                                <td data-column="name">Node 1</td>
                                                <td>Additional info</td>
                                            </tr>
                                            <tr data-id="3" data-parent="1" data-level="2">
                                                <td data-column="name">Node 1</td>
                                                <td>Additional info</td>
                                            </tr>
                                            <tr data-id="4" data-parent="3" data-level="3">
                                                <td data-column="name">Node 1</td>
                                                <td>Additional info</td>
                                            </tr>
                                            <tr data-id="5" data-parent="3" data-level="3">
                                                <td data-column="name">Node 1</td>
                                                <td>Additional info</td>
                                            </tr>
                                            </tbody>

                                        </table>--%>

                                        <table class="tree table table-bordered">

                                        </table>

                                    </s:if>

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



