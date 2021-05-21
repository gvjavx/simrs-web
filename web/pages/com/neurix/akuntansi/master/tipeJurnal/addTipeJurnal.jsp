<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <link rel="stylesheet" href="<s:url value="/pages/bootstraplte/css/addrawatpasien.css"/>">

    <script type="text/javascript">

        $(document).ready(function () {
            window.close = function () {
                //$('#waiting_dialog').dialog('close');
                $('#view_dialog_menu').dialog('close');
                $('#info_dialog').dialog('close');

                window.location.href = "<s:url action='search_tipeJurnal.action'/>";
                window.location.reload(true);
            };

            var checkOperasional = '<s:property value="tipeJurnal.isOperasional"/>'
            if (checkOperasional == 'Y') {
                $('#is_operasional').prop('checked', true);
            }
        });

        $.subscribe('beforeProcessSave', function (event, data) {
            var nameTipeJurnal = document.getElementById("tipeJurnalNameAdd").value;
            var idTipeJurnal = document.getElementById("tipeJurnalIdAdd").value;
            dwr.engine.setAsync(false);
            if (nameTipeJurnal != ''&&idTipeJurnal!='') {
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
                if (nameTipeJurnal == '') {
                    msg += 'Field <strong>Nama Tipe Jurnal</strong> is required.' + '<br/>';
                }
                if (idTipeJurnal == '') {
                    msg += 'Field <strong>Id Tipe Jurnal</strong> is required.' + '<br/>';
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
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/tipeJurnal" action="saveAdd_tipeJurnal" cssClass="well form-horizontal">
                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">Add Tipe Jurnal</legend>
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
                            <label class="control-label"><small>Id Tipe Jurnal :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="tipeJurnalIdAdd" name="tipeJurnal.tipeJurnalId" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama Tipe Jurnal :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="tipeJurnalNameAdd" name="tipeJurnal.tipeJurnalName" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Operasional :</small></label>
                        </td>
                        <td class="form-check">
                            <input type="checkbox"
                                   id="is_operasional" value="Y"
                                   name="tipeJurnal.isOperasional">
                            <label for="is_operasional"></label>
                        </td>
                    </tr>
                </table>
                <br>
                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="modifyRolefuncForm" id="save" name="save"
                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                            <i class="fa fa-check"></i>
                            Save
                        </sj:submit>
                        <button type="button" id="cancel" class="btn btn-danger" onclick="cancelBtn();">
                            <i class="fa fa-refresh"/> Cancel
                        </button>
                    </div>
                </div>

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
                                                                       close();
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
            </s:form>
        </td>
    </tr>
</table>
</body>
</html>
