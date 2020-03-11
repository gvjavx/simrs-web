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
            <%--var groupShiftId = '<s:property value="jamKerja.jamKerjaId"/>';--%>
            var groupShiftName = '<s:property value="jamKerja.jamKerjaId"/>';


            if (groupShiftName != '' ) {

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
                if (groupShiftName =='') {
                    msg = 'Field <strong>jam Kerja Id</strong> is required.' + '<br/>';
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

<s:form id="deleteForm" method="post" theme="simple" namespace="/jamkerja" action="saveDelete_jamkerja" cssClass="well form-horizontal">

    <legend align="left">Delete Shift</legend>

    <table>
        <tr>
            <td width="10%" align="center">
                <%@ include file="/pages/common/message.jsp" %>
            </td>
        </tr>
    </table>

    <div class="form-group">
        <label class="control-label col-sm-2">Jam Kerja Id :</label>
        <div class="col-sm-8">
            <s:textfield cssClass="form-control" id="jamKerjaId" name="jamKerja.jamKerjaId" readonly="true" required="true" />
            <s:hidden id="createdDate" name="jamKerja.createdDate" />
            <s:hidden id="createdWho" name="jamKerja.createdWho" />
            <s:hidden id="statusGiling" name="jamKerja.statusGiling" />
                <%--<input type="text" class="form-control" name="kodeAlat" id="alat.kodeAlat" readonly >--%>
        </div>
    </div>

    <%--<div class="form-group">
        <label class="control-label col-sm-2">Status Giling:</label>
        <div class="col-sm-8">
            <s:select cssClass="form-control" list="#{'DMG':'DMG', 'LMG':'LMG'}" id="statusGiling" name="jamKerja.statusGiling"
                      headerKey="" headerValue="[Select one]" disabled="true"/>
        </div>
    </div>--%>

    <div class="form-group">
        <label class="control-label col-sm-2">Jam Awal Kerja :</label>
        <div class="col-sm-8">
            <s:select cssClass="form-control" list="#{'01':'01', '02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23','24':'24'}" id="jamAwalJam" name="jamKerja.jamAwalJam"
                      headerKey="" headerValue="[Jam]" readonly="true"/>
            <s:select cssClass="form-control" list="#{'00':'00', '30':'30'}" id="jamAwalMenit" name="jamKerja.jamAwalMenit"
                      headerKey="" headerValue="[Menit]" readonly="true"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">Jam Akhir Kerja :</label>
        <div class="col-sm-8">
            <s:select cssClass="form-control" list="#{'01':'01', '02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23','24':'24'}" id="jamAkhirJam" name="jamKerja.jamAkhirJam"
                      headerKey="" headerValue="[Jam]" readonly="true"/>
            <s:select cssClass="form-control" list="#{'00':'00', '30':'30'}" id="jamAkhirMenit" name="jamKerja.jamAkhirMenit"
                      headerKey="" headerValue="[Menit]" readonly="true"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2">Flag :</label>
        <div class="col-sm-8" align="left">
            <s:select cssClass="form-control" list="#{'Y':'Active', 'N':'NonActive'}" id="flag" name="jamKerja.flag"
                      headerKey="" headerValue="[Select one]" readonly="true"/>
        </div>
    </div>


    <br>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
                <%--<button type="submit" class="btn btn-default">Submit</button>--%>
            <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="deleteForm" id="save" name="save"
                       onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
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

