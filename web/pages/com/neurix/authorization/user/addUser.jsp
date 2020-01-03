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
    <script type='text/javascript'>

        $.subscribe('beforeProcessSave', function (event, data) {

            if (document.getElementById("userid").value != '' &&
                    document.getElementById("name").value != '' &&
                    document.getElementById("password").value != '' &&
                    document.getElementById("confirmPassword").value != '' &&
                    //document.getElementById("positionid").value != '' &&
                    document.getElementById("roleid").value != '' &&
                    document.getElementById("areaid").value != '' &&
                    document.getElementById("branchid").value != '' &&
                    //document.getElementById("fileUpload").value != '' &&
                    document.getElementById("password").value == document.getElementById("confirmPassword").value) {

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
                if (document.getElementById("userid").value == '') {
                    msg = 'Field <strong>User Id</strong> is required.' + '<br/>';
                }

                if (document.getElementById("name").value == '') {
                    msg = msg + 'Field <strong>UserName</strong> is required.' + '<br/>';
                }

                if (document.getElementById("password").value == '') {
                    msg = msg + 'Field <strong>Password</strong> is required.' + '<br/>';
                }

                if (document.getElementById("confirmPassword").value == '') {
                    msg = msg + 'Field <strong>ConfirmPassword</strong> is required.' + '<br/>';
                }

                if (document.getElementById("password").value != document.getElementById("confirmPassword").value) {
                    msg = msg + 'Field <strong>Password</strong> and <strong>confirmPassword</strong> not match.' + '<br/>';
                }

                /*if (document.getElementById("positionid").value == '') {
                    msg = msg + 'Field <strong>Jabatan</strong> is required.' + '<br/>';
                }*/

                if (document.getElementById("areaid").value == '') {
                    msg = msg + 'Field <strong>Area Id</strong> is required.' + '<br/>';
                }

                if (document.getElementById("branchid").value == '') {
                    msg = msg + 'Field <strong>Unit</strong> is required.' + '<br/>';
                }

                if (document.getElementById("roleid").value == '') {
                    msg = msg + 'Field <strong>Role Id</strong> is required.' + '<br/>';
                }

                /*if (document.getElementById("fileUpload").value == '') {
                    msg = msg + 'Field <strong>File Upload</strong> is required.' + '<br/>';
                }*/

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

            document.getElementById("userid").value = '';
            document.getElementById("name").value = '';
            document.getElementById("password").value = '';
            document.getElementById("email").value = '';
            document.getElementById("fileUpload").value = '';
            document.getElementById("confirmPassword").value = '';
            $('#positionId').empty();
            document.getElementById("roleid").value = '';
            document.getElementById("areaid").value = '';
            document.getElementById("branchid").value = '';

        }


    </script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BranchAction.js"/>'></script>
</head>

<body class="hold-transition skin-blue sidebar-mini">

<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Add User Information
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
                        <%--<h3 class="box-title">Search Form</h3>--%>
                    </div>
                    <s:url id="urlProcess" namespace="/admin/user" action="save_user" includeContext="false"/>

                        <div class="box-body">
                            <div class="form-group">
                                <s:url id="urlProcess" namespace="/admin/user" action="save_user" includeContext="false"/>
                                <s:form id="addUserForm" enctype="multipart/form-data" method="post" action="%{urlProcess}" theme="simple" cssClass="well form-horizontal">
                                    <s:hidden name="addOrEdit"/>
                                    <s:hidden name="add"/>
                                    <%--<s:form id="userForm" method="post" action="%{urlProcess}" cssClass="form-horizontal">--%>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="users.userId">User Id :</label>
                                        <div class="col-sm-3">
                                            <s:textfield id="userid" name="users.userId" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="users.username">Username :</label>
                                        <div class="col-sm-3">
                                            <s:textfield id="name" name="users.username" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="users.password">Password :</label>
                                        <div class="col-sm-3">
                                            <s:password id="password" name="users.password" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="users.password">Confirmation Password :</label>
                                        <div class="col-sm-3">
                                            <s:password id="confirmPassword" name="users.confirmPassword" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="users.divisiId">Bagian :</label>
                                        <div class="col-sm-3">
                                            <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                            <s:select list="#comboDivisi.listComboDepartment" id="users.divisiId" name="users.divisiId" onchange="listPosisi()"
                                                      listKey="departmentId" listValue="departmentName" headerKey="" headerValue="" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" >Jabatan :</label>
                                        <div class="col-sm-3">
                                            <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                                            <select id="positionId" class="form-control" name="users.positionId"></select>
                                            <%--<s:select list="#comboPosition.listOfComboPositions" id="positionid" name="users.positionId"
                                                      listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control" />--%>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="users.areaId">Area :</label>
                                        <div class="col-sm-3">
                                            <s:action id="comboArea" namespace="/admin/user" name="initComboArea_user"/>
                                            <s:select list="#comboArea.listOfComboAreas" id="areaid" name="users.areaId"
                                                      listKey="areaId" listValue="areaName" headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control"
                                                      onchange="selectOptionBranch(this)"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-5">Unit :</label>
                                        <div class="col-sm-3">
                                            <%--<s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>--%>
                                            <%--<s:select list="#comboBranch.listOfComboBranches" id="branchid" name="users.branchId" onchange="listPosisi()"--%>
                                                      <%--listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]"--%>
                                                      <%--cssClass="form-control" />--%>
                                            <select class="form-control" name="users.branchId" id="branchid"></select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="users.roleId">Role :</label>
                                        <div class="col-sm-3">
                                            <s:action id="comboRole" namespace="/admin/user" name="initComboRole_user"/>
                                            <s:select list="#comboRole.listOfComboRoles" id="roleid" name="users.roleId"
                                                      listKey="stRoleId" listValue="roleName" headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control"
                                                      onchange="showSelectPoli(this)"/>
                                        </div>
                                    </div>
                                    <div class="form-group" id="poli-form" style="display: none">
                                        <label class="control-label col-sm-5" for="users.roleId">Poli :</label>
                                        <div class="col-sm-3">
                                            <s:action id="initComboPoli" namespace="/checkup"
                                                      name="getComboPelayanan_checkup"/>
                                            <s:select cssStyle="margin-top: 7px; width: 100%"
                                                      list="#initComboPoli.listOfPelayanan" id="poli"
                                                      name="users.idPelayanan" listKey="idPelayanan"
                                                      listValue="namaPelayanan"
                                                      headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control select2" theme="simple"/>
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
                                    <div class="form-group">
                                        <label class="control-label col-sm-5" for="fileUpload">Upload Photo:</label>
                                        <div class="col-sm-3">
                                            <s:file id="fileUpload" name="fileUpload" cssClass="form-control" />
                                        </div>
                                    </div>
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
                                            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addUserForm" id="save" name="save"
                                                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                <i class="fa fa-check"></i>
                                                Save
                                            </sj:submit>

                                            <button type="button" class="btn btn-danger" onclick="window.location.href='<s:url action="initForm_user"/>'">
                                                <i class="fa fa-refresh"></i> Reset
                                            </button>
                                        </div>

                                    </div>
                                </s:form>
                                <table>
                                    <tr>
                                        <div id="crud">
                                            <td>
                                                <table>

                                                    <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                               resizable="false"
                                                               height="250" width="600" autoOpen="false" title="Saving ...">
                                                        Please don't close this window, server is processing your request ...
                                                        </br>
                                                        </br>
                                                        </br>
                                                        <center>
                                                            <img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">
                                                        </center>
                                                    </sj:dialog>

                                                    <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false" closeOnEscape="false"
                                                               height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                               buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         resetField();
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
                                                                <center>
                                                                    <div id="errorValidationMessage"></div></center>
                                                            </label>
                                                        </div>
                                                    </sj:dialog>

                                                </table>
                                            </td>
                                        </div>
                                    </tr>
                                </table>
                            </div>
                        </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<script>
    window.listPosisi = function(branch, divisi){
        var branch = document.getElementById("branchid").value;
        var divisi = document.getElementById("users.divisiId").value;

        $('#positionId').empty();
        PositionAction.searchPosition2(branch, divisi, function(listdata){
            $.each(listdata, function (i, item) {
                $('#positionId').append($("<option></option>")
                        .attr("value",item.positionId)
                        .text(item.positionName));
            });
        });
    }

    function selectOptionBranch(select){
        var idx     = select.selectedIndex;
        var idArea  = select.options[idx].value;
        var option = "";

        BranchAction.getListComboBranch(idArea, function (response) {
            option = "<option value=''>[Select One]</option>";
            if (response != null) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.branchId + "'>" + item.branchName + "</option>";
                });
            } else {
                option = option;
            }
        });
        $('#branchid').html(option);
    }

    function showSelectPoli(select){
        var idx     = select.selectedIndex;
        var idRole  = select.options[idx].value;
        if(idRole == 34){
            $('#poli-form').show();
        }else{
            $('#poli-form').hide();
        }
    }
</script>
<%@ include file="/pages/common/footer.jsp" %>


<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>