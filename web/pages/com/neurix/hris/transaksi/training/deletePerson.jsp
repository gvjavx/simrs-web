<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">

        function closeBtnAddTraining() {
            $('#view_dialog_menu').dialog('close');
        }
        function saveButtonDeleteTraining() {
            var personId = document.getElementById('personId').value;
            var personName = document.getElementById('personName').value;
            if (personId!='' && personName!='') {
                dwr.engine.setAsync(false);
                TrainingAction.saveAddPersonDelete(personId, function (response) {
                    if (response == '00') {
                        $('#view_dialog_menu').dialog('close');
                        document.training.action='initEdit_training.action';
                        document.training.submit();
                    } else {
                        $('#info_dialog_add_lahan').dialog('open');
                    }
                });
            } else {
                var msg = "";
                if (personId == '') {
                    msg = 'Field <strong>Person Id</strong> is required.' + '<br/>';
                }
                document.getElementById('errorValidationMessageAddTraining').innerHTML = msg;
                $.publish('showErrorValidationDialogAddTraining');
            }
        }
    </script>

</head>

<body bgcolor="#FFFFFF">
<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="addBiayaForm" method="post" theme="simple" namespace="/training" action="addPersonAdd_training" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <s:hidden id="unit" name="training.unitId"/>
                <legend align="left">Delete Person</legend>
                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td>
                            <label class="control-label"><small>NIP :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="personId" cssClass="form-control" name="trainingPerson.personId" required="false" disabled="false" readonly="true"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Nama Pegawai :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="personName" cssClass="form-control" name="trainingPerson.personName" required="false" readonly="true"/>
                                <s:hidden id="project" name="trainingPerson.project"/>
                            </table>
                        </td>
                    </tr>
                </table>
                <br>
                <br>
                <div id="actions" class="form-actions">
                    <table>
                        <tr>
                            <td></td>
                            <td>
                                <div id="crud">
                                    <table>
                                        <tr>
                                            <td>
                                                <label class="control-label"></label>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td align="center">

                                                <sj:dialog id="info_dialog_add_lahan" openTopics="showInfoDialogAddTraining" modal="true" resizable="false"
                                                           position="center" height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                           buttons="{
                                                                'OK':function() { okFailureButtonAddTraining(); }
                                                                }"
                                                >
                                                    <img id="iconinfo" border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error">
                                                    Found failure when saving, please try again or call your admin.
                                                </sj:dialog>

                                                <sj:dialog id="error_validation_dialog_add_lahan" openTopics="showErrorValidationDialogAddTraining" modal="true" resizable="false"
                                                           position="center" height="280" width="500" autoOpen="false" title="Warning"
                                                           buttons="{
                                                                    'OK':function() { $('#error_validation_dialog_add_lahan').dialog('close'); }
                                                                }"
                                                >
                                                    <div class="alert alert-error fade in">
                                                        <label class="control-label" align="left">
                                                            <img id="iconerror" border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                            <br/>
                                                            <center><div id="errorValidationMessageAddTraining"></div></center>
                                                        </label>
                                                    </div>
                                                </sj:dialog>

                                                <button type="button" id="deletebtn" class="btn btn-primary" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="saveButtonDeleteTraining();">
                                                    <i class="icon-ok-circle icon-white"/> Delete
                                                </button>

                                            </td>
                                            <td>

                                                <button type="button" id="cancelbtn" class="btn" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="closeBtnAddTraining();">
                                                    <i class="icon-remove-circle"/> Cancel
                                                </button>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </s:form>
        </td>
    </tr>
</table>
</body>
</html>