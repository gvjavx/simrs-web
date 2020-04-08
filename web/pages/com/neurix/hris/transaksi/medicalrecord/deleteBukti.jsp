<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">

        function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            document.saveMedicalRecord.action='initAdd_medicalrecord.action';
            document.saveMedicalRecord.submit();
        };
        $.subscribe('beforeProcessDelete', function (event, data) {
            if (confirm('Do you want to delete this record ?')) {
                event.originalEvent.options.submit = true;
                $.publish('showDialogDelete');

            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
            }
        });


        $.subscribe('successDialog', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialogDelete');
            }
        });

        $.subscribe('errorDialogDelete', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialogDelete');
        });

        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };


    </script>

</head>

<body bgcolor="#FFFFFF">

<s:form id="deleteBuktiForm" method="post" theme="simple" namespace="/medicalrecord" action="saveDeleteBukti_medicalrecord" cssClass="well form-horizontal" enctype="multipart/form-data">

    <s:hidden name="addOrEdit"/>
    <s:hidden name="delete"/>



    <legend align="left">Delete Bukti Pengobatan</legend>


    <table>
        <tr>
            <td width="10%" align="center">
                <%@ include file="/pages/common/message.jsp" %>
            </td>
        </tr>
    </table>

    <%--<div class="form-group">--%>
    <%--<label class="control-label col-sm-2" for="alat.kodeAlat">Kode Alat :</label>--%>
    <%--<div class="col-sm-8">--%>
    <%--<s:textfield cssClass="form-control" id="kodeAlat" name="alat.kodeAlat" readonly="true" required="false" />--%>
    <%--</div>--%>
    <%--</div>--%>
    <div class="form-group">
        <label class="control-label col-sm-2">Bukti id :</label>
        <div class="col-sm-8" align="left">
            <s:textfield id="buktiId" cssClass="form-control" name="buktiPengobatan.buktiId" required="false" disabled="false" readonly="true"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">Pengobatan id :</label>
        <div class="col-sm-8" align="left">
            <s:textfield id="pengobatanId" cssClass="form-control" name="buktiPengobatan.pengobatanId" required="false" disabled="false" readonly="true"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">File Name :</label>
        <div class="col-sm-8" align="left">
            <s:textfield id="fileName" cssClass="form-control" name="buktiPengobatan.fileName" required="false" disabled="false" readonly="true"/>

        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">Keterangan :</label>
        <div class="col-sm-8" align="left">
            <s:textfield id="keterangan" cssClass="form-control" name="buktiPengobatan.keterangan" required="false" disabled="false" readonly="true"/>
        </div>
    </div>



    <br>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <sj:submit targets="crusd" type="button" cssClass="btn btn-primary" formIds="deleteBuktiForm" id="savedelete" name="savedelete"
                       onBeforeTopics="beforeProcessDelete" onCompleteTopics="closeDialog,successDialog"
                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                <i class="icon-ok-sign icon-white"></i>
                Delete
            </sj:submit>
            <button type="button" id="cancel" class="btn btn-default" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelBtn();">
                <i class="icon-remove-circle"/> Cancel
            </button>
        </div>
    </div>


    <div id="actions" class="form-actions">
        <table>
            <tr>
                <div id="crusd">
                    <td>
                        <table>
                            <sj:dialog id="waiting_dialog" openTopics="showDialogDelete" closeTopics="closeDialog" modal="true"
                                       resizable="false"
                                       height="350" width="600" autoOpen="false" title="Saving ...">
                                Please don't close this window, server is processing your request ...
                                </br>
                                </br>
                                </br>
                                <center>
                                    <img border="0" src="<s:url value="/pages/images/loading11.gif"/>" name="image_indicator_write">
                                </center>
                            </sj:dialog>

                            <sj:dialog id="info_dialog_delete" openTopics="showInfoDialogDelete" modal="true" resizable="false"
                                       height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                       buttons="{
                                                              'OK':function() {
                                                                      callSearch2();
                                                                   }
                                                            }"
                            >
                                <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                Record has been saved successfully.
                            </sj:dialog>

                            <sj:dialog id="error_dialog_delete" openTopics="showErrorDialogDelete" modal="true" resizable="false"
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

                            <sj:dialog id="error_validation_dialog_delete" openTopics="showErrorValidationDialogDelete" modal="true" resizable="false"
                                       height="280" width="500" autoOpen="false" title="Warning"
                                       buttons="{
                                                                        'OK':function() { $('#error_validation_dialog_delete').dialog('close'); }
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

