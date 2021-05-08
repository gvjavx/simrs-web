<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%--<link href="<s:url value="/pages/bootstrap/css/bootstrap.css"/>" rel="stylesheet" media="screen">--%>
    <%--<script type='text/javascript' src='<s:url value="/dwr/interface/RoleFuncAction.js"/>'></script>--%>

    <script type="text/javascript">

        $.subscribe('beforeProcessSave', function (event, data) {
            /*var kodeAlat = $("#kodeAlat").val();
            var namaAlat = $("#namaAlat").val();*/

            var tipeLiburId = '<s:property value="tipePegawai.tipePegawaiId"/>';
            var tipeLiburName = '<s:property value="tipePegawai.tipePegawaiName"/>';

            console.log(tipeLiburName);
            if (tipeLiburName != '' ) {

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
                if (tipeLiburName =='') {
                    msg = 'Field <strong>Tipe Pegawai name</strong> is required.' + '<br/>';
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

    </script>

</head>

<s:form id="editForm" method="post" theme="simple" namespace="/tipepegawai" action="saveEdit_tipepegawai" cssClass="well form-horizontal">

    <s:hidden name="addOrEdit"/>
    <s:hidden name="delete"/>



    <legend align="left">Edit Status Pegawai</legend>


    <table>
        <tr>
            <td width="10%" align="center">
                <%@ include file="/pages/common/message.jsp" %>
            </td>
        </tr>
    </table>

    <div class="form-group">
        <label class="control-label col-sm-2">Status Pegawai ID :</label>
        <div class="col-sm-8">
            <s:textfield cssClass="form-control" id="tipePegawaiId" name="tipePegawai.tipePegawaiId" readonly="true" required="true" />
            <s:hidden id="createdDate" name="tipePegawai.createdDate" />
            <s:hidden id="createdWho" name="tipePegawai.createdWho" />
                <%--<input type="text" class="form-control" name="kodeAlat" id="alat.kodeAlat" readonly >--%>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">Status Pegawai Name:</label>
        <div class="col-sm-8">
            <s:textfield id="tipePegawaiName" cssClass="form-control" name="tipePegawai.tipePegawaiName" required="false" disabled="false"/>
        </div>
    </div>

    <br>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
                <%--<button type="submit" class="btn btn-default">Submit</button>--%>
            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="editForm" id="save" name="save"
                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                       onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                <i class="icon-ok-sign icon-white"></i>
                Save
            </sj:submit>
            <button type="button" id="cancel" class="btn btn-default" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelBtn();">
                <i class="icon-remove-circle"/> Cancel
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
                    </td>
                </div>
            </tr>
        </table>
    </div>
</s:form>

</body>
</html>

