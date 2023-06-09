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
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var idIjin = document.getElementById("ijinId").value;
            var nameIjin    = document.getElementById("ijinName1").value;
            var jumlahIjin  = document.getElementById("ijinJumlah1").value;
            var genderIjin    = document.getElementById("ijinGender1").value;
            var ijinTipeHari    = document.getElementById("ijinTipeHari1").value;


            if (nameIjin != '' && jumlahIjin != '' && genderIjin != '' && ijinTipeHari != '') {
                if(Number.isInteger(Number(jumlahIjin))){
                    if (confirm('Do you want to save this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');

                    } else {
                        // Cancel Submit comes with 1.8.0
                        event.originalEvent.options.submit = false;
                    }
                }else {
                    event.originalEvent.options.submit = false;
                    var msg = "";

                    msg += 'Field <strong>Jumlah Ijin Must Number</strong> ' + '<br/>';
                    document.getElementById('errorValidationMessage').innerHTML = msg;
                    $.publish('showErrorValidationDialog');
                }
            } else {
                event.originalEvent.options.submit = false;

                var msg = "";

                if (nameIjin == '') {
                    msg += 'Field <strong>Ijin Name</strong> is required.' + '<br/>';
                }

                if (jumlahIjin == '') {
                    msg += 'Field <strong>Jumlah Ijin</strong> is required.' + '<br/>';
                }
                if (genderIjin == '') {
                    msg += 'Field <strong>Ijin Gender</strong> is required.' + '<br/>';
                }
                if (ijinTipeHari == '') {
                    msg += 'Field <strong>Tipe Hari</strong> is required.' + '<br/>';
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
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/ijin" action="saveAdd_ijin" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">Add Ijin</legend>


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
                            <label class="control-label"><small>Ijin Name :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="ijinName1" name="ijin.ijinName" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Jumlah Hari :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" min="0" id="ijinJumlah1" name="ijin.jumlahIjin" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<label class="control-label"><small>Ijin Gender :</small></label>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<table>--%>
                                <%--<s:textfield id="ijinGender1" name="ijin.gender" required="true" disabled="false" cssClass="form-control"/>--%>
                            <%--</table>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <tr>
                        <td>
                            <label class="control-label"><small>Ijin Gender :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'L':'Laki - laki', 'P' : 'Perempuan'}" id="ijinGender1" name="ijin.gender"
                                          headerKey="L/P" headerValue="[Select one]" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tipe Hari :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'kerja':'Kerja', 'libur' : 'Libur'}" id="ijinTipeHari1" name="ijin.tipeHari"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Agama :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'islam':'Islam', 'kristen' : 'Kristen', 'katolik' : 'Katolik', 'hindu' : 'Hindu',
                                            'budha' : 'Buddha', 'kong hu cu' : 'Kong Hu Cu'}" headerKey="all" headerValue="Semua" id="agama1" name="ijin.agama"
                                          cssClass="form-control" />
                            </table>
                        </td>
                    </tr>
                </table>
                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
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
                                                   title="Searching ...">
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
                                                                      link();
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

