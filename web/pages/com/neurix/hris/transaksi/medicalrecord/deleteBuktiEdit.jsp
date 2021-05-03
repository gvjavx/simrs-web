<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">

        function closeBtnAddLahan() {
            $('#view_dialog_add_user').dialog('close');
        };

        function saveButtonAddLahan() {
            var keterangan = document.getElementById('keterangan').value;
            var buktiId = document.getElementById('buktiId').value;
            var pengobatanId = document.getElementById('pengobatanId').value;
            var fileName = document.getElementById('fileName').value;

//            alert(memberPosition);
            if (keterangan!='' && buktiId!='' && pengobatanId != '' && fileName !='') {

                dwr.engine.setAsync(false);
                MedicalRecordAction.saveDeleteBukti(pengobatanId,keterangan, buktiId, fileName, function (response) {
                    if (response == '00') {
                        $('#view_dialog_menu').dialog('close');


                        //reload popup add permohonan
                        document.saveMedicalRecord.action='initEdit_medicalrecord.action';
                        document.saveMedicalRecord.submit();

                    } else {
                        $('#info_dialog_add_lahan').dialog('open');
                    }

                });

            } else {

                var msg = "";
                if (keterangan == '') {
                    msg = 'Field <strong>keterangan</strong> is required.' + '<br/>';
                }
                if (buktiId == '') {
                    msg = 'Field <strong>bukti Id</strong> is required.' + '<br/>';
                }
                if (pengobatanId == '') {
                    msg = 'Field <strong>pengobatan Id</strong> is required.' + '<br/>';
                }
                if (biayaPengobatanId == '') {
                    msg = 'Field <strong>biaya TrainingPerson Id</strong> is required.' + '<br/>';
                }

                if (fileName == '') {
                    msg = msg + 'Field <strong>file Name</strong> is required.' + '<br/>';
                }

                document.getElementById('errorValidationMessageAddLahan').innerHTML = msg;

                $.publish('showErrorValidationDialogAddLahan');

            }


        };

        function okFailureButtonAddLahan() {
            $('#info_dialog_add_lahan').dialog('close');
            $('#view_dialog_menu').dialog('close');
        };

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
                <s:hidden id="tipe" name="pengobatan.tipe"/>

                <legend align="left">Biaya Pengobatan</legend>


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
                            <label class="control-label"><small>Bukti id :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="buktiId" cssClass="form-control" name="buktiPengobatan.buktiId" required="false" disabled="false" readonly="true"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Pengobatan id :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="pengobatanId" cssClass="form-control" name="buktiPengobatan.pengobatanId" required="false" disabled="false" readonly="true"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>File Name :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="fileName" cssClass="form-control" name="buktiPengobatan.fileName" required="false" disabled="false" readonly="true"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Keterangan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="keterangan" cssClass="form-control" name="buktiPengobatan.keterangan" required="false" disabled="false" readonly="true"/>
                            </table>
                        </td>
                    </tr>
                </table>
                <br>
                <%--<div class="form-group">--%>
                <%--<div class="col-sm-offset-2 col-sm-10">--%>
                <%--<sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="addBiayaForm" id="save" name="save"--%>
                <%--onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"--%>
                <%--onSuccessTopics="successDialog" onErrorTopics="errorDialog" >--%>
                <%--<i class="icon-ok-sign icon-white"></i>--%>
                <%--Save--%>
                <%--</sj:submit>--%>
                <%--<button type="button" id="cancel" class="btn btn-default" onclick="cancelBtn();">--%>
                <%--<i class="icon-remove-circle"/> Cancel--%>
                <%--</button>--%>
                <%--</div>--%>
                <%--</div>--%>


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

