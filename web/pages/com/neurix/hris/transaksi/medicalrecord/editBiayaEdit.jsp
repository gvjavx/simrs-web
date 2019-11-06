<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">

        function closeBtnAddLahan() {
            $('#view_dialog_menu').dialog('close');
        };

        function saveButtonAddLahan() {
            var biayaPengobatanId = document.getElementById('biayaPengobatanId').value;
            var jumlah = document.getElementById('jumlah').value;
            var pengobatanId = document.getElementById('pengobatanId').value;
            var tipe = document.getElementById('tipe').value;
            if (biayaPengobatanId!='' && jumlah!='' && pengobatanId != '' && tipe !='') {
                dwr.engine.setAsync(false);
                MedicalRecordAction.saveEditPengobatanEdit(pengobatanId,biayaPengobatanId, jumlah, tipe, function (response) {
                    if (response == '00') {
                        $('#view_dialog_menu').dialog('close');
                        //reload popup add permohonan
                        document.saveMedicalRecord.action='initEdit_medicalrecord.action';
                        document.saveMedicalRecord.submit();
                    } else if(response == '02'){
                        msg = 'Data <strong> biaya pengobatan Id</strong> Masih Dipakai di Bukti Pengobatan<br/>';
                        document.getElementById('errorValidationMessageAddLahan').innerHTML = msg;
                        $.publish('showErrorValidationDialogAddLahan');
                    } else {
                        $('#info_dialog_add_lahan').dialog('open');
                    }
                });
            } else {
                var msg = "";
                if (biayaPengobatanId == '') {
                    msg = 'Field <strong>biaya TrainingPerson Id</strong> is required.' + '<br/>';
                }
                if (pengobatanId == '') {
                    msg = 'Field <strong>TrainingPerson Id</strong> is required.' + '<br/>';
                }
                if (tipe == '') {
                    msg = 'Field <strong>Tipe</strong> is required.' + '<br/>';
                }
                if (biayaPengobatanId == '') {
                    msg = 'Field <strong>biaya TrainingPerson Id</strong> is required.' + '<br/>';
                }
                if (jumlah == '') {
                    msg = msg + 'Field <strong>jumlah</strong> is required.' + '<br/>';
                }
                document.getElementById('errorValidationMessageAddLahan').innerHTML = msg;
                $.publish('showErrorValidationDialogAddLahan');
            }
        }

        function okFailureButtonAddLahan() {
            $('#info_dialog_add_lahan').dialog('close');
            $('#view_dialog_menu').dialog('close');
        }
        var tipe = document.getElementById('tipe').value;
        if (tipe == 'delete'){
            document.getElementById('biayaPengobatanId').disabled = true;
            document.getElementById('jumlah').readOnly = true;
            document.getElementById('pengobatanId').readOnly = true;
            document.getElementById('tipe').readOnly = true;
            document.getElementById('saveabtn').style.display = 'none';
        } else {
            document.getElementById('deleteBtn').style.display = 'none';
        }
    </script>
</head>
<body bgcolor="#FFFFFF">
<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="addBiayaForm" method="post" theme="simple" namespace="/medicalrecord" action="addBiaya_medicalrecord" cssClass="well form-horizontal">
                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>
                <legend align="left">Biaya Pengobatan</legend>
                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>
                <table>
                    <s:hidden id="tipe" name="pengobatan.tipe"/>
                    <tr>
                        <td>
                            <label class="control-label"><small>Pengobatan Id </small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="pengobatanId" cssClass="form-control" name="pengobatan.pengobatanId" required="false" disabled="false"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Biaya Pengobatan </small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboBiayaPengobatan" namespace="/medicalrecord" name="initComboBiayaPengobatan_medicalrecord"/>
                                <s:select list="#comboBiayaPengobatan.listOfComboBiayaPengobatan" id="biayaPengobatanId" name="pengobatan.biayaPengobatanId"
                                          listKey="biayaPengobatanId" listValue="biayaPengobatanName" headerKey="" headerValue="[Select one]"
                                          cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Jumlah </small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="jumlah" cssClass="form-control" name="pengobatan.jumlah" required="false" disabled="false"/>
                                <s:hidden id="biayaPengobatanName"></s:hidden>
                            </table>
                        </td>
                    </tr>
                </table>
                <br>
                <div id="actions" class="form-actions">
                    <center>
                        <table>
                            <tr>
                                <td></td>
                                <td>
                                    <div id="crud">
                                        <table>
                                            <tr>
                                                <td>
                                                    <label class="control-label"></label>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td align="center">

                                                    <sj:dialog id="info_dialog_add_lahan" openTopics="showInfoDialogAddLahan" modal="true" resizable="false"
                                                               position="center" height="200" width="400" autoOpen="false" title="Infomation Dialog"
                                                               buttons="{
                                                                'OK':function() { okFailureButtonAddLahan(); }
                                                                }"
                                                    >
                                                        <img id="iconinfo" border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error">
                                                        Found failure when saving, please try again or call your admin.
                                                    </sj:dialog>

                                                    <sj:dialog id="error_validation_dialog_add_lahan" openTopics="showErrorValidationDialogAddLahan" modal="true" resizable="false"
                                                               position="center" height="280" width="500" autoOpen="false" title="Warning"
                                                               buttons="{
                                                                    'OK':function() { $('#error_validation_dialog_add_lahan').dialog('close'); }
                                                                }"
                                                    >
                                                        <div class="alert alert-error fade in">
                                                            <label class="control-label" align="left">
                                                                <img id="iconerror" border="0" src="<s:url value="/pages/images/icon_error.png"/>" name="icon_error"> Please check this field :
                                                                <br/>
                                                                <center><div id="errorValidationMessageAddLahan"></div></center>
                                                            </label>
                                                        </div>
                                                    </sj:dialog>
                                                    <button type="button" id="deleteBtn" class="btn btn-primary" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="saveButtonAddLahan();">
                                                        <i class="icon-ok-circle icon-white"/>Delete
                                                    </button>
                                                    <button type="button" id="saveabtn" class="btn btn-primary" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="saveButtonAddLahan();">
                                                        <i class="icon-ok-circle icon-white"/>Save
                                                    </button>
                                                </td>
                                                <td>
                                                    <button type="button" id="cancelbtn" class="btn" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px;font-weight: bold;" onclick="closeBtnAddLahan();">
                                                        <i class="icon-remove-circle"/> Cancel
                                                    </button>

                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </center>
                </div>
            </s:form>
        </td>
    </tr>
</table>
</body>
</html>

