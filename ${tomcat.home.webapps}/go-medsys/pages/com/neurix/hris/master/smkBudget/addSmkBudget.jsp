<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type='text/javascript' src='<s:url value="/dwr/interface/SmkBudgetAction.js"/>'></script>
    <script type="text/javascript">

        function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var sjId = document.getElementById("strukturJabatanId").value;
            var bagian = document.getElementById("bagianName").value;
            var target = document.getElementById("target").value;
            var bobot = document.getElementById("bobot").value;
            var realisasi = document.getElementById("realisasi").value;
            var periode = document.getElementById("periode12").value;


            if (sjId != ''&&bagian != ''&&target != ''&&bobot != ''&&realisasi != ''&&periode != '') {
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

                if (sjId == '') {
                    msg += 'Field <strong>Struktur Jabatan Id</strong> is required.' + '<br/>';
                }
                if (bagian == '') {
                    msg += 'Field <strong>Bagian Name</strong> is required.' + '<br/>';
                }
                if (target == '') {
                    msg += 'Field <strong>Target</strong> is required.' + '<br/>';
                }
                if (bobot == '') {
                    msg += 'Field <strong>Bobot</strong> is required.' + '<br/>';
                }
                if (realisasi == '') {
                    msg += 'Field <strong>Realisasi</strong> is required.' + '<br/>';
                }
                if (periode == '') {
                    msg += 'Field <strong>Periode</strong> is required.' + '<br/>';
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
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/smkBudget" action="saveAdd_smkBudget" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">Add SMK Budget</legend>


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
                            <label class="control-label"><small>Struktur Jabatan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="strukturJabatanId" name="smkBudget.strukturJabatanId" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <script type='text/javascript'>
                        var functions, mapped;
                        $('#strukturJabatanId').typeahead({
                            minLength: 1,
                            source: function (query, process) {
                                functions = [];
                                mapped = {};

                                var data = [];
                                dwr.engine.setAsync(false);
                                SmkBudgetAction.initComboStrukturJabatan(query, "", function (listdata) {
                                    data = listdata;
                                });

                                $.each(data, function (i, item) {
                                    var labelItem = item.strukturJabatanId;
                                    mapped[labelItem] = {branchId:item.branchId,positionId: item.positionId, strukturJabatanId: labelItem,nama:item.strukturJabatanId,positionName:item.positionName};
                                    functions.push(labelItem);
                                });

                                process(functions);
                            },
                            updater: function (item) {
                                var selectedObj = mapped[item];
                                //var namaMember = selectedObj.label;
                                document.getElementById("positionId").value = selectedObj.positionId;
                                document.getElementById("positionName").value = selectedObj.positionName;
                                document.getElementById("branchid12").value = selectedObj.branchId;
                                return selectedObj.strukturJabatanId;
                            }
                        });
                    </script>
                    <tr>
                        <td>
                            <label class="control-label"><small>Position Id :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="positionId" name="smkBudget.positionId" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Position Name :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="positionName" name="smkBudget.positionName" readonly="true" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Unit :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboBranch" namespace="/admin/user" name="initComboBranch_user"/>
                                <s:select readonly="true" cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid12" name="smkBudget.unitId" required="true" listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Bagian Name :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="bagianName" name="smkBudget.bagianName" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Bobot (%):</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="bobot" name="smkBudget.bobot" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Target :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="target" name="smkBudget.target" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Realisasi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="realisasi" name="smkBudget.realisasi" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Periode :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboPeriode" namespace="/smkBudget" name="initComboPeriode_smkBudget"/>
                                <s:select cssClass="form-control" list="#comboPeriode.listOfComboPeriode" id="periode12" name="smkBudget.periode" required="true" headerKey="" headerValue="[Select one]" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nilai Realisasi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="nilaiRealisasi" readonly="true" name="smkBudget.nilaiRealisasi" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nilai Prestasi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="nilaiPrestasi" readonly="true" name="smkBudget.nilaiPrestasi" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Point Prestasi Bagian :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="pointPrestasiBagian" readonly="true" name="smkBudget.pointPrestasiBagian" required="true" cssClass="form-control"/>
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
<script>
    function calculate(){
        var target = document.getElementById('target').value;
        var bobot = document.getElementById('bobot').value;
        var realisasi= document.getElementById('realisasi').value;
        if (target!=""&&bobot!=""&&realisasi!=""){
            dwr.engine.setAsync(false);
            SmkBudgetAction.PointCalculate(target,bobot,realisasi, function (listdata) {
                data = listdata;
            })
            $.each(data, function (i, item) {
                var labelItem = item.budgetId;
                mapped[labelItem] = { id: item.budgetId,nilaiPrestasi:item.nilaiPrestasi,nilaiRealisasi:item.nilaiRealisasi,pointPrestasiBagian:item.pointPrestasiBagian};
                $('#nilaiRealisasi').val(item.nilaiRealisasi).change();
                $('#nilaiPrestasi').val(item.nilaiPrestasi).change();
                $('#pointPrestasiBagian').val(item.pointPrestasiBagian).change();
            });
        }
    }
    $('#strukturJabatanId').change(function() {
        var sjid = document.getElementById('strukturJabatanId').value;
        dwr.engine.setAsync(false);
        SmkBudgetAction.initComboSmkBudget(sjid, function (listdata) {
            data = listdata;
            if (data!=null&&data!=""){
                $('#bagianName').attr("readonly", "true");
                $('#bobot').attr("readonly", "true");
                $('#realisasi').attr("readonly", "true");
                $('#target').attr("readonly", "true");
            }
            else{
                $('#bagianName').removeAttr("readonly");
                $('#bobot').removeAttr("readonly");
                $('#realisasi').removeAttr("readonly");
                $('#target').removeAttr("readonly");
                $('#bagianName').val("").change();
                $('#bobot').val("").change();
                $('#target').val("").change();
                $('#realisasi').val("").change();
                $('#periode12').val("").change();
                $('#nilaiRealisasi').val("").change();
                $('#nilaiPrestasi').val("").change();
                $('#pointPrestasiBagian').val("").change();
            }
        });
        $.each(data, function (i, item) {
            var labelItem = item.budgetId;
            mapped[labelItem] = { id: item.budgetId, strukturJabatanId: item.strukturJabatanId, bobot : item.bobot,target:item.target,realisasi:item.realisasi,periode:item.periode,bagian:item.bagianName};
            var selectedObj = mapped[item];
            $('#bagianName').val(item.bagianName).change();
            $('#bobot').val(item.bobot).change();
            $('#target').val(item.target).change();
            $('#realisasi').val(item.realisasi).change();
            $('#periode12').val(item.periode).change();
        });
    });
    $('#realisasi').change(calculate);
    $('#target').change(calculate);
    $('#bobot').change(calculate);

</script>