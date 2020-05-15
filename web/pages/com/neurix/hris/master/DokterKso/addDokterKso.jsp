<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type='text/javascript' src='<s:url value="/dwr/interface/DokterAction.js"/>'></script>
    <script type='text/javascript' src='<s:url value="/dwr/interface/DokterKsoAction.js"/>'></script>
    <%--<script type='text/javascript' src='<s:url value="/dwr/interface/PayrollSkalaGajiAction.js"/>'></script>--%>
    <script type="text/javascript">
        function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
            window.location.reload(true);
        };

        $.subscribe('beforeProcessSave', function (event, data) {
            var nip = document.getElementById("nip1").value;
            var branchId = document.getElementById("branchId1").value;
            var masterId = document.getElementById("masterId1").value;
            var jenisKso = document.getElementById("jenisKso1").value;
            var positionId = document.getElementById("positionId1").value;
            var persenKso = document.getElementById("persenKso1").value;
            var persenKs = document.getElementById("persenKs1").value;

            console.log(nip);
            console.log(branchId);
            console.log(masterId);
            console.log(jenisKso);
            console.log(positionId);
            console.log(persenKso);
            console.log(persenKs);

            if (nip != ''&& branchId != '' && masterId != '' && jenisKso != ''
                    && persenKso != '' && persenKs != '' && positionId != '') {
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
                if (nip == '') {
                    msg += 'Field <strong>Nip </strong> is required.' + '<br/>';
                }
                if (branchId == '') {
                    msg += 'Field <strong>Unit </strong> is required.' + '<br/>';
                }
                if (masterId == '') {
                    msg += 'Field <strong>Master ID </strong> is required.' + '<br/>';
                }
                if (jenisKso == '') {
                    msg += 'Field <strong>Jenis KSO </strong> is required.' + '<br/>';
                }
                if (positionId == '') {
                    msg += 'Field <strong>Position ID </strong> is required.' + '<br/>';
                }
                if (persenKso == '') {
                    msg += 'Field <strong>Persen KSO </strong> is required.' + '<br/>';
                }
                if (persenKs == '') {
                    msg += 'Field <strong>Persen KS </strong> is required.' + '<br/>';
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
            window.location.reload(true)
        };


    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="addDokterKsoForm" method="post" theme="simple" namespace="/dokterkso" action="saveAdd_dokterkso" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>

                <legend align="left">Add Dokter KSO</legend>

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
                            <label class="control-label"><small>NIP Dokter :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="nip1" name="dokterKso.nip" cssClass="form-control"
                                             maxlength="12"
                                />
                                <script>
                                    $(document).ready(function() {
                                        var functions, mapped;
                                        $('#nip1').typeahead({
                                            minLength: 1,
                                            source: function (query, process) {
                                                functions = [];
                                                mapped = {};
                                                var data = [];
                                                dwr.engine.setAsync(false);
                                                DokterAction.initTypeaheadDokter(query,function (listdata) {
                                                    data = listdata;
                                                });
                                                $.each(data, function (i, item) {
                                                    var labelItem = item.idDokter + " | " + item.namaDokter;
                                                    mapped[labelItem] = {
                                                        id: item.idDokter,
                                                        nama: item.namaDokter
                                                    };
                                                    functions.push(labelItem);
                                                });
                                                process(functions);
                                            },
                                            updater: function (item) {
                                                var selectedObj = mapped[item];
                                                $('#namaDokter1').val(selectedObj.nama);
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
                            <label class="control-label"><small>Nama Dokter:</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="namaDokter1" name="dokterKso.namaDokter" readonly="true" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Master Id :</small></label>
                        </td>
                        <td>
                            <table>
                                    <%--<s:select list="#{'igd':'IGD', 'rawat_jalan' : 'Rawat Jalan', 'apotek' : 'Apotek',--%>
                                    <%--'rawat_inap' : 'Rawat Inap', 'radiologi' : 'Radiologi', 'lab' : 'LAB'}" id="tipePelayanan" name="pelayanan.tipePelayanan"--%>
                                    <%--listKey="positionId" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                                <s:select list="#{'bpjs':'BPJS', 'umum' : 'Umum'}"
                                          id="masterId1" name="dokterKso.masterId"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Unit :</small></label>
                        </td>
                        <td>
                            <table>
                                    <%--<s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>--%>
                                    <%--<s:select list="#initComboBranch.listOfComboBranch" id="branchId" name="pendapatanDokter.branchId"--%>
                                    <%--listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                                <s:if test='dokterKso.branchUser == "KP"'>
                                    <s:action id="initComboBranch" namespace="/dokterkso" name="initComboBranch_dokterkso"/>
                                    <s:select list="#initComboBranch.listOfComboBranches" id="branchId1" name="dokterKso.branchId"
                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                </s:if>
                                <s:else>
                                    <s:action id="initComboBranch" namespace="/dokterkso" name="initComboBranch_dokterkso"/>
                                    <s:select list="#initComboBranch.listOfComboBranches" id="branchId1" name="dokterKso.branchId" disabled="true"
                                              listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                                    <s:hidden id="branchId1" name="dokterKso.branchId" />
                                </s:else>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Divisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboPosition" namespace="/dokterkso" name="initComboPosition_dokterkso"/>
                                <s:select list="#initComboPosition.listOfComboPositions" id="positionId1" name="dokterKso.positionId"
                                          listKey="positionId" listValue="positionName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Jenis KSO:</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'tindakan':'Tindakan', 'obat' : 'Obat', 'kamar' : 'Kamar'}"
                                          id="jenisKso1" name="dokterKso.jenisKso"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Persen KSO:</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="persenKso1" name="dokterKso.persenKso" type="number" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Persen KS:</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="persenKs1" name="dokterKso.persenKs" type="number" required="true" disabled="false" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tarif Ina :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'N':'Non-Active'}" id="tarifIna1" name="dokterKso.tarifIna"
                                          headerKey="Y" headerValue="Active" cssClass="form-control" />
                            </table>

                        </td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<label class="control-label"><small>Tindakan :</small></label>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<table>--%>
                                <%--<s:action id="initComboTindakan" namespace="/dokterkso" name="initComboTindakan_dokterkso"/>--%>
                                <%--<s:select list="#initComboTindakan.listOfComboTindakans" id="tindakanId1" name="dokterKso.tindakanId"--%>
                                          <%--listKey="idTindakan" listValue="tindakan" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                            <%--</table>--%>
                        <%--</td>--%>
                    <%--</tr>--%>

                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<label class="control-label"><small>Riwayat Tindakan :</small></label>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<table>--%>
                                <%--<s:textfield id="riwayatTindakan1" name="dokterKso.tindakan" cssClass="form-control"--%>
                                             <%--maxlength="12"--%>
                                <%--/>--%>
                                <%--<script>--%>
                                    <%--$(document).ready(function() {--%>
                                        <%--var functions, mapped;--%>
                                        <%--$('#riwayatTindakan1').typeahead({--%>
                                            <%--minLength: 1,--%>
                                            <%--source: function (query, process) {--%>
                                                <%--functions = [];--%>
                                                <%--mapped = {};--%>
                                                <%--var data = [];--%>
                                                <%--dwr.engine.setAsync(false);--%>
                                                <%--DokterKsoAction.initTypeaheadRiwayatTindakan(query,function (listdata) {--%>
                                                    <%--data = listdata;--%>
                                                <%--});--%>
                                                <%--$.each(data, function (i, item) {--%>
                                                    <%--var labelItem = item.idTindakan + " | " + item.namaTindakan;--%>
                                                    <%--mapped[labelItem] = {--%>
                                                        <%--id: item.idTindakan,--%>
                                                        <%--nama: item.namaTindakan--%>
                                                    <%--};--%>
                                                    <%--functions.push(labelItem);--%>
                                                <%--});--%>
                                                <%--process(functions);--%>
                                            <%--},--%>
                                            <%--updater: function (item) {--%>
                                                <%--var selectedObj = mapped[item];--%>
                                                <%--$('#namaTindakan1').val(selectedObj.nama);--%>
                                                <%--return selectedObj.id;--%>
                                            <%--}--%>
                                        <%--});--%>
                                    <%--});--%>
                                <%--</script>--%>
                            <%--</table>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<label class="control-label"><small>Nama Tindakan:</small></label>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<table>--%>
                                <%--<s:textfield id="namaTindakan1" name="dokterKso.namaTindakan" readonly="true" required="true" disabled="false" cssClass="form-control"/>--%>
                            <%--</table>--%>
                        <%--</td>--%>
                    <%--</tr>--%>

                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<label class="control-label"><small>Persen KSO Tindakan:</small></label>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<table>--%>
                                <%--<s:textfield id="persenKsoTindakan1" name="dokterKso.persenKso" type="number" required="true" disabled="false" cssClass="form-control"/>--%>
                            <%--</table>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                </table>



                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addDokterKsoForm" id="save" name="save"
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
                                                                    //$(this).dialog('close');
                                                                      callSearch2();
                                                                   }
                                                            }"
                                        >
                                            <img border="0" src="<s:url value="/pages/images/icon_success.png"/>" name="icon_success">
                                            Record has been saved successfully.
                                        </sj:dialog>

                                        <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close');  window.location.reload(true)}
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

