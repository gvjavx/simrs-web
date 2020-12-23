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

    <script type='text/javascript' src='<s:url value="/dwr/interface/RoleAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RoleFuncAction.js"/>'></script>
    <script type='text/javascript'>

        function selectAll(box, nameElement) {
            var roleId = document.getElementById('roleId').value;
            if (roleId != '') {

                var checked = box.checked;
                var listOfMenu=document.getElementsByName(nameElement);
                for (var i = 0; i < listOfMenu.length; i++) {
                    document.getElementsByName(nameElement)[i].checked=checked;
                    setEnableLinkFunction(checked,document.getElementsByName(nameElement)[i].value);
                    setCheckedFunction(checked,document.getElementsByName(nameElement)[i].value);
                }

            } else {

                box.checked = false;

                var msg = 'Field <strong>RoleId</strong> is required. You must entry <strong>RoleId</strong>.' + '<br/>';

                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

            }
        }

        function checkedHasChild(menuId, checked, listOfMenu, parent, nameElement, nameElementOther) {

            for (var i = 0; i < listOfMenu.length; i++) {
                if (parent == document.getElementsByName(nameElementOther)[i].innerHTML) {
                    document.getElementsByName(nameElement)[i].checked=checked;
                    setEnableLinkFunction(checked, document.getElementsByName(nameElement)[i].value);
                    setCheckedFunction(checked,document.getElementsByName(nameElement)[i].value);
                    checkedHasChild(menuId, checked, listOfMenu, document.getElementsByName(nameElement)[i].value, nameElement, nameElementOther);
                }
            }
        }

        function selectItem(menuId, box, nameElement, nameElementOther) {

            var roleId = document.getElementById('roleId').value;
            if (roleId != '') {
                var checked = box.checked;
                setEnableLinkFunction(checked, menuId);
                setCheckedFunction(checked,menuId);
                var listOfMenu=document.getElementsByName(nameElementOther);
                for (var i = 0; i < listOfMenu.length; i++) {
                    if (menuId == document.getElementsByName(nameElementOther)[i].innerHTML) {
                        document.getElementsByName(nameElement)[i].checked=checked;
                        setEnableLinkFunction(checked, document.getElementsByName(nameElement)[i].value);
                        setCheckedFunction(checked, document.getElementsByName(nameElement)[i].value);
                        checkedHasChild(menuId, checked, listOfMenu, document.getElementsByName(nameElement)[i].value, nameElement, nameElementOther);
                    }
                }
            } else {

                box.checked = false;

                var msg = 'Field <strong>RoleId</strong> is required. You must entry <strong>RoleId</strong>.' + '<br/>';

                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

            }

        }

        function setEnableLinkFunction(checked, menuId) {
            if (checked) {

                if (document.getElementById(menuId)!= null) {
                    document.getElementById(menuId).style.visibility = 'visible';
                    document.getElementById(menuId + '_').style.visibility = 'hidden';
                }

            } else {
                if (document.getElementById(menuId) != null) {
                    document.getElementById(menuId).style.visibility = 'hidden';
                    document.getElementById(menuId + '_').style.visibility = 'visible';
                }
            }
        }

        function setCheckedFunction(checked, menuId) {
            var addFlag = true;
            var editFlag = false;
            var roleId = document.getElementById('roleId').value;

            dwr.engine.setAsync(false);
            RoleFuncAction.saveDetailFunction(checked,addFlag,editFlag,roleId,menuId, function (message) {
                if (message!='00') {

                    document.getElementById('errorMessage').innerHTML = msg;

                    $.publish('showErrorDialog');

                }
            });
        }

        $.subscribe('beforeProcessSave', function (event, data) {
            var listOfMenu=document.getElementsByName('menuIdChecked');
            if (document.getElementById("roleId").value != '' && listOfMenu.length != 0 ) {

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
                if (document.getElementById("roleId").value == '') {
                    msg = 'Field <strong>RoleId</strong> is required.' + '<br/>';
                }

                if (listOfMenu.length == 0) {
                    msg = msg + '<strong>List of Menu</strong> is required. Please checked minimum one.' + '<br/>';
                }

                document.getElementById('errorValidationMessage').innerHTML = msg;

                $.publish('showErrorValidationDialog');

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

            document.getElementById("roleId").value = '';
            document.getElementById("roleName").value = '';
            document.getElementById("selectall").checked = false;
            var listOfMenu=document.getElementsByName('menuIdChecked');
            for (var i = 0; i < listOfMenu.length; i++) {
                document.getElementsByName('menuIdChecked')[i].checked=false;
            }

        }


    </script>

</head>

<body class="hold-transition skin-blue sidebar-mini" onload="showAlert()">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Add Role-Function
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


                    <div class="box-body">
                            <s:form id="addRolefuncForm" method="post" namespace="/admin/rolefunction" action="save_rolefunc" cssClass="well form-horizontal">
                                <s:hidden name="add"/>
                                <s:hidden name="addOrEdit"/>
                                <table width="100%" align="center">
                                    <tr>
                                        <td align="center">
                                            <table>
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
                                                                    var arrLabel = selectedObj.label.split('-');
                                                                    document.getElementById("roleName").value = arrLabel[1];
                                                                    return selectedObj.id;
                                                                }
                                                            });
                                                        </script>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <label>Role Name</label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <s:textfield id="roleName" name="roleFunc.roleName" required="false" readonly="true" cssClass="form-control"
                                                                     cssStyle="margin-top: -30px; margin-left: 20px" />
                                                    </td>
                                                </tr>
                                            </table>
                                            <br><br>
                                            <table>
                                                <tr>
                                                    <td>

                                                        <%--<sj:dialog id="view_dialog_function" openTopics="showDialogFunction" modal="true" resizable="false" cssStyle="text-align:left;"--%>
                                                                   <%--height="600" width="750" autoOpen="false" title="View Detail Function"--%>
                                                        <%-->--%>
                                                            <%--<center><img border="0" src="<s:url value="/pages/images/indicator-read.gif"/>" alt="Loading..."/></center>--%>
                                                        <%--</sj:dialog>--%>

                                                            <sj:dialog id="view_dialog_function" openTopics="showDialogFunction" modal="true"
                                                                       resizable="false"
                                                                       height="250" width="600" autoOpen="false"
                                                                       title="View Detail Function">
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

                                                        <s:set name="listOfMenu" value="roleFunc.listOfMenu" scope="request"/>
                                                        <display:table name="listOfMenu" class="table table-condensed table-striped table-hover"
                                                                       requestURI="" id="row" style="font-size:11">

                                                            <display:column property="displayObjectCheck.checkBox"
                                                                            title="Check <center><input type='checkbox' id='selectall' name='selectall' onClick='selectAll(this, \"menuIdChecked\")' /></center>"
                                                                            media="html" style="text-align:center;"/>

                                                            <display:column property="displayObjectDiv.div"
                                                                            title=""
                                                                            media="html" style="text-align:center;"/>

                                                            <display:column media="html" title="Detail">
                                                                <s:if test="#attr.row.initForm">
                                                                    <s:url var="urlView" namespace="/admin/rolefunction" action="viewDetail_rolefunc" escapeAmp="false">
                                                                        <s:param name="id"><s:property value="#attr.row.funcId"/></s:param>
                                                                        <s:param name="add"><s:property value="add"/></s:param>
                                                                    </s:url>
                                                                    <s:div id="%{#attr.row.funcId}" cssStyle="visibility:hidden">
                                                                        <sj:a onClickTopics="showDialogFunction" href="%{urlView}">
                                                                            <img border="0" src="<s:url value="/pages/images/icon_edit.ico"/>" name="icon_edit">
                                                                        </sj:a>
                                                                    </s:div>
                                                                    <s:div id="%{#attr.row.funcId}_"/>
                                                                </s:if>
                                                            </display:column>

                                                            <display:column property="funcId" title="Id" />
                                                            <display:column property="funcName" title="Name" />
                                                            <display:column property="url" title="URL" />
                                                            <display:column property="parent" title="Parent"/>
                                                            <display:column property="funcLevel" title="Level"/>
                                                            <display:column property="statusPath" title="Status Path"/>
                                                            <display:caption style="font-weight:bold;font-size:17px">List Of Menu</display:caption>
                                                        </display:table>

                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                                <br>
                                <br><br>
                                <center>
                                    <table>
                                        <tr>
                                            <div id="crud">
                                                <td>
                                                    <table>

                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addRolefuncForm" id="save" name="save"
                                                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                            <i class="fa fa-check"></i>
                                                            Save
                                                        </sj:submit>

                                                        <%--<sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"--%>
                                                                   <%--resizable="false"--%>
                                                                   <%--height="250" width="600" autoOpen="false" title="Saving ...">--%>
                                                            <%--Please don't close this window, server is processing your request ...--%>
                                                            <%--</br>--%>
                                                            <%--</br>--%>
                                                            <%--</br>--%>
                                                            <%--<img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">--%>
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

                                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                                   buttons="{
                                                                          'OK':function() {
                                                                                    $( '#rolefuncForm').submit();
                                                                                  <%--$('#info_dialog').dialog('close');--%>
                                                                                  <%--resetField();--%>
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
                                                                <label class="control-label" align="left">
                                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage"></p>
                                                                </label>
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

                                                    </table>
                                                </td>
                                            </div>

                                            <td>
                                                <table>
                                                    <a type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_rolefunc"/>'">
                                                        <i class="fa fa-refresh"></i> Reset
                                                    </a>
                                                </table>
                                            </td>

                                        </tr>
                                    </table>
                                </center>
                            </s:form>
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



