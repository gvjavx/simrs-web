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
            var unit = document.getElementById("branch1").value;
            var minBpjsKs = document.getElementById("minBpjsKs1").value;
            var maxBpjsKs = document.getElementById("maxBpjsKs1").value;
            var minBpjsTk = document.getElementById("minBpjsTk1").value;
            var maxBpjsTk = document.getElementById("maxBpjsTk1").value;

            var percentBpjsKsKary = document.getElementById("percentBpjsKsKary1").value;
            var percentBpjsKsPers = document.getElementById("percentBpjsKsPers1").value;
            var percentBpjsTkKary = document.getElementById("percentBpjsTkKary1").value;
            var percentBpjsTkPers = document.getElementById("percentBpjsTkPers1").value;

            if (unit != ''&& minBpjsKs != '' && maxBpjsKs != '' && minBpjsTk != '' && maxBpjsTk != ''
                    && percentBpjsKsKary != '' && percentBpjsKsPers != '' && percentBpjsTkKary != '' && percentBpjsTkPers != '') {
                if(isNaN(minBpjsKs) ==  false && isNaN(maxBpjsKs) == false && isNaN(minBpjsTk) == false && isNaN(maxBpjsTk) == false){
                    if (confirm('Do you want to save this record?')) {
                        event.originalEvent.options.submit = true;
                        $.publish('showDialog');
                    } else {
                        // Cancel Submit comes with 1.8.0
                        event.originalEvent.options.submit = false;
                    }
                }else{
                    event.originalEvent.options.submit = false;
                    var msg = "";
                    if (isNaN(minBpjsKs)) {
                        msg += 'Field <strong>min Bpjs Ks</strong> Harus angka tanpa koma.' + '<br/>';
                    }
                    if (isNaN(maxBpjsKs)) {
                        msg += 'Field <strong>max Bpjs Ks</strong> Harus angka tanpa koma.' + '<br/>';
                    }
                    if (isNaN(minBpjsTk)) {
                        msg += 'Field <strong>min Bpjs Tk</strong> Harus angka tanpa koma.' + '<br/>';
                    }
                    if (isNaN(maxBpjsTk)) {
                        msg += 'Field <strong>max Bpjs Tk</strong> Harus angka tanpa koma.' + '<br/>';
                    }

                    document.getElementById('errorValidationMessage').innerHTML = msg;

                    $.publish('showErrorValidationDialog');
                }
            } else {
                event.originalEvent.options.submit = false;
                var msg = "";
                if (unit == '') {
                    msg += 'Field <strong>Golongan </strong> is required.' + '<br/>';
                }
                if (minBpjsKs == '') {
                    msg += 'Field <strong>min BpjsKs </strong> is required.' + '<br/>';
                }
                if (maxBpjsKs == '') {
                    msg += 'Field <strong>max BpjsKs </strong> is required.' + '<br/>';
                }
                if (minBpjsTk == '') {
                    msg += 'Field <strong>min BpjsTk </strong> is required.' + '<br/>';
                }
                if (maxBpjsTk == '') {
                    msg += 'Field <strong>max BpjsTk </strong> is required.' + '<br/>';
                }
                if (percentBpjsKsKary == '') {
                    msg += 'Field <strong>persen BpjsKs Kary </strong> is required.' + '<br/>';
                }
                if (percentBpjsKsPers == '') {
                    msg += 'Field <strong>persen BpjsKs Pers </strong> is required.' + '<br/>';
                }
                if (percentBpjsTkKary == '') {
                    msg += 'Field <strong>persen BpjsTk Kary </strong> is required.' + '<br/>';
                }
                if (percentBpjsTkPers == '') {
                    msg += 'Field <strong>persen BpjsTk Pers </strong> is required.' + '<br/>';
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
            <s:form id="addPayrollSkalaGajiForm" method="post" theme="simple" namespace="/payrollBpjs" action="saveAdd_payrollBpjs" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">Add Payroll Bpjs</legend>


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
                            <label class="control-label"><small>Unit :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                <s:select list="#initComboBranch.listOfComboBranch" id="branch1" name="payrollBpjs.branchId"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Min Bpjs Ks :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" min="0" id="minBpjsKs1" name="payrollBpjs.minBpjsKs" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Max Bpjs Ks :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" min="0" id="maxBpjsKs1" name="payrollBpjs.maxBpjsKs" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Min Bpjs Tk :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" min="0" id="minBpjsTk1" name="payrollBpjs.minBpjsTk" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Max Bpjs Tk :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield type="number" min="0" id="maxBpjsTk1" name="payrollBpjs.maxBpjsTk" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Persen Bpjs Ks Kary(%) :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="percentBpjsKsKary1" name="payrollBpjs.iuranBpjsKsKaryPersen" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Persen Bpjs Ks Pers(%) :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="percentBpjsKsPers1" name="payrollBpjs.iuranBpjsKsPersPersen" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Persen Bpjs Tk Kary(%) :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="percentBpjsTkKary1" name="payrollBpjs.iuranBpjsTkKaryPersen" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Persen Bpjs Tk Pers(%) :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="percentBpjsTkPers1" name="payrollBpjs.iuranBpjsTkPersPersen" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                </table>



                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addPayrollSkalaGajiForm" id="save" name="save"
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

