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
        #coba{
            text-align: center;
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



                    <div class="box-body">
                        <s:form id="functionForm" method="post" action="%{urlProcess}"
                                cssClass="well form-horizontal">

                        <s:hidden name="addOrEdit"/>
                        <s:hidden id="add" name="add"/>

                        <%--<div class="form-group">--%>
                            <%--<div id="actions" class="form-actions">--%>
                            <table  width="100%" align="center">
                                <tr>
                                    <td align="center">
                                        <table>

                                    <tr>
                                        <td>
                                            <label class="control-label" for="functions.stFuncId">Function Id :</label>
                                        </td>

                                        <td>
                                            <table>
                                                <s:if test="isAddOrEdit()">
                                                    <s:if test="isAdd()">
                                                        <s:textfield id="funcId" name="functions.stFuncId" required="true"/>
                                                    </s:if>
                                                    <s:else>
                                                        <s:textfield id="funcId" name="functions.stFuncId" required="true" readonly="true"/>
                                                    </s:else>
                                                </s:if>
                                                <s:elseif test="isDelete()">
                                                    <s:textfield id="funcId" cssClass="disabled" readonly="true" name="functions.stFuncId"/>
                                                </s:elseif>
                                                <s:else>
                                                    <s:textfield id="funcId" name="functions.stFuncId" required="false"/>
                                                </s:else>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="control-label" for="functions.funcName">Function Name :</label>
                                        </td>

                                        <td>
                                            <table>
                                                <s:if test="isAddOrEdit()">
                                                    <s:textfield id="funcName" name="functions.funcName" required="true"/>
                                                </s:if>
                                                <s:elseif test="isDelete()">
                                                    <s:textfield id="funcName" cssClass="disabled" readonly="true" name="functions.funcName" required="false"/>
                                                </s:elseif>
                                                <s:else>
                                                    <s:textfield id="funcName" name="functions.funcName" required="false"/>
                                                </s:else>

                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="control-label" for="functions.url">URL :</label>
                                        </td>

                                        <td>
                                            <table>
                                                <s:if test="isDelete()">
                                                    <s:textfield id="url" cssClass="disabled" readonly="true" name="functions.url" required="false"/>
                                                </s:if>
                                                <s:else>
                                                    <s:textfield id="url" name="functions.url"/>
                                                </s:else>
                                            </table>
                                        </td>
                                    </tr>
                                    <s:if test="isAddOrEdit()">
                                        <tr>
                                            <td>
                                                <label class="control-label" for="functions.statusMenuFunc">Is Menu ?:</label>
                                            </td>
                                            <td>
                                                <table>
                                                    <s:checkbox name="functions.statusMenuFunc" id="statusMenuFunc" onclick="setStatusMenu()"/>
                                                </table>
                                            </td>
                                        </tr>
                                    </s:if>
                                    <s:else>
                                        <tr>
                                            <td>
                                                <label class="control-label" for="functions.stMenu">Menu :</label>
                                            </td>

                                            <td>
                                                <table>
                                                    <s:if test="isDelete()">
                                                        <s:textfield id="menu" cssClass="disabled" readonly="true" name="functions.stMenu"
                                                                     required="false"/>
                                                    </s:if>
                                                    <s:else>
                                                        <s:textfield id="menu" name="functions.stMenu" required="false"/>
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
                                                </table>
                                            </td>
                                        </tr>
                                    </s:else>
                                    <tr id="trParent">
                                        <td>
                                            <label class="control-label" for="functions.stParent">Parent :</label>
                                        </td>

                                        <td>
                                            <table>
                                                <s:if test="isDelete()">
                                                    <s:textfield id="parent" cssClass="disabled" readonly="true" name="functions.stParent"/>
                                                </s:if>
                                                <s:else>
                                                    <%--<s:action id="combo" name="initComboParent_function"/>--%>
                                                    <%--<s:select list="#combo.listOfComboParent" id="parent" name="functions.stParent"--%>
                                                    <%--listKey="stFuncId" listValue="funcName" headerKey=""--%>
                                                    <%--headerValue="[Select None]" onchange="createLevel()"/>--%>

                                                    <s:textfield id="parent" name="functions.stParent" required="true"/>
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
                                            </table>
                                        </td>
                                    </tr>
                                    <tr id="trLevel">
                                        <td>
                                            <label class="control-label" for="functions.stFuncLevel">Function Level :</label>
                                        </td>

                                        <td>
                                            <table>
                                                <s:if test="isAddOrEdit() || isDelete()">
                                                    <s:textfield id="funcLevel" cssClass="disabled" readonly="true" name="functions.stFuncLevel"/>
                                                </s:if>
                                                <s:else>
                                                    <s:textfield id="funcLevel" name="functions.stFuncLevel"/>
                                                </s:else>
                                            </table>
                                        </td>
                                    </tr>
                                    <script>setStatusMenu()</script>
                                    <s:if test="!(isAddOrEdit() || isDelete())">
                                        <tr>
                                            <td>
                                                <label class="control-label" for="functions.flag">Flag :</label>
                                            </td>

                                            <td>
                                                <table>
                                                    <s:hidden id="flag" name="functions.flag"/>

                                                    <s:select list="#{'Y':'Active', 'N':'NonActive'}" id="stFlag" name="functions.stFlag"
                                                              headerKey="" headerValue="[Select one]" onchange="setRealFlag(this);" cssClass="form-control"/>
                                                </table>
                                            </td>
                                        </tr>
                                    </s:if>

                                            <%--</table>--%>
                                        <%--</td>--%>
                                    <%--</tr>--%>

                                <br><br>
                                <table >
                                    <tr>
                                        <div id="crud">
                                            <td >
                                                <table  width="100%" align="center">

                                                    <s:if test="isAddOrEdit()">
                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="functionForm" id="save" name="save"
                                                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                            <i class="icon-ok-sign icon-white"></i>
                                                            Save
                                                        </sj:submit>

                                                        <%--<sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"--%>
                                                                   <%--resizable="false" position="center"--%>
                                                                   <%--height="250" width="600" autoOpen="false" title="Saving ...">--%>
                                                            <%--Please don't close this window, server is processing your request ...--%>
                                                            <%--</br>--%>
                                                            <%--</br>--%>
                                                            <%--</br>--%>
                                                            <%--<img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>"--%>
                                                                 <%--name="image_indicator_write">--%>
                                                        <%--</sj:dialog>--%>
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
                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="functionForm"
                                                                   id="delete" name="delete"
                                                                   onBeforeTopics="beforeProcessDelete" onCompleteTopics="closeDialog,successDialog"
                                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog">
                                                            <i class="icon-trash icon-white"></i>
                                                            Delete
                                                        </sj:submit>
                                                        <sj:dialog id="waiting_dialog" openTopics="showDialog"
                                                                   closeTopics="closeDialog" modal="true"
                                                                   resizable="false"
                                                                   height="250" width="600" autoOpen="false"
                                                                   title="Deleting ...">
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

                                                        <%--<sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"--%>
                                                                   <%--resizable="false"--%>
                                                                   <%--height="250" width="600" autoOpen="false" title="Deleting ...">--%>
                                                            <%--Please don't close this window, server is processing your request ...--%>
                                                            <%--</br>--%>
                                                            <%--</br>--%>
                                                            <%--</br>--%>
                                                            <%--<img border="0" src="<s:url value="/pages/images/indicator-trash.gif"/>"--%>
                                                                 <%--name="image_indicator_trash">--%>
                                                        <%--</sj:dialog>--%>

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

                                                        <%--<sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"--%>
                                                                   <%--resizable="false"--%>
                                                                   <%--position="center" height="250" width="600" autoOpen="false" title="Searching...">--%>
                                                            <%--Please don't close this window, server is processing your request ...--%>
                                                            <%--</br>--%>
                                                            <%--</br>--%>
                                                            <%--</br>--%>
                                                            <%--<img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>"--%>
                                                                 <%--name="image_indicator_read">--%>
                                                        <%--</sj:dialog>--%>
                                                        <sj:dialog id="waiting_dialog" openTopics="showDialog"
                                                                   closeTopics="closeDialog" modal="true"
                                                                   resizable="false"
                                                                   height="250" width="600" autoOpen="false"
                                                                   title="Searching ...">
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
                                                        <sj:submit type="button" cssClass="btn btn-primary" formIds="functionForm" id="search"
                                                                   name="search"
                                                                   onClickTopics="showDialog" onCompleteTopics="closeDialog">
                                                            <i class="icon-search icon-white"></i>
                                                            Search
                                                        </sj:submit>

                                                    </s:else>

                                                </table>
                                            </td>

                                            <td>
                                                <table >

                                                    <s:url id="urlAdd" namespace="/admin/function" action="add_function" escapeAmp="false"/>

                                                        <%--<s:url id="urlEdit" namespace="/admin/function" action="edit_function" escapeAmp="false" includeParams="get"/>                                                --%>
                                                        <%--<s:url id="urlDelete" namespace="/admin/function" action="delete_function" escapeAmp="false" includeParams="get"/>--%>

                                                    <s:url id="urlDownloadPdf" namespace="/admin/function" action="downloadPdf_function" escapeAmp="false"></s:url>
                                                    <s:url id="urlDownloadXls" namespace="/admin/function" action="downloadXls_function" escapeAmp="false"></s:url>

                                                    <s:if test="!isAddOrEdit() && !isDelete()">
                                                        <div class="btn-group">
                                                            <button class="btn btn-success dropdown-toggle" data-toggle="dropdown">
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

                                                <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_function"/>'">
                                                    <i class="icon-repeat"></i> Reset
                                                </button>

                                            </table>
                                        </td>
                                    </tr>
                                    <br>
                                </table>
                                </table>
                            </td>
                            </tr>
                            </table>

                                    <br>
                                    <br><br>
                                    <tr>
                                        <center>
                                        <table width="100%" align="center">
                                            <tr>
                                                <td align="center">
                                                    <s:if test="!isAddOrEdit() && !isDelete()">

                                                        <sj:dialog id="view_dialog_detail" openTopics="showDialogDetail" modal="true" resizable="false" cssStyle="text-align:left;"
                                                                   position="center" height="450" width="700" autoOpen="false" title="View Detail"
                                                                   buttons="{ 'Close':function() { $('#view_dialog_detail').dialog('close'); } }"
                                                        >
                                                            <center><img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" alt="Loading..."/></center>
                                                        </sj:dialog>

                                                        <s:set name="listOfFunctions" value="#session.listOfResult" scope="request"/>
                                                        <display:table name="listOfFunctions" class="table table-condensed table-striped table-hover"
                                                                       requestURI="paging_displaytag.action" id="row" export="true" pagesize="14" style="font-size:10">

                                                            <%--<display:column property="funcId" sortable="true" href="view_function.action"--%>
                                                            <%--media="html"--%>
                                                            <%--paramId="id" paramProperty="funcId" title="Id"/>--%>

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
                                                </td>
                                            </tr>
                                        </table>
                                        </center>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>

                    </s:form>
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


