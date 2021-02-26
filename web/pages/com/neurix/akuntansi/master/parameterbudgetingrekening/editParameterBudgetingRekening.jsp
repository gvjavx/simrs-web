<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%--<script type='text/javascript' src='<s:url value="/dwr/interface/PayrollSkalaGajiAction.js"/>'></script>--%>
    <script type="text/javascript">
        $(document).ready(function(){

        });
        function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            // $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
           // window.location.reload(true);
            document.SearchParameterBudgetingRekeningForm.action = "search_parameterbudgetingrekening.action"
            document.SearchParameterBudgetingRekeningForm.submit();
        };

        $.subscribe('beforeProcessSaveEdit', function (event, data) {
            var parameterbudgetingrekeningEdit = document.getElementById("parameterbudgetingrekeningEdit").value;
            var jenisrekneningedit = document.getElementById("jenisrekneningedit").value;

            console.log(parameterbudgetingrekeningEdit);

            if ( parameterbudgetingrekeningEdit != '' && jenisrekneningedit != '') {
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

                if (parameterbudgetingrekeningEdit == '') {
                    msg += 'Field <strong>parameter budgeting rekening </strong> is required.' + '<br/>';
                }
                if (jenisrekneningedit == '') {
                    msg += 'Field <strong>jenis rekening </strong> is required.' + '<br/>';
                }


                document.getElementById('errorValidationMessageEdit').innerHTML = msg;

                $.publish('showErrorValidationDialogEdit');
            }
        });

        $.subscribe('successDialogEdit', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialog');
            }
        });

        $.subscribe('errorDialogEdit', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessageEdit').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialogEdit');
        });

        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };


    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="editParameterBudgetingRekeningForm" method="post" theme="simple"
                    namespace="/parameterbudgetingrekening" action="saveEdit_parameterbudgetingrekening"
                    cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>

                <legend align="left">Edit Rekening Budgeting </legend>

                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>

                <table >
                    <tr>
                        <td width="40%">
                            <label class="control-label"><small>Id Rekening Budgeting :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="idparam" name="parameterBudgetingRekening.id"
                                             required="true" readonly="true" cssClass="form-control"/>

                                <%--<s:hidden id="idParameterBudgetingRekeningdelete" name="parameterBudgetingRekening.idParameterBudgetingRekening" />--%>
                            </table>
                        </td>
                    </tr>


                    <tr>
                        <td >
                            <label class="control-label"><small>Nama Rekening Budgeting :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="parameterbudgetingrekeningEdit" name="parameterBudgetingRekening.nama"
                                             cssStyle="margin-top: 7px"
                                             cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>


                    <tr>
                        <td>
                            <label class="control-label"><small>Jenis Rekening :</small></label>
                        </td>
                        <td >
                            <table>
                                <s:action id="rekening" namespace="/kodeRekening"
                                          name="initComboKodeRekening_kodeRekening" />
                                <s:select cssStyle="margin-top: 7px; width: 100%" list="#rekening.listOfComboKodeRekening"
                                          id="jenisrekneningedit" name="parameterBudgetingRekening.rekeningId"
                                          listKey="rekeningId" listValue="namaKodeRekening" headerKey="" headerValue="[Select one]"
                                          cssClass="form-control select2" onchange="KODE()" />
                            </table>
                        </td>
                    </tr>
                </table>



                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="editParameterBudgetingRekeningForm" id="save" name="save"
                                   onBeforeTopics="beforeProcessSaveEdit" onCompleteTopics="closeDialog,successDialogEdit"
                                   onSuccessTopics="successDialogEdit" onErrorTopics="errorDialogEdit" >
                            <i class="fa fa-check"></i>
                            Save
                        </sj:submit>
                        <button type="button" id="cancel" class="btn btn-danger" onclick="cancelBtn();">
                            <i class="fa fa-refresh"/> Cancel
                        </button>
                    </div>
                </div>


                <div id="actions" class="form-actions">
                    <table>
                        <tr>
                            <div id="crud">
                                <td>
                                    <table>
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

                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                              'OK':function() {
                                                                    //$(this).dialog('close');
                                                                      callSearch2();
                                                                   }
                                                            }"
                                        >
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                            Record has been saved successfully.
                                        </sj:dialog>

                                        <sj:dialog id="error_dialog" openTopics="showErrorDialogEdit" modal="true" resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close');}
                                                                    }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                         name="icon_error"> System Found : <p id="errorMessageEdit"></p>
                                                </label>
                                            </div>
                                        </sj:dialog>

                                        <sj:dialog id="error_validation_dialog_edit" openTopics="showErrorValidationDialogEdit"
                                                   modal="true" resizable="false"
                                                   height="280" width="500" autoOpen="false" title="Warning"
                                                   buttons="{
                                                                        'OK':function() { $('#error_validation_dialog_edit').dialog('close'); window.location.reload(true)}
                                                                    }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>"
                                                         name="icon_error"> Please check this field :
                                                    <br/>
                                                    <center><div id="errorValidationMessageEdit"></div></center>
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
        </td>
    </tr>
</table>
</body>
</html>
<script>

    function KODE(){
        var label = $("#koderekedit option:selected").text();
        $("#parameterbudgetingrekeningEdit").val(label);
    }
</script>
