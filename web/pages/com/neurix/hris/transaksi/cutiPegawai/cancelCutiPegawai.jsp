<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">
        $.subscribe('beforeProcessSaveCancelCutiPegawai', function (event, data) {
            var keterangan;
            keterangan = document.getElementById("keteranganBatal").value;
            if (keterangan!=="") {
                if (confirm('Do you want to cancel this record?')) {
                    event.originalEvent.options.submit = true;
                    $.publish('showDialogCancelCutiPegawai');
                } else {
                    // Cancel Submit comes with 1.8.0
                    event.originalEvent.options.submit = false;
                }
            }
            else {
                event.originalEvent.options.submit = false;
                var msg = "";
                if ( keterangan === '') {
                    msg += 'Field <strong>Keterangan</strong> is required.' + '<br/>';
                }
                document.getElementById('errorMessage').innerHTML = msg;
                $.publish('showErrorDialogCancelCutiPegawai');
            }
        });
        $.subscribe('successDialogCancelCutiPegawai', function (event, data) {
            if (event.originalEvent.request.status == 200) {
                jQuery(".ui-dialog-titlebar-close").hide();
                $.publish('showInfoDialogCancelCutiPegawai');
            }
        });
        $.subscribe('errorDialogCancelCutiPegawai', function (event, data) {
            document.getElementById('errorMessage').innerHTML = "Status = " + event.originalEvent.request.status + ", \n\n" + event.originalEvent.request.getResponseHeader('message');
            $.publish('showErrorDialogCancelCutiPegawai');
        });

        function cancelBtn() {
            $('#view_dialog_menu_cuti_pegawai').dialog('close');
        }
        function callSearch() {
            window.location.reload(true);
            $('#waiting_dialog').dialog('close');
            $('#view_dialog_menu_cuti_pegawai').dialog('close');
            $('#info_dialog').dialog('close');
        }

    </script>

</head>

<body bgcolor="#FFFFFF">

<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/cutiPegawai" action="saveCancel_cutiPegawai" cssClass="well form-horizontal">

                <s:hidden name="addOrCancel"/>
                <s:hidden name="delete"/>



                <legend align="left">Batal Cuti Pegawai</legend>


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
                            <div style="display: none">
                                <label class="control-label"><small>Cuti Pegawai ID :</small></label>
                            </div>
                        </td>
                        <td>
                            <table>
                                <div style="display: none">
                                    <s:textfield  id="cutiPegawaiId" name="cutiPegawai.cutiPegawaiId" required="true" readonly="true" cssClass="form-control"/>
                                </div>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>NIP :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="nipId" name="cutiPegawai.nip" required="true" readonly="true" cssClass="form-control"/>
                                <s:textfield  id="golonganId12" name="" required="true" readonly="true" cssStyle="display:none;" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="namaId1" name="cutiPegawai.namaPegawai" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Jabatan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboPosition" namespace="/admin/user" name="initComboPosition_user"/>
                                <s:select cssClass="form-control" list="#comboPosition.listOfComboPositions" id="positionid12" name="cutiPegawai.posisiId" required="false" readonly="true"
                                          listKey="stPositionId" listValue="positionName" headerKey="" headerValue="[Select one]"/>                                    <%--<s:hidden name="cutiPegawai.cutiPegawaiId"/>--%>

                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Bagian :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                <s:select list="#comboDivisi.listComboDepartment" id="divisiId12" name="cutiPegawai.divisiId"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" readonly="true" />
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
                                <s:select cssClass="form-control" list="#comboBranch.listOfComboBranches" id="branchid" name="cutiPegawai.unitId" required="true" readonly="true"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" />
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboCuti" namespace="/cuti" name="initComboCuti_cuti"/>
                                <s:select list="#comboCuti.listComboCuti" id="cuti124" name="cutiPegawai.cutiId" readonly="true"
                                          listKey="cutiId" listValue="cutiName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                                <s:textfield  id="jenisCuti" name="cutiPegawai.cutiName" required="false" readonly="true" cssClass="form-control" cssStyle="display: none"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Sisa Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="sisaCuti" name="cutiPegawai.sisaCutiHari" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal Awal Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tgl2" name="cutiPegawai.stTanggalDari" cssClass="form-control pull-right" readonly="true"
                                                 required="true"  cssStyle=""/>
                                </div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tanggal Selesai Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <div class="input-group date">
                                    <div class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </div>
                                    <s:textfield id="tgl1" name="cutiPegawai.stTanggalSelesai" cssClass="form-control pull-right" readonly="true"
                                                 required="true"  cssStyle=""/></div>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Lama Cuti :</small></label>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td width="60px">
                                        <s:textfield  id="lamaCuti" name="cutiPegawai.lamaHariCuti" required="false" readonly="true" cssClass="form-control"/>
                                    </td>
                                    <td>&nbsp;&nbsp;&nbsp;</td>
                                    <td>
                                        hari
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Pegawai Pengganti Sementara :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="pegawaiPenggantiSementara" name="cutiPegawai.pegawaiPenggantiSementara" required="false" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Keterangan Batal :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="3" id="keteranganBatal" required="false" name="cutiPegawai.cancelNote"/>
                            </table>
                        </td>
                    </tr>
                </table>

                <br>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="modifyRolefuncForm" id="save" name="save"
                                   onBeforeTopics="beforeProcessSaveCancelCutiPegawai" onCompleteTopics="closeDialogCancelCutiPegawai,successDialogCancelCutiPegawai"
                                   onSuccessTopics="successDialogCancelCutiPegawai" onErrorTopics="errorDialogCancelCutiPegawai" >
                            <i class="fa fa-check"></i>
                            Batalkan
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
                                        <sj:dialog id="waiting_dialog" openTopics="showDialogCancelCutiPegawai" closeTopics="closeDialogCancelCutiPegawai" modal="true"
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

                                        <sj:dialog id="info_dialog" openTopics="showInfoDialogCancelCutiPegawai" modal="true" resizable="false"
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

                                        <sj:dialog id="error_dialog" openTopics="showErrorDialogCancelCutiPegawai" modal="true" resizable="false"
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

                                        <sj:dialog id="error_validation_dialog" openTopics="showErrorValidationDialogCancelCutiPegawai" modal="true" resizable="false"
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
