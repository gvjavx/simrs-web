<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">
        function cekAvailableCoa(nilai){
            var coa = nilai.value;
            var length = nilai.length;
            if (length!=0){
                dwr.engine.setAsync(false);
                ReportDetailAction.cekAvailableCoa(coa, function(listdata) {
                    if (listdata.length!=0){
                        alert("COA sudah ada");
                        $('#reportDetailAdd').val("");
                    }
                });
            }
        }
        function cekAvailableParent(nilai){
            var coa = nilai.value;
            var length = nilai.length;
            if (length!=0){
                dwr.engine.setAsync(false);
                ReportDetailAction.cekAvailableParent(coa, function(adaParent) {
                    if (!adaParent){
                        alert("COA induk tidak ada");
                        $('#reportDetailAdd').val("");
                    }
                });
            }
        }

        function callSearch() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var namaRekening = document.getElementById("reportDetailNameAdd").value;
            var coa    = document.getElementById("reportDetailAdd").value;
            var tipeRekening    = document.getElementById("tipeRekeningIdAdd").value;

            if (namaRekening != '' && coa != '' && tipeRekening != '') {
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
                if (namaRekening == '') {
                    msg += 'Field <strong>nama rekening</strong> is required.' + '<br/>';
                }
                if (coa == '') {
                    msg += 'Field <strong>COA</strong> is required.' + '<br/>';
                }
                if (tipeRekening == '') {
                    msg += 'Field <strong>tipe rekening</strong> is required.' + '<br/>';
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
            <s:form id="formAdd" method="post" theme="simple" namespace="/reportDetail" action="saveAdd_reportDetail" cssClass="well form-horizontal">
                <legend align="left">Add Report Detail</legend>
                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>

                <table>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama Report Detail :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="reportDetailNameAdd" name="reportDetail.namaReportDetail"  cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>COA :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="reportDetailAdd" name="reportDetail.reportDetail"
                                             onkeydown="formatReportDetail(this)"
                                             onkeyup="formatReportDetail(this)" onchange="cekAvailableCoa(this),cekAvailableParent(this)" cssClass="form-control"
                                             maxlength="12"
                                />
                                <script>
                                    $(document).ready(function() {
                                        var functions, mapped;
                                        $('#reportDetailAdd').typeahead({
                                            minLength: 1,
                                            source: function (query, process) {
                                                functions = [];
                                                mapped = {};
                                                var data = [];
                                                dwr.engine.setAsync(false);
                                                ReportDetailAction.initTypeaheadReportDetail(query,function (listdata) {
                                                    data = listdata;
                                                });
                                                $.each(data, function (i, item) {
                                                    var labelItem = item.reportDetail + " | " + item.namaReportDetail;
                                                    mapped[labelItem] = {
                                                        id: item.reportDetail,
                                                        nama: item.namaReportDetail
                                                    };
                                                    functions.push(labelItem);
                                                });
                                                process(functions);
                                            },
                                            updater: function (item) {
                                                var selectedObj = mapped[item];
                                                return selectedObj.id;
                                            }
                                        });
                                    });
                                </script>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tipe Rekening :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboTipeRekening" namespace="/tipeRekening" name="initComboTipeRekening_tipeRekening"/>
                                <s:select list="#initComboTipeRekening.listOfComboTipeRekening" id="tipeRekeningIdAdd" name="reportDetail.tipeRekeningId"
                                          listKey="tipeRekeningId" listValue="tipeRekeningName"  headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                </table>
                <br>
                <br>
                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="formAdd" id="save" name="save"
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
                                        </sj:dialog>

                                        <sj:dialog id="info_dialog" openTopics="showInfoDialog" modal="true" resizable="false"
                                                   height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                   buttons="{
                                                              'OK':function() {
                                                                    //$(this).dialog('close');
                                                                      callSearch();
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