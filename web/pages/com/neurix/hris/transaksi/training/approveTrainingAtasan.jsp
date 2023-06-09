<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">

        $.subscribe('beforeProcessSave', function (event, data) {
            /*var kodeAlat = $("#kodeAlat").val();
             var namaAlat = $("#namaAlat").val();*/

            var medicalRecordId = '<s:property value="trainingPerson.trainingPersonId"/>';


            if (medicalRecordId != '' ) {

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
                if (medicalRecordId =='') {
                    msg = 'Field <strong>Training Person Id</strong> is required.' + '<br/>';
                }


                document.getElementById('errorValidationMessage').innerHTML = msg;
                document.getElementById("errorValidationMessage").style.color = "red";

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

        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
            //window.location.reload(true);
        };

        function callSearch() {
            $('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            window.location.reload(true);
        };

        function changeTipe(){
            var ck = document.getElementById("check");
            if (ck.checked == true){
                document.getElementById("flagApprove").value = "N";
            } else {
                document.getElementById("flagApprove").value = "";
            }
        }




    </script>

</head>

<body bgcolor="#FFFFFF">



<s:form id="approveForm" method="post" theme="simple" namespace="/training" action="saveApprove_training" cssClass="well form-horizontal">

    <s:hidden name="addOrEdit"/>
    <s:hidden name="delete"/>

    <legend align="left">Training</legend>


    <table>
        <tr>
            <td width="10%" align="center">
                <%@ include file="/pages/common/message.jsp" %>
            </td>
        </tr>
    </table>
    <div class="form-group">
        <label class="control-label col-sm-4">Training Id :</label>
        <div class="col-sm-8">
            <s:textfield id="medicalRecordId" name="trainingPerson.trainingId" cssClass="form-control"  readonly="true"/>
            <s:hidden id="trainingPersonId" name="trainingPerson.trainingPersonId"/>
            <s:hidden name="trainingPerson.personId"/>
            <s:hidden name="trainingPerson.divisiId"/>
            <s:hidden name="trainingPerson.approvalId"/>
            <s:hidden name="trainingPerson.approvalName"/>
            <s:hidden name="trainingPerson.approvalDate"/>
            <s:hidden name="trainingPerson.approvalFlag"/>
            <s:hidden name="trainingPerson.notApprovalNote"/>
            <s:hidden name="trainingPerson.approvalBosId"/>
            <s:hidden name="trainingPerson.createdDate"/>
            <s:hidden name="trainingPerson.createdWho"/>
            <s:hidden name="trainingPerson.lastUpdate"/>
            <s:hidden name="trainingPerson.lastUpdateWho"/>
            <s:hidden name="trainingPerson.flag"/>
            <s:hidden name="trainingPerson.action"/>
            <s:hidden name="trainingPerson.approvalSdmDate"/>
            <s:hidden name="trainingPerson.trainingStartdate"/>
            <s:hidden name="trainingPerson.trainingEndDate"/>
            <s:hidden name="trainingPerson.approvalSdm"/>
            <s:hidden id="flagApprove" name="trainingPerson.flagApprove"/>

        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Nama Training :</label>
        <div class="col-sm-8">
            <s:textfield id="nip" name="trainingPerson.trainingName" cssClass="form-control" readonly="true"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">Nama :</label>
        <div class="col-sm-8">
            <s:textfield id="namaBerobat" name="trainingPerson.personName" cssClass="form-control"  readonly="true"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">Tanggal Mulai :</label>
        <div class="col-sm-8">
            <s:textfield id="tanggalBerobat" name="trainingPerson.stTrainingStartdate" cssClass="form-control" readonly="true"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">Tanggal Selesai :</label>
        <div class="col-sm-8">
            <s:textfield id="tipeBerobatName" name="trainingPerson.stTrainingEndDate" cssClass="form-control"  readonly="true"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-8">
        </div>
    </div>
    <br>
    <div class="form-group">
        <div class="col-md-12 col-sm-12">
            <center>
                <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="approveForm" id="save" name="save"
                           onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                           onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                    <i class="icon-ok-sign icon-white"></i>
                    Save
                </sj:submit>
                <button type="button" id="cancel" class="btn btn-default" onclick="cancelBtn();">
                    <i class="icon-remove-circle"/> Cancel
                </button>
            </center>

        </div>
    </div>


    <div id="actions" class="form-actions">
        <table>
            <tr>
                <td></td>
                <td>
                    <div id="crud">
                        <table>
                            <sj:dialog id="waiting_dialog" openTopics="showDialog" closeTopics="closeDialog" modal="true"
                                       resizable="false"
                                       height="350" width="600" autoOpen="false" title="Saving ...">
                                Please don't close this window, server is processing your request ...
                                </br>
                                </br>
                                </br>
                                <center>
                                    <img border="0" src="<s:url value="/pages/images/indicator-write.gif"/>" name="image_indicator_write">
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
                    </div>
                </td>
            </tr>
        </table>
    </div>
</s:form>

</body>
</html>

