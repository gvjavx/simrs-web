<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="ivelincloud" uri="/WEB-INF/tld/mainmenu.tld" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>

    <%@ include file="/pages/common/header.jsp" %>

    <script type='text/javascript' src='<s:url value="/dwr/interface/UserAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/BranchAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PositionAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RoleAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PelayananAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RuanganAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/VendorAction.js"/>'></script>
    <style>
        .form-check {
            display: inline-block;
            padding-left: 2px;
        }

        .form-check input {
            padding: 0;
            height: initial;
            width: initial;
            margin-bottom: 0;
            display: none;
            cursor: pointer;
        }

        .form-check label {
            position: relative;
            cursor: pointer;
        }

        .form-check label:before {
            content:'';
            -webkit-appearance: none;
            background-color: transparent;
            border: 2px solid #0079bf;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05);
            padding: 10px;
            display: inline-block;
            position: relative;
            vertical-align: middle;
            cursor: pointer;
            margin-right: 5px;
        }

        .form-check input:checked + label:after {
            content: '';
            display: block;
            position: absolute;
            top: 2px;
            left: 9px;
            width: 6px;
            height: 14px;
            border: solid #0079bf;
            border-width: 0 2px 2px 0;
            transform: rotate(45deg);
        }
    </style>
</head>

<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="se-pre-con"></div>
<%@ include file="/pages/common/headerNav.jsp" %>

<ivelincloud:mainMenu/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Data User
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-filter"></i> Pencarian Data User</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <s:form id="userForm" method="post" namespace="/admin/user"
                                    action="search_user.action"
                                    theme="simple" cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-4">User ID</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="userId" name="users.userId" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group top_margin">
                                    <label class="control-label col-sm-4">Username</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="username" name="users.username" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Area</label>
                                    <div class="col-sm-4">
                                        <s:action id="comboArea" namespace="/admin/user" name="initComboArea_user"/>
                                        <s:select list="#comboArea.listOfComboAreas" id="areaId" name="users.areaId"
                                                  listKey="areaId" listValue="areaName" headerKey=""
                                                  headerValue="[Select one]"
                                                  cssClass="form-control select2" cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Unit</label>
                                    <div class="col-sm-4">
                                        <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                        <s:select list="#comboBranch.listOfComboBranches" id="branchid"
                                                  name="users.branchId"
                                                  listKey="branchId" listValue="branchName" headerKey=""
                                                  headerValue="[Select one]"
                                                  cssClass="form-control select2" cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Divisi/Bidang</label>
                                    <div class="col-sm-4">
                                        <s:action id="comboDivisi" namespace="/department"
                                                  name="searchDepartment_department"/>
                                        <s:select list="#comboDivisi.listComboDepartment" id="users.divisiId"
                                                  name="users.divisiId"
                                                  listKey="departmentId" listValue="departmentName" headerKey=""
                                                  headerValue="[Select One]"
                                                  cssClass="form-control select2" cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Posisi/Jabatan</label>
                                    <div class="col-sm-4">
                                        <s:action id="comboPosition" namespace="/admin/user"
                                                  name="initComboPosition_user"/>
                                        <s:select list="#comboPosition.listOfComboPositions" id="positionId"
                                                  name="users.positionId"
                                                  listKey="stPositionId" listValue="positionName" headerKey=""
                                                  headerValue="[Select one]"
                                                  cssClass="form-control select2" cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4">Role</label>
                                    <div class="col-sm-4">
                                        <s:action id="comboRole" namespace="/admin/user" name="initComboRole_user"/>
                                        <s:select list="#comboRole.listOfComboRoles" id="roleId" name="users.roleId"
                                                  listKey="stRoleId" listValue="roleName" headerKey=""
                                                  headerValue="[Select one]"
                                                  cssClass="form-control select2" cssStyle="width: 100%"/>
                                    </div>
                                </div>
                                <div class="form-group top_margin">
                                    <label class="control-label col-sm-4">Email</label>
                                    <div class="col-sm-4">
                                        <s:textfield id="email" name="users.email" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group top_margin">
                                    <label class="control-label col-sm-4">Flag</label>
                                    <div class="col-sm-4">
                                        <s:select list="#{'Y':'Active', 'N':'Non Active'}" id="flag" name="users.flag"
                                                  headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4"></label>
                                    <div class="col-sm-6" style="margin-top: 7px">
                                        <sj:submit type="button" cssClass="btn btn-success" formIds="userForm"
                                                   id="search" name="search"
                                                   onClickTopics="showDialogLoading"
                                                   onCompleteTopics="closeDialogLoading">
                                            <i class="fa fa-search"></i>
                                            Search
                                        </sj:submit>
                                        <a type="button" class="btn btn-danger" href="initForm_user.action">
                                            <i class="fa fa-refresh"></i> Reset
                                        </a>
                                        <a onclick="showModal('add')" class="btn btn-primary"><i class="fa fa-plus"></i>
                                            Add User</a>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-5"></label>
                                    <div class="col-sm-5" style="display: none">

                                        <sj:dialog id="waiting_dialog" openTopics="showDialogLoading"
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
                                                <img class="spin" border="0"
                                                     style="width: 50px; height: 50px; margin-top: -70px; margin-left: 45px"
                                                     src="<s:url value="/pages/images/plus-logo-nmu-2.png"/>"
                                                     name="image_indicator_write">
                                            </center>
                                        </sj:dialog>
                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true"
                                                   resizable="false"
                                                   closeOnEscape="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                                                'OK':function() {
                                                                                         $('#info_dialog').dialog('close');
                                                                                         window.location.reload(true);
                                                                                     }
                                                                            }"
                                        >
                                            <s:hidden id="close_pos"></s:hidden>
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>"
                                                 name="icon_success">
                                            Record has been saved successfully.
                                        </sj:dialog>
                                        <sj:dialog id="view_dialog_user" openTopics="showDialogUser" modal="true"
                                                   resizable="false" cssStyle="text-align:left;"
                                                   height="650" width="900" autoOpen="false" title="View Detail"
                                        >
                                            <center><img border="0" src="<s:url value="/pages/images/spinner.gif"/>"
                                                         alt="Loading..."/></center>
                                        </sj:dialog>
                                        <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true"
                                                   resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                                'OK':function() { $('#error_dialog').dialog('close'); }
                                                                            }"
                                        >
                                            <div class="alert alert-danger alert-dismissible">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                         name="icon_error"> System Found : <p id="errorMessage"></p>
                                                </label>
                                            </div>
                                        </sj:dialog>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                    </div>
                    <div class="box-header with-border"></div>
                    <div class="box-header with-border">
                        <h3 class="box-title"><i class="fa fa-th-list"></i> Daftar User</h3>
                    </div>
                    <div class="box-body">
                        <table id="sortTable" class="table table-bordered table-striped">
                            <thead>
                            <tr bgcolor="#90ee90">
                                <td>User ID</td>
                                <td>Username</td>
                                <td>Email</td>
                                <td>Unit</td>
                                <td>Role</td>
                                <td align="center">Action</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="#session.listOfResult" var="row">
                                <tr>
                                    <td><s:property value="userId"/></td>
                                    <td><s:property value="username"/></td>
                                    <td><s:property value="email"/></td>
                                    <td><s:property value="branchName"/></td>
                                    <td><s:property value="roleName"/></td>
                                    <td align="center" width="10%">
                                        <img class="hvr-grow"
                                             onclick="showModal('detail', '<s:property value="userId"/>',
                                                     '<s:property value="flag"/>')"
                                             style="cursor: pointer"
                                             src="<s:url value="/pages/images/icons8-view-25.png"/>">
                                        <s:if test='#row.flag == "Y"'>
                                            <img class="hvr-grow"
                                                 onclick="showModal('edit', '<s:property value="userId"/>',
                                                         '<s:property value="flag"/>')"
                                                 style="cursor: pointer"
                                                 src="<s:url value="/pages/images/icons8-create-25.png"/>">
                                            <img class="hvr-grow"
                                                 onclick="showModal('delete', '<s:property value="userId"/>',
                                                         '<s:property value="flag"/>')"
                                                 style="cursor: pointer"
                                                 src="<s:url value="/pages/images/cancel-flat-new.png"/>">
                                        </s:if>
                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- /.content -->
</div>

<div class="modal fade" id="modal-add">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user-plus"></i> <span id="title_modal"></span>
                </h4>
            </div>
            <div class="modal-body" id="temp_scrol">
                <div class="alert alert-danger alert-dismissible" style="display: none" id="warning_add">
                    <h4><i class="icon fa fa-ban"></i> Warning!</h4>
                    <p id="msg_add"></p>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3">User ID</label>
                        <div class="col-md-7">
                            <input class="form-control" id="set_user_id"
                                   oninput="inputWarning('war_set_user_id', 'cor_set_user_id')"
                                   onchange="checkUserid(this.value)">
                            <div class="alert alert-danger alert-dismissible" style="display: none; padding: 5px"
                                 id="warning_userid">
                                <p id="msg_userid"></p>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 10px; display: none; margin-left: -20px"
                               id="war_set_user_id">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 10px; display: none; margin-left: -20px"
                               id="cor_set_user_id"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row top_margin">
                    <div class="form-group">
                        <label class="col-md-3">Username</label>
                        <div class="col-md-7">
                            <input class="form-control" id="set_username"
                                   oninput="inputWarning('war_set_username', 'cor_set_username')">
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 10px; display: none; margin-left: -20px"
                               id="war_set_username">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 10px; display: none; margin-left: -20px"
                               id="cor_set_username"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row top_margin" style="display: none" id="cek_password">
                    <div class="form-group">
                        <label class="col-md-3">Rubah Password ?</label>
                        <div class="col-md-7">
                            <div class="form-check">
                                <input onclick="changePassword(this.id)" type="checkbox" id="is_edit_password" value="yes">
                                <label for="is_edit_password"></label>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="form-password">
                    <div class="row top_margin">
                        <div class="form-group">
                            <label class="col-md-3">Password</label>
                            <div class="col-md-7">
                                <input type="password" class="form-control" id="set_password"
                                       oninput="inputWarning('war_set_password', 'cor_set_password')">
                            </div>
                            <div class="col-md-2">
                                <p style="color: red; margin-top: 10px; display: none; margin-left: -20px"
                                   id="war_set_password">
                                    <i class="fa fa-times"></i> required</p>
                                <p style="color: green; margin-top: 10px; display: none; margin-left: -20px"
                                   id="cor_set_password"><i class="fa fa-check"></i> correct</p>
                            </div>
                        </div>
                    </div>
                    <div class="row top_margin">
                        <div class="form-group">
                            <label class="col-md-3">Confirm Password</label>
                            <div class="col-md-7">
                                <input type="password" class="form-control" id="set_con_password"
                                       oninput="inputWarning('war_set_con_password', 'cor_set_con_password'); cekPassword(this.value)">
                            </div>
                            <div class="col-md-2">
                                <p style="color: red; margin-top: 10px; display: none; margin-left: -20px"
                                   id="war_set_con_password">
                                    <i class="fa fa-times"></i> required</p>
                                <p style="color: green; margin-top: 10px; display: none; margin-left: -20px"
                                   id="cor_set_con_password"><i class="fa fa-check"></i> correct</p>
                                <p style="color: red; margin-top: 10px; display: none; margin-left: -20px"
                                   id="not_valid">
                                    <i class="fa fa-times"></i> invalid</p>
                                <p style="color: green; margin-top: 10px; display: none; margin-left: -20px"
                                   id="valid"><i class="fa fa-check"></i> valid</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3 top_margin">Area</label>
                        <div class="col-md-7">
                            <s:action name="initComboArea_user" namespace="/admin/user" id="areaCombo"/>
                            <s:select list="#areaCombo.listOfComboAreas" id="set_area"
                                      onchange="listUnit(this.value); inputWarning('war_set_area', 'cor_set_area')"
                                      listKey="areaId" listValue="areaName" headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2" cssStyle="width: 100%"
                            />
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 10px; display: none; margin-left: -20px"
                               id="war_set_area">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 10px; display: none; margin-left: -20px"
                               id="cor_set_area"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3 top_margin">Unit</label>
                        <div class="col-md-7">
                            <select onchange="inputWarning('war_set_unit', 'cor_set_unit');" id="set_unit"
                                    style="width: 100%" class="form-control select2">
                                <option value="">[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 10px; display: none; margin-left: -20px"
                               id="war_set_unit">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 10px; display: none; margin-left: -20px"
                               id="cor_set_unit"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3 top_margin">Bidang</label>
                        <div class="col-md-7">
                            <s:action id="comboDivisi123" namespace="/department" name="searchDepartment_department"/>
                            <s:select list="#comboDivisi123.listComboDepartment" id="set_bidang"
                                      onchange="listJabatan(this.value); inputWarning('war_set_bidang', 'cor_set_bidang')"
                                      listKey="departmentId" listValue="departmentName" headerKey=""
                                      headerValue="[Select One]" cssClass="form-control select2"
                                      cssStyle="width: 100%"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 10px; display: none; margin-left: -20px"
                               id="war_set_bidang">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 10px; display: none; margin-left: -20px"
                               id="cor_set_bidang"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3 top_margin">Jabatan</label>
                        <div class="col-md-7">
                            <select id="set_jabatan" class="form-control select2" style="width: 100%"
                                    onchange="inputWarning('war_set_jabatan', 'cor_set_jabatan')">
                                <option value="">[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 10px; display: none; margin-left: -20px"
                               id="war_set_jabatan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 10px; display: none; margin-left: -20px"
                               id="cor_set_jabatan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-md-3 top_margin">Role</label>
                        <div class="col-md-7">
                            <s:action id="comboRole" namespace="/admin/user" name="initComboRole_user"/>
                            <s:select list="#comboRole.listOfComboRoles" id="set_role"
                                      listKey="stRoleId" listValue="roleName" headerKey="" headerValue="[Select one]"
                                      cssClass="form-control select2" cssStyle="width: 100%"/>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 10px; display: none; margin-left: -20px"
                               id="war_set_role">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 10px; display: none; margin-left: -20px"
                               id="cor_set_role"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row" style="display: none" id="form-pelayanan">
                    <div class="form-group">
                        <label class="col-md-3 top_margin">Pelayanan</label>
                        <div class="col-md-7">
                            <select style="width: 100%" class="form-control select2" id="set_pelayanan"
                                    onchange="inputWarning('war_set_pelayanan', 'cor_set_pelayanan')">
                                <option value="">[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 10px; display: none; margin-left: -20px"
                               id="war_set_pelayanan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 10px; display: none; margin-left: -20px"
                               id="cor_set_pelayanan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row" style="display: none" id="form-ruangan">
                    <div class="form-group">
                        <label class="col-md-3 top_margin">Ruangan</label>
                        <div class="col-md-7">
                            <select style="width: 100%" class="form-control select2" id="set_ruangan"
                                    onchange="inputWarning('war_set_ruangan', 'cor_set_ruangan')">
                                <option value="">[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 10px; display: none; margin-left: -20px"
                               id="war_set_ruangan">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 10px; display: none; margin-left: -20px"
                               id="cor_set_ruangan"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row" style="display: none" id="form-vendor">
                    <div class="form-group">
                        <label class="col-md-3 top_margin">Vendor</label>
                        <div class="col-md-7">
                            <select style="width: 100%" class="form-control select2" id="set_vendor"
                                    onchange="inputWarning('war_set_vendor', 'cor_set_vendor')">
                                <option value="">[Select One]</option>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 10px; display: none; margin-left: -20px"
                               id="war_set_vendor">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 10px; display: none; margin-left: -20px"
                               id="cor_set_vendor"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row top_margin">
                    <div class="form-group">
                        <label class="col-md-3">Email</label>
                        <div class="col-md-7">
                            <input class="form-control" id="set_email"
                                   oninput="inputWarning('war_set_email', 'cor_set_email')"
                                   onchange="checkEmail(this.value)">
                            <div class="alert alert-danger alert-dismissible" style="display: none; padding: 5px"
                                 id="warning_email">
                                <p id="msg_email"></p>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 10px; display: none; margin-left: -20px"
                               id="war_set_email">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 10px; display: none; margin-left: -20px"
                               id="cor_set_email"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
                <div class="row top_margin">
                    <div class="form-group">
                        <label class="col-md-3">Upload Foto</label>
                        <div class="col-md-7">
                            <div class="input-group">
                              <span class="input-group-btn">
                              <span class="btn btn-default btn-file">
                               Browse…<input type="file" accept=".jpg" name="fileUpload"
                                             onchange="$('#img_file').css('border',''); setCanvasAtasWithText('img_canvas', 'set_foto')">
                              </span>
                              </span>
                                <input type="text" class="form-control" readonly id="set_foto">
                            </div>
                            <canvas id="img_canvas"
                                    style="border: solid 1px #ccc; width: 100%; height: 135px"></canvas>
                        </div>
                        <div class="col-md-2">
                            <p style="color: red; margin-top: 10px; display: none; margin-left: -20px"
                               id="war_set_foto">
                                <i class="fa fa-times"></i> required</p>
                            <p style="color: green; margin-top: 10px; display: none; margin-left: -20px"
                               id="cor_set_foto"><i class="fa fa-check"></i> correct</p>
                        </div>
                    </div>
                </div>
            </div>
            <input id="temp_tipe_pelayanan" type="hidden">
            <input id="temp_email" type="hidden">
            <input id="temp_user_id" type="hidden">
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
                <button type="button" class="btn btn-success" id="save_add"><i
                        class="fa fa-check"></i> Save
                </button>
                <button style="display: none; cursor: no-drop" type="button" class="btn btn-success" id="load_add"><i
                        class="fa fa-spinner fa-spin"></i> Sedang Menyimpan...
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-view">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #00a65a">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color: white"><i class="fa fa-user"></i> Detail Data User</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="box-body box-profile" style="margin-top: -20px">
                                <span id="v_img"></span>
                                <h3 class="profile-username text-center" id="v_username"></h3>
                                <p class="text-muted text-center" id="v_role"></p>
                                <p class="text-muted text-center"><span id="v_flag"></span></p>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <table class="table table-striped" style="font-size: 14px">
                                <tr>
                                    <td><b>Area</b></td>
                                    <td><span id="v_area"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Unit</b></td>
                                    <td><span id="v_unit"></span></td>
                                </tr>
                                <tr>
                                    <td><b>User ID</b></td>
                                    <td><span id="v_userid"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Bidang</b></td>
                                    <td><span id="v_bidang"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Jabatan</b></td>
                                    <td><span id="v_jabatan"></span></td>
                                </tr>
                                <tr>
                                    <td><b>Email</b></td>
                                    <td><span id="v_email"></span></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="background-color: #cacaca">
                <button type="button" class="btn btn-warning" data-dismiss="modal"><i class="fa fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal-confirm-dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="fa fa-info"></i> Confirmation
                </h4>
            </div>
            <div class="modal-body">
                <h4 id="pesan"></h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal"><i class="fa fa-times"></i> No
                </button>
                <button type="button" class="btn btn-sm btn-default" id="save_con"><i class="fa fa-arrow-right"></i> Yes
                </button>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>

    $(document).ready(function () {
        $('#tindakan').addClass('active');
    });

    function showModal(tipe, id, flag) {

        if('add' == tipe || 'edit' == tipe || 'delete' == tipe){
            if('add' == tipe){
                reset();
                $('#set_role').attr('onchange','showPelayanan(this.value); inputWarning(\'war_set_role\', \'cor_set_role\')');
                $('#title_modal').text("Add User");
                $('#save_add').attr('onclick', 'saveUser(\''+tipe+'\')');
            }
            if('edit' == tipe){
                reset();
                $('#cek_password').show();
                $('#form-password').hide();
                $('#set_role').attr('onchange','showPelayanan(this.value); inputWarning(\'war_set_role\', \'cor_set_role\')');
                $('#set_user_id').attr('disabled', true);
                getDataUser(id, flag);
                $('#title_modal').text("Edit User");
                $('#save_add').attr('onclick', 'saveUser(\''+tipe+'\')');
            }
            if('delete' == tipe){
                disabled();
                $('#set_role').attr('onchange','showPelayanan(this.value); inputWarning(\'war_set_role\', \'cor_set_role\')');
                getDataUser(id, flag);
                $('#title_modal').text("Delete User");
                $('#save_add').attr('onclick', 'saveUser(\''+tipe+'\')');
            }
            $('#modal-add').modal({show: true, backdrop: 'static'});
        }

        if('detail' == tipe){
            getDataUser(id, flag);
            $('#modal-view').modal({show: true, backdrop: 'static'});
        }
    }

    function reset(){
        $('#warning_userid, #warning_email, #warning_add, #war_set_user_id, #war_set_username, #war_set_password, #war_set_con_password').hide();
        $('#not_valid, #valid, #war_set_area, #war_set_unit, #war_set_bidang, #war_set_jabatan').hide();
        $('#war_set_role, #war_set_email, #war_set_pelayanan, #war_set_ruangan, #war_set_vendor, #cek_password').hide();
        $('#set_user_id, #set_username, #set_password, #set_con_password, #set_email').val('').attr('disabled', false);
        $('#set_area, #set_unit, #set_bidang, #set_jabatan, #set_role, #set_pelayanan, #set_ruangan, #set_vendor').val('').trigger('change').attr('disabled', false);
        $('#temp_user_id, #temp_email, #temp_tipe_pelayanan').val('');
        $('#form-password').show();
        var canvas = document.getElementById('img_canvas');
        const context = canvas.getContext('2d');
        context.clearRect(0, 0, canvas.width, canvas.height);
    }

    function disabled(){
        $('#not_valid, #valid, #war_set_area, #war_set_unit, #war_set_bidang, #war_set_jabatan, #form-password').hide();
        $('#war_set_role, #war_set_email, #war_set_pelayanan, #war_set_ruangan, #war_set_vendor, #cek_password').hide();
        $('#set_user_id, #set_username, #set_password, #set_con_password, #set_email').val('').attr('disabled', true);
        $('#set_area, #set_unit, #set_bidang, #set_jabatan, #set_role, #set_pelayanan, #set_ruangan, #set_vendor').val('').trigger('change').attr('disabled', true);
        $('#temp_user_id, #temp_email, #temp_tipe_pelayanan').val('');
        var canvas = document.getElementById('img_canvas');
        const context = canvas.getContext('2d');
        context.clearRect(0, 0, canvas.width, canvas.height);
    }

    function cekPassword(val) {
        var pass = $('#set_password').val();
        if (pass != '') {
            if (val == pass) {
                $('#not_valid').hide();
                $('#valid').fadeIn();
            } else {
                $('#not_valid').fadeIn();
                $('#valid').hide();
            }
        } else {
            $('#not_valid').fadeIn();
            $('#valid').hide();
        }
    }

    function saveUser(tipe) {
        var data = "";
        if (!cekSession()) {
            var userId = $('#set_user_id').val();
            var username = $('#set_username').val();
            var password = $('#set_password').val();
            var passwordCon = $('#set_con_password').val();
            var area = $('#set_area').val();
            var unit = $('#set_unit').val();
            var bidang = $('#set_bidang').val();
            var jabatan = $('#set_jabatan').val();
            var role = $('#set_role').val();
            var email = $('#set_email').val();
            var pelayanan = $('#set_pelayanan').val();
            var ruangan = $('#set_ruangan').val();
            var vendor = $('#set_vendor').val();
            var tempUserId = $('#temp_user_id').val();
            var tempEmail = $('#temp_email').val();
            var tempPelayanan = $('#temp_tipe_pelayanan').val();
            var foto = document.getElementById('img_canvas');
            var cekFoto = "";
            if ($('#set_foto').val() != '') {
                cekFoto = convertToDataURLAtas(foto);
            }

            var cekPelayanan = true;
            if (tempPelayanan != '') {
                if (tempPelayanan == 'rawat_inap') {
                    if (ruangan != '') {
                        cekPelayanan = true;
                    } else {
                        cekPelayanan = false;
                    }
                } else if (tempPelayanan == 'pbf') {
                    if (vendor != '') {
                        cekPelayanan = true;
                    } else {
                        cekPelayanan = false;
                    }
                } else {
                    if (pelayanan != '') {
                        cekPelayanan = true;
                    } else {
                        cekPelayanan = false;
                    }
                }
            }

            var cekPassword = true;
            if(tipe == 'edit'){
                if($('#is_edit_password').is(':checked')){
                    if(password == passwordCon){
                        cekPassword = true;
                    }else{
                        cekPassword = false;
                    }
                }else{
                    cekPassword = true;
                }
            }else if(tipe == 'add'){
                if(password == passwordCon){
                    cekPassword = true;
                }else{
                    cekPassword = false;
                }
            }else{
                cekPassword = true;
            }

            if ((userId !='' && username !='' && area !='' && unit !='' && role !='' && bidang !='' && jabatan !='' && email != ''
                && cekPassword !='' && cekPelayanan !='') || (tipe = 'delete')) {
                if (tempUserId == 'Y' && tempEmail == 'Y') {
                    $('#save_add').hide();
                    $('#load_add').show();
                    data = {
                        'tipe_action': tipe,
                        'user_id': userId,
                        'user_name': username,
                        'password': password,
                        'area_id': area,
                        'branch_id': unit,
                        'divisi_id': bidang,
                        'position_id': jabatan,
                        'role_id': role,
                        'id_pelayanan': pelayanan,
                        'id_ruangan': ruangan,
                        'id_vendor': vendor,
                        'email': email,
                        'foto': cekFoto
                    }
                    var dataString = JSON.stringify(data);
                    dwr.engine.setAsync(true);
                    UserAction.saveAdd(dataString, {
                        callback: function (res) {
                            if (res.status == 'success') {
                                $('#save_add').show();
                                $('#load_add').hide();
                                $('#modal-add').modal('hide');
                                $('#info_dialog').dialog('open');
                            } else {
                                $('#save_add').show();
                                $('#load_add').hide();
                                $('#modal-add').scrollTop(0);
                                $('#warning_add').show().fadeOut(5000);
                                $('#msg_add').text(res.msg);
                            }
                        }
                    });
                } else {
                    var msg1 = "";
                    var msg2 = "";
                    if (tempUserId == 'N') {
                        msg1 = 'User ID'
                    }
                    if (tempEmail == 'N') {
                        msg2 = 'Email'
                    }

                    var plus = "";
                    if (tempUserId && tempEmail == 'N') {
                        plus = " dan ";
                    }
                    $('#modal-add').scrollTop(0);
                    $('#warning_add').show().fadeOut(5000);
                    $('#msg_add').text(msg1 + plus + msg2 + " sudah tersedia...!");
                }
            } else {
                $('#modal-add').scrollTop(0);
                $('#warning_add').show().fadeOut(5000);
                $('#msg_add').text("Silahkan cek kembali data inputan berikut...!");

                if (userId == '') {
                    $('#war_set_user_id').show();
                }
                if (username == '') {
                    $('#war_set_username').show();
                }
                if (password == '') {
                    $('#war_set_password').show();
                }
                if (passwordCon == '') {
                    $('#war_set_con_password').show();
                }
                if (password != passwordCon) {
                    $('#not_valid').show();
                    $('#valid').hide();
                }
                if (area == '') {
                    $('#war_set_area').show();
                }
                if (unit == '') {
                    $('#war_set_unit').show();
                }
                if (bidang == '') {
                    $('#war_set_bidang').show();
                }
                if (jabatan == '') {
                    $('#war_set_jabatan').show();
                }
                if (role == '') {
                    $('#war_set_role').show();
                }
                if (email == '') {
                    $('#war_set_email').show();
                }
                if (pelayanan == '') {
                    $('#war_set_pelayanan').show();
                }
                if (ruangan == '') {
                    $('#war_set_ruangan').show();
                }
                if (vendor == '') {
                    $('#war_set_vendor').show();
                }
            }
        }
    }

    function getDataUser(id, flag) {
        if (!cekSession()) {
            UserAction.initUser(id, flag, function (res) {
                if (res.username != null) {
                    var url = contextPathHeader+res.photoUserUrl;
                    if(cekImages(url)){
                        url = url;
                    }else{
                        url = contextPathHeader+'/pages/images/no-images.png';
                    }
                    $('#v_img').html('<img class="profile-user-img img-responsive img-circle" style="width: 100%; height: 140px" src="'+url+'" alt="User profile picture">');
                    $('#v_area').text(res.areaName);
                    $('#v_unit').text(res.branchName);
                    $('#v_userid').text(res.userId);
                    $('#v_username').text(res.username);
                    $('#v_bidang').text(res.divisiName);
                    $('#v_jabatan').text(res.positionName);
                    $('#v_role').text(res.roleName);
                    $('#v_email').text(res.email);
                    if("Y" == res.flag){
                        $('#v_flag').text("Active");
                        $('#v_flag').removeClass("span-danger").addClass("span-success");
                    }else{
                        $('#v_flag').text("Non Active");
                        $('#v_flag').removeClass("span-success").addClass("span-danger");
                    }

                    $('#temp_email, #temp_user_id').val('Y');
                    $('#set_user_id').val(res.userId);
                    $('#set_username').val(res.username);
                    $('#set_area').val(res.areaId).trigger('change');
                    $('#set_bidang').val(res.departmentId).trigger('change');
                    setTimeout(function () {
                        $('#set_unit').val(res.branchId).trigger('change');
                        $('#set_jabatan').val(res.positionId).trigger('change');
                        setTimeout(function () {
                            $('#set_role').val(res.roleId).trigger('change');
                            setTimeout(function () {
                                $('#set_pelayanan').val(res.idPelayanan).trigger('change');
                                $('#set_ruangan').val(res.idRuangan).trigger('change');
                                $('#set_vendor').val(res.idVendor).trigger('change');
                            },100);
                        },200);
                    },300);
                    $('#set_email').val(res.email);
                    $('#set_foto').val(res.previewPhoto);
                    if (res.photoUserUrl != null) {
                        var canvas = document.getElementById('img_canvas');
                        var ctx = canvas.getContext('2d');
                        var img = new Image();
                        img.src = contextPathHeader+res.photoUserUrl;
                        img.onload = function (ev) {
                            canvas.width = img.width;
                            canvas.height = img.height;
                            ctx.clearRect(0, 0, canvas.width, canvas.height);
                            ctx.drawImage(img, 0, 0);
                        }
                    }
                }
            });
        }
    }

    function listUnit(area) {
        var option = '<option value="">[Select One]</option>';
        BranchAction.getComboBranchByArea(area, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.branchId + "'>" + item.branchName + "</option>";
                });
                $('#set_unit').html(option);
            } else {
                $('#set_unit').html(option);
            }
        });
    }

    function listJabatan(divisi) {
        var option = '<option value="">[Select One]</option>';
        PositionAction.searchPositionBiodata(divisi, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.positionId + "'>" + item.positionName + "</option>";
                });
                $('#set_jabatan').html(option);
            } else {
                $('#set_jabatan').html(option);
            }
        });
    }

    function showPelayanan(role) {
        var branch = $('#set_unit').val();
        if(role != ''){
            if (branch == null || branch == "") {
                $('#modal-add').scrollTop(0);
                $('#warning_add').show();
                $('#msg_add').text("Silahkan pilih unit terlebih dahulu...!");
                setTimeout(function () {
                    $('#set_role').val('').trigger('change');
                    $('#warning_add').hide();
                }, 5000);
            } else {
                RoleAction.getRoleById(role, function (res) {
                    $('#form-pelayanan').hide();
                    $('#form-ruangan').hide();
                    $('#form-vendor').hide();
                    if (res.tipePelayanan == "rawat_inap") {
                        $('#form-ruangan').show();
                        getListRuanganByBranch(branch);
                        $('#temp_tipe_pelayanan').val(res.tipePelayanan);
                        $('#set_pelayanan').val('');
                        $('#set_vendor').val('');
                    } else if (res.tipePelayanan == "pbf") {
                        $('#form-vendor').show();
                        getListVendorByBranch();
                        $('#temp_tipe_pelayanan').val(res.tipePelayanan);
                        $('#set_pelayanan').val('');
                        $('#set_vendor').val('');
                    } else if (res.tipePelayanan != "" && res.tipePelayanan != null) {
                        $('#form-pelayanan').show();
                        getListPelayananByBranchAndTipe(branch, res.tipePelayanan);
                        $('#temp_tipe_pelayanan').val(res.tipePelayanan);
                        $('#set_ruangan').val('');
                        $('#set_vendor').val('');
                    } else {
                        $('#form-pelayanan').hide();
                        $('#form-ruangan').hide();
                        $('#form-vendor').hide();
                        $('#temp_tipe_pelayanan').val('');
                        $('#set_pelayanan').val('');
                        $('#set_ruangan').val('');
                        $('#set_vendor').val('');
                    }
                });
            }
        }
    }

    function getListPelayananByBranchAndTipe(branch, tipe) {
        var option = "";
        PelayananAction.getListPelayananByBranchAndTipe(branch, tipe, function (response) {
            option = "<option value=''>[Select One]</option>";
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idPelayanan + "'>" + item.namaPelayanan + "</option>";
                });
            } else {
                option = option;
            }
            $('#set_pelayanan').html(option);
        });
    }

    function getListRuanganByBranch(branch) {
        var option = "";
        RuanganAction.getListRuangan(branch, function (response) {
            option = "<option value=''>[Select One]</option>";
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idRuangan + "'>" + item.namaRuangan + "</option>";
                });
            } else {
                option = option;
            }
            $('#set_ruangan').html(option);
        });
    }

    function getListVendorByBranch() {
        var option = "";
        VendorAction.getListVendor(function (response) {
            option = "<option value=''>[Select One]</option>";
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idVendor + "'>" + item.namaVendor + "</option>";
                });
            } else {
                option = option;
            }
            $('#set_vendor').html(option);
        });
    }

    function listApotek(branch) {
        var option = "";
        CheckupAction.getListComboApotek(branch, function (response) {
            option = "<option value=''>[Select One]</option>";
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idPelayanan + "'>" + item.namaPelayanan + "</option>";
                });
            } else {
                option = option;
            }
            $('#set_pelayanan').html(option);
        });
    }

    function listPelayanan(branch) {
        var option = "";
        CheckupAction.getListComboPoli(branch, function (response) {
            option = "<option value=''>[Select One]</option>";
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idPelayanan + "'>" + item.namaPelayanan + "</option>";
                });
            } else {
                option = option;
            }
            $('#set_pelayanan').html(option);
        });
    }

    function listPelayananIgd(branch) {
        var option = "";
        CheckupAction.getListComboPelayananIgd(branch, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idPelayanan + "'>" + item.namaPelayanan + "</option>";
                });
            } else {
                option = option;
            }
            $('#set_pelayanan').html(option);
        });
    }

    function listGudangObat(branch) {
        var option = "";
        CheckupAction.getListComboGudangByBranch(branch, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.idPelayanan + "'>" + item.namaPelayanan + "</option>";
                });
            } else {
                option = option;
            }
            $('#set_pelayanan').html(option);
        });
    }

    function checkEmail(email) {
        UserAction.checkEmailAvailable(email, function (res) {
            if (res.status == "error") {
                $("#warning_email").show();
                $("#msg_email").text(res.msg);
                $('#temp_email').val('N');
            } else {
                $("#warning_email").hide();
                $('#temp_email').val('Y');
            }
        });
    }

    function checkUserid(userid) {
        UserAction.checkUserIdAvailable(userid, function (res) {
            if (res.status == "error") {
                $("#warning_userid").show();
                $("#msg_userid").text(res.msg);
                $('#temp_user_id').val('N');
            } else {
                $("#warning_userid").hide();
                $('#temp_user_id').val('Y');
            }
        });
    }

    function changePassword(id){
        if($('#'+id).is(':checked')){
            $('#form-password').show();
        }else{
            $('#form-password').hide();
        }
    }


</script>

<%@ include file="/pages/common/footer.jsp" %>
<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>