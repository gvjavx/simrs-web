<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">

        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var notifId = document.getElementById("notifid1").value;
            var nip = document.getElementById("nip1").value;
            var tipeNotifId = document.getElementById("tipeNotifId1").value;
//            var tipeNotifName = document.getElementById("tipeNotifName1").value;
            var note = document.getElementById("note1").value;
            var read = document.getElementById("read1").value;
            var fromPerson = document.getElementById("fromPerson1").value;
            var noRequest = document.getElementById("noRequest1").value;
            console.log('yayayaya');
            if (notifId !='' && nip != '' && tipeNotifId != '' &&  note != '' && read != '' && fromPerson != '' && noRequest != '' ) {
//                if(isNaN(nip) == false){
                if (confirm('Do you want to save this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialog');
                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }
//                }else{
//                    event.originalEvent.options.submit = false;
//                    var msg = "";
//                    if (isNaN(nip)) {
//                        msg += 'Field <strong>nilai</strong> Harus angka tanpa koma.' + '<br/>';
//                    }
//
//                    document.getElementById('errorValidationMessage').innerHTML = msg;
//
//                    $.publish('showErrorValidationDialog');
//                }
            } else {
                event.originalEvent.options.submit = false;
                var msg = "";

                if (notifId == '') {
                    msg += 'Field <strong>Notif Id </strong> is required.' + '<br/>';
                }

                if (nip == '') {
                    msg += 'Field <strong>Nip </strong> is required.' + '<br/>';
                }

                if (tipeNotifId == '') {
                    msg += 'Field <strong>Tipe Notif Id </strong> is required.' + '<br/>';
                }


                if (note == '') {
                    msg += 'Field <strong>Note </strong> is required.' + '<br/>';
                }

                if (read == '') {
                    msg += 'Field <strong>Read</strong> is required.' + '<br/>';
                }

                if (fromPerson == '') {
                    msg += 'Field <strong>From Person </strong> is required.' + '<br/>';
                }

                if (noRequest == '') {
                    msg += 'Field <strong>No Requestt</strong> is required.' + '<br/>';
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
            <s:form id="formEdit" method="post" theme="simple" namespace="/notifikasi" action="saveDelete_notifikasi" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">Delete Notifikasi</legend>


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
                            <label class="control-label"><small>Notif Id :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="notifid1" readonly="true" name="notifikasi.notifId" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Nip :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="nip1" readonly="true" name="notifikasi.nip" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tipe Notif:</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboTipeNotif" namespace="/tipeNotif" name="initComboTipeNotif_tipeNotif"/>
                                <s:select list="#initComboTipeNotif.listOfComboTipeNotif" id="tipeNotifId1" name="notifikasi.tipeNotifId"
                                          listKey="tipeNotifId" listValue="tipeNotifName" headerKey="" headerValue="" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Note :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="note1" readonly="true" name="notifikasi.note" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Read :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="read1" readonly="true" name="notifikasi.read" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>From Person :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="fromPerson1" readonly="true" name="notifikasi.fromPerson" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>No request :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="noRequest1" readonly="true" name="notifikasi.noRequest" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                </table>



                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="formEdit" id="save" name="save"
                                   onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"
                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                            <i class="fa fa-trash"></i>
                            Delete
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
                                                                    //$(this).dialog('close');
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
        </td>
    </tr>
</table>
</body>
</html>
