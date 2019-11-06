<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>--%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%--<link href="<s:url value="/pages/bootstrap/css/bootstrap.css"/>" rel="stylesheet" media="screen">--%>
    <%--<script type='text/javascript' src='<s:url value="/dwr/interface/RoleFuncAction.js"/>'></script>--%>
        <script type='text/javascript' src='<s:url value="/pages/plugins/datepicker/bootstrap-datepicker.js"/>'></script>

        <style>
            .ui-datepicker
            {
                z-index: 1032 !important;
            }
        </style>
        <%--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.js"></script>--%>
    <%--<%@ include file="/pages/common/header.jsp" %>--%>
        <script type="text/javascript">


        $.subscribe('beforeProcessSaveStatus', function (event, data) {
            /*var kodeAlat = $("#kodeAlat").val();
            var namaAlat = $("#namaAlat").val();*/

            var groupShiftName = document.getElementById('tanggalAwal').value;


            if (groupShiftName != '' ) {

                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialogSave');

                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }

            } else {

                event.originalEvent.options.submit = false;
                var msg = "";
                if (groupShiftName =='') {
                    msg = 'Field <strong>Tanggal Awal</strong> is required.' + '<br/>';
                }


                document.getElementById('errorValidationMessage').innerHTML = msg;
                document.getElementById("errorValidationMessage").style.color = "white";

                $.publish('showErrorValidationDialog');

            }
        });

        $.subscribe('beforeProcessDelete', function (event, data) {
            if (confirm('Do you want to delete this record ?')) {
                event.originalEvent.options.submit = true;
                $.publish('showDialogSave');

            } else {
                // Cancel Submit comes with 1.8.0
                event.originalEvent.options.submit = false;
            }
        });


        $.subscribe('successDialogSave', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialogSave');
            }
        });

        $.subscribe('errorDialogSave', function (event, data) {

//            alert(event.originalEvent.request.getResponseHeader('message'));
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialogSave');
        });

        function cancelBtnSave() {
//            alert("klik");
            $('#view_dialog_menu_status_open').dialog('close');
            //window.location.reload(true);
        };

        function callSearch() {
//            alert("klik");
            $('#waiting_dialog_save').dialog('close');
//            $('#info_dialog_save').dialog('close');
            $('#view_dialog_menu_status_open').dialog('close');
            window.location.reload(true);
        };

        $(document).ready(function() {
            $('#tanggalAwal').datepicker({
                dateFormat: 'dd-mm-yy'
//                dateFormat: 'yy-mm-dd'
            });
            $('#tanggalAkhir').datepicker({
                dateFormat: 'dd-mm-yy'
//                dateFormat: 'yy-mm-dd'
            });
        });
    </script>
</head>

<%--<body>--%>

<s:form id="editStatusForm" method="post" theme="simple" namespace="/admin/branch" action="saveStatusEdit_branch" cssClass="well form-horizontal">

    <%--<s:hidden name="addOrEdit"/>--%>
    <%--<s:hidden name="delete"/>--%>
    <legend align="left">Edit Status Pabrik</legend>


    <table>
        <tr>
            <td width="10%" align="center">
                <%@ include file="/pages/common/message.jsp" %>
            </td>
        </tr>
    </table>

    <div class="form-group">
        <label class="control-label col-sm-2">BranchId :</label>
        <div class="col-sm-8">
            <s:textfield cssClass="form-control" id="groupShiftId" name="branch.branchId" readonly="true" required="true" />
            <s:hidden id="createdDate" name="branch.createdDate" />
            <s:hidden id="createdWho" name="branch.createdWho" />
            <s:hidden id="enabled" name="branch.enabled" />
            <s:hidden id="statusPabrikBefore" name="branch.statusPabrikBefore" />
            <s:hidden id="tanggalAkhirBefore" name="branch.tanggalAkhirBefore" />
            <s:hidden id="createDateStatus" name="branch.createDateStatus" />
            <s:hidden id="createdWhoStatus" name="branch.createdWhoStatus" />
            <s:hidden id="statusId" name="branch.statusId" />
            <s:hidden id="tanggalAwalBefore" name="branch.tanggalAwalBefore" />
            <s:hidden id="branchAddress" name="branch.branchAddress" />
                <%--<input type="text" class="form-control" name="kodeAlat" id="alat.kodeAlat" readonly >--%>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">Branch Name:</label>
        <div class="col-sm-8">
            <s:textfield id="branchName" cssClass="form-control" name="branch.branchName" required="false" disabled="false"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">Status Pabrik:</label>
        <div class="col-sm-8">
            <s:select cssClass="form-control" list="#{'DMG':'DMG', 'LMG':'LMG'}" id="statusGiling" name="branch.statusPabrik"
                      headerKey="" headerValue="[Select one]"/>
        </div>
    </div>

    <%--<div class="form-group">--%>
        <%--<label class="control-label col-sm-2">Tanggal Awal:</label>--%>
        <%--<div class="col-sm-8">--%>
            <%--<sj:datepicker id="tanggalAwal" name="branch.tanggalAwal" displayFormat="dd-mm-yy"--%>
                           <%--showAnim="fadeIn" showOptions="{direction: 'up' }" duration="slow" changeMonth="true" changeYear="true" cssClass="input-small" />--%>
        <%--</div>--%>
    <%--</div>--%>

    <%--<div class="form-group">--%>
        <%--<label class="control-label col-sm-2">Tanggal Akhir:</label>--%>
        <%--<div class="col-sm-8">--%>
            <%--<sj:datepicker id="tanggalAkhir" name="branch.tanggalAkhir" displayFormat="dd-mm-yy"--%>
                           <%--showAnim="fadeIn" showOptions="{direction: 'up' }" duration="slow" changeMonth="true" changeYear="true" cssClass="input-small" />--%>
        <%--</div>--%>
    <%--</div>--%>

    <div class="form-group">
        <label class="control-label col-sm-2">Tanggal Awal :</label>
        <div class="col-sm-8">
            <div class="input-group date">
                <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                </div>
                <%--<input id="tanggalAwal" name="branch.tanggalAwal" value="<s:property value='branch.tanggalAwal'/>" type="date">--%>
                <s:textfield id="tanggalAwal" name="branch.tanggalAwal" cssClass="form-control pull-right"
                             required="false"  cssStyle=""/>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">Tanggal Akhir :</label>
        <div class="col-sm-8">
            <div class="input-group date">
                <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                </div>
                <%--<input id="tanggalAkhir" name="branch.tanggalAkhir" value="<s:property value='branch.tanggalAwal'/>" type="date">--%>
                <s:textfield id="tanggalAkhir" name="branch.tanggalAkhir" cssClass="form-control pull-right"
                             required="false"  cssStyle=""/>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">Flag :</label>
        <div class="col-sm-8" align="left">
            <s:select cssClass="form-control" list="#{'Y':'Active', 'N':'NonActive'}" id="flag" name="branch.flag"
                      headerKey="" headerValue="[Select one]"/>
        </div>
    </div>

    <br>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
                <%--<button type="submit" class="btn btn-default">Submit</button>--%>
            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="editStatusForm" id="save" name="save"
                       onBeforeTopics="beforeProcessSaveStatus" onCompleteTopics="closeDialogSave,successDialogSave"
                       onSuccessTopics="successDialogSave" onErrorTopics="errorDialogSave" >
                <i class="fa fa-check"></i>
                Save
            </sj:submit>
            <button type="button" id="cancel" class="btn btn-default" onclick="cancelBtnSave();">
                <i class="fa fa-close"/> Cancel
            </button>
        </div>
    </div>


    <div id="actions" class="form-actions">
        <table>
            <tr>
                <div id="crud">
                    <td>
                        <table>
                            <sj:dialog id="waiting_dialog_save" openTopics="showDialogSave" closeTopics="closeDialogSave" modal="true"
                                       resizable="false"
                                       height="350" width="600" autoOpen="false" title="Saving ...">
                                Please don't close this window, server is processing your request ...
                                </br>
                                </br>
                                </br>
                                <center>
                                    <img border="0" src="<s:url value="/pages/images/loading4.gif"/>" name="image_indicator_write">
                                </center>
                            </sj:dialog>

                            <sj:dialog id="info_dialog_save" openTopics="showInfoDialogSave" modal="true" resizable="false"
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

                            <sj:dialog id="error_dialog_save" openTopics="showErrorDialogSave" modal="true" resizable="false"
                                       height="250" width="600" autoOpen="false" title="Error Dialog"
                                       buttons="{
                                                                        'OK':function() { $('#error_dialog_save').dialog('close'); }
                                                                    }"
                            >
                                <div class="alert alert-error fade in">
                                    <label class="control-label" align="left">
                                        <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage"></p>
                                    </label>
                                </div>
                            </sj:dialog>

                            <sj:dialog id="error_validation_dialog_save" openTopics="showErrorValidationDialog" modal="true" resizable="false"
                                       height="280" width="500" autoOpen="false" title="Warning"
                                       buttons="{
                                                                        'OK':function() { $('#error_validation_dialog_save').dialog('close'); }
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
<%--<%@ include file="/pages/common/lastScript.jsp" %>--%>
<%--</body>--%>
</html>

