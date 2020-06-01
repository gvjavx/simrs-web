<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <%--<script type='text/javascript' src='<s:url value="/dwr/interface/PayrollSkalaGajiAction.js"/>'></script>--%>
    <script type="text/javascript">
    function callSearch2() {
            //$('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            $('#info_dialog').dialog('close');
//            window.location.reload(true);
            document.mappingPersenGajiForm.action = "search_mappingPersenGaji.action";
            document.mappingPersenGajiForm.submit();
        };

        $.subscribe('beforeProcessSave2', function (event, data) {
            var idMapping = document.getElementById("mappingPersenGajiId2").value;
            var namaMapping = document.getElementById("namaMappingPersenGaji1").value;
            var jenisGaji = document.getElementById("jenisGaji1").value;
            var persentase = document.getElementById("persentase1").value;

            if (idMapping != '' && namaMapping != '' && jenisGaji != '' && persentase != '') {
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
                if (idMapping == '') {
                    msg += 'Field <strong>ID Mapping Persentase Gaji </strong> is required.' + '<br/>';
                }
                if (namaMapping == '') {
                    msg += 'Field <strong>Nama Mapping Persentase Gaji </strong> is required.' + '<br/>';
                }
                if (jenisGaji == '') {
                    msg += 'Field <strong>Jenis Gaji </strong> is required.' + '<br/>';
                }
                if (persentase == '') {
                    msg += 'Field <strong>Persentase </strong> is required.' + '<br/>';
                }
                document.getElementById('errorValidationMessage2').innerHTML = msg;

                $.publish('showErrorValidationDialog1');
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
            <s:form id="editMappingForm" method="post" theme="simple" namespace="/mappingPersenGaji" action="saveEdit_mappingPersenGaji" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>

                <legend align="left">Edit Mapping Persen Gaji</legend>


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
                            <label class="control-label"><small>ID :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="mappingPersenGajiId2" name="mappingPersenGaji.mappingPersenGajiId" readonly="true" required="true" disabled="false" cssClass="form-control"/>
                                <%--<s:hidden id="mappingPersenGajiId2" name="dokter.idDokter" />--%>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama Mapping :</small></label>
                        </td>
                        <td>
                            <table>
                                    <%--<s:select list="#{'igd':'IGD', 'rawat_jalan' : 'Rawat Jalan', 'apotek' : 'Apotek',--%>
                                    <%--'rawat_inap' : 'Rawat Inap', 'radiologi' : 'Radiologi', 'lab' : 'LAB'}" id="tipePelayanan" name="pelayanan.tipePelayanan"--%>
                                    <%--listKey="positionId" headerKey="" headerValue="[Select one]" cssClass="form-control"/>--%>
                                <s:select list="#{'plt':'PLT', 'pjs' : 'PJS', 'percobaan' : 'Percobaan'}"
                                          id="namaMappingPersenGaji2" name="mappingPersenGaji.namaMappingPersenGaji"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Jenis Gaji :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'gaji_golongan':'Gaji Golongan', 'tunjangan_umk' : 'Santunan Khusus', 'tunjangan_jabatan' : 'Tunjangan Jabatan',
                                                         'tunjangan_jabatan_struktural' : 'Tunjangan Struktural', 'tunjangan_strategis' : 'Tunjangan Fungsional', 'tunjangan_tambahan' : 'Tunjangan Tambahan'}"
                                          id="jenisGaji2" name="mappingPersenGaji.jenisGaji"
                                          headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Persentase :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="persentase2" name="mappingPersenGaji.presentase" required="true" disabled="false" type="number" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                </table>

                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                            <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="editMappingForm" id="save2" name="save"
                                   onBeforeTopics="beforeProcessSave2" onCompleteTopics="closeDialog,successDialog"
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
                                                   title="Update Data ...">
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
                                            Record has been updated successfully.
                                        </sj:dialog>

                                        <sj:dialog id="error_dialog" openTopics="showErrorDialog" modal="true" resizable="false"
                                                   height="250" width="600" autoOpen="false" title="Error Dialog"
                                                   buttons="{
                                                                        'OK':function() { $('#error_dialog').dialog('close'); window.location.reload(true)}
                                                                    }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> System Found : <p id="errorMessage"></p>
                                                </label>
                                            </div>
                                        </sj:dialog>

                                        <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialog1" modal="true" resizable="false"
                                                   height="280" width="500" autoOpen="false" title="Warning"
                                                   buttons="{
                                                                        'OK':function() { $('#error_validation_dialog').dialog('close'); window.location.reload(true)}
                                                                    }"
                                        >
                                            <div class="alert alert-error fade in">
                                                <label class="control-label" align="left">
                                                    <img border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                    <br/>
                                                    <center><div id="errorValidationMessage2"></div></center>
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


