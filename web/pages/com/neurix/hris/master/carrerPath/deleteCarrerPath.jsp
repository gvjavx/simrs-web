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
            var branchId = document.getElementById("branchId1").value;
            var positionId = document.getElementById("positionId1").value;
            var jurusanId = document.getElementById("jurusanId1").value;

            if (branchId != '' && positionId != '') {
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

                if (branchId == '') {
                    msg += 'Field <strong>Unit </strong> is required.' + '<br/>';
                }

                if (positionId == '') {
                    msg += 'Field <strong>Posisi / Jabatan</strong> is required.' + '<br/>';
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
            <s:form id="modifyRolefuncForm" method="post" theme="simple" namespace="/carrerPath" action="saveDelete_carrerPath"
                    cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">Add Carrer Path</legend>


                <table>
                    <tr>
                        <td width="10%" align="center">
                            <%@ include file="/pages/common/message.jsp" %>
                        </td>
                    </tr>
                </table>

                <table >
                    <tr style="display: none">
                        <td>
                            <label class="control-label"><small>Carrer Id :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield  id="carrerPathId1" name="carrerPath.carrerPathId" required="true" readonly="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Unit :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="initComboBranch" namespace="/admin/branch" name="initComboBranch_branch"/>
                                <s:select list="#initComboBranch.listOfComboBranch" id="branchId1" name="carrerPath.branchId" disabled="true"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Jabatan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboPosition" namespace="/admin/position" name="searchPosition_position"/>
                                <s:select list="#comboPosition.listOfComboPosition" id="positionId1" name="carrerPath.jabatanId" disabled="true"
                                          listKey="positionId" listValue="positionName" headerKey="" headerValue="" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Min Kerja (Th) :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield onkeyup="checkDec(this);" id="thMinKerja1" name="carrerPath.thMinKerja" disabled="true" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Bidang :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboDivisi" namespace="/department" name="searchDepartment_department"/>
                                <s:select list="#comboDivisi.listComboDepartment" id="divisiId1" name="carrerPath.divisiId" disabled="true"
                                          listKey="departmentId" listValue="departmentName" headerKey="" headerValue="[Select one]" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Min Bidang (Th):</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield onkeyup="checkDec(this);" id="thMinBidang1" name="carrerPath.thMinBidang" disabled="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Bagian :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboBagian" namespace="/positionBagian" name="searchPositionBagian_positionBagian"/>
                                <s:select list="#comboBagian.comboListOfPositionBagian" id="bagianId1" name="carrerPath.bagianId" disabled="true"
                                          listKey="bagianId" listValue="bagianName" headerKey="" headerValue="[Select one]"
                                          cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Min Bagian (Th) :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield onkeyup="checkDec(this);" id="thMinBagian1" disabled="true" name="carrerPath.thMinBagian" required="true" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Pendidikan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:select list="#{'D1':'D1', 'D2':'D2', 'D3':'D3', 'S1':'S1', 'S2':'S2', 'S3':'S3'}" id="flag" disabled="true" name="carrerPath.pendidikan"
                                          headerKey="" headerValue="" cssClass="form-control" />
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Jurusan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboJurusan" namespace="/studyJurusan" name="searchStudyJurusan_studyJurusan"/>
                                <s:select list="#comboJurusan.comboListOfStudyJurusan" id="jurusanId1" name="carrerPath.jurusanIdAdd" disabled="true"
                                          listKey="jurusanId" listValue="jurusanName" headerKey="" headerValue="" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Kelompok Posisi :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboKelompok" namespace="/kelompokPosition" name="searchKelompok_kelompokPosition"/>
                                <s:select list="#comboKelompok.comboListOfKelompokPosition" id="kelompokId1" name="carrerPath.kelompokPositionId" disabled="true"
                                          listKey="kelompokId" listValue="kelompokName" headerKey="" headerValue="" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Golongan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:action id="comboGolongan" namespace="/golongan" name="initComboGolongan_golongan"/>
                                <s:select list="#comboGolongan.listComboGolongan" id="golonganId1" name="carrerPath.golonganId" disabled="true"
                                          listKey="golonganId" listValue="golonganName" headerKey="" headerValue="" cssClass="form-control"/>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label class="control-label"><small>Keterangan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea rows="2" id="keterangan1" name="carrerPath.keterangan" required="true" disabled="true" cssClass="form-control"/>
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
                            <i class="fa fa-trash"></i>
                            Delete
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

