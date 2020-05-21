<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%--<script type='text/javascript' src='<s:url value="/dwr/interface/PayrollSkalaGajiAction.js"/>'></script>--%>
    <script type="text/javascript">
    function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
//            window.location.reload(true);
            document.labForm.action = "search_lab.action";
            document.labForm.submit();
        };

        $.subscribe('beforeProcessSave2', function (event, data) {
            var idLab = document.getElementById("idLab2").value;
            var namaLab = document.getElementById("namaLab2").value;
            var idOperatorLab = document.getElementById("idOperatorLab2").value;
            var idDokter = document.getElementById("idDokter2").value;
            var idKategoriLab = document.getElementById("idKategoriLab2").value;
            var tarif = document.getElementById("tarif2").value;

            if (idLab != '' && namaLab != '' && idOperatorLab != '' && idDokter != '' && idKategoriLab != '' && tarif != '') {
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
                if (idLab == '') {
                    msg += 'Field <strong>Id Lab </strong> is required.' + '<br/>';
                }
                if (namaLab == '') {
                    msg += 'Field <strong>Nama Lab </strong> is required.' + '<br/>';
                }
                if (idOperatorLab == '') {
                    msg += 'Field <strong>Id Operator Lab </strong> is required.' + '<br/>';
                }
                if (idDokter == '') {
                    msg += 'Field <strong>Id Dokter </strong> is required.' + '<br/>';
                }
                if (idKategoriLab == '') {
                    msg += 'Field <strong>Id Kategori Lab </strong> is required.' + '<br/>';
                }
                if (tarif == '') {
                    msg += 'Field <strong>Tarif </strong> is required.' + '<br/>';
                }

                document.getElementById('errorValidationMessage2').innerHTML = msg;

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

        function cancelBtn() {
            $('#view_dialog_menu').dialog('close');
        };


    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="editLabForm" method="post" theme="simple" namespace="/lab" action="saveEdit_lab" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>

                <legend align="left">Edit Lab</legend>


                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>

                <table >
                    <tr>
                        <td>
                            <label class="control-label"><small>Lab. ID :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="idLab2" name="lab.idLab" required="true" readonly="true" disabled="false" cssClass="form-control"/>
                                <%--<s:hidden id="idLab2" name="lab.idLab" />--%>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama Lab :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="namaLab2" name="lab.namaLab" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Operator Lab :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboOperatorLab" namespace="/lab" name="initComboOperatorLab_lab"/>
                                <s:select list="#initComboOperatorLab.listOfComboOperatorLab" id="idOperatorLab2" name="lab.idOperatorLab"
                                          listKey="idOperatorLab" listValue="namaOperator" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Dokter :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboDokter" namespace="/lab" name="initComboDokter_lab"/>
                                <s:select list="#initComboDokter.listOfComboDokter" id="idDokter2" name="lab.idDokter"
                                          listKey="idDokter" listValue="namaDokter" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Kategori Lab :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboKategoriLab" namespace="/lab" name="initComboKategoriLab_lab"/>
                                <s:select list="#initComboKategoriLab.listOfComboKategoriLab" id="idKategoriLab2" name="lab.idKategoriLab"
                                          listKey="idKategoriLab" listValue="namaKategori" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Tarif :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="tarif2" name="lab.tarif" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                </table>

                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="editLabForm" id="save" name="save"
                                   onBeforeTopics="beforeProcessSave2" onCompleteTopics="closeDialog,successDialog"
                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
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
                                                   title="Update Data ...">
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
                                            Record has been updated successfully.
                                        </sj:dialog>

                                        <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close');  window.location.reload(true)}
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
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); window.location.reload(true)}
                                                                    }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                    <br/>
                                                    <center><div id="errorValidationMessage2"></div></center>
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


