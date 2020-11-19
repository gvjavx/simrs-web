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
            //current date
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth()+1; //January is 0!
            var yyyy = today.getFullYear();
            today = yyyy + '-' + mm + '-' + dd;

            var liburTahun = document.getElementById("tahun").value;
            var tanggal1    = document.getElementById("tgl1").value;
            var tanggal2    = document.getElementById("tgl2").value;
            var tang1 = tanggal1.split("-");
            var tang2 = tanggal2.split("-");

            //alert(today);
            /*alert(liburTahun);
             alert(tanggal1);
             alert(tanggal2);*/

            if (liburTahun != "" && tanggal1 != "" && tanggal2 != "") {
                if(Date.parse(tang1[2] + '-' + tang1[1] + '-' + tang1[0]) > Date.parse(tang2[2] + '-' + tang2[1] + '-' + tang2[0])){
                    //alert('error');
                    event.originalEvent.options.submit = false;
                    var msg = "";

                    msg += '<strong>Date From</strong> Cannot be more than <strong>Date To</strong>' + '<br/>';

                    document.getElementById('errorValidationMessage').innerHTML = msg;

                    $.publish('showErrorValidationDialog');
                }else{
                    if(Date.parse(today) > Date.parse(tang1[2] + '-' + tang1[1] + '-' + tang1[0])){
                        event.originalEvent.options.submit = false;
                        var msg = "";

                        msg += '<strong> can not Delete past data</strong>' + '<br/>';

                        document.getElementById('errorValidationMessage').innerHTML = msg;

                        $.publish('showErrorValidationDialog');
                    }else{
                        if (confirm('Do you want to Delete this record?')) {
                            event.originalEvent.options.submit = true;
                            $.publish('showDialog');

                        } else {
                            // Cancel Submit comes with 1.8.0
                            event.originalEvent.options.submit = false;
                        }
                    }
                }

            } else {

                event.originalEvent.options.submit = false;

                var msg = "";
                if (liburTahun == '') {
                    msg += 'Field <strong>Tahun</strong> is required.' + '<br/>';
                }
                if (tanggal1 == '') {
                    msg += 'Field <strong>Date From</strong> is required.' + '<br/>';
                }
                if (tanggal2 == '') {
                    msg += 'Field <strong>Date To</strong> is required.' + '<br/>';
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

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/ijinKeluar" action="saveDelete_libur" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">Delete Ijin Tidak Masuk</legend>


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
                            <label class="control-label"><small>Ijin Tidak Masuk Id :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="liburId" name="ijinKeluar.liburId" required="true" readonly="true" cssClass="form-control"/>
                                    <%--<s:hidden name="ijinKeluar.liburId"/>--%>

                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Tipe Ijin Tidak Masuk :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#session.listOfResultTipe" id="Tipe" name="ijinKeluar.tipeLiburId" disabled="true"
                                          listKey="tipeLiburId" listValue="tipeLiburName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>


                    <tr>
                        <td>
                            <label class="control-label"><small>Tahun :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield readonly="true" id="tahun" name="ijinKeluar.liburTahun" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>From :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield readonly="true" id="tgl1" name="ijinKeluar.stLiburFrom" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>To :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield readonly="true" id="tgl2" name="ijinKeluar.stLiburTo" required="true" disabled="false" cssClass="form-control"/>

                            </table>

                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Keterangan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="4" readonly="true" id="liburKeterangan" name="ijinKeluar.liburKeterangan" required="false" disabled="false" cssClass="form-control"/>

                            </table>

                        </td>
                    </tr>

                </table>



                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="modifyRolefuncForm" id="save" name="save"
                                    onCompleteTopics="closeDialog,successDialog" onBeforeTopics="beforeProcessSave"
                                   onSuccessTopics="successDialog" onErrorTopics="errorDialog" >
                            <i class="fa fa-trash"></i>
                            Delete
                        </sj:submit>
                        <button type="button" id="cancel" class="btn btn-default" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="cancelBtn();">
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
                                                <img border="0" src="<s:url value="/pages/images/loading4.gif"/>" name="image_indicator_write">
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
        </td>
    </tr>
</table>
</body>
</html>

