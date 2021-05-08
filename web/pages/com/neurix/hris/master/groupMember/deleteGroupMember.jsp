<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%--<link href="<s:url value="/pages/bootstrap/css/bootstrap.css"/>" rel="stylesheet" media="screen">--%>
    <%--<script type='text/javascript' src='<s:url value="/dwr/interface/RoleFuncAction.js"/>'></script>--%>

    <script type="text/javascript">

        function callSearch() {
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var groupId = '<s:property value="group.groupId"/>';
            var groupName = '<s:property value="group.groupName"/>';


            if (groupName != '' ) {

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
                if (groupName =='') {
                    msg = 'Field <strong>Group Name</strong> is required.' + '<br/>';
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
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialog');
        });

        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };


    </script>

</head>

<body bgcolor="#FFFFFF">

<s:form id="deleteForm" method="post" theme="simple" namespace="/groupMember" action="saveDelete_groupMember" cssClass="well form-horizontal">

    <legend align="left">Delete Group Member</legend>

    <table>
        <tr>
            <td width="10%" align="center">
                <%@ include file="/pages/common/message.jsp" %>
            </td>
        </tr>
    </table>
    <center>
        <table>
            <tr>
                <td>
                    <label class="control-label"><small>Group Member Id :</small></label>
                </td>

                <td>
                    <table>
                        <s:textfield  id="groupMemberId12" name="groupMember.groupMemberId" required="true" readonly="true" cssClass="form-control"/>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label"><small>Unit :</small></label>
                </td>
                <td>
                    <table>
                        <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                        <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="unitId12" name="groupMember.branchId" required="true" disabled="true"
                                  listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label"><small>Group :</small></label>
                </td>

                <td>
                    <table>
                        <s:action id="comboGroup" namespace="/group" name="initComboGroup_group"/>
                        <s:select cssClass="form-control" list="#comboGroup.listOfComboGroup" id="groupId12" name="groupMember.groupId" required="true" disabled="true"
                                  listKey="groupId" listValue="groupName" headerKey="" headerValue="[Select one]" />
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label"><small>NIP :</small></label>
                </td>

                <td>
                    <table>
                        <s:textfield  id="nipId12" name="groupMember.nip" required="true" readonly="true" cssClass="form-control"/>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label"><small>Nama :</small></label>
                </td>
                <td>
                    <table>
                        <s:textfield  id="namaAddId" name="ijinKeluar.namaPegawai" required="false" readonly="true" cssClass="form-control"/>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label"><small>Divisi :</small></label>
                </td>
                <td>
                    <table>
                        <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                        <s:select list="#comboDivisi.listComboDepartment" id="divisiId12" name="ijinKeluar.divisiId"
                                  listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" disabled="true" />

                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label"><small>Jabatan :</small></label>
                </td>
                <td>
                    <table>
                        <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                        <s:select list="#comboPosition.listOfComboPosition" id="positionId12" name="ijinKeluar.positionId" readonly="true" disabled="true"
                                  listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label"><small>Golongan :</small></label>
                </td>
                <td>
                    <table>
                        <s:action id="comboGolongan" namespace="/golongan" name="initComboGolongan_golongan"/>
                        <s:select list="#comboGolongan.listComboGolongan" id="golonganId12" name="ijinKeluar.golonganId" disabled="true"
                                  listKey="golonganId" listValue="golonganName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />
                    </table>
                </td>
            </tr>
            <tr>
                <center>
                    <td>
                        <br>
                        <br>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="deleteForm" id="save" name="save"
                                   onBeforeTopics="beforeProcessDelete" onCompleteTopics="closeDialog,successDialog"
                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                            <i class="icon-ok-sign icon-white"></i>
                            Save
                        </sj:submit>
                        <button type="button" id="cancel" class="btn btn-default" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelBtn();">
                            <i class="icon-remove-circle"/> Cancel
                        </button>
                    </td>
                </center>
            </tr>
        </table>
    </center>


    <div id="actions" class="form-actions">
        <table>
            <tr>
                <div id="crud">
                    <td>
                        <table>
                            <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                       resizable="false"
                                       height="350" width="600" autoOpen="false" title="Saving ...">
                                Please don't close this window, server is processing your request ...
                                </br>
                                </br>
                                </br>
                                <center>
                                    <img border="0" src="<s:url value="/pages/images/indicator-trash.gif"/>" name="image_indicator_write">
                                </center>
                            </sj:dialog>

                            <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                       height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                       buttons="{
                                                              'OK':function() {
                                                                      callSearch();
                                                                   }
                                                            }"
                            >
                                <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                Record has been Deleted successfully.
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
    </div>
</s:form>
</body>
</html>

