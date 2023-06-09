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
        .jarak{
            margin-top: 7px;
        }
    </style>
    <script type='text/javascript'>



        $.subscribe('beforeProcessSave', function (event, data) {

            var errEmail = "";
            var iduser = $("#userid").val();
            var email = $("#email").val();
            var name = $("#name").val();
            var pass = $("#password").val();
            var conpass = $("#confirmPassword").val();
            var roleid = $("#roleid").val();
            var areaid = $("#areaid").val();
            var branchid = $("#branchid").val();

            var msg = "";

            // Sigit, 2020-11-26
            // check apakah ada error email, email tidak boleh sama dengan userId
            // dan password harus sama dengan confirmasi passwordnya
            if (errEmail == "" && email != iduser && pass == conpass) {

                if (email != "" &&
                        iduser != "" &&
                        name != "" &&
                        pass != "" &&
                        conpass != "" &&
                        roleid != "" &&
                        areaid != "" &&
                        branchid != "")
                {

                    // Sigit, 2020-11-26
                    // jika input  diisi semua
                    if (confirm('Do you want to save this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');

                    } else {
                        // Cancel Submit comes with 1.8.0
                        event.originalEvent.options.submit = false;
                    }

                } else {

                    // Sigit, 2020-11-26
                    // validasi error jika ada yng tidak diiisi

                    event.originalEvent.options.submit = false;

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

                    if (document.getElementById("areaid").value == '') {
                        msg = msg + 'Field <strong>Area Id</strong> is required.' + '<br/>';
                    }

                    if (document.getElementById("branchid").value == '') {
                        msg = msg + 'Field <strong>Unit</strong> is required.' + '<br/>';
                    }

                    if (document.getElementById("roleid").value == '') {
                        msg = msg + 'Field <strong>Role Id</strong> is required.' + '<br/>';
                    }

                }

            } else {

                // Sigit, 2020-11-26
                // validasi error jika kondisi tidak terpenuhi

                event.originalEvent.options.submit = false;

                if (errEmail == "Y") {
                    msg = msg + '<strong>Email</strong> is not available.' + '<br/>';
                }

                if (email == iduser) {
                    msg = msg + '<strong>Email</strong> must be different with <strong>User ID</strong>.' + '<br/>';
                }

                if (pass != conpass) {
                    msg = msg + '<strong>Password</strong> must be same with <strong>Confirmation Password</strong>.' + '<br/>';
                }
                document.getElementById('errorValidationMessage').innerHTML = msg;
                $.publish('showErrorValidationDialog');

            }
        });

        $.subscribe('successDialog', function (event, data) {
            console.log(event.originalEvent.request);
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');

            }
        });

        $.subscribe('errorDialog', function (event, data) {
            console.log(event.originalEvent.request);
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
    <script type='text/javascript' src='<s:url value="/dwr/interface/RoleAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/PelayananAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/RuanganAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/VendorAction.js"/>'></script>
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
                                        <label class="control-label col-sm-4" for="users.userId">User Id :</label>
                                        <div class="col-sm-4">
                                            <s:textfield id="userid" name="users.userId" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>

                                    <div class="form-group jarak">
                                        <label class="control-label col-sm-4" for="users.username">Username :</label>
                                        <div class="col-sm-4">
                                            <s:textfield id="name" name="users.username" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group jarak">
                                        <label class="control-label col-sm-4" for="users.password">Password :</label>
                                        <div class="col-sm-4">
                                            <s:password id="password" name="users.password" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group jarak">
                                        <label class="control-label col-sm-4" for="users.password">Confirm Password :</label>
                                        <div class="col-sm-4">
                                            <s:password id="confirmPassword" name="users.confirmPassword" required="true" cssClass="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4" for="users.areaId">Area :</label>
                                        <div class="col-sm-4">
                                            <s:action id="comboArea" namespace="/admin/user" name="initComboArea_user"/>
                                            <s:select list="#comboArea.listOfComboAreas" id="areaid" name="users.areaId"
                                                      onchange="listUnit(this.value)"
                                                      listKey="areaId" listValue="areaName" headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control select2"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">Unit:</label>
                                        <div class="col-sm-4">
                                            <select id="branchid" style="width: 100%" class="form-control select2" name="users.branchId">
                                                <option value="">[Select One]</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4" for="users.divisiId">Bidang :</label>
                                        <div class="col-sm-4">
                                            <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                            <s:select list="#comboDivisi.listComboDepartment" id="users.divisiId" name="users.divisiId" onchange="listPosisi()"
                                                      listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select One]" cssClass="form-control select2" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4" >Jabatan :</label>
                                        <div class="col-sm-4">
                                            <%--<s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>--%>
                                            <select id="positionId" class="form-control select2" name="users.positionId">
                                                <option value="">[Select One]</option>
                                            </select>
                                            <%--<s:select list="#comboPosition.listOfComboPositions" id="positionid" name="users.positionId"
                                                      listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control" />--%>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-4" for="users.roleId">Role :</label>
                                        <div class="col-sm-4">
                                            <s:action id="comboRole" namespace="/admin/user" name="initComboRole_user"/>
                                            <s:select list="#comboRole.listOfComboRoles" id="roleid" name="users.roleId"
                                                      onchange="showPelayanan(this.value)"
                                                      listKey="stRoleId" listValue="roleName" headerKey="" headerValue="[Select one]"
                                                      cssClass="form-control select2"/>
                                        </div>
                                    </div>

                                    <div class="form-group" style="display: none" id="form-pelayanan">
                                        <label class="control-label col-sm-4" for="users.roleId">Pelayanan :</label>
                                        <div class="col-sm-4">
                                            <select style="width: 100%" class="form-control select2" name="users.idPelayanan" id="pelayananId">
                                                <option value="">[Select One]</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group" style="display: none" id="form-ruangan">
                                        <label class="control-label col-sm-4" for="users.roleId">Ruangan :</label>
                                        <div class="col-sm-4">
                                            <select style="width: 100%" class="form-control select2" name="users.idRuangan" id="ruanganId">
                                                <option value="">[Select One]</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group" style="display: none" id="form-vendor">
                                        <label class="control-label col-sm-4" for="users.roleId">Vendor :</label>
                                        <div class="col-sm-4">
                                            <select style="width: 100%" class="form-control select2" name="users.idVendor" id="vendorId">
                                                <option value="">[Select One]</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group jarak">
                                        <label class="control-label col-sm-4" for="users.email">Email :</label>
                                        <div class="col-sm-4">
                                            <s:textfield id="email" name="users.email" required="true" cssClass="form-control" onchange="checkEmail()" />
                                            <div class="alert alert-danger" id="err-email" style="display: none"></div>
                                        </div>
                                    </div>
                                    <div class="form-group jarak">
                                        <label class="control-label col-sm-4" for="users.roleId">Flag :</label>
                                        <div class="col-sm-4">
                                            <s:select list="#{'N':'Non Active'}" id="flag" name="users.flag"
                                                      headerKey="Y" headerValue="Active" cssClass="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group jarak">
                                        <label class="control-label col-sm-4" for="fileUpload">Upload Photo:</label>
                                        <div class="col-sm-4">
                                            <s:file id="fileUpload" name="fileUpload" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group jarak">
                                        <div style="padding-left: 140px" class="col-md-offset-3 col-sm-4">
                                            <%--<sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"--%>
                                                       <%--resizable="false"--%>
                                                       <%--height="250" width="600" autoOpen="false" title="Searching...">--%>
                                                <%--Please don't close this window, server is processing your request ...--%>
                                                <%--</br>--%>
                                                <%--</br>--%>
                                                <%--</br>--%>
                                                <%--<center>--%>
                                                    <%--<img border="0" src="<s:url value="/pages/images/loading11.gif"/>" name="image_indicator_read">--%>
                                                <%--</center>--%>
                                            <%--</sj:dialog>--%>
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
                                                <table style="display: none">
                                                        <sj:dialog id="waiting_dialog" openTopics="showDialog"
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
                                                                                         window.location.reload(true);
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

<script type='text/javascript' src='<s:url value="/dwr/interface/CheckupAction.js"/>'></script>

<script>
    window.listPosisi = function(branch, divisi){
        var branch = document.getElementById("branchid").value;
        var divisi = document.getElementById("users.divisiId").value;

        $('#positionId').empty();
        PositionAction.searchPositionBiodata(divisi, function(listdata){
            $.each(listdata, function (i, item) {
                $('#positionId').append($("<option></option>")
                        .attr("value",item.positionId)
                        .text(item.positionName));
            });
        });
    }

    function showPelayanan(role){
        var branch = $('#set_unit').val();
        if (branch == null || branch == ""){
            $('#warning_add').show().fadeOut(5000);
            $('#msg_add').text("Silahkan pilih unit terlebih dahulu...!");
            $('#set_role').val('').trigger('change');
        }else{
            RoleAction.getRoleById(role, function (res) {
                $('#form-pelayanan').hide();
                $('#form-ruangan').hide();
                $('#form-vendor').hide();
                if(res.tipePelayanan == "rawat_inap"){
                    $('#form-ruangan').show();
                    getListRuanganByBranch(branch);
                } else if(res.tipePelayanan == "pbf") {
                    $('#form-vendor').show();
                    getListVendorByBranch();
                } else if(res.tipePelayanan != "" && res.tipePelayanan != null){
                    $('#form-pelayanan').show();
                    getListPelayananByBranchAndTipe(branch, res.tipePelayanan);
                }else{
                    $('#form-pelayanan').hide();
                    $('#form-ruangan').hide();
                    $('#form-vendor').hide();
                }
            });
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

    function listApotek(branch){
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

    function listPelayanan(branch){
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

    function listPelayananIgd(branch){
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

    function listGudangObat(branch){
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

    function checkEmail(emal) {
        UserAction.checkEmailAvailable(email, function (res) {
            if (res.status == "error"){
                $("#err-email").show();
                $("#err-email").html(res.msg);
                errEmail = "Y";
            } else {
                $("#err-email").hide();
                errEmail = "";
            }
        });
    }

    function eraseInput(id) {
        if (id == "email"){
            $("#err-email").hide();
        }
    }

    function listUnit(area){
        var option = '<option value="">[Select One]</option>';
        BranchAction.getComboBranchByArea(area, function (response) {
            if (response.length > 0) {
                $.each(response, function (i, item) {
                    option += "<option value='" + item.branchId + "'>" + item.branchName + "</option>";
                });
                $('#branchid').html(option);
            } else {
                $('#branchid').html(option);
            }
        });
    }

</script>
<%@ include file="/pages/common/footer.jsp" %>


<%@ include file="/pages/common/lastScript.jsp" %>

</body>
</html>