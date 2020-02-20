<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/tld/displaytag-el.tld" %>

<html>
<head>
    <script type="text/javascript">

        $.subscribe('beforeProcessSave', function (event, data) {
            /*var kodeAlat = $("#kodeAlat").val();
             var namaAlat = $("#namaAlat").val();*/

            var medicalRecordId = '<s:property value="medicalRecord.medicalRecordId"/>';


            if (medicalRecordId != '' ) {

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
                if (medicalRecordId =='') {
                    msg = 'Field <strong>medical Record Id</strong> is required.' + '<br/>';
                }


                document.getElementById('errorValidationMessage').innerHTML = msg;
                document.getElementById("errorValidationMessage").style.color = "red";

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
            //window.location.reload(true);
        };

        function callSearch() {
            $('#waiting_dialog').dialog('close');
            $('#view_dialog_menu').dialog('close');
            window.location.reload(true);
        };

        function changeTipe(){
            var ck = document.getElementById("check");
            if (ck.checked == true){
                document.getElementById("flagApprove").value = "N";
            } else {
                document.getElementById("flagApprove").value = "";
            }
        }




    </script>

</head>

<body bgcolor="#FFFFFF">
<table width="100%" align="center">
    <tr>
        <td align="center">
            <s:form id="" method="post" theme="simple" namespace="/medicalrecord" action="saveApprove_medicalrecord" cssClass="well form-horizontal">

                <s:hidden name="addOrEdit"/>
                <s:hidden name="delete"/>



                <legend align="left">Medical Record</legend>


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
                            <label class="control-label"><small>Medical Record Id :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="medicalRecordId" name="medicalRecord.medicalRecordId" cssClass="form-control"  readonly="true"/>
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
                                <s:select list="#comboBranch.listOfComboBranches" id="branchId" name="medicalRecord.branchId"
                                          listKey="branchId" listValue="branchName" headerKey="" headerValue="[Select one]"
                                          cssClass="form-control" disabled="true"/>
                                <s:hidden name="medicalRecord.branchId"></s:hidden>                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nip :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="nip" name="medicalRecord.nip" cssClass="form-control" readonly="true"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Nama :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="namaBerobat" name="medicalRecord.namaBerobat" cssClass="form-control"  readonly="true"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>tanggal berobat :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="tanggalBerobat" name="medicalRecord.tanggalBerobat" cssClass="form-control" readonly="true"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tipe Berobat :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="tipeBerobatName" name="medicalRecord.tipeBerobatName" cssClass="form-control"  readonly="true"/>
                                <s:hidden name="medicalRecord.tipeOrangBerobat"></s:hidden>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Keluarga :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="keluarga" name="medicalRecord.namaKeluarga" cssClass="form-control"  readonly="true"/>
                                <s:hidden name="medicalRecord.keluargaId"></s:hidden>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Tipe Pengobatan :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="tipePengobatanName" name="medicalRecord.tipePengobatanName" cssClass="form-control"  readonly="true"/>
                                <s:hidden name="medicalRecord.tipePengobatan"></s:hidden>
                                <s:hidden name="medicalRecord.createdDate"></s:hidden>
                                <s:hidden name="medicalRecord.createdWho"></s:hidden>
                                <s:hidden name="medicalRecord.flag"></s:hidden>
                                <s:hidden name="medicalRecord.action"></s:hidden>
                                <s:hidden id="flagApprove" name="medicalRecord.flagApprove"></s:hidden>
                                <s:hidden  name="medicalRecord.flagSuratJaminan"></s:hidden>
                                <s:hidden  name="medicalRecord.noSuratJaminan"></s:hidden>                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Rumah Sakit :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="rsName" name="medicalRecord.rsName" cssClass="form-control"  readonly="true"/>
                                <s:hidden name="medicalRecord.rsId"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Kelas Layanan RS :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textfield id="rsKelasName" name="medicalRecord.rsKelasName" cssClass="form-control"  readonly="true"/>
                                <s:hidden name="medicalRecord.rsKelasName"/>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label"><small>Note :</small></label>
                        </td>
                        <td>
                            <table>
                                <s:textarea id="note" name="medicalRecord.note" cssClass="form-control" rows="4" disabled="true"/>
                            </table>
                        </td>
                    </tr>
                </table>



                <br>
                <div class="form-group">
                    <div class="col-md-12 col-sm-12">
                        <center>
                                <%--<sj:submit targets="crud" type="button" cssClass="btn btn-primary" formIds="approveForm" id="save" name="save"--%>
                                <%--onBeforeTopics="beforeProcessSave" onCompleteTopics="closeDialog,successDialog"--%>
                                <%--onSuccessTopics="successDialog" onErrorTopics="errorDialog" >--%>
                                <%--<i class="icon-ok-sign icon-white"></i>--%>
                                <%--Save--%>
                                <%--</sj:submit>--%>
                            <button type="button" id="cancel" class="btn btn-default" onclick="cancelBtn();">
                                <i class="icon-remove-circle"/> Cancel
                            </button>
                        </center>

                    </div>
                </div>


                <div id="actions" class="form-actions">
                    <table>
                        <tr>
                            <td></td>
                            <td>
                                <div id="crud">
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
                                                                      callSearch();
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
                                </div>
                            </td>
                        </tr>
                    </table>

                    <center>
                        <table width="40%">
                            <tr>
                                <td align="center">
                                    <br>
                                    <label>Data Biaya Pengobatan</label>
                                    <br>
                                    <s:set name="listOfResult" value="#session.listOfResultPengobatan" scope="request" />
                                    <display:table name="listOfResult" class="table table-condensed table-striped table-hover"
                                                   requestURI="paging_displaytag_medicalrecord.action" export="false" id="row" pagesize="10" style="font-size:10">

                                        <display:column property="pengobatanId" sortable="true" title="Pengobatan Id" />
                                        <display:column property="biayaPengobatanId" sortable="true" title="Biaya Pengobatan Id" />
                                        <display:column property="namaBiayaPengobatan" sortable="true" title="Biaya Pengobatan" />
                                        <display:column property="jumlah" sortable="true" title="jumlah"  />
                                        <%--<display:column property="action" sortable="true" title="CreatedWho"/>--%>
                                    </display:table>
                                </td>
                            </tr>
                        </table>

                        <table width="40%">
                            <tr>
                                <td align="center">
                                    <br>
                                    <label>Bukti Pengobatan</label>
                                    <br>
                                    <sj:dialog id="view_dialog_view" openTopics="showDialogView" modal="true"
                                               height="500" width="900" autoOpen="false"
                                               title="View">
                                        <center><img border="0" src="<s:url value="/pages/images/loading11.gif"/>" alt="Loading..."/></center>
                                    </sj:dialog>

                                    <s:set name="listOfResultBukti" value="#session.listOfResultBuktiPengobatan" scope="request" />
                                    <display:table name="listOfResultBukti" class="table table-condensed table-striped table-hover"
                                                   requestURI="paging_displaytag_medicalrecord.action" export="false" id="row" pagesize="10" style="font-size:10">

                                        <display:column property="pengobatanId" sortable="true" title="Pengobatan Id" />
                                        <display:column property="buktiId" sortable="true" title="Bukti Pengobatan Id" />
                                        <display:column property="fileName" sortable="true" title="File Name" />
                                        <display:column property="keterangan" sortable="true" title="Keterangan"  />

                                        <display:column media="html" title="View" style="text-align:center;font-size:9">
                                            <s:url var="urlViewDoc" namespace="/medicalrecord" action="viewDoc_medicalrecord" escapeAmp="false">
                                                <s:param name="id"><s:property value="#attr.row.buktiId" /></s:param>
                                                <s:param name="flag"><s:property value="#attr.row.flag" /></s:param>
                                            </s:url>
                                            <sj:a onClickTopics="showDialogView" href="%{urlViewDoc}">
                                                <img border="0" src="<s:url value="/pages/images/view_detail_project.png"/>" name="icon_trash">
                                            </sj:a>
                                        </display:column>
                                        <%--<display:column property="action" sortable="true" title="CreatedWho"/>--%>

                                    </display:table>
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

