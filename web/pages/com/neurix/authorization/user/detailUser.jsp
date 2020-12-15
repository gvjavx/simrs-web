<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 19/02/2018
  Time: 06.07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%@ include file="/pages/common/header.jsp" %>
    <style>
        .btn-save{
            float: right;
        }
    </style>
    <script type="text/javascript">

        function setReadOnlyField(){
            document.getElementById("userid").disabled = true;
            document.getElementById("name").disabled = true;
            document.getElementById("password").disabled = true;
            document.getElementById("emailid").disabled = true;
            document.getElementById("positionid").disabled = true;
            document.getElementById("roleid-edit").disabled = true;
            document.getElementById("areaid").disabled = true;
            document.getElementById("branchid_edit").disabled = true;
            document.getElementById("divisiId").disabled = true;
            document.getElementById("pelayananId-edit").disabled = true;
            document.getElementById("ruanganId-edit").disabled = true;
            document.getElementById("vendorId-edit").disabled = true;
        }

        $.subscribe('beforeProcessSave', function (event, data) {
            if (document.getElementById("userid").value != '' &&
                    document.getElementById("name").value != '' &&
                    document.getElementById("password").value != '' &&
                    document.getElementById("confirmPassword").value != '' &&
                    document.getElementById("positionid").value != '' &&
                    document.getElementById("roleid-edit").value != '' &&
                    document.getElementById("areaid").value != '' &&
                    document.getElementById("branchid_edit").value != '' &&
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
                    msg = "Field <strong>User Id</strong> is required." + '<br/>';
                }

                if (document.getElementById("name").value == '') {
                    msg = msg + "Field <strong>UserName</strong> is required." + '<br/>';
                }

                if (document.getElementById("password").value == '') {
                    msg = msg + "Field <strong>Password</strong> is required." + '<br/>';
                }

                if (document.getElementById("confirmPassword").value == '') {
                    msg = msg + "Field <strong>ConfirmPassword</strong> is required." + '<br/>';
                }

                if (document.getElementById("password").value != document.getElementById("confirmPassword").value) {
                    msg = msg + "Field <strong>Password</strong> and <strong>confirmPassword</strong> not match." + '<br/>';
                }

                if (document.getElementById("positionid").value == '') {
                    msg = msg + "Field <strong>Position Id</strong> is required." + '<br/>';
                }

                if (document.getElementById("roleid-edit").value == '') {
                    msg = msg + "Field <strong>Role Id</strong> is required." + '<br/>';
                }

                if (document.getElementById("areaid").value == '') {
                    msg = msg + "Field <strong>Area Id</strong> is required." + '<br/>';
                }

                if (document.getElementById("branchid_edit").value == '') {
                    msg = msg + "Field <strong>Branch Id</strong> is required." + '<br/>';
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
                $.publish('showInfoDialog');
                jQuery(".ui-dialog-titlebar-close").hide();
            }
        });

        $.subscribe('errorDialog', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function cancelBtn() {
            //alert('aa');
            window.location.href='<s:url action="search_user"/>';
            $(this).dialog('close');
        };

    </script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RoleAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PelayananAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RuanganAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/VendorAction.js"/>'></script>

</head>

<body class="hold-transition skin-blue sidebar-mini">

<section class="content-header">
    <h1>
        <div class="box-header with-border">
            <s:if test="isDelete()">
            Delete User
            </s:if>
            <s:elseif test="isAddOrEdit()">
            Update User
            </s:elseif>
            <s:else>
            View User
            </s:else>
            <%--<small>HRIS</small>--%>
    </h1>
</section>

<!-- Main content -->
<section class="content">

    <!-- Your Page Content Here -->
    <div class="row">
        <div class="col-md-12">
            <div class="box box-primary">

            </div>

            <s:form id="modifyUserForm" method="post" enctype="multipart/form-data" namespace="/admin/user" action="save_user" cssClass="well form-horizontal">
                <s:hidden id="addOrEditFlag" name="addOrEdit"/>
                <s:hidden id="deleteFlag" name="delete"/>

                <div class="box-body">
                    <div class="form-group">
                        <table align="center">

                            <tr>
                                <td>
                                    <label class="control-label" for="users.userId">User Id</label>
                                </td>
                                <td>
                                    <table>
                                        <s:textfield cssClass="form-control"  id="userid" name="users.userId" required="true" readonly="true"/>
                                        <s:hidden name="userId"/>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label" for="users.userName">User Name</label>
                                </td>

                                <td>
                                    <table>
                                        <s:textfield cssClass="form-control" id="name" name="users.username" required="true"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label" for="users.password">Password</label>
                                </td>

                                <td>
                                    <table>
                                        <s:password cssClass="form-control" id="password" name="users.password" required="true" showPassword="true"/>
                                    </table>
                                </td>
                            </tr>

                            <s:if test="isAddOrEdit()">
                                <tr>
                                    <td style="text-align: left">
                                        <label class="control-label" for="users.confirmPassword">Confirm</label>
                                    </td>

                                    <td>
                                        <table>
                                            <s:password cssClass="form-control"  id="confirmPassword" name="users.confirmPassword" required="true" showPassword="true"/>
                                        </table>
                                    </td>
                                </tr>
                            </s:if>
                            <tr>
                                <td>
                                    <label class="control-label" for="users.email">Email</label>
                                </td>

                                <td>
                                    <table>
                                        <s:textfield cssClass="form-control" id="emailid" name="users.email" required="true"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label" for="users.positionId">Divisi</label>
                                </td>

                                <td>
                                    <table>
                                        <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId" name="users.divisiId"
                                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label" for="users.positionId">Position</label>
                                </td>

                                <td>
                                    <table>
                                        <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                                        <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="positionid" name="users.positionId" required="false"
                                                  listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label" for="users.areaId">Area</label>
                                </td>

                                <td>
                                    <table>
                                        <s:action id="comboArea" namespace="/admin/user" name="initComboArea_user"/>
                                        <s:select cssClass="form-control" list="#comboArea.listOfComboAreas" id="areaid" name="users.areaId" required="true"
                                                  listKey="areaId" listValue="areaName" headerKey="" headerValue="[Select one]" />
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="control-label" for="users.branchId">Branch</label>
                                </td>

                                <td>
                                    <table>
                                        <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                        <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid_edit" name="users.branchId" required="true"
                                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <label class="control-label" for="users.roleId">Role</label>
                                </td>

                                <td>
                                    <table>
                                        <s:action id="comboRole" namespace="/admin/user" name="initComboRole_user"/>
                                        <s:select cssClass="form-control" list="#comboRole.listOfComboRoles" id="roleid-edit" name="users.roleId" required="false"
                                                  listKey="stRoleId" listValue="roleName" headerKey="" headerValue="[Select one]" onchange="showComboEdit(this.value)"/>
                                    </table>
                                </td>
                            </tr>

                            <tr style="display: none" id="form-pelayanan-edit">
                                <td>
                                    <label class="control-label"  for="users.roleId">Pelayanan</label>
                                </td>
                                <td>
                                    <select style="width: 100%" class="form-control select2" name="users.idPelayanan" id="pelayananId-edit">
                                        <option value="">[Select One]</option>
                                    </select>
                                </td>
                            </tr>

                            <tr style="display: none" id="form-ruangan-edit">
                                <td>
                                    <label class="control-label"  for="users.roleId">Ruangan</label>
                                </td>
                                <td>
                                    <select style="width: 100%" class="form-control select2" name="users.idRuangan" id="ruanganId-edit">
                                        <option value="">[Select One]</option>
                                    </select>
                                </td>
                            </tr>

                            <tr style="display: none" id="form-vendor-edit">
                                <td>
                                    <label class="control-label"  for="users.roleId">Vendor</label>
                                </td>
                                <td>
                                    <select style="width: 100%" class="form-control select2" name="users.idVendor" id="vendorId-edit">
                                        <option value="">[Select One]</option>
                                    </select>
                                </td>
                            </tr>


                            <s:if test="isAddOrEdit()">
                                <tr>
                                    <td>
                                        <label class="control-label" for="fileUpload">Upload Photo</label>
                                    </td>

                                    <td>
                                        <table>
                                            <s:file cssClass="form-control"  name="fileUpload"  cssStyle="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;"/>
                                        </table>
                                    </td>
                                </tr>
                            </s:if>
                            <s:if test="!isDelete() && !isAddOrEdit()">
                                <tr>
                                    <td>
                                        <label class="control-label" for="users.stCreatedDate">Created Date</label>
                                    </td>

                                    <td>
                                        <table>
                                            <s:textfield cssClass="form-control"  name="users.stCreatedDate" disabled="true"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label" for="users.createdWho">Created Who</label>
                                    </td>

                                    <td>
                                        <table>
                                            <s:textfield cssClass="form-control" name="users.createdWho" disabled="true"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label" for="users.stLastUpdate">Last Update</label>
                                    </td>

                                    <td>
                                        <table>
                                            <s:textfield cssClass="form-control"  name="users.stLastUpdate" disabled="true"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label" for="users.lastUpdateWho">Updated Who</label>
                                    </td>

                                    <td>
                                        <table>
                                            <s:textfield cssClass="form-control" name="users.lastUpdateWho" disabled="true"/>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class="control-label" for="users.flag">Flag</label>
                                    </td>

                                    <td>
                                        <table>
                                            <s:textfield cssClass="form-control"  name="users.flag" disabled="true"/>
                                        </table>
                                    </td>
                                </tr>
                            </s:if>

                            <s:if test="isDelete() || !isAddOrEdit()">
                                <script>setReadOnlyField();</script>
                            </s:if>

                        </table>
                    </div>


                    <div class="box-footer">
                        <table align="center">
                            <tr>
                                <div id="cr">
                                    <td>
                                        <table align="center">
                                            <s:if test="isAddOrEdit()">
                                                <sj:submit targets="crud" type="button" cssClass="btn btn-primary btn-save" formIds="modifyUserForm" id="save" name="save"
                                                           onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                           onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                    <i class="fa fa-check"></i>
                                                    Save
                                                </sj:submit>

                                                <%--<sj:dialog id="waiting_dialog_saveuser" openTopics="showDialog" closeTopics="closeDialog" modal="true"--%>
                                                           <%--resizable="false"--%>
                                                           <%--height="250" width="600" autoOpen="false" title="Saving ...">--%>
                                                    <%--Please don't close this window, server is processing your request ...--%>
                                                    <%--</br>--%>
                                                    <%--</br>--%>
                                                    <%--</br>--%>
                                                    <%--<img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">--%>
                                                <%--</sj:dialog>--%>
                                                <sj:dialog id="waiting_dialog_saveuser" openTopics="showDialog"
                                                           closeTopics="closeDialog" modal="true"
                                                           resizable="false"
                                                           height="250" width="600" autoOpen="false"
                                                           title="Save Data ...">
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

                                                <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false" closeOnEscape="false"
                                                           height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                           buttons="{
                                                                    'OK':function() {
                                                                               $('#info_dialog').dialog('close');
                                                                               $('#view_dialog_users').dialog('close');
                                                                               document.userForm.action='search_user.action';
                                                                               document.userForm.submit();
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
                                            </s:if>
                                            <s:elseif test="isDelete()">
                                                <sj:submit targets="crud" type="button" cssClass="btn btn-danger btn-save" formIds="modifyUserForm" id="delete" name="delete"
                                                           onBeforeTopics="beforeProcessDelete" onCompleteTopics="closeDialog,successDialog"
                                                           onSuccessTopics="successDialog" onErrorTopics="errorDialog">
                                                    <i class="fa fa-trash"></i>
                                                    Delete
                                                </sj:submit>

                                                <%--<sj:dialog id="waiting_dialog_saveuser" openTopics="showDialog" closeTopics="closeDialog" modal="true"--%>
                                                           <%--resizable="false"--%>
                                                           <%--height="250" width="600" autoOpen="false" title="Deleting ...">--%>
                                                    <%--Please don't close this window, server is processing your request ...--%>
                                                    <%--</br>--%>
                                                    <%--</br>--%>
                                                    <%--</br>--%>
                                                    <%--<center>--%>
                                                        <%--<img border="0" src="<s:url value="/pages/images/indicator-trash.gif"/>" name="image_indicator_trash">--%>
                                                    <%--</center>--%>
                                                <%--</sj:dialog>--%>
                                                <sj:dialog id="waiting_dialog_saveuser" openTopics="showDialog"
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

                                                <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                           height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                           buttons="{
                                                                     'OK':function() {
                                                                              $('#info_dialog').dialog('close');
                                                                              $('#view_dialog_users').dialog('close');
                                                                              document.userForm.action='search_user.action';
                                                                              document.userForm.submit();

                                                                          }
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
                                            </s:elseif>
                                        </table>
                                    </td>
                                </div>

                                <td>
                                    <table>
                                        <button type="button" id="cancel" class="btn btn-default btn-save"  onclick="cancelBtn();">
                                            <i class="icon-remove-circle"/> Cancel
                                        </button>
                                    </table>
                                </td>

                            </tr>
                        </table>
                    </div>
                </div>
            </s:form>

        </div>
    </div>
    </div>
    <div class="row">
        <div class="col-md-12">

        </div>
    </div>
</section>
<script>

    var pelayananId = '<s:property value="users.idPelayanan" />';
    var ruanganId = '<s:property value="users.idRuangan" />';
    var vendorId = '<s:property value="users.idVendor" />';

    $( document ).ready(function() {
        console.log("pelayanan Id edit -> "+pelayananId);
        console.log("ruangan Id edit -> "+ruanganId);
        console.log("vendor Id edit -> "+vendorId);
        console.log("role Id edit -> "+ $("#roleid-edit").val());
        if(ruanganId != null && ruanganId != "") {
            var roleIdEdit = $("#roleid-edit").val();
            showComboEdit(roleIdEdit, ruanganId);
        }
        else if(vendorId != null && vendorId != "") {
            var roleIdEdit = $("#roleid-edit").val();
            showComboEdit(roleIdEdit, vendorId);
        }
        else if(pelayananId != null && pelayananId != "") {
            var roleIdEdit = $("#roleid-edit").val();
            showComboEdit(roleIdEdit, pelayananId);
        }
    });

    function showComboEdit(role, idComponent){

        var branch = $('#branchid_edit').val();
        if (branch == null || branch == "")
            alert("Pilih Unit Dahulu");
        RoleAction.getRoleById(role, function (res) {
            console.log(res);
            if(res.tipePelayanan == "rawat_inap") {
                $('#form-ruangan-edit').show();
                $('#form-pelayanan-edit').hide();
                $('#form-vendor-edit').hide();
                getListRuanganByBranchEdit(branch, idComponent);
            } else if(res.tipePelayanan == "pbf"){
                $('#form-vendor-edit').show();
                $('#form-pelayanan-edit').hide();
                $('#form-ruangan-edit').hide();
                getListVendorEdit(idComponent);
            } else if(res.tipePelayanan != "" && res.tipePelayanan != null){
                $('#form-pelayanan-edit').show();
                $('#form-ruangan-edit').hide();
                $('#form-vendor-edit').hide();
                getListPelayananByBranchAndTipeEdit(branch, res.tipePelayanan, idComponent);
            }
        });
    }

    function getListPelayananByBranchAndTipeEdit(branch, tipe, pelayanan) {
        var option = "";
        PelayananAction.getListPelayananByBranchAndTipe(branch, tipe, function (response) {
            option = "<option value=''>[Select One]</option>";
            if (response.length > 0) {
                $.each(response, function (i, item) {

                    if (item.idPelayanan == pelayanan){
                        option += "<option value='" + item.idPelayanan + "' selected>" + item.namaPelayanan + "</option>";
                    } else {
                        option += "<option value='" + item.idPelayanan + "'>" + item.namaPelayanan + "</option>";
                    }
                });
            } else {
                option = option;
                $('#form-pelayanan-edit').hide();
            }
            $('#pelayananId-edit').html(option);
        });
    }

    function getListRuanganByBranchEdit(branch, ruangan) {
        var option = "";
        RuanganAction.getListRuangan(branch, function(response) {
            option = "<option value=''>[Select One]</option>";
            if (response.length > 0) {
                $.each(response, function (i, item) {

                    if (item.idRuangan == ruangan){
                        option += "<option value='" + item.idRuangan + "' selected>" + item.namaRuangan + "</option>";
                    } else {
                        option += "<option value='" + item.idRuangan + "'>" + item.namaRuangan + "</option>";
                    }
                });
            } else {
                option = option;
                $('#form-ruangan-edit').hide();
            }
            $('#ruanganId-edit').html(option);
        });
    }

    function getListVendorEdit(vendor) {
        var option = "";
        VendorAction.getListVendor(function(response) {
            option = "<option value=''>[Select One]</option>";
            if (response.length > 0) {
                $.each(response, function (i, item) {

                    if (item.idVendor == vendor){
                        option += "<option value='" + item.idVendor + "' selected>" + item.namaVendor + "</option>";
                    } else {
                        option += "<option value='" + item.idVendor + "'>" + item.namaVendor + "</option>";
                    }
                });
            } else {
                option = option;
                $('#form-vendor-edit').hide();
            }
            $('#vendorId-edit').html(option);
        });
    }

</script>

<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>

