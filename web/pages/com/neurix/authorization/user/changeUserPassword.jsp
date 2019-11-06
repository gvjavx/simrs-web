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

            if (document.getElementById("username").value != '' &&
                    document.getElementById("password").value != '' &&
                    document.getElementById("confirmPassword").value != '' &&
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
                if (document.getElementById("username").value == '') {
                    msg = msg + 'Field <strong>User Name</strong> is required.' + '<br/>';
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
            Change New Password
            <small>e-HEALTH</small>
        </h1>
    </section>


    <!-- Main content -->
    <section class="content">

        <!-- Your Page Content Here -->
        <div class="row">
            <div class="col-md-12">
                <div class="box box-primary">

                        <s:hidden name="addOrEdit"/>
                        <s:hidden id="add" name="add"/>
                        <s:hidden name="delete"/>

                        <div class="box-body">
                            <div class="form-group">
                                <s:form id="changeUserPasswordForm" enctype="multipart/form-data" method="post"
                                        namespace="/admin/user" action="saveNewPassword_user" cssClass="well form-horizontal">
                                    <table align="center">
                                        <tr>
                                            <td>
                                                <label>User Id </label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <s:textfield id="userId" name="users.userId" required="true" readonly="true" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label>User Name</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <s:textfield id="username" name="users.username" required="true" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label>New Password</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <s:password id="password" name="users.password" required="true" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label>Confir. New Password</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <s:password id="confirmPassword" name="users.confirmPassword" required="true" cssClass="form-control"
                                                                 cssStyle="margin-top: -30px; margin-left: 20px"/>
                                            </td>
                                        </tr>
                                    </table>
                                    <br>

                                    <table align="center">
                                        <tr>
                                            <div id="crud">

                                                <td>
                                                    <table>

                                                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="changeUserPasswordForm" id="save" name="save"
                                                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                                                            <i class="fa fa-check"></i>
                                                            Save
                                                        </sj:submit>

                                                        <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                                                   resizable="false"
                                                                   height="250" width="600" autoOpen="false" title="Saving ...">
                                                            Please don't close this window, server is processing your request ...
                                                            </br>
                                                            </br>
                                                            </br>
                                                            <img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">
                                                        </sj:dialog>

                                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                                   buttons="{
                                                                               'OK':function() { $('#info_dialog').dialog('close'); }
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
                                        </tr>
                                    </table>
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
</script>


